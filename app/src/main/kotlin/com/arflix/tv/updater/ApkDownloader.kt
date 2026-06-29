package com.arflix.tv.updater

import android.content.Context
import com.arflix.tv.R
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApkDownloader @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient
) {
    suspend fun download(
        url: String,
        destinationFile: File,
        onProgress: (downloadedBytes: Long, totalBytes: Long?) -> Unit
    ): Result<File> {
        return runCatching {
            destinationFile.parentFile?.mkdirs()
            if (destinationFile.exists()) destinationFile.delete()

            // Use a dedicated client with longer timeouts for large APK downloads
            val downloadClient = okHttpClient.newBuilder()
                .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
                .build()

            val request = Request.Builder().url(url).build()
            downloadClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    error(context.getString(R.string.update_error_download_http, response.code))
                }

                val body = response.body ?: error(context.getString(R.string.update_error_empty_download_body))
                val total = body.contentLength().takeIf { it > 0L }
                body.byteStream().use { input ->
                    FileOutputStream(destinationFile).use { output ->
                        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                        var downloaded = 0L
                        while (true) {
                            val read = input.read(buffer)
                            if (read <= 0) break
                            output.write(buffer, 0, read)
                            downloaded += read
                            onProgress(downloaded, total)
                        }
                        output.flush()
                    }
                }
            }

            destinationFile
        }
    }
}
