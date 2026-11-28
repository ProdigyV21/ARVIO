package com.arflix.tv.ui.screens.music

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.arflix.tv.R
import com.arflix.tv.music.MusicAddon
import com.arflix.tv.music.MusicAudioCache
import com.arflix.tv.music.MusicLibrary
import com.arflix.tv.music.MusicPlayer
import com.arflix.tv.music.MusicRepository
import com.arflix.tv.music.MusicServerType
import com.arflix.tv.ui.skin.resolveAccentColor
import android.content.Context
import android.text.format.Formatter
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Music's sources for Settings: what is connected, adding one, removing one. Loads no music. */
@HiltViewModel
class MusicSourcesViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: MusicRepository,
    private val player: MusicPlayer,
) : ViewModel() {
    val library: StateFlow<MusicLibrary?> = repository.library.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _cacheBytes = MutableStateFlow<Long?>(null)
    /** Space the songs kept on the device take; null until measured. */
    val cacheBytes: StateFlow<Long?> = _cacheBytes.asStateFlow()
    val cacheLimitBytes: Long = MusicAudioCache.limitBytes(context)

    init {
        refreshCache()
    }

    fun refreshCache() {
        viewModelScope.launch { _cacheBytes.value = withContext(Dispatchers.IO) { MusicAudioCache.usedBytes(context) } }
    }

    fun clearCache() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { MusicAudioCache.clear(context) }
            refreshCache()
        }
    }

    private val _status = MutableStateFlow(MusicSourceStatus())
    internal val status: StateFlow<MusicSourceStatus> = _status.asStateFlow()

    fun addAddon(url: String) {
        if (_status.value.addingAddon) return
        _status.update { it.copy(addingAddon = true, addonError = null) }
        viewModelScope.launch {
            runCatching { repository.addAddon(url) }
                .onSuccess { _status.update { it.copy(addingAddon = false) } }
                .onFailure { error -> _status.update { it.copy(addingAddon = false, addonError = error.message ?: error.javaClass.simpleName) } }
        }
    }

    fun connect(type: MusicServerType, url: String, username: String, password: String) {
        if (_status.value.connecting) return
        _status.update { it.copy(connecting = true, connectError = null) }
        viewModelScope.launch {
            runCatching { repository.connect(type, url, username, password) }
                .onSuccess { _status.update { it.copy(connecting = false) } }
                .onFailure { error -> _status.update { it.copy(connecting = false, connectError = error.message ?: error.javaClass.simpleName) } }
        }
    }

    fun clearErrors() = _status.update { it.copy(addonError = null, connectError = null) }

    // A removed source can no longer play its songs, so whatever is playing stops, as on the Music screen.
    fun removeAddon(addon: MusicAddon) {
        player.stop()
        viewModelScope.launch { repository.removeAddon(addon.baseUrl) }
    }

    fun disconnect() {
        player.stop()
        viewModelScope.launch { repository.disconnect() }
    }
}

/** A short line for the Settings row: the sources' names, or that there are none. */
@Composable
fun musicSourcesSummary(library: MusicLibrary?): String {
    val names = buildList {
        library?.server?.let { add(serverName(it.type)) }
        library?.addons?.forEach { add(it.name) }
    }
    return if (names.isEmpty()) stringResource(R.string.music_sources_none) else names.joinToString(", ")
}

/** The cache row's value: how much space the kept songs take. */
@Composable
fun musicCacheSize(bytes: Long?): String {
    val context = androidx.compose.ui.platform.LocalContext.current
    return bytes?.let { Formatter.formatShortFileSize(context, it) } ?: ""
}

/** The cache row's explanation, with the most it may take on this device. */
@Composable
fun musicCacheDescription(limitBytes: Long): String {
    val context = androidx.compose.ui.platform.LocalContext.current
    return stringResource(R.string.music_cache_desc, Formatter.formatShortFileSize(context, limitBytes))
}

@Composable
private fun serverName(type: MusicServerType): String =
    stringResource(if (type == MusicServerType.JELLYFIN) R.string.music_server_jellyfin else R.string.music_server_subsonic_short)

/**
 * Settings -> Music -> Sources: every source with its Remove button, and Add source, which opens
 * the same form the Music tab shows before its first source. Full screen, on TV and on phones.
 */
@Composable
fun MusicSourcesDialog(viewModel: MusicSourcesViewModel, onDismiss: () -> Unit) {
    val library by viewModel.library.collectAsStateWithLifecycle()
    val status by viewModel.status.collectAsStateWithLifecycle()
    val accent = resolveAccentColor(fallback = Color.White)
    var adding by rememberSaveable { mutableStateOf(false) }
    // A source was added (or the list changed some other way): back to the list.
    LaunchedEffect(library) { adding = false }
    val close = {
        if (adding) {
            adding = false
            viewModel.clearErrors()
        } else onDismiss()
    }

    Dialog(onDismissRequest = close, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        BackHandler(onBack = close)
        Box(Modifier.fillMaxSize().background(Color.Black)) {
            if (adding) {
                ConnectView(
                    status = status,
                    accent = accent,
                    onConnect = viewModel::connect,
                    onAddAddon = viewModel::addAddon,
                    onClose = close,
                )
            } else {
                SourcesList(library, accent, onAdd = { viewModel.clearErrors(); adding = true }, onClose = close, viewModel)
            }
        }
    }
}

@Composable
private fun SourcesList(
    library: MusicLibrary?,
    accent: Color,
    onAdd: () -> Unit,
    onClose: () -> Unit,
    viewModel: MusicSourcesViewModel,
) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(modifier = Modifier.tvFocusRing(CircleShape), onClick = onClose) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.music_back), tint = Color.White)
            }
            Spacer(Modifier.size(8.dp))
            Text(stringResource(R.string.music_sources_title), color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp))
        Column(Modifier.widthIn(max = 560.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            val server = library?.server
            if (server == null && library?.addons.isNullOrEmpty()) {
                Text(stringResource(R.string.music_sources_none), color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)
            }
            server?.let {
                SourceRow(
                    icon = Icons.Default.Dns,
                    title = serverName(it.type),
                    subtitle = stringResource(R.string.music_connected_as, it.baseUrl.substringAfter("://"), it.username),
                    action = stringResource(R.string.music_disconnect),
                    onAction = viewModel::disconnect,
                )
            }
            library?.addons?.forEach { addon ->
                SourceRow(
                    icon = Icons.Default.Extension,
                    title = addon.name,
                    // Only the host: an addon's address can carry the user's own key.
                    subtitle = addon.baseUrl.substringAfter("://").substringBefore('/'),
                    action = stringResource(R.string.music_remove),
                    onAction = { viewModel.removeAddon(addon) },
                )
            }
            Spacer(Modifier.height(6.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .tvFocusRing(RoundedCornerShape(26.dp))
                    .background(accent, RoundedCornerShape(26.dp))
                    .focusRequester(rememberTvInitialFocus())
                    .clickable(onClick = onAdd),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Default.Add, null, tint = contentOn(accent))
                Spacer(Modifier.size(8.dp))
                Text(stringResource(R.string.music_add_source), color = contentOn(accent), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SourceRow(icon: ImageVector, title: String, subtitle: String, action: String, onAction: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF141414), RoundedCornerShape(12.dp))
            .padding(start = 16.dp, end = 8.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(icon, null, tint = Color.White.copy(alpha = 0.7f), modifier = Modifier.size(22.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(subtitle, color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Box(
            Modifier
                .tvFocusRing(RoundedCornerShape(18.dp))
                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(18.dp))
                .clickable(onClick = onAction)
                .padding(horizontal = 14.dp, vertical = 8.dp),
        ) {
            Text(action, color = Color(0xFFFF8A80), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
