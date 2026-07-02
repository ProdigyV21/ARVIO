package com.arflix.tv

import android.app.ActivityManager
import android.app.Application
import android.content.ComponentCallbacks2
import android.graphics.Bitmap
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.imageLoader
import coil.memory.MemoryCache
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.data.repository.AppUsageAnalyticsRepository
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.data.repository.CloudSyncCoordinator
import com.arflix.tv.data.repository.CloudSyncRepository
import com.arflix.tv.data.repository.RealtimeSyncManager
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.AppLogger
import com.arflix.tv.util.CrashlyticsProvider
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.SentryCrashReporter
import com.arflix.tv.util.detectDeviceType
import com.arflix.tv.worker.TraktSyncWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.arflix.tv.util.settingsDataStore
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * ARVIO TV Application class
 */
@HiltAndroidApp
class ArflixApplication : Application(), Configuration.Provider, ImageLoaderFactory {
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    @Volatile
    private var appImageLoader: ImageLoader? = null

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    @Inject
    lateinit var profileManager: ProfileManager
    @Inject
    lateinit var authRepository: AuthRepository
    @Inject
    lateinit var cloudSyncRepository: CloudSyncRepository
    @Inject
    lateinit var cloudSyncCoordinator: CloudSyncCoordinator
    @Inject
    lateinit var realtimeSyncManager: RealtimeSyncManager
    @Inject
    lateinit var watchlistRepository: WatchlistRepository
    @Inject
    lateinit var appUsageAnalyticsRepository: AppUsageAnalyticsRepository

    override fun onCreate() {
        super.onCreate()
        instance = this

        // OkHttpProvider.init(context) just stashes the app context; it does
        // not build the OkHttpClient. Safe to keep on the main thread — it's
        // a single volatile assignment.
        OkHttpProvider.init(this)

        // Initialize global DNS provider and user agent from DataStore before network calls.
        appScope.launch(Dispatchers.IO) {
            val prefs = settingsDataStore.data.first()
            val dnsKey = androidx.datastore.preferences.core.stringPreferencesKey(OkHttpProvider.DNS_PROVIDER_PREF_KEY)
            val dnsPref = prefs[dnsKey]
            val provider = OkHttpProvider.parseDnsProvider(dnsPref)
            OkHttpProvider.setDnsProvider(provider)

            val uaKey = androidx.datastore.preferences.core.stringPreferencesKey(OkHttpProvider.USER_AGENT_PREF_KEY)
            val savedUserAgent = prefs[uaKey].orEmpty()
            OkHttpProvider.setCustomUserAgent(savedUserAgent)

            runCatching { OkHttpProvider.dns.lookup("image.tmdb.org") }
        }

        // Initialize crash reporting. Sentry is preferred when SENTRY_DSN is configured;
        // Crashlytics remains as a fallback for builds with Firebase configuration.
        if (!SentryCrashReporter.initialize(this)) {
            CrashlyticsProvider.initialize()
        }

        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                val prefs = getSharedPreferences("arvio_crash_store", android.content.Context.MODE_PRIVATE)
                val fallbackId = java.util.UUID.randomUUID().toString()
                prefs.edit()
                    .putString("last_crash_id", fallbackId)
                    .putString("last_crash_msg", "${throwable::class.java.simpleName}: ${throwable.message?.take(200) ?: ""}")
                    .putLong("last_crash_time", System.currentTimeMillis())
                    .putString("last_crash_version", "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
                    .putBoolean("has_pending_crash_report", true)
                    .commit()
            } catch (_: Throwable) {
            } finally {
                defaultHandler?.uncaughtException(thread, throwable) ?: android.os.Process.killProcess(android.os.Process.myPid())
            }
        }
        // Initialize active profile asynchronously to avoid blocking cold start.
        // Wire realtime push notification when realtime is enabled.
        cloudSyncRepository.onPushCompleted = { realtimeSyncManager.markPush() }

        appScope.launch {
            runCatching { profileManager.initialize() }
            // Preload watchlist cache in background for instant display
            runCatching { watchlistRepository.getWatchlistItems() }
            delay(2_500L)
            cloudSyncCoordinator.start()
            if (!authRepository.getCurrentUserId().isNullOrBlank()) {
                if (BuildConfig.ENABLE_REALTIME_CLOUD_SYNC) {
                    realtimeSyncManager.start()
                }
                // Pull early enough that reopening the app feels like an actual
                // sync, while still letting the first frame and profile bootstrap land.
                delay(3_000L)
                runCatching { cloudSyncRepository.pullFromCloud() }
            }
        }

        appScope.launch {
            // Wait for first navigation/auth restore work to start so the
            // event can include account context without delaying app launch.
            delay(3_000L)
            runCatching { appUsageAnalyticsRepository.recordAppOpen() }
        }

        // Observe auth state: start realtime on login, stop on logout
        appScope.launch {
            authRepository.authState.collectLatest { state ->
                when (state) {
                    is AuthState.Authenticated -> {
                        delay(2_000L)
                        if (!authRepository.getCurrentUserId().isNullOrBlank()) {
                            cloudSyncCoordinator.start()
                            if (BuildConfig.ENABLE_REALTIME_CLOUD_SYNC) {
                                realtimeSyncManager.start()
                            }
                            runCatching { cloudSyncRepository.pullFromCloud() }
                        }
                    }
                    AuthState.NotAuthenticated -> {
                        realtimeSyncManager.stop()
                        cloudSyncCoordinator.stop()
                    }
                    is AuthState.Error -> {
                        realtimeSyncManager.stop()
                        cloudSyncCoordinator.stop()
                    }
                    AuthState.Loading -> Unit
                }
            }
        }
    }

    override fun newImageLoader(): ImageLoader {
        val isTvDevice = detectDeviceType(this) == DeviceType.TV
        val isLowRamDevice = isLowRamDevice()
        return ImageLoader.Builder(this)
            // Use the dedicated Coil HTTP client instead of the main API client.
            // Avoids logging interceptor overhead and connection pool contention.
            .okHttpClient(OkHttpProvider.coilClient)
            .memoryCache {
                MemoryCache.Builder(this)
                    // The 2 GB Android TV dump showed Arvio spending most of
                    // its memory in native bitmap/texture allocations
                    // (255 MB native heap, 77 MB GPU cache). Use a fixed TV
                    // image budget instead of a percent of largeHeap memory so
                    // low-RAM TVs do not drift into zram pressure while rows
                    // are being scrolled.
                    .maxSizeBytes(
                        when {
                            isTvDevice && isLowRamDevice -> 32 * 1024 * 1024
                            isTvDevice -> 48 * 1024 * 1024
                            else -> 64 * 1024 * 1024
                        }
                    )
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizeBytes(if (isTvDevice) 128L * 1024L * 1024L else 96L * 1024L * 1024L)
                    .build()
            }
            .crossfade(false)
            .respectCacheHeaders(false)
            .allowRgb565(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            // No global placeholder — card composables use their own surface
            // background color as the visual placeholder. A global placeholder
            // causes a dark-rectangle flash behind transparent clearlogo PNGs
            // on the home hero. Error = transparent so failed loads are invisible
            // (the card surface background is the fallback visual).
            .error(android.R.color.transparent)
            .components {
                add(SvgDecoder.Factory())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
            .also { appImageLoader = it }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (
            level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN ||
            level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE
        ) {
            clearImageMemoryCaches()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        clearImageMemoryCaches()
    }

    private fun clearImageMemoryCaches() {
        appImageLoader?.memoryCache?.clear()
        // Settings can replace Coil's global loader after DNS changes; clear the
        // active loader too so memory-pressure callbacks always affect what UI uses.
        runCatching { imageLoader.memoryCache?.clear() }
    }

    private fun isLowRamDevice(): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as? ActivityManager
        return activityManager?.isLowRamDevice == true
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.ASSERT)
            .build()

    /**
     * Schedule periodic Trakt data sync
     */
    fun scheduleTraktSyncIfNeeded() {
        // Use INCREMENTAL sync on startup for fast app launch
        // Full sync only happens on periodic schedule or explicit user action
        val oneTimeRequest = OneTimeWorkRequestBuilder<TraktSyncWorker>()
            // Defer startup sync to keep first-run navigation and scrolling smooth.
            .setInitialDelay(2, TimeUnit.MINUTES)
            .setInputData(
                workDataOf(TraktSyncWorker.INPUT_SYNC_MODE to TraktSyncWorker.SYNC_MODE_INCREMENTAL)
            )
            .addTag(TraktSyncWorker.TAG)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<TraktSyncWorker>(
            TraktSyncWorker.SYNC_INTERVAL_HOURS, TimeUnit.HOURS
        )
            .addTag(TraktSyncWorker.TAG)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            TraktSyncWorker.WORK_NAME_ON_OPEN,
            ExistingWorkPolicy.KEEP,
            oneTimeRequest
        )

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            TraktSyncWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }

    companion object {
        lateinit var instance: ArflixApplication
            private set

        fun trimImageMemory() {
            if (::instance.isInitialized) {
                instance.clearImageMemoryCaches()
            }
        }
    }
}
