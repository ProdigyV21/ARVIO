// A deliberately small, public-safe subset of ARVIO's collection format.
// This module has no network or storage dependencies; all exports work in Node and browsers.
export const LIMITS = Object.freeze({ folders: 8, title: 64, description: 180, share: 6000, documentBytes: 16384 });
export const THEMES = Object.freeze(['sage', 'plum', 'ocean', 'ember']);
export const PRESETS = Object.freeze([
  Object.freeze({ id: 'movie-popular', label: 'Popular movies', detail: 'Movies with at least 100 votes, ordered by popularity.', mediaType: 'MOVIE', sortBy: 'popularity.desc', filters: Object.freeze({ voteCountGte: 100 }) }),
  Object.freeze({ id: 'movie-acclaimed', label: 'Highly rated movies', detail: 'Rating 7+, at least 1,000 votes, ordered by rating.', mediaType: 'MOVIE', sortBy: 'vote_average.desc', filters: Object.freeze({ voteCountGte: 1000, voteAverageGte: 7 }) }),
  Object.freeze({ id: 'movie-scifi', label: 'Science fiction movies', detail: 'Science fiction, rating 6+, at least 100 votes.', mediaType: 'MOVIE', sortBy: 'popularity.desc', filters: Object.freeze({ withGenres: '878', voteCountGte: 100, voteAverageGte: 6 }) }),
  Object.freeze({ id: 'movie-animation', label: 'Animated movies', detail: 'Animation with at least 100 votes, ordered by popularity.', mediaType: 'MOVIE', sortBy: 'popularity.desc', filters: Object.freeze({ withGenres: '16', voteCountGte: 100 }) }),
  Object.freeze({ id: 'tv-popular', label: 'Popular shows', detail: 'TV shows with at least 100 votes, ordered by popularity.', mediaType: 'TV', sortBy: 'popularity.desc', filters: Object.freeze({ voteCountGte: 100 }) }),
  Object.freeze({ id: 'tv-acclaimed', label: 'Highly rated shows', detail: 'TV shows rated 7+, at least 500 votes, ordered by rating.', mediaType: 'TV', sortBy: 'vote_average.desc', filters: Object.freeze({ voteCountGte: 500, voteAverageGte: 7 }) })
]);

function fail(message) { throw new Error(message); }
function record(value, keys, context) {
  if (!value || typeof value !== 'object' || Array.isArray(value) || ![Object.prototype, null].includes(Object.getPrototypeOf(value))) fail(`${context} must be an object.`);
  if (Object.keys(value).some(key => !keys.includes(key))) fail(`${context} contains an unsupported field. This studio accepts only public list references and its own TMDB presets.`);
  return value;
}
function safeText(value, max, label, required = true) {
  if (typeof value !== 'string') fail(`${label} must be text.`);
  const text = value.trim().normalize('NFC');
  if (required && !text) fail(`${label} is required.`);
  if ([...text].length > max) fail(`${label} must be ${max} characters or fewer.`);
  if (/[\u0000-\u0009\u000b-\u001f\u007f\u202a-\u202e\u2066-\u2069]/u.test(text) || /[\ud800-\udfff]/u.test(text)) fail(`${label} contains unsupported control characters.`);
  if (/\b[a-z][a-z\d+.-]*:\s*\/\/|\b(?:javascript|data|file|mailto):/iu.test(text)) fail(`${label} cannot contain URLs. Add a public list in the source field.`);
  if (/\b(?:password|passwd|api[_-]?key|access[_-]?token|refresh[_-]?token|client[_-]?secret|token|secret|authorization)["']?\s*[:=]|\bbearer\s+\S+/iu.test(text)) fail(`${label} cannot contain credentials or tokens.`);
  return text;
}

export function validatePublicListUrl(value, kind) {
  if (!['mdblist', 'trakt'].includes(kind)) fail('Choose a supported public list service.');
  if (typeof value !== 'string' || value.length > 240) fail('Enter a public list URL of 240 characters or fewer.');
  const raw = value.trim();
  // Match the raw form before URL parsing: parsing can hide backslashes, encoded
  // separators, dot segments, explicit ports, credentials and empty query strings.
  const pattern = kind === 'mdblist'
    ? /^https:\/\/mdblist\.com\/lists\/([a-zA-Z0-9][a-zA-Z0-9_-]{0,63})\/([a-zA-Z0-9][a-zA-Z0-9_-]{0,99})\/?$/
    : /^https:\/\/trakt\.tv\/lists\/([1-9][0-9]{0,14})\/?$/;
  const match = raw.match(pattern);
  if (!match) fail(kind === 'mdblist'
    ? 'Use https://mdblist.com/lists/USERNAME/LIST with no query, token, port or fragment.'
    : 'Use the public numeric link https://trakt.tv/lists/12345 with no query, token, port or fragment. User/list slug links cannot be resolved here.');
  return raw.replace(/\/$/, '');
}

export function validateDraft(value) {
  const draft = record(value, ['version', 'title', 'folders'], 'Collection');
  if (draft.version !== 1) fail('This shared collection uses an unsupported version.');
  const title = safeText(draft.title, LIMITS.title, 'Home row title');
  if (!Array.isArray(draft.folders) || !draft.folders.length || draft.folders.length > LIMITS.folders) fail(`Use between 1 and ${LIMITS.folders} folders in one Home row.`);
  const ids = new Set();
  const folders = draft.folders.map((item, index) => {
    const folder = record(item, ['id', 'title', 'description', 'theme', 'source'], `Folder ${index + 1}`);
    if (typeof folder.id !== 'string' || folder.id.length > 40 || !/^[a-z][a-z0-9]*(?:-[a-z0-9]+)*$/.test(folder.id) || ids.has(folder.id)) fail('Folder IDs must be unique lowercase letters and numbers, with single hyphens between segments.');
    ids.add(folder.id);
    if (!THEMES.includes(folder.theme)) fail('Choose one of the studio preview colors.');
    const source = record(folder.source, ['kind', 'preset', 'url'], `Folder ${index + 1} source`);
    let cleanSource;
    if (source.kind === 'tmdb') {
      if ('url' in source || !PRESETS.some(preset => preset.id === source.preset)) fail('Choose one of the authored TMDB Discover presets.');
      cleanSource = { kind: 'tmdb', preset: source.preset };
    } else if (['mdblist', 'trakt'].includes(source.kind)) {
      if ('preset' in source) fail('A public list folder uses only its list URL.');
      try { cleanSource = { kind: source.kind, url: validatePublicListUrl(source.url, source.kind) }; }
      catch (error) { fail(`Folder ${index + 1}: ${error.message}`); }
    } else fail('Use an authored TMDB preset, public MDBList list or public Trakt list.');
    return { id: folder.id, title: safeText(folder.title, LIMITS.title, `Folder ${index + 1} title`),
      description: safeText(folder.description, LIMITS.description, `Folder ${index + 1} description`, false),
      theme: folder.theme, source: cleanSource };
  });
  return { version: 1, title, folders };
}

export function createDefaultDraft() {
  return { version: 1, title: 'Weekend discoveries', folders: [
    { id: 'folder-1', title: 'Cinema night', description: 'Highly rated stories for an evening in.', theme: 'sage', source: { kind: 'tmdb', preset: 'movie-acclaimed' } },
    { id: 'folder-2', title: 'One more episode', description: 'Find your next favorite show.', theme: 'plum', source: { kind: 'tmdb', preset: 'tv-popular' } },
    { id: 'folder-3', title: 'Beyond the ordinary', description: 'A little science. A lot of imagination.', theme: 'ocean', source: { kind: 'tmdb', preset: 'movie-scifi' } }
  ] };
}

export function sourceLabel(source) {
  return source.kind === 'tmdb' ? PRESETS.find(p => p.id === source.preset)?.label ?? 'TMDB preset' : source.kind === 'mdblist' ? 'Public MDBList' : 'Public Trakt';
}

export function createCollectionDocument(value) {
  const draft = validateDraft(value);
  return [{ id: 'studio-row', title: draft.title, folders: draft.folders.map(folder => {
    let source;
    if (folder.source.kind === 'tmdb') {
      const preset = PRESETS.find(p => p.id === folder.source.preset);
      source = { provider: 'tmdb', tmdbSourceType: 'DISCOVER', mediaType: preset.mediaType, sortBy: preset.sortBy, filters: { ...preset.filters } };
    } else if (folder.source.kind === 'mdblist') {
      source = { provider: 'mdblist', slug: folder.source.url.slice('https://mdblist.com/lists/'.length) };
    } else {
      source = { provider: 'trakt', traktListId: folder.source.url.slice('https://trakt.tv/lists/'.length) };
    }
    return { id: folder.id, title: folder.title, description: folder.description, tileShape: 'LANDSCAPE', sources: [source] };
  }) }];
}

export function serializeCollections(value) {
  const json = JSON.stringify(createCollectionDocument(value), null, 2);
  if (new TextEncoder().encode(json).length > LIMITS.documentBytes) fail('The collection document is too large. Shorten the titles or descriptions.');
  return json;
}

export function encodeShare(value) {
  const bytes = new TextEncoder().encode(JSON.stringify(validateDraft(value)));
  if (bytes.length > Math.floor((LIMITS.share - 8) * 3 / 4)) fail('This preview link is too long. Shorten titles or descriptions, remove folders, or share collections.json instead.');
  const token = btoa(Array.from(bytes, byte => String.fromCharCode(byte)).join('')).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
  const fragment = `#studio=${token}`;
  if (fragment.length > LIMITS.share) fail('This preview link is too long. Share collections.json instead.');
  return fragment;
}

export function decodeShare(fragment) {
  if (typeof fragment !== 'string' || !fragment.startsWith('#studio=') || fragment.length > LIMITS.share) fail('This preview link is missing, too long or unsupported.');
  const token = fragment.slice(8);
  if (!token || !/^[a-zA-Z0-9_-]+$/.test(token) || token.length % 4 === 1) fail('This preview link is incomplete or damaged.');
  try {
    const decoded = atob(token.replace(/-/g, '+').replace(/_/g, '/'));
    if (btoa(decoded).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '') !== token) fail('Invalid encoding.');
    const bytes = Uint8Array.from(decoded, char => char.charCodeAt(0));
    return validateDraft(JSON.parse(new TextDecoder('utf-8', { fatal: true }).decode(bytes)));
  } catch (error) {
    // JSON parser diagnostics may quote the untrusted payload. Do not echo it in
    // recovery text: a damaged link can contain credentials or private addresses.
    const detail = error instanceof Error && !(error instanceof SyntaxError) && !(error instanceof TypeError)
      ? error.message : 'Ask the sender for a fresh link or the collections.json file.';
    fail(`This preview link could not be read. ${detail}`);
  }
}
