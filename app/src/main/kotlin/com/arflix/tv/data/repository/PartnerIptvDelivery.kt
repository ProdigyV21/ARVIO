package com.arflix.tv.data.repository

import com.google.gson.JsonObject
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.Addon
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.time.Instant

/** Validated, account-scoped runtime data. Never serialize this into personal settings. */
internal data class PartnerIptvDelivery(
    val revision: Int,
    val expiresAt: Long,
    val playlists: List<IptvPlaylistEntry>,
    val organizationId: String = "",
    val permissions: PartnerPermissions = PartnerPermissions(),
    val catalogs: List<CatalogConfig> = emptyList(),
    val collectionUrls: List<String> = emptyList(),
    val hasCatalogConfiguration: Boolean = false,
    val settings: Map<String, Any> = emptyMap(),
    val additionalServices: List<JsonObject> = emptyList(),
    val addons: List<Addon> = emptyList(),
    val homeServers: List<HomeServerConnection> = emptyList()
) {
    companion object {
        fun parse(body: JsonObject, now: Long): PartnerIptvDelivery? {
            if (body.get("managed")?.asBoolean != true || body.get("active")?.asBoolean != true) return null
            val organization = body.get("organizationId")?.asString.orEmpty()
            require(organization.matches(Regex("[a-zA-Z0-9-]{1,128}"))) { "Invalid organization" }
            val revision = body.get("revision")?.asInt ?: error("Missing revision")
            require(revision >= 0)
            val expires = Instant.parse(body.get("leaseUntil")?.asString).toEpochMilli()
            require(expires > now && expires <= now + 24 * 60 * 60_000L + 60_000L) { "Invalid lease" }
            val config = body.getAsJsonObject("config") ?: error("Missing configuration")
            // Do not acknowledge a revision with resources this client cannot apply.
            val catalogJson = config.getAsJsonArray("catalogs") ?: error("Missing catalogs")
            val collectionUrls = (config.getAsJsonArray("collectionUrls") ?: error("Missing collection feeds"))
                .map { it.asString.also { url -> require(url.toHttpUrlOrNull() != null) } }
            val catalogs = catalogJson.filter { it.asJsonObject.get("enabled")?.asBoolean != false }
                .map { PartnerCatalogs.parse(it.asJsonObject, organization) }
            require(catalogs.map { it.id }.distinct().size == catalogs.size) { "Duplicate catalog" }
            val settings = PartnerPreferencePolicy.parse(config.getAsJsonObject("settings") ?: error("Missing preferences"))
            val permissions = PartnerPermissions.parse(body.getAsJsonObject("permissions") ?: error("Missing permissions"))
            val services = body.getAsJsonArray("services") ?: error("Missing services")
            require(services.all { it.asJsonObject.get("kind")?.asString in setOf("iptv", "addon", "plex", "emby", "jellyfin") }) {
                "Unsupported service"
            }
            require(services.map { it.asJsonObject.get("id")?.asString }.distinct().size == services.size()) { "Duplicate service" }
            val playlists = services.filter { it.asJsonObject.get("kind")?.asString == "iptv" }.map { element ->
                val service = element.asJsonObject
                require(service.get("kind")?.asString == "iptv") { "This package requires additional service support" }
                val id = service.get("id")?.asString.orEmpty()
                require(id.matches(Regex("[a-zA-Z0-9-]{1,128}"))) { "Invalid service ID" }
                val credentials = service.get("credentials")?.takeUnless { it.isJsonNull }?.asJsonObject ?: JsonObject()
                val options = service.getAsJsonObject("iptv") ?: JsonObject()
                fun enabled(key: String) = options.get(key)?.asBoolean != false
                fun credential(key: String) = credentials.get(key)?.takeUnless { it.isJsonNull }?.asString.orEmpty()
                val endpoint = service.get("url")?.asString?.toHttpUrlOrNull() ?: error("Invalid IPTV endpoint")
                val explicit = credential("playlistUrl")
                val playlist = if (explicit.isNotBlank()) {
                    explicit.toHttpUrlOrNull() ?: error("Invalid playlist URL")
                } else if (options.get("mode")?.asString == "m3u" || (options.get("mode")?.asString != "xtream" &&
                    (endpoint.encodedPath.endsWith("get.php") || endpoint.encodedPath.matches(Regex(".*\\.m3u8?", RegexOption.IGNORE_CASE))))) {
                    endpoint
                } else {
                    val username = credential("username")
                    val password = credential("password")
                    require(username.isNotBlank() && password.isNotBlank()) { "Customer credentials required" }
                    val base = endpoint.encodedPath.replace(Regex("/(player_api\\.php|get\\.php)/?$"), "").trimEnd('/')
                    endpoint.newBuilder().encodedPath("$base/get.php").query(null)
                        .addQueryParameter("username", username).addQueryParameter("password", password)
                        .addQueryParameter("type", "m3u_plus").addQueryParameter("output", "ts").build()
                }
                val epg = credential("epgUrl").takeIf { it.isNotBlank() }?.let {
                    it.toHttpUrlOrNull() ?: error("Invalid guide URL")
                } ?: if (playlist.encodedPath.endsWith("/get.php") && playlist.queryParameter("username") != null &&
                    playlist.queryParameter("password") != null) {
                    playlist.newBuilder().encodedPath(playlist.encodedPath.removeSuffix("get.php") + "xmltv.php")
                        .removeAllQueryParameters("type").removeAllQueryParameters("output").build()
                } else null
                IptvPlaylistEntry(
                    id = "partner-$organization-$id", name = service.get("name")?.asString.orEmpty(),
                    m3uUrl = playlist.toString(), epgUrl = if (enabled("epg")) epg?.toString().orEmpty() else "",
                    importLiveTv = enabled("liveTv"), importVod = enabled("movies"), importSeries = enabled("series"),
                    epgEnabled = enabled("liveTv") && enabled("epg"),
                    catchupEnabled = enabled("liveTv") && enabled("epg") && enabled("catchup")
                )
            }
            require(playlists.map { it.id }.distinct().size == playlists.size) { "Duplicate service" }
            return PartnerIptvDelivery(revision, expires, playlists, organization, permissions, catalogs,
                collectionUrls, catalogJson.size() > 0 || collectionUrls.isNotEmpty(), settings,
                services.filter { it.asJsonObject.get("kind")?.asString != "iptv" }.map { it.asJsonObject })
        }
    }
}
