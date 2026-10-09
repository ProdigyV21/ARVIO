# Watchlist membership sync

Cloud snapshots retain `watchlistByProfile` for item metadata and add
`watchlistChangesByProfile` for explicit membership operations:

```json
{
  "watchlistByProfile": { "profile-id": [] },
  "watchlistChangesByProfile": {
    "profile-id": {
      "movie:123": { "updatedAt": 1791100000000, "removed": true }
    }
  }
}
```

Keys include media type and TMDB ID. Events belong to one profile. Higher event
timestamps win; removal wins a tie. An explicit re-add advances beyond the last
observed event. Artwork enrichment, list ordering, old `addedAt` values, missing
items and ordinary provider refreshes are not membership operations.

Merge membership events first, then merge item metadata and filter removed
items. Keep the latest event for each item even after a successful upload: an
offline device can return much later. An empty list must still carry its events.
Never infer historical removals from an old empty or partial snapshot.

Android writes item data and membership events in one DataStore edit, exports
both from one snapshot, and merges at import time. Explicit edits during a
restore still invalidate sync, and an upload only clears the dirty revision it
actually captured. Web retains failed actions in an account/profile-scoped
outbox and retries their original timestamps, including after reload.

The Netlify backend retains events when an old client omits them, and uses
conditional canonical blob writes so concurrent pushes reload and re-merge
instead of dropping a peer's operation. Profiles without events retain legacy
replacement behavior for legacy-only clients. Updated clients identify support
by sending a per-profile changes map, even when it is empty.

## Rollout

Ship the backend and both clients together. Older Android clients cannot apply
removal events to their own local union cache; users need the updated app for
that behavior. Historical removals were never recorded, so a title already
restored by the old bug must be removed once using an updated client. The active
build uses Netlify account sync; this change does not migrate or modify the
disabled Supabase mirror.

Regression coverage includes stale and legacy uploads, the last item being
removed, explicit re-adds, equal timestamps, isolated profiles/media types,
concurrent writes, interrupted web uploads, and Android DataStore persistence.
