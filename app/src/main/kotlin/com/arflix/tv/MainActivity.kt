package com.arflix.tv

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.arflix.tv.R
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.arflix.tv.util.LAST_APP_LANGUAGE_KEY
import com.arflix.tv.util.detectDeviceType
import com.arflix.tv.util.deviceHasTouchScreen
import com.arflix.tv.util.settingsDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.delay
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

    // Prefetch IPTV early so the TV screen opens without a loading stall.
    // IptvRepository is @Singleton; touching it at activity start warms the
    // in-memory snapshot (and will trigger a disk-cache read + silent
    // background refresh) so by the time the user navigates into the TV tab
    // everything is already resident.
    @Inject
    lateinit var iptvRepository: Lazy<com.arflix.tv.data.repository.IptvRepository>

    private var jankStats: JankStats? = null
    private var pendingLauncherRequest by mutableStateOf<LauncherContinueWatchingRequest?>(null)

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

        // Set orientation based on device type
        requestedOrientation = when (initialDeviceType) {
            DeviceType.TV -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            DeviceType.TABLET -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
            DeviceType.PHONE -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        }

        // All devices use edge-to-edge (setDecorFitsSystemWindows=false).
        // TV hides the bars; mobile keeps them visible and Compose handles
        // insets via systemBarsPadding() in the root layout.
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (initialDeviceType == DeviceType.TV) {
            WindowInsetsControllerCompat(window, window.decorView).apply {
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(WindowInsetsCompat.Type.systemBars())
            }
        } else {
            // Clear any FLAG_FULLSCREEN the Leanback theme may have set
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            // Transparent bars — the dark app background shows through them.
            // White (light) icons are used since the background is dark.
            @Suppress("DEPRECATION")
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            @Suppress("DEPRECATION")
            window.navigationBarColor = android.graphics.Color.TRANSPARENT
            WindowInsetsControllerCompat(window, window.decorView).apply {
                show(WindowInsetsCompat.Type.systemBars())
                isAppearanceLightStatusBars = false      // white icons on dark bg
                isAppearanceLightNavigationBars = false  // white icons on dark bg
            }
        }

        lifecycleScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            runCatching { iptvRepository.get().warmupFromCacheOnly() }
        }

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
            val appLanguage by remember(activeProfileId) {
                this@MainActivity.settingsDataStore.data.map { prefs ->
                    val fallbackLanguage = prefs[LAST_APP_LANGUAGE_KEY] ?: "en-US"
                    val profileId = activeProfileId
                    if (profileId.isNullOrBlank()) {
                        fallbackLanguage
                    } else {
                        prefs[stringPreferencesKey("profile_${profileId}_content_language")] ?: fallbackLanguage
                    }
                }
            }.collectAsStateWithLifecycle(initialValue = "en-US")
            LaunchedEffect(appLanguage) {
                mediaRepository.get().contentLanguage = if (appLanguage == "en-US") null else appLanguage
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
                runCatching { repo.warmupFromCacheOnly() }
                kotlinx.coroutines.delay(60_000L)
                runCatching { repo.prefetchFreshStartupData() }
            }
        }
    }

    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        pendingLauncherRequest = parseLauncherRequest(intent)
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
}

private fun MainActivity.parseLauncherRequest(intent: android.content.Intent?): LauncherContinueWatchingRequest? {
    return intent?.data?.toLauncherContinueWatchingRequest()
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
    var startupIntroComplete by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(1350)
        startupIntroComplete = true
    }
    val activeProfile = (activeProfileState as? ActiveProfileLoadState.Loaded)?.profile
    val startupReady = skipProfileSelection != null &&
        activeProfileState is ActiveProfileLoadState.Loaded &&
        authState !is AuthState.Loading

    if (!startupReady || !startupIntroComplete) {
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

    val startDestination = if (skipProfileSelection == true && activeProfile != null) {
        Screen.Home.route
    } else {
        Screen.ProfileSelection.route
    }

    val deviceType = LocalDeviceType.current
    val isMobile = deviceType.isTouchDevice()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    var iptvFullscreen by remember { mutableStateOf(false) }
    LaunchedEffect(currentRoute) {
        if (currentRoute?.startsWith("tv") != true) {
            iptvFullscreen = false
        }
    }
    // Hide bottom bar on player, profile selection, and login screens.
    // TV route shows the bottom bar on mobile (touch devices) for easy navigation;
    // the fullscreen IPTV player uses BackHandler to return to the guide.
    val showBottomBar = isMobile && activeProfile != null &&
        currentRoute != null &&
        !iptvFullscreen &&
        !currentRoute.contains("player") &&
        !currentRoute.contains("profile") &&
        !currentRoute.contains("login")

    Column(
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
            // On mobile, push content between the status bar and navigation bar.
            // Applied AFTER background so the gradient fills behind the bars.
            // systemBarsPadding() reads live WindowInsets, so it automatically
            // becomes 0 when the player hides the bars.
            .then(if (isMobile) Modifier.systemBarsPadding() else Modifier)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            AppNavigation(
                navController = navController,
                startDestination = startDestination,
                preloadedCategories = preloadedCategories,
                preloadedHeroItem = preloadedHeroItem,
                preloadedHeroLogoUrl = preloadedHeroLogoUrl,
                preloadedLogoCache = preloadedLogoCache,
                currentProfile = activeProfile,
                isCloudConnected = authState is AuthState.Authenticated,
                onSwitchProfile = {
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
                onExitApp = onExitApp
            )
        }
        if (showBottomBar) {
            AppBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo("home") { inclusive = false }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    LaunchedEffect(activeProfile?.id, pendingLauncherRequest) {
        val request = pendingLauncherRequest ?: return@LaunchedEffect
        if (activeProfile == null) return@LaunchedEffect

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
