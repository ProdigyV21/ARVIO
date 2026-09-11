const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load } = require('./load.cjs');

test('Telegram configuration accepts valid build environment credentials', () => {
  const config = load('lib/telegram/config.ts', {}, { process: { env: {
    NEXT_PUBLIC_TELEGRAM_API_ID: '123456',
    NEXT_PUBLIC_TELEGRAM_API_HASH: 'test-app-hash',
  } } });
  assert.equal(config.TELEGRAM_API_ID, 123456);
  assert.equal(config.TELEGRAM_API_HASH, 'test-app-hash');
  assert.equal(config.isTelegramConfigured, true);
});

test('missing or empty Telegram build variables resolve to unconfigured defaults', () => {
  const configEmpty = load('lib/telegram/config.ts', {}, { process: { env: {
    NEXT_PUBLIC_TELEGRAM_API_ID: '',
    NEXT_PUBLIC_TELEGRAM_API_HASH: '',
  } } });
  assert.equal(configEmpty.TELEGRAM_API_ID, 0);
  assert.equal(configEmpty.TELEGRAM_API_HASH, '');
  assert.equal(configEmpty.isTelegramConfigured, false);

  const configUndefined = load('lib/telegram/config.ts', {}, { process: { env: {} } });
  assert.equal(configUndefined.TELEGRAM_API_ID, 0);
  assert.equal(configUndefined.TELEGRAM_API_HASH, '');
  assert.equal(configUndefined.isTelegramConfigured, false);
});

test('placeholder template values resolve to unconfigured', () => {
  const config = load('lib/telegram/config.ts', {}, { process: { env: {
    NEXT_PUBLIC_TELEGRAM_API_ID: 'your-telegram-api-id',
    NEXT_PUBLIC_TELEGRAM_API_HASH: 'your-telegram-api-hash',
  } } });
  assert.equal(config.TELEGRAM_API_ID, 0);
  assert.equal(config.TELEGRAM_API_HASH, '');
  assert.equal(config.isTelegramConfigured, false);
});

test('malformed Telegram build variables are safely sanitized to unconfigured', () => {
  const malformedInputs = [
    { id: 'not-a-number', hash: 'valid-hash' },
    { id: '-12345', hash: 'valid-hash' },
    { id: '0', hash: 'valid-hash' },
    { id: '12.34', hash: 'valid-hash' },
    { id: '123456', hash: '   ' },
    { id: 'disabled', hash: 'disabled' },
  ];

  for (const { id, hash } of malformedInputs) {
    const config = load('lib/telegram/config.ts', {}, { process: { env: {
      NEXT_PUBLIC_TELEGRAM_API_ID: id,
      NEXT_PUBLIC_TELEGRAM_API_HASH: hash,
    } } });
    assert.equal(config.isTelegramConfigured, false, `Expected unconfigured for id="${id}", hash="${hash}"`);
  }
});

test('sanitizeTelegramApiId and sanitizeTelegramApiHash edge cases', () => {
  const config = load('lib/telegram/config.ts');
  const { sanitizeTelegramApiId, sanitizeTelegramApiHash, isTelegramCredentialsConfigured } = config;

  assert.equal(sanitizeTelegramApiId(null), 0);
  assert.equal(sanitizeTelegramApiId(undefined), 0);
  assert.equal(sanitizeTelegramApiId(''), 0);
  assert.equal(sanitizeTelegramApiId('   '), 0);
  assert.equal(sanitizeTelegramApiId('your-key'), 0);
  assert.equal(sanitizeTelegramApiId('disabled'), 0);
  assert.equal(sanitizeTelegramApiId('abc'), 0);
  assert.equal(sanitizeTelegramApiId('-50'), 0);
  assert.equal(sanitizeTelegramApiId(0), 0);
  assert.equal(sanitizeTelegramApiId('987654'), 987654);
  assert.equal(sanitizeTelegramApiId(987654), 987654);

  assert.equal(sanitizeTelegramApiHash(null), '');
  assert.equal(sanitizeTelegramApiHash(undefined), '');
  assert.equal(sanitizeTelegramApiHash(''), '');
  assert.equal(sanitizeTelegramApiHash('   '), '');
  assert.equal(sanitizeTelegramApiHash('your-hash'), '');
  assert.equal(sanitizeTelegramApiHash('disabled'), '');
  assert.equal(sanitizeTelegramApiHash('  abc123hash  '), 'abc123hash');

  assert.equal(isTelegramCredentialsConfigured(0, 'valid-hash'), false);
  assert.equal(isTelegramCredentialsConfigured(123456, ''), false);
  assert.equal(isTelegramCredentialsConfigured(0, ''), false);
  assert.equal(isTelegramCredentialsConfigured(123456, 'valid-hash'), true);
});

test('web deployment maps the repository Telegram secrets to the browser build names', () => {
  const workflow = fs.readFileSync(path.resolve(__dirname, '../../.github/workflows/deploy-web.yml'), 'utf8');
  const buildStep = workflow.split('- name: Build Netlify bundle')[1]?.split('- name:')[0];
  assert.ok(buildStep, 'Missing web build step');
  for (const key of ['TELEGRAM_API_ID', 'TELEGRAM_API_HASH']) {
    assert.ok(buildStep.includes('NEXT_PUBLIC_' + key + ': ${{ secrets.' + key + ' }}'));
  }
});

