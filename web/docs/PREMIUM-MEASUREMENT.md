# Premium access and measurement

The September 2026 release fixes billing-email requests encoded by Netlify,
requires ownership verification before linking a different billing email,
rechecks access on expiry and when returning from checkout, and offers a manual
access check without asking customers to pay twice. Trial navigation uses the
server's actual expiry, including existing shorter trials. Prices are unchanged.

## Measurement

- Existing website Premium/membership buttons use fixed-destination HTML handoffs.
  Netlify Web Analytics counts these HTTP 200 HTML requests, unlike the previous
  HTTP 302 responses. They add no cookies, databases or analytics function calls.
  The free Android app remains the primary homepage offer; its layout is unchanged.
- Compare `/go/premium/*` and `/go/membership/*` separately by placement. Exclude
  these handoffs from content pageviews. They are not unique people or verified
  purchases; bots and reloads can be included. There is no historical backfill.
- The authenticated admin `premium-funnel-report` endpoint reports account-level
  steps, distinct starts/renewals and trial cohorts, with partial-day/window caveats.
- Billing identities join only after a successful server-side ownership flow in
  the report window. No raw billing email is added to funnel storage. Ambiguous
  ownership is not attributed. Historical mismatched emails remain unmatched.
- Internal first playback requires the video `playing` event. External-player
  launches and download requests/handoffs/failures are separate bounded events.
  A handoff is not proof that an external player opened or a download completed.
- Compare complete seven-day UTC windows and mature trials, not this partial day
  against last week's totals. A short-term increase or decline is not causal proof.

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
