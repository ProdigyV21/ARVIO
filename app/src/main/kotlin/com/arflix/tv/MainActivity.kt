package com.arflix.tv

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.view.WindowManager
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
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.PerformanceMetricsState
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
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.StreamRepository
import com.arflix.tv.data.repository.TraktRepository
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    @Inject
    lateinit var authRepository: Lazy<AuthRepository>

    @Inject
    lateinit var profileRepository: Lazy<ProfileRepository>

    @Inject
    lateinit var streamRepository: Lazy<StreamRepository>

    @Inject
    lateinit var traktRepository: Lazy<TraktRepository>

    private var jankStats: JankStats? = null

    // StartupViewModel for parallel loading during splash
    private val startupViewModel: StartupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen BEFORE super.onCreate()
        // Don't use setKeepOnScreenCondition - it causes black screen on some TV devices
        // Instead, let the splash dismiss immediately and show our Compose loading screen
        installSplashScreen()

        super.onCreate(savedInstanceState)

        // Keep screen on during playback
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Immersive fullscreen mode
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.systemBars())
        }

        setContent {
            ArflixTvTheme {
                val startupState by startupViewModel.state.collectAsState()

                if (startupState.isReady) {
                    // Data is loaded, show the app
                    ArflixApp(
                        authRepository = authRepository.get(),
                        profileRepository = profileRepository.get(),
                        streamRepository = streamRepository.get(),
                        traktRepository = traktRepository.get(),
                        preloadedCategories = startupState.categories,
                        preloadedHeroItem = startupState.heroItem,
                        preloadedHeroLogoUrl = startupState.heroLogoUrl,
                        preloadedLogoCache = startupState.logoCache,
                        onExitApp = { finish() }
                    )
                } else {
                    // Simple loading screen - ARVIO with white glow + spinner
                    ArvioLoadingScreen()
                }
            }
        }

        if (BuildConfig.DEBUG) {
            jankStats = JankStats.createAndTrack(window) { frameData ->
                if (frameData.isJank) {
                    val durationMs = frameData.frameDurationUiNanos / 1_000_000
                    Log.w("JankStats", "Jank frame: ${durationMs}ms, states=${frameData.states}")
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // Re-apply immersive mode when window regains focus
            WindowInsetsControllerCompat(window, window.decorView).apply {
                hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    override fun onDestroy() {
        jankStats?.isTrackingEnabled = false
        jankStats = null
        super.onDestroy()
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
 * Simple ARVIO loading screen - white glowing text + spinner
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

    // Pulsing glow for text
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0a0a0a)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ARVIO text with white glow
            Text(
                text = "ARVIO",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 8.sp,
                style = androidx.compose.ui.text.TextStyle(
                    shadow = Shadow(
                        color = Color.White.copy(alpha = glowAlpha),
                        offset = Offset.Zero,
                        blurRadius = 30f
                    )
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Simple rotating spinner
            Canvas(modifier = Modifier.size(48.dp)) {
                val strokeWidth = 3.dp.toPx()
                val arcSize = androidx.compose.ui.geometry.Size(
                    size.width - strokeWidth,
                    size.height - strokeWidth
                )

                // Background ring
                drawArc(
                    color = Color.White.copy(alpha = 0.15f),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )

                // Rotating arc
                drawArc(
                    color = Color.White,
                    startAngle = rotation,
                    sweepAngle = 90f,
                    useCenter = false,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }
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
    streamRepository: StreamRepository,
    traktRepository: TraktRepository,
    preloadedCategories: List<com.arflix.tv.data.model.Category> = emptyList(),
    preloadedHeroItem: com.arflix.tv.data.model.MediaItem? = null,
    preloadedHeroLogoUrl: String? = null,
    preloadedLogoCache: Map<String, String> = emptyMap(),
    onExitApp: () -> Unit = {}
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val authState by authRepository.authState.collectAsState()
    val activeProfile by profileRepository.activeProfile.collectAsState(initial = null)
    var addonsSynced by remember { mutableStateOf(false) }

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated && !addonsSynced) {
            withContext(Dispatchers.IO) {
                streamRepository.syncAddonsFromCloud()
            }
            addonsSynced = true
        } else if (authState is AuthState.NotAuthenticated) {
            addonsSynced = false
        }
    }

    // Always show profile selection on startup - user must manually choose a profile
    val startDestination = Screen.ProfileSelection.route

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        BackgroundGradientStart,
                        BackgroundGradientCenter,
                        BackgroundGradientEnd
                    )
                )
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
            onSwitchProfile = {
                // Clear active profile when switching
                MainScope().launch {
                    profileRepository.clearActiveProfile()
                }
            },
            onExitApp = onExitApp
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

    Log.d("MainActivity", "Enqueued full Trakt sync (REPLACE)")
}
