# Client-Delegated Addon Catalogs

An addon manifest catalog can declare `clientDelegate` and `clientFilter`.
ARVIO resolves these using the profile's existing tracker clients or explicitly
selected home-server library. No tracker/server credentials go to the addon.
Ordinary catalogs without `clientDelegate` keep the existing addon HTTP path.

```json
{
  "type": "movie",
  "id": "personal-action",
  "name": "My Action Watchlist",
  "clientDelegate": "trakt:watchlist?type=movie",
  "clientFilter": "{\"genreIds\":[28],\"minYear\":2000,\"minRating\":6}"
}
```

## Delegation URLs

Both `trakt:watchlist?type=movie` and `trakt://watchlist?type=movie` work on
Android and web. Schemes/actions are case-insensitive. Supported actions:

- `trakt:watchlist`, `trakt:watched` (a show with watched episodes is watched).
- `simkl:plantowatch`, `simkl:completed`, `simkl:watching`, `simkl:hold`, `simkl:dropped`.
  `simkl:plan` and `simkl:watchlist` alias `plantowatch`.
- `hybrid:watchlist?tracker=trakt` or `hybrid:completed?tracker=simkl` delegates
  to that tracker. This is a tracker alias, not a merge/intersection of lists.
- `homeserver:library?source=ENCODED_LIBRARY_REFERENCE` selects one library.
  `source` accepts Android's `home_server_catalog|...` reference or web's
  `hslib:SERVER_ID:LIBRARY_ID` reference. URL-encode the entire source parameter.
  The server must already be connected in the current profile. There is no
  fuzzy "first matching server/library" fallback. A native reference in the
  catalog's `sourceRef`/`sourceUrl` is also accepted when `source` is absent.

The optional `type` is `movie`/`movies`, `tv`/`series`/`show`/`shows`, or `all`.
When absent it defaults to the manifest catalog type. Unknown schemes, actions,
media types, repeated parameters and fragments fail explicitly.

## Filters

`clientFilter` is a JSON object serialized as a string, not executable code.
Its optional fields are:

- `mediaType`: the same movie/TV aliases as `type`, or `all`.
- `genreIds`: positive TMDB genre IDs; an item must match at least one.
- `minYear` and `maxYear`: inclusive integer years from 1 to 9999.
- `minRating`: inclusive minimum rating from 0 to 10.

All specified predicates must match, in addition to the delegation URL's type.
Missing metadata does not satisfy a requested predicate. Unsupported fields,
wrong types and invalid ranges throw rather than silently returning unfiltered
results. Home-server rows use each platform's existing catalog loader: Android
matches to playable TMDB-backed catalog entries; web retains its native server
playback identities. Native items without genre metadata cannot satisfy a genre
filter. This protocol does not change either platform's playback routing.

Filtering happens before the displayed item limit. Android's returned
`nextOffset` counts consumed source entries, including rejected/unresolvable
entries; use it unchanged when requesting the next page. Web Home requests one
extra matching item to expose `hasMore`. Tracker artwork/metadata hydration is
limited to six concurrent requests and stops when the requested row is filled.
No new cloud writes or persisted credentials are introduced.
