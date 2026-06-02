# Local Development and Compilation Guide

This document describes build variants, testing procedures, and deployment workflows for the ARVIO codebase.

---

## 1. Android Build Flavors & Types

ARVIO utilizes two product flavors and three build types configured in [build.gradle.kts](file:///Users/durgaprasadml/Documents/ARVIO/app/build.gradle.kts).

### Product Flavors (`flavorDimensions = "distribution"`)
- **`play`:** Targets Google Play. In-app updater checks are disabled. Excludes FFmpeg software decoding libraries to ensure compliance with 16KB system memory alignment constraints in modern Android releases.
- **`sideload`:** Targets direct APK distribution. Self-updating is enabled, and it packs the Jellyfin-built FFmpeg Media3 decoder extension to support DTS/TrueHD/Atmos/HEVC audio/video codecs natively.

### Build Types
- **`debug`:** Developer builds. Includes debugging symbols, enables logcat output, and disables code minification (R8).
- **`staging`:** Signed with the debug keystore, but applies full R8 optimization flags. Useful to test upgrade continuity over old installations while preserving profile caches.
- **`release`:** Production-ready optimized APK. Must be signed with a private signing key.

---

## 2. Compile and Assemble APKs

To compile APKs via the Gradle wrapper command line:

### Play Store Variants
```bash
./gradlew :app:assemblePlayDebug      # Outputs debug Play Store APK
./gradlew :app:assemblePlayRelease    # Outputs optimized signed Play Store APK
```

### Sideload Variants
```bash
./gradlew :app:assembleSideloadDebug    # Outputs debug Sideload APK
./gradlew :app:assembleSideloadRelease  # Outputs optimized Sideload APK
```

APKs are compiled to the output directory:
`app/build/outputs/apk/[play|sideload]/[debug|release]/`

---

## 3. Installing and Running on Emulator or Device

### Using Gradle (Auto-installation)
Ensure you have a running emulator or a connected device listed via `adb devices`.

- **Install Play Debug:**
  ```bash
  ./gradlew :app:installPlayDebug
  ```
- **Install Sideload Debug:**
  ```bash
  ./gradlew :app:installSideloadDebug
  ```

---

## 4. Connecting and Debugging Android TV via Network ADB

Android TV and Fire TV devices are usually debugged over Wi-Fi.

1. Enable **Developer Options** on your TV device:
   - Go to **Settings > Device Preferences > About**.
   - Click **Build** 7 times.
2. Enable **ADB Debugging** under Developer Options.
3. Find the IP Address of your TV in Network Settings.
4. Establish ADB connection:
   ```bash
   adb connect <your-tv-ip>:5555
   ```
5. Install your compiled sideload APK:
   ```bash
   adb install -r app/build/outputs/apk/sideload/debug/app-sideload-debug.apk
   ```
6. Monitor logcat output:
   ```bash
   adb logcat -v time | grep ARVIO
   ```

---

## 5. iOS Development Workflow (macOS)

1. Generate the Xcode project:
   ```bash
   xcodegen generate --spec iosApp/project.yml --project iosApp
   ```
2. Open the workspace project file:
   ```bash
   open iosApp/ARVIO.xcodeproj
   ```
3. In Xcode, select your simulator target or provisioned developer device.
4. Press **Cmd + R** to build and run the SwiftUI application.

---

## 6. Running the Test Suite

We maintain a test suite verifying repositories, models, and utility classes under `app/src/test`.

### Run Unit Tests
```bash
./gradlew :app:testPlayDebugUnitTest
```
Test summaries are generated in HTML format:
`app/build/reports/tests/testPlayDebugUnitTest/index.html`

### Run detekt checks
Verify that your changes match coding styling and static analysis parameters:
```bash
./gradlew detekt
```

---

## 📖 Documentation Navigation

- [README.md](file:///Users/durgaprasadml/Documents/ARVIO/README.md) - Main repository overview.
- [CONTRIBUTING.md](file:///Users/durgaprasadml/Documents/ARVIO/CONTRIBUTING.md) - Guidelines for contributing code.
- [CODE_OF_CONDUCT.md](file:///Users/durgaprasadml/Documents/ARVIO/CODE_OF_CONDUCT.md) - Behavior and community guidelines.
- [docs/architecture.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/architecture.md) - System architecture and dependency dataflows.
- [docs/setup.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/setup.md) - Environment installation checklist.
- [docs/development.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/development.md) - Development commands and workflows (this document).
- [docs/configuration.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/configuration.md) - App parameters and credentials reference.
- [docs/api.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/api.md) - Edge Function API proxies documentation.
- [docs/deployment.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/deployment.md) - CI/CD pipeline automation and TestFlight uploads.
- [docs/troubleshooting.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/troubleshooting.md) - Common problems and resolution guide.
- [docs/ios-testflight.md](file:///Users/durgaprasadml/Documents/ARVIO/docs/ios-testflight.md) - iOS App Store/TestFlight packaging instructions.
