import { copyFileSync, existsSync, mkdirSync, readFileSync, readdirSync, writeFileSync } from "node:fs";
import { dirname, join, relative, resolve } from "node:path";
import { fileURLToPath } from "node:url";

// Packaging-time collection, including vendored licenses (e.g. Next's compiled
// dependencies) and nested npm dependencies. No application env/config is read.
const webRoot = fileURLToPath(new URL("..", import.meta.url));
const dependencyRoot = join(webRoot, "node_modules");
const output = resolve(process.argv[2] || join(webRoot, ".next", "third-party-notices"));
if (!existsSync(dependencyRoot)) throw new Error("Install dependencies before collecting their notices.");
if (output === dependencyRoot || output.startsWith(dependencyRoot + "/") || output.startsWith(dependencyRoot + "\\")) {
  throw new Error("Notice output must not be inside node_modules.");
}
mkdirSync(output, { recursive: true });
const packages = [];
let notices = 0;

function walk(directory) {
  for (const entry of readdirSync(directory, { withFileTypes: true })) {
    if (entry.isSymbolicLink()) continue; // Never follow paths outside this install.
    const source = join(directory, entry.name);
    if (entry.isDirectory()) {
      if (entry.name !== ".bin" && entry.name !== ".cache") walk(source);
      continue;
    }
    if (!entry.isFile()) continue;
    const rel = relative(dependencyRoot, source);
    if (/^(?:(?:THIRD[-_]PARTY[-_])?LICEN[CS]ES?|COPYING|(?:THIRD[-_]PARTY[-_])?NOTICES?|COPYRIGHT)(?:[._-]|$)/i.test(entry.name) || /\.(?:LEGAL|LICEN[CS]ES?|NOTICES?)(?:[._-]|$)/i.test(entry.name)) {
      const destination = join(output, "files", rel);
      mkdirSync(dirname(destination), { recursive: true });
      copyFileSync(source, destination);
      notices++;
    } else if (entry.name === "package.json") {
      try {
        const info = JSON.parse(readFileSync(source, "utf8"));
        if (typeof info.name === "string" && typeof info.version === "string") {
          packages.push({ name: info.name, version: info.version,
            license: info.license || info.licenses || "See package notices",
            repository: info.repository || null, homepage: info.homepage || null,
            path: rel.replaceAll("\\", "/") });
        }
      } catch { /* Third-party test fixtures can contain intentionally invalid JSON. */ }
    }
  }
}

walk(dependencyRoot);
packages.sort((a, b) => a.path.localeCompare(b.path));
writeFileSync(join(output, "packages.json"), JSON.stringify(packages, null, 2) + "\n");
writeFileSync(join(output, "README.txt"),
  "ARVIO Web third-party notices\n\n" +
  "files/ preserves installed dependency copyright/license/notice files, including vendored and nested dependencies.\n" +
  "packages.json records installed package names, versions, declared licenses and upstream source repositories/homepages. Build-only dependencies may also be included.\n" +
  "This collection is a packaging aid, not a declaration that all dependencies share ARVIO's license.\n" +
  "Review source-availability and other obligations for copyleft/embedded-code dependencies before public redistribution; copying notices alone does not certify compliance.\n" +
  "ARVIO source and original project license: https://github.com/ProdigyV21/ARVIO\n");
console.log(`Collected ${notices} dependency notices and ${packages.length} package metadata records.`);
