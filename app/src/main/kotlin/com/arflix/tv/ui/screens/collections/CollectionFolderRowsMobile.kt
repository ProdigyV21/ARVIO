package com.arflix.tv.ui.screens.collections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arflix.tv.R
import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.ui.components.LocalBottomBarInset
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.theme.appBackgroundDark

/** Poster width of a folder row's cards on a phone or tablet. */
private val FOLDER_CARD_WIDTH = 116.dp

/**
 * An imported folder with several catalogs on a touch device: the folder's artwork, then one
 * row per catalog under its own name, each with a "View all" that opens that catalog's grid.
 * TV shows the same rows through [ServiceHomeScreen].
 */
@Composable
internal fun CollectionFolderRowsMobile(
    folder: CatalogConfig,
    onOpen: (MediaItem) -> Unit,
    onViewAll: (ServiceHome.ViewAll) -> Unit,
    onBack: () -> Unit,
    viewModel: ServiceHomeViewModel = hiltViewModel()
) {
    LaunchedEffect(folder.id) { viewModel.load(folder) }
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val kids by viewModel.kidsFilterState.collectAsStateWithLifecycle()
    val cardLogoUrls by viewModel.cardLogoUrls.collectAsStateWithLifecycle()
    val page = state.page
    val rows = remember(page.rows, kids) { viewModel.visibleRows(page, kids) }
    val lead = remember(page.billboard, rows, kids) {
        page.billboard?.takeIf { viewModel.allows(it, kids) }
            ?: rows.flatMap { it.items }.firstOrNull { !it.backdrop.isNullOrBlank() }
    }
    val compact = LocalConfiguration.current.screenHeightDp < 480
    val openedAt = rememberCollectionOpenedAt()

    Box(Modifier.fillMaxSize().background(appBackgroundDark()).testTag("collection_folder_rows")) {
        CollectionBackdrop(folder, lead, true, collectionSpotlightHeight(true, compact) + 150.dp)
        Column(Modifier.fillMaxSize().padding(top = 12.dp)) {
            Row(
                Modifier.fillMaxWidth().height(52.dp).padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back), tint = Color.White)
                }
                Text(
                    folder.title, color = Color.White, fontSize = 18.sp, maxLines = 1,
                    overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f)
                )
            }
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 48.dp + LocalBottomBarInset.current),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                item(key = "spotlight", contentType = "spotlight") {
                    // The lead title arrives with the first row; it rises in like the rows do.
                    if (lead != null) CollectionSpotlightEntrance(openedAt) {
                        CollectionSpotlight(
                            folder, lead, true, compact, onBack,
                            clearLogoUrl = cardLogoUrls["${lead.mediaType}_${lead.id}"],
                            showBackButton = false
                        )
                    } else CollectionSpotlight(
                        folder, null, true, compact, onBack,
                        clearLogoUrl = null,
                        showBackButton = false
                    )
                }
                when {
                    rows.isEmpty() && page.isLoading -> item(key = "loading") {
                        Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(24.dp))
                        }
                    }
                    rows.isEmpty() -> item(key = "empty") {
                        Column(
                            Modifier.fillMaxWidth().padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                stringResource(if (page.sourceLoadFailed) R.string.collection_failed_load else R.string.collection_empty),
                                color = Color.White
                            )
                            TextButton(onClick = viewModel::retry) { Text(stringResource(R.string.retry), color = Color.White) }
                        }
                    }
                    else -> itemsIndexed(rows, key = { _, it -> it.row.id }, contentType = { _, _ -> "catalog_row" }) { index, row ->
                        FolderCatalogRow(
                            title = serviceHomeRowTitle(row, folder.title),
                            items = row.items,
                            onOpen = onOpen,
                            onViewAll = row.row.viewAll?.let { viewAll -> { onViewAll(viewAll) } },
                            modifier = Modifier.collectionEntrance(openedAt, index + 1)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FolderCatalogRow(
    title: String,
    items: List<MediaItem>,
    onOpen: (MediaItem) -> Unit,
    onViewAll: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .then(if (onViewAll != null) Modifier.clickable(onClick = onViewAll) else Modifier)
                .padding(start = 20.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f)
            )
            if (onViewAll != null) {
                IconButton(onClick = onViewAll) {
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        stringResource(R.string.home_view_all),
                        tint = Color.White
                    )
                }
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items, key = { "${it.mediaType}_${it.id}" }) { item ->
                MediaCard(
                    item = item,
                    width = FOLDER_CARD_WIDTH,
                    isLandscape = false,
                    showTitle = true,
                    titleMaxLines = 2,
                    titleMinLines = 2,
                    onClick = { onOpen(item) }
                )
            }
        }
    }
}

/** The spotlight's entrance: it fades in and rises with the first row. */
@Composable
private fun CollectionSpotlightEntrance(openedAt: Long, content: @Composable () -> Unit) {
    Box(Modifier.collectionEntrance(openedAt, 0)) { content() }
}
