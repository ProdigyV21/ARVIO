# Privacy Policy

**Last updated: May 11, 2026**

## Overview

ARVIO ("the App") is an Android media hub for TV, phone, and tablet form factors. ARVIO does not host, sell, or distribute movies, series, live TV channels, playlists, streams, or other third-party media. This policy explains what app data is stored locally, what can be synced when you sign in, and which third-party services may receive data when you use optional features.

## Data We Collect Or Store

### Local App Data

ARVIO stores app data on your device so the app can remember your setup and playback state. This may include:

- Profiles, profile names, profile PIN settings, and avatar choices
- App language, interface, subtitle, audio, playback, catalog, addon, and IPTV settings
- IPTV playlist configuration, category order/visibility, favorites, recently watched channels, and EPG-related state
- Watch history, watch progress, watchlist, and continue-watching state
- Home server connection details that you add, such as server label, server URL, server type, username, access token, library identifiers, and source-matching metadata

### ARVIO Cloud Sync

If you sign in to ARVIO Cloud, selected app data is synced with Supabase so your profiles and settings can follow you across devices. Synced data may include the local app data listed above, plus your cloud account identifier/email and custom profile avatar images uploaded from your device.

ARVIO Cloud sync is optional. If you do not sign in, this data stays on your device unless you connect another third-party service yourself.

### Trakt

If you connect Trakt.tv, ARVIO can read and update Trakt watch history, progress, watchlist, and related authorization tokens for the active profile.

### Crash Diagnostics

Release builds may send crash stack traces, app version, device model, Android version, and limited app state to Sentry or Firebase Crashlytics. ARVIO does not intentionally send stream URLs, account tokens, screenshots, or view hierarchy data in crash reports.

## How We Use Data

We use app data to:

- Sync profiles, settings, catalog configuration, watch history, watchlist, and continue-watching state between your devices when ARVIO Cloud is enabled
- Restore your subtitle, audio, playback, IPTV, and catalog preferences
- Match your selected movie or episode with user-configured sources and home servers
- Diagnose crashes and stability issues

We do not sell your personal information.

## Third-Party Services

The App may use these services depending on the features you enable:

| Service | Purpose | Privacy Policy |
|---------|---------|----------------|
| Supabase / ARVIO Cloud | Authentication, cloud sync, device login, profile avatar storage, and API proxy functions | [supabase.com/privacy](https://supabase.com/privacy) |
| Google Sign-In | Optional sign-in provider | [policies.google.com/privacy](https://policies.google.com/privacy) |
| TMDB | Movie/TV metadata and images | [themoviedb.org/privacy-policy](https://www.themoviedb.org/privacy-policy) |
| Trakt.tv | Optional watch history, progress, and watchlist sync | [trakt.tv/privacy](https://trakt.tv/privacy) |
| Sentry | Crash reporting and diagnostics, when enabled | [sentry.io/privacy](https://sentry.io/privacy/) |
| Firebase Crashlytics | Crash reporting and diagnostics, when enabled | [firebase.google.com/support/privacy](https://firebase.google.com/support/privacy) |
| User-configured services | Addons, IPTV providers, home servers, and URLs that you add yourself | Governed by the provider you configure |

## Security And Network Notes

ARVIO uses HTTPS for ARVIO Cloud and supported third-party APIs. The app may also allow HTTP URLs for local home servers or IPTV providers because many private home-network servers do not use HTTPS. HTTP connections are not encrypted, so use HTTPS where possible and only add services you trust.

## Data Retention And Deletion

Local data remains on your device until you remove it in the app, clear Android app data, or uninstall the app.

ARVIO Cloud data remains in Supabase until you delete it or request deletion. You can request cloud account and synced data deletion at [auth.arvio.tv/delete-account](https://auth.arvio.tv/delete-account). You can also disconnect Trakt in app settings or revoke ARVIO access from your Trakt account.

## Children's Privacy

The App is not directed at children under 13. We do not knowingly collect data from children.

## Changes To This Policy

We may update this policy occasionally. Changes will be posted here with an updated date.

## Contact

For privacy questions, open an issue on GitHub or use the account deletion page above.

---

*ARVIO is an open-source project licensed under Apache 2.0.*
