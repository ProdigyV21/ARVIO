package com.arflix.tv.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.arflix.tv.R

/** Keyboard/D-pad and touch accessible; Back cancels the pending destination, not playback. */
@Composable
internal fun PartnerOpenLinkDialog(failed: Boolean, onRetry: () -> Unit, onCancel: () -> Unit) {
    val actionFocus = remember { FocusRequester() }
    Dialog(onDismissRequest = onCancel) {
        Surface(shape = RoundedCornerShape(24.dp), color = Color(0xFF181818), contentColor = Color.White) {
            Column(
                Modifier.fillMaxWidth().padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text("ARVIO", fontSize = 22.sp)
                if (!failed) CircularProgressIndicator(color = Color.White)
                Text(stringResource(if (failed) R.string.partner_link_failed else R.string.partner_link_loading))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (failed) {
                        Button(
                            onClick = onRetry,
                            modifier = Modifier.focusRequester(actionFocus),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
                        ) { Text(stringResource(R.string.retry)) }
                    }
                    Button(
                        onClick = onCancel,
                        modifier = if (failed) Modifier else Modifier.focusRequester(actionFocus),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White)
                    ) { Text(stringResource(R.string.cancel)) }
                }
            }
        }
        LaunchedEffect(failed) { actionFocus.requestFocus() }
    }
}
