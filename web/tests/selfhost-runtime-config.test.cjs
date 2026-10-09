const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const { load } = require('./load.cjs');

const env = {
  NEXT_PUBLIC_SELF_HOSTED: 'true',
  TRAKT_CLIENT_ID: 'own-trakt-public-id', SIMKL_CLIENT_ID: 'own-simkl-public-id', SIMKL_V2_CLIENT_ID: 'own-simkl-v2-public-id',
  TELEGRAM_API_ID: '123456', TELEGRAM_API_HASH: 'a'.repeat(32),
  ARVIO_RESOLVER_URL: 'https://resolver.example/api/',
  TMDB_API_KEY: 'private-tmdb-key', TRAKT_CLIENT_SECRET: 'private-trakt-secret',
  SIMKL_CLIENT_SECRET: 'private-simkl-secret', NEXT_PUBLIC_ARVIO_APP_ANON_KEY: 'stale-hosted-key',
  NEXT_PUBLIC_SUPABASE_URL: 'https://legacy.invalid', APP_ANON_KEY: 'stale-cloud-key',
  TELEGRAM_SESSION_STRING: 'private-telegram-user-session'
};
const runtimeModule = globals => load('lib/selfhostRuntimeConfig.ts', {}, globals);
function route(values) {
  return load('app/api/selfhost-config/route.ts', {
    '@/lib/selfhostRuntimeConfig': runtimeModule()
  }, { process: { env: values } });
}

test('runtime bootstrap exposes only allowlisted public fields, never server/account secrets', async () => {
  const response = route(env).GET();
  assert.equal(response.status, 200);
  assert.equal(response.headers.get('content-type'), 'application/javascript; charset=utf-8');
  assert.match(response.headers.get('cache-control'), /no-store/);
  assert.equal(response.headers.get('x-content-type-options'), 'nosniff');
  const text = await response.text();
  const window = {};
  vm.runInNewContext(text, { window });
  assert.deepEqual(JSON.parse(JSON.stringify(window.__ARVIO_SELFHOST_CONFIG__)), {
    traktClientId: env.TRAKT_CLIENT_ID, simklClientId: env.SIMKL_CLIENT_ID, simklV2ClientId: env.SIMKL_V2_CLIENT_ID,
    telegramApiId: env.TELEGRAM_API_ID, telegramApiHash: env.TELEGRAM_API_HASH,
    resolverUrl: 'https://resolver.example/api'
  });
  for (const name of ['TMDB_API_KEY', 'TRAKT_CLIENT_SECRET', 'SIMKL_CLIENT_SECRET', 'APP_ANON_KEY',
    'NEXT_PUBLIC_ARVIO_APP_ANON_KEY', 'NEXT_PUBLIC_SUPABASE_URL', 'TELEGRAM_SESSION_STRING']) {
    assert.equal(text.includes(env[name]), false, `Must not leak ${name}`);
    assert.equal(text.includes(name), false, `Must not export ${name}`);
  }
});

test('Telegram-free image withholds even valid runtime Telegram credentials', async () => {
  const text = await route({ ...env, NEXT_PUBLIC_TELEGRAM_ENABLED: 'false' }).GET().text();
  const window = {};
  vm.runInNewContext(text, { window });
  assert.equal(window.__ARVIO_SELFHOST_CONFIG__.telegramApiId, '');
  assert.equal(window.__ARVIO_SELFHOST_CONFIG__.telegramApiHash, '');
  assert.equal(window.__ARVIO_SELFHOST_CONFIG__.traktClientId, env.TRAKT_CLIENT_ID);
  assert.equal(text.includes(env.TELEGRAM_API_HASH), false);
});

test('hosted service does not expose a runtime configuration even if personal IDs are set', async () => {
  const response = route({ ...env, NEXT_PUBLIC_SELF_HOSTED: 'false' }).GET();
  assert.equal(response.status, 404);
  assert.equal((await response.text()).includes(env.TRAKT_CLIENT_ID), false);
  assert.equal(route({ ...env, NEXT_PUBLIC_SELF_HOSTED: undefined }).GET().status, 404);
});

test('runtime script reads fresh server values on every request rather than sharing cached values', async () => {
  const values = { ...env };
  const handler = route(values);
  const before = await handler.GET().text();
  values.TRAKT_CLIENT_ID = 'changed-public-id';
  const after = await handler.GET().text();
  assert.notEqual(before, after);
  assert.equal(after.includes('changed-public-id'), true);
});

test('canonical runtime names take precedence; existing public aliases still work', () => {
  const m = runtimeModule();
  const canonical = m.serverSelfhostRuntimeConfig({ ...env, NEXT_PUBLIC_TRAKT_CLIENT_ID: 'stale-built-id' });
  assert.equal(canonical.traktClientId, env.TRAKT_CLIENT_ID);
  const aliases = m.serverSelfhostRuntimeConfig({
    NEXT_PUBLIC_TRAKT_CLIENT_ID: env.TRAKT_CLIENT_ID, NEXT_PUBLIC_SIMKL_CLIENT_ID: env.SIMKL_CLIENT_ID, NEXT_PUBLIC_SIMKL_V2_CLIENT_ID: env.SIMKL_V2_CLIENT_ID,
    NEXT_PUBLIC_TELEGRAM_API_ID: env.TELEGRAM_API_ID, NEXT_PUBLIC_TELEGRAM_API_HASH: env.TELEGRAM_API_HASH,
    NEXT_PUBLIC_ARVIO_RESOLVER_URL: env.ARVIO_RESOLVER_URL
  });
  assert.deepEqual(JSON.parse(JSON.stringify(canonical)), JSON.parse(JSON.stringify(aliases)));
});

test('malformed runtime public IDs, Telegram pairs and unsafe resolver URLs remain unconfigured', () => {
  const m = runtimeModule();
  for (const value of ['<script>alert(1)</script>', 'key with space', '$MASKED', '****', 'your-key', 'disabled', 'a'.repeat(257)]) {
    assert.equal(m.normalizeSelfhostRuntimeConfig({ traktClientId: value }).traktClientId, '');
  }
  for (const pair of [
    { telegramApiId: '123', telegramApiHash: '' }, { telegramApiId: '', telegramApiHash: 'a'.repeat(32) },
    { telegramApiId: '0', telegramApiHash: 'a'.repeat(32) }, { telegramApiId: '2147483648', telegramApiHash: 'a'.repeat(32) },
    { telegramApiId: '00123', telegramApiHash: 'a'.repeat(32) }, { telegramApiId: '123', telegramApiHash: '<private-account-token>' }
  ]) {
    const result = m.normalizeSelfhostRuntimeConfig(pair);
    assert.equal(result.telegramApiId, '');
    assert.equal(result.telegramApiHash, '');
  }
  for (const url of ['javascript:alert(1)', 'http://evil.example', 'https://user:secret@example.com',
    'https://example.com/?token=private', 'https://example.com/#private', ' https://example.com', 'a'.repeat(2049)]) {
    assert.equal(m.normalizeSelfhostRuntimeConfig({ resolverUrl: url }).resolverUrl, '');
  }
  assert.equal(m.normalizeSelfhostRuntimeConfig({ resolverUrl: 'http://localhost:3001' }).resolverUrl, 'http://localhost:3001');
});

test('self-host browser consumes runtime values; blank runtime settings override stale build-time IDs', () => {
  const runtime = runtimeModule().serverSelfhostRuntimeConfig(env);
  const globals = { process: { env: { NEXT_PUBLIC_SELF_HOSTED: 'true', NEXT_PUBLIC_TRAKT_CLIENT_ID: 'stale-built-id' } },
    window: { __ARVIO_SELFHOST_CONFIG__: runtime } };
  const config = load('lib/config.ts', {}, globals);
  assert.equal(config.config.traktClientId, env.TRAKT_CLIENT_ID);
  assert.equal(config.config.simklClientId, env.SIMKL_CLIENT_ID);
  assert.equal(config.config.resolverUrl, 'https://resolver.example/api');
  assert.equal(config.config.netlifyBackendUrl, '');
  assert.equal(config.config.paywallEnabled, false);
  const telegram = load('lib/telegram/config.ts', {}, globals);
  assert.equal(telegram.getTelegramCredentials().apiId, 123456);
  assert.equal(telegram.getTelegramCredentials().apiHash, env.TELEGRAM_API_HASH);
  assert.equal(telegram.isTelegramConfigured(), true);
  globals.window.__ARVIO_SELFHOST_CONFIG__ = runtimeModule().serverSelfhostRuntimeConfig({});
  assert.equal(load('lib/config.ts', {}, globals).hasTraktConfig(), false);
  assert.equal(load('lib/telegram/config.ts', {}, globals).isTelegramConfigured(), false);
});

test('hosted browser ignores visitor/global runtime config; unavailable bootstrap falls back safely', () => {
  const globals = { process: { env: { NEXT_PUBLIC_TRAKT_CLIENT_ID: 'hosted-public-id' } },
    window: { __ARVIO_SELFHOST_CONFIG__: runtimeModule().serverSelfhostRuntimeConfig(env) } };
  const config = load('lib/config.ts', {}, globals);
  assert.equal(config.config.selfHosted, false);
  assert.equal(config.config.traktClientId, 'hosted-public-id');
  assert.equal(load('lib/telegram/config.ts', {}, globals).isTelegramConfigured(), false);
  const independent = load('lib/config.ts', {}, { process: { env: { NEXT_PUBLIC_SELF_HOSTED: 'true', NEXT_PUBLIC_TRAKT_CLIENT_ID: 'stale-built-id' } }, window: {} });
  assert.equal(independent.config.netlifyBackendUrl, '');
  assert.equal(independent.hasTraktConfig(), false);
  assert.equal(independent.hasSimklConfig(), false);
});

test('already evaluated client modules consume a delayed bootstrap without remounting or stale credential snapshots', () => {
  const window = {};
  const globals = { process: { env: { NEXT_PUBLIC_SELF_HOSTED: 'true',
    NEXT_PUBLIC_TRAKT_CLIENT_ID: 'stale-build-id', NEXT_PUBLIC_SIMKL_CLIENT_ID: 'stale-build-id',
    NEXT_PUBLIC_TELEGRAM_API_ID: '999', NEXT_PUBLIC_TELEGRAM_API_HASH: 'b'.repeat(32) } }, window };
  // These SAME module instances exist before the script's network response.
  const config = load('lib/config.ts', {}, globals);
  const telegram = load('lib/telegram/config.ts', {}, globals);
  assert.equal(config.hasTraktConfig(), false);
  assert.equal(config.hasSimklConfig(), false);
  assert.equal(config.config.resolverUrl, '');
  assert.equal(telegram.isTelegramConfigured(), false);
  assert.deepEqual(JSON.parse(JSON.stringify(telegram.getTelegramCredentials())), { apiId: 0, apiHash: '' });
  window.__ARVIO_SELFHOST_CONFIG__ = runtimeModule().serverSelfhostRuntimeConfig(env);
  assert.equal(config.hasTraktConfig(), true);
  assert.equal(config.hasSimklConfig(), true);
  assert.equal(config.config.traktClientId, env.TRAKT_CLIENT_ID);
  assert.equal(config.config.simklClientId, env.SIMKL_CLIENT_ID);
  assert.equal(config.config.resolverUrl, 'https://resolver.example/api');
  assert.equal(telegram.isTelegramConfigured(), true);
  assert.deepEqual(JSON.parse(JSON.stringify(telegram.getTelegramCredentials())), { apiId: 123456, apiHash: env.TELEGRAM_API_HASH });
  // Removing/invalidating bootstrap cannot reveal previously baked credentials.
  delete window.__ARVIO_SELFHOST_CONFIG__;
  assert.equal(config.hasTraktConfig(), false);
  assert.equal(config.hasSimklConfig(), false);
  assert.equal(telegram.isTelegramConfigured(), false);
});

test('generic Docker builds never accept public credentials as build arguments and bootstrap precedes hydration', () => {
  const dockerfile = fs.readFileSync(path.resolve(__dirname, '../Dockerfile'), 'utf8');
  assert.equal(/ARG (?:TRAKT|SIMKL|NEXT_PUBLIC_TELEGRAM|NEXT_PUBLIC_ARVIO_RESOLVER)/.test(dockerfile), false);
  assert.match(dockerfile, /NEXT_PUBLIC_SELF_HOSTED=true/);
  const layout = fs.readFileSync(path.resolve(__dirname, '../app/layout.tsx'), 'utf8');
  assert.match(layout, /process\.env\.NEXT_PUBLIC_SELF_HOSTED === "true"/);
  assert.match(layout, /src="\/api\/selfhost-config" strategy="beforeInteractive"/);
  const routeSource = fs.readFileSync(path.resolve(__dirname, '../app/api/selfhost-config/route.ts'), 'utf8');
  assert.match(routeSource, /dynamic = "force-dynamic"/);
});

test('image preserves the original ARVIO license and explicitly packages dependency notices', () => {
  const root = path.resolve(__dirname, '../..');
  const original = fs.readFileSync(path.join(root, 'LICENSE'), 'utf8').replace(/\r\n/g, '\n').trim();
  const packaged = fs.readFileSync(path.join(root, 'web/LICENSE'), 'utf8').replace(/\r\n/g, '\n').trim();
  assert.equal(packaged, original);
  const dockerfile = fs.readFileSync(path.join(root, 'web/Dockerfile'), 'utf8');
  assert.match(dockerfile, /node scripts\/collect-licenses\.mjs/);
  assert.match(dockerfile, /\.\/licenses\/ARVIO-LICENSE/);
  assert.match(dockerfile, /\.\/licenses\/third-party/);
});
