package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.ui.screens.player.subtitles.SubtitleTranslationService
import com.arflix.tv.ui.screens.player.subtitles.ai.AiPresets
import com.arflix.tv.ui.screens.player.subtitles.ai.AiProtocol
import com.arflix.tv.ui.screens.player.subtitles.ai.CustomAiConfigStore
import com.arflix.tv.ui.screens.player.subtitles.ai.CustomAiConfig
import com.arflix.tv.R
import com.arflix.tv.ui.screens.player.subtitles.ai.CustomAiProfiles
import com.arflix.tv.ui.theme.AccentWhite
import com.arflix.tv.ui.theme.ErrorRed
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

/**
 * Saved translation services: any OpenAI-compatible Chat Completions, OpenAI Responses or
 * Anthropic Messages endpoint. Several can be saved and one is active (or none: built-in
 * Gemini/Groq). Edits happen on a draft and only "Save" writes them.
 */
@Composable
internal fun CustomAiSection(
    store: CustomAiConfigStore,
    profiles: CustomAiProfiles,
    targetLanguageName: String,
) {
    var editing by remember { mutableStateOf<CustomAiConfig?>(null) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(stringResource(R.string.custom_ai_title).uppercase(), color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.8.sp)

        ServiceRow(
            title = stringResource(R.string.custom_ai_builtin),
            subtitle = stringResource(R.string.custom_ai_builtin_desc),
            selected = !profiles.active.isActive,
            onSelect = { store.activate(null) },
            onEdit = null,
        )
        profiles.profiles.forEach { p ->
            ServiceRow(
                title = p.displayName,
                subtitle = "${p.model} · ${protocolName(p.protocol)}" + if (p.isComplete) "" else " · " + stringResource(R.string.custom_ai_incomplete),
                selected = profiles.activeId == p.id && p.isComplete,
                onSelect = { if (p.isComplete) store.activate(p.id) else editing = p },
                onEdit = { editing = p },
            )
        }
        if (editing == null) {
            Text(
                "+ " + stringResource(R.string.custom_ai_add),
                color = AccentWhite, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { editing = CustomAiConfig() }.padding(vertical = 6.dp)
            )
        }
        editing?.let { start ->
            ServiceEditor(
                initial = start,
                isNew = profiles.profiles.none { it.id == start.id && start.id.isNotBlank() },
                targetLanguageName = targetLanguageName,
                onSave = { draft -> store.save(draft, activate = false); editing = null },
                onDelete = { store.delete(start.id); editing = null },
                onCancel = { editing = null },
            )
        }

        Text(
            stringResource(R.string.custom_ai_device_storage),
            color = TextSecondary, fontSize = 11.sp
        )
    }
}

@Composable
private fun ServiceRow(title: String, subtitle: String, selected: Boolean, onSelect: () -> Unit, onEdit: (() -> Unit)?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) AccentWhite.copy(alpha = 0.16f) else TextPrimary.copy(alpha = 0.12f))
            .clickable { onSelect() }
            .padding(horizontal = 12.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(if (selected) "●" else "○", color = if (selected) AccentWhite else TextSecondary, fontSize = 14.sp, modifier = Modifier.padding(end = 10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = TextPrimary, fontSize = 13.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal)
            Text(subtitle, color = TextSecondary, fontSize = 11.sp)
        }
        if (onEdit != null) {
            Text(stringResource(R.string.custom_ai_edit), color = AccentWhite, fontSize = 12.5.sp, modifier = Modifier.clickable { onEdit() }.padding(6.dp))
        }
    }
}

@Composable
private fun ServiceEditor(
    initial: CustomAiConfig,
    isNew: Boolean,
    targetLanguageName: String,
    onSave: (CustomAiConfig) -> Unit,
    onDelete: () -> Unit,
    onCancel: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val service = remember { SubtitleTranslationService(apiKeyProvider = { "" }) }
    var draft by remember(initial) { mutableStateOf(initial) }
    var maxTokensText by remember(initial) { mutableStateOf(initial.maxTokens.toString()) }
    var showKey by remember { mutableStateOf(false) }
    var testState by remember { mutableStateOf<Pair<Boolean, String>?>(null) }
    var testing by remember { mutableStateOf(false) }
    fun edit(t: (CustomAiConfig) -> CustomAiConfig) { draft = t(draft); testState = null }
    fun finalDraft(): CustomAiConfig = draft.copy(
        name = draft.name.trim().ifBlank { draft.model },
        maxTokens = (maxTokensText.trim().toIntOrNull() ?: 4096).coerceIn(256, 65536),
    )
    val endpoint = draft.endpointUrl()
    val bodyOk = draft.extraBody.isBlank() || runCatching { org.json.JSONObject(draft.extraBody) }.isSuccess

    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(TextPrimary.copy(alpha = 0.08f)).padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(if (isNew) stringResource(R.string.custom_ai_new) else stringResource(R.string.custom_ai_edit_service), color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Text(stringResource(R.string.custom_ai_preset), color = TextPrimary, fontSize = 13.sp)
        Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AiPresets.all.forEach { preset ->
                Chip(text = preset.name, selected = false) {
                    edit { it.copy(protocol = preset.protocol, baseUrl = preset.baseUrl, model = preset.model, name = if (it.name.isBlank()) preset.name else it.name) }
                }
            }
        }
        Field(stringResource(R.string.custom_ai_name), draft.name, placeholder = "DeepSeek") { v -> edit { it.copy(name = v) } }
        Text(stringResource(R.string.custom_ai_protocol), color = TextPrimary, fontSize = 13.sp)
        Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AiProtocol.entries.forEach { p ->
                Chip(text = protocolName(p), selected = draft.protocol == p) { edit { it.copy(protocol = p) } }
            }
        }
        Field(stringResource(R.string.custom_ai_endpoint), draft.baseUrl, placeholder = "https://api.openai.com/v1", keyboard = KeyboardType.Uri) { v -> edit { it.copy(baseUrl = v.trim()) } }
        if (endpoint.isNotBlank()) {
            Text(stringResource(R.string.custom_ai_request_url, endpoint), color = TextSecondary, fontSize = 10.5.sp)
        }
        Field(
            stringResource(R.string.custom_ai_key), draft.apiKey, placeholder = "sk-…", keyboard = KeyboardType.Password,
            visual = if (showKey) VisualTransformation.None else PasswordVisualTransformation(),
            trailing = { Text(if (showKey) stringResource(R.string.custom_ai_hide) else stringResource(R.string.custom_ai_show), color = AccentWhite, fontSize = 12.sp, modifier = Modifier.clickable { showKey = !showKey }.padding(8.dp)) }
        ) { v -> edit { it.copy(apiKey = v.trim()) } }
        Field(stringResource(R.string.custom_ai_model), draft.model, placeholder = "deepseek-flash / gpt-4.1-mini / claude-…") { v -> edit { it.copy(model = v.trim()) } }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                Field(stringResource(R.string.custom_ai_temperature), draft.temperature, placeholder = "0.2", keyboard = KeyboardType.Decimal) { v -> edit { it.copy(temperature = v.trim()) } }
            }
            Box(modifier = Modifier.weight(1f)) {
                Field(stringResource(R.string.custom_ai_max_tokens), maxTokensText, placeholder = "4096", keyboard = KeyboardType.Number) { v -> maxTokensText = v.filter { it.isDigit() } }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().clickable { edit { it.copy(disableThinking = !it.disableThinking) } },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.custom_ai_thinking), color = TextPrimary, fontSize = 13.sp)
                Text(stringResource(R.string.custom_ai_thinking_desc), color = TextSecondary, fontSize = 11.sp)
            }
            Switch(
                checked = draft.disableThinking,
                onCheckedChange = { on -> edit { it.copy(disableThinking = on) } },
                colors = SwitchDefaults.colors(checkedTrackColor = AccentWhite, checkedThumbColor = Color.Black)
            )
        }
        Field(stringResource(R.string.custom_ai_headers), draft.extraHeaders, placeholder = "HTTP-Referer: https://example.com", singleLine = false) { v -> edit { it.copy(extraHeaders = v) } }
        Field(stringResource(R.string.custom_ai_body), draft.extraBody, placeholder = "{\"top_p\": 0.9}", singleLine = false) { v -> edit { it.copy(extraBody = v) } }
        if (!bodyOk) Text(stringResource(R.string.custom_ai_invalid_json), color = ErrorRed, fontSize = 11.sp)

        Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            val canSave = draft.isComplete
            ActionButton(stringResource(R.string.save), enabled = canSave, primary = true) { onSave(finalDraft()) }
            ActionButton(if (testing) stringResource(R.string.custom_ai_testing) else stringResource(R.string.custom_ai_test), enabled = canSave && !testing) {
                testing = true
                testState = null
                scope.launch {
                    try {
                        val result = service.testCustom(finalDraft(), targetLanguageName.ifBlank { "English" })
                        testState = if (result.success) true to result.lines.firstOrNull().orEmpty()
                        else false to result.errorMessage.orEmpty()
                    } catch (e: CancellationException) {
                        throw e
                    } catch (_: Exception) {
                        testState = false to ""
                    } finally {
                        testing = false
                    }
                }
            }
            if (!isNew) ActionButton(stringResource(R.string.delete), enabled = true, danger = true) { onDelete() }
            Text(stringResource(R.string.cancel), color = TextSecondary, fontSize = 12.5.sp, modifier = Modifier.clickable { onCancel() }.padding(6.dp))
        }
        if (!draft.isComplete) {
            Text(stringResource(R.string.custom_ai_incomplete_input), color = TextSecondary, fontSize = 11.sp)
        }
        testState?.let { (ok, msg) ->
            Text(
                if (ok) stringResource(R.string.custom_ai_test_ok, msg)
                else stringResource(R.string.custom_ai_test_failed, friendlyError(msg)),
                color = if (ok) SuccessGreen else ErrorRed, fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ActionButton(text: String, enabled: Boolean, primary: Boolean = false, danger: Boolean = false, onClick: () -> Unit) {
    val tint = if (danger) ErrorRed else AccentWhite
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                when {
                    !enabled -> tint.copy(alpha = 0.08f)
                    primary -> tint
                    else -> tint.copy(alpha = 0.22f)
                }
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 14.dp, vertical = 9.dp)
    ) {
        Text(text, color = if (primary && enabled) Color.Black else tint.copy(alpha = if (enabled) 1f else 0.5f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}

private fun protocolName(p: AiProtocol): String = when (p) {
    AiProtocol.OPENAI_CHAT -> "OpenAI Chat Completions"
    AiProtocol.OPENAI_RESPONSES -> "OpenAI Responses"
    AiProtocol.ANTHROPIC -> "Anthropic Messages"
}

@Composable
private fun friendlyError(raw: String): String = when {
    raw.isBlank() -> stringResource(R.string.custom_ai_unknown_error)
    raw == "RATE_LIMITED" -> stringResource(R.string.custom_ai_rate_limited)
    raw.startsWith("HTTP 401") || raw.startsWith("HTTP 403") -> stringResource(R.string.custom_ai_rejected_key, raw.take(120))
    raw.startsWith("HTTP 404") -> stringResource(R.string.custom_ai_not_found, raw.take(120))
    else -> raw.take(200)
}

@Composable
private fun Chip(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) AccentWhite else TextPrimary.copy(alpha = 0.12f))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 7.dp)
    ) {
        Text(text, color = if (selected) Color.Black else TextPrimary, fontSize = 12.5.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal)
    }
}

@Composable
private fun Field(
    label: String,
    value: String,
    placeholder: String = "",
    keyboard: KeyboardType = KeyboardType.Text,
    visual: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    trailing: (@Composable () -> Unit)? = null,
    onChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label, fontSize = 12.sp) },
        placeholder = { Text(placeholder, fontSize = 12.sp, color = TextSecondary) },
        singleLine = singleLine,
        minLines = if (singleLine) 1 else 2,
        visualTransformation = visual,
        keyboardOptions = KeyboardOptions(keyboardType = keyboard, autoCorrect = false),
        trailingIcon = trailing,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            focusedBorderColor = AccentWhite,
            unfocusedBorderColor = Color.White.copy(alpha = 0.25f),
            focusedLabelColor = AccentWhite,
            unfocusedLabelColor = TextSecondary,
            cursorColor = AccentWhite,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
