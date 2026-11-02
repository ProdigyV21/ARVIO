package com.arflix.tv.ui.screens.settings

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.test.platform.app.InstrumentationRegistry
import com.arflix.tv.R
import com.arflix.tv.di.RepositoryAccessEntryPoint
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.LocalDeviceType
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import java.io.File

/** Requests a code and checks pending approval only; never authorizes or changes a real account. */
@OptIn(ExperimentalTestApi::class)
class WeTrakrActivationDeviceTest {
    @get:Rule val compose = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    @Test fun liveCodeHasScannableQrAndWorkingTvRemote() = runBlocking {
        assumeTrue(InstrumentationRegistry.getArguments().getString("wetrakrLive") == "true")
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val repository = EntryPointAccessors.fromApplication(context, RepositoryAccessEntryPoint::class.java).weTrakrRepository()
        val profile = "wetrakr-emulator-activation-test"
        val started = android.os.SystemClock.elapsedRealtime()
        val code = repository.start(profile)
        val elapsed = android.os.SystemClock.elapsedRealtime() - started
        try {
            assertEquals("https://wetrakr.com/activate", code.verificationUrl)
            assertTrue(code.userCode.isNotBlank())
            assertFalse(repository.isConnected(profile))
            var dismissed = false
            compose.setContent {
                CompositionLocalProvider(LocalDeviceType provides DeviceType.TV) {
                    TraktActivationModal(code.verificationUrl, code.userCode, onDismiss = { dismissed = true },
                        title = context.getString(R.string.settings_connect_wetrakr),
                        instruction = context.getString(R.string.settings_activation_visit_instruction, code.verificationUrl),
                        expiresAtMillis = code.expiresAt)
                }
            }
            compose.waitForIdle()
            compose.onNodeWithText(code.userCode).assertIsDisplayed()
            val qr = compose.onNodeWithContentDescription(context.getString(R.string.component_qr_code))
            qr.assertIsDisplayed()
            val bitmap = qr.captureToImage().asAndroidBitmap()
            val pixels = IntArray(bitmap.width * bitmap.height)
            bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
            val decoded = MultiFormatReader().decode(BinaryBitmap(HybridBinarizer(RGBLuminanceSource(bitmap.width, bitmap.height, pixels))))
            assertEquals(code.verificationUrl, decoded.text)
            File(context.getExternalFilesDir(null), "wetrakr-tv-activation.png").outputStream().use {
                compose.onNode(isDialog()).captureToImage().asAndroidBitmap().compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
            }
            delay(code.interval * 1000)
            assertFalse("Unapproved codes must not report a connected account", repository.poll(profile))
            compose.onNode(isDialog()).performKeyInput { pressKey(Key.Enter) }
            compose.runOnIdle { assertTrue(dismissed) }
            instrumentation.sendStatus(0, android.os.Bundle().apply {
                putString("stream", "WeTrakr live activation ${elapsed}ms; QR, pending state and TV remote dismissal passed.\n")
            })
        } finally { repository.cancel(profile) }
    }
}
