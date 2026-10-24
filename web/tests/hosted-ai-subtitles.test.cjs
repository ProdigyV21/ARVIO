const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const { SubtitleTranslator } = load('lib/subtitleAi.ts');

test('hosted translation needs no personal key and batches up to 24 lines', async () => {
  const batches = [];
  const hosted = async (lines, language) => { batches.push({ lines, language }); return lines.map((line) => `${language}:${line}`); };
  const translator = new SubtitleTranslator('', 'hosted', 'Dutch', hosted);
  const texts = Array.from({ length: 30 }, (_, i) => `line ${i}`);
  const results = await Promise.all(texts.map((text) => translator.translate(text)));
  assert.equal(results[0], 'Dutch:line 0');
  assert.equal(results[29], 'Dutch:line 29');
  assert.equal(batches[0].lines.length, 24);
  assert.equal(batches[1].lines.length, 6);
  assert.equal(await translator.translate('line 3'), 'Dutch:line 3', 'cached cue');
  assert.equal(batches.length, 2);
});

test('in-cue line breaks survive the hosted round trip', async () => {
  const translator = new SubtitleTranslator('', 'hosted', 'German', async (lines) => lines.map((line) => line.toUpperCase()));
  assert.equal(await translator.translate('come back\nnow'), 'COME BACK\nNOW');
});

test('when Premium ends, the player falls back to the original cues and stops asking', async () => {
  let calls = 0;
  const errors = [];
  const denied = Object.assign(new Error('premium_required'), { code: 'premium_required', status: 403 });
  const translator = new SubtitleTranslator('', 'hosted', 'Dutch', async () => { calls += 1; throw denied; });
  translator.onHostedError = (code) => errors.push(code);
  assert.equal(await translator.translate('Hello'), 'Hello');
  assert.equal(await translator.translate('Bye'), 'Bye');
  assert.equal(calls, 1, 'no further requests after a 403');
  assert.deepEqual(errors, ['premium_required']);
});
