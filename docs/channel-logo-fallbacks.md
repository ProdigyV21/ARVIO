# Channel logo fallbacks

Provider logos are attempted first. Missing or failed images fall back to the bundled channel directory, using an exact EPG/channel ID or an unambiguous normalized name. Explicit country prefixes disambiguate names; channel numbers, regional names and +1 variants are retained. Unknown or ambiguous channels keep a placeholder rather than borrowing an incorrect logo.

The directory is generated from [iptv-org API](https://github.com/iptv-org/api) channels and logos metadata. Its repository is distributed under the Unlicense. Logos remain the property of their respective broadcasters; this metadata license does not grant trademark or artwork rights. Only metadata is bundled, not image binaries or streams. Image requests go to the listed image hosts, not a lookup server.

Run `node scripts/update-channel-logo-directory.mjs` from the repository root to refresh both platform snapshots. Review the resulting diff and run the logo tests before committing. The generator excludes closed/adult channels, inactive logos and feed-specific images. It retains at most two fallback URLs per channel.

Android reads the local directory off the main thread on the first missing/broken image. Fallback image requests identify ARVIO in the User-Agent, as Wikimedia rejects generic okhttp requests. Provider-image headers are unchanged. The web app fetches the static directory once on demand. Both cache failures for ten minutes (bounded to 1,024 URLs) to avoid repeated image requests while scrolling. Neither implementation probes streams or makes provider API requests for logo matching.

Recently watched and Favorites use the same resolved, visibility-filtered channel lists for the guide and sidebar count. Saved IDs are retained in history, but missing, hidden and restricted entries are not counted as available channels. Recent channels are newest first, independently of database query order.
