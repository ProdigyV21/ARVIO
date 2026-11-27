package com.arflix.tv.data.api

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/**
 * An optional number that also accepts `"123"`, `"123.0"`, `""`, `true`/`false`
 * or a stray object. IPTV panels are loose about types: one `"stream_id": ""`
 * in a 40k-title VOD list used to fail the whole list, so the provider showed
 * no VOD at all. Unreadable values become null and only that item is skipped.
 */
internal class LenientIntAdapter : TypeAdapter<Int?>() {
    override fun read(reader: JsonReader): Int? = when (reader.peek()) {
        JsonToken.NULL -> { reader.nextNull(); null }
        JsonToken.NUMBER, JsonToken.STRING -> parseLenientInt(reader.nextString())
        JsonToken.BOOLEAN -> if (reader.nextBoolean()) 1 else 0
        else -> { reader.skipValue(); null }
    }

    override fun write(writer: JsonWriter, value: Int?) {
        if (value == null) writer.nullValue() else writer.value(value.toLong())
    }
}

/**
 * An optional string that also accepts numbers and booleans and turns arrays or
 * objects into null instead of failing the whole response.
 */
internal class LenientStringAdapter : TypeAdapter<String?>() {
    override fun read(reader: JsonReader): String? = when (reader.peek()) {
        JsonToken.NULL -> { reader.nextNull(); null }
        JsonToken.STRING, JsonToken.NUMBER -> reader.nextString()
        JsonToken.BOOLEAN -> reader.nextBoolean().toString()
        else -> { reader.skipValue(); null }
    }

    override fun write(writer: JsonWriter, value: String?) {
        if (value == null) writer.nullValue() else writer.value(value)
    }
}

internal fun parseLenientInt(raw: String?): Int? {
    val text = raw?.trim().orEmpty()
    if (text.isEmpty()) return null
    text.toIntOrNull()?.let { return it }
    val asDouble = text.toDoubleOrNull() ?: return null
    if (asDouble.isNaN() || asDouble < Int.MIN_VALUE || asDouble > Int.MAX_VALUE) return null
    if (asDouble != Math.floor(asDouble)) return null
    return asDouble.toInt()
}
