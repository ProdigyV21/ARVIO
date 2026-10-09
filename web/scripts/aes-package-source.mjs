import { copyFile, mkdir, readFile, stat, writeFile } from "node:fs/promises";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { bundleRoot, sha256 } from "./aes-rebuild.mjs";
import { verifyAesMaterials } from "./aes-verify-source.mjs";

const webRoot = path.resolve(fileURLToPath(new URL("../", import.meta.url)));
export const defaultPackageOutput = path.join(webRoot, ".next/distribution-sources/aes");

export async function packageAesSources(output = defaultPackageOutput) {
  await verifyAesMaterials();
  const manifest = JSON.parse(await readFile(path.join(bundleRoot, "manifest.json"), "utf8"));
  const comparison = JSON.parse(await readFile(path.join(bundleRoot, "rebuild-output/latest-comparison.json"), "utf8"));
  if (comparison.package !== manifest.npm.name || comparison.version !== manifest.npm.version
    || comparison.sourceCommit !== manifest.upstream.commit || !comparison.byteIdenticalMinifiedJavaScript
    || !comparison.correspondingExecutableAndDeclarationFiles) {
    throw new Error("Run a successful AES rebuild before packaging its source");
  }
  const target = path.resolve(output);
  if (target === webRoot || target === path.resolve(bundleRoot) || !target.startsWith(`${webRoot}${path.sep}`)) throw new Error("AES source package output must be a child of the web workspace");
  try { await stat(target); throw new Error("AES source package output already exists; use a fresh build/output directory"); }
  catch (error) { if (error.code !== "ENOENT") throw error; }
  await mkdir(target, { recursive: true });
  // Deliberate allow-list: no installed tools, npm cache, prior build products,
  // unrelated source components, credentials or local verification paths.
  const materials = [".gitattributes", ".gitignore", "README.md", "GPL-3.0.txt", "manifest.json", "comparison.json", "aes-worker-limit.cjs", "aes-source.test.mjs",
    "toolchain/.npmrc", "toolchain/package.json", "toolchain/package-lock.json", ...Object.keys(manifest.sourceFiles).map((name) => `upstream/${name}`)];
  const inventory = {};
  async function include(source, relative) {
    const destination = path.join(target, relative);
    await mkdir(path.dirname(destination), { recursive: true });
    await copyFile(source, destination);
    inventory[relative.replaceAll("\\", "/")] = sha256(await readFile(destination));
  }
  for (const relative of materials) {
    await include(path.join(bundleRoot, relative), path.join("source-root/web/distribution-sources/aes", relative));
  }
  for (const script of ["aes-rebuild.mjs", "aes-verify-source.mjs", "aes-package-source.mjs"]) {
    await include(path.join(webRoot, "scripts", script), `source-root/web/scripts/${script}`);
  }
  // The offline verifier reads the web lock; preserve it in the same layout.
  await include(path.join(webRoot, "package-lock.json"), "source-root/web/package-lock.json");
  await include(path.join(webRoot, "LICENSE"), "source-root/web/LICENSE");
  await include(path.join(bundleRoot, "GPL-3.0.txt"), "GPL-3.0.txt");
  await include(path.join(bundleRoot, "rebuild-output/latest-comparison.json"), "verified-comparison.json");
  const directions = "AES-specific source materials for @cryptography/aes 0.1.1.\n\n" +
    "See source-root/web/distribution-sources/aes/README.md for provenance, component licence and rebuild instructions.\n" +
    "Run the documented commands from source-root/. No installed tools or local cache/build directories are included.\n" +
    "verified-comparison.json records this build's successful npm/source comparison.\n" +
    "This bundle does not assert licensing clearance for the complete ARVIO/Telegram bundle or container.\n";
  await writeFile(path.join(target, "README.txt"), directions);
  inventory["README.txt"] = sha256(Buffer.from(directions));
  await writeFile(path.join(target, "source-files.json"), `${JSON.stringify(inventory, null, 2)}\n`);
  return { component: `${manifest.npm.name}@${manifest.npm.version}`, files: Object.keys(inventory).length, output: target };
}

if (process.argv[1] && path.resolve(process.argv[1]) === fileURLToPath(import.meta.url)) {
  const args = process.argv.slice(2);
  if (args.length && !(args.length === 2 && args[0] === "--output")) throw new Error("Usage: aes-package-source.mjs [--output fresh-directory]");
  console.log(JSON.stringify(await packageAesSources(args[1] ?? defaultPackageOutput), null, 2));
}
