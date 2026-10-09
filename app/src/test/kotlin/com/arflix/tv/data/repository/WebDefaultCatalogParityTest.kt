package com.arflix.tv.data.repository

import com.arflix.tv.data.model.CatalogKind
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import java.io.File
import java.util.Locale
import org.junit.Assert.assertEquals
import org.junit.Test

class WebDefaultCatalogParityTest {
    @Test fun `web defaults match the APK catalogs artwork queries and ordering`() {
        val gson = Gson()
        val television = setOf("trending_tv", "trending_anime", "favorite_tv", "top10_shows_today", "new_kdramas")
        val expected = JsonArray().apply {
            MediaRepository.buildPreinstalledDefaults().forEach { catalog ->
                add(gson.toJsonTree(catalog).asJsonObject.apply {
                    addProperty("name", catalog.title)
                    addProperty("sourceType", catalog.sourceType.name.lowercase(Locale.US))
                    addProperty("enabled", true)
                    if (catalog.kind == CatalogKind.STANDARD) {
                        addProperty("mediaType", if (catalog.id in television) "tv" else "movie")
                    }
                })
            }
        }
        val root = sequenceOf(File("."), File("..")).first { File(it, "web/lib").isDirectory }
        val snapshot = File(root, "web/lib/defaultCatalogs.json")
        // Explicit regeneration only; normal test runs never modify the source tree.
        if (System.getenv("ARVIO_EXPORT_WEB_DEFAULTS") == "1") {
            snapshot.writeText(GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(expected) + "\n")
        }
        assertEquals("Regenerate with ARVIO_EXPORT_WEB_DEFAULTS=1 and this test", expected,
            JsonParser.parseString(snapshot.readText()))
    }
}
