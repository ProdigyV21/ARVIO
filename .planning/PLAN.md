# Fix Plan: Settings, Subtitles, and Episode Scrolling Issues

## Issue 1: Switch Profile Button Not Working in Settings

**Root Cause:** In `AppNavigation.kt:181-192`, the `SettingsScreen` is called without passing the `onSwitchProfile` callback. The button exists but has no navigation action.

**Fix Location:** `app/src/main/kotlin/com/arflix/tv/navigation/AppNavigation.kt:191`

**Change:**
```kotlin
// Add onSwitchProfile callback to SettingsScreen call
onSwitchProfile = {
    navController.navigate(Screen.ProfileSelection.route) {
        popUpTo(Screen.Home.route) { inclusive = true }
    }
},
```

---

## Issue 2: Remove ARVIO Account Sign In Button

**Root Cause:** The "ARVIO Account" row is displayed in the Accounts section but should be removed.

**Fix Locations:**
1. `SettingsScreen.kt:239` - Change max index from 2 to 1 (Accounts: 2 items now)
2. `SettingsScreen.kt:291-296` - Remove case 0 for Account action, shift Trakt to 0, Switch Profile to 1
3. `SettingsScreen.kt:885-893` - Remove the `AccountActionRow` for ARVIO Account
4. `SettingsScreen.kt:400` - Remove `isLoggedIn` and `accountEmail` props from `AccountsSettings` call

---

## Issue 3: Subtitle Auto-Selection Defaults to English

**Root Cause:** In `PlayerViewModel.kt:318-319`, the `getDefaultSubtitle()` function defaults to "English" when no preference is set:
```kotlin
prefs[DEFAULT_SUBTITLE_KEY] ?: "English"  // Wrong default
```

**Fix Location:** `app/src/main/kotlin/com/arflix/tv/ui/screens/player/PlayerViewModel.kt:318-322`

**Change:**
```kotlin
// Default to "Off" instead of "English"
prefs[DEFAULT_SUBTITLE_KEY] ?: "Off"
```

This ensures:
- Fresh install = No subtitles auto-selected
- User sets "English" in settings = English subtitles auto-selected
- User sets any other language = That language auto-selected

---

## Issue 4: Last Episode Outside Screen in Details Page

**Root Cause:** Looking at the scroll logic in `DetailsScreen.kt:1050-1094`, the `extraOffset` calculation tries to handle end items, but the issue is that when navigating to the last few episodes, they may not be fully visible.

**Analysis:**
- `contentPadding` has `end = 240.dp` which should provide space
- The scroll calculates `maxFirstIndex = totalCount - visibleCount`
- For items beyond `maxFirstIndex`, it calculates `extraOffset`
- But the `extraOffset` might not be applied correctly when navigating right

**Fix Location:** `app/src/main/kotlin/com/arflix/tv/ui/screens/details/DetailsScreen.kt:1050-1094`

**Change:** Ensure the last item is always visible by:
1. Adding check for when `episodeIndex >= totalCount - 2` (last 2 items)
2. Scrolling with sufficient offset to bring item fully into view
3. Using `animateScrollToItem` with calculated offset for end items

---

## Implementation Order

1. Fix Switch Profile button (1 file change)
2. Remove ARVIO Account button (1 file, multiple edits)
3. Fix subtitle default (1 line change)
4. Fix last episode scrolling (scroll logic adjustment)

## Files to Modify

1. `app/src/main/kotlin/com/arflix/tv/navigation/AppNavigation.kt`
2. `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsScreen.kt`
3. `app/src/main/kotlin/com/arflix/tv/ui/screens/player/PlayerViewModel.kt`
4. `app/src/main/kotlin/com/arflix/tv/ui/screens/details/DetailsScreen.kt`
