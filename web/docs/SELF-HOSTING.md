# Self-Hosting ARVIO Web

Self-hosting uses the same webapp code, with your own infrastructure and API
credentials. No ARVIO subscription is required. The official hosted service
remains separate and keeps its membership checks.

## Choose a Setup

- **Node.js 22.19+**: follow [the quick start](../README.md). Suitable for a computer, server or a Node-capable hosting service.
- **Docker Compose**: use the included [compose.yaml](../compose.yaml) and [Dockerfile](../Dockerfile). Docker builds the app from this checkout.
- **Static hosting only**: not supported. Authentication exchanges and metadata/API proxies require the Next.js server routes, even though the home page is a client-rendered application.

Start from a fresh checkout and origin when possible. Do not reuse production
ARVIO credentials, a public paid-service deployment, or an existing browser
profile containing someone else's data.

## Configuration

`npm run setup:selfhost` copies `.env.selfhost.example` to `.env.local`, without
overwriting an existing file. Without Node installed, copy the file yourself:

```powershell
# Windows PowerShell, from web/
Copy-Item .env.selfhost.example .env.local
```

```sh
# Linux/macOS, from web/
cp -n .env.selfhost.example .env.local
```

Check that `.env.local` does not already contain important configuration before
copying it manually. Keep this file private; Git and Docker's build context
exclude it. Compose injects its values at runtime.

### Keys

1. **TMDB:** request your own API key in [TMDB API settings](https://www.themoviedb.org/settings/api). Put the **API key (v3 auth)** in `TMDB_API_KEY`. Do not paste the longer read-access bearer token. This supplies built-in catalogs and metadata; it does not supply video.
2. **Trakt:** register an application under [Trakt API applications](https://trakt.tv/oauth/applications). Configure your public client ID and server-only client secret. ARVIO uses the device-code flow; connect it under Settings > Accounts after building. Follow Trakt's application requirements and usage limits.
3. **Simkl:** register your application in [Simkl developer settings](https://simkl.com/settings/developer/). Set `SIMKL_CLIENT_ID` and, if using OAuth token exchange, `SIMKL_CLIENT_SECRET`. The in-app PIN connection uses the public client ID.
4. **MDBList:** paste your personal key in the app's Accounts settings, not a public environment variable.
5. **Telegram:** obtain application credentials at [my.telegram.org](https://my.telegram.org). The browser client embeds the application ID/hash. Never substitute a bot token, account password, one-time code or session string; account authorization happens separately in the app.

Addons, debrid, IPTV and home servers are configured through the app. Their own
fees, availability and rules still apply. Only connect content you are entitled
to access; ARVIO does not include a media subscription.

`npm run check:selfhost` checks configuration without printing credentials or
contacting providers. It does not verify account eligibility, valid credentials,
provider quotas or source playback. Missing optional integrations are reported
without preventing the basic setup.

### Build-Time Versus Runtime

- `NEXT_PUBLIC_SELF_HOSTED`, public client IDs, Telegram application credentials and resolver URL are **build-time** settings. Rebuild after changing them. A runtime-only environment change cannot rewrite an existing browser bundle.
- `TMDB_API_KEY`, `TRAKT_CLIENT_SECRET` and `SIMKL_CLIENT_SECRET` stay server-side. Restart/recreate the server after changing them. Never prefix these with `NEXT_PUBLIC_`.
- In Node builds, either canonical `TRAKT_CLIENT_ID` / `SIMKL_CLIENT_ID` or their public aliases work; prefer the canonical names in the provided template. Compose passes the canonical names to Docker at build time.
- The self-host Docker image always builds in independent mode. Do not use it for the official paid deployment.

The mode disables hosted Cloud and legacy Supabase configuration even if stale
keys remain in your environment. It also disables Premium analytics and the
membership gate. It does not alter access rights on `web.arvio.tv`.

## First Run and Data

Open `http://localhost:3000` and select/create a local profile. Add your home
servers, playlists or addons in Settings. Connect trackers if desired.

Profiles, settings, library state and tokens are stored in the browser's site
storage. This is **not** a central multi-user account server. Each browser/device
has its own data; clearing site data or changing the hostname/port creates an
empty local installation. Rebuilding the same origin does not intentionally
clear it. Preserve your `.env.local` and browser profile securely when backing up.
A Docker volume does not back up browser-local state.

Trakt and Simkl can synchronize the watched/watchlist information their APIs
support. Configure the read and write switches in Settings; both can receive
playback updates independently. They do not synchronize ARVIO profiles, addons,
IPTV favorites or all application preferences. Independently self-hosted ARVIO
Cloud is a separate backend deployment/migration project, not part of this
single-container setup. There is no silent fallback to the official service.

The current Watchlist action requires a connected Trakt, Simkl or MDBList
account. Home-server libraries and local playback progress do not require an
ARVIO account. Leaving optional tracker credentials blank does not supply a
replacement cloud watchlist service.

## Network and Security

The provided Compose file binds `127.0.0.1:3000`. Node's documented start command
also binds loopback. For access from other devices, place a reverse proxy with
HTTPS and authentication in front of it, or use a trusted VPN. Do not simply
publish port 3000 to the internet: local profiles/PINs are not server access
control, and unauthenticated visitors could use your API quota/proxy routes.

Use a stable origin such as `https://media.example.com` and a certificate your
devices trust. HTTPS (or localhost) is needed for secure browser media APIs;
plain HTTP on a LAN IP is not equivalent to localhost. A Next.js reverse proxy
must forward both page requests and `/api/*`, not just static files. Protect the
API paths with the same authentication as the pages.

LAN/private-address proxying is blocked by default. For your private Plex,
Emby or Jellyfin metadata API, you may set `ALLOW_PRIVATE_PROXY=true` **only on
a trusted, authenticated installation**. It deliberately expands what the
server can reach, so never enable it on an open public instance. This flag
does not enable media relay or disable browser mixed-content/CORS restrictions.

## Playback and Resolver Limits

- Media comes from your configured provider, not ARVIO's servers. The bundled production server is not an unrestricted video proxy/CDN or transcoder.
- Browser-supported direct video/HLS/DASH and the existing browser conversion paths remain available. Compatibility depends on CORS, source headers, codecs, DRM, device and browser. Some files still need provider-side conversion or an external player.
- Merely putting the app behind HTTPS does not allow an HTTPS page to play every HTTP/LAN source. Give the media server a trusted HTTPS endpoint and appropriate CORS settings, or use a suitable external player/provider path.
- `NEXT_PUBLIC_ARVIO_RESOLVER_URL` is blank by default. Only set it to your own API-compatible resolver if you operate one. Leaving it blank uses the app's bounded metadata proxy; it does not borrow ARVIO's hosted resolver.
- Media-proxy flags are not a supported production transcoding solution. Do not enable large media traffic through metered serverless hosting to work around a codec problem.

## Updates and Troubleshooting

From the repository root, run `git pull --ff-only`, then enter `web/` and rebuild.
With Node: `npm ci`, `npm run build`, then restart the process. With Docker:

```sh
docker compose --env-file .env.local up -d --build
docker compose --env-file .env.local ps
docker compose --env-file .env.local logs --tail=100 arvio
```

- **A subscription/login is required:** check the address is your installation, not `web.arvio.tv`, and rebuild with `NEXT_PUBLIC_SELF_HOSTED=true`. Don't just change a running container's environment.
- **Empty built-in catalogs:** run the checker and verify your TMDB v3 key. Inspect `/api/tmdb/*` responses for a provider error. Other integrations do not replace the TMDB key.
- **Tracker not configured:** add your own client credentials and rebuild. Reconnect the tracker if you changed its application/client ID.
- **LAN server blocked:** review the trusted-network instructions above. Do not disable safeguards on a public instance.
- **New browser has no profiles:** expected in local mode; it is not cross-device Cloud sync.
- **Black picture or unsupported source:** consult the source compatibility warning. Self-hosting does not add missing hardware decoders or universally support Dolby Vision/DRM.

Before sharing logs, remove API keys, account tokens, playlist passwords and
signed media URLs. Keep the original repository license and notices when
redistributing the software.

Implementation references: [Next.js self-hosting](https://nextjs.org/docs/app/guides/self-hosting),
[standalone output](https://nextjs.org/docs/app/api-reference/config/next-config-js/output),
and [Compose environment interpolation](https://docs.docker.com/compose/how-tos/environment-variables/variable-interpolation/).
