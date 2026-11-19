const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const { load } = require('./load.cjs');
const { sourceOf, storeSource } = require('./sources.cjs');

const read = (file) => sourceOf(file);

test('without a registered gate (self-hosted, signed out) everything may play', () => {
  const gate = load('lib/premiumGate.ts');
  assert.equal(gate.premiumAllows('play'), true);
  const asked = [];
  gate.setPremiumGate((intent, resume) => { asked.push([intent, typeof resume]); return false; });
  assert.equal(gate.premiumAllows('live', () => {}), false);
  assert.deepEqual(asked, [['live', 'function']]);
  gate.setPremiumGate(null);
  assert.equal(gate.premiumAllows('download'), true);
});

test('the hosted app renders for signed-in accounts and asks for Premium only at the moment of playing', () => {
  const paywall = read('components/shell/Paywall.tsx');
  const gateBody = paywall.slice(paywall.indexOf('export function EntitlementGate'), paywall.indexOf('export function PaywallScreen'));
  assert.ok(!gateBody.includes('<PaywallScreen'), 'the gate no longer replaces the app with the paywall');
  assert.match(gateBody, /\{children\}/);
  assert.match(gateBody, /setPremiumGate\(/);
  const store = read('lib/store.tsx');
  assert.match(store, /premiumAllows\("play", headedExternal \? undefined : \(\) => playStream\(stream, options\)\)/);
  assert.match(store, /premiumAllows\("live", \(\) => \{ void playChannel\(channel\); \}\)/);
  assert.match(store, /premiumAllows\("live", \(\) => playCatchup\(channel, program\)\)/);
  // The source picker (play/external/copy/download) lives beside the details view.
  const details = read('components/details/DetailsDrawer.tsx') + read('components/details/SourcePicker.tsx');
  for (const intent of ['play', 'external', 'copy', 'download']) assert.ok(details.includes(`premiumAllows("${intent}"`), `details screen gates ${intent}`);
  // The VLC action resumes itself after paying from the offer.
  assert.ok(read('components/livetv/LiveTvScreen.tsx').includes('premiumAllows("live", () => openExternally(channel, player))'));
});

test('grace keeps access until the grace end, then it ends without a reload', () => {
  const policy = load('lib/entitlementPolicy.ts');
  const now = Date.parse('2026-11-01T12:00:00Z');
  const grace = { entitled: true, reason: 'grace', source: 'kofi', expiresAt: new Date(now - 86400000).toISOString(), graceEndsAt: new Date(now + 2 * 86400000).toISOString(), trialAvailable: false };
  assert.equal(policy.currentEntitlement(grace, now).entitled, true);
  assert.equal(policy.accessEndsAt(grace), grace.graceEndsAt);
  assert.equal(policy.entitlementCheckDelay(grace, now), 15 * 60000);
  const after = policy.currentEntitlement(grace, now + 3 * 86400000);
  assert.equal(after.entitled, false);
  assert.equal(after.reason, 'expired');
});

test('invite links are remembered until sign-in and claimed once', async () => {
  const values = new Map();
  const localStorage = { getItem: (k) => values.get(k) ?? null, setItem: (k, v) => values.set(k, String(v)), removeItem: (k) => values.delete(k) };
  const claims = [];
  const referral = load('lib/referral.ts', { './entitlement': { claimReferralCode: async (_auth, code) => { claims.push(code); return { ok: true }; } } }, { localStorage });
  let replaced = null;
  const history = { state: null, replaceState: (_s, _t, url) => { replaced = url; } };
  referral.captureReferralCode({ href: 'https://web.arvio.tv/?ref=abcd2345&utm_source=x' }, history);
  assert.equal(referral.pendingReferralCode(), 'ABCD2345');
  assert.equal(replaced, '/?utm_source=x');
  referral.captureReferralCode({ href: 'https://web.arvio.tv/?ref=<script>' }, history);
  assert.equal(referral.pendingReferralCode(), 'ABCD2345', 'junk codes are ignored');
  assert.equal(await referral.claimPendingReferral({ session: null }, false), 'none');
  assert.equal(await referral.claimPendingReferral({ session: { userId: 'a' } }, false), 'claimed');
  assert.deepEqual(claims, ['ABCD2345']);
  assert.equal(referral.pendingReferralCode(), null);
  values.set('arvio.referral.pending', 'ZZZZ2345');
  assert.equal(await referral.claimPendingReferral({ session: { userId: 'a' } }, true), 'skipped', 'members cannot be invited');
  assert.deepEqual(claims, ['ABCD2345']);
});

test('the media relay URL carries the Premium stream ticket when there is one', async () => {
  const ticketModule = load('lib/streamTicket.ts', { './entitlement': { fetchStreamTicket: async () => ({ ticket: 'v1.9999999999.tag.sig', expiresAt: new Date(Date.now() + 12 * 3600000).toISOString() }) } });
  const resolver = load('lib/resolver.ts', { './config': { config: { resolverUrl: 'https://resolver.example' } }, './streamTicket': ticketModule });
  assert.equal(new URL(resolver.resolverMediaUrl('https://cdn.example/a.m3u8')).searchParams.get('t'), null);
  await ticketModule.refreshStreamTicket({ session: { userId: 'u1' } });
  assert.equal(new URL(resolver.resolverMediaUrl('https://cdn.example/a.m3u8')).searchParams.get('t'), 'v1.9999999999.tag.sig');
  assert.equal(new URL(resolver.resolverDownloadUrl('https://cdn.example/a.mkv', 'A')).searchParams.get('t'), 'v1.9999999999.tag.sig');
  ticketModule.clearStreamTicket();
  assert.equal(new URL(resolver.resolverMediaUrl('https://cdn.example/a.m3u8')).searchParams.get('t'), null);
});

test('the offer names the device it is opened on', () => {
  const source = read('components/shell/PremiumOffer.tsx');
  const start = source.indexOf('export function deviceName');
  const fn = source.slice(start, source.indexOf('\n}\n', start) + 2).replace('export ', '').replace(/: string/g, '').replace(/ = typeof navigator === "undefined" \? "" : navigator.userAgent/, '').replace(/ = typeof navigator === "undefined" \? 0 : navigator.maxTouchPoints/, '');
  const deviceName = new Function(`${fn}; return deviceName;`)();
  assert.equal(deviceName('Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X)', 5), 'iPhone');
  assert.equal(deviceName('Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)', 5), 'iPad');
  assert.equal(deviceName('Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)', 0), 'Mac');
  assert.equal(deviceName('Mozilla/5.0 (Windows NT 10.0; Win64; x64)', 0), 'computer');
  assert.equal(deviceName('Mozilla/5.0 (SMART-TV; Linux; Tizen 6.5) AppleWebKit/537.36', 0), 'TV');
});

test("what's new shows the latest entry once", () => {
  const whatsNew = load('lib/whatsNew.ts');
  assert.ok(whatsNew.WHATS_NEW.length > 0);
  assert.equal(new Set(whatsNew.WHATS_NEW.map((e) => e.id)).size, whatsNew.WHATS_NEW.length);
  assert.equal(whatsNew.unseenWhatsNew(null).id, whatsNew.WHATS_NEW[0].id);
  assert.equal(whatsNew.unseenWhatsNew(whatsNew.WHATS_NEW[0].id), null);
});

test('a hosted build denies playback until the gate is registered', () => {
  const hosted = load('lib/premiumGate.ts', { './config': { config: { paywallEnabled: true } } });
  assert.equal(hosted.premiumAllows('play'), false);
  const selfHosted = load('lib/premiumGate.ts', { './config': { config: { paywallEnabled: false } } });
  assert.equal(selfHosted.premiumAllows('play'), true);
});

test('review fixes stay in place: error card, upgrades, external players, tickets on worker playlists', () => {
  const offer = read('components/shell/PremiumOffer.tsx');
  assert.match(offer, /status === "error" \? <div role="alert">/, 'a failed check never shows a paying member the buy plans');
  const account = read('components/shell/PremiumAccount.tsx');
  assert.match(account, /activeWhen=\{paid \? \(next\) => next\.plan === "yearly" : undefined\}/, 'switching to the pass waits for the pass');
  const checkout = read('components/shell/MembershipCheckout.tsx');
  assert.match(checkout, /activeWhen: state => done\.current\?\.\(state\) \?\? true/, 'recovery checks for the annual upgrade before stopping');
  const store = read('lib/store.tsx');
  assert.match(store, /premiumAllows\("play", headedExternal \? undefined : \(\) => playStream\(stream, options\)\)/, 'VLC/Infuse is not opened outside the tap');
  const proxy = read('app/api/proxy/route.ts');
  assert.match(proxy, /if \(ticket\) proxied\.searchParams\.set\("t", ticket\)/);
  assert.match(proxy, /if \(ticket\) params\.set\("t", ticket\)/);
  const gate = read('components/shell/Paywall.tsx');
  assert.match(gate, /useEffect\(\(\) => \{ if \(config\.paywallEnabled\) captureReferralCode\(\); \}, \[\]\)/, 'invite links work when already signed in');
  assert.match(gate, /setInterval\(\(\) => \{ void refreshStreamTicket\(authClient\); \}/, 'tickets are kept fresh');
  for (const [file, call] of [['components/livetv/SportsGuidePane.tsx', 'premiumAllows("external")'], ['components/settings/DownloadsPanel.tsx', 'premiumAllows("download")']]) {
    assert.ok(read(file).includes(call), `${file} is gated`);
  }
});
