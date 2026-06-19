# Majo Stream Kodi Catalog AIOStreams Stream Summary Design

## Summary

The TV catalog should keep the Kodi-inspired layout from `kodi-catalog-v2`: compact list on the left, full-screen fanart, prominent poster and metadata on the right, and no catalog-side action buttons. Stream quality badges should be enriched from AIOStreams after the selected row is already visible, so browsing remains immediate.

## Goals

- Show stream availability signals in catalog preview without blocking list rendering.
- Surface the best known stream traits: resolution, HDR/Dolby Vision, audio format/channels, cached/debrid readiness, source count, and largest useful size.
- Reuse the same stream interpretation rules as the existing player/source selector so catalog badges match playback choices.
- Keep D-pad navigation smooth on Google TV 4K Streamer.

## Non-Goals

- Do not open action controls in the catalog view.
- Do not resolve every visible catalog item at once.
- Do not guarantee exact stream properties before the user focuses an item.
- Do not redesign the full details page in this pass.

## User Experience

The catalog renders immediately from metadata already available in the collection item: title, year, runtime or season info, IMDb rating, poster, backdrop, and overview. When the focused row settles briefly, the preview requests stream summary data for that title. Until the stream summary arrives, the preview shows temporary loading labels such as `Sources ...` or no stream badges.

When stream summary data arrives, the preview displays compact flags such as:

- `4K`
- `DV` or `HDR10`
- `Atmos`, `TrueHD`, `7.1`, or `5.1`
- `Cached` or `Debrid`
- `12 sources`
- `52 GB max`

Rows may show one or two tiny availability hints only when cached data already exists. The richer badges live in the preview area so the list stays compact.

## Data Flow

1. Collection item focus changes.
2. Preview switches immediately to local metadata and artwork.
3. A short debounce starts, scoped to the currently focused media id.
4. The stream summary loader checks memory cache, then persisted cache.
5. If no fresh cache exists, it resolves streams through the existing stream repository/AIOStreams path.
6. Returned streams are converted into a small `StreamAvailabilitySummary`.
7. If the focused item has not changed, the preview applies the summary; otherwise the stale result is ignored.

For movies, lookup uses the movie IMDb id when available. For series, lookup uses the next in-progress episode when progress exists; otherwise it uses season 1 episode 1.

## Stream Summary Model

The catalog should not pass full source lists into UI state. It should expose a compact immutable summary:

```kotlin
data class StreamAvailabilitySummary(
    val bestResolution: String?,
    val bestVisualTag: String?,
    val bestAudioTag: String?,
    val isCachedOrDebridReady: Boolean,
    val sourceCount: Int,
    val maxSizeLabel: String?,
    val bestSourceLabel: String?,
    val loadedAtMs: Long
)
```

The summary should be derived from the same parsing and scoring logic used by the source selector/autoplay planner. If needed, shared source presentation utilities should be extracted from the source selector into a non-Compose helper so tests can cover them without UI dependencies.

## Performance Rules

- Debounce focus changes before stream lookup.
- Cancel or ignore stale lookups when the user scrolls quickly.
- Limit concurrent catalog stream summary requests.
- Prefer warm cached summaries over live network calls.
- Persist summaries per profile, media type, id, and episode key.
- Keep cache TTL shorter than metadata cache because stream availability changes more often.
- Never block fanart, list navigation, or details navigation on stream summary loading.

## Error Handling

If AIOStreams or any stream addon fails, the catalog keeps the metadata preview and hides stream-specific badges. Errors should be logged as source-resolution failures, but they should not show blocking dialogs in the catalog. If cached data exists but live refresh fails, stale cached data can remain visible with no user-facing error.

## Testing

- Unit-test stream summary scoring for 720p, 1080p, 4K, HDR10, Dolby Vision, Atmos, TrueHD, 7.1, 5.1, cached/debrid, source count, and size tie-breaks.
- Unit-test focus debounce and stale result ignoring.
- Unit-test movie and series lookup keys, including next-episode and S1E1 fallback.
- Unit-test cache hit, stale cache, refresh failure, and no-stream cases.
- Manually test on Google TV 4K Streamer that fast D-pad scrolling stays smooth and badges fill in without jank.

## Decision

For catalog rows, the recommended first pass is preview-only stream badges. Row-level badges should be limited to already-cached summaries because live row enrichment across many items can make list scrolling feel slower.
