package com.arflix.tv.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder

@Composable
internal fun HomeOrientationState(isLandscape: Boolean, content: @Composable () -> Unit) {
    val stateHolder = rememberSaveableStateHolder()
    stateHolder.SaveableStateProvider(if (isLandscape) "landscape_home" else "portrait_home") {
        content()
    }
}
