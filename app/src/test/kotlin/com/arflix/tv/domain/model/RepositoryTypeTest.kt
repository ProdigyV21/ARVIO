package com.arflix.tv.domain.model

import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryTypeTest {
    private val adapter = Moshi.Builder().build().adapter(RepositoryType::class.java)

    @Test fun pluginListsSavedByEarlierVersionsStillLoad() {
        assertEquals(RepositoryType.JS_PLUGIN, adapter.fromJson("\"$LEGACY_JS_PLUGIN_NAME\""))
        assertEquals(RepositoryType.EXTERNAL_DEX, adapter.fromJson("\"EXTERNAL_DEX\""))
        // Saved again under the same name, so an older app version can still read it.
        assertEquals("\"$LEGACY_JS_PLUGIN_NAME\"", adapter.toJson(RepositoryType.JS_PLUGIN))
    }

    @Test fun syncedTypeHintsMapToTheRenamedType() {
        assertEquals(RepositoryType.JS_PLUGIN, RepositoryType.fromStored(LEGACY_JS_PLUGIN_NAME))
        assertEquals(RepositoryType.JS_PLUGIN, RepositoryType.fromStored("JS_PLUGIN"))
        assertEquals(RepositoryType.EXTERNAL_DEX, RepositoryType.fromStored("EXTERNAL_DEX"))
        assertEquals(null, RepositoryType.fromStored("SOMETHING_ELSE"))
        assertEquals(null, RepositoryType.fromStored(null))
    }
}
