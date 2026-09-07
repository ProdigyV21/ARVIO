const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load } = require('./load.cjs');

test('Telegram configuration accepts the public build environment credentials', () => {
  const config = load('lib/telegram/config.ts', {}, { process: { env: {
    NEXT_PUBLIC_TELEGRAM_API_ID: '123456',
    NEXT_PUBLIC_TELEGRAM_API_HASH: 'test-app-hash',
  } } });
  assert.equal(config.TELEGRAM_API_ID, 123456);
  assert.equal(config.TELEGRAM_API_HASH, 'test-app-hash');
});

test('empty optional Telegram build variables preserve the default credentials', () => {
  const defaults = load('lib/telegram/config.ts');
  const config = load('lib/telegram/config.ts', {}, { process: { env: {
    NEXT_PUBLIC_TELEGRAM_API_ID: '',
    NEXT_PUBLIC_TELEGRAM_API_HASH: '',
  } } });
  assert.ok(Number.isInteger(defaults.TELEGRAM_API_ID) && defaults.TELEGRAM_API_ID > 0);
  assert.ok(defaults.TELEGRAM_API_HASH.length > 0);
  assert.equal(config.TELEGRAM_API_ID, defaults.TELEGRAM_API_ID);
  assert.equal(config.TELEGRAM_API_HASH, defaults.TELEGRAM_API_HASH);
});

test('web deployment maps the repository Telegram secrets to the browser build names', () => {
  const workflow = fs.readFileSync(path.resolve(__dirname, '../../.github/workflows/deploy-web.yml'), 'utf8');
  const buildStep = workflow.split('- name: Build Netlify bundle')[1]?.split('- name:')[0];
  assert.ok(buildStep, 'Missing web build step');
  for (const key of ['TELEGRAM_API_ID', 'TELEGRAM_API_HASH']) {
    assert.ok(buildStep.includes('NEXT_PUBLIC_' + key + ': ${{ secrets.' + key + ' }}'));
  }
});
