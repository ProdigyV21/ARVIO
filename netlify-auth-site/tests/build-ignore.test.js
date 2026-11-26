const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs/promises");
const path = require("node:path");
const { createRequire } = require("node:module");

// netlify.toml only rebuilds this site when its own folder or the listed ../web paths change.
// If the partner pilot bundle starts importing another web file, a change there would be
// skipped and production would serve a stale bundle, so every bundle input must be listed.
test("auth build ignore rule covers every web file the partner pilot bundle reads", async () => {
  const root = path.resolve(__dirname, "..");
  const web = path.resolve(root, "../web");
  const config = await fs.readFile(path.join(root, "netlify.toml"), "utf8");
  const rule = config.match(/^\s*ignore = '(.*)'$/m)?.[1];
  assert.ok(rule, "ignore rule present");
  const watched = [...rule.matchAll(/\.\.\/web\/(\S+)/g)].map((m) => m[1].replace(/\/$/, ""));
  assert.ok(watched.length > 0, "ignore rule lists web paths");

  const { build } = createRequire(path.join(web, "package.json"))("esbuild");
  const result = await build({
    entryPoints: [path.join(web, "partner-pilot/entry.tsx")], absWorkingDir: web, bundle: true, write: false,
    outfile: path.join(root, "unused/app.js"), metafile: true, jsx: "automatic", target: ["es2022"],
    tsconfig: path.join(web, "tsconfig.json"), define: { "process.env": "{}" }, logLevel: "silent"
  });
  const inputs = Object.keys(result.metafile.inputs)
    .filter((file) => !file.startsWith("<") && !file.includes("node_modules/"));
  assert.ok(inputs.length > 0);
  const missing = inputs.filter((file) => !watched.some((dir) => file === dir || file.startsWith(`${dir}/`)));
  assert.deepEqual(missing, [], "add these paths to the ignore rule in netlify.toml");
  assert.ok(watched.includes("package-lock.json"));
});
