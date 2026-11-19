<div align="center">

  <img src="web/public/arvio-logo.svg" alt="ARVIO" width="96" />

  <h1>ARVIO</h1>

  <p><strong>Your media, together. On TV, phone, tablet and the web.</strong></p>

  <p>An open-source media hub for your home servers, live TV, collections and connected sources.</p>

</div>

<div align="center">

[![Watch ARVIO: a looping preview of the home screen, collections, live TV and connected sources.](netlify-arvio-tv-site/assets/promo/arvio-tour-preview.gif)](https://arvio.tv/#demo)

</div>

<p align="center">
  <a href="https://arvio.tv/#demo"><strong>Watch the full 85-second tour</strong></a>
  &middot; <a href="https://arvio.tv/assets/promo/arvio-tour-720p.mp4">Download video</a>
</p>

<p align="center">
  <a href="https://arvio.tv/">Website</a>
  &middot; <a href="https://arvio.tv/guides/">Setup guides</a>
  &middot; <a href="https://discord.gg/UavuEYMfQ4">Discord</a>
</p>

<p align="center">
  <a href="https://github.com/ProdigyV21/ARVIO/releases/latest"><img src="https://img.shields.io/github/v/release/ProdigyV21/ARVIO?style=flat-square" alt="Latest release" /></a>
  <a href="https://github.com/ProdigyV21/ARVIO/releases"><img src="https://img.shields.io/github/downloads/ProdigyV21/ARVIO/total?style=flat-square" alt="Release downloads" /></a>
  <a href="LICENSE"><img src="https://img.shields.io/github/license/ProdigyV21/ARVIO?style=flat-square" alt="License" /></a>
</p>

**ARVIO includes no movies, series, live channels or third-party subscriptions.** Connect your own authorized services and sources. Catalog artwork and listings are for discovery, not a grant of access to the content.

[Get ARVIO](#get-arvio) &middot; [Features](#features) &middot; [Get started](#get-started) &middot; [Collections](#make-it-yours) &middot; [Self-host](#self-host-arvio-web) &middot; [Build](#build-from-source) &middot; [Contribute](#contributing)

## Get ARVIO

<p>
  <a href="https://play.google.com/store/apps/details?id=com.arvio.tv"><img src="assets/badges/badge_google_play.png" alt="Get it on Google Play" width="160" /></a>
  <a href="https://github.com/ProdigyV21/ARVIO/releases/latest"><img src="assets/badges/badge_github.png" alt="Download the APK on GitHub" width="160" /></a>
  <a href="https://apps.obtainium.imranr.dev/redirect?r=obtainium%3A%2F%2Fadd%2Fhttps%3A%2F%2Fgithub.com%2FProdigyV21%2FARVIO"><img src="assets/badges/badge_obtainium.png" alt="Get it on Obtainium" width="160" /></a>
</p>

| Where you watch | How to get it |
| --- | --- |
| Android TV, Google TV, Fire TV, Android phones and tablets | **Free app.** Install from Google Play where available, or download the signed APK from [GitHub Releases](https://github.com/ProdigyV21/ARVIO/releases/latest). |
| Your own computer or server | **Free self-hosting.** Run the web app with Docker Compose or Node.js and your own integration credentials. [Setup instructions](web/README.md). |

The GitHub APK supports Android 6.0+; the Play build requires Android 7.0+. The standard APK contains ARMv7 and ARM64 builds. Device, codec and service support can vary.

For GitHub updates through Obtainium, add `https://github.com/ProdigyV21/ARVIO` as the app source. The [Obtainium guide](docs/distribution/OBTAINIUM.md) includes an import file and APK filtering instructions.

**What's new?** See the [latest release notes](https://github.com/ProdigyV21/ARVIO/releases/latest) and [changelog](CHANGELOG.md). This README and the tour describe the current `main` branch, which can include improvements not yet in a published APK. Features can differ between Android, web and distribution builds.

## Features

- **A home screen you can make your own.** Choose the expanded-card TV home or the classic layout, arrange catalogs and collections, show your watchlist beneath Continue Watching, and enable optional trailer previews.
- **Collections and discovery.** Browse built-in collections or import your own. Collection pages include artwork, title previews, descriptions and ratings where available. Add compatible TMDB pages, Trakt, SIMKL and MDBList lists, or add-on catalogs.
- **Your library and release Calendar.** Keep watchlists, personal lists and home-server libraries together. See upcoming movies and episodes from supported lists and shows you are watching, with local-time schedules where an air time is known.
- **Plex, Emby and Jellyfin.** Connect your own servers or libraries shared with you, browse their contents and use available server media as playback sources.
- **Live TV and catch-up.** Connect supported M3U, Xtream Codes or Stalker sources. Browse categories, favorites, recently watched channels and the programme guide, with a mini-player and fullscreen playback. Guide coverage, catch-up and seeking depend on your provider.
- **Sports inside Live TV.** Browse live and upcoming events, then choose a matched channel or compatible sports add-on source. Use IPTV, sports add-ons, or both. Coverage and artwork depend on the schedules and sources available to your setup.
- **Add-ons, debrid and Telegram.** Browse and install compatible community add-ons, configure supported debrid sources, or search video files in your connected Telegram channels and groups. These services need their own configuration and access.
- **Trakt and SIMKL tracking.** Connect accounts per profile for supported watchlists, history, scrobbling and Continue Watching/next episodes. SIMKL uses AUTH V2 for new connections; custom-list availability depends on your SIMKL account's capabilities.
- **Playback and subtitles.** Media3 playback on Android, episode-aware controls, source selection, audio/subtitle tracks, automatic frame-rate matching on supported hardware, subtitle language preferences and optional AI subtitle tools.
- **Profiles and optional cloud sync.** Separate viewing state, avatars and PINs, plus age-filtered kids profiles on Android. ARVIO Cloud can sync supported settings, catalogs, playlists and watch state across your devices.

Browser playback depends on the source, CORS, codecs and browser. It is not a guarantee that every source playable on Android will play in a browser. See the [browser playback guide](https://arvio.tv/browser-playback-guide/).

## Get Started

1. **Install and create a profile.** Use local profiles, or connect ARVIO Cloud for supported cross-device sync.
2. **Connect your sources in Settings.** Add your home server, a compatible add-on, your live TV playlist or Telegram account. Browsing a catalog alone does not create a playable source.
3. **Connect your trackers.** Add Trakt or SIMKL for viewing progress and supported lists; connect MDBList if you use its lists.
4. **Make Home yours.** Choose your layout, organize catalogs and import collections. Open a title and select an available source to watch.

[All setup guides](https://arvio.tv/guides/) &middot; [Plex / Emby / Jellyfin](https://arvio.tv/plex-emby-jellyfin/) &middot; [Ask the community](https://discord.gg/UavuEYMfQ4)

## Make It Yours

**Catalogs** are rows of titles, such as a trending feed or a personal list. **Collections** organize supported catalogs and discovery sources into browsable folders with their own artwork.

Use [Collection Studio](https://arvio.tv/collection-studio/) to create a collection, then import its JSON in Settings. You can also paste a supported collection document or a direct JSON URL hosted on GitHub or another public host. **Direct file uploads are not supported yet.**

- [Create, host and import collections and catalogs](https://arvio.tv/collections-catalogs/)
- [Collection format, supported sources and examples](docs/custom-collections.md)
- [Release Calendar behavior and supported sources](docs/library-calendar.md)
- [SIMKL V2 and custom-list setup](docs/simkl-v2-and-custom-lists.md)

Imported collections do not include streaming access. You still need your own sources, and any subscriptions required by the connected service.

## Self-Host ARVIO Web

ARVIO Web is the browser version for desktop, iPhone, iPad and TV browsers. **Self-host it on your own computer or server** with Docker Compose or Node.js, using your own TMDB key and optional integration credentials.

For Node.js, install **Node.js 22.19+** and Git, then:

```sh
git clone https://github.com/ProdigyV21/ARVIO.git
cd ARVIO/web
npm ci
npm run setup:selfhost
```

Edit `.env.local` and set your own TMDB API v3 key as `TMDB_API_KEY`. Keep this file private. Then:

```sh
npm run check:selfhost
npm run build
npm run start -- --hostname 127.0.0.1
```

Open **http://localhost:3000**, create a local profile and connect your sources in Settings.

For Docker Compose, configuration details and updates, follow the [web README](web/README.md). Read the [self-hosting guide](web/docs/SELF-HOSTING.md) before exposing it to your network or the internet; the commands above bind only to the local computer.

Self-hosted profiles and settings are local to each browser; this mode does **not** provide ARVIO Cloud cross-device sync. Connected Trakt/SIMKL accounts can still synchronize the data those services support. Media and access to third-party services are not included.

## Help and Support

- **Setup help and discussion:** [ARVIO Discord](https://discord.gg/UavuEYMfQ4) and [guides](https://arvio.tv/guides/).
- **Bug reports:** [open a GitHub issue](https://github.com/ProdigyV21/ARVIO/issues/new/choose) with the app version, device/OS, install source, steps to reproduce and expected behavior. Include a crash reference or redacted logs when available.
- **Feature requests:** describe the workflow you want to improve and check [existing issues](https://github.com/ProdigyV21/ARVIO/issues) first.

Never post passwords, API keys, tokens, private playlist URLs or unredacted account information in an issue or screenshot.

## Build From Source

This repository contains the Android app, web app, public website and supporting services. Android uses Kotlin and Jetpack Compose; web setup is documented separately in [web/README.md](web/README.md).

### Android Development

Requirements: **JDK 17**, **Android SDK 36**, Android Studio or the SDK command-line tools, and the tracked Gradle wrapper.

```bash
git clone https://github.com/ProdigyV21/ARVIO.git
cd ARVIO
cp secrets.defaults.properties secrets.properties
# Configure the integrations you want to test in secrets.properties.
./gradlew :app:assembleSideloadDebug
```

On Windows PowerShell:

```powershell
git clone https://github.com/ProdigyV21/ARVIO.git
cd ARVIO
Copy-Item secrets.defaults.properties secrets.properties
# Configure the integrations you want to test in secrets.properties.
.\gradlew.bat :app:assembleSideloadDebug
```

The debug APK is written to `app/build/outputs/apk/sideload/debug/`. For an x86/x86_64 emulator, add `-PincludeX86Abis=true`; APK builds otherwise include ARMv7 and ARM64.

Use your own TMDB, Trakt, SIMKL, Telegram and other application credentials for the integrations you test. Placeholder values do not provide working authentication. Cloud/auth features also require appropriate backend configuration. Keep `secrets.properties`, keystores and signing credentials out of Git.

**Test on an emulator or dedicated device.** Ordinary debug builds use the production package ID but a different signing key, so they cannot update an official signed installation. Do not uninstall a user's app just to install a debug build.

<details>
<summary>Build variants, optional SDKs and release verification</summary>

### Variants and Installation

| Variant | Purpose |
| --- | --- |
| `play` | Google Play distribution; in-app self-update disabled. |
| `sideload` | Direct APK distribution; in-app self-update enabled. |
| `debug` | Development build with debugging enabled. |
| `staging` | Release-like beta in the separate `com.arvio.tv.beta` package; uses release signing when configured, otherwise debug signing. |
| `release` | Optimized production build. Configure your own private release keystore before distribution. |

```bash
./gradlew :app:installSideloadDebug
./gradlew :app:assemblePlayDebug
./gradlew :app:testSideloadDebugUnitTest
```

For an emulator, include `-PincludeX86Abis=true` in the build/install command. For a network-connected test device:

```bash
adb connect <device-ip>:5555
adb -s <device-ip>:5555 install -r app/build/outputs/apk/sideload/debug/app-sideload-debug.apk
```

### Optional Integrations

Discord Rich Presence requires Discord's separately licensed Android Partner SDK. Place the approved AAR at `app/libs/discord_partner_sdk.aar` and configure `DISCORD_CLIENT_ID`. Builds without it remain valid and show Discord as unavailable. Do not commit or redistribute the SDK without the required permission.

The speech-recognition AAR is downloaded and checksum-verified by Gradle. Integration availability can differ by distribution: for example, Sync by Hearing is currently a sideload feature.

Debug builds disable crash reporting. Fork maintainers should use their own Firebase client configuration before distributing builds. See [Firebase diagnostics](docs/firebase-diagnostics.md) for configuration, consent and symbol-upload instructions.

### Release Verification

Copy `keystore.properties.template` to `keystore.properties`, configure a private release keystore, and keep both out of version control. A locally built APK does not share the official release signing key.

```bash
./gradlew :app:testSideloadDebugUnitTest
./gradlew :app:assemblePlayRelease
./gradlew :app:assembleSideloadRelease
./gradlew :app:bundlePlayRelease
```

Smoke-test startup, profile switching, playback, source fallback, audio/subtitles, IPTV/EPG, add-on management, search, settings, sync and repeated player open/close on the supported device classes. Complete the [release content/rights checklist](docs/play-copyright-release-checklist.md) before publishing.

</details>

## Contributing

Bug fixes, tests, translations, documentation and accessibility improvements are welcome. Open a focused pull request, explain the change, and include testing details and screenshots for UI work. Discuss larger changes in an issue first.

Keep credentials and private media out of submissions. Public demo assets need documented permission. AI-assisted contributions are welcome under the same review and testing standards as other changes; ARVIO itself has been developed with significant AI assistance.

Thank you to [all contributors](https://github.com/ProdigyV21/ARVIO/graphs/contributors), testers, translators and community members. Individual release credits are preserved in the [changelog](CHANGELOG.md) and [release notes](https://github.com/ProdigyV21/ARVIO/releases).

## Credits, Privacy and License

<img src="app/src/main/assets/tmdb-logo.svg" alt="TMDB" width="100" />

This product uses the TMDB API but is not endorsed or certified by TMDB. Other integrated services, trademarks, SDKs and artwork belong to their respective owners; compatibility does not imply endorsement.

The optional Kaptain collection import example retains credit to [Kaptain / ImKaptain](https://github.com/ImKaptain), [Kaptain Collection](https://github.com/ImKaptain/Kaptain-Collection) and [nuvio-art](https://github.com/ImKaptain/nuvio-art). See [collection credits and reuse notes](docs/custom-collections.md#collection-credits). ARVIO's built-in collection queries are maintained separately.

- **Privacy and cloud data:** [Privacy policy](PRIVACY.md). Cloud account and synced-data deletion is available at [auth.arvio.tv/delete](https://auth.arvio.tv/delete).
- **Content and copyright:** ARVIO does not host, sell or distribute movies, series, channels or streams. Metadata and artwork do not grant viewing rights or permission to reuse an image. Use sources you are authorized to access. Report concerns about ARVIO-controlled material or links through [COPYRIGHT.md](COPYRIGHT.md); independently hosted material may also need reporting to its provider.
- **License:** [Apache License 2.0](LICENSE) for this project. Third-party components and assets retain their own terms.

<p align="center">
  <a href="https://trendshift.io/repositories/28671?utm_source=trendshift-badge&amp;utm_medium=badge&amp;utm_campaign=badge-trendshift-28671"><img src="https://trendshift.io/api/badge/trendshift/repositories/28671/weekly?language=Kotlin" alt="ARVIO on Trendshift" width="250" height="55" /></a>
</p>
