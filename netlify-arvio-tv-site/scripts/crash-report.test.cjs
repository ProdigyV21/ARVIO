const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const html = fs.readFileSync(path.join(__dirname, '../report/index.html'), 'utf8');
const script = [...html.matchAll(/<script[^>]*>([\s\S]*?)<\/script>/g)]
  .map(match => match[1]).find(source => source.includes('URLSearchParams'));

function fixture(query, { clipboard, legacyCopy = false } = {}) {
  const elements = new Map();
  const timers = [];
  const element = () => ({
    textContent: '', value: '', hidden: true, style: {}, listeners: {},
    addEventListener(name, handler) { this.listeners[name] = handler; },
    focus() { this.focused = true; }, select() { this.selected = true; }, remove() {},
    classList: { add() {} },
  });
  const getElementById = id => {
    if (!elements.has(id)) elements.set(id, element());
    return elements.get(id);
  };
  vm.runInNewContext(script, {
    URLSearchParams, Date, parseInt, isNaN, encodeURIComponent,
    window: { location: { search: query } },
    navigator: { clipboard }, setTimeout: fn => timers.push(fn),
    document: { getElementById, createElement: element,
      body: { appendChild() {} }, execCommand: () => legacyCopy },
  });
  return { elements, timers };
}
const report = query => fixture(query).elements.get('report-text').textContent;

test('Firebase QR reports include the ARVIO custom-key reference, not a fake event URL', () => {
  const text = report('?provider=firebase&id=test-reference&v=2.2&err=Verification&t=1791968400000');
  assert.match(text, /project\/arvio-15c5a\/crashlytics/);
  assert.match(text, /arvio_report_id=test-reference/);
  assert.doesNotMatch(text, /sentry\.io/);
});

test('historical QR reports retain their Sentry link when provider=sentry is explicit', () => {
  const text = report('?provider=sentry&id=old-event&v=2.0');
  assert.match(text, /sentry\.io\/issues/);
  assert.doesNotMatch(text, /arvio_report_id/);
});

test('reports default to Firebase Crashlytics when provider is omitted', () => {
  const text = report('?id=default-reference&v=2.3');
  assert.match(text, /project\/arvio-15c5a\/crashlytics/);
  assert.match(text, /arvio_report_id=default-reference/);
  assert.doesNotMatch(text, /sentry\.io/);
});

test('untrusted error text is assigned as text, not HTML', () => {
  assert.match(report('?provider=firebase&err=%3Cimg%20onerror%3Dx%3E'), /<img onerror=x>/);
  assert.match(script, /preEl\.textContent = reportText/);
});

test('a blocked clipboard exposes selected manual-copy text without a false success or redirect', async () => {
  const result = fixture('?id=clipboard-failure', {
    clipboard: { writeText: async () => { throw new Error('Blocked'); } },
  });
  await result.elements.get('copy-btn').listeners.click();
  assert.equal(result.elements.get('copy-error').hidden, false);
  const manual = result.elements.get('report-manual-copy');
  assert.equal(manual.hidden, false);
  assert.equal(manual.selected, true);
  assert.match(manual.value, /clipboard-failure/);
  assert.match(result.elements.get('copy-btn').textContent, /^Couldn't copy/);
  assert.equal(result.timers.length, 0);
});

test('the legacy clipboard fallback can succeed when the modern API is blocked', async () => {
  const result = fixture('?id=legacy-copy', {
    clipboard: { writeText: async () => { throw new Error('Blocked'); } }, legacyCopy: true,
  });
  await result.elements.get('copy-btn').listeners.click();
  assert.equal(result.elements.get('copy-error').hidden, true);
  assert.match(result.elements.get('copy-btn').textContent, /^Copied!/);
  assert.equal(result.timers.length, 1);
});

test('a bare diagnostics visit and a captured crash use distinct views', () => {
  for (const [query, hasCrash] of [['', false], ['?error=Verification', true]]) {
    const result = fixture(query);
    assert.equal(result.elements.get('crash-view').style.display, hasCrash ? 'block' : 'none');
    assert.equal(result.elements.get('healthy-view').style.display, hasCrash ? 'none' : 'block');
  }
});
