package com.arflix.tv.ui.screens.news

import android.text.format.DateUtils
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import com.arflix.tv.data.news.NewsArticle
import com.arflix.tv.data.news.NewsProvider
import com.arflix.tv.ui.components.AppTopBar
import com.arflix.tv.ui.components.AppTopBarContentTopInset
import com.arflix.tv.ui.components.CompactLoadingIndicator
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.components.QrCodeImage
import com.arflix.tv.ui.components.ReconcileTopBarFocus
import com.arflix.tv.ui.components.SidebarItem
import com.arflix.tv.ui.components.topBarFocusedItem
import com.arflix.tv.ui.components.topBarMaxIndex
import com.arflix.tv.ui.components.topBarSelectedIndex
import com.arflix.tv.ui.focus.mirrorHorizontalForRtl
import com.arflix.tv.ui.screens.watchlist.LibraryTabsTopInset
import com.arflix.tv.ui.screens.watchlist.OledControl
import com.arflix.tv.ui.skin.resolveAccentColor
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.util.LocalDeviceType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** Hebrew and Arabic headlines lay out right-to-left whatever the app language is. */
private val ContentDirection = TextStyle(textDirection = TextDirection.Content)

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    currentProfile: Profile? = null,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToWatchlist: () -> Unit = {},
    onNavigateToTv: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onSwitchProfile: () -> Unit = {},
    onBack: () -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val touch = LocalDeviceType.current.isTouchDevice()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val hasProfile = currentProfile != null

    var topFocused by remember { mutableStateOf(false) }
    var topIndex by remember { mutableIntStateOf(topBarSelectedIndex(SidebarItem.NEWS, hasProfile).coerceAtLeast(0)) }
    ReconcileTopBarFocus(topIndex, hasProfile) { topIndex = it }
    val topFocus = remember { FocusRequester() }
    val firstChip = remember { FocusRequester() }
    val firstRow = remember { FocusRequester() }
    var initialFocusPlaced by remember { mutableStateOf(false) }
    var focusedLink by rememberSaveable { mutableStateOf<String?>(null) }
    val rowFocus = remember { mutableStateMapOf<String, FocusRequester>() }

    val articles = state.articles
    val focusedArticle = articles.firstOrNull { it.link == focusedLink } ?: articles.firstOrNull()

    BackHandler(state.openArticle == null) {
        if (!touch && !topFocused) topFocus.requestFocus() else onBack()
    }

    // Returning from the reader puts focus back on the headline that was opened.
    var lastOpened by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(state.openArticle) {
        val open = state.openArticle
        if (open != null) lastOpened = open.link
        else if (!touch) lastOpened?.let { link -> runCatching { rowFocus[link]?.requestFocus() } }
    }

    BoxWithConstraints(Modifier.fillMaxSize().background(Color.Black).testTag("news-screen")) {
        val compact = touch && maxWidth < 720.dp
        if (!touch) {
            Box(Modifier.fillMaxWidth().height(AppTopBarContentTopInset).focusRequester(topFocus)
                .onFocusChanged { topFocused = it.isFocused }
                .onKeyEvent { event ->
                    if (event.type != KeyEventType.KeyDown) false else when (event.key.mirrorHorizontalForRtl(isRtl)) {
                        Key.DirectionLeft -> { topIndex = (topIndex - 1).coerceAtLeast(0); true }
                        Key.DirectionRight -> { topIndex = (topIndex + 1).coerceAtMost(topBarMaxIndex(hasProfile)); true }
                        Key.DirectionDown -> { runCatching { firstChip.requestFocus() }; true }
                        Key.Enter, Key.DirectionCenter, Key.NumPadEnter -> {
                            when (topBarFocusedItem(topIndex, hasProfile)) {
                                SidebarItem.HOME -> onNavigateToHome()
                                SidebarItem.SEARCH -> onNavigateToSearch()
                                SidebarItem.WATCHLIST -> onNavigateToWatchlist()
                                SidebarItem.TV -> onNavigateToTv()
                                SidebarItem.SETTINGS -> onNavigateToSettings()
                                SidebarItem.NEWS -> runCatching { firstChip.requestFocus() }
                                null -> onSwitchProfile()
                            }; true
                        }
                        else -> false
                    }
                }.focusable()) {
                AppTopBar(SidebarItem.NEWS, topFocused, topIndex, profile = currentProfile)
            }
        }

        Column(Modifier.fillMaxSize()
            .then(if (touch) Modifier.statusBarsPadding() else Modifier.padding(top = LibraryTabsTopInset))
            .padding(horizontal = if (compact) 14.dp else 26.dp)) {
            if (touch) Spacer(Modifier.height(10.dp))
            // Provider tabs. "All" mixes every followed provider.
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).heightIn(min = 48.dp)
                .padding(bottom = 8.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val chipFocus = Modifier.focusRequester(firstChip)
                if (state.providers.size > 1) {
                    OledControl(stringResource(R.string.news_all), modifier = chipFocus, selected = state.activeProviderId == null,
                        compact = compact, onClick = { viewModel.selectProvider(null) })
                }
                state.providers.forEachIndexed { index, provider ->
                    OledControl(provider.name, modifier = if (index == 0 && state.providers.size == 1) chipFocus else Modifier,
                        selected = state.activeProviderId == provider.id, compact = compact,
                        onClick = { viewModel.selectProvider(provider.id) })
                }
                OledControl("⟳ " + stringResource(R.string.news_refresh),
                    modifier = if (state.providers.isEmpty()) chipFocus else Modifier, compact = compact,
                    onClick = { viewModel.refresh() })
            }
            Text(stringResource(R.string.news_change_providers_hint),
                color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 4.dp).testTag("news-providers-hint"))
            if (state.selection.topics.isNotEmpty()) {
                val topicNames = state.selection.topics.map { topicLabel(it) }.joinToString(" · ")
                val uncovered = state.selection.uncoveredProviders
                Text(stringResource(R.string.news_topics_active, topicNames) +
                    if (uncovered.isEmpty()) "" else "   —   " +
                        stringResource(R.string.news_topics_uncovered, uncovered.joinToString(", ") { it.name }),
                    color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 6.dp).testTag("news-topics-line"))
            }
            if (state.failedProviders.isNotEmpty() && !state.isLoading) {
                Text(stringResource(R.string.news_load_failed, state.failedProviders.joinToString(", ")),
                    color = Color(0xFFFF8A80), fontSize = 13.sp, modifier = Modifier.padding(bottom = 6.dp))
            }
            LaunchedEffect(articles.isNotEmpty(), state.providers.isEmpty()) {
                if (!touch && !initialFocusPlaced && (articles.isNotEmpty() || state.providers.isEmpty() && !state.isLoading)) {
                    initialFocusPlaced = true
                    delay(50)
                    runCatching { if (articles.isNotEmpty()) firstRow.requestFocus() else firstChip.requestFocus() }
                }
            }
            when {
                state.selection.providers.isEmpty() -> NewsEmptyState(stringResource(R.string.news_no_sources),
                    stringResource(R.string.news_no_sources_desc))
                state.providers.isEmpty() -> NewsEmptyState(stringResource(R.string.news_no_topic_sources),
                    stringResource(R.string.news_no_topic_sources_desc))
                articles.isEmpty() && state.isLoading -> CompactLoadingIndicator(Modifier.fillMaxSize())
                articles.isEmpty() -> NewsEmptyState(stringResource(R.string.news_no_articles), null)
                touch -> LazyColumn(Modifier.fillMaxSize().testTag("news-list"),
                    contentPadding = PaddingValues(bottom = LocalBottomBarInset.current + 16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    itemsIndexed(articles, key = { _, a -> a.link }) { _, article ->
                        NewsTouchCard(article, state.provider(article.providerId), wide = !compact) {
                            viewModel.openArticle(article)
                        }
                    }
                }
                else -> Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(28.dp)) {
                    LazyColumn(Modifier.weight(0.46f).fillMaxHeight().testTag("news-list"),
                        contentPadding = PaddingValues(top = 4.dp, bottom = 40.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        itemsIndexed(articles, key = { _, a -> a.link }) { index, article ->
                            val requester = rowFocus.getOrPut(article.link) { FocusRequester() }
                            NewsHeadlineRow(article, state.provider(article.providerId),
                                modifier = Modifier.focusRequester(if (index == 0) firstRow else requester)
                                    .then(if (index == 0) Modifier.focusRequester(requester) else Modifier),
                                onFocused = { focusedLink = article.link },
                                onClick = { viewModel.openArticle(article) })
                        }
                    }
                    val preview = focusedArticle
                    if (preview != null) {
                        NewsPreviewPane(preview, state.provider(preview.providerId), Modifier.weight(0.54f).fillMaxHeight())
                    }
                }
            }
        }

        state.openArticle?.let { article ->
            NewsReader(article, state.provider(article.providerId), touch, onClose = viewModel::closeArticle)
        }
    }
}

@Composable
private fun NewsEmptyState(title: String, body: String?) {
    Column(Modifier.fillMaxSize().padding(top = 60.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Outlined.Newspaper, contentDescription = null, tint = Color.White.copy(alpha = 0.5f), modifier = Modifier.size(48.dp))
        Spacer(Modifier.height(14.dp))
        Text(title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        if (body != null) {
            Spacer(Modifier.height(6.dp))
            Text(body, color = Color.LightGray, fontSize = 14.sp)
        }
    }
}

@Composable
private fun articleMeta(article: NewsArticle, provider: NewsProvider?): String {
    val name = provider?.name.orEmpty()
    val time = article.publishedAt?.let { at ->
        DateUtils.getRelativeTimeSpanString(at, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_RELATIVE).toString()
    }
    return listOfNotNull(name.takeIf { it.isNotBlank() }, time).joinToString(" · ")
}

@Composable
private fun NewsHeadlineRow(
    article: NewsArticle,
    provider: NewsProvider?,
    modifier: Modifier,
    onFocused: () -> Unit,
    onClick: () -> Unit,
) {
    var focused by remember { mutableStateOf(false) }
    val accent = resolveAccentColor(Color.White)
    val background by animateColorAsState(if (focused) Color.White.copy(alpha = 0.14f) else Color.Transparent, tween(120), label = "news-row")
    Row(modifier.fillMaxWidth()
        .onFocusChanged { focused = it.isFocused; if (it.isFocused) onFocused() }
        .clip(RoundedCornerShape(10.dp))
        .background(background)
        .border(2.dp, if (focused) accent else Color.Transparent, RoundedCornerShape(10.dp))
        .clickable(onClick = onClick)
        .padding(8.dp)
        .testTag("news-row"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        if (article.imageUrl != null) {
            AsyncImage(model = article.imageUrl, contentDescription = null, contentScale = ContentScale.Crop,
                modifier = Modifier.width(112.dp).aspectRatio(16f / 9f).clip(RoundedCornerShape(6.dp)).background(BackgroundElevated))
        }
        Column(Modifier.weight(1f)) {
            Text(articleMeta(article, provider), color = Color.White.copy(alpha = 0.55f), fontSize = 11.sp, maxLines = 1)
            Spacer(Modifier.height(2.dp))
            Text(article.title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                maxLines = 2, overflow = TextOverflow.Ellipsis, style = ContentDirection, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun NewsPreviewPane(article: NewsArticle, provider: NewsProvider?, modifier: Modifier) {
    val image = article.imageUrl
    val summary = article.summary
    Column(modifier.padding(top = 4.dp)) {
        if (image != null) {
            AsyncImage(model = image, contentDescription = null, contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f).heightIn(max = 300.dp)
                    .clip(RoundedCornerShape(12.dp)).background(BackgroundElevated))
            Spacer(Modifier.height(14.dp))
        }
        Text(articleMeta(article, provider), color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
        Spacer(Modifier.height(6.dp))
        Text(article.title, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold, lineHeight = 30.sp,
            maxLines = 3, overflow = TextOverflow.Ellipsis, style = ContentDirection, modifier = Modifier.fillMaxWidth())
        if (summary.isNotBlank()) {
            Spacer(Modifier.height(10.dp))
            Text(summary, color = Color.White.copy(alpha = 0.8f), fontSize = 15.sp, lineHeight = 22.sp,
                maxLines = if (image != null) 4 else 10, overflow = TextOverflow.Ellipsis, style = ContentDirection,
                modifier = Modifier.fillMaxWidth())
        }
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.news_read_hint), color = Color.White.copy(alpha = 0.45f), fontSize = 12.sp)
    }
}

@Composable
private fun NewsTouchCard(article: NewsArticle, provider: NewsProvider?, wide: Boolean, onClick: () -> Unit) {
    val content: @Composable () -> Unit = {
        Text(articleMeta(article, provider), color = Color.White.copy(alpha = 0.55f), fontSize = 12.sp, maxLines = 1)
        Spacer(Modifier.height(3.dp))
        Text(article.title, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, lineHeight = 22.sp,
            maxLines = 3, overflow = TextOverflow.Ellipsis, style = ContentDirection, modifier = Modifier.fillMaxWidth())
        if (article.summary.isNotBlank()) {
            Spacer(Modifier.height(4.dp))
            Text(article.summary, color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp, lineHeight = 19.sp,
                maxLines = 2, overflow = TextOverflow.Ellipsis, style = ContentDirection, modifier = Modifier.fillMaxWidth())
        }
    }
    val image: @Composable (Modifier) -> Unit = { m ->
        AsyncImage(model = article.imageUrl, contentDescription = null, contentScale = ContentScale.Crop,
            modifier = m.aspectRatio(16f / 9f).clip(RoundedCornerShape(10.dp)).background(BackgroundElevated))
    }
    if (wide) {
        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).clickable(onClick = onClick).testTag("news-row"),
            horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            if (article.imageUrl != null) image(Modifier.width(220.dp))
            Column(Modifier.weight(1f)) { content() }
        }
    } else {
        Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).clickable(onClick = onClick).testTag("news-row")) {
            if (article.imageUrl != null) {
                image(Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
            }
            content()
        }
    }
}

@Composable
private fun NewsReader(
    article: NewsArticle,
    provider: NewsProvider?,
    touch: Boolean,
    onClose: () -> Unit,
) {
    BackHandler(onBack = onClose)
    val list = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val focus = remember { FocusRequester() }
    val step = with(LocalDensity.current) { 260.dp.toPx() }
    LaunchedEffect(article.link) {
        list.scrollToItem(0)
        if (!touch) runCatching { focus.requestFocus() }
    }
    // Only the feed's own summary. The full story stays on the publisher's site: it opens in the browser
    // (or by QR code on a TV), and the article page is never fetched or extracted here.
    val image = article.imageUrl
    val paragraphs = listOf(article.summary).filter { it.isNotBlank() }
    BoxWithConstraints(Modifier.fillMaxSize().background(Color.Black).testTag("news-reader")
        .focusRequester(focus)
        .onPreviewKeyEvent { event ->
            if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
            val delta = when (event.key) {
                Key.DirectionDown, Key.PageDown, Key.ChannelDown -> step
                Key.DirectionUp, Key.PageUp, Key.ChannelUp -> -step
                else -> return@onPreviewKeyEvent event.key == Key.DirectionLeft || event.key == Key.DirectionRight
            }
            scope.launch { list.animateScrollBy(delta) }
            true
        }
        .focusable(),
        contentAlignment = Alignment.TopCenter) {
        // The picture takes at most about a third of the screen, so the opening paragraphs show without scrolling.
        val columnWidth = minOf(maxWidth, 860.dp) - 48.dp
        val imageHeight = minOf(columnWidth * 9f / 16f, maxHeight * 0.36f)
        LazyColumn(state = list, modifier = Modifier.fillMaxHeight().widthIn(max = 860.dp).fillMaxWidth()
            .then(if (touch) Modifier.statusBarsPadding() else Modifier),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = if (touch) 56.dp else 28.dp, bottom = 60.dp)) {
            if (image != null) item("image") {
                AsyncImage(model = image, contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(imageHeight).clip(RoundedCornerShape(12.dp)).background(BackgroundElevated))
                Spacer(Modifier.height(14.dp))
            }
            item("meta") {
                Text(articleMeta(article, provider), color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)
                Spacer(Modifier.height(6.dp))
                Text(article.title, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 33.sp,
                    style = ContentDirection, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(14.dp))
            }
            itemsIndexed(paragraphs) { _, paragraph ->
                Text(paragraph, color = Color.White.copy(alpha = 0.88f), fontSize = 19.sp, lineHeight = 30.sp,
                    style = ContentDirection, modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp))
            }
            item("source") { NewsOpenArticle(article.link, touch) }
        }
        if (touch) {
            Box(Modifier.align(Alignment.TopStart).statusBarsPadding().padding(8.dp).size(44.dp)
                .clip(RoundedCornerShape(22.dp)).background(Color.Black.copy(alpha = 0.6f)).clickable(onClick = onClose),
                contentAlignment = Alignment.Center) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
        }
    }
}

/** The way to the full story on the publisher's own site: a browser on phones, a QR code on TVs. */
@Composable
private fun NewsOpenArticle(link: String, touch: Boolean) {
    val context = androidx.compose.ui.platform.LocalContext.current
    Column(Modifier.fillMaxWidth().padding(top = 10.dp).testTag("news-open-article")) {
        if (touch) {
            OledControl(stringResource(R.string.news_open_article), selected = true, onClick = {
                runCatching {
                    context.startActivity(android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(link))
                        .addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            })
        } else {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                Box(Modifier.size(132.dp).clip(RoundedCornerShape(10.dp)).background(Color.White).padding(8.dp)) {
                    QrCodeImage(data = link, sizePx = 400, modifier = Modifier.fillMaxSize())
                }
                Column(Modifier.weight(1f)) {
                    Text(stringResource(R.string.news_scan_to_read), color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Text(link, color = Color.White.copy(alpha = 0.45f), fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}
