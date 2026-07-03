package com.arflix.tv.ui.screens.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.arflix.tv.BuildConfig
import androidx.compose.foundation.background
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LinkOff
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Subtitles
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speaker
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.Surface
import androidx.compose.foundation.layout.PaddingValues
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Icon
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.QrCodeImage
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.arflix.tv.ui.components.SettingsRow
import com.arflix.tv.ui.components.SettingsToggleRow
import com.arflix.tv.ui.components.localizeSettingValue
import androidx.core.widget.doAfterTextChanged
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogDiscoveryResult
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.QualityFilterConfig
import com.arflix.tv.data.model.RuntimeKind
import com.arflix.tv.data.repository.HomeServerConnection
import com.arflix.tv.data.repository.HomeServerKind
import com.arflix.tv.data.repository.IptvPlaylistEntry
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.CatalogueRowLayoutToggleButton
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.tr
import com.arflix.tv.util.trUpper
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.toggleCatalogueRowLayoutMode
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import kotlin.math.abs
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import com.arflix.tv.network.OkHttpProvider

/**
 * Per-section registry of [BringIntoViewRequester]s keyed by the row's
 * `contentFocusIndex`. Rows register via [settingsFocusSlot]; the scroll
 * effect invokes `bringIntoView()` on the requester for the focused index.
 *
 * This is Compose's native mechanism for nested-scroll focus-follow; it
 * correctly handles variable-height rows and arbitrary nesting depth
 * between the slot and the scrollable ancestor, replacing the old ratio
 * heuristic which failed on variable heights (the root cause of the
 * "focus moves but screen doesn't scroll" bug, e.g. reaching "Account").
 */
@OptIn(ExperimentalFoundationApi::class)
private class SettingsFocusTracker {
    val requesters = mutableStateMapOf<Int, BringIntoViewRequester>()
    fun clear() = requesters.clear()
}

private val LocalSettingsFocusTracker = compositionLocalOf<SettingsFocusTracker?> { null }

private const val ACCOUNT_DELETION_URL = "https://auth.arvio.tv/delete"

private val tvGeneralSectionIds = setOf(
    "language",
    "subtitles",
    "ai_subtitles",
    "playback",
    "appearance",
    "profiles",
    "network"
)

private fun tvGeneralRowsForSection(section: String): List<Int> {
    return when (section) {
        "language" -> listOf(0, 3)
        "subtitles" -> listOf(1, 2, 4, 5, 6, 7, 8, 9)
        "ai_subtitles" -> listOf(28, 29, 30, 31, 32, 33)
        "playback" -> listOf(10, 11, 12, 13, 14, 37, 34, 16, 15, 27)
        "appearance" -> listOf(17, 18, 20, 21, 24, 23, 22, 36)
        "profiles" -> listOf(19)
        "network" -> listOf(25, 26, 35)
        else -> emptyList()
    }
}

private fun orderedIptvGroups(
    playlistId: String,
    availableGroups: List<String>,
    groupOrder: List<String>
): List<String> {
    val explicitOrder = groupOrder
        .map { com.arflix.tv.data.model.PlaylistGroupKey(it) }
        .filter { it.playlistId == playlistId }
        .map { it.groupName }
    return (explicitOrder + availableGroups).distinct()
}

private fun openExternalUrl(context: Context, url: String) {
    runCatching {
        context.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}

@Composable
private fun formatUserAgentPreview(value: String, maxLength: Int): String {
    val displayValue = value.ifBlank { stringResource(R.string.settings_ua_default) }
    val preview = displayValue.take(maxLength)
    return if (displayValue.length > maxLength) "$preview..." else preview
}

/**
 * Attach to the outermost modifier of a focusable settings row so that when
 * it gains focus the scroll container brings it fully into view. Pass the
 * same [index] the parent uses as its `focusedIndex == N` comparator.
 *
 * Sections that don't adopt this modifier fall back to the legacy ratio
 * scroll — this modifier is purely additive, non-regressive.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Modifier.settingsFocusSlot(index: Int): Modifier {
    val tracker = LocalSettingsFocusTracker.current ?: return this
    val requester = remember(index) { BringIntoViewRequester() }
    DisposableEffect(tracker, index, requester) {
        tracker.requesters[index] = requester
        onDispose {
            if (tracker.requesters[index] === requester) {
                tracker.requesters.remove(index)
            }
        }
    }
    return this.bringIntoViewRequester(requester)
}

/**
 * Settings screen
 */
@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    autoStartCloudAuth: Boolean = false,
    initialSection: String? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTelegramSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isRtlLayoutDirection = LocalLayoutDirection.current == LayoutDirection.Rtl

    val sections = remember {
        buildList {
            add("accounts")
            add("profiles")
            add("playback")
            add("language")
            add("subtitles")
            add("ai_subtitles")
            add("iptv")
            add("stremio")
            add("catalogs")
            add("home_server")
            if (BuildConfig.FEATURE_PLUGINS_ENABLED) {
                add("plugins")
            }
            add("appearance")
            add("network")
        }
    }

    val initialSectionIdx = remember(initialSection, sections) {
        initialSection?.let { sections.indexOf(it) }?.takeIf { it >= 0 }
    }

    // Auto-start cloud auth if requested (e.g. from profile selection page)
    LaunchedEffect(autoStartCloudAuth) {
        if (autoStartCloudAuth && !uiState.isLoggedIn) {
            if (isTouchDevice) {
                viewModel.openCloudEmailPasswordDialog()
            } else {
                viewModel.startCloudAuth()
            }
        }
    }

    var isSidebarFocused by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = topBarMaxIndex(hasProfile)
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 5 else 4) } // SETTINGS
    var sectionIndex by remember { mutableIntStateOf(initialSectionIdx ?: 0) }
    var mobilePage by remember {
        mutableStateOf(
            if (initialSection == "iptv") "TV" else "MAIN"
        )
    }
    var contentFocusIndex by remember { mutableIntStateOf(0) }
    var activeZone by remember { mutableStateOf(Zone.CONTENT) }
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    // Sub-focus for addon rows: 0 = toggle, 1 = delete
    var addonActionIndex by remember { mutableIntStateOf(0) }
    // Sub-focus for catalog rows: 0 = edit, 1 = up, 2 = down, 3 = layout, 4 = delete
    var catalogActionIndex by remember { mutableIntStateOf(0) }
    // Sub-focus for IPTV playlist rows: 0 = categories, 1 = enable, 2 = edit, 3 = up, 4 = down, 5 = delete
    // For IPTV category rows: 0 = visibility, 1 = up, 2 = down
    var iptvActionIndex by remember { mutableIntStateOf(0) }
    var showIptvCategoriesSettings by remember { mutableStateOf(false) }
    // Rename dialog state
    var showCatalogRename by remember { mutableStateOf(false) }
    var renameCatalogId by remember { mutableStateOf("") }
    var renameCatalogTitle by remember { mutableStateOf("") }

    // Input modal states
    var showCustomAddonInput by remember { mutableStateOf(false) }
    var customAddonUrl by remember { mutableStateOf("") }
    var showIptvInput by remember { mutableStateOf(false) }
    var editingIptvIndex by remember { mutableIntStateOf(-1) }
    var iptvEditName by remember { mutableStateOf("") }
    var iptvEditUrl by remember { mutableStateOf("") }
    var iptvEditEpg by remember { mutableStateOf("") }
    var iptvEditEnabled by remember { mutableStateOf(true) }
    var iptvEditXtreamUser by remember { mutableStateOf("") }
    var iptvEditXtreamPass by remember { mutableStateOf("") }
    var showCatalogInput by remember { mutableStateOf(false) }
    var catalogInputUrl by remember { mutableStateOf("") }
    var showSubtitlePicker by remember { mutableStateOf(false) }
    var subtitlePickerIndex by remember { mutableIntStateOf(0) }
    var showSecondarySubtitlePicker by remember { mutableStateOf(false) }
    var secondarySubtitlePickerIndex by remember { mutableIntStateOf(0) }
    var showAudioLanguagePicker by remember { mutableStateOf(false) }
    var audioLanguagePickerIndex by remember { mutableIntStateOf(0) }
    var showDnsProviderPicker by remember { mutableStateOf(false) }
    var dnsProviderPickerIndex by remember { mutableIntStateOf(0) }
    var showContentLanguagePicker by remember { mutableStateOf(false) }
    var contentLanguagePickerIndex by remember { mutableIntStateOf(0) }
    var showUiModeWarningDialog by remember { mutableStateOf(false) }
    var nextUiMode by remember { mutableStateOf("") }
    var showQualityFiltersModal by remember { mutableStateOf(false) }
    var showQualityFilterEditor by remember { mutableStateOf(false) }
    var editingQualityFilterId by remember { mutableStateOf<String?>(null) }
    var qualityFilterDeviceName by remember { mutableStateOf("") }
    var showAiModelDialog by remember { mutableStateOf(false) }
    var showAiApiKeyDialog by remember { mutableStateOf(false) }
    var showCustomUserAgentDialog by remember { mutableStateOf(false) }
    var qualityFilterRegexPattern by remember { mutableStateOf("") }
    var showHomeServerInput by remember { mutableStateOf(false) }
    var showPlexHomeServerInput by remember { mutableStateOf(false) }
    var homeServerUrl by remember { mutableStateOf("") }
    var homeServerDisplayName by remember { mutableStateOf("") }
    var homeServerUsername by remember { mutableStateOf("") }
    var homeServerPassword by remember { mutableStateOf("") }
    var plexHomeServerDisplayName by remember { mutableStateOf("") }
    var plexHomeServerUrl by remember { mutableStateOf("") }

    val stremioAddons = remember(uiState.addons) {
        uiState.addons.filter { it.runtimeKind == RuntimeKind.STREMIO }
    }
    val sectionMaxIndex: (String) -> Int = { section ->
        when (section) {
            in tvGeneralSectionIds -> (tvGeneralRowsForSection(section).size - 1).coerceAtLeast(0)
            "iptv" -> if (showIptvCategoriesSettings) {
                orderedIptvGroups(
                    playlistId = uiState.iptvSelectedPlaylistId.orEmpty(),
                    availableGroups = uiState.iptvAvailableGroups,
                    groupOrder = uiState.iptvGroupOrder
                ).size // Reset row + category rows
            } else {
                2 + uiState.iptvPlaylists.size // Add + rows + refresh + clear
            }
            "home_server" -> uiState.homeServerConnections.size + 3
            "catalogs" -> uiState.catalogs.size // Add + rows
            "stremio" -> stremioAddons.size // rows + add button
            "accounts" -> 5 // Cloud + Trakt + Telegram + Force Sync + App Update + Privacy/Data
            else -> 0
        }
    }

    val focusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()
    val sectionScrollState = rememberScrollState()
    val focusTracker = remember { SettingsFocusTracker() }
    val openSubtitlePicker = {
        viewModel.refreshSubtitleOptions()
        val options = uiState.subtitleOptions
        subtitlePickerIndex = options.indexOfFirst { it.equals(uiState.defaultSubtitle, ignoreCase = true) }
            .coerceAtLeast(0)
        showSubtitlePicker = true
    }
    val openSecondarySubtitlePicker = {
        viewModel.refreshSubtitleOptions()
        val options = uiState.subtitleOptions
        secondarySubtitlePickerIndex = options.indexOfFirst { it.equals(uiState.secondarySubtitle, ignoreCase = true) }
            .coerceAtLeast(0)
        showSecondarySubtitlePicker = true
    }
    val openAudioLanguagePicker = {
        viewModel.refreshAudioLanguageOptions()
        val options = uiState.audioLanguageOptions
        audioLanguagePickerIndex = options.indexOfFirst { it.equals(uiState.defaultAudioLanguage, ignoreCase = true) }
            .coerceAtLeast(0)
        showAudioLanguagePicker = true
    }
    val openDnsProviderPicker = {
        val options = uiState.dnsProviderOptions
        dnsProviderPickerIndex = options.indexOfFirst { it.equals(uiState.dnsProvider, ignoreCase = true) }
            .coerceAtLeast(0)
        showDnsProviderPicker = true
    }
    val openIptvCategories: (String) -> Unit = { playlistId ->
        viewModel.setIptvSelectedPlaylistId(playlistId)
        showIptvCategoriesSettings = true
        activeZone = Zone.CONTENT
        contentFocusIndex = 0
        iptvActionIndex = 0
    }
    val openContentLanguagePicker = {
        contentLanguagePickerIndex = TMDB_LANGUAGES.indexOfFirst { it.first == uiState.contentLanguage }.coerceAtLeast(0)
        showContentLanguagePicker = true
    }
    val openUiModeWarningDialog = {
        nextUiMode = when (uiState.deviceModeOverride) {
            "auto" -> "tv"
            "tv" -> "tablet"
            "tablet" -> "phone"
            else -> "auto"
        }
        showUiModeWarningDialog = true
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + 150L
    }

    LaunchedEffect(sections.size) {
        if (sectionIndex > sections.lastIndex) {
            sectionIndex = sections.lastIndex.coerceAtLeast(0)
            contentFocusIndex = 0
        }
    }

    LaunchedEffect(showSubtitlePicker, uiState.subtitleOptions) {
        if (showSubtitlePicker) {
            val options = uiState.subtitleOptions
            val maxIndex = (options.size - 1).coerceAtLeast(0)
            val targetIndex = options.indexOfFirst { it.equals(uiState.defaultSubtitle, ignoreCase = true) }
            subtitlePickerIndex = if (targetIndex >= 0) targetIndex else subtitlePickerIndex.coerceIn(0, maxIndex)
        }
    }

    LaunchedEffect(showSecondarySubtitlePicker, uiState.subtitleOptions) {
        if (showSecondarySubtitlePicker) {
            val options = uiState.subtitleOptions
            val maxIndex = (options.size - 1).coerceAtLeast(0)
            val targetIndex = options.indexOfFirst { it.equals(uiState.secondarySubtitle, ignoreCase = true) }
            secondarySubtitlePickerIndex = if (targetIndex >= 0) targetIndex else secondarySubtitlePickerIndex.coerceIn(0, maxIndex)
        }
    }

    LaunchedEffect(showAudioLanguagePicker, uiState.audioLanguageOptions) {
        if (showAudioLanguagePicker) {
            val options = uiState.audioLanguageOptions
            val maxIndex = (options.size - 1).coerceAtLeast(0)
            val targetIndex = options.indexOfFirst { it.equals(uiState.defaultAudioLanguage, ignoreCase = true) }
            audioLanguagePickerIndex = if (targetIndex >= 0) targetIndex else audioLanguagePickerIndex.coerceIn(0, maxIndex)
        }
    }

    LaunchedEffect(showDnsProviderPicker, uiState.dnsProviderOptions) {
        if (showDnsProviderPicker) {
            val options = uiState.dnsProviderOptions
            val maxIndex = (options.size - 1).coerceAtLeast(0)
            val targetIndex = options.indexOfFirst { it.equals(uiState.dnsProvider, ignoreCase = true) }
            dnsProviderPickerIndex = if (targetIndex >= 0) targetIndex else dnsProviderPickerIndex.coerceIn(0, maxIndex)
        }
    }

    // Reset content scroll AND position cache when switching sections.
    LaunchedEffect(sectionIndex) {
        focusTracker.clear()
        if (scrollState.value != 0) {
            scrollState.scrollTo(0)
        }
    }

    LaunchedEffect(sectionIndex, activeZone, sections.size) {
        if (isTouchDevice || activeZone != Zone.SECTION) return@LaunchedEffect
        val maxScroll = sectionScrollState.maxValue
        if (maxScroll <= 0) return@LaunchedEffect
        val maxIndex = sections.lastIndex.coerceAtLeast(1)
        val ratio = sectionIndex.coerceIn(0, maxIndex).toFloat() / maxIndex.toFloat()
        val targetScroll = (maxScroll * ratio).toInt().coerceIn(0, maxScroll)
        if (abs(sectionScrollState.value - targetScroll) > 24) {
            sectionScrollState.animateScrollTo(targetScroll)
        }
    }

    // Auto-scroll content to keep focused item visible in all sections.
    //
    // Strategy: prefer the per-row [BringIntoViewRequester] registered via
    // Modifier.settingsFocusSlot(...) — this is Compose's native mechanism
    // for nested-scroll focus-follow and correctly handles variable-height
    // rows and arbitrary nesting depth. Sections that haven't adopted the
    // modifier fall back to the legacy ratio heuristic, which is imprecise
    // but non-regressive.
    //
    // Triggers on focus/section change AND on the tracker map itself, so late
    // layout registrations (which happen one frame after composition) still
    // produce a correct scroll.
    LaunchedEffect(
        contentFocusIndex,
        sectionIndex,
        activeZone,
        uiState.catalogs.size,
        uiState.addons.size,
        focusTracker.requesters[contentFocusIndex]
    ) {
        if (activeZone != Zone.CONTENT) return@LaunchedEffect

        val requester = focusTracker.requesters[contentFocusIndex]
        if (requester != null) {
            // Native branch — handles all geometry correctly.
            runCatching { requester.bringIntoView() }
            return@LaunchedEffect
        }

        // Fallback: legacy ratio heuristic, only when positions are unknown
        // and scrolling is actually possible.
        val maxScroll = scrollState.maxValue
        val currentScroll = scrollState.value
        if (maxScroll <= 0) return@LaunchedEffect
        val currentSection = sections.getOrNull(sectionIndex).orEmpty()
        val maxIndex = sectionMaxIndex(currentSection).coerceAtLeast(1)
        val clampedFocus = contentFocusIndex.coerceIn(0, maxIndex)
        val ratio = clampedFocus.toFloat() / maxIndex.toFloat()
        val targetScroll = (maxScroll * ratio).toInt().coerceIn(0, maxScroll)
        if (abs(currentScroll - targetScroll) > 24) {
            scrollState.animateScrollTo(targetScroll)
        }
    }

    LaunchedEffect(uiState.iptvPlaylists, showIptvInput, editingIptvIndex) {
        if (!showIptvInput) {
            editingIptvIndex = -1
            iptvEditName = ""
            iptvEditUrl = ""
            iptvEditEpg = ""
            iptvEditEnabled = true
            iptvEditXtreamUser = ""
            iptvEditXtreamPass = ""
        } else {
            val playlist = uiState.iptvPlaylists.getOrNull(editingIptvIndex)
            iptvEditName = playlist?.name ?: "List ${editingIptvIndex + 2}".takeIf { editingIptvIndex >= 0 } ?: "List ${uiState.iptvPlaylists.size + 1}"
            // When editing, try to extract Xtream credentials from the saved URL
            // (normalizeIptvInput converts "host user pass" to a full get.php URL)
            val savedUrl = playlist?.m3uUrl ?: ""
            val xtreamUri = try { android.net.Uri.parse(savedUrl) } catch (_: Exception) { null }
            val xtreamUser = xtreamUri?.getQueryParameter("username") ?: ""
            val xtreamPass = xtreamUri?.getQueryParameter("password") ?: ""
            val isXtreamUrl = savedUrl.contains("get.php") && xtreamUser.isNotBlank() && xtreamPass.isNotBlank()
            if (isXtreamUrl) {
                // Show just the host:port for Xtream URLs
                val baseUrl = "${xtreamUri!!.scheme}://${xtreamUri.host}${if (xtreamUri.port > 0) ":${xtreamUri.port}" else ""}"
                iptvEditUrl = baseUrl
                iptvEditXtreamUser = xtreamUser
                iptvEditXtreamPass = xtreamPass
            } else {
                iptvEditUrl = savedUrl
                iptvEditXtreamUser = ""
                iptvEditXtreamPass = ""
            }
            iptvEditEpg = playlist?.settingsEpgInput().orEmpty()
            iptvEditEnabled = playlist?.enabled ?: true
        }
    }

    var showCloudDisconnectConfirm by remember { mutableStateOf(false) }
    var showTraktDisconnectConfirm by remember { mutableStateOf(false) }
    var cloudDialogEmail by remember { mutableStateOf("") }
    var cloudDialogPassword by remember { mutableStateOf("") }

    LaunchedEffect(uiState.showCloudEmailPasswordDialog) {
        if (uiState.showCloudEmailPasswordDialog) {
            cloudDialogEmail = ""
            cloudDialogPassword = ""
        }
    }

    LaunchedEffect(uiState.shouldSwitchProfile) {
        if (uiState.shouldSwitchProfile) {
            viewModel.onCloudProfileSwitchHandled()
            onSwitchProfile()
        }
    }

    val hasBlockingModal =
        showCustomAddonInput ||
        showIptvInput ||
        showHomeServerInput ||
        showPlexHomeServerInput ||
        showCatalogInput ||
        showCatalogRename ||
        showSubtitlePicker ||
        showSecondarySubtitlePicker ||
        showAudioLanguagePicker ||
        showDnsProviderPicker ||
        showContentLanguagePicker ||
        showUiModeWarningDialog ||
        showAiModelDialog ||
        showAiApiKeyDialog ||
        showCustomUserAgentDialog ||
        uiState.aiKeyServerState.isActive ||
        uiState.showCloudPairDialog ||
        uiState.showCloudEmailPasswordDialog ||
        uiState.traktCode != null ||
        uiState.plexHomeServerAuth != null ||
        uiState.showAppUpdateDialog ||
        uiState.showUnknownSourcesDialog ||
        showCloudDisconnectConfirm ||
        showTraktDisconnectConfirm

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                    if (isTouchDevice) return@onPreviewKeyEvent false
                    if (hasBlockingModal) return@onPreviewKeyEvent false

                if (event.type == KeyEventType.KeyDown) {
                    val currentSection = sections.getOrNull(sectionIndex).orEmpty()
                    if (currentSection == "plugins" && activeZone == Zone.CONTENT) return@onPreviewKeyEvent false
                    val focusedStremioAddon = stremioAddons.getOrNull(contentFocusIndex)
                    val focusedStremioAddonCanDelete = focusedStremioAddon?.let { addon ->
                        !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
                    } ?: false
                    val focusedStremioAddonMaxAction = if (focusedStremioAddon == null) {
                        0
                    } else if (focusedStremioAddonCanDelete) {
                        3
                    } else {
                        2
                    }

                    val isRtl = isRtlLayoutDirection
                    val actualKey = event.key
                    val logicalKey = if (isRtl) {
                        when (actualKey) {
                            Key.DirectionLeft -> Key.DirectionRight
                            Key.DirectionRight -> Key.DirectionLeft
                            else -> actualKey
                        }
                    } else actualKey

                    when (logicalKey) {
                        Key.Back, Key.Escape -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> onBack()
                                Zone.SECTION -> {
                                    activeZone = Zone.SIDEBAR
                                    isSidebarFocused = true
                                }
                                Zone.CONTENT -> {
                                    if (currentSection == "iptv" && showIptvCategoriesSettings) {
                                        showIptvCategoriesSettings = false
                                        contentFocusIndex = 0
                                        iptvActionIndex = 0
                                    } else {
                                        activeZone = Zone.SECTION
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionLeft -> {
                            when (activeZone) {
                                Zone.CONTENT -> {
                                    if (currentSection == "stremio" && contentFocusIndex < stremioAddons.size && addonActionIndex > 0) {
                                        addonActionIndex--
                                    } else if (currentSection == "iptv" &&
                                        iptvActionIndex > 0 &&
                                        (
                                            showIptvCategoriesSettings && contentFocusIndex > 0 ||
                                                !showIptvCategoriesSettings && contentFocusIndex in 1..uiState.iptvPlaylists.size
                                            )
                                    ) {
                                        iptvActionIndex--
                                    } else if (currentSection == "catalogs" && contentFocusIndex > 0 && catalogActionIndex > 0) {
                                        catalogActionIndex--
                                    } else {
                                        activeZone = Zone.SECTION
                                        addonActionIndex = 0
                                        iptvActionIndex = 0
                                        catalogActionIndex = 0
                                    }
                                }
                                Zone.SECTION -> {
                                    Unit
                                }
                                Zone.SIDEBAR -> {
                                    if (sidebarFocusIndex > 0) {
                                        sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionRight -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> {
                                    if (sidebarFocusIndex < maxSidebarIndex) {
                                        sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
                                    }
                                }
                                Zone.SECTION -> {
                                    activeZone = Zone.CONTENT
                                    addonActionIndex = 0
                                    iptvActionIndex = 0
                                    catalogActionIndex = 0
                                }
                                Zone.CONTENT -> {
                                    if (currentSection == "stremio" &&
                                        contentFocusIndex in 0 until stremioAddons.size &&
                                        addonActionIndex < focusedStremioAddonMaxAction
                                    ) {
                                        addonActionIndex++
                                    } else if (currentSection == "iptv" && showIptvCategoriesSettings && contentFocusIndex > 0 && iptvActionIndex < 2) {
                                        iptvActionIndex++
                                    } else if (currentSection == "iptv" && !showIptvCategoriesSettings && contentFocusIndex in 1..uiState.iptvPlaylists.size && iptvActionIndex < 5) {
                                        iptvActionIndex++
                                    } else if (currentSection == "catalogs" && contentFocusIndex > 0 && catalogActionIndex < 4) {
                                        catalogActionIndex++
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionUp -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> Unit
                                Zone.SECTION -> {
                                    if (sectionIndex > 0) {
                                        sectionIndex--
                                        contentFocusIndex = 0 // Reset content focus when changing section
                                        addonActionIndex = 0
                                        iptvActionIndex = 0
                                        catalogActionIndex = 0
                                        showIptvCategoriesSettings = false
                                    } else {
                                        activeZone = Zone.SIDEBAR
                                        isSidebarFocused = true
                                    }
                                }
                                Zone.CONTENT -> {
                                    if (contentFocusIndex > 0) {
                                        contentFocusIndex--
                                        addonActionIndex = 0 // Reset to toggle when changing rows
                                        iptvActionIndex = 0
                                        catalogActionIndex = 0
                                    } else {
                                        activeZone = Zone.SECTION
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionDown -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> {
                                    activeZone = Zone.SECTION
                                    isSidebarFocused = false
                                }
                                Zone.SECTION -> {
                                    if (sectionIndex < sections.size - 1) {
                                        sectionIndex++
                                        contentFocusIndex = 0 // Reset content focus when changing section
                                        addonActionIndex = 0
                                        iptvActionIndex = 0
                                        catalogActionIndex = 0
                                        showIptvCategoriesSettings = false
                                    }
                                }
                                Zone.CONTENT -> {
                                    val maxIndex = sectionMaxIndex(currentSection)
                                    if (contentFocusIndex < maxIndex) {
                                        contentFocusIndex++
                                        addonActionIndex = 0 // Reset to toggle when changing rows
                                        iptvActionIndex = 0
                                        catalogActionIndex = 0
                                    }
                                }
                            }
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> {
                                    if (hasProfile && sidebarFocusIndex == 0) {
                                        onSwitchProfile()
                                    } else {
                                        when (topBarFocusedItem(sidebarFocusIndex, hasProfile)) {
                                            SidebarItem.SEARCH -> onNavigateToSearch()
                                            SidebarItem.HOME -> onNavigateToHome()
                                            SidebarItem.TV -> onNavigateToTv()
                                            SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                            SidebarItem.SETTINGS -> { /* Already here */ }
                                            null -> Unit
                                        }
                                    }
                                }
                                Zone.SECTION -> activeZone = Zone.CONTENT
                                Zone.CONTENT -> {
                                    when (currentSection) {
                                        in tvGeneralSectionIds -> {
                                            when (tvGeneralRowsForSection(currentSection).getOrNull(contentFocusIndex)) {
                                                0 -> openContentLanguagePicker()
                                                1 -> openSubtitlePicker()
                                                2 -> openSecondarySubtitlePicker()
                                                3 -> openAudioLanguagePicker()
                                                4 -> viewModel.cycleSubtitleSize()
                                                5 -> viewModel.cycleSubtitleColor()
                                                6 -> viewModel.cycleSubtitleOffset()
                                                7 -> viewModel.cycleSubtitleStyle()
                                                8 -> viewModel.toggleSubtitleStylized()
                                                9 -> viewModel.setFilterSubtitlesByLanguage(!uiState.filterSubtitlesByLanguage)
                                                10 -> viewModel.setAutoPlayNext(!uiState.autoPlayNext)
                                                11 -> viewModel.setAutoPlaySingleSource(!uiState.autoPlaySingleSource)
                                                12 -> viewModel.cycleAutoPlayMinQuality()
                                                13 -> viewModel.setTrailerAutoPlay(!uiState.trailerAutoPlay)
                                                14 -> viewModel.setTrailerSoundEnabled(!uiState.trailerSoundEnabled)
                                                15 -> viewModel.cycleFrameRateMatchingMode()
                                                16 -> showQualityFiltersModal = true
                                                17 -> viewModel.toggleCardLayoutMode()
                                                18 -> openUiModeWarningDialog()
                                                19 -> viewModel.setSkipProfileSelection(!uiState.skipProfileSelection)
                                                20 -> viewModel.setOledBlackBackground(!uiState.oledBlackBackground)
                                                21 -> viewModel.cycleClockFormat()
                                                22 -> viewModel.setShowBudget(!uiState.showBudget)
                                                 36 -> viewModel.setSmoothScrolling(!uiState.smoothScrolling)
                                                23 -> viewModel.setSpoilerBlurEnabled(!uiState.spoilerBlurEnabled)
                                                24 -> viewModel.cycleAccentColor()
                                                25 -> openDnsProviderPicker()
                                                26 -> viewModel.setShowLoadingStats(!uiState.showLoadingStats)
                                                35 -> showCustomUserAgentDialog = true
                                                27 -> viewModel.cycleVolumeBoost()
                                                28 -> viewModel.setSubtitleAiEnabled(!uiState.subtitleAiEnabled)
                                                29 -> showAiModelDialog = true
                                                30 -> viewModel.setSubtitleAiAutoSelect(!uiState.subtitleAiAutoSelect)
                                                31 -> viewModel.setSubtitleRemoveHearingImpaired(!uiState.subtitleRemoveHearingImpaired)
                                                32 -> showAiApiKeyDialog = true
                                                33 -> viewModel.startAiKeyServer()
                                                34 -> viewModel.cycleTrailerDelay()
                                                37 -> viewModel.setTrailerInCards(!uiState.trailerInCards)
                                            }
                                        }
                                        "iptv" -> {
                                            if (showIptvCategoriesSettings) {
                                                val playlistId = uiState.iptvSelectedPlaylistId.orEmpty()
                                                val orderedGroups = orderedIptvGroups(
                                                    playlistId = playlistId,
                                                    availableGroups = uiState.iptvAvailableGroups,
                                                    groupOrder = uiState.iptvGroupOrder
                                                )
                                                when {
                                                    contentFocusIndex == 0 -> {
                                                        viewModel.resetIptvGroupOrder(playlistId)
                                                    }
                                                    contentFocusIndex in 1..orderedGroups.size -> {
                                                        val group = orderedGroups.getOrNull(contentFocusIndex - 1)
                                                        if (!group.isNullOrBlank()) {
                                                            when (iptvActionIndex) {
                                                                0 -> viewModel.toggleIptvHiddenGroup(playlistId, group)
                                                                1 -> viewModel.moveIptvGroupUp(playlistId, group)
                                                                2 -> viewModel.moveIptvGroupDown(playlistId, group)
                                                            }
                                                        }
                                                    }
                                                }
                                            } else when {
                                                contentFocusIndex == 0 -> {
                                                    editingIptvIndex = -1
                                                    showIptvInput = true
                                                }
                                                contentFocusIndex in 1..uiState.iptvPlaylists.size -> {
                                                    val idx = contentFocusIndex - 1
                                                    val updated = uiState.iptvPlaylists.toMutableList()
                                                    val playlist = updated.getOrNull(idx)
                                                    if (playlist != null) {
                                                        when (iptvActionIndex) {
                                                            0 -> {
                                                                openIptvCategories(playlist.id)
                                                            }
                                                            1 -> {
                                                                updated[idx] = playlist.copy(enabled = !playlist.enabled)
                                                                viewModel.saveIptvPlaylists(updated)
                                                            }
                                                            2 -> {
                                                                editingIptvIndex = idx
                                                                showIptvInput = true
                                                            }
                                                            3 -> {
                                                                if (idx > 0) {
                                                                    val item = updated.removeAt(idx)
                                                                    updated.add(idx - 1, item)
                                                                    viewModel.saveIptvPlaylists(updated)
                                                                }
                                                            }
                                                            4 -> {
                                                                if (idx < updated.lastIndex) {
                                                                    val item = updated.removeAt(idx)
                                                                    updated.add(idx + 1, item)
                                                                    viewModel.saveIptvPlaylists(updated)
                                                                }
                                                            }
                                                            else -> {
                                                                updated.removeAt(idx)
                                                                viewModel.saveIptvPlaylists(updated)
                                                            }
                                                        }
                                                    }
                                                }
                                                contentFocusIndex == uiState.iptvPlaylists.size + 1 -> {
                                                    viewModel.refreshIptv(force = true)
                                                }
                                                contentFocusIndex == uiState.iptvPlaylists.size + 2 -> {
                                                    viewModel.clearIptvConfig()
                                                }
                                            }
                                        }
                                        "home_server" -> {
                                            when (contentFocusIndex) {
                                       0 -> {
                                           homeServerUrl = ""
                                           homeServerDisplayName = ""
                                           homeServerUsername = ""
                                           homeServerPassword = ""
                                           showHomeServerInput = true
                                       }
                                       1 -> {
                                           plexHomeServerDisplayName = ""
                                           plexHomeServerUrl = ""
                                           showPlexHomeServerInput = true
                                       }
                                       in 2..(uiState.homeServerConnections.size + 1) -> {
                                           val connection = uiState.homeServerConnections.getOrNull(contentFocusIndex - 2)
                                           homeServerUrl = connection?.serverUrl.orEmpty()
                                           homeServerDisplayName = connection?.displayName.orEmpty()
                                           homeServerUsername = connection?.userName.orEmpty()
                                           homeServerPassword = ""
                                           showHomeServerInput = true
                                       }
                                                uiState.homeServerConnections.size + 2 -> viewModel.testHomeServerConnection()
                                                uiState.homeServerConnections.size + 3 -> viewModel.disconnectHomeServer()
                                            }
                                        }
                                        "catalogs" -> {
                                            if (contentFocusIndex == 0) {
                                                showCatalogInput = true
                                            } else {
                                                val catalog = uiState.catalogs.getOrNull(contentFocusIndex - 1)
                                                if (catalog != null) {
                                                    when (catalogActionIndex) {
                                                        0 -> {
                                                            renameCatalogId = catalog.id
                                                            renameCatalogTitle = catalog.title
                                                            showCatalogRename = true
                                                        }
                                                        1 -> viewModel.moveCatalogUp(catalog.id)
                                                        2 -> viewModel.moveCatalogDown(catalog.id)
                                                        3 -> scope.launch {
                                                            if (catalog.kind != CatalogKind.COLLECTION_RAIL) {
                                                                toggleCatalogueRowLayoutMode(context, catalogueLayoutRowKey(catalog))
                                                            }
                                                        }
                                                        else -> viewModel.removeCatalog(catalog.id)
                                                    }
                                                }
                                            }
                                        }
                                        "stremio" -> {
                                            when {
                                                contentFocusIndex in 0 until stremioAddons.size -> {
                                                    val addon = stremioAddons[contentFocusIndex]
                                                    val canDelete = !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
                                                    when (addonActionIndex) {
                                                        0 -> viewModel.toggleAddon(addon.id)
                                                        1 -> viewModel.moveAddonUp(addon.id)
                                                        2 -> viewModel.moveAddonDown(addon.id)
                                                        3 -> if (canDelete) {
                                                            viewModel.removeAddon(addon.id)
                                                            addonActionIndex = 0
                                                            if (contentFocusIndex >= stremioAddons.size && contentFocusIndex > 0) {
                                                                contentFocusIndex--
                                                            }
                                                        }
                                                        else -> viewModel.toggleAddon(addon.id)
                                                    }
                                                }
                                                else -> {
                                                    showCustomAddonInput = true
                                                }
                                            }
                                        }
                                        "accounts" -> {
                                            when (contentFocusIndex) {
                                                0 -> {
                                                    if (uiState.isLoggedIn) {
                                                        showCloudDisconnectConfirm = true
                                                    } else {
                                                        viewModel.startCloudAuth()
                                                    }
                                                }
                                                1 -> {
                                                    if (uiState.isTraktAuthenticated) {
                                                        showTraktDisconnectConfirm = true
                                                    } else if (uiState.isTraktPolling) {
                                                        viewModel.cancelTraktAuth()
                                                    } else {
                                                        viewModel.startTraktAuth()
                                                    }
                                                }
                                                2 -> onNavigateToTelegramSettings()
                                                3 -> viewModel.forceCloudSyncNow()
                                                4 -> {
                                                    if (uiState.updateStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall) {
                                                        viewModel.installAppUpdateOrRequestPermission()
                                                    } else {
                                                        viewModel.checkForAppUpdates(force = true, showNoUpdateFeedback = true)
                                                    }
                                                }
                                            }
                                        }
                                        else -> Unit
                                    }
                                }
                                else -> {}
                            }
                            true
                        }
                        else -> false
                    }
                } else false
            }
    ) {
        if (isTouchDevice) {
            MobileSettingsLayout(
                page = mobilePage,
                onNavigate = { mobilePage = it },
                uiState = uiState,
                viewModel = viewModel,
                stremioAddons = stremioAddons,
                onSwitchProfile = onSwitchProfile,
                openContentLanguagePicker = openContentLanguagePicker,
                openSubtitlePicker = openSubtitlePicker,
                openSecondarySubtitlePicker = openSecondarySubtitlePicker,
                openAudioLanguagePicker = openAudioLanguagePicker,
                openDnsProviderPicker = openDnsProviderPicker,
                openUiModeWarningDialog = openUiModeWarningDialog,
                openQualityFiltersModal = { showQualityFiltersModal = true },
                onSubtitleAiModelClick = { showAiModelDialog = true },
                onSubtitleAiApiKeyClick = { showAiApiKeyDialog = true },
                onSubtitleAiQrClick = { viewModel.startAiKeyServer() },
                onAddIptvClick = { editingIptvIndex = -1; showIptvInput = true },
                onEditIptvClick = { idx -> editingIptvIndex = idx; showIptvInput = true },
                onAddCatalogClick = { showCatalogInput = true },
                onRenameCatalogClick = { catalog ->
                    renameCatalogId = catalog.id
                    renameCatalogTitle = catalog.title
                    showCatalogRename = true
                },
                onConnectHomeServerClick = {
                    val connection = uiState.homeServerConnection
                    homeServerUrl = connection?.serverUrl.orEmpty()
                    homeServerDisplayName = connection?.displayName.orEmpty()
                    homeServerUsername = connection?.userName.orEmpty()
                    homeServerPassword = ""
                    showHomeServerInput = true
                },
                onConnectPlexHomeServerClick = {
                    plexHomeServerDisplayName = ""
                    plexHomeServerUrl = ""
                    showPlexHomeServerInput = true
                },
                onAddCustomAddonClick = { showCustomAddonInput = true },
                openCustomUserAgentDialog = { showCustomUserAgentDialog = true },
                onNavigateToTelegram = onNavigateToTelegramSettings,
                onDisconnectCloud = { showCloudDisconnectConfirm = true },
                onDisconnectTrakt = { showTraktDisconnectConfirm = true }
            )
        } else {
            AppTopBar(
                selectedItem = SidebarItem.SETTINGS,
                isFocused = activeZone == Zone.SIDEBAR,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = AppTopBarContentTopInset)
                    .padding(start = 34.dp, end = 42.dp, bottom = 28.dp)
            ) {
                // Settings internal sidebar
                Column(
                    modifier = Modifier
                        .width(204.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF050505))
                        .border(1.dp, Color.White.copy(alpha = 0.07f), RoundedCornerShape(16.dp))
                        .padding(vertical = 18.dp, horizontal = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.settings),
                        style = ArflixTypography.cardTitle.copy(fontSize = 19.sp),
                        color = TextPrimary,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .padding(bottom = 14.dp)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(sectionScrollState)
                    ) {
                        sections.forEachIndexed { index, section ->
                            val group = tvSettingsSidebarGroup(section)
                            val previousGroup = sections.getOrNull(index - 1)?.let { tvSettingsSidebarGroup(it) }
                            if (index == 0 || group != previousGroup) {
                                SettingsSectionGroupLabel(group)
                                Spacer(modifier = Modifier.height(6.dp))
                            }
                            SettingsSectionItem(
                                icon = when (section) {
                                    "language" -> Icons.Default.Language
                                    "subtitles" -> Icons.Default.Subtitles
                                    "ai_subtitles" -> Icons.Default.AutoAwesome
                                    "playback" -> Icons.Default.PlayArrow
                                    "appearance" -> Icons.Default.Palette
                                    "profiles" -> Icons.Default.SwitchAccount
                                    "network" -> Icons.Default.Settings
                                    "iptv" -> Icons.Default.LiveTv
                                    "home_server" -> Icons.Default.Cloud
                                    "catalogs" -> Icons.Default.Widgets
                                    "stremio" -> Icons.Default.Extension
                                    "accounts" -> Icons.Default.Person
                                    else -> Icons.Default.Settings
                                },
                                title = when (section) {
                                    "language" -> stringResource(R.string.language_and_audio)
                                    "subtitles" -> stringResource(R.string.subtitles)
                                    "ai_subtitles" -> stringResource(R.string.ai_subtitles_section)
                                    "playback" -> stringResource(R.string.playback)
                                    "appearance" -> stringResource(R.string.interface_label)
                                    "profiles" -> stringResource(R.string.profiles)
                                    "network" -> stringResource(R.string.network)
                                    "iptv" -> stringResource(R.string.iptv)
                                    "home_server" -> stringResource(R.string.settings_home_server)
                                    "catalogs" -> stringResource(R.string.catalogs)
                                    "stremio" -> stringResource(R.string.addons)
                                    "accounts" -> stringResource(R.string.accounts)
                                    else -> section.replaceFirstChar { it.uppercase() }
                                },
                                isSelected = sectionIndex == index,
                                isFocused = activeZone == Zone.SECTION && sectionIndex == index,
                                onClick = {
                                    sectionIndex = index
                                    contentFocusIndex = 0
                                    iptvActionIndex = 0
                                    showIptvCategoriesSettings = false
                                    activeZone = Zone.SECTION
                                }
                            )

                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(R.string.settings_app_version_label, BuildConfig.VERSION_NAME),
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.5f),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                // Content area
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(start = 28.dp)
                ) {
                    TvSettingsSectionHeader(
                        section = sections[sectionIndex],
                        uiState = uiState,
                        addonCount = stremioAddons.size
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                  CompositionLocalProvider(LocalSettingsFocusTracker provides focusTracker) {
                    when (sections[sectionIndex]) {
                        in tvGeneralSectionIds -> {
                        TvGeneralSettingsRows(
                            section = sections[sectionIndex],
                            defaultSubtitle = uiState.defaultSubtitle,
                            secondarySubtitle = uiState.secondarySubtitle,
                            defaultAudioLanguage = uiState.defaultAudioLanguage,
                            dnsProvider = uiState.dnsProvider,
                            cardLayoutMode = uiState.cardLayoutMode,
                            frameRateMatchingMode = uiState.frameRateMatchingMode,
                            autoPlayNext = uiState.autoPlayNext,
                            autoPlaySingleSource = uiState.autoPlaySingleSource,
                            autoPlayMinQuality = uiState.autoPlayMinQuality,
                            contentLanguage = uiState.contentLanguage,
                            subtitleSize = uiState.subtitleSize,
                            subtitleColor = uiState.subtitleColor,
                            subtitleOffset = uiState.subtitleOffset,
                            subtitleStyle = uiState.subtitleStyle,
                            subtitleStylized = uiState.subtitleStylized,
                            deviceModeOverride = uiState.deviceModeOverride,
                            skipProfileSelection = uiState.skipProfileSelection,
                            oledBlackBackground = uiState.oledBlackBackground,
                            clockFormat = uiState.clockFormat,
                            showBudget = uiState.showBudget,
                            volumeBoostDb = uiState.volumeBoostDb,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            onSubtitleClick = openSubtitlePicker,
                            onSecondarySubtitleClick = openSecondarySubtitlePicker,
                            onAudioLanguageClick = openAudioLanguagePicker,
                            onCardLayoutToggle = { viewModel.toggleCardLayoutMode() },
                            onFrameRateMatchingClick = { viewModel.cycleFrameRateMatchingMode() },
                            onDnsProviderClick = openDnsProviderPicker,
                            onAutoPlayToggle = { viewModel.setAutoPlayNext(it) },
                            onAutoPlaySingleSourceToggle = { viewModel.setAutoPlaySingleSource(it) },
                            onAutoPlayMinQualityClick = { viewModel.cycleAutoPlayMinQuality() },
                            trailerAutoPlay = uiState.trailerAutoPlay,
                            onTrailerAutoPlayToggle = { viewModel.setTrailerAutoPlay(it) },
                            trailerSoundEnabled = uiState.trailerSoundEnabled,
                            onTrailerSoundEnabledToggle = { viewModel.setTrailerSoundEnabled(it) },
                            trailerInCards = uiState.trailerInCards,
                            onTrailerInCardsToggle = { viewModel.setTrailerInCards(it) },
                            trailerDelaySeconds = uiState.trailerDelaySeconds,
                            onTrailerDelayClick = { viewModel.cycleTrailerDelay() },
                            onDeviceModeClick = openUiModeWarningDialog,
                            onContentLanguageClick = openContentLanguagePicker,
                            onSkipProfileSelectionToggle = { viewModel.setSkipProfileSelection(it) },
                            onOledBlackBackgroundToggle = { viewModel.setOledBlackBackground(it) },
                            onClockFormatClick = { viewModel.cycleClockFormat() },
                            onShowBudgetToggle = { viewModel.setShowBudget(it) },
                            smoothScrolling = uiState.smoothScrolling,
                            onSmoothScrollingToggle = { viewModel.setSmoothScrolling(it) },
                            spoilerBlurEnabled = uiState.spoilerBlurEnabled,
                            onSpoilerBlurToggle = { viewModel.setSpoilerBlurEnabled(it) },
                            accentColor = uiState.accentColor,
                            onAccentColorClick = { viewModel.cycleAccentColor() },
                            showLoadingStats = uiState.showLoadingStats,
                            onShowLoadingStatsToggle = { viewModel.setShowLoadingStats(it) },
                            onVolumeBoostClick = { viewModel.cycleVolumeBoost() },
                            onSubtitleSizeClick = { viewModel.cycleSubtitleSize() },
                            onSubtitleColorClick = { viewModel.cycleSubtitleColor() },
                            onSubtitleOffsetClick = { viewModel.cycleSubtitleOffset() },
                            onSubtitleStyleClick = { viewModel.cycleSubtitleStyle() },
                            onSubtitleStylizedToggle = { viewModel.toggleSubtitleStylized() },
                            filterSubtitlesByLanguage = uiState.filterSubtitlesByLanguage,
                            onFilterSubtitlesByLanguageToggle = { viewModel.setFilterSubtitlesByLanguage(it) },
                            qualityFilterValue = uiState.qualityFilterPresetLabel,
                            onQualityFiltersClick = { showQualityFiltersModal = true },
                            subtitleAiEnabled = uiState.subtitleAiEnabled,
                            subtitleAiAutoSelect = uiState.subtitleAiAutoSelect,
                            subtitleAiApiKey = uiState.subtitleAiApiKey,
                            subtitleAiModel = uiState.subtitleAiModel,
                            subtitleRemoveHearingImpaired = uiState.subtitleRemoveHearingImpaired,
                            onSubtitleAiEnabledToggle = { viewModel.setSubtitleAiEnabled(it) },
                            onSubtitleAiModelClick = { showAiModelDialog = true },
                            onSubtitleAiAutoSelectToggle = { viewModel.setSubtitleAiAutoSelect(it) },
                            onSubtitleRemoveHearingImpairedToggle = { viewModel.setSubtitleRemoveHearingImpaired(it) },
                            onSubtitleAiApiKeyClick = { showAiApiKeyDialog = true },
                            onSubtitleAiQrClick = { viewModel.startAiKeyServer() },
                            customUserAgent = uiState.customUserAgent,
                            onCustomUserAgentClick = { showCustomUserAgentDialog = true }
                        )
                        if (showAiModelDialog) {
                            AiModelDialog(
                                currentModel = uiState.subtitleAiModel,
                                onModelSelected = { model ->
                                    viewModel.setSubtitleAiModel(model)
                                    showAiModelDialog = false
                                },
                                onDismiss = { showAiModelDialog = false }
                            )
                        }
                        if (showAiApiKeyDialog) {
                            AiApiKeyDialog(
                                currentKey = uiState.subtitleAiApiKey,
                                model = uiState.subtitleAiModel,
                                onSave = { key ->
                                    viewModel.saveSubtitleAiApiKey(key)
                                    showAiApiKeyDialog = false
                                },
                                onDismiss = { showAiApiKeyDialog = false }
                            )
                        }
                        if (showCustomUserAgentDialog) {
                            CustomUserAgentDialog(
                                currentValue = uiState.customUserAgent,
                                onSave = { value ->
                                    viewModel.setCustomUserAgent(value)
                                    showCustomUserAgentDialog = false
                                },
                                onDismiss = { showCustomUserAgentDialog = false }
                            )
                        }
                        if (uiState.aiKeyServerState.isActive) {
                            AiKeyQrOverlay(
                                qrBitmap = uiState.aiKeyServerState.qrBitmap,
                                serverUrl = uiState.aiKeyServerState.serverUrl,
                                keyReceived = uiState.aiKeyServerState.keyReceived,
                                onClose = { viewModel.stopAiKeyServer() }
                            )
                        }
                        } // end "general" block
                        "iptv" -> if (showIptvCategoriesSettings) {
                            IptvCategoriesSettings(
                                playlistId = uiState.iptvSelectedPlaylistId ?: "",
                                availableGroups = uiState.iptvAvailableGroups,
                                hiddenGroups = uiState.iptvHiddenGroups,
                                groupOrder = uiState.iptvGroupOrder,
                                focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                                focusedActionIndex = iptvActionIndex,
                                onToggleHidden = { viewModel.toggleIptvHiddenGroup(uiState.iptvSelectedPlaylistId ?: "", it) },
                                onMoveUp = { viewModel.moveIptvGroupUp(uiState.iptvSelectedPlaylistId ?: "", it) },
                                onMoveDown = { viewModel.moveIptvGroupDown(uiState.iptvSelectedPlaylistId ?: "", it) },
                                onReset = { viewModel.resetIptvGroupOrder(uiState.iptvSelectedPlaylistId ?: "") }
                            )
                        } else IptvSettings(
                            playlists = uiState.iptvPlaylists,
                            channelCount = uiState.iptvChannelCount,
                            isLoading = uiState.isIptvLoading,
                            error = uiState.iptvError,
                            statusMessage = uiState.iptvStatusMessage,
                            statusType = uiState.iptvStatusType,
                            progressText = uiState.iptvProgressText,
                            progressPercent = uiState.iptvProgressPercent,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            focusedActionIndex = iptvActionIndex,
                            onConfigure = { editingIptvIndex = -1; showIptvInput = true },
                            onEditPlaylist = { idx -> editingIptvIndex = idx; showIptvInput = true },
                            onTogglePlaylist = { idx ->
                                val updated = uiState.iptvPlaylists.toMutableList()
                                val item = updated.getOrNull(idx) ?: return@IptvSettings
                                updated[idx] = item.copy(enabled = !item.enabled)
                                viewModel.saveIptvPlaylists(updated)
                            },
                            onMovePlaylistUp = { idx ->
                                if (idx <= 0) return@IptvSettings
                                val updated = uiState.iptvPlaylists.toMutableList()
                                val item = updated.removeAt(idx)
                                updated.add(idx - 1, item)
                                viewModel.saveIptvPlaylists(updated)
                            },
                            onMovePlaylistDown = { idx ->
                                val updated = uiState.iptvPlaylists.toMutableList()
                                if (idx !in 0 until updated.lastIndex) return@IptvSettings
                                val item = updated.removeAt(idx)
                                updated.add(idx + 1, item)
                                viewModel.saveIptvPlaylists(updated)
                            },
                            onDeletePlaylist = { idx ->
                                val updated = uiState.iptvPlaylists.toMutableList()
                                if (idx in updated.indices) {
                                    updated.removeAt(idx)
                                    viewModel.saveIptvPlaylists(updated)
                                }
                            },
                            onRefresh = { viewModel.refreshIptv() },
                            onDelete = { viewModel.clearIptvConfig() },
                            onManageCategories = openIptvCategories
                        )
                        "TV" -> IptvSettings(
                            playlists = uiState.iptvPlaylists,
                            channelCount = uiState.iptvChannelCount,
                            isLoading = uiState.isIptvLoading,
                            error = uiState.iptvError,
                            statusMessage = uiState.iptvStatusMessage,
                            statusType = uiState.iptvStatusType,
                            progressText = uiState.iptvProgressText,
                            progressPercent = uiState.iptvProgressPercent,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            focusedActionIndex = iptvActionIndex,
                            onConfigure = { editingIptvIndex = -1; showIptvInput = true },
                            onEditPlaylist = { idx -> editingIptvIndex = idx; showIptvInput = true },
                            onTogglePlaylist = { idx ->
                                val updated = uiState.iptvPlaylists.toMutableList()
                                val item = updated.getOrNull(idx) ?: return@IptvSettings
                                updated[idx] = item.copy(enabled = !item.enabled)
                                viewModel.saveIptvPlaylists(updated)
                            },
                            onMovePlaylistUp = { idx ->
                                if (idx <= 0) return@IptvSettings
                                val updated = uiState.iptvPlaylists.toMutableList()
                                val item = updated.removeAt(idx)
                                updated.add(idx - 1, item)
                                viewModel.saveIptvPlaylists(updated)
                            },
                            onMovePlaylistDown = { idx ->
                                val updated = uiState.iptvPlaylists.toMutableList()
                                if (idx !in 0 until updated.lastIndex) return@IptvSettings
                                val item = updated.removeAt(idx)
                                updated.add(idx + 1, item)
                                viewModel.saveIptvPlaylists(updated)
                            },
                            onDeletePlaylist = { idx ->
                                val updated = uiState.iptvPlaylists.toMutableList()
                                if (idx in updated.indices) {
                                    updated.removeAt(idx)
                                    viewModel.saveIptvPlaylists(updated)
                                }
                            },
                            onRefresh = { viewModel.refreshIptv() },
                            onDelete = { viewModel.clearIptvConfig() },
                            onManageCategories = openIptvCategories
                        )
                        "home_server" -> HomeServerSettings(
                            connections = uiState.homeServerConnections,
                            isWorking = uiState.isHomeServerConnecting,
                            isPlexWorking = uiState.isPlexHomeServerPolling || uiState.plexHomeServerAuth != null,
                            error = uiState.homeServerError,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            onConnect = {
                                homeServerUrl = ""
                                homeServerDisplayName = ""
                                homeServerUsername = ""
                                homeServerPassword = ""
                                showHomeServerInput = true
                            },
                            onConnectPlex = {
                                plexHomeServerDisplayName = ""
                                plexHomeServerUrl = ""
                                showPlexHomeServerInput = true
                            },
                            onEditConnection = { connection ->
                                homeServerUrl = connection.serverUrl
                                homeServerDisplayName = connection.displayName
                                homeServerUsername = connection.userName
                                homeServerPassword = ""
                                showHomeServerInput = true
                            },
                            onTest = { viewModel.testHomeServerConnection() },
                            onDisconnect = { viewModel.disconnectHomeServer() }
                        )
                        "catalogs" -> CatalogsSettings(
                            catalogs = uiState.catalogs,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            focusedActionIndex = catalogActionIndex,
                            onAddCatalog = { showCatalogInput = true },
                            onRenameCatalog = { catalog ->
                                renameCatalogId = catalog.id
                                renameCatalogTitle = catalog.title
                                showCatalogRename = true
                            },
                            onMoveCatalogUp = { catalog -> viewModel.moveCatalogUp(catalog.id) },
                            onMoveCatalogDown = { catalog -> viewModel.moveCatalogDown(catalog.id) },
                            onDeleteCatalog = { catalog -> viewModel.removeCatalog(catalog.id) }
                        )
                        "stremio" -> StremioAddonsSettings(
                            addons = stremioAddons,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            focusedActionIndex = addonActionIndex,
                            onToggleAddon = { viewModel.toggleAddon(it) },
                            onMoveAddonUp = { viewModel.moveAddonUp(it) },
                            onMoveAddonDown = { viewModel.moveAddonDown(it) },
                            onDeleteAddon = { viewModel.removeAddon(it) },
                            onAddCustomAddon = { showCustomAddonInput = true }
                        )
                        "plugins" -> {
                            com.arflix.tv.ui.screens.plugin.PluginScreen(
                                onBackPressed = { activeZone = Zone.SECTION },
                                onNavigateToSection = { activeZone = Zone.SECTION }
                            )
                        }
                        "accounts" -> AccountsSettings(
                            isCloudAuthenticated = uiState.isLoggedIn,
                            cloudEmail = uiState.accountEmail,
                            cloudHint = null,
                            isTraktAuthenticated = uiState.isTraktAuthenticated,
                            traktCode = uiState.traktCode?.userCode,
                            traktUrl = uiState.traktCode?.verificationUrl,
                            isTraktAuthStarting = uiState.isTraktAuthStarting,
                            isTraktPolling = uiState.isTraktPolling,
                            isForceCloudSyncing = uiState.isForceCloudSyncing,
                            lastCloudSyncStatus = uiState.lastCloudSyncStatus,
                            isSelfUpdateSupported = uiState.isSelfUpdateSupported,
                            updateStatus = uiState.updateStatus,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            onConnectCloud = {
                                if (isTouchDevice) {
                                    viewModel.openCloudEmailPasswordDialog()
                                } else {
                                    viewModel.startCloudAuth()
                                }
                            },
                            onDisconnectCloud = { showCloudDisconnectConfirm = true },
                            onConnectTrakt = { viewModel.startTraktAuth() },
                            onCancelTrakt = { viewModel.cancelTraktAuth() },
                            onDisconnectTrakt = { showTraktDisconnectConfirm = true },
                            onForceCloudSync = { viewModel.forceCloudSyncNow() },
                            onSwitchProfile = onSwitchProfile,
                            onCheckUpdates = { viewModel.checkForAppUpdates(force = true, showNoUpdateFeedback = true) },
                            onInstallUpdate = { viewModel.installAppUpdateOrRequestPermission() },
                            onOpenDataDeletion = { openExternalUrl(context, ACCOUNT_DELETION_URL) },
                            onNavigateToTelegram = onNavigateToTelegramSettings
                        )
                    }
                  }
                }
            }
        }

        // Custom Addon Input Modal
        if (showCustomAddonInput) {
            InputModal(
                title = stringResource(R.string.add_addon),
                fields = listOf(
                    InputField(label = stringResource(R.string.settings_label_url), value = customAddonUrl, onValueChange = { customAddonUrl = it })
                ),
                onConfirm = {
                    if (customAddonUrl.isNotBlank()) {
                        viewModel.addCustomAddon(customAddonUrl.trim())
                        customAddonUrl = ""
                        showCustomAddonInput = false
                    }
                },
                onDismiss = {
                    customAddonUrl = ""
                    showCustomAddonInput = false
                }
            )
        }

        if (showHomeServerInput) {
            InputModal(
                title = stringResource(R.string.settings_home_server),
                supportingText = stringResource(R.string.settings_home_server_https_note),
                fields = listOf(
                    InputField(
                        label = stringResource(R.string.settings_label_server_name),
                        value = homeServerDisplayName,
                        placeholder = stringResource(R.string.settings_ph_server_name),
                        onValueChange = { homeServerDisplayName = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_server_url),
                        value = homeServerUrl,
                        placeholder = stringResource(R.string.settings_ph_server_url),
                        onValueChange = { homeServerUrl = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_username),
                        value = homeServerUsername,
                        placeholder = stringResource(R.string.settings_ph_username_token),
                        onValueChange = { homeServerUsername = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_password_token),
                        value = homeServerPassword,
                        isSecret = true,
                        onValueChange = { homeServerPassword = it }
                    )
                ),
                onConfirm = {
                    if (homeServerUrl.isNotBlank() && homeServerPassword.isNotBlank()) {
                        viewModel.connectHomeServer(
                            serverUrl = homeServerUrl.trim(),
                            username = homeServerUsername.trim(),
                            password = homeServerPassword,
                            displayName = homeServerDisplayName.trim()
                        )
                        homeServerDisplayName = ""
                        homeServerPassword = ""
                        showHomeServerInput = false
                    }
                },
                onDismiss = {
                    homeServerDisplayName = ""
                    homeServerPassword = ""
                    showHomeServerInput = false
                }
            )
        }
        if (showPlexHomeServerInput) {
            InputModal(
                title = stringResource(R.string.settings_connect_with_code),
                supportingText = stringResource(R.string.settings_home_server_https_note),
                fields = listOf(
                    InputField(
                        label = stringResource(R.string.settings_label_server_name),
                        value = plexHomeServerDisplayName,
                        placeholder = stringResource(R.string.settings_ph_server_name),
                        onValueChange = { plexHomeServerDisplayName = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_server_url_optional),
                        value = plexHomeServerUrl,
                        placeholder = stringResource(R.string.settings_ph_server_url_discover),
                        onValueChange = { plexHomeServerUrl = it }
                    )
                ),
                onConfirm = {
                    viewModel.startPlexHomeServerAuth(
                        serverUrl = plexHomeServerUrl.trim(),
                        displayName = plexHomeServerDisplayName.trim()
                    )
                    plexHomeServerDisplayName = ""
                    plexHomeServerUrl = ""
                    showPlexHomeServerInput = false
                },
                onDismiss = {
                    plexHomeServerDisplayName = ""
                    plexHomeServerUrl = ""
                    showPlexHomeServerInput = false
                }
            )
        }
        if (showIptvInput) {
            InputModal(
                title = if (editingIptvIndex >= 0) stringResource(R.string.settings_edit_tv_playlist) else stringResource(R.string.settings_add_tv_playlist),
                supportingText = stringResource(R.string.settings_iptv_epg_note),
                fields = listOf(
                    InputField(
                        label = stringResource(R.string.settings_label_playlist_name),
                        value = iptvEditName,
                        onValueChange = { iptvEditName = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_m3u_or_xtream),
                        value = iptvEditUrl,
                        placeholder = stringResource(R.string.settings_ph_provider_host),
                        onValueChange = { iptvEditUrl = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_xtream_user),
                        value = iptvEditXtreamUser,
                        placeholder = stringResource(R.string.settings_ph_plain_m3u),
                        onValueChange = { iptvEditXtreamUser = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_xtream_pass),
                        value = iptvEditXtreamPass,
                        placeholder = stringResource(R.string.settings_ph_plain_m3u),
                        isSecret = true,
                        onValueChange = { iptvEditXtreamPass = it }
                    ),
                    InputField(
                        label = stringResource(R.string.settings_label_epg_sources),
                        value = iptvEditEpg,
                        placeholder = "https://provider.com/xmltv.xml\nhttps://backup.com/epg.xml.gz",
                        helper = stringResource(R.string.settings_helper_epg_one_per_line),
                        singleLine = false,
                        onValueChange = { iptvEditEpg = it }
                    )
                ),
                onConfirm = {
                    // Build the m3uUrl: if Xtream credentials are provided, combine as "host user pass"
                    val hasXtream = iptvEditXtreamUser.isNotBlank() && iptvEditXtreamPass.isNotBlank()
                    val finalM3uUrl = if (hasXtream) {
                        "${iptvEditUrl.trim()} ${iptvEditXtreamUser.trim()} ${iptvEditXtreamPass.trim()}"
                    } else {
                        iptvEditUrl
                    }
                    // Auto-derive EPG for Xtream if not provided
                    val finalEpgUrl = if (hasXtream && iptvEditEpg.isBlank()) {
                        "${iptvEditUrl.trim()} ${iptvEditXtreamUser.trim()} ${iptvEditXtreamPass.trim()}"
                    } else {
                        iptvEditEpg
                    }
                    val finalEpgUrls = splitSettingsEpgInput(finalEpgUrl)
                    val updated = uiState.iptvPlaylists.toMutableList()
                    val entry = com.arflix.tv.data.repository.IptvPlaylistEntry(
                        id = updated.getOrNull(editingIptvIndex)?.id ?: "list_${editingIptvIndex + 2}".takeIf { editingIptvIndex >= 0 } ?: "list_${updated.size + 1}",
                        name = iptvEditName,
                        m3uUrl = finalM3uUrl,
                        epgUrl = finalEpgUrls.firstOrNull().orEmpty(),
                        enabled = iptvEditEnabled,
                        epgUrls = finalEpgUrls
                    )
                    if (editingIptvIndex in updated.indices) updated[editingIptvIndex] = entry else updated.add(entry)
                    viewModel.saveIptvPlaylists(updated)
                    showIptvInput = false
                },
                onDismiss = {
                    showIptvInput = false
                }
            )
        }



        if (showCatalogInput) {
            CatalogDiscoveryModal(
                query = uiState.catalogSearchQuery,
                results = uiState.catalogSearchResults,
                isSearching = uiState.isCatalogSearching,
                error = uiState.catalogSearchError,
                manualUrl = catalogInputUrl,
                addedCatalogUrls = uiState.catalogs.mapNotNull { it.sourceUrl }.toSet(),
                onQueryChange = viewModel::setCatalogSearchQuery,
                onSearch = { viewModel.searchCatalogLists() },
                onAddResult = viewModel::addDiscoveredCatalog,
                onManualUrlChange = { catalogInputUrl = it },
                onManualAdd = {
                    if (catalogInputUrl.isNotBlank()) {
                        viewModel.addCatalog(catalogInputUrl)
                        catalogInputUrl = ""
                        showCatalogInput = false
                    }
                },
                onDismiss = {
                    catalogInputUrl = ""
                    viewModel.clearCatalogDiscovery()
                    showCatalogInput = false
                }
            )
        }

        if (showCatalogRename) {
            InputModal(
                title = stringResource(R.string.rename_catalog),
                fields = listOf(
                    InputField(label = stringResource(R.string.settings_label_title), value = renameCatalogTitle, onValueChange = { renameCatalogTitle = it })
                ),
                onConfirm = {
                    if (renameCatalogTitle.isNotBlank()) {
                        viewModel.renameCatalog(renameCatalogId, renameCatalogTitle)
                        showCatalogRename = false
                    }
                },
                onDismiss = {
                    showCatalogRename = false
                }
            )
        }

        if (showQualityFiltersModal) {
            QualityFiltersModal(
                filters = uiState.qualityFilters,
                onDismiss = { showQualityFiltersModal = false },
                onAdd = {
                    editingQualityFilterId = null
                    qualityFilterDeviceName = ""
                    qualityFilterRegexPattern = ""
                    showQualityFilterEditor = true
                },
                onEdit = { filter ->
                    editingQualityFilterId = filter.id
                    qualityFilterDeviceName = filter.deviceName
                    qualityFilterRegexPattern = filter.regexPattern
                    showQualityFilterEditor = true
                },
                onToggle = { viewModel.toggleQualityFilter(it) },
                onDelete = { viewModel.deleteQualityFilter(it) }
            )
        }

        if (showQualityFilterEditor) {
            QualityFilterEditorModal(
                title = if (editingQualityFilterId == null) stringResource(R.string.settings_add_quality_filter) else stringResource(R.string.settings_edit_quality_filter),
                deviceName = qualityFilterDeviceName,
                regexPattern = qualityFilterRegexPattern,
                onDeviceNameChange = { qualityFilterDeviceName = it },
                onRegexPatternChange = { qualityFilterRegexPattern = it },
                onDismiss = { showQualityFilterEditor = false },
                onSave = {
                    val id = editingQualityFilterId
                    if (id == null) {
                        viewModel.addQualityFilter(qualityFilterDeviceName, qualityFilterRegexPattern)
                    } else {
                        viewModel.updateQualityFilter(id, qualityFilterDeviceName, qualityFilterRegexPattern)
                    }
                    showQualityFilterEditor = false
                }
            )
        }

        if (showSubtitlePicker) {
            SubtitlePickerModal(
                title = stringResource(R.string.default_subtitle),
                options = uiState.subtitleOptions,
                selected = uiState.defaultSubtitle,
                focusedIndex = subtitlePickerIndex,
                onFocusChange = { subtitlePickerIndex = it },
                onSelect = {
                    viewModel.setDefaultSubtitle(it)
                    showSubtitlePicker = false
                },
                onDismiss = { showSubtitlePicker = false }
            )
        }

        if (showSecondarySubtitlePicker) {
            SubtitlePickerModal(
                title = stringResource(R.string.secondary_subtitle),
                options = uiState.subtitleOptions,
                selected = uiState.secondarySubtitle,
                focusedIndex = secondarySubtitlePickerIndex,
                onFocusChange = { secondarySubtitlePickerIndex = it },
                onSelect = {
                    viewModel.setSecondarySubtitle(it)
                    showSecondarySubtitlePicker = false
                },
                onDismiss = { showSecondarySubtitlePicker = false }
            )
        }

        if (showAudioLanguagePicker) {
            SubtitlePickerModal(
                title = stringResource(R.string.default_audio),
                options = uiState.audioLanguageOptions,
                selected = uiState.defaultAudioLanguage,
                focusedIndex = audioLanguagePickerIndex,
                onFocusChange = { audioLanguagePickerIndex = it },
                onSelect = {
                    viewModel.setDefaultAudioLanguage(it)
                    showAudioLanguagePicker = false
                },
                onDismiss = { showAudioLanguagePicker = false }
            )
        }

        if (showDnsProviderPicker) {
            SubtitlePickerModal(
                title = stringResource(R.string.dns_provider),
                options = uiState.dnsProviderOptions,
                selected = uiState.dnsProvider,
                focusedIndex = dnsProviderPickerIndex,
                onFocusChange = { dnsProviderPickerIndex = it },
                onSelect = {
                    showDnsProviderPicker = false
                    viewModel.setDnsProvider(it)
                },
                onDismiss = { showDnsProviderPicker = false }
            )
        }

        if (showContentLanguagePicker) {
            SubtitlePickerModal(
                title = stringResource(R.string.app_language),
                options = TMDB_LANGUAGES.map { it.second },
                selected = TMDB_LANGUAGES.firstOrNull { it.first == uiState.contentLanguage }?.second ?: "English",
                focusedIndex = contentLanguagePickerIndex,
                onFocusChange = { contentLanguagePickerIndex = it },
                onSelect = { displayName ->
                    val code = TMDB_LANGUAGES.firstOrNull { it.second == displayName }?.first ?: "en-US"
                    viewModel.setContentLanguage(code)
                    showContentLanguagePicker = false
                },
                onDismiss = { showContentLanguagePicker = false }
            )
        }

        if (isTouchDevice && showAiModelDialog) {
            AiModelDialog(
                currentModel = uiState.subtitleAiModel,
                onModelSelected = { model ->
                    viewModel.setSubtitleAiModel(model)
                    showAiModelDialog = false
                },
                onDismiss = { showAiModelDialog = false }
            )
        }

        if (isTouchDevice && showAiApiKeyDialog) {
            AiApiKeyDialog(
                currentKey = uiState.subtitleAiApiKey,
                model = uiState.subtitleAiModel,
                onSave = { key ->
                    viewModel.saveSubtitleAiApiKey(key)
                    showAiApiKeyDialog = false
                },
                onDismiss = { showAiApiKeyDialog = false }
            )
        }

        if (isTouchDevice && showCustomUserAgentDialog) {
            CustomUserAgentDialog(
                currentValue = uiState.customUserAgent,
                onSave = { value ->
                    viewModel.setCustomUserAgent(value)
                    showCustomUserAgentDialog = false
                },
                onDismiss = { showCustomUserAgentDialog = false }
            )
        }

        if (isTouchDevice && uiState.aiKeyServerState.isActive) {
            AiKeyQrOverlay(
                qrBitmap = uiState.aiKeyServerState.qrBitmap,
                serverUrl = uiState.aiKeyServerState.serverUrl,
                keyReceived = uiState.aiKeyServerState.keyReceived,
                onClose = { viewModel.stopAiKeyServer() }
            )
        }

        if (showCloudDisconnectConfirm) {
            AccountDisconnectConfirmDialog(
                title = stringResource(R.string.settings_cloud_disconnect_confirm_title),
                description = stringResource(R.string.settings_cloud_disconnect_confirm_desc),
                onConfirm = {
                    showCloudDisconnectConfirm = false
                    viewModel.logout()
                },
                onDismiss = { showCloudDisconnectConfirm = false }
            )
        }

        if (showTraktDisconnectConfirm) {
            AccountDisconnectConfirmDialog(
                title = stringResource(R.string.settings_trakt_disconnect_confirm_title),
                description = stringResource(R.string.settings_trakt_disconnect_confirm_desc),
                onConfirm = {
                    showTraktDisconnectConfirm = false
                    viewModel.disconnectTrakt()
                },
                onDismiss = { showTraktDisconnectConfirm = false }
            )
        }

        if (uiState.showCloudEmailPasswordDialog) {
            CloudEmailPasswordModal(
                email = cloudDialogEmail,
                password = cloudDialogPassword,
                onEmailChange = { cloudDialogEmail = it },
                onPasswordChange = { cloudDialogPassword = it },
                onDismiss = { viewModel.closeCloudEmailPasswordDialog() },
                onSignIn = { viewModel.completeCloudAuthWithEmailPassword(cloudDialogEmail, cloudDialogPassword, createAccount = false) },
                onCreateAccount = { viewModel.completeCloudAuthWithEmailPassword(cloudDialogEmail, cloudDialogPassword, createAccount = true) }
            )
        }

        if (uiState.showCloudPairDialog) {
            CloudPairModal(
                verificationUrl = uiState.cloudVerificationUrl.orEmpty(),
                userCode = uiState.cloudUserCode.orEmpty(),
                isWorking = uiState.isCloudAuthWorking,
                onDismiss = { viewModel.cancelCloudAuth() },
                onUseEmailPassword = { viewModel.openCloudEmailPasswordDialog() }
            )
        }

        uiState.traktCode?.let { traktCode ->
            TraktActivationModal(
                verificationUrl = traktCode.verificationUrl,
                userCode = traktCode.userCode,
                onDismiss = { viewModel.cancelTraktAuth() }
            )
        }

        uiState.plexHomeServerAuth?.let { plexAuth ->
            TraktActivationModal(
                title = stringResource(R.string.settings_connect_with_code),
                instruction = if (LocalDeviceType.current.isTouchDevice()) {
                    stringResource(R.string.settings_plex_auth_touch_instruction)
                } else {
                    stringResource(R.string.settings_plex_auth_tv_instruction)
                },
                verificationUrl = plexAuth.verificationUrl,
                userCode = plexAuth.code,
                onOpenUrl = { openExternalUrl(context, plexAuth.verificationUrl) },
                onDismiss = { viewModel.cancelPlexHomeServerAuth() }
            )
        }

        if (uiState.showAppUpdateDialog) {
            com.arflix.tv.ui.components.AppUpdateModal(
                status = uiState.updateStatus,
                onDownload = { viewModel.downloadAppUpdate() },
                onCancelDownload = { viewModel.cancelDownloadAppUpdate() },
                onInstall = { viewModel.installAppUpdateOrRequestPermission() },
                onDismiss = { viewModel.dismissAppUpdateDialog() },
                onIgnore = { viewModel.ignoreAppUpdate() }
            )
        }

        if (uiState.showUnknownSourcesDialog) {
            UnknownSourcesModal(
                onDismiss = { viewModel.dismissAppUpdateDialog() },
                onOpenSettings = { viewModel.openUnknownSourcesSettings() }
            )
        }

        if (showUiModeWarningDialog) {
            UiModeWarningDialog(
                nextMode = nextUiMode,
                onConfirm = {
                    viewModel.setDeviceModeOverride(nextUiMode)
                    showUiModeWarningDialog = false
                },
                onDismiss = {
                    showUiModeWarningDialog = false
                }
            )
        }

        // Toast notification
        uiState.toastMessage?.let { message ->
            Toast(
                message = message,
                type = when (uiState.toastType) {
                    ToastType.SUCCESS -> ComponentToastType.SUCCESS
                    ToastType.ERROR -> ComponentToastType.ERROR
                    ToastType.INFO -> ComponentToastType.INFO
                },
                isVisible = true,
                onDismiss = { viewModel.dismissToast() }
            )
        }

    }
}

@Composable
private fun ModalScrim(
    onDismiss: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val scrimInteraction = remember { MutableInteractionSource() }
    val contentInteraction = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.72f))
            .clickable(
                interactionSource = scrimInteraction,
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.clickable(
                interactionSource = contentInteraction,
                indication = null,
                onClick = {}
            ),
            content = content
        )
    }
}

@Composable
private fun QualityFiltersModal(
    filters: List<QualityFilterConfig>,
    onDismiss: () -> Unit,
    onAdd: () -> Unit,
    onEdit: (QualityFilterConfig) -> Unit,
    onToggle: (String) -> Unit,
    onDelete: (String) -> Unit,
    focusedFilterIndex: Int = -1,
    onFocusedFilterIndexChange: (Int) -> Unit = {},
    focusedActionIndex: Int = -1,
    onFocusedActionIndexChange: (Int) -> Unit = {}
) {
    val modalFocusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState()
    val hasFilters = filters.isNotEmpty()
    var isFooterFocused by remember(filters) { mutableStateOf(!hasFilters) }
    var selectedFooterAction by remember { mutableIntStateOf(1) } // 0 = Close, 1 = Add
    var selectedFilterIndex by remember(filters, focusedFilterIndex) {
        mutableIntStateOf(
            if (hasFilters) focusedFilterIndex.coerceIn(0, filters.lastIndex).takeIf { it >= 0 } ?: 0 else -1
        )
    }
    var selectedFilterAction by remember(filters, focusedActionIndex) {
        mutableIntStateOf(
            if (hasFilters) focusedActionIndex.coerceIn(0, 2).takeIf { it >= 0 } ?: 0 else 0
        )
    }

    LaunchedEffect(hasFilters) {
        modalFocusRequester.requestFocus()
        if (!hasFilters) {
            isFooterFocused = true
            selectedFilterIndex = -1
        } else if (selectedFilterIndex !in filters.indices) {
            selectedFilterIndex = 0
        }
    }

    LaunchedEffect(selectedFilterIndex, isFooterFocused, hasFilters) {
        if (hasFilters && !isFooterFocused && selectedFilterIndex in filters.indices) {
            listState.animateScrollToItem(selectedFilterIndex)
        }
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .width(760.dp)
                    .heightIn(max = 760.dp)
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(24.dp)
                    .focusRequester(modalFocusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            Key.DirectionUp -> {
                                if (isFooterFocused) {
                                    if (hasFilters) isFooterFocused = false
                                } else if (selectedFilterIndex > 0) {
                                    selectedFilterIndex--
                                }
                                true
                            }
                            Key.DirectionDown -> {
                                if (!isFooterFocused) {
                                    if (selectedFilterIndex < filters.lastIndex) {
                                        selectedFilterIndex++
                                    } else {
                                        isFooterFocused = true
                                    }
                                }
                                true
                            }
                            Key.DirectionLeft -> {
                                if (isFooterFocused) {
                                    if (selectedFooterAction > 0) selectedFooterAction--
                                } else if (selectedFilterAction > 0) {
                                    selectedFilterAction--
                                }
                                true
                            }
                            Key.DirectionRight -> {
                                if (isFooterFocused) {
                                    if (selectedFooterAction < 1) selectedFooterAction++
                                } else if (selectedFilterAction < 2) {
                                    selectedFilterAction++
                                }
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                if (isFooterFocused || !hasFilters) {
                                    if (selectedFooterAction == 0) onDismiss() else onAdd()
                                } else {
                                    val selectedFilter = filters.getOrNull(selectedFilterIndex)
                                    if (selectedFilter != null) {
                                        when (selectedFilterAction) {
                                            0 -> onToggle(selectedFilter.id)
                                            1 -> onEdit(selectedFilter)
                                            2 -> onDelete(selectedFilter.id)
                                        }
                                    }
                                }
                                true
                            }
                            else -> false
                        }
                    }
            ) {
                Text(
                    text = stringResource(R.string.quality_filters),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.settings_quality_filters_subtitle),
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (filters.isEmpty()) {
                    Text(
                        text = stringResource(R.string.settings_no_filters_yet),
                        style = ArflixTypography.body,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 460.dp)
                    ) {
                        itemsIndexed(filters) { index, filter ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White.copy(alpha = 0.04f), RoundedCornerShape(10.dp))
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = filter.deviceName.ifBlank { stringResource(R.string.settings_unnamed_device) },
                                        style = ArflixTypography.body,
                                        color = TextPrimary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = filter.regexPattern,
                                        style = ArflixTypography.caption,
                                        color = TextSecondary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                CatalogActionChip(
                                    icon = if (filter.enabled) Icons.Default.Check else Icons.Default.VisibilityOff,
                                    isFocused = !isFooterFocused && selectedFilterIndex == index && selectedFilterAction == 0,
                                    onClick = { onToggle(filter.id) }
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                CatalogActionChip(
                                    icon = Icons.Default.Edit,
                                    isFocused = !isFooterFocused && selectedFilterIndex == index && selectedFilterAction == 1,
                                    onClick = { onEdit(filter) }
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                CatalogActionChip(
                                    icon = Icons.Default.Delete,
                                    isFocused = !isFooterFocused && selectedFilterIndex == index && selectedFilterAction == 2,
                                    isDestructive = true,
                                    onClick = { onDelete(filter.id) }
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SettingsChip(
                        label = stringResource(R.string.close),
                        enabled = true,
                        onClick = onDismiss,
                        isFocused = isFooterFocused && selectedFooterAction == 0,
                        modifier = Modifier.weight(1f)
                    )
                    SettingsChip(
                        label = stringResource(R.string.settings_add_filter),
                        enabled = true,
                        onClick = onAdd,
                        isFocused = isFooterFocused && selectedFooterAction == 1,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsChip(
    label: String,
    enabled: Boolean,
    onClick: () -> Unit,
    isFocused: Boolean = false,
    modifier: Modifier = Modifier
) {
    val chipFocusColor = resolveAccentColor(fallback = Color.White)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                when {
                    !enabled -> Color.White.copy(alpha = 0.06f)
                    isFocused -> Color.White
                    else -> Color.White.copy(alpha = 0.12f)
                }
            )
            .border(
                width = if (isFocused) 1.dp else 0.dp,
                color = if (isFocused) chipFocusColor else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = ArflixTypography.button,
            color = when {
                !enabled -> TextSecondary
                isFocused -> Color.Black
                else -> TextPrimary
            }
        )
    }
}

@Composable
private fun QualityFilterEditorModal(
    title: String,
    deviceName: String,
    regexPattern: String,
    onDeviceNameChange: (String) -> Unit,
    onRegexPatternChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    // Focus order: 0 device name, 1 regex, 2 cancel, 3 save
    var focusedIndex by remember { mutableIntStateOf(0) }
    val totalItems = 4
    val modalFocusRequester = remember { FocusRequester() }
    val deviceNameRequester = remember { FocusRequester() }
    val regexPatternRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) { modalFocusRequester.requestFocus() }
    LaunchedEffect(focusedIndex) {
        when (focusedIndex) {
            0 -> deviceNameRequester.requestFocus()
            1 -> regexPatternRequester.requestFocus()
            else -> modalFocusRequester.requestFocus()
        }
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(if (LocalDeviceType.current.isTouchDevice()) 0.92f else 1f)
                    .widthIn(max = 760.dp)
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 24.dp)
                    .focusRequester(modalFocusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                Key.DirectionUp -> {
                                    if (focusedIndex > 0) focusedIndex--
                                    true
                                }
                                Key.DirectionDown -> {
                                    if (focusedIndex < totalItems - 1) focusedIndex++
                                    true
                                }
                                Key.DirectionLeft -> {
                                    if (focusedIndex == 3) focusedIndex = 2
                                    true
                                }
                                Key.DirectionRight -> {
                                    if (focusedIndex == 2) focusedIndex = 3
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    when (focusedIndex) {
                                        0 -> deviceNameRequester.requestFocus()
                                        1 -> regexPatternRequester.requestFocus()
                                        2 -> onDismiss()
                                        3 -> if (regexPattern.trim().isNotBlank()) onSave()
                                    }
                                    true
                                }
                                else -> false
                            }
                        } else false
                    }
            ) {
                Text(
                    text = title,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                androidx.compose.material3.TextField(
                    value = deviceName,
                    onValueChange = onDeviceNameChange,
                    singleLine = true,
                    label = { Text(stringResource(R.string.settings_label_device_preset_name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(deviceNameRequester)
                        .onFocusChanged {
                            if (it.hasFocus) focusedIndex = 0
                        }
                )
                Spacer(modifier = Modifier.height(12.dp))
                androidx.compose.material3.TextField(
                    value = regexPattern,
                    onValueChange = onRegexPatternChange,
                    singleLine = false,
                    minLines = 3,
                    label = { Text(stringResource(R.string.settings_label_regex_pattern)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(regexPatternRequester)
                        .onFocusChanged {
                            if (it.hasFocus) focusedIndex = 1
                        }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SettingsChip(
                        label = stringResource(R.string.cancel),
                        enabled = true,
                        onClick = onDismiss,
                        isFocused = focusedIndex == 2,
                        modifier = Modifier.weight(1f)
                    )
                    SettingsChip(
                        label = stringResource(R.string.save),
                        enabled = regexPattern.trim().isNotBlank(),
                        onClick = onSave,
                        isFocused = focusedIndex == 3,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CloudEmailPasswordModal(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSignIn: () -> Unit,
    onCreateAccount: () -> Unit
) {
    // Focus order: 0 email, 1 password, 2 cancel, 3 sign in, 4 create
    var focusedIndex by remember { mutableIntStateOf(0) }
    val emailRequester = remember { FocusRequester() }
    val passwordRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) { emailRequester.requestFocus() }
    LaunchedEffect(focusedIndex) {
        when (focusedIndex) {
            0 -> emailRequester.requestFocus()
            1 -> passwordRequester.requestFocus()
        }
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(if (LocalDeviceType.current.isTouchDevice()) 0.92f else 1f)
                    .widthIn(max = 600.dp)
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 32.dp)
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                Key.DirectionUp -> {
                                    focusedIndex = when (focusedIndex) {
                                        1 -> 0
                                        2, 3, 4 -> 1
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.DirectionDown -> {
                                    focusedIndex = when (focusedIndex) {
                                        0 -> 1
                                        1 -> 2
                                        2 -> 3
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.DirectionLeft -> {
                                    focusedIndex = when (focusedIndex) {
                                        4 -> 3
                                        3 -> 2
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.DirectionRight -> {
                                    focusedIndex = when (focusedIndex) {
                                        2 -> 3
                                        3 -> 4
                                        else -> focusedIndex
                                    }
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    when (focusedIndex) {
                                        2 -> { onDismiss(); true }
                                        3 -> { onSignIn(); true }
                                        4 -> { onCreateAccount(); true }
                                        else -> false
                                    }
                                }
                                else -> false
                            }
                        } else false
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.settings_cloud_signin_title),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.settings_label_email),
                        style = ArflixTypography.caption,
                        color = if (focusedIndex == 0) Pink else TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    androidx.compose.material3.TextField(
                        value = email,
                        onValueChange = onEmailChange,
                        singleLine = true,
                        textStyle = ArflixTypography.body.copy(color = TextPrimary),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedContainerColor = Color.White.copy(alpha = 0.1f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                            focusedIndicatorColor = Pink,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Pink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(emailRequester)
                            .border(
                                width = if (focusedIndex == 0) 2.dp else 1.dp,
                                color = if (focusedIndex == 0) Pink else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.settings_label_password),
                        style = ArflixTypography.caption,
                        color = if (focusedIndex == 1) Pink else TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    androidx.compose.material3.TextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        singleLine = true,
                        visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                        textStyle = ArflixTypography.body.copy(color = TextPrimary),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedContainerColor = Color.White.copy(alpha = 0.1f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                            focusedIndicatorColor = Pink,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Pink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(passwordRequester)
                            .border(
                                width = if (focusedIndex == 1) 2.dp else 1.dp,
                                color = if (focusedIndex == 1) Pink else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val isCancelFocused = focusedIndex == 2
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isCancelFocused) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f)
                            )
                            .clickable { onDismiss() }
                            .border(
                                width = if (isCancelFocused) 2.dp else 0.dp,
                                color = if (isCancelFocused) Pink else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = ArflixTypography.button,
                            color = if (isCancelFocused) TextPrimary else TextSecondary
                        )
                    }

                    val isSignInFocused = focusedIndex == 3
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isSignInFocused) SuccessGreen else Pink.copy(alpha = 0.6f)
                            )
                            .clickable { onSignIn() }
                            .border(
                                width = if (isSignInFocused) 2.dp else 0.dp,
                                color = if (isSignInFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.sign_in),
                            style = ArflixTypography.button,
                            color = if (isSignInFocused) Color.White else Color.Black
                        )
                    }

                    val isCreateFocused = focusedIndex == 4
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isCreateFocused) SuccessGreen else Color.White.copy(alpha = 0.08f)
                            )
                            .clickable { onCreateAccount() }
                            .border(
                                width = if (isCreateFocused) 2.dp else 0.dp,
                                color = if (isCreateFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.settings_btn_create),
                            style = ArflixTypography.button,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = if (LocalDeviceType.current.isTouchDevice()) stringResource(R.string.settings_cloud_signin_hint_touch) else stringResource(R.string.settings_cloud_signin_hint_tv),
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CloudPairModal(
    verificationUrl: String,
    userCode: String,
    isWorking: Boolean,
    onDismiss: () -> Unit,
    onUseEmailPassword: () -> Unit,
) {
    val effectiveVerificationUrl = remember(verificationUrl, userCode) {
        verificationUrl.ifBlank {
            userCode.takeIf { it.isNotBlank() }?.let { code ->
                "https://auth.arvio.tv/?code=$code"
            }.orEmpty()
        }
    }
    // Focus order: 0 cancel, 1 email/password
    var focusedIndex by remember { mutableIntStateOf(1) }
    val modalFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        modalFocusRequester.requestFocus()
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        val isMobile = LocalDeviceType.current.isTouchDevice()
        ModalScrim(onDismiss = onDismiss) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val modalWidth = if (isMobile) maxWidth else (maxWidth * 0.62f).coerceIn(520.dp, 760.dp)
            val qrContainerSize = (modalWidth * 0.42f).coerceIn(190.dp, 260.dp)
            val qrBitmapSizePx = ((qrContainerSize.value * 3.2f).toInt()).coerceIn(512, 900)

            Column(
                modifier = Modifier
                    .then(
                        if (isMobile) Modifier.fillMaxWidth(0.92f).widthIn(max = 600.dp)
                        else Modifier.widthIn(max = modalWidth).fillMaxWidth(0.62f)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(horizontal = if (isMobile) 20.dp else 24.dp, vertical = if (isMobile) 24.dp else 20.dp)
                    .focusRequester(modalFocusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                Key.DirectionLeft -> {
                                    focusedIndex = 0
                                    true
                                }
                                Key.DirectionRight -> {
                                    focusedIndex = 1
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    when (focusedIndex) {
                                        0 -> { onDismiss(); true }
                                        1 -> { onUseEmailPassword(); true }
                                        else -> false
                                    }
                                }
                                else -> false
                            }
                        } else false
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.settings_cloud_pairing_title),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                if (isMobile) {
                    // On mobile, skip QR (can't scan own screen) and prompt email/password
                    Text(
                        text = stringResource(R.string.settings_pair_signin_link_device),
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.settings_pair_scan_qr_link_tv),
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                // QR code section - only shown on TV (phones can't scan their own screen)
                if (!isMobile && effectiveVerificationUrl.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .size(qrContainerSize)
                            .background(appBackgroundDark().copy(alpha = 0.92f), RoundedCornerShape(16.dp))
                            .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            QrCodeImage(
                                data = effectiveVerificationUrl,
                                sizePx = qrBitmapSizePx,
                                modifier = Modifier.fillMaxSize(),
                                foreground = android.graphics.Color.BLACK,
                                background = android.graphics.Color.WHITE,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (userCode.isNotBlank()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentPaste,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.settings_pair_code, userCode),
                            style = ArflixTypography.body,
                            color = TextPrimary
                        )
                    }
                }

                if (isWorking) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        LoadingIndicator(size = 20.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.settings_waiting_for_approval),
                            style = ArflixTypography.body,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isMobile) {
                    // On mobile, show "Use Email/Password" prominently as the primary action
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    color = SuccessGreen,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { onUseEmailPassword() }
                                .padding(vertical = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Link,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.settings_use_email_password),
                                    style = ArflixTypography.button,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    color = Color.White.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { onDismiss() }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
                                style = ArflixTypography.button,
                                color = TextSecondary
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val isCancelFocused = focusedIndex == 0
                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (isCancelFocused) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .border(
                                    width = if (isCancelFocused) 2.dp else 0.dp,
                                    color = if (isCancelFocused) Pink else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { onDismiss() }
                                .padding(vertical = 12.dp, horizontal = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LinkOff,
                                    contentDescription = null,
                                    tint = if (isCancelFocused) TextPrimary else TextSecondary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.cancel),
                                    style = ArflixTypography.button,
                                    color = if (isCancelFocused) TextPrimary else TextSecondary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        val isFallbackFocused = focusedIndex == 1
                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (isFallbackFocused) SuccessGreen else Pink.copy(alpha = 0.6f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .border(
                                    width = if (isFallbackFocused) 2.dp else 0.dp,
                                    color = if (isFallbackFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { onUseEmailPassword() }
                                .padding(vertical = 12.dp, horizontal = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Link,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.settings_use_email_password_short),
                                    style = ArflixTypography.button,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun TraktActivationModal(
    verificationUrl: String,
    userCode: String,
    onDismiss: () -> Unit,
    title: String? = null,
    instruction: String? = null,
    onOpenUrl: (() -> Unit)? = null
) {
    val resolvedTitle = title ?: stringResource(R.string.settings_connect_trakt)
    val resolvedInstruction = instruction ?: stringResource(R.string.settings_trakt_instruction, verificationUrl)
    val focusRequester = remember { FocusRequester() }
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val qrContainerSize = if (isMobile) 0.dp else 172.dp
    val qrBitmapSizePx = if (isMobile) 0 else 512
    val clipboardManager = LocalClipboardManager.current

    LaunchedEffect(userCode) {
        focusRequester.requestFocus()
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .then(
                        if (isMobile) Modifier.fillMaxWidth(0.92f).widthIn(max = 520.dp)
                        else Modifier.width(560.dp)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(16.dp))
                    .padding(if (isMobile) 20.dp else 28.dp)
                    .focusRequester(focusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                onDismiss()
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                onDismiss()
                                true
                            }
                            else -> false
                        }
                    }
            ) {
                Text(
                    text = resolvedTitle,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = resolvedInstruction,
                    style = ArflixTypography.body,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    if (!isMobile && verificationUrl.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .size(qrContainerSize)
                                .background(Color.White, RoundedCornerShape(14.dp))
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            QrCodeImage(
                                data = verificationUrl,
                                sizePx = qrBitmapSizePx,
                                modifier = Modifier.fillMaxSize(),
                                foreground = android.graphics.Color.BLACK,
                                background = android.graphics.Color.WHITE
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = userCode,
                            style = ArflixTypography.heroTitle.copy(fontSize = 42.sp),
                            color = Pink,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(R.string.settings_waiting_for_authorization),
                            style = ArflixTypography.caption,
                            color = TextSecondary.copy(alpha = 0.78f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                if (isMobile && onOpenUrl != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Pink, RoundedCornerShape(10.dp))
                            .clickable { onOpenUrl() }
                            .padding(vertical = 13.dp, horizontal = 18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.settings_open_auth_page),
                            style = ArflixTypography.button,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(10.dp))
                            .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(10.dp))
                            .clickable { clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(userCode)) }
                            .padding(vertical = 12.dp, horizontal = 18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.settings_copy_code),
                            style = ArflixTypography.button,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                Box(
                    modifier = Modifier
                        .background(
                            if (isMobile && onOpenUrl != null) Color.White.copy(alpha = 0.08f) else Pink,
                            RoundedCornerShape(10.dp)
                        )
                        .then(if (isMobile && onOpenUrl != null) Modifier.fillMaxWidth() else Modifier)
                        .clickable { onDismiss() }
                        .padding(vertical = 12.dp, horizontal = 18.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = ArflixTypography.button,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MobileSettingsLayout(
    page: String,
    onNavigate: (String) -> Unit,
    uiState: SettingsUiState,
    viewModel: SettingsViewModel,
    stremioAddons: List<com.arflix.tv.data.model.Addon>,
    onSwitchProfile: () -> Unit,
    openContentLanguagePicker: () -> Unit,
    openSubtitlePicker: () -> Unit,
    openSecondarySubtitlePicker: () -> Unit = {},
    openAudioLanguagePicker: () -> Unit,
    openDnsProviderPicker: () -> Unit,
    openUiModeWarningDialog: () -> Unit,
    openQualityFiltersModal: () -> Unit,
    onSubtitleAiModelClick: () -> Unit,
    onSubtitleAiApiKeyClick: () -> Unit,
    onSubtitleAiQrClick: () -> Unit,
    onAddIptvClick: () -> Unit,
    onEditIptvClick: (Int) -> Unit,
    onAddCatalogClick: () -> Unit,
    onRenameCatalogClick: (CatalogConfig) -> Unit,
    onConnectHomeServerClick: () -> Unit,
    onConnectPlexHomeServerClick: () -> Unit,
    onAddCustomAddonClick: () -> Unit,
    openCustomUserAgentDialog: () -> Unit = {},
    onNavigateToTelegram: () -> Unit = {},
    onDisconnectCloud: () -> Unit = {},
    onDisconnectTrakt: () -> Unit = {}
) {
    BackHandler(enabled = page != "MAIN") {
        onNavigate("MAIN")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
    ) {
        if (page == "MAIN") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.settings),
                    style = ArflixTypography.heroTitle.copy(fontSize = 28.sp),
                    color = TextPrimary,
                    modifier = Modifier.weight(1f)
                )
                if (uiState.isLoggedIn) {
                    Text(
                        text = stringResource(R.string.log_out),
                        style = ArflixTypography.button,
                        color = Pink,
                        modifier = Modifier
                            .heightIn(min = 48.dp)
                            .widthIn(min = 72.dp)
                            .clickable { onDisconnectCloud() }
                            .padding(horizontal = 12.dp, vertical = 14.dp)
                    )
                }
            }
            MobileSettingsMainPage(
                uiState = uiState,
                viewModel = viewModel,
                onNavigate = onNavigate,
                openContentLanguagePicker = openContentLanguagePicker,
                openSubtitlePicker = openSubtitlePicker,
                openSecondarySubtitlePicker = openSecondarySubtitlePicker,
                openAudioLanguagePicker = openAudioLanguagePicker,
                onSwitchProfile = onSwitchProfile,
                onNavigateToTelegram = onNavigateToTelegram,
                onDisconnectTrakt = onDisconnectTrakt
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = TextPrimary,
                    modifier = Modifier
                        .clickable { onNavigate("MAIN") }
                        .padding(end = 16.dp)
                        .size(28.dp)
                )
                Text(
                    text = mobileCategoryTitle(page),
                    style = ArflixTypography.heroTitle.copy(fontSize = 24.sp),
                    color = TextPrimary,
                    modifier = Modifier.weight(1f)
                )
            }
            MobileSettingsSubPage(
                page = page,
                onNavigate = onNavigate,
                uiState = uiState,
                viewModel = viewModel,
                stremioAddons = stremioAddons,
                openDnsProviderPicker = openDnsProviderPicker,
                openUiModeWarningDialog = openUiModeWarningDialog,
                openQualityFiltersModal = openQualityFiltersModal,
                onSubtitleAiModelClick = onSubtitleAiModelClick,
                onSubtitleAiApiKeyClick = onSubtitleAiApiKeyClick,
                onSubtitleAiQrClick = onSubtitleAiQrClick,
                onAddIptvClick = onAddIptvClick,
                onEditIptvClick = onEditIptvClick,
                onAddCatalogClick = onAddCatalogClick,
                onRenameCatalogClick = onRenameCatalogClick,
                onConnectHomeServerClick = onConnectHomeServerClick,
                onConnectPlexHomeServerClick = onConnectPlexHomeServerClick,
                onAddCustomAddonClick = onAddCustomAddonClick,
                openCustomUserAgentDialog = openCustomUserAgentDialog
            )
        }
    }
}

/**
 * Maps a stable mobile-settings page navigation key (kept in English so the
 * `when (page)` routing stays stable) to its localized display title.
 */
@Composable
private fun mobileCategoryTitle(page: String): String = when (page) {
    "Playback & Controls" -> stringResource(R.string.settings_cat_playback_controls)
    "Audio & Subtitles" -> stringResource(R.string.settings_cat_audio_subtitles)
    "Appearance" -> stringResource(R.string.interface_label)
    "Addons" -> stringResource(R.string.addons)
    "Plugins & Extensions" -> stringResource(R.string.settings_cat_plugins_extensions)
    "Catalogs" -> stringResource(R.string.catalogs)
    "TV" -> stringResource(R.string.iptv)
    "Home Server" -> stringResource(R.string.settings_home_server)
    else -> page
}

@Composable
private fun MobileSettingsMainPage(
    uiState: SettingsUiState,
    viewModel: SettingsViewModel,
    onNavigate: (String) -> Unit,
    openContentLanguagePicker: () -> Unit,
    openSubtitlePicker: () -> Unit,
    openSecondarySubtitlePicker: () -> Unit = {},
    openAudioLanguagePicker: () -> Unit,
    onSwitchProfile: () -> Unit,
    onNavigateToTelegram: () -> Unit = {},
    onDisconnectTrakt: () -> Unit = {}
) {
    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            MobileSettingsCategory(title = stringResource(R.string.settings_section_languages)) {
                MobileSettingsRow(
                    icon = Icons.Default.Language,
                    title = stringResource(R.string.content_language),
                    value = TMDB_LANGUAGES.firstOrNull { it.first == uiState.contentLanguage }?.second ?: uiState.contentLanguage,
                    isFocused = false,
                    onClick = openContentLanguagePicker
                )
                MobileSettingsRow(
                    icon = Icons.Default.Subtitles,
                    title = stringResource(R.string.default_subtitle),
                    value = uiState.defaultSubtitle,
                    isFocused = false,
                    onClick = openSubtitlePicker
                )
                MobileSettingsRow(
                    icon = Icons.Default.Subtitles,
                    title = stringResource(R.string.secondary_subtitle),
                    value = uiState.secondarySubtitle,
                    isFocused = false,
                    onClick = openSecondarySubtitlePicker
                )
                MobileSettingsRow(
                    icon = Icons.Default.Subtitles,
                    title = stringResource(R.string.filter_subtitles),
                    value = if (uiState.filterSubtitlesByLanguage) "On" else "Off",
                    isFocused = false,
                    onClick = { viewModel.setFilterSubtitlesByLanguage(!uiState.filterSubtitlesByLanguage) }
                )
                MobileSettingsRow(
                    icon = Icons.Default.VolumeUp,
                    title = stringResource(R.string.default_audio),
                    value = uiState.defaultAudioLanguage,
                    isFocused = false,
                    showDivider = false,
                    onClick = openAudioLanguagePicker
                )
            }
        }

        item {
            MobileSettingsCategory(title = stringResource(R.string.settings_section_categories)) {
                val categories = buildList {
                    add("Playback & Controls" to Icons.Default.PlayArrow)
                    add("Audio & Subtitles" to Icons.Default.Speaker)
                    add("Appearance" to Icons.Default.Palette)
                    add("Addons" to Icons.Default.Extension)
                    if (BuildConfig.FEATURE_PLUGINS_ENABLED) {
                        add("Plugins & Extensions" to Icons.Default.Extension)
                    }
                    add("Catalogs" to Icons.Default.Widgets)
                    add("TV" to Icons.Default.LiveTv)
                    add("Home Server" to Icons.Default.Cloud)
                }
                categories.forEachIndexed { index, (name, icon) ->
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigate(name) }
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = icon, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = mobileCategoryTitle(name), style = ArflixTypography.cardTitle.copy(fontSize = 16.sp), color = TextPrimary, modifier = Modifier.weight(1f))
                            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(20.dp))
                        }
                        if (index < categories.lastIndex) {
                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).padding(horizontal = 16.dp).background(Color.White.copy(alpha = 0.05f)))
                        }
                    }
                }
            }
        }

        item {
            MobileSettingsCategory(title = stringResource(R.string.settings_section_user_account)) {
                if (uiState.isLoggedIn) {
                    MobileSettingsRow(
                        icon = Icons.Default.Person,
                        title = stringResource(R.string.cloud_account),
                        subtitle = uiState.accountEmail ?: "",
                        value = stringResource(R.string.settings_force_sync),
                        isFocused = false,
                        onClick = { viewModel.forceCloudSyncNow() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.SwitchAccount,
                        title = stringResource(R.string.switch_profile),
                        value = "",
                        isFocused = false,
                        onClick = onSwitchProfile
                    )
                } else {
                    MobileSettingsRow(
                        icon = Icons.Default.Person,
                        title = stringResource(R.string.cloud_account),
                        value = stringResource(R.string.sign_in),
                        isFocused = false,
                        onClick = { viewModel.openCloudEmailPasswordDialog() }
                    )
                }
                MobileSettingsRow(
                    icon = Icons.Default.Movie,
                    title = stringResource(R.string.trakt_account),
                    value = if (uiState.isTraktAuthenticated) stringResource(R.string.settings_disconnect) else stringResource(R.string.connect),
                    isFocused = false,
                    onClick = { if (uiState.isTraktAuthenticated) onDisconnectTrakt() else viewModel.startTraktAuth() }
                )
                MobileSettingsRow(
                    icon = Icons.Default.QrCode,
                    title = "Telegram",
                    value = stringResource(R.string.settings_open),
                    isFocused = false,
                    onClick = onNavigateToTelegram
                )
                MobileSettingsRow(
                    icon = Icons.Default.SystemUpdate,
                    title = stringResource(R.string.app_version),
                    subtitle = "V${BuildConfig.VERSION_NAME}",
                    value = if (uiState.updateStatus is com.arflix.tv.updater.UpdateStatus.UpdateAvailable) stringResource(R.string.settings_update_available) else stringResource(R.string.settings_check_updates),
                    isFocused = false,
                    showDivider = false,
                    onClick = { viewModel.checkForAppUpdates(force = true, showNoUpdateFeedback = true) }
                )
            }
        }
    }
}

@Composable
private fun MobileSettingsSubPage(
    page: String,
    onNavigate: (String) -> Unit,
    uiState: SettingsUiState,
    viewModel: SettingsViewModel,
    stremioAddons: List<com.arflix.tv.data.model.Addon>,
    openDnsProviderPicker: () -> Unit,
    openUiModeWarningDialog: () -> Unit,
    openQualityFiltersModal: () -> Unit,
    onSubtitleAiModelClick: () -> Unit,
    onSubtitleAiApiKeyClick: () -> Unit,
    onSubtitleAiQrClick: () -> Unit,
    onAddIptvClick: () -> Unit,
    onEditIptvClick: (Int) -> Unit,
    onAddCatalogClick: () -> Unit,
    onRenameCatalogClick: (CatalogConfig) -> Unit,
    onConnectHomeServerClick: () -> Unit,
    onConnectPlexHomeServerClick: () -> Unit,
    onAddCustomAddonClick: () -> Unit,
    openCustomUserAgentDialog: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        when (page) {
            "Playback & Controls" -> {
                MobileSettingsCategory(title = stringResource(R.string.settings_section_playback)) {
                    MobileSettingsRow(
                        icon = Icons.Default.PlayArrow,
                        title = stringResource(R.string.auto_play_next_title),
                        value = if (uiState.autoPlayNext) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setAutoPlayNext(!uiState.autoPlayNext) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.PlayArrow,
                        title = stringResource(R.string.autoplay),
                        value = if (uiState.autoPlaySingleSource) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setAutoPlaySingleSource(!uiState.autoPlaySingleSource) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.HighQuality,
                        title = stringResource(R.string.auto_play_min_quality),
                        value = uiState.autoPlayMinQuality,
                        isFocused = false,
                        onClick = { viewModel.cycleAutoPlayMinQuality() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Movie,
                        title = stringResource(R.string.trailer_auto_play),
                        value = if (uiState.trailerAutoPlay) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setTrailerAutoPlay(!uiState.trailerAutoPlay) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.VolumeUp,
                        title = stringResource(R.string.trailer_sound),
                        value = if (uiState.trailerSoundEnabled) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setTrailerSoundEnabled(!uiState.trailerSoundEnabled) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Schedule,
                        title = stringResource(R.string.trailer_delay),
                        value = "${uiState.trailerDelaySeconds}s",
                        isFocused = false,
                        onClick = { viewModel.cycleTrailerDelay() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Movie,
                        title = stringResource(R.string.trailer_in_cards),
                        value = if (uiState.trailerInCards) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setTrailerInCards(!uiState.trailerInCards) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Settings,
                        title = stringResource(R.string.frame_rate),
                        value = uiState.frameRateMatchingMode,
                        isFocused = false,
                        onClick = { viewModel.cycleFrameRateMatchingMode() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.HighQuality,
                        title = stringResource(R.string.quality_filters),
                        value = uiState.qualityFilterPresetLabel,
                        isFocused = false,
                        showDivider = false,
                        onClick = openQualityFiltersModal
                    )
                }
                MobileSettingsCategory(title = stringResource(R.string.settings_section_controls)) {
                    MobileSettingsRow(
                        icon = Icons.Default.Person,
                        title = stringResource(R.string.skip_profile),
                        value = if (uiState.skipProfileSelection) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setSkipProfileSelection(!uiState.skipProfileSelection) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Language,
                        title = stringResource(R.string.dns_provider),
                        value = uiState.dnsProvider,
                        isFocused = false,
                        onClick = openDnsProviderPicker
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Language,
                        title = stringResource(R.string.custom_user_agent),
                        value = formatUserAgentPreview(uiState.customUserAgent, 20),
                        isFocused = false,
                        showDivider = false,
                        onClick = openCustomUserAgentDialog
                    )
                }
            }
            "Audio & Subtitles" -> {
                MobileSettingsCategory(title = stringResource(R.string.settings_section_subtitles)) {
                    MobileSettingsRow(
                        icon = Icons.Default.Subtitles,
                        title = stringResource(R.string.subtitle_size),
                        value = uiState.subtitleSize,
                        isFocused = false,
                        onClick = { viewModel.cycleSubtitleSize() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Subtitles,
                        title = stringResource(R.string.subtitle_color),
                        value = uiState.subtitleColor,
                        isFocused = false,
                        onClick = { viewModel.cycleSubtitleColor() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Subtitles,
                        title = stringResource(R.string.subtitle_offset),
                        value = uiState.subtitleOffset,
                        isFocused = false,
                        onClick = { viewModel.cycleSubtitleOffset() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Subtitles,
                        title = stringResource(R.string.subtitle_style),
                        value = uiState.subtitleStyle,
                        isFocused = false,
                        onClick = { viewModel.cycleSubtitleStyle() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Subtitles,
                        title = stringResource(R.string.subtitle_stylized),
                        subtitle = stringResource(R.string.subtitle_stylized_desc),
                        value = if (uiState.subtitleStylized) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.toggleSubtitleStylized() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Subtitles,
                        title = stringResource(R.string.filter_subtitles),
                        subtitle = stringResource(R.string.filter_subtitles_desc),
                        value = if (uiState.filterSubtitlesByLanguage) "On" else "Off",
                        isFocused = false,
                        showDivider = false,
                        onClick = { viewModel.setFilterSubtitlesByLanguage(!uiState.filterSubtitlesByLanguage) }
                    )
                }
                MobileSettingsCategory(title = stringResource(R.string.ai_subtitles_section)) {
                    MobileSettingsRow(
                        icon = Icons.Default.AutoAwesome,
                        title = stringResource(R.string.ai_subtitle_translation_title),
                        subtitle = stringResource(R.string.ai_subtitle_translation_desc),
                        value = if (uiState.subtitleAiEnabled) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setSubtitleAiEnabled(!uiState.subtitleAiEnabled) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.AutoAwesome,
                        title = stringResource(R.string.ai_model_title),
                        subtitle = stringResource(R.string.ai_model_desc),
                        value = when (uiState.subtitleAiModel) {
                            com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B -> "Groq - Llama 3.3 70B"
                            com.arflix.tv.ui.screens.player.SubtitleAiModel.GEMINI_FLASH_25 -> "Google - Gemini 2.5 Flash"
                        },
                        isFocused = false,
                        onClick = onSubtitleAiModelClick
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.AutoAwesome,
                        title = stringResource(R.string.ai_auto_select_title),
                        subtitle = stringResource(R.string.ai_auto_select_desc),
                        value = if (uiState.subtitleAiAutoSelect) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setSubtitleAiAutoSelect(!uiState.subtitleAiAutoSelect) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Subtitles,
                        title = stringResource(R.string.ai_remove_hi_title),
                        subtitle = stringResource(R.string.ai_remove_hi_desc),
                        value = if (uiState.subtitleRemoveHearingImpaired) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setSubtitleRemoveHearingImpaired(!uiState.subtitleRemoveHearingImpaired) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.VpnKey,
                        title = stringResource(R.string.ai_api_key_title),
                        subtitle = stringResource(R.string.ai_api_key_desc),
                        value = maskAiApiKey(uiState.subtitleAiApiKey, stringResource(R.string.ai_key_not_set)),
                        isFocused = false,
                        onClick = onSubtitleAiApiKeyClick
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.QrCode,
                        title = stringResource(R.string.ai_scan_qr_title),
                        subtitle = stringResource(R.string.ai_scan_qr_desc),
                        value = "",
                        isFocused = false,
                        showDivider = false,
                        onClick = onSubtitleAiQrClick
                    )
                    if (uiState.subtitleAiEnabled) {
                        Text(
                            text = when (uiState.subtitleAiModel) {
                                com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B ->
                                    stringResource(R.string.ai_groq_disclaimer)
                                com.arflix.tv.ui.screens.player.SubtitleAiModel.GEMINI_FLASH_25 ->
                                    stringResource(R.string.ai_gemini_disclaimer)
                            },
                            style = ArflixTypography.caption.copy(fontSize = 11.sp),
                            color = TextSecondary.copy(alpha = 0.5f),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                    }
                }
                MobileSettingsCategory(title = stringResource(R.string.settings_section_audio)) {
                    MobileSettingsRow(
                        icon = Icons.Default.VolumeUp,
                        title = stringResource(R.string.volume_boost),
                        value = if (uiState.volumeBoostDb > 0) "+${uiState.volumeBoostDb} dB" else "Off",
                        isFocused = false,
                        showDivider = false,
                        onClick = { viewModel.cycleVolumeBoost() }
                    )
                }
            }
            "Appearance" -> {
                MobileSettingsCategory(title = stringResource(R.string.settings_section_appearance)) {
                    MobileSettingsRow(
                        icon = Icons.Default.Palette,
                        title = stringResource(R.string.ui_mode),
                        value = uiState.deviceModeOverride.replaceFirstChar { it.uppercase() },
                        isFocused = false,
                        onClick = openUiModeWarningDialog
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Widgets,
                        title = stringResource(R.string.card_layout),
                        value = uiState.cardLayoutMode,
                        isFocused = false,
                        onClick = { viewModel.toggleCardLayoutMode() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Palette,
                        title = stringResource(R.string.oled_black_background),
                        subtitle = stringResource(R.string.oled_black_background_desc),
                        value = if (uiState.oledBlackBackground) "On" else "Off",
                        isFocused = false,
                        onClick = { viewModel.setOledBlackBackground(!uiState.oledBlackBackground) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Schedule,
                        title = stringResource(R.string.clock_format),
                        value = uiState.clockFormat,
                        isFocused = false,
                        onClick = { viewModel.cycleClockFormat() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Movie,
                        title = stringResource(R.string.show_budget),
                        value = if (uiState.showBudget) "On" else "Off",
                        isFocused = false,
                        showDivider = true,
                        onClick = { viewModel.setShowBudget(!uiState.showBudget) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.VisibilityOff,
                        title = stringResource(R.string.spoiler_blur),
                        value = if (uiState.spoilerBlurEnabled) "On" else "Off",
                        isFocused = false,
                        showDivider = true,
                        onClick = { viewModel.setSpoilerBlurEnabled(!uiState.spoilerBlurEnabled) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Palette,
                        title = stringResource(R.string.accent_color),
                        value = uiState.accentColor,
                        isFocused = false,
                        showDivider = true,
                        onClick = { viewModel.cycleAccentColor() }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Palette,
                        title = stringResource(R.string.smooth_scrolling),
                        subtitle = stringResource(R.string.smooth_scrolling_desc),
                        value = if (uiState.smoothScrolling) "On" else "Off",
                        isFocused = false,
                        showDivider = false,
                        onClick = { viewModel.setSmoothScrolling(!uiState.smoothScrolling) }
                    )
                }
            }
            "Addons", "Plugins & Extensions" -> {
                StremioAddonsSettings(
                    addons = stremioAddons,
                    focusedIndex = -1,
                    focusedActionIndex = 0,
                    onToggleAddon = { viewModel.toggleAddon(it) },
                    onMoveAddonUp = { viewModel.moveAddonUp(it) },
                    onMoveAddonDown = { viewModel.moveAddonDown(it) },
                    onDeleteAddon = { viewModel.removeAddon(it) },
                    onAddCustomAddon = onAddCustomAddonClick
                )
            }
            "Catalogs" -> {
                CatalogsSettings(
                    catalogs = uiState.catalogs,
                    focusedIndex = -1,
                    focusedActionIndex = 0,
                    onAddCatalog = onAddCatalogClick,
                    onRenameCatalog = onRenameCatalogClick,
                    onMoveCatalogUp = { viewModel.moveCatalogUp(it.id) },
                    onMoveCatalogDown = { viewModel.moveCatalogDown(it.id) },
                    onDeleteCatalog = { viewModel.removeCatalog(it.id) }
                )
            }
            "TV" -> {
                IptvSettings(
                    playlists = uiState.iptvPlaylists,
                    channelCount = uiState.iptvChannelCount,
                    isLoading = uiState.isIptvLoading,
                    error = uiState.iptvError,
                    statusMessage = uiState.iptvStatusMessage,
                    statusType = uiState.iptvStatusType,
                    progressText = uiState.iptvProgressText,
                    progressPercent = uiState.iptvProgressPercent,
                    focusedIndex = -1,
                    focusedActionIndex = 0,
                    onConfigure = onAddIptvClick,
                    onEditPlaylist = onEditIptvClick,
                    onTogglePlaylist = { idx ->
                        val updated = uiState.iptvPlaylists.toMutableList()
                        val item = updated.getOrNull(idx) ?: return@IptvSettings
                        updated[idx] = item.copy(enabled = !item.enabled)
                        viewModel.saveIptvPlaylists(updated)
                    },
                    onMovePlaylistUp = { idx ->
                        if (idx <= 0) return@IptvSettings
                        val updated = uiState.iptvPlaylists.toMutableList()
                        val item = updated.removeAt(idx)
                        updated.add(idx - 1, item)
                        viewModel.saveIptvPlaylists(updated)
                    },
                    onMovePlaylistDown = { idx ->
                        val updated = uiState.iptvPlaylists.toMutableList()
                        if (idx !in 0 until updated.lastIndex) return@IptvSettings
                        val item = updated.removeAt(idx)
                        updated.add(idx + 1, item)
                        viewModel.saveIptvPlaylists(updated)
                    },
                    onDeletePlaylist = { idx ->
                        val updated = uiState.iptvPlaylists.toMutableList()
                        if (idx in updated.indices) {
                            updated.removeAt(idx)
                            viewModel.saveIptvPlaylists(updated)
                        }
                    },
                    onRefresh = { viewModel.refreshIptv() },
                    onDelete = { viewModel.clearIptvConfig() },
                    onManageCategories = { playlistId ->
                        viewModel.setIptvSelectedPlaylistId(playlistId)
                        onNavigate("IPTV_CATEGORIES")
                    }
                )
            }
            "IPTV_CATEGORIES" -> {
                IptvCategoriesSettings(
                    playlistId = uiState.iptvSelectedPlaylistId ?: "",
                    availableGroups = uiState.iptvAvailableGroups,
                    hiddenGroups = uiState.iptvHiddenGroups,
                    groupOrder = uiState.iptvGroupOrder,
                    focusedIndex = -1,
                    focusedActionIndex = 0,
                    onToggleHidden = { viewModel.toggleIptvHiddenGroup(uiState.iptvSelectedPlaylistId ?: "", it) },
                    onMoveUp = { viewModel.moveIptvGroupUp(uiState.iptvSelectedPlaylistId ?: "", it) },
                    onMoveDown = { viewModel.moveIptvGroupDown(uiState.iptvSelectedPlaylistId ?: "", it) },
                    onReset = { viewModel.resetIptvGroupOrder(uiState.iptvSelectedPlaylistId ?: "") }
                )
            }
            "Home Server" -> {
                HomeServerSettings(
                    connections = uiState.homeServerConnections,
                    isWorking = uiState.isHomeServerConnecting,
                    isPlexWorking = uiState.isPlexHomeServerPolling || uiState.plexHomeServerAuth != null,
                    error = uiState.homeServerError,
                    focusedIndex = -1,
                    onConnect = onConnectHomeServerClick,
                    onConnectPlex = onConnectPlexHomeServerClick,
                    onEditConnection = { connection ->
                        onConnectHomeServerClick()
                    },
                    onTest = { viewModel.testHomeServerConnection() },
                    onDisconnect = { viewModel.disconnectHomeServer() }
                )
            }
        }
    }
}

@Composable
private fun MobileSettingsCategory(
    title: String,
    content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = ArflixTypography.caption.copy(fontSize = 12.sp, letterSpacing = 1.sp),
            color = TextSecondary,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(BackgroundElevated)
        ) {
            content()
        }
    }
}

@Composable
private fun MobileSettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String = "",
    value: String,
    isFocused: Boolean = false,
    showDivider: Boolean = true,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = title,
                        style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (subtitle.isNotEmpty()) {
                        Text(
                            text = subtitle,
                            style = ArflixTypography.caption.copy(fontSize = 13.sp),
                            color = TextSecondary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            if (value.isNotEmpty()) {
                Spacer(modifier = Modifier.width(16.dp))
                if (value == "On" || value == "Off") {
                    val isChecked = value == "On"
                    Box(
                        modifier = Modifier
                            .width(44.dp)
                            .height(24.dp)
                            .background(
                                color = if (isChecked) SuccessGreen else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(13.dp)
                            )
                            .padding(3.dp),
                        contentAlignment = if (isChecked) Alignment.CenterEnd else Alignment.CenterStart
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        )
                    }
                } else {
                    Text(
                        text = localizeSettingValue(value),
                        style = ArflixTypography.caption.copy(
                            fontSize = 13.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                        ),
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        if (showDivider) {
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).padding(horizontal = 16.dp).background(Color.White.copy(alpha = 0.05f)))
        }
    }
}

private enum class Zone {
    SIDEBAR, SECTION, CONTENT
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun UnknownSourcesModal(
    onDismiss: () -> Unit,
    onOpenSettings: () -> Unit
) {
    var focusedIndex by remember { mutableIntStateOf(1) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .then(
                        if (LocalDeviceType.current.isTouchDevice()) Modifier.fillMaxWidth(0.92f).widthIn(max = 600.dp)
                        else Modifier.width(620.dp)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(18.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 28.dp)
                    .focusRequester(focusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                        when (event.key) {
                            Key.Back, Key.Escape -> { onDismiss(); true }
                            Key.DirectionLeft -> { focusedIndex = 0; true }
                            Key.DirectionRight -> { focusedIndex = 1; true }
                            Key.Enter, Key.DirectionCenter -> {
                                if (focusedIndex == 0) onDismiss() else onOpenSettings()
                                true
                            }
                            else -> false
                        }
                    }
            ) {
                Text(stringResource(R.string.settings_allow_unknown_sources), style = ArflixTypography.sectionTitle, color = TextPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    stringResource(R.string.settings_allow_unknown_sources_desc),
                    style = ArflixTypography.body,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UpdateActionButton(stringResource(R.string.close), focusedIndex == 0, onDismiss)
                    UpdateActionButton(stringResource(R.string.settings_open_settings), focusedIndex == 1, onOpenSettings, highlighted = true)
                }
            }
        }
    }
}

@Composable
private fun UpdateActionButton(
    label: String,
    isFocused: Boolean,
    onClick: () -> Unit,
    highlighted: Boolean = false,
    enabled: Boolean = true
) {
    val background = when {
        !enabled -> Color.White.copy(alpha = 0.06f)
        highlighted && isFocused -> Pink
        isFocused -> Color.White.copy(alpha = 0.16f)
        highlighted -> Pink.copy(alpha = 0.18f)
        else -> Color.White.copy(alpha = 0.08f)
    }
    val textColor = when {
        !enabled -> TextSecondary.copy(alpha = 0.6f)
        highlighted && isFocused -> Color.Black
        highlighted -> Color.White
        isFocused -> TextPrimary
        else -> TextSecondary
    }
    val borderColor = when {
        !enabled -> Color.White.copy(alpha = 0.1f)
        highlighted && isFocused -> Pink.copy(alpha = 0.95f)
        highlighted -> Pink.copy(alpha = 0.4f)
        isFocused -> Color.White.copy(alpha = 0.75f)
        else -> Color.White.copy(alpha = 0.12f)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(background, RoundedCornerShape(10.dp))
            .border(
                width = if (isFocused || highlighted) 1.5.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 18.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = ArflixTypography.button,
            color = textColor
        )
    }
}

@Composable
private fun tvSettingsSidebarGroup(section: String): String {
    return when (section) {
        "accounts", "profiles" -> stringResource(R.string.settings_group_profile)
        "playback", "language", "subtitles", "ai_subtitles" -> stringResource(R.string.playback)
        "iptv", "stremio", "catalogs", "home_server" -> stringResource(R.string.sources)
        else -> stringResource(R.string.settings_group_system)
    }
}

@Composable
private fun SettingsSectionGroupLabel(label: String) {
    Text(
        text = label.uppercase(),
        style = ArflixTypography.caption.copy(fontSize = 10.sp),
        color = TextSecondary.copy(alpha = 0.46f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 9.dp, top = 8.dp)
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsSectionItem(
    icon: ImageVector,
    title: String,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit = {}
) {
    val bgColor = when {
        isFocused -> Color.White.copy(alpha = 0.12f)
        isSelected -> Color.White.copy(alpha = 0.07f)
        else -> Color.Transparent
    }
    val textColor = when {
        isFocused -> TextPrimary
        isSelected -> TextPrimary
        else -> TextSecondary
    }
    val accentColor = resolveAccentColor(fallback = Pink)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(bgColor, RoundedCornerShape(12.dp))
            .border(
                width = if (isFocused) 1.dp else 0.dp,
                color = if (isFocused) accentColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 9.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(
                    if (isSelected || isFocused) accentColor.copy(alpha = 0.14f) else Color.White.copy(alpha = 0.05f),
                    RoundedCornerShape(9.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isFocused || isSelected) accentColor else textColor,
                modifier = Modifier.size(17.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title,
            style = ArflixTypography.body.copy(fontSize = 16.sp),
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(20.dp)
                    .background(accentColor, RoundedCornerShape(99.dp))
            )
        }
    }
}

@Composable
private fun TvSettingsSectionHeader(
    section: String,
    uiState: SettingsUiState,
    addonCount: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tvSettingsSectionTitle(section),
                    style = ArflixTypography.sectionTitle.copy(fontSize = 24.sp),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tvSettingsSectionDescription(section),
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary.copy(alpha = 0.74f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(18.dp))
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tvSettingsSectionPills(section, uiState, addonCount).take(2).forEach { pill ->
                    TvSettingsStatusPill(label = pill)
                }
            }
        }
    }
}

@Composable
private fun TvSettingsStatusPill(label: String) {
    Row(
        modifier = Modifier
            .height(26.dp)
            .background(Color.White.copy(alpha = 0.065f), RoundedCornerShape(999.dp))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(resolveAccentColor(fallback = Pink), RoundedCornerShape(99.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = ArflixTypography.caption.copy(fontSize = 11.sp),
            color = TextSecondary.copy(alpha = 0.86f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun TvSettingsInsightPanel(
    section: String,
    focusedIndex: Int,
    uiState: SettingsUiState,
    addonCount: Int,
    modifier: Modifier = Modifier
) {
    val help = tvSettingsFocusedHelp(section, focusedIndex)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFF050505))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(22.dp))
            .padding(22.dp)
    ) {
        Text(
            text = stringResource(R.string.settings_overview),
            style = ArflixTypography.sectionTitle.copy(fontSize = 22.sp),
            color = TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = tvSettingsSectionDescription(section),
            style = ArflixTypography.caption,
            color = TextSecondary.copy(alpha = 0.7f),
            lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(22.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.055f), RoundedCornerShape(16.dp))
                .border(1.dp, Color.White.copy(alpha = 0.07f), RoundedCornerShape(16.dp))
                .padding(18.dp)
        ) {
            Column {
                Text(
                    text = if (focusedIndex >= 0) stringResource(R.string.settings_focused_setting) else stringResource(R.string.settings_section_label),
                    style = ArflixTypography.caption,
                    color = resolveAccentColor(fallback = Pink),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = help.title,
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = help.description,
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.76f),
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = stringResource(R.string.settings_status),
            style = ArflixTypography.caption.copy(fontSize = 11.sp),
            color = TextSecondary.copy(alpha = 0.54f),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(10.dp))
        tvSettingsPanelFacts(section, uiState, addonCount).forEach { fact ->
            TvSettingsFactRow(fact.first, fact.second)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun TvSettingsFactRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = ArflixTypography.caption,
            color = TextSecondary.copy(alpha = 0.62f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = ArflixTypography.caption,
            color = TextPrimary.copy(alpha = 0.88f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            modifier = Modifier.widthIn(max = 132.dp)
        )
    }
}

private data class TvSettingsHelp(
    val title: String,
    val description: String
)

@Composable
private fun tvSettingsSectionTitle(section: String): String {
    return when (section) {
        "language" -> stringResource(R.string.language_and_audio)
        "subtitles" -> stringResource(R.string.subtitles)
        "ai_subtitles" -> stringResource(R.string.ai_subtitles_section)
        "playback" -> stringResource(R.string.playback)
        "appearance" -> stringResource(R.string.interface_label)
        "profiles" -> stringResource(R.string.profiles)
        "network" -> stringResource(R.string.network)
        "iptv" -> stringResource(R.string.iptv)
        "home_server" -> stringResource(R.string.settings_home_server)
        "catalogs" -> stringResource(R.string.catalogs)
        "stremio" -> stringResource(R.string.addons)
        "accounts" -> stringResource(R.string.accounts)
        else -> section.replaceFirstChar { it.uppercase() }
    }
}

@Composable
private fun tvSettingsSectionDescription(section: String): String {
    return when (section) {
        "language" -> stringResource(R.string.settings_desc_language)
        "subtitles" -> stringResource(R.string.settings_desc_subtitles)
        "ai_subtitles" -> stringResource(R.string.settings_desc_ai_subtitles)
        "playback" -> stringResource(R.string.settings_desc_playback)
        "appearance" -> stringResource(R.string.settings_desc_appearance)
        "profiles" -> stringResource(R.string.settings_desc_profiles)
        "network" -> stringResource(R.string.settings_desc_network)
        "iptv" -> stringResource(R.string.settings_desc_iptv)
        "home_server" -> stringResource(R.string.settings_desc_home_server)
        "catalogs" -> stringResource(R.string.settings_desc_catalogs)
        "stremio" -> stringResource(R.string.settings_desc_stremio)
        "accounts" -> stringResource(R.string.settings_desc_accounts)
        else -> stringResource(R.string.settings_desc_default)
    }
}

@Composable
private fun tvSettingsSectionPills(
    section: String,
    uiState: SettingsUiState,
    addonCount: Int
): List<String> {
    return when (section) {
        "language" -> listOf(
            stringResource(R.string.settings_pill_app, uiState.contentLanguage.uppercase()),
            stringResource(R.string.settings_pill_audio, localizeSettingValue(uiState.defaultAudioLanguage))
        )
        "subtitles" -> listOf(
            stringResource(R.string.settings_pill_default, localizeSettingValue(uiState.defaultSubtitle)),
            stringResource(R.string.settings_pill_size, localizeSettingValue(uiState.subtitleSize))
        )
        "ai_subtitles" -> listOf(
            if (uiState.subtitleAiEnabled) stringResource(R.string.settings_pill_ai_on) else stringResource(R.string.settings_pill_ai_off),
            if (uiState.subtitleAiAutoSelect) stringResource(R.string.settings_pill_autoselect_on) else stringResource(R.string.settings_pill_autoselect_off)
        )
        "playback" -> listOf(
            stringResource(R.string.settings_pill_autoplay, if (uiState.autoPlaySingleSource) stringResource(R.string.settings_inline_on) else stringResource(R.string.settings_inline_off)),
            stringResource(R.string.settings_pill_trailers, if (uiState.trailerAutoPlay) stringResource(R.string.settings_inline_on) else stringResource(R.string.settings_inline_off)),
            stringResource(R.string.settings_pill_min, localizeSettingValue(uiState.autoPlayMinQuality)),
        )
        "appearance" -> listOf(
            stringResource(R.string.settings_pill_oled, if (uiState.oledBlackBackground) stringResource(R.string.settings_inline_on) else stringResource(R.string.settings_inline_off))
        )
        "profiles" -> listOf(if (uiState.skipProfileSelection) stringResource(R.string.settings_pill_skip_on) else stringResource(R.string.settings_pill_picker_on))
        "network" -> listOf(stringResource(R.string.settings_pill_dns, uiState.dnsProvider), if (uiState.showLoadingStats) stringResource(R.string.settings_pill_stats_on) else stringResource(R.string.settings_pill_stats_off))
        "iptv" -> listOf(
            stringResource(R.string.settings_pill_playlists, uiState.iptvPlaylists.size),
            stringResource(R.string.settings_pill_channels, formatCompactCount(uiState.iptvChannelCount)),
            if (uiState.isIptvLoading) stringResource(R.string.settings_refreshing) else stringResource(R.string.settings_ready)
        )
        "home_server" -> listOf(
            stringResource(R.string.settings_pill_connected_count, uiState.homeServerConnections.size),
            if (uiState.isHomeServerConnecting || uiState.isPlexHomeServerPolling) stringResource(R.string.settings_working) else stringResource(R.string.settings_idle)
        )
        "catalogs" -> listOf(stringResource(R.string.settings_pill_catalogs, uiState.catalogs.size), stringResource(R.string.settings_cloud_synced))
        "stremio" -> listOf(stringResource(R.string.settings_pill_installed, addonCount), stringResource(R.string.settings_profile_scoped))
        "accounts" -> listOf(
            if (uiState.isLoggedIn) stringResource(R.string.settings_cloud_connected) else stringResource(R.string.settings_cloud_off),
            if (uiState.isTraktAuthenticated) stringResource(R.string.settings_trakt_connected) else stringResource(R.string.settings_trakt_off),
            if (uiState.isForceCloudSyncing) stringResource(R.string.syncing) else stringResource(R.string.settings_ready)
        )
        else -> emptyList()
    }
}

@Composable
private fun tvSettingsPanelFacts(
    section: String,
    uiState: SettingsUiState,
    addonCount: Int
): List<Pair<String, String>> {
    return when (section) {
        "language" -> listOf(
            stringResource(R.string.settings_fact_app_language) to uiState.contentLanguage.uppercase(),
            stringResource(R.string.audio) to uiState.defaultAudioLanguage
        )
        "subtitles" -> listOf(
            stringResource(R.string.settings_fact_default) to uiState.defaultSubtitle,
            stringResource(R.string.settings_fact_secondary) to uiState.secondarySubtitle,
            stringResource(R.string.settings_fact_style) to uiState.subtitleStyle
        )
        "ai_subtitles" -> listOf(
            stringResource(R.string.settings_fact_ai) to if (uiState.subtitleAiEnabled) stringResource(R.string.settings_enabled) else stringResource(R.string.off),
            stringResource(R.string.settings_fact_autoselect) to if (uiState.subtitleAiAutoSelect) stringResource(R.string.on) else stringResource(R.string.off)
        )
        "playback" -> listOf(
            stringResource(R.string.settings_fact_autoplay) to if (uiState.autoPlaySingleSource) stringResource(R.string.on) else stringResource(R.string.off),
            stringResource(R.string.settings_fact_trailers) to if (uiState.trailerAutoPlay) stringResource(R.string.on) else stringResource(R.string.off),
            stringResource(R.string.settings_fact_frame_rate) to uiState.frameRateMatchingMode
        )
        "appearance" -> listOf(
            stringResource(R.string.settings_fact_oled) to if (uiState.oledBlackBackground) stringResource(R.string.on) else stringResource(R.string.off),
            stringResource(R.string.settings_fact_accent_color) to uiState.accentColor
        )
        "profiles" -> listOf(
            stringResource(R.string.settings_fact_startup) to if (uiState.skipProfileSelection) stringResource(R.string.settings_skip_picker) else stringResource(R.string.settings_show_picker)
        )
        "network" -> listOf(
            stringResource(R.string.settings_fact_dns) to uiState.dnsProvider,
            stringResource(R.string.settings_fact_loading_stats) to if (uiState.showLoadingStats) stringResource(R.string.on) else stringResource(R.string.off),
            stringResource(R.string.settings_fact_user_agent) to formatUserAgentPreview(uiState.customUserAgent, 40)
        )
        "iptv" -> listOf(
            stringResource(R.string.settings_fact_playlists) to "${uiState.iptvPlaylists.size}/3",
            stringResource(R.string.settings_fact_channels) to formatCompactCount(uiState.iptvChannelCount),
            stringResource(R.string.settings_fact_epg) to if (uiState.iptvPlaylists.any { it.epgUrl.isNotBlank() || it.epgUrls.orEmpty().isNotEmpty() }) stringResource(R.string.settings_configured) else stringResource(R.string.settings_optional),
            stringResource(R.string.settings_fact_state) to if (uiState.isIptvLoading) stringResource(R.string.settings_refreshing) else stringResource(R.string.settings_ready)
        )
        "home_server" -> listOf(
            stringResource(R.string.settings_fact_servers) to uiState.homeServerConnections.size.toString(),
            stringResource(R.string.settings_fact_status) to if (uiState.isHomeServerConnecting || uiState.isPlexHomeServerPolling) stringResource(R.string.settings_working) else stringResource(R.string.settings_ready)
        )
        "catalogs" -> listOf(
            stringResource(R.string.catalogs) to uiState.catalogs.size.toString(),
            stringResource(R.string.settings_fact_discovery) to if (uiState.isCatalogSearching) stringResource(R.string.settings_searching) else stringResource(R.string.settings_ready)
        )
        "stremio" -> listOf(
            stringResource(R.string.addons) to addonCount.toString(),
            stringResource(R.string.settings_fact_scope) to stringResource(R.string.settings_current_profile)
        )
        "accounts" -> listOf(
            stringResource(R.string.settings_fact_cloud) to if (uiState.isLoggedIn) stringResource(R.string.connected) else stringResource(R.string.settings_disconnected),
            stringResource(R.string.settings_fact_trakt) to if (uiState.isTraktAuthenticated) stringResource(R.string.connected) else stringResource(R.string.settings_disconnected),
            stringResource(R.string.settings_fact_updates) to if (uiState.isSelfUpdateSupported) stringResource(R.string.settings_available) else stringResource(R.string.settings_play_build)
        )
        else -> emptyList()
    }
}

@Composable
private fun tvSettingsFocusedHelp(section: String, focusedIndex: Int): TvSettingsHelp {
    if (focusedIndex < 0) {
        return TvSettingsHelp(
            title = stringResource(R.string.settings_help_choose_title),
            description = stringResource(R.string.settings_help_choose_desc)
        )
    }
    return when (section) {
        "language" -> when (focusedIndex) {
            0 -> TvSettingsHelp(stringResource(R.string.settings_fact_app_language), stringResource(R.string.settings_help_app_language_desc))
            else -> TvSettingsHelp(stringResource(R.string.default_audio), stringResource(R.string.settings_help_default_audio_desc))
        }
        "subtitles" -> when (focusedIndex) {
            0 -> TvSettingsHelp(stringResource(R.string.default_subtitle), stringResource(R.string.settings_help_default_subtitle_desc))
            1 -> TvSettingsHelp(stringResource(R.string.settings_help_secondary_subtitle), stringResource(R.string.settings_help_secondary_subtitle_desc))
            else -> TvSettingsHelp(stringResource(R.string.settings_help_subtitle_pref), stringResource(R.string.settings_help_subtitle_pref_desc))
        }
        "ai_subtitles" -> TvSettingsHelp(stringResource(R.string.ai_subtitles_section), stringResource(R.string.settings_help_ai_subtitles_desc))
        "playback" -> when (focusedIndex) {
            0 -> TvSettingsHelp(stringResource(R.string.settings_help_next_autoplay), stringResource(R.string.settings_help_next_autoplay_desc))
            1 -> TvSettingsHelp(stringResource(R.string.settings_help_source_autoplay), stringResource(R.string.settings_help_source_autoplay_desc))
            in 3..4 -> TvSettingsHelp(stringResource(R.string.settings_help_trailers), stringResource(R.string.settings_help_trailers_desc))
            7 -> TvSettingsHelp(stringResource(R.string.volume_boost), stringResource(R.string.settings_help_volume_boost_desc))
            else -> TvSettingsHelp(stringResource(R.string.playback), stringResource(R.string.settings_help_playback_desc))
        }
        "appearance" -> TvSettingsHelp(stringResource(R.string.interface_label), stringResource(R.string.settings_help_interface_desc))
        "profiles" -> TvSettingsHelp(stringResource(R.string.profiles), stringResource(R.string.settings_help_profiles_desc))
        "network" -> TvSettingsHelp(stringResource(R.string.network), stringResource(R.string.settings_desc_network))
        "iptv" -> when (focusedIndex) {
            0 -> TvSettingsHelp(stringResource(R.string.settings_help_add_playlist), stringResource(R.string.settings_help_add_playlist_desc))
            else -> TvSettingsHelp(stringResource(R.string.settings_help_iptv_playlist), stringResource(R.string.settings_help_iptv_playlist_desc))
        }
        "home_server" -> when (focusedIndex) {
            0 -> TvSettingsHelp(stringResource(R.string.settings_help_add_server), stringResource(R.string.settings_help_add_server_desc))
            1 -> TvSettingsHelp(stringResource(R.string.settings_connect_with_code), stringResource(R.string.settings_help_connect_code_desc))
            else -> TvSettingsHelp(stringResource(R.string.settings_help_server_connection), stringResource(R.string.settings_help_server_connection_desc))
        }
        "catalogs" -> when (focusedIndex) {
            0 -> TvSettingsHelp(stringResource(R.string.settings_help_discover_catalog), stringResource(R.string.settings_help_discover_catalog_desc))
            else -> TvSettingsHelp(stringResource(R.string.settings_help_catalog_row), stringResource(R.string.settings_help_catalog_row_desc))
        }
        "stremio" -> TvSettingsHelp(stringResource(R.string.settings_help_addon), stringResource(R.string.settings_help_addon_desc))
        "accounts" -> when (focusedIndex) {
            0 -> TvSettingsHelp(stringResource(R.string.cloud_account), stringResource(R.string.settings_help_cloud_account_desc))
            1 -> TvSettingsHelp(stringResource(R.string.settings_help_trakt), stringResource(R.string.settings_help_trakt_desc))
            2 -> TvSettingsHelp(stringResource(R.string.force_cloud_sync), stringResource(R.string.settings_help_force_sync_desc))
            3 -> TvSettingsHelp(stringResource(R.string.settings_help_app_updates), stringResource(R.string.settings_help_app_updates_desc))
            else -> TvSettingsHelp(stringResource(R.string.settings_help_account_data), stringResource(R.string.settings_help_account_data_desc))
        }
        else -> TvSettingsHelp(stringResource(R.string.settings_help_setting), stringResource(R.string.settings_help_setting_desc))
    }
}

private fun formatCompactCount(value: Int): String {
    return when {
        value >= 1_000_000 -> "${value / 1_000_000}M"
        value >= 1_000 -> "${value / 1_000}k"
        else -> value.toString()
    }
}

@Composable
private fun TvGeneralSettingsRows(
    section: String,
    defaultSubtitle: String,
    secondarySubtitle: String = "Off",
    defaultAudioLanguage: String,
    contentLanguage: String = "en-US",
    dnsProvider: String,
    cardLayoutMode: String,
    frameRateMatchingMode: String,
    autoPlayNext: Boolean,
    autoPlaySingleSource: Boolean,
    autoPlayMinQuality: String,
    subtitleSize: String = "Medium",
    subtitleColor: String = "White",
    subtitleOffset: String = "Low",
    subtitleStyle: String = "Bold",
    deviceModeOverride: String = "auto",
    skipProfileSelection: Boolean = false,
    oledBlackBackground: Boolean = false,
    clockFormat: String = "24h",
    showBudget: Boolean = true,
    smoothScrolling: Boolean = true,
    spoilerBlurEnabled: Boolean = false,
    accentColor: String = "White",
    volumeBoostDb: Int = 0,
    focusedIndex: Int,
    onSubtitleClick: () -> Unit,
    onSecondarySubtitleClick: () -> Unit = {},
    onAudioLanguageClick: () -> Unit,
    onCardLayoutToggle: () -> Unit,
    onFrameRateMatchingClick: () -> Unit,
    onDnsProviderClick: () -> Unit,
    onAutoPlayToggle: (Boolean) -> Unit,
    onAutoPlaySingleSourceToggle: (Boolean) -> Unit,
    onAutoPlayMinQualityClick: () -> Unit,
    onDeviceModeClick: () -> Unit = {},
    onContentLanguageClick: () -> Unit = {},
    onSkipProfileSelectionToggle: (Boolean) -> Unit = {},
    onOledBlackBackgroundToggle: (Boolean) -> Unit = {},
    onClockFormatClick: () -> Unit = {},
    onShowBudgetToggle: (Boolean) -> Unit = {},
    onSmoothScrollingToggle: (Boolean) -> Unit = {},
    onSpoilerBlurToggle: (Boolean) -> Unit = {},
    onAccentColorClick: () -> Unit = {},
    showLoadingStats: Boolean = true,
    onShowLoadingStatsToggle: (Boolean) -> Unit = {},
    onVolumeBoostClick: () -> Unit = {},
    trailerAutoPlay: Boolean = false,
    trailerSoundEnabled: Boolean = false,
    onSubtitleSizeClick: () -> Unit = {},
    onSubtitleColorClick: () -> Unit = {},
    onSubtitleOffsetClick: () -> Unit = {},
    onSubtitleStyleClick: () -> Unit = {},
    subtitleStylized: Boolean = true,
    onSubtitleStylizedToggle: () -> Unit = {},
    filterSubtitlesByLanguage: Boolean = true,
    onFilterSubtitlesByLanguageToggle: (Boolean) -> Unit = {},
    onTrailerAutoPlayToggle: (Boolean) -> Unit = {},
    onTrailerSoundEnabledToggle: (Boolean) -> Unit = {},
    trailerInCards: Boolean = true,
    onTrailerInCardsToggle: (Boolean) -> Unit = {},
    trailerDelaySeconds: Int = 1,
    onTrailerDelayClick: () -> Unit = {},
    qualityFilterValue: String = "OFF",
    onQualityFiltersClick: () -> Unit = {},
    subtitleAiEnabled: Boolean = false,
    subtitleAiAutoSelect: Boolean = false,
    subtitleAiApiKey: String = "",
    subtitleAiModel: com.arflix.tv.ui.screens.player.SubtitleAiModel = com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B,
    subtitleRemoveHearingImpaired: Boolean = true,
    onSubtitleAiEnabledToggle: (Boolean) -> Unit = {},
    onSubtitleAiModelClick: () -> Unit = {},
    onSubtitleAiAutoSelectToggle: (Boolean) -> Unit = {},
    onSubtitleRemoveHearingImpairedToggle: (Boolean) -> Unit = {},
    onSubtitleAiApiKeyClick: () -> Unit = {},
    onSubtitleAiQrClick: () -> Unit = {},
    customUserAgent: String = "",
    onCustomUserAgentClick: () -> Unit = {}
) {
    Column {
        tvGeneralRowsForSection(section).forEachIndexed { localIndex, rowId ->
            if (localIndex > 0) Spacer(modifier = Modifier.height(10.dp))
            when (rowId) {
                0 -> SettingsRow(
                    icon = Icons.Default.Language,
                    title = stringResource(R.string.app_language),
                    subtitle = stringResource(R.string.app_language_desc),
                    value = TMDB_LANGUAGES.firstOrNull { it.first == contentLanguage }?.second ?: contentLanguage,
                    isFocused = focusedIndex == localIndex,
                    onClick = onContentLanguageClick,
                    modifier = Modifier.settingsFocusSlot(localIndex)
                )
                1 -> SettingsRow(
                    icon = Icons.Default.Subtitles,
                    title = stringResource(R.string.default_subtitle),
                    subtitle = stringResource(R.string.subtitle_desc),
                    value = defaultSubtitle,
                    isFocused = focusedIndex == localIndex,
                    onClick = onSubtitleClick,
                    modifier = Modifier.settingsFocusSlot(localIndex)
                )
                2 -> SettingsRow(
                    icon = Icons.Default.Subtitles,
                    title = stringResource(R.string.secondary_subtitle),
                    subtitle = stringResource(R.string.secondary_subtitle_desc),
                    value = secondarySubtitle,
                    isFocused = focusedIndex == localIndex,
                    onClick = onSecondarySubtitleClick,
                    modifier = Modifier.settingsFocusSlot(localIndex)
                )
                3 -> SettingsRow(
                    icon = Icons.Default.VolumeUp,
                    title = stringResource(R.string.default_audio),
                    subtitle = stringResource(R.string.audio_desc),
                    value = defaultAudioLanguage,
                    isFocused = focusedIndex == localIndex,
                    onClick = onAudioLanguageClick,
                    modifier = Modifier.settingsFocusSlot(localIndex)
                )
                4 -> SettingsRow(Icons.Default.Subtitles, stringResource(R.string.subtitle_size), stringResource(R.string.subtitle_size_desc), subtitleSize, focusedIndex == localIndex, onSubtitleSizeClick, Modifier.settingsFocusSlot(localIndex))
                5 -> SettingsRow(Icons.Default.Subtitles, stringResource(R.string.subtitle_color), stringResource(R.string.subtitle_color_desc), subtitleColor, focusedIndex == localIndex, onSubtitleColorClick, Modifier.settingsFocusSlot(localIndex))
                6 -> SettingsRow(Icons.Default.Subtitles, stringResource(R.string.subtitle_offset), stringResource(R.string.subtitle_offset_desc), subtitleOffset, focusedIndex == localIndex, onSubtitleOffsetClick, Modifier.settingsFocusSlot(localIndex))
                7 -> SettingsRow(Icons.Default.Subtitles, stringResource(R.string.subtitle_style), stringResource(R.string.subtitle_style_desc), subtitleStyle, focusedIndex == localIndex, onSubtitleStyleClick, Modifier.settingsFocusSlot(localIndex))
                8 -> SettingsToggleRow(stringResource(R.string.subtitle_stylized), stringResource(R.string.subtitle_stylized_desc), subtitleStylized, focusedIndex == localIndex, { onSubtitleStylizedToggle() }, Modifier.settingsFocusSlot(localIndex))
                9 -> SettingsToggleRow(stringResource(R.string.filter_subtitles), stringResource(R.string.filter_subtitles_desc), filterSubtitlesByLanguage, focusedIndex == localIndex, onFilterSubtitlesByLanguageToggle, Modifier.settingsFocusSlot(localIndex))
                10 -> SettingsToggleRow(stringResource(R.string.auto_play_next_title), stringResource(R.string.auto_play_desc), autoPlayNext, focusedIndex == localIndex, onAutoPlayToggle, Modifier.settingsFocusSlot(localIndex))
                11 -> SettingsToggleRow(stringResource(R.string.autoplay), stringResource(R.string.autoplay_desc), autoPlaySingleSource, focusedIndex == localIndex, onAutoPlaySingleSourceToggle, Modifier.settingsFocusSlot(localIndex))
                12 -> SettingsRow(Icons.Default.HighQuality, stringResource(R.string.auto_play_min_quality), stringResource(R.string.auto_play_quality_desc), autoPlayMinQuality, focusedIndex == localIndex, onAutoPlayMinQualityClick, Modifier.settingsFocusSlot(localIndex))
                13 -> SettingsToggleRow(stringResource(R.string.trailer_auto_play), stringResource(R.string.trailer_desc), trailerAutoPlay, focusedIndex == localIndex, onTrailerAutoPlayToggle, Modifier.settingsFocusSlot(localIndex))
                14 -> SettingsToggleRow(stringResource(R.string.trailer_sound), stringResource(R.string.trailer_sound_desc), trailerSoundEnabled, focusedIndex == localIndex, onTrailerSoundEnabledToggle, Modifier.settingsFocusSlot(localIndex))
                15 -> SettingsRow(Icons.Default.Movie, stringResource(R.string.frame_rate), stringResource(R.string.frame_rate_desc), frameRateMatchingMode, focusedIndex == localIndex, onFrameRateMatchingClick, Modifier.settingsFocusSlot(localIndex))
                16 -> SettingsRow(Icons.Default.HighQuality, stringResource(R.string.quality_filters), stringResource(R.string.quality_filters_desc), qualityFilterValue, focusedIndex == localIndex, onQualityFiltersClick, Modifier.settingsFocusSlot(localIndex))
                17 -> SettingsRow(Icons.Default.Widgets, stringResource(R.string.card_layout), stringResource(R.string.card_layout_desc), cardLayoutMode, focusedIndex == localIndex, onCardLayoutToggle, Modifier.settingsFocusSlot(localIndex))
                18 -> SettingsRow(
                    icon = Icons.Default.Settings,
                    title = stringResource(R.string.ui_mode),
                    subtitle = stringResource(R.string.ui_mode_desc),
                    value = when (deviceModeOverride) {
                        "tv" -> "TV"
                        "tablet" -> "Tablet"
                        "phone" -> "Phone"
                        else -> "Auto"
                    },
                    isFocused = focusedIndex == localIndex,
                    onClick = onDeviceModeClick,
                    modifier = Modifier.settingsFocusSlot(localIndex)
                )
                19 -> SettingsToggleRow(stringResource(R.string.skip_profile), stringResource(R.string.skip_profile_desc), skipProfileSelection, focusedIndex == localIndex, onSkipProfileSelectionToggle, Modifier.settingsFocusSlot(localIndex))
                20 -> SettingsToggleRow(stringResource(R.string.oled_black_background), stringResource(R.string.oled_black_background_desc), oledBlackBackground, focusedIndex == localIndex, onOledBlackBackgroundToggle, Modifier.settingsFocusSlot(localIndex))
                21 -> SettingsRow(Icons.Default.Schedule, stringResource(R.string.clock_format), stringResource(R.string.clock_format_desc), if (clockFormat == "12h") "12-hour" else "24-hour", focusedIndex == localIndex, onClockFormatClick, Modifier.settingsFocusSlot(localIndex))
                22 -> SettingsToggleRow(stringResource(R.string.show_budget), stringResource(R.string.show_budget_desc), showBudget, focusedIndex == localIndex, onShowBudgetToggle, Modifier.settingsFocusSlot(localIndex))
                36 -> SettingsToggleRow(stringResource(R.string.smooth_scrolling), stringResource(R.string.smooth_scrolling_desc), smoothScrolling, focusedIndex == localIndex, onSmoothScrollingToggle, Modifier.settingsFocusSlot(localIndex))
                23 -> SettingsToggleRow(stringResource(R.string.spoiler_blur), stringResource(R.string.spoiler_blur_desc), spoilerBlurEnabled, focusedIndex == localIndex, onSpoilerBlurToggle, Modifier.settingsFocusSlot(localIndex))
                24 -> SettingsRow(Icons.Default.Palette, stringResource(R.string.accent_color), stringResource(R.string.accent_color_desc), accentColor, focusedIndex == localIndex, onAccentColorClick, Modifier.settingsFocusSlot(localIndex))
                25 -> SettingsRow(Icons.Default.Language, stringResource(R.string.dns_provider), stringResource(R.string.dns_desc), dnsProvider, focusedIndex == localIndex, onDnsProviderClick, Modifier.settingsFocusSlot(localIndex))
                26 -> SettingsToggleRow(stringResource(R.string.show_loading_stats), stringResource(R.string.show_loading_stats_desc), showLoadingStats, focusedIndex == localIndex, onShowLoadingStatsToggle, Modifier.settingsFocusSlot(localIndex))
                27 -> SettingsRow(
                    icon = Icons.Default.VolumeUp,
                    title = stringResource(R.string.volume_boost),
                    subtitle = stringResource(R.string.volume_boost_desc),
                    value = if (volumeBoostDb == 0) "Off" else "+${volumeBoostDb} dB",
                    isFocused = focusedIndex == localIndex,
                    onClick = onVolumeBoostClick,
                    modifier = Modifier.settingsFocusSlot(localIndex)
                )
                28 -> SettingsToggleRow(stringResource(R.string.ai_subtitle_translation_title), stringResource(R.string.ai_subtitle_translation_desc), subtitleAiEnabled, focusedIndex == localIndex, onSubtitleAiEnabledToggle, Modifier.settingsFocusSlot(localIndex))
                29 -> SettingsRow(
                    icon = Icons.Default.AutoAwesome,
                    title = stringResource(R.string.ai_model_title),
                    subtitle = stringResource(R.string.ai_model_desc),
                    value = when (subtitleAiModel) {
                        com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B -> "Groq - Llama 3.3 70B"
                        com.arflix.tv.ui.screens.player.SubtitleAiModel.GEMINI_FLASH_25 -> "Google - Gemini 2.5 Flash"
                    },
                    isFocused = focusedIndex == localIndex,
                    onClick = onSubtitleAiModelClick,
                    modifier = Modifier.settingsFocusSlot(localIndex).alpha(if (subtitleAiEnabled) 1f else 0.4f)
                )
                30 -> SettingsToggleRow(stringResource(R.string.ai_auto_select_title), stringResource(R.string.ai_auto_select_desc), subtitleAiAutoSelect, focusedIndex == localIndex, onSubtitleAiAutoSelectToggle, Modifier.settingsFocusSlot(localIndex).alpha(if (subtitleAiEnabled) 1f else 0.4f))
                31 -> SettingsToggleRow(stringResource(R.string.ai_remove_hi_title), stringResource(R.string.ai_remove_hi_desc), subtitleRemoveHearingImpaired, focusedIndex == localIndex, onSubtitleRemoveHearingImpairedToggle, Modifier.settingsFocusSlot(localIndex).alpha(if (subtitleAiEnabled) 1f else 0.4f))
                32 -> SettingsRow(Icons.Default.VpnKey, stringResource(R.string.ai_api_key_title), stringResource(R.string.ai_api_key_desc), maskAiApiKey(subtitleAiApiKey, stringResource(R.string.ai_key_not_set)), focusedIndex == localIndex, onSubtitleAiApiKeyClick, Modifier.settingsFocusSlot(localIndex).alpha(if (subtitleAiEnabled) 1f else 0.4f))
                33 -> SettingsRow(Icons.Default.QrCode, stringResource(R.string.ai_scan_qr_title), stringResource(R.string.ai_scan_qr_desc), "", focusedIndex == localIndex, onSubtitleAiQrClick, Modifier.settingsFocusSlot(localIndex).alpha(if (subtitleAiEnabled) 1f else 0.4f))
                34 -> SettingsRow(Icons.Default.Schedule, stringResource(R.string.trailer_delay), stringResource(R.string.trailer_delay_desc), "${trailerDelaySeconds}s", focusedIndex == localIndex, onTrailerDelayClick, Modifier.settingsFocusSlot(localIndex))
                35 -> SettingsRow(Icons.Default.Language, stringResource(R.string.custom_user_agent), stringResource(R.string.custom_user_agent_desc), formatUserAgentPreview(customUserAgent, 30), focusedIndex == localIndex, onCustomUserAgentClick, Modifier.settingsFocusSlot(localIndex))
                37 -> SettingsToggleRow(stringResource(R.string.trailer_in_cards), stringResource(R.string.trailer_in_cards_desc), trailerInCards, focusedIndex == localIndex, onTrailerInCardsToggle, Modifier.settingsFocusSlot(localIndex))
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun GeneralSettings(
    defaultSubtitle: String,
    secondarySubtitle: String = "Off",
    defaultAudioLanguage: String,
    contentLanguage: String = "en-US",
    dnsProvider: String,
    cardLayoutMode: String,
    frameRateMatchingMode: String,
    autoPlayNext: Boolean,
    autoPlaySingleSource: Boolean,
    autoPlayMinQuality: String,
    subtitleSize: String = "Medium",
    subtitleColor: String = "White",
    subtitleOffset: String = "Low",
    subtitleStyle: String = "Bold",
    deviceModeOverride: String = "auto",
    skipProfileSelection: Boolean = false,
    oledBlackBackground: Boolean = false,
    clockFormat: String = "24h",
    showBudget: Boolean = true,
    spoilerBlurEnabled: Boolean = false,
    accentColor: String = "White",
    volumeBoostDb: Int = 0,
    focusedIndex: Int,
    onSubtitleClick: () -> Unit,
    onSecondarySubtitleClick: () -> Unit = {},
    onAudioLanguageClick: () -> Unit,
    onCardLayoutToggle: () -> Unit,
    onFrameRateMatchingClick: () -> Unit,
    onDnsProviderClick: () -> Unit,
    onAutoPlayToggle: (Boolean) -> Unit,
    onAutoPlaySingleSourceToggle: (Boolean) -> Unit,
    onAutoPlayMinQualityClick: () -> Unit,
    onDeviceModeClick: () -> Unit = {},
    onContentLanguageClick: () -> Unit = {},
    onSkipProfileSelectionToggle: (Boolean) -> Unit = {},
    onOledBlackBackgroundToggle: (Boolean) -> Unit = {},
    onClockFormatClick: () -> Unit = {},
    onShowBudgetToggle: (Boolean) -> Unit = {},
    onSpoilerBlurToggle: (Boolean) -> Unit = {},
    onAccentColorClick: () -> Unit = {},
    showLoadingStats: Boolean = true,
    onShowLoadingStatsToggle: (Boolean) -> Unit = {},
    onVolumeBoostClick: () -> Unit = {},
    trailerAutoPlay: Boolean = false,
    trailerSoundEnabled: Boolean = false,
    onSubtitleSizeClick: () -> Unit = {},
    onSubtitleColorClick: () -> Unit = {},
    onSubtitleOffsetClick: () -> Unit = {},
    onSubtitleStyleClick: () -> Unit = {},
    subtitleStylized: Boolean = true,
    onSubtitleStylizedToggle: () -> Unit = {},
    filterSubtitlesByLanguage: Boolean = true,
    onFilterSubtitlesByLanguageToggle: (Boolean) -> Unit = {},
    onTrailerAutoPlayToggle: (Boolean) -> Unit = {},
    onTrailerSoundEnabledToggle: (Boolean) -> Unit = {},
    qualityFilterValue: String = "OFF",
    onQualityFiltersClick: () -> Unit = {},
    subtitleAiEnabled: Boolean = false,
    subtitleAiAutoSelect: Boolean = false,
    subtitleAiApiKey: String = "",
    subtitleAiModel: com.arflix.tv.ui.screens.player.SubtitleAiModel = com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B,
    subtitleRemoveHearingImpaired: Boolean = true,
    onSubtitleAiEnabledToggle: (Boolean) -> Unit = {},
    onSubtitleAiModelClick: () -> Unit = {},
    onSubtitleAiAutoSelectToggle: (Boolean) -> Unit = {},
    onSubtitleRemoveHearingImpairedToggle: (Boolean) -> Unit = {},
    onSubtitleAiApiKeyClick: () -> Unit = {},
    onSubtitleAiQrClick: () -> Unit = {},
    customUserAgent: String = "",
    onCustomUserAgentClick: () -> Unit = {}
) {
    Column {
        // ── Language & Subtitles ──
        Text(
            text = stringResource(R.string.language_and_subtitles),
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = stringResource(R.string.app_language),
            subtitle = stringResource(R.string.app_language_desc),
            value = TMDB_LANGUAGES.firstOrNull { it.first == contentLanguage }?.second ?: contentLanguage,
            isFocused = focusedIndex == 0,
            onClick = onContentLanguageClick,
            modifier = Modifier.settingsFocusSlot(0)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = stringResource(R.string.default_subtitle),
            subtitle = stringResource(R.string.subtitle_desc),
            value = defaultSubtitle,
            isFocused = focusedIndex == 1,
            onClick = onSubtitleClick,
            modifier = Modifier.settingsFocusSlot(1)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = stringResource(R.string.secondary_subtitle),
            subtitle = stringResource(R.string.secondary_subtitle_desc),
            value = secondarySubtitle,
            isFocused = focusedIndex == 2,
            onClick = onSecondarySubtitleClick,
            modifier = Modifier.settingsFocusSlot(2)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.VolumeUp,
            title = stringResource(R.string.default_audio),
            subtitle = stringResource(R.string.audio_desc),
            value = defaultAudioLanguage,
            isFocused = focusedIndex == 3,
            onClick = onAudioLanguageClick,
            modifier = Modifier.settingsFocusSlot(3)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = stringResource(R.string.subtitle_size),
            subtitle = stringResource(R.string.subtitle_size_desc),
            value = subtitleSize,
            isFocused = focusedIndex == 4,
            onClick = onSubtitleSizeClick,
            modifier = Modifier.settingsFocusSlot(4)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = stringResource(R.string.subtitle_color),
            subtitle = stringResource(R.string.subtitle_color_desc),
            value = subtitleColor,
            isFocused = focusedIndex == 5,
            onClick = onSubtitleColorClick,
            modifier = Modifier.settingsFocusSlot(5)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = stringResource(R.string.subtitle_offset),
            subtitle = stringResource(R.string.subtitle_offset_desc),
            value = subtitleOffset,
            isFocused = focusedIndex == 6,
            onClick = onSubtitleOffsetClick,
            modifier = Modifier.settingsFocusSlot(6)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = stringResource(R.string.subtitle_style),
            subtitle = stringResource(R.string.subtitle_style_desc),
            value = subtitleStyle,
            isFocused = focusedIndex == 7,
            onClick = onSubtitleStyleClick,
            modifier = Modifier.settingsFocusSlot(7)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.subtitle_stylized),
            subtitle = stringResource(R.string.subtitle_stylized_desc),
            isEnabled = subtitleStylized,
            isFocused = focusedIndex == 8,
            onToggle = { onSubtitleStylizedToggle() },
            modifier = Modifier.settingsFocusSlot(8)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.filter_subtitles),
            subtitle = stringResource(R.string.filter_subtitles_desc),
            isEnabled = filterSubtitlesByLanguage,
            isFocused = focusedIndex == 9,
            onToggle = onFilterSubtitlesByLanguageToggle,
            modifier = Modifier.settingsFocusSlot(9)
        )

        // ── Playback ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.playback),
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsToggleRow(
            title = stringResource(R.string.auto_play_next_title),
            subtitle = stringResource(R.string.auto_play_desc),
            isEnabled = autoPlayNext,
            isFocused = focusedIndex == 10,
            onToggle = onAutoPlayToggle,
            modifier = Modifier.settingsFocusSlot(10)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.autoplay),
            subtitle = stringResource(R.string.autoplay_desc),
            isEnabled = autoPlaySingleSource,
            isFocused = focusedIndex == 11,
            onToggle = onAutoPlaySingleSourceToggle,
            modifier = Modifier.settingsFocusSlot(11)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.HighQuality,
            title = stringResource(R.string.auto_play_min_quality),
            subtitle = stringResource(R.string.auto_play_quality_desc),
            value = autoPlayMinQuality,
            isFocused = focusedIndex == 12,
            onClick = onAutoPlayMinQualityClick,
            modifier = Modifier.settingsFocusSlot(12)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.trailer_auto_play),
            subtitle = stringResource(R.string.trailer_desc),
            isEnabled = trailerAutoPlay,
            isFocused = focusedIndex == 13,
            onToggle = onTrailerAutoPlayToggle,
            modifier = Modifier.settingsFocusSlot(13)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.trailer_sound),
            subtitle = stringResource(R.string.trailer_sound_desc),
            isEnabled = trailerSoundEnabled,
            isFocused = focusedIndex == 14,
            onToggle = onTrailerSoundEnabledToggle,
            modifier = Modifier.settingsFocusSlot(14)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Movie,
            title = stringResource(R.string.frame_rate),
            subtitle = stringResource(R.string.frame_rate_desc),
            value = frameRateMatchingMode,
            isFocused = focusedIndex == 15,
            onClick = onFrameRateMatchingClick,
            modifier = Modifier.settingsFocusSlot(15)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.HighQuality,
            title = stringResource(R.string.quality_filters),
            subtitle = stringResource(R.string.quality_filters_desc),
            value = qualityFilterValue,
            isFocused = focusedIndex == 16,
            onClick = onQualityFiltersClick,
            modifier = Modifier.settingsFocusSlot(16)
        )

        // ── Interface ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.interface_label),
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.Widgets,
            title = stringResource(R.string.card_layout),
            subtitle = stringResource(R.string.card_layout_desc),
            value = cardLayoutMode,
            isFocused = focusedIndex == 17,
            onClick = onCardLayoutToggle,
            modifier = Modifier.settingsFocusSlot(17)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Settings,
            title = stringResource(R.string.ui_mode),
            subtitle = stringResource(R.string.ui_mode_desc),
            value = when (deviceModeOverride) {
                "tv" -> "TV"
                "tablet" -> "Tablet"
                "phone" -> "Phone"
                else -> "Auto"
            },
            isFocused = focusedIndex == 18,
            onClick = onDeviceModeClick,
            modifier = Modifier.settingsFocusSlot(18)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.skip_profile),
            subtitle = stringResource(R.string.skip_profile_desc),
            isEnabled = skipProfileSelection,
            isFocused = focusedIndex == 19,
            onToggle = onSkipProfileSelectionToggle,
            modifier = Modifier.settingsFocusSlot(19)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.oled_black_background),
            subtitle = stringResource(R.string.oled_black_background_desc),
            isEnabled = oledBlackBackground,
            isFocused = focusedIndex == 20,
            onToggle = onOledBlackBackgroundToggle,
            modifier = Modifier.settingsFocusSlot(20)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Schedule,
            title = stringResource(R.string.clock_format),
            subtitle = stringResource(R.string.clock_format_desc),
            value = if (clockFormat == "12h") stringResource(R.string.settings_clock_12h) else stringResource(R.string.settings_clock_24h),
            isFocused = focusedIndex == 21,
            onClick = onClockFormatClick,
            modifier = Modifier.settingsFocusSlot(21)
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Home hero controls — issue #72. The movie Budget line on the hero banner
        // makes the metadata row noisy on small screens and some users want to hide it.
        SettingsToggleRow(
            title = stringResource(R.string.show_budget),
            subtitle = stringResource(R.string.show_budget_desc),
            isEnabled = showBudget,
            isFocused = focusedIndex == 22,
            onToggle = onShowBudgetToggle,
            modifier = Modifier.settingsFocusSlot(22)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.spoiler_blur),
            subtitle = stringResource(R.string.spoiler_blur_desc),
            isEnabled = spoilerBlurEnabled,
            isFocused = focusedIndex == 23,
            onToggle = onSpoilerBlurToggle,
            modifier = Modifier.settingsFocusSlot(23)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Palette,
            title = stringResource(R.string.accent_color),
            subtitle = stringResource(R.string.accent_color_desc),
            value = accentColor,
            isFocused = focusedIndex == 24,
            onClick = onAccentColorClick,
            modifier = Modifier.settingsFocusSlot(24)
        )

        // ── Network ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.network),
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.Language,
            title = stringResource(R.string.dns_provider),
            subtitle = stringResource(R.string.dns_desc),
            value = dnsProvider,
            isFocused = focusedIndex == 25,
            onClick = onDnsProviderClick,
            modifier = Modifier.settingsFocusSlot(25)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.show_loading_stats),
            subtitle = stringResource(R.string.show_loading_stats_desc),
            isEnabled = showLoadingStats,
            isFocused = focusedIndex == 26,
            onToggle = onShowLoadingStatsToggle,
            modifier = Modifier.settingsFocusSlot(26)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Language,
            title = stringResource(R.string.custom_user_agent),
            subtitle = stringResource(R.string.custom_user_agent_desc),
            value = formatUserAgentPreview(customUserAgent, 30),
            isFocused = focusedIndex == 35,
            onClick = onCustomUserAgentClick,
            modifier = Modifier.settingsFocusSlot(35)
        )

        // ── Audio ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.audio),
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.VolumeUp,
            title = stringResource(R.string.volume_boost),
            subtitle = stringResource(R.string.volume_boost_desc),
            value = when (volumeBoostDb) {
                0 -> "Off"
                else -> "+${volumeBoostDb} dB"
            },
            isFocused = focusedIndex == 27,
            onClick = onVolumeBoostClick,
            modifier = Modifier.settingsFocusSlot(27)
        )

        // ── AI Subtitles ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.ai_subtitles_section),
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )
        SettingsToggleRow(
            title = stringResource(R.string.ai_subtitle_translation_title),
            subtitle = stringResource(R.string.ai_subtitle_translation_desc),
            isEnabled = subtitleAiEnabled,
            isFocused = focusedIndex == 28,
            onToggle = onSubtitleAiEnabledToggle,
            modifier = Modifier.settingsFocusSlot(28)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.AutoAwesome,
            title = stringResource(R.string.ai_model_title),
            subtitle = stringResource(R.string.ai_model_desc),
            value = when (subtitleAiModel) {
                com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B -> "Groq – Llama 3.3 70B"
                com.arflix.tv.ui.screens.player.SubtitleAiModel.GEMINI_FLASH_25 -> "Google – Gemini 2.5 Flash"
            },
            isFocused = focusedIndex == 29,
            onClick = onSubtitleAiModelClick,
            modifier = Modifier.settingsFocusSlot(29).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.ai_auto_select_title),
            subtitle = stringResource(R.string.ai_auto_select_desc),
            isEnabled = subtitleAiAutoSelect,
            isFocused = focusedIndex == 30,
            onToggle = onSubtitleAiAutoSelectToggle,
            modifier = Modifier.settingsFocusSlot(30).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.ai_remove_hi_title),
            subtitle = stringResource(R.string.ai_remove_hi_desc),
            isEnabled = subtitleRemoveHearingImpaired,
            isFocused = focusedIndex == 31,
            onToggle = onSubtitleRemoveHearingImpairedToggle,
            modifier = Modifier.settingsFocusSlot(31).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.VpnKey,
            title = stringResource(R.string.ai_api_key_title),
            subtitle = stringResource(R.string.ai_api_key_desc),
            value = maskAiApiKey(subtitleAiApiKey, stringResource(R.string.ai_key_not_set)),
            isFocused = focusedIndex == 32,
            onClick = onSubtitleAiApiKeyClick,
            modifier = Modifier.settingsFocusSlot(32).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.QrCode,
            title = stringResource(R.string.ai_scan_qr_title),
            subtitle = stringResource(R.string.ai_scan_qr_desc),
            value = "",
            isFocused = focusedIndex == 33,
            onClick = onSubtitleAiQrClick,
            modifier = Modifier.settingsFocusSlot(33).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        if (subtitleAiEnabled) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = when (subtitleAiModel) {
                    com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B ->
                        stringResource(R.string.ai_groq_disclaimer)
                    com.arflix.tv.ui.screens.player.SubtitleAiModel.GEMINI_FLASH_25 ->
                        stringResource(R.string.ai_gemini_disclaimer)
                },
                style = ArflixTypography.caption.copy(fontSize = 11.sp),
                color = TextSecondary.copy(alpha = 0.4f),
                modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 8.dp)
            )
        }
    }
}

private fun maskAiApiKey(key: String, notSetLabel: String = "Not set"): String {
    val trimmed = key.trim()
    if (trimmed.isBlank()) return notSetLabel
    val provider = when {
        trimmed.startsWith("gsk_") -> "Groq · "
        trimmed.startsWith("AIzaSy") -> "Gemini · "
        else -> ""
    }
    val masked = if (trimmed.length <= 4) "••••" else "••••${trimmed.takeLast(4)}"
    return "$provider$masked"
}

@Composable
private fun AiModelDialog(
    currentModel: com.arflix.tv.ui.screens.player.SubtitleAiModel,
    onModelSelected: (com.arflix.tv.ui.screens.player.SubtitleAiModel) -> Unit,
    onDismiss: () -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val options = listOf(
        Triple(com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B, "Groq – Llama 3.3 70B", stringResource(R.string.ai_groq_model_note)),
        Triple(com.arflix.tv.ui.screens.player.SubtitleAiModel.GEMINI_FLASH_25, "Google – Gemini 2.5 Flash", stringResource(R.string.ai_gemini_model_note))
    )
    BackHandler { onDismiss() }
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .then(
                    if (isMobile) Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    else Modifier.width(480.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundElevated)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                Text(
                    text = stringResource(R.string.ai_model_title),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.ai_model_dialog_subtitle),
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                options.forEach { (model, label, note) ->
                    val focusRequester = remember { FocusRequester() }
                    val isSelected = model == currentModel
                    Surface(
                        onClick = { onModelSelected(model) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                            .focusRequester(focusRequester),
                        colors = ClickableSurfaceDefaults.colors(
                            containerColor = if (isSelected) Pink.copy(alpha = 0.15f) else BackgroundElevated,
                            focusedContainerColor = Pink.copy(alpha = 0.25f)
                        ),
                        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(10.dp))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = label, style = ArflixTypography.cardTitle, color = TextPrimary)
                                Text(text = note, style = ArflixTypography.caption, color = TextSecondary)
                            }
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Pink,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AiApiKeyDialog(
    currentKey: String,
    onSave: (String) -> Unit,
    onDismiss: () -> Unit,
    model: com.arflix.tv.ui.screens.player.SubtitleAiModel = com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    var value by remember(currentKey) { mutableStateOf(currentKey) }
    val inputFocusRequester = remember { FocusRequester() }
    val placeholder = when (model) {
        com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B -> "gsk_..."
        com.arflix.tv.ui.screens.player.SubtitleAiModel.GEMINI_FLASH_25 -> "AIzaSy..."
    }
    BackHandler { onDismiss() }
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100)
        inputFocusRequester.requestFocus()
    }
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .then(
                    if (isMobile) Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    else Modifier.width(520.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundElevated)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                Text(text = stringResource(R.string.ai_api_key_title), style = ArflixTypography.sectionTitle, color = TextPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(if (model == com.arflix.tv.ui.screens.player.SubtitleAiModel.GROQ_LLAMA_70B) R.string.ai_api_key_dialog_subtitle_groq else R.string.ai_api_key_dialog_subtitle_gemini),
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.material3.OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    placeholder = { Text(placeholder, color = TextSecondary.copy(alpha = 0.4f)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().focusRequester(inputFocusRequester),
                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedBorderColor = Pink,
                        unfocusedBorderColor = TextSecondary.copy(alpha = 0.3f)
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    val cancelFocus = remember { FocusRequester() }
                    val saveFocus = remember { FocusRequester() }
                    Surface(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f).focusRequester(cancelFocus),
                        colors = ClickableSurfaceDefaults.colors(
                            containerColor = BackgroundElevated,
                            focusedContainerColor = BackgroundElevated.copy(alpha = 0.8f)
                        ),
                        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp)),
                        border = ClickableSurfaceDefaults.border(
                            border = androidx.tv.material3.Border(
                                border = androidx.compose.foundation.BorderStroke(1.dp, TextSecondary.copy(alpha = 0.3f))
                            )
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = TextSecondary
                        )
                    }
                    Surface(
                        onClick = { onSave(value) },
                        modifier = Modifier.weight(1f).focusRequester(saveFocus),
                        colors = ClickableSurfaceDefaults.colors(
                            containerColor = Pink.copy(alpha = 0.15f),
                            focusedContainerColor = Pink.copy(alpha = 0.3f)
                        ),
                        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = stringResource(R.string.save),
                            modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = Pink
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomUserAgentDialog(
    currentValue: String,
    onSave: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    var value by remember(currentValue) { mutableStateOf(currentValue) }
    val inputFocusRequester = remember { FocusRequester() }
    BackHandler { onDismiss() }
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100)
        inputFocusRequester.requestFocus()
    }
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .then(
                    if (isMobile) Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    else Modifier.width(520.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundElevated)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                Text(text = stringResource(R.string.custom_user_agent), style = ArflixTypography.sectionTitle, color = TextPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.custom_user_agent_desc),
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.material3.OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    placeholder = { Text(OkHttpProvider.DEFAULT_USER_AGENT, color = TextSecondary.copy(alpha = 0.4f)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().focusRequester(inputFocusRequester),
                    colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedBorderColor = Pink,
                        unfocusedBorderColor = TextSecondary.copy(alpha = 0.3f)
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    val cancelFocus = remember { FocusRequester() }
                    val saveFocus = remember { FocusRequester() }
                    Surface(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f).focusRequester(cancelFocus).pointerInput(Unit) {
                            detectTapGestures(onTap = { onDismiss() })
                        },
                        colors = ClickableSurfaceDefaults.colors(
                            containerColor = BackgroundElevated,
                            focusedContainerColor = BackgroundElevated.copy(alpha = 0.8f)
                        ),
                        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp)),
                        border = ClickableSurfaceDefaults.border(
                            border = androidx.tv.material3.Border(
                                border = androidx.compose.foundation.BorderStroke(1.dp, TextSecondary.copy(alpha = 0.3f))
                            )
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = TextSecondary
                        )
                    }
                    Surface(
                        onClick = { onSave(value) },
                        modifier = Modifier.weight(1f).focusRequester(saveFocus).pointerInput(Unit) {
                            detectTapGestures(onTap = { onSave(value) })
                        },
                        colors = ClickableSurfaceDefaults.colors(
                            containerColor = Pink.copy(alpha = 0.15f),
                            focusedContainerColor = Pink.copy(alpha = 0.25f)
                        ),
                        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp)),
                        border = ClickableSurfaceDefaults.border(
                            border = androidx.tv.material3.Border(
                                border = androidx.compose.foundation.BorderStroke(1.dp, Pink.copy(alpha = 0.4f))
                            )
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.save),
                            modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = Pink
                        )
                    }
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(150)
                        inputFocusRequester.requestFocus()
                    }
                }
            }
        }
    }
}

@Composable
private fun AiKeyQrOverlay(
    qrBitmap: android.graphics.Bitmap?,
    serverUrl: String?,
    keyReceived: Boolean = false,
    onClose: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }
    BackHandler { onClose() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.88f)),
        contentAlignment = Alignment.Center
    ) {
        if (keyReceived) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Pink,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = stringResource(R.string.ai_key_saved_title), style = ArflixTypography.sectionTitle, color = TextPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(R.string.ai_key_saved_subtitle), style = ArflixTypography.caption, color = TextSecondary)
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.ai_qr_scan_hint),
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (qrBitmap != null) {
                    androidx.compose.foundation.Image(
                        bitmap = qrBitmap.asImageBitmap(),
                        contentDescription = stringResource(R.string.settings_cd_qr_code),
                        modifier = Modifier.size(220.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                if (serverUrl != null) {
                    Text(text = serverUrl, style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.5f))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    onClick = onClose,
                    modifier = Modifier.focusRequester(focusRequester),
                    colors = ClickableSurfaceDefaults.colors(
                        containerColor = BackgroundElevated,
                        focusedContainerColor = Pink
                    ),
                    shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = stringResource(R.string.done),
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 12.dp),
                        color = TextPrimary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun HomeServerSettings(
    connections: List<HomeServerConnection>,
    isWorking: Boolean,
    isPlexWorking: Boolean,
    error: String?,
    focusedIndex: Int,
    onConnect: () -> Unit,
    onConnectPlex: () -> Unit,
    onEditConnection: (HomeServerConnection) -> Unit,
    onTest: () -> Unit,
    onDisconnect: () -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val hasConnections = connections.isNotEmpty()

    if (isMobile) {
        // Mobile UI: use MobileSettingsCategory/MobileSettingsRow style
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            MobileSettingsCategory(title = stringResource(R.string.settings_section_connections)) {
                MobileSettingsRow(
                    icon = Icons.Default.Cloud,
                    title = stringResource(R.string.settings_add_server),
                    subtitle = stringResource(R.string.settings_add_server_subtitle),
                    value = if (isWorking) stringResource(R.string.settings_working) else if (hasConnections) stringResource(R.string.settings_add_another) else "",
                    isFocused = false,
                    onClick = onConnect
                )
                MobileSettingsRow(
                    icon = Icons.Default.QrCode,
                    title = stringResource(R.string.settings_connect_with_code),
                    subtitle = stringResource(R.string.settings_connect_code_subtitle),
                    value = if (isPlexWorking) stringResource(R.string.settings_waiting) else "",
                    isFocused = false,
                    showDivider = hasConnections,
                    onClick = onConnectPlex
                )
                connections.forEachIndexed { index, connection ->
                    val libraries = connection.collections.count { it.enabled }
                    val description = listOfNotNull(
                        homeServerKindLabel(connection.serverKind).takeIf { it.isNotBlank() },
                        connection.userName.takeIf { it.isNotBlank() },
                        if (libraries > 0) stringResource(R.string.settings_collections_count, libraries) else null
                    ).joinToString("  |  ").ifBlank { connection.serverUrl }
                    MobileSettingsRow(
                        icon = Icons.Default.Cloud,
                        title = connection.displayName.ifBlank { connection.serverName }.ifBlank { connection.serverUrl },
                        subtitle = description,
                        value = "",
                        isFocused = false,
                        showDivider = index < connections.size - 1,
                        onClick = { onEditConnection(connection) }
                    )
                }
            }
            MobileSettingsCategory(title = stringResource(R.string.settings_section_actions)) {
                MobileSettingsRow(
                    icon = Icons.Default.Settings,
                    title = stringResource(R.string.settings_test_connection),
                    subtitle = if (!hasConnections) stringResource(R.string.settings_connect_server_first) else stringResource(R.string.settings_test_reach_servers),
                    value = "",
                    isFocused = false,
                    onClick = { if (hasConnections) onTest() }
                )
                MobileSettingsRow(
                    icon = Icons.Default.Delete,
                    title = stringResource(R.string.settings_disconnect_all),
                    subtitle = if (!hasConnections) stringResource(R.string.settings_no_server_connected) else stringResource(R.string.settings_remove_all_servers),
                    value = "",
                    isFocused = false,
                    showDivider = false,
                    onClick = { if (hasConnections) onDisconnect() }
                )
            }
            if (!error.isNullOrBlank()) {
                Text(
                    text = error,
                    style = ArflixTypography.caption.copy(fontSize = 11.sp),
                    color = Color(0xFFFF6B6B),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    } else {
        // TV UI: existing layout
        Column {
            SettingsRow(
                icon = Icons.Default.Cloud,
                title = stringResource(R.string.settings_add_server),
                subtitle = stringResource(R.string.settings_add_server_subtitle),
                value = if (isWorking) stringResource(R.string.settings_working) else if (hasConnections) stringResource(R.string.settings_add_another) else stringResource(R.string.connect),
                isFocused = focusedIndex == 0,
                onClick = onConnect,
                modifier = Modifier.settingsFocusSlot(0)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SettingsActionRow(
                title = stringResource(R.string.settings_connect_with_code),
                description = stringResource(R.string.settings_connect_code_description),
                actionLabel = if (isPlexWorking) stringResource(R.string.settings_waiting) else stringResource(R.string.settings_code),
                isFocused = focusedIndex == 1,
                onClick = onConnectPlex,
                modifier = Modifier.settingsFocusSlot(1)
            )

            connections.forEachIndexed { index, connection ->
                Spacer(modifier = Modifier.height(16.dp))
                val libraries = connection.collections.count { it.enabled }
                val description = listOfNotNull(
                    homeServerKindLabel(connection.serverKind).takeIf { it.isNotBlank() },
                    connection.userName.takeIf { it.isNotBlank() },
                    if (libraries > 0) stringResource(R.string.settings_collections_count, libraries) else null
                ).joinToString("  |  ").ifBlank { connection.serverUrl }

                SettingsActionRow(
                    title = connection.displayName.ifBlank { connection.serverName }.ifBlank { connection.serverUrl },
                    description = description,
                    actionLabel = stringResource(R.string.settings_change),
                    isFocused = focusedIndex == index + 2,
                    onClick = { onEditConnection(connection) },
                    modifier = Modifier.settingsFocusSlot(index + 2)
                )
            }

            val testIndex = connections.size + 2
            val disconnectIndex = connections.size + 3

            Spacer(modifier = Modifier.height(16.dp))

            SettingsActionRow(
                title = stringResource(R.string.settings_test_connection),
                description = if (!hasConnections) stringResource(R.string.settings_connect_server_first) else stringResource(R.string.settings_test_reach_servers),
                actionLabel = if (isWorking) stringResource(R.string.settings_working) else stringResource(R.string.settings_test),
                isFocused = focusedIndex == testIndex,
                onClick = { if (hasConnections) onTest() },
                modifier = Modifier.settingsFocusSlot(testIndex)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SettingsActionRow(
                title = stringResource(R.string.settings_disconnect_all),
                description = if (!hasConnections) stringResource(R.string.settings_no_server_connected) else stringResource(R.string.settings_remove_all_servers),
                actionLabel = stringResource(R.string.settings_remove),
                isFocused = focusedIndex == disconnectIndex,
                onClick = { if (hasConnections) onDisconnect() },
                modifier = Modifier.settingsFocusSlot(disconnectIndex)
            )

            if (!error.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = error,
                    style = ArflixTypography.caption,
                    color = Color(0xFFFF6B6B)
                )
            }
        }
    }
}

@Composable
private fun homeServerKindLabel(kind: HomeServerKind): String {
    return when (kind) {
        HomeServerKind.JELLYFIN,
        HomeServerKind.EMBY,
        HomeServerKind.PLEX -> stringResource(R.string.settings_media_server)
        HomeServerKind.UNKNOWN -> ""
    }
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun IptvSettings(
    playlists: List<com.arflix.tv.data.repository.IptvPlaylistEntry>,
    channelCount: Int,
    isLoading: Boolean,
    error: String?,
    statusMessage: String?,
    statusType: ToastType,
    progressText: String?,
    progressPercent: Int,
    focusedIndex: Int,
    focusedActionIndex: Int,
    onConfigure: () -> Unit,
    onEditPlaylist: (Int) -> Unit,
    onTogglePlaylist: (Int) -> Unit,
    onMovePlaylistUp: (Int) -> Unit,
    onMovePlaylistDown: (Int) -> Unit,
    onDeletePlaylist: (Int) -> Unit,
    onRefresh: () -> Unit,
    onDelete: () -> Unit,
    onManageCategories: (String) -> Unit = {}
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    var selectionMode by remember { mutableStateOf(false) }
    var selectedIndices by remember { mutableStateOf(setOf<Int>()) }

    if (isMobile) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            if (selectionMode) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.settings_n_selected, selectedIndices.size), style = ArflixTypography.sectionTitle, color = TextPrimary)
                    Spacer(modifier = Modifier.weight(1f))
                    if (selectedIndices.isNotEmpty()) {
                        Box(modifier = Modifier.size(36.dp).clickable { selectedIndices.sortedDescending().forEach { onDeletePlaylist(it) }; selectionMode = false; selectedIndices = emptySet() }.background(Color(0xFFDC2626), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete), tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Box(modifier = Modifier.size(36.dp).clickable { selectionMode = false; selectedIndices = emptySet() }.background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close), tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                }
            }
            MobileSettingsCategory(title = stringResource(R.string.settings_section_playlists)) {
                MobileSettingsRow(icon = Icons.Default.Add, title = stringResource(R.string.add_playlist), subtitle = if (playlists.isEmpty()) stringResource(R.string.settings_add_tv_lists_hint) else stringResource(R.string.settings_create_another_tv), value = if (playlists.size >= 3) stringResource(R.string.settings_badge_full_short) else "", isFocused = false, showDivider = playlists.isNotEmpty(), onClick = onConfigure)
                playlists.forEachIndexed { index, playlist ->
                    val isSelected = selectedIndices.contains(index)
                    val epgSourceCount = playlist.settingsEpgInput().lineSequence().count { it.isNotBlank() }
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .background(if (isSelected) Pink.copy(alpha = 0.15f) else Color.Transparent)
                                .then(Modifier.combinedClickable(
                                    onClick = { if (selectionMode) { selectedIndices = if (isSelected) selectedIndices - index else selectedIndices + index; if (selectedIndices.isEmpty()) selectionMode = false } else onEditPlaylist(index) },
                                    onLongClick = { if (!selectionMode) { selectionMode = true; selectedIndices = setOf(index) } }
                                ))
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (selectionMode) {
                                Icon(imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked, contentDescription = null, tint = if (isSelected) Pink else TextSecondary, modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(16.dp))
                            } else {
                                Icon(imageVector = Icons.Default.LiveTv, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(playlist.name, style = ArflixTypography.cardTitle.copy(fontSize = 16.sp), color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                Text(buildString { append(playlist.m3uUrl.take(56)); when { epgSourceCount > 1 -> append(" • $epgSourceCount EPGs"); epgSourceCount == 1 -> append(" • EPG") } }, style = ArflixTypography.caption.copy(fontSize = 13.sp), color = TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                            if (selectionMode && selectedIndices.size == 1 && isSelected) {
                                Icon(imageVector = Icons.Default.DragHandle, contentDescription = stringResource(R.string.settings_cd_drag_reorder), tint = TextSecondary, modifier = Modifier.size(24.dp).pointerInput(index) {
                                    var dragOffset = 0f; val itemHeight = 64.dp.toPx()
                                    detectVerticalDragGestures(onDragEnd = { dragOffset = 0f }, onDragCancel = { dragOffset = 0f }) { change, dragAmount -> change.consume(); dragOffset += dragAmount; if (dragOffset > itemHeight) { onMovePlaylistDown(index); dragOffset -= itemHeight } else if (dragOffset < -itemHeight) { onMovePlaylistUp(index); dragOffset += itemHeight } }
                                })
                            } else if (!selectionMode) {
                                Icon(
                                    imageVector = Icons.Default.List,
                                    contentDescription = stringResource(R.string.settings_cd_manage_categories),
                                    tint = TextSecondary,
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clickable { onManageCategories(playlist.id) }
                                        .padding(6.dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                // Toggle chip
                                Box(modifier = Modifier.width(44.dp).height(24.dp).background(color = if (playlist.enabled) SuccessGreen else Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(13.dp)).clickable { onTogglePlaylist(index) }.padding(3.dp), contentAlignment = if (playlist.enabled) Alignment.CenterEnd else Alignment.CenterStart) {
                                    Box(modifier = Modifier.size(18.dp).background(color = Color.White, shape = RoundedCornerShape(10.dp)))
                                }
                            }
                        }
                        if (index < playlists.size - 1) {
                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).padding(horizontal = 16.dp).background(Color.White.copy(alpha = 0.05f)))
                        }
                    }
                }
            }
            MobileSettingsCategory(title = stringResource(R.string.settings_section_actions)) {
                val refreshSubtitle = when { isLoading -> stringResource(R.string.settings_refreshing_channels_epg); error != null -> error; playlists.none { it.epgUrl.isNotBlank() || it.epgUrls.orEmpty().isNotEmpty() } -> stringResource(R.string.settings_reload_playlists_now); else -> stringResource(R.string.settings_reload_playlist_epg_now) }
                MobileSettingsRow(icon = Icons.Default.Link, title = stringResource(R.string.refresh_iptv), subtitle = refreshSubtitle, value = if (isLoading) stringResource(R.string.loading_label) else "", isFocused = false, onClick = onRefresh)
                MobileSettingsRow(icon = Icons.Default.Delete, title = stringResource(R.string.delete_iptv), subtitle = if (playlists.isEmpty()) stringResource(R.string.settings_no_playlists_configured) else stringResource(R.string.settings_remove_playlists_epg), value = "", isFocused = false, showDivider = false, onClick = onDelete)
            }
            if (isLoading && !progressText.isNullOrBlank()) {
                Text(stringResource(R.string.settings_progress_format, progressText, progressPercent.coerceIn(0, 100)), style = ArflixTypography.caption, color = TextSecondary)
                Box(modifier = Modifier.fillMaxWidth().height(8.dp).background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(999.dp))) {
                    Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(progressPercent.coerceIn(0, 100) / 100f).background(Pink, RoundedCornerShape(999.dp)))
                }
            }
        }
    } else {
        // TV UI
        Column {
            SettingsRow(icon = Icons.Default.LiveTv, title = stringResource(R.string.add_playlist), subtitle = if (playlists.isEmpty()) stringResource(R.string.settings_add_iptv_lists_hint) else stringResource(R.string.settings_create_another_iptv), value = if (playlists.size >= 3) stringResource(R.string.settings_badge_full) else stringResource(R.string.settings_badge_add), isFocused = focusedIndex == 0, onClick = onConfigure, modifier = Modifier.settingsFocusSlot(0))
            Spacer(modifier = Modifier.height(16.dp))
            playlists.forEachIndexed { index, playlist ->
                val rowIndex = index + 1
                val epgSourceCount = playlist.settingsEpgInput().lineSequence().count { it.isNotBlank() }
                val focusRingColor = resolveAccentColor(fallback = Pink)
                Row(modifier = Modifier.settingsFocusSlot(rowIndex).fillMaxWidth().background(if (focusedIndex == rowIndex) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp)).border(width = if (focusedIndex == rowIndex) 2.dp else 0.dp, color = if (focusedIndex == rowIndex) focusRingColor else Color.Transparent, shape = RoundedCornerShape(12.dp)).clickable { onEditPlaylist(index) }.padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(playlist.name, style = ArflixTypography.cardTitle.copy(fontSize = 16.sp), color = if (focusedIndex == rowIndex) TextPrimary else TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(buildString { append(playlist.m3uUrl.take(56)); when { epgSourceCount > 1 -> append(" • $epgSourceCount EPGs"); epgSourceCount == 1 -> append(" • EPG") } }, style = ArflixTypography.caption.copy(fontSize = 13.sp), color = TextSecondary.copy(alpha = 0.72f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    CatalogActionChip(
                        icon = Icons.Default.List,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 0,
                        onClick = { onManageCategories(playlist.id) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = if (playlist.enabled) Icons.Default.Check else Icons.Default.VisibilityOff,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 1,
                        onClick = { onTogglePlaylist(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.Edit,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 2,
                        onClick = { onEditPlaylist(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.ArrowUpward,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 3,
                        onClick = { onMovePlaylistUp(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.ArrowDownward,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 4,
                        onClick = { onMovePlaylistDown(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.Delete,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 5,
                        isDestructive = true,
                        onClick = { onDeletePlaylist(index) }
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Spacer(modifier = Modifier.height(6.dp))
            val refreshSubtitle = when { isLoading -> stringResource(R.string.settings_refreshing_channels_epg); error != null -> error; playlists.none { it.epgUrl.isNotBlank() || it.epgUrls.orEmpty().isNotEmpty() } -> stringResource(R.string.settings_reload_playlists_now); else -> stringResource(R.string.settings_reload_playlist_epg_now) }
            SettingsRow(icon = Icons.Default.Link, title = stringResource(R.string.refresh_iptv), subtitle = refreshSubtitle, value = if (isLoading) stringResource(R.string.settings_badge_loading) else stringResource(R.string.settings_badge_refresh), isFocused = focusedIndex == playlists.size + 1, onClick = onRefresh, modifier = Modifier.settingsFocusSlot(playlists.size + 1))
            Spacer(modifier = Modifier.height(16.dp))
            SettingsRow(icon = Icons.Default.Delete, title = stringResource(R.string.delete_iptv), subtitle = if (playlists.isEmpty()) stringResource(R.string.settings_no_playlists_configured) else stringResource(R.string.settings_remove_playlists_epg), value = if (playlists.isEmpty()) stringResource(R.string.settings_badge_empty) else stringResource(R.string.settings_badge_delete), isFocused = focusedIndex == playlists.size + 2, onClick = onDelete, modifier = Modifier.settingsFocusSlot(playlists.size + 2))
            if (isLoading && !progressText.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(stringResource(R.string.settings_progress_format, progressText, progressPercent.coerceIn(0, 100)), style = ArflixTypography.caption, color = TextSecondary)
                Spacer(modifier = Modifier.height(6.dp))
                Box(modifier = Modifier.fillMaxWidth().height(8.dp).background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(999.dp))) { Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(progressPercent.coerceIn(0, 100) / 100f).background(Pink, RoundedCornerShape(999.dp))) }
            }
            if (!statusMessage.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                val statusColor = when (statusType) { ToastType.SUCCESS -> SuccessGreen; ToastType.ERROR -> Color(0xFFFF8A8A); ToastType.INFO -> TextSecondary }
                Box(modifier = Modifier.fillMaxWidth().background(statusColor.copy(alpha = 0.12f), RoundedCornerShape(10.dp)).border(1.dp, statusColor.copy(alpha = 0.35f), RoundedCornerShape(10.dp)).padding(horizontal = 14.dp, vertical = 10.dp)) { Text(statusMessage, style = ArflixTypography.caption, color = statusColor) }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CatalogDiscoveryModal(
    query: String,
    results: List<CatalogDiscoveryResult>,
    isSearching: Boolean,
    error: String?,
    manualUrl: String,
    addedCatalogUrls: Set<String>,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onAddResult: (CatalogDiscoveryResult) -> Unit,
    onManualUrlChange: (String) -> Unit,
    onManualAdd: () -> Unit,
    onDismiss: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val view = LocalView.current
    var editingInput by remember { mutableStateOf<CatalogDiscoveryInputTarget?>(null) }
    var optimisticAddedUrls by remember { mutableStateOf(emptySet<String>()) }
    val normalizedAddedCatalogUrls = remember(addedCatalogUrls) {
        addedCatalogUrls.map { normalizeCatalogDiscoveryUrl(it) }.toSet()
    }
    fun addResult(result: CatalogDiscoveryResult) {
        val normalizedUrl = normalizeCatalogDiscoveryUrl(result.sourceUrl)
        if (normalizedUrl in normalizedAddedCatalogUrls || normalizedUrl in optimisticAddedUrls) return
        optimisticAddedUrls = optimisticAddedUrls + normalizedUrl
        onAddResult(result)
    }
    fun submitSearch() {
        focusManager.clearFocus()
        (view.context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(view.windowToken, 0)
        onSearch()
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isCompact = maxWidth < 720.dp
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.86f))
                    .padding(
                        horizontal = if (isCompact) 10.dp else 48.dp,
                        vertical = if (isCompact) 10.dp else 34.dp
                    ),
                contentAlignment = Alignment.TopCenter
            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(if (isCompact) 12.dp else 18.dp))
                    .background(Color.Black.copy(alpha = 0.34f), RoundedCornerShape(if (isCompact) 12.dp else 18.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(if (isCompact) 12.dp else 18.dp))
                    .padding(if (isCompact) 10.dp else 18.dp)
            ) {
                if (isCompact) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.Black.copy(alpha = 0.32f))
                            .border(1.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(14.dp))
                            .padding(10.dp)
                    ) {
                        CatalogDiscoveryInputButton(
                            label = stringResource(R.string.settings_search_lists),
                            value = query,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = stringResource(R.string.settings_search_lists),
                            onClick = { editingInput = CatalogDiscoveryInputTarget.Search }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DiscoveryActionButton(
                                label = if (isSearching) stringResource(R.string.settings_searching) else stringResource(R.string.search),
                                onClick = { submitSearch() },
                                highlighted = true,
                                enabled = !isSearching && query.isNotBlank(),
                                modifier = Modifier.weight(1f)
                            )
                            DiscoveryActionButton(
                                label = stringResource(R.string.close),
                                onClick = onDismiss,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CatalogDiscoveryInputButton(
                            label = stringResource(R.string.settings_paste_url),
                            value = manualUrl,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = stringResource(R.string.settings_ph_trakt_mdblist),
                            onClick = { editingInput = CatalogDiscoveryInputTarget.ManualUrl }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        DiscoveryActionButton(
                            label = stringResource(R.string.settings_add_url),
                            onClick = onManualAdd,
                            enabled = manualUrl.isNotBlank(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Black.copy(alpha = 0.32f))
                            .border(1.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(16.dp))
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CatalogDiscoveryInputButton(
                            label = stringResource(R.string.settings_search_lists),
                            value = query,
                            modifier = Modifier.weight(1f),
                            placeholder = stringResource(R.string.settings_search_lists),
                            onClick = { editingInput = CatalogDiscoveryInputTarget.Search }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        DiscoveryActionButton(
                            label = if (isSearching) stringResource(R.string.settings_searching) else stringResource(R.string.search),
                            onClick = { submitSearch() },
                            highlighted = true,
                            enabled = !isSearching && query.isNotBlank()
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        CatalogDiscoveryInputButton(
                            label = stringResource(R.string.settings_paste_url),
                            value = manualUrl,
                            modifier = Modifier.weight(1f),
                            placeholder = stringResource(R.string.settings_ph_trakt_mdblist),
                            onClick = { editingInput = CatalogDiscoveryInputTarget.ManualUrl }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        DiscoveryActionButton(
                            label = stringResource(R.string.settings_add_url),
                            onClick = onManualAdd,
                            enabled = manualUrl.isNotBlank()
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        DiscoveryActionButton(
                            label = stringResource(R.string.close),
                            onClick = onDismiss
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (isCompact) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = when {
                                isSearching -> stringResource(R.string.settings_searching_both_sources)
                                results.isNotEmpty() -> stringResource(R.string.settings_lists_found, results.size)
                                else -> stringResource(R.string.settings_searches_trakt_mdblist)
                            },
                            style = ArflixTypography.caption,
                            color = TextSecondary
                        )
                        Text(
                            text = stringResource(R.string.settings_tap_field_to_edit),
                            style = ArflixTypography.caption,
                            color = TextSecondary.copy(alpha = 0.75f)
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                    Text(
                        text = when {
                            isSearching -> stringResource(R.string.settings_searching_both_sources)
                            results.isNotEmpty() -> stringResource(R.string.settings_lists_found, results.size)
                            else -> stringResource(R.string.settings_searches_trakt_mdblist)
                        },
                        style = ArflixTypography.caption,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.settings_press_select_to_edit),
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.75f)
                    )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Black.copy(alpha = 0.20f))
                        .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                        .padding(if (isCompact) 8.dp else 12.dp)
                ) {
                    when {
                        isSearching -> {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                LoadingIndicator(size = 30.dp)
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(stringResource(R.string.settings_searching_lists), color = TextSecondary, style = ArflixTypography.body)
                            }
                        }
                        error != null -> {
                            Text(
                                text = error,
                                color = TextSecondary,
                                style = ArflixTypography.body,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        results.isEmpty() -> {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.settings_start_with_search),
                                    color = TextPrimary,
                                    style = ArflixTypography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = stringResource(R.string.settings_search_examples),
                                    color = TextSecondary,
                                    style = ArflixTypography.body,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 28.dp)
                                )
                            }
                        }
                        else -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                itemsIndexed(results, key = { _, item -> item.id }) { _, item ->
                                    val isAdded = normalizeCatalogDiscoveryUrl(item.sourceUrl) in normalizedAddedCatalogUrls ||
                                        normalizeCatalogDiscoveryUrl(item.sourceUrl) in optimisticAddedUrls
                                    CatalogDiscoveryResultRow(
                                        result = item,
                                        isAdded = isAdded,
                                        onAdd = { addResult(item) },
                                        compact = isCompact
                                    )
                                }
                            }
                        }
                    }
                }
            }
            }
        }
    }

    editingInput?.let { target ->
        CatalogDiscoveryTextInputDialog(
            title = if (target == CatalogDiscoveryInputTarget.Search) stringResource(R.string.settings_search_lists) else stringResource(R.string.settings_paste_catalog_url),
            initialValue = if (target == CatalogDiscoveryInputTarget.Search) query else manualUrl,
            placeholder = if (target == CatalogDiscoveryInputTarget.Search) stringResource(R.string.settings_search_lists) else "https://trakt.tv/users/...",
            confirmLabel = if (target == CatalogDiscoveryInputTarget.Search) stringResource(R.string.settings_use_search) else stringResource(R.string.settings_use_url),
            onConfirm = { value ->
                if (target == CatalogDiscoveryInputTarget.Search) {
                    onQueryChange(value)
                } else {
                    onManualUrlChange(value)
                }
                editingInput = null
            },
            onDismiss = { editingInput = null }
        )
    }
}

private enum class CatalogDiscoveryInputTarget {
    Search,
    ManualUrl
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CatalogDiscoveryInputButton(
    label: String,
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val inputFocusColor = resolveAccentColor(fallback = Color.White)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isFocused) Color.White else Color.Black.copy(alpha = 0.36f))
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) inputFocusColor else Color.White.copy(alpha = 0.55f),
                shape = RoundedCornerShape(12.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(
            text = label,
            style = ArflixTypography.caption,
            color = if (isFocused) Color.Black.copy(alpha = 0.68f) else TextSecondary,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value.ifBlank { placeholder },
            style = ArflixTypography.body,
            color = when {
                isFocused -> Color.Black
                value.isBlank() -> TextSecondary.copy(alpha = 0.7f)
                else -> TextPrimary
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CatalogDiscoveryTextInputDialog(
    title: String,
    initialValue: String,
    placeholder: String,
    confirmLabel: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var value by remember(initialValue) { mutableStateOf(initialValue) }
    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val isCompact = maxWidth < 720.dp
            Column(
                modifier = Modifier
                    .widthIn(max = 680.dp)
                    .fillMaxWidth(if (isCompact) 0.92f else 0.76f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF181818), RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(16.dp))
                    .padding(if (isCompact) 16.dp else 20.dp)
            ) {
            Text(title, style = ArflixTypography.sectionTitle, color = TextPrimary)
            Spacer(modifier = Modifier.height(14.dp))
            androidx.compose.material3.TextField(
                value = value,
                onValueChange = { value = it },
                placeholder = { androidx.compose.material3.Text(placeholder) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.TextFieldDefaults.colors(
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    focusedContainerColor = Color.White.copy(alpha = 0.08f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.06f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.18f),
                    focusedPlaceholderColor = TextSecondary,
                    unfocusedPlaceholderColor = TextSecondary
                )
            )
            Spacer(modifier = Modifier.height(18.dp))
            if (isCompact) {
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    DiscoveryActionButton(
                        label = confirmLabel,
                        onClick = { onConfirm(value.trim()) },
                        highlighted = true,
                        enabled = value.isNotBlank(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    DiscoveryActionButton(
                        label = stringResource(R.string.cancel),
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    DiscoveryActionButton(label = stringResource(R.string.cancel), onClick = onDismiss)
                    Spacer(modifier = Modifier.width(10.dp))
                    DiscoveryActionButton(
                        label = confirmLabel,
                        onClick = { onConfirm(value.trim()) },
                        highlighted = true,
                        enabled = value.isNotBlank()
                    )
                }
            }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CatalogDiscoveryResultRow(
    result: CatalogDiscoveryResult,
    isAdded: Boolean,
    onAdd: () -> Unit,
    compact: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }
    val compactFocusColor = resolveAccentColor(fallback = Color.White)
    val creator = result.creatorName ?: result.creatorHandle
    val creatorMeta = creator?.let { stringResource(R.string.settings_by, it) }
    val itemCountMeta = result.itemCount?.let { stringResource(R.string.settings_items_count, it.toString()) }
    val likesMeta = result.likes?.let { stringResource(R.string.settings_likes_count, it.toString()) } ?: stringResource(R.string.settings_likes_count, "0")
    val updatedMeta = result.updatedAt?.let { stringResource(R.string.settings_updated_meta, formatCatalogDiscoveryDate(it)) }

    if (compact) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .onFocusChanged { isFocused = it.isFocused || it.hasFocus }
                .clickable(onClick = { if (!isAdded) onAdd() })
                .background(
                    if (isFocused) Color.White.copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.38f),
                    RoundedCornerShape(14.dp)
                )
                .border(
                    width = if (isFocused) 3.dp else 1.dp,
                    color = if (isFocused) compactFocusColor else Color.White.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val posters = result.previewPosterUrls.take(5)
                if (posters.isEmpty()) {
                    repeat(5) {
                        Box(
                            modifier = Modifier
                                .size(width = 52.dp, height = 78.dp)
                                .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(7.dp))
                        )
                    }
                } else {
                    posters.forEach { posterUrl ->
                        AsyncImage(
                            model = posterUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(width = 52.dp, height = 78.dp)
                                .clip(RoundedCornerShape(7.dp))
                                .background(Color.White.copy(alpha = 0.08f))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = result.title,
                style = ArflixTypography.bodyLarge,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                SourceChip(label = sourceLabel(result.sourceType), active = true, compact = true)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = listOfNotNull(itemCountMeta, likesMeta).joinToString("  |  "),
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }

            if (!creatorMeta.isNullOrBlank()) {
                Text(
                    text = creatorMeta,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            if (!updatedMeta.isNullOrBlank()) {
                Text(
                    text = updatedMeta,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            if (!result.description.isNullOrBlank()) {
                Text(
                    text = result.description,
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.82f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            CatalogDiscoveryAddPill(
                isAdded = isAdded,
                modifier = Modifier.fillMaxWidth()
            )
        }
        return
    }

    val resultFocusColor = resolveAccentColor(fallback = Color.White)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .onFocusChanged { isFocused = it.isFocused || it.hasFocus }
            .clickable(onClick = { if (!isAdded) onAdd() })
            .background(
                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.38f),
                RoundedCornerShape(14.dp)
            )
            .border(
                width = if (isFocused) 3.dp else 1.dp,
                color = if (isFocused) resultFocusColor else Color.White.copy(alpha = 0.08f),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .width(330.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            val posters = result.previewPosterUrls.take(5)
            if (posters.isEmpty()) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(width = 60.dp, height = 90.dp)
                            .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(7.dp))
                    )
                }
            } else {
                posters.forEach { posterUrl ->
                    AsyncImage(
                        model = posterUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 60.dp, height = 90.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(Color.White.copy(alpha = 0.08f))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(18.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = result.title,
                style = ArflixTypography.bodyLarge,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SourceChip(label = sourceLabel(result.sourceType), active = true, compact = true)
                if (!creatorMeta.isNullOrBlank()) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = creatorMeta,
                        style = ArflixTypography.caption,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (!itemCountMeta.isNullOrBlank()) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "|",
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.65f),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = itemCountMeta,
                        style = ArflixTypography.caption,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 112.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "|",
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.65f),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = likesMeta,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(min = 64.dp, max = 86.dp)
                )
            }

            if (!updatedMeta.isNullOrBlank()) {
                Text(
                    text = updatedMeta,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            if (!result.description.isNullOrBlank()) {
                Text(
                    text = result.description,
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.82f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(18.dp))
        CatalogDiscoveryAddPill(isAdded = isAdded)
    }
}

@Composable
private fun CatalogDiscoveryAddPill(
    isAdded: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .widthIn(min = 112.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Black.copy(alpha = 0.46f), RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.74f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 22.dp, vertical = 13.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isAdded) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = TextPrimary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = if (isAdded) stringResource(R.string.settings_added) else stringResource(R.string.add),
                style = ArflixTypography.button,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SourceChip(
    label: String,
    active: Boolean,
    compact: Boolean = false
) {
    Text(
        text = label,
        style = if (compact) ArflixTypography.label else ArflixTypography.body,
        color = if (active) TextPrimary else TextSecondary,
        modifier = Modifier
            .background(
                if (active) Color.Black.copy(alpha = 0.30f) else Color.White.copy(alpha = 0.06f),
                RoundedCornerShape(999.dp)
            )
            .border(
                width = 1.dp,
                color = if (active) Color.White.copy(alpha = 0.48f) else Color.White.copy(alpha = 0.12f),
                shape = RoundedCornerShape(999.dp)
            )
            .padding(horizontal = if (compact) 8.dp else 12.dp, vertical = if (compact) 3.dp else 5.dp)
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun DiscoveryActionButton(
    label: String,
    onClick: () -> Unit,
    icon: ImageVector? = null,
    highlighted: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .widthIn(min = if (label.length <= 5) 112.dp else 132.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                when {
                    !enabled -> Color.Black.copy(alpha = 0.18f)
                    isFocused -> Color.White
                    highlighted -> Color.Black.copy(alpha = 0.46f)
                    else -> Color.Black.copy(alpha = 0.36f)
                },
                RoundedCornerShape(10.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = when {
                    !enabled -> Color.White.copy(alpha = 0.18f)
                    isFocused -> Color.White
                    highlighted -> Color.White.copy(alpha = 0.74f)
                    else -> Color.White.copy(alpha = 0.55f)
                },
                shape = RoundedCornerShape(10.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 13.dp),
        contentAlignment = Alignment.Center
    ) {
        val contentColor = when {
            !enabled -> TextSecondary.copy(alpha = 0.72f)
            isFocused -> Color.Black
            else -> TextPrimary
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = label,
                style = ArflixTypography.button,
                color = contentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun normalizeCatalogDiscoveryUrl(url: String): String {
    return url.trim().trimEnd('/').lowercase()
}

@Composable
private fun sourceLabel(sourceType: CatalogSourceType): String {
    return when (sourceType) {
        CatalogSourceType.TRAKT -> "Trakt"
        CatalogSourceType.MDBLIST -> "MDBList"
        CatalogSourceType.PREINSTALLED -> stringResource(R.string.settings_source_builtin)
        CatalogSourceType.ADDON -> stringResource(R.string.settings_source_addon)
        CatalogSourceType.HOME_SERVER -> stringResource(R.string.settings_home_server)
    }
}

private fun formatCatalogDiscoveryDate(raw: String): String {
    return raw.substringBefore('T')
        .takeIf { it.length == 10 }
        ?: raw.replace('T', ' ').substringBefore('.').take(16)
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun CatalogsSettings(
    catalogs: List<CatalogConfig>,
    focusedIndex: Int,
    focusedActionIndex: Int,
    onAddCatalog: () -> Unit,
    onRenameCatalog: (CatalogConfig) -> Unit,
    onMoveCatalogUp: (CatalogConfig) -> Unit,
    onMoveCatalogDown: (CatalogConfig) -> Unit,
    onDeleteCatalog: (CatalogConfig) -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    var selectionMode by remember { mutableStateOf(false) }
    var selectedIds by remember { mutableStateOf(setOf<String>()) }

    if (isMobile) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            if (selectionMode) {
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.settings_n_selected, selectedIds.size), style = ArflixTypography.sectionTitle, color = TextPrimary)
                    Spacer(modifier = Modifier.weight(1f))
                    if (selectedIds.isNotEmpty()) {
                        Box(modifier = Modifier.size(36.dp).clickable { selectedIds.forEach { id -> val cat = catalogs.find { it.id == id }; if (cat != null) onDeleteCatalog(cat) }; selectionMode = false; selectedIds = emptySet() }.background(Color(0xFFDC2626), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete), tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Box(modifier = Modifier.size(36.dp).clickable { selectionMode = false; selectedIds = emptySet() }.background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close), tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                }
            }
            MobileSettingsCategory(title = stringResource(R.string.settings_section_add_catalog)) {
                MobileSettingsRow(icon = Icons.Default.Add, title = stringResource(R.string.add_catalog), subtitle = stringResource(R.string.add_catalog_desc), value = "", isFocused = false, showDivider = false, onClick = onAddCatalog)
            }
            if (catalogs.isNotEmpty()) {
                MobileSettingsCategory(title = stringResource(R.string.settings_section_my_catalogs)) {
                    catalogs.forEachIndexed { index, catalog ->
                        val title = if (catalog.isPreinstalled) { when (catalog.kind) { CatalogKind.COLLECTION -> stringResource(R.string.settings_title_builtin_collection, catalog.title); CatalogKind.COLLECTION_RAIL -> stringResource(R.string.settings_title_builtin_rail, catalog.title); else -> stringResource(R.string.settings_title_builtin, catalog.title) } } else catalog.title
                        val collectionFallback = stringResource(R.string.settings_collection_fallback)
                        val addonFallback = stringResource(R.string.settings_source_addon)
                        val subtitle = when { catalog.kind == CatalogKind.COLLECTION_RAIL -> { val group = catalog.collectionGroup?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: collectionFallback; stringResource(R.string.settings_group_rail, group) }; catalog.kind == CatalogKind.COLLECTION -> { val group = catalog.collectionGroup?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: collectionFallback; stringResource(R.string.settings_group_collection, group) }; catalog.sourceType == CatalogSourceType.PREINSTALLED -> stringResource(R.string.settings_preinstalled_catalog); else -> when (catalog.sourceType) { CatalogSourceType.ADDON -> { val addonLabel = catalog.addonName?.takeIf { it.isNotBlank() } ?: addonFallback; stringResource(R.string.settings_from_source, addonLabel) }; CatalogSourceType.HOME_SERVER -> stringResource(R.string.settings_from_home_server); else -> catalog.sourceUrl ?: stringResource(R.string.settings_custom_catalog) } }
                        val isSelected = selectedIds.contains(catalog.id)
                        val layoutToggleEnabled = catalog.kind != CatalogKind.COLLECTION_RAIL
                        val layoutRowKey = remember(catalog.id, catalog.kind) { catalogueLayoutRowKey(catalog) }
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .background(if (isSelected) Pink.copy(alpha = 0.15f) else Color.Transparent)
                                    .combinedClickable(
                                        onClick = { if (selectionMode) { selectedIds = if (isSelected) selectedIds - catalog.id else selectedIds + catalog.id; if (selectedIds.isEmpty()) selectionMode = false } else onRenameCatalog(catalog) },
                                        onLongClick = { if (!selectionMode) { selectionMode = true; selectedIds = setOf(catalog.id) } }
                                    )
                                    .padding(horizontal = 16.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (selectionMode) {
                                    Icon(if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked, contentDescription = null, tint = if (isSelected) Pink else TextSecondary, modifier = Modifier.size(24.dp))
                                    Spacer(modifier = Modifier.width(16.dp))
                                } else {
                                    Icon(Icons.Default.Widgets, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(24.dp))
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(title, style = ArflixTypography.cardTitle.copy(fontSize = 16.sp), color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    Text(subtitle, style = ArflixTypography.caption.copy(fontSize = 13.sp), color = TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                }
                                if (!selectionMode) {
                                    CatalogueRowLayoutToggleButton(rowKey = layoutRowKey, enabled = layoutToggleEnabled)
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                                if (selectionMode && selectedIds.size == 1 && isSelected) {
                                    Icon(imageVector = Icons.Default.DragHandle, contentDescription = stringResource(R.string.settings_cd_drag_reorder), tint = TextSecondary, modifier = Modifier.size(24.dp).pointerInput(catalog.id) {
                                        var dragOffset = 0f; val itemHeight = 64.dp.toPx()
                                        detectVerticalDragGestures(onDragEnd = { dragOffset = 0f }, onDragCancel = { dragOffset = 0f }) { change, dragAmount -> change.consume(); dragOffset += dragAmount; if (dragOffset > itemHeight) { onMoveCatalogDown(catalog); dragOffset -= itemHeight } else if (dragOffset < -itemHeight) { onMoveCatalogUp(catalog); dragOffset += itemHeight } }
                                    })
                                }
                            }
                            if (index < catalogs.size - 1) {
                                Box(modifier = Modifier.fillMaxWidth().height(1.dp).padding(horizontal = 16.dp).background(Color.White.copy(alpha = 0.05f)))
                            }
                        }
                    }
                }
            }
        }
    } else {
        // TV UI
        Column {
            if (selectionMode) {
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.settings_n_selected, selectedIds.size), style = ArflixTypography.sectionTitle, color = TextPrimary)
                    Spacer(modifier = Modifier.weight(1f))
                    if (selectedIds.isNotEmpty()) { Box(modifier = Modifier.size(36.dp).clickable { selectedIds.forEach { id -> val cat = catalogs.find { it.id == id }; if (cat != null) onDeleteCatalog(cat) }; selectionMode = false; selectedIds = emptySet() }.background(Color(0xFFDC2626), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) { Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete), tint = Color.White, modifier = Modifier.size(20.dp)) }; Spacer(modifier = Modifier.width(12.dp)) }
                    Box(modifier = Modifier.size(36.dp).clickable { selectionMode = false; selectedIds = emptySet() }.background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) { Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close), tint = Color.White, modifier = Modifier.size(20.dp)) }
                }
            }
            Text(text = stringResource(R.string.catalogs), style = ArflixTypography.caption, color = TextSecondary.copy(alpha = 0.65f), modifier = Modifier.padding(bottom = 20.dp))
            SettingsRow(icon = Icons.Default.Add, title = stringResource(R.string.add_catalog), subtitle = stringResource(R.string.add_catalog_desc), value = stringResource(R.string.settings_badge_add), isFocused = focusedIndex == 0, onClick = onAddCatalog, modifier = Modifier.settingsFocusSlot(0))
            Spacer(modifier = Modifier.height(16.dp))
            catalogs.forEachIndexed { index, catalog ->
                val rowFocusIndex = index + 1; val isRowFocused = focusedIndex == rowFocusIndex
                val title = if (catalog.isPreinstalled) { when (catalog.kind) { CatalogKind.COLLECTION -> stringResource(R.string.settings_title_builtin_collection, catalog.title); CatalogKind.COLLECTION_RAIL -> stringResource(R.string.settings_title_builtin_rail, catalog.title); else -> stringResource(R.string.settings_title_builtin, catalog.title) } } else catalog.title
                val collectionFallback = stringResource(R.string.settings_collection_fallback)
                val addonFallback = stringResource(R.string.settings_source_addon)
                val subtitle = when { catalog.kind == CatalogKind.COLLECTION_RAIL -> { val group = catalog.collectionGroup?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: collectionFallback; stringResource(R.string.settings_group_rail, group) }; catalog.kind == CatalogKind.COLLECTION -> { val group = catalog.collectionGroup?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: collectionFallback; stringResource(R.string.settings_group_collection, group) }; catalog.sourceType == CatalogSourceType.PREINSTALLED -> stringResource(R.string.settings_preinstalled_catalog); else -> when (catalog.sourceType) { CatalogSourceType.ADDON -> { val addonLabel = catalog.addonName?.takeIf { it.isNotBlank() } ?: addonFallback; stringResource(R.string.settings_from_source, addonLabel) }; CatalogSourceType.HOME_SERVER -> stringResource(R.string.settings_from_home_server); else -> catalog.sourceUrl ?: stringResource(R.string.settings_custom_catalog) } }
                val isSelected = selectedIds.contains(catalog.id)
                val layoutToggleEnabled = catalog.kind != CatalogKind.COLLECTION_RAIL
                val layoutRowKey = remember(catalog.id, catalog.kind) { catalogueLayoutRowKey(catalog) }
                val focusRingColor = resolveAccentColor(fallback = Pink)
                Row(modifier = Modifier.settingsFocusSlot(rowFocusIndex).fillMaxWidth().background(if (isSelected) Pink.copy(alpha = 0.2f) else if (isRowFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp)).border(width = if (isRowFocused) 2.dp else 0.dp, color = if (isRowFocused) focusRingColor else Color.Transparent, shape = RoundedCornerShape(12.dp)).clickable { onRenameCatalog(catalog) }.padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(title, style = ArflixTypography.cardTitle.copy(fontSize = 16.sp), color = if (isRowFocused || isSelected) TextPrimary else TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(subtitle, style = ArflixTypography.caption.copy(fontSize = 13.sp), color = TextSecondary.copy(alpha = 0.7f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    CatalogActionChip(icon = Icons.Default.Edit, isFocused = isRowFocused && focusedActionIndex == 0, onClick = { onRenameCatalog(catalog) })
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(icon = Icons.Default.ArrowUpward, isFocused = isRowFocused && focusedActionIndex == 1, onClick = { onMoveCatalogUp(catalog) })
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(icon = Icons.Default.ArrowDownward, isFocused = isRowFocused && focusedActionIndex == 2, onClick = { onMoveCatalogDown(catalog) })
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogueRowLayoutToggleButton(rowKey = layoutRowKey, enabled = layoutToggleEnabled, forceFocused = isRowFocused && focusedActionIndex == 3)
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(icon = Icons.Default.Delete, isFocused = isRowFocused && focusedActionIndex == 4, isDestructive = true, enabled = true, onClick = { onDeleteCatalog(catalog) })
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


private fun catalogueLayoutRowKey(catalog: CatalogConfig): String {
    return if (catalog.kind == CatalogKind.COLLECTION) {
        "collection:${catalog.id}"
    } else {
        "home:${catalog.id}"
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CatalogActionChip(
    icon: ImageVector,
    isFocused: Boolean,
    isDestructive: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    // Support both D-pad focus AND touch pressed state
    val accent = resolveAccentColor(fallback = Color.White)
    var isPressed by remember { mutableStateOf(false) }
    val visualActive = isFocused || isPressed
    val bgColor = when {
        !enabled -> Color.Black.copy(alpha = 0.4f)
        visualActive && isDestructive -> Color(0xFFDC2626)
        visualActive -> accent
        else -> Color.White.copy(alpha = 0.08f)
    }
    // Choose foreground (icon) color based on accent luminance for contrast
    val accentFg = if (visualActive) {
        val l = 0.299f * accent.red + 0.587f * accent.green + 0.114f * accent.blue
        if (l > 0.5f) Color.Black else Color.White
    } else Color.White
    val fgColor = when {
        !enabled -> Color.White.copy(alpha = 0.5f)
        visualActive && isDestructive -> Color.White
        visualActive -> accentFg
        else -> Color.White.copy(alpha = 0.7f)
    }
    Box(
        modifier = Modifier
            .size(36.dp)
            .onPreviewKeyEvent { event ->
                if (!enabled || !isFocused || event.type != KeyEventType.KeyDown) {
                    return@onPreviewKeyEvent false
                }
                when (event.key) {
                    Key.Enter, Key.DirectionCenter -> {
                        onClick()
                        true
                    }
                    else -> false
                }
            }
            .clickable(enabled = enabled, onClick = onClick)
            .background(bgColor, RoundedCornerShape(8.dp))
            .border(
                width = if (visualActive) 1.5.dp else 1.dp,
                color = if (visualActive) accent else Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = fgColor,
            modifier = Modifier.size(16.dp)
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun StremioAddonsSettings(
    addons: List<com.arflix.tv.data.model.Addon> = emptyList(),
    focusedIndex: Int = -1,
    focusedActionIndex: Int = 0,
    onToggleAddon: (String) -> Unit = {},
    onMoveAddonUp: (String) -> Unit = {},
    onMoveAddonDown: (String) -> Unit = {},
    onDeleteAddon: (String) -> Unit = {},
    onAddCustomAddon: () -> Unit = {}
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()

    if (isMobile) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            MobileSettingsCategory(title = stringResource(R.string.settings_section_add_addon)) {
                MobileSettingsRow(icon = Icons.Default.Add, title = stringResource(R.string.add_addon), subtitle = stringResource(R.string.settings_install_custom_addon), value = "", isFocused = false, showDivider = false, onClick = onAddCustomAddon)
            }
            MobileSettingsCategory(title = stringResource(R.string.settings_section_my_addons)) {
                if (addons.isEmpty()) {
                    MobileSettingsRow(icon = Icons.Default.Extension, title = stringResource(R.string.settings_no_addons_installed), value = "", isFocused = false, showDivider = false, onClick = {})
                } else {
                    addons.forEachIndexed { index, addon ->
                        val canDelete = !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
                        val canMoveUp = index > 0
                        val canMoveDown = index < addons.lastIndex
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable { onToggleAddon(addon.id) }.padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Extension, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(addon.name, style = ArflixTypography.cardTitle.copy(fontSize = 16.sp), color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                Text(addon.description, style = ArflixTypography.caption.copy(fontSize = 13.sp), color = TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            // Toggle switch
                            Box(modifier = Modifier.width(44.dp).height(24.dp).background(color = if (addon.isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(13.dp)).padding(3.dp), contentAlignment = if (addon.isEnabled) Alignment.CenterEnd else Alignment.CenterStart) {
                                Box(modifier = Modifier.size(18.dp).background(color = Color.White, shape = RoundedCornerShape(10.dp)))
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable(enabled = canMoveUp) { onMoveAddonUp(addon.id) }
                                    .background(Color.White.copy(alpha = if (canMoveUp) 0.1f else 0.04f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.ArrowUpward,
                                    contentDescription = stringResource(R.string.settings_cd_move_addon_up),
                                    tint = TextSecondary.copy(alpha = if (canMoveUp) 1f else 0.35f),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable(enabled = canMoveDown) { onMoveAddonDown(addon.id) }
                                    .background(Color.White.copy(alpha = if (canMoveDown) 0.1f else 0.04f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.ArrowDownward,
                                    contentDescription = stringResource(R.string.settings_cd_move_addon_down),
                                    tint = TextSecondary.copy(alpha = if (canMoveDown) 1f else 0.35f),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            if (canDelete) {
                                Spacer(modifier = Modifier.width(12.dp))
                                Box(modifier = Modifier.size(32.dp).clickable { onDeleteAddon(addon.id) }.background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.settings_cd_delete_addon), tint = TextSecondary, modifier = Modifier.size(18.dp))
                                }
                            }
                        }
                        if (index < addons.size - 1) {
                            Box(modifier = Modifier.fillMaxWidth().height(1.dp).padding(horizontal = 16.dp).background(Color.White.copy(alpha = 0.05f)))
                        }
                    }
                }
            }
        }
    } else {
        // TV UI
        Column {
            Text(stringResource(R.string.settings_section_stremio_addons), style = ArflixTypography.caption.copy(fontSize = 12.sp, letterSpacing = 1.sp), color = TextSecondary, modifier = Modifier.padding(bottom = 10.dp))
            if (addons.isEmpty()) {
                Text(stringResource(R.string.settings_no_addons_installed), style = ArflixTypography.body, color = TextSecondary)
            } else {
                addons.forEachIndexed { index, addon ->
                    val canDelete = !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
                    AddonRow(
                        addon = addon,
                        isFocused = focusedIndex == index,
                        focusedAction = if (focusedIndex == index) focusedActionIndex else -1,
                        canDelete = canDelete,
                        onToggle = { onToggleAddon(addon.id) },
                        onMoveUp = { onMoveAddonUp(addon.id) },
                        onMoveDown = { onMoveAddonDown(addon.id) },
                        onDelete = { onDeleteAddon(addon.id) },
                        modifier = Modifier.settingsFocusSlot(index)
                    )
                    if (index < addons.size - 1) { Spacer(modifier = Modifier.height(12.dp)) }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.settingsFocusSlot(addons.size).fillMaxWidth().clickable(onClick = onAddCustomAddon).background(if (focusedIndex == addons.size) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp)).border(width = if (focusedIndex == addons.size) 2.dp else 0.dp, color = if (focusedIndex == addons.size) Pink else Color.Transparent, shape = RoundedCornerShape(12.dp)).padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Icon(Icons.Default.Widgets, contentDescription = null, tint = Pink, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(stringResource(R.string.add_addon), style = ArflixTypography.button, color = Pink)
            }
        }
    }
}

@Composable
private fun AddonStatusChip(
    text: String,
    background: Color,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .background(background, RoundedCornerShape(999.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(fontSize = 11.sp),
            color = textColor,
            maxLines = 1
        )
    }
}

@Composable
private fun AddonRow(
    addon: com.arflix.tv.data.model.Addon,
    isFocused: Boolean,
    focusedAction: Int = -1,
    canDelete: Boolean = true,
    onToggle: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isToggleFocused = isFocused && focusedAction == 0
    val isMoveUpFocused = isFocused && focusedAction == 1
    val isMoveDownFocused = isFocused && focusedAction == 2
    val isDeleteFocused = canDelete && isFocused && focusedAction == 3
    val isEnabled = addon.isEnabled
    val focusRingColor = resolveAccentColor(fallback = Pink)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Pink.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Widgets,
                    contentDescription = null,
                    tint = Pink,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = addon.name,
                    style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = addon.description,
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AddonStatusChip(
                        text = stringResource(R.string.settings_status_installed),
                        background = Color(0xFF2563EB).copy(alpha = 0.18f),
                        textColor = Color(0xFF93C5FD)
                    )
                    AddonStatusChip(
                        text = if (isEnabled) stringResource(R.string.settings_enabled) else stringResource(R.string.settings_disabled),
                        background = if (isEnabled) SuccessGreen.copy(alpha = 0.18f) else Color.White.copy(alpha = 0.08f),
                        textColor = if (isEnabled) SuccessGreen else TextSecondary
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = if (isToggleFocused) 2.dp else 0.dp,
                        color = if (isToggleFocused) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(13.dp)
                    )
                    .padding(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(24.dp)
                        .background(
                            color = if (isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(13.dp)
                        )
                        .padding(3.dp),
                    contentAlignment = if (isEnabled) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                }
            }

            CatalogActionChip(
                icon = Icons.Default.ArrowUpward,
                isFocused = isMoveUpFocused,
                onClick = onMoveUp
            )

            CatalogActionChip(
                icon = Icons.Default.ArrowDownward,
                isFocused = isMoveDownFocused,
                onClick = onMoveDown
            )

            if (canDelete) {
                CatalogActionChip(
                    icon = Icons.Default.Delete,
                    isFocused = isDeleteFocused,
                    isDestructive = true,
                    onClick = onDelete
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AccountsSettings(
    isCloudAuthenticated: Boolean,
    cloudEmail: String?,
    cloudHint: String?,
    isTraktAuthenticated: Boolean,
    traktCode: String?,
    traktUrl: String?,
    isTraktAuthStarting: Boolean,
    isTraktPolling: Boolean,
    isForceCloudSyncing: Boolean,
    lastCloudSyncStatus: String?,
    isSelfUpdateSupported: Boolean,
    updateStatus: com.arflix.tv.updater.UpdateStatus,
    focusedIndex: Int,
    onConnectCloud: () -> Unit,
    onDisconnectCloud: () -> Unit,
    onConnectTrakt: () -> Unit,
    onCancelTrakt: () -> Unit,
    onDisconnectTrakt: () -> Unit,
    onForceCloudSync: () -> Unit,
    onSwitchProfile: () -> Unit,
    onCheckUpdates: () -> Unit,
    onInstallUpdate: () -> Unit,
    onOpenDataDeletion: () -> Unit,
    onNavigateToTelegram: () -> Unit = {}
) {
    Column {
        if (LocalDeviceType.current.isTouchDevice()) {
            Text(
                text = stringResource(R.string.accounts),
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        AccountRow(
            name = "ARVIO Cloud",
            description = cloudEmail ?: stringResource(R.string.settings_cloud_account_desc),
            isConnected = isCloudAuthenticated,
            isWorking = false,
            authCode = null,
            authUrl = null,
            isFocused = focusedIndex == 0,
            onConnect = {
                onConnectCloud()
            },
            onDisconnect = onDisconnectCloud,
            modifier = Modifier.settingsFocusSlot(0),
            expirationText = cloudHint
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Trakt.tv
        AccountRow(
            name = "Trakt.tv",
            description = stringResource(R.string.settings_trakt_desc),
            isConnected = isTraktAuthenticated,
            isWorking = isTraktAuthStarting || isTraktPolling,
            authCode = traktCode,
            authUrl = traktUrl,
            isFocused = focusedIndex == 1,
            onConnect = { if (isTraktPolling) onCancelTrakt() else onConnectTrakt() },
            onDisconnect = onDisconnectTrakt,
            modifier = Modifier.settingsFocusSlot(1),
            expirationText = null  // Don't show expiration - Trakt tokens auto-refresh
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Telegram
        SettingsActionRow(
            title = "Telegram",
            description = stringResource(R.string.settings_telegram_desc),
            actionLabel = stringResource(R.string.settings_badge_open),
            isFocused = focusedIndex == 2,
            onClick = onNavigateToTelegram,
            modifier = Modifier.settingsFocusSlot(2)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = stringResource(R.string.force_cloud_sync),
            description = if (isForceCloudSyncing) {
                stringResource(R.string.settings_sync_local_cloud_now)
            } else if (!lastCloudSyncStatus.isNullOrBlank()) {
                lastCloudSyncStatus
            } else if (isCloudAuthenticated) {
                stringResource(R.string.settings_upload_restore_now)
            } else {
                stringResource(R.string.settings_signin_to_force_sync)
            },
            actionLabel = if (isForceCloudSyncing) stringResource(R.string.settings_badge_syncing) else stringResource(R.string.settings_badge_sync),
            isFocused = focusedIndex == 3,
            onClick = { if (!isForceCloudSyncing) onForceCloudSync() },
            modifier = Modifier.settingsFocusSlot(3)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = stringResource(R.string.app_update),
            description = when {
                !isSelfUpdateSupported -> stringResource(R.string.settings_update_managed_play)
                updateStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall -> stringResource(R.string.settings_update_ready_install)
                updateStatus is com.arflix.tv.updater.UpdateStatus.Checking -> stringResource(R.string.settings_update_checking)
                updateStatus is com.arflix.tv.updater.UpdateStatus.UpdateAvailable -> stringResource(R.string.settings_update_available_format, updateStatus.update.title.ifBlank { updateStatus.update.tag })
                updateStatus is com.arflix.tv.updater.UpdateStatus.Success -> stringResource(R.string.settings_update_latest)
                else -> stringResource(R.string.settings_update_check_releases)
            },
            actionLabel = when {
                !isSelfUpdateSupported -> stringResource(R.string.settings_badge_play)
                updateStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall -> stringResource(R.string.settings_badge_install)
                updateStatus is com.arflix.tv.updater.UpdateStatus.Checking -> stringResource(R.string.settings_badge_checking)
                updateStatus is com.arflix.tv.updater.UpdateStatus.UpdateAvailable -> stringResource(R.string.settings_badge_update)
                else -> stringResource(R.string.settings_badge_check)
            },
            isFocused = focusedIndex == 4,
            onClick = {
                if (updateStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall) onInstallUpdate() else onCheckUpdates()
            },
            modifier = Modifier.settingsFocusSlot(4)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = stringResource(R.string.settings_privacy_data_deletion),
            description = stringResource(R.string.settings_privacy_data_deletion_desc),
            actionLabel = stringResource(R.string.settings_badge_open),
            isFocused = focusedIndex == 5,
            onClick = onOpenDataDeletion,
            modifier = Modifier.settingsFocusSlot(5)
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AccountActionRow(
    title: String,
    description: String,
    actionLabel: String,
    isEnabled: Boolean,
    isFocused: Boolean
) {
    val focusRingColor = resolveAccentColor(fallback = Pink)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .background(
                    if (isEnabled) Pink.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.05f),
                    RoundedCornerShape(999.dp)
                )
                .border(
                    1.dp,
                    if (isEnabled) Pink.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.15f),
                    RoundedCornerShape(999.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = actionLabel.uppercase(),
                style = ArflixTypography.label.copy(fontSize = 11.sp, letterSpacing = 0.5.sp),
                color = if (isEnabled) Pink else TextSecondary,
                maxLines = 1
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsActionRow(
    title: String,
    description: String,
    actionLabel: String,
    isFocused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRingColor = resolveAccentColor(fallback = Pink)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .background(Pink.copy(alpha = 0.15f), RoundedCornerShape(999.dp))
                .border(1.dp, Pink.copy(alpha = 0.3f), RoundedCornerShape(999.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = actionLabel.uppercase(),
                style = ArflixTypography.label.copy(fontSize = 11.sp, letterSpacing = 0.5.sp),
                color = Pink,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AccountDisconnectConfirmDialog(
    title: String,
    description: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var focusedButton by remember { mutableIntStateOf(0) } // 0 = cancel, 1 = disconnect
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    LaunchedEffect(Unit) { runCatching { focusRequester.requestFocus() } }

    // onPreviewKeyEvent on the root Box fires for ANY focused child (Cancel button,
    // Disconnect button, or the Box itself). This is intentional: it intercepts key
    // events BEFORE clickable children can handle them, preventing the Cancel button
    // from firing via its own clickable when the user presses OK/Enter to open the dialog.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.65f))
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                when (event.key) {
                    Key.Back, Key.Escape -> { onDismiss(); true }
                    Key.DirectionLeft  -> { focusedButton = if (isRtl) 1 else 0; true }
                    Key.DirectionRight -> { focusedButton = if (isRtl) 0 else 1; true }
                    Key.Enter, Key.DirectionCenter -> {
                        if (focusedButton == 0) onDismiss() else onConfirm()
                        true
                    }
                    else -> false
                }
            }
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 360.dp)
                .background(BackgroundElevated, RoundedCornerShape(16.dp))
                .clickable(enabled = false) {}
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle.copy(fontSize = 18.sp),
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            if (focusedButton == 0) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.06f),
                            RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (focusedButton == 0) 2.dp else 0.dp,
                            color = if (focusedButton == 0) Color.White.copy(alpha = 0.5f) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { onDismiss() }
                        .padding(vertical = 13.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.cancel).uppercase(),
                        style = ArflixTypography.label.copy(fontSize = 12.sp),
                        color = TextPrimary
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            if (focusedButton == 1) Pink.copy(alpha = 0.3f) else Pink.copy(alpha = 0.12f),
                            RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (focusedButton == 1) 2.dp else 1.dp,
                            color = if (focusedButton == 1) Pink else Pink.copy(alpha = 0.35f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { onConfirm() }
                        .padding(vertical = 13.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.settings_disconnect).uppercase(),
                        style = ArflixTypography.label.copy(fontSize = 12.sp),
                        color = Pink
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AccountRow(
    name: String,
    description: String,
    isConnected: Boolean,
    isWorking: Boolean,
    authCode: String?,
    authUrl: String?,
    isFocused: Boolean,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryActionLabel: String? = null,
    expirationText: String? = null
) {
    val focusRingColor = resolveAccentColor(fallback = Pink)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = !isWorking) {
                if (isConnected) onDisconnect() else onConnect()
            }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        style = ArflixTypography.caption.copy(fontSize = 13.sp),
                        color = TextSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))

            if (isConnected) {
                Box(
                    modifier = Modifier
                        .background(SuccessGreen.copy(alpha = 0.15f), RoundedCornerShape(999.dp))
                        .border(1.dp, SuccessGreen.copy(alpha = 0.3f), RoundedCornerShape(999.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.connected).uppercase(),
                        style = ArflixTypography.label.copy(fontSize = 11.sp, letterSpacing = 0.5.sp),
                        color = SuccessGreen,
                        maxLines = 1
                    )
                }
            } else if (isWorking) {
                LoadingIndicator(
                    color = Pink,
                    size = 24.dp,
                    strokeWidth = 2.dp
                )
            } else {
                Box(
                    modifier = Modifier
                        .background(Pink.copy(alpha = 0.15f), RoundedCornerShape(999.dp))
                        .border(1.dp, Pink.copy(alpha = 0.3f), RoundedCornerShape(999.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.connect).uppercase(),
                        style = ArflixTypography.label.copy(fontSize = 11.sp, letterSpacing = 0.5.sp),
                        color = Pink,
                        maxLines = 1
                    )
                }
            }
        }

        // Show expiration date when connected
        if (isConnected && expirationText != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = expirationText,
                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                color = TextSecondary.copy(alpha = 0.7f)
            )
        }

        // Show auth code when polling
        if (!isConnected && isWorking && !authCode.isNullOrBlank() && !authUrl.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.settings_go_to, authUrl),
                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                color = TextSecondary.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.enter_code),
                    style = ArflixTypography.caption.copy(fontSize = 13.sp),
                    color = TextSecondary.copy(alpha = 0.9f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .background(Pink.copy(alpha = 0.18f), RoundedCornerShape(8.dp))
                        .border(1.dp, Pink.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = authCode,
                        style = ArflixTypography.label,
                        color = Pink
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.loading_label),
                style = ArflixTypography.caption.copy(fontSize = 13.sp),
                color = TextSecondary.copy(alpha = 0.7f)
            )
        }
    }
}

/**
 * Data class for input field
 */
data class InputField(
    val label: String,
    val value: String,
    val placeholder: String = "",
    val helper: String = "",
    val isSecret: Boolean = false,
    val singleLine: Boolean = true,
    val onValueChange: (String) -> Unit
)

private fun IptvPlaylistEntry.settingsEpgInput(): String {
    return (epgUrls.orEmpty().ifEmpty { listOf(epgUrl) })
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .distinct()
        .joinToString("\n")
}

private fun splitSettingsEpgInput(raw: String): List<String> {
    return raw
        .split('\n', '\r', ',', ';', '|')
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .distinct()
}

/**
 * Input modal for text entry (custom addon URL, API keys, etc.)
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun InputModalLegacy(
    title: String,
    fields: List<InputField>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    // Track which element is focused: 0 to fields.size-1 = text fields, fields.size = paste button, fields.size+1 = cancel, fields.size+2 = confirm
    var focusedIndex by remember { mutableIntStateOf(0) } // Start on first text field
    val totalItems = fields.size + 3 // fields + paste + cancel + confirm

    // Create focus requesters for each text field
    val fieldFocusRequesters = remember { fields.map { FocusRequester() } }

    // Clipboard manager for paste functionality
    val clipboardManager = LocalClipboardManager.current

    // Request focus on first field when modal opens
    LaunchedEffect(Unit) {
        if (fieldFocusRequesters.isNotEmpty()) {
            fieldFocusRequesters[0].requestFocus()
        }
    }

    // Request focus when focusedIndex changes to a text field
    LaunchedEffect(focusedIndex) {
        if (focusedIndex < fields.size && focusedIndex >= 0) {
            fieldFocusRequesters[focusedIndex].requestFocus()
        }
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .width(550.dp)
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(32.dp)
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                Key.DirectionUp -> {
                                    if (focusedIndex > 0) {
                                        focusedIndex--
                                    }
                                    true
                                }
                                Key.DirectionDown -> {
                                    if (focusedIndex < totalItems - 1) {
                                        focusedIndex++
                                    }
                                    true
                                }
                                Key.DirectionLeft -> {
                                    if (focusedIndex == fields.size + 2) {
                                        focusedIndex = fields.size + 1
                                    }
                                    true
                                }
                                Key.DirectionRight -> {
                                    if (focusedIndex == fields.size + 1) {
                                        focusedIndex = fields.size + 2
                                    }
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    when {
                                        focusedIndex == fields.size -> {
                                            val clipboardText = clipboardManager.getText()?.text
                                            // Paste into URL field (index 1) when available
                                            val targetIndex = if (fields.size > 1) 1 else 0
                                            if (clipboardText != null && fields.size > targetIndex) {
                                                fields[targetIndex].onValueChange(clipboardText)
                                            }
                                            true
                                        }
                                        focusedIndex == fields.size + 1 -> {
                                            onDismiss()
                                            true
                                        }
                                        focusedIndex == fields.size + 2 -> {
                                            onConfirm()
                                            true
                                        }
                                        else -> false
                                    }
                                }
                                else -> false
                            }
                        } else false
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                text = title,
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Input fields
            fields.forEachIndexed { index, field ->
                val isFocused = focusedIndex == index

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = field.label,
                        style = ArflixTypography.caption,
                        color = if (isFocused) Pink else TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    androidx.compose.material3.TextField(
                        value = field.value,
                        onValueChange = field.onValueChange,
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = stringResource(R.string.settings_enter_field, field.label.lowercase()),
                                color = TextSecondary.copy(alpha = 0.5f)
                            )
                        },
                        textStyle = ArflixTypography.body.copy(color = TextPrimary),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedContainerColor = Color.White.copy(alpha = 0.1f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                            focusedIndicatorColor = Pink,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Pink
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(fieldFocusRequesters[index])
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) resolveAccentColor(fallback = Pink) else Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }

                if (index < fields.size - 1) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Paste button
            val isPasteFocused = focusedIndex == fields.size
            val pasteFocusRingColor = resolveAccentColor(fallback = Pink)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (isPasteFocused) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = if (isPasteFocused) 2.dp else 0.dp,
                        color = if (isPasteFocused) pasteFocusRingColor else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ContentPaste,
                    contentDescription = stringResource(R.string.settings_cd_paste),
                    tint = if (isPasteFocused) Pink else TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.settings_paste_from_clipboard),
                    style = ArflixTypography.button,
                    color = if (isPasteFocused) Pink else TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Cancel button
                val isCancelFocused = focusedIndex == fields.size + 1
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (isCancelFocused) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (isCancelFocused) 2.dp else 0.dp,
                            color = if (isCancelFocused) Pink else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tr("Cancel"),
                        style = ArflixTypography.button,
                        color = if (isCancelFocused) TextPrimary else TextSecondary
                    )
                }

                // Confirm button
                val isConfirmFocused = focusedIndex == fields.size + 2
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (isConfirmFocused) SuccessGreen else Pink.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (isConfirmFocused) 2.dp else 0.dp,
                            color = if (isConfirmFocused) SuccessGreen.copy(alpha = 0.5f) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tr("Confirm"),
                        style = ArflixTypography.button,
                        color = Color.White
                    )
                }
            }

            // Hint text
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.settings_hint_enter_dpad),
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.5f)
            )
            }
        }
    }
}

/**
 * TV IME-safe input modal.
 * - D-pad navigation is handled by the dialog container
 * - Keyboard opens only on OK/Click on a field
 * - Back closes the keyboard first (doesn't dismiss), second Back dismisses
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun InputModal(
    title: String,
    supportingText: String? = null,
    fields: List<InputField>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var focusedIndex by remember(title, fields.size) { mutableIntStateOf(0) }
    var lastFocusedFieldIndex by remember(title, fields.size) { mutableStateOf<Int?>(null) }
    val totalItems = fields.size + 3 // inputs + paste + cancel + confirm
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
    val maxDialogHeight = (screenHeightDp * 0.88f).coerceAtMost(if (isTouchDevice) 620.dp else 660.dp)

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val view = LocalView.current
    val modalFocusRequester = remember { FocusRequester() }
    val formScrollState = rememberScrollState()

    val editTextRefs = remember { MutableList<EditText?>(fields.size) { null } }

    fun anyEditTextFocused(): Boolean = editTextRefs.any { it?.hasFocus() == true }

    fun pasteTargetIndex(): Int {
        if (focusedIndex in 0 until fields.size) return focusedIndex
        lastFocusedFieldIndex?.takeIf { it in 0 until fields.size }?.let { return it }
        return fields.indexOfFirst { field ->
            field.label.contains("url", ignoreCase = true) ||
                field.label.contains("server", ignoreCase = true) ||
                field.label.contains("host", ignoreCase = true)
        }.takeIf { it >= 0 } ?: if (fields.size > 1) 1 else 0
    }

    fun pasteClipboardIntoTarget() {
        val clipboardText = clipboardManager.getText()?.text ?: return
        val targetIndex = pasteTargetIndex()
        val target = fields.getOrNull(targetIndex) ?: return
        target.onValueChange(clipboardText)
        editTextRefs.getOrNull(targetIndex)?.let { edit ->
            edit.setText(clipboardText)
            edit.setSelection(edit.text?.length ?: 0)
            edit.clearFocus()
        }
        modalFocusRequester.requestFocus()
    }

    fun hideKeyboardAll() {
        val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        editTextRefs.forEach { edit ->
            if (edit != null) {
                imm?.hideSoftInputFromWindow(edit.windowToken, 0)
                edit.clearFocus()
                runCatching { imm?.restartInput(edit) }
            }
        }
        view.requestFocus()
    }

    fun showKeyboardFor(index: Int) {
        val edit = editTextRefs.getOrNull(index) ?: return
        val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        edit.post {
            edit.requestFocus()
            val shown = imm?.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT) ?: false
            if (!shown) imm?.showSoftInput(edit, InputMethodManager.SHOW_FORCED)
        }
    }

    LaunchedEffect(title, fields.size) {
        // Ensure D-pad events always start from the dialog on all TV devices.
        modalFocusRequester.requestFocus()
    }

    // Auto-scroll the form so the focused field stays in view when D-pad navigates.
    // Each field is ~86dp tall (label 22dp + spacer 6dp + edittext 48dp + spacing 12dp).
    // Scroll so the focused field stays in view. Distribute proportionally across the actual
    // scroll range so the calculation works at any screen density and form height.
    LaunchedEffect(focusedIndex) {
        if (focusedIndex in 0 until fields.size) {
            lastFocusedFieldIndex = focusedIndex
            val maxScroll = formScrollState.maxValue
            if (maxScroll > 0 && fields.size > 1) {
                val targetScroll = (focusedIndex.toFloat() / (fields.size - 1) * maxScroll).toInt()
                runCatching { formScrollState.animateScrollTo(targetScroll) }
            }
        } else if (focusedIndex >= fields.size) {
            // Focused on paste/cancel/confirm — scroll form to end so it's not blocking
            runCatching { formScrollState.animateScrollTo(formScrollState.maxValue) }
        }
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = {
            hideKeyboardAll()
            onDismiss()
        },
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(
            onDismiss = {
                hideKeyboardAll()
                onDismiss()
            }
        ) {
            Column(
                modifier = Modifier
                    .then(
                        if (isTouchDevice) Modifier.fillMaxWidth(0.92f).widthIn(max = 640.dp)
                        else Modifier.width(620.dp)
                    )
                    .navigationBarsPadding()
                    .imePadding()
                    .heightIn(max = maxDialogHeight)
                    .background(BackgroundElevated, RoundedCornerShape(14.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(14.dp))
                    .padding(horizontal = if (isTouchDevice) 16.dp else 20.dp, vertical = 18.dp)
                    .focusRequester(modalFocusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    if (anyEditTextFocused()) {
                                        hideKeyboardAll()
                                    } else {
                                        hideKeyboardAll()
                                        onDismiss()
                                    }
                                    true
                                }
                                Key.DirectionUp -> {
                                    if (focusedIndex > 0) {
                                        if (focusedIndex < fields.size) hideKeyboardAll()
                                        focusedIndex--
                                    }
                                    true
                                }
                                Key.DirectionDown -> {
                                    if (focusedIndex < totalItems - 1) {
                                        if (focusedIndex < fields.size) hideKeyboardAll()
                                        focusedIndex++
                                    }
                                    true
                                }
                                Key.DirectionLeft -> {
                                    if (focusedIndex == fields.size + 2) focusedIndex = fields.size + 1
                                    true
                                }
                                Key.DirectionRight -> {
                                    if (focusedIndex == fields.size + 1) focusedIndex = fields.size + 2
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    when {
                                        focusedIndex in 0 until fields.size -> {
                                            showKeyboardFor(focusedIndex)
                                            true
                                        }
                                        focusedIndex == fields.size -> {
                                            pasteClipboardIntoTarget()
                                            true
                                        }
                                        focusedIndex == fields.size + 1 -> {
                                            hideKeyboardAll()
                                            onDismiss()
                                            true
                                        }
                                        focusedIndex == fields.size + 2 -> {
                                            hideKeyboardAll()
                                            onConfirm()
                                            true
                                        }
                                        else -> false
                                    }
                                }
                                else -> false
                            }
                        } else false
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Text(
                    text = if (LocalDeviceType.current.isTouchDevice()) {
                        stringResource(R.string.settings_modal_hint_touch)
                    } else {
                        stringResource(R.string.settings_modal_hint_tv)
                    },
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.75f),
                    modifier = Modifier.padding(top = 2.dp, bottom = if (supportingText == null) 12.dp else 4.dp)
                )
                if (supportingText != null) {
                    Text(
                        text = supportingText,
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.68f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                        .verticalScroll(formScrollState)
                ) {
                    fields.forEachIndexed { index, field ->
                        val isFocused = focusedIndex == index

                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(22.dp)
                                        .background(
                                            if (isFocused) Pink.copy(alpha = 0.20f) else Color.White.copy(alpha = 0.08f),
                                            RoundedCornerShape(11.dp)
                                        )
                                        .border(
                                            1.dp,
                                            if (isFocused) Pink else Color.White.copy(alpha = 0.12f),
                                            RoundedCornerShape(11.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${index + 1}",
                                        style = ArflixTypography.caption,
                                        color = if (isFocused) Pink else TextSecondary
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = field.label,
                                    style = ArflixTypography.caption,
                                    color = if (isFocused) Pink else TextSecondary
                                )
                            }
                            if (field.helper.isNotBlank()) {
                                Text(
                                    text = field.helper,
                                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                                    color = TextSecondary.copy(alpha = 0.68f),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(start = 30.dp, bottom = 6.dp)
                                )
                            }

                            val regexFieldFocusColor = resolveAccentColor(fallback = Pink)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White.copy(alpha = if (isFocused) 0.12f else 0.05f), RoundedCornerShape(10.dp))
                                    .border(
                                        width = if (isFocused) 2.dp else 1.dp,
                                        color = if (isFocused) regexFieldFocusColor else Color.White.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(2.dp)
                            ) {
                                AndroidView(
                                    factory = { ctx ->
                                        EditText(ctx).apply {
                                            editTextRefs[index] = this
                                            setText(field.value)
                                            setTextColor(android.graphics.Color.WHITE)
                                            setHintTextColor(android.graphics.Color.GRAY)
                                            hint = field.placeholder.ifBlank { "Enter ${field.label.lowercase()}..." }
                                            textSize = 16f
                                            background = null
                                            setPadding(20, 14, 20, 14)
                                            isSingleLine = field.singleLine
                                            setHorizontallyScrolling(field.singleLine)
                                            minLines = if (field.singleLine) 1 else 3
                                            maxLines = if (field.singleLine) 1 else 5
                                            isFocusable = true
                                            isFocusableInTouchMode = true

                                            val isPasswordField = field.isSecret || field.label.contains("password", ignoreCase = true)
                                            val isLikelyUrlField =
                                                field.label.contains("url", ignoreCase = true) ||
                                                    field.label.contains("m3u", ignoreCase = true) ||
                                                    field.label.contains("epg", ignoreCase = true) ||
                                                    field.label.contains("server", ignoreCase = true)
                                            inputType = if (isPasswordField) {
                                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                                            } else if (isLikelyUrlField) {
                                                (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI) or
                                                    if (field.singleLine) 0 else InputType.TYPE_TEXT_FLAG_MULTI_LINE
                                            } else {
                                                InputType.TYPE_CLASS_TEXT or
                                                    if (field.singleLine) 0 else InputType.TYPE_TEXT_FLAG_MULTI_LINE
                                            }
                                            if (isPasswordField) {
                                                transformationMethod = PasswordTransformationMethod.getInstance()
                                            }

                                            doAfterTextChanged { editable ->
                                                field.onValueChange(editable?.toString() ?: "")
                                            }

                                            // Sync Compose focusedIndex when this EditText gains native focus
                                            // (prevents Bug 25: clicking one field opens another)
                                            setOnFocusChangeListener { _, hasFocus ->
                                                if (hasFocus && focusedIndex != index) {
                                                    focusedIndex = index
                                                }
                                                if (hasFocus) {
                                                    lastFocusedFieldIndex = index
                                                }
                                            }

                                            // Forward D-pad events to Compose navigation instead of letting EditText consume them
                                            setOnKeyListener { v, keyCode, event ->
                                                if (event.action == android.view.KeyEvent.ACTION_DOWN) {
                                                    when (keyCode) {
                                                        android.view.KeyEvent.KEYCODE_DPAD_DOWN -> {
                                                            val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                                            imm?.hideSoftInputFromWindow(windowToken, 0)
                                                            clearFocus()
                                                            focusedIndex = (index + 1).coerceAtMost(totalItems - 1)
                                                            modalFocusRequester.requestFocus()
                                                            true
                                                        }
                                                        android.view.KeyEvent.KEYCODE_DPAD_UP -> {
                                                            if (index > 0) {
                                                                val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                                                imm?.hideSoftInputFromWindow(windowToken, 0)
                                                                clearFocus()
                                                                focusedIndex = index - 1
                                                                modalFocusRequester.requestFocus()
                                                            }
                                                            true
                                                        }
                                                        android.view.KeyEvent.KEYCODE_BACK -> {
                                                            val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                                            imm?.hideSoftInputFromWindow(windowToken, 0)
                                                            clearFocus()
                                                            modalFocusRequester.requestFocus()
                                                            true
                                                        }
                                                        else -> false
                                                    }
                                                } else false
                                            }

                                            setOnEditorActionListener { _, actionId, _ ->
                                                if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
                                                    val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                                    imm?.hideSoftInputFromWindow(windowToken, 0)
                                                    clearFocus()
                                                    // Return focus to Compose so D-pad navigation works
                                                    modalFocusRequester.requestFocus()
                                                    true
                                                } else false
                                            }
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    update = { editText ->
                                        val current = editText.text?.toString().orEmpty()
                                        if (current != field.value) {
                                            editText.setText(field.value)
                                            editText.setSelection(field.value.length)
                                        }
                                    }
                                )
                            }

                            if (index < fields.size - 1) {
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                val isPasteFocused = focusedIndex == fields.size
                val fieldFallbackLabel = stringResource(R.string.settings_field_fallback)
                val pasteTargetLabel = fields.getOrNull(pasteTargetIndex())
                    ?.label
                    ?.substringBefore("(")
                    ?.trim()
                    ?.ifBlank { fieldFallbackLabel }
                    ?: fieldFallbackLabel
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            color = if (isPasteFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (isPasteFocused) Color.White else Color.White.copy(alpha = 0.14f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            pasteClipboardIntoTarget()
                        }
                        .padding(vertical = 11.dp, horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentPaste,
                        contentDescription = stringResource(R.string.settings_cd_paste),
                        tint = if (isPasteFocused) Color.Black else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.settings_paste_into, pasteTargetLabel),
                        style = ArflixTypography.button,
                        color = if (isPasteFocused) Color.Black else Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val isCancelFocused = focusedIndex == fields.size + 1
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                color = if (isCancelFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isCancelFocused) Color.White else Color.White.copy(alpha = 0.14f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                hideKeyboardAll()
                                onDismiss()
                            }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tr("Cancel"),
                            style = ArflixTypography.button,
                            color = if (isCancelFocused) Color.Black else Color.White
                        )
                    }

                    val isConfirmFocused = focusedIndex == fields.size + 2
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                color = if (isConfirmFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isConfirmFocused) Color.White else Color.White.copy(alpha = 0.14f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                hideKeyboardAll()
                                onConfirm()
                            }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tr("Confirm"),
                            style = ArflixTypography.button,
                            color = if (isConfirmFocused) Color.Black else Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (LocalDeviceType.current.isTouchDevice()) stringResource(R.string.settings_modal_footer_touch) else stringResource(R.string.settings_modal_footer_tv),
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.56f)
                    )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SubtitlePickerModal(
    title: String,
    options: List<String>,
    selected: String,
    focusedIndex: Int,
    onFocusChange: (Int) -> Unit,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState()
    val safeIndex = focusedIndex.coerceIn(0, (options.size - 1).coerceAtLeast(0))

    LaunchedEffect(safeIndex) {
        if (options.isNotEmpty()) {
            listState.animateScrollToItem(safeIndex)
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .then(
                        if (LocalDeviceType.current.isTouchDevice()) Modifier.fillMaxWidth(0.92f).widthIn(max = 520.dp)
                        else Modifier.width(520.dp)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 28.dp)
                    .focusRequester(focusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                Key.DirectionUp -> {
                                    if (safeIndex > 0) onFocusChange(safeIndex - 1)
                                    true
                                }
                                Key.DirectionDown -> {
                                    if (safeIndex < options.size - 1) onFocusChange(safeIndex + 1)
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    if (options.isNotEmpty()) {
                                        onSelect(options[safeIndex])
                                    }
                                    true
                                }
                                else -> false
                            }
                        } else false
                    }
            ) {
                Text(
                    text = title,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    state = listState,
                    modifier = Modifier.heightIn(max = 360.dp).arvioDpadFocusGroup()
                ) {
                    itemsIndexed(options) { index, option ->
                        val isFocused = index == safeIndex
                        val isSelected = option.equals(selected, ignoreCase = true)
                        val optionFocusColor = resolveAccentColor(fallback = Pink)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (isFocused) Color.White.copy(alpha = 0.12f) else Color.Transparent,
                                    RoundedCornerShape(10.dp)
                                )
                                .border(
                                    width = if (isFocused) 2.dp else 1.dp,
                                    color = if (isFocused) optionFocusColor else Color.White.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { onSelect(option) }
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = option,
                                style = ArflixTypography.body,
                                color = if (isFocused) TextPrimary else TextSecondary,
                                modifier = Modifier.weight(1f)
                            )
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = SuccessGreen,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.settings_press_enter_to_select),
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

/** All TMDB-supported languages as (code, displayName) pairs. */
@Composable
private fun UiModeWarningDialog(
    nextMode: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var focusedIndex by remember { mutableIntStateOf(0) } // 0 = Confirm, 1 = Cancel
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .then(
                        if (LocalDeviceType.current.isTouchDevice()) Modifier.fillMaxWidth(0.92f).widthIn(max = 400.dp)
                        else Modifier.width(400.dp)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 28.dp)
                    .focusRequester(focusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyDown) {
                            when (event.key) {
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                Key.DirectionLeft -> {
                                    if (focusedIndex > 0) focusedIndex--
                                    true
                                }
                                Key.DirectionRight -> {
                                    if (focusedIndex < 1) focusedIndex++
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    if (focusedIndex == 0) onConfirm() else onDismiss()
                                    true
                                }
                                else -> false
                            }
                        } else false
                    }
            ) {
                Text(
                    text = stringResource(R.string.settings_change_ui_mode),
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(16.dp))

                val modeString = when (nextMode) {
                    "tv" -> stringResource(R.string.settings_mode_tv)
                    "tablet" -> stringResource(R.string.settings_mode_tablet)
                    "phone" -> stringResource(R.string.settings_mode_phone)
                    else -> stringResource(R.string.auto)
                }

                Text(
                    text = stringResource(R.string.settings_change_ui_mode_confirm, modeString),
                    style = ArflixTypography.body,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val isConfirmFocused = focusedIndex == 0
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isConfirmFocused) SuccessGreen else SuccessGreen.copy(alpha = 0.6f)
                            )
                            .clickable { onConfirm() }
                            .border(
                                width = if (isConfirmFocused) 2.dp else 0.dp,
                                color = if (isConfirmFocused) Color.White else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tr("Confirm"),
                            style = ArflixTypography.button,
                            color = Color.White
                        )
                    }

                    val isCancelFocused = focusedIndex == 1
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                color = if (isCancelFocused) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f)
                            )
                            .clickable { onDismiss() }
                            .border(
                                width = if (isCancelFocused) 2.dp else 0.dp,
                                color = if (isCancelFocused) Pink else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tr("Cancel"),
                            style = ArflixTypography.button,
                            color = if (isCancelFocused) TextPrimary else TextSecondary
                        )
                    }
                }
            }
        }
    }
}

val TMDB_LANGUAGES = listOf(
    "en-US" to "English",
    "nl-NL" to "Dutch (Nederlands)",
    "fr-FR" to "French (Francais)",
    "de-DE" to "German (Deutsch)",
    "es-ES" to "Spanish (Espanol)",
    "pt-PT" to "Portuguese (Portugues)",
    "pt-BR" to "Portuguese - Brazil",
    "it-IT" to "Italian (Italiano)",
    "ru-RU" to "Russian",
    "ja-JP" to "Japanese",
    "ko-KR" to "Korean",
    "zh-CN" to "Chinese (Simplified)",
    "zh-TW" to "Chinese (Traditional)",
    "ar-SA" to "Arabic",
    "hi-IN" to "Hindi",
    "tr-TR" to "Turkish (Turkce)",
    "pl-PL" to "Polish (Polski)",
    "sv-SE" to "Swedish (Svenska)",
    "da-DK" to "Danish (Dansk)",
    "no-NO" to "Norwegian (Norsk)",
    "fi-FI" to "Finnish (Suomi)",
    "el-GR" to "Greek",
    "cs-CZ" to "Czech (Cesky)",
    "hu-HU" to "Hungarian (Magyar)",
    "ro-RO" to "Romanian (Romana)",
    "th-TH" to "Thai",
    "vi-VN" to "Vietnamese",
    "id-ID" to "Indonesian",
    "ms-MY" to "Malay",
    "tl-PH" to "Filipino/Tagalog",
    "uk-UA" to "Ukrainian",
    "bg-BG" to "Bulgarian",
    "hr-HR" to "Croatian (Hrvatski)",
    "sr-RS" to "Serbian (Srpski)",
    "sk-SK" to "Slovak (Slovensky)",
    "sl-SI" to "Slovenian (Slovenscina)",
    "he-IL" to "Hebrew",
    "fa-IR" to "Persian (Farsi)",
    "bn-BD" to "Bengali",
    "ta-IN" to "Tamil",
    "te-IN" to "Telugu",
    "ur-PK" to "Urdu",
    "ca-ES" to "Catalan",
    "eu-ES" to "Basque (Euskara)",
    "gl-ES" to "Galician (Galego)",
    "lt-LT" to "Lithuanian",
    "lv-LV" to "Latvian",
    "et-EE" to "Estonian",
    "af-ZA" to "Afrikaans",
    "sw-KE" to "Swahili",
    "sq-AL" to "Albanian (Shqip)"
)

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun IptvCategoriesSettings(
    playlistId: String,
    availableGroups: List<String>,
    hiddenGroups: List<String>,
    groupOrder: List<String>,
    focusedIndex: Int,
    focusedActionIndex: Int,
    onToggleHidden: (String) -> Unit,
    onMoveUp: (String) -> Unit,
    onMoveDown: (String) -> Unit,
    onReset: () -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val orderedGroups = remember(groupOrder, availableGroups, playlistId) {
        orderedIptvGroups(
            playlistId = playlistId,
            availableGroups = availableGroups,
            groupOrder = groupOrder
        )
    }
    val categoryListState = rememberLazyListState()

    LaunchedEffect(isMobile, focusedIndex, orderedGroups.size) {
        if (!isMobile && focusedIndex > 0 && orderedGroups.isNotEmpty()) {
            categoryListState.animateScrollToItem((focusedIndex - 1).coerceIn(0, orderedGroups.lastIndex))
        }
    }

    Column {
        if (!isMobile) {
            Text(
                text = stringResource(R.string.settings_iptv_categories),
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        SettingsRow(
            icon = Icons.Default.Refresh,
            title = stringResource(R.string.settings_reset_order),
            subtitle = stringResource(R.string.settings_reset_order_desc),
            value = stringResource(R.string.settings_badge_reset),
            isFocused = focusedIndex == 0,
            onClick = onReset,
            modifier = Modifier.settingsFocusSlot(0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isMobile) {
            MobileSettingsCategory(title = stringResource(R.string.settings_section_categories)) {
                if (orderedGroups.isEmpty()) {
                    Text(
                        text = stringResource(R.string.settings_no_categories_available),
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    orderedGroups.forEachIndexed { index, group ->
                        val groupKey = com.arflix.tv.data.model.PlaylistGroupKey.build(playlistId, group)
                        val isHidden = hiddenGroups.contains(groupKey)
                        MobileSettingsRow(
                            icon = if (isHidden) Icons.Default.VisibilityOff else Icons.Default.Check,
                            title = group,
                            subtitle = if (isHidden) stringResource(R.string.settings_hidden) else stringResource(R.string.settings_visible),
                            value = "",
                            onClick = { onToggleHidden(group) },
                            showDivider = index < orderedGroups.lastIndex
                        )
                    }
                }
            }
        } else {
            if (orderedGroups.isEmpty()) {
                Text(
                    text = stringResource(R.string.settings_no_categories_available),
                    style = ArflixTypography.body,
                    color = TextSecondary,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(
                    state = categoryListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 560.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    itemsIndexed(
                        items = orderedGroups,
                        key = { _, group -> com.arflix.tv.data.model.PlaylistGroupKey.build(playlistId, group) }
                    ) { index, group ->
                        val rowFocusIndex = index + 1
                        val isRowFocused = focusedIndex == rowFocusIndex
                        val groupKey = com.arflix.tv.data.model.PlaylistGroupKey.build(playlistId, group)
                        val isHidden = hiddenGroups.contains(groupKey)

                        Row(
                            modifier = Modifier
                                .settingsFocusSlot(rowFocusIndex)
                                .fillMaxWidth()
                                .background(
                                    if (isRowFocused) Color.White.copy(alpha = 0.08f)
                                    else Color.Transparent,
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable { onToggleHidden(group) }
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = group,
                                    style = ArflixTypography.body,
                                    color = if (isRowFocused) TextPrimary else TextSecondary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (isHidden) stringResource(R.string.settings_hidden) else stringResource(R.string.settings_visible),
                                    style = ArflixTypography.caption,
                                    color = TextSecondary.copy(alpha = 0.7f)
                                )
                            }

                            CatalogActionChip(
                                icon = if (isHidden) Icons.Default.VisibilityOff else Icons.Default.Check,
                                isFocused = isRowFocused && focusedActionIndex == 0,
                                onClick = { onToggleHidden(group) }
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            CatalogActionChip(
                                icon = Icons.Default.ArrowUpward,
                                isFocused = isRowFocused && focusedActionIndex == 1,
                                onClick = { onMoveUp(group) }
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            CatalogActionChip(
                                icon = Icons.Default.ArrowDownward,
                                isFocused = isRowFocused && focusedActionIndex == 2,
                                onClick = { onMoveDown(group) }
                            )
                        }
                    }
                }
            }
        }
    }
}
