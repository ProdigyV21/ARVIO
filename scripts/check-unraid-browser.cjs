// Exercise the real production container UI, not a bundled component fixture.
// Only local containers are accepted. Provider responses are isolated offline;
// the runtime bootstrap is the sole API response read from the actual server.
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { chromium, expect } = require('../web/node_modules/@playwright/test');

const EXPECTED_PUBLIC_IDS = {
  traktClientId: 'unraid-trakt-public-id',
  simklClientId: 'unraid-simkl-public-id',
  simklV2ClientId: 'unraid-simkl-v2-public-id'
};
const BOOTSTRAP_DELAY_MS = 1500;
const EMPTY_METADATA = {
  page: 1, total_pages: 0, total_results: 0, results: [], genres: [],
  cast: [], crew: [], movie_results: [], tv_results: [], tv_episode_results: []
};

function localBaseUrl(value) {
  const url = new URL(value);
  assert.equal(url.protocol, 'http:', 'Use HTTP to a loopback test container.');
  assert.ok(['127.0.0.1', 'localhost', '[::1]'].includes(url.hostname),
    'Browser smoke checks only accept loopback hosts, never public/user servers.');
  assert.ok(!url.username && !url.password && !url.search && !url.hash && url.pathname === '/',
    'Use a bare loopback origin without credentials, query or path.');
  return url.origin;
}

async function assertIndependentUi(page, label) {
  await expect(page.locator('.cloud-connect-btn, .login-shell, .paywall, .paywall-boot, .premium-account'))
    .toHaveCount(0, { timeout: 15000 });
  assert.equal(await page.getByRole('button', {
    name: /Connect to Cloud|Sign In with ARVIO Cloud|Reconnect to Cloud|free trial/i
  }).count(), 0, `${label}: no hosted Cloud or trial actions`);
  assert.equal(await page.locator('a[href*="ko-fi.com"], a[href*="kofi.com"]').count(), 0,
    `${label}: no subscription checkout`);
}

async function exerciseContext(browser, baseUrl, scenario, evidenceDir) {
  const context = await browser.newContext({
    viewport: { width: 1440, height: 1000 }, locale: 'en-US',
    colorScheme: 'dark', serviceWorkers: 'block'
  });
  const stats = {
    scenario, bootstrapRequests: 0, delayedBootstrapMs: 0,
    mockedMetadataRequests: 0, blockedExternalRequests: 0,
    unexpectedApiRequests: 0, forbiddenServiceRequests: 0
  };
  const pageErrors = [];
  try {
    await context.route('**/*', async route => {
      const url = new URL(route.request().url());
      const forbiddenHost = /^(?:auth|web)\.arvio\.tv$/.test(url.hostname) ||
        /(?:^|\.)(?:trakt\.tv|simkl\.com|supabase\.co|google-analytics\.com|googletagmanager\.com)$/.test(url.hostname);
      const forbiddenPath = /^\/api\/(?:cloud-auth|premium|membership|entitlement|analytics|trakt|simkl|mdblist)(?:\/|$)/.test(url.pathname) ||
        url.pathname.startsWith('/.netlify/functions/');
      if (forbiddenHost || forbiddenPath) stats.forbiddenServiceRequests += 1;
      if (url.origin !== baseUrl) {
        stats.blockedExternalRequests += 1;
        await route.abort('blockedbyclient');
        return;
      }
      if (url.pathname === '/api/selfhost-config') {
        stats.bootstrapRequests += 1;
        if (scenario === 'bootstrap-blocked') {
          await route.abort('blockedbyclient');
        } else {
          const started = Date.now();
          await new Promise(resolve => setTimeout(resolve, BOOTSTRAP_DELAY_MS));
          stats.delayedBootstrapMs = Date.now() - started;
          await route.continue();
        }
        return;
      }
      // No real TMDB account or server-side media/provider proxy is contacted.
      if (url.pathname.startsWith('/api/tmdb/') || url.pathname === '/api/proxy') {
        stats.mockedMetadataRequests += 1;
        await route.fulfill({
          status: 200, contentType: 'application/json',
          headers: { 'cache-control': 'no-store' }, body: JSON.stringify(EMPTY_METADATA)
        });
        return;
      }
      if (url.pathname.startsWith('/api/') || forbiddenPath) {
        stats.unexpectedApiRequests += 1;
        await route.abort('blockedbyclient');
        return;
      }
      await route.continue();
    });

    const page = await context.newPage();
    page.setDefaultTimeout(20000);
    page.on('pageerror', error => pageErrors.push(error.message));
    const response = await page.goto(`${baseUrl}/?lang=en`, { waitUntil: 'domcontentloaded', timeout: 45000 });
    assert.equal(response?.status(), 200, `${scenario}: production page responds`);
    await expect(page.getByRole('heading', { name: "Who's watching?", exact: true })).toBeVisible();
    await expect(page.getByRole('link', { name: 'Source code & licences', exact: true })).toBeVisible();
    await assertIndependentUi(page, scenario);
    assert.ok(stats.bootstrapRequests > 0, `${scenario}: page requested runtime bootstrap`);

    const profileName = scenario === 'bootstrap-blocked' ? 'Unraid fallback' : 'Unraid smoke';
    await page.getByRole('button', { name: 'Add Profile', exact: true }).click();
    const dialog = page.getByRole('dialog');
    await expect(dialog).toBeVisible();
    await dialog.getByPlaceholder('Profile name', { exact: true }).fill(profileName);
    await dialog.getByRole('button', { name: 'Create', exact: true }).click();
    await expect(dialog).toHaveCount(0);
    const profile = page.locator('.profile-pick').filter({ has: page.getByText(profileName, { exact: true }) });
    await expect(profile).toBeVisible();
    await page.screenshot({ path: path.join(evidenceDir, `${scenario}-profiles.png`) });
    await profile.click();
    await expect(page.locator('.settings-gear')).toBeVisible();
    await page.locator('.settings-gear').click();
    await expect(page.getByRole('heading', { name: 'Local Account', exact: true })).toBeVisible();
    await expect(page.getByText('Profiles and settings are saved in this browser. ARVIO Cloud is not connected.', { exact: true }))
      .toBeVisible();
    await assertIndependentUi(page, scenario);

    assert.equal(await page.getByRole('button', { name: 'Telegram', exact: true }).count(), 0,
      'Disabled integration must not offer a Telegram account action.');
    for (const provider of ['Trakt', 'Simkl']) {
      const panel = page.locator('.settings-panel-card').filter({
        has: page.getByRole('heading', { name: provider, exact: true })
      });
      const button = panel.getByRole('button', { name: 'Start device link', exact: true });
      await expect(button).toBeVisible();
      if (scenario === 'bootstrap-blocked') await expect(button).toBeDisabled();
      else await expect(button).toBeEnabled();
    }

    const runtimeIds = await page.evaluate(() => {
      const value = window.__ARVIO_SELFHOST_CONFIG__;
      return value ? { traktClientId: value.traktClientId, simklClientId: value.simklClientId, simklV2ClientId: value.simklV2ClientId } : null;
    });
    if (scenario === 'bootstrap-blocked') {
      assert.equal(runtimeIds, null, 'Blocked bootstrap does not invent or reuse runtime credentials.');
      await expect(page.getByText('Trakt client id is missing.', { exact: true })).toBeVisible();
      await expect(page.getByText('Simkl client configuration is missing.', { exact: true })).toBeVisible();
    } else {
      assert.deepEqual(runtimeIds, EXPECTED_PUBLIC_IDS, 'UI receives only the expected dummy public tracker IDs.');
      assert.ok(stats.delayedBootstrapMs >= BOOTSTRAP_DELAY_MS - 100, 'Bootstrap was deliberately delayed.');
    }

    // Confirm real UI-created state is browser-local and profile navigation works.
    await expect.poll(async () => page.evaluate(name => {
      const profiles = JSON.parse(localStorage.getItem('arvio.web.profiles') || '[]');
      return profiles.some(profile => profile.name === name);
    }, profileName)).toBe(true);
    await page.screenshot({ path: path.join(evidenceDir, `${scenario}-accounts.png`) });
    await page.locator('.sidebar').getByRole('button', { name: 'Switch profile', exact: true }).click();
    await expect(page.getByRole('heading', { name: "Who's watching?", exact: true })).toBeVisible();
    await expect(page.locator('.profile-pick').filter({ hasText: profileName })).toBeVisible();
    await assertIndependentUi(page, scenario);
    assert.equal(stats.forbiddenServiceRequests, 0, `${scenario}: no Cloud, billing, analytics or tracker requests`);
    assert.equal(stats.unexpectedApiRequests, 0, `${scenario}: no unmocked provider API routes`);
    assert.deepEqual(pageErrors, [], `${scenario}: no unhandled application errors`);
    return stats;
  } finally {
    await context.close();
  }
}

async function checkUnraidBrowser(baseUrl) {
  const base = localBaseUrl(baseUrl);
  const evidenceDir = path.resolve(__dirname, '../artifacts/unraid/browser');
  fs.mkdirSync(evidenceDir, { recursive: true });
  const browser = await chromium.launch({ channel: 'chrome', headless: true });
  try {
    const results = [];
    for (const scenario of ['bootstrap-delayed', 'bootstrap-blocked']) {
      results.push(await exerciseContext(browser, base, scenario, evidenceDir));
    }
    return { passed: true, offlineProviders: true, evidenceDir, results };
  } finally {
    await browser.close();
  }
}

module.exports = { checkUnraidBrowser };

if (require.main === module) {
  const baseUrl = process.argv[2];
  if (!baseUrl) {
    console.error('Usage: node scripts/check-unraid-browser.cjs http://127.0.0.1:PORT');
    process.exitCode = 1;
  } else {
    checkUnraidBrowser(baseUrl).then(result => console.log(JSON.stringify(result, null, 2))).catch(error => {
      console.error(error);
      process.exitCode = 1;
    });
  }
}
