package com.arflix.tv.data.repository

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CatalogSourceType
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.Locale
import java.net.URLDecoder
import java.net.URLEncoder

data class PartnerPermissions(
    val editCatalogs: Boolean = false,
    val addConnections: Boolean = false,
    val editAppearance: Boolean = false,
    val editPlayback: Boolean = false,
    val editMetadata: Boolean = false
) {
    companion object {
        fun parse(json: JsonObject): PartnerPermissions {
            fun allowed(key: String): Boolean {
                val value = json.get(key)
                require(value?.isJsonPrimitive == true && value.asJsonPrimitive.isBoolean) { "Missing permission" }
                return value.asBoolean
            }
            return PartnerPermissions(allowed("editCatalogs"), allowed("addConnections"),
                allowed("editAppearance"), allowed("editPlayback"), allowed("editMetadata"))
        }
    }
}

/** Convert the shared wire format without letting Gson create null Kotlin defaults. */
internal object PartnerCatalogs {
    private val gson = Gson()
    fun id(organization: String, id: String) = "partner-$organization-$id"
    fun isManaged(id: String) = id.startsWith("partner-")

    fun parse(json: JsonObject, organization: String): CatalogConfig {
        val rawId = json.get("id")?.asString.orEmpty()
        val title = (json.get("name") ?: json.get("title"))?.asString.orEmpty()
        require(rawId.isNotBlank() && title.isNotBlank()) { "Invalid catalog" }
        val type = json.get("sourceType")?.asString.orEmpty().uppercase(Locale.ROOT).replace('-', '_')
        val source = if (type == "TEMPLATE") CatalogSourceType.PREINSTALLED else CatalogSourceType.valueOf(type)
        val defaults = gson.toJsonTree(CatalogConfig(rawId, title, source)).asJsonObject
        json.entrySet().forEach { (key, value) -> if (!value.isJsonNull) defaults.add(key, value) }
        defaults.addProperty("sourceType", source.name)
        defaults.addProperty("title", title)
        listOf("kind", "collectionGroup", "collectionTileShape").forEach { key ->
            defaults.get(key)?.takeUnless { it.isJsonNull }?.let {
                defaults.addProperty(key, it.asString.uppercase(Locale.ROOT).replace('-', '_'))
            }
        }
        defaults.getAsJsonArray("collectionSources")?.forEach { item ->
            val sourceJson = item.asJsonObject
            sourceJson.get("kind")?.let { sourceJson.addProperty("kind", it.asString.uppercase(Locale.ROOT).replace('-', '_')) }
        }
        val catalog = gson.fromJson(defaults, CatalogConfig::class.java)
        require(catalog.kind != null && catalog.collectionTileShape != null) { "Invalid catalog layout" }
        require(catalog.kind != CatalogKind.COLLECTION || catalog.collectionSources.isNotEmpty()) { "Empty collection" }
        require(catalog.collectionSources.all { it.kind != null }) { "Unknown collection source" }
        if (source == CatalogSourceType.PREINSTALLED && catalog.kind == CatalogKind.STANDARD) {
            val builtIn = MediaRepository.buildPreinstalledDefaults().firstOrNull { it.id == rawId }
                ?: error("Unknown built-in catalog")
            return namespace(builtIn.copy(title = title, sourceRef = builtIn.id), organization)
        }
        return namespace(catalog.copy(sourceRef = catalog.sourceRef ?: rawId.takeIf {
            source == CatalogSourceType.PREINSTALLED && catalog.kind == CatalogKind.STANDARD
        }), organization)
    }

    fun namespace(catalog: CatalogConfig, organization: String): CatalogConfig = catalog.copy(
        id = id(organization, catalog.id), isPreinstalled = false,
        packId = catalog.packId?.let { id(organization, it) },
        collectionRailKey = catalog.collectionRailKey?.let { id(organization, it) }
    )

    fun overlay(personal: List<CatalogConfig>, delivery: PartnerIptvDelivery?): List<CatalogConfig> {
        if (delivery == null || !delivery.hasCatalogConfiguration) return personal
        return delivery.catalogs + if (delivery.permissions.editCatalogs) {
            personal.filterNot { it.isPreinstalled || isManaged(it.id) }
        } else emptyList()
    }

    fun remapReferences(catalog: CatalogConfig, aliases: Map<String, String>): CatalogConfig {
        val ref = catalog.sourceRef?.let { original ->
            val parts = original.split('|').toMutableList()
            if (parts.size >= 3 && parts[0] in setOf("addon_catalog", "home_server_catalog")) {
                aliases[URLDecoder.decode(parts[1], "UTF-8")]?.let { parts[1] = URLEncoder.encode(it, "UTF-8") }
                parts.joinToString("|")
            } else original
        }
        return catalog.copy(sourceRef = ref, addonId = aliases[catalog.addonId] ?: catalog.addonId,
            collectionSources = catalog.collectionSources.map { it.copy(addonId = aliases[it.addonId] ?: it.addonId) })
    }
}
