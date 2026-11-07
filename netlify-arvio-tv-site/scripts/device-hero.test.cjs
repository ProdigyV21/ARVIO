// Localized homepages lead iPhone, iPad, Mac and Windows visitors to ARVIO Web
// (with its price); Android and TV visitors keep the free app first.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const root = path.join(__dirname, '..');
const homes = { de: 'de', fr: 'fr', nl: 'nl', it: 'it', sv: 'sv', da: 'da', nb: 'no', fi: 'fi', hi: 'hi' };

for (const [key, dir] of Object.entries(homes)) {
  test(`${dir}/ homepage has the device-aware hero`, () => {
    const html = fs.readFileSync(path.join(root, dir, 'index.html'), 'utf8');
    const locale = JSON.parse(fs.readFileSync(path.join(root, 'scripts/guide-locales', `${key}.json`), 'utf8')).locale;
    assert.match(html, /<div class="actions" id="hero-actions"><a class="button hero-apk" href="https:\/\/play\.google\.com\//);
    assert.match(html, new RegExp(`<a class="button secondary hero-web" href="/${dir}/premium/" data-premium-placement="hero-membership">`));
    assert.ok(html.includes(`<p class="hero-price-note" hidden>${locale.priceNote}</p>`), 'price note in the page language');
    assert.match(html, /if\(\/Android\/i\.test\(u\+' '\+p\)\)return;/, 'Android keeps the app first');
    assert.match(html, /a\.insertBefore\(web,apk\)/);
  });
}
