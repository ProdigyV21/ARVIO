package com.arflix.tv.baselineprofile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generateBaselineProfile() {
        baselineProfileRule.collect(
            packageName = "com.arvio.tv",
            profileBlock = {
                pressHome()
                startActivityAndWait()
                collectHomeInteractions()
            }
        )
    }
}

private fun MacrobenchmarkScope.collectHomeInteractions() {
    // Allow first frame + compose settle.
    Thread.sleep(1200)

    // DPAD navigation across a row and down/up to touch next rows.
    repeat(8) { device.pressDPadRight(); Thread.sleep(120) }
    repeat(2) { device.pressDPadDown(); Thread.sleep(200) }
    repeat(6) { device.pressDPadRight(); Thread.sleep(120) }
    repeat(2) { device.pressDPadUp(); Thread.sleep(200) }
    repeat(6) { device.pressDPadLeft(); Thread.sleep(120) }
}
