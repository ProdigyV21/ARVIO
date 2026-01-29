# Arflix Native Android TV App

A native Android TV application built with Kotlin, Jetpack Compose for TV, and ExoPlayer. This is a recreation of the Arflix web app designed specifically for Android TV with D-pad navigation.

## Tech Stack

- **Kotlin** - Primary language
- **Jetpack Compose for TV** - UI framework (`tv-foundation`, `tv-material`)
- **ExoPlayer / Media3** - Video playback with FFmpeg extension for DTS/TrueHD/Atmos
- **Hilt** - Dependency injection
- **Retrofit** - Networking
- **Coil** - Image loading
- **Navigation Compose** - Screen navigation

## Project Structure

```
android-tv-native/
├── app/
│   └── src/main/
│       ├── kotlin/com/arflix/tv/
│       │   ├── ArflixApplication.kt     # Application class
│       │   ├── MainActivity.kt          # Single activity host
│       │   ├── data/
│       │   │   ├── api/                  # Retrofit API interfaces
│       │   │   ├── model/                # Data classes
│       │   │   └── repository/           # Data layer
│       │   ├── di/                       # Hilt modules
│       │   ├── navigation/               # Navigation graph
│       │   ├── ui/
│       │   │   ├── components/           # Reusable composables
│       │   │   ├── screens/              # Screen composables
│       │   │   └── theme/                # Colors, typography, theme
│       │   └── util/                     # Constants, utilities
│       └── res/                          # Resources
├── build.gradle.kts                      # Root build file
└── settings.gradle.kts                   # Settings
```

## Screens

1. **Home** - Hero section with backdrop + category rows (horizontal scrolling)
2. **Details** - Movie/TV show info, episodes, cast, similar content
3. **Player** - ExoPlayer with custom TV controls
4. **Search** - Virtual keyboard + results grid
5. **Watchlist** - Saved items grid
6. **Settings** - Preferences and account linking

## D-pad Navigation

Each screen implements explicit D-pad navigation:

- **Arrow keys** - Navigate between focusable elements
- **Enter/OK** - Select/activate focused element
- **Back** - Go back to previous screen or exit app

Focus states are clearly indicated with:
- White border/glow on focused cards
- Pink highlight on focused buttons
- Scale animations on focus

## Building

### Requirements

- Android Studio Hedgehog or newer
- JDK 17
- Android SDK 34
- Gradle 8.4+

### Steps

1. Open `android-tv-native` folder in Android Studio
2. Sync Gradle files
3. Build and run on an Android TV device or emulator

```bash
cd android-tv-native
./gradlew assembleDebug
```

### Running on Emulator

Create an Android TV emulator:
1. Tools → Device Manager → Create Device
2. Select TV category
3. Choose a TV profile (e.g., Android TV 1080p)
4. Select API 34 system image
5. Finish and launch

### Running on Device

1. Enable Developer Options on your Android TV
2. Enable USB debugging
3. Connect via USB or ADB over WiFi
4. Run from Android Studio

## Configuration

API keys and configuration are in `Constants.kt`:
- TMDB API key (for content metadata)
- Trakt API credentials (for sync)
- Supabase credentials (for cloud storage)

## Features

### Implemented
- [x] Home screen with hero + category rows
- [x] Details screen with episodes/cast/similar
- [x] Basic player with ExoPlayer
- [x] Search with virtual keyboard
- [x] Settings screen structure
- [x] Watchlist screen structure
- [x] D-pad navigation throughout

### Coming Soon
- [ ] Trakt integration (watch history, watchlist sync)
- [ ] Real-Debrid/TorBox stream resolution
- [ ] Subtitle selection in player
- [ ] Continue Watching row
- [ ] Person/Cast modal
- [ ] Addon management

## License

Private - For internal use only.


