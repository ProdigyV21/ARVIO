package com.arvio.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

internal fun MacrobenchmarkScope.openHome() = device.openTvHome()
internal fun MacrobenchmarkScope.openSettings(playback: Boolean) = device.openTvSettings(playback)

internal fun UiDevice.selectTvPerformanceProfileIfShown(timeoutMs: Long = 1_000) {
    if (!wait(Until.hasObject(By.text("Who's watching?")), timeoutMs)) return
    val profile = checkNotNull(wait(Until.findObject(By.text("TV Performance")), 5_000)) {
        "Use only the isolated TV Performance profile"
    }
    // TV profiles accept D-pad input, not touch. Allow their existing input guard to finish.
    Thread.sleep(500)
    repeat(5) { pressDPadLeft() }
    val label = profile.visibleBounds
    val focused = checkNotNull(findObject(By.focused(true))).visibleBounds
    check(focused.centerX() in label.left..label.right && focused.bottom < label.top) {
        "The QA avatar must be focused before selecting a profile"
    }
    pressDPadCenter()
    check(wait(Until.gone(By.text("Who's watching?")), 5_000)) { "The isolated profile did not open" }
}

internal fun UiDevice.openTvHome() {
    // The screen owns D-pad navigation; top-bar chips are decorative, not click targets.
    repeat(24) { pressDPadUp() }
    repeat(8) { pressDPadLeft() }
    repeat(2) { pressDPadRight() }
    pressDPadCenter()
    check(wait(Until.hasObject(By.text("Trending Movies")), 20_000)) { "Home catalogs did not load" }
    repeat(24) { pressDPadUp() }
    pressDPadDown()
    pressDPadDown()
}

internal fun UiDevice.openTvSettings(playback: Boolean) {
    repeat(24) { pressDPadUp() }
    repeat(8) { pressDPadLeft() }
    repeat(5) { pressDPadRight() }
    pressDPadCenter()
    check(wait(Until.hasObject(By.text("Accounts")), 10_000)) { "Settings did not open" }
    repeat(24) { pressDPadUp() }
    pressDPadDown()
    if (playback) {
        repeat(2) { pressDPadDown() }
        pressDPadRight()
        check(wait(Until.hasObject(By.textContains("Auto")), 5_000)) { "Playback settings did not open" }
    }
}
