const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { renderToStaticMarkup } = require('react-dom/server');
const icons = require('lucide-react');
const { load } = require('./load.cjs');

const { sourcePlaybackPresentation } = load('components/details/sourcePlaybackPresentation.ts');
const filename = path.resolve(__dirname, '../components/details/DetailsDrawer.tsx');
const source = ts.createSourceFile(filename, fs.readFileSync(filename, 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
const stream = (overrides = {}) => ({ source: 'Fixture', addonName: 'Library', addonId: 'library', url: 'https://media.invalid/movie.mp4', ...overrides });
const plan = (overrides = {}) => ({ route: 'here', method: 'direct', detail: '', label: 'Play in browser', ...overrides });

// Like the playback integration harness, execute the actual component callback,
// including JSX, without mounting unrelated provider state or running effects.
function extracted(selector, globals = {}) {
  const matches = [];
  const visit = (node) => { const match = selector(node); if (match) matches.push(match); ts.forEachChild(node, visit); };
  visit(source);
  assert.equal(matches.length, 1, 'Expected one drawer callback');
  const code = ts.transpileModule(`module.exports = (${matches[0].getText(source)});`, {
    compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS, jsx: ts.JsxEmit.ReactJSX }
  }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, {
    module, exports: module.exports, ...globals,
    require(name) { assert.equal(name, 'react/jsx-runtime'); return require(name); },
    fetch() { throw new Error('Source rows must not request or probe media'); }
  }, { filename });
  return module.exports;
}
const namedFunction = (name, globals) => extracted((node) => ts.isFunctionDeclaration(node) && node.name?.text === name ? node : undefined, globals);
const detectSourceBadge = namedFunction('detectSourceBadge');
const streamBadges = namedFunction('streamBadges', { detectSourceBadge, parseDebridStream: () => null });

function row(selectedPlan = plan(), uncached = false) {
  const calls = [];
  const render = extracted((node) => ts.isCallExpression(node) && node.expression.getText(source) === 'filtered.map'
    ? node.arguments[0] : undefined, {
    sourcePlaybackPresentation, streamBadges,
    playbackPlan: typeof selectedPlan === 'function' ? selectedPlan : () => selectedPlan, isUncachedDebridStream: () => uncached,
    TriangleAlert: icons.TriangleAlert, Info: icons.Info, Play: icons.Play,
    ExternalLink: icons.ExternalLink, Download: icons.Download, Copy: icons.Copy,
    playStream: (...args) => calls.push(['play', ...args]), onClose: () => calls.push(['close']),
    openExternal: (...args) => calls.push(['external', ...args]), openAnyPlayer: (...args) => calls.push(['player', ...args]),
    downloadSource: (...args) => calls.push(['download', ...args]), copyUrl: (...args) => calls.push(['copy', ...args])
  });
  return { render, calls };
}
function elements(node, predicate) {
  if (!node || typeof node !== 'object') return [];
  if (Array.isArray(node)) return node.flatMap((child) => elements(child, predicate));
  return [...(predicate(node) ? [node] : []), ...elements(node.props?.children, predicate)];
}
const button = (node, label) => elements(node, (entry) => entry.type === 'button' && entry.props['aria-label'] === label)[0];

for (const route of ['vlc', 'dead']) {
  test(`${route} rows explicitly reject browser playback while keeping the reason visible`, () => {
    const selectedPlan = plan({ route, detail: 'This device has no HEVC decoder' });
    const h = row(selectedPlan); const result = h.render(stream(), 0);
    const html = renderToStaticMarkup(result);
    assert.match(html, /Not playable in this browser/);
    assert.match(html, /This device has no HEVC decoder/);
    assert.match(html, /data-playback-state="blocked"/);
    assert.match(html, /recommend-external/);
    assert.equal(button(result, 'Try browser playback'), undefined);
    const external = elements(result, (entry) => entry.type === 'button' && Array.isArray(entry.props.children) && entry.props.children.includes(' VLC'))[0];
    assert.equal(external.props.disabled, false);
    external.props.onClick();
    assert.equal(h.calls[0][0], 'external');
    assert.equal(h.calls[0][1], 'vlc');
  });
}

test('unresolved rows are blocked regardless of an optimistic plan and preserve disabled external actions', () => {
  const h = row(); const result = h.render(stream({ url: null }), 0); const html = renderToStaticMarkup(result);
  assert.match(html, /Not playable in this browser/);
  assert.match(html, /No resolved playback URL/);
  assert.match(html, /NO URL/);
  assert.doesNotMatch(html, /ANDROID|Browser or external player/);
  for (const action of elements(result, (entry) => entry.type === 'button')) assert.equal(action.props.disabled, true);
});

test('provider conversion is conditional, never claimed ready, and remains selectable', () => {
  const h = row(plan({ method: 'transcode', detail: 'Conversion requires provider support and permission' }));
  const input = stream(); const result = h.render(input, 0); const html = renderToStaticMarkup(result);
  assert.match(html, /Requires provider conversion/);
  assert.match(html, /Conversion requires provider support and permission/);
  assert.doesNotMatch(html, /Browser or external player|is-web/);
  const action = button(result, 'Try provider conversion in browser');
  assert.ok(action);
  action.props.onClick();
  assert.equal(h.calls[0][0], 'play');
  assert.equal(h.calls[0][1], input);
  assert.equal(h.calls[0][2].forceBrowser, true);
  assert.deepEqual(h.calls[1], ['close']);
});

test('a failed conversion or probe from the shared plan overrides the previous possible route', () => {
  for (const detail of ['Provider conversion failed: permission denied.', 'Probe found unsupported video decoding.']) {
    const h = row(plan({ route: 'vlc', method: 'transcode', detail }));
    const result = h.render(stream(), 0); const html = renderToStaticMarkup(result);
    assert.match(html, /Not playable in this browser/);
    assert.ok(html.includes(detail));
    assert.doesNotMatch(html, /Requires provider conversion/);
    assert.equal(button(result, 'Try provider conversion in browser'), undefined);
  }
});

test('unknown metadata never gets a browser-supported claim, but still offers a browser attempt', () => {
  const h = row(plan()); const result = h.render(stream({ source: 'Unknown 4K', url: 'https://media.invalid/opaque' }), 0);
  const html = renderToStaticMarkup(result);
  assert.match(html, /Browser playback unverified/);
  assert.equal(elements(result, (entry) => entry.props?.className === 'source-warning').length, 0, 'Do not repeat the unverified label in a second line');
  assert.doesNotMatch(html, /Browser or external player|Play in browser|DIRECT|is-web/);
  assert.ok(button(result, 'Try browser playback'));
});

test('even codec metadata does not turn an untested source into a verified success', () => {
  const result = sourcePlaybackPresentation(stream({ media: { container: 'mp4', videoCodec: 'h264', audioCodec: 'aac' } }), plan(), false);
  assert.equal(result.state, 'unverified');
  assert.equal(result.canTryBrowser, true);
});

test('browser preparation retains the shared audio/container reason and an attempt action', () => {
  const h = row(plan({ method: 'remux', detail: 'DTS needs audio conversion' }));
  const result = h.render(stream(), 0); const html = renderToStaticMarkup(result);
  assert.match(html, /Requires browser preparation/);
  assert.match(html, /DTS needs audio conversion/);
  assert.doesNotMatch(html, /Requires provider conversion|Not playable in this browser/);
  assert.ok(button(result, 'Try browser playback'));
});

test('uncached status cannot conceal an unsupported-browser warning or imply immediate conversion', () => {
  for (const selectedPlan of [plan({ route: 'vlc', detail: 'Unsupported video codec' }), plan({ method: 'transcode' }), plan()]) {
    const h = row(selectedPlan, true); const result = h.render(stream(), 0); const html = renderToStaticMarkup(result);
    assert.match(html, /Not cached: the provider must download this source first/);
    assert.equal(button(result, 'Try browser playback'), undefined);
    assert.equal(button(result, 'Try provider conversion in browser'), undefined);
    if (selectedPlan.route === 'vlc') assert.match(html, /Not playable in this browser/);
    if (selectedPlan.method === 'transcode') assert.match(html, /Requires provider conversion/);
  }
});

test('status wraps within narrow rows and exposes text independently of color or hover', () => {
  const reason = 'Unsupported container: ' + 'VeryLongProviderFormatName'.repeat(12);
  const result = row(plan({ route: 'vlc', detail: reason })).render(stream(), 0);
  const status = elements(result, (entry) => entry.props?.className?.startsWith('source-playback-status'))[0];
  assert.equal(status.props.style.whiteSpace, 'normal');
  assert.equal(status.props.style.overflowWrap, 'anywhere');
  assert.equal(status.props.style.maxWidth, '100%');
  const warning = elements(result, (entry) => entry.props?.className === 'source-warning')[0];
  assert.equal(warning.props.children, reason);
  assert.equal(elements(result, (entry) => entry.props?.role === 'alert').length, 0, 'Do not announce every result row as an alert');
  const icon = elements(status, (entry) => entry.type === icons.TriangleAlert)[0];
  assert.equal(icon.props['aria-hidden'], 'true');
  assert.equal(icon.props.style.flexShrink, 0);
});

test('source labels and warning details remain escaped JSX text', () => {
  const html = renderToStaticMarkup(row(plan({ route: 'vlc', detail: '<script>not executable</script>' }))
    .render(stream({ source: '<img src=x onerror=bad()>' }), 0));
  assert.doesNotMatch(html, /<script>|<img src=x/);
  assert.match(html, /&lt;script&gt;/);
  assert.match(html, /&lt;img/);
});

test('URL and missing-URL badges do not claim direct playback or Android compatibility', () => {
  const withUrl = streamBadges(stream());
  assert.equal(withUrl.find((badge) => badge.label === 'URL').tone, undefined);
  assert.equal(withUrl.some((badge) => badge.label === 'DIRECT'), false);
  const withoutUrl = streamBadges(stream({ url: undefined }));
  assert.equal(withoutUrl.some((badge) => badge.label === 'NO URL'), true);
  assert.equal(withoutUrl.some((badge) => badge.label === 'ANDROID'), false);
});

test('filtering keeps quality-first sorting and never removes blocked or unknown sources', () => {
  const streams = [stream({ source: 'Unknown', score: 10 }), stream({ source: 'External 4K', score: 30 }), stream({ source: 'Conversion', score: 20 }), stream({ source: 'No URL', url: null, score: 5 })];
  const targets = [];
  const filtered = extracted((node) => ts.isVariableDeclaration(node) && node.name.getText(source) === 'filtered'
    && ts.isCallExpression(node.initializer) ? node.initializer.arguments[0] : undefined, {
    streams, query: '', addonFilter: 'all', sourcePickerScore: (entry, target) => { targets.push(target); return entry.score; }
  });
  assert.deepEqual(Array.from(filtered(), (entry) => entry.source), ['External 4K', 'Conversion', 'Unknown', 'No URL']);
  assert.ok(targets.every((target) => target === 'external'));
  assert.deepEqual(streams.map((entry) => entry.source), ['Unknown', 'External 4K', 'Conversion', 'No URL'], 'Do not mutate the provider list');
});

test('a large set of rows only consumes existing plans and never performs a network request', () => {
  const h = row(plan());
  for (let i = 0; i < 200; i++) assert.ok(h.render(stream({ url: `https://media.invalid/${i}` }), i));
  assert.equal(h.calls.length, 0);
});

function compatibility() {
  return load('lib/streamCompatibility.ts', {
    './capabilities': { getMediaCapabilities: () => ({ mse: true, nativeHls: false, h264: true, aac: true,
      hevc: true, hevc10: true, dolbyVision: false, av1: true, vp9: true, ac3: false, eac3: false, opus: true, flac: true }) },
    './debrid': { parseDebridStream: () => null }
  });
}

test('picker subscribes to selected-source evidence, refreshes plans, and uses a stable server snapshot', () => {
  const shared = compatibility();
  const h = row(shared.playbackPlan);
  const selected = stream();
  let rendered = h.render(selected, 0);
  let unsubscribe;
  const revisions = [];
  const initial = extracted((node) => ts.isCallExpression(node) && node.expression.getText(source) === 'useSyncExternalStore' ? node : undefined, {
    subscribePlaybackCompatibility: shared.subscribePlaybackCompatibility,
    playbackCompatibilityRevision: shared.playbackCompatibilityRevision,
    useSyncExternalStore(subscribe, getSnapshot, getServerSnapshot) {
      assert.equal(subscribe, shared.subscribePlaybackCompatibility);
      assert.equal(getSnapshot, shared.playbackCompatibilityRevision);
      assert.equal(getServerSnapshot(), 0);
      unsubscribe = subscribe(() => { revisions.push(getSnapshot()); rendered = h.render(selected, 0); });
      return getSnapshot();
    }
  });
  assert.equal(initial, 0);
  assert.match(renderToStaticMarkup(rendered), /Browser playback unverified/);
  shared.recordBrowserPlaybackFailure(selected, 'Selected-file probe found unsupported video decoding.');
  assert.deepEqual(revisions, [1]);
  assert.match(renderToStaticMarkup(rendered), /Not playable in this browser/);
  assert.match(renderToStaticMarkup(rendered), /Selected-file probe found unsupported video decoding/);
  assert.equal(button(rendered, 'Try browser playback'), undefined);
  shared.clearBrowserPlaybackFailure(selected);
  assert.deepEqual(revisions, [1, 2]);
  assert.ok(button(rendered, 'Try browser playback'));
  unsubscribe();
  shared.recordBrowserPlaybackFailure(selected, 'Later failure');
  assert.deepEqual(revisions, [1, 2], 'Unmounted pickers must not receive updates');
  assert.equal(h.calls.length, 0, 'Evidence notifications do not start playback or network requests');
});

test('Dolby Vision conditional remux keeps the verified-HDR10-base-layer requirement visible', () => {
  const shared = compatibility();
  const selected = stream({ url: 'https://media.invalid/movie.mkv', media: { container: 'mkv', videoCodec: 'dvhe.08.06', audioCodec: 'aac' } });
  const rendered = row(shared.playbackPlan).render(selected, 0);
  const html = renderToStaticMarkup(rendered);
  assert.match(html, /Requires browser preparation/);
  assert.match(html, /Selected-file check required: only a verified HDR10-compatible Dolby Vision base layer can be played\./);
  assert.ok(button(rendered, 'Try browser playback'));
  assert.doesNotMatch(html, /is-web|Browser or external player/);
});
