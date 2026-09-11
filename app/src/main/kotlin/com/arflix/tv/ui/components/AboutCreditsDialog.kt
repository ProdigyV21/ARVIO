package com.arflix.tv.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.arflix.tv.R

@Composable
fun AboutCreditsDialog(onDismiss: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    val closeFocus = remember { FocusRequester() }
    Dialog(onDismissRequest = onDismiss) {
        Surface(color = Color(0xFF181818), contentColor = Color.White) {
            Column(
                modifier = Modifier.fillMaxWidth().heightIn(max = 520.dp)
                    .verticalScroll(rememberScrollState()).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("ARVIO", fontSize = 28.sp)
                Text(stringResource(R.string.about_credits), fontSize = 20.sp)
                AsyncImage(
                    model = "file:///android_asset/tmdb-logo.svg",
                    contentDescription = "TMDB",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(100.dp).height(16.dp)
                )
                Text(stringResource(R.string.tmdb_attribution))
                Text("Sports data and artwork provided by TheSportsDB.")
                Button(
                    onClick = { runCatching { uriHandler.openUri("https://www.thesportsdb.com") } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)
                ) { Text("TheSportsDB") }
                Text(stringResource(R.string.authorized_sources_notice))
                Button(
                    onClick = { runCatching { uriHandler.openUri("https://arvio.tv/credits/") } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)
                ) { Text(stringResource(R.string.credits_and_copyright)) }
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.focusRequester(closeFocus),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
                ) { Text(stringResource(R.string.close)) }
            }
        }
        LaunchedEffect(Unit) { closeFocus.requestFocus() }
    }
}
