package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.arflix.tv.R
import com.arflix.tv.ui.screens.player.PlaybackCompatibility
import com.arflix.tv.ui.screens.player.PlaybackFormatCheck
import com.arflix.tv.ui.theme.BackgroundElevated
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal fun PlaybackCompatibilityDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    val closeFocus = remember { FocusRequester() }
    val scope = rememberCoroutineScope()
    var testing by remember { mutableStateOf(false) }
    var testResults by remember { mutableStateOf<String?>(null) }
    val testFailed = stringResource(R.string.playback_compatibility_test_failed)
    val report by produceState<String?>(null, context) {
        value = withContext(Dispatchers.Default) { PlaybackCompatibility.report(context.applicationContext) }
    }
    val displayed = report?.let { base -> testResults?.let { "$base\n\n$it" } ?: base }
    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), color = BackgroundElevated, contentColor = Color.White) {
            Column(Modifier.fillMaxWidth().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(stringResource(R.string.playback_compatibility), fontSize = 20.sp)
                Text(
                    displayed ?: stringResource(R.string.playback_compatibility_checking),
                    Modifier.weight(1f, fill = false).heightIn(max = 330.dp).verticalScroll(rememberScrollState()),
                    fontSize = 14.sp
                )
                Button(
                    onClick = {
                        testing = true
                        scope.launch {
                            try {
                                testResults = PlaybackFormatCheck.run(context)
                            } catch (cancelled: CancellationException) {
                                throw cancelled
                            } catch (_: Exception) {
                                testResults = testFailed
                            } finally {
                                testing = false
                            }
                        }
                    },
                    enabled = !testing,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)
                ) {
                    Text(stringResource(if (testing) R.string.playback_compatibility_checking else R.string.playback_compatibility_test))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { displayed?.let { clipboard.setText(AnnotatedString(it)) } },
                        enabled = displayed != null,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)
                    ) { Text(stringResource(R.string.playback_compatibility_copy)) }
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.focusRequester(closeFocus),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
                    ) { Text(stringResource(R.string.close)) }
                }
            }
        }
        LaunchedEffect(Unit) { closeFocus.requestFocus() }
    }
}
