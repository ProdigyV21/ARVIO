const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const os = require('node:os');
const path = require('node:path');
const { spawnSync } = require('node:child_process');
const { load, storage } = require('./load.cjs');

const hostedEnv = {
  NEXT_PUBLIC_PAYWALL_ENABLED: 'true',
  NEXT_PUBLIC_ARVIO_APP_ANON_KEY: 'a'.repeat(80),
  APP_ANON_KEY: 'a'.repeat(80),
  NEXT_PUBLIC_NETLIFY_BACKEND_URL: 'https://hosted.invalid/functions',
  NEXT_PUBLIC_SUPABASE_URL: 'https://legacy.invalid',
  NEXT_PUBLIC_SUPABASE_ANON_KEY: 'a'.repeat(80),
};
const ownEnv = {
  ...hostedEnv, NEXT_PUBLIC_SELF_HOSTED: 'true',
  TMDB_API_KEY: 'b'.repeat(32), TRAKT_CLIENT_ID: 'own-trakt-public-id',
  TRAKT_CLIENT_SECRET: 'trakt-private-secret', SIMKL_CLIENT_ID: 'own-simkl-public-id',
  SIMKL_CLIENT_SECRET: 'simkl-private-secret', NEXT_PUBLIC_SIMKL_CLIENT_ID: '',
};
function configuration(env) { return load('lib/config.ts', {}, { process: { env } }); }
function route(provider, env, fetch) {
  class NextResponse extends Response {
    static json(data, init) { return Response.json(data, init); }
  }
  return load(`app/api/${provider}/${provider === 'cloud-auth' ? '[action]' : '[...path]'}/route.ts`, {
    'next/server': { NextResponse },
  }, { process: { env }, fetch });
}

test('independent config disables hosted memberships, legacy cloud and backend even with stale keys', () => {
  const m = configuration(ownEnv);
  assert.equal(m.config.selfHosted, true);
  assert.equal(m.config.paywallEnabled, false);
  assert.equal(m.config.netlifyBackendUrl, '');
  assert.equal(m.config.appAnonKey, '');
  assert.equal(m.hasNetlifyBackendConfig(), false);
  assert.equal(m.hasNetlifyBackendUrl(), false);
  assert.equal(m.hasSupabaseConfig(), false);
  assert.equal(m.hasResolverConfig(), false);
  assert.equal(m.getAuthPortalUrl(), '/');
});

test('hosted config keeps the existing membership and cloud checks', () => {
  const m = configuration(hostedEnv);
  assert.equal(m.config.selfHosted, false);
  assert.equal(m.config.paywallEnabled, true);
  assert.equal(m.hasNetlifyBackendConfig(), true);
  assert.equal(m.config.netlifyBackendUrl, hostedEnv.NEXT_PUBLIC_NETLIFY_BACKEND_URL);
});

test('self-hosted integrations advertise only actually configured providers', () => {
  const empty = configuration({ NEXT_PUBLIC_SELF_HOSTED: 'true' });
  assert.equal(empty.hasTraktConfig(), false);
  assert.equal(empty.hasSimklConfig(), false);
  const own = configuration({ ...ownEnv, NEXT_PUBLIC_TRAKT_CLIENT_ID: ownEnv.TRAKT_CLIENT_ID });
  assert.equal(own.hasTraktConfig(), true);
  assert.equal(own.hasSimklConfig(), true);
});

test('build exposes public integration IDs and mode, but not API keys or OAuth secrets', () => {
  const m = load('next.config.mjs', { 'node:fs': { writeFileSync() {}, mkdirSync() {} } }, {
    process: { cwd: () => '.', env: { ...ownEnv, ARVIO_STANDALONE: 'true' } },
  }).default;
  assert.equal(m.env.NEXT_PUBLIC_SELF_HOSTED, 'true');
  assert.equal(m.env.NEXT_PUBLIC_TRAKT_CLIENT_ID, ownEnv.TRAKT_CLIENT_ID);
  assert.equal(m.env.NEXT_PUBLIC_SIMKL_CLIENT_ID, ownEnv.SIMKL_CLIENT_ID);
  assert.equal(m.output, 'standalone');
  for (const secret of [ownEnv.TMDB_API_KEY, ownEnv.TRAKT_CLIENT_SECRET, ownEnv.SIMKL_CLIENT_SECRET]) {
    assert.equal(JSON.stringify(m.env).includes(secret), false);
  }
});

test('official builds do not opt into standalone output or independent mode', () => {
  const m = load('next.config.mjs', { 'node:fs': { writeFileSync() {}, mkdirSync() {} } }, {
    process: { cwd: () => '.', env: hostedEnv },
  }).default;
  assert.equal(m.env.NEXT_PUBLIC_SELF_HOSTED, 'false');
  assert.equal(m.output, undefined);
});

for (const provider of ['tmdb', 'trakt', 'simkl']) {
  test(`${provider}: self-hosted requests go directly to the provider with personal credentials`, async () => {
    const calls = [];
    const m = route(provider, ownEnv, async (url, init) => {
      calls.push({ url: new URL(url), init });
      return Response.json({ ok: true });
    });
    const segments = provider === 'tmdb' ? ['movie', '123'] : provider === 'trakt' ? ['users', 'settings'] : ['sync', 'activities'];
    const response = await m.GET(new Request(`https://self.invalid/api/${provider}/${segments.join('/')}?language=en-US`, {
      headers: { 'x-user-token': 'personal-user-token' },
    }), { params: Promise.resolve({ path: segments }) });
    assert.equal(response.status, 200);
    assert.equal(calls.length, 1);
    assert.equal(calls[0].url.hostname, { tmdb: 'api.themoviedb.org', trakt: 'api.trakt.tv', simkl: 'api.simkl.com' }[provider]);
    if (provider === 'tmdb') assert.equal(calls[0].url.searchParams.get('api_key'), ownEnv.TMDB_API_KEY);
    else {
      const headers = new Headers(calls[0].init.headers);
      assert.equal(headers.get('authorization'), 'Bearer personal-user-token');
      assert.equal(headers.get(provider === 'trakt' ? 'trakt-api-key' : 'simkl-api-key'), ownEnv[`${provider.toUpperCase()}_CLIENT_ID`]);
    }
  });
  test(`${provider}: missing personal credentials never borrows hosted credentials`, async () => {
    let requests = 0;
    const m = route(provider, { ...hostedEnv, NEXT_PUBLIC_SELF_HOSTED: 'true' }, async () => { requests++; return Response.json({}); });
    const segments = provider === 'simkl' ? ['sync', 'activities'] : ['movie', '123'];
    const response = await m.GET(new Request('https://self.invalid/api'), { params: Promise.resolve({ path: segments }) });
    assert.equal(response.status, 500);
    assert.equal(requests, 0);
  });
  test(`${provider}: official hosted requests retain the hosted proxy`, async () => {
    const calls = [];
    const m = route(provider, hostedEnv, async (url) => { calls.push(new URL(url)); return Response.json({}); });
    await m.GET(new Request('https://official.invalid/api'), { params: Promise.resolve({ path: provider === 'simkl' ? ['sync', 'activities'] : ['movie', '123'] }) });
    assert.equal(calls[0].hostname, 'hosted.invalid');
  });
}

for (const provider of ['trakt', 'simkl']) {
  test(`${provider}: OAuth exchange adds personal secret only on the server`, async () => {
    let sent;
    const m = route(provider, ownEnv, async (url, init) => { sent = { url: new URL(url), body: JSON.parse(init.body) }; return Response.json({ access_token: 'issued' }); });
    const response = await m.POST(new Request(`https://self.invalid/api/${provider}/oauth/token`, {
      method: 'POST', body: JSON.stringify({ code: 'device-code', client_id: 'wrong-client' }),
    }), { params: Promise.resolve({ path: ['oauth', 'token'] }) });
    assert.equal(sent.body.client_id, ownEnv[`${provider.toUpperCase()}_CLIENT_ID`]);
    assert.equal(sent.body.client_secret, ownEnv[`${provider.toUpperCase()}_CLIENT_SECRET`]);
    assert.equal((await response.text()).includes('private-secret'), false);
  });
}

test('self-hosted cloud auth makes no upstream call, despite leftover app keys', async () => {
  let calls = 0;
  const m = route('cloud-auth', ownEnv, async () => { calls++; return Response.json({}); });
  const result = await m.POST(new Request('https://self.invalid/api/cloud-auth/auth-login', { method: 'POST', body: '{}' }), { params: Promise.resolve({ action: 'auth-login' }) });
  assert.equal(result.status, 503);
  assert.equal(calls, 0);
});

test('self-hosted auth does not hydrate a previous hosted session', () => {
  const disk = storage();
  disk.saveStored('arvio.web.supabase.session', { accessToken: 'stale-hosted-token' });
  const { AuthClient } = load('lib/auth.ts', { './config': configuration(ownEnv), './storage': disk, './http': {} });
  assert.equal(new AuthClient().session, null);
});

test('self-hosting sends no Premium analytics even with an old account supplied', async () => {
  let calls = 0;
  const m = load('lib/premiumAnalytics.ts', { './config': configuration(ownEnv), './http': { jsonRequest: async () => calls++ } });
  assert.equal(await m.trackPremiumEvent({ session: { userId: 'old-account' }, accessToken: async () => 'token' }, 'web_opened'), false);
  assert.equal(calls, 0);
});

test('setup is non-destructive and checker diagnoses missing settings without exposing secrets', (t) => {
  const root = fs.mkdtempSync(path.join(os.tmpdir(), 'arvio-selfhost-test-'));
  t.after(() => fs.rmSync(root, { recursive: true, force: true }));
  fs.mkdirSync(path.join(root, 'scripts'));
  fs.copyFileSync(path.resolve(__dirname, '../scripts/selfhost.mjs'), path.join(root, 'scripts/selfhost.mjs'));
  fs.copyFileSync(path.resolve(__dirname, '../.env.selfhost.example'), path.join(root, '.env.selfhost.example'));
  const env = Object.fromEntries(Object.entries(process.env).filter(([name]) => !/^(NEXT_|TMDB_|TRAKT_|SIMKL_|ALLOW_|ARVIO_)/.test(name)));
  const run = (command) => spawnSync(process.execPath, ['scripts/selfhost.mjs', command], { cwd: root, env, encoding: 'utf8' });
  assert.equal(run('check').status, 1);
  assert.equal(run('setup').status, 0);
  assert.equal(run('check').status, 1);
  const filename = path.join(root, '.env.local');
  const configured = fs.readFileSync(filename, 'utf8').replace('TMDB_API_KEY=', `TMDB_API_KEY=${ownEnv.TMDB_API_KEY}`);
  fs.writeFileSync(filename, configured);
  assert.equal(run('setup').status, 0);
  assert.equal(fs.readFileSync(filename, 'utf8'), configured);
  const checked = run('check');
  assert.equal(checked.status, 0, checked.stderr);
  assert.equal(checked.stdout.includes(ownEnv.TMDB_API_KEY), false);
  fs.appendFileSync(filename, '\nNEXT_PUBLIC_TRAKT_CLIENT_SECRET=secret-value');
  assert.equal(run('check').status, 1);
});
