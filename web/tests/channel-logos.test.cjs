const test = require('node:test');
const assert = require('node:assert/strict');
const ts = require('typescript');
const fs = require('node:fs');
const vm = require('node:vm');
const sandbox = { exports: {}, Date, Map, Set, atob };
vm.runInNewContext(ts.transpileModule(fs.readFileSync(require.resolve('../lib/channelLogos.ts'), 'utf8'), {
  compilerOptions: { module: ts.ModuleKind.CommonJS, target: ts.ScriptTarget.ES2022 }
}).outputText, sandbox);
const { channelLogoIndex, providerLogoUrl, channelLogoFailed, failChannelLogo } = sandbox.exports;
const entries = [['ESPN.us', 'US', ['ESPN'], ['us']], ['ESPN.nl', 'NL', ['ESPN'], ['nl']],
  ['BBCOne.uk', 'UK', ['BBC One', 'BBC 1'], ['bbc']], ['Channel4.uk', 'UK', ['Channel 4'], ['four']],
  ['Channel4Plus1.uk', 'UK', ['Channel 4 +1'], ['shift']], ['Local.us', 'US', ['NBC Charlotte'], ['local']]];
const match = channelLogoIndex(entries);
test('exact IDs and unique country-specific names only', () => {
  assert.equal(match(' ESPN.US ', 'Other').join(), 'us');
  assert.equal(match(undefined, 'ESPN').length, 0);
  assert.equal(match(undefined, 'NL | ESPN FHD').join(), 'nl');
  assert.equal(match(undefined, 'UK: BBC 1 HD').join(), 'bbc');
});
test('time shifts, numbers and regions are not discarded', () => {
  assert.equal(match(undefined, 'Channel 4 +1 HD').join(), 'shift');
  for (const name of ['Channel 5', 'BBC One +1', 'NBC', 'NBC Boston', 'Unknown', 'FR | BBC One']) {
    assert.equal(match(undefined, name).length, 0, name);
  }
});

test('provider and quality prefixes do not prevent an exact regional match', () => {
  assert.equal(match(undefined, 'UK-NOWTV| BBC One FHD').join(), 'bbc');
  assert.equal(match(undefined, '4K| NL: ESPN UHD').join(), 'nl');
  assert.equal(match(undefined, '4K| ESPN UHD').length, 0, 'ambiguous ESPN regions must not be guessed');
  assert.equal(match(undefined, 'UK-NOWTV| BBC One +1').length, 0);
});
test('provider fallback URLs are normalized and unsafe schemes are rejected', () => {
  assert.equal(providerLogoUrl('//example.com/logo.png'), 'https://example.com/logo.png');
  assert.equal(providerLogoUrl(btoa('https://example.com/logo.png')), 'https://example.com/logo.png');
  assert.equal(providerLogoUrl('javascript:alert(1)'), undefined);
  failChannelLogo('https://example.com/broken.png');
  assert.equal(channelLogoFailed('https://example.com/broken.png'), true);
});
test('bundled Android and web metadata are identical and contain no stream URLs', () => {
  const web = fs.readFileSync(require.resolve('../public/data/channel-logos.json'), 'utf8');
  assert.equal(web, fs.readFileSync(require.resolve('../../app/src/main/assets/channel-logos.json'), 'utf8'));
  const data = JSON.parse(web);
  assert.ok(data.entries.length > 1000);
  for (const entry of data.entries) {
    assert.equal(entry.length, 4);
    assert.ok(entry[3].length <= 2);
    assert.ok(entry[3].every(url => url.startsWith('https://')));
  }
});
