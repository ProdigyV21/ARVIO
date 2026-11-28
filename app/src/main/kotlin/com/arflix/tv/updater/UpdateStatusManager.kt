package com.arflix.tv.updater

import java.util.concurrent.atomic.AtomicBoolean
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed class UpdateStatus {
    data object Idle : UpdateStatus()
    data object Checking : UpdateStatus()
    data class UpdateAvailable(val update: AppUpdate) : UpdateStatus()
    data class Downloading(val progress: Float?, val update: AppUpdate) : UpdateStatus()
    data class ReadyToInstall(val apkPath: String, val update: AppUpdate) : UpdateStatus()
    /**
     * [preparing] is how much of the APK has been copied to Android's installer (0..1). That copy
     * takes seconds on slow TV storage (~20 s for 150 MB on a Mi Box) before Android can show its
     * confirmation. Null once Android has the whole APK.
     *
     * [apkPath] lets "Retry install" start over: some Android TV builds (the Mi Box's Android 9)
     * report nothing when the user cancels Android's confirmation, so the status stays here.
     */
    data class Installing(
        val update: AppUpdate?,
        val preparing: Float? = null,
        val apkPath: String? = null,
    ) : UpdateStatus()
    data object Success : UpdateStatus()
    /** [apkPath] is set when Retry should install the downloaded APK again, not download it. */
    data class Failure(
        val message: String,
        val update: AppUpdate? = null,
        val apkPath: String? = null,
    ) : UpdateStatus()
}

/**
 * The downloaded APK that Install, or "Retry install" after Android's confirmation went away,
 * should install. Null while there is nothing to install yet or the APK is still being copied.
 */
fun UpdateStatus.installableApk(): Pair<String, AppUpdate>? = when (this) {
    is UpdateStatus.ReadyToInstall -> apkPath to update
    is UpdateStatus.Installing ->
        if (preparing == null && apkPath != null && update != null) apkPath to update else null
    is UpdateStatus.Failure -> if (apkPath != null && update != null) apkPath to update else null
    else -> null
}

@Singleton
class UpdateStatusManager @Inject constructor() {
    private val _status = MutableStateFlow<UpdateStatus>(UpdateStatus.Idle)
    val status: StateFlow<UpdateStatus> = _status.asStateFlow()

    var sessionIgnoredTag: String? = null
    private var lastUpdate: AppUpdate? = null

    fun updateStatus(newStatus: UpdateStatus) {
        val statusWithContext = when (newStatus) {
            is UpdateStatus.UpdateAvailable -> newStatus.also { lastUpdate = it.update }
            is UpdateStatus.Downloading -> newStatus.also { lastUpdate = it.update }
            is UpdateStatus.ReadyToInstall -> newStatus.also { lastUpdate = it.update }
            is UpdateStatus.Installing -> newStatus.also { if (it.update != null) lastUpdate = it.update }
            is UpdateStatus.Failure -> {
                val update = newStatus.update ?: lastUpdate
                if (update != null) newStatus.copy(update = update) else newStatus
            }
            else -> newStatus
        }
        _status.value = statusWithContext
    }

    /** Moves the copy progress of an install in progress; ignored once the status moved on. */
    fun setInstallPreparing(progress: Float?) {
        val current = _status.value as? UpdateStatus.Installing ?: return
        _status.value = current.copy(preparing = progress)
    }

    /** Android is showing its confirmation; keeps the APK path so the install can be retried. */
    fun waitForConfirmation() {
        val current = _status.value
        _status.value = if (current is UpdateStatus.Installing) {
            current.copy(preparing = null)
        } else {
            UpdateStatus.Installing(lastUpdate)
        }
    }

    /**
     * Android reported the install failed. [keepApk] (the user cancelled) lets Retry install the
     * downloaded APK again.
     */
    fun installFailed(message: String, keepApk: Boolean) {
        val apkPath = if (keepApk) (_status.value as? UpdateStatus.Installing)?.apkPath else null
        updateStatus(UpdateStatus.Failure(message, apkPath = apkPath))
    }

    private val installAfterPermission = AtomicBoolean(false)

    /** The user was sent to Android's "install unknown apps" screen to allow ARVIO. */
    fun awaitInstallPermission() {
        installAfterPermission.set(true)
    }

    /**
     * True once, when the screen comes back after [awaitInstallPermission], so the install can
     * continue without the user pressing Install again.
     */
    fun takeInstallAfterPermission(): Boolean = installAfterPermission.getAndSet(false)

    fun reset() {
        lastUpdate = null
        _status.value = UpdateStatus.Idle
    }
}
