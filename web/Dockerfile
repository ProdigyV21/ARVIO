# syntax=docker/dockerfile:1
FROM node:22-bookworm-slim AS dependencies
WORKDIR /app
ENV NEXT_TELEMETRY_DISABLED=1
COPY package.json package-lock.json ./
RUN npm ci

FROM dependencies AS builder
ARG TRAKT_CLIENT_ID=""
ARG SIMKL_CLIENT_ID=""
ARG NEXT_PUBLIC_TELEGRAM_API_ID=""
ARG NEXT_PUBLIC_TELEGRAM_API_HASH=""
ARG NEXT_PUBLIC_ARVIO_RESOLVER_URL=""
ENV NEXT_PUBLIC_SELF_HOSTED=true \
    NEXT_PUBLIC_PAYWALL_ENABLED=false \
    ARVIO_STANDALONE=true \
    NEXT_PUBLIC_TRAKT_CLIENT_ID=$TRAKT_CLIENT_ID \
    NEXT_PUBLIC_SIMKL_CLIENT_ID=$SIMKL_CLIENT_ID \
    NEXT_PUBLIC_TELEGRAM_API_ID=$NEXT_PUBLIC_TELEGRAM_API_ID \
    NEXT_PUBLIC_TELEGRAM_API_HASH=$NEXT_PUBLIC_TELEGRAM_API_HASH \
    NEXT_PUBLIC_ARVIO_RESOLVER_URL=$NEXT_PUBLIC_ARVIO_RESOLVER_URL
COPY . .
RUN npm run build

FROM node:22-bookworm-slim AS runner
WORKDIR /app
ENV NODE_ENV=production \
    NEXT_TELEMETRY_DISABLED=1 \
    NEXT_PUBLIC_SELF_HOSTED=true \
    NEXT_PUBLIC_PAYWALL_ENABLED=false \
    HOSTNAME=0.0.0.0 \
    PORT=3000
COPY --from=builder --chown=node:node /app/.next/standalone ./
COPY --from=builder --chown=node:node /app/.next/static ./.next/static
COPY --from=builder --chown=node:node /app/public ./public
USER node
EXPOSE 3000
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s \
  CMD node -e "fetch('http://127.0.0.1:3000/version.json').then(r=>process.exit(r.ok?0:1)).catch(()=>process.exit(1))"
CMD ["node", "server.js"]
