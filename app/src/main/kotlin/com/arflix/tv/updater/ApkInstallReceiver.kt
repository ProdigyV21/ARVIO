package com.arflix.tv.updater

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.arflix.tv.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Handles PackageInstaller session callbacks for the in-app APK updater.
 *
 * The Android [PackageInstaller] session API requires user confirmation for non-privileged
 * apps. It delivers the result by firing the supplied PendingIntent with
 * [PackageInstaller.EXTRA_STATUS] == [PackageInstaller.STATUS_PENDING_USER_ACTION] and an
 * [Intent.EXTRA_INTENT] containing the system install-confirmation Activity. Without a
 * receiver to pick that up and start the confirm Activity, the "Installing update..." flow
 * hangs forever and no install ever happens — which is exactly what was reported in
 * issues #116, #99, and #75 for versions 1.9.3 through 1.9.73.
 */
@AndroidEntryPoint
class ApkInstallReceiver : BroadcastReceiver() {

    @Inject
    lateinit var updateStatusManager: UpdateStatusManager

    override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getIntExtra(PackageInstaller.EXTRA_STATUS, -999)
        val message = intent.getStringExtra(PackageInstaller.EXTRA_STATUS_MESSAGE)

        when (status) {
            PackageInstaller.STATUS_PENDING_USER_ACTION -> {
                // The system needs user confirmation — launch the confirm Activity.
                val confirmIntent: Intent? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(Intent.EXTRA_INTENT, Intent::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra(Intent.EXTRA_INTENT)
                }

                if (confirmIntent == null) {
                    Log.e(TAG, "STATUS_PENDING_USER_ACTION without EXTRA_INTENT — cannot prompt user.")
                    return
                }

                confirmIntent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

                try {
                    context.startActivity(confirmIntent)
                    // We are waiting for the user to confirm in the system UI
                    updateStatusManager.updateStatus(
                        UpdateStatus.Installing(null) // We don't have the full AppUpdate object here, but the status type indicates what's happening
                    )
                } catch (e: Exception) {
                    // Some Android TV forks (particularly Chinese AOSP variants) don't
                    // handle the system confirm intent correctly. Log but don't crash.
                    Log.e(TAG, "Failed to launch install confirmation Activity: ${e.message}", e)
                    showToast(context, context.getString(R.string.update_install_manual_confirm))
                }
            }

            PackageInstaller.STATUS_SUCCESS -> {
                Log.i(TAG, "Update installed successfully.")
                updateStatusManager.updateStatus(UpdateStatus.Success)
                // No toast needed — the new APK is installing/replacing the running process.
            }

            PackageInstaller.STATUS_FAILURE,
            PackageInstaller.STATUS_FAILURE_ABORTED,
            PackageInstaller.STATUS_FAILURE_BLOCKED,
            PackageInstaller.STATUS_FAILURE_CONFLICT,
            PackageInstaller.STATUS_FAILURE_INCOMPATIBLE,
            PackageInstaller.STATUS_FAILURE_INVALID,
            PackageInstaller.STATUS_FAILURE_STORAGE -> {
                Log.e(TAG, "Update install failed: status=$status message=$message")
                val userMessage = when (status) {
                    PackageInstaller.STATUS_FAILURE_ABORTED -> context.getString(R.string.update_install_cancelled)
                    PackageInstaller.STATUS_FAILURE_BLOCKED -> context.getString(R.string.update_install_blocked)
                    PackageInstaller.STATUS_FAILURE_CONFLICT -> context.getString(R.string.update_install_conflict)
                    PackageInstaller.STATUS_FAILURE_INCOMPATIBLE -> context.getString(R.string.update_install_incompatible)
                    PackageInstaller.STATUS_FAILURE_INVALID -> context.getString(R.string.update_install_invalid)
                    PackageInstaller.STATUS_FAILURE_STORAGE -> context.getString(R.string.update_install_storage)
                    else -> message ?: context.getString(R.string.update_install_failed)
                }
                updateStatusManager.updateStatus(UpdateStatus.Failure(userMessage))
                showToast(context, userMessage)
            }

            else -> {
                Log.w(TAG, "Unexpected PackageInstaller status=$status message=$message")
            }
        }
    }

    private fun showToast(context: Context, text: String) {
        try {
            Toast.makeText(context.applicationContext, text, Toast.LENGTH_LONG).show()
        } catch (_: Exception) {
            // Receiver may not have a main looper in some paths; swallow silently.
        }
    }

    companion object {
        private const val TAG = "ApkInstallReceiver"

        /**
         * The broadcast action used for PackageInstaller session callbacks. Derived from the
         * applicationId at runtime so it's unique per build flavor (e.g. `.staging`) and
         * cannot collide with other installs of ARVIO on the same device.
         */
        fun actionFor(context: Context): String {
            return "${context.packageName}.INSTALL_COMPLETE"
        }
    }
}
