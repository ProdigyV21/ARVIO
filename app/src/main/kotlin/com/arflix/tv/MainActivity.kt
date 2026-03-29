package com.arflix.tv

import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.arflix.tv.R
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.drawscope.Stroke
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
import com.arflix.tv.util.LocalDeviceType
import com.arflix.tv.util.LocalHasTouchScreen
import com.arflix.tv.util.detectDeviceType
import com.arflix.tv.util.deviceHasTouchScreen
import com.arflix.tv.util.settingsDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
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
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.TraktRepository
import com.arflix.tv.data.repository.toLauncherContinueWatchingRequest
import com.arflix.tv.navigation.AppNavigation
import com.arflix.tv.navigation.Screen
import com.arflix.tv.ui.screens.login.LoginScreen
import com.arflix.tv.ui.startup.StartupViewModel
import com.arflix.tv.ui.theme.ArflixTvTheme
import com.arflix.tv.ui.theme.BackgroundGradientCenter
import com.arflix.tv.ui.theme.BackgroundGradientEnd
import com.arflix.tv.ui.theme.BackgroundGradientStart
import com.arflix.tv.worker.TraktSyncWorker
import dagger.hilt.android.AndroidEntryPoint
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Main Activity - Single activity architecture with Compose Navigation
 * Uses Android 12+ Splash Screen API for instant launch feedback
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private val DNS_RELOAD_NONCE_KEY = longPreferencesKey("dns_reload_nonce")
    }

    @Inject
    lateinit var authRepository: Lazy<AuthRepository>

    @Inject
    lateinit var profileRepository: Lazy<ProfileRepository>

    @Inject
    lateinit var traktRepository: Lazy<TraktRepository>

    @Inject
    lateinit var launcherContinueWatchingRepository: Lazy<LauncherContinueWatchingRepository>

    private var jankStats: JankStats? = null
    private var pendingLauncherRequest by mutableStateOf<LauncherContinueWatchingRequest?>(null)

    // StartupViewModel for parallel loading during splash
    private val startupViewModel: StartupViewModel by viewModels()

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
        pendingLauncherRequest = parseLauncherRequest(intent)

        // Set orientation based on device type
        requestedOrientation = when (initialDeviceType) {
            DeviceType.TV -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            DeviceType.TABLET -> ActivityInfo.SCREEN_ORIENTATION_SENSOR
            DeviceType.PHONE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }

        // Keep screen on during playback
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

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
            val deviceModeOverride by remember { this@MainActivity.settingsDataStore.data.map { it[DEVICE_MODE_OVERRIDE_KEY] } }.collectAsState(initial = null)
            val skipProfileSelection by remember {
                this@MainActivity.settingsDataStore.data.map { it[SKIP_PROFILE_SELECTION_KEY] ?: false }
            }.collectAsState(initial = null as Boolean?)
            val activeProfileLoaded by remember {
                profileRepository.get().activeProfileId.map { true }
            }.collectAsState(initial = false)
            val dnsReloadNonce by remember {
                this@MainActivity.settingsDataStore.data
                    .map { prefs -> prefs[DNS_RELOAD_NONCE_KEY] ?: 0L }
                    .distinctUntilChanged()
            }.collectAsState(initial = 0L)
            var previousDnsReloadNonce by remember { mutableStateOf<Long?>(null) }
            LaunchedEffect(dnsReloadNonce) {
                val previous = previousDnsReloadNonce
                previousDnsReloadNonce = dnsReloadNonce
                if (previous != null && dnsReloadNonce > 0L && dnsReloadNonce != previous) {
                    // Reload only on explicit user DNS change events, never on startup reads.
                    this@MainActivity.recreate()
                }
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
            CompositionLocalProvider(
                LocalDeviceType provides effectiveDeviceType,
                LocalHasTouchScreen provides hasTouchScreen,
                androidx.compose.ui.platform.LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr
            ) {
                ArflixTvTheme {
                    val startupState by startupViewModel.state.collectAsState()
                    ArflixApp(
                        authRepository = authRepository.get(),
                        profileRepository = profileRepository.get(),
                        traktRepository = traktRepository.get(),
                        launcherContinueWatchingRepository = launcherContinueWatchingRepository.get(),
                        skipProfileSelection = skipProfileSelection,
                        activeProfileLoaded = activeProfileLoaded,
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

    // Rotating spinner
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val logoAlpha by infiniteTransition.animateFloat(
        initialValue = 0.72f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0a0a0a)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.arvio_loading_logo),
            contentDescription = "ARVIO",
            modifier = Modifier.padding(horizontal = 24.dp),
            contentScale = ContentScale.Fit,
            alpha = logoAlpha
        )

        Canvas(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 132.dp)
                .size(28.dp)
        ) {
            val strokeWidth = 2.dp.toPx()
            val arcSize = androidx.compose.ui.geometry.Size(
                size.width - strokeWidth,
                size.height - strokeWidth
            )

            drawArc(
                color = Color.White.copy(alpha = 0.12f),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            drawArc(
                color = Color.White.copy(alpha = 0.9f),
                startAngle = rotation,
                sweepAngle = 82f,
                useCenter = false,
                topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
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
    launcherContinueWatchingRepository: LauncherContinueWatchingRepository,
    skipProfileSelection: Boolean? = null,
    activeProfileLoaded: Boolean = false,
    pendingLauncherRequest: LauncherContinueWatchingRequest? = null,
    onConsumeLauncherRequest: () -> Unit = {},
    preloadedCategories: List<com.arflix.tv.data.model.Category> = emptyList(),
    preloadedHeroItem: com.arflix.tv.data.model.MediaItem? = null,
    preloadedHeroLogoUrl: String? = null,
    preloadedLogoCache: Map<String, String> = emptyMap(),
    onExitApp: () -> Unit = {}
) {
    val context = LocalContext.current
    val authState by authRepository.authState.collectAsState()
    val activeProfile by profileRepository.activeProfile.collectAsState(initial = null)
    val startupReady = skipProfileSelection != null &&
        activeProfileLoaded &&
        authState !is AuthState.Loading

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

    // Always show profile selection on startup - user must manually choose a profile
    val startDestination = if (skipProfileSelection == true && activeProfile != null) {
        Screen.Home.route
    } else {
        Screen.ProfileSelection.route
    }

    val deviceType = LocalDeviceType.current
    val isMobile = deviceType.isTouchDevice()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    // Hide bottom bar on player, profile selection, login, and TV screens
    // TV route is hidden because Live TV fullscreen player stays on "tv" route
    val showBottomBar = isMobile && activeProfile != null &&
        currentRoute != null &&
        !currentRoute.contains("player") &&
        !currentRoute.contains("profile") &&
        !currentRoute.contains("login") &&
        !currentRoute.contains("tv")

    Column(
        modifier = Modifier
            .fillMaxSize()
            // Background fills edge-to-edge (including behind transparent bars)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        BackgroundGradientStart,
                        BackgroundGradientCenter,
                        BackgroundGradientEnd
                    )
                )
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
                        profileRepository.clearActiveProfile()
                    }
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
