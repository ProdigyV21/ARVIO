import { spawnSync } from "node:child_process";
import { createRequire } from "node:module";
import { rmSync } from "node:fs";
import { fileURLToPath } from "node:url";

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
// Validate configuration before generating files or starting the build.
// One stamp is inherited by every Next worker. Remove only the obsolete public
// generated file, whose route is now built atomically with the client bundle.
env.ARVIO_BUILD_STAMP = String(Date.now());
rmSync(fileURLToPath(new URL('../public/version.json', import.meta.url)), { force: true });
const translationMode = env.ARVIO_TRANSLATION_MODE || "generate";
if (translationMode === "generate") {
  await import('./generate-translations.mjs');
} else if (translationMode === "prebuilt") {
  const { validatePrebuiltTranslations } = await import('./validate-prebuilt-translations.mjs');
  const checked = validatePrebuiltTranslations();
  console.log(`Validated ${checked.locales} packaged interface languages (${checked.phrases} phrases each).`);
} else {
  throw new Error("ARVIO_TRANSLATION_MODE must be generate or prebuilt.");
}
const result = spawnSync(process.execPath, [require.resolve("next/dist/bin/next"), "build"], { env, stdio: "inherit" });
if (result.error) throw result.error;
process.exit(result.status ?? 1);
