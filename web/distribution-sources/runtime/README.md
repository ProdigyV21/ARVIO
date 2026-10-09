# Linux runtime distribution materials

`manifest.json` pins the Node/Debian image and the Linux glibc libvips package.
It is a component inventory/source plan, not a legal determination or a claim
that all image contents have ARVIO's Apache-2.0 license.

Run these during packaging, not in a deployed application:

1. In the exact pinned Node base, **before installing build-only OS packages**,
   run `node scripts/runtime-collect.mjs <output>/runtime`. This records actual
   Debian binary and source versions and preserves installed copyright files,
   common licenses, Node's combined license, and npm/Yarn notices.
2. After Linux `npm ci`, run
   `node scripts/runtime-fetch-sources.mjs <output>/runtime`. It checks the
   installed sharp/libvips package and native versions against the manifest,
   fetches upstream notices, the exact Node source with its official SHA256,
   sharp/libvips build scripts, source archives and patches, and the exact
   Debian source files (including `.dsc`, original sources and Debian patches).
   Debian source-file SHA1 addresses and `.dsc` SHA256 records are checked.
   No fetched scripts are executed. SHA256/size/URL records accompany every
   downloaded file. All41 Node/native/notice resources are pinned to SHA256
   hashes from the actual upstream download audit, not only versioned URLs.
   Failed, changed or incomplete downloads fail packaging.
3. Run `node scripts/runtime-asset-inventory.mjs <output>/runtime` to inventory
   public assets and retain the separately licensed channel-metadata notice.
4. Include the generated directory in the public corresponding-source bundle
   distributed with the exact image. Preserve it for recipients rather than
   relying only on mutable third-party URLs or short-lived CI artifacts.

Large source archives are generated build artifacts, not checked into Git.
Source archives carry their original license/copyright files. Installed Debian
common licenses include GPL/LGPL texts; the downloader also includes the native
MPL text and upstream native-component license summary. The libvips upstream
recipe applies patches, edits build definitions, and statically incorporates
dependencies into a dynamically loaded shared library. Both the original
archives and the build recipe/patches are retained. The recipe uses external
build tools and regenerates a Rust lockfile; this packaging mechanism does not
assert a bit-for-bit reproducible rebuild or independently audit every
upstream-generated intermediate file.

Runtime collection verifies Node22.23.3, Debian12 and linux/amd64. Updating a
base or native dependency requires updating this manifest and checking actual
runtime inventories, source availability, notices, and the built image again.
The OCI application license label must describe mixed image licensing rather
than declaring the entire Node/Debian/native/browser image Apache-2.0.
