package com.arflix.tv.ui.screens.plugin

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Extension
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import androidx.compose.material3.Icon
import com.arflix.tv.R
import com.arflix.tv.ui.components.MobileSettingsCategory
import com.arflix.tv.ui.components.MobileSettingsRow
import com.arflix.tv.ui.components.SettingsRow
import com.arflix.tv.ui.components.SettingsToggleRow
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.screens.settings.LocalSettingsFocusTracker
import com.arflix.tv.ui.screens.settings.settingsFocusSlot
import com.arflix.tv.util.LocalDeviceType

import com.arflix.tv.domain.model.PluginRepository

@Composable
private fun PluginMessage.localizedText(): String =
    stringResource(resourceId, *formatArgs.toTypedArray())

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PluginScreen(
    viewModel: PluginViewModel = hiltViewModel(),
    focusedIndex: Int = -1,
    onFocusedIndexChanged: (Int) -> Unit = {},
    onMaxIndexChanged: (Int) -> Unit = {},
    enterTrigger: Int = -1,
    onEnterTriggerHandled: () -> Unit = {},
    onModalStateChanged: (Boolean) -> Unit = {},
    onBackPressed: () -> Unit,
    onNavigateToSection: (() -> Unit)? = null
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }
    var repoToDelete by remember { mutableStateOf<PluginRepository?>(null) }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val sectionNavKey = if (isRtl) Key.DirectionRight else Key.DirectionLeft
    val isMobile = LocalDeviceType.current.isTouchDevice()

    val repositories = uiState.repositories
    val scrapers = uiState.scrapers

    // Dynamic index mapping
    // Slot 0: Add button
    // Slot 1 to repos.size: Repos
    // Slot repos.size + 1 to repos.size + scrapers.size (or + 1 if empty text)
    // Slot repos.size + scrapers.size + 1: Reset button
    val scrapersCount = if (scrapers.isEmpty()) 1 else scrapers.size
    val totalItems = 1 + repositories.size + scrapersCount + 1

    LaunchedEffect(totalItems) {
        onMaxIndexChanged((totalItems - 1).coerceAtLeast(0))
        if (focusedIndex >= totalItems) {
            onFocusedIndexChanged((totalItems - 1).coerceAtLeast(0))
        }
    }

    val modalOpen = showAddDialog || showResetDialog || (repoToDelete != null)
    LaunchedEffect(modalOpen) {
        onModalStateChanged(modalOpen)
    }

    LaunchedEffect(enterTrigger, repositories, scrapersCount, totalItems) {
        if (enterTrigger >= 0) {
            when (enterTrigger) {
                0 -> { showAddDialog = true }
                in 1..repositories.size -> {
                    val repo = repositories[enterTrigger - 1]
                    viewModel.onEvent(PluginUiEvent.RemoveRepository(repo.id))
                    onFocusedIndexChanged((enterTrigger - 1).coerceAtLeast(0))
                }
                in (1 + repositories.size)..(repositories.size + scrapersCount) -> {
                    if (scrapers.isNotEmpty()) {
                        val scraper = scrapers[enterTrigger - 1 - repositories.size]
                        viewModel.onEvent(PluginUiEvent.ToggleScraper(scraper.id, !scraper.enabled))
                    }
                }
                totalItems - 1 -> { showResetDialog = true }
            }
            onEnterTriggerHandled()
        }
    }

    BackHandler(enabled = modalOpen) {
        if (showAddDialog) showAddDialog = false
        else if (showResetDialog) showResetDialog = false
        else if (repoToDelete != null) repoToDelete = null
    }

    BackHandler(enabled = !isMobile && !modalOpen) {
        onBackPressed()
    }

    if (isMobile) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            uiState.errorMessage?.let { msg ->
                Text(msg.localizedText(), color = Color.Red, style = ArflixTypography.body)
            }

            MobileSettingsCategory(title = stringResource(R.string.plugin_screen_add_repo)) {
                MobileSettingsRow(
                    icon = Icons.Default.Add,
                    title = stringResource(R.string.plugin_screen_add_repo),
                    subtitle = stringResource(R.string.plugin_screen_repo_url),
                    value = "",
                    isFocused = false,
                    showDivider = false,
                    onClick = { showAddDialog = true }
                )
            }

            if (repositories.isNotEmpty()) {
                MobileSettingsCategory(title = stringResource(R.string.plugin_screen_installed_repos)) {
                    repositories.forEachIndexed { idx, repo ->
                        MobileSettingsRow(
                            icon = Icons.Default.Extension,
                            title = repo.name,
                            subtitle = repo.url,
                            value = stringResource(R.string.delete),
                            isFocused = false,
                            showDivider = idx < repositories.lastIndex,
                            onClick = { repoToDelete = repo }
                        )
                    }
                }
            }

            MobileSettingsCategory(title = stringResource(R.string.plugin_screen_installed_scrapers)) {
                if (scrapers.isEmpty()) {
                    MobileSettingsRow(
                        icon = Icons.Default.Extension,
                        title = stringResource(R.string.plugin_screen_no_scrapers),
                        value = "",
                        isFocused = false,
                        showDivider = false,
                        onClick = {}
                    )
                } else {
                    scrapers.forEachIndexed { idx, scraper ->
                        MobileSettingsRow(
                            icon = Icons.Default.Extension,
                            title = scraper.name,
                            subtitle = scraper.id,
                            value = if (scraper.enabled) stringResource(R.string.on) else stringResource(R.string.off),
                            isFocused = false,
                            showDivider = idx < scrapers.lastIndex,
                            onClick = { viewModel.onEvent(PluginUiEvent.ToggleScraper(scraper.id, !scraper.enabled)) }
                        )
                    }
                }
            }

            MobileSettingsCategory(title = "") {
                MobileSettingsRow(
                    icon = Icons.Default.Delete,
                    title = stringResource(R.string.plugin_screen_reset_title),
                    subtitle = stringResource(R.string.plugin_screen_reset_desc),
                    value = "",
                    isFocused = false,
                    showDivider = false,
                    onClick = { showResetDialog = true }
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .padding(bottom = 80.dp)
                .fillMaxWidth()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            sectionNavKey -> {
                                onNavigateToSection?.invoke()
                                return@onPreviewKeyEvent onNavigateToSection != null
                            }
                            Key.Back, Key.Escape -> {
                                onBackPressed()
                                return@onPreviewKeyEvent true
                            }
                            else -> {}
                        }
                    }
                    false
                }
        ) {
            uiState.errorMessage?.let { msg ->
                Text(msg.localizedText(), color = Color.Red, style = ArflixTypography.body)
                Spacer(modifier = Modifier.height(16.dp))
            }

            val accentColor = resolveAccentColor(fallback = Pink)
            val isAddRowFocused = (focusedIndex == 0)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .settingsFocusSlot(0)
                    .focusProperties { canFocus = false }
                    .clickable { showAddDialog = true }
                    .background(
                        if (isAddRowFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                        RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = if (isAddRowFocused) 2.dp else 0.dp,
                        color = if (isAddRowFocused) accentColor else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(R.string.plugin_screen_add_repo),
                    style = ArflixTypography.button,
                    color = accentColor
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (repositories.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.plugin_screen_installed_repos),
                    style = ArflixTypography.caption.copy(fontSize = 12.sp, letterSpacing = 1.sp),
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                repositories.forEachIndexed { idx, repo ->
                    val slotIndex = 1 + idx
                    FocusableSettingsRow(
                        index = slotIndex,
                        focusedIndex = focusedIndex,
                        icon = Icons.Default.Delete,
                        title = repo.name,
                        subtitle = repo.url,
                        value = stringResource(R.string.delete),
                        onClick = { viewModel.onEvent(PluginUiEvent.RemoveRepository(repo.id)) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Text(
                text = stringResource(R.string.plugin_screen_installed_scrapers),
                style = ArflixTypography.caption.copy(fontSize = 12.sp, letterSpacing = 1.sp),
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            val scraperStartIdx = 1 + repositories.size
            if (scrapers.isEmpty()) {
                val slotIndex = scraperStartIdx
                Text(
                    text = stringResource(R.string.plugin_screen_no_scrapers),
                    style = ArflixTypography.body,
                    color = TextSecondary,
                    modifier = Modifier.settingsFocusSlot(slotIndex)
                )
            } else {
                scrapers.forEachIndexed { idx, scraper ->
                    val slotIndex = scraperStartIdx + idx
                    FocusableSettingsToggleRow(
                        index = slotIndex,
                        focusedIndex = focusedIndex,
                        title = scraper.name,
                        subtitle = scraper.id,
                        isEnabled = scraper.enabled,
                        onToggle = { enabled -> viewModel.onEvent(PluginUiEvent.ToggleScraper(scraper.id, enabled)) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            val resetIndex = totalItems - 1
            val isResetRowFocused = (focusedIndex == resetIndex)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .settingsFocusSlot(resetIndex)
                    .focusProperties { canFocus = false }
                    .clickable { showResetDialog = true }
                    .background(
                        if (isResetRowFocused) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.05f),
                        RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = if (isResetRowFocused) 2.dp else 0.dp,
                        color = if (isResetRowFocused) Color.Red else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(R.string.plugin_screen_reset_title),
                    style = ArflixTypography.button,
                    color = Color.Red
                )
            }
        }
    }

    if (showAddDialog) {
        AddRepoDialog(
            onSave = { url ->
                viewModel.onEvent(PluginUiEvent.AddRepository(url))
                showAddDialog = false
            },
            onDismiss = {
                showAddDialog = false
            }
        )
    }

    if (showResetDialog) {
        WarningDialog(
            title = stringResource(R.string.plugin_screen_reset_warning_title),
            message = stringResource(R.string.plugin_screen_reset_warning_message),
            cancelText = stringResource(R.string.cancel),
            confirmText = stringResource(R.string.delete),
            onConfirm = {
                viewModel.onEvent(PluginUiEvent.ResetAllPlugins)
                onFocusedIndexChanged(0)
                showResetDialog = false
            },
            onDismiss = { showResetDialog = false }
        )
    }

    repoToDelete?.let { repo ->
        WarningDialog(
            title = stringResource(R.string.delete),
            message = stringResource(R.string.plugin_screen_remove_repo_message, repo.name),
            cancelText = stringResource(R.string.cancel),
            confirmText = stringResource(R.string.delete),
            onConfirm = {
                viewModel.onEvent(PluginUiEvent.RemoveRepository(repo.id))
                repoToDelete = null
            },
            onDismiss = { repoToDelete = null }
        )
    }
}
@Composable
fun HideDialogSystemBars() {
    val view = LocalView.current
    val context = LocalContext.current
    val window = (view.parent as? DialogWindowProvider)?.window
    val isTv = remember {
        val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as? android.app.UiModeManager
        uiModeManager?.currentModeType == android.content.res.Configuration.UI_MODE_TYPE_TELEVISION
    }
    LaunchedEffect(window) {
        if (window != null && isTv) {
            WindowInsetsControllerCompat(window, window.decorView).apply {
                hide(WindowInsetsCompat.Type.systemBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}

@Composable
fun FocusableSettingsRow(
    index: Int,
    focusedIndex: Int,
    icon: ImageVector,
    title: String,
    subtitle: String = "",
    value: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsRow(
        icon = icon,
        title = title,
        subtitle = subtitle,
        value = value,
        isFocused = (focusedIndex == index),
        onClick = onClick,
        modifier = modifier
            .settingsFocusSlot(index)
            .focusProperties { canFocus = false }
    )
}

@Composable
fun FocusableSettingsToggleRow(
    index: Int,
    focusedIndex: Int,
    title: String,
    subtitle: String = "",
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsToggleRow(
        title = title,
        subtitle = subtitle,
        isEnabled = isEnabled,
        isFocused = (focusedIndex == index),
        onToggle = onToggle,
        modifier = modifier
            .settingsFocusSlot(index)
            .focusProperties { canFocus = false }
    )
}

@Composable
fun AddRepoDialog(
    onSave: (String) -> Unit,
    onDismiss: () -> Unit
) {
    HideDialogSystemBars()
    var value by remember { mutableStateOf("") }
    val inputFocusRequester = remember { FocusRequester() }
    val saveFocus = remember { FocusRequester() }
    val cancelFocus = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100)
        try { inputFocusRequester.requestFocus() } catch (_: Exception) {}
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown &&
                        (event.key == Key.Back || event.key == Key.Escape)) {
                        onDismiss()
                        return@onPreviewKeyEvent true
                    }
                    false
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(520.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(BackgroundElevated)
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Text(
                        text = stringResource(R.string.plugin_screen_add_repo_dialog_title),
                        style = ArflixTypography.sectionTitle,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.OutlinedTextField(
                        value = value,
                        onValueChange = { value = it },
                        singleLine = true,
                        placeholder = { Text(stringResource(R.string.plugin_screen_repo_url), color = TextSecondary.copy(alpha = 0.4f)) },
                        modifier = Modifier.fillMaxWidth().focusRequester(inputFocusRequester),
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary,
                            focusedBorderColor = Pink,
                            unfocusedBorderColor = TextSecondary.copy(alpha = 0.3f),
                            focusedLabelColor = Pink,
                            unfocusedLabelColor = TextSecondary
                        )
                    )

                    // On a TV, on demand: or paste the repository link on the phone.
                    Spacer(modifier = Modifier.height(12.dp))
                    com.arflix.tv.ui.components.PhoneInputOnDemand(
                        title = stringResource(R.string.plugin_screen_add_repo_dialog_title),
                        fields = listOf(com.arflix.tv.server.PhoneInputServer.Field(stringResource(R.string.plugin_screen_repo_url), value)),
                        onValues = { values -> values.firstOrNull()?.trim()?.takeIf { it.isNotEmpty() }?.let { value = it } },
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                        var isCancelFocused by remember { mutableStateOf(false) }
                        var isSaveFocused by remember { mutableStateOf(false) }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(cancelFocus)
                                .onFocusChanged { isCancelFocused = it.isFocused }
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isCancelFocused) BackgroundElevated.copy(alpha = 0.8f) else BackgroundElevated)
                                .border(
                                    width = if (isCancelFocused) 2.dp else 1.dp,
                                    color = if (isCancelFocused) Color.White.copy(alpha = 0.8f) else TextSecondary.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onDismiss() }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
                                textAlign = TextAlign.Center,
                                color = TextSecondary,
                                style = ArflixTypography.button
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(saveFocus)
                                .onFocusChanged { isSaveFocused = it.isFocused }
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isSaveFocused) Pink.copy(alpha = 0.35f) else Pink.copy(alpha = 0.15f))
                                .border(
                                    width = if (isSaveFocused) 2.dp else 1.dp,
                                    color = if (isSaveFocused) Pink else Pink.copy(alpha = 0.4f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onSave(value) }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.add),
                                textAlign = TextAlign.Center,
                                color = Pink,
                                style = ArflixTypography.button
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun WarningDialog(
    title: String,
    message: String,
    cancelText: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    HideDialogSystemBars()
    val cancelFocusRequester = remember { FocusRequester() }
    val confirmFocus = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100)
        try { cancelFocusRequester.requestFocus() } catch (_: Exception) {}
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true, usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown &&
                        (event.key == Key.Back || event.key == Key.Escape)) {
                        onDismiss()
                        return@onPreviewKeyEvent true
                    }
                    false
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(420.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(BackgroundElevated)
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = title,
                        style = ArflixTypography.sectionTitle,
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = message,
                        style = ArflixTypography.body,
                        color = TextSecondary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        var isCancelFocused by remember { mutableStateOf(false) }
                        var isConfirmFocused by remember { mutableStateOf(false) }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(cancelFocusRequester)
                                .onFocusChanged { isCancelFocused = it.isFocused }
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isCancelFocused) BackgroundElevated.copy(alpha = 0.8f) else BackgroundElevated)
                                .border(
                                    width = if (isCancelFocused) 2.dp else 1.dp,
                                    color = if (isCancelFocused) Color.White.copy(alpha = 0.8f) else TextSecondary.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onDismiss() }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = cancelText,
                                textAlign = TextAlign.Center,
                                color = TextSecondary,
                                style = ArflixTypography.button
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(confirmFocus)
                                .onFocusChanged { isConfirmFocused = it.isFocused }
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isConfirmFocused) Pink.copy(alpha = 0.35f) else Pink.copy(alpha = 0.15f))
                                .border(
                                    width = if (isConfirmFocused) 2.dp else 1.dp,
                                    color = if (isConfirmFocused) Pink else Pink.copy(alpha = 0.4f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onConfirm() }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = confirmText,
                                textAlign = TextAlign.Center,
                                color = Pink,
                                style = ArflixTypography.button
                            )
                        }
                    }
                }
            }
        }
    }
}
