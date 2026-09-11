# ARVIO v1.9.996

A large update for Android TV and mobile, with a redesigned mobile player, seek previews, expanded IPTV and Stalker support, more consistent navigation, and new translations.

This changelog covers the changes since **v1.9.995**, including older contributions that were merged after that release. Web and website changes are listed separately from the Android app.

## Player and subtitles

- Redesigned the mobile player with audio and subtitle menus, brightness and volume gestures, double-tap seeking, aspect-ratio modes, clearer buffering and error controls, and a reachable unlock button. Added follow-up fixes for gesture cancellation and cancelling Up Next. Contributors: @Himanth-reddy, @ProdigyV21. [#610](https://github.com/ProdigyV21/ARVIO/pull/610)
- Added on-device seek previews across the timeline and quick-seek controls, with aspect-ratio preservation and fallback handling for streams where preview extraction is limited. Contributors: @ProdigyV21, @Himanth-reddy. [Preview work](https://github.com/ProdigyV21/ARVIO/commit/c5282912), [follow-up](https://github.com/ProdigyV21/ARVIO/commit/f6a531cb), [#610](https://github.com/ProdigyV21/ARVIO/pull/610)
- Open the player and show stream-loading feedback earlier after Play, while retaining quality rules, background source discovery, and playback errors. Contributors: @Himanth-reddy, @ProdigyV21. [#651](https://github.com/ProdigyV21/ARVIO/pull/651)
- Fixed mobile landscape back navigation and exit transitions, including waiting for the exit fade before changing orientation. Contributors: @Himanth-reddy, @ProdigyV21. [#652](https://github.com/ProdigyV21/ARVIO/pull/652)
- Stop automatic next-episode playback before an unaired episode. Contributor: @Saelon600. [#602](https://github.com/ProdigyV21/ARVIO/pull/602)
- Added selectable accessible subtitle font families and live updates when changing fonts. Contributor: @Saelon600. [#606](https://github.com/ProdigyV21/ARVIO/pull/606)
- Improved AI bitmap subtitle handling, subtitle best-match selection, text encoding, and cancellation when switching streams. Contributors: @silentbil, @ProdigyV21. [#593](https://github.com/ProdigyV21/ARVIO/pull/593), [#596](https://github.com/ProdigyV21/ARVIO/pull/596), [#627](https://github.com/ProdigyV21/ARVIO/pull/627)

## IPTV, Stalker, and Live TV

- Added Stalker portal setup and support for multiple saved portals, editing and reordering, per-portal group visibility, readable portal names, and isolated portal state. Contributors: @ReichiMD, @ProdigyV21. [#591](https://github.com/ProdigyV21/ARVIO/pull/591), [#607](https://github.com/ProdigyV21/ARVIO/pull/607), [#622](https://github.com/ProdigyV21/ARVIO/pull/622)
- Added Stalker programme-guide loading with bulk and per-channel fallbacks, support for different response formats, and bounded per-portal caching. Guide availability still depends on the portal. Contributors: @ReichiMD, @ProdigyV21. [#623](https://github.com/ProdigyV21/ARVIO/pull/623)
- Unified IPTV, Xtream, and Stalker Add/Edit setup in a TV-friendly widescreen dialog with a mobile layout. Saved fields are prefilled; follow-up fixes preserve guide sources, separate source-type credentials, and set initial focus reliably. Contributors: @Himanth-reddy, @ProdigyV21. [#647](https://github.com/ProdigyV21/ARVIO/pull/647)
- Added separate import choices for Live TV, Movies, and Series, plus an IPTV VOD search toggle and fixes for catalog resolution and older configurations. Contributors: @Aaronnn17, @Himanth-reddy, @ProdigyV21. [#626](https://github.com/ProdigyV21/ARVIO/pull/626), [#628](https://github.com/ProdigyV21/ARVIO/pull/628)
- Added Watch Live and Stream Now guide actions, including matching available content across playlists, respecting programme state, and rejecting stale lookup results. Stream Now requires an available matching source; it does not create an archive where the provider supplies none. Contributors: @Saelon600, @ProdigyV21. [#604](https://github.com/ProdigyV21/ARVIO/pull/604)
- Group Live TV categories in playlist order, preserve mixed and single-source categories, and hide empty category dropdowns when every group is hidden. Contributors: @Saelon600, @ProdigyV21. [#603](https://github.com/ProdigyV21/ARVIO/pull/603), [#611](https://github.com/ProdigyV21/ARVIO/pull/611)
- Reload programme data on manual playlist refresh and keep the guide visible on landscape phones. Contributor: @Saelon600. [#601](https://github.com/ProdigyV21/ARVIO/pull/601), [#600](https://github.com/ProdigyV21/ARVIO/pull/600)
- Keep disabled M3U playlists disabled, including during cache warmup, and improve title matching for accented and other non-ASCII letters. Contributors: @ReichiMD, @ProdigyV21. [#621](https://github.com/ProdigyV21/ARVIO/pull/621), [#635](https://github.com/ProdigyV21/ARVIO/pull/635)
- Fixed favourite-channel menus and startup channels outside the current visible window, and synchronized the favourites-on-Home setting. Contributors: @silentbil, @ProdigyV21. [#636](https://github.com/ProdigyV21/ARVIO/pull/636)
- Improved TV guide drawer focus, remote scrolling, retained guide/scroll state, XMLTV loading, and provider-request limits. Contributor: @ProdigyV21. [Guide navigation](https://github.com/ProdigyV21/ARVIO/commit/d2b451b6), [guide state](https://github.com/ProdigyV21/ARVIO/commit/a8162d9a), [rendering and menus](https://github.com/ProdigyV21/ARVIO/commit/bbbadab3)
- Display measured playback resolution instead of an unknown-quality label, and fit favourite channel logos without background title text. Contributor: @ProdigyV21. [Resolution](https://github.com/ProdigyV21/ARVIO/commit/c838cc5a), [logos](https://github.com/ProdigyV21/ARVIO/commit/6c40ea6e)

## Home, Search, Details, and Library

- Added progressive mobile Home loading, earlier Details header content, season-tab navigation, smoother screen transitions, and predictive-back handling. Follow-up work preserves profile catalog order and avoids repeated pagination refreshes. Contributors: @Himanth-reddy, @ProdigyV21. [#598](https://github.com/ProdigyV21/ARVIO/pull/598), [#612](https://github.com/ProdigyV21/ARVIO/pull/612)
- Cache Home content across reloads and improve cold-start and trailer behavior, while keeping already-loaded cards visible and recording refresh success only after fresh rows arrive. Contributors: @silentbil, @ProdigyV21. [#630](https://github.com/ProdigyV21/ARVIO/pull/630), [loaded cards](https://github.com/ProdigyV21/ARVIO/commit/09ec7c06)
- Aligned TV Home and Details hero layouts and spacing, with measured room for the hero and unclipped content rows. Contributors: @Himanth-reddy, @ProdigyV21. [#645](https://github.com/ProdigyV21/ARVIO/pull/645)
- Fixed TV Search filter focus and adjusted mobile catalog layout, with keyboard and accessible-filter regression coverage. Contributors: @Himanth-reddy, @ProdigyV21. [#649](https://github.com/ProdigyV21/ARVIO/pull/649)
- Made bottom navigation and Details backdrops more compact on landscape phones while preserving readable overlays. Contributors: @Saelon600, @ProdigyV21. [#599](https://github.com/ProdigyV21/ARVIO/pull/599), [#608](https://github.com/ProdigyV21/ARVIO/pull/608)
- Restore mobile bottom navigation after the first cloud connection and clear profile-selection destinations correctly. Contributors: @Himanth-reddy, @ProdigyV21. [#653](https://github.com/ProdigyV21/ARVIO/pull/653)
- Added age-certification badges to movie and show Details. Contributor: @ReichiMD. [#633](https://github.com/ProdigyV21/ARVIO/pull/633)
- Continue Watching and Android TV launcher titles now follow the selected language, with bounded lookups and caching. Contributors: @Aaronnn17, @ProdigyV21. [#518](https://github.com/ProdigyV21/ARVIO/pull/518)
- Added episode thumbnails to Continue Watching and retain IPTV VOD playback there. Contributor: @ProdigyV21. [Thumbnails](https://github.com/ProdigyV21/ARVIO/commit/762a3f61), [IPTV VOD](https://github.com/ProdigyV21/ARVIO/commit/4e2b3b06)

## Languages, integrations, and reliability

- Added Brazilian Portuguese (pt-BR), with follow-up fixes preserving stored language preferences and regional fallback behavior. Contributors: @procopio1000, @ProdigyV21. [#650](https://github.com/ProdigyV21/ARVIO/pull/650)
- Expanded German translations across Player, subtitles, Details, Settings, Live TV, profiles, authentication, updates, and related dialogs. Contributor: @ReichiMD. [#615](https://github.com/ProdigyV21/ARVIO/pull/615), [#616](https://github.com/ProdigyV21/ARVIO/pull/616), [#617](https://github.com/ProdigyV21/ARVIO/pull/617), [#618](https://github.com/ProdigyV21/ARVIO/pull/618), [#631](https://github.com/ProdigyV21/ARVIO/pull/631)
- Moved more Player, Settings, Live TV, watchlist, crash, and related UI text into translatable resources. Home catalog titles, TMDB genres, discovery rows, and status messages follow the selected app language; translated toggle labels no longer change switch behavior. Contributors: @ReichiMD, @ProdigyV21. [#632](https://github.com/ProdigyV21/ARVIO/pull/632), [#634](https://github.com/ProdigyV21/ARVIO/pull/634), [#640](https://github.com/ProdigyV21/ARVIO/pull/640), [#641](https://github.com/ProdigyV21/ARVIO/pull/641), [#642](https://github.com/ProdigyV21/ARVIO/pull/642), [#643](https://github.com/ProdigyV21/ARVIO/pull/643), [#654](https://github.com/ProdigyV21/ARVIO/pull/654)
- Improved Discord mobile sign-in and TV QR pairing, including separate mobile and TV OAuth callbacks. Contributors: @Himanth-reddy, @ProdigyV21. [#592](https://github.com/ProdigyV21/ARVIO/pull/592)
- Updated Telegram integration and mobile Settings navigation. Contributors: @silentbil, @Himanth-reddy. [#605](https://github.com/ProdigyV21/ARVIO/pull/605), [#598](https://github.com/ProdigyV21/ARVIO/pull/598)
- Preserve tracker authentication and routing choices during sync, and allow MDBList alongside Trakt and SIMKL. Contributor: @ProdigyV21. [Sync](https://github.com/ProdigyV21/ARVIO/commit/189a2b92), [integrations](https://github.com/ProdigyV21/ARVIO/commit/b2f7fcc7)
- Fixed the TV focus-restoration crash caused by unattached focus requesters and preserved remembered sidebar focus. Contributors: @ReichiMD, @ProdigyV21. [#613](https://github.com/ProdigyV21/ARVIO/pull/613)
- Hardened exception handling, coroutine cancellation, and memory use, including IPTV HLS detection. Contributor: @Himanth-reddy. [#580](https://github.com/ProdigyV21/ARVIO/pull/580)
- Updated native-library compatibility for 16 KB memory pages. Reduced APK packaging overhead with WebP assets, resource shrinking, and one ARM-universal APK; retained the full font and opt-in x86 emulator builds for development. Contributors: @ProdigyV21, @Himanth-reddy. [16 KB fix](https://github.com/ProdigyV21/ARVIO/commit/cc240908), [#586](https://github.com/ProdigyV21/ARVIO/pull/586)

## Web, website, and project maintenance

- Keep IPTV VOD in the web app's Continue Watching list, repair membership trial conversion, and clarify optional cross-platform web access. Contributor: @ProdigyV21. [Web VOD](https://github.com/ProdigyV21/ARVIO/commit/049d19bc), [trial conversion](https://github.com/ProdigyV21/ARVIO/commit/d653d53a), [web access](https://github.com/ProdigyV21/ARVIO/commit/46420b30)
- Improved website navigation, visible Dashboard/Web App actions, localized page layout, media-management messaging, device and setup guides, and search-engine indexing controls. Contributor: @ProdigyV21. [Navigation](https://github.com/ProdigyV21/ARVIO/commit/bdc9efbf), [localized layout](https://github.com/ProdigyV21/ARVIO/commit/f5ab37b3), [guides](https://github.com/ProdigyV21/ARVIO/commit/8d28108c), [IndexNow](https://github.com/ProdigyV21/ARVIO/commit/aaa343c5), [web-app indexing](https://github.com/ProdigyV21/ARVIO/commit/cd7660b6)
- Updated the official web brand mark and README branding, badges, and web-app links. Contributors: @Himanth-reddy, @keviiixaviiii. [#620](https://github.com/ProdigyV21/ARVIO/pull/620), [#619](https://github.com/ProdigyV21/ARVIO/pull/619)
- Removed obsolete iOS builds, workflows, and documentation from this repository. Contributor: @Himanth-reddy. [#624](https://github.com/ProdigyV21/ARVIO/pull/624)
- Added regression coverage for playback, settings localization, remote focus, guide behavior, search keyboards, IPTV setup, and navigation while integrating these contributions. Contributor: @ProdigyV21; original feature authors and individual test commits are credited in the complete history below.
- The proposed plugin separation in [#655](https://github.com/ProdigyV21/ARVIO/pull/655) was reverted before release and deferred for further review. **Installed plugin/extension support remains in the GitHub/sideload app; the existing Play distribution behavior is unchanged.** Revert: @ProdigyV21. Original proposal: @Himanth-reddy.
- Prepared the signed Android release and Play bundle as version **1.9.996**, version code **312**, with the full source-history and contribution ledgers. Release packaging: @ProdigyV21.

## Contributors and complete history

Thank you to **@Himanth-reddy, @ReichiMD, @Saelon600, @Aaronnn17, @silentbil, @procopio1000, @keviiixaviiii, @tormox, and @ProdigyV21**. The complete history also preserves recorded Git author names, co-authors, and development-tool contributions rather than attributing every change to the person who merged it.

- [All merged contributions, with an author on every entry](https://github.com/ProdigyV21/ARVIO/blob/v1.9.996/releases/v1.9.996/MERGED_CONTRIBUTIONS.md)
- [Complete commit-by-commit changelog and credits](https://github.com/ProdigyV21/ARVIO/blob/v1.9.996/releases/v1.9.996/COMMIT_CHANGELOG.md)
- [Full source comparison](https://github.com/ProdigyV21/ARVIO/compare/v1.9.995...v1.9.996)

## Downloads

The GitHub APK is one **ARM-universal** download for supported Android phones, tablets, and TV devices (32-bit ARM and ARM64). Intel/x86 emulator builds are not included in this download. The Play Store distribution is built separately as an Android App Bundle.

Availability of IPTV guides, VOD, subtitle services, and account integrations continues to depend on the configured provider and credentials. Website changes listed above are not installed by the APK.
