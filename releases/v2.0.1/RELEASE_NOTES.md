# ARVIO 2.0.1

**Richer Collections. Sports from your add-ons. Smoother browsing and more reliable playback.**

This update brings together the changes since 2.0.0 across Android TV, phones, tablets and the web app.

## Collections, brought to life

- Import custom collection JSON from a hosted URL, including compatible collection packs, and choose whether to keep the built-in collections.
- Browse refreshed built-in collections with a compact cinematic hero, focused-title clearlogos, descriptions, ratings, release information and movie budgets where available.
- IMDb and streaming-service branding use the same components as Home. The backdrop blends behind the cards and remains visible while browsing.
- Cards now match Home's TV sizing, with corrected phone/tablet grids that fill the available width and adapt to resizing and rotation.
- Faster initial loading: cached list metadata appears before optional detail enrichment, with bounded parallel requests, improved retries and media-type pagination.
- Fixed jumping focus, stale hero images and Continue Watching artwork appearing while a collection is focused.
- Collections now work in the web app with movie/series tabs, responsive grids and a matching hero presentation.
- Added website guides for creating collection JSON, hosting it on GitHub or another free host, and importing collections and catalogs.

## Sports add-ons and Live TV

- Sports schedules can include compatible sports add-ons alongside your IPTV playlists, or work with add-ons alone. Matched add-on streams appear in the event's available sources.
- Prefer existing subscription-backed sports artwork when an event can be matched; retain add-on artwork as a fallback.
- Improved sports source resolution, stream request headers and browser playback handling.
- Live playback now includes a sports match guide beside sports channels, improved expandable-group navigation and a setting for the number of guide rows.
- Live player controls follow your selected accent color, with clearer focus and synchronized guide preferences.
- IPTV settings show provider subscription expiry and connection limits where the provider supplies them; Refresh IPTV also refreshes account information.
- Choose which Stalker movie and series categories are searched. Fixed first-search races, missing category loading and shared portal-session handling.
- VOD sources show their provider and a quality label when the metadata supports it. Unknown quality is no longer guessed, and cached quality labels survive reloads.
- Fixed remote focus returning from the keyboard in playlist dialogs and source loading remaining stuck after one provider finishes.

## Playback and subtitles

- More reliable automatic frame-rate matching: stable source-rate detection, verified display-mode changes, restored display preferences and a narrow fractional/integer fallback for displays that do not expose exact fractional modes.
- Seed frame-rate detection from decoder input metadata so matching does not have to wait for rendered frames. Actual switching still depends on the device and TV's advertised modes.
- Recover supported audio through an alternate decoder when a hardware audio decoder fails during playback.
- New Forced Subtitles preference, improved built-in track labels and automatic subtitle reconsideration when the audio track changes.
- Improved subtitle auto-sync using container indexes and whole-file references from supported Matroska/MP4 sources, with bounded downloads and better best-match selection.
- **Sync by Hearing (GitHub/sideload):** an on-device English-audio fallback when Find Best Match cannot verify subtitle timing. It downloads an approximately 74 MB speech model on first use and has a cloud-synced toggle in Playback settings. The bundled speech engine increases the sideload APK size; this initial feature is not included in the Play build.
- Better subtitle timing for dense dialogue and different edits, plus a fix for untranslated English appearing after switching away from AI subtitles. Turning subtitles off or changing sources cancels pending hearing-sync work. IPTV VOD and live streams use audio from the existing playback connection without opening additional sampling connections.
- Choose Broadcast or Standard TMDB anime episode ordering, with improved season-boundary resolution.
- Stream Integrations settings let you manage enabled providers and their search priority/strategy, synchronized across devices.

## Tracking, integrations and cloud sync

- MDBList now supports OAuth device-code sign-in, with hardened activation, cancellation, profile isolation and cloud synchronization.
- Trakt activation provides a countdown, success/expiry states and a code-prefilled activation link/TV QR code. Initial connection is more resilient to temporary network failures.
- SIMKL sign-in includes copy-and-open and countdown controls; dismissing the activation dialog no longer disconnects an existing account.
- Improved Trakt/MDBList Continue Watching reconciliation, progress ordering and refresh behavior.
- Watched ticks appear in Home, Search and Discover, including local watched history without a Trakt account. Marking watched/unwatched and saving a watchlist update immediately.
- Prevent stale remote history from overriding newer local changes or results from a previous profile.
- Fixed deleted catalogs/add-ons returning after cloud sync, including intentionally empty catalog lists and edits made during outages.
- Restored official Telegram release configuration and home-server library recovery from the 2.0.0 hotfix.
- Telegram searches run on demand instead of flooding requests, preserve results during sibling searches and retain sources when reopening the selector.
- Added compatibility with Jellyfin 12 authentication.

## Navigation and performance

- Preserve tab back stacks and horizontal row positions, with more consistent top-bar and Details D-pad navigation.
- Prioritize visible metadata and request artwork sized to its display slot, reducing background work and memory pressure.
- Improved Home compositing, Settings responsiveness and collection focus stability without removing their visual presentation.
- Smoother mobile predictive-back/dismiss transitions, restored responsive landscape layouts, and fixes for taps reaching the Settings screen behind a subpage.
- Long-press menus remain clear of mobile navigation; update release notes can be scrolled.
- Official Obtainium support for GitHub APK updates.

## Web app and hosted services

Web changes are delivered through the web app, not installed by this APK.

- Collection browsing now aligns with TV, including clearlogos, hero metadata, movie/series filters, retry handling and mobile/tablet layouts.
- Improved in-browser autoplay, playback recovery, add-on request headers, IPTV VOD stream variants and subscriber-first connection handling.
- Fixed iPad remux codec signaling and gesture-blocked playback startup.
- Preserve local settings and add-on visibility changes while cloud data hydrates or the connection is unavailable.
- Updated multilingual setup guides, public media-kit screenshots and the website's Premium showcase and account-recovery links.
- Clearer Premium trial entry, plus practical browser-playback and web-app self-hosting guides.

## Contributors

Thank you to **@Himanth-reddy, @ReichiMD, @silentbil, @Saelon600, @test01203 and @GAPP99**, and everyone who tested and reported issues.

- **@Himanth-reddy:** stream integrations/search strategy, MDBList OAuth, anime ordering, navigation/performance, predictive back and Obtainium support.
- **@ReichiMD:** watched-state feedback, activation dialogs, add-on configuration, Stalker/quality/account details, collection loading, forced subtitles and audio fallback.
- **@silentbil:** Continue Watching reconciliation, Telegram search/source reliability, subtitle matching/auto-sync and Sync by Hearing.
- **@Saelon600:** responsive mobile landscape layouts.
- **@test01203:** custom collection imports and replacement of built-in collections.
- **@GAPP99:** live guide improvements, sports guide placement, expandable groups and configurable guide rows.

The optional collection import example retains credit to [Kaptain / ImKaptain](https://github.com/ImKaptain/Kaptain-Collection). ARVIO's built-in replacement collections are independently authored.

[Merged contributions](https://github.com/ProdigyV21/ARVIO/blob/v2.0.1/releases/v2.0.1/MERGED_CONTRIBUTIONS.md) | [Complete commit history](https://github.com/ProdigyV21/ARVIO/blob/v2.0.1/releases/v2.0.1/COMMIT_CHANGELOG.md) | [Full comparison](https://github.com/ProdigyV21/ARVIO/compare/v2.0.0...v2.0.1)

## Downloads

- **ARVIO-v2.0.1-sideload-release.apk:** signed, optimized ARMv7/ARM64 release for supported Android TVs, phones and tablets. Install over the existing app to retain your account and settings.
- **ARVIO-v2.0.1-source.zip:** source snapshot of this release.

Google Play receives a separate Play-flavor app bundle requiring Android 7.0 or newer, subject to Google's review. The GitHub APK retains Android 6.0 support.

**Version 2.0.1 | Build 318 | GitHub: Android 6.0+ | Google Play: Android 7.0+ | Target SDK 36**
