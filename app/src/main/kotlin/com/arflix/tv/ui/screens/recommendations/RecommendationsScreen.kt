package com.arflix.tv.ui.screens.recommendations

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.material3.Button
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.ui.components.MediaCard
import com.arflix.tv.ui.focus.arvioDpadFocusGroup
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.appBackgroundDark
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI state for the Recommendations screen.
 * @param isEmptyResult true when the API returned successfully but had zero results
 */
data class RecommendationsUiState(
    val items: List<MediaItem> = emptyList(),
    val isLoading: Boolean = true,
    val isEmptyResult: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecommendationsUiState())
    val uiState: StateFlow<RecommendationsUiState> = _uiState.asStateFlow()

    fun load(mediaType: MediaType, mediaId: Int) {
        if (!_uiState.value.isLoading && _uiState.value.items.isNotEmpty()) return
        viewModelScope.launch {
            _uiState.value = RecommendationsUiState(isLoading = true)
            val result = runCatching {
                mediaRepository.getSimilar(mediaType, mediaId)
            }

            _uiState.value = result.fold(
                onSuccess = { items ->
                    if (items.isEmpty()) {
                        RecommendationsUiState(
                            isLoading = false,
                            isEmptyResult = true
                        )
                    } else {
                        RecommendationsUiState(
                            items = items,
                            isLoading = false
                        )
                    }
                },
                onFailure = { throwable ->
                    RecommendationsUiState(
                        isLoading = false,
                        error = throwable.message ?: "Unable to load recommendations"
                    )
                }
            )
        }
    }

    fun retry(mediaType: MediaType, mediaId: Int) {
        _uiState.value = RecommendationsUiState(isLoading = true)
        load(mediaType, mediaId)
    }
}

@Composable
fun RecommendationsScreen(
    mediaType: MediaType,
    mediaId: Int,
    mediaTitle: String,
    viewModel: RecommendationsViewModel = hiltViewModel(),
    onNavigateToDetails: (MediaType, Int) -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(mediaType, mediaId) { viewModel.load(mediaType, mediaId) }
    BackHandler(onBack = onBack)

    val isMobile = LocalDeviceType.current.isTouchDevice()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Responsive grid columns — same formula as CollectionDetailsScreen poster mode
    val gridColumns = if (isMobile) {
        if (isLandscape) 4 else 3
    } else {
        when {
            configuration.screenWidthDp >= 2200 -> 8
            configuration.screenWidthDp >= 1600 -> 7
            else -> 5
        }
    }

    val cardWidth = if (isMobile) 138.dp else when {
        configuration.screenWidthDp >= 2200 -> 196.dp
        configuration.screenWidthDp >= 1600 -> 184.dp
        else -> 172.dp
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackgroundDark())
            // Key.Escape for keyboard support — BackHandler handles system/remote back
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Escape) {
                    onBack()
                    true
                } else {
                    false
                }
            }
    ) {
        // Accent gradient backdrop
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF2F9C95).copy(alpha = 0.25f),
                            Color.Transparent
                        )
                    )
                )
        )

        if (uiState.isLoading) {
            // Skeleton grid
            TvLazyVerticalGrid(
                columns = TvGridCells.Fixed(gridColumns),
                modifier = Modifier
                    .fillMaxSize()
                    .arvioDpadFocusGroup()
                    .padding(top = 72.dp),
                contentPadding = PaddingValues(
                    start = 42.dp,
                    end = 42.dp,
                    top = 20.dp,
                    bottom = 48.dp
                ),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                itemsIndexed((1..gridColumns * 3).toList()) { _, _ ->
                    Box(
                        modifier = Modifier
                            .height(cardWidth * 1.5f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.05f))
                    )
                }
            }

            // Title overlay during loading
            Text(
                text = stringResource(R.string.more_like, mediaTitle),
                style = ArflixTypography.sectionTitle.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = TextPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 42.dp, top = 18.dp, end = 42.dp)
            )
        } else if (uiState.error != null) {
            // Network / API error state — show retry button
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = uiState.error ?: stringResource(R.string.recommendations_network_error),
                        style = ArflixTypography.body,
                        color = TextSecondary
                    )
                    Button(
                        onClick = { viewModel.retry(mediaType, mediaId) }
                    ) {
                        Text(stringResource(R.string.retry))
                    }
                }
            }
        } else if (uiState.isEmptyResult) {
            // Graceful empty state — API returned successfully but no recommendations exist
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_recommendations_found),
                    style = ArflixTypography.body,
                    color = TextSecondary
                )
            }
        } else {
            // Recommendations grid
            TvLazyVerticalGrid(
                columns = TvGridCells.Fixed(gridColumns),
                modifier = Modifier
                    .fillMaxSize()
                    .arvioDpadFocusGroup()
                    .padding(top = 56.dp),
                contentPadding = PaddingValues(
                    start = 42.dp,
                    end = 42.dp,
                    top = 4.dp,
                    bottom = 48.dp
                ),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item(
                    span = { TvGridItemSpan(maxLineSpan) },
                    contentType = "header"
                ) {
                    Text(
                        text = stringResource(R.string.more_like, mediaTitle),
                        style = ArflixTypography.sectionTitle.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = TextPrimary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }

                itemsIndexed(
                    items = uiState.items,
                    key = { _, item -> "${item.mediaType}-${item.id}" },
                    contentType = { _, _ -> "recommendation_card" }
                ) { _, item ->
                    MediaCard(
                        item = item,
                        width = cardWidth,
                        isLandscape = false,
                        showTitle = true,
                        titleMaxLines = 2,
                        onClick = { onNavigateToDetails(item.mediaType, item.id) }
                    )
                }
            }
        }
    }
}
