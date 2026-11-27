package com.arflix.tv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arflix.tv.R
import com.arflix.tv.data.model.Profile
import kotlinx.coroutines.launch

/** Destinations that left the phone tab bar and now live behind the profile avatar. */
internal enum class MobileAccountAction { SWITCH_PROFILE, NEWS, SETTINGS }

/** News stays out of the menu when the profile hid it or is a kids profile, like its old tab. */
internal fun mobileAccountActions(hiddenSections: Collection<String>, isKids: Boolean): List<MobileAccountAction> =
    MobileAccountAction.entries.filter {
        it != MobileAccountAction.NEWS ||
            com.arflix.tv.util.isNavigationSectionVisible("news", hiddenSections, isKids)
    }

/**
 * Round profile avatar for phone headers. A tap opens a sheet with Switch profile, News and
 * Settings, the places that used to take three of the seven tabs.
 */
@Composable
fun MobileAccountButton(
    profile: Profile?,
    onSwitchProfile: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
) {
    var open by rememberSaveable { mutableStateOf(false) }
    val label = stringResource(R.string.settings)
    val press = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
    Box(
        modifier
            .size(48.dp)
            .mobilePressScale(press, 0.9f)
            .clip(CircleShape)
            .clickable(
                interactionSource = press,
                indication = androidx.compose.foundation.LocalIndication.current,
                role = Role.Button,
                onClickLabel = label,
            ) { open = true }
            .semantics { contentDescription = label }
            .testTag("mobile_account_button"),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier.size(size).clip(CircleShape)
                .background(Color.White.copy(alpha = 0.10f))
                .border(1.dp, Color.White.copy(alpha = 0.22f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            if (profile != null) {
                ProfileAvatarVisual(profile = profile, letterFontSize = 15.sp, iconPadding = 5.dp)
            } else {
                Icon(Icons.Filled.Settings, null, tint = Color.White, modifier = Modifier.size(22.dp))
            }
        }
    }
    if (open) {
        MobileAccountSheet(
            profile = profile,
            onDismiss = { open = false },
            onSwitchProfile = onSwitchProfile,
            onOpenSettings = onOpenSettings,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MobileAccountSheet(
    profile: Profile?,
    onDismiss: () -> Unit,
    onSwitchProfile: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    fun closeThen(action: () -> Unit) {
        scope.launch {
            sheetState.hide()
            onDismiss()
            action()
        }
    }
    val actions = remember(NavigationVisibility.hiddenSections, KidsNavigation.hideLiveTv) {
        mobileAccountActions(NavigationVisibility.hiddenSections, KidsNavigation.hideLiveTv)
    }
    // The sheet is its own window, whose LocalContext is the Activity again: its resources keep
    // the language the app started in. Hand it the app-language context the screen uses.
    val appContext = androidx.compose.ui.platform.LocalContext.current
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xFF141416),
        scrimColor = Color.Black.copy(alpha = 0.6f),
        contentColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
        androidx.compose.runtime.CompositionLocalProvider(androidx.compose.ui.platform.LocalContext provides appContext) {
        Column(Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 20.dp)) {
            if (profile != null) {
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(Modifier.size(52.dp).clip(RoundedCornerShape(14.dp))) {
                        ProfileAvatarVisual(profile = profile, letterFontSize = 20.sp, iconPadding = 6.dp)
                    }
                    Spacer(Modifier.width(14.dp))
                    Text(
                        profile.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(Modifier.size(16.dp))
            }
            Column(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.06f)),
            ) {
                actions.forEachIndexed { index, action ->
                    if (index > 0) {
                        Box(Modifier.padding(start = 56.dp).fillMaxWidth().height(1.dp)
                            .background(Color.White.copy(alpha = 0.08f)))
                    }
                    when (action) {
                        MobileAccountAction.SWITCH_PROFILE -> AccountRow(
                            Icons.Filled.People, stringResource(R.string.switch_profile),
                        ) { closeThen(onSwitchProfile) }
                        MobileAccountAction.NEWS -> AccountRow(
                            Icons.Filled.Newspaper, stringResource(R.string.nav_news),
                        ) { closeThen { NewsNavigation.open() } }
                        MobileAccountAction.SETTINGS -> AccountRow(
                            Icons.Filled.Settings, stringResource(R.string.settings),
                        ) { closeThen(onOpenSettings) }
                    }
                }
            }
        }
        }
    }
}

@Composable
private fun AccountRow(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().heightIn(min = 56.dp)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(icon, null, tint = Color.White.copy(alpha = 0.85f), modifier = Modifier.size(24.dp))
        Text(label, fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight, null,
            tint = Color.White.copy(alpha = 0.4f), modifier = Modifier.size(22.dp),
        )
    }
}
