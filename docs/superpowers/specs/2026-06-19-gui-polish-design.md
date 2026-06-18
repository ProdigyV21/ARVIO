# 2026 GUI Polish Design

## Goal

Move ARVIO's Android TV interface from a very flat black-and-white skin toward a premium 2026 media-center feel: cinematic like Netflix, organized like Plex, and TV-remote friendly like Kodi.

## First Pass Scope

This pass focuses on the home experience because it controls the first impression and most repeated navigation:

- Global skin tokens: color depth, focus treatment, card radius, and typography rhythm.
- Home hero: richer cinematic scrims and more readable metadata.
- Navigation chrome: top bar and sidebar glass treatment with clear selected/focused states.
- Media cards: subtle image scrim, better resting border, stronger but stable focus.

## Design Principles

- Keep the app media-first. Artwork and trailers remain the visual lead.
- Avoid a one-note palette. Keep black as the canvas, but add restrained teal, amber, and cool-glass accents.
- Keep TV focus instant and unambiguous without resizing rows or causing layout shifts.
- Keep cards tight and modern; use smaller radii and hairline borders rather than large soft cards.
- Do not add marketing copy or tutorial text inside the app.

## Verification

- Unit token tests cover the baseline skin invariants.
- `testSideloadDebugUnitTest` must pass.
- `assembleSideloadDebug` must pass.
- Manual code review checks that text remains inside fixed surfaces and no large decorative blobs/orbs were introduced.
