# Premium activation and payment recovery

Scope: hosted webapp only. No Android paywall, price change, new email campaign,
advertising pixels or paid advertising. The marketing homepage is unchanged.

## Changes

- Account settings expose live membership state, the signed-in email, local-time
  access deadline, payment recheck and verified different-email linking during a
  trial. The subscription action disappears when paid access is confirmed.
- The paywall and account settings use the same ownership-verification form.
  Knowing another person's billing address alone cannot grant access.
- Trial email jobs recheck active paid/linked memberships and suppress obsolete
  trial reminders. Storage failures defer sending, rather than falsely telling
  someone their access expired. There are still only three scheduled messages;
  links back to the webapp identify the existing message type, not the recipient.
- Hosted web usage records account-event-days for opening the app, configured or
  missing sources, requested/started/failed browser playback. Only state categories
  are added, never titles, source URLs or provider credentials. Configured does
  not prove the provider works. External-player handoffs remain separate from
  confirmed browser playback.
- New client diagnostics coalesce per account/event/UTC day, including when
  browser storage is unavailable. Repeated events do not rewrite the same server
  record. The new diagnostics are disabled when the hosted paywall is disabled.
- The protected report includes matched 7/14-day observed cohorts and trial
  activation milestones. Immature trials are excluded from those denominators.

## Local verification

- `cd web && npm test`
- `cd netlify-auth-site && npm test`
- `cd web && npm run build`
- `cd web && node tests/premium-ui-server.cjs`: test-only production components
  with synthetic authentication/HTTP transport on 127.0.0.1:3098. No real account,
  email or payment is created. `/` is an active trial, `/?case=paywall` is an unused
  trial, `/?case=error` is an unavailable membership backend.
- Browser checks at 390x844 and 1280x900: billing form fits, invalid code rejected,
  valid code updates membership and removes the subscribe button without reload,
  starting a trial reaches the app, unavailable access has retry/reconnect rather
  than a request to pay again.
- Backend tests cover same/different billing addresses, wrong/expired/replayed
  codes, ownership conflicts, encoded bodies, paid-email suppression, retry on
  storage failure, cohort boundaries and bounded diagnostic writes.

## Measurement limitations and next decision

The report is not a payment ledger. Reconcile subscription starts against Ko-fi
payments before calling them revenue or net subscriber growth. Report days use
UTC and day-level event ordering, not exact elapsed hours. Older unmatched billing
addresses and activity outside the report window remain limitations. Daily usage
diagnostics were not present before this release: missing historic events do not
mean users could not play. A browser failure can be followed by a successful retry.

Review complete 7/14-day cohorts after deployment, separating source setup,
browser playback and external-player handoffs. Do not scale paid traffic based
on trial counts alone. Payment-provider checkout approval, actual charging and
native Safari/PayPal flows require separate live checks; local fixtures do not
claim to verify those external systems. No payment or billing configuration was
changed as part of this work.
