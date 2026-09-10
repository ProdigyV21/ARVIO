import { spawnSync } from "node:child_process";
import { createRequire } from "node:module";

const require = createRequire(import.meta.url);
const env = { ...process.env };
// Netlify CLI can replace browser variables with masked secret values. Carry
// the CI-supplied public key under a separate name until the actual Next build.
if (env.ARVIO_BUILD_APP_ANON_KEY) env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY = env.ARVIO_BUILD_APP_ANON_KEY;
delete env.ARVIO_BUILD_APP_ANON_KEY;
if (env.ARVIO_VERIFY_BUILD_CONFIG === "true") {
  const key = env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY ?? "";
  if (key.length < 40 || key.includes("*") || key.startsWith("$")) {
    throw new Error("Missing or masked public Cloud client key; refusing production build.");
  }
}
const result = spawnSync(process.execPath, [require.resolve("next/dist/bin/next"), "build"], { env, stdio: "inherit" });
if (result.error) throw result.error;
process.exit(result.status ?? 1);
