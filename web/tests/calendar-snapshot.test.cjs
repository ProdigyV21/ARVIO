const test = require('node:test');
const assert = require('node:assert/strict');
const { load, storage } = require('./load.cjs');
const calendar = load('lib/calendar.ts');
const release = { id: 'movie:1:cinema:2026-10-16', date: '2026-10-16', kind: 'cinema', item: { id: 1, mediaType: 'movie', title: 'A movie' }, sources: ['arvio', 'trakt'] };
const complete = { releases: [release], failedSources: [], pendingSources: [], failedTitles: 0, titleCount: 1, sourceTitleCounts: { arvio: 1, trakt: 1 } };
const json = value => JSON.parse(JSON.stringify(value));

test('complete calendar snapshots restore synchronously and remain isolated by account/profile/month key', () => {
  const local = storage();
  const module = load('lib/calendarSnapshot.ts', { './storage': local, './calendar': calendar });
  module.saveCalendarSnapshot('account-a:profile-a:October', complete);
  assert.deepEqual(json(module.readCalendarSnapshot('account-a:profile-a:October')), complete);
  assert.equal(module.readCalendarSnapshot('account-b:profile-a:October'), null);
  assert.equal(module.readCalendarSnapshot('account-a:profile-b:October'), null);
  assert.equal(module.readCalendarSnapshot('account-a:profile-a:November'), null);
  module.saveCalendarSnapshot('account-a:profile-a:October', { ...complete, releases: [], failedSources: ['trakt'] });
  assert.equal(module.readCalendarSnapshot('account-a:profile-a:October').releases.length, 1, 'Failed refresh keeps complete cache');
  module.saveCalendarSnapshot('account-a:profile-a:October', { ...complete, releases: [], sourceTitleCounts: { arvio: 0, trakt: 0 } });
  assert.equal(module.readCalendarSnapshot('account-a:profile-a:October').releases.length, 0, 'Healthy empty refresh removes stale releases');
});

test('calendar refresh retains only unfinished or failed source memberships and reconciles deletions', () => {
  const module = load('lib/calendarSnapshot.ts', { './storage': storage(), './calendar': calendar });
  const waiting = module.mergeCalendarRefresh(complete, { ...complete, releases: [], pendingSources: ['trakt'], sourceTitleCounts: { arvio: 0 } });
  assert.deepEqual(json(waiting.releases[0].sources), ['trakt'], 'ARVIO removal is already authoritative');
  assert.equal(module.mergeCalendarRefresh(complete, { ...complete, releases: [] }).releases.length, 0);
  const updated = module.mergeCalendarRefresh(complete, { ...complete, releases: [{ ...release, sources: ['arvio'] }], pendingSources: ['trakt'] });
  assert.deepEqual(json(updated.releases[0].sources), ['arvio', 'trakt']);
  assert.deepEqual(release.sources, ['arvio', 'trakt'], 'Inputs stay unchanged');
});

test('expired calendar snapshots are ignored and retained month cache stays bounded', () => {
  const local = storage();
  local.saveStored('arvio.web.calendarSnapshots.v1', [{ key: 'old', at: Date.now() - 25 * 60 * 60_000, value: complete }]);
  const module = load('lib/calendarSnapshot.ts', { './storage': local, './calendar': calendar });
  assert.equal(module.readCalendarSnapshot('old'), null);
  local.saveStored('arvio.web.calendarSnapshots.v1', [{ key: 'future', at: Date.now() + 60_000, value: complete }]);
  assert.equal(module.readCalendarSnapshot('future'), null);
  for (let i = 0; i < 8; i++) module.saveCalendarSnapshot(`month-${i}`, complete);
  assert.equal(local.values.get('arvio.web.calendarSnapshots.v1').length, 6);
  assert.equal(module.readCalendarSnapshot('month-0'), null);
});

test('a failed provider cannot resurrect another provider\'s confirmed removal on the next open', () => {
  const local = storage();
  const module = load('lib/calendarSnapshot.ts', { './storage': local, './calendar': calendar });
  const simklRelease = { ...release, id: 'movie:2:cinema:2026-10-16', item: { ...release.item, id: 2 }, sources: ['simkl'] };
  module.saveCalendarSnapshot('profile', { ...complete, releases: [{ ...release, sources: ['trakt'] }, simklRelease] });
  module.saveCalendarSnapshot('profile', { ...complete, releases: [], failedSources: ['simkl'], sourceTitleIds: { trakt: [] }, sourceTitleCounts: { trakt: 0 } });
  const reopened = module.readCalendarSnapshot('profile');
  assert.deepEqual(json(reopened.releases.map(row => row.item.id)), [2]);
  assert.deepEqual(json(reopened.releases[0].sources), ['simkl']);
});

test('failed title metadata retains only current provider memberships', () => {
  const module = load('lib/calendarSnapshot.ts', { './storage': storage(), './calendar': calendar });
  const result = module.mergeCalendarRefresh(complete, { ...complete, releases: [], failedTitles: 1, failedTitleKeys: ['movie:1'], sourceTitleIds: { arvio: [], trakt: ['movie:1'] } });
  assert.deepEqual(json(result.releases[0].sources), ['trakt']);
});

test('provider connection fingerprint partitions account replacements without persisting credentials', () => {
  const module = load('lib/calendarSnapshot.ts', { './storage': storage(), './calendar': calendar });
  const first = module.calendarConnectionFingerprint(['private-token-a', null, 'key-a']);
  assert.equal(first, module.calendarConnectionFingerprint(['private-token-a', null, 'key-a']));
  assert.notEqual(first, module.calendarConnectionFingerprint(['private-token-b', null, 'key-a']));
  assert.notEqual(first, module.calendarConnectionFingerprint(['private-token-a', null, 'key-b']));
  assert.equal(first.includes('private-token'), false);
});

test('repeated provider outages do not extend the original cached fallback expiry', () => {
  let now = Date.now();
  class Clock extends Date { static now() { return now; } }
  const local = storage();
  const module = load('lib/calendarSnapshot.ts', { './storage': local, './calendar': calendar }, { Date: Clock });
  module.saveCalendarSnapshot('profile', complete);
  now += 23 * 60 * 60_000;
  module.saveCalendarSnapshot('profile', { ...complete, releases: [], failedSources: ['trakt'] });
  assert.equal(module.readCalendarSnapshot('profile').releases.length, 1);
  now += 2 * 60 * 60_000;
  assert.equal(module.readCalendarSnapshot('profile'), null);
});
