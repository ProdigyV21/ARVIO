package com.arflix.tv.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.util.settingsDataStore
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

enum class CardLayoutMode {
    LANDSCAPE,
    POSTER
}

const val CARD_LAYOUT_MODE_LANDSCAPE = "Landscape"
const val CARD_LAYOUT_MODE_POSTER = "Poster"

private val cardLayoutModeKey = stringPreferencesKey("card_layout_mode")

fun normalizeCardLayoutMode(raw: String?): String {
    return if (raw?.trim()?.equals(CARD_LAYOUT_MODE_POSTER, ignoreCase = true) == true) {
        CARD_LAYOUT_MODE_POSTER
    } else {
        CARD_LAYOUT_MODE_LANDSCAPE
    }
}

fun parseCardLayoutMode(raw: String?): CardLayoutMode {
    return if (normalizeCardLayoutMode(raw) == CARD_LAYOUT_MODE_POSTER) {
        CardLayoutMode.POSTER
    } else {
        CardLayoutMode.LANDSCAPE
    }
}

@Composable
fun rememberCardLayoutMode(): CardLayoutMode {
    val context = LocalContext.current
    val modeFlow = remember(context) {
        context.settingsDataStore.data
            .map { prefs -> parseCardLayoutMode(prefs[cardLayoutModeKey]) }
            .distinctUntilChanged()
    }
    val mode by modeFlow.collectAsState(initial = CardLayoutMode.LANDSCAPE)
    return mode
}
