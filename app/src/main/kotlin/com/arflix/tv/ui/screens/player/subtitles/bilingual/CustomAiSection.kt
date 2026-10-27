package com.arflix.tv.ui.screens.player.subtitles.bilingual

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.arflix.tv.ui.screens.player.subtitles.ai.AiUsageMeter
import kotlinx.coroutines.launch

private val CInk = Color(0xFFF2F2F2)
private val CInkDim = Color(0xFFA8A8A8)
private val CCard = Color(0x22FFFFFF)
private val CAccent = Color(0xFFF4D979)
private val COk = Color(0xFF8BE28B)
private val CErr = Color(0xFFFF8A80)

/**
 * Saved translation services: any OpenAI-compatible Chat Completions, OpenAI Responses or
 * Anthropic Messages endpoint. Several can be saved and one is active (or none: built-in
 * Gemini/Groq). Edits happen on a draft and only "Save" writes them.
 */
@Composable
fun CustomAiSection(targetLanguageName: String) {
    val context = LocalContext.current
    val store = remember { CustomAiConfigStore.from(context) }
    val profiles by store.profiles.collectAsState()
    val usage by AiUsageMeter.total.collectAsState()
    var editing by remember { mutableStateOf<CustomAiConfig?>(null) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(bt("Translation service").uppercase(), color = CInkDim, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.8.sp)

        ServiceRow(
            title = bt("Built-in (Gemini / Groq)"),
            subtitle = bt("Uses the key from Settings → AI Subtitles"),
            selected = profiles.activeId == null,
            onSelect = { store.activate(null) },
            onEdit = null,
        )
        profiles.profiles.forEach { p ->
            ServiceRow(
                title = p.displayName,
                subtitle = "${p.model} · ${protocolName(p.protocol)}" + if (p.isComplete) "" else " · " + bt("incomplete"),
                selected = profiles.activeId == p.id,
                onSelect = { if (p.isComplete) store.activate(p.id) else editing = p },
                onEdit = { editing = p },
            )
        }
        if (editing == null) {
            Text(
                "+ " + bt("Add a service"),
                color = CAccent, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { editing = CustomAiConfig(temperature = "0.2") }.padding(vertical = 6.dp)
            )
        }
        editing?.let { start ->
            ServiceEditor(
                initial = start,
                isNew = profiles.profiles.none { it.id == start.id && start.id.isNotBlank() },
                targetLanguageName = targetLanguageName,
                onSave = { draft -> store.save(draft, activate = true); editing = null },
                onDelete = { store.delete(start.id); editing = null },
                onCancel = { editing = null },
            )
        }

        if (usage.requests > 0) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    bt("Used since app start: %1\$s requests · input %2\$s (cached %3\$s) · output %4\$s (thinking %5\$s) tokens")
                        .format(usage.requests, usage.input, usage.cachedInput, usage.output, usage.reasoning),
                    color = if (usage.reasoning > usage.output / 2 && usage.reasoning > 0) CErr else CInkDim,
                    fontSize = 11.sp, modifier = Modifier.weight(1f)
                )
                Text(bt("Reset"), color = CAccent, fontSize = 11.sp, modifier = Modifier.clickable { AiUsageMeter.reset() }.padding(4.dp))
            }
        }
        Text(
            bt("Keys are stored only on this device. Translations are cached, so re-watching or seeking back costs nothing. Lines are translated in batches of ~50 with the previous lines as context."),
            color = CInkDim, fontSize = 11.sp
        )
    }
}

@Composable
private fun ServiceRow(title: String, subtitle: String, selected: Boolean, onSelect: () -> Unit, onEdit: (() -> Unit)?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) CAccent.copy(alpha = 0.16f) else CCard)
            .clickable { onSelect() }
            .padding(horizontal = 12.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(if (selected) "●" else "○", color = if (selected) CAccent else CInkDim, fontSize = 14.sp, modifier = Modifier.padding(end = 10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = CInk, fontSize = 13.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal)
            Text(subtitle, color = CInkDim, fontSize = 11.sp)
        }
        if (onEdit != null) {
            Text(bt("Edit"), color = CAccent, fontSize = 12.5.sp, modifier = Modifier.clickable { onEdit() }.padding(6.dp))
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
    val bodyOk = draft.extraBody.isBlank() || runCatching { org.json.JSONObject(draft.extraBody) }.isSuccess

    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0x14FFFFFF)).padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(if (isNew) bt("New service") else bt("Edit service"), color = CInk, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Text(bt("Preset"), color = CInk, fontSize = 13.sp)
        Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AiPresets.all.forEach { preset ->
                Chip(text = preset.name, selected = false) {
                    edit { it.copy(protocol = preset.protocol, baseUrl = preset.baseUrl, model = preset.model, name = if (it.name.isBlank()) preset.name else it.name) }
                }
            }
        }
        Field(bt("Name"), draft.name, placeholder = "DeepSeek") { v -> edit { it.copy(name = v) } }
        Text(bt("Protocol"), color = CInk, fontSize = 13.sp)
        Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AiProtocol.entries.forEach { p ->
                Chip(text = protocolName(p), selected = draft.protocol == p) { edit { it.copy(protocol = p) } }
            }
        }
        Field(bt("API URL"), draft.baseUrl, placeholder = "https://api.openai.com/v1", keyboard = KeyboardType.Uri) { v -> edit { it.copy(baseUrl = v.trim()) } }
        if (draft.baseUrl.isNotBlank()) {
            Text(bt("Requests go to: %s").format(draft.endpointUrl()), color = CInkDim, fontSize = 10.5.sp)
        }
        Field(
            bt("API key"), draft.apiKey, placeholder = "sk-…", keyboard = KeyboardType.Password,
            visual = if (showKey) VisualTransformation.None else PasswordVisualTransformation(),
            trailing = { Text(if (showKey) bt("Hide") else bt("Show"), color = CAccent, fontSize = 12.sp, modifier = Modifier.clickable { showKey = !showKey }.padding(8.dp)) }
        ) { v -> edit { it.copy(apiKey = v.trim()) } }
        Field(bt("Model"), draft.model, placeholder = "deepseek-flash / gpt-4.1-mini / claude-…") { v -> edit { it.copy(model = v.trim()) } }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                Field(bt("Temperature (blank = default)"), draft.temperature, placeholder = "0.2", keyboard = KeyboardType.Decimal) { v -> edit { it.copy(temperature = v.trim()) } }
            }
            Box(modifier = Modifier.weight(1f)) {
                Field(bt("Max output tokens"), maxTokensText, placeholder = "4096", keyboard = KeyboardType.Number) { v -> maxTokensText = v.filter { it.isDigit() } }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().clickable { edit { it.copy(disableThinking = !it.disableThinking) } },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(bt("Turn off thinking (recommended)"), color = CInk, fontSize = 13.sp)
                Text(bt("Reasoning tokens are billed as output and don't improve subtitle translation. DeepSeek, Qwen, GLM, OpenAI, Gemini, OpenRouter and Anthropic are switched off automatically."), color = CInkDim, fontSize = 11.sp)
            }
            Switch(
                checked = draft.disableThinking,
                onCheckedChange = { on -> edit { it.copy(disableThinking = on) } },
                colors = SwitchDefaults.colors(checkedTrackColor = CAccent, checkedThumbColor = Color.Black)
            )
        }
        Field(bt("Extra headers (optional, one \"Name: value\" per line)"), draft.extraHeaders, placeholder = "HTTP-Referer: https://example.com", singleLine = false) { v -> edit { it.copy(extraHeaders = v) } }
        Field(bt("Extra request JSON (optional, advanced)"), draft.extraBody, placeholder = "{\"top_p\": 0.9}", singleLine = false) { v -> edit { it.copy(extraBody = v) } }
        if (!bodyOk) Text(bt("Not valid JSON — it will be ignored."), color = CErr, fontSize = 11.sp)

        Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            val canSave = draft.isComplete
            ActionButton(bt("Save and use"), enabled = canSave, primary = true) { onSave(finalDraft()) }
            ActionButton(if (testing) bt("Testing…") else bt("Test connection"), enabled = canSave && !testing) {
                testing = true
                testState = null
                scope.launch {
                    val service = SubtitleTranslationService(apiKeyProvider = { "" })
                    val result = service.testCustom(finalDraft(), targetLanguageName.ifBlank { "Simplified Chinese" })
                    testing = false
                    testState = if (result.success) true to (result.lines.firstOrNull() ?: "")
                    else false to friendlyError(result.errorMessage)
                }
            }
            if (!isNew) ActionButton(bt("Delete"), enabled = true, danger = true) { onDelete() }
            Text(bt("Cancel"), color = CInkDim, fontSize = 12.5.sp, modifier = Modifier.clickable { onCancel() }.padding(6.dp))
        }
        if (!draft.isComplete) {
            Text(bt("Fill in the API URL, model and key to save."), color = CInkDim, fontSize = 11.sp)
        }
        testState?.let { (ok, msg) ->
            Text(
                if (ok) bt("Works: \"Winter is coming.\" → %s").format(msg) else bt("Failed: %s").format(msg),
                color = if (ok) COk else CErr, fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ActionButton(text: String, enabled: Boolean, primary: Boolean = false, danger: Boolean = false, onClick: () -> Unit) {
    val tint = if (danger) CErr else CAccent
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

private fun friendlyError(raw: String?): String = when {
    raw == null -> bt("Unknown error")
    raw == "RATE_LIMITED" -> bt("Rate limited (HTTP 429) — wait a moment or check your quota")
    raw.startsWith("HTTP 401") || raw.startsWith("HTTP 403") -> bt("Key rejected (%s)").format(raw.take(120))
    raw.startsWith("HTTP 404") -> bt("Not found — check the API URL and model name (%s)").format(raw.take(120))
    else -> raw.take(200)
}

@Composable
private fun Chip(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) CAccent else CCard)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 7.dp)
    ) {
        Text(text, color = if (selected) Color.Black else CInk, fontSize = 12.5.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal)
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
        placeholder = { Text(placeholder, fontSize = 12.sp, color = CInkDim) },
        singleLine = singleLine,
        minLines = if (singleLine) 1 else 2,
        visualTransformation = visual,
        keyboardOptions = KeyboardOptions(keyboardType = keyboard, autoCorrect = false),
        trailingIcon = trailing,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = CInk,
            unfocusedTextColor = CInk,
            focusedBorderColor = CAccent,
            unfocusedBorderColor = Color.White.copy(alpha = 0.25f),
            focusedLabelColor = CAccent,
            unfocusedLabelColor = CInkDim,
            cursorColor = CAccent,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
