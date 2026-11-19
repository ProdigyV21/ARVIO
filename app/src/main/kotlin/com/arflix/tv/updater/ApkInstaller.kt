package com.arflix.tv.updater

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageInstaller
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.WorkerThread
import androidx.core.content.FileProvider
import androidx.profileinstaller.DeviceProfileWriter
import androidx.profileinstaller.ProfileInstaller
import com.arflix.tv.BuildConfig
import com.arflix.tv.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

object ApkInstaller {
    /** Set on the status intent when the session included base.dm, with [EXTRA_APK_PATH]. */
    const val EXTRA_WITH_DEX_METADATA = "com.arflix.tv.updater.WITH_DEX_METADATA"
    const val EXTRA_APK_PATH = "com.arflix.tv.updater.APK_PATH"

    fun canRequestPackageInstalls(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.packageManager.canRequestPackageInstalls()
        } else {
            true
        }
    }

    fun buildUnknownSourcesSettingsIntent(context: Context): Intent? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(
                Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                Uri.parse("package:${context.packageName}")
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        } else {
            null
        }
    }

    /**
     * Check if installing the given APK would cause a signature conflict.
     * Returns a user-friendly error message if there's a conflict, or null if OK.
     */
    fun checkSignatureConflict(context: Context, apkFile: File): String? {
        try {
            val pm = context.packageManager

            // Get the signing info of the APK to install
            val apkInfo: PackageInfo? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.getPackageArchiveInfo(apkFile.absolutePath, PackageManager.PackageInfoFlags.of(PackageManager.GET_SIGNING_CERTIFICATES.toLong()))
            } else {
                @Suppress("DEPRECATION")
                pm.getPackageArchiveInfo(apkFile.absolutePath, PackageManager.GET_SIGNATURES)
            }

            val apkPackage = apkInfo?.packageName ?: return null

            // Get the signing info of the currently installed app
            val installedInfo: PackageInfo? = try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pm.getPackageInfo(apkPackage, PackageManager.PackageInfoFlags.of(PackageManager.GET_SIGNING_CERTIFICATES.toLong()))
                } else {
                    @Suppress("DEPRECATION")
                    pm.getPackageInfo(apkPackage, PackageManager.GET_SIGNATURES)
                }
            } catch (_: PackageManager.NameNotFoundException) {
                return null // Not installed, no conflict possible
            }

            // Compare signatures
            val installedSigs = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                installedInfo?.signingInfo?.apkContentsSigners?.map { it.toByteArray().contentHashCode() } ?: emptyList()
            } else {
                @Suppress("DEPRECATION")
                installedInfo?.signatures?.map { it.toByteArray().contentHashCode() } ?: emptyList()
            }

            val apkSigs = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                apkInfo.signingInfo?.apkContentsSigners?.map { it.toByteArray().contentHashCode() } ?: emptyList()
            } else {
                @Suppress("DEPRECATION")
                apkInfo.signatures?.map { it.toByteArray().contentHashCode() } ?: emptyList()
            }

            if (installedSigs.isNotEmpty() && apkSigs.isNotEmpty() && installedSigs != apkSigs) {
                return context.getString(
                    R.string.update_error_signature_conflict,
                    context.applicationInfo.loadLabel(pm)
                )
            }
        } catch (_: Exception) {
            // Can't check — proceed with install attempt
        }
        return null
    }

    /**
     * Build an intent to uninstall the current app (user must confirm).
     */
    fun buildUninstallIntent(context: Context): Intent {
        return Intent(Intent.ACTION_DELETE, Uri.parse("package:${context.packageName}"))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    /**
     * @param withDexMetadata false skips base.dm; used by [ApkInstallReceiver] to retry once
     * when an install that included it fails.
     */
    fun launchInstall(context: Context, apkFile: File, withDexMetadata: Boolean = true) {
        ApkInstallWorker.enqueue(context, apkFile, withDexMetadata)
    }

    @WorkerThread
    internal fun installNow(context: Context, apkFile: File, withDexMetadata: Boolean): Boolean {
        // Try session-based installer first (smoother UX on Android 5+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                val metadata = if (withDexMetadata) createDexMetadata(context, apkFile) else null
                try {
                    commitSession(context, apkFile, metadata)
                } catch (e: DexMetadataWriteException) {
                    // The failed session may contain a partial base.dm. Never commit it.
                    System.err.println("[ApkInstaller] Retrying a clean APK-only session: ${e.cause?.message}")
                    commitSession(context, apkFile, null)
                }
                return true
            } catch (e: Exception) {
                System.err.println("[ApkInstaller] Session install failed, falling back to ACTION_VIEW: ${e.message}")
            }
        }

        // Fallback: classic ACTION_VIEW install (used when the session path throws OR on API < 21).
        try {
            val uri = FileProvider.getUriForFile(context, "${BuildConfig.APPLICATION_ID}.fileprovider", apkFile)
            val intent = Intent(Intent.ACTION_VIEW)
                .setDataAndType(uri, "application/vnd.android.package-archive")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(intent)
            return true
        } catch (e: Exception) {
            System.err.println("[ApkInstaller] Fallback ACTION_VIEW install failed: ${e.message}")
            return false
        }
    }

    private fun commitSession(context: Context, apkFile: File, metadata: ByteArray?) {
        val installer = context.packageManager.packageInstaller
        val params = PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL)
        params.setSize(apkFile.length() + (metadata?.size ?: 0))
        val sessionId = installer.createSession(params)
        var committed = false
        try {
            installer.openSession(sessionId).use { session ->
                // Named base.apk so the dex metadata can pair with it as base.dm.
                session.openWrite("base.apk", 0, apkFile.length()).use { out ->
                    FileInputStream(apkFile).use { input -> input.copyTo(out) }
                    session.fsync(out)
                }
                if (metadata != null) {
                    try {
                        session.openWrite("base.dm", 0, metadata.size.toLong()).use { out ->
                            out.write(metadata)
                            session.fsync(out)
                        }
                    } catch (e: Exception) {
                        throw DexMetadataWriteException(e)
                    }
                }

                // The receiver opens the system confirmation UI when user action is needed.
                val intent = Intent(ApkInstallReceiver.actionFor(context))
                    .setPackage(context.packageName)
                if (metadata != null) {
                    intent.putExtra(EXTRA_WITH_DEX_METADATA, true)
                        .putExtra(EXTRA_APK_PATH, apkFile.absolutePath)
                }
                val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                } else {
                    PendingIntent.FLAG_UPDATE_CURRENT
                }
                val pendingIntent = PendingIntent.getBroadcast(
                    context.applicationContext, sessionId, intent, flags
                )
                session.commit(pendingIntent.intentSender)
                committed = true
            }
        } finally {
            if (!committed) runCatching { installer.abandonSession(sessionId) }
        }
    }

    private class DexMetadataWriteException(cause: Exception) : Exception(cause)

    /**
     * Adds the APK's own baseline profile to the session as base.dm, so the system compiles
     * the update with it at install time. Without it, a sideloaded update runs uncompiled
     * (slow Home scrolling) until background dexopt picks up the profile, which on TV boxes
     * that rarely sit idle-but-awake can take days.
     *
     * Dex metadata exists from Android 9. The profile is converted to this device's ART
     * format first (Android 12+ reads only a newer format than the one shipped in the APK).
     * Any failure here just leaves the install without dex metadata.
     *
     * Prepared before opening a session, so failures here cannot leave a partial base.dm.
     */
    internal fun createDexMetadata(context: Context, apkFile: File): ByteArray? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) return null
        try {
            val profile = deviceProfileOf(context, apkFile) ?: return null
            val dm = ByteArrayOutputStream()
            ZipOutputStream(dm).use { zip ->
                zip.putNextEntry(ZipEntry("primary.prof"))
                zip.write(profile)
                zip.closeEntry()
            }
            return dm.toByteArray()
        } catch (e: Exception) {
            System.err.println("[ApkInstaller] Skipping dex metadata: ${e.message}")
            return null
        }
    }

    /**
     * The downloaded APK's assets/dexopt profile, transcoded by ProfileInstaller to the format
     * this device's ART expects, the same conversion it does for the installed app on first run.
     * Null when the APK has no profile or the device format isn't supported.
     */
    private fun deviceProfileOf(context: Context, apkFile: File): ByteArray? {
        val pm = context.packageManager
        @Suppress("DEPRECATION")
        val appInfo = pm.getPackageArchiveInfo(apkFile.absolutePath, 0)?.applicationInfo ?: return null
        appInfo.sourceDir = apkFile.absolutePath
        appInfo.publicSourceDir = apkFile.absolutePath
        val assets = pm.getResourcesForApplication(appInfo).assets
        val out = File.createTempFile("update-profile", ".prof", context.cacheDir)
        try {
            val writer = DeviceProfileWriter(
                assets, Runnable::run, NoProfileDiagnostics,
                "base.apk", "dexopt/baseline.prof", "dexopt/baseline.profm", out
            )
            if (!writer.deviceAllowsProfileInstallerAotWrites()) return null
            if (!writer.read().transcodeIfNeeded().write()) return null
            return out.readBytes().takeIf { it.isNotEmpty() }
        } finally {
            out.delete()
        }
    }

    private object NoProfileDiagnostics : ProfileInstaller.DiagnosticsCallback {
        override fun onDiagnosticReceived(code: Int, data: Any?) = Unit
        override fun onResultReceived(code: Int, data: Any?) = Unit
    }
}
