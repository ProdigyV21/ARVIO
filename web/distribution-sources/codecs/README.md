# Full-featured browser codec rebuild

The original Mediabunny 1.55.7 npm codec packages embed prebuilt FFmpeg WASM.
Their upstream source includes bridge code and build instructions but does not
pin the FFmpeg revision and compiler used for those binaries. This build does
not claim an exact source match for those original WASMs: it replaces all three
codec packages with an ARVIO rebuild from declared, checked inputs.

Mediabunny core and wrappers retain MPL-2.0. The new FFmpeg build is LGPL-2.1-or-later,
with GPL and nonfree disabled. ARVIO's own license is unchanged. This is technical
source/build evidence, not a blanket legal or patent clearance.

## Inputs and retained features

`inputs.json` pins the exact Mediabunny 1.55.7 Git tree/archive, FFmpeg 8.1.3
source archive and Linux amd64 Emscripten 4.0.23 image digest. Downloads are
hash-checked before extraction; upstream's package-lock pins JS build tools.

- AC-3 and E-AC-3: decoder and encoder.
- DTS (`dca`): decoder and encoder.
- AAC: encoder.

No listed codec capability is removed. New binaries still require WASM SIMD,
as did the originals. Browser/provider/playback compatibility needs runtime QA.

## Build and integrate

From the application source repository:

```sh
docker build --platform linux/amd64 -f web/distribution-sources/codecs/Dockerfile --target codecs-export --output type=local,dest=codec-output web
```

The compiler emits complete rebuilt packages at
`packages/@mediabunny/{ac3,dts,aac-encoder}` and corresponding materials at
`distribution-sources/codecs`. The application build must replace the three
installed package directories **before** bundling the app, then ship the entire
source directory at `/distribution-sources/codecs/`. Leaving original package
files/bundles in place does not establish that the app uses the new binaries.

The generated manifest hashes package files, source archives, build configs,
FFmpeg static libraries and bridge objects. Actual archive bytes and package
hashes must be checked when preparing the final image; this README alone is not
evidence that a deployed image contains them.

With the application's development dependencies installed and Google Chrome
available, verify the exported packages using:

```sh
node web/scripts/codecs-check.mjs codec-output web
node web/scripts/codecs-runtime-test.mjs codec-output web
```

The browser test uses the rebuilt package glue and inline workers to encode
one second of synthetic stereo PCM as AC-3, E-AC-3, DTS and AAC. It decodes the
first three with the rebuilt decoders, and AAC with Chromium's independent
WebCodecs decoder. It checks packet output, decoded duration/frame count,
finite non-silent samples and timestamps; no external media/provider is used.

## Modify and relink

The source-only Mediabunny archive includes core TS sources, worker wrappers,
C bridges, upstream locked JS tools and configs. It excludes upstream prebuilt
WASMs/object libraries, npm output and test media. The full FFmpeg source archive
and its original LGPL text are supplied alongside it. Build scripts/configs and
toolchain notices accompany the exact binaries.

For each codec, `relink/<package>/` contains `bridge.c`, `bridge.o`,
`libavcodec.a`, `libavutil.a`, FFmpeg config files and `relink.sh`. Recipients may
replace the FFmpeg libraries with modified builds and relink using Emscripten
4.0.23. To rebuild preferred-form C source, use `codecs-build.sh`; to rebundle a
modified JavaScript glue module, use `codecs-bundle.mjs` with the upstream source
tree and locked JS tools. This distribution adds no prohibition on reverse
engineering for debugging such modifications.

The Dockerfile currently describes the normal web build context. After extracting
these source materials, place `Dockerfile`, `inputs.json`, `README.md`, `relink.sh`
under `distribution-sources/codecs/` and the five `codecs-*` scripts under
`scripts/`, or adapt their explicit paths. Preserve license notices when modifying
covered files. FFmpeg source changes must be identified and provided with the
modified binary; rebuild hashes/provenance for your version instead of claiming
it is the original package.

Primary references: [Mediabunny exact source](https://github.com/Vanilagy/mediabunny/tree/c67c5e4072cf834743498c45a5a5bdf058947aec),
[FFmpeg release sources](https://ffmpeg.org/download.html),
[FFmpeg license checklist](https://ffmpeg.org/legal.html),
[MPL 2.0](https://www.mozilla.org/en-US/MPL/2.0/),
[Emscripten installation](https://emscripten.org/docs/getting_started/downloads.html).
