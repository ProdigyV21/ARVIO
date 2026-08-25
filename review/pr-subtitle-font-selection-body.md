## Summary
- Adds selectable subtitle fonts: System, Noto Sans, Atkinson Hyperlegible, Lexend, and Roboto Condensed.
- Bundles the four open-licensed font families and their SIL Open Font License texts.
- Adds the font control to TV/D-pad and mobile subtitle settings.
- Stores the choice per profile and includes it in the existing cloud settings snapshot.
- Applies font, colour, style, size, and embedded-style preferences through one Media3 subtitle styling path during both PlayerView creation and Compose updates.

## Behaviour
- Unknown or removed stored values safely fall back to System.
- Bold and Background styles request the bold face of the selected family; Normal uses regular weight.
- With Stylized Subtitles enabled, ASS/SSA embedded styling remains enabled and the selected font is the fallback for plain cues.
- With Stylized Subtitles disabled, the selected font is applied uniformly.

## Fonts and licensing
- Noto Sans — SIL Open Font License 1.1
- Atkinson Hyperlegible — SIL Open Font License 1.1
- Lexend — SIL Open Font License 1.1
- Roboto Condensed — SIL Open Font License 1.1
- Font files and licence texts are sourced from the official `google/fonts` repository.

## Testing
- Focused `SubtitleFontOptionTest`: fallback, complete cycle order, and stored-value round trips.
- Full `:app:testSideloadDebugUnitTest` suite.
- `:app:compileSideloadDebugKotlin`.
- `:app:assembleSideloadDebug`.
- APK inspection confirms all font and OFL assets are packaged.
- `git diff --check` and added-line security scan.

## Scope
Android subtitle rendering/settings only. No backend, app-version, IPTV, catalog, or unrelated player-behaviour changes.
