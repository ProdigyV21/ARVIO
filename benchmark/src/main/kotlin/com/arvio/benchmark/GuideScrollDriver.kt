package com.arvio.benchmark

import android.os.SystemClock
import android.view.KeyEvent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assume.assumeTrue
import org.junit.Test
import org.junit.runner.RunWith

/** Opt-in input driver for Perfetto/gfxinfo comparisons of a manually prepared guide.
 * Does not launch the app, sign in, or change any playlist/account configuration.
 */
@RunWith(AndroidJUnit4::class)
class GuideScrollDriver {
    @Test fun scrollPreparedGuide() {
        val args = InstrumentationRegistry.getArguments()
        assumeTrue(args.getString("guidePrepared") == "true")
        repeat(160) { pressGuideKey(KeyEvent.KEYCODE_DPAD_DOWN) }
        repeat(160) { pressGuideKey(KeyEvent.KEYCODE_DPAD_UP) }
    }

    private fun pressGuideKey(keyCode: Int) {
        // Avoid per-key shell processes and idle waits while live video animates.
        val automation = InstrumentationRegistry.getInstrumentation().uiAutomation
        val downTime = SystemClock.uptimeMillis()
        check(automation.injectInputEvent(KeyEvent(downTime, downTime, KeyEvent.ACTION_DOWN, keyCode, 0), true))
        check(automation.injectInputEvent(KeyEvent(downTime, SystemClock.uptimeMillis(), KeyEvent.ACTION_UP, keyCode, 0), true))
        SystemClock.sleep(150)
    }
}
