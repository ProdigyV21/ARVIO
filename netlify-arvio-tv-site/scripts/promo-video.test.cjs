const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const root = path.resolve(__dirname, '..');
const source = fs.readFileSync(path.join(root, 'assets/promo-video.js'), 'utf8');
const settle = () => new Promise(resolve => setImmediate(resolve));

function fixture({ reduced = false, saveData = false, rejectPlay = false } = {}) {
  function events(values = {}) {
    const handlers = {};
    return Object.assign(values, {
      addEventListener(name, fn) { (handlers[name] ||= []).push(fn); },
      emit(name, event) { (handlers[name] || []).forEach(fn => fn(event)); },
    });
  }
  const motion = events({ matches: reduced });
  const connection = events({ saveData });
  const video = events({
    paused: true, autoplay: true, muted: false, src: '', calls: 0,
    dataset: { src: '/assets/promo/arvio-tour-720p.mp4' },
    getAttribute(name) { return this[name]; },
    async play() {
      this.calls++;
      if (rejectPlay) throw new Error('Autoplay blocked');
      this.paused = false;
      this.emit('play');
    },
    pause() { this.paused = true; this.emit('pause'); },
  });
  const document = events({ hidden: false, getElementById: () => video, body: { classList: { contains: () => reduced } } });
  let observer;
  class IntersectionObserver {
    constructor(callback) { observer = callback; }
    observe() {}
  }
  vm.runInNewContext(source, {
    document, navigator: { connection }, IntersectionObserver,
    window: { matchMedia: () => motion, IntersectionObserver },
  });
  return { video, document, motion, connection, visible: value => observer([
    { isIntersecting: value, intersectionRatio: value ? 1 : 0 },
  ]) };
}

test('defers the video and autoplays muted only in view', async () => {
  const f = fixture();
  assert.equal(f.video.src, '');
  assert.equal(f.video.autoplay, false);
  assert.equal(f.video.muted, true);
  f.visible(true);
  await settle();
  assert.equal(f.video.src, f.video.dataset.src);
  assert.equal(f.video.paused, false);
  f.visible(false);
  assert.equal(f.video.paused, true);
  f.visible(true);
  await settle();
  assert.equal(f.video.paused, false);
});

test('does not override a visitor pause after scrolling or switching tabs', async () => {
  const f = fixture();
  f.visible(true);
  await settle();
  f.video.pause();
  f.visible(false);
  f.visible(true);
  f.document.hidden = true;
  f.document.emit('visibilitychange');
  f.document.hidden = false;
  f.document.emit('visibilitychange');
  await settle();
  assert.equal(f.video.calls, 1);
  await f.video.play();
  f.visible(false);
  f.visible(true);
  await settle();
  assert.equal(f.video.paused, false);
});

for (const preference of [{ reduced: true }, { saveData: true }]) {
  test(`respects ${JSON.stringify(preference)} but allows explicit playback`, async () => {
    const f = fixture(preference);
    f.visible(true);
    await settle();
    assert.equal(f.video.calls, 0);
    await f.video.play();
    assert.equal(f.video.paused, false);
  });
}

test('pauses when hidden and safely resumes when visible', async () => {
  const f = fixture();
  f.visible(true);
  await settle();
  f.document.hidden = true;
  f.document.emit('visibilitychange');
  assert.equal(f.video.paused, true);
  f.document.hidden = false;
  f.document.emit('visibilitychange');
  await settle();
  assert.equal(f.video.paused, false);
});

test('autoplay rejection remains a paused player without an unhandled error', async () => {
  const f = fixture({ rejectPlay: true });
  f.visible(true);
  await settle();
  assert.equal(f.video.paused, true);
});

test('the shared page motion control pauses and resumes the hero video', async () => {
  const f = fixture();
  f.visible(true);
  await settle();
  f.document.emit('arvio:motionchange', { detail: { paused: true } });
  assert.equal(f.video.paused, true);
  f.visible(false);
  f.visible(true);
  await settle();
  assert.equal(f.video.paused, true);
  f.document.emit('arvio:motionchange', { detail: { paused: false } });
  await settle();
  assert.equal(f.video.paused, false);
});

test('enabling page motion does not override an explicit video pause', async () => {
  const f = fixture();
  f.visible(true);
  await settle();
  f.video.pause();
  f.document.emit('arvio:motionchange', { detail: { paused: true } });
  f.document.emit('arvio:motionchange', { detail: { paused: false } });
  await settle();
  assert.equal(f.video.paused, true);
});

test('a preference change during play startup cannot leak autoplay', async () => {
  const f = fixture();
  f.visible(true);
  f.motion.matches = true;
  f.motion.emit('change');
  await settle();
  assert.equal(f.video.paused, true);
});

for (const file of ['index.html', 'es/index.html', 'pt-br/index.html']) {
  test(`${file}: full tour has controls, inline playback and a download fallback`, () => {
    const html = fs.readFileSync(path.join(root, file), 'utf8');
    assert.match(html, /href="#demo"/);
    assert.match(html, /<video[^>]+controls autoplay muted loop playsinline preload="none"/);
    // Stream the lighter web encode; the download keeps the full-quality file the README links to.
    assert.match(html, /data-src="\/assets\/promo\/arvio-tour-web.mp4"/);
    assert.match(html, /href="\/assets\/promo\/arvio-tour-720p.mp4" download/);
    assert.match(html, /promo-video.js\?v=2/);
    const hero = html.match(/<section class="hero hero-v2"[^>]*>([\s\S]*?)<\/section>/)?.[1];
    assert.ok(hero?.includes('id="promo-video"'), 'The video must be inside the first-screen hero');
    assert.ok(hero.includes('id="demo"'), 'README links must still target the tour');
    assert.equal((html.match(/<video\b/g) || []).length, 1, 'Do not duplicate the video below the hero');
    assert.doesNotMatch(hero, /id="hero-screen"|class="scene-phone"|class="chapter-dock"/);
  });
}

test('Web encodes stay light enough for the homepage and Premium page', () => {
  for (const file of ['assets/promo/arvio-tour-web.mp4', 'assets/promo/arvio-premium-poster.webp']) {
    assert.ok(fs.existsSync(path.join(root, file)), file);
  }
  assert.ok(fs.statSync(path.join(root, 'assets/promo/arvio-tour-web.mp4')).size <= 5 * 1024 * 1024);
  assert.ok(fs.statSync(path.join(root, 'assets/promo/arvio-premium-poster.webp')).size <= 150 * 1024);
  const premium = fs.readFileSync(path.join(root, 'premium/index.html'), 'utf8');
  assert.match(premium, /<video id="promo-video"[^>]+controls muted playsinline preload="none"[^>]+src="\/assets\/promo\/arvio-tour-web.mp4"/);
  assert.doesNotMatch(premium.match(/<video[^>]*>/)[0], /\bautoplay\b/, 'promo-video.js decides on autoplay after checking motion and Save-Data');
  assert.match(premium, /promo-video.js\?v=2/);
});

test('README leads with a linked animated preview before downloads and long-form sections', () => {
  const readme = fs.readFileSync(path.join(root, '../README.md'), 'utf8');
  assert.doesNotMatch(readme, /screenshots-v2|## Screenshots/);
  assert.match(readme, /arvio-tour-preview.gif\)\]\(https:\/\/arvio.tv\/#demo\)/);
  assert.match(readme, /arvio-tour-720p.mp4/);
  const preview = readme.indexOf('arvio-tour-preview.gif');
  assert.ok(preview < readme.indexOf('\n## '), 'Keep the tour above all major sections');
  assert.ok(preview < readme.indexOf('img.shields.io'), 'Do not bury the tour below badges');
  assert.ok(readme.slice(0, preview).split('\n').length <= 25, 'The tour belongs directly below the introduction');
});
