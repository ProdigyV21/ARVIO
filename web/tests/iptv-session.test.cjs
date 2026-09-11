const test = require('node:test');
const assert = require('node:assert/strict');
const crypto = require('node:crypto');
const { load, storage } = require('./load.cjs');
const session = load('lib/iptvSession.ts');
const iptv = load('lib/iptv.ts', { './storage': storage(), './http': {} });
const plain = value => JSON.parse(JSON.stringify(value));
const m3uId = (url, epg) => `list_1:m3u:${epg}:${url.length}-${crypto.createHash('sha1').update(url).digest('hex').slice(0,16)}`;

test('custom provider stream paths resolve exactly without confusing HD variants or another provider', async () => {
  const url = 'https://provider.invalid/custom/relay/live/user/pass/101.ts';
  const channels = [{ id: 'list_1:xtream:101', name: 'NPO 1 HD' }, { id: 'list_1:xtream:102', name: 'NPO 1 UHD' }, { id: 'list_2:xtream:101', name: 'Other provider' }];
  const enriched = await iptv.attachM3uChannelIdentities(channels, `#EXTM3U\n#EXTINF:-1 tvg-id="npo1.nl",NPO 1 HD\n${url}`, 'list_1');
  const index = session.channelIdentityIndex(enriched);
  const id = m3uId(url, 'npo1.nl');
  assert.equal(index.get(id).id, 'list_1:xtream:101');
  assert.equal(index.get(id).cloudId, id);
  assert.equal(index.get(id.replace('list_1:', 'list_2:')), undefined);
  assert.deepEqual(plain(session.resolveChannelReferences([id, channels[0].id, 'unavailable'], index)).map(c=>c.id), ['list_1:xtream:101']);
  assert.equal(enriched[1].cloudId, undefined);
});

test('web playback uses Android identity, deduplicates aliases and keeps oldest-first history capped at 40', () => {
  const channel = { id: 'list_1:xtream:10', cloudId: 'list_1:m3u:exact', group: 'Sports', syncAliases: ['list_1:m3u:old'] };
  const result = session.recordTvPlayback({ recentChannelIds: ['one', channel.id, 'two', 'list_1:m3u:old'] }, channel, 200);
  assert.deepEqual(plain(result.recentChannelIds), ['one', 'two', channel.cloudId]);
  assert.equal(result.lastOpenedAt, 200);
  assert.equal(result.lastChannelId, channel.cloudId);
  const full = session.recordTvPlayback({recentChannelIds: Array.from({length:40},(_,i)=>String(i))},channel);
  assert.equal(full.recentChannelIds.length, 40);
  assert.equal(full.recentChannelIds[0], '1');
});

test('concurrent sessions keep both devices plays and the genuinely newest last channel', () => {
  const remote = { lastChannelId: 'tv-new', lastOpenedAt: 300, recentChannelIds: ['shared','tv-new'] };
  const local = { lastChannelId: 'web-new', lastOpenedAt: 200, recentChannelIds: ['shared','web-new'] };
  const merged = session.mergeTvSessions(remote, local);
  assert.equal(merged.lastChannelId, 'tv-new');
  assert.deepEqual(plain(merged.recentChannelIds), ['web-new','shared','tv-new']);
  assert.equal(session.mergeTvSessions(remote, undefined).lastChannelId, 'tv-new');
});

test('malformed TV history is normalized rather than crashing the guide', () => {
  assert.deepEqual(plain(session.normalizeTvSession('{bad').recentChannelIds), []);
  assert.deepEqual(plain(session.normalizeTvSession({recentChannelIds:[null,3,'a','a','']})).recentChannelIds, ['a']);
});
