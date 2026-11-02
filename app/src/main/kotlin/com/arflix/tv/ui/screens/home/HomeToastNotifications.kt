package com.arflix.tv.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.arflix.tv.ui.components.Toast
import com.arflix.tv.ui.components.ToastType as ComponentToastType

@Composable
internal fun HomeToastNotifications(
    toastMessage: String?,
    toastType: ToastType,
    quietUpdateNotice: String?,
    onToastDismiss: () -> Unit,
    onQuietUpdateDismiss: () -> Unit
) {
    if (toastMessage == null && quietUpdateNotice == null) return
    // One visible toast above Home's menus. An ordinary message takes priority; the
    // separate update state survives and gets its own timer once that message clears.
    Box(modifier = Modifier.fillMaxSize().zIndex(122f)) {
        if (toastMessage != null) {
            Toast(
                message = toastMessage,
                type = when (toastType) {
                    ToastType.SUCCESS -> ComponentToastType.SUCCESS
                    ToastType.ERROR -> ComponentToastType.ERROR
                    ToastType.INFO -> ComponentToastType.INFO
                },
                isVisible = true,
                onDismiss = onToastDismiss
            )
        } else if (quietUpdateNotice != null) {
            Toast(
                message = quietUpdateNotice,
                type = ComponentToastType.INFO,
                isVisible = true,
                durationMs = 8_000L,
                onDismiss = onQuietUpdateDismiss
            )
        }
    }
}
