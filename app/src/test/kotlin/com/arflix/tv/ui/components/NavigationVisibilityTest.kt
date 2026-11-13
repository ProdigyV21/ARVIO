package com.arflix.tv.ui.components

import android.app.Application
import androidx.datastore.preferences.core.mutablePreferencesOf
import com.arflix.tv.data.repository.CloudSyncRepository.CloudProfileSettings
import com.arflix.tv.ui.screens.settings.tvGeneralRowsForSection
import com.arflix.tv.util.*
import com.google.gson.Gson
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class, manifest = Config.NONE)
@ConscryptMode(ConscryptMode.Mode.OFF)
class NavigationVisibilityTest {
    @After fun reset() {
        NavigationVisibility.hiddenSections = emptySet()
        KidsNavigation.hideLiveTv = false
    }

    @Test fun defaultsKeepEveryTabAndMalformedValuesCannotHideEssentialTabs() {
        assertEquals(SidebarItem.entries, visibleNavigationItems())
        for (json in listOf(null, "broken", "{}", "null", "42")) assertTrue(readHiddenNavigationSections(json).isEmpty())
        assertEquals(setOf("search", "tv"), readHiddenNavigationSections("""["tv","home",null,42,"search","settings","tv"]"""))
        NavigationVisibility.hiddenSections = setOf("home", "settings", "search", "watchlist", "tv")
        assertEquals(listOf(SidebarItem.HOME, SidebarItem.SETTINGS), visibleNavigationItems())
    }

    @Test fun everyVisibilityCombinationHasAContiguousFocusMapAndReachableSettings() {
        for (mask in 0..7) for (kids in listOf(false, true)) for (hasProfile in listOf(false, true)) {
            NavigationVisibility.hiddenSections = OPTIONAL_NAVIGATION_SECTIONS.filterIndexed { index, _ -> mask and (1 shl index) != 0 }.toSet()
            KidsNavigation.hideLiveTv = kids
            val visible = visibleNavigationItems()
            val mapped = (0..topBarMaxIndex(hasProfile)).mapNotNull { topBarFocusedItem(it, hasProfile) }
            assertEquals(visible, mapped)
            for (item in visible) assertEquals(item, topBarFocusedItem(topBarSelectedIndex(item, hasProfile), hasProfile))
            assertEquals(SidebarItem.SETTINGS, topBarFocusedItem(99, hasProfile))
            if (SidebarItem.TV !in visible) assertEquals(-1, topBarSelectedIndex(SidebarItem.TV, hasProfile))
            assertNull(topBarFocusedItem(-1, hasProfile))
        }
    }

    @Test fun kidsRestrictionStillWinsWhenAllPersonalTabsAreEnabled() {
        KidsNavigation.hideLiveTv = true
        assertFalse(visibleNavigationItems().contains(SidebarItem.TV))
        assertTrue(visibleNavigationItems().contains(SidebarItem.SEARCH))
        assertFalse(tvGeneralRowsForSection("appearance").contains(58))
    }

    @Test fun changingVisibilityKeepsFocusOnTheSameItemInsteadOfTheOldPosition() {
        val all = SidebarItem.entries.toList()
        val noSearch = all.filter { it != SidebarItem.SEARCH }
        assertEquals(1, remapTopBarFocus(2, all, true, noSearch, true))
        assertEquals(4, remapTopBarFocus(5, all, true, noSearch, true))
        assertEquals(1, remapTopBarFocus(1, all, true, noSearch, true))
        assertEquals(0, remapTopBarFocus(0, all, true, noSearch, true))
        assertEquals(2, remapTopBarFocus(1, all, false, all, true))
    }

    @Test fun appearanceRowsHaveUniqueIdsWithoutChangingExistingSettingsSlots() {
        val appearance = tvGeneralRowsForSection("appearance")
        assertEquals(listOf(56, 57, 58), appearance.takeLast(3))
        val all = listOf("language", "subtitles", "ai_subtitles", "playback", "appearance", "profiles", "network")
            .flatMap(::tvGeneralRowsForSection)
        assertEquals(all.size, all.distinct().size)
    }

    @Test fun cloudRoundTripIsProfileScopedAndExplicitEmptyUnhidesEverything() {
        val source = mutablePreferencesOf(hiddenNavigationSectionsKey("first") to """["tv","settings","search"]""")
        val gson = Gson()
        val wire = gson.toJson(CloudProfileSettings(hiddenNavigationSections = navigationSectionsForCloud(source, "first")))
        val restored = gson.fromJson(wire, CloudProfileSettings::class.java)
        val target = mutablePreferencesOf(hiddenNavigationSectionsKey("second") to """["watchlist"]""")
        restoreNavigationSections(target, "first", restored.hiddenNavigationSections)
        assertEquals(setOf("search", "tv"), readHiddenNavigationSections(target[hiddenNavigationSectionsKey("first")]))
        assertEquals(setOf("watchlist"), readHiddenNavigationSections(target[hiddenNavigationSectionsKey("second")]))
        restoreNavigationSections(target, "first", emptyList())
        assertEquals(emptyList<String>(), navigationSectionsForCloud(target, "first"))
        assertEquals(setOf("watchlist"), readHiddenNavigationSections(target[hiddenNavigationSectionsKey("second")]))
    }

    @Test fun olderClientsAndUninitializedDevicesCannotResetTheChoice() {
        val preferences = mutablePreferencesOf(hiddenNavigationSectionsKey("first") to """["tv"]""")
        val old = Gson().fromJson("{}", CloudProfileSettings::class.java)
        assertNull(old.hiddenNavigationSections)
        restoreNavigationSections(preferences, "first", old.hiddenNavigationSections)
        assertEquals(listOf("tv"), navigationSectionsForCloud(preferences, "first"))
        assertNull(navigationSectionsForCloud(preferences, "new-device"))
    }
}
