# ARVIO Web

ARVIO's browser media hub for Windows, macOS, Linux, iPhone, iPad and TV browsers.
The webapp source is included in this repository. **You can self-host it without
an ARVIO Premium subscription and use your own API credentials.** The paid
[hosted service](https://web.arvio.tv) is optional; self-hosting does not grant
access to that service or include subscriptions/content from third parties.

## Quick Start: Node.js

Install Node.js 22.19+ (22 LTS recommended) and Git, then:

```sh
git clone https://github.com/ProdigyV21/ARVIO.git
cd ARVIO/web
npm ci
npm run setup:selfhost
```

Edit `.env.local` and set your **TMDB API v3 key** as `TMDB_API_KEY`.
Trakt, Simkl and Telegram are optional. Then:

```sh
npm run check:selfhost
npm run build
npm run start -- --hostname 127.0.0.1
```

Open **http://localhost:3000**, choose/create a local profile and add your sources
in Settings. There is no ARVIO sign-in, trial or subscription step. The setup
command never overwrites an existing `.env.local`. For development, use
`npm run dev -- --hostname 127.0.0.1` instead of build/start.

## Docker Compose

Install Docker with Compose. From `ARVIO/web`, create `.env.local` using
`npm run setup:selfhost`, or copy `.env.selfhost.example` to `.env.local`
if Node is not installed on the host. Add your TMDB key, then run:

```sh
docker compose --env-file .env.local up -d --build
docker compose --env-file .env.local ps
```

Open **http://localhost:3000**. This builds the same webapp as the Node setup,
runs as a non-root user, and binds only to loopback by default. Your TMDB key
and OAuth secrets are runtime variables, not Docker build arguments.
There is no prebuilt official Docker image required.

Update from the repository root:

```sh
git pull --ff-only
cd web
docker compose --env-file .env.local up -d --build
```

For Node deployments, update the checkout, run `npm ci` and `npm run build`,
then restart the server. Neither method updates itself automatically.
See [the full self-hosting guide](docs/SELF-HOSTING.md) before exposing a server
on your network or the internet.

## Personal API Credentials

| Integration | Where to configure | Required? |
| --- | --- | --- |
| TMDB | `TMDB_API_KEY` in `.env.local`; use the v3 API key, not the bearer token | For built-in movie/show metadata |
| Trakt | `TRAKT_CLIENT_ID` and `TRAKT_CLIENT_SECRET`, then connect in Settings | Optional |
| Simkl | `SIMKL_CLIENT_ID`; `SIMKL_CLIENT_SECRET` for token exchange, then connect in Settings | Optional; PIN login uses the client ID |
| MDBList | Enter your own key in Settings > Accounts | Optional |
| Telegram | `NEXT_PUBLIC_TELEGRAM_API_ID` and `NEXT_PUBLIC_TELEGRAM_API_HASH`, then connect your account | Optional |
| Plex / Emby / Jellyfin | Connect your server in Settings | Optional |
| Addons / live TV / debrid | Configure your own providers in Settings or their addon configuration | Optional |

Credential links, secure deployment, browser limitations, backup considerations
and troubleshooting are in [SELF-HOSTING.md](docs/SELF-HOSTING.md).

## What Is Independent?

With `NEXT_PUBLIC_SELF_HOSTED=true`, profiles/settings/history stay in that
browser, TMDB/Trakt/Simkl API routes use your credentials directly, and ARVIO
Cloud, membership requests and Premium funnel reporting are disabled. A
separately configured resolver remains optional. Built-in catalogs and your
configured integrations still contact their respective third-party services.

**Local storage is not cross-device cloud sync.** Trakt and Simkl can sync their
own watched/watchlist data when connected, but they do not sync ARVIO profiles,
IPTV settings, addons or the complete app state. Clearing browser site data
removes local data. The Docker filesystem is not a backup of browser profiles.

## Maintainers: Official Hosted Service

Production `web.arvio.tv` uses the same source with self-hosted mode disabled
and its existing Cloud and Premium configuration. Do not apply the self-hosted
environment example to the official site.

The deployment workflow is `../.github/workflows/deploy-web.yml`, with
`web/` as the working directory. `netlify.toml` uses `npm run build`,
publishes `.next`, and uses Netlify's Next.js runtime. A static file host alone
is insufficient: `/api/*` routes require a Node/Next-compatible server.

## Verification

```sh
npm test
npx tsc --noEmit
npm run build
```

Browser playback depends on the source, CORS, codecs, device and browser.
Self-hosting does not automatically make every Dolby Vision, DRM or torrent
source browser-playable, and this setup does not run a media transcoding server.
