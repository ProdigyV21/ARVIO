const test = require('node:test');
const assert = require('node:assert/strict');
// Include the static-site activation regressions in the existing web CI suite.
const { run, id } = require('../../netlify-arvio-tv-site/scripts/premium-journey.test.cjs');
test('Premium landing preserves language and carries journey only to first-party destinations', () => {
  const result = run();
  assert.equal(result.events[0].event_name, 'premium_page_view');
  assert.equal(new URL(result.links[0].href).searchParams.get('lang'), 'es-ES');
  result.activate(1);
  assert.equal(new URL(result.links[1].href).searchParams.get('arvio_journey'), id);
  assert.equal(new URL(result.links[1].href).searchParams.get('intent'), 'trial');
  assert.equal(result.links[2].href, 'https://ko-fi.com/arvio/tiers');
  assert.equal(result.links[3].href, 'https://github.com/ProdigyV21/ARVIO');
});
test('Guide navigation retains the campaign without decorating anchors, downloads or external links', () => {
  const result = run({ url: 'https://arvio.tv/arvio-web/?utm_source=reddit&utm_campaign=setup_guides',
    destinations: ['/guides/', '/premium/', '#setup', '/media-kit/arvio-media-kit.zip', '/assets/example.webp', '/go/premium/', 'https://example.org/'] });
  for (const index of [0, 1]) {
    assert.equal(new URL(result.links[index].href).searchParams.has('arvio_journey'), false);
    const url = new URL(result.activate(index));
    assert.equal(url.searchParams.get('utm_source'), 'reddit');
    assert.equal(url.searchParams.get('utm_campaign'), 'setup_guides');
    assert.equal(url.searchParams.get('arvio_journey'), id);
  }
  for (let index = 2; index < result.links.length; index++) assert.equal(new URL(result.activate(index)).searchParams.has('arvio_journey'), false);
  const next = run({ url: result.links[1].href });
  assert.equal(next.events[0].metadata.source, 'reddit');
  assert.equal(next.events[0].journey_id, id);
});
test('Homepage visits are not counted as Premium landing visits; outbound clicks are deduplicated', () => {
  const result = run({ url: 'https://arvio.tv/' });
  assert.equal(result.events.length, 0);
  result.activate(2);
  result.activate(2, { type: 'auxclick', button: 1 });
  result.activate(1, { type: 'auxclick', button: 2 });
  assert.equal(result.events.length, 1);
  assert.equal(result.events[0].event_name, 'membership_clicked');
});
test('Malicious tags and full referrer paths are not collected', () => {
  const result = run({ url: 'https://arvio.tv/premium/?utm_source=user%40example.com&arvio_journey=not-valid', referrer: 'https://example.org/private?token=secret' });
  assert.equal(result.events[0].metadata.source, 'example.org');
  assert.equal(result.events[0].journey_id, id);
  assert.ok(!JSON.stringify(result.events).includes('secret'));
});
test('DNT and GPC disable measurement and decoration', () => {
  for (const navigator of [{ doNotTrack: '1' }, { globalPrivacyControl: true }]) {
    const result = run({ navigator });
    assert.equal(result.events.length, 0);
    assert.equal(result.links[1].href, 'https://web.arvio.tv/?intent=trial');
  }
});
test('Blocked analytics never prevent membership navigation', async () => {
  const result = run({ fetchFails: true });
  result.activate(2);
  await Promise.resolve();
  assert.equal(result.links[2].href, 'https://ko-fi.com/arvio/tiers');
});
