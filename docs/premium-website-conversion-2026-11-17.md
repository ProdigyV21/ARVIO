# Premium website conversion improvements — 17 November 2026

## Evidence and scope

Completed UTC weeks 2–8 versus 9–15 November: marketing website range-unique IP addresses increased 7,031 → 7,277, while public Premium pageviews decreased 581 → 500, authenticated Web opens 288 → 250 and Ko-fi-link opening accounts 33 → 23. First-monthly/annual purchases changed 17 → 15. These are different observed populations, not one sequential conversion funnel. Receipts increased through annual purchases; a price reduction is not justified by this evidence alone.

Public pricing cards previously used the same generic “Open ARVIO Web” action and discarded which plan the visitor chose. Signing in could therefore lead to browsing and profile setup rather than the purchase decision already made. Public copy also used “pay when you press Play,” which could be read as per-play billing.

This change preserves the free Android/TV app, free self-hosting, existing prices, annual-pass duration, regional eligibility, refund promise and no-trial policy. It adds no advertising, countdowns, fabricated popularity claims or automatic purchases.

## Primary research applied

- [Nielsen Norman Group: generic “Get Started” actions](https://www.nngroup.com/articles/get-started/) reports confusion when an ambiguous action sends visitors into onboarding before they understand an offer. Applied here: distinguish seeing plans, previewing the product, selecting a specific plan and continuing to Ko-fi.
- [Baymard: displaying price discounts](https://baymard.com/research-articles/product-page-price-discounts) documents uncertainty from unclear or inconsistent discount presentation. Applied here: show the actual total, recurring status, and comparison basis. Standard annual saving is 44% versus twelve $2.99 payments ($35.88); the regional $10 pass is 50% below $20 and 72% below those twelve monthly payments.
- [Baymard: account creation friction](https://baymard.com/research-articles/make-guest-checkout-prominent) supports avoiding unnecessary identity steps. Its findings concern retail checkout and do not justify removing ARVIO's account-to-entitlement pairing. Applied here: retain plan selection through the existing sign-in instead of asking for it again; do not require a new Ko-fi account.
- [Google: accessible tap targets](https://web.dev/articles/accessible-tap-targets) recommends about 48px targets with spacing. Applied here: visible plan actions at least 48px high, narrow-screen stacking, native links, keyboard focus and RTL checks.

These findings support usability decisions, not a guaranteed conversion uplift or a generic SaaS benchmark for ARVIO's mixed free/paid audience.

## Resulting journey

1. Website explains hosted browser access, supported devices, source requirements and what Premium includes.
2. The visitor sees both prices and chooses monthly or 12 months. Preview remains a separate action and does not unlock playback.
3. The chosen plan survives ARVIO sign-in. Only two bounded plan identifiers are accepted; no amount, payment destination or entitlement comes from the query.
4. A confirmation shows the actual plan, current country-based total, account email and Ko-fi handoff. Existing entitled users continue to their access rather than being prompted to pay again; the existing account-settings upgrade flow remains available.
5. Native checkout and the existing payment-recovery flow remain responsible for pending, error and server-confirmed active states. A click or return never proves payment.

## Pricing and attribution integrity

Marketing and Web use the same eligible-country set. Marketing no longer guesses a regional discount from browser language or timezone. Unknown country retains the standard offer; the Web confirmation checks the current offer before providing the payment action. Localized homepages and Premium routes share the same public price renderer.

Website monthly/yearly checkout placements have explicit bounded reporting labels. Acquisition metadata continues through first-party sign-in using sanitized fields; it is not attached to Ko-fi payment URLs. No email address or arbitrary redirect destination is carried in the new plan-intent query.

## Evaluation

Record publication time and compare complete weeks after deployment. Primary measures are accounts reaching the website-specific checkout actions, server-confirmed first-monthly/annual payments, activation success and receipts after refunds. Separate renewals and annual upgrades from genuinely new customers. Compare traffic source/device mix and Premium interest alongside purchases; never treat all site visitor IPs as new eligible Premium prospects. Current volumes are too small to promise statistical significance after a few days.

Verification evidence and live deployment identifiers are retained in the local task artifacts. No real purchase is required to test selection, sign-in persistence, pricing, external handoff, pending recovery or already-paid behavior.
