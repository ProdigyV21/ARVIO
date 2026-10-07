const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');

const site = path.resolve(__dirname, '..');

for (const file of ['index.html', 'es/index.html', 'pt-br/index.html']) {
  test(`${file}: Web App and Premium stay together in the header`, () => {
    const html = fs.readFileSync(path.join(site, file), 'utf8');
    const header = html.match(/<header\b[^>]*>([\s\S]*?)<\/header>/u)?.[1];
    const actions = header?.match(/<div class="nav-actions">([\s\S]*?)<\/div>/u)?.[1];
    assert.ok(actions, 'Header actions must be grouped');
    assert.match(actions, /class="button small glass nav-webapp" href="https:\/\/web\.arvio\.tv\/"/u);
    assert.match(actions, /class="button small mint-button nav-premium" href="https:\/\/ko-fi\.com\/arvio\/tiers"/u);
    assert.ok(actions.indexOf('nav-webapp') < actions.indexOf('nav-premium'));
    assert.match(actions, file === 'es/index.html' ? />App web </u : />Web App </u);
    assert.ok(!actions.includes('target="_blank"'), 'Web App should navigate directly');
  });
}
