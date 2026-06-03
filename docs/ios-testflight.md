# iOS TestFlight

This repo contains an additive iOS SwiftUI shell under `iosApp/`.

The Android TV APK remains in `app/` and is not coupled to the iOS target.

## Required GitHub Actions secrets

- `APP_STORE_CONNECT_KEY_ID`
- `APP_STORE_CONNECT_ISSUER_ID`
- `APP_STORE_CONNECT_PRIVATE_KEY`
- `APPLE_TEAM_ID`
- `IOS_BUNDLE_ID`

The workflow runs on GitHub's macOS runner. It dynamically generates an App Store distribution certificate and provisioning profile on the fly via the App Store Connect API, installs them to the runner's keychain, and signs the archive manually using those assets.

## Running

Use **Actions > iOS TestFlight > Run workflow**.

The workflow:

1. Installs XcodeGen.
2. Generates `iosApp/ARVIO.xcodeproj`.
3. Archives the app using the dynamically generated manual signing assets.
4. Exports an App Store IPA.
5. Uploads the IPA to TestFlight.

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
- [docs/troubleshooting.md](./troubleshooting.md) - Common problems and resolution guide.
- [docs/ios-testflight.md](./ios-testflight.md) - iOS App Store/TestFlight packaging instructions (this document).
