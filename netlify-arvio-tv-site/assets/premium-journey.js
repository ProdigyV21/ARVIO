/* Short-lived, first-party campaign measurement. No cookies or browser storage. */
(function () {
  'use strict';
  if (navigator.doNotTrack === '1' || navigator.globalPrivacyControl === true) return;
  const here = new URL(window.location.href);
  if (!['arvio.tv', 'www.arvio.tv', 'localhost', '127.0.0.1'].includes(here.hostname)) return;
  const uuid = /^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i;
  const incoming = here.searchParams.get('arvio_journey') || '';
  const journey = uuid.test(incoming) ? incoming.toLowerCase() : window.crypto?.randomUUID?.();
  if (!journey) return;
  const clean = value => /^[a-z0-9._-]{1,80}$/i.test(value || '') ? value.toLowerCase() : '';
  let referrer = '';
  try { referrer = clean(new URL(document.referrer).hostname); } catch {}
  const organicReferrer = /^(?:www\.)?google\.(?:com|[a-z]{2}|com\.[a-z]{2}|co\.[a-z]{2})$/.test(referrer) ||
    ['www.bing.com', 'bing.com', 'duckduckgo.com', 'www.duckduckgo.com'].includes(referrer);
  const campaign = {
    source: clean(here.searchParams.get('utm_source')) || referrer || 'direct',
    medium: clean(here.searchParams.get('utm_medium')) || (organicReferrer ? 'organic' : referrer ? 'referral' : 'direct'),
    campaign: clean(here.searchParams.get('utm_campaign')) || 'premium',
    content: clean(here.searchParams.get('utm_content')) || '',
  };
  // Bounded editorial route labels only, never arbitrary paths, queries or hashes.
  // Studio intentionally keeps its network-free CSP and is not instrumented.
  const guides = {
    '/guides/': 'guides', '/android-tv-media-hub/': 'android',
    '/jellyfin-android-tv/': 'jellyfin', '/plex-emby-jellyfin/': 'servers',
    '/stremio-addons-android-tv/': 'addons', '/debrid-usenet-android-tv/': 'debrid',
    '/trakt-simkl-sync/': 'tracking', '/live-tv-epg/': 'live',
    '/ai-subtitles-android-tv/': 'subtitles', '/fire-tv-media-player/': 'firetv',
    '/arvio-web/': 'browser', '/collections-catalogs/': 'collections',
    '/self-host-arvio-web/': 'selfhost', '/browser-playback-guide/': 'playback',
    '/pt-br/guias/': 'guides', '/es/guias/': 'guides',
    '/pt-br/jellyfin-android-tv/': 'jellyfin', '/es/jellyfin-android-tv/': 'jellyfin',
    '/pt-br/plex-emby-jellyfin/': 'servers', '/es/plex-emby-jellyfin/': 'servers',
    '/pt-br/central-midia-android-tv/': 'android', '/es/centro-multimedia-android-tv/': 'android',
    '/pt-br/arvio-web/': 'browser', '/es/arvio-web/': 'browser',
    '/pt-br/arvio-fire-tv/': 'firetv', '/es/arvio-fire-tv/': 'firetv',
    '/pt-br/debrid-usenet-android-tv/': 'debrid', '/es/debrid-usenet-android-tv/': 'debrid',
    '/pt-br/sincronizacao-trakt-simkl/': 'tracking', '/es/sincronizacion-trakt-simkl/': 'tracking',
    '/pt-br/tv-ao-vivo-epg/': 'live', '/es/tv-en-vivo-epg/': 'live',
    '/pt-br/legendas-ia-android-tv/': 'subtitles', '/es/subtitulos-ia-android-tv/': 'subtitles',
  };
  const page = /\/premium\/?$/.test(here.pathname) ? 'premium' : guides[here.pathname] || 'home';
  const sent = new Set();
  function record(eventName, placement) {
    if (sent.has(eventName)) return;
    sent.add(eventName);
    try {
      fetch('https://auth.arvio.tv/.netlify/functions/premium-funnel-visit', {
        method: 'POST', mode: 'cors', credentials: 'omit', keepalive: true,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ event_name: eventName, journey_id: journey,
          metadata: { ...campaign, content: campaign.content || placement || page, page } }),
      }).catch(() => {});
    } catch {}
  }
  function classify(link) {
    if (link.hasAttribute('download')) return null;
    let url;
    try { url = new URL(link.href, here); } catch { return null; }
    if (!['https:', 'http:'].includes(url.protocol)) return null;
    // Carry attribution across content pages, never assets or same-page anchors.
    if (url.origin === here.origin && url.pathname !== here.pathname &&
        /\/$/.test(url.pathname) && !/^\/(assets|go)\//.test(url.pathname)) return { url, kind: 'internal' };
    if (url.hostname === 'web.arvio.tv') return { url, kind: 'web_clicked' };
    if (url.hostname === 'ko-fi.com' && /^\/arvio\/tiers\/?$/.test(url.pathname)) return { url, kind: 'membership_clicked' };
    return null;
  }
  function attribute(link, target) {
    const originalHref = link.getAttribute('href');
    target.url.searchParams.set('arvio_journey', journey);
    for (const [name, value] of Object.entries(campaign)) {
      if (value) target.url.searchParams.set('utm_' + name, value);
    }
    link.href = target.url.href;
    // Native navigation reads the href during activation, including new-tab clicks.
    // Restore it afterward for canceled navigation, modifier clicks and back/forward.
    window.setTimeout(() => {
      if (link.getAttribute('href') === target.url.href) link.setAttribute('href', originalHref);
    }, 0);
  }
  function clicked(event) {
    if (!event.isTrusted || event.defaultPrevented) return;
    if (event.type === 'auxclick' ? event.button !== 1 : event.button !== 0) return;
    const link = event.target?.closest?.('a[href]');
    if (!link) return;
    const target = classify(link);
    if (!target) return;
    // Keep crawlable links clean until a person activates them. Do not intercept
    // the browser's default action: targets, keyboard and modifiers remain native.
    if (target.kind !== 'membership_clicked') attribute(link, target);
    if (target.kind === 'internal') return;
    const placement = clean(link.dataset.premiumPlacement) || (link.closest('footer') ? 'footer' : link.closest('header') ? 'nav' : page);
    record(target.kind, placement);
  }
  document.addEventListener('click', clicked, { capture: true });
  document.addEventListener('auxclick', clicked, { capture: true });
  if (page === 'premium') record('premium_page_view');
  else if (guides[here.pathname]) record('guide_page_view');
})();
