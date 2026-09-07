import { writeFileSync, mkdirSync } from "node:fs";
import { join } from "node:path";

// Build stamp for the in-app update watcher. iOS home-screen webapps cache the
// start page HTML aggressively (days) with no service worker to bust it — the
// client compares its baked-in stamp against /version.json (no-store) and
// reloads itself when a newer deploy exists. The same stamp is inlined via
// NEXT_PUBLIC_BUILD_STAMP and written to public/version.json here so they are
// always generated together.
const buildStamp = String(Date.now());
try {
  mkdirSync(join(process.cwd(), "public"), { recursive: true });
  writeFileSync(join(process.cwd(), "public", "version.json"), JSON.stringify({ v: buildStamp }));
} catch {
  // Non-fatal: the watcher simply stays inactive if the stamp is missing.
}

/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  poweredByHeader: false,
  outputFileTracingRoot: process.cwd(),
  env: {
    NEXT_PUBLIC_BUILD_STAMP: buildStamp,
    // APP_ANON_KEY is intentionally a public client key (the same value is
    // bundled in Android). Expose it under Next's browser-visible name so a
    // production deploy cannot silently lose ARVIO Cloud login/sync when the
    // Netlify site only defines the canonical server-side variable.
    NEXT_PUBLIC_ARVIO_APP_ANON_KEY:
      process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY || process.env.APP_ANON_KEY || "",
    // The client ID is public, unlike the OAuth secret. Direct browser reads
    // avoid the Netlify egress block and must work with the canonical site env.
    NEXT_PUBLIC_TRAKT_CLIENT_ID:
      process.env.NEXT_PUBLIC_TRAKT_CLIENT_ID || process.env.TRAKT_CLIENT_ID || ""
  },
  images: {
    remotePatterns: [
      { protocol: "https", hostname: "image.tmdb.org" },
      { protocol: "https", hostname: "**" }
    ]
  },
  webpack: (config, { isServer }) => {
    // GramJS (the browser-side Telegram/MTProto client used by lib/telegram)
    // imports Node core modules for its Node TCP transport and StoreSession. In
    // the browser it uses WebSocket + StringSession instead, so stub the Node-only
    // modules for the client bundle — otherwise webpack fails to resolve fs/net/etc.
    if (!isServer) {
      config.resolve = config.resolve ?? {};
      config.resolve.fallback = {
        ...config.resolve.fallback,
        fs: false,
        net: false,
        tls: false,
        dns: false,
        os: false,
        path: false,
        zlib: false,
        http: false,
        https: false,
        stream: false,
        crypto: false,
        perf_hooks: false
      };
    }
    return config;
  }
};

export default nextConfig;
