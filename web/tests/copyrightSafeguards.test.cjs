const { test } = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const root = path.resolve(__dirname, '../..');
const read = (name) => fs.readFileSync(path.join(root, name), 'utf8');

test('app, website and web credits retain the required TMDB notice and approved logo', () => {
  const notice = 'This product uses the TMDB API but is not endorsed or certified by TMDB.';
  for (const file of ['app/src/main/res/values/strings.xml', 'web/components/settings/SettingsScreen.tsx', 'netlify-arvio-tv-site/credits/index.html']) {
    assert.ok(read(file).includes(notice), file);
  }
  const logo = read('app/src/main/assets/tmdb-logo.svg');
  assert.equal(read('web/public/tmdb-logo.svg'), logo);
  assert.equal(read('netlify-arvio-tv-site/assets/tmdb-logo.svg'), logo);
});

test('onboarding offers configuration, not an unvetted external addon directory', () => {
  const prompt = read('web/components/shell/NoAddonsPrompt.tsx');
  assert.doesNotMatch(prompt, /stremio-addons\.net|Browse addons/);
  assert.match(prompt, /authorized to use/);
  assert.match(prompt, /setSection\("addons"\)/);
  assert.match(prompt, /setSection\("settings"\)/);
});

test('Android has no direct YouTube extraction implementation', () => {
  const dir = path.join(root, 'app/src/main/kotlin');
  for (const file of fs.readdirSync(dir, { recursive: true }).filter(name => name.endsWith('.kt'))) {
    const source = fs.readFileSync(path.join(dir, file), 'utf8');
    assert.doesNotMatch(source, /extractPlaybackSource|youtubei\/v1\/player|InAppYouTubeExtractor/, file);
  }
});

test('web YouTube player uses an unmodified embed with a separate toolbar', () => {
  const source = read('web/components/player/PlayerOverlay.tsx');
  assert.match(source, /youtube-nocookie\.com\/embed/);
  assert.match(source, /youtube-player-toolbar/);
  assert.match(source, /referrerPolicy="strict-origin-when-cross-origin"/);
  const css = read('web/app/globals.css');
  assert.match(css, /youtube-player-layout[^}]+grid-template-rows: auto minmax\(200px, 1fr\)/);
});
