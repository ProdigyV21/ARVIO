package com.arflix.tv.updater

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed class UpdateStatus {
    object Idle : UpdateStatus()
    object Checking : UpdateStatus()
    data class UpdateAvailable(val update: AppUpdate) : UpdateStatus()
    data class Downloading(val progress: Float?, val update: AppUpdate) : UpdateStatus()
    data class ReadyToInstall(val apkPath: String, val update: AppUpdate) : UpdateStatus()
    data class Installing(val update: AppUpdate?) : UpdateStatus()
    object Success : UpdateStatus()
    data class Failure(val message: String, val update: AppUpdate? = null) : UpdateStatus()
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

    fun reset() {
        lastUpdate = null
        _status.value = UpdateStatus.Idle
    }
}
