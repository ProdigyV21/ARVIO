const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

function iptv(textRequest = async () => { throw new Error('Unexpected playlist request'); }) {
  return load('lib/iptv.ts', {
    './storage': storage(),
    './http': { proxiedUrl: (url) => url, textRequest }
  });
}

function parse(...lines) {
  return structuredClone(iptv().parseM3u(['#EXTM3U', ...lines].join('\r\n'), 'fixture'));
}

test('VLC user agent, referrer and origin belong only to their stream', () => {
  const channels = parse(
    '#EXTVLCOPT:http-user-agent=Orphan/1.0',
    '#EXTINF:-1 tvg-id="news" group-title="News",News HD',
    '#EXTVLCOPT:http-user-agent=VLC/3.0.20 LibVLC/3.0.20',
    '#EXTVLCOPT:http-referrer=https://portal.invalid/watch?channel=1&mode=live',
    '#EXTVLCOPT:http-origin=https://portal.invalid',
    '#EXTVLCOPT:network-caching=1000',
    '# a comment between options and URL',
    '',
    'https://media.invalid/live/1.m3u8?token=a%2Fb+z&expires=123',
    '#EXTINF:-1,Next channel',
    'https://media.invalid/live/2.ts'
  );
  assert.equal(channels.length, 2);
  assert.deepEqual(channels[0].requestHeaders, {
    'User-Agent': 'VLC/3.0.20 LibVLC/3.0.20',
    Referer: 'https://portal.invalid/watch?channel=1&mode=live',
    Origin: 'https://portal.invalid'
  });
  assert.equal(channels[0].streamUrl, 'https://media.invalid/live/1.m3u8?token=a%2Fb+z&expires=123');
  assert.equal(channels[1].requestHeaders, undefined);
});

for (const [agent, referer, origin] of [
  ['http-user-agent', 'http-referrer', 'http-origin'],
  ['user-agent', 'http-referer', 'origin'],
  ['User-Agent', 'Referer', 'Origin'],
  ['useragent', 'referrer', 'origin']
]) {
  test(`EXTINF header attributes support ${agent}, ${referer} and ${origin}`, () => {
    const [channel] = parse(
      `#EXTINF:-1 ${agent}="Player/1.0 (one, two)" ${referer}='https://portal.invalid/a?x=1&y=2' ${origin}=https://portal.invalid,News, International`,
      'https://media.invalid/stream'
    );
    assert.equal(channel.name, 'News, International');
    assert.deepEqual(channel.requestHeaders, {
      'User-Agent': 'Player/1.0 (one, two)',
      Referer: 'https://portal.invalid/a?x=1&y=2',
      Origin: 'https://portal.invalid'
    });
  });
}

test('Header-like text in unrelated attributes and channel titles is not a header', () => {
  const [channel] = parse(
    '#EXTINF:-1 not-user-agent="Ignore" tvg-logo="https://img.invalid/icon?origin=ignore",News user-agent="Also ignore"',
    'https://media.invalid/stream'
  );
  assert.equal(channel.requestHeaders, undefined);
  assert.equal(channel.name, 'News user-agent="Also ignore"');
});

test('Pipe headers decode once and preserve every byte of the stream URL', () => {
  const url = 'https://media.invalid/a%2Fb/Live.M3U8?signature=a%2Bb%2F%3D&literal=%7C&plus=+&x=1&x=2#part';
  const [channel] = parse(
    '#EXTINF:-1,Pipe headers',
    `${url}|User-Agent=Player%2F1.0+TV&Referer=https%3A%2F%2Fportal.invalid%2Fwatch%3Fa%3D1%26b%3D2&Origin=https%3A%2F%2Fportal.invalid&Authorization=Bearer+abc%3D%3D&Cookie=session%3Da%2Bb%3D%3D%3B+mode%3Dlive&X-Token=keep%252Fencoded%7Cvalue`
  );
  assert.equal(channel.streamUrl, url);
  assert.deepEqual(channel.requestHeaders, {
    'User-Agent': 'Player/1.0 TV',
    Referer: 'https://portal.invalid/watch?a=1&b=2',
    Origin: 'https://portal.invalid',
    Authorization: 'Bearer abc==',
    Cookie: 'session=a+b==; mode=live',
    'X-Token': 'keep%2Fencoded|value'
  });
});

test('Pipe headers override VLC options which override attributes, case-insensitively', () => {
  const metadata = [
    '#EXTINF:-1 user-agent="Attribute" referer="https://attribute.invalid" origin="https://attribute.invalid",Precedence',
    '#EXTVLCOPT:HTTP-USER-AGENT="First option"',
    '#extvlcopt:http-user-agent=Last option',
    '#EXTVLCOPT:http-referer=https://option.invalid'
  ];
  const [optionsOnly] = parse(...metadata, 'https://media.invalid/stream');
  assert.deepEqual(optionsOnly.requestHeaders, {
    'User-Agent': 'Last option',
    Referer: 'https://option.invalid',
    Origin: 'https://attribute.invalid'
  });
  const [channel] = parse(
    ...metadata,
    'https://media.invalid/stream|user-agent=Pipe&http-referrer=https%3A%2F%2Fpipe.invalid|http-origin=https%3A%2F%2Forigin.invalid&X-Token=first&x-token=last'
  );
  assert.deepEqual(channel.requestHeaders, {
    'User-Agent': 'Pipe',
    Referer: 'https://pipe.invalid',
    Origin: 'https://origin.invalid',
    'X-Token': 'last'
  });
});

test('Invalid headers cannot inject request lines or erase valid entry headers', () => {
  const [channel] = parse(
    '#EXTINF:-1 user-agent="Valid/1.0",Invalid headers',
    '#EXTVLCOPT:http-user-agent=Unsafe\u0000Agent',
    'https://media.invalid/stream|User-Agent=bad%0D%0AX-Evil%3Ayes&Referer=&Bad%20Name=bad&X-Nul=%00&X-Del=%7F&X-End=bad%0A&broken&=empty&X-Good=kept&X-Percent=literal%ZZ'
  );
  assert.equal(channel.streamUrl, 'https://media.invalid/stream');
  assert.deepEqual(channel.requestHeaders, {
    'User-Agent': 'Valid/1.0',
    'X-Good': 'kept',
    'X-Percent': 'literal%ZZ'
  });
});

test('A BOM, indentation and case-insensitive directives do not become stream URLs', () => {
  const channels = structuredClone(iptv().parseM3u(
    '\uFEFF#EXTM3U\r\n  #extinf:-1,Indented\r\n  #extvlcopt:http-origin=https://portal.invalid\r\n  # Comment\r\n  https://media.invalid/stream  \r\n',
    'fixture'
  ));
  assert.equal(channels.length, 1);
  assert.equal(channels[0].streamUrl, 'https://media.invalid/stream');
  assert.deepEqual(channels[0].requestHeaders, { Origin: 'https://portal.invalid' });
});

test('Provider order, metadata and first duplicate ownership survive header parsing', () => {
  const metadata = '#EXTINF:-1 tvg-id="shared" tvg-name="Zulu HD" tvg-chno="30" group-title="Zulu" tvg-logo="https://img.invalid/logo.png" catchup="append" catchup-days="3" catchup-source="?utc={utc}" tvg-language="Dutch" tvg-country="NL" quality="FHD",Fallback';
  const channels = parse(
    `${metadata} http-user-agent="This is title text"`,
    '#EXTVLCOPT:http-user-agent=First/1.0',
    'https://media.invalid/30.ts|Origin=https%3A%2F%2Fportal.invalid',
    '#EXTINF:-1 tvg-id="shared" group-title="Changed",Duplicate',
    '#EXTVLCOPT:http-user-agent=Duplicate/1.0',
    'https://media.invalid/30.ts|Origin=https%3A%2F%2Fother.invalid',
    '#EXTINF:-1 group-title="Alpha" tvg-chno="1",Alpha SD',
    'https://media.invalid/1.ts',
    '#EXTINF:-1 group-title="Zulu" tvg-chno="20",Middle HD',
    'https://media.invalid/20.ts'
  );
  assert.deepEqual(channels.map(({ name }) => name), ['Zulu HD', 'Alpha SD', 'Middle HD']);
  assert.deepEqual(channels.map(({ number }) => number), ['30', '1', '20']);
  const [plain] = parse(metadata, 'https://media.invalid/30.ts');
  const { requestHeaders, ...channel } = channels[0];
  assert.deepEqual(channel, plain);
  assert.deepEqual(requestHeaders, { 'User-Agent': 'First/1.0', Origin: 'https://portal.invalid' });
  assert.equal(channels[1].requestHeaders, undefined);
});

test('Abandoned entries, divider rows and empty URLs cannot leak headers', () => {
  const channels = parse(
    '#EXTINF:-1,Abandoned',
    '#EXTVLCOPT:http-user-agent=Abandoned',
    '#EXTINF:-1,---- SPORTS ----',
    '#EXTVLCOPT:http-referrer=https://divider.invalid',
    'https://media.invalid/divider',
    '#EXTVLCOPT:http-origin=https://orphan.invalid',
    '#EXTINF:-1,Missing URL',
    '|User-Agent=Missing',
    '#EXTINF:-1,Clean',
    'https://media.invalid/clean',
    '#EXTINF:-1,Trailing metadata',
    '#EXTVLCOPT:http-user-agent=Trailing'
  );
  assert.equal(channels.length, 1);
  assert.equal(channels[0].name, 'Clean');
  assert.equal(channels[0].requestHeaders, undefined);
});

test('Snapshots retain playlist/group order and do not fetch EPG while carrying headers', async () => {
  const requests = [];
  const playlists = ['z-provider', 'a-provider'].map((id) => ({
    id, name: id, enabled: true, m3uUrl: `https://provider.invalid/${id}.m3u`, epgUrl: `https://provider.invalid/${id}.xml`
  }));
  const module = iptv(async (url) => {
    requests.push(url);
    assert.ok(url.endsWith('.m3u'), 'The channel snapshot must not request a guide');
    if (url.includes('z-provider')) await new Promise((resolve) => setImmediate(resolve));
    return '#EXTM3U\n#EXTINF:-1 tvg-id="shared" tvg-chno="20" group-title="Zulu",First\n#EXTVLCOPT:http-origin=https://portal.invalid\nhttps://media.invalid/20.ts\n#EXTINF:-1 tvg-id="shared" tvg-chno="1" group-title="Alpha",Second\nhttps://media.invalid/1.ts';
  });
  const snapshot = structuredClone(await module.loadIptvSnapshot(playlists));
  assert.deepEqual(requests, playlists.map(({ m3uUrl }) => m3uUrl));
  assert.deepEqual(snapshot.channels.map(({ id }) => id.split(':')[0]), ['z-provider', 'z-provider', 'a-provider', 'a-provider']);
  assert.deepEqual(snapshot.channels.map(({ number }) => number), ['20', '1', '20', '1']);
  assert.deepEqual(Object.keys(snapshot.grouped), ['Zulu', 'Alpha']);
  assert.deepEqual(snapshot.channels.map(({ tvgId }) => tvgId), ['shared', 'shared', 'shared', 'shared']);
  assert.deepEqual(snapshot.grouped.Zulu.map(({ requestHeaders }) => requestHeaders), [
    { Origin: 'https://portal.invalid' }, { Origin: 'https://portal.invalid' }
  ]);
  assert.deepEqual(snapshot.nowNext, {});
  assert.deepEqual(snapshot.playlistWarnings, []);
});
