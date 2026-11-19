import fs from "node:fs/promises";
import path from "node:path";
import { createRequire } from "node:module";
import { fileURLToPath } from "node:url";

const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const web = path.resolve(root, "../web");
const requireWeb = createRequire(path.join(web, "package.json"));
const { build } = requireWeb("esbuild");
const publicKey = process.env.APP_ANON_KEY;
if (!publicKey || publicKey.length < 40) throw new Error("The public ARVIO app key is required");
// Fresh output only. Never publish source, SQL, scripts, test fixtures or secrets.
const artifacts = path.resolve(root, "../artifacts");
let out;
if (process.argv.includes("--site")) {
  out = path.join(root, "public-build");
  await fs.mkdir(out, { recursive: true });
} else {
  await fs.mkdir(artifacts, { recursive: true });
  out = await fs.mkdtemp(path.join(artifacts, "partner-pilot-public-"));
}
for (const name of ["index.html", "delete-account.html", "assets", "discord", "docs", "_headers", "_redirects"]) {
  await fs.cp(path.join(root, name), path.join(out, name), { recursive: true });
}
await fs.cp(path.join(web, "public/arvio-icon-192.png"), path.join(out, "arvio-icon-192.png"));
const defines = { "process.env.NODE_ENV": '"production"', "process.env.NEXT_PUBLIC_ARVIO_APP_ANON_KEY": JSON.stringify(publicKey), "process.env.NEXT_PUBLIC_NETLIFY_BACKEND_URL": '"https://auth.arvio.tv/.netlify/functions"', "process.env": "{}" };
await build({ entryPoints: [path.join(web, "partner-pilot/entry.tsx")], bundle: true, minify: true, jsx: "automatic", target: ["es2022"],
  outfile: path.join(out, "partner-assets/app.js"), define: defines, tsconfig: path.join(web, "tsconfig.json") });
await fs.mkdir(path.join(out, "partner"), { recursive: true });
await fs.writeFile(path.join(out, "partner/index.html"), '<!doctype html><html lang="en"><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1"><meta name="robots" content="noindex,nofollow"><title>ARVIO Partner</title><link rel="icon" href="/arvio-icon-192.png"><link rel="stylesheet" href="/partner-assets/app.css"></head><body><div id="root"></div><script defer src="/partner-assets/app.js"></script></body></html>');
await fs.appendFile(path.join(out, "_redirects"), '\n/partner /partner/index.html 200\n/partner/* /partner/index.html 200\n');
await fs.appendFile(path.join(out, "_headers"), '\n/partner*\n  X-Robots-Tag: noindex, nofollow\n  Cache-Control: no-store\n  Referrer-Policy: no-referrer\n  X-Content-Type-Options: nosniff\n  Content-Security-Policy: default-src \'none\'; script-src \'self\'; style-src \'self\'; img-src \'self\'; connect-src \'self\' https://auth.arvio.tv; font-src \'self\'; base-uri \'none\'; frame-ancestors \'none\'; form-action \'self\'\n');
console.log(JSON.stringify({ publishDirectory: out }));
