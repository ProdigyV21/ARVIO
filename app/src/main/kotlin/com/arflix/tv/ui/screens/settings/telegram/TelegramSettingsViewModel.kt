package com.arflix.tv.ui.screens.settings.telegram

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.telegram.TelegramAuthState
import com.arflix.tv.data.telegram.TelegramRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TelegramSettingsViewModel @Inject constructor(
    private val repository: TelegramRepository
) : ViewModel() {

    val authState: StateFlow<TelegramAuthState> = repository.authState

    private val _cacheSizeBytes = MutableStateFlow(0L)
    val cacheSizeBytes: StateFlow<Long> = _cacheSizeBytes.asStateFlow()

    init {
        refreshCacheSize()
    }

    fun startAuth() = repository.startAuth()
    fun startQrAuth() = repository.requestQrCode()
    fun submitPhone(phone: String) = repository.submitPhone(phone)
    fun submitCode(code: String) = repository.submitCode(code)
    fun submitPassword(password: String) = repository.submitPassword(password)

    fun disconnect() {
        repository.disconnect()
        _cacheSizeBytes.value = 0L
    }

    fun clearCache() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearCache()
            _cacheSizeBytes.value = repository.getCacheSize()
        }
    }

    private fun refreshCacheSize() {
        viewModelScope.launch(Dispatchers.IO) {
            _cacheSizeBytes.value = repository.getCacheSize()
        }
    }
}
