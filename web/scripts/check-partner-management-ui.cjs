/* Actual dashboard and PostgreSQL service; disposable accounts, no real payments or messages. */
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const os = require('node:os');
const http = require('node:http');
const crypto = require('node:crypto');
const esbuild = require('esbuild');
const { chromium } = require('@playwright/test');
const { PGlite } = require('../../netlify-auth-site/node_modules/@electric-sql/pglite');
const { createService } = require('../../netlify-auth-site/netlify/functions/_partner-service');

async function main() {
  const preview = process.argv.includes('--serve');
  const started = Date.now();
  const root = path.resolve(__dirname, '..');
  const out = fs.mkdtempSync(path.join(os.tmpdir(), 'arvio-partner-management-'));
  const evidence = path.resolve(root, '../artifacts/partner-management');
  fs.mkdirSync(evidence, { recursive: true });
  await esbuild.build({ entryPoints: [path.join(root, 'tests/partner-management/entry.tsx')], bundle: true,
    outfile: path.join(out, 'app.js'), jsx: 'automatic', define: { 'process.env.NODE_ENV': '"test"' },
    plugins: [{ name: 'isolated-management-fixture', setup(build) {
      build.onResolve({ filter: /^\.\/(auth|config)$/ }, args => args.importer.endsWith('partnerManagement.ts')
        ? { path: path.join(root, `tests/partner-management/${args.path.slice(2)}.ts`) } : undefined);
      build.onResolve({ filter: /^@\// }, args => ({ path: ['.tsx', '.ts', '.js', '']
        .map(ext => path.join(root, args.path.slice(2) + ext)).find(file => fs.existsSync(file)) }));
    } }]
  });
  const db = new PGlite();
  await db.exec(fs.readFileSync(path.join(root, '../netlify-auth-site/sql/partner.sql'), 'utf8'));
  let tail = Promise.resolve();
  const pool = { query: (...args) => db.query(...args), connect: async () => {
    const previous = tail; let release; tail = new Promise(resolve => { release = resolve; }); await previous;
    return { query: (...args) => db.query(...args), release };
  } };
  const mail = [];
  let clock = Date.now();
  const owner = { supabaseUserId: 'operator', email: 'operator@example.test' };
  const service = createService({ pool, master: crypto.randomBytes(32).toString('hex'), now: () => clock + (preview ? Date.now() - started : 0),
    sendEmail: async (...args) => mail.push(args) });
  await service.beginVerification(owner);
  const admin = await service.verify(owner, mail.at(-1)[2].match(/\b\d{6}\b/)[0]);
  let org = (await service.createOrganization(owner, admin.proof, 'Northside Media')).organization;
  await service.payment({ tier_name: 'ARVIO Partner 50', amount: '79.00', currency: 'USD', type: 'Subscription', is_subscription_payment: true,
    kofi_transaction_id: 'fixture-payment', email: owner.email, timestamp: new Date(clock).toISOString() });
  const command = async input => {
    org = (await service.overview(owner, admin.proof)).organization;
    const result = await service.command(owner, admin.proof, org.id, org.revision, input);
    org = result.organization; return result.target;
  };
  const template = await command({ action: 'save-template', name: 'Cinema essentials', config: { settings: { netflixHomeStyle: true, watchlistOnHome: true } } });
  const connection = await command({ action: 'save-service', name: 'Family media', kind: 'jellyfin', url: 'https://private.fixture.invalid' });
  const personalConnection = await command({ action: 'save-service', name: 'Your TV account', kind: 'iptv', url: 'https://tv.fixture.invalid', credentialMode: 'customer' });
  const pkg = await command({ action: 'save-package', name: 'Cinema', templateId: template, serviceIds: [connection, personalConnection] });
  for (const [index, name] of ['Alex Morgan', 'Sam Rivera', 'Taylor James', 'Jordan Lee', 'Casey Brooks', 'Riley Parker'].entries()) {
    const email = `viewer${index}@example.test`;
    await command({ action: 'invite-customer', name, email, packageId: pkg });
    if (index < 4) {
      const identity = { supabaseUserId: `viewer${index}`, email };
      const token = mail.at(-1)[2].match(/join#([\w-]+)/)[1];
      await service.invitation(identity, token, true);
      const device = { key: crypto.randomBytes(32).toString('base64url'), name: index % 2 ? 'Bedroom TV' : 'Living room TV', platform: 'android-tv', appliedRevision: 0 };
      const delivery = await service.delivery(identity, device);
      if (index < 3) await service.delivery(identity, { ...device, appliedRevision: delivery.revision });
    }
  }
  clock += 61000;
  let revisionConflict = false;
  const server = http.createServer(async (req, res) => {
    res.setHeader('cache-control', 'no-store');
    const pathname = new URL(req.url, 'http://localhost').pathname;
    if (preview && pathname === '/fixture/inbox') {
      res.setHeader('content-type', 'application/json');
      res.end(JSON.stringify(mail.slice(-10).map(message => ({ to: message[0], subject: message[1], body: message[2] })), null, 2));
      return;
    }
    if (pathname === '/fixture/partner-api') {
      try {
        const identity = req.headers.authorization === 'Bearer fixture-operator' ? owner : req.headers.authorization === 'Bearer fixture-viewer0' ? { supabaseUserId: 'viewer0', email: 'viewer0@example.test' } : null;
        if (!identity) throw Object.assign(new Error('Unauthorized'), { statusCode: 401 });
        let raw = ''; for await (const chunk of req) raw += chunk;
        const body = JSON.parse(raw);
        if (identity !== owner && !['membership', 'customer-credentials', 'leave'].includes(body.operation)) throw Object.assign(new Error('Forbidden'), { statusCode: 403 });
        let value;
        if (body.operation === 'membership') value = await service.membership(identity);
        else if (body.operation === 'customer-credentials') value = await service.customerCredentials(identity, body.serviceId, body.credentials);
        else if (body.operation === 'leave' && body.confirm === true) value = await service.leave(identity);
        else if (body.operation === 'verification-start') value = await service.beginVerification(owner);
        else if (body.operation === 'verification-complete') value = await service.verify(owner, body.code);
        else if (body.operation === 'overview') value = await service.overview(owner, body.proof);
        else if (body.operation === 'command') {
          if (revisionConflict) {
            revisionConflict = false;
            await service.command(owner, body.proof, body.organizationId, body.revision, { action: 'save-template', name: 'Concurrent draft', config: {} });
          }
          value = await service.command(owner, body.proof, body.organizationId, body.revision, body.command);
        } else throw Object.assign(new Error('Unknown operation'), { statusCode: 400 });
        res.setHeader('content-type', 'application/json'); res.end(JSON.stringify(value));
      } catch (error) { res.statusCode = error.statusCode || 500; res.setHeader('content-type', 'application/json'); res.end(JSON.stringify({ error: error.message })); }
      return;
    }
    if (pathname === '/' || pathname === '/partner' || pathname === '/partner/account') {
      res.setHeader('content-type', 'text/html');
      res.end('<html><head><title>ARVIO Partner - Local preview</title><meta name="viewport" content="width=device-width,initial-scale=1"/><link rel="stylesheet" href="/globals.css"/><link rel="stylesheet" href="/app.css"/></head><body>' +
        (preview ? '<aside style="position:fixed;bottom:8px;right:8px;z-index:10000;background:#202423;color:white;padding:8px 12px;font:13px sans-serif;border:1px solid #52605b;border-radius:4px">Local preview / Test data &nbsp; <a href="/fixture/inbox" target="_blank" rel="noopener" style="color:#9cebd1">Test inbox</a></aside>' : '') +
        '<div id="root"></div><script src="/app.js"></script></body></html>'); return;
    }
    const filename = { '/app.js': path.join(out, 'app.js'), '/app.css': path.join(out, 'app.css'), '/globals.css': path.join(root, 'app/globals.css'), '/arvio-icon-192.png': path.join(root, 'public/arvio-icon-192.png') }[pathname];
    if (!filename) { res.statusCode = 404; res.end(); return; }
    res.setHeader('content-type', pathname.endsWith('.css') ? 'text/css' : pathname.endsWith('.png') ? 'image/png' : 'application/javascript');
    fs.createReadStream(filename).pipe(res);
  });
  await new Promise(resolve => server.listen(0, '127.0.0.1', resolve));
  const origin = `http://127.0.0.1:${server.address().port}`;
  if (preview) {
    console.log(`Preview: ${origin}/partner\nAdmin: operator@example.test\nCustomer: viewer0@example.test\nPassword: fixture-only\nVerification messages: ${origin}/fixture/inbox\nLocal disposable data only. No real emails or payments.`);
    const stop = async () => { await new Promise(resolve => server.close(resolve)); await db.close(); };
    process.once('SIGINT', stop); process.once('SIGTERM', stop);
    return;
  }
  const browser = await chromium.launch({ channel: 'msedge', headless: true });
  const page = await browser.newPage({ viewport: { width: 1440, height: 1000 } });
  const errors = [];
  page.on('pageerror', error => errors.push(error.message));
  await page.route('**/*', route => new URL(route.request().url()).origin === origin ? route.continue() : route.abort());
  try {
    await page.goto(origin);
    await page.getByLabel('Email', { exact: true }).fill(owner.email);
    await page.getByLabel('Password', { exact: true }).fill('fixture-only');
    await page.getByRole('button', { name: 'Sign in', exact: true }).click();
    await page.getByRole('button', { name: 'Send verification code', exact: true }).click();
    await page.getByLabel('Email verification code').fill(mail.at(-1)[2].match(/\b\d{6}\b/)[0]);
    await page.getByRole('button', { name: 'Verify sign-in', exact: true }).click();
    await page.getByRole('heading', { name: 'Overview', exact: true }).waitFor();
    await page.screenshot({ path: path.join(evidence, 'overview-desktop.png'), fullPage: true });
    const nav = name => page.getByRole('navigation', { name: 'Partner navigation' }).getByRole('button', { name, exact: false });
    await nav('Customers').click();
    await page.getByLabel('Search customers').fill('Morgan');
    assert.equal(await page.locator('tbody tr').count(), 1);
    await page.getByLabel('Search customers').fill('');
    await page.getByRole('button', { name: 'Invite customer', exact: true }).click();
    await page.getByRole('dialog').getByLabel('Name', { exact: true }).fill('Robin Bell');
    await page.getByRole('dialog').getByLabel('Email', { exact: true }).fill('robin@example.test');
    await page.getByRole('dialog').getByLabel('Package', { exact: true }).selectOption({ label: 'Cinema' });
    await page.getByRole('button', { name: 'Send invitation', exact: true }).click();
    await page.getByRole('button', { name: 'Manage Robin Bell', exact: true }).waitFor();
    assert.match(mail.at(-1)[2], /join#/);
    await page.screenshot({ path: path.join(evidence, 'customers-desktop.png'), fullPage: true });
    await page.getByRole('button', { name: 'Manage Robin Bell', exact: true }).click();
    await page.getByRole('button', { name: 'Set credentials', exact: false }).first().click();
    await page.getByLabel('Access token', { exact: true }).fill('never-render-this-token');
    await page.getByLabel('User ID', { exact: true }).fill('robin-id');
    await page.getByRole('button', { name: 'Save and publish', exact: true }).click();
    await page.getByRole('dialog').waitFor({ state: 'hidden' });
    assert.equal((await page.locator('body').innerText()).includes('never-render-this-token'), false);
    await nav('Connections').click();
    await page.getByRole('button', { name: 'Add connection', exact: true }).click();
    await page.getByLabel('Connection name', { exact: true }).fill('Live and VOD QA');
    await page.getByLabel('IPTV format', { exact: true }).selectOption('m3u');
    await page.getByLabel('VOD movies', { exact: true }).uncheck();
    await page.getByLabel('Catch-up', { exact: true }).uncheck();
    await page.getByLabel('Service endpoint', { exact: true }).fill('https://iptv.fixture.invalid');
    await page.screenshot({ path: path.join(evidence, 'iptv-connection-desktop.png'), fullPage: true });
    await page.getByRole('button', { name: 'Save connection', exact: true }).click();
    await page.getByRole('dialog').waitFor({ state: 'hidden' });
    await page.getByRole('button', { name: 'Edit Live and VOD QA', exact: true }).click();
    assert.equal(await page.getByLabel('IPTV format', { exact: true }).inputValue(), 'm3u');
    assert.equal(await page.getByLabel('VOD movies', { exact: true }).isChecked(), false);
    await page.getByRole('button', { name: 'Cancel', exact: true }).click();
    await page.getByRole('button', { name: 'Add connection', exact: true }).click();
    await page.getByLabel('Connection name', { exact: true }).fill('Sports add-on QA');
    await page.getByLabel('Type', { exact: true }).selectOption('addon');
    await page.getByLabel('Service endpoint', { exact: true }).fill('https://addon.fixture.invalid/manifest.json');
    await page.getByRole('button', { name: 'Save connection', exact: true }).click();
    await page.getByRole('dialog').waitFor({ state: 'hidden' });
    await nav('Templates').click();
    await page.getByRole('button', { name: 'New template', exact: true }).click();
    await page.getByLabel('Template name', { exact: true }).fill('Family evenings');
    await page.getByLabel('Autoplay trailers', { exact: true }).selectOption('off');
    await page.getByRole('checkbox', { name: /Sports add-on QA/ }).check();
    await page.getByRole('checkbox', { name: /Live and VOD QA/ }).check();
    await page.screenshot({ path: path.join(evidence, 'template-desktop.png'), fullPage: true });
    revisionConflict = true;
    await page.getByRole('button', { name: 'Save template', exact: true }).click();
    await page.getByRole('dialog').getByRole('alert').waitFor();
    assert.equal(await page.getByLabel('Template name', { exact: true }).inputValue(), 'Family evenings');
    await page.getByRole('button', { name: 'Reload workspace', exact: true }).click();
    await page.getByRole('dialog').getByRole('alert').waitFor({ state: 'hidden' });
    await page.getByRole('button', { name: 'Save template', exact: true }).click();
    await page.getByRole('dialog').waitFor({ state: 'hidden' });
    await page.getByRole('button', { name: 'Edit Family evenings', exact: true }).waitFor();
    await page.getByRole('button', { name: 'Refresh workspace', exact: true }).click();
    await page.getByRole('button', { name: 'Edit Family evenings', exact: true }).click();
    assert.equal(await page.getByRole('checkbox', { name: /Sports add-on QA/ }).isChecked(), true);
    assert.equal(await page.getByRole('checkbox', { name: /Live and VOD QA/ }).isChecked(), true);
    await page.getByRole('button', { name: 'Cancel', exact: true }).click();
    await nav('Packages').click();
    await page.getByRole('button', { name: 'Publish', exact: true }).click();
    await page.getByRole('dialog').getByRole('button', { name: 'Confirm', exact: true }).click();
    await page.getByRole('dialog').waitFor({ state: 'hidden' });
    await nav('Devices').click();
    await page.screenshot({ path: path.join(evidence, 'devices-desktop.png'), fullPage: true });
    await page.setViewportSize({ width: 390, height: 844 });
    await nav('Connections').click();
    await page.getByRole('button', { name: 'Edit Live and VOD QA', exact: true }).click();
    await page.screenshot({ path: path.join(evidence, 'iptv-connection-mobile.png'), fullPage: true });
    assert.equal(await page.getByRole('dialog').evaluate(dialog => dialog.scrollWidth <= dialog.clientWidth), true, 'IPTV form must not overflow horizontally');
    assert.equal(await page.getByRole('button', { name: 'Save connection', exact: true }).evaluate(button => {
      const box = button.getBoundingClientRect();
      return box.top >= 0 && box.bottom <= innerHeight;
    }), true, 'Long IPTV forms keep their save action in view');
    await page.getByRole('button', { name: 'Cancel', exact: true }).click();
    await nav('Customers').click();
    assert.equal(await page.locator('.partner-sidebar nav button').evaluateAll(buttons => buttons.every((button, index) => {
      const box = button.getBoundingClientRect();
      const text = button.querySelector('span').getBoundingClientRect();
      const previous = buttons[index - 1]?.getBoundingClientRect();
      return text.right <= box.right && (!previous || previous.right <= box.left);
    })), true, 'Mobile navigation labels must not overlap adjacent tabs');
    await page.screenshot({ path: path.join(evidence, 'customers-mobile.png'), fullPage: true });
    assert.equal(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), true, 'Page must not overflow horizontally');
    await page.getByRole('button', { name: 'Invite customer', exact: true }).click();
    await page.screenshot({ path: path.join(evidence, 'invite-mobile.png'), fullPage: true });
    await page.keyboard.press('Escape');
    await page.getByRole('dialog').waitFor({ state: 'hidden' });
    await nav('Billing').click();
    await page.screenshot({ path: path.join(evidence, 'billing-mobile.png'), fullPage: true });
    assert.equal(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), true);
    await page.goto(origin + '/partner/account');
    await page.getByLabel('Email', { exact: true }).fill('viewer0@example.test');
    await page.getByLabel('Password', { exact: true }).fill('fixture-only');
    await page.getByRole('button', { name: 'Sign in', exact: true }).click();
    await page.getByRole('heading', { name: 'Northside Media', exact: true }).waitFor();
    await page.getByRole('button', { name: 'Add your credentials' }).click();
    await page.getByLabel('Username', { exact: true }).fill('viewer-tv');
    await page.getByLabel('Password', { exact: true }).fill('private-viewer-password');
    await page.getByRole('button', { name: 'Save credentials' }).click();
    await page.getByRole('button', { name: 'Replace credentials' }).waitFor();
    await page.getByRole('button', { name: 'Replace credentials' }).click();
    await page.getByLabel('Connection format', { exact: true }).selectOption('m3u');
    await page.getByLabel('Playlist URL', { exact: true }).fill('https://iptv.fixture.invalid/list?token=private-m3u');
    await page.getByLabel('EPG URL (optional)', { exact: true }).fill('https://iptv.fixture.invalid/guide.xml');
    await page.getByRole('button', { name: 'Save credentials' }).click();
    await page.getByRole('button', { name: 'Replace credentials' }).waitFor();
    const delivered = await service.delivery({ supabaseUserId: 'viewer0', email: 'viewer0@example.test' }, { key: 'z'.repeat(40), name: 'QA browser', platform: 'web' });
    assert.equal(delivered.services.find(s => s.id === personalConnection).credentials.playlistUrl, 'https://iptv.fixture.invalid/list?token=private-m3u');
    assert.equal((await page.locator('body').innerText()).includes('fixture.invalid'), false);
    assert.equal(await page.locator('input[type="password"]').count(), 0);
    await page.screenshot({ path: path.join(evidence, 'account-mobile.png'), fullPage: true });
    assert.equal(await page.evaluate(() => document.documentElement.scrollWidth <= innerWidth), true);
    await page.getByRole('button', { name: 'Leave membership', exact: true }).click();
    await page.getByRole('button', { name: 'Confirm leave', exact: true }).click();
    await page.getByText('No managed membership is connected to this account.').waitFor();
    assert.equal((await service.membership({ supabaseUserId: 'viewer0', email: 'viewer0@example.test' })).managed, false);
    assert.deepEqual(errors, []);
    console.log('PASS: isolated PGlite-backed dashboard, sign-in verification, search, invite, credentials, conflict retention, add-on/IPTV template selection, IPTV capability editing, M3U/EPG delivery, publish, desktop/mobile layout, modal Escape, customer credentials and leaving. Screenshots: ' + evidence);
  } finally { await browser.close(); await new Promise(resolve => server.close(resolve)); await db.close(); }
}
main().catch(error => { console.error(error); process.exitCode = 1; });
