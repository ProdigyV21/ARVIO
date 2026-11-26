package com.arflix.tv.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CatalogTitleTest {
    @Test
    fun `empty parts an export joins into a catalog title are dropped`() {
        assertThat(cleanCatalogTitle("Latest · None")).isEqualTo("Latest")
        assertThat(cleanCatalogTitle("Top 10 · null")).isEqualTo("Top 10")
        assertThat(cleanCatalogTitle("Latest Telugu Movies")).isEqualTo("Latest Telugu Movies")
        assertThat(cleanCatalogTitle("Netflix · Drama")).isEqualTo("Netflix · Drama")
        assertThat(cleanCatalogTitle("None")).isNull()
        assertThat(cleanCatalogTitle("  ")).isNull()
        assertThat(cleanCatalogTitle(null)).isNull()
    }

    @Test
    fun `no-value genre spellings are not labels`() {
        assertThat(meaningfulLabel("None")).isNull()
        assertThat(meaningfulLabel(" undefined ")).isNull()
        assertThat(meaningfulLabel("Action")).isEqualTo("Action")
    }
}
