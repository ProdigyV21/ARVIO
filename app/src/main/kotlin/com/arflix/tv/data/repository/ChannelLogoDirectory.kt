package com.arflix.tv.data.repository

import android.content.Context
import android.util.JsonReader
import com.arflix.tv.data.model.ChannelLogoEntry
import com.arflix.tv.data.model.ChannelLogoIndex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

object ChannelLogoDirectory {
    private val mutex = Mutex()
    @Volatile private var index: ChannelLogoIndex? = null

    suspend fun candidates(context: Context, epgId: String?, name: String): List<String> = withContext(Dispatchers.IO) {
        val directory = index ?: mutex.withLock {
            index ?: run {
                val entries = mutableListOf<ChannelLogoEntry>()
                context.applicationContext.assets.open("channel-logos.json").bufferedReader().use { input ->
                    JsonReader(input).use { reader ->
                        reader.beginObject()
                        while (reader.hasNext()) {
                            if (reader.nextName() != "entries") { reader.skipValue(); continue }
                            reader.beginArray()
                            while (reader.hasNext()) {
                                reader.beginArray()
                                val id = reader.nextString()
                                val country = reader.nextString()
                                val names = reader.strings()
                                val urls = reader.strings()
                                reader.endArray()
                                entries += ChannelLogoEntry(id, country, names, urls)
                            }
                            reader.endArray()
                        }
                        reader.endObject()
                    }
                }
                ChannelLogoIndex(entries).also { index = it }
            }
        }
        directory.candidates(epgId, name)
    }

    private fun JsonReader.strings(): List<String> {
        beginArray()
        val values = mutableListOf<String>()
        while (hasNext()) values += nextString()
        endArray()
        return values
    }
}
