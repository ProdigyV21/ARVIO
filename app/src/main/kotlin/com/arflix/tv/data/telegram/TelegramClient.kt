package com.arflix.tv.data.telegram

import android.content.Context
import android.util.Log
import com.arflix.tv.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class TelegramApiException(message: String) : Exception(message)

@Singleton
class TelegramClient @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val TAG = "TelegramClient"
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _authState = MutableStateFlow<TelegramAuthState>(TelegramAuthState.Idle)
    val authState: StateFlow<TelegramAuthState> = _authState.asStateFlow()

    private var client: Client? = null

    val isAvailable: Boolean by lazy {
        try {
            System.loadLibrary("tdjni")
            true
        } catch (e: Throwable) {
            Log.w(TAG, "TDLib not available — Telegram integration disabled: ${e.message}")
            false
        }
    }

    fun initialize() {
        if (client != null) return
        scope.launch {
            if (client != null) return@launch
            stepLog("checking library availability")
            if (!isAvailable) {
                _authState.value = TelegramAuthState.Error(context.getString(R.string.telegram_tdlib_unavailable))
                return@launch
            }
            stepLog("library loaded OK")
            _authState.value = TelegramAuthState.Initializing
            try {
                stepLog("calling Client.create")
                client = Client.create(
                    { update -> handleUpdate(update) },
                    { e -> Log.e(TAG, "Update exception", e) },
                    { e -> Log.e(TAG, "Default exception", e) }
                )
                stepLog("Client.create returned")
                sendTdlibParameters()
                stepLog("SetTdlibParameters sent")
            } catch (e: Throwable) {
                Log.e(TAG, "TDLib Client.create failed", e)
                stepLog("EXCEPTION: ${e.message}")
                _authState.value = TelegramAuthState.Error(context.getString(R.string.telegram_tdlib_failed, e.message ?: ""))
            }
        }
    }

    private fun stepLog(step: String) {
        Log.d(TAG, "STEP: $step")
        try {
            File(context.filesDir, "tdlib_init_log.txt")
                .appendText("${System.currentTimeMillis()} $step\n")
        } catch (_: Throwable) {}
    }

    fun readInitLog(): String =
        try { File(context.filesDir, "tdlib_init_log.txt").readText().takeLast(2000) }
        catch (_: Throwable) { "" }

    fun clearInitLog() =
        try { File(context.filesDir, "tdlib_init_log.txt").delete() } catch (_: Throwable) {}

    private fun sendTdlibParameters() {
        val dbDir = File(context.filesDir, "tdlib").absolutePath
        val filesDir = File(context.filesDir, "tdlib_files").absolutePath
        client?.send(TdApi.SetTdlibParameters().also { p ->
            p.apiId = TelegramConfig.API_ID
            p.apiHash = TelegramConfig.API_HASH
            p.databaseDirectory = dbDir
            p.filesDirectory = filesDir
            p.useMessageDatabase = false
            p.useSecretChats = false
            p.systemLanguageCode = "en"
            p.deviceModel = "Android TV"
            p.applicationVersion = "1.0"
        }, null)
    }

    private fun handleUpdate(obj: TdApi.Object) {
        when (obj) {
            is TdApi.UpdateAuthorizationState -> handleAuthState(obj.authorizationState)
            is TdApi.Error -> {
                val state = _authState.value
                if (state is TelegramAuthState.Initializing ||
                    state is TelegramAuthState.WaitPhone ||
                    state is TelegramAuthState.WaitQr ||
                    state is TelegramAuthState.WaitCode ||
                    state is TelegramAuthState.WaitPassword) {
                    _authState.value = TelegramAuthState.Error(obj.message)
                }
            }
        }
    }

    private fun handleAuthState(state: TdApi.AuthorizationState) {
        Log.d(TAG, "authState -> ${state::class.simpleName}")
        when (state) {
            is TdApi.AuthorizationStateWaitTdlibParameters -> sendTdlibParameters()
            is TdApi.AuthorizationStateWaitPhoneNumber -> {
                _authState.value = TelegramAuthState.WaitPhone
            }
            is TdApi.AuthorizationStateWaitCode -> {
                val len = when (val t = state.codeInfo.type) {
                    is TdApi.AuthenticationCodeTypeTelegramMessage -> t.length
                    is TdApi.AuthenticationCodeTypeSms -> t.length
                    else -> 5
                }
                _authState.value = TelegramAuthState.WaitCode(len)
            }
            is TdApi.AuthorizationStateWaitOtherDeviceConfirmation -> {
                _authState.value = TelegramAuthState.WaitQr(state.link)
            }
            is TdApi.AuthorizationStateWaitPassword -> {
                _authState.value = TelegramAuthState.WaitPassword
            }
            is TdApi.AuthorizationStateReady -> {
                scope.launch {
                    val user = sendRequest(TdApi.GetMe()) as? TdApi.User
                    File(context.filesDir, "tdlib_session_ok").createNewFile()
                    _authState.value = TelegramAuthState.Ready(
                        firstName = user?.firstName ?: "",
                        userId = user?.id ?: 0L
                    )
                }
            }
            is TdApi.AuthorizationStateClosing,
            is TdApi.AuthorizationStateClosed -> {
                _authState.value = TelegramAuthState.Idle
            }
            else -> {}
        }
    }

    fun requestQrCode() {
        client?.send(TdApi.RequestQrCodeAuthentication(LongArray(0)), null)
    }

    fun submitPhone(phone: String) {
        client?.send(TdApi.SetAuthenticationPhoneNumber(phone, null), null)
    }

    fun submitCode(code: String) {
        client?.send(TdApi.CheckAuthenticationCode(code), null)
    }

    fun submitPassword(password: String) {
        client?.send(TdApi.CheckAuthenticationPassword(password), null)
    }

    suspend fun sendRequest(
        function: TdApi.Function<out TdApi.Object>,
        timeoutMs: Long = 10_000L
    ): TdApi.Object? = withTimeoutOrNull(timeoutMs) {
        suspendCancellableCoroutine { cont ->
            val c = client
            if (c == null) {
                cont.resume(null)
                return@suspendCancellableCoroutine
            }
            c.send(function) { result ->
                if (cont.isActive) {
                    if (result is TdApi.Error) cont.resumeWithException(TelegramApiException(result.message))
                    else cont.resume(result)
                }
            }
        }
    }

    fun reset() {
        client?.send(TdApi.Close(), null)
        client = null
        _authState.value = TelegramAuthState.Idle
    }
}
