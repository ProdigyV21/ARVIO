package com.arflix.tv.updater

import android.app.Application
import android.content.Intent
import android.content.pm.PackageInstaller
import android.os.Looper
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.unmockkAll
import io.mockk.verify
import java.io.File
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class ApkInstallWorkerTest {
    private val context: Application get() = RuntimeEnvironment.getApplication()
    private lateinit var apk: File
    private lateinit var manager: WorkManager
    private val status = UpdateStatusManager()

    @Before fun setUp() {
        apk = File.createTempFile("updater-worker-test", ".apk", context.cacheDir)
        manager = mockk(relaxed = true)
        mockkStatic(WorkManager::class)
        every { WorkManager.getInstance(context) } returns manager
        mockkObject(ApkInstaller)
    }

    @After fun tearDown() {
        unmockkAll()
        apk.delete()
    }

    @Test fun launchOnlyEnqueuesWorkAndDoesNotCopyTheApkOnMain() {
        val request = slot<WorkRequest>()
        every { manager.enqueue(capture(request)) } returns mockk(relaxed = true)
        ApkInstaller.launchInstall(context, apk)
        assertEquals(ApkInstallWorker::class.java.name, request.captured.workSpec.workerClassName)
        assertEquals(apk.absolutePath, request.captured.workSpec.input.getString(ApkInstaller.EXTRA_APK_PATH))
        assertTrue(request.captured.workSpec.input.getBoolean(ApkInstaller.EXTRA_WITH_DEX_METADATA, false))
        verify(exactly = 0) { ApkInstaller.installNow(any(), any(), any()) }
    }

    @Test fun receiverRetryOnlyEnqueuesAnApkOnlyJob() {
        val request = slot<WorkRequest>()
        every { manager.enqueue(capture(request)) } returns mockk(relaxed = true)
        assertTrue(ApkInstallReceiver().retryWithoutDexMetadata(context, failureIntent()))
        assertFalse(request.captured.workSpec.input.getBoolean(ApkInstaller.EXTRA_WITH_DEX_METADATA, true))
        verify(exactly = 0) { ApkInstaller.installNow(any(), any(), any()) }
    }

    @Test fun cancelledInstallDoesNotEnqueueARetry() {
        val intent = failureIntent().putExtra(PackageInstaller.EXTRA_STATUS, PackageInstaller.STATUS_FAILURE_ABORTED)
        assertFalse(ApkInstallReceiver().retryWithoutDexMetadata(context, intent))
        verify(exactly = 0) { manager.enqueue(any<WorkRequest>()) }
    }

    @Test fun apkOnlyFailureDoesNotLoop() {
        val intent = failureIntent().putExtra(ApkInstaller.EXTRA_WITH_DEX_METADATA, false)
        assertFalse(ApkInstallReceiver().retryWithoutDexMetadata(context, intent))
        verify(exactly = 0) { manager.enqueue(any<WorkRequest>()) }
    }

    @Test fun workerStagesTheApkOffTheMainThread() = runBlocking {
        every { ApkInstaller.installNow(context, apk, true) } answers {
            assertNotSame(Looper.getMainLooper().thread, Thread.currentThread())
            true
        }
        assertEquals(ListenableWorker.Result.success(), worker(true).doWork())
        verify(exactly = 1) { ApkInstaller.installNow(context, apk, true) }
    }

    @Test fun workerHonorsAnApkOnlyRetry() = runBlocking {
        every { ApkInstaller.installNow(context, apk, false) } returns true
        assertEquals(ListenableWorker.Result.success(), worker(false).doWork())
        verify(exactly = 1) { ApkInstaller.installNow(context, apk, false) }
    }

    @Test fun failedInstallerReportsFailureInsteadOfLeavingInstallingStatus() = runBlocking {
        every { ApkInstaller.installNow(context, apk, true) } returns false
        status.updateStatus(UpdateStatus.Installing(null))
        assertEquals(ListenableWorker.Result.failure(), worker(true).doWork())
        assertTrue(status.status.value is UpdateStatus.Failure)
    }

    @Test fun missingApkFailsWithoutCallingTheInstaller() = runBlocking {
        apk.delete()
        assertEquals(ListenableWorker.Result.failure(), worker(true).doWork())
        assertTrue(status.status.value is UpdateStatus.Failure)
        verify(exactly = 0) { ApkInstaller.installNow(any(), any(), any()) }
    }

    private fun worker(withMetadata: Boolean): ApkInstallWorker {
        val parameters = mockk<WorkerParameters>(relaxed = true)
        every { parameters.inputData } returns workDataOf(
            ApkInstaller.EXTRA_APK_PATH to apk.absolutePath,
            ApkInstaller.EXTRA_WITH_DEX_METADATA to withMetadata
        )
        return ApkInstallWorker(context, parameters, status)
    }

    private fun failureIntent() = Intent()
        .putExtra(PackageInstaller.EXTRA_STATUS, PackageInstaller.STATUS_FAILURE_INVALID)
        .putExtra(ApkInstaller.EXTRA_WITH_DEX_METADATA, true)
        .putExtra(ApkInstaller.EXTRA_APK_PATH, apk.absolutePath)
}
