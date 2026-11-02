package com.arflix.tv.ui.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.profileinstaller.ProfileVerifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * True while the app ships a baseline profile that the device hasn't compiled yet.
 *
 * Android compiles the profiled code (Home, Live TV) ahead of time only once the profile is
 * applied: at install via Play or a .dm, otherwise by background dexopt while the device is
 * idle. Until then that code runs interpreted and scrolls slowly. Debug builds have no
 * embedded profile, and devices the verifier can't check report false.
 */
suspend fun isAwaitingProfileCompilation(context: Context): Boolean = withContext(Dispatchers.IO) {
    val status = runCatching { ProfileVerifier.writeProfileVerification(context.applicationContext) }
        .getOrNull() ?: return@withContext false
    val code = status.profileInstallResultCode
    val pending = code == ProfileVerifier.CompilationStatus.RESULT_CODE_NO_PROFILE_INSTALLED ||
        code == ProfileVerifier.CompilationStatus.RESULT_CODE_PROFILE_ENQUEUED_FOR_COMPILATION
    status.appApkHasEmbeddedProfile() && !status.isCompiledWithProfile && pending
}

/** [isAwaitingProfileCompilation], checked each time the caller enters composition. */
@Composable
fun rememberAwaitingProfileCompilation(): Boolean {
    val context = LocalContext.current
    var awaiting by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { awaiting = isAwaitingProfileCompilation(context) }
    return awaiting
}
