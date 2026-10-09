import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import { STARTERS } from './starters.mjs';
import { encodeShare, decodeShare, createCollectionDocument, serializeCollections, validateDraft } from './studio-core.mjs';

const html = fs.readFileSync(new URL('./index.html', import.meta.url), 'utf8');
test('all authored starters round-trip through sharing and match generated importer documents', () => {
  assert.equal(new Set(STARTERS.map(s => s.id)).size, 3);
  for (const starter of STARTERS) {
    assert.deepEqual(decodeShare(encodeShare(starter.draft)), validateDraft(starter.draft));
    const exported = fs.readFileSync(new URL(`./examples/${starter.id}.json`, import.meta.url), 'utf8');
    assert.deepEqual(JSON.parse(exported), createCollectionDocument(starter.draft));
    assert.equal(exported.trim(), serializeCollections(starter.draft));
    assert.ok(starter.draft.folders.every(f => f.source.kind === 'tmdb'));
    assert.ok(html.includes(`href="/collection-studio/${encodeShare(starter.draft)}" target="_blank" rel="noopener"`));
    assert.ok(html.includes(`href="/collection-studio/examples/${starter.id}.json" download`));
  }
});
test('Studio has crawlable useful examples and truthful metadata without enabling network access', () => {
  assert.match(html, /id="examples"/);
  assert.match(html, /not fixed movie lists/);
  assert.match(html, /not an import URL/);
  assert.match(html, /connect-src 'none'/);
  const schema = JSON.parse(html.match(/<script type="application\/ld\+json">([\s\S]*?)<\/script>/)[1]);
  assert.equal(schema['@graph'][0].offers.price, '0');
  assert.equal(schema['@graph'][0].url, 'https://arvio.tv/collection-studio/');
  const script = fs.readFileSync(new URL('./studio.js', import.meta.url), 'utf8');
  assert.match(script, /const pageAnchors = new Set\(\['#studio', '#how-to-use', '#examples'\]\)/);
  assert.match(script, /if \(fragment && !pageAnchors\.has\(fragment\)\)/);
});
