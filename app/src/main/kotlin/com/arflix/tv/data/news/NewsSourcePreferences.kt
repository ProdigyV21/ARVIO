package com.arflix.tv.data.news

import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * A profile's news setup: which providers it follows, in order, plus any RSS feeds it added itself.
 * Stored as JSON under one per-profile key so a malformed value can only reset this one feature.
 */
data class NewsSourceSelection(
    val selectedIds: List<String>,
    val customProviders: List<NewsProvider> = emptyList(),
    /** Interests to show; empty means every topic (each provider's main feed). */
    val topics: List<NewsTopic> = emptyList(),
) {
    val providers: List<NewsProvider>
        get() {
            val byId = (NewsProviders.presets + customProviders).associateBy { it.id }
            return selectedIds.mapNotNull { byId[it] }
        }

    /** A feed the user added is always shown: it may well be the topic feed they wanted. */
    fun covers(provider: NewsProvider): Boolean =
        topics.isEmpty() || provider.isCustom || topics.any { it in provider.topics }

    /** Followed providers that have something for the chosen topics. */
    val shownProviders: List<NewsProvider> get() = providers.filter(::covers)

    /** Followed providers with no feed for any chosen topic. */
    val uncoveredProviders: List<NewsProvider> get() = providers.filterNot(::covers)
}

object NewsSourcePreferences {
    fun key(profileId: String) = stringPreferencesKey("profile_${profileId}_news_sources")

    fun decode(json: String?, appLanguage: String): NewsSourceSelection {
        val root = runCatching { JsonParser.parseString(json ?: "") }.getOrNull()
            ?.takeIf { it.isJsonObject }?.asJsonObject
            ?: return NewsSourceSelection(NewsProviders.defaultSelection(appLanguage))
        val custom = root.arrayOrEmpty("custom").mapNotNull { element ->
            val obj = element.takeIf { it.isJsonObject }?.asJsonObject ?: return@mapNotNull null
            val id = obj.string("id") ?: return@mapNotNull null
            val url = obj.string("url")?.takeIf(::isValidFeedUrl) ?: return@mapNotNull null
            if (!id.startsWith(CUSTOM_PREFIX)) return@mapNotNull null
            NewsProvider(
                id = id,
                name = obj.string("name")?.takeIf { it.isNotBlank() } ?: hostLabel(url),
                url = url,
                language = obj.string("language") ?: "en",
                isCustom = true,
            )
        }.distinctBy { it.id }
        val known = NewsProviders.presets.map { it.id }.toSet() + custom.map { it.id }
        val selected = root.arrayOrEmpty("selected").mapNotNull {
            it.takeIf { e -> e.isJsonPrimitive && e.asJsonPrimitive.isString }?.asString
        }.filter { it in known }.distinct()
        val topics = root.arrayOrEmpty("topics").mapNotNull {
            it.takeIf { e -> e.isJsonPrimitive && e.asJsonPrimitive.isString }?.asString?.let(NewsTopic::fromId)
        }.distinct().sorted()
        return NewsSourceSelection(selected, custom, topics)
    }

    fun encode(selection: NewsSourceSelection): String = JsonObject().apply {
        add("selected", JsonArray().apply { selection.selectedIds.distinct().forEach { add(it) } })
        add("topics", JsonArray().apply { selection.topics.distinct().forEach { add(it.id) } })
        add("custom", JsonArray().apply {
            selection.customProviders.forEach { provider ->
                add(JsonObject().apply {
                    addProperty("id", provider.id)
                    addProperty("name", provider.name)
                    addProperty("url", provider.url)
                    addProperty("language", provider.language)
                })
            }
        })
    }.toString()

    fun toggle(selection: NewsSourceSelection, providerId: String): NewsSourceSelection {
        val selected = if (providerId in selection.selectedIds) selection.selectedIds - providerId
        else selection.selectedIds + providerId
        return selection.copy(selectedIds = selected)
    }

    /** `null` = all topics. Removing the last chosen topic also means all topics again. */
    fun toggleTopic(selection: NewsSourceSelection, topic: NewsTopic?): NewsSourceSelection {
        val topics = when (topic) {
            null -> emptyList()
            in selection.topics -> selection.topics - topic
            else -> (selection.topics + topic).sorted()
        }
        return selection.copy(topics = topics)
    }

    fun addCustom(selection: NewsSourceSelection, name: String, rawUrl: String): NewsSourceSelection? {
        val url = normalizeFeedUrl(rawUrl) ?: return null
        selection.customProviders.firstOrNull { it.url == url }?.let { existing ->
            return if (existing.id in selection.selectedIds) selection else selection.copy(selectedIds = selection.selectedIds + existing.id)
        }
        val id = CUSTOM_PREFIX + Integer.toHexString(url.hashCode())
        val provider = NewsProvider(id, name.trim().ifBlank { hostLabel(url) }, url,
            language = if (url.contains(".co.il") || url.contains(".il/")) "he" else "en", isCustom = true)
        return selection.copy(selectedIds = selection.selectedIds + id, customProviders = selection.customProviders + provider)
    }

    fun removeCustom(selection: NewsSourceSelection, providerId: String) = selection.copy(
        selectedIds = selection.selectedIds - providerId,
        customProviders = selection.customProviders.filterNot { it.id == providerId },
    )

    fun normalizeFeedUrl(raw: String): String? {
        val trimmed = raw.trim()
        val withScheme = if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) trimmed else "https://$trimmed"
        return withScheme.takeIf(::isValidFeedUrl)
    }

    private fun isValidFeedUrl(url: String): Boolean = runCatching {
        val parsed = java.net.URI(url)
        (parsed.scheme == "http" || parsed.scheme == "https") && !parsed.host.isNullOrBlank() && parsed.host.contains('.')
    }.getOrDefault(false)

    private fun hostLabel(url: String): String =
        runCatching { java.net.URI(url).host.removePrefix("www.") }.getOrNull() ?: url

    private const val CUSTOM_PREFIX = "custom_"

    private fun JsonObject.arrayOrEmpty(name: String): List<com.google.gson.JsonElement> =
        get(name)?.takeIf { it.isJsonArray }?.asJsonArray?.toList().orEmpty()

    private fun JsonObject.string(name: String): String? =
        get(name)?.takeIf { it.isJsonPrimitive && it.asJsonPrimitive.isString }?.asString?.trim()
}
