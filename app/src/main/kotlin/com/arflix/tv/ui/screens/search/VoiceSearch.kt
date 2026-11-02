package com.arflix.tv.ui.screens.search

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.arflix.tv.R
import java.util.Locale

/**
 * Voice search inside the app: Android's SpeechRecognizer listens while the search bar shows it,
 * the way Leanback's search does. Handing off to ACTION_RECOGNIZE_SPEECH instead opens another
 * app's voice screen, makes the user pick one when two are installed, and on some boxes never
 * returns the words at all.
 */
@Stable
class VoiceSearch internal constructor(
    private val context: Context,
    val isAvailable: Boolean
) {
    var isListening by mutableStateOf(false)
        private set
    internal var requestPermission: () -> Unit = {}
    internal var onPartial: (String) -> Unit = {}
    internal var onResult: (String) -> Unit = {}
    private var recognizer: SpeechRecognizer? = null

    /** Starts listening, or finishes early when already listening (the words so far count). */
    fun toggle() {
        when {
            isListening -> recognizer?.stopListening()
            hasPermission() -> start()
            else -> requestPermission()
        }
    }

    fun cancel() {
        val previous = recognizer
        // Invalidate callbacks before Android asynchronously tears down the recognizer.
        recognizer = null
        isListening = false
        previous?.destroy()
    }

    internal fun cancelIfListening(): Boolean {
        if (!isListening) return false
        cancel()
        return true
    }

    internal fun hasPermission() =
        ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

    internal fun start() {
        cancel()
        val r = try { SpeechRecognizer.createSpeechRecognizer(context) } catch (_: Exception) { null } ?: return
        recognizer = r
        r.setRecognitionListener(object : RecognitionListener {
            override fun onPartialResults(partialResults: Bundle?) {
                if (recognizer !== r) return
                partialResults.firstText()?.let { onPartial(it) }
            }
            override fun onResults(results: Bundle?) {
                if (recognizer !== r) return
                val spoken = results.firstText()
                cancel()
                if (spoken != null) onResult(spoken) else toast(R.string.search_voice_no_match)
            }
            override fun onError(error: Int) {
                // Only this recognizer's errors: a cancelled one is already gone.
                if (recognizer !== r) return
                cancel()
                toast(
                    when (error) {
                        SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> R.string.search_voice_permission
                        SpeechRecognizer.ERROR_NETWORK, SpeechRecognizer.ERROR_NETWORK_TIMEOUT,
                        SpeechRecognizer.ERROR_SERVER -> R.string.search_voice_network
                        else -> R.string.search_voice_no_match
                    }
                )
            }
            override fun onReadyForSpeech(params: Bundle?) = Unit
            override fun onBeginningOfSpeech() = Unit
            override fun onRmsChanged(rmsdB: Float) = Unit
            override fun onBufferReceived(buffer: ByteArray?) = Unit
            override fun onEndOfSpeech() = Unit
            override fun onEvent(eventType: Int, params: Bundle?) = Unit
        })
        r.startListening(
            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
                // The app's language (MainActivity makes it the default), not the device's: it is
                // the language the user reads titles in.
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toLanguageTag())
            }
        )
        isListening = true
    }

    internal fun toast(message: Int) {
        Toast.makeText(context, context.getString(message), Toast.LENGTH_SHORT).show()
    }

    private fun Bundle?.firstText() =
        this?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.trim()?.takeIf { it.isNotEmpty() }
}

/**
 * [onPartial] gets the words while they are spoken, [onResult] the final phrase. Listening stops
 * when the screen is paused or left.
 */
@Composable
fun rememberVoiceSearch(onPartial: (String) -> Unit, onResult: (String) -> Unit): VoiceSearch {
    val context = LocalContext.current
    val voiceSearch = remember(context) { VoiceSearch(context, SpeechRecognizer.isRecognitionAvailable(context)) }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) voiceSearch.start() else voiceSearch.toast(R.string.search_voice_permission)
    }
    SideEffect {
        voiceSearch.requestPermission = { permissionLauncher.launch(Manifest.permission.RECORD_AUDIO) }
        voiceSearch.onPartial = onPartial
        voiceSearch.onResult = onResult
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(voiceSearch, lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) voiceSearch.cancel()
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
            voiceSearch.cancel()
        }
    }
    return voiceSearch
}
