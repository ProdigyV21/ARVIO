const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const read = (file) => fs.readFileSync(path.resolve(__dirname, '..', file), 'utf8');

test('landing page and offer share one two-plan card with the refund line', () => {
  const plans = read('components/shell/PremiumPlans.tsx');
  assert.match(plans, /\$20/);
  assert.match(plans, /\$2\.99/);
  assert.match(plans, /save 44%/);
  assert.match(plans, /within 7 days for a full refund/);
  assert.match(read('components/shell/WebFeaturePreview.tsx'), /<PremiumPlans compact \/>/);
  const offer = read('components/shell/PremiumOffer.tsx');
  assert.match(offer, /<PremiumPlans action=/);
  // "Use this email at checkout" appears under both plans.
  assert.equal((offer.match(/showEmailHint>/g) ?? []).length, 2);
});

test('the play-blocking offer has no exit survey; lapsed members get it as a notice', () => {
  const offer = read('components/shell/PremiumOffer.tsx');
  const dialog = offer.slice(offer.indexOf('export function PremiumOffer('));
  assert.doesNotMatch(dialog, /<ExitSurvey/);
  assert.match(read('components/shell/MemberNotices.tsx'), /<ExitSurvey state=\{state\} account=\{account\} notice \/>/);
});

test('TV-class browsers get a pay-on-phone QR; pop-ups start on their main button and close on Back', () => {
  const offer = read('components/shell/PremiumOffer.tsx');
  assert.match(offer, /isTvClassBrowser\(\)/);
  assert.match(offer, /<PayOnPhone/);
  assert.match(offer, /event\.key === "Escape"/);
  assert.match(offer, /\.premium-offer-plan \.paywall-primary/);
  const checkout = read('components/shell/MembershipCheckout.tsx');
  assert.match(checkout, /querySelector<HTMLElement>\("\.paywall-primary"\)\?\.focus/);
  assert.match(checkout, /Activation usually takes under a minute\./);
  assert.match(checkout, /DISCORD_URL/);
  const plans = read('components/shell/PremiumPlans.tsx');
  assert.match(plans, /navigator\.maxTouchPoints === 0/, 'phones and tablets are not TV-class');
  assert.match(plans, /https:\/\/arvio\.tv\//);
  assert.match(read('components/profile/PinDialog.tsx'), /input\.current\?\.focus\(\)/);
  assert.match(read('components/livetv/SportsGuidePane.tsx'), /onKeyDown=\{\(event\) => \{ if \(event\.key === "Escape"\)/);
});

test('sign-in hands over without flashing a Back button, and only says "required" when it is', () => {
  const login = read('components/login/LoginScreen.tsx');
  assert.match(login, /if \(redirecting\) return \(/);
  assert.match(login, /\{cloudLoginRequired && <p className="login-tag">/);
});
