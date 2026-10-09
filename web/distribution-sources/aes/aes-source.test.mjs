import test from "node:test";
import assert from "node:assert/strict";
import { readFile } from "node:fs/promises";
import path from "node:path";
import { gzipSync } from "node:zlib";
import { bundleRoot, normalizeLineEndings, packageFiles, verifySourceSnapshot } from "../../scripts/aes-rebuild.mjs";
import { verifyAesMaterials } from "../../scripts/aes-verify-source.mjs";

function tarball(entries) {
  const parts = [];
  for (const [name, payload = "x", kind = "0"] of entries) {
    const bytes = Buffer.from(payload);
    const header = Buffer.alloc(512);
    header.write(name, 0, 100);
    header.write(`${bytes.length.toString(8).padStart(11, "0")}\0`, 124, 12);
    header[156] = kind.charCodeAt(0);
    header.fill(32, 148, 156);
    const checksum = header.reduce((total, byte) => total + byte, 0);
    header.write(`${checksum.toString(8).padStart(6, "0")}\0 `, 148, 8);
    parts.push(header, bytes, Buffer.alloc((512 - bytes.length % 512) % 512));
  }
  return gzipSync(Buffer.concat([...parts, Buffer.alloc(1024)]));
}

test("pinned source, component license, lock and correspondence evidence verify offline", async () => {
  assert.deepEqual(await verifyAesMaterials(), { component: "@cryptography/aes@0.1.1", sourceFiles: 28,
    matchedJavaScriptAndDeclarations: 13, byteIdenticalMinifiedFiles: 3 });
});

test("source verifier rejects hash drift and traversal without changing files", async () => {
  const manifest = JSON.parse(await readFile(path.join(bundleRoot, "manifest.json"), "utf8"));
  const changed = structuredClone(manifest);
  changed.sourceFiles["packages/aes/src/aes.ts"] = "0".repeat(64);
  await assert.rejects(verifySourceSnapshot(changed), /Source snapshot differs/);
  const unsafe = structuredClone(manifest);
  unsafe.sourceFiles = { "../escape": "0".repeat(64) };
  await assert.rejects(verifySourceSnapshot(unsafe), /Unsafe source manifest path/);
});

test("only CRLF line endings are normalized", () => {
  assert.equal(normalizeLineEndings(Buffer.from("a\r\nb\n")).toString(), "a\nb\n");
  assert.notEqual(normalizeLineEndings(Buffer.from("a  \r\n")).toString(), "a\n");
  assert.equal(normalizeLineEndings(Buffer.from("a\rb")).toString(), "a\rb");
});

test("npm reference reader accepts regular files and rejects unsafe/duplicate entries", () => {
  const files = packageFiles(tarball([["package", "", "5"], ["package/dist/es/aes.js", "code"]]));
  assert.equal(files.get("package/dist/es/aes.js").toString(), "code");
  assert.throws(() => packageFiles(tarball([["package/../escape", "x"]])), /Unsafe npm tar entry/);
  assert.throws(() => packageFiles(tarball([["package/link", "x", "2"]])), /Unsupported npm tar entry/);
  assert.throws(() => packageFiles(tarball([["package/x", "x"], ["package/x", "y"]])), /Duplicate npm tar entry/);
});

test("the full GPL text and original upstream license are retained separately", async () => {
  const gpl = await readFile(path.join(bundleRoot, "GPL-3.0.txt"), "utf8");
  assert.match(gpl, /GNU GENERAL PUBLIC LICENSE/);
  assert.match(gpl, /Version 3, 29 June 2007/);
  assert.match(gpl, /END OF TERMS AND CONDITIONS/);
  assert.match(gpl, /How to Apply These Terms to Your New Programs/);
  assert.match(await readFile(path.join(bundleRoot, "upstream/LICENSE"), "utf8"), /Apache License/);
});
