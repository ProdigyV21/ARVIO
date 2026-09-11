import { constants, copyFileSync, readFileSync } from "node:fs";
import { fileURLToPath } from "node:url";
import { parseEnv } from "node:util";

const template = new URL("../.env.selfhost.example", import.meta.url);
const destination = new URL("../.env.local", import.meta.url);
const command = process.argv[2];

if (command === "setup") {
  try {
    copyFileSync(template, destination, constants.COPYFILE_EXCL);
    console.log(`Created ${fileURLToPath(destination)}. Add your TMDB_API_KEY, then run npm run check:selfhost.`);
  } catch (error) {
    if (error.code !== "EEXIST") throw error;
    console.log(".env.local already exists; nothing was overwritten. Compare it with .env.selfhost.example, then run npm run check:selfhost.");
  }
} else if (command === "check") {
  let file;
  try { file = readFileSync(destination, "utf8"); }
  catch (error) {
    if (error.code !== "ENOENT") throw error;
    console.error("Missing .env.local. Run npm run setup:selfhost first.");
    process.exit(1);
  }
  const env = { ...parseEnv(file), ...process.env };
  const errors = [];
  if (env.NEXT_PUBLIC_SELF_HOSTED !== "true") errors.push("Set NEXT_PUBLIC_SELF_HOSTED=true for an independent installation.");
  if (!/^[a-f0-9]{32}$/i.test(env.TMDB_API_KEY ?? "")) errors.push("Add your TMDB API v3 key (32 hexadecimal characters), not the API read-access bearer token.");
  const traktId = env.NEXT_PUBLIC_TRAKT_CLIENT_ID || env.TRAKT_CLIENT_ID;
  const simklId = env.NEXT_PUBLIC_SIMKL_CLIENT_ID || env.SIMKL_CLIENT_ID;
  if (traktId && !env.TRAKT_CLIENT_SECRET) errors.push("TRAKT_CLIENT_SECRET is required for your Trakt OAuth token exchange/refresh.");
  if (Boolean(env.NEXT_PUBLIC_TELEGRAM_API_ID) !== Boolean(env.NEXT_PUBLIC_TELEGRAM_API_HASH)) errors.push("Set both Telegram application credentials, or leave both empty.");
  for (const name of ["NEXT_PUBLIC_TMDB_API_KEY", "NEXT_PUBLIC_TRAKT_CLIENT_SECRET", "NEXT_PUBLIC_SIMKL_CLIENT_SECRET"]) {
    if (env[name]) errors.push(`Remove ${name}: secrets must stay server-side.`);
  }
  if (env.NEXT_PUBLIC_ARVIO_RESOLVER_URL) console.log("Custom resolver configured: check that it is yours and supports the required API.");
  if (env.ALLOW_PRIVATE_PROXY === "true") console.log("LAN proxy enabled: protect this installation with authentication; never expose it publicly.");
  console.log(`Trakt: ${traktId ? "configured" : "optional, not configured"}. Simkl: ${simklId ? "configured" : "optional, not configured"}.`);
  if (errors.length) {
    errors.forEach((message) => console.error(message));
    process.exitCode = 1;
  } else {
    console.log("Configuration looks ready. This validates settings, not whether providers accept your keys. Rebuild after changing public credentials or mode.");
  }
} else {
  console.error("Usage: node scripts/selfhost.mjs setup|check");
  process.exitCode = 1;
}
