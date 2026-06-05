# 🚀 ARVIO

ARVIO is an Android media hub designed for TV, phone, and tablet form factors.
This repository serves as a source-code and development mirror for the Android application.

---

## 📌 Repository Purpose

This repository is intended for:

* Source code review and development
* Issue tracking and technical discussions
* Build documentation
* License and privacy documentation
* Contribution review

❗ It is **not** a media distribution, streaming, or content hosting platform.

---

## ✨ Features

* Android TV, Fire TV, phone, and tablet UI support
* TMDB-powered browsing for movies, series, cast, collections, and franchises
* IPTV M3U/Xtream support with categories, favorites, EPG, and fullscreen playback
* Optional ARVIO Cloud sync for profiles, settings, catalogs, IPTV state, and watch progress
* Optional Trakt.tv integration (watchlist, history, continue watching)
* Catalog management with manual URLs and external list support
* Home-server integration (Jellyfin, Emby, Plex)
* Third-party addon support for user-configured sources
* Profile-based watch history and state isolation
* Subtitle and audio track selection with filtering tools
* Profile PIN protection and custom avatars
* Media playback via ExoPlayer / Media3 with multi-device controls

---

## 📱 Availability

ARVIO is available on Google Play:

[<img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" width="160">](https://play.google.com/store/apps/details?id=com.arvio.tv)

---

## 💖 Support ARVIO

ARVIO is a free hobby project maintained with ongoing development and infrastructure costs.

If you find it useful, support is appreciated:

👉 https://ko-fi.com/arvio

---

## 📸 Screenshots

| Home                               | Details                                  |
| ---------------------------------- | ---------------------------------------- |
| ![Home](screenshots/home_v190.png) | ![Details](screenshots/details_v190.png) |

| Live TV                                   | Collections                                       |
| ----------------------------------------- | ------------------------------------------------- |
| ![Live TV](screenshots/live_tv_v1991.png) | ![Collections](screenshots/collections_v1991.png) |

| Mobile                                  | Profiles                                    |
| --------------------------------------- | ------------------------------------------- |
| ![Mobile](screenshots/mobile_home.webp) | ![Profiles](screenshots/profiles_v1991.png) |

---

## 📡 Content & Source Policy

ARVIO is a media browser/player interface for user-configured sources.

* Users provide their own playlists, services, addons, and URLs
* No media content is hosted, bundled, or distributed by this repository
* No IPTV playlists, credentials, or third-party streaming content are included

Users are responsible for compliance with local laws and regulations.

If external content violates copyright law, contact the actual source provider.

---

## ☁️ Cloud Sync

ARVIO Cloud is optional and supports syncing:

* Profiles and settings
* Catalogs and IPTV state
* Watch history and progress
* Profile avatars

See [PRIVACY.md](PRIVACY.md) for full details.

---

## ⚙️ Build & Run

### Requirements

* Android Studio / SDK tools
* JDK 17
* Android SDK 35

### Build

```bash
./gradlew :app:assemblePlayDebug
./gradlew :app:assembleSideloadDebug
```

### Install

```bash
./gradlew :app:installPlayDebug
./gradlew :app:installSideloadDebug
```

### Windows

```powershell
.\gradlew.bat :app:assemblePlayDebug
.\gradlew.bat :app:assembleSideloadDebug
```

---

## 🔧 Build Variants

* **play** → Play Store build (no self-update)
* **sideload** → APK build (self-update enabled)
* **debug** → Development build
* **staging** → Release-like test build
* **release** → Production build

---

## 🔐 Local Configuration

Copy and configure secrets:

```bash
cp secrets.defaults.properties secrets.properties
```

For signing:

```bash
cp keystore.properties.template keystore.properties
```

---

## 🚀 Release Checks

Before release:

```bash
./gradlew :app:compilePlayDebugKotlin
./gradlew :app:assemblePlayRelease
./gradlew :app:assembleSideloadRelease
```

---

## 🔒 Privacy

See [PRIVACY.md](PRIVACY.md)

Account deletion:
https://auth.arvio.tv/delete-account

---

## 📄 License

Apache License 2.0 — see [LICENSE](LICENSE)

---

## 🤖 AI Disclosure

This application was developed with significant AI assistance.
All contributions should still be reviewed and tested like normal production code.
