import { readFile } from "node:fs/promises";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { bundleRoot, verifySourceSnapshot } from "./aes-rebuild.mjs";

export async function verifyAesMaterials() {
  const manifest = JSON.parse(await readFile(path.join(bundleRoot, "manifest.json"), "utf8"));
  await verifySourceSnapshot(manifest);
  const lock = JSON.parse(await readFile(new URL("../package-lock.json", import.meta.url), "utf8"));
  const entry = lock.packages["node_modules/@cryptography/aes"];
  if (!entry || entry.version !== manifest.npm.version || entry.integrity !== manifest.npm.integrity || entry.resolved !== manifest.npm.tarball) {
    throw new Error("Web lockfile no longer corresponds to the supplied AES source materials");
  }
  const metadata = JSON.parse(await readFile(path.join(bundleRoot, "upstream/packages/aes/package.json"), "utf8"));
  if (metadata.name !== manifest.npm.name || metadata.version !== manifest.npm.version || metadata.license !== manifest.npm.declaredLicense) {
    throw new Error("Original AES package metadata disagrees with the component manifest");
  }
  const tools = JSON.parse(await readFile(path.join(bundleRoot, "toolchain/package-lock.json"), "utf8"));
  for (const [name, version] of Object.entries(manifest.rebuildTools)) {
    if (tools.packages[`node_modules/${name}`]?.version !== version) throw new Error(`AES rebuild tool lock differs: ${name}`);
  }
  const evidence = JSON.parse(await readFile(path.join(bundleRoot, "comparison.json"), "utf8"));
  const software = evidence.files.filter((item) => /\.(?:js|d\.ts)$/.test(item.file));
  const minified = software.filter((item) => item.file.endsWith(".min.js"));
  if (evidence.package !== manifest.npm.name || evidence.version !== manifest.npm.version || evidence.sourceCommit !== manifest.upstream.commit
    || software.length !== 13 || minified.length !== 3 || !software.every((item) => ["byte-identical", "line-ending-identical"].includes(item.match))
    || !minified.every((item) => item.match === "byte-identical" && item.originalSha256 === item.rebuiltSha256)) {
    throw new Error("Incomplete AES package correspondence record");
  }
  return { component: `${manifest.npm.name}@${manifest.npm.version}`, sourceFiles: Object.keys(manifest.sourceFiles).length,
    matchedJavaScriptAndDeclarations: software.length, byteIdenticalMinifiedFiles: minified.length };
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  console.log(JSON.stringify(await verifyAesMaterials(), null, 2));
}
