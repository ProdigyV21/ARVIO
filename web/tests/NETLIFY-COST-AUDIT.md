# Netlify Cost Audit

Date: 2026-09-08. Workspace: `C:/Users/arvin/ARVIO-WEB-PLAYBACK`.
This audit used source inspection, local tests, and bounded read-only Netlify API requests with existing CLI authentication. No deployment, billing/account changes, credential rotation, or auth-backend edits were performed by this audit.

## Actual Usage and Coverage

The account bandwidth API returned HTTP 200:

| Field | Actual API value |
| --- | --- |
| Used bandwidth | 6,912,131,183 bytes |
| Counter updated | `2026-09-08T14:44:27.659+00:00` |
| Usage period start | `2026-09-07T00:00:00.000-07:00` |
| Usage period end | `2026-10-07T00:00:00.000-07:00` |

This is a partial-period counter for September 7 at 07:00 UTC through October 7 at 07:00 UTC, not a daily total. No billed-credit total or production savings were verified.

The separately fetched domain ranking returned:

| Selected host | Returned bytes |
| --- | ---: |
| `auth.arvio.tv` | 5,719,818,696 |
| `arvio.tv` | 770,075,196 |
| `web.arvio.tv` | 272,760,176 |
| `arvio-web.netlify.app` | 122,828,653 |

The ranking supplied no independent period/update watermark and was not reconciled to the account counter. **Do not describe the auth figure as 5.72 GB/day or verified endpoint-specific usage.** No traffic, savings, or credit extrapolation is made here.

## Actionable Auth Findings

**Whole-document progress saves:** at inspection, signed-in non-live playback could save progress every 15 seconds. `saveProgress()` calls `mutateCloudPayload()`, which invalidates the five-second read cache, downloads the full account snapshot, and uploads the full modified document. The backend stores account/email copies and a snapshot event. This proves transfer amplification, not its share of measured bandwidth.

Ordinary checkpoints now run every 60 seconds, retaining immediate flushes on pause, backgrounding, completion and close. Three regression tests cover cadence, forced flushes and account-change isolation. This reduces scheduled checkpoint frequency by 75%, not total account traffic or billed credits by 75%. Production savings remain unmeasured.

**No supported delta write:** the settings outbox also uses full read-modify-write. `account-sync-push` requires `body.payload`; it has no general progress-patch, atomic operation, stable mutation-ID acknowledgement, or progress-tombstone protocol. `account-sync-delta` returns empty events and a wall-clock cursor. Sparse writes could be rejected or lose unrelated state. This audit left `cloud.ts` and the auth backend unchanged. Any future delta protocol needs reviewed ownership, conflict, tombstone, retry, and durability semantics.

**No auth media relay found:** source, redirects, and deployed function inventory showed authentication/sync/metadata functions, not an arbitrary video/playlist relay. Tracker proxies use fixed TMDB/Trakt/Simkl API hosts. Auth bandwidth remains unattributed by route.

Successful historical log samples:

| Function | Log entries | Distinct reported requests | Observed report span, UTC on September 8 |
| --- | ---: | ---: | --- |
| `account-sync-pull` | 100 | 100 | 13:51:55.879 - 13:53:51.282 |
| `tmdb-proxy` | 100 | 100 | 14:04:18.033 - 14:04:22.458 |
| `app-usage-event` | 100 | 40 | 13:51:58.267 - 14:00:32.613 |
| `tv-auth-status` | 100 | 100 | 13:52:58.214 - 14:37:07.271 |

These preceding-hour queries repeatedly returned 100 entries without an exposed pagination cursor. Treat them as incomplete samples, not hourly totals or per-user hotpoll rates. The usage sample included 60 ordinary log lines. Response bytes and client attribution were not verified. A narrower analytics retry returned HTTP 401 at approximately 15:24:56 UTC: unavailable data, not zero traffic.

The settings retry timer exits when nothing is pending; ordinary reads are single-flight/cached. No idle auth polling loop was established. Snapshot/TMDB bursts warrant route-byte and cache-hit attribution before further backend changes. No paid telemetry was added.

## Production Configuration

- Both media-proxy flags were absent in checked site/shared environment APIs and legacy build settings. Deployed values were not changed.
- The web site's production public resolver was separately verified with HTTP 200: `NEXT_PUBLIC_ARVIO_RESOLVER_URL=https://resolve.arvio.tv/`.
- Live TV logos and home-server artwork use direct provider URLs; no ordinary logo traffic through the metadata proxy was identified.

## Implemented Safeguards

Changed paths under `C:/Users/arvin/ARVIO-WEB-PLAYBACK`:

- `web/lib/server/safeProxy.ts`: hosted media opt-in disabled; redirect media paths, ranges/partial responses, and mislabeled binary media rejected; cancellation releases upstream work.
- `web/app/api/proxy/route.ts`: playlist segments stay off Netlify; full-query catalog caching retained; live HLS excluded from hour-long caching, including misleading `.m3u`/`get.php` names.
- `web/app/api/subtitle/route.ts`: DNS-pinned fetching, request budget, 2 MiB limit, cancellation, uncached errors, and full-query cache variation.
- `web/netlify.toml`: both media-proxy build flags explicitly false.
- `web/tests/netlify-cost.test.cjs`: 30 focused regressions.
- `web/tests/NETLIFY-COST-AUDIT.md`: this report.

Images retain original bytes/formats after Sharp metadata verification, with 4 MiB and 16,777,216-pixel limits. No image re-encoding occurs. SVG gets the same byte bound, sandboxed CSP, and `nosniff`; missing decoder support fails closed. Verification does not strip trailing data: the bounded-image exception is not content sanitization.

The existing 128 MiB text-catalog allowance remains. Known `.m3u8` fetches and subtitles use 2 MiB budgets; misnamed HLS is limited before being returned but can be identified after reading under the broader budget. Per-instance request limits are not an account-wide spending ceiling. Metadata, manifests, assets, rejected requests, and CDN traffic can still incur costs.

## Final Verification

- Final shared suite: **407 passed**, zero failures/skips/TODOs, including **30 cost tests** and **three checkpoint tests**.
- TypeScript: `node node_modules/typescript/bin/tsc --noEmit --incremental false` passed.
- Production build and `npm audit --omit=dev --audit-level=high` passed; zero reported dependency vulnerabilities.
- Diff whitespace check passed. Billing inspection itself did not use a browser/TV playback session.
- Web changes deployed from commit `901834b32d2fdce9ee59c2cbf8f3b6c0199ac798`. GitHub Actions run `34245573964` passed tests, build, production deployment and live build-stamp verification.

No post-deployment savings are claimed. The auth backend was not changed or deployed by this work; replacing whole-document progress writes with an atomic operation remains a separate backend improvement.
