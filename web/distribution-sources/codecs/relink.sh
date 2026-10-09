#!/usr/bin/env bash
set -euo pipefail
# Run from this codec's extracted relink directory with Emscripten 4.0.23 on PATH.
# libavcodec.a and libavutil.a may be replaced with your modified FFmpeg build.
emcc bridge.o libavcodec.a libavutil.a \
  -s MODULARIZE=1 -s EXPORT_ES6=1 -s SINGLE_FILE=1 -s ALLOW_MEMORY_GROWTH=1 \
  -s ENVIRONMENT=web,worker -s FILESYSTEM=0 -s MALLOC=emmalloc -s SUPPORT_LONGJMP=0 \
  -s EXPORTED_RUNTIME_METHODS=cwrap,HEAPU8 -s EXPORTED_FUNCTIONS=_malloc,_free \
  -msimd128 -flto -Oz -o rebuilt-codec.js
