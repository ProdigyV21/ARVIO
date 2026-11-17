package com.arflix.tv.ui.components

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.arflix.tv.R
import com.arflix.tv.server.PhoneInputServer
import com.arflix.tv.ui.theme.ArflixTypography
import com.arflix.tv.ui.theme.TextPrimary
import com.arflix.tv.ui.theme.TextSecondary
import com.arflix.tv.util.DeviceIpAddress
import com.arflix.tv.util.LocalDeviceType

/**
 * "Use your phone" for a TV form: a button that, when chosen, reveals a QR code opening the same
 * fields on the phone. What the phone sends arrives in [onValues] (main thread) to fill them;
 * nothing is saved until the user confirms on the TV. The phone page's server runs only while the
 * code is shown. Not shown on phones and tablets, which have a keyboard already.
 *
 * For forms with ordinary Compose focus. Forms that drive focus themselves place
 * [PhoneInputButton] (with `focused`) and [PhoneInputPanel] on their own.
 */
@Composable
fun PhoneInputOnDemand(
    title: String,
    fields: List<PhoneInputServer.Field>,
    onValues: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
    qrSize: Dp = 96.dp,
) {
    if (LocalDeviceType.current.isTouchDevice()) return
    var revealed by remember(title) { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxWidth()) {
        PhoneInputButton(revealed = revealed, onClick = { revealed = !revealed })
        if (revealed) {
            Spacer(modifier = Modifier.height(10.dp))
            PhoneInputPanel(title = title, fields = fields, onValues = onValues, qrSize = qrSize)
        }
    }
}

/**
 * The "Use your phone" / "Hide phone code" button. With [focused] null it takes focus itself;
 * a form that tracks focus on its own passes whether it is the focused item.
 */
@Composable
fun PhoneInputButton(
    revealed: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    focused: Boolean? = null,
) {
    var hasFocus by remember { mutableStateOf(false) }
    val isFocused = focused ?: hasFocus
    val shape = RoundedCornerShape(12.dp)
    Row(
        modifier = modifier
            .clip(shape)
            .background(if (isFocused) Color.White else Color.Black.copy(alpha = 0.82f), shape)
            .border(1.dp, if (isFocused) Color.White else Color.White.copy(alpha = 0.14f), shape)
            .then(if (focused == null) Modifier.onFocusChanged { hasFocus = it.isFocused } else Modifier)
            .clickable(onClick = onClick)
            .padding(vertical = 11.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.PhoneAndroid,
            contentDescription = null,
            tint = if (isFocused) Color.Black else Color.White,
            modifier = Modifier.size(18.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(if (revealed) R.string.phone_input_button_hide else R.string.phone_input_button),
            style = ArflixTypography.button,
            color = if (isFocused) Color.Black else Color.White,
            maxLines = 1,
        )
    }
}

/**
 * The QR code and address of the phone page for [fields]. The page's server runs while this is
 * shown; the fields' current values prefill the phone form once.
 */
@Composable
fun PhoneInputPanel(
    title: String,
    fields: List<PhoneInputServer.Field>,
    onValues: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
    qrSize: Dp = 96.dp,
) {
    if (LocalDeviceType.current.isTouchDevice()) return
    val context = LocalContext.current
    val latestOnValues by rememberUpdatedState(onValues)
    val texts = PhoneInputServer.Texts(
        hint = stringResource(R.string.phone_input_page_hint),
        send = stringResource(R.string.phone_input_page_send),
        sent = stringResource(R.string.phone_input_page_sent),
        notSent = stringResource(R.string.phone_input_page_not_sent),
        expired = stringResource(R.string.phone_input_page_expired),
    )
    var url by remember { mutableStateOf<String?>(null) }

    // One server while the code is shown; the fields' current values prefill the phone form once.
    DisposableEffect(title) {
        val main = Handler(Looper.getMainLooper())
        val server = PhoneInputServer.start(title, fields, texts) { values -> main.post { latestOnValues(values) } }
        val ip = DeviceIpAddress.get(context)
        url = if (server != null && ip != null) "http://$ip:${server.listeningPort}/?t=${server.token}" else null
        onDispose {
            runCatching { server?.stop() }
            url = null
        }
    }

    val address = url ?: return
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(qrSize)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(6.dp)
        ) {
            QrCodeImage(data = address, sizePx = 360, modifier = Modifier.size(qrSize))
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = stringResource(R.string.phone_input_qr_title), style = ArflixTypography.cardTitle, color = TextPrimary)
            Text(text = stringResource(R.string.phone_input_qr_hint), style = ArflixTypography.caption, color = TextSecondary)
            Text(
                text = address.substringBefore("/?"),
                style = ArflixTypography.caption,
                color = TextSecondary.copy(alpha = 0.6f)
            )
        }
    }
}
