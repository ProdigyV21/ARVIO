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
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Subtitles
import androidx.compose.material.icons.outlined.SystemUpdate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.ui.components.TextInputModal
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.appBackgroundDark

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    initialSection: String? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showTmdbKeyDialog by remember { mutableStateOf(false) }
    var showWatchmodeKeyDialog by remember { mutableStateOf(false) }
    var showAddonUrlDialog by remember { mutableStateOf(false) }

    val sections = listOf(
        SettingsSection(
            icon = Icons.Outlined.PlayCircle,
            title = stringResource(R.string.playback),
            rows = listOf(
                SettingsRow("Autoplay next", uiState.autoPlayNext.asEnabledLabel()),
                SettingsRow("Single source autoplay", uiState.autoPlaySingleSource.asEnabledLabel()),
                SettingsRow("Frame rate matching", uiState.frameRateMatchingMode),
                SettingsRow("Volume boost", "${uiState.volumeBoostDb} dB")
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Subtitles,
            title = stringResource(R.string.subtitles),
            rows = listOf(
                SettingsRow("Default subtitle", uiState.defaultSubtitle),
                SettingsRow("Secondary subtitle", uiState.secondarySubtitle),
                SettingsRow("AI subtitles", uiState.subtitleAiEnabled.asEnabledLabel()),
                SettingsRow("AI model", uiState.subtitleAiModel.name)
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Key,
            title = "API keys",
            rows = listOf(
                SettingsRow(
                    label = "TMDB API key",
                    value = uiState.tmdbApiKeyStatus,
                    onClick = { showTmdbKeyDialog = true }
                ),
                SettingsRow(
                    label = "Watchmode API key",
                    value = uiState.watchmodeApiKeyStatus,
                    onClick = { showWatchmodeKeyDialog = true }
                )
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Extension,
            title = "Stremio addons",
            rows = listOf(
                SettingsRow("Installed addons", uiState.addons.size.toString()),
                SettingsRow("Catalogs", uiState.catalogs.size.toString()),
                SettingsRow(
                    label = "Add Stremio/AIOStreams addon",
                    value = if (uiState.isInstallingAddon) {
                        "Installing..."
                    } else {
                        uiState.addonInstallStatus.ifBlank { "Paste URL" }
                    },
                    onClick = if (uiState.isInstallingAddon) {
                        null
                    } else {
                        {
                            viewModel.clearAddonInstallStatus()
                            showAddonUrlDialog = true
                        }
                    }
                )
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Palette,
            title = stringResource(R.string.interface_label),
            rows = listOf(
                SettingsRow("Theme accent", uiState.accentColor),
                SettingsRow("OLED black", uiState.oledBlackBackground.asEnabledLabel()),
                SettingsRow("Clock", uiState.clockFormat),
                SettingsRow("Language", uiState.contentLanguage)
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.Dns,
            title = stringResource(R.string.network),
            rows = listOf(
                SettingsRow("DNS provider", uiState.dnsProvider),
                SettingsRow("Custom user agent", uiState.customUserAgent.ifBlank { "Default" })
            )
        ),
        SettingsSection(
            icon = Icons.Outlined.SystemUpdate,
            title = "Updates",
            rows = listOf(
                SettingsRow("Self update", uiState.isSelfUpdateSupported.asEnabledLabel()),
                SettingsRow("Status", uiState.updateStatus.toString())
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
                onNavigateToWatchlist = onNavigateToWatchlist
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Text(
                        text = initialSection?.let { "${stringResource(R.string.settings)} / ${it.replace('_', ' ')}" }
                            ?: stringResource(R.string.settings),
                        color = TextPrimary,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                items(sections) { section ->
                    SettingsSectionCard(section)
                }
            }
        }

        TextInputModal(
            isVisible = showTmdbKeyDialog,
            title = "TMDB API key",
            hint = "Paste TMDB v3 API key",
            initialValue = "",
            isPassword = true,
            onConfirm = { value ->
                viewModel.saveTmdbApiKey(value)
                showTmdbKeyDialog = false
            },
            onCancel = { showTmdbKeyDialog = false }
        )
        TextInputModal(
            isVisible = showWatchmodeKeyDialog,
            title = "Watchmode API key",
            hint = "Paste Watchmode API key",
            initialValue = "",
            isPassword = true,
            onConfirm = { value ->
                viewModel.saveWatchmodeApiKey(value)
                showWatchmodeKeyDialog = false
            },
            onCancel = { showWatchmodeKeyDialog = false }
        )
        TextInputModal(
            isVisible = showAddonUrlDialog,
            title = "Addon URL",
            hint = "Paste Stremio manifest URL",
            initialValue = "",
            isPassword = true,
            onConfirm = { value ->
                viewModel.installAddonFromUrl(value)
                showAddonUrlDialog = false
            },
            onCancel = { showAddonUrlDialog = false }
        )
    }
}

@Composable
private fun SettingsHeader(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToWatchlist: () -> Unit
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
    val rows: List<SettingsRow>
)

private data class SettingsRow(
    val label: String,
    val value: String,
    val onClick: (() -> Unit)? = null
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
            section.rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .then(
                            if (row.onClick != null) {
                                Modifier
                                    .clickable { row.onClick.invoke() }
                                    .padding(horizontal = 8.dp, vertical = 6.dp)
                            } else {
                                Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                            }
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = row.label, color = TextSecondary, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = row.value,
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
