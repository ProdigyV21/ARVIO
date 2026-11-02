package com.arflix.tv.updater

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.content.pm.PackageManager
import android.os.Build
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifyOrder
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.ConscryptMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28], application = Application::class)
@ConscryptMode(ConscryptMode.Mode.OFF)
class ApkInstallerTest {
    private val app: Application get() = RuntimeEnvironment.getApplication()
    private lateinit var context: Context
    private lateinit var installer: PackageInstaller
    private lateinit var first: PackageInstaller.Session
    private lateinit var second: PackageInstaller.Session
    private lateinit var apk: File
    private val apkBytes = byteArrayOf(1, 2, 3, 4)
    private val metadata = byteArrayOf(5, 6, 7, 8)
    private lateinit var firstApk: ByteArrayOutputStream
    private lateinit var secondApk: ByteArrayOutputStream
    private lateinit var firstMetadata: ByteArrayOutputStream

    @Before fun setUp() {
        apk = File.createTempFile("updater-test", ".apk", app.cacheDir).apply { writeBytes(apkBytes) }
        installer = mockk(relaxed = true)
        val pm = mockk<PackageManager>()
        context = mockk()
        every { context.applicationContext } returns app
        every { context.packageName } returns app.packageName
        every { context.packageManager } returns pm
        every { pm.packageInstaller } returns installer
        first = mockk(relaxed = true)
        second = mockk(relaxed = true)
        firstApk = ByteArrayOutputStream()
        secondApk = ByteArrayOutputStream()
        firstMetadata = ByteArrayOutputStream()
        every { installer.createSession(any()) } returnsMany listOf(11, 12)
        every { installer.openSession(11) } returns first
        every { installer.openSession(12) } returns second
        every { first.openWrite("base.apk", 0, any()) } returns firstApk
        every { second.openWrite("base.apk", 0, any()) } returns secondApk
        every { first.openWrite("base.dm", 0, any()) } returns firstMetadata
        mockkObject(ApkInstaller)
        every { ApkInstaller.createDexMetadata(context, apk) } returns metadata
    }

    @After fun tearDown() {
        unmockkAll()
        apk.delete()
    }

    @Test fun successfulProfileInstallPreservesMetadataAndRetryInformation() {
        assertTrue(ApkInstaller.installNow(context, apk, true))
        assertArrayEquals(apkBytes, firstApk.toByteArray())
        assertArrayEquals(metadata, firstMetadata.toByteArray())
        verify(exactly = 1) { first.commit(any()) }
        verify(exactly = 0) { installer.abandonSession(any()) }
        val callback = callbackFor(11)
        assertTrue(callback.getBooleanExtra(ApkInstaller.EXTRA_WITH_DEX_METADATA, false))
        assertEquals(apk.absolutePath, callback.getStringExtra(ApkInstaller.EXTRA_APK_PATH))
    }

    @Test fun failedMetadataOpenStartsACleanApkOnlySession() {
        every { first.openWrite("base.dm", 0, any()) } throws IOException("Cannot open metadata")
        assertCleanFallback()
    }

    @Test fun partialMetadataWriteNeverCommitsTheDamagedSession() {
        val partial = object : ByteArrayOutputStream() {
            override fun write(bytes: ByteArray) {
                super.write(bytes, 0, bytes.size / 2)
                throw IOException("Partial metadata write")
            }
        }
        every { first.openWrite("base.dm", 0, any()) } returns partial
        assertCleanFallback()
        assertTrue(partial.size() in 1 until metadata.size)
    }

    @Test fun metadataFsyncFailureStartsACleanApkOnlySession() {
        every { first.fsync(firstMetadata) } throws IOException("Cannot sync metadata")
        assertCleanFallback()
    }

    @Test fun missingProfileInstallsOnceWithoutMetadata() {
        every { ApkInstaller.createDexMetadata(context, apk) } returns null
        assertTrue(ApkInstaller.installNow(context, apk, true))
        verify(exactly = 1) { installer.createSession(any()) }
        verify(exactly = 0) { first.openWrite("base.dm", any(), any()) }
        assertFalse(callbackFor(11).hasExtra(ApkInstaller.EXTRA_WITH_DEX_METADATA))
    }

    @Test fun apkOnlyRetryNeverTriesToGenerateOrWriteMetadata() {
        assertTrue(ApkInstaller.installNow(context, apk, false))
        verify(exactly = 0) { ApkInstaller.createDexMetadata(any(), any()) }
        verify(exactly = 0) { first.openWrite("base.dm", any(), any()) }
        assertFalse(callbackFor(11).hasExtra(ApkInstaller.EXTRA_WITH_DEX_METADATA))
    }

    @Test fun sessionIsAbandonedEvenWhenOpeningItFails() {
        every { installer.openSession(11) } throws IOException("Session unavailable")
        every { context.startActivity(any()) } throws IllegalStateException("No legacy installer")
        assertFalse(ApkInstaller.installNow(context, apk, false))
        verify(exactly = 1) { installer.abandonSession(11) }
    }

    @Test @Config(sdk = [31])
    fun android12CallbackStillCarriesMetadataRetryInformation() {
        successfulProfileInstallPreservesMetadataAndRetryInformation()
    }

    private fun assertCleanFallback() {
        assertTrue(ApkInstaller.installNow(context, apk, true))
        verify(exactly = 0) { first.commit(any()) }
        verify(exactly = 1) { first.close() }
        verifyOrder {
            installer.abandonSession(11)
            installer.createSession(any())
            second.commit(any())
        }
        assertArrayEquals(apkBytes, secondApk.toByteArray())
        verify(exactly = 0) { second.openWrite("base.dm", any(), any()) }
        assertFalse(callbackFor(12).hasExtra(ApkInstaller.EXTRA_WITH_DEX_METADATA))
    }

    private fun callbackFor(sessionId: Int): Intent {
        val flags = PendingIntent.FLAG_NO_CREATE or
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_MUTABLE else 0
        val pending = PendingIntent.getBroadcast(
            app, sessionId, Intent(ApkInstallReceiver.actionFor(app)).setPackage(app.packageName), flags
        )
        assertNotNull(pending)
        return shadowOf(pending).savedIntent
    }
}
