# Cloudstream / Phisher / StreamPlay web proof of concept

Local experiment, 9 November 2026. **No ARVIO UI changes and no production deployment.**

## What exists

An isolated JVM worker runs Phisher's pinned StreamPlay extension. A small Node bridge exposes the normal Stremio manifest, catalog, metadata and stream endpoints consumed by ARVIO. Normal ARVIO titles resolve by TMDB ID, including the requested season/episode. Native provider catalog items also have their own IDs and metadata.

The bridge returns original HTTPS media URLs. It has **no media relay endpoint**. No cookies, provider request headers, DRM keys or runtime interceptors are passed into ARVIO. A small bounded range request checks direct accessibility before a source is offered. This browser-only bridge now requires CORS for all returned candidates: a native MP4 host passed server/FFmpeg checks but returned HTTP 428 to the real browser. Alternate hosts are probed before quality variants of the same host. These checks do not guarantee browser codec support or that every HLS segment will load.

Both services bind to loopback. The worker requires a random bearer token. The public-facing local bridge checks Host and permitted local origins, rejects private-network probe targets, validates redirects and pins probe connections to validated addresses. It permits two concurrent resolutions, short session-local source caches and bounded probes. This is not a publicly exposed multi-user service.

## Actual test results

| Check | Result |
| --- | --- |
| Install through unchanged ARVIO web settings | Passed; installed screenshot saved |
| StreamPlay real search and metadata | Sintel found; real plot, artwork and metadata returned |
| ARVIO's actual `installAddon` and `getStreams` modules | Passed against live bridge; no mocked source payload |
| Sintel movie sources | Three direct sources; 4.25 seconds in final warm-runtime / uncached-title check |
| Sintel media verification | 1920×1080 H.264 + AAC, 889.09 seconds; decoded six seconds at both 0:00 and 2:00 without provider headers |
| Fallout S1E1 | Four direct candidates in 2.84 seconds; source resolution only, no episode playback claim |
| ARVIO video relay | Zero bytes; no relay route exists |
| Contract/security tests | Passed, including exact episode IDs, standard TMDB IDs, deduplication, concurrent-request coalescing, short cache, origin/Host checks, DRM/interceptor rejection and disabled media route |
| In-browser playback | **Passed** in the actual ARVIO webapp using the dedicated windowless Chrome headless shell: real catalog → details → Play → seek to 2:00; audio/video decoded, zero dropped frames and page errors |
| Final desktop browser run | 4.152 s from Play to 3 s played; 2.349 s from seek to 2 s played at the new position; 1282×546 video, zero dropped frames/page errors. Source speed/quality varies |
| Apple WebKit / iPad emulation | **Passed** on macOS WebKit 26.6 with native HLS and iPad landscape/touch settings. 10.128 s from Play to 3 s played; 6.211 s from seek to 2 s played at 2:00. Video advanced from 640×360 to 1280×720; independent presented-frame count advanced from 72 to 120 after seeking; one audio track; no page errors. This is not a physical iPad test |
| Browser media routing | Direct to upstream hosts; no ARVIO proxy/remux requests; bridge relay bytes 0. Some provider URLs are third-party relays, not original CDN URLs |
| Regression/type checks | 50 targeted bridge/addon/player/catalog tests passed after rebasing on main; TypeScript no-emit passed |
| Full deployment-platform suite | Linux: 1,507 passed, 11 skipped, one pre-existing Android YouTube-extraction safeguard failure. The same failure blocked main's deployment at 92359a33e before this branch. It has not been disabled |

Evidence is under `artifacts/cloudstream-poc` in the ARVIO checkout: `installed.png`, `integration-result.json`, `media-result.json`, `episode-result.json`. The historical Vidlink FFmpeg results above are **not** evidence of browser support; that source was excluded after the HTTP 428 browser finding. Files named `*.private.json` contain expiring media links; do not publish them. Browser state is local test-only state, not a real account.

## Compatibility limitations found

This is **partial StreamPlay support**, not blanket Phisher compatibility:

- The unmodified DEX-to-JVM conversion fails at extraction: `invokeMovieBox` exceeds the JVM's 65,535-byte method limit (75,051 bytes originally, 74,872 with register reuse). The local conversion replaces **only that method** with an explicit unsupported error so other extractors can load. The original downloaded `.cs3` stays unchanged. MovieBox is not supported by this POC.
- Minimal account/data shims permit movie/TV initialization. Anime tracking and Android UI APIs are not implemented.
- Browser launching is forcibly disabled in the worker. WebView-only extractors remain unsupported. The upstream runtime's Linux-specific headless option opened visible Chrome windows on Windows; do not re-enable it here.
- Desktop and macOS Apple WebKit native-HLS playback and seeking are verified. Physical iPad hardware and server-to-client IP-bound source behavior remain unverified. Same-host direct playback does not prove a hosted server can resolve URLs for clients on other IPs.
- ARVIO's current search screen uses its metadata search. This bridge adds sources for those titles; it does not redesign search or add a Cloudstream search UI. The native home catalog is verified. Catalogs now load directly first; add-on rows wait for manifests to hydrate before mapping native IDs. Explicit HLS/DASH transport hints survive normalization for extensionless media URLs. No visual changes.
- Conversion needed a 3 GB heap once; the successfully loaded worker was subsequently tested with a 768 MB maximum heap. These are limits, not production sizing measurements.
- Upstream runtime/plugin updates must be reviewed and retested. No third-party runtime or extension binaries are committed or redistributed here.

## Pins

- Runtime: [IstarVin/cloudstream-desktop](https://github.com/IstarVin/cloudstream-desktop), commit `25ea36ba05195e6712d96f0290f3ff49b88cf87a`.
- Phisher build: `6f9edcb8164b4a155e2a255b4d4aa9b67126c7ae`, StreamPlay v694.
- Original extension SHA-256: `a63bd63f0203db0c62ed0e85a1d3f039a9e98d7cdc9fa79f233af6cce825f805`.

## Reproduce locally

Requires PowerShell 7, Git, JDK 17, Node 22+, Android SDK platform stubs, and ARVIO web dependencies. Use separate runtime/data folders, never a real Cloudstream configuration directory.

1. Run `setup.ps1 -RuntimePath <isolated-runtime> -DataPath <isolated-data> -AndroidSdk <sdk>`. This clones/pins the runtime, verifies the extension and applies two tiny compatibility changes to that isolated runtime only. It does not launch anything.
2. Set `ARVIO_CS_WORKER_TOKEN` from `<isolated-data>/worker-token.txt` in the current process, then run `node tools/cloudstream-poc/bridge.mjs`. Do not print or commit the token.
3. In another terminal, run `run-worker.ps1` with the same runtime/data paths. Use `-Heap 3g` for the first conversion; subsequent starts can use the default `768m`. Fresh conversion is reloaded with the explicit MovieBox exclusion automatically.
4. Run the local ARVIO webapp on `127.0.0.1:8876` with `NEXT_PUBLIC_SELF_HOSTED=true`, `NEXT_PUBLIC_ARVIO_RESOLVER_URL` empty, and a separate build directory. Install `http://127.0.0.1:8950/manifest.json` through existing Addons settings in an isolated local profile. Do not sync this loopback add-on to a real cloud account.
5. `node --test tools/cloudstream-poc/bridge.test.mjs` checks the bridge contract without browsers. `node tools/cloudstream-poc/integration-test.cjs` checks the live worker through ARVIO's source code. Set `ARVIO_FFMPEG_DIR` and run `media-check.cjs` for the two direct Sintel decode/seek samples.

`web-smoke.cjs` uses `ARVIO_HEADLESS_SHELL` pointing to a dedicated `chrome-headless-shell` installation for desktop checks. It refuses system Chrome and never uses a visible-browser fallback. For Apple-platform checks, set `ARVIO_TEST_BROWSER=webkit` on macOS with Playwright WebKit installed. It fetches the live manifest and seeds a fresh isolated browser profile before application hydration, then exercises real home/detail/play/seek flows. No existing account or browser state is needed. Results: `web-playback-result.json` and `actual-web-playback.png` (prefixed `webkit-` for Apple checks). Windows process checks confirmed all headless-shell main window handles were zero. The upstream worker browser launcher remains hard-disabled.

Before considering deployment: test resolution from a separate server IP, establish a supported extension/runtime combination without fragile conversion exclusions, and measure per-session CPU/memory/request cost. No UI redesign is needed for the source integration.

## Hosted release prerequisites

Pushing the web files does not host the Java resolver. The existing Netlify build and ordinary Cloudflare Worker deployment do not start this JVM service. The user confirmed no VPS/container host is configured. No new hosting account or charge has been created. The read-only GitHub capability check found the Cloudflare deployment secrets unset; the local Wrangler session also reports not logged in.

Keep the loopback prototype off production until a host is configured, the gateway has production authentication/rate limits, and client playback is verified from a different IP than the resolver. Never tunnel the developer's PC as the production backend. Provider-dependent direct playback must not silently become an ARVIO video relay.

The Phisher repository contains multiple extensions. This prototype executes its pinned **StreamPlay** extension; it does not claim compatibility with every Phisher `.cs3` or its Android-only APIs. The MovieBox conversion exclusion remains explicit.

The isolated macOS workflow exercises real StreamPlay search/source resolution and the unchanged ARVIO Home → details → Play → seek flow with WebKit and iPad touch/viewport settings. This is Apple-platform browser evidence, not a physical iPad test. Windows WebKit has no native HLS in the tested build and is deliberately rejected as Apple playback proof. Test setup installs the live manifest into an isolated profile; media responses and playback are never mocked.

Verified Apple run: [42625503626 / apple-webkit](https://github.com/ProdigyV21/ARVIO/actions/runs/42625503626/job/129188077357). The Apple job passed; the overall workflow failed only because the separate Linux suite detected the existing Android YouTube safeguard failure. Sanitized playback JSON and screenshot are saved as that run's `apple-webkit-playback` artifact (seven-day retention), with a local copy under `artifacts/cloudstream-poc/apple-webkit-42625503626/`. No signed media URLs or credentials are included in those artifacts.
