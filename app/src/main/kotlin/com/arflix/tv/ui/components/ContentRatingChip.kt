package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.ui.theme.ArflixTypography

/**
 * Age certification in a small rounded chip — same shape and padding as the "In Cinema"
 * pill on the details screen, on a neutral translucent surface instead of a solid colour.
 * Deliberately not colour coded by age: the age is only unambiguous for a few numeric
 * rating systems, so a colour would have to be guessed for the letter based ones.
 */
@Composable
fun ContentRatingChip(
    text: String,
    fontWeight: FontWeight,
    textColor: Color,
    textShadow: Shadow,
    fontSize: TextUnit = 13.sp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.White.copy(alpha = 0.14f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = ArflixTypography.caption.copy(
                fontSize = fontSize,
                fontWeight = fontWeight,
                shadow = textShadow
            ),
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
