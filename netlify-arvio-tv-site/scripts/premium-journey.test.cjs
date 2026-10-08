const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const vm = require('node:vm');
const path = require('node:path');

const code = fs.readFileSync(path.join(__dirname, '../assets/premium-journey.js'), 'utf8');
const id = '38b5a038-5e30-4325-81ad-a2e6d000de18';

function run({ url = 'https://arvio.tv/premium/?lang=es-ES', referrer = '', navigator = {}, fetchFails = false,
  destinations = ['/premium/?lang=es-ES', 'https://web.arvio.tv/?intent=trial', 'https://ko-fi.com/arvio/tiers', 'https://github.com/ProdigyV21/ARVIO'] } = {}) {
  const links = destinations.map(destination => {
    const options = typeof destination === 'string' ? { href: destination } : destination;
    const attributes = new Map([['href', options.href]]);
    if (options.download) attributes.set('download', '');
    if (options.target) attributes.set('target', options.target);
    return {
      get href() { return new URL(attributes.get('href'), url).href; },
      set href(value) { attributes.set('href', value); },
      getAttribute: name => attributes.get(name) ?? null,
      setAttribute: (name, value) => attributes.set(name, value),
      hasAttribute: name => attributes.has(name),
      dataset: options.placement ? { premiumPlacement: options.placement } : {},
      closest(selector) { return selector === 'a[href]' || selector === options.region ? this : null; },
    };
  });
  const events = [], requests = [], timers = [], listeners = {};
  const window = { location: { href: url }, crypto: { randomUUID: () => id }, setTimeout: callback => timers.push(callback) };
  for (const name of ['localStorage', 'sessionStorage']) Object.defineProperty(window, name, { get() { throw new Error('Storage must not be touched'); } });
  const document = { referrer, querySelectorAll: () => links, addEventListener: (name, handler) => { listeners[name] = handler; } };
  Object.defineProperty(document, 'cookie', { get() { throw new Error('Cookies must not be touched'); }, set() { throw new Error('Cookies must not be touched'); } });
  vm.runInNewContext(code, { URL, Set, navigator, window, document, fetch: (endpoint, options) => {
    requests.push({ endpoint, options });
    events.push(JSON.parse(options.body));
    if (fetchFails === 'sync') throw new Error('blocked');
    return fetchFails ? Promise.reject(new Error('offline')) : Promise.resolve({ ok: true });
  } });
  return { links, events, requests, listeners,
    activate(index, options = {}) {
      const event = { type: 'click', button: 0, isTrusted: true, defaultPrevented: false, target: links[index],
        preventDefault() { throw new Error('Navigation must remain native'); }, ...options };
      listeners[event.type]?.(event);
      // This is the href the browser's default activation would read.
      return links[index].href;
    },
    flushTimers() { while (timers.length) timers.shift()(); },
  };
}

test('content hrefs remain clean before activation, even with an incoming campaign', () => {
  const result = run({ url: `https://arvio.tv/arvio-web/?arvio_journey=${id}&utm_source=reddit&utm_campaign=guides`,
    destinations: ['/guides/', '/premium/?lang=nl#plans', 'https://web.arvio.tv/?intent=trial'] });
  assert.deepEqual(result.links.map(link => link.getAttribute('href')), ['/guides/', '/premium/?lang=nl#plans', 'https://web.arvio.tv/?intent=trial']);
  assert.deepEqual(result.events.map(event => event.event_name), ['guide_page_view']);
  result.flushTimers();
  assert.equal(result.links[0].getAttribute('href'), '/guides/');
});

test('native primary, keyboard, modifier, new-tab and middle clicks receive attribution', () => {
  for (const activation of [{}, { detail: 0 }, { ctrlKey: true }, { metaKey: true }, { shiftKey: true }, { altKey: true }, { type: 'auxclick', button: 1 }]) {
    const result = run({ url: `https://arvio.tv/?arvio_journey=${id}&utm_source=Discord&utm_medium=social&utm_campaign=launch&utm_content=nav`,
      destinations: [{ href: '/guides/?lang=nl#setup', target: '_blank' }] });
    const clicked = new URL(result.activate(0, activation));
    assert.equal(clicked.searchParams.get('arvio_journey'), id);
    assert.equal(clicked.searchParams.get('utm_source'), 'discord');
    assert.equal(clicked.searchParams.get('utm_medium'), 'social');
    assert.equal(clicked.searchParams.get('utm_campaign'), 'launch');
    assert.equal(clicked.searchParams.get('utm_content'), 'nav');
    assert.equal(clicked.searchParams.get('lang'), 'nl');
    assert.equal(clicked.hash, '#setup');
    assert.equal(result.links[0].getAttribute('target'), '_blank');
    assert.equal(result.events.length, 0);
    result.flushTimers();
    assert.equal(result.links[0].getAttribute('href'), '/guides/?lang=nl#setup');
  }
});

test('synthetic, canceled and right-button events leave hrefs and measurement untouched', () => {
  for (const activation of [{ isTrusted: false }, { defaultPrevented: true }, { button: 2 }, { type: 'auxclick', button: 2 }, { type: 'auxclick', button: 0 }]) {
    const result = run({ url: 'https://arvio.tv/', destinations: ['/guides/', 'https://web.arvio.tv/'] });
    for (const index of [0, 1]) {
      const original = result.links[index].getAttribute('href');
      result.activate(index, activation);
      assert.equal(result.links[index].getAttribute('href'), original);
    }
    assert.equal(result.events.length, 0);
  }
});

test('nested click targets work and repeated clicks restore the original relative href', () => {
  const result = run({ url: 'https://arvio.tv/', destinations: ['/guides/'] });
  result.activate(0, { target: { closest: selector => selector === 'a[href]' ? result.links[0] : null } });
  result.activate(0);
  result.flushTimers();
  assert.equal(result.links[0].getAttribute('href'), '/guides/');
  assert.doesNotThrow(() => result.activate(0, { target: null }));
});

test('restoration does not overwrite a link changed by another handler', () => {
  const result = run({ url: 'https://arvio.tv/', destinations: ['/guides/'] });
  result.activate(0);
  result.links[0].setAttribute('href', '/premium/');
  result.flushTimers();
  assert.equal(result.links[0].getAttribute('href'), '/premium/');
});

test('web funnel clicks retain intent and placement while external membership URLs remain clean', () => {
  const result = run({ url: 'https://arvio.tv/', destinations: [
    { href: 'https://web.arvio.tv/?intent=trial', placement: 'hero' },
    { href: 'https://ko-fi.com/arvio/tiers', region: 'footer' },
    'https://example.org/',
  ] });
  const web = new URL(result.activate(0));
  assert.equal(web.searchParams.get('arvio_journey'), id);
  assert.equal(web.searchParams.get('intent'), 'trial');
  assert.equal(result.activate(1), 'https://ko-fi.com/arvio/tiers');
  assert.equal(result.activate(2), 'https://example.org/');
  result.activate(0, { type: 'auxclick', button: 1 });
  result.activate(1);
  assert.deepEqual(result.events.map(event => [event.event_name, event.metadata.content]), [['web_clicked', 'hero'], ['membership_clicked', 'footer']]);
  for (const request of result.requests) {
    assert.equal(request.endpoint, 'https://auth.arvio.tv/.netlify/functions/premium-funnel-visit');
    assert.equal(request.options.credentials, 'omit');
    assert.equal(request.options.keepalive, true);
  }
  result.flushTimers();
  assert.equal(result.links[0].getAttribute('href'), 'https://web.arvio.tv/?intent=trial');
});

test('same-page anchors, assets, redirects, downloads and unsafe protocols are never decorated', () => {
  const destinations = ['#setup', '/?lang=nl', '/assets/image/', '/go/premium/', '/media-kit/arvio-media-kit.zip',
    { href: '/guides/', download: true }, 'mailto:hello@arvio.tv', 'javascript:void(0)', 'https://web.arvio.tv.evil.invalid/', 'https://example.org/'];
  const result = run({ url: 'https://arvio.tv/', destinations });
  for (let index = 0; index < destinations.length; index++) {
    const original = result.links[index].getAttribute('href');
    result.activate(index);
    assert.equal(result.links[index].getAttribute('href'), original);
  }
  assert.equal(result.events.length, 0);
});

test('privacy signals and untrusted page origins disable click attribution and analytics', () => {
  for (const options of [{ navigator: { doNotTrack: '1' } }, { navigator: { globalPrivacyControl: true } }, { url: 'https://example.org/premium/' }]) {
    const result = run({ ...options, destinations: ['/guides/', 'https://web.arvio.tv/'] });
    result.activate(0);
    result.activate(1);
    assert.deepEqual(result.links.map(link => link.getAttribute('href')), ['/guides/', 'https://web.arvio.tv/']);
    assert.equal(result.events.length, 0);
  }
});

test('unavailable analytics does not delay attributed native navigation', async () => {
  for (const fetchFails of [true, 'sync']) {
    const result = run({ url: 'https://arvio.tv/', fetchFails, destinations: ['https://web.arvio.tv/?intent=trial'] });
    assert.equal(new URL(result.activate(0)).searchParams.get('arvio_journey'), id);
    await Promise.resolve();
    result.flushTimers();
    assert.equal(result.links[0].getAttribute('href'), 'https://web.arvio.tv/?intent=trial');
  }
});

module.exports = { run, id };

test('guide landings record only bounded labels and carry the journey into Premium', () => {
  for (const [route, page] of [['/jellyfin-android-tv/', 'jellyfin'], ['/plex-emby-jellyfin/', 'servers'], ['/stremio-addons-android-tv/', 'addons'], ['/pt-br/jellyfin-android-tv/', 'jellyfin']]) {
    const result = run({ url: `https://arvio.tv${route}?utm_source=google&utm_campaign=discovery#private-fragment`, destinations: ['/premium/'] });
    assert.equal(result.events[0].event_name, 'guide_page_view');
    assert.equal(result.events[0].metadata.page, page);
    assert.equal(result.events[0].metadata.source, 'google');
    assert.equal(JSON.stringify(result.events).includes('private-fragment'), false);
    assert.equal(new URL(result.activate(0)).searchParams.get('arvio_journey'), id);
    result.flushTimers();
    assert.equal(result.links[0].getAttribute('href'), '/premium/');
  }
});
test('unknown paths and Studio previews do not generate guide landing events', () => {
  for (const route of ['/private-account/', '/collection-studio/#studio=private']) {
    const result = run({ url: 'https://arvio.tv' + route, destinations: [] });
    assert.equal(result.events.length, 0);
  }
});
test('guide measurement respects privacy opt-outs without changing links', () => {
  for (const navigator of [{ doNotTrack: '1' }, { globalPrivacyControl: true }]) {
    const result = run({ url: 'https://arvio.tv/jellyfin-android-tv/', navigator, destinations: ['/premium/'] });
    assert.equal(result.events.length, 0);
    assert.equal(result.activate(0), 'https://arvio.tv/premium/');
  }
});
test('search referrers are organic but cannot override explicit campaign medium', () => {
  const organic = run({ url: 'https://arvio.tv/jellyfin-android-tv/', referrer: 'https://www.google.nl/search?q=private-search' });
  assert.equal(organic.events[0].metadata.medium, 'organic');
  assert.equal(organic.events[0].metadata.source, 'www.google.nl');
  assert.equal(JSON.stringify(organic.events).includes('private-search'), false);
  const paid = run({ url: 'https://arvio.tv/jellyfin-android-tv/?utm_medium=cpc', referrer: 'https://www.google.com/' });
  assert.equal(paid.events[0].metadata.medium, 'cpc');
  const fake = run({ url: 'https://arvio.tv/jellyfin-android-tv/', referrer: 'https://www.google.com.evil.invalid/' });
  assert.equal(fake.events[0].metadata.medium, 'referral');
});
