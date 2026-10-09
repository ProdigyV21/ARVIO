const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const ts = require('typescript');
const { load } = require('./load.cjs');
const links = load('lib/partnerLinks.ts');
const target = search => {
  const parsed = links.parsePartnerLink(search);
  assert.equal(parsed.status, 'valid', search);
  return parsed.target;
};

test('partner query accepts IMDb and scoped TMDB IDs, empty optional fields and specials', () => {
  assert.equal(target('?open=1&imdb=tt0137523').imdb, 'tt0137523');
  assert.equal(target('?open=1&imdb=tt12345').imdb, 'tt12345');
  assert.equal(target('?open=1&imdb=tt123456789012').imdb, 'tt123456789012');
  assert.equal(target('?open=1&type=movie&id=2147483647').id, 2147483647);
  assert.equal(target('?open=1&type=tv&id=1399&season=0&episode=1').season, 0);
  assert.equal(target('?open=1&type=tv&id=1399&season=10').episode, undefined);
  assert.equal(target('?open=1&imdb=tt0137523&season=&episode=').season, undefined);
  assert.equal(links.parsePartnerLink('?imdb=tt0137523').status, 'none');
});

test('reject malformed, contradictory, duplicate and arbitrary URL targets before requesting', () => {
  for (const query of [
    'open=0&imdb=tt0137523', 'open=true&imdb=tt0137523', 'open=1',
    'open=1&imdb=https://evil.invalid', 'open=1&url=https://evil.invalid',
    'open=1&type=movie&id=https://evil.invalid', 'open=1&imdb=TT0137523',
    'open=1&imdb=tt1234', 'open=1&imdb=tt1234567890123',
    'open=1&type=movie&id=0', 'open=1&type=tv&id=-1',
    'open=1&type=tv&id=2147483648', 'open=1&type=tv&id=1e3',
    'open=1&type=tv&id=1.5', 'open=1&type=tv&id=+1',
    'open=1&type=series&id=123', 'open=1&id=550',
    'open=1&imdb=tt0137523&id=550', 'open=1&imdb=tt0137523&id=',
    'open=1&imdb=tt0137523&type=', 'open=1&imdb=tt0137523&type=tv',
    'open=1&imdb=&type=movie&id=550', 'open=1&type=tv&id=001',
    'open=1&type=tv&id=1399&season=01', 'open=1&type=tv&id=1399&season=0&episode=01',
    'open=1&type=movie&id=550&season=0', 'open=1&type=movie&id=550&episode=1',
    'open=1&type=tv&id=1399&episode=1', 'open=1&type=tv&id=1399&season=-1',
    'open=1&type=tv&id=1399&season=10001', 'open=1&type=tv&id=1399&season=1&episode=0',
    'open=1&type=tv&id=1399&season=1&episode=10001',
    'open=1&open=1&imdb=tt0137523', 'open=1&imdb=tt0137523&imdb=tt0137523',
    'open=1&type=movie&type=tv&id=550', 'open=1&type=movie&id=550&id=550',
    'open=1&type=tv&id=1399&season=1&season=2',
    'open=1&type=tv&id=1399&season=1&episode=2&episode=',
    'open=1&%69mdb=tt0137523&imdb=tt0137523'
  ]) assert.equal(links.parsePartnerLink(query).status, 'invalid', query);
  assert.equal(links.parsePartnerLink('?open=1&imdb=tt0137523&extra=' + 'x'.repeat(4096)).status, 'invalid');
});

test('stripping removes only partner keys and preserves query attribution and hash', () => {
  const original = 'https://web.arvio.tv/?open=1&type=tv&id=1399&season=0&episode=1&utm_source=simkl&theme=dark#settings';
  assert.equal(links.stripPartnerLink(original), '/?utm_source=simkl&theme=dark#settings');
  assert.equal(links.stripPartnerLink('https://web.arvio.tv/?open=1&imdb=tt0137523#anchor'), '/#anchor');
});

test('login redirects preserve valid pending titles and attribution, not arbitrary destinations', () => {
  assert.equal(links.partnerLoginRedirect('https://web.arvio.tv', '?open=1&imdb=tt0137523&utm_source=simkl'),
    'https://web.arvio.tv/?open=1&imdb=tt0137523&utm_source=simkl');
  assert.equal(links.partnerLoginRedirect('https://web.arvio.tv', '?open=1&url=https://evil.invalid'), 'https://web.arvio.tv/');
  assert.equal(links.partnerLoginRedirect('https://web.arvio.tv', '?return_url=https://evil.invalid'), 'https://web.arvio.tv/');
});

test('IMDb find resolves films and shows with explicit type hints and excludes adult records', async () => {
  const calls = [];
  const request = async (requestPath, params) => {
    calls.push([requestPath, params]);
    return { movie_results: [{ id: 550 }], tv_results: [{ id: 1399 }] };
  };
  assert.equal((await links.resolvePartnerReference({ imdb: 'tt0137523', mediaType: 'movie' }, request)).id, 550);
  assert.equal((await links.resolvePartnerReference({ imdb: 'tt0137523', mediaType: 'tv' }, request)).id, 1399);
  assert.equal(calls[0][0], 'find/tt0137523');
  assert.equal(calls[0][1].external_source, 'imdb_id');
  await assert.rejects(links.resolvePartnerReference(target('?open=1&imdb=tt0137523'), request), /unambiguously/);
  await assert.rejects(links.resolvePartnerReference(target('?open=1&imdb=tt0137523'), async () => ({ movie_results: [{ id: 550, adult: true }] })), /not be found/);
});

test('IMDb episode finds parent show, retains episode coordinates including season zero', async () => {
  const ref = await links.resolvePartnerReference(target('?open=1&imdb=tt12345'), async () => ({
    tv_episode_results: [{ id: 987654, show_id: 1399, season_number: 0, episode_number: 2 }]
  }));
  assert.equal(ref.id, 1399);
  assert.equal(ref.mediaType, 'tv');
  assert.equal(ref.season, 0);
  assert.equal(ref.episode, 2);
  await assert.rejects(links.resolvePartnerReference(target('?open=1&imdb=tt12345&season=1&episode=2'), async () => ({
    tv_episode_results: [{ show_id: 1399, season_number: 0, episode_number: 2 }]
  })), /do not match/);
});

test('IMDb movie rejects episode coordinates after type inference and unknown IDs are retryable', async () => {
  await assert.rejects(links.resolvePartnerReference(target('?open=1&imdb=tt0137523&season=1'), async () => ({ movie_results: [{ id: 550 }] })), /movie link/);
  await assert.rejects(links.resolvePartnerReference(target('?open=1&imdb=tt0137523'), async () => ({})), /not be found/);
  await assert.rejects(links.resolvePartnerReference(target('?open=1&imdb=tt0137523'), async () => ({ movie_results: [{ id: -1 }] })), /not be found/);
  await assert.rejects(links.resolvePartnerReference(target('?open=1&imdb=tt0137523'), async () => ({ tv_episode_results: [{ show_id: 1399, season_number: 0, episode_number: 10001 }] })), /not be found/);
});

function mediaFixture(respond) {
  const calls = [];
  const media = load('lib/partnerMedia.ts', {
    './partnerLinks': links,
    './tmdb': {
      tmdb: async (requestPath, params, key) => { calls.push({ path: requestPath, params, key }); return respond(requestPath); },
      mapTmdbItem: (payload, mediaType) => ({ id: payload.id, title: payload.title ?? payload.name, mediaType })
    }
  });
  return { ...media, calls };
}

test('media resolver fetches existing TMDB proxy using profile language/key and verifies episode', async () => {
  const api = mediaFixture(requestPath => requestPath === 'tv/1399'
    ? { id: 1399, name: 'Show' } : { season_number: 0, episode_number: 2, name: 'Special' });
  const item = await api.resolvePartnerMedia(target('?open=1&type=tv&id=1399&season=0&episode=2'), 'nl-NL', 'own-key');
  assert.equal(item.seasonNumber, 0);
  assert.equal(item.episodeNumber, 2);
  assert.equal(item.episodeTitle, 'Special');
  assert.deepEqual(api.calls.map(x => x.path), ['tv/1399', 'tv/1399/season/0/episode/2']);
  assert.ok(api.calls.every(x => x.params.language === 'nl-NL' && x.key === 'own-key'));
});

test('media resolver validates a season-only link and does not set a phantom episode', async () => {
  const api = mediaFixture(requestPath => requestPath === 'tv/1399' ? { id: 1399, name: 'Show' } : { season_number: 3 });
  const item = await api.resolvePartnerMedia(target('?open=1&type=tv&id=1399&season=3'), 'en');
  assert.equal(item.seasonNumber, 3);
  assert.equal(item.episodeNumber, null);
  assert.equal(api.calls[1].path, 'tv/1399/season/3');
});

test('episode IMDb identity is not mistaken for parent-show identity', async () => {
  const api = mediaFixture(requestPath => requestPath.startsWith('find/')
    ? { tv_episode_results: [{ show_id: 1399, season_number: 1, episode_number: 2 }] }
    : requestPath === 'tv/1399' ? { id: 1399, name: 'Show' } : { season_number: 1, episode_number: 2 });
  const item = await api.resolvePartnerMedia(target('?open=1&imdb=tt12345'), 'en');
  assert.equal(item.id, 1399);
  assert.equal(item.imdbId, undefined);
});

test('unavailable/adult/mismatched TMDB responses fail rather than opening fabricated metadata', async () => {
  for (const response of [{ id: 550, adult: true, title: 'Adult' }, { id: 999, title: 'Other' }, { id: 550 }]) {
    const api = mediaFixture(() => response);
    await assert.rejects(api.resolvePartnerMedia(target('?open=1&type=movie&id=550'), 'en'));
  }
  const api = mediaFixture(requestPath => requestPath === 'tv/1399'
    ? { id: 1399, name: 'Show' } : { season_number: 2, episode_number: 1 });
  await assert.rejects(api.resolvePartnerMedia(target('?open=1&type=tv&id=1399&season=1&episode=1'), 'en'), /could not be found/);
});

test('network failure is not permanently cached: retry performs a fresh metadata request', async () => {
  let attempt = 0;
  const api = mediaFixture(() => { if (!attempt++) throw new Error('Network offline'); return { id: 550, title: 'Film' }; });
  const movie = target('?open=1&type=movie&id=550');
  await assert.rejects(api.resolvePartnerMedia(movie, 'en'));
  assert.equal((await api.resolvePartnerMedia(movie, 'en')).title, 'Film');
  assert.equal(api.calls.length, 2);
});

function lifecycle() {
  const state = { requests: 0, opens: [], errors: [] };
  const options = {
    ready: true, isCurrent: () => true,
    resolve: async () => { state.requests++; return { id: 550 }; },
    open: item => state.opens.push(item), error: message => state.errors.push(message)
  };
  return { state, options };
}

test('login/profile/access gating does not resolve or consume a pending link until ready', async () => {
  const { state, options } = lifecycle();
  assert.equal(await links.runPartnerLink({ ...options, ready: false }), 'waiting');
  assert.equal(state.requests, 0);
  assert.equal(state.opens.length, 0);
  assert.equal(await links.runPartnerLink(options), 'opened');
  assert.equal(state.requests, 1);
  assert.equal(state.opens.length, 1);
});

test('new link, profile/account change, manual selection or cancel makes async completion stale', async () => {
  for (const reason of ['new link', 'profile', 'account', 'selection', 'cancel']) {
    const { state, options } = lifecycle();
    let finish, current = true;
    const pending = links.runPartnerLink({ ...options, isCurrent: () => current,
      resolve: () => new Promise(resolve => { finish = resolve; }) });
    current = false;
    finish({ id: 550 });
    assert.equal(await pending, 'stale', reason);
    assert.equal(state.opens.length, 0, reason);
    assert.equal(state.errors.length, 0, reason);
  }
});

test('stale network errors do not overwrite the newer link state', async () => {
  const { state, options } = lifecycle();
  let fail, current = true;
  const pending = links.runPartnerLink({ ...options, isCurrent: () => current,
    resolve: () => new Promise((_, reject) => { fail = reject; }) });
  current = false;
  fail(new Error('Old request failed'));
  assert.equal(await pending, 'stale');
  assert.equal(state.errors.length, 0);
});

test('current errors remain actionable and a successful retry opens once', async () => {
  const { state, options } = lifecycle();
  assert.equal(await links.runPartnerLink({ ...options, resolve: async () => { throw new Error('Offline'); } }), 'error');
  assert.equal(state.errors.length, 1);
  assert.equal(state.opens.length, 0);
  assert.equal(await links.runPartnerLink(options), 'opened');
  assert.equal(state.opens.length, 1);
});

test('network/provider diagnostics cannot expose credentials in the visible error', async () => {
  const { state, options } = lifecycle();
  await links.runPartnerLink({ ...options, resolve: async () => { throw new Error('https://private.invalid/?api_key=secret'); } });
  assert.equal(state.errors[0], 'Could not open this title. Please try again.');
  await links.runPartnerLink({ ...options, resolve: async () => { throw new links.PartnerLinkError('This title is unavailable in ARVIO.'); } });
  assert.equal(state.errors[1], 'This title is unavailable in ARVIO.');
});

test('production episode boundary helper advances specials without treating season zero as missing', () => {
  const filename = path.resolve(__dirname, '../lib/store.tsx');
  const source = ts.createSourceFile(filename, fs.readFileSync(filename, 'utf8'), ts.ScriptTarget.Latest, true, ts.ScriptKind.TSX);
  let declaration;
  const visit = node => {
    if (ts.isFunctionDeclaration(node) && node.name?.text === 'nextLocalEpisode') declaration = node;
    ts.forEachChild(node, visit);
  };
  visit(source);
  assert.ok(declaration);
  const code = ts.transpileModule(`module.exports = (${declaration.getText(source)});`, {
    compilerOptions: { target: ts.ScriptTarget.ES2022, module: ts.ModuleKind.CommonJS }
  }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, { module });
  const special = { seasonNumber: 0, episodeNumber: 2, seasons: [
    { seasonNumber: 0, episodeCount: 2 }, { seasonNumber: 1, episodeCount: 3 }
  ] };
  assert.equal(module.exports(special).seasonNumber, 0);
  const next = module.exports({ ...special, episodeNumber: 3 });
  assert.equal(next.seasonNumber, 1);
  assert.equal(next.episodeNumber, 1);
});

test('UI wiring remains behind entitlement and hydrated profile checks; title links never autoplay', () => {
  const read = relative => fs.readFileSync(path.resolve(__dirname, '..', relative), 'utf8');
  const shell = read('components/shell/AppShell.tsx');
  const handler = read('components/shell/PartnerLinkHandler.tsx');
  const store = read('lib/store.tsx');
  assert.ok(shell.indexOf('<PartnerLinkHandler />') > shell.indexOf('<EntitlementGate>'));
  assert.ok(shell.indexOf('<PartnerLinkHandler />') < shell.indexOf('</EntitlementGate>'));
  assert.match(store, /partnerLinkReady: view === "app" && Boolean\(activeProfile\) && \(!auth \|\| cloudProfilesHydrated\)/);
  assert.match(handler, /void openDetails\(item\)/);
  assert.doesNotMatch(handler, /\b(?:playStream|playTrailer|loadEpisodeStreams)\s*\(/);
  assert.match(handler, /translateUi\("Retry"\)/);
  assert.match(handler, /translateUi\("Cancel"\)/);
  assert.match(handler, /window\.history\.state/);
});

// Execute the real component/effects with deterministic React and location fixtures.
// This catches lifecycle regressions that a pure resolver test cannot exercise.
function handlerFixture(options = {}) {
  const slots = [];
  let cursor = 0, dirty = false, effects = [], tree;
  const state = { requests: 0, opens: [], currentUrl: new URL(options.url ?? 'https://web.arvio.tv/?open=1&type=movie&id=550&utm_source=partner#anchor') };
  const app = {
    partnerLinkReady: options.ready ?? true, activeProfile: { id: 'profile-a' }, auth: { userId: 'account-a' },
    selected: null, section: 'home', settings: { language: 'en' },
    closePlayer: () => {},
    openDetails: async item => { state.opens.push(item); app.selected = item; dirty = true; }
  };
  const sameDeps = (a, b) => a?.length === b?.length && a.every((value, index) => Object.is(value, b[index]));
  const react = {
    useState: initial => {
      const index = cursor++;
      slots[index] ??= { value: typeof initial === 'function' ? initial() : initial };
      return [slots[index].value, value => {
        const next = typeof value === 'function' ? value(slots[index].value) : value;
        if (!Object.is(next, slots[index].value)) { slots[index].value = next; dirty = true; }
      }];
    },
    useRef: initial => {
      const index = cursor++;
      slots[index] ??= { value: { current: initial } };
      return slots[index].value;
    },
    useEffect: (effect, deps) => {
      const index = cursor++;
      const previous = slots[index];
      if (previous && sameDeps(previous.deps, deps)) return;
      slots[index] = { deps, cleanup: previous?.cleanup };
      effects.push(() => { slots[index].cleanup?.(); slots[index].cleanup = effect(); });
    }
  };
  const location = {
    get search() { return state.currentUrl.search; },
    get href() { return state.currentUrl.href; }
  };
  const listeners = new Map();
  const mocks = {
    'react': react,
    'react/jsx-runtime': { jsx: (type, props) => ({ type, props }), jsxs: (type, props) => ({ type, props }) },
    'lucide-react': { Loader2() {}, RefreshCw() {}, X() {} },
    '@/lib/i18n': { useTranslation: () => value => value },
    '@/lib/partnerLinks': links,
    '@/lib/partnerMedia': { resolvePartnerMedia: async (...args) => { state.requests++; return options.resolve ? options.resolve(...args) : { id: 550, mediaType: 'movie', title: 'Film' }; } },
    '@/lib/store': { useApp: () => app, getPriorityConfig: () => ({}) },
    './PartnerLinkHandler.module.css': { default: {} }
  };
  const filename = path.resolve(__dirname, '../components/shell/PartnerLinkHandler.tsx');
  const code = ts.transpileModule(fs.readFileSync(filename, 'utf8'), {
    compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022, jsx: ts.JsxEmit.ReactJSX, esModuleInterop: true }
  }).outputText;
  const module = { exports: {} };
  vm.runInNewContext(code, {
    module, exports: module.exports,
    require: name => { if (!(name in mocks)) throw new Error(`Unmocked handler dependency ${name}`); return mocks[name]; },
    window: { location, history: { state: { preserved: true }, replaceState: (_, __, url) => { state.currentUrl = new URL(url, state.currentUrl); } },
      addEventListener: (name, listener) => listeners.set(name, listener), removeEventListener: name => listeners.delete(name) }
  }, { filename });
  const render = patch => {
    Object.assign(app, patch);
    for (let pass = 0; pass < 20; pass++) {
      dirty = false; cursor = 0; effects = [];
      tree = module.exports.PartnerLinkHandler();
      effects.forEach(effect => effect());
      if (!dirty) return tree;
    }
    throw new Error('Handler render did not settle');
  };
  const nodes = (node, predicate) => {
    if (!node || typeof node !== 'object') return [];
    const children = Array.isArray(node.props?.children) ? node.props.children : [node.props?.children];
    return [...(predicate(node) ? [node] : []), ...children.flatMap(child => nodes(child, predicate))];
  };
  render();
  return {
    state, app, render,
    flush: async () => { await new Promise(setImmediate); return render(); },
    buttons: () => nodes(tree, node => node.type === 'button'),
    changeUrl: url => { state.currentUrl = new URL(url); listeners.get('popstate')?.(); return render(); },
    unmount: () => slots.forEach(slot => slot?.cleanup?.())
  };
}

test('real handler waits for profile hydration, then opens once and preserves attribution/hash', async () => {
  const fixture = handlerFixture({ ready: false });
  assert.equal(fixture.state.requests, 0);
  fixture.render({ partnerLinkReady: true });
  await fixture.flush();
  assert.equal(fixture.state.requests, 1);
  assert.equal(fixture.state.opens.length, 1);
  assert.equal(fixture.state.currentUrl.search, '?utm_source=partner');
  assert.equal(fixture.state.currentUrl.hash, '#anchor');
  fixture.render();
  await fixture.flush();
  assert.equal(fixture.state.opens.length, 1);
});

test('real handler cancels on user selection, leaving later async metadata unable to open', async () => {
  let finish;
  const fixture = handlerFixture({ resolve: () => new Promise(resolve => { finish = resolve; }) });
  fixture.render({ selected: { id: 999, title: 'User choice', mediaType: 'movie' } });
  assert.equal(fixture.state.currentUrl.search, '?utm_source=partner');
  finish({ id: 550, title: 'Partner title', mediaType: 'movie' });
  await fixture.flush();
  assert.equal(fixture.state.opens.length, 0);
});

test('real handler exposes retry/cancel, retains failed query, and retries successfully', async () => {
  let attempt = 0;
  const fixture = handlerFixture({ resolve: () => {
    if (!attempt++) throw new Error('Offline');
    return { id: 550, title: 'Film', mediaType: 'movie' };
  } });
  const errorTree = await fixture.flush();
  assert.equal(errorTree.props.role, 'alert');
  assert.equal(fixture.buttons().length, 2);
  assert.ok(fixture.state.currentUrl.search.includes('open=1'));
  fixture.buttons()[0].props.onClick();
  fixture.render();
  await fixture.flush();
  assert.equal(fixture.state.requests, 2);
  assert.equal(fixture.state.opens.length, 1);
});

test('real handler cancel and unmount invalidate requests; invalid queries never request', async () => {
  for (const action of ['cancel', 'unmount']) {
    let finish;
    const fixture = handlerFixture({ resolve: () => new Promise(resolve => { finish = resolve; }) });
    if (action === 'cancel') fixture.buttons()[0].props.onClick();
    else fixture.unmount();
    finish({ id: 550, mediaType: 'movie' });
    await fixture.flush();
    assert.equal(fixture.state.opens.length, 0, action);
  }
  const invalid = handlerFixture({ url: 'https://web.arvio.tv/?open=1&id=https://evil.invalid' });
  assert.equal(invalid.state.requests, 0);
  assert.equal(invalid.buttons().length, 1);
});

test('real handler newest location wins and a stale completion cannot remove its query', async () => {
  const pending = [];
  const fixture = handlerFixture({ resolve: () => new Promise(resolve => pending.push(resolve)) });
  fixture.changeUrl('https://web.arvio.tv/?open=1&type=movie&id=999&utm_source=new#new');
  pending[0]({ id: 550, mediaType: 'movie' });
  await fixture.flush();
  assert.equal(fixture.state.opens.length, 0);
  assert.ok(fixture.state.currentUrl.search.includes('id=999'));
  pending[1]({ id: 999, mediaType: 'movie' });
  await fixture.flush();
  assert.equal(fixture.state.opens.length, 1);
  assert.equal(fixture.state.opens[0].id, 999);
  assert.equal(fixture.state.currentUrl.search, '?utm_source=new');
  assert.equal(fixture.state.currentUrl.hash, '#new');
});
