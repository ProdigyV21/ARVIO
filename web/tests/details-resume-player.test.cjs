const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const plain = (value) => JSON.parse(JSON.stringify(value));

// ---------- Resume from any entry point ----------

const resume = load('lib/playerResume.ts');
const movie = { mediaType: 'movie', id: 7, title: 'Film' };
const show = { mediaType: 'tv', id: 9, title: 'Show' };

test('a movie opened from search finds its Continue Watching position: "Resume 1:12:04" + "Start over"', () => {
  const rail = [{ ...movie, progress: 60, resumePositionSeconds: 4324, durationSeconds: 7200, activityAt: 5 }];
  const merged = resume.resumeFromContinueWatching(movie, rail);
  assert.equal(merged.resumePositionSeconds, 4324);
  const points = resume.collectResumePoints(movie, { self: { ...movie, ...merged }, continueWatching: rail });
  const plan = resume.planPrimaryAction({ item: movie, points });
  assert.equal(plan.kind, 'resume');
  assert.equal(plan.label, 'Resume {value0}');
  assert.equal(plan.labelValues.value0, '1:12:04');
  assert.equal(plan.resumeSeconds, 4324);
  assert.equal(plan.startOverLabel, 'Start over');
});

test('movies under 3 % / a minute, at 90 % or marked watched just "Play"', () => {
  for (const entry of [{ progress: 1, resumePositionSeconds: 20, durationSeconds: 7200 }, { progress: 92, resumePositionSeconds: 6700, durationSeconds: 7200 }]) {
    const points = resume.collectResumePoints(movie, { continueWatching: [{ ...movie, ...entry }] });
    assert.equal(resume.planPrimaryAction({ item: movie, points }).kind, 'play');
  }
  const points = resume.collectResumePoints(movie, { continueWatching: [{ ...movie, progress: 40, resumePositionSeconds: 2000, durationSeconds: 5000 }] });
  assert.equal(resume.planPrimaryAction({ item: movie, points, isWatched: () => true }).kind, 'play');
});

test('a series resumes its in-progress episode, from the rail or the cloud history', () => {
  const rail = [{ ...show, seasonNumber: 2, episodeNumber: 3, progress: 40, resumePositionSeconds: 900, durationSeconds: 2400 }];
  const merged = resume.resumeFromContinueWatching(show, rail);
  assert.deepEqual(plain([merged.seasonNumber, merged.episodeNumber, merged.resumePositionSeconds]), [2, 3, 900]);
  const plan = resume.planPrimaryAction({ item: show, points: resume.collectResumePoints(show, { continueWatching: rail }) });
  assert.equal(plan.kind, 'resume');
  assert.deepEqual(plain(plan.labelValues), { value0: 2, value1: 3 });
  assert.equal(plan.resumeSeconds, 900);
  // Cloud history rows (seconds or legacy milliseconds) count too.
  const history = [{ media_type: 'tv', show_tmdb_id: 9, season: 1, episode: 5, progress: 0.5, position_seconds: 1_200_000, duration_seconds: 2400, updated_at: '2026-10-01T00:00:00Z' }];
  const fromHistory = resume.planPrimaryAction({ item: show, points: resume.collectResumePoints(show, { history }) });
  assert.deepEqual(plain([fromHistory.season, fromHistory.episode, fromHistory.resumeSeconds]), [1, 5, 1200]);
});

test('an "Up next" entry plays that episode from the start; nothing started plays the first unwatched aired episode', () => {
  const rail = [{ ...show, seasonNumber: 2, episodeNumber: 4, badge: 'Up Next', timeRemainingLabel: 'Up next', progress: 35 }];
  const merged = resume.resumeFromContinueWatching(show, rail);
  assert.equal(merged.resumePositionSeconds, undefined, 'Up next contributes only its episode');
  const upNext = resume.planPrimaryAction({ item: show, points: resume.collectResumePoints(show, { continueWatching: rail }) });
  assert.deepEqual(plain([upNext.kind, upNext.label, upNext.resumeSeconds]), ['episode', 'Play S{value0} E{value1}', 0]);
  const episodes = [{ seasonNumber: 0, episodeNumber: 1 }, { seasonNumber: 1, episodeNumber: 1 }, { seasonNumber: 1, episodeNumber: 2 }, { seasonNumber: 1, episodeNumber: 3, airDate: '2999-01-01' }];
  const watched = (s, e) => s === 1 && e === 1;
  assert.deepEqual(plain(resume.firstUnwatchedEpisode(episodes, watched)), { seasonNumber: 1, episodeNumber: 2 });
  assert.equal(resume.firstUnwatchedEpisode(episodes, () => false).seasonNumber, 1, 'specials are never the default start');
  const fresh = resume.planPrimaryAction({ item: show, points: [], firstUnwatched: { season: 1, episode: 1 } });
  assert.deepEqual(plain([fresh.kind, fresh.labelValues]), ['episode', { value0: 1, value1: 1 }]);
  assert.equal(resume.planPrimaryAction({ item: show, points: [] }).kind, 'choose');
});

test('an episode picked in details wins over history, resuming only its own progress', () => {
  const rail = [{ ...show, seasonNumber: 2, episodeNumber: 3, progress: 40, resumePositionSeconds: 900, durationSeconds: 2400 }];
  const points = resume.collectResumePoints(show, { continueWatching: rail });
  assert.equal(resume.planPrimaryAction({ item: show, points, selectedEpisode: { season: 1, episode: 1 } }).kind, 'episode');
  assert.equal(resume.planPrimaryAction({ item: show, points, selectedEpisode: { season: 2, episode: 3 } }).resumeSeconds, 900);
});

test('items that already carry an episode or progress are not overridden by the rail', () => {
  const rail = [{ ...show, seasonNumber: 2, episodeNumber: 3, progress: 40, resumePositionSeconds: 900 }];
  assert.equal(resume.resumeFromContinueWatching({ ...show, seasonNumber: 1, episodeNumber: 1 }, rail), null);
  assert.equal(resume.resumeFromContinueWatching({ ...movie, progress: 10 }, [{ ...movie, progress: 50, resumePositionSeconds: 10 }]), null);
  assert.equal(resume.resumeFromContinueWatching({ ...movie, id: 8 }, [{ ...movie, progress: 50, resumePositionSeconds: 3000 }]), null);
});

test('episode progress bars: Android thresholds (≥3 % or ≥1 min, below 90 %, not watched)', () => {
  const points = [
    { season: 1, episode: 1, percent: 50, positionSeconds: 1200, durationSeconds: 2400, upNext: false, activityAt: 3 },
    { season: 1, episode: 2, percent: 1, positionSeconds: 20, durationSeconds: 2400, upNext: false, activityAt: 2 },
    { season: 1, episode: 3, percent: 95, positionSeconds: 2300, durationSeconds: 2400, upNext: false, activityAt: 1 },
    { season: 1, episode: 4, percent: 40, positionSeconds: 900, durationSeconds: 2400, upNext: false, activityAt: 1 }
  ];
  const map = resume.episodeProgressMap(points, (s, e) => e === 4);
  assert.deepEqual([...map.keys()], ['1:1']);
});

test('the external-player prompt treats 90 % as finished (marked watched, not silently dropped)', () => {
  assert.equal(resume.isFinishedPercent(90), true);
  assert.equal(resume.isFinishedPercent(75), false);
});

// ---------- Details loaders: one request per key in flight ----------

test('concurrent details requests share one fetch', async () => {
  let calls = 0;
  let finish;
  const loaders = load('components/details/detailsLoaders.ts', { '@/lib/tmdb': {
    getDetails: () => { calls++; return calls === 1 ? new Promise((resolve) => { finish = resolve; }) : Promise.resolve({ id: 1396 }); },
    getSeasonEpisodes: async () => { calls++; return []; }
  } });
  const a = loaders.loadDetails({ mediaType: 'tv', id: 1396 });
  const b = loaders.loadDetails({ mediaType: 'tv', id: 1396 });
  const c = loaders.loadDetails({ mediaType: 'tv', id: 1396 });
  assert.equal(calls, 1);
  finish({ id: 1396 });
  assert.equal(await a, await b);
  assert.equal(await c, await a);
  await loaders.loadDetails({ mediaType: 'tv', id: 1396 });
  assert.equal(calls, 2, 'a finished request is not pinned: the tmdb cache owns reuse');
  await Promise.all([loaders.loadSeasonEpisodes(1396, 2), loaders.loadSeasonEpisodes(1396, 2)]);
  assert.equal(calls, 3);
});

// ---------- Details text ----------

test('reviews are plain text and dates are human', () => {
  const text = load('components/details/detailsText.ts');
  assert.equal(text.cleanReviewText('<p>Great <em>film</em></p><p>**Bold** and _calm_ [link](https://x.y) &amp; more</p>'), 'Great film\n\nBold and calm link & more');
  assert.match(text.formatDate('2024-02-27'), /2024/);
  assert.doesNotMatch(text.formatDate('2024-02-27'), /2024-02-27/);
  const meta = text.buildDetailMeta({ mediaType: 'movie', id: 1, title: 'x', releaseDate: '2024-02-27', status: 'Released', originalLanguage: 'en' }, false);
  assert.equal(meta.includes('Released'), false, 'the "Released" chip is gone');
  assert.equal(meta.some((entry) => /^\d{4}-\d{2}-\d{2}$/.test(entry)), false);
  const series = text.buildDetailMeta({ mediaType: 'tv', id: 1, title: 'x', status: 'Returning Series', lastAirDate: '2025-05-01' }, false);
  assert.ok(series.includes('Returning Series'));
  assert.ok(series.some((entry) => entry.startsWith('Last aired') && !entry.includes('2025-05-01')));
});

// ---------- Autoplay starts as soon as the best source passes ----------

const selection = load('lib/autoplaySelection.ts');
const isDebrid = () => true;

test('autoplay starts the moment the top candidate passes instead of waiting for the probe budget', async () => {
  const a = { url: 'https://h.example/a.mp4', addonId: 'x' };
  const b = { url: 'https://h.example/b.mp4', addonId: 'x' };
  const started = Date.now();
  const result = await selection.chooseAutoplaySource({
    candidates: () => [a, b], searching: () => false, isDebrid, verifyBudgetMs: 5000,
    verify: (head, _budget, _signal, onVerdict) => { onVerdict(a, true); return new Promise(() => {}); }
  });
  assert.equal(result.stream, a);
  assert.ok(Date.now() - started < 500, 'no wait for the slow probe of b or the 5 s budget');
});

test('a lower-ranked pass never jumps ahead of a pending top candidate', async () => {
  const a = { url: 'https://h.example/a.mp4' };
  const b = { url: 'https://h.example/b.mp4' };
  let report;
  const pending = selection.chooseAutoplaySource({
    candidates: () => [a, b], searching: () => false, isDebrid, verifyBudgetMs: 5000,
    verify: (_head, _budget, _signal, onVerdict) => { report = onVerdict; onVerdict(b, true); return new Promise(() => {}); }
  });
  await new Promise((resolve) => setTimeout(resolve, 30));
  report(a, false);
  const result = await pending;
  assert.equal(result.stream, b);
  assert.equal(result.rejected, 1);
  assert.equal(selection.decidedCandidate([a, b], new Map([[b, true]])), undefined);
  assert.equal(selection.decidedCandidate([a, b], new Map([[a, null]])), a, 'inconclusive never demotes');
  assert.equal(selection.decidedCandidate([a, b], new Map([[a, false], [b, false]])), null);
});

// ---------- Remote / keyboard model ----------

const keys = load('lib/playerKeys.ts');
const state = (overrides = {}) => ({ controlsVisible: false, panelOpen: false, modalState: false, onSeekBar: false, focusInControls: false, ...overrides });

test('controls hidden: Left/Right seek 10 s, OK/Up/Down reveal the bar', () => {
  assert.deepEqual(plain(keys.playerKeyAction('ArrowLeft', state())), { type: 'seek', seconds: -10 });
  assert.deepEqual(plain(keys.playerKeyAction('ArrowRight', state())), { type: 'seek', seconds: 10 });
  for (const key of ['Enter', 'ArrowUp', 'ArrowDown']) assert.equal(keys.playerKeyAction(key, state()).type, 'show-controls');
  assert.equal(keys.playerKeyAction(' ', state()).type, 'toggle-play');
});

test('controls visible: arrows move focus between controls; the seek bar keeps Left/Right', () => {
  const visible = state({ controlsVisible: true, focusInControls: true });
  for (const [key, direction] of [['ArrowLeft', 'left'], ['ArrowRight', 'right'], ['ArrowUp', 'up'], ['ArrowDown', 'down']]) {
    assert.deepEqual(plain(keys.playerKeyAction(key, visible)), { type: 'move-focus', direction });
  }
  assert.equal(keys.playerKeyAction('Enter', visible).type, 'native');
  assert.equal(keys.playerKeyAction('ArrowRight', { ...visible, onSeekBar: true }).type, 'native');
  assert.equal(keys.playerKeyAction('ArrowUp', { ...visible, onSeekBar: true }).type, 'move-focus');
});

test('Back closes a panel, then hides the controls, then closes the player', () => {
  assert.equal(keys.playerKeyAction('Escape', state({ panelOpen: true, controlsVisible: true })).type, 'close-panel');
  assert.equal(keys.playerKeyAction('Escape', state({ controlsVisible: true })).type, 'hide-controls');
  assert.equal(keys.playerKeyAction('Escape', state()).type, 'close');
  assert.equal(keys.playerKeyAction('Escape', state({ controlsVisible: true, modalState: true })).type, 'close', 'error/end/loading screens close at once');
});

test('TV Back arrives as two synthetic Escapes: only the first one acts', () => {
  keys.resetEscapeClaim();
  const first = { key: 'Escape', isTrusted: false };
  const second = { key: 'Escape', isTrusted: false };
  assert.equal(keys.claimEscape(first, 1000), true);
  assert.equal(keys.claimEscape(first, 1001), true, 'every listener sees the same answer for one event');
  assert.equal(keys.claimEscape(second, 1002), false);
  assert.equal(keys.claimEscape({ key: 'Escape', isTrusted: false }, 2000), true, 'a later press acts again');
  assert.equal(keys.claimEscape({ key: 'Escape', isTrusted: true }, 2001), true, 'real keyboards are never deduplicated');
});

test('focus moves to the nearest control in the pressed direction', () => {
  const rect = (left, top) => ({ left, top, width: 40, height: 40 });
  const items = [{ item: 'play', rect: rect(0, 100) }, { item: 'back10', rect: rect(60, 100) }, { item: 'seekbar', rect: rect(200, 40) }, { item: 'subtitles', rect: rect(600, 100) }];
  assert.equal(keys.nearestInDirection(rect(0, 100), items.filter((i) => i.item !== 'play'), 'right'), 'back10');
  assert.equal(keys.nearestInDirection(rect(60, 100), items, 'up'), 'seekbar');
  assert.equal(keys.nearestInDirection(rect(0, 100), items, 'left'), null);
});

// ---------- Subtitles ----------

const subs = load('lib/playerSubtitles.ts', { './languages': load('lib/languages.ts') });
function encode(text, encoding) {
  const decoder = new TextDecoder(encoding);
  const table = new Map();
  for (let byte = 0; byte < 256; byte++) table.set(decoder.decode(new Uint8Array([byte])), byte);
  return Uint8Array.from([...text].map((ch) => table.get(ch)));
}
const cue = (text) => `1\r\n00:00:01,000 --> 00:00:02,000\r\n${text}\r\n`;

test('subtitle bytes: BOM and UTF-8 first, then the code page that yields real words', () => {
  assert.equal(subs.decodeSubtitleBytes(new Uint8Array([0xef, 0xbb, 0xbf, 0x68, 0xc3, 0xa9])).text, 'hé');
  assert.equal(subs.decodeSubtitleBytes(new TextEncoder().encode('Łódź')).encoding, 'utf-8');
  const samples = {
    'windows-1250': 'Przepraszam, nie wiem gdzie jest łódź. Děkuji, že jsi přišel.',
    'windows-1251': 'Привет, как дела? Я не знаю, где он.',
    'windows-1256': 'مرحبا، كيف حالك؟ لا أعرف أين هو.',
    'windows-1252': 'Où est la bibliothèque? Ça va très bien, merci. ¿Qué pasó?'
  };
  for (const [encoding, text] of Object.entries(samples)) {
    const decoded = subs.decodeSubtitleBytes(encode(cue(text), encoding));
    assert.equal(decoded.encoding, encoding);
    assert.ok(decoded.text.includes(text));
    assert.ok(!decoded.text.includes('�'));
  }
  assert.equal(subs.charsetFromContentType('text/plain; charset=windows-1251'), 'windows-1251');
});

test('ASS/SSA subtitles become WebVTT with styling dropped', () => {
  const ass = '[Script Info]\nTitle: x\n\n[V4+ Styles]\nStyle: Default,Arial\n\n[Events]\nFormat: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text\n'
    + 'Dialogue: 0,0:00:05.50,0:00:07.00,Default,,0,0,0,,Second {\\i1}line{\\i0}, with comma\n'
    + 'Dialogue: 0,0:00:01.00,0:00:04.00,Default,,0,0,0,,Hello\\Nworld <3\n'
    + 'Dialogue: 0,0:00:08.00,0:00:09.00,Default,,0,0,0,,{\\p1}m 0 0 l 100 0{\\p0}\n';
  const vtt = subs.normalizeSubtitleText(ass);
  assert.equal(vtt, 'WEBVTT\n\n00:00:01.000 --> 00:00:04.000\nHello\nworld &lt;3\n\n00:00:05.500 --> 00:00:07.000\nSecond line, with comma\n');
  assert.equal(subs.normalizeSubtitleText('1\n00:00:01,000 --> 00:00:02,000\n{\\an8}Top'), 'WEBVTT\n\n1\n00:00:01.000 --> 00:00:02.000\nTop\n');
});

test('subtitle languages are readable and grouped: preferred first, best release match, SDH/forced flagged', () => {
  assert.equal(subs.subtitleLanguageLabel('pob'), 'Portuguese (Brazil)');
  assert.equal(subs.subtitleLanguageLabel('eng'), 'English');
  assert.equal(subs.subtitleLanguageLabel('dut'), 'Dutch');
  assert.equal(subs.subtitleLanguageLabel('spl'), 'Spanish (Latin America)');
  const list = [
    { lang: 'eng', label: 'Other.Movie.720p.HDTV' },
    { lang: 'dut', label: 'Dutch' },
    { lang: 'eng', label: 'Movie.2024.1080p.WEB-DL.GROUP.SDH', id: 'x' },
    { lang: 'eng', label: 'Movie.2024.1080p.WEB-DL.GROUP' },
    { lang: 'eng', label: 'Movie forced', isForced: true },
    { lang: 'pob', label: 'pob' }
  ];
  const groups = plain(subs.groupSubtitles(list, { preferred: 'Dutch', releaseName: 'Movie.2024.1080p.WEB-DL.GROUP.mkv' }));
  assert.deepEqual(groups.map((group) => group.language), ['Dutch', 'English', 'Portuguese (Brazil)']);
  const english = groups[1].choices;
  assert.deepEqual(english.map((choice) => choice.index), [3, 2, 0, 4]);
  assert.equal(english[1].hearingImpaired, true);
  assert.equal(english[3].forced, true);
  assert.equal(groups[0].choices[0].release, null, 'a bare language label is not a release name');
  assert.equal(plain(subs.groupSubtitles(list, { preferred: 'en' }))[0].language, 'English');
});

test('per-title subtitle delay: ±0.1 s steps, remembered per title, global default otherwise', () => {
  const values = new Map();
  const storage = { getItem: (key) => values.get(key) ?? null, setItem: (key, value) => values.set(key, value) };
  assert.equal(subs.loadTitleSubtitleDelay('movie:1', 250, storage), 250);
  subs.saveTitleSubtitleDelay('movie:1', subs.stepSubtitleDelay(250, 1), storage);
  assert.equal(subs.loadTitleSubtitleDelay('movie:1', 0, storage), 350);
  assert.equal(subs.loadTitleSubtitleDelay('movie:2', 0, storage), 0);
  assert.equal(subs.stepSubtitleDelay(0, -1), -100);
  assert.equal(subs.stepSubtitleDelay(30_000, 1), 30_000);
});

test('the subtitle route decodes legacy encodings and converts ASS instead of response.text()', async () => {
  const route = (body, headers = {}) => load('app/api/subtitle/route.ts', {
    'next/server': { NextResponse: class extends Response { static json(value, init) { return new Response(JSON.stringify(value), init); } } },
    '@/lib/server/safeProxy': { withinProxyBudget: () => true, safeProxyFetch: async () => new Response(body, { headers }) },
    '@/lib/playerSubtitles': subs
  });
  const request = (url) => new Request(`https://web.example/api/subtitle?url=${encodeURIComponent(url)}`);
  const legacy = await route(encode(cue('Привет, как дела? Я не знаю.'), 'windows-1251')).GET(request('https://subs.example/a.srt'));
  assert.equal(legacy.status, 200);
  assert.match(await legacy.text(), /Привет, как дела/);
  const ass = await route('[Script Info]\n[Events]\nFormat: Layer, Start, End, Style, Text\nDialogue: 0,0:00:01.00,0:00:02.00,Default,Hi {\\b1}there\n').GET(request('https://subs.example/a.ass'));
  assert.equal(await ass.text(), 'WEBVTT\n\n00:00:01.000 --> 00:00:02.000\nHi there\n');
  const html = await route('<html>blocked</html>').GET(request('https://subs.example/a.srt'));
  assert.equal(html.status, 422);
});

// ---------- Skip intro / credits, Up next ----------

const skip = load('lib/playerSkipSegments.ts');

test('IntroDB first (through the app proxy), AniSkip via ARM for anime, cached per episode', async () => {
  const requested = [];
  const fetchJson = async (url) => {
    requested.push(url);
    if (url.startsWith('proxy:')) return { intro: null, recap: null, outro: null };
    if (url.includes('arm.haglund.dev')) return [{ myanimelist: 21 }];
    if (url.includes('aniskip')) return { found: true, results: [{ interval: { startTime: 90, endTime: 180 }, skipType: 'op' }, { interval: { startTime: 1300, endTime: 1390 }, skipType: 'ed' }] };
    throw new Error('unexpected');
  };
  const segments = plain(await skip.fetchSkipSegments({ imdbId: 'tt0388629', season: 1, episode: 3 }, { fetchJson, introDbUrl: (url) => `proxy:${url}` }));
  assert.deepEqual(segments.map((s) => [s.type, s.startMs, s.endMs, s.provider]), [['op', 90000, 180000, 'aniskip'], ['ed', 1300000, 1390000, 'aniskip']]);
  assert.match(requested[0], /^proxy:https:\/\/api\.introdb\.app\/segments\?imdb_id=tt0388629&season=1&episode=3$/);
  assert.match(requested[2], /skip-times\/21\/3\?types=op&types=ed&types=recap/);
  await skip.fetchSkipSegments({ imdbId: 'tt0388629', season: 1, episode: 3 }, { fetchJson, introDbUrl: (url) => `proxy:${url}` });
  assert.equal(requested.length, 3, 'cached');
  assert.deepEqual(plain(skip.parseIntroDb({ intro: { start_sec: 60, end_sec: 120 }, outro: { start_ms: 3431000, end_ms: 3500000 } })).map((s) => s.type), ['intro', 'outro']);
});

test('skip button timing follows Android (1.2 s early, hidden 0.5 s before the end); Up next appears at the credits', () => {
  const segments = [{ startMs: 60000, endMs: 120000, type: 'intro', provider: 'introdb' }, { startMs: 2500000, endMs: 2560000, type: 'outro', provider: 'introdb' }];
  assert.equal(skip.activeSkipSegment(segments, 58700), null);
  assert.equal(skip.activeSkipSegment(segments, 58900).type, 'intro');
  assert.equal(skip.activeSkipSegment(segments, 119600), null);
  assert.equal(skip.skipSegmentLabel({ type: 'intro' }), 'Skip intro');
  assert.equal(skip.skipSegmentLabel({ type: 'ed' }), 'Skip credits');
  assert.equal(skip.skipSegmentLabel({ type: 'recap' }), 'Skip recap');
  assert.equal(skip.upNextStartTime(2600, segments), 2500, 'credits start when known');
  assert.equal(skip.upNextStartTime(2600), 2560, '40 s before the end otherwise');
  assert.equal(skip.upNextStartTime(300), 285, '95 % for short videos');
  assert.equal(skip.upNextStartTime(0), Infinity);
});

// ---------- Errors say what actually failed ----------

test('a refused source is reported by its HTTP status, not as a format problem', async () => {
  const recovery = load('lib/playerRecovery.ts');
  assert.match(recovery.describeHttpStatus(503), /server is unavailable \(HTTP 503\)/);
  assert.match(recovery.describeHttpStatus(403), /expired/);
  assert.match(recovery.describeHttpStatus(404), /no longer on the host/);
  assert.equal(recovery.describeHttpStatus(206), null);
  let init;
  const reason = await recovery.diagnoseSourceFailure('https://cdn.example/file.mp4', { fetchImpl: async (_url, options) => { init = options; return new Response('down', { status: 503 }); } });
  assert.match(reason, /HTTP 503/);
  assert.equal(init.headers.Range, 'bytes=0-0');
  assert.equal(await recovery.diagnoseSourceFailure('https://cdn.example/file.mp4', { fetchImpl: async () => { throw new TypeError('CORS'); } }), null);
  assert.equal(await recovery.diagnoseSourceFailure('blob:local', { fetchImpl: async () => new Response('', { status: 500 }) }), null);
});

// ---------- iPhone/iPad platform details ----------

test('the lock screen skips by the on-screen interval; fullscreen locks landscape where allowed', () => {
  const platform = load('lib/playerPlatform.ts');
  const handlers = new Map();
  const session = { setActionHandler: (name, handler) => handlers.set(name, handler) };
  const seeks = [];
  platform.bindMediaSession({ title: 'x' }, { play() {}, pause() {}, seekBy: (delta) => seeks.push(delta), skipSeconds: 10 }, { navigator: { mediaSession: session } });
  handlers.get('seekbackward')({ seekOffset: 15 });
  handlers.get('seekforward')({});
  assert.deepEqual(seeks, [-10, 10]);
  const locks = [];
  assert.equal(platform.lockLandscape({ screen: { orientation: { lock: async (value) => { locks.push(value); } } } }), true);
  assert.deepEqual(locks, ['landscape']);
  assert.equal(platform.lockLandscape({ screen: {} }), false, 'iOS Safari has no orientation lock');
});
