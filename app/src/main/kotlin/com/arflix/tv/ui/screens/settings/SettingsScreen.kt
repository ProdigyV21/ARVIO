package com.arflix.tv.ui.screens.settings

import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import com.arflix.tv.BuildConfig
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Subtitles
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HighQuality
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.QrCodeImage
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
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
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import kotlin.math.abs

/**
 * Settings screen
 */
@OptIn(ExperimentalTvMaterial3Api::class)
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
    val uiState by viewModel.uiState.collectAsState()
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()

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
    var contentFocusIndex by remember { mutableIntStateOf(0) }
    var activeZone by remember { mutableStateOf(Zone.CONTENT) }
    var suppressSelectUntilMs by remember { mutableLongStateOf(0L) }

    // Sub-focus for addon rows: 0 = toggle, 1 = delete
    var addonActionIndex by remember { mutableIntStateOf(0) }
    // Sub-focus for catalog rows: 0 = edit, 1 = up, 2 = down, 3 = delete
    var catalogActionIndex by remember { mutableIntStateOf(0) }
    // Rename dialog state
    var showCatalogRename by remember { mutableStateOf(false) }
    var renameCatalogId by remember { mutableStateOf("") }
    var renameCatalogTitle by remember { mutableStateOf("") }

    // Input modal states
    var showCustomAddonInput by remember { mutableStateOf(false) }
    var customAddonUrl by remember { mutableStateOf("") }
    var showIptvInput by remember { mutableStateOf(false) }
    var showStalkerInput by remember { mutableStateOf(false) }
    var stalkerPortalUrlInput by remember { mutableStateOf("") }
    var stalkerMacInput by remember { mutableStateOf("") }
    var iptvM3uUrl by remember { mutableStateOf(uiState.iptvM3uUrl) }
    var iptvEpgUrl by remember { mutableStateOf(uiState.iptvEpgUrl) }
    var iptvXtreamUsername by remember { mutableStateOf("") }
    var iptvXtreamPassword by remember { mutableStateOf("") }
    var showCatalogInput by remember { mutableStateOf(false) }
    var catalogInputUrl by remember { mutableStateOf("") }
    var showSubtitlePicker by remember { mutableStateOf(false) }
    var subtitlePickerIndex by remember { mutableIntStateOf(0) }
    var showAudioLanguagePicker by remember { mutableStateOf(false) }
    var audioLanguagePickerIndex by remember { mutableIntStateOf(0) }
    var showDnsProviderPicker by remember { mutableStateOf(false) }
    var dnsProviderPickerIndex by remember { mutableIntStateOf(0) }
    var showContentLanguagePicker by remember { mutableStateOf(false) }
    var contentLanguagePickerIndex by remember { mutableIntStateOf(0) }
    var showUiModeWarningDialog by remember { mutableStateOf(false) }
    var nextUiMode by remember { mutableStateOf("") }

    val sections = remember { listOf("general", "iptv", "catalogs", "addons", "accounts") }

    val focusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()
    val openSubtitlePicker = {
        viewModel.refreshSubtitleOptions()
        val options = uiState.subtitleOptions
        subtitlePickerIndex = options.indexOfFirst { it.equals(uiState.defaultSubtitle, ignoreCase = true) }
            .coerceAtLeast(0)
        showSubtitlePicker = true
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
        suppressSelectUntilMs = SystemClock.elapsedRealtime() + 300L
    }

    LaunchedEffect(showSubtitlePicker, uiState.subtitleOptions) {
        if (showSubtitlePicker) {
            val options = uiState.subtitleOptions
            val maxIndex = (options.size - 1).coerceAtLeast(0)
            val targetIndex = options.indexOfFirst { it.equals(uiState.defaultSubtitle, ignoreCase = true) }
            subtitlePickerIndex = if (targetIndex >= 0) targetIndex else subtitlePickerIndex.coerceIn(0, maxIndex)
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
    
    // Reset content scroll when switching sections.
    LaunchedEffect(sectionIndex) {
        if (scrollState.value != 0) {
            scrollState.scrollTo(0)
        }
    }

    // Auto-scroll content to keep focused item visible in all sections.
    LaunchedEffect(contentFocusIndex, sectionIndex, activeZone, uiState.catalogs.size, uiState.addons.size) {
        if (activeZone != Zone.CONTENT) return@LaunchedEffect
        if (scrollState.maxValue <= 0) return@LaunchedEffect

        val maxIndex = when (sectionIndex) {
            0 -> 13 // General: 14 items
            1 -> 3 // IPTV: Configure + Refresh + Delete + Stalker
            2 -> uiState.catalogs.size // Catalogs
            3 -> uiState.addons.size // Addons
            4 -> 3 // Accounts
            else -> 0
        }.coerceAtLeast(1)

        val clampedFocus = contentFocusIndex.coerceIn(0, maxIndex)
        val ratio = clampedFocus.toFloat() / maxIndex.toFloat()
        val targetScroll = (scrollState.maxValue * ratio).toInt().coerceIn(0, scrollState.maxValue)
        if (abs(scrollState.value - targetScroll) > 24) {
            scrollState.animateScrollTo(targetScroll)
        }
    }

    LaunchedEffect(uiState.iptvM3uUrl, uiState.iptvEpgUrl, showIptvInput) {
        if (!showIptvInput) {
            iptvM3uUrl = uiState.iptvM3uUrl
            iptvEpgUrl = uiState.iptvEpgUrl
            iptvXtreamUsername = ""
            iptvXtreamPassword = ""
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
        showStalkerInput ||
        showCatalogInput ||
        showCatalogRename ||
        showSubtitlePicker ||
        showAudioLanguagePicker ||
        showDnsProviderPicker ||
        showContentLanguagePicker ||
        showUiModeWarningDialog ||
        uiState.showCloudPairDialog ||
        uiState.showCloudEmailPasswordDialog ||
        uiState.showAppUpdateDialog ||
        uiState.showUnknownSourcesDialog

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                    if (isTouchDevice) return@onPreviewKeyEvent false
                    if (hasBlockingModal) return@onPreviewKeyEvent false

                if (event.type == KeyEventType.KeyDown) {
                    val currentSection = sections.getOrNull(sectionIndex).orEmpty()
                    val focusedAddon = uiState.addons.getOrNull(contentFocusIndex)
                    val focusedAddonCanDelete = focusedAddon?.let {
                        !(it.id == "opensubtitles" && it.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
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
                                    if (currentSection == "addons" && contentFocusIndex < uiState.addons.size && addonActionIndex > 0) {
                                        addonActionIndex = 0
                                    } else if (currentSection == "catalogs" && contentFocusIndex > 0 && catalogActionIndex > 0) {
                                        catalogActionIndex--
                                    } else {
                                        activeZone = Zone.SECTION
                                        addonActionIndex = 0
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
                                    catalogActionIndex = 0
                                }
                                Zone.CONTENT -> {
                                    if (currentSection == "addons" &&
                                        contentFocusIndex in 0 until uiState.addons.size &&
                                        addonActionIndex < 1 &&
                                        focusedAddonCanDelete
                                    ) {
                                        addonActionIndex = 1
} else if (currentSection == "catalogs" && contentFocusIndex > 0 && catalogActionIndex < 3) {
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
                                        catalogActionIndex = 0
                                    }
                                }
                                Zone.CONTENT -> {
                                    // Dynamic max based on current section
                                    val maxIndex = when (sectionIndex) {
                                        0 -> 13 // General: 14 items
                                        1 -> 3 // IPTV: Configure + Refresh + Delete + Stalker
                                        2 -> uiState.catalogs.size // Catalogs: Add + N catalogs
                                        3 -> uiState.addons.size // Addons: N addons + "Add Custom" button
                                        4 -> 3 // Accounts: Cloud + Trakt + Switch Profile + App Update
                                        else -> 0
                                    }
                                    if (contentFocusIndex < maxIndex) {
                                        contentFocusIndex++
                                        addonActionIndex = 0 // Reset to toggle when changing rows
                                        catalogActionIndex = 0
                                    }
                                }
                            }
                            true
                        }
                        Key.Enter, Key.DirectionCenter -> {
                            if (SystemClock.elapsedRealtime() < suppressSelectUntilMs) {
                                return@onPreviewKeyEvent true
                            }
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
                                    when (sectionIndex) {
                                        0 -> { // General
                                            when (contentFocusIndex) {
                                                0 -> openContentLanguagePicker()
                                                1 -> openSubtitlePicker()
                                                2 -> openAudioLanguagePicker()
                                                3 -> viewModel.cycleSubtitleSize()
                                                4 -> viewModel.cycleSubtitleColor()
                                                5 -> viewModel.setAutoPlayNext(!uiState.autoPlayNext)
                                                6 -> viewModel.setAutoPlaySingleSource(!uiState.autoPlaySingleSource)
                                                7 -> viewModel.cycleAutoPlayMinQuality()
                                                8 -> viewModel.setTrailerAutoPlay(!uiState.trailerAutoPlay)
                                                9 -> viewModel.cycleFrameRateMatchingMode()
                                                10 -> viewModel.toggleCardLayoutMode()
                                                11 -> openUiModeWarningDialog()
                                                12 -> viewModel.setSkipProfileSelection(!uiState.skipProfileSelection)
                                                13 -> openDnsProviderPicker()
                                            }
                                        }
                                        1 -> { // IPTV
                                            when (contentFocusIndex) {
                                                0 -> {
                                                    showIptvInput = true
                                                }
                                                1 -> {
                                                    viewModel.refreshIptv(force = true)
                                                }
                                                2 -> {
                                                    viewModel.clearIptvConfig()
                                                }
                                                3 -> {
                                                    stalkerPortalUrlInput = uiState.iptvStalkerUrl
                                                    stalkerMacInput = uiState.iptvStalkerMac
                                                    showStalkerInput = true
                                                }
                                            }
                                        }
                                        2 -> { // Catalogs
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
                                                        else -> viewModel.removeCatalog(catalog.id)
                                                    }
                                                }
                                            }
                                        }
                                        3 -> { // Addons
                                            when {
                                                contentFocusIndex in 0 until uiState.addons.size -> {
                                                    val addon = uiState.addons[contentFocusIndex]
                                                    val canDelete = !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
                                                    if (addonActionIndex == 0 || !canDelete) {
                                                        // Toggle addon on/off
                                                        viewModel.toggleAddon(addon.id)
                                                    } else {
                                                        // Delete addon
                                                        viewModel.removeAddon(addon.id)
                                                        addonActionIndex = 0
                                                        // Adjust focus if we deleted the last addon item
                                                        if (contentFocusIndex >= uiState.addons.size && contentFocusIndex > 0) {
                                                            contentFocusIndex--
                                                        }
                                                    }
                                                }
                                                else -> {
                                                    // "Add Custom Addon" button
                                                    showCustomAddonInput = true
                                                }
                                            }
                                        }
                                        4 -> { // Accounts
                                            when (contentFocusIndex) {
                                                0 -> { // Cloud account
                                                    if (uiState.isLoggedIn) {
                                                        viewModel.logout()
                                                    } else {
                                                        viewModel.startCloudAuth()
                                                    }
                                                }
                                                1 -> { // Trakt
                                                    if (uiState.isTraktAuthenticated) {
                                                        viewModel.disconnectTrakt()
                                                    } else if (uiState.isTraktPolling) {
                                                        viewModel.cancelTraktAuth()
                                                    } else {
                                                        viewModel.startTraktAuth()
                                                    }
                                                }
                                                2 -> { // Switch Profile
                                                    onSwitchProfile()
                                                }
                                                3 -> { // App Update
                                                    if (uiState.downloadedApkPath != null) {
                                                        viewModel.installAppUpdateOrRequestPermission()
                                                    } else {
                                                        viewModel.checkForAppUpdates(force = true, showNoUpdateFeedback = true)
                                                    }
                                                }
                                            }
                                        }
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
                sections = sections,
                sectionIndex = sectionIndex,
                onSectionSelected = { index ->
                    sectionIndex = index
                    contentFocusIndex = 0
                },
                content = {
                    when (sections[sectionIndex]) {
                        "general" -> GeneralSettings(
                            defaultSubtitle = uiState.defaultSubtitle,
                            defaultAudioLanguage = uiState.defaultAudioLanguage,
                            contentLanguage = uiState.contentLanguage,
                            dnsProvider = uiState.dnsProvider,
                            cardLayoutMode = uiState.cardLayoutMode,
                            frameRateMatchingMode = uiState.frameRateMatchingMode,
                            autoPlayNext = uiState.autoPlayNext,
                            autoPlaySingleSource = uiState.autoPlaySingleSource,
                            autoPlayMinQuality = uiState.autoPlayMinQuality,
                            subtitleSize = uiState.subtitleSize,
                            subtitleColor = uiState.subtitleColor,
                            deviceModeOverride = uiState.deviceModeOverride,
                            skipProfileSelection = uiState.skipProfileSelection,
                            focusedIndex = -1,
                            onSubtitleClick = openSubtitlePicker,
                            onAudioLanguageClick = openAudioLanguagePicker,
                            onCardLayoutToggle = { viewModel.toggleCardLayoutMode() },
                            onFrameRateMatchingClick = { viewModel.cycleFrameRateMatchingMode() },
                            onDnsProviderClick = openDnsProviderPicker,
                            onAutoPlayToggle = { viewModel.setAutoPlayNext(it) },
                            onAutoPlaySingleSourceToggle = { viewModel.setAutoPlaySingleSource(it) },
                            onAutoPlayMinQualityClick = { viewModel.cycleAutoPlayMinQuality() },
                            trailerAutoPlay = uiState.trailerAutoPlay,
                            onTrailerAutoPlayToggle = { viewModel.setTrailerAutoPlay(it) },
                            onDeviceModeClick = openUiModeWarningDialog,
                            onContentLanguageClick = openContentLanguagePicker,
                            onSubtitleSizeClick = { viewModel.cycleSubtitleSize() },
                            onSkipProfileSelectionToggle = { viewModel.setSkipProfileSelection(it) },
                            onSubtitleColorClick = { viewModel.cycleSubtitleColor() }
                        )
                        "iptv" -> IptvSettings(
                            m3uUrl = uiState.iptvM3uUrl,
                            epgUrl = uiState.iptvEpgUrl,
                            channelCount = uiState.iptvChannelCount,
                            isLoading = uiState.isIptvLoading,
                            error = uiState.iptvError,
                            statusMessage = uiState.iptvStatusMessage,
                            statusType = uiState.iptvStatusType,
                            progressText = uiState.iptvProgressText,
                            progressPercent = uiState.iptvProgressPercent,
                            stalkerUrl = uiState.iptvStalkerUrl,
                            stalkerMac = uiState.iptvStalkerMac,
                            focusedIndex = -1,
                            onConfigure = { showIptvInput = true },
                            onConfigureStalker = { stalkerPortalUrlInput = uiState.iptvStalkerUrl; stalkerMacInput = uiState.iptvStalkerMac; showStalkerInput = true },
                            onRefresh = { viewModel.refreshIptv() },
                            onDelete = { viewModel.clearIptvConfig() }
                        )
                        "catalogs" -> CatalogsSettings(
                            catalogs = uiState.catalogs,
                            focusedIndex = -1,
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
                        "addons" -> AddonsSettings(
                            addons = uiState.addons,
                            focusedIndex = -1,
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
                            isTraktPolling = uiState.isTraktPolling,
                            isSelfUpdateSupported = uiState.isSelfUpdateSupported,
                            isCheckingForUpdate = uiState.isCheckingForUpdate,
                            isAppUpdateAvailable = uiState.isAppUpdateAvailable,
                            availableAppUpdate = uiState.availableAppUpdate,
                            downloadedApkPath = uiState.downloadedApkPath,
                            focusedIndex = -1,
                            onConnectCloud = { viewModel.openCloudEmailPasswordDialog() },
                            onDisconnectCloud = { viewModel.logout() },
                            onConnectTrakt = { viewModel.startTraktAuth() },
                            onCancelTrakt = { viewModel.cancelTraktAuth() },
                            onDisconnectTrakt = { viewModel.disconnectTrakt() },
                            onSwitchProfile = onSwitchProfile,
                            onCheckUpdates = { viewModel.checkForAppUpdates(force = true, showNoUpdateFeedback = true) },
                            onInstallUpdate = { viewModel.installAppUpdateOrRequestPermission() }
                        )
                    }
                }
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
                        .background(BackgroundDark)
                        .padding(vertical = 32.dp, horizontal = 24.dp)
                ) {
                    Text(
                        text = "Settings",
                        style = ArflixTypography.heroTitle.copy(fontSize = androidx.compose.ui.unit.TextUnit.Unspecified),
                        color = TextPrimary,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(bottom = 24.dp)
                    )
                    
                    sections.forEachIndexed { index, section ->
                        SettingsSectionItem(
                            icon = when (section) {
                                "general" -> Icons.Default.Settings
                                "iptv" -> Icons.Default.LiveTv
                                "catalogs" -> Icons.Default.Widgets
                                "addons" -> Icons.Default.Widgets
                                "accounts" -> Icons.Default.Person
                                else -> Icons.Default.Settings
                            },
                            title = section.replaceFirstChar { it.uppercase() },
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
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
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
                    when (sections[sectionIndex]) {
                        "general" -> GeneralSettings(
                            defaultSubtitle = uiState.defaultSubtitle,
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
                            deviceModeOverride = uiState.deviceModeOverride,
                            skipProfileSelection = uiState.skipProfileSelection,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            onSubtitleClick = openSubtitlePicker,
                            onAudioLanguageClick = openAudioLanguagePicker,
                            onCardLayoutToggle = { viewModel.toggleCardLayoutMode() },
                            onFrameRateMatchingClick = { viewModel.cycleFrameRateMatchingMode() },
                            onDnsProviderClick = openDnsProviderPicker,
                            onAutoPlayToggle = { viewModel.setAutoPlayNext(it) },
                            onAutoPlaySingleSourceToggle = { viewModel.setAutoPlaySingleSource(it) },
                            onAutoPlayMinQualityClick = { viewModel.cycleAutoPlayMinQuality() },
                            trailerAutoPlay = uiState.trailerAutoPlay,
                            onTrailerAutoPlayToggle = { viewModel.setTrailerAutoPlay(it) },
                            onDeviceModeClick = openUiModeWarningDialog,
                            onContentLanguageClick = openContentLanguagePicker,
                            onSkipProfileSelectionToggle = { viewModel.setSkipProfileSelection(it) },
                            onSubtitleSizeClick = { viewModel.cycleSubtitleSize() },
                            onSubtitleColorClick = { viewModel.cycleSubtitleColor() }
                        )
                        "iptv" -> IptvSettings(
                            m3uUrl = uiState.iptvM3uUrl,
                            epgUrl = uiState.iptvEpgUrl,
                            channelCount = uiState.iptvChannelCount,
                            isLoading = uiState.isIptvLoading,
                            error = uiState.iptvError,
                            statusMessage = uiState.iptvStatusMessage,
                            statusType = uiState.iptvStatusType,
                            progressText = uiState.iptvProgressText,
                            progressPercent = uiState.iptvProgressPercent,
                            stalkerUrl = uiState.iptvStalkerUrl,
                            stalkerMac = uiState.iptvStalkerMac,
                            focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                            onConfigure = { showIptvInput = true },
                            onConfigureStalker = { stalkerPortalUrlInput = uiState.iptvStalkerUrl; stalkerMacInput = uiState.iptvStalkerMac; showStalkerInput = true },
                            onRefresh = { viewModel.refreshIptv() },
                            onDelete = { viewModel.clearIptvConfig() }
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
                        "addons" -> AddonsSettings(
                            addons = uiState.addons,
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
                            isTraktPolling = uiState.isTraktPolling,
                            isSelfUpdateSupported = uiState.isSelfUpdateSupported,
                            isCheckingForUpdate = uiState.isCheckingForUpdate,
                            isAppUpdateAvailable = uiState.isAppUpdateAvailable,
                            availableAppUpdate = uiState.availableAppUpdate,
                            downloadedApkPath = uiState.downloadedApkPath,
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
                            onSwitchProfile = onSwitchProfile,
                            onCheckUpdates = { viewModel.checkForAppUpdates(force = true, showNoUpdateFeedback = true) },
                            onInstallUpdate = { viewModel.installAppUpdateOrRequestPermission() }
                        )
                    }
                }
            }
        }

        // Custom Addon Input Modal
        if (showCustomAddonInput) {
            InputModal(
                title = "Add Addon",
                fields = listOf(
                    InputField(label = "URL", value = customAddonUrl, onValueChange = { customAddonUrl = it })
                ),
                onConfirm = {
                    if (customAddonUrl.isNotBlank()) {
                        viewModel.addCustomAddon(customAddonUrl)
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

        if (showIptvInput) {
            InputModal(
                title = "Configure IPTV",
                fields = listOf(
                    InputField(
                        label = "M3U URL or Xtream Host",
                        value = iptvM3uUrl,
                        placeholder = "https://provider.host:port",
                        onValueChange = { iptvM3uUrl = it }
                    ),
                    InputField(
                        label = "Xtream Username (Optional)",
                        value = iptvXtreamUsername,
                        placeholder = "username",
                        onValueChange = { iptvXtreamUsername = it }
                    ),
                    InputField(
                        label = "Xtream Password (Optional)",
                        value = iptvXtreamPassword,
                        placeholder = "password",
                        isSecret = true,
                        onValueChange = { iptvXtreamPassword = it }
                    ),
                    InputField(
                        label = "EPG URL (Optional)",
                        value = iptvEpgUrl,
                        placeholder = "Leave empty to auto-derive for Xtream",
                        onValueChange = { iptvEpgUrl = it }
                    )
                ),
                onConfirm = {
                    viewModel.saveIptvConfigWithXtream(
                        sourceOrHost = iptvM3uUrl,
                        epgUrl = iptvEpgUrl,
                        xtreamUsername = iptvXtreamUsername,
                        xtreamPassword = iptvXtreamPassword
                    )
                    showIptvInput = false
                },
                onDismiss = {
                    showIptvInput = false
                }
            )
        }

        if (showStalkerInput) {
            InputModal(
                title = "Stalker Portal",
                fields = listOf(
                    InputField(
                        label = "Portal URL",
                        value = stalkerPortalUrlInput,
                        placeholder = "http://portal.example.com",
                        onValueChange = { stalkerPortalUrlInput = it }
                    ),
                    InputField(
                        label = "MAC Address",
                        value = stalkerMacInput,
                        placeholder = "00:1A:79:XX:XX:XX",
                        onValueChange = { stalkerMacInput = it }
                    )
                ),
                onConfirm = {
                    viewModel.saveStalkerConfig(stalkerPortalUrlInput, stalkerMacInput)
                    showStalkerInput = false
                },
                onDismiss = { showStalkerInput = false }
            )
        }

        if (showCatalogInput) {
            InputModal(
                title = "Add Catalog",
                fields = listOf(
                    InputField(label = "Catalog URL", value = catalogInputUrl, onValueChange = { catalogInputUrl = it })
                ),
                onConfirm = {
                    if (catalogInputUrl.isNotBlank()) {
                        viewModel.addCatalog(catalogInputUrl)
                        catalogInputUrl = ""
                        showCatalogInput = false
                    }
                },
                onDismiss = {
                    catalogInputUrl = ""
                    showCatalogInput = false
                }
            )
        }

        if (showCatalogRename) {
            InputModal(
                title = "Rename Catalog",
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

        if (showSubtitlePicker) {
            SubtitlePickerModal(
                title = "Default Subtitles",
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

        if (showAudioLanguagePicker) {
            SubtitlePickerModal(
                title = "Default Audio",
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
                title = "DNS Provider",
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
                title = "Content Language",
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

        if (uiState.showAppUpdateDialog) {
            AppUpdateModal(
                update = uiState.availableAppUpdate,
                isChecking = uiState.isCheckingForUpdate,
                isAppUpdateAvailable = uiState.isAppUpdateAvailable,
                isDownloading = uiState.isDownloadingAppUpdate,
                progress = uiState.appUpdateDownloadProgress,
                errorMessage = uiState.appUpdateError,
                downloadedApkPath = uiState.downloadedApkPath,
                isSelfUpdateSupported = uiState.isSelfUpdateSupported,
                onDismiss = { viewModel.dismissAppUpdateDialog() },
                onIgnore = { viewModel.ignoreAppUpdate() },
                onDownload = { viewModel.downloadAppUpdate() },
                onInstall = { viewModel.installAppUpdateOrRequestPermission() }
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
                                        1 -> 3
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
                            .background(BackgroundDark.copy(alpha = 0.92f), RoundedCornerShape(16.dp))
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
private fun MobileSettingsLayout(
    sections: List<String>,
    sectionIndex: Int,
    onSectionSelected: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    val mobileScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        // Title
        Text(
            text = "Settings",
            style = ArflixTypography.heroTitle.copy(fontSize = androidx.compose.ui.unit.TextUnit.Unspecified),
            color = TextPrimary,
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 12.dp)
        )

        // Horizontal section chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sections.forEachIndexed { index, section ->
                val isSelected = sectionIndex == index
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isSelected) Pink else Color.White.copy(alpha = 0.08f)
                        )
                        .clickable { onSectionSelected(index) }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = section.replaceFirstChar { it.uppercase() },
                        style = ArflixTypography.button,
                        color = if (isSelected) Color.Black else TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Content area — vertically scrollable
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(mobileScrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            content()
        }

        // Version text at bottom
        Text(
            text = "ARVIO V${BuildConfig.VERSION_NAME}",
            style = ArflixTypography.caption,
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 20.dp, bottom = 12.dp, top = 4.dp)
        )
    }
}

private enum class Zone {
    SIDEBAR, SECTION, CONTENT
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AppUpdateModal(
    update: com.arflix.tv.updater.AppUpdate?,
    isChecking: Boolean,
    isAppUpdateAvailable: Boolean,
    isDownloading: Boolean,
    progress: Float?,
    errorMessage: String?,
    downloadedApkPath: String?,
    isSelfUpdateSupported: Boolean,
    onDismiss: () -> Unit,
    onIgnore: () -> Unit,
    onDownload: () -> Unit,
    onInstall: () -> Unit
) {
    val primaryEnabled = downloadedApkPath != null || isAppUpdateAvailable
    var focusedIndex by remember(primaryEnabled) { mutableIntStateOf(if (primaryEnabled) 2 else 0) }
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
                        else Modifier.width(760.dp)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(18.dp))
                    .padding(if (LocalDeviceType.current.isTouchDevice()) 20.dp else 28.dp)
                    .focusRequester(focusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                        when (event.key) {
                            Key.Back, Key.Escape -> { onDismiss(); true }
                            Key.DirectionLeft -> {
                                focusedIndex = (focusedIndex - 1).coerceAtLeast(0)
                                true
                            }
                            Key.DirectionRight -> {
                                focusedIndex = (focusedIndex + 1).coerceAtMost(2)
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                when (focusedIndex) {
                                    0 -> onDismiss()
                                    1 -> onIgnore()
                                    2 -> if (primaryEnabled) {
                                        if (downloadedApkPath != null) onInstall() else onDownload()
                                    }
                                }
                                true
                            }
                            else -> false
                        }
                    }
            ) {
            Text("App Update", style = ArflixTypography.sectionTitle, color = TextPrimary)
            Spacer(modifier = Modifier.height(10.dp))

            val subtitle = when {
                !isSelfUpdateSupported -> "This install is managed by the Play Store."
                downloadedApkPath != null && update != null -> "${update.title} is ready to install."
                isAppUpdateAvailable && update != null -> "Update available: ${update.title} (${update.tag})"
                update != null -> "You already have the latest version installed."
                isChecking -> "Checking GitHub Releases..."
                else -> "No release information available."
            }
            Text(subtitle, style = ArflixTypography.body, color = TextSecondary)

            if (update != null && !isChecking && !isDownloading) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isAppUpdateAvailable) {
                        "Current version ${BuildConfig.VERSION_NAME} -> latest ${update.tag}"
                    } else {
                        "Current version ${BuildConfig.VERSION_NAME} is up to date"
                    },
                    style = ArflixTypography.caption,
                    color = TextSecondary.copy(alpha = 0.78f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!errorMessage.isNullOrBlank()) {
                Text(errorMessage, style = ArflixTypography.body, color = Pink)
                Spacer(modifier = Modifier.height(12.dp))
            }

            when {
                isDownloading -> {
                    Text("Downloading update...", style = ArflixTypography.body, color = TextPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    androidx.compose.material3.LinearProgressIndicator(
                        progress = progress ?: 0f,
                        modifier = Modifier.fillMaxWidth(),
                        color = SuccessGreen,
                        trackColor = Color.White.copy(alpha = 0.08f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = progress?.let { "${(it * 100).toInt()}%" } ?: "Preparing...",
                        style = ArflixTypography.caption,
                        color = TextSecondary
                    )
                }
                downloadedApkPath != null -> {
                    Text("The latest ARVIO update has been downloaded and is ready to install.", style = ArflixTypography.body, color = TextPrimary)
                }
                !update?.notes.isNullOrBlank() -> {
                    Text(
                        text = update!!.notes.take(900),
                        style = ArflixTypography.caption.copy(lineHeight = 18.sp),
                        color = TextSecondary,
                        modifier = Modifier.heightIn(max = 260.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                UpdateActionButton("Close", focusedIndex == 0, onDismiss)
                UpdateActionButton("Ignore", focusedIndex == 1, onIgnore)
                UpdateActionButton(
                    when {
                        downloadedApkPath != null -> "Install"
                        isAppUpdateAvailable -> "Download"
                        else -> "Latest"
                    },
                    focusedIndex == 2,
                    if (downloadedApkPath != null) onInstall else onDownload,
                    highlighted = true,
                    enabled = isSelfUpdateSupported && !isChecking && primaryEnabled
                )
            }
        }
        }
    }
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
    deviceModeOverride: String = "auto",
    skipProfileSelection: Boolean = false,
    focusedIndex: Int,
    onSubtitleClick: () -> Unit,
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
    trailerAutoPlay: Boolean = false,
    onSubtitleSizeClick: () -> Unit = {},
    onSubtitleColorClick: () -> Unit = {},
    onTrailerAutoPlayToggle: (Boolean) -> Unit = {}
) {
    Column {
        // ── Language & Subtitles ──
        Text(
            text = "Language & Subtitles",
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = "Content Language",
            subtitle = "Titles, descriptions and metadata",
            value = TMDB_LANGUAGES.firstOrNull { it.first == contentLanguage }?.second ?: contentLanguage,
            isFocused = focusedIndex == 0,
            onClick = onContentLanguageClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = "Default Subtitle",
            subtitle = "Auto-select subtitle language",
            value = defaultSubtitle,
            isFocused = focusedIndex == 1,
            onClick = onSubtitleClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.VolumeUp,
            title = "Default Audio",
            subtitle = "Preferred audio track",
            value = defaultAudioLanguage,
            isFocused = focusedIndex == 2,
            onClick = onAudioLanguageClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = "Subtitle Size",
            subtitle = "Text size for subtitles",
            value = subtitleSize,
            isFocused = focusedIndex == 3,
            onClick = onSubtitleSizeClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = "Subtitle Color",
            subtitle = "Text color for subtitles",
            value = subtitleColor,
            isFocused = focusedIndex == 4,
            onClick = onSubtitleColorClick
        )

        // ── Playback ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Playback",
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsToggleRow(
            title = "Auto-Play Next",
            subtitle = "Start next episode automatically",
            isEnabled = autoPlayNext,
            isFocused = focusedIndex == 5,
            onToggle = onAutoPlayToggle
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = "Auto-Play Single Source",
            subtitle = "Skip source picker with one source",
            isEnabled = autoPlaySingleSource,
            isFocused = focusedIndex == 6,
            onToggle = onAutoPlaySingleSourceToggle
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.HighQuality,
            title = "Auto-Play Min Quality",
            subtitle = "Min quality for auto-play",
            value = autoPlayMinQuality,
            isFocused = focusedIndex == 7,
            onClick = onAutoPlayMinQualityClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = "Trailer Auto-Play",
            subtitle = "Play trailers in hero banner",
            isEnabled = trailerAutoPlay,
            isFocused = focusedIndex == 8,
            onToggle = onTrailerAutoPlayToggle
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Movie,
            title = "Match Frame Rate",
            subtitle = "Off, Seamless, or Always",
            value = frameRateMatchingMode,
            isFocused = focusedIndex == 9,
            onClick = onFrameRateMatchingClick
        )

        // ── Interface ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Interface",
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.Widgets,
            title = "Card Layout",
            subtitle = "Landscape or poster cards",
            value = cardLayoutMode,
            isFocused = focusedIndex == 10,
            onClick = onCardLayoutToggle
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsRow(
            icon = Icons.Default.Settings,
            title = "UI Mode",
            subtitle = "Force TV, Tablet, or Phone",
            value = when (deviceModeOverride) {
                "tv" -> "TV"
                "tablet" -> "Tablet"
                "phone" -> "Phone"
                else -> "Auto"
            },
            isFocused = focusedIndex == 11,
            onClick = onDeviceModeClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsToggleRow(
            title = "Skip Profile Selection",
            subtitle = "Auto-load last used profile",
            isEnabled = skipProfileSelection,
            isFocused = focusedIndex == 12,
            onToggle = onSkipProfileSelectionToggle
        )

        // ── Network ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Network",
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.Language,
            title = "DNS Provider",
            subtitle = "Resolve API and stream requests",
            value = dnsProvider,
            isFocused = focusedIndex == 13,
            onClick = onDnsProviderClick
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun IptvSettings(
    m3uUrl: String,
    epgUrl: String,
    channelCount: Int,
    isLoading: Boolean,
    error: String?,
    statusMessage: String?,
    statusType: ToastType,
    progressText: String?,
    progressPercent: Int,
    focusedIndex: Int,
    stalkerUrl: String = "",
    stalkerMac: String = "",
    onConfigure: () -> Unit,
    onConfigureStalker: () -> Unit = {},
    onRefresh: () -> Unit,
    onDelete: () -> Unit
) {
    Column {
        Text(
            text = "IPTV",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        SettingsRow(
            icon = Icons.Default.LiveTv,
            title = "Playlist",
            subtitle = if (m3uUrl.isBlank()) "Set M3U URL (or Xtream host/user/pass) and optional EPG URL" else "Playlist configured",
            value = if (m3uUrl.isBlank()) "NOT SET" else "$channelCount CH",
            isFocused = focusedIndex == 0,
            onClick = onConfigure
        )

        Spacer(modifier = Modifier.height(16.dp))

        val refreshSubtitle = when {
            isLoading -> "Refreshing channels and EPG..."
            error != null -> error
            epgUrl.isBlank() -> "Reload playlist now"
            else -> "Reload playlist and EPG now"
        }
        SettingsRow(
            icon = Icons.Default.Link,
            title = "Refresh IPTV Data",
            subtitle = refreshSubtitle,
            value = if (isLoading) "LOADING" else "REFRESH",
            isFocused = focusedIndex == 1,
            onClick = onRefresh
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsRow(
            icon = Icons.Default.Delete,
            title = "Delete M3U Playlist",
            subtitle = if (m3uUrl.isBlank()) "No playlist configured" else "Remove M3U, EPG and favorites",
            value = if (m3uUrl.isBlank()) "EMPTY" else "DELETE",
            isFocused = focusedIndex == 2,
            onClick = onDelete
        )

        if (isLoading && !progressText.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "${progressText} (${progressPercent.coerceIn(0, 100)}%)",
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

        // ── Stalker Portal ──
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Stalker Portal (MAC)",
            style = ArflixTypography.caption.copy(fontSize = 11.sp, letterSpacing = 0.8.sp),
            color = TextSecondary.copy(alpha = 0.5f),
            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
        )

        SettingsRow(
            icon = Icons.Default.LiveTv,
            title = "Stalker Portal",
            subtitle = if (stalkerUrl.isBlank()) "Not configured" else stalkerUrl.take(40),
            value = if (stalkerUrl.isBlank()) "ADD" else "EDIT",
            isFocused = focusedIndex == 3,
            onClick = onConfigureStalker
        )

        if (stalkerMac.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "MAC: $stalkerMac",
                style = ArflixTypography.caption.copy(fontSize = 10.sp),
                color = TextSecondary.copy(alpha = 0.5f),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    value: String,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
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
                Text(
                    text = subtitle,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
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
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle(!isEnabled) }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
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
    Column {
        Text(
            text = "Catalogs",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = "Trakt/MDBList URLs can be added manually. Addon catalogs appear automatically.",
            style = ArflixTypography.caption,
            color = TextSecondary.copy(alpha = 0.65f),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        SettingsRow(
            icon = Icons.Default.Add,
            title = "Add Catalog",
            subtitle = "Import a Trakt or MDBList catalog URL",
            value = "ADD",
            isFocused = focusedIndex == 0,
            onClick = onAddCatalog
        )

        Spacer(modifier = Modifier.height(16.dp))

        catalogs.forEachIndexed { index, catalog ->
            val rowFocusIndex = index + 1
            val isRowFocused = focusedIndex == rowFocusIndex
            val title = if (catalog.isPreinstalled) "${catalog.title} (Built-in)" else catalog.title
            val subtitle = when (catalog.sourceType) {
                CatalogSourceType.PREINSTALLED -> "Preinstalled catalog"
                CatalogSourceType.ADDON -> {
                    val addonLabel = catalog.addonName?.takeIf { it.isNotBlank() } ?: "Addon"
                    "From $addonLabel"
                }
                else -> catalog.sourceUrl ?: "Custom catalog"
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (isRowFocused) Color.White.copy(alpha = 0.08f) else Color.Transparent,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = ArflixTypography.body,
                        color = if (isRowFocused) TextPrimary else TextSecondary,
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
                CatalogActionChip(
                    icon = Icons.Default.Delete,
                    isFocused = isRowFocused && focusedActionIndex == 3,
                    isDestructive = true,
                    enabled = true,
                    onClick = { onDeleteCatalog(catalog) }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
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
private fun AddonsSettings(
    addons: List<com.arflix.tv.data.model.Addon> = emptyList(),
    focusedIndex: Int = -1,
    focusedActionIndex: Int = 0,
    onToggleAddon: (String) -> Unit = {},
    onDeleteAddon: (String) -> Unit = {},
    onAddCustomAddon: () -> Unit = {}
) {
    Column {
        Text(
            text = "Manage Addons",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
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
                    onDelete = { onDeleteAddon(addon.id) }
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
                text = "Add Custom Addon",
                style = ArflixTypography.button,
                color = Pink
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun AddonRow(
    addon: com.arflix.tv.data.model.Addon,
    isFocused: Boolean,
    focusedAction: Int = -1, // 0 = toggle, 1 = delete
    canDelete: Boolean = true,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    val canToggle = !(addon.id == "opensubtitles" && addon.type == com.arflix.tv.data.model.AddonType.SUBTITLE)
    val isToggleFocused = canToggle && isFocused && focusedAction == 0
    val isDeleteFocused = canDelete && isFocused && focusedAction == 1
    val isEnabled = addon.isEnabled

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
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
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Toggle indicator with focus highlight
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
    isTraktPolling: Boolean,
    isSelfUpdateSupported: Boolean,
    isCheckingForUpdate: Boolean,
    isAppUpdateAvailable: Boolean,
    availableAppUpdate: com.arflix.tv.updater.AppUpdate?,
    downloadedApkPath: String?,
    focusedIndex: Int,
    onConnectCloud: () -> Unit,
    onDisconnectCloud: () -> Unit,
    onConnectTrakt: () -> Unit,
    onCancelTrakt: () -> Unit,
    onDisconnectTrakt: () -> Unit,
    onSwitchProfile: () -> Unit,
    onCheckUpdates: () -> Unit,
    onInstallUpdate: () -> Unit
) {
    Column {
        Text(
            text = "Linked Accounts",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        AccountRow(
            name = "ARVIO Cloud",
            description = cloudEmail ?: "Optional account for syncing profiles, addons, catalogs and IPTV settings",
            isConnected = isCloudAuthenticated,
            isPolling = false,
            authCode = null,
            authUrl = null,
            isFocused = focusedIndex == 0,
            onConnect = {
                onConnectCloud()
            },
            onDisconnect = onDisconnectCloud,
            expirationText = cloudHint
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Trakt.tv
        AccountRow(
            name = "Trakt.tv",
            description = "Sync watch history, progress, and watchlist",
            isConnected = isTraktAuthenticated,
            isPolling = isTraktPolling,
            authCode = traktCode,
            authUrl = traktUrl,
            isFocused = focusedIndex == 1,
            onConnect = { if (isTraktPolling) onCancelTrakt() else onConnectTrakt() },
            onDisconnect = onDisconnectTrakt,
            expirationText = null  // Don't show expiration - Trakt tokens auto-refresh
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Switch Profile
        SettingsActionRow(
            title = "Switch Profile",
            description = "Change to a different user profile",
            actionLabel = "SWITCH",
            isFocused = focusedIndex == 2,
            onClick = onSwitchProfile
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsActionRow(
            title = "App Updates",
            description = when {
                !isSelfUpdateSupported -> "This install is managed by the Play Store"
                downloadedApkPath != null -> "Latest update downloaded and ready to install"
                isCheckingForUpdate -> "Checking GitHub Releases for a newer APK"
                isAppUpdateAvailable -> "Update available: ${availableAppUpdate?.title ?: availableAppUpdate?.tag ?: "latest release"}"
                availableAppUpdate != null -> "You already have ARVIO v${BuildConfig.VERSION_NAME}"
                else -> "Check GitHub Releases for the latest ARVIO APK"
            },
            actionLabel = when {
                !isSelfUpdateSupported -> "PLAY"
                downloadedApkPath != null -> "INSTALL"
                isCheckingForUpdate -> "CHECKING"
                isAppUpdateAvailable -> "UPDATE"
                else -> "CHECK"
            },
            isFocused = focusedIndex == 3,
            onClick = {
                if (downloadedApkPath != null) onInstallUpdate() else onCheckUpdates()
            }
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
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
                text = actionLabel,
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
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
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
                text = actionLabel,
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
    isPolling: Boolean,
    authCode: String?,
    authUrl: String?,
    isFocused: Boolean,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    secondaryActionLabel: String? = null,
    expirationText: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isPolling) {
                if (isConnected) onDisconnect() else onConnect()
            }
            .background(
                if (isFocused) Color.White.copy(alpha = 0.1f) else BackgroundElevated,
                RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Pink else Color.Transparent,
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
                        text = "CONNECTED",
                        style = ArflixTypography.label,
                        color = SuccessGreen
                    )
                }
            } else if (isPolling) {
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
                        text = "CONNECT",
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
        if (!isConnected && isPolling && !authCode.isNullOrBlank() && !authUrl.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Go to: $authUrl",
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Enter code:",
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
                text = "Waiting for authorization... (Press OK to cancel)",
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
                                            if (clipboardText != null && fields.isNotEmpty()) {
                                                fields[0].onValueChange(clipboardText)
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
                                color = if (isFocused) Pink else Color.White.copy(alpha = 0.2f),
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (isPasteFocused) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = if (isPasteFocused) 2.dp else 0.dp,
                        color = if (isPasteFocused) Pink else Color.Transparent,
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
                        text = "Cancel",
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
                        text = "Confirm",
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
    fields: List<InputField>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var focusedIndex by remember(title, fields.size) { mutableIntStateOf(0) }
    val totalItems = fields.size + 3 // inputs + paste + cancel + confirm
    val formMaxHeight = if (fields.size >= 4) 360.dp else 290.dp

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
                                            val target = fields.firstOrNull()
                                            if (clipboardText != null && target != null) {
                                                target.onValueChange(clipboardText)
                                                // Also update the EditText directly to keep in sync
                                                editTextRefs.getOrNull(0)?.let { edit ->
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
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

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

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White.copy(alpha = if (isFocused) 0.12f else 0.05f), RoundedCornerShape(10.dp))
                                    .border(
                                        width = if (isFocused) 2.dp else 1.dp,
                                        color = if (isFocused) Pink else Color.White.copy(alpha = 0.2f),
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
                            val target = fields.firstOrNull()
                            if (clipboardText != null && target != null) {
                                target.onValueChange(clipboardText)
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
                            text = "Cancel",
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
                            text = "Confirm",
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
                    modifier = Modifier.heightIn(max = 360.dp)
                ) {
                    itemsIndexed(options) { index, option ->
                        val isFocused = index == safeIndex
                        val isSelected = option.equals(selected, ignoreCase = true)
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
                                    color = if (isFocused) Pink else Color.White.copy(alpha = 0.1f),
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
                            text = "Confirm",
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
                            text = "Cancel",
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
