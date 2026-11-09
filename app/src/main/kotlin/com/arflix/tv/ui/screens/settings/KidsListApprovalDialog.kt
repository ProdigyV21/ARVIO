package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaItem

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun KidsListOptionsDialog(title: String, onRename: () -> Unit, onApprove: () -> Unit, onDismiss: () -> Unit) {
    val first = remember { FocusRequester() }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onRename, modifier = Modifier.fillMaxWidth().focusRequester(first)) {
                    Text(stringResource(R.string.rename_catalog))
                }
                Button(onClick = onApprove, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.kids_list_approve_title))
                }
                LaunchedEffect(Unit) { first.requestFocus() }
            }
        },
        confirmButton = { Button(onClick = onDismiss) { Text(stringResource(R.string.cancel)) } }
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun KidsListTitleDialog(
    approval: KidsListApprovalUi,
    error: String?,
    onApprove: (MediaItem) -> Unit,
    onMore: () -> Unit,
    onDismiss: () -> Unit
) {
    val first = remember { FocusRequester() }
    val cancel = remember { FocusRequester() }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.kids_list_approve_title)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(approval.catalog.title, maxLines = 2, overflow = TextOverflow.Ellipsis)
                if (error != null) Text(error)
                if (approval.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
                if (!approval.loading && approval.items.isEmpty() && approval.nextOffset == null && error == null) {
                    Text(stringResource(R.string.kids_list_approval_empty))
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().heightIn(max = 320.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    itemsIndexed(approval.items, key = { _, item -> "${item.mediaType}/${item.id}" }) { index, item ->
                        Button(
                            onClick = { onApprove(item) },
                            modifier = Modifier.fillMaxWidth().then(if (index == 0) Modifier.focusRequester(first) else Modifier),
                            enabled = !approval.loading
                        ) {
                            Text(listOf(item.title, item.year).filter(String::isNotBlank).joinToString("  |  "),
                                maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                    }
                    if (approval.nextOffset != null) item(key = "more") {
                        Button(
                            onClick = onMore,
                            modifier = Modifier.fillMaxWidth().then(if (approval.items.isEmpty()) Modifier.focusRequester(first) else Modifier),
                            enabled = !approval.loading
                        ) { Text(stringResource(R.string.kids_list_more_titles)) }
                    }
                }
                LaunchedEffect(approval.items.isEmpty(), approval.loading) {
                    if (!approval.loading) {
                        val target = if (approval.items.isNotEmpty() || approval.nextOffset != null) first else cancel
                        runCatching { target.requestFocus() }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss, modifier = Modifier.focusRequester(cancel)) { Text(stringResource(R.string.cancel)) }
        }
    )
}
