# CI/CD and Deployment Guide

This document describes the automated CI/CD pipelines, TestFlight release steps, Netlify configurations, and release smoke test checklists.

---

## 1. Automated GitHub Actions Workflows

We maintain three GitHub Actions workflows in [.github/workflows/](../.github/workflows):

### A. Build Check (`build-check.yml`)
- **Trigger:** Pull Requests targeting the `main` branch.
- **Action:**
  1. Sets up JDK 17 (Temurin).
  2. Generates a temporary Android signing keystore locally.
  3. Builds the sideload debug variant using Gradle: `./gradlew assembleSideloadDebug --no-daemon`.

### B. iOS TestFlight Pipeline (`ios-testflight.yml`)
- **Trigger:** Manual (`workflow_dispatch`).
- **Action:**
  1. Installs **XcodeGen** on a macOS runner.
  2. Injects Supabase parameters into `AppConfig.swift`.
  3. Writes App Store Connect signing credentials.
  4. Generates the Xcode project file: `xcodegen generate --spec iosApp/project.yml --project iosApp`.
  5. Decodes and unlocks distribution signing certificates.
  6. Archives the app and exports the `.ipa` package.
  7. Uploads the final bundle to Apple TestFlight.

### C. iOS Upload Existing IPA (`ios-upload-existing-ipa.yml`)
- **Trigger:** Manual (`workflow_dispatch`) with `ipa_run_id` input.
- **Action:**
  1. Injects App Store Connect credentials on a macOS runner.
  2. Downloads the pre-built `ARVIO-iOS-IPA` artifact from the specified GitHub Actions run.
  3. Uploads the downloaded `.ipa` package to Apple TestFlight.

---

## 2. Release Checks and Smoke Testing

Before deploying updates or releasing production APKs, run the following verification steps:

### A. Local Compilations
```bash
./gradlew :app:compilePlayDebugKotlin
./gradlew :app:assemblePlayRelease
./gradlew :app:assembleSideloadRelease
```

### B. Manual Smoke Testing Checklist
Before publishing:
- [ ] **Startup:** Verify clean app launch on Android TV and mobile layouts.
- [ ] **Profile Switching:** Confirm settings and lists isolate correctly between profiles.
- [ ] **IPTV Load:** Test category lists reordering and EPG backfill checks.
- [ ] **Playback:** Open streams, select internal/external subtitle tracks, adjust audio sync, and verify frame-rate switches.
- [ ] **Addon Sync:** Add and delete community streaming addons. Verify persistence across restarts.
- [ ] **Cloud Sync:** Sync changes from a mobile device and confirm they are immediately reflected on the TV emulator via WebSockets.

---

## 3. Netlify Auth Site Deployment

The web auth pairing portal resides in the [netlify-auth-site/](../netlify-auth-site/) directory.

- **Files:**
  - `index.html`: Portal landing and pairing forms.
  - `delete-account.html`: Required account data deletion endpoint.
  - `netlify.toml`: Deployment and routing settings.
- **Deployment:**
  - Connect your GitHub fork to **Netlify**.
  - Configure the build base directory to `netlify-auth-site`.
  - Set the build command to *empty* (this is a static HTML site).
  - Publish the site. Netlify will auto-deploy updates pushed to `main`.

---

## 📖 Documentation Navigation

- [README.md](../README.md) - Main repository overview.
- [CONTRIBUTING.md](../CONTRIBUTING.md) - Guidelines for contributing code.
- [CODE_OF_CONDUCT.md](../CODE_OF_CONDUCT.md) - Behavior and community guidelines.
- [docs/architecture.md](./architecture.md) - System architecture and dependency dataflows.
- [docs/setup.md](./setup.md) - Environment installation checklist.
- [docs/development.md](./development.md) - Development commands and workflows.
- [docs/configuration.md](./docs/configuration.md) - App parameters and credentials reference.
- [docs/api.md](./api.md) - Edge Function API proxies documentation.
- [docs/deployment.md](./deployment.md) - CI/CD pipeline automation and TestFlight uploads (this document).
- [docs/troubleshooting.md](./troubleshooting.md) - Common problems and resolution guide.
- [docs/ios-testflight.md](./ios-testflight.md) - iOS App Store/TestFlight packaging instructions.
