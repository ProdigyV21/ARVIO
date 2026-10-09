const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load, storage } = require('./load.cjs');

// App modules share the browser's Error constructor. Give separately compiled
// test VM modules the same realm for existing instanceof Error handling.
const disabledGlobals = { Error, process: { env: { NEXT_PUBLIC_TELEGRAM_ENABLED: 'false' } } };
const source = relative => fs.readFileSync(path.resolve(__dirname, '..', relative), 'utf8');

test('Telegram is enabled by default, including ordinary self-hosting, and disabled only by the build flag', () => {
  for (const env of [{}, { NEXT_PUBLIC_TELEGRAM_ENABLED: 'true' }, { NEXT_PUBLIC_SELF_HOSTED: 'true' }]) {
    assert.equal(load('lib/config.ts', {}, { process: { env } }).config.telegramEnabled, true);
  }
  const disabled = load('lib/config.ts', {}, {
    ...disabledGlobals,
    window: { __ARVIO_SELFHOST_CONFIG__: { telegramApiId: '123456', telegramApiHash: 'a'.repeat(32) } }
  });
  assert.equal(disabled.config.telegramEnabled, false, 'runtime credentials cannot override the build flag');
});

test('saved Telegram source recognition handles missing or malformed shape and absolute or original URLs', () => {
  const enabled = load('lib/config.ts');
  const disabled = load('lib/config.ts', {}, disabledGlobals);
  for (const value of [null, undefined, false, 4, '', '/tg-stream/id', [], {},
    { url: null }, { url: 7 }, { url: {} }, { addonId: [] },
    { url: 'https://media.example/video.mp4' }, { url: 'http://[' },
    { url: 'https://media.example/not-tg-stream/id' }]) {
    assert.equal(disabled.isTelegramSource(value), false);
    assert.doesNotThrow(() => disabled.assertTelegramSourceAvailable(value));
  }
  for (const value of [{ addonId: 'telegram_native' }, { url: '/tg-stream/id' },
    { url: ' https://old-install.example/tg-stream/id?download=1 ' },
    { url: '//old-install.example/tg-stream/id' },
    { url: 'https://relay.example/media?url=%2Ftg-stream%2Fdownload-id&dl=1' },
    { url: 'blob:https://new-install.example/id', originalUrl: '/tg-stream/old-id' }]) {
    assert.equal(disabled.isTelegramSource(value), true);
    assert.equal(disabled.isDisabledTelegramSource(value), true);
    assert.throws(() => disabled.assertTelegramSourceAvailable(value), /Telegram is not available in this Unraid preview/);
    assert.equal(enabled.isDisabledTelegramSource(value), false);
    assert.doesNotThrow(() => enabled.assertTelegramSourceAvailable(value));
  }
});

test('disabled Telegram module preserves its surface without executable SDK imports or side effects', async () => {
  const forbidden = () => assert.fail('Disabled Telegram must not access a SDK, network, storage or service worker');
  const api = load('lib/telegram-disabled.ts', {}, {
    ...disabledGlobals, fetch: forbidden,
    window: { localStorage: new Proxy({}, { get: forbidden }) },
    navigator: { serviceWorker: new Proxy({}, { get: forbidden }) }
  });
  assert.equal(api.TELEGRAM_ADDON_ID, 'telegram_native');
  assert.equal(api.TELEGRAM_ADDON_NAME, 'Telegram');
  assert.equal(api.isConnected(), false);
  assert.equal(api.isTelegramConfigured(), false);
  const states = [];
  const unsubscribe = api.subscribe(state => { states.push(state); state.message = 'Mutated caller copy'; });
  assert.equal(states.length, 1);
  assert.equal(api.getAuthState().k, 'error');
  assert.match(api.getAuthState().message, /Telegram is not available/);
  unsubscribe();
  await api.restoreSession();
  await api.disconnect();
  api.resetToIdle();
  assert.equal((await api.resolveTelegramSources({}, 1, 1)).length, 0);
  await assert.rejects(api.startQrAuth(), /Telegram is not available/);
  await assert.rejects(api.startPhoneAuth('+1234567890'), /Telegram is not available/);
  await assert.rejects(api.initTelegramStreaming(), /Telegram is not available/);
  assert.throws(() => api.submitCode('12345'), /Telegram is not available/);
  assert.throws(() => api.submitPassword('not-a-real-password'), /Telegram is not available/);
  const js = ts.transpileModule(source('lib/telegram-disabled.ts'), { compilerOptions: { module: ts.ModuleKind.CommonJS } }).outputText;
  assert.doesNotMatch(js, /require\(["'](?:telegram|\.\/telegram\/)/, 'type-only imports must be erased');
});

test('disabled Telegram preparation rejects before ordinary playback or provider work', async () => {
  let calls = 0;
  const forbidden = () => { calls++; assert.fail('Telegram preparation reached provider work'); };
  const api = load('lib/prepareBrowserStream.ts', {
    './debrid': {}, './streamCompatibility': { playbackPlan: forbidden },
    './homeServerPlayback': { prepareHomeServerPlayback: forbidden },
    './resolver': { declaredHeaderRelayUrl: forbidden }
  }, disabledGlobals);
  for (const stream of [{ addonId: 'telegram_native', url: 'https://example.test/fixture.mp4' },
    { url: '/tg-stream/saved-id' }, { url: 'https://relay.example/file', originalUrl: '/tg-stream/old-id', homeServer: {} }]) {
    await assert.rejects(api.prepareBrowserStream(stream, {}), /Telegram is not available/);
  }
  assert.equal(calls, 0);
});

test('ordinary playback preparation is unchanged when the Telegram flag is absent', async () => {
  const api = load('lib/prepareBrowserStream.ts', {
    './debrid': { parseDebridStream: () => null, cachedDebridDirectUrl: () => null },
    './streamCompatibility': { playbackPlan: () => ({ route: 'here', method: 'direct' }), streamTransport: () => 'mp4', streamContainer: () => 'mp4' },
    './homeServerPlayback': {}, './resolver': { declaredHeaderRelayUrl: () => null }
  });
  const stream = { addonId: 'telegram_native', url: '/tg-stream/existing-id' };
  const result = await api.prepareBrowserStream(stream, {});
  assert.equal(result.url, stream.url);
  assert.equal(result.addonId, stream.addonId);
});

test('new and saved managed Telegram downloads fail clearly before file or network access', async () => {
  const disk = storage();
  const entry = { id: 'saved-tg', scope: 'local:profile', title: 'Fixture', url: '/tg-stream/old-id', status: 'paused', received: 17, total: 100, updatedAt: 1 };
  disk.saveStored('arvio.web.downloads.v1', [entry]);
  const forbidden = () => assert.fail('Disabled download must not reach network or file permission');
  const api = load('lib/downloads.ts', { './storage': disk }, {
    ...disabledGlobals, indexedDB: { open: forbidden }, fetch: forbidden, window: {}
  });
  await assert.rejects(api.startManagedDownload('Fixture', '/tg-stream/new-id', 'local:profile', {}), /Telegram is not available/);
  await api.resumeDownload('saved-tg');
  const saved = api.downloadEntries()[0];
  assert.equal(saved.status, 'failed');
  assert.match(saved.error, /Telegram is not available/);
  assert.equal(saved.received, 17, 'do not truncate existing downloaded bytes');
  assert.equal(saved.url, entry.url, 'retain the saved source for a different build');
  disk.saveStored('arvio.web.downloads.v1', [{ ...entry, url: 'https://relay.example/media?url=%2Ftg-stream%2Fold-id&dl=1' }]);
  await api.resumeDownload('saved-tg');
  assert.match(api.downloadEntries()[0].error, /Telegram is not available/);
});

test('settings navigation and direct render hide Telegram only in disabled builds', () => {
  const settings = source('components/settings/SettingsScreen.tsx');
  const declarations = settings.match(/const SECTIONS = [\s\S]*?const VISIBLE_SECTIONS = [^;]+;/)?.[0];
  assert.ok(declarations);
  const icons = ['Cloud','User','Play','Download','VlcIcon','Languages','Subtitles','Captions','LayoutGrid','Tv','Network','Server','Send','ListVideo','Sparkles','Eye'];
  for (const enabled of [true, false]) {
    const context = { config: { telegramEnabled: enabled }, module: { exports: null }, ...Object.fromEntries(icons.map(name => [name, {}])) };
    const js = ts.transpileModule(declarations + '\nmodule.exports = VISIBLE_SECTIONS.map(section => section.id);', { compilerOptions: { target: ts.ScriptTarget.ES2022 } }).outputText;
    vm.runInNewContext(js, context);
    assert.equal(context.module.exports.includes('telegram'), enabled);
    assert.equal(context.module.exports.includes('credits'), true);
  }
  assert.equal((settings.match(/VISIBLE_SECTIONS\.map\(/g) || []).length, 2, 'both navigation surfaces must use the filtered list');
  assert.match(settings, /case "telegram":\s*return config\.telegramEnabled \? <TelegramSection \/> : null/);
  assert.match(settings, /useEffect\(\(\) => \{\s*if \(!config\.telegramEnabled\) return;\s*let unsub/);
});

test('store imports use the disabled-build alias and saved-source actions are guarded', () => {
  const store = source('lib/store.tsx');
  assert.doesNotMatch(store, /import\(["']\.\/telegram["']\)/);
  assert.equal((store.match(/import\("@\/lib\/telegram"\)/g) || []).length, 2);
  assert.match(store, /const appendTelegramSources = [\s\S]*?if \(!config\.telegramEnabled\) return Promise\.resolve/);
  assert.match(store, /const playStream = [^\n]+\n\s*if \(isDisabledTelegramSource\(stream\)\)/);
  const details = source('components/details/DetailsDrawer.tsx');
  assert.match(details, /!telegramDisabled && <span className="source-row-actions">/);
  for (const handler of ['openExternal', 'openAnyPlayer', 'copyUrl', 'downloadSource']) {
    assert.match(details, new RegExp('const ' + handler + ' = [^\\n]+\\n\\s*if \\(rejectDisabledTelegram\\(stream\\)\\) return;'));
  }
});
