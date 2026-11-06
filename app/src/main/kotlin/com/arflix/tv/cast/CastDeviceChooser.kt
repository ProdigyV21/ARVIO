package com.arflix.tv.cast

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.mediarouter.media.MediaRouter
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import com.arflix.tv.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private sealed class CastTarget {
    abstract val key: String
    abstract val name: String
    abstract val detail: String
    data class Google(val route: MediaRouter.RouteInfo) : CastTarget() {
        override val key get() = "gc:" + route.id
        override val name: String get() = route.name
        override val detail get() = "Google Cast"
    }
    data class Dlna(val device: DlnaDevice) : CastTarget() {
        override val key get() = "dlna:" + device.id
        override val name get() = device.friendlyName
        override val detail get() = listOf("DLNA", device.manufacturer, device.modelName)
            .filter { it.isNotBlank() }.distinct().joinToString(" · ")
    }
}

/**
 * One chooser for every TV on the local network — Google Cast devices
 * (through MediaRouter, when Google Play services is present) and DLNA/UPnP renderers.
 */
@Composable
fun CastDeviceChooserDialog(
    castManager: CastManager,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    val googleTargets = remember { mutableStateListOf<CastTarget.Google>() }
    val dlnaTargets = remember { mutableStateListOf<CastTarget.Dlna>() }
    var scanning by remember { mutableStateOf(true) }
    var scanRound by remember { mutableIntStateOf(0) }

    // Google Cast routes via MediaRouter (active scan while the dialog is open).
    DisposableEffect(Unit) {
        val router = runCatching { MediaRouter.getInstance(context) }.getOrNull()
        val selector = castManager.getRouteSelector()
        fun refresh() {
            val r = router ?: return
            googleTargets.clear()
            r.routes
                .filter { !it.isDefaultOrBluetooth && it.isEnabled && it.matchesSelector(selector) }
                .forEach { googleTargets.add(CastTarget.Google(it)) }
        }
        val callback = object : MediaRouter.Callback() {
            override fun onRouteAdded(router: MediaRouter, route: MediaRouter.RouteInfo) = refresh()
            override fun onRouteRemoved(router: MediaRouter, route: MediaRouter.RouteInfo) = refresh()
            override fun onRouteChanged(router: MediaRouter, route: MediaRouter.RouteInfo) = refresh()
        }
        if (router != null && castManager.isGoogleCastReady) {
            router.addCallback(selector, callback, MediaRouter.CALLBACK_FLAG_PERFORM_ACTIVE_SCAN)
            refresh()
        }
        onDispose { runCatching { router?.removeCallback(callback) } }
    }

    // DLNA renderers via SSDP.
    LaunchedEffect(scanRound) {
        scanning = true
        withContext(Dispatchers.IO) {
            DlnaDiscovery.discover(context, timeoutMs = 6_000L) { device ->
                val target = CastTarget.Dlna(device)
                android.os.Handler(android.os.Looper.getMainLooper()).post {
                    if (dlnaTargets.none { it.key == target.key }) dlnaTargets.add(target)
                }
            }
        }
        scanning = false
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFF1C1C1E))
                .padding(vertical = 18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.cast_chooser_title),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                if (scanning) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = stringResource(R.string.cast_chooser_search_again),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { scanRound++ }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            val all: List<CastTarget> = googleTargets + dlnaTargets
            if (all.isEmpty()) {
                Text(
                    text = if (scanning) stringResource(R.string.cast_chooser_searching)
                    else stringResource(R.string.cast_chooser_none_found),
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 360.dp)) {
                    items(all, key = { it.key }) { target ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    when (target) {
                                        is CastTarget.Google -> runCatching {
                                            MediaRouter.getInstance(context).selectRoute(target.route)
                                        }
                                        is CastTarget.Dlna -> castManager.connectDlna(target.device)
                                    }
                                    onDismiss()
                                }
                                .padding(horizontal = 20.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Icon(
                                imageVector = if (target is CastTarget.Google) Icons.Default.Cast else Icons.Default.Tv,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = target.name,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = target.detail,
                                    color = Color.White.copy(alpha = 0.55f),
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
            Text(
                text = stringResource(R.string.cast_chooser_cancel),
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 12.dp, top = 6.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onDismiss)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            )
        }
    }
}
