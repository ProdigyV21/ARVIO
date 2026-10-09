/* Actual partner handler, legacy navigation and details UI; offline adapters, no user accounts. */
const path = require('node:path');
const fs = require('node:fs');
const os = require('node:os');
const http = require('node:http');
const assert = require('node:assert/strict');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');

(async () => {
  const root = path.resolve(__dirname, '..');
  const out = fs.mkdtempSync(path.join(os.tmpdir(), 'arvio-partner-ui-'));
  const store = path.join(root, 'tests/partner-ui/store.tsx');
  const tmdb = path.join(root, 'tests/partner-ui/tmdb.ts');
  await esbuild.build({ entryPoints: [path.join(root, 'tests/partner-ui/entry.tsx')], bundle: true,
    outfile: path.join(out, 'app.js'), jsx: 'automatic', define: { 'process.env.NODE_ENV': '"test"', 'process.env': '{}' },
    plugins: [{ name: 'offline-partner-adapters', setup(build) {
      build.onResolve({ filter: /^@\/lib\/store$/ }, () => ({ path: store }));
      build.onResolve({ filter: /^\.\/store$/ }, args => args.importer.endsWith(path.join('lib', 'sync.ts')) ? { path: store } : undefined);
      build.onResolve({ filter: /^@\/lib\/(tmdb|imdbRatings)$/ }, () => ({ path: tmdb }));
      build.onResolve({ filter: /^\.\/tmdb$/ }, args => args.importer.endsWith('partnerMedia.ts') ? { path: tmdb } : undefined);
      build.onResolve({ filter: /^@\// }, args => ({ path: ['.tsx', '.ts', '.js', '/index.tsx', '/index.ts', '']
        .map(extension => path.join(root, args.path.slice(2) + extension)).find(file => fs.existsSync(file)) }));
    } }]
  });
  let requests = 0, failNext = false, delay = 0;
  const server = http.createServer((req, res) => {
    const pathname = new URL(req.url, 'http://localhost').pathname;
    if (pathname.startsWith('/fixture/tmdb/')) {
      requests++;
      if (failNext) { failNext = false; res.statusCode = 503; res.end('offline'); return; }
      const metadataPath = pathname.slice('/fixture/tmdb/'.length);
      let payload;
      if (metadataPath.startsWith('find/')) payload = { tv_episode_results: [{ id: 90001, show_id: 1399, season_number: 0, episode_number: 2 }] };
      else if (metadataPath.includes('/episode/')) payload = { season_number: Number(metadataPath.split('/')[3]), episode_number: Number(metadataPath.split('/')[5]), name: 'Special 2' };
      else if (metadataPath.includes('/season/')) payload = { season_number: Number(metadataPath.split('/')[3]) };
      else payload = { id: Number(metadataPath.split('/')[1]), name: metadataPath.startsWith('tv/') ? 'Partner Show' : undefined,
        title: metadataPath.startsWith('movie/') ? 'Partner Film' : undefined };
      setTimeout(() => { res.setHeader('content-type', 'application/json'); res.end(JSON.stringify(payload)); }, delay);
      return;
    }
    if (pathname === '/') {
      res.setHeader('content-type', 'text/html');
      res.end('<html><head><meta name="viewport" content="width=device-width,initial-scale=1"/><link rel="stylesheet" href="/globals.css"/><link rel="stylesheet" href="/app.css"/></head><body><div id="root"></div><script src="/app.js"></script></body></html>');
      return;
    }
    const known = { '/app.js': path.join(out, 'app.js'), '/app.css': path.join(out, 'app.css'), '/globals.css': path.join(root, 'app/globals.css') };
    const filename = known[pathname];
    if (!filename || !fs.existsSync(filename)) { res.statusCode = 404; res.end(); return; }
    res.setHeader('content-type', pathname.endsWith('.css') ? 'text/css' : 'application/javascript');
    fs.createReadStream(filename).pipe(res);
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  const origin = `http://127.0.0.1:${server.address().port}`;
  const evidence = path.resolve(root, '../artifacts/growth-partners/web');
  fs.mkdirSync(evidence, { recursive: true });
  const page = await browser.newPage({ viewport: { width: 1200, height: 850 } });
  const errors = [];
  page.on('pageerror', error => { errors.push(error.message); console.error('Fixture runtime error:', error.message); });
  page.setDefaultTimeout(8000);
  // No fixture request may escape to a real metadata, media or identity service.
  await page.route('**/*', route => new URL(route.request().url()).origin === origin ? route.continue() : route.abort());
  const enterApp = async (hydrate = true) => {
    await page.getByRole('button', { name: 'Simulate login', exact: true }).click();
    await page.getByRole('button', { name: 'Choose profile', exact: true }).click();
    await page.getByRole('button', { name: 'Grant membership', exact: true }).click();
    if (hydrate) await page.getByRole('button', { name: 'Hydrate profile', exact: true }).click();
  };
  try {
    requests = 0;
    await page.goto(origin + '/?open=1&imdb=tt12345&utm_source=fixture&title=movie:999#preserved');
    await enterApp(false);
    assert.equal(requests, 0, 'No title request before login/profile/access hydration');
    assert.equal(await page.locator('[data-testid="qa-state"]').getAttribute('data-opens'), '0');
    await page.getByRole('button', { name: 'Hydrate profile', exact: true }).click();
    await page.getByRole('heading', { name: 'Partner Show', exact: true }).waitFor();
    await page.locator('.episode-row.is-active').waitFor();
    assert.equal(await page.locator('.season-tab.is-active').innerText(), 'Specials');
    assert.match(await page.locator('.episode-row.is-active').innerText(), /Special 2/);
    assert.equal(await page.getByRole('button', { name: 'Continue S0 E2', exact: true }).count(), 1, 'Season zero is not mistaken for no episode');
    assert.equal(await page.locator('[data-testid="qa-state"]').getAttribute('data-opens'), '1');
    assert.equal(await page.locator('[data-testid="qa-state"]').getAttribute('data-plays'), '0');
    const url = new URL(page.url());
    assert.equal(url.searchParams.get('open'), null);
    assert.equal(url.searchParams.get('utm_source'), 'fixture');
    assert.equal(url.hash, '#preserved');
    await page.screenshot({ path: path.join(evidence, 'partner-specials-desktop.png') });

    await page.setViewportSize({ width: 390, height: 844 });
    failNext = true;
    await page.goto(origin + '/?open=1&type=movie&id=550&utm_source=retry');
    await enterApp();
    await page.getByRole('alert').waitFor();
    assert.ok(new URL(page.url()).searchParams.has('open'), 'Error keeps pending link');
    assert.doesNotMatch(await page.getByRole('alert').innerText(), /fixture-secret|api_key/);
    await page.screenshot({ path: path.join(evidence, 'partner-retry-phone.png') });
    await page.getByRole('button', { name: 'Retry', exact: true }).click();
    await page.getByRole('heading', { name: 'Partner Film', exact: true }).waitFor();
    assert.equal(await page.locator('[data-testid="qa-state"]').getAttribute('data-opens'), '1');
    assert.equal(await page.locator('[data-testid="qa-state"]').getAttribute('data-plays'), '0');

    await page.goto(origin + '/?open=1&type=movie&id=550&utm_source=cancel');
    delay = 250;
    await enterApp();
    await page.getByRole('button', { name: 'Manual selection', exact: true }).click();
    await page.getByRole('heading', { name: 'Manual selection', exact: true }).waitFor();
    await page.waitForTimeout(400);
    assert.equal(await page.locator('[data-testid="qa-state"]').getAttribute('data-opens'), '1', 'Late lookup cannot replace manual selection');
    assert.equal(new URL(page.url()).searchParams.get('open'), null);
    assert.equal(new URL(page.url()).searchParams.get('utm_source'), 'cancel');
    assert.deepEqual(errors, [], 'No component runtime errors');
    console.log('Partner browser UI passed: gated pending link, legacy navigation coexistence, specials/episode highlight, no autoplay, safe retry, stale manual-selection cancellation.');
    console.log(`Offline QA screenshots: ${evidence}`);
  } finally {
    await browser.close();
    server.close();
    // Only the explicit OS-created test directory is removed; screenshots remain in artifacts.
    const resolvedTemp = path.resolve(out);
    const expectedParent = path.resolve(os.tmpdir());
    if (path.dirname(resolvedTemp) !== expectedParent || !path.basename(resolvedTemp).startsWith('arvio-partner-ui-')) {
      throw new Error('Refusing to remove an unexpected temporary directory');
    }
    fs.rmSync(resolvedTemp, { recursive: true, force: true });
  }
})().catch(error => { console.error(error); process.exitCode = 1; });
