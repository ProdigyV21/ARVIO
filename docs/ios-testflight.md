# iOS TestFlight

This repo contains an additive iOS SwiftUI shell under `iosApp/`.

The Android TV APK remains in `app/` and is not coupled to the iOS target.

## Required GitHub Actions secrets

- `APP_STORE_CONNECT_KEY_ID`
- `APP_STORE_CONNECT_ISSUER_ID`
- `APP_STORE_CONNECT_PRIVATE_KEY`
- `APPLE_TEAM_ID`
- `IOS_BUNDLE_ID`

The current workflow uses GitHub's macOS runner and Xcode automatic signing with the App Store Connect API key.
If Apple refuses automatic signing in CI, the workflow will need manual distribution certificate and provisioning profile secrets.

## Running

Use **Actions > iOS TestFlight > Run workflow**.

The workflow:

1. Installs XcodeGen.
2. Generates `iosApp/ARVIO.xcodeproj`.
3. Archives the app with automatic signing.
4. Exports an App Store IPA.
5. Uploads the IPA to TestFlight.
