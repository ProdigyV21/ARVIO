# Web stabilization implementation

Date: 2026-09-06. Implementation branch: `codex/webapp-stabilization`, based on
`e3ffc89fe`. This is a tested implementation pass, not certification of complete
Android parity or a record of a production deployment.

## Delivered

| Area | Changes |
| --- | --- |
| Home/artwork | Cached image loads no longer race a loading-state reset; failed artwork gets a fallback rather than an endless blank card. Tracker metadata hydration has bounded concurrency and stable identities instead of fabricated TMDB IDs. |
| Library | Dedicated Trakt and Simkl sources, contextual desktop sidebar and mobile list selector; Simkl Plan to Watch, Watching, Completed, On Hold and Dropped; search, sorting, poster/landscape preference, cached content, scoped errors and retry. Home-server caches are account/profile scoped and pagination errors preserve loaded cards. |
| Live TV | Channels, categories and guide rows are virtualized. Provider-scoped grouping/visibility, complete playlist loading, persisted favorite ordering, working hide/restore and move controls, collapsible groups, stable keyboard focus, per-profile last-played selection, and debounced archive requests while browsing. |
| EPG | Channel publication does not wait for XMLTV. Shared EPG IDs populate sibling channels within the same provider. Visible guide requests are coalesced, with a negative-cache retry window. Guide window navigates 48 hours backwards/forwards and returns to a rolling Now window. Available past programmes can invoke catch-up; future programmes do not incorrectly launch live TV. This does not manufacture missing provider archive data. |
| Sources/player | Late source resolutions cannot reopen an obsolete title. Compatible sources have an explicit browser-play action. Next episode uses the common source-resolution path, including home servers, IPTV VOD and Telegram, and checks season/air-date boundaries. Countdown can be cancelled. Final progress is flushed on pause/close. Removed brightness-based source hopping on legitimate dark scenes. Fixed global keyboard shortcuts interfering with the focused seek slider and form controls. |
| Sync | Settings persist in an account/profile-scoped outbox before the network debounce; retries preserve unacknowledged changes. Acknowledged cloud cache is not overwritten by a failed push or stale in-flight read. Per-feature tracker read preferences are respected. Partial failures are surfaced rather than accepted as authoritative deletion/success. |
| Authentication | Server-side Cloud auth fallback when browser configuration lacks the app key; coalesced Cloud/Trakt renewal; Android-compatible profile PIN verification and locked-profile entry guards, including sign-in redirects. |
| Premium/security | Linking a different billing email requires an expiring verification code, rate limits and ownership checks. Proxy validates/pins public DNS addresses, checks redirects, strips unsafe headers, and limits response size and request duration. |
| UI | Existing OLED design retained. Neutral selection, visible focus, compact source rows, corrected overlapping player actions, responsive library controls, and reduced-motion support. |

## Verification

- Web regression suite: **21/21 passed**.
- Backend suite: **49/49 passed**, including five billing-email ownership tests.
- TypeScript and optimized Next.js 15.5.25 build passed.
- Production dependency audit: **0 reported vulnerabilities** at test time.
- Desktop browser used actual components with controlled data: **55,000 channels**,
  only **12 list rows / 15 guide rows mounted** in the tested viewport.
- Keyboard End reached channel 55,000; Home returned to the first row.
- Hiding a 100-channel group reduced All Channels to 54,900 and excluded the same
  channels from Favorites. Restoring returned the count to 55,000. Favorite movement
  was checked against the displayed order.
- Groups collapse/reopen, Guide/List switching, previous guide window and Now
  navigation were visually checked. The guide had no page-level horizontal overflow.
- Desktop tracker Library displayed 24 loaded cards. Simulating an outage retained
  all 24 cards and displayed an actionable error instead of an empty library.
- The 390-pixel mobile iframe showed the mobile tracker selector and a two-column
  poster grid. This is responsive browser testing, not physical iPhone/Safari testing.
- A real external CC0 MP4 decoded at **960x540**, played to its 5.055-second duration,
  paused/resumed, and sought to **1.000 seconds** with matching slider and rendered
  frame after the keyboard conflict was fixed. This is not 4K/remux or all-provider
  playback certification.
- Browser automation occasionally detached. Successful checks above were repeated
  in a fresh local tab; failed tool calls were not counted as successful app tests.

Local screenshots from this pass (controlled data, no account credentials):

- `audit/implementation-library-desktop.png`
- `audit/implementation-library-mobile.png`
- `audit/implementation-tv-guide.png`
- `audit/implementation-player.png`

## Run locally

From `web`, run `npm ci`, `npm test`, and `npm run build` with Node **22.19 or newer**
(verification used 22.20.0). The dependency on undici 8 requires this Node minimum.

For the controlled UI fixture, set `ARVIO_UI_FIXTURES=true` and run the development
server. Open `/dev/stabilization`, or `/dev/stabilization?mobile=1` for the narrow
iframe. Both development mode and the explicit flag are required. The route returns
404 in production, even if the fixture flag is mistakenly present.

## Release gates and remaining work

1. Deploy the backend verification-code flow together with the web paywall change
   (backend first). Verify a real delivery to an authorized test inbox, successful
   ownership linking, expiry and cancellation. No real membership or payment was
   altered during this implementation.
2. Verify production server environment: `APP_ANON_KEY` (or its existing public
   alias), backend URL, Trakt proxy/client configuration and mail configuration.
   Confirm the previously failing production Trakt route, an OAuth renewal, and
   a private library read. Local passing tests cannot repair missing hosted secrets.
3. Test two real devices editing concurrently, going offline and reconnecting.
   Durable retry currently covers settings, not every history/progress/addon write.
   Existing focus/visibility refresh is not a real-time cross-device guarantee.
4. Introduce a worker/IndexedDB pipeline for very large XMLTV/catalog parsing and a
   shared provider request scheduler. The 55,000-channel fixture proves bounded DOM
   rendering, not a five-second cold download/parse guarantee from a real provider.
5. Complete hosted proxy authorization and distributed rate/concurrency budgets.
   Current in-process throttling is defense in depth, not a complete anti-abuse or
   billing control. Mislabelled binary content needs additional handling.
6. Validate browser-capability negotiation and server-side transcoding with real
   Plex/Jellyfin/Emby accounts. Test HLS, MPEG-TS, long MP4, MKV/remux, subtitles,
   Safari/iOS backgrounding, and external-player returns. Browser codec, CORS,
   mixed-content and autoplay restrictions remain real platform limits.
7. Provider-order contracts, group PINs, all Stalker modes, full archive availability,
   live telemetry, and deploy Git-SHA/configuration gates still need broader work.

These remaining items are not marked complete and the physical TV was not used.
