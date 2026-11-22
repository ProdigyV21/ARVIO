package com.arflix.tv.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.news.NewsProvider
import com.arflix.tv.data.news.NewsProviders
import com.arflix.tv.data.news.NewsSourceSelection
import com.arflix.tv.data.news.NewsTopic
import com.arflix.tv.ui.components.TextInputModal
import com.arflix.tv.ui.screens.watchlist.OledControl
import com.arflix.tv.ui.skin.resolveAccentColor
import kotlinx.coroutines.delay

/** The two pickers under Settings → News. */
enum class NewsSettingsDialog { TOPICS, SOURCES }

/** Shows [open] (or nothing) and the "add RSS feed" text entry that the sources picker leads to. */
@Composable
fun NewsSettingsDialogHost(open: NewsSettingsDialog?, viewModel: NewsSettingsViewModel, onClose: () -> Unit) {
    val selection by viewModel.selection.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()
    var addingFeed by rememberSaveable { mutableStateOf(false) }

    when {
        addingFeed -> Dialog(onDismissRequest = { addingFeed = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)) {
            Box(Modifier.fillMaxSize()) {
                TextInputModal(
                    isVisible = true,
                    title = stringResource(R.string.news_add_feed),
                    hint = stringResource(R.string.news_add_feed_hint),
                    onConfirm = { url ->
                        addingFeed = false
                        if (url.isNotBlank()) viewModel.addCustomProvider(url)
                    },
                    onCancel = { addingFeed = false },
                )
            }
        }
        open == NewsSettingsDialog.TOPICS -> NewsTopicsDialog(selection.topics, viewModel::toggleTopic, onClose)
        open == NewsSettingsDialog.SOURCES -> NewsSourcesDialog(
            selection = selection,
            isProbing = state.isProbingFeed,
            feedError = state.customFeedError,
            onToggle = viewModel::toggleProvider,
            onRemove = viewModel::removeCustomProvider,
            onAddFeed = { viewModel.dismissCustomFeedError(); addingFeed = true },
            onDismiss = { viewModel.dismissCustomFeedError(); onClose() },
        )
    }
}

/** Row value for the Topics setting: "All topics" or the chosen ones. */
@Composable
fun newsTopicsSummary(selection: NewsSourceSelection): String =
    if (selection.topics.isEmpty()) stringResource(R.string.news_topic_all)
    else selection.topics.map { topicLabel(it) }.joinToString(", ")

/** Row value for the Sources setting: the followed providers, in tab order. */
@Composable
fun newsSourcesSummary(selection: NewsSourceSelection): String =
    selection.providers.joinToString(", ") { it.name }.ifEmpty { stringResource(R.string.news_no_sources) }

@Composable
internal fun topicLabel(topic: NewsTopic): String = stringResource(when (topic) {
    NewsTopic.WORLD -> R.string.news_topic_world
    NewsTopic.POLITICS -> R.string.news_topic_politics
    NewsTopic.BUSINESS -> R.string.news_topic_business
    NewsTopic.TECH -> R.string.news_topic_tech
    NewsTopic.SPORTS -> R.string.news_topic_sports
    NewsTopic.HEALTH -> R.string.news_topic_health
    NewsTopic.SCIENCE -> R.string.news_topic_science
    NewsTopic.ENTERTAINMENT -> R.string.news_topic_entertainment
})

@Composable
private fun NewsDialogFrame(title: String, description: String, testTag: String, fillHeight: Boolean,
                            content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit) {
    Column(Modifier.widthIn(max = 620.dp).fillMaxWidth(0.92f)
        .then(if (fillHeight) Modifier.fillMaxHeight(0.9f) else Modifier)
        .clip(RoundedCornerShape(16.dp)).background(Color(0xFF141414)).padding(22.dp)
        .testTag(testTag)) {
        Text(title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text(description, color = Color.LightGray, fontSize = 13.sp)
        Spacer(Modifier.height(14.dp))
        content()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NewsTopicsDialog(topics: List<NewsTopic>, onToggleTopic: (NewsTopic?) -> Unit, onDismiss: () -> Unit) {
    val first = remember { FocusRequester() }
    LaunchedEffect(Unit) { delay(80); runCatching { first.requestFocus() } }
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        NewsDialogFrame(stringResource(R.string.news_topics_section), stringResource(R.string.news_topics_desc),
            "news-topics-dialog", fillHeight = false) {
            FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OledControl(stringResource(R.string.news_topic_all), selected = topics.isEmpty(),
                    modifier = Modifier.focusRequester(first).testTag("news-topic-all"), onClick = { onToggleTopic(null) })
                NewsTopic.entries.forEach { topic ->
                    OledControl(topicLabel(topic), selected = topic in topics,
                        trailingIcon = if (topic in topics) Icons.Outlined.Check else null,
                        modifier = Modifier.testTag("news-topic-${topic.id}"), onClick = { onToggleTopic(topic) })
                }
            }
            Spacer(Modifier.height(18.dp))
            OledControl(stringResource(R.string.news_done), selected = true, onClick = onDismiss)
        }
    }
}

@Composable
private fun NewsSourcesDialog(
    selection: NewsSourceSelection,
    isProbing: Boolean,
    feedError: Boolean,
    onToggle: (String) -> Unit,
    onRemove: (String) -> Unit,
    onAddFeed: () -> Unit,
    onDismiss: () -> Unit,
) {
    val firstRow = remember { FocusRequester() }
    LaunchedEffect(Unit) { delay(80); runCatching { firstRow.requestFocus() } }
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        NewsDialogFrame(stringResource(R.string.news_sources_section), stringResource(R.string.news_sources_desc),
            "news-sources-dialog", fillHeight = true) {
            LazyColumn(Modifier.weight(1f, fill = false), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                val all = NewsProviders.presets + selection.customProviders
                itemsIndexed(all, key = { _, p -> p.id }) { index, provider ->
                    val position = selection.selectedIds.indexOf(provider.id).takeIf { it >= 0 }?.plus(1)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        NewsSourceRow(provider, position,
                            Modifier.weight(1f).then(if (index == 0) Modifier.focusRequester(firstRow) else Modifier)) {
                            onToggle(provider.id)
                        }
                        if (provider.isCustom) OledControl(stringResource(R.string.news_remove), onClick = { onRemove(provider.id) })
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            when {
                isProbing -> Text(stringResource(R.string.news_add_feed_checking), color = Color.LightGray, fontSize = 13.sp)
                feedError -> Text(stringResource(R.string.news_add_feed_failed), color = Color(0xFFFF8A80), fontSize = 13.sp)
            }
            Spacer(Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OledControl("+ " + stringResource(R.string.news_add_feed), onClick = onAddFeed)
                OledControl(stringResource(R.string.news_done), selected = true, onClick = onDismiss)
            }
        }
    }
}

@Composable
private fun NewsSourceRow(provider: NewsProvider, position: Int?, modifier: Modifier, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(Color.White)
    Row(modifier.onFocusChanged { focused = it.isFocused }
        .clip(RoundedCornerShape(8.dp))
        .background(if (focused) Color.White.copy(alpha = 0.14f) else Color.Transparent)
        .border(2.dp, if (focused) accent else Color.Transparent, RoundedCornerShape(8.dp))
        .clickable(onClick = onClick)
        .padding(horizontal = 12.dp, vertical = 10.dp)
        .testTag("news-source-${provider.id}"),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Icon(if (position != null) Icons.Outlined.CheckCircle else Icons.Outlined.RadioButtonUnchecked, contentDescription = null,
            tint = if (position != null) accent else Color.White.copy(alpha = 0.5f), modifier = Modifier.size(22.dp))
        Column(Modifier.weight(1f)) {
            Text(provider.name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, maxLines = 1)
            Text(if (provider.isCustom) stringResource(R.string.news_custom_feed) + " · " + provider.url
                else java.util.Locale.forLanguageTag(provider.language).displayLanguage,
                color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        if (position != null) Text("#$position", color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
    }
}
