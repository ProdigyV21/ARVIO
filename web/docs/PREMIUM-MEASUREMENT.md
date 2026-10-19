# Premium access and measurement

The September 2026 release fixes billing-email requests encoded by Netlify,
requires ownership verification before linking a different billing email,
rechecks access on expiry and when returning from checkout, and offers a manual
access check without asking customers to pay twice. Trial navigation uses the
server's actual expiry, including existing shorter trials. Prices are unchanged.

## Measurement

- Website Premium navigation uses a random in-memory navigation ID. The public
  `premium-funnel-visit` endpoint counts `premium_page_view`, `membership_clicked`
  and `web_clicked` separately. A normal homepage view is not a Premium lead.
  The ID crosses only ARVIO navigation, never the Ko-fi link. It is not a cookie,
  fingerprint or persistent visitor identity; reloading can produce a new ID.
- The hosted webapp takes `arvio_journey` from the query, removes it from the
  address bar, and associates it with optional authenticated funnel diagnostics.
  Its attribution is session-only, expires within 24 hours and resets on account
  changes. DNT and GPC suppress browser analytics on both sites. Storage blockers
  cannot prevent navigation, login, payment or playback. Self-hosted apps send no
  Premium analytics.
- The anonymous endpoint allows only the production ARVIO website origins. It
  accepts a UUIDv4, fixed event names, safe campaign tags and fixed page values;
  it rejects large payloads and stores keyed hashes rather than the raw ID.
  Atomic storage limits accept at most three events per minute/network and 256
  globally per hour. Limited probing can undercount before that ceiling. Budget
  keys use a daily HMAC of the network address; raw IPs are never stored there.
  Anonymous records expire after 30 calendar days, budget records after two.
  Existing authenticated diagnostics retain their 90-day cleanup.
- Legacy `/go/premium/*` and `/go/membership/*` handoffs remain valid. Netlify
  counts those HTML requests separately; exclude them from content pageviews.
  The new journey report does not backfill them or turn pageviews into people.
- The authenticated admin `premium-funnel-report` endpoint reports account-level
  steps, distinct starts/renewals and trial cohorts, with partial-day/window caveats.
- Billing identities join only after a successful server-side ownership flow in
  retained history before the report end. No raw billing email is added to funnel
  storage. Ambiguous ownership or a shared navigation ID is not attributed.
- `checkout_opened` is the legacy name for an authenticated membership-page link
  click. It is **not** a confirmed Ko-fi checkout, attempted payment or abandoned
  checkout. Only the existing verified Ko-fi webhook records new memberships and
  renewals. The server separately records `paid_access_observed` when a signed-in
  account actually retrieves an active paid entitlement; the browser cannot claim
  this event. Access observation is not proof of successful media playback.
- The October 19 direct flow opens the existing Ko-fi membership URL in one
  native link activation, without an entitlement preflight or preparation step.
  Email guidance appears inline on the paywall/account screen; optional recovery
  is available after handoff or through "Already paid? Check access". Its
  `flow_version=direct_v2` marks diagnostics, not a randomized experiment.
- `checkoutDiagnostics` counts unique authenticated accounts at visible CTAs,
  membership-link handoffs, returns, recovery opens/closes and access checks.
  Hidden mobile/desktop duplicates are not CTA impressions. Entry and version
  groups can overlap across days. These overlapping events are not click counts
  or proof of checkout abandonment. New visibility/recovery measurements have
  no historical baseline before October 19.
- Small authenticated telemetry POSTs use `keepalive` and never block payment
  navigation. Privacy settings, blockers, closing the browser and network errors
  can still prevent measurement. Nothing identifying is added to Ko-fi URLs.
- Status checks retain the first timestamp for each of pending/active/error per
  account/UTC day, with bounded conditional writes. Browser-reported activation
  is distinct from server-confirmed payment and observed paid entitlement.
  Return diagnostics require a recorded click before return before status check;
  a journey connected after payment cannot retroactively claim that payment.
- `journeys.bySource` reports navigation IDs, matched trials, new memberships and
  access observations. Each account conversion belongs to at most the earliest
  matched navigation in this window. Direct Ko-fi sales, different unverified
  billing emails, blocked tracking and another browser/device can remain unmatched.
  `confirmedPaymentsNotMatchedToJourney` makes that gap explicit. Never divide
  unrelated traffic and payment totals and call that a tracked conversion rate.
- Internal first playback requires the video `playing` event. External-player
  launches and download requests/handoffs/failures are separate bounded events.
  A handoff is not proof that an external player opened or a download completed.
- `playbackDiagnostics` groups the first failure recorded per account/UTC day by
  a fixed failure category, transport and startup/playback phase. Historical
  events without this metadata appear as `unclassified`. An account can have
  both successful and failed playback; these are not attempt counts or a failure
  percentage. Media URLs, video titles and raw error messages are not collected.
- Compare complete seven-day UTC windows and mature trials, not this partial day
  against last week's totals. A short-term increase or decline is not causal proof.

## Comparing the release

The private report accepts `?days=7&end_date=YYYY-MM-DD`; `end_date` is an inclusive
UTC date and must be today or earlier. Use yesterday to exclude a partial day.
The response includes `periodStart`, `periodEnd`, `includesPartialToday`, matched
seven/fourteen-day trial cohorts, `paymentActivation`, `journeys` and diagnostics.
Keep the existing `x-admin-secret` authentication in a header, never in a URL or
public page. Reports contain aggregates, not per-account IDs.

Compare the first seven complete days after deployment against the preceding
seven days. Compare traffic, trial starts and membership-page clicks immediately;
evaluate conversion only once those trial cohorts have seven complete days of
  observation. New attribution and categorized errors begin at deployment, so
historical zeroes mean unmeasured data. Check confirmed new memberships separately
from renewals and payment-to-access gaps before drawing conclusions about sales.

The report lists event keys for cohort totals and reads only journey-link,
trial/payment/access/click and failure records, with 32 concurrent reads. An
account's first received journey link per UTC day is indexed under its existing
account deletion/retention prefix; repeated IDs cannot create unlimited writes.
Later same-day journeys can remain unmatched. Anonymous site metrics are not
billable-accuracy analytics and should always be checked against Ko-fi receipts.

## Operational checks

Deploy backend first, then the webapp and marketing handoffs. Verify production
paywall configuration, billing URL, auth configuration and mail configuration.
Keep the private traffic report and local fixture screenshots out of publish roots.
The controlled UI fixture remains unavailable in production.

Production verification also found a stale team app key and an invalid public
alias on the web site. Both web-specific app-key variables now use the auth
backend's existing public application key; no account signing secrets are exposed
or rotated. TMDB errors are no longer CDN-cached as successful catalogue responses.
Netlify draft functions use preview environment values even when their build used
production context. Check the production function configuration after publishing;
do not promote a draft with missing mail/auth runtime settings blindly.

No purchases, customer trials, payout settings or campaign emails are changed by
the deployment tests. Adding Stripe/card payments requires the owner's payment
provider connection and a separate end-to-end transaction. Ko-fi only sends payment
webhooks, not cancellation notifications; no real-time cancellation claim is made.
