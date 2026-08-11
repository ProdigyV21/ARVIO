const assert = require("node:assert/strict");
const fs = require("node:fs");
const path = require("node:path");
const test = require("node:test");
const vm = require("node:vm");

function loadRedirectValidator() {
  const html = fs.readFileSync(path.join(__dirname, "..", "index.html"), "utf8");
  const start = html.indexOf("    function isValidRedirectUri(uri) {");
  const end = html.indexOf("    const statusEl", start);
  assert.notEqual(start, -1, "redirect validator should exist in auth portal");
  assert.notEqual(end, -1, "redirect validator should have a stable end marker");

  const context = { URL };
  vm.runInNewContext(`${html.slice(start, end)}\nvalidator = isValidRedirectUri;`, context);
  return context.validator;
}

const isValidRedirectUri = loadRedirectValidator();

test("accepts ARVIO production, local, and web preview callback URLs", () => {
  const allowed = [
    "https://web.arvio.tv/",
    "https://arvio.tv/",
    "https://arvio-web.netlify.app/",
    "https://simkl-web--arvio-web.netlify.app/",
    "https://deploy-preview-554--arvio-web.netlify.app/",
    "https://devserver-simkl-web--arvio-web.netlify.app/",
    "http://localhost:3000/",
    "http://127.0.0.1:3000/"
  ];

  for (const uri of allowed) {
    assert.equal(isValidRedirectUri(uri), true, uri);
  }
});

test("rejects unrelated, deceptive, and insecure callback URLs", () => {
  const rejected = [
    "https://example.com/",
    "https://arvio-web.netlify.app.example.com/",
    "https://arvio-web--attacker.netlify.app/",
    "http://simkl-web--arvio-web.netlify.app/",
    "https://user:password@web.arvio.tv/",
    "javascript:alert(1)",
    "not-a-url",
    ""
  ];

  for (const uri of rejected) {
    assert.equal(isValidRedirectUri(uri), false, uri);
  }
});
