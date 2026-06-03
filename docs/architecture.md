# ARVIO Architecture Documentation

This document describes the high-level architecture, module breakdown, internal dependency flows, data lifecycles, and backend integrations of the ARVIO codebase.

---

## 1. High-Level System Overview

ARVIO is structured as a decoupled multi-platform project with a shared backend service layer.

```mermaid
graph TD
    subgraph Client Apps
        Android["Android TV/Mobile App (:app)"]
        iOS["iOS App (iosApp)"]
    end
    subgraph Cloud Services
        Supabase["Supabase (Edge Functions & PostgreSQL)"]
        Netlify["Netlify Auth site (Web Portal)"]
    end
    subgraph Third-Party integrations
        TMDB["TMDB API (Metadata)"]
        Trakt["Trakt API (Sync & Watchlists)"]
        IPTV["IPTV M3U / EPG Streams"]
        Jellyfin["Jellyfin / Plex / Emby Servers"]
    end

    Android -->|GraphQL/REST| Supabase
    iOS -->|REST| Supabase
    Supabase -->|Secure Proxy| TMDB
    Supabase -->|Secure Proxy| Trakt
    Android -->|Direct HTTP/HLS| IPTV
    Android -->|Direct API| Jellyfin
    Netlify -->|Auth Sync| Supabase
```

---

## 2. Core Android Modules & Folder Responsibilities

The primary Android application resides inside the `app/src/main/kotlin/com/arflix/tv/` directory.

### Module Breakdown
- **[ArflixApplication.kt](../app/src/main/kotlin/com/arflix/tv/ArflixApplication.kt):** Main application class. Initializes Sentry, Hilt Dependency Injection, and registers WorkManager periodic sync workers.
- **[MainActivity.kt](../app/src/main/kotlin/com/arflix/tv/MainActivity.kt):** Single entry point activity. Sets up the Compose content tree, handles deep links, and manages fullscreen system UI visibility.
- **`ui/`:** Implements UI components and screens. Includes `theme/` definitions, custom components (e.g. `Sidebar.kt`, `MediaCard.kt`), and features grouped by page flow (home, search, tv, player).
- **`data/`:** Encapsulates the data access layer:
  - `model/`: Domain models (e.g. `Profile.kt`, `CatalogModels.kt`).
  - `api/`: Retrofit service endpoints (e.g. `TmdbApi.kt`, `TraktApi.kt`).
  - `repository/`: Single source of truth repositories coordinating local cache and remote fetches (e.g. `CloudSyncRepository.kt`, `IptvRepository.kt`).
- **`di/`:** Defines Hilt Dependency Injection modules for providing singleton network, database, and repository instances.
- **`navigation/`:** Manages Compose Navigation graph mapping destinations (Home, TV, Player, Settings) and passing route arguments.
- **`network/`:** Network monitors and interceptors (e.g. `ApiProxyInterceptor.kt`) that append tokens or rewrite URLs for Supabase proxies.
- **`updater/`:** Controls sideload app updates, including APK downloading, file provider sharing, and triggering package installers.
- **`util/`:** Core utility files, including frame-rate matching helpers, subtitle scoring logic, and logging systems.
- **`worker/`:** Background services using Android WorkManager for scheduling period tasks.

---

## 3. iOS SwiftUI Shell Architecture

The additive iOS target resides under `iosApp/`:
- **[ARVIOApp.swift](../iosApp/ARVIO/ARVIOApp.swift):** Entry point of the application.
- **`AuthService.swift` / `CloudSyncService.swift`:** Swift clients that consume Supabase REST endpoints for profile restoration and settings sync.
- **`AddonService.swift`:** Manages addon discovery and resolves stream URLs.
- **`HomeView.swift` / `SettingsView.swift`:** SwiftUI views rendering reactive lists.

---

## 4. Internal Dependency Flow

Data flows reactive-style from database sources up to the UI components.

```mermaid
graph LR
    API["API Data Sources"] --> Repo["Repositories"]
    LocalDB["Local Cache / DataStore"] --> Repo
    Repo --> ViewModel["ViewModels"]
    ViewModel --> UI["Jetpack Compose UI / SwiftUI"]
```

1. **API & Local Cache:** Repositories combine API calls with local SQLite or DataStore preferences.
2. **State Sharing:** Repositories publish Kotlin `Flow`s representing loading, success, or error states.
3. **ViewModel Consumption:** ViewModels collect flows, apply map transformations, and expose state.
4. **Reactive UI:** Compositions collect ViewModel state (using `collectAsStateWithLifecycle`) to trigger recomposition when the data updates.

---

## 5. Configuration & Sync Lifecycles

### Local Storage & Settings
Local settings are stored using Jetpack DataStore Preferences in [DataStores.kt](../app/src/main/kotlin/com/arflix/tv/util/DataStores.kt). This includes subtitle defaults, display layout selections, active profiles, and DNS parameters.

### Cloud Synchronization Flow
1. **Trigger:** Profile change, app resume, or manual sync trigger in Settings.
2. **Fetch:** [CloudSyncCoordinator.kt](../app/src/main/kotlin/com/arflix/tv/data/repository/CloudSyncCoordinator.kt) pulls the latest profile snapshot from Supabase.
3. **Merge:** System compares timestamps and applies incoming updates to local DataStore settings and IPTV favorites.
4. **WebSocket Sync:** When active, [RealtimeSyncManager.kt](../app/src/main/kotlin/com/arflix/tv/data/repository/RealtimeSyncManager.kt) maintains a persistent WebSocket connection to broadcast settings updates in real time to other paired devices.

---

## 6. Critical Architectural Utilities

- **[FrameRateUtils.kt](../app/src/main/kotlin/com/arflix/tv/util/FrameRateUtils.kt):** Matches the physical TV display rate to the video stream rate to eliminate stuttering during ExoPlayer playback.
- **[SubtitleScoring.kt](../app/src/main/kotlin/com/arflix/tv/util/SubtitleScoring.kt):** Scores and ranks available subtitle tracks based on filename keywords (e.g. matching release team) to present the best option first.
- **[OkHttpProvider.kt](../app/src/main/kotlin/com/arflix/tv/network/OkHttpProvider.kt):** Builds the standard HTTP client, injecting interceptors for API credentials and setting custom timeouts for streaming feeds.

---

## 7. Offline Caching Subsystem (Metadata & Artwork)

ARVIO implements a robust local cache-first architecture for TMDB metadata and media artwork to speed up navigation, decrease API requests, and enable seamless offline or low-connectivity browsing.

### Data Flow & Strategy
When a detail page or media list is loaded:
1. **In-Memory Cache:** The repository checks transient in-memory maps for hot-access records (with 5-minute TTL).
2. **Database Cache:** If missed in memory, it queries the local Room Database. If the database entry is fresh (according to `CachePolicyManager` TTL policies), it returns it immediately and prefetches dependencies.
3. **Network Fetch:** If stale or missing, the repository fetches details from TMDB (or Edge Proxy) asynchronously.
4. **Cache Sync:** Upon a successful fetch, the new metadata is stored back in the Room Database, updating memory caches.
5. **Offline Fallback:** If the network request fails but a stale cached record is present in the database, the system gracefully falls back to displaying the stale record.

### Local Cache Entities
The caching layer leverages **Room Database** to persist metadata across the following tables:
- **`media_items`:** Movie and TV Show details (popularity, original language, runtimes, rating, overview, etc.).
- **`cast_members`:** Actor details associated with a TV show or movie.
- **`episodes`:** Individual episodes for TV seasons.
- **`similar_items`:** Inter-item recommendation maps for detail sections.
- **`reviews`:** Textual user reviews from TMDB.
- **`collection_refs` & `search_history`:** Offline reference tables for catalog collections and queries.

### TTL Freshness Policies
Expiration policies are centrally defined in `CachePolicyManager`:
- **Movies & Reviews:** 7 Days
- **TV Shows & Episodes:** 3 Days
- **Cast Details:** 14 Days
- **Franchise Collections:** 1 Day

### Background Refresh & Pruning
A periodic task (`CacheRefreshWorker`) runs under constraints of **charging** and **unmetered network connection** using WorkManager:
1. It queries the database for stale media items.
2. It fetches fresh details for the most recently accessed items to keep details ready.
3. It prunes old metadata from the tables to manage storage size.

### Artwork Caching
All images and artwork are cached using Coil's automated Disk Cache. Custom hooks under **Settings -> Storage** permit querying the directory size and clearing stored images independently.

---

## 📖 Documentation Navigation

- [README.md](../README.md) - Main repository overview.
- [CONTRIBUTING.md](../CONTRIBUTING.md) - Guidelines for contributing code.
- [CODE_OF_CONDUCT.md](../CODE_OF_CONDUCT.md) - Behavior and community guidelines.
- [docs/architecture.md](./architecture.md) - System architecture and dependency dataflows (this document).
- [docs/setup.md](./setup.md) - Environment installation checklist.
- [docs/development.md](./development.md) - Development commands and workflows.
- [docs/configuration.md](./configuration.md) - App parameters and credentials reference.
- [docs/api.md](./api.md) - Edge Function API proxies documentation.
- [docs/deployment.md](./deployment.md) - CI/CD pipeline automation and TestFlight uploads.
- [docs/troubleshooting.md](./troubleshooting.md) - Common problems and resolution guide.
- [docs/ios-testflight.md](./ios-testflight.md) - iOS App Store/TestFlight packaging instructions.
