package com.arflix.tv

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
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
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.content.pm.ActivityInfo
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
import com.arflix.tv.ui.components.AppBottomBar
import com.arflix.tv.ui.startup.StartupViewModel
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.ui.theme.appBackgroundDark
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
import com.arflix.tv.util.findActivity
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.worker.TraktSyncWorker
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.PerformanceMetricsState
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private sealed interface ActiveProfileLoadState {
    data object Loading : ActiveProfileLoadState
    data class Loaded(val profile: com.arflix.tv.data.model.Profile?) : ActiveProfileLoadState
}

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

    @Inject
    lateinit var iptvRepository: Lazy<com.arflix.tv.data.repository.IptvRepository>

    private var jankStats: JankStats? = null
    private var pendingLauncherRequest by mutableStateOf<LauncherContinueWatchingRequest?>(null)
    private var pendingInstallPackUrl by mutableStateOf<String?>(null)

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
        installSplashScreen()

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

        com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.init(this)
        intent?.data?.let { uri ->
            android.util.Log.d("MainActivity", "Received intent data URI in onCreate: $uri")
            if (uri.scheme == "arvio" && uri.host == "discord" && uri.path == "/auth") {
                android.util.Log.i("MainActivity", "Matching Discord auth redirect. Forwarding to DiscordRpcManager.")
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.onLoginDeepLink(uri)
            }
        }

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
            @Suppress("DEPRECATION")
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        lifecycleScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            runCatching { iptvRepository.get().warmupFromCacheOnly() }
        }

        setContent {
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
                mediaRepository.get().contentLanguage = appLanguage
            }
            val deviceType = when (deviceModeOverride) {
                "tv" -> DeviceType.TV
                "tablet" -> DeviceType.TABLET
                "phone" -> DeviceType.PHONE
                else -> initialDeviceType
            }
            val hasTouchScreen = remember { deviceHasTouchScreen(this@MainActivity) }
            val effectiveDeviceType = if (!hasTouchScreen && deviceType != DeviceType.TV) DeviceType.TV else deviceType
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
        pendingInstallPackUrl = parseInstallPackUrl(intent)
        intent.data?.let { uri ->
            android.util.Log.d("MainActivity", "Received intent data URI in onNewIntent: $uri")
            if (uri.scheme == "arvio" && uri.host == "discord" && uri.path == "/auth") {
                android.util.Log.i("MainActivity", "Matching Discord auth redirect. Forwarding to DiscordRpcManager.")
                com.arflix.tv.ui.screens.details.discord.DiscordRpcManager.onLoginDeepLink(uri)
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
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

    LaunchedEffect(authState, activeProfile?.id) {
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

    val showBottomBar = isMobile && activeProfile != null &&
            currentRoute != null &&
            !iptvFullscreen &&
            !currentRoute.contains("player") &&
            !currentRoute.contains("profile") &&
            !currentRoute.contains("login")

    val isPlayerRoute = iptvFullscreen || currentRoute?.contains("player") == true

    val hostActivity = remember(context) { context.findActivity() }
    LaunchedEffect(isPlayerRoute, isMobile) {
        if (isMobile && !isPlayerRoute) {
            val win = hostActivity?.window ?: (context as? ComponentActivity)?.window
            if (win != null) {
                @Suppress("DEPRECATION")
                win.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                WindowInsetsControllerCompat(win, win.decorView).apply {
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                    show(WindowInsetsCompat.Type.systemBars())
                    isAppearanceLightStatusBars = false
                    isAppearanceLightNavigationBars = false
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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

        if (isMobile && !isPlayerRoute) {
            val bottomBarAlpha by animateFloatAsState(
                targetValue = if (showBottomBar) 1f else 0f,
                animationSpec = tween(250),
                label = "bottom_bar_alpha"
            )

            AppBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (showBottomBar) {
                        navController.navigate(route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .padding(horizontal = 24.dp, vertical = 14.dp)
                    .graphicsLayer {
                        alpha = bottomBarAlpha
                    }
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

    LaunchedEffect(activeProfile?.id, pendingInstallPackUrl) {
        val packUrl = pendingInstallPackUrl ?: return@LaunchedEffect
        if (activeProfile == null) return@LaunchedEffect

        val encodedUrl = java.net.URLEncoder.encode(packUrl, "UTF-8")
        val route = "settings?initialSection=catalogs&installPackUrl=$encodedUrl"
        navController.navigate(route) {
            popUpTo(Screen.ProfileSelection.route) { inclusive = true }
            launchSingleTop = true
        }
        onConsumeInstallPackUrl()
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