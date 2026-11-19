const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { renderToStaticMarkup } = require('react-dom/server');
const icons = require('lucide-react');
const { load } = require('./load.cjs');

const presentation = load('components/details/sourcePlaybackPresentation.ts');
const { sourcePlaybackPresentation } = presentation;
const stream = (overrides = {}) => ({ source: 'Fixture', addonName: 'Library', addonId: 'library', url: 'https://media.invalid/movie.mp4', ...overrides });
const plan = (overrides = {}) => ({ route: 'here', method: 'direct', detail: '', label: 'Play in browser', ...overrides });
const translate = (text, values) => String(text).replace(/\{(\w+)\}/g, (match, key) => String(values?.[key] ?? match));

// SourceRow is rendered by calling the component with a minimal hooks shim, so
// the returned element tree can be inspected and its handlers invoked.
function loadSourceRow() {
  const filename = path.resolve(__dirname, '../components/details/SourceRow.tsx');
  const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), {
    compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS, jsx: ts.JsxEmit.ReactJSX, esModuleInterop: true }
  }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, {
    module, exports: module.exports,
    require(name) {
      if (name === './sources.css') return {};
      if (name === 'react') return { useState: (initial) => [initial, () => {}], useRef: () => ({ current: null }), useEffect: () => {} };
      if (name === '@/lib/i18n') return { useTranslation: () => translate };
      if (name === 'lucide-react') return icons;
      if (name === 'react/jsx-runtime') return require(name);
      throw new Error(`Unexpected import ${name}`);
    },
    fetch() { throw new Error('Source rows must not request or probe media'); }
  }, { filename });
  return module.exports.SourceRow;
}
const SourceRow = loadSourceRow();
const VLC = { id: 'vlc', label: 'VLC' };
const INFUSE = { id: 'infuse', label: 'Infuse' };

function row(selectedPlan = plan(), { uncached = false, locked = false, compact = false, options = [INFUSE, VLC], input = stream() } = {}) {
  const calls = [];
  const view = sourcePlaybackPresentation(locked ? { url: null } : input, selectedPlan, uncached);
  const tree = SourceRow({
    rank: 1, title: presentation.sourceTitle(input), provider: input.addonName, presentation: view,
    badges: presentation.sourceBadges(input, { debrid: false, uncached }), externalOptions: options, locked, compact,
    onPlay: () => calls.push(['play']), onExternal: (option) => calls.push(['external', option.id]),
    onDownload: () => calls.push(['download']), onCopy: () => calls.push(['copy'])
  });
  return { tree, calls, html: renderToStaticMarkup(tree), view };
}
function elements(node, predicate) {
  if (!node || typeof node !== 'object') return [];
  if (Array.isArray(node)) return node.flatMap((child) => elements(child, predicate));
  return [...(predicate(node) ? [node] : []), ...elements(node.props?.children, predicate)];
}
const main = (tree) => elements(tree, (node) => node.type === 'button' && node.props.className === 'src-row-main')[0];
const sideButtons = (tree) => elements(tree, (node) => node.type === 'button' && node.props.className === 'src-action');

test('three tiers: plays here, needs conversion (slower start), external player only', () => {
  assert.equal(sourcePlaybackPresentation(stream(), plan(), false).tier, 'here');
  assert.equal(sourcePlaybackPresentation(stream(), plan(), false).label, 'Plays here');
  for (const method of ['remux', 'transcode']) {
    const view = sourcePlaybackPresentation(stream(), plan({ method }), false);
    assert.equal(view.tier, 'conversion');
    assert.match(view.label, /Needs conversion · slower start/);
    assert.equal(view.canTryBrowser, true);
  }
  for (const route of ['vlc', 'dead']) {
    const view = sourcePlaybackPresentation(stream(), plan({ route, detail: 'This device has no HEVC decoder' }), false);
    assert.equal(view.tier, 'external');
    assert.equal(view.label, 'External player only');
    assert.equal(view.canTryBrowser, false);
    assert.match(view.detail, /no HEVC decoder/);
  }
  assert.equal(sourcePlaybackPresentation({ url: null }, plan(), false).tier, 'external');
  // Nothing is labelled "unverified" any more.
  assert.doesNotMatch(JSON.stringify(sourcePlaybackPresentation(stream(), plan(), false)), /unverified/i);
});

test('a source that plays here starts in the browser from the row itself', () => {
  const h = row();
  assert.match(h.html, /Plays here/);
  assert.match(h.html, /src-play-cue/);
  main(h.tree).props.onClick();
  assert.deepEqual(h.calls, [['play']]);
  // External players stay one click away, without duplicates.
  assert.deepEqual(sideButtons(h.tree).map((button) => button.key), ['infuse', 'vlc']);
});

test('an external-only source has no Play: the row opens the first external player', () => {
  const h = row(plan({ route: 'vlc', detail: 'Unsupported video codec' }));
  assert.doesNotMatch(h.html, /src-play-cue/);
  assert.match(h.html, /External player only/);
  assert.match(h.html, /Unsupported video codec/);
  main(h.tree).props.onClick();
  assert.deepEqual(h.calls, [['external', 'infuse']]);
  assert.equal(main(h.tree).props['aria-label'], 'Open Fixture in Infuse');
  assert.deepEqual(sideButtons(h.tree).map((button) => button.key), ['vlc'], 'the primary player is not repeated beside the row');
});

test('uncached debrid sources cannot start in the browser and say why', () => {
  for (const selectedPlan of [plan(), plan({ method: 'transcode' })]) {
    const h = row(selectedPlan, { uncached: true });
    assert.match(h.html, /Not cached: the provider must download this source first/);
    assert.match(h.html, /Not cached/);
    main(h.tree).props.onClick();
    assert.notDeepEqual(h.calls[0], ['play']);
  }
});

test('locked rows (no URL, disabled Telegram) offer no action at all', () => {
  const h = row(plan(), { locked: true, input: stream({ url: null }) });
  assert.equal(main(h.tree).props.disabled, true);
  assert.equal(sideButtons(h.tree).length, 0);
  assert.equal(elements(h.tree, (node) => node.props?.['aria-haspopup'] === 'menu').length, 0);
  main(h.tree).props.onClick?.();
  assert.equal(h.calls.length, 0);
});

test('phones keep one primary action and move players and file actions into the ⋯ menu', () => {
  const h = row(plan(), { compact: true });
  assert.equal(sideButtons(h.tree).length, 0);
  const menu = elements(h.tree, (node) => node.props?.['aria-haspopup'] === 'menu')[0];
  assert.ok(menu, 'overflow menu button');
  assert.match(menu.props['aria-label'], /More actions for Fixture/);
});

test('badges show quality, HDR, audio, size and cache state — never a bare "URL" badge', () => {
  const labels = (input, info = { debrid: false, uncached: false }) => JSON.parse(JSON.stringify(presentation.sourceBadges(input, info).map((badge) => badge.label)));
  const remux = stream({ source: 'Movie 2160p REMUX DV HDR TrueHD Atmos', description: 'Movie.2160p.mkv 💾 58.3 GB' });
  assert.deepEqual(labels(remux), ['4K', 'DV', 'HDR', 'Atmos', '58.3 GB']);
  assert.equal(labels(stream()).includes('URL'), false);
  assert.ok(labels(stream({ url: null })).includes('No URL'));
  assert.ok(labels(stream({ source: '[TB+] Movie 1080p' }), { debrid: true, uncached: false }).includes('Cached'));
  assert.ok(labels(stream({ behaviorHints: { cached: true } })).includes('Cached'));
  assert.ok(labels(stream({ source: '[TB download] Movie' }), { debrid: true, uncached: true }).includes('Not cached'));
  assert.ok(labels(stream({ source: 'Movie' }), { debrid: true, uncached: false }).includes('Debrid'));
});

test('rows are titled by the release/file name rather than the addon label', () => {
  assert.equal(presentation.sourceTitle(stream({ source: 'Torrentio\n1080p', description: 'Movie.2024.1080p.WEB-DL.mkv 💾 3.2 GB' })), 'Movie.2024.1080p.WEB-DL.mkv');
  assert.equal(presentation.sourceTitle(stream({ behaviorHints: { filename: 'file.name.mkv' } })), 'file.name.mkv');
  assert.equal(presentation.sourceTitle(stream({ source: 'Library 4K', description: '👤 12 💾 3 GB' })), 'Library 4K');
});

test('filters: plays here (incl. conversion), 4K, 1080p and cached', () => {
  const { matchesSourceFilter } = presentation;
  assert.equal(matchesSourceFilter('here', { tier: 'conversion', quality: '', cached: false }), true);
  assert.equal(matchesSourceFilter('here', { tier: 'external', quality: '', cached: false }), false);
  assert.equal(matchesSourceFilter('4k', { tier: 'external', quality: '4K', cached: false }), true);
  assert.equal(matchesSourceFilter('1080p', { tier: 'here', quality: '4K', cached: false }), false);
  assert.equal(matchesSourceFilter('cached', { tier: 'here', quality: '', cached: true }), true);
  assert.equal(matchesSourceFilter('all', { tier: 'external', quality: '', cached: false }), true);
});

test('source labels and reasons remain escaped JSX text', () => {
  const html = row(plan({ route: 'vlc', detail: '<script>not executable</script>' }), { input: stream({ source: '<img src=x onerror=bad()>' }) }).html;
  assert.doesNotMatch(html, /<script>|<img src=x/);
  assert.match(html, /&lt;script&gt;/);
});

test('the picker and the player list share one order: plays here (direct → conversion), then external', () => {
  const plans = new Map();
  const make = (name, score, planFor) => { const s = stream({ source: name, url: `https://h.example/${name}`, score }); plans.set(s, planFor); return s; };
  const rank = load('lib/sourceRank.ts', {
    './debrid': { parseDebridStream: () => null, isUncachedDebridStream: () => false },
    './streamCompatibility': { playbackPlan: (s) => plans.get(s) ?? plan(), audioDecodableForDevice: () => true, videoDecodableForDevice: () => true, isDirectPlayableStream: () => false, isBrowserPlayableStream: () => true }
  });
  const external4k = make('External 2160p REMUX', 0, plan({ route: 'vlc' }));
  const remux1080 = make('Remux 1080p', 0, plan({ method: 'remux' }));
  const direct720 = make('Direct 720p', 0, plan());
  const direct1080 = make('Direct 1080p', 0, plan());
  const transcode = make('Transcode 2160p', 0, plan({ method: 'transcode' }));
  const groups = rank.groupSourcesForPlayback([external4k, remux1080, direct720, transcode, direct1080], (s) => plans.get(s));
  assert.deepEqual(Array.from(groups.here, (s) => s.source), ['Direct 1080p', 'Direct 720p', 'Remux 1080p', 'Transcode 2160p']);
  assert.deepEqual(Array.from(groups.external, (s) => s.source), ['External 2160p REMUX']);
  assert.deepEqual(Array.from(rank.orderSourcesForPlayback([direct720, external4k, direct1080], (s) => plans.get(s)), (s) => s.source), ['Direct 1080p', 'Direct 720p', 'External 2160p REMUX']);
});

test('external players per platform never offer the same app twice', () => {
  const players = load('lib/externalPlayers.ts', { './resolver': { resolverDownloadUrl: () => null, resolverLaunchUrl: () => null, resolverSubtitleUrl: (u) => u } });
  assert.deepEqual(Array.from(players.externalPlayerOptions('apple'), (o) => o.id), ['infuse', 'vlc']);
  assert.deepEqual(Array.from(players.externalPlayerOptions('android'), (o) => o.id), ['chooser']);
  assert.deepEqual(Array.from(players.externalPlayerOptions('desktop'), (o) => o.id), ['vlc']);
});

function compatibility() {
  return load('lib/streamCompatibility.ts', {
    './capabilities': { getMediaCapabilities: () => ({ mse: true, nativeHls: false, h264: true, aac: true,
      hevc: true, hevc10: true, dolbyVision: false, av1: true, vp9: true, ac3: false, eac3: false, opus: true, flac: true }) },
    './debrid': { parseDebridStream: () => null }
  });
}

test('selected-source failure evidence moves a row to "External player only"', () => {
  const shared = compatibility();
  const selected = stream();
  assert.equal(sourcePlaybackPresentation(selected, shared.playbackPlan(selected), false).tier, 'here');
  shared.recordBrowserPlaybackFailure(selected, 'Selected-file probe found unsupported video decoding.', true);
  const view = sourcePlaybackPresentation(selected, shared.playbackPlan(selected), false);
  assert.equal(view.tier, 'external');
  assert.match(view.detail, /unsupported video decoding/);
  shared.clearBrowserPlaybackFailure(selected);
  assert.equal(sourcePlaybackPresentation(selected, shared.playbackPlan(selected), false).tier, 'here');
});

test('Dolby Vision conditional remux keeps the verified-HDR10-base-layer requirement visible', () => {
  const shared = compatibility();
  const selected = stream({ url: 'https://media.invalid/movie.mkv', media: { container: 'mkv', videoCodec: 'dvhe.08.06', audioCodec: 'aac' } });
  const view = sourcePlaybackPresentation(selected, shared.playbackPlan(selected), false);
  assert.equal(view.tier, 'conversion');
  assert.match(view.detail, /Selected-file check required: only a verified HDR10-compatible Dolby Vision base layer can be played\./);
});
