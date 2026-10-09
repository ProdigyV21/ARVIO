const { test } = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const vtt = 'WEBVTT\n\n00:00:01.000 --> 00:00:05.000\nA subtitle\n';

test('subtitle relay responses normalize BOM, CRLF and SRT without changing VTT markup', () => {
  const { normalizeSubtitleText } = load('lib/playerSubtitles.ts');
  assert.equal(normalizeSubtitleText('\uFEFF  ' + vtt.replace(/\n/g, '\r\n')), vtt);
  assert.equal(normalizeSubtitleText('1\n00:00:01,000 --> 00:00:05,000\n<b>Hello</b>'),
    'WEBVTT\n\n1\n00:00:01.000 --> 00:00:05.000\n<b>Hello</b>\n');
  for (const text of ['', 'WEBVTT', '<html>Unavailable</html>', '{"error":"denied"}']) {
    assert.throws(() => normalizeSubtitleText(text), /Invalid subtitle/);
  }
});

test('subtitle fetch preserves cancellation, omits credentials and rejects failures', async () => {
  const controller = new AbortController();
  let options;
  const { fetchSubtitleText } = load('lib/playerSubtitles.ts', {}, {
    fetch: async (_url, init) => { options = init; return new Response(vtt); }
  });
  assert.equal(await fetchSubtitleText('https://relay.example/subtitle', controller.signal), vtt);
  assert.equal(options.signal, controller.signal);
  assert.equal(options.credentials, 'omit');
  const fail = load('lib/playerSubtitles.ts', {}, { fetch: async () => new Response('Denied', { status: 403 }) });
  await assert.rejects(fail.fetchSubtitleText('https://relay.example', controller.signal), /request failed/);
});

test('subtitle fetch bounds both declared and streamed body sizes', async () => {
  for (const headers of [{ 'content-length': String(3 * 1024 * 1024) }, {}]) {
    const { fetchSubtitleText } = load('lib/playerSubtitles.ts', {}, {
      fetch: async () => new Response('x'.repeat(2 * 1024 * 1024 + 1), { headers })
    });
    await assert.rejects(fetchSubtitleText('https://relay.example', new AbortController().signal), /too large/);
  }
});

test('subtitle timing changes are relative to original cues, including resetting to zero', () => {
  const { positionSubtitleCues } = load('lib/playerSubtitles.ts');
  const cue = { startTime: 10, endTime: 20 };
  const track = { cues: [cue] };
  positionSubtitleCues(track, 84, 1000);
  assert.equal(cue.startTime, 11);
  positionSubtitleCues(track, 68, 2000);
  assert.equal(cue.startTime, 12);
  assert.equal(cue.endTime, 22);
  assert.equal(cue.line, 68);
  positionSubtitleCues(track, 84, 0);
  assert.equal(cue.startTime, 10);
  assert.equal(cue.endTime, 20);
  assert.equal(cue.snapToLines, false);
});

function fixture(fetcher) {
  const native = { mode: 'showing' };
  const tracks = Object.assign(new EventTarget(), { 0: native, length: 1, *[Symbol.iterator]() { yield* Array.from({ length: this.length }, (_, i) => this[i]); } });
  const video = Object.assign(new EventTarget(), { textTracks: tracks, children: [], appendChild(element) {
    this.children.push(element); tracks[tracks.length++] = element.track; tracks.dispatchEvent(new Event('addtrack'));
  } });
  const revoked = [];
  const states = [];
  const created = [];
  const { attachExternalSubtitle } = load('lib/playerSubtitles.ts', {}, {
    fetch: fetcher || (async () => new Response(vtt)), Blob,
    window: { setTimeout, clearTimeout },
    URL: { createObjectURL: () => 'blob:local/subtitle', revokeObjectURL: url => revoked.push(url) },
    document: { createElement: () => {
      const el = Object.assign(new EventTarget(), { track: { mode: 'disabled', cues: [{ text: 'A subtitle' }] },
        remove() { video.children = video.children.filter(child => child !== el); } });
      created.push(el); return el;
    } }
  });
  return { attach: subtitle => attachExternalSubtitle(video, subtitle, state => states.push(state)), native, states, video, revoked, created };
}
const flush = async () => { for (let i = 0; i < 20; i++) await Promise.resolve(); };

test('selected subtitle uses its own track, not the native track index, and cleans up', async () => {
  const f = fixture();
  const stop = f.attach({ url: 'https://relay.example/subtitle', lang: 'nl' });
  await flush();
  assert.equal(f.created.length, 1);
  const el = f.created[0];
  assert.equal(el.src, 'blob:local/subtitle');
  assert.equal(el.track.mode, 'showing');
  assert.equal(f.native.mode, 'disabled');
  el.dispatchEvent(new Event('load'));
  assert.equal(f.states.at(-1).track, el.track);
  assert.equal(f.states.at(-1).status, 'ready');
  stop();
  assert.equal(el.track.mode, 'disabled');
  assert.equal(f.video.children.length, 0);
  assert.deepEqual(f.revoked, ['blob:local/subtitle']);
});

test('turning subtitles off downloads nothing and disables native captions', () => {
  const f = fixture(() => { throw new Error('Should not fetch'); });
  const stop = f.attach(null);
  assert.equal(f.native.mode, 'disabled');
  assert.equal(f.states.at(-1).status, 'off');
  stop();
});

test('stale subtitle downloads cannot reattach after selection changes or closing', async () => {
  let finish;
  const f = fixture(() => new Promise(resolve => { finish = resolve; }));
  const stop = f.attach({ url: 'https://relay.example/subtitle' });
  stop();
  finish(new Response(vtt));
  await flush();
  assert.equal(f.created.length, 0);
  assert.equal(f.states.length, 1);
});

test('a parsed empty track reports failure instead of silently staying selected', async () => {
  const f = fixture();
  const stop = f.attach({ url: 'https://relay.example/subtitle' });
  await flush();
  f.created[0].track.cues = [];
  f.created[0].dispatchEvent(new Event('load'));
  assert.equal(f.states.at(-1).status, 'failed');
  assert.equal(f.created[0].track.mode, 'disabled');
  stop();
});
