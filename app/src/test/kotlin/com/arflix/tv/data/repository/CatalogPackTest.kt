package com.arflix.tv.data.repository

import android.content.Context
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogPackManifest
import com.arflix.tv.data.model.CatalogPackItem
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CatalogValidationResult
import com.arflix.tv.data.api.TraktApi
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Assert.*
import org.junit.Test

class CatalogPackTest {

    @Test
    fun `fetchCatalogPackManifest rejects duplicate catalog URLs`() = runBlocking {
        val context = mockk<Context>(relaxed = true)
        val profileManager = mockk<ProfileManager>(relaxed = true)
        val traktApi = mockk<TraktApi>(relaxed = true)
        val okHttpClient = mockk<OkHttpClient>(relaxed = true)
        val invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)

        val repository = spyk(CatalogRepository(context, profileManager, traktApi, okHttpClient, invalidationBus))

        val duplicateManifestJson = """
            {
              "id": "test-pack",
              "name": "Test Pack",
              "author": "Tester",
              "version": "1.0.0",
              "catalogs": [
                {
                  "name": "Catalog 1",
                  "url": "https://mdblist.com/lists/snoak/trending-movies"
                },
                {
                  "name": "Catalog 2",
                  "url": "https://mdblist.com/lists/snoak/trending-movies/"
                }
              ]
            }
        """.trimIndent()

        coEvery { repository["fetchUrl"](any<String>()) } returns duplicateManifestJson

        val result = repository.fetchCatalogPackManifest("https://example.com/pack.json")
        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertNotNull(exception)
        assertTrue(exception is CatalogException)
        assertEquals(R.string.catalog_pack_duplicate_url, (exception as CatalogException).messageRes)
        assertEquals(listOf("https://mdblist.com/lists/snoak/trending-movies"), exception.formatArgs)
    }

    @Test
    fun `addCatalogPack derives unique pack ID from normalized manifest URL plus manifest ID`() = runBlocking {
        val context = mockk<Context>(relaxed = true)
        val profileManager = mockk<ProfileManager>(relaxed = true)
        val traktApi = mockk<TraktApi>(relaxed = true)
        val okHttpClient = mockk<OkHttpClient>(relaxed = true)
        val invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)

        val repository = spyk(CatalogRepository(context, profileManager, traktApi, okHttpClient, invalidationBus))

        val addedCatalogs = mutableListOf<CatalogConfig>()
        coEvery { repository.getCatalogs() } returns emptyList()
        coEvery { repository["saveCatalogs"](any<List<CatalogConfig>>()) } answers {
            addedCatalogs.addAll(firstArg<List<CatalogConfig>>())
        }

        coEvery { repository.validateCatalogUrl(any()) } returns CatalogValidationResult(
            isValid = true,
            normalizedUrl = "https://mdblist.com/lists/snoak/trending-movies",
            sourceType = CatalogSourceType.MDBLIST
        )

        val manifest = CatalogPackManifest(
            id = "same-id",
            name = "Test Pack",
            author = "Tester",
            version = "1.0.0",
            description = "Desc",
            catalogs = listOf(
                CatalogPackItem(name = "Catalog 1", url = "https://mdblist.com/lists/snoak/trending-movies")
            )
        )

        val result1 = repository.addCatalogPack("https://example.com/pack1.json", manifest)
        assertTrue(result1.isSuccess)
        val packId1 = addedCatalogs[0].packId
        assertNotNull(packId1)

        addedCatalogs.clear()

        val result2 = repository.addCatalogPack("https://example.com/pack2.json", manifest)
        assertTrue(result2.isSuccess)
        val packId2 = addedCatalogs[0].packId
        assertNotNull(packId2)

        assertNotEquals(packId1, packId2)
    }

    @Test
    fun `addCatalogPack with pre-fetched manifest avoids network call`() = runBlocking {
        val context = mockk<Context>(relaxed = true)
        val profileManager = mockk<ProfileManager>(relaxed = true)
        val traktApi = mockk<TraktApi>(relaxed = true)
        val okHttpClient = mockk<OkHttpClient>(relaxed = true)
        val invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)

        val repository = spyk(CatalogRepository(context, profileManager, traktApi, okHttpClient, invalidationBus))

        coEvery { repository.getCatalogs() } returns emptyList()
        coEvery { repository["saveCatalogs"](any<List<CatalogConfig>>()) } returns Unit

        coEvery { repository.validateCatalogUrl(any()) } returns CatalogValidationResult(
            isValid = true,
            normalizedUrl = "https://mdblist.com/lists/snoak/trending-movies",
            sourceType = CatalogSourceType.MDBLIST
        )

        val manifest = CatalogPackManifest(
            id = "test-pack",
            name = "Test Pack",
            author = "Tester",
            version = "1.0.0",
            description = "Desc",
            catalogs = listOf(
                CatalogPackItem(name = "Catalog 1", url = "https://mdblist.com/lists/snoak/trending-movies")
            )
        )

        val result = repository.addCatalogPack("https://example.com/pack.json", manifest)
        assertTrue(result.isSuccess)

        coVerify(exactly = 0) { repository["fetchUrl"](any<String>()) }
    }

    @Test
    fun `fetchCatalogPackManifest rejects malformed manifests`() = runBlocking {
        val context = mockk<Context>(relaxed = true)
        val profileManager = mockk<ProfileManager>(relaxed = true)
        val traktApi = mockk<TraktApi>(relaxed = true)
        val okHttpClient = mockk<OkHttpClient>(relaxed = true)
        val invalidationBus = mockk<CloudSyncInvalidationBus>(relaxed = true)

        val repository = spyk(CatalogRepository(context, profileManager, traktApi, okHttpClient, invalidationBus))

        // 1. Missing ID
        val missingIdJson = """
            {
              "name": "Test Pack",
              "catalogs": [{"name": "C1", "url": "https://example.com/c1"}]
            }
        """.trimIndent()
        coEvery { repository["fetchUrl"](any<String>()) } returns missingIdJson
        val res1 = repository.fetchCatalogPackManifest("https://example.com/pack.json")
        assertTrue(res1.isFailure)

        // 2. Empty catalogs list
        val emptyCatalogsJson = """
            {
              "id": "id",
              "name": "Test Pack",
              "catalogs": []
            }
        """.trimIndent()
        coEvery { repository["fetchUrl"](any<String>()) } returns emptyCatalogsJson
        val res2 = repository.fetchCatalogPackManifest("https://example.com/pack.json")
        assertTrue(res2.isFailure)
    }
}
