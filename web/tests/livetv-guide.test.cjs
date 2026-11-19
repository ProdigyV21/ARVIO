const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

// Values from the module sandbox are cross-realm: compare their JSON.
const deq = (actual, expected, message) => assert.deepEqual(JSON.parse(JSON.stringify(actual)), expected, message);
const guide = () => load('lib/iptvGuide.ts');
const zap = () => load('lib/liveZap.ts', {}, { Element: class {} });

test('first playlist edit while a load is in flight reloads the new list and always clears loading', async () => {
  const { createIptvRefresher } = load('lib/iptvRefresh.ts');
  let current = { scope: 'a:p', signature: 'empty', settings: { playlists: [] } };
  const loads = [], applied = [], loading = [];
  let release;
  const refresh = createIptvRefresher({
    read: () => current,
    load: (settings) => { loads.push(settings.playlists.length); return loads.length === 1 ? new Promise(r => { release = () => r('old'); }) : Promise.resolve('new'); },
    apply: (result, state) => applied.push([result, state.signature]),
    report: () => assert.fail('no error expected'),
    setLoading: (value) => loading.push(value)
  });
  const first = refresh();
  // The user adds the first playlist while the empty list is still loading.
  current = { scope: 'a:p', signature: 'one', settings: { playlists: [{ id: 'x' }] } };
  release();
  await first;
  deq(loads, [0, 1], 'the stale run is followed by a run with the new playlist');
  deq(applied, [['new', 'one']], 'only the current result is applied');
  assert.equal(loading.at(-1), false, 'the loading label never sticks');
});

test('refreshes for the same playlists share one load; another profile never reruns', async () => {
  const { createIptvRefresher } = load('lib/iptvRefresh.ts');
  let current = { scope: 'a:p', signature: 'one', settings: {} };
  let count = 0, release;
  const loading = [];
  const refresh = createIptvRefresher({
    read: () => current, load: () => { count++; return new Promise(r => { release = r; }); },
    apply: () => assert.fail('switched profile must not apply'), report() {}, setLoading: (v) => loading.push(v)
  });
  const a = refresh(); const b = refresh();
  assert.equal(a, b);
  current = { scope: 'a:other', signature: 'two', settings: {} };
  release('x');
  await a;
  assert.equal(count, 1);
  deq(loading, [true, false]);
});

test('quiet refreshes never touch the loading label or report errors', async () => {
  const { createIptvRefresher } = load('lib/iptvRefresh.ts');
  const loading = [], reports = [];
  const refresh = createIptvRefresher({ read: () => ({ scope: 's', signature: 'x', settings: {} }), load: async () => { throw new Error('boom'); },
    apply() {}, report: (e) => reports.push(e), setLoading: (v) => loading.push(v) });
  await refresh({ quiet: true });
  deq(loading, []);
  deq(reports, []);
  await refresh();
  assert.equal(reports.length, 1);
  deq(loading, [true, false]);
});

test('guide clock honours 12h/24h without a leading zero in 12h', () => {
  const { formatGuideTime } = guide();
  const evening = new Date(2026, 9, 24, 21, 5).getTime();
  const morning = new Date(2026, 9, 24, 9, 0).getTime();
  assert.equal(formatGuideTime(evening, '24h'), '21:05');
  assert.equal(formatGuideTime(morning, '24h'), '09:00');
  assert.match(formatGuideTime(morning, '12h'), /^9:00\s?AM$/i);
  assert.match(formatGuideTime(evening, '12h'), /^9:05\s?PM$/i);
  assert.equal(formatGuideTime(Number.NaN, '24h'), '');
});

test('Up/Down keep the focus time: the programme airing then, else the latest earlier one', () => {
  const { slotAtFocusTime, focusTimeForSlot } = guide();
  const row = [{ start: 0, end: 60 }, { start: 60, end: 120 }, { start: 120, end: 180 }];
  assert.equal(slotAtFocusTime(row, 75), 1);
  assert.equal(slotAtFocusTime(row, 120), 2);
  assert.equal(slotAtFocusTime([{ start: 0, end: 30 }, { start: 90, end: 120 }], 60), 0, 'gap: stay on the earlier programme');
  assert.equal(slotAtFocusTime([{ start: 100, end: 130 }], 60), 0, 'nothing earlier: first programme');
  assert.equal(slotAtFocusTime([], 60), -1);
  // A programme that began before the window sets the focus time to the window start.
  assert.equal(focusTimeForSlot({ start: 10, end: 90 }, 30), 30);
  assert.equal(focusTimeForSlot({ start: 40, end: 90 }, 30), 40);
});

test('channel search matches names, numbers and the titles of loaded programmes', () => {
  const { channelMatchesQuery } = guide();
  const channel = { id: 'p:1', name: 'BBC One', group: 'UK', number: '101', streamUrl: '' };
  const entry = { now: { title: 'Match of the Day', startUtcMillis: 0, endUtcMillis: 1 }, next: { title: 'News at Ten', startUtcMillis: 1, endUtcMillis: 2 } };
  assert.equal(channelMatchesQuery(channel, 'bbc'), true);
  assert.equal(channelMatchesQuery(channel, '101'), true);
  assert.equal(channelMatchesQuery(channel, 'match of'), false, 'no guide loaded');
  assert.equal(channelMatchesQuery(channel, 'match of', entry), true);
  assert.equal(channelMatchesQuery(channel, 'news at', entry), true);
  assert.equal(channelMatchesQuery(channel, 'weather', entry), false);
});

test('playlist errors map to plain causes', () => {
  const { playlistErrorCause, splitPlaylistWarning } = guide();
  assert.equal(playlistErrorCause('direct: not found; proxy: Blocked proxy target; proxy with media headers: Blocked proxy target'), 'not-found');
  assert.equal(playlistErrorCause('direct: The provider returned an HTML page instead of an M3U playlist.'), 'html');
  assert.equal(playlistErrorCause('direct: Failed to fetch; proxy: Blocked proxy target'), 'blocked');
  assert.equal(playlistErrorCause('direct: Failed to fetch; proxy: The service took too long to respond. Please retry.'), 'unreachable');
  assert.equal(playlistErrorCause('Request failed with 401'), 'auth');
  assert.equal(playlistErrorCause('IPTV provider requests are temporarily paused. Please wait before refreshing.'), 'paused');
  assert.equal(playlistErrorCause('Playlist response did not contain any channels.'), 'empty');
  deq(splitPlaylistWarning('My list: boom: bang'), { name: 'My list', message: 'boom: bang' });
});

test('Xtream Codes login builds the same get.php and xmltv.php links as Android', () => {
  const { xtreamPlaylistUrls } = guide();
  const urls = xtreamPlaylistUrls('provider.example:8080/', 'me', 'secret ');
  assert.equal(urls.m3uUrl, 'http://provider.example:8080/get.php?username=me&password=secret&type=m3u_plus&output=m3u8');
  assert.equal(urls.epgUrl, 'http://provider.example:8080/xmltv.php?username=me&password=secret');
  assert.equal(xtreamPlaylistUrls('https://p.example/player_api.php', 'a', 'b').epgUrl, 'https://p.example/xmltv.php?username=a&password=b');
  assert.equal(xtreamPlaylistUrls('', 'a', 'b'), null);
  assert.equal(xtreamPlaylistUrls('p.example', 'a', ''), null);
  const { xtreamInfoFromUrl } = load('lib/iptv.ts', { './http': {}, './storage': { loadStored: (_k, f) => f, saveStored() {} } });
  deq({ ...xtreamInfoFromUrl(urls.m3uUrl) }, { baseUrl: 'http://provider.example:8080', username: 'me', password: 'secret' });
});

test('playlist links are masked to their host', () => {
  const { maskedUrl } = load('lib/maskUrl.ts');
  assert.equal(maskedUrl('https://torrentio.example/realdebrid=SECRET/manifest.json'), 'torrentio.example/•••');
  assert.equal(maskedUrl('https://addon.example/'), 'addon.example');
  assert.ok(!maskedUrl('http://p.example/get.php?username=me&password=secret').includes('secret'));
  assert.ok(!maskedUrl('not a url with secret').includes('secret'));
});

test('zap keys: Up/CH+ next, Down/CH- previous, digits, OK info, Back', () => {
  const { liveZapAction } = zap();
  const key = (key, keyCode = 0) => ({ key, keyCode, altKey: false, ctrlKey: false, metaKey: false });
  deq(liveZapAction(key('ArrowUp')), { type: 'zap', delta: 1 });
  deq(liveZapAction(key('Unidentified', 427)), { type: 'zap', delta: 1 });
  deq(liveZapAction(key('Unidentified', 428)), { type: 'zap', delta: -1 });
  deq(liveZapAction(key('PageDown')), { type: 'zap', delta: -1 });
  deq(liveZapAction(key('ChannelUp')), { type: 'zap', delta: 1 });
  deq(liveZapAction(key('7')), { type: 'digit', digit: 7 });
  deq(liveZapAction(key('Enter')), { type: 'info' });
  assert.equal(liveZapAction(key('Enter'), true), null, 'OK on a focused player button stays a click');
  deq(liveZapAction(key('Escape')), { type: 'back' });
  assert.equal(liveZapAction(key('ArrowLeft')), null, 'seeking stays with the player');
  assert.equal(liveZapAction({ ...key('ArrowUp'), ctrlKey: true }), null);
});

test('zapping wraps round the current list and channel numbers tune like Android', () => {
  const { zapTarget, appendChannelDigit, exactChannelNumber, uniqueChannelNumberPrefix } = zap();
  const list = [{ id: 'a', number: '1' }, { id: 'b', number: '2' }, { id: 'c', number: '12' }, { id: 'd', number: '007' }];
  assert.equal(zapTarget(list, 'a', 1).id, 'b');
  assert.equal(zapTarget(list, 'a', -1).id, 'd');
  assert.equal(zapTarget(list, 'd', 1).id, 'a');
  assert.equal(zapTarget(list, 'missing', 1).id, 'a');
  assert.equal(zapTarget([list[0]], 'a', 1), null);
  assert.equal(appendChannelDigit('1', 2, 1000, 1500), '12');
  assert.equal(appendChannelDigit('1', 2, 1000, 3000), '2', 'a stale buffer starts over');
  assert.equal(appendChannelDigit('1234', 5, 0, 1), '2345');
  assert.equal(exactChannelNumber(list, '7').id, 'd');
  assert.equal(exactChannelNumber(list, '12').id, 'c');
  assert.equal(uniqueChannelNumberPrefix(list, '1'), null, '1 and 12 both start with 1');
  assert.equal(uniqueChannelNumberPrefix(list, '12').id, 'c');
});

test('back in the fullscreen live player docks it once per press and zaps reach Live TV', () => {
  const lib = zap();
  const event = (key) => ({ key, keyCode: 0, altKey: false, ctrlKey: false, metaKey: false, target: null, defaultPrevented: false, preventDefault() { this.defaultPrevented = true; } });
  let collapsed = 0;
  const context = { canDock: true, collapse: () => collapsed++ };
  assert.equal(lib.handleLiveZapKey(event('ArrowUp'), context), false, 'no Live TV screen registered');
  const actions = [];
  const off = lib.registerLiveZap((action) => { actions.push(action.type); return action.type !== 'back'; });
  const up = event('ArrowUp');
  assert.equal(lib.handleLiveZapKey(up, context), true);
  assert.equal(up.defaultPrevented, true);
  assert.equal(lib.handleLiveZapKey(event('Escape'), context), true);
  // TV Back arrives as two Escapes at once: one step only.
  assert.equal(lib.handleLiveZapKey(event('Escape'), context), true);
  assert.equal(collapsed, 1);
  off();
  assert.equal(lib.handleLiveZapKey(event('ArrowUp'), context), false);
  deq(actions, ['zap', 'back']);
  lib.keepLivePlayerExpanded();
  assert.equal(lib.consumeKeepLivePlayerExpanded(), true);
  assert.equal(lib.consumeKeepLivePlayerExpanded(), false);
});

test('favourite and hidden-group edits keep the synced shapes', () => {
  const { toggleFavoriteChannel, moveFavoriteChannel, favoritePosition, toggleGroupKey } = load('lib/iptvFavorites.ts');
  const index = new Map([['p:1', { id: 'p:1', cloudId: 'cloud-1' }], ['cloud-1', { id: 'p:1', cloudId: 'cloud-1' }], ['p:2', { id: 'p:2' }]]);
  deq(toggleFavoriteChannel(['p:2'], 'p:1', index), ['cloud-1', 'p:2'], 'new favourites go first, by cloud id');
  deq(toggleFavoriteChannel(['cloud-1', 'p:2', 'unloaded'], 'p:1', index), ['p:2', 'unloaded'], 'unloaded references are kept');
  deq(moveFavoriteChannel(['cloud-1', 'p:2'], 'p:2', -1, index), ['p:2', 'cloud-1']);
  deq(moveFavoriteChannel(['cloud-1', 'p:2'], 'p:1', -1, index), ['cloud-1', 'p:2']);
  assert.equal(favoritePosition(['x', 'cloud-1'], 'p:1', index), 1);
  deq(toggleGroupKey(['a|News'], 'a|Sports'), ['a|Sports', 'a|News']);
  deq(toggleGroupKey(['a|News'], 'a|News'), []);
});

test('categories start collapsed below 1024 px', () => {
  const source = require('node:fs').readFileSync(require('node:path').resolve(__dirname, '../components/livetv/LiveTvScreen.tsx'), 'utf8');
  const body = source.match(/export function categoriesOpenByDefault\(width: number\) \{([^}]*)\}/)[1];
  const fn = new Function('width', body);
  assert.equal(fn(390), false);
  assert.equal(fn(820), false);
  assert.equal(fn(1024), true);
  assert.equal(fn(1920), true);
});
