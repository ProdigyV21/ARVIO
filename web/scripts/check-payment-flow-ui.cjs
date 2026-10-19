// Production payment components, isolated identity and transport. This script
// owns its ephemeral server and headless browser; it never uses a customer
// account, the user's browser, real billing, external network or email.
const { chromium } = require('@playwright/test');
const esbuild = require('esbuild');
const fs = require('node:fs');
const http = require('node:http');
const path = require('node:path');
const assert = require('node:assert/strict');

(async () => {
  const root = path.resolve(__dirname, '..');
  const fixture = name => path.join(root, 'tests/fixtures', name);
  const result = await esbuild.build({
    entryPoints: [fixture('premium-ui.tsx')], bundle: true, write: false, outdir: 'payment-fixture', jsx: 'automatic',
    define: { 'process.env': '{}', 'process.env.NODE_ENV': '"development"', 'process.env.NEXT_PUBLIC_PAYWALL_ENABLED': '"true"', 'process.env.NEXT_PUBLIC_KOFI_URL': '"https://ko-fi.com/arvio/tiers"' },
    plugins: [{ name: 'local-only-payment-transport', setup(build) {
      build.onResolve({ filter: /^@\// }, args => ({ path: args.path === '@/lib/store' ? fixture('premium-store.ts') : ['.tsx', '.ts', '/index.tsx', '/index.ts', ''].map(ext => path.join(root, args.path.slice(2) + ext)).find(file => fs.existsSync(file)) }));
      build.onResolve({ filter: /(^|\/)http$/ }, () => ({ path: fixture('premium-http.ts') }));
    } }]
  });
  const js = result.outputFiles.find(file => file.path.endsWith('.js')).contents;
  const css = result.outputFiles.find(file => file.path.endsWith('.css')).contents;
  const server = http.createServer((req, res) => {
    const url = new URL(req.url, 'http://localhost');
    res.setHeader('Content-Security-Policy', "default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; connect-src 'self'");
    if (url.pathname === '/bundle.js' || url.pathname === '/bundle.css') {
      res.setHeader('content-type', url.pathname.endsWith('js') ? 'text/javascript' : 'text/css');
      return res.end(url.pathname.endsWith('js') ? js : css);
    }
    if (['/arvio-logo.svg', '/arvio-wordmark.svg'].includes(url.pathname) || /^\/i18n\/[a-zA-Z-]+\.json$/.test(url.pathname)) {
      res.setHeader('content-type', url.pathname.endsWith('.svg') ? 'image/svg+xml' : 'application/json');
      return res.end(fs.readFileSync(path.join(root, 'public', url.pathname)));
    }
    res.setHeader('content-type', 'text/html');
    res.end('<!doctype html><html><head><meta name="viewport" content="width=device-width,initial-scale=1"><title>Local payment-flow test</title><link rel="stylesheet" href="/bundle.css"></head><body><div id="root"></div><script src="/bundle.js"></script></body></html>');
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  // A second, fail-closed network barrier covers native browser requests that
  // are not associated with a routed page. This proxy never opens an upstream
  // connection. Local fixture traffic bypasses it.
  const denyProxy = http.createServer((_req, res) => { res.writeHead(502); res.end('External network disabled in payment tests'); });
  denyProxy.on('connect', (_req, socket) => socket.end('HTTP/1.1 403 Forbidden\r\nConnection: close\r\n\r\n'));
  await new Promise(resolve => denyProxy.listen(0, '127.0.0.1', resolve));
  const base = `http://127.0.0.1:${server.address().port}`;
  const evidence = path.resolve(root, '../artifacts/payment-flow-20261019');
  fs.mkdirSync(evidence, { recursive: true });
  let browser;
  let passed = 0;
  try {
    browser = await chromium.launch({ ...(process.platform === 'win32' ? { channel: 'msedge' } : {}), headless: true,
      proxy: { server: `http://127.0.0.1:${denyProxy.address().port}`, bypass: '127.0.0.1' } });
    const errors = [];
    const externalAttempts = [];
    const context = await browser.newContext({ viewport: { width: 390, height: 844 }, locale: 'en-US', serviceWorkers: 'block' });
    context.setDefaultTimeout(10000);
    await context.route('**/*', route => {
      const url = new URL(route.request().url());
      if (url.origin === base) return route.continue();
      externalAttempts.push(url.href);
      return route.abort();
    });
    context.on('page', page => page.on('pageerror', error => errors.push(error.message)));
    const page = await context.newPage();
    const events = name => page.evaluate(name => window.__premiumFixture.events.filter(event => event.event_name === name), name);
    const waitEvent = name => page.waitForFunction(name => window.__premiumFixture.events.some(event => event.event_name === name), name);
    const go = async scenario => { await page.goto(`${base}/?case=${scenario}`); await page.getByRole('link', { name: /Keep Premium|Subscribe on Ko-fi/ }).first().waitFor(); };
    const handoff = async (link, button = 'left') => {
      assert.equal(await link.getAttribute('href'), 'https://ko-fi.com/arvio/tiers', 'native provider URL has no email, journey ID or tracking query');
      assert.equal(await link.getAttribute('target'), '_blank');
      assert.match(await link.getAttribute('rel'), /noopener/);
      const externalBefore = externalAttempts.length;
      // Modified native links open a new background tab, which need not be
      // reported as the opener page's "popup" event.
      const popupPromise = context.waitForEvent('page');
      if (button === 'enter') { await link.focus(); await link.press('Enter'); }
      else await link.click({ button: button === 'control' ? 'left' : button, ...(button === 'control' ? { modifiers: ['ControlOrMeta'] } : {}) });
      let popup;
      try { popup = await popupPromise; }
      catch (error) { console.error('Native handoff failed', { button, externalAttempts: externalAttempts.slice(externalBefore) }); throw error; }
      await waitEvent('checkout_opened');
      await popup.close();
    };
    const returnToApp = () => page.evaluate(() => { window.dispatchEvent(new Event('blur')); window.dispatchEvent(new Event('focus')); });
    const noOverflow = async name => {
      assert.equal(await page.evaluate(() => document.documentElement.scrollWidth > innerWidth), false, `${name}: no page overflow`);
      for (const dialog of await page.getByRole('dialog').all()) assert.equal(await dialog.evaluate(element => element.scrollWidth > element.clientWidth), false, `${name}: no dialog overflow`);
    };
    const focusOrderScenario = async () => {
      await go('checkout');
      // Some browsers deliver visibilitychange before the newly visible page
      // actually receives focus. This event must not consume the return.
      await page.evaluate(() => { Object.defineProperty(document, 'hasFocus', { configurable: true, value: () => false }); });
      await handoff(page.getByRole('link', { name: /Keep Premium/ }), 'control');
      await page.evaluate(() => { document.dispatchEvent(new Event('visibilitychange')); });
      assert.equal(await page.evaluate(() => window.__premiumFixture.statusRequests), 0, 'visible-but-unfocused tabs do not poll');
      assert.equal((await events('checkout_returned')).length, 0, 'visibility without focus does not prematurely count a return');
      await page.evaluate(() => {
        window.__premiumFixture.markPaid();
        Object.defineProperty(document, 'hasFocus', { configurable: true, value: () => true });
        window.dispatchEvent(new Event('focus'));
      });
      await page.getByTestId('activation-result').filter({ hasText: /^Paid activation/ }).waitFor();
      await waitEvent('checkout_returned');
      await waitEvent('payment_status_checked');
      assert.equal(await page.evaluate(() => window.__premiumFixture.statusRequests), 1, 'later real focus checks and activates exactly once');
      passed++;
    };
    const exhaustedCycleScenario = async () => {
      // A late payment must recover after bounded polling has exhausted, both
      // through manual reopen and through another genuine return from Ko-fi.
      await go('checkout');
      await page.evaluate(() => { Object.defineProperty(document, 'hasFocus', { configurable: true, value: () => false }); });
      await handoff(page.getByRole('link', { name: /Keep Premium/ }), 'control');
      await page.evaluate(() => {
        Object.defineProperty(document, 'hasFocus', { configurable: true, value: () => true });
        window.dispatchEvent(new Event('focus'));
      });
      await page.waitForFunction(() => window.__premiumFixture.statusRequests === 4, null, { timeout: 35000 });
      assert.match(await page.getByTestId('activation-result').innerText(), /No paid/);
      await page.keyboard.press('Escape');
      await page.getByRole('button', { name: 'Already paid? Check access', exact: true }).click();
      await page.waitForFunction(() => window.__premiumFixture.statusRequests === 5);
      assert.match(await page.getByTestId('activation-result').innerText(), /No paid/, 'manual recovery cannot turn trial access into paid access');
      await page.waitForFunction(() => window.__premiumFixture.statusRequests === 8, null, { timeout: 35000 });
      await page.evaluate(() => { window.__premiumFixture.markPaid(); });
      await returnToApp();
      await page.getByTestId('activation-result').filter({ hasText: /^Paid activation/ }).waitFor();
      assert.equal(await page.evaluate(() => window.__premiumFixture.statusRequests), 9, 'manual reopen and new return restart exhausted four-check cycles; paid access stops checking');
      passed++;
    };
    // A bounded subset is useful while reviewing recovery-only changes. The
    // default command always exercises the complete UI suite below.
    if (process.env.PAYMENT_UI_ONLY === 'focus-order' || process.env.PAYMENT_UI_ONLY === 'recovery-regressions') {
      await focusOrderScenario();
      if (process.env.PAYMENT_UI_ONLY === 'recovery-regressions') await exhaustedCycleScenario();
      assert.deepEqual(errors, []);
      console.log(`Payment recovery UI passed ${passed} targeted scenarios (${process.env.PAYMENT_UI_ONLY}); external network, emails and real payments blocked.`);
      return;
    }
    for (const [name, width, height] of [['small-phone', 320, 720], ['mobile', 390, 844], ['desktop', 1440, 900]]) {
      await page.setViewportSize({ width, height });
      await go('checkout');
      await waitEvent('membership_cta_viewed');
      assert.equal(await page.getByRole('dialog').count(), 0, `${name}: no pre-payment modal`);
      assert.match(await page.locator('body').innerText(), /tester@example\.test/);
      assert.equal(await page.evaluate(() => window.__premiumFixture.statusRequests), 0, 'showing the CTA does not make an access preflight');
      await noOverflow(name);
      await page.screenshot({ path: path.join(evidence, `direct-checkout-${name}.png`) });
      await handoff(page.getByRole('link', { name: /Keep Premium/ }));
      await page.getByRole('dialog', { name: 'Finish your membership' }).waitFor();
      assert.equal((await events('checkout_opened')).length, 1, 'one click records one actual handoff');
      await noOverflow(name);
      await page.screenshot({ path: path.join(evidence, `payment-recovery-${name}.png`) });
      await page.keyboard.press('Escape');
      assert.equal(await page.getByRole('dialog').count(), 0, 'Escape closes recovery');
      await page.getByRole('button', { name: 'Already paid? Check access', exact: true }).click();
      await waitEvent('checkout_recovery_opened');
      await page.getByRole('dialog').getByRole('button', { name: 'Close', exact: true }).click();
      assert.equal((await events('checkout_opened')).length, 1, 'opening recovery is not counted as a second checkout');
      passed++;
    }

    await page.goto(`${base}/?case=checkout-hidden`);
    await page.getByRole('heading', { name: 'Isolated membership checkout' }).waitFor();
    // Wait for an observer turn, not a real customer/network operation.
    await page.evaluate(() => new Promise(resolve => requestAnimationFrame(() => requestAnimationFrame(resolve))));
    assert.equal((await events('membership_cta_viewed')).length, 0, 'hidden offer is not a viewed CTA');
    await page.evaluate(() => window.__premiumFixture.setOfferVisible(true));
    await waitEvent('membership_cta_viewed');
    assert.equal((await events('membership_cta_viewed')).length, 1, 'visible offer is recorded once');
    await page.evaluate(() => window.__premiumFixture.setOfferMounted(false));
    await page.getByTestId('fixture-offer').waitFor({ state: 'detached' });
    await page.evaluate(() => { window.dispatchEvent(new Event('focus')); document.dispatchEvent(new Event('visibilitychange')); });
    assert.equal((await events('membership_cta_viewed')).length, 1, 'unmounted observer does not record more views');
    passed++;

    await go('checkout');
    await handoff(page.getByRole('link', { name: /Keep Premium/ }), 'middle');
    assert.equal((await events('checkout_opened')).length, 1, 'native middleclick is counted once');
    await page.getByRole('dialog', { name: 'Finish your membership' }).waitFor();
    await page.keyboard.press('Escape');
    passed++;

    for (const method of ['enter', 'control']) {
      await go('checkout');
      await handoff(page.getByRole('link', { name: /Keep Premium/ }), method);
      assert.equal((await events('checkout_opened')).length, 1, `native ${method} activation is counted once`);
      await page.getByRole('dialog').waitFor();
      await page.keyboard.press('Escape');
      const before = externalAttempts.length;
      const recoveryButton = page.getByRole('button', { name: 'Check payment status', exact: true });
      await recoveryButton.focus();
      await recoveryButton.press('Space');
      await page.getByRole('dialog').waitFor();
      assert.equal(externalAttempts.length, before, 'subsequent Space activation reopens recovery without another native handoff');
      assert.equal((await events('checkout_opened')).length, 1);
      passed++;
    }

    // Slow or broken analytics and entitlement requests cannot block the native
    // handoff. Access is only checked after returning/opening recovery.
    for (const mode of ['error', 'pending']) {
      await go('checkout');
      await page.evaluate(mode => { window.__premiumFixture.statusMode = mode; window.__premiumFixture.analyticsMode = mode; }, mode);
      await handoff(page.getByRole('link', { name: /Keep Premium/ }));
      await returnToApp();
      await page.getByRole('dialog').getByText(mode === 'error' ? /Could not check access/ : 'Checking your payment status…').waitFor();
      assert.equal(await page.getByRole('dialog').getByRole('link', { name: /Return to Ko-fi/ }).isVisible(), true, 'provider link remains available during checks/outages');
      await page.keyboard.press('Escape');
      passed++;
    }

    await go('delayed-payment');
    await handoff(page.getByRole('link', { name: /Keep Premium/ }));
    await returnToApp();
    await page.getByText('Membership active', { exact: true }).waitFor({ timeout: 20000 });
    // EntitlementGate also refreshes on focus and may activate/unmount the
    // checkout before its own return listener. Assert the user outcome here;
    // the isolated focus-order scenario asserts checkout-specific telemetry.
    assert.equal((await events('checkout_opened')).length, 1);
    passed++;

    await focusOrderScenario();
    await exhaustedCycleScenario();

    // The recovery path still requires proof of ownership, not just knowing a
    // billing address. All requests remain in the fixture transport.
    await go('checkout');
    await page.getByRole('button', { name: 'Already paid? Check access', exact: true }).click();
    const dialog = page.getByRole('dialog');
    const billingToggle = dialog.getByRole('button', { name: /Paid with a different email/ });
    if (await billingToggle.getAttribute('aria-expanded') !== 'true') await billingToggle.click();
    await dialog.getByLabel('Ko-fi / PayPal email').fill('billing@example.test');
    await dialog.getByRole('button', { name: 'Send verification code', exact: true }).click();
    await dialog.getByLabel('Code sent to your billing email').fill('0000000000000000');
    await dialog.getByRole('button', { name: 'Verify email', exact: true }).click();
    await dialog.getByRole('alert').filter({ hasText: /Invalid or expired/ }).waitFor();
    assert.match(await page.getByTestId('activation-result').innerText(), /No paid/);
    await dialog.getByLabel('Code sent to your billing email').fill('1234567890abcdef');
    await dialog.getByRole('button', { name: 'Verify email', exact: true }).click();
    await page.getByTestId('activation-result').filter({ hasText: /^Paid activation/ }).waitFor();
    await waitEvent('membership_linked');
    assert.doesNotMatch(JSON.stringify(await page.evaluate(() => window.__premiumFixture.events)), /tester@example|billing@example|1234567890abcdef/, 'billing recovery telemetry contains no account/billing email or verification code');
    assert.equal((await events('checkout_opened')).length, 0, 'email recovery is not a checkout');
    passed++;

    // A paid response belonging to an earlier account must not unlock the new
    // account or retain that account's recovery dialog.
    await go('checkout');
    await page.evaluate(() => { window.__premiumFixture.deferNextStatus = true; });
    await page.getByRole('button', { name: 'Already paid? Check access', exact: true }).click();
    await page.waitForFunction(() => window.__premiumFixture.statusRequests === 1);
    await page.evaluate(() => { window.__premiumFixture.setAccount('second-fixture'); });
    await page.getByRole('dialog').waitFor({ state: 'detached' });
    await page.evaluate(() => { window.__premiumFixture.completeStatusAsPaid(); });
    assert.match(await page.getByTestId('activation-result').innerText(), /No paid/);
    assert.match(await page.locator('body').innerText(), /second-fixture@example\.test/);
    passed++;

    await go('checkout');
    await page.getByRole('button', { name: 'Already paid? Check access', exact: true }).click();
    await page.getByRole('dialog').getByRole('button', { name: /Paid with a different email/ }).click();
    await page.getByRole('dialog').getByLabel('Ko-fi / PayPal email').fill('billing@example.test');
    await page.getByRole('dialog').getByRole('button', { name: 'Send verification code', exact: true }).click();
    await page.getByRole('dialog').getByLabel('Code sent to your billing email').fill('1234567890abcdef');
    await page.evaluate(() => { window.__premiumFixture.deferNextLink = true; });
    await page.getByRole('dialog').getByRole('button', { name: 'Verify email', exact: true }).click();
    await page.waitForFunction(() => window.__premiumFixture.billingRequests.length === 2);
    await page.evaluate(() => { window.__premiumFixture.setAccount('second-fixture'); });
    await page.getByRole('dialog').waitFor({ state: 'detached' });
    await page.evaluate(() => { window.__premiumFixture.completeLinkAsPaid(); });
    assert.match(await page.getByTestId('activation-result').innerText(), /No paid/);
    passed++;

    await go('checkout');
    await page.evaluate(() => { window.__premiumFixture.deferNextStatus = true; });
    await page.getByRole('button', { name: 'Already paid? Check access', exact: true }).click();
    await page.waitForFunction(() => window.__premiumFixture.statusRequests === 1);
    await page.keyboard.press('Escape');
    await page.evaluate(() => { window.__premiumFixture.completeStatusAsPaid(); });
    assert.match(await page.getByTestId('activation-result').innerText(), /No paid/);
    passed++;

    // Full paywall integration retains a direct purchase path as well.
    await go('paywall');
    assert.equal(await page.getByRole('dialog').count(), 0);
    await handoff(page.getByRole('link', { name: 'Subscribe on Ko-fi', exact: true }));
    await page.getByRole('dialog', { name: 'Finish your membership' }).waitFor();
    await noOverflow('paywall');
    passed++;

    const recordedEvents = await page.evaluate(() => window.__premiumFixture.events);
    assert.doesNotMatch(JSON.stringify(recordedEvents), /tester@example|billing@example|1234567890abcdef/, 'analytics excludes billing/account emails and verification codes');
    assert.equal(externalAttempts.every(url => url === 'https://ko-fi.com/arvio/tiers'), true, 'all attempted external navigation is the expected native provider handoff');
    assert.ok(externalAttempts.length >= 7, 'native handoffs were exercised; every external request was blocked');
    assert.deepEqual(errors, [], 'no uncaught UI errors');
    console.log(`Payment UI passed ${passed} scenarios: small/mobile/desktop direct native handoff; slow/offline telemetry and access; delayed activation; verified email recovery; account/close guards; paywall; privacy. External network, real email and payments blocked. Screenshots: ${evidence}`);
  } finally {
    if (browser) await browser.close();
    await new Promise(resolve => server.close(resolve));
    await new Promise(resolve => denyProxy.close(resolve));
  }
})().catch(error => { console.error(error); process.exitCode = 1; });
