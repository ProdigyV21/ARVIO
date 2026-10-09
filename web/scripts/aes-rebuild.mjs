import { createHash } from "node:crypto";
import { cp, mkdir, mkdtemp, readFile, readdir, symlink, writeFile } from "node:fs/promises";
import path from "node:path";
import { spawn } from "node:child_process";
import { fileURLToPath } from "node:url";
import { gunzipSync } from "node:zlib";

export const bundleRoot = fileURLToPath(new URL("../distribution-sources/aes/", import.meta.url));
export const sha256 = (bytes) => createHash("sha256").update(bytes).digest("hex");
export const normalizeLineEndings = (bytes) => Buffer.from(bytes.toString("utf8").replace(/\r\n/g, "\n"), "utf8");

// Read, do not extract, the small pinned npm tarball. Reject non-regular files
// and traversal names before accepting any package contents as reference data.
export function packageFiles(tgz) {
  const tar = gunzipSync(tgz);
  const files = new Map();
  for (let offset = 0; offset + 512 <= tar.length;) {
    const header = tar.subarray(offset, offset + 512);
    if (header.every((byte) => byte === 0)) break;
    const name = header.subarray(0, 100).toString("utf8").replace(/\0.*$/, "");
    const prefix = header.subarray(345, 500).toString("utf8").replace(/\0.*$/, "");
    const entry = prefix ? `${prefix}/${name}` : name;
    const size = Number.parseInt(header.subarray(124, 136).toString("ascii").replace(/\0.*$/, "").trim(), 8);
    const kind = String.fromCharCode(header[156]);
    const recordedChecksum = Number.parseInt(header.subarray(148, 156).toString("ascii").replace(/\0.*$/, "").trim(), 8);
    const checksum = header.reduce((sum, byte, index) => sum + (index >= 148 && index < 156 ? 32 : byte), 0);
    if (checksum !== recordedChecksum) throw new Error("Invalid npm tar header checksum");
    if (!Number.isSafeInteger(size) || size < 0 || offset + 512 + size > tar.length) throw new Error("Invalid npm tar entry size");
    if (!(entry.startsWith("package/") || entry === "package" && kind === "5") || entry.includes("\\") || entry.split("/").includes("..")) throw new Error(`Unsafe npm tar entry path: ${entry} (${kind})`);
    if (kind !== "0" && kind !== "\0" && kind !== "5") throw new Error(`Unsupported npm tar entry type: ${kind}`);
    if (kind !== "5") {
      if (files.has(entry)) throw new Error("Duplicate npm tar entry");
      files.set(entry, tar.subarray(offset + 512, offset + 512 + size));
    }
    offset += 512 + Math.ceil(size / 512) * 512;
  }
  return files;
}

export async function referenceFiles(manifest) {
  const response = await fetch(manifest.npm.tarball, { signal: AbortSignal.timeout(30000) });
  if (!response.ok) throw new Error(`npm reference download failed: ${response.status}`);
  const tarball = Buffer.from(await response.arrayBuffer());
  const integrity = `sha512-${createHash("sha512").update(tarball).digest("base64")}`;
  if (integrity !== manifest.npm.integrity) throw new Error("npm reference integrity does not match the pinned web lockfile");
  return packageFiles(tarball);
}

export async function verifySourceSnapshot(manifest) {
  for (const [relative, expected] of Object.entries(manifest.sourceFiles)) {
    if (relative.includes("\\") || relative.split("/").includes("..") || path.isAbsolute(relative)) throw new Error("Unsafe source manifest path");
    let bytes = await readFile(path.join(bundleRoot, "upstream", relative));
    if (manifest.sourceNormalizations?.[relative] === "one trailing LF added by text-file import") {
      if (bytes.at(-1) !== 10) throw new Error(`Missing recorded trailing LF: ${relative}`);
      bytes = bytes.subarray(0, -1);
    }
    const actual = sha256(bytes);
    if (actual !== expected) throw new Error(`Source snapshot differs from the recorded upstream bytes: ${relative}`);
  }
  const license = await readFile(path.join(bundleRoot, "GPL-3.0.txt"));
  if (sha256(license) !== manifest.license.sha256) throw new Error("GPL license text checksum differs");
}

function run(command, args, cwd) {
  return new Promise((resolve, reject) => {
    const child = spawn(command, args, { cwd, stdio: "inherit" });
    child.on("error", reject);
    child.on("exit", (code) => code === 0 ? resolve() : reject(new Error(`AES build exited with ${code}`)));
  });
}

export async function rebuild() {
  const manifest = JSON.parse(await readFile(path.join(bundleRoot, "manifest.json"), "utf8"));
  await verifySourceSnapshot(manifest);
  const toolchain = path.join(bundleRoot, "toolchain");
  for (const [name, expected] of Object.entries(manifest.rebuildTools)) {
    const actual = JSON.parse(await readFile(path.join(toolchain, "node_modules", name, "package.json"), "utf8")).version;
    if (actual !== expected) throw new Error(`Install the pinned AES toolchain: ${name} is ${actual}, expected ${expected}`);
  }
  const reference = await referenceFiles(manifest);
  const referenceMetadata = JSON.parse(reference.get("package/package.json")?.toString("utf8") ?? "{}");
  if (referenceMetadata.name !== manifest.npm.name || referenceMetadata.version !== manifest.npm.version || referenceMetadata.license !== manifest.npm.declaredLicense) {
    throw new Error("Pinned npm tarball package identity disagrees with the source manifest");
  }
  const output = path.join(bundleRoot, "rebuild-output");
  await mkdir(output, { recursive: true });
  const scratch = await mkdtemp(path.join(output, "run-"));
  await cp(path.join(bundleRoot, "upstream"), scratch, { recursive: true });
  const moduleRoot = path.join(scratch, "node_modules");
  await mkdir(moduleRoot);
  const linkType = process.platform === "win32" ? "junction" : "dir";
  for (const entry of await readdir(path.join(toolchain, "node_modules"), { withFileTypes: true })) {
    if (!entry.isDirectory() || entry.name.startsWith(".")) continue;
    await symlink(path.join(toolchain, "node_modules", entry.name), path.join(moduleRoot, entry.name), linkType);
  }
  await mkdir(path.join(moduleRoot, "@cryptography"));
  await symlink(path.join(scratch, "packages/utils"), path.join(moduleRoot, "@cryptography/utils"), linkType);
  // Bound the old minifier's worker pool; this changes scheduling, not code.
  const preload = path.join(bundleRoot, "aes-worker-limit.cjs");
  const buildArgs = ["--require", preload, path.join(toolchain, "node_modules/rollup/dist/bin/rollup"), "--config", "rollup.config.js"];
  await run(process.execPath, buildArgs, path.join(scratch, "packages/utils"));
  await run(process.execPath, buildArgs, path.join(scratch, "packages/aes"));
  const results = [];
  for (const [name, original] of reference) {
    if (!name.startsWith("package/dist/")) continue;
    const relative = name.slice("package/".length);
    let generated;
    try { generated = await readFile(path.join(scratch, "packages/aes", relative)); }
    catch { results.push({ file: relative, originalSha256: sha256(original), match: "missing" }); continue; }
    const normalized = normalizeLineEndings(original);
    const match = original.equals(generated) ? "byte-identical"
      : normalized.equals(normalizeLineEndings(generated)) ? "line-ending-identical" : "different";
    results.push({ file: relative, originalSha256: sha256(original), rebuiltSha256: sha256(generated), normalizedSha256: sha256(normalized), match });
  }
  const report = { package: manifest.npm.name, version: manifest.npm.version, sourceCommit: manifest.upstream.commit,
    nodeVersion: process.version, tools: manifest.rebuildTools, files: results,
    byteIdenticalMinifiedJavaScript: results.filter((item) => item.file.endsWith(".min.js")).every((item) => item.match === "byte-identical"),
    correspondingExecutableAndDeclarationFiles: results.filter((item) => /\.(?:js|d\.ts)$/.test(item.file)).every((item) => ["byte-identical", "line-ending-identical"].includes(item.match)) };
  await writeFile(path.join(scratch, "comparison.json"), `${JSON.stringify(report, null, 2)}\n`);
  console.log(JSON.stringify({ ...report, output: scratch }, null, 2));
  const software = results.filter((item) => /\.(?:js|d\.ts)$/.test(item.file));
  if (software.length !== 13 || software.filter((item) => item.file.endsWith(".min.js")).length !== 3
    || !report.byteIdenticalMinifiedJavaScript || !report.correspondingExecutableAndDeclarationFiles) {
    throw new Error("The rebuilt package does not match the pinned npm release; retain the report for investigation");
  }
  await writeFile(path.join(output, "latest-comparison.json"), `${JSON.stringify(report, null, 2)}\n`);
  return report;
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  await rebuild();
}
