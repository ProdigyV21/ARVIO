const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const html = fs.readFileSync(path.join(__dirname, '../report/index.html'), 'utf8');
const script = [...html.matchAll(/<script[^>]*>([\s\S]*?)<\/script>/g)]
  .map(match => match[1]).find(source => source.includes('URLSearchParams'));

function report(query) {
  const element = { textContent: '', addEventListener() {} };
  vm.runInNewContext(script, {
    URLSearchParams, Date, parseInt, isNaN, encodeURIComponent,
    window: { location: { search: query } },
    document: { getElementById: () => element }
  });
  return element.textContent;
}

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
