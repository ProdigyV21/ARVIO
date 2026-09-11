const test = require('node:test');
const assert = require('node:assert/strict');
const { createHash, randomBytes } = require('node:crypto');
const { load, storage } = require('./load.cjs');
const playlist = (id, epgUrl = '') => ({ id, name: id, enabled: true, m3uUrl: `https://provider.invalid/${id}.m3u`, epgUrl });
const m3u = '#EXTM3U\n#EXTINF:-1 tvg-id="shared" group-title="Hidden",Example HD\nhttps://provider.invalid/1.ts\n#EXTINF:-1 tvg-id="shared" group-title="Visible",Example SD\nhttps://provider.invalid/2.ts\n';
const stamp = (ms) => new Date(ms).toISOString().replace(/[-:T]/g, '').slice(0,14) + ' +0000';
const xml = `<tv><programme channel="shared" start="${stamp(Date.now()-600000)}" stop="${stamp(Date.now()+600000)}"><title>Live</title></programme></tv>`;
function iptv() { return load('lib/iptv.ts', { './storage': storage(), './http': { proxiedUrl: (x) => x, textRequest: async (url) => url.endsWith('.xml') ? xml : m3u } }); }
function mappers(tmdb = {}) { return load('lib/mappers.ts', { './config': { config: {} }, './mediaImages': {}, './tmdb': tmdb }); }

test('Trakt collection rows infer TV type from show object', () => {
  assert.equal(mappers().traktItemToMedia({ show: { title: 'Series', ids: { tmdb: 42 } } }).mediaType, 'tv');
});
test('Unknown tracker IDs are stable and never fabricated TMDB IDs', () => {
  const m = mappers(); const row = { show: { ids: { trakt: 9876 } } };
  assert.ok(m.traktItemToMedia(row).id < 0);
  assert.equal(m.traktItemToMedia(row).id, m.traktItemToMedia(row).id);
  for (const map of [m.traktHistoryToMedia, m.traktPlaybackToMedia]) assert.ok(map(row).id < 0);
});
test('Tracker hydration resolves the namespace and bounds metadata concurrency', async () => {
  let concurrent = 0, peak = 0;
  const m = mappers({ resolveTmdbId: async () => 123, getDetails: async (item) => { concurrent++; peak = Math.max(peak, concurrent); await new Promise((r) => setTimeout(r, 2)); concurrent--; return item; } });
  const rows = await m.hydrateTraktItems(Array.from({ length: 40 }, () => ({ id: -12, mediaType: 'tv', title: 'Fixture' })));
  assert.equal(rows.length, 40); assert.equal(rows[0].id, 123); assert.ok(peak <= 6);
});
test('Hidden groups disappear from every accessible-channel result, but remain restorable', async () => {
  const m = iptv(); const result = await m.loadIptvSnapshot([playlist('p')], [], [], ['Hidden']);
  assert.equal(result.channels.length, 1); assert.equal(result.allChannels.length, 2);
  assert.equal(m.accessibleChannels(result.allChannels, []).length, 2);
});
test('All four playlists load, without silent truncation', async () => {
  const result = await iptv().loadIptvSnapshot(['a','b','c','d'].map((id) => playlist(id)));
  assert.equal(result.channels.length, 8);
});
test('Shared guide IDs populate all variants only within their provider', async () => {
  const m = iptv(); const lists = [playlist('a', 'https://provider.invalid/a.xml'), playlist('b')];
  const result = await m.loadIptvSnapshot(lists);
  const guide = await m.loadIptvGuideForChannels(lists, result.channels);
  assert.equal(Object.keys(guide).length, 2);
  for (const channel of result.channels) assert.equal(guide[channel.id]?.now?.title, channel.id.startsWith('a:') ? 'Live' : undefined);
});
test('Channel snapshot does not wait for guide downloads', async () => {
  let xmlRequests = 0;
  const m = load('lib/iptv.ts', { './storage': storage(), './http': { proxiedUrl: (x) => x, textRequest: async (url) => { if (url.endsWith('.xml')) { xmlRequests++; throw new Error('No guide'); } return m3u; } } });
  assert.equal((await m.loadIptvSnapshot([playlist('a', 'https://provider.invalid/a.xml')])).channels.length, 2);
  assert.equal(xmlRequests, 0);
});
test('Failed cloud push cannot poison acknowledged cache; reads are isolated clones', async () => {
  const m = load('lib/cloud.ts', { './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true }, './homeserver': {}, './iptv': {}, './mediaImages': {}, './http': { jsonRequest: async (url) => { if (url.endsWith('account-sync-pull')) return { payload: { fixture: 'server' } }; throw new Error('offline'); } } });
  const auth = { session: { userId: 'a', accessToken: 'fake' }, isNetlifySession: true, accessToken: async () => 'fake' };
  await assert.rejects(m.mutateCloudPayload(auth, (root) => { root.fixture = 'unsaved'; }), /offline/);
  assert.equal((await m.pullRawPayload(auth)).fixture, 'server');
  const copy = await m.pullRawPayload(auth); copy.fixture = 'changed';
  assert.equal((await m.pullRawPayload(auth)).fixture, 'server');
});
test('Durable settings outbox keeps failed edits and replays only its account', async () => {
  const disk = storage(); let offline = true, writes = 0;
  const m = load('lib/settingsOutbox.ts', { './storage': disk, './cloud': { saveCloudSettings: async () => { if (offline) throw new Error('offline'); writes++; } } });
  const auth = { session: { userId: 'a' } };
  m.queueSettings(auth, 'p', { accentColor: 'white' }, { accentColor: 'blue' });
  await assert.rejects(m.flushSettingsOutbox(auth)); assert.equal(m.hasPendingSettings(auth), true);
  auth.session.userId = 'b'; await m.flushSettingsOutbox(auth); assert.equal(writes, 0);
  auth.session.userId = 'a'; offline = false; await m.flushSettingsOutbox(auth);
  assert.equal(writes, 1); assert.equal(m.hasPendingSettings(auth), false);
});
test('Cloud renewal uses the server configuration when browser app key is missing', async () => {
  const requests = [];
  const { AuthClient } = load('lib/auth.ts', { './storage': storage(), './config': { config: {}, hasNetlifyBackendConfig: () => false, hasSupabaseConfig: () => false }, './http': { jsonRequest: async (url) => { requests.push(url); return { access_token: 'new', refresh_token: 'rotated', user: { id: 'a' } }; } } });
  const auth = new AuthClient(); auth.session = { userId: 'a', accessToken: 'old', refreshToken: 'old', provider: 'netlify', expiresAt: 0 };
  await Promise.all([auth.accessToken(), auth.accessToken()]);
  assert.equal(requests.length, 1); assert.equal(requests[0], '/api/cloud-auth/auth-refresh'); assert.equal(auth.session.refreshToken, 'rotated');
});
test('Trakt renewal is single flight for a profile', async () => {
  const { TraktClient } = load('lib/trakt.ts', { './config': { config: {} }, './storage': storage(), './http': {} });
  const client = new TraktClient(); client.token = { refresh_token: 'old', expires_at: 0 }; let calls = 0;
  client.trakt = async () => { calls++; await new Promise((r) => setTimeout(r, 3)); return { access_token: 'new', refresh_token: 'new', expires_in: 3600 }; };
  await Promise.all([client.refreshIfNeeded(), client.refreshIfNeeded()]); assert.equal(calls, 1);
});
test('Simkl Up Next survives optional playback failure; total failure is not empty success', async () => {
  const { SimklClient } = load('lib/simkl.ts', { './config': { config: {} }, './sync': {}, './storage': storage(), './http': {}, './tmdb': {} });
  const client = new SimklClient(); client.token = { access_token: 'fake' };
  client.simkl = async () => { throw new Error('offline'); };
  client.loadSnapshot = async () => ({ shows: [{ status: 'watching', show: { ids: { tmdb: 123 } }, next_to_watch: 'S1E2' }], anime: [] });
  client.resolveMedia = async (item) => item;
  const rows = await client.playback(); assert.equal(rows[0].episode.number, 2);
  client.loadSnapshot = async () => { throw new Error('offline'); }; await assert.rejects(client.playback());
});
test('PIN validation is byte-compatible with Android salted SHA-256', async () => {
  const salt = randomBytes(16); const hash = createHash('sha256').update(salt).update('12345').digest('base64');
  const m = load('lib/profilePin.ts'); const value = `${salt.toString('base64')}$${hash}`;
  assert.equal(await m.verifyProfilePin('12345', value), true);
  assert.equal(await m.verifyProfilePin('54321', value), false);
  assert.equal(await m.verifyProfilePin('12345', 'broken'), false);
});
test('Hosted proxy rejects private, loopback and mapped addresses and strips unsafe headers', () => {
  const m = load('lib/server/safeProxy.ts');
  for (const ip of ['127.0.0.1', '10.0.0.1', '192.168.1.1', '169.254.169.254', '100.64.0.1', '::1', '::ffff:127.0.0.1', 'fc00::1', 'fe80::1']) assert.equal(m.isPublicAddress(ip), false, ip);
  assert.equal(m.isPublicAddress('8.8.8.8'), true);
  assert.equal(m.safeProxyHeaders({ host: 'internal', cookie: 'secret', authorization: 'Bearer x' }).has('host'), false);
  assert.equal(m.safeProxyHeaders({ cookie: 'secret' }).has('cookie'), false);
});
test('Proxy pins DNS and rejects a redirect into a private network before fetching it', async () => {
  const requested = [];
  const m = load('lib/server/safeProxy.ts', { 'node:dns/promises': { lookup: async (host) => [{ address: host === 'private.invalid' ? '127.0.0.1' : '8.8.8.8', family: 4 }] }, undici: { Agent: class { async destroy() {} }, fetch: async (url) => { requested.push(String(url)); return new Response(null, { status: 302, headers: { location: 'http://private.invalid/data' } }); } } });
  await assert.rejects(m.safeProxyFetch(new URL('http://public.invalid/data'), {}), /Blocked proxy/);
  assert.equal(requested.length, 1);
});

function trackingRouter() {
  const disk = storage();
  const client = () => ({ isConnected: true, currentProfileId: 'p', watchlist: async () => [], playback: async () => [], watched: async () => [], addToHistory: async () => {} });
  const trakt = client(), simkl = client();
  const m = load('lib/sync.ts', { './storage': disk, './store': { traktClient: trakt }, './simkl': { simklClient: simkl }, './mdblist': { mdblistClient: { isConnected: false } } });
  return { m, trakt, simkl };
}

test('Tracker read preferences stay independent from connected services', async () => {
  const { m, trakt, simkl } = trackingRouter();
  m.saveTrackingPreferences('p', { ...m.defaultTrackingPreferences(), watchlistReadMode: 'trakt', continueWatchingReadMode: 'simkl' });
  trakt.watchlist = async () => ['trakt'];
  simkl.watchlist = async () => { throw new Error('must not read'); };
  assert.deepEqual(Array.from(await m.syncClient().watchlist()), ['trakt']);
  assert.equal(m.readsFrom('continueWatching', 'trakt'), false);
  assert.equal(m.readsFrom('continueWatching', 'simkl'), true);
});

test('A partial tracker outage is not an authoritative empty or partial snapshot', async () => {
  const { m, trakt, simkl } = trackingRouter();
  trakt.watchlist = async () => ['retained'];
  simkl.watchlist = async () => { throw new Error('offline'); };
  await assert.rejects(m.syncClient().watchlist(), /saved library has been kept/);
  simkl.watchlist = async () => [];
  trakt.watchlist = async () => [];
  assert.equal((await m.syncClient().watchlist()).length, 0);
});

test('A partial tracking write failure is surfaced, not reported as success', async () => {
  const { m, trakt, simkl } = trackingRouter(); let saved = 0;
  trakt.addToHistory = async () => { saved++; };
  simkl.addToHistory = async () => { throw new Error('offline'); };
  await assert.rejects(m.syncClient().addToHistory({ mediaType: 'movie', tmdbId: 123 }), /Not all tracking services saved/);
  assert.equal(saved, 1);
});

test('An invalidated in-flight cloud read cannot overwrite a newer snapshot', async () => {
  let resolveOld, calls = 0;
  const m = load('lib/cloud.ts', { './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true }, './homeserver': {}, './iptv': {}, './mediaImages': {}, './http': { jsonRequest: async () => { calls++; return calls === 1 ? new Promise((resolve) => { resolveOld = resolve; }) : { payload: { value: 'new' } }; } } });
  const auth = { session: { userId: 'a', accessToken: 'fake' }, isNetlifySession: true, accessToken: async () => 'fake' };
  const old = m.pullRawPayload(auth);
  await new Promise((resolve) => setImmediate(resolve));
  m.invalidateRawPayloadCache();
  assert.equal((await m.pullRawPayload(auth)).value, 'new');
  resolveOld({ payload: { value: 'old' } }); await old;
  assert.equal((await m.pullRawPayload(auth)).value, 'new');
  assert.equal(calls, 2);
});

test('HTTP deadline includes reading a stalled response body', async () => {
  let cleared = false;
  const m = load('lib/http.ts', { './config': { config: {} } }, {
    setTimeout: (callback) => setTimeout(callback, 5),
    clearTimeout: (timer) => { cleared = true; clearTimeout(timer); },
    fetch: async (_url, init) => ({ ok: true, status: 200, json: () => new Promise((_resolve, reject) => {
      init.signal.addEventListener('abort', () => reject(init.signal.reason), { once: true });
    }) })
  });
  await assert.rejects(m.jsonRequest('https://service.invalid'), /too long/);
  assert.equal(cleared, true);
});

test('HTTP cancellation follows caller cancellation and does not leak the deadline', async () => {
  const caller = new AbortController(); let cleared = false;
  const m = load('lib/http.ts', { './config': { config: {} } }, {
    clearTimeout: (timer) => { cleared = true; clearTimeout(timer); },
    fetch: async (_url, init) => new Promise((_resolve, reject) => {
      init.signal.addEventListener('abort', () => reject(init.signal.reason), { once: true });
    })
  });
  const pending = m.textRequest('https://service.invalid', { signal: caller.signal });
  caller.abort(new Error('caller cancelled'));
  await assert.rejects(pending, /caller cancelled/);
  assert.equal(cleared, true);
});
