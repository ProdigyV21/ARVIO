package com.arflix.tv.updater

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.arflix.tv.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class ApkInstallWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
    private val updateStatusManager: UpdateStatusManager
) : CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val apkFile = inputData.getString(ApkInstaller.EXTRA_APK_PATH)?.let(::File)
        val installed = apkFile?.isFile == true && ApkInstaller.installNow(
            applicationContext,
            apkFile,
            withDexMetadata = inputData.getBoolean(ApkInstaller.EXTRA_WITH_DEX_METADATA, true)
        )
        if (installed) {
            Result.success()
        } else {
            updateStatusManager.updateStatus(
                UpdateStatus.Failure(applicationContext.getString(R.string.update_install_failed))
            )
            Result.failure()
        }
    }

    companion object {
        internal fun enqueue(context: Context, apkFile: File, withDexMetadata: Boolean) {
            // Durable work keeps both initial installs and receiver-triggered retries off Main,
            // without keeping a broadcast open while a large APK is copied and fsynced.
            val request = OneTimeWorkRequestBuilder<ApkInstallWorker>()
                .setInputData(workDataOf(
                    ApkInstaller.EXTRA_APK_PATH to apkFile.absolutePath,
                    ApkInstaller.EXTRA_WITH_DEX_METADATA to withDexMetadata
                ))
                .build()
            WorkManager.getInstance(context.applicationContext).enqueue(request)
        }
    }
}
