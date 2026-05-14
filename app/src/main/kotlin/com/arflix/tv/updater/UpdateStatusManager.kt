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

    fun updateStatus(newStatus: UpdateStatus) {
        _status.value = newStatus
    }

    fun reset() {
        _status.value = UpdateStatus.Idle
    }
}
