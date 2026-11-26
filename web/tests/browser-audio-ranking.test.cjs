// Autoplay must start a release whose audio this browser decodes, instead of
// a UHD Blu-ray rip with unnamed (AC-3/DTS/TrueHD) audio that plays silently
// in Chromium and then hops. Also: Trakt answers early pauses with 422.
const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');

const debrid = { parseDebridStream: (url) => (String(url).includes('/torbox/') ? { provider: 'torbox' } : null), isUncachedDebridStream: () => false };
// Chrome on Windows: AAC/Opus/HEVC yes, AC-3/E-AC-3 no.
const caps = { mse: true, nativeHls: false, h264: true, hevc: true, hevc10: true, dolbyVision: false, av1: true, vp9: true, aac: true, ac3: false, eac3: false, opus: true, flac: true };
const CHROME = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/156.0.0.0 Safari/537.36';
const compat = load('lib/streamCompatibility.ts', { './capabilities': { getMediaCapabilities: () => caps }, './debrid': debrid }, { navigator: { userAgent: CHROME } });
const rank = load('lib/sourceRank.ts', { './debrid': debrid, './streamCompatibility': compat });

// Debrid rows from the Matrix list that reproduced the bug (resolve URLs have
// no file extension, so the container is unknown and the plan says "direct").
const row = (name, size, extra = {}) => ({ source: name, size, quality: /2160|4k|uhd/i.test(name) ? '4K' : '1080p', url: `https://torrentio.example/resolve/torbox/x/${encodeURIComponent(name)}`, behaviorHints: { cached: true }, ...extra });
const ruUhd = row('Матрица / The Matrix (1999) UHD Blu-ray 2160p | 4K | HDR | Remastered | D', '64.61 GB');
const bluray4k = row('The Matrix 1999 4K 2160p BluRay x265 10bit HDR', '35.28 GB');
const yts = row('The.Matrix.1999.2160p.4K.BluRay.x265.10bit.AAC5.1-[YTS.MX].mkv', '6.49 GB');
const tigole = row('The Matrix (1999) (2160p BluRay x265 HEVC 10bit HDR AAC 7.1 Tigole) [QxR]', '14.2 GB');
const ddp = row('The.Matrix.1999.2160p.BluRay.HDR.DDP5.1.x265-GalaxyUHD.mkv', '3.41 GB');
const web = row('The.Matrix.1999.1080p.WEB-DL.H264', '6 GB');

test('audio class reads the release name and measured metadata', () => {
  assert.equal(rank.browserAudioClass(yts), 'ok');
  assert.equal(rank.browserAudioClass(tigole), 'ok');
  assert.equal(rank.browserAudioClass(ddp), 'dolby');
  assert.equal(rank.browserAudioClass(row('Film 2160p TrueHD Atmos 7.1', '50 GB')), 'dolby');
  assert.equal(rank.browserAudioClass(row('Film 1080p AC3 5.1', '2 GB')), 'dolby');
  assert.equal(rank.browserAudioClass(row('Film 1080p AAC AC3 multi', '2 GB')), 'dolby');
  assert.equal(rank.browserAudioClass(ruUhd), 'unknown-disc');
  assert.equal(rank.browserAudioClass(bluray4k), 'unknown-disc');
  assert.equal(rank.browserAudioClass(web), 'unknown');
  assert.equal(rank.browserAudioClass({ ...bluray4k, media: { audioCodec: 'aac' } }), 'ok');
  assert.equal(rank.browserAudioClass({ ...yts, media: { audioCodec: 'eac3' } }), 'dolby');
});

test('Chrome autoplay order: known browser audio first, unnamed disc audio last', () => {
  const ordered = rank.groupSourcesForPlayback([ruUhd, bluray4k, ddp, web, yts, tigole]).here;
  const names = Array.from(ordered, (s) => s.source);
  assert.deepEqual(names.slice(0, 2).sort(), [tigole.source, yts.source].sort());
  assert.equal(names[2], web.source);
  // DD+ converts in the browser (remux) before an unnamed-audio disc rip is tried.
  assert.ok(names.indexOf(ddp.source) < names.indexOf(ruUhd.source));
  assert.ok(names.indexOf(ddp.source) < names.indexOf(bluray4k.source));
  assert.deepEqual(names.slice(-2).sort(), [bluray4k.source, ruUhd.source].sort());
});

test('playback tiers put silent-risk files behind safe ones', () => {
  assert.equal(rank.browserPlaybackTier(yts), 0);
  assert.equal(rank.browserPlaybackTier(web), 1);
  assert.equal(rank.browserPlaybackTier(ddp), 2);
  assert.equal(rank.browserPlaybackTier(ruUhd), 3);
});

test('external players keep the pure quality order', () => {
  assert.ok(rank.sourcePickerScore(ruUhd, 'external') > rank.sourcePickerScore(yts, 'external'));
});

function trakt(status, body) {
  const http = load('lib/http.ts', { './config': { config: {} } });
  const { TraktClient } = load('lib/trakt.ts', {
    './config': { config: { traktClientId: 'fixture-client', traktClientSecret: 'fixture-secret' }, hasTraktConfig: () => true },
    './storage': storage(),
    './http': { ...http, jsonRequest: async () => { throw new Error('Unexpected proxy request'); } }
  }, { fetch: async () => new Response(JSON.stringify(body), { status, headers: { 'content-type': 'application/json' } }), window: { location: { origin: 'https://web.example' } } });
  const client = new TraktClient();
  client.setProfile('first');
  client.setToken({ access_token: 'a', refresh_token: 'r', expires_at: Date.now() + 86_400_000 });
  return client;
}
const movie = { mediaType: 'movie', tmdbId: 603, progress: 0.05 };

test('Trakt progress_too_low (422) and duplicate (409) scrobbles are not failures', async () => {
  await trakt(422, { error: 'progress_too_low' }).scrobble('pause', movie);
  await trakt(409, { watched_at: 'x' }).scrobble('start', movie);
  await assert.rejects(trakt(500, { error: 'down' }).scrobble('pause', movie));
});
