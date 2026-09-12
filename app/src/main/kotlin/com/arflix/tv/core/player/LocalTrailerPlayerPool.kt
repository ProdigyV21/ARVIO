package com.arflix.tv.core.player

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal providing access to the shared [TrailerPlayerPool] singleton.
 * Provided at the root Activity level.
 */
val LocalTrailerPlayerPool = staticCompositionLocalOf<TrailerPlayerPool?> { null }
