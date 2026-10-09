# Imported collections

Import a Nuvio collection document in Settings > Catalogs, using a URL or JSON.
Each collection becomes a Home row, with a tile for each supported folder.
The existing Home layout and built-in artwork are unchanged.

Imports are stored in the active profile's catalogs and use the existing account
sync on Android and web. Re-importing the same URL replaces that pack's contents
while keeping existing titles and ordering. Removing a pack removes its rows and
folders. Built-in collection visibility uses the same profile visibility settings.

Supported sources: installed add-on catalogs (including genre), TMDB collection,
public TMDB list, TMDB discover/company/network, person cast and director credits,
public Trakt lists and public MDBList lists. Add-ons must already be installed.
Private lists and unavailable upstream services cannot be made available by importing.
Additional discover filters on person/director credit sources are rejected rather
than silently ignored. Other unsupported source providers are skipped; imports
without any supported folders are rejected. Imports are limited to 2 million
characters, 100 rows and 500 folders; duplicate identifiers are rejected.

The web collection view supports poster/landscape covers, keyboard navigation,
missing-artwork fallback and retry. Animated hero videos are not played in the web
collection dialog; their metadata is retained when syncing back to Android.

## Collection Studio

[Collection Studio](https://arvio.tv/collection-studio/) is a browser-only builder
for one Home row with one to eight folders. Name the row and folders, choose a
TMDB discovery preset, a public MDBList list URL, or a public numeric Trakt list
URL such as `https://trakt.tv/lists/12345`. The studio does not resolve Trakt
user/list slugs. It supports this small subset of the collection format, rather
than every source supported by ARVIO's importer.

Editing, JSON generation and preview-link encoding happen in the browser. The
studio does not upload the collection, use an account or store it in a backend.
The colored folder gradients are preview styles; they are not exported as
collection artwork. Titles are limited to 64 characters and descriptions to 180
characters; generated JSON is limited to 16 KiB.

Use **Copy JSON**, then paste the JSON into **Settings > Catalogs > Import Catalog
Pack / Collections** on Android. On ARVIO Web, paste it into **Settings > Catalogs
(Home Rows) > Collection** and choose **Import collections**. Import into the
profile that should receive the row. **Download JSON** saves `collections.json`
for sharing or hosting; ARVIO does not currently accept direct file uploads.
There is no one-click import from Studio.

**Copy preview link** places the draft in the URL fragment. The recipient opens
a read-only public preview and can make an editable copy or copy its JSON for
manual import. The preview link is not a raw JSON URL for ARVIO's URL importer.
Anyone with the link can read the draft's names, descriptions and public list
references; it is not encrypted. Include no credentials or private information.
Preview fragments are limited to 6,000 characters; if a draft is too long, shorten
it or share the JSON file instead.

Studio validates the supported document and URL shapes without requesting list
contents. It cannot verify that a list is public, has titles or is available.
Importing a collection does not grant access to private lists or playable media.
The sender and recipient each need their own authorized playback sources.

## Android loading

Collection cards reuse the titles and artwork already returned by TMDB lists,
discover, franchise and person-credit endpoints. ID-only sources fetch lightweight
details with a shared six-request limit. The initial page does not wait for IMDb
ratings; visible cards receive full details and ratings afterward, two at a time.
Partial pages keep source order so TV focus does not jump as requests finish.
Imported collections fetch one upstream page initially and expand on scroll;
movie/series filters, pagination and the collection's contents are preserved.

Run the collection regression tests with:

```powershell
./gradlew.bat :app:testSideloadDebugUnitTest --tests '*Collection*'
```

`CollectionLoadingBenchmarkTest` includes a deterministic cold/warm request-count
benchmark. Set `ARVIO_LIVE_COLLECTION_BENCHMARK=1` to additionally measure two public
lists from the attributed Kaptain import example (requires the configured TMDB key). JVM timings
exclude image decoding and UI rendering. The opt-in Android instrumentation test
`CollectionLoadingDeviceTest`, with argument `collectionLive=true`, measures actual
repository loading, poster downloads/decoding, warm reloads and pagination.

## Built-in Collections and Reuse

ARVIO's built-in collections use ARVIO-authored TMDB queries and TMDB collection
identifiers, not a repackaged third-party collection export. Service identities
are retained during upgrades; imported packs remain user-managed and keep their
own names and sources. Disabling the built-in set also hides newly added defaults.

No explicit reuse license was found on `ImKaptain/Kaptain-Collection` or
`ImKaptain/nuvio-art` when checked on 2026-09-28. The existing attributed example
file has not been renamed or stripped of its credits and is not bundled as an
app default. Obtain the creator's permission before redistributing that pack or
its artwork; public access is not a substitute for permission.

The collection detail screen has a bounded artwork preview of the focused title.
Ratings identify their source, and budgets are shown in USD only for movies with
a known positive budget. Missing figures are omitted, not estimated. Preview
artwork waits briefly for focus to settle; the grid's focus feedback is immediate.
The IMDb badge uses the same SVG component as Home. Service logos use Home's
region-aware provider lookup and bundled brand assets, requested only for the
settled preview and reused during navigation. Unknown services are not guessed;
TMDB-only scores retain their TMDB label rather than an IMDb badge.

## Collection Credits

The existing `collections/kaptain-arvio-trimmed.json` import example originates
from **Kaptain (ImKaptain)**. Credit for the original collection curation belongs
to [Kaptain Collection](https://github.com/ImKaptain/Kaptain-Collection), and
associated artwork is credited to [nuvio-art](https://github.com/ImKaptain/nuvio-art).
The example retains its original source references. This credit is not a claim
of permission, endorsement, or ownership by ARVIO.

The replacement built-in set is independently authored for ARVIO and uses TMDB
metadata and artwork, not the Kaptain pack.
