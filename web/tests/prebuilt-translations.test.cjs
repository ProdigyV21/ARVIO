const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const os = require('node:os');
const path = require('node:path');
const crypto = require('node:crypto');

test('web-only packaging validates the checked-in dictionaries without reading Android resources', async () => {
  const { validatePrebuiltTranslations } = await import('../scripts/validate-prebuilt-translations.mjs');
  const checked = validatePrebuiltTranslations();
  assert.ok(checked.locales > 40);
  assert.ok(checked.phrases > 500);
});

test('prebuilt validation rejects missing/stale assets and placeholder loss rather than skipping generation', async (t) => {
  const { validatePrebuiltTranslations } = await import('../scripts/validate-prebuilt-translations.mjs');
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'arvio-prebuilt-i18n-'));
  t.after(() => fs.rmSync(root, { recursive: true, force: true }));
  fs.mkdirSync(path.join(root, 'lib/i18n'), { recursive: true });
  fs.mkdirSync(path.join(root, 'public/i18n'), { recursive: true });
  const phrases = ['Hello {value0}'];
  const dictionary = { 'Hello {value0}': 'Hallo {value0}' };
  const save = (file, value) => fs.writeFileSync(path.join(root, file), JSON.stringify(value));
  const saveManifest = value => save('lib/i18n/manifest.json', { nl: crypto.createHash('sha256').update(JSON.stringify(value)).digest('hex').slice(0, 12) });
  save('lib/i18n/languages.json', [{ code: 'en-US', label: 'English' }, { code: 'nl-NL', label: 'Dutch' }]);
  save('lib/i18n/phrases.json', phrases);
  assert.throws(() => validatePrebuiltTranslations(root, phrases));
  saveManifest(dictionary);
  assert.throws(() => validatePrebuiltTranslations(root, phrases), /ENOENT/);
  save('public/i18n/nl.json', dictionary);
  assert.deepEqual(validatePrebuiltTranslations(root, phrases), { locales: 1, phrases: 1 });
  assert.throws(() => validatePrebuiltTranslations(root, [...phrases, 'New UI']), /stale/);
  save('public/i18n/nl.json', { 'Hello {value0}': 'Hallo' });
  assert.throws(() => validatePrebuiltTranslations(root, phrases), /hash/);
  saveManifest({ 'Hello {value0}': 'Hallo' });
  assert.throws(() => validatePrebuiltTranslations(root, phrases), /Incomplete/);
  save('lib/i18n/manifest.json', { '../secret': 'a'.repeat(12) });
  assert.throws(() => validatePrebuiltTranslations(root, phrases));
});

test('only the explicit container translation mode uses prebuilt assets; hosted default still generates', () => {
  const build = fs.readFileSync(path.join(__dirname, '../scripts/build.mjs'), 'utf8');
  assert.match(build, /env\.ARVIO_TRANSLATION_MODE \|\| "generate"/);
  assert.match(build, /translationMode === "generate"[\s\S]*import\('\.\/generate-translations\.mjs'\)/);
  assert.match(build, /translationMode === "prebuilt"[\s\S]*validatePrebuiltTranslations\(\)/);
  const dockerfile = fs.readFileSync(path.join(__dirname, '../Dockerfile'), 'utf8');
  assert.match(dockerfile, /ARVIO_TRANSLATION_MODE=prebuilt/);
});
