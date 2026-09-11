package com.arflix.tv.ui.components

import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.widget.doAfterTextChanged
import com.arflix.tv.R
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.SuccessGreen
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.tr

enum class IptvSourceType {
    M3U,
    XTREAM,
    STALKER
}

private enum class ActivePane {
    LEFT,
    RIGHT
}

@Composable
fun IptvPlaylistModal(
    isEditing: Boolean,
    initialSourceType: IptvSourceType = IptvSourceType.M3U,
    initialName: String,
    initialUrl: String,
    initialXtreamUser: String = "",
    initialXtreamPass: String = "",
    initialEpg: String = "",
    initialMacAddress: String = "",
    initialImportLiveTv: Boolean = true,
    initialImportVod: Boolean = true,
    initialImportSeries: Boolean = true,
    onSaveIptv: (
        name: String,
        url: String,
        xtreamUser: String,
        xtreamPass: String,
        epg: String,
        importLiveTv: Boolean,
        importVod: Boolean,
        importSeries: Boolean
    ) -> Unit,
    onSaveStalker: (
        name: String,
        portalUrl: String,
        macAddress: String
    ) -> Unit,
    onDismiss: () -> Unit
) {
    var sourceType by remember(initialSourceType, initialXtreamUser, initialXtreamPass) {
        mutableStateOf(
            when {
                initialSourceType == IptvSourceType.STALKER -> IptvSourceType.STALKER
                initialXtreamUser.isNotBlank() && initialXtreamPass.isNotBlank() -> IptvSourceType.XTREAM
                else -> initialSourceType
            }
        )
    }

    var playlistName by remember(initialName, isEditing, initialSourceType) {
        mutableStateOf(
            if (isEditing) initialName
            else if (initialName.isNotBlank() && initialSourceType != IptvSourceType.STALKER) initialName
            else "Playlist 1"
        )
    }
    var playlistUrl by remember(initialUrl, initialSourceType) {
        mutableStateOf(if (initialSourceType != IptvSourceType.STALKER) initialUrl else "")
    }
    var xtreamUser by remember(initialXtreamUser) { mutableStateOf(initialXtreamUser) }
    var xtreamPass by remember(initialXtreamPass) { mutableStateOf(initialXtreamPass) }
    var epgSources by remember(initialEpg) { mutableStateOf(initialEpg) }

    var stalkerName by remember(initialName, isEditing, initialSourceType) {
        mutableStateOf(
            if (initialSourceType == IptvSourceType.STALKER && initialName.isNotBlank()) initialName
            else if (isEditing && initialSourceType == IptvSourceType.STALKER) initialName
            else "Portal 1"
        )
    }
    var stalkerPortalUrl by remember(initialUrl, initialSourceType) {
        mutableStateOf(if (initialSourceType == IptvSourceType.STALKER) initialUrl else "")
    }
    var stalkerMac by remember(initialMacAddress) { mutableStateOf(initialMacAddress) }

    var importLiveTv by remember(initialImportLiveTv) { mutableStateOf(initialImportLiveTv) }
    var importVod by remember(initialImportVod) { mutableStateOf(initialImportVod) }
    var importSeries by remember(initialImportSeries) { mutableStateOf(initialImportSeries) }

    var activePane by remember(isEditing) {
        mutableStateOf(if (isEditing) ActivePane.RIGHT else ActivePane.LEFT)
    }
    // Left pane indices: 0 = M3U, 1 = Xtream, 2 = Stalker, 3 = Live, 4 = Movies, 5 = Series
    var leftFocusedIndex by remember(sourceType) {
        mutableIntStateOf(
            when (sourceType) {
                IptvSourceType.M3U -> 0
                IptvSourceType.XTREAM -> 1
                IptvSourceType.STALKER -> 2
            }
        )
    }

    // Right pane grid: Name, URL, EPG/User/MAC, Password and EPG (Xtream), then actions.
    // rightFocusedColumn: 0 = Input field, 1 = Paste button (or Save button on actions row)
    var rightFocusedRow by remember { mutableIntStateOf(0) }
    var rightFocusedColumn by remember { mutableIntStateOf(0) }

    val isTouchDevice = LocalDeviceType.current.isTouchDevice()
    val configuration = LocalConfiguration.current
    val isTvLayout = !isTouchDevice
    val screenHeightDp = configuration.screenHeightDp.dp
    val maxDialogHeight = (screenHeightDp * 0.90f).coerceAtMost(if (isTouchDevice) 620.dp else 680.dp)

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val view = LocalView.current
    val modalFocusRequester = remember { FocusRequester() }
    val formScrollState = rememberScrollState()

    // 0: Name, 1: URL/Host, 2: EPG/User/MAC, 3: Pass, 4: EPG (Xtream)
    val editTextRefs = remember { MutableList<EditText?>(5) { null } }

    fun actionsRow(): Int = if (sourceType == IptvSourceType.XTREAM) 5 else 3
    fun hasPasteButton(row: Int): Boolean = row in 1 until actionsRow()

    fun anyEditTextFocused(): Boolean = editTextRefs.any { it?.hasFocus() == true }

    fun hideKeyboardAll() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        editTextRefs.forEach { edit ->
            if (edit != null) {
                imm?.hideSoftInputFromWindow(edit.windowToken, 0)
                edit.clearFocus()
                runCatching { imm?.restartInput(edit) }
            }
        }
        view.requestFocus()
    }

    fun showKeyboardFor(row: Int) {
        val edit = editTextRefs.getOrNull(row) ?: return
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        edit.post {
            edit.requestFocus()
            val shown = imm?.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT) ?: false
            if (!shown) imm?.showSoftInput(edit, InputMethodManager.SHOW_FORCED)
        }
    }

    fun saveSource() {
        hideKeyboardAll()
        if (sourceType == IptvSourceType.STALKER) {
            onSaveStalker(stalkerName, stalkerPortalUrl, stalkerMac)
        } else {
            onSaveIptv(
                playlistName,
                playlistUrl,
                if (sourceType == IptvSourceType.XTREAM) xtreamUser else "",
                if (sourceType == IptvSourceType.XTREAM) xtreamPass else "",
                epgSources,
                importLiveTv,
                importVod,
                importSeries
            )
        }
    }

    fun pasteClipboardIntoRow(row: Int) {
        val text = clipboardManager.getText()?.text?.toString() ?: return
        when (sourceType) {
            IptvSourceType.M3U -> {
                when (row) {
                    0 -> {
                        playlistName = text
                        editTextRefs[0]?.setText(text)
                        editTextRefs[0]?.setSelection(text.length)
                    }
                    1 -> {
                        playlistUrl = text
                        editTextRefs[1]?.setText(text)
                        editTextRefs[1]?.setSelection(text.length)
                    }
                    2 -> {
                        epgSources = text
                        editTextRefs[2]?.setText(text)
                        editTextRefs[2]?.setSelection(text.length)
                    }
                }
            }
            IptvSourceType.XTREAM -> {
                when (row) {
                    0 -> {
                        playlistName = text
                        editTextRefs[0]?.setText(text)
                        editTextRefs[0]?.setSelection(text.length)
                    }
                    1 -> {
                        playlistUrl = text
                        editTextRefs[1]?.setText(text)
                        editTextRefs[1]?.setSelection(text.length)
                    }
                    2 -> {
                        xtreamUser = text
                        editTextRefs[2]?.setText(text)
                        editTextRefs[2]?.setSelection(text.length)
                    }
                    3 -> {
                        xtreamPass = text
                        editTextRefs[3]?.setText(text)
                        editTextRefs[3]?.setSelection(text.length)
                    }
                    4 -> {
                        epgSources = text
                        editTextRefs[4]?.setText(text)
                        editTextRefs[4]?.setSelection(text.length)
                    }
                }
            }
            IptvSourceType.STALKER -> {
                when (row) {
                    0 -> {
                        stalkerName = text
                        editTextRefs[0]?.setText(text)
                        editTextRefs[0]?.setSelection(text.length)
                    }
                    1 -> {
                        stalkerPortalUrl = text
                        editTextRefs[1]?.setText(text)
                        editTextRefs[1]?.setSelection(text.length)
                    }
                    2 -> {
                        stalkerMac = text
                        editTextRefs[2]?.setText(text)
                        editTextRefs[2]?.setSelection(text.length)
                    }
                }
            }
        }
        modalFocusRequester.requestFocus()
    }

    Dialog(
        onDismissRequest = {
            hideKeyboardAll()
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        // Dialog content is composed separately; its focus target must be attached first.
        LaunchedEffect(Unit) {
            modalFocusRequester.requestFocus()
        }

        BackHandler {
            hideKeyboardAll()
            onDismiss()
        }

        ModalScrim(
            onDismiss = {
                hideKeyboardAll()
                onDismiss()
            }
        ) {
            Box(
                modifier = Modifier
                    .then(
                        if (isTvLayout) Modifier.width(730.dp)
                        else Modifier.fillMaxWidth(0.92f).widthIn(max = 640.dp)
                    )
                    .navigationBarsPadding()
                    .imePadding()
                    .heightIn(max = maxDialogHeight)
                    .background(BackgroundElevated, RoundedCornerShape(14.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(14.dp))
                    .padding(
                        horizontal = if (isTvLayout) 20.dp else 16.dp,
                        vertical = if (isTvLayout) 20.dp else 18.dp
                    )
                    .focusRequester(modalFocusRequester)
                    .testTag("iptv_playlist_modal")
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false

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

                            Key.DirectionLeft -> {
                                if (anyEditTextFocused()) return@onPreviewKeyEvent false
                                if (activePane == ActivePane.RIGHT) {
                                    if (rightFocusedColumn == 1) {
                                        // On paste button or Save button -> move left to Input field or Cancel
                                        rightFocusedColumn = 0
                                        true
                                    } else {
                                        // Jump across to Left Pane
                                        activePane = ActivePane.LEFT
                                        leftFocusedIndex = when {
                                            sourceType == IptvSourceType.STALKER -> 2
                                            rightFocusedRow == 0 -> 2 // Land on Stalker tab, then Left -> Xtream -> M3U
                                            rightFocusedRow == 1 -> 3 // Live toggle
                                            rightFocusedRow == 2 -> 4 // Movies toggle
                                            else -> 5 // Series toggle
                                        }
                                        true
                                    }
                                } else {
                                    // In Left Pane: move between tabs 0, 1, 2
                                    if (leftFocusedIndex in 1..2) {
                                        leftFocusedIndex--
                                    }
                                    true
                                }
                            }

                            Key.DirectionRight -> {
                                if (anyEditTextFocused()) return@onPreviewKeyEvent false
                                if (activePane == ActivePane.LEFT) {
                                    if (leftFocusedIndex in 0..1) {
                                        leftFocusedIndex++
                                        true
                                    } else {
                                        // From Stalker tab (2) or toggles: jump across to Right Pane
                                        activePane = ActivePane.RIGHT
                                        rightFocusedRow = when (leftFocusedIndex) {
                                            0, 1, 2 -> 0 // Playlist/Portal Name
                                            3 -> 1 // URL
                                            4 -> 2 // EPG / User / MAC
                                            else -> if (sourceType == IptvSourceType.XTREAM) 3 else 2 // Password / EPG
                                        }
                                        rightFocusedColumn = 0
                                        true
                                    }
                                } else {
                                    // In Right Pane
                                    if (rightFocusedColumn == 0 && (hasPasteButton(rightFocusedRow) || rightFocusedRow == actionsRow())) {
                                        rightFocusedColumn = 1 // Move to paste button or Save button
                                    }
                                    true
                                }
                            }

                            Key.DirectionUp -> {
                                if (anyEditTextFocused()) {
                                    hideKeyboardAll()
                                }
                                if (activePane == ActivePane.LEFT) {
                                    if (leftFocusedIndex == 3) {
                                        leftFocusedIndex = when (sourceType) {
                                            IptvSourceType.M3U -> 0
                                            IptvSourceType.XTREAM -> 1
                                            IptvSourceType.STALKER -> 2
                                        }
                                    } else if (leftFocusedIndex in 4..5) {
                                        leftFocusedIndex--
                                    }
                                    // If leftFocusedIndex in 0..2, stay on that tab
                                    true
                                } else {
                                    if (rightFocusedRow > 0) {
                                        rightFocusedRow--
                                        rightFocusedColumn = 0
                                    }
                                    true
                                }
                            }

                            Key.DirectionDown -> {
                                if (anyEditTextFocused()) {
                                    hideKeyboardAll()
                                }
                                if (activePane == ActivePane.LEFT) {
                                    if (sourceType == IptvSourceType.STALKER) {
                                        // Stalker has no toggles; jump to Right Pane
                                        activePane = ActivePane.RIGHT
                                        rightFocusedRow = 0
                                        rightFocusedColumn = 0
                                    } else {
                                        if (leftFocusedIndex in 0..2) {
                                            leftFocusedIndex = 3 // Move to Live toggle
                                        } else if (leftFocusedIndex < 5) {
                                            leftFocusedIndex++
                                        }
                                    }
                                    true
                                } else {
                                    if (rightFocusedRow < actionsRow()) {
                                        rightFocusedRow++
                                        rightFocusedColumn = 0
                                    }
                                    true
                                }
                            }

                            Key.Enter, Key.DirectionCenter -> {
                                if (activePane == ActivePane.LEFT) {
                                    when (leftFocusedIndex) {
                                        0 -> sourceType = IptvSourceType.M3U
                                        1 -> sourceType = IptvSourceType.XTREAM
                                        2 -> sourceType = IptvSourceType.STALKER
                                        3 -> importLiveTv = !importLiveTv
                                        4 -> importVod = !importVod
                                        5 -> importSeries = !importSeries
                                    }
                                    true
                                } else {
                                    if (rightFocusedRow == actionsRow()) {
                                        if (rightFocusedColumn == 0) {
                                            hideKeyboardAll()
                                            onDismiss()
                                        } else {
                                            saveSource()
                                        }
                                        true
                                    } else {
                                        if (rightFocusedColumn == 1) {
                                            pasteClipboardIntoRow(rightFocusedRow)
                                        } else {
                                            showKeyboardFor(rightFocusedRow)
                                        }
                                        true
                                    }
                                }
                            }

                            else -> false
                        }
                    }
            ) {
                if (isTvLayout) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(22.dp)
                    ) {
                        // Left Pane: Source Type & Content Selection
                        Column(
                            modifier = Modifier.width(240.dp)
                        ) {
                            Text(
                                text = stringResource(
                                    when {
                                        isEditing && sourceType == IptvSourceType.STALKER -> R.string.settings_cd_edit_stalker_config
                                        isEditing -> R.string.settings_edit_tv_playlist
                                        sourceType == IptvSourceType.STALKER -> R.string.settings_add_stalker_portal_button
                                        else -> R.string.settings_add_tv_playlist
                                    }
                                ),
                                style = ArflixTypography.sectionTitle,
                                color = TextPrimary
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            Text(
                                text = stringResource(R.string.settings_source_type),
                                style = ArflixTypography.caption,
                                color = TextSecondary,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // 3 Source Type Tabs: M3U | Xtream | Stalker
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                SourceTypeTab(
                                    label = "M3U",
                                    isSelected = sourceType == IptvSourceType.M3U,
                                    isFocused = activePane == ActivePane.LEFT && leftFocusedIndex == 0,
                                    onClick = {
                                        activePane = ActivePane.LEFT
                                        leftFocusedIndex = 0
                                        sourceType = IptvSourceType.M3U
                                    },
                                    modifier = Modifier.weight(1f)
                                )

                                SourceTypeTab(
                                    label = "Xtream",
                                    isSelected = sourceType == IptvSourceType.XTREAM,
                                    isFocused = activePane == ActivePane.LEFT && leftFocusedIndex == 1,
                                    onClick = {
                                        activePane = ActivePane.LEFT
                                        leftFocusedIndex = 1
                                        sourceType = IptvSourceType.XTREAM
                                    },
                                    modifier = Modifier.weight(1.1f)
                                )

                                SourceTypeTab(
                                    label = "Stalker",
                                    isSelected = sourceType == IptvSourceType.STALKER,
                                    isFocused = activePane == ActivePane.LEFT && leftFocusedIndex == 2,
                                    onClick = {
                                        activePane = ActivePane.LEFT
                                        leftFocusedIndex = 2
                                        sourceType = IptvSourceType.STALKER
                                    },
                                    modifier = Modifier.weight(1.1f)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            if (sourceType != IptvSourceType.STALKER) {
                                Text(
                                    text = stringResource(R.string.settings_content_to_import),
                                    style = ArflixTypography.caption,
                                    color = TextSecondary,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                ToggleRow(
                                    label = stringResource(R.string.live),
                                    value = importLiveTv,
                                    isFocused = activePane == ActivePane.LEFT && leftFocusedIndex == 3,
                                    onClick = {
                                        activePane = ActivePane.LEFT
                                        leftFocusedIndex = 3
                                        importLiveTv = !importLiveTv
                                    }
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                ToggleRow(
                                    label = stringResource(R.string.movies),
                                    value = importVod,
                                    isFocused = activePane == ActivePane.LEFT && leftFocusedIndex == 4,
                                    onClick = {
                                        activePane = ActivePane.LEFT
                                        leftFocusedIndex = 4
                                        importVod = !importVod
                                    }
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                ToggleRow(
                                    label = stringResource(R.string.series),
                                    value = importSeries,
                                    isFocused = activePane == ActivePane.LEFT && leftFocusedIndex == 5,
                                    onClick = {
                                        activePane = ActivePane.LEFT
                                        leftFocusedIndex = 5
                                        importSeries = !importSeries
                                    }
                                )
                            } else {
                                // Stalker Portal Info Card
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color.White.copy(alpha = 0.05f))
                                        .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(10.dp))
                                        .padding(12.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.settings_stalker_middleware_hint),
                                        style = ArflixTypography.caption.copy(fontWeight = FontWeight.SemiBold),
                                        color = TextPrimary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = stringResource(R.string.settings_stalker_desc),
                                        style = ArflixTypography.caption.copy(fontSize = 11.sp),
                                        color = TextSecondary
                                    )
                                }
                            }
                        }

                        // Right Pane: Input Form Fields & Action Buttons
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(formScrollState)
                        ) {
                            when (sourceType) {
                                IptvSourceType.M3U -> {
                                    // Row 0: Playlist Name (Input only, NO paste button)
                                    InputFieldBlock(
                                        stepNumber = 1,
                                        label = stringResource(R.string.settings_label_playlist_name),
                                        placeholder = stringResource(R.string.settings_label_playlist_name),
                                        value = playlistName,
                                        isFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 0,
                                        onValueChange = { playlistName = it },
                                        onRegisterEditText = { editTextRefs[0] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 0
                                            rightFocusedColumn = 0
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Row 1: M3U URL (with inline Paste button)
                                    InputFieldWithPaste(
                                        stepNumber = 2,
                                        label = stringResource(R.string.settings_label_m3u_url),
                                        placeholder = "https://example.com/playlist.m3u",
                                        value = playlistUrl,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 1 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 1 && rightFocusedColumn == 1,
                                        onValueChange = { playlistUrl = it },
                                        onRegisterEditText = { editTextRefs[1] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 0
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(1)
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Row 2: EPG Sources (with inline Paste button)
                                    InputFieldWithPaste(
                                        stepNumber = 3,
                                        label = stringResource(R.string.settings_label_epg_sources),
                                        placeholder = "https://provider.com/epg.xml.gz",
                                        value = epgSources,
                                        singleLine = false,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 2 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 2 && rightFocusedColumn == 1,
                                        onValueChange = { epgSources = it },
                                        onRegisterEditText = { editTextRefs[2] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 3
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(2)
                                        }
                                    )
                                }

                                IptvSourceType.XTREAM -> {
                                    // Row 0: Playlist Name (Input only, NO paste button)
                                    InputFieldBlock(
                                        stepNumber = 1,
                                        label = stringResource(R.string.settings_label_playlist_name),
                                        placeholder = stringResource(R.string.settings_label_playlist_name),
                                        value = playlistName,
                                        isFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 0,
                                        onValueChange = { playlistName = it },
                                        onRegisterEditText = { editTextRefs[0] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 0
                                            rightFocusedColumn = 0
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Row 1: Server URL (with inline Paste button)
                                    InputFieldWithPaste(
                                        stepNumber = 2,
                                        label = stringResource(R.string.settings_label_server_url),
                                        placeholder = "http://provider.host:port",
                                        value = playlistUrl,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 1 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 1 && rightFocusedColumn == 1,
                                        onValueChange = { playlistUrl = it },
                                        onRegisterEditText = { editTextRefs[1] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 0
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(1)
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Row 2: Username (with inline Paste button)
                                    InputFieldWithPaste(
                                        stepNumber = 3,
                                        label = stringResource(R.string.settings_label_username),
                                        placeholder = stringResource(R.string.settings_label_username),
                                        value = xtreamUser,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 2 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 2 && rightFocusedColumn == 1,
                                        onValueChange = { xtreamUser = it },
                                        onRegisterEditText = { editTextRefs[2] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 3
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(2)
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Row 3: Password (with inline Paste button)
                                    InputFieldWithPaste(
                                        stepNumber = 4,
                                        label = stringResource(R.string.settings_label_password),
                                        placeholder = stringResource(R.string.settings_label_password),
                                        value = xtreamPass,
                                        isSecret = true,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 3 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 3 && rightFocusedColumn == 1,
                                        onValueChange = { xtreamPass = it },
                                        onRegisterEditText = { editTextRefs[3] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 3
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 4
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 3
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(3)
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    InputFieldWithPaste(
                                        stepNumber = 5,
                                        label = stringResource(R.string.settings_label_epg_sources),
                                        placeholder = "https://provider.com/epg.xml.gz",
                                        value = epgSources,
                                        singleLine = false,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 4 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 4 && rightFocusedColumn == 1,
                                        onValueChange = { epgSources = it },
                                        onRegisterEditText = { editTextRefs[4] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 4
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 3
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 5
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 4
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(4)
                                        }
                                    )
                                }

                                IptvSourceType.STALKER -> {
                                    // Row 0: Portal Name (Input only, NO paste button)
                                    InputFieldBlock(
                                        stepNumber = 1,
                                        label = stringResource(R.string.settings_stalker_portal_name_label),
                                        placeholder = stringResource(R.string.settings_stalker_portal_name_placeholder),
                                        value = stalkerName,
                                        isFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 0,
                                        onValueChange = { stalkerName = it },
                                        onRegisterEditText = { editTextRefs[0] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 0
                                            rightFocusedColumn = 0
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Row 1: Portal URL (with inline Paste button)
                                    InputFieldWithPaste(
                                        stepNumber = 2,
                                        label = stringResource(R.string.settings_stalker_label_portal_url),
                                        placeholder = stringResource(R.string.settings_stalker_ph_portal_url),
                                        value = stalkerPortalUrl,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 1 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 1 && rightFocusedColumn == 1,
                                        onValueChange = { stalkerPortalUrl = it },
                                        onRegisterEditText = { editTextRefs[1] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 0
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(1)
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Row 2: MAC Address (with inline Paste button)
                                    InputFieldWithPaste(
                                        stepNumber = 3,
                                        label = stringResource(R.string.settings_stalker_label_mac),
                                        placeholder = stringResource(R.string.settings_stalker_ph_mac),
                                        value = stalkerMac,
                                        isInputFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 2 && rightFocusedColumn == 0,
                                        isPasteFocused = activePane == ActivePane.RIGHT && rightFocusedRow == 2 && rightFocusedColumn == 1,
                                        onValueChange = { stalkerMac = it },
                                        onRegisterEditText = { editTextRefs[2] = it },
                                        onGainNativeFocus = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 0
                                        },
                                        onDpadUp = {
                                            rightFocusedRow = 1
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadDown = {
                                            rightFocusedRow = 3
                                            rightFocusedColumn = 0
                                            modalFocusRequester.requestFocus()
                                        },
                                        onDpadRight = {
                                            rightFocusedColumn = 1
                                            modalFocusRequester.requestFocus()
                                        },
                                        onPasteClick = {
                                            activePane = ActivePane.RIGHT
                                            rightFocusedRow = 2
                                            rightFocusedColumn = 1
                                            pasteClipboardIntoRow(2)
                                        }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(22.dp))

                            // Bottom Action Row: Cancel and Save
                            val actRow = actionsRow()
                            val isCancelFocused = activePane == ActivePane.RIGHT && rightFocusedRow == actRow && rightFocusedColumn == 0
                            val isSaveFocused = activePane == ActivePane.RIGHT && rightFocusedRow == actRow && rightFocusedColumn == 1

                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .revealFocusedField(isCancelFocused || isSaveFocused),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            if (isCancelFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                                            RoundedCornerShape(10.dp)
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

                                Box(
                                    modifier = Modifier
                                        .weight(1.3f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            if (isSaveFocused) Color.White else Color.Black.copy(alpha = 0.82f),
                                            RoundedCornerShape(10.dp)
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = if (isSaveFocused) Color.White else Color.White.copy(alpha = 0.14f),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .clickable { saveSource() }
                                        .padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(
                                            if (sourceType == IptvSourceType.STALKER) R.string.settings_save_portal
                                            else R.string.settings_save_playlist
                                        ),
                                        style = ArflixTypography.button,
                                        color = if (isSaveFocused) Color.Black else Color.White
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Mobile / Touchscreen Layout (matches InputModal / Add Addons design)
                    var mobileFocusedIndex by remember(sourceType) { mutableIntStateOf(1) }

                    fun mobilePasteTargetIndex(): Int {
                        val maxField = if (sourceType == IptvSourceType.XTREAM) 4 else 2
                        return mobileFocusedIndex.coerceIn(0, maxField)
                    }

                    val pasteTargetLabel = when (sourceType) {
                        IptvSourceType.M3U -> when (mobilePasteTargetIndex()) {
                            0 -> stringResource(R.string.settings_label_playlist_name)
                            2 -> stringResource(R.string.settings_label_epg_sources)
                            else -> stringResource(R.string.settings_label_m3u_url)
                        }
                        IptvSourceType.XTREAM -> when (mobilePasteTargetIndex()) {
                            0 -> stringResource(R.string.settings_label_playlist_name)
                            2 -> stringResource(R.string.settings_label_username)
                            3 -> stringResource(R.string.settings_label_password)
                            4 -> stringResource(R.string.settings_label_epg_sources)
                            else -> stringResource(R.string.settings_label_server_url)
                        }
                        IptvSourceType.STALKER -> when (mobilePasteTargetIndex()) {
                            0 -> stringResource(R.string.settings_stalker_portal_name_label)
                            2 -> stringResource(R.string.settings_stalker_label_mac)
                            else -> stringResource(R.string.settings_stalker_label_portal_url)
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(
                                when {
                                    isEditing && sourceType == IptvSourceType.STALKER -> R.string.settings_cd_edit_stalker_config
                                    isEditing -> R.string.settings_edit_tv_playlist
                                    sourceType == IptvSourceType.STALKER -> R.string.settings_add_stalker_portal_button
                                    else -> R.string.settings_add_tv_playlist
                                }
                            ),
                            style = ArflixTypography.sectionTitle,
                            color = TextPrimary
                        )
                        Text(
                            text = stringResource(R.string.settings_modal_hint_touch),
                            style = ArflixTypography.caption,
                            color = TextSecondary.copy(alpha = 0.75f),
                            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                        )

                        // Source Type Tabs
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SourceTypeTab(
                                label = "M3U",
                                isSelected = sourceType == IptvSourceType.M3U,
                                isFocused = false,
                                onClick = {
                                    sourceType = IptvSourceType.M3U
                                    if (!isEditing && playlistName.isBlank()) playlistName = "Playlist 1"
                                },
                                modifier = Modifier.weight(1f)
                            )
                            SourceTypeTab(
                                label = "Xtream",
                                isSelected = sourceType == IptvSourceType.XTREAM,
                                isFocused = false,
                                onClick = {
                                    sourceType = IptvSourceType.XTREAM
                                    if (!isEditing && playlistName.isBlank()) playlistName = "Playlist 1"
                                },
                                modifier = Modifier.weight(1.1f)
                            )
                            SourceTypeTab(
                                label = "Stalker",
                                isSelected = sourceType == IptvSourceType.STALKER,
                                isFocused = false,
                                onClick = {
                                    sourceType = IptvSourceType.STALKER
                                    if (!isEditing && stalkerName.isBlank()) stalkerName = "Portal 1"
                                },
                                modifier = Modifier.weight(1.1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Scrollable middle section for fields and toggles
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f, fill = false)
                                .verticalScroll(formScrollState)
                        ) {
                            when (sourceType) {
                                IptvSourceType.M3U -> {
                                    InputFieldBlock(
                                        stepNumber = 1,
                                        label = stringResource(R.string.settings_label_playlist_name),
                                        placeholder = stringResource(R.string.settings_label_playlist_name),
                                        value = playlistName,
                                        isFocused = mobileFocusedIndex == 0,
                                        onValueChange = { playlistName = it },
                                        onRegisterEditText = { editTextRefs[0] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 0 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 2,
                                        label = stringResource(R.string.settings_label_m3u_url),
                                        placeholder = "https://example.com/playlist.m3u",
                                        value = playlistUrl,
                                        isFocused = mobileFocusedIndex == 1,
                                        onValueChange = { playlistUrl = it },
                                        onRegisterEditText = { editTextRefs[1] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 1 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 3,
                                        label = stringResource(R.string.settings_label_epg_sources),
                                        placeholder = "https://provider.com/epg.xml.gz",
                                        value = epgSources,
                                        singleLine = false,
                                        isFocused = mobileFocusedIndex == 2,
                                        onValueChange = { epgSources = it },
                                        onRegisterEditText = { editTextRefs[2] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 2 }
                                    )
                                }
                                IptvSourceType.XTREAM -> {
                                    InputFieldBlock(
                                        stepNumber = 1,
                                        label = stringResource(R.string.settings_label_playlist_name),
                                        placeholder = stringResource(R.string.settings_label_playlist_name),
                                        value = playlistName,
                                        isFocused = mobileFocusedIndex == 0,
                                        onValueChange = { playlistName = it },
                                        onRegisterEditText = { editTextRefs[0] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 0 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 2,
                                        label = stringResource(R.string.settings_label_server_url),
                                        placeholder = "http://provider.host:port",
                                        value = playlistUrl,
                                        isFocused = mobileFocusedIndex == 1,
                                        onValueChange = { playlistUrl = it },
                                        onRegisterEditText = { editTextRefs[1] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 1 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 3,
                                        label = stringResource(R.string.settings_label_username),
                                        placeholder = stringResource(R.string.settings_label_username),
                                        value = xtreamUser,
                                        isFocused = mobileFocusedIndex == 2,
                                        onValueChange = { xtreamUser = it },
                                        onRegisterEditText = { editTextRefs[2] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 2 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 4,
                                        label = stringResource(R.string.settings_label_password),
                                        placeholder = stringResource(R.string.settings_label_password),
                                        value = xtreamPass,
                                        isSecret = true,
                                        isFocused = mobileFocusedIndex == 3,
                                        onValueChange = { xtreamPass = it },
                                        onRegisterEditText = { editTextRefs[3] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 3 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 5,
                                        label = stringResource(R.string.settings_label_epg_sources),
                                        placeholder = "https://provider.com/epg.xml.gz",
                                        value = epgSources,
                                        singleLine = false,
                                        isFocused = mobileFocusedIndex == 4,
                                        onValueChange = { epgSources = it },
                                        onRegisterEditText = { editTextRefs[4] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 4 }
                                    )
                                }
                                IptvSourceType.STALKER -> {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color.White.copy(alpha = 0.05f))
                                            .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(10.dp))
                                            .padding(12.dp)
                                    ) {
                                        Text(
                                            text = stringResource(R.string.settings_stalker_middleware_hint),
                                            style = ArflixTypography.caption.copy(fontWeight = FontWeight.SemiBold),
                                            color = TextPrimary
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = stringResource(R.string.settings_stalker_desc),
                                            style = ArflixTypography.caption.copy(fontSize = 11.sp),
                                            color = TextSecondary
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 1,
                                        label = stringResource(R.string.settings_stalker_portal_name_label),
                                        placeholder = stringResource(R.string.settings_stalker_portal_name_placeholder),
                                        value = stalkerName,
                                        isFocused = mobileFocusedIndex == 0,
                                        onValueChange = { stalkerName = it },
                                        onRegisterEditText = { editTextRefs[0] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 0 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 2,
                                        label = stringResource(R.string.settings_stalker_label_portal_url),
                                        placeholder = stringResource(R.string.settings_stalker_ph_portal_url),
                                        value = stalkerPortalUrl,
                                        isFocused = mobileFocusedIndex == 1,
                                        onValueChange = { stalkerPortalUrl = it },
                                        onRegisterEditText = { editTextRefs[1] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 1 }
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    InputFieldBlock(
                                        stepNumber = 3,
                                        label = stringResource(R.string.settings_stalker_label_mac),
                                        placeholder = stringResource(R.string.settings_stalker_ph_mac),
                                        value = stalkerMac,
                                        isFocused = mobileFocusedIndex == 2,
                                        onValueChange = { stalkerMac = it },
                                        onRegisterEditText = { editTextRefs[2] = it },
                                        onGainNativeFocus = { mobileFocusedIndex = 2 }
                                    )
                                }
                            }

                            if (sourceType != IptvSourceType.STALKER) {
                                Spacer(modifier = Modifier.height(14.dp))
                                ToggleRow(
                                    label = stringResource(R.string.live),
                                    value = importLiveTv,
                                    isFocused = false,
                                    onClick = { importLiveTv = !importLiveTv }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                ToggleRow(
                                    label = stringResource(R.string.movies),
                                    value = importVod,
                                    isFocused = false,
                                    onClick = { importVod = !importVod }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                ToggleRow(
                                    label = stringResource(R.string.series),
                                    value = importSeries,
                                    isFocused = false,
                                    onClick = { importSeries = !importSeries }
                                )
                            }
                        }

                        // Dedicated Full-Width Paste Button (mirroring InputModal)
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Black.copy(alpha = 0.82f), RoundedCornerShape(10.dp))
                                .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(10.dp))
                                .clickable {
                                    pasteClipboardIntoRow(mobilePasteTargetIndex())
                                }
                                .padding(vertical = 12.dp, horizontal = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentPaste,
                                contentDescription = stringResource(R.string.settings_cd_paste),
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.settings_paste_into, pasteTargetLabel),
                                style = ArflixTypography.button,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        // Bottom Action Buttons (mirroring InputModal)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.Black.copy(alpha = 0.82f), RoundedCornerShape(10.dp))
                                    .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(10.dp))
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
                                    color = Color.White
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White, RoundedCornerShape(10.dp))
                                    .clickable { saveSource() }
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(
                                        if (sourceType == IptvSourceType.STALKER) R.string.settings_save_portal
                                        else R.string.settings_save_playlist
                                    ),
                                    style = ArflixTypography.button,
                                    color = Color.Black
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
@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
private fun Modifier.revealFocusedField(isFocused: Boolean): Modifier {
    val requester = remember { BringIntoViewRequester() }
    val isTv = !LocalDeviceType.current.isTouchDevice()
    LaunchedEffect(isFocused, isTv) {
        if (isFocused && isTv) requester.bringIntoView()
    }
    return bringIntoViewRequester(requester)
}

@Composable
private fun SourceTypeTab(
    label: String,
    isSelected: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accent = resolveAccentColor(fallback = Pink)
    val cornerRadius = 10.dp

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = if (isFocused) 1.05f else 1.0f
                scaleY = if (isFocused) 1.05f else 1.0f
            }
            .then(
                if (isFocused) {
                    Modifier
                        .border(
                            width = 2.5.dp,
                            color = accent,
                            shape = RoundedCornerShape(cornerRadius)
                        )
                        .padding(2.5.dp)
                } else {
                    Modifier.padding(2.5.dp)
                }
            )
            .clip(RoundedCornerShape(cornerRadius - 2.dp))
            .background(
                when {
                    isSelected -> Color.White
                    isFocused -> Color.White.copy(alpha = 0.20f)
                    else -> Color.White.copy(alpha = 0.06f)
                }
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = ArflixTypography.caption.copy(
                fontSize = 12.sp,
                fontWeight = if (isSelected || isFocused) FontWeight.Bold else FontWeight.Medium
            ),
            color = when {
                isSelected -> Color.Black
                isFocused -> Color.White
                else -> TextSecondary
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun StepBadge(stepNumber: Int, isFocused: Boolean) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .background(
                if (isFocused) Pink.copy(alpha = 0.20f) else Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(10.dp)
            )
            .border(
                1.dp,
                if (isFocused) Pink else Color.White.copy(alpha = 0.12f),
                RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$stepNumber",
            style = ArflixTypography.caption.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold),
            color = if (isFocused) Pink else TextSecondary
        )
    }
}

@Composable
private fun ToggleRow(
    label: String,
    value: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                Color.White.copy(alpha = if (isFocused) 0.14f else 0.05f),
                RoundedCornerShape(10.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) resolveAccentColor(fallback = Pink) else Color.White.copy(alpha = 0.14f),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = ArflixTypography.caption,
            color = if (isFocused) resolveAccentColor(fallback = Pink) else TextPrimary,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .width(42.dp)
                .height(22.dp)
                .background(
                    color = if (value) SuccessGreen else Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(11.dp)
                )
                .padding(2.dp),
            contentAlignment = if (value) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(Color.White, RoundedCornerShape(9.dp))
            )
        }
    }
}

@Composable
private fun InputFieldWithPaste(
    stepNumber: Int,
    label: String,
    placeholder: String,
    value: String,
    isSecret: Boolean = false,
    singleLine: Boolean = true,
    isInputFocused: Boolean,
    isPasteFocused: Boolean,
    onValueChange: (String) -> Unit,
    onRegisterEditText: (EditText) -> Unit,
    onGainNativeFocus: () -> Unit,
    onDpadUp: (() -> Unit)? = null,
    onDpadDown: (() -> Unit)? = null,
    onDpadRight: (() -> Unit)? = null,
    onPasteClick: () -> Unit
) {
    val buttonPadding = 5.dp

    Column(modifier = Modifier.fillMaxWidth().revealFocusedField(isInputFocused || isPasteFocused)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            StepBadge(stepNumber = stepNumber, isFocused = isInputFocused || isPasteFocused)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = ArflixTypography.caption,
                color = if (isInputFocused || isPasteFocused) resolveAccentColor(fallback = Pink) else TextSecondary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (singleLine) 46.dp else 84.dp)
                .background(
                    Color.White.copy(alpha = if (isInputFocused || isPasteFocused) 0.12f else 0.05f),
                    RoundedCornerShape(10.dp)
                )
                .border(
                    width = if (isInputFocused || isPasteFocused) 2.dp else 1.dp,
                    color = if (isInputFocused || isPasteFocused) resolveAccentColor(fallback = Pink) else Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(start = 2.dp, top = buttonPadding, bottom = buttonPadding, end = buttonPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                AndroidView(
                    factory = { ctx ->
                        EditText(ctx).apply {
                            onRegisterEditText(this)
                            setText(value)
                            setTextColor(android.graphics.Color.WHITE)
                            setHintTextColor(android.graphics.Color.GRAY)
                            hint = placeholder
                            textSize = 15f
                            background = null
                            gravity = android.view.Gravity.CENTER_VERTICAL
                            setPadding(14, 0, 8, 0)
                            this.isSingleLine = singleLine
                            setHorizontallyScrolling(singleLine)
                            minLines = 1
                            maxLines = if (singleLine) 1 else 3
                            isFocusable = true
                            isFocusableInTouchMode = true
                            inputType = if (isSecret) {
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            } else {
                                InputType.TYPE_CLASS_TEXT or if (singleLine) 0 else InputType.TYPE_TEXT_FLAG_MULTI_LINE
                            }
                            if (isSecret) {
                                transformationMethod = PasswordTransformationMethod.getInstance()
                            }

                            doAfterTextChanged { editable ->
                                onValueChange(editable?.toString() ?: "")
                            }

                            setOnFocusChangeListener { _, hasFocus ->
                                if (hasFocus) onGainNativeFocus()
                            }

                            setOnKeyListener { _, keyCode, event ->
                                if (event.action == android.view.KeyEvent.ACTION_DOWN) {
                                    when (keyCode) {
                                        android.view.KeyEvent.KEYCODE_DPAD_DOWN -> {
                                            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                            imm?.hideSoftInputFromWindow(windowToken, 0)
                                            clearFocus()
                                            onDpadDown?.invoke()
                                            true
                                        }
                                        android.view.KeyEvent.KEYCODE_DPAD_UP -> {
                                            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                            imm?.hideSoftInputFromWindow(windowToken, 0)
                                            clearFocus()
                                            onDpadUp?.invoke()
                                            true
                                        }
                                        android.view.KeyEvent.KEYCODE_DPAD_RIGHT -> {
                                            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                            imm?.hideSoftInputFromWindow(windowToken, 0)
                                            clearFocus()
                                            onDpadRight?.invoke()
                                            true
                                        }
                                        android.view.KeyEvent.KEYCODE_BACK -> {
                                            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                            imm?.hideSoftInputFromWindow(windowToken, 0)
                                            clearFocus()
                                            true
                                        }
                                        else -> false
                                    }
                                } else false
                            }

                            setOnEditorActionListener { _, actionId, _ ->
                                if (actionId == EditorInfo.IME_ACTION_DONE) {
                                    val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                    imm?.hideSoftInputFromWindow(windowToken, 0)
                                    clearFocus()
                                    true
                                } else false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize().testTag("iptv_input_$stepNumber"),
                    update = { editText ->
                        val current = editText.text?.toString().orEmpty()
                        if (current != value) {
                            editText.setText(value)
                            editText.setSelection(value.length)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            // Inline Paste Button: Top, Bottom, and Right padding are identical (buttonPadding = 5.dp)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(7.dp))
                    .background(
                        if (isPasteFocused) Color.White
                        else Color.White.copy(alpha = 0.10f)
                    )
                    .border(
                        1.dp,
                        if (isPasteFocused) Color.White else Color.White.copy(alpha = 0.15f),
                        RoundedCornerShape(7.dp)
                    )
                    .clickable(onClick = onPasteClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ContentPaste,
                    contentDescription = stringResource(R.string.settings_cd_paste),
                    tint = if (isPasteFocused) Color.Black else Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun InputFieldBlock(
    stepNumber: Int,
    label: String,
    placeholder: String,
    value: String,
    isSecret: Boolean = false,
    singleLine: Boolean = true,
    isFocused: Boolean,
    onValueChange: (String) -> Unit,
    onRegisterEditText: (EditText) -> Unit,
    onGainNativeFocus: () -> Unit,
    onDpadUp: (() -> Unit)? = null,
    onDpadDown: (() -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth().revealFocusedField(isFocused)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            StepBadge(stepNumber = stepNumber, isFocused = isFocused)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = ArflixTypography.caption,
                color = if (isFocused) resolveAccentColor(fallback = Pink) else TextSecondary
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (singleLine) 46.dp else 84.dp)
                .background(
                    Color.White.copy(alpha = if (isFocused) 0.12f else 0.05f),
                    RoundedCornerShape(10.dp)
                )
                .border(
                    width = if (isFocused) 2.dp else 1.dp,
                    color = if (isFocused) resolveAccentColor(fallback = Pink) else Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 2.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            AndroidView(
                factory = { ctx ->
                    EditText(ctx).apply {
                        onRegisterEditText(this)
                        setText(value)
                        setTextColor(android.graphics.Color.WHITE)
                        setHintTextColor(android.graphics.Color.GRAY)
                        hint = placeholder
                        textSize = 15f
                        background = null
                        gravity = android.view.Gravity.CENTER_VERTICAL
                        setPadding(14, 0, 14, 0)
                        this.isSingleLine = singleLine
                        setHorizontallyScrolling(singleLine)
                        minLines = 1
                        maxLines = if (singleLine) 1 else 3
                        isFocusable = true
                        isFocusableInTouchMode = true

                        val isLikelyUrl = label.contains("url", ignoreCase = true) ||
                            label.contains("server", ignoreCase = true) ||
                            label.contains("epg", ignoreCase = true)

                        inputType = if (isSecret) {
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        } else if (isLikelyUrl) {
                            (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI) or
                                if (singleLine) 0 else InputType.TYPE_TEXT_FLAG_MULTI_LINE
                        } else {
                            InputType.TYPE_CLASS_TEXT or if (singleLine) 0 else InputType.TYPE_TEXT_FLAG_MULTI_LINE
                        }
                        if (isSecret) {
                            transformationMethod = PasswordTransformationMethod.getInstance()
                        }

                        doAfterTextChanged { editable ->
                            onValueChange(editable?.toString() ?: "")
                        }

                        setOnFocusChangeListener { _, hasFocus ->
                            if (hasFocus) onGainNativeFocus()
                        }

                        setOnKeyListener { _, keyCode, event ->
                            if (event.action == android.view.KeyEvent.ACTION_DOWN) {
                                when (keyCode) {
                                    android.view.KeyEvent.KEYCODE_DPAD_DOWN -> {
                                        val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                        imm?.hideSoftInputFromWindow(windowToken, 0)
                                        clearFocus()
                                        onDpadDown?.invoke()
                                        true
                                    }
                                    android.view.KeyEvent.KEYCODE_DPAD_UP -> {
                                        val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                        imm?.hideSoftInputFromWindow(windowToken, 0)
                                        clearFocus()
                                        onDpadUp?.invoke()
                                        true
                                    }
                                    android.view.KeyEvent.KEYCODE_BACK -> {
                                        val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                        imm?.hideSoftInputFromWindow(windowToken, 0)
                                        clearFocus()
                                        true
                                    }
                                    else -> false
                                }
                            } else false
                        }

                        setOnEditorActionListener { _, actionId, _ ->
                            if (actionId == EditorInfo.IME_ACTION_DONE) {
                                val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                                imm?.hideSoftInputFromWindow(windowToken, 0)
                                clearFocus()
                                true
                            } else false
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth().testTag("iptv_input_$stepNumber"),
                update = { editText ->
                    val current = editText.text?.toString().orEmpty()
                    if (current != value) {
                        editText.setText(value)
                        editText.setSelection(value.length)
                    }
                }
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
