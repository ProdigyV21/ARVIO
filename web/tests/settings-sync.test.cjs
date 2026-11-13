// Settings changed on ARVIO Web must arrive on the Android/TV app in the
// values the app understands (CloudSyncRepository / CloudProfileSettings).
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load, storage } = require('./load.cjs');
const { playerSource, sourceOf, storeSource } = require('./sources.cjs');

function cloudHarness(remote) {
  const disk = storage();
  const state = { remote };
  const cloud = load('lib/cloud.ts', {
    './config': { config: { netlifyBackendUrl: 'https://backend.invalid' }, hasNetlifyBackendUrl: () => true },
    './homeserver': { serializeHomeServerConnectionJson: () => '' },
    './iptv': load('lib/iptv.ts', { './http': {}, './storage': disk }), './mediaImages': {},
    './http': { jsonRequest: async (_url, options) => {
      if (options?.method === 'POST') { state.remote = JSON.parse(options.body).payload; return { accepted: true }; }
      return { payload: structuredClone(state.remote) };
    } },
  });
  const auth = { session: { userId: 'account', accessToken: 'test' }, isNetlifySession: true, accessToken: async () => 'test' };
  return { cloud, auth, remote: () => state.remote };
}

const store = storeSource();
function webDefaults() {
  // The defaults object is plain data apart from the default catalogs import.
  const start = store.indexOf('export const defaultSettings: AppSettings = {');
  const body = store.slice(store.indexOf('{', start), store.indexOf('\n};', start) + 2);
  return Function('defaultCatalogs', `return (${body});`)([]);
}

test('AI model and accent colour sync as the names Android understands', async () => {
  const h = cloudHarness({ subtitleAiModel: 'GROQ_LLAMA_70B', accentColor: 'White', profileSettingsById: { p1: {} } });
  const base = { ...webDefaults(), aiSubtitleModel: 'groq', accentColor: 'arctic' };
  await h.cloud.saveCloudSettings(h.auth, { ...base, aiSubtitleModel: 'gemini', accentColor: 'purple' }, [], 'p1', [], base);
  assert.equal(h.remote().subtitleAiModel, 'GEMINI_FLASH_25');
  assert.equal(h.remote().accentColor, 'Violet');
  assert.equal(h.remote().focusBorderColor, 'Violet');
  const pulled = await h.cloud.pullCloudPayload(h.auth, 'p1');
  assert.equal(pulled.settings.aiSubtitleModel, 'gemini');
  assert.equal(pulled.settings.accentColor, 'purple');
  // Values written by Android, and by older web builds, read back correctly.
  assert.equal(h.cloud.webAiModel('GROQ_LLAMA_70B'), 'groq');
  assert.equal(h.cloud.webAiModel('off'), 'groq');
  assert.equal(h.cloud.webAccent('Indigo'), 'indigo');
  assert.equal(h.cloud.webAccent('Yellow'), 'gold');
  assert.equal(h.cloud.webAccent('gold'), 'gold');
  assert.equal(h.cloud.androidAccent('arctic'), 'White');
});

test('Android-only profile settings are written only once chosen on the web', async () => {
  const h = cloudHarness({ profileSettingsById: { p1: { netflixHomeStyle: false, trailerAutoPlay: true, homePresentationVersion: 0 } } });
  const base = webDefaults();
  // An unrelated change must not send defaults for the optional fields.
  await h.cloud.saveCloudSettings(h.auth, { ...base, showBudget: !base.showBudget }, [], 'p1', [], base);
  let profile = h.remote().profileSettingsById.p1;
  assert.equal(profile.netflixHomeStyle, false, 'kept the TV value');
  assert.equal('watchlistOnHome' in profile, false);
  assert.equal('autoPlayMaxQuality' in profile, false);
  assert.equal(profile.homePresentationVersion, 0);
  // Choosing them on the web sends them, with Android's vocabulary.
  const next = { ...base, netflixHomeStyle: true, autoPlayMaxQuality: '1080p', autoPlayMaxSizeGb: 15, guideRowCount: 8,
    streamSearchMode: 'sequential', animeEpisodeStructuring: 'standard', subtitleFont: 'Lexend', useForcedSubtitles: true, subtitleSize: 170 };
  await h.cloud.saveCloudSettings(h.auth, next, [], 'p1', [], base);
  profile = h.remote().profileSettingsById.p1;
  assert.equal(profile.netflixHomeStyle, true);
  assert.equal(profile.homePresentationVersion, 1, 'Android only applies home presentation fields from version 1');
  assert.equal(profile.autoPlayMaxQuality, '1080p');
  assert.equal(profile.autoPlayMaxSizeGb, 15);
  assert.equal(profile.guideRowCount, 8);
  assert.equal(profile.streamSearchMode, 'sequential');
  assert.equal(profile.animeEpisodeStructuring, 'standard');
  assert.equal(profile.subtitleFont, 'Lexend');
  assert.equal(profile.useForcedSubtitles, true);
  assert.equal(profile.subtitleSize, 'Extra Large');
  assert.ok(h.remote().fieldUpdatedAt['p:p1:netflixHomeStyle'] > 0, 'changes carry a merge timestamp');
  const pulled = (await h.cloud.pullCloudPayload(h.auth, 'p1')).settings;
  assert.equal(pulled.netflixHomeStyle, true);
  assert.equal(pulled.autoPlayMaxQuality, '1080p');
  assert.equal(pulled.guideRowCount, 8);
  assert.equal(pulled.subtitleFont, 'Lexend');
  assert.equal(pulled.streamSearchMode, 'sequential');
});

test('web saves preserve the TV-only addon details setting for every profile', async () => {
  const h = cloudHarness({ profileSettingsById: { p1: { showAddonStreamDetails: true }, p2: { showAddonStreamDetails: false } } });
  const base = webDefaults();
  await h.cloud.saveCloudSettings(h.auth, { ...base, showBudget: !base.showBudget }, [], 'p1', [], base);
  assert.equal(h.remote().profileSettingsById.p1.showAddonStreamDetails, true);
  assert.equal(h.remote().profileSettingsById.p2.showAddonStreamDetails, false);
  await h.cloud.saveCloudSettings(h.auth, base, [], 'p2', []);
  assert.equal(h.remote().profileSettingsById.p1.showAddonStreamDetails, true);
  assert.equal(h.remote().profileSettingsById.p2.showAddonStreamDetails, false);
});

test('account-wide Android toggles round-trip through the root of the payload', async () => {
  const h = cloudHarness({ subtitleAiEnabled: false, subtitlePreloadEnabled: true, dolbyVisionCompatEnabled: true, telegramSearchOnClickOnly: true, profileSettingsById: { p1: {} } });
  const base = webDefaults();
  await h.cloud.saveCloudSettings(h.auth, { ...base, aiFindBestMatch: true, subtitlePreload: false, dolbyVisionCompat: false, telegramSearchOnClickOnly: false }, [], 'p1', [], base);
  const root = h.remote();
  assert.equal(root.subtitleAiFindBestMatch, true);
  assert.equal(root.subtitlePreloadEnabled, false);
  assert.equal(root.dolbyVisionCompatEnabled, false);
  assert.equal(root.telegramSearchOnClickOnly, false);
  assert.ok(root.fieldUpdatedAt['g:subtitlePreloadEnabled'] > 0);
  const pulled = (await h.cloud.pullCloudPayload(h.auth, 'p1')).settings;
  assert.equal(pulled.aiFindBestMatch, true);
  assert.equal(pulled.subtitlePreload, false);
  assert.equal(pulled.dolbyVisionCompat, false);
  assert.equal(pulled.telegramSearchOnClickOnly, false);
});

test('track languages match Android names, codes and labels', () => {
  const l = load('lib/languages.ts');
  for (const [value, code] of [['English', 'en'], ['eng', 'en'], ['en-US', 'en'], ['Portuguese (Brazil)', 'pt-br'], ['pt_BR', 'pt-br'],
    ['Hebrew', 'he'], ['heb', 'he'], ['Dutch', 'nl'], ['dut', 'nl'], ['Off', ''], ['', ''], ['Auto (Original)', ''], ['Forced', '']]) {
    assert.equal(l.languageCode(value), code, value);
  }
  assert.equal(l.trackLanguageName('nl'), 'Dutch');
  assert.equal(l.trackLanguageName('pt-BR'), 'Portuguese (Brazil)');
  assert.equal(l.trackLanguageName('Off'), '');
  assert.ok(l.sameLanguage('English', 'eng'));
  assert.ok(l.sameLanguage('pt', 'Portuguese (Brazil)'));
  assert.ok(!l.sameLanguage('English', 'Dutch'));
  for (const name of l.TRACK_LANGUAGES) assert.notEqual(l.languageCode(name), name.toLowerCase(), `${name} has a code`);
});

test('every searchable setting points at a section and a row that exist', () => {
  const dir = path.join(__dirname, '../components/settings');
  const sources = ['SettingsScreen.tsx', ...fs.readdirSync(path.join(dir, 'sections')).map((f) => `sections/${f}`)]
    .map((f) => fs.readFileSync(path.join(dir, f), 'utf8')).join('\n');
  const index = fs.readFileSync(path.join(dir, 'settingsIndex.ts'), 'utf8');
  const sections = [...sources.matchAll(/\{ id: "([a-z]+)", group:/g)].map((m) => m[1]);
  assert.ok(sections.length >= 15);
  for (const [, section] of index.matchAll(/section: "([a-z]+)"/g)) assert.ok(sections.includes(section), section);
  for (const [, id] of index.matchAll(/ id: "([a-z0-9-]+)"/g)) assert.ok(sources.includes(`id="${id}"`), `row ${id}`);
});

test('the player matches Android-style subtitle names against track codes', () => {
  const player = playerSource();
  assert.match(player, /const desired = languageCode\(language\);/);
  assert.match(player, /languageCode\(settings\.defaultSubtitle\) !== "en"/);
  assert.doesNotMatch(player, /aiSubtitleModel !== "off"/);
});

test('longer language names win, and audio preferences match Android names', () => {
  const l = load('lib/languages.ts');
  assert.equal(l.languageCode('Malayalam (SDH)'), 'ml');
  assert.equal(l.languageCode('Malay'), 'ms');
  const remux = load('lib/remuxProtocol.ts');
  const track = (index, language) => ({ index, codec: 'aac', label: language, language, browserPlayable: true, passthrough: true });
  const tracks = [track(0, 'eng'), track(1, 'tur'), track(2, 'pol'), track(3, 'por')];
  assert.equal(remux.preferredAudioIndex(tracks, 'Turkish'), 1);
  assert.equal(remux.preferredAudioIndex(tracks, 'Polish'), 2);
  assert.equal(remux.preferredAudioIndex(tracks, 'Portuguese (Brazil)'), 3);
  assert.equal(remux.preferredAudioIndex(tracks, 'English'), 0);
  assert.equal(remux.preferredAudioIndex(tracks, ''), 0);
});

test('values older web builds wrote are rewritten for Android on the next save', async () => {
  const h = cloudHarness({ subtitleAiModel: 'gemini', accentColor: 'purple', profileSettingsById: { p1: {} } });
  const base = { ...webDefaults(), aiSubtitleModel: 'gemini', accentColor: 'purple' };
  await h.cloud.saveCloudSettings(h.auth, { ...base, showBudget: !base.showBudget }, [], 'p1', [], base);
  assert.equal(h.remote().subtitleAiModel, 'GEMINI_FLASH_25');
  assert.equal(h.remote().accentColor, 'Violet');
  assert.ok(h.remote().fieldUpdatedAt['g:subtitleAiModel'] > 0);
});

test('returning from a settings section does not leave the app history stuck', () => {
  const back = fs.readFileSync(path.join(__dirname, '../components/shell/BackHandler.tsx'), 'utf8');
  const guard = back.indexOf("route.section===current.section&&route.query===shownQuery)return;");
  assert.ok(guard > 0, 'a pop to the route already shown is ignored');
  assert.ok(guard < back.indexOf('applying.current=route;'), 'before it is recorded as pending');
  const ai = playerSource();
  assert.match(ai, /Boolean\(languageCode\(settings\.defaultSubtitle\)\) && languageCode\(settings\.defaultSubtitle\) !== "en"/, '"Forced" is not a translation target');
});
