const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const caps = { mse: true, nativeHls: false, h264: true, hevc: false, hevc10: false,
  dolbyVision: false, av1: false, vp9: false, aac: true, ac3: false, eac3: false, opus: false, flac: false };
const jf = { id: 'jf', type: 'jellyfin', name: 'Library', url: 'https://media.example/jellyfin', token: 'secret & token', userId: 'user', enabled: true };
const plex = { ...jf, id: 'plex', type: 'plex', url: 'https://plex.example' };
const source = (id = 'version-b', overrides = {}) => ({
  Id: id, Container: 'mp4', SupportsDirectPlay: true, SupportsDirectStream: true, SupportsTranscoding: true,
  MediaStreams: [{ Type: 'Video', Codec: 'h264', Width: 1920, Height: 1080, Level: 40, BitDepth: 8, VideoRange: 'SDR' },
    { Type: 'Audio', Codec: 'aac', Index: 1, IsDefault: true }], ...overrides
});
const stream = (server = jf) => ({ source: 'Library 1080p', addonName: 'Library', addonId: 'home-server',
  url: `${server.url}/Videos/movie/stream.mp4?MediaSourceId=version-b`,
  media: { container: 'mp4', videoCodec: 'h264', audioCodec: 'aac' },
  homeServer: { serverId: server.id, itemId: 'movie', mediaSourceId: 'version-b', mediaIndex: 1 } });
const playbackInfo = (selected = source()) => ({ PlaySessionId: 'play-session', MediaSources: [source('version-a'), selected] });
const plain = (value) => JSON.parse(JSON.stringify(value));
const window = { location: { origin: 'https://app.example' } };
function harness(respond, capabilities = caps, extra = {}) {
  const calls = [];
  const proxy = (url, headers) => {
    const proxy = new URL('/api/proxy', window.location.origin);
    proxy.searchParams.set('url', url);
    if (headers) proxy.searchParams.set('headers', btoa(JSON.stringify(headers)));
    return proxy.toString();
  };
  const request = async (kind, raw, init = {}) => {
    const proxy = new URL(raw);
    assert.equal(proxy.origin, window.location.origin);
    assert.equal(proxy.pathname, '/api/proxy');
    const url = new URL(proxy.searchParams.get('url'));
    assert.doesNotMatch(url.pathname, /\.(m3u8|mp4|mkv|ts)$/i, 'only metadata may use proxy');
    const call = { kind, url, init, body: init.body ? JSON.parse(init.body) : undefined,
      headers: proxy.searchParams.has('headers') ? JSON.parse(atob(proxy.searchParams.get('headers'))) : {} };
    calls.push(call);
    return respond(call, calls);
  };
  const http = { proxiedUrl: proxy, jsonRequest: (url, init) => request('json', url, init), textRequest: (url, init) => request('text', url, init) };
  const homeserver = load('lib/homeserver.ts', { './http': http }, { window });
  const playback = load('lib/homeServerPlayback.ts', { './http': http, './homeserver': homeserver,
    './capabilities': { getMediaCapabilities: () => capabilities }, ...extra }, { window });
  return { ...playback, homeserver, calls };
}

test('device profile advertises only detected native codecs and a decodable HLS target', () => {
  const h = harness(() => {});
  const profile = plain(h.buildHomeServerDeviceProfile(caps));
  assert.deepEqual(profile.DirectPlayProfiles, [{ Type: 'Video', Container: 'mp4,m4v', VideoCodec: 'h264', AudioCodec: 'aac' }]);
  assert.equal(profile.TranscodingProfiles[0].VideoCodec, 'h264');
  assert.doesNotMatch(JSON.stringify(profile), /dts|truehd|hevc|av1|mkv/);
  const none = h.buildHomeServerDeviceProfile(Object.fromEntries(Object.keys(caps).map((key) => [key, false])));
  assert.equal(none.DirectPlayProfiles.length, 0);
  assert.equal(none.TranscodingProfiles.length, 0);
  const hevc = h.buildHomeServerDeviceProfile({ ...caps, hevc: true, hevc10: false });
  assert.match(hevc.DirectPlayProfiles[0].VideoCodec, /hevc/);
  assert.equal(hevc.CodecProfiles.find((p) => p.Codec === 'hevc').Conditions[0].Value, '8');
});

for (const type of ['jellyfin', 'emby']) test(`${type} sends PlaybackInfo profile and keeps exact source, token, base path and session`, async () => {
  const server = { ...jf, type };
  const h = harness(() => playbackInfo());
  const input = stream(server);
  const controller = new AbortController();
  const result = await h.prepareHomeServerPlayback(input, { homeServers: [server] }, { startTime: 120, signal: controller.signal });
  assert.equal(h.calls.length, 1);
  assert.equal(h.calls[0].init.signal, controller.signal);
  assert.equal(h.calls[0].body.MediaSourceId, 'version-b');
  assert.equal(h.calls[0].body.UserId, 'user');
  assert.equal(h.calls[0].body.StartTimeTicks, 1200000000);
  assert.equal(h.calls[0].body.AutoOpenLiveStream, false);
  assert.equal(h.calls[0].body.DeviceProfile.TranscodingProfiles[0].AudioCodec, 'aac');
  assert.equal(h.calls[0].body.DeviceProfile.CodecProfiles[0].Conditions.find((condition) => condition.Value === 'SDR').Property,
    type === 'jellyfin' ? 'VideoRangeType' : 'VideoRange');
  const url = new URL(result.url);
  assert.equal(url.origin, 'https://media.example');
  assert.equal(url.pathname, '/jellyfin/Videos/movie/stream.mp4');
  assert.equal(url.searchParams.get('MediaSourceId'), 'version-b');
  assert.equal(url.searchParams.get('api_key'), server.token);
  assert.equal(url.searchParams.get('PlaySessionId'), 'play-session');
  assert.equal(result.playbackSession.startOffset, 0);
  assert.equal(result.transport, 'file');
  assert.equal(result.originalUrl, input.url);
  assert.equal(result.homeServer, input.homeServer);
  assert.equal(input.playbackSession, undefined);
});

test('JF DirectStreamUrl is preferred over transcode when the browser can decode the source', async () => {
  const h = harness(() => playbackInfo(source('version-b', { SupportsDirectPlay: false,
    DirectStreamUrl: '/jellyfin/Videos/movie/stream.mp4?Static=true', TranscodingUrl: '/Videos/movie/master.m3u8' })));
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  assert.equal(result.transport, 'file');
  assert.equal(new URL(result.url).pathname, '/jellyfin/Videos/movie/stream.mp4');
  await h.reportHomeServerPlayback(result, jf, 'start');
  assert.equal(h.calls[1].body.PlayMethod, 'DirectStream');
});

test('JF negotiates incompatible MKV HEVC/TrueHD to HLS, preserving version and offset', async () => {
  const selected = source('version-b', { Container: 'mkv', SupportsDirectPlay: false, SupportsDirectStream: false,
    TranscodingUrl: '/Videos/movie/master.m3u8?VideoCodec=h264&AudioCodec=aac', TranscodingSubProtocol: 'hls',
    MediaStreams: [{ Type: 'Video', Codec: 'hevc', BitDepth: 10, VideoRangeType: 'DOVI' }, { Type: 'Audio', Codec: 'truehd' }] });
  const h = harness(() => playbackInfo(selected));
  const result = await h.prepareHomeServerPlayback(stream(), [jf], { startTime: 42.5 });
  const url = new URL(result.url);
  assert.equal(url.origin, 'https://media.example');
  assert.equal(url.pathname, '/jellyfin/Videos/movie/master.m3u8');
  assert.equal(url.searchParams.get('StartTimeTicks'), '425000000');
  assert.equal(url.searchParams.get('MediaSourceId'), 'version-b');
  assert.equal(result.playbackSession.transcoding, true);
  assert.equal(result.playbackSession.startOffset, 42.5);
  assert.equal(result.media.videoCodec, 'h264');
  assert.equal(result.media.hdr, 'SDR');
});

test('forced JF fallback disables direct play and stream copy without changing version', async () => {
  const h = harness(() => playbackInfo(source('version-b', { TranscodingUrl: '/Videos/movie/master.m3u8?VideoCodec=h264&AudioCodec=aac' })));
  const input = stream(); input.originalUrl = input.url;
  const result = await h.prepareHomeServerPlayback(input, jf, { forceTranscode: true });
  for (const name of ['EnableDirectPlay', 'EnableDirectStream', 'AllowVideoStreamCopy', 'AllowAudioStreamCopy']) assert.equal(h.calls[0].body[name], false);
  assert.equal(result.transport, 'hls');
  assert.equal(new URL(result.url).searchParams.get('MediaSourceId'), input.homeServer.mediaSourceId);
  assert.equal(result.originalUrl, input.originalUrl);
});

test('missing JF version fails closed and reports the abandoned session stopped', async () => {
  const h = harness(({ kind }) => kind === 'json' ? { PlaySessionId: 'abandoned', MediaSources: [source('version-a')] } : '');
  await assert.rejects(h.prepareHomeServerPlayback(stream(), jf), /version is no longer available/);
  assert.equal(h.calls.length, 2);
  assert.equal(h.calls[1].url.pathname, '/jellyfin/Sessions/Playing/Stopped');
  assert.equal(h.calls[1].body.PlaySessionId, 'abandoned');
});

for (const raw of ['https://other.example/master.m3u8', '//other.example/master.m3u8', 'javascript:alert(1)', '../master.m3u8', '/api/proxy?url=secret']) {
  test(`JF refuses unsafe media URL ${raw}`, async () => {
    const h = harness(({ kind }) => kind === 'json' ? playbackInfo(source('version-b', {
      SupportsDirectPlay: false, SupportsDirectStream: false, TranscodingUrl: raw })) : '');
    await assert.rejects(h.prepareHomeServerPlayback(stream(), jf), /unsafe playback URL/);
    assert.ok(h.calls.every((call) => call.url.hostname === 'media.example'));
  });
}

test('JF rejects unsupported output and does not claim DTS/HEVC HLS is browser playable', async () => {
  for (const suffix of ['master.m3u8?VideoCodec=hevc&AudioCodec=dts', 'stream.mkv']) {
    const h = harness(({ kind }) => kind === 'json' ? playbackInfo(source('version-b', {
      SupportsDirectPlay: false, SupportsDirectStream: false, TranscodingUrl: `/Videos/movie/${suffix}` })) : '');
    await assert.rejects(h.prepareHomeServerPlayback(stream(), jf), /profile|compatible HLS/);
  }
});

test('cancellation during PlaybackInfo never returns a stale stream and cleans up a late session', async () => {
  const controller = new AbortController();
  const h = harness(({ kind }) => {
    if (kind === 'json') { controller.abort(new Error('cancelled')); return playbackInfo(); }
    return '';
  });
  await assert.rejects(h.prepareHomeServerPlayback(stream(), jf, { signal: controller.signal }), /cancelled/);
  assert.equal(h.calls[1].url.pathname, '/jellyfin/Sessions/Playing/Stopped');
  assert.equal(h.calls[1].init.signal, undefined, 'cleanup must not inherit the cancelled signal');
});

test('pre-aborted preparation and reports do not send requests or swallow cancellation', async () => {
  const h = harness(() => { throw new Error('unexpected request'); });
  const signal = AbortSignal.abort(new Error('cancelled'));
  await assert.rejects(h.prepareHomeServerPlayback(stream(), jf, { signal }), /cancelled/);
  await assert.rejects(h.reportHomeServerPlayback(stream(), jf, 'stop', { signal }), /cancelled/);
  assert.equal(h.calls.length, 0);
});

test('JF auth cancellation is not converted into fallback authentication or cached', async () => {
  const controller = new AbortController();
  let cancelled = false;
  const h = harness(() => { if (!cancelled) { cancelled = true; controller.abort(new Error('cancel auth')); } return { Id: 'user' }; });
  const server = { ...jf, userId: undefined };
  await assert.rejects(h.homeserver.ensureHomeServerSession(server, controller.signal), /cancel auth/);
  assert.equal(h.calls.length, 1);
  await h.homeserver.ensureHomeServerSession(server);
  assert.equal(h.calls.length, 2);
});

test('cached JF credentials cannot survive changing server URL/token under the same config ID', async () => {
  const h = harness(() => {});
  assert.equal((await h.homeserver.ensureHomeServerSession(jf)).token, jf.token);
  assert.equal((await h.homeserver.ensureHomeServerSession({ ...jf, token: 'new-token' })).token, 'new-token');
});

const plexMetadata = () => ({ MediaContainer: { Metadata: [{ ratingKey: 'movie', title: 'Fixture', year: 2020, Media: [
  { id: 10, container: 'mp4', videoCodec: 'h264', audioCodec: 'aac', Part: [{ id: 100, key: '/library/parts/100/file.mp4' }] },
  { id: 20, container: 'mkv', videoCodec: 'hevc', audioCodec: 'truehd', Part: [
    { id: 200, key: '/library/parts/200/file.mkv' },
    { id: 201, key: '/library/parts/201/file.mkv', Stream: [{ streamType: 1, codec: 'hevc', DOVIPresent: 1, bitDepth: 10 }, { streamType: 2, codec: 'truehd', selected: 1 }] }
  ] }
] }] } });
const plexStream = () => ({ ...stream(plex), url: `${plex.url}/library/parts/201/file.mkv?X-Plex-Token=fake`,
  homeServer: { serverId: plex.id, itemId: 'movie', mediaSourceId: '20', mediaIndex: 1, partIndex: 1 } });
const plexDecision = (code = 1001) => ({ MediaContainer: { generalDecisionCode: code, directPlayDecisionCode: 3000, transcodeDecisionCode: code,
  Metadata: [{ ratingKey: 'movie', Media: [{ id: 20, Part: [{ id: 201, decision: 'transcode', Stream: [{ streamType: 1, codec: 'h264', decision: 'transcode' }, { streamType: 2, codec: 'aac', decision: 'transcode' }] }] }] }] } });

test('Plex decision and HLS start keep the second media version and second part', async () => {
  const h = harness(({ url }) => url.pathname.endsWith('/decision') ? plexDecision() : plexMetadata());
  const result = await h.prepareHomeServerPlayback(plexStream(), plex, { startTime: 22, forceTranscode: true });
  const decision = h.calls[1].url;
  const url = new URL(result.url);
  assert.equal(url.origin, 'https://plex.example');
  assert.equal(url.pathname, '/video/:/transcode/universal/start.m3u8');
  assert.equal(url.search, decision.search);
  assert.equal(url.searchParams.get('mediaIndex'), '1');
  assert.equal(url.searchParams.get('partIndex'), '1');
  assert.equal(url.searchParams.get('directPlay'), '0');
  assert.equal(url.searchParams.get('directStream'), '0');
  assert.equal(url.searchParams.get('directStreamAudio'), '0');
  assert.equal(url.searchParams.get('offset'), '22');
  assert.equal(result.playbackSession.startOffset, 22);
  assert.equal(result.playbackSession.mediaSourceId, '20');
  assert.equal(result.media.videoCodec, 'h264');
});

test('Plex direct play requires both browser compatibility and a positive decision', async () => {
  const metadata = plexMetadata();
  const selected = { ...plexStream(), url: `${plex.url}/library/parts/100/file.mp4`,
    homeServer: { serverId: plex.id, itemId: 'movie', mediaSourceId: '10', mediaIndex: 0, partIndex: 0 } };
  const h = harness(({ url }) => url.pathname.endsWith('/decision')
    ? { MediaContainer: { generalDecisionCode: 1000, directPlayDecisionCode: 1000 } } : metadata);
  const result = await h.prepareHomeServerPlayback(selected, plex);
  assert.equal(result.transport, 'file');
  assert.equal(new URL(result.url).pathname, '/library/parts/100/file.mp4');
  assert.equal(result.playbackSession.transcoding, false);
  assert.equal(result.playbackSession.startOffset, 0);
});

test('Plex H264/AAC MKV permits direct-stream HLS without advertising MKV direct play', async () => {
  const metadata = plexMetadata();
  const selected = metadata.MediaContainer.Metadata[0].Media[1];
  selected.videoCodec = 'h264'; selected.audioCodec = 'aac'; selected.Part[1].Stream = [];
  const h = harness(({ url }) => url.pathname.endsWith('/decision') ? plexDecision() : metadata);
  await h.prepareHomeServerPlayback(plexStream(), plex);
  assert.equal(h.calls[1].url.searchParams.get('directPlay'), '0');
  assert.equal(h.calls[1].url.searchParams.get('directStream'), '1');
  assert.equal(h.calls[1].url.searchParams.get('directStreamAudio'), '1');
});

test('Plex rejects re-ordered/deleted media and changed part instead of silently using version zero', async () => {
  for (const change of ['media', 'part']) {
    const metadata = plexMetadata();
    if (change === 'media') metadata.MediaContainer.Metadata[0].Media.reverse();
    else metadata.MediaContainer.Metadata[0].Media[1].Part.reverse();
    const h = harness(() => metadata);
    await assert.rejects(h.prepareHomeServerPlayback(plexStream(), plex), /version is no longer|part changed/);
    assert.equal(h.calls.length, 1);
  }
});

test('Plex denial does not return an optimistic untested transcode URL', async () => {
  const h = harness(({ url }) => url.pathname.endsWith('/decision') ? plexDecision(4000) : plexMetadata());
  await assert.rejects(h.prepareHomeServerPlayback(plexStream(), plex), /Plex refused/);
});

test('Plex cancellation after metadata prevents the decision request', async () => {
  const controller = new AbortController();
  const h = harness(() => { controller.abort(new Error('cancelled')); return plexMetadata(); });
  await assert.rejects(h.prepareHomeServerPlayback(plexStream(), plex, { signal: controller.signal }), /cancelled/);
  assert.equal(h.calls.length, 1);
});

test('session reporting sends absolute JF ticks and serializes stop after in-flight progress', async () => {
  let release;
  const h = harness(({ kind, url }) => {
    if (kind === 'json') return playbackInfo();
    if (url.pathname.endsWith('/Progress')) return new Promise((resolve) => { release = resolve; });
    return '';
  });
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  await h.reportHomeServerPlayback(result, jf, 'start', { positionSeconds: 11, paused: false });
  const pending = h.reportHomeServerPlayback(result, jf, 'progress', { positionSeconds: 12.5, paused: true });
  await new Promise(setImmediate);
  const stopping = h.reportHomeServerPlayback(result, jf, 'stop', { positionSeconds: 13 });
  await new Promise(setImmediate);
  assert.equal(h.calls.length, 3);
  release('');
  await Promise.all([pending, stopping]);
  assert.equal(h.calls[2].body.PositionTicks, 125000000);
  assert.equal(h.calls[2].body.IsPaused, true);
  assert.equal(h.calls[3].body.PositionTicks, 130000000);
  assert.equal(h.calls[3].url.pathname, '/jellyfin/Sessions/Playing/Stopped');
  assert.equal(h.calls[1].headers['X-Emby-Authorization'], h.calls[3].headers['X-Emby-Authorization']);
  await h.reportHomeServerPlayback(result, jf, 'progress');
  await h.reportHomeServerPlayback(result, jf, 'stop');
  assert.equal(h.calls.length, 4);
});

test('Plex reports milliseconds and explicitly stops transcode even when timeline reporting fails', async () => {
  const h = harness(({ url, kind }) => {
    if (kind === 'json') return url.pathname.endsWith('/decision') ? plexDecision() : plexMetadata();
    if (url.pathname === '/:/timeline') throw new Error('timeline unavailable');
    return '';
  });
  const result = await h.prepareHomeServerPlayback(plexStream(), plex);
  await assert.rejects(h.reportHomeServerPlayback(result, plex, 'stop', { positionSeconds: 42.5, durationSeconds: 90 }), /timeline unavailable/);
  assert.equal(h.calls[2].url.searchParams.get('time'), '42500');
  assert.equal(h.calls[2].url.searchParams.get('duration'), '90000');
  assert.equal(h.calls[2].url.searchParams.get('state'), 'stopped');
  assert.equal(h.calls[3].url.pathname, '/video/:/transcode/universal/stop');
  assert.equal(h.calls[3].url.searchParams.get('session'), result.playbackSession.sessionId);
});

test('discovery populates real JF metadata including selected audio and all source IDs', async () => {
  const selected = source('version-b', { DefaultAudioStreamIndex: 2, Container: 'mkv',
    MediaStreams: [{ Type: 'Video', Codec: 'hevc', Width: 3840, Height: 2160, DvProfile: 5 },
      { Type: 'Audio', Codec: 'aac', Index: 1 }, { Type: 'Audio', Codec: 'truehd', Index: 2 }] });
  const h = harness(({ url }) => url.pathname.endsWith('/PlaybackInfo') ? playbackInfo(selected)
    : { Items: [{ Id: 'movie', Name: 'Fixture', ProductionYear: 2020, ProviderIds: { Tmdb: '123' } }] });
  const sources = await h.homeserver.resolveHomeServerMovieSources([jf], { title: 'Fixture', year: 2020, tmdbId: 123 });
  assert.equal(sources.length, 2);
  const result = sources.find((s) => s.homeServer.mediaSourceId === 'version-b');
  assert.deepEqual(plain(result.media), { container: 'mkv', videoCodec: 'hevc', audioCodec: 'truehd', hdr: 'Dolby Vision' });
  assert.equal(result.homeServer.itemId, 'movie');
  assert.equal(result.homeServer.mediaIndex, 1);
  assert.equal(new URL(result.url).searchParams.get('api_key'), jf.token);
});

test('Plex discovery retains every media/part with provider codec and HDR metadata', async () => {
  const h = harness(() => plexMetadata());
  const sources = await h.homeserver.resolveHomeServerMovieSources([plex], { title: 'Fixture', year: 2020 });
  assert.equal(sources.length, 3);
  const result = sources.find((s) => s.homeServer.mediaIndex === 1 && s.homeServer.partIndex === 1);
  assert.equal(result.homeServer.mediaSourceId, '20');
  assert.equal(result.media.container, 'mkv');
  assert.equal(result.media.videoCodec, 'hevc');
  assert.equal(result.media.audioCodec, 'truehd');
  assert.equal(result.media.hdr, 'Dolby Vision');
});

test('non-home-server sources pass through and absent/disabled server configurations fail closed', async () => {
  const h = harness(() => {});
  const other = { source: 'Other', addonName: 'Other', url: 'https://other.example/video.mp4' };
  assert.equal(await h.prepareHomeServerPlayback(other, []), other);
  await h.reportHomeServerPlayback(other, [], 'stop');
  await assert.rejects(h.prepareHomeServerPlayback(stream(), []), /unavailable or disabled/);
  await assert.rejects(h.prepareHomeServerPlayback(stream(), { ...jf, enabled: false }), /unavailable or disabled/);
  assert.equal(h.calls.length, 0);
});

test('forced conversion removes case-variant copy/session/version parameters', async () => {
  const h = harness(() => playbackInfo(source('version-b', {
    TranscodingUrl: '/Videos/movie/master.m3u8?videoCodec=h264&audioCodec=aac&allowVideoStreamCopy=true&allowAudioStreamCopy=true&mediaSourceId=wrong&playSessionId=old&startTimeTicks=1'
  })));
  const result = await h.prepareHomeServerPlayback(stream(), jf, { forceTranscode: true, startTime: 2 });
  const params = [...new URL(result.url).searchParams];
  for (const [name, value] of [['allowvideostreamcopy', 'false'], ['allowaudiostreamcopy', 'false'],
    ['mediasourceid', 'version-b'], ['playsessionid', 'play-session'], ['starttimeticks', '20000000']]) {
    assert.deepEqual(params.filter(([key]) => key.toLowerCase() === name).map(([, value]) => value), [value]);
  }
});

test('provider media paths cannot silently switch the selected JF item', async () => {
  const h = harness(({ kind }) => kind === 'json' ? playbackInfo(source('version-b', {
    SupportsDirectPlay: false, SupportsDirectStream: false, TranscodingUrl: '/Videos/another-item/master.m3u8'
  })) : '');
  await assert.rejects(h.prepareHomeServerPlayback(stream(), jf), /different item/);
});

test('Plex validates decision media/part identities and output codecs', async () => {
  for (const change of ['item', 'media', 'part', 'codec', 'ignored conversion', 'mde denial']) {
    const decision = plexDecision();
    const item = decision.MediaContainer.Metadata[0];
    if (change === 'item') item.ratingKey = 'other-item';
    if (change === 'media') item.Media[0].id = 10;
    if (change === 'part') item.Media[0].Part[0].id = 200;
    if (change === 'codec') item.Media[0].Part[0].Stream[0].codec = 'hevc';
    if (change === 'ignored conversion') item.Media[0].Part[0].decision = 'directplay';
    if (change === 'mde denial') decision.MediaContainer.mdeDecisionCode = 2000;
    const h = harness(({ url }) => url.pathname.endsWith('/decision') ? decision : plexMetadata());
    await assert.rejects(h.prepareHomeServerPlayback(plexStream(), plex, { forceTranscode: true }), /different|outside|ignored|refused/);
  }
});

test('unsupported HDR and H264 high-10-bit sources never qualify for direct playback', async () => {
  for (const video of [{ Codec: 'hevc', BitDepth: 10, VideoRangeType: 'HDR10' }, { Codec: 'h264', Profile: 'High 10', BitDepth: 10 }]) {
    const selected = source('version-b', { TranscodingUrl: '/Videos/movie/master.m3u8?VideoCodec=h264&AudioCodec=aac',
      MediaStreams: [{ Type: 'Video', ...video }, { Type: 'Audio', Codec: 'aac' }] });
    const h = harness(() => playbackInfo(selected), { ...caps, hevc: true, hevc10: true, dolbyVision: true });
    assert.equal((await h.prepareHomeServerPlayback(stream(), jf)).transport, 'hls');
  }
});

test('no HLS output is advertised or requested when the browser lacks H264/AAC decoding', async () => {
  const h = harness(() => { throw new Error('unexpected request'); }, { ...caps, aac: false });
  await assert.rejects(h.prepareHomeServerPlayback(stream(), jf, { forceTranscode: true }), /cannot play/);
  assert.equal(h.calls.length, 0);
});

test('metadata uses default audio even when both source and stream indices are absent', () => {
  const h = harness(() => {});
  const result = h.homeserver.jellyfinSourceMedia(source('version-b', { MediaStreams: [
    { Type: 'Video', Codec: 'h264' }, { Type: 'Audio', Codec: 'aac' }, { Type: 'Audio', Codec: 'dts', IsDefault: true }
  ] }));
  assert.equal(result.audioCodec, 'dts');
});

test('failed stop can retry, while successful stop uses credentials captured before config changes', async () => {
  let fail = true;
  const h = harness(({ kind }) => {
    if (kind === 'json') return playbackInfo();
    if (fail) { fail = false; throw new Error('offline'); }
    return '';
  });
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  await assert.rejects(h.reportHomeServerPlayback(result, { ...jf, token: 'changed' }, 'stop'), /offline/);
  await h.reportHomeServerPlayback(result, { ...jf, url: 'https://other.example', token: 'changed' }, 'stop');
  assert.equal(h.calls[2].headers['X-Emby-Token'], jf.token);
  assert.equal(h.calls[2].url.hostname, 'media.example');
});

test('JF constructs a static DirectStream route when compatible source has no DirectStreamUrl', async () => {
  const h = harness(({ kind }) => kind === 'json' ? playbackInfo(source('version-b', { SupportsDirectPlay: false })) : '');
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  assert.equal(new URL(result.url).searchParams.get('Static'), 'true');
  await h.reportHomeServerPlayback(result, jf, 'start');
  assert.equal(h.calls[1].body.PlayMethod, 'DirectStream');
});

test('local snapshots send no requests and a later stop defaults to the latest position', async () => {
  const h = harness(({ kind }) => kind === 'json' ? playbackInfo() : '');
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  h.updateHomeServerPlaybackPosition(result, { positionSeconds: 125.5, durationSeconds: 300, paused: true });
  assert.equal(h.calls.length, 1);
  await h.reportHomeServerPlayback(result, [], 'stop');
  assert.equal(h.calls[1].body.PositionTicks, 1255000000);
  assert.equal(h.calls[1].body.IsPaused, true);
  assert.equal(h.calls[1].headers['X-Emby-Token'], jf.token);
});

test('snapshot fields merge and invalid media-element values do not erase valid progress', async () => {
  const h = harness(({ kind }) => kind === 'json' ? playbackInfo() : '');
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  h.updateHomeServerPlaybackPosition(result, { positionSeconds: 21, durationSeconds: 300, paused: false });
  h.updateHomeServerPlaybackPosition(result, { positionSeconds: Number.NaN, durationSeconds: Infinity });
  h.updateHomeServerPlaybackPosition(result, { paused: true });
  await h.reportHomeServerPlayback(result, jf, 'progress');
  assert.equal(h.calls[1].body.PositionTicks, 210000000);
  assert.equal(h.calls[1].body.IsPaused, true);
  await h.reportHomeServerPlayback(result, jf, 'stop', { positionSeconds: 22 });
  assert.equal(h.calls[2].body.PositionTicks, 220000000);
  assert.equal(h.calls[2].body.IsPaused, true);
});

test('snapshot preserves absolute Plex position and full duration for owner-only stop', async () => {
  const h = harness(({ kind, url }) => kind === 'json'
    ? url.pathname.endsWith('/decision') ? plexDecision() : plexMetadata() : '');
  const result = await h.prepareHomeServerPlayback(plexStream(), plex, { startTime: 120 });
  h.updateHomeServerPlaybackPosition(result, { positionSeconds: 130, durationSeconds: 300, paused: false });
  h.updateHomeServerPlaybackPosition(result, { durationSeconds: Number.NaN });
  h.updateHomeServerPlaybackPosition(result, { durationSeconds: 0 });
  assert.equal(h.calls.length, 2);
  await h.reportHomeServerPlayback(result, { homeServers: [] }, 'stop');
  assert.equal(h.calls[2].url.searchParams.get('time'), '130000');
  assert.equal(h.calls[2].url.searchParams.get('duration'), '300000');
  assert.equal(h.calls[3].url.pathname, '/video/:/transcode/universal/stop');
});

test('unmounted HLS preparation defaults to its requested start offset instead of zero', async () => {
  const h = harness(({ kind }) => kind === 'json' ? playbackInfo(source('version-b', {
    TranscodingUrl: '/Videos/movie/master.m3u8?VideoCodec=h264&AudioCodec=aac'
  })) : '');
  const result = await h.prepareHomeServerPlayback(stream(), jf, { forceTranscode: true, startTime: 75 });
  await h.reportHomeServerPlayback(result, jf, 'stop');
  assert.equal(h.calls[1].body.PositionTicks, 750000000);
});

test('concurrent and repeated session starts are deduplicated after successful acknowledgement', async () => {
  let release;
  const h = harness(({ kind }) => kind === 'json' ? playbackInfo() : new Promise((resolve) => { release = resolve; }));
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  const first = h.reportHomeServerPlayback(result, jf, 'start');
  const second = h.reportHomeServerPlayback(result, jf, 'start');
  await new Promise(setImmediate);
  assert.equal(h.calls.length, 2);
  release('');
  await Promise.all([first, second]);
  await h.reportHomeServerPlayback(result, jf, 'start');
  assert.equal(h.calls.length, 2);
});

test('a failed start is retryable and only a successful retry suppresses later starts', async () => {
  let fail = true;
  const h = harness(({ kind }) => {
    if (kind === 'json') return playbackInfo();
    if (fail) { fail = false; throw new Error('start unavailable'); }
    return '';
  });
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  await assert.rejects(h.reportHomeServerPlayback(result, jf, 'start'), /start unavailable/);
  await h.reportHomeServerPlayback(result, jf, 'start');
  await h.reportHomeServerPlayback(result, jf, 'start');
  assert.equal(h.calls.length, 3);
});

test('late snapshots and start calls cannot resurrect a stopped session', async () => {
  const h = harness(({ kind }) => kind === 'json' ? playbackInfo() : '');
  const result = await h.prepareHomeServerPlayback(stream(), jf);
  h.updateHomeServerPlaybackPosition(result, { positionSeconds: 80 });
  await h.reportHomeServerPlayback(result, jf, 'stop');
  h.updateHomeServerPlaybackPosition(result, { positionSeconds: 0 });
  await h.reportHomeServerPlayback(result, jf, 'start');
  await h.reportHomeServerPlayback(result, jf, 'stop');
  assert.equal(h.calls.length, 2);
  assert.equal(h.calls[1].body.PositionTicks, 800000000);
});

test('unknown stream snapshots perform no authentication or network requests', () => {
  const h = harness(() => { throw new Error('unexpected request'); });
  h.updateHomeServerPlaybackPosition(stream(), { positionSeconds: 12 });
  h.updateHomeServerPlaybackPosition({ ...stream(), playbackSession: { serverId: 'unknown', sessionId: 'unknown', itemId: 'movie', transcoding: false } }, { positionSeconds: 12 });
  assert.equal(h.calls.length, 0);
});
