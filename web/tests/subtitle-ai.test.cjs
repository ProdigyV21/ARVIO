const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

const plain = (value) => JSON.parse(JSON.stringify(value));

function translator(responses) {
  const requests = [];
  const queue = [...responses];
  const fetch = async (url, init) => {
    requests.push({ url, init, body: JSON.parse(init.body) });
    const next = queue.shift() ?? { status: 500, body: {} };
    return { ok: next.status >= 200 && next.status < 300, status: next.status, json: async () => next.body };
  };
  const module = load('lib/subtitleAi.ts', {}, { fetch, Math, JSON });
  const instance = new module.SubtitleTranslator('user-key-123', 'gemini', 'Dutch');
  const errors = [];
  instance.onError = (error) => errors.push(error);
  return { module, instance, requests, errors };
}

const geminiOk = (lines) => ({ status: 200, body: { candidates: [{ content: { parts: [{ thought: true, text: 'thinking…' }, { text: JSON.stringify(lines) }] } }] } });

test('own-key Gemini uses gemini-3.5-flash-lite like Android, with minimal thinking and the key in a header', async () => {
  const h = translator([geminiOk(['Hallo daar'])]);
  assert.equal(await h.instance.translate('Hello there'), 'Hallo daar');
  const [request] = h.requests;
  assert.equal(h.module.GEMINI_MODEL_ID, 'gemini-3.5-flash-lite');
  assert.equal(request.url, 'https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent');
  assert.equal(request.url.includes('user-key-123'), false, 'the key never goes in the URL');
  assert.equal(request.init.headers['x-goog-api-key'], 'user-key-123');
  assert.deepEqual(plain(request.body.generationConfig), { temperature: 0.1, responseMimeType: 'application/json', thinkingConfig: { thinkingLevel: 'minimal' } });
  assert.equal(request.body.safetySettings.length, 4);
  assert.ok(request.body.system_instruction.parts[0].text.includes('Dutch'));
  assert.match(request.body.contents[0].parts[0].text, /^Translate to Dutch:\n\["Hello there"\]$/);
  assert.equal(h.errors.length, 0);
});

test('a failed translation is reported to the viewer instead of silently showing the original', async () => {
  const h = translator([{ status: 404, body: { error: { message: 'gone' } } }]);
  assert.equal(await h.instance.translate('Hello'), 'Hello');
  assert.equal(h.errors.length, 1);
  assert.equal(h.errors[0].kind, 'model');
  assert.match(h.errors[0].message, /model is unavailable/);
  const auth = translator([{ status: 403, body: {} }]);
  await auth.instance.translate('Hi');
  assert.match(auth.errors[0].message, /rejected the API key/);
  const garbled = translator([{ status: 200, body: { candidates: [{ content: { parts: [{ text: 'not json' }] } }] } }]);
  assert.equal(await garbled.instance.translate('Hi'), 'Hi');
  assert.equal(garbled.errors[0].kind, 'response');
});

test('transient Gemini errors retry before failing, and content blocks stay quiet', async () => {
  const h = translator([{ status: 503, body: {} }, geminiOk(['Ja'])]);
  assert.equal(await h.instance.translate('Yes'), 'Ja');
  assert.equal(h.requests.length, 2);
  assert.equal(h.errors.length, 0);
  const blocked = translator([{ status: 200, body: { promptFeedback: { blockReason: 'PROHIBITED_CONTENT' } } }]);
  assert.equal(await blocked.instance.translate('Line'), 'Line');
  assert.equal(blocked.errors.length, 0, 'a content-policy block is not actionable (same as Android)');
});
