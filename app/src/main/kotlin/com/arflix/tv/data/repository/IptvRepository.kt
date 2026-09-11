package com.arflix.tv.data.repository

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.arflix.tv.data.model.IptvChannel
import com.arflix.tv.data.model.IptvGuideHistory
import com.arflix.tv.data.model.IptvVodSourceIds
import com.arflix.tv.data.model.DrmInfo
import com.arflix.tv.data.model.IptvNowNext
import com.arflix.tv.data.model.IptvProgram
import com.arflix.tv.data.model.IptvSnapshot
import com.arflix.tv.data.model.StalkerVodLink
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.R
import com.arflix.tv.network.withIptvProviderRequestGuard
import com.arflix.tv.network.iptvProviderCooldownMs
import com.arflix.tv.util.IPTV_VOD_SEARCH_ENABLED_KEY
import com.arflix.tv.util.settingsDataStore
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlinx.coroutines.delay
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Deferred
import com.arflix.tv.data.model.PlaylistGroupKey
import kotlinx.coroutines.launch
import com.arflix.tv.network.OkHttpProvider
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.BufferedOutputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.FilterInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.io.File
import java.nio.charset.StandardCharsets
import java.text.Normalizer
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import javax.xml.XMLConstants
import javax.inject.Inject
import javax.inject.Singleton
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.xml.parsers.SAXParserFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.lang.reflect.Type
import java.security.KeyStore
import java.security.MessageDigest

private object IptvRepoDateRegexes {
    val MINUTE_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm")
    val SECOND_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm-ss")
    val SPACE_SECOND_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val SPACE_MINUTE_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val YEAR_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy")
    val MONTH_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("MM")
    val DAY_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("dd")



    private val datePatternRegexCache = java.util.concurrent.ConcurrentHashMap<String, Regex>()

    fun getDatePatternRegex(key: String): Regex {
        return datePatternRegexCache.getOrPut(key) {
            Regex("""\$\{$key:([^}]+)\}|\{$key:([^}]+)\}""")
        }
    }
    val HOUR_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("HH")
    val MIN_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("mm")
    val SEC_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("ss")

    private const val MAX_DYNAMIC_FORMATTERS = 256
    val formatterCache = java.util.concurrent.ConcurrentHashMap<String, DateTimeFormatter>()

    fun formatterFor(pattern: String): DateTimeFormatter {
        formatterCache[pattern]?.let { return it }
        if (formatterCache.size >= MAX_DYNAMIC_FORMATTERS) {
            return DateTimeFormatter.ofPattern(pattern)
        }
        return formatterCache.getOrPut(pattern) { DateTimeFormatter.ofPattern(pattern) }
    }
}

private object IptvIdSentinels {
    const val IMDB_NONE: String = "tt0"
    const val TMDB_NONE: Int = 0

    fun normalizeImdb(id: String?): String {
        val trimmed = id?.trim()?.lowercase(Locale.US).orEmpty()
        if (trimmed.isBlank()) return IMDB_NONE
        if (!trimmed.startsWith("tt")) return IMDB_NONE
        if (trimmed.length < 3) return IMDB_NONE
        return trimmed
    }

    fun normalizeTmdb(id: Int?): Int = id?.takeIf { it > 0 } ?: TMDB_NONE

    fun normalizeTmdb(id: String?): Int {
        val raw = id?.trim().orEmpty()
        if (raw.isBlank()) return TMDB_NONE
        return raw.toIntOrNull()?.takeIf { it > 0 } ?: TMDB_NONE
    }

    fun isReal(imdb: String): Boolean = imdb.isNotBlank() && imdb != IMDB_NONE
    fun isReal(tmdb: Int): Boolean = tmdb > 0
}

private const val LargeIptvListChannelCount = 10_000
private const val LargeListMemoryChannelLimit = 240
private const val LargeListMemoryGuideLimit = 512
private const val LargeListMemoryFavoriteGuideLimit = 256
internal const val IPTV_GROUP_ORDER_SCHEMA = 3

internal fun normalizeIptvSortOrder(value: String?): String = when (value?.trim()?.lowercase()) {
    "number" -> "number"
    "name" -> "name"
    else -> "provider"
}

/**
 * Title normalization for Xtream VOD/series lookups.
 *
 * Lives at top level so it can be unit-tested without an Android `Context`,
 * mirroring `HomeServerMatcher.normalizeTitle` in `HomeServerRepository.kt`.
 */
internal object IptvTitleNormalizer {
    val BRACKET_CONTENT_REGEX = Regex("""\[[^\]]*]""")
    val PAREN_CONTENT_REGEX = Regex("""\([^\)]*\)""")
    val MULTI_SPACE_REGEX = Regex("\\s+")
    private val YEAR_PAREN_REGEX = Regex("""\((19|20)\d{2}\)""")
    private val SEASON_TOKEN_REGEX = Regex("""\b(s|season)\s*\d{1,2}\b""", RegexOption.IGNORE_CASE)
    private val EPISODE_TOKEN_REGEX = Regex("""\b(e|ep|episode)\s*\d{1,3}\b""", RegexOption.IGNORE_CASE)
    private val RELEASE_TAG_REGEX = Regex(
        """\b(2160p|1080p|720p|480p|4k|uhd|fhd|hdr|dv|dovi|hevc|x265|x264|h264|remux|bluray|bdrip|webrip|web[- ]?dl|proper|repack|multi|dubbed|dual[- ]?audio)\b""",
        RegexOption.IGNORE_CASE
    )
    private val NON_ALPHA_NUM_REGEX = Regex("[^a-z0-9]+")
    private val DIACRITICS_REGEX = Regex("\\p{Mn}+")

    /** Box-drawing bar some IPTV panels use in place of a pipe: "\u2503DE\u2503 Title". */
    private const val BOX_DRAWING_BAR = '\u2503'

    /**
     * Leading language/quality tags in pipes: "|DE| Title", "|DE|HD| Title".
     * The tags share their separators, hence one opening bar followed by
     * repeated "TAG|" groups rather than repeated "|TAG|" groups.
     */
    private val LEADING_PIPE_TAG_REGEX = Regex("""^\s*\|(?:[A-Za-z0-9]{1,6}\|)+\s*""")

    /**
     * Leading language marker: "DE: Title", "GER - Title", "EN| Title".
     *
     * Deliberately an explicit code list instead of a generic two-or-three
     * letter prefix: the generic form also eats real titles such as
     * "IT: Chapter Two". Codes that double as English words ("it", "no", "se",
     * "us") are left out for the same reason.
     */
    private val LANGUAGE_PREFIX_REGEX = Regex(
        """^(?:de|deu|ger|en|eng|fr|fra|fre|es|esp|spa|pt|por|nl|ned|dut|pl|pol|tr|tur|ar|ara|""" +
            """ru|rus|ro|ron|rom|ita|ell|gre|cz|cze|hu|hun|swe|nor|dan|fin|bg|bul|hr|hrv|srp|""" +
            """sk|slo|slv|ua|ukr|mk|mkd|vip|multi|dual)\s*[:\-|]\s*""",
        RegexOption.IGNORE_CASE
    )

    fun normalize(value: String): String {
        if (value.isBlank()) return ""
        val stripped = value
            .replace(BOX_DRAWING_BAR, '|')
            .replace(LEADING_PIPE_TAG_REGEX, " ")
            .trimStart()
            .replace(LANGUAGE_PREFIX_REGEX, " ")
            .replace(BRACKET_CONTENT_REGEX, " ")
            .replace(PAREN_CONTENT_REGEX, " ")
            .replace(YEAR_PAREN_REGEX, " ")
            .replace(SEASON_TOKEN_REGEX, " ")
            .replace(EPISODE_TOKEN_REGEX, " ")
            .replace(RELEASE_TAG_REGEX, " ")
        return foldLetters(stripped)
            .lowercase(Locale.US)
            .replace(NON_ALPHA_NUM_REGEX, " ")
            .trim()
            .replace(MULTI_SPACE_REGEX, " ")
    }

    /**
     * Maps non-ASCII letters onto their ASCII base. Without this the `[^a-z0-9]+`
     * filter below turns them into a space and splits the word in two — "Doğu"
     * became "do u" instead of "dogu", so the title never matched again.
     *
     * Two steps are needed: [nonDecomposableReplacement] first, because Unicode has
     * no canonical decomposition for letters such as "ß" or the Turkish dotless "ı"
     * (NFD leaves them untouched), then NFD plus removal of the combining marks it
     * splits off for the rest.
     */
    fun foldLetters(value: String): String {
        if (value.all { it.code < 0x80 }) return value
        val mapped = StringBuilder(value.length + 8)
        value.forEach { character ->
            val replacement = nonDecomposableReplacement(character)
            if (replacement != null) mapped.append(replacement) else mapped.append(character)
        }
        return Normalizer.normalize(mapped, Normalizer.Form.NFD).replace(DIACRITICS_REGEX, "")
    }

    /**
     * Catalog-side alias for German umlaut transcriptions: providers write "Für"
     * either transliterated ("Fur", which [normalize] already produces) or
     * transcribed ("Fuer"). Collapsing "ue"/"oe"/"ae" onto the transliterated form
     * gives such entries a second index key, so both provider spellings are found.
     *
     * Only ever used to *add* keys on the catalog side — applying it to a query
     * would also rewrite plain-ASCII words ("blue" -> "blu") and change their
     * scoring.
     */
    fun foldUmlautTranscription(value: String): String {
        if (!value.contains("ue") && !value.contains("oe") && !value.contains("ae")) return value
        return value
            .replace("ue", "u")
            .replace("oe", "o")
            .replace("ae", "a")
    }

    private fun nonDecomposableReplacement(character: Char): String? = when (character) {
        'ß' -> "ss"
        'ẞ' -> "SS"
        'ı' -> "i"
        'İ' -> "I"
        'ø' -> "o"
        'Ø' -> "O"
        'æ' -> "ae"
        'Æ' -> "AE"
        'œ' -> "oe"
        'Œ' -> "OE"
        'đ' -> "d"
        'Đ' -> "D"
        'ð' -> "d"
        'Ð' -> "D"
        'ł' -> "l"
        'Ł' -> "L"
        'þ' -> "th"
        'Þ' -> "TH"
        else -> null
    }
}

data class IptvConfig(
    val m3uUrl: String = "",
    val epgUrl: String = "",
    val playlists: List<IptvPlaylistEntry> = emptyList(),
    val stalkerPortals: List<StalkerPortalEntry> = emptyList(),
    val sortOrder: String = "provider"
)

data class IptvPlaylistEntry(
    val id: String,
    val name: String,
    val m3uUrl: String,
    val epgUrl: String = "",
    val enabled: Boolean = true,
    val epgUrls: List<String> = emptyList(),
    // NEW FIELDS: Selective Import
    val importLiveTv: Boolean? = true,
    val importVod: Boolean? = true,
    val importSeries: Boolean? = true
)

/**
 * A single Stalker/Ministra portal configuration. Mirrors [IptvPlaylistEntry]
 * so Stalker portals can be managed (add/edit/remove/reorder/toggle) like M3U
 * playlists. Channel ids are prefixed `stalker:<id>:<origId>` so the existing
 * `startsWith("stalker:")` checks keep matching.
 */
data class StalkerPortalEntry(
    val id: String,
    val name: String,
    val portalUrl: String,
    val macAddress: String,
    val enabled: Boolean = true
)

data class IptvLoadProgress(
    val message: String,
    val percent: Int? = null
)

data class IptvCloudProfileState(
    val m3uUrl: String = "",
    val epgUrl: String = "",
    val stalkerPortals: List<StalkerPortalEntry> = emptyList(),
    // Retained for importing cloud snapshots written before multi-portal support.
    val stalkerPortalUrl: String = "",
    val stalkerMacAddress: String = "",
    val favoriteGroups: List<String> = emptyList(),
    val favoriteChannels: List<String> = emptyList(),
    val hiddenGroups: List<String> = emptyList(),
    val lockedGroups: List<String> = emptyList(),
    val groupOrder: List<String> = emptyList(),
    val groupOrderSchema: Int = 0,
    val sortOrder: String = "provider",
    val playlists: List<IptvPlaylistEntry> = emptyList(),
    val tvSession: IptvTvSessionState = IptvTvSessionState()
)

data class IptvTvSessionState(
    val lastChannelId: String = "",
    val lastGroupName: String = "",
    val lastFocusedZone: String = "GUIDE",
    val lastOpenedAt: Long = 0L,
    val recentChannelIds: List<String> = emptyList()
)

@Singleton
class IptvRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient,
    private val profileManager: ProfileManager,
    private val invalidationBus: CloudSyncInvalidationBus
) {
    private val gson = Gson()
    private val loadMutex = Mutex()
    private val stalkerApiMutex = Mutex()
    private val xtreamDataMutex = Mutex()
    private val xtreamSeriesEpisodeCacheMutex = Mutex()
    private val xtreamSeriesEpisodeInFlightMutex = Mutex()
    private val epgIndex by lazy { IptvEpgIndex(context) }
    private val channelStore by lazy { IptvChannelStore(context) }
    private val maxSeriesEpisodeCacheEntries = 8

    @Volatile
    private var cachedChannels: List<IptvChannel> = emptyList()
    @Volatile
    private var cachedChannelsLookupSource: List<IptvChannel>? = null
    @Volatile
    private var cachedChannelsById: Map<String, IptvChannel> = emptyMap()
    @Volatile
    private var cachedGroupedChannels: Map<String, List<IptvChannel>> = emptyMap()
    @Volatile
    private var cachedStalkerApis: Map<String, com.arflix.tv.data.api.StalkerApi> = emptyMap()

    /**
     * Scope for the shared Stalker channel-list download. Deliberately not tied
     * to a caller: when the entry point that started the download gives up (its
     * own timeout, a screen the user left), the download still finishes and the
     * next entry point reuses it instead of starting another one.
     */
    private val stalkerChannelListScope = kotlinx.coroutines.CoroutineScope(
        kotlinx.coroutines.SupervisorJob() + Dispatchers.IO
    )

    /**
     * What one portal answered: the session it opened and the channels it
     * returned. A portal that did not answer carries a null [api] and no
     * channels — that is what makes a failure visible per portal instead of
     * disappearing into a merged list.
     */
    internal data class StalkerPortalChannels(
        val portalId: String,
        val api: com.arflix.tv.data.api.StalkerApi?,
        val channels: List<IptvChannel>
    )

    /**
     * One shared download **per portal**.
     *
     * Keyed per portal rather than per portal set: with several portals
     * configured, a portal that is temporarily down must not hide behind one
     * that answered. Its result is never remembered, so the next ordinary load
     * asks it again, while the portals that did answer keep their lists.
     *
     * `internal` so a test can check what [invalidateCache] and
     * [ensureCacheOwnership] do to it — the bug this loader exists to prevent
     * came back through its caller, not through the loader itself (same
     * convention as [activePlaylists]).
     */
    internal val stalkerChannelListLoader =
        StalkerChannelListLoader<StalkerPortalChannels>(
            scope = stalkerChannelListScope,
            isReusable = { it.api != null && it.channels.isNotEmpty() }
        )

    private data class StalkerEpgPortalCacheKey(
        val portalId: String,
        val apiIdentity: String
    )

    /** Compact, timestamp-safe guide slices produced from the streamed bulk response. */
    private data class StalkerEpgCacheEntry(
        val fetchedAtMs: Long,
        val programsByChannel: Map<String, List<IptvProgram>>
    )

    private data class StalkerShortEpgCacheKey(
        val portal: StalkerEpgPortalCacheKey,
        val originalChannelId: String
    )

    private data class StalkerShortEpgCacheEntry(
        val fetchedAtMs: Long,
        val programs: List<IptvProgram>
    )

    private val stalkerEpgCache = ConcurrentHashMap<StalkerEpgPortalCacheKey, StalkerEpgCacheEntry>()
    private val stalkerShortEpgCache = ConcurrentHashMap<StalkerShortEpgCacheKey, StalkerShortEpgCacheEntry>()
    private val stalkerEpgCacheTtlMs = 5 * 60 * 1000L
    private val stalkerShortEpgCacheTtlMs = 2 * 60 * 1000L
    private val stalkerBulkProgramsPerChannelLimit = 16

    private data class StalkerVodSearchCacheKey(
        val portalId: String,
        val apiIdentity: String,
        val query: String
    )

    private data class StalkerVodSearchCacheEntry(
        val fetchedAtMs: Long,
        val items: List<com.arflix.tv.data.api.StalkerApi.StalkerVodItem>
    )

    /**
     * Raw portal answers per search term. The persisted movie-source cache
     * already covers "same movie looked up again", this one covers different
     * movies that normalize onto the same query and the repeated lookups a
     * single detail screen can trigger, so neither hits the portal twice.
     */
    private val stalkerVodSearchCache =
        ConcurrentHashMap<StalkerVodSearchCacheKey, StalkerVodSearchCacheEntry>()
    private val stalkerVodSearchCacheTtlMs = 6 * 60 * 60_000L

    /**
     * A "the portal knows no such title" answer is kept only briefly. It is a
     * real answer, so it earns an entry - it stops a browsed-past show from
     * asking again on every screen - but six hours is far too long to be wrong
     * about: catalogs change, and a title the portal gains today would stay
     * invisible for the rest of the day.
     */
    private val stalkerVodSearchEmptyCacheTtlMs = 10 * 60_000L
    private val maxStalkerVodSearchCacheEntries = 64

    /**
     * Shortest head-of-title that is still worth asking a portal for. Below
     * this a subtitle split stops naming a film - "It: Chapter Two" would ask
     * for "It" and get a slice of the catalog back.
     */
    private val minStalkerVodQueryHeadLength = 3

    /**
     * Public accessor kept for compatibility with code that previously read the
     * single cached Stalker API instance. Returns the first cached portal API.
     */
    val cachedStalkerApi: com.arflix.tv.data.api.StalkerApi?
        get() = cachedStalkerApis.values.firstOrNull()

    internal fun portalIdFromChannelId(channelId: String): String? =
        StalkerPortalSupport.portalIdFromChannelId(channelId)

    private suspend fun getOrCreateStalkerApi(portal: StalkerPortalEntry): com.arflix.tv.data.api.StalkerApi? {
        cachedStalkerApis[portal.id]?.let { return it }
        return stalkerApiMutex.withLock {
            cachedStalkerApis[portal.id]?.let { return it }
            com.arflix.tv.data.api.StalkerApi(portal.portalUrl, portal.macAddress)
                .takeIf { it.handshake() }
                ?.also { api ->
                    cachedStalkerApis = cachedStalkerApis + (portal.id to api)
                }
        }
    }

    /** (store key, channel count) — see [pagedChannelStoreCount]. */
    @Volatile
    private var cachedPagedChannelStoreCount: Pair<String, Int>? = null

    @Volatile
    private var groupOrderLocallyDirty = false

    fun isGroupOrderLocallyDirty(): Boolean = groupOrderLocallyDirty

    @Volatile
    private var cachedNowNext: ConcurrentHashMap<String, IptvNowNext> = ConcurrentHashMap()
    private val emptyShortEpgCooldownUntil = ConcurrentHashMap<String, Long>()
    private val visibleXmlEpgCooldownUntil = ConcurrentHashMap<String, Long>()

    private val guideKeyCandidatesCache = java.util.Collections.synchronizedMap(
        object : java.util.LinkedHashMap<String, Set<String>>(512, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<String, Set<String>>?): Boolean = size > 4096
        }
    )

    internal class EpgNotModifiedException : Exception("EPG content has not modified (HTTP 304)")

    private fun getEpgHttpCachePrefs() = context.getSharedPreferences("arvio_epg_http_cache", Context.MODE_PRIVATE)

    private fun getEpgCachedEtag(url: String): String? {
        return getEpgHttpCachePrefs().getString("${url}_etag", null)
    }

    private fun getEpgCachedLastModified(url: String): String? {
        return getEpgHttpCachePrefs().getString("${url}_last_modified", null)
    }

    private fun saveEpgHttpCacheHeaders(url: String, etag: String?, lastModified: String?) {
        getEpgHttpCachePrefs().edit().apply {
            if (etag != null) putString("${url}_etag", etag) else remove("${url}_etag")
            if (lastModified != null) putString("${url}_last_modified", lastModified) else remove("${url}_last_modified")
            apply()
        }
    }

    @Volatile
    private var cachedPlaylistAt: Long = 0L

    @Volatile
    private var cachedEpgAt: Long = 0L

    private val discoveredM3uEpgUrls = java.util.concurrent.CopyOnWriteArraySet<String>()
    @Volatile
    private var cacheOwnerProfileId: String? = null
    @Volatile
    private var cacheOwnerConfigSig: String? = null
    @Volatile
    private var currentEpgIndexKey: String = ""
    @Volatile
    private var xtreamVodCacheKey: String? = null
    @Volatile
    private var xtreamVodLoadedAtMs: Long = 0L
    private val startupPrefetchInFlight = AtomicBoolean(false)
    private val liveTvInteractive = AtomicBoolean(false)
    private val sha1Digest: ThreadLocal<MessageDigest> =
        ThreadLocal.withInitial { MessageDigest.getInstance("SHA-1") }

    fun setLiveTvInteractive(active: Boolean) {
        liveTvInteractive.set(active)
    }

    private fun abortLargeEpgWorkIfInteractive(channelCount: Int) {
        if (channelCount > LargeIptvListChannelCount && liveTvInteractive.get()) {
            throw kotlinx.coroutines.CancellationException(
                "Large EPG work deferred while Live TV is interactive"
            )
        }
    }

    private fun buildGroupedChannels(channels: List<IptvChannel>): Map<String, List<IptvChannel>> =
        channels.groupBy { it.group.ifBlank { "Uncategorized" } }

    private fun isLargePersistedChannelSnapshot(config: IptvConfig, loadedChannelCount: Int): Boolean {
        if (loadedChannelCount > LargeIptvListChannelCount) return true
        val key = currentEpgIndexKey(config)
        val indexedCount = runCatching { channelStore.count(key) }.getOrDefault(loadedChannelCount)
        return indexedCount > LargeIptvListChannelCount
    }

    private fun retainGuideForMemory(
        channels: List<IptvChannel>,
        nowNext: Map<String, IptvNowNext>,
        priorityChannelIds: Collection<String>
    ): Map<String, IptvNowNext> {
        if (nowNext.size <= LargeListMemoryGuideLimit) return nowNext
        val keepIds = LinkedHashSet<String>(LargeListMemoryGuideLimit)
        priorityChannelIds
            .asSequence()
            .filter { it.isNotBlank() && nowNext.containsKey(it) }
            .take(LargeListMemoryFavoriteGuideLimit)
            .forEach(keepIds::add)
        channels
            .asSequence()
            .map { it.id }
            .filter { it.isNotBlank() && nowNext.containsKey(it) }
            .take(LargeListMemoryGuideLimit - keepIds.size)
            .forEach(keepIds::add)
        return LinkedHashMap<String, IptvNowNext>(keepIds.size).apply {
            keepIds.forEach { channelId -> nowNext[channelId]?.let { put(channelId, it) } }
        }
    }

    private fun cachedChannelLookup(): Map<String, IptvChannel> {
        val source = cachedChannels
        val current = cachedChannelsById
        if (cachedChannelsLookupSource === source && current.size == source.size) {
            return current
        }
        val rebuilt = source.associateBy { it.id }
        cachedChannelsLookupSource = source
        cachedChannelsById = rebuilt
        return rebuilt
    }

    private fun channelsForEpgRefresh(channelIds: Collection<String>): List<IptvChannel> {
        if (channelIds.isEmpty()) return emptyList()
        val lookup = cachedChannelLookup()
        val channels = ArrayList<IptvChannel>(channelIds.size)
        val missing = LinkedHashSet<String>()
        channelIds.forEach { id ->
            val trimmed = id.trim()
            if (trimmed.isBlank()) return@forEach
            val channel = lookup[trimmed]
            if (channel != null) {
                channels += channel
            } else {
                missing += trimmed
            }
        }
        if (missing.isNotEmpty()) {
            val indexed = runCatching { channelStore.getByIds(currentEpgIndexKey, missing) }
                .onFailure { error -> System.err.println("[EPG-Refresh] paged channel lookup failed: ${error.message}") }
                .getOrDefault(emptyList())
            if (indexed.isNotEmpty()) {
                channels += indexed
                System.err.println("[EPG-Refresh] hydrated ${indexed.size}/${missing.size} channels from paged store")
            }
        }
        return channels.distinctBy { it.id }
    }

    private data class ScopedEpgCandidate(
        val url: String,
        val playlistId: String? = null,
        val providerFallback: Boolean = false,
    )
    internal fun hasAnyConfiguredSource(config: IptvConfig): Boolean =
        activePlaylists(config).any { it.m3uUrl.isNotBlank() } ||
            activeStalkerPortals(config).isNotEmpty()

    internal fun activePlaylists(config: IptvConfig): List<IptvPlaylistEntry> {
        // config.playlists.isEmpty() means the user has never created a playlist entry
        // (pre-multi-playlist legacy state) - fall back to the single legacy m3uUrl field.
        // Once config.playlists is non-empty, respect it as-is (including "all disabled"):
        // config.m3uUrl always mirrors playlists.firstOrNull()?.m3uUrl regardless of that
        // entry's enabled state (see observeConfig()), so checking m3uUrl.isNotBlank() here
        // instead would resurrect a playlist the user explicitly disabled.
        if (config.playlists.isNotEmpty()) {
            return config.playlists.filter { it.enabled }
        }
        if (config.m3uUrl.isBlank()) {
            return emptyList()
        }
        val epgUrls = normalizeEpgInputs(config.epgUrl)
        return listOf(
            IptvPlaylistEntry(
                "list_1",
                "List 1",
                config.m3uUrl,
                epgUrls.firstOrNull().orEmpty(),
                enabled = true,
                epgUrls = epgUrls
            )
        )
    }

    /** Enabled Stalker portals with a non-blank URL — the ones that load channels. */
    private fun activeStalkerPortals(config: IptvConfig): List<StalkerPortalEntry> =
        config.stalkerPortals.filter { it.enabled && it.portalUrl.isNotBlank() }

    /**
     * Identifies one configured portal for [stalkerChannelListLoader]. Hashed so
     * the portal URL and MAC address never travel further than this function.
     */
    private fun stalkerPortalKey(portal: StalkerPortalEntry): String {
        val raw = listOf(portal.id.trim(), portal.portalUrl.trim(), portal.macAddress.trim())
            .joinToString("|")
        return MessageDigest.getInstance("SHA-256")
            .digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    /** The loader keys of the portals [config] currently has enabled. */
    private fun activeStalkerPortalKeys(config: IptvConfig): Set<String> =
        activeStalkerPortals(config).map { stalkerPortalKey(it) }.toSet()

    /**
     * Downloads every enabled portal's channel list, each portal at most once
     * per freshness window, and merges the answers.
     *
     * This is the only place that opens portal sessions for the channel list —
     * the startup prefetch, the live TV snapshot load and the guide backfill
     * load all come through here, so a start costs one download instead of one
     * per entry point (measured before: 3 x 29 MB in 17 seconds). Sharing and
     * the freshness window live in [StalkerChannelListLoader].
     *
     * Each portal is loaded under its own key, so a portal that fails is
     * retried by the next ordinary load while the portals that answered keep
     * their lists. Merging is the only thing that happens here, and it happens
     * after the decision what may be remembered — that decision is per portal.
     *
     * Channel ids are prefixed with `stalker:<portalId>:<origId>` so playback
     * can route back to the portal that owns them.
     *
     * `internal` so a test can drive the real merge and the real loader keys
     * with [fetchPortal] standing in for the network; production never passes
     * it.
     *
     * @param freshSinceMs the moment the caller decided it needed fresh data;
     *   `0` accepts any list inside the freshness window. See
     *   [StalkerChannelListLoader.load].
     */
    internal suspend fun loadStalkerChannels(
        portals: List<StalkerPortalEntry>,
        freshSinceMs: Long = 0L,
        fetchPortal: suspend (StalkerPortalEntry) -> StalkerPortalChannels = { fetchStalkerChannels(it) }
    ): Pair<Map<String, com.arflix.tv.data.api.StalkerApi>, List<IptvChannel>> = coroutineScope {
        if (portals.isEmpty()) {
            return@coroutineScope emptyMap<String, com.arflix.tv.data.api.StalkerApi>() to emptyList()
        }
        val answers = portals
            .map { portal ->
                async {
                    stalkerChannelListLoader.load(stalkerPortalKey(portal), freshSinceMs) {
                        fetchPortal(portal)
                    }
                }
            }
            .awaitAll()
        val apis = LinkedHashMap<String, com.arflix.tv.data.api.StalkerApi>()
        val channels = ArrayList<IptvChannel>()
        for (answer in answers) {
            channels.addAll(answer.channels)
            answer.api?.let { apis[answer.portalId] = it }
        }
        apis.toMap() to channels.toList()
    }

    /**
     * Opens one portal's session and downloads its channels. A portal that does
     * not answer returns no session and no channels, which is what keeps its
     * failure out of [stalkerChannelListLoader]'s memory.
     */
    private suspend fun fetchStalkerChannels(portal: StalkerPortalEntry): StalkerPortalChannels =
        runCatching {
            val stalker = com.arflix.tv.data.api.StalkerApi(portal.portalUrl, portal.macAddress)
            if (!stalker.handshake()) {
                return@runCatching StalkerPortalChannels(portal.id, null, emptyList())
            }
            stalker.getProfile()
            StalkerPortalChannels(
                portalId = portal.id,
                api = stalker,
                channels = stalker.getChannels().map { it.copy(id = "stalker:${portal.id}:${it.id}") }
            )
        }.getOrElse { StalkerPortalChannels(portal.id, null, emptyList()) }

    @Volatile
    private var xtreamSeriesLoadedAtMs: Long = 0L
    @Volatile
    private var cachedXtreamVodStreams: List<XtreamVodStream> = emptyList()
    @Volatile
    private var cachedXtreamSeries: List<XtreamSeriesItem> = emptyList()
    @Volatile
    private var cachedXtreamSeriesEpisodes: Map<Int, List<XtreamSeriesEpisode>> = emptyMap()
    @Volatile
    private var xtreamSeriesEpisodeInFlight: Map<Int, Deferred<List<XtreamSeriesEpisode>>> = emptyMap()
    private val seriesResolver by lazy { IptvSeriesResolverService() }

    private val staleAfterMs = 24 * 60 * 60_000L
    private val playlistCacheMs = staleAfterMs
    private val epgCacheMs = staleAfterMs
    private val epgEmptyRetryMs = 30_000L
    private val epgUpcomingProgramLimit = 96
    private val epgRecentProgramLimit = 2
    private val xmlTvPastWindowMs = 3L * IptvGuideHistory.DAY_MS
    private val xmlTvFutureWindowMs = 72L * 60L * 60_000L
    private val indexedGuideFutureWarmMs = 6L * 60L * 60_000L
    private val completeEpgCoverageTarget = 0.98f
    private val xtreamShortEpgLimit = 24
    private val xtreamVisibleShortEpgLimit = 96
    private val startupShortEpgChannelLimit = 24
    private val fullCatchupHistoryChannelLimit = 4
    private val xtreamShortEpgBatchSize = 1024
    private val xtreamShortEpgConcurrency = 2
    private val guideRequestBudget = IptvGuideRequestBudget()

    // Per-channel `get_short_epg` fallback (portals whose bulk EPG actions return
    // nothing, e.g. get_simple_data_table/get_epg_info both empty - confirmed
    // live). Only worth doing for small batches (the on-demand visible-guide
    // refresh, typically tens of channels); a full-catalog backfill can be
    // thousands of channels and would hammer the portal with that many
    // individual requests, so it's skipped above this cap and that batch
    // simply gets no EPG until it's requested via the on-demand path instead.
    private val stalkerShortEpgFallbackMaxChannels = 24
    private val stalkerShortEpgFallbackConcurrency = 2
    private val cacheUpcomingProgramLimit = 48
    private val cacheRecentProgramLimit = 1
    private val cacheCatchupRecentProgramLimit = 96
    private val catchupRecentProgramLimit = IptvGuideHistory.MAX_PROGRAMS
    private val catchupProbeCandidateLimit = 3
    private val xtreamVodCacheMs = 6 * 60 * 60_000L
    private val iptvHttpClient: OkHttpClient by lazy {
        // Used for full playlist/EPG loading – generous timeouts for large
        // Xtream EPG feeds. TX-4K serves a ~100 MB XMLTV dump so the read
        // and call timeouts need to be minutes, not seconds.
        okHttpClient.newBuilder()
            .withIptvProviderRequestGuard()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .callTimeout(300, TimeUnit.SECONDS)
            .build()
    }
    private val xtreamLookupHttpClient: OkHttpClient by lazy {
        // Fast-fail client for VOD/source lookups - must be quick for instant playback
        okHttpClient.newBuilder()
            .withIptvProviderRequestGuard()
            .connectTimeout(6, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .callTimeout(12, TimeUnit.SECONDS)
            .build()
    }
    private val xtreamGuideHttpClient: OkHttpClient by lazy {
        okHttpClient.newBuilder()
            .withIptvProviderRequestGuard()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .callTimeout(6, TimeUnit.SECONDS)
            .build()
    }
    private val xtreamCatchupGuideHttpClient: OkHttpClient by lazy {
        // Full catchup history (`get_simple_data_table`) is much larger than
        // short now/next EPG. Keep short EPG snappy, but give catchup history
        // enough time on slower TV boxes and large providers.
        okHttpClient.newBuilder()
            .withIptvProviderRequestGuard()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .callTimeout(45, TimeUnit.SECONDS)
            .build()
    }
    private val iptvCatalogHttpClient: OkHttpClient by lazy {
        // Live catalog payloads can be very large (50k+ streams), so keep them
        // below XMLTV timeouts but long enough to finish on TV WiFi.
        okHttpClient.newBuilder()
            .withIptvProviderRequestGuard()
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(12, TimeUnit.SECONDS)
            .callTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private data class IptvCachePayload(
        val channels: List<IptvChannel> = emptyList(),
        val nowNext: Map<String, IptvNowNext> = emptyMap(),
        val loadedAtEpochMs: Long = 0L,
        val configSignature: String = "",
        val sourceSignature: String = "",
        val discoveredEpgUrls: List<String> = emptyList()
    )

    private data class IptvChannelCachePayload(
        val channels: List<IptvChannel> = emptyList(),
        val loadedAtEpochMs: Long = 0L,
        val configSignature: String = "",
        val sourceSignature: String = "",
        val discoveredEpgUrls: List<String> = emptyList()
    )

    fun observeConfig(): Flow<IptvConfig> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            val playlists = decodePlaylists(prefs[playlistsKey()].orEmpty())
            val primary = playlists.firstOrNull()
            val legacyM3uUrl = normalizeStoredIptvUrl(decryptConfigValue(prefs[m3uUrlKey()].orEmpty()))
            val legacyEpgUrls = normalizeStoredEpgInputs(decryptConfigValue(prefs[epgUrlKey()].orEmpty()))
            IptvConfig(
                m3uUrl = primary?.m3uUrl ?: legacyM3uUrl,
                epgUrl = primary?.epgUrl ?: legacyEpgUrls.firstOrNull().orEmpty(),
                playlists = playlists,
                stalkerPortals = readStalkerPortals(prefs),
                sortOrder = normalizeIptvSortOrder(prefs[sortOrderKey()])
            )
        }

    fun observeFavoriteGroups(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeFavoriteGroups(prefs)
        }

    fun observeFavoriteChannels(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeFavoriteChannels(prefs)
        }

    fun observeHiddenGroups(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeHiddenGroups(prefs)
        }

    fun observeLockedGroups(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeLockedGroups(prefs)
        }

    fun observeGroupOrder(): Flow<List<String>> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeGroupOrder(prefs)
        }

    fun observeVodSearchEnabled(): Flow<Boolean> =
        context.settingsDataStore.data.map { prefs ->
            prefs[IPTV_VOD_SEARCH_ENABLED_KEY] ?: true
        }

    suspend fun isVodSearchEnabled(): Boolean =
        runCatching {
            context.settingsDataStore.data.first()[IPTV_VOD_SEARCH_ENABLED_KEY] ?: true
        }.getOrDefault(true)

    fun observeTvSessionState(): Flow<IptvTvSessionState> =
        profileManager.activeProfileId.combine(context.settingsDataStore.data) { _, prefs ->
            decodeTvSessionState(prefs)
        }

    suspend fun saveTvSessionState(state: IptvTvSessionState) {
        context.settingsDataStore.edit { prefs ->
            if (state == IptvTvSessionState()) {
                prefs.remove(tvSessionKey())
            } else {
                prefs[tvSessionKey()] = gson.toJson(
                    state.copy(
                        lastChannelId = StalkerPortalSupport.migrateLegacyChannelId(state.lastChannelId),
                        lastGroupName = state.lastGroupName.trim(),
                        lastFocusedZone = state.lastFocusedZone.trim().ifBlank { "GUIDE" },
                        recentChannelIds = state.recentChannelIds
                            .map(StalkerPortalSupport::migrateLegacyChannelId)
                            .filter { it.isNotBlank() }
                            .distinct()
                            .takeLast(40)
                    )
                )
            }
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save tv session")
    }

    suspend fun saveConfig(m3uUrl: String, epgUrl: String) {
        val profileId = profileManager.getProfileIdSync()
        val previousConfig = observeConfig().first()
        val normalizedM3u = normalizeStoredIptvUrl(m3uUrl)
        val normalizedEpgUrls = normalizeStoredEpgInputs(epgUrl)
        val normalizedEpg = normalizedEpgUrls.firstOrNull().orEmpty()
        val primary = if (normalizedM3u.isNotBlank()) listOf(
            IptvPlaylistEntry(
                id = "list_1",
                name = "List 1",
                m3uUrl = normalizedM3u,
                epgUrl = normalizedEpg,
                epgUrls = normalizedEpgUrls
            )
        ) else emptyList()
        val nextConfig = previousConfig.copy(
            m3uUrl = normalizedM3u,
            epgUrl = normalizedEpg,
            playlists = primary,
        )
        val previousSourceKey = epgIndexKey(profileId, previousConfig)
        val sourceChanged = buildSourceSignature(previousConfig) != buildSourceSignature(nextConfig)
        context.settingsDataStore.edit { prefs ->
            val previous = decodePlaylists(prefs[playlistsKey()].orEmpty())
            val changedSourceIds = changedPlaylistSourceIds(previous, primary)
            prefs[m3uUrlKey()] = encryptConfigValue(normalizedM3u)
            prefs[epgUrlKey()] = encryptConfigValue(normalizedEpg)
            prefs[playlistsKey()] = gson.toJson(primary)
            if (previousConfig.playlists != primary || previousConfig.m3uUrl != normalizedM3u || previousConfig.epgUrl != normalizedEpg) {
                IptvCloudFields.stamp(prefs, profileId, org.json.JSONObject()
                    .put("playlists", org.json.JSONArray(gson.toJson(primary)))
                    .put("m3uUrl", normalizedM3u).put("epgUrl", normalizedEpg))
            }
            val retainedOrder = retainGroupOrderForUnchangedSources(
                decodeGroupOrder(prefs),
                changedSourceIds,
            )
            if (retainedOrder.isEmpty()) prefs.remove(groupOrderKey())
            else prefs[groupOrderKey()] = gson.toJson(retainedOrder)
            prefs[groupOrderSchemaKey()] = IPTV_GROUP_ORDER_SCHEMA.toString()
        }
        groupOrderLocallyDirty = true
        if (sourceChanged) {
            withContext(Dispatchers.IO) { deletePersistedSourceCaches(previousSourceKey) }
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save iptv config")
    }

    suspend fun savePlaylists(playlists: List<IptvPlaylistEntry>) {
        val profileId = profileManager.getProfileIdSync()
        val previousConfig = observeConfig().first()
        val normalized = playlists.mapIndexed { index, item ->
            normalizePlaylistEntry(item, index)
        }.filterNotNull().take(3)
        val primary = normalized.firstOrNull()
        if (normalized == previousConfig.playlists) return
        val nextConfig = previousConfig.copy(
            m3uUrl = primary?.m3uUrl.orEmpty(),
            epgUrl = primary?.epgUrl.orEmpty(),
            playlists = normalized,
        )
        val previousSourceKey = epgIndexKey(profileId, previousConfig)
        val sourceChanged = buildSourceSignature(previousConfig) != buildSourceSignature(nextConfig)

        context.settingsDataStore.edit { prefs ->
            val previous = decodePlaylists(prefs[playlistsKey()].orEmpty())
            val changedSourceIds = changedPlaylistSourceIds(previous, normalized)
            prefs[playlistsKey()] = gson.toJson(normalized)
            prefs[m3uUrlKey()] = encryptConfigValue(primary?.m3uUrl.orEmpty())
            prefs[epgUrlKey()] = encryptConfigValue(primary?.epgUrl.orEmpty())
            IptvCloudFields.stamp(prefs, profileId, org.json.JSONObject()
                .put("playlists", org.json.JSONArray(gson.toJson(normalized)))
                .put("m3uUrl", primary?.m3uUrl.orEmpty()).put("epgUrl", primary?.epgUrl.orEmpty()))
            val retainedOrder = retainGroupOrderForUnchangedSources(
                decodeGroupOrder(prefs),
                changedSourceIds,
            )
            if (retainedOrder.isEmpty()) prefs.remove(groupOrderKey())
            else prefs[groupOrderKey()] = gson.toJson(retainedOrder)
            prefs[groupOrderSchemaKey()] = IPTV_GROUP_ORDER_SCHEMA.toString()
        }
        groupOrderLocallyDirty = true
        if (sourceChanged) {
            withContext(Dispatchers.IO) { deletePersistedSourceCaches(previousSourceKey) }
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save iptv playlists")
    }

    suspend fun saveStalkerConfig(portalUrl: String, macAddress: String) {
        val profileId = profileManager.getProfileIdSync()
        val previousConfig = observeConfig().first()
        val normalizedUrl = portalUrl.trim().trimEnd('/')
        val normalizedMac = macAddress.trim().uppercase().let { mac ->
            if (mac.isNotEmpty() && !mac.startsWith("00:1A:79:")) mac else mac
        }
        // Legacy single-portal save: writes/updates the first portal entry.
        val existing = previousConfig.stalkerPortals
        val nextPortals = if (existing.isEmpty()) {
            listOf(StalkerPortalEntry("stalker1", "Portal 1", normalizedUrl, normalizedMac))
        } else {
            existing.mapIndexed { index, portal ->
                if (index == 0) portal.copy(portalUrl = normalizedUrl, macAddress = normalizedMac)
                else portal
            }
        }
        val nextConfig = previousConfig.copy(stalkerPortals = nextPortals)
        val previousSourceKey = epgIndexKey(profileId, previousConfig)
        val sourceChanged = buildSourceSignature(previousConfig) != buildSourceSignature(nextConfig)
        context.settingsDataStore.edit { prefs ->
            prefs[stalkerPortalsKey()] = gson.toJson(nextPortals)
            // Clear legacy single-portal keys so the list store becomes the source of truth.
            prefs.remove(stalkerPortalUrlKey())
            prefs.remove(stalkerMacAddressKey())
        }
        cachedStalkerApis = emptyMap()
        groupOrderLocallyDirty = true
        if (sourceChanged) {
            withContext(Dispatchers.IO) { deletePersistedSourceCaches(previousSourceKey) }
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save stalker config")
    }

    suspend fun clearStalkerConfig() {
        val profileId = profileManager.getProfileIdSync()
        val previousConfig = observeConfig().first()
        val previousSourceKey = epgIndexKey(profileId, previousConfig)
        val sourceChanged = previousConfig.stalkerPortals.isNotEmpty()
        context.settingsDataStore.edit { prefs ->
            prefs.remove(stalkerPortalsKey())
            prefs.remove(stalkerPortalUrlKey())
            prefs.remove(stalkerMacAddressKey())
        }
        // Drop both legacy and portal-scoped group preferences.
        (previousConfig.stalkerPortals.map { it.id } + STALKER_PLAYLIST_ID)
            .distinct()
            .forEach { clearGroupPreferences(it) }
        cachedStalkerApis = emptyMap()
        groupOrderLocallyDirty = true
        if (sourceChanged) {
            withContext(Dispatchers.IO) { deletePersistedSourceCaches(previousSourceKey) }
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "clear stalker config")
    }

    /**
     * Persists the full Stalker portal list — the Stalker counterpart of
     * [savePlaylists]. The caller (SettingsViewModel) builds the mutated list
     * (add / edit / remove / reorder / toggle / rename) and hands it here.
     *
     * - Normalizes every entry (trims URL, uppercases MAC, assigns default
     *   id/name) and caps the list at [MAX_STALKER_PORTALS].
     * - Drops group preferences for portals that were removed so no stale
     *   `stalker<id>|...` keys linger.
     * - Invalidates the Stalker API cache and persisted source caches when the
     *   source signature changes.
     */
    suspend fun saveStalkerPortals(portals: List<StalkerPortalEntry>) {
        val profileId = profileManager.getProfileIdSync()
        val previousConfig = observeConfig().first()
        val normalized = StalkerPortalSupport.normalizeStalkerPortals(
            portals,
            MAX_STALKER_PORTALS,
        )
        val nextConfig = previousConfig.copy(stalkerPortals = normalized)
        val previousSourceKey = epgIndexKey(profileId, previousConfig)
        val sourceChanged = buildSourceSignature(previousConfig) != buildSourceSignature(nextConfig)

        val removedIds = previousConfig.stalkerPortals
            .map { it.id }
            .filterNot { id -> normalized.any { it.id == id } }

        context.settingsDataStore.edit { prefs ->
            prefs[stalkerPortalsKey()] = gson.toJson(normalized)
            // Clear legacy single-portal keys so the list store stays authoritative.
            prefs.remove(stalkerPortalUrlKey())
            prefs.remove(stalkerMacAddressKey())
        }
        // Drop group preferences for removed portals (independent sets, decision #3).
        removedIds.forEach { clearGroupPreferences(it) }
        cachedStalkerApis = emptyMap()
        groupOrderLocallyDirty = true
        if (sourceChanged) {
            withContext(Dispatchers.IO) { deletePersistedSourceCaches(previousSourceKey) }
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save stalker portals")
    }

    /**
     * Resolves a Stalker `cmd` stream argument into an authenticated URL. When
     * [channelId] carries the `stalker:<portalId>:` prefix the matching portal
     * API is used; otherwise the first configured portal is used as a fallback
     * (keeps the legacy single-portal behavior intact).
     */
    suspend fun resolveStalkerStreamUrl(channelId: String?, command: String): String? {
        val config = observeConfig().first()
        val portals = config.stalkerPortals
        if (portals.isEmpty()) return null
        val portalId = channelId?.let { portalIdFromChannelId(it) }
        val portal = portalId
            ?.let { id -> portals.firstOrNull { it.id == id } }
            ?: portals.first()
        if (portal.portalUrl.isBlank() || portal.macAddress.isBlank()) return null
        val stalker = getOrCreateStalkerApi(portal)
        return stalker?.resolveStreamUrl(command)
    }

    suspend fun saveSortOrder(sortOrder: String) {
        val normalizedSortOrder = normalizeIptvSortOrder(sortOrder)
        context.settingsDataStore.edit { prefs ->
            prefs[sortOrderKey()] = normalizedSortOrder
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "save iptv sort order")
    }

    /**
     * Accept common Xtream Codes inputs and convert to a canonical M3U URL.
     *
     * Supported inputs:
     * - Full m3u/get.php URL: https://host/get.php?username=U&password=P&type=m3u_plus&output=ts
     * - Space-separated: https://host:port U P
     * - Line-separated: host\nuser\npass
     * - Prefix forms: xtream://host user pass (also xstream://)
     */
    private fun normalizeIptvInput(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""

        // Handle explicit Xtream triplets first (works for hosts with or without scheme).
        extractXtreamTriplet(trimmed)?.let { (host, user, pass) ->
            normalizeXtreamHost(host)?.let { base -> return buildXtreamM3uUrl(base, user, pass) }
        }

        // Already a URL.
        if (trimmed.contains("://")) {
            // If this is an Xtream get.php URL, normalize type/output to a sensible default.
            val parsed = trimmed.toHttpUrlOrNull()
            if (parsed != null && (
                    parsed.encodedPath.endsWith("/get.php") ||
                        parsed.encodedPath.endsWith("/player_api.php")
                    )
            ) {
                extractXtreamCredentialsFromUrl(parsed)?.let { (username, password) ->
                    val base = parsed.toXtreamBaseUrl()
                    return buildXtreamM3uUrl(base, username, password)
                }
            }
            return trimmed
        }

        // Multi-line: host\nuser\npass.
        val partsByLine = trimmed
            .split('\n', '\r')
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsByLine.size >= 3) {
            val host = partsByLine[0]
            val user = partsByLine[1]
            val pass = partsByLine[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamM3uUrl(base, user, pass) }
        }

        // Space-separated: host user pass.
        val partsBySpace = trimmed
            .split(MULTI_SPACE_REGEX)
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsBySpace.size >= 3) {
            val host = partsBySpace[0]
            val user = partsBySpace[1]
            val pass = partsBySpace[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamM3uUrl(base, user, pass) }
        }

        return trimmed
    }

    private fun normalizeStoredIptvUrl(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""
        val decoded = decodeLegacyBase64Url(trimmed)
        return normalizeIptvInput(decoded ?: trimmed)
    }

    /**
     * Accept Xtream credentials in the EPG field too.
     *
     * Supported:
     * - Full xmltv.php URL
     * - Full get.php URL (auto-converts to xmltv.php)
     * - host user pass (space-separated)
     * - host\\nuser\\npass (line-separated)
     * - xtream://host user pass
     */
    private fun normalizeEpgInput(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""

        // Handle explicit Xtream triplets first (works for hosts with or without scheme).
        extractXtreamTriplet(trimmed)?.let { (host, user, pass) ->
            normalizeXtreamHost(host)?.let { base -> return buildXtreamEpgUrl(base, user, pass) }
        }

        if (trimmed.contains("://")) {
            val parsed = trimmed.toHttpUrlOrNull()
            if (parsed != null) {
                val isXtreamPath = parsed.encodedPath.endsWith("/xmltv.php") ||
                    parsed.encodedPath.endsWith("/get.php") ||
                    parsed.encodedPath.endsWith("/player_api.php")
                if (isXtreamPath) {
                    extractXtreamCredentialsFromUrl(parsed)?.let { (username, password) ->
                        val base = parsed.toXtreamBaseUrl()
                        return buildXtreamEpgUrl(base, username, password)
                    }
                }
            }
            return trimmed
        }

        val partsByLine = trimmed
            .split('\n', '\r')
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsByLine.size >= 3) {
            val host = partsByLine[0]
            val user = partsByLine[1]
            val pass = partsByLine[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamEpgUrl(base, user, pass) }
        }

        val partsBySpace = trimmed
            .split(MULTI_SPACE_REGEX)
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (partsBySpace.size >= 3) {
            val host = partsBySpace[0]
            val user = partsBySpace[1]
            val pass = partsBySpace[2]
            normalizeXtreamHost(host)?.let { base -> return buildXtreamEpgUrl(base, user, pass) }
        }

        return trimmed
    }

    private fun normalizeStoredEpgInputs(raw: String): List<String> {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return emptyList()
        val decoded = decodeLegacyBase64Url(trimmed)
        return normalizeEpgInputs(decoded ?: trimmed)
    }

    private fun normalizeEpgInputs(raw: String): List<String> {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return emptyList()

        extractXtreamTriplet(trimmed)?.let { (host, user, pass) ->
            normalizeXtreamHost(host)?.let { base ->
                return listOf(buildXtreamEpgUrl(base, user, pass))
            }
        }

        val urls = HTTP_URL_REGEX.findAll(trimmed)
            .map { it.value.trim() }
            .filter { it.isNotBlank() }
            .toList()
        if (urls.size > 1) {
            return urls.map { normalizeEpgInput(it) }
                .filter { it.isNotBlank() }
                .distinct()
        }

        val parts = trimmed
            .split('\n', '\r', ',', ';', '|')
            .map { it.trim() }
            .filter { it.isNotBlank() }
        if (parts.size > 1) {
            return parts.flatMap { normalizeEpgInputs(it) }
                .filter { it.isNotBlank() }
                .distinct()
        }

        return listOf(normalizeEpgInput(trimmed))
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun normalizePlaylistEpgUrls(playlist: IptvPlaylistEntry): List<String> {
        val epgUrl = runCatching { playlist.epgUrl }.getOrNull().orEmpty()
        val epgUrls = runCatching { playlist.epgUrls }.getOrNull().orEmpty()
        return buildList {
            add(epgUrl)
            addAll(epgUrls)
        }
            .flatMap { normalizeStoredEpgInputs(it) }
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun normalizePlaylistEntry(playlist: IptvPlaylistEntry, index: Int): IptvPlaylistEntry? {
        val m3uUrl = normalizeStoredIptvUrl(runCatching { playlist.m3uUrl }.getOrNull().orEmpty())
        if (m3uUrl.isBlank()) return null
        val epgUrls = normalizePlaylistEpgUrls(playlist)
        return IptvPlaylistEntry(
            id = runCatching { playlist.id }.getOrNull().orEmpty().trim().ifBlank { "list_${index + 1}" },
            name = runCatching { playlist.name }.getOrNull().orEmpty().trim().ifBlank { "List ${index + 1}" },
            m3uUrl = m3uUrl,
            epgUrl = epgUrls.firstOrNull().orEmpty(),
            enabled = runCatching { playlist.enabled }.getOrDefault(true),
            epgUrls = epgUrls,
            // Selective import settings default to true for existing playlists
            importLiveTv = playlist.importLiveTv ?: true,
            importVod = playlist.importVod ?: true,
            importSeries = playlist.importSeries ?: true
        )
    }

    private fun decodeLegacyBase64Url(value: String): String? {
        val trimmed = value.trim()
        if (trimmed.length < 12 || trimmed.contains("://") || trimmed.any { it.isWhitespace() }) {
            return null
        }
        return listOf(Base64.DEFAULT, Base64.URL_SAFE or Base64.NO_WRAP)
            .mapNotNull { flags ->
                runCatching { String(Base64.decode(trimmed, flags), StandardCharsets.UTF_8).trim() }.getOrNull()
            }
            .firstOrNull { decoded ->
                decoded.startsWith("http://", ignoreCase = true) ||
                    decoded.startsWith("https://", ignoreCase = true)
            }
    }

    private fun normalizedIptvHttpUrlOrNull(raw: String): okhttp3.HttpUrl? {
        val normalized = normalizeStoredIptvUrl(raw)
        val parsed = normalized.toHttpUrlOrNull() ?: return null
        return parsed.takeIf { it.scheme == "http" || it.scheme == "https" }
    }

    private fun normalizedEpgHttpUrlOrNull(raw: String): okhttp3.HttpUrl? {
        val normalized = normalizeStoredEpgInputs(raw).firstOrNull().orEmpty()
        val parsed = normalized.toHttpUrlOrNull() ?: return null
        return parsed.takeIf { it.scheme == "http" || it.scheme == "https" }
    }

    private fun validatedIptvHttpUrl(raw: String, label: String): okhttp3.HttpUrl =
        normalizedIptvHttpUrlOrNull(raw)
            ?: throw IOException("$label is not a valid http(s) URL.")

    private fun validatedEpgHttpUrl(raw: String, label: String): okhttp3.HttpUrl =
        normalizedEpgHttpUrlOrNull(raw)
            ?: throw IOException("$label is not a valid http(s) URL.")

    private fun IptvPlaylistEntry.allEpgUrls(): List<String> {
        return buildList {
            add(epgUrl)
            addAll(epgUrls.orEmpty())
        }
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
    }

    private fun extractXtreamCredentialsFromUrl(parsed: okhttp3.HttpUrl): Pair<String, String>? {
        val username = parsed.queryParameter("username")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("user")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("uname")?.trim()?.ifBlank { null }
            ?: ""
        val password = parsed.queryParameter("password")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pass")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pwd")?.trim()?.ifBlank { null }
            ?: ""
        if (username.isNotBlank() && password.isNotBlank()) {
            return Pair(username, password)
        }
        return null
    }

    private data class XtreamTriplet(
        val host: String,
        val username: String,
        val password: String
    )

    private fun extractXtreamTriplet(raw: String): XtreamTriplet? {
        // Multi-line: host\nuser\npass.
        val partsByLine = raw
            .split('\n', '\r')
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .take(3)
            .toList()
        if (partsByLine.size == 3) {
            return XtreamTriplet(
                host = partsByLine[0],
                username = partsByLine[1],
                password = partsByLine[2]
            )
        }

        // Space-separated: host user pass.
        val partsBySpace = raw
            .split(MULTI_SPACE_REGEX)
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .take(3)
            .toList()
        if (partsBySpace.size == 3) {
            return XtreamTriplet(
                host = partsBySpace[0],
                username = partsBySpace[1],
                password = partsBySpace[2]
            )
        }

        return null
    }

    private fun okhttp3.HttpUrl.toXtreamBaseUrl(): String {
        val raw = toString().substringBefore('?').trimEnd('/')
        return raw
            .removeSuffix("/get.php")
            .removeSuffix("/xmltv.php")
            .removeSuffix("/player_api.php")
            .trimEnd('/')
    }

    private fun normalizeXtreamHost(host: String): String? {
        val h = host.trim().removeSuffix("/")
        if (h.isBlank()) return null

        val cleaned = h
            .removePrefix("xtream://")
            .removePrefix("xstream://")
            .removePrefix("xtreamcodes://")
            .removePrefix("xc://")
            .let {
                when {
                    it.startsWith("http:/", ignoreCase = true) && !it.startsWith("http://", ignoreCase = true) ->
                        "http://${it.removePrefix("http:/").removePrefix("/")}"
                    it.startsWith("https:/", ignoreCase = true) && !it.startsWith("https://", ignoreCase = true) ->
                        "https://${it.removePrefix("https:/").removePrefix("/")}"
                    else -> it
                }
            }

        // Add scheme if missing.
        return if (cleaned.startsWith("http://", true) || cleaned.startsWith("https://", true)) {
            cleaned.removeSuffix("/")
        } else {
            // Default to http (most providers use http).
            "http://${cleaned.removeSuffix("/")}"
        }
    }

    private fun buildXtreamM3uUrl(baseUrl: String, username: String, password: String): String {
        val safeBase = baseUrl.trim().trimEnd('/')
        val u = username.trim()
        val p = password.trim()
        return "$safeBase/get.php?username=$u&password=$p&type=m3u_plus&output=ts"
    }

    private fun buildXtreamEpgUrl(baseUrl: String, username: String, password: String): String {
        val safeBase = baseUrl.trim().trimEnd('/')
        val u = username.trim()
        val p = password.trim()
        return "$safeBase/xmltv.php?username=$u&password=$p"
    }

    fun getCatchupUrl(channel: IptvChannel, program: IptvProgram): String {
        return getCatchupUrlCandidates(channel, program).firstOrNull() ?: channel.streamUrl
    }

    suspend fun resolvePlayableCatchupUrl(
        channel: IptvChannel,
        program: IptvProgram,
        startAttempt: Int = 0
    ): String {
        if (program.catchupAvailable == false) {
            throw IOException(context.getString(R.string.iptv_no_catchup))
        }
        val candidates = getCatchupUrlCandidates(channel, program)
        if (candidates.isEmpty()) throw IOException(context.getString(R.string.iptv_no_catchup))
        val safeAttempt = startAttempt.coerceAtLeast(0)
        // Each playback retry gets a fresh, bounded batch, never a rotated full scan.
        val ordered = candidates.drop(safeAttempt.coerceAtMost(candidates.size) * catchupProbeCandidateLimit)
        return withContext(Dispatchers.IO) {
            for (candidate in ordered.take(catchupProbeCandidateLimit)) {
                val probe = probePlaybackUrl(candidate, channel.requestHeaders)
                if (probe != null && probe.isPlayable) {
                    System.err.println(
                        "[IPTV-Catchup] selected " +
                            "${if (candidate == ordered.firstOrNull()) "primary" else "fallback"} " +
                            "status=${probe.statusCode} url=${redactIptvUrl(candidate)}"
                    )
                    return@withContext candidate
                }
                if (probe != null) {
                    System.err.println(
                        "[IPTV-Catchup] rejected status=${probe.statusCode} reason=${probe.reason} " +
                            "url=${redactIptvUrl(candidate)}"
                    )
                    if (iptvProviderCooldownMs(probe.statusCode, null, System.currentTimeMillis()) > 0L) {
                        throw IOException("Catch-up stopped: provider returned HTTP ${probe.statusCode}. Please wait before retrying.")
                    }
                }
                if (probe == null) break
            }
            throw IOException(context.getString(R.string.iptv_no_catchup))
        }
    }

    fun getCatchupUrlCandidates(channel: IptvChannel, program: IptvProgram): List<String> {
        if (program.catchupAvailable == false) return emptyList()
        val startUnix = program.startUtcMillis / 1000L
        val endUnix = program.endUtcMillis / 1000L
        val nowUnix = System.currentTimeMillis() / 1000L
        val durationMs = (program.endUtcMillis - program.startUtcMillis).coerceAtLeast(1L)
        val durationMin = ((durationMs + 59_999L) / 60_000L).coerceAtLeast(1L)
        val creds = resolveXtreamCredentials(channel.streamUrl)
        val streamId = channel.xtreamStreamId ?: resolveXtreamStreamId(channel)
        val xtreamCandidates = if (creds != null && streamId != null) {
            buildXtreamCatchupCandidates(creds, streamId, program, durationMin)
        } else {
            emptyList()
        }

        val resolvedType = channel.catchupType?.trim()?.lowercase(Locale.US)?.takeIf { it.isNotBlank() }
            ?: if (xtreamCandidates.isNotEmpty()) "xtream" else "default"

        val sourceTemplate = channel.catchupSource?.takeIf { it.isNotBlank() }
        val serverStartMs = creds?.let { program.startUtcMillis + getServerOffset(it) } ?: program.startUtcMillis
        val candidates = when (resolvedType) {
            "xtream", "xc", "xciptv", "timeshift" -> {
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, serverStartMs, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    addAll(xtreamCandidates)
                }
            }
            "flussonic", "ts" -> {
                val connector = if (channel.streamUrl.contains("?")) "&" else "?"
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, program.startUtcMillis, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    add("${channel.streamUrl}${connector}utc=$startUnix")
                    addAll(xtreamCandidates)
                }
            }
            "append", "shift" -> {
                val connector = if (channel.streamUrl.contains("?")) "&" else "?"
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, program.startUtcMillis, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    add("${channel.streamUrl}${connector}utc=$startUnix&lutc=$nowUnix")
                    addAll(xtreamCandidates)
                }
            }
            "default", "source" -> {
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, serverStartMs, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    addAll(xtreamCandidates)
                }
            }
            else -> {
                // If catchup-source is present but type is unknown, try placeholder replacement anyway
                buildList {
                    sourceTemplate?.let {
                        add(applyCatchupSourceTemplate(channel, it, program, serverStartMs, startUnix, endUnix, nowUnix, durationMin, streamId))
                    }
                    addAll(xtreamCandidates)
                }
            }
        }
        return candidates
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
            .toList()
    }

    private fun buildXtreamCatchupCandidates(
        creds: XtreamCredentials,
        streamId: Int,
        program: IptvProgram,
        durationMin: Long
    ): List<String> {
        val serverStartMs = program.startUtcMillis + getServerOffset(creds)
        val minutePattern = IptvRepoDateRegexes.MINUTE_PATTERN
        val secondPattern = IptvRepoDateRegexes.SECOND_PATTERN
        val spaceSecondPattern = IptvRepoDateRegexes.SPACE_SECOND_PATTERN
        val spaceMinutePattern = IptvRepoDateRegexes.SPACE_MINUTE_PATTERN
        val hasSecondOffset = serverStartMs % 60_000L != 0L || program.startUtcMillis % 60_000L != 0L
        val starts = if (hasSecondOffset) {
            listOf(
                formatUtcDateTime(serverStartMs, secondPattern),
                formatUtcDateTime(program.startUtcMillis, secondPattern),
                formatUtcDateTime(serverStartMs, spaceSecondPattern),
                formatUtcDateTime(program.startUtcMillis, spaceSecondPattern),
                formatUtcDateTime(serverStartMs, minutePattern),
                formatUtcDateTime(program.startUtcMillis, minutePattern),
                formatUtcDateTime(serverStartMs, spaceMinutePattern),
                formatUtcDateTime(program.startUtcMillis, spaceMinutePattern)
            )
        } else {
            listOf(
                formatUtcDateTime(serverStartMs, minutePattern),
                formatUtcDateTime(program.startUtcMillis, minutePattern),
                formatUtcDateTime(serverStartMs, secondPattern),
                formatUtcDateTime(program.startUtcMillis, secondPattern),
                formatUtcDateTime(serverStartMs, spaceSecondPattern),
                formatUtcDateTime(program.startUtcMillis, spaceSecondPattern),
                formatUtcDateTime(serverStartMs, spaceMinutePattern),
                formatUtcDateTime(program.startUtcMillis, spaceMinutePattern)
            )
        }.distinct()

        return buildList {
            starts.forEach { startStr ->
                add(buildXtreamTimeshiftPathUrl(creds, streamId, startStr, durationMin, extension = "ts"))
                add(buildXtreamTimeshiftPathUrl(creds, streamId, startStr, durationMin, extension = null))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = true))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = false))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = true, streamParameter = "stream_id"))
                add(buildXtreamTimeshiftQueryUrl(creds, streamId, startStr, durationMin, includeStreamingPrefix = false, streamParameter = "stream_id"))
                add(buildXtreamTimeshiftPathUrl(creds, streamId, startStr, durationMin, extension = "m3u8"))
            }
        }.distinct()
    }

    private fun formatUtcDateTime(epochMs: Long, formatter: DateTimeFormatter): String {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMs), ZoneId.of("UTC")).format(formatter)
    }

    private fun applyCatchupSourceTemplate(
        channel: IptvChannel,
        source: String,
        program: IptvProgram,
        startForDateMs: Long,
        startUnix: Long,
        endUnix: Long,
        nowUnix: Long,
        durationMin: Long,
        streamId: Int?
    ): String {
        val startDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(startForDateMs), ZoneId.of("UTC"))
        val endDt = LocalDateTime.ofInstant(Instant.ofEpochMilli(program.endUtcMillis), ZoneId.of("UTC"))
        val durationSec = ((program.endUtcMillis - program.startUtcMillis) / 1000L).coerceAtLeast(1L)
        val templated = decodeM3uEntities(source)
            .replaceDurationScalePlaceholders(durationSec)
            .replaceDatePatternPlaceholders("start", startDt)
            .replaceDatePatternPlaceholders("end", endDt)
            .replace("{utc}", startUnix.toString())
            .replace("\${start}", startUnix.toString())
            .replace("{start}", startUnix.toString())
            .replace("\$start", startUnix.toString())
            .replace("{timestamp}", nowUnix.toString())
            .replace("\${timestamp}", nowUnix.toString())
            .replace("\$timestamp", nowUnix.toString())
            .replace("{lutc}", nowUnix.toString())
            .replace("\${lutc}", nowUnix.toString())
            .replace("\$lutc", nowUnix.toString())
            .replace("\${end}", endUnix.toString())
            .replace("{end}", endUnix.toString())
            .replace("\$end", endUnix.toString())
            .replace("{duration}", durationMin.toString())
            .replace("\${duration}", durationMin.toString())
            .replace("\$duration", durationMin.toString())
            .replace("{duration_sec}", durationSec.toString())
            .replace("\${duration_sec}", durationSec.toString())
            .replace("{channel}", streamId?.toString().orEmpty())
            .replace("\${channel}", streamId?.toString().orEmpty())
            .replace("\$channel", streamId?.toString().orEmpty())
            .replace("{channel_id}", streamId?.toString().orEmpty())
            .replace("\${channel_id}", streamId?.toString().orEmpty())
            .replace("\$channel_id", streamId?.toString().orEmpty())
            .replace("{stream}", streamId?.toString().orEmpty())
            .replace("\${stream}", streamId?.toString().orEmpty())
            .replace("\$stream", streamId?.toString().orEmpty())
            .replace("{stream_id}", streamId?.toString().orEmpty())
            .replace("\${stream_id}", streamId?.toString().orEmpty())
            .replace("\$stream_id", streamId?.toString().orEmpty())
            .replace("{Y}", startDt.format(IptvRepoDateRegexes.YEAR_PATTERN))
            .replace("{m}", startDt.format(IptvRepoDateRegexes.MONTH_PATTERN))
            .replace("{d}", startDt.format(IptvRepoDateRegexes.DAY_PATTERN))
            .replace("{H}", startDt.format(IptvRepoDateRegexes.HOUR_PATTERN))
            .replace("{M}", startDt.format(IptvRepoDateRegexes.MIN_PATTERN))
            .replace("{S}", startDt.format(IptvRepoDateRegexes.SEC_PATTERN))
            .replace("{start:Y}", startDt.format(IptvRepoDateRegexes.YEAR_PATTERN))
            .replace("{start:m}", startDt.format(IptvRepoDateRegexes.MONTH_PATTERN))
            .replace("{start:d}", startDt.format(IptvRepoDateRegexes.DAY_PATTERN))
            .replace("{start:H}", startDt.format(IptvRepoDateRegexes.HOUR_PATTERN))
            .replace("{start:M}", startDt.format(IptvRepoDateRegexes.MIN_PATTERN))
            .replace("{start:S}", startDt.format(IptvRepoDateRegexes.SEC_PATTERN))
            .replace("{end:Y}", endDt.format(IptvRepoDateRegexes.YEAR_PATTERN))
            .replace("{end:m}", endDt.format(IptvRepoDateRegexes.MONTH_PATTERN))
            .replace("{end:d}", endDt.format(IptvRepoDateRegexes.DAY_PATTERN))
            .replace("{end:H}", endDt.format(IptvRepoDateRegexes.HOUR_PATTERN))
            .replace("{end:M}", endDt.format(IptvRepoDateRegexes.MIN_PATTERN))
            .replace("{end:S}", endDt.format(IptvRepoDateRegexes.SEC_PATTERN))
        return when {
            templated.startsWith("http://", ignoreCase = true) || templated.startsWith("https://", ignoreCase = true) -> templated
            templated.startsWith("/") -> channel.streamUrl.toHttpUrlOrNull()?.let { parsed ->
                buildString {
                    append(parsed.scheme)
                    append("://")
                    append(parsed.host)
                    if (parsed.port != if (parsed.scheme == "https") 443 else 80) {
                        append(":${parsed.port}")
                    }
                    append(templated)
                }
            } ?: templated
            templated.startsWith("?") -> channel.streamUrl.substringBefore('?') + templated
            templated.startsWith("&") -> channel.streamUrl + templated
            else -> templated
        }
    }

    private fun decodeM3uEntities(value: String): String {
        return value
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&#34;", "\"")
            .replace("&apos;", "'")
            .replace("&#39;", "'")
    }

    private fun String.replaceDurationScalePlaceholders(durationSec: Long): String {
        return DURATION_SCALE_REGEX.replace(this) { match ->
            val divisor = (match.groupValues.getOrNull(1)?.takeIf { it.isNotBlank() }
                ?: match.groupValues.getOrNull(2))
                ?.toLongOrNull()
                ?.takeIf { it > 0L }
                ?: return@replace match.value
            (durationSec / divisor).coerceAtLeast(1L).toString()
        }
    }



    private fun String.replaceDatePatternPlaceholders(key: String, dateTime: LocalDateTime): String {
        val regex = IptvRepoDateRegexes.getDatePatternRegex(key)
        return regex.replace(this) { match ->
            val pattern = match.groupValues.getOrNull(1)?.takeIf { it.isNotBlank() }
                ?: match.groupValues.getOrNull(2)
                ?: return@replace match.value
            if (pattern in setOf("Y", "m", "d", "H", "M", "S")) {
                return@replace match.value
            }
            try {
                dateTime.format(IptvRepoDateRegexes.formatterFor(pattern))
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) throw e
                match.value
            }
        }
    }

    private fun buildXtreamTimeshiftPathUrl(
        creds: XtreamCredentials,
        streamId: Int,
        startStr: String,
        durationMin: Long,
        extension: String? = "ts"
    ): String {
        val encodedUser = urlEncodePathSegment(creds.username)
        val encodedPass = urlEncodePathSegment(creds.password)
        val suffix = extension?.let { ".$it" }.orEmpty()
        return "${creds.baseUrl}/timeshift/$encodedUser/$encodedPass/$durationMin/${urlEncodeTimeshiftStart(startStr)}/$streamId$suffix"
    }

    private fun buildXtreamTimeshiftQueryUrl(
        creds: XtreamCredentials,
        streamId: Int,
        startStr: String,
        durationMin: Long,
        includeStreamingPrefix: Boolean,
        streamParameter: String = "stream"
    ): String {
        val path = if (includeStreamingPrefix) "streaming/timeshift.php" else "timeshift.php"
        return "${creds.baseUrl}/$path?username=${urlEncodeQuery(creds.username)}" +
            "&password=${urlEncodeQuery(creds.password)}" +
            "&$streamParameter=$streamId&start=${urlEncodeQuery(startStr)}&duration=$durationMin"
    }

    private data class PlaybackProbeResult(
        val statusCode: Int,
        val isPlayable: Boolean,
        val reason: String
    )

    private fun probePlaybackUrl(url: String, headers: Map<String, String>): PlaybackProbeResult? {
        val ranged = executePlaybackProbe(url, headers, useRange = true)
        if (ranged == null || ranged.isPlayable) return ranged
        if (ranged.statusCode in setOf(405, 416)) {
            val normal = executePlaybackProbe(url, headers, useRange = false)
            if (normal?.isPlayable == true) return normal.copy(reason = "ok-no-range")
            return normal ?: ranged
        }
        return ranged
    }

    private fun executePlaybackProbe(
        url: String,
        headers: Map<String, String>,
        useRange: Boolean
    ): PlaybackProbeResult? {
        return runCatching {
            val builder = Request.Builder()
                .url(url)
                .header("User-Agent", headers["User-Agent"] ?: OkHttpProvider.userAgentOr(IPTV_USER_AGENT))
                .header("Accept", "*/*")
                .header("Accept-Encoding", "identity")
                .get()
            if (useRange) {
                builder.header("Range", "bytes=0-0")
            }
            headers.forEach { (name, value) ->
                if (name.isNotBlank() && value.isNotBlank() && !name.equals("Range", ignoreCase = true)) {
                    builder.header(name, value)
                }
            }
            xtreamGuideHttpClient.newCall(builder.build()).execute().use { response ->
                val statusCode = response.code
                if (statusCode !in 200..399) {
                    return@use PlaybackProbeResult(statusCode, isPlayable = false, reason = "http")
                }

                val contentType = response.header("Content-Type").orEmpty().lowercase(Locale.US)
                if (contentType.contains("text/html") || contentType.contains("application/json")) {
                    return@use PlaybackProbeResult(statusCode, isPlayable = false, reason = "content-type:$contentType")
                }

                PlaybackProbeResult(statusCode, isPlayable = true, reason = "ok")
            }
        }.getOrNull()
    }

    private fun redactIptvUrl(url: String): String {
        val withoutQuerySecrets = URL_QUERY_SECRETS_REGEX.replace(url) { match -> "${match.groupValues[1]}***" }

        return URL_PATH_SECRETS_REGEX
            .replace(withoutQuerySecrets) { match ->
                "${match.groupValues[1]}***/***${match.groupValues[4]}"
            }
            .take(260)
    }

    private fun urlEncodeQuery(value: String): String =
        java.net.URLEncoder.encode(value, StandardCharsets.UTF_8.name()).replace("+", "%20")

    private fun urlEncodePathSegment(value: String): String =
        urlEncodeQuery(value).replace("%2F", "%252F")

    private fun urlEncodeTimeshiftStart(value: String): String =
        urlEncodeQuery(value).replace("%3A", ":")

    suspend fun clearConfig() {
        val profileId = profileManager.getProfileIdSync()
        val previousConfig = observeConfig().first()
        val previousSourceKey = epgIndexKey(profileId, previousConfig)
        context.settingsDataStore.edit { prefs ->
            prefs.remove(m3uUrlKey())
            prefs.remove(epgUrlKey())
            prefs.remove(playlistsKey())
            prefs.remove(stalkerPortalsKey())
            prefs.remove(stalkerPortalUrlKey())
            prefs.remove(stalkerMacAddressKey())
            prefs.remove(favoriteGroupsKey())
            prefs.remove(favoriteChannelsKey())
            prefs.remove(hiddenGroupsKey())
            prefs.remove(lockedGroupsKey())
            prefs.remove(groupOrderKey())
            prefs.remove(groupOrderSchemaKey())
            prefs.remove(tvSessionKey())
            IptvCloudFields.stamp(prefs, profileId, org.json.JSONObject()
                .put("playlists", org.json.JSONArray()).put("m3uUrl", "").put("epgUrl", "")
                .put("stalkerPortals", org.json.JSONArray()).put("stalkerPortalUrl", "").put("stalkerMacAddress", "")
                .put("favoriteGroups", org.json.JSONArray()).put("favoriteChannels", org.json.JSONArray())
                .put("hiddenGroups", org.json.JSONArray()).put("lockedGroups", org.json.JSONArray())
                .put("groupOrder", org.json.JSONArray()).put("groupOrderSchema", IPTV_GROUP_ORDER_SCHEMA))
        }
        groupOrderLocallyDirty = true
        cachedStalkerApis = emptyMap()
        withContext(Dispatchers.IO) { deletePersistedSourceCaches(previousSourceKey) }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "clear iptv config")
    }

    suspend fun importCloudConfig(
        m3uUrl: String,
        epgUrl: String,
        favoriteGroups: List<String>,
        favoriteChannels: List<String> = emptyList()
    ) {
        val normalizedM3u = normalizeStoredIptvUrl(m3uUrl)
        val normalizedEpgUrls = normalizeStoredEpgInputs(epgUrl)
        val normalizedEpg = normalizedEpgUrls.firstOrNull().orEmpty()
        context.settingsDataStore.edit { prefs ->
            if (normalizedM3u.isBlank()) {
                prefs.remove(m3uUrlKey())
            } else {
                prefs[m3uUrlKey()] = encryptConfigValue(normalizedM3u)
            }
            if (normalizedEpg.isBlank()) {
                prefs.remove(epgUrlKey())
            } else {
                prefs[epgUrlKey()] = encryptConfigValue(normalizedEpg)
            }
            prefs[playlistsKey()] = gson.toJson(
                if (normalizedM3u.isNotBlank()) {
                    listOf(
                        IptvPlaylistEntry(
                            "list_1",
                            "List 1",
                            normalizedM3u,
                            normalizedEpgUrls.firstOrNull().orEmpty(),
                            epgUrls = normalizedEpgUrls
                        )
                    )
                } else {
                    emptyList()
                }
            )
            val cleanedFavorites = favoriteGroups
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .distinct()
            if (cleanedFavorites.isEmpty()) {
                prefs.remove(favoriteGroupsKey())
            } else {
                prefs[favoriteGroupsKey()] = gson.toJson(cleanedFavorites)
            }

            val cleanedFavoriteChannels = favoriteChannels
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .distinct()
            if (cleanedFavoriteChannels.isEmpty()) {
                prefs.remove(favoriteChannelsKey())
            } else {
                prefs[favoriteChannelsKey()] = gson.toJson(cleanedFavoriteChannels)
            }
        }
        invalidateCache()
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "import iptv config")
    }

    suspend fun toggleFavoriteGroup(groupName: String) {
        val trimmed = groupName.trim()
        if (trimmed.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val existing = decodeFavoriteGroups(prefs).toMutableList()
            if (existing.contains(trimmed)) {
                existing.remove(trimmed)
            } else {
                existing.remove(trimmed)
                existing.add(0, trimmed) // newest favorite first
            }
            prefs[favoriteGroupsKey()] = gson.toJson(existing)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "toggle favorite group")
    }

    suspend fun toggleHiddenGroup(playlistId: String, groupName: String) {
        val trimmed = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val existing = decodeHiddenGroups(prefs).toMutableList()
            if (existing.contains(trimmed)) {
                existing.remove(trimmed)
            } else {
                existing.add(trimmed)
            }
            prefs[hiddenGroupsKey()] = gson.toJson(existing)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "toggle hidden group")
    }

    suspend fun toggleLockedGroup(playlistId: String, groupName: String) {
        val trimmedGroup = groupName.trim()
        val trimmedPlaylist = playlistId.trim()
        if (trimmedPlaylist.isEmpty() || trimmedGroup.isEmpty()) return
        val key = PlaylistGroupKey.build(trimmedPlaylist, trimmedGroup)
        context.settingsDataStore.edit { prefs ->
            val existing = decodeLockedGroups(prefs).toMutableList()
            if (!existing.remove(key)) existing.add(key)
            if (existing.isEmpty()) prefs.remove(lockedGroupsKey())
            else prefs[lockedGroupsKey()] = gson.toJson(existing.distinct())
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "toggle locked group")
    }

    /**
     * Show or hide all groups of a playlist in one operation. Used by the
     * "show all / hide all" bulk toggle in the categories screen. A group is
     * hidden when its playlistId|groupName key is present in the hidden set;
     * `hidden=true` adds the missing keys, `hidden=false` removes them.
     */
    suspend fun setGroupsHidden(playlistId: String, groups: List<String>, hidden: Boolean) {
        val trimmedId = playlistId.trim()
        if (trimmedId.isEmpty()) return
        val targetKeys = groups
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { PlaylistGroupKey.build(trimmedId, it) }
            .toHashSet()
        if (targetKeys.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val existing = decodeHiddenGroups(prefs).toMutableList()
            if (hidden) {
                existing.addAll(targetKeys)
            } else {
                existing.removeAll { it in targetKeys }
            }
            prefs[hiddenGroupsKey()] = gson.toJson(existing.distinct())
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "set groups hidden")
    }

    /**
     * Remove every hidden-group and group-order entry that belongs to the
     * given playlist. Called when a Stalker portal is removed so no stale
     * `stalker|...` preferences linger after the source is gone.
     */
    suspend fun clearGroupPreferences(playlistId: String) {
        val trimmedId = playlistId.trim()
        if (trimmedId.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val retainedHidden = decodeHiddenGroups(prefs)
                .filterNot { PlaylistGroupKey(it).playlistId == trimmedId }
            if (retainedHidden.isEmpty()) prefs.remove(hiddenGroupsKey())
            else prefs[hiddenGroupsKey()] = gson.toJson(retainedHidden)

            val retainedLocked = decodeLockedGroups(prefs)
                .filterNot { PlaylistGroupKey(it).playlistId == trimmedId }
            if (retainedLocked.isEmpty()) prefs.remove(lockedGroupsKey())
            else prefs[lockedGroupsKey()] = gson.toJson(retainedLocked)

            val retainedOrder = decodeGroupOrder(prefs)
                .filterNot { PlaylistGroupKey(it).playlistId == trimmedId }
            if (retainedOrder.isEmpty()) prefs.remove(groupOrderKey())
            else prefs[groupOrderKey()] = gson.toJson(retainedOrder)
            prefs[groupOrderSchemaKey()] = IPTV_GROUP_ORDER_SCHEMA.toString()
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "clear group preferences")
    }

    suspend fun moveGroupUp(playlistId: String, groupName: String, currentGroups: List<String> = emptyList()) {
        val target = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        val currentKeys = currentGroups.map { PlaylistGroupKey.build(playlistId, it.trim()) }
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentKeys)
            if (order.isEmpty()) return@edit
            val idx = order.indexOf(target)
            if (idx > 0) { order.removeAt(idx); order.add(idx - 1, target) }
            prefs[groupOrderKey()] = gson.toJson(order)
            prefs[groupOrderSchemaKey()] = IPTV_GROUP_ORDER_SCHEMA.toString()
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group up")
    }

    suspend fun moveGroupToTop(playlistId: String, groupName: String, currentGroups: List<String> = emptyList()) {
        val target = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        val currentKeys = currentGroups.map { PlaylistGroupKey.build(playlistId, it.trim()) }
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentKeys)
            if (order.isEmpty()) return@edit
            if (target !in order && currentKeys.contains(target)) order.add(target)
            order.remove(target)
            order.add(0, target)
            prefs[groupOrderKey()] = gson.toJson(order)
            prefs[groupOrderSchemaKey()] = IPTV_GROUP_ORDER_SCHEMA.toString()
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group to top")
    }

    suspend fun moveGroupDown(playlistId: String, groupName: String, currentGroups: List<String> = emptyList()) {
        val target = PlaylistGroupKey.build(playlistId, groupName.trim())
        if (groupName.trim().isEmpty()) return
        val currentKeys = currentGroups.map { PlaylistGroupKey.build(playlistId, it.trim()) }
        context.settingsDataStore.edit { prefs ->
            val order = mergedGroupOrder(decodeGroupOrder(prefs), currentKeys)
            if (order.isEmpty()) return@edit
            val idx = order.indexOf(target)
            if (idx >= 0 && idx < order.size - 1) { order.removeAt(idx); order.add(idx + 1, target) }
            prefs[groupOrderKey()] = gson.toJson(order)
            prefs[groupOrderSchemaKey()] = IPTV_GROUP_ORDER_SCHEMA.toString()
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "move group down")
    }

    suspend fun resetGroupOrder(playlistId: String) {
        context.settingsDataStore.edit { prefs ->
            val existing = decodeGroupOrder(prefs).toMutableList()
            existing.removeAll { PlaylistGroupKey(it).playlistId == playlistId }
            prefs[groupOrderKey()] = gson.toJson(existing)
            prefs[groupOrderSchemaKey()] = IPTV_GROUP_ORDER_SCHEMA.toString()
        }
        groupOrderLocallyDirty = true
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "reset group order")
    }

    suspend fun toggleFavoriteChannel(channelId: String) {
        val trimmed = channelId.trim()
        if (trimmed.isEmpty()) return
        context.settingsDataStore.edit { prefs ->
            val existing = decodeFavoriteChannels(prefs).toMutableList()
            if (existing.contains(trimmed)) {
                existing.remove(trimmed)
            } else {
                existing.remove(trimmed)
                existing.add(0, trimmed)
            }
            prefs[favoriteChannelsKey()] = gson.toJson(existing)
        }
        invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "toggle favorite channel")
    }

    suspend fun setFavoriteChannel(channelId: String, favorite: Boolean) {
        val trimmed = channelId.trim()
        if (trimmed.isEmpty()) return
        var changed = false
        context.settingsDataStore.edit { prefs ->
            val existing = decodeFavoriteChannels(prefs)
            val updated = favoriteChannelsWithMembership(existing, trimmed, favorite)
            if (updated != existing) {
                prefs[favoriteChannelsKey()] = gson.toJson(updated)
                changed = true
            }
        }
        if (changed) {
            invalidationBus.markDirty(CloudSyncScope.IPTV, profileManager.getProfileIdSync(), "set favorite channel")
        }
    }

    /**
     * Reorders a channel within the favourites list.
     *
     * The stored order is the display order everywhere favourites are surfaced — the Live TV
     * favourites category and the home "Favorite TV" row both iterate this list — so moving an
     * entry here is what "move up" means to the user. [delta] is negative to move earlier.
     * No-ops when the channel is not a favourite or is already at the end it is moving toward.
     */
    suspend fun moveFavoriteChannel(channelId: String, delta: Int) {
        val trimmed = channelId.trim()
        if (trimmed.isEmpty() || delta == 0) return
        var changed = false
        context.settingsDataStore.edit { prefs ->
            val existing = decodeFavoriteChannels(prefs).toMutableList()
            val from = existing.indexOf(trimmed)
            if (from < 0) return@edit
            val to = (from + delta).coerceIn(0, existing.lastIndex)
            if (to == from) return@edit
            existing.removeAt(from)
            existing.add(to, trimmed)
            prefs[favoriteChannelsKey()] = gson.toJson(existing)
            changed = true
        }
        if (changed) {
            invalidationBus.markDirty(
                CloudSyncScope.IPTV,
                profileManager.getProfileIdSync(),
                "move favorite channel"
            )
        }
    }

    suspend fun loadSnapshot(
        forcePlaylistReload: Boolean = false,
        forceEpgReload: Boolean = false,
        allowNetworkEpgFetch: Boolean = false,
        allowBroadShortEpg: Boolean = true,
        onProgress: (IptvLoadProgress) -> Unit = {},
        onChannelsReady: suspend (List<IptvChannel>) -> Unit = {}
    ): IptvSnapshot {
        // Taken before the lock on purpose: it is the moment this caller asked
        // for data, not the moment it got its turn. A forced reload uses it to
        // tell "the list is from before I asked" from "someone downloaded it
        // while I was waiting" — the second entry point reacting to a single
        // configuration change is the latter, and must not download again.
        val requestedAtMs = System.currentTimeMillis()
        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
            cleanupStaleEpgTempFiles()
            onProgress(IptvLoadProgress(context.getString(R.string.iptv_starting_load), 2))
            val now = System.currentTimeMillis()
            val config = observeConfig().first()
            val profileId = profileManager.getProfileIdSync()
            ensureCacheOwnership(profileId, config)
            cleanupIptvCacheDirectory()
            val activePlaylists = activePlaylists(config)
            val stalkerPortals = activeStalkerPortals(config)
            if (activePlaylists.isEmpty() && stalkerPortals.isEmpty()) {
                return@withContext IptvSnapshot(
                    channels = emptyList(),
                    grouped = emptyMap(),
                    nowNext = emptyMap(),
                    favoriteGroups = observeFavoriteGroups().first(),
                    favoriteChannels = observeFavoriteChannels().first(),
                    loadedAt = Instant.now()
                )
            }

            // ── Stalker Portal path ──
            // Load every enabled Stalker portal in parallel with M3U/Xtream
            // playlists when both are configured (hybrid mode). Stalker-only
            // (no playlists) keeps the legacy early-return behavior.
            // A forced reload asks for data that is newer than the request, so
            // a list remembered from before it is skipped — but one downloaded
            // while this caller waited for the lock counts, and a download that
            // is already running is shared. Skipping every remembered list
            // instead made two entry points reacting to the same configuration
            // change pull the full channel list twice (measured: 2 x 27.67 MB
            // on every playlist toggle).
            val stalkerChannelsDeferred = if (stalkerPortals.isNotEmpty()) {
                async {
                    onProgress(IptvLoadProgress(context.getString(R.string.iptv_connecting_stalker), 10))
                    loadStalkerChannels(
                        stalkerPortals,
                        freshSinceMs = if (forcePlaylistReload) requestedAtMs else 0L
                    )
                }
            } else {
                null
            }

            // Stalker-only mode: no playlists configured → return Stalker channels directly.
            if (activePlaylists.isEmpty() && stalkerPortals.isNotEmpty()) {
                val (stalkerApis, stalkerChannels) = stalkerChannelsDeferred?.await()
                    ?: (emptyMap<String, com.arflix.tv.data.api.StalkerApi>() to emptyList<IptvChannel>())
                if (stalkerChannels.isEmpty()) {
                    return@withContext IptvSnapshot(
                        epgWarning = "Stalker handshake failed. Check Portal URL and MAC.",
                        loadedAt = Instant.now()
                    )
                }
                onProgress(IptvLoadProgress(context.getString(R.string.iptv_loaded_channels, stalkerChannels.size), 80))
                val grouped = buildGroupedChannels(stalkerChannels)
                val favGroups = observeFavoriteGroups().first()
                val favChannels = observeFavoriteChannels().first()
                val hiddenGroups = observeHiddenGroups().first()
                val groupOrder = observeGroupOrder().first()
                cachedChannels = stalkerChannels
                cachedGroupedChannels = grouped
                cachedStalkerApis = stalkerApis
                System.err.println(
                    "[EPG] loadSnapshot Stalker-only: allowNetworkEpgFetch=$allowNetworkEpgFetch " +
                        "apis=${stalkerApis.keys} channels=${stalkerChannels.size}"
                )
                val stalkerNowNext = if (allowNetworkEpgFetch) {
                    val fresh = runCatching {
                        fetchStalkerEpgForActivePortals(stalkerApis, stalkerChannels)
                    }.getOrDefault(emptyMap())
                    if (fresh.isNotEmpty()) persistEpgIndexChannels(config, fresh, System.currentTimeMillis())
                    fresh
                } else {
                    runCatching {
                        epgIndex.loadNowNext(currentEpgIndexKey(config), stalkerChannels.asSequence().map { it.id }.toSet())
                    }.getOrDefault(emptyMap())
                }
                val snapshot = IptvSnapshot(
                    channels = stalkerChannels,
                    grouped = grouped,
                    nowNext = stalkerNowNext,
                    favoriteGroups = favGroups,
                    favoriteChannels = favChannels,
                    hiddenGroups = hiddenGroups,
                    groupOrder = groupOrder,
                    loadedAt = Instant.now()
                )
                onProgress(IptvLoadProgress(context.getString(R.string.done), 100))
                return@withContext snapshot
            }

            val cachedChannelsFromDisk = if (cachedChannels.isEmpty()) readChannelCache(config) else null
            val cachedChannelsAreLarge = cachedChannelsFromDisk?.let { cached ->
                isLargePersistedChannelSnapshot(config, cached.channels.size)
            } ?: false
            val cachedGuideFromDisk = if (cachedChannelsFromDisk != null && !cachedChannelsAreLarge) {
                readCache(config)
            } else {
                null
            }
            if (cachedChannelsFromDisk != null) {
                cachedChannels = cachedChannelsFromDisk.channels
                cachedGroupedChannels = buildGroupedChannels(cachedChannelsFromDisk.channels)
                cachedNowNext = ConcurrentHashMap(cachedGuideFromDisk?.nowNext.orEmpty())
                cachedPlaylistAt = cachedChannelsFromDisk.loadedAtEpochMs
                cachedEpgAt = cachedGuideFromDisk?.loadedAtEpochMs ?: cachedChannelsFromDisk.loadedAtEpochMs
                if (!cachedChannelsAreLarge) {
                    reDeriveCachedNowNext(cachedChannelsFromDisk.channels.asSequence().map { it.id }.toSet())
                } else {
                    System.err.println(
                        "[EPG-Memory] large playlist warm start uses indexed guide only; " +
                            "window=${cachedChannelsFromDisk.channels.size}"
                    )
                }
            }

            var playlistChanged = false
            val channels = if (!forcePlaylistReload && cachedChannels.isNotEmpty()) {
                val isFresh = now - cachedPlaylistAt < playlistCacheMs
                onProgress(
                    IptvLoadProgress(
                        if (isFresh) {
                            "Using cached playlist (${cachedChannels.size} channels)"
                        } else {
                            "Using cached playlist (${cachedChannels.size} channels, stale)"
                        },
                        80
                    )
                )
                // If Stalker is configured but not yet in cache, await and merge.
                if (stalkerChannelsDeferred != null) {
                    val (stalkerApis, stalkerChannels) = stalkerChannelsDeferred.await()
                    if (stalkerApis.isNotEmpty()) {
                        cachedStalkerApis = stalkerApis
                    }
                    if (stalkerChannels.isNotEmpty()) {
                        // cachedChannels may already contain Stalker entries merged by a
                        // previous load (memory) or read from the disk cache — drop those
                        // first so repeated cached loads don't duplicate them.
                        (cachedChannels.filterNot { it.id.startsWith("stalker:") } + stalkerChannels).also { merged ->
                            cachedChannels = merged
                            cachedGroupedChannels = buildGroupedChannels(merged)
                            playlistChanged = true
                        }
                    } else {
                        cachedChannels
                    }
                } else {
                    cachedChannels
                }
            } else {
                coroutineScope {
                    val playlistResults = Array<MutableList<IptvChannel>?>(activePlaylists.size) { null }
                    val playlistErrors = arrayOfNulls<Throwable>(activePlaylists.size)
                    val playlistResultsLock = Any()
                    activePlaylists.mapIndexed { playlistIndex, playlist ->
                        async {
                            val playlistChannels = try {
                                fetchChannelsForPlaylistWithRetries(playlist, onProgress)
                                    .map { channel ->
                                        channel.copy(
                                            id = "${playlist.id}:${channel.id}",
                                            group = channel.group
                                        )
                                    }
                            } catch (error: Throwable) {
                                if (error is kotlinx.coroutines.CancellationException) throw error
                                synchronized(playlistResultsLock) {
                                    playlistErrors[playlistIndex] = error
                                }
                                System.err.println(
                                    "IptvRepository: playlist ${playlist.id} failed without blocking other providers: " +
                                        error.message
                                )
                                emptyList()
                            }
                            if (playlistChannels.isNotEmpty()) {
                                val currentList = synchronized(playlistResultsLock) {
                                    playlistResults[playlistIndex] = playlistChannels.toMutableList()
                                    ArrayList<IptvChannel>().apply {
                                        playlistResults.forEach { result ->
                                            if (!result.isNullOrEmpty()) addAll(result)
                                        }
                                    }
                                }
                                runCatching { onChannelsReady(currentList) }
                            } else {
                                synchronized(playlistResultsLock) {
                                    playlistResults[playlistIndex] = mutableListOf()
                                }
                            }
                        }
                    }.awaitAll()
                    synchronized(playlistResultsLock) {
                        val merged = playlistResults.flatMap { it.orEmpty() }
                        if (merged.isEmpty()) {
                            playlistErrors.firstOrNull { it != null }?.let { throw it }
                        }
                        merged
                    }
                }.let { playlistChannels ->
                    // Merge Stalker channels (if configured) into the final list.
                    val (stalkerApis, stalkerChannels) = stalkerChannelsDeferred?.await()
                        ?: (emptyMap<String, com.arflix.tv.data.api.StalkerApi>() to emptyList<IptvChannel>())
                    if (stalkerApis.isNotEmpty()) {
                        cachedStalkerApis = stalkerApis
                    }
                    val merged = playlistChannels + stalkerChannels
                    cachedChannels = merged
                    cachedGroupedChannels = buildGroupedChannels(merged)
                    cachedPlaylistAt = System.currentTimeMillis()
                    playlistChanged = true
                    merged
                }
            }

            // Publish channels immediately so the UI can show them while EPG loads.
            // Uses cached nowNext if available to paint initial EPG state.
            runCatching { onChannelsReady(channels) }
            if (playlistChanged && channels.isNotEmpty()) {
                val immediateNowNext = if (channels.size > LargeIptvListChannelCount) {
                    emptyMap()
                } else {
                    reDeriveCachedNowNext(channels.asSequence().map { it.id }.toSet()).orEmpty()
                }
                writeCache(
                    config = config,
                    channels = channels,
                    nowNext = immediateNowNext,
                    loadedAtMs = System.currentTimeMillis(),
                    persistChannels = true
                )
            }

            if (allowNetworkEpgFetch) {
                discoverEmbeddedEpgSourcesIfNeeded(activePlaylists)
            }
            val epgCandidates = resolveScopedEpgCandidates(config)
            val largePersistedPlaylist = isLargePersistedChannelSnapshot(config, channels.size)
            var epgUpdated = false
            val cachedHasPrograms = hasAnyProgramData(cachedNowNext)
            val shouldUseCachedEpg = !forceEpgReload && (
                cachedHasPrograms ||
                    (!cachedHasPrograms && now - cachedEpgAt < epgEmptyRetryMs)
                )
            var epgFailureMessage: String? = null

            // Check if this is an Xtream provider (can use fast short EPG API)
            val xtreamProviderGroups = groupXtreamChannelsByCredentials(config, channels)
            val hasXtreamChannels = xtreamProviderGroups.isNotEmpty()
            val shouldFetchBroadShortEpg = channels.size <= startupShortEpgChannelLimit &&
                (allowBroadShortEpg || epgCandidates.isEmpty())
            System.err.println("[EPG] loadSnapshot: forceEpgReload=$forceEpgReload shouldUseCachedEpg=$shouldUseCachedEpg cachedHasPrograms=$cachedHasPrograms xtreamProviders=${xtreamProviderGroups.size} hasXtreamChannels=$hasXtreamChannels epgCandidates=${epgCandidates.size} broadShort=$shouldFetchBroadShortEpg")
            val cachedFallbackNowNext = if (channels.size > LargeIptvListChannelCount) {
                emptyMap()
            } else {
                reDeriveCachedNowNext(channels.asSequence().map { it.id }.toSet()) ?: cachedNowNext
            }
            val nowNext = if (shouldUseCachedEpg) {
                onProgress(IptvLoadProgress(context.getString(R.string.epg_using_cached), 92))
                System.err.println("[EPG] Using cached EPG (${cachedNowNext.size} channels, age=${(now - cachedEpgAt)/1000}s)")
                cachedFallbackNowNext
            } else if (!allowNetworkEpgFetch) {
                if (cachedFallbackNowNext.isNotEmpty()) {
                    onProgress(IptvLoadProgress(context.getString(R.string.epg_using_cached), 92))
                    System.err.println("[EPG] Skipping broad network EPG fetch and using cached fallback (${cachedFallbackNowNext.size} channels)")
                    cachedFallbackNowNext
                } else {
                    System.err.println("[EPG] Skipping broad network EPG fetch until category-scoped guide request")
                    emptyMap()
                }
            } else if (epgCandidates.isEmpty() && !hasXtreamChannels) {
                if (cachedFallbackNowNext.isNotEmpty()) {
                    onProgress(IptvLoadProgress(context.getString(R.string.epg_using_cached), 92))
                    System.err.println("[EPG] No active EPG source, keeping cached EPG fallback (${cachedFallbackNowNext.size} channels)")
                    cachedFallbackNowNext
                } else {
                    onProgress(IptvLoadProgress(context.getString(R.string.epg_no_url), 90))
                    System.err.println("[EPG] No EPG URL and no Xtream creds - skipping EPG")
                    emptyMap()
                }
            } else {
                var resolvedNowNext: Map<String, IptvNowNext> = emptyMap()
                var resolved = false

                // Collect EPG from multiple sources and merge them all.
                // Short EPG is fast (~10s) but only covers channels that have data.
                // XMLTV is slow (~60s) but comprehensive. Both run, results are merged.
                var shortEpgResult: Map<String, IptvNowNext>? = null

                // ── Fast path: Xtream short EPG API ──
                if (hasXtreamChannels && shouldFetchBroadShortEpg) {
                    System.err.println("[EPG] Attempting provider-scoped Xtream short EPG (${xtreamProviderGroups.size} providers)")
                    val shortEpgAttempt = runCatching {
                        fetchXtreamShortEpgForActiveProviders(config, channels, onProgress)
                    }
                    if (shortEpgAttempt.isSuccess) {
                        val parsed = shortEpgAttempt.getOrNull()
                        val parsedHasData = parsed != null && hasAnyProgramData(parsed)
                        System.err.println("[EPG] Xtream short EPG result: ${parsed?.size ?: 0} channels, hasData=$parsedHasData")
                        if (parsed != null && parsedHasData) {
                            shortEpgResult = parsed
                            // Provide immediate results: merge short EPG with cached data (no stale removal)
                            cachedNowNext.putAll(parsed) // Short EPG data takes priority (fresher)
                            resolvedNowNext = cachedNowNext
                            cachedEpgAt = System.currentTimeMillis()
                            persistEpgIndexChannels(config, parsed, cachedEpgAt)
                            epgUpdated = true
                            resolved = true
                            System.err.println("[EPG] Xtream short EPG SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                        }
                    } else {
                        System.err.println("[EPG] Xtream short EPG FAILED: ${shortEpgAttempt.exceptionOrNull()?.message}")
                    }
                } else if (hasXtreamChannels) {
                    System.err.println("[EPG] Skipping broad Xtream short EPG so full XMLTV can backfill ${channels.size} channels first")
                }

                // ── Slow path: XMLTV download. On large lists normal startup keeps first paint fast
                // by skipping this after short EPG; forced/background refreshes still run it.
                val skipXmlTvAfterXtreamShort = hasXtreamChannels &&
                    !forceEpgReload &&
                    channels.size > LargeIptvListChannelCount &&
                    shortEpgResult != null &&
                    hasAnyProgramData(shortEpgResult)
                if (skipXmlTvAfterXtreamShort) {
                    System.err.println(
                        "[EPG] Skipping XMLTV after large-list Xtream sweep; " +
                            "provider short/simple APIs already returned ${shortEpgResult?.size ?: 0} channels"
                    )
                } else if (epgCandidates.isNotEmpty()) {
                    val epgCandidatesToTry = epgCandidates
                    var bestCoverage = epgCoverageRatio(channels, resolvedNowNext)
                    val mergedXmlNowNext = ConcurrentHashMap(resolvedNowNext)
                    var xmltvChanged = false
                    val indexedXmlPlaylists = HashSet<String?>()
                    val completedXmlUrls = HashSet<String>()
                    val xmlChannels = if (largePersistedPlaylist) {
                        channelStore.loadAll(currentEpgIndexKey(config))
                    } else channels
                    for ((index, candidate) in epgCandidatesToTry.withIndex()) {
                        if (candidate.providerFallback && candidate.playlistId in indexedXmlPlaylists) continue
                        val epgUrl = candidate.url
                        val candidateChannels = channelsForScopedEpgCandidate(candidate, xmlChannels)
                        if (candidateChannels.isEmpty()) continue
                        val pct = (90 + ((index * 8) / epgCandidatesToTry.size.coerceAtLeast(1))).coerceIn(90, 98)
                        onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_full_epg, index + 1, epgCandidatesToTry.size), pct))
                        val attempt = runCatching {
                            // 300 s: some providers (like TX-4K) serve a 100 MB
                            // XMLTV dump that needs 2-3 min on a TV's WiFi.
                            // 90 s was aborting before the file finished.
                            withTimeoutOrNull(300_000L) {
                                val jobContext = currentCoroutineContext()
                                runInterruptible(Dispatchers.IO) {
                                    fetchAndParseEpg(epgUrl, candidateChannels) { jobContext.ensureActive() }
                                }
                            }
                                ?: throw java.util.concurrent.TimeoutException(context.getString(R.string.epg_timeout, epgUrl.take(80)))
                        }
                        if (attempt.isSuccess) {
                            val parsed = attempt.getOrDefault(emptyMap())
                            val parsedHasPrograms = hasAnyProgramData(parsed)
                            if (parsedHasPrograms) {
                                completedXmlUrls.add(epgUrl)
                                // Auto-discovered Xtream URLs are alternatives to the same feed.
                                indexedXmlPlaylists.add(candidate.playlistId)
                                xmltvChanged = true
                                parsed.forEach { (channelId, nowNext) ->
                                    val current = mergedXmlNowNext[channelId]
                                    if (!hasProgramData(current) && hasProgramData(nowNext)) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    } else if (current == null) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    }
                                }
                                val coverage = epgCoverageRatio(channels, mergedXmlNowNext)
                                if (coverage >= bestCoverage) {
                                    bestCoverage = coverage
                                }
                                resolved = true
                                System.err.println("[EPG] XMLTV candidate ${index + 1} merged coverage=${(coverage * 100).toInt()}%")
                                if (!largePersistedPlaylist && coverage >= completeEpgCoverageTarget) {
                                    System.err.println("[EPG] XMLTV coverage target reached; skipping remaining EPG candidates")
                                    break
                                }
                            }
                        } else {
                            val exception = attempt.exceptionOrNull()
                            if (exception is kotlinx.coroutines.CancellationException) throw exception
                            if (exception is EpgNotModifiedException) {
                                completedXmlUrls.add(epgUrl)
                                indexedXmlPlaylists.add(candidate.playlistId)
                                System.err.println("[EPG] XMLTV candidate ${index + 1} is unchanged (HTTP 304). Loading existing index...")
                                val existing = runCatching {
                                    epgIndex.loadNowNext(
                                        sourceKey = currentEpgIndexKey(config),
                                        // The full guide is already indexed. Do not hydrate tens
                                        // of thousands of schedules after a cheap HTTP 304.
                                        channelIds = (if (largePersistedPlaylist) candidateChannels.take(16) else candidateChannels)
                                            .map { it.id }.toSet()
                                    )
                                }.getOrDefault(emptyMap())
                                existing.forEach { (channelId, nowNext) ->
                                    val current = mergedXmlNowNext[channelId]
                                    if (!hasProgramData(current) && hasProgramData(nowNext)) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    } else if (current == null) {
                                        mergedXmlNowNext[channelId] = nowNext
                                    }
                                }
                                resolved = true
                            } else {
                                epgFailureMessage = exception?.message
                                System.err.println("[EPG] XMLTV attempt ${index + 1} failed: ${epgFailureMessage}")
                                if (largePersistedPlaylist && (exception is java.util.concurrent.TimeoutException ||
                                        exception is java.io.InterruptedIOException)) break
                            }
                        }
                    }
                    if (resolved) {
                        shortEpgResult?.let { mergedXmlNowNext.putAll(it) } // Short EPG wins for channels it covers
                        resolvedNowNext = mergedXmlNowNext
                        val refreshedAt = System.currentTimeMillis()
                        if (largePersistedPlaylist && epgCandidatesToTry.all {
                                it.url in completedXmlUrls || (it.providerFallback && it.playlistId in indexedXmlPlaylists)
                            }) {
                            epgIndex.markFullRefreshComplete(currentEpgIndexKey(config), refreshedAt)
                        }
                        if (xmltvChanged) {
                            // Persist first. If Live TV became interactive while this
                            // large parse ran, cancellation keeps the previous compact
                            // in-memory/indexed guide instead of retaining a 50k map.
                            persistEpgIndexAll(config, mergedXmlNowNext, refreshedAt)
                        } else {
                            System.err.println("[EPG] Skipping persistEpgIndexAll because XMLTV index is unchanged")
                        }
                        cachedNowNext = mergedXmlNowNext
                        cachedEpgAt = refreshedAt
                        epgUpdated = true
                        System.err.println("[EPG] Final merged EPG coverage=${(epgCoverageRatio(channels, mergedXmlNowNext) * 100).toInt()}% for ${channels.size} channels")
                    }
                }

                val currentCoverage = epgCoverageRatio(channels, resolvedNowNext)
                if (
                    resolved &&
                    currentCoverage < completeEpgCoverageTarget &&
                    !shouldFetchBroadShortEpg &&
                    hasXtreamChannels && !largePersistedPlaylist
                ) {
                    val missingXtreamChannels = channels.filter { channel ->
                        resolveXtreamStreamId(channel) != null && !hasProgramData(resolvedNowNext[channel.id])
                    }
                    if (missingXtreamChannels.isNotEmpty()) {
                        System.err.println(
                            "[EPG] XMLTV coverage ${(currentCoverage * 100).toInt()}%; " +
                                "backfilling ${missingXtreamChannels.size} missing Xtream channels"
                        )
                        val shortEpgAttempt = runCatching {
                            fetchXtreamShortEpgForActiveProviders(config, missingXtreamChannels, onProgress)
                        }
                        if (shortEpgAttempt.isSuccess) {
                            val parsed = shortEpgAttempt.getOrNull()
                            if (parsed != null && hasAnyProgramData(parsed)) {
                                val merged = ConcurrentHashMap(resolvedNowNext)
                                merged.putAll(parsed)
                                resolvedNowNext = merged
                                cachedNowNext = ConcurrentHashMap(merged)
                                cachedEpgAt = System.currentTimeMillis()
                                persistEpgIndexChannels(config, parsed, cachedEpgAt)
                                epgUpdated = true
                                val backfilledCoverage = epgCoverageRatio(channels, merged)
                                System.err.println(
                                    "[EPG] Missing-channel Xtream backfill added ${parsed.size} channels; " +
                                        "coverage=${(backfilledCoverage * 100).toInt()}%"
                                )
                            }
                        } else {
                            epgFailureMessage = shortEpgAttempt.exceptionOrNull()?.message
                            System.err.println("[EPG] Missing-channel Xtream backfill failed: $epgFailureMessage")
                        }
                    }
                }

                if (!resolved && !shouldFetchBroadShortEpg && hasXtreamChannels &&
                    channels.size <= startupShortEpgChannelLimit) {
                    System.err.println("[EPG] XMLTV did not resolve; falling back to full Xtream guide API")
                    val fullEpgAttempt = runCatching {
                        fetchXtreamFullEpgForActiveProviders(config, channels, onProgress)
                    }
                    if (fullEpgAttempt.isSuccess) {
                        val parsed = fullEpgAttempt.getOrNull()
                        if (parsed != null && hasAnyProgramData(parsed)) {
                            cachedNowNext.putAll(parsed)
                            resolvedNowNext = cachedNowNext
                            cachedEpgAt = System.currentTimeMillis()
                            persistEpgIndexChannels(config, parsed, cachedEpgAt)
                            epgUpdated = true
                            resolved = true
                            System.err.println("[EPG] Full Xtream fallback SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                        }
                    } else {
                        epgFailureMessage = fullEpgAttempt.exceptionOrNull()?.message
                        System.err.println("[EPG] Full Xtream fallback failed: $epgFailureMessage")
                    }
                    if (!resolved) {
                        System.err.println("[EPG] Full Xtream fallback did not resolve; falling back to broad Xtream short EPG")
                        val shortEpgAttempt = runCatching {
                            fetchXtreamShortEpgForActiveProviders(config, channels, onProgress)
                        }
                        if (shortEpgAttempt.isSuccess) {
                            val parsed = shortEpgAttempt.getOrNull()
                            if (parsed != null && hasAnyProgramData(parsed)) {
                                cachedNowNext.putAll(parsed)
                                resolvedNowNext = cachedNowNext
                                cachedEpgAt = System.currentTimeMillis()
                                persistEpgIndexChannels(config, parsed, cachedEpgAt)
                                epgUpdated = true
                                resolved = true
                                System.err.println("[EPG] Broad Xtream fallback SUCCESS: ${parsed.size} fresh, ${cachedNowNext.size} total cached")
                            }
                        } else {
                            epgFailureMessage = shortEpgAttempt.exceptionOrNull()?.message
                            System.err.println("[EPG] Broad Xtream fallback failed: $epgFailureMessage")
                        }
                    }
                }

                if (!resolved) {
                    if (cachedFallbackNowNext.isNotEmpty()) {
                        resolvedNowNext = cachedFallbackNowNext
                        System.err.println("[EPG] Keeping stale cached EPG fallback after refresh failure (${cachedFallbackNowNext.size} channels)")
                    } else {
                        // Throttle repeated failures to avoid refetching every open.
                        cachedNowNext = ConcurrentHashMap()
                        cachedEpgAt = System.currentTimeMillis()
                        epgUpdated = true
                    }
                }
                resolvedNowNext
            }

            // Stalker channels never had an epgCandidate/Xtream path above, so merge
            // their now/next data in here — additive only, never overwrites data the
            // M3U/Xtream/XMLTV resolution above already found for a channel (C1/C4/C6).
            val stalkerChannelsInSnapshot = if (stalkerPortals.isNotEmpty()) {
                channels.filter { StalkerPortalSupport.portalIdFromChannelId(it.id) != null }
            } else {
                emptyList()
            }
            val stalkerNowNextHybrid = if (stalkerChannelsInSnapshot.isNotEmpty()) {
                if (allowNetworkEpgFetch) {
                    val fresh = runCatching {
                        fetchStalkerEpgForActivePortals(cachedStalkerApis, stalkerChannelsInSnapshot)
                    }.getOrDefault(emptyMap())
                    if (fresh.isNotEmpty()) {
                        persistEpgIndexChannels(config, fresh, System.currentTimeMillis())
                        epgUpdated = true
                    }
                    fresh
                } else {
                    runCatching {
                        epgIndex.loadNowNext(
                            currentEpgIndexKey(config),
                            stalkerChannelsInSnapshot.asSequence().map { it.id }.toSet()
                        )
                    }.getOrDefault(emptyMap())
                }
            } else {
                emptyMap()
            }
            val finalNowNext = if (stalkerNowNextHybrid.isEmpty()) {
                nowNext
            } else {
                HashMap(nowNext).apply {
                    stalkerNowNextHybrid.forEach { (channelId, value) ->
                        if (!hasProgramData(this[channelId])) this[channelId] = value
                    }
                }
            }

            val epgFailure = epgFailureMessage
            val epgWarning = if (epgCandidates.isNotEmpty() && nowNext.isEmpty()) {
                if (!epgFailure.isNullOrBlank()) {
                    "EPG unavailable right now (${epgFailure.take(120)})."
                } else {
                    "EPG unavailable for this source right now."
                }
            } else null

            val favoriteGroups = observeFavoriteGroups().first()
            val favoriteChannels = observeFavoriteChannels().first()
            val isLargePagedSnapshot = channels.size > LargeIptvListChannelCount
            val retainedChannels = if (isLargePagedSnapshot) {
                channels.take(LargeListMemoryChannelLimit)
            } else {
                channels
            }
            val retainedNowNext = if (isLargePagedSnapshot) {
                retainGuideForMemory(
                    channels = retainedChannels,
                    nowNext = finalNowNext,
                    priorityChannelIds = favoriteChannels
                )
            } else {
                finalNowNext
            }
            val grouped = if (!isLargePagedSnapshot && cachedChannels === channels && cachedGroupedChannels.isNotEmpty()) {
                cachedGroupedChannels
            } else {
                buildGroupedChannels(retainedChannels)
            }

            if (isLargePagedSnapshot) {
                cachedChannels = retainedChannels
                cachedGroupedChannels = grouped
                cachedNowNext = ConcurrentHashMap(retainedNowNext)
                System.err.println(
                    "[IPTV-Memory] retained channels=${retainedChannels.size}/${channels.size} " +
                        "guide=${retainedNowNext.size}/${finalNowNext.size}; full snapshots remain indexed"
                )
            }

            val loadedAtMillis = if (cachedPlaylistAt > 0L) cachedPlaylistAt else now
            val loadedAtInstant = Instant.ofEpochMilli(loadedAtMillis)

            val hiddenGroups = observeHiddenGroups().first()
            val groupOrder = observeGroupOrder().first()

            IptvSnapshot(
                channels = retainedChannels,
                grouped = grouped,
                nowNext = retainedNowNext,
                favoriteGroups = favoriteGroups,
                favoriteChannels = favoriteChannels,
                hiddenGroups = hiddenGroups,
                groupOrder = groupOrder,
                epgWarning = epgWarning,
                loadedAt = loadedAtInstant
            ).also {
                if (playlistChanged || forceEpgReload || epgUpdated) {
                    writeCache(
                        config = config,
                        channels = channels,
                        nowNext = retainedNowNext,
                        loadedAtMs = System.currentTimeMillis(),
                        // A changed playlist was already committed as soon as it
                        // became available. This final pass only persists guide
                        // metadata and must not rewrite all 50k channel rows again.
                        persistChannels = false
                    )
                }
                onProgress(IptvLoadProgress(context.getString(R.string.iptv_loaded_channels, channels.size), 100))
            }
            }
        }
    }

    /**
     * Cache-only warmup used at app start.
     * Never performs network calls, so startup cannot get blocked by heavy playlists.
     */
    suspend fun warmupFromCacheOnly() {
        withContext(Dispatchers.IO) {
            loadMutex.withLock {
                val config = observeConfig().first()
                val profileId = profileManager.getProfileIdSync()
                ensureCacheOwnership(profileId, config)
                if (!hasAnyConfiguredSource(config)) return@withLock
                if (cachedChannels.isNotEmpty()) return@withLock

                val cached = readChannelCache(config) ?: return@withLock
                cachedChannels = cached.channels
                cachedGroupedChannels = buildGroupedChannels(cached.channels)
                cachedPlaylistAt = cached.loadedAtEpochMs
                val cachedChannelsAreLarge = isLargePersistedChannelSnapshot(config, cached.channels.size)
                val guideCache = if (!cachedChannelsAreLarge) {
                    readCache(config)
                        ?.takeIf { it.nowNext.isNotEmpty() && hasAnyProgramData(it.nowNext) }
                } else {
                    null
                }
                if (guideCache != null) {
                    cachedNowNext = ConcurrentHashMap(guideCache.nowNext)
                    cachedEpgAt = guideCache.loadedAtEpochMs
                    persistEpgIndexChannels(config, guideCache.nowNext, cachedEpgAt)
                    val indexedChannels = countIndexedGuideChannels()
                    val indexedPrograms = countIndexedGuidePrograms()
                    System.err.println(
                        "[EPG] Warm cache loaded ${guideCache.nowNext.size} guide channels from disk; " +
                            "index=$indexedChannels channels/$indexedPrograms programs"
                    )
                } else {
                    cachedNowNext = ConcurrentHashMap()
                    cachedEpgAt = cached.loadedAtEpochMs
                    if (cachedChannelsAreLarge) {
                        System.err.println(
                            "[EPG-Memory] warm cache skipped full guide hydration; window=${cached.channels.size}"
                        )
                    }
                }
            }
        }
    }

    /**
     * Returns the latest snapshot from memory/disk cache only.
     * Never performs network calls.
     */
    suspend fun getCachedSnapshotOrNull(): IptvSnapshot? {
        return withContext(Dispatchers.IO) {
            loadMutex.withLock {
                val config = observeConfig().first()
                val profileId = profileManager.getProfileIdSync()
                ensureCacheOwnership(profileId, config)

                if (!hasAnyConfiguredSource(config)) {
                    return@withLock IptvSnapshot(
                        channels = emptyList(),
                        grouped = emptyMap(),
                        nowNext = emptyMap(),
                        favoriteGroups = observeFavoriteGroups().first(),
                        favoriteChannels = observeFavoriteChannels().first(),
                        loadedAt = Instant.now()
                    )
                }

                if (cachedChannels.isEmpty()) {
                    val cached = readChannelCache(config) ?: return@withLock null
                    val cachedChannelsAreLarge = isLargePersistedChannelSnapshot(config, cached.channels.size)
                    val guideCache = if (!cachedChannelsAreLarge) {
                        readCache(config)
                    } else {
                        null
                    }
                    cachedChannels = cached.channels
                    cachedGroupedChannels = buildGroupedChannels(cached.channels)
                    cachedNowNext = ConcurrentHashMap(guideCache?.nowNext.orEmpty())
                    cachedPlaylistAt = cached.loadedAtEpochMs
                    cachedEpgAt = guideCache?.loadedAtEpochMs ?: cached.loadedAtEpochMs
                    if (cachedChannelsAreLarge) {
                        System.err.println(
                            "[EPG-Memory] cached snapshot skipped full guide hydration; window=${cached.channels.size}"
                        )
                    }
                }

                val favoriteGroups = observeFavoriteGroups().first()
                val favoriteChannels = observeFavoriteChannels().first()
                val hiddenGroups = observeHiddenGroups().first()
                val groupOrder = observeGroupOrder().first()
                val grouped = cachedGroupedChannels.ifEmpty {
                    buildGroupedChannels(cachedChannels).also { cachedGroupedChannels = it }
                }
                val loadedAtMillis = if (cachedPlaylistAt > 0L) cachedPlaylistAt else System.currentTimeMillis()

                IptvSnapshot(
                    channels = cachedChannels,
                    grouped = grouped,
                    nowNext = cachedNowNext,
                    favoriteGroups = favoriteGroups,
                    favoriteChannels = favoriteChannels,
                    hiddenGroups = hiddenGroups,
                    groupOrder = groupOrder,
                    epgWarning = null,
                    loadedAt = Instant.ofEpochMilli(loadedAtMillis)
                )
            }
        }
    }

    fun isSnapshotStale(snapshot: IptvSnapshot): Boolean {
        val ageMs = System.currentTimeMillis() - snapshot.loadedAt.toEpochMilli()
        return ageMs > staleAfterMs
    }

    /** Age of cached EPG data in milliseconds, or Long.MAX_VALUE if no cache. */
    fun cachedEpgAgeMs(): Long {
        val at = cachedEpgAt
        return if (at <= 0L) Long.MAX_VALUE else System.currentTimeMillis() - at
    }

    /**
     * Non-blocking in-memory snapshot read. Returns null if in-memory cache is empty.
     * Unlike [getCachedSnapshotOrNull], this does NOT acquire [loadMutex] and does NOT
     * fall back to disk — it only reads volatile in-memory fields.
     * Use this when you need a fast, contention-free read (e.g., on navigation).
     */
    suspend fun getPagedStartupSnapshotOrNull(): IptvSnapshot? = withContext(Dispatchers.IO) {
        val config = observeConfig().first()
        val key = epgIndexKey(profileManager.getProfileIdSync(), config)
        // Never read another profile's active store while ownership is changing.
        if (key != currentEpgIndexKey) return@withContext null
        val channels = channelStore.loadStartupChannels(key, LargeIptvListChannelCount, LargeListMemoryChannelLimit)
        if (channels.isEmpty()) return@withContext null
        IptvSnapshot(
            channels = channels, grouped = buildGroupedChannels(channels),
            favoriteChannels = observeFavoriteChannels().first(),
            favoriteGroups = observeFavoriteGroups().first(),
            hiddenGroups = observeHiddenGroups().first(),
            groupOrder = observeGroupOrder().first(), sortOrder = config.sortOrder,
            loadedAt = Instant.ofEpochMilli(channelStore.updatedAtMs(key)),
        )
    }

    suspend fun getMemoryCachedSnapshot(): IptvSnapshot? {
        if (cacheOwnerProfileId != profileManager.getProfileIdSync()) return null
        val channels = cachedChannels
        if (channels.isEmpty()) return null
        val favoriteGroups = observeFavoriteGroups().first()
        val favoriteChannels = observeFavoriteChannels().first()
        val hiddenGroups = observeHiddenGroups().first()
        val groupOrder = observeGroupOrder().first()
        val grouped = cachedGroupedChannels.ifEmpty {
            buildGroupedChannels(channels).also { cachedGroupedChannels = it }
        }
        val loadedAtMillis = if (cachedPlaylistAt > 0L) cachedPlaylistAt else System.currentTimeMillis()
        return IptvSnapshot(
            channels = channels,
            grouped = grouped,
            nowNext = cachedNowNext,
            favoriteGroups = favoriteGroups,
            favoriteChannels = favoriteChannels,
            hiddenGroups = hiddenGroups,
            groupOrder = groupOrder,
            epgWarning = null,
            loadedAt = Instant.ofEpochMilli(loadedAtMillis)
        )
    }

    /**
     * Re-derive now/next from cached EPG program data without any network call.
     * Programs shift: if "now" has ended, "next" becomes "now", etc.
     * Updates cachedNowNext in place so subsequent reads via getCachedSnapshotOrNull()
     * return the re-derived data.
     * Returns updated nowNext map for the given channel IDs, or null if no cached data.
     */
    fun reDeriveCachedNowNext(channelIds: Set<String>): Map<String, IptvNowNext>? {
        val cached = cachedNowNext
        val nowMs = System.currentTimeMillis()
        val cachedChannelMetadata = cachedChannelLookup()
        val missingMetadataIds = channelIds.filter { it !in cachedChannelMetadata }
        val indexedMetadata = if (missingMetadataIds.isNotEmpty() && currentEpgIndexKey.isNotBlank()) {
            try {
                channelStore.getByIds(currentEpgIndexKey, missingMetadataIds).associateBy { it.id }
            } catch (error: Exception) {
                if (error is kotlinx.coroutines.CancellationException) throw error
                System.err.println("[EPG-Index] Failed to read channel metadata: ${error.javaClass.simpleName}")
                emptyMap()
            }
        } else emptyMap()
        val channelsById = if (indexedMetadata.isEmpty()) cachedChannelMetadata else cachedChannelMetadata + indexedMetadata
        val missingIndexedIds = channelIds.filterTo(LinkedHashSet()) { channelId ->
            val guide = cached[channelId]
            !hasProgramData(guide) || shouldLoadIndexedGuide(guide, channelsById[channelId], nowMs)
        }
        val indexKey = currentEpgIndexKey
        if (missingIndexedIds.isNotEmpty() && indexKey.isNotBlank()) {
            val indexed = runCatching {
                epgIndex.loadNowNext(indexKey, missingIndexedIds)
            }.onFailure { error ->
                System.err.println("[EPG-Index] Failed to read guide index: ${error.message}")
            }.getOrDefault(emptyMap())
            if (indexed.isNotEmpty()) {
                indexed.forEach { (channelId, indexedGuide) ->
                    cachedNowNext[channelId] = mergeCachedGuideSlice(cachedNowNext[channelId], indexedGuide)
                }
            }
        }
        if (cachedNowNext.isEmpty()) return null

        val result = mutableMapOf<String, IptvNowNext>()
        for (channelId in channelIds) {
            val existing = cachedNowNext[channelId] ?: continue
            val recentCutoff = recentCutoffForChannel(channelsById[channelId], nowMs)
            // Collect all known programs from the cached entry efficiently
            val allPrograms = java.util.ArrayList<IptvProgram>(
                (if (existing.now != null) 1 else 0) +
                    (if (existing.next != null) 1 else 0) +
                    (if (existing.later != null) 1 else 0) +
                    existing.upcoming.size +
                    existing.recent.size
            )
            existing.now?.let { allPrograms.add(it) }
            existing.next?.let { allPrograms.add(it) }
            existing.later?.let { allPrograms.add(it) }
            allPrograms.addAll(existing.upcoming)
            allPrograms.addAll(existing.recent)
            allPrograms.sortBy { it.startUtcMillis }

            var now: IptvProgram? = null
            var next: IptvProgram? = null
            var later: IptvProgram? = null
            val upcoming = java.util.ArrayList<IptvProgram>(epgUpcomingProgramLimit)
            val recent = java.util.ArrayList<IptvProgram>()

            if (allPrograms.isNotEmpty()) {
                var startIndex = allPrograms.binarySearch { it.startUtcMillis.compareTo(recentCutoff) }
                if (startIndex < 0) {
                    startIndex = -(startIndex + 1)
                }

                // Walk backward to include programs starting before recentCutoff but ending after
                while (startIndex > 0 && allPrograms[startIndex - 1].endUtcMillis > recentCutoff) {
                    startIndex--
                }

                for (i in startIndex until allPrograms.size) {
                    val p = allPrograms[i]
                    when {
                        p.endUtcMillis <= nowMs && p.endUtcMillis > recentCutoff -> {
                            addRecentCandidate(recent, p, recentProgramLimitForChannel(channelsById[channelId]))
                        }
                        p.isLive(nowMs) -> now = p
                        p.startUtcMillis > nowMs && next == null -> next = p
                        p.startUtcMillis > nowMs && later == null -> later = p
                        p.startUtcMillis > nowMs -> {
                            upcoming.add(p)
                            if (upcoming.size >= epgUpcomingProgramLimit) {
                                break // We have enough upcoming programs
                            }
                        }
                    }
                }
            }

            result[channelId] = IptvNowNext(
                now = now,
                next = next,
                later = later,
                upcoming = upcoming,
                recent = recent
            )
        }
        if (result.isEmpty()) return null

        // Write back re-derived entries into cachedNowNext (in-place, no copy)
        cachedNowNext.putAll(result)

        return result
    }

    fun indexedGuideChannelCount(): Int = countIndexedGuideChannels()

    /** Read complete archive only for the opened channel, not every row on a large playlist. */
    fun indexedCatchupGuide(channelId: String): Map<String, IptvNowNext> {
        val key = currentEpgIndexKey
        if (key.isBlank() || channelId.isBlank()) return emptyMap()
        val indexed = try {
            epgIndex.loadNowNext(
                key, setOf(channelId), pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS,
                recentProgramLimit = IptvGuideHistory.MAX_PROGRAMS,
            )
        } catch (error: Exception) {
            if (error is kotlinx.coroutines.CancellationException) throw error
            System.err.println("[EPG-Index] Failed to read archive: ${error.javaClass.simpleName}")
            emptyMap()
        }
        return indexed.also { guide ->
            guide.forEach { (id, value) -> cachedNowNext[id] = mergeCachedGuideSlice(cachedNowNext[id], value) }
        }
    }

    fun indexedGuideProgramCount(): Int = countIndexedGuidePrograms()

    // ── Paged channel access (Stage 2) ─────────────────────────────────────────
    // Backed by the SQLite channel store so the Live TV UI can page the channel
    // list per category/window instead of holding all (50k+) channels in memory.
    // Keyed by the active index key (set during loadSnapshot / cache ownership).

    fun pagedChannelsReady(): Boolean =
        currentEpgIndexKey.isNotBlank() && runCatching { channelStore.count(currentEpgIndexKey) }.getOrDefault(0) > 0

    fun pagedChannelCount(groupTitle: String?): Int =
        runCatching { channelStore.countForGroup(currentEpgIndexKey, groupTitle) }.getOrDefault(0)

    fun pagedChannelWindow(groupTitle: String?, offset: Int, limit: Int): List<IptvChannel> =
        runCatching { channelStore.windowForGroup(currentEpgIndexKey, groupTitle, offset, limit) }.getOrDefault(emptyList())

    fun pagedChannelCount(playlistId: String?, groupTitle: String?): Int =
        runCatching { channelStore.countForPlaylistGroup(currentEpgIndexKey, playlistId, groupTitle) }.getOrDefault(0)

    fun pagedChannelWindow(playlistId: String?, groupTitle: String?, offset: Int, limit: Int, excludedGroups: Set<String> = emptySet()): List<IptvChannel> =
        runCatching { channelStore.windowForPlaylistGroup(currentEpgIndexKey, playlistId, groupTitle, offset, limit, excludedGroups) }.getOrDefault(emptyList())

    /**
     * Number of channels in the paged store.
     *
     * Cached per store key. The uncached version ran a `COUNT(*)` over a 90MB
     * SQLite database, and callers ask this on the main thread on every focus
     * change (via TvViewModel.isActiveLargeIptvList). Under sustained d-pad
     * navigation those queries queued on the connection pool until the main
     * thread blocked past the ANR threshold and Android killed the app —
     * captured on device: "Waited 10010ms for KeyEvent", main thread parked in
     * SQLiteConnectionPool.waitForConnection under IptvChannelStore.count.
     *
     * The count only changes when the store is rewritten, so [invalidatePagedChannelStoreCount]
     * clears it there.
     */
    fun pagedChannelStoreCount(): Int {
        val key = currentEpgIndexKey
        cachedPagedChannelStoreCount?.let { (cachedKey, cachedCount) ->
            if (cachedKey == key) return cachedCount
        }
        val count = runCatching { channelStore.count(key) }.getOrDefault(0)
        cachedPagedChannelStoreCount = key to count
        return count
    }

    /** Stable revision for the active paged channel snapshot. */
    fun pagedChannelStoreUpdatedAtMs(): Long =
        runCatching { channelStore.updatedAtMs(currentEpgIndexKey) }.getOrDefault(0L)

    /** Drop the cached channel count after the paged store changes. */
    fun invalidatePagedChannelStoreCount() {
        cachedPagedChannelStoreCount = null
    }

    fun pagedChannelGroupCounts(): List<Pair<String, Int>> =
        runCatching { channelStore.groupCounts(currentEpgIndexKey) }.getOrDefault(emptyList())

    fun pagedPlaylistGroupCounts(): List<Triple<String, String, Int>> =
        runCatching { channelStore.playlistGroupCounts(currentEpgIndexKey) }.getOrDefault(emptyList())

    fun visitStoredChannelLabels(playlistId: String?, visitor: (String, String, String) -> Unit) =
        channelStore.visitLabels(currentEpgIndexKey, playlistId, visitor)

    fun cachedGuideChannelIds(startMs: Long, endMs: Long): Set<String> =
        epgIndex.channelIdsInWindow(currentEpgIndexKey, startMs, endMs)

    fun visitCachedGuideWindow(channelIds: Set<String>, startMs: Long, endMs: Long,
        visitor: (String, IptvProgram) -> Unit) =
        epgIndex.visitWindow(currentEpgIndexKey, channelIds, startMs, endMs, visitor)

    fun pagedChannelsByIds(ids: Collection<String>): List<IptvChannel> =
        runCatching { channelStore.getByIds(currentEpgIndexKey, ids) }.getOrDefault(emptyList())

    fun pagedChannelIndexOf(groupTitle: String?, channelId: String): Int =
        runCatching { channelStore.indexOfId(currentEpgIndexKey, groupTitle, channelId) }.getOrDefault(-1)

    fun pagedChannelIndexOf(playlistId: String?, groupTitle: String?, channelId: String): Int =
        runCatching {
            channelStore.indexOfId(currentEpgIndexKey, playlistId, groupTitle, channelId)
        }.getOrDefault(-1)

    fun pagedSearchChannels(query: String, limit: Int): List<IptvChannel> =
        runCatching { channelStore.search(currentEpgIndexKey, query, limit) }.getOrDefault(emptyList())

    fun indexedGuideWindow(
        channelIds: Set<String>,
        startMs: Long,
        endMs: Long
    ): Map<String, IptvNowNext> {
        if (channelIds.isEmpty() || startMs >= endMs) return emptyMap()
        val indexKey = currentEpgIndexKey
        if (indexKey.isBlank()) return emptyMap()
        val nowMs = System.currentTimeMillis()
        val totalStartedAt = android.os.SystemClock.elapsedRealtime()
        var databaseDurationMs = 0L
        return runCatching {
            buildMap {
                val databaseStartedAt = android.os.SystemClock.elapsedRealtime()
                val window = epgIndex.loadWindow(indexKey, channelIds, startMs, endMs)
                databaseDurationMs = android.os.SystemClock.elapsedRealtime() - databaseStartedAt
                window
                    .forEach { (channelId, programs) ->
                    if (programs.isEmpty()) return@forEach
                    val sorted = programs
                        .asSequence()
                        .filter { it.endUtcMillis > it.startUtcMillis }
                        .distinctBy { "${it.startUtcMillis}|${it.endUtcMillis}|${it.title}" }
                        .sortedBy { it.startUtcMillis }
                        .toList()
                    if (sorted.isEmpty()) return@forEach
                    val now = sorted.lastOrNull { it.isLive(nowMs) }
                    val future = sorted.filter { it.startUtcMillis > nowMs }
                    val recent = sorted.filter { it.endUtcMillis <= nowMs }
                    put(
                        channelId,
                        IptvNowNext(
                            now = now,
                            next = future.getOrNull(0),
                            later = future.getOrNull(1),
                            upcoming = future.take(96),
                            recent = recent.takeLast(IptvGuideHistory.MAX_PROGRAMS)
                        )
                    )
                }
            }
        }.onFailure { error ->
            System.err.println("[EPG-Index] visible window read failed: ${error.message}")
        }.getOrDefault(emptyMap()).also { result ->
            System.err.println(
                "[EPG-IndexQuery] channels=${channelIds.size} matched=${result.size} " +
                    "db=${databaseDurationMs}ms total=${android.os.SystemClock.elapsedRealtime() - totalStartedAt}ms"
            )
        }
    }

    /**
     * Refreshes short Xtream EPG data for the specified channel IDs.
     *
     * Updates the repository's in-memory `cachedNowNext` entries for channels that have Xtream stream identifiers.
     *
     * @return A map of channel ID to `IptvNowNext` containing the updated EPG entries for those channels, or `null` if Xtream credentials are not available or no EPG data was retrieved.
     */
    suspend fun refreshEpgForChannels(
        channelIds: Set<String>,
        maxChannels: Int = startupShortEpgChannelLimit,
        preferFullCatchupHistory: Boolean = false
    ): Map<String, IptvNowNext>? {
        if (channelIds.isEmpty()) return null
        return withContext(Dispatchers.IO) {
            val config = observeConfig().first()
            val requested = if (maxChannels > 0) {
                channelIds.take(maxChannels).toHashSet()
            } else {
                channelIds
            }
            val channels = channelsForEpgRefresh(requested)
            System.err.println("[EPG-Refresh] refreshEpgForChannels: requested=${requested.size} resolved=${channels.size}")
            if (channels.isEmpty()) return@withContext null

            val activePlaylistById = activePlaylists(config).associateBy { it.id }
            val fallbackCreds = resolveXtreamCredentials(config)
            val channelsByCredentials = LinkedHashMap<XtreamCredentials, MutableList<IptvChannel>>()
            for (channel in channels) {
                val playlistId = channel.id.substringBefore(':', missingDelimiterValue = "")
                    .takeIf { it.isNotBlank() }
                val playlistCreds = playlistId
                    ?.let { activePlaylistById[it] }
                    ?.let { resolveXtreamCredentials(it) }
                val creds = playlistCreds ?: fallbackCreds ?: continue
                if (resolveXtreamStreamId(channel) == null) continue
                channelsByCredentials.getOrPut(creds) { mutableListOf() }.add(channel)
            }
            val allChannelsByCredentials = groupXtreamChannelsByCredentials(
                config,
                (cachedChannels + channels).distinctBy { it.id }
            )
            val mergedNowNext = ConcurrentHashMap<String, IptvNowNext>()
            var totalListings = 0
            var totalErrors = 0

            channelsByCredentials.forEach { (creds, providerChannels) ->
                val providerCooldownKey = "${creds.baseUrl}|${creds.username}"
                val nowMs = System.currentTimeMillis()
                val useProviderCooldown = providerChannels.size >= startupShortEpgChannelLimit
                val cooldownUntil = if (useProviderCooldown) emptyShortEpgCooldownUntil[providerCooldownKey] ?: 0L else 0L
                if (cooldownUntil > nowMs && !preferFullCatchupHistory) {
                    System.err.println(
                        "[EPG-Refresh] Skipping empty short EPG provider ${creds.baseUrl} " +
                            "for ${(cooldownUntil - nowMs) / 1000}s"
                    )
                    return@forEach
                }

                // Build lookups for this provider's channels only. Multi-playlist
                // setups may use different Xtream credentials per playlist.
                //
                // Xtream EPG responses are often keyed by epg_channel_id rather
                // than the exact stream_id. When a small visible refresh asks
                // for one variant first (for example NPO 1 4K), fan that guide
                // data out to same-provider channels sharing the same EPG id
                // (for example NPO 1 HD/SD) without collapsing channel rows or
                // mixing playback sources.
                val providerLookupChannels = allChannelsByCredentials[creds]
                    ?.takeIf { it.isNotEmpty() }
                    ?.let { allProviderChannels ->
                        scopedProviderGuideLookupChannels(providerChannels, allProviderChannels)
                    }
                    ?: providerChannels
                val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
                val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
                for (ch in providerLookupChannels) {
                    ch.epgId?.let { eid ->
                        addChannelIdToLookup(epgIdToChannelIds, eid, ch.id)
                    }
                    ch.tvgName?.let { tvg ->
                        addChannelIdToLookup(epgIdToChannelIds, tvg, ch.id)
                    }
                    ch.variantKey?.let { key ->
                        addChannelIdToLookup(epgIdToChannelIds, key, ch.id)
                    }
                    resolveXtreamStreamId(ch)?.let { sid ->
                        streamIdToChannelIds.getOrPut(sid.toString()) { mutableListOf() }.add(ch.id)
                    }
                }

                System.err.println(
                    "[EPG-Refresh] Fetching short EPG for ${providerChannels.size} " +
                        "requested channels from ${creds.baseUrl}; sample=${describeEpgChannels(providerChannels)}"
                )

                val streamIds = providerChannels.mapNotNull { resolveXtreamStreamId(it) }
                var errors = 0
                val shouldUseFullCatchupHistory = preferFullCatchupHistory &&
                    providerChannels.size <= fullCatchupHistoryChannelLimit
                val fullListings = if (shouldUseFullCatchupHistory) {
                    fetchXtreamFullEpgListingsAsync(
                        creds = creds,
                        streamIds = streamIds,
                        timeoutMillis = xtreamFullCatchupEpgTimeout(streamIds.size)
                    ) { _, hadError ->
                        if (hadError) errors++
                    }
                } else {
                    emptyList()
                }
                val allListings = fullListings.ifEmpty {
                    fetchXtreamEpgListingsAsync(
                        creds = creds,
                        streamIds = streamIds,
                        timeoutMillis = xtreamShortEpgTimeout(streamIds.size),
                        listingLimit = if (providerChannels.size <= 128) {
                            xtreamVisibleShortEpgLimit
                        } else {
                            xtreamShortEpgLimit
                        },
                        allowUnboundedFallback = preferFullCatchupHistory
                    ) { _, hadError ->
                        if (hadError) errors++
                    }
                }
                totalListings += allListings.size
                totalErrors += errors
                System.err.println(
                    "[EPG-Refresh] Provider done: ${allListings.size} listings, $errors errors " +
                        "fullCatchup=$shouldUseFullCatchupHistory"
                )

                if (allListings.isEmpty()) {
                    if (errors == 0 && !preferFullCatchupHistory && useProviderCooldown) {
                        emptyShortEpgCooldownUntil[providerCooldownKey] =
                            System.currentTimeMillis() + 10 * 60_000L
                    }
                    return@forEach
                }
                emptyShortEpgCooldownUntil.remove(providerCooldownKey)

                val freshNowNext = buildNowNextFromXtreamListings(
                    creds = creds,
                    listings = allListings,
                    epgIdToChannelIds = epgIdToChannelIds,
                    streamIdToChannelIds = streamIdToChannelIds,
                    channelsById = providerLookupChannels.associateBy { it.id },
                    forceCatchupHistory = preferFullCatchupHistory
                )
                if (freshNowNext.isNotEmpty()) {
                    mergedNowNext.putAll(freshNowNext)
                }
            }

            // Stalker channels never have Xtream credentials, so the loop above never
            // touches them - without this they'd silently fall through to the XMLTV
            // fallback below (which also can't help them) and this on-demand refresh,
            // used all over the visible guide/grid, would return nothing for them.
            val stalkerRequestedChannels = channels.filter {
                StalkerPortalSupport.portalIdFromChannelId(it.id) != null
            }
            System.err.println(
                "[EPG-Refresh] refreshEpgForChannels: batch=${channels.size} " +
                    "stalkerChannels=${stalkerRequestedChannels.size} cachedStalkerApis=${cachedStalkerApis.keys}"
            )
            if (stalkerRequestedChannels.isNotEmpty()) {
                val stalkerFresh = runCatching {
                    fetchStalkerEpgForActivePortals(cachedStalkerApis, stalkerRequestedChannels)
                }.getOrElse { error ->
                    System.err.println("[EPG-Refresh] fetchStalkerEpgForActivePortals threw: ${error.message}")
                    emptyMap()
                }
                if (stalkerFresh.isNotEmpty()) {
                    mergedNowNext.putAll(stalkerFresh)
                }
            }

            val missingXmlChannels = channels.filter { channel ->
                !hasProgramData(mergedNowNext[channel.id])
            }
            val hasXtreamRequestedChannels = channelsByCredentials.isNotEmpty()
            var xmlFallback: Map<String, IptvNowNext> = emptyMap()
            var isXmlCached = false
            if (missingXmlChannels.isNotEmpty() && !preferFullCatchupHistory && !hasXtreamRequestedChannels) {
                val result = fetchVisibleXmlEpgForChannels(config, missingXmlChannels)
                xmlFallback = result.first
                isXmlCached = result.second
                if (xmlFallback.isNotEmpty()) {
                    mergedNowNext.putAll(xmlFallback)
                    System.err.println(
                        "[EPG-Refresh] XMLTV visible fallback added ${xmlFallback.size} channels"
                    )
                }
            } else if (missingXmlChannels.isNotEmpty() && hasXtreamRequestedChannels) {
                System.err.println(
                    "[EPG-Refresh] Skipping XMLTV visible fallback for Xtream channels; " +
                        "missing=${missingXmlChannels.size}"
                )
            }
            if (mergedNowNext.isEmpty()) return@withContext null

            // Merge into cache (in-place, no copy). Visible short EPG refreshes
            // only carry now/next/later slices, so replacing the whole entry
            // would wipe the richer catch-up history loaded for archive rows.
            val mergedForCache = mergedNowNext.mapValues { (channelId, fresh) ->
                mergeCachedGuideSlice(cachedNowNext[channelId], fresh)
            }
            cachedNowNext.putAll(mergedForCache)
            cachedEpgAt = System.currentTimeMillis()

            val toPersist = if (isXmlCached && xmlFallback.isNotEmpty()) {
                mergedForCache.filterKeys { it !in xmlFallback.keys }
            } else {
                mergedForCache
            }
            if (toPersist.isNotEmpty()) {
                persistEpgIndexChannels(config, toPersist, cachedEpgAt)
            }

            System.err.println(
                "[EPG-Refresh] Updated ${mergedForCache.size} channels in cache " +
                    "from $totalListings listings, $totalErrors errors"
            )
            mergedForCache
        }
    }

    private fun describeEpgChannels(channels: List<IptvChannel>, limit: Int = 4): String {
        if (channels.isEmpty()) return "[]"
        return channels
            .asSequence()
            .take(limit)
            .joinToString(prefix = "[", postfix = if (channels.size > limit) ", ...]" else "]") { channel ->
                val streamId = resolveXtreamStreamId(channel)?.toString().orEmpty()
                val epg = channel.epgId.orEmpty().take(32)
                val name = channel.name.replace('\n', ' ').take(36)
                "{id=${channel.id.take(48)}, stream=$streamId, epg=$epg, name=$name}"
            }
    }

    private suspend fun fetchVisibleXmlEpgForChannels(
        config: IptvConfig,
        channels: List<IptvChannel>
    ): Pair<Map<String, IptvNowNext>, Boolean> {
        if (channels.isEmpty()) return Pair(emptyMap(), false)
        val candidates = resolveScopedEpgCandidates(config)
        if (candidates.isEmpty()) return Pair(emptyMap(), false)

        val playlistKey = channels
            .asSequence()
            .map { it.id.substringBefore(':', missingDelimiterValue = "") }
            .filter { it.isNotBlank() }
            .distinct()
            .take(4)
            .joinToString(",")
        val channelKey = channels
            .asSequence()
            .map { it.id }
            .filter { it.isNotBlank() }
            .take(24)
            .joinToString(",")
            .hashCode()
        val cooldownKey = "${currentEpgIndexKey(config)}|$playlistKey|$channelKey|visible_xml"
        val nowMs = System.currentTimeMillis()
        val cooldownUntil = visibleXmlEpgCooldownUntil[cooldownKey] ?: 0L
        if (cooldownUntil > nowMs) {
            System.err.println(
                "[EPG-Refresh] Skipping XMLTV visible fallback for ${(cooldownUntil - nowMs) / 1000}s"
            )
            return Pair(emptyMap(), false)
        }
        visibleXmlEpgCooldownUntil[cooldownKey] = nowMs + 90_000L

        val visibleCandidates = candidates.take(2)
        for ((index, candidate) in visibleCandidates.withIndex()) {
            val candidateChannels = channelsForScopedEpgCandidate(candidate, channels)
            if (candidateChannels.isEmpty()) continue
            System.err.println(
                "[EPG-Refresh] XMLTV visible fallback ${index + 1}/${visibleCandidates.size} " +
                    "for ${candidateChannels.size} channels"
            )
            var isCached = false
            val parsed = runCatching {
                guideRequestBudget.request(candidate.url) {
                    withTimeoutOrNull(12_000L) {
                        val jobContext = currentCoroutineContext()
                        runInterruptible(Dispatchers.IO) {
                            fetchAndParseEpg(candidate.url, candidateChannels) { jobContext.ensureActive() }
                        }
                    }
                } ?: emptyMap()
            }.recover { error ->
                if (error is EpgNotModifiedException) {
                    System.err.println("[EPG-Refresh] XMLTV visible fallback candidate is unchanged (HTTP 304). Loading existing index...")
                    isCached = true
                    runCatching {
                        epgIndex.loadNowNext(
                            sourceKey = currentEpgIndexKey(config),
                            channelIds = candidateChannels.map { it.id }.toSet()
                        )
                    }.getOrDefault(emptyMap())
                } else {
                    throw error
                }
            }.onFailure { error ->
                if (error is kotlinx.coroutines.CancellationException) throw error
                System.err.println("[EPG-Refresh] XMLTV visible fallback failed: ${error.message}")
            }.getOrDefault(emptyMap())

            if (parsed.isNotEmpty() && hasAnyProgramData(parsed)) {
                visibleXmlEpgCooldownUntil[cooldownKey] = System.currentTimeMillis() + 30_000L
                return Pair(parsed, isCached)
            }
        }

        visibleXmlEpgCooldownUntil[cooldownKey] = System.currentTimeMillis() + 5 * 60_000L
        return Pair(emptyMap(), false)
    }

    private fun persistCurrentCacheSnapshot(config: IptvConfig, loadedAtMs: Long = System.currentTimeMillis()) {
        val channels = cachedChannels
        if (channels.isEmpty()) return
        val nowNext = cachedNowNext
        writeCache(
            config = config,
            channels = channels,
            nowNext = nowNext,
            loadedAtMs = loadedAtMs.coerceAtLeast(cachedPlaylistAt.coerceAtLeast(loadedAtMs))
        )
    }

    private fun persistEpgIndexAll(
        config: IptvConfig,
        nowNext: Map<String, IptvNowNext>,
        updatedAtMs: Long = System.currentTimeMillis()
    ) {
        if (!hasAnyProgramData(nowNext)) return
        abortLargeEpgWorkIfInteractive(nowNext.size)
        val startedAt = System.currentTimeMillis()
        runCatching {
            epgIndex.replaceAll(
                sourceKey = currentEpgIndexKey(config),
                nowNext = nowNext,
                updatedAtMs = updatedAtMs,
                shouldAbort = liveTvInteractive::get
            )
        }.onFailure { error ->
            if (error is kotlinx.coroutines.CancellationException) throw error
            System.err.println("[EPG-Index] Failed to replace guide index: ${error.message}")
        }.onSuccess {
            System.err.println(
                "[IPTV-Timing] full guide index replace channels=${nowNext.size} " +
                    "in ${System.currentTimeMillis() - startedAt}ms"
            )
        }
    }

    private fun persistEpgIndexChannels(
        config: IptvConfig,
        nowNext: Map<String, IptvNowNext>,
        updatedAtMs: Long = System.currentTimeMillis()
    ) {
        if (!hasAnyProgramData(nowNext)) return
        val startedAt = System.currentTimeMillis()
        runCatching {
            epgIndex.replaceChannels(currentEpgIndexKey(config), nowNext, updatedAtMs)
        }.onFailure { error ->
            System.err.println("[EPG-Index] Failed to update guide index: ${error.message}")
        }.onSuccess {
            if (nowNext.size >= 100) {
                System.err.println(
                    "[IPTV-Timing] partial guide index replace channels=${nowNext.size} " +
                        "in ${System.currentTimeMillis() - startedAt}ms"
                )
            }
        }
    }

    private fun countIndexedGuideChannels(): Int {
        val indexKey = currentEpgIndexKey
        if (indexKey.isBlank()) return 0
        return runCatching { epgIndex.countChannelsWithPrograms(indexKey) }.getOrDefault(0)
    }

    private fun countIndexedGuidePrograms(): Int {
        val indexKey = currentEpgIndexKey
        if (indexKey.isBlank()) return 0
        return runCatching { epgIndex.countPrograms(indexKey) }.getOrDefault(0)
    }

    private suspend fun fetchFreshChannelsForStartup(config: IptvConfig): Pair<List<IptvChannel>, Map<String, com.arflix.tv.data.api.StalkerApi>>? {
        val activeLists = activePlaylists(config)
        val stalkerPortals = activeStalkerPortals(config)
        if (activeLists.isEmpty() && stalkerPortals.isEmpty()) return null

        // Stalker-only mode: no playlists configured.
        if (activeLists.isEmpty() && stalkerPortals.isNotEmpty()) {
            val (apis, channels) = loadStalkerChannels(stalkerPortals)
            return if (channels.isNotEmpty()) channels to apis else null
        }

        // Load playlists and Stalker in parallel when both are configured.
        val (playlistChannels, stalkerApis, stalkerChannels) = coroutineScope {
            val stalkerDeferred = if (stalkerPortals.isNotEmpty()) {
                async { loadStalkerChannels(stalkerPortals) }
            } else {
                null
            }
            val playlists = activeLists.map { playlist ->
                async {
                    try {
                        fetchChannelsForPlaylistWithRetries(playlist) { }
                            .map { channel ->
                                channel.copy(
                                    id = "${playlist.id}:${channel.id}",
                                    group = channel.group
                                )
                            }
                    } catch (error: Throwable) {
                        if (error is kotlinx.coroutines.CancellationException) throw error
                        System.err.println(
                            "IptvRepository: startup prefetch skipped failed playlist ${playlist.id}: ${error.message}"
                        )
                        emptyList()
                    }
                }
            }.awaitAll().flatten()
            val (sa, sc) = stalkerDeferred?.await()
                ?: (emptyMap<String, com.arflix.tv.data.api.StalkerApi>() to emptyList<IptvChannel>())
            Triple(playlists, sa, sc)
        }

        val merged = playlistChannels + stalkerChannels
        return if (merged.isNotEmpty()) merged to stalkerApis else null
    }

    private suspend fun storeStartupChannels(
        config: IptvConfig,
        channels: List<IptvChannel>,
        stalkerApis: Map<String, com.arflix.tv.data.api.StalkerApi>
    ) {
        loadMutex.withLock {
            cachedChannels = channels
            cachedGroupedChannels = buildGroupedChannels(channels)
            cachedPlaylistAt = System.currentTimeMillis()
            if (stalkerApis.isNotEmpty()) {
                cachedStalkerApis = stalkerApis
            }
            val validIds = channels.asSequence().map { it.id }.toSet()
            if (cachedNowNext.isNotEmpty()) {
                cachedNowNext.keys.retainAll(validIds)
            }
            persistCurrentCacheSnapshot(config, cachedPlaylistAt)
        }
    }

    suspend fun prefetchFreshStartupData() {
        if (!startupPrefetchInFlight.compareAndSet(false, true)) return
        try {
            warmupFromCacheOnly()
            val config = observeConfig().first()
            if (!hasAnyConfiguredSource(config)) return

            val cached = getMemoryCachedSnapshot() ?: getCachedSnapshotOrNull()
            if (cached == null || cached.channels.isEmpty()) {
                val fresh = withTimeoutOrNull(25_000L) {
                    fetchFreshChannelsForStartup(config)
                }
                if (fresh != null) {
                    storeStartupChannels(config, fresh.first, fresh.second)
                }
                return
            }
        } finally {
            startupPrefetchInFlight.set(false)
        }
    }

    fun invalidateCache() {
        // The shared Stalker downloads are deliberately NOT dropped here. Each
        // is keyed by one portal (id + URL + MAC), and portals that disappear
        // are released in ensureCacheOwnership, which every entry point passes
        // through. Everything else this function is called for — a playlist
        // toggled, an EPG URL edited, a profile switched to one with the same
        // portals — leaves the portals alone, so their channel lists stay
        // valid. Dropping them anyway tore up a download that another entry
        // point was already running and cost a second full 27.67 MB list on
        // every playlist toggle (measured).
        cachedChannels = emptyList()
        cachedChannelsLookupSource = null
        cachedChannelsById = emptyMap()
        cachedGroupedChannels = emptyMap()
        cachedNowNext = ConcurrentHashMap()
        cachedPlaylistAt = 0L
        cachedEpgAt = 0L
        stalkerEpgCache.clear()
        stalkerShortEpgCache.clear()
        stalkerVodSearchCache.clear()
        discoveredM3uEpgUrls.clear()
        xtreamVodCacheKey = null
        xtreamVodLoadedAtMs = 0L
        xtreamSeriesLoadedAtMs = 0L
        cachedXtreamVodStreams = emptyList()
        cachedXtreamSeries = emptyList()
        cachedXtreamSeriesEpisodes = emptyMap()
        xtreamSeriesEpisodeInFlight = emptyMap()
        cacheOwnerProfileId = null
        cacheOwnerConfigSig = null
        currentEpgIndexKey = ""
        // Drop derived per-creds caches that depend on the catalogs above.
        cachedXtreamVodCategories = emptyList()
        cachedXtreamSeriesCategories = emptyList()
        xtreamVodCategoriesLoadedAtMs = 0L
        xtreamSeriesCategoriesLoadedAtMs = 0L
        cachedVodIndex = null
        cachedVodIdIndex = null
        clearIptvMovieSourceCache()
        // Keep disk VOD/series catalogs. They are credential-keyed and TTL checked;
        // deleting them during a generic refresh can race with playback source resolution.
    }

    /**
     * Hard reset of every IPTV-side cache: in-memory state, resolver memory,
     * persisted resolver SharedPrefs, persisted movie-source cache, and the
     * on-disk Xtream VOD/series/category catalogs. Use this when the user
     * explicitly asks for a full refresh — the next resolve will go all the
     * way back to the provider.
     *
     * Unlike [invalidateCache], this also deletes the disk catalogs so that
     * [warmVodCachesIfPossible] is guaranteed to re-fetch from network.
     */
    suspend fun purgeAllIptvSourceCaches(preserveLiveSnapshot: Boolean = false) {
        val sourceKey = currentEpgIndexKey
        invalidateCache()
        if (preserveLiveSnapshot && sourceKey.isNotBlank()) {
            currentEpgIndexKey = sourceKey
        }
        withContext(Dispatchers.IO) {
            if (preserveLiveSnapshot) {
                // A refresh must never blank Live TV. Keep the last complete
                // channel/EPG indexes readable until their replacements commit.
                runCatching { cacheFile().delete() }
                runCatching { channelCacheFile().delete() }
            } else {
                deletePersistedSourceCaches(sourceKey)
            }
            runCatching { seriesResolver.clearAll() }
            runCatching {
                xtreamDiskCacheDir().listFiles()?.forEach { it.delete() }
            }
        }
    }

    private fun deletePersistedSourceCaches(sourceKey: String) {
        runCatching { channelStore.deleteSource(sourceKey) }
        invalidatePagedChannelStoreCount()
        runCatching { epgIndex.deleteSource(sourceKey) }
        runCatching { cacheFile().delete() }
        runCatching { channelCacheFile().delete() }
    }

    /**
     * Releases what is held for Stalker portals that are no longer configured:
     * their channel list and, with it, the portal session that came with it.
     *
     * [loadStalkerChannels] cannot do this. When the last portal is removed
     * every caller returns before it is reached, so the removed portal's list
     * and session stayed in memory until the app was closed.
     *
     * Only portals that actually disappeared are dropped. A playlist toggled,
     * an EPG URL edited or a profile switched to one with the same portals
     * leaves every key in place, so unrelated changes keep sharing the download
     * they already have (measured: dropping it anyway cost a second full
     * 27.67 MB list on every playlist toggle).
     */
    private fun releaseStalkerStateForRemovedPortals(config: IptvConfig) {
        val liveKeys = activeStalkerPortalKeys(config)
        stalkerChannelListLoader.retainOnly(liveKeys)
        val livePortalIds = activeStalkerPortals(config).map { it.id }.toSet()
        if (cachedStalkerApis.keys.any { it !in livePortalIds }) {
            cachedStalkerApis = cachedStalkerApis.filterKeys { it in livePortalIds }
        }
    }

    /**
     * Runs before any entry point can decide it has nothing to do — the live TV
     * snapshot load, the cache-only warmup and the cached-snapshot read all pass
     * through here first, whether or not a source is configured. That makes it
     * the one place where "the last portal is gone" is actually observed, so it
     * is where the Stalker state of removed portals is released.
     *
     * `internal` so a test can drive it with a plain [IptvConfig], same
     * convention as [activePlaylists].
     */
    internal fun ensureCacheOwnership(profileId: String, config: IptvConfig) {
        releaseStalkerStateForRemovedPortals(config)
        val sig = buildSourceSignature(config)
        val ownerChanged = cacheOwnerProfileId != null && cacheOwnerProfileId != profileId
        val configChanged = cacheOwnerConfigSig != null && cacheOwnerConfigSig != sig
        if (ownerChanged || configChanged) {
            invalidateCache()
        }
        cacheOwnerProfileId = profileId
        cacheOwnerConfigSig = sig
        currentEpgIndexKey = epgIndexKey(profileId, config)
    }

    private fun m3uUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_m3u_url")
    private fun m3uUrlKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_m3u_url")
    private fun epgUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_epg_url")
    private fun playlistsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_playlists_json")
    private fun stalkerPortalsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_stalker_portals_json")
    private fun stalkerPortalsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_stalker_portals_json")
    // Legacy single-portal keys kept only to read+ migrate pre-list data.
    private fun stalkerPortalUrlKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_stalker_portal_url")
    private fun stalkerPortalUrlKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_stalker_portal_url")
    private fun stalkerMacAddressKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_stalker_mac_address")
    private fun stalkerMacAddressKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_stalker_mac_address")
    private fun sortOrderKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_sort_order")
    private fun sortOrderKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_sort_order")
    private fun epgUrlKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_epg_url")
    private fun favoriteGroupsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_favorite_groups")
    private fun favoriteGroupsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_favorite_groups")
    private fun favoriteChannelsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_favorite_channels")
    private fun favoriteChannelsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_favorite_channels")

    private fun decodeFavoriteGroups(prefs: Preferences): List<String> {
        val raw = prefs[favoriteGroupsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodePlaylists(raw: String): List<IptvPlaylistEntry> {
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, IptvPlaylistEntry::class.java).type
            gson.fromJson<List<IptvPlaylistEntry>>(raw, type)
                ?.mapIndexed { index, playlist ->
                    normalizePlaylistEntry(playlist, index)
                }
                ?.filterNotNull()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun normalizeStalkerPortalEntry(
        portal: StalkerPortalEntry,
        index: Int
    ): StalkerPortalEntry? = StalkerPortalSupport.normalizeStalkerPortalEntry(portal, index)

    private fun decodeStalkerPortals(raw: String): List<StalkerPortalEntry> =
        StalkerPortalSupport.decodeStalkerPortals(raw, MAX_STALKER_PORTALS)

    /**
     * Reads the persisted Stalker portal list. When the list store is empty but
     * the legacy single-portal fields are set, migrates them into `Portal 1`
     * (id `stalker1`) once so existing users keep their portal after the update.
     */
    private fun readStalkerPortals(prefs: Preferences): List<StalkerPortalEntry> {
        val listRaw = prefs[stalkerPortalsKey()].orEmpty()
        if (listRaw.isNotBlank()) return decodeStalkerPortals(listRaw)
        val legacyUrl = decryptConfigValue(prefs[stalkerPortalUrlKey()].orEmpty()).trim().trimEnd('/')
        val legacyMac = prefs[stalkerMacAddressKey()].orEmpty().trim().uppercase()
        return StalkerPortalSupport.migratedPortalFromLegacy(legacyUrl, legacyMac)?.let { listOf(it) } ?: emptyList()
    }

    private fun readStalkerPortalsFor(
        prefs: Preferences,
        profileId: String
    ): List<StalkerPortalEntry> {
        val listRaw = prefs[stalkerPortalsKeyFor(profileId)].orEmpty()
        if (listRaw.isNotBlank()) return decodeStalkerPortals(listRaw)
        val legacyUrl = decryptConfigValue(prefs[stalkerPortalUrlKeyFor(profileId)].orEmpty()).trim().trimEnd('/')
        val legacyMac = prefs[stalkerMacAddressKeyFor(profileId)].orEmpty().trim().uppercase()
        return StalkerPortalSupport.migratedPortalFromLegacy(legacyUrl, legacyMac)?.let { listOf(it) } ?: emptyList()
    }

    private fun hiddenGroupsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_hidden_groups")
    private fun hiddenGroupsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_hidden_groups")
    private fun lockedGroupsKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_locked_groups")
    private fun lockedGroupsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_locked_groups")
    private fun groupOrderKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_group_order")
    private fun groupOrderKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_group_order")
    private fun groupOrderSchemaKey(): Preferences.Key<String> =
        profileManager.profileStringKey("iptv_group_order_schema")
    private fun groupOrderSchemaKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_group_order_schema")
    private fun playlistsKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_playlists_json")
    private fun tvSessionKey(): Preferences.Key<String> = profileManager.profileStringKey("iptv_tv_session")
    private fun tvSessionKeyFor(profileId: String): Preferences.Key<String> =
        profileManager.profileStringKeyFor(profileId, "iptv_tv_session")

    private fun decodeHiddenGroups(prefs: Preferences): List<String> {
        val raw = prefs[hiddenGroupsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            val list = gson.fromJson<List<String>>(raw, type)?.map { it.trim() }?.filter { it.isNotBlank() }?.distinct() ?: emptyList()
            val scoped = if (list.any { !it.contains('|') }) {
                val playlistsRaw = prefs[playlistsKey()].orEmpty()
                if (playlistsRaw.isBlank()) {
                    list
                } else {
                    val playlists = decodePlaylists(playlistsRaw)
                    val firstId = playlists.firstOrNull()?.id
                    if (firstId != null) {
                        list.map { if (it.contains('|')) it else "$firstId|$it" }.distinct()
                    } else list
                }
            } else list
            StalkerPortalSupport.normalizePlaylistGroupKeys(scoped)
        }.getOrDefault(emptyList())
    }

    private fun decodeLockedGroups(prefs: Preferences): List<String> {
        val raw = prefs[lockedGroupsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            val list = gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                .orEmpty()
            val playlists = decodePlaylists(prefs[playlistsKey()].orEmpty())
            val firstId = playlists.firstOrNull()?.id
            val scoped = if (firstId != null) {
                list.map { if ('|' in it) it else "$firstId|$it" }
            } else {
                list
            }
            StalkerPortalSupport.normalizePlaylistGroupKeys(scoped)
        }.getOrDefault(emptyList())
    }

    private fun decodeGroupOrder(prefs: Preferences): List<String> {
        if (prefs[groupOrderSchemaKey()]?.toIntOrNull() != IPTV_GROUP_ORDER_SCHEMA) return emptyList()
        val raw = prefs[groupOrderKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            val list = gson.fromJson<List<String>>(raw, type)?.map { it.trim() }?.filter { it.isNotBlank() }?.distinct() ?: emptyList()
            val scoped = if (list.any { !it.contains('|') }) {
                val playlistsRaw = prefs[playlistsKey()].orEmpty()
                if (playlistsRaw.isBlank()) {
                    list
                } else {
                    val playlists = decodePlaylists(playlistsRaw)
                    val firstId = playlists.firstOrNull()?.id
                    if (firstId != null) {
                        list.map { if (it.contains('|')) it else "$firstId|$it" }.distinct()
                    } else list
                }
            } else list
            StalkerPortalSupport.normalizePlaylistGroupKeys(scoped)
        }.getOrDefault(emptyList())
    }

    private fun mergedGroupOrder(savedOrder: List<String>, currentGroups: List<String>): MutableList<String> {
        val current = currentGroups
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinct()
        val currentSet = current.toHashSet()
        val merged = savedOrder
            .map { it.trim() }
            .filter { it.isNotBlank() && (currentSet.isEmpty() || it in currentSet) }
            .distinct()
            .toMutableList()
        current.forEach { group ->
            if (group !in merged) merged.add(group)
        }
        return merged
    }

    private fun decodeFavoriteChannels(prefs: Preferences): List<String> {
        val raw = prefs[favoriteChannelsKey()].orEmpty()
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodeTvSessionState(prefs: Preferences): IptvTvSessionState =
        decodeTvSessionState(prefs[tvSessionKey()].orEmpty())

    private fun decodeTvSessionState(raw: String): IptvTvSessionState {
        if (raw.isBlank()) return IptvTvSessionState()
        return runCatching {
            gson.fromJson(raw, IptvTvSessionState::class.java)?.let { session ->
                session.copy(
                    lastChannelId = StalkerPortalSupport.migrateLegacyChannelId(session.lastChannelId),
                    lastGroupName = session.lastGroupName.trim(),
                    lastFocusedZone = session.lastFocusedZone.trim().ifBlank { "GUIDE" },
                    recentChannelIds = runCatching { session.recentChannelIds }
                        .getOrNull()
                        .orEmpty()
                        .map(StalkerPortalSupport::migrateLegacyChannelId)
                        .filter { it.isNotBlank() }
                        .distinct()
                        .takeLast(40)
                )
            } ?: IptvTvSessionState()
        }.getOrDefault(IptvTvSessionState())
    }

    private fun decodeFavoriteGroups(raw: String): List<String> {
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    private fun decodeFavoriteChannels(raw: String): List<String> {
        if (raw.isBlank()) return emptyList()
        return runCatching {
            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
            gson.fromJson<List<String>>(raw, type)
                ?.map(StalkerPortalSupport::migrateLegacyChannelId)
                ?.filter { it.isNotBlank() }
                ?.distinct()
                ?: emptyList()
        }.getOrDefault(emptyList())
    }

    suspend fun exportCloudConfigForProfile(profileId: String): IptvCloudProfileState {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val prefs = context.settingsDataStore.data.first()
        val hiddenRaw = prefs[hiddenGroupsKeyFor(safeProfileId)].orEmpty()
        val lockedRaw = prefs[lockedGroupsKeyFor(safeProfileId)].orEmpty()
        val orderRaw = prefs[groupOrderKeyFor(safeProfileId)].orEmpty()
        val orderSchema = prefs[groupOrderSchemaKeyFor(safeProfileId)]?.toIntOrNull() ?: 0
        val playlistsRaw = prefs[playlistsKeyFor(safeProfileId)].orEmpty()
        val tvSessionRaw = prefs[tvSessionKeyFor(safeProfileId)].orEmpty()
        val playlists = decodePlaylists(playlistsRaw)
        val stalkerPortals = readStalkerPortalsFor(prefs, safeProfileId)
        val validSourceIds = buildSet {
            playlists.forEach { add(it.id) }
            stalkerPortals.forEach { add(it.id) }
        }
        val primary = playlists.firstOrNull()
        val legacyM3uUrl = normalizeStoredIptvUrl(decryptConfigValue(prefs[m3uUrlKeyFor(safeProfileId)].orEmpty()))
        val legacyEpgUrls = normalizeStoredEpgInputs(decryptConfigValue(prefs[epgUrlKeyFor(safeProfileId)].orEmpty()))
        return IptvCloudProfileState(
            m3uUrl = primary?.m3uUrl ?: legacyM3uUrl,
            epgUrl = primary?.epgUrl ?: legacyEpgUrls.firstOrNull().orEmpty(),
            stalkerPortals = stalkerPortals,
            favoriteGroups = decodeFavoriteGroups(prefs[favoriteGroupsKeyFor(safeProfileId)].orEmpty()),
            favoriteChannels = decodeFavoriteChannels(prefs[favoriteChannelsKeyFor(safeProfileId)].orEmpty()),
            hiddenGroups = if (hiddenRaw.isNotBlank()) {
                runCatching {
                    val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                    StalkerPortalSupport.normalizePlaylistGroupKeys(
                        gson.fromJson<List<String>>(hiddenRaw, type).orEmpty(),
                        validSourceIds,
                    )
                }.getOrDefault(emptyList())
            } else emptyList(),
            lockedGroups = if (lockedRaw.isNotBlank()) {
                runCatching {
                    val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                    StalkerPortalSupport.normalizePlaylistGroupKeys(
                        gson.fromJson<List<String>>(lockedRaw, type).orEmpty(),
                        validSourceIds,
                    )
                }.getOrDefault(emptyList())
            } else emptyList(),
            groupOrder = if (orderSchema == IPTV_GROUP_ORDER_SCHEMA && orderRaw.isNotBlank()) {
                runCatching {
                    val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                    StalkerPortalSupport.normalizePlaylistGroupKeys(
                        gson.fromJson<List<String>>(orderRaw, type).orEmpty(),
                        validSourceIds,
                    )
                }.getOrDefault(emptyList())
            } else emptyList(),
            groupOrderSchema = IPTV_GROUP_ORDER_SCHEMA,
            sortOrder = normalizeIptvSortOrder(prefs[sortOrderKeyFor(safeProfileId)]),
            playlists = playlists,
            tvSession = decodeTvSessionState(tvSessionRaw)
        )
    }

    suspend fun importCloudConfigForProfile(
        profileId: String,
        state: IptvCloudProfileState,
        incomingFieldTimestamps: org.json.JSONObject? = null,
    ): Boolean {
        val safeProfileId = profileId.trim().ifBlank { "default" }
        val previousState = exportCloudConfigForProfile(safeProfileId)
        if (previousState == state) return false
        val normalizedM3u = normalizeStoredIptvUrl(state.m3uUrl)
        val normalizedEpgUrls = normalizeStoredEpgInputs(state.epgUrl)
        val normalizedEpg = normalizedEpgUrls.firstOrNull().orEmpty()
        val importedStalkerPortals = runCatching { state.stalkerPortals }
            .getOrNull()
            .orEmpty()
            .ifEmpty {
                StalkerPortalSupport.migratedPortalFromLegacy(
                    runCatching { state.stalkerPortalUrl }.getOrNull().orEmpty(),
                    runCatching { state.stalkerMacAddress }.getOrNull().orEmpty(),
                )?.let(::listOf).orEmpty()
            }
        val normalizedStalkerPortals = StalkerPortalSupport.normalizeStalkerPortals(
            importedStalkerPortals,
            MAX_STALKER_PORTALS,
        )
        val normalizedPlaylists = state.playlists.mapIndexed { index, playlist ->
            normalizePlaylistEntry(playlist, index)
        }.filterNotNull().take(3)
        val effectivePlaylists = normalizedPlaylists.ifEmpty {
            if (normalizedM3u.isBlank()) emptyList() else listOf(
                IptvPlaylistEntry(
                    id = "list_1",
                    name = "List 1",
                    m3uUrl = normalizedM3u,
                    epgUrl = normalizedEpg,
                    epgUrls = normalizedEpgUrls,
                )
            )
        }
        val sourcesChanged = previousState.m3uUrl != normalizedM3u || previousState.epgUrl != normalizedEpg ||
            previousState.playlists != effectivePlaylists || previousState.stalkerPortals != normalizedStalkerPortals
        val validSourceIds = buildSet {
            effectivePlaylists.forEach { add(it.id) }
            normalizedStalkerPortals.forEach { add(it.id) }
        }
        val defaultSourceId = effectivePlaylists.firstOrNull()?.id
            ?: normalizedStalkerPortals.firstOrNull()?.id
        fun normalizeCloudGroupKeys(keys: List<String>): List<String> {
            val scoped = keys.map { raw ->
                val trimmed = raw.trim()
                if ('|' !in trimmed && defaultSourceId != null) "$defaultSourceId|$trimmed" else trimmed
            }
            return StalkerPortalSupport.normalizePlaylistGroupKeys(scoped, validSourceIds)
        }
        val normalizedHiddenGroups = normalizeCloudGroupKeys(state.hiddenGroups)
        val normalizedLockedGroups = normalizeCloudGroupKeys(state.lockedGroups)
        val normalizedGroupOrder = state.groupOrder
            .takeIf { state.groupOrderSchema >= IPTV_GROUP_ORDER_SCHEMA }
            .orEmpty()
            .let(::normalizeCloudGroupKeys)
        val normalizedFavoriteChannels = state.favoriteChannels
            .map(StalkerPortalSupport::migrateLegacyChannelId)
            .filter { it.isNotBlank() }
            .distinct()
        val normalizedTvSession = state.tvSession.copy(
            lastChannelId = StalkerPortalSupport.migrateLegacyChannelId(state.tvSession.lastChannelId),
            lastGroupName = state.tvSession.lastGroupName.trim(),
            lastFocusedZone = state.tvSession.lastFocusedZone.trim().ifBlank { "GUIDE" },
            recentChannelIds = state.tvSession.recentChannelIds
                .map(StalkerPortalSupport::migrateLegacyChannelId)
                .filter { it.isNotBlank() }
                .distinct()
                .takeLast(40),
        )
        var preservedConcurrentEdit = false
        context.settingsDataStore.edit { prefs ->
            if (incomingFieldTimestamps != null &&
                IptvCloudFields.hasNewerLocalChange(prefs, safeProfileId, incomingFieldTimestamps)
            ) {
                preservedConcurrentEdit = true
                return@edit
            }
            prefs[m3uUrlKeyFor(safeProfileId)] = encryptConfigValue(normalizedM3u)
            prefs[epgUrlKeyFor(safeProfileId)] = encryptConfigValue(normalizedEpg)
            if (normalizedStalkerPortals.isEmpty()) {
                prefs.remove(stalkerPortalsKeyFor(safeProfileId))
            } else {
                prefs[stalkerPortalsKeyFor(safeProfileId)] = gson.toJson(normalizedStalkerPortals)
            }
            // Clear legacy single-portal keys so the list store is authoritative.
            prefs.remove(stalkerPortalUrlKeyFor(safeProfileId))
            prefs.remove(stalkerMacAddressKeyFor(safeProfileId))
            prefs[favoriteGroupsKeyFor(safeProfileId)] = gson.toJson(state.favoriteGroups.distinct())
            prefs[favoriteChannelsKeyFor(safeProfileId)] = gson.toJson(normalizedFavoriteChannels)
            if (normalizedHiddenGroups.isEmpty()) prefs.remove(hiddenGroupsKeyFor(safeProfileId))
            else prefs[hiddenGroupsKeyFor(safeProfileId)] = gson.toJson(normalizedHiddenGroups)
            if (normalizedLockedGroups.isEmpty()) prefs.remove(lockedGroupsKeyFor(safeProfileId))
            else prefs[lockedGroupsKeyFor(safeProfileId)] = gson.toJson(normalizedLockedGroups)
            if (normalizedGroupOrder.isEmpty()) prefs.remove(groupOrderKeyFor(safeProfileId))
            else prefs[groupOrderKeyFor(safeProfileId)] = gson.toJson(normalizedGroupOrder)
            prefs[groupOrderSchemaKeyFor(safeProfileId)] = IPTV_GROUP_ORDER_SCHEMA.toString()
            prefs[sortOrderKeyFor(safeProfileId)] = normalizeIptvSortOrder(state.sortOrder)
            if (effectivePlaylists.isEmpty()) prefs.remove(playlistsKeyFor(safeProfileId))
            else prefs[playlistsKeyFor(safeProfileId)] = gson.toJson(effectivePlaylists)
            if (normalizedTvSession != IptvTvSessionState()) {
                prefs[tvSessionKeyFor(safeProfileId)] = gson.toJson(normalizedTvSession)
            } else {
                prefs.remove(tvSessionKeyFor(safeProfileId))
            }
        }
        if (preservedConcurrentEdit) return true
        groupOrderLocallyDirty = false
        if (sourcesChanged && profileManager.getProfileIdSync() == safeProfileId) {
            cachedStalkerApis = emptyMap()
            invalidateCache()
        }
        return false
    }

    fun completedFullGuideAgeMs(): Long {
        val key = currentEpgIndexKey
        if (key.isBlank()) return Long.MAX_VALUE
        val completedAt = runCatching { epgIndex.fullRefreshAtMs(key) }.getOrDefault(0L)
        return if (completedAt > 0L) (System.currentTimeMillis() - completedAt).coerceAtLeast(0L) else Long.MAX_VALUE
    }

    private suspend fun fetchChannelsForPlaylistWithRetries(
    playlist: IptvPlaylistEntry,
    onProgress: (IptvLoadProgress) -> Unit
): List<IptvChannel> {
    if (playlist.importLiveTv == false) {
            return emptyList()
    }
    resolveXtreamCredentials(playlist)?.let { creds ->
        onProgress(IptvLoadProgress(context.getString(R.string.iptv_xtream_detected), 6))
        val apiResult = runCatching {
            withTimeoutOrNull(60_000L) {
                fetchXtreamLiveChannels(creds, onProgress)
            } ?: throw IllegalStateException(context.getString(R.string.iptv_xtream_timeout))
        }
        apiResult.exceptionOrNull()?.let { error ->
            if (error is kotlinx.coroutines.CancellationException) throw error
        }
        val providerOrdered = apiResult.getOrDefault(emptyList())
        if (providerOrdered.isNotEmpty()) {
            onProgress(
                IptvLoadProgress(
                    context.getString(R.string.iptv_loaded_api, providerOrdered.size),
                    95,
                )
            )
            return providerOrdered
        }
        apiResult.exceptionOrNull()?.let { error ->
            System.err.println("IptvRepository: Xtream catalog unavailable; falling back to M3U: ${error.message}")
        }
    }
    return fetchAndParseM3uWithRetries(playlist.m3uUrl, onProgress)
}

    private suspend fun fetchAndParseM3uWithRetries(
        url: String,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val normalizedUrl = normalizeStoredIptvUrl(url)
        var lastError: Throwable? = null
        val maxAttempts = 2
        repeat(maxAttempts) { attempt ->
            onProgress(IptvLoadProgress("Connecting to playlist (attempt ${attempt + 1}/$maxAttempts)...", 5))
            runCatching {
                withTimeoutOrNull(90_000L) {
                    fetchAndParseM3uOnce(normalizedUrl, onProgress)
                } ?: throw IllegalStateException(context.getString(R.string.iptv_playlist_timeout))
            }.onSuccess { channels ->
                if (channels.isNotEmpty()) return channels
                lastError = IllegalStateException(context.getString(R.string.iptv_no_channels))
            }.onFailure { error ->
                if (error is kotlinx.coroutines.CancellationException) throw error
                lastError = error
            }

            if (attempt < maxAttempts - 1) {
                val backoffMs = (1_000L * (attempt + 1)).coerceAtMost(2_000L)
                onProgress(IptvLoadProgress("Retrying in ${backoffMs / 1000}s...", 5))
                delay(backoffMs)
            }
        }
        throw (lastError ?: IllegalStateException(context.getString(R.string.iptv_failed_load_m3u)))
    }

    private data class XtreamCredentials(
        val baseUrl: String,
        val username: String,
        val password: String
    )

    private data class XtreamLiveCategory(
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("category_name") val categoryName: String? = null
    )

    private data class XtreamLiveStream(
        @SerializedName("stream_id") val streamId: Int? = null,
        @SerializedName("num") val providerNumber: String? = null,
        val name: String? = null,
        @SerializedName("stream_icon") val streamIcon: String? = null,
        @SerializedName("epg_channel_id") val epgChannelId: String? = null,
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("container_extension") val containerExtension: String? = null,
        @SerializedName("tv_archive") val tvArchive: Int? = null,
        @SerializedName("tv_archive_duration") val tvArchiveDuration: Int? = null
    )

    private data class XtreamVodStream(
        @SerializedName("stream_id") val streamId: Int? = null,
        val name: String? = null,
        val year: String? = null,
        @SerializedName("container_extension") val containerExtension: String? = null,
        @SerializedName(value = "imdb", alternate = ["imdb_id", "imdbid"]) val imdb: String? = null,
        @SerializedName(value = "tmdb", alternate = ["tmdb_id", "tmdbid"]) val tmdb: String? = null,
        @SerializedName("category_id") val categoryId: String? = null
    )

    private data class XtreamSeriesItem(
        @SerializedName(value = "series_id", alternate = ["seriesid", "id"]) val seriesId: Int? = null,
        val name: String? = null,
        @SerializedName(value = "imdb", alternate = ["imdb_id", "imdbid"]) val imdb: String? = null,
        @SerializedName(value = "tmdb", alternate = ["tmdb_id", "tmdbid"]) val tmdb: String? = null,
        @SerializedName("category_id") val categoryId: String? = null
    )

    private data class XtreamSeriesEpisode(
        val id: Int,
        val season: Int,
        val episode: Int,
        val title: String,
        val containerExtension: String?
    )

    private data class ResolverSeriesEntry(
        val seriesId: Int,
        val name: String = "",
        val normalizedName: String = "",
        val canonicalTitleKey: String = "",
        val titleTokens: Set<String> = emptySet(),
        val tmdb: String?,
        val imdb: String?,
        val year: Int?
    )

    private data class ResolverCatalogIndex(
        val createdAtMs: Long,
        val entries: List<ResolverSeriesEntry>,
        val tmdbMap: Map<String, List<ResolverSeriesEntry>>,
        val imdbMap: Map<String, List<ResolverSeriesEntry>>,
        val canonicalTitleMap: Map<String, List<ResolverSeriesEntry>>,
        val tokenMap: Map<String, List<ResolverSeriesEntry>>
    )

    private data class ResolverCandidate(
        val entry: ResolverSeriesEntry,
        val confidence: Float,
        val method: String,
        val baseScore: Int
    )

    private data class ResolverEpisodeHit(
        val episode: XtreamSeriesEpisode,
        val score: Int
    )

    private data class ResolverCachedResolvedEpisode(
        val streamId: Int,
        val containerExtension: String?,
        val seriesId: Int,
        val confidence: Float,
        val method: String,
        val title: String? = null,
        val savedAtMs: Long
    )

    private data class ResolverPersistedCatalog(
        val formatVersion: Int = 0,
        val createdAtMs: Long = 0L,
        val entries: List<ResolverSeriesEntry> = emptyList()
    )

    private data class ResolverPersistedResolved(
        val items: Map<String, ResolverCachedResolvedEpisode> = emptyMap()
    )

    private data class ResolverPersistedSeriesInfo(
        val savedAtMs: Long = 0L,
        val episodes: List<XtreamSeriesEpisode> = emptyList()
    )

    private data class ResolverPersistedSeriesBindings(
        val items: Map<String, List<Int>> = emptyMap()
    )

    private inner class IptvSeriesResolverService {
        private val prefs by lazy { context.getSharedPreferences("iptv_series_resolver_cache_v1", Context.MODE_PRIVATE) }
        private val catalogLoadMutex = Mutex()
        private val catalogTtlMs = 24 * 60 * 60_000L
        private val resolvedTtlMs = 24 * 60 * 60_000L
        private val seriesInfoTtlMs = 24 * 60 * 60_000L
        private val catalogMemory = ConcurrentHashMap<String, ResolverCatalogIndex>()
        private val resolvedMemory = object : LinkedHashMap<String, ResolverCachedResolvedEpisode>(512, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, ResolverCachedResolvedEpisode>?): Boolean {
                return size > 512
            }
        }
        private val resolvedLock = Any()
        // Multi-binding: a single show name/ID can match multiple series entries
        // in the provider catalog (different qualities, dub variants, etc).
        // Storing the full list lets the picker show every resolved variant.
        private val seriesBindingMemory = object : LinkedHashMap<String, List<Int>>(2048, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, List<Int>>?): Boolean {
                return size > 2048
            }
        }
        private val maxSeriesBindingsPerKey = 8
        private val seriesBindingLock = Any()
        private val seriesInfoMemory = object : LinkedHashMap<String, List<XtreamSeriesEpisode>>(50, 0.75f, true) {
            override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, List<XtreamSeriesEpisode>>?): Boolean {
                return size > 50
            }
        }
        private val seriesInfoLock = Any()

        private fun catalogPrefKey(providerKey: String): String = "catalog_${providerKey.hashCode()}"

        private val resolvedPrefKey = "resolved_episode_map"
        // v2: stores List<Int> per binding key instead of single Int.
        // Bumping the key avoids parsing failures against the legacy single-id format.
        private val seriesBindingPrefKey = "series_binding_map_v2"
        private fun seriesInfoPrefKey(providerKey: String, seriesId: Int): String =
            "series_info_${(providerKey + "|" + seriesId).hashCode()}"

        /**
         * Entries are stored with their *pre-computed* normalized name, canonical
         * key and tokens. Whenever the normalization changes, those fields become
         * incompatible with freshly normalized queries and every title lookup
         * silently misses until the 24h TTL expires — longer still, because an
         * empty network response deliberately keeps the stale catalog alive.
         * Bumping this version discards such entries instead. Legacy JSON has no
         * field and therefore deserializes to 0.
         */
        private val catalogFormatVersion = 1

        private fun readPersistedCatalog(providerKey: String): ResolverPersistedCatalog? {
            val raw = runCatching { prefs.getString(catalogPrefKey(providerKey), null) }.getOrNull()
            if (raw.isNullOrBlank()) return null
            val persisted = runCatching {
                gson.fromJson(raw, ResolverPersistedCatalog::class.java)
            }.getOrNull() ?: return null
            if (persisted.formatVersion != catalogFormatVersion) {
                System.err.println(
                    "[VOD-Resolver] loadCatalog: discarding catalog written in format " +
                        "${persisted.formatVersion} (current $catalogFormatVersion)"
                )
                runCatching { prefs.edit().remove(catalogPrefKey(providerKey)).apply() }
                return null
            }
            return persisted.takeIf { it.entries.isNotEmpty() }
        }

        suspend fun refreshCatalog(
            providerKey: String,
            creds: XtreamCredentials
        ) {
            loadCatalog(providerKey, creds, allowNetwork = true, forceRefresh = true)
        }

        suspend fun prefetchSeriesInfo(
            providerKey: String,
            creds: XtreamCredentials,
            showTitle: String,
            tmdbId: Int?,
            imdbId: String?,
            year: Int?
        ) {
            val normalizedShow = normalizeLookupText(showTitle)
            val normalizedTmdb = normalizeTmdbId(tmdbId)
            val normalizedImdb = normalizeImdbId(imdbId)
            if (normalizedShow.isBlank() && normalizedTmdb.isNullOrBlank() && normalizedImdb.isNullOrBlank()) return

            val catalog = loadCatalog(providerKey, creds, allowNetwork = true, forceRefresh = false)
            if (catalog.entries.isEmpty()) return
            val candidates = buildCandidates(catalog, normalizedShow, normalizedTmdb, normalizedImdb, year)
            if (candidates.isEmpty()) return

            val probeList = if (candidates.first().confidence >= 0.9f) {
                candidates.take(1)
            } else {
                candidates.take(2)
            }
            coroutineScope {
                probeList.map { candidate ->
                    async {
                        withTimeoutOrNull(5_000L) {
                            loadSeriesInfo(
                                providerKey = providerKey,
                                creds = creds,
                                seriesId = candidate.entry.seriesId,
                                allowNetwork = true
                            )
                        }
                    }
                }.awaitAll()
            }
        }

        suspend fun resolveEpisode(
            providerKey: String,
            creds: XtreamCredentials,
            showTitle: String,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            imdbId: String?,
            year: Int?,
            allowNetwork: Boolean
        ): ResolverCachedResolvedEpisode? = resolveEpisodeVariants(
            providerKey = providerKey,
            creds = creds,
            showTitle = showTitle,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            imdbId = imdbId,
            year = year,
            allowNetwork = allowNetwork
        ).firstOrNull()

        /**
         * Cache-only fast path used at the top of `findEpisodeVodSources`.
         * Returns one variant per bound series ID whose episode list is
         * already cached (memory or fresh SharedPrefs entry). Empty list
         * means "no fast-path hit — fall through to normal resolution".
         */
        fun tryFastResolveEpisodeFromCache(
            providerKey: String,
            showTitle: String,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            imdbId: String?
        ): List<ResolverCachedResolvedEpisode> {
            val normalizedShow = normalizeLookupText(showTitle)
            val normalizedTmdb = normalizeTmdbId(tmdbId)
            val normalizedImdb = normalizeImdbId(imdbId)
            val bindingKeys = buildSeriesBindingKeys(
                providerKey = providerKey,
                normalizedTmdb = normalizedTmdb,
                normalizedImdb = normalizedImdb,
                normalizedShow = normalizedShow
            )
            val seriesIds = readSeriesBinding(bindingKeys)
            if (seriesIds.isEmpty()) return emptyList()

            val now = System.currentTimeMillis()
            val out = mutableListOf<ResolverCachedResolvedEpisode>()
            for (seriesId in seriesIds) {
                val infoKey = "$providerKey|$seriesId"
                var episodes: List<XtreamSeriesEpisode>? = synchronized(seriesInfoLock) {
                    seriesInfoMemory[infoKey]
                }
                if (episodes.isNullOrEmpty()) {
                    val raw = prefs.getString(seriesInfoPrefKey(providerKey, seriesId), null) ?: continue
                    val persisted = runCatching {
                        gson.fromJson(raw, ResolverPersistedSeriesInfo::class.java)
                    }.getOrNull() ?: continue
                    if (persisted.episodes.isEmpty()) continue
                    if (now - persisted.savedAtMs > seriesInfoTtlMs) continue
                    synchronized(seriesInfoLock) {
                        seriesInfoMemory[infoKey] = persisted.episodes
                    }
                    episodes = persisted.episodes
                }
                val list = episodes ?: continue
                val hits = matchEpisodes(list, season, episode)
                val best = hits.maxByOrNull { it.score } ?: continue
                out += ResolverCachedResolvedEpisode(
                    streamId = best.episode.id,
                    containerExtension = best.episode.containerExtension,
                    seriesId = seriesId,
                    confidence = 0.995f,
                    method = "fast_path_cache",
                    title = best.episode.title,
                    savedAtMs = now
                )
            }
            return out.distinctBy { it.streamId }
        }

        suspend fun resolveEpisodeVariants(
            providerKey: String,
            creds: XtreamCredentials,
            showTitle: String,
            season: Int,
            episode: Int,
            tmdbId: Int?,
            imdbId: String?,
            year: Int?,
            allowNetwork: Boolean
        ): List<ResolverCachedResolvedEpisode> {
            val resolveStart = System.currentTimeMillis()
            System.err.println("[VOD-Resolver] resolveEpisode start: '$showTitle' S${season}E${episode} tmdb=$tmdbId imdb=$imdbId")
            val normalizedShow = normalizeLookupText(showTitle)
            val normalizedTmdb = normalizeTmdbId(tmdbId)
            val normalizedImdb = normalizeImdbId(imdbId)
            if (normalizedShow.isBlank() && normalizedTmdb.isNullOrBlank() && normalizedImdb.isNullOrBlank()) {
                System.err.println("[VOD-Resolver] No identifiers, returning null")
                return emptyList()
            }

            val cacheKey = buildResolvedCacheKey(providerKey, normalizedTmdb, normalizedImdb, normalizedShow, season, episode)
            val cachedResolved = readResolved(cacheKey)?.takeIf { cached ->
                System.currentTimeMillis() - cached.savedAtMs < resolvedTtlMs
            }
            if (!allowNetwork && cachedResolved != null) {
                System.err.println("[VOD-Resolver] Hit resolved cache candidate (method=${cachedResolved.method}, streamId=${cachedResolved.streamId}) in ${System.currentTimeMillis() - resolveStart}ms")
            }
            var bindingResolved = emptyList<ResolverCachedResolvedEpisode>()

            val bindingKeys = buildSeriesBindingKeys(
                providerKey = providerKey,
                normalizedTmdb = normalizedTmdb,
                normalizedImdb = normalizedImdb,
                normalizedShow = normalizedShow
            )
            val boundSeriesIds = readSeriesBinding(bindingKeys)
            if (boundSeriesIds.isNotEmpty()) {
                System.err.println("[VOD-Resolver] Found series bindings: ${boundSeriesIds.size} seriesIds=$boundSeriesIds, loading info in parallel...")
                val bindStart = System.currentTimeMillis()
                val perSeriesEpisodes = coroutineScope {
                    boundSeriesIds.map { seriesId ->
                        async {
                            seriesId to loadSeriesInfo(
                                providerKey = providerKey,
                                creds = creds,
                                seriesId = seriesId,
                                allowNetwork = allowNetwork
                            )
                        }
                    }.awaitAll()
                }
                System.err.println("[VOD-Resolver] loadSeriesInfo for ${boundSeriesIds.size} bindings took ${System.currentTimeMillis() - bindStart}ms")
                val boundHits = perSeriesEpisodes.flatMap { (seriesId, episodes) ->
                    matchEpisodes(episodes, season, episode).map { hit -> seriesId to hit }
                }
                if (boundHits.isNotEmpty()) {
                    val resolved = boundHits
                        .sortedWith(
                            compareByDescending<Pair<Int, ResolverEpisodeHit>> {
                                it.second.score + vodQualityRank(it.second.episode.title)
                            }.thenBy { it.second.episode.id }
                        )
                        .map { (seriesId, hit) ->
                            ResolverCachedResolvedEpisode(
                                streamId = hit.episode.id,
                                containerExtension = hit.episode.containerExtension,
                                seriesId = seriesId,
                                confidence = 0.995f,
                                method = "series_binding",
                                title = hit.episode.title,
                                savedAtMs = System.currentTimeMillis()
                            )
                        }
                        .distinctBy { it.streamId }
                    val best = resolved.first()
                    writeResolved(cacheKey, best)
                    // Intentionally do NOT shrink bindings to only the IDs
                    // that matched this S/E. A series that lacks S01E01 may
                    // still be the right match for S04E01 — keep all known
                    // bindings around so future episode lookups can find
                    // them. Cache already holds the full set from the
                    // initial resolve.
                    val matchedSeriesIds = boundHits.map { it.first }.distinct()
                    System.err.println("[VOD-Resolver] Resolved ${resolved.size} variants from ${matchedSeriesIds.size}/${boundSeriesIds.size} bound series via binding in ${System.currentTimeMillis() - resolveStart}ms")
                    if (!allowNetwork) {
                        return resolved
                    }
                    bindingResolved = resolved
                } else {
                    System.err.println("[VOD-Resolver] Bindings didn't match S${season}E${episode}")
                }
            }

            if (!allowNetwork && cachedResolved != null) {
                return listOf(cachedResolved)
            }

            System.err.println("[VOD-Resolver] Loading catalog...")
            val catalogStart = System.currentTimeMillis()
            val catalog = loadCatalog(providerKey, creds, allowNetwork = allowNetwork, forceRefresh = false)
            System.err.println("[VOD-Resolver] loadCatalog took ${System.currentTimeMillis() - catalogStart}ms, entries=${catalog.entries.size}")
            if (catalog.entries.isEmpty()) {
                System.err.println("[VOD-Resolver] Empty catalog, returning null")
                return bindingResolved.ifEmpty { cachedResolved?.let { listOf(it) } ?: emptyList() }
            }

            val candidateStart = System.currentTimeMillis()
            val candidates = buildCandidates(catalog, normalizedShow, normalizedTmdb, normalizedImdb, year)
            System.err.println("[VOD-Resolver] buildCandidates took ${System.currentTimeMillis() - candidateStart}ms, found ${candidates.size} candidates")
            if (candidates.isEmpty()) {
                System.err.println("[VOD-Resolver] No candidates, returning null after ${System.currentTimeMillis() - resolveStart}ms")
                return bindingResolved.ifEmpty { cachedResolved?.let { listOf(it) } ?: emptyList() }
            }
            fun ResolverCandidate.isStrongIdentityMatch(): Boolean {
                return method == "tmdb_id" || method == "imdb_id" || method == "title_canonical"
            }
            val probeList = if (candidates.first().isStrongIdentityMatch()) {
                candidates
                    .filter { it.isStrongIdentityMatch() && it.confidence >= 0.90f }
                    .take(8)
                    .ifEmpty { candidates.take(1) }
            } else {
                candidates.take(4)
            }
            System.err.println("[VOD-Resolver] Probing ${probeList.size} candidates: ${probeList.map { "${it.entry.name}(${it.method},${it.confidence})" }}")

            val probeStart = System.currentTimeMillis()
            val hits = coroutineScope {
                probeList.map { candidate ->
                    async {
                        val infoStart = System.currentTimeMillis()
                        val episodes = loadSeriesInfo(providerKey, creds, candidate.entry.seriesId, allowNetwork)
                        System.err.println("[VOD-Resolver] loadSeriesInfo(${candidate.entry.seriesId}) took ${System.currentTimeMillis() - infoStart}ms, got ${episodes.size} episodes")
                        matchEpisodes(episodes, season, episode).map { hit ->
                            Triple(candidate, hit.episode, hit.score)
                        }
                    }
                }.awaitAll().flatten()
            }
            System.err.println("[VOD-Resolver] Probing took ${System.currentTimeMillis() - probeStart}ms, hits=${hits.size}")
            if (hits.isEmpty()) {
                System.err.println("[VOD-Resolver] No hits, returning null after ${System.currentTimeMillis() - resolveStart}ms")
                return bindingResolved.ifEmpty { cachedResolved?.let { listOf(it) } ?: emptyList() }
            }

            val probedResolved = hits
                .sortedWith(
                    compareByDescending<Triple<ResolverCandidate, XtreamSeriesEpisode, Int>> {
                        it.first.confidence * 1000f + it.third + vodQualityRank(it.second.title)
                    }.thenBy { it.second.id }
                )
                .distinctBy { it.second.id }
                .map { hit ->
                    ResolverCachedResolvedEpisode(
                        streamId = hit.second.id,
                        containerExtension = hit.second.containerExtension,
                        seriesId = hit.first.entry.seriesId,
                        confidence = hit.first.confidence,
                        method = hit.first.method,
                        title = hit.second.title,
                        savedAtMs = System.currentTimeMillis()
                    )
                }
            val resolved = (probedResolved + bindingResolved)
                .distinctBy { it.streamId }
                .sortedWith(
                    compareByDescending<ResolverCachedResolvedEpisode> {
                        it.confidence * 1000f + vodQualityRank(it.title.orEmpty())
                    }.thenBy { it.streamId }
                )
            val best = resolved.firstOrNull() ?: return cachedResolved?.let { listOf(it) } ?: emptyList()
            writeResolved(cacheKey, best)
            // Persist EVERY probed candidate as a binding, regardless of
            // whether it had this episode. A provider may split a show into
            // multiple catalog entries (e.g. one with only S04, another with
            // S01-S03) — caching every name-matched entry means future
            // episode lookups can still find the right series. Merge with
            // existing bindings so we never lose ones discovered earlier.
            val probedSeriesIds = probeList.map { it.entry.seriesId }
            val mergedBindings = (boundSeriesIds + probedSeriesIds).distinct()
            writeSeriesBinding(bindingKeys, mergedBindings)
            val winnerCount = resolved.map { it.seriesId }.distinct().size
            System.err.println("[VOD-Resolver] Resolved ${resolved.size} variants ($winnerCount/${mergedBindings.size} bound series had this episode) via ${best.method} (conf=${best.confidence}) in ${System.currentTimeMillis() - resolveStart}ms")
            return resolved
        }

        private suspend fun loadCatalog(
            providerKey: String,
            creds: XtreamCredentials,
            allowNetwork: Boolean,
            forceRefresh: Boolean
        ): ResolverCatalogIndex {
            // Fast path: check in-memory cache (no lock needed)
            val now = System.currentTimeMillis()
            val inMem = catalogMemory[providerKey]
            if (!forceRefresh && inMem != null && now - inMem.createdAtMs < catalogTtlMs) return inMem

            if (!allowNetwork) {
                if (inMem != null) return inMem
                // Try SharedPreferences for stale data
                val persisted = readPersistedCatalog(providerKey)
                if (persisted != null) {
                    val built = buildCatalogIndex(persisted.createdAtMs, persisted.entries)
                    catalogMemory[providerKey] = built
                    return built
                }
                return ResolverCatalogIndex(now, emptyList(), emptyMap(), emptyMap(), emptyMap(), emptyMap())
            }

            // Serialize catalog builds — only one thread does the expensive work,
            // others wait and get the result from memory.
            // Use NonCancellable so a cancelled coroutine doesn't abandon the build
            // while holding the mutex, leaving catalogMemory empty for everyone.
            return withContext(kotlinx.coroutines.NonCancellable) { catalogLoadMutex.withLock {
                // Re-check memory inside lock — another thread may have built it while we waited
                val afterLockMem = catalogMemory[providerKey]
                val lockNow = System.currentTimeMillis()
                if (!forceRefresh && afterLockMem != null && lockNow - afterLockMem.createdAtMs < catalogTtlMs) {
                    System.err.println("[VOD-Resolver] loadCatalog: found in memory after lock wait (${afterLockMem.entries.size} entries)")
                    return@withLock afterLockMem
                }

                // Try SharedPreferences persisted catalog
                var stalePersisted: ResolverPersistedCatalog? = null
                if (!forceRefresh) {
                    val persisted = readPersistedCatalog(providerKey)
                    if (persisted != null) {
                        stalePersisted = persisted
                        if (lockNow - persisted.createdAtMs < catalogTtlMs) {
                            System.err.println("[VOD-Resolver] loadCatalog: building from persisted prefs (${persisted.entries.size} entries)")
                            val built = buildCatalogIndex(persisted.createdAtMs, persisted.entries)
                            catalogMemory[providerKey] = built
                            return@withLock built
                        }
                    }
                }

                System.err.println("[VOD-Resolver] loadCatalog: fetching series list from network...")
                val fetchStart = System.currentTimeMillis()
                val entries = withTimeoutOrNull(45_000L) {
                    val rawList = getXtreamSeriesList(creds, allowNetwork = true, fast = false)
                    System.err.println("[VOD-Resolver] loadCatalog: got ${rawList.size} raw series in ${System.currentTimeMillis() - fetchStart}ms, building entries...")
                    rawList.mapNotNull { item ->
                        val seriesId = item.seriesId ?: return@mapNotNull null
                        val name = item.name?.trim().orEmpty()
                        if (name.isBlank()) return@mapNotNull null
                        val normalizedName = normalizeLookupText(name)
                        val tokens = extractTitleTokensFromNormalized(normalizedName)
                        ResolverSeriesEntry(
                            seriesId = seriesId,
                            name = name,
                            normalizedName = normalizedName,
                            canonicalTitleKey = toCanonicalTitleKeyFromTokens(tokens),
                            titleTokens = tokens,
                            tmdb = normalizeTmdbId(item.tmdb),
                            imdb = normalizeImdbId(item.imdb),
                            year = parseYear(item.name ?: "")
                        )
                    }
                }.orEmpty()
                System.err.println("[VOD-Resolver] loadCatalog: entries=${entries.size} in ${System.currentTimeMillis() - fetchStart}ms")

                if (entries.isEmpty()) {
                    val stale = stalePersisted
                    if (stale != null && stale.entries.isNotEmpty()) {
                        System.err.println("[VOD-Resolver] loadCatalog: network empty, using stale persisted (${stale.entries.size})")
                        val built = buildCatalogIndex(stale.createdAtMs, stale.entries)
                        catalogMemory[providerKey] = built
                        return@withLock built
                    }
                }

                val buildStart = System.currentTimeMillis()
                val built = buildCatalogIndex(lockNow, entries)
                System.err.println("[VOD-Resolver] loadCatalog: buildCatalogIndex took ${System.currentTimeMillis() - buildStart}ms")
                catalogMemory[providerKey] = built
                // Persist to SharedPreferences in background — don't block resolution
                if (entries.isNotEmpty() && entries.size <= 50_000) {
                    runCatching {
                        val payload = ResolverPersistedCatalog(
                            formatVersion = catalogFormatVersion,
                            createdAtMs = lockNow,
                            entries = entries
                        )
                        prefs.edit().putString(catalogPrefKey(providerKey), gson.toJson(payload)).apply()
                    }
                }
                built
            } }
        }

        /**
         * The canonical key plus — when the entry carries a transcribed umlaut —
         * the transliterated alias, so "Fuer alle Faelle" is also reachable under
         * the key a query for "Für alle Fälle" produces.
         */
        private fun canonicalTitleKeysFor(entry: ResolverSeriesEntry): List<String> {
            val primary = entry.canonicalTitleKey
            if (primary.isBlank()) return emptyList()
            val alias = toCanonicalTitleKeyFromTokens(umlautAliasTokens(entry.titleTokens))
            return if (alias.isBlank() || alias == primary) listOf(primary) else listOf(primary, alias)
        }

        private fun buildCatalogIndex(createdAtMs: Long, entries: List<ResolverSeriesEntry>): ResolverCatalogIndex {
            val normalizedEntries = entries.map { entry ->
                val normalizedName = entry.normalizedName.ifBlank { normalizeLookupText(entry.name) }
                val titleTokens = if (entry.titleTokens.isEmpty()) extractTitleTokensFromNormalized(normalizedName) else entry.titleTokens
                val canonicalTitleKey = entry.canonicalTitleKey.ifBlank { toCanonicalTitleKeyFromTokens(titleTokens) }
                entry.copy(
                    normalizedName = normalizedName,
                    canonicalTitleKey = canonicalTitleKey,
                    // Alias tokens are index-only: they widen what a query can find
                    // without changing the persisted entry or any query key.
                    titleTokens = withUmlautAliasTokens(titleTokens)
                )
            }
            // IMPORTANT: Normalize keys so lookup matches correctly
            val tmdbMap = normalizedEntries
                .filter { !it.tmdb.isNullOrBlank() }
                .groupBy { normalizeTmdbId(it.tmdb)!! }
            val imdbMap = normalizedEntries
                .filter { !it.imdb.isNullOrBlank() }
                .groupBy { normalizeImdbId(it.imdb)!! }
            val canonicalTitleMap = normalizedEntries
                .filter { it.canonicalTitleKey.isNotBlank() }
                .flatMap { entry -> canonicalTitleKeysFor(entry).map { key -> key to entry } }
                .groupBy({ it.first }, { it.second })
            val tokenMap = buildMap<String, List<ResolverSeriesEntry>> {
                val temp = LinkedHashMap<String, MutableList<ResolverSeriesEntry>>()
                normalizedEntries.forEach { entry ->
                    entry.titleTokens.forEach { token ->
                        temp.getOrPut(token) { mutableListOf() }.add(entry)
                    }
                }
                temp.forEach { (token, tokenEntries) ->
                    put(token, tokenEntries.distinctBy { it.seriesId })
                }
            }
            return ResolverCatalogIndex(
                createdAtMs = createdAtMs,
                entries = normalizedEntries,
                tmdbMap = tmdbMap,
                imdbMap = imdbMap,
                canonicalTitleMap = canonicalTitleMap,
                tokenMap = tokenMap
            )
        }

        private fun buildCandidates(
            catalog: ResolverCatalogIndex,
            normalizedShow: String,
            normalizedTmdb: String?,
            normalizedImdb: String?,
            inputYear: Int?
        ): List<ResolverCandidate> {
            val out = LinkedHashMap<Int, ResolverCandidate>()

            if (!normalizedTmdb.isNullOrBlank()) {
                catalog.tmdbMap[normalizedTmdb].orEmpty().forEach { entry ->
                    out[entry.seriesId] = ResolverCandidate(entry, confidence = 0.98f, method = "tmdb_id", baseScore = 20_000)
                }
            }
            if (!normalizedImdb.isNullOrBlank()) {
                catalog.imdbMap[normalizedImdb].orEmpty().forEach { entry ->
                    val prev = out[entry.seriesId]
                    if (prev == null || prev.confidence < 0.99f) {
                        out[entry.seriesId] = ResolverCandidate(entry, confidence = 0.99f, method = "imdb_id", baseScore = 21_000)
                    }
                }
            }

            if (normalizedShow.isNotBlank()) {
                val canonicalShow = toCanonicalTitleKey(normalizedShow)
                if (canonicalShow.isNotBlank()) {
                    catalog.canonicalTitleMap[canonicalShow].orEmpty().forEach { entry ->
                        val yearDelta = when {
                            inputYear == null || entry.year == null -> 0
                            else -> kotlin.math.abs(inputYear - entry.year)
                        }
                        if (yearDelta > 1) return@forEach
                        val total = when (yearDelta) {
                            0 -> 18_000
                            1 -> 17_500
                            else -> 17_200
                        }
                        val confidence = when (yearDelta) {
                            0 -> 0.93f
                            1 -> 0.90f
                            else -> 0.88f
                        }
                        val existing = out[entry.seriesId]
                        if (existing == null || total > existing.baseScore) {
                            out[entry.seriesId] = ResolverCandidate(entry, confidence = confidence, method = "title_canonical", baseScore = total)
                        }
                    }
                }

                val queryTokens = extractTitleTokens(normalizedShow)
                if (queryTokens.isNotEmpty()) {
                    val candidatePool = LinkedHashMap<Int, ResolverSeriesEntry>()
                    queryTokens.forEach { token ->
                        catalog.tokenMap[token].orEmpty().forEach { entry ->
                            candidatePool[entry.seriesId] = entry
                        }
                    }
                    candidatePool.values.forEach { entry ->
                        val overlap = entry.titleTokens.intersect(queryTokens).size
                        if (overlap <= 0) return@forEach
                        val coverage = overlap.toFloat() / queryTokens.size.toFloat()
                        val accepted = if (queryTokens.size == 1) {
                            coverage >= 1f
                        } else {
                            overlap >= 2 || coverage >= 0.6f
                        }
                        if (!accepted) return@forEach
                        val yearDelta = when {
                            inputYear == null || entry.year == null -> 0
                            else -> kotlin.math.abs(inputYear - entry.year)
                        }
                        if (yearDelta > 1) return@forEach
                        val yearScore = when (yearDelta) {
                            0 -> 120
                            1 -> 70
                            else -> 35
                        }
                        val total = (coverage * 1_000f).toInt() + (overlap * 180) + yearScore
                        val confidence = when {
                            coverage >= 1f && overlap >= 2 -> 0.86f
                            coverage >= 0.8f -> 0.82f
                            else -> 0.76f
                        }
                        val existing = out[entry.seriesId]
                        if (existing == null || total > existing.baseScore) {
                            out[entry.seriesId] = ResolverCandidate(entry, confidence = confidence, method = "title_tokens", baseScore = total)
                        }
                    }
                }
            }

            return out.values
                .sortedWith(compareByDescending<ResolverCandidate> { it.confidence }.thenByDescending { it.baseScore })
        }

        private suspend fun loadSeriesInfo(
            providerKey: String,
            creds: XtreamCredentials,
            seriesId: Int,
            allowNetwork: Boolean
        ): List<XtreamSeriesEpisode> {
            val key = "$providerKey|$seriesId"
            synchronized(seriesInfoLock) {
                val cached = seriesInfoMemory[key]
                if (!cached.isNullOrEmpty()) return cached
            }
            val persisted = runCatching {
                gson.fromJson(
                    prefs.getString(seriesInfoPrefKey(providerKey, seriesId), null),
                    ResolverPersistedSeriesInfo::class.java
                )
            }.getOrNull()
            if (persisted != null &&
                persisted.episodes.isNotEmpty() &&
                System.currentTimeMillis() - persisted.savedAtMs < seriesInfoTtlMs
            ) {
                synchronized(seriesInfoLock) {
                    seriesInfoMemory[key] = persisted.episodes
                }
                return persisted.episodes
            }
            val episodes = withTimeoutOrNull(10_000L) {
                getXtreamSeriesEpisodes(creds, seriesId, allowNetwork = allowNetwork, fast = false)
            }.orEmpty()
            if (episodes.isNotEmpty()) {
                synchronized(seriesInfoLock) {
                    seriesInfoMemory[key] = episodes
                }
                runCatching {
                    prefs.edit().putString(
                        seriesInfoPrefKey(providerKey, seriesId),
                        gson.toJson(
                            ResolverPersistedSeriesInfo(
                                savedAtMs = System.currentTimeMillis(),
                                episodes = episodes
                            )
                        )
                    ).apply()
                }
            }
            return episodes
        }

        private fun matchEpisode(
            episodes: List<XtreamSeriesEpisode>,
            requestedSeason: Int,
            requestedEpisode: Int
        ): ResolverEpisodeHit? = matchEpisodes(episodes, requestedSeason, requestedEpisode).firstOrNull()

        private fun matchEpisodes(
            episodes: List<XtreamSeriesEpisode>,
            requestedSeason: Int,
            requestedEpisode: Int
        ): List<ResolverEpisodeHit> {
            if (episodes.isEmpty()) return emptyList()

            // Exact season/episode is the only high-confidence match.
            val exact = episodes.filter { it.season == requestedSeason && it.episode == requestedEpisode }
            if (exact.isNotEmpty()) {
                return exact.map { ResolverEpisodeHit(it, score = 1000) }
            }

            // If provider clearly has the requested season, do not cross-match to another season.
            if (episodes.any { it.season == requestedSeason }) {
                return emptyList()
            }

            // Flattened providers sometimes expose all episodes as season 1 (or 0).
            // Allow this only when there is a single unambiguous episode-number match.
            val sameEpisode = episodes.filter { it.episode == requestedEpisode }
            val flattened = episodes.all { it.season <= 1 }
            if (flattened && sameEpisode.size == 1) {
                return listOf(ResolverEpisodeHit(sameEpisode.first(), score = 640))
            }
            return emptyList()
        }

        private fun buildResolvedCacheKey(
            providerKey: String,
            tmdb: String?,
            imdb: String?,
            normalizedTitle: String,
            season: Int,
            episode: Int
        ): String {
            return listOf(
                providerKey,
                tmdb.orEmpty(),
                imdb.orEmpty(),
                normalizedTitle,
                season.toString(),
                episode.toString()
            ).joinToString("|")
        }

        private fun readResolved(key: String): ResolverCachedResolvedEpisode? {
            synchronized(resolvedLock) {
                resolvedMemory[key]?.let { return it }
            }
            val raw = prefs.getString(resolvedPrefKey, null) ?: return null
            val persisted = runCatching { gson.fromJson(raw, ResolverPersistedResolved::class.java) }.getOrNull() ?: return null
            val hit = persisted.items[key] ?: return null
            if (System.currentTimeMillis() - hit.savedAtMs > resolvedTtlMs) return null
            synchronized(resolvedLock) { resolvedMemory[key] = hit }
            return hit
        }

        private fun writeResolved(key: String, value: ResolverCachedResolvedEpisode) {
            synchronized(resolvedLock) {
                resolvedMemory[key] = value
            }
            val existingRaw = prefs.getString(resolvedPrefKey, null)
            val existing = runCatching { gson.fromJson(existingRaw, ResolverPersistedResolved::class.java) }.getOrNull()
                ?: ResolverPersistedResolved()
            val merged = LinkedHashMap(existing.items)
            merged[key] = value
            while (merged.size > 512) {
                val oldest = merged.entries.minByOrNull { it.value.savedAtMs }?.key ?: break
                merged.remove(oldest)
            }
            runCatching {
                prefs.edit().putString(resolvedPrefKey, gson.toJson(ResolverPersistedResolved(merged))).apply()
            }
        }

        private fun buildSeriesBindingKeys(
            providerKey: String,
            normalizedTmdb: String?,
            normalizedImdb: String?,
            normalizedShow: String
        ): List<String> {
            val keys = mutableListOf<String>()
            if (!normalizedTmdb.isNullOrBlank()) keys += "$providerKey|tmdb:$normalizedTmdb"
            if (!normalizedImdb.isNullOrBlank()) keys += "$providerKey|imdb:$normalizedImdb"
            val canonicalShow = toCanonicalTitleKey(normalizedShow)
            if (canonicalShow.isNotBlank()) keys += "$providerKey|title:$canonicalShow"
            return keys.distinct()
        }

        /**
         * Returns the union of bound series IDs across all binding keys. The
         * order preserves match-confidence priority (TMDB-id keys come before
         * IMDb keys, then title keys — see [buildSeriesBindingKeys]).
         */
        private fun readSeriesBinding(keys: List<String>): List<Int> {
            if (keys.isEmpty()) return emptyList()
            val combined = LinkedHashSet<Int>()
            synchronized(seriesBindingLock) {
                keys.forEach { key ->
                    seriesBindingMemory[key]?.forEach { combined.add(it) }
                }
                if (combined.isNotEmpty()) return combined.toList()
                // Read prefs inside the lock: prevents two concurrent IO threads from
                // racing to populate seriesBindingMemory from the same prefs blob.
                val raw = prefs.getString(seriesBindingPrefKey, null) ?: return emptyList()
                val persisted = runCatching {
                    gson.fromJson(raw, ResolverPersistedSeriesBindings::class.java)
                }.getOrNull() ?: return emptyList()
                keys.forEach { key ->
                    val ids = persisted.items[key].orEmpty()
                    if (ids.isNotEmpty()) {
                        seriesBindingMemory[key] = ids
                        ids.forEach { combined.add(it) }
                    }
                }
                return combined.toList()
            }
        }

        private fun writeSeriesBinding(keys: List<String>, seriesIds: List<Int>) {
            if (keys.isEmpty() || seriesIds.isEmpty()) return
            // Merge new IDs with whatever is already stored per key before capping,
            // so a subsequent call never loses bindings discovered in a prior resolve.
            synchronized(seriesBindingLock) {
                keys.forEach { key ->
                    val existing = seriesBindingMemory[key].orEmpty()
                    seriesBindingMemory[key] = (existing + seriesIds).distinct().take(maxSeriesBindingsPerKey)
                }
            }
            val existingRaw = prefs.getString(seriesBindingPrefKey, null)
            val existing = runCatching {
                gson.fromJson(existingRaw, ResolverPersistedSeriesBindings::class.java)
            }.getOrNull() ?: ResolverPersistedSeriesBindings()
            val persisted = LinkedHashMap(existing.items)
            keys.forEach { key ->
                val existingIds = persisted[key].orEmpty()
                persisted[key] = (existingIds + seriesIds).distinct().take(maxSeriesBindingsPerKey)
            }
            while (persisted.size > 2048) {
                val oldestKey = persisted.keys.firstOrNull() ?: break
                persisted.remove(oldestKey)
            }
            runCatching {
                prefs.edit().putString(seriesBindingPrefKey, gson.toJson(ResolverPersistedSeriesBindings(persisted))).apply()
            }
        }

        /**
         * Wipe every resolver-owned cache: in-memory catalogs / resolved
         * episodes / series bindings / per-series episode lists, plus the
         * backing SharedPreferences file. Used when the user explicitly
         * refreshes IPTV from Settings — everything is rebuilt on the next
         * resolve.
         */
        fun clearAll() {
            catalogMemory.clear()
            synchronized(resolvedLock) { resolvedMemory.clear() }
            synchronized(seriesBindingLock) { seriesBindingMemory.clear() }
            synchronized(seriesInfoLock) { seriesInfoMemory.clear() }
            runCatching {
                val editor = prefs.edit()
                    .remove(resolvedPrefKey)
                    .remove(seriesBindingPrefKey)
                prefs.all.keys
                    .filter { it.startsWith("catalog_") || it.startsWith("series_info_") }
                    .forEach { editor.remove(it) }
                editor.apply()
            }
        }
    }

    internal fun activeVodPlaylists(config: IptvConfig): List<IptvPlaylistEntry> =
        activePlaylists(config).filter { it.importVod ?: true }

    internal fun activeSeriesPlaylists(config: IptvConfig): List<IptvPlaylistEntry> =
        activePlaylists(config).filter { it.importSeries ?: true }

    private fun xtreamCredentialsForVodImport(config: IptvConfig): List<XtreamCredentials> =
        activeVodPlaylists(config)
            .mapNotNull(::resolveXtreamCredentials)
            .distinct()

    private fun xtreamCredentialsForSeriesImport(config: IptvConfig): List<XtreamCredentials> =
        activeSeriesPlaylists(config)
            .mapNotNull(::resolveXtreamCredentials)
            .distinct()

    suspend fun findMovieVodSource(
        title: String,
        year: Int?,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): StreamSource? = findMovieVodSources(
        title = title,
        year = year,
        imdbId = imdbId,
        tmdbId = tmdbId,
        allowNetwork = allowNetwork
    ).firstOrNull()

    suspend fun findMovieVodSources(
        title: String,
        year: Int?,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true,
        originalTitle: String? = null
    ): List<StreamSource> {
        return withContext(Dispatchers.IO) {
            if (!isVodSearchEnabled()) return@withContext emptyList()
            val config = observeConfig().first()
            val xtreamSources = xtreamCredentialsForVodImport(config)
                .flatMap { creds ->
                    runCatching {
                        findMovieVodSourcesForCredentials(
                            creds = creds,
                            title = title,
                            year = year,
                            imdbId = imdbId,
                            tmdbId = tmdbId,
                            allowNetwork = allowNetwork
                        )
                    }.getOrDefault(emptyList())
                }
            // Additive second provider: each Stalker portal is searched on its
            // own, and a failing portal never removes Xtream results.
            val stalkerSources = activeStalkerPortals(config)
                .flatMap { portal ->
                    runCatching {
                        findStalkerMovieVodSources(
                            portal = portal,
                            title = title,
                            year = year,
                            tmdbId = tmdbId,
                            imdbId = imdbId,
                            allowNetwork = allowNetwork,
                            originalTitle = originalTitle
                        )
                    }.getOrDefault(emptyList())
                }
            sortVodSources(xtreamSources + stalkerSources)
        }
    }

    private suspend fun findMovieVodSourcesForCredentials(
        creds: XtreamCredentials,
        title: String,
        year: Int?,
        imdbId: String?,
        tmdbId: Int?,
        allowNetwork: Boolean
    ): List<StreamSource> {
        val credsFingerprint = xtreamDiskCacheHash(creds)
        val cacheKey = iptvMovieSourceCacheKey(
            profileIdHash = profileIdHash(),
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = title,
            year = year
        )
        if (cacheKey != null) {
            lookupCachedMovieSources(cacheKey, credsFingerprint)?.let { cached ->
                return cached
            }
        }

        val vod = getXtreamVodStreams(creds, allowNetwork, fast = true)
        if (vod.isEmpty()) return emptyList()

        val normalizedTmdb = normalizeTmdbId(tmdbId)
        val normalizedImdb = normalizeImdbId(imdbId)
            ?.takeIf { it != IptvIdSentinels.IMDB_NONE }

        // Keep the existing matching algorithm isolated per provider so each
        // provider's credentials and catalog cache remain paired correctly.
        fun finalizeIdMatches(
            matches: List<XtreamVodStream>,
            fallbackTitle: String
        ): List<StreamSource> {
            val sources = sortVodSources(
                matches.mapNotNull { it.toMovieVodSource(creds, title.ifBlank { fallbackTitle }) }
            )
            if (cacheKey != null && sources.isNotEmpty()) {
                storeCachedMovieSources(cacheKey, sources, credsFingerprint)
            }
            return sources
        }

        // ID-only index built at catalog-load gives O(1) ID lookup.
        val idIndex = cachedVodIdIndex?.takeIf { it.items === vod }
            ?: buildVodIdIndex(vod).also { cachedVodIdIndex = it }
        if (!normalizedTmdb.isNullOrBlank()) {
            val hits = idIndex.tmdbMap[normalizedTmdb].orEmpty()
            if (hits.isNotEmpty()) {
                return finalizeIdMatches(hits.map { idIndex.items[it] }, normalizedTmdb)
            }
        }
        if (!normalizedImdb.isNullOrBlank()) {
            val hits = idIndex.imdbMap[normalizedImdb].orEmpty()
            if (hits.isNotEmpty()) {
                return finalizeIdMatches(hits.map { idIndex.items[it] }, normalizedImdb)
            }
        }

        val normalizedTitle = normalizeLookupText(title)
        if (normalizedTitle.isBlank()) return emptyList()
        val inputYear = year ?: parseYear(title)

        // Title fallback: build (or reuse) the indexed catalog. Expensive
        // first time but reused for subsequent title-only queries.
        val filteredIndex = ensureVodCatalogIndex(vod)
        val matches = findMovieCandidatesIndexed(
            filteredIndex,
            normalizedTitle,
            normalizedTmdb,
            normalizedImdb,
            inputYear
        )

        if (matches.isEmpty()) return emptyList()

        val sources = sortVodSources(
            matches.mapNotNull {
                it.toMovieVodSource(
                    creds,
                    title.ifBlank { normalizedTmdb ?: normalizedImdb.orEmpty() }
                )
            }
        )
        if (cacheKey != null && sources.isNotEmpty()) {
            storeCachedMovieSources(cacheKey, sources, credsFingerprint)
        }
        return sources
    }

    // ── Stalker VOD (movies) ────────────────────────────────────────────────

    /**
     * Stalker counterpart to [findMovieVodSourcesForCredentials].
     *
     * Unlike Xtream, a Stalker portal serves its catalog only in small pages
     * (14 entries on a stock Ministra build), so downloading and indexing the
     * whole catalog locally would mean hundreds of requests per refresh on a
     * large portal - exactly the request pattern that gets users throttled or
     * IP-banned by their provider. The portal's own `search` narrows the same
     * endpoint server-side instead, and the handful of entries that come back
     * is scored with the same TMDB-id / title+year matching the Xtream path
     * uses.
     */
    private suspend fun findStalkerMovieVodSources(
        portal: StalkerPortalEntry,
        title: String,
        year: Int?,
        tmdbId: Int?,
        imdbId: String?,
        allowNetwork: Boolean,
        originalTitle: String? = null
    ): List<StreamSource> {
        if (portal.portalUrl.isBlank() || portal.macAddress.isBlank()) return emptyList()
        val fingerprint = stalkerPortalFingerprint(portal)
        // Portal id and fingerprint are both part of the key: two portals never
        // read each other's matches, and re-pointing a portal at another server
        // invalidates only that portal's entries.
        val cacheKey = iptvMovieSourceCacheKey(
            profileIdHash = profileIdHash(),
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = title,
            year = year
        )?.let { base -> "stalker|${portal.id}|$base" }
        if (cacheKey != null) {
            lookupCachedMovieSources(cacheKey, fingerprint)?.let { return it }
        }
        // Nothing to fall back on offline: there is no local Stalker catalog,
        // the cached result above is the only network-free answer.
        if (!allowNetwork) return emptyList()

        val normalizedTitle = normalizeLookupText(title)
        if (normalizedTitle.isBlank()) return emptyList()
        val api = getOrCreateStalkerApi(portal) ?: return emptyList()

        val normalizedTmdb = normalizeTmdbId(tmdbId)
        val normalizedOriginalTitle = normalizeLookupText(originalTitle.orEmpty())
            .takeIf { it.isNotBlank() && it != normalizedTitle }
        val inputYear = year ?: parseYear(title)

        var matches: List<com.arflix.tv.data.api.StalkerApi.StalkerVodItem> = emptyList()
        // Counted separately from the matches: portals that ignore `search` answer
        // every query with the head of their whole catalogue, so a high offered
        // count next to zero matches names the portal as the cause, whereas both
        // at zero points at the request or the portal's catalogue.
        var offered = 0
        for (query in stalkerVodSearchQueries(title, originalTitle)) {
            val items = stalkerVodSearch(portal, fingerprint, api, query)
            offered += items.size
            if (items.isEmpty()) continue
            matches = matchStalkerVodItems(
                items = items,
                normalizedTitle = normalizedTitle,
                normalizedTmdb = normalizedTmdb,
                inputYear = inputYear,
                normalizedOriginalTitle = normalizedOriginalTitle
            )
            if (matches.isNotEmpty()) break
        }
        System.err.println(
            "[Stalker-VOD] portal=${portal.id} title='$title' " +
                "offered=$offered matches=${matches.size}"
        )
        if (matches.isEmpty()) return emptyList()

        val sources = sortVodSources(
            matches.mapNotNull { item ->
                item.toStalkerMovieVodSource(portal, title.ifBlank { normalizedTmdb.orEmpty() })
            }
        )
        if (cacheKey != null && sources.isNotEmpty()) {
            storeCachedMovieSources(cacheKey, sources, fingerprint)
        }
        return sources
    }

    /** Empty answers expire quickly, real hits keep the long TTL. */
    private fun cacheTtlFor(items: List<*>): Long =
        if (items.isEmpty()) stalkerVodSearchEmptyCacheTtlMs else stalkerVodSearchCacheTtlMs

    /**
     * The terms one portal lookup may spend, most likely first. The caller
     * stops at the first term that produced a match, so the later ones only
     * cost a request when the earlier ones found nothing.
     *
     * A portal matches `search` literally against its own catalog name, and
     * that name is not the name TMDB shows the user. Measured against a real
     * portal: TMDB says "Der Astronaut - Project Hail Mary" with an en dash,
     * the catalog lists "DE - Der Astronaut: Project Hail Mary (2026)" with a
     * colon, and the literal search therefore answers with nothing at all -
     * while the same film sits in that catalog eleven times under its original
     * title. Hence three terms, none of which is enough on its own:
     *
     *  1. [originalTitle] - most catalog entries are listed under the original
     *     name, so this is the term that hits first most of the time.
     *  2. [title] as displayed - the only term that finds an entry a panel
     *     carries purely localized: "Die Verurteilten" does not contain
     *     "The Shawshank Redemption" anywhere.
     *  3. The part in front of a subtitle separator - the rescue anchor for
     *     the punctuation mismatch above, and for panels that list "Dune"
     *     where TMDB says "Dune: Part Two".
     *
     * Costs nothing in the common case: when a user browses in the original
     * language, terms 1 and 2 are the same string and only one request goes
     * out, exactly as before.
     */
    internal fun stalkerVodSearchQueries(
        title: String,
        originalTitle: String? = null
    ): List<String> {
        val queries = mutableListOf<String>()
        fun add(candidate: String) {
            val term = candidate.trim()
            if (term.isBlank()) return
            // Case-insensitive: a portal search is case-insensitive too, so a
            // second spelling of the same term would only buy a second
            // identical answer.
            if (queries.any { it.equals(term, ignoreCase = true) }) return
            queries += term
        }

        add(originalTitle.orEmpty())
        val primary = title.trim()
        add(primary)

        // Derived, not given: only used when it still names the film. Two
        // characters ("It: Chapter Two" -> "It") would ask the portal for a
        // slice of its whole catalog instead.
        val head = primary.subtitleHead()
        if (head.length >= minStalkerVodQueryHeadLength) add(head)

        return queries
    }

    /**
     * Everything in front of the first subtitle separator.
     *
     * The dashes are spaced on purpose: an unspaced hyphen belongs to names
     * like "Spider-Man", and an unspaced en dash to year ranges. The en and em
     * dash are in the list because TMDB writes German subtitles with them
     * while portals write a colon - the exact mismatch this whole helper is
     * about.
     */
    private fun String.subtitleHead(): String =
        substringBefore(':')
            .substringBefore(" - ")
            .substringBefore(" – ")
            .substringBefore(" — ")
            .trim()

    private suspend fun stalkerVodSearch(
        portal: StalkerPortalEntry,
        fingerprint: String,
        api: com.arflix.tv.data.api.StalkerApi,
        query: String
    ): List<com.arflix.tv.data.api.StalkerApi.StalkerVodItem> {
        val term = query.trim()
        if (term.isBlank()) return emptyList()
        val key = StalkerVodSearchCacheKey(portal.id, fingerprint, term.lowercase(Locale.US))
        val now = System.currentTimeMillis()
        stalkerVodSearchCache[key]?.let { cached ->
            if (now - cached.fetchedAtMs < cacheTtlFor(cached.items)) return cached.items
            stalkerVodSearchCache.remove(key)
        }
        // null means the request itself failed. Caching that would turn one
        // bad moment into hours of "this portal has no such film".
        val items = api.searchVod(term) ?: return emptyList()
        if (stalkerVodSearchCache.size >= maxStalkerVodSearchCacheEntries) {
            // Bounded on purpose: one answer is small, but a long browsing
            // session must not accumulate an entry per looked-up movie.
            stalkerVodSearchCache.clear()
        }
        stalkerVodSearchCache[key] = StalkerVodSearchCacheEntry(now, items)
        return items
    }

    /** Movie entries of a portal search, scored by [matchStalkerCatalogEntries]. */
    internal fun matchStalkerVodItems(
        items: List<com.arflix.tv.data.api.StalkerApi.StalkerVodItem>,
        normalizedTitle: String,
        normalizedTmdb: String?,
        inputYear: Int?,
        normalizedOriginalTitle: String? = null
    ): List<com.arflix.tv.data.api.StalkerApi.StalkerVodItem> = matchStalkerCatalogEntries(
        items = items,
        normalizedTitle = normalizedTitle,
        normalizedTmdb = normalizedTmdb,
        inputYear = inputYear,
        normalizedOriginalTitle = normalizedOriginalTitle
    ) { StalkerCatalogFields(it.name, it.cmd, it.year, it.tmdbId) }

    /** The fields a Stalker catalog entry is scored on. */
    private data class StalkerCatalogFields(
        val name: String?,
        val cmd: String?,
        val year: String?,
        val tmdbId: String?
    )

    /**
     * Scores portal entries against a wanted title. Written over the entry's
     * fields rather than over one item type: `get_ordered_list` answers with
     * the same four fields for every catalog it serves.
     *
     * Two stages, as on the Xtream path: a portal-supplied `tmdb_id` wins
     * outright, otherwise entries are scored on their title with the existing
     * [scoreNameMatch] plus the year bonus/penalty and score window
     * [findMovieCandidatesIndexed] applies. Entries without a `cmd` are dropped
     * either way - there would be nothing to play.
     *
     * [normalizedOriginalTitle] is scored as an equal alternative, not as a
     * fallback: [stalkerVodSearchQueries] asks the portal for the original
     * title as well, and a catalog listing the film only under that name -
     * "EN - Project Hail Mary (2026)" for a user browsing in German - would
     * otherwise be found and then thrown away. Both names denote the same
     * film, so the better of the two scores is the entry's score. Portals that
     * supply a `tmdb_id` never reach this stage.
     */
    private fun <T> matchStalkerCatalogEntries(
        items: List<T>,
        normalizedTitle: String,
        normalizedTmdb: String?,
        inputYear: Int?,
        normalizedOriginalTitle: String? = null,
        fields: (T) -> StalkerCatalogFields
    ): List<T> {
        if (items.isEmpty()) return emptyList()
        if (!normalizedTmdb.isNullOrBlank()) {
            val idMatches = items.filter { normalizeTmdbId(fields(it).tmdbId) == normalizedTmdb }
            if (idMatches.isNotEmpty()) return idMatches
        }
        val wantedNames = listOfNotNull(
            normalizedTitle.takeIf { it.isNotBlank() },
            normalizedOriginalTitle?.takeIf { it.isNotBlank() }
        ).distinct()
        if (wantedNames.isEmpty()) return emptyList()

        val scored = items
            .mapNotNull { item ->
                val entry = fields(item)
                val itemName = entry.name?.trim().orEmpty()
                if (itemName.isBlank()) return@mapNotNull null
                if (entry.cmd.isNullOrBlank()) return@mapNotNull null
                val score = wantedNames.maxOf { scoreNameMatch(itemName, it) }
                if (score <= 0) return@mapNotNull null
                val providerYear = parseYear(entry.year?.trim().orEmpty().ifBlank { itemName })
                val yearDelta = if (inputYear != null && providerYear != null) {
                    kotlin.math.abs(providerYear - inputYear)
                } else null
                val yearAdjust = when {
                    yearDelta == null -> 0
                    yearDelta == 0 -> 20
                    yearDelta == 1 -> 8
                    else -> -25
                }
                item to (score + yearAdjust)
            }
            .sortedByDescending { it.second }
        val bestScore = scored.firstOrNull()?.second ?: return emptyList()
        val minScore = maxOf(65, bestScore - 8)
        return scored.takeWhile { it.second >= minScore }.map { it.first }
    }

    private fun com.arflix.tv.data.api.StalkerApi.StalkerVodItem.toStalkerMovieVodSource(
        portal: StalkerPortalEntry,
        fallbackTitle: String
    ): StreamSource? {
        val marker = StalkerVodLink.buildMarker(portal.id, cmd.orEmpty()) ?: return null
        val sourceName = name?.trim().orEmpty().ifBlank { fallbackTitle }
        return StreamSource(
            source = sourceName,
            addonName = "IPTV VOD",
            addonId = IptvVodSourceIds.STALKER,
            quality = stalkerVodQuality(sourceName, hd),
            size = "",
            url = marker,
            description = stalkerVodDescription(portal, time, ratingImdb)
        )
    }

    /**
     * Stalker knows no resolution field - the portal only flags `hd` - so the
     * title is still the better source when it names one. Falling back to the
     * flag at least separates HD entries from the rest.
     */
    private fun stalkerVodQuality(sourceName: String, hdFlag: String?): String {
        val inferred = inferQuality(sourceName)
        if (inferred != "VOD") return inferred
        return if (hdFlag?.trim() == "1") "HD" else "VOD"
    }

    /**
     * The little the portal knows beyond the title, which is what makes two
     * entries of the same movie tellable apart: which portal it came from, how
     * long it runs, and its IMDb rating.
     */
    private fun stalkerVodDescription(
        portal: StalkerPortalEntry,
        runtime: String?,
        ratingImdb: String?
    ): String? {
        val parts = mutableListOf<String>()
        portal.name.trim().takeIf { it.isNotBlank() }?.let(parts::add)
        runtime?.trim()?.takeIf { it.isNotBlank() }?.let { value ->
            val minutes = value.toIntOrNull()
            parts += if (minutes != null && minutes > 0) "$minutes min" else value
        }
        ratingImdb?.trim()?.toDoubleOrNull()?.takeIf { it > 0.0 }?.let { parts += "IMDb $it" }
        return parts.joinToString(" \u00b7 ").ifBlank { null }
    }

    /**
     * Stable, secret-free identity of a portal. Used as the cache fingerprint,
     * so a portal that gets re-pointed at another server or MAC drops its own
     * cached matches without touching any other source.
     */
    private fun stalkerPortalFingerprint(portal: StalkerPortalEntry): String {
        val raw = "${portal.portalUrl.trim().trimEnd('/').lowercase(Locale.ROOT)}|" +
            portal.macAddress.trim().uppercase(Locale.ROOT)
        return MessageDigest.getInstance("MD5").digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    /**
     * Turns the `stalker_vod://` placeholder of a matched movie into a playable
     * URL. Called from [StreamRepository.resolveStreamForPlayback] the moment
     * playback starts - never while a source list is being built.
     */
    suspend fun resolveStalkerVodStreamUrl(markerUrl: String): String? {
        val (portalId, command) = StalkerVodLink.parseMarker(markerUrl) ?: return null
        val config = observeConfig().first()
        // No "fall back to the first portal" here: the marker always carries the
        // portal it came from, and guessing would resolve against a stranger.
        val portal = config.stalkerPortals.firstOrNull { it.id == portalId } ?: return null
        if (portal.portalUrl.isBlank() || portal.macAddress.isBlank()) return null
        val api = getOrCreateStalkerApi(portal) ?: return null
        val resolved = api.resolveVodStreamUrl(command)
        System.err.println(
            "[Stalker-VOD] create_link portal=$portalId resolved=${!resolved.isNullOrBlank()}"
        )
        return resolved
    }

    suspend fun findEpisodeVodSource(
        title: String,
        season: Int,
        episode: Int,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): StreamSource? = findEpisodeVodSources(
        title = title,
        season = season,
        episode = episode,
        imdbId = imdbId,
        tmdbId = tmdbId,
        allowNetwork = allowNetwork
    ).firstOrNull()

    suspend fun findEpisodeVodSources(
        title: String,
        season: Int,
        episode: Int,
        imdbId: String? = null,
        tmdbId: Int? = null,
        allowNetwork: Boolean = true
    ): List<StreamSource> {
        return withContext(Dispatchers.IO) {
            if (!isVodSearchEnabled()) return@withContext emptyList()
            val config = observeConfig().first()
            xtreamCredentialsForSeriesImport(config)
                .flatMap { creds ->
                    runCatching {
                        findEpisodeVodSourcesForCredentials(
                            creds = creds,
                            title = title,
                            season = season,
                            episode = episode,
                            imdbId = imdbId,
                            tmdbId = tmdbId,
                            allowNetwork = allowNetwork
                        )
                    }.getOrDefault(emptyList())
                }
                .let(::sortVodSources)
        }
    }

    private suspend fun findEpisodeVodSourcesForCredentials(
        creds: XtreamCredentials,
        title: String,
        season: Int,
        episode: Int,
        imdbId: String?,
        tmdbId: Int?,
        allowNetwork: Boolean
    ): List<StreamSource> {
        val normalizedTitle = normalizeLookupText(title)
        val normalizedImdb = normalizeImdbId(imdbId)
        val normalizedTmdb = normalizeTmdbId(tmdbId)
        if (normalizedTitle.isBlank() && normalizedImdb.isNullOrBlank() && normalizedTmdb.isNullOrBlank()) {
            return emptyList()
        }
        val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
        val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"

        fun List<ResolverCachedResolvedEpisode>.toSeriesVodSources(): List<StreamSource> {
            return map { resolved ->
                val resolvedTitle = resolved.title?.trim().orEmpty()
                val ext = resolved.containerExtension?.trim()?.ifBlank { null } ?: "mp4"
                val streamUrl = "${creds.baseUrl}/series/${creds.username}/${creds.password}/${resolved.streamId}.$ext"
                val sourceName = resolvedTitle.ifBlank { "$title S${season}E${episode}" }
                StreamSource(
                    source = sourceName,
                    addonName = "IPTV Series VOD",
                    addonId = IptvVodSourceIds.XTREAM,
                    quality = inferQuality(sourceName),
                    size = "",
                    url = streamUrl
                )
            }
        }

        // FAST PATH: stored series bindings + cached episodes, scoped to this provider.
        val fastResolved = seriesResolver.tryFastResolveEpisodeFromCache(
            providerKey = providerKey,
            showTitle = title,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            imdbId = imdbId
        )
        if (fastResolved.isNotEmpty()) {
            return sortVodSources(fastResolved.toSeriesVodSources())
        }

        val cachedVodCatalogSources = findEpisodeVodFromVodCatalogFallbackSources(
            creds = creds,
            title = title,
            season = season,
            episode = episode,
            normalizedImdb = normalizedImdb,
            normalizedTmdb = normalizedTmdb,
            allowNetwork = false
        )
        if (cachedVodCatalogSources.isNotEmpty()) {
            return cachedVodCatalogSources
        }

        val cachedSeriesSources = seriesResolver.resolveEpisodeVariants(
            providerKey = providerKey,
            creds = creds,
            showTitle = title,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            imdbId = imdbId,
            year = parseYear(title),
            allowNetwork = false
        ).toSeriesVodSources()

        if (!allowNetwork) {
            return sortVodSources(cachedSeriesSources)
        }

        val networkSeriesSources = seriesResolver.resolveEpisodeVariants(
            providerKey = providerKey,
            creds = creds,
            showTitle = title,
            season = season,
            episode = episode,
            tmdbId = tmdbId,
            imdbId = imdbId,
            year = parseYear(title),
            allowNetwork = true
        ).toSeriesVodSources()
        if (networkSeriesSources.isNotEmpty()) {
            return sortVodSources(networkSeriesSources)
        }

        val vodCatalogSources = findEpisodeVodFromVodCatalogFallbackSources(
            creds = creds,
            title = title,
            season = season,
            episode = episode,
            normalizedImdb = normalizedImdb,
            normalizedTmdb = normalizedTmdb,
            allowNetwork = true
        )
        return sortVodSources(vodCatalogSources + cachedSeriesSources)
    }

    private suspend fun <T> Deferred<T>.awaitWithin(timeoutMs: Long): T? {
        return withTimeoutOrNull(timeoutMs) {
            join()
            awaitSafely()
        }
    }

    private suspend fun <T> Deferred<T>.awaitSafely(): T? {
        return runCatching { await() }.getOrNull()
    }

    private suspend fun findEpisodeVodFromVodCatalogFallback(
        creds: XtreamCredentials,
        title: String,
        season: Int,
        episode: Int,
        normalizedImdb: String?,
        normalizedTmdb: String?,
        allowNetwork: Boolean
    ): StreamSource? = findEpisodeVodFromVodCatalogFallbackSources(
        creds = creds,
        title = title,
        season = season,
        episode = episode,
        normalizedImdb = normalizedImdb,
        normalizedTmdb = normalizedTmdb,
        allowNetwork = allowNetwork
    ).firstOrNull()

    private suspend fun findEpisodeVodFromVodCatalogFallbackSources(
        creds: XtreamCredentials,
        title: String,
        season: Int,
        episode: Int,
        normalizedImdb: String?,
        normalizedTmdb: String?,
        allowNetwork: Boolean
    ): List<StreamSource> {
        val normalizedTitle = normalizeLookupText(title)
        val vod = getXtreamVodStreams(creds, allowNetwork = allowNetwork, fast = true)
        if (vod.isEmpty()) return emptyList()

        val scored = vod.asSequence()
            .mapNotNull { item ->
                val streamId = item.streamId ?: return@mapNotNull null
                val name = item.name?.trim().orEmpty()
                if (name.isBlank()) return@mapNotNull null
                val parsedEpisode = extractSeasonEpisodeFromName(name)
                val episodeOnly = if (parsedEpisode == null) extractEpisodeOnlyFromName(name) else null
                val hasExactSeasonEpisode = parsedEpisode?.let { it.first == season && it.second == episode } == true
                val hasEpisodeOnlyMatch = episodeOnly == episode
                if (!hasExactSeasonEpisode && !hasEpisodeOnlyMatch) return@mapNotNull null

                val imdbScore = if (!normalizedImdb.isNullOrBlank() && normalizeImdbId(item.imdb) == normalizedImdb) 10_000 else 0
                val tmdbScore = if (!normalizedTmdb.isNullOrBlank() && normalizeTmdbId(item.tmdb) == normalizedTmdb) 9_500 else 0
                val titleScore = if (normalizedTitle.isNotBlank()) {
                    maxOf(scoreNameMatch(name, normalizedTitle), looseSeriesTitleScore(name, normalizedTitle))
                } else {
                    0
                }
                // For episode-only patterns (no season marker), require stronger identity if season > 1.
                if (!hasExactSeasonEpisode && season > 1 && imdbScore == 0 && tmdbScore == 0) return@mapNotNull null
                if (imdbScore == 0 && tmdbScore == 0 && titleScore <= 0) return@mapNotNull null
                Triple(item, streamId, imdbScore + tmdbScore + titleScore)
            }
            .sortedByDescending { it.third }
            .toList()
        val bestScore = scored.firstOrNull()?.third ?: return emptyList()
        val minScore = maxOf(45, bestScore - 120)
        return sortVodSources(
            scored
                .asSequence()
                .takeWhile { it.third >= minScore }
                .mapNotNull { it.first.toEpisodeVodSource(creds, "$title S${season}E${episode}") }
                .toList()
        )
    }

    private fun XtreamVodStream.toMovieVodSource(
        creds: XtreamCredentials,
        fallbackTitle: String
    ): StreamSource? {
        val streamId = streamId ?: return null
        val ext = containerExtension?.trim()?.ifBlank { null } ?: "mp4"
        val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"
        val sourceName = name?.trim().orEmpty().ifBlank { fallbackTitle }
        return StreamSource(
            source = sourceName,
            addonName = "IPTV VOD",
            addonId = IptvVodSourceIds.XTREAM,
            quality = inferQuality(sourceName),
            size = "",
            url = streamUrl
        )
    }

    private fun XtreamVodStream.toEpisodeVodSource(
        creds: XtreamCredentials,
        fallbackTitle: String
    ): StreamSource? {
        val streamId = streamId ?: return null
        val ext = containerExtension?.trim()?.ifBlank { null } ?: "mp4"
        val streamUrl = "${creds.baseUrl}/movie/${creds.username}/${creds.password}/$streamId.$ext"
        val sourceName = name?.trim().orEmpty().ifBlank { fallbackTitle }
        return StreamSource(
            source = sourceName,
            addonName = "IPTV Episode VOD",
            addonId = IptvVodSourceIds.XTREAM,
            quality = inferQuality(sourceName),
            size = "",
            url = streamUrl
        )
    }

    private fun sortVodSources(sources: List<StreamSource>): List<StreamSource> {
        return sources
            .filter { !it.url.isNullOrBlank() }
            .distinctBy { "${it.url.orEmpty().trim()}|${it.source.trim()}" }
            .sortedWith(
                compareByDescending<StreamSource> { vodQualityRank(it.quality.ifBlank { it.source }) }
                    .thenByDescending { vodQualityRank(it.source) }
                    .thenBy { it.source.lowercase(Locale.US) }
            )
    }

    /**
     * Background pre-warm for every configured VOD provider, called from the
     * home, TV and settings screens so the first movie lookup after start-up is
     * not the one that pays for the cold caches.
     *
     * Stalker has no catalog to pre-download - its searches run server-side -
     * but the portal handshake does probe up to five base paths before the
     * first request succeeds, so that is what gets warmed here. A new source
     * that skips this path still works, it just silently loses the head start
     * this function exists for.
     */
    suspend fun warmVodCachesIfPossible() {
        withContext(Dispatchers.IO) {
            if (!isVodSearchEnabled()) return@withContext
            val config = observeConfig().first()
            activeStalkerPortals(config).forEach { portal ->
                runCatching { getOrCreateStalkerApi(portal) }
            }
            xtreamCredentialsForVodImport(config).forEach { creds ->
                runCatching {
                    loadXtreamVodStreams(creds)
                }
            }
            xtreamCredentialsForSeriesImport(config).forEach { creds ->
                runCatching {
                    loadXtreamSeriesList(creds)
                    val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
                    val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"
                    seriesResolver.refreshCatalog(providerKey, creds)
                }
            }
        }
    }

    suspend fun prefetchEpisodeVodResolution(
        title: String,
        season: Int,
        episode: Int,
        imdbId: String? = null,
        tmdbId: Int? = null
    ) {
        withContext(Dispatchers.IO) {
            if (!isVodSearchEnabled()) return@withContext
            val config = observeConfig().first()
            val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
            xtreamCredentialsForSeriesImport(config).forEach { creds ->
                val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"
                runCatching {
                    seriesResolver.resolveEpisode(
                        providerKey = providerKey,
                        creds = creds,
                        showTitle = title,
                        season = season,
                        episode = episode,
                        tmdbId = tmdbId,
                        imdbId = imdbId,
                        year = parseYear(title),
                        allowNetwork = true
                    )
                }
            }
        }
    }

    suspend fun prefetchSeriesInfoForShow(
        title: String,
        imdbId: String? = null,
        tmdbId: Int? = null
    ) {
        withContext(Dispatchers.IO) {
            if (!isVodSearchEnabled()) return@withContext
            val config = observeConfig().first()
            val activeProfileId = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
            xtreamCredentialsForSeriesImport(config).forEach { creds ->
                val providerKey = "$activeProfileId|${xtreamCacheKey(creds)}"
                runCatching {
                    seriesResolver.prefetchSeriesInfo(
                        providerKey = providerKey,
                        creds = creds,
                        showTitle = title,
                        tmdbId = tmdbId,
                        imdbId = imdbId,
                        year = parseYear(title)
                    )
                }
            }
        }
    }

    private fun xtreamCacheKey(creds: XtreamCredentials): String {
        return "${creds.baseUrl}|${creds.username}|${creds.password}"
    }

    private fun ensureXtreamVodCacheOwnership(creds: XtreamCredentials) {
        val key = xtreamCacheKey(creds)
        if (xtreamVodCacheKey == key) return
        xtreamVodCacheKey = key
        xtreamVodLoadedAtMs = 0L
        xtreamSeriesLoadedAtMs = 0L
        cachedXtreamVodStreams = emptyList()
        cachedXtreamSeries = emptyList()
        cachedXtreamSeriesEpisodes = emptyMap()
        xtreamSeriesEpisodeInFlight = emptyMap()
    }

    // ── Disk cache helpers for Xtream VOD / Series catalogs ──────────────
    // Persists catalogs to JSON files so they survive app restarts, avoiding
    // 15-28 s re-downloads on cold start for large providers.

    private data class XtreamDiskCache<T>(val savedAtMs: Long, val items: List<T>)

    private fun xtreamDiskCacheDir(): File = File(context.filesDir, "xtream_vod_disk_cache").also { it.mkdirs() }

    private fun xtreamDiskCacheHash(creds: XtreamCredentials): String {
        val raw = "${creds.baseUrl}|${creds.username}|${creds.password}"
        return MessageDigest.getInstance("MD5").digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun vodDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "vod_${xtreamDiskCacheHash(creds)}.json")

    private fun seriesDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "series_${xtreamDiskCacheHash(creds)}.json")

    private fun <T> readDiskCache(file: File, type: Type): XtreamDiskCache<T>? {
        val parentExists = file.parentFile?.exists() == true
        val parentFiles = runCatching { file.parentFile?.listFiles()?.map { it.name } }.getOrNull()
        if (!file.exists()) {
            System.err.println("[VOD-Cache] Disk cache file not found: ${file.absolutePath} (parent exists=$parentExists, parent files=$parentFiles)")
            return null
        }
        val sizeKb = file.length() / 1024
        System.err.println("[VOD-Cache] Reading disk cache: ${file.name} (${sizeKb}KB)")
        return runCatching {
            file.bufferedReader(StandardCharsets.UTF_8).use { reader ->
                gson.fromJson<XtreamDiskCache<T>>(reader, type)
            }
        }.onFailure { e ->
            System.err.println("[VOD-Cache] Failed to read disk cache: ${file.name}: ${e.message}")
        }.getOrNull()
    }

    private fun <T> writeDiskCache(file: File, savedAtMs: Long, items: List<T>) {
        runCatching {
            file.parentFile?.mkdirs()
            val tmpFile = File(file.parentFile, "${file.name}.tmp")
            java.io.FileOutputStream(tmpFile).use { fos ->
                java.io.BufferedWriter(java.io.OutputStreamWriter(fos, StandardCharsets.UTF_8)).use { writer ->
                    gson.toJson(XtreamDiskCache(savedAtMs, items), writer)
                    writer.flush()
                }
                runCatching { fos.fd.sync() }  // Best-effort flush to disk; may fail on emulator
            }
            if (!tmpFile.renameTo(file)) {
                tmpFile.copyTo(file, overwrite = true)
                tmpFile.delete()
            }
            System.err.println("[VOD-Cache] Wrote disk cache: ${file.name} (${file.length() / 1024}KB), exists=${file.exists()}")
        }.onFailure { e ->
            System.err.println("[VOD-Cache] Failed to write disk cache: ${file.name}: ${e.message}")
        }
    }

    private val vodDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamVodStream::class.java).type
    }
    private val seriesDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamSeriesItem::class.java).type
    }

    // ── Restructured load methods: disk cache + non-blocking network ─────

    private suspend fun loadXtreamVodStreams(
        creds: XtreamCredentials,
        fast: Boolean = false
    ): List<XtreamVodStream> {
        return withContext(Dispatchers.IO) {
            // 1. Fast check: in-memory cache (no lock needed, fields are @Volatile)
            ensureXtreamVodCacheOwnership(creds)
            val now = System.currentTimeMillis()
            if (cachedXtreamVodStreams.isNotEmpty() && now - xtreamVodLoadedAtMs < xtreamVodCacheMs) {
                if (cachedVodIdIndex?.items !== cachedXtreamVodStreams) {
                    cachedVodIdIndex = buildVodIdIndex(cachedXtreamVodStreams)
                }
                return@withContext cachedXtreamVodStreams
            }

            // 2. Check disk cache (fast — reading a file, not a network call)
            val diskFile = vodDiskCacheFile(creds)
            val diskCache: XtreamDiskCache<XtreamVodStream>? = readDiskCache(diskFile, vodDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty() && now - diskCache.savedAtMs < xtreamVodCacheMs) {
                System.err.println("[VOD-Cache] Loaded ${diskCache.items.size} VOD streams from disk cache (age ${(now - diskCache.savedAtMs) / 1000}s)")
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
                cachedVodIdIndex = buildVodIdIndex(diskCache.items)
                return@withContext diskCache.items
            }

            // 3. Download from network — NO mutex held during download
            System.err.println("[VOD-Cache] Downloading VOD streams from network...")
            val downloadStart = System.currentTimeMillis()
            val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_vod_streams"
            val vod: List<XtreamVodStream> =
                requestJson(
                    url,
                    TypeToken.getParameterized(List::class.java, XtreamVodStream::class.java).type,
                    client = iptvHttpClient
                ) ?: emptyList()
            val elapsed = System.currentTimeMillis() - downloadStart
            System.err.println("[VOD-Cache] Downloaded ${vod.size} VOD streams in ${elapsed}ms")

            // 4. Swap into memory cache
            if (vod.isNotEmpty()) {
                val writeTime = System.currentTimeMillis()
                cachedXtreamVodStreams = vod
                xtreamVodLoadedAtMs = writeTime
                cachedVodIdIndex = buildVodIdIndex(vod)
                // 5. Persist to disk in background
                runCatching { writeDiskCache(diskFile, writeTime, vod) }
                System.err.println("[VOD-Cache] Saved VOD streams to disk cache")
            } else if (diskCache != null && diskCache.items.isNotEmpty()) {
                // Network returned empty — use stale disk cache
                System.err.println("[VOD-Cache] Network returned empty, using stale disk cache (${diskCache.items.size} items)")
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
                cachedVodIdIndex = buildVodIdIndex(diskCache.items)
                return@withContext diskCache.items
            }

            vod
        }
    }

    private suspend fun getXtreamVodStreams(
        creds: XtreamCredentials,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamVodStream> {
        if (allowNetwork) return loadXtreamVodStreams(creds, fast = fast)
        // If no network allowed, try memory first, then disk
        ensureXtreamVodCacheOwnership(creds)
        if (cachedXtreamVodStreams.isNotEmpty()) return cachedXtreamVodStreams
        // Try disk cache even when network not allowed
        return withContext(Dispatchers.IO) {
            val diskCache: XtreamDiskCache<XtreamVodStream>? = readDiskCache(vodDiskCacheFile(creds), vodDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty()) {
                cachedXtreamVodStreams = diskCache.items
                xtreamVodLoadedAtMs = diskCache.savedAtMs
                cachedVodIdIndex = buildVodIdIndex(diskCache.items)
                diskCache.items
            } else {
                emptyList()
            }
        }
    }

    private suspend fun loadXtreamSeriesList(
        creds: XtreamCredentials,
        fast: Boolean = false
    ): List<XtreamSeriesItem> {
        return withContext(Dispatchers.IO) {
            // 1. Fast check: in-memory cache
            ensureXtreamVodCacheOwnership(creds)
            val now = System.currentTimeMillis()
            if (cachedXtreamSeries.isNotEmpty() && now - xtreamSeriesLoadedAtMs < xtreamVodCacheMs) {
                return@withContext cachedXtreamSeries
            }

            // 2. Check disk cache
            val diskFile = seriesDiskCacheFile(creds)
            val diskCache: XtreamDiskCache<XtreamSeriesItem>? = readDiskCache(diskFile, seriesDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty() && now - diskCache.savedAtMs < xtreamVodCacheMs) {
                System.err.println("[VOD-Cache] Loaded ${diskCache.items.size} series from disk cache (age ${(now - diskCache.savedAtMs) / 1000}s)")
                cachedXtreamSeries = diskCache.items
                xtreamSeriesLoadedAtMs = diskCache.savedAtMs
                return@withContext diskCache.items
            }

            // 3. Download from network — NO mutex held
            System.err.println("[VOD-Cache] Downloading series list from network...")
            val downloadStart = System.currentTimeMillis()
            val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series"
            val series: List<XtreamSeriesItem> =
                requestJson(
                    url,
                    TypeToken.getParameterized(List::class.java, XtreamSeriesItem::class.java).type,
                    client = iptvHttpClient
                ) ?: emptyList()
            val elapsed = System.currentTimeMillis() - downloadStart
            System.err.println("[VOD-Cache] Downloaded ${series.size} series in ${elapsed}ms")

            // 4. Swap into memory cache
            if (series.isNotEmpty()) {
                val writeTime = System.currentTimeMillis()
                cachedXtreamSeries = series
                xtreamSeriesLoadedAtMs = writeTime
                // 5. Persist to disk
                runCatching { writeDiskCache(diskFile, writeTime, series) }
                System.err.println("[VOD-Cache] Saved series list to disk cache")
            } else if (diskCache != null && diskCache.items.isNotEmpty()) {
                System.err.println("[VOD-Cache] Network returned empty, using stale disk cache (${diskCache.items.size} items)")
                cachedXtreamSeries = diskCache.items
                xtreamSeriesLoadedAtMs = diskCache.savedAtMs
                return@withContext diskCache.items
            }

            series
        }
    }

    private suspend fun getXtreamSeriesList(
        creds: XtreamCredentials,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamSeriesItem> {
        if (allowNetwork) return loadXtreamSeriesList(creds, fast = fast)
        // If no network allowed, try memory first, then disk
        ensureXtreamVodCacheOwnership(creds)
        if (cachedXtreamSeries.isNotEmpty()) return cachedXtreamSeries
        return withContext(Dispatchers.IO) {
            val diskCache: XtreamDiskCache<XtreamSeriesItem>? = readDiskCache(seriesDiskCacheFile(creds), seriesDiskCacheType)
            if (diskCache != null && diskCache.items.isNotEmpty()) {
                cachedXtreamSeries = diskCache.items
                xtreamSeriesLoadedAtMs = diskCache.savedAtMs
                diskCache.items
            } else {
                emptyList()
            }
        }
    }

    private suspend fun loadXtreamSeriesEpisodes(
        creds: XtreamCredentials,
        seriesId: Int,
        fast: Boolean = false
    ): List<XtreamSeriesEpisode> {
        ensureXtreamVodCacheOwnership(creds)
        val cached = cachedXtreamSeriesEpisodes[seriesId]
        if (!cached.isNullOrEmpty()) return cached

        val existingInFlight = xtreamSeriesEpisodeInFlightMutex.withLock {
            xtreamSeriesEpisodeInFlight[seriesId]
        }
        if (existingInFlight != null) return existingInFlight.await()

        return coroutineScope {
            val created = async(Dispatchers.IO) {
                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series_info&series_id=$seriesId"
                val info: JsonObject = requestJson(
                    url,
                    JsonObject::class.java,
                    client = if (fast) xtreamLookupHttpClient else iptvHttpClient
                ) ?: return@async emptyList()
                val parsed = parseXtreamSeriesEpisodes(info)
                if (!fast && parsed.isNotEmpty()) {
                    xtreamSeriesEpisodeCacheMutex.withLock {
                        val next = LinkedHashMap(cachedXtreamSeriesEpisodes)
                        next[seriesId] = parsed
                        while (next.size > maxSeriesEpisodeCacheEntries) {
                            val oldestKey = next.keys.firstOrNull() ?: break
                            next.remove(oldestKey)
                        }
                        cachedXtreamSeriesEpisodes = next
                    }
                }
                parsed
            }

            val deferred = xtreamSeriesEpisodeInFlightMutex.withLock {
                val race = xtreamSeriesEpisodeInFlight[seriesId]
                if (race != null) {
                    created.cancel()
                    race
                } else {
                    xtreamSeriesEpisodeInFlight = xtreamSeriesEpisodeInFlight + (seriesId to created)
                    created
                }
            }

            try {
                deferred.await()
            } finally {
                xtreamSeriesEpisodeInFlightMutex.withLock {
                    if (xtreamSeriesEpisodeInFlight[seriesId] === deferred) {
                        xtreamSeriesEpisodeInFlight = xtreamSeriesEpisodeInFlight - seriesId
                    }
                }
            }
        }
    }

    private suspend fun getXtreamSeriesEpisodes(
        creds: XtreamCredentials,
        seriesId: Int,
        allowNetwork: Boolean,
        fast: Boolean = false
    ): List<XtreamSeriesEpisode> {
        if (allowNetwork) return loadXtreamSeriesEpisodes(creds, seriesId, fast = fast)
        ensureXtreamVodCacheOwnership(creds)
        return cachedXtreamSeriesEpisodes[seriesId].orEmpty()
    }

    private fun parseXtreamSeriesEpisodes(root: JsonObject): List<XtreamSeriesEpisode> {
        val episodesElement = root.get("episodes") ?: return emptyList()
        val episodeObjects = mutableListOf<Pair<JsonObject, Int?>>()
        collectXtreamEpisodeObjects(episodesElement, seasonHint = null, out = episodeObjects)
        if (episodeObjects.isEmpty()) return emptyList()

        return episodeObjects.mapNotNull { (item, seasonHint) ->
            parseEpisodeObject(item, seasonHint = seasonHint, fallbackIndex = null)
        }
    }

    private fun parseSeasonKey(raw: String): Int? {
        if (raw.isBlank()) return null
        val parsed = raw.toIntOrNull() ?: SEASON_KEY_REGEX.find(raw)?.value?.toIntOrNull()
        return parsed?.takeIf { it in 0..99 }
    }

    private fun parseSeasonEpisodes(seasonKey: Int?, array: JsonArray): List<XtreamSeriesEpisode> {
        val out = mutableListOf<XtreamSeriesEpisode>()
        array.forEachIndexed { index, element ->
            val item = element?.asJsonObject ?: return@forEachIndexed
            parseEpisodeObject(item, seasonHint = seasonKey, fallbackIndex = index)?.let { out += it }
        }
        return out
    }

    private fun collectXtreamEpisodeObjects(
        element: com.google.gson.JsonElement?,
        seasonHint: Int?,
        out: MutableList<Pair<JsonObject, Int?>>
    ) {
        when {
            element == null || element.isJsonNull -> return
            element.isJsonArray -> {
                element.asJsonArray.forEach { child ->
                    collectXtreamEpisodeObjects(child, seasonHint, out)
                }
            }
            !element.isJsonObject -> return
            else -> {
                val obj = element.asJsonObject
                val objectSeasonHint = seasonHint
                    ?: parseFlexibleInt(obj.get("season"))
                    ?: parseFlexibleInt(obj.get("season_number"))
                    ?: parseFlexibleInt(obj.get("season_num"))
                if (looksLikeXtreamEpisodeObject(obj)) {
                    out += obj to objectSeasonHint
                    return
                }

                val nestedEpisodes = obj.get("episodes")
                if (nestedEpisodes != null && !nestedEpisodes.isJsonNull) {
                    collectXtreamEpisodeObjects(nestedEpisodes, objectSeasonHint, out)
                }

                obj.entrySet().forEach { (key, value) ->
                    if (key.equals("episodes", ignoreCase = true)) return@forEach
                    val keyedSeasonHint = parseSeasonKey(key) ?: objectSeasonHint
                    collectXtreamEpisodeObjects(value, keyedSeasonHint, out)
                }
            }
        }
    }

    private fun looksLikeXtreamEpisodeObject(obj: JsonObject): Boolean {
        if (parseFlexibleInt(obj.get("id")) != null) return true
        if (parseFlexibleInt(obj.get("stream_id")) != null) return true
        if (parseFlexibleInt(obj.get("episode_id")) != null) return true
        if (parseFlexibleInt(obj.get("episode_num")) != null) return true
        if (parseFlexibleInt(obj.get("episode_number")) != null) return true
        val infoObj = obj.get("info")?.takeIf { it.isJsonObject }?.asJsonObject
        if (infoObj != null) {
            if (parseFlexibleInt(infoObj.get("id")) != null) return true
            if (parseFlexibleInt(infoObj.get("stream_id")) != null) return true
            if (parseFlexibleInt(infoObj.get("episode_id")) != null) return true
            if (parseFlexibleInt(infoObj.get("episode_num")) != null) return true
            if (parseFlexibleInt(infoObj.get("episode_number")) != null) return true
        }
        return false
    }

    private fun parseEpisodeObject(
        item: JsonObject,
        seasonHint: Int?,
        fallbackIndex: Int?
    ): XtreamSeriesEpisode? {
        val infoObj = item.get("info")?.takeIf { it.isJsonObject }?.asJsonObject
        val rawTitle = item.get("title")?.asString?.trim().orEmpty().ifBlank {
            infoObj?.get("title")?.asString?.trim().orEmpty()
        }
        val parsedSeasonEpisode = extractSeasonEpisodeFromName(rawTitle)
        val resolvedSeason = seasonHint
            ?: parseFlexibleInt(item.get("season"))
            ?: parseFlexibleInt(item.get("season_number"))
            ?: parseFlexibleInt(item.get("season_num"))
            ?: parseFlexibleInt(infoObj?.get("season"))
            ?: parseFlexibleInt(infoObj?.get("season_number"))
            ?: parseFlexibleInt(infoObj?.get("season_num"))
            ?: parsedSeasonEpisode?.first
            ?: 1
        val episodeNum = parseFlexibleInt(item.get("episode_num"))
            ?: parseFlexibleInt(item.get("episode"))
            ?: parseFlexibleInt(item.get("episode_number"))
            ?: parseFlexibleInt(item.get("number"))
            ?: parseFlexibleInt(item.get("sort"))
            ?: parseFlexibleInt(item.get("sort_order"))
            ?: parseFlexibleInt(infoObj?.get("episode_num"))
            ?: parseFlexibleInt(infoObj?.get("episode"))
            ?: parseFlexibleInt(infoObj?.get("episode_number"))
            ?: parseFlexibleInt(infoObj?.get("number"))
            ?: parseFlexibleInt(infoObj?.get("sort"))
            ?: parsedSeasonEpisode?.second
            ?: extractEpisodeOnlyFromName(rawTitle)
            ?: fallbackIndex?.let { it + 1 }
            ?: 1
        val id = parseFlexibleInt(item.get("id"))
            ?: parseFlexibleInt(item.get("stream_id"))
            ?: parseFlexibleInt(item.get("episode_id"))
            ?: parseFlexibleInt(infoObj?.get("id"))
            ?: parseFlexibleInt(infoObj?.get("stream_id"))
            ?: parseFlexibleInt(infoObj?.get("episode_id"))
            ?: return null
        val title = rawTitle.ifBlank { "S${resolvedSeason}E${episodeNum}" }
        val ext = item.get("container_extension")?.asString?.trim()?.ifBlank { null }
            ?: infoObj?.get("container_extension")?.asString?.trim()?.ifBlank { null }
        return XtreamSeriesEpisode(
            id = id,
            season = resolvedSeason,
            episode = episodeNum,
            title = title,
            containerExtension = ext
        )
    }

    private fun parseFlexibleInt(element: com.google.gson.JsonElement?): Int? {
        if (element == null || element.isJsonNull) return null
        return runCatching {
            when {
                element.isJsonPrimitive && element.asJsonPrimitive.isNumber -> {
                    val number = element.asDouble
                    if (number.isFinite()) number.toInt() else null
                }
                element.isJsonPrimitive -> {
                    val raw = element.asString.trim()
                    raw.toIntOrNull()
                        ?: raw.toDoubleOrNull()?.toInt()
                        ?: FLEXIBLE_INT_REGEX.find(raw)?.value?.toIntOrNull()
                }
                else -> null
            }
        }.getOrNull()
    }

    private fun normalizeLookupText(value: String): String = IptvTitleNormalizer.normalize(value)

    /**
     * Alias tokens for catalog entries — see [IptvTitleNormalizer.foldUmlautTranscription].
     * Returns the input unchanged when no token carries a transcribed umlaut.
     */
    private fun umlautAliasTokens(tokens: Set<String>): Set<String> =
        tokens.mapTo(LinkedHashSet<String>()) { IptvTitleNormalizer.foldUmlautTranscription(it) }

    private fun withUmlautAliasTokens(tokens: Set<String>): Set<String> {
        val alias = umlautAliasTokens(tokens)
        return if (alias == tokens) tokens else tokens + alias
    }

    private val titleTokenNoise = setOf(
        "the", "a", "an", "and", "of", "to", "in", "on",
        "complete", "series", "tv", "show", "season", "seasons",
        "episode", "episodes", "part", "collection", "pack"
    )

    private fun extractTitleTokens(value: String): Set<String> {
        val normalized = normalizeLookupText(value)
        return extractTitleTokensFromNormalized(normalized)
    }

    /** Fast version: skips redundant normalizeLookupText when input is already normalized. */
    private fun extractTitleTokensFromNormalized(normalized: String): Set<String> {
        if (normalized.isBlank()) return emptySet()
        return normalized
            .split(' ')
            .asSequence()
            .map { it.trim() }
            .filter { it.length >= 3 && it !in titleTokenNoise }
            .toSet()
    }

    private fun toCanonicalTitleKey(value: String): String {
        val tokens = extractTitleTokens(value)
        if (tokens.isEmpty()) return ""
        return tokens.sorted().joinToString(" ")
    }

    /** Fast version: uses pre-computed tokens. */
    private fun toCanonicalTitleKeyFromTokens(tokens: Set<String>): String {
        if (tokens.isEmpty()) return ""
        return tokens.sorted().joinToString(" ")
    }

    private fun normalizeImdbId(value: String?): String? {
        if (value.isNullOrBlank()) return null
        val cleaned = value.trim().lowercase(Locale.US)
        val match = IMDB_ID_REGEX.find(cleaned)?.value
        return match ?: cleaned.takeIf { it.startsWith("tt") && it.length >= 7 }
    }

    private fun normalizeTmdbId(value: String?): String? {
        if (value.isNullOrBlank()) return null
        val digits = TMDB_ID_REGEX.find(value.trim())?.value
        return digits?.trimStart('0')?.ifBlank { "0" }
    }

    private fun normalizeTmdbId(value: Int?): String? {
        if (value == null || value <= 0) return null
        return value.toString()
    }

    private fun parseYear(value: String): Int? {
        return YEAR_REGEX
            .find(value)
            ?.value
            ?.toIntOrNull()
    }

    private fun extractSeasonEpisodeFromName(value: String): Pair<Int, Int>? {
        val normalized = value.lowercase(Locale.US)
        SEASON_EPISODE_PATTERNS.forEach { regex ->
            val match = regex.find(normalized) ?: return@forEach
            val season = match.groupValues.getOrNull(1)?.toIntOrNull() ?: return@forEach
            val episode = match.groupValues.getOrNull(2)?.toIntOrNull() ?: return@forEach
            return Pair(season, episode)
        }
        return null
    }

    private fun extractEpisodeOnlyFromName(value: String): Int? {
        val normalized = value.lowercase(Locale.US)
        EPISODE_ONLY_PATTERNS.forEach { regex ->
            val match = regex.find(normalized) ?: return@forEach
            val episode = match.groupValues.getOrNull(1)?.toIntOrNull() ?: return@forEach
            if (episode > 0) return episode
        }
        return null
    }

    private fun pickEpisodeMatch(
        episodes: List<XtreamSeriesEpisode>,
        season: Int,
        episode: Int
    ): XtreamSeriesEpisode? {
        if (episodes.isEmpty()) return null
        episodes.firstOrNull { it.season == season && it.episode == episode }?.let { return it }

        // Some providers flatten seasoning; if exactly one candidate has the episode number, use it.
        val byEpisode = episodes.filter { it.episode == episode }
        if (byEpisode.size == 1) return byEpisode.first()
        if (byEpisode.isNotEmpty()) {
            return byEpisode.minByOrNull { kotlin.math.abs(it.season - season) }
        }
        return null
    }

    private fun scoreNameMatch(providerName: String, normalizedInput: String): Int {
        val normalizedProvider = normalizeLookupText(providerName)
        val direct = scoreNormalizedNameMatch(normalizedProvider, normalizedInput)
        val alias = IptvTitleNormalizer.foldUmlautTranscription(normalizedProvider)
        if (alias == normalizedProvider) return direct
        return maxOf(direct, scoreNormalizedNameMatch(alias, normalizedInput))
    }

    private fun scoreNormalizedNameMatch(normalizedProvider: String, normalizedInput: String): Int {
        if (normalizedProvider.isBlank() || normalizedInput.isBlank()) return 0
        if (normalizedProvider == normalizedInput) return 120
        if (normalizedProvider.contains(normalizedInput)) return 90
        if (normalizedInput.contains(normalizedProvider)) return 70
        if (normalizedProvider.startsWith(normalizedInput) || normalizedInput.startsWith(normalizedProvider)) return 68
        val stopWords = setOf("the", "a", "an", "and", "of", "part", "episode", "season", "movie")
        val providerWords = normalizedProvider
            .split(' ')
            .filter { it.isNotBlank() && it !in stopWords }
            .toSet()
        val inputWords = normalizedInput
            .split(' ')
            .filter { it.isNotBlank() && it !in stopWords }
            .toSet()
        if (providerWords.isEmpty() || inputWords.isEmpty()) return 0
        val overlap = providerWords.intersect(inputWords).size
        val coverage = overlap.toDouble() / inputWords.size.toDouble()
        return when {
            overlap >= 2 && coverage >= 0.75 -> 75 + overlap
            overlap >= 2 -> 55 + overlap
            overlap == 1 && inputWords.size >= 3 && providerWords.size >= 3 -> 42
            overlap == 1 && inputWords.size <= 2 -> 35
            else -> 0
        }
    }

    private fun looseSeriesTitleScore(providerName: String, normalizedInput: String): Int {
        val normalizedProvider = normalizeLookupText(providerName)
        val direct = looseSeriesTitleScoreNormalized(normalizedProvider, normalizedInput)
        val alias = IptvTitleNormalizer.foldUmlautTranscription(normalizedProvider)
        if (alias == normalizedProvider) return direct
        return maxOf(direct, looseSeriesTitleScoreNormalized(alias, normalizedInput))
    }

    private fun looseSeriesTitleScoreNormalized(normalizedProvider: String, normalizedInput: String): Int {
        if (normalizedProvider.isBlank() || normalizedInput.isBlank()) return 0
        val providerWords = normalizedProvider.split(' ').filter { it.length >= 3 }.toSet()
        val inputWords = normalizedInput.split(' ').filter { it.length >= 3 }.toSet()
        if (providerWords.isEmpty() || inputWords.isEmpty()) return 0
        val overlap = providerWords.intersect(inputWords).size
        return when {
            overlap >= 2 -> 50 + overlap
            overlap == 1 -> 24
            else -> 0
        }
    }

    private fun rankSeriesCandidate(
        providerName: String,
        normalizedInput: String,
        baseScore: Int
    ): Int {
        val normalizedProvider = normalizeLookupText(providerName)
        if (normalizedProvider.isBlank() || normalizedInput.isBlank()) return baseScore
        var score = baseScore
        if (normalizedProvider == normalizedInput) score += 500
        if (normalizedProvider.contains(normalizedInput)) score += 320
        if (normalizedInput.contains(normalizedProvider)) score += 180
        val providerHead = normalizedProvider.split(' ').take(2).joinToString(" ")
        if (providerHead.isNotBlank() && normalizedInput.startsWith(providerHead)) score += 110
        return score
    }

    private fun inferQuality(value: String): String {
        val lower = value.lowercase(Locale.US)
        return when {
            lower.contains("2160") || lower.contains("4k") -> "4K"
            lower.contains("1080") -> "1080p"
            lower.contains("720") -> "720p"
            lower.contains("480") -> "480p"
            else -> "VOD"
        }
    }

    private fun vodQualityRank(value: String): Int {
        val lower = value.lowercase(Locale.US)
        return when {
            lower.contains("2160") || lower.contains("4k") -> 500
            lower.contains("1080") -> 400
            lower.contains("720") -> 300
            lower.contains("480") -> 200
            lower.contains("360") -> 100
            else -> 0
        }
    }

    private fun resolveXtreamCredentials(url: String): XtreamCredentials? {
        if (url.isBlank()) return null
        val parsed = normalizeStoredIptvUrl(url).toHttpUrlOrNull() ?: return null
        var username = parsed.queryParameter("username")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("user")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("uname")?.trim()?.ifBlank { null }
            ?: ""
        var password = parsed.queryParameter("password")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pass")?.trim()?.ifBlank { null }
            ?: parsed.queryParameter("pwd")?.trim()?.ifBlank { null }
            ?: ""

        // Try extracting from path if query params are missing.
        if (username.isBlank() || password.isBlank()) {
            val segments = parsed.pathSegments
            val knownPrefix = segments.firstOrNull()?.lowercase(Locale.US)
            if (segments.size >= 4 && knownPrefix in setOf("live", "movie", "series")) {
                username = segments[1]
                password = segments[2]
            } else if (segments.size >= 3 && segments.last().substringBefore('.').toIntOrNull() != null) {
                username = segments[segments.size - 3]
                password = segments[segments.size - 2]
            }
        }

        if (username.isBlank() || password.isBlank()) return null
        // Accept any URL with username/password params; derive baseUrl from scheme+host+port
        val path = parsed.encodedPath.lowercase(Locale.US)
        val knownXtreamPath = path.endsWith("/get.php") || path.endsWith("/xmltv.php") || path.endsWith("/player_api.php")
        val baseUrl = if (knownXtreamPath) {
            parsed.toXtreamBaseUrl()
        } else {
            // Derive from scheme + host + port for non-standard paths
            buildString {
                append(parsed.scheme)
                append("://")
                append(parsed.host)
                if (parsed.port != if (parsed.scheme == "https") 443 else 80) {
                    append(":${parsed.port}")
                }
            }
        }
        return XtreamCredentials(baseUrl, username, password)
    }

    private fun resolveXtreamCredentials(playlist: IptvPlaylistEntry): XtreamCredentials? {
        playlist.allEpgUrls().forEach { epgUrl ->
            resolveXtreamCredentials(epgUrl)?.let { return it }
        }
        return resolveXtreamCredentials(playlist.m3uUrl)
    }

    private fun resolveScopedEpgCandidates(config: IptvConfig): List<ScopedEpgCandidate> {
        val allLists = activePlaylists(config)
        return buildList {
            allLists.forEach { list ->
                list.allEpgUrls().forEach { add(ScopedEpgCandidate(it, list.id)) }
                val creds = resolveXtreamCredentials(list)
                if (creds != null) {
                    add(ScopedEpgCandidate("${creds.baseUrl}/xmltv.php?username=${creds.username}&password=${creds.password}", list.id, providerFallback = true))
                    add(ScopedEpgCandidate("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xmltv", list.id, providerFallback = true))
                    add(ScopedEpgCandidate("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}&type=xml", list.id, providerFallback = true))
                    add(ScopedEpgCandidate("${creds.baseUrl}/xmltv.php", list.id, providerFallback = true))
                    add(ScopedEpgCandidate("${creds.baseUrl}/get.php?username=${creds.username}&password=${creds.password}", list.id, providerFallback = true))
                }
            }
            discoveredM3uEpgUrls.forEach { add(ScopedEpgCandidate(it)) }
        }
            .filter { it.url.isNotBlank() }
            .distinctBy { "${it.playlistId.orEmpty()}|${it.url}" }
    }

    private fun channelsForScopedEpgCandidate(
        candidate: ScopedEpgCandidate,
        channels: List<IptvChannel>
    ): List<IptvChannel> {
        val playlistId = candidate.playlistId?.trim().orEmpty()
        if (playlistId.isBlank()) return channels
        val prefix = "$playlistId:"
        return channels.filter { channel -> channel.id.startsWith(prefix) }
    }

    private fun groupXtreamChannelsByCredentials(
        config: IptvConfig,
        channels: List<IptvChannel>
    ): LinkedHashMap<XtreamCredentials, MutableList<IptvChannel>> {
        val activePlaylistById = activePlaylists(config).associateBy { it.id }
        val fallbackCreds = resolveXtreamCredentials(config)
        val result = LinkedHashMap<XtreamCredentials, MutableList<IptvChannel>>()
        channels.forEach { channel ->
            if (resolveXtreamStreamId(channel) == null) return@forEach
            val playlistId = channel.id.substringBefore(':', missingDelimiterValue = "")
                .takeIf { it.isNotBlank() }
            val playlistCreds = playlistId
                ?.let { activePlaylistById[it] }
                ?.let { resolveXtreamCredentials(it) }
            val creds = playlistCreds ?: fallbackCreds ?: return@forEach
            result.getOrPut(creds) { mutableListOf() }.add(channel)
        }
        return result
    }

    private fun resolveXtreamCredentials(config: IptvConfig): XtreamCredentials? {
        activePlaylists(config).forEach { playlist ->
            resolveXtreamCredentials(playlist)?.let { return it }
        }
        resolveXtreamCredentials(config.epgUrl)?.let { return it }
        resolveXtreamCredentials(config.m3uUrl)?.let { return it }
        return null
    }

    private suspend fun discoverEmbeddedEpgSourcesIfNeeded(playlists: List<IptvPlaylistEntry>) {
        if (discoveredM3uEpgUrls.isNotEmpty()) return
        val candidates = playlists.filter { playlist ->
            playlist.m3uUrl.isNotBlank() &&
                playlist.allEpgUrls().isEmpty() &&
                resolveXtreamCredentials(playlist) == null
        }
        if (candidates.isEmpty()) return

        coroutineScope {
            candidates.map { playlist ->
                async(Dispatchers.IO) {
                    discoverM3uHeaderEpgUrls(playlist.m3uUrl)
                }
            }.awaitAll()
        }
            .flatten()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .forEach { discoveredM3uEpgUrls.add(it) }
    }

    private fun discoverM3uHeaderEpgUrls(m3uUrl: String): List<String> {
        return runCatching {
            val request = Request.Builder()
                .url(validatedIptvHttpUrl(m3uUrl, "IPTV playlist URL"))
                .build()
            iptvCatalogHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@use emptyList()
                val body = response.body ?: return@use emptyList()
                BufferedReader(InputStreamReader(body.byteStream(), StandardCharsets.UTF_8), 32 * 1024).use { reader ->
                    repeat(32) {
                        val line = reader.readLine() ?: return@use emptyList()
                        val trimmed = line.trim()
                        if (trimmed.startsWith("#EXTM3U", ignoreCase = true)) {
                            return@use extractM3uDeclaredEpgUrls(trimmed)
                        }
                        if (trimmed.isNotEmpty() && !trimmed.startsWith("#")) {
                            return@use emptyList()
                        }
                    }
                    emptyList()
                }
            }
        }.getOrDefault(emptyList())
    }

    private suspend fun fetchXtreamLiveChannels(
        creds: XtreamCredentials,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val categoriesUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_live_categories"
        val streamsUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_live_streams"

        onProgress(IptvLoadProgress("Loading categories...", 10))
        val categories: List<XtreamLiveCategory> =
            requestJson(
                categoriesUrl,
                TypeToken.getParameterized(List::class.java, XtreamLiveCategory::class.java).type,
                client = iptvCatalogHttpClient
            ) ?: emptyList()
        val categoryMap = LinkedHashMap<String, String>(categories.size)
        val categoryOrder = ArrayList<String>(categories.size)
        categories.forEach { category ->
            val categoryId = category.categoryId.orEmpty().trim()
            if (categoryId.isBlank() || categoryId in categoryMap) return@forEach
            categoryMap[categoryId] = category.categoryName?.trim().orEmpty().ifBlank { "Uncategorized" }
            categoryOrder += categoryId
        }

        onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_live), 35))
        val streams: List<XtreamLiveStream> =
            requestJson(
                streamsUrl,
                TypeToken.getParameterized(List::class.java, XtreamLiveStream::class.java).type,
                client = iptvCatalogHttpClient
            ) ?: emptyList()
        if (streams.isEmpty()) return emptyList()

        val total = streams.size.coerceAtLeast(1)
        val categorizedChannels = streams.mapIndexedNotNull { index, stream ->
            if (index % 500 == 0) {
                val pct = (35 + ((index.toLong() * 55L) / total.toLong())).toInt().coerceIn(35, 90)
                onProgress(IptvLoadProgress("Parsing provider streams... $index/$total", pct))
            }

            val streamId = stream.streamId ?: return@mapIndexedNotNull null
            val name = stream.name?.trim().orEmpty().ifBlank { return@mapIndexedNotNull null }
            val categoryId = stream.categoryId.orEmpty().trim()
            val group = categoryMap[categoryId].orEmpty().ifBlank { "Uncategorized" }
            val streamUrl = buildXtreamLiveStreamUrl(
                creds.baseUrl, creds.username, creds.password, streamId, stream.containerExtension,
            )

            categoryId to IptvChannel(
                id = "xtream:$streamId",
                name = name,
                streamUrl = streamUrl,
                group = group,
                logo = stream.streamIcon?.takeIf { it.isNotBlank() },
                epgId = stream.epgChannelId?.trim()?.takeIf { it.isNotBlank() },
                rawTitle = name,
                xtreamStreamId = streamId,
                providerChannelNumber = stream.providerNumber?.trim()?.takeIf { it.isNotBlank() },
                catchupDays = (stream.tvArchiveDuration ?: stream.tvArchive ?: 0).coerceAtLeast(0),
                catchupType = if ((stream.tvArchive ?: 0) > 0 || (stream.tvArchiveDuration ?: 0) > 0) "xtream" else null
            )
        }
        return orderXtreamChannelsByProviderCategories(categoryOrder, categorizedChannels)
    }

    private suspend fun <T> requestJson(
        url: String,
        type: Type,
        client: OkHttpClient = iptvHttpClient
    ): T? = if (client === xtreamGuideHttpClient || client === xtreamCatchupGuideHttpClient) {
        guideRequestBudget.request(url) { requestJsonUnbudgeted<T>(url, type, client, guideRequest = true) }
    } else {
        requestJsonUnbudgeted(url, type, client)
    }

    private suspend fun <T> requestJsonUnbudgeted(
        url: String,
        type: Type,
        client: OkHttpClient,
        guideRequest: Boolean = false,
    ): T? = suspendCancellableCoroutine { continuation ->
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", OkHttpProvider.userAgentOr(IPTV_USER_AGENT))
            .header("Accept", "application/json,*/*")
            .get()
            .build()

        val call = client.newCall(request)

        continuation.invokeOnCancellation {
            call.cancel()
        }

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (continuation.isActive) continuation.resume(null)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!continuation.isActive) {
                    response.close()
                    return
                }
                response.use {
                    if (guideRequest) {
                        guideRequestBudget.onResponse(url, it.code, it.header("Retry-After")?.toLongOrNull())
                    }
                    if (!it.isSuccessful) {
                        continuation.resume(null)
                        return
                    }
                    val responseBody = it.body
                    if (responseBody == null) {
                        continuation.resume(null)
                        return
                    }
                    try {
                        responseBody.charStream().use { reader ->
                            val result = gson.fromJson<T>(reader, type)
                            if (continuation.isActive) continuation.resume(result)
                        }
                    } catch (error: Throwable) {
                        System.err.println("IptvRepository: JSON request failed for ${redactIptvUrl(url)}: ${error.message}")
                        if (continuation.isActive) continuation.resume(null)
                    }
                }
            }
        })
    }

    private fun fetchAndParseM3uOnce(
        url: String,
        onProgress: (IptvLoadProgress) -> Unit,
        client: OkHttpClient = iptvHttpClient,
    ): List<IptvChannel> {
        val startedAt = System.currentTimeMillis()
        val request = Request.Builder()
            .url(validatedIptvHttpUrl(url, "IPTV playlist URL"))
            .header("User-Agent", OkHttpProvider.userAgentOr(IPTV_USER_AGENT))
            .header("Accept", "*/*")
            .get()
            .build()
        client.newCall(request).execute().use { response ->
            val raw = response.body?.byteStream() ?: throw IllegalStateException(context.getString(R.string.iptv_m3u_empty))
            val contentLength = response.body?.contentLength()?.takeIf { it > 0L }
            val progressStream = ProgressInputStream(raw) { bytesRead ->
                if (contentLength != null) {
                    val pct = ((bytesRead * 70L) / contentLength).toInt().coerceIn(8, 74)
                    onProgress(IptvLoadProgress("Downloading playlist... $pct%", pct))
                } else {
                    onProgress(IptvLoadProgress("Downloading playlist...", 15))
                }
            }
            val stream = BufferedInputStream(progressStream, 256 * 1024)
            if (!response.isSuccessful && !looksLikeM3u(stream)) {
                val rawPreview = response.peekBody(512).string().replace('\n', ' ').trim()
                // Strip HTML tags and CSS to produce a clean error message
                val cleanPreview = rawPreview
                    .replace(HTML_STYLE_REGEX, "")
                    .replace(HTML_SCRIPT_REGEX, "")
                    .replace(HTML_TAG_REGEX, " ")
                    .replace(CSS_BRACE_REGEX, "")
                    .replace(MULTI_SPACE_REGEX, " ")
                    .trim()
                    .take(150)
                val detail = when {
                    response.code == 403 -> "Access denied by the server. The IPTV provider may be blocking this request."
                    response.code == 404 -> "Playlist URL not found. Check the M3U URL in settings."
                    response.code in 500..599 -> "Server error (${response.code}). The IPTV provider may be temporarily down."
                    cleanPreview.isBlank() -> "HTTP ${response.code}"
                    else -> cleanPreview
                }
                throw IllegalStateException(context.getString(R.string.iptv_m3u_failed, response.code) + " " + detail)
            }
            onProgress(IptvLoadProgress("Parsing channels...", 78))
            val channels = parseM3u(stream, onProgress)
            System.err.println(
                "[IPTV-Timing] playlist fetch+parse channels=${channels.size} " +
                    "in ${System.currentTimeMillis() - startedAt}ms"
            )
            return channels
        }
    }

    private fun fetchAndParseEpg(
        url: String,
        channels: List<IptvChannel>,
        checkActive: () -> Unit = {},
    ): Map<String, IptvNowNext> {
        checkActive()
        // A 304 is only useful when this exact request can be served from cache.
        val hasDbEntries = channels.all { hasProgramData(cachedNowNext[it.id]) }

        fun epgRequest(targetUrl: String, userAgent: String, forceFull: Boolean = false): Request {
            val builder = Request.Builder()
                .url(validatedEpgHttpUrl(targetUrl, "EPG URL"))
                .header("User-Agent", userAgent)
                .header("Accept", "*/*")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Cache-Control", "no-cache")

            if (hasDbEntries && !forceFull) {
                getEpgCachedEtag(targetUrl)?.let { etag ->
                    builder.header("If-None-Match", etag)
                }
                getEpgCachedLastModified(targetUrl)?.let { lm ->
                    builder.header("If-Modified-Since", lm)
                }
            }

            return builder.get().build()
        }

        val primaryUserAgent = OkHttpProvider.userAgentOr(IPTV_USER_AGENT)
        val fallbackUserAgent = OkHttpProvider.userAgentOr(BROWSER_USER_AGENT)
        var response = iptvHttpClient.newCall(epgRequest(url, primaryUserAgent)).execute()
        guideRequestBudget.onResponse(url, response.code, response.header("Retry-After")?.toLongOrNull())
        try {
            checkActive()
        } catch (error: Exception) {
            response.close()
            throw error
        }
        if (response.code == 304) {
            response.close()
            throw EpgNotModifiedException()
        }
        if (!response.isSuccessful && response.code == 511) {
            response.close()
            response = iptvHttpClient.newCall(
                epgRequest(url, fallbackUserAgent)
            ).execute()
            guideRequestBudget.onResponse(url, response.code, response.header("Retry-After")?.toLongOrNull())
            if (response.code == 304) {
                response.close()
                throw EpgNotModifiedException()
            }
        }
        response.use { safeResponse ->
            checkActive()
            val stream = safeResponse.body?.byteStream() ?: throw IllegalStateException(context.getString(R.string.epg_empty))
            val prepared = BufferedInputStream(prepareInputStream(stream, url))
            if (!safeResponse.isSuccessful && !looksLikeXmlTv(prepared)) {
                val preview = safeResponse.peekBody(220).string().replace('\n', ' ').trim()
                val detail = if (preview.isBlank()) "No response body." else preview
                throw IllegalStateException(context.getString(R.string.epg_failed, safeResponse.code) + " " + detail)
            }

            // Try streaming parse first (avoids disk I/O for the common case).
            // Only spool to disk and retry if the stream parse fails.
            try {
                val sanitized = BackslashEscapeSanitizingInputStream(prepared)
                val parsed = parseXmlTvToIndex(BufferedInputStream(sanitized), channels, checkActive, url)
                if (parsed.isNotEmpty()) {
                    saveEpgHttpCacheHeaders(url, safeResponse.header("ETag"), safeResponse.header("Last-Modified"))
                }
                return parsed
            } catch (streamError: Exception) {
                if (streamError is kotlinx.coroutines.CancellationException) throw streamError
                checkActive()
                // Streaming parse failed – the network stream is consumed, so we
                // cannot retry from it.  Check if we got a useful partial result
                // or need to re-download.  Re-download and spool to disk for retries.
                val tmpFile = File.createTempFile("epg_", ".xml", context.cacheDir)
                try {
                    // Re-download
                    val retryResponse = iptvHttpClient.newCall(
                        epgRequest(url, primaryUserAgent, forceFull = true)
                    ).execute()
                    retryResponse.use { rr ->
                        checkActive()
                        guideRequestBudget.onResponse(url, rr.code, rr.header("Retry-After")?.toLongOrNull())
                        val retryStream = rr.body?.byteStream()
                            ?: throw IllegalStateException(context.getString(R.string.epg_retry_empty))
                        BufferedInputStream(prepareInputStream(retryStream, url)).use { input ->
                            BufferedOutputStream(tmpFile.outputStream()).use { output ->
                                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                                while (true) {
                                    checkActive()
                                    val count = input.read(buffer)
                                    if (count < 0) break
                                    output.write(buffer, 0, count)
                                }
                            }
                        }
                    }

                    try {
                        return FileInputStream(tmpFile).use { input ->
                            parseXmlTvToIndex(BufferedInputStream(input), channels, checkActive, url)
                        }
                    } catch (retryError: Exception) {
                        if (retryError is kotlinx.coroutines.CancellationException) throw retryError
                        checkActive()
                        // Final fallback: SAX parser (different engine).
                        return FileInputStream(tmpFile).use { input ->
                            val sanitized2 = BackslashEscapeSanitizingInputStream(BufferedInputStream(input))
                            if (channels.size > LargeIptvListChannelCount) throw retryError
                            parseXmlTvNowNextWithSax(BufferedInputStream(sanitized2), channels, checkActive)
                        }
                    }
                } finally {
                    tmpFile.delete()
                    cleanupStaleEpgTempFiles(maxAgeMs = 60_000L)
                }
            }
        }
    }

    // ── Xtream Short EPG (instant loading) ─────────────────────────────

    /**
     * Data class for a single EPG listing returned by the Xtream short EPG APIs.
     * Fields like `title` and `description` are base64-encoded by the server.
     */
    private data class XtreamEpgListing(
        val id: String? = null,
        @SerializedName("epg_id") val epgId: String? = null,
        val title: String? = null,
        val lang: String? = null,
        val start: String? = null,
        val end: String? = null,
        val description: String? = null,
        @SerializedName("channel_id") val channelId: String? = null,
        @SerializedName("start_timestamp") val startTimestamp: String? = null,
        @SerializedName("stop_timestamp") val stopTimestamp: String? = null,
        @SerializedName("stream_id") val streamId: String? = null,
        @SerializedName("has_archive") val hasArchive: Int? = null
    )

    private data class XtreamEpgResponse(
        @SerializedName("epg_listings") val epgListings: List<XtreamEpgListing>? = null
    )

    private fun parseXtreamListingsFromJson(response: JsonObject?): List<XtreamEpgListing> {
        val listingsElement = response?.get("epg_listings") ?: return emptyList()
        return when {
            listingsElement.isJsonArray -> listingsElement.asJsonArray.mapNotNull { it.toXtreamEpgListingOrNull() }
            listingsElement.isJsonObject -> listingsElement.asJsonObject.entrySet()
                .asSequence()
                .mapNotNull { it.value.toXtreamEpgListingOrNull() }
                .toList()
            else -> emptyList()
        }
    }

    private fun trimXtreamListingsToGuideWindow(
        listings: List<XtreamEpgListing>,
        nowMs: Long = System.currentTimeMillis(),
        pastWindowMs: Long = xmlTvPastWindowMs,
        futureWindowMs: Long = xmlTvFutureWindowMs
    ): List<XtreamEpgListing> {
        if (listings.isEmpty()) return listings
        val startBound = nowMs - pastWindowMs
        val endBound = nowMs + futureWindowMs
        return listings.filter { listing ->
            val startMs = listing.startTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.start)
                ?: return@filter false
            val stopMs = listing.stopTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.end)
                ?: return@filter false
            stopMs > startBound && startMs < endBound
        }
    }

    private fun JsonElement.toXtreamEpgListingOrNull(): XtreamEpgListing? =
        runCatching { gson.fromJson(this, XtreamEpgListing::class.java) }.getOrNull()

    private fun List<XtreamEpgListing>.withRequestedStreamId(streamId: Int): List<XtreamEpgListing> {
        if (isEmpty()) return this
        val streamIdValue = streamId.toString()
        return map { listing ->
            if (listing.streamId.isNullOrBlank()) {
                listing.copy(streamId = streamIdValue)
            } else {
                listing
            }
        }
    }

    /**
     * Decode a base64-encoded string from the Xtream short EPG API.
     * Returns the decoded text or the original string if decoding fails.
     */
    private fun decodeBase64Field(encoded: String?): String {
        if (encoded.isNullOrBlank()) return ""
        return try {
            String(Base64.decode(encoded, Base64.DEFAULT), StandardCharsets.UTF_8).trim()
        } catch (_: Exception) {
            encoded.trim()  // Not base64; return raw
        }
    }

    /**
     * Fetch EPG data using the Xtream Codes `get_simple_data_table` API.
     * This returns current/next program data for ALL channels in one lightweight
     * JSON response, instead of downloading a 20-150+ MB XMLTV XML file.
     *
     * Returns null if the API is not supported or fails (caller should fall back to XMLTV).
     */
    /**
     * Determine the Xtream numeric stream identifier for a channel.
     *
     * @param ch The channel to inspect; may contain an explicit `xtreamStreamId` or an `id` with the `xtream:{id}` form.
     * @return The numeric Xtream stream id if present, `null` otherwise.
     */
    private fun resolveXtreamStreamId(ch: IptvChannel): Int? {
        ch.xtreamStreamId?.let { return it }
        if (ch.id.startsWith("xtream:")) {
            return ch.id.removePrefix("xtream:").toIntOrNull()
        }
        val parsed = ch.streamUrl.toHttpUrlOrNull()
        if (parsed != null) {
            val segments = parsed.pathSegments
            val knownPrefix = segments.firstOrNull()?.lowercase(Locale.US)
            if (knownPrefix in setOf("live", "movie", "series") && segments.size >= 4) {
                return segments.lastOrNull()
                    ?.substringBefore('.')
                    ?.toIntOrNull()
            }
            if (resolveXtreamCredentials(ch.streamUrl) != null && segments.size >= 3) {
                return segments.lastOrNull()
                    ?.substringBefore('.')
                    ?.toIntOrNull()
            }
        }
        return null
    }

    private suspend fun fetchXtreamShortEpgForActiveProviders(
        config: IptvConfig,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit,
        listingLimit: Int = xtreamShortEpgLimit
    ): Map<String, IptvNowNext>? {
        val groups = groupXtreamChannelsByCredentials(config, channels)
        if (groups.isEmpty()) return null

        val merged = ConcurrentHashMap<String, IptvNowNext>()
        groups.entries.forEachIndexed { index, (creds, providerChannels) ->
            if (providerChannels.isEmpty()) return@forEachIndexed
            onProgress(
                IptvLoadProgress(
                    context.getString(R.string.iptv_progress_loading_epg_provider, index + 1, groups.size),
                    90 + ((index * 6) / groups.size.coerceAtLeast(1))
                )
            )
            val parsed = fetchXtreamShortEpg(creds, providerChannels, onProgress, listingLimit)
            if (!parsed.isNullOrEmpty()) {
                merged.putAll(parsed)
            }
        }
        return merged.takeIf { hasAnyProgramData(it) }
    }

    private suspend fun fetchXtreamFullEpgForActiveProviders(
        config: IptvConfig,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit
    ): Map<String, IptvNowNext>? {
        val groups = groupXtreamChannelsByCredentials(config, channels)
        if (groups.isEmpty()) return null

        val merged = ConcurrentHashMap<String, IptvNowNext>()
        groups.entries.forEachIndexed { index, (creds, providerChannels) ->
            if (providerChannels.isEmpty()) return@forEachIndexed
            onProgress(
                IptvLoadProgress(
                    context.getString(R.string.iptv_progress_loading_xtream_guide, index + 1, groups.size),
                    90 + ((index * 6) / groups.size.coerceAtLeast(1))
                )
            )
            val parsed = fetchXtreamFullEpg(creds, providerChannels, onProgress)
            if (!parsed.isNullOrEmpty()) {
                merged.putAll(parsed)
            }
        }
        return merged.takeIf { hasAnyProgramData(it) }
    }

    /**
     * Fetches now/next EPG data from every active Stalker portal and returns it keyed by
     * the same `stalker:<portalId>:<origId>` channel ids the portal's channel list already
     * uses (see [StalkerPortalSupport]) — no separate EPG-index source key needed, isolation
     * comes from the channel id prefix like everywhere else.
     */
    internal suspend fun fetchStalkerEpgForActivePortals(
        stalkerApis: Map<String, com.arflix.tv.data.api.StalkerApi>,
        stalkerChannels: List<IptvChannel>
    ): Map<String, IptvNowNext> {
        System.err.println("[Stalker-EPG] fetchStalkerEpgForActivePortals called: apis=${stalkerApis.keys} channels=${stalkerChannels.size}")
        if (stalkerApis.isEmpty() || stalkerChannels.isEmpty()) {
            System.err.println("[Stalker-EPG] Nothing to fetch (apis or channels empty), returning early")
            return emptyMap()
        }
        val nowMs = System.currentTimeMillis()
        val merged = ConcurrentHashMap<String, IptvNowNext>()
        coroutineScope {
            stalkerApis.entries.map { (portalId, api) ->
                async {
                    val portalChannels = stalkerChannels.filter {
                        StalkerPortalSupport.portalIdFromChannelId(it.id) == portalId
                    }
                    if (portalChannels.isEmpty()) {
                        System.err.println("[Stalker-EPG] Portal $portalId: no channels in batch, skipping")
                        return@async
                    }
                    // Stalker's ch_id is the portal's own numeric id, i.e. the last
                    // segment of our "stalker:<portalId>:<origId>" channel id.
                    val origIdToChannelId = portalChannels.associateBy(
                        keySelector = { it.id.substringAfterLast(':') },
                        valueTransform = { it.id }
                    )
                    val portalCacheKey = StalkerEpgPortalCacheKey(portalId, api.epgCacheIdentity)
                    val cachedBulk = stalkerEpgCache[portalCacheKey]
                    var programsByOriginalChannel = if (
                        cachedBulk != null && nowMs - cachedBulk.fetchedAtMs < stalkerEpgCacheTtlMs
                    ) {
                        System.err.println(
                            "[Stalker-EPG] Portal $portalId: using cached getEpg() result " +
                                "(${cachedBulk.programsByChannel.size} channels, age=${nowMs - cachedBulk.fetchedAtMs}ms)"
                        )
                        cachedBulk.programsByChannel
                    } else {
                        System.err.println("[Stalker-EPG] Portal $portalId: requesting getEpg() for ${portalChannels.size} channels")
                        val fetched = runCatching {
                            api.getEpg(
                                notBeforeEpochSeconds = nowMs / 1000L,
                                maxProgramsPerChannel = stalkerBulkProgramsPerChannelLimit
                            )
                        }.getOrElse { error ->
                            if (error is kotlinx.coroutines.CancellationException) throw error
                            System.err.println("[Stalker-EPG] Portal $portalId EPG fetch failed: ${error.message}")
                            emptyList()
                        }
                        val compact = compactStalkerProgramsByChannel(fetched, nowMs)
                        stalkerEpgCache[portalCacheKey] = StalkerEpgCacheEntry(nowMs, compact)
                        compact
                    }
                    System.err.println(
                        "[Stalker-EPG] Portal $portalId: getEpg() retained " +
                            "${programsByOriginalChannel.size} channels"
                    )

                    // Bulk EPG actions confirmed empty on some portal builds -
                    // fall back to get_short_epg per channel. Only for small
                    // batches (see stalkerShortEpgFallbackMaxChannels comment).
                    if (
                        programsByOriginalChannel.isEmpty() &&
                        origIdToChannelId.size <= stalkerShortEpgFallbackMaxChannels
                    ) {
                        System.err.println(
                            "[Stalker-EPG] Portal $portalId: bulk EPG empty, falling back to " +
                                "get_short_epg for ${origIdToChannelId.size} channels"
                        )
                        val gate = Semaphore(stalkerShortEpgFallbackConcurrency)
                        val shortEpgResult = ConcurrentHashMap<String, List<IptvProgram>>()
                        coroutineScope {
                            origIdToChannelId.keys.map { origId ->
                                async {
                                    gate.withPermit {
                                        val shortCacheKey = StalkerShortEpgCacheKey(portalCacheKey, origId)
                                        val cachedShort = stalkerShortEpgCache[shortCacheKey]
                                        val channelPrograms = if (
                                            cachedShort != null &&
                                            nowMs - cachedShort.fetchedAtMs < stalkerShortEpgCacheTtlMs
                                        ) {
                                            cachedShort.programs
                                        } else {
                                            val fetched = runCatching { api.getShortEpg(origId) }.getOrElse { error ->
                                                if (error is kotlinx.coroutines.CancellationException) throw error
                                                emptyList()
                                            }
                                            val compact = compactStalkerProgramsByChannel(fetched, nowMs)[origId].orEmpty()
                                            stalkerShortEpgCache[shortCacheKey] =
                                                StalkerShortEpgCacheEntry(nowMs, compact)
                                            compact
                                        }
                                        shortEpgResult[origId] = channelPrograms
                                    }
                                }
                            }.awaitAll()
                        }
                        programsByOriginalChannel = shortEpgResult
                        System.err.println(
                            "[Stalker-EPG] Portal $portalId: get_short_epg fallback retained " +
                                "${programsByOriginalChannel.count { it.value.isNotEmpty() }} channels"
                        )
                    }
                    if (programsByOriginalChannel.isEmpty()) return@async

                    var skippedUnknownChannel = 0
                    programsByOriginalChannel.forEach { (originalChannelId, channelPrograms) ->
                        val channelId = origIdToChannelId[originalChannelId]
                        if (channelId == null) {
                            skippedUnknownChannel += channelPrograms.size
                            return@forEach
                        }
                        stalkerNowNextFromPrograms(channelPrograms, nowMs)?.let { merged[channelId] = it }
                    }
                    System.err.println(
                        "[Stalker-EPG] Portal $portalId: matched ${merged.size} channels, " +
                            "skippedUnknownChannel=$skippedUnknownChannel"
                    )
                }
            }.awaitAll()
        }
        System.err.println("[Stalker-EPG] fetchStalkerEpgForActivePortals result: ${merged.size} channels with data")
        return merged
    }

    private fun compactStalkerProgramsByChannel(
        programs: List<com.arflix.tv.data.api.StalkerApi.StalkerEpgProgram>,
        nowMs: Long
    ): Map<String, List<IptvProgram>> {
        val byChannel = LinkedHashMap<String, MutableList<IptvProgram>>()
        programs.forEach { program ->
            val channelId = program.chId?.takeIf { it.isNotBlank() } ?: return@forEach
            val startMs = program.startTimestamp?.toLongOrNull()?.let { it * 1000L } ?: return@forEach
            val stopMs = program.stopTimestamp?.toLongOrNull()?.let { it * 1000L } ?: return@forEach
            if (stopMs <= startMs || stopMs <= nowMs) return@forEach
            byChannel.getOrPut(channelId) { mutableListOf() } += IptvProgram(
                title = program.name?.takeIf { it.isNotBlank() }
                    ?: context.getString(R.string.program_no_title),
                description = program.descr?.takeIf { it.isNotBlank() },
                startUtcMillis = startMs,
                endUtcMillis = stopMs
            )
        }
        return byChannel.mapValues { (_, channelPrograms) ->
            channelPrograms.sortedBy { it.startUtcMillis }
        }
    }

    /**
     * Compacts one channel's programs into a now/next slice. Unlike the Xtream/XMLTV
     * paths this keeps no "recent" history — Stalker catchup isn't in scope here (K10).
     */
    internal fun stalkerNowNextFromPrograms(programs: List<IptvProgram>, nowMs: Long): IptvNowNext? {
        val sorted = programs.sortedBy { it.startUtcMillis }
        var now: IptvProgram? = null
        var next: IptvProgram? = null
        var later: IptvProgram? = null
        val upcoming = mutableListOf<IptvProgram>()
        for (p in sorted) {
            when {
                p.isLive(nowMs) -> now = p
                p.startUtcMillis > nowMs && next == null -> next = p
                p.startUtcMillis > nowMs && later == null -> later = p
                p.startUtcMillis > nowMs -> {
                    upcoming.add(p)
                    if (upcoming.size >= epgUpcomingProgramLimit) break
                }
            }
        }
        if (now == null && next == null && later == null && upcoming.isEmpty()) return null
        return IptvNowNext(now = now, next = next, later = later, upcoming = upcoming)
    }

    private fun addChannelIdToLookup(
        target: MutableMap<String, MutableList<String>>,
        rawKey: String?,
        channelId: String
    ) {
        guideKeyCandidates(rawKey).forEach { key ->
            target.getOrPut(key) { mutableListOf() }.let { ids ->
                if (channelId !in ids) ids += channelId
            }
        }
    }

    private fun resolveChannelIdsFromLookup(
        lookup: Map<String, List<String>>,
        rawKey: String?
    ): List<String> {
        if (rawKey.isNullOrBlank()) return emptyList()
        val resolved = LinkedHashSet<String>()
        guideKeyCandidates(rawKey).forEach { key ->
            lookup[key]?.let { resolved.addAll(it) }
        }
        return resolved.toList()
    }

    /**
     * Fetches short EPG listings from an Xtream provider and converts them into now/next program snapshots per channel.
     *
     * @param creds Xtream credentials used to query the provider's short EPG endpoints.
     * @param channels The channels to resolve short EPG for; only channels with resolvable Xtream stream IDs are queried.
     * @param onProgress Callback invoked with load progress updates.
     * @return A map from IPTV channel ID to its derived IptvNowNext when listings were successfully retrieved and considered reliable, or `null` if no listings were available or the fetch was deemed unreliable (e.g., excessive errors).
     */
    private suspend fun fetchXtreamShortEpg(
        creds: XtreamCredentials,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit,
        listingLimit: Int = xtreamShortEpgLimit
    ): Map<String, IptvNowNext>? {
        if (channels.isEmpty()) return null

        // Build lookups: epgId -> channelIds, streamId -> channelIds
        val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        for (ch in channels) {
            ch.epgId?.let { eid ->
                addChannelIdToLookup(epgIdToChannelIds, eid, ch.id)
            }
            ch.tvgName?.let { tvg ->
                addChannelIdToLookup(epgIdToChannelIds, tvg, ch.id)
            }
            ch.variantKey?.let { key ->
                addChannelIdToLookup(epgIdToChannelIds, key, ch.id)
            }
            resolveXtreamStreamId(ch)?.let { sid ->
                streamIdToChannelIds.getOrPut(sid.toString()) { mutableListOf() }.add(ch.id)
            }
        }

        onProgress(IptvLoadProgress("Loading EPG (fast Xtream API)...", 90))

        // Prioritize: favorite channels first, then favorite groups, then rest.
        // Deduplicate stream IDs so we don't fetch the same channel twice.
        val xtreamChannels = channels.filter { resolveXtreamStreamId(it) != null }
        // We're already inside a suspend fun — runBlocking here was pinning
        // the calling coroutine's dispatcher thread while it waited on the
        // DataStore flow. Direct suspension lets the scheduler pick up other
        // work during the (typically ~10 ms) DataStore read.
        val favoriteChannelIds = runCatching { observeFavoriteChannels().first() }
            .getOrDefault(emptyList()).toSet()
        val favoriteGroupNames = runCatching { observeFavoriteGroups().first() }
            .getOrDefault(emptyList()).toSet()

        val favChannels = xtreamChannels.filter { it.id in favoriteChannelIds }
        val favGroupChannels = xtreamChannels.filter { it.id !in favoriteChannelIds && it.group in favoriteGroupNames }
        val alreadyPrioritized = (favChannels.map { it.id } + favGroupChannels.map { it.id }).toSet()
        val rest = xtreamChannels.filter { it.id !in alreadyPrioritized }
        val prioritized = favChannels + favGroupChannels + rest

        // Full guides come from XMLTV, not thousands of per-stream API calls.
        val toFetch = prioritized.take(startupShortEpgChannelLimit)
        val includeStreamsWithoutGuideKey = toFetch.size <= xtreamShortEpgBatchSize
        System.err.println(
            "[EPG] Xtream short EPG: preparing stream sweep for ${toFetch.size} channels " +
                "includeNoGuide=$includeStreamsWithoutGuideKey"
        )
        val representatives = representativeXtreamEpgStreamIds(
            channels = toFetch,
            includeStreamsWithoutGuideKey = includeStreamsWithoutGuideKey
        )
        val streamIds = representatives.streamIds
        System.err.println(
            "[EPG] Xtream short EPG: fetching ${streamIds.size} streams " +
                "for ${toFetch.size}/${xtreamChannels.size} channels " +
                "skippedNoGuide=${representatives.skippedWithoutGuideKey}"
        )
        if (toFetch.isEmpty()) return null

        var errors = 0
        var fetched = 0
        val total = streamIds.size.coerceAtLeast(1)

        val allListings = fetchXtreamEpgListingsAsync(
            creds = creds,
            streamIds = streamIds,
            timeoutMillis = xtreamShortEpgTimeout(streamIds.size),
            listingLimit = listingLimit
        ) { _, hadError ->
            fetched++
            if (hadError) errors++
            if (fetched % 50 == 0) {
                val pct = (90 + ((fetched.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                onProgress(IptvLoadProgress("Loading EPG... $fetched/$total streams", pct))
            }
        }
        System.err.println("[EPG] Xtream short EPG done: ${allListings.size} listings, $fetched fetched, $errors errors")

        if (errors > fetched / 2 && fetched > 20) {
            return null
        }
        if (allListings.isEmpty()) return null

        onProgress(IptvLoadProgress("Parsing EPG data (${allListings.size} listings)...", 98))
        return buildNowNextFromXtreamListings(
            creds = creds,
            listings = allListings,
            epgIdToChannelIds = epgIdToChannelIds,
            streamIdToChannelIds = streamIdToChannelIds,
            channelsById = channels.associateBy { it.id }
        )
    }

    private suspend fun fetchXtreamFullEpg(
        creds: XtreamCredentials,
        channels: List<IptvChannel>,
        onProgress: (IptvLoadProgress) -> Unit
    ): Map<String, IptvNowNext>? {
        if (channels.isEmpty()) return null

        val epgIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        val streamIdToChannelIds = mutableMapOf<String, MutableList<String>>()
        for (ch in channels) {
            ch.epgId?.let { eid ->
                addChannelIdToLookup(epgIdToChannelIds, eid, ch.id)
            }
            ch.tvgName?.let { tvg ->
                addChannelIdToLookup(epgIdToChannelIds, tvg, ch.id)
            }
            ch.variantKey?.let { key ->
                addChannelIdToLookup(epgIdToChannelIds, key, ch.id)
            }
            resolveXtreamStreamId(ch)?.let { sid ->
                streamIdToChannelIds.getOrPut(sid.toString()) { mutableListOf() }.add(ch.id)
            }
        }

        val xtreamChannels = channels.filter { resolveXtreamStreamId(it) != null }
        val representatives = representativeXtreamEpgStreamIds(
            channels = xtreamChannels,
            includeStreamsWithoutGuideKey = false
        )
        val streamIds = representatives.streamIds
        if (streamIds.isEmpty()) return null

        onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_xtream_epg), 90))
        System.err.println(
            "[EPG] Xtream full EPG: fetching ${streamIds.size} representative streams " +
                "for ${xtreamChannels.size}/${channels.size} channels skippedNoGuide=${representatives.skippedWithoutGuideKey}"
        )

        var errors = 0
        var fetched = 0
        val total = streamIds.size.coerceAtLeast(1)
        val channelsById = channels.associateBy { it.id }
        val indexKey = currentEpgIndexKey

        // Process the stream set in batches: fetch a batch's listings, build its
        // now/next slice, PERSIST it to the SQLite EPG index, then drop it so the
        // next batch can be GC'd. Accumulating all 10k+ streams' listings at once
        // exhausted the 384MB heap and OOM-crashed the app. Memory now stays bounded
        // to one batch regardless of playlist size, and the guide still reaches full
        // coverage (the index, which the grid reads from, accumulates every batch).
        val batchSize = 600
        var totalIndexed = 0
        var firstBatch: Map<String, IptvNowNext> = emptyMap()
        for (batchStreamIds in streamIds.chunked(batchSize)) {
            val batchListings = fetchXtreamFullEpgListingsAsync(
                creds = creds,
                streamIds = batchStreamIds,
                timeoutMillis = xtreamFullEpgSweepTimeout(batchStreamIds.size),
                parallelism = xtreamFullEpgSweepConcurrency(batchStreamIds.size)
            ) { _, hadError ->
                fetched++
                if (hadError) errors++
                if (fetched % 50 == 0) {
                    val pct = (90 + ((fetched.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                    onProgress(IptvLoadProgress(context.getString(R.string.iptv_progress_loading_full_epg_streams, fetched, total), pct))
                }
            }
            if (batchListings.isEmpty()) continue
            val batchNowNext = buildNowNextFromXtreamListings(
                creds = creds,
                listings = batchListings,
                epgIdToChannelIds = epgIdToChannelIds,
                streamIdToChannelIds = streamIdToChannelIds,
                channelsById = channelsById,
                forceCatchupHistory = true
            )
            if (batchNowNext.isNotEmpty()) {
                if (indexKey.isNotBlank()) {
                    runCatching { epgIndex.replaceChannels(indexKey, batchNowNext, System.currentTimeMillis()) }
                }
                totalIndexed += batchNowNext.size
                if (firstBatch.isEmpty()) firstBatch = batchNowNext
            }
            // batchListings + batchNowNext now eligible for GC before the next batch.
        }
        System.err.println("[EPG] Xtream full EPG done (batched): $fetched fetched, $errors errors, indexed=$totalIndexed channels")

        if (totalIndexed == 0) return null
        // Return only the first batch for the in-memory cache: the full guide already
        // lives in the SQLite index (which the UI queries per visible window), so we
        // never hand the whole 10k-channel map back to the caller (that re-OOM'd).
        return firstBatch
    }

    private fun representativeXtreamEpgStreamIds(
        channels: List<IptvChannel>,
        includeStreamsWithoutGuideKey: Boolean
    ): XtreamEpgRepresentativeStreams {
        val withGuideKey = LinkedHashMap<String, Int>()
        val withoutGuideKey = LinkedHashSet<Int>()
        var skippedWithoutGuideKey = 0

        channels.forEach { channel ->
            val streamId = resolveXtreamStreamId(channel) ?: return@forEach
            val guideKey = representativeGuideKey(channel)
            if (guideKey.isNullOrBlank()) {
                if (includeStreamsWithoutGuideKey) {
                    withoutGuideKey += streamId
                } else {
                    skippedWithoutGuideKey++
                }
            } else {
                withGuideKey.putIfAbsent(guideKey, streamId)
            }
        }

        val streamIds = buildList {
            addAll(withGuideKey.values)
            addAll(withoutGuideKey)
        }.distinct()
        return XtreamEpgRepresentativeStreams(streamIds, skippedWithoutGuideKey)
    }

    private data class XtreamEpgRepresentativeStreams(
        val streamIds: List<Int>,
        val skippedWithoutGuideKey: Int
    )

    private fun representativeGuideKey(channel: IptvChannel): String? {
        return sequenceOf(channel.epgId, channel.tvgName)
            .filterNotNull()
            .map { normalizeLooseKey(it) }
            .firstOrNull { it.isNotBlank() }
    }



    /**
     * Fetches short EPG listings for the given Xtream stream IDs in parallel.
     *
 * Requests the Xtream `get_short_epg` endpoint for each stream ID (first with a `limit=24`,
     * then a fallback without `limit` if the first response is empty). Records one sample log
     * for the first non-empty response observed and invokes `onStreamProcessed` for each stream
     * to report whether that stream encountered an error.
     *
     * @param creds Xtream credentials and base URL used to construct API requests.
     * @param streamIds The list of Xtream stream IDs to query.
     * @param onStreamProcessed Callback invoked once per stream ID with `(streamId, hadError)`,
     *   where `hadError` is `true` if the request sequence for that stream failed.
     * @return A flattened list of all `XtreamEpgListing` objects returned by the provider
     *   (empty if no listings were retrieved).
     */
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private suspend fun fetchXtreamEpgListingsAsync(
        creds: XtreamCredentials,
        streamIds: List<Int>,
        timeoutMillis: Long = 180_000L,
        listingLimit: Int = xtreamShortEpgLimit,
        allowUnboundedFallback: Boolean = false,
        onStreamProcessed: (Int, Boolean) -> Unit = { _, _ -> }
    ): List<XtreamEpgListing> {
        // The repository-wide budget also covers overlapping viewport and catch-up requests.
        val distinctStreamIds = streamIds.distinct()
        if (distinctStreamIds.isEmpty()) return emptyList()
        val gate = Semaphore(xtreamShortEpgConcurrency)
        val listingsResult = ConcurrentLinkedQueue<XtreamEpgListing>()
        val simpleFallbacks = AtomicInteger(0)
        val completed = withTimeoutOrNull(timeoutMillis) {
            withContext(Dispatchers.IO.limitedParallelism(xtreamShortEpgConcurrency)) {
                val sampleLogged = AtomicBoolean(false)
                distinctStreamIds.chunked(xtreamShortEpgBatchSize).forEach { batch ->
                    batch.map { sid ->
                        async {
                            gate.withPermit {
                                var hadError = false
                                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                    "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=$listingLimit"
                                var listings: List<XtreamEpgListing>? = null
                                try {
                                    var resp: XtreamEpgResponse? = requestJson(
                                        url,
                                        XtreamEpgResponse::class.java,
                                        client = xtreamGuideHttpClient
                                    )
                                    listings = resp?.epgListings
                                    if (resp != null && listings.isNullOrEmpty() && allowUnboundedFallback) {
                                        val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                            "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                                        resp = requestJson(
                                            fallbackUrl,
                                            XtreamEpgResponse::class.java,
                                            client = xtreamGuideHttpClient
                                        )
                                        listings = resp?.epgListings
                                    }
                                    // A deferred/failed request is not proof that this provider
                                    // has no guide. Do not give it the ten-minute empty-feed TTL.
                                    if (resp == null) hadError = true
                                    if (resp != null && listings.isNullOrEmpty() && allowUnboundedFallback) {
                                        val simpleUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                            "&password=${creds.password}&action=get_simple_data_table&stream_id=$sid"
                                        val simpleResp: JsonObject? = requestJson(
                                            url = simpleUrl,
                                            type = JsonObject::class.java,
                                            client = xtreamCatchupGuideHttpClient
                                        )
                                        if (simpleResp == null) {
                                            hadError = true
                                        }
                                        listings = trimXtreamListingsToGuideWindow(parseXtreamListingsFromJson(simpleResp))
                                        if (!listings.isNullOrEmpty()) {
                                            simpleFallbacks.incrementAndGet()
                                        }
                                    }
                                    if (!listings.isNullOrEmpty()) {
                                        val taggedListings = listings.withRequestedStreamId(sid)
                                        listingsResult.addAll(taggedListings)
                                        if (sampleLogged.compareAndSet(false, true)) {
                                            val sample = taggedListings.first()
                                            System.err.println("[EPG] Sample response for stream_id=$sid: channelId=${sample.channelId} epgId=${sample.epgId} streamId=${sample.streamId} start=${sample.start} startTs=${sample.startTimestamp} title=${sample.title?.take(40)}")
                                        }
                                    }
                                } catch (error: Exception) {
                                    if (error is kotlinx.coroutines.CancellationException) throw error
                                    hadError = true
                                }
                                onStreamProcessed(sid, hadError)
                            }
                        }
                    }.awaitAll()
                }
            }
        }
        if (completed == null) {
            System.err.println(
                "[EPG] Xtream short EPG timed out after ${timeoutMillis}ms; " +
                    "keeping ${listingsResult.size} fetched listings"
            )
        }
        val fallbackCount = simpleFallbacks.get()
        if (fallbackCount > 0) {
            System.err.println("[EPG] Xtream simple-data fallback filled $fallbackCount short-empty streams")
        }
        return listingsResult.toList()
    }

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private suspend fun fetchXtreamFullEpgListingsAsync(
        creds: XtreamCredentials,
        streamIds: List<Int>,
        timeoutMillis: Long = 16_000L,
        parallelism: Int = 4,
        onStreamProcessed: (Int, Boolean) -> Unit = { _, _ -> }
    ): List<XtreamEpgListing> {
        val distinctStreamIds = streamIds.distinct()
        if (distinctStreamIds.isEmpty()) return emptyList()
        val safeParallelism = parallelism.coerceIn(1, 32)
        val gate = Semaphore(safeParallelism)
        val listingsResult = ConcurrentLinkedQueue<XtreamEpgListing>()
        val completed = withTimeoutOrNull(timeoutMillis) {
            withContext(Dispatchers.IO.limitedParallelism(safeParallelism)) {
                distinctStreamIds.map { sid ->
                    async {
                        gate.withPermit {
                            var hadError = false
                            val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                "&password=${creds.password}&action=get_simple_data_table&stream_id=$sid"
                            try {
                                val resp: JsonObject? = requestJson(
                                    url = url,
                                    type = JsonObject::class.java,
                                    client = xtreamCatchupGuideHttpClient
                                )
                                if (resp == null) {
                                    hadError = true
                                }
                                val listings = trimXtreamListingsToGuideWindow(
                                    parseXtreamListingsFromJson(resp),
                                    pastWindowMs = IptvGuideHistory.MAX_WINDOW_MS,
                                )
                                    .withRequestedStreamId(sid)
                                if (listings.isNotEmpty()) {
                                    listingsResult.addAll(listings)
                                }
                            } catch (error: Exception) {
                                if (error is kotlinx.coroutines.CancellationException) throw error
                                hadError = true
                            }
                            onStreamProcessed(sid, hadError)
                        }
                    }
                }.awaitAll()
            }
        }
        if (completed == null) {
            System.err.println(
                "[EPG] Xtream full catchup EPG timed out after ${timeoutMillis}ms; " +
                    "keeping ${listingsResult.size} fetched listings"
            )
        }
        return listingsResult.toList()
    }

    private fun xtreamShortEpgTimeout(streamCount: Int): Long =
        when {
            streamCount > 4_000 -> 90_000L
            streamCount > 1_200 -> 45_000L
            streamCount > 256 -> 18_000L
            streamCount > 64 -> 8_000L
            streamCount > 16 -> 5_000L
            else -> 2_500L
        }

    private fun xtreamFullCatchupEpgTimeout(streamCount: Int): Long =
        when {
            streamCount > 2 -> 30_000L
            streamCount > 1 -> 24_000L
            else -> 18_000L
        }

    private fun xtreamFullEpgSweepTimeout(streamCount: Int): Long =
        when {
            streamCount > 8_000 -> 420_000L
            streamCount > 4_000 -> 300_000L
            streamCount > 1_200 -> 180_000L
            streamCount > 256 -> 90_000L
            else -> 45_000L
        }

    private fun xtreamFullEpgSweepConcurrency(streamCount: Int): Int =
        when {
            streamCount > 4_000 -> 24
            streamCount > 1_200 -> 18
            streamCount > 256 -> 12
            else -> 6
        }

    /**
     * Constructs a mapping of IPTV channel IDs to their current and upcoming program windows from a list of Xtream short EPG listings.
     *
     * The function groups listings by resolved channel (using `epgIdToChannelIds` and `streamIdToChannelIds`), orders programs by start time, and populates `now`, `next`, `later`, `upcoming`, and `recent` slots for each channel.
     *
     * @param listings Xtream short EPG listings to convert into program windows.
     * @param epgIdToChannelIds Map from EPG identifier to the list of IPTV channel IDs that share that EPG id.
     * @param streamIdToChannelIds Map from Xtream stream identifier to the list of IPTV channel IDs that correspond to that stream.
     * @return A map keyed by IPTV channel ID with values of `IptvNowNext`. Each `IptvNowNext` may contain `now`, `next`, `later`, a truncated `upcoming` list (at most 12 items), and a `recent` list of programs that ended within the recent cutoff window.
     */
    private fun buildNowNextFromXtreamListings(
        creds: XtreamCredentials,
        listings: List<XtreamEpgListing>,
        epgIdToChannelIds: Map<String, List<String>>,
        streamIdToChannelIds: Map<String, List<String>>,
        channelsById: Map<String, IptvChannel> = emptyMap(),
        forceCatchupHistory: Boolean = false
    ): Map<String, IptvNowNext> {
        // Detect and save server timezone offset
        val sampleListing = listings.firstOrNull { it.startTimestamp != null && !it.start.isNullOrBlank() }
        if (sampleListing != null) {
            val startMs = sampleListing.startTimestamp?.toLongOrNull()?.let { it * 1000L }
            val parsedMs = parseXtreamDateTime(sampleListing.start)
            if (startMs != null && parsedMs != null) {
                val offset = parsedMs - startMs
                if (Math.abs(offset) <= 18 * 60 * 60 * 1000L) {
                    saveServerOffset(creds, offset)
                    System.err.println("[EPG] Detected Xtream Server timezone offset: ${offset / 3600000.0} hours")
                }
            }
        }

        val nowMs = System.currentTimeMillis()
        val oldestRecentCutoff = oldestRecentCutoff(channelsById.values, nowMs, forceCatchupHistory)

        // Group listings by channel.
        // Try matching by: epg_id (channelId field), then stream_id.
        data class ChannelPrograms(val programs: MutableList<IptvProgram> = mutableListOf())
        val channelProgramsMap = mutableMapOf<String, ChannelPrograms>()

        for (listing in listings) {
            val startMs = listing.startTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.start)
                ?: continue
            val stopMs = listing.stopTimestamp?.toLongOrNull()?.let { it * 1000L }
                ?: parseXtreamDateTime(listing.end)
                ?: continue

            // Skip programs that ended before the oldest possible catchup window.
            if (stopMs < oldestRecentCutoff) continue

            val title = decodeBase64Field(listing.title).ifBlank { context.getString(R.string.program_no_title) }
            val description = decodeBase64Field(listing.description).takeIf { it.isNotBlank() }

            val program = IptvProgram(
                title = title,
                description = description,
                startUtcMillis = startMs,
                endUtcMillis = stopMs,
                catchupAvailable = listing.hasArchive?.let { it > 0 }
            )

            // Resolve which IptvChannel IDs this listing maps to
            val resolvedChannelIds = mutableSetOf<String>()

            val exactStreamIds = listing.streamId
                ?.let { sid -> streamIdToChannelIds[sid] }
                .orEmpty()
            if (forceCatchupHistory && exactStreamIds.isNotEmpty()) {
                resolvedChannelIds.addAll(exactStreamIds)
                // Some providers only expose catch-up on one quality variant
                // while all variants share the same EPG identity. Keep rows
                // separate, but fan the guide history out inside the same
                // provider/EPG family so 4K/FHD/HD/SD rows can all show the
                // same aired programme list.
                exactStreamIds.forEach { exactChannelId ->
                    channelsById[exactChannelId]?.let { exactChannel ->
                        resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, exactChannel.epgId))
                        resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, exactChannel.tvgName))
                        exactChannel.variantKey?.let { variantKey ->
                            resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, variantKey))
                        }
                    }
                }
            } else {
                // Match by epg_id / channel_id field
                listing.channelId?.let { cid ->
                    resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, cid))
                }
                listing.lang?.let { lang ->
                    resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, lang))
                }
                listing.epgId?.let { eid ->
                    // epg_id can be the stream_id in some providers
                    streamIdToChannelIds[eid]?.let { resolvedChannelIds.addAll(it) }
                    resolvedChannelIds.addAll(resolveChannelIdsFromLookup(epgIdToChannelIds, eid))
                }
                resolvedChannelIds.addAll(exactStreamIds)
            }

            for (channelId in resolvedChannelIds) {
                channelProgramsMap.getOrPut(channelId) { ChannelPrograms() }.programs.add(program)
            }
        }

        System.err.println("[EPG] buildNowNext: ${listings.size} listings -> ${channelProgramsMap.size} channels mapped (epgIdMap=${epgIdToChannelIds.size}, streamIdMap=${streamIdToChannelIds.size})")
        if (channelProgramsMap.isEmpty() && listings.isNotEmpty()) {
            // Log first few listings to debug mapping issues
            listings.take(3).forEach { l ->
                System.err.println("[EPG]   sample listing: channelId=${l.channelId} epgId=${l.epgId} streamId=${l.streamId} start=${l.start} startTs=${l.startTimestamp} title=${l.title?.take(30)}")
            }
        }

        // Build NowNext from sorted programs
        val result = mutableMapOf<String, IptvNowNext>()
        for ((channelId, cp) in channelProgramsMap) {
            val sorted = cp.programs.sortedBy { it.startUtcMillis }
            var now: IptvProgram? = null
            var next: IptvProgram? = null
            var later: IptvProgram? = null
            val upcoming = mutableListOf<IptvProgram>()
            val recent = mutableListOf<IptvProgram>()

            if (sorted.isNotEmpty()) {
                val recentCutoff = recentCutoffForChannel(channelsById[channelId], nowMs, forceCatchupHistory)
                var startIndex = sorted.binarySearch { it.startUtcMillis.compareTo(recentCutoff) }
                if (startIndex < 0) {
                    startIndex = -(startIndex + 1)
                }

                // Walk backward to include programs starting before recentCutoff but ending after
                while (startIndex > 0 && sorted[startIndex - 1].endUtcMillis > recentCutoff) {
                    startIndex--
                }

                for (i in startIndex until sorted.size) {
                    val p = sorted[i]
                    when {
                        p.endUtcMillis <= nowMs && p.endUtcMillis > recentCutoff -> {
                            addRecentCandidate(
                                recent = recent,
                                candidate = p,
                                limit = recentProgramLimitForChannel(channelsById[channelId], forceCatchupHistory)
                            )
                        }
                        p.isLive(nowMs) -> now = p
                        p.startUtcMillis > nowMs && next == null -> next = p
                        p.startUtcMillis > nowMs && later == null -> later = p
                        p.startUtcMillis > nowMs -> {
                            upcoming.add(p)
                            if (upcoming.size >= epgUpcomingProgramLimit) {
                                break // We have enough upcoming programs
                            }
                        }
                    }
                }
            }

            result[channelId] = IptvNowNext(
                now = now,
                next = next,
                later = later,
                upcoming = upcoming,
                recent = recent
            )
        }

        val withNow = result.values.count { it.now != null }
        val withNext = result.values.count { it.next != null }
        val withRecent = result.values.count { it.recent.isNotEmpty() }
        System.err.println("[EPG] buildNowNext result: ${result.size} channels, $withNow with NOW, $withNext with NEXT, $withRecent with RECENT")
        return result
    }

    /**
     * Parse Xtream datetime strings like "2024-01-01 12:00:00" to epoch millis.
     */
    private fun parseXtreamDateTime(dateStr: String?): Long? {
        if (dateStr.isNullOrBlank()) return null
        return try {
            val formatter = IptvRepoDateRegexes.SPACE_SECOND_PATTERN
            val local = java.time.LocalDateTime.parse(dateStr, formatter)
            // Xtream times are typically UTC
            local.atZone(java.time.ZoneOffset.UTC).toInstant().toEpochMilli()
        } catch (_: Exception) {
            null
        }
    }

    private fun saveServerOffset(creds: XtreamCredentials, offset: Long) {
        runCatching {
            context.getSharedPreferences("arvio_iptv_prefs", android.content.Context.MODE_PRIVATE)
                .edit()
                .putLong(xtreamServerOffsetKey(creds), offset)
                .apply()
        }
    }

    private fun getServerOffset(creds: XtreamCredentials): Long {
        return runCatching {
            context.getSharedPreferences("arvio_iptv_prefs", android.content.Context.MODE_PRIVATE)
                .getLong(xtreamServerOffsetKey(creds), 0L)
        }.getOrDefault(0L)
    }

    private fun xtreamServerOffsetKey(creds: XtreamCredentials): String =
        "xtream_server_offset_${xtreamDiskCacheHash(creds)}"

    /**
     * Some providers return malformed XML text that includes JSON-style backslash escapes
     * (for example: \" or \n) inside element values. KXmlParser can fail hard on this.
     * This filter normalizes the most common escapes into plain text so XML parsing can continue.
     */
    private class BackslashEscapeSanitizingInputStream(
        input: InputStream
    ) : FilterInputStream(input) {
        override fun read(): Int {
            val buf = ByteArray(1)
            val read = read(buf, 0, 1)
            return if (read <= 0) -1 else buf[0].toInt() and 0xFF
        }

        override fun read(b: ByteArray, off: Int, len: Int): Int {
            if (len == 0) return 0

            // Read from the underlying stream
            val rawRead = super.read(b, off, len)
            if (rawRead == -1) return -1

            var writeIdx = off
            var readIdx = off
            val endIdx = off + rawRead

            while (readIdx < endIdx) {
                val current = b[readIdx++].toInt() and 0xFF
                if (current == '\\'.code) {
                    val next = if (readIdx < endIdx) {
                        b[readIdx++].toInt() and 0xFF
                    } else {
                        // The backslash is at the very end of the read chunk.
                        // Fetch the next byte from the underlying stream.
                        val n = super.read()
                        if (n == -1) -1 else n
                    }

                    if (next == -1) {
                        b[writeIdx++] = '\\'.toByte()
                    } else {
                        val mapped = when (next.toChar()) {
                            '\\' -> '\\'.code
                            '"' -> '"'.code
                            '\'' -> '\''.code
                            '/' -> '/'.code
                            'n' -> '\n'.code
                            'r' -> '\r'.code
                            't' -> '\t'.code
                            'b' -> '\b'.code
                            'f' -> 0x0C
                            else -> next
                        }

                        val finalChar = if (mapped in 0x00..0x1F && mapped != '\n'.code && mapped != '\r'.code && mapped != '\t'.code) {
                            ' '.code
                        } else {
                            mapped
                        }
                        b[writeIdx++] = finalChar.toByte()
                    }
                } else {
                    val finalChar = if (current in 0x00..0x1F && current != '\n'.code && current != '\r'.code && current != '\t'.code) {
                        ' '.code
                    } else {
                        current
                    }
                    b[writeIdx++] = finalChar.toByte()
                }
            }

            return writeIdx - off
        }
    }

    private fun parseM3u(
        input: InputStream,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {
        val channels = mutableListOf<IptvChannel>()
        val seenChannelIds = HashSet<String>()
        var pendingMetadata: String? = null
        val pendingHeaders = linkedMapOf<String, String>()
        val pendingDrmProps = linkedMapOf<String, String>()
        var parsedCount = 0

        BufferedReader(InputStreamReader(input, StandardCharsets.UTF_8), 256 * 1024).use { reader ->
            while (true) {
                val rawLine = reader.readLine() ?: break
                val line = rawLine.trim()
                if (line.isEmpty()) continue

                if (line.startsWith("#EXTM3U", ignoreCase = true)) {
                    extractM3uDeclaredEpgUrls(line)
                        .forEach { discoveredM3uEpgUrls.add(it) }
                    continue
                }

                if (line.startsWith("#EXTINF", ignoreCase = true)) {
                    pendingMetadata = line
                    pendingHeaders.clear()
                    pendingDrmProps.clear()
                    mergeM3uHeaderOptions(pendingHeaders, pendingDrmProps, line)
                    continue
                }

                if (line.startsWith("#EXTVLCOPT", ignoreCase = true) || line.startsWith("#KODIPROP", ignoreCase = true)) {
                    mergeM3uHeaderOptions(pendingHeaders, pendingDrmProps, line)
                    continue
                }

                if (line.startsWith("#")) continue

                val metadata = pendingMetadata
                pendingMetadata = null
                val attributes = parseM3uAttributes(metadata)

                val streamUrl = normalizeIptvStreamUrl(line)
                val epgId = attributes["tvg-id"]
                val id = buildChannelId(streamUrl, epgId)
                if (!seenChannelIds.add(id)) {
                    pendingHeaders.clear()
                    continue
                }

                val tvgName = attributes["tvg-name"]
                val channelName = tvgName?.takeIf { it.isNotBlank() } ?: extractChannelName(metadata)
                val groupTitle = attributes["group-title"]?.takeIf { it.isNotBlank() } ?: "Uncategorized"
                val logo = normalizeIptvLogoUrlOrNull(attributes["tvg-logo"])
                val catchupType = attributes["catchup"]
                val catchupDays = firstM3uAttribute(attributes, "catchup-days", "timeshift")?.toIntOrNull() ?: 0
                val catchupSource = attributes["catchup-source"]
                val providerChannelNumber = firstM3uAttribute(
                    attributes,
                    "tvg-chno",
                    "tvg-ch-number",
                    "channel-number",
                    "ch-number",
                    "number"
                )
                val language = firstM3uAttribute(attributes, "tvg-language", "tvg-lang", "language", "lang")
                val country = firstM3uAttribute(attributes, "tvg-country", "country")
                val qualityLabel = firstM3uAttribute(attributes, "quality", "tvg-quality", "resolution")
                    ?: inferQualityLabel(channelName, groupTitle)
                val inlineHeaders = extractInlineRequestHeaders(attributes)
                val requestHeaders = if (pendingHeaders.isEmpty()) {
                    if (inlineHeaders.isEmpty()) {
                        emptyMap()
                    } else {
                        inlineHeaders.filterValues { it.isNotBlank() }
                    }
                } else {
                    if (inlineHeaders.isEmpty()) {
                        pendingHeaders.filterValues { it.isNotBlank() }
                    } else {
                        (pendingHeaders + inlineHeaders).filterValues { it.isNotBlank() }
                    }
                }

                channels += IptvChannel(
                    id = id,
                    name = channelName,
                    streamUrl = streamUrl,
                    group = groupTitle,
                    logo = logo,
                    epgId = epgId,
                    rawTitle = metadata ?: channelName,
                    catchupDays = catchupDays,
                    catchupType = catchupType,
                    catchupSource = catchupSource,
                    tvgName = tvgName,
                    providerChannelNumber = providerChannelNumber,
                    requestHeaders = requestHeaders,
                    language = language,
                    country = country,
                    qualityLabel = qualityLabel,
                    variantKey = buildChannelVariantKey(tvgName ?: channelName, groupTitle, epgId),
                    drmInfo = buildDrmInfo(pendingDrmProps)
                )
                pendingHeaders.clear()
                pendingDrmProps.clear()
                parsedCount++
                if (parsedCount % 5000 == 0) {
                    onProgress(IptvLoadProgress("Parsing channels... $parsedCount found", 85))
                }
            }
        }

        onProgress(IptvLoadProgress("Finalizing ${channels.size} channels...", 95))
        return channels
    }

    private fun extractM3uDeclaredEpgUrls(line: String): List<String> {
        return listOf("url-tvg", "x-tvg-url", "tvg-url")
            .mapNotNull { attr -> extractAttr(line, attr) }
            .flatMap { normalizeEpgInputs(it) }
            .map { it.trim() }
            .filter { it.startsWith("http", ignoreCase = true) }
            .distinct()
    }

    private fun parseXmlTvToIndex(
        input: InputStream,
        channels: List<IptvChannel>,
        checkActive: () -> Unit,
        feedUrl: String,
    ): Map<String, IptvNowNext> {
        if (channels.size <= LargeIptvListChannelCount || currentEpgIndexKey.isBlank()) {
            return parseXmlTvNowNext(input, channels, checkActive)
        }
        val key = currentEpgIndexKey
        val updatedAt = System.currentTimeMillis()
        val startedAt = android.os.SystemClock.elapsedRealtime()
        val pending = LinkedHashMap<String, MutableList<IptvProgram>>()
        val pendingAliases = LinkedHashMap<String, List<String>>()
        val registered = HashSet<String>()
        val feedPrefix = "@xml:" + MessageDigest.getInstance("SHA-256")
            .digest(feedUrl.toByteArray(StandardCharsets.UTF_8)).take(16).joinToString("") { "%02x".format(it) } + ":"
        val sampleIds = LinkedHashSet<String>()
        var pendingCount = 0
        var indexedCount = 0
        fun flush() {
            checkActive()
            if (pendingCount == 0) return
            epgIndex.replaceChannels(key, pending.mapValues { IptvNowNext(upcoming = it.value) }, updatedAt, pendingAliases)
            indexedCount += pendingCount
            pending.clear()
            pendingAliases.clear()
            pendingCount = 0
        }
        val threadId = android.os.Process.myTid()
        val priority = android.os.Process.getThreadPriority(threadId)
        try {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
            parseXmlTvNowNext(input, channels, checkActive) { xmlId, matches, program ->
                val guideId = feedPrefix + xmlId
                if (registered.add(guideId)) {
                    pendingAliases[guideId] = matches.map { it.id }
                    matches.forEach { if (sampleIds.size < 16) sampleIds.add(it.id) }
                }
                pending.getOrPut(guideId) { ArrayList() }.add(program)
                if (++pendingCount >= 4_096) flush()
            }
            flush()
            checkActive()
            epgIndex.finishStreamingRefresh(key, updatedAt)
            System.err.println("[EPG-Timing] streamed XMLTV rows=$indexedCount channels=${channels.size} elapsed_ms=${android.os.SystemClock.elapsedRealtime() - startedAt}")
            return epgIndex.loadNowNext(key, sampleIds)
        } finally {
            android.os.Process.setThreadPriority(priority)
        }
    }

    internal fun parseXmlTvNowNext(
        input: InputStream,
        channels: List<IptvChannel>,
        checkActive: () -> Unit = {},
        onProgram: ((String, List<IptvChannel>, IptvProgram) -> Unit)? = null,
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val nowUtc = System.currentTimeMillis()
        val recentCutoff = xmlTvRecentCutoff(channels, nowUtc)
        val futureCutoff = nowUtc + xmlTvFutureWindowMs
        val recentCutoffByChannelId = if (onProgram == null) buildRecentCutoffByChannelId(channels, nowUtc) else emptyMap()
        val recentLimitByChannelId = if (onProgram == null) buildRecentLimitByChannelId(channels) else emptyMap()

        val keyLookup = buildChannelKeyLookup(channels)
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()
        val xmlChannelResolveCache = mutableMapOf<String, List<IptvChannel>>()
        val nowCandidates = mutableMapOf<String, IptvProgram?>()
        val upcomingCandidates = mutableMapOf<String, MutableList<IptvProgram>>()
        val recentCandidates = mutableMapOf<String, MutableList<IptvProgram>>()

        var currentXmlChannelId: String? = null
        var currentChannelKey: String? = null
        var currentStart = 0L
        var currentStop = 0L
        var currentTitle: String? = null
        var currentDesc: String? = null

        val parser = android.util.Xml.newPullParser()
        var currentArtwork: String? = null
        var currentCategory: String? = null
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(input, null)
        var eventType = parser.eventType
        var parsedEvents = 0

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if ((parsedEvents++ and 0xFF) == 0) {
                checkActive()
                if (onProgram == null) abortLargeEpgWorkIfInteractive(channels.size)
            }
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    when (parser.name.lowercase(Locale.US)) {
                        "channel" -> {
                            currentXmlChannelId = normalizeChannelKey(parser.getAttributeValue(null, "id") ?: "")
                        }
                        "display-name" -> {
                            val xmlId = currentXmlChannelId
                            val displayText = parser.nextText().orEmpty()
                            if (!xmlId.isNullOrBlank()) {
                                val display = normalizeChannelKey(displayText)
                                if (display.isNotBlank()) {
                                    val isUseful = guideKeyCandidates(display).any { it in keyLookup }
                                    if (isUseful) {
                                        xmlChannelNameMap.getOrPut(xmlId) { mutableSetOf() }.add(display)
                                    }
                                }
                            }
                        }
                        "programme" -> {
                            val rawKey = normalizeChannelKey(parser.getAttributeValue(null, "channel") ?: "")
                            // Most entries in a large XMLTV file are not in this
                            // viewport. Do not parse their dates/titles/descriptions.
                            val resolved = xmlChannelResolveCache.getOrPut(rawKey) {
                                resolveXmlTvChannels(rawKey, xmlChannelNameMap, keyLookup)
                            }
                            val start = if (resolved.isNotEmpty()) parseXmlTvDate(parser.getAttributeValue(null, "start")) else 0L
                            val stop = if (resolved.isNotEmpty()) parseXmlTvDate(parser.getAttributeValue(null, "stop")) else 0L
                            // Skip programmes that ended before the recent cutoff
                            if (resolved.isEmpty() || (stop > 0L && stop <= recentCutoff) || (start > 0L && start >= futureCutoff)) {
                                currentChannelKey = null
                            } else {
                                currentChannelKey = rawKey
                                currentStart = start
                                currentStop = stop
                                currentTitle = null
                                currentDesc = null
                                currentArtwork = null
                                currentCategory = null
                            }
                        }
                        "title" -> {
                            if (currentChannelKey != null) {
                                currentTitle = parser.nextText().trim().ifBlank { null }
                            }
                        }
                        "desc" -> {
                            if (currentChannelKey != null) {
                                currentDesc = parser.nextText().trim().ifBlank { null }
                            }
                        }
                        "icon" -> if (currentChannelKey != null) {
                            currentArtwork = com.arflix.tv.data.model.safeSportsImage(parser.getAttributeValue(null, "src"))
                        }
                        "category" -> if (currentChannelKey != null) {
                            val value = parser.nextText().trim()
                            currentCategory = listOfNotNull(currentCategory, value).joinToString(" ").take(200)
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    when {
                        parser.name.equals("channel", ignoreCase = true) -> {
                            currentXmlChannelId = null
                        }
                        parser.name.equals("programme", ignoreCase = true) -> {
                        val key = currentChannelKey
                        val resolvedChannels = key?.let {
                            xmlChannelResolveCache[it] ?: resolveXmlTvChannels(it, xmlChannelNameMap, keyLookup)
                                .also { resolved -> xmlChannelResolveCache[it] = resolved }
                        }.orEmpty()
                        if (resolvedChannels.isNotEmpty() && currentStop > currentStart) {
                            val program = IptvProgram(
                                title = currentTitle ?: context.getString(R.string.program_unknown),
                                description = currentDesc,
                                startUtcMillis = currentStart,
                                endUtcMillis = currentStop,
                                artworkUrl = currentArtwork,
                                category = currentCategory,
                            )

                            if (onProgram != null) {
                                onProgram(key!!, resolvedChannels, program)
                            } else resolvedChannels.forEach { channel ->
                                val nowProgram = pickNow(nowCandidates[channel.id], program, nowUtc)
                                nowCandidates[channel.id] = nowProgram
                                if (program.startUtcMillis > nowUtc) {
                                    val future = upcomingCandidates.getOrPut(channel.id) { mutableListOf() }
                                    addUpcomingCandidate(future, program, limit = epgUpcomingProgramLimit)
                                } else if (program.endUtcMillis <= nowUtc && program.endUtcMillis > recentCutoffByChannelId.getValue(channel.id)) {
                                    val recent = recentCandidates.getOrPut(channel.id) { mutableListOf() }
                                    val limit = recentLimitByChannelId.getValue(channel.id)
                                    addRecentCandidate(recent, program, limit)
                                }
                            }
                        }
                        currentChannelKey = null
                    }
                    }
                }
            }
            eventType = parser.next()
        }

        return if (onProgram != null) emptyMap()
        else buildParsedNowNextResult(channels, nowCandidates, upcomingCandidates, recentCandidates)
    }

    private fun parseXmlTvNowNextWithSax(
        input: InputStream,
        channels: List<IptvChannel>,
        checkActive: () -> Unit = {},
    ): Map<String, IptvNowNext> {
        if (channels.isEmpty()) return emptyMap()

        val nowUtc = System.currentTimeMillis()
        val recentCutoff = xmlTvRecentCutoff(channels, nowUtc)
        val futureCutoff = nowUtc + xmlTvFutureWindowMs
        val recentCutoffByChannelId = buildRecentCutoffByChannelId(channels, nowUtc)
        val recentLimitByChannelId = buildRecentLimitByChannelId(channels)

        val keyLookup = buildChannelKeyLookup(channels)
        val xmlChannelNameMap = mutableMapOf<String, MutableSet<String>>()
        val xmlChannelResolveCache = mutableMapOf<String, List<IptvChannel>>()
        val nowCandidates = mutableMapOf<String, IptvProgram?>()
        val upcomingCandidates = mutableMapOf<String, MutableList<IptvProgram>>()
        val recentCandidates = mutableMapOf<String, MutableList<IptvProgram>>()

        val factory = SAXParserFactory.newInstance().apply {
            isNamespaceAware = false
            isValidating = false
            runCatching { setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true) }
            runCatching { setFeature("http://xml.org/sax/features/validation", false) }
            runCatching { setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false) }
        }
        val parser = factory.newSAXParser()

        var currentXmlChannelId: String? = null
        var currentChannelKey: String? = null
        var currentStart = 0L
        var currentStop = 0L
        var currentTitle: String? = null
        var currentDesc: String? = null
        var readingDisplayName = false
        var readingTitle = false
        var readingDesc = false
        var readingCategory = false
        var currentArtwork: String? = null
        var currentCategory: String? = null
        val textBuffer = StringBuilder(128)

        val handler = object : DefaultHandler() {
            private var parsedElements = 0

            override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
                if ((parsedElements++ and 0xFF) == 0) {
                    checkActive()
                    abortLargeEpgWorkIfInteractive(channels.size)
                }
                val name = (localName?.takeIf { it.isNotEmpty() } ?: qName ?: "").lowercase(Locale.US)
                when (name) {
                    "channel" -> {
                        currentXmlChannelId = normalizeChannelKey(attributes?.getValue("id").orEmpty())
                    }
                    "display-name" -> {
                        readingDisplayName = currentXmlChannelId
                            ?.let { xmlId -> keyLookup[xmlId].isNullOrEmpty() }
                            ?: false
                        textBuffer.setLength(0)
                    }
                    "programme" -> {
                        val start = parseXmlTvDate(attributes?.getValue("start"))
                        val stop = parseXmlTvDate(attributes?.getValue("stop"))
                        if ((stop > 0L && stop <= recentCutoff) || (start > 0L && start >= futureCutoff)) {
                            currentChannelKey = null
                            currentStart = 0L
                            currentStop = 0L
                        } else {
                            currentChannelKey = normalizeChannelKey(attributes?.getValue("channel").orEmpty())
                            currentStart = start
                            currentStop = stop
                        }
                        currentTitle = null
                        currentDesc = null
                        currentArtwork = null
                        currentCategory = null
                    }
                    "title" -> {
                        if (!currentChannelKey.isNullOrBlank()) {
                            readingTitle = true
                            textBuffer.setLength(0)
                        }
                    }
                    "desc" -> {
                        if (!currentChannelKey.isNullOrBlank()) {
                            readingDesc = true
                            textBuffer.setLength(0)
                        }
                    }
                    "icon" -> if (!currentChannelKey.isNullOrBlank()) {
                        currentArtwork = com.arflix.tv.data.model.safeSportsImage(attributes?.getValue("src"))
                    }
                    "category" -> if (!currentChannelKey.isNullOrBlank()) {
                        readingCategory = true
                        textBuffer.setLength(0)
                    }
                }
            }

            override fun characters(ch: CharArray?, start: Int, length: Int) {
                if (ch == null || length <= 0) return
                if (readingDisplayName || readingTitle || readingDesc || readingCategory) {
                    textBuffer.append(ch, start, length)
                }
            }

            override fun endElement(uri: String?, localName: String?, qName: String?) {
                val name = (localName?.takeIf { it.isNotEmpty() } ?: qName ?: "").lowercase(Locale.US)
                when (name) {
                    "display-name" -> {
                        if (readingDisplayName) {
                            val xmlId = currentXmlChannelId
                            if (!xmlId.isNullOrBlank()) {
                                val display = normalizeChannelKey(textBuffer.toString())
                                if (display.isNotBlank()) {
                                    val isUseful = guideKeyCandidates(display).any { it in keyLookup }
                                    if (isUseful) {
                                        xmlChannelNameMap.getOrPut(xmlId) { mutableSetOf() }.add(display)
                                    }
                                }
                            }
                            readingDisplayName = false
                            textBuffer.setLength(0)
                        }
                    }
                    "channel" -> {
                        currentXmlChannelId = null
                    }
                    "title" -> {
                        if (readingTitle) {
                            currentTitle = textBuffer.toString().trim().ifBlank { null }
                            readingTitle = false
                            textBuffer.setLength(0)
                        }
                    }
                    "desc" -> {
                        if (readingDesc) {
                            currentDesc = textBuffer.toString().trim().ifBlank { null }
                            readingDesc = false
                            textBuffer.setLength(0)
                        }
                    }
                    "category" -> if (readingCategory) {
                        currentCategory = listOfNotNull(currentCategory, textBuffer.toString().trim()).joinToString(" ").take(200)
                        readingCategory = false
                        textBuffer.setLength(0)
                    }
                    "programme" -> {
                        val key = currentChannelKey
                        val resolvedChannels = key?.let {
                            xmlChannelResolveCache[it] ?: resolveXmlTvChannels(it, xmlChannelNameMap, keyLookup)
                                .also { resolved -> xmlChannelResolveCache[it] = resolved }
                        }.orEmpty()
                        if (resolvedChannels.isNotEmpty() && currentStop > currentStart) {
                            val program = IptvProgram(
                                title = currentTitle ?: context.getString(R.string.program_unknown),
                                description = currentDesc,
                                startUtcMillis = currentStart,
                                endUtcMillis = currentStop,
                                artworkUrl = currentArtwork,
                                category = currentCategory,
                            )
                            resolvedChannels.forEach { channel ->
                                val nowProgram = pickNow(nowCandidates[channel.id], program, nowUtc)
                                nowCandidates[channel.id] = nowProgram
                            if (program.startUtcMillis > nowUtc) {
                                val future = upcomingCandidates.getOrPut(channel.id) { mutableListOf() }
                                addUpcomingCandidate(future, program, limit = epgUpcomingProgramLimit)
                            } else if (program.endUtcMillis <= nowUtc && program.endUtcMillis > recentCutoffByChannelId.getValue(channel.id)) {
                                // Recently ended program – keep for the past-window in the EPG guide
                                val recent = recentCandidates.getOrPut(channel.id) { mutableListOf() }
                                val limit = recentLimitByChannelId.getValue(channel.id)
                                addRecentCandidate(recent, program, limit)
                            }
                            }
                        }
                        currentChannelKey = null
                        currentStart = 0L
                        currentStop = 0L
                        currentTitle = null
                        currentDesc = null
                    }
                }
            }
        }

        parser.parse(input, handler)

        return buildParsedNowNextResult(channels, nowCandidates, upcomingCandidates, recentCandidates)
    }

    private fun buildParsedNowNextResult(
        channels: List<IptvChannel>,
        nowCandidates: Map<String, IptvProgram?>,
        upcomingCandidates: Map<String, List<IptvProgram>>,
        recentCandidates: Map<String, List<IptvProgram>>
    ): ConcurrentHashMap<String, IptvNowNext> {
        val result = ConcurrentHashMap<String, IptvNowNext>(channels.size)
        channels.forEach { channel ->
            val future = upcomingCandidates[channel.id].orEmpty()
            val recent = recentCandidates[channel.id].orEmpty().sortedBy { it.startUtcMillis }
            val nowNext = IptvNowNext(
                now = nowCandidates[channel.id],
                next = future.getOrNull(0),
                later = future.getOrNull(1),
                upcoming = future,
                recent = recent
            )
            if (hasProgramData(nowNext)) {
                result[channel.id] = nowNext
            }
        }
        return result
    }

    private fun pickNow(existing: IptvProgram?, candidate: IptvProgram, nowUtcMillis: Long): IptvProgram? {
        if (!candidate.isLive(nowUtcMillis)) return existing
        if (existing == null) return candidate
        return if (candidate.startUtcMillis >= existing.startUtcMillis) candidate else existing
    }

    private fun addUpcomingCandidate(
        upcoming: MutableList<IptvProgram>,
        candidate: IptvProgram,
        limit: Int
    ) {
        val duplicate = upcoming.any {
            it.startUtcMillis == candidate.startUtcMillis &&
                it.endUtcMillis == candidate.endUtcMillis &&
                it.title.equals(candidate.title, ignoreCase = true)
        }
        if (duplicate) return

        val insertIndex = upcoming.indexOfFirst {
            candidate.startUtcMillis < it.startUtcMillis ||
                (candidate.startUtcMillis == it.startUtcMillis && candidate.endUtcMillis > it.endUtcMillis)
        }
        if (insertIndex >= 0) {
            upcoming.add(insertIndex, candidate)
        } else {
            upcoming.add(candidate)
        }
        while (upcoming.size > limit) {
            upcoming.removeAt(upcoming.lastIndex)
        }
    }

    private fun addRecentCandidate(
        recent: MutableList<IptvProgram>,
        candidate: IptvProgram,
        limit: Int
    ) {
        val duplicate = recent.any {
            it.startUtcMillis == candidate.startUtcMillis &&
                it.endUtcMillis == candidate.endUtcMillis &&
                it.title.equals(candidate.title, ignoreCase = true)
        }
        if (duplicate) return

        val insertIndex = recent.indexOfFirst {
            candidate.startUtcMillis < it.startUtcMillis ||
                (candidate.startUtcMillis == it.startUtcMillis && candidate.endUtcMillis > it.endUtcMillis)
        }
        if (insertIndex >= 0) {
            recent.add(insertIndex, candidate)
        } else {
            recent.add(candidate)
        }
        while (recent.size > limit) {
            recent.removeAt(0)
        }
    }

    private fun recentProgramLimitForChannel(channel: IptvChannel?, forceCatchupHistory: Boolean = false): Int {
        return if (forceCatchupHistory || effectiveCatchupDays(channel) > 0) {
            catchupRecentProgramLimit
        } else {
            epgRecentProgramLimit
        }
    }

    private fun recentCutoffForChannel(
        channel: IptvChannel?,
        nowUtcMillis: Long,
        forceCatchupHistory: Boolean = false
    ): Long {
        val catchupDays = effectiveCatchupDays(channel, forceCatchupHistory)
        return if (catchupDays > 0) {
            nowUtcMillis - catchupDays * 24L * 60L * 60_000L
        } else {
            nowUtcMillis - 30L * 60_000L
        }
    }

    private fun oldestRecentCutoff(
        channels: Collection<IptvChannel>,
        nowUtcMillis: Long,
        forceCatchupHistory: Boolean = false
    ): Long {
        val maxCatchupDays = channels.maxOfOrNull { effectiveCatchupDays(it, forceCatchupHistory) } ?: 0
        return if (maxCatchupDays > 0) {
            nowUtcMillis - maxCatchupDays * 24L * 60L * 60_000L
        } else {
            nowUtcMillis - 30L * 60_000L
        }
    }

    private fun xmlTvRecentCutoff(channels: Collection<IptvChannel>, nowUtcMillis: Long): Long {
        return maxOf(oldestRecentCutoff(channels, nowUtcMillis), nowUtcMillis - xmlTvPastWindowMs)
    }

    private fun buildRecentCutoffByChannelId(
        channels: Collection<IptvChannel>,
        nowUtcMillis: Long
    ): Map<String, Long> {
        return channels.associate { channel ->
            channel.id to maxOf(recentCutoffForChannel(channel, nowUtcMillis), nowUtcMillis - xmlTvPastWindowMs)
        }
    }

    private fun buildRecentLimitByChannelId(channels: Collection<IptvChannel>): Map<String, Int> {
        return channels.associate { channel -> channel.id to recentProgramLimitForChannel(channel) }
    }

    private fun effectiveCatchupDays(channel: IptvChannel?, forceCatchupHistory: Boolean = false): Int {
        return IptvGuideHistory.days(channel, forceCatchupHistory)
    }

    private fun shouldLoadIndexedGuide(item: IptvNowNext?, channel: IptvChannel?, nowMs: Long): Boolean {
        if (item == null) return true
        return !hasEnoughFutureGuide(item, nowMs) || !hasEnoughCatchupHistory(item, channel, nowMs)
    }

    private fun hasEnoughFutureGuide(item: IptvNowNext, nowMs: Long): Boolean {
        val future = buildList {
            item.next?.let(::add)
            item.later?.let(::add)
            addAll(item.upcoming)
        }
            .asSequence()
            .filter { it.endUtcMillis > nowMs && it.startUtcMillis > nowMs }
            .distinctBy { programKey(it) }
            .sortedBy { it.startUtcMillis }
            .toList()
        if (future.size >= 6) return true
        val farthestEnd = future.maxOfOrNull { it.endUtcMillis } ?: item.now?.endUtcMillis ?: 0L
        return farthestEnd - nowMs >= indexedGuideFutureWarmMs
    }

    private fun hasEnoughCatchupHistory(item: IptvNowNext, channel: IptvChannel?, nowMs: Long): Boolean {
        val days = effectiveCatchupDays(channel)
        return IptvGuideHistory.hasCoverage(item, days * IptvGuideHistory.DAY_MS, nowMs)
    }

    private fun mergeCachedGuideSlice(existing: IptvNowNext?, fresh: IptvNowNext): IptvNowNext {
        return IptvGuideHistory.mergeSchedules(existing, fresh, epgUpcomingProgramLimit)
    }

    private fun programKey(program: IptvProgram): String {
        return "${program.startUtcMillis}|${program.endUtcMillis}|${program.title}"
    }

    private fun hasAnyProgramData(nowNext: Map<String, IptvNowNext>): Boolean {
        if (nowNext.isEmpty()) return false
        return nowNext.values.any { item -> hasProgramData(item) }
    }

    private fun hasProgramData(item: IptvNowNext?): Boolean {
        return item != null && (
            item.now != null ||
                item.next != null ||
                item.later != null ||
                item.upcoming.isNotEmpty() ||
                item.recent.isNotEmpty()
            )
    }

    private fun epgCoverageRatio(channels: List<IptvChannel>, nowNext: Map<String, IptvNowNext>): Float {
        if (channels.isEmpty() || nowNext.isEmpty()) return 0f
        val covered = channels.count { ch ->
            hasProgramData(nowNext[ch.id])
        }
        return covered.toFloat() / channels.size.toFloat()
    }

    private fun parseXmlTvDate(rawValue: String?): Long {
        if (rawValue.isNullOrBlank()) return 0L
        val value = rawValue.trim()

        parseFixedXmlTvDate(value)?.let { return it }

        return runCatching {
            OffsetDateTime.parse(value, XMLTV_OFFSET_FORMATTER).toInstant().toEpochMilli()
        }.recoverCatching {
            val local = LocalDateTime.parse(value.take(14), XMLTV_LOCAL_FORMATTER)
            local.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }.getOrDefault(0L)
    }

    /**
     * XMLTV timestamps are overwhelmingly `yyyyMMddHHmmss +HHmm`. Parsing that
     * fixed representation directly avoids two DateTimeFormatter parses (and an
     * exception on offset-less values) for every programme. A 100 MB guide can
     * contain hundreds of thousands of these values, so the generic fallback is
     * intentionally reserved for uncommon provider-specific formats.
     */
    private fun parseFixedXmlTvDate(value: String): Long? {
        if (value.length < 14) return null
        for (index in 0 until 14) {
            if (!value[index].isDigit()) return null
        }

        fun number(start: Int, length: Int): Int {
            var result = 0
            for (index in start until start + length) {
                result = result * 10 + (value[index] - '0')
            }
            return result
        }

        return runCatching {
            val local = LocalDateTime.of(
                number(0, 4),
                number(4, 2),
                number(6, 2),
                number(8, 2),
                number(10, 2),
                number(12, 2),
            )

            var offsetStart = 14
            while (offsetStart < value.length && value[offsetStart].isWhitespace()) {
                offsetStart++
            }
            if (offsetStart >= value.length) {
                return@runCatching local.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            }
            if (value[offsetStart] == 'Z' || value[offsetStart] == 'z') {
                return@runCatching local.toInstant(ZoneOffset.UTC).toEpochMilli()
            }

            val sign = when (value[offsetStart]) {
                '+' -> 1
                '-' -> -1
                else -> return null
            }
            val hourStart = offsetStart + 1
            if (hourStart + 1 >= value.length ||
                !value[hourStart].isDigit() ||
                !value[hourStart + 1].isDigit()
            ) {
                return null
            }
            val offsetHours = (value[hourStart] - '0') * 10 + (value[hourStart + 1] - '0')
            val minuteStart = if (value.getOrNull(hourStart + 2) == ':') hourStart + 3 else hourStart + 2
            val offsetMinutes = if (
                minuteStart + 1 < value.length &&
                value[minuteStart].isDigit() &&
                value[minuteStart + 1].isDigit()
            ) {
                (value[minuteStart] - '0') * 10 + (value[minuteStart + 1] - '0')
            } else {
                0
            }
            val offset = ZoneOffset.ofTotalSeconds(sign * (offsetHours * 60 + offsetMinutes) * 60)
            local.toInstant(offset).toEpochMilli()
        }.getOrNull()
    }

    private fun buildChannelId(streamUrl: String, epgId: String?): String {
        val normalizedEpg = normalizeChannelKey(epgId ?: "")
        val streamKey = stableStreamKey(streamUrl)
        return if (normalizedEpg.isNotBlank()) {
            "m3u:$normalizedEpg:$streamKey"
        } else {
            "m3u:$streamKey"
        }
    }

    private fun stableStreamKey(streamUrl: String): String {
        val normalized = streamUrl.trim()
        if (normalized.isEmpty()) return "empty"
        val digest = checkNotNull(sha1Digest.get())
            .digest(normalized.toByteArray(StandardCharsets.UTF_8))
        val digits = "0123456789abcdef"
        val shortHex = CharArray(16)
        for (index in 0 until 8) {
            val value = digest[index].toInt() and 0xff
            shortHex[index * 2] = digits[value ushr 4]
            shortHex[index * 2 + 1] = digits[value and 0x0f]
        }
        return "${normalized.length}-${String(shortHex)}"
    }

    private fun extractChannelName(metadata: String?): String {
        if (metadata.isNullOrBlank()) return "Unknown Channel"
        val idx = metadata.indexOf(',')
        return if (idx >= 0 && idx < metadata.lastIndex) {
            metadata.substring(idx + 1).trim().ifBlank { "Unknown Channel" }
        } else {
            "Unknown Channel"
        }
    }

    private fun extractAttr(metadata: String?, attr: String): String? {
        if (metadata.isNullOrBlank()) return null
        val source = metadata
        val key = "$attr="
        val startIndex = source.indexOf(key, ignoreCase = true)
        if (startIndex < 0) return null

        var valueStart = startIndex + key.length
        while (valueStart < source.length && source[valueStart].isWhitespace()) {
            valueStart++
        }
        if (valueStart >= source.length) return null

        val quote = source[valueStart]
        val raw = if (quote == '"' || quote == '\'') {
            var i = valueStart + 1
            while (i < source.length) {
                val ch = source[i]
                val escaped = i > valueStart + 1 && source[i - 1] == '\\'
                if (ch == quote && !escaped) break
                i++
            }
            source.substring(valueStart + 1, i.coerceAtMost(source.length))
        } else {
            var i = valueStart
            while (i < source.length) {
                val ch = source[i]
                if (ch.isWhitespace() || ch == ',') break
                i++
            }
            source.substring(valueStart, i.coerceAtMost(source.length))
        }

        // Handle malformed IPTV provider values such as tvg-name=\'VALUE\'.
        return normalizeM3uAttributeValue(raw)
    }

    private fun parseM3uAttributes(metadata: String?): Map<String, String> {
        if (metadata.isNullOrBlank()) return emptyMap()
        val attributes = HashMap<String, String>(16)
        var index = metadata.indexOf(':').let { if (it >= 0) it + 1 else 0 }
        val length = metadata.length

        while (index < length) {
            while (index < length && metadata[index].isWhitespace()) index++
            if (index >= length || metadata[index] == ',') break

            val keyStart = index
            while (index < length &&
                metadata[index] != '=' &&
                !metadata[index].isWhitespace() &&
                metadata[index] != ','
            ) {
                index++
            }
            val keyEnd = index
            while (index < length && metadata[index].isWhitespace()) index++
            if (index >= length || metadata[index] != '=') {
                continue
            }

            val key = metadata.substring(keyStart, keyEnd).lowercase(Locale.ROOT)
            index++
            while (index < length && metadata[index].isWhitespace()) index++
            if (index >= length) break

            val valueStart: Int
            val valueEnd: Int
            val escapedQuote = metadata[index] == '\\' &&
                index + 1 < length &&
                (metadata[index + 1] == '"' || metadata[index + 1] == '\'')
            val quote = if (escapedQuote) metadata[index + 1] else metadata[index]
            if (escapedQuote) {
                index += 2
                valueStart = index
                while (index + 1 < length && !(metadata[index] == '\\' && metadata[index + 1] == quote)) index++
                valueEnd = index
                if (index + 1 < length) index += 2
            } else if (quote == '"' || quote == '\'') {
                index++
                valueStart = index
                while (index < length) {
                    val escaped = index > valueStart && metadata[index - 1] == '\\'
                    if (metadata[index] == quote && !escaped) break
                    index++
                }
                valueEnd = index
                if (index < length) index++
            } else {
                valueStart = index
                while (index < length && !metadata[index].isWhitespace() && metadata[index] != ',') index++
                valueEnd = index
            }

            normalizeM3uAttributeValue(metadata.substring(valueStart, valueEnd))
                ?.let { value -> attributes[key] = value }
        }
        return attributes
    }

    private fun normalizeM3uAttributeValue(raw: String): String? {
        val normalized = raw
            .trim()
            .removePrefix("\\'")
            .removeSuffix("\\'")
            .removePrefix("\\\"")
            .removeSuffix("\\\"")
            .trim('"', '\'')
            .trim()
        return normalized.takeIf { it.isNotBlank() }
    }

    private fun firstM3uAttribute(attributes: Map<String, String>, vararg names: String): String? {
        names.forEach { name ->
            attributes[name]?.takeIf { it.isNotBlank() }?.let { return it }
        }
        return null
    }

    private fun extractFirstAttr(metadata: String?, vararg attrs: String): String? {
        attrs.forEach { attr ->
            extractAttr(metadata, attr)?.takeIf { it.isNotBlank() }?.let { return it }
        }
        return null
    }

    private fun mergeM3uHeaderOptions(
        target: MutableMap<String, String>,
        drmProps: MutableMap<String, String>,
        line: String
    ) {
        val value = line.substringAfter(':', missingDelimiterValue = "").trim()
        if (value.isBlank()) return

        when {
            // ── DRM properties (routed to drmProps, not HTTP headers) ────
            value.startsWith("inputstream.adaptive.license_type=", ignoreCase = true) ->
                drmProps["license_type"] = value.substringAfter('=').trim()
            value.startsWith("inputstream.adaptive.license_key=", ignoreCase = true) ->
                drmProps["license_key"] = value.substringAfter('=').trim()
            value.startsWith("inputstream.adaptive.license_data=", ignoreCase = true) ->
                drmProps["license_data"] = value.substringAfter('=').trim()

            // ── HTTP headers ────────────────────────────────────────────
            value.startsWith("http-user-agent=", ignoreCase = true) ->
                target.putSafeHeader("User-Agent", value.substringAfter('=').trim())
            value.startsWith("user-agent=", ignoreCase = true) ->
                target.putSafeHeader("User-Agent", value.substringAfter('=').trim())
            value.startsWith("http-referrer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("http-referer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("referer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("referrer=", ignoreCase = true) ->
                target.putSafeHeader("Referer", value.substringAfter('=').trim())
            value.startsWith("inputstream.adaptive.stream_headers=", ignoreCase = true) ->
                target.putAll(parseHeaderPairs(value.substringAfter('=')))
            value.startsWith("inputstream.adaptive.manifest_headers=", ignoreCase = true) ->
                target.putAll(parseHeaderPairs(value.substringAfter('=')))
        }
    }

    /**
     * Builds a [DrmInfo] from accumulated `#KODIPROP` DRM properties, or returns
     * `null` if no `license_type` was declared.
     */
    private fun buildDrmInfo(props: Map<String, String>): DrmInfo? {
        val rawScheme = props["license_type"] ?: return null
        return DrmInfo(
            scheme = com.arflix.tv.util.ClearKeyUtil.normalizeScheme(rawScheme),
            licenseUrl = props["license_key"],
            licenseData = props["license_data"]
        )
    }

    private fun extractInlineRequestHeaders(metadata: String?): Map<String, String> {
        if (metadata.isNullOrBlank()) return emptyMap()
        val userAgent = extractFirstAttr(metadata, "http-user-agent", "user-agent")
        val referrer = extractFirstAttr(metadata, "http-referrer", "http-referer", "referrer", "referer")
        return buildInlineRequestHeaders(userAgent, referrer)
    }

    private fun extractInlineRequestHeaders(attributes: Map<String, String>): Map<String, String> {
        val userAgent = firstM3uAttribute(attributes, "http-user-agent", "user-agent")
        val referrer = firstM3uAttribute(attributes, "http-referrer", "http-referer", "referrer", "referer")
        return buildInlineRequestHeaders(userAgent, referrer)
    }

    private fun buildInlineRequestHeaders(userAgent: String?, referrer: String?): Map<String, String> {
        if (userAgent == null && referrer == null) return emptyMap()
        val headers = LinkedHashMap<String, String>(2)
        userAgent?.let { headers.putSafeHeader("User-Agent", it) }
        referrer?.let { headers.putSafeHeader("Referer", it) }
        return headers
    }

    private fun parseHeaderPairs(raw: String): Map<String, String> {
        if (raw.isBlank()) return emptyMap()
        return raw
            .split('&', '|')
            .mapNotNull { part ->
                val decoded = runCatching { java.net.URLDecoder.decode(part.trim(), "UTF-8") }
                    .getOrDefault(part.trim())
                val separator = when {
                    decoded.contains("=") -> "="
                    decoded.contains(":") -> ":"
                    else -> return@mapNotNull null
                }
                val name = decoded.substringBefore(separator).trim()
                val value = decoded.substringAfter(separator).trim()
                val headerName = canonicalHeaderName(name)
                if (isSafeHttpHeader(headerName, value)) headerName to value else null
            }
            .toMap()
    }

    private fun MutableMap<String, String>.putSafeHeader(name: String, value: String) {
        if (isSafeHttpHeader(name, value)) {
            put(name, value)
        }
    }

    private fun isSafeHttpHeader(name: String, value: String): Boolean {
        return name.isNotBlank() &&
            value.isNotBlank() &&
            name.all { ch ->
                ch.code in 33..126 &&
                    ch !in setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '"', '/', '[', ']', '?', '=', '{', '}')
            } &&
            value.all { ch -> ch == '\t' || ch.code in 32..126 }
    }

    private fun canonicalHeaderName(name: String): String {
        return when (name.trim().lowercase(Locale.US)) {
            "user-agent", "http-user-agent", "useragent" -> "User-Agent"
            "referer", "referrer", "http-referer", "http-referrer" -> "Referer"
            else -> name.trim()
        }
    }

    private fun inferQualityLabel(name: String, group: String): String? {
        val source = "$name $group".uppercase(Locale.US)
        return when {
            "4K" in source || "UHD" in source || "2160" in source -> "4K"
            "FHD" in source || "1080" in source -> "FHD"
            "HD" in source || "720" in source -> "HD"
            "SD" in source || "576" in source || "480" in source -> "SD"
            else -> null
        }
    }

    private fun buildChannelVariantKey(name: String, group: String, epgId: String?): String {
        val base = epgId?.takeIf { it.isNotBlank() } ?: name
        val normalizedBase = normalizeLooseKey(
            base
                .replace(QUALITY_WORDS_REGEX, " ")
                .replace(BRACKET_PAREN_REGEX, " ")
        )
        val normalizedGroup = normalizeLooseKey(group)
        return listOf(normalizedGroup, normalizedBase).filter { it.isNotBlank() }.joinToString(":")
    }

    private fun normalizeChannelKey(value: String): String = value.trim().lowercase(Locale.US)

    private fun normalizeLooseKey(value: String): String {
        return normalizeChannelKey(value).replace(NON_ALPHA_NUM_REGEX_INLINE, "")
    }

    private fun buildChannelKeyLookup(channels: List<IptvChannel>): Map<String, List<IptvChannel>> {
        if (channels.size > LargeIptvListChannelCount) {
            return buildLargeChannelKeyLookup(channels)
        }
        val map = LinkedHashMap<String, MutableList<IptvChannel>>(channels.size * 8)
        channels.forEach { channel ->
            val candidates = mutableSetOf<String>()
            candidates += guideKeyCandidates(channel.name)

            channel.epgId?.takeIf { it.isNotBlank() }?.let { epgId ->
                candidates += guideKeyCandidates(epgId)
            }

            channel.tvgName?.takeIf { it.isNotBlank() }?.let { tvgName ->
                candidates += guideKeyCandidates(tvgName)
            }
            extractAttr(channel.rawTitle, "tvg-name")?.takeIf { it.isNotBlank() }?.let { tvgName ->
                candidates += guideKeyCandidates(tvgName)
            }

            candidates.filter { it.isNotBlank() }.forEach { key ->
                val bucket = map.getOrPut(key) { mutableListOf() }
                if (bucket.none { it.id == channel.id }) {
                    bucket += channel
                }
            }
        }
        return map
    }

    /**
     * Large provider lists almost always expose stable tvg-id values. Building
     * every fuzzy alias for 50k+ rows creates millions of temporary strings and
     * continuously churns the bounded alias cache. Keep exact provider keys and
     * a compact loose fallback; fuzzy matching remains available for smaller or
     * poorly identified playlists.
     */
    private fun buildLargeChannelKeyLookup(channels: List<IptvChannel>): Map<String, List<IptvChannel>> {
        val map = LinkedHashMap<String, MutableList<IptvChannel>>(channels.size * 3)

        channels.forEach { channel ->
            fun addNormalized(key: String) {
                if (key.isBlank()) return
                val bucket = map.getOrPut(key) { mutableListOf() }
                if (bucket.lastOrNull()?.id != channel.id) {
                    bucket += channel
                }
            }

            val epgId = channel.epgId?.trim().orEmpty()
            val tvgName = channel.tvgName?.trim().orEmpty()
            val name = channel.name.trim()

            listOf(epgId, tvgName, name)
                .asSequence()
                .filter { it.isNotBlank() }
                .forEach { raw ->
                    addNormalized(normalizeChannelKey(raw))
                    addNormalized(normalizeLooseKey(raw))
                }

            // Numeric API EPG IDs often differ from the XMLTV IDs. Keep the
            // country prefix, but let HD/FHD/SD/LQ variants share its schedule.
            addNormalized(normalizeLooseKey(stripQualitySuffixes(name)))
            if (tvgName.isNotBlank()) addNormalized(normalizeLooseKey(stripQualitySuffixes(tvgName)))
        }
        return map
    }

    private fun resolveXmlTvChannels(
        xmlChannelKey: String,
        xmlChannelNameMap: Map<String, Set<String>>,
        keyLookup: Map<String, List<IptvChannel>>
    ): List<IptvChannel> {
        val normalized = normalizeChannelKey(xmlChannelKey)

        val exact = keyLookup[normalized].orEmpty()
        val names = xmlChannelNameMap[normalized].orEmpty()
        val named = names.flatMap { display ->
            keyLookup[normalizeLooseKey(stripQualitySuffixes(display))].orEmpty()
        }
        if (exact.isNotEmpty() || named.isNotEmpty()) return (exact + named).distinctBy { it.id }

        guideKeyCandidates(xmlChannelKey).forEach { key ->
            keyLookup[key]?.let { return it }
        }

        names.forEach { display ->
            guideKeyCandidates(display).forEach { key ->
                keyLookup[key]?.let { return it }
            }
        }
        return emptyList()
    }

    private fun scopedProviderGuideLookupChannels(
        requestedChannels: List<IptvChannel>,
        allProviderChannels: List<IptvChannel>
    ): List<IptvChannel> {
        if (requestedChannels.isEmpty()) return emptyList()
        if (requestedChannels.size > 256 || allProviderChannels.size <= requestedChannels.size) {
            return allProviderChannels
        }

        val requestedGuideKeys = requestedChannels
            .asSequence()
            .flatMap { channel -> channel.guideLookupKeys().asSequence() }
            .toSet()
        if (requestedGuideKeys.isEmpty()) return requestedChannels

        val scoped = LinkedHashMap<String, IptvChannel>(requestedChannels.size * 4)
        requestedChannels.forEach { channel -> scoped[channel.id] = channel }

        allProviderChannels.forEach { channel ->
            if (channel.id in scoped) return@forEach
            val matchesRequestedGuide = channel.guideLookupKeys().any { it in requestedGuideKeys }
            if (matchesRequestedGuide) {
                scoped[channel.id] = channel
            }
        }

        return scoped.values.toList()
    }

    private fun IptvChannel.guideLookupKeys(): Set<String> {
        val out = LinkedHashSet<String>()
        guideKeyCandidates(epgId).forEach(out::add)
        guideKeyCandidates(tvgName).forEach(out::add)
        guideKeyCandidates(variantKey).forEach(out::add)
        return out
    }

    private fun guideKeyCandidates(value: String?): Set<String> {
        val raw = value?.trim().orEmpty()
        if (raw.isBlank()) return emptySet()
        val cached = guideKeyCandidatesCache[raw]
        if (cached != null) return cached

        val withoutBrackets = raw
            .replace(BRACKET_CONTENT_REGEX, " ")
            .replace(PAREN_CONTENT_REGEX, " ")
            .replace(MULTI_SPACE_REGEX, " ")
            .trim()
        val withoutPrefix = stripGuidePrefix(withoutBrackets)
        val afterPipe = withoutBrackets.substringAfterLast('|').trim()
        val beforeDomain = withoutBrackets
            .takeIf { '.' in it && !it.any(Char::isWhitespace) }
            ?.substringBefore('.')
            ?.trim()

        val rawAliases = buildList {
            add(raw)
            add(withoutBrackets)
            add(stripQualitySuffixes(withoutBrackets))
            add(withoutPrefix)
            add(stripQualitySuffixes(withoutPrefix))
            add(afterPipe)
            add(stripQualitySuffixes(afterPipe))
            beforeDomain?.let {
                add(it)
                add(stripQualitySuffixes(it))
            }
        }

        val result = rawAliases
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .flatMap { alias ->
                sequenceOf(
                    normalizeChannelKey(alias),
                    normalizeLooseKey(alias),
                    normalizeLooseKey(stripQualitySuffixes(alias))
                )
            }
            .filter { it.isNotBlank() }
            .toSet()

        guideKeyCandidatesCache[raw] = result
        return result
    }

    private fun stripGuidePrefix(value: String): String {
        return value.replace(GUIDE_PREFIX_REGEX, "").trim()
    }

    private fun stripQualitySuffixes(value: String): String {
        return value
            .lowercase(Locale.US)
            .replace(QUALITY_SUFFIX_REGEX, "")
            .replace(MULTI_SPACE_REGEX, " ")
            .trim()
    }

    private fun prepareInputStream(source: InputStream, url: String): InputStream {
        val buffered = BufferedInputStream(source)
        buffered.mark(4)
        val b1 = buffered.read()
        val b2 = buffered.read()
        buffered.reset()
        val isGzipMagic = b1 == 0x1f && b2 == 0x8b
        return if (isGzipMagic || url.lowercase(Locale.US).endsWith(".gz")) {
            GZIPInputStream(buffered)
        } else {
            buffered
        }
    }

    private fun looksLikeM3u(source: InputStream): Boolean {
        source.mark(1024)
        val bytes = ByteArray(1024)
        val read = source.read(bytes)
        source.reset()
        if (read <= 0) return false
        val text = String(bytes, 0, read, StandardCharsets.UTF_8).trimStart()
        return text.startsWith("#EXTM3U", ignoreCase = true)
    }

    private fun looksLikeXmlTv(source: InputStream): Boolean {
        source.mark(2048)
        val bytes = ByteArray(2048)
        val read = source.read(bytes)
        source.reset()
        if (read <= 0) return false
        val text = String(bytes, 0, read, StandardCharsets.UTF_8).trimStart()
        return text.startsWith("<?xml", ignoreCase = true) || text.startsWith("<tv", ignoreCase = true)
    }

    private fun cacheFile(): File {
        val dir = File(context.filesDir, "iptv_cache")
        if (!dir.exists()) dir.mkdirs()
        return File(dir, "${profileManager.getProfileIdSync()}_iptv_cache.json")
    }

    private fun channelCacheFile(): File {
        val dir = File(context.filesDir, "iptv_cache")
        if (!dir.exists()) dir.mkdirs()
        return File(dir, "${profileManager.getProfileIdSync()}_iptv_channels_cache.json")
    }

    private fun cleanupStaleEpgTempFiles(maxAgeMs: Long = 3 * 60_000L) {
        runCatching {
            val now = System.currentTimeMillis()
            context.cacheDir.listFiles { _, name -> name.startsWith("epg_") && name.endsWith(".xml") }?.forEach { file ->
                val age = now - file.lastModified()
                if (age > maxAgeMs) {
                    runCatching { file.delete() }
                }
            }
        }
    }

    private fun cleanupIptvCacheDirectory() {
        runCatching {
            val dir = File(context.filesDir, "iptv_cache")
            if (!dir.exists()) return
            dir.listFiles { _, name -> name.endsWith("_iptv_cache.json") }?.forEach { file ->
                if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                    runCatching { file.delete() }
                }
            }
        }
    }

    private fun pruneOversizedIptvCacheFile() {
        runCatching {
            val file = cacheFile()
            if (!file.exists()) return
            if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                file.delete()
            }
        }
    }

    private fun stalkerPortalSignature(config: IptvConfig): String =
        config.stalkerPortals.joinToString(separator = "||") { portal ->
            listOf(
                portal.id.trim(),
                portal.name.trim(),
                portal.portalUrl.trim(),
                portal.macAddress.trim(),
                portal.enabled.toString()
            ).joinToString("|")
        }

    private fun buildConfigSignature(config: IptvConfig): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val playlistSignature = config.playlists.joinToString(separator = "||") { playlist ->
            listOf(
                playlist.id.trim(),
                playlist.name.trim(),
                playlist.m3uUrl.trim(),
                playlist.epgUrl.trim(),
                playlist.epgUrls.orEmpty().joinToString(",") { it.trim() },
                playlist.enabled.toString(),
                (playlist.importLiveTv ?: true).toString(),
                (playlist.importVod ?: true).toString(),
                (playlist.importSeries ?: true).toString()
            ).joinToString("|")
        }
        val raw = listOf(
            "playlist-group-prefix-v6-stalker-portal-list-xtream-category-order-catchup-history-48h",
            config.m3uUrl.trim(),
            config.epgUrl.trim(),
            stalkerPortalSignature(config),
            playlistSignature
        ).joinToString("||")
        return digest.digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun buildSourceSignature(config: IptvConfig): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val playlistSignature = config.playlists.joinToString(separator = "||") { playlist ->
            listOf(
                playlist.id.trim(),
                playlist.name.trim(),
                playlist.m3uUrl.trim(),
                playlist.enabled.toString(),
                (playlist.importLiveTv ?: true).toString(),
                (playlist.importVod ?: true).toString(),
                (playlist.importSeries ?: true).toString()
            ).joinToString("|")
        }
        val raw = listOf(
            "playlist-sources-v5-stalker-portal-list-xtream-category-order-catchup-history-48h",
            config.m3uUrl.trim(),
            stalkerPortalSignature(config),
            playlistSignature
        ).joinToString("||")
        return digest.digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun epgIndexKey(profileId: String, config: IptvConfig): String =
        "${profileId.trim().ifBlank { "default" }}|${buildSourceSignature(config)}"

    private fun currentEpgIndexKey(config: IptvConfig): String {
        val existing = currentEpgIndexKey
        if (existing.isNotBlank()) return existing
        return epgIndexKey(profileManager.getProfileIdSync(), config)
    }

    private fun buildLegacyConfigSignature(config: IptvConfig): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val raw = "${config.m3uUrl.trim()}|${config.epgUrl.trim()}"
        return digest.digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }

    private fun writeCache(
        config: IptvConfig,
        channels: List<IptvChannel>,
        nowNext: Map<String, IptvNowNext>,
        loadedAtMs: Long,
        persistChannels: Boolean = true
    ) {
        runCatching {
            // Channels are persisted to the SQLite channel store (streamed, bounded
            // memory) instead of being serialized to a giant gzipped-JSON blob. This
            // removes the ~50k-channel Gson serialization (plus the 50k channel.copy()
            // allocations) that spiked the 384MB-capped heap into a blocking-GC spiral.
            val key = currentEpgIndexKey(config)
            if (persistChannels) runCatching {
                val existingCount = channelStore.count(key)
                if (existingCount > LargeIptvListChannelCount && channels.size < existingCount) {
                    System.err.println("[IPTV-Paged] Keeping full channel store ($existingCount); skip partial write ${channels.size}")
                } else {
                    val storeStartedAt = System.currentTimeMillis()
                    channelStore.replaceAll(key, channels, loadedAtMs)
                    invalidatePagedChannelStoreCount()
                    System.err.println(
                        "[IPTV-Timing] channel index replace rows=${channels.size} " +
                            "in ${System.currentTimeMillis() - storeStartedAt}ms"
                    )
                }
            }

            val indexedChannelCount = runCatching { channelStore.count(key) }.getOrDefault(0)
            if (maxOf(indexedChannelCount, channels.size) > LargeIptvListChannelCount) {
                // The SQLite guide is authoritative for large playlists. Building
                // and gzipping a second 50k-entry JSON guide duplicates tens of MB
                // and triggers multi-second blocking GCs on TV hardware.
                val metadataOnly = IptvCachePayload(
                    channels = emptyList(),
                    nowNext = emptyMap(),
                    loadedAtEpochMs = loadedAtMs,
                    configSignature = buildConfigSignature(config),
                    sourceSignature = buildSourceSignature(config),
                    discoveredEpgUrls = discoveredM3uEpgUrls
                        .asSequence()
                        .map { it.trim() }
                        .filter { it.isNotBlank() }
                        .distinct()
                        .toList()
                )
                cacheFile().writeBytes(gzipBytes(gson.toJson(metadataOnly)))
                return@runCatching
            }

            val channelsById = channels.associateBy { it.id }
            val compactNowNext = nowNext
                .asSequence()
                .filter { (_, value) -> hasProgramData(value) }
                .associate { (channelId, value) ->
                    val isCatchupChannel = effectiveCatchupDays(channelsById[channelId]) > 0
                    val recentLimit = if (isCatchupChannel) {
                        cacheCatchupRecentProgramLimit
                    } else {
                        cacheRecentProgramLimit
                    }
                    channelId to IptvNowNext(
                        now = value.now?.compactForCache(),
                        next = value.next?.compactForCache(),
                        later = value.later?.compactForCache(),
                        upcoming = value.upcoming
                            .asSequence()
                            .map { it.compactForCache() }
                            .take(cacheUpcomingProgramLimit)
                            .toList(),
                        recent = value.recent
                            .takeLast(recentLimit)
                            .map { it.compactForCache() }
                    )
                }
            val payload = IptvCachePayload(
                // Channels live in the SQLite store now; the guide cache keeps only the
                // now/next slice. (readCache is only consumed for its nowNext.)
                channels = emptyList(),
                nowNext = compactNowNext,
                loadedAtEpochMs = loadedAtMs,
                configSignature = buildConfigSignature(config),
                sourceSignature = buildSourceSignature(config),
                discoveredEpgUrls = discoveredM3uEpgUrls
                    .asSequence()
                    .map { it.trim() }
                    .filter { it.isNotBlank() }
                    .distinct()
                    .toList()
            )
            val compressed = gzipBytes(gson.toJson(payload))
            if (compressed.size <= MAX_IPTV_CACHE_BYTES) {
                cacheFile().writeBytes(compressed)
            } else {
                val reducedPayload = payload.copy(
                    nowNext = compactNowNext.mapValues { (channelId, value) ->
                        val keepCatchupRecent = if (effectiveCatchupDays(channelsById[channelId]) > 0) {
                            value.recent.takeLast(cacheCatchupRecentProgramLimit / 2)
                        } else {
                            emptyList()
                        }
                        value.copy(
                            later = null,
                            upcoming = value.upcoming.take(8),
                            recent = keepCatchupRecent
                        )
                    }
                )
                val reduced = gzipBytes(gson.toJson(reducedPayload))
                if (reduced.size <= MAX_IPTV_CACHE_BYTES) {
                    cacheFile().writeBytes(reduced)
                } else {
                    // Final fallback: keep a fast warm-start playlist even if the guide slice is too large.
                    cacheFile().writeBytes(gzipBytes(gson.toJson(payload.copy(nowNext = emptyMap()))))
                }
            }
        }
    }

    private fun readCache(config: IptvConfig): IptvCachePayload? {
        return runCatching {
            val file = cacheFile()
            if (!file.exists()) return null
            if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                runCatching { file.delete() }
                return null
            }
            val text = decodeCacheText(file.readBytes())
            if (text.isBlank()) return null
            val payload = gson.fromJson(text, IptvCachePayload::class.java) ?: return null
            if (!isValidCacheSignature(config, payload.configSignature, payload.sourceSignature)) return null
            // Channels now come from the SQLite store, so the guide cache is channel-less;
            // it's only useful for its nowNext slice.
            if (payload.channels.isEmpty() && payload.nowNext.isEmpty()) return null
            rememberDiscoveredEpgUrls(payload.discoveredEpgUrls.orEmpty())
            payload
        }.getOrNull()
    }

    private fun readChannelCache(config: IptvConfig): IptvChannelCachePayload? {
        readDedicatedChannelCache(config)?.let { return it }
        return readChannelsFromLegacyCache(config)
    }

    private fun readDedicatedChannelCache(config: IptvConfig): IptvChannelCachePayload? {
        // Prefer the SQLite channel store: streamed cursor read, no multi-MB JSON parse.
        // The store is keyed by the config-derived index key, so its contents already
        // correspond to the current playlist/profile.
        runCatching {
            val key = currentEpgIndexKey(config)
            val count = if (key.isNotBlank()) channelStore.count(key) else 0
            if (count in PARTIAL_PAGED_CACHE_REPAIR_COUNTS && hasAnyConfiguredSource(config)) {
                System.err.println("[IPTV-Paged] Repairing partial channel store count=$count; forcing playlist cache reload")
                runCatching { channelStore.deleteSource(key) }
                invalidatePagedChannelStoreCount()
                return@runCatching
            }
            if (count > 0) {
                val channels = channelStore.loadStartupChannels(key, LargeIptvListChannelCount, LargeListMemoryChannelLimit)
                if (channels.isNotEmpty()) {
                    if (count > LargeIptvListChannelCount) {
                        System.err.println("[IPTV-Paged] Large channel cache first paint window=${channels.size}/$count")
                    }
                    return IptvChannelCachePayload(
                        channels = channels,
                        loadedAtEpochMs = channelStore.updatedAtMs(key),
                        configSignature = buildConfigSignature(config),
                        sourceSignature = buildSourceSignature(config),
                        discoveredEpgUrls = discoveredM3uEpgUrls
                            .asSequence().map { it.trim() }.filter { it.isNotBlank() }.distinct().toList()
                    )
                }
            }
        }
        // Legacy Gson channel cache fallback (pre-store builds).
        return runCatching {
            val file = channelCacheFile()
            if (!file.exists()) return null
            if (file.length() > MAX_IPTV_CACHE_BYTES) {
                runCatching { file.delete() }
                return null
            }
            val text = decodeCacheText(file.readBytes())
            if (text.isBlank()) return null
            val payload = gson.fromJson(text, IptvChannelCachePayload::class.java) ?: return null
            payload.copy(channels = sanitizeCachedChannels(payload.channels))
                .takeIf { isValidCacheSignature(config, it.configSignature, it.sourceSignature) && it.channels.isNotEmpty() }
                ?.also { rememberDiscoveredEpgUrls(it.discoveredEpgUrls) }
        }.getOrNull()
    }

    private fun readChannelsFromLegacyCache(config: IptvConfig): IptvChannelCachePayload? {
        return runCatching {
            val file = cacheFile()
            if (!file.exists()) return null
            if (file.length() > MAX_IPTV_CACHE_BYTES * 2) {
                runCatching { file.delete() }
                return null
            }

            var channels: List<IptvChannel> = emptyList()
            var loadedAt = 0L
            var configSignature = ""
            var sourceSignature = ""
            var discoveredUrls: List<String> = emptyList()

            cacheJsonReader(file).use { reader ->
                reader.beginObject()
                while (reader.hasNext()) {
                    when (reader.nextName()) {
                        "channels" -> {
                            val type = TypeToken.getParameterized(List::class.java, IptvChannel::class.java).type
                            channels = gson.fromJson(reader, type) ?: emptyList()
                        }
                        "loadedAtEpochMs" -> loadedAt = runCatching { reader.nextLong() }.getOrDefault(0L)
                        "configSignature" -> configSignature = reader.nextString().orEmpty()
                        "sourceSignature" -> sourceSignature = reader.nextString().orEmpty()
                        "discoveredEpgUrls" -> {
                            val type = TypeToken.getParameterized(List::class.java, String::class.java).type
                            discoveredUrls = gson.fromJson(reader, type) ?: emptyList()
                        }
                        else -> reader.skipValue()
                    }
                }
                reader.endObject()
            }

            IptvChannelCachePayload(
                channels = sanitizeCachedChannels(channels),
                loadedAtEpochMs = loadedAt,
                configSignature = configSignature,
                sourceSignature = sourceSignature,
                discoveredEpgUrls = discoveredUrls
            ).takeIf { isValidCacheSignature(config, it.configSignature, it.sourceSignature) && it.channels.isNotEmpty() }
                ?.also {
                    rememberDiscoveredEpgUrls(it.discoveredEpgUrls)
                    channelCacheFile().writeBytes(gzipBytes(gson.toJson(it)))
                }
        }.getOrNull()
    }

    private fun sanitizeCachedChannels(channels: List<IptvChannel>): List<IptvChannel> {
        if (channels.isEmpty()) return channels
        var changed = false
        val sanitized = channels.map { channel ->
            val streamUrl = normalizeIptvStreamUrl(channel.streamUrl)
            val logo = normalizeIptvLogoUrlOrNull(channel.logo)
            if (streamUrl == channel.streamUrl && logo == channel.logo) {
                channel
            } else {
                changed = true
                channel.copy(streamUrl = streamUrl, logo = logo)
            }
        }
        return if (changed) sanitized else channels
    }

    private fun cacheJsonReader(file: File): JsonReader {
        val input = FileInputStream(file)
        val stream = BufferedInputStream(input, 64 * 1024)
        stream.mark(2)
        val b1 = stream.read()
        val b2 = stream.read()
        stream.reset()
        val source = if (b1 == 0x1f && b2 == 0x8b) GZIPInputStream(stream) else stream
        return JsonReader(InputStreamReader(source, StandardCharsets.UTF_8))
    }

    private fun isValidCacheSignature(config: IptvConfig, cacheSignature: String, cacheSourceSignature: String): Boolean {
        val currentSignature = buildConfigSignature(config)
        val legacySignature = buildLegacyConfigSignature(config)
        val currentSourceSignature = buildSourceSignature(config)
        val signature = cacheSignature.trim()
        val sourceSignature = cacheSourceSignature.trim()
        if (sourceSignature.isNotBlank()) {
            return sourceSignature == currentSourceSignature &&
                (signature.isBlank() || signature == currentSignature || signature == legacySignature)
        }
        return signature.isBlank() ||
            signature == currentSignature ||
            signature == legacySignature
    }

    private fun rememberDiscoveredEpgUrls(urls: List<String>) {
        urls.asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .forEach { discoveredM3uEpgUrls.add(it) }
    }

    private fun IptvProgram.compactForCache(): IptvProgram =
        IptvProgram(
            title = title,
            description = null,
            startUtcMillis = startUtcMillis,
            endUtcMillis = endUtcMillis
        )

    private fun gzipBytes(text: String): ByteArray {
        val output = ByteArrayOutputStream()
        GZIPOutputStream(output).bufferedWriter(StandardCharsets.UTF_8).use { writer ->
            writer.write(text)
        }
        return output.toByteArray()
    }

    private fun decodeCacheText(bytes: ByteArray): String {
        if (bytes.isEmpty()) return ""
        val isGzip = bytes.size >= 2 &&
            bytes[0] == 0x1f.toByte() &&
            bytes[1] == 0x8b.toByte()
        return if (isGzip) {
            GZIPInputStream(ByteArrayInputStream(bytes))
                .bufferedReader(StandardCharsets.UTF_8)
                .use { it.readText() }
        } else {
            bytes.toString(StandardCharsets.UTF_8)
        }
    }

    private fun encryptConfigValue(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isBlank()) return ""
        if (trimmed.startsWith(ENC_PREFIX)) return trimmed
        return runCatching { ENC_PREFIX + encryptAesGcm(trimmed) }.getOrDefault(trimmed)
    }

    private fun decryptConfigValue(stored: String): String {
        val trimmed = stored.trim()
        if (trimmed.isBlank()) return ""
        if (!trimmed.startsWith(ENC_PREFIX)) return trimmed
        val payload = trimmed.removePrefix(ENC_PREFIX)
        return runCatching { decryptAesGcm(payload) }.getOrElse { "" }
    }

    private fun encryptAesGcm(plainText: String): String {
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, getOrCreateSecretKey())
        val iv = cipher.iv
        val encrypted = cipher.doFinal(plainText.toByteArray(StandardCharsets.UTF_8))
        val ivPart = Base64.encodeToString(iv, Base64.NO_WRAP)
        val dataPart = Base64.encodeToString(encrypted, Base64.NO_WRAP)
        return "$ivPart:$dataPart"
    }

    private fun decryptAesGcm(payload: String): String {
        val split = payload.split(":", limit = 2)
        require(split.size == 2) { "Invalid encrypted payload" }
        val iv = Base64.decode(split[0], Base64.NO_WRAP)
        val encrypted = Base64.decode(split[1], Base64.NO_WRAP)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, getOrCreateSecretKey(), GCMParameterSpec(128, iv))
        val plain = cipher.doFinal(encrypted)
        return String(plain, StandardCharsets.UTF_8)
    }

    private fun getOrCreateSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
        (keyStore.getKey(CONFIG_KEY_ALIAS, null) as? SecretKey)?.let { return it }

        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE)
        val spec = KeyGenParameterSpec.Builder(
            CONFIG_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()
        keyGenerator.init(spec)
        return keyGenerator.generateKey()
    }

    private class ProgressInputStream(
        source: InputStream,
        private val onBytesRead: (Long) -> Unit
    ) : FilterInputStream(source) {
        private var totalRead: Long = 0L
        private var lastEmit: Long = 0L
        private val emitStepBytes = 8L * 1024L * 1024L

        override fun read(): Int {
            val value = super.read()
            if (value >= 0) trackRead(1)
            return value
        }

        override fun read(b: ByteArray, off: Int, len: Int): Int {
            val read = super.read(b, off, len)
            if (read > 0) trackRead(read.toLong())
            return read
        }

        private fun trackRead(bytes: Long) {
            totalRead += bytes
            if (totalRead - lastEmit >= emitStepBytes) {
                lastEmit = totalRead
                onBytesRead(totalRead)
            }
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    // IPTV source-cache + language-scoped catalog index
    // (kept as a single contiguous region for easy rebase.)
    // ════════════════════════════════════════════════════════════════════════

    // ── Public types: VOD/series categories ─────────────────────────────────

    data class XtreamVodCategoryInfo(
        val categoryId: String,
        val categoryName: String
    )

    data class XtreamSeriesCategoryInfo(
        val categoryId: String,
        val categoryName: String
    )

    // ── Private wire models for categories ──────────────────────────────────

    private data class XtreamVodCategoryWire(
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("category_name") val categoryName: String? = null
    )

    private data class XtreamSeriesCategoryWire(
        @SerializedName("category_id") val categoryId: String? = null,
        @SerializedName("category_name") val categoryName: String? = null
    )

    @Volatile
    private var cachedXtreamVodCategories: List<XtreamVodCategoryWire> = emptyList()
    @Volatile
    private var cachedXtreamSeriesCategories: List<XtreamSeriesCategoryWire> = emptyList()
    @Volatile
    private var xtreamVodCategoriesLoadedAtMs: Long = 0L
    @Volatile
    private var xtreamSeriesCategoriesLoadedAtMs: Long = 0L

    private val vodCategoriesDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamVodCategoryWire::class.java).type
    }
    private val seriesCategoriesDiskCacheType: Type by lazy {
        TypeToken.getParameterized(XtreamDiskCache::class.java, XtreamSeriesCategoryWire::class.java).type
    }

    private fun vodCategoriesDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "categories_vod_${xtreamDiskCacheHash(creds)}.json")

    private fun seriesCategoriesDiskCacheFile(creds: XtreamCredentials): File =
        File(xtreamDiskCacheDir(), "categories_series_${xtreamDiskCacheHash(creds)}.json")

    private suspend fun loadXtreamVodCategoriesInternal(
        creds: XtreamCredentials,
        allowNetwork: Boolean
    ): List<XtreamVodCategoryWire> = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        if (cachedXtreamVodCategories.isNotEmpty() && now - xtreamVodCategoriesLoadedAtMs < xtreamVodCacheMs) {
            return@withContext cachedXtreamVodCategories
        }
        val diskFile = vodCategoriesDiskCacheFile(creds)
        val diskCache: XtreamDiskCache<XtreamVodCategoryWire>? =
            readDiskCache(diskFile, vodCategoriesDiskCacheType)
        if (diskCache != null && diskCache.items.isNotEmpty() &&
            now - diskCache.savedAtMs < xtreamVodCacheMs
        ) {
            cachedXtreamVodCategories = diskCache.items
            xtreamVodCategoriesLoadedAtMs = diskCache.savedAtMs
            return@withContext diskCache.items
        }
        if (!allowNetwork) {
            val items = diskCache?.items.orEmpty()
            if (items.isNotEmpty() && diskCache != null) {
                cachedXtreamVodCategories = items
                xtreamVodCategoriesLoadedAtMs = diskCache.savedAtMs
            }
            return@withContext items
        }
        val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_vod_categories"
        val list: List<XtreamVodCategoryWire> = requestJson(
            url,
            TypeToken.getParameterized(List::class.java, XtreamVodCategoryWire::class.java).type,
            client = xtreamLookupHttpClient
        ) ?: emptyList()
        if (list.isNotEmpty()) {
            val ts = System.currentTimeMillis()
            cachedXtreamVodCategories = list
            xtreamVodCategoriesLoadedAtMs = ts
            runCatching { writeDiskCache(diskFile, ts, list) }
        }
        list
    }

    private suspend fun loadXtreamSeriesCategoriesInternal(
        creds: XtreamCredentials,
        allowNetwork: Boolean
    ): List<XtreamSeriesCategoryWire> = withContext(Dispatchers.IO) {
        val now = System.currentTimeMillis()
        if (cachedXtreamSeriesCategories.isNotEmpty() && now - xtreamSeriesCategoriesLoadedAtMs < xtreamVodCacheMs) {
            return@withContext cachedXtreamSeriesCategories
        }
        val diskFile = seriesCategoriesDiskCacheFile(creds)
        val diskCache: XtreamDiskCache<XtreamSeriesCategoryWire>? =
            readDiskCache(diskFile, seriesCategoriesDiskCacheType)
        if (diskCache != null && diskCache.items.isNotEmpty() &&
            now - diskCache.savedAtMs < xtreamVodCacheMs
        ) {
            cachedXtreamSeriesCategories = diskCache.items
            xtreamSeriesCategoriesLoadedAtMs = diskCache.savedAtMs
            return@withContext diskCache.items
        }
        if (!allowNetwork) {
            val items = diskCache?.items.orEmpty()
            if (items.isNotEmpty() && diskCache != null) {
                cachedXtreamSeriesCategories = items
                xtreamSeriesCategoriesLoadedAtMs = diskCache.savedAtMs
            }
            return@withContext items
        }
        val url = "${creds.baseUrl}/player_api.php?username=${creds.username}&password=${creds.password}&action=get_series_categories"
        val list: List<XtreamSeriesCategoryWire> = requestJson(
            url,
            TypeToken.getParameterized(List::class.java, XtreamSeriesCategoryWire::class.java).type,
            client = xtreamLookupHttpClient
        ) ?: emptyList()
        if (list.isNotEmpty()) {
            val ts = System.currentTimeMillis()
            cachedXtreamSeriesCategories = list
            xtreamSeriesCategoriesLoadedAtMs = ts
            runCatching { writeDiskCache(diskFile, ts, list) }
        }
        list
    }

    suspend fun getVodCategories(): List<XtreamVodCategoryInfo> {
        val config = observeConfig().first()
        val creds = xtreamCredentialsForVodImport(config).firstOrNull() ?: return emptyList()
        return loadXtreamVodCategoriesInternal(creds, allowNetwork = true)
            .mapNotNull { it.toInfoOrNull() }
    }

    suspend fun getCachedVodCategories(): List<XtreamVodCategoryInfo> {
        val config = observeConfig().first()
        val creds = xtreamCredentialsForVodImport(config).firstOrNull() ?: return emptyList()
        return loadXtreamVodCategoriesInternal(creds, allowNetwork = false)
            .mapNotNull { it.toInfoOrNull() }
    }

    suspend fun getSeriesCategories(): List<XtreamSeriesCategoryInfo> {
        val config = observeConfig().first()
        val creds = xtreamCredentialsForSeriesImport(config).firstOrNull() ?: return emptyList()
        return loadXtreamSeriesCategoriesInternal(creds, allowNetwork = true)
            .mapNotNull { it.toInfoOrNull() }
    }

    suspend fun getCachedSeriesCategories(): List<XtreamSeriesCategoryInfo> {
        val config = observeConfig().first()
        val creds = xtreamCredentialsForSeriesImport(config).firstOrNull() ?: return emptyList()
        return loadXtreamSeriesCategoriesInternal(creds, allowNetwork = false)
            .mapNotNull { it.toInfoOrNull() }
    }

    private fun XtreamVodCategoryWire.toInfoOrNull(): XtreamVodCategoryInfo? {
        val id = categoryId?.trim().orEmpty()
        val name = categoryName?.trim().orEmpty()
        if (id.isBlank()) return null
        return XtreamVodCategoryInfo(id, name)
    }

    private fun XtreamSeriesCategoryWire.toInfoOrNull(): XtreamSeriesCategoryInfo? {
        val id = categoryId?.trim().orEmpty()
        val name = categoryName?.trim().orEmpty()
        if (id.isBlank()) return null
        return XtreamSeriesCategoryInfo(id, name)
    }

    // ── VOD ID-only index (cheap, built at catalog load) ────────────────────

    /**
     * Lightweight TMDB/IMDb → catalog-index map, built inline during
     * `loadXtreamVodStreams` (~100 ms for a 60 k catalog). Lets the common
     * ID-based movie lookup short-circuit to O(1) without paying the
     * full token-index build cost.
     */
    private data class VodIdIndex(
        val items: List<XtreamVodStream>,
        val tmdbMap: Map<String, List<Int>>,
        val imdbMap: Map<String, List<Int>>
    )

    @Volatile
    private var cachedVodIdIndex: VodIdIndex? = null

    private fun buildVodIdIndex(catalog: List<XtreamVodStream>): VodIdIndex {
        if (catalog.isEmpty()) return VodIdIndex(catalog, emptyMap(), emptyMap())
        val tmdbMap = HashMap<String, MutableList<Int>>()
        val imdbMap = HashMap<String, MutableList<Int>>()
        catalog.forEachIndexed { idx, item ->
            val tmdbKey = normalizeTmdbId(item.tmdb)
            if (!tmdbKey.isNullOrBlank()) {
                tmdbMap.getOrPut(tmdbKey) { mutableListOf() }.add(idx)
            }
            val imdbKey = normalizeImdbId(item.imdb)
            if (!imdbKey.isNullOrBlank() && imdbKey != IptvIdSentinels.IMDB_NONE) {
                imdbMap.getOrPut(imdbKey) { mutableListOf() }.add(idx)
            }
        }
        return VodIdIndex(items = catalog, tmdbMap = tmdbMap, imdbMap = imdbMap)
    }

    // ── VOD catalog index (O(1) ID lookup + O(k) token intersection) ────────

    private data class VodCatalogIndex(
        val createdAtMs: Long,
        val items: List<XtreamVodStream>,
        val tmdbMap: Map<String, List<Int>>,
        val imdbMap: Map<String, List<Int>>,
        val canonicalTitleMap: Map<String, List<Int>>,
        val tokenMap: Map<String, List<Int>>
    )

    @Volatile
    private var cachedVodIndex: VodCatalogIndex? = null

    private fun buildVodCatalogIndex(catalog: List<XtreamVodStream>): VodCatalogIndex {
        val tmdbMap = HashMap<String, MutableList<Int>>()
        val imdbMap = HashMap<String, MutableList<Int>>()
        val canonicalTitleMap = HashMap<String, MutableList<Int>>()
        val tokenMap = HashMap<String, MutableList<Int>>()
        catalog.forEachIndexed { idx, item ->
            val tmdbKey = normalizeTmdbId(item.tmdb)
            if (!tmdbKey.isNullOrBlank()) {
                tmdbMap.getOrPut(tmdbKey) { mutableListOf() }.add(idx)
            }
            val imdbKey = normalizeImdbId(item.imdb)
            if (!imdbKey.isNullOrBlank() && imdbKey != IptvIdSentinels.IMDB_NONE) {
                imdbMap.getOrPut(imdbKey) { mutableListOf() }.add(idx)
            }
            val name = item.name?.trim().orEmpty()
            if (name.isNotBlank()) {
                val normalized = normalizeLookupText(name)
                val tokens = extractTitleTokensFromNormalized(normalized)
                if (tokens.isNotEmpty()) {
                    val canonical = toCanonicalTitleKeyFromTokens(tokens)
                    if (canonical.isNotBlank()) {
                        canonicalTitleMap.getOrPut(canonical) { mutableListOf() }.add(idx)
                    }
                    // Index-only alias for transcribed umlauts ("Fuer" -> "fur"), so a
                    // query for the properly spelled title reaches this entry too.
                    val aliasCanonical = toCanonicalTitleKeyFromTokens(umlautAliasTokens(tokens))
                    if (aliasCanonical.isNotBlank() && aliasCanonical != canonical) {
                        canonicalTitleMap.getOrPut(aliasCanonical) { mutableListOf() }.add(idx)
                    }
                    withUmlautAliasTokens(tokens).forEach { token ->
                        tokenMap.getOrPut(token) { mutableListOf() }.add(idx)
                    }
                }
            }
        }
        return VodCatalogIndex(
            createdAtMs = System.currentTimeMillis(),
            items = catalog,
            tmdbMap = tmdbMap,
            imdbMap = imdbMap,
            canonicalTitleMap = canonicalTitleMap,
            tokenMap = tokenMap
        )
    }

    private fun ensureVodCatalogIndex(vod: List<XtreamVodStream>): VodCatalogIndex {
        val existing = cachedVodIndex
        if (existing != null && existing.items === vod) {
            return existing
        }
        return buildVodCatalogIndex(vod).also { cachedVodIndex = it }
    }

    /**
     * Look up movie matches using the indexed catalog. Falls back to a token
     * scan only across a narrow candidate set rather than the whole catalog.
     */
    private fun findMovieCandidatesIndexed(
        index: VodCatalogIndex,
        normalizedTitle: String,
        normalizedTmdb: String?,
        normalizedImdb: String?,
        inputYear: Int?
    ): List<XtreamVodStream> {
        if (index.items.isEmpty()) return emptyList()

        if (!normalizedTmdb.isNullOrBlank()) {
            val hits = index.tmdbMap[normalizedTmdb].orEmpty()
            if (hits.isNotEmpty()) return hits.map { index.items[it] }
        }
        if (!normalizedImdb.isNullOrBlank() && normalizedImdb != IptvIdSentinels.IMDB_NONE) {
            val hits = index.imdbMap[normalizedImdb].orEmpty()
            if (hits.isNotEmpty()) return hits.map { index.items[it] }
        }
        if (normalizedTitle.isBlank()) return emptyList()

        val canonical = toCanonicalTitleKey(normalizedTitle)
        val canonicalHits = if (canonical.isNotBlank()) {
            index.canonicalTitleMap[canonical].orEmpty().map { index.items[it] }
        } else emptyList()

        // Build candidate pool via token intersection: union of items mentioning any token.
        val queryTokens = extractTitleTokensFromNormalized(normalizedTitle)
        val candidatePoolIdx = LinkedHashSet<Int>()
        queryTokens.forEach { token ->
            index.tokenMap[token]?.forEach { idx -> candidatePoolIdx.add(idx) }
        }
        if (candidatePoolIdx.isEmpty() && canonicalHits.isEmpty()) return emptyList()

        // Score on the candidate set (typically < 100 items even on big catalogs).
        val scored = (canonicalHits.indices.map { canonicalHits[it] } + candidatePoolIdx.map { index.items[it] })
            .asSequence()
            .distinctBy { it.streamId ?: System.identityHashCode(it) }
            .mapNotNull { item ->
                val name = item.name?.trim().orEmpty()
                if (name.isBlank()) return@mapNotNull null
                val score = scoreNameMatch(name, normalizedTitle)
                if (score <= 0) return@mapNotNull null
                val providerYear = parseYear(item.year ?: name)
                val yearDelta = if (inputYear != null && providerYear != null) {
                    kotlin.math.abs(providerYear - inputYear)
                } else null
                val yearAdjust = when {
                    inputYear == null || providerYear == null -> 0
                    yearDelta == 0 -> 20
                    yearDelta == 1 -> 8
                    else -> -25
                }
                Pair(item, score + yearAdjust)
            }
            .sortedByDescending { it.second }
            .toList()
        val bestScore = scored.firstOrNull()?.second ?: return emptyList()
        val minScore = maxOf(65, bestScore - 8)
        return scored.takeWhile { it.second >= minScore }.map { it.first }
    }

    // ── Movie source cache (persistent, profile-scoped, 24h TTL) ────────────

    private data class CachedIptvMovieSources(
        val sources: List<StreamSource>,
        val savedAtMs: Long,
        val credsFingerprint: String
    )

    private data class PersistedMovieSourceCache(
        val items: Map<String, CachedIptvMovieSources> = emptyMap()
    )

    private val iptvMovieSourcePrefs by lazy {
        context.getSharedPreferences("iptv_movie_source_cache_v1", Context.MODE_PRIVATE)
    }
    private val iptvMovieSourcePrefsKey = "movie_source_map"

    private val iptvMovieSourceMemory = object : LinkedHashMap<String, CachedIptvMovieSources>(256, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, CachedIptvMovieSources>?): Boolean {
            return size > 256
        }
    }
    private val iptvMovieSourceLock = Any()

    @Volatile
    private var iptvMovieSourceCacheHydrated = false

    private val iptvMovieSourceTtlMs = 24 * 60 * 60_000L
    @Volatile
    private var cachedProfileIdHashPair: Pair<String, String>? = null
    private val iptvCacheScope = kotlinx.coroutines.CoroutineScope(
        kotlinx.coroutines.SupervisorJob() + Dispatchers.IO
    )
    @Volatile
    private var iptvMovieSourcePersistJob: kotlinx.coroutines.Job? = null
    private val iptvPersistLock = Any()

    private fun hydrateIptvMovieSourceCache() {
        if (iptvMovieSourceCacheHydrated) return
        // Deserialize outside the lock so concurrent callers don't queue on the monitor
        // waiting for a potentially large JSON parse.
        val loaded: Map<String, CachedIptvMovieSources> = runCatching {
            val raw = iptvMovieSourcePrefs.getString(iptvMovieSourcePrefsKey, null)
            if (!raw.isNullOrBlank()) gson.fromJson(raw, PersistedMovieSourceCache::class.java)?.items else null
        }.getOrNull().orEmpty()
        synchronized(iptvMovieSourceLock) {
            if (iptvMovieSourceCacheHydrated) return
            iptvMovieSourceMemory.putAll(loaded)
            iptvMovieSourceCacheHydrated = true
        }
    }

    private fun iptvMovieSourceCacheKey(
        profileIdHash: String,
        imdbId: String?,
        tmdbId: Int?,
        title: String,
        year: Int?
    ): String? {
        val imdb = IptvIdSentinels.normalizeImdb(imdbId)
        val tmdb = IptvIdSentinels.normalizeTmdb(tmdbId)
        val titleKey = if (IptvIdSentinels.isReal(imdb) || IptvIdSentinels.isReal(tmdb)) {
            ""
        } else {
            val canonical = toCanonicalTitleKey(title)
            // For title-only matches, append year to disambiguate remakes
            // (e.g., Dune 1984 vs Dune 2021)
            val resolvedYear = year ?: parseYear(title)
            if (canonical.isNotBlank() && resolvedYear != null) {
                "$canonical|$resolvedYear"
            } else {
                canonical
            }
        }
        // No real id AND no title token → uncacheable (would collide).
        if (!IptvIdSentinels.isReal(imdb) && !IptvIdSentinels.isReal(tmdb) && titleKey.isBlank()) {
            return null
        }
        return "$profileIdHash|$imdb|$tmdb|$titleKey"
    }

    private fun profileIdHash(): String {
        val raw = runCatching { profileManager.getProfileIdSync() }.getOrDefault("default")
        cachedProfileIdHashPair?.let { (cachedRaw, cachedHash) ->
            if (cachedRaw == raw) return cachedHash
        }
        val hash = MessageDigest.getInstance("MD5").digest(raw.toByteArray(StandardCharsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
            .take(12)
        cachedProfileIdHashPair = raw to hash
        return hash
    }

    private fun lookupCachedMovieSources(
        key: String,
        credsFingerprint: String
    ): List<StreamSource>? {
        hydrateIptvMovieSourceCache()
        val entry = synchronized(iptvMovieSourceLock) { iptvMovieSourceMemory[key] } ?: return null
        if (entry.credsFingerprint != credsFingerprint) return null
        if (entry.sources.isEmpty()) return null
        if (System.currentTimeMillis() - entry.savedAtMs > iptvMovieSourceTtlMs) return null
        return entry.sources
    }

    private fun storeCachedMovieSources(
        key: String,
        sources: List<StreamSource>,
        credsFingerprint: String
    ) {
        if (sources.isEmpty()) return
        hydrateIptvMovieSourceCache()
        synchronized(iptvMovieSourceLock) {
            iptvMovieSourceMemory[key] = CachedIptvMovieSources(
                sources = sources,
                savedAtMs = System.currentTimeMillis(),
                credsFingerprint = credsFingerprint
            )
        }
        scheduleIptvMovieSourceCachePersist()
    }

    private fun scheduleIptvMovieSourceCachePersist() {
        synchronized(iptvPersistLock) {
            iptvMovieSourcePersistJob?.cancel()
            iptvMovieSourcePersistJob = iptvCacheScope.launch {
                delay(2_000L)
                val snapshot = synchronized(iptvMovieSourceLock) {
                    PersistedMovieSourceCache(iptvMovieSourceMemory.toMap())
                }
                runCatching {
                    iptvMovieSourcePrefs.edit()
                        .putString(iptvMovieSourcePrefsKey, gson.toJson(snapshot))
                        .apply()
                }
            }
        }
    }

    private fun clearIptvMovieSourceCache() {
        synchronized(iptvMovieSourceLock) {
            iptvMovieSourceMemory.clear()
        }
        runCatching { iptvMovieSourcePrefs.edit().remove(iptvMovieSourcePrefsKey).apply() }
        synchronized(iptvPersistLock) { iptvMovieSourcePersistJob?.cancel() }
    }

    // ════════════════════════════════════════════════════════════════════════

    private companion object {
        private val DURATION_SCALE_REGEX = Regex("""\$\{duration:(\d+)\}|\{duration:(\d+)\}""")
        private val URL_QUERY_SECRETS_REGEX = Regex("""(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+""")
        private val URL_PATH_SECRETS_REGEX = Regex("""(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)""")
        private val QUALITY_WORDS_REGEX = Regex("""\b(4K|UHD|FHD|HD|SD|2160P?|1080P?|720P?|576P?|480P?)\b""", RegexOption.IGNORE_CASE)
        private val BRACKET_PAREN_REGEX = Regex("""\[[^\]]*]|\([^)]*\)""")

        const val ENC_PREFIX = "encv1:"
        const val ANDROID_KEYSTORE = "AndroidKeyStore"
        const val CONFIG_KEY_ALIAS = "arvio_iptv_config_v1"
        const val MAX_IPTV_CACHE_BYTES = 25L * 1024L * 1024L
        val PARTIAL_PAGED_CACHE_REPAIR_COUNTS = setOf(144, 240)
        const val IPTV_USER_AGENT = "VLC/3.0.20 LibVLC/3.0.20"
        const val BROWSER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"

        // Title-cleanup regexes live in IptvTitleNormalizer so the whole
        // normalization stays testable without an Android Context.
        val BRACKET_CONTENT_REGEX = IptvTitleNormalizer.BRACKET_CONTENT_REGEX
        val PAREN_CONTENT_REGEX = IptvTitleNormalizer.PAREN_CONTENT_REGEX
        val MULTI_SPACE_REGEX = IptvTitleNormalizer.MULTI_SPACE_REGEX
        val HTTP_URL_REGEX = Regex("""https?://[^\s,;|"]+""", RegexOption.IGNORE_CASE)
        val SEASON_KEY_REGEX = Regex("""\d{1,2}""")
        val FLEXIBLE_INT_REGEX = Regex("""\d{1,4}""")
        val IMDB_ID_REGEX = Regex("tt\\d{5,10}")
        val TMDB_ID_REGEX = Regex("\\d{1,10}")
        val YEAR_REGEX = Regex("(19|20)\\d{2}")
        val SEASON_EPISODE_PATTERNS = listOf(
            Regex("""\bs(\d{1,2})\s*[\.\-_ ]*\s*e(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\b(\d{1,2})x(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bseason\s*(\d{1,2}).*episode\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bseason\s*(\d{1,2}).*ep(?:isode)?\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\b(\d{1,2})\.(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\b(\d)(\d{2})\b""", RegexOption.IGNORE_CASE)
        )
        val EPISODE_ONLY_PATTERNS = listOf(
            Regex("""\bepisode\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bep\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\be(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""\bpart\s*(\d{1,3})\b""", RegexOption.IGNORE_CASE),
            Regex("""[\[\(\- ](\d{1,3})[\]\) ]?$""", RegexOption.IGNORE_CASE)
        )
        val HTML_STYLE_REGEX = Regex("<style[^>]*>[\\s\\S]*?</style>", RegexOption.IGNORE_CASE)
        val HTML_SCRIPT_REGEX = Regex("<script[^>]*>[\\s\\S]*?</script>", RegexOption.IGNORE_CASE)
        val HTML_TAG_REGEX = Regex("<[^>]+>")
        val CSS_BRACE_REGEX = Regex("\\{[^}]*\\}")
        val NON_ALPHA_NUM_REGEX_INLINE = Regex("[^a-z0-9]")
        val QUALITY_SUFFIX_REGEX = Regex("\\b(hd|fhd|uhd|sd|lq|hq|4k|8k|2160p|1080p|720p|hevc|x265|x264|h264|h265)\\b")
        val GUIDE_PREFIX_REGEX = Regex("""^\s*[a-z]{2,4}\s*[\|:：/\-]+\s*""", RegexOption.IGNORE_CASE)

        val XMLTV_LOCAL_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

        val XMLTV_OFFSET_FORMATTER: DateTimeFormatter = DateTimeFormatterBuilder()
            .appendPattern("yyyyMMddHHmmss")
            .optionalStart()
            .appendLiteral(' ')
            .appendPattern("XX")
            .optionalEnd()
            .toFormatter(Locale.US)

    }
}


private object IptvRepositoryRegexes {
    val DURATION_PLACEHOLDER_REGEX = Regex("""\$\{duration:(\d+)\}|\{duration:(\d+)\}""")
    val IPTV_URL_REDACT_SECRETS_REGEX = Regex("""(?i)([?&](?:username|user|uname|password|pass|pwd)=)[^&]+""")
    val IPTV_URL_REDACT_PATH_REGEX = Regex("""(?i)(/(?:live|movie|series|timeshift)/)([^/]+)/([^/]+)(/)""")
    val RESOLUTION_TAG_REGEX = Regex("""\b(4K|UHD|FHD|HD|SD|2160P?|1080P?|720P?|576P?|480P?)\b""", RegexOption.IGNORE_CASE)
    val BRACKET_PAREN_REGEX = Regex("""\[[^\]]*]|\([^)]*\)""")
}
