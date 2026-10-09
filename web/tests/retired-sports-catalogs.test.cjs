const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const { mergeCatalogs, defaultCatalogs } = load('lib/catalogs.ts');

test('retired Android sports defaults cannot return through cloud sync', () => {
  const rows = mergeCatalogs([
    { id: 'sports', title: 'Sports', sourceType: 'PREINSTALLED', isPreinstalled: true },
    { id: 'popular_live_tv', title: 'Popular Live Sports', sourceType: 'PREINSTALLED' },
    { id: 'my-sports', title: 'Sports', sourceType: 'ADDON', addonId: 'my-addon' },
  ]);
  assert.ok(!rows.some(row => ['sports', 'popular_live_tv'].includes(row.id)));
  assert.ok(rows.some(row => row.id === 'my-sports'));
  assert.ok(defaultCatalogs.every(row => !['sports', 'popular_live_tv'].includes(row.id)));
});
