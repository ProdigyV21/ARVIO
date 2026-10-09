#!/usr/bin/env bash
set -euo pipefail

# This runs only inside the pinned, isolated Linux compiler image. It does not
# publish packages, containers or files to an external service.
CODECS_WORK=/work/arvio-codecs
CODECS_OUT=/out
CODECS_TOOLS=/opt/arvio-codecs
mkdir -p "$CODECS_WORK/inputs" "$CODECS_WORK/mediabunny" "$CODECS_WORK/ffmpeg" "$CODECS_OUT/packages/@mediabunny" "$CODECS_OUT/distribution-sources/codecs"
node -e 'const p=require("/opt/arvio-codecs/inputs.json"); if(p.compiler.version!=="4.0.23")throw Error("Compiler pin changed");'
emcc --version | head -n 1
emcc --version | grep -q '4.0.23'

fetch_input() {
  local input_name="$1" output_file="$2" input_url input_sha
  input_url=$(node -p "require('$CODECS_TOOLS/inputs.json').$input_name.url")
  input_sha=$(node -p "require('$CODECS_TOOLS/inputs.json').$input_name.sha256")
  curl --fail --location --retry 3 --output "$output_file" "$input_url"
  printf '%s  %s\n' "$input_sha" "$output_file" | sha256sum --check --status
}
fetch_input mediabunny "$CODECS_WORK/inputs/mediabunny-input.tar.gz"
fetch_input ffmpeg "$CODECS_WORK/inputs/ffmpeg-8.1.3.tar.xz"
tar -xzf "$CODECS_WORK/inputs/mediabunny-input.tar.gz" --strip-components=1 -C "$CODECS_WORK/mediabunny"
tar -xJf "$CODECS_WORK/inputs/ffmpeg-8.1.3.tar.xz" --strip-components=1 -C "$CODECS_WORK/ffmpeg"
test "$(cat "$CODECS_WORK/ffmpeg/VERSION")" = '8.1.3'
node -e 'if(require("/work/arvio-codecs/mediabunny/package.json").version!=="1.55.7")throw Error("Mediabunny source mismatch");'

# Archive only preferred-form source, not upstream's unrelated prebuilt WASM,
# object libraries, npm dist bundles, test media or downloaded dependencies.
# All core/selected-package sources and upstream locked build tooling remain.
tar -czf "$CODECS_OUT/distribution-sources/codecs/mediabunny-1.55.7-sources.tar.gz" \
  --exclude='*/build' --exclude='*/dist' --exclude='*/node_modules' \
  --exclude='*.wasm' --exclude='*.a' --exclude='*.o' --exclude='*.mp4' --exclude='*.webm' \
  -C "$CODECS_WORK/mediabunny" LICENSE README.md package.json package-lock.json tsconfig.json src shared scripts packages
cp "$CODECS_WORK/inputs/ffmpeg-8.1.3.tar.xz" "$CODECS_OUT/distribution-sources/codecs/"
cp "$CODECS_TOOLS/inputs.json" "$CODECS_OUT/distribution-sources/codecs/"
cp "$CODECS_TOOLS/codecs-build.sh" "$CODECS_TOOLS/codecs-bundle.mjs" "$CODECS_TOOLS/codecs-finalize.mjs" "$CODECS_TOOLS/codecs-check.mjs" "$CODECS_TOOLS/codecs-runtime-test.mjs" "$CODECS_TOOLS/Dockerfile" "$CODECS_TOOLS/README.md" "$CODECS_TOOLS/relink.sh" "$CODECS_OUT/distribution-sources/codecs/"
cp "$CODECS_WORK/mediabunny/LICENSE" "$CODECS_OUT/distribution-sources/codecs/MEDIABUNNY-LICENSE.txt"
cp "$CODECS_WORK/ffmpeg/COPYING.LGPLv2.1" "$CODECS_OUT/distribution-sources/codecs/FFMPEG-LGPL-2.1.txt"
mkdir -p "$CODECS_OUT/distribution-sources/codecs/toolchain-notices"
find /emsdk/upstream/emscripten -maxdepth 2 -type f \( -iname '*license*' -o -iname '*copying*' -o -iname '*copyright*' \) \
  -exec cp --parents '{}' "$CODECS_OUT/distribution-sources/codecs/toolchain-notices/" \;
# The linked libc/compiler-rt sources have nested notices (including musl's
# COPYRIGHT), not just the top-level Emscripten license.
find /emsdk/upstream/emscripten/system -type f \( -iname '*license*' -o -iname '*copying*' -o -iname '*copyright*' \) \
  -exec cp --parents '{}' "$CODECS_OUT/distribution-sources/codecs/toolchain-notices/" \;

for package in ac3 dts aac-encoder; do
  codec_stem="$package"
  codec_flags=()
  case "$package" in
    ac3) codec_flags=(--enable-decoder=ac3 --enable-decoder=eac3 --enable-encoder=ac3 --enable-encoder=eac3) ;;
    dts) codec_flags=(--enable-decoder=dca --enable-encoder=dca) ;;
    aac-encoder) codec_stem=aac; codec_flags=(--enable-encoder=aac) ;;
  esac
  codec_build="$CODECS_WORK/ffmpeg-$package"
  codec_relink="$CODECS_OUT/distribution-sources/codecs/relink/$package"
  mkdir -p "$codec_build" "$codec_relink"
  cd "$codec_build"
  emconfigure "$CODECS_WORK/ffmpeg/configure" \
    --target-os=none --arch=x86_32 --enable-cross-compile \
    --disable-asm --disable-x86asm --disable-inline-asm \
    --disable-programs --disable-doc --disable-debug \
    --disable-all --disable-everything --disable-autodetect \
    --disable-pthreads --disable-runtime-cpudetect \
    --disable-gpl --disable-nonfree --enable-avcodec \
    "${codec_flags[@]}" \
    --cc=emcc --cxx=em++ --ar=emar --ranlib=emranlib --nm=emnm \
    --extra-cflags='-DNDEBUG -Oz -flto -msimd128' --extra-ldflags='-Oz -flto'
  grep -q '#define CONFIG_GPL 0' config.h
  grep -q '#define CONFIG_NONFREE 0' config.h
  emmake make -j2
  cp libavcodec/libavcodec.a libavutil/libavutil.a "$codec_relink/"
  cp config.h config_components.h ffbuild/config.mak "$codec_relink/"
  cp "$CODECS_WORK/mediabunny/packages/$package/src/bridge.c" "$codec_relink/"
  emcc -c "$CODECS_WORK/mediabunny/packages/$package/src/bridge.c" \
    -I"$codec_build" -I"$CODECS_WORK/ffmpeg" -msimd128 -flto -Oz -o "$codec_relink/bridge.o"
  emcc "$codec_relink/bridge.o" "$codec_relink/libavcodec.a" "$codec_relink/libavutil.a" \
    -s MODULARIZE=1 -s EXPORT_ES6=1 -s SINGLE_FILE=1 -s ALLOW_MEMORY_GROWTH=1 \
    -s ENVIRONMENT=web,worker -s FILESYSTEM=0 -s MALLOC=emmalloc -s SUPPORT_LONGJMP=0 \
    -s EXPORTED_RUNTIME_METHODS=cwrap,HEAPU8 -s EXPORTED_FUNCTIONS=_malloc,_free \
    -msimd128 -flto -Oz -o "$CODECS_WORK/mediabunny/packages/$package/build/$codec_stem.js"
  # This reusable command allows recipients to relink with modified libraries.
  cp "$CODECS_TOOLS/relink.sh" "$codec_relink/relink.sh"
done

cd "$CODECS_WORK/mediabunny"
npm ci --ignore-scripts
node node_modules/typescript/bin/tsc -p src --stripInternal false
for package in ac3 dts aac-encoder; do
  node node_modules/typescript/bin/tsc -p "packages/$package"
done
node node_modules/tsx/dist/cli.mjs "$CODECS_TOOLS/codecs-bundle.mjs" "$CODECS_WORK/mediabunny"
for package in ac3 dts aac-encoder; do
  mkdir -p "$CODECS_OUT/packages/@mediabunny/$package"
  cp -R "packages/$package/dist" "packages/$package/src" "packages/$package/README.md" "packages/$package/LICENSE" "packages/$package/package.json" "$CODECS_OUT/packages/@mediabunny/$package/"
done
node "$CODECS_TOOLS/codecs-finalize.mjs" "$CODECS_OUT"
CODECS_INPUTS_PATH="$CODECS_TOOLS/inputs.json" node "$CODECS_TOOLS/codecs-check.mjs" "$CODECS_OUT" "$CODECS_WORK/mediabunny"
