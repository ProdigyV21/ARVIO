const { test } = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { playerSource } = require('./sources.cjs');
const root = path.resolve(__dirname, '../..');
const read = (name) => fs.readFileSync(path.join(root, name), 'utf8');

test('app, website and web credits retain the required TMDB notice and approved logo', () => {
  const notice = 'This product uses the TMDB API but is not endorsed or certified by TMDB.';
  for (const file of ['app/src/main/res/values/strings.xml', 'web/components/settings/sections/About.tsx', 'netlify-arvio-tv-site/credits/index.html']) {
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

// This suite gates the web deployment. Android's restored native trailer player
// is a separate implementation; keep this guard on the web code being shipped.
test('web runtime has no direct YouTube extraction implementation', () => {
  for (const folder of ['web/app', 'web/components', 'web/lib']) {
    const dir = path.join(root, folder);
    const files = fs.readdirSync(dir, { recursive: true }).filter(name => /\.(?:[cm]?js|jsx|ts|tsx)$/.test(name));
    assert.ok(files.length > 0, folder);
    for (const file of files) {
      const source = fs.readFileSync(path.join(dir, file), 'utf8');
      assert.doesNotMatch(source, /extractPlaybackSource|youtubei\/v1\/player|InAppYouTubeExtractor/, path.join(folder, file));
    }
  }
});

test('web YouTube player uses an unmodified embed with a separate toolbar', () => {
  const source = playerSource();
  const embed = read('web/components/player/YouTubeEmbed.tsx');
  const api = read('web/lib/youtubePlayback.ts');
  assert.match(api, /youtube-nocookie\.com\/embed/);
  assert.match(source, /youtube-player-toolbar/);
  assert.match(embed, /referrerPolicy = "strict-origin-when-cross-origin"/);
  assert.match(embed, /createElement\("iframe"\)/);
  assert.match(embed, /allowFullscreen = true/);
  assert.match(api, /www\.youtube\.com\/iframe_api/);
  const css = read('web/components/player/player.css');
  assert.match(css, /youtube-player-layout[^}]+grid-template-rows: auto minmax\(200px, 1fr\)/);
});
