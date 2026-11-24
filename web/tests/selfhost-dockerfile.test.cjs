const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');

const read = (name) => fs.readFileSync(path.resolve(__dirname, '..', name), 'utf8');
const instructions = (text) => text.split(/\r?\n/).filter((line) => !line.trimStart().startsWith('#')).join('\n');

// A plain `git clone` + `docker compose up --build` must not depend on CI-only inputs.
test('compose builds the self-host image, which needs nothing a fresh clone lacks', () => {
  assert.match(read('compose.yaml'), /dockerfile:\s*Dockerfile\.selfhost/);
  // Comments may explain what the file avoids; only instructions matter.
  const dockerfile = instructions(read('Dockerfile.selfhost'));
  for (const ciOnly of ['distribution-artifacts', 'distribution-sources', 'runtime-fetch-sources', 'unraid-package', 'codecs-build', 'arvio-source']) {
    assert.equal(dockerfile.includes(ciOnly), false, `${ciOnly} is a CI/Unraid-only input`);
  }
});

test('the self-host image is credential-free, self-hosted and uses the prebuilt translations', () => {
  const dockerfile = read('Dockerfile.selfhost');
  assert.equal(/ARG (?:TRAKT|SIMKL|NEXT_PUBLIC_TELEGRAM|NEXT_PUBLIC_ARVIO_RESOLVER)/.test(dockerfile), false);
  assert.match(dockerfile, /NEXT_PUBLIC_SELF_HOSTED=true/);
  assert.match(dockerfile, /ARVIO_TRANSLATION_MODE=prebuilt/);
  assert.match(dockerfile, /ARVIO_STANDALONE=true/);
  assert.match(dockerfile, /USER node/);
});

test('the self-host image keeps the ARVIO license and the collected dependency notices', () => {
  const dockerfile = read('Dockerfile.selfhost');
  assert.match(dockerfile, /node scripts\/collect-licenses\.mjs/);
  assert.match(dockerfile, /\.\/licenses\/ARVIO-LICENSE/);
  assert.match(dockerfile, /\.\/licenses\/third-party/);
});

test('the Unraid/CI image stays separate and the README says which Dockerfile a plain clone uses', () => {
  assert.ok(fs.existsSync(path.resolve(__dirname, '../Dockerfile')));
  assert.match(read('README.md'), /Dockerfile\.selfhost/);
});
