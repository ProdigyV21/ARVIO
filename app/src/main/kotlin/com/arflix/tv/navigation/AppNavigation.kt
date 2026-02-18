package com.arflix.tv.navigation

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
import com.arflix.tv.ui.screens.search.SearchScreen
import com.arflix.tv.ui.screens.settings.SettingsScreen
import com.arflix.tv.ui.screens.tv.TvScreen
import com.arflix.tv.ui.screens.watchlist.WatchlistScreen
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreen

/**
 * Navigation destinations
 */
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Search : Screen("search")
    object Watchlist : Screen("watchlist")
    object Tv : Screen("tv")
    object Settings : Screen("settings")
    object ProfileSelection : Screen("profile_selection")
    
    object Details : Screen("details/{mediaType}/{mediaId}?initialSeason={initialSeason}&initialEpisode={initialEpisode}") {
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
    
    object Player : Screen("player/{mediaType}/{mediaId}?seasonNumber={seasonNumber}&episodeNumber={episodeNumber}&imdbId={imdbId}&streamUrl={streamUrl}&startPositionMs={startPositionMs}") {
        fun createRoute(
            mediaType: MediaType,
            mediaId: Int,
            seasonNumber: Int? = null,
            episodeNumber: Int? = null,
            imdbId: String? = null,
            streamUrl: String? = null,
            startPositionMs: Long? = null
        ): String {
            val base = "player/${mediaType.name.lowercase()}/$mediaId"
            val params = mutableListOf<String>()
            seasonNumber?.let { params.add("seasonNumber=$it") }
            episodeNumber?.let { params.add("episodeNumber=$it") }
            imdbId?.let { params.add("imdbId=${java.net.URLEncoder.encode(it, "UTF-8")}") }
            streamUrl?.let { params.add("streamUrl=${java.net.URLEncoder.encode(it, "UTF-8")}") }
            startPositionMs?.let { params.add("startPositionMs=$it") }
            return if (params.isNotEmpty()) "$base?${params.joinToString("&")}" else base
        }
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
    onSwitchProfile: () -> Unit = {},
    onExitApp: () -> Unit = {}
) {
    val navigateTopLevel: (String) -> Unit = { route ->
        val popped = navController.popBackStack(route, false)
        if (!popped) {
            navController.navigate(route) {
                launchSingleTop = true
            }
        }
    }

    val navigateHome: () -> Unit = {
        navigateTopLevel(Screen.Home.route)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
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
                onNavigateToSearch = {
                    navigateTopLevel(Screen.Search.route)
                },
                onNavigateToWatchlist = {
                    navigateTopLevel(Screen.Watchlist.route)
                },
                onNavigateToTv = {
                    navigateTopLevel(Screen.Tv.route)
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
                onNavigateToTv = { navigateTopLevel(Screen.Tv.route) },
                onNavigateToSettings = { navigateTopLevel(Screen.Settings.route) },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigate(Screen.ProfileSelection.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
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
                onNavigateToTv = { navigateTopLevel(Screen.Tv.route) },
                onNavigateToSettings = { navigateTopLevel(Screen.Settings.route) },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigate(Screen.ProfileSelection.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        // TV screen
        composable(Screen.Tv.route) {
            TvScreen(
                currentProfile = currentProfile,
                onNavigateToHome = { navigateHome() },
                onNavigateToSearch = { navigateTopLevel(Screen.Search.route) },
                onNavigateToWatchlist = { navigateTopLevel(Screen.Watchlist.route) },
                onNavigateToSettings = { navigateTopLevel(Screen.Settings.route) },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigate(Screen.ProfileSelection.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Settings screen
        composable(Screen.Settings.route) {
            SettingsScreen(
                currentProfile = currentProfile,
                onNavigateToHome = { navigateHome() },
                onNavigateToSearch = { navigateTopLevel(Screen.Search.route) },
                onNavigateToTv = { navigateTopLevel(Screen.Tv.route) },
                onNavigateToWatchlist = { navigateTopLevel(Screen.Watchlist.route) },
                onSwitchProfile = {
                    onSwitchProfile()
                    navController.navigate(Screen.ProfileSelection.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Profile selection screen
        composable(Screen.ProfileSelection.route) {
            ProfileSelectionScreen(
                onProfileSelected = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.ProfileSelection.route) { inclusive = true }
                    }
                },
                onShowAddProfile = { /* Handled internally by ProfileSelectionScreen */ }
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
            val initialSeason = backStackEntry.arguments?.getInt("initialSeason")?.takeIf { it >= 0 }
            val initialEpisode = backStackEntry.arguments?.getInt("initialEpisode")?.takeIf { it >= 0 }
            val mediaType = if (mediaTypeStr == "tv") MediaType.TV else MediaType.MOVIE

            DetailsScreen(
                mediaType = mediaType,
                mediaId = mediaId,
                initialSeason = initialSeason,
                initialEpisode = initialEpisode,
                currentProfile = currentProfile,
                onNavigateToPlayer = { type, id, season, episode, imdbId, url, startPositionMs ->
                    navController.navigate(
                        Screen.Player.createRoute(type, id, season, episode, imdbId, url, startPositionMs)
                    )
                },
                onNavigateToDetails = { type, id ->
                    navController.navigate(Screen.Details.createRoute(type, id))
                },
                onNavigateToHome = {
                    navigateHome()
                },
                onNavigateToSearch = {
                    navigateTopLevel(Screen.Search.route)
                },
                onNavigateToTv = {
                    navigateTopLevel(Screen.Tv.route)
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
                navArgument("imdbId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("streamUrl") { 
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("startPositionMs") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val mediaTypeStr = backStackEntry.arguments?.getString("mediaType") ?: "movie"
            val mediaId = backStackEntry.arguments?.getInt("mediaId") ?: 0
            val seasonNumber = backStackEntry.arguments?.getInt("seasonNumber")?.takeIf { it >= 0 }
            val episodeNumber = backStackEntry.arguments?.getInt("episodeNumber")?.takeIf { it >= 0 }
            val imdbId = backStackEntry.arguments?.getString("imdbId")?.takeIf { it.isNotBlank() }
            val streamUrl = backStackEntry.arguments?.getString("streamUrl")?.takeIf { it.isNotEmpty() }
            val startPositionMs = backStackEntry.arguments?.getLong("startPositionMs")?.takeIf { it >= 0L }
            val mediaType = if (mediaTypeStr == "tv") MediaType.TV else MediaType.MOVIE
            
            PlayerScreen(
                mediaType = mediaType,
                mediaId = mediaId,
                seasonNumber = seasonNumber,
                episodeNumber = episodeNumber,
                imdbId = imdbId,
                streamUrl = streamUrl,
                startPositionMs = startPositionMs,
                onBack = { navController.popBackStack() },
                onPlayNext = { nextSeason, nextEpisode ->
                    // Navigate to next episode
                    navController.navigate(
                        Screen.Player.createRoute(mediaType, mediaId, nextSeason, nextEpisode)
                    ) {
                        popUpTo(Screen.Player.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
