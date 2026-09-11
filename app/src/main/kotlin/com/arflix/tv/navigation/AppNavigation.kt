package com.arflix.tv.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arflix.tv.data.model.Category
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.repository.AuthState
import com.arflix.tv.ui.screens.details.DetailsScreen
import com.arflix.tv.ui.screens.home.HomeScreen
import com.arflix.tv.ui.screens.login.LoginScreen
import com.arflix.tv.ui.screens.player.PlayerScreen
import com.arflix.tv.ui.screens.collections.CollectionDetailsScreen
import com.arflix.tv.ui.screens.search.SearchScreen
import com.arflix.tv.ui.screens.settings.SettingsScreen
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreen
import com.arflix.tv.ui.screens.tv.live.LiveTvScreen
import com.arflix.tv.ui.screens.watchlist.WatchlistScreen
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreen
import com.arflix.tv.util.LocalDeviceType

/**
 * Navigation destinations
 */
sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Watchlist : Screen("watchlist")
    data object CollectionDetails : Screen("collections/{catalogId}") {
        fun createRoute(catalogId: String): String {
            return "collections/${android.net.Uri.encode(catalogId)}"
        }
    }
    data object Tv : Screen("tv?channelId={channelId}&streamUrl={streamUrl}") {
        fun createRoute(channelId: String? = null, streamUrl: String? = null): String {
            if (channelId == null) return "tv"
            val enc = java.net.URLEncoder.encode(channelId, "UTF-8")
            val streamEnc = streamUrl?.let { java.net.URLEncoder.encode(it, "UTF-8") }
            return if (streamEnc != null) "tv?channelId=$enc&streamUrl=$streamEnc" else "tv?channelId=$enc"
        }
    }
    data object Settings : Screen("settings") {
        fun createRoute(autoCloudAuth: Boolean = false, initialSection: String? = null): String {
            val base = "settings"
            val params = mutableListOf<String>()
            if (autoCloudAuth) params.add("autoCloudAuth=true")
            initialSection?.let { params.add("initialSection=$it") }
            return if (params.isNotEmpty()) "$base?${params.joinToString("&")}" else base
        }
    }
    data object TelegramSettings : Screen("telegram_settings")
    data object ProfileSelection : Screen("profile_selection")

    data object Details : Screen("details/{mediaType}/{mediaId}?initialSeason={initialSeason}&initialEpisode={initialEpisode}") {
        fun createRoute(
            mediaType: MediaType,
            mediaId: Int,
            initialSeason: Int? = null,
            initialEpisode: Int? = null
        ): String {
            val base = "details/${mediaType.name.lowercase()}/$mediaId"
            val params = mutableListOf<String>()
            initialSeason?.let { params.add("initialSeason=$it") }
            initialEpisode?.let { params.add("initialEpisode=$it") }
            return if (params.isNotEmpty()) "$base?${params.joinToString("&")}" else base
        }
    }

    data object Player : Screen("player/{mediaType}/{mediaId}?seasonNumber={seasonNumber}&episodeNumber={episodeNumber}&tmdbSeasonNumber={tmdbSeasonNumber}&tmdbEpisodeNumber={tmdbEpisodeNumber}&kitsuId={kitsuId}&kitsuEpisodeNumber={kitsuEpisodeNumber}&imdbId={imdbId}&streamUrl={streamUrl}&preferredAddonId={preferredAddonId}&preferredSourceName={preferredSourceName}&preferredBingeGroup={preferredBingeGroup}&startPositionMs={startPositionMs}&isLiveStream={isLiveStream}") {
        fun createRoute(
            mediaType: MediaType,
            mediaId: Int,
            seasonNumber: Int? = null,
            episodeNumber: Int? = null,
            tmdbSeasonNumber: Int? = seasonNumber,
            tmdbEpisodeNumber: Int? = episodeNumber,
            kitsuId: Int? = null,
            kitsuEpisodeNumber: Int? = null,
            imdbId: String? = null,
            streamUrl: String? = null,
            preferredAddonId: String? = null,
            preferredSourceName: String? = null,
            preferredBingeGroup: String? = null,
            startPositionMs: Long? = null,
            isLiveStream: Boolean = false
        ): String {
            val base = "player/${mediaType.name.lowercase()}/$mediaId"
            val params = mutableListOf<String>()
            seasonNumber?.let { params.add("seasonNumber=$it") }
            episodeNumber?.let { params.add("episodeNumber=$it") }
            tmdbSeasonNumber?.let { params.add("tmdbSeasonNumber=$it") }
            tmdbEpisodeNumber?.let { params.add("tmdbEpisodeNumber=$it") }
            kitsuId?.let { params.add("kitsuId=$it") }
            kitsuEpisodeNumber?.let { params.add("kitsuEpisodeNumber=$it") }
            imdbId?.let { params.add("imdbId=${java.net.URLEncoder.encode(it, "UTF-8")}") }
            streamUrl?.let { params.add("streamUrl=${java.net.URLEncoder.encode(it, "UTF-8")}") }
            preferredAddonId?.let { params.add("preferredAddonId=${java.net.URLEncoder.encode(it, "UTF-8")}") }
            preferredSourceName?.let { params.add("preferredSourceName=${java.net.URLEncoder.encode(it, "UTF-8")}") }
            preferredBingeGroup?.let { params.add("preferredBingeGroup=${java.net.URLEncoder.encode(it, "UTF-8")}") }
            startPositionMs?.let { params.add("startPositionMs=$it") }
            if (isLiveStream) params.add("isLiveStream=true")
            return if (params.isNotEmpty()) "$base?${params.joinToString("&")}" else base
        }
    }
}

internal fun NavHostController.navigateToProfileSelection() {
    navigate(Screen.ProfileSelection.route) {
        // The start destination may already have been removed after profile selection.
        popUpTo(graph.id) { inclusive = false }
        launchSingleTop = true
    }
}

/**
 * Main navigation graph
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route,
    preloadedCategories: List<Category> = emptyList(),
    preloadedHeroItem: MediaItem? = null,
    preloadedHeroLogoUrl: String? = null,
    preloadedLogoCache: Map<String, String> = emptyMap(),
    currentProfile: Profile? = null,
    isCloudConnected: Boolean = false,
    onSwitchProfile: () -> Unit = {},
    onTvFullscreenChanged: (Boolean) -> Unit = {},
    onExitApp: () -> Unit = {}
) {
    val navigateTopLevel: (String) -> Unit = { route ->
        navController.navigate(route) {
            popUpTo(Screen.Home.route) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateHome: () -> Unit = {
        // Pop back to the EXISTING Home entry rather than replacing it.
        //
        // navigateTopLevel uses popUpTo(Home) with inclusive = false, so Home is still on the back
        // stack while the user is on Watchlist/TV/Search. Re-navigating with inclusive = true
        // destroyed that entry, and with it the HomeViewModel that hiltViewModel() scopes to it —
        // so every return to Home rebuilt the whole screen from scratch (~550 requests, ~5s).
        //
        // popBackStack also clears everything stacked above Home, which is what the previous
        // comment here wanted (no stale Details pages); the fallback covers the case it worried
        // about, Home not being on the stack at all.
        if (!navController.popBackStack(Screen.Home.route, inclusive = false)) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) { inclusive = true; saveState = false }
                launchSingleTop = true
                restoreState = false
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        // Premium screen transitions — subtle fade + slight depth push.
        // Netflix TV uses ~250ms fade; this is tuned for Android TV's 60fps.
        // Pure crossfade — no horizontal slides (those feel mobile, not TV).
        // Netflix TV uses ~250ms crossfade for all screen transitions.
        enterTransition = { fadeIn(androidx.compose.animation.core.tween(280, easing = androidx.compose.animation.core.FastOutSlowInEasing)) },
        exitTransition = { fadeOut(androidx.compose.animation.core.tween(240, easing = androidx.compose.animation.core.FastOutSlowInEasing)) },
        popEnterTransition = { fadeIn(androidx.compose.animation.core.tween(280, easing = androidx.compose.animation.core.FastOutSlowInEasing)) },
        popExitTransition = { fadeOut(androidx.compose.animation.core.tween(240, easing = androidx.compose.animation.core.FastOutSlowInEasing)) }
    ) {
        // Login screen
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Home screen
        composable(Screen.Home.route) {
            HomeScreen(
                preloadedCategories = preloadedCategories,
                preloadedHeroItem = preloadedHeroItem,
                preloadedHeroLogoUrl = preloadedHeroLogoUrl,
                preloadedLogoCache = preloadedLogoCache,
                currentProfile = currentProfile,
                onNavigateToDetails = { mediaType, mediaId, initialSeason, initialEpisode ->
                    navController.navigate(Screen.Details.createRoute(mediaType, mediaId, initialSeason, initialEpisode))
                },
                onNavigateToCollection = { catalogId ->
                    navController.navigate(Screen.CollectionDetails.createRoute(catalogId))
                },
                onNavigateToSearch = {
                    navigateTopLevel(Screen.Search.route)
                },
                onNavigateToWatchlist = {
                    navigateTopLevel(Screen.Watchlist.route)
                },
                onNavigateToTv = { channelId, streamUrl ->
                    navigateTopLevel(Screen.Tv.createRoute(channelId, streamUrl))
                },
                onNavigateToPlayer = { mediaType, mediaId, streamUrl, preferredAddonId, preferredSourceName ->
                    navController.navigate(
                        Screen.Player.createRoute(
                            mediaType = mediaType,
                            mediaId = mediaId,
                            streamUrl = streamUrl,
                            preferredAddonId = preferredAddonId,
                            preferredSourceName = preferredSourceName,
                            isLiveStream = true
                        )
                    )
                },
                onNavigateToSettings = {
                    navigateTopLevel(Screen.Settings.route)
                },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigateToProfileSelection()
                },
                onExitApp = onExitApp
            )
        }

        // Search screen
        composable(Screen.Search.route) {
            SearchScreen(
                currentProfile = currentProfile,
                onNavigateToDetails = { mediaType, mediaId ->
                    navController.navigate(Screen.Details.createRoute(mediaType, mediaId))
                },
                onNavigateToHome = { navigateHome() },
                onNavigateToWatchlist = { navigateTopLevel(Screen.Watchlist.route) },
                onNavigateToTv = { navigateTopLevel(Screen.Tv.createRoute()) },
                onNavigateToSettings = { navigateTopLevel(Screen.Settings.route) },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigateToProfileSelection()
                },
                onBack = { navigateHome() }
            )
        }

        // Watchlist screen
        composable(Screen.Watchlist.route) {
            WatchlistScreen(
                currentProfile = currentProfile,
                onNavigateToDetails = { mediaType, mediaId ->
                    navController.navigate(Screen.Details.createRoute(mediaType, mediaId))
                },
                onNavigateToHome = { navigateHome() },
                onNavigateToSearch = { navigateTopLevel(Screen.Search.route) },
                onNavigateToTv = { navigateTopLevel(Screen.Tv.createRoute()) },
                onNavigateToSettings = { section ->
                    navigateTopLevel(Screen.Settings.createRoute(initialSection = section))
                },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigateToProfileSelection()
                },
                onBack = { navigateHome() }
            )
        }

        // TV screen
        composable(
            route = Screen.Tv.route,
            arguments = listOf(
                navArgument("channelId") { type = NavType.StringType; nullable = true; defaultValue = null },
                navArgument("streamUrl") { type = NavType.StringType; nullable = true; defaultValue = null }
            )
        ) { backStackEntry ->
            val initialChannelId = backStackEntry.arguments?.getString("channelId")
            val initialStreamUrl = backStackEntry.arguments?.getString("streamUrl")
            LiveTvScreen(
                currentProfile = currentProfile,
                initialChannelId = initialChannelId,
                initialStreamUrl = initialStreamUrl,
                onFullscreenChanged = onTvFullscreenChanged,
                onNavigateToHome = { navigateHome() },
                onNavigateToSearch = { navigateTopLevel(Screen.Search.route) },
                onNavigateToWatchlist = { navigateTopLevel(Screen.Watchlist.route) },
                onNavigateToSettings = { navigateTopLevel(Screen.Settings.route) },
                onNavigateToIptvSettings = { navigateTopLevel(Screen.Settings.createRoute(initialSection = "iptv")) },
                onNavigateToDetails = { mediaType, mediaId ->
                    navController.navigate(Screen.Details.createRoute(mediaType, mediaId))
                },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigateToProfileSelection()
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Settings screen
        composable(
            route = "settings?autoCloudAuth={autoCloudAuth}&initialSection={initialSection}&installPackUrl={installPackUrl}",
            arguments = listOf(
                navArgument("autoCloudAuth") {
                    type = NavType.BoolType
                    defaultValue = false
                },
                navArgument("initialSection") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("installPackUrl") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val autoCloudAuth = backStackEntry.arguments?.getBoolean("autoCloudAuth") ?: false
            val initialSection = backStackEntry.arguments?.getString("initialSection")
            val installPackUrl = backStackEntry.arguments?.getString("installPackUrl")
            SettingsScreen(
                currentProfile = currentProfile,
                autoStartCloudAuth = autoCloudAuth,
                initialSection = initialSection,
                installPackUrl = installPackUrl,
                onNavigateToHome = { navigateHome() },
                onNavigateToSearch = { navigateTopLevel(Screen.Search.route) },
                onNavigateToTv = { navigateTopLevel(Screen.Tv.createRoute()) },
                onNavigateToWatchlist = { navigateTopLevel(Screen.Watchlist.route) },
                onNavigateToTelegramSettings = { navController.navigate(Screen.TelegramSettings.route) },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigateToProfileSelection()
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Telegram settings screen
        composable(Screen.TelegramSettings.route) {
            TelegramSettingsScreen(onBack = { navController.popBackStack() })
        }

        // Profile selection screen
        composable(Screen.ProfileSelection.route) {
            ProfileSelectionScreen(
                onProfileSelected = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.ProfileSelection.route) { inclusive = true }
                    }
                },
                onShowAddProfile = { /* Handled internally by ProfileSelectionScreen */ },
                onConnectCloud = {
                    navController.navigate("settings?autoCloudAuth=true")
                },
                isCloudConnected = isCloudConnected
            )
        }

        // Details screen
        composable(
            route = Screen.CollectionDetails.route,
            arguments = listOf(navArgument("catalogId") { type = NavType.StringType })
        ) { backStackEntry ->
            val catalogId = backStackEntry.arguments?.getString("catalogId").orEmpty()
            if (catalogId.isBlank()) {
                navigateHome()
                return@composable
            }
            CollectionDetailsScreen(
                catalogId = catalogId,
                currentProfile = currentProfile,
                onNavigateToDetails = { mediaType, mediaId ->
                    navController.navigate(Screen.Details.createRoute(mediaType, mediaId))
                },
                onNavigateToPlayer = { mediaType, mediaId, streamUrl, preferredAddonId, preferredSourceName ->
                    navController.navigate(
                        Screen.Player.createRoute(
                            mediaType = mediaType,
                            mediaId = mediaId,
                            streamUrl = streamUrl,
                            preferredAddonId = preferredAddonId,
                            preferredSourceName = preferredSourceName,
                            isLiveStream = true
                        )
                    )
                },
                onNavigateToHome = { navigateHome() },
                onNavigateToSearch = { navigateTopLevel(Screen.Search.route) },
                onNavigateToWatchlist = { navigateTopLevel(Screen.Watchlist.route) },
                onNavigateToTv = { navigateTopLevel(Screen.Tv.createRoute()) },
                onNavigateToSettings = { navigateTopLevel(Screen.Settings.route) },
                onBack = { navController.popBackStack() }
            )
        }

        // Details screen
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("mediaType") { type = NavType.StringType },
                navArgument("mediaId") { type = NavType.IntType },
                navArgument("initialSeason") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("initialEpisode") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val mediaTypeStr = backStackEntry.arguments?.getString("mediaType") ?: "movie"
            val mediaId = backStackEntry.arguments?.getInt("mediaId") ?: 0
            if (mediaId <= 0) {
                navigateHome()
                return@composable
            }
            val initialSeason = backStackEntry.arguments?.getInt("initialSeason")?.takeIf { it >= 0 }
            val initialEpisode = backStackEntry.arguments?.getInt("initialEpisode")?.takeIf { it >= 0 }
            val mediaType = if (mediaTypeStr == "tv") MediaType.TV else MediaType.MOVIE

            DetailsScreen(
                mediaType = mediaType,
                mediaId = mediaId,
                initialSeason = initialSeason,
                initialEpisode = initialEpisode,
                currentProfile = currentProfile,
                onNavigateToPlayer = { type, id, identity, imdbId, url, preferredAddonId, preferredSourceName, startPositionMs ->
                    navController.navigate(
                        Screen.Player.createRoute(
                            mediaType = type,
                            mediaId = id,
                            seasonNumber = identity?.displaySeason,
                            episodeNumber = identity?.displayEpisode,
                            tmdbSeasonNumber = identity?.tmdbSeason,
                            tmdbEpisodeNumber = identity?.tmdbEpisode,
                            kitsuId = identity?.kitsuId,
                            kitsuEpisodeNumber = identity?.kitsuEpisode,
                            imdbId = imdbId,
                            streamUrl = url,
                            preferredAddonId = preferredAddonId,
                            preferredSourceName = preferredSourceName,
                            startPositionMs = startPositionMs
                        )
                    )
                },
                onNavigateToDetails = { type, id ->
                    navController.navigate(Screen.Details.createRoute(type, id))
                },
                onNavigateToCollection = { catalogId ->
                    navController.navigate(Screen.CollectionDetails.createRoute(catalogId))
                },
                onNavigateToHome = {
                    navigateHome()
                },
                onNavigateToSearch = {
                    navigateTopLevel(Screen.Search.route)
                },
                onNavigateToTv = {
                    navigateTopLevel(Screen.Tv.createRoute())
                },
                onNavigateToWatchlist = {
                    navigateTopLevel(Screen.Watchlist.route)
                },
                onNavigateToSettings = {
                    navigateTopLevel(Screen.Settings.route)
                },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigate(Screen.ProfileSelection.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Player screen
        composable(
            route = Screen.Player.route,
            arguments = listOf(
                navArgument("mediaType") { type = NavType.StringType },
                navArgument("mediaId") { type = NavType.IntType },
                navArgument("seasonNumber") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("episodeNumber") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("tmdbSeasonNumber") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("tmdbEpisodeNumber") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("kitsuId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("kitsuEpisodeNumber") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("imdbId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("streamUrl") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("preferredAddonId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("preferredSourceName") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("preferredBingeGroup") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("startPositionMs") {
                    type = NavType.LongType
                    defaultValue = -1L
                },
                navArgument("isLiveStream") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            ),
            exitTransition = { ExitTransition.None },
            popExitTransition = { ExitTransition.None }
        ) { backStackEntry ->
            val mediaTypeStr = backStackEntry.arguments?.getString("mediaType") ?: "movie"
            val mediaId = backStackEntry.arguments?.getInt("mediaId") ?: 0
            val seasonNumber = backStackEntry.arguments?.getInt("seasonNumber")?.takeIf { it >= 0 }
            val episodeNumber = backStackEntry.arguments?.getInt("episodeNumber")?.takeIf { it >= 0 }
            val tmdbSeasonNumber = backStackEntry.arguments?.getInt("tmdbSeasonNumber")?.takeIf { it >= 0 }
                ?: seasonNumber
            val tmdbEpisodeNumber = backStackEntry.arguments?.getInt("tmdbEpisodeNumber")?.takeIf { it >= 0 }
                ?: episodeNumber
            val kitsuId = backStackEntry.arguments?.getInt("kitsuId")?.takeIf { it > 0 }
            val kitsuEpisodeNumber = backStackEntry.arguments?.getInt("kitsuEpisodeNumber")?.takeIf { it > 0 }
            val imdbId = backStackEntry.arguments?.getString("imdbId")?.takeIf { it.isNotBlank() }
            val streamUrl = backStackEntry.arguments?.getString("streamUrl")?.takeIf { it.isNotEmpty() }
            val preferredAddonId = backStackEntry.arguments?.getString("preferredAddonId")?.takeIf { it.isNotBlank() }
            val preferredSourceName = backStackEntry.arguments?.getString("preferredSourceName")?.takeIf { it.isNotBlank() }
            val preferredBingeGroup = backStackEntry.arguments?.getString("preferredBingeGroup")?.takeIf { it.isNotBlank() }
            val startPositionMs = backStackEntry.arguments?.getLong("startPositionMs")?.takeIf { it >= 0L }
            val isLiveStream = backStackEntry.arguments?.getBoolean("isLiveStream") ?: false
            val mediaType = if (mediaTypeStr == "tv") MediaType.TV else MediaType.MOVIE

            PlayerScreen(
                mediaType = mediaType,
                mediaId = mediaId,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber,
                tmdbSeasonNumber = tmdbSeasonNumber,
                tmdbEpisodeNumber = tmdbEpisodeNumber,
                kitsuId = kitsuId,
                kitsuEpisodeNumber = kitsuEpisodeNumber,
                imdbId = imdbId,
                streamUrl = streamUrl,
                preferredAddonId = preferredAddonId,
                preferredSourceName = preferredSourceName,
                preferredBingeGroup = preferredBingeGroup,
                startPositionMs = startPositionMs,
                isLiveStream = isLiveStream,
                onBack = { navController.popBackStack() },
                onPlayNext = { nextIdentity, nextPreferredAddonId, nextPreferredSourceName, nextPreferredBingeGroup ->
                    // Navigate to next episode
                    navController.navigate(
                        Screen.Player.createRoute(
                            mediaType = mediaType,
                            mediaId = mediaId,
                            seasonNumber = nextIdentity.displaySeason,
                            episodeNumber = nextIdentity.displayEpisode,
                            tmdbSeasonNumber = nextIdentity.tmdbSeason,
                            tmdbEpisodeNumber = nextIdentity.tmdbEpisode,
                            kitsuId = nextIdentity.kitsuId,
                            kitsuEpisodeNumber = nextIdentity.kitsuEpisode,
                            preferredAddonId = nextPreferredAddonId,
                            preferredSourceName = nextPreferredSourceName,
                            preferredBingeGroup = nextPreferredBingeGroup
                        )
                    ) {
                        popUpTo(Screen.Player.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
