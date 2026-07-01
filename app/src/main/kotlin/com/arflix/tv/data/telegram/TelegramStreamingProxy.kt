package com.arflix.tv.data.telegram

import android.util.Log
import io.ktor.http.ContentRange
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.response.respondBytesWriter
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.utils.io.writeFully
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import org.drinkless.tdlib.TdApi
import java.net.ServerSocket
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Runs a local Ktor HTTP server on a random port.
 * ExoPlayer streams from http://localhost:PORT/file/{fileId}
 * with full Range request support.
 *
 * TDLib downloads the requested byte range on demand via downloadFile
 * with offset + limit parameters, enabling seek without full download.
 */
@Singleton
class TelegramStreamingProxy @Inject constructor(
    private val client: TelegramClient
) {
    companion object {
        private const val TAG = "TelegramProxy"
        private const val CHUNK_SIZE = 2 * 1024 * 1024       // 2 MB served per ExoPlayer request
        private const val PREFETCH_SIZE = 20 * 1024 * 1024L  // 20 MB prefetch window sent to TDLib
        private const val DOWNLOAD_TIMEOUT_MS = 30_000L
        private const val DOWNLOAD_PRIORITY = 32              // max TDLib priority
        private const val POLL_INTERVAL_MS = 100L
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var port: Int = 0
    private var server: io.ktor.server.engine.ApplicationEngine? = null
    @Volatile private var lastStreamedFileId: Int? = null

    fun start() {
        if (server != null) return
        port = findFreePort()
        server = embeddedServer(CIO, port = port) {
            routing {
                get("/file/{fileId}") {
                    val fileId = call.parameters["fileId"]?.toIntOrNull()
                    Log.d(TAG, "Request: fileId=$fileId range=${call.request.headers[HttpHeaders.Range]}")
                    if (fileId == null) {
                        call.respond(HttpStatusCode.BadRequest)
                        return@get
                    }

                    // When a new file starts streaming, clean up the previous one so
                    // TDLib's downloaded chunks don't accumulate in tdlib_files/ indefinitely.
                    val prev = lastStreamedFileId
                    if (prev != null && prev != fileId) {
                        scope.launch { deleteFile(prev) }
                    }
                    lastStreamedFileId = fileId

                    val rangeHeader = call.request.headers[HttpHeaders.Range]
                    val (rangeStart, rangeEnd) = parseRange(rangeHeader)

                    // Get file info to know total size
                    val fileInfo = getFileInfo(fileId)
                    val totalSize = fileInfo?.second ?: 0L
                    val localPath = fileInfo?.first
                    Log.d(TAG, "FileInfo: fileId=$fileId totalSize=$totalSize localPath=$localPath")

                    if (totalSize <= 0L) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }

                    val start = rangeStart ?: 0L
                    val end = rangeEnd ?: (totalSize - 1L)
                    val length = end - start + 1

                    call.response.header(HttpHeaders.ContentLength, length.toString())
                    call.response.header(HttpHeaders.AcceptRanges, "bytes")
                    call.response.header(
                        HttpHeaders.ContentRange,
                        "bytes $start-$end/$totalSize"
                    )

                    val status = if (rangeHeader != null) HttpStatusCode.PartialContent else HttpStatusCode.OK

                    call.respondBytesWriter(
                        contentType = ContentType.Video.Any,
                        status = status
                    ) {
                        var offset = start
                        while (offset <= end) {
                            val chunkSize = minOf(CHUNK_SIZE.toLong(), end - offset + 1).toInt()
                            val bytes = downloadChunk(fileId, localPath, offset, chunkSize)
                            if (bytes == null || bytes.isEmpty()) break
                            writeFully(bytes)
                            offset += bytes.size
                        }
                    }
                }
            }
        }
        server!!.start(wait = false)
        Log.d(TAG, "Streaming proxy started on port $port")
    }

    fun stop() {
        lastStreamedFileId?.let { scope.launch { deleteFile(it) } }
        lastStreamedFileId = null
        server?.stop(0, 0)
        server = null
        Log.d(TAG, "Streaming proxy stopped")
    }

    private suspend fun deleteFile(fileId: Int) {
        runCatching {
            client.sendRequest(TdApi.CancelDownloadFile().also { req ->
                req.fileId = fileId
                req.onlyIfPending = false
            })
        }
        runCatching {
            client.sendRequest(TdApi.DeleteFile().also { it.fileId = fileId })
            Log.d(TAG, "Deleted cached file $fileId")
        }
    }

    fun getUrl(fileId: Int): String {
        val url = "http://localhost:$port/file/$fileId"
        Log.d(TAG, "Generated stream URL: $url")
        return url
    }

    /**
     * Downloads a chunk of the file via TDLib and returns the raw bytes.
     * Uses DownloadFile to ensure the range is cached, then ReadFilePart to read it.
     */
    private suspend fun downloadChunk(
        fileId: Int,
        @Suppress("UNUSED_PARAMETER") localPath: String?,
        offset: Long,
        limit: Int
    ): ByteArray? {
        // Ask TDLib to prefetch a large window (non-blocking), but only wait for chunk_size bytes
        withTimeoutOrNull(DOWNLOAD_TIMEOUT_MS) {
            client.sendRequest(TdApi.DownloadFile().also { req ->
                req.fileId = fileId
                req.priority = DOWNLOAD_PRIORITY
                req.offset = offset
                req.limit = PREFETCH_SIZE   // download 20 MB ahead in background
                req.synchronous = false     // don't block — TDLib downloads while we poll
            })
        }

        // Poll until just the current chunk is available
        val ready = withTimeoutOrNull(DOWNLOAD_TIMEOUT_MS) {
            var attempts = 0
            while (attempts < 300) {
                val file = client.sendRequest(TdApi.GetFile(fileId)) as? TdApi.File
                val local = file?.local
                if (local != null && (local.isDownloadingCompleted || local.downloadedPrefixSize >= limit)) {
                    return@withTimeoutOrNull true
                }
                delay(POLL_INTERVAL_MS)
                attempts++
            }
            false
        }
        if (ready != true) return null

        // Read the bytes directly via TDLib — no file path or skip arithmetic needed
        val data = client.sendRequest(
            TdApi.ReadFilePart(fileId, offset, limit.toLong())
        ) as? TdApi.Data
        return data?.data?.takeIf { it.isNotEmpty() }
    }

    /** Returns (localPath, totalSize) for the file, or null if unavailable. */
    private suspend fun getFileInfo(fileId: Int): Pair<String?, Long>? {
        val file = client.sendRequest(TdApi.GetFile(fileId)) as? TdApi.File ?: return null
        val totalSize = file.size.takeIf { it > 0 } ?: file.expectedSize
        val localPath = file.local?.path?.takeIf { it.isNotBlank() }
        return Pair(localPath, totalSize)
    }

    private fun parseRange(header: String?): Pair<Long?, Long?> {
        if (header == null) return Pair(null, null)
        return try {
            val range = header.removePrefix("bytes=")
            val parts = range.split("-")
            val start = parts.getOrNull(0)?.toLongOrNull()
            val end = parts.getOrNull(1)?.toLongOrNull()
            Pair(start, end)
        } catch (e: Exception) {
            if (e is kotlinx.coroutines.CancellationException) throw e

            Pair(null, null)
        }
    }

    private fun findFreePort(): Int {
        ServerSocket(0).use { return it.localPort }
    }
}
