# ARVIO Web on Unraid

This package installs the **independent browser client**, not the Android APK,
a media server or a transcoder. You supply your own authorized media sources
and API credentials. No ARVIO membership is required. The optional managed
service at web.arvio.tv is separate.

## Preview status

The template targets `ghcr.io/prodigyv21/arvio-web:unraid-preview` for
Linux x86-64 (`linux/amd64`). This is a preview, not a claim of Community Apps
acceptance. Before submitting, verify that the image is public and anonymously
pullable and that the template feed exists on its repository's default branch.
A draft PR or a Dockerfile alone does not meet those gates.

**Publication is a separate release step.** See the
[template feed's release status](https://github.com/ProdigyV21/ARVIO-Unraid)
for the currently verified image digest; do not infer availability from this
recipe or a draft PR. The maintainer selected a preview without
Telegram: its browser SDK and AES dependency are not included in the runtime.
Plex, Jellyfin, Emby and the rebuilt audio codecs remain available. Original
ARVIO source remains Apache-2.0; Android and the hosted service are unchanged.
The container recipe
packages exact source archives, dependency notices and rebuilt codec relinking
materials. Publication is gated on successful source-bundle verification,
full container QA and the release checks below. Do not mistake this recipe or
source work for proof of a released image or an approved Community Apps listing.

## Install

After Community Apps approves the listing, search for **ARVIO-Web** in Apps.
Until then, the maintainer can test the XML from `templates/arvio-web.xml` using
Unraid's Docker template editor. Do not install a template from an unknown source.

1. Supply your own **TMDB API v3 key** from
   [TMDB's API settings](https://www.themoviedb.org/settings/api). This is the
   32-character hexadecimal v3 key, not the read-access bearer token.
2. Keep bridge networking and unprivileged mode. The default host port is
   **8133**, mapped to container port **3000**; choose another unused host port
   if necessary. No media share, Docker socket or host directory mount is needed.
3. Keep **Allow private home-server proxy** false initially. Read the security
   section below before enabling it.
4. Apply the template and open WebUI. Create/select a local profile and configure
   your sources in Settings. Use a trusted HTTPS URL for full browser support.
5. Optional Trakt and Simkl application credentials are advanced fields.
   Use applications you own. Restart after changing them; no image rebuild is
   required. A generic image never contains the ARVIO owner's API keys.
   For new SIMKL sign-ins, register an AUTH V2 **Mobile, desktop & browser** app
   and set `SIMKL_V2_CLIENT_ID`; no SIMKL client secret is needed. Legacy V1
   fields only retain existing V1 connections. Custom-list contents require
   SIMKL PRO/VIP, independently of ARVIO.

## Important browser and storage limitations

- Profiles, settings, history and source credentials are stored in **that
  browser's site data**. They are not in an Unraid appdata volume. Changing the
  URL/origin, clearing site data or using another browser can show a fresh profile.
- This independent client does not provide ARVIO Cloud account/profile sync.
  Trakt/Simkl can synchronize their own supported watch/list data when configured;
  they are not a backup of the entire ARVIO installation.
- Keep a backup/export of browser settings where supported. Backing up the
  container filesystem does not back up browser profiles.
- Playback depends on source access, CORS, codec, DRM, browser and device support.
  Installing on Unraid does not add server-side transcoding or grant media access.
- A plain HTTP LAN-IP address is not a secure browser context. Some media,
  storage and web-app capabilities require trusted HTTPS; do not bypass browser
  certificate warnings.

## Security and private Jellyfin/Plex/Emby servers

There is no built-in authentication protecting this independent web server.
Do not forward its HTTP port to the internet. Use a VPN/private trusted network
or an authenticated HTTPS reverse proxy that protects **every path**, including
`/api/*`. Authentication on the home page alone is not sufficient.

Server-side access to private home-server addresses is blocked by default.
If you need the API proxy to reach a LAN Jellyfin/Plex/Emby server, first protect
the installation as above, then explicitly set `ALLOW_PRIVATE_PROXY=true`.
Enabling this on an exposed installation can let strangers make requests into
your private network. A provider login inside the ARVIO UI does not authenticate
incoming requests to the web server itself.

The TMDB key and OAuth secrets stay server-side. Optional public application IDs,
and the resolver URL are delivered to your
browser by a no-store self-host configuration endpoint. Do not put passwords,
user access tokens, session strings or URL-embedded credentials in those fields.
Masking a field in Unraid does not encrypt the Docker template or environment.
Redact keys and tokens from support screenshots, logs and exported templates.

## Updates and support

Push and pull-request runs **build and test only; they never publish an image**.
Publication requires an explicit `workflow_dispatch` run with
`publish_preview=true`, after the maintainer has closed the source/licensing gate.
The workflow then publishes the exact tested image as `unraid-preview` and as an
commit-named `sha-<12-character-commit>` tag; it does not publish `latest`. Tags
are mutable, including a commit tag if the same source is built again; only an
image digest identifies the exact image. These
workflow rules describe a release mechanism, not proof that any tag is already
public. Verify anonymous pulls separately. Pin a tested image digest when
reproducibility matters. Updating/recreating this stateless container should not
clear data in the browser, but changing the origin does.

Report problems at [ARVIO GitHub issues](https://github.com/ProdigyV21/ARVIO/issues).
Include Unraid version, image tag/digest, browser, whether HTTPS is used and a
redacted error. Do not upload credentials or private server addresses.

## Maintainer checks before submission

1. Run `pwsh -File scripts/check-unraid.ps1`.
   Run `pwsh -File scripts/test-unraid-contract.ps1` for adversarial metadata
   regressions. These are project-specific checks, not Unraid's own scanner.
2. Commit all public build inputs, then run
   `node scripts/test-unraid-source.cjs` and
   `node scripts/prepare-unraid-source.cjs <full-checked-out-commit-SHA>`.
   The bundle uses only committed public inputs; dirty/untracked build inputs
   are rejected. Runtime credentials are never input to this archive.
   Build without credentials: `docker build --platform linux/amd64 -t arvio-web:unraid-test web`.
3. Run `node scripts/check-unraid-container.cjs arvio-web:unraid-test`.
4. Close the source/redistribution-license review below **before** any public
   image publication. Then explicitly authorize a publishing workflow run.
5. Confirm the registry package is public and an unauthenticated pull succeeds.
6. Test a clean install, WebUI port mapping, restart/update and private home-server
   access on a real Unraid host. Docker Desktop/CI smoke tests are not Unraid tests.
7. Export a **dedicated template-only feed** into a new empty directory:
   `pwsh -File scripts/export-unraid-feed.ps1 -OutputDirectory /path/to/new-empty-feed`.
   Review and publish only the exported root `LICENSE`, `README.md`,
   `ca_profile.xml` and `templates/arvio-web.xml` to
   [ProdigyV21/ARVIO-Unraid](https://github.com/ProdigyV21/ARVIO-Unraid)'s default
   branch. Do not submit the ARVIO Android/source repository: its ordinary
   resource XML is not an Unraid template and causes unrelated scan warnings.
   In the
   [Unraid submission workspace](https://ca.unraid.net/submit/new), sign in, add
   **https://github.com/ProdigyV21/ARVIO-Unraid**, run **Validate**, then **Scan**,
   and review the preview. The export validator checks for exactly one app XML,
   one non-empty repository profile and no unrelated XML. It is not Unraid's
   scanner and does not prove public image availability.
8. The owner must approve the final submission and associated terms. Record the
   actual submission result; do not label an unsubmitted package as listed.

### Distribution source and licences

The maintainer selected a preview **without Telegram**. Its SDK and GPL AES
runtime dependency are excluded at compile time and checked against the built
server/client output. Saved Telegram sources cannot play or download in this
preview and receive a clear unavailable message; existing user data is not
deleted. Other integrations retain their normal behavior. Original component
notices and source licences are retained. Settings → About & Credits and the
profile screen link to `/distribution-sources/index.html`, which contains source,
licence notices and a SHA-256 inventory. Source archives ship with the image,
rather than depending solely on mutable URLs or short-lived CI artifacts.

Mediabunny core/wrapper source is pinned; the
three opaque upstream codec packages are completely replaced with builds from
FFmpeg 8.1.3 and Emscripten 4.0.23. GPL/nonfree FFmpeg options are disabled.
FFmpeg configurations, bridge objects, static libraries and relinking scripts
accompany the binaries. Node, actual Debian package sources, native libvips
sources/build recipes, installed notices and public-asset hashes are packaged.
The Debian runner keeps only glibc Sharp/libvips binaries; unused optional musl
variants are removed before tracing and rejected by the output audit.

Packaging checks must actually run and pass before release. These technical
records are not a legal opinion, a codec-patent clearance or a guarantee of
individual third-party artwork/trademark ownership. Original dependency licence
conditions still apply. The original SDK remains a build-only type dependency,
so it may appear in the broad build-time notices inventory. Experimental AES
and npm source-audit helpers in the repository are not active release outputs.
The narrowed preview does not rely on a browser-incompatible replacement or
an unverified downgrade.

### Isolated image dependencies

The normal `web/package.json` and lock retain hosted functionality. The scoped
`web/distribution-sources/unraid` manifest moves Telegram to a builder-only
development dependency without changing any locked package versions. To
regenerate it after a dependency update, run `node scripts/unraid-package.mjs
--prepare` from `web`, then run `npm install --package-lock-only --ignore-scripts`
from `web/distribution-sources/unraid`, and verify with
`node scripts/unraid-package.mjs` from `web`.

Official contracts: [submission help](https://ca.unraid.net/submit/help),
[Docker XML](https://ca.unraid.net/submit/help/repository-xml),
[repository profile](https://ca.unraid.net/submit/help/repository-info-xml).
