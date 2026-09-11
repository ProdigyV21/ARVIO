# Native Source Previews

## Provider API

`StreamSource.preview: StreamPreviewMetadata? = null` is an optional explicit descriptor in
`com.arflix.tv.data.model`. `HomeServerRepository` attaches server, account/profile, item, selected
media-source/part ID, version, ETag, duration, and authentication without any extra network request.

`NativeSeekPreviewAdapter(metadata, playbackClient, headers = emptyMap(), maxWidthPx = 480)`
has `suspend load(positionMs: Long): NativePreviewFrame?` and `close()`. A convenience constructor
takes a `StreamSource` with non-null preview metadata. Construction does no I/O. A nonblank
`mediaVersion` is required. Call only from the independent preview path, not playback preparation.

Successful results contain `bitmap`, `requestedPositionMs`, `cueStartMs`, `cueEndMs`, `cacheIdentity`,
and nullable `actualPositionMs`. Cue ends are exclusive, on the playback timeline. Images are validated
against the source's cue interval, not claimed to have a decoded presentation timestamp. The caller
owns returned bitmaps and must gate stale results by source generation/request ID. Close on source
replacement/disposal; cancel the load coroutine on obsolete targets. `null` is unavailable, not loading.

Use `nativePreviewCacheIdentity(metadata)` for caches, never descriptor `toString()`. It hashes stable
identity fields and omits request headers. Generic manifest URLs are hashed in full because their query
can select a different media version; signed URL rotation can therefore miss a cache without causing
cross-version reuse. `timelineOffsetMs` is original media time minus player time. Resume alone is not
an offset. The adapter clips a cue crossing player time zero and never seeks playback.

## Capabilities

- Jellyfin: authenticated item DTO discovery, exact selected media-source `Trickplay` dictionary,
  width selection, grid sheets, final cue clamping and `MediaSourceId` on every tile request.
- Plex: JSON metadata discovery requires a matching single-part media variant with existing `sd`
  indexes. Retrieves the BIF index and selected image bytes. Multipart playback is unsupported.
- Emby: authenticated item verification and `/Videos/{Id}/index.bif` for a single matching media
  source only. The documented endpoint has no media-source selector, so alternate versions are
  refused. No chapter-image approximation, ThumbnailSet-specific adapter, or generation fallback.
- Explicit WebVTT: finite image cues, relative URLs, `xywh=` and `xywh=pixel:` sprite crops, BOM,
  CRLF and cue identifiers. Gaps stay gaps; overlapping/reversed intervals are rejected.
- Explicit image HLS: follows one `EXT-X-IMAGE-STREAM-INF` child, then requires `EXT-X-IMAGES-ONLY`
  and `EXT-X-ENDLIST`. Supports individual images and `EXT-X-TILES`, including a partial final
  sprite. Ordinary video playlists and I-frame variants are not image sources.
- Explicit BIF: version zero, unsigned little-endian indexes, real index timestamps and bounded
  byte-range retrieval. Without a known original duration, the final unbounded image is omitted.

No generic addon DTO mapping is added: VTT/BIF/image-HLS descriptors must be explicitly attached by
the source integration. The player can separately opt into image-track discovery on a known VOD HLS
manifest. It must supply a stable media version and correct timeline, and must not trigger new server
transcoding through speculative playback URLs.

## Limits and Authentication

The adapter retains one index and one compressed sprite, no decoded-image or disk cache. Limits are
20,000 cues, seven-day original timeline, 1 MiB manifests, 6 MiB compressed images/full BIF fallback,
32,768 pixels per source dimension and 64 million source pixels. Region decoding avoids allocating
the full sprite bitmap. Returned images aspect-fit within a square of the requested width, clamped
to 64-640 pixels. The provider remains responsible for UI/cache sizing.

Loads have a six-second deadline; each HTTP call has a three-second deadline and two-second
connect/read timeouts. There are no automatic HTTP retries, and two failed loads trip the adapter's
failure limit. Concurrent calls do not queue. Missing/invalid manifests are remembered for the
adapter lifetime. A cold Plex/Emby BIF load uses up to four GETs before redirects; a range-ignoring
server is accepted only for an initial complete BIF of at most 6 MiB. Subsequent range offsets,
lengths, total length and available ETag/Last-Modified validators must match.

The supplied playback client's cookie jar, TLS and interceptors are retained. Discovery requests
explicitly ask for JSON. Descriptor headers override inherited headers. Generic descriptors without
`serverUrl` use only their own headers; setting `serverUrl` explicitly declares the origin allowed to
receive inherited playback headers. Requests, manifest children and redirects must remain on that
origin, with no embedded URL credentials; HTTPS downgrades and cross-origin redirects are rejected.
At most two same-origin redirects are followed. URL query tokens are not copied into child URLs;
authenticated children need headers/cookies or explicit signed URLs in the manifest.

Unsupported: encrypted image playlists, live/sliding playlists, nonzero HLS media sequences,
discontinuities, byte-range HLS images, MPEG-TS-mapped VTT, DASH, external-origin CDN children,
animated/AVIF images, server generation/refresh endpoints, and preview inference from addon labels.
JPEG, PNG and WebP image bounds are checked before decode. Server availability, real image content,
HDR conversion and physical-device performance require separate genuine-server/device validation.

## Reference Protocols

- Jellyfin: https://github.com/jellyfin/jellyfin/blob/master/Jellyfin.Api/Controllers/TrickplayController.cs
- Plex BIF retrieval: https://developer.plex.tv/pms/
- Emby BIF: https://dev.emby.media/reference/RestAPI/BifService/getVideosByIdIndexBif.html
- BIF file format: https://developer.roku.com/dev/docs/bif-file-creation

Focused JVM suites: `PreviewStoryboardTest`, `NativePreviewIndexesTest`, `PreviewHttpClientTest`.
These use synthetic metadata and a loopback HTTP fixture with no external credentials or services.
