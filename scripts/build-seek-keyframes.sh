#!/usr/bin/env bash
# Linux/WSL, Android NDK r28c. Rebuildable LGPL libraries, no mpv/GPL components.
set -euo pipefail
repo=$(cd "$(dirname "$0")/.." && pwd)
: "${ANDROID_NDK_HOME:?Set ANDROID_NDK_HOME to Android NDK r28c for Linux}"
work=${SEEK_BUILD_DIR:-"$HOME/.cache/arvio-seek-native"}
mkdir -p "$work"
version=8.0.1
archive="$work/ffmpeg-$version.tar.xz"
if [[ ! -f "$archive" ]]; then
    bundled="$repo/third_party/ffmpeg/ffmpeg-$version.tar.xz"
    if [[ -f "$bundled" ]]; then
        cp "$bundled" "$archive"
    else
        curl --fail --location --retry 2 "https://ffmpeg.org/releases/ffmpeg-$version.tar.xz" -o "$archive"
    fi
fi
echo "05ee0b03119b45c0bdb4df654b96802e909e0a752f72e4fe3794f487229e5a41  $archive" | sha256sum --check -
if [[ ! -d "$work/ffmpeg-$version" ]]; then tar -xf "$archive" -C "$work"; fi
export PATH="$ANDROID_NDK_HOME/toolchains/llvm/prebuilt/linux-x86_64/bin:$PATH"
abis=${SEEK_ABIS:-"armeabi-v7a arm64-v8a x86 x86_64"}
for abi in $abis; do
    extra=()
    case "$abi" in
        armeabi-v7a) arch=arm; compiler=armv7a-linux-androideabi23; extra=(--enable-neon);;
        arm64-v8a) arch=aarch64; compiler=aarch64-linux-android23;;
        x86) arch=x86; compiler=i686-linux-android23; extra=(--disable-x86asm --disable-inline-asm);;
        x86_64) arch=x86_64; compiler=x86_64-linux-android23; extra=(--disable-x86asm);;
        *) echo "Unknown ABI: $abi" >&2; exit 1;;
    esac
    mkdir -p "$work/build-$abi" "$work/install-$abi"
    cd "$work/build-$abi"
    "$work/ffmpeg-$version/configure" --prefix="$work/install-$abi" \
        --target-os=android --arch="$arch" --enable-cross-compile \
        --cc="$compiler-clang" --cxx="$compiler-clang++" --ar=llvm-ar --ranlib=llvm-ranlib --strip=llvm-strip \
        --disable-programs --disable-doc --disable-debug --disable-autodetect --disable-everything \
        --enable-shared --disable-static --disable-avdevice --disable-avfilter --disable-swresample \
        --enable-avformat --enable-avcodec --enable-swscale --enable-avutil \
        --enable-decoder=h264,hevc,vp9 --enable-parser=h264,hevc,vp9 \
        --enable-demuxer=mov,matroska,mpegts --enable-protocol=http,tcp --enable-network --enable-pic \
        --extra-ldflags=-Wl,-z,max-page-size=16384 "${extra[@]}"
    make -j"${SEEK_JOBS:-4}"
    make install
    destination="$repo/app/src/main/jniLibs/$abi"
    mkdir -p "$destination"
    "$compiler-clang++" -std=c++17 -O2 -fPIC -shared -static-libstdc++ \
        -fvisibility=hidden -Wl,--exclude-libs,ALL -Wl,-z,max-page-size=16384 -Wl,-soname,libarvio_seek.so \
        -I"$work/install-$abi/include" "$repo/app/src/main/cpp/seek_keyframe.cpp" \
        -L"$work/install-$abi/lib" -lavformat -lavcodec -lavutil -lswscale -ljnigraphics -llog \
        -o "$destination/libarvio_seek.so"
    llvm-strip --strip-unneeded "$destination/libarvio_seek.so"
    cp "$work/install-$abi/lib/"*.so "$destination/"
done
mkdir -p "$repo/app/src/main/assets/licenses"
cp "$work/ffmpeg-$version/COPYING.LGPLv2.1" "$repo/app/src/main/assets/licenses/ffmpeg-LGPL-2.1.txt"
