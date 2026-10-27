package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.ui.screens.player.SubtitleFontOption
import kotlin.math.roundToInt

private val Ink = Color(0xFFF2F2F2)
private val InkDim = Color(0xFFA8A8A8)
private val CardBg = Color(0x22FFFFFF)
private val Accent = Color(0xFFF4D979)
private val Warn = Color(0xFFFFB36B)

/**
 * "Bilingual AI Subtitles" settings. Shared by the in-player subtitle sheet and
 * Settings → Audio & Subtitles. Every change is applied live (the overlay listens to the same
 * store) and persisted to the profile's DataStore.
 *
 * @param aiAvailable null when unknown (global settings screen); false shows the
 *   "no embedded text subtitle" hint inside the player.
 */
@Composable
fun BilingualSubtitleSettingsPanel(
    modifier: Modifier = Modifier,
    aiAvailable: Boolean? = null,
    aiTranslating: Boolean = false,
    onEnableAiTranslation: (() -> Unit)? = null,
    showPreview: Boolean = true,
    /** Inside the player: offer "use Smart Bilingual for this video" (sent via [BilingualPlayerBridge]). */
    inPlayer: Boolean = aiAvailable != null,
) {
    val context = LocalContext.current
    val store = remember { BilingualSubtitleSettingsStore.from(context) }
    val settings by store.settings.collectAsState()
    val geminiKeyConfigured by store.aiKeyConfigured.collectAsState(initial = true)
    val customAi by remember { com.arflix.tv.ui.screens.player.subtitles.ai.CustomAiConfigStore.from(context) }.config.collectAsState()
    val keyConfigured = geminiKeyConfigured || customAi.isActive
    var advanced by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (!keyConfigured) {
            Hint(bt("No AI is set up for translation. Add a Gemini/Groq key in Settings → AI Subtitles, or turn on a custom model below; until then only the original subtitle can be shown."))
        } else if (aiAvailable == false) {
            Hint(bt("This video has no embedded text subtitle that AI can translate (image subtitles such as PGS can't be read). Pick another source or a normal subtitle track."))
        }
        if (onEnableAiTranslation != null && aiAvailable == true && !aiTranslating) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Accent.copy(alpha = 0.18f))
                    .clickable { onEnableAiTranslation() }
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(bt("Turn on Bilingual AI Translation for this video"), color = Accent, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        val primaryName = settings.primaryExplicit?.localName ?: bt("Primary")
        val secondaryName = settings.secondary.localName

        if (showPreview) Preview(settings)

        // ── Smart bilingual (source selection) ──────────────────────────────
        SectionTitle(bt("Bilingual subtitles"))
        ToggleLine(
            label = bt("Bilingual subtitles on"),
            checked = settings.smartEnabled,
            onToggle = { on -> store.update { it.copy(smartEnabled = on) } }
        )
        Text(
            bt("Shows two languages at once using the Generation mode below. Off = ARVIO's normal subtitles (a subtitle you pick from the normal list is always respected). Display mode further down is separate: it only decides which of the two lines are shown."),
            color = InkDim,
            fontSize = 11.sp
        )
        SectionTitle(bt("Generation mode"))
        ChipRow(
            options = listOf(
                BilingualGenerationMode.AUTO to bt("Auto"),
                BilingualGenerationMode.MERGE to bt("Merge"),
                BilingualGenerationMode.AI_TRANSLATE to bt("AI Translate"),
            ),
            selected = settings.generationMode,
            onSelect = { g ->
                store.update { it.copy(generationMode = g) }
                if (inPlayer && !settings.smartEnabled) BilingualPlayerBridge.requestSmartMode()
            }
        )
        Text(
            when (settings.generationMode) {
                BilingualGenerationMode.AUTO -> bt("Auto: merge two existing text subtitles when they line up (no AI); otherwise translate the missing language with Gemini/Groq.")
                BilingualGenerationMode.MERGE -> bt("Merge: only combine existing subtitle tracks. AI is never called — if a language is missing you'll get a notice and can pick tracks below.")
                BilingualGenerationMode.AI_TRANSLATE -> bt("AI Translate: always translate the primary track with Gemini/Groq, even if the other language exists. On API errors the primary stays on screen.")
            },
            color = InkDim,
            fontSize = 11.sp
        )
        if (inPlayer) {
            val status by BilingualPlayerBridge.status.collectAsState()
            status?.let { Text(it, color = Accent, fontSize = 12.sp) }
            val tracks by BilingualPlayerBridge.tracks.collectAsState()
            val primaryOverride by BilingualPlayerBridge.primaryOverride.collectAsState()
            val secondaryOverride by BilingualPlayerBridge.secondaryOverride.collectAsState()
            if (tracks.isNotEmpty()) {
                Text(bt("Primary track (this video)"), color = Ink, fontSize = 13.sp)
                ChipRow(
                    options = listOf<String?>(null).map { it to bt("Automatic") } + tracks.map { it.id to it.label },
                    selected = primaryOverride,
                    onSelect = { id -> BilingualPlayerBridge.primaryOverride.value = id; BilingualPlayerBridge.requestSmartMode() }
                )
                if (settings.generationMode != BilingualGenerationMode.AI_TRANSLATE) {
                    Text(bt("Secondary track to merge (this video)"), color = Ink, fontSize = 13.sp)
                    ChipRow(
                        options = listOf<String?>(null).map { it to bt("Automatic") } +
                            tracks.filter { it.mergeable }.map { it.id to it.label },
                        selected = secondaryOverride,
                        onSelect = { id -> BilingualPlayerBridge.secondaryOverride.value = id; BilingualPlayerBridge.requestSmartMode() }
                    )
                }
            }
        }
        if (inPlayer) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Accent.copy(alpha = 0.18f))
                    .clickable { BilingualPlayerBridge.requestSmartMode() }
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(bt("Turn on bilingual for this video"), color = Accent, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Text(bt("Primary subtitle language"), color = Ink, fontSize = 13.sp)
        ChipRow(
            options = listOf(SubtitleLanguage.AUTO to bt("Auto")) +
                SubtitleLanguage.PICKER_OPTIONS.map { it.tag to it.localName },
            selected = settings.primaryExplicit?.tag ?: SubtitleLanguage.AUTO,
            onSelect = { tag -> store.update { it.copy(primaryLanguage = tag) } }
        )
        Text(bt("Secondary subtitle language"), color = Ink, fontSize = 13.sp)
        ChipRow(
            options = SubtitleLanguage.PICKER_OPTIONS.map { it.tag to it.localName },
            selected = settings.secondary.tag,
            onSelect = { tag -> store.update { it.copy(secondaryLanguage = tag) } }
        )
        if (SubtitleLanguageMatcher.sameLanguage(settings.primaryExplicit, settings.secondary)) {
            Hint(bt("Primary and secondary are the same language — only one line would be shown. Pick a different secondary language."))
        }

        CustomAiSection(targetLanguageName = settings.secondary.translationTargetName)


        SectionTitle(bt("Display mode"))
        ChipRow(
            options = listOf(
                BilingualDisplayMode.BILINGUAL to bt("Bilingual"),
                BilingualDisplayMode.ORIGINAL_ONLY to bt("%s only").format(primaryName),
                BilingualDisplayMode.TRANSLATION_ONLY to bt("%s only").format(secondaryName),
                BilingualDisplayMode.OFF to bt("Off"),
            ),
            selected = settings.mode,
            onSelect = { m -> store.update { it.copy(mode = m) } }
        )

        SectionTitle(bt("Basic"))
        ChipRow(
            options = listOf(
                BilingualOrder.ORIGINAL_FIRST to bt("%s on top").format(primaryName),
                BilingualOrder.TRANSLATION_FIRST to bt("%s on top").format(secondaryName),
            ),
            selected = settings.order,
            onSelect = { o -> store.update { it.copy(order = o) } }
        )
        LabeledSlider(
            label = bt("Gap between languages"),
            valueText = "${settings.gapDp} dp",
            value = settings.gapDp.toFloat(),
            range = 0f..BilingualSubtitleSettings.MAX_GAP_DP.toFloat(),
            onChange = { v -> store.update { it.copy(gapDp = v.roundToInt()) } }
        )
        LabeledSlider(
            label = bt("Group position (from bottom)"),
            valueText = "${settings.bottomPercent}%",
            value = settings.bottomPercent.toFloat(),
            range = 0f..BilingualSubtitleSettings.MAX_BOTTOM_PERCENT.toFloat(),
            onChange = { v -> store.update { it.copy(bottomPercent = v.roundToInt()) } }
        )
        LabeledSlider(
            label = bt("%s size").format(primaryName),
            valueText = "${settings.original.sizeSp} sp",
            value = settings.original.sizeSp.toFloat(),
            range = BilingualLineStyle.MIN_SIZE_SP.toFloat()..BilingualLineStyle.MAX_SIZE_SP.toFloat(),
            onChange = { v -> store.update { it.copy(original = it.original.copy(sizeSp = v.roundToInt())) } }
        )
        LabeledSlider(
            label = bt("%s size").format(secondaryName),
            valueText = "${settings.translation.sizeSp} sp",
            value = settings.translation.sizeSp.toFloat(),
            range = BilingualLineStyle.MIN_SIZE_SP.toFloat()..BilingualLineStyle.MAX_SIZE_SP.toFloat(),
            onChange = { v -> store.update { it.copy(translation = it.translation.copy(sizeSp = v.roundToInt())) } }
        )
        ColorPicker(
            label = bt("%s color").format(primaryName),
            hex = settings.original.colorHex,
            onPick = { h -> store.update { it.copy(original = it.original.copy(colorHex = h)) } }
        )
        ColorPicker(
            label = bt("%s color").format(secondaryName),
            hex = settings.translation.colorHex,
            onPick = { h -> store.update { it.copy(translation = it.translation.copy(colorHex = h)) } }
        )

        ToggleLine(
            label = bt("Advanced settings"),
            checked = advanced,
            onToggle = { advanced = it }
        )
        if (advanced) {
            LanguageSection(
                title = "$primaryName (primary)",
                style = settings.original,
                onChange = { s -> store.update { it.copy(original = s) } }
            )
            LanguageSection(
                title = "$secondaryName (secondary)",
                style = settings.translation,
                onChange = { s -> store.update { it.copy(translation = s) } }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(CardBg)
                .clickable { store.resetToDefaults() }
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Text(bt("Reset bilingual style to defaults"), color = InkDim, fontSize = 13.sp)
        }
        Text(
            bt("Applies to the bilingual layer (Smart Bilingual or AI Translation). Normal subtitle tracks keep ARVIO's own subtitle style. ARVIO's own \"Secondary subtitle language\" (subtitle search filter) is a different setting."),
            color = InkDim,
            fontSize = 11.sp
        )
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun Preview(settings: BilingualSubtitleSettings) {
    val frame = remember(settings.primaryLanguage, settings.secondaryLanguage) {
        BilingualFrame(
            generation = 1,
            sourceKey = "preview",
            original = previewLine(settings.primaryExplicit ?: SubtitleLanguage.ENGLISH),
            translation = previewLine(settings.secondary),
        )
    }
    val blocks = BilingualComposer.compose(frame, settings)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Brush.verticalGradient(listOf(Color(0xFF3A4A5C), Color(0xFF1B1F24))))
    ) {
        Text(bt("Preview"), color = Color.White.copy(alpha = 0.5f), fontSize = 10.sp, modifier = Modifier.padding(8.dp))
        if (blocks.isEmpty()) {
            Text(
                bt("Subtitles off"),
                color = InkDim,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            // Positions in the preview are scaled to its small height, sizes are not.
            BilingualSubtitleGroup(
                blocks = blocks,
                settings = settings,
                scale = 0.75f,
                modifier = Modifier.fillMaxWidth().height(150.dp),
            )
        }
    }
}

@Composable
private fun LanguageSection(title: String, style: BilingualLineStyle, onChange: (BilingualLineStyle) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(CardBg)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(title, color = Ink, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        ToggleLine(bt("Show"), style.visible) { onChange(style.copy(visible = it)) }
        LabeledSlider(
            label = bt("Extra vertical offset"),
            valueText = "${style.offsetDp} dp",
            value = style.offsetDp.toFloat(),
            range = -BilingualLineStyle.MAX_OFFSET_DP.toFloat()..BilingualLineStyle.MAX_OFFSET_DP.toFloat(),
            onChange = { onChange(style.copy(offsetDp = it.roundToInt())) }
        )
        ToggleLine(bt("Bold"), style.bold) { onChange(style.copy(bold = it)) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    val all = SubtitleFontOption.entries
                    val idx = all.indexOf(SubtitleFontOption.fromPreference(style.font))
                    onChange(style.copy(font = all[(idx + 1) % all.size].preferenceValue))
                }
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(bt("Font (tap to change)"), color = Ink, fontSize = 13.sp)
            Text(SubtitleFontOption.fromPreference(style.font).preferenceValue, color = Accent, fontSize = 13.sp)
        }
        Text(bt("Outline"), color = Ink, fontSize = 13.sp)
        ChipRow(
            options = SubtitleOutline.entries.map { it to it.name.lowercase().replaceFirstChar { c -> c.uppercase() } },
            selected = style.outline,
            onSelect = { onChange(style.copy(outline = it)) }
        )
        ColorPicker(bt("Outline color"), style.outlineColorHex) { onChange(style.copy(outlineColorHex = it)) }
        ToggleLine(bt("Shadow"), style.shadow) { onChange(style.copy(shadow = it)) }
        ToggleLine(bt("Background box"), style.background) { onChange(style.copy(background = it)) }
    }
}

@Composable
private fun ColorPicker(label: String, hex: String, onPick: (String) -> Unit) {
    var text by remember(hex) { mutableStateOf(hex) }
    val valid = BilingualColors.normalizeHex(text) != null
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, color = Ink, fontSize = 13.sp)
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BilingualColors.PRESETS.forEach { (_, preset) ->
                val selected = preset.equals(hex, ignoreCase = true)
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color(BilingualColors.parseArgb(preset)))
                        .border(if (selected) 3.dp else 1.dp, if (selected) Accent else Color.White.copy(alpha = 0.4f), CircleShape)
                        .clickable { onPick(preset) }
                )
            }
            OutlinedTextField(
                value = text,
                onValueChange = { v ->
                    text = v.take(9)
                    BilingualColors.normalizeHex(text)?.let(onPick)
                },
                singleLine = true,
                isError = !valid,
                label = { Text("HEX", fontSize = 10.sp) },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Ink,
                    unfocusedTextColor = Ink,
                    focusedBorderColor = Accent,
                    unfocusedBorderColor = InkDim,
                    focusedLabelColor = Accent,
                    unfocusedLabelColor = InkDim,
                    cursorColor = Accent,
                ),
                modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Composable
private fun <T> ChipRow(options: List<Pair<T, String>>, selected: T, onSelect: (T) -> Unit) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { (value, label) ->
            val isSel = value == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSel) Accent else CardBg)
                    .clickable { onSelect(value) }
                    .padding(horizontal = 12.dp, vertical = 7.dp)
            ) {
                Text(label, color = if (isSel) Color.Black else Ink, fontSize = 12.5.sp, fontWeight = if (isSel) FontWeight.SemiBold else FontWeight.Normal)
            }
        }
    }
}

@Composable
private fun LabeledSlider(
    label: String,
    valueText: String,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    onChange: (Float) -> Unit,
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, color = Ink, fontSize = 13.sp)
            Text(valueText, color = Accent, fontSize = 13.sp)
        }
        Slider(
            value = value.coerceIn(range.start, range.endInclusive),
            onValueChange = onChange,
            valueRange = range,
            colors = SliderDefaults.colors(
                thumbColor = Accent,
                activeTrackColor = Accent,
                inactiveTrackColor = Color.White.copy(alpha = 0.2f),
            ),
            modifier = Modifier.height(32.dp)
        )
    }
}

@Composable
private fun ToggleLine(label: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle(!checked) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Ink, fontSize = 13.sp)
        Switch(
            checked = checked,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(checkedTrackColor = Accent, checkedThumbColor = Color.Black)
        )
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text.uppercase(), color = InkDim, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.8.sp)
}

@Composable
private fun Hint(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Warn.copy(alpha = 0.15f))
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Text(text, color = Warn, fontSize = 12.5.sp)
    }
}

/** Sample line for the preview in [lang] (falls back to English). */
private fun previewLine(lang: SubtitleLanguage): String = when {
    lang.base == "zh" && lang.script == SubtitleLanguage.HANT -> "凜冬將至，而我們還沒有準備好。"
    lang.base == "zh" -> "凛冬将至，而我们还没有准备好。"
    lang.base == "ja" -> "冬が来る、そして私たちはまだ準備ができていない。"
    lang.base == "ko" -> "겨울이 오고 있는데, 우리는 아직 준비가 안 됐어."
    lang.base == "es" -> "Se acerca el invierno y no estamos preparados."
    lang.base == "fr" -> "L'hiver arrive, et nous ne sommes pas prêts."
    lang.base == "de" -> "Der Winter naht, und wir sind nicht bereit."
    else -> "Winter is coming, and we are not ready."
}

/** Player-side requests from the settings panel (decoupled: the panel is also used outside the player). */
object BilingualPlayerBridge {
    val requests = kotlinx.coroutines.flow.MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    fun requestSmartMode() { requests.tryEmit(Unit) }

    /** A text subtitle track of the playing video the user can assign to a role. */
    data class TrackChoice(val id: String, val label: String, val mergeable: Boolean)

    /** Text tracks of the current video (published by the player; empty outside it). */
    val tracks = kotlinx.coroutines.flow.MutableStateFlow<List<TrackChoice>>(emptyList())
    /** Per-video manual track choices (null = automatic). Reset when the video changes. */
    val primaryOverride = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)
    val secondaryOverride = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)
    /** What the bilingual layer is doing right now (one line, for the panel). */
    val status = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)

}

