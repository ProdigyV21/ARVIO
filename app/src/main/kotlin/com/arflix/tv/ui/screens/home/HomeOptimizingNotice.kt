package com.arflix.tv.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.ui.components.isAwaitingProfileCompilation
import com.arflix.tv.ui.theme.ArflixTypography
import kotlinx.coroutines.delay

internal val NoticeYellow = Color(0xFFFACC15)

// Checked once per process, so returning to Home doesn't show it again.
private var optimizingNoticeChecked = false

/**
 * Small notice that Home may scroll slowly because the app isn't compiled yet.
 *
 * Android compiles Home ahead of time only once the baseline profile is applied (at install
 * via Play / a .dm, otherwise by background dexopt while idle); until then Home runs
 * interpreted and scrolls slowly. Debug builds have no embedded profile and never show it.
 */
@Composable
fun HomeOptimizingNotice(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (optimizingNoticeChecked) return@LaunchedEffect
        optimizingNoticeChecked = true
        if (isAwaitingProfileCompilation(context)) {
            visible = true
            delay(8_000)
            visible = false
        }
    }

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn() + slideInVertically { -it / 3 },
        exit = fadeOut() + slideOutVertically { -it / 3 }
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = 420.dp)
                .background(Color(0xE61A1E28), RoundedCornerShape(14.dp))
                .border(1.dp, NoticeYellow.copy(alpha = 0.6f), RoundedCornerShape(14.dp))
                .padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Speed,
                contentDescription = null,
                tint = NoticeYellow,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = stringResource(R.string.home_optimizing_notice),
                style = ArflixTypography.caption,
                color = Color.White
            )
        }
    }
}
