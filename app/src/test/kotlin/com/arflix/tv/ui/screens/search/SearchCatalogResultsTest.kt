package com.arflix.tv.ui.screens.search

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.data.model.CollectionGroupKind
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchCatalogResultsTest {

    @Test
    fun `catalog search prioritizes exact and prefix service matches`() {
        val results = searchCatalogResults(
            query = "netflix",
            catalogs = listOf(
                catalog(
                    id = "genre_action",
                    title = "Action från Netflix",
                    group = CollectionGroupKind.GENRE
                ),
                catalog(
                    id = "service_netflix",
                    title = "Netflix",
                    group = CollectionGroupKind.SERVICE
                ),
                catalog(
                    id = "service_netflix_new",
                    title = "Netflix - nytt i veckan",
                    group = CollectionGroupKind.SERVICE
                )
            )
        )

        assertThat(results.map { it.catalogId }).containsExactly(
            "service_netflix",
            "service_netflix_new",
            "genre_action"
        ).inOrder()
        assertThat(results.first().sourceLabel).isEqualTo("Majo")
    }

    @Test
    fun `catalog search matches description group and source metadata`() {
        val results = searchCatalogResults(
            query = "julfilmer",
            catalogs = listOf(
                catalog(
                    id = "season_christmas_movies",
                    title = "Vinter och högtider",
                    description = "Julfilmer för december",
                    group = CollectionGroupKind.FEATURED,
                    sourceType = CatalogSourceType.MDBLIST
                ),
                catalog(
                    id = "service_disney",
                    title = "Disney+",
                    description = "Familjefilm och serier",
                    group = CollectionGroupKind.SERVICE
                )
            )
        )

        assertThat(results).hasSize(1)
        assertThat(results.single().catalogId).isEqualTo("season_christmas_movies")
        assertThat(results.single().sourceLabel).isEqualTo("MDBList")
    }

    private fun catalog(
        id: String,
        title: String,
        description: String? = null,
        group: CollectionGroupKind? = null,
        sourceType: CatalogSourceType = CatalogSourceType.PREINSTALLED
    ): CatalogConfig {
        return CatalogConfig(
            id = id,
            title = title,
            sourceType = sourceType,
            collectionGroup = group,
            collectionDescription = description
        )
    }
}
