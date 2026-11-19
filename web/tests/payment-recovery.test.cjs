const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { createPaymentRecovery } = load('lib/paymentRecovery.ts');
const trial = { entitled: true, reason: 'trial' };
const paid = { entitled: true, reason: 'subscription' };
function fixture(check = async () => trial, activeWhen) {
  let current = true, visible = true, calls = 0;
  const timers = new Map(), results = [];
  const controller = createPaymentRecovery({ check: async () => { calls++; return check(); }, isCurrent: () => current, isVisible: () => visible,
    activeWhen,
    onResult: (status, state) => results.push({ status, state }),
    schedule: (callback, delay) => { const key = timers.size + 1; timers.set(key, { callback, delay }); return key; },
    cancel: key => timers.delete(key)
  });
  return { controller, timers, results, calls: () => calls, setCurrent: x => current = x, setVisible: x => visible = x,
    next: async () => { const [id, item] = [...timers][0]; timers.delete(id); item.callback(); await new Promise(resolve => setImmediate(resolve)); } };
}
test('return polling stops after four checks; an active trial is not payment', async () => {
  const f = fixture(); await f.controller.resume();
  while (f.timers.size) await f.next();
  await f.controller.resume();
  assert.equal(f.calls(), 4);
  assert.equal(f.results.some(x => x.status === 'active'), false);
  assert.equal(f.results.at(-1).status, 'pending');
});
test('a delayed server-confirmed payment activates once and stops polling', async () => {
  let n = 0; const f = fixture(async () => ++n === 2 ? paid : trial);
  await f.controller.resume(); await f.next(); await f.controller.resume();
  assert.equal(f.calls(), 2); assert.equal(f.timers.size, 0);
  assert.equal(f.results.filter(x => x.status === 'active').length, 1);
});
test('an existing monthly membership keeps annual upgrade recovery running', async () => {
  let state = { ...paid, plan: 'monthly' };
  const f = fixture(async () => state, next => next.plan === 'yearly');
  await f.controller.resume();
  assert.equal(f.results.at(-1).status, 'pending');
  assert.equal(f.timers.size, 1);
  state = { ...paid, plan: 'yearly' };
  await f.next();
  assert.equal(f.results.at(-1).status, 'active');
  assert.equal(f.timers.size, 0);
});
test('hidden tabs do not check; visibility resumes the bounded controller', async () => {
  const f = fixture(); f.setVisible(false); await f.controller.resume(); assert.equal(f.calls(), 0);
  f.setVisible(true); await f.controller.resume(); f.setVisible(false); await f.next();
  assert.equal(f.calls(), 1); assert.equal(f.timers.size, 0);
  f.setVisible(true); await f.controller.resume(); assert.equal(f.calls(), 2); f.controller.stop();
});
test('late responses after switching accounts or closing cannot change access', async () => {
  for (const cancel of ['account', 'stop']) {
    let finish; const f = fixture(() => new Promise(resolve => { finish = resolve; }));
    const pending = f.controller.resume();
    if (cancel === 'account') f.setCurrent(false); else f.controller.stop();
    finish(paid); await pending;
    assert.deepEqual(f.results.map(x => x.status), ['checking']); assert.equal(f.timers.size, 0);
  }
});
test('simultaneous focus/manual checks coalesce and transient errors can recover', async () => {
  let finish; const f = fixture(() => new Promise(resolve => { finish = resolve; }));
  const pending = f.controller.resume(); await f.controller.resume(); await f.controller.retry();
  assert.equal(f.calls(), 1); finish(paid); await pending;
  const e = fixture(async () => { throw Error('offline'); }); await e.controller.resume();
  assert.equal(e.results.at(-1).status, 'error'); e.controller.stop(); assert.equal(e.timers.size, 0);
});
test('manual retry starts a new bounded cycle after propagation delays', async () => {
  const f = fixture(); await f.controller.resume(); while(f.timers.size) await f.next();
  await f.controller.retry(); assert.equal(f.calls(), 5); f.controller.stop();
});
