package com.arflix.tv.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arflix.tv.R
import com.arflix.tv.util.profilesDataStore
import com.arflix.tv.data.repository.effectiveSettingsDataStore as settingsDataStore
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

enum class MobileHomeHeroStyle { IMMERSIVE, POSTER }

internal fun mobileHeroStyleKey(profile: String) = stringPreferencesKey("profile_${profile}_mobile_home_hero_style")
internal fun parseMobileHeroStyle(value: String?) = MobileHomeHeroStyle.entries.firstOrNull { it.name == value }
    ?: MobileHomeHeroStyle.IMMERSIVE

@Composable
fun rememberMobileHomeHeroStyle(): MobileHomeHeroStyle {
    val context = LocalContext.current
    val flow = remember(context) {
        combine(context.profilesDataStore.data, context.settingsDataStore.data) { profiles, settings ->
            val profile = profiles[stringPreferencesKey("active_profile_id")] ?: "default"
            parseMobileHeroStyle(settings[mobileHeroStyleKey(profile)])
        }.distinctUntilChanged()
    }
    return flow.collectAsStateWithLifecycle(MobileHomeHeroStyle.IMMERSIVE).value
}

@Composable
fun MobileHomeHeroStyleSetting() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val style = rememberMobileHomeHeroStyle()
    var open by remember { mutableStateOf(false) }
    MobileSettingsRow(icon = Icons.Outlined.Wallpaper, title = stringResource(R.string.mobile_home_hero_style),
        value = stringResource(if (style == MobileHomeHeroStyle.POSTER) R.string.mobile_hero_poster else R.string.mobile_hero_immersive),
        isToggle = false, onClick = { open = true })
    if (open) androidx.compose.material3.AlertDialog(
        onDismissRequest = { open = false },
        containerColor = Color(0xFF171717),
        titleContentColor = Color.White,
        textContentColor = Color.LightGray,
        title = { androidx.compose.material3.Text(stringResource(R.string.mobile_home_hero_style)) },
        text = {
            androidx.compose.foundation.layout.Column {
                MobileHomeHeroStyle.entries.forEach { option ->
                    val label = stringResource(if (option == MobileHomeHeroStyle.POSTER) R.string.mobile_hero_poster else R.string.mobile_hero_immersive)
                    Row(Modifier.fillMaxWidth().heightIn(min = 56.dp)
                        .selectable(selected = option == style, role = Role.RadioButton, onClick = {
                            scope.launch {
                                val profiles = context.profilesDataStore.data.first()
                                val profile = profiles[stringPreferencesKey("active_profile_id")] ?: "default"
                                context.settingsDataStore.edit { it[mobileHeroStyleKey(profile)] = option.name }
                                open = false
                            }
                        }), verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.Text(label, color = Color.White, modifier = Modifier.weight(1f))
                        RadioButton(selected = option == style, onClick = null,
                            colors = RadioButtonDefaults.colors(selectedColor = Color.White, unselectedColor = Color.Gray))
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = { androidx.compose.material3.TextButton(onClick = { open = false }) {
            androidx.compose.material3.Text(stringResource(R.string.cancel), color = Color.White)
        } }
    )
}
