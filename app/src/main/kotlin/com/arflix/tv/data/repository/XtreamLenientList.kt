package com.arflix.tv.data.repository

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import java.io.Reader

/**
 * Reads an Xtream catalog answer as a list of [itemClass].
 *
 * Accepts the usual JSON array and the index-keyed object some panels send
 * (`{"0": {...}, "1": {...}}`). Each entry is read on its own, so an entry
 * that cannot be mapped is dropped instead of failing the whole list. Anything
 * else (an error object, `false`, an HTML page) gives an empty list.
 */
internal fun <T> readLenientXtreamList(gson: Gson, reader: Reader, itemClass: Class<T>): List<T> {
    val json = JsonReader(reader).apply { isLenient = true }
    val items = ArrayList<T>()
    fun readItem() {
        if (json.peek() != JsonToken.BEGIN_OBJECT) {
            json.skipValue()
            return
        }
        // One entry as a small tree: a failing entry leaves the stream intact.
        val element = JsonParser.parseReader(json)
        runCatching { gson.fromJson(element, itemClass) }.getOrNull()?.let(items::add)
    }
    when (json.peek()) {
        JsonToken.BEGIN_ARRAY -> {
            json.beginArray()
            while (json.hasNext()) readItem()
            json.endArray()
        }
        JsonToken.BEGIN_OBJECT -> {
            json.beginObject()
            while (json.hasNext()) {
                // Only an index-keyed object is a list; `{"user_info": ...}` is not.
                if (json.nextName().trim().all(Char::isDigit)) readItem() else json.skipValue()
            }
            json.endObject()
        }
        else -> json.skipValue()
    }
    return items
}
