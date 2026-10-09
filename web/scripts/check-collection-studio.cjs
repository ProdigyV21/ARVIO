const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');
const assert = require('node:assert/strict');
const { chromium } = require('playwright');

const siteRoot = path.resolve(__dirname, '../../netlify-arvio-tv-site');
const output = path.resolve(__dirname, '../../.planning');
const contentTypes = { '.html': 'text/html; charset=utf-8', '.css': 'text/css', '.js': 'text/javascript', '.mjs': 'text/javascript', '.json': 'application/json', '.svg': 'image/svg+xml', '.png': 'image/png', '.woff2': 'font/woff2' };
const server = http.createServer((req, res) => {
  try {
    const url = new URL(req.url, 'http://localhost');
    const relative = decodeURIComponent(url.pathname).replace(/^\/+/, '');
    const file = path.resolve(siteRoot, relative, url.pathname.endsWith('/') ? 'index.html' : '');
    if (!file.startsWith(siteRoot + path.sep) || !fs.existsSync(file) || !fs.statSync(file).isFile()) {
      res.writeHead(404).end();
      return;
    }
    res.writeHead(200, { 'Content-Type': contentTypes[path.extname(file)] || 'application/octet-stream' });
    fs.createReadStream(file).pipe(res);
  } catch {
    res.writeHead(400).end();
  }
});

function fragment(draft) {
  return '#studio=' + Buffer.from(JSON.stringify(draft), 'utf8').toString('base64url');
}

function draft(overrides = {}) {
  return {
    version: 1,
    title: 'Weekend picks',
    folders: [{ id: 'folder-1', title: 'Popular movies', description: '', theme: 'sage', source: { kind: 'tmdb', preset: 'movie-popular' } }],
    ...overrides,
  };
}

async function exported(page) {
  return JSON.parse(await page.locator('#json-output').inputValue());
}

async function noOverflow(page, label) {
  await page.evaluate(() => document.fonts.ready);
  assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth + 1), false, `${label}: document overflows horizontally`);
  assert.match(await page.locator('#how-title').innerText(), /collection\s+where/, `${label}: heading words must remain separated when line breaks change`);
}

(async () => {
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const origin = `http://127.0.0.1:${server.address().port}`;
  fs.mkdirSync(output, { recursive: true });
  let browser;
  const failures = [];
  try {
    browser = await chromium.launch({ headless: true, channel: process.env.PLAYWRIGHT_CHANNEL || 'chrome' });
    const run = async (name, action, viewport = { width: 1440, height: 1000 }) => {
      const context = await browser.newContext({ viewport, permissions: ['clipboard-read', 'clipboard-write'] });
      const page = await context.newPage();
      const errors = [];
      const outward = [];
      const dialogs = [];
      page.on('pageerror', error => errors.push(error.message));
      page.on('dialog', dialog => { dialogs.push(dialog.message()); dialog.dismiss(); });
      await context.route('**/*', route => {
        if (new URL(route.request().url()).origin === origin) return route.continue();
        outward.push(route.request().url());
        return route.abort();
      });
      try {
        await action(page, outward);
        assert.deepEqual(errors, [], `${name}: browser errors`);
        assert.deepEqual(dialogs, [], `${name}: unexpected dialogs`);
        assert.deepEqual(outward, [], `${name}: outward requests attempted`);
        console.log(`PASS ${name}`);
      } catch (error) {
        failures.push(`${name}: ${error.message}`);
        console.error(`FAIL ${name}: ${error.message}`);
        await page.screenshot({ path: path.join(output, `collection-studio-failure-${name.replace(/[^a-z0-9-]/gi, '-')}.png`), fullPage: true }).catch(() => {});
      } finally {
        await context.close();
      }
    };

    const openEditor = async page => {
      await page.goto(origin + '/collection-studio/');
      await page.locator('#row-title').waitFor();
    };

    for (const [name, width, height] of [['desktop', 1440, 1000], ['mobile-390', 390, 844], ['mobile-320', 320, 740]]) {
      await run(`layout-${name}`, async page => {
        await openEditor(page);
        await noOverflow(page, name);
        await page.screenshot({ path: path.join(output, `collection-studio-${name}.png`), fullPage: true });
        await page.locator('#row-title').fill('週末の映画 🎬 — İzmir & São Paulo');
        await page.locator('#folder-title').fill('Été / 東京 / العربية 🎞️');
        await page.locator('#folder-description').fill('Films and series to explore together. '.repeat(3));
        const json = await exported(page);
        assert.equal(json[0].title, '週末の映画 🎬 — İzmir & São Paulo');
        assert.equal(json[0].folders[0].title, 'Été / 東京 / العربية 🎞️');
        await noOverflow(page, `${name} after Unicode edits`);
        await page.locator('#add-folder').click();
        const next = await exported(page);
        assert.equal(next[0].folders.length, json[0].folders.length + 1);
        await noOverflow(page, `${name} after adding folder`);
      }, { width, height });
    }

    await run('keyboard-copy-download', async page => {
      await openEditor(page);
      await page.locator('#row-title').focus();
      await page.keyboard.press('ControlOrMeta+A');
      await page.keyboard.type('Keyboard collection');
      await page.keyboard.press('Tab');
      assert.notEqual(await page.evaluate(() => document.activeElement.id), 'row-title', 'Tab must leave the row title');
      const json = await exported(page);
      assert.equal(json[0].title, 'Keyboard collection');
      await page.locator('#copy-json').focus();
      await page.keyboard.press('Enter');
      assert.deepEqual(JSON.parse(await page.evaluate(() => navigator.clipboard.readText())), json);
      const downloadPromise = page.waitForEvent('download');
      await page.locator('#download-json').click();
      const download = await downloadPromise;
      assert.match(download.suggestedFilename(), /\.json$/);
      assert.deepEqual(JSON.parse(fs.readFileSync(await download.path(), 'utf8')), json);
      assert.equal(await page.locator('#json-output').getAttribute('readonly') !== null, true, 'JSON output must remain read-only');
    });

    await run('keyboard-folder-arrangement-and-limits', async page => {
      await openEditor(page);
      const initial = (await exported(page))[0].folders;
      for (let step = 0; step < initial.length - 1; step++) {
        await page.locator('#move-down').focus();
        await page.keyboard.press('Enter');
      }
      const moved = (await exported(page))[0].folders;
      assert.equal(moved.at(-1).id, initial[0].id, 'Keyboard move must preserve and reorder the selected folder');
      assert.equal(await page.evaluate(() => document.activeElement !== document.body && !document.activeElement.disabled), true, 'Moving to an edge must retain a usable keyboard focus');
      await page.locator('#remove-folder').click();
      assert.equal((await exported(page))[0].folders.length, initial.length - 1);
      for (let index = initial.length - 1; index < 8; index++) await page.locator('#add-folder').click();
      assert.equal((await exported(page))[0].folders.length, 8);
      assert.equal(await page.locator('#add-folder').isDisabled(), true, 'Folder cap must be visible in the UI');
      assert.equal(new Set((await exported(page))[0].folders.map(folder => folder.id)).size, 8, 'Removing and adding must preserve unique folder IDs');
      await noOverflow(page, 'eight folders at 320px');
      await page.screenshot({ path: path.join(output, 'collection-studio-eight-folders-mobile-320.png'), fullPage: true });
    }, { width: 320, height: 740 });

    await run('share-readonly-fork-unicode', async page => {
      const original = draft({ title: '東京で週末 🎬', folders: [{ id: 'folder-1', title: 'São Paulo & İzmir', description: 'Une sélection 🎞️', theme: 'plum', source: { kind: 'tmdb', preset: 'movie-popular' } }] });
      await page.goto(origin + '/collection-studio/' + fragment(original));
      await page.locator('#fork-shared').waitFor();
      assert.equal(await page.locator('input:not([readonly]):not([disabled]), select:not([disabled]), textarea:not([readonly]):not([disabled])').evaluateAll(elements => elements.filter(element => element.getClientRects().length > 0).length), 0, 'Shared view must not expose editable fields');
      assert.equal((await exported(page))[0].title, original.title);
      await noOverflow(page, 'shared Unicode draft');
      await page.screenshot({ path: path.join(output, 'collection-studio-shared.png'), fullPage: true });
      await page.locator('#fork-shared').click();
      await page.locator('#row-title').waitFor();
      assert.equal(await page.locator('#row-title').inputValue(), original.title);
      await page.locator('#row-title').fill('My adapted collection');
      assert.equal((await exported(page))[0].title, 'My adapted collection');
      assert.equal(new URL(page.url()).hash, '', 'Forking must remove the original read-only fragment');
    }, { width: 390, height: 844 });

    await run('copy-share-roundtrip', async page => {
      await openEditor(page);
      await page.locator('#row-title').fill('東京で週末 🎬');
      const json = await exported(page);
      await page.locator('#copy-share').click();
      const share = new URL(await page.evaluate(() => navigator.clipboard.readText()));
      assert.equal(share.origin, origin);
      assert.equal(share.pathname, '/collection-studio/');
      assert.equal(share.search, '', 'Collection data must not appear in the query string');
      assert.match(share.hash, /^#studio=[a-zA-Z0-9_-]+$/);
      assert.ok(share.hash.length <= 6000);
      assert.equal(JSON.parse(Buffer.from(share.hash.slice(8), 'base64url').toString('utf8')).title, '東京で週末 🎬');
      await page.goto(share.href);
      await page.locator('#fork-shared').waitFor();
      assert.deepEqual(await exported(page), json, 'Recipient export must match the shared collection');
    });

    await run('valid-public-list-sources', async page => {
      await openEditor(page);
      await page.locator('#preset').selectOption('tv-acclaimed');
      const tvSource = (await exported(page))[0].folders[0].sources[0];
      assert.equal(tvSource.provider, 'tmdb');
      assert.equal(tvSource.mediaType, 'TV', 'Changing the preset must change exported media type');
      await page.locator('#source-kind').selectOption('mdblist');
      await page.locator('#source-url').fill('https://mdblist.com/lists/example/weekend-picks');
      let source = (await exported(page))[0].folders[0].sources[0];
      assert.equal(source.provider, 'mdblist');
      assert.equal(source.slug || source.mdblistSlug, 'example/weekend-picks');
      await page.locator('#source-kind').selectOption('trakt');
      await page.locator('#source-url').fill('https://trakt.tv/lists/12345');
      source = (await exported(page))[0].folders[0].sources[0];
      assert.equal(source.provider, 'trakt');
      assert.equal(String(source.traktListId), '12345');
    });

    await run('shared-literal-markup-small-screen', async page => {
      const title = '<svg onload=alert(1)> Cinema';
      await page.goto(origin + '/collection-studio/' + fragment(draft({ title })));
      await page.locator('#fork-shared').waitFor();
      assert.equal((await exported(page))[0].title, title);
      assert.equal(await page.locator('svg[onload], img[onerror]').count(), 0, 'User text cannot become executable markup');
      assert.equal(await page.evaluate(() => localStorage.length + sessionStorage.length), 0, 'Shared drafts must not be saved to browser storage');
      await noOverflow(page, 'shared 320px literal text');
      await page.screenshot({ path: path.join(output, 'collection-studio-shared-mobile-320.png'), fullPage: true });
    }, { width: 320, height: 740 });

    await run('unsafe-and-sensitive-source-inputs', async page => {
      await openEditor(page);
      await page.locator('#source-kind').selectOption('mdblist');
      for (const value of [
        'javascript:alert(1)', 'file:///C:/private.json', 'http://localhost/list',
        'https://127.0.0.1/list', 'https://10.0.0.1/list', 'https://[::1]/list',
        'https://user:password@mdblist.com/lists/example/weekend',
        'https://mdblist.com/lists/example/weekend?api_key=private-secret',
        'https://mdblist.com.evil.invalid/lists/example/weekend',
      ]) {
        await page.locator('#source-url').fill(value);
        assert.equal(await page.locator('#copy-json').isDisabled(), true, `Must disable export for ${value}`);
        assert.equal(await page.locator('#copy-share').isDisabled(), true, `Must disable sharing for ${value}`);
        assert.ok((await page.locator('#field-error').textContent()).trim(), `Must explain invalid input ${value}`);
      }
      await page.locator('#source-url').fill('https://mdblist.com/lists/example/weekend');
      await page.locator('#folder-description').fill('api_key=private-secret');
      assert.equal(await page.locator('#copy-share').isDisabled(), true, 'Credentials in text must not be shareable');
      await page.locator('#folder-description').fill('A public collection');
      assert.equal(await page.locator('#copy-json').isDisabled(), false, 'Valid input must recover without reloading');
    });

    await run('invalid-shares-recover-without-network', async page => {
      const valid = draft();
      const injected = [
        '#studio=not-valid-base64-json',
        '#studio=' + 'a'.repeat(6001),
        fragment({ ...valid, apiKey: 'private-secret' }),
        fragment({ ...valid, folders: [{ ...valid.folders[0], coverImageUrl: 'https://payload-target.invalid/image.png' }] }),
        fragment({ ...valid, folders: [{ ...valid.folders[0], source: { kind: 'mdblist', url: 'https://user:secret@mdblist.com/lists/example/weekend' } }] }),
        fragment({ ...valid, folders: [{ ...valid.folders[0], source: { kind: 'mdblist', url: 'https://127.0.0.1/private' } }] }),
        fragment({ ...valid, title: '<img src="https://payload-target.invalid/image.png" onerror="alert(1)">' }),
      ];
      for (const hash of injected) {
        await page.goto(origin + '/collection-studio/' + hash);
        await page.locator('#start-new').waitFor();
        assert.equal(await page.locator('#fork-shared').isVisible().catch(() => false), false, 'Invalid draft cannot be forked');
        assert.equal(await page.locator('img[src*="payload-target"]').count(), 0, 'Payload artwork/markup cannot become an image');
        assert.equal(await page.locator('#copy-share').isVisible().catch(() => false), false, 'Invalid draft cannot produce a share');
      }
      await page.locator('#start-new').click();
      await page.locator('#row-title').waitFor();
      assert.equal(new URL(page.url()).hash, '', 'Recovery must clear the invalid fragment');
    });

    assert.deepEqual(failures, [], failures.join('\n'));
    console.log('Collection Studio browser QA passed. Screenshots saved in .planning/collection-studio*.png.');
  } finally {
    await browser?.close();
    await new Promise(resolve => server.close(resolve));
  }
})().catch(error => { console.error(error); process.exitCode = 1; });
