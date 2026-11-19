// Sports on iPhone: Safari killed the tab ("A problem repeatedly occurred")
// once a screen of rows held full-size 1280×720 thumbnails. Cards now ask for
// TheSportsDB's resized copies, and rows far off-screen release their artwork.
const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load } = require('./load.cjs');
const { sizedSportsImage } = load('lib/sportsImageSize.ts');
const pane = fs.readFileSync(path.join(__dirname, '../components/livetv/SportsGuidePane.tsx'), 'utf8');

test('TheSportsDB thumbs and badges get a resized copy', () => {
  assert.equal(sizedSportsImage('https://r2.thesportsdb.com/images/media/event/thumb/s6nlgj1787253554.jpg', 'medium'),
    'https://r2.thesportsdb.com/images/media/event/thumb/s6nlgj1787253554.jpg/medium');
  assert.equal(sizedSportsImage('https://www.thesportsdb.com/images/media/team/badge/abc.png', 'small'),
    'https://www.thesportsdb.com/images/media/team/badge/abc.png/small');
});

test('other hosts, already-sized and odd URLs are left alone', () => {
  for (const url of ['https://pengu.uk/a.jpg', 'https://r2.thesportsdb.com/images/x.jpg/preview', 'https://r2.thesportsdb.com/images/x.jpg?v=2', 'not a url', undefined])
    assert.equal(sizedSportsImage(url, 'medium'), url);
});

test('a missing resized copy falls back to the original before giving up', () => {
  assert.match(pane, /onError=\{\(\) => \{ setLoadedUrl\(null\); if \(banner\.canFallBack\) banner\.fallBack\(\); else setBannerFailed\(true\); \}\}/);
  assert.match(pane, /home\.canFallBack \? home\.fallBack\(\) : setPairFailed\(true\)/);
});

test('rows more than a screen away render no artwork', () => {
  assert.match(pane, /new IntersectionObserver\([\s\S]*?\{ rootMargin: "100% 0px" \}\)/);
  assert.match(pane, /data-row=\{row\.id\}/);
  assert.match(pane, /\(nearRows\.has\(row\.id\) \|\| rowIndex < 2\) && <EventArtwork/);
});

test('the worker gets only the scanned channels\' schedules', () => {
  assert.match(pane, /for \(const ch of scanned\) if \(guide\[ch\.id\]\) scannedGuide\[ch\.id\] = guide\[ch\.id\];/);
  assert.match(pane, /worker\.postMessage\(\{ channels: scanned, guide: scannedGuide,/);
});
