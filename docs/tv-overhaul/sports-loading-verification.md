# Sports loading and presentation verification

## Changes

- Keep the empty state loading until guide, metadata, broadcaster matching and catalogue construction have completed.
- Distinguish unavailable event artwork from an actual absence of channel matches; allow retry without leaving Sports.
- Stream the cached guide in 1,024-channel batches instead of building and sorting complete 48-hour schedules in 128-channel batches. Shared XMLTV aliases are resolved together; channel-specific corrections still win.
- Preserve a fresh disk artwork cache after a failed network refresh, using wall-clock cache age rather than device uptime.
- Match country suffixes in event broadcaster listings (for example ESPN 3 Netherlands) to equivalent provider labels without conflating channel numbers or other countries.
- Present Featured live, Upcoming highlights, then complete per-sport rows. Upcoming highlights prioritize competition/broadcast reach, while each sport's upcoming events remain chronological. This is editorial ranking, not measured viewership.
- Keep event-specific artwork required. No stock sports images or unverified fuzzy team matches were introduced. Broadcast-listing candidates remain labelled separately from guide matches.

## Verification (10 September 2026)

- Android: 43 sports/database tests passed, including shared aliases, provider corrections, source isolation, match qualifiers and local-calendar boundaries.
- Web: 29 sports tests passed; TypeScript check passed.
- Browser: actual SportsGuidePane with real metadata and fixture channels rendered at 1672, 768 and 390 pixels. Artwork loaded, channel picker opened, no document-width overflow. These are UI tests, not real-provider playback tests.
- Sideload release build passed. Certificate SHA-256 matches the existing release: `9778d7533d4bc1aee80c1d2d7043fb22cba3b79cd11911d3b131c1316d5f17c1`.
- Installed as an update on the TCL, preserving settings. Post-update TV timing/screenshot verification was paused when YouTube became active. No new on-device performance result is claimed yet.

The original TV page eventually populated after its lengthy local-guide scan. Its initial no-match message therefore did not establish that the user's subscription had no sports events.
