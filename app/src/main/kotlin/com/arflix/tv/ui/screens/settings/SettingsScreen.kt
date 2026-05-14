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
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
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
import com.arflix.tv.ui.skin.resolveFocusBorderColor
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

private const val ACCOUNT_DELETION_URL = "https://auth.arvio.tv/delete-account"

private fun openExternalUrl(context: Context, url: String) {
    runCatching {
        context.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
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
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
    var sectionIndex by remember { mutableIntStateOf(0) }
    var mobilePage by remember { mutableStateOf("MAIN") }
    var contentFocusIndex by remember { mutableIntStateOf(0) }
    var activeZone by remember { mutableStateOf(Zone.CONTENT) }
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    // Sub-focus for addon rows: 0 = toggle, 1 = delete
    var addonActionIndex by remember { mutableIntStateOf(0) }
    // Sub-focus for catalog rows: 0 = edit, 1 = up, 2 = down, 3 = layout, 4 = delete
    var catalogActionIndex by remember { mutableIntStateOf(0) }
    // Sub-focus for IPTV rows: 0 = enable, 1 = edit, 2 = up, 3 = down, 4 = delete
    var iptvActionIndex by remember { mutableIntStateOf(0) }
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
    val sections = remember {
        buildList {
            add("general")
            add("iptv")
            add("home_server")
            add("catalogs")
            add("stremio")
            add("accounts")
        }
    }
    val sectionMaxIndex: (String) -> Int = { section ->
        when (section) {
            "general" -> 32 // 33 rows (27 from main + 6 AI subtitle rows)
            "iptv" -> 2 + uiState.iptvPlaylists.size // Add + rows + refresh + clear
            "home_server" -> uiState.homeServerConnections.size + 3
            "catalogs" -> uiState.catalogs.size // Add + rows
            "stremio" -> stremioAddons.size // rows + add button
            "accounts" -> 4 // Cloud + Trakt + Force Sync + App Update + Privacy/Data
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
            iptvEditEpg = playlist?.epgUrl ?: ""
            iptvEditEnabled = playlist?.enabled ?: true
        }
    }

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
        uiState.aiKeyServerState.isActive ||
        uiState.showCloudPairDialog ||
        uiState.showCloudEmailPasswordDialog ||
        uiState.traktCode != null ||
        uiState.plexHomeServerAuth != null ||
        uiState.showAppUpdateDialog ||
        uiState.showUnknownSourcesDialog

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
                    val focusedStremioAddon = stremioAddons.getOrNull(contentFocusIndex)
                    val focusedStremioAddonCanDelete = focusedStremioAddon?.let { addon ->
                        !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
                    } ?: false
                    when (event.key) {
                        Key.Back, Key.Escape -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> onBack()
                                Zone.SECTION -> {
                                    activeZone = Zone.SIDEBAR
                                    isSidebarFocused = true
                                }
                                Zone.CONTENT -> {
                                    activeZone = Zone.SECTION
                                }
                            }
                            true
                        }
                        Key.DirectionLeft -> {
                            when (activeZone) {
                                Zone.CONTENT -> {
                                    if (currentSection == "stremio" && contentFocusIndex < stremioAddons.size && addonActionIndex > 0) {
                                        addonActionIndex = 0
                                    } else if (currentSection == "iptv" && contentFocusIndex in 1..uiState.iptvPlaylists.size && iptvActionIndex > 0) {
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
                                        addonActionIndex < 1 &&
                                        focusedStremioAddonCanDelete
                                    ) {
                                        addonActionIndex = 1
                                    } else if (currentSection == "iptv" && contentFocusIndex in 1..uiState.iptvPlaylists.size && iptvActionIndex < 4) {
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
                                        "general" -> {
                                            when (contentFocusIndex) {
                                                0 -> openContentLanguagePicker()
                                                1 -> openSubtitlePicker()
                                                2 -> openSecondarySubtitlePicker()
                                                3 -> openAudioLanguagePicker()
                                                4 -> viewModel.cycleSubtitleSize()
                                                5 -> viewModel.cycleSubtitleColor()
                                                6 -> viewModel.cycleSubtitleOffset()
                                                7 -> viewModel.cycleSubtitleStyle()
                                                8 -> viewModel.setFilterSubtitlesByLanguage(!uiState.filterSubtitlesByLanguage)
                                                9 -> viewModel.setAutoPlayNext(!uiState.autoPlayNext)
                                                10 -> viewModel.setAutoPlaySingleSource(!uiState.autoPlaySingleSource)
                                                11 -> viewModel.cycleAutoPlayMinQuality()
                                                12 -> viewModel.setTrailerAutoPlay(!uiState.trailerAutoPlay)
                                                13 -> viewModel.setTrailerSoundEnabled(!uiState.trailerSoundEnabled)
                                                14 -> viewModel.cycleFrameRateMatchingMode()
                                                15 -> showQualityFiltersModal = true
                                                16 -> viewModel.toggleCardLayoutMode()
                                                17 -> openUiModeWarningDialog()
                                                18 -> viewModel.setSkipProfileSelection(!uiState.skipProfileSelection)
                                                19 -> viewModel.setOledBlackBackground(!uiState.oledBlackBackground)
                                                20 -> viewModel.cycleClockFormat()
                                                21 -> viewModel.setShowBudget(!uiState.showBudget)
                                                22 -> viewModel.setSpoilerBlurEnabled(!uiState.spoilerBlurEnabled)
                                                23 -> viewModel.cycleFocusBorderColor()
                                                24 -> openDnsProviderPicker()
                                                25 -> viewModel.setShowLoadingStats(!uiState.showLoadingStats)
                                                26 -> viewModel.cycleVolumeBoost()
                                                27 -> viewModel.setSubtitleAiEnabled(!uiState.subtitleAiEnabled)
                                                28 -> showAiModelDialog = true
                                                29 -> viewModel.setSubtitleAiAutoSelect(!uiState.subtitleAiAutoSelect)
                                                30 -> viewModel.setSubtitleRemoveHearingImpaired(!uiState.subtitleRemoveHearingImpaired)
                                                31 -> showAiApiKeyDialog = true
                                                32 -> viewModel.startAiKeyServer()
                                            }
                                        }
                                        "iptv" -> {
                                            when {
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
                                                                updated[idx] = playlist.copy(enabled = !playlist.enabled)
                                                                viewModel.saveIptvPlaylists(updated)
                                                            }
                                                            1 -> {
                                                                editingIptvIndex = idx
                                                                showIptvInput = true
                                                            }
                                                            2 -> {
                                                                if (idx > 0) {
                                                                    val item = updated.removeAt(idx)
                                                                    updated.add(idx - 1, item)
                                                                    viewModel.saveIptvPlaylists(updated)
                                                                }
                                                            }
                                                            3 -> {
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
                                                    if (addonActionIndex == 0 || !canDelete) {
                                                        viewModel.toggleAddon(addon.id)
                                                    } else {
                                                        viewModel.removeAddon(addon.id)
                                                        addonActionIndex = 0
                                                        if (contentFocusIndex >= stremioAddons.size && contentFocusIndex > 0) {
                                                            contentFocusIndex--
                                                        }
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
                                                        viewModel.logout()
                                                    } else {
                                                        viewModel.startCloudAuth()
                                                    }
                                                }
                                                1 -> {
                                                    if (uiState.isTraktAuthenticated) {
                                                        viewModel.disconnectTrakt()
                                                    } else if (uiState.isTraktPolling) {
                                                        viewModel.cancelTraktAuth()
                                                    } else {
                                                        viewModel.startTraktAuth()
                                                    }
                                                }
                                                2 -> {
                                                    viewModel.forceCloudSyncNow()
                                                }
                                                3 -> {
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
                onAddCustomAddonClick = { showCustomAddonInput = true }
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
            ) {
                // Settings internal sidebar
                Column(
                    modifier = Modifier
                        .width(280.dp)
                        .fillMaxSize()
                        .background(appBackgroundDark())
                        .padding(vertical = 32.dp, horizontal = 24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.settings),
                        style = ArflixTypography.heroTitle.copy(fontSize = androidx.compose.ui.unit.TextUnit.Unspecified),
                        color = TextPrimary,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(bottom = 24.dp)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(sectionScrollState)
                    ) {
                        sections.forEachIndexed { index, section ->
                            SettingsSectionItem(
                                icon = when (section) {
                                    "general" -> Icons.Default.Settings
                                    "iptv" -> Icons.Default.LiveTv
                                    "home_server" -> Icons.Default.Cloud
                                    "catalogs" -> Icons.Default.Widgets
                                    "stremio" -> Icons.Default.Widgets
                                    "accounts" -> Icons.Default.Person
                                    else -> Icons.Default.Settings
                                },
                                title = when (section) {
                                    "general" -> stringResource(R.string.general)
                                    "iptv" -> stringResource(R.string.iptv)
                                    "home_server" -> "Home Server"
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
                                    activeZone = Zone.SECTION
                                }
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "ARVIO V${BuildConfig.VERSION_NAME}",
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.5f),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                // Content area
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(48.dp)
                ) {
                  CompositionLocalProvider(LocalSettingsFocusTracker provides focusTracker) {
                    when (sections[sectionIndex]) {
                        "general" -> {
                        GeneralSettings(
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
                            onDeviceModeClick = openUiModeWarningDialog,
                            onContentLanguageClick = openContentLanguagePicker,
                            onSkipProfileSelectionToggle = { viewModel.setSkipProfileSelection(it) },
                            onOledBlackBackgroundToggle = { viewModel.setOledBlackBackground(it) },
                            onClockFormatClick = { viewModel.cycleClockFormat() },
                            onShowBudgetToggle = { viewModel.setShowBudget(it) },
                            spoilerBlurEnabled = uiState.spoilerBlurEnabled,
                            onSpoilerBlurToggle = { viewModel.setSpoilerBlurEnabled(it) },
                            focusBorderColor = uiState.focusBorderColor,
                            onFocusBorderColorClick = { viewModel.cycleFocusBorderColor() },
                            showLoadingStats = uiState.showLoadingStats,
                            onShowLoadingStatsToggle = { viewModel.setShowLoadingStats(it) },
                            onVolumeBoostClick = { viewModel.cycleVolumeBoost() },
                            onSubtitleSizeClick = { viewModel.cycleSubtitleSize() },
                            onSubtitleColorClick = { viewModel.cycleSubtitleColor() },
                            onSubtitleOffsetClick = { viewModel.cycleSubtitleOffset() },
                            onSubtitleStyleClick = { viewModel.cycleSubtitleStyle() },
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
                            onSubtitleAiQrClick = { viewModel.startAiKeyServer() }
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
                        if (uiState.aiKeyServerState.isActive) {
                            AiKeyQrOverlay(
                                qrBitmap = uiState.aiKeyServerState.qrBitmap,
                                serverUrl = uiState.aiKeyServerState.serverUrl,
                                keyReceived = uiState.aiKeyServerState.keyReceived,
                                onClose = { viewModel.stopAiKeyServer() }
                            )
                        }
                        } // end "general" block
                        "iptv" -> IptvSettings(
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
                            onDelete = { viewModel.clearIptvConfig() }
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
                            onDeleteAddon = { viewModel.removeAddon(it) },
                            onAddCustomAddon = { showCustomAddonInput = true }
                        )
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
                            onDisconnectCloud = { viewModel.logout() },
                            onConnectTrakt = { viewModel.startTraktAuth() },
                            onCancelTrakt = { viewModel.cancelTraktAuth() },
                            onDisconnectTrakt = { viewModel.disconnectTrakt() },
                            onForceCloudSync = { viewModel.forceCloudSyncNow() },
                            onSwitchProfile = onSwitchProfile,
                            onCheckUpdates = { viewModel.checkForAppUpdates(force = true, showNoUpdateFeedback = true) },
                            onInstallUpdate = { viewModel.installAppUpdateOrRequestPermission() },
                            onOpenDataDeletion = { openExternalUrl(context, ACCOUNT_DELETION_URL) }
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
                    InputField(label = "URL", value = customAddonUrl, onValueChange = { customAddonUrl = it })
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
                title = "Home Server",
                supportingText = "Use HTTPS where possible. HTTP server URLs are allowed for local networks but are not encrypted.",
                fields = listOf(
                    InputField(
                        label = "Server name",
                        value = homeServerDisplayName,
                        placeholder = "Optional, shown in sources",
                        onValueChange = { homeServerDisplayName = it }
                    ),
                    InputField(
                        label = "Server URL",
                        value = homeServerUrl,
                        placeholder = "http://server:8096 or http://server:32400",
                        onValueChange = { homeServerUrl = it }
                    ),
                    InputField(
                        label = "Username",
                        value = homeServerUsername,
                        placeholder = "Optional for token sign-in",
                        onValueChange = { homeServerUsername = it }
                    ),
                    InputField(
                        label = "Password / token",
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
                title = "Connect with code",
                supportingText = "Use HTTPS where possible. HTTP server URLs are allowed for local networks but are not encrypted.",
                fields = listOf(
                    InputField(
                        label = "Server name",
                        value = plexHomeServerDisplayName,
                        placeholder = "Optional, shown in sources",
                        onValueChange = { plexHomeServerDisplayName = it }
                    ),
                    InputField(
                        label = "Server URL (optional)",
                        value = plexHomeServerUrl,
                        placeholder = "Leave empty to discover automatically",
                        onValueChange = { plexHomeServerUrl = it }
                    )
                ),
                onConfirm = {
                    viewModel.startPlexHomeServerAuth(
                        serverUrl = plexHomeServerUrl.trim(),
                        displayName = plexHomeServerDisplayName.trim()
                    )
                    plexHomeServerDisplayName = ""
                    showPlexHomeServerInput = false
                },
                onDismiss = {
                    plexHomeServerDisplayName = ""
                    showPlexHomeServerInput = false
                }
            )
        }
        if (showIptvInput) {
            InputModal(
                title = if (editingIptvIndex >= 0) "Edit IPTV Playlist" else "Add IPTV Playlist",
                fields = listOf(
                    InputField(
                        label = "Playlist Name",
                        value = iptvEditName,
                        onValueChange = { iptvEditName = it }
                    ),
                    InputField(
                        label = "M3U URL or Xtream Host",
                        value = iptvEditUrl,
                        placeholder = "https://provider.host:port",
                        onValueChange = { iptvEditUrl = it }
                    ),
                    InputField(
                        label = "Xtream Username (Optional)",
                        value = iptvEditXtreamUser,
                        placeholder = "Leave empty for plain M3U",
                        onValueChange = { iptvEditXtreamUser = it }
                    ),
                    InputField(
                        label = "Xtream Password (Optional)",
                        value = iptvEditXtreamPass,
                        placeholder = "Leave empty for plain M3U",
                        isSecret = true,
                        onValueChange = { iptvEditXtreamPass = it }
                    ),
                    InputField(
                        label = "EPG URL (Optional)",
                        value = iptvEditEpg,
                        placeholder = "Leave empty to auto-derive for Xtream",
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
                    val updated = uiState.iptvPlaylists.toMutableList()
                    val entry = com.arflix.tv.data.repository.IptvPlaylistEntry(
                        id = updated.getOrNull(editingIptvIndex)?.id ?: "list_${editingIptvIndex + 2}".takeIf { editingIptvIndex >= 0 } ?: "list_${updated.size + 1}",
                        name = iptvEditName,
                        m3uUrl = finalM3uUrl,
                        epgUrl = finalEpgUrl,
                        enabled = iptvEditEnabled
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
                    InputField(label = "Title", value = renameCatalogTitle, onValueChange = { renameCatalogTitle = it })
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
                title = if (editingQualityFilterId == null) "Add Quality Filter" else "Edit Quality Filter",
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

        if (isTouchDevice && uiState.aiKeyServerState.isActive) {
            AiKeyQrOverlay(
                qrBitmap = uiState.aiKeyServerState.qrBitmap,
                serverUrl = uiState.aiKeyServerState.serverUrl,
                keyReceived = uiState.aiKeyServerState.keyReceived,
                onClose = { viewModel.stopAiKeyServer() }
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
                title = "Connect with code",
                instruction = "Scan the QR code or open the auth page and confirm this code",
                verificationUrl = plexAuth.verificationUrl,
                userCode = plexAuth.code,
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
                    text = "Quality Regex Filters",
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Device-local filters. Matching streams are excluded.",
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (filters.isEmpty()) {
                    Text(
                        text = "No filters configured yet.",
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
                                        text = filter.deviceName.ifBlank { "Unnamed Device" },
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
                        label = "Close",
                        enabled = true,
                        onClick = onDismiss,
                        isFocused = isFooterFocused && selectedFooterAction == 0,
                        modifier = Modifier.weight(1f)
                    )
                    SettingsChip(
                        label = "Add Filter",
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
    val chipFocusColor = resolveFocusBorderColor(fallback = Color.White)
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
                    label = { Text("Device / Preset Name") },
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
                    label = { Text("Regex Pattern") },
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
                        label = "Cancel",
                        enabled = true,
                        onClick = onDismiss,
                        isFocused = focusedIndex == 2,
                        modifier = Modifier.weight(1f)
                    )
                    SettingsChip(
                        label = "Save",
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
                    text = "ARVIO Cloud Sign-in",
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Email",
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
                        text = "Password",
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
                            text = "Cancel",
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
                            text = "Sign In",
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
                            text = "Create",
                            style = ArflixTypography.button,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = if (LocalDeviceType.current.isTouchDevice()) "Enter your email and password to sign in." else "Tip: Use TV keyboard. D-pad to navigate.",
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
                    text = "ARVIO Cloud Pairing",
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                if (isMobile) {
                    // On mobile, skip QR (can't scan own screen) and prompt email/password
                    Text(
                        text = "Sign in with your email and password to link this device.",
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                } else {
                    Text(
                        text = "Scan this QR code to sign in and link this TV.",
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
                            text = "Code: $userCode",
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
                            text = "Waiting for approval...",
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
                                    text = "Use Email & Password",
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
                                text = "Cancel",
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
                                    text = "Cancel",
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
                                    text = "Use Email/Password",
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
    title: String = "Connect Trakt.tv",
    instruction: String = "Go to $verificationUrl and enter this code"
) {
    val focusRequester = remember { FocusRequester() }
    val isMobile = LocalDeviceType.current.isTouchDevice()
    val qrContainerSize = if (isMobile) 0.dp else 172.dp
    val qrBitmapSizePx = if (isMobile) 0 else 512

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
                    text = title,
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = instruction,
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
                            text = "Waiting for authorization",
                            style = ArflixTypography.caption,
                            color = TextSecondary.copy(alpha = 0.78f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Box(
                    modifier = Modifier
                        .background(Pink, RoundedCornerShape(10.dp))
                        .clickable { onDismiss() }
                        .padding(vertical = 12.dp, horizontal = 18.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cancel",
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
    onAddCustomAddonClick: () -> Unit
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
                        modifier = Modifier.clickable { viewModel.logout() }.padding(8.dp)
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
                onSwitchProfile = onSwitchProfile
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
                    text = page,
                    style = ArflixTypography.heroTitle.copy(fontSize = 24.sp),
                    color = TextPrimary,
                    modifier = Modifier.weight(1f)
                )
            }
            MobileSettingsSubPage(
                page = page,
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
                onAddCustomAddonClick = onAddCustomAddonClick
            )
        }
    }
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
    onSwitchProfile: () -> Unit
) {
    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            MobileSettingsCategory(title = "LANGUAGES") {
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
            MobileSettingsCategory(title = "CATEGORIES") {
                val categories = listOf(
                    "Playback & Controls" to Icons.Default.PlayArrow,
                    "Audio & Subtitles" to Icons.Default.Speaker,
                    "Appearance" to Icons.Default.Palette,
                    "Plugins & Extensions" to Icons.Default.Extension,
                    "Catalogs" to Icons.Default.Widgets,
                    "IPTV" to Icons.Default.LiveTv,
                    "Home Server" to Icons.Default.Cloud
                )
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
                            Text(text = name, style = ArflixTypography.body, color = TextPrimary, modifier = Modifier.weight(1f))
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
            MobileSettingsCategory(title = "USER INFO & ACCOUNT") {
                if (uiState.isLoggedIn) {
                    MobileSettingsRow(
                        icon = Icons.Default.Person,
                        title = stringResource(R.string.cloud_account),
                        subtitle = uiState.accountEmail ?: "",
                        value = "Force Sync",
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
                        value = "Sign In",
                        isFocused = false,
                        onClick = { viewModel.openCloudEmailPasswordDialog() }
                    )
                }
                MobileSettingsRow(
                    icon = Icons.Default.Movie,
                    title = stringResource(R.string.trakt_account),
                    value = if (uiState.isTraktAuthenticated) "Disconnect" else "Connect",
                    isFocused = false,
                    onClick = { if (uiState.isTraktAuthenticated) viewModel.disconnectTrakt() else viewModel.startTraktAuth() }
                )
                MobileSettingsRow(
                    icon = Icons.Default.SystemUpdate,
                    title = stringResource(R.string.app_version),
                    subtitle = "V${BuildConfig.VERSION_NAME}",
                    value = if (uiState.updateStatus is com.arflix.tv.updater.UpdateStatus.UpdateAvailable) "Update Available" else "Check Updates",
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
    onAddCustomAddonClick: () -> Unit
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
                MobileSettingsCategory(title = "PLAYBACK") {
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
                MobileSettingsCategory(title = "CONTROLS") {
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
                        showDivider = false,
                        onClick = openDnsProviderPicker
                    )
                }
            }
            "Audio & Subtitles" -> {
                MobileSettingsCategory(title = "SUBTITLES") {
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
                MobileSettingsCategory(title = "AUDIO") {
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
                MobileSettingsCategory(title = "APPEARANCE") {
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
                        showDivider = false,
                        onClick = { viewModel.setShowBudget(!uiState.showBudget) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.VisibilityOff,
                        title = stringResource(R.string.spoiler_blur),
                        value = if (uiState.spoilerBlurEnabled) "On" else "Off",
                        isFocused = false,
                        showDivider = false,
                        onClick = { viewModel.setSpoilerBlurEnabled(!uiState.spoilerBlurEnabled) }
                    )
                    MobileSettingsRow(
                        icon = Icons.Default.Palette,
                        title = stringResource(R.string.focus_border_color),
                        value = uiState.focusBorderColor,
                        isFocused = false,
                        showDivider = false,
                        onClick = { viewModel.cycleFocusBorderColor() }
                    )
                }
            }
            "Plugins & Extensions" -> {
                StremioAddonsSettings(
                    addons = stremioAddons,
                    focusedIndex = -1,
                    focusedActionIndex = 0,
                    onToggleAddon = { viewModel.toggleAddon(it) },
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
            "IPTV" -> {
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
                    onDelete = { viewModel.clearIptvConfig() }
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
                        style = ArflixTypography.body,
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (subtitle.isNotEmpty()) {
                        Text(
                            text = subtitle,
                            style = ArflixTypography.caption,
                            color = TextSecondary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            if (value.isNotEmpty()) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = value,
                    style = ArflixTypography.caption.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
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
                Text("Allow Unknown Sources", style = ArflixTypography.sectionTitle, color = TextPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Allow installs from unknown sources for ARVIO so the downloaded update APK can be installed.",
                    style = ArflixTypography.body,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UpdateActionButton("Close", focusedIndex == 0, onDismiss)
                    UpdateActionButton("Open Settings", focusedIndex == 1, onOpenSettings, highlighted = true)
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
        isFocused -> Color.White.copy(alpha = 0.1f)
        isSelected -> Color.White.copy(alpha = 0.05f)
        else -> Color.Transparent
    }
    val textColor = when {
        isFocused -> Pink
        isSelected -> TextPrimary
        else -> TextSecondary
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = ArflixTypography.body,
            color = textColor
        )
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
    focusBorderColor: String = "White",
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
    onFocusBorderColorClick: () -> Unit = {},
    showLoadingStats: Boolean = true,
    onShowLoadingStatsToggle: (Boolean) -> Unit = {},
    onVolumeBoostClick: () -> Unit = {},
    trailerAutoPlay: Boolean = false,
    trailerSoundEnabled: Boolean = false,
    onSubtitleSizeClick: () -> Unit = {},
    onSubtitleColorClick: () -> Unit = {},
    onSubtitleOffsetClick: () -> Unit = {},
    onSubtitleStyleClick: () -> Unit = {},
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
    onSubtitleAiQrClick: () -> Unit = {}
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
            title = stringResource(R.string.filter_subtitles),
            subtitle = stringResource(R.string.filter_subtitles_desc),
            isEnabled = filterSubtitlesByLanguage,
            isFocused = focusedIndex == 8,
            onToggle = onFilterSubtitlesByLanguageToggle,
            modifier = Modifier.settingsFocusSlot(8)
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
            isFocused = focusedIndex == 9,
            onToggle = onAutoPlayToggle,
            modifier = Modifier.settingsFocusSlot(9)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.autoplay),
            subtitle = stringResource(R.string.autoplay_desc),
            isEnabled = autoPlaySingleSource,
            isFocused = focusedIndex == 10,
            onToggle = onAutoPlaySingleSourceToggle,
            modifier = Modifier.settingsFocusSlot(10)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.HighQuality,
            title = stringResource(R.string.auto_play_min_quality),
            subtitle = stringResource(R.string.auto_play_quality_desc),
            value = autoPlayMinQuality,
            isFocused = focusedIndex == 11,
            onClick = onAutoPlayMinQualityClick,
            modifier = Modifier.settingsFocusSlot(11)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.trailer_auto_play),
            subtitle = stringResource(R.string.trailer_desc),
            isEnabled = trailerAutoPlay,
            isFocused = focusedIndex == 12,
            onToggle = onTrailerAutoPlayToggle,
            modifier = Modifier.settingsFocusSlot(12)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.trailer_sound),
            subtitle = stringResource(R.string.trailer_sound_desc),
            isEnabled = trailerSoundEnabled,
            isFocused = focusedIndex == 13,
            onToggle = onTrailerSoundEnabledToggle,
            modifier = Modifier.settingsFocusSlot(13)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Movie,
            title = stringResource(R.string.frame_rate),
            subtitle = stringResource(R.string.frame_rate_desc),
            value = frameRateMatchingMode,
            isFocused = focusedIndex == 14,
            onClick = onFrameRateMatchingClick,
            modifier = Modifier.settingsFocusSlot(14)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.HighQuality,
            title = stringResource(R.string.quality_filters),
            subtitle = stringResource(R.string.quality_filters_desc),
            value = qualityFilterValue,
            isFocused = focusedIndex == 15,
            onClick = onQualityFiltersClick,
            modifier = Modifier.settingsFocusSlot(15)
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
            isFocused = focusedIndex == 16,
            onClick = onCardLayoutToggle,
            modifier = Modifier.settingsFocusSlot(16)
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
            isFocused = focusedIndex == 17,
            onClick = onDeviceModeClick,
            modifier = Modifier.settingsFocusSlot(17)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.skip_profile),
            subtitle = stringResource(R.string.skip_profile_desc),
            isEnabled = skipProfileSelection,
            isFocused = focusedIndex == 18,
            onToggle = onSkipProfileSelectionToggle,
            modifier = Modifier.settingsFocusSlot(18)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.oled_black_background),
            subtitle = stringResource(R.string.oled_black_background_desc),
            isEnabled = oledBlackBackground,
            isFocused = focusedIndex == 19,
            onToggle = onOledBlackBackgroundToggle,
            modifier = Modifier.settingsFocusSlot(19)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Schedule,
            title = stringResource(R.string.clock_format),
            subtitle = stringResource(R.string.clock_format_desc),
            value = if (clockFormat == "12h") "12-hour" else "24-hour",
            isFocused = focusedIndex == 20,
            onClick = onClockFormatClick,
            modifier = Modifier.settingsFocusSlot(20)
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Home hero controls — issue #72. The movie Budget line on the hero banner
        // makes the metadata row noisy on small screens and some users want to hide it.
        SettingsToggleRow(
            title = stringResource(R.string.show_budget),
            subtitle = stringResource(R.string.show_budget_desc),
            isEnabled = showBudget,
            isFocused = focusedIndex == 21,
            onToggle = onShowBudgetToggle,
            modifier = Modifier.settingsFocusSlot(21)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.spoiler_blur),
            subtitle = stringResource(R.string.spoiler_blur_desc),
            isEnabled = spoilerBlurEnabled,
            isFocused = focusedIndex == 22,
            onToggle = onSpoilerBlurToggle,
            modifier = Modifier.settingsFocusSlot(22)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Palette,
            title = stringResource(R.string.focus_border_color),
            subtitle = stringResource(R.string.focus_border_color_desc),
            value = focusBorderColor,
            isFocused = focusedIndex == 23,
            onClick = onFocusBorderColorClick,
            modifier = Modifier.settingsFocusSlot(23)
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
            isFocused = focusedIndex == 24,
            onClick = onDnsProviderClick,
            modifier = Modifier.settingsFocusSlot(24)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.show_loading_stats),
            subtitle = stringResource(R.string.show_loading_stats_desc),
            isEnabled = showLoadingStats,
            isFocused = focusedIndex == 25,
            onToggle = onShowLoadingStatsToggle,
            modifier = Modifier.settingsFocusSlot(25)
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
            isFocused = focusedIndex == 26,
            onClick = onVolumeBoostClick,
            modifier = Modifier.settingsFocusSlot(26)
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
            isFocused = focusedIndex == 27,
            onToggle = onSubtitleAiEnabledToggle,
            modifier = Modifier.settingsFocusSlot(27)
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
            isFocused = focusedIndex == 28,
            onClick = onSubtitleAiModelClick,
            modifier = Modifier.settingsFocusSlot(28).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.ai_auto_select_title),
            subtitle = stringResource(R.string.ai_auto_select_desc),
            isEnabled = subtitleAiAutoSelect,
            isFocused = focusedIndex == 29,
            onToggle = onSubtitleAiAutoSelectToggle,
            modifier = Modifier.settingsFocusSlot(29).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = stringResource(R.string.ai_remove_hi_title),
            subtitle = stringResource(R.string.ai_remove_hi_desc),
            isEnabled = subtitleRemoveHearingImpaired,
            isFocused = focusedIndex == 30,
            onToggle = onSubtitleRemoveHearingImpairedToggle,
            modifier = Modifier.settingsFocusSlot(30).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.VpnKey,
            title = stringResource(R.string.ai_api_key_title),
            subtitle = stringResource(R.string.ai_api_key_desc),
            value = maskAiApiKey(subtitleAiApiKey, stringResource(R.string.ai_key_not_set)),
            isFocused = focusedIndex == 31,
            onClick = onSubtitleAiApiKeyClick,
            modifier = Modifier.settingsFocusSlot(31).alpha(if (subtitleAiEnabled) 1f else 0.4f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.QrCode,
            title = stringResource(R.string.ai_scan_qr_title),
            subtitle = stringResource(R.string.ai_scan_qr_desc),
            value = "",
            isFocused = focusedIndex == 32,
            onClick = onSubtitleAiQrClick,
            modifier = Modifier.settingsFocusSlot(32).alpha(if (subtitleAiEnabled) 1f else 0.4f)
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
                        contentDescription = "QR Code",
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
    val hasConnections = connections.isNotEmpty()
    Column {
        if (!LocalDeviceType.current.isTouchDevice()) {
            Text(
                text = "Home Server",
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        SettingsRow(
            icon = Icons.Default.Cloud,
            title = "Add server",
            subtitle = "Personal media libraries as sources",
            value = if (isWorking) "Working" else if (hasConnections) "Add another" else "Connect",
            isFocused = focusedIndex == 0,
            onClick = onConnect,
            modifier = Modifier.settingsFocusSlot(0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = "Connect with code",
            description = "Sign in with a server code and use media libraries as sources",
            actionLabel = if (isPlexWorking) "Waiting" else "Code",
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
                if (libraries > 0) "$libraries collections" else null
            ).joinToString("  |  ").ifBlank { connection.serverUrl }

            SettingsActionRow(
                title = connection.displayName.ifBlank { connection.serverName }.ifBlank { connection.serverUrl },
                description = description,
                actionLabel = "Change",
                isFocused = focusedIndex == index + 2,
                onClick = { onEditConnection(connection) },
                modifier = Modifier.settingsFocusSlot(index + 2)
            )
        }

        val testIndex = connections.size + 2
        val disconnectIndex = connections.size + 3

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = "Test connection",
            description = if (!hasConnections) "Connect a server first" else "Check that this profile can reach every server",
            actionLabel = if (isWorking) "Working" else "Test",
            isFocused = focusedIndex == testIndex,
            onClick = { if (hasConnections) onTest() },
            modifier = Modifier.settingsFocusSlot(testIndex)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = "Disconnect all",
            description = if (!hasConnections) "No server is connected" else "Remove all servers from the active profile",
            actionLabel = "Remove",
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

private fun homeServerKindLabel(kind: HomeServerKind): String {
    return when (kind) {
        HomeServerKind.JELLYFIN,
        HomeServerKind.EMBY,
        HomeServerKind.PLEX -> "Media Server"
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
    onDelete: () -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    var selectionMode by remember { mutableStateOf(false) }
    var selectedIndices by remember { mutableStateOf(setOf<Int>()) }

    Column {
        if (!isMobile) {
            Text(
                text = stringResource(R.string.iptv),
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        } else if (selectionMode) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${selectedIndices.size} ${stringResource(R.string.selected)}",
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.weight(1f))
                if (selectedIndices.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                selectedIndices.sortedDescending().forEach { index ->
                                    onDeletePlaylist(index)
                                }
                                selectionMode = false
                                selectedIndices = emptySet()
                            }
                            .background(Color(0xFFDC2626), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete), tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            selectionMode = false
                            selectedIndices = emptySet()
                        }
                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close), tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
        }

        SettingsRow(
            icon = Icons.Default.LiveTv,
            title = stringResource(R.string.add_playlist),
            subtitle = if (playlists.isEmpty()) "Add up to 3 M3U / Xtream IPTV lists with names" else "Create another IPTV list",
            value = if (playlists.size >= 3) "FULL" else "ADD",
            isFocused = focusedIndex == 0,
            onClick = onConfigure,
            modifier = Modifier.settingsFocusSlot(0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        playlists.forEachIndexed { index, playlist ->
            val rowIndex = index + 1
            val isSelected = selectedIndices.contains(index)
            Row(
                modifier = Modifier
                    .settingsFocusSlot(rowIndex)
                    .fillMaxWidth()
                    .background(
                        if (isSelected) Pink.copy(alpha = 0.2f)
                        else if (focusedIndex == rowIndex) Color.White.copy(alpha = 0.08f) 
                        else Color.Transparent,
                        RoundedCornerShape(12.dp)
                    )
                    .then(
                        if (isMobile) {
                            Modifier.combinedClickable(
                                onClick = {
                                    if (selectionMode) {
                                        selectedIndices = if (isSelected) selectedIndices - index else selectedIndices + index
                                        if (selectedIndices.isEmpty()) selectionMode = false
                                    } else {
                                        onEditPlaylist(index)
                                    }
                                },
                                onLongClick = {
                                    if (!selectionMode) {
                                        selectionMode = true
                                        selectedIndices = setOf(index)
                                    }
                                }
                            )
                        } else {
                            Modifier.clickable { onEditPlaylist(index) }
                        }
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isMobile && selectionMode) {
                    Icon(
                        imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = if (isSelected) Pink else TextSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = playlist.name,
                        style = ArflixTypography.body,
                        color = if (focusedIndex == rowIndex || isSelected) TextPrimary else TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = playlist.m3uUrl.take(56),
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.72f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (isMobile) {
                    if (selectionMode && selectedIndices.size == 1 && isSelected) {
                        Icon(
                            imageVector = Icons.Default.DragHandle,
                            contentDescription = "Drag to reorder",
                            tint = TextSecondary,
                            modifier = Modifier
                                .size(24.dp)
                                .pointerInput(index) {
                                    var dragOffset = 0f
                                    val itemHeight = 64.dp.toPx()
                                    detectVerticalDragGestures(
                                        onDragEnd = { dragOffset = 0f },
                                        onDragCancel = { dragOffset = 0f }
                                    ) { change, dragAmount ->
                                        change.consume()
                                        dragOffset += dragAmount
                                        if (dragOffset > itemHeight) {
                                            onMovePlaylistDown(index)
                                            dragOffset -= itemHeight
                                        } else if (dragOffset < -itemHeight) {
                                            onMovePlaylistUp(index)
                                            dragOffset += itemHeight
                                        }
                                    }
                                }
                        )
                    } else if (!selectionMode) {
                        CatalogActionChip(
                            icon = if (playlist.enabled) Icons.Default.Check else Icons.Default.VisibilityOff,
                            isFocused = false,
                            onClick = { onTogglePlaylist(index) }
                        )
                    }
                } else {
                    CatalogActionChip(
                        icon = if (playlist.enabled) Icons.Default.Check else Icons.Default.VisibilityOff,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 0,
                        onClick = { onTogglePlaylist(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.Edit,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 1,
                        onClick = { onEditPlaylist(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.ArrowUpward,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 2,
                        onClick = { onMovePlaylistUp(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.ArrowDownward,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 3,
                        onClick = { onMovePlaylistDown(index) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.Delete,
                        isFocused = focusedIndex == rowIndex && focusedActionIndex == 4,
                        isDestructive = true,
                        onClick = { onDeletePlaylist(index) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(6.dp))

        val refreshSubtitle = when {
            isLoading -> "Refreshing channels and EPG..."
            error != null -> error
            playlists.none { it.epgUrl.isNotBlank() } -> "Reload playlists now"
            else -> "Reload playlist and EPG now"
        }
        SettingsRow(
            icon = Icons.Default.Link,
            title = stringResource(R.string.refresh_iptv),
            subtitle = refreshSubtitle,
            value = if (isLoading) "LOADING" else "REFRESH",
            isFocused = focusedIndex == playlists.size + 1,
            onClick = onRefresh,
            modifier = Modifier.settingsFocusSlot(playlists.size + 1)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsRow(
            icon = Icons.Default.Delete,
            title = stringResource(R.string.delete_iptv),
            subtitle = if (playlists.isEmpty()) "No playlists configured" else "Remove playlists, EPG and favorites",
            value = if (playlists.isEmpty()) "EMPTY" else "DELETE",
            isFocused = focusedIndex == playlists.size + 2,
            onClick = onDelete,
            modifier = Modifier.settingsFocusSlot(playlists.size + 2)
        )

        if (isLoading && !progressText.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
            text = "$progressText (${progressPercent.coerceIn(0, 100)}%)",
                style = ArflixTypography.caption,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(999.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progressPercent.coerceIn(0, 100) / 100f)
                        .background(Pink, RoundedCornerShape(999.dp))
                )
            }
        }

        if (!statusMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            val statusColor = when (statusType) {
                ToastType.SUCCESS -> SuccessGreen
                ToastType.ERROR -> Color(0xFFFF8A8A)
                ToastType.INFO -> TextSecondary
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = statusColor.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = statusColor.copy(alpha = 0.35f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Text(
                    text = statusMessage,
                    style = ArflixTypography.caption,
                    color = statusColor
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String = "",
    value: String,
    isFocused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRingColor = resolveFocusBorderColor(fallback = Pink)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
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
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        style = ArflixTypography.caption,
                        color = TextSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        
        Text(
            text = value.uppercase(),
            style = ArflixTypography.label,
            color = Pink,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsToggleRow(
    title: String,
    subtitle: String,
    isEnabled: Boolean,
    isFocused: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRingColor = resolveFocusBorderColor(fallback = Pink)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onToggle(!isEnabled) }
            )
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                style = ArflixTypography.caption,
                color = TextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        // Custom toggle indicator instead of Switch
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(26.dp)
                .background(
                    color = if (isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(13.dp)
                )
                .padding(3.dp),
            contentAlignment = if (isEnabled) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
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
                            label = "Search Lists",
                            value = query,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = "Search Lists",
                            onClick = { editingInput = CatalogDiscoveryInputTarget.Search }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DiscoveryActionButton(
                                label = if (isSearching) "Searching" else "Search",
                                onClick = { submitSearch() },
                                highlighted = true,
                                enabled = !isSearching && query.isNotBlank(),
                                modifier = Modifier.weight(1f)
                            )
                            DiscoveryActionButton(
                                label = "Close",
                                onClick = onDismiss,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CatalogDiscoveryInputButton(
                            label = "Paste URL",
                            value = manualUrl,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = "Trakt or MDBList URL",
                            onClick = { editingInput = CatalogDiscoveryInputTarget.ManualUrl }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        DiscoveryActionButton(
                            label = "Add URL",
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
                            label = "Search Lists",
                            value = query,
                            modifier = Modifier.weight(1f),
                            placeholder = "Search Lists",
                            onClick = { editingInput = CatalogDiscoveryInputTarget.Search }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        DiscoveryActionButton(
                            label = if (isSearching) "Searching" else "Search",
                            onClick = { submitSearch() },
                            highlighted = true,
                            enabled = !isSearching && query.isNotBlank()
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        CatalogDiscoveryInputButton(
                            label = "Paste URL",
                            value = manualUrl,
                            modifier = Modifier.weight(1f),
                            placeholder = "Trakt or MDBList URL",
                            onClick = { editingInput = CatalogDiscoveryInputTarget.ManualUrl }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        DiscoveryActionButton(
                            label = "Add URL",
                            onClick = onManualAdd,
                            enabled = manualUrl.isNotBlank()
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        DiscoveryActionButton(
                            label = "Close",
                            onClick = onDismiss
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (isCompact) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = when {
                                isSearching -> "Searching both sources"
                                results.isNotEmpty() -> "${results.size} lists found"
                                else -> "Searches Trakt and MDBList automatically"
                            },
                            style = ArflixTypography.caption,
                            color = TextSecondary
                        )
                        Text(
                            text = "Tap a field to edit",
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
                            isSearching -> "Searching both sources"
                            results.isNotEmpty() -> "${results.size} lists found"
                            else -> "Searches Trakt and MDBList automatically"
                        },
                        style = ArflixTypography.caption,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Press Select on a field to edit",
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
                                Text("Searching lists", color = TextSecondary, style = ArflixTypography.body)
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
                                    text = "Start with a search",
                                    color = TextPrimary,
                                    style = ArflixTypography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Try top trending anime, best horror, Marvel chronological, or 2025 movies.",
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
            title = if (target == CatalogDiscoveryInputTarget.Search) "Search Lists" else "Paste catalog URL",
            initialValue = if (target == CatalogDiscoveryInputTarget.Search) query else manualUrl,
            placeholder = if (target == CatalogDiscoveryInputTarget.Search) "Search Lists" else "https://trakt.tv/users/...",
            confirmLabel = if (target == CatalogDiscoveryInputTarget.Search) "Use Search" else "Use URL",
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
    val inputFocusColor = resolveFocusBorderColor(fallback = Color.White)
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
                        label = "Cancel",
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    DiscoveryActionButton(label = "Cancel", onClick = onDismiss)
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
    val compactFocusColor = resolveFocusBorderColor(fallback = Color.White)
    val creator = result.creatorName ?: result.creatorHandle
    val creatorMeta = creator?.let { "by $it" }
    val itemCountMeta = result.itemCount?.let { "$it items" }
    val likesMeta = result.likes?.let { "$it likes" } ?: "0 likes"
    val updatedMeta = result.updatedAt?.let { "Updated ${formatCatalogDiscoveryDate(it)}" }

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

    val resultFocusColor = resolveFocusBorderColor(fallback = Color.White)
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
                text = if (isAdded) "Added" else "Add",
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

private fun sourceLabel(sourceType: CatalogSourceType): String {
    return when (sourceType) {
        CatalogSourceType.TRAKT -> "Trakt"
        CatalogSourceType.MDBLIST -> "MDBList"
        CatalogSourceType.PREINSTALLED -> "Built-in"
        CatalogSourceType.ADDON -> "Addon"
        CatalogSourceType.HOME_SERVER -> "Home Server"
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

    Column {
        if (!isMobile) {
            Text(
                text = stringResource(R.string.catalogs),
                style = ArflixTypography.sectionTitle,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        } else if (selectionMode) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${selectedIds.size} ${stringResource(R.string.selected)}",
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.weight(1f))
                if (selectedIds.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                selectedIds.forEach { id ->
                                    val cat = catalogs.find { it.id == id }
                                    if (cat != null) onDeleteCatalog(cat)
                                }
                                selectionMode = false
                                selectedIds = emptySet()
                            }
                            .background(Color(0xFFDC2626), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete), tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            selectionMode = false
                            selectedIds = emptySet()
                        }
                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close), tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
        }
        Text(
            text = stringResource(R.string.catalogs),
            style = ArflixTypography.caption,
            color = TextSecondary.copy(alpha = 0.65f),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        SettingsRow(
            icon = Icons.Default.Add,
            title = stringResource(R.string.add_catalog),
            subtitle = stringResource(R.string.add_catalog_desc),
            value = "ADD",
            isFocused = focusedIndex == 0,
            onClick = onAddCatalog,
            modifier = Modifier.settingsFocusSlot(0)
        )

        Spacer(modifier = Modifier.height(16.dp))

        catalogs.forEachIndexed { index, catalog ->
            val rowFocusIndex = index + 1
            val isRowFocused = focusedIndex == rowFocusIndex
            val title = if (catalog.isPreinstalled) {
                when (catalog.kind) {
                    CatalogKind.COLLECTION -> "${catalog.title} (Built-in Collection)"
                    CatalogKind.COLLECTION_RAIL -> "${catalog.title} (Built-in Rail)"
                    else -> "${catalog.title} (Built-in)"
                }
            } else catalog.title
            val subtitle = when {
                catalog.kind == CatalogKind.COLLECTION_RAIL -> {
                    val group = catalog.collectionGroup?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "Collection"
                    "$group rail"
                }
                catalog.kind == CatalogKind.COLLECTION -> {
                    val group = catalog.collectionGroup?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "Collection"
                    "$group collection"
                }
                catalog.sourceType == CatalogSourceType.PREINSTALLED -> "Preinstalled catalog"
                else -> when (catalog.sourceType) {
                CatalogSourceType.ADDON -> {
                    val addonLabel = catalog.addonName?.takeIf { it.isNotBlank() } ?: "Addon"
                    "From $addonLabel"
                }
                CatalogSourceType.HOME_SERVER -> "From Home Server"
                    else -> catalog.sourceUrl ?: "Custom catalog"
                }
            }

            val isSelected = selectedIds.contains(catalog.id)
            val layoutToggleEnabled = catalog.kind != CatalogKind.COLLECTION_RAIL
            val layoutRowKey = remember(catalog.id, catalog.kind) { catalogueLayoutRowKey(catalog) }
            Row(
                modifier = Modifier
                    .settingsFocusSlot(rowFocusIndex)
                    .fillMaxWidth()
                    .background(
                        if (isSelected) Pink.copy(alpha = 0.2f)
                        else if (isRowFocused) Color.White.copy(alpha = 0.08f) 
                        else Color.Transparent,
                        RoundedCornerShape(12.dp)
                    )
                    .then(
                        if (isMobile) {
                            Modifier.combinedClickable(
                                onClick = {
                                    if (selectionMode) {
                                        selectedIds = if (isSelected) selectedIds - catalog.id else selectedIds + catalog.id
                                        if (selectedIds.isEmpty()) selectionMode = false
                                    } else {
                                        onRenameCatalog(catalog)
                                    }
                                },
                                onLongClick = {
                                    if (!selectionMode) {
                                        selectionMode = true
                                        selectedIds = setOf(catalog.id)
                                    }
                                }
                            )
                        } else {
                            Modifier.clickable { onRenameCatalog(catalog) }
                        }
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isMobile && selectionMode) {
                    Icon(
                        imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = if (isSelected) Pink else TextSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = ArflixTypography.body,
                        color = if (isRowFocused || isSelected) TextPrimary else TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (isMobile) {
                    if (!selectionMode) {
                        CatalogueRowLayoutToggleButton(
                            rowKey = layoutRowKey,
                            enabled = layoutToggleEnabled
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    if (selectionMode && selectedIds.size == 1 && isSelected) {
                        Icon(
                            imageVector = Icons.Default.DragHandle,
                            contentDescription = "Drag to reorder",
                            tint = TextSecondary,
                            modifier = Modifier
                                .size(24.dp)
                                .pointerInput(catalog.id) {
                                    var dragOffset = 0f
                                    val itemHeight = 64.dp.toPx()
                                    detectVerticalDragGestures(
                                        onDragEnd = { dragOffset = 0f },
                                        onDragCancel = { dragOffset = 0f }
                                    ) { change, dragAmount ->
                                        change.consume()
                                        dragOffset += dragAmount
                                        if (dragOffset > itemHeight) {
                                            onMoveCatalogDown(catalog)
                                            dragOffset -= itemHeight
                                        } else if (dragOffset < -itemHeight) {
                                            onMoveCatalogUp(catalog)
                                            dragOffset += itemHeight
                                        }
                                    }
                                }
                        )
                    }
                } else {
                    CatalogActionChip(
                        icon = Icons.Default.Edit,
                        isFocused = isRowFocused && focusedActionIndex == 0,
                        onClick = { onRenameCatalog(catalog) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.ArrowUpward,
                        isFocused = isRowFocused && focusedActionIndex == 1,
                        onClick = { onMoveCatalogUp(catalog) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.ArrowDownward,
                        isFocused = isRowFocused && focusedActionIndex == 2,
                        onClick = { onMoveCatalogDown(catalog) }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogueRowLayoutToggleButton(
                        rowKey = layoutRowKey,
                        enabled = layoutToggleEnabled,
                        forceFocused = isRowFocused && focusedActionIndex == 3
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    CatalogActionChip(
                        icon = Icons.Default.Delete,
                        isFocused = isRowFocused && focusedActionIndex == 4,
                        isDestructive = true,
                        enabled = true,
                        onClick = { onDeleteCatalog(catalog) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
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
    var isPressed by remember { mutableStateOf(false) }
    val visualActive = isFocused || isPressed
    val bgColor = when {
        !enabled -> Color.Black.copy(alpha = 0.4f)
        visualActive && isDestructive -> Color(0xFFDC2626)
        visualActive -> Color.White
        else -> Color.White.copy(alpha = 0.08f)
    }
    val fgColor = when {
        !enabled -> Color.White.copy(alpha = 0.5f)
        visualActive && isDestructive -> Color.White
        visualActive -> Color.Black
        else -> Color.White.copy(alpha = 0.7f)
    }
    Box(
        modifier = Modifier
            .size(36.dp)
            .clickable(enabled = enabled, onClick = onClick)
            .background(bgColor, RoundedCornerShape(8.dp))
            .border(
                width = if (visualActive) 1.5.dp else 1.dp,
                color = if (visualActive) Color.White.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.15f),
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
    onDeleteAddon: (String) -> Unit = {},
    onAddCustomAddon: () -> Unit = {}
) {
    Column {
        Text(
            text = "STREMIO ADDONS",
            style = ArflixTypography.caption.copy(fontSize = 12.sp, letterSpacing = 1.sp),
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        if (addons.isEmpty()) {
            Text(
                text = "No addons installed",
                style = ArflixTypography.body,
                color = TextSecondary
            )
        } else {
            addons.forEachIndexed { index, addon ->
                val canDelete = !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
                AddonRow(
                    addon = addon,
                    isFocused = focusedIndex == index,
                    focusedAction = if (focusedIndex == index) focusedActionIndex else -1,
                    canDelete = canDelete,
                    onToggle = { onToggleAddon(addon.id) },
                    onDelete = { onDeleteAddon(addon.id) },
                    modifier = Modifier.settingsFocusSlot(index)
                )
                if (index < addons.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add custom addon button
        Row(
            modifier = Modifier
                .settingsFocusSlot(addons.size)
                .fillMaxWidth()
                .clickable(onClick = onAddCustomAddon)
                .background(
                    if (focusedIndex == addons.size) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                    RoundedCornerShape(12.dp)
                )
                .border(
                    width = if (focusedIndex == addons.size) 2.dp else 0.dp,
                    color = if (focusedIndex == addons.size) Pink else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Widgets,
                contentDescription = null,
                tint = Pink,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Add Addon",
                style = ArflixTypography.button,
                color = Pink
            )
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
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isToggleFocused = isFocused && focusedAction == 0
    val isDeleteFocused = canDelete && isFocused && focusedAction == 1
    val isEnabled = addon.isEnabled
    val focusRingColor = resolveFocusBorderColor(fallback = Pink)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
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
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = addon.description,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AddonStatusChip(
                        text = "Installed",
                        background = Color(0xFF2563EB).copy(alpha = 0.18f),
                        textColor = Color(0xFF93C5FD)
                    )
                    AddonStatusChip(
                        text = if (isEnabled) "Enabled" else "Disabled",
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
                        .width(48.dp)
                        .height(26.dp)
                        .background(
                            color = if (isEnabled) SuccessGreen else Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(13.dp)
                        )
                        .padding(3.dp),
                    contentAlignment = if (isEnabled) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                }
            }

            if (canDelete) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { onDelete() }
                        .background(
                            color = if (isDeleteFocused) Color(0xFFEF4444) else Color.White.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (isDeleteFocused) 2.dp else 0.dp,
                            color = if (isDeleteFocused) Color.White else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete addon",
                        tint = if (isDeleteFocused) Color.White else TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
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
    onOpenDataDeletion: () -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.accounts),
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        AccountRow(
            name = "ARVIO Cloud",
            description = cloudEmail ?: "Optional account for syncing profiles, addons, catalogs and IPTV settings",
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
            description = "Sync watch history, progress, and watchlist",
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

        SettingsActionRow(
            title = stringResource(R.string.force_cloud_sync),
            description = if (isForceCloudSyncing) {
                "Syncing local and cloud state now"
            } else if (isCloudAuthenticated) {
                "Upload local state, then restore from cloud now"
            } else {
                "Sign in to ARVIO Cloud to force sync"
            },
            actionLabel = if (isForceCloudSyncing) "SYNCING" else "SYNC",
            isFocused = focusedIndex == 2,
            onClick = { if (!isForceCloudSyncing) onForceCloudSync() },
            modifier = Modifier.settingsFocusSlot(2)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = stringResource(R.string.app_update),
            description = when {
                !isSelfUpdateSupported -> "This install is managed by the Play Store"
                updateStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall -> "Latest update downloaded and ready to install"
                updateStatus is com.arflix.tv.updater.UpdateStatus.Checking -> "Checking GitHub Releases for a newer APK"
                updateStatus is com.arflix.tv.updater.UpdateStatus.UpdateAvailable -> "Update available: ${updateStatus.update.title.ifBlank { updateStatus.update.tag }}"
                updateStatus is com.arflix.tv.updater.UpdateStatus.Success -> "You already have the latest ARVIO version"
                else -> "Check GitHub Releases for the latest ARVIO APK"
            },
            actionLabel = when {
                !isSelfUpdateSupported -> "PLAY"
                updateStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall -> "INSTALL"
                updateStatus is com.arflix.tv.updater.UpdateStatus.Checking -> "CHECKING"
                updateStatus is com.arflix.tv.updater.UpdateStatus.UpdateAvailable -> "UPDATE"
                else -> "CHECK"
            },
            isFocused = focusedIndex == 3,
            onClick = {
                if (updateStatus is com.arflix.tv.updater.UpdateStatus.ReadyToInstall) onInstallUpdate() else onCheckUpdates()
            },
            modifier = Modifier.settingsFocusSlot(3)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = "Privacy and data deletion",
            description = "Open privacy and ARVIO Cloud account deletion instructions",
            actionLabel = "OPEN",
            isFocused = focusedIndex == 4,
            onClick = onOpenDataDeletion,
            modifier = Modifier.settingsFocusSlot(4)
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
    val focusRingColor = resolveFocusBorderColor(fallback = Pink)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = description,
                style = ArflixTypography.caption,
                color = TextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    if (isEnabled) Pink.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.1f),
                    RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(
                imageVector = if (isEnabled) Icons.Default.LinkOff else Icons.Default.Link,
                contentDescription = null,
                tint = if (isEnabled) Pink else TextSecondary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = actionLabel.uppercase(),
                style = ArflixTypography.label,
                color = if (isEnabled) Pink else TextSecondary
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
    val focusRingColor = resolveFocusBorderColor(fallback = Pink)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = ArflixTypography.cardTitle,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = description,
                style = ArflixTypography.caption,
                color = TextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    Pink.copy(alpha = 0.2f),
                    RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Pink,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = actionLabel.uppercase(),
                style = ArflixTypography.label,
                color = Pink
            )
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
    val focusRingColor = resolveFocusBorderColor(fallback = Pink)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = !isWorking) {
                if (isConnected) onDisconnect() else onConnect()
            }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) focusRingColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = ArflixTypography.cardTitle,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            if (isConnected) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(SuccessGreen.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SuccessGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.connected).uppercase(),
                        style = ArflixTypography.label,
                        color = SuccessGreen
                    )
                }
            } else if (isWorking) {
                LoadingIndicator(
                    color = Pink,
                    size = 24.dp,
                    strokeWidth = 2.dp
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Pink.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = null,
                        tint = Pink,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.connect).uppercase(),
                        style = ArflixTypography.label,
                        color = Pink
                    )
                }
            }
        }
        
        // Show expiration date when connected
        if (isConnected && expirationText != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = expirationText,
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.7f)
            )
        }

        // Show auth code when polling
        if (!isConnected && isWorking && !authCode.isNullOrBlank() && !authUrl.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Go to: $authUrl",
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.enter_code),
                    style = ArflixTypography.caption,
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
                style = ArflixTypography.caption,
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
    val isSecret: Boolean = false,
    val onValueChange: (String) -> Unit
)

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
                                text = "Enter ${field.label.lowercase()}...",
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
                                color = if (isFocused) resolveFocusBorderColor(fallback = Pink) else Color.White.copy(alpha = 0.2f),
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
            val pasteFocusRingColor = resolveFocusBorderColor(fallback = Pink)
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
                    contentDescription = "Paste",
                    tint = if (isPasteFocused) Pink else TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Paste from Clipboard",
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
                text = "Press Enter to select • Navigate with D-pad",
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
    val totalItems = fields.size + 3 // inputs + paste + cancel + confirm
    val formMaxHeight = when {
        fields.size >= 5 -> 280.dp
        fields.size >= 4 -> 260.dp
        else -> 290.dp
    }

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val view = LocalView.current
    val modalFocusRequester = remember { FocusRequester() }
    val formScrollState = rememberScrollState()

    val editTextRefs = remember { MutableList<EditText?>(fields.size) { null } }

    fun anyEditTextFocused(): Boolean = editTextRefs.any { it?.hasFocus() == true }

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
    // Scroll proportionally so the active field sits roughly in the middle of the viewport.
    LaunchedEffect(focusedIndex) {
        if (focusedIndex in 0 until fields.size) {
            val approxFieldHeightPx = 260 // rough pixels per field at typical density
            val targetScroll = (focusedIndex * approxFieldHeightPx).coerceAtLeast(0)
            runCatching { formScrollState.animateScrollTo(targetScroll) }
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
                        if (LocalDeviceType.current.isTouchDevice()) Modifier.fillMaxWidth(0.92f).widthIn(max = 600.dp)
                        else Modifier.width(560.dp)
                    )
                    .heightIn(max = 560.dp)
                    .background(BackgroundElevated, RoundedCornerShape(14.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(14.dp))
                    .padding(horizontal = if (LocalDeviceType.current.isTouchDevice()) 16.dp else 20.dp, vertical = 18.dp)
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
                                            val clipboardText = clipboardManager.getText()?.text
                                            // Paste into the URL/server/host field when present,
                                            // otherwise fall back to the historical second field.
                                            val targetIndex = fields.indexOfFirst { field ->
                                                field.label.contains("url", ignoreCase = true) ||
                                                    field.label.contains("server", ignoreCase = true) ||
                                                    field.label.contains("host", ignoreCase = true)
                                            }.takeIf { it >= 0 } ?: if (fields.size > 1) 1 else 0
                                            val target = fields.getOrNull(targetIndex)
                                            if (clipboardText != null && target != null) {
                                                target.onValueChange(clipboardText)
                                                // Also update the EditText directly to keep in sync
                                                editTextRefs.getOrNull(targetIndex)?.let { edit ->
                                                    edit.setText(clipboardText)
                                                    edit.clearFocus()
                                                }
                                            }
                                            // Ensure Compose focus stays on the modal for D-pad nav
                                            modalFocusRequester.requestFocus()
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
                    text = "Use D-pad to move, press OK to edit a field",
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
                        .heightIn(max = formMaxHeight)
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

                            val regexFieldFocusColor = resolveFocusBorderColor(fallback = Pink)
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
                                            isSingleLine = true
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
                                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
                                            } else {
                                                InputType.TYPE_CLASS_TEXT
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
                            val clipboardText = clipboardManager.getText()?.text
                            // Paste into the URL field (index 1) when available,
                            // otherwise fall back to the first field.
                            val targetIndex = if (fields.size > 1) 1 else 0
                            val target = fields.getOrNull(targetIndex)
                            if (clipboardText != null && target != null) {
                                target.onValueChange(clipboardText)
                                editTextRefs.getOrNull(targetIndex)?.let { edit ->
                                    edit.setText(clipboardText)
                                }
                            }
                        }
                        .padding(vertical = 11.dp, horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentPaste,
                        contentDescription = "Paste",
                        tint = if (isPasteFocused) Color.Black else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Paste from Clipboard",
                        style = ArflixTypography.button,
                        color = if (isPasteFocused) Color.Black else Color.White
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
                    text = if (LocalDeviceType.current.isTouchDevice()) "Tap a field to edit, tap Confirm when done" else "OK: edit/select \u2022 Back: close keyboard first",
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
                        val optionFocusColor = resolveFocusBorderColor(fallback = Pink)
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
                    text = "Press Enter to select",
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
                    text = "Change UI Mode",
                    style = ArflixTypography.sectionTitle,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(16.dp))

                val modeString = when (nextMode) {
                    "tv" -> "TV"
                    "tablet" -> "Tablet"
                    "phone" -> "Phone"
                    else -> "Auto"
                }

                Text(
                    text = "Are you sure you want to change the UI mode to $modeString?",
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
