const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const { spawnSync } = require('node:child_process');
const path = require('node:path');

test('sports counts distinguish guide matches from possible broadcasts without duplicates', () => {
  const { sportsChannelSummary } = load('lib/sportsGuide.ts');
  const event = { channels: [{ id: 'a' }, { id: 'a' }], possibleChannels: [{ id: 'a' }, { id: 'b' }, { id: 'b' }],
    programme: { startUtcMillis: 10, endUtcMillis: 30 } };
  assert.equal(sportsChannelSummary(event, 20), '1 guide match · 1 possible');
  assert.equal(sportsChannelSummary({ ...event, channels: [] }, 20), '2 possible');
  assert.equal(sportsChannelSummary({ ...event, possibleChannels: [] }, 20), '1 guide match');
  assert.equal(sportsChannelSummary({ ...event, channels: [], possibleChannels: [] }, 20), 'No channels');
});

test('masked deployment values are never treated as configured client keys', () => {
  const env = { NEXT_PUBLIC_ARVIO_APP_ANON_KEY: '*'.repeat(45) + 'abcd' };
  const config = load('lib/config.ts', {}, { process: { env } });
  assert.equal(config.config.appAnonKey, '');
  assert.equal(config.hasNetlifyBackendConfig(), false);
  const valid = load('lib/config.ts', {}, { process: { env: { NEXT_PUBLIC_ARVIO_APP_ANON_KEY: 'valid'.repeat(20) } } });
  assert.equal(valid.hasNetlifyBackendConfig(), true);
});

test('production build refuses masked values before starting Next', () => {
  const result = spawnSync(process.execPath, [path.resolve(__dirname, '../scripts/build.mjs')], {
    env: { ...process.env, ARVIO_VERIFY_BUILD_CONFIG: 'true', ARVIO_BUILD_APP_ANON_KEY: '', NEXT_PUBLIC_ARVIO_APP_ANON_KEY: '*'.repeat(50) },
    encoding: 'utf8', timeout: 5000
  });
  assert.equal(result.status, 1);
  assert.match(result.stderr, /refusing production build/);
});

test('late cloud hydration keeps the profile selected on this device', () => {
  const { hydratedProfileId } = load('lib/profiles.ts');
  const profiles = [{ id: 'arvind' }, { id: 'shai' }];
  assert.equal(hydratedProfileId('arvind', profiles, 'shai'), 'arvind');
  assert.equal(hydratedProfileId('shai', profiles, 'arvind'), 'shai');
  assert.equal(hydratedProfileId('deleted', profiles, 'shai'), 'shai');
  assert.equal(hydratedProfileId(null, profiles, 'missing'), 'arvind');
  assert.equal(hydratedProfileId('deleted', [], 'missing'), null);
});

test('unhydrated settings never overwrite cloud playlists, including legacy queued defaults', async () => {
  const persisted = storage();
  const writes = [];
  const auth = { session: { userId: 'test' } };
  const outbox = load('lib/settingsOutbox.ts', { './storage': persisted, './cloud': { saveCloudSettings: async (...args) => writes.push(args) } });
  outbox.queueSettings(auth, 'new-profile', { iptvPlaylists: [] }, null);
  assert.equal(outbox.hasPendingSettings(auth), false);
  persisted.saveStored('arvio.web.settingsOutbox.v1:test', [{ id: 'legacy', profileId: 'new-profile', settings: { iptvPlaylists: [] }, baseline: null, changedAt: 1 }]);
  await outbox.flushSettingsOutbox(auth);
  assert.equal(writes.length, 0);
  assert.equal(outbox.hasPendingSettings(auth), false);
  // An explicit deletion after hydration must still sync.
  outbox.queueSettings(auth, 'new-profile', { iptvPlaylists: [] }, { iptvPlaylists: [{ id: 'one' }] });
  await outbox.flushSettingsOutbox(auth);
  assert.equal(writes.length, 1);
});
