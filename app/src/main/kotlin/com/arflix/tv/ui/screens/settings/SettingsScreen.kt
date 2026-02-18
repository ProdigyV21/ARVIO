package com.arflix.tv.ui.screens.settings

import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LinkOff
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Subtitles
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ArrowDownward
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.doAfterTextChanged
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.ui.components.Sidebar
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Settings screen
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    currentProfile: com.arflix.tv.data.model.Profile? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    var isSidebarFocused by remember { mutableStateOf(false) }
    val hasProfile = currentProfile != null
    val maxSidebarIndex = if (hasProfile) SidebarItem.entries.size else SidebarItem.entries.size - 1
    var sidebarFocusIndex by remember { mutableIntStateOf(if (hasProfile) 5 else 4) } // SETTINGS
    var sectionIndex by remember { mutableIntStateOf(0) }
    var contentFocusIndex by remember { mutableIntStateOf(0) }
    var activeZone by remember { mutableStateOf(Zone.CONTENT) }

    // Sub-focus for addon rows: 0 = toggle, 1 = delete
    var addonActionIndex by remember { mutableIntStateOf(0) }
    // Sub-focus for catalog rows: 0 = up, 1 = down, 2 = delete
    var catalogActionIndex by remember { mutableIntStateOf(0) }

    // Input modal states
    var showCustomAddonInput by remember { mutableStateOf(false) }
    var customAddonUrl by remember { mutableStateOf("") }
    var showIptvInput by remember { mutableStateOf(false) }
    var iptvM3uUrl by remember { mutableStateOf(uiState.iptvM3uUrl) }
    var iptvEpgUrl by remember { mutableStateOf(uiState.iptvEpgUrl) }
    var iptvXtreamUsername by remember { mutableStateOf("") }
    var iptvXtreamPassword by remember { mutableStateOf("") }
    var showCatalogInput by remember { mutableStateOf(false) }
    var catalogInputUrl by remember { mutableStateOf("") }
    var showSubtitlePicker by remember { mutableStateOf(false) }
    var subtitlePickerIndex by remember { mutableIntStateOf(0) }

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

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(showSubtitlePicker, uiState.subtitleOptions) {
        if (showSubtitlePicker) {
            val options = uiState.subtitleOptions
            val maxIndex = (options.size - 1).coerceAtLeast(0)
            val targetIndex = options.indexOfFirst { it.equals(uiState.defaultSubtitle, ignoreCase = true) }
            subtitlePickerIndex = if (targetIndex >= 0) targetIndex else subtitlePickerIndex.coerceIn(0, maxIndex)
        }
    }
    
    // Auto-scroll focused row using normalized position instead of fixed pixel heuristics.
    LaunchedEffect(contentFocusIndex, sectionIndex) {
        if (activeZone == Zone.CONTENT) {
            val maxIndex = when (sectionIndex) {
                0 -> 1 // General: 2 rows
                1 -> 2 // IPTV: configure + refresh + delete
                2 -> uiState.catalogs.size // Catalogs: add + list rows
                3 -> uiState.addons.size // Addons: list + add button
                4 -> 2 // Accounts: Cloud + Trakt + switch profile
                else -> 0
            }.coerceAtLeast(0)

            if (contentFocusIndex <= 0 || maxIndex == 0 || scrollState.maxValue <= 0) {
                scrollState.scrollTo(0)
            } else {
                val ratio = contentFocusIndex.toFloat() / maxIndex.toFloat()
                val targetScroll = (scrollState.maxValue * ratio).toInt()
                scrollState.scrollTo(targetScroll.coerceIn(0, scrollState.maxValue))
            }
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .focusRequester(focusRequester)
            .focusable()
            .onPreviewKeyEvent { event ->
                    // BLOCKER FIX: Ignore main screen navigation if modals are open
                    if (showCustomAddonInput || showSubtitlePicker || showIptvInput || showCatalogInput || uiState.showCloudPairDialog || uiState.showCloudEmailPasswordDialog) return@onPreviewKeyEvent false

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
                                    activeZone = Zone.SIDEBAR
                                    isSidebarFocused = true
                                }
                                else -> {}
                            }
                            true
                        }
                        Key.DirectionRight -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> {
                                    activeZone = Zone.SECTION
                                    isSidebarFocused = false
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
                                    } else if (currentSection == "catalogs" && contentFocusIndex > 0 && catalogActionIndex < 2) {
                                        catalogActionIndex++
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionUp -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> if (sidebarFocusIndex > 0) {
                                    sidebarFocusIndex = (sidebarFocusIndex - 1).coerceIn(0, maxSidebarIndex)
                                }
                                Zone.SECTION -> {
                                    if (sectionIndex > 0) {
                                        sectionIndex--
                                        contentFocusIndex = 0 // Reset content focus when changing section
                                        addonActionIndex = 0
                                        catalogActionIndex = 0
                                    }
                                }
                                Zone.CONTENT -> {
                                    if (contentFocusIndex > 0) {
                                        contentFocusIndex--
                                        addonActionIndex = 0 // Reset to toggle when changing rows
                                        catalogActionIndex = 0
                                    }
                                }
                            }
                            true
                        }
                        Key.DirectionDown -> {
                            when (activeZone) {
                                Zone.SIDEBAR -> if (sidebarFocusIndex < maxSidebarIndex) {
                                    sidebarFocusIndex = (sidebarFocusIndex + 1).coerceIn(0, maxSidebarIndex)
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
                                        0 -> 2 // General: 3 items (subtitle, frame-rate matching, auto-play)
                                        1 -> 2 // IPTV: Configure + Refresh + Delete
                                        2 -> uiState.catalogs.size // Catalogs: Add + N catalogs
                                        3 -> uiState.addons.size // Addons: N addons + "Add Custom" button
                                        4 -> 2 // Accounts: Cloud + Trakt + Switch Profile
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
                            when (activeZone) {
                                Zone.SIDEBAR -> {
                                    if (hasProfile && sidebarFocusIndex == 0) {
                                        onSwitchProfile()
                                    } else {
                                        val itemIndex = if (hasProfile) sidebarFocusIndex - 1 else sidebarFocusIndex
                                        when (SidebarItem.entries[itemIndex]) {
                                            SidebarItem.SEARCH -> onNavigateToSearch()
                                            SidebarItem.HOME -> onNavigateToHome()
                                            SidebarItem.TV -> onNavigateToTv()
                                            SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                            SidebarItem.SETTINGS -> { /* Already here */ }
                                        }
                                    }
                                }
                                Zone.SECTION -> activeZone = Zone.CONTENT
                                Zone.CONTENT -> {
                                    when (sectionIndex) {
                                        0 -> { // General
                                            when (contentFocusIndex) {
                                                0 -> openSubtitlePicker()
                                                1 -> viewModel.cycleFrameRateMatchingMode()
                                                2 -> viewModel.setAutoPlayNext(!uiState.autoPlayNext)
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
                                            }
                                        }
                                        2 -> { // Catalogs
                                            if (contentFocusIndex == 0) {
                                                showCatalogInput = true
                                            } else {
                                                val catalog = uiState.catalogs.getOrNull(contentFocusIndex - 1)
                                                if (catalog != null) {
                                                    when (catalogActionIndex) {
                                                        0 -> viewModel.moveCatalogUp(catalog.id)
                                                        1 -> viewModel.moveCatalogDown(catalog.id)
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
        Row(modifier = Modifier.fillMaxSize()) {
            // Sidebar
            Sidebar(
                selectedItem = SidebarItem.SETTINGS,
                isSidebarFocused = activeZone == Zone.SIDEBAR,
                focusedIndex = sidebarFocusIndex,
                profile = currentProfile,
                onProfileClick = onSwitchProfile
            )
            
            // Settings internal sidebar
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .fillMaxSize()
                    .background(BackgroundDark)
                    .padding(vertical = 80.dp, horizontal = 24.dp)
            ) {
                Text(
                    text = "Settings",
                    style = ArflixTypography.heroTitle.copy(fontSize = androidx.compose.ui.unit.TextUnit.Unspecified),
                    color = TextPrimary,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .padding(bottom = 32.dp)
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
                        isFocused = activeZone == Zone.SECTION && sectionIndex == index
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = "ARVIO V1.5",
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
                        frameRateMatchingMode = uiState.frameRateMatchingMode,
                        autoPlayNext = uiState.autoPlayNext,
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                        onSubtitleClick = openSubtitlePicker,
                        onFrameRateMatchingClick = { viewModel.cycleFrameRateMatchingMode() },
                        onAutoPlayToggle = { viewModel.setAutoPlayNext(it) }
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
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1
                    )
                    "catalogs" -> CatalogsSettings(
                        catalogs = uiState.catalogs,
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                        focusedActionIndex = catalogActionIndex
                    )
                    "addons" -> AddonsSettings(
                        addons = uiState.addons,
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                        focusedActionIndex = addonActionIndex,
                        onToggleAddon = { viewModel.toggleAddon(it) },
                        onDeleteAddon = { viewModel.removeAddon(it) },
                        onAddCustomAddon = { /* TODO: Show input modal */ }
                    )
                    "accounts" -> AccountsSettings(
                        isCloudAuthenticated = uiState.isLoggedIn,
                        cloudEmail = uiState.accountEmail,
                        cloudHint = null,
                        isTraktAuthenticated = uiState.isTraktAuthenticated,
                        traktCode = uiState.traktCode?.userCode,
                        traktUrl = uiState.traktCode?.verificationUrl,
                        isTraktPolling = uiState.isTraktPolling,
                        focusedIndex = if (activeZone == Zone.CONTENT) contentFocusIndex else -1,
                        onConnectCloud = { viewModel.startCloudAuth() },
                        onDisconnectCloud = { viewModel.logout() },
                        onConnectTrakt = { viewModel.startTraktAuth() },
                        onCancelTrakt = { viewModel.cancelTraktAuth() },
                        onDisconnectTrakt = { viewModel.disconnectTrakt() },
                        onSwitchProfile = onSwitchProfile
                    )
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

        if (showSubtitlePicker) {
            SubtitlePickerModal(
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
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .width(600.dp)
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
                                    1 -> 3 // Move to primary action; avoid "Down moves right" feeling.
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

                val isSignInFocused = focusedIndex == 3
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (isSignInFocused) SuccessGreen else Pink.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(8.dp)
                        )
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
                        color = Color.White
                    )
                }

                val isCreateFocused = focusedIndex == 4
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (isCreateFocused) SuccessGreen else Color.White.copy(alpha = 0.08f),
                            shape = RoundedCornerShape(8.dp)
                        )
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
                text = "Tip: Use TV keyboard. D-pad to navigate.",
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.5f)
            )
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
    // Focus order: 0 cancel, 1 email/password
    var focusedIndex by remember { mutableIntStateOf(1) }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val modalWidth = (maxWidth * 0.62f).coerceIn(520.dp, 760.dp)
            val qrContainerSize = (modalWidth * 0.42f).coerceIn(190.dp, 260.dp)
            val qrBitmapSizePx = ((qrContainerSize.value * 3.2f).toInt()).coerceIn(512, 900)

            Column(
                modifier = Modifier
                    .widthIn(max = modalWidth)
                    .fillMaxWidth(0.62f)
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .padding(horizontal = 24.dp, vertical = 20.dp)
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

                Text(
                    text = "Scan this QR code to sign in and link this TV.",
                    style = ArflixTypography.body,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                if (verificationUrl.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .size(qrContainerSize)
                            .background(Color.White, RoundedCornerShape(12.dp))
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        QrCodeImage(
                            data = verificationUrl,
                            sizePx = qrBitmapSizePx,
                            modifier = Modifier.fillMaxSize(),
                            foreground = android.graphics.Color.BLACK,
                            background = android.graphics.Color.WHITE,
                        )
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

private enum class Zone {
    SIDEBAR, SECTION, CONTENT
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsSectionItem(
    icon: ImageVector,
    title: String,
    isSelected: Boolean,
    isFocused: Boolean
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
    frameRateMatchingMode: String,
    autoPlayNext: Boolean,
    focusedIndex: Int,
    onSubtitleClick: () -> Unit,
    onFrameRateMatchingClick: () -> Unit,
    onAutoPlayToggle: (Boolean) -> Unit
) {
    Column {
        Text(
            text = "Player Preferences",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Default Subtitle
        SettingsRow(
            icon = Icons.Default.Subtitles,
            title = "Default Subtitle",
            subtitle = "Preferred language for auto-selection",
            value = defaultSubtitle,
            isFocused = focusedIndex == 0,
            onClick = onSubtitleClick
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        // Frame-Rate Matching
        SettingsRow(
            icon = Icons.Default.Movie,
            title = "Match Frame Rate",
            subtitle = "Off, Seamless only, or Always (may blank-screen on some TVs)",
            value = frameRateMatchingMode,
            isFocused = focusedIndex == 1,
            onClick = onFrameRateMatchingClick
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        // Auto-Play Next
        SettingsToggleRow(
            title = "Auto-Play Next",
            subtitle = "Start next episode automatically",
            isEnabled = autoPlayNext,
            isFocused = focusedIndex == 2,
            onToggle = onAutoPlayToggle
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
    focusedIndex: Int
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
            onClick = {}
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
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        SettingsRow(
            icon = Icons.Default.Delete,
            title = "Delete M3U Playlist",
            subtitle = if (m3uUrl.isBlank()) "No playlist configured" else "Remove M3U, EPG and favorites",
            value = if (m3uUrl.isBlank()) "EMPTY" else "DELETE",
            isFocused = focusedIndex == 2,
            onClick = {}
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
        Row(verticalAlignment = Alignment.CenterVertically) {
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
                    color = TextPrimary
                )
                Text(
                    text = subtitle,
                    style = ArflixTypography.caption,
                    color = TextSecondary
                )
            }
        }
        
        Text(
            text = value.uppercase(),
            style = ArflixTypography.label,
            color = Pink
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
        Column {
            Text(
                text = title,
                style = ArflixTypography.cardTitle,
                color = TextPrimary
            )
            Text(
                text = subtitle,
                style = ArflixTypography.caption,
                color = TextSecondary
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
    focusedActionIndex: Int
) {
    Column {
        Text(
            text = "Catalogs",
            style = ArflixTypography.sectionTitle,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = "Only Trakt and MDBlist URLs are supported.",
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
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        catalogs.forEachIndexed { index, catalog ->
            val rowFocusIndex = index + 1
            val isRowFocused = focusedIndex == rowFocusIndex
            val title = if (catalog.isPreinstalled) "${catalog.title} (Built-in)" else catalog.title

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
                        color = if (isRowFocused) TextPrimary else TextSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = catalog.sourceUrl ?: "Preinstalled catalog",
                        style = ArflixTypography.caption,
                        color = TextSecondary.copy(alpha = 0.7f)
                    )
                }

                CatalogActionChip(
                    icon = Icons.Default.ArrowUpward,
                    label = "Up",
                    isFocused = isRowFocused && focusedActionIndex == 0
                )
                Spacer(modifier = Modifier.width(8.dp))
                CatalogActionChip(
                    icon = Icons.Default.ArrowDownward,
                    label = "Down",
                    isFocused = isRowFocused && focusedActionIndex == 1
                )
                Spacer(modifier = Modifier.width(8.dp))
                CatalogActionChip(
                    icon = Icons.Default.Delete,
                    label = "Delete",
                    isFocused = isRowFocused && focusedActionIndex == 2,
                    isDestructive = true,
                    enabled = true
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
    label: String,
    isFocused: Boolean,
    isDestructive: Boolean = false,
    enabled: Boolean = true
) {
    val bgColor = when {
        !enabled -> Color.Black.copy(alpha = 0.4f)
        isFocused && isDestructive -> Color(0xFFDC2626)
        isFocused -> Color.White
        else -> Color.Black
    }
    val fgColor = when {
        !enabled -> Color.White.copy(alpha = 0.5f)
        isFocused && isDestructive -> Color.White
        isFocused -> Color.Black
        else -> Color.White
    }
    Row(
        modifier = Modifier
            .requiredWidth(86.dp)
            .background(bgColor, RoundedCornerShape(8.dp))
            .border(
                width = if (isFocused) 0.dp else 1.dp,
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = fgColor,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, style = ArflixTypography.caption, color = fgColor)
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
                    color = TextPrimary
                )
                Text(
                    text = addon.description,
                    style = ArflixTypography.caption,
                    color = TextSecondary,
                    maxLines = 1
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
    focusedIndex: Int,
    onConnectCloud: () -> Unit,
    onDisconnectCloud: () -> Unit,
    onConnectTrakt: () -> Unit,
    onCancelTrakt: () -> Unit,
    onDisconnectTrakt: () -> Unit,
    onSwitchProfile: () -> Unit
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
                color = TextPrimary
            )
            Text(
                text = description,
                style = ArflixTypography.caption,
                color = TextSecondary
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
                color = TextPrimary
            )
            Text(
                text = description,
                style = ArflixTypography.caption,
                color = TextSecondary
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
                    color = TextPrimary
                )
                Text(
                    text = description,
                    style = ArflixTypography.caption,
                    color = TextSecondary
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
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
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
                                        // Paste button - paste clipboard to first field (URL)
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
                                    else -> false // Let text field handle Enter
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
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.72f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(560.dp)
                    .background(BackgroundElevated, RoundedCornerShape(14.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(14.dp))
                    .padding(horizontal = 20.dp, vertical = 18.dp)
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
                                            }
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

                                            setOnEditorActionListener { _, actionId, _ ->
                                                if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
                                                    val imm = ctx.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                                    imm?.hideSoftInputFromWindow(windowToken, 0)
                                                    clearFocus()
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
                        .background(
                            color = if (isPasteFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (isPasteFocused) Color.White else Color.White.copy(alpha = 0.14f),
                            shape = RoundedCornerShape(10.dp)
                        )
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
                            .background(
                                color = if (isCancelFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isCancelFocused) Color.White else Color.White.copy(alpha = 0.14f),
                                shape = RoundedCornerShape(10.dp)
                            )
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
                            .background(
                                color = if (isConfirmFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isConfirmFocused) Color.White else Color.White.copy(alpha = 0.14f),
                                shape = RoundedCornerShape(10.dp)
                            )
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
                    text = "OK: edit/select • Back: close keyboard first",
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
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
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(520.dp)
                .background(BackgroundElevated, RoundedCornerShape(16.dp))
                .padding(28.dp)
        ) {
            Text(
                text = "Default Subtitles",
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
                            .background(
                                if (isFocused) Color.White.copy(alpha = 0.12f) else Color.Transparent,
                                RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) Pink else Color.White.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(10.dp)
                            )
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
