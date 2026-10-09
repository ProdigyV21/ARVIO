# syntax=docker/dockerfile:1@sha256:4edf897a3ffa55b89f906fc8cc78afdb3f1834cc9c7083565e611a8a7d5fe99e
FROM emscripten/emsdk:4.0.23@sha256:835a0e39f40a84d0b754d946d70c2da62239dad2c9cd5d9316f246a12f399f42 AS codecs
COPY distribution-sources/codecs /opt/arvio-codecs
COPY scripts/codecs-build.sh scripts/codecs-bundle.mjs scripts/codecs-finalize.mjs scripts/codecs-check.mjs scripts/codecs-runtime-test.mjs /opt/arvio-codecs/
RUN bash /opt/arvio-codecs/codecs-build.sh
RUN CODECS_INPUTS_PATH=/opt/arvio-codecs/inputs.json node /opt/arvio-codecs/codecs-check.mjs /out /work/arvio-codecs/mediabunny

FROM scratch AS codecs-export
COPY --from=codecs /out/ /

FROM node:22.23.3-bookworm-slim@sha256:25330af3531fb5e23318554a0aa911125b6e91b1b777edf7655501d207c067a2 AS runtime-materials
WORKDIR /app
COPY distribution-sources/runtime ./distribution-sources/runtime
COPY scripts/runtime-collect.mjs ./scripts/runtime-collect.mjs
# Inventory the exact unmodified runner base, not build-only system packages.
RUN node scripts/runtime-collect.mjs /distribution/runtime

FROM runtime-materials AS dependencies
WORKDIR /app
ENV NEXT_TELEMETRY_DISABLED=1
COPY distribution-sources/unraid/package.json distribution-sources/unraid/package-lock.json ./
RUN npm ci
COPY scripts/runtime-fetch-sources.mjs ./scripts/runtime-fetch-sources.mjs
RUN node scripts/runtime-fetch-sources.mjs /distribution/runtime
# This runner is Debian/glibc only. npm also installs optional musl variants;
# omit those unused binaries before tracing so they cannot silently widen the
# native distribution/source contract. Keep the active glibc Sharp packages.
RUN node -e "const fs=require('node:fs');for(const p of ['sharp-linuxmusl-x64','sharp-libvips-linuxmusl-x64'])fs.rmSync('/app/node_modules/@img/'+p,{recursive:true,force:true})"
# These directories contain old opaque embedded WASM. Remove only these three
# temporary dependency dirs before replacing them; overlay COPY is insufficient.
RUN node -e "const fs=require('node:fs');for(const p of ['ac3','dts','aac-encoder'])fs.rmSync('/app/node_modules/@mediabunny/'+p,{recursive:true,force:true})"
COPY --from=codecs /out/packages/@mediabunny/ac3 ./node_modules/@mediabunny/ac3
COPY --from=codecs /out/packages/@mediabunny/dts ./node_modules/@mediabunny/dts
COPY --from=codecs /out/packages/@mediabunny/aac-encoder ./node_modules/@mediabunny/aac-encoder

FROM dependencies AS builder
ENV NEXT_PUBLIC_SELF_HOSTED=true \
    NEXT_PUBLIC_UNRAID_DISTRIBUTION=true \
    NEXT_PUBLIC_TELEGRAM_ENABLED=false \
    NEXT_PUBLIC_PAYWALL_ENABLED=false \
    ARVIO_TRANSLATION_MODE=prebuilt \
    ARVIO_STANDALONE=true
# Build a generic, credential-free image. Optional public application IDs are
# loaded by the self-host-only /api/selfhost-config bootstrap at runtime.
# The web-only context validates checked-in i18n assets; hosted/source builds
# still generate these assets from Android resources by default.
COPY . .
RUN node scripts/unraid-package.mjs \
    && cp distribution-sources/unraid/package.json distribution-sources/unraid/package-lock.json ./ \
    && node -e "require('node:fs').unlinkSync('public/tg-stream-sw.js')" \
    && npm run build \
    && node scripts/collect-licenses.mjs \
    && node scripts/unraid-dependency-audit.mjs --stats-directory .next/dependency-audit --output .next/third-party-notices/unraid-audit.json \
    && node scripts/runtime-asset-inventory.mjs /distribution/runtime
COPY --from=codecs /out/distribution-sources/codecs /distribution/codecs
RUN mkdir -p public/distribution-sources \
    && cp distribution-artifacts/arvio-source.* public/distribution-sources/ \
    && cp LICENSE public/distribution-sources/ARVIO-LICENSE.txt \
    && cp distribution-sources/DISTRIBUTION-LICENSE.txt public/distribution-sources/ \
    && cp -R /distribution/runtime /distribution/codecs public/distribution-sources/ \
    && cp -R .next/third-party-notices public/distribution-sources/notices \
    && node scripts/distribution-index.mjs

FROM node:22.23.3-bookworm-slim@sha256:25330af3531fb5e23318554a0aa911125b6e91b1b777edf7655501d207c067a2 AS runner
WORKDIR /app
ENV NODE_ENV=production \
    NEXT_TELEMETRY_DISABLED=1 \
    NEXT_PUBLIC_SELF_HOSTED=true \
    NEXT_PUBLIC_UNRAID_DISTRIBUTION=true \
    NEXT_PUBLIC_TELEGRAM_ENABLED=false \
    NEXT_PUBLIC_PAYWALL_ENABLED=false \
    HOSTNAME=0.0.0.0 \
    PORT=3000
COPY --from=builder --chown=node:node /app/.next/standalone ./
COPY --from=builder --chown=node:node /app/.next/static ./.next/static
COPY --from=builder --chown=node:node /app/public ./public
COPY --from=builder --chown=node:node /app/LICENSE ./licenses/ARVIO-LICENSE
COPY --from=builder --chown=node:node /app/.next/third-party-notices ./licenses/third-party
USER node
EXPOSE 3000
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s \
  CMD node -e "fetch('http://127.0.0.1:3000/version.json',{signal:AbortSignal.timeout(4000)}).then(r=>process.exit(r.ok?0:1)).catch(()=>process.exit(1))"
CMD ["node", "server.js"]
