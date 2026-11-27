package com.arflix.tv.ui.screens.music

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.automirrored.filled.QueueMusic
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Lyrics
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.RepeatOne
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import com.arflix.tv.music.AlbumShelf
import com.arflix.tv.music.ChartsSource
import com.arflix.tv.music.MusicAlbum
import com.arflix.tv.music.MusicArtist
import com.arflix.tv.music.MusicCollections
import com.arflix.tv.music.MusicPin
import com.arflix.tv.music.MusicPinType
import com.arflix.tv.music.MusicPlaybackState
import com.arflix.tv.music.MusicSearchRanking
import com.arflix.tv.music.MusicServerType
import com.arflix.tv.music.MusicTrack
import com.arflix.tv.music.SearchSection
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.ReconcileTopBarFocus
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.topBarSelectedIndex
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import com.arflix.tv.ui.screens.watchlist.LibraryTabsTopInset
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.BackgroundElevated
import kotlinx.coroutines.delay

/*
 * The Music tab on Android TV: the same library, sources and player as the phone tab
 * (MusicViewModel), laid out for a remote. Every control is a focus target with a clear focus
 * state, pages slide in and out, and the player fills the screen with the cover's colours.
 */

private val TvGutter = 48.dp
private val FocusRing = Color.White

/** False where focus targets must step aside, e.g. the player controls under the queue panel. */
private val LocalTvFocusEnabled = compositionLocalOf { true }

/** Where the TV Music tab is: the library tabs, plus a search tab the phone keeps in its header. */
private enum class TvMusicTab { HOME, MINE, ALBUMS, ARTISTS, SEARCH }

private fun TvMusicTab.libraryTab(): MusicTab? = when (this) {
    TvMusicTab.HOME -> MusicTab.HOME
    TvMusicTab.MINE -> MusicTab.MINE
    TvMusicTab.ALBUMS -> MusicTab.ALBUMS
    TvMusicTab.ARTISTS -> MusicTab.ARTISTS
    TvMusicTab.SEARCH -> null
}

/** One choice in a TV list dialog. */
private class TvChoice(val label: String, val icon: ImageVector? = null, val onClick: () -> Unit)

@Composable
fun TvMusicScreen(
    viewModel: MusicViewModel = hiltViewModel(),
    currentProfile: Profile? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val playback by viewModel.playback.collectAsStateWithLifecycle()
    val accent = resolveAccentColor(fallback = Color.White)
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val focusManager = LocalFocusManager.current
    val hasProfile = currentProfile != null

    var topFocused by remember { mutableStateOf(false) }
    var topIndex by remember { mutableIntStateOf(topBarSelectedIndex(SidebarItem.MUSIC, hasProfile).coerceAtLeast(0)) }
    ReconcileTopBarFocus(topIndex, hasProfile) { topIndex = it }
    val topFocus = remember { FocusRequester() }

    var tab by rememberSaveable { mutableStateOf(TvMusicTab.HOME) }
    var addingSource by rememberSaveable { mutableStateOf(false) }
    var sourcesDialog by remember { mutableStateOf(false) }
    var trackMenu by remember { mutableStateOf<List<TvChoice>?>(null) }
    LaunchedEffect(state.library) { addingSource = false }

    // Cards register here, so focus goes back to the card that opened a page when it closes.
    val cardFocus = remember { mutableStateMapOf<String, FocusRequester>() }
    var openedFrom by rememberSaveable { mutableStateOf<String?>(null) }
    val nowPlayingFocus = remember { FocusRequester() }
    // The selected library tab; the top bar's Down lands here instead of the nearest chip.
    val contentEntry = remember { FocusRequester() }
    // Only the library has a tab row; pages, the source form and the empty state use the nearest target.
    val libraryShown = state.hasSources && !addingSource && state.pages.isEmpty()
    val enterContent = {
        if (!libraryShown || runCatching { contentEntry.requestFocus() }.isFailure) focusManager.moveFocus(FocusDirection.Down)
    }

    val current = playback.current
    val playerOpen = state.playerOpen && current != null

    BackHandler(!playerOpen && trackMenu == null && !sourcesDialog) {
        when {
            addingSource && state.hasSources -> addingSource = false
            viewModel.back() -> Unit
            tab == TvMusicTab.SEARCH && state.query.isNotEmpty() -> viewModel.setQuery("")
            !topFocused -> runCatching { topFocus.requestFocus() }
            else -> onBack()
        }
    }

    val context = LocalContext.current
    val openTrackMenu: (MusicTrack, List<TvChoice>) -> Unit = { track, extra ->
        trackMenu = trackChoices(context, track, state.collections, viewModel, extra)
    }

    Box(
        Modifier.fillMaxSize().background(Color.Black).testTag("tv-music-screen")
            .onPreviewKeyEvent { event ->
                // The remote's media keys work anywhere on the tab, not only in the player.
                if (event.type != KeyEventType.KeyDown || current == null) return@onPreviewKeyEvent false
                when (event.key) {
                    Key.MediaPlayPause, Key.MediaPlay, Key.MediaPause -> { viewModel.togglePlay(); true }
                    Key.MediaNext, Key.MediaFastForward -> { viewModel.next(); true }
                    Key.MediaPrevious, Key.MediaRewind -> { viewModel.previous(); true }
                    else -> false
                }
            },
    ) {
        Box(
            Modifier.fillMaxWidth().height(AppTopBarContentTopInset).focusRequester(topFocus)
                .onFocusChanged { topFocused = it.isFocused }
                .onKeyEvent { event ->
                    if (event.type != KeyEventType.KeyDown) false else when (event.key.mirrorHorizontalForRtl(isRtl)) {
                        Key.DirectionLeft -> { topIndex = (topIndex - 1).coerceAtLeast(0); true }
                        Key.DirectionRight -> { topIndex = (topIndex + 1).coerceAtMost(topBarMaxIndex(hasProfile)); true }
                        Key.DirectionDown -> { enterContent(); true }
                        Key.Enter, Key.DirectionCenter, Key.NumPadEnter -> {
                            when (topBarFocusedItem(topIndex, hasProfile)) {
                                SidebarItem.HOME -> onNavigateToHome()
                                SidebarItem.SEARCH -> onNavigateToSearch()
                                SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                SidebarItem.TV -> onNavigateToTv()
                                SidebarItem.NEWS -> com.arflix.tv.ui.components.NewsNavigation.open()
                                SidebarItem.SETTINGS -> onNavigateToSettings()
                                SidebarItem.MUSIC -> enterContent()
                                null -> onSwitchProfile()
                            }; true
                        }
                        else -> false
                    }
                }.focusable()
        ) {
            AppTopBar(SidebarItem.MUSIC, topFocused, topIndex, profile = currentProfile)
        }

        Box(Modifier.fillMaxSize().padding(top = LibraryTabsTopInset)) {
            when {
                !state.configLoaded -> Unit
                !state.hasSources || addingSource -> TvConnectView(
                    state = state,
                    accent = accent,
                    onConnect = viewModel::connect,
                    onAddAddon = viewModel::addAddon,
                    onClose = if (state.hasSources) ({ addingSource = false }) else null,
                )
                else -> TvMusicPages(
                    state = state,
                    playback = playback,
                    accent = accent,
                    tab = tab,
                    onTab = { next ->
                        tab = next
                        next.libraryTab()?.let(viewModel::selectTab)
                    },
                    viewModel = viewModel,
                    cardFocus = cardFocus,
                    openedFrom = openedFrom,
                    onOpenFrom = { openedFrom = it },
                    nowPlayingFocus = nowPlayingFocus,
                    tabFocus = contentEntry,
                    onSources = { sourcesDialog = true },
                    onTrackMenu = openTrackMenu,
                )
            }
        }

        // The player is its own window, so the remote cannot wander onto the library behind it.
        if (playerOpen && current != null) {
            Dialog(
                onDismissRequest = { viewModel.setPlayerOpen(false) },
                properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false, dismissOnBackPress = false),
            ) {
                TvPlayer(
                    track = current,
                    playback = playback,
                    state = state,
                    appAccent = accent,
                    viewModel = viewModel,
                    onTrackMenu = openTrackMenu,
                )
            }
        }
        // Closing the player puts focus back on the now playing chip that opened it.
        var wasOpen by remember { mutableStateOf(false) }
        LaunchedEffect(playerOpen) {
            if (wasOpen && !playerOpen) {
                delay(60)
                runCatching { nowPlayingFocus.requestFocus() }
            }
            wasOpen = playerOpen
        }

        trackMenu?.let { choices ->
            TvChoiceDialog(title = null, choices = choices, onDismiss = { trackMenu = null })
        }
        if (sourcesDialog) {
            val choices = buildList {
                state.library.addons.forEach { addon ->
                    add(TvChoice(stringResource(R.string.music_remove_addon, addon.name), Icons.Default.Delete) { viewModel.removeAddon(addon) })
                }
                if (state.config != null) add(TvChoice(stringResource(R.string.music_disconnect), Icons.Default.Close) { viewModel.disconnect() })
                add(TvChoice(stringResource(R.string.music_add_source), Icons.Default.LibraryMusic) { addingSource = true })
            }
            val subtitle = state.config?.let { stringResource(R.string.music_connected_as, it.baseUrl.substringAfter("://"), it.username) }
            TvChoiceDialog(title = stringResource(R.string.music_add_source), subtitle = subtitle, choices = choices, onDismiss = { sourcesDialog = false })
        }
        state.addingToPlaylist?.let { tracks ->
            val choices = buildList {
                state.collections.playlists.forEach { playlist ->
                    add(TvChoice(playlist.name, Icons.AutoMirrored.Filled.QueueMusic) { viewModel.addToPlaylist(playlist.id, tracks) })
                }
                add(TvChoice(stringResource(R.string.music_new_playlist), Icons.AutoMirrored.Filled.PlaylistAdd) {
                    viewModel.createPlaylist(tracks.firstOrNull()?.album?.takeIf { it.isNotBlank() } ?: MusicCollections.DEFAULT_NAME, tracks)
                })
            }
            TvChoiceDialog(
                title = stringResource(R.string.music_add_to_playlist),
                choices = choices,
                onDismiss = { viewModel.showAddToPlaylist(null) },
                closeOnPick = false,
            )
        }
    }
}

/** The menu a song gets on TV: whatever the page adds, then like, playlists and the queue. */
private fun trackChoices(
    context: Context,
    track: MusicTrack,
    collections: MusicCollections,
    viewModel: MusicActions,
    extra: List<TvChoice>,
): List<TvChoice> {
    val liked = collections.isFavorite(track)
    return extra + listOf(
        TvChoice(context.getString(if (liked) R.string.music_unlike else R.string.music_like),
            if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder) { viewModel.toggleFavorite(track) },
        TvChoice(context.getString(R.string.music_add_to_playlist), Icons.AutoMirrored.Filled.PlaylistAdd) { viewModel.showAddToPlaylist(listOf(track)) },
        TvChoice(context.getString(R.string.music_play_next), Icons.AutoMirrored.Filled.PlaylistPlay) { viewModel.playNext(track) },
        TvChoice(context.getString(R.string.music_add_to_queue), Icons.AutoMirrored.Filled.QueueMusic) { viewModel.addToQueue(track) },
    )
}

// ---------------------------------------------------------------------------------------------
// Pages

@Composable
private fun TvMusicPages(
    state: MusicUiState,
    playback: MusicPlaybackState,
    accent: Color,
    tab: TvMusicTab,
    onTab: (TvMusicTab) -> Unit,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    openedFrom: String?,
    onOpenFrom: (String?) -> Unit,
    nowPlayingFocus: FocusRequester,
    tabFocus: FocusRequester,
    onSources: () -> Unit,
    onTrackMenu: (MusicTrack, List<TvChoice>) -> Unit,
) {
    val saveable = rememberSaveableStateHolder()
    val page = state.pages.lastOrNull()
    val depth = state.pages.size
    // Deeper pages come in from the right, going back slides the other way.
    val previousDepth = remember { mutableIntStateOf(depth) }
    val forward = depth >= previousDepth.intValue
    SideEffect { previousDepth.intValue = depth }
    AnimatedContent(
        targetState = page,
        contentKey = { p -> p?.let(::pageKey) ?: "library" },
        transitionSpec = {
            val enter = fadeIn(tween(260)) + slideInHorizontally(tween(320, easing = FastOutSlowInEasing)) { w -> if (forward) w / 12 else -w / 12 }
            val exit = fadeOut(tween(180)) + slideOutHorizontally(tween(260)) { w -> if (forward) -w / 16 else w / 16 }
            enter togetherWith exit
        },
        label = "tv-music-page",
    ) { shown ->
        val key = shown?.let(::pageKey) ?: "library"
        saveable.SaveableStateProvider(key) {
            when (shown) {
                null -> TvLibrary(state, playback, accent, tab, onTab, viewModel, cardFocus, openedFrom, onOpenFrom, nowPlayingFocus, tabFocus, onSources, onTrackMenu)
                is MusicPage.Album -> TvAlbumPage(shown, state.collections, playback, accent, viewModel, nowPlayingFocus, onTrackMenu)
                is MusicPage.Artist -> TvArtistPage(shown, state.collections, playback, accent, viewModel, nowPlayingFocus)
                is MusicPage.Playlist -> TvPlaylistPage(shown, state.collections, playback, accent, viewModel, nowPlayingFocus, onTrackMenu)
            }
        }
    }
}

private fun pageKey(page: MusicPage): String = when (page) {
    is MusicPage.Album -> "album:" + page.album.id
    is MusicPage.Artist -> "artist:" + page.artist.id
    is MusicPage.Playlist -> "playlist:" + page.id
}

@Composable
private fun TvLibrary(
    state: MusicUiState,
    playback: MusicPlaybackState,
    accent: Color,
    tab: TvMusicTab,
    onTab: (TvMusicTab) -> Unit,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    openedFrom: String?,
    onOpenFrom: (String?) -> Unit,
    nowPlayingFocus: FocusRequester,
    tabFocus: FocusRequester,
    onSources: () -> Unit,
    onTrackMenu: (MusicTrack, List<TvChoice>) -> Unit,
) {
    // Back from a page lands on the card that opened it; otherwise focus starts on the tabs.
    LaunchedEffect(Unit) {
        delay(80)
        val target = openedFrom?.let { cardFocus[it] }
        val restored = target != null && runCatching { target.requestFocus() }.isSuccess
        if (!restored) runCatching { tabFocus.requestFocus() }
        onOpenFrom(null)
    }
    val open: (String, () -> Unit) -> Unit = { key, action -> onOpenFrom(key); action() }

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = TvGutter, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            listOf(
                TvMusicTab.HOME to stringResource(R.string.music_tab_home),
                TvMusicTab.MINE to stringResource(R.string.music_tab_mine),
                TvMusicTab.ALBUMS to stringResource(R.string.music_tab_albums),
                TvMusicTab.ARTISTS to stringResource(R.string.music_tab_artists),
                TvMusicTab.SEARCH to stringResource(R.string.search),
            ).forEach { (option, label) ->
                TvChip(
                    label = label,
                    icon = if (option == TvMusicTab.SEARCH) Icons.Default.Search else null,
                    selected = tab == option,
                    accent = accent,
                    modifier = if (tab == option) Modifier.focusRequester(tabFocus) else Modifier,
                    tag = "tv-music-tab-" + option.name.lowercase(),
                    onFocus = { if (tab != option) onTab(option) },
                    onClick = { onTab(option) },
                )
            }
            TvIconButton(Icons.Default.Settings, stringResource(R.string.music_add_source), accent, tag = "tv-music-sources", onClick = onSources)
            Spacer(Modifier.weight(1f))
            NowPlayingChip(playback, accent, nowPlayingFocus) { viewModel.setPlayerOpen(true) }
        }

        Box(Modifier.fillMaxSize()) {
            when (tab) {
                TvMusicTab.HOME -> TvHome(state, accent, viewModel, cardFocus, open)
                TvMusicTab.MINE -> TvMine(state, accent, viewModel, cardFocus, open)
                TvMusicTab.ALBUMS -> {
                    val albums = state.allAlbums
                    if (albums == null) LoadingBox() else TvAlbumGrid(albums, "albums", viewModel, cardFocus, open)
                }
                TvMusicTab.ARTISTS -> {
                    val artists = state.artists
                    if (artists == null) LoadingBox() else TvArtistGrid(artists, viewModel, cardFocus, open)
                }
                TvMusicTab.SEARCH -> TvSearch(state, playback, accent, viewModel, cardFocus, open, onTrackMenu)
            }
        }
    }
}

@Composable
private fun TvHome(
    state: MusicUiState,
    accent: Color,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    open: (String, () -> Unit) -> Unit,
) {
    val shelves = state.shelves
    when {
        state.loading && shelves.isEmpty() -> LoadingBox()
        state.error != null && shelves.isEmpty() -> TvMessage(
            text = stringResource(R.string.music_load_failed) + "\n" + state.error,
            action = stringResource(R.string.music_retry),
            accent = accent,
            onAction = viewModel::refresh,
        )
        shelves.isEmpty() && state.collections.isEmpty -> TvMessage(stringResource(R.string.music_empty_library))
        else -> LazyColumn(
            Modifier.fillMaxSize().testTag("tv-music-home"),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(top = 4.dp, bottom = 48.dp),
        ) {
            item(key = "speed-dial") { TvSpeedDial(state.collections, accent, viewModel, cardFocus, open) }
            if (state.taste.recent.isNotEmpty()) item(key = "jump-back-in") {
                TvRowTitle(stringResource(R.string.music_jump_back_in))
                LazyRow(contentPadding = PaddingValues(horizontal = TvGutter), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                    itemsIndexed(state.taste.recent, key = { _, t -> "recent-" + t.id }) { index, track ->
                        TvCoverCard(
                            title = track.title, subtitle = track.artist, coverUrl = track.coverUrl, icon = Icons.Default.MusicNote,
                            focusKey = "recent/" + track.id, cardFocus = cardFocus, width = 150.dp,
                        ) { viewModel.play(state.taste.recent, index) }
                    }
                }
            }
            if (state.collections.favoriteAlbums.isNotEmpty()) item(key = "favorites") {
                TvAlbumRow(stringResource(R.string.music_shelf_favorites), "fav", state.collections.favoriteAlbums, viewModel, cardFocus, open)
            }
            items(shelves, key = { it.key }) { shelf ->
                val title = when (shelf.kind) {
                    AlbumShelf.NEWEST -> stringResource(R.string.music_shelf_newest)
                    AlbumShelf.RECENT -> stringResource(R.string.music_shelf_recent)
                    AlbumShelf.FREQUENT -> stringResource(R.string.music_shelf_frequent)
                    AlbumShelf.RANDOM -> stringResource(R.string.music_shelf_random)
                    else -> shelf.title.orEmpty()
                }
                TvAlbumRow(title, shelf.key, shelf.albums, viewModel, cardFocus, open)
            }
            if (shelves.any { ChartsSource.isChartShelf(it.key) }) item(key = "charts-credit") {
                Text(
                    stringResource(R.string.music_charts_credit),
                    color = Color.White.copy(alpha = 0.45f), fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = TvGutter, vertical = 20.dp),
                )
            }
        }
    }
}

/** "Surprise me", liked songs and the pins, as a row of wide tiles. */
@Composable
private fun TvSpeedDial(
    collections: MusicCollections,
    accent: Color,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    open: (String, () -> Unit) -> Unit,
) {
    val pins = collections.visiblePins()
    val likedPinned = collections.isPinned(MusicPinType.PLAYLIST, MusicCollections.LIKED_ID)
    val likedName = stringResource(R.string.music_liked_songs)
    TvRowTitle(stringResource(R.string.music_speed_dial))
    LazyRow(
        contentPadding = PaddingValues(horizontal = TvGutter),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.testTag("tv-music-speed-dial"),
    ) {
        item(key = "surprise") {
            TvFocusBox(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.width(240.dp).height(76.dp),
                focusKey = "dial/surprise", cardFocus = cardFocus,
                tag = "tv-music-surprise-me",
                onClick = { viewModel.surpriseMe() },
            ) {
                Row(
                    Modifier.fillMaxSize().background(Brush.linearGradient(listOf(accent, accent.copy(alpha = 0.5f)))),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(Modifier.size(76.dp), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.AutoAwesome, null, tint = contentOn(accent), modifier = Modifier.size(30.dp))
                    }
                    Text(
                        stringResource(R.string.music_surprise_me), color = contentOn(accent), fontSize = 16.sp,
                        fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 12.dp),
                    )
                }
            }
        }
        if (collections.favoriteTracks.isNotEmpty() && !likedPinned) item(key = "liked") {
            TvDialTile(likedName, null, Icons.Default.Favorite, accent, false, "dial/liked", cardFocus) {
                open("dial/liked") { viewModel.openPlaylist(MusicPage.LIKED) }
            }
        }
        items(pins, key = { "pin-" + it.type + it.id }) { pin ->
            val liked = pin.type == MusicPinType.PLAYLIST && pin.id == MusicCollections.LIKED_ID
            val key = "dial/" + pin.type + "/" + pin.id
            TvDialTile(
                title = if (liked) likedName else pin.name,
                coverUrl = if (liked) null else pin.coverUrl,
                icon = when {
                    liked -> Icons.Default.Favorite
                    pin.type == MusicPinType.ARTIST -> Icons.Default.Person
                    pin.type == MusicPinType.PLAYLIST -> Icons.AutoMirrored.Filled.QueueMusic
                    else -> Icons.Default.Album
                },
                accent = accent,
                round = pin.type == MusicPinType.ARTIST,
                focusKey = key, cardFocus = cardFocus,
            ) { open(key) { viewModel.openPin(pin) } }
        }
    }
}

@Composable
private fun TvDialTile(
    title: String,
    coverUrl: String?,
    icon: ImageVector,
    accent: Color,
    round: Boolean,
    focusKey: String,
    cardFocus: MutableMap<String, FocusRequester>,
    onClick: () -> Unit,
) {
    TvFocusBox(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.width(240.dp).height(76.dp),
        focusKey = focusKey, cardFocus = cardFocus, onClick = onClick,
    ) { focused ->
        Row(Modifier.fillMaxSize().background(if (focused) Color(0xFF2A2A2A) else Surface), verticalAlignment = Alignment.CenterVertically) {
            if (coverUrl == null && icon == Icons.Default.Favorite) {
                Box(
                    Modifier.size(76.dp).background(Brush.linearGradient(listOf(accent.copy(alpha = 0.6f), Surface))),
                    contentAlignment = Alignment.Center,
                ) { Icon(icon, null, tint = Color.White, modifier = Modifier.size(28.dp)) }
            } else {
                Cover(
                    coverUrl,
                    Modifier.size(76.dp).then(if (round) Modifier.padding(8.dp) else Modifier),
                    shape = if (round) CircleShape else RoundedCornerShape(0.dp),
                    icon = icon,
                )
            }
            Text(
                title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 19.sp,
                modifier = Modifier.padding(horizontal = 12.dp),
            )
        }
    }
}

@Composable
private fun TvAlbumRow(
    title: String,
    keyPrefix: String,
    albums: List<MusicAlbum>,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    open: (String, () -> Unit) -> Unit,
) {
    if (albums.isEmpty()) return
    TvRowTitle(title)
    LazyRow(contentPadding = PaddingValues(horizontal = TvGutter), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
        items(albums, key = { it.id }) { album ->
            val key = "$keyPrefix/${album.id}"
            TvCoverCard(
                title = album.name, subtitle = album.artist, coverUrl = album.coverUrl, icon = Icons.Default.Album,
                focusKey = key, cardFocus = cardFocus, width = 170.dp,
            ) { open(key) { viewModel.openAlbum(album) } }
        }
    }
}

@Composable
private fun TvAlbumGrid(
    albums: List<MusicAlbum>,
    keyPrefix: String,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    open: (String, () -> Unit) -> Unit,
    header: (@Composable () -> Unit)? = null,
) {
    if (albums.isEmpty() && header == null) {
        TvMessage(stringResource(R.string.music_empty_library))
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(170.dp),
        state = rememberLazyGridState(),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = TvGutter, end = TvGutter, top = 12.dp, bottom = 48.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        if (header != null) item(span = { GridItemSpan(maxLineSpan) }) { header() }
        items(albums.size, key = { albums[it].id }) { index ->
            val album = albums[index]
            val key = "$keyPrefix/${album.id}"
            TvCoverCard(
                title = album.name, subtitle = album.artist, coverUrl = album.coverUrl, icon = Icons.Default.Album,
                focusKey = key, cardFocus = cardFocus,
            ) { open(key) { viewModel.openAlbum(album) } }
        }
    }
}

@Composable
private fun TvArtistGrid(
    artists: List<MusicArtist>,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    open: (String, () -> Unit) -> Unit,
) {
    if (artists.isEmpty()) {
        TvMessage(stringResource(R.string.music_empty_library))
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        state = rememberLazyGridState(),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = TvGutter, end = TvGutter, top = 12.dp, bottom = 48.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        items(artists.size, key = { artists[it].id }) { index ->
            val artist = artists[index]
            val key = "artist/" + artist.id
            TvCoverCard(
                title = artist.name,
                subtitle = artist.albumCount?.takeIf { it > 0 }?.let { "$it " + stringResource(R.string.music_tab_albums).lowercase() },
                coverUrl = artist.coverUrl, icon = Icons.Default.Person, round = true,
                focusKey = key, cardFocus = cardFocus,
            ) { open(key) { viewModel.openArtist(artist) } }
        }
    }
}

@Composable
private fun TvMine(
    state: MusicUiState,
    accent: Color,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    open: (String, () -> Unit) -> Unit,
) {
    val collections = state.collections
    LazyVerticalGrid(
        columns = GridCells.Adaptive(170.dp),
        state = rememberLazyGridState(),
        modifier = Modifier.fillMaxSize().testTag("tv-music-mine"),
        contentPadding = PaddingValues(start = TvGutter, end = TvGutter, top = 12.dp, bottom = 48.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        item(key = "liked") {
            TvCoverCard(
                title = stringResource(R.string.music_liked_songs),
                subtitle = pluralStringResource(R.plurals.music_track_count, collections.favoriteTracks.size, collections.favoriteTracks.size),
                coverUrl = null, icon = Icons.Default.Favorite, gradient = accent,
                focusKey = "mine/liked", cardFocus = cardFocus,
            ) { open("mine/liked") { viewModel.openPlaylist(MusicPage.LIKED) } }
        }
        items(collections.playlists.size, key = { "pl-" + collections.playlists[it].id }) { index ->
            val playlist = collections.playlists[index]
            val key = "mine/pl/" + playlist.id
            TvCoverCard(
                title = playlist.name,
                subtitle = pluralStringResource(R.plurals.music_track_count, playlist.tracks.size, playlist.tracks.size),
                coverUrl = playlist.tracks.firstNotNullOfOrNull { it.coverUrl }, icon = Icons.AutoMirrored.Filled.QueueMusic,
                focusKey = key, cardFocus = cardFocus,
            ) { open(key) { viewModel.openPlaylist(playlist.id) } }
        }
        if (collections.favoriteAlbums.isNotEmpty()) {
            item(key = "fav-title", span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    stringResource(R.string.music_favorite_albums), color = Color.White, fontSize = 22.sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp),
                )
            }
            items(collections.favoriteAlbums.size, key = { "fa-" + collections.favoriteAlbums[it].id }) { index ->
                val album = collections.favoriteAlbums[index]
                val key = "mine/fa/" + album.id
                TvCoverCard(
                    title = album.name, subtitle = album.artist, coverUrl = album.coverUrl, icon = Icons.Default.Album,
                    focusKey = key, cardFocus = cardFocus,
                ) { open(key) { viewModel.openAlbum(album) } }
            }
        }
        if (collections.isEmpty) item(key = "empty", span = { GridItemSpan(maxLineSpan) }) {
            Text(stringResource(R.string.music_mine_empty), color = Muted, fontSize = 16.sp, lineHeight = 22.sp, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
private fun TvSearch(
    state: MusicUiState,
    playback: MusicPlaybackState,
    accent: Color,
    viewModel: MusicActions,
    cardFocus: MutableMap<String, FocusRequester>,
    open: (String, () -> Unit) -> Unit,
    onTrackMenu: (MusicTrack, List<TvChoice>) -> Unit,
) {
    val fieldFocus = remember { FocusRequester() }
    Column(Modifier.fillMaxSize()) {
        TvTextField(
            value = state.query,
            onValue = viewModel::setQuery,
            label = "",
            placeholder = stringResource(R.string.music_search_hint),
            accent = accent,
            leading = Icons.Default.Search,
            keyboard = KeyboardOptions(imeAction = ImeAction.Search),
            modifier = Modifier.widthIn(max = 640.dp).fillMaxWidth().padding(horizontal = TvGutter, vertical = 8.dp)
                .focusRequester(fieldFocus).testTag("tv-music-search"),
        )
        val results = state.results
        when {
            state.query.isBlank() -> Unit
            results == null -> LoadingBox()
            results.isEmpty && !state.searching -> TvMessage(stringResource(R.string.music_no_results, state.query.trim()))
            else -> {
                val order = remember(results, state.query) { MusicSearchRanking.order(state.query, results) }
                LazyColumn(Modifier.fillMaxSize().testTag("tv-music-search-results"), contentPadding = PaddingValues(bottom = 48.dp)) {
                    order.forEach { section ->
                        when (section) {
                            SearchSection.ARTISTS -> if (results.artists.isNotEmpty()) item(key = "artists") {
                                TvRowTitle(stringResource(R.string.music_tab_artists))
                                LazyRow(contentPadding = PaddingValues(horizontal = TvGutter), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                                    items(results.artists, key = { "a-" + it.id }) { artist ->
                                        val key = "search-artist/" + artist.id
                                        TvCoverCard(
                                            title = artist.name, subtitle = null, coverUrl = artist.coverUrl, icon = Icons.Default.Person,
                                            round = true, focusKey = key, cardFocus = cardFocus, width = 150.dp,
                                        ) { open(key) { viewModel.openArtist(artist) } }
                                    }
                                }
                            }
                            SearchSection.ALBUMS -> if (results.albums.isNotEmpty()) item(key = "albums") {
                                TvAlbumRow(stringResource(R.string.music_tab_albums), "search-album", results.albums, viewModel, cardFocus, open)
                            }
                            SearchSection.PLAYLISTS -> if (results.playlists.isNotEmpty()) item(key = "playlists") {
                                TvAlbumRow(stringResource(R.string.music_playlists), "search-playlist", results.playlists, viewModel, cardFocus, open)
                            }
                            SearchSection.TRACKS -> if (results.tracks.isNotEmpty()) {
                                item(key = "tracks-title") { TvRowTitle(stringResource(R.string.music_songs)) }
                                itemsIndexed(results.tracks, key = { _, t -> "t-" + t.id }) { index, track ->
                                    TvTrackRow(
                                        track = track, playing = playback.current?.id == track.id, isPlaying = playback.isPlaying,
                                        accent = accent, showCover = true,
                                        modifier = Modifier.padding(horizontal = TvGutter - 12.dp),
                                        onMenu = { onTrackMenu(track, emptyList()) },
                                    ) { viewModel.play(results.tracks, index) }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------------------------
// Album, artist and playlist pages

/** The cover blurred and dimmed behind a page. */
@Composable
private fun PageBackdrop(coverUrl: String?) {
    if (coverUrl == null) return
    Box(Modifier.fillMaxSize()) {
        Cover(coverUrl, Modifier.fillMaxSize().blur(90.dp).graphicsLayer { alpha = 0.45f }, shape = RoundedCornerShape(0.dp), icon = Icons.Default.Album)
        Box(
            Modifier.fillMaxSize().background(
                Brush.horizontalGradient(listOf(Color.Black.copy(alpha = 0.55f), Color.Black.copy(alpha = 0.85f), Color.Black))
            )
        )
    }
}

/** Left column of a page: cover art, title, details and the page's buttons. */
@Composable
private fun PageHeader(
    cover: @Composable () -> Unit,
    title: String,
    subtitle: String?,
    onSubtitle: (() -> Unit)?,
    meta: String?,
    accent: Color,
    playFocus: FocusRequester,
    canPlay: Boolean,
    onPlay: () -> Unit,
    onShuffle: (() -> Unit)?,
    actions: @Composable () -> Unit,
) {
    Column(
        Modifier.width(340.dp).fillMaxHeight().verticalScroll(rememberScrollState())
            .padding(start = TvGutter, top = 12.dp, bottom = 32.dp),
    ) {
        cover()
        Spacer(Modifier.height(18.dp))
        Text(title, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 31.sp)
        if (subtitle != null) {
            if (onSubtitle != null) {
                TvFocusBox(shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(top = 4.dp), focusedScale = 1.03f, onClick = onSubtitle) { focused ->
                    Text(
                        subtitle, color = if (focused) Color.Black else accent, fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
                        maxLines = 1, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.background(if (focused) Color.White else Color.Transparent).padding(horizontal = 8.dp, vertical = 3.dp),
                    )
                }
            } else {
                Text(subtitle, color = Muted, fontSize = 17.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 4.dp))
            }
        }
        if (!meta.isNullOrEmpty()) Text(meta, color = Faint, fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
        Spacer(Modifier.height(18.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            TvPillButton(stringResource(R.string.music_play), Icons.Default.PlayArrow, accent, filled = true, enabled = canPlay,
                modifier = Modifier.focusRequester(playFocus), tag = "tv-music-play", onClick = onPlay)
            if (onShuffle != null) {
                TvPillButton(stringResource(R.string.music_shuffle), Icons.Default.Shuffle, accent, filled = false, enabled = canPlay, onClick = onShuffle)
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) { actions() }
    }
}

@Composable
private fun EnterPageFocus(target: FocusRequester) {
    LaunchedEffect(Unit) {
        delay(120)
        runCatching { target.requestFocus() }
    }
}

@Composable
private fun TvAlbumPage(
    page: MusicPage.Album,
    collections: MusicCollections,
    playback: MusicPlaybackState,
    accent: Color,
    viewModel: MusicActions,
    nowPlayingFocus: FocusRequester,
    onTrackMenu: (MusicTrack, List<TvChoice>) -> Unit,
) {
    val album = page.album
    val playFocus = remember { FocusRequester() }
    EnterPageFocus(playFocus)
    Box(Modifier.fillMaxSize().testTag("tv-music-album")) {
        PageBackdrop(album.coverUrl)
        Row(Modifier.fillMaxSize()) {
            val count = page.tracks.size.takeIf { it > 0 } ?: album.trackCount
            PageHeader(
                cover = { Cover(album.coverUrl, Modifier.size(220.dp), shape = RoundedCornerShape(14.dp), icon = Icons.Default.Album) },
                title = album.name,
                subtitle = album.artist.takeIf { it.isNotBlank() },
                onSubtitle = if (album.artistId != null) ({ viewModel.openArtist(album.artistId, album.artist) }) else null,
                meta = listOfNotNull(album.year?.toString(), count?.let { pluralStringResource(R.plurals.music_track_count, it, it) }).joinToString(" · "),
                accent = accent,
                playFocus = playFocus,
                canPlay = page.tracks.isNotEmpty(),
                onPlay = { viewModel.play(page.tracks) },
                onShuffle = { viewModel.shuffle(page.tracks) },
            ) {
                val liked = collections.isFavorite(album)
                TvIconButton(if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    stringResource(if (liked) R.string.music_unlike else R.string.music_like), accent, active = liked) { viewModel.toggleFavorite(album) }
                val pinned = collections.isPinned(MusicPinType.ALBUM, album.id)
                TvIconButton(if (pinned) Icons.Default.PushPin else Icons.Outlined.PushPin,
                    stringResource(if (pinned) R.string.music_unpin else R.string.music_pin), accent, active = pinned) { viewModel.togglePin(MusicPin.of(album)) }
                TvIconButton(Icons.AutoMirrored.Filled.PlaylistAdd, stringResource(R.string.music_add_to_playlist), accent,
                    enabled = page.tracks.isNotEmpty()) { viewModel.showAddToPlaylist(page.tracks) }
            }
            Column(Modifier.weight(1f).fillMaxHeight().padding(start = 28.dp)) {
                PageTopRow(playback, accent, nowPlayingFocus, viewModel)
                when {
                    page.loading -> LoadingBox()
                    page.error != null -> TvMessage(stringResource(R.string.music_load_failed) + "\n" + page.error)
                    else -> LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(end = TvGutter - 12.dp, bottom = 48.dp)) {
                        itemsIndexed(page.tracks, key = { _, track -> track.id }) { index, track ->
                            TvTrackRow(
                                track = track, playing = playback.current?.id == track.id, isPlaying = playback.isPlaying,
                                accent = accent, number = track.trackNumber ?: (index + 1),
                                showArtist = track.artist.isNotBlank() && track.artist != album.artist,
                                onMenu = { onTrackMenu(track, emptyList()) },
                            ) { viewModel.play(page.tracks, index) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TvArtistPage(
    page: MusicPage.Artist,
    collections: MusicCollections,
    playback: MusicPlaybackState,
    accent: Color,
    viewModel: MusicActions,
    nowPlayingFocus: FocusRequester,
) {
    val playFocus = remember { FocusRequester() }
    EnterPageFocus(playFocus)
    // Artist pages open albums too; they come back to this page, so these cards are local.
    val localFocus = remember { mutableStateMapOf<String, FocusRequester>() }
    Box(Modifier.fillMaxSize().testTag("tv-music-artist")) {
        PageBackdrop(page.artist.coverUrl)
        Row(Modifier.fillMaxSize()) {
            PageHeader(
                cover = { Cover(page.artist.coverUrl, Modifier.size(200.dp), shape = CircleShape, icon = Icons.Default.Person) },
                title = page.artist.name,
                subtitle = null, onSubtitle = null,
                meta = page.albums.size.takeIf { it > 0 }?.let { "$it " + stringResource(R.string.music_tab_albums).lowercase() },
                accent = accent,
                playFocus = playFocus,
                canPlay = page.albums.isNotEmpty(),
                onPlay = { page.albums.firstOrNull()?.let(viewModel::playAlbum) },
                onShuffle = null,
            ) {
                val pinned = collections.isPinned(MusicPinType.ARTIST, page.artist.id)
                TvIconButton(if (pinned) Icons.Default.PushPin else Icons.Outlined.PushPin,
                    stringResource(if (pinned) R.string.music_unpin else R.string.music_pin), accent, active = pinned) { viewModel.togglePin(MusicPin.of(page.artist)) }
            }
            Column(Modifier.weight(1f).fillMaxHeight().padding(start = 12.dp)) {
                PageTopRow(playback, accent, nowPlayingFocus, viewModel)
                when {
                    page.loading -> LoadingBox()
                    page.error != null -> TvMessage(stringResource(R.string.music_load_failed) + "\n" + page.error)
                    else -> TvAlbumGrid(page.albums, "artist-album", viewModel, localFocus, { _, action -> action() })
                }
            }
        }
    }
}

@Composable
private fun TvPlaylistPage(
    page: MusicPage.Playlist,
    collections: MusicCollections,
    playback: MusicPlaybackState,
    accent: Color,
    viewModel: MusicActions,
    nowPlayingFocus: FocusRequester,
    onTrackMenu: (MusicTrack, List<TvChoice>) -> Unit,
) {
    val liked = page.id == MusicPage.LIKED
    val playlist = if (liked) null else collections.playlist(page.id)
    if (!liked && playlist == null) {
        TvMessage(stringResource(R.string.music_playlist_gone))
        return
    }
    val name = playlist?.name ?: stringResource(R.string.music_liked_songs)
    val tracks = playlist?.tracks ?: collections.favoriteTracks
    val coverUrl = if (liked) null else tracks.firstNotNullOfOrNull { it.coverUrl }
    val playFocus = remember { FocusRequester() }
    var deleting by remember { mutableStateOf(false) }
    val removeLabel = stringResource(R.string.music_remove_from_playlist)
    EnterPageFocus(playFocus)
    Box(Modifier.fillMaxSize().testTag("tv-music-playlist")) {
        PageBackdrop(coverUrl)
        Row(Modifier.fillMaxSize()) {
            PageHeader(
                cover = {
                    if (liked) {
                        Box(
                            Modifier.size(220.dp).clip(RoundedCornerShape(14.dp))
                                .background(Brush.linearGradient(listOf(accent.copy(alpha = 0.6f), Surface))),
                            contentAlignment = Alignment.Center,
                        ) { Icon(Icons.Default.Favorite, null, tint = Color.White, modifier = Modifier.size(84.dp)) }
                    } else {
                        Cover(coverUrl, Modifier.size(220.dp), shape = RoundedCornerShape(14.dp), icon = Icons.AutoMirrored.Filled.QueueMusic)
                    }
                },
                title = name,
                subtitle = null, onSubtitle = null,
                meta = pluralStringResource(R.plurals.music_track_count, tracks.size, tracks.size),
                accent = accent,
                playFocus = playFocus,
                canPlay = tracks.isNotEmpty(),
                onPlay = { viewModel.play(tracks) },
                onShuffle = { viewModel.shuffle(tracks) },
            ) {
                val pinned = collections.isPinned(MusicPinType.PLAYLIST, page.id)
                TvIconButton(if (pinned) Icons.Default.PushPin else Icons.Outlined.PushPin,
                    stringResource(if (pinned) R.string.music_unpin else R.string.music_pin), accent, active = pinned) {
                    viewModel.togglePin(playlist?.let { MusicPin.of(it) } ?: MusicPin(MusicPinType.PLAYLIST, page.id, name))
                }
                if (playlist != null) {
                    TvIconButton(Icons.Default.Delete, stringResource(R.string.music_delete_playlist), accent) { deleting = true }
                }
            }
            Column(Modifier.weight(1f).fillMaxHeight().padding(start = 28.dp)) {
                PageTopRow(playback, accent, nowPlayingFocus, viewModel)
                if (tracks.isEmpty()) {
                    TvMessage(stringResource(if (liked) R.string.music_liked_empty else R.string.music_playlist_empty))
                } else {
                    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(end = TvGutter - 12.dp, bottom = 48.dp)) {
                        itemsIndexed(tracks, key = { index, track -> "$index-${track.id}" }) { index, track ->
                            TvTrackRow(
                                track = track, playing = playback.current?.id == track.id, isPlaying = playback.isPlaying,
                                accent = accent, showCover = true,
                                onMenu = {
                                    val extra = if (playlist != null) {
                                        listOf(TvChoice(removeLabel, Icons.Default.Close) { viewModel.removeFromPlaylist(playlist.id, index) })
                                    } else emptyList()
                                    onTrackMenu(track, extra)
                                },
                            ) { viewModel.play(tracks, index) }
                        }
                    }
                }
            }
        }
    }
    if (deleting && playlist != null) {
        TvChoiceDialog(
            title = stringResource(R.string.music_delete_playlist),
            subtitle = stringResource(R.string.music_delete_playlist_confirm, playlist.name),
            choices = listOf(
                TvChoice(stringResource(R.string.music_delete), Icons.Default.Delete) { viewModel.deletePlaylist(playlist.id) },
                TvChoice(stringResource(R.string.music_cancel), Icons.Default.Close) {},
            ),
            onDismiss = { deleting = false },
        )
    }
}

/** Above a page's list: a back button and, while music plays, the now playing chip. */
@Composable
private fun PageTopRow(playback: MusicPlaybackState, accent: Color, nowPlayingFocus: FocusRequester, viewModel: MusicActions) {
    Row(
        Modifier.fillMaxWidth().padding(end = TvGutter, top = 6.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TvIconButton(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.music_back), accent, tag = "tv-music-back") { viewModel.back() }
        Spacer(Modifier.weight(1f))
        NowPlayingChip(playback, accent, nowPlayingFocus) { viewModel.setPlayerOpen(true) }
    }
}

// ---------------------------------------------------------------------------------------------
// Player

@Composable
private fun TvPlayer(
    track: MusicTrack,
    playback: MusicPlaybackState,
    state: MusicUiState,
    appAccent: Color,
    viewModel: MusicActions,
    onTrackMenu: (MusicTrack, List<TvChoice>) -> Unit,
) {
    val coverColors = rememberCoverColors(track.coverUrl)
    val accent = coverColors?.accent ?: appAccent
    val tint = coverColors?.background ?: Color.Black
    var lyricsOn by rememberSaveable { mutableStateOf(false) }
    var queueOpen by remember { mutableStateOf(false) }
    var sleepDialog by remember { mutableStateOf(false) }
    val playFocus = remember { FocusRequester() }
    val queueChipFocus = remember { FocusRequester() }
    val sleepFocus = remember { FocusRequester() }
    // After picking a time, focus goes back to the moon button instead of the seek bar.
    var sleepWasOpen by remember { mutableStateOf(false) }
    LaunchedEffect(sleepDialog) {
        if (sleepWasOpen && !sleepDialog) {
            delay(50)
            runCatching { sleepFocus.requestFocus() }
        }
        sleepWasOpen = sleepDialog
    }
    val closeQueue = { queueOpen = false }
    // Once the controls can take focus again, it goes back to the Queue chip.
    var queueWasOpen by remember { mutableStateOf(false) }
    LaunchedEffect(queueOpen) {
        if (queueWasOpen && !queueOpen) {
            delay(50)
            runCatching { queueChipFocus.requestFocus() }
        }
        queueWasOpen = queueOpen
    }
    LaunchedEffect(lyricsOn, track.id) { if (lyricsOn) viewModel.loadLyrics(track) }
    LaunchedEffect(Unit) {
        delay(150)
        runCatching { playFocus.requestFocus() }
    }
    val appear = remember { MutableTransitionState(false) }.apply { targetState = true }
    // A BackHandler, not a key handler: with predictive back (Android 13+) the Back key never
    // reaches compose key events. Back closes the queue first, then the player.
    BackHandler { if (queueOpen) closeQueue() else viewModel.setPlayerOpen(false) }

    // The cover breathes a little while the music plays.
    val coverScale by animateFloatAsState(
        if (playback.isPlaying) 1f else 0.94f,
        spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow),
        label = "tv-cover-scale",
    )

    AnimatedVisibility(
        visibleState = appear,
        enter = fadeIn(tween(260)) + scaleIn(tween(320, easing = FastOutSlowInEasing), initialScale = 0.94f),
    ) {
    Box(
        Modifier.fillMaxSize().background(Color.Black).testTag("tv-music-player")
            .onPreviewKeyEvent { event ->
                // The media keys work here as well (Back is handled by the BackHandler above).
                when (event.key) {
                    Key.MediaPlayPause, Key.MediaPlay, Key.MediaPause -> { if (event.type == KeyEventType.KeyDown) viewModel.togglePlay(); true }
                    Key.MediaNext, Key.MediaFastForward -> { if (event.type == KeyEventType.KeyDown) viewModel.next(); true }
                    Key.MediaPrevious, Key.MediaRewind -> { if (event.type == KeyEventType.KeyDown) viewModel.previous(); true }
                    else -> false
                }
            },
    ) {
        Cover(track.coverUrl, Modifier.fillMaxSize().blur(100.dp), shape = RoundedCornerShape(0.dp), icon = Icons.Default.MusicNote)
        Box(
            Modifier.fillMaxSize().background(
                Brush.horizontalGradient(listOf(tint.copy(alpha = 0.55f), tint.copy(alpha = 0.8f), Color.Black.copy(alpha = 0.92f)))
            )
        )
        // While the queue is open the controls behind it take no focus, so the remote stays in the panel.
        CompositionLocalProvider(LocalTvFocusEnabled provides !queueOpen) {
        Row(
            Modifier.fillMaxSize().padding(horizontal = 72.dp, vertical = 56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AnimatedContent(
                targetState = track,
                contentKey = { it.id },
                transitionSpec = { (fadeIn(tween(300)) + scaleIn(tween(300), initialScale = 0.92f)) togetherWith fadeOut(tween(200)) },
                label = "tv-player-cover",
            ) { shown ->
                Cover(
                    shown.coverUrl,
                    Modifier.size(340.dp).graphicsLayer { scaleX = coverScale; scaleY = coverScale },
                    shape = RoundedCornerShape(20.dp),
                    icon = Icons.Default.MusicNote,
                )
            }
            Spacer(Modifier.width(56.dp))
            Column(Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                if (lyricsOn) {
                    LyricsPanel(
                        lyrics = state.lyrics?.takeIf { it.trackId == track.id },
                        positionMs = playback.positionMs,
                        accent = accent,
                        onSeek = viewModel::seekTo,
                        modifier = Modifier.weight(1f).fillMaxWidth(),
                        tv = true,
                    )
                    Spacer(Modifier.height(12.dp))
                }
                Text(
                    track.title, color = Color.White, fontSize = if (lyricsOn) 24.sp else 38.sp, fontWeight = FontWeight.Bold,
                    maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = if (lyricsOn) 29.sp else 44.sp,
                )
                Text(
                    listOf(track.artist, track.album).filter { it.isNotBlank() }.joinToString(" · "),
                    color = Muted, fontSize = if (lyricsOn) 16.sp else 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 6.dp),
                )
                Spacer(Modifier.height(if (lyricsOn) 14.dp else 28.dp))
                sleepLabel(playback)?.let { left ->
                    Row(Modifier.padding(bottom = 8.dp).testTag("tv-music-sleep-left"), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Bedtime, contentDescription = null, tint = accent, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(left, color = Muted, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
                TvSeekBar(playback, accent, viewModel::seekTo)
                Spacer(Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    TvIconButton(Icons.Default.Shuffle, stringResource(R.string.music_shuffle), accent, active = playback.shuffle, tag = "tv-music-shuffle") { viewModel.toggleShuffle() }
                    TvIconButton(Icons.Default.SkipPrevious, stringResource(R.string.music_previous), accent, size = 56.dp) { viewModel.previous() }
                    TvPlayPauseButton(playback, accent, Modifier.focusRequester(playFocus)) { viewModel.togglePlay() }
                    TvIconButton(Icons.Default.SkipNext, stringResource(R.string.music_next), accent, size = 56.dp, tag = "tv-music-next") { viewModel.next() }
                    TvIconButton(
                        if (playback.repeatMode == Player.REPEAT_MODE_ONE) Icons.Default.RepeatOne else Icons.Default.Repeat,
                        stringResource(R.string.music_repeat), accent, active = playback.repeatMode != Player.REPEAT_MODE_OFF,
                    ) { viewModel.cycleRepeat() }
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    Modifier.horizontalScroll(rememberScrollState()).padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val liked = state.collections.isFavorite(track)
                    TvIconButton(if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        stringResource(if (liked) R.string.music_unlike else R.string.music_like), accent, active = liked) { viewModel.toggleFavorite(track) }
                    TvChip(stringResource(R.string.music_lyrics), Icons.Default.Lyrics, selected = lyricsOn, accent = accent, tag = "tv-music-lyrics") { lyricsOn = !lyricsOn }
                    TvChip(stringResource(R.string.music_queue), Icons.AutoMirrored.Filled.QueueMusic, selected = false, accent = accent,
                        modifier = Modifier.focusRequester(queueChipFocus), tag = "tv-music-queue") { queueOpen = true }
                    // Always just the moon, so the whole row fits; it lights up while a timer runs and the time
                    // left shows above the seek bar.
                    TvIconButton(Icons.Default.Bedtime, stringResource(R.string.music_sleep_timer), accent, active = playback.sleepTimerOn,
                        modifier = Modifier.focusRequester(sleepFocus), tag = "tv-music-sleep") { sleepDialog = true }
                    TvIconButton(Icons.Default.MoreVert, stringResource(R.string.music_more), accent) { onTrackMenu(track, emptyList()) }
                }
                if (!lyricsOn) {
                    val upNext = playback.upNext.take(2).mapNotNull { playback.queue.getOrNull(it) }
                    if (upNext.isNotEmpty()) {
                        Spacer(Modifier.height(28.dp))
                        Text(stringResource(R.string.music_up_next), color = Faint, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        upNext.forEach { next ->
                            Text(
                                next.title + (if (next.artist.isNotBlank()) "  ·  " + next.artist else ""),
                                color = Muted, fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(top = 6.dp),
                            )
                        }
                    }
                }
            }
        }

        }

        AnimatedVisibility(
            visible = queueOpen,
            enter = slideInHorizontally(tween(300, easing = FastOutSlowInEasing)) { it } + fadeIn(tween(200)),
            exit = slideOutHorizontally(tween(240)) { it } + fadeOut(tween(200)),
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            TvQueuePanel(playback, accent, viewModel) { closeQueue() }
        }
        if (sleepDialog) {
            val choices = buildList {
                listOf(15, 30, 45, 60, 90).forEach { minutes ->
                    add(TvChoice(stringResource(R.string.music_sleep_minutes, minutes), Icons.Default.Bedtime) { viewModel.setSleepTimer(minutes) })
                }
                add(TvChoice(stringResource(R.string.music_sleep_end_of_track), Icons.Default.MusicNote) { viewModel.sleepAtEndOfTrack() })
                if (playback.sleepTimerOn) add(TvChoice(stringResource(R.string.music_sleep_off), Icons.Default.Close) { viewModel.cancelSleepTimer() })
            }
            TvChoiceDialog(stringResource(R.string.music_sleep_timer), choices = choices, onDismiss = { sleepDialog = false })
        }
    }
    }
}

@Composable
private fun TvQueuePanel(playback: MusicPlaybackState, accent: Color, viewModel: MusicActions, onClose: () -> Unit) {
    val firstFocus = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        delay(120)
        runCatching { firstFocus.requestFocus() }
    }
    val upNext = playback.upNext
    Column(
        Modifier.width(520.dp).fillMaxHeight().background(Color(0xF20B0B0B)).padding(top = 40.dp).testTag("tv-music-queue-panel"),
    ) {
        Text(stringResource(R.string.music_queue), color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 32.dp))
        playback.current?.let { now ->
            Text(stringResource(R.string.music_now_playing), color = Faint, fontSize = 14.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 32.dp, top = 18.dp, bottom = 4.dp))
            Row(Modifier.padding(horizontal = 32.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                Cover(now.coverUrl, Modifier.size(52.dp), shape = RoundedCornerShape(6.dp), icon = Icons.Default.MusicNote)
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(now.title, color = accent, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(now.artist, color = Faint, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Equalizer(playback.isPlaying, accent)
            }
        }
        Text(stringResource(R.string.music_up_next), color = Faint, fontSize = 14.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 32.dp, top = 18.dp, bottom = 4.dp))
        if (upNext.isEmpty()) {
            Text(stringResource(R.string.music_queue_empty), color = Muted, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp))
            // Something must hold focus while the panel is open.
            TvPillButton(stringResource(R.string.music_close), Icons.Default.Close, accent, filled = false, enabled = true,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 12.dp).focusRequester(firstFocus), onClick = onClose)
        } else {
            LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 40.dp)) {
                itemsIndexed(upNext, key = { position, index -> "q-$position-" + (playback.queue.getOrNull(index)?.id ?: "") }) { position, index ->
                    val track = playback.queue.getOrNull(index) ?: return@itemsIndexed
                    TvTrackRow(
                        track = track, playing = false, isPlaying = false, accent = accent, showCover = true,
                        rowModifier = if (position == 0) Modifier.focusRequester(firstFocus) else Modifier,
                        menuIcon = Icons.Default.Close,
                        menuLabel = stringResource(R.string.music_remove_from_queue),
                        onMenu = { viewModel.removeFromQueue(index) },
                    ) { viewModel.skipTo(index) }
                }
            }
        }
    }
}

@Composable
private fun TvSeekBar(playback: MusicPlaybackState, accent: Color, onSeek: (Long) -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val duration = playback.durationMs.coerceAtLeast(1L)
    val progress = (playback.positionMs.toFloat() / duration).coerceIn(0f, 1f)
    val height by animateFloatAsState(if (focused) 8f else 4f, tween(160), label = "tv-seek-height")
    Column(Modifier.fillMaxWidth().widthIn(max = 760.dp)) {
        Box(
            Modifier.fillMaxWidth().height(24.dp)
                .onFocusChanged { focused = it.isFocused }
                .onKeyEvent { event ->
                    if (event.type != KeyEventType.KeyDown) return@onKeyEvent false
                    when (event.key) {
                        Key.DirectionRight -> { onSeek((playback.positionMs + 10_000).coerceAtMost(playback.durationMs)); true }
                        Key.DirectionLeft -> { onSeek((playback.positionMs - 10_000).coerceAtLeast(0)); true }
                        else -> false
                    }
                }
                .focusable(enabled = LocalTvFocusEnabled.current)
                .testTag("tv-music-seek"),
            contentAlignment = Alignment.CenterStart,
        ) {
            Box(Modifier.fillMaxWidth().height(height.dp).clip(RoundedCornerShape(4.dp)).background(Color(0x33EDEDED)))
            Box(Modifier.fillMaxWidth(progress).height(height.dp).clip(RoundedCornerShape(4.dp)).background(if (focused) Color.White else accent))
            if (focused) {
                Box(Modifier.fillMaxWidth(progress), contentAlignment = Alignment.CenterEnd) {
                    Box(Modifier.size(18.dp).clip(CircleShape).background(Color.White))
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Text(formatTime(playback.positionMs), color = Faint, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text(formatTime(playback.durationMs), color = Faint, fontSize = 14.sp)
        }
    }
}

@Composable
private fun TvPlayPauseButton(playback: MusicPlaybackState, accent: Color, modifier: Modifier, onClick: () -> Unit) {
    TvFocusBox(shape = CircleShape, modifier = modifier.size(84.dp), focusedScale = 1.1f, ring = false, tag = "tv-music-playpause", onClick = onClick) { focused ->
        Box(Modifier.fillMaxSize().background(if (focused) Color.White else accent), contentAlignment = Alignment.Center) {
            val content = if (focused) Color.Black else contentOn(accent)
            if (playback.isBuffering && !playback.isPlaying) {
                CircularProgressIndicator(color = content, strokeWidth = 3.dp, modifier = Modifier.size(34.dp))
            } else {
                Icon(
                    if (playback.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    stringResource(if (playback.isPlaying) R.string.music_pause else R.string.music_play),
                    tint = content, modifier = Modifier.size(46.dp),
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------------------------
// Building blocks

/**
 * A focus target that grows a little and gets a white ring when focused, the way the rest of the
 * TV app shows focus. Registers itself under [focusKey] so a page can hand focus back to it.
 */
@Composable
private fun TvFocusBox(
    shape: Shape,
    modifier: Modifier = Modifier,
    focusedScale: Float = 1.06f,
    ring: Boolean = true,
    enabled: Boolean = true,
    focusKey: String? = null,
    cardFocus: MutableMap<String, FocusRequester>? = null,
    tag: String? = null,
    onFocus: (Boolean) -> Unit = {},
    onClick: () -> Unit,
    content: @Composable BoxScope.(focused: Boolean) -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        if (focused) focusedScale else 1f,
        spring(dampingRatio = 0.75f, stiffness = Spring.StiffnessMediumLow),
        label = "tv-focus-scale",
    )
    val requester = remember { FocusRequester() }
    if (focusKey != null && cardFocus != null) {
        DisposableEffect(focusKey) {
            cardFocus[focusKey] = requester
            onDispose { if (cardFocus[focusKey] === requester) cardFocus.remove(focusKey) }
        }
    }
    Box(
        modifier
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .then(if (ring && focused) Modifier.border(2.5.dp, FocusRing, shape) else Modifier)
            .clip(shape)
            .focusRequester(requester)
            .onFocusChanged { if (focused != it.isFocused) { focused = it.isFocused; onFocus(it.isFocused) } }
            .clickable(
                enabled = enabled && LocalTvFocusEnabled.current,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .then(if (tag != null) Modifier.testTag(tag) else Modifier),
    ) { content(focused) }
}

@Composable
private fun TvCoverCard(
    title: String,
    subtitle: String?,
    coverUrl: String?,
    icon: ImageVector,
    focusKey: String,
    cardFocus: MutableMap<String, FocusRequester>,
    width: Dp? = null,
    round: Boolean = false,
    gradient: Color? = null,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    Column(if (width != null) Modifier.width(width) else Modifier.fillMaxWidth()) {
        val shape = if (round) CircleShape else RoundedCornerShape(12.dp)
        TvFocusBox(
            shape = shape,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            focusKey = focusKey, cardFocus = cardFocus,
            onFocus = { focused = it },
            onClick = onClick,
        ) {
            if (gradient != null) {
                Box(
                    Modifier.fillMaxSize().background(Brush.linearGradient(listOf(gradient.copy(alpha = 0.65f), Surface))),
                    contentAlignment = Alignment.Center,
                ) { Icon(icon, null, tint = Color.White, modifier = Modifier.size(56.dp)) }
            } else {
                Cover(coverUrl, Modifier.fillMaxSize(), shape = shape, icon = icon)
            }
        }
        Text(
            title, color = if (focused) Color.White else Color.White.copy(alpha = 0.88f), fontSize = 15.sp,
            fontWeight = if (focused) FontWeight.Bold else FontWeight.SemiBold,
            maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 10.dp),
        )
        if (!subtitle.isNullOrBlank()) {
            Text(subtitle, color = Faint, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun TvTrackRow(
    track: MusicTrack,
    playing: Boolean,
    isPlaying: Boolean,
    accent: Color,
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    number: Int? = null,
    showCover: Boolean = false,
    showArtist: Boolean = true,
    menuIcon: ImageVector = Icons.Default.MoreVert,
    menuLabel: String? = null,
    onMenu: (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Row(modifier.fillMaxWidth().padding(vertical = 3.dp), verticalAlignment = Alignment.CenterVertically) {
        TvFocusBox(
            shape = RoundedCornerShape(10.dp),
            modifier = rowModifier.weight(1f),
            focusedScale = 1.015f,
            ring = false,
            onClick = onClick,
        ) { focused ->
            Row(
                Modifier.fillMaxWidth().background(if (focused) Color.White else Color.Transparent)
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val main = if (focused) Color.Black else if (playing) accent else Color.White
                val sub = if (focused) Color.Black.copy(alpha = 0.6f) else Faint
                if (showCover) {
                    Box(contentAlignment = Alignment.Center) {
                        Cover(track.coverUrl, Modifier.size(48.dp), shape = RoundedCornerShape(6.dp), icon = Icons.Default.MusicNote)
                        if (playing) Box(Modifier.size(48.dp).clip(RoundedCornerShape(6.dp)).background(Color.Black.copy(alpha = 0.55f)), contentAlignment = Alignment.Center) {
                            Equalizer(isPlaying, accent)
                        }
                    }
                    Spacer(Modifier.width(14.dp))
                } else if (number != null) {
                    Box(Modifier.width(40.dp)) {
                        if (playing) Equalizer(isPlaying, if (focused) Color.Black else accent)
                        else Text(number.toString(), color = sub, fontSize = 16.sp)
                    }
                }
                Column(Modifier.weight(1f)) {
                    Text(track.title, color = main, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    if (showArtist && track.artist.isNotBlank()) {
                        Text(
                            if (showCover && track.album.isNotBlank()) "${track.artist} · ${track.album}" else track.artist,
                            color = sub, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                if (track.durationSec > 0) {
                    Text(formatTime(track.durationSec * 1000L), color = sub, fontSize = 15.sp, modifier = Modifier.padding(start = 12.dp))
                }
            }
        }
        if (onMenu != null) {
            Spacer(Modifier.width(6.dp))
            TvIconButton(menuIcon, menuLabel ?: stringResource(R.string.music_more), accent, size = 44.dp, onClick = onMenu)
        }
    }
}

/** Three bars that bounce while the song plays, and rest while it is paused. */
@Composable
private fun Equalizer(playing: Boolean, color: Color) {
    val transition = rememberInfiniteTransition(label = "tv-eq")
    val bars = listOf(520, 380, 640).map { duration ->
        transition.animateFloat(
            initialValue = 0.25f, targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(duration, easing = LinearEasing), RepeatMode.Reverse),
            label = "tv-eq-bar",
        )
    }
    Row(Modifier.height(18.dp), horizontalArrangement = Arrangement.spacedBy(3.dp), verticalAlignment = Alignment.Bottom) {
        bars.forEach { bar ->
            val fraction = if (playing) bar.value else 0.3f
            Box(Modifier.width(4.dp).fillMaxHeight(fraction).clip(RoundedCornerShape(1.dp)).background(color))
        }
    }
}

/** The song playing now, at the top right of the tab; OK opens the player. */
@Composable
private fun NowPlayingChip(playback: MusicPlaybackState, accent: Color, focus: FocusRequester, onOpen: () -> Unit) {
    val track = playback.current
    AnimatedVisibility(visible = track != null, enter = fadeIn(tween(240)) + scaleIn(tween(240), initialScale = 0.9f), exit = fadeOut(tween(160))) {
        if (track == null) return@AnimatedVisibility
        TvFocusBox(
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier.focusRequester(focus).widthIn(max = 380.dp),
            focusedScale = 1.04f,
            ring = false,
            tag = "tv-music-now-playing",
            onClick = onOpen,
        ) { focused ->
            Row(
                Modifier.background(if (focused) Color.White else BackgroundElevated).padding(start = 6.dp, end = 16.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Cover(track.coverUrl, Modifier.size(40.dp), shape = CircleShape, icon = Icons.Default.MusicNote)
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f, fill = false)) {
                    Text(track.title, color = if (focused) Color.Black else Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                        maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(track.artist, color = if (focused) Color.Black.copy(alpha = 0.6f) else Faint, fontSize = 12.sp,
                        maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Spacer(Modifier.width(10.dp))
                Equalizer(playback.isPlaying, if (focused) Color.Black else accent)
            }
        }
    }
}

@Composable
private fun TvChip(
    label: String,
    icon: ImageVector? = null,
    selected: Boolean,
    accent: Color,
    modifier: Modifier = Modifier,
    tag: String? = null,
    onFocus: () -> Unit = {},
    onClick: () -> Unit,
) {
    TvFocusBox(
        shape = RoundedCornerShape(22.dp), modifier = modifier, focusedScale = 1.05f, ring = false, tag = tag,
        onFocus = { if (it) onFocus() }, onClick = onClick,
    ) { focused ->
        val background = when {
            focused -> Color.White
            selected -> Color.White.copy(alpha = 0.26f)
            else -> Color.Transparent
        }
        val content = when {
            focused -> Color.Black
            selected -> Color.White
            else -> Color.White.copy(alpha = 0.62f)
        }
        Row(
            Modifier.background(background)
                .then(if (!focused && !selected) Modifier.border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(22.dp)) else Modifier)
                .padding(horizontal = 18.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(icon, null, tint = content, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
            }
            Text(label, color = content, fontSize = 15.sp, fontWeight = if (selected || focused) FontWeight.Bold else FontWeight.SemiBold, maxLines = 1)
        }
    }
}

@Composable
private fun TvIconButton(
    icon: ImageVector,
    description: String,
    accent: Color,
    active: Boolean = false,
    enabled: Boolean = true,
    size: Dp = 48.dp,
    modifier: Modifier = Modifier,
    tag: String? = null,
    onClick: () -> Unit,
) {
    TvFocusBox(shape = CircleShape, modifier = modifier.size(size), focusedScale = 1.12f, ring = false, enabled = enabled, tag = tag, onClick = onClick) { focused ->
        Box(
            Modifier.fillMaxSize().background(if (focused) Color.White else Color(0x1FFFFFFF)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                icon, description,
                tint = when {
                    focused -> Color.Black
                    !enabled -> Color.White.copy(alpha = 0.3f)
                    active -> accent
                    else -> Color.White
                },
                modifier = Modifier.size(size * 0.5f),
            )
        }
    }
}

@Composable
private fun TvPillButton(
    label: String,
    icon: ImageVector,
    accent: Color,
    filled: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    tag: String? = null,
    onClick: () -> Unit,
) {
    TvFocusBox(shape = RoundedCornerShape(26.dp), modifier = modifier, focusedScale = 1.06f, ring = false, enabled = enabled, tag = tag, onClick = onClick) { focused ->
        val background = when {
            focused -> Color.White
            filled -> accent.copy(alpha = if (enabled) 1f else 0.35f)
            else -> Color(0x26FFFFFF)
        }
        val content = when {
            focused -> Color.Black
            filled -> contentOn(accent)
            else -> Color.White
        }
        Row(Modifier.background(background).padding(horizontal = 24.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = content, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(8.dp))
            Text(label, color = content, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun TvRowTitle(text: String) {
    Text(
        text, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = TvGutter, top = 22.dp, bottom = 12.dp),
    )
}

@Composable
private fun TvMessage(text: String, action: String? = null, accent: Color = Color.White, onAction: () -> Unit = {}) {
    Column(Modifier.fillMaxWidth().padding(horizontal = TvGutter, vertical = 40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text, color = Muted, fontSize = 17.sp, lineHeight = 24.sp)
        if (action != null) {
            Spacer(Modifier.height(18.dp))
            TvPillButton(action, Icons.Default.Repeat, accent, filled = true, enabled = true, onClick = onAction)
        }
    }
}

/** A list of choices over the screen; OK picks one, Back closes it. */
@Composable
private fun TvChoiceDialog(
    title: String?,
    subtitle: String? = null,
    choices: List<TvChoice>,
    onDismiss: () -> Unit,
    closeOnPick: Boolean = true,
) {
    val first = remember { FocusRequester() }
    Dialog(onDismissRequest = onDismiss) {
        LaunchedEffect(Unit) {
            delay(80)
            runCatching { first.requestFocus() }
        }
        Column(
            Modifier.widthIn(min = 360.dp, max = 520.dp).clip(RoundedCornerShape(18.dp)).background(BackgroundElevated)
                .padding(vertical = 18.dp, horizontal = 14.dp).testTag("tv-music-dialog"),
        ) {
            if (title != null) {
                Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp))
            }
            if (subtitle != null) {
                Text(subtitle, color = Muted, fontSize = 15.sp, lineHeight = 21.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
            }
            if (title != null || subtitle != null) Spacer(Modifier.height(8.dp))
            LazyColumn(Modifier.fillMaxWidth()) {
                itemsIndexed(choices) { index, choice ->
                    TvFocusBox(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp).then(if (index == 0) Modifier.focusRequester(first) else Modifier),
                        focusedScale = 1.02f,
                        ring = false,
                        onClick = {
                            choice.onClick()
                            if (closeOnPick) onDismiss()
                        },
                    ) { focused ->
                        Row(
                            Modifier.fillMaxWidth().background(if (focused) Color.White else Color.Transparent).padding(horizontal = 14.dp, vertical = 13.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (choice.icon != null) {
                                Icon(choice.icon, null, tint = if (focused) Color.Black else Muted, modifier = Modifier.size(22.dp))
                                Spacer(Modifier.width(14.dp))
                            }
                            Text(choice.label, color = if (focused) Color.Black else Color.White, fontSize = 17.sp,
                                fontWeight = FontWeight.Medium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------------------------
// Adding a source with the remote

private enum class TvSourceKind { ADDON, SUBSONIC, JELLYFIN }

@Composable
private fun TvConnectView(
    state: MusicUiState,
    accent: Color,
    onConnect: (MusicServerType, String, String, String) -> Unit,
    onAddAddon: (String) -> Unit,
    onClose: (() -> Unit)?,
) {
    var kind by rememberSaveable { mutableStateOf(TvSourceKind.ADDON) }
    var url by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isAddon = kind == TvSourceKind.ADDON
    val connecting = if (isAddon) state.addingAddon else state.connecting
    val error = if (isAddon) state.addonError?.let { stringResource(R.string.music_addon_failed, it) }
    else state.connectError?.let { stringResource(R.string.music_connect_failed, it) }
    val canConnect = url.isNotBlank() && (isAddon || username.isNotBlank()) && !connecting
    val submit = {
        if (canConnect) {
            when (kind) {
                TvSourceKind.ADDON -> onAddAddon(url)
                TvSourceKind.SUBSONIC -> onConnect(MusicServerType.SUBSONIC, url, username, password)
                TvSourceKind.JELLYFIN -> onConnect(MusicServerType.JELLYFIN, url, username, password)
            }
        }
    }
    val firstFocus = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        delay(120)
        runCatching { firstFocus.requestFocus() }
    }
    Row(Modifier.fillMaxSize().padding(horizontal = 72.dp, vertical = 24.dp).testTag("tv-music-connect")) {
        Column(Modifier.weight(1f).padding(end = 48.dp, top = 24.dp)) {
            Box(Modifier.size(84.dp).clip(CircleShape).background(accent.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.LibraryMusic, null, tint = accent, modifier = Modifier.size(42.dp))
            }
            Spacer(Modifier.height(22.dp))
            Text(stringResource(R.string.music_connect_title), color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text(stringResource(R.string.music_connect_body), color = Muted, fontSize = 17.sp, lineHeight = 25.sp)
            Spacer(Modifier.height(16.dp))
            Text(stringResource(R.string.music_tv_sources_hint), color = Faint, fontSize = 15.sp, lineHeight = 21.sp)
        }
        Column(Modifier.weight(1f).padding(top = 24.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                if (onClose != null) TvIconButton(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.music_back), accent, onClick = onClose)
                listOf(
                    TvSourceKind.ADDON to R.string.music_source_addon,
                    TvSourceKind.SUBSONIC to R.string.music_server_subsonic_short,
                    TvSourceKind.JELLYFIN to R.string.music_server_jellyfin,
                ).forEach { (option, label) ->
                    TvChip(
                        stringResource(label), selected = option == kind, accent = accent,
                        modifier = if (option == TvSourceKind.ADDON) Modifier.focusRequester(firstFocus) else Modifier,
                    ) { kind = option }
                }
            }
            Spacer(Modifier.height(18.dp))
            TvTextField(
                value = url, onValue = { url = it },
                label = stringResource(if (isAddon) R.string.music_addon_url else R.string.music_server_url),
                placeholder = stringResource(if (isAddon) R.string.music_addon_url_hint else R.string.music_server_url_hint),
                accent = accent,
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Uri, imeAction = if (isAddon) ImeAction.Done else ImeAction.Next, autoCorrectEnabled = false),
                onDone = submit,
            )
            if (!isAddon) {
                Spacer(Modifier.height(12.dp))
                TvTextField(
                    value = username, onValue = { username = it }, label = stringResource(R.string.music_username), accent = accent,
                    keyboard = KeyboardOptions(imeAction = ImeAction.Next, autoCorrectEnabled = false),
                )
                Spacer(Modifier.height(12.dp))
                TvTextField(
                    value = password, onValue = { password = it }, label = stringResource(R.string.music_password), accent = accent,
                    password = true,
                    keyboard = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    onDone = submit,
                )
            }
            if (error != null) {
                Spacer(Modifier.height(12.dp))
                Text(error, color = Color(0xFFE74C3C), fontSize = 15.sp)
            }
            Spacer(Modifier.height(20.dp))
            if (connecting) {
                CircularProgressIndicator(color = accent, strokeWidth = 2.dp, modifier = Modifier.size(28.dp))
            } else {
                TvPillButton(
                    stringResource(if (isAddon) R.string.music_add_addon else R.string.music_connect),
                    Icons.Default.LibraryMusic, accent, filled = true, enabled = canConnect, tag = "tv-music-connect-button",
                ) { submit() }
            }
        }
    }
}

@Composable
private fun TvTextField(
    value: String,
    onValue: (String) -> Unit,
    label: String,
    accent: Color,
    modifier: Modifier = Modifier.fillMaxWidth(),
    placeholder: String? = null,
    password: Boolean = false,
    leading: ImageVector? = null,
    keyboard: KeyboardOptions = KeyboardOptions.Default,
    onDone: () -> Unit = {},
) {
    val labelContent: (@Composable () -> Unit)? = if (label.isNotEmpty()) ({ Text(label) }) else null
    val placeholderContent: (@Composable () -> Unit)? = if (placeholder != null) ({ Text(placeholder, color = Faint) }) else null
    val leadingContent: (@Composable () -> Unit)? = if (leading != null) ({ Icon(leading, null, tint = Muted) }) else null
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onValue,
        // A text field keeps the D-pad for its cursor; Up and Down must still move on, or a remote
        // can never reach the button under it.
        modifier = modifier.onPreviewKeyEvent { event ->
            when (event.key) {
                Key.DirectionDown -> { if (event.type == KeyEventType.KeyDown) focusManager.moveFocus(FocusDirection.Down); true }
                Key.DirectionUp -> { if (event.type == KeyEventType.KeyDown) focusManager.moveFocus(FocusDirection.Up); true }
                else -> false
            }
        },
        singleLine = true,
        label = labelContent,
        placeholder = placeholderContent,
        leadingIcon = leadingContent,
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboard,
        keyboardActions = KeyboardActions(onDone = { onDone() }, onSearch = { onDone() }, onGo = { onDone() }),
        shape = RoundedCornerShape(12.dp),
        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color(0x33EDEDED),
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Muted,
            cursorColor = accent,
            focusedContainerColor = Surface,
            unfocusedContainerColor = Surface,
        ),
    )
}
