package com.arflix.tv.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.data.repository.PersonalListsRepository
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.BackgroundElevated
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.LocalDeviceType

/**
 * "Add to list": the user's own lists, each ticked when it already holds the title.
 * Selecting a list adds or removes the title and keeps the picker open, so several lists
 * can be changed in one go. Same look as [MediaContextMenu], which opens it.
 */
@Composable
fun ListPickerMenu(
    title: String,
    isLoading: Boolean,
    choices: List<PersonalListsRepository.Choice>,
    busyKeys: Set<String>,
    onToggle: (PersonalListsRepository.Choice) -> Unit,
    onDismiss: () -> Unit
) {
    val isMobile = LocalDeviceType.current.isTouchDevice()
    var focusedIndex by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState()
    // The service name only helps when lists from more than one service are shown.
    val showProvider = choices.map { it.list.provider }.distinct().size > 1

    LaunchedEffect(Unit) {
        if (!isMobile) runCatching { focusRequester.requestFocus() }
    }
    LaunchedEffect(focusedIndex, choices.size) {
        if (choices.isNotEmpty()) listState.animateScrollToItem(focusedIndex.coerceIn(0, choices.lastIndex))
    }
    BackHandler { onDismiss() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(60f)
            .background(Color.Black.copy(alpha = 0.5f))
            .then(
                if (isMobile) {
                    Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDismiss() }
                } else {
                    Modifier
                        .focusRequester(focusRequester)
                        .focusable()
                        .onPreviewKeyEvent { event ->
                            if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                            when (event.key) {
                                Key.DirectionUp -> {
                                    if (focusedIndex > 0) focusedIndex--
                                    true
                                }
                                Key.DirectionDown -> {
                                    if (focusedIndex < choices.lastIndex) focusedIndex++
                                    true
                                }
                                Key.Enter, Key.DirectionCenter -> {
                                    choices.getOrNull(focusedIndex)?.let(onToggle)
                                    true
                                }
                                Key.Back, Key.Escape -> {
                                    onDismiss()
                                    true
                                }
                                else -> false
                            }
                        }
                }
            ),
        contentAlignment = if (isMobile) Alignment.BottomCenter else Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .then(
                    if (isMobile) {
                        Modifier
                            .fillMaxWidth()
                            .background(BackgroundElevated, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { /* keep taps on the sheet from closing it */ }
                            .padding(top = 16.dp, bottom = 24.dp + LocalBottomBarHeight.current)
                            .padding(horizontal = 16.dp)
                    } else {
                        Modifier
                            .padding(top = 110.dp)
                            .width(420.dp)
                            .background(BackgroundElevated, RoundedCornerShape(8.dp))
                            .padding(20.dp)
                    }
                )
        ) {
            Text(
                text = stringResource(R.string.list_add_to),
                style = ArflixTypography.sectionTitle,
                color = TextPrimary
            )
            Text(
                text = title,
                style = ArflixTypography.body,
                color = TextSecondary,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(14.dp))

            when {
                isLoading -> Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = TextSecondary,
                        strokeWidth = 2.dp
                    )
                    Text(
                        text = stringResource(R.string.list_picker_loading),
                        style = ArflixTypography.body,
                        color = TextSecondary
                    )
                }
                choices.isEmpty() -> Text(
                    text = stringResource(R.string.list_picker_empty),
                    style = ArflixTypography.body,
                    color = TextSecondary,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                else -> LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.heightIn(max = 420.dp)
                ) {
                    itemsIndexed(choices, key = { _, choice -> choice.list.key }, contentType = { _, _ -> "ListPickerMenu.choices" }) { index, choice ->
                        ListChoiceRow(
                            choice = choice,
                            showProvider = showProvider,
                            isBusy = choice.list.key in busyKeys,
                            isFocused = !isMobile && index == focusedIndex,
                            onClick = {
                                focusedIndex = index
                                onToggle(choice)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ListChoiceRow(
    choice: PersonalListsRepository.Choice,
    showProvider: Boolean,
    isBusy: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (isFocused) Color.Black else TextPrimary
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isFocused) Color.White else Color.Transparent, RoundedCornerShape(8.dp))
            .border(
                width = if (isFocused) 0.dp else 1.dp,
                color = if (isFocused) Color.Transparent else Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .heightIn(min = 48.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(modifier = Modifier.size(22.dp), contentAlignment = Alignment.Center) {
            if (isBusy) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    color = contentColor,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    imageVector = if (choice.contains) Icons.Default.Check else Icons.Default.Add,
                    contentDescription = null,
                    tint = if (isFocused) Color.Black else if (choice.contains) TextPrimary else TextSecondary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = choice.list.title,
                style = ArflixTypography.body,
                color = contentColor,
                maxLines = 1
            )
            if (showProvider) {
                Text(
                    text = choice.list.provider.label,
                    style = ArflixTypography.caption,
                    color = if (isFocused) Color.Black.copy(alpha = 0.6f) else TextSecondary
                )
            }
        }
    }
}
