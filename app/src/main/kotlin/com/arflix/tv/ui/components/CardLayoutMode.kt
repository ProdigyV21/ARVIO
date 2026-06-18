package com.arflix.tv.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.skin.ArvioFocusableSurface
import com.arflix.tv.ui.skin.ArvioSkin
import com.arflix.tv.ui.skin.rememberArvioCardShape
import com.arflix.tv.util.profilesDataStore
import com.arflix.tv.util.settingsDataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

enum class CardLayoutMode {
    LANDSCAPE,
    POSTER
}

const val CARD_LAYOUT_MODE_LANDSCAPE = "Landscape"
const val CARD_LAYOUT_MODE_POSTER = "Poster"

private val cardLayoutModeKey = stringPreferencesKey("card_layout_mode")
private val activeProfileIdKey = stringPreferencesKey("active_profile_id")
private const val CATALOGUE_ROW_LAYOUT_PREFIX = "catalogue_row_layout_"
private val ALPHANUMERIC_REGEX = Regex("[^a-z0-9_.:-]+")

private fun profileCardLayoutModeKey(profileId: String): Preferences.Key<String> {
    return stringPreferencesKey("profile_${profileId}_card_layout_mode")
}

fun catalogueRowLayoutPreferenceName(rowKey: String): String {
    val normalizedRowKey = normalizeCatalogueRowLayoutKey(rowKey)
    return "$CATALOGUE_ROW_LAYOUT_PREFIX$normalizedRowKey"
}

fun profileCatalogueRowLayoutPreferenceName(profileId: String, rowKey: String): String {
    return "profile_${profileId}_${catalogueRowLayoutPreferenceName(rowKey)}"
}

fun catalogueRowLayoutPreferencePrefixFor(profileId: String): String {
    return "profile_${profileId}_$CATALOGUE_ROW_LAYOUT_PREFIX"
}

fun catalogueRowLayoutKeyFromPreferenceName(profileId: String, preferenceName: String): String? {
    val prefix = catalogueRowLayoutPreferencePrefixFor(profileId)
    return preferenceName.removePrefix(prefix).takeIf { it != preferenceName && it.isNotBlank() }
}

fun profileCatalogueRowLayoutModeKey(
    profileId: String,
    rowKey: String
): Preferences.Key<String> {
    return stringPreferencesKey(profileCatalogueRowLayoutPreferenceName(profileId, rowKey))
}

fun normalizeCatalogueRowLayoutKey(rowKey: String): String {
    return rowKey
        .trim()
        .lowercase()
        .replace(ALPHANUMERIC_REGEX, "_")
        .trim('_')
        .ifBlank { "default" }
}

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

fun toggledCardLayoutMode(mode: CardLayoutMode): String {
    return if (mode == CardLayoutMode.POSTER) {
        CARD_LAYOUT_MODE_LANDSCAPE
    } else {
        CARD_LAYOUT_MODE_POSTER
    }
}

suspend fun toggleCatalogueRowLayoutMode(
    context: Context,
    rowKey: String
) {
    val normalizedRowKey = normalizeCatalogueRowLayoutKey(rowKey)
    val entryPoint = EntryPointAccessors.fromApplication(
        context,
        RepositoryAccessEntryPoint::class.java
    )
    val profileId = entryPoint.profileManager().getProfileId()
    val key = profileCatalogueRowLayoutModeKey(profileId, normalizedRowKey)
    context.settingsDataStore.edit { prefs ->
        val fallback = normalizeCardLayoutMode(
            prefs[profileCardLayoutModeKey(profileId)] ?: prefs[cardLayoutModeKey]
        )
        val current = parseCardLayoutMode(prefs[key] ?: fallback)
        prefs[key] = toggledCardLayoutMode(current)
    }
}

@Composable
fun rememberCardLayoutMode(): CardLayoutMode {
    val context = LocalContext.current
    val modeFlow = remember(context) {
        combine(context.profilesDataStore.data, context.settingsDataStore.data) { profilePrefs, settingsPrefs ->
            val profileId = profilePrefs[activeProfileIdKey].orEmpty().ifBlank { "default" }
            val profileValue = settingsPrefs[profileCardLayoutModeKey(profileId)]
            val legacyValue = settingsPrefs[cardLayoutModeKey]
            parseCardLayoutMode(profileValue ?: legacyValue)
        }
            .distinctUntilChanged()
    }
    val mode by modeFlow.collectAsStateWithLifecycle(initialValue = CardLayoutMode.LANDSCAPE)
    return mode
}

@Composable
fun rememberCatalogueRowLayoutMode(rowKey: String): CardLayoutMode {
    val context = LocalContext.current
    val normalizedRowKey = remember(rowKey) { normalizeCatalogueRowLayoutKey(rowKey) }
    val rowModeFlow = remember(context, normalizedRowKey) {
        combine(context.profilesDataStore.data, context.settingsDataStore.data) { profilePrefs, settingsPrefs ->
            val profileId = profilePrefs[activeProfileIdKey].orEmpty().ifBlank { "default" }
            val rowKey = profileCatalogueRowLayoutModeKey(profileId, normalizedRowKey)
            val profileValue = settingsPrefs[profileCardLayoutModeKey(profileId)]
            val legacyValue = settingsPrefs[cardLayoutModeKey]
            val fallbackValue = normalizeCardLayoutMode(profileValue ?: legacyValue)
            val rowValue = settingsPrefs[rowKey]
            parseCardLayoutMode(rowValue ?: fallbackValue)
        }.distinctUntilChanged()
    }
    val mode by rowModeFlow.collectAsStateWithLifecycle(initialValue = CardLayoutMode.LANDSCAPE)
    return mode
}

@Composable
fun CatalogueRowLayoutToggleButton(
    rowKey: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    forceFocused: Boolean = false
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val normalizedRowKey = remember(rowKey) { normalizeCatalogueRowLayoutKey(rowKey) }
    val mode = rememberCatalogueRowLayoutMode(normalizedRowKey)
    val shape = rememberArvioCardShape(8.dp)

    ArvioFocusableSurface(
        modifier = modifier
            .size(36.dp)
            .arvioDpadFocusGroup(enableFocusRestorer = false),
        shape = shape,
        backgroundColor = Color.Transparent,
        outlineColor = ArvioSkin.colors.focusOutline,
        outlineWidth = 2.dp,
        focusedScale = 1.08f,
        pressedScale = 0.95f,
        enabled = enabled,
        enableSystemFocus = enabled,
        onClick = {
            if (!enabled) return@ArvioFocusableSurface
            scope.launch {
                toggleCatalogueRowLayoutMode(context, normalizedRowKey)
            }
        }
    ) { isFocused ->
        val visualFocused = isFocused || forceFocused
        val bgColor = when {
            !enabled -> Color.Black.copy(alpha = 0.4f)
            visualFocused -> Color.White
            else -> Color.White.copy(alpha = 0.08f)
        }
        val canvasColor = when {
            !enabled -> Color.White.copy(alpha = 0.5f)
            visualFocused -> Color.Black
            else -> Color.White.copy(alpha = 0.7f)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor, RoundedCornerShape(8.dp))
                .border(
                    width = if (visualFocused) 1.5.dp else 1.dp,
                    color = if (visualFocused) Color.White.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            val isPoster = mode == CardLayoutMode.POSTER

            androidx.compose.foundation.Canvas(modifier = Modifier.size(16.dp)) {
                val path = androidx.compose.ui.graphics.Path()
                val w = size.width
                val h = size.height

                val rectW = if (isPoster) w * 0.60f else w
                val rectH = if (isPoster) h else h * 0.60f

                val left = (w - rectW) / 2f
                val top = (h - rectH) / 2f
                val right = left + rectW
                val bottom = top + rectH

                path.moveTo(left, top)
                if (isPoster) {
                    val curve = rectW * 0.20f
                    path.lineTo(right, top)
                    path.quadraticBezierTo(right - curve, top + rectH / 2f, right, bottom)
                    path.lineTo(left, bottom)
                    path.quadraticBezierTo(left + curve, top + rectH / 2f, left, top)
                } else {
                    val curve = rectH * 0.20f
                    path.quadraticBezierTo(left + rectW / 2f, top + curve, right, top)
                    path.lineTo(right, bottom)
                    path.quadraticBezierTo(left + rectW / 2f, bottom - curve, left, bottom)
                    path.lineTo(left, top)
                }
                path.close()

                drawPath(
                    path = path,
                    color = canvasColor,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 1.5.dp.toPx(),
                        cap = androidx.compose.ui.graphics.StrokeCap.Round,
                        join = androidx.compose.ui.graphics.StrokeJoin.Round
                    )
                )
            }
        }
    }
}
