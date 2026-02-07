# ARVIO - Android TV Streaming App

A native Android TV streaming application that aggregates content from multiple sources into a unified Netflix-style interface. Built with Kotlin and Jetpack Compose for TV.

## Features

- **Content Discovery** - Browse trending, popular, and new releases from TMDB
- **Stream Aggregation** - Resolves streams from Stremio addons with Real-Debrid/TorBox support
- **Netflix-style UI** - Horizontal row browsing optimized for D-pad/remote navigation
- **Video Playback** - ExoPlayer with FFmpeg decoder for 4K/HDR/DTS/Atmos support
- **Watch Progress Sync** - Cross-device sync via Trakt.tv integration
- **Watchlist** - Cloud-synced watchlist with Supabase
- **Multi-profile** - Multiple user profiles per account
- **Subtitle Support** - Multiple subtitle tracks with language selection
- **Audio Track Selection** - Switch between audio tracks (5.1, 7.1, Atmos)
- **Continue Watching** - Resume playback from where you left off
- **Auto-play** - Next episode auto-play with countdown

## Screenshots

*Coming soon*

## Requirements

- Android TV device or emulator (API 26+)
- JDK 17
- Android Studio (Hedgehog or later recommended)

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/ARVIO-APP.git
cd ARVIO-APP
```

### 2. Configure Secrets

Copy the defaults file and fill in your values:

```bash
cp secrets.defaults.properties secrets.properties
```

Edit `secrets.properties`:
```properties
SUPABASE_URL=https://your-project.supabase.co
SUPABASE_ANON_KEY=your-supabase-anon-key
GOOGLE_WEB_CLIENT_ID=your-google-client-id.apps.googleusercontent.com
```

### 3. Set Up Supabase Edge Functions

The app uses Supabase Edge Functions to securely proxy API requests. API keys (TMDB, Trakt) are stored server-side and never exposed in the app.

#### Deploy Edge Functions

```bash
cd supabase

# Link to your Supabase project
npx supabase link --project-ref your-project-ref

# Set secrets (get these from TMDB and Trakt developer portals)
npx supabase secrets set TMDB_API_KEY=your-tmdb-api-key
npx supabase secrets set TRAKT_CLIENT_ID=your-trakt-client-id
npx supabase secrets set TRAKT_CLIENT_SECRET=your-trakt-client-secret

# Deploy functions
npx supabase functions deploy tmdb-proxy
npx supabase functions deploy trakt-proxy
```

### 4. (Optional) Firebase Setup

For crash reporting, add your `google-services.json` to `app/` and uncomment the Firebase plugins in `app/build.gradle.kts`.

### 5. (Optional) Release Signing

Create `keystore.properties` in the project root:

```properties
storeFile=path/to/your.keystore
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

## Building

### Debug Build

```bash
# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-17
./gradlew.bat assembleDebug

# Linux/Mac
export JAVA_HOME=/path/to/jdk-17
./gradlew assembleDebug
```

APK location: `app/build/outputs/apk/debug/app-debug.apk`

### Release Build

```bash
./gradlew assembleRelease
```

APK location: `app/build/outputs/apk/release/app-release.apk`

## Installation

### Using ADB

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Launch

```bash
adb shell am start -n com.arvio.tv/.MainActivity
```

## Architecture

- **Language**: Kotlin
- **UI**: Jetpack Compose for TV
- **Architecture**: MVVM with Hilt dependency injection
- **Networking**: Retrofit + OkHttp with proxy interceptor
- **Video**: ExoPlayer + Jellyfin FFmpeg extension
- **Auth**: Supabase Auth (Google Sign-In, Email/Password)
- **Sync**: Trakt.tv for watch history, Supabase for cloud data
- **Image Loading**: Coil

## Project Structure

```
app/src/main/kotlin/com/arflix/tv/
├── data/
│   ├── api/          # Retrofit API interfaces (TMDB, Trakt, Stream)
│   ├── model/        # Data models
│   └── repository/   # Data repositories
├── di/               # Hilt dependency injection modules
├── navigation/       # Navigation setup
├── network/          # OkHttp provider, interceptors
├── ui/
│   ├── components/   # Reusable UI components
│   ├── screens/      # Screen composables (Home, Details, Player, etc.)
│   ├── skin/         # Custom design system tokens
│   └── theme/        # Material theme setup
├── util/             # Utilities and constants
└── worker/           # WorkManager background tasks
```

## D-Pad Navigation

| Screen | Up/Down | Left/Right | Enter/OK | Back |
|--------|---------|------------|----------|------|
| Home | Switch rows | Navigate items | Open details | Exit |
| Details | Scroll sections | Navigate items | Play/Select | Go back |
| Player | Show controls | Seek ±10s | Play/Pause | Exit player |
| Settings | Navigate items | - | Toggle/Activate | Go back |

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Privacy Policy

See [PRIVACY.md](PRIVACY.md) for our privacy policy.

## Acknowledgments

- [TMDB](https://www.themoviedb.org/) for movie/TV metadata
- [Trakt.tv](https://trakt.tv/) for watch history sync
- [Supabase](https://supabase.com/) for authentication and cloud sync
- [Jellyfin](https://jellyfin.org/) for the FFmpeg decoder extension
- [Stremio](https://www.stremio.com/) addon ecosystem

## Disclaimer

This application does not host or distribute any copyrighted content. It is a media player that can access publicly available content through user-configured addons and services. Users are responsible for ensuring their use complies with applicable laws in their jurisdiction.
