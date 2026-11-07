const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const site = path.resolve(__dirname, '..');

test('the custom 404 uses Netlify fallback without shadowing TOML routes', () => {
  const redirects = fs.readFileSync(path.join(site, '_redirects'), 'utf8');
  assert.doesNotMatch(redirects, /^\/\*\s+\/404\.html/m);
  assert.match(redirects, /^\/companion\s+\/\s+301!/m);
  assert.match(redirects, /^\/companion\/\*\s+\/\s+301!/m);
  assert.ok(fs.existsSync(path.join(site, '404.html')));
});

test('all localized homepage wordmarks and release labels survive conflict resolution', () => {
  for (const [file, label] of [['index.html', 'LATEST RELEASE'], ['es/index.html', 'ULTIMA'], ['pt-br/index.html', 'ULTIMA']]) {
    const html = fs.readFileSync(path.join(site, file), 'utf8');
    assert.equal((html.match(/src="\/assets\/arvio-wordmark\.svg"/g) || []).length, 2);
    const release = html.match(/data-release-label="([^"]+)"/)[1].normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    assert.ok(release.startsWith(label));
    assert.match(html, /assets\/badge-google-play\.png/);
    assert.doesNotMatch(html, /^(<<<<<<<|=======|>>>>>>>)/m);
  }
  assert.doesNotThrow(() => JSON.parse(fs.readFileSync(path.join(site, 'scripts/homepage-copy-v2.json'), 'utf8')));
});
