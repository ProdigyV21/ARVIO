# Changelog

All notable changes to this project are documented in this file.

## [Unreleased]

### Added
- (Nothing yet)

## [1.5.0] - 2026-02-17

### Added
- Full TV device pairing stack with Supabase edge functions updates and dedicated approve/status/start flows.
- Web pairing frontend scaffold (`netlify-auth-site/`) for QR login/register -> pair flow.
- Skip Intro integration in player (`SkipIntroApi`, `SkipIntroRepository`, `SkipIntroButton`) with UI support.
- QR rendering component for in-app pairing (`QrCodeImage`).
- New Supabase migrations for user settings and watch-history stream pinning.

### Changed
- App version bumped to `1.5.0` (`versionCode 150`).
- Catalog system refactor for stronger profile-aware behavior and preinstalled/custom catalog handling.
- Home data loading and row merge behavior for faster incremental catalog rendering.
- Media repository catalog fetch logic tuned to target larger row fill and better custom list coverage.
- Player pipeline and stream selection behavior updated for more stable playback startup and source selection.
- Settings/Addons/Catalogs flows revised with improved focus and input handling for Android TV remote usage.
- Supabase config and TV auth function wiring updated for production pairing flow.
- ProGuard/network module/repository wiring updated to support new auth/player/catalog pathways.

### Fixed
- Source discovery regression where results became very slow or stalled after initial successful loads.
- Autoplay/source fallback behavior that switched too aggressively across sources.
- Playback start issues at `00:00` for some streams and large files.
- Large 4K stream handling and retention so high-size sources are given a fair start window.
- VOD source visibility and matching reliability, including TV-show catalog flow improvements.
- Subtitle menu back-navigation behavior (back now closes subtitle layer correctly instead of exiting playback flow).
- Cloud/account pairing flow reliability between app and web sign-in path.
- TV remote navigation issues in settings forms/addon-list sections (focus traversal and keyboard interaction).
- Catalog row population limits causing low item counts in some custom/built-in rows.

## [1.4.0] - 2026-02-14

### Added
- Optional `ARVIO Cloud` account connection in Settings for syncing profiles, addons, catalogs, and IPTV settings.
- Supabase migration and edge functions for TV device auth flow: `tv-auth-start`, `tv-auth-status`, `tv-auth-complete`.

### Fixed
- Trakt connect now displays activation URL and code while authorization is pending.
- Cloud sign-in/sign-up modal D-pad navigation (Down/Up/Left/Right) is now consistent on Android TV remotes.

## [1.3.0] - 2026-02-11

### Added
- IPTV settings now include a dedicated `Delete M3U Playlist` action to remove configured M3U/EPG and IPTV favorites.
- Updated release screenshots for Catalogs and Live TV (`v1.3`).

### Changed
- Player controls overlay no longer adds a dark background scrim behind play/pause controls.
- Sidebar focus visibility and section handoff behavior improved for clearer TV remote navigation.
- Continue Watching cards show resume timestamp and a subtle progress track.

### Fixed
- Resume metadata flow to keep Continue Watching playback start position aligned with player start.
- Multiple focus/scroll consistency issues across Home/Settings/TV surfaces.

## [1.2.0] - 2026-02-10

### Added
- Live TV page in sidebar with IPTV support.
- M3U playlist configuration in Settings.
- Catalogs tab in Settings for custom Trakt and MDBList URLs.
- Catalog ordering controls (up/down) and deletion for custom catalogs.
- Live TV mini-player flow and expanded TV navigation support.
- New screenshots for Live TV and Catalogs in README.

### Changed
- Home and catalog loading behavior across profiles.
- Focus and scroll behavior improvements across Home, Details, Search, Watchlist, and TV surfaces.
- Player/stream handling refinements for smoother transitions.
- App release version updated to `1.2.0`.

### Fixed
- Continue Watching visibility and persistence regressions.
- Custom catalog rows not appearing on Home in some profile states.
- IPTV and mini-player stability issues including focus restore and state persistence.
- Multiple UI alignment and layout consistency issues in Settings and TV screens.
