package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import io.mockk.mockk
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test

class CatalogRepositoryLegacyCompatibilityTest {
    @Test
    fun `SIMKL restores from enum reference or valid URL across native and browser payloads`() {
        val repository = CatalogRepository(mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true))
        val parse = CatalogRepository::class.java.getDeclaredMethod("parseCatalogsJson", String::class.java).apply { isAccessible = true }
        @Suppress("UNCHECKED_CAST")
        val restored = parse.invoke(repository, """[
            {"id":"enum","title":"My list","sourceType":"simkl","sourceUrl":"https://simkl.com/5/list/14462"},
            {"id":"ref","title":"My list","sourceType":"UNKNOWN","sourceRef":"simkl_list:14462"},
            {"id":"url","title":"My list","sourceType":"TRAKT","sourceUrl":"https://simkl.com/5/list/14462/slug"}
        ]""") as List<CatalogConfig>
        assertEquals(3, restored.size)
        assertEquals(listOf(CatalogSourceType.SIMKL, CatalogSourceType.SIMKL, CatalogSourceType.SIMKL), restored.map { it.sourceType })
    }

    @Test
    fun `TMDB restores from enum reference or URL without losing titles or order`() {
        val repository = CatalogRepository(
            context = mockk<Context>(relaxed = true),
            profileManager = mockk<ProfileManager>(relaxed = true),
            traktApi = mockk<TraktApi>(relaxed = true),
            okHttpClient = mockk<OkHttpClient>(relaxed = true),
            invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)
        )
        val parse = CatalogRepository::class.java.getDeclaredMethod("parseCatalogsJson", String::class.java)
            .apply { isAccessible = true }
        val json = """[
            {"id":"enum","title":"Renamed","sourceType":"TMDB","sourceRef":"tmdb:list:99:"},
            {"id":"ref","title":"Reference","sourceType":"UNKNOWN","sourceRef":"tmdb:company:3:tv"},
            {"id":"url","title":"URL","sourceType":"TRAKT","sourceUrl":"https://www.themoviedb.org/collection/1241"}
        ]"""
        @Suppress("UNCHECKED_CAST")
        val restored = parse.invoke(repository, json) as List<CatalogConfig>
        assertEquals(3, restored.size)
        assertEquals(listOf(CatalogSourceType.TMDB, CatalogSourceType.TMDB, CatalogSourceType.TMDB), restored.map { it.sourceType })
        assertEquals("Renamed", restored.first().title)
        assertEquals("tmdb:list:99:", restored.first().sourceRef)
        assertEquals(listOf("enum", "ref", "url"), restored.map { it.id })
    }

    @Test
    fun `retired sports defaults are excluded but custom sports catalogs survive`() {
        val repository = CatalogRepository(
            context = mockk<Context>(relaxed = true),
            profileManager = mockk<ProfileManager>(relaxed = true),
            traktApi = mockk<TraktApi>(relaxed = true),
            okHttpClient = mockk<OkHttpClient>(relaxed = true),
            invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)
        )
        val parse = CatalogRepository::class.java.getDeclaredMethod(
            "parseCatalogsJson", String::class.java
        ).apply { isAccessible = true }
        val retired = """
            [{"id":"sports","title":"Sports","sourceType":"PREINSTALLED","isPreinstalled":true},
             {"id":"popular_live_tv","title":"Popular Live Sports","sourceType":"PREINSTALLED"}]
        """.trimIndent()
        @Suppress("UNCHECKED_CAST")
        fun decode(json: String) = parse.invoke(repository, json) as List<CatalogConfig>
        assertEquals(emptyList<CatalogConfig>(), decode(retired))
        val mixed = retired.dropLast(1) + """,
            {"id":"addon_sports","title":"Sports","sourceType":"ADDON","addonId":"my-addon","sourceRef":"my-addon"},
            {"id":"trending_movies","title":"Trending Movies","sourceType":"PREINSTALLED"}]
        """.trimIndent()
        assertEquals(listOf("addon_sports", "trending_movies"), decode(mixed).map { it.id })
        assertEquals(false, MediaRepository.buildPreinstalledDefaults().any {
            it.id == "sports" || it.id == "popular_live_tv"
        })
    }

    @Test
    fun `catalog without kind defaults to standard`() {
        val repository = CatalogRepository(
            context = mockk<Context>(relaxed = true),
            profileManager = mockk<ProfileManager>(relaxed = true),
            traktApi = mockk<TraktApi>(relaxed = true),
            okHttpClient = mockk<OkHttpClient>(relaxed = true),
            invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)
        )
        val legacyJson = """
            [
              {
                "id": "legacy_catalog",
                "title": "Legacy Catalog",
                "sourceType": "PREINSTALLED"
              }
            ]
        """.trimIndent()

        val parseMethod = CatalogRepository::class.java.getDeclaredMethod(
            "parseCatalogsJson",
            String::class.java
        ).apply {
            isAccessible = true
        }

        @Suppress("UNCHECKED_CAST")
        val parsed = parseMethod.invoke(repository, legacyJson) as List<CatalogConfig>

        assertEquals(1, parsed.size)
        assertEquals(CatalogKind.STANDARD, parsed.single().kind)
    }
}
