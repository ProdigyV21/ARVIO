# Public asset inventory and identifying marks

ARVIO declares Apache-2.0 for the source project. The generated
`public-assets.json` records the exact paths, byte sizes and SHA256 hashes of
the bundled public files. This project-level declaration is not an independent
guarantee of original authorship or of permission to sublicense third-party
artwork, trademarks or badges. Where original provenance is not recorded here,
the inventory says so explicitly; it does not infer infringement or ownership.
No asset features are removed by the inventory.

`public/data/channel-logos.json` contains metadata generated from the
[iptv-org API](https://github.com/iptv-org/api), whose repository declares the
Unlicense. Only names, identifiers and remote image URLs are bundled, not
channel-logo image binaries, stream lists or streams. The accompanying
`licenses/iptv-org-Unlicense` preserves that upstream metadata license. Logos
remain identifying marks/artwork of their respective broadcasters; the
metadata license does not grant trademark or artwork rights. The original
generator and behavior are described in `scripts/update-channel-logo-directory.mjs`
and `docs/channel-logo-fallbacks.md` in ARVIO's source project.

Service logos and store/install badges are identifying marks. Their presence
does not imply ownership, an endorsement, or a trademark license. The generated
inventory labels them separately from project graphics, profile avatars,
sports illustrations and translations.
