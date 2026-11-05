package com.arflix.tv.ui.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.arflix.tv.ui.theme.contrastingContentColor
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isTouch) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White.copy(alpha = 0.06f))
                                .clickable(onClick = onDismiss),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = TextPrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
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
                    if (isTouch) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White.copy(alpha = 0.06f))
                                .clickable(enabled = !isLoading, onClick = onRetry),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) {
                                LoadingIndicator(size = 18.dp, strokeWidth = 2.dp)
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = stringResource(R.string.refresh),
                                    tint = TextSecondary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                CommunitySearchField(
                    query = query,
                    onQueryChange = { query = it },
                    isTouch = isTouch,
                    focusRequester = searchRequester,
                    showClear = query.isNotBlank(),
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
                            isTouch = isTouch,
                            onClick = { typeFilter = null }
                        )
                        typeFilters.forEach { type ->
                            CommunityFilterChip(
                                label = typeLabel(type),
                                selected = typeFilter == type,
                                isTouch = isTouch,
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
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            LoadingIndicator(size = 48.dp)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.settings_pulling_latest_addon),
                                style = ArflixTypography.caption,
                                color = TextSecondary
                            )
                        }
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
                            isTouch = isTouch,
                            onClick = onRetry
                        )
                    }
                    visible.isEmpty() && addons.isNotEmpty() -> Column(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.settings_community_addons_empty),
                            style = ArflixTypography.body,
                            color = TextSecondary
                        )
                        if (query.isNotBlank() || typeFilter != null) {
                            Spacer(modifier = Modifier.height(16.dp))
                            CommunityFilterChip(
                                label = stringResource(R.string.settings_community_addons_clear_filters),
                                selected = true,
                                isTouch = isTouch,
                                onClick = {
                                    query = ""
                                    typeFilter = null
                                }
                            )
                        }
                    }
                    else -> LazyColumn(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        verticalArrangement = Arrangement.spacedBy(if (isTouch) 8.dp else 10.dp),
                        contentPadding = PaddingValues(bottom = if (isTouch) 32.dp else 24.dp)
                    ) {
                        items(visible, key = { it.transportUrl }) { addon ->
                            val requester = remember { FocusRequester() }
                            DisposableEffect(addon.transportUrl) {
                                rowRequesters[addon.transportUrl] = requester
                                onDispose { rowRequesters.remove(addon.transportUrl) }
                            }
                            val setupSettingsUrl = remember(addon, installedAddons) {
                                addon.installedSetups(installedAddons)
                                    .firstNotNullOfOrNull { it.settingsPageUrl }
                            }
                            CommunityAddonRow(
                                addon = addon,
                                installed = addon.manifestId in installedIds,
                                busy = addon.transportUrl in busyUrls,
                                isTouch = isTouch,
                                configureUrl = setupSettingsUrl ?: addon.configureUrl,
                                focusRequester = requester,
                                onFocused = { lastFocusedUrl = addon.transportUrl },
                                onClick = { selectedUrl = addon.transportUrl },
                                onInstall = { onInstall(addon) },
                                onConfigure = { url ->
                                    if (isTouch) onOpenUrl(url) else qrTarget = addon.name to url
                                }
                            )
                        }
                    }
                }
            }

            if (!isTouch) {
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
    onQueryChange: (String) -> Unit,
    isTouch: Boolean,
    focusRequester: FocusRequester,
    showClear: Boolean,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(fallback = Pink)
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!isTouch) {
                    Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged { focused = it.isFocused }
                        .clickable(onClick = onClick)
                } else {
                    Modifier
                }
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = if (focused) 0.12f else 0.06f))
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) accent else Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = if (isTouch) 10.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            tint = if (focused || query.isNotBlank()) accent else TextSecondary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        if (isTouch) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                if (query.isEmpty()) {
                    Text(
                        text = stringResource(R.string.settings_community_addons_search),
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focused = it.isFocused },
                    textStyle = ArflixTypography.body.copy(color = TextPrimary),
                    cursorBrush = SolidColor(accent),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() })
                )
            }
        } else {
            Text(
                text = query.ifBlank { stringResource(R.string.settings_community_addons_search) },
                style = ArflixTypography.body,
                color = if (query.isBlank()) TextSecondary else TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
        if (showClear) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onClear),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = stringResource(R.string.cancel),
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CommunityFilterChip(
    label: String,
    selected: Boolean,
    isTouch: Boolean,
    onClick: () -> Unit
) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(fallback = Pink)
    val accentContent = contrastingContentColor(accent)
    Box(
        modifier = Modifier
            .then(
                if (!isTouch) {
                    Modifier
                        .onFocusChanged { focused = it.isFocused }
                } else Modifier
            )
            .clip(CircleShape)
            .background(
                when {
                    selected -> accent
                    focused -> Color.White.copy(alpha = 0.16f)
                    else -> Color.White.copy(alpha = 0.06f)
                }
            )
            .border(
                width = if (focused) 2.dp else if (!selected) 1.dp else 0.dp,
                color = if (focused) Color.White else if (!selected) Color.White.copy(alpha = 0.10f) else Color.Transparent,
                shape = CircleShape
            )
            .clickable(onClick = onClick)
            .padding(horizontal = if (isTouch) 14.dp else 16.dp, vertical = if (isTouch) 7.dp else 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = ArflixTypography.button.copy(fontSize = 13.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal),
            color = if (selected) accentContent else TextPrimary,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CommunityChip(text: String, background: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(background, RoundedCornerShape(6.dp))
            .padding(horizontal = 7.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(fontSize = 11.sp, fontWeight = FontWeight.Medium),
            color = textColor,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun CommunityAddonRow(
    addon: CommunityAddon,
    installed: Boolean,
    busy: Boolean,
    isTouch: Boolean,
    configureUrl: String?,
    focusRequester: FocusRequester,
    onFocused: () -> Unit,
    onClick: () -> Unit,
    onInstall: () -> Unit,
    onConfigure: (String) -> Unit
) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(fallback = Pink)
    val accentContent = contrastingContentColor(accent)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!isTouch) {
                    Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            focused = it.isFocused
                            if (it.isFocused) onFocused()
                        }
                } else Modifier
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = if (focused) 0.12f else 0.05f))
            .border(
                width = if (focused) 2.dp else 1.dp,
                color = if (focused) accent else Color.White.copy(alpha = 0.06f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = if (isTouch) 14.dp else 16.dp, vertical = if (isTouch) 12.dp else 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(if (isTouch) 44.dp else 48.dp)
                .background(Color.White.copy(alpha = 0.06f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            AddonLogo(logoUrl = addon.logo, size = if (isTouch) 36.dp else 40.dp, fallbackTint = TextSecondary)
        }
        Spacer(modifier = Modifier.width(if (isTouch) 12.dp else 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = addon.name,
                    style = ArflixTypography.cardTitle.copy(fontSize = 15.sp),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                if (addon.version.isNotBlank()) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "v${addon.version}",
                        style = ArflixTypography.caption.copy(fontSize = 11.sp),
                        color = TextSecondary,
                        maxLines = 1
                    )
                }
            }
            if (addon.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = addon.description,
                    style = ArflixTypography.caption.copy(fontSize = 12.sp, lineHeight = 16.sp),
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                addon.types.take(if (isTouch) 2 else 3).forEach { type ->
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
        Spacer(modifier = Modifier.width(10.dp))
        when {
            busy -> LoadingIndicator(size = 22.dp, strokeWidth = 2.dp)
            installed -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(SuccessGreen.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = SuccessGreen,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = stringResource(R.string.settings_status_installed),
                                style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold),
                                color = SuccessGreen,
                                maxLines = 1
                            )
                        }
                    }
                    if (isTouch && configureUrl != null) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White.copy(alpha = 0.08f))
                                .clickable { onConfigure(configureUrl) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = stringResource(R.string.settings_community_addon_configure),
                                tint = TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
            isTouch && addon.configurationRequired -> {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(AddonSetupRequiredColor)
                        .clickable { onConfigure(configureUrl ?: addon.configureUrl ?: addon.transportUrl) }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.settings_addon_setup),
                        style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        maxLines = 1
                    )
                }
            }
            isTouch -> {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(accent)
                        .clickable { onInstall() }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.settings_addon_install),
                        style = ArflixTypography.caption.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                        color = accentContent,
                        maxLines = 1
                    )
                }
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
        Spacer(modifier = Modifier.height(14.dp))
        val clipboardManager = LocalClipboardManager.current
        var copied by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White.copy(alpha = 0.06f))
                .clickable {
                    clipboardManager.setText(AnnotatedString(addon.transportUrl))
                    copied = true
                }
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (copied) Icons.Default.Check else Icons.Default.ContentCopy,
                contentDescription = null,
                tint = if (copied) SuccessGreen else TextSecondary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (copied) stringResource(R.string.settings_copied_url) else addon.transportUrl,
                style = ArflixTypography.caption.copy(fontSize = 12.sp),
                color = if (copied) SuccessGreen else TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.settings_community_addon_disclaimer),
            style = ArflixTypography.caption.copy(fontSize = 12.sp),
            color = TextSecondary
        )
    }
}
