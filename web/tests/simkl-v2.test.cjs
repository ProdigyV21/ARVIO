const test = require('node:test');
const assert = require('node:assert/strict');
const crypto = require('node:crypto');
const { load, storage } = require('./load.cjs');

function harness(fetcher, clock = { now: 100_000 }, http = {}) {
  const saved = storage();
  class Clock extends Date { static now() { return clock.now; } }
  const { SimklClient } = load('lib/simkl.ts', {
    './config': { config: { simklV2ClientId: 'public-v2-client', simklClientId: 'legacy-client' } },
    './storage': saved, './sync': {}, './http': http, './tmdb': {}
  }, { fetch: fetcher, Date: Clock });
  const client = new SimklClient(); client.setProfile('qa-profile');
  return { client, saved, clock };
}
const response = (value, status = 200) => new Response(JSON.stringify(value), { status });
const device = { device_code: 'private-device', user_code: 'ABCD-EFGH', verification_uri: 'https://simkl.com/pin',
  verification_uri_complete: 'https://simkl.com/pin?user_code=ABCD-EFGH', expires_in: 900, interval: 5 };
const token = { access_token: 'simkl_at_qa', refresh_token: 'simkl_rt_qa', token_type: 'Bearer', scope: 'media:read media:write', expires_in: 604800 };

test('V2 device flow sends S256 PKCE, correct scopes and matching verifier', async () => {
  const requests = [];
  const h = harness(async (url, init) => { requests.push({ url, body: new URLSearchParams(init.body) }); return response(requests.length === 1 ? device : token); });
  const code = await h.client.beginPinAuth();
  assert.equal(code.verification_url, device.verification_uri_complete);
  assert.equal(requests[0].body.get('scope'), 'media:read media:write');
  assert.equal(requests[0].body.get('code_challenge_method'), 'S256');
  assert.match(requests[0].url, /\/oauth2\/device/);
  assert.equal(await h.client.pollPinToken(code.user_code), null, 'cannot poll early');
  h.clock.now += 5000;
  const grant = await h.client.pollPinToken(code.user_code);
  const verifier = requests[1].body.get('code_verifier');
  assert.equal(crypto.createHash('sha256').update(verifier).digest('base64url'), requests[0].body.get('code_challenge'));
  assert.equal(requests[1].body.get('grant_type'), 'urn:ietf:params:oauth:grant-type:device_code');
  assert.equal(grant.refresh_token, token.refresh_token);
  assert.ok(grant.connection_id);
});

test('V2 pending and slow_down increase the poll interval without losing session', async () => {
  let calls = 0;
  const h = harness(async () => { calls++; return response(calls === 1 ? device : calls === 2 ? { error: 'slow_down' } : token, calls === 2 ? 400 : 200); });
  const code = await h.client.beginPinAuth(); h.clock.now += 5000;
  assert.equal(await h.client.pollPinToken(code.user_code), null);
  h.clock.now += 5000;
  assert.equal(await h.client.pollPinToken(code.user_code), null);
  assert.equal(calls, 2);
  h.clock.now += 5000;
  assert.equal((await h.client.pollPinToken(code.user_code)).access_token, token.access_token);
});

test('V2 grant cannot be applied after switching profile or with missing write permissions', async () => {
  const h = harness(async (_url, init) => response(new URLSearchParams(init.body).has('scope') ? device : { ...token, scope: 'media:read' }));
  const code = await h.client.beginPinAuth(); h.clock.now += 5000;
  await assert.rejects(h.client.pollPinToken(code.user_code), /permissions/);
  h.client.setProfile('another-profile');
  await assert.rejects(h.client.pollPinToken(code.user_code), /expired/);
  assert.equal(h.client.token, null);
});

test('V2 refresh is shared by simultaneous consumers and preserves account cache scope', async () => {
  let refreshes = 0;
  const h = harness(async () => { refreshes++; await new Promise(setImmediate); return response({ ...token, access_token: 'simkl_at_new', refresh_token: 'simkl_rt_new' }); });
  h.client.setToken({ access_token: 'simkl_at_old', refresh_token: 'simkl_rt_old', expires_at: 0, connection_id: 'same-grant' });
  const scope = h.client.scope();
  await Promise.all([h.client.refreshV2(), h.client.refreshV2(), h.client.refreshV2()]);
  assert.equal(refreshes, 1);
  assert.equal(h.client.token.access_token, 'simkl_at_new');
  assert.equal(h.client.scope(), scope);
  assert.equal(h.saved.loadStored('arvio.web.simkl.token:qa-profile').refresh_token, 'simkl_rt_new');
});

test('V1 tokens still restore without being rewritten as V2 grants', () => {
  const h = harness();
  h.client.setToken({ access_token: 'legacy-v1-token' });
  h.client.setProfile('other'); h.client.setProfile('qa-profile');
  assert.equal(h.client.token.access_token, 'legacy-v1-token');
  assert.equal(h.client.token.refresh_token, undefined);
});

test('V2 refresh rejects a partial grant without discarding the working local connection', async () => {
  const h = harness(async () => response({ ...token, scope: 'media:read' }));
  h.client.setToken({ access_token: 'simkl_at_old', refresh_token: 'simkl_rt_old', expires_at: 0, connection_id: 'same-grant' });
  await assert.rejects(h.client.refreshV2(), /invalid refresh permissions/);
  assert.equal(h.client.token.access_token, 'simkl_at_old');
});

test('Disconnect during a refresh cannot silently reconnect the account', async () => {
  let finish;
  const h = harness(() => new Promise(resolve => { finish = resolve; }));
  h.client.setToken({ access_token: 'simkl_at_old', refresh_token: 'simkl_rt_old', expires_at: 0, connection_id: 'same-grant' });
  const refresh = h.client.refreshV2();
  h.client.disconnect(); finish(response(token)); await refresh;
  assert.equal(h.client.token, null);
  assert.equal(h.saved.loadStored('arvio.web.simkl.token:qa-profile', null), null);
});

function listsHarness(reader) {
  const simklClient = { token: { connection_id: 'grant', refresh_token: 'private-refresh' }, customListsRequest: reader };
  return load('lib/simklLists.ts', { './simkl': { simklClient } });
}
test('A profile switch during V2 refresh cannot send the old request through another account', async () => {
  let finish, reads = 0;
  const h = harness(() => new Promise(resolve => { finish = resolve; }), { now: 100_000 }, { jsonRequest: async () => { reads++; return {}; } });
  h.client.setToken({ access_token: 'simkl_at_old', refresh_token: 'simkl_rt_old', expires_at: 0, connection_id: 'same-grant' });
  const request = h.client.customListsRequest('/lists/14462');
  h.client.setProfile('another-profile'); finish(response(token));
  await assert.rejects(request, /profile changed/);
  assert.equal(reads, 0);
});
test('SIMKL URL parsing rejects lookalike hosts, credentials, discovery pages and invalid IDs', () => {
  const lists = listsHarness();
  assert.equal(lists.parseSimklListUrl('simkl.com/5/list/14462/best-mindfucks/?sort=position').id, 14462);
  for (const url of ['https://simkl.com.evil.test/5/list/14462', 'https://evil.simkl.com/5/list/14462', 'https://simkl.com/lists/official', 'https://simkl.com/lists/0', 'https://user@simkl.com/lists/1']) assert.equal(lists.parseSimklListUrl(url), null);
});

test('SIMKL list API pages in owner order, caches navigation and never accepts premium_only', async () => {
  const requests = [];
  const lists = listsHarness(async path => { requests.push(path); return { id: 14462, name: 'Real list', media_type: 'tv',
    pagination: { total_pages: 2 }, items: [{ title: path.endsWith('page=1') ? 'Dark' : '1899', ids: { tmdb: path.endsWith('page=1') ? 70523 : 90669 } }] }; });
  const list = await lists.loadSimklCustomList('https://simkl.com/5/list/14462');
  assert.deepEqual(Array.from(list.items, x => x.title), ['Dark', '1899']);
  await lists.loadSimklCustomList('https://simkl.com/lists/14462');
  assert.equal(requests.length, 2);
  assert.ok(requests.every(path => !path.includes('sort=')));
  const free = listsHarness(async () => ({ error: 'premium_only', item: { title: 'Upgrade' } }));
  await assert.rejects(free.loadSimklCustomList('simkl.com/5/list/14462'), /SIMKL PRO or VIP/);
});

test('SIMKL search combines paginated personal/followed/shared and official indexes without fetching contents', async () => {
  const requests = [];
  const lists = listsHarness(async (path, options) => {
    requests.push(path);
    if (path === '/users/settings') { assert.equal(options.method, 'POST'); return { account: { id: '10' } }; }
    const own = path.startsWith('/lists/user/10');
    return { lists: [{ id: own ? 42 : 14462, name: own ? 'My Mindfuck List' : 'Best Mindfucks', description: { en: 'Localized description' }, updated_at: '2026-10-06' }], pagination: { total_pages: own ? 2 : 1 } };
  });
  const found = await lists.searchSimklCustomLists('mindfuck');
  assert.deepEqual(Array.from(found, list => list.id), [42, 14462]);
  assert.equal(requests.length, 5);
  assert.ok(requests.slice(1, 3).every(path => path.includes('followed=true&collaborants=true')));
  assert.ok(requests[3].includes('sort=updated&direction=desc'));
  assert.ok(requests[4].includes('sort=popularity&direction=desc'));
  await lists.searchSimklCustomLists('best'); assert.equal(requests.length, 5);
});
