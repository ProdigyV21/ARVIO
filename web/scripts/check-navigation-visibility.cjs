// Real Next app + real settings/outbox/cloud code, with an isolated account endpoint.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const http = require('node:http');
const path = require('node:path');
const { execFile } = require('node:child_process');
const { promisify } = require('node:util');
const { chromium, expect } = require('@playwright/test');
const { _test: { mergeWithStoredSnapshot } } = require('../../netlify-auth-site/netlify/functions/account-sync-push');

const origin = process.env.NAVIGATION_TEST_URL || 'http://127.0.0.1:38787';
const evidence = path.resolve(__dirname, '../../artifacts/issue887-web');
const first = 'issue887-first', second = 'issue887-second';
const profiles = [first, second].map((id, i) => ({ id, name: `Navigation ${i + 1}`, avatarId: 0, avatarColor: 0xff367b72,
  isKidsProfile: false, isLocked: false, pin: null, createdAt: 1, lastUsedAt: 1, updatedAt: 1 }));
const session = { userId: 'issue887-account', email: 'navigation-fixture@arvio.invalid', provider: 'netlify',
  accessToken: 'fixture-not-a-real-token', refreshToken: 'fixture', expiresAt: Date.now() + 86_400_000 };
let payload = { version: 1, updatedAt: Date.now(), userId: session.userId, profiles, activeProfileId: first,
  skipProfileSelection: true, profileSettingsById: { [first]: {}, [second]: {} },
  catalogsByProfile: { [first]: [], [second]: [] },
  hiddenPreinstalledByProfile: { [first]: ['collection_defaults_disabled'], [second]: ['collection_defaults_disabled'] },
  addonsByProfile: { [first]: [], [second]: [] }, fieldUpdatedAt: {}, iptvByProfile: { [first]: { playlists: [] }, [second]: { playlists: [] } } };
let androidWrites = 0;

function accept(incoming, base) {
  payload = mergeWithStoredSnapshot({ payload }, incoming, base);
}

async function backend(route) {
  const request = route.request();
  const url = new URL(request.url());
  if (url.pathname.endsWith('account-sync-pull')) return route.fulfill({ json: { payload } });
  if (url.pathname.endsWith('account-sync-push')) {
    const body = request.postDataJSON();
    accept(body.payload, body.baseUpdatedAtMs);
    return route.fulfill({ json: { accepted: true } });
  }
  if (/partner|managed/.test(url.pathname)) return route.fulfill({ json: { managed: false, active: false } });
  if (/entitlement/.test(url.pathname)) return route.fulfill({ json: { entitled: true, reason: 'admin' } });
  return route.fulfill({ json: {} });
}

async function pageFor(browser, viewport, profileId = first) {
  const context = await browser.newContext({ viewport, reducedMotion: 'reduce' });
  await context.addInitScript(({ session, profiles, profileId }) => {
    if (localStorage.getItem('issue887-fixture')) return;
    localStorage.setItem('issue887-fixture', '1');
    localStorage.setItem('arvio.web.supabase.session', JSON.stringify(session));
    localStorage.setItem('arvio.web.profiles', JSON.stringify(profiles));
    localStorage.setItem('arvio.web.activeProfileId', JSON.stringify(profileId));
    localStorage.setItem('arvio.web.profilesOwner', JSON.stringify(session.email));
    localStorage.setItem('arvio.web.settings', JSON.stringify({ skipProfileSelection: true, language: 'en-US',
      catalogs: [], hiddenCatalogIds: ['collection_defaults_disabled'], iptvPlaylists: [] }));
  }, { session, profiles, profileId });
  await context.route('**/*', async route => {
    const url = new URL(route.request().url());
    if (url.hostname === 'arvio-sync-fixture.invalid' || /account-sync-(pull|push)/.test(url.pathname)) return backend(route);
    if (url.origin === origin && !url.pathname.startsWith('/api/')) return route.continue();
    if (route.request().resourceType() === 'image') return route.fulfill({ contentType: 'image/png', body: Buffer.from('iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAwMCAO+jD1sAAAAASUVORK5CYII=', 'base64') });
    return route.fulfill({ json: { results: [], genres: [], catalogs: [], streams: [], items: [] } });
  });
  const page = await context.newPage();
  const errors = [];
  page.on('pageerror', error => errors.push(error.message));
  await page.goto(origin);
  await settingsButton(page).waitFor({ timeout: 120_000 });
  return { page, context, errors };
}

function settingsButton(page) {
  return page.locator('.sidebar .settings-gear:visible, .mobile-bottom-nav button[aria-label="Settings"]:visible');
}

async function appearance(page) {
  await settingsButton(page).click();
  await page.locator('.st-shell').waitFor();
  const category = page.getByRole('button', { name: /Home and appearance/ }).first();
  if (await category.isVisible()) await category.click();
  await page.getByRole('switch', { name: 'Show TV Player', exact: true }).waitFor();
}

async function toggle(page, label, visible) {
  const control = page.getByRole('switch', { name: label, exact: true });
  if ((await control.getAttribute('aria-checked')) !== String(visible)) await control.click();
  await expect(control).toHaveAttribute('aria-checked', String(visible));
}

async function fits(page, name) {
  const layout = await page.evaluate(() => {
    const controls = [...document.querySelectorAll('[role="switch"]')].filter(node => node.getBoundingClientRect().width > 0);
    return { width: innerWidth, scrollWidth: document.documentElement.scrollWidth,
      outside: controls.some(node => { const rect = node.getBoundingClientRect(); return rect.left < -1 || rect.right > innerWidth + 1; }) };
  });
  assert(layout.scrollWidth <= layout.width + 1 && !layout.outside, `${name}: no horizontal overflow`);
}

async function main() {
  fs.mkdirSync(evidence, { recursive: true });
  const bridge = http.createServer(async (req, res) => {
    if (req.url !== '/fixture/payload') { res.writeHead(404); return res.end(); }
    res.setHeader('Content-Type', 'application/json');
    if (req.method === 'GET') return res.end(JSON.stringify({ payload }));
    let raw = '';
    for await (const chunk of req) raw += chunk;
    const body = JSON.parse(raw);
    const incoming = structuredClone(payload);
    incoming.profileSettingsById[body.profileId].hiddenNavigationSections = body.settings.hiddenNavigationSections;
    incoming.fieldUpdatedAt[`p:${body.profileId}:hiddenNavigationSections`] = body.updatedAt;
    accept(incoming);
    androidWrites++;
    res.end(JSON.stringify({ accepted: true }));
  });
  await new Promise(resolve => bridge.listen(0, '127.0.0.1', resolve));
  const browser = await chromium.launch({ channel: process.env.UI_BROWSER_CHANNEL || 'msedge', headless: true });
  try {
    for (const [name, width, height] of [['desktop', 1440, 1000], ['phone', 390, 844], ['small-phone', 320, 568], ['tablet', 820, 1180], ['landscape', 844, 390]]) {
      const fixture = await pageFor(browser, { width, height });
      const { page, errors, context } = fixture;
      await appearance(page);
      for (const [label, id] of [['Show Search', 'search'], ['Show Library', 'watchlist'], ['Show TV Player', 'tv']]) {
        await toggle(page, label, false);
        await expect(page.locator(`[data-nav-section="${id}"]`)).toHaveCount(0);
      }
      await expect.poll(() => payload.profileSettingsById[first].hiddenNavigationSections).toEqual(['search', 'watchlist', 'tv']);
      assert.equal(await page.locator('[data-nav-section="home"]:visible').count(), 1);
      assert.equal(await settingsButton(page).count(), 1);
      await fits(page, name);
      await page.getByRole('switch', { name: 'Show TV Player' }).scrollIntoViewIfNeeded();
      await page.screenshot({ path: path.join(evidence, `${name}-hidden.png`) });
      await page.reload();
      await appearance(page);
      for (const label of ['Show Search', 'Show Library', 'Show TV Player']) await expect(page.getByRole('switch', { name: label, exact: true })).toHaveAttribute('aria-checked', 'false');
      for (const label of ['Show Search', 'Show Library', 'Show TV Player']) await toggle(page, label, true);
      await expect.poll(() => payload.profileSettingsById[first].hiddenNavigationSections).toEqual([]);
      await expect(page.locator('[data-nav-section="tv"]:visible')).toHaveCount(1);
      assert.deepEqual(errors, [], `${name}: no runtime errors`);
      console.log(`${name}: switches, persistence, navigation and layout passed`);
      await context.close();
    }

    const phone = await pageFor(browser, { width: 390, height: 844 });
    const peer = await pageFor(browser, { width: 1440, height: 1000 });
    await appearance(phone.page);
    await toggle(phone.page, 'Show TV Player', false);
    await expect.poll(() => payload.profileSettingsById[first].hiddenNavigationSections).toEqual(['tv']);
    await peer.page.reload();
    await appearance(peer.page);
    await expect(peer.page.getByRole('switch', { name: 'Show TV Player' })).toHaveAttribute('aria-checked', 'false');
    await expect(peer.page.locator('[data-nav-section="tv"]')).toHaveCount(0);
    console.log('phone-to-independent-desktop-context sync passed');

    const other = await pageFor(browser, { width: 1440, height: 1000 }, second);
    await appearance(other.page);
    await expect(other.page.getByRole('switch', { name: 'Show TV Player' })).toHaveAttribute('aria-checked', 'true');
    await other.page.getByRole('button', { name: 'Switch profile', exact: true }).filter({ visible: true }).click();
    await other.page.locator(`[data-profile-id="${first}"]`).click();
    await appearance(other.page);
    await expect(other.page.getByRole('switch', { name: 'Show TV Player' })).toHaveAttribute('aria-checked', 'false');
    await other.page.getByRole('button', { name: 'Switch profile', exact: true }).filter({ visible: true }).click();
    await other.page.locator(`[data-profile-id="${second}"]`).click();
    await appearance(other.page);
    await expect(other.page.getByRole('switch', { name: 'Show TV Player' })).toHaveAttribute('aria-checked', 'true');
    await other.context.close();
    console.log('another profile keeps its own visible tabs, including switching profiles in place');

    if (process.argv.includes('--android')) {
      await peer.page.locator('[data-nav-section="watchlist"]:visible').focus();
      const adb = process.env.ADB || 'adb';
      const serial = process.env.NAVIGATION_TEST_SERIAL || 'emulator-5598';
      const expectedAvd = process.env.NAVIGATION_TEST_AVD || 'codex_issue894';
      assert.match(serial, /^emulator-\d+$/, 'physical devices are not permitted');
      const run = promisify(execFile);
      const avd = await run(adb, ['-s', serial, 'emu', 'avd', 'name']);
      assert.equal(avd.stdout.trim().split(/\r?\n/)[0].trim(), expectedAvd, 'only the chosen isolated emulator is permitted');
      const url = `http://10.0.2.2:${bridge.address().port}/fixture/payload`;
      const result = await run(adb, ['-s', serial, 'shell', 'timeout', '-k', '5s', '360s', 'am', 'instrument', '-w',
        '-e', 'class', 'com.arflix.tv.ui.screens.settings.NavigationVisibilityDeviceTest', '-e', 'navigationSyncUrl', url,
        'com.arvio.tv.test/androidx.test.runner.AndroidJUnitRunner'], { timeout: 390_000, maxBuffer: 4_000_000 });
      fs.writeFileSync(path.join(evidence, 'emulator-tests.log'), result.stdout + result.stderr);
      console.log(result.stdout);
      assert.match(result.stdout, /OK \(6 tests\)/);
      assert.equal(androidWrites, 1, 'Android restored the web choice and pushed its own edit back');
      assert.deepEqual(payload.profileSettingsById[first].hiddenNavigationSections, ['watchlist', 'tv']);
      await expect(async () => {
        await peer.page.evaluate(() => window.dispatchEvent(new Event('focus')));
        await expect(peer.page.locator('[data-nav-section="watchlist"]')).toHaveCount(0, { timeout: 2_000 });
      }).toPass({ timeout: 45_000 });
      await expect(peer.page.locator('[data-nav-section="home"]:visible')).toBeFocused();
      await expect(async () => {
        await phone.page.evaluate(() => window.dispatchEvent(new Event('focus')));
        await expect(phone.page.getByRole('switch', { name: 'Show Library' })).toHaveAttribute('aria-checked', 'false', { timeout: 2_000 });
      }).toPass({ timeout: 45_000 });
      await expect(phone.page.getByRole('switch', { name: 'Show Library' })).toHaveAttribute('aria-checked', 'false');
      await expect(phone.page.getByRole('switch', { name: 'Show TV Player' })).toHaveAttribute('aria-checked', 'false');
      await expect(phone.page.locator('[data-nav-section="watchlist"], [data-nav-section="tv"]')).toHaveCount(0);
      console.log('web-to-emulator-to-web round trip and automatic foreground refresh passed');
    }
    assert.deepEqual([...phone.errors, ...peer.errors], []);
    await phone.context.close();
    await peer.context.close();
  } finally {
    await browser.close();
    await new Promise(resolve => bridge.close(resolve));
  }
}

main().catch(error => { console.error(error); process.exitCode = 1; });
