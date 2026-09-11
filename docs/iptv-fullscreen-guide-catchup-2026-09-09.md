# Fullscreen guide and catch-up investigation

## Confirmed causes

- The grid could show SQLite-indexed EPG while fullscreen read only the small
  in-memory snapshot. Quick-zap had the same snapshot-only lookup.
- Opening the guide through quick-zap did not request the selected channel's
  full archive. The other entry point used a six-programme count as a proxy for
  history coverage, although six short programmes do not cover three days.
- A short schedule update replaced the ViewModel's whole `recent` list, dropping
  older programmes already loaded. The repository's duplicate handling could
  keep stale availability metadata ahead of fresh provider data.
- The guide grid inferred two days for legacy Xtream channels while the archive
  loader used three days.
- SQLite did not persist `IptvProgram.catchupAvailable`. The fullscreen guide and
  URL resolver also ignored an explicit provider `has_archive=0` result.
- With no archive route, the resolver could silently use the live stream URL.

## Changes

- Fullscreen resolves both indexed and in-memory schedules for the exact selected
  channel ID; quick-zap uses the same effective guide as the grid.
- Both guide entry points invoke the existing bounded, selected-channel archive
  loader. It reads the complete local archive off the main thread, then requests
  provider history only when coverage is insufficient and the request budget allows.
- Shared schedule merging retains older entries, applies fresh metadata and keeps
  history bounded to 1,000 programmes. Unknown XMLTV metadata does not overwrite a
  known provider archive flag in the in-memory merge.
- Shared replay-window logic uses the same three-day Xtream fallback throughout.
  Explicitly unavailable programmes remain visible but are not replay actions.
- Database version 7 adds nullable `catchup_available` without deleting version
  2-6 schedules. Existing rows remain unknown rather than being marked available.
- The resolver rejects known-unavailable archives without network probes, and no
  longer substitutes live playback when there is no archive route.

## Provider evidence

The configured Flixnest Xtream proxy was checked with a small number of authorized
metadata requests, not a playlist-wide stream probe:

- NPO 1 stream ID `570551683`: `tv_archive=0`, `tv_archive_duration=0`.
- Its simple guide response contained 90 programmes, all with `has_archive=0`.
- Previously, the three attempted catch-up URL candidates returned HTTP 200 with
  `text/html`, not a playable video container.

This establishes that the tested route does not advertise archive playback. It
does not prove that the original upstream provider lacks catch-up or that other
configured providers cannot supply it. Three days of replay cannot be certified
from this route, and HTML responses must not be treated as successful playback.

## Automated verification

Sideload debug unit suite: 905 tests, 904 passed, 1 skipped, 0 failures.

Regressions cover indexed-only fullscreen data, selected-channel isolation,
clock rebasing, archive retention through a six-programme refresh, provider
availability precedence, three-day bounds, bounded memory, no-network rejection,
non-destructive migrations and actual SQLite close/reopen/correction of nullable
archive flags.

Additional regressions cover null channel selection with concurrent caches and
stale snapshot programmes not hiding newer live programmes from the index.

## Physical verification

TCL Smart TV Pro, release 1.9.996 (312), installed in place with the existing
release certificate. No uninstall, profile deletion or guide-cache clearing.

- An initial candidate exposed a null-key lookup against a ConcurrentHashMap
  during TV-page entry. The remember keys are now null-guarded; subsequent cold
  launches and TV-page entry were verified without that crash.
- Final cold activity launch: 877 ms reported by Android (not total home-data
  loading time). All three existing profiles remained present.
- Fullscreen guide: ESPN displayed 38 aired, 1 live and 42 upcoming programmes,
  replacing the previously observed zero/zero/zero counts.
- Quick-zap displayed programme titles and opened NPO 1's guide for its selected
  ID while another channel remained the playback selection.
- NPO 1: 70 aired and 1 live, plus 48 future programmes from the initial cached
  window, expanding to 66 after refresh. The first screenshot was complete
  2.32 seconds after the guide input command, including a deliberate one-second
  wait and screenshot transfer. This is an observed upper bound, not a frame-level
  render latency measurement.
- Selected-channel refresh logs: ESPN 337 ms; NPO 1 1,802 ms. A visible 18-channel
  indexed guide read logged 104 ms. These are individual samples, not benchmarks.
- Scrolling to the beginning showed yesterday (8 September) at 15:00 as NPO 1's
  oldest displayed entry. Approximately 25 hours were available, not 72 hours.
- Selecting the recent explicitly unavailable programme left the guide open.
  No catch-up playback start appeared in the final test log. No fatal exception
  or index read error appeared in the captured final run.
- During the preceding candidate test, ESPN live returned HTTP 502 and NPO 1
  live returned a container parsing error. Archive playback, pause and seeking
  are therefore NOT certified. No repeated archive URL probing was performed.

The final TV was returned to ARVIO Home with playback stopped.

Final APK SHA-256:
`5A3D0F935DA7017E10306C5F4EA9659F79E50D7256819208EB70A6EE55854B1B`

Private device evidence: `C:/Users/arvin/ARVIO-catchup-tv-test-2026-09-09`.
Shareable APK: `C:/Users/arvin/Downloads/ARVIO-1.9.996-guide-catchup-fix.apk`.
