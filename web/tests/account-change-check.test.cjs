const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const { createAccountChangeCheck } = load('lib/accountChange.ts');

function fixture(reads, { active = true } = {}) {
  const snapshot = { current: null };
  const refreshes = [];
  let index = 0;
  const check = createAccountChangeCheck({
    key: 'user:p1',
    snapshot,
    isActive: () => active,
    read: async () => { const next = reads[Math.min(index, reads.length - 1)]; index += 1; if (next instanceof Error) throw next; return next; },
    refresh: async () => { refreshes.push(snapshot.current?.revision); }
  });
  return { check, snapshot, refreshes };
}

test('the first read only sets the baseline and never refreshes', async () => {
  const f = fixture(['r1']);
  await f.check.check();
  assert.equal(JSON.stringify(f.snapshot.current), JSON.stringify({ key: 'user:p1', revision: 'r1' }));
  assert.equal(f.refreshes.length, 0);
});

test('an unchanged revision costs no refresh', async () => {
  const f = fixture(['r1', 'r1', 'r1']);
  for (let i = 0; i < 3; i++) await f.check.check();
  assert.equal(f.refreshes.length, 0);
});

test('a new revision refreshes once, and the refreshed revision becomes the baseline', async () => {
  const f = fixture(['r1', 'r2', 'r2']);
  await f.check.check();
  await f.check.check();
  await f.check.check();
  assert.deepEqual(f.refreshes, ['r2']);
  assert.equal(f.snapshot.current.revision, 'r2');
});

test('a failed or empty read never counts as a change and keeps the baseline', async () => {
  const f = fixture(['r1', new Error('offline'), null, 'r1']);
  for (let i = 0; i < 4; i++) await f.check.check();
  assert.equal(f.refreshes.length, 0);
  assert.equal(f.snapshot.current.revision, 'r1');
});

test('nothing is read while the page is hidden, playing or already refreshing', async () => {
  let reads = 0;
  const check = createAccountChangeCheck({
    key: 'user:p1', snapshot: { current: null }, isActive: () => false,
    read: async () => { reads += 1; return 'r1'; }, refresh: async () => undefined
  });
  await check.check();
  assert.equal(reads, 0);
});

test('a different account or profile starts a new baseline instead of refreshing', async () => {
  const snapshot = { current: { key: 'user:other', revision: 'old' } };
  const refreshes = [];
  const check = createAccountChangeCheck({
    key: 'user:p1', snapshot, isActive: () => true,
    read: async () => 'r9', refresh: async () => { refreshes.push(1); }
  });
  await check.check();
  assert.equal(refreshes.length, 0);
  assert.equal(JSON.stringify(snapshot.current), JSON.stringify({ key: 'user:p1', revision: 'r9' }));
});

test('a refresh that keeps failing for the same revision is not retried in a loop', async () => {
  const snapshot = { current: { key: 'user:p1', revision: 'r1' } };
  let refreshes = 0;
  const check = createAccountChangeCheck({
    key: 'user:p1', snapshot, isActive: () => true,
    read: async () => 'r2',
    refresh: async () => { refreshes += 1; snapshot.current = { key: 'user:p1', revision: 'r1' }; throw new Error('partial outage'); }
  });
  await check.check();
  await check.check();
  await check.check();
  assert.equal(refreshes, 1);
});

test('a save that lands during the refresh is noticed on the next check', async () => {
  const f = fixture(['r1', 'r2', 'r3']);
  await f.check.check();
  await f.check.check();
  await f.check.check();
  assert.deepEqual(f.refreshes, ['r2', 'r3']);
});
