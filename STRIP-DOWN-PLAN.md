# Arvio Strip-Down Plan

Mål: Skala ner Arvio till en minimal, fokuserad Stremio-addon-baserad mediastreaming-app
med Plex-liknande UI (2026-stil), YouTube-trailers, och svenska som enda språk.

**Viktigt:** Varje fas ska avslutas med en lyckad Gradle build (`./gradlew assembleSideloadDebug`).
Kör INTE `assemblePlayDebug` — Play-varianten rivs i fas 1. Verifiera efter varje fas att appen bygger.

---

## Fas 1: Ta bort hela projekt/mappar (säkert, inga beroenden)

Radera dessa top-level-mappar helt:

```
rm -rf web/                      # Next.js webbklient
rm -rf iosApp/                   # SwiftUI iOS-app
rm -rf netlify-arvio-tv-site/    # Marknadsföringssida
rm -rf netlify-auth-site/        # Cloud auth backend (Netlify Functions)
rm -rf supabase/                 # Alla Supabase Edge Functions + migrations
rm -rf benchmark/                # Baseline profile benchmarks
rm -rf releases/                 # Gamla APK-releases
rm -rf docs/                     # iOS TestFlight docs etc
```

Rensa även:

- Ta bort `app/src/play/` (hela Play build-varianten)
- GitHub Actions: ta bort `ios-testflight.yml`, `ios-upload-existing-ipa.yml` om de finns i `.github/workflows/`
- Ta bort `project.yml` (XcodeGen) om den finns i roten

**Gradle-ändring (build.gradle.kts):**

- Ta bort `play` product flavor (rad ~64-68)
- Behåll `sideload` men den kan bli default (eller döp om till bara en variant)
- Ta bort alla Supabase-relaterade konfiguration

**Verifiera:** `./gradlew assembleSideloadDebug` bygger utan fel.

---

## Fas 2: Riv IPTV/Live TV

Detta är den största borttagningen (~12 000+ rader).

### Filer att radera:

```
# Modeller och data
app/src/main/kotlin/com/arflix/tv/data/model/IptvModels.kt
app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt
app/src/main/kotlin/com/arflix/tv/data/repository/IptvChannelStore.kt
app/src/main/kotlin/com/arflix/tv/data/repository/IptvEpgIndex.kt
app/src/main/kotlin/com/arflix/tv/data/api/StalkerApi.kt

# UI — hela live-TV-mappen
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/CategorySidebar.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/ChannelLogo.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/ChannelRow.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/EpgGrid.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/FullscreenGuideOverlay.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/FullscreenHud.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/LiveCategory.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/LivePanes.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/LiveTokens.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/LiveTvEnhancements.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/LiveTvScreen.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/MiniPlayer.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/ProgramCell.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/QuickZapOverlay.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/SearchOverlay.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/tv/live/TouchCategoryRail.kt
```

### Rensa referenser:

1. **AppNavigation.kt** — Ta bort:
   - `Screen.Tv` route-definition
   - `LiveTvScreen` composable-block (~rad 252-270)
   - Alla `onNavigateToTv`-parametrar och callbacks
   - Import av `LiveTvScreen`
2. **SettingsScreen.kt** — Ta bort IPTV-sektionen (sök efter "iptv", "IPTV", "playlist", "m3u", "xtream", "stalker")
3. **HomeScreen.kt / HomeViewModel.kt** — Ta bort eventuella IPTV-relaterade katalog-anrop
4. **CloudSyncRepository.kt** — (rivs i fas 5, men om den finns kvar: ta bort IPTV-sync-logik)
5. **Models.kt** — Ta bort IPTV-relaterade fält om de finns i gemensamma modeller
6. **Bottom navigation** — Ta bort "TV"/"Live"-flik om den finns
7. **Hilt/DI modules** — Ta bort `@Provides`/`@Binds` för IptvRepository, IptvChannelStore etc.

**Sök brett:** `grep -rn "Iptv\|IPTV\|iptv\|stalker\|Stalker\|LiveTv\|liveTv\|live_tv\|channelId\|IptvChannel\|IptvProgram\|IptvSnapshot\|EpgGrid\|M3U\|m3u\|Xtream\|xtream" app/src/main/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 3: Riv Telegram-integration

### Filer att radera:

```
# Kotlin-filer
app/src/main/kotlin/com/arflix/tv/data/telegram/TelegramAuthState.kt
app/src/main/kotlin/com/arflix/tv/data/telegram/TelegramClient.kt
app/src/main/kotlin/com/arflix/tv/data/telegram/TelegramConfig.kt
app/src/main/kotlin/com/arflix/tv/data/telegram/TelegramRepository.kt
app/src/main/kotlin/com/arflix/tv/data/telegram/TelegramSearchMatcher.kt
app/src/main/kotlin/com/arflix/tv/data/telegram/TelegramSourceResolver.kt
app/src/main/kotlin/com/arflix/tv/data/telegram/TelegramStreamingProxy.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/settings/telegram/TelegramSettingsScreen.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/settings/telegram/TelegramSettingsViewModel.kt

# TDLib native libs (STOR APK-besparing)
app/src/main/jniLibs/arm64-v8a/libtdjni.so
app/src/main/jniLibs/armeabi-v7a/libtdjni.so
app/src/main/jniLibs/x86/libtdjni.so
app/src/main/jniLibs/x86_64/libtdjni.so

# TDLib Java bindings
app/src/main/tdlib-java/org/drinkless/tdlib/Client.java
app/src/main/tdlib-java/org/drinkless/tdlib/TdApi.java
```

### Rensa referenser:

1. **AppNavigation.kt** — Ta bort `Screen.TelegramSettings` route + composable
2. **build.gradle.kts** — Ta bort `java.srcDir("src/main/tdlib-java")` (rad ~155)
3. **build.gradle.kts** — Ta bort Ktor server-dependencies (rad ~313-316), BARA om de inte
   används av annat. Kolla med: `grep -rn "ktor.server\|Ktor\|embeddedServer\|CIO" app/src/main/ --include="*.kt" | grep -v telegram`
   OBS: AiKeyConfigServer.kt kan använda Ktor! Om så — behåll Ktor.
4. **Models.kt** — Ta bort `RuntimeKind.TELEGRAM` om det finns
5. **StreamRepository.kt** — Ta bort Telegram som stream-källa
6. **SettingsScreen.kt** — Ta bort Telegram-settings-sektionen
7. **Hilt/DI modules** — Ta bort Telegram-provides

**Sök brett:** `grep -rn "Telegram\|telegram\|TELEGRAM\|TdApi\|tdlib\|RuntimeKind.TELEGRAM" app/src/main/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 4: Riv plugin-systemet (CloudStream3/QuickJS/Rhino)

### Filer att radera:

```
# Sideload-specifika plugin-filer
app/src/sideload/kotlin/com/arflix/tv/core/plugin/PluginManager.kt
app/src/sideload/kotlin/com/arflix/tv/core/plugin/PluginRuntime.kt
app/src/sideload/kotlin/com/arflix/tv/core/plugin/cloudstream/ExternalExtensionLoader.kt
app/src/sideload/kotlin/com/arflix/tv/core/plugin/cloudstream/ExternalExtensionRunner.kt
app/src/sideload/kotlin/com/arflix/tv/core/plugin/cloudstream/ExternalExtractorRegistry.kt
app/src/sideload/kotlin/com/arflix/tv/core/plugin/cloudstream/ExternalRepoParser.kt
app/src/sideload/kotlin/com/arflix/tv/core/plugin/cloudstream/TvTypeExtensions.kt
app/src/sideload/kotlin/com/arflix/tv/core/runtime/PluginRuntimeHooks.kt
app/src/sideload/kotlin/com/arflix/tv/domain/model/ContentType.kt
app/src/sideload/kotlin/com/lagradost/cloudstream3/AcraApplication.kt
app/src/sideload/kotlin/com/lagradost/cloudstream3/plugins/Plugin.kt
app/src/sideload/kotlin/com/lagradost/cloudstream3/plugins/PluginManagerStub.kt

# Main source set plugin-filer
app/src/main/kotlin/com/arflix/tv/core/plugin/PluginSafety.kt
app/src/main/kotlin/com/arflix/tv/core/plugin/TestDiagnostics.kt
app/src/main/kotlin/com/arflix/tv/data/repository/HttpLocalScraperRuntime.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/plugin/PluginScreen.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/plugin/PluginUiState.kt
app/src/main/kotlin/com/arflix/tv/ui/screens/plugin/PluginViewModel.kt
app/src/main/kotlin/com/arflix/tv/domain/model/Plugin.kt

# Native libs
app/libs/quickjs-kt-android-1.0.5-nuvio.aar
```

### build.gradle.kts — Ta bort sideload-dependencies:

```
# Rad ~358-368 och ~482-494 — ALLA sideloadImplementation utom FFmpeg:
# Ta bort:
sideloadImplementation quickjs-kt-android AAR
sideloadImplementation jackson-databind
sideloadImplementation jackson-module-kotlin
sideloadImplementation NiceHttp
sideloadImplementation conscrypt-android
sideloadImplementation cloudstream library-android
sideloadImplementation rhino
sideloadImplementation re2j
sideloadImplementation crypto-js

# BEHÅLL:
sideloadImplementation org.jellyfin.media3:media3-ffmpeg-decoder
```

### Rensa referenser:

1. **AppNavigation.kt** — Ta bort plugin-relaterade routes om de finns
2. **SettingsScreen.kt** — Ta bort plugin/addon-repository-sektioner (EJ Stremio-addon-hantering)
3. **StreamRepository.kt** — Ta bort plugin-baserade runtime-anrop (behåll StremioAddonRuntime)
4. **AddonRuntimeAggregator.kt** — Ta bort plugin-runtime-registrering
5. **Sideload TMDB-filer** — Behåll `TmdbMetadataService.kt` och `TmdbService.kt` om de
   används av main-koden. Annars radera.
6. **Sideload ProfileDataStoreFactory.kt** — Kolla om main behöver den. Om inte, radera.

**OBS:** Stremio-addon-systemet (StremioAddonRuntime, StreamApi, addon-UI) ska BEHÅLLAS.
Plugin-systemet är JS/DEX-baserade scrapers — det är en annan sak.

**Sök brett:** `grep -rn "PluginManager\|PluginRuntime\|CloudStream\|cloudstream\|ExternalExtension\|HttpLocalScraper\|NUVIO_JS\|EXTERNAL_DEX\|quickjs\|QuickJS\|Rhino\|rhino" app/src/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 5: Riv Cloud Sync + Auth

### Filer att radera:

```
app/src/main/kotlin/com/arflix/tv/data/repository/CloudSyncRepository.kt
app/src/main/kotlin/com/arflix/tv/data/repository/CloudSyncCoordinator.kt
app/src/main/kotlin/com/arflix/tv/data/repository/CloudSyncInvalidationBus.kt
app/src/main/kotlin/com/arflix/tv/data/repository/RealtimeSyncManager.kt
app/src/main/kotlin/com/arflix/tv/worker/CloudSyncWorker.kt
app/src/main/kotlin/com/arflix/tv/data/repository/AuthRepository.kt
app/src/main/kotlin/com/arflix/tv/data/repository/TvDeviceAuthRepository.kt
app/src/main/kotlin/com/arflix/tv/util/AuthEmailValidator.kt
```

### Rensa:

1. **SettingsScreen.kt** — Ta bort Cloud/Account/Sync-sektion, login/logout-UI
2. **build.gradle.kts** — Ta bort Supabase-dependencies om de finns (sök "supabase")
3. **build.gradle.kts** — Ta bort Google Sign-In/Credential Manager om den BARA användes
   för Cloud Auth (sök "credentials-play-services-auth", "googleid"). OBS: Chromecast kan
   behöva Play Services — kolla innan borttagning.
4. **Hilt/DI** — Ta bort provides för CloudSync*, Auth*, TvDeviceAuth\*
5. **WorkManager** — Ta bort CloudSyncWorker-registrering
6. **Profil-systemet** — Cloud sync av profiler tas bort, men lokal profil-hantering behålls

**Sök brett:** `grep -rn "CloudSync\|cloudSync\|cloud_sync\|AuthRepository\|TvDeviceAuth\|RealtimeSync\|supabase\|Supabase" app/src/main/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 6: Riv Home Server (Jellyfin/Emby/Plex)

### Filer att radera:

```
app/src/main/kotlin/com/arflix/tv/data/repository/HomeServerRepository.kt
```

### Rensa:

1. **SettingsScreen.kt** — Ta bort Home Server-konfigurationssektion
2. **CatalogModels.kt** — Ta bort `CatalogSourceType.HOME_SERVER` och relaterade typer
3. **CatalogRepository.kt** — Ta bort Home Server-katalog-logik
4. **Hilt/DI** — Ta bort HomeServerRepository-provides

**Sök brett:** `grep -rn "HomeServer\|homeServer\|home_server\|JELLYFIN\|EMBY\|PLEX\|HomeServerKind\|HomeServerConnection\|HomeServerCollection" app/src/main/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 7: Riv Anime-lager

### Filer att radera:

```
app/src/main/kotlin/com/arflix/tv/data/api/JikanApi.kt
app/src/main/kotlin/com/arflix/tv/data/repository/AnimeScoreRepository.kt
app/src/main/kotlin/com/arflix/tv/util/AnimeMapper.kt
```

### Rensa:

1. **SkipIntroApi.kt** — Ta bort AniSkip-endpoints (behåll IntroDB)
2. **SkipIntroRepository.kt** — Ta bort AniSkip-anrop (behåll IntroDB)
3. **StreamApi.kt** — Ta bort Kitsu API och ARM API (anime ID-mappningar)
4. **DetailsScreen.kt / DetailsViewModel.kt** — Ta bort anime-betyg, MAL-info
5. **Hilt/DI** — Ta bort AnimeScoreRepository, JikanApi provides

**Sök brett:** `grep -rn "Anime\|anime\|Jikan\|jikan\|Kitsu\|kitsu\|AniSkip\|aniSkip\|AnimeMapper\|animeScore\|MAL\|myAnimeList\|arm\.haglund" app/src/main/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 8: Riv Firebase Crashlytics + Analytics

### Filer att radera:

```
app/src/main/kotlin/com/arflix/tv/data/repository/PlaybackTelemetryRepository.kt
app/src/main/kotlin/com/arflix/tv/data/repository/AppUsageAnalyticsRepository.kt
app/src/main/kotlin/com/arflix/tv/util/CrashlyticsProvider.kt
```

### Rensa:

1. **build.gradle.kts** — Ta bort Firebase BOM + crashlytics-ktx (rad ~345-346)
2. **build.gradle.kts** — Ta bort `com.google.firebase.crashlytics` plugin (rad ~18, redan kommenterat?)
3. **google-services.json** — Kan behöva behållas om andra Google-tjänster används (Cast).
   Om BARA Crashlytics använde det → radera + ta bort `com.google.gms.google-services` plugin
4. **Alla anrop** till telemetry/analytics-repositories i ViewModels och screens
5. **Hilt/DI** — Ta bort provides

**Sök brett:** `grep -rn "Crashlytics\|crashlytics\|PlaybackTelemetry\|playbackTelemetry\|AppUsageAnalytics\|appUsageAnalytics\|firebase\|Firebase" app/src/main/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 9: Riv Screensaver + SoundManager

### Filer att radera:

```
app/src/main/kotlin/com/arflix/tv/ui/components/Screensaver.kt
app/src/main/kotlin/com/arflix/tv/util/SoundManager.kt
```

### Rensa:

1. Ta bort alla referenser till `Screensaver` composable
2. Ta bort alla `SoundManager`-anrop (click-ljud etc)
3. Ta bort ljud-resursfiler i `res/raw/` om de finns
4. **Hilt/DI** — Ta bort provides

**Sök brett:** `grep -rn "Screensaver\|screensaver\|SoundManager\|soundManager\|playSound\|playClick" app/src/main/`

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 10: Rensa lokalisering

Radera ALLA locale-mappar utom svenska och default:

```
cd app/src/main/res/
rm -rf values-af values-ar values-bg values-bn values-ca values-cs values-da values-de \
       values-el values-es values-et values-eu values-fa values-fi values-fr values-gl \
       values-hi values-hr values-hu values-id values-it values-iw values-ja values-ko \
       values-lt values-lv values-ms values-nb values-nl values-pl values-pt-rBR \
       values-pt-rPT values-ro values-ru values-sk values-sl values-sq values-sr \
       values-sw values-ta values-te values-th values-tl values-tr values-uk values-ur \
       values-vi values-zh-rCN values-zh-rTW
```

Behåll: `values/` (default/engelska fallback) och `values-sv/` (svenska).

Om appen ska vara rent svensk: sätt svenska som enda locale i `build.gradle.kts`:

```kotlin
defaultConfig {
    resourceConfigurations += listOf("sv")
}
```

**Verifiera:** `./gradlew assembleSideloadDebug`

---

## Fas 11: Slutstädning

1. **Ta bort oanvända dependencies** i build.gradle.kts — kör build, se vad som varnar
2. **Ta bort oanvända imports** — Android Studio / lint kan hjälpa
3. **Ta bort oanvända drawable/resurser** — `./gradlew lint` rapporterar unused resources
4. **Ta bort tomma mappar** som blir kvar efter filraderingar
5. **Kör Detekt** om det är konfigurerat: `./gradlew detekt`
6. **Kör tester**: `./gradlew testSideloadDebugUnitTest`

---

## Sammanfattning: Vad som BEHÅLLS

| Modul                                     | Status                     |
| ----------------------------------------- | -------------------------- |
| Stremio addon-system                      | ✅ Behålls (kärna)         |
| YouTube-trailers                          | ✅ Behålls                 |
| TMDB metadata/browsing                    | ✅ Behålls                 |
| Media3/ExoPlayer + FFmpeg                 | ✅ Behålls                 |
| Trakt.tv sync                             | ✅ Behålls                 |
| Katalog-system (TMDB+Trakt+MDBList+Addon) | ✅ Behålls                 |
| Profiler (lokal, omdesignas senare)       | ✅ Behålls                 |
| Chromecast                                | ✅ Behålls                 |
| AI-undertextöversättning                  | ✅ Behålls (utökas senare) |
| Skip Intro (IntroDB)                      | ✅ Behålls                 |
| Self-updater                              | ✅ Behålls                 |
| Sentry crash reporting                    | ✅ Behålls                 |
| DNS-over-HTTPS                            | ✅ Behålls                 |
| QR-kod-parning                            | ✅ Behålls                 |
| Frame rate matching                       | ✅ Behålls                 |
| Compose UI + theming/skin                 | ✅ Behålls                 |
| DataStore + Hilt DI                       | ✅ Behålls                 |
| Watch history / Continue Watching         | ✅ Behålls                 |
| Coil image loading                        | ✅ Behålls                 |
| Lokalisering (svenska)                    | ✅ Behålls                 |
| Detekt                                    | ✅ Behålls                 |

## Framtida features (EFTER nedskalning)

1. **Kids mode** → whitelist-baserat med admin-läge
2. **AI-undertexter** → +DeepSeek, +OpenAI, +Codex providers
3. **IMDB-listimport** i katalog-systemet
4. **Plex 2026-UI-polish** — mörkt, poster-fokuserat, filmiskt
