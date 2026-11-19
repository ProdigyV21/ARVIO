package com.arflix.tv.ui.screens.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.arflix.tv.R

@Composable
internal fun MobileConnectionForm(title: String, supportingText: String?, fields: List<InputField>,
    toggles: List<ToggleField>, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    val clipboard = LocalClipboardManager.current
    var help by remember { mutableStateOf(false) }
    MaterialTheme(colorScheme = darkColorScheme(primary = Color.White, onPrimary = Color.Black,
        surface = Color.Black, onSurface = Color.White, onSurfaceVariant = Color.LightGray,
        outline = Color.White.copy(alpha = 0.4f))) {
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false,
        decorFitsSystemWindows = false)) {
        Column(Modifier.fillMaxSize().background(Color.Black).systemBarsPadding().imePadding()
            .padding(16.dp).testTag("mobile-connection-form")) {
            Text(title, style = MaterialTheme.typography.headlineSmall, color = Color.White,
                modifier = Modifier.padding(vertical = 12.dp))
            Column(Modifier.weight(1f).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                if (!supportingText.isNullOrBlank()) {
                    Text(supportingText, color = Color.LightGray,
                        maxLines = if (help) Int.MAX_VALUE else 3,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
                    TextButton(onClick = { help = !help }) { Text(stringResource(R.string.mobile_connection_help)) }
                }
                fields.forEachIndexed { index, field ->
                    var visible by remember(index) { mutableStateOf(false) }
                    val secret = field.isSecret || field.label.contains("password", ignoreCase = true)
                    OutlinedTextField(value = field.value, onValueChange = field.onValueChange,
                        label = { Text(field.label) }, placeholder = { Text(field.placeholder) },
                        supportingText = field.helper.takeIf { it.isNotBlank() }?.let { { Text(it) } },
                        modifier = Modifier.fillMaxWidth().testTag("connection-field-$index"),
                        singleLine = field.singleLine, minLines = if (field.singleLine) 1 else 3,
                        visualTransformation = if (secret && !visible) PasswordVisualTransformation() else VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = if (secret) KeyboardType.Password else if (field.label.contains("url", true)) KeyboardType.Uri else KeyboardType.Text,
                            imeAction = if (index == fields.lastIndex) ImeAction.Done else ImeAction.Next),
                        trailingIcon = { Row {
                            if (secret) IconButton(onClick = { visible = !visible }) {
                                Icon(if (visible) Icons.Default.VisibilityOff else Icons.Default.Visibility, field.label)
                            }
                            IconButton(onClick = { clipboard.getText()?.text?.let(field.onValueChange) }) {
                                Icon(Icons.Default.ContentPaste, stringResource(R.string.settings_cd_paste))
                            }
                        } })
                }
                toggles.forEach { field ->
                    com.arflix.tv.ui.components.MobileSettingsRow(title = field.label, value = "",
                        toggleChecked = field.value, onClick = { field.onValueChange(!field.value) })
                }
                Spacer(Modifier.height(12.dp))
            }
            Row(Modifier.fillMaxWidth().padding(top = 12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
                Button(onClick = onConfirm, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)) {
                    Text(stringResource(R.string.confirm))
                }
            }
        }
    }
    }
}
