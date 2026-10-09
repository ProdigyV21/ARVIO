package com.arflix.tv.data.api

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LenientStringListAdapterTest {
    private val gson = Gson()

    @Test fun catalogWithBareStringGenresStillParses() {
        val json = """{"metas":[{"id":"a","genres":["Drama","Comedy"]},{"id":"b","genres":"Documentary"},{"id":"c","genres":null},{"id":"d"}]}"""
        val metas = gson.fromJson(json, StremioCatalogResponse::class.java).metas!!
        assertEquals(listOf("Drama", "Comedy"), metas[0].genres)
        assertEquals(listOf("Documentary"), metas[1].genres)
        assertNull(metas[2].genres)
        assertNull(metas[3].genres)
    }
}
