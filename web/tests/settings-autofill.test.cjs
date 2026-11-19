// Password managers must not fill a saved e-mail into the Settings search box
// (it happened when a section with an API-key or password field opened).
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const read = (f) => fs.readFileSync(path.join(__dirname, '..', f), 'utf8');

test('the settings search is not a login field and stays read-only until focused', () => {
  const screen = read('components/settings/SettingsScreen.tsx');
  assert.match(screen, /name="arvio-settings-search" autoComplete="off"/);
  assert.match(screen, /readOnly=\{searchLocked\} onFocus=\{\(\) => setSearchLocked\(false\)\}/);
});

test('key and token fields are not offered to password managers', () => {
  const ui = read('components/settings/ui.tsx');
  assert.match(ui, /WebkitTextSecurity: "disc"/);
  for (const f of ['components/settings/sections/Account.tsx', 'components/settings/sections/HomeServer.tsx', 'components/settings/sections/Telegram.tsx']) {
    for (const m of read(f).matchAll(/<input[\s\S]*?\/>/g)) { if (/type="password"/.test(m[0])) assert.match(m[0], /autoComplete="new-password"/, f); }
  }
});
