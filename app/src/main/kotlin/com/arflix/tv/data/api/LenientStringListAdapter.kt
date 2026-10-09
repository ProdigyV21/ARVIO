package com.arflix.tv.data.api

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/**
 * A string list that also accepts a bare string (or number), since some addons send
 * `"genres": "Drama"` where the protocol says `["Drama"]`. Strict parsing fails the whole
 * catalog response over one such item.
 */
internal class LenientStringListAdapter : TypeAdapter<List<String>?>() {
    override fun read(reader: JsonReader): List<String>? = when (reader.peek()) {
        JsonToken.NULL -> { reader.nextNull(); null }
        JsonToken.BEGIN_ARRAY -> {
            val values = mutableListOf<String>()
            reader.beginArray()
            while (reader.hasNext()) {
                when (reader.peek()) {
                    JsonToken.STRING, JsonToken.NUMBER -> values += reader.nextString()
                    else -> reader.skipValue()
                }
            }
            reader.endArray()
            values
        }
        JsonToken.STRING, JsonToken.NUMBER -> listOf(reader.nextString()).filter { it.isNotBlank() }
        else -> { reader.skipValue(); null }
    }

    override fun write(writer: JsonWriter, value: List<String>?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.beginArray()
        value.forEach { writer.value(it) }
        writer.endArray()
    }
}
