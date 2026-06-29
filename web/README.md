# ARVIO Web

Browser-native ARVIO app for iPad, desktop, and TV browsers. This app lives beside the Android app and mirrors the same product surface without touching Android-only code.

## What It Reuses

- Supabase account login, profile reads, account sync state, and watch history table shapes.
- Android's TMDB and Trakt proxy model through Next API routes.
- Stremio-style addon manifest and stream response contracts.
- IPTV M3U playlist parsing, channel groups, favorites, and browser HLS playback.
- ARVIO visual language: dark full-screen shell, left TV navigation, hero, rails, details drawer, source selector, player overlay, Live TV, addons, settings.

## Environment

Copy `.env.example` to `.env.local` and fill:

```bash
NEXT_PUBLIC_SUPABASE_URL=
NEXT_PUBLIC_SUPABASE_ANON_KEY=
NEXT_PUBLIC_TRAKT_CLIENT_ID=
TMDB_API_KEY=
TRAKT_CLIENT_SECRET=
```

When Supabase is configured, `/api/tmdb/*` and `/api/trakt/*` use the same Edge Function proxy pattern as Android. If Supabase is not configured, the TMDB route can use `TMDB_API_KEY`, and the Trakt route can use direct Trakt device auth with `TRAKT_CLIENT_SECRET`.

## Run

```bash
npm install
npm run dev
```

Production check:

```bash
npm run build
npm run start
```

## Browser Limits

The browser player can play direct HTTP/HLS URLs. Addon streams that only return torrents, info hashes, or Android-player-only formats still need a web resolver/transcode path before they can play in a browser.
