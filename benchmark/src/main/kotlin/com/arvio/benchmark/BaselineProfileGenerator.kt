package com.arvio.benchmark

import android.content.pm.PackageManager
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.Assume.assumeTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val baselineRule = BaselineProfileRule()

    @Test
    fun generateTvNavigation() {
        assumeTrue(InstrumentationRegistry.getInstrumentation().context.packageManager
            .hasSystemFeature(PackageManager.FEATURE_LEANBACK))
        baselineRule.collect(packageName = TARGET_PACKAGE, maxIterations = 5) {
            pressHome()
            startActivityAndWait()
            device.selectTvPerformanceProfileIfShown()
            openHome()
            repeat(2) {
                repeat(6) { device.pressDPadRight(); Thread.sleep(120) }
                repeat(6) { device.pressDPadLeft(); Thread.sleep(120) }
                repeat(3) { device.pressDPadDown(); Thread.sleep(180) }
                repeat(3) { device.pressDPadUp(); Thread.sleep(180) }
            }
            openSettings(playback = false)
            repeat(10) { device.pressDPadDown(); Thread.sleep(120) }
            repeat(10) { device.pressDPadUp(); Thread.sleep(120) }
            // Return through Home to use the same deterministic entry point.
            device.pressBack()
            openHome()
            openSettings(playback = true)
            repeat(15) { device.pressDPadDown(); Thread.sleep(120) }
            repeat(15) { device.pressDPadUp(); Thread.sleep(120) }
        }
    }

    @Test
    fun generate() {
        baselineRule.collect(
            packageName = TARGET_PACKAGE,
            maxIterations = 5
        ) {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.pkg(TARGET_PACKAGE).depth(0)), 8_000)

            // Let home compose and initial rows settle.
            device.waitForIdle(1_000)
            if (device.wait(Until.hasObject(By.textContains("watching")), 1_200)) {
                device.pressDPadCenter()
                device.waitForIdle(1_000)
            }

            // Home: cover the hot TV rail paths. Most user-visible jank happens
            // while moving within large rows and crossing heavy catalog sections.
            device.pressDPadDown()
            device.waitForIdle(500)
            repeat(12) {
                device.pressDPadRight()
                device.waitForIdle(120)
            }
            repeat(8) {
                device.pressDPadLeft()
                device.waitForIdle(120)
            }
            repeat(7) {
                device.pressDPadDown()
                device.waitForIdle(180)
            }
            repeat(5) {
                device.pressDPadUp()
                device.waitForIdle(180)
            }

            // Details: open the focused item and cover the lower TV/detail rows.
            device.pressDPadCenter()
            device.waitForIdle(2_000)
            repeat(8) {
                device.pressDPadDown()
                device.waitForIdle(180)
            }
            repeat(6) {
                device.pressDPadUp()
                device.waitForIdle(180)
            }
        }
    }

    companion object {
        private const val TARGET_PACKAGE = "com.arvio.tv"
    }
}
