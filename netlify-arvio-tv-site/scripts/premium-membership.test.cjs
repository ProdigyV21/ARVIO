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
const membership = 'https://ko-fi.com/arvio/tiers';
function links(html) {
  return [...html.matchAll(/<a\b([^>]+)>([\s\S]*?)<\/a>/gu)].map(([, attributes, content]) => ({
    href: attributes.match(/\bhref="([^"]+)"/u)?.[1],
    placement: attributes.match(/\bdata-premium-placement="([^"]+)"/u)?.[1],
    classes: attributes.match(/\bclass="([^"]+)"/u)?.[1]?.split(/\s+/u) ?? [],
    text: content.replace(/<[^>]*>/gu, '').trim(),
  }));
}

test('Premium displays a real, prominent Ko-fi subscription before the separate trial', () => {
  const anchors = links(premium);
  const subscribe = anchors.find(link => link.placement === 'hero-membership');
  const trial = anchors.find(link => link.placement === 'hero-trial');
  assert.equal(subscribe.href, membership);
  assert.ok(subscribe.classes.includes('button') && subscribe.classes.includes('membership-button'));
  assert.ok(!subscribe.classes.includes('secondary'));
  assert.ok(anchors.indexOf(subscribe) < anchors.indexOf(trial));
  assert.equal(trial.href, 'https://web.arvio.tv/?intent=trial');
  assert.ok(trial.classes.includes('trial-button'));
  assert.match(premium, /<strong>\$2\.99<\/strong>/u);
  assert.match(premium, /24\/7 · ARVIO Web/u);
  assert.match(premium, /Monthly membership\. Cancel anytime through Ko-fi\./u);
  assert.match(premium, /No payment required\./u);
  assert.match(premium, /Android &amp; TV stay free/u);
});

test('Premium remains usable in English without JavaScript or translated-copy requests', () => {
  assert.equal(links(premium).filter(link => link.href === membership && link.classes.includes('membership-button')).length, 2);
  for (const key of new Set([...premium.matchAll(/data-copy="([A-Za-z]+)"/gu)].map(match => match[1]))) {
    for (const [locale, copy] of Object.entries(copies)) assert.ok(copy[key]?.trim(), `${locale}: ${key}`);
  }
});

test('Every Premium locale has explicit recurring membership terms and a localized action', () => {
  for (const [locale, copy] of Object.entries(copies)) {
    const base = locale.split('-')[0];
    const termKey = locale.startsWith('no-') ? 'nb' : locale in terms ? locale : base;
    assert.equal(copy.membershipTerms, terms[termKey], locale);
    assert.match(copy.join, /Ko-fi/u, locale);
    if (base !== 'en') assert.notEqual(copy.membershipTerms, terms.en, locale);
  }
  assert.match(copies['nl-NL'].coffee, /24\/7/u);
  assert.match(copies['en-US'].coffee, /monthly coffee/u);
});

test('English, Spanish and Portuguese homepages subscribe directly without losing the benefits route', () => {
  for (const file of ['index.html', 'es/index.html', 'pt-br/index.html']) {
    const html = read(file);
    const anchors = links(html);
    for (const placement of ['header-membership', 'hero-membership', 'premium-section']) {
      const link = anchors.find(anchor => anchor.placement === placement);
      assert.equal(link?.href, membership, `${file}: ${placement}`);
      assert.ok(link.classes.includes('button'), file);
      assert.ok(link.text, file);
    }
    const details = anchors.find(anchor => anchor.placement === 'hero-details');
    assert.match(details?.href, /^\/premium\//u);
    if (file !== 'index.html') assert.ok(details.href.includes('lang='));
    assert.match(html, /24\/7/u);
    // The client translation map legitimately contains English keys; check visible markup only.
    const visibleMarkup = html.replace(/<script\b[^>]*>[\s\S]*?<\/script>/gu, '');
    if (file !== 'index.html') assert.ok(!visibleMarkup.includes('Monthly membership. Cancel anytime through Ko-fi.'));
  }
});

test('New homepage and Premium placements measure clean Ko-fi links without blocking navigation', async () => {
  for (const url of ['https://arvio.tv/', 'https://arvio.tv/premium/?lang=nl-NL']) {
    for (const placement of ['header-membership', 'hero-membership', 'premium-section', 'support-membership']) {
      const result = run({ url, fetchFails: true, destinations: [{ href: membership, placement }] });
      assert.equal(result.activate(0), membership);
      await Promise.resolve();
      const click = result.events.find(event => event.event_name === 'membership_clicked');
      assert.equal(click.metadata.content, placement);
      assert.equal(click.metadata.page, url.includes('/premium/') ? 'premium' : 'home');
    }
  }
  for (const navigator of [{ doNotTrack: '1' }, { globalPrivacyControl: true }]) {
    const result = run({ navigator, destinations: [{ href: membership, placement: 'hero-membership' }] });
    assert.equal(result.activate(0), membership);
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
    assert.equal(result.elements.find(element => element.dataset.copy === 'join').textContent, copies[locale].join);
    assert.equal(result.elements.find(element => element.dataset.copy === 'membershipTerms').textContent, copies[locale].membershipTerms);
  }
  const fallback = await renderLocale('nl-NL', false);
  assert.equal(fallback.document.documentElement.lang, 'en');
});
