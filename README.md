# ARVIO — Multi-Platform Media Hub & Browser

[![Build Status](https://img.shields.io/github/actions/workflow/status/ProdigyV21/ARVIO/build-check.yml?branch=main&label=Build%20Check&style=flat-square)](https://github.com/ProdigyV21/ARVIO/actions)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square)](file:///Users/durgaprasadml/Documents/ARVIO/LICENSE)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-purple?style=flat-square&logo=kotlin)](https://kotlinlang.org/)
[![Swift](https://img.shields.io/badge/Swift-5.10-orange?style=flat-square&logo=swift)](https://swift.org/)

ARVIO is a production-grade media hub designed for Android (TV, Fire TV, phone, and tablet form factors) and iOS (SwiftUI shell). It provides unified media browsing, catalog configuration, IPTV playlist loading, and home-server integrations. 

> [!IMPORTANT]
> **Content & Source Policy:** ARVIO is a media browser and player interface for user-configured sources. It functions like a web browser: users must configure their own services, playlists, addons, or URLs. This repository does not host, store, sell, or distribute movies, series, live TV channels, playlists, or other copyrighted media.

---

## 1. Project Overview

ARVIO consists of:
- **Android App (`app`):** Written in Kotlin using Jetpack Compose, Compose for TV, and Media3/ExoPlayer. It supports TV remotes and touchscreen controls.
- **iOS App (`iosApp`):** Written in Swift and SwiftUI, serving as an additive native shell.
- **Backend Services (`supabase`):** Supabase database, authentication, and Edge Functions for proxying external APIs (TMDB, Trakt) and supporting TV code pairing.
- **Auth Page (`netlify-auth-site`):** A static HTML site deployed on Netlify that manages web-based user logins, pairing links, and cloud account management.

---

## 2. Key Features

- **Multi-Form Factor UI:** Native layouts optimized for Android TV/Fire TV D-pad navigation, as well as adaptive layouts for iOS/Android phones and tablets.
- **Metadata Indexing:** TMDB-powered movie, series, cast, collection, and franchise browsing.
- **IPTV Integration:** Support for M3U and Xtream Playlists with custom category ordering, hiding, favorites, and Electronic Program Guide (EPG) backfill.
- **ARVIO Cloud Sync:** Optional Cloud synchronization backed by Supabase for profiles, settings, catalogs, IPTV preferences, and profile avatars.
- **Trakt.tv Sync:** Profile-isolated Trakt integration for watched history, progress sync, and watchlists.
- **Home Server Support:** Source and catalog support for user-owned Jellyfin, Emby, and Plex libraries.
- **Addon Ecosystem:** Extensible source scraping using Stremio-compatible addons.
- **Player Enhancements:** Frame-rate matching, subtitle language filters, subtitle offset adjustments, audio track selection, and AI-powered subtitle tools.

---

## 3. Architecture Summary

ARVIO follows a clean architectural pattern:
- **UI Layer:** Jetpack Compose (Android) and SwiftUI (iOS) driving reactive state using ViewModels.
- **Repository Layer:** Encapsulates business logic, orchestrating database transactions, API requests, and data caching.
- **Data Source Layer:** Uses Retrofit/OkHttp for external APIs, Room/SQLite (Android) or local structures (iOS) for storage, and Datastore for settings.
- **Worker Layer:** Android WorkManager coordinates periodic background tasks (e.g. Trakt syncing).
- **Backend Layer:** Supabase Edge Functions act as secure proxies to hide client credentials for TMDB/Trakt and perform TV auth pairing.

For details, refer to the [docs/architecture.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/architecture.md) guide.

---

## 4. Folder Structure Overview

```directory
ARVIO/
├── app/                      # Android Application Module (Kotlin)
│   ├── src/main/kotlin/      # Android source package com.arflix.tv
│   └── src/test/kotlin/      # Android local unit tests
├── benchmark/                # Android Macrobenchmark Module
├── iosApp/                   # iOS Application Shell (Swift/SwiftUI)
│   ├── ARVIO/                # Xcode project source code
│   └── ci/                   # Code signing & CI automation scripts
├── supabase/                 # Supabase Edge Functions & DB Migrations
│   ├── functions/            # Deno/TypeScript serverless functions
│   └── migrations/           # Database schema migrations
├── netlify-auth-site/        # HTML/JS Landing & Account Deletion page
├── docs/                     # Detailed developer and deploy guides
├── screenshots/              # Visual assets for README/Store listings
└── releases/                 # Release notes history
```

---

## 5. Installation/Setup

Detailed local setup instructions can be found in the [docs/setup.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/setup.md) guide.

### Quick Prerequisites
- **Android:** Android Studio Jellyfish+, JDK 17, Android SDK 35.
- **iOS:** macOS, Xcode 15+, XcodeGen `2.40.0+`.
- **Backend:** Node.js 18+, Supabase CLI.

### Quick Start
1. Clone the repository:
   ```bash
   git clone https://github.com/ProdigyV21/ARVIO.git
   cd ARVIO
   ```
2. Copy default secrets:
   ```bash
   cp secrets.defaults.properties secrets.properties
   ```
3. Open the project in **Android Studio** or generate the Xcode project:
   ```bash
   xcodegen generate --spec iosApp/project.yml --project iosApp
   ```

---

## 6. Local Development Workflow

To build and run debug variants locally:

### Android (CLI)
- **Compile Play Store Debug APK:**
  ```bash
  ./gradlew :app:assemblePlayDebug
  ```
- **Install Play Store Debug to Connected Device/Emulator:**
  ```bash
  ./gradlew :app:installPlayDebug
  ```

### iOS (CLI)
- **Generate Xcode project:**
  ```bash
  xcodegen generate --spec iosApp/project.yml --project iosApp
  ```
- **Open Xcode:**
  ```bash
  open iosApp/ARVIO.xcodeproj
  ```

For more info, see [docs/development.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/development.md).

---

## 7. Environment Variables

Key parameters are read by Gradle from `secrets.properties` or environment variables in CI/CD. The default keys include:

| Secret Key | Purpose | Default Value |
|------------|---------|---------------|
| `SUPABASE_URL` | Cloud Sync & Auth API Endpoint | `https://your-project.supabase.co` |
| `SUPABASE_ANON_KEY` | Supabase Public JWT Key | `your-supabase-anon-key` |
| `GOOGLE_WEB_CLIENT_ID` | OAuth Client ID for Google Auth | `your-google-web-client-id...` |
| `SENTRY_DSN` | Sentry crash tracking URL (optional) | `disabled` |
| `TMDB_API_KEY` | TMDB API Key (direct fallback) | `your-tmdb-api-key` |
| `TRAKT_CLIENT_ID` | Trakt Client ID (direct fallback) | `your-trakt-client-id` |
| `TRAKT_CLIENT_SECRET` | Trakt Client Secret (direct fallback) | `your-trakt-client-secret` |

---

## 8. Usage Examples

### Configuring a Custom IPTV Playlist
1. Navigate to **Settings > IPTV Configurations**.
2. Select **Add M3U Playlist**.
3. Input your M3U URL and (optional) XMLTV EPG URL.
4. Save to start loading the channel list.

### Syncing Profiles
1. Go to the profile selection screen.
2. Select **Cloud Connect**.
3. If on TV, scan the QR code to pair your device. If on mobile, log in with your email/password.

---

## 9. Configuration Explanation

- **`secrets.properties`:** Stores local API credentials and dev keys. (Ignored by Git).
- **`keystore.properties`:** Holds details of the signing certificate (`storeFile`, `storePassword`, etc.) used to sign releases. (Ignored by Git).
- **`supabase/config.toml`:** Controls local Supabase emulation and proxy settings.
- **`netlify-auth-site/netlify.toml`:** Specifies redirection rules and headers for the Netlify static site.

See [docs/configuration.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/configuration.md) for details.

---

## 10. Scripts/Commands Reference

| Command | Action |
|---------|--------|
| `./gradlew :app:assemblePlayDebug` | Builds Android Play Store Debug APK |
| `./gradlew :app:assembleSideloadDebug` | Builds Android Sideload Debug APK |
| `./gradlew :app:installPlayDebug` | Installs Play Store Debug APK on connected device |
| `./gradlew :app:testPlayDebugUnitTest` | Runs all local JUnit tests for Play Debug |
| `./gradlew detekt` | Runs Detekt static analysis checks |
| `xcodegen generate --spec iosApp/project.yml --project iosApp` | Regenerates the Xcode project files |

---

## 11. Contribution Guide References

We welcome developer contributions! Before submitting code:
1. Review the [CONTRIBUTING.md](file:///Users/durgaprasadml/Documents/ARVIO/CONTRIBUTING.md) guide.
2. Comply with the [CODE_OF_CONDUCT.md](file:///Users/durgaprasadml/Documents/ARVIO/CODE_OF_CONDUCT.md).
3. Ensure all code tests pass and comply with Detekt styling format.

---

## 12. Documentation Navigation Section

To read more about specific parts of the codebase:

- [README.md](file:///Users/durgaprasadml/Documents/ARVIO/README.md) - Main repository overview (this document).
- [CONTRIBUTING.md](file:///Users/durgaprasadml/Documents/ARVIO/CONTRIBUTING.md) - Guidelines for contributing code.
- [CODE_OF_CONDUCT.md](file:///Users/durgaprasadml/Documents/ARVIO/CODE_OF_CONDUCT.md) - Behavior and community guidelines.
- [docs/architecture.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/architecture.md) - System architecture and dependency dataflows.
- [docs/setup.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/setup.md) - Environment installation checklist.
- [docs/development.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/development.md) - Development commands and workflows.
- [docs/configuration.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/configuration.md) - App parameters and credentials reference.
- [docs/api.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/api.md) - Edge Function API proxies documentation.
- [docs/deployment.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/deployment.md) - CI/CD pipeline automation and TestFlight uploads.
- [docs/troubleshooting.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/troubleshooting.md) - Common problems and resolution guide.
- [docs/ios-testflight.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/ios-testflight.md) - iOS App Store/TestFlight packaging instructions.

---

## 13. Troubleshooting Section

Common development issues:
- **Unable to locate a Java Runtime:** Ensure JDK 17 is installed. Run `export JAVA_HOME=/path/to/jdk` in your terminal shell.
- **Supabase Invalid JWT Signature:** Check that your local `secrets.properties` contains matching `SUPABASE_ANON_KEY` credentials matching your Supabase project.

For exhaustive answers, refer to [docs/troubleshooting.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/troubleshooting.md).

---

## 14. FAQ Section

#### Q: Does ARVIO host or stream content directly?
No. ARVIO is a player shell and metadata browser. Users must supply their own content sources, playlists, or IPTV providers.

#### Q: How do I test cloud synchronization locally?
Deploy local Supabase edge functions or use Supabase emulation via the CLI. Configure your `secrets.properties` to target the local emulator port.

---

## 15. License and Acknowledgements

This project is licensed under the **Apache License 2.0**. See the [LICENSE](file:///Users/durgaprasadml/Documents/ARVIO/LICENSE) file for the full text.

Developed with AI assistance. Contribution reviews and commits undergo normal engineering code standards.
