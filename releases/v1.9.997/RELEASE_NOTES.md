# ARVIO v1.9.997

This update improves Live TV and Stalker playback, remote navigation, seek previews, library sorting, and tracking across devices. It includes all changes on main since v1.9.996, through source commit `bbb469245fbdb341f34057fd1fbd08e73b61fe02`, plus release packaging.

## Android TV and mobile

- **More reliable IPTV and Stalker playback:** preserve portal link requirements, reuse the correct handshake token, recover expired redirects, respect server stream types, and stop repeated retries of dead channels. Provider requests and startup work are bounded. Contributor: @ReichiMD. [#675](https://github.com/ProdigyV21/ARVIO/pull/675)
- **Smoother Live TV guide and navigation:** preserve category and playlist selection, improve drawer re-entry and focus outlines, fix channel focus bouncing and clipped programme times, and reduce guide rendering work with indexed channel lookup. Added playback transitions and mini-player fades. Contributor: @Himanth-reddy. [#672](https://github.com/ProdigyV21/ARVIO/pull/672)
- **More complete guide imports:** preserve saved playlists and indexed catch-up guides, improve guide import completion, and bound provider traffic and cache work.
- **Better seek previews:** display ready frames without moving the player controls, recover cancelled preview work, retain decoder work between seeks, and automatically commit remote seeks. Preview availability still depends on the stream and whether a frame has been decoded.
- **Player stability:** fix an exception during source discovery and address startup, guide, and cache failures. Contributor: @Himanth-reddy. [#667](https://github.com/ProdigyV21/ARVIO/pull/667)
- **Library and Search:** add release-date sorting, retain Library scroll position and sidebar focus, show saved media-server tabs without waiting for discovery, prioritize title matches, and improve TV Search navigation.
- **Home and Details:** preserve the configured Favorite TV catalog order and fix backward season-rail clipping and scrolling. Contributor: @Himanth-reddy. [#660](https://github.com/ProdigyV21/ARVIO/pull/660), [#659](https://github.com/ProdigyV21/ARVIO/pull/659)
- **Continue Watching:** preserve authoritative Trakt progress and retain IPTV VOD entries across tracker refreshes.
- **SIMKL:** update API conventions across Android, web, and proxies; improve deletion reconciliation and tracking reliability. Contributor: @Himanth-reddy. [#670](https://github.com/ProdigyV21/ARVIO/pull/670)
- **Telegram and configuration:** identify ARVIO device sessions, support custom API credentials, and explain when Telegram is unconfigured. Public build configuration is separated from private credentials. Contributor: @Himanth-reddy. [#669](https://github.com/ProdigyV21/ARVIO/pull/669), [#671](https://github.com/ProdigyV21/ARVIO/pull/671)
- **Localization and trailers:** move remaining hardcoded interface text into translation resources, use official trailers, and add copyright safeguards. Contributor: @ReichiMD. [#656](https://github.com/ProdigyV21/ARVIO/pull/656)

## Web app and hosted services

These changes are delivered through the web app and services; they are not installed by the Android APK.

- Keep Home cards stationary while hover previews change the hero.
- Remove stale watched Trakt episodes from Continue Watching, reconcile cross-device completion, preserve explicit rewatches, and deliver playback tracking to Trakt and SIMKL correctly.
- Expand browser playback compatibility, improve conversion and home-server sessions, and recover interrupted range requests with bounded retries and memory queues. Compatibility continues to depend on the browser and source.
- Stabilize libraries, profile synchronization, and premium access; synchronize IPTV favourites using Android channel identities and retain IPTV VOD progress.
- Add release-date sorting and simplify independent self-hosting with Docker and personal API keys.
- Improve trial payment recovery, activation diagnostics, conversion measurement, and production integration configuration.

SIMKL and credential contributions by @Himanth-reddy.

## Contributors and complete changelog

Thank you to **@Himanth-reddy and @ReichiMD**. The complete history preserves original Git authors and recorded co-authors, including Claude, rather than crediting only the person who merged a change.

- [Merged pull requests and their authors](https://github.com/ProdigyV21/ARVIO/blob/v1.9.997/releases/v1.9.997/MERGED_CONTRIBUTIONS.md)
- [Every commit since v1.9.996, with author and co-author credits](https://github.com/ProdigyV21/ARVIO/blob/v1.9.997/releases/v1.9.997/COMMIT_CHANGELOG.md)
- [Full source comparison](https://github.com/ProdigyV21/ARVIO/compare/v1.9.996...v1.9.997)

## Downloads

- **ARVIO-v1.9.997-sideload-release.apk:** signed ARM-universal APK for supported Android phones, tablets, and TVs (ARMv7 and ARM64), with the existing sideload plugin and self-update support.
- **SHA256SUMS.txt:** checksums for the release downloads.

App version: **1.9.997**. Android version code: **313**.
