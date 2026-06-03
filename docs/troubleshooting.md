# Troubleshooting and FAQ Guide

This document lists common issues encountered during development, installation, or runtime, and provides troubleshooting steps to resolve them.

---

## 1. Local Build and Compilation Failures

### A. Java Runtime Error
- **Symptom:** `The operation couldn’t be completed. Unable to locate a Java Runtime.`
- **Reason:** No JDK is installed or configured on the terminal path.
- **Solution:** Install JDK 17 (Zulu or Temurin) and set `JAVA_HOME`.
  - **macOS:**
    ```bash
    export JAVA_HOME=$(/usr/libexec/java_home -v 17)
    export PATH=$JAVA_HOME/bin:$PATH
    ```
  - Confirm settings: `java -version`.

### B. Missing or Blank `secrets.properties`
- **Symptom:** Gradle build fails with missing parameter errors or Supabase URL verification checks fail on staging compile.
- **Reason:** Release and staging builds run a verification check task `validateReleaseSupabaseSecrets` which enforces presence of a real `SUPABASE_URL`.
- **Solution:** Create `secrets.properties` and add your endpoint configs:
  ```properties
  SUPABASE_URL=https://your-project.supabase.co
  SUPABASE_ANON_KEY=your-actual-anon-key
  ```

---

## 2. Emulator and Device Connectivity Issues

### A. TV Device Not Showing in `adb devices`
- **Symptom:** Device list is empty or TV returns "Connection refused" when running `adb connect`.
- **Reason:** ADB debugging is disabled on the TV, or the device is on a different subnet.
- **Solution:**
  1. Confirm ADB Debugging is checked under **Settings > Device Preferences > Developer Options**.
  2. Ping the TV IP to verify network routing: `ping <tv-ip>`.
  3. Reset the ADB server:
     ```bash
     adb kill-server
     adb start-server
     adb connect <tv-ip>:5555
     ```

### B. Play Store APK Fails to Install over Staging Build
- **Symptom:** `INSTALL_FAILED_UPDATE_INCOMPATIBLE: Package signatures do not match.`
- **Reason:** Play Store release builds use separate signing certificates compared to staging/debug builds (which utilize default debug keystores).
- **Solution:** You must uninstall the existing build before installing a different signature build:
  ```bash
  adb uninstall com.arvio.tv
  ```

---

## 3. Runtime & Network Troubleshooting

### A. Cloud Sync Fails with "Invalid JWT"
- **Symptom:** Logs show `GotrueException: Invalid token signature` on app startup when sync runs.
- **Reason:** The client `secrets.properties` contains `SUPABASE_ANON_KEY` credentials that do not match the target `SUPABASE_URL`.
- **Solution:** Double check your credentials in the Supabase console dashboard under **Project Settings > API**.

### B. Live TV Page Shows "Timeout / Loader Loop"
- **Symptom:** Selecting a channel results in an infinite loader circle or a timeout error toast.
- **Reason:** The IPTV provider's stream endpoint is offline, or OkHttp's read timeout is too low for a slow IPTV stream.
- **Solution:**
  - Verify that the stream URL plays correctly in a desktop media player (e.g. VLC).
  - Check the logcat for `java.net.SocketTimeoutException`. ARVIO configures connection reuse, but network drops may require a playlist refresh in settings.

---

## 4. FAQ (Frequently Asked Questions)

#### Q: How do I enable the self-updater feature?
Configure the product flavor to `sideload` during compilation. The `play` flavor deliberately disables update check tasks to meet Google Play Store distribution policies.

#### Q: Can I run this app on standard Android phones?
Yes. ARVIO is a universal responsive application. If run on a touch device, the bottom navigation bar (`AppBottomBar.kt`) replaces the TV sidebar navigation (`Sidebar.kt`) automatically.

---

## 📖 Documentation Navigation

- [README.md](../README.md) - Main repository overview.
- [CONTRIBUTING.md](../CONTRIBUTING.md) - Guidelines for contributing code.
- [CODE_OF_CONDUCT.md](../CODE_OF_CONDUCT.md) - Behavior and community guidelines.
- [docs/architecture.md](./architecture.md) - System architecture and dependency dataflows.
- [docs/setup.md](./setup.md) - Environment installation checklist.
- [docs/development.md](./development.md) - Development commands and workflows.
- [docs/configuration.md](./configuration.md) - App parameters and credentials reference.
- [docs/api.md](./api.md) - Edge Function API proxies documentation.
- [docs/deployment.md](./deployment.md) - CI/CD pipeline automation and TestFlight uploads.
- [docs/troubleshooting.md](./troubleshooting.md) - Common problems and resolution guide (this document).
- [docs/ios-testflight.md](./ios-testflight.md) - iOS App Store/TestFlight packaging instructions.
