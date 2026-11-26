package com.arflix.tv.data.repository

import com.arflix.tv.data.api.LenientIntAdapter
import com.arflix.tv.data.api.LenientStringAdapter
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.StringReader

class XtreamLenientListTest {
    private data class Item(
        @SerializedName("stream_id") @JsonAdapter(LenientIntAdapter::class) val streamId: Int? = null,
        @SerializedName("name") @JsonAdapter(LenientStringAdapter::class) val name: String? = null,
        @SerializedName("category_id") @JsonAdapter(LenientStringAdapter::class) val categoryId: String? = null,
    )

    private fun read(json: String) = readLenientXtreamList(Gson(), StringReader(json), Item::class.java)

    @Test
    fun `loosely typed fields do not fail the list`() {
        val items = read(
            """[{"stream_id":"12","name":"Ταινία","category_id":5},
               {"stream_id":"","name":"Без номера","category_id":[1,2]},
               {"stream_id":13.0,"name":42,"category_id":true}]"""
        )
        assertEquals(
            listOf(Item(12, "Ταινία", "5"), Item(null, "Без номера", null), Item(13, "42", "true")),
            items,
        )
    }

    @Test
    fun `index keyed objects are read as a list`() {
        assertEquals(listOf(Item(1, "a"), Item(2, "b")), read("""{"0":{"stream_id":1,"name":"a"},"1":{"stream_id":2,"name":"b"}}"""))
    }

    @Test
    fun `error answers give an empty list`() {
        assertEquals(emptyList<Item>(), read("""{"user_info":{"auth":0}}"""))
        assertEquals(emptyList<Item>(), read("false"))
        assertEquals(emptyList<Item>(), read("""[1, "x", null]"""))
    }
}
