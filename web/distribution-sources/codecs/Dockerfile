# Full-featured codec compiler. Keep this base in sync with inputs.json.
FROM emscripten/emsdk:4.0.23@sha256:835a0e39f40a84d0b754d946d70c2da62239dad2c9cd5d9316f246a12f399f42 AS codecs
COPY distribution-sources/codecs /opt/arvio-codecs
COPY scripts/codecs-build.sh scripts/codecs-bundle.mjs scripts/codecs-finalize.mjs scripts/codecs-check.mjs scripts/codecs-runtime-test.mjs /opt/arvio-codecs/
RUN bash /opt/arvio-codecs/codecs-build.sh

FROM scratch AS codecs-export
COPY --from=codecs /out/ /
