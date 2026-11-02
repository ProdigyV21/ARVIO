package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.ui.screens.home.NoticeYellow
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary

@Composable
internal fun SettingsSidebarHeader(availableUpdateTag: String?, awaitingCompilation: Boolean) {
    Text(
        text = stringResource(R.string.settings),
        style = ArflixTypography.cardTitle.copy(fontSize = 19.sp),
        color = TextPrimary,
        modifier = Modifier.padding(start = 8.dp)
            .padding(bottom = if (availableUpdateTag != null || awaitingCompilation) 4.dp else 14.dp)
    )
    if (availableUpdateTag != null) {
        Text(
            text = stringResource(R.string.settings_sidebar_update_available, availableUpdateTag),
            style = ArflixTypography.caption,
            color = Color(0xFF34D399),
            modifier = Modifier.padding(start = 8.dp, end = 4.dp, bottom = 12.dp)
        )
    }
    if (awaitingCompilation) {
        Text(
            text = stringResource(R.string.settings_optimizing_notice),
            style = ArflixTypography.caption,
            color = NoticeYellow,
            modifier = Modifier.padding(start = 8.dp, end = 4.dp, bottom = 12.dp)
        )
    }
}
