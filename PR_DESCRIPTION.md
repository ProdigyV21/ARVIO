# PR Description: Mobile Fullscreen UX Refactor

## Summary
Refactor the mobile UI to remove forced fullscreen mode on non-player screens. All home, browse, and profile screens now display standard transparent status and navigation bars, while the video player retains its immersive edge-to-edge behavior.

## Problem
The app was using `Theme.Leanback` as a parent for all devices, which forces `android:windowFullscreen=true`. This resulted in a sub-optimal mobile experience where system bars were permanently hidden or poorly integrated, making the app feel "trapped" and inconsistent with standard Android UX.

## Solution
1. **Conditional Theming**: Introduced `Theme.ArflixTV.Mobile` which overrides the fullscreen flag. 
2. **Edge-to-Edge with Insets**: Switched the root layout to use `WindowCompat.setDecorFitsSystemWindows(window, false)` (Edge-to-Edge) globally.
3. **Manual Inset Handling**: Used Compose `systemBarsPadding()` on the root `Column` for mobile only. This ensures UI content stays within the safe area while the background gradient bleeds seamlessly behind the transparent bars.
4. **Dynamic Fullscreen**: Added a `DisposableEffect` in `PlayerScreen` to hide system bars only during playback on mobile.
5. **Aesthetics**: Configured transparent bar colors with white (light) icons to match the dark Arctic Fuse theme.

## Changes

### `app/src/main/res/values/themes.xml`
- Added `Theme.ArflixTV.Mobile` inheriting from the main theme but with `windowFullscreen=false` and transparent bar colors.
- Updated `Theme.ArflixTV.Splash` to use the mobile theme as `postSplashScreenTheme`.

### `app/src/main/kotlin/com/arflix/tv/MainActivity.kt`
- Implemented logic to override the splash theme back to the Leanback fullscreen theme only on TV devices.
- Configured window flags for transparent status/navigation bars and white icon appearance.
- Re-ordered root modifiers: `.background()` -> `.systemBarsPadding()` to allow the background to fill the status/nav bar areas.

### `app/src/main/kotlin/com/arflix/tv/ui/screens/player/PlayerScreen.kt`
- Added `DisposableEffect` to manage immersive mode specifically for the mobile video player, ensuring it returns to normal once dismissed.

## Verification
- [x] Home screen shows status and navigation bars on mobile.
- [x] Navigation bar is transparent and shows app background.
- [x] Content is correctly offset by `systemBarsPadding`.
- [x] Video player still enters immersive fullscreen on mobile.
- [x] TV behavior remains immersive fullscreen globally.
