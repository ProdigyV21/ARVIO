// Interface translations: the gap fill keeps coverage high, and sentences with
// a link inside are translated whole (word order differs per language).
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');

const dir = path.join(__dirname, '../public/i18n');
const locales = fs.readdirSync(dir).filter((f) => f.endsWith('.json'));
const phrases = JSON.parse(fs.readFileSync(path.join(__dirname, '../lib/i18n/phrases.json'), 'utf8'));
const norm = (s) => s.trim().toLocaleLowerCase('en').replace(/…/g, '...');

test('every language translates at least 90% of the interface phrases', () => {
  for (const file of locales) {
    const dictionary = JSON.parse(fs.readFileSync(path.join(dir, file), 'utf8'));
    const byNorm = new Map(Object.entries(dictionary).map(([k, v]) => [norm(k), v]));
    const translated = phrases.filter((p) => { const v = byNorm.get(norm(p)); return v && v !== p; }).length;
    assert.ok(translated / phrases.length >= 0.9, `${file}: ${translated}/${phrases.length}`);
  }
});

test('the device-code instruction is one sentence with the link as a placeholder', () => {
  const account = fs.readFileSync(path.join(__dirname, '../components/settings/sections/Account.tsx'), 'utf8');
  assert.match(account, /translateUi\("Open \{value0\} and enter this code, then come back here\.", \{ value0: LINK_SLOT \}\)/);
  assert.doesNotMatch(account, /translateUi\("Open "\)/);
  for (const file of locales) {
    const value = JSON.parse(fs.readFileSync(path.join(dir, file), 'utf8'))['Open {value0} and enter this code, then come back here.'];
    assert.ok(value && value.includes('{value0}') && value !== 'Open {value0} and enter this code, then come back here.', file);
  }
});
