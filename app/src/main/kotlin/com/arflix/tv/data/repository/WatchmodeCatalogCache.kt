package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.model.MediaType
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchmodeCatalogCache(
    private val directory: File
) {
    @Inject
    constructor(@ApplicationContext context: Context) : this(
        File(context.cacheDir, "watchmode_catalog_refs")
    )

    data class Snapshot(
        val refs: List<Pair<MediaType, Int>>,
        val fetchedAtMs: Long,
        val complete: Boolean
    )

    private data class RefPayload(
        val type: String,
        val id: Int
    )

    private data class Payload(
        val fetchedAtMs: Long,
        val complete: Boolean,
        val refs: List<RefPayload>
    )

    private val gson = Gson()

    fun read(cacheKey: String): Snapshot? {
        val file = fileFor(cacheKey)
        if (!file.exists()) return null
        return runCatching {
            val payload = gson.fromJson(file.readText(), Payload::class.java)
            val refs = payload.refs.mapNotNull { ref ->
                val type = runCatching { MediaType.valueOf(ref.type) }.getOrNull()
                    ?: return@mapNotNull null
                val id = ref.id.takeIf { it > 0 } ?: return@mapNotNull null
                type to id
            }
            Snapshot(
                refs = refs.distinct(),
                fetchedAtMs = payload.fetchedAtMs,
                complete = payload.complete
            )
        }.getOrElse {
            runCatching { file.delete() }
            null
        }
    }

    fun write(cacheKey: String, snapshot: Snapshot) {
        if (snapshot.refs.isEmpty()) return
        runCatching {
            directory.mkdirs()
            val file = fileFor(cacheKey)
            val temp = File(file.parentFile, "${file.name}.tmp")
            val payload = Payload(
                fetchedAtMs = snapshot.fetchedAtMs,
                complete = snapshot.complete,
                refs = snapshot.refs.distinct().map { (type, id) ->
                    RefPayload(type = type.name, id = id)
                }
            )
            temp.writeText(gson.toJson(payload))
            if (!temp.renameTo(file)) {
                file.delete()
                temp.renameTo(file)
            }
        }
    }

    private fun fileFor(cacheKey: String): File {
        return File(directory, "${sha256(cacheKey)}.json")
    }

    companion object {
        fun keyFor(
            sourceId: Int,
            mediaType: String,
            region: String,
            sourceTypes: String?,
            sortBy: String
        ): String = listOf(
            sourceId.toString(),
            mediaType,
            region,
            sourceTypes.orEmpty(),
            sortBy
        ).joinToString(":")

        private fun sha256(value: String): String {
            val bytes = MessageDigest.getInstance("SHA-256")
                .digest(value.toByteArray(Charsets.UTF_8))
            return bytes.joinToString("") { "%02x".format(it) }
        }
    }
}
