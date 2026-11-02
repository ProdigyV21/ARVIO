const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const service = load('lib/server/weTrakrSession.ts');
const token = { access_token: 'a'.repeat(64), refresh_token: 'b'.repeat(64), token_type: 'Bearer', expires_in: 604800 };
const device = { device_code: 'private-device', user_code: 'ABC123', verification_url: 'https://wetrakr.com/activate', expires_in: 600, interval: 5 };
class Store {
  entries = new Map(); version = 0;
  async read(key) { return this.entries.get(key) || null; }
  async compareAndSet(key, data, etag) {
    if ((this.entries.get(key)?.etag || null) !== etag) return false;
    this.entries.set(key, { data, etag: String(++this.version) }); return true;
  }
}
function harness(handler) {
  const store = new Store(), clock = { now: 1000000 }, requests = [];
  const fetcher = async (url, init) => {
    const request = { path: new URL(url).pathname, url: new URL(url), init, body: init.body ? JSON.parse(init.body) : null };
    requests.push(request);
    return handler ? handler(request) : Response.json(request.path === '/oauth/device/code' ? device : request.path.startsWith('/oauth/') ? token : []);
  };
  const sessions = new service.WeTrakrSessions(store, 'public-client', fetcher, () => clock.now), id = service.newWeTrakrSessionId();
  return { sessions, id, clock, store, requests, another: () => new service.WeTrakrSessions(store, 'public-client', fetcher, () => clock.now),
    async connect() { const code = await sessions.start(id, 'profile'); clock.now += 5000; return sessions.poll(id, 'profile', code.user_code); } };
}
test('device flow hides credentials, reuses pending codes and needs no client secret', async () => {
  const h = harness(); const code = await h.sessions.start(h.id, 'profile');
  assert.equal(code.device_code, undefined);
  await h.sessions.start(h.id, 'profile'); assert.equal(h.requests.length, 1);
  assert.equal(await h.sessions.poll(h.id, 'profile', code.user_code), null);
  h.clock.now += 5000;
  const marker = await h.sessions.poll(h.id, 'profile', code.user_code);
  assert.equal(marker.server_session, true); assert.equal(marker.refresh_token, undefined);
  assert.ok(!marker.access_token.includes(token.access_token));
  assert.ok(!JSON.stringify([...h.store.entries]).includes(token.refresh_token));
  assert.ok(!JSON.stringify(h.requests).includes('client_secret'));
  assert.equal(h.requests[1].body.code, device.device_code);
});
test('pending, slowdown and expiry keep their distinct meanings across server instances', async () => {
  let polls = 0;
  const h = harness(r => r.path.endsWith('/code') ? Response.json(device) : Response.json({ error: ++polls === 1 ? 'authorization_pending' : 'slow_down' }, { status: polls === 1 ? 400 : 429, headers: { 'Retry-After': '20' } }));
  const code = await h.sessions.start(h.id, 'profile'); h.clock.now += 5000;
  assert.equal(await h.sessions.poll(h.id, 'profile', code.user_code), null); h.clock.now += 5000;
  assert.equal(await h.sessions.poll(h.id, 'profile', code.user_code), null); h.clock.now += 10000;
  assert.equal(await h.another().poll(h.id, 'profile', code.user_code), null); assert.equal(polls, 2);
  h.clock.now += 600000; await assert.rejects(h.sessions.poll(h.id, 'profile', code.user_code), /expired_token/);
});
test('grants survive restarts, stay profile scoped and refresh once across concurrent workers', async () => {
  const h = harness(); const marker = await h.connect();
  assert.equal((await h.another().restore(h.id, 'profile')).connection_id, marker.connection_id);
  await assert.rejects(h.sessions.restore(h.id, 'other'), /session_required/);
  h.clock.now += 604800000;
  await Promise.all([h.sessions, h.another(), h.another()].map(s => s.request(h.id, 'profile', marker.connection_id, '/sync/tracking/planning/all', 'GET', new URLSearchParams())));
  assert.equal(h.requests.filter(r => r.path === '/oauth/token/refresh').length, 1);
});
test('declined activation can immediately request a fresh code', async () => {
  const h = harness(r => r.path.endsWith('/code') ? Response.json(device) : Response.json({ error: 'access_denied' }, { status: 418 }));
  const code = await h.sessions.start(h.id, 'profile'); h.clock.now += 5000;
  await assert.rejects(h.sessions.poll(h.id, 'profile', code.user_code), /access_denied/);
  await h.sessions.start(h.id, 'profile');
  assert.equal(h.requests.filter(r => r.path.endsWith('/code')).length, 2);
  assert.equal(await h.sessions.restore(h.id, 'profile'), null);
});
test('provider errors do not erase a grant; permanent refresh rejection does', async () => {
  for (const permanent of [false, true]) {
    const h = harness(r => r.path.endsWith('/code') ? Response.json(device) : r.path === '/oauth/token/refresh'
      ? Response.json({ error: permanent ? 'INVALID_REFRESH_TOKEN' : 'provider_error' }, { status: permanent ? 401 : 503 })
      : Response.json(token));
    const marker = await h.connect(); h.clock.now += 604800000;
    await assert.rejects(h.sessions.request(h.id, 'profile', marker.connection_id, '/sync/tracking/planning/all', 'GET', new URLSearchParams()));
    assert.equal(Boolean(await h.sessions.restore(h.id, 'profile')), !permanent);
  }
});
test('disconnect revokes the correct access/refresh pair and rejects stale connection markers', async () => {
  const h = harness(); const marker = await h.connect();
  await assert.rejects(h.sessions.disconnect(h.id, 'profile', 'old-connection'), /session_changed/);
  await h.sessions.disconnect(h.id, 'profile', marker.connection_id);
  const revoke = h.requests.find(r => r.path === '/oauth/logout');
  assert.equal(revoke.body.refresh_token, token.refresh_token);
  assert.equal(revoke.init.headers.Authorization, `Bearer ${token.access_token}`);
  assert.equal(await h.sessions.restore(h.id, 'profile'), null);
});
test('scrobble forces the watched threshold and preserves target-specific DELETE bodies', async () => {
  const h = harness(); const marker = await h.connect();
  await h.sessions.request(h.id, 'profile', marker.connection_id, '/scrobble/pause', 'POST', new URLSearchParams('min_progress=1&access_token=bad'), '{}');
  const request = h.requests.at(-1);
  assert.equal(request.url.searchParams.get('min_progress'), '90'); assert.equal(request.url.searchParams.has('access_token'), false);
  assert.equal(request.init.headers['wetrakr-api-key'], 'public-client');
  const body = JSON.stringify({ show: { ids: { tmdb: 1396 } }, episode: { season: 1, number: 2 } });
  await h.sessions.request(h.id, 'profile', marker.connection_id, '/scrobble/playing', 'DELETE', new URLSearchParams(), body);
  assert.equal(h.requests.at(-1).init.body, body);
});
const clientModule = load('lib/wetrakr.ts');
test('manual episode changes use parent TMDB identity and exact season/episode, never internal IDs', () => {
  const body = clientModule.weTrakrBody({ mediaType: 'tv', tmdbId: 1396, season: 1, episode: 2 }, 'watched');
  assert.equal(body.shows[0].ids.tmdb, 1396); assert.equal(body.shows[0].id, undefined);
  assert.equal(body.shows[0].seasons[0].episodes[0].number, 2); assert.equal(body.allow_rewatch, false);
  assert.equal(clientModule.weTrakrEpisodeKey({ id: 999, show: { ids: { tmdb: 1396 } }, season_number: 1, number: 2 }), 'show_tmdb:1396:1:2');
  assert.equal(clientModule.weTrakrEpisodeKey({ show: { id: 1396 }, season_number: 1, number: 2 }), null);
  assert.throws(() => clientModule.weTrakrPlaybackRef({ mediaType: 'tv', tmdbId: 1396 }));
});
test('HTTP 200 item errors cannot masquerade as saved changes', () => {
  assert.throws(() => clientModule.assertWeTrakrWrite({ notFound: { movies: [{ ids: { tmdb: 1 } }] } }));
  assert.throws(() => clientModule.assertWeTrakrWrite({ errored: { episodes: [{ error: 'Invalid episode' }] } }));
  clientModule.assertWeTrakrWrite({ errored: { episodes: [{ error: 'Already watched!' }] }, notFound: { shows: [] } });
});
test('reads fetch every page and discard already watched or invented next episodes', async () => {
  const requests = [];
  const show = { id: 999, ids: { tmdb: 1396 }, type: 'show', title: 'Example' };
  const mod = load('lib/wetrakr.ts', {}, { fetch: async url => {
    requests.push(url);
    if (url.endsWith('/session')) return Response.json({ token: { connection_id: 'marker', server_session: true } });
    if (url.includes('/planning/')) return Response.json([{ id: 1, ids: { tmdb: url.includes('page=1') ? 155 : 156 }, type: 'movie', title: 'Movie' }], { headers: { 'X-Pagination-Page-Count': '2' } });
    if (url.includes('/watched/episodes')) return Response.json([{ show, season_number: 1, number: 2 }]);
    if (url.includes('/playing/')) return Response.json([{ show, type: 'episode', season_number: 1, number: 2, playback: { progress_percent: 20 } }]);
    if (url.includes('/watching/')) return Response.json([{ ...show, next_watching_episode: { season_number: 1, number: 3, air_date: '2020-01-01' } }, { ...show, ids: { tmdb: 2 } }, { ...show, ids: { tmdb: 3 }, next_watching_episode: { season_number: 2, number: 1, air_date: '2099-01-01' } }]);
    return Response.json([]);
  } });
  const client = new mod.WeTrakrClient(); client.setProfile('profile'); await client.restoreSession();
  assert.equal((await client.watchlist()).length, 2);
  const rows = await client.playback(); assert.equal(rows.length, 1); assert.equal(rows[0].episode.number, 3); assert.equal(rows[0].is_up_next, true);
  await client.watchlist(); assert.equal(requests.filter(url => url.includes('/planning/')).length, 2, 'cached lists do not repeatedly spend the provider quota');
});
