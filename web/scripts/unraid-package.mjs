import { readFileSync, mkdirSync, writeFileSync } from 'node:fs';
import { resolve } from 'node:path';
import { fileURLToPath } from 'node:url';
import assert from 'node:assert/strict';

const root = fileURLToPath(new URL('../', import.meta.url));
const source = JSON.parse(readFileSync(resolve(root, 'package.json'), 'utf8'));
const expected = structuredClone(source);
const telegram = expected.dependencies.telegram;
assert.equal(typeof telegram, 'string', 'Original Telegram SDK must remain available for hosted builds.');
delete expected.dependencies.telegram;
expected.devDependencies.telegram = telegram;
const output = resolve(root, 'distribution-sources/unraid');
if (process.argv.includes('--prepare')) {
  // Only creates this image-specific manifest; never edits the hosted manifest.
  mkdirSync(output, { recursive: true });
  writeFileSync(resolve(output, 'package.json'), JSON.stringify(expected, null, 2) + '\n');
  writeFileSync(resolve(output, 'package-lock.json'), readFileSync(resolve(root, 'package-lock.json')));
} else {
  const manifest = JSON.parse(readFileSync(resolve(output, 'package.json'), 'utf8'));
  const lock = JSON.parse(readFileSync(resolve(output, 'package-lock.json'), 'utf8'));
  assert.deepEqual(manifest, expected, 'Regenerate isolated Unraid manifest after dependency changes.');
  assert.deepEqual(lock.packages[''].dependencies, expected.dependencies);
  assert.deepEqual(lock.packages[''].devDependencies, expected.devDependencies);
  assert.equal(lock.packages['node_modules/telegram'].dev, true, 'SDK is builder-only for type checking.');
  assert.equal(lock.packages['node_modules/@cryptography/aes'].dev, true, 'AES is builder-only, never a runtime dependency.');
  const mainLock = JSON.parse(readFileSync(resolve(root, 'package-lock.json'), 'utf8'));
  for (const [name, item] of Object.entries(lock.packages)) {
    if (!name) continue;
    const original = mainLock.packages[name];
    assert.ok(original, `Unexpected image-only package ${name}`);
    for (const field of ['version', 'resolved', 'integrity']) {
      assert.equal(item[field], original[field], `Image must not silently replace ${name} ${field}`);
    }
  }
  console.log('Unraid manifest is scoped; exact locked versions unchanged; Telegram/AES builder-only.');
}
