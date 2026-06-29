# ARVIO Web ↔ Android Parity — Design

**Date:** 2026-06-07
**Status:** Phases 0–8 delivered (a few niche backends + full UI i18n explicitly deferred)
**Goal:** Make the browser app in `web/` reach feature + screen parity with the Android/TV app in `app/` — same screens, same settings, same catalogs, same backend — for TV and desktop browsers.

## Progress log

| Phase | Status | Notes |
|---|---|---|
| 0 — Foundation | ✅ Done | Monolith → components + `lib/store.tsx`; same Supabase/TMDB/Trakt backend wired via `.env.local`; Arctic design tokens. |
| 1 — Entry | ✅ Done | Login, Profile Selection (84 avatars + colors), profile switcher; profiles read/write the shared `account_sync_state` payload. |
| 2 — Home + Catalogs | ✅ Done | Hero + card title-treatment logos, hero metadata, hover-hero; MDBList id-extraction fixed (all rows resolve); lazy rails (eager 8 + scroll). |
| 3 — Details | ✅ Done | Season tabs + episode list, per-episode sources, reviews, cast, related; non-playable sources marked. |
| 4 — Player | ✅ Done | HLS playback, subtitle cue styling from settings, Trakt + Supabase scrobble, auto-play-next + manual next-episode. |
| 5 — Settings | ✅ Done | Sidebar + 12 sections; all web-meaningful options; Android-only ones disabled; clock format + OLED wired live. |
| 6 — Live TV | ✅ Done | M3U/EPG playlists, groups, favorites, now/next with times + live progress, browser HLS playback. |
| 7 — Extended backends | ✅ Mostly | **Home Server (Jellyfin/Emby) browse + direct play** done & verified vs demo.jellyfin.org (token or username/password; rows on home; direct stream). **Deferred (niche, need live services to verify):** Plex browse, anime mapping (Jikan/Kitsu/ARM), skip-intro, Telegram. |
| 8 — Visual + i18n + QA | ✅ Mostly | Poster mode, accent themes, OLED, clock format, smooth scrolling, spoiler blur all applied live; full-screen QA across every screen, zero console errors. **Deferred:** 50-language *UI-chrome* translation (TMDB *content* already respects the language setting); fine-grained pixel matching. |

All shipped work verified: `tsc` clean, `next build` success, in-browser smoke + feature tests with zero console errors. Work lives on branch `web-android-parity`.

## Explicitly deferred (clearly scoped follow-ups)

- Plex library browse/play (Jellyfin/Emby done; Plex uses a different API).
- Anime metadata mapping (Jikan/Kitsu/ARM) and Skip-Intro timestamps.
- Telegram bot source/notifications (browser-feasible subset).
- TV-series direct play from Home Server (movies play; series needs episode browse).
- Full 50-language UI-string localization.

---

## 1. Decisions (locked)

| Axis | Decision | Implication |
|---|---|---|
| **UI fidelity** | Screen + feature parity (not pixel-perfect) | Every screen, setting, catalog and backend present and behaving the same; styled to closely match the ARVIO look; minor web-native polish (scrollbars, hover, focus) allowed. |
| **Form factor** | TV + desktop only | Mirror the Android **TV layout** (top nav, hero, rails, focus states) for desktop and TV browsers; basic responsive shrink for tablets. **No separate phone/bottom-nav UI.** |
| **Streams** | Match list, mark playable | Show the **same source list** as Android for UI parity, but visually mark/disable sources the browser can't play (torrent / magnet / infohash / Android-player-only). No new resolver/transcode infra in scope. |
| **Backend** | Same project, keys ready | Web points at the **identical** Supabase project, Edge Function proxy, and Trakt app that Android uses. Accounts / profiles / watch history are shared. Env keys are available to wire in. |

---

## 2. Current state vs target

**Web today** (`web/`): Next.js 15 + React 19 + TS. A single 1,093-line `app/page.tsx` holds every screen and all state, plus a small, well-shaped `lib/` backend layer.
- Screens present (approximate): Home (hero + rails), Search, Watchlist, Live TV (basic), Details (drawer), Player (overlay), Settings (grid of ~8 panels).
- Backends wired: Supabase auth, TMDB proxy, Trakt device-link, Stremio addons, IPTV/M3U.
- Styling: CSS-token system in `app/globals.css` approximating the dark hero/rails look.

**Android today** (`app/`, Kotlin + Jetpack Compose):
- **11+ screens**: Login, Profile Selection, Home, Search, Watchlist, Live TV, Details, Player, Collection Details, Settings, Telegram Settings.
- **14 settings sections**: Accounts, Profiles, Playback, Language & Audio, Subtitles, AI Subtitles, Appearance, Network, TV/IPTV, Home Server, Catalogs, Stremio (Addons), Plugins (flagged).
- **Catalogs**: preinstalled collections, Stremio addons, Trakt lists, MDBList, Plex/Jellyfin/Emby home servers.
- **Backends**: TMDB, Trakt, Supabase, Stremio, Skip-Intro, anime mapping (Jikan/Kitsu/ARM), Telegram, IPTV/Stalker, Home Server, YouTube trailers.

**The gap:** missing screens (Login, Profile Selection, Collection Details, Telegram Settings), a far smaller Settings surface, a thinner catalog engine, basic Live TV, and several backends (Home Server, anime, skip-intro, Telegram). The single-file architecture won't scale to close this.

---

## 3. Approach — A: Component-architecture rewrite, phased screen-by-screen

Keep and extend the existing `lib/` backend layer (already mirrors Android contracts). Refactor the monolithic `page.tsx` into a proper component + state-store structure, then build each screen up to Android parity, phase by phase. Android's `strings.xml` / `colors.xml` are the source of truth for labels and colors.

**Alternatives rejected:**
- **B — keep extending the single file:** fast to start, unmaintainable at 11 screens / 14 settings sections.
- **C — port Compose's ViewModel/screen graph 1:1:** most faithful internally, over-engineered for web, slow.

---

## 4. Architecture

- **Stack (unchanged):** Next.js 15 App Router, React 19, TypeScript, `hls.js`, `lucide-react`.
- **Backend layer (`lib/`):** reuse `tmdb`, `trakt`, `addons`, `iptv`, `cloud`, `auth`, `catalogs`, `http`, `player`, `storage`, `config`, `types`. **Add** `lib/homeserver`, `lib/anime`, `lib/skipintro`, `lib/telegram`. All point at the same Supabase project + Edge Function proxy as Android.
- **UI structure:** split the monolith into
  - `components/shell/` — `TopNav`, `SyncStrip`, `Toast`, `AppShell`
  - feature folders: `login/`, `profile/`, `home/`, `details/`, `collections/`, `player/`, `search/`, `watchlist/`, `livetv/`, `settings/`
- **State:** replace the single giant `useState` cluster with small stores (e.g. `HomeStore`, `DetailsStore`, `PlayerStore`, `SettingsStore`) echoing Android's ViewModels. Implementation detail (context vs. lightweight store lib) decided in Phase 0 planning.
- **Styling:** keep the CSS-token system in `globals.css`; re-derive tokens (colors, type scale, spacing) from Android `colors.xml` and screen measurements; per-feature CSS modules.
- **Navigation parity:** match Android's exact order/labels — **Home · Search · Watchlist · TV** + Settings gear + Profile switcher. Remove the dead `movies` / `series` sections. Details and Player remain overlays (matches the TV app's modal feel); top-level tabs stay section-state-driven.
- **Routing:** keep section-state for top-level tabs; evaluate adding shareable Next routes for `details`/`collections` during Phase 3 planning (non-blocking).

---

## 5. Parity roadmap

Each phase is its own spec → plan → build cycle. Build roughly in order; 0→1→2 are the backbone, after which 5/6/7 can overlap.

| Phase | Scope | Notes |
|---|---|---|
| **0 — Foundation** | Verify same-backend wiring (Supabase + proxy + Trakt). Refactor monolith → components + stores. Extract Android design tokens into `globals.css`. Lock device-mode = TV/desktop. | No new user features; unblocks everything. |
| **1 — Entry** | Login screen, Profile Selection (multi-profile, avatars), profile switcher in nav. | Honors "skip profile selection". |
| **2 — Home + Catalogs** | Hero behavior, Continue Watching (Supabase + Trakt), Trending, all dynamic rows in Android order. Catalog engine: preinstalled collections, addon catalogs, Trakt lists, MDBList. Card layouts (landscape/grid). | Core surface. |
| **3 — Details + Collections** | Full Details (synopsis, cast, seasons/episodes w/ per-season lazy load, reviews, related, trailer, watchlist/watched actions). Collection Details screen. Source selector (same list, mark web-playable). | |
| **4 — Player** | Subtitles (size/color/offset/secondary), auto-play next, Trakt + Supabase scrobble/progress, skip-intro hook. Apply subtitle styling settings. | |
| **5 — Settings** | Rebuild as Android sidebar + all 14 sections; every web-meaningful option. Telegram Settings sub-page. Android-only options (FFmpeg, frame-rate matching, etc.) shown but clearly disabled. | Largest phase. |
| **6 — Live TV** | Full IPTV: M3U / Xtream / Stalker, EPG guide grid, category management, favorites, now/next, mini-player. | Extends current basic version. |
| **7 — Extended backends** | Home Server (Plex/Jellyfin/Emby) browse + play; anime mapping (Jikan/Kitsu/ARM); skip-intro; Telegram source/notifications where browser-feasible. | The backend-heavy tail. |
| **8 — Visual parity + i18n + QA** | Side-by-side polish vs Android screenshots; accent themes / OLED black / clock format; reuse 50+ language strings from Android `strings.xml`; cross-browser (Chrome/Safari/Firefox + TV browsers) + remote/keyboard nav QA. | Final identical pass. |

---

## 6. Key constraints & risks

- **Browser playback limits:** torrent/magnet/infohash and Android-player-only formats can't play in-browser. We match the Android source list visually and mark/disable non-playable entries (per decision). Direct HTTP/HLS plays via `hls.js`; proxy headers via the existing `/api/proxy` route.
- **Android-only settings:** some Playback/Network options (FFmpeg decoder, frame-rate matching, volume boost, custom user agent at the OS level) have no browser equivalent. Shown for parity, disabled with a short "Android only" note.
- **D-pad/focus model:** the TV app relies on D-pad focus navigation. Web replicates focus-visible states and keyboard/remote arrow navigation rather than Android's exact focus engine.
- **Secrets handling:** TMDB/Trakt secrets stay server-side in Next API routes (never shipped to the browser), matching the Edge Function proxy pattern.
- **Plugins section:** Android's Plugins feature is build-flagged and likely out of scope for web parity unless explicitly requested.

---

## 7. Out of scope (initially)

- Standalone phone/bottom-nav layout (decision: TV + desktop only).
- Server-side stream resolver / transcoding / debrid (decision: mark playable only).
- Google Cast, FFmpeg software decoding, OS-level frame-rate matching (no browser equivalent).
- Android Plugins system (build-flagged).

---

## 8. Backend mapping (Android → Web)

| Concern | Android | Web equivalent |
|---|---|---|
| Auth / profiles / history | Supabase Kotlin SDK | Same Supabase project via `lib/auth` + `lib/cloud` |
| TMDB | Retrofit → Edge Function proxy | `/api/tmdb/[...path]` → same proxy; `lib/tmdb` |
| Trakt | Retrofit + OAuth/device | `/api/trakt/[...path]`; `lib/trakt` device-link |
| Stremio addons | `AddonRuntimeAggregator` | `lib/addons` (manifest + stream contracts) |
| IPTV / Stalker | `IptvRepository` | `lib/iptv` (extend for Xtream/Stalker + EPG) |
| Home Server | `HomeServerRepository` | **new** `lib/homeserver` |
| Anime mapping | Jikan / Kitsu / ARM | **new** `lib/anime` |
| Skip-Intro | `SkipIntroRepository` | **new** `lib/skipintro` |
| Telegram | `TelegramRepository` | **new** `lib/telegram` (browser-feasible subset) |
| Trailers | YouTube extractor | YouTube embed/link |
