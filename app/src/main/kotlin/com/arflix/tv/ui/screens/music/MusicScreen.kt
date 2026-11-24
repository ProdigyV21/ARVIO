package com.arflix.tv.ui.screens.music

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.RepeatOne
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.music.AlbumShelf
import com.arflix.tv.music.MusicAlbum
import com.arflix.tv.music.MusicArtist
import com.arflix.tv.music.MusicPlaybackState
import com.arflix.tv.music.MusicServerType
import com.arflix.tv.music.MusicTrack
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.BackgroundElevated

private val Surface = Color(0xFF141414)
private val Muted = Color(0xB3EDEDED)
private val Faint = Color(0x80EDEDED)
private val MiniPlayerHeight = 64.dp

/**
 * The Music tab: the user's own music server (Navidrome and other Subsonic servers, or
 * Jellyfin), with a home of album shelves, albums, artists, search and a player.
 */
@Composable
fun MusicScreen(
    viewModel: MusicViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val playback by viewModel.playback.collectAsStateWithLifecycle()
    val accent = resolveAccentColor(fallback = Color.White)

    var addingSource by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(state.library) { addingSource = false }

    BackHandler {
        if (addingSource && state.hasSources) addingSource = false
        else if (!viewModel.back()) onBack()
    }

    Box(Modifier.fillMaxSize().background(Color.Black).testTag("music-screen")) {
        when {
            !state.configLoaded -> Unit
            !state.hasSources || addingSource -> ConnectView(
                state = state,
                accent = accent,
                onConnect = viewModel::connect,
                onAddAddon = viewModel::addAddon,
                onClose = if (state.hasSources) ({ addingSource = false }) else null,
            )
            else -> {
                val bottomPadding = LocalBottomBarInset.current +
                    (if (playback.current != null) MiniPlayerHeight + 8.dp else 0.dp) + 16.dp
                when (val page = state.pages.lastOrNull()) {
                    is MusicPage.Album -> AlbumPageView(page, playback, accent, bottomPadding, viewModel)
                    is MusicPage.Artist -> ArtistPageView(page, accent, bottomPadding, viewModel)
                    null -> LibraryView(state, playback, accent, bottomPadding, viewModel) { addingSource = true }
                }
            }
        }

        val current = playback.current
        if (current != null && state.hasSources && !addingSource) {
            MiniPlayer(
                track = current,
                playback = playback,
                accent = accent,
                onOpen = { viewModel.setPlayerOpen(true) },
                onToggle = viewModel::togglePlay,
                onNext = viewModel::next,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = LocalBottomBarInset.current + 8.dp, start = 8.dp, end = 8.dp),
            )
        }

        AnimatedVisibility(
            visible = state.playerOpen && current != null,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
        ) {
            if (current != null) FullPlayer(current, playback, accent, viewModel)
        }
    }
}

// ---------------------------------------------------------------------------------------------
// Connecting a server

/** How a source is added: an addon URL, or a login on the user's own server. */
private enum class SourceKind { ADDON, SUBSONIC, JELLYFIN }

@Composable
private fun ConnectView(
    state: MusicUiState,
    accent: Color,
    onConnect: (MusicServerType, String, String, String) -> Unit,
    onAddAddon: (String) -> Unit,
    onClose: (() -> Unit)?,
) {
    var kind by rememberSaveable { mutableStateOf(SourceKind.ADDON) }
    var url by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isAddon = kind == SourceKind.ADDON
    val connecting = if (isAddon) state.addingAddon else state.connecting
    val error = if (isAddon) state.addonError?.let { stringResource(R.string.music_addon_failed, it) }
    else state.connectError?.let { stringResource(R.string.music_connect_failed, it) }
    val canConnect = url.isNotBlank() && (isAddon || username.isNotBlank()) && !connecting
    val submit = {
        if (canConnect) {
            when (kind) {
                SourceKind.ADDON -> onAddAddon(url)
                SourceKind.SUBSONIC -> onConnect(MusicServerType.SUBSONIC, url, username, password)
                SourceKind.JELLYFIN -> onConnect(MusicServerType.JELLYFIN, url, username, password)
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(top = if (onClose != null) 8.dp else 48.dp, bottom = LocalBottomBarInset.current + 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (onClose != null) {
            Row(Modifier.fillMaxWidth()) {
                IconButton(onClick = onClose) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.music_back), tint = Color.White)
                }
            }
        }
        Box(
            Modifier.size(72.dp).clip(CircleShape).background(accent.copy(alpha = 0.14f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Default.LibraryMusic, null, tint = accent, modifier = Modifier.size(36.dp))
        }
        Spacer(Modifier.height(20.dp))
        Text(
            stringResource(R.string.music_connect_title),
            color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            stringResource(R.string.music_connect_body),
            color = Muted, fontSize = 14.sp, lineHeight = 20.sp,
            modifier = Modifier.widthIn(max = 420.dp),
        )
        Spacer(Modifier.height(24.dp))

        Column(Modifier.widthIn(max = 420.dp).fillMaxWidth()) {
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Surface).padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                listOf(
                    SourceKind.ADDON to R.string.music_source_addon,
                    SourceKind.SUBSONIC to R.string.music_server_subsonic_short,
                    SourceKind.JELLYFIN to R.string.music_server_jellyfin,
                ).forEach { (option, label) ->
                    val selected = option == kind
                    Box(
                        Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(9.dp))
                            .background(if (selected) accent else Color.Transparent)
                            .clickable { kind = option }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            stringResource(label),
                            color = if (selected) contentOn(accent) else Muted,
                            fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            MusicField(
                value = url, onValue = { url = it },
                label = stringResource(if (isAddon) R.string.music_addon_url else R.string.music_server_url),
                placeholder = stringResource(if (isAddon) R.string.music_addon_url_hint else R.string.music_server_url_hint),
                accent = accent,
                keyboard = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = if (isAddon) ImeAction.Done else ImeAction.Next,
                    autoCorrectEnabled = false,
                ),
                onDone = submit,
            )
            if (!isAddon) {
                Spacer(Modifier.height(12.dp))
                MusicField(
                    value = username, onValue = { username = it },
                    label = stringResource(R.string.music_username),
                    accent = accent,
                    keyboard = KeyboardOptions(imeAction = ImeAction.Next, autoCorrectEnabled = false),
                )
                Spacer(Modifier.height(12.dp))
                MusicField(
                    value = password, onValue = { password = it },
                    label = stringResource(R.string.music_password),
                    accent = accent,
                    password = true,
                    keyboard = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    onDone = submit,
                )
            }
            if (error != null) {
                Spacer(Modifier.height(12.dp))
                Text(error, color = Color(0xFFE74C3C), fontSize = 13.sp)
            }
            Spacer(Modifier.height(20.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(if (canConnect) accent else accent.copy(alpha = 0.3f))
                    .clickable(enabled = canConnect, onClick = submit)
                    .testTag("music-connect"),
                contentAlignment = Alignment.Center,
            ) {
                if (connecting) {
                    CircularProgressIndicator(color = contentOn(accent), strokeWidth = 2.dp, modifier = Modifier.size(22.dp))
                } else {
                    Text(
                        stringResource(if (isAddon) R.string.music_add_addon else R.string.music_connect),
                        color = contentOn(accent), fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun MusicField(
    value: String,
    onValue: (String) -> Unit,
    label: String,
    accent: Color,
    placeholder: String? = null,
    password: Boolean = false,
    keyboard: KeyboardOptions = KeyboardOptions.Default,
    onDone: () -> Unit = {},
    leading: ImageVector? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    val labelContent: (@Composable () -> Unit)? = if (label.isNotEmpty()) {
        { Text(label) }
    } else null
    val placeholderContent: (@Composable () -> Unit)? = if (placeholder != null) {
        { Text(placeholder, color = Faint) }
    } else null
    val leadingContent: (@Composable () -> Unit)? = if (leading != null) {
        { Icon(leading, null, tint = Muted) }
    } else null
    val trailingContent: (@Composable () -> Unit)? = if (leading != null && value.isNotEmpty()) {
        { IconButton(onClick = { onValue("") }) { Icon(Icons.Default.Close, null, tint = Muted) } }
    } else null
    OutlinedTextField(
        value = value,
        onValueChange = onValue,
        modifier = modifier,
        singleLine = true,
        label = labelContent,
        placeholder = placeholderContent,
        leadingIcon = leadingContent,
        trailingIcon = trailingContent,
        visualTransformation = if (password) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = keyboard,
        keyboardActions = KeyboardActions(onDone = { onDone() }, onSearch = { onDone() }),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = accent,
            unfocusedBorderColor = Color(0x33EDEDED),
            focusedLabelColor = accent,
            unfocusedLabelColor = Muted,
            cursorColor = accent,
            focusedContainerColor = Surface,
            unfocusedContainerColor = Surface,
        ),
    )
}

// ---------------------------------------------------------------------------------------------
// Library: home shelves, albums, artists, search

@Composable
private fun LibraryView(
    state: MusicUiState,
    playback: MusicPlaybackState,
    accent: Color,
    bottomPadding: Dp,
    viewModel: MusicViewModel,
    onAddSource: () -> Unit,
) {
    Column(Modifier.fillMaxSize().statusBarsPadding()) {
        Row(
            Modifier.fillMaxWidth().padding(start = 20.dp, end = 8.dp, top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                stringResource(R.string.nav_music),
                color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
            )
            var menu by remember { mutableStateOf(false) }
            Box {
                IconButton(onClick = { menu = true }) {
                    Icon(Icons.Default.MoreVert, stringResource(R.string.music_more), tint = Color.White)
                }
                DropdownMenu(expanded = menu, onDismissRequest = { menu = false }, containerColor = BackgroundElevated) {
                    state.config?.let { config ->
                        Text(
                            stringResource(R.string.music_connected_as, config.baseUrl.substringAfter("://"), config.username),
                            color = Faint, fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).widthIn(max = 260.dp),
                        )
                    }
                    state.library.addons.forEach { addon ->
                        Text(addon.name, color = Faint, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.music_remove_addon, addon.name), color = Color.White) },
                            onClick = { menu = false; viewModel.removeAddon(addon) },
                        )
                    }
                    if (state.config != null) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.music_disconnect), color = Color.White) },
                            onClick = { menu = false; viewModel.disconnect() },
                        )
                    }
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.music_add_source), color = Color.White) },
                        onClick = { menu = false; onAddSource() },
                    )
                }
            }
        }

        MusicField(
            value = state.query,
            onValue = viewModel::setQuery,
            label = "",
            placeholder = stringResource(R.string.music_search_hint),
            accent = accent,
            leading = Icons.Default.Search,
            keyboard = KeyboardOptions(imeAction = ImeAction.Search),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).testTag("music-search"),
        )

        if (state.query.isNotBlank()) {
            SearchResultsView(state, playback, accent, bottomPadding, viewModel)
            return@Column
        }

        Row(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf(
                MusicTab.HOME to R.string.music_tab_home,
                MusicTab.ALBUMS to R.string.music_tab_albums,
                MusicTab.ARTISTS to R.string.music_tab_artists,
            ).forEach { (tab, label) ->
                val selected = state.tab == tab
                Box(
                    Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (selected) accent else Surface)
                        .clickable { viewModel.selectTab(tab) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Text(
                        stringResource(label),
                        color = if (selected) contentOn(accent) else Color.White,
                        fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }

        when (state.tab) {
            MusicTab.HOME -> HomeShelves(state, accent, bottomPadding, viewModel)
            MusicTab.ALBUMS -> {
                val albums = state.allAlbums
                if (albums == null) LoadingBox() else AlbumGrid(albums, bottomPadding, viewModel)
            }
            MusicTab.ARTISTS -> {
                val artists = state.artists
                if (artists == null) LoadingBox() else LazyColumn(
                    Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 8.dp, bottom = bottomPadding),
                ) {
                    items(artists, key = { it.id }) { artist -> ArtistRow(artist) { viewModel.openArtist(artist) } }
                }
            }
        }
    }
}

@Composable
private fun HomeShelves(state: MusicUiState, accent: Color, bottomPadding: Dp, viewModel: MusicViewModel) {
    val shelves = state.shelves
    when {
        state.loading && shelves.isEmpty() -> LoadingBox()
        state.error != null && shelves.isEmpty() -> MessageBox(
            text = stringResource(R.string.music_load_failed) + "\n" + state.error,
            action = stringResource(R.string.music_retry),
            accent = accent,
            onAction = viewModel::refresh,
        )
        shelves.isEmpty() -> MessageBox(stringResource(R.string.music_empty_library))
        else -> LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 8.dp, bottom = bottomPadding),
        ) {
            items(shelves, key = { it.key }) { shelf ->
                val title = when (shelf.kind) {
                    AlbumShelf.NEWEST -> stringResource(R.string.music_shelf_newest)
                    AlbumShelf.RECENT -> stringResource(R.string.music_shelf_recent)
                    AlbumShelf.FREQUENT -> stringResource(R.string.music_shelf_frequent)
                    AlbumShelf.RANDOM -> stringResource(R.string.music_shelf_random)
                    else -> shelf.title.orEmpty()
                }
                val albums = shelf.albums
                Text(
                    title,
                    color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 10.dp),
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(albums, key = { it.id }) { album ->
                        AlbumCard(album, Modifier.width(144.dp)) { viewModel.openAlbum(album) }
                    }
                }
            }
        }
    }
}

@Composable
private fun AlbumGrid(albums: List<MusicAlbum>, bottomPadding: Dp, viewModel: MusicViewModel, header: (@Composable () -> Unit)? = null) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = bottomPadding),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (header != null) item(span = { GridItemSpan(maxLineSpan) }) { header() }
        items(albums, key = { it.id }) { album -> AlbumCard(album) { viewModel.openAlbum(album) } }
    }
}

@Composable
private fun SearchResultsView(
    state: MusicUiState,
    playback: MusicPlaybackState,
    accent: Color,
    bottomPadding: Dp,
    viewModel: MusicViewModel,
) {
    val results = state.results
    when {
        results == null -> LoadingBox()
        results.isEmpty && !state.searching -> MessageBox(stringResource(R.string.music_no_results, state.query.trim()))
        else -> LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = bottomPadding)) {
            if (results.artists.isNotEmpty()) {
                item { SectionTitle(stringResource(R.string.music_tab_artists)) }
                items(results.artists, key = { "artist-" + it.id }) { artist ->
                    ArtistRow(artist) { viewModel.openArtist(artist) }
                }
            }
            if (results.albums.isNotEmpty()) {
                item { SectionTitle(stringResource(R.string.music_tab_albums)) }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(results.albums, key = { it.id }) { album ->
                            AlbumCard(album, Modifier.width(144.dp)) { viewModel.openAlbum(album) }
                        }
                    }
                }
            }
            if (results.tracks.isNotEmpty()) {
                item { SectionTitle(stringResource(R.string.music_songs)) }
                itemsIndexed(results.tracks, key = { _, track -> "track-" + track.id }) { index, track ->
                    TrackRow(
                        track = track,
                        playing = playback.current?.id == track.id,
                        accent = accent,
                        showCover = true,
                    ) { viewModel.play(results.tracks, index) }
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------------------------
// Album and artist pages

@Composable
private fun AlbumPageView(
    page: MusicPage.Album,
    playback: MusicPlaybackState,
    accent: Color,
    bottomPadding: Dp,
    viewModel: MusicViewModel,
) {
    val album = page.album
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = bottomPadding)) {
        item {
            Box(Modifier.fillMaxWidth()) {
                Cover(
                    album.coverUrl,
                    Modifier.fillMaxWidth().height(300.dp).blur(48.dp),
                    shape = RoundedCornerShape(0.dp),
                    icon = Icons.Default.Album,
                )
                Box(
                    Modifier.fillMaxWidth().height(300.dp)
                        .background(Brush.verticalGradient(listOf(Color.Black.copy(alpha = 0.35f), Color.Black)))
                )
                Column(
                    Modifier.fillMaxWidth().statusBarsPadding().padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    BackRow(viewModel)
                    Cover(album.coverUrl, Modifier.size(220.dp), shape = RoundedCornerShape(14.dp), icon = Icons.Default.Album)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        album.name, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold,
                        maxLines = 2, overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 24.dp),
                    )
                    Text(
                        album.artist, color = accent, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .clickable(enabled = album.artistId != null) { viewModel.openArtist(album.artistId, album.artist) }
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                    )
                    val count = page.tracks.size.takeIf { it > 0 } ?: album.trackCount
                    val meta = listOfNotNull(
                        album.year?.toString(),
                        count?.let { pluralStringResource(R.plurals.music_track_count, it, it) },
                    ).joinToString(" · ")
                    if (meta.isNotEmpty()) Text(meta, color = Faint, fontSize = 13.sp, modifier = Modifier.padding(top = 2.dp))
                    Spacer(Modifier.height(16.dp))
                    PlayButtons(accent, enabled = page.tracks.isNotEmpty(),
                        onPlay = { viewModel.play(page.tracks) },
                        onShuffle = { viewModel.shuffle(page.tracks) })
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
        when {
            page.loading -> item { LoadingBox(Modifier.fillMaxWidth().height(160.dp)) }
            page.error != null -> item { MessageBox(stringResource(R.string.music_load_failed) + "\n" + page.error) }
            else -> itemsIndexed(page.tracks, key = { _, track -> track.id }) { index, track ->
                TrackRow(
                    track = track,
                    playing = playback.current?.id == track.id,
                    accent = accent,
                    number = track.trackNumber ?: (index + 1),
                    showArtist = track.artist.isNotBlank() && track.artist != album.artist,
                ) { viewModel.play(page.tracks, index) }
            }
        }
    }
}

@Composable
private fun ArtistPageView(page: MusicPage.Artist, accent: Color, bottomPadding: Dp, viewModel: MusicViewModel) {
    Column(Modifier.fillMaxSize().statusBarsPadding()) {
        BackRow(viewModel)
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Cover(page.artist.coverUrl, Modifier.size(72.dp), shape = CircleShape, icon = Icons.Default.Person)
            Spacer(Modifier.width(16.dp))
            Text(
                page.artist.name, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold,
                maxLines = 2, overflow = TextOverflow.Ellipsis,
            )
        }
        when {
            page.loading -> LoadingBox()
            page.error != null -> MessageBox(stringResource(R.string.music_load_failed) + "\n" + page.error)
            else -> AlbumGrid(page.albums, bottomPadding, viewModel, header = {
                Box(Modifier.padding(vertical = 4.dp)) {
                    PlayButtons(accent, enabled = page.albums.isNotEmpty(),
                        onPlay = { page.albums.firstOrNull()?.let(viewModel::playAlbum) },
                        onShuffle = null)
                }
            })
        }
    }
}

@Composable
private fun BackRow(viewModel: MusicViewModel) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 4.dp)) {
        IconButton(onClick = { viewModel.back() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.music_back), tint = Color.White)
        }
    }
}

@Composable
private fun PlayButtons(accent: Color, enabled: Boolean, onPlay: () -> Unit, onShuffle: (() -> Unit)?) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        PillButton(stringResource(R.string.music_play), Icons.Default.PlayArrow, accent, filled = true, enabled = enabled, onClick = onPlay)
        if (onShuffle != null) {
            PillButton(stringResource(R.string.music_shuffle), Icons.Default.Shuffle, accent, filled = false, enabled = enabled, onClick = onShuffle)
        }
    }
}

@Composable
private fun PillButton(label: String, icon: ImageVector, accent: Color, filled: Boolean, enabled: Boolean, onClick: () -> Unit) {
    val content = if (filled) contentOn(accent) else Color.White
    Row(
        Modifier
            .clip(RoundedCornerShape(24.dp))
            .then(if (filled) Modifier.background(accent) else Modifier.border(1.dp, Color(0x4DEDEDED), RoundedCornerShape(24.dp)))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, null, tint = content, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(6.dp))
        Text(label, color = content, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

// ---------------------------------------------------------------------------------------------
// Rows and cards

@Composable
private fun AlbumCard(album: MusicAlbum, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier.clip(RoundedCornerShape(10.dp)).clickable(onClick = onClick)) {
        Cover(album.coverUrl, Modifier.fillMaxWidth().aspectRatio(1f), shape = RoundedCornerShape(10.dp), icon = Icons.Default.Album)
        Text(
            album.name, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
            maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 8.dp),
        )
        Text(album.artist, color = Faint, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun ArtistRow(artist: MusicArtist, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Cover(artist.coverUrl, Modifier.size(52.dp), shape = CircleShape, icon = Icons.Default.Person)
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(artist.name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            artist.albumCount?.takeIf { it > 0 }?.let {
                Text("$it " + stringResource(R.string.music_tab_albums).lowercase(), color = Faint, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun TrackRow(
    track: MusicTrack,
    playing: Boolean,
    accent: Color,
    number: Int? = null,
    showCover: Boolean = false,
    showArtist: Boolean = true,
    onClick: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showCover) {
            Cover(track.coverUrl, Modifier.size(46.dp), shape = RoundedCornerShape(6.dp), icon = Icons.Default.MusicNote)
            Spacer(Modifier.width(14.dp))
        } else if (number != null) {
            Box(Modifier.width(32.dp)) {
                if (playing) Icon(Icons.Default.MusicNote, null, tint = accent, modifier = Modifier.size(18.dp))
                else Text(number.toString(), color = Faint, fontSize = 14.sp)
            }
        }
        Column(Modifier.weight(1f)) {
            Text(
                track.title, color = if (playing) accent else Color.White, fontSize = 15.sp,
                fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
            if (showArtist && track.artist.isNotBlank()) {
                Text(
                    if (showCover && track.album.isNotBlank()) "${track.artist} · ${track.album}" else track.artist,
                    color = Faint, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (track.durationSec > 0) {
            Text(formatTime(track.durationSec * 1000L), color = Faint, fontSize = 13.sp, modifier = Modifier.padding(start = 12.dp))
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text, color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 8.dp),
    )
}

@Composable
private fun Cover(url: String?, modifier: Modifier, shape: androidx.compose.ui.graphics.Shape, icon: ImageVector) {
    Box(modifier.clip(shape).background(Surface), contentAlignment = Alignment.Center) {
        Icon(icon, null, tint = Color(0x33EDEDED), modifier = Modifier.fillMaxSize(0.4f))
        if (url != null) {
            AsyncImage(model = url, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun LoadingBox(modifier: Modifier = Modifier.fillMaxSize()) {
    Box(modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(28.dp))
    }
}

@Composable
private fun MessageBox(text: String, action: String? = null, accent: Color = Color.White, onAction: () -> Unit = {}) {
    Column(
        Modifier.fillMaxWidth().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text, color = Muted, fontSize = 14.sp, lineHeight = 20.sp)
        if (action != null) {
            Spacer(Modifier.height(16.dp))
            PillButton(action, Icons.Default.Repeat, accent, filled = true, enabled = true, onClick = onAction)
        }
    }
}

// ---------------------------------------------------------------------------------------------
// Player

@Composable
private fun MiniPlayer(
    track: MusicTrack,
    playback: MusicPlaybackState,
    accent: Color,
    onOpen: () -> Unit,
    onToggle: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(MiniPlayerHeight)
            .clip(RoundedCornerShape(14.dp))
            .background(BackgroundElevated)
            .clickable(onClick = onOpen)
            .testTag("music-mini-player"),
    ) {
        Row(Modifier.fillMaxSize().padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Cover(track.coverUrl, Modifier.size(46.dp), shape = RoundedCornerShape(8.dp), icon = Icons.Default.MusicNote)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(track.title, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(track.artist, color = Faint, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            PlayPauseIcon(playback, Color.White, onToggle, size = 44.dp)
            IconButton(onClick = onNext) { Icon(Icons.Default.SkipNext, stringResource(R.string.music_next), tint = Color.White) }
        }
        val progress = if (playback.durationMs > 0) (playback.positionMs.toFloat() / playback.durationMs).coerceIn(0f, 1f) else 0f
        Box(
            Modifier.align(Alignment.BottomStart).padding(horizontal = 12.dp).fillMaxWidth(progress)
                .height(2.dp).clip(RoundedCornerShape(1.dp)).background(accent)
        )
    }
}

@Composable
private fun PlayPauseIcon(playback: MusicPlaybackState, tint: Color, onClick: () -> Unit, size: Dp) {
    IconButton(onClick = onClick, modifier = Modifier.size(size)) {
        if (playback.isBuffering && !playback.isPlaying) {
            CircularProgressIndicator(color = tint, strokeWidth = 2.dp, modifier = Modifier.size(size * 0.45f))
        } else {
            Icon(
                if (playback.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                stringResource(if (playback.isPlaying) R.string.music_pause else R.string.music_play),
                tint = tint,
                modifier = Modifier.size(size * 0.6f),
            )
        }
    }
}

@Composable
private fun FullPlayer(track: MusicTrack, playback: MusicPlaybackState, accent: Color, viewModel: MusicViewModel) {
    Box(Modifier.fillMaxSize().background(Color.Black).clickable(enabled = false) {}.testTag("music-player")) {
        Cover(track.coverUrl, Modifier.fillMaxSize().blur(80.dp), shape = RoundedCornerShape(0.dp), icon = Icons.Default.MusicNote)
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(listOf(Color.Black.copy(alpha = 0.55f), Color.Black.copy(alpha = 0.8f), Color.Black))
            )
        )
        Column(
            Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding().padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { viewModel.setPlayerOpen(false) }) {
                    Icon(Icons.Default.KeyboardArrowDown, stringResource(R.string.music_close_player), tint = Color.White, modifier = Modifier.size(32.dp))
                }
                Text(
                    track.album, color = Muted, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                )
                Spacer(Modifier.width(48.dp))
            }
            Spacer(Modifier.weight(0.6f))
            Cover(
                track.coverUrl,
                Modifier.fillMaxWidth().widthIn(max = 420.dp).aspectRatio(1f),
                shape = RoundedCornerShape(18.dp),
                icon = Icons.Default.MusicNote,
            )
            Spacer(Modifier.weight(0.5f))
            Column(Modifier.fillMaxWidth().widthIn(max = 520.dp)) {
                Text(track.title, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(
                    track.artist, color = Muted, fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp),
                )
                Spacer(Modifier.height(18.dp))

                var dragging by remember { mutableStateOf(false) }
                var dragValue by remember { mutableFloatStateOf(0f) }
                val duration = playback.durationMs.coerceAtLeast(1L)
                val value = if (dragging) dragValue else (playback.positionMs.toFloat() / duration).coerceIn(0f, 1f)
                Slider(
                    value = value,
                    onValueChange = { dragging = true; dragValue = it },
                    onValueChangeFinished = {
                        viewModel.seekTo((dragValue * duration).toLong())
                        dragging = false
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = accent,
                        activeTrackColor = accent,
                        inactiveTrackColor = Color(0x33EDEDED),
                    ),
                )
                Row(Modifier.fillMaxWidth()) {
                    Text(formatTime((value * duration).toLong()), color = Faint, fontSize = 12.sp, modifier = Modifier.weight(1f))
                    Text(formatTime(playback.durationMs), color = Faint, fontSize = 12.sp)
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = viewModel::toggleShuffle) {
                        Icon(Icons.Default.Shuffle, stringResource(R.string.music_shuffle), tint = if (playback.shuffle) accent else Muted)
                    }
                    IconButton(onClick = viewModel::previous, modifier = Modifier.size(56.dp)) {
                        Icon(Icons.Default.SkipPrevious, stringResource(R.string.music_previous), tint = Color.White, modifier = Modifier.size(36.dp))
                    }
                    Box(
                        Modifier.size(72.dp).clip(CircleShape).background(accent),
                        contentAlignment = Alignment.Center,
                    ) {
                        PlayPauseIcon(playback, contentOn(accent), viewModel::togglePlay, size = 72.dp)
                    }
                    IconButton(onClick = viewModel::next, modifier = Modifier.size(56.dp)) {
                        Icon(Icons.Default.SkipNext, stringResource(R.string.music_next), tint = Color.White, modifier = Modifier.size(36.dp))
                    }
                    IconButton(onClick = viewModel::cycleRepeat) {
                        Icon(
                            if (playback.repeatMode == Player.REPEAT_MODE_ONE) Icons.Default.RepeatOne else Icons.Default.Repeat,
                            stringResource(R.string.music_repeat),
                            tint = if (playback.repeatMode != Player.REPEAT_MODE_OFF) accent else Muted,
                        )
                    }
                }
            }
            Spacer(Modifier.weight(0.6f))
        }
    }
}

/** Black text on a light accent (the default white), white text on a dark one. */
private fun contentOn(color: Color): Color =
    if (color.red * 0.299f + color.green * 0.587f + color.blue * 0.114f > 0.6f) Color.Black else Color.White

internal fun formatTime(ms: Long): String {
    val total = (ms / 1000).coerceAtLeast(0)
    val hours = total / 3600
    val minutes = (total % 3600) / 60
    val seconds = total % 60
    return if (hours > 0) "%d:%02d:%02d".format(hours, minutes, seconds) else "%d:%02d".format(minutes, seconds)
}
