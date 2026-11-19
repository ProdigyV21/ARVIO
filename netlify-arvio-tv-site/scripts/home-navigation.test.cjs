const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');

const site = path.resolve(__dirname, '..');

for (const file of ['index.html', 'es/index.html', 'pt-br/index.html']) {
  test(`${file}: hero promotes the current release and Google Play`, () => {
    const html = fs.readFileSync(path.join(site, file), 'utf8');
    assert.match(html, /class="release" href="https:\/\/github\.com\/ProdigyV21\/ARVIO\/releases\/tag\/v2\.3\.1"/u);
    assert.doesNotMatch(html, /releases\/tag\/v2\.0\.0/u);
    const store = html.match(/<a class="hero-store"[^>]*>[\s\S]*?<\/a>/u)?.[0];
    assert.ok(store, 'Google Play must be visible in the hero');
    assert.match(store, /href="https:\/\/play\.google\.com\/store\/apps\/details\?id=com\.arvio\.tv"/u);
    assert.match(store, /<img src="\/assets\/badge-google-play.png" width="646" height="250" alt="[^"]+"/u);
    assert.ok(fs.existsSync(path.join(site, 'assets/badge-google-play.png')));
  });
  test(`${file}: the free APK and ARVIO Web stay together in the header`, () => {
    const html = fs.readFileSync(path.join(site, file), 'utf8');
    const header = html.match(/<header\b[^>]*>([\s\S]*?)<\/header>/u)?.[1];
    const actions = header?.match(/<div class="nav-actions">([\s\S]*?)<\/div>/u)?.[1];
    assert.ok(actions, 'Header actions must be grouped');
    assert.match(actions, /class="button small glass nav-apk" href="https:\/\/github\.com\/ProdigyV21\/ARVIO\/releases\/latest"/u);
    assert.match(actions, /class="button small mint-button nav-premium" href="https:\/\/web\.arvio\.tv\/" data-premium-placement="header-membership"/u);
    assert.ok(actions.indexOf('nav-apk') < actions.indexOf('nav-premium'));
    assert.match(actions, { 'es/index.html': />Abrir ARVIO Web </u, 'pt-br/index.html': />Abrir o ARVIO Web </u }[file] ?? />Open ARVIO Web </u);
    assert.ok(!actions.includes('target="_blank"'), 'ARVIO Web should navigate directly');
  });
}
