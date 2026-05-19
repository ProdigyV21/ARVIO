## Title: Extend ROYGBIV Accent Color to Button Focus States & Rename Setting

### Summary

Extends the user-configurable ROYGBIV accent colour (from Settings → Appearance → Accent Color) beyond focus border highlights to include full button/chip focus backgrounds and selected text/icon colours. Also renames the setting from "Focus Border Color" → "Accent Color" to reflect its expanded role.

### Changes

#### ROYGBIV Accent Extension (6 Composables)

| Composable | File | Behaviour |
|---|---|---|
| **ActionButton** (Details page) | `DetailsScreen.kt:3120` | Full accent background fill on D-pad focus; text/icon turns **white** |
| **CatalogActionChip** (Settings page) | `SettingsScreen.kt:6880` | Full accent background fill on focus; icon turns **white**; border → accent |
| **GroupRailItem** (TV page) | `TvScreen.kt:1500` | Full accent background fill on focus; border → accent |
| **TopBarNavChip** (Top app bar) | `AppTopBar.kt:196` | **Selected** item text/icon → accent colour; **focused** (hovering) stays white |
| **TopBarSettingsGear** (Top app bar) | `AppTopBar.kt:275` | **Selected** gear icon → accent colour |
| **SidebarIcon** (Sidebar) | `Sidebar.kt:215` | **Selected** sidebar icon → accent colour |

**Design decisions:**
- **TopBar/Sidebar**: Only `isSelected` text/icon gets the accent colour; `isFocused` remains white. Background chips/rings are unchanged (keeps the subtle transparent ring).
- **All other buttons**: Full accent fill on focus with white text/icon for strong, clear D-pad visual feedback.
- Backward compatible — defaults to white when no accent colour is configured.

#### Rename: "Focus Border Color" → "Accent Color"

Since the colour now affects borders, backgrounds, and text, the old name was misleading.

| Old | New | Files Affected |
|---|---|---|
| `LocalFocusBorderColorOverride` | `LocalAccentColorOverride` | `ArvioSkin.kt`, `ArvioFocus.kt`, `PlayerScreen.kt` |
| `resolveFocusBorderColor()` | `resolveAccentColor()` | All 10 consumer files |
| `focusBorderColorFromName()` | `accentColorFromName()` | `ArvioSkin.kt`, `Theme.kt` |
| `FOCUS_BORDER_COLOR_KEY` | `ACCENT_COLOR_KEY` | `DeviceType.kt`, `MainActivity.kt`, `SettingsViewModel.kt` |
| `"focus_border_color"` (DataStore) | `"accent_color"` | `DeviceType.kt` |
| `R.string.focus_border_color` | `R.string.accent_color` | `strings.xml`, `SettingsScreen.kt` |
| `"Focus Border Color"` (label) | `"Accent Color"` | `strings.xml` |
| `"Choose the D-pad focus ring color"` | `"Choose the accent color for focus rings, buttons, and selected items"` | `strings.xml` |
| `SettingsUiState.focusBorderColor` | `SettingsUiState.accentColor` | `SettingsViewModel.kt` |
| `cycleFocusBorderColor()` | `cycleAccentColor()` | `SettingsViewModel.kt`, `SettingsScreen.kt` |

### Files Changed (15)

| File | Status |
|---|---|
| `app/src/main/res/values/strings.xml` | Modified |
| `app/src/main/kotlin/com/arflix/tv/util/DeviceType.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/skin/ArvioSkin.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/skin/ArvioFocus.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/theme/Theme.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/MainActivity.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsViewModel.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/screens/settings/SettingsScreen.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/components/AppTopBar.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/components/Sidebar.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/screens/details/DetailsScreen.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/screens/tv/TvScreen.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/screens/search/SearchScreen.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/components/PersonModal.kt` | Modified |
| `app/src/main/kotlin/com/arflix/tv/ui/screens/player/PlayerScreen.kt` | Modified |

### Testing Notes

- Verify D-pad focus navigation still flows correctly on all affected composables
- Cycle through all 7 accent colours (White → Red → Orange → Yellow → Green → Blue → Indigo → Violet) and confirm each renders correctly on:
  - Focus rings (existing behaviour — unchanged)
  - ActionButton background fill (new)
  - CatalogActionChip background fill (new)
  - GroupRailItem background fill (new)
  - TopBar selected text/icon (new)
  - Sidebar selected icon (new)
- Check that existing DataStore key `"focus_border_color"` migrates cleanly — users who previously set a colour will need to re-select it under the new `"accent_color"` key
