package com.arflix.tv.ui.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.CommunityAddon
import com.arflix.tv.data.model.CommunityAddons
import com.arflix.tv.data.model.settingsPageUrl
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.TextInputModal
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.delay
import java.util.Locale

/**
 * Full-screen browser for the Stremio community addon catalog, like Addons > Community in
 * Stremio. Picking an addon opens its details, where it is installed, removed or configured.
 * Works with touch and with the D-pad: on TV every control is a regular Compose focus target.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun CommunityAddonsBrowser(
    addons: List<CommunityAddon>,
    installedAddons: List<Addon>,
    isLoading: Boolean,
    failed: Boolean,
    busyUrls: Set<String>,
    onRetry: () -> Unit,
    onInstall: (CommunityAddon) -> Unit,
    onUninstall: (CommunityAddon) -> Unit,
    onOpenUrl: (String) -> Unit,
    onDismiss: () -> Unit,
    overlay: @Composable () -> Unit = {}
) {
    val isTouch = LocalDeviceType.current.isTouchDevice()
    var query by rememberSaveable { mutableStateOf("") }
    var typeFilter by rememberSaveable { mutableStateOf<String?>(null) }
    var isSearchOpen by remember { mutableStateOf(false) }
    var selectedUrl by remember { mutableStateOf<String?>(null) }
    /** Name and settings page of the addon shown as a QR code (TV only). */
    var qrTarget by remember { mutableStateOf<Pair<String, String>?>(null) }
    var lastFocusedUrl by remember { mutableStateOf<String?>(null) }

    val typeFilters = remember(addons) { CommunityAddons.typeFilters(addons) }
    val visible = remember(addons, query, typeFilter) { CommunityAddons.filter(addons, query, typeFilter) }
    val installedIds = remember(installedAddons) { installedAddons.mapNotNull { it.manifest?.id }.toSet() }
    val selected = addons.firstOrNull { it.transportUrl == selectedUrl }

    val searchRequester = remember { FocusRequester() }
    val rowRequesters = remember { mutableMapOf<String, FocusRequester>() }

    // TV: start on the search field, and come back to the last addon when a dialog closes.
    // The dialog window composes a frame or two later, so retry until the target is attached.
    LaunchedEffect(isSearchOpen, selectedUrl, qrTarget) {
        if (isTouch || isSearchOpen || selectedUrl != null || qrTarget != null) return@LaunchedEffect
        repeat(6) {
            delay(50)
            val row = lastFocusedUrl?.let { rowRequesters[it] }
            if (runCatching { (row ?: searchRequester).requestFocus() }.isSuccess) return@LaunchedEffect
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
            // Fitting the keyboard would pad this window with a see-through strip that shows
            // the screen behind it, so draw edge to edge and pad the content ourselves.
            decorFitsSystemWindows = false
        )
    ) {
        BackHandler {
            if (isSearchOpen) isSearchOpen = false else onDismiss()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDark)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(
                        horizontal = if (isTouch) 16.dp else 48.dp,
                        vertical = if (isTouch) 12.dp else 28.dp
                    )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isTouch) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable(onClick = onDismiss),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = TextPrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Column {
                        Text(
                            text = stringResource(R.string.settings_community_addons),
                            style = ArflixTypography.sectionTitle,
                            color = TextPrimary
                        )
                        if (addons.isNotEmpty()) {
                            Text(
                                text = stringResource(R.string.settings_community_addons_count, addons.size),
                                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                                color = TextSecondary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                CommunitySearchField(
                    query = query,
                    focusRequester = searchRequester,
                    showClear = isTouch && query.isNotBlank(),
                    onClick = { isSearchOpen = true },
                    onClear = { query = "" }
                )

                if (typeFilters.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CommunityFilterChip(
                            label = stringResource(R.string.settings_community_addons_all),
                            selected = typeFilter == null,
                            onClick = { typeFilter = null }
                        )
                        typeFilters.forEach { type ->
                            CommunityFilterChip(
                                label = typeLabel(type),
                                selected = typeFilter == type,
                                onClick = { typeFilter = if (typeFilter == type) null else type }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                when {
                    addons.isEmpty() && isLoading -> Box(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator(size = 48.dp)
                    }
                    addons.isEmpty() && failed -> Column(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.settings_community_addons_failed),
                            style = ArflixTypography.body,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CommunityFilterChip(
                            label = stringResource(R.string.retry),
                            selected = true,
                            onClick = onRetry
                        )
                    }
                    visible.isEmpty() && addons.isNotEmpty() -> Box(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = stringResource(R.string.settings_community_addons_empty),
                            style = ArflixTypography.body,
                            color = TextSecondary,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                    }
                    else -> LazyColumn(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        items(visible, key = { it.transportUrl }) { addon ->
                            val requester = remember { FocusRequester() }
                            DisposableEffect(addon.transportUrl) {
                                rowRequesters[addon.transportUrl] = requester
                                onDispose { rowRequesters.remove(addon.transportUrl) }
                            }
                            CommunityAddonRow(
                                addon = addon,
                                installed = addon.manifestId in installedIds,
                                busy = addon.transportUrl in busyUrls,
                                focusRequester = requester,
                                onFocused = { lastFocusedUrl = addon.transportUrl },
                                onClick = { selectedUrl = addon.transportUrl }
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier.fillMaxSize().imePadding()) {
                TextInputModal(
                    isVisible = isSearchOpen,
                    title = stringResource(R.string.settings_community_addons_search),
                    initialValue = query,
                    onConfirm = {
                        query = it.trim()
                        isSearchOpen = false
                    },
                    onCancel = { isSearchOpen = false }
                )
            }

            overlay()
        }

        if (selected != null) {
            // An installed setup keeps its own settings in its URL, so configure that one.
            val setupSettingsUrl = selected.installedSetups(installedAddons)
                .firstNotNullOfOrNull { it.settingsPageUrl }
            CommunityAddonDetailsDialog(
                addon = selected,
                configureUrl = setupSettingsUrl ?: selected.configureUrl,
                installed = selected.manifestId in installedIds,
                busy = selected.transportUrl in busyUrls,
                onInstall = {
                    selectedUrl = null
                    onInstall(selected)
                },
                onUninstall = {
                    selectedUrl = null
                    onUninstall(selected)
                },
                onConfigure = { url ->
                    selectedUrl = null
                    if (isTouch) onOpenUrl(url) else qrTarget = selected.name to url
                },
                onDismiss = { selectedUrl = null }
            )
        }

        qrTarget?.let { (name, url) ->
            AddonConfigureQrDialog(
                addonName = name,
                url = url,
                onDismiss = { qrTarget = null }
            )
        }
    }
}

/** A content type's display name. Addons may invent their own types; those are shown as written. */
@Composable
private fun typeLabel(type: String): String {
    val known = when (type) {
        "movie" -> R.string.settings_community_addon_type_movie
        "series" -> R.string.settings_community_addon_type_series
        "anime" -> R.string.settings_community_addon_type_anime
        "subtitles" -> R.string.settings_community_addon_type_subtitles
        "other" -> R.string.settings_community_addon_type_other
        "channel" -> R.string.settings_community_addon_type_channel
        "tv" -> R.string.settings_community_addon_type_tv
        "music" -> R.string.settings_community_addon_type_music
        else -> null
    }
    return known?.let { stringResource(it) }
        ?: type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CommunitySearchField(
    query: String,
    focusRequester: FocusRequester,
    showClear: Boolean,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(fallback = Pink)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { focused = it.isFocused }
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = if (focused) 0.12f else 0.06f))
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) accent else Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = query.ifBlank { stringResource(R.string.settings_community_addons_search) },
            style = ArflixTypography.body,
            color = if (query.isBlank()) TextSecondary else TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        if (showClear) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onClear),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Close, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CommunityFilterChip(label: String, selected: Boolean, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(fallback = Pink)
    Box(
        modifier = Modifier
            .onFocusChanged { focused = it.isFocused }
            .clip(RoundedCornerShape(999.dp))
            .background(
                when {
                    selected -> Color.White
                    focused -> Color.White.copy(alpha = 0.16f)
                    else -> Color.White.copy(alpha = 0.06f)
                }
            )
            .border(
                width = if (focused) 2.dp else 0.dp,
                color = if (focused) accent else Color.Transparent,
                shape = RoundedCornerShape(999.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = ArflixTypography.button.copy(fontSize = 14.sp),
            color = if (selected) Color.Black else TextPrimary,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CommunityChip(text: String, background: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(background, RoundedCornerShape(999.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(fontSize = 11.sp),
            color = textColor,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CommunityAddonRow(
    addon: CommunityAddon,
    installed: Boolean,
    busy: Boolean,
    focusRequester: FocusRequester,
    onFocused: () -> Unit,
    onClick: () -> Unit
) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(fallback = Pink)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged {
                focused = it.isFocused
                if (it.isFocused) onFocused()
            }
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = if (focused) 0.12f else 0.05f))
            .border(
                width = if (focused) 2.dp else 0.dp,
                color = if (focused) accent else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.White.copy(alpha = 0.06f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            AddonLogo(logoUrl = addon.logo, size = 40.dp, fallbackTint = TextSecondary)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = addon.name,
                    style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                if (addon.version.isNotBlank()) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "v${addon.version}",
                        style = ArflixTypography.caption.copy(fontSize = 12.sp),
                        color = TextSecondary,
                        maxLines = 1
                    )
                }
            }
            if (addon.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = addon.description,
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                addon.types.take(3).forEach { type ->
                    CommunityChip(typeLabel(type), Color.White.copy(alpha = 0.08f), TextSecondary)
                }
                when {
                    addon.configurationRequired -> CommunityChip(
                        stringResource(R.string.settings_addon_setup_required),
                        AddonSetupRequiredColor,
                        Color.Black
                    )
                    addon.configurable -> CommunityChip(
                        stringResource(R.string.settings_community_addon_configurable),
                        Color(0xFF2563EB).copy(alpha = 0.18f),
                        Color(0xFF93C5FD)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        when {
            busy -> LoadingIndicator(size = 22.dp, strokeWidth = 2.dp)
            installed -> Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(R.string.settings_status_installed),
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = SuccessGreen,
                    maxLines = 1
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CommunityAddonDetailsDialog(
    addon: CommunityAddon,
    configureUrl: String?,
    installed: Boolean,
    busy: Boolean,
    onInstall: () -> Unit,
    onUninstall: () -> Unit,
    onConfigure: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val install = stringResource(R.string.settings_addon_install)
    val uninstall = stringResource(R.string.settings_community_addon_uninstall)
    val configure = stringResource(R.string.settings_community_addon_configure)
    val close = stringResource(R.string.close)
    val options = buildList {
        if (!busy) {
            if (installed) {
                if (configureUrl != null) add(DialogOption(configure, true) { onConfigure(configureUrl) })
                add(DialogOption(uninstall, configureUrl == null, onUninstall))
            } else if (addon.configurationRequired) {
                if (configureUrl != null) add(DialogOption(configure, true) { onConfigure(configureUrl) })
            } else {
                add(DialogOption(install, true, onInstall))
                if (configureUrl != null) add(DialogOption(configure, false) { onConfigure(configureUrl) })
            }
        }
        add(DialogOption(close, false, onDismiss))
    }

    AddonDialogFrame(onDismiss = onDismiss, options = options, tvWidth = 520.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.White.copy(alpha = 0.06f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                AddonLogo(logoUrl = addon.logo, size = 48.dp, fallbackTint = TextSecondary)
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = addon.name,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                val meta = listOfNotNull(
                    addon.version.takeIf { it.isNotBlank() }?.let { "v$it" },
                    addon.types.map { typeLabel(it) }.takeIf { it.isNotEmpty() }?.joinToString(", ")
                ).joinToString(" · ")
                if (meta.isNotEmpty()) {
                    Text(
                        text = meta,
                        style = ArflixTypography.caption.copy(fontSize = 13.sp),
                        color = TextSecondary
                    )
                }
            }
            if (busy) {
                Spacer(modifier = Modifier.width(12.dp))
                LoadingIndicator(size = 24.dp, strokeWidth = 2.dp)
            }
        }
        if (addon.description.isNotBlank()) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = addon.description,
                style = ArflixTypography.body.copy(fontSize = 14.sp),
                color = TextPrimary
            )
        }
        if (addon.configurationRequired && !installed) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.settings_community_addon_setup_hint),
                style = ArflixTypography.body.copy(fontSize = 14.sp),
                color = AddonSetupRequiredColor
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = addon.transportUrl,
            style = ArflixTypography.caption.copy(fontSize = 12.sp),
            color = TextSecondary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.settings_community_addon_disclaimer),
            style = ArflixTypography.caption.copy(fontSize = 12.sp),
            color = TextSecondary
        )
    }
}
