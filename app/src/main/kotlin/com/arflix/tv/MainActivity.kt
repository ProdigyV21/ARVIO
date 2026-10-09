package com.arflix.tv

import android.content.Context
import com.arflix.tv.util.AppLogger
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.arflix.tv.R
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import androidx.compose.runtime.mutableFloatStateOf
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.components.LocalBottomBarHeight
import com.arflix.tv.ui.components.mobileContentInsets
import com.arflix.tv.ui.components.currentBottomBarSpec
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arflix.tv.ui.components.AppBottomBar
import com.arflix.tv.ui.components.shouldShowBottomBar
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.content.pm.ActivityInfo
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.DEVICE_MODE_OVERRIDE_KEY
import com.arflix.tv.util.SKIP_PROFILE_SELECTION_KEY
import com.arflix.tv.util.OLED_BLACK_BACKGROUND_KEY
import com.arflix.tv.util.ACCENT_COLOR_KEY
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.LocalHasTouchScreen
import com.arflix.tv.util.LocalAppLanguage
import com.arflix.tv.util.resolveAppLanguage
import com.arflix.tv.util.detectDeviceType
import com.arflix.tv.util.deviceHasTouchScreen
import com.arflix.tv.util.findActivity
import com.arflix.tv.util.settingsDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.PerformanceMetricsState
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.arflix.tv.data.repository.AuthRepository
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository
import com.arflix.tv.data.repository.LauncherContinueWatchingRequest
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.WatchHistoryRepository
import com.arflix.tv.data.repository.WatchlistRepository
import com.arflix.tv.data.repository.toLauncherContinueWatchingRequest
import com.arflix.tv.navigation.AppNavigation
import com.arflix.tv.navigation.Screen
import com.arflix.tv.navigation.ProfileSessionGate
import com.arflix.tv.navigation.PartnerOpenLinkParser
import com.arflix.tv.navigation.PartnerOpenLinkResolver
import com.arflix.tv.navigation.PartnerOpenLinkInbox
import com.arflix.tv.navigation.PartnerOpenRequest
import com.arflix.tv.navigation.PartnerOpenTarget
import com.arflix.tv.navigation.PendingPartnerOpenLink
import com.arflix.tv.navigation.partnerOpenNavigationOptions
import com.arflix.tv.ui.screens.login.LoginScreen
import com.arflix.tv.ui.startup.StartupViewModel
import com.arflix.tv.ui.theme.ArflixTvTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.worker.TraktSyncWorker
import dagger.hilt.android.AndroidEntryPoint
import dagger.Lazy
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.ensureActive
import javax.inject.Inject
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private sealed interface ActiveProfileLoadState {
    data object Loading : ActiveProfileLoadState
    data class Loaded(val profile: com.arflix.tv.data.model.Profile?) : ActiveProfileLoadState
}

/**
 * Main Activity - Single activity architecture with Compose Navigation
 * Uses Android 12+ Splash Screen API for instant launch feedback
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appForegroundSignals: com.arflix.tv.data.repository.AppForegroundSignals

    /** True until the first onResume after onCreate has been handled. */
    private var hasHandledFirstResume = false

    @Inject
    lateinit var authRepository: Lazy<AuthRepository>

    @Inject
    lateinit var profileRepository: Lazy<ProfileRepository>

    @Inject
    lateinit var traktRepository: Lazy<TraktRepository>

    @Inject
    lateinit var profileManager: Lazy<ProfileManager>

    @Inject
    lateinit var watchHistoryRepository: Lazy<WatchHistoryRepository>

    @Inject
    lateinit var watchlistRepository: Lazy<WatchlistRepository>

    @Inject
    lateinit var launcherContinueWatchingRepository: Lazy<LauncherContinueWatchingRepository>

    @Inject
    lateinit var mediaRepository: Lazy<MediaRepository>

    @Inject
    internal lateinit var partnerOpenLinkResolver: Lazy<PartnerOpenLinkResolver>

    // Prefetch IPTV early so the TV screen opens without a loading stall.
    // IptvRepository is @Singleton; touching it at activity start warms the
    // in-memory snapshot (and will trigger a disk-cache read + silent
    // background refresh) so by the time the user navigates into the TV tab
    // everything is already resident.
    @Inject
    lateinit var iptvRepository: Lazy<com.arflix.tv.data.repository.IptvRepository>

    private var jankStats: JankStats? = null
    private var pendingLauncherRequest by mutableStateOf<LauncherContinueWatchingRequest?>(null)
    private var pendingInstallPackUrl by mutableStateOf<String?>(null)
    private var pendingInstallAddonUrl by mutableStateOf<String?>(null)
    private val partnerOpenLinks = PartnerOpenLinkInbox()
    private val pendingPartnerOpenLink get() = partnerOpenLinks.pending

    // StartupViewModel for parallel loading during splash
    private val startupViewModel: StartupViewModel by viewModels()

    override fun attachBaseContext(newBase: Context) {
        val tag = newBase.getSharedPreferences("app_locale", Context.MODE_PRIVATE)
            .getString("locale_tag", null)
        if (!tag.isNullOrEmpty()) {
            val locale = java.util.Locale.forLanguageTag(tag)
            java.util.Locale.setDefault(locale)
            val config = Configuration(newBase.resources.configuration)
            config.setLocale(locale)
            super.attachBaseContext(newBase.createConfigurationContext(config))
        } else {
            super.attachBaseContext(newBase)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen BEFORE super.onCreate()
        // Don't use setKeepOnScreenCondition - it causes black screen on some TV devices
        // Instead, let the splash dismiss immediately and show our Compose loading screen
        installSplashScreen()

        // Detect device type before super.onCreate().
        // The splash screen's postSplashScreenTheme is Theme.ArflixTV.Mobile (no fullscreen)
        // which is correct for phones/tablets. On TV we override to the fullscreen Leanback theme.
        val initialDeviceType = detectDeviceType(this)
        if (initialDeviceType == DeviceType.TV) {
            setTheme(R.style.Theme_ArflixTV)
        }

        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.BLACK))
        window.decorView.setBackgroundColor(android.graphics.Color.BLACK)
        @Suppress("DEPRECATION")
        overridePendingTransition(0, 0)
        pendingLauncherRequest = parseLauncherRequest(intent)
        pendingInstallPackUrl = parseInstallPackUrl(intent)
        pendingInstallAddonUrl = parseInstallAddonUrl(intent)
        // A consumed link must not replay after rotation/process recreation from the old intent.
        if (savedInstanceState?.getBoolean(PARTNER_LINK_STATE_SAVED) == true) {
            partnerOpenLinks.restore(savedInstanceState.getString(PARTNER_LINK_URI), savedInstanceState.getString(PARTNER_LINK_TICKET))
        } else {
            partnerOpenLinks.replace(parsePartnerOpenLink(intent))
        }

        val crashPrefs = getSharedPreferences("arvio_crash_store", Context.MODE_PRIVATE)
        if (crashPrefs.getBoolean("has_pending_crash_report", false)) {
            val crashId = crashPrefs.getString("last_crash_id", "N/A")
            val crashMsg = crashPrefs.getString("last_crash_msg", "Unexpected error")
            val crashTime = crashPrefs.getLong("last_crash_time", System.currentTimeMillis())
            crashPrefs.edit().putBoolean("has_pending_crash_report", false).commit()

            val crashIntent = android.content.Intent(this, com.arflix.tv.ui.screens.crash.CrashReportActivity::class.java).apply {
                putExtra(com.arflix.tv.ui.screens.crash.CrashReportActivity.EXTRA_CRASH_ID, crashId)
                putExtra(com.arflix.tv.ui.screens.crash.CrashReportActivity.EXTRA_CRASH_MSG, crashMsg)
                putExtra(com.arflix.tv.ui.screens.crash.CrashReportActivity.EXTRA_CRASH_TIME, crashTime)
            }
            startActivity(crashIntent)
        }

        // Initialize Discord RPC Manager
        com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.init(this)
        intent?.data?.let { uri ->
            android.util.Log.d("MainActivity", "Received intent data URI in onCreate")
            if (uri.scheme == "arvio" && uri.host == "discord" && uri.path == "/auth") {
                android.util.Log.i("MainActivity", "Matching Discord auth redirect. Forwarding to DiscordRpcManager.")
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.onLoginDeepLink(uri)
            }
        }

        // Set orientation based on device type
        requestedOrientation = when (initialDeviceType) {
            DeviceType.TV -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            DeviceType.TABLET -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
            DeviceType.PHONE -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        }

        if (initialDeviceType == DeviceType.TV) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(WindowInsetsCompat.Type.systemBars())
            }
        } else {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
                navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
                window.isStatusBarContrastEnforced = false
            }
            window.navigationBarColor = android.graphics.Color.TRANSPARENT
            // Clear any FLAG_FULLSCREEN the Leanback theme may have set
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        // Cache warmup runs once in runAfterFirstDraw below (warmup + 60s
        // delayed fresh prefetch). Starting it here as well would serialize a
        // second disk read behind the same loadMutex for no benefit.

        setContent {
            // Observe device mode override changes live from DataStore
            val deviceModeOverride by remember {
                this@MainActivity.settingsDataStore.data.map { it[DEVICE_MODE_OVERRIDE_KEY] }
            }.collectAsStateWithLifecycle(initialValue = null)
            var skipProfileSelection by remember { mutableStateOf<Boolean?>(null) }
            LaunchedEffect(Unit) {
                val skipSelection =
                    this@MainActivity.settingsDataStore.data.first()[SKIP_PROFILE_SELECTION_KEY] ?: false
                if (skipSelection) {
                    val profiles = profileRepository.get()
                    val activeProfile = profiles.getActiveProfile()
                    if (activeProfile == null) {
                        val fallbackProfile = profiles.getProfiles().maxByOrNull { it.lastUsedAt }
                            ?: profiles.createDefaultProfileIfNeeded()
                        if (fallbackProfile != null) {
                            profiles.setActiveProfile(fallbackProfile.id)
                        }
                    }
                }
                skipProfileSelection = skipSelection
            }
            val oledBlackBackground by remember {
                this@MainActivity.settingsDataStore.data.map { it[OLED_BLACK_BACKGROUND_KEY] ?: false }
            }.collectAsStateWithLifecycle(initialValue = false)
            val accentColorName by remember {
                this@MainActivity.settingsDataStore.data.map { it[ACCENT_COLOR_KEY] }
            }.collectAsStateWithLifecycle(initialValue = null)
            val activeProfileId by remember {
                profileRepository.get().activeProfileId
            }.collectAsStateWithLifecycle(initialValue = null)
            val initialAppLanguage = remember {
                getSharedPreferences("app_locale", Context.MODE_PRIVATE)
                    .getString("locale_tag", null)?.takeIf { it.isNotBlank() }
                    ?: com.arflix.tv.util.defaultAppLanguage()
            }
            val appLanguage by remember(activeProfileId) {
                this@MainActivity.settingsDataStore.data.map { prefs ->
                    resolveAppLanguage(prefs, activeProfileId)
                }
            }.collectAsStateWithLifecycle(initialValue = initialAppLanguage)
            LaunchedEffect(appLanguage) {
                mediaRepository.get().contentLanguage = appLanguage
            }
            val deviceType = when (deviceModeOverride) {
                "tv" -> DeviceType.TV
                "tablet" -> DeviceType.TABLET
                "phone" -> DeviceType.PHONE
                else -> initialDeviceType
            }
            val hasTouchScreen = remember { deviceHasTouchScreen(this@MainActivity) }
            // If no touchscreen, force TV mode regardless of override setting
            // (prevents tablet/phone UI on devices with only D-pad input)
            val effectiveDeviceType = if (!hasTouchScreen && deviceType != DeviceType.TV) DeviceType.TV else deviceType
            // Wrap the Activity as a ContextWrapper that only overrides getResources() with
            // localized resources. Hilt traverses ContextWrapper chains to find the Activity,
            // so hiltViewModel() still works correctly.
            val localizedContext = remember(appLanguage) {
                val locale = com.arflix.tv.util.appLocale(appLanguage)
                java.util.Locale.setDefault(locale)
                val config = Configuration(this@MainActivity.resources.configuration)
                config.setLocale(locale)
                val localizedRes = this@MainActivity.createConfigurationContext(config).resources
                object : android.content.ContextWrapper(this@MainActivity) {
                    override fun getResources() = localizedRes
                }
            }
            val isRtl = remember(appLanguage) {
                val lang = java.util.Locale.forLanguageTag(appLanguage.replace('_', '-')).language
                lang in listOf("ar", "he", "iw", "fa", "ur")
            }
            CompositionLocalProvider(
                androidx.compose.ui.platform.LocalContext provides localizedContext,
                LocalAppLanguage provides appLanguage,
                LocalDeviceType provides effectiveDeviceType,
                LocalHasTouchScreen provides hasTouchScreen,
                androidx.compose.ui.platform.LocalLayoutDirection provides
                    if (isRtl) androidx.compose.ui.unit.LayoutDirection.Rtl
                    else androidx.compose.ui.unit.LayoutDirection.Ltr
            ) {
                ArflixTvTheme(
                    oledBlackBackground = oledBlackBackground,
                    accentColorName = accentColorName
                ) {
                    val startupState by startupViewModel.state.collectAsStateWithLifecycle()
                    ArflixApp(
                        authRepository = authRepository.get(),
                        profileRepository = profileRepository.get(),
                        traktRepository = traktRepository.get(),
                        profileManager = profileManager.get(),
                        watchHistoryRepository = watchHistoryRepository.get(),
                        watchlistRepository = watchlistRepository.get(),
                        iptvRepository = iptvRepository.get(),
                        launcherContinueWatchingRepository = launcherContinueWatchingRepository.get(),
                        oledBlackBackground = oledBlackBackground,
                        skipProfileSelection = skipProfileSelection,
                        pendingLauncherRequest = pendingLauncherRequest,
                        onConsumeLauncherRequest = { pendingLauncherRequest = null },
                        pendingInstallPackUrl = pendingInstallPackUrl,
                        onConsumeInstallPackUrl = { pendingInstallPackUrl = null },
                        pendingInstallAddonUrl = pendingInstallAddonUrl,
                        onConsumeInstallAddonUrl = { pendingInstallAddonUrl = null },
                        pendingPartnerOpenLink = pendingPartnerOpenLink,
                        resolvePartnerOpenLink = { partnerOpenLinkResolver.get().resolve(it) },
                        onConsumePartnerOpenLink = partnerOpenLinks::consume,
                        preloadedCategories = startupState.categories,
                        preloadedHeroItem = startupState.heroItem,
                        preloadedHeroLogoUrl = startupState.heroLogoUrl,
                        preloadedLogoCache = startupState.logoCache,
                        onExitApp = { finish() }
                    )
                }
            }
        }

        if (BuildConfig.DEBUG) {
            jankStats = JankStats.createAndTrack(window) { frameData ->
                if (frameData.isJank) {
                    val durationMs = frameData.frameDurationUiNanos / 1_000_000
                }
            }
            PerformanceMetricsState.getHolderForHierarchy(window.decorView)
                .state?.putState("screen", "Main")
        }

        runAfterFirstDraw {
            lifecycleScope.launch {
                authRepository.get().checkAuthState()
            }
            ArflixApplication.instance.scheduleTraktSyncIfNeeded()
            lifecycleScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                val repo = iptvRepository.get()
                try {
                    repo.warmupFromCacheOnly()
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                    AppLogger.recordException(e)
                }
                kotlinx.coroutines.delay(60_000L)
                try {
                    repo.prefetchFreshStartupData()
                } catch (e: Exception) {
                    if (e is kotlinx.coroutines.CancellationException) throw e
                    AppLogger.recordException(e)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Signalled from here, not from a screen: the app can return to the
        // foreground on any destination — a TV woken in the morning resumes onto
        // whatever was left on display — and a Home-scoped observer would never
        // run in that case. The first resume is skipped because startup already
        // fetches.
        if (hasHandledFirstResume) {
            appForegroundSignals.notifyReturnedToForeground()
        } else {
            hasHandledFirstResume = true
        }
    }

    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        pendingLauncherRequest = parseLauncherRequest(intent)
        pendingInstallPackUrl = parseInstallPackUrl(intent)
        pendingInstallAddonUrl = parseInstallAddonUrl(intent)
        val partnerLink = parsePartnerOpenLink(intent)
        val isDiscordAuthCallback = intent.data?.let {
            it.scheme == "arvio" && it.host == "discord" && it.path == "/auth"
        } == true
        if (partnerLink != null ||
            (intent.action == android.content.Intent.ACTION_VIEW && !isDiscordAuthCallback) ||
            intent.action == android.content.Intent.ACTION_MAIN) {
            // Supersede an in-flight lookup immediately. Discord auth callbacks deliberately
            // leave the title pending so authentication cannot discard the user's destination.
            partnerOpenLinks.replace(partnerLink)
        }
        intent.data?.let { uri ->
            android.util.Log.d("MainActivity", "Received intent data URI in onNewIntent")
            if (uri.scheme == "arvio" && uri.host == "discord" && uri.path == "/auth") {
                android.util.Log.i("MainActivity", "Matching Discord auth redirect. Forwarding to DiscordRpcManager.")
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.onLoginDeepLink(uri)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // Re-apply immersive mode only for TV when window regains focus.
            // Mobile fullscreen is managed per-screen (e.g. player).
            val currentDeviceType = detectDeviceType(this)
            if (currentDeviceType == DeviceType.TV) {
                WindowInsetsControllerCompat(window, window.decorView).apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                }
            }
        }
    }

    override fun onDestroy() {
        jankStats?.isTrackingEnabled = false
        jankStats = null
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(PARTNER_LINK_STATE_SAVED, true)
        outState.putString(PARTNER_LINK_URI, pendingPartnerOpenLink?.request?.canonicalUri())
        outState.putString(PARTNER_LINK_TICKET, pendingPartnerOpenLink?.ticket)
        super.onSaveInstanceState(outState)
    }
}

private const val PARTNER_LINK_STATE_SAVED = "partner_link_state_saved"
private const val PARTNER_LINK_URI = "partner_link_uri"
private const val PARTNER_LINK_TICKET = "partner_link_ticket"

private fun parsePartnerOpenLink(intent: android.content.Intent?): PartnerOpenRequest? {
    if (intent?.action != android.content.Intent.ACTION_VIEW) return null
    return PartnerOpenLinkParser.parse(intent.data?.toString())
}

private fun MainActivity.parseLauncherRequest(intent: android.content.Intent?): LauncherContinueWatchingRequest? {
    return intent?.data?.toLauncherContinueWatchingRequest()
}

private fun MainActivity.parseInstallPackUrl(intent: android.content.Intent?): String? {
    val data = intent?.data ?: return null
    val scheme = data.scheme ?: return null
    val host = data.host ?: return null
    return if (scheme == "arvio" && host == "install-pack") {
        data.getQueryParameter("url")
    } else if ((scheme == "http" || scheme == "https") && host == "arvio.app" && data.path?.startsWith("/install-pack") == true) {
        data.getQueryParameter("url")
    } else {
        null
    }
}

/** The install link behind an addon website's "Install" button (stremio://host/.../manifest.json). */
private fun MainActivity.parseInstallAddonUrl(intent: android.content.Intent?): String? {
    val data = intent?.data ?: return null
    return if (data.scheme.equals("stremio", ignoreCase = true)) data.toString() else null
}

private fun ComponentActivity.runAfterFirstDraw(block: () -> Unit) {
    val content = window.decorView
    content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            content.viewTreeObserver.removeOnPreDrawListener(this)
            content.post { block() }
            return true
        }
    })
}

/**
 * Simple ARVIO loading screen - app logo + spinner
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ArvioLoadingScreen() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val reveal = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        reveal.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 920, easing = FastOutSlowInEasing)
        )
    }

    val sweep by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1550, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweep"
    )

    val logoAlpha by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(color = Color.Black)

            val progress = reveal.value
            val logoCenterY = center.y - 8.dp.toPx()
            val baselineY = logoCenterY + 138.dp.toPx()

            val halfWidth = 180.dp.toPx() * progress
            val lineStartX = center.x - halfWidth
            val lineEndX = center.x + halfWidth
            drawLine(
                color = Color(0xFF00F0D0).copy(alpha = 0.32f * progress),
                start = Offset(lineStartX, baselineY),
                end = Offset(lineEndX, baselineY),
                strokeWidth = 1.6.dp.toPx(),
                cap = StrokeCap.Round
            )

            val sweepHalfWidth = 34.dp.toPx()
            val sweepTravel = (halfWidth - sweepHalfWidth).coerceAtLeast(0f)
            val sweepX = center.x + (sweep * sweepTravel)
            drawLine(
                color = Color.White.copy(alpha = 0.54f * progress),
                start = Offset(sweepX - sweepHalfWidth, baselineY),
                end = Offset(sweepX + sweepHalfWidth, baselineY),
                strokeWidth = 1.2.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        Image(
            painter = painterResource(id = R.drawable.arvio_loading_logo),
            contentDescription = "ARVIO",
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(0.52f)
                .widthIn(max = 320.dp)
                .graphicsLayer {
                    alpha = reveal.value * logoAlpha
                    val scale = 0.88f + (0.12f * reveal.value)
                    scaleX = scale
                    scaleY = scale
                    translationY = (1f - reveal.value) * 18.dp.toPx()
                },
            contentScale = ContentScale.Fit,
        )
    }
}

/**
 * Root composable for the ARVIO app
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ArflixApp(
    authRepository: AuthRepository,
    profileRepository: ProfileRepository,
    traktRepository: TraktRepository,
    profileManager: ProfileManager,
    watchHistoryRepository: WatchHistoryRepository,
    watchlistRepository: WatchlistRepository,
    iptvRepository: com.arflix.tv.data.repository.IptvRepository,
    launcherContinueWatchingRepository: LauncherContinueWatchingRepository,
    oledBlackBackground: Boolean = false,
    skipProfileSelection: Boolean? = null,
    pendingLauncherRequest: LauncherContinueWatchingRequest? = null,
    onConsumeLauncherRequest: () -> Unit = {},
    pendingInstallPackUrl: String? = null,
    onConsumeInstallPackUrl: () -> Unit = {},
    pendingInstallAddonUrl: String? = null,
    onConsumeInstallAddonUrl: () -> Unit = {},
    pendingPartnerOpenLink: PendingPartnerOpenLink? = null,
    resolvePartnerOpenLink: suspend (PartnerOpenRequest) -> PartnerOpenTarget? = { null },
    onConsumePartnerOpenLink: (PendingPartnerOpenLink) -> Boolean = { false },
    preloadedCategories: List<com.arflix.tv.data.model.Category> = emptyList(),
    preloadedHeroItem: com.arflix.tv.data.model.MediaItem? = null,
    preloadedHeroLogoUrl: String? = null,
    preloadedLogoCache: Map<String, String> = emptyMap(),
    onExitApp: () -> Unit = {}
) {
    val context = LocalContext.current
    val authState by authRepository.authState.collectAsStateWithLifecycle()
    val activeProfileState by remember(profileRepository) {
        profileRepository.activeProfile.map { profile ->
            ActiveProfileLoadState.Loaded(profile) as ActiveProfileLoadState
        }
    }.collectAsStateWithLifecycle(initialValue = ActiveProfileLoadState.Loading)
    val activeProfile = (activeProfileState as? ActiveProfileLoadState.Loaded)?.profile
    val startupReady = skipProfileSelection != null &&
        activeProfileState is ActiveProfileLoadState.Loaded &&
        authState !is AuthState.Loading

    // Render content as soon as auth/profile/selection state resolves. The
    // previous fixed 1350ms minimum splash delay added over a second to every
    // fast cold start; the loading screen still shows while data is loading.
    if (!startupReady) {
        ArvioLoadingScreen()
        return
    }

    val navController = rememberNavController()
    val appCoroutineScope = androidx.compose.runtime.rememberCoroutineScope()
    var lastAddonsSyncKey by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(authState, activeProfile?.id) {
        if (authState is AuthState.NotAuthenticated) {
            lastAddonsSyncKey = null
        }
        if (activeProfile != null) {
            launcherContinueWatchingRepository.refreshForCurrentProfile()
        } else {
            launcherContinueWatchingRepository.clearPublishedPrograms()
        }
    }

    var selectedSessionProfileId by rememberSaveable {
        mutableStateOf(ProfileSessionGate.initialProfileId(skipProfileSelection == true, activeProfile))
    }
    val startDestination = rememberSaveable {
        if (selectedSessionProfileId != null) Screen.Home.route else Screen.ProfileSelection.route
    }
    val canOpenPendingLink = ProfileSessionGate.canOpenLink(activeProfile?.id, selectedSessionProfileId)

    val deviceType = LocalDeviceType.current
    val isMobile = deviceType.isTouchDevice()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val partnerLifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current.lifecycle
    val partnerLifecycleState by partnerLifecycle.currentStateFlow.collectAsState()
    val canResolvePartnerLink = canOpenPendingLink && authState !is AuthState.Loading &&
        currentRoute != null && currentRoute != Screen.Login.route && currentRoute != Screen.ProfileSelection.route &&
        partnerLifecycleState.isAtLeast(androidx.lifecycle.Lifecycle.State.RESUMED)
    var partnerRetry by remember(pendingPartnerOpenLink?.ticket) { mutableStateOf(0) }
    var partnerLinkFailed by remember(pendingPartnerOpenLink?.ticket) { mutableStateOf(false) }
    var iptvFullscreen by remember { mutableStateOf(false) }
    // A fullscreen overlay inside a screen (the trailer modal) is not a route,
    // so it cannot be read off the back stack. Without this the bottom bar keeps
    // its height reserved and the video is drawn smaller than the screen.
    var overlayFullscreen by remember { mutableStateOf(false) }
    var isSettingsSubPage by remember { mutableStateOf(false) }
    var isTvSubScreen by remember { mutableStateOf(false) }
    LaunchedEffect(currentRoute) {
        if (currentRoute?.startsWith("tv") != true) {
            iptvFullscreen = false
            isTvSubScreen = false
        }
        if (currentRoute?.startsWith("settings") != true) {
            isSettingsSubPage = false
        }
    }
    // Hide bottom bar on player, profile selection, login, and all subscreens.
    // Bottom bar is only shown on main screens: Home, Search, Watchlist, TV guide, and main Settings.
    val isPlayerScreen = currentRoute?.startsWith("player") == true
    val isFullscreenRoute = isPlayerScreen || iptvFullscreen || overlayFullscreen
    val showBottomBar = shouldShowBottomBar(
        isMobile = isMobile,
        currentRoute = currentRoute,
        isFullscreenRoute = isFullscreenRoute,
        isSettingsSubPage = isSettingsSubPage,
        isTvSubScreen = isTvSubScreen
    )
    val applySystemBarsPadding = isMobile && !isFullscreenRoute

    val isPlayerRoute = iptvFullscreen || overlayFullscreen || currentRoute?.contains("player") == true

    val hostActivity = remember(context) { context.findActivity() }
    LaunchedEffect(isPlayerRoute, isMobile) {
        if (isMobile && !isPlayerRoute) {
            val win = hostActivity?.window ?: (context as? ComponentActivity)?.window
            if (win != null) {
                @Suppress("DEPRECATION")
                win.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    win.isNavigationBarContrastEnforced = false
                    win.isStatusBarContrastEnforced = false
                }
                win.navigationBarColor = android.graphics.Color.TRANSPARENT
                WindowInsetsControllerCompat(win, win.decorView).apply {
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                    show(WindowInsetsCompat.Type.systemBars())
                    isAppearanceLightStatusBars = false
                    isAppearanceLightNavigationBars = false
                }
            }
        }
    }

    val density = LocalDensity.current
    val barSpec = currentBottomBarSpec()
    val navigationInset = with(density) { WindowInsets.navigationBars.getBottom(this).toDp() }
    var measuredBarHeight by remember(barSpec, density.fontScale) { mutableStateOf(0.dp) }
    var measuredBarHeightPx by remember { mutableFloatStateOf(0f) }
    var bottomBarOffsetPx by remember { mutableFloatStateOf(0f) }
    var settleJob by remember { mutableStateOf<kotlinx.coroutines.Job?>(null) }
    val hazeState = remember { HazeState() }

    val isScrollAwayRoute = isMobile && showBottomBar

    val mainScreenBottomBarOffsets = remember { mutableMapOf<String, Float>() }
    val currentMainRoute = remember(currentRoute) {
        when (currentRoute?.substringBefore('?')) {
            Screen.Home.route, Screen.Search.route, Screen.Watchlist.route, "tv", "settings" ->
                currentRoute.substringBefore('?')
            else -> null
        }
    }


    // Restore the bottom bar's state when returning to a main screen from a subpage or subscreen
    LaunchedEffect(currentRoute, isSettingsSubPage, isTvSubScreen, showBottomBar) {
        settleJob?.cancel()
        settleJob = null
        if (showBottomBar && currentMainRoute != null) {
            val saved = mainScreenBottomBarOffsets[currentMainRoute] ?: 0f
            bottomBarOffsetPx = if (measuredBarHeightPx > 0f) {
                if (saved > measuredBarHeightPx * 0.5f) measuredBarHeightPx else 0f
            } else saved
        }
    }

    val barInset = if (showBottomBar) navigationInset else 0.dp
    val fullBarHeight = if (showBottomBar) {
        maxOf(measuredBarHeight, (barSpec.itemHeightDp ?: 52).dp + navigationInset)
    } else 0.dp

    val nestedScrollConnection = remember(measuredBarHeightPx, currentMainRoute, showBottomBar) {
        object : NestedScrollConnection {
            private fun animateToOffset(target: Float, durationMs: Int = 220) {
                settleJob?.cancel()
                settleJob = appCoroutineScope.launch {
                    androidx.compose.animation.core.animate(
                        initialValue = bottomBarOffsetPx,
                        targetValue = target,
                        animationSpec = androidx.compose.animation.core.tween(
                            durationMillis = durationMs,
                            easing = androidx.compose.animation.core.FastOutSlowInEasing
                        )
                    ) { value, _ ->
                        bottomBarOffsetPx = value
                        if (showBottomBar && currentMainRoute != null) {
                            mainScreenBottomBarOffsets[currentMainRoute] = value
                        }
                    }
                }
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val maxOffset = measuredBarHeightPx
                if (maxOffset <= 0f) return Offset.Zero

                // When dragging downward (swiping down, available.y > 0) to scroll back up,
                // bring the bottom bar back immediately if it is partially or fully hidden.
                if (source == NestedScrollSource.Drag && available.y > 0f && bottomBarOffsetPx > 0f) {
                    settleJob?.cancel()
                    settleJob = null
                    val newOffset = (bottomBarOffsetPx - available.y).coerceIn(0f, maxOffset)
                    bottomBarOffsetPx = newOffset
                    if (showBottomBar && currentMainRoute != null) {
                        mainScreenBottomBarOffsets[currentMainRoute] = newOffset
                    }
                }

                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val maxOffset = measuredBarHeightPx
                if (maxOffset <= 0f) return Offset.Zero

                // When dragging upward (swiping up, consumed.y < 0) and the child ACTUALLY scrolled,
                // hide the bottom bar. If child content has nowhere to scroll (e.g. short content),
                // consumed.y is 0, so the bar stays docked at 0f!
                if (source == NestedScrollSource.Drag && consumed.y < 0f) {
                    settleJob?.cancel()
                    settleJob = null
                    val newOffset = (bottomBarOffsetPx - consumed.y).coerceIn(0f, maxOffset)
                    bottomBarOffsetPx = newOffset
                    if (showBottomBar && currentMainRoute != null) {
                        mainScreenBottomBarOffsets[currentMainRoute] = newOffset
                    }
                }

                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                val maxOffset = measuredBarHeightPx
                if (maxOffset <= 0f) return Velocity.Zero

                // If the bottom bar never moved (e.g. short/static content), do NOT fling to hide it!
                if (bottomBarOffsetPx <= 0f) return Velocity.Zero

                val halfThreshold = maxOffset * 0.5f
                val targetOffset = when {
                    available.y < -150f -> maxOffset
                    available.y > 150f -> 0f
                    bottomBarOffsetPx > halfThreshold -> maxOffset
                    else -> 0f
                }

                animateToOffset(targetOffset, 200)
                return Velocity.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            // Background fills edge-to-edge (including behind transparent bars).
            .background(
                brush = if (oledBlackBackground) {
                    Brush.linearGradient(colors = listOf(Color.Black, Color.Black))
                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            appBackgroundDark(),
                            appBackgroundDark(),
                            appBackgroundDark()
                        )
                    )
                }
            )
            // Mobile navigation overlays scrollable content; other screens reserve its height.
            // Player screens remain completely stable edge-to-edge without jumping when
            // transient system bars appear or disappear.
            .then(when {
                isMobile && !isFullscreenRoute -> Modifier.windowInsetsPadding(
                    mobileContentInsets(WindowInsets.systemBars, showBottomBar)
                )
                applySystemBarsPadding -> Modifier.systemBarsPadding()
                else -> Modifier
            })
    ) {
        CompositionLocalProvider(
            LocalBottomBarInset provides if (showBottomBar) barInset else 0.dp,
            LocalBottomBarHeight provides fullBarHeight
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .then(if (isMobile) Modifier.haze(hazeState) else Modifier)
                    .then(if (isScrollAwayRoute) Modifier.nestedScroll(nestedScrollConnection) else Modifier)
            ) {
                AppNavigation(
                    navController = navController,
                    startDestination = startDestination,
                    preloadedCategories = preloadedCategories,
                    preloadedHeroItem = preloadedHeroItem,
                    preloadedHeroLogoUrl = preloadedHeroLogoUrl,
                    preloadedLogoCache = preloadedLogoCache,
                    currentProfile = activeProfile,
                    isCloudConnected = authState is AuthState.Authenticated,
                    onProfileSelected = { profileId -> selectedSessionProfileId = profileId },
                    onSwitchProfile = {
                        selectedSessionProfileId = null
                        appCoroutineScope.launch {
                            traktRepository.clearAllProfileCaches()
                            watchHistoryRepository.clearProfileCaches()
                            watchlistRepository.clearWatchlistCache()
                            iptvRepository.invalidateCache()
                            profileManager.setCurrentProfileId("default")
                            profileManager.setCurrentProfileName("default")
                            profileRepository.clearActiveProfile()
                        }
                    },
                    onTvFullscreenChanged = { fullscreen ->
                        iptvFullscreen = fullscreen
                    },
                    onOverlayFullscreenChanged = { fullscreen ->
                        overlayFullscreen = fullscreen
                    },
                    onTvSubScreenChanged = { isSubScreen ->
                        isTvSubScreen = isSubScreen
                    },
                    onSettingsSubPageChanged = { isSubPage ->
                        isSettingsSubPage = isSubPage
                    },
                    onExitApp = onExitApp
                )
            }

        }

        androidx.compose.animation.AnimatedVisibility(
            visible = showBottomBar,
            enter = androidx.compose.animation.slideInVertically(
                animationSpec = androidx.compose.animation.core.tween(220, easing = androidx.compose.animation.core.FastOutSlowInEasing)
            ) { it },
            exit = androidx.compose.animation.slideOutVertically(
                animationSpec = androidx.compose.animation.core.tween(220, easing = androidx.compose.animation.core.FastOutSlowInEasing)
            ) { it },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AppBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (route == currentRoute) {
                        return@AppBottomBar
                    }
                    mainScreenBottomBarOffsets[route] = 0f
                    bottomBarOffsetPx = 0f
                    navController.navigate(route) {
                        popUpTo("home") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                hazeState = hazeState,
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged {
                        measuredBarHeight = with(density) { it.height.toDp() }
                        measuredBarHeightPx = it.height.toFloat()
                    }
                    .graphicsLayer {
                        translationY = bottomBarOffsetPx
                    }
            )
        }
    }

    LaunchedEffect(activeProfile?.id, canOpenPendingLink, pendingLauncherRequest) {
        val request = pendingLauncherRequest ?: return@LaunchedEffect
        if (!canOpenPendingLink) return@LaunchedEffect

        val route = Screen.Details.createRoute(
            mediaType = request.mediaType,
            mediaId = request.mediaId,
            initialSeason = request.season,
            initialEpisode = request.episode
        )
        navController.navigate(route) {
            popUpTo(Screen.ProfileSelection.route) { inclusive = true }
            launchSingleTop = true
        }
        onConsumeLauncherRequest()
    }

    LaunchedEffect(activeProfile?.id, canOpenPendingLink, pendingInstallPackUrl) {
        val packUrl = pendingInstallPackUrl ?: return@LaunchedEffect
        if (!canOpenPendingLink) return@LaunchedEffect

        val encodedUrl = java.net.URLEncoder.encode(packUrl, "UTF-8")
        val route = "settings?initialSection=catalogs&installPackUrl=$encodedUrl"
        navController.navigate(route) {
            popUpTo(Screen.ProfileSelection.route) { inclusive = true }
            launchSingleTop = true
        }
        onConsumeInstallPackUrl()
    }

    LaunchedEffect(activeProfile?.id, canOpenPendingLink, pendingInstallAddonUrl) {
        val addonUrl = pendingInstallAddonUrl ?: return@LaunchedEffect
        if (!canOpenPendingLink) return@LaunchedEffect

        val encodedUrl = java.net.URLEncoder.encode(addonUrl, "UTF-8")
        val route = "settings?initialSection=stremio&installAddonUrl=$encodedUrl"
        navController.navigate(route) {
            popUpTo(Screen.ProfileSelection.route) { inclusive = true }
            launchSingleTop = true
        }
        onConsumeInstallAddonUrl()
    }

    LaunchedEffect(activeProfile?.id, canResolvePartnerLink, pendingPartnerOpenLink?.ticket, partnerRetry) {
        val link = pendingPartnerOpenLink ?: return@LaunchedEffect
        if (!canResolvePartnerLink) return@LaunchedEffect
        val profileId = activeProfile?.id ?: return@LaunchedEffect
        partnerLinkFailed = false
        val target = try {
            kotlinx.coroutines.withTimeout(15_000L) { resolvePartnerOpenLink(link.request) }
        } catch (_: kotlinx.coroutines.TimeoutCancellationException) {
            null
        } catch (cancelled: kotlinx.coroutines.CancellationException) {
            throw cancelled
        } catch (_: Exception) {
            // No URI, token or API-key-bearing network exception is logged.
            null
        }
        kotlinx.coroutines.currentCoroutineContext().ensureActive()
        // State can change before Compose cancels this effect. Recheck the profile session and
        // atomically consume the ticket in the Activity before navigating to the title details.
        if (!ProfileSessionGate.canOpenLink(activeProfile?.id, selectedSessionProfileId) ||
            activeProfile?.id != profileId || authState is AuthState.Loading ||
            navController.currentDestination?.route in listOf(Screen.Login.route, Screen.ProfileSelection.route) ||
            !partnerLifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.RESUMED)) {
            return@LaunchedEffect
        }
        if (target == null) {
            partnerLinkFailed = true
        } else if (onConsumePartnerOpenLink(link)) {
            navController.navigate(
                Screen.Details.createRoute(target.mediaType, target.tmdbId, target.season, target.episode),
                partnerOpenNavigationOptions()
            )
        }
    }

    if (pendingPartnerOpenLink != null && canResolvePartnerLink) {
        com.arflix.tv.ui.components.PartnerOpenLinkDialog(
            failed = partnerLinkFailed,
            onRetry = { partnerLinkFailed = false; partnerRetry += 1 },
            onCancel = { pendingPartnerOpenLink?.let { onConsumePartnerOpenLink(it) } }
        )
    }
}

private fun enqueueFullTraktSync(context: android.content.Context) {
    val request = OneTimeWorkRequestBuilder<TraktSyncWorker>()
        .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
        .setInputData(
            workDataOf(TraktSyncWorker.INPUT_SYNC_MODE to TraktSyncWorker.SYNC_MODE_FULL)
        )
        .addTag(TraktSyncWorker.TAG)
        .build()

    WorkManager.getInstance(context).enqueueUniqueWork(
        "trakt_sync_after_auth",
        ExistingWorkPolicy.REPLACE,
        request
    )
}
