# ARVIO Partner

Status: implementation in progress. Do not sell access until the release gates below pass.

## Verified development state

The implementation is on `codex/partner-management`. A restricted private pilot is
deployed at `https://auth.arvio.tv/partner/`; this is not a public commercial launch.
Four Partner membership tiers are saved as unpublished drafts on the existing
`ko-fi.com/arvio` account. Consumer Premium is unchanged. No second email is needed.
Prices below are draft launch prices, not a published offer.

Local checks completed:

- Backend suite: 236 passing tests, including tenant separation, invitations,
  encrypted credentials, billing routing, expiry, refunds, write conflicts and
  personal Premium preservation and Partner database outage isolation. Most
  database tests use PGlite, not concurrent
  production PostgreSQL connections.
- Edge desktop/mobile dashboard flows: login verification, customers, invitations,
  credential assignment, retained edits after conflicts, templates, publishing,
  customer credential entry and leaving, add-on/IPTV template selection, inherited
  connections, IPTV capability editing and M3U/EPG credential delivery. Screenshots
  in `artifacts/partner-management`. These use isolated fixture accounts, not the
  owner's personal ARVIO account.
- Web TypeScript check passes. Managed configuration, personal-data preservation,
  collection/addon references and playback lease tests pass. The focused IPTV,
  Partner delivery, source setup and provider budget regression run passes 64 tests.
- Full web regression run: 1,400 tests, 1,384 passed, 11 skipped, 5 failed.
  Failures concern collection hover styles, YouTube extraction policy, device-name
  test extraction, guide clock formatting and stylesheet import line endings.
  These must be triaged; the full suite is not green.

Android delivery remains experimental behind `-PpartnerManagement=true`; normal
builds default to disabled. Catalogs, collection feeds, template preferences,
add-ons and home servers now have separate runtime overlays. Personal cloud export
does not serialize these managed resources. Unsupported configurations fail before
acknowledgement. EPG/catch-up restrictions are enforced without rejecting otherwise
supported IPTV packages, including cached-channel replay and guide refresh paths.

On 1 November, live Android instrumentation on `ArvioLauncherAndroidTV` verified:

- The designated QA customer received its assigned Xtream playlist and two TMDB
  lists; both lists loaded real titles. No managed playlist/catalog was persisted
  into personal settings.
- The live manager dashboard showed the emulator and configuration v3 Applied.
- A manager publication changing list order/title and replacing one list reached
  the running client as v4 without restarting. Both replacement lists loaded.
- The final rebuilt emulator test passed against v4, waiting for the successful
  server acknowledgement. Star Wars returned four titles and Lord of the Rings
  three; Watchlist on Home was enabled and trailer autoplay disabled as assigned.
  This repository-level instrumentation finished in 3.584 seconds; it is not a
  full UI startup or playback timing measurement.
- The focused Android JVM run passed 91 tests across Partner delivery/preferences,
  home-server regressions and playlist selection (13 suites, no failures/errors).
  These include fixture-based home-server overlay and revocation checks; they do
  not establish live server playback or full Settings-screen permission coverage.
- The provider account itself now returns one channel named "ACTION NEEDED:
  switch to your new provider logins (play me)". A direct provider API check
  confirmed this. Normal live TV/VOD playback is therefore NOT verified; replacement
  QA credentials were requested. Do not report this as working IPTV playback.

On 2 November, an alternate add-on playback check removed the need for repeated
administrator verification during emulator development:

- Fixed Android rejecting public/shared add-ons when the backend sends
  `credentials: null`. Missing/null credentials now use the administrator's
  manifest URL; explicit per-customer manifest overrides still take precedence.
- The focused backend suite passed 20 tests and the Android Partner JVM suite
  passed 19 tests. Credential-null delivery and manifest overrides are covered.
- `PartnerAddonPlaybackDeviceTest` uses an isolated in-memory delivery response
  and real Archive.org add-on requests, stream resolution and Media3 decoding.
  No administrator credentials, production writes or persistent add-on installation
  are needed. It refuses physical devices and non-QA signed-in accounts.
- On the QA Android TV emulator, Night of the Living Dead rendered at 1920x1080,
  first frame 11,078 ms, then 212 frames over ten seconds, with no playback error.
  Personal installed add-ons were unchanged. Audio was muted.
- This proves alternate add-on resolution and playback through a managed delivery
  fixture, not a new production dashboard publication or sports schedule matching.
  The old Highfly manifest returns a reinstall notice rather than a sports stream;
  it was not counted as successful playback.

Repeat the playback check after building/installing the Partner-enabled debug and
androidTest APKs on the QA emulator:

```powershell
adb -s emulator-5554 shell am instrument -w -r -e class com.arflix.tv.data.repository.PartnerAddonPlaybackDeviceTest -e partnerAddonPlayback true com.arvio.tv.test/androidx.test.runner.AndroidJUnitRunner
```

Further IPTV verification on 2 November did not depend on Flixnest:

- Fixed public M3U delivery with null/missing customer credentials. Xtream still
  requires credentials. Disabled EPG/catch-up is represented in cache signatures,
  guide-request filtering and persisted channel replay capabilities.
- Android JVM regression: 305 tests across 39 suites, no failures or skips.
  Focused backend regression: 20 passing. Focused web IPTV, provider-budget,
  source-setup and Partner playback-access regression: 22 passing.
- `PartnerIptvPlaybackDeviceTest`: four passing emulator tests, 138.361 seconds.
  An isolated HTTP Xtream contract server exercises the actual repository parser,
  guide matching, movie/episode lookup and Media3 playback. Live, movie, episode
  and catch-up streams rendered for at least ten seconds. Movie, episode and
  catch-up streams passed seeks forward to 20 seconds and backward to 5 seconds.
  Synthetic 160x90 video is used here; this is not a commercial provider test.
- Public M3U plus XMLTV matched the current programme and played the fixture
  stream. A separate M3U pointed to DW English's real public HLS broadcast: first
  frame 5,430 ms, 640x360 selected by the player, 102 rendered frames during the
  check and at least ten seconds of continued playback, without playback errors.
  These are test measurements, not startup-time or video-quality guarantees.
- Capability updates removed cached guide/replay access. VOD-only packages
  returned movies and episodes without requesting live channels or guide data.
  Subsequently disabling all content blocked cached VOD and made no provider
  requests. Revoking delivery removed its playlist and blocked stale replay.
- Tests use unique isolated profiles and in-memory managed delivery, never store
  provider credentials in personal settings, mute playback and refuse physical
  devices. They require no administrator verification or production writes.
- The real QA customer's delivery test was rerun with the updated APK: revision 4
  acknowledged, two lists (four Star Wars and three Lord of the Rings titles),
  one managed playlist, personal storage clean. It passed in 5.008 seconds without
  manager re-verification. This checks actual cloud delivery, not Flixnest playback.

```powershell
adb -s emulator-5554 shell am instrument -w -r -e class com.arflix.tv.data.repository.PartnerIptvPlaybackDeviceTest -e partnerIptvPlayback true com.arvio.tv.test/androidx.test.runner.AndroidJUnitRunner
```

This verifies the tested Android IPTV protocol paths, not every provider variation,
the full remote-control UI workflow, or commercial Partner launch readiness.
Partner Android builds must explicitly enable `-PpartnerManagement=true`; normal
builds retain their existing behavior. Pilot builds are not a public product launch.

The QA changes were confined to the labelled QA template/package/customer. The
owner's original template and personal preferences were not changed. Live
collection-feed/add-on/home-server playback, permission/revocation UI coverage,
mobile validation and full release regression coverage remain gates. The APK is
not ready to distribute as the completed Partner product. Do not weaken package
restrictions to make a test pass. See `PartnerLiveDeviceTest` for opt-in,
emulator-only live tests; credentials are supplied at runtime, never in source.
Web delivery is behind `NEXT_PUBLIC_PARTNER_MANAGEMENT=true`; backend access is
behind `PARTNER_ENABLED=true`. Both remain off by default in code. The auth-site
backend is enabled only for the private allowlisted pilot. The normal webapp's
managed delivery has not been deployed. No real-payment test has been performed.

## Sharing an Android pilot

Build the normal release variant with the pilot flag, not the debug APK:

```powershell
./gradlew.bat :app:assembleSideloadRelease -PpartnerManagement=true
```

Use the existing release keystore and omit `includeX86Abis` for the shareable
ARM/ARM64 APK. Verify the signer, non-debuggable manifest and generated Partner
flag before sharing. Install as an update over a matching signed GitHub APK;
do not uninstall or clear app data. The device must sign in as the customer who
accepted the manager's invitation, not merely as the dashboard administrator.
Keep the package published and the customer enabled. This build is for the private
pilot, not a replacement for the public release or permission to sell Partner access.

After integrating main on 2 November, all 238 backend tests, the public-build
packaging check, 36 focused web tests and the web TypeScript check passed. The
full web run had 1,444 passes, 11 skips and 9 failures out of 1,464 tests. Failures
include the earlier five, a calendar timing check and three stale-translation
checks for the playlist-limit wording. Do not report the whole web suite as green.

Run `npm run preview:partner-management` from `web` for a disposable localhost
preview. The command prints its URL. Admin `operator@example.test`, customer
`viewer0@example.test`, password `fixture-only`. The preview's test inbox contains
verification messages; it never sends email or charges anyone. Do not deploy this
test harness. Normal production routes use real ARVIO authentication and emailed MFA.

## Private pilot deployment

The standalone pilot uses the actual dashboard components and ARVIO authentication,
not the disposable local test harness. `scripts/build-partner-pilot.mjs` in the auth
site stages public pages and the bundled dashboard only; never publish the auth
source directory, SQL, scripts, test fixtures or environment files as static assets.
The existing auth and deletion pages are preserved. Partner pages have no-store,
noindex and a restrictive content security policy.

Availability recovery, 2026-10-28: a newer `main` auth deployment omitted the
uncommitted pilot page/assets and both Partner endpoints. Deployment
`6ae1d74d78dd8768e49fb7d7` restores them while retaining all 59 pre-existing
production function bundle hashes, static files and scheduled functions. The
recovery script `scripts/restore-partner-pilot.mjs` rebuilt only the two Partner
handlers and merged routing into the current deployed configuration. The focused
Partner regression run passed 20 tests; packaged dependencies/authentication gates,
public page/assets, deep links, existing auth/deletion pages and unauthenticated
API denial were verified. Edge loads the administrator verification screen again.
No account/workspace data or database access settings were changed in this recovery.

This is still an out-of-band pilot: a normal auth-site deployment from `main` can
omit it again until the pilot assets/routes/functions are included in the normal
deployment pipeline. Do not lock or roll back unrelated production deployments as
a workaround. Do not claim the full Partner product or managed clients are released.
The separate QA account exists, but its workspace/grant provisioning and signed-in
live end-to-end verification remain outstanding.

`scripts/partner-pilot-operator.mjs build` reads the public application key using the
operator's existing Netlify CLI login. `prepare <email> --confirm-production`
applies the additive schema and provisions an existing account's 20-seat, 30-day
complimentary grant. It refuses conflicting existing rollout configuration and
never rotates an existing encryption key or extends an existing grant on retry.
The grant is separate from paid transactions and does not edit consumer Premium.
`status <email>` reports only safe workspace/grant metadata.

`runtime <email> --confirm-production` provisions a dedicated restricted PostgreSQL
role and saves `PARTNER_DATABASE_URL` as a functions-only production secret. The
legacy Lambda-compatible functions require an explicit connection URL. Never use
the migration/owner connection as their runtime connection. The role can read/write
Partner tables, but only read pilot grants; it cannot read account records or create
schema objects. RLS permits this server role only; API checks enforce tenant scope.

Pilot controls: `PARTNER_ENABLED=true`, `PARTNER_PILOT_MODE=true`, an explicit
`PARTNER_PILOT_OWNERS` email allowlist and `PARTNER_APP_URL=https://auth.arvio.tv`.
An empty pilot allowlist denies every administrator. Customers can join only an
allowlisted workspace by accepting their own invitation. Sales are disabled in
pilot mode. No Ko-fi tier should be published during the pilot.

Netlify currently defaults personal access tokens to production read-only access.
If a temporary provisioning window is needed, obtain explicit approval before
enabling full access, then immediately restore read-only after provisioning/tests.
Do not bypass this setting with a temporary public migration endpoint. The operator
script checks migration permissions before writing anything.

Private hosted preview verified: sign-in renders, /partner/join and /partner/account
resolve, existing auth/deletion pages remain available, and SQL/scripts return 404.
The approved production provisioning ran on 27 October 2026. The pilot owner has
a complimentary, expiring 20-seat grant, not a payment or auto-renewing subscription.
Personal Premium, profiles and settings were not changed. The encryption key and
restricted runtime connection are production secrets, not local/public files.
After provisioning, Netlify's personal-token full-access setting was disabled and
an independent connection confirmed `netlifydb_readonly`, without CREATE or INSERT
permission. A runtime transaction confirmed Partner writes and was rolled back.
The pilot deployment includes the restricted runtime and consumer outage isolation.
Real sign-in and email verification succeeded. The owner completed MFA and the real
dashboard loaded. Subsequent approved tests used a separate QA customer and labelled
QA package; the original template and personal preferences remained untouched.
See the verified development state above for the completed delivery checks.
Unauthenticated and invalid-token requests return 401; Partner routes return 200
with no-store; `/sql/partner.sql` returns 404. Preview deployment alone is not a
production launch.

## Product boundary

One organization per administrator, one managing organization per customer account.
Customers retain their own ARVIO login, profiles, watch history and tracking accounts.
Joining an organization requires an emailed invitation and explicit consent. Leaving,
suspension and subscription expiry remove managed access, not personal account data.

Plans: 20 enabled accounts / USD 39; 50 / 79; 100 / 129; 200 / 229 monthly.
All plans have the same features. Pending invitations reserve seats. No automatic
billing upgrades. The partner provides first-line support. Third-party subscriptions,
content, transcoding, bandwidth and custom development are not included.

Use the existing Ko-fi page with separate Partner membership tiers. Route exact tier
names before the consumer Premium handler. A verified payment is not evidence of
control over an ARVIO account: billing linkage requires email verification. Ko-fi
does not send cancellation/refund webhooks; expiry is derived from actual paid
periods, with a three-day grace, plus an audited operator reconciliation route.
Never sell a three-month introductory price as a recurring tier that silently
increases later. Any pilot discount needs a separate, explicit billing agreement.

## Security and delivery

- Server-only PostgreSQL tables, transactionally locked organization writes, optimistic
  revisions and atomic seat limits. No browser database credentials.
- Verified ARVIO bearer identity plus a short-lived emailed administrator challenge.
- Tenant-scoped access on every lookup, including invitations and device delivery.
- AES-256-GCM envelopes for connection configuration, with tenant-bound authenticated
  data and a separately configured encryption key. Credentials are write-only in
  dashboard responses and excluded from templates, copy operations and audit data.
- Configuration revisions and acknowledgements distinguish pending from applied.
- Personal cloud snapshots and managed configuration are separate. Customers cannot
  change the authoritative managed configuration through personal sync.
- No remote silent APK installation. App upgrades use the existing distribution path.
- Direct playback requires URLs/tokens on the device; hiding a settings field is not
  DRM or a promise that credentials cannot be extracted. Device revocation blocks new
  managed delivery, not credentials already issued by an external provider.
- Already-issued managed media relay tickets last at most 24 hours, bounded by the
  billing grace deadline. They cannot be cut to a few minutes without renewing the
  relay URLs during playback. Suspension prevents new tickets; it is not immediate
  revocation of an already-issued playback URL. Consumer ticket duration is unchanged.

## IPTV and template connections

Templates select registered add-ons and connections by tenant-owned IDs; packages
can add more. Publication deduplicates the union and snapshots connection options.
Credentials remain assigned separately per customer and are never copied with a
template. Publishing/rollback retains the selected connection capabilities.

IPTV supports Xtream credentials or a customer-specific M3U playlist URL, with an
optional EPG URL. Controls cover live TV, VOD movies, VOD series, EPG and catch-up.
Web normalization and cache signatures retain these controls. Disabled live TV or
EPG causes no channel/guide requests, and disabled catch-up removes archive badges
and prevents archive lookup even for previously cached channels. Source setup
recognizes VOD-only Xtream packages without advertising them as live TV playlists.

Xtream VOD discovery checks all eligible providers with bounded concurrency and
coalesced requests. Movies and series load independently; disabled catalog types
are not fetched. Credentials are included in private cache keys so a changed
password cannot reuse stale results. This is discovery/delivery verification, not
a claim that live provider playback has been verified. Generic M3U on-demand
metadata browsing and Partner-managed Stalker portals are not implemented.

## Remaining implementation and operations

- Android TV/mobile: separate managed overlays in the catalog, addon, IPTV,
  home-server and preference repositories; no managed credentials in personal cloud
  exports. Restrict every settings/editor entry point and apply revisions only while
  idle. Verify joining, rollback, suspension, logout and personal-data restoration.
- Web: exercise the delivery hook across account changes, offline expiry, active
  playback and provider failures. Allowed preference overrides currently live on
  the device, so cross-device override synchronization is not complete.
- Validate production PostgreSQL contention with separate connections, migration
  permissions and backup/restore before rollout. Never log decrypted delivery payloads.
- The random 32-byte `PARTNER_ENCRYPTION_KEY` is provisioned only in the backend
  secret store. Back up securely; losing it makes assigned connections unreadable. Key
  rotation needs an explicit versioned re-encryption procedure before launch.
- Run an isolated staging pilot and verify a genuine Ko-fi webhook against the
  existing account without granting consumer Premium for Partner payments.
- Reconcile cancellations/refunds with Ko-fi/its payment processor: cancellation
  stops renewal, not the already-paid month; confirmed refunds use the audited
  operator reconciliation route. Document support ownership and escalation.
- Finalize Partner terms and pricing, then publish the tiers only after all gates
  pass. The service does not include or sell third-party media subscriptions.

## Release gates

- [ ] Tenant/authentication/invitation/security regression tests
- [ ] Atomic customer limits, edit conflicts and idempotent billing tests
- [ ] Dashboard flows, keyboard navigation, mobile/desktop visual checks
- [ ] Android TV and mobile managed configuration, restrictions and rollback
- [ ] Web managed configuration and personal-data preservation
- [ ] Billing failure, cancellation/reconciliation and grace-period checks
- [ ] Staging deployment and real end-to-end pilot (without charging customers)
- [ ] Ko-fi tier setup, operational runbook and final activation approval
