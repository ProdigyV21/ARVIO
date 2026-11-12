const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

class HttpError extends Error {
  constructor(status, message, retryAfter = null) { super(message); this.status = status; this.retryAfter = retryAfter; }
}
const token = { access_token: 'approved', refresh_token: 'refresh', expires_in: 3600 };
const code = (id = 'device', expires_in = 300) => ({ device_code: id, user_code: id.toUpperCase(),
  verification_uri: 'https://mdblist.invalid/device', interval: 5, expires_in });

function hooks() {
  const slots = [];
  let cursor = 0, effects = [];
  const changed = (slot, deps) => !slot || deps.some((value, i) => !Object.is(value, slot.deps[i]));
  return {
    react: {
      useRef(initial) { return slots[cursor++] ??= { current: initial }; },
      useCallback(fn, deps) {
        const i = cursor++;
        if (changed(slots[i], deps)) slots[i] = { fn, deps };
        return slots[i].fn;
      },
      useEffect(effect, deps) {
        const i = cursor++;
        if (changed(slots[i], deps)) effects.push(() => { slots[i]?.cleanup?.(); slots[i] = { deps, cleanup: effect() }; });
      },
    },
    render(fn) { cursor = 0; effects = []; const result = fn(); effects.forEach(effect => effect()); return result; },
  };
}

function fixture(respond = async url => url.includes('/user') ? { username: 'fixture' } : token) {
  const saved = storage();
  const calls = [], cloud = [], refreshes = [];
  let now = Date.UTC(2026, 0, 1);
  class Clock extends Date { static now() { return now; } }
  const { MdbListClient } = load('lib/mdblist.ts', { './storage': saved, './http': { HttpError,
    jsonRequest: async (url, init) => { calls.push({ url, init, at: now }); return respond(url, init); },
  } }, { Date: Clock, window: { location: { origin: 'https://arvio.invalid' } } });
  const client = new MdbListClient();
  client.setProfile('A');
  const h = hooks();
  const authClient = { session: { userId: 'owner' } };
  const tracker = { isConnected: false, token: null, setProfile() {} };
  const { useTrackingActions } = load('lib/store/tracking.ts', {
    react: h.react, '../http': { HttpError }, '../mdblist': { mdblistClient: client },
    '../cloud': { saveCloudTrackingSelection: async (_auth, profile, payload) => cloud.push({ profile, payload }) },
    '../mappers': {}, '../storage': saved, '../wetrakr': { weTrakrClient: {} },
    '../simkl': { simklClient: tracker }, './clients': { authClient, traktClient: tracker }, './listCache': {},
    '../sync': { loadTrackingPreferences: () => ({ watchlistReadMode: 'auto', continueWatchingReadMode: 'auto', watchedReadMode: 'auto', writeToTrakt: false, writeToSimkl: false }), saveTrackingPreferences() {} },
  }, { Date: Clock });
  const store = { activeProfileId: 'A', auth: authClient.session, activeProfileIdRef: { current: 'A' },
    mdblistDeviceCodeRef: { current: null }, deviceCodeRef: { current: null }, simklDeviceCodeRef: { current: null },
    setMdblistDeviceCode(value) { store.mdblistDeviceCodeRef.current = value; },
    setMdblistConnected() {}, setTrackingPreferences() {}, refreshData: async profile => refreshes.push(profile),
    setDeviceCode() {}, setSimklConnected() {}, setSimklDeviceCode() {}, setTraktConnected() {},
  };
  const render = () => h.render(() => useTrackingActions(store));
  const f = { client, saved, calls, cloud, refreshes, store, actions: render(), advance(ms) { now += ms; },
    switchProfile(id) { store.activeProfileId = id; store.activeProfileIdRef.current = id; client.setProfile(id); f.actions = render(); },
    switchAccount(userId) { authClient.session = { userId }; store.auth = authClient.session; f.actions = render(); },
  };
  return f;
}

test('late device-code responses are discarded after profile switches, including A to B to A', async () => {
  for (const ids of [['B'], ['B', 'A']]) {
    let release;
    const f = fixture(async () => new Promise(resolve => { release = resolve; }));
    const pending = f.actions.beginMdblist();
    for (const id of ids) f.switchProfile(id);
    assert.equal(f.calls[0].init.signal.aborted, true);
    release(code());
    await pending;
    assert.equal(f.store.mdblistDeviceCodeRef.current, null);
    assert.equal(f.cloud.length, 0);
  }
});

test('changing the cloud account invalidates authorization even when profile ids match', async () => {
  let release;
  const f = fixture(async () => new Promise(resolve => { release = resolve; }));
  const pending = f.actions.beginMdblist();
  f.switchAccount('different-owner');
  release(code());
  await pending;
  assert.equal(f.store.mdblistDeviceCodeRef.current, null);
  assert.equal(f.cloud.length, 0);
});

test('the newest device-code request wins when responses finish out of order', async () => {
  const releases = [];
  const f = fixture(async () => new Promise(resolve => releases.push(resolve)));
  const first = f.actions.beginMdblist();
  const second = f.actions.beginMdblist();
  releases[1](code('new'));
  await second;
  releases[0](code('old'));
  await first;
  assert.equal(f.store.mdblistDeviceCodeRef.current.device_code, 'new');
});

test('late OAuth tokens cannot reconnect after profile changes, cancellation or disconnect', async () => {
  for (const change of [f => f.switchProfile('B'), f => f.actions.cancelMdblistLink(), f => f.actions.disconnectMdblist()]) {
    let release;
    const f = fixture(async url => url.includes('device-authorization') ? code() : new Promise(resolve => { release = resolve; }));
    await f.actions.beginMdblist();
    const pending = f.actions.pollMdblist();
    await Promise.resolve();
    await change(f);
    assert.equal(f.calls[1].init.signal.aborted, true);
    release(token);
    await pending;
    assert.equal(f.client.token, null);
    assert.equal(f.saved.loadStored('arvio.web.mdblist.token:B', null), null);
    assert.ok(f.cloud.every(entry => entry.payload.mdbListAccessToken === null));
  }
});

test('an in-flight OAuth response cannot overwrite a newer API-key connection', async () => {
  let release;
  const f = fixture(async url => {
    if (url.includes('device-authorization')) return code();
    if (url.includes('/user')) return { username: 'api-key-account' };
    return new Promise(resolve => { release = resolve; });
  });
  await f.actions.beginMdblist();
  const pending = f.actions.pollMdblist();
  await Promise.resolve();
  await f.actions.connectMdblist('manual-key');
  release(token);
  await pending;
  assert.equal(f.client.key, 'manual-key');
  assert.equal(f.client.token, null);
  assert.equal(f.cloud.length, 1);
  assert.equal(f.cloud[0].payload.mdbListApiKey, 'manual-key');
});

test('a late API-key validation cannot overwrite a newer OAuth connection', async () => {
  let release;
  const f = fixture(async url => {
    if (url.includes('/user')) return new Promise(resolve => { release = resolve; });
    return url.includes('device-authorization') ? code() : token;
  });
  const pending = f.actions.connectMdblist('old-key');
  await f.actions.beginMdblist();
  await f.actions.pollMdblist();
  release({ username: 'old-account' });
  await pending;
  assert.equal(f.client.key, null);
  assert.equal(f.client.token.accessToken, token.access_token);
  assert.equal(f.cloud.length, 1);
});

test('manual and automatic checks share one token exchange, storage commit and cloud write', async () => {
  let release;
  const f = fixture(async url => url.includes('device-authorization') ? code() : new Promise(resolve => { release = resolve; }));
  await f.actions.beginMdblist();
  const manual = f.actions.pollMdblist();
  const automatic = f.actions.pollMdblist();
  assert.equal(manual, automatic);
  await Promise.resolve();
  assert.equal(f.calls.length, 2);
  release(token);
  await Promise.all([manual, automatic]);
  assert.equal(f.cloud.length, 1);
  assert.equal(f.cloud[0].profile, 'A');
  assert.equal(f.cloud[0].payload.mdbListRefreshToken, 'refresh');
  assert.equal(f.saved.loadStored('arvio.web.mdblist.token:A', null).accessToken, 'approved');
  assert.deepEqual(f.refreshes, ['A']);
  assert.equal(f.store.mdblistDeviceCodeRef.current, null);
});

test('pending responses enforce the provider interval across manual and automatic retries', async () => {
  const f = fixture(async url => {
    if (url.includes('device-authorization')) return code();
    throw new HttpError(400, 'authorization_pending');
  });
  await f.actions.beginMdblist();
  await assert.rejects(f.actions.pollMdblist(), /not approved/);
  await assert.rejects(f.actions.pollMdblist(), /not approved/);
  assert.equal(f.calls.length, 2);
  f.advance(5000);
  await assert.rejects(f.actions.pollMdblist(), /not approved/);
  assert.equal(f.calls.length, 3);
});

test('slow_down and Retry-After delay every polling caller without sending extra requests', async () => {
  let checks = 0;
  const f = fixture(async url => {
    if (url.includes('device-authorization')) return code();
    checks++;
    throw checks === 1 ? new HttpError(400, 'slow_down') : new HttpError(429, 'rate limited', '60');
  });
  await f.actions.beginMdblist();
  await assert.rejects(f.actions.pollMdblist(), /limiting/);
  f.advance(5000);
  await assert.rejects(f.actions.pollMdblist(), /not approved/);
  assert.equal(checks, 1);
  f.advance(5000);
  await assert.rejects(f.actions.pollMdblist(), /limiting/);
  f.advance(15000);
  await assert.rejects(f.actions.pollMdblist(), /not approved/);
  assert.equal(checks, 2);
  f.advance(45000);
  await assert.rejects(f.actions.pollMdblist(), /limiting/);
  assert.equal(checks, 3);
});

test('local device-code expiry stops polling before a network request is sent', async () => {
  const f = fixture(async () => code('expires', 2));
  await f.actions.beginMdblist();
  f.advance(2000);
  await assert.rejects(f.actions.pollMdblist(), /expired/);
  assert.equal(f.calls.length, 1);
});
