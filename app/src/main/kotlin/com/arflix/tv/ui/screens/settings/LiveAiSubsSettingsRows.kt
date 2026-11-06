package com.arflix.tv.ui.screens.settings

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.ui.components.MobileSettingsRow
import com.arflix.tv.ui.components.SettingsRow
import com.arflix.tv.ui.components.SettingsToggleRow
import com.arflix.tv.ui.screens.player.subtitles.SubtitleAiModel
import com.arflix.tv.ui.screens.tv.live.aisubs.GeminiSegmentEngine
import com.arflix.tv.ui.screens.tv.live.aisubs.LiveAiSubsActivation
import com.arflix.tv.ui.screens.tv.live.aisubs.LiveAiSubsDelay
import com.arflix.tv.ui.screens.tv.live.aisubs.LiveAiSubsPrefs
import com.arflix.tv.ui.screens.tv.live.aisubs.LiveAiSubsSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale

/*
 * Live TV AI subtitles settings, shown in the "AI subtitles" section next to the Gemini key they
 * use. The rows read and write their own preferences (LiveAiSubsPrefs) instead of threading new
 * state through the settings screen; SettingsViewModel pushes changes to the cloud.
 */

/** TV settings row indices, after the existing ones (0–50). */
internal val LIVE_AI_SUBS_ROWS = listOf(51, 52, 53, 54, 55)

private object LiveAiSubsKeyDialogState {
    var open by mutableStateOf(false)
}

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
            52 -> LiveAiSubsPrefs.setDelay(appContext, settings.delay.next())
            53 -> LiveAiSubsPrefs.setActivation(appContext, settings.activation.next())
            54 -> {
                val choices = LiveAiSubsPrefs.TARGET_LANGUAGE_CHOICES
                val next = choices[(choices.indexOf(settings.targetLanguage).coerceAtLeast(0) + 1) % choices.size]
                LiveAiSubsPrefs.setTargetLanguage(appContext, next)
            }
            55 -> if (settings.usesSharedKey) {
                toast(appContext, R.string.live_ai_subs_key_shared)
            } else {
                LiveAiSubsKeyDialogState.open = true
            }
        }
    }
}

/** Validates the key with a cheap call before turning the feature on, and says how it went. */
private suspend fun enableAfterKeyCheck(context: Context, settings: LiveAiSubsSettings) {
    if (!settings.hasKey) {
        toast(context, R.string.live_ai_subs_needs_key)
        if (!settings.usesSharedKey) LiveAiSubsKeyDialogState.open = true
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

@Composable
private fun delayLabel(delay: LiveAiSubsDelay): String =
    if (delay == LiveAiSubsDelay.AUTO) stringResource(R.string.auto) else stringResource(R.string.live_ai_subs_seconds, (delay.fixedMs ?: 0L) / 1_000L)

@Composable
private fun activationLabel(activation: LiveAiSubsActivation): String = stringResource(
    if (activation == LiveAiSubsActivation.ALWAYS) R.string.live_ai_subs_activation_always else R.string.live_ai_subs_activation_per_channel,
)

@Composable
private fun languageLabel(code: String): String =
    if (code.isBlank()) {
        stringResource(R.string.live_ai_subs_target_profile)
    } else {
        Locale.forLanguageTag(code).getDisplayLanguage(Locale.getDefault()).replaceFirstChar { it.titlecase(Locale.getDefault()) }
    }

@Composable
private fun keyLabel(settings: LiveAiSubsSettings): String = when {
    settings.usesSharedKey -> stringResource(R.string.live_ai_subs_key_shared_short)
    settings.ownApiKey.isBlank() -> stringResource(R.string.ai_key_not_set)
    else -> "••••" + settings.ownApiKey.takeLast(4)
}

/** One TV row for [index] in [LIVE_AI_SUBS_ROWS]. */
@Composable
internal fun LiveAiSubsTvRow(index: Int, isFocused: Boolean, modifier: Modifier) {
    val context = LocalContext.current
    val settings = rememberLiveAiSubsSettings()
    val click = { onLiveAiSubsRowClick(context, index) }
    when (index) {
        51 -> SettingsToggleRow(
            title = stringResource(R.string.live_ai_subs_title),
            subtitle = stringResource(R.string.live_ai_subs_desc),
            isEnabled = settings.enabled,
            isFocused = isFocused,
            onToggle = { click() },
            modifier = modifier,
        )
        52 -> SettingsRow(Icons.Default.Schedule, stringResource(R.string.live_ai_subs_delay_title), stringResource(R.string.live_ai_subs_delay_desc), delayLabel(settings.delay), isFocused, click, modifier)
        53 -> SettingsRow(Icons.Default.Tv, stringResource(R.string.live_ai_subs_activation_title), stringResource(R.string.live_ai_subs_activation_desc), activationLabel(settings.activation), isFocused, click, modifier)
        54 -> SettingsRow(Icons.Default.Language, stringResource(R.string.live_ai_subs_target_title), stringResource(R.string.live_ai_subs_target_desc), languageLabel(settings.targetLanguage), isFocused, click, modifier)
        55 -> SettingsRow(Icons.Default.VpnKey, stringResource(R.string.live_ai_subs_key_title), stringResource(R.string.live_ai_subs_key_desc), keyLabel(settings), isFocused, click, modifier)
    }
    if (index == LIVE_AI_SUBS_ROWS.last()) LiveAiSubsKeyDialogHost()
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
    MobileSettingsRow(icon = Icons.Default.Schedule, title = stringResource(R.string.live_ai_subs_delay_title), subtitle = stringResource(R.string.live_ai_subs_delay_desc), value = delayLabel(settings.delay), isToggle = false, onClick = { onLiveAiSubsRowClick(context, 52) })
    MobileSettingsRow(icon = Icons.Default.Tv, title = stringResource(R.string.live_ai_subs_activation_title), subtitle = stringResource(R.string.live_ai_subs_activation_desc), value = activationLabel(settings.activation), isToggle = false, onClick = { onLiveAiSubsRowClick(context, 53) })
    MobileSettingsRow(icon = Icons.Default.Language, title = stringResource(R.string.live_ai_subs_target_title), subtitle = stringResource(R.string.live_ai_subs_target_desc), value = languageLabel(settings.targetLanguage), isToggle = false, onClick = { onLiveAiSubsRowClick(context, 54) })
    MobileSettingsRow(icon = Icons.Default.VpnKey, title = stringResource(R.string.live_ai_subs_key_title), subtitle = stringResource(R.string.live_ai_subs_key_desc), value = keyLabel(settings), isToggle = false, showDivider = false, onClick = { onLiveAiSubsRowClick(context, 55) })
    LiveAiSubsKeyDialogHost()
}

@Composable
private fun LiveAiSubsKeyDialogHost() {
    if (!LiveAiSubsKeyDialogState.open) return
    val context = LocalContext.current
    val settings = rememberLiveAiSubsSettings()
    AiApiKeyDialog(
        currentKey = settings.ownApiKey,
        model = SubtitleAiModel.GEMINI_FLASH_25,
        onSave = { key ->
            LiveAiSubsKeyDialogState.open = false
            val appContext = context.applicationContext
            actionScope.launch {
                LiveAiSubsPrefs.setOwnApiKey(appContext, key)
                when (GeminiSegmentEngine(apiKey = { key }).validateKey(key)) {
                    true -> toast(appContext, R.string.live_ai_subs_key_valid)
                    false -> toast(appContext, R.string.live_ai_subs_key_rejected)
                    null -> toast(appContext, R.string.live_ai_subs_key_unchecked)
                }
            }
        },
        onDismiss = { LiveAiSubsKeyDialogState.open = false },
    )
}
