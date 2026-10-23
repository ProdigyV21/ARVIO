package com.arflix.tv.ui.screens.player.subtitles.bilingual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Subtitles
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.arflix.tv.ui.components.MobileSettingsCategory
import com.arflix.tv.ui.components.MobileSettingsRow

/** Settings → Audio & Subtitles entry: opens the full bilingual settings in a full-screen dialog. */
@Composable
fun BilingualSettingsEntry() {
    val context = LocalContext.current
    val store = remember { BilingualSubtitleSettingsStore.from(context) }
    val settings by store.settings.collectAsState()
    var open by rememberSaveable { mutableStateOf(false) }

    MobileSettingsCategory(title = bt("BILINGUAL SUBTITLES")) {
        MobileSettingsRow(
            icon = Icons.Default.Subtitles,
            title = bt("Bilingual Subtitles"),
            subtitle = bt("Generation mode (Auto / Merge / AI Translate), languages, display mode, styles"),
            value = if (!settings.smartEnabled) bt("Off") else when (settings.generationMode) {
                BilingualGenerationMode.AUTO -> bt("Auto")
                BilingualGenerationMode.MERGE -> bt("Merge")
                BilingualGenerationMode.AI_TRANSLATE -> bt("AI Translate")
            },
            isToggle = false,
            showDivider = false,
            onClick = { open = true }
        )
    }

    if (open) {
        Dialog(
            onDismissRequest = { open = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF111316))
                    .safeDrawingPadding()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            bt("Bilingual Subtitles"),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = bt("Close"),
                            tint = Color.White,
                            modifier = Modifier
                                .clickable { open = false }
                                .padding(6.dp)
                        )
                    }
                    BilingualSubtitleSettingsPanel(modifier = Modifier.fillMaxWidth().weight(1f))
                }
            }
        }
    }
}
