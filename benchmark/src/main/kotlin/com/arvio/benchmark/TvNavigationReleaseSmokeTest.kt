package com.arvio.benchmark

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/** Functional checks against the actual non-debuggable release, separate from timing runs. */
@RunWith(AndroidJUnit4::class)
class TvNavigationReleaseSmokeTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val device = UiDevice.getInstance(instrumentation)

    @Before fun openIsolatedProfile() {
        val context = instrumentation.context
        device.executeShellCommand("am force-stop com.arvio.tv")
        device.pressHome()
        context.startActivity(checkNotNull(context.packageManager.getLeanbackLaunchIntentForPackage("com.arvio.tv"))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        device.selectTvPerformanceProfileIfShown(timeoutMs = 5_000)
        device.openTvHome()
    }

    @Test fun rapidReversalThenSelectChangesOnlyTheLatestSetting() {
        device.openTvSettings(playback = true)
        val next = checked("Auto-Play Next")
        val single = checked("Autoplay")
        repeat(8) { device.pressDPadDown() }
        repeat(7) { device.pressDPadUp() }
        device.pressDPadCenter()
        device.waitForIdle(1_000)
        try {
            assertEquals("OK must not act on the previous row", next, checked("Auto-Play Next"))
            assertNotEquals("One OK must activate the new row", single, checked("Autoplay"))
        } finally {
            if (checked("Autoplay") != single) device.pressDPadCenter()
        }
        assertEquals(single, checked("Autoplay"))
    }

    @Test fun screenshotsAndDialogReentryAfterDeepScrolling() {
        screenshot("release-home.png")
        device.openTvSettings(playback = true)
        repeat(15) { device.pressDPadDown() }
        repeat(15) { device.pressDPadUp() }
        screenshot("release-settings.png")
        repeat(10) { device.pressDPadDown() }
        repeat(2) {
            device.pressDPadCenter()
            check(device.wait(Until.hasObject(By.text("Close")), 5_000)) { "A single OK must open the quality dialog" }
            device.pressBack()
            check(device.wait(Until.gone(By.text("Close")), 5_000)) { "Back must dismiss the dialog" }
        }
    }

    private fun checked(title: String): Boolean {
        val switches = device.wait(Until.findObjects(By.checkable(true)), 5_000).orEmpty()
        val row = checkNotNull(switches.firstOrNull { it.text == title || it.findObject(By.text(title)) != null }) {
            "Missing switch for $title"
        }
        return row.isChecked
    }

    private fun screenshot(name: String) {
        Thread.sleep(500) // Accessibility idle alone does not wait for Compose's morph/scroll animations.
        device.waitForIdle(1_000)
        val directory = checkNotNull(instrumentation.context.externalMediaDirs.firstOrNull())
        directory.mkdirs()
        check(device.takeScreenshot(File(directory, name)))
    }
}
