# SIMKL AUTH V2 and custom list catalogues

ARVIO now uses SIMKL AUTH V2 for new Android/TV and browser sign-ins. Existing V1 connections remain usable for tracking during SIMKL's staged migration. Reconnect SIMKL once on each device to enable V2 custom lists; V1 grants cannot be converted into V2 grants.

## Registration and deployment

- Public V2 app: ARVIO, app ID `8269755`, mobile/desktop/browser type, homepage `https://arvio.tv`.
- Public client ID is included in `secrets.defaults.properties` and the hosted web configuration. It is an application identifier, not a credential. No client secret is needed.
- Native override: `SIMKL_V2_CLIENT_ID` in the local secrets configuration.
- Web override: `SIMKL_V2_CLIENT_ID` or `NEXT_PUBLIC_SIMKL_V2_CLIENT_ID`. Independent Docker installations read their own ID through the allowlisted runtime bootstrap; restart the container after changing it.
- Registered browser callback: `https://web.arvio.tv/auth/simkl/callback`. This release uses the device/PIN flow on every platform; it does not implement the browser authorization-code callback.

## Authentication and privacy

Device authorization uses PKCE S256, `media:read media:write`, form-encoded OAuth requests and the server's polling interval. Pending authorization and `slow_down` retain the session; profile switches invalidate it. Expiring access tokens refresh through one owner, with rotating refresh tokens persisted before another caller proceeds. Browser tabs additionally coordinate refresh with Web Locks. Tracking cache identity remains stable across refreshes.

V2 grants stay local to the device and profile: Android encrypts them with the existing secure-storage mechanism; the browser uses its existing local credential store. Cloud sync deliberately excludes V2 access/refresh grants, because sharing a rotating refresh token between devices breaks rotation. V1 credential compatibility is preserved. Disconnect removes the local connection; remote grants can also be revoked in SIMKL's Connected Apps settings.

## Lists

Settings → Add catalogue accepts real SIMKL URLs such as `https://simkl.com/5/list/14462/best-mindfucks-tv-shows/`. List IDs provide stable duplicate detection across URL variants. SIMKL catalogue types and IDs survive Cloud sync and native/web normalization.

Catalogue discovery searches the signed-in user's owned, followed and shared lists, plus featured official lists (the first 500 recently updated and first 500 popular lists, deduplicated). SIMKL currently has no global list-name search endpoint, and its official index exceeds its 10,000-entry paging ceiling. Use a direct URL for lists outside this discovery selection. Discovery uses index poster previews and does not fetch every list's contents.

Contents load through the official API, preserve the owner's order, paginate at up to 500 items per request, and resolve TMDB/IMDb/TVDB identities for ARVIO artwork and details. Native catalogue browsing continues beyond the initial rail through existing paging. In-memory list caches survive screen navigation; auto lists use a 24-hour cache and ordinary lists a 15-minute cache. Changed index timestamps invalidate ordinary lists. Lists exceeding the API's 10,000-item ceiling report the limitation instead of silently truncating contents.

SIMKL PRO/VIP is required to read custom-list contents. A free account's HTTP 200 `premium_only` body is an error, not an empty or successful catalogue. Private/inaccessible lists, expired credentials, invalid configuration and rate limits produce actionable errors. ARVIO Premium does not grant SIMKL PRO/VIP.

## Verification

Protocol tests cover PKCE, scope validation, polling, refresh ownership, legacy credentials, strict URL parsing, list order/pagination, featured-index bounds and SIMKL's error-body responses. The opt-in `SimklCatalogDeviceTest` uses an uncommitted device-local grant for live checks; credentials are never printed or included in Git. The live checks exercise V2 PIN authorization, encrypted grant reload, refresh, list search, catalogue persistence, duplicate rejection, real TMDB cards and SIMKL poster decoding. The phone viewport check exercises compact catalogue controls.

References: [V2 migration](https://api.simkl.org/guides/migrating-v1-to-v2), [custom lists](https://api.simkl.org/guides/custom-lists), [device authorization](https://api.simkl.org/api-reference/oauth2-device).
