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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arflix.tv.ui.components.AppBottomBar
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.content.pm.ActivityInfo
import android.util.Log
import com.arflix.tv.util.DeviceType
import com.arflix.tv.util.DEVICE_MODE_OVERRIDE_KEY
import com.arflix.tv.util.OLED_BLACK_BACKGROUND_KEY
import com.arflix.tv.util.ACCENT_COLOR_KEY
import com.arflix.tv.util.AppContentPreferences
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.LocalHasTouchScreen
import com.arflix.tv.util.LocalAppLanguage
import com.arflix.tv.util.LAST_APP_LANGUAGE_KEY
import com.arflix.tv.util.detectDeviceType
import com.arflix.tv.util.deviceHasTouchScreen
import com.arflix.tv.util.settingsDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository
import com.arflix.tv.data.repository.LauncherContinueWatchingRequest
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.toLauncherContinueWatchingRequest
import com.arflix.tv.navigation.AppNavigation
import com.arflix.tv.navigation.Screen
import com.arflix.tv.ui.startup.StartupViewModel
import com.arflix.tv.ui.theme.ArflixTvTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.arflix.tv.ui.performance.performanceScreenName
import com.arflix.tv.ui.performance.shouldTrackJankStats
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.worker.TraktSyncWorker
import dagger.hilt.android.AndroidEntryPoint
import dagger.Lazy
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
    lateinit var profileRepository: Lazy<ProfileRepository>

    @Inject
    lateinit var launcherContinueWatchingRepository: Lazy<LauncherContinueWatchingRepository>

    @Inject
    lateinit var mediaRepository: Lazy<MediaRepository>

    @Inject
    lateinit var streamRepository: Lazy<StreamRepository>

    private var jankStats: JankStats? = null
    @Volatile
    private var currentJankScreen: String = "main"
    private var pendingLauncherRequest by mutableStateOf<LauncherContinueWatchingRequest?>(null)
    private var pendingDebugRoute by mutableStateOf<String?>(null)

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
        pendingDebugRoute = parseDebugRoute(intent)
        installDebugAddonIfRequested(intent)

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

        setContent {
            // Observe device mode override changes live from DataStore
            val deviceModeOverride by remember {
                this@MainActivity.settingsDataStore.data.map { it[DEVICE_MODE_OVERRIDE_KEY] }
            }.collectAsStateWithLifecycle(initialValue = null)
            var skipProfileSelection by remember { mutableStateOf(true) }
            LaunchedEffect(Unit) {
                val profiles = profileRepository.get()
                val activeProfile = profiles.getActiveProfile()
                if (activeProfile == null) {
                    val fallbackProfile = profiles.getProfiles().maxByOrNull { it.lastUsedAt }
                        ?: profiles.createDefaultProfileIfNeeded()
                    if (fallbackProfile != null) {
                        profiles.setActiveProfile(fallbackProfile.id)
                    }
                }
                skipProfileSelection = true
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
                    val fallbackLanguage = prefs[LAST_APP_LANGUAGE_KEY]
                        ?: AppContentPreferences.DEFAULT_LANGUAGE_TAG
                    val profileId = activeProfileId
                    if (profileId.isNullOrBlank()) {
                        fallbackLanguage
                    } else {
                        prefs[stringPreferencesKey("profile_${profileId}_content_language")] ?: fallbackLanguage
                    }
                }
            }.collectAsStateWithLifecycle(initialValue = AppContentPreferences.DEFAULT_LANGUAGE_TAG)
            LaunchedEffect(appLanguage) {
                mediaRepository.get().contentLanguage = AppContentPreferences.normalizeLanguageForTmdb(appLanguage)
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
                        profileRepository = profileRepository.get(),
                        launcherContinueWatchingRepository = launcherContinueWatchingRepository.get(),
                        oledBlackBackground = oledBlackBackground,
                        skipProfileSelection = skipProfileSelection,
                        pendingLauncherRequest = pendingLauncherRequest,
                        onConsumeLauncherRequest = { pendingLauncherRequest = null },
                        pendingDebugRoute = pendingDebugRoute,
                        onConsumeDebugRoute = { pendingDebugRoute = null },
                        preloadedCategories = startupState.categories,
                        preloadedHeroItem = startupState.heroItem,
                        preloadedHeroLogoUrl = startupState.heroLogoUrl,
                        preloadedLogoCache = startupState.logoCache,
                        onPerformanceScreenChanged = ::updatePerformanceScreen,
                        onExitApp = { finish() }
                    )
                }
            }
        }

        if (shouldTrackJankStats(BuildConfig.DEBUG, BuildConfig.BUILD_TYPE)) {
            jankStats = JankStats.createAndTrack(window) { frameData ->
                if (frameData.isJank) {
                    val durationMs = frameData.frameDurationUiNanos / 1_000_000
                    Log.w(JANK_TAG, "screen=$currentJankScreen durationMs=$durationMs")
                }
            }
            updatePerformanceScreen("main")
        }

        runAfterFirstDraw {
            ArflixApplication.instance.scheduleTraktSyncIfNeeded()
            ArflixApplication.instance.scheduleStreamingCatalogRefreshIfNeeded()
        }
    }

    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        pendingLauncherRequest = parseLauncherRequest(intent)
        pendingDebugRoute = parseDebugRoute(intent)
        installDebugAddonIfRequested(intent)
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

    private fun installDebugAddonIfRequested(intent: android.content.Intent?) {
        if (!BuildConfig.DEBUG) return
        val addonUrl = intent
            ?.getStringExtra("majo_debug_addon_url")
            ?.trim()
            ?.takeIf { it.isNotBlank() }
            ?: return

        lifecycleScope.launch {
            streamRepository.get().ensureCustomAddons(listOf(addonUrl))
        }
    }

    private fun updatePerformanceScreen(route: String?) {
        val screen = performanceScreenName(route)
        currentJankScreen = screen
        if (!shouldTrackJankStats(BuildConfig.DEBUG, BuildConfig.BUILD_TYPE)) return
        PerformanceMetricsState.getHolderForHierarchy(window.decorView)
            .state
            ?.putState("screen", screen)
    }

    private companion object {
        const val JANK_TAG = "MajoJank"
    }
}

private fun MainActivity.parseLauncherRequest(intent: android.content.Intent?): LauncherContinueWatchingRequest? {
    return intent?.data?.toLauncherContinueWatchingRequest()
}

private fun parseDebugRoute(intent: android.content.Intent?): String? {
    if (!BuildConfig.DEBUG) return null
    val route = intent
        ?.getStringExtra("majo_debug_route")
        ?.trim()
        ?.removePrefix("/")
        ?.takeIf { it.isNotBlank() }
        ?: return null

    return route.takeIf {
        it == Screen.Home.route ||
            it == Screen.Search.route ||
            it == Screen.Discover.route ||
            it == Screen.Watchlist.route ||
            it.startsWith("settings") ||
            it.startsWith("collections/") ||
            it.startsWith("details/") ||
            it.startsWith("player/")
    }
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
 * Majo Stream startup screen.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MajoStreamLoadingScreen() {
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

    val wordmarkAlpha by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "wordmarkAlpha"
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
            val baselineY = logoCenterY + 86.dp.toPx()

            val halfWidth = 210.dp.toPx() * progress
            val lineStartX = center.x - halfWidth
            val lineEndX = center.x + halfWidth
            drawLine(
                color = Color(0xFFFFB000).copy(alpha = 0.54f * progress),
                start = Offset(lineStartX, baselineY),
                end = Offset(lineEndX, baselineY),
                strokeWidth = 1.8.dp.toPx(),
                cap = StrokeCap.Round
            )

            val sweepHalfWidth = 46.dp.toPx()
            val sweepTravel = (halfWidth - sweepHalfWidth).coerceAtLeast(0f)
            val sweepX = center.x + (sweep * sweepTravel)
            drawLine(
                color = Color.White.copy(alpha = 0.72f * progress),
                start = Offset(sweepX - sweepHalfWidth, baselineY),
                end = Offset(sweepX + sweepHalfWidth, baselineY),
                strokeWidth = 1.2.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .graphicsLayer {
                    alpha = reveal.value * wordmarkAlpha
                    val scale = 0.92f + (0.08f * reveal.value)
                    scaleX = scale
                    scaleY = scale
                    translationY = (1f - reveal.value) * 18.dp.toPx()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MAJO",
                fontSize = 52.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 7.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "STREAM",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFB000),
                letterSpacing = 5.sp
            )
        }
    }
}

/**
 * Root composable for the Majo Stream app
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ArflixApp(
    profileRepository: ProfileRepository,
    launcherContinueWatchingRepository: LauncherContinueWatchingRepository,
    oledBlackBackground: Boolean = false,
    skipProfileSelection: Boolean? = null,
    pendingLauncherRequest: LauncherContinueWatchingRequest? = null,
    onConsumeLauncherRequest: () -> Unit = {},
    pendingDebugRoute: String? = null,
    onConsumeDebugRoute: () -> Unit = {},
    preloadedCategories: List<com.arflix.tv.data.model.Category> = emptyList(),
    preloadedHeroItem: com.arflix.tv.data.model.MediaItem? = null,
    preloadedHeroLogoUrl: String? = null,
    preloadedLogoCache: Map<String, String> = emptyMap(),
    onPerformanceScreenChanged: (String?) -> Unit = {},
    onExitApp: () -> Unit = {}
) {
    val activeProfileState by remember(profileRepository) {
        profileRepository.activeProfile.map { profile ->
            ActiveProfileLoadState.Loaded(profile) as ActiveProfileLoadState
        }
    }.collectAsStateWithLifecycle(initialValue = ActiveProfileLoadState.Loading)
    val activeProfile = (activeProfileState as? ActiveProfileLoadState.Loaded)?.profile
        ?: remember {
            com.arflix.tv.data.model.Profile(
                id = "default",
                name = "Profile 1",
                avatarColor = com.arflix.tv.data.model.ProfileColors.colors[0]
            )
        }
    val startupReady = skipProfileSelection == true

    if (!startupReady) {
        MajoStreamLoadingScreen()
        return
    }

    val activeProfileId = activeProfile.id
    val navController = rememberNavController()
    LaunchedEffect(activeProfileId) {
        launcherContinueWatchingRepository.refreshForCurrentProfile()
    }

    val startDestination = Screen.Home.route

    val deviceType = LocalDeviceType.current
    val isMobile = deviceType.isTouchDevice()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    LaunchedEffect(currentRoute) {
        onPerformanceScreenChanged(currentRoute)
    }
    // Hide bottom bar on player, profile selection, and login screens.
    val showBottomBar = isMobile &&
        currentRoute != null &&
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
                currentProfile = null,
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

    LaunchedEffect(activeProfileId, pendingLauncherRequest) {
        val request = pendingLauncherRequest ?: return@LaunchedEffect

        val route = Screen.Details.createRoute(
            mediaType = request.mediaType,
            mediaId = request.mediaId,
            initialSeason = request.season,
            initialEpisode = request.episode
        )
        navController.navigate(route) {
            popUpTo(Screen.Home.route) { inclusive = false }
            launchSingleTop = true
        }
        onConsumeLauncherRequest()
    }

    LaunchedEffect(activeProfileId, pendingDebugRoute) {
        val route = pendingDebugRoute ?: return@LaunchedEffect

        navController.navigate(route) {
            popUpTo(Screen.Home.route) { inclusive = false }
            launchSingleTop = true
        }
        onConsumeDebugRoute()
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
