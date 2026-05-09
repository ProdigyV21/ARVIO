package com.arflix.tv.data.repository

import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.data.model.CatalogDiscoveryResult
import com.arflix.tv.data.model.CatalogSourceType
import com.arflix.tv.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogDiscoveryRepository @Inject constructor(
    private val traktApi: TraktApi,
    private val okHttpClient: OkHttpClient
) {
    suspend fun searchCatalogLists(query: String): Result<List<CatalogDiscoveryResult>> = withContext(Dispatchers.IO) {
        val normalizedQuery = query.trim()
        if (normalizedQuery.length < 2) {
            return@withContext Result.success(emptyList())
        }

        val trakt = runCatching { searchTraktLists(normalizedQuery) }
        val mdblist = runCatching { searchMdblistLists(normalizedQuery) }

        val combined = (trakt.getOrDefault(emptyList()) + mdblist.getOrDefault(emptyList()))
            .distinctBy { it.sourceUrl.lowercase() }
            .sortedWith(
                compareByDescending<CatalogDiscoveryResult> { relevanceScore(normalizedQuery, it) > 0 }
                    .thenByDescending { it.likes ?: 0 }
                    .thenByDescending { relevanceScore(normalizedQuery, it) }
                    .thenByDescending { it.itemCount ?: 0 }
            )
            .take(24)

        if (combined.isNotEmpty() || trakt.isSuccess || mdblist.isSuccess) {
            Result.success(combined)
        } else {
            Result.failure(
                trakt.exceptionOrNull()
                    ?: mdblist.exceptionOrNull()
                    ?: IllegalStateException("Failed to search catalogs")
            )
        }
    }

    private suspend fun searchTraktLists(query: String): List<CatalogDiscoveryResult> {
        return traktApi.searchLists(
            clientId = Constants.TRAKT_CLIENT_ID,
            query = query,
            limit = 40
        )
            .asSequence()
            .mapNotNull { result ->
                val list = result.list ?: return@mapNotNull null
                if (!list.privacy.equals("public", ignoreCase = true)) return@mapNotNull null

                val userSlug = list.user?.ids?.slug?.takeIf { it.isNotBlank() }
                    ?: list.user?.username?.takeIf { it.isNotBlank() }
                    ?: return@mapNotNull null
                val listSlug = list.ids?.slug?.takeIf { it.isNotBlank() } ?: return@mapNotNull null
                val title = list.name?.trim().orEmpty()
                if (title.isBlank()) return@mapNotNull null

                val url = "https://trakt.tv/users/$userSlug/lists/$listSlug"
                CatalogDiscoveryResult(
                    id = "trakt:$userSlug:$listSlug",
                    title = title,
                    description = list.description?.trim()?.takeIf { it.isNotBlank() },
                    sourceType = CatalogSourceType.TRAKT,
                    sourceUrl = url,
                    creatorName = list.user?.name?.trim()?.takeIf { it.isNotBlank() },
                    creatorHandle = userSlug,
                    updatedAt = list.updatedAt,
                    itemCount = list.itemCount,
                    likes = list.likes,
                    previewPosterUrls = list.images?.posters.orEmpty()
                        .asSequence()
                        .map { normalizePosterUrl(it) }
                        .filter { it.isNotBlank() }
                        .distinct()
                        .take(5)
                        .toList()
                )
            }
            .distinctBy { it.sourceUrl.lowercase() }
            .toList()
    }

    private fun searchMdblistLists(query: String): List<CatalogDiscoveryResult> {
        val encodedQuery = URLEncoder.encode(query, Charsets.UTF_8.name())
        val request = Request.Builder()
            .url("https://mdblist.com/toplists/?public_list_name=$encodedQuery&preferences=bot_test_message")
            .header("User-Agent", "ARVIO")
            .get()
            .build()
        val html = okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return emptyList()
            response.body?.string().orEmpty()
        }
        if (html.isBlank()) return emptyList()

        val document = Jsoup.parse(html, "https://mdblist.com")
        return document.select("article.related-list-card")
            .asSequence()
            .mapNotNull { card ->
                val titleLink = card.selectFirst(".related-list-meta__title a") ?: return@mapNotNull null
                val title = titleLink.text().trim()
                if (title.isBlank()) return@mapNotNull null

                val href = titleLink.attr("abs:href").ifBlank { titleLink.attr("href") }
                if (href.isBlank()) return@mapNotNull null
                val sourceUrl = when {
                    href.startsWith("http", ignoreCase = true) -> href
                    href.startsWith("/") -> "https://mdblist.com$href"
                    else -> "https://mdblist.com/$href"
                }
                val pathParts = sourceUrl.substringAfter("mdblist.com/lists/", "")
                    .split('/')
                    .filter { it.isNotBlank() }
                if (pathParts.size < 2) return@mapNotNull null

                val user = card.selectFirst(".related-list-meta__user")
                val creatorHandle = pathParts.firstOrNull()
                val itemCount = card.selectFirst(".related-list-meta__items")
                    ?.text()
                    ?.substringBefore(" items")
                    ?.filter { it.isDigit() }
                    ?.toIntOrNull()
                val likes = card.selectFirst(".related-list-meta__likes")
                    ?.text()
                    ?.filter { it.isDigit() }
                    ?.toIntOrNull()

                CatalogDiscoveryResult(
                    id = "mdblist:${pathParts[0]}:${pathParts[1]}",
                    title = title,
                    description = card.selectFirst(".related-list-meta__type")
                        ?.text()
                        ?.trim()
                        ?.takeIf { it.isNotBlank() }
                        ?.let { "$it list from MDBList" },
                    sourceType = CatalogSourceType.MDBLIST,
                    sourceUrl = sourceUrl,
                    creatorName = user?.text()?.trim()?.takeIf { it.isNotBlank() },
                    creatorHandle = creatorHandle,
                    updatedAt = card.selectFirst(".related-list-meta__updated")
                        ?.text()
                        ?.removePrefix("Updated")
                        ?.trim()
                        ?.takeIf { it.isNotBlank() },
                    itemCount = itemCount,
                    likes = likes,
                    previewPosterUrls = card.select(".related-list-posters img")
                        .asSequence()
                        .map { image -> image.attr("abs:src").ifBlank { image.attr("src") } }
                        .map { normalizePosterUrl(it) }
                        .filter { it.isNotBlank() }
                        .distinct()
                        .take(5)
                        .toList()
                )
            }
            .distinctBy { it.sourceUrl.lowercase() }
            .toList()
    }

    private fun normalizePosterUrl(url: String): String {
        val trimmed = url.trim()
        if (trimmed.isBlank()) return ""
        return when {
            trimmed.startsWith("http://", ignoreCase = true) -> trimmed
            trimmed.startsWith("https://", ignoreCase = true) -> trimmed
            trimmed.startsWith("//") -> "https:$trimmed"
            else -> "https://$trimmed"
        }
    }

    private fun relevanceScore(query: String, result: CatalogDiscoveryResult): Int {
        val searchable = buildString {
            append(result.title.lowercase())
            append(' ')
            append(result.description.orEmpty().lowercase())
        }
        val tokens = query.lowercase()
            .split(NON_ALPHA_NUM_REGEX)
            .filter { it.length >= 3 }
            .distinct()
        if (tokens.isEmpty()) return 0
        return tokens.sumOf { token ->
            val titleScore = if (result.title.contains(token, ignoreCase = true)) 4 else 0
            val bodyScore = if (searchable.contains(token)) 1 else 0
            titleScore + bodyScore
        }
    }
    private companion object {
        private val NON_ALPHA_NUM_REGEX = Regex("[^a-z0-9]+")
    }
}
