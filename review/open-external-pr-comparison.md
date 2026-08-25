# ARVIO external open PR comparison

Audit target: all 25 open pull requests in `ProdigyV21/ARVIO` not authored by `Saelon600`.

Compared against:
- Submitted PRs #599–#604.
- Local cumulative/deferred source commits `666d1d95`, `2a5ecd63`, `52c70004`, `9f393fe2`, `540ee45a`, `533acfd5`, `2994d895`, and `3866e86a`.
- Current upstream `origin/main` at `9ba76ab2`.

## Executive findings

- None of the 25 external PRs semantically duplicates or supersedes our six submitted PRs.
- All six of our PRs merge cleanly into current upstream main.
- Only external #598 and #586 merge cleanly into current upstream main in a local merge-tree simulation.
- External #549, #548, #543, #518, and #504 share current history but conflict with current main and need rebasing.
- External #417 and every lower-numbered open PR in this audit have no merge base with current main after the upstream history rewrite; they need recreation/cherry-picking onto current main before they can be meaningfully integrated.
- If #598 lands first, it creates real merge conflicts with our #599 in `AppBottomBar.kt` and our #604 in `LiveTvScreen.kt`. Its overlaps with #600, #602, and #603 merge cleanly.

## Per-PR comparison

| PR | Relationship to our work | Risk | Required action |
|---|---|---:|---|
| #598 — Progressive mobile Home UX | Conflict-risk | High | Unique feature, not a duplicate. If it merges first, rebase #599 and combine its navigation-bar insets with our landscape compaction; rebase #604 and preserve its system-bar/findActivity cleanup alongside our EPG actions. Re-extract deferred `540ee45a` on top because both change the mobile hero/Home UI. |
| #586 — APK-size optimizations | File-only overlap | Low | Only `app/build.gradle.kts` overlaps our source commits, and upstream PRs excluded our fork version bumps. It merges cleanly with all six submitted PRs. |
| #549 — GitHub raw fallback/Pastebin timeout | None | None | No overlap with our features. It already conflicts with current main and needs author rebase. |
| #548 — Client-side auth validation | File-only overlap | Low | Shares `SettingsScreen.kt` with #604/deferred settings work but changes auth inputs, not IPTV. Rebase mechanically if revived; preserve both settings sections. |
| #543 — MDBList watched completion | None | None | No overlap with our work. It conflicts with current main before our changes are considered. |
| #518 — Localized Continue Watching titles | None | None | No overlap with our work. It conflicts with current main in `LauncherContinueWatchingRepository.kt`. |
| #504 — “Only IPTV” mode | Conflict-risk | High | Not the same as deferred catalog visibility. It changes catalog read filtering and IPTV settings row indices used by #601/#604 and `533acfd5`; it also has Changes Requested. If revived, redesign/rebase rather than combining branches blindly. |
| #417 — Profile-selection accent borders | None | None | No feature/file overlap. Branch has no current-main merge base and must be recreated. |
| #416 — Secondary accent colours | File-only overlap | Low | Shares `SettingsViewModel.kt` with #601/#604 but is semantically independent. Recreate on current main and preserve all preference fields. |
| #415 — Episode overview in pause overlay | File-only overlap | Low | Shares `PlayerViewModel.kt` with #602 but changes current-episode overview, while ours gates next-episode autoplay. Recreate/rebase and preserve both. |
| #414 — Hide redundant movie title with clearlogo | File-only overlap | Low | Shares `PlayerScreen.kt` with #602 but is independent of autoplay. Recreate/rebase and retain both conditions. |
| #411 — CloudStream meta-repo browser | File-only overlap | Medium | Shares `CloudSyncRepository.kt` with deferred catalog visibility. If recreated, merge plugin payload fields and catalog visibility fields explicitly. |
| #409 — Auto-skip failed source | Conflict-risk | High | Distinct feature but overlaps #602 player state, #601/#604 settings, and deferred cloud/catalog files. If recreated, integrate player effects, row IDs, cancellation, and cloud fields deliberately. |
| #408 — Catalog artwork editor/cloud sync | Conflict-risk | High | Directly overlaps deferred `533acfd5` in catalog persistence, settings, and cloud sync. If recreated, artwork and visibility must coexist in every `CatalogConfig.copy`, save, export, and restore path. Do not cherry-pick its broad companion-web/plugin additions blindly. |
| #324 — README rewrite | None | None | Documentation-only; no relation to our work. Branch has no current-main merge base. |
| #310 — Buffering settings UI | None | None | Adds a separate buffering screen; no overlap with our changed files/features. Branch must be recreated and its navigation/persistence wiring checked independently. |
| #309 — Gradle build instructions | None | None | README-only; no relation to our work. Branch has no current-main merge base. |
| #308 — Session import race | None | None | Changes `AuthRepository.kt`; no overlap with our work. Branch has no current-main merge base. |
| #307 — PlaybackCookieJar memory leak | File-only overlap | Low | Shares `PlayerScreen.kt` with #602 but changes cookie-cache behavior, not autoplay. Recreate/rebase and preserve both. |
| #306 — Bounded MediaRepository cache | None | None | No changed-file or semantic overlap. #604 consumes MediaRepository but does not modify it. Branch must be recreated. |
| #305 — JWT validation bypass | None | None | Auth/security change with no overlap. Branch must be recreated and independently security-reviewed. |
| #290 — Documentation/codebase alignment | File-only overlap | Low | Touches some app/settings/build files mainly for documentation/structure, not our features. No current-main merge base; recreate only the still-relevant documentation hunks. |
| #288 — Metadata-driven Settings navigation | Conflict-risk | High | Structurally rewrites `SettingsScreen.kt`, directly affecting #604 and all deferred settings features. If recreated/merged, port our IPTV EPG toggle and any later settings rows into its metadata model rather than resolving row-index conflicts mechanically. |
| #284 — Adaptive playback monitoring | File-only overlap | Low | Shares `PlayerScreen.kt` imports with #602; functional changes live in separate playback/TV files. Recreate/rebase and rerun autoplay/subtitle tests. |
| #280 — Offline downloads | Conflict-risk | High | Broadly overlaps `AppNavigation.kt`, `AppBottomBar.kt`, `PlayerScreen.kt`, and `PlayerViewModel.kt` with #599/#602/#604, but is not a duplicate. If recreated, integrate destinations and player state on current main, then rerun all affected suites. |

## Recommended sequence

1. Leave #586 independent; it has no conflict with our six PRs.
2. Watch #598. If it lands before #599/#604, immediately rebase those two and resolve the two confirmed conflicts.
3. Do not change or close our PRs because of the other 23 external PRs: none currently supersedes our behavior.
4. Treat #504, #408, #409, #288, and #280 as future integration projects if their authors recreate/rebase them.
5. Keep deferred Home hero work (`540ee45a`) paused until #598 is resolved, and build deferred catalog visibility (`533acfd5`) with explicit compatibility for any revived #408/#411 work.
