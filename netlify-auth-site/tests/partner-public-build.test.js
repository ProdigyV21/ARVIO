const test = require("node:test");
const assert = require("node:assert/strict");
const fs = require("node:fs/promises");
const path = require("node:path");
const { promisify } = require("node:util");
const { execFile } = require("node:child_process");

test("auth deployment retains Partner routes and excludes backend source and secrets", async () => {
  const root = path.resolve(__dirname, "..");
  const config = await fs.readFile(path.join(root, "netlify.toml"), "utf8");
  assert.match(config, /publish = "public-build"/);
  assert.match(config, /build-partner-pilot\.mjs --site/);
  const { stdout } = await promisify(execFile)(process.execPath,
    [path.join(root, "scripts/build-partner-pilot.mjs")], {
      cwd: root,
      env: { ...process.env, APP_ANON_KEY: "public-test-key-not-a-credential-12345678901234567890" }
    });
  const output = JSON.parse(stdout).publishDirectory;
  assert.equal(path.dirname(output), path.resolve(root, "../artifacts"));
  try {
    for (const name of ["index.html", "delete-account.html", "partner/index.html", "partner-assets/app.js", "partner-assets/app.css"]) {
      assert.ok((await fs.stat(path.join(output, name))).isFile(), name);
    }
    for (const name of ["sql", "scripts", "netlify", "tests", "node_modules", ".env", "package.json"]) {
      await assert.rejects(fs.access(path.join(output, name)), { code: "ENOENT" });
    }
    const redirects = await fs.readFile(path.join(output, "_redirects"), "utf8");
    assert.match(redirects, /\/partner\/\* \/partner\/index\.html 200/);
    assert.match(await fs.readFile(path.join(output, "_headers"), "utf8"), /Cache-Control: no-store/);
  } finally {
    await fs.rm(output, { recursive: true, force: true });
  }
});
