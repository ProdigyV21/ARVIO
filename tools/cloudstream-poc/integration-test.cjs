// Exercises ARVIO's real add-on/source code without launching a browser.
const fs = require('node:fs');
const path = require('node:path');
const assert = require('node:assert/strict');
const { build } = require('../../web/node_modules/esbuild');
(async () => {
  const evidence = path.resolve(__dirname, '../../artifacts/cloudstream-poc');
  fs.mkdirSync(evidence, { recursive: true });
  const bundle = path.join(evidence, 'addon-client.cjs');
  await build({ entryPoints: [path.resolve(__dirname, '../../web/lib/addons.ts')], bundle: true, platform: 'node', format: 'cjs', outfile: bundle,
    define: { 'process.env.NEXT_PUBLIC_SELF_HOSTED': '"true"', 'process.env.NEXT_PUBLIC_ARVIO_RESOLVER_URL': '""' } });
  global.window = { location: { origin: 'http://127.0.0.1:8876' } };
  const { installAddon, getStreams } = require(bundle);
  const addon = await installAddon('http://127.0.0.1:8950/manifest.json');
  assert.equal(addon.id, 'arvio.local.cloudstream.streamplay.poc');
  const started = Date.now();
  const sources = await getStreams([addon], { id: 45745, title: 'Sintel', mediaType: 'movie', imdbId: 'tt1727587' });
  assert.ok(sources.length > 0, 'StreamPlay must return real direct sources for Sintel');
  assert.ok(sources.every(s => s.url?.startsWith('https://') && !s.behaviorHints?.proxyHeaders));
  const health = await fetch('http://127.0.0.1:8950/health').then(r => r.json());
  assert.equal(health.metrics.mediaRelayBytes, 0);
  fs.writeFileSync(path.join(evidence, 'sources.private.json'), JSON.stringify(sources, null, 2));
  const report = { checkedAt: new Date().toISOString(), title: 'Sintel', test: 'ARVIO installAddon + getStreams, real runtime, no browser',
    elapsedMs: Date.now() - started, sources: sources.map(s => ({ name: s.source, quality: s.quality, host: new URL(s.url).hostname })), health,
    browserPlaybackVerified: false };
  fs.writeFileSync(path.join(evidence, 'integration-result.json'), JSON.stringify(report, null, 2));
  console.log(JSON.stringify(report, null, 2));
})().catch(error => { console.error(error.message); process.exitCode = 1; });
