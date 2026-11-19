# ARVIO 2.3

**A new TV Home. Richer libraries. Kids profiles. YouTube collections. More reliable tracking and playback.**

Changes since **2.2**, including the playback-sync hotfix previously available in the GitHub APK. Android, web and website changes are identified below; the APK does not deploy or update a web server.

## New TV Home and trailers

- New Netflix-style TV Home with a large featured billboard and focused cards that expand from posters into landscape previews.
- Coordinated horizontal and vertical scrolling, card expansion and artwork blending reduce jumps and brief poster/hero flickers while browsing.
- Restore Continue Watching during startup and keep the focused title's artwork stable as background data arrives.
- Trailer autoplay is back in cards and the main billboard, with native HD playback where the trailer provides a compatible stream, cached resolution and improved fallback handling. Quality still depends on the source and connection; not every trailer offers 1080p.
- The main billboard and row cards use consistent autoplay behavior. Trailer clearlogos stay compact and sharp instead of becoming oversized or blurry.
- Manual trailer playback remains available in Details, including compatible official-player playback.
- Shorter, more accurate descriptions on Home; fuller descriptions on Details. Improved preview ratings and metadata.
- Optional Watchlist row directly below Continue Watching.
- Hold Back on Home to return to the top and the beginning of the catalog rows.
- Favorite TV rebuilds correctly after switching IPTV playlists.
- **On the first launch after updating to 2.3, the new TV Home style and trailer autoplay are enabled for existing profiles as well as new profiles.** Both remain optional in Settings. Later opt-outs are retained, and older cloud backups cannot immediately revert the migration. Trailer sound preferences are unchanged.

## Library and navigation

- Library browsing now uses the collection-style spotlight with title artwork, clearlogos, descriptions and metadata as you move through Watchlists, My Lists and home-server titles.
- Balanced padding and fading blend preview artwork into the black background.
- Improved library row alignment and scrolling so partially obscured cards do not remain awkwardly beneath the fixed preview area.
- Refined Settings focus appearance and responsiveness, with more reliable focus restoration while scrolling and returning from controls.
- Source-list navigation keeps focus on the intended row instead of jumping with reordered sources.
- Recently played source indicators remain attached to the source actually used, separately for each episode.

## Kids profiles and personal lists

- Dedicated kids profiles with age-based content filtering and parent-PIN protection for restricted actions.
- Apply restrictions to Details entry points and catalog pagination, rather than only filtering the first visible row.
- Improved profile synchronization: newer profile edits win, and a device with an outdated profile list cannot silently delete profiles created elsewhere.
- Add titles to your own Trakt and MDBList lists from ARVIO.
- More reliable list pagination, per-item success/failure feedback and profile-safe Home refresh after list changes.

## YouTube collections

- Create a personal YouTube collection row from supported YouTube video links and browse it on Home.
- Collection rows synchronize through the existing profile/catalog system and are supported on Android and web.
- Resolve fresh playback URLs when opening videos and preserve viewing progress without treating YouTube-only items as TMDB movies or episodes.
- Parent-PIN checks protect adding YouTube content to kids profiles.
- Web YouTube playback and progress payloads remain compatible with the existing backend schema.

## Continue Watching, Trakt and SIMKL

- More resilient Trakt and SIMKL Continue Watching, including advancing to the correct next episode after completing an episode.
- Improved reconciliation of provider updates and cached progress so startup or incomplete responses do not unnecessarily hide titles.
- Live streams no longer pollute movie/series Continue Watching.
- Publish episode artwork and clearlogos to Android TV launcher's Continue Watching where supported.
- SIMKL playback reads are gated by activity timestamps instead of unconditionally fetching progress every 15 minutes.
- Use persistent `date_from` checkpoints for incremental playback updates, reconcile cleared sessions and preserve checkpoints when requests fail.
- Coalesce concurrent/forced SIMKL activity checks and respect read backoff to prevent bursts of duplicate requests.

## Live TV and playback

- Select available subtitle and audio tracks directly while watching live channels.
- Detect muxed subtitles and expose available DVB subtitle languages in compatible IPTV HLS streams.
- Keep subtitle/audio menus focused when player controls hide, with more consistent remote navigation and HUD layout.
- Improved recovery for stalled live streams.
- Load the EPG URL declared by an IPTV playlist and normalize pasted playlist/guide addresses.
- Fixed a release-only player verification crash introduced by optimized compilation. The player composition is split to avoid excessive register use, with the updated R8 compiler retained.

## Accounts and diagnostics

- More complete sign-out cleanup, including Discord and SIMKL V2 sessions, with cleanup work kept off the main thread.
- Replaced Sentry with Firebase Crashlytics for diagnostics and optional, opt-in usage analytics.
- Improved Premium checkout handoff, late payment recovery, account isolation and diagnostic attribution, including compact mobile recovery controls.

## Web and website

- YouTube collection browsing/playback and compatibility fixes for saved progress.
- Activity-gated SIMKL playback caching and shared concurrent requests.
- Hosted web access now uses a paid-membership flow with a public feature preview instead of the previous trial flow. This does not turn the free Android app into a paid download.
- Refreshed README with the app tour at the top, updated features and setup information.
- The ARVIO website's main hero now plays the promotional tour.

## Contributors

Special thanks to **[@silentbil](https://github.com/silentbil)** for the new TV Home, trailers, kids profiles, YouTube collections, personal-list actions, Live TV subtitle/audio improvements, source-selection fixes, sign-out cleanup and release-player fixes. Thanks also to everyone who tested builds and reported issues.

Existing third-party collection artwork and data retain their original attribution.

[Merged contributions](https://github.com/ProdigyV21/ARVIO/blob/v2.3/releases/v2.3/MERGED_CONTRIBUTIONS.md) | [Complete commit history](https://github.com/ProdigyV21/ARVIO/blob/v2.3/releases/v2.3/COMMIT_CHANGELOG.md) | [Full comparison](https://github.com/ProdigyV21/ARVIO/compare/v2.2...v2.3)

## Downloads

- **ARVIO-v2.3-sideload-release.apk:** signed, optimized ARMv7/ARM64 release. Install over the existing GitHub app to retain your account and settings.
- **ARVIO-v2.3-source.zip:** source snapshot of this release.

**Version 2.3 | Build 321 | GitHub APK: Android 6.0+ | Google Play: Android 7.0+ | Target SDK 36**

Google Play availability follows its separate review and rollout process.
