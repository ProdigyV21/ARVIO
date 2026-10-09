package com.arflix.tv.data.repository.simkl

import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.security.MessageDigest
import java.security.SecureRandom
import okio.ByteString.Companion.toByteString

class SimklListsException(val reason: String, val status: Int = 0) : IOException(when (reason) {
    "premium_only" -> "SIMKL custom lists require a SIMKL PRO or VIP account."
    "oauth2_token_required", "user_token_required", "invalid_grant", "user_token_failed", "expired_token" -> "Connect SIMKL Lists in Add catalogue to access custom lists."
    "invalid_client", "client_id_failed" -> "SIMKL Lists requires ARVIO's AUTH V2 client configuration."
    "private_list" -> "This SIMKL list is private. Connect an account that has access."
    "url_failed" -> "This SIMKL list could not be found."
    "rate_limit", "slow_down" -> "SIMKL is limiting requests. Please try again later."
    else -> "SIMKL could not load this list ($reason)."
})

data class SimklListItem(
    val title: String, val year: Int?, val type: String, val animeType: String?,
    val tmdb: Int?, val imdb: String?, val tvdb: Int?, val poster: String?
)

data class SimklCustomList(
    val id: Long, val name: String, val mediaType: String, val description: String?,
    val ownerId: Long?, val ownerName: String?, val slug: String?, val updatedAt: String?,
    val count: Int?, val likes: Int?, val auto: Boolean, val posters: List<String>,
    val items: List<SimklListItem> = emptyList()
) {
    val url: String get() = if (ownerId != null) "https://simkl.com/$ownerId/list/$id" else "https://simkl.com/lists/$id"
}

data class SimklListsDeviceCode(val deviceCode: String, val userCode: String, val url: String, val expiresIn: Int, val interval: Int, val verifier: String)
data class SimklListsGrant(val accessToken: String, val refreshToken: String, val expiresAt: Long, val userId: Long? = null,
    val connectionId: String = java.util.UUID.randomUUID().toString())

/** Read-only custom-list API. This deliberately never fetches simkl.com HTML. */
class SimklListsClient(
    private val http: OkHttpClient, private val clientId: String,
    private val baseUrl: HttpUrl = "https://api.simkl.com/".toHttpUrl(),
    private val version: String = "2.0"
) {
    private val gson = Gson()

    suspend fun startDevice(): SimklListsDeviceCode {
        val verifier = ByteArray(32).also { SecureRandom().nextBytes(it) }.toByteString().base64Url().trimEnd('=')
        val challenge = MessageDigest.getInstance("SHA-256").digest(verifier.toByteArray(Charsets.US_ASCII)).toByteString().base64Url().trimEnd('=')
        val body = request("oauth2/device", form = mapOf("client_id" to clientId, "scope" to "media:read media:write",
            "code_challenge" to challenge, "code_challenge_method" to "S256"))
        return SimklListsDeviceCode(body.required("device_code"), body.required("user_code"),
            body.text("verification_uri_complete") ?: body.required("verification_uri"),
            body.number("expires_in") ?: 900, (body.number("interval") ?: 5).coerceAtLeast(5), verifier)
    }

    suspend fun poll(session: SimklListsDeviceCode): SimklListsGrant = grant(request("oauth2/token", form = mapOf(
        "client_id" to clientId, "grant_type" to "urn:ietf:params:oauth:grant-type:device_code",
        "device_code" to session.deviceCode, "code_verifier" to session.verifier)))

    suspend fun refresh(refreshToken: String): SimklListsGrant = grant(request("oauth2/token", form = mapOf(
        "client_id" to clientId, "grant_type" to "refresh_token", "refresh_token" to refreshToken)))

    private fun grant(body: JsonObject): SimklListsGrant {
        if (!body.text("token_type").equals("Bearer", true)) throw SimklListsException("invalid_response")
        if (body.text("scope")?.split(' ')?.containsAll(listOf("media:read", "media:write")) != true) throw SimklListsException("invalid_scope")
        return SimklListsGrant(body.required("access_token"), body.required("refresh_token"),
            System.currentTimeMillis() + (body.number("expires_in") ?: 604800).toLong() * 1000)
    }

    suspend fun userId(token: String): Long = request("users/settings", token, jsonPost = true)
        .getAsJsonObject("account")?.text("id")?.toLongOrNull()?.takeIf { it > 0 }
        ?: throw SimklListsException("invalid_response")

    suspend fun list(id: Long, token: String): SimklCustomList {
        require(id > 0)
        val first = request("lists/$id", token, query = mapOf("limit" to "500", "page" to "1"))
        val metadata = parseList(first)
        val items = parseItems(first)
        val pages = pages(first)
        // SIMKL cannot paginate beyond 10,000; don't silently present a truncated list.
        if (pages > 20) throw SimklListsException("list_exceeds_api_limit")
        val all = items.toMutableList()
        for (page in 2..pages) all += parseItems(request("lists/$id", token,
            query = mapOf("limit" to "500", "page" to page.toString())))
        return metadata.copy(items = all.distinct())
    }

    suspend fun userLists(userId: Long, token: String, previewOnly: Boolean = false, sort: String = "updated"): List<SimklCustomList> {
        val result = mutableListOf<SimklCustomList>()
        var page = 1
        do {
            val query = mapOf("limit" to "500", "page" to page.toString()) + if (previewOnly)
                mapOf("sort" to sort, "direction" to "desc") else mapOf("followed" to "true", "collaborants" to "true")
            val body = request("lists/user/$userId", token, query = query)
            val lists = body.getAsJsonArray("lists") ?: throw SimklListsException("invalid_response")
            result += lists.map { parseList(it.asJsonObject) }
            val total = if (previewOnly) 1 else pages(body)
            if (total > 20) throw SimklListsException("list_exceeds_api_limit")
            page++
        } while (page <= total)
        return result.distinctBy { it.id }
    }

    suspend fun officialLists(token: String): List<SimklCustomList> =
        (userLists(5, token, previewOnly = true) + userLists(5, token, previewOnly = true, sort = "popularity")).distinctBy { it.id }

    private fun pages(body: JsonObject): Int = (body.getAsJsonObject("pagination")?.number("total_pages") ?: 1).coerceAtLeast(1)

    private fun parseList(body: JsonObject): SimklCustomList {
        val id = body.required("id").toLongOrNull()?.takeIf { it > 0 } ?: throw SimklListsException("invalid_response")
        val user = body.getAsJsonObject("user")
        val counts = body.getAsJsonObject("counts")
        return SimklCustomList(id, body.required("name"), body.text("media_type") ?: "movies", body.text("description"),
            user?.text("id")?.toLongOrNull(), user?.text("name") ?: user?.text("username"), body.text("slug"),
            body.text("updated_at"), counts?.number("items"), counts?.number("likes"), body.text("type") == "auto",
            body.getAsJsonArray("top_items")?.mapNotNull { poster(it.asJsonObject.text("poster")) }.orEmpty())
    }

    private fun parseItems(body: JsonObject): List<SimklListItem> {
        val type = body.text("media_type") ?: "movies"
        val items = body.getAsJsonArray("items") ?: throw SimklListsException("invalid_response")
        return items.mapNotNull { element ->
            val item = element.asJsonObject
            val title = item.text("title") ?: return@mapNotNull null
            val ids = item.getAsJsonObject("ids")
            SimklListItem(title, item.number("year"), item.text("type") ?: type, item.text("anime_type"),
                ids?.number("tmdb"), ids?.text("imdb"), ids?.number("tvdb"), poster(item.text("poster")))
        }
    }

    private suspend fun request(path: String, token: String? = null, query: Map<String, String> = emptyMap(),
        form: Map<String, String>? = null, jsonPost: Boolean = false): JsonObject = withContext(Dispatchers.IO) {
        if (clientId.isBlank()) throw SimklListsException("invalid_client")
        val url = baseUrl.newBuilder().addPathSegments(path).addQueryParameter("client_id", clientId)
            .addQueryParameter("app-name", "arvio").addQueryParameter("app-version", version)
        query.forEach { (key, value) -> url.addQueryParameter(key, value) }
        val builder = Request.Builder().url(url.build()).header("User-Agent", "ARVIO/$version")
        token?.let { builder.header("Authorization", "Bearer $it") }
        if (form != null) builder.post(FormBody.Builder().apply { form.forEach { (key, value) -> add(key, value) } }.build())
        if (jsonPost) builder.post("{}".toRequestBody("application/json".toMediaType()))
        http.newCall(builder.build()).execute().use { response ->
            val body = runCatching { gson.fromJson(response.body?.string(), JsonObject::class.java) }.getOrNull()
                ?: throw SimklListsException("invalid_response", response.code)
            val error = body.text("error")
            if (!response.isSuccessful || error != null) throw SimklListsException(error ?: "http_${response.code}", response.code)
            body
        }
    }

    companion object {
        fun poster(value: String?): String? = value?.takeIf { it.isNotBlank() }?.let {
            if (it.startsWith("https://simkl.in/")) it else if (!it.contains(":") && !it.contains("..")) "https://simkl.in/posters/${it}_m.webp" else null
        }
    }
}

private fun JsonObject.text(key: String): String? = get(key)?.takeIf { it.isJsonPrimitive }?.asString?.takeIf { it.isNotBlank() }
private fun JsonObject.number(key: String): Int? = text(key)?.toIntOrNull()
private fun JsonObject.required(key: String): String = text(key) ?: throw SimklListsException("invalid_response")
