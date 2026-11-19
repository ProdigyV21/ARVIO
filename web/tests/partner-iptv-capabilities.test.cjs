const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const playlist = {
  id: 'managed-iptv', name: 'Managed IPTV', enabled: true,
  m3uUrl: 'https://iptv.fixture.invalid/get.php?username=qa&password=fixture&type=m3u_plus',
  epgUrl: 'https://iptv.fixture.invalid/xmltv.php?username=qa&password=fixture',
};
const channel = {
  id: 'managed-iptv:9', name: 'QA channel', group: 'QA',
  streamUrl: 'https://iptv.fixture.invalid/live/qa/fixture/9.m3u8',
  catchupDays: 3,
};

function fixture() {
  const hits = [];
  const api = load('lib/iptv.ts', {
    './storage': storage(),
    './http': {
      proxiedUrl: url => url,
      textRequest: async url => {
        hits.push(url);
        const action = new URL(url).searchParams.get('action');
        if (action === 'get_live_streams') return JSON.stringify([{ stream_id: 9, name: 'QA channel', tv_archive: 1, tv_archive_duration: 3 }]);
        if (action === 'get_live_categories') return '[]';
        throw new Error('Unexpected provider request');
      },
    },
  });
  return { api, hits };
}

test('managed IPTV capability flags survive normalization and invalidate snapshots', () => {
  const { api } = fixture();
  const flags = { importLiveTv: false, importVod: true, importSeries: false, enableEpg: false, enableCatchup: false };
  const normalized = api.normalizeIptvPlaylist({ ...playlist, ...flags });
  for (const [key, value] of Object.entries(flags)) assert.equal(normalized[key], value, key);
  for (const key of Object.keys(flags)) {
    assert.notEqual(api.iptvPlaylistSignature([playlist]), api.iptvPlaylistSignature([{ ...playlist, [key]: false }]), key);
  }
});

test('VOD-only packages never fetch live channels or guide data', async () => {
  const { api, hits } = fixture();
  const entries = [{ ...playlist, importLiveTv: false }];
  const snapshot = await api.loadIptvSnapshot(entries);
  assert.equal(snapshot.channels.length, 0);
  assert.equal(Object.keys(await api.loadIptvGuideForChannels(entries, [channel])).length, 0);
  assert.equal((await api.loadXtreamCatchup(entries, channel)).length, 0);
  assert.equal(hits.length, 0);
});

test('EPG and catch-up restrictions apply even to previously cached channel objects', async () => {
  const { api, hits } = fixture();
  const program = { title: 'QA programme', startUtcMillis: Date.now() - 7200000, endUtcMillis: Date.now() - 3600000 };
  for (const flag of ['enabled', 'importLiveTv', 'enableEpg']) {
    const entries = [{ ...playlist, [flag]: false }];
    assert.equal(Object.keys(await api.loadIptvGuideForChannels(entries, [channel])).length, 0, flag);
    assert.equal(await api.loadXtreamCatchup(entries, channel).then(rows => rows.length), 0, flag);
    assert.equal(api.buildXtreamCatchupUrl(entries, channel, program), null, flag);
  }
  const entries = [{ ...playlist, enableCatchup: false }];
  assert.equal((await api.loadXtreamCatchup(entries, channel)).length, 0);
  assert.equal(api.buildXtreamCatchupUrl(entries, channel, program), null);
  assert.equal(hits.length, 0);
  assert.match(api.buildXtreamCatchupUrl([playlist], channel, program), /\/timeshift\//);
});

test('disabling catch-up removes provider archive badges without removing live channels', async () => {
  const { api } = fixture();
  const snapshot = await api.loadIptvSnapshot([{ ...playlist, enableCatchup: false }]);
  assert.equal(snapshot.channels.length, 1);
  assert.equal(snapshot.channels[0].catchupAvailable, false);
  assert.equal(snapshot.channels[0].catchupDays, undefined);
  assert.equal(snapshot.channels[0].catchupType, undefined);
});
