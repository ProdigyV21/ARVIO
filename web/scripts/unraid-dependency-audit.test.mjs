import test from 'node:test';
import assert from 'node:assert/strict';
import { mkdir, mkdtemp, rm, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { tmpdir } from 'node:os';
import { auditUnraidDependencies, excludedSdkMarkers, isLegalFile, productionPackages, scanJavaScript } from './unraid-dependency-audit.mjs';

test('legal discovery includes Next generated LEGAL and LICENSE sidecars', () => {
  for (const name of ['LICENSE', 'LICENSES.md', 'COPYING.lib', 'THIRD-PARTY-NOTICES.txt', 'fetch.js.LEGAL.txt', 'bundle.js.LICENSE.txt', 'copyright.txt']) assert.equal(isLegalFile(name), true, name);
  for (const name of ['license-loader.js', 'notices.ts', 'package.json', 'header.js']) if (name !== 'license-loader.js' && name !== 'notices.ts') assert.equal(isLegalFile(name), false, name);
});
test('production graph excludes Telegram dev-only closure and root build-only peers', () => {
  const lock = { lockfileVersion: 3, packages: {
    '': { dependencies: { next: '1' }, devDependencies: { telegram: '1', playwright: '1' } },
    'node_modules/next': { version: '1', peerDependencies: { playwright: '1' } },
    'node_modules/playwright': { version: '1', dependencies: { 'playwright-core': '1' } },
    'node_modules/playwright-core': { version: '1' },
    'node_modules/telegram': { version: '1', dev: true, dependencies: { '@cryptography/aes': '1' } },
    'node_modules/@cryptography/aes': { version: '1', dev: true },
  } };
  assert.deepEqual(productionPackages(lock).map(pkg => pkg.name), ['next']);
  lock.packages[''].dependencies.telegram = '1';
  assert.deepEqual(productionPackages(lock).map(pkg => pkg.name), ['@cryptography/aes', 'next', 'telegram']);
});
test('canonical npm alias identity remains visible in production audit', () => {
  const lock = { lockfileVersion: 3, packages: { '': { dependencies: { helper: 'npm:telegram@1' } }, 'node_modules/helper': { name: 'telegram', version: '1' } } };
  assert.equal(productionPackages(lock)[0].name, 'telegram');
});
test('known markers and imports fail without flagging ordinary Telegram UI or WebCrypto AES', () => {
  for (const marker of excludedSdkMarkers) assert.ok(scanJavaScript(`console.log(${JSON.stringify(marker)})`).length);
  for (const source of ['require("telegram/sessions")', 'import("@cryptography/aes")', 'import { Api } from "telegram"']) assert.ok(scanJavaScript(source).length);
  assert.deepEqual(scanJavaScript('const label="Telegram unavailable in preview";crypto.subtle.encrypt({name:"AES-GCM"},key,data)'), []);
});
async function fixture(t) {
  const root = await mkdtemp(path.join(tmpdir(), 'arvio-unraid-audit-'));
  t.after(() => rm(root, { recursive: true, force: true }));
  const options = Object.fromEntries(['standalone', 'client', 'notices', 'input'].map(name => [name, path.join(root, name)]));
  options.lock = path.join(root, 'package-lock.json');
  for (const directory of Object.values(options).filter(filename => filename !== options.lock)) await mkdir(directory, { recursive: true });
  await writeFile(options.lock, JSON.stringify({ lockfileVersion: 3, packages: { '': { dependencies: { next: '1' }, devDependencies: { telegram: '1' } }, 'node_modules/next': { version: '1' }, 'node_modules/telegram': { version: '1', dev: true } } }));
  await writeFile(path.join(options.standalone, 'server.js'), 'console.log("server")');
  await writeFile(path.join(options.standalone, 'package.json'), JSON.stringify({ name: 'arvio', dependencies: { next: '1' }, devDependencies: { telegram: '1' } }));
  await writeFile(path.join(options.client, 'page.js'), 'console.log("Telegram disabled");');
  const rel = 'next/dist/compiled/@edge-runtime/primitives/fetch.js.LEGAL.txt';
  for (const filename of [path.join(options.input, rel), path.join(options.notices, 'files', rel)]) {
    await mkdir(path.dirname(filename), { recursive: true }); await writeFile(filename, 'Bundled MIT license notice');
  }
  return options;
}
test('complete artifact fixture passes without treating build-only notice records as runtime', async t => {
  const options = await fixture(t);
  await writeFile(path.join(options.notices, 'packages.json'), JSON.stringify([{ name: 'telegram', version: '1' }]));
  const result = await auditUnraidDependencies(options);
  assert.equal(result.passed, true);
  assert.equal(result.nextCompiledLegalFiles, 1);
  assert.equal(result.javaScriptFiles, 2);
  assert.ok(result.limitations.some(text => text.includes('No webpack module stats')));
});
test('changed legal sidecar and embedded SDK chunk are build-gate failures', async t => {
  const options = await fixture(t);
  await writeFile(path.join(options.notices, 'files/next/dist/compiled/@edge-runtime/primitives/fetch.js.LEGAL.txt'), 'changed');
  await writeFile(path.join(options.client, 'sdk.js'), 'const cache="GramJs:apiCache";');
  const result = await auditUnraidDependencies(options);
  assert.equal(result.passed, false);
  assert.ok(result.issues.some(issue => issue.kind === 'dependency-legal-file-not-preserved'));
  assert.ok(result.issues.some(issue => issue.kind === 'excluded-sdk-bundle-marker'));
});
test('runtime excluded package paths and declarations fail despite harmless UI copy', async t => {
  const options = await fixture(t);
  const packageFile = path.join(options.standalone, 'node_modules/telegram/package.json');
  await mkdir(path.dirname(packageFile), { recursive: true });
  await writeFile(packageFile, JSON.stringify({ name: 'telegram', version: '1' }));
  const result = await auditUnraidDependencies(options);
  assert.equal(result.passed, false);
  assert.ok(result.issues.some(issue => issue.kind === 'excluded-sdk-runtime-path'));
  assert.ok(result.issues.some(issue => issue.kind === 'excluded-sdk-runtime-declaration'));
});
test('webpack stats distinguish ignored aliases from included package and external modules', async t => {
  const options = await fixture(t), stats = path.join(path.dirname(options.lock), 'stats.json');
  await writeFile(stats, JSON.stringify({ modules: [{ identifier: 'telegram (ignored)' }, { identifier: '/app/node_modules/@cryptography/aes/dist/es/aes.js' }, { name: 'external commonjs "telegram/sessions"' }] }));
  const result = await auditUnraidDependencies({ ...options, stats: [stats] });
  assert.equal(result.issues.filter(issue => issue.kind === 'excluded-sdk-webpack-module').length, 2);
});
test('stats directory requires client/node compiler evidence, with edge compilation optional or empty', async t => {
  const options = await fixture(t), directory = path.join(path.dirname(options.lock), 'stats');
  await mkdir(directory);
  for (const name of ['client.json', 'server-nodejs.json']) await writeFile(path.join(directory, name), JSON.stringify({ modules: [{ identifier: '/app/app/page.tsx' }] }));
  assert.equal((await auditUnraidDependencies({ ...options, statsDirectory: directory })).passed, true);
  await writeFile(path.join(directory, 'server-edge.json'), JSON.stringify({ modules: [] }));
  assert.equal((await auditUnraidDependencies({ ...options, statsDirectory: directory })).passed, true);
  await rm(path.join(directory, 'client.json'));
  const result = await auditUnraidDependencies({ ...options, statsDirectory: directory });
  assert.equal(result.passed, false);
  assert.ok(result.issues.some(issue => issue.kind === 'missing-required-webpack-stats'));
});
test('glibc native runtime passes, but either unused musl Sharp alternative fails the source-plan guard', async t => {
  const options = await fixture(t);
  const glibcFiles = ['node_modules/@img/sharp-linux-x64/lib/sharp-linux-x64-0.35.4.node', 'node_modules/@img/sharp-libvips-linux-x64/lib/libvips-cpp.so.8.18.6'];
  for (const relative of glibcFiles) {
    const filename = path.join(options.standalone, relative);
    await mkdir(path.dirname(filename), { recursive: true }); await writeFile(filename, Buffer.from([0x7f, 0x45, 0x4c, 0x46]));
  }
  const valid = await auditUnraidDependencies(options);
  assert.equal(valid.passed, true);
  assert.equal(valid.redistributedNativeAndFontFiles.length, 2);
  for (const relative of ['node_modules/@img/sharp-linuxmusl-x64/lib/sharp-linuxmusl-x64-0.35.4.node', 'node_modules/@img/sharp-libvips-linuxmusl-x64/lib/libvips-cpp.so.8.18.6']) {
    const filename = path.join(options.standalone, relative);
    await mkdir(path.dirname(filename), { recursive: true }); await writeFile(filename, Buffer.from([0x7f, 0x45, 0x4c, 0x46]));
    const result = await auditUnraidDependencies(options);
    assert.equal(result.passed, false);
    assert.ok(result.issues.some(issue => issue.kind === 'unsupported-native-runtime-path' && issue.path === relative));
    await rm(filename);
  }
  // Builder-only metadata/notices can retain optional-platform descriptions.
  await mkdir(path.join(options.input, '@img/sharp-linuxmusl-x64'), { recursive: true });
  await writeFile(path.join(options.input, '@img/sharp-linuxmusl-x64/package.json'), JSON.stringify({ name: '@img/sharp-linuxmusl-x64', version: '0.35.4' }));
  assert.equal((await auditUnraidDependencies(options)).passed, true);
});
