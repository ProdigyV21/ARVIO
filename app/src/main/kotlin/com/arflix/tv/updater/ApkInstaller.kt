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
import androidx.core.content.FileProvider
import com.arflix.tv.BuildConfig
import com.arflix.tv.R
import java.io.File
import java.io.FileInputStream

object ApkInstaller {
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

    fun launchInstall(context: Context, apkFile: File) {
        // Try session-based installer first (smoother UX on Android 5+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                val pm = context.packageManager
                val installer = pm.packageInstaller
                val params = PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL)
                params.setSize(apkFile.length())

                val sessionId = installer.createSession(params)
                val session = installer.openSession(sessionId)

                session.openWrite("app_update", 0, apkFile.length()).use { out ->
                    FileInputStream(apkFile).use { input ->
                        input.copyTo(out)
                    }
                    session.fsync(out)
                }

                // Use a broadcast PendingIntent instead of activity — works reliably
                // on Android TV where the Application context isn't an Activity.
                // The action is per-applicationId so it's unique per flavor / install.
                // ApkInstallReceiver (registered in the manifest) picks up the callback and
                // launches the system install-confirmation Activity on STATUS_PENDING_USER_ACTION,
                // without which the session commit succeeds silently but no install ever happens.
                val intent = Intent(ApkInstallReceiver.actionFor(context))
                    .setPackage(context.packageName)
                val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                } else {
                    PendingIntent.FLAG_UPDATE_CURRENT
                }
                val pendingIntent = PendingIntent.getBroadcast(
                    context.applicationContext, sessionId, intent, flags
                )

                session.commit(pendingIntent.intentSender)
                session.close()
                return
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
        } catch (e: Exception) {
            System.err.println("[ApkInstaller] Fallback ACTION_VIEW install failed: ${e.message}")
        }
    }
}
