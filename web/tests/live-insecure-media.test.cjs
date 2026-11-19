// Live TV channels whose video comes from an http:// IP address: blocked by the
// browser (mixed content) and unreachable for the Cloudflare relay (error 1003).
// The player must explain how to allow it, not blame the subscription.
const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');
const live = load('lib/liveTvPlayback.ts');

const attempt = { variant: 'original', route: 'relay', transport: 'hls', url: 'x' };
const relaySeg = 'https://resolve.arvio.tv/media?url=' + encodeURIComponent('http://185.245.1.253/hls/abc/1_2.ts') + '&h=e30';
const ctx = { browser: 'Chrome', insecureSource: false, relayAvailable: true, relayTicket: true, played: false };

test('detects http:// IP-address media, also behind the relay or proxy', () => {
  assert.equal(live.isInsecureIpMedia('http://185.245.1.253/hls/abc/1_2.ts'), true);
  assert.equal(live.isInsecureIpMedia(relaySeg), true);
  assert.equal(live.isInsecureIpMedia('https://web.arvio.tv/api/proxy?url=' + encodeURIComponent('http://10.0.0.5:8080/a.ts')), true);
  assert.equal(live.isInsecureIpMedia('http://provider.example/a.ts'), false, 'hostnames reach the relay');
  assert.equal(live.isInsecureIpMedia('https://185.245.1.253/a.ts'), false, 'https is not mixed content');
  assert.equal(live.isInsecureIpMedia(undefined), false);
});

test('a relay 403 for such a segment shows the allow-insecure steps, not "subscription expired"', () => {
  const faults = [{ attempt, fault: { transport: 'hls', kind: 'network', fatal: true, retryable: false, message: '', httpStatus: 403, resourceUrl: relaySeg } }];
  const failure = live.classifyLiveFailure(faults, ctx);
  assert.equal(failure.kind, 'insecure-media');
  assert.match(failure.message, /Site settings, set Insecure content to Allow/);
  assert.match(failure.message, /Firefox/);
  assert.equal(live.providerRefusal(faults, ctx), null, 'not treated as the provider refusing');
});

test('Safari and iPhone/iPad get the honest "no setting" version', () => {
  const faults = [{ attempt, fault: { transport: 'hls', kind: 'network', fatal: true, retryable: false, message: '', resourceUrl: 'http://185.245.1.253/x.ts' } }];
  const failure = live.classifyLiveFailure(faults, { ...ctx, browser: 'Safari', appleWebKit: true });
  assert.equal(failure.kind, 'insecure-media');
  assert.match(failure.message, /no setting to allow it/);
});
