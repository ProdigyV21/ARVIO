# Changelog

All notable changes to this project are documented in this file.

## [Unreleased]

### Added
- (Nothing yet)

## [1.9.997] - 2026-09-09

- Improved IPTV and Stalker playback, guide imports, provider request limits, and Live TV focus and rendering. Contributors: @ReichiMD, @Himanth-reddy.
- Improved seek previews, Library sorting and scroll restoration, Search navigation, Home catalog order, season rails, and startup stability. Contributor: @Himanth-reddy.
- Improved Trakt and SIMKL tracking, Telegram configuration and device sessions, localization, and official trailer handling. Contributors: @Himanth-reddy, @ReichiMD.
- Fixed web Home hover layout shifts and stale watched episodes; improved browser playback, self-hosting, synchronization, and premium recovery. Contributor: @Himanth-reddy.
- Signed APK and Play AAB version 1.9.997, version code 313. Full details and individual credits: [release notes](releases/v1.9.997/RELEASE_NOTES.md), [merged contributions](releases/v1.9.997/MERGED_CONTRIBUTIONS.md), and [commit changelog](releases/v1.9.997/COMMIT_CHANGELOG.md).

## [1.9.996] - 2026-09-06

- Redesigned mobile playback, seek previews, Home loading, and navigation. Contributors: @Himanth-reddy, @silentbil, @Saelon600, @ProdigyV21.
- Expanded IPTV, Stalker portals, guides, and selective imports. Contributors: @ReichiMD, @Saelon600, @Aaronnn17, @Himanth-reddy, @silentbil, @ProdigyV21.
- Added Brazilian Portuguese and expanded localization and Continue Watching language handling. Contributors: @procopio1000, @ReichiMD, @Aaronnn17, @ProdigyV21.
- Updated integrations, subtitles, website branding, and release packaging. Contributors: @silentbil, @Saelon600, @Himanth-reddy, @keviiixaviiii, @ProdigyV21.
- Retained the existing installed-plugin behavior by reverting the proposed separation (#655) before release. Revert: @ProdigyV21. Original proposal, deferred for further review: @Himanth-reddy.
- Full per-update credits, 55 historically merged PRs (54 effective, #655 reverted), and all 344 source-history commits since v1.9.995 are in [release notes](releases/v1.9.996/RELEASE_NOTES.md), [merged contributions](releases/v1.9.996/MERGED_CONTRIBUTIONS.md), and [commit history](releases/v1.9.996/COMMIT_CHANGELOG.md). This preserves individual Git authors, including @tormox, co-authors, and PR contributors. Release packaging: @ProdigyV21.

## [1.9.995] - 2026-08-20

- Added 16 KB memory-page support to the Play release native libraries.

### Tracking and Library
- Fixed SIMKL libraries appearing empty by accepting the live list response used for Movies, Shows, and Anime while retaining compatibility with wrapped responses.
- Restored Trakt personal and custom list contents in Library and kept list loading isolated to the active profile.
- Improved SIMKL Library and Continue Watching synchronization, including partial-failure recovery and stricter title matching for entries without TMDB IDs.

### Anime and playback
- Added official anime season structures backed by ARM and Kitsu while preserving canonical TMDB and Trakt episode identities for tracking, progress, and source lookup. Contributor: @Leyto59 via #568.
- Finalized playback progress before Next Episode navigation so watched state and scrobbles are not cancelled during destination changes. Contributor: @Aerya via #583.
- Fixed missing subtitle add-on results and upgraded Groq subtitle translation to GPT-OSS 120B. Contributor: @silentbil via #578.
- Fixed mobile AI subtitle Save and Cancel buttons not responding. Contributor: @silentbil via #587.

### Discord, profiles, and settings
- Added native Discord Rich Presence with poster artwork, episode details, elapsed playback time, secure TV authorization, and account controls. Contributor: @Himanth-reddy via #517.
- Fixed selecting the already-active profile sometimes requiring repeated clicks. Contributor: @silentbil via #579.
- Added IPTV sort-order controls and synchronized the selected order across profiles and devices. Contributor: @Himanth-reddy via #581.
- Added monochrome branding for beta builds so beta and production installs are visually distinct. Contributor: @Himanth-reddy via #585.

### Web and membership
- Improved premium trial conversion, entitlement handling, and direct source download visibility.
- Fixed responsive site navigation overlap and renamed the web membership tab for clearer navigation.

### Release contributors
- @Aerya
- @Himanth-reddy
- @Leyto59
- @silentbil
- @ProdigyV21

### Project contributors
- @Aaronnn17
- @Aerya
- @Bivek01
- @chillpill244
- @EierKopZA
- @eierkop80-stack
- @foXaCe
- @Himanth-reddy
- @jonahmichael
- @Leyto59
- @mvanhorn
- @NightCorpse
- @nubblyn
- @pika1998
- @pjetrazz
- @ProdigyV21
- @sagedavids-ZA
- @saidai-bhuvanesh
- @silentbil
- @test01203
- @willhowlett

## [1.9.983] - 2026-07-30

### Player, subtitles, and playback
- Moved ordinary subtitles into the full player viewport so they can use letterbox space instead of being forced above the bottom black bar.
- Preserved authored ASS/SSA/WebVTT positioning and bitmap subtitle coordinates while applying the new letterbox behavior to normal text subtitles.
- Fixed the Next Episode close button restarting its countdown instead of cancelling playback progression and returning to the show.
- Fixed duplicate source names causing a source-selector crash by guaranteeing stable, unique list keys.
- Restored trailer autoplay after profile and cloud settings are loaded.
- Remembered the last played episode and browsing season when returning from the player, including brief plays without a resume point. Contributor: @silentbil via #468.
- Fixed extensionless IPTV HLS redirects so redirected streams are detected and played with the correct media type.
- Made aborted Telegram source requests end quietly instead of surfacing as crashes.

### Live TV, IPTV, and Continue Watching
- Fixed Live TV focus being trapped in search and reduced category/channel focus jumps while data is loading.
- Stabilized paged IPTV channel snapshots so channel windows remain available while navigating very large playlists.
- Added regression coverage for Live TV startup, channel availability, search focus, and guide-loading rules.
- Removed live channels and live-addon events from Continue Watching, including delayed live metadata results. Contributor: @Himanth-reddy via #492.
- Reused the web Live TV channel snapshot on entry to reduce unnecessary list rebuilding and improve startup responsiveness.

### Catalogs, details, and integrations
- Added Catalog Packs so multiple catalogs can be discovered, installed, grouped, deep-linked, and removed together. Contributor: @Himanth-reddy via #463.
- Added MDBList as a remote sync provider alongside Trakt across Android TV, mobile, and web, with follow-up matching and sync fixes. Contributor: @silentbil via #488 and #489.
- Added a configurable episode-rating heatmap to TV and mobile details pages without additional metadata requests. Contributor: @NightCorpse via #469.
- Added compatibility for Nuvio-style JavaScript scraper plugins and redesigned plugin settings for TV and mobile. Contributor: @Himanth-reddy via #466.
- Preserved legacy catalog-kind fallback behavior so older catalog configurations continue to load.
- Switched AI subtitle translation to Gemini 3.5 Flash Lite and improved MDBList integration behavior. Contributor: @silentbil via #489.
- Added rounded launcher icons and Android 13+ themed icon support. Contributor: @Himanth-reddy via #499.
- Corrected the Spanish translation resources. Contributor: @Aaronnn17 via #497.

### Reliability and platform support
- Updated Android targeting to API level 36 while retaining support for older Android and Fire TV devices.
- Improved cancellation propagation, network diagnostics, regex reuse, and exception safety across catalogs, cloud sync, home servers, IPTV, Trakt, analytics, and playback. Contributor: @Himanth-reddy via #476.
- Fixed sideload production-plugin routing, extractor unloading, mobile routing, and TV focus limits. Contributor: @Himanth-reddy via #466.
- Fixed analytics compilation and logging consistency introduced by the broader reliability work. Contributor: @Himanth-reddy via #476.

### ARVIO Cloud, security, and accounts
- Centralized web and TV-pairing authentication through `auth.arvio.tv` for a consistent ARVIO Cloud sign-in flow. Contributor: @Himanth-reddy via #472.
- Hardened Catalog Pack submissions with URL validation, SSRF protection, rate limiting, and safer manifest handling. Contributor: @Himanth-reddy via #463.
- Expanded disposable-email blocking for cloud account registration. Contributor: @mvanhorn via #496.
- Fixed web login redirect loops, stale-account profile leakage, signup routing, and login wordmark overlap. Contributor: @Himanth-reddy via #475.
- Added the ARVIO Web membership and trial flow while keeping the Android APK free.
- Improved linked Ko-fi entitlement refresh and made membership-trial failures report their actual cause.

### Web app
- Added one-click external-player support for VLC and compatible players on Windows, macOS, Linux, and Android, including protocol setup and URL repair.
- Added TV-style D-pad spatial navigation and remote-key handling for browser-based TV platforms.
- Improved Chromium MKV direct play and added a remux watchdog for playback that stalls before video starts.
- Unified web source playability decisions so badges, filters, and Play buttons agree; unsupported sources no longer expose a broken Play action.
- Re-resolved expired debrid links before failing playback and detected Dolby Vision releases that could otherwise produce a black picture.
- Clarified provider-gated IPTV VOD sources and removed duplicated add-on names from their source descriptions.
- Replaced mislabeled TMDB scores with real IMDb ratings on movies, shows, and episodes.
- Aligned Continue Watching with Trakt Up Next, recovered expired Trakt sessions, and clamped invalid next-episode results.
- Added Telegram web integration. Contributor: @silentbil via #478.
- Improved MDBList, watched-state, and web synchronization behavior. Contributor: @silentbil via #488 and #489.
- Fixed home-server catalog titles and retained artwork placeholders for items without images. Contributor: @Himanth-reddy via #493.
- Fixed overlapping details metadata, genre chips, overview content, hero spacing, and stacked page padding. Contributor: @Himanth-reddy via #494.
- Refined the responsive settings navigation, sidebar focus, mobile overlay bounds, and active-state styling. Contributor: @Himanth-reddy via #474.
- Added a dedicated VLC integration section with Linux support and protocol diagnostics. Contributor: @Himanth-reddy via #473.
- Updated the website and README with ARVIO Web and iPad information.

### Contributors
- @Himanth-reddy
- @silentbil
- @NightCorpse
- @Aaronnn17
- @mvanhorn

## [1.9.92] - 2026-05-11

### Home server sources and catalogs
- Added Home Server source support for user-owned Jellyfin, Emby, and Plex libraries.
- Added Home Server catalog import so personal server collections can appear as ARVIO catalogs.
- Added distinct server labels in sources so multiple connected servers can be identified clearly.
- Improved Home Server matching speed, source labels, playback readiness, and autoplay behavior.
- Improved Plex authentication discovery and matching reliability.

### TV, IPTV, and VOD
- Improved full IPTV EPG backfill coverage so more channels receive guide data.
- Improved live TV category context actions, category reorder behavior, and left-navigation focus.
- Improved channel logo loading performance in the TV page.
- Fixed South Africa country labeling in TV categories.
- Improved IPTV VOD quality handling for episodes and sources.

### Details, search, and navigation
- Added TMDB movie collections to details pages and moved collection rows above More Like This.
- Fixed duplicate "Collection" naming in details pages.
- Fixed several details-page spacing, cast-row, collection-row, and poster-clipping issues.
- Fixed details page cast focus jumps and vertical focus skips.
- Fixed search genre filters, search keyboard activation, and search/filter focus indicators.
- Improved home hero syncing so focused cards drive the displayed metadata more reliably.
- Improved focus border behavior and added focus-border color support.

### Continue Watching, profiles, and cloud
- Added continue-watching card enhancements, including clearer season/episode progress badges.
- Fixed false "continue at" resume times on new or unwatched upcoming episodes.
- Refreshed Continue Watching after cloud restore so cloud login restores visible progress sooner.
- Added synced custom profile avatars and fixed avatar preservation during cloud sync.
- Fixed season unwatch and batch season-watch behavior to avoid unnecessary duplicate Trakt writes.
- Fixed Trakt-connected watchlist add/remove failures after token refresh by using the secured Trakt auth proxy.

### Player, subtitles, and accessibility
- Fixed remote selection for the next-episode prompt and routed up-next remote keys correctly.
- Added AI subtitles support and upgraded Media3/ExoPlayer to 1.9.0.
- Added AI subtitle settings on mobile.
- Fixed manual subtitle selection being overwritten by default subtitle rules.
- Fixed subtitle language filtering and subtitle sorting behavior.
- Added subtitle offset and subtitle style settings.
- Added spoiler blur support and Android TV 10 fallback behavior.
- Added trailer sound controls and improved trailer setting behavior on mobile.

### Policy and cleanup
- Removed the non-working CloudStream integration path from the app for this build.
- Tightened Play/GitHub policy wording, README content, and source disclosure.
- Removed Advertising ID usage from the Play build path and clarified privacy/account deletion documentation.

### Contributors
- Sage Gavin Davids: search focus/filter fixes, details collection visibility, continue-watching cards, poster episode badges, subtitle/trailer/spoiler settings, and TV layout fixes.
- EierkopZA: spoiler blur fallback, focus border color support, TV details poster clipping, search filter focus borders, and collection/watchlist focus fixes.
- Himanth Reddy: regex/performance optimization work, codebase optimization, README maintenance, and catalog/settings stability work.
- silentbil: AI subtitles, subtitle scoring/sorting, subtitle settings fixes, and mobile AI settings visibility.

## [1.9.91] - 2026-05-01

### IPTV and TV page
- Reworked IPTV category handling so provider playlist groups can stay in the same order users configured in their IPTV list.
- Added the expandable All Channels grouping for automatically matched categories.
- Added category context actions for hiding and restoring IPTV groups.
- Removed extra playlist-name clutter from channel rows.
- Improved mobile and tablet TV playback fullscreen behavior so the bottom navigation bar no longer remains visible.
- Changed the mobile top navigation label from TV Shows to TV.
- Improved IPTV VOD source handling so multiple available qualities can appear instead of only one VOD quality.

### Watchlist and Continue Watching
- Fixed Trakt watchlist order so items better follow the latest-added order.
- Fixed Trakt watchlist matching so the app is less likely to choose the wrong remake or wrong year.
- Fixed a regression where the watchlist could briefly load and then disappear into an empty state.
- Fixed stale local watchlist data on TV after switching accounts or profiles.
- Improved Continue Watching startup so cached items appear faster on the home screen.
- Improved Continue Watching behavior with and without Trakt so profile-specific progress is used more consistently.

### Playback and sources
- Improved source switching reliability in the player. Contributor: EierkopZA.
- Improved source loading from fast Search-to-Details navigation. Contributor: EierkopZA.
- Improved player back behavior and playback navigation. Contributor: Himanth Reddy.
- Improved stream startup behavior for selected sources.
- Improved trailer and service video behavior.
- Fixed loading clearlogo flicker in the player. Contributor: EierkopZA.
- Improved Android TV stability on lower-memory devices by reducing image-cache pressure during catalog scrolling and before stream playback starts.

### Catalogs and discovery
- Added Discover Catalogs search for public Trakt and MDBList lists.
- Improved Discover Catalogs TV focus outlines and navigation.
- Improved Discover Catalogs mobile layout.
- Made catalog list adding a one-click action, with Added state feedback.
- Improved catalog rename and dialog language handling.
- Fixed catalog layout controls and focus behavior. Contributor: Himanth Reddy.
- Improved catalog navigation restoration. Contributor: silentbil.

### Details, anime, and metadata
- Fixed anime episode source matching for multi-season anime.
- Fixed details page metadata behavior. Contributor: EierkopZA.
- Improved details/source reliability by waiting for IMDb ID where needed. Contributor: EierkopZA.
- Fixed several details layout and focus regressions.
- Added and refined Crunchyroll assets. Contributor: Himanth Reddy.

### Settings, language, profiles, and cloud
- Improved app language resources. Contributor: silentbil.
- Added subtitle language filtering UI. Contributor: silentbil.
- Fixed DNS persistence. Contributor: Himanth Reddy.
- Fixed cloud login/startup language restore.
- Improved profile loading and profile creation behavior.
- Improved profile and player focus fixes. Contributor: silentbil.

## [1.9.9] - 2026-04-28

### Android TV / IPTV overhaul
- Reworked the TV page for very large IPTV lists, including lists with 50,000+ channels.
- Improved channel loading, first EPG appearance, favorites, recent channels, and startup behavior.
- Fixed major DPAD focus and navigation issues across IPTV rows and channel lists.

### Smoother TV navigation
- Improved rail scrolling, focus behavior, and animation timing across the home, details, watchlist, collections, and TV pages.
- Reduced jank in heavy catalog sections such as genres, franchises, Top 10, and recently added rows.
- Fixed multiple focus cropping and blinking issues without lowering artwork quality or removing video previews.

### Playback and source loading
- Improved source discovery speed and reliability for HTTP, VOD, IPTV VOD, and debrid sources.
- Restored and improved MP4/service video playback behavior.
- Improved autoplay selection so higher-quality and larger sources are preferred while keeping startup faster.
- Added frame-rate matching before playback to reduce stutter.

### Trakt, watchlist, and continue watching
- Reworked Trakt watchlist ordering and matching so items better follow the newest-added order from Trakt.
- Improved matching by title, year, and type to avoid wrong versions, such as older remakes or unrelated entries.
- Fixed continue watching logic so it uses real in-progress Trakt data instead of everything that was ever left unfinished.
- Improved profile isolation for Trakt data, watch history, watchlist, and continue watching.

### Profile isolation and cloud sync
- Profiles now have isolated settings, catalogs, Trakt connections, history, watchlists, and continue watching.
- Addons and IPTV can still be shared where intended.
- Improved real-time cloud sync behavior across profiles and devices.
- Fixed force cloud sync and subtitle preference persistence.
- Added and refined profile PIN support, including fixes for mobile profile creation.

### Mobile and settings improvements
- Reworked the mobile settings layout and naming, including renaming "Stremio" to "Addons".
- Improved profile creation and editing on mobile, including keyboard handling and avatar picker scaling.
- Added app-wide language coverage for the languages listed in app settings.
- Added better catalog and IPTV management controls on mobile.

### Collections, catalogs, and metadata
- Fixed several genre, service, franchise, and Top 10 catalog issues.
- Top 10 Movies and Top 10 Shows are now capped correctly.
- Removed unwanted Favorite TV catalog behavior from the homescreen.
- Improved metadata logos and IMDb SVG rating display on home and details pages.
- Removed MAL score display.
- Added cleaner provider logos, including Netflix, HBO Max, Disney+, Prime Video, Hulu, Paramount+, Peacock, Apple TV+, IMDb, and others.

### Contributors
Thank you to everyone who helped with this release, including:
- EierkopZA
- Himanth Reddy
- chrishudson918
- mrtxiv
- And many more people who contributed smaller fixes, ideas, testing, and feedback. Thank you.

### Sources
- Metadata and discovery: TMDB, IMDb metadata/logo assets, Trakt.
- Sync/auth: Supabase and ARVIO Cloud.
- Playback/addons: IPTV M3U/Xtream/Stalker sources, Stremio-compatible addons, and community HTTP sources.
- Smoothness references: Android TV device traces and public Android TV performance research.

## [1.9.8] - 2026-04-10

### Added
- Premium source picker overhaul shared between Details and Player, with richer source cards, improved metadata chips, better sorting, and clearer quality/release/audio/provider presentation.
- Clock format setting in Settings (`12-hour` / `24-hour`) with app-wide top bar clock support.
- Volume Boost setting using Android `LoudnessEnhancer`.
- MAL score badge on anime details pages.
- Mobile-visible back button on deep screens.
- Post-episode "Up Next" prompt that respects auto-play-next.
- Fire TV / Bluetooth media remote support (play/pause, stop, rewind/fast-forward, next/previous episode).
- Multiple named IPTV playlist backend support (up to 3 lists) with enabled/disabled state.

### Improved
- Top navigation bar redesigned: centered nav items, settings gear on the right, avatar-only profile entry, cleaner visual hierarchy.
- Home screen startup speed: categories cached to disk for near-instant relaunch, Continue Watching fetch decoupled from `loadHomeData` so it can complete independently.
- Image loading and perceived loading speed improved via dedicated Coil client, larger disk/memory caches, DNS warm-up, better preload behavior, and empty-image-url guards.
- Player controls, top bar focus, screen transitions, row emphasis, and card interactions feel smoother and more premium.
- Tablet player controls are larger, better centered, and more readable on bright content.
- Source picker labeling refined so torrent/cached/VOD are surfaced more accurately and without noisy HTTP/Direct badges.
- Top 10 rows redesigned to use normal cards with gold rank badges instead of oversized background numerals.
- TV page EPG now loads up front when stale/missing instead of trickling in after page open.

### Fixed
- In-app updater downloads but never installs (missing PackageInstaller broadcast receiver / confirmation flow).
- Profile dialog focus flow and input handling.
- Deleted catalogs flashing back on home load.
- Player crash when switching audio language.
- Details page now focuses the first unwatched episode by default.
- Custom subtitle addons like Wizdom/Ktuvit now install and resolve correctly.
- IMAX badge added; Dolby Vision badge false positives fixed.
- Cross-device cloud sync timing improved with ON_RESUME pull, watch-history realtime updates, token refresh, and dirty-push retry behavior.
- Continue Watching / Trakt logic substantially reworked to reduce stale and incorrect items, better handle new episode premieres, and improve refresh timing.
- Home focus/row stability improved across startup and catalog updates.
- Trailer button / trailer behavior and details-page asset prefetching improved to reduce clearlogo and episode-load lag.
- Mobile watchlist/details/search/settings responsiveness improved, including reduced first-press dead time and faster activation.
- Poster rows no longer use internal bottom gradients.
- Top 10 badges now stay visible when cards are focused.
- Normal login flow now performs full cloud restore (not just addons), so catalogs, IPTV favorites, and other cloud-backed state restore after login.
- `main` pushed to GitLab and GitLab repo prepared as the active remote while GitHub remains suspended.

## [1.9.74] - 2026-04-03

### Fixed
- Fixed unreadable white-on-white buttons throughout the app
- Fixed cloud sign-in failing with misleading "expired" error
- Fixed app startup crash on certain TV devices
- Fixed Play Store builds not connecting to cloud services
- Fixed APK signing key mismatch causing "app in conflict" install errors

## [1.9.7] - 2026-04-01

### Added
- Trakt watchlist two-way sync: items added in ARVIO sync to Trakt and vice versa
- Clearlogo overlays on watchlist cards
- Clearlogo repositioned to bottom-left corner on all landscape cards for a cleaner look
- Watchlist preloads on app startup for instant display
- Home screen categories cached for instant re-navigation
- Automated release pipeline (GitHub Actions: build, GitHub Release, Play Store, Discord)

### Improved
- Player buttons: focused state now shows white filled circle with black icon
- Subtitle system: only the selected subtitle is loaded instead of all 30+, significantly faster playback startup
- Non-English subtitles (OpenSubtitles) now work reliably across all languages
- Poster cards 10% larger on home screen with proper row spacing
- Watchlist poster cards sized consistently with home screen
- Watchlist grid columns optimized for poster layout (6-8 columns)
- Home screen card titles removed (clearlogo on card is sufficient)
- Real-time cloud sync fixed: WebSocket now authenticates with user JWT for instant cross-device updates
- Addon input modal: D-pad navigation fully working after typing/pasting URL
- Addon save reliability: fixed race condition where addon showed as added but wasn't persisted

### Fixed
- Continue Watching showing episodes/seasons that don't exist (e.g., S2E1 for a 1-season show)
- Watchlist page: left D-pad navigation to sidebar now works correctly
- Watchlist/sidebar: selecting Home/TV/Settings no longer accidentally opens a details page
- Subtitle rebuild loop removed: no more flickering or infinite re-preparing during playback

## [1.9.2] - 2026-03-19

### Added
- Full mobile/tablet support: same APK now works on phones, tablets, and TV with adaptive UI.
- Mobile bottom navigation bar replacing TV topbar on touch devices.
- Mobile Home: swipeable hero carousel with clearlogo, IMDb badge, auto-scroll, page indicators.
- Mobile Details: vertical scroll layout with backdrop gradient, labeled action buttons, touch-scrollable sections.
- Mobile Settings: single-column layout with horizontal tab chips.
- Mobile Search: soft keyboard with OutlinedTextField, touch-scrollable result rows.
- Mobile Player: touch controls overlay (tap to toggle, drag to seek, tap play/pause).
- Mobile Sources page: full-width single-column stream selector with tappable cards.
- Mobile Subtitle/Audio menu: bottom-sheet style with tappable track items.
- Mobile Context menus: bottom-sheet style with slide-up animation and touch items.
- Mobile Live TV: optimized vertical layout with smaller fonts and touch-friendly channel rows.
- Mobile Cast/Person modal: vertical scrollable layout with centered photo and biography.
- Mobile Profile page: scrollable LazyRow for 4+ profiles, smaller avatars on phone.
- Long-press context menu on mobile Home cards (via combinedClickable).
- Cloud connect button on profile selection page (opens QR on TV, email/password on mobile).
- Default home launcher intent filter so ARVIO can be set as default launcher.
- Background logo prefetch for all Home categories on mobile (not just first 2 rows).
- Frame rate matching: real display mode switching via Display.Mode API with stabilization polling.

### Improved
- Playback startup speed through progressive source loading, background prefetch, and smart autoplay window.
- Player buffering tuned for large debrid files (80MB byte cap, cache bypass for heavy streams).
- Subtitle ordering: embedded subtitles appear first and are auto-selected over addon subtitles.
- In-app updater: marks installed tag as ignored to prevent re-prompt loop, clears on actual version upgrade.
- IPTV error messages: stripped HTML/CSS from provider error responses, human-readable messages for common HTTP errors.
- Dialogs responsive on mobile: CloudPair, AppUpdate, UnknownSources, InputModal, SubtitlePicker all adapt width.
- Live TV fullscreen EPG overlay: smaller fonts and tighter layout on mobile.
- Top gradient behind topbar for readability over backdrops.
- Collapsible category rail in Live TV when browsing channels.
- Bottom bar visual upgrade: top border, larger icons, pill highlight, indicator dot.

### Fixed
- Major play-button crash from SimpleCache folder lock conflict (singleton fix).
- Mobile-only crash after profile selection from TV launcher channel provider on non-TV devices.
- Continue Watching wrong resume time: removed ALL stale position leak paths (Supabase history cleanup, CW cache purge, zero-value placeholders).
- Edit Profile delete button rendering as thin white stripe (missing weight/fillMaxWidth).
- Player error buttons not clickable when source/subtitle menu was open.
- Player error on fast-forward/rewind with mid-playback recovery (light seek first, then re-prepare).
- Player select/enter key now always toggles play/pause.
- Subtitle/source menu focus broken by touch clickable modifier on player container.
- Live TV sound continuing when switching to another app (lifecycle-aware pause/resume).
- Live TV fullscreen black screen (postDelayed player attachment with requestLayout/invalidate).
- Trakt list catalogs disappearing from Home (merge filtering and DataStore race fixes).
- IPTV config cloud sync timing for non-primary profiles.
- Text overflow/vertical wrapping across player, settings, details, bottom bar (maxLines + ellipsis).
- Profile page TV focus restored (Surface for TV, Box+clickable for mobile).
- Profile dialog focus: Create/Save button gets initial focus on TV.

## [1.9.1] - 2026-03-14

### Improved
- Playback startup speed improved ~300% through progressive source loading and background stream prefetching on Details page open.
- Smart autoplay: when stream cache is warm (prefetched), playback starts instantly. When cold, a 3.5s collection window ensures the best source is selected from all responding addons.
- Player buffering reduced with larger buffer window, 256MB disk media cache, and stronger connection reuse for large files.
- Live TV mini-player no longer switches channel on focus change; first click previews, second click opens fullscreen.
- Search page layout tightened so Movies and TV Shows rows are fully visible and readable under the topbar.
- Non-English subtitle selection with OpenSubtitles now matches correctly using normalized language tokens.
- Details action buttons (Sources, Trailer, etc.) now render instantly without waiting for external IDs to load.

### Fixed
- Major crash when pressing play caused by SimpleCache folder lock conflict when re-entering the player. Fixed with a process-wide singleton cache.
- Intermittent crash from ExoPlayer race conditions during rapid navigation and force-unwrap on nullable season/episode fields.
- Continue Watching showing wrong resume time on unwatched next episodes (e.g. "Continue S2E2 33:02" after finishing S2E1).
- Trakt list catalogs disappearing from Homepage after initial load due to premature merge filtering and DataStore re-trigger race.
- IPTV config for non-primary profiles not persisting to cloud due to cloud push timing before DataStore flush.
- ExoPlayer onPlayerError listener crash after player release during back navigation.
- AudioManager unsafe cast crash on non-standard Android TV firmware.
- Home left-scroll viewport not following focus on first left move.
- Live TV timeout popup after extended watching caused by insufficient OkHttp read timeout and small buffer window for IPTV streams.

## [1.9] - 2026-03-13

### Added
- GitHub Releases in-app updater for non-Play installs, including download, installer handoff, and unknown-sources guidance.
- Android TV / launcher Continue Watching publishing support for launcher channels and Watch Next style surfaces.
- Cloud backup/restore coverage for non-Trakt local watched state and local Continue Watching across profiles.
- Downloader code `3366110` documented for direct-install users.

### Changed
- App version updated to `1.9` (`versionCode 190`) and Settings version label now reads from `BuildConfig`.
- Home / Details navigation, focus ownership, and topbar entry were reworked so topbar is entered via `Up` instead of left-edge drift.
- Home and Details metadata/description layout was refined for more stable hero placement and clearer text hierarchy.
- Live TV layout was tightened under the topbar, with denser guide rows, a smaller preview block, and more compact category typography.
- IPTV group/category ordering now preserves playlist-provided order instead of forcing alphabetical sorting.

### Fixed
- Home open-item crash paths caused by placeholder Continue Watching entries and invalid hero/logo fetches.
- Continue Watching now refreshes more reliably across Trakt and non-Trakt profiles, including remove/dismiss persistence and next-episode advancement.
- Details now keeps the correct Continue Watching target and watched markers when opening into a resumed episode/season path.
- Home context menu focus, overlay layering, and back handling regressions.
- ARVIO Cloud TV pairing fallback/verification flow and missing release-build Supabase host configuration.
- Live TV guide viewport/focus polish, including more visible channels and cleaner spacing.
- Startup crash caused by restricted TV provider channel selection query in launcher integration.

## [1.8.4] - 2026-03-04

### Added
- Player binge-group-aware next-episode preference handoff for more consistent source continuity.
- TMDB watch-provider data support in repository layer (used for details enrichment and future UI extensions).

### Changed
- App version label and package version updated to `1.8.4` (`versionCode 184`).
- Home vertical catalog navigation tuned for smoother up/down transitions and lower frame-skip risk.
- Home focus retention now survives category/custom-catalog list updates more reliably.
- Custom catalog incremental load starts earlier to reduce time-to-visible after entering Home.
- Details page layout overhauled for cleaner hierarchy (actions -> seasons -> episodes) with larger, richer episode cards.
- Home and Details metadata/description spacing and typography refined for improved readability.

### Fixed
- Focus could drift off-screen on some pages when navigating back across rows/lists; viewport correction logic now clamps and recenters focus targets.
- Source-switch flow hardened in Player to reduce black/stuck states during stream changes.
- Subtitle switching no longer requires full media-source rebuild in normal track-switch cases.
- Home hero metadata (time/budget/rating) now appears much faster when focus changes.
- Cross-screen focus loss regressions when custom catalogs finished loading on Home.

### Removed
- Search suggestions/typeahead flow from Search screen, including D-pad suggestion navigation and inline suggestion list.

## [1.8.2] - 2026-03-02

### Changed
- Cross-device cloud sync (IPTV, addons, catalogs, watchlist, settings) now triggers on every profile selection instead of only on first app launch.
- Playback starts significantly faster — removed redundant startup buffer gate and lowered initial buffer threshold.

### Fixed
- Continue Watching no longer shows a 60-second empty gap when auto-playing the next episode.
- "Mark as Watched" from the context menu now correctly removes the item from Continue Watching.
- "Mark as Watched" now automatically adds the next episode to Continue Watching.
- Watched status now loads from ARVIO Cloud for non-Trakt profiles, so badges appear without a Trakt account.
- Continue Watching now syncs across devices for non-Trakt profiles using profile name instead of device-local UUID.
- Legacy Continue Watching entries no longer leak across profiles.
- Fixed duplicate key crash ("Key was already used") in Continue Watching row when the same show appeared twice.
- Watched badges now appear on initial Details page load without needing to navigate away and back.
- ARVIO Cloud watched data queries now paginate correctly for large libraries (previously capped at 1,000 rows).
- Hero clear logo now loads immediately on startup when selecting a profile, instead of requiring a focus change.
- When no streaming addons are configured, the app now shows a friendly setup guide instead of a playback error.
- Source selector shows setup instructions instead of generic "No sources found" when no addons are installed.
- Next auto-played episode no longer starts at 01:00 — correctly starts at 00:01.

## [1.6.0] - 2026-02-22

### Added
- Extended Live TV EPG timeline model to support multiple upcoming programs per channel (beyond now/next).
- Per-profile cloud snapshot payload maps for settings, addons, catalogs, IPTV config/favorites, and watchlist.
- Repository helpers for profile-specific export/import of addons, catalogs, IPTV config, and watchlist state.
- Expanded HTTP/HTTPS playback compatibility path for stream sources and header handling.
- IPTV VOD support for both movies and TV shows integrated into source resolution flows.
- Card layout mode toggle for switching between landscape and poster styles.
- Default audio language option in Settings with profile-scoped persistence.

### Changed
- App version updated to `1.6.0` (`versionCode 160`) and Settings label updated to `ARVIO V1.6`.
- Live TV EPG lane now uses real upcoming program blocks and only shows filler when timeline data is genuinely unavailable.
- IPTV loading/retry strategy tuned to reduce multi-minute startup delays and improve responsiveness.
- Playback startup buffering strategy rebalanced for movie/TV streams (larger startup gate + safer initial buffer thresholds).
- External subtitle injection timing adjusted to avoid immediate post-start media-item rebuilds.
- Profile boot flow now starts IPTV warm/load earlier after profile selection for faster Live TV readiness.
- Live TV and Settings surfaces received additional UI polish and focus/navigation refinements for Android TV remote use.

### Fixed
- IPTV Refresh action could fail with cancellation errors (`StandaloneCoroutine was canceled`) and not reload channels.
- Live TV timeline third/fourth blocks incorrectly showing `No EPG data` despite available EPG entries.
- Cross-profile leakage risk where addon sets could appear across profiles due to account-wide startup sync behavior.
- Profile isolation gaps by moving remaining global settings storage (`card layout mode`) to profile scope.
- Multiple IPTV EPG parsing paths now keep consistent upcoming-program selection across pull-parser and SAX fallbacks.
- Improved Dolby Vision startup compatibility with automatic codec fallback path (DV -> HEVC -> AVC) before source failover.

## [1.5.0] - 2026-02-17

### Added
- ARVIO Cloud TV pairing flow via QR sign-in/register and direct account linking.
- VOD sources available inside source selection for playback.
- Skip Intro integration in player with dedicated button and backend wiring.
- QR rendering component for in-app pairing.
- IPTV support now includes Xtream Codes connections.

### Changed
- App version bumped to `1.5.0` (`versionCode 150`).
- Updated Downloader install code to `5955104`.
- Catalog limits increased from `20` to `40` entries for built-in catalogs and added Trakt/MDBList catalogs.
- Improved player startup and stream handling to reduce delays before playback starts.
- Better Android TV keyboard and remote handling in settings/addon/list flows.
- Improved compatibility for Fire TV / Firestick class devices.
- Android 7 (API 24/25) support enabled by lowering app minimum SDK requirement.
- Framerate matching behavior refined in playback flow.

### Fixed
- Source discovery regression where results became very slow or stalled after initial successful loads.
- Autoplay/source fallback behavior that switched too aggressively across sources.
- Playback start issues at `00:00` for some streams and large files.
- Large 4K stream handling and retention so high-size sources are given a fair start window.
- VOD source visibility and matching reliability, including TV-show catalog flow improvements.
- Subtitle menu back-navigation behavior (back now closes subtitle layer correctly instead of exiting playback flow).
- ARVIO Cloud account pairing reliability between app and web sign-in path.
- TV remote navigation issues in settings forms/addon-list sections.
- EPG reliability and parser flow issues affecting guide behavior.

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
