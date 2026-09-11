const test = require('node:test');
const assert = require('node:assert/strict');
const { load } = require('./load.cjs');

for (const level of ['L123', 'L150', 'L153']) {
  test(`Main10 discovery accepts ${level} without promising unsupported track levels`, () => {
    const type = `video/mp4; codecs="hvc1.2.4.${level}.B0"`;
    const Mse = { isTypeSupported: (value) => value === type };
    const { getMediaCapabilities } = load('lib/capabilities.ts', {}, {
      window: { ManagedMediaSource: Mse },
      document: { createElement: () => ({ canPlayType: () => '' }) }
    });
    assert.equal(getMediaCapabilities().hevc10, true);
    assert.equal(getMediaCapabilities().dolbyVision, false);
    assert.equal(Mse.isTypeSupported('video/mp4; codecs="hvc1.2.4.L186.B0"'), false);
  });
}

test('No Main10 decoder is not inferred from 8-bit HEVC support', () => {
  const { getMediaCapabilities } = load('lib/capabilities.ts', {}, {
    window: {}, document: { createElement: () => ({
      canPlayType: (type) => type.includes('hvc1.1.6.') ? 'probably' : ''
    }) }
  });
  assert.equal(getMediaCapabilities().hevc, true);
  assert.equal(getMediaCapabilities().hevc10, false);
  assert.equal(getMediaCapabilities().mse, false);
});
