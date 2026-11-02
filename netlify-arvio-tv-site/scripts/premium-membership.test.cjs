const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const { run } = require('./premium-journey.test.cjs');

const site = path.resolve(__dirname, '..');
const read = file => fs.readFileSync(path.join(site, file), 'utf8');
const premium = read('premium/index.html');
const copies = JSON.parse(read('premium/languages.json'));
const terms = JSON.parse(read('scripts/premium-membership-terms.json'));
const web = 'https://web.arvio.tv/';
const kofi = 'https://ko-fi.com/arvio/tiers';
const offer = JSON.parse(read('scripts/premium-offer-copy.json'));
function links(html) {
  return [...html.matchAll(/<a\b([^>]+)>([\s\S]*?)<\/a>/gu)].map(([, attributes, content]) => ({
    href: attributes.match(/\bhref="([^"]+)"/u)?.[1],
    placement: attributes.match(/\bdata-premium-placement="([^"]+)"/u)?.[1],
    classes: attributes.match(/\bclass="([^"]+)"/u)?.[1]?.split(/\s+/u) ?? [],
    text: content.replace(/<[^>]*>/gu, '').trim(),
  }));
}

test('Premium leads with hosted ARVIO Web and sends every plan to the web app', () => {
  const anchors = links(premium);
  const open = anchors.find(link => link.placement === 'hero-membership');
  assert.equal(open.href, web);
  assert.ok(open.classes.includes('button') && open.classes.includes('membership-button'));
  for (const placement of ['plan-monthly', 'plan-yearly', 'support-membership']) {
    assert.equal(anchors.find(link => link.placement === placement)?.href, web, placement);
  }
  // Ko-fi is only a secondary management link, never a button.
  for (const link of anchors.filter(link => link.href?.startsWith('https://ko-fi.com'))) {
    assert.equal(link.placement, 'manage-membership');
    assert.ok(!link.classes.includes('button'));
  }
  assert.match(premium, /<strong>\$2\.99<\/strong>/u);
  assert.match(premium, /<strong>\$20<\/strong>/u);
  assert.match(premium, /Best value · save 44%/u);
  assert.match(premium, /One payment, no automatic renewal/u);
  assert.match(premium, /Message us within 7 days for a full refund\./u);
  assert.match(premium, /href="https:\/\/discord\.gg\/UavuEYMfQ4"/u);
  assert.doesNotMatch(premium, /coffee|trial|No payment required/iu);
  assert.match(premium, /Android &amp; TV stay free/u);
  // The support line closes the plans; it is never the headline.
  assert.ok(premium.indexOf('Your membership supports development.') > premium.indexOf('data-premium-placement="plan-yearly"'));
  assert.ok(premium.indexOf('<video') < premium.indexOf('id="plans"'), 'The tour belongs at the top');
  assert.match(premium, /<video[^>]+controls muted playsinline preload="none" poster="\/assets\/promo\/arvio-premium-poster.webp"/u);
});

test('The 12-month pass saving matches the monthly price', () => {
  assert.equal(Math.round((1 - 20 / (2.99 * 12)) * 100), 44);
});

test('Premium remains usable in English without JavaScript or translated-copy requests', () => {
  assert.equal(links(premium).filter(link => link.href === web && link.classes.includes('membership-button')).length, 3);
  for (const key of new Set([...premium.matchAll(/data-copy="([A-Za-z]+)"/gu)].map(match => match[1]))) {
    for (const [locale, copy] of Object.entries(copies)) assert.ok(copy[key]?.trim(), `${locale}: ${key}`);
  }
});

test('Every Premium locale has explicit monthly terms, plan copy and a localized action', () => {
  for (const [locale, copy] of Object.entries(copies)) {
    const base = locale.split('-')[0];
    const termKey = locale.startsWith('no-') ? 'nb' : locale in terms ? locale : base;
    assert.equal(copy.membershipTerms, terms[termKey], locale);
    assert.ok(copy.open.endsWith('ARVIO Web') || copy.open.includes('ARVIO Web'), locale);
    for (const key of Object.keys(offer.en)) assert.ok(copy[key]?.trim(), `${locale}: ${key}`);
    for (const key of ['trial', 'trialNoPayment', 'trialEligibility', 'afterTrial', 'coffee', 'join']) {
      assert.equal(Object.hasOwn(copy, key), false, `${locale}: stale ${key}`);
    }
    if (base !== 'en') assert.notEqual(copy.membershipTerms, terms.en, locale);
  }
  for (const locale of ['es-ES', 'pt-BR', 'nl-NL', 'de-DE', 'fr-FR', 'ja-JP']) {
    assert.notEqual(copies[locale].headline, offer.en.headline, locale);
    assert.notEqual(copies[locale].refund, offer.en.refund, locale);
  }
  assert.equal(copies['en-US'].refund, "Doesn't work on your device? Message us within 7 days for a full refund.");
});

test('Public Premium routes and generation sources never promise free trial access', () => {
  for (const file of [
    'index.html', 'es/index.html', 'pt-br/index.html', 'premium/index.html',
    'arvio-web/index.html', 'es/arvio-web/index.html', 'pt-br/arvio-web/index.html',
    'browser-playback-guide/index.html', 'premium-redirect.html', 'premium-redirect.js',
    'scripts/build-premium.py', 'scripts/generate-localized-homepages.mjs',
    'scripts/generate-localized-guides.mjs', 'media-kit/reviewer-brief.txt',
  ]) {
    assert.doesNotMatch(read(file), /\btrial\b|Iniciar teste gratuito|Iniciar prueba gratuita|Try it free for 3 days/iu, file);
  }
});

test('Legacy Premium and membership redirects open ARVIO Web without requesting a trial', () => {
  const { premiumDestination } = require('../premium-redirect');
  for (const placement of ['', '/nav', '/hero', '/spotlight', '/preview', '/details', '/faq', '/footer']) {
    const target = new URL(premiumDestination('/go/premium' + placement));
    assert.equal(target.origin, 'https://web.arvio.tv');
    assert.equal(target.searchParams.has('intent'), false);
    assert.equal(target.searchParams.get('utm_campaign'), 'premium');
  }
  for (const placement of ['/nav', '/spotlight', '/details', '/footer']) {
    assert.equal(new URL(premiumDestination('/go/membership' + placement)).origin, 'https://web.arvio.tv');
  }
  assert.equal(premiumDestination('/go/membership/hero'), null);
});

test('English, Spanish and Portuguese homepages send Premium CTAs to ARVIO Web and show both plans', () => {
  for (const file of ['index.html', 'es/index.html', 'pt-br/index.html']) {
    const html = read(file);
    const anchors = links(html);
    for (const placement of ['header-membership', 'hero-membership', 'premium-section', 'premium-yearly']) {
      const link = anchors.find(anchor => anchor.placement === placement);
      assert.equal(link?.href, web, `${file}: ${placement}`);
      assert.ok(link.classes.includes('button'), file);
      assert.ok(link.text, file);
    }
    const kofiLinks = anchors.filter(anchor => anchor.href?.startsWith('https://ko-fi.com'));
    assert.deepEqual(kofiLinks.map(anchor => anchor.placement), ['manage-membership'], file);
    assert.ok(!kofiLinks[0].classes.includes('button'), file);
    const details = anchors.find(anchor => anchor.placement === 'hero-details');
    // English links /premium/; the localized homepages link their own-language Premium page.
    if (file === 'index.html') assert.match(details?.href, /^\/premium\//u);
    else assert.equal(details?.href, `/${file.split('/')[0]}/premium/`, file);
    assert.equal((html.match(/class="plan(?: plan-best)?"/gu) || []).length, 2, file);
    assert.match(html, /class="plan plan-best"/u);
    assert.match(html, /href="https:\/\/discord\.gg\/UavuEYMfQ4">[^<]+<\/a> [^<]*7/u, file);
    // The client translation map legitimately contains English keys; check visible markup only.
    const visibleMarkup = html.replace(/<script\b[^>]*>[\s\S]*?<\/script>/gu, '');
    assert.doesNotMatch(visibleMarkup, /coffee|café|cafezinho/iu, file);
    const premiumSection = visibleMarkup.match(/<section id="premium"[\s\S]*?<\/section>/u)[0];
    assert.match(premiumSection, /class="premium-support">[^<]+<\/p><\/div><div class="premium-visual">/u, `${file}: support line is last`);
    if (file !== 'index.html') assert.ok(!visibleMarkup.includes('Look around first. You only pay when you press Play.'));
  }
});

test('Homepage hero puts the free app first by default and swaps for iPhone, iPad, Mac and Windows', () => {
  const html = read('index.html');
  const script = html.match(/<div class="actions" id="hero-actions">[\s\S]*?<\/div>\s*<script>([\s\S]*?)<\/script>/u)?.[1];
  assert.ok(script, 'Inline device script follows the hero actions');
  const agents = {
    android: ['Mozilla/5.0 (Linux; Android 14; Pixel 8) AppleWebKit/537.36 Chrome/128 Mobile Safari/537.36', 0],
    iphone: ['Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 Version/18.0 Mobile/15E148 Safari/604.1', 5],
    ipad: ['Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 Version/18.0 Safari/605.1.15', 5],
    mac: ['Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 Version/18.0 Safari/605.1.15', 0],
    windows: ['Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/128 Safari/537.36', 0],
    '': ['Mozilla/5.0 (X11; CrOS x86_64 14541.0.0) AppleWebKit/537.36 Chrome/128 Safari/537.36', 0],
  };
  for (const [expected, [userAgent, maxTouchPoints]] of Object.entries(agents)) {
    const label = hidden => ({ hidden });
    const make = name => ({ name, classes: new Set(name === 'apk' ? ['white'] : ['mint-button']), labels: [label(false), label(true)] });
    const apk = make('apk'), webLink = make('web');
    for (const link of [apk, webLink]) {
      link.classList = { replace: (a, b) => link.classes.delete(a) && link.classes.add(b) };
      link.querySelectorAll = () => link.labels;
    }
    const order = [apk, webLink];
    const attributes = {};
    const actions = { querySelector: selector => selector === '.hero-apk' ? apk : webLink,
      insertBefore: (node, before) => { order.splice(order.indexOf(node), 1); order.splice(order.indexOf(before), 0, node); } };
    vm.runInNewContext(script, { navigator: { userAgent, maxTouchPoints, platform: '' },
      document: { getElementById: () => actions, documentElement: { setAttribute: (name, value) => { attributes[name] = value; } } } });
    assert.equal(attributes['data-device'], expected || undefined, expected);
    if (['iphone', 'ipad', 'mac', 'windows'].includes(expected)) {
      assert.deepEqual(order.map(link => link.name), ['web', 'apk'], expected);
      assert.ok(apk.classes.has('glass') && webLink.classes.has('mint-button'), expected);
      assert.deepEqual(webLink.labels.map(item => item.hidden), [true, false], expected);
    } else {
      assert.deepEqual(order.map(link => link.name), ['apk', 'web'], expected || 'unknown');
      assert.ok(apk.classes.has('white'), expected || 'unknown');
      assert.deepEqual(webLink.labels.map(item => item.hidden), [false, true]);
      if (expected === 'android') assert.ok(webLink.classes.has('glass'));
    }
  }
});

test('Homepage and Premium placements measure web clicks and the secondary Ko-fi link without blocking navigation', async () => {
  for (const url of ['https://arvio.tv/', 'https://arvio.tv/premium/?lang=nl-NL']) {
    for (const placement of ['header-membership', 'hero-membership', 'premium-section', 'premium-yearly', 'plan-monthly', 'plan-yearly', 'support-membership']) {
      const result = run({ url, fetchFails: true, destinations: [{ href: web, placement }] });
      assert.equal(new URL(result.activate(0)).origin, 'https://web.arvio.tv');
      await Promise.resolve();
      const click = result.events.find(event => event.event_name === 'web_clicked');
      assert.equal(click.metadata.content, placement);
      assert.equal(click.metadata.page, url.includes('/premium/') ? 'premium' : 'home');
    }
    const manage = run({ url, fetchFails: true, destinations: [{ href: kofi, placement: 'manage-membership' }] });
    assert.equal(manage.activate(0), kofi);
    assert.equal(manage.events.find(event => event.event_name === 'membership_clicked').metadata.content, 'manage-membership');
  }
  for (const navigator of [{ doNotTrack: '1' }, { globalPrivacyControl: true }]) {
    const result = run({ navigator, destinations: [{ href: web, placement: 'hero-membership' }] });
    assert.equal(result.activate(0), web);
    assert.equal(result.events.length, 0);
  }
});

async function renderLocale(locale, available = true) {
  const elements = [...new Set([...premium.matchAll(/data-copy="([A-Za-z]+)"/gu)].map(match => match[1]))]
    .map(key => ({ dataset: { copy: key }, textContent: '' }));
  const images = Array.from({ length: 4 }, () => ({}));
  const hero = {};
  const select = { replaceChildren() {}, append() {}, setAttribute() {}, addEventListener() {} };
  const document = {
    documentElement: { lang: 'en' }, getElementById: () => select, createElement: () => ({}),
    querySelectorAll: selector => selector === '[data-copy]' ? elements : images,
    querySelector: () => hero,
  };
  await vm.runInNewContext(read('premium/premium.js'), {
    document, fetch: async () => ({ ok: available, json: async () => copies }),
    navigator: { languages: [locale] }, location: { href: 'https://arvio.tv/premium/' },
    URL, Intl, history: { replaceState() {} },
  });
  return { document, elements, select };
}

test('Premium renders the new terms correctly for all languages, including RTL', async () => {
  for (const locale of Object.keys(copies)) {
    const result = await renderLocale(locale);
    assert.equal(result.document.documentElement.lang, locale);
    assert.equal(result.document.documentElement.dir, /^(ar|he|fa|ur)-/u.test(locale) ? 'rtl' : 'ltr');
    assert.equal(result.elements.find(element => element.dataset.copy === 'open').textContent, copies[locale].open);
    assert.equal(result.elements.find(element => element.dataset.copy === 'membershipTerms').textContent, copies[locale].membershipTerms);
  }
  const fallback = await renderLocale('nl-NL', false);
  assert.equal(fallback.document.documentElement.lang, 'en');
});
