package com.arflix.tv.ui.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material3.Icon
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.data.model.Addon
import com.arflix.tv.data.model.isRemovable
import com.arflix.tv.data.model.settingsPageUrl
import com.arflix.tv.ui.components.LoadingIndicator
import com.arflix.tv.ui.components.QrCodeImage
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType

/** Buttons of an addon row on TV, in D-pad order from left to right. */
internal enum class AddonRowAction { TOGGLE, CONFIGURE, DELETE }

internal fun addonRowActions(addon: Addon): List<AddonRowAction> = buildList {
    add(AddonRowAction.TOGGLE)
    if (addon.settingsPageUrl != null) add(AddonRowAction.CONFIGURE)
    if (addon.isRemovable) add(AddonRowAction.DELETE)
}

internal val AddonSetupRequiredColor = Color(0xFFF59E0B)

/** The addon's own logo from its manifest, or the generic addon icon when it has none. */
@Composable
internal fun AddonLogo(addon: Addon, size: Dp, fallbackTint: Color, modifier: Modifier = Modifier) {
    AddonLogo(logoUrl = addon.logo, size = size, fallbackTint = fallbackTint, modifier = modifier)
}

/** The logo at [logoUrl], or the generic addon icon when there is none or it fails to load. */
@Composable
internal fun AddonLogo(logoUrl: String?, size: Dp, fallbackTint: Color, modifier: Modifier = Modifier) {
    val logo = logoUrl?.takeIf { it.isNotBlank() }
    var failed by remember(logo) { mutableStateOf(false) }
    Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
        if (logo == null || failed) {
            Icon(
                imageVector = Icons.Default.Extension,
                contentDescription = null,
                tint = fallbackTint,
                modifier = Modifier.size(size * 0.7f)
            )
        } else {
            AsyncImage(
                model = logo,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                onError = { failed = true },
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
internal fun AddonSetupRequiredChip() {
    Box(
        modifier = Modifier
            .background(AddonSetupRequiredColor, RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = stringResource(R.string.settings_addon_setup_required),
            style = ArflixTypography.caption.copy(fontSize = 11.sp),
            color = Color.Black,
            maxLines = 1
        )
    }
}

internal data class DialogOption(val label: String, val primary: Boolean, val onClick: () -> Unit)

/**
 * Confirms an addon install. A link opened from a website always lands here; so does an
 * addon that is already installed with other settings, which offers "replace" or "keep both".
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun AddonInstallDialog(
    pending: PendingAddonInstall?,
    isLoading: Boolean,
    onInstall: () -> Unit,
    onReplace: () -> Unit,
    onKeepBoth: () -> Unit,
    onDismiss: () -> Unit
) {
    val options = when {
        pending == null -> emptyList()
        pending.existing.isNotEmpty() -> listOf(
            DialogOption(stringResource(R.string.settings_addon_replace_existing), true, onReplace),
            DialogOption(stringResource(R.string.settings_addon_keep_both), false, onKeepBoth),
            DialogOption(stringResource(R.string.cancel), false, onDismiss)
        )
        else -> listOf(
            DialogOption(stringResource(R.string.settings_addon_install), true, onInstall),
            DialogOption(stringResource(R.string.cancel), false, onDismiss)
        )
    }
    AddonDialogFrame(onDismiss = onDismiss, options = options) {
        if (isLoading || pending == null) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingIndicator(size = 40.dp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.settings_addon_loading),
                    style = ArflixTypography.body,
                    color = TextPrimary
                )
            }
            return@AddonDialogFrame
        }
        val addon = pending.addon
        Text(
            text = if (pending.existing.isNotEmpty()) {
                stringResource(R.string.settings_addon_already_installed_title, addon.name)
            } else {
                stringResource(R.string.settings_addon_install_title)
            },
            style = ArflixTypography.sectionTitle,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.06f), RoundedCornerShape(10.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AddonLogo(addon = addon, size = 36.dp, fallbackTint = TextSecondary)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = addon.name,
                    style = ArflixTypography.cardTitle.copy(fontSize = 16.sp),
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = addon.url.orEmpty(),
                    style = ArflixTypography.caption.copy(fontSize = 12.sp),
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(
                if (pending.existing.isNotEmpty()) {
                    R.string.settings_addon_already_installed_message
                } else {
                    R.string.settings_addon_install_trust
                }
            ),
            style = ArflixTypography.body.copy(fontSize = 14.sp),
            color = TextSecondary
        )
    }
}

/** Shows an addon's settings page as a QR code, for TVs without a usable browser. */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun AddonConfigureQrDialog(addonName: String, url: String, onDismiss: () -> Unit) {
    // Wide and with a modest QR code, so title, code, hint and button fit a 540 dp tall TV.
    AddonDialogFrame(
        onDismiss = onDismiss,
        tvWidth = 560.dp,
        options = listOf(DialogOption(stringResource(R.string.close), true, onDismiss))
    ) {
        Text(
            text = stringResource(R.string.settings_addon_configure_qr_title, addonName),
            style = ArflixTypography.sectionTitle,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(160.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            QrCodeImage(data = url, sizePx = 512, modifier = Modifier.fillMaxSize())
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = url,
            style = ArflixTypography.caption.copy(fontSize = 12.sp),
            color = TextSecondary,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.settings_addon_configure_qr_hint),
            style = ArflixTypography.body.copy(fontSize = 14.sp),
            color = TextSecondary
        )
    }
}

/** Dialog shell with a vertical list of buttons that works with touch and with the D-pad. */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun AddonDialogFrame(
    onDismiss: () -> Unit,
    options: List<DialogOption>,
    tvWidth: Dp = 440.dp,
    content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var focusedIndex by remember(options.size) { mutableIntStateOf(0) }
    val isTouchDevice = LocalDeviceType.current.isTouchDevice()

    LaunchedEffect(options.size) {
        runCatching { focusRequester.requestFocus() }
    }

    androidx.compose.ui.window.Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        BackHandler { onDismiss() }
        ModalScrim(onDismiss = onDismiss) {
            Column(
                modifier = Modifier
                    .then(
                        if (isTouchDevice) Modifier.fillMaxWidth(0.92f).widthIn(max = 440.dp)
                        else Modifier.width(tvWidth)
                    )
                    .background(BackgroundElevated, RoundedCornerShape(16.dp))
                    .verticalScroll(rememberScrollState())
                    .padding(if (isTouchDevice) 20.dp else 24.dp)
                    .focusRequester(focusRequester)
                    .focusable()
                    .onPreviewKeyEvent { event ->
                        if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
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
                                if (focusedIndex < options.size - 1) focusedIndex++
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                options.getOrNull(focusedIndex)?.onClick?.invoke()
                                true
                            }
                            else -> false
                        }
                    }
            ) {
                content()
                if (options.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        options.forEachIndexed { index, option ->
                            val isFocused = !isTouchDevice && focusedIndex == index
                            val filled = if (isTouchDevice) option.primary else isFocused
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (filled) Color.White else Color.Black.copy(alpha = 0.82f))
                                    .border(
                                        width = 1.dp,
                                        color = if (filled) Color.White else Color.White.copy(alpha = 0.14f),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clickable { option.onClick() }
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option.label,
                                    style = ArflixTypography.button,
                                    color = if (filled) Color.Black else Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
