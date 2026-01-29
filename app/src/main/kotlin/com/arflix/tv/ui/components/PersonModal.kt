package com.arflix.tv.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.arflix.tv.data.model.MediaItem
import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.model.PersonDetails
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundDark
import com.arflix.tv.ui.theme.Pink
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary

/**
 * Premium full-screen modal for displaying person/cast details
 * Clean, minimal design with horizontal cards like homepage
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PersonModal(
    isVisible: Boolean,
    person: PersonDetails?,
    isLoading: Boolean = false,
    onClose: () -> Unit = {},
    onMediaClick: (MediaType, Int) -> Unit = { _, _ -> }
) {
    var focusedKnownForIndex by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }

    // Request focus when modal becomes visible
    androidx.compose.runtime.LaunchedEffect(isVisible) {
        if (isVisible) {
            kotlinx.coroutines.delay(100)
            try {
                focusRequester.requestFocus()
            } catch (_: Exception) {
                // Ignore focus request failures
            }
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInHorizontally(initialOffsetX = { it }),
        exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it })
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
                .focusable()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF1a0a20), // Dark purple center
                            Color(0xFF0f0812), // Darker purple
                            Color(0xFF0a0a0a)  // Almost black edges
                        ),
                        radius = 1200f
                    )
                )
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown) {
                        when (event.key) {
                            Key.Back, Key.Escape -> {
                                onClose()
                                true
                            }
                            Key.DirectionLeft -> {
                                if (focusedKnownForIndex > 0) focusedKnownForIndex--
                                true
                            }
                            Key.DirectionRight -> {
                                val max = (person?.knownFor?.size ?: 1) - 1
                                if (focusedKnownForIndex < max) focusedKnownForIndex++
                                true
                            }
                            Key.Enter, Key.DirectionCenter -> {
                                person?.knownFor?.getOrNull(focusedKnownForIndex)?.let { item ->
                                    onMediaClick(item.mediaType, item.id)
                                }
                                true
                            }
                            else -> false
                        }
                    } else false
                }
        ) {
            // Subtle purple gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Pink.copy(alpha = 0.08f),
                                Color.Transparent,
                                Pink.copy(alpha = 0.04f)
                            )
                        )
                    )
            )

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator(color = Pink, size = 64.dp)
                }
            } else if (person != null) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 60.dp, end = 48.dp, top = 48.dp, bottom = 48.dp)
                ) {
                    // Left side - Profile photo and info (compact)
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Profile photo - smaller, elegant
                        Box(
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(
                                    width = 2.dp,
                                    color = Color.White.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            if (person.profilePath != null) {
                                AsyncImage(
                                    model = person.profilePath,
                                    contentDescription = person.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color(0xFF1a1a1a)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = person.name.firstOrNull()?.toString() ?: "?",
                                        style = ArflixTypography.heroTitle.copy(fontSize = 48.sp),
                                        color = TextSecondary.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Name
                        Text(
                            text = person.name,
                            style = ArflixTypography.sectionTitle.copy(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = TextPrimary,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Birth info - subtle
                        if (person.birthday != null) {
                            Text(
                                text = person.birthday,
                                style = ArflixTypography.body.copy(fontSize = 13.sp),
                                color = TextSecondary.copy(alpha = 0.7f)
                            )
                        }

                        if (person.placeOfBirth != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = person.placeOfBirth,
                                style = ArflixTypography.caption.copy(fontSize = 11.sp),
                                color = TextSecondary.copy(alpha = 0.5f),
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(48.dp))

                    // Right side - Biography and Known For
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .verticalScroll(scrollState)
                    ) {
                        // Biography section
                        if (person.biography.isNotEmpty()) {
                            Text(
                                text = "Biography",
                                style = ArflixTypography.sectionTitle.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 0.5.sp
                                ),
                                color = TextPrimary.copy(alpha = 0.9f)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = person.biography,
                                style = ArflixTypography.body.copy(
                                    fontSize = 14.sp,
                                    lineHeight = 22.sp
                                ),
                                color = TextSecondary.copy(alpha = 0.8f),
                                maxLines = 10,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(32.dp))
                        }

                        // Known For section
                        if (person.knownFor.isNotEmpty()) {
                            val knownForListState = androidx.tv.foundation.lazy.list.rememberTvLazyListState()

                            // Auto-scroll when focus changes
                            androidx.compose.runtime.LaunchedEffect(focusedKnownForIndex) {
                                knownForListState.animateScrollToItem(focusedKnownForIndex)
                            }

                            Text(
                                text = "Known For",
                                style = ArflixTypography.sectionTitle.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 0.5.sp
                                ),
                                color = TextPrimary.copy(alpha = 0.9f)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Horizontal cards like homepage
                            TvLazyRow(
                                state = knownForListState,
                                contentPadding = PaddingValues(end = 24.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                itemsIndexed(person.knownFor) { index, item ->
                                    HorizontalKnownForCard(
                                        item = item,
                                        isFocused = index == focusedKnownForIndex
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }
}

/**
 * Horizontal card for Known For - matches homepage style (16:9 aspect ratio)
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun HorizontalKnownForCard(
    item: MediaItem,
    isFocused: Boolean
) {
    val cardWidth = 220.dp
    val cardHeight = 124.dp // 16:9 aspect ratio

    Column(
        modifier = Modifier.width(cardWidth),
        horizontalAlignment = Alignment.Start
    ) {
        // Card with backdrop image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = if (isFocused) 3.dp else 0.dp,
                    color = if (isFocused) Color.White else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(Color(0xFF1a1a1a))
        ) {
            // Use backdrop if available, otherwise poster
            val imageUrl = item.backdrop?.takeIf { it.isNotEmpty() } ?: item.image

            if (imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Gradient overlay at bottom
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )

            // Title overlay at bottom
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = item.title,
                        style = ArflixTypography.cardTitle.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = item.year.toString(),
                            style = ArflixTypography.caption.copy(fontSize = 11.sp),
                            color = Color.White.copy(alpha = 0.7f)
                        )
                        Box(
                            modifier = Modifier
                                .size(3.dp)
                                .background(Color.White.copy(alpha = 0.5f), CircleShape)
                        )
                        Text(
                            text = if (item.mediaType == MediaType.TV) "TV Series" else "Movie",
                            style = ArflixTypography.caption.copy(fontSize = 11.sp),
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            // Focus glow effect
            if (isFocused) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.1f),
                                    Color.Transparent
                                )
                            )
                        )
                )
            }
        }

        // Character/role name below the card
        if (item.character.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "as ${item.character}",
                style = ArflixTypography.caption.copy(
                    fontSize = 11.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                ),
                color = TextSecondary.copy(alpha = 0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}
