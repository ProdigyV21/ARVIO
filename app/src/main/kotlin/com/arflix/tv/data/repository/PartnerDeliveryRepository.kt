package com.arflix.tv.data.repository

import android.content.Context
import android.os.Build
import com.arflix.tv.BuildConfig
import com.arflix.tv.util.Constants
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.isActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartnerDeliveryRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val auth: AuthRepository,
    client: OkHttpClient,
    private val streams: dagger.Lazy<StreamRepository>? = null,
    private val homeServers: dagger.Lazy<HomeServerRepository>? = null
) {
    internal data class State(val accountId: String, val delivery: PartnerIptvDelivery)
    private val mutableState = MutableStateFlow<State?>(null)
    internal val state = mutableState.asStateFlow()
    private val mutableStatus = MutableStateFlow("Not connected")
    val status = mutableStatus.asStateFlow()
    private val mutableAcknowledgedRevision = MutableStateFlow(0)
    internal val acknowledgedRevision = mutableAcknowledgedRevision.asStateFlow()
    internal fun accountId(): String? = (auth.authState.value as? AuthState.Authenticated)?.userId
    private val sessionMutex = Mutex()
    private val http = client.newBuilder().callTimeout(20, TimeUnit.SECONDS)
        .followRedirects(false).followSslRedirects(false).build()

    internal fun activeDelivery(): PartnerIptvDelivery? = mutableState.value?.takeIf {
        BuildConfig.PARTNER_MANAGEMENT_ENABLED &&
            it.accountId == (auth.authState.value as? AuthState.Authenticated)?.userId &&
            it.delivery.expiresAt > System.currentTimeMillis()
    }?.delivery

    @OptIn(ExperimentalCoroutinesApi::class)
    internal val deliveries = combine(state, auth.authState) { _, _ -> activeDelivery() }
        .flatMapLatest { delivery -> flow {
            emit(delivery)
            if (delivery != null) {
                delay((delivery.expiresAt - System.currentTimeMillis()).coerceAtLeast(1L))
                emit(null)
            }
        } }.distinctUntilChanged()

    private suspend fun resolveCollections(delivery: PartnerIptvDelivery): PartnerIptvDelivery = withContext(Dispatchers.IO) {
        val catalogs = delivery.catalogs.toMutableList()
        for (url in delivery.collectionUrls) {
            val request = Request.Builder().url(url).build()
            val json = http.newCall(request).execute().use { response ->
                check(response.isSuccessful) { "Collection feed unavailable" }
                val body = response.body ?: error("Empty collection feed")
                require(body.contentLength() <= 4_194_304) { "Collection feed too large" }
                val source = body.source()
                source.request(4_194_305)
                require(source.buffer.size <= 4_194_304) { "Collection feed too large" }
                source.readUtf8()
            }
            val rails = CustomCollections.parse(json, url).getOrThrow()
            catalogs += CustomCollections.catalogs(rails).map { PartnerCatalogs.namespace(it, delivery.organizationId) }
        }
        val addons = mutableListOf<com.arflix.tv.data.model.Addon>()
        val servers = mutableListOf<HomeServerConnection>()
        val aliases = mutableMapOf<String, String>()
        for (service in delivery.additionalServices) {
            val serviceId = service.get("id")?.asString.orEmpty()
            require(serviceId.matches(Regex("[a-zA-Z0-9-]{1,128}"))) { "Invalid service ID" }
            val id = PartnerCatalogs.id(delivery.organizationId, serviceId)
            val url = service.get("url")?.asString ?: error("Missing service URL")
            if (service.get("kind").asString == "addon") {
                // Public/shared manifests have no per-customer credential object.
                val credentials = service.get("credentials")?.takeUnless { it.isJsonNull }?.asJsonObject
                val addon = requireNotNull(streams).get().prepareCustomAddon(
                    credentials?.get("manifestUrl")?.takeUnless { it.isJsonNull }?.asString
                        ?.takeIf { it.isNotBlank() } ?: url).getOrThrow()
                aliases[addon.id] = id
                addon.manifest?.id?.let { aliases[it] = id }
                aliases[serviceId] = id
                addons += addon.copy(id = id, isInstalled = true, isEnabled = true)
            } else {
                aliases[serviceId] = id
                servers += requireNotNull(homeServers).get().resolveManagedConnection(id, service)
            }
        }
        delivery.copy(catalogs = catalogs.distinctBy { it.id }.map { PartnerCatalogs.remapReferences(it, aliases) },
            addons = addons, homeServers = servers)
    }

    // Called only while foregrounded. IPTV loading consumes the runtime overlay;
    // collection feeds and other service metadata resolve before acknowledgement.
    suspend fun runForegroundSession() = sessionMutex.withLock {
        auth.authState.map { (it as? AuthState.Authenticated)?.userId }
            .distinctUntilChanged().collectLatest { accountId ->
                if (mutableState.value?.accountId != accountId ||
                    (mutableState.value?.delivery?.expiresAt ?: 0L) <= System.currentTimeMillis()) {
                    mutableState.value = null
                    mutableAcknowledgedRevision.value = 0
                }
                mutableStatus.value = "Not connected"
                if (accountId == null || !Constants.USE_NETLIFY_CLOUD_SYNC) return@collectLatest
                var retryMillis = 30_000L
                val key = withContext(Dispatchers.IO) {
                    val prefs = context.getSharedPreferences("partner_devices", Context.MODE_PRIVATE)
                    prefs.getString(accountId, null) ?: UUID.randomUUID().toString().also {
                        check(prefs.edit().putString(accountId, it).commit())
                    }
                }
                while (currentCoroutineContext().isActive) {
                    var waitMillis = 120_000L
                    try {
                        val token = auth.getAccessToken() ?: error("Authentication required")
                        if ((auth.authState.value as? AuthState.Authenticated)?.userId != accountId) return@collectLatest
                        val current = mutableState.value?.takeIf { it.accountId == accountId }?.delivery
                        val device = JsonObject().apply {
                            addProperty("key", key)
                            addProperty("name", "${Build.MANUFACTURER} ${Build.MODEL}")
                            addProperty("platform", "android-${BuildConfig.VERSION_NAME}".take(30))
                            addProperty("appliedRevision", current?.revision ?: 0)
                        }
                        val payload = JsonObject().apply {
                            addProperty("operation", "delivery"); add("device", device)
                        }
                        val response = withContext(Dispatchers.IO) {
                            val request = Request.Builder().url("${Constants.NETLIFY_BACKEND_URL}/partner-api")
                                .header("Authorization", "Bearer $token")
                                .header("Cache-Control", "no-store")
                                .post(payload.toString().toRequestBody("application/json".toMediaType())).build()
                            http.newCall(request).execute().use { result ->
                                if (result.code == 401 || result.code == 403) {
                                    mutableState.value = null
                                }
                                check(result.isSuccessful) { "Partner service unavailable (${result.code})" }
                                val body = result.body ?: error("Empty delivery")
                                require(body.contentLength() <= 1_048_576) { "Delivery too large" }
                                val source = body.source()
                                source.request(1_048_577)
                                require(source.buffer.size <= 1_048_576) { "Delivery too large" }
                                JsonParser.parseString(source.readUtf8()).asJsonObject
                            }
                        }
                        val delivery = try {
                            PartnerIptvDelivery.parse(response, System.currentTimeMillis())?.let { parsed ->
                                if (current != null && parsed.revision == current.revision &&
                                    parsed.organizationId == current.organizationId) {
                                    current.copy(expiresAt = parsed.expiresAt)
                                } else resolveCollections(parsed)
                            }
                        } catch (invalid: Exception) {
                            mutableState.value = null
                            throw invalid
                        }
                        if ((auth.authState.value as? AuthState.Authenticated)?.userId != accountId) return@collectLatest
                        mutableState.value = delivery?.let { State(accountId, it) }
                        if (delivery == null) mutableAcknowledgedRevision.value = 0
                        else if (current?.revision == delivery.revision) mutableAcknowledgedRevision.value = delivery.revision
                        mutableStatus.value = if (delivery == null) "No active managed package" else "Package applied"
                        if (delivery == null) waitMillis = 15 * 60_000L
                        // Acknowledge once promptly, without waiting for the next heartbeat.
                        if (delivery != null && current?.revision != delivery.revision) waitMillis = 2_000L
                        retryMillis = 30_000L
                    } catch (cancelled: CancellationException) {
                        throw cancelled
                    } catch (_: Exception) {
                        // Never log responses, tokens or provider URLs/credentials.
                        mutableStatus.value = "Package could not be applied"
                        waitMillis = retryMillis
                        retryMillis = (retryMillis * 2).coerceAtMost(300_000L)
                    }
                    val expires = mutableState.value?.delivery?.expiresAt
                    if (expires != null && expires <= System.currentTimeMillis()) mutableState.value = null
                    delay(minOf(waitMillis, expires?.minus(System.currentTimeMillis())?.coerceAtLeast(1L) ?: waitMillis))
                    if (expires != null && expires <= System.currentTimeMillis()) mutableState.value = null
                }
            }
    }
}
