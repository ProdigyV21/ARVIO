# ARVIO Web — Unraid Community Applications templates

This is the dedicated template feed for the independent ARVIO browser client.
Submit **https://github.com/ProdigyV21/ARVIO-Unraid** to Community Applications,
not the Android application source repository. Keeping this feed template-only
prevents Android resources and manifests from being mistaken for app templates.

**Current status: a Telegram-free, Docker-tested preview is published and was
successfully pulled without a GitHub login on 2 October 2026.** Real Unraid
installation testing is still pending. This is not yet an approved Community
Applications listing.

- Release source: [`fc839f7511a1cf009fc06c05198816ee8e421649`](https://github.com/ProdigyV21/ARVIO/tree/fc839f7511a1cf009fc06c05198816ee8e421649).
- Immutable image tag: `ghcr.io/prodigyv21/arvio-web:sha-fc839f7511a1`.
- Verified image digest: `sha256:16deae9da977f07102fab864cf218d8eac286d0467d7f38e70e79c6d4702e3e1`.
- [Successful release checks](https://github.com/ProdigyV21/ARVIO/actions/runs/37051815727):
  803 web regressions, type checking, source/notice audits, actual browser
  AC-3/E-AC-3/DTS/AAC round trips, and container/UI/restart checks. Provider
  account flows used dummy credentials/offline fixtures, not live user accounts.

The `unraid-preview` tag can move to a future tested preview. Use the immutable
tag or digest when you need this exact release.

| Application | Image | Template |
| --- | --- | --- |
| ARVIO-Web (preview) | `ghcr.io/prodigyv21/arvio-web:unraid-preview` | [arvio-web.xml](templates/arvio-web.xml) |

The application source, Dockerfile, build workflow and container tests are in
[ProdigyV21/ARVIO](https://github.com/ProdigyV21/ARVIO). This feed's metadata uses
Apache-2.0; dependencies in the image retain their own licenses. A valid XML
file alone does not prove that an image is published, installable or accepted
by Community Applications.

## Before installation or submission

The release above passed its automated source/notice closure checks and is
publicly pullable. These checks are not proof of real Unraid compatibility or
a blanket legal/patent clearance. Review the limitations in the
[application's Unraid preview guide](https://github.com/ProdigyV21/ARVIO/blob/fc839f7511a1cf009fc06c05198816ee8e421649/unraid/README.md).
Do not substitute another publisher's image or treat a passing metadata scan
as Community Applications approval.

## Installation

ARVIO-Web is a browser media hub, **not** a media server, transcoder or Android
APK. Plex, Jellyfin and Emby remain supported; **Telegram is not available in
this preview**. No media, subscriptions or ARVIO Cloud sync are included. You supply your
own authorized sources and a TMDB API v3 key. Optional provider application
credentials belong to you; the image contains no ARVIO owner's integration keys.

After Community Applications approves the listing, search for **ARVIO-Web**.
For maintainer testing before approval, use the [raw template](https://raw.githubusercontent.com/ProdigyV21/ARVIO-Unraid/main/templates/arvio-web.xml)
in Unraid's Docker template editor.

1. Keep **bridge** networking and **unprivileged** mode. Host port **8133** maps
   to container port **3000**. No appdata, media-share or Docker-socket mount is
   needed: profiles/settings/history stay in each browser's site data.
2. Supply your own 32-character **TMDB API v3 key**, not a read-access bearer token.
3. Leave **Allow private home-server proxy** false unless the installation is
   private and protected. Read the full [security/setup preview guide](https://github.com/ProdigyV21/ARVIO/blob/codex/unraid-distribution/unraid/README.md)
   before enabling access to LAN Plex, Jellyfin or Emby addresses.
4. Open WebUI, create/select a local profile and configure sources in Settings.
   A trusted HTTPS origin is needed for full browser capabilities.
5. Restart after changing optional provider application credentials.

There is no built-in server authentication. **Never expose the HTTP port or
unrestricted private proxy to the internet.** Use a private network/VPN or an
authenticated HTTPS reverse proxy protecting **all routes, including `/api/*`**.
Masking an Unraid field does not encrypt saved templates or container variables.

Changing the browser/origin, clearing site data or using another device can
show a fresh profile. A container backup is not a browser profile backup.
Playback depends on provider access, CORS, codecs, DRM, browser and device;
installing on Unraid does not add transcoding or grant media access.

## Support and feed maintenance

Report bugs at [ARVIO issues](https://github.com/ProdigyV21/ARVIO/issues), including
Unraid version, image tag/digest, browser and a redacted error. Do not share
keys, tokens or private server URLs. This is an ARVIO-maintained template, not
an endorsement by Unraid, Plex, Jellyfin or Emby.

The maintainer exports these files from the app source using
`scripts/export-unraid-feed.ps1` into a new empty directory and reviews them
before committing to this feed. Keep the root `ca_profile.xml`, a non-empty
`Profile`, root `LICENSE` and exactly one `templates/arvio-web.xml` with its
canonical `TemplateURL`. Do not copy Android XML or unrelated application code.
After each metadata update run **Validate**, then **Scan** in the
[submission workspace](https://ca.unraid.net/submit/new).
