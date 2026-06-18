# YouTube Trailers Design

## Goal

Make ARVIO choose dependable YouTube trailers for both movies and TV series using the data sources already in the Android app.

## Approach

Trailer lookup stays inside `MediaRepository`, but trailer ranking moves to a small pure Kotlin resolver that can be tested without Android or network calls. TMDB remains the source of truth for trailer metadata. The app will not scrape YouTube search results or require a YouTube Data API key in this pass.

## Behavior

- Use only TMDB videos where `site` is YouTube and `key` is non-blank.
- Prefer `Trailer` over `Teaser`, `Opening Credits`, `Featurette`, `Clip`, and every other YouTube video type.
- Prefer official videos over unofficial videos within the same type.
- Prefer videos whose language matches `contentLanguage`, then English, then language-agnostic entries.
- Prefer names that look like real trailers, such as "Official Trailer", "Main Trailer", "Final Trailer", and "Trailer".
- Avoid weak names such as "behind the scenes", "bloopers", "interview", "recap", and "promo" when better candidates exist.
- For TV series, first try show-level TMDB videos. If no playable YouTube candidate exists, try season-level videos, newest regular season first and season 0 last.
- Cache resolved trailer keys per media id and language for the normal repository cache TTL.

## Non-Goals

- No YouTube search scraping.
- No new API key or backend requirement.
- No UI redesign in this trailer step.
- No changes to ExoPlayer playback extraction unless verification shows a separate playback defect.

## Verification

- Unit tests cover ranking, language fallback, non-YouTube filtering, and season fallback ordering.
- `assembleSideloadDebug` proves the Android app still compiles.
- A PR is created from `codex/youtube-trailers` after tests and build pass.
