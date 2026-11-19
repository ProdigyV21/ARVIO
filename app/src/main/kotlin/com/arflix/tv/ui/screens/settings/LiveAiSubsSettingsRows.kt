package com.arflix.tv.ui.screens.settings

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.ui.components.MobileSettingsRow
import com.arflix.tv.ui.components.SettingsToggleRow
import com.arflix.tv.ui.screens.tv.live.aisubs.GeminiSegmentEngine
import com.arflix.tv.ui.screens.tv.live.aisubs.LiveAiSubsPrefs
import com.arflix.tv.ui.screens.tv.live.aisubs.LiveAiSubsSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/*
 * Live TV AI subtitles settings, in the "AI subtitles" section next to the Gemini key they use:
 * the feature switch and "auto-translate when a channel starts". The rows read and write their
 * own preferences (LiveAiSubsPrefs); SettingsViewModel pushes changes to the cloud.
 */

/** TV settings row indices, after the existing ones (0–50). */
internal val LIVE_AI_SUBS_ROWS = listOf(51, 52)

private val actionScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

/** Handles OK/click on one of [LIVE_AI_SUBS_ROWS]. */
internal fun onLiveAiSubsRowClick(context: Context, index: Int) {
    val appContext = context.applicationContext
    actionScope.launch {
        val settings = LiveAiSubsPrefs.flow(appContext).first()
        when (index) {
            51 -> if (settings.enabled) {
                LiveAiSubsPrefs.setEnabled(appContext, false)
            } else {
                enableAfterKeyCheck(appContext, settings)
            }
            52 -> if (settings.enabled) LiveAiSubsPrefs.setAutoStart(appContext, !settings.autoStart)
        }
    }
}

/** Validates the AI subtitles Gemini key with a cheap call before turning the feature on. */
private suspend fun enableAfterKeyCheck(context: Context, settings: LiveAiSubsSettings) {
    if (!settings.hasKey) {
        toast(context, R.string.live_ai_subs_needs_key)
        return
    }
    when (GeminiSegmentEngine(apiKey = { settings.apiKey }).validateKey(settings.apiKey)) {
        true -> {
            toast(context, R.string.live_ai_subs_key_valid)
            LiveAiSubsPrefs.setEnabled(context, true)
        }
        false -> toast(context, R.string.live_ai_subs_key_rejected)
        // Could not check (offline): turn it on; the player reports key problems itself.
        null -> {
            toast(context, R.string.live_ai_subs_key_unchecked)
            LiveAiSubsPrefs.setEnabled(context, true)
        }
    }
}

private fun toast(context: Context, resId: Int) {
    Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
}

@Composable
private fun rememberLiveAiSubsSettings(): LiveAiSubsSettings {
    val context = LocalContext.current
    val flow = remember(context) { LiveAiSubsPrefs.flow(context.applicationContext) }
    val settings by flow.collectAsState(initial = LiveAiSubsSettings())
    return settings
}

/** One TV row for [index] in [LIVE_AI_SUBS_ROWS]. */
@Composable
internal fun LiveAiSubsTvRow(index: Int, isFocused: Boolean, modifier: Modifier) {
    val context = LocalContext.current
    val settings = rememberLiveAiSubsSettings()
    when (index) {
        51 -> SettingsToggleRow(
            title = stringResource(R.string.live_ai_subs_title),
            subtitle = stringResource(R.string.live_ai_subs_desc),
            isEnabled = settings.enabled,
            isFocused = isFocused,
            onToggle = { onLiveAiSubsRowClick(context, 51) },
            modifier = modifier,
        )
        52 -> SettingsToggleRow(
            title = stringResource(R.string.live_ai_subs_auto_title),
            subtitle = stringResource(R.string.live_ai_subs_auto_desc),
            isEnabled = settings.enabled && settings.autoStart,
            isFocused = isFocused,
            onToggle = { onLiveAiSubsRowClick(context, 52) },
            modifier = if (settings.enabled) modifier else modifier.alpha(0.4f),
        )
    }
}

/** The same settings as touch rows, for the mobile settings page. */
@Composable
internal fun LiveAiSubsMobileRows() {
    val context = LocalContext.current
    val settings = rememberLiveAiSubsSettings()
    MobileSettingsRow(
        title = stringResource(R.string.live_ai_subs_title),
        subtitle = stringResource(R.string.live_ai_subs_desc),
        value = stringResource(if (settings.enabled) R.string.on else R.string.off),
        toggleChecked = settings.enabled,
        onClick = { onLiveAiSubsRowClick(context, 51) },
    )
    MobileSettingsRow(
        title = stringResource(R.string.live_ai_subs_auto_title),
        subtitle = stringResource(R.string.live_ai_subs_auto_desc),
        value = stringResource(if (settings.enabled && settings.autoStart) R.string.on else R.string.off),
        toggleChecked = settings.enabled && settings.autoStart,
        enabled = settings.enabled,
        showDivider = false,
        onClick = { onLiveAiSubsRowClick(context, 52) },
    )
}
