package com.arflix.tv.updater

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class UpdateStatusManagerTest {
    private val update = AppUpdate(
        tag = "v9.9",
        title = "Test release",
        notes = "",
        releaseUrl = null,
        assetName = "app.apk",
        assetUrl = "http://example.invalid/app.apk",
        assetSizeBytes = 1L,
    )

    @Test fun retryInstallsTheSameApkAfterAndroidsConfirmationWentAway() {
        val manager = UpdateStatusManager()
        manager.updateStatus(UpdateStatus.Installing(update, preparing = 0f, apkPath = "/cache/app.apk"))
        // Still copying: nothing to retry yet.
        assertNull(manager.status.value.installableApk())

        manager.waitForConfirmation()

        val status = manager.status.value as UpdateStatus.Installing
        assertNull(status.preparing)
        assertEquals("/cache/app.apk" to update, status.installableApk())
    }

    @Test fun cancelledInstallRetriesWithTheDownloadedApk() {
        val manager = UpdateStatusManager()
        manager.updateStatus(UpdateStatus.Installing(update, apkPath = "/cache/app.apk"))
        manager.installFailed("Update cancelled.", keepApk = true)
        val failure = manager.status.value as UpdateStatus.Failure
        assertEquals("Update cancelled.", failure.message)
        assertEquals("/cache/app.apk" to update, failure.installableApk())
    }

    @Test fun otherInstallFailuresDownloadAgainOnRetry() {
        val manager = UpdateStatusManager()
        manager.updateStatus(UpdateStatus.Installing(update, apkPath = "/cache/app.apk"))
        manager.installFailed("The update file is invalid.", keepApk = false)
        val failure = manager.status.value as UpdateStatus.Failure
        assertEquals(update, failure.update)
        assertNull(failure.installableApk())
    }

    @Test fun readyToInstallIsInstallable() {
        assertEquals(
            "/cache/app.apk" to update,
            UpdateStatus.ReadyToInstall("/cache/app.apk", update).installableApk(),
        )
    }

    @Test fun confirmationWithoutAnInstallInProgressHasNothingToRetry() {
        val manager = UpdateStatusManager()
        manager.waitForConfirmation()
        assertNull(manager.status.value.installableApk())
    }

    @Test fun permissionReturnContinuesTheInstallOnlyOnce() {
        val manager = UpdateStatusManager()
        assertFalse(manager.takeInstallAfterPermission())
        manager.awaitInstallPermission()
        assertTrue(manager.takeInstallAfterPermission())
        assertFalse(manager.takeInstallAfterPermission())
    }
}
