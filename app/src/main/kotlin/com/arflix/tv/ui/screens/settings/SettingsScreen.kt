package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Dns
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Subtitles
import androidx.compose.material.icons.outlined.SystemUpdate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.Profile
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.appBackgroundDark

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    currentProfile: Profile? = null,
    initialSection: String? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val sections = listOf(
        SettingsSection(
            icon = Icons.Outlined.Person,
            title = "Profile",
            rows = listOf(
                "Active profile" to (currentProfile?.name ?: "No profile selected")
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.PlayCircle,
            title = "Playback",
            rows = listOf(
                "Autoplay next" to uiState.autoPlayNext.asEnabledLabel(),
                "Single source autoplay" to uiState.autoPlaySingleSource.asEnabledLabel(),
                "Frame rate matching" to uiState.frameRateMatchingMode,
                "Volume boost" to "${uiState.volumeBoostDb} dB"
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Subtitles,
            title = "Subtitles",
            rows = listOf(
                "Default subtitle" to uiState.defaultSubtitle,
                "Secondary subtitle" to uiState.secondarySubtitle,
                "AI subtitles" to uiState.subtitleAiEnabled.asEnabledLabel(),
                "AI model" to uiState.subtitleAiModel.name
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Extension,
            title = "Stremio addons",
            rows = listOf(
                "Installed addons" to uiState.addons.size.toString(),
                "Catalogs" to uiState.catalogs.size.toString()
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Palette,
            title = "Appearance",
            rows = listOf(
                "Theme accent" to uiState.accentColor,
                "OLED black" to uiState.oledBlackBackground.asEnabledLabel(),
                "Clock" to uiState.clockFormat,
                "Language" to uiState.contentLanguage
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Dns,
            title = "Network",
            rows = listOf(
                "DNS provider" to uiState.dnsProvider,
                "Custom user agent" to uiState.customUserAgent.ifBlank { "Default" }
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.SystemUpdate,
            title = "Updates",
            rows = listOf(
                "Self update" to uiState.isSelfUpdateSupported.asEnabledLabel(),
                "Status" to uiState.updateStatus.toString()
            )
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        appBackgroundDark(),
                        Color(0xFF080A10),
                        Color.Black
                    )
                )
            )
            .padding(horizontal = 48.dp, vertical = 36.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SettingsHeader(
                onBack = onBack,
                onNavigateToHome = onNavigateToHome,
                onNavigateToSearch = onNavigateToSearch,
                onNavigateToWatchlist = onNavigateToWatchlist,
                onSwitchProfile = onSwitchProfile
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        text = initialSection?.let { "Settings / ${it.replace('_', ' ')}" } ?: "Settings",
                        color = TextPrimary,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Core controls for the stripped-down Arvio experience.",
                        color = TextSecondary,
                        fontSize = 15.sp
                    )
                }

                items(sections) { section ->
                    SettingsSectionCard(section)
                }
            }
        }
    }
}

@Composable
private fun SettingsHeader(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToWatchlist: () -> Unit,
    onSwitchProfile: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            HeaderIcon(Icons.Outlined.ArrowBack, onBack)
            HeaderIcon(Icons.Outlined.Home, onNavigateToHome)
            HeaderIcon(Icons.Outlined.Search, onNavigateToSearch)
            HeaderIcon(Icons.Outlined.Bookmark, onNavigateToWatchlist)
        }
        HeaderIcon(Icons.Outlined.Person, onSwitchProfile)
    }
}

@Composable
private fun HeaderIcon(icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.10f))
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = TextPrimary)
    }
}

private data class SettingsSection(
    val icon: ImageVector,
    val title: String,
    val rows: List<Pair<String, String>>
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsSectionCard(section: SettingsSection) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White.copy(alpha = 0.07f))
            .padding(18.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White.copy(alpha = 0.12f))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = section.icon, contentDescription = null, tint = TextPrimary)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = section.title,
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            section.rows.forEach { (label, value) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = label, color = TextSecondary, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = value,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

private fun Boolean.asEnabledLabel(): String = if (this) "On" else "Off"
