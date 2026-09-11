# Android TV search refinement

## Root causes

- Android put up to three actor filmography rows before title matches. The webapp renders the title-search response directly.
- The first Android title row interleaved movie and TV results, discarding the cross-type relevance order. An unrelated movie could precede an exact series match.
- Android issued the same multi-search request twice, then waited for as many as 56 logo lookups before publishing results.
- Discover could concurrently start 60 logo lookups while the user was typing.
- Horizontal navigation scrolled each selected card toward the start of the row, even when it was already visible. Moving vertically discarded the horizontal selection.
- Substring-based smart-query detection could treat actual titles such as "Best in Show" as a discovery request.

## Changes

- Titles and people now come from one metadata search response. Title results appear first; filmographies remain available below them.
- Exact matches precede partial matches across both movies and shows. Normalization handles punctuation, accents, articles and Unicode. Provider relevance order is retained for equivalent matches, rather than arbitrarily preferring a newer remake or penalizing documentaries.
- Publish selectable results immediately; load logos separately with three concurrent requests and per-request timeouts. Actor-credit fallbacks run separately as well.
- Match the web's 260 ms debounce, support single-letter titles, cancel obsolete queries and competing discovery work, and reuse the current query's results on repeated submission.
- Smart discovery only recognizes explicit discovery phrases.
- Scroll only to reveal an offscreen or clipped card. Preserve row selections, keep enrichment from moving focus, consume handled key-up events and put actual focus in the results area.
- Keyboard Search enters available results; Down while loading queues entry. New queries reset their viewport. Titles can use two lines, with type/year shown below.
- Failed title requests display the error state rather than claiming there are no matching titles.

## Verification

Full unit suites: sideload 807 tests (806 passed, one skipped); Play 808 tests (807 passed, one skipped). No failures or errors. Includes 17 new repository, ranking and ViewModel tests.

All 13 TV/touch UI regression tests passed, including exact-title-first ordering, visible-card stability, row memory, saved-screen restoration, late logos, new-query reset, keyboard submission, pending-result entry, actor queries, filter navigation, RTL and touch-layout sanity checks. Tests use an isolated debug application ID, not an uninstall or replacement of the production app. The separate live smoke test also passed.

Live public-metadata smoke test on an API 31 Android TV emulator (1280 x 720, approximately 2 GB RAM):

| Query | Results ready, including debounce | Title results |
| --- | ---: | ---: |
| Loki | 903 ms | 10 |
| Matlock | 605 ms | 3 |
| Breaking Bad | 538 ms | 14 |
| Alien | 550 ms | 18 |

All four showed an exact-title match first, accepted one Down press to select it, and opened its details callback on Select. For Matlock, the provider ordered the 1986 series before the 2024 series; both were adjacent in the first row with their years visible. Screenshots were inspected locally, not added to public marketing assets.

These timings measure results becoming usable, not every image finishing. They are four observed searches, not a network-latency guarantee or a release-build performance benchmark. Live test is opt-in via instrumentation argument `searchLive=true`. No physical TV was used.

The first live attempt correctly failed with HTTP 401 because the isolated checkout used the default placeholder API key. The successful run used the existing ignored local secrets configuration. No credentials are included in this change.
