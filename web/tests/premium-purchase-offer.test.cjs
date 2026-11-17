const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const policy = load('lib/entitlementPolicy.ts');
const { premiumPurchaseOffer } = load('lib/premiumPurchaseOffer.ts', { './entitlementPolicy': policy });
const now = Date.UTC(2026, 10, 17);
const paid = { entitled: true, reason: 'subscription', source: 'kofi', plan: 'yearly', expiresAt: '2027-11-17T00:00:00Z' };

test('selected monthly and annual offers keep their exact total and renewal terms', () => {
  for (const regional of [false, true]) {
    const monthly = premiumPurchaseOffer('monthly', regional, null, now);
    assert.equal(monthly.total, '$2.99');
    assert.equal(monthly.period, '/ month');
    assert.equal(monthly.saving, null);
  }
  assert.equal(premiumPurchaseOffer('yearly', false, null, now).total, '$20');
  const regional = premiumPurchaseOffer('yearly', true, null, now);
  assert.equal(regional.total, '$10');
  assert.equal(regional.period, '/ 12 months');
  assert.equal(regional.saving, '72%');
  assert.equal(Math.round((1 - 10 / (12 * 2.99)) * 100), Number(regional.saving.slice(0, -1)));
});

test('any existing valid access prevents a duplicate purchase, irrespective of the selected plan', () => {
  for (const plan of ['monthly', 'yearly']) for (const reason of ['subscription', 'grace', 'partner']) {
    assert.equal(premiumPurchaseOffer(plan, false, { ...paid, reason }, now).active, true);
  }
  assert.equal(premiumPurchaseOffer('monthly', false, { ...paid, plan: 'monthly' }, now).active, true);
  assert.equal(premiumPurchaseOffer('yearly', false, { ...paid, plan: 'monthly' }, now).active, true);
});

test('a plan query, expired access and legacy trials cannot grant Premium', () => {
  assert.equal(premiumPurchaseOffer('yearly', true, null, now).active, false);
  assert.equal(premiumPurchaseOffer('yearly', true, { ...paid, entitled: false }, now).active, false);
  assert.equal(premiumPurchaseOffer('yearly', true, { ...paid, expiresAt: '2026-11-16T00:00:00Z' }, now).active, false);
  assert.equal(premiumPurchaseOffer('yearly', true, { ...paid, reason: 'trial' }, now).active, false);
  assert.equal(premiumPurchaseOffer('yearly', true, { ...paid, source: 'trial' }, now).active, false);
});
