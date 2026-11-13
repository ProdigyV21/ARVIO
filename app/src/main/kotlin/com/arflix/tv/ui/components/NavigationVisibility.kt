package com.arflix.tv.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arflix.tv.util.hiddenNavigationSectionsKey
import com.arflix.tv.util.isNavigationSectionVisible
import com.arflix.tv.util.readHiddenNavigationSections
import com.arflix.tv.util.settingsDataStore
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

/** Shared with the plain D-pad index helpers, like [KidsNavigation]. */
object NavigationVisibility {
    var hiddenSections by mutableStateOf<Set<String>>(emptySet())
}

@Composable
fun ObserveNavigationVisibility(profileId: String?) {
    val context = LocalContext.current
    key(profileId) {
        // Read during composition so preference emissions re-run the side effect.
        val hidden = remember(context, profileId) {
            context.settingsDataStore.data.map { prefs ->
                profileId?.let { readHiddenNavigationSections(prefs[hiddenNavigationSectionsKey(it)]) }.orEmpty()
            }.distinctUntilChanged()
        }.collectAsStateWithLifecycle(initialValue = emptySet()).value
        SideEffect { NavigationVisibility.hiddenSections = hidden }
    }
}

fun SidebarItem.navigationSection(): String = when (this) {
    SidebarItem.SEARCH -> "search"
    SidebarItem.HOME -> "home"
    SidebarItem.WATCHLIST -> "watchlist"
    SidebarItem.TV -> "tv"
    SidebarItem.SETTINGS -> "settings"
}

fun visibleNavigationItems(): List<SidebarItem> = SidebarItem.entries.filter {
    isNavigationSectionVisible(it.navigationSection(), NavigationVisibility.hiddenSections, KidsNavigation.hideLiveTv)
}

internal fun remapTopBarFocus(index: Int, old: List<SidebarItem>, hadProfile: Boolean,
    next: List<SidebarItem>, hasProfile: Boolean): Int {
    if (index < 0) return index
    if (hadProfile && index == 0 && hasProfile) return 0
    val item = old.getOrNull(index - if (hadProfile) 1 else 0)
    val target = next.indexOf(item).takeIf { it >= 0 } ?: next.indexOf(SidebarItem.HOME)
    return target.coerceAtLeast(0) + if (hasProfile) 1 else 0
}

private class NavigationFocusLayout(var items: List<SidebarItem>, var hasProfile: Boolean)

@Composable
fun ReconcileTopBarFocus(index: Int, hasProfile: Boolean, onChange: (Int) -> Unit) {
    val items = visibleNavigationItems()
    val previous = remember { NavigationFocusLayout(items, hasProfile) }
    SideEffect {
        val nextIndex = if (previous.items != items || previous.hasProfile != hasProfile) {
            remapTopBarFocus(index, previous.items, previous.hasProfile, items, hasProfile)
        } else index.coerceAtMost(topBarMaxIndex(hasProfile))
        previous.items = items
        previous.hasProfile = hasProfile
        if (nextIndex != index) onChange(nextIndex)
    }
}
