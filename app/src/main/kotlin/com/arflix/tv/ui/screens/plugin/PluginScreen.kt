package com.arflix.tv.ui.screens.plugin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable

import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.Text
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.ClickableSurfaceDefaults
import com.arflix.tv.R

@Composable
fun PluginScreen(
    viewModel: PluginViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onNavigateToSection: (() -> Unit)? = null
) {
    val uiState by viewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    val addButtonFocusRequester = remember { FocusRequester() }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val sectionNavKey = if (isRtl) Key.DirectionRight else Key.DirectionLeft

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100)
        try {
            addButtonFocusRequester.requestFocus()
        } catch (e: Exception) {}
    }

    Column(
        modifier = Modifier
            .padding(bottom = 80.dp)
            .fillMaxWidth()
            .onPreviewKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        sectionNavKey -> {
                            onNavigateToSection?.invoke()
                            return@onPreviewKeyEvent onNavigateToSection != null
                        }
                        Key.Back, Key.Escape -> {
                            onBackPressed()
                            return@onPreviewKeyEvent true
                        }
                        else -> {}
                    }
                }
                false
            }
    ) {
        Text(stringResource(R.string.plugin_screen_title), color = Color.White, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        uiState.errorMessage?.let { msg ->
            Text(msg, color = Color.Red)
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Full width Add Button to easily catch focus
        Surface(
            onClick = { showAddDialog = true },
            colors = ClickableSurfaceDefaults.colors(
                containerColor = Color(0xFF2B2B2B),
                focusedContainerColor = Color(0xFFE91E63)
            ),
            shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp)),
            modifier = Modifier.fillMaxWidth().focusRequester(addButtonFocusRequester)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.plugin_screen_add_repo), color = Color.White, style = MaterialTheme.typography.titleMedium)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.repositories.isNotEmpty()) {
            Text(stringResource(R.string.plugin_screen_installed_repos), color = Color.White, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            uiState.repositories.forEach { repo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("💠 ${repo.name}", color = Color.Cyan)
                    Spacer(modifier = Modifier.width(16.dp))
                    Surface(
                        onClick = { viewModel.onEvent(PluginUiEvent.RemoveRepository(repo.id)) },
                        colors = ClickableSurfaceDefaults.colors(
                            containerColor = Color.Transparent,
                            focusedContainerColor = Color.White.copy(alpha = 0.1f)
                        ),
                        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(4.dp))
                    ) {
                        Text("🗑️ ${stringResource(R.string.delete)}", color = Color.Red, modifier = Modifier.padding(4.dp))
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Text(stringResource(R.string.plugin_screen_installed_scrapers), color = Color.White, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        if (uiState.scrapers.isEmpty()) {
            Text(stringResource(R.string.plugin_screen_no_scrapers), color = Color.Gray)
        }

        if (uiState.scrapers.isNotEmpty()) {
            uiState.scrapers.forEach { scraper ->
                // Make the entire row focusable and clickable
                Surface(
                    onClick = { viewModel.onEvent(PluginUiEvent.ToggleScraper(scraper.id, !scraper.enabled)) },
                    colors = ClickableSurfaceDefaults.colors(
                        containerColor = Color.Transparent,
                        focusedContainerColor = Color.White.copy(alpha = 0.1f)
                    ),
                    shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp)),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(scraper.name, color = Color.White, modifier = Modifier.weight(1f))

                        // Custom TV-safe Switch visualization (doesn't trap focus itself)
                        Box(
                            modifier = Modifier
                                .width(44.dp)
                                .height(24.dp)
                                .background(
                                    color = if (scraper.enabled) Color(0xFF4CAF50) else Color.White.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(13.dp)
                                )
                                .padding(3.dp),
                            contentAlignment = if (scraper.enabled) Alignment.CenterEnd else Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddRepoDialog(
            onSave = { url ->
                viewModel.onEvent(PluginUiEvent.AddRepository(url))
                showAddDialog = false
                try { addButtonFocusRequester.requestFocus() } catch (e: Exception) {}
            },
            onDismiss = {
                showAddDialog = false
                try { addButtonFocusRequester.requestFocus() } catch (e: Exception) {}
            }
        )
    }
}

@Composable
fun AddRepoDialog(
    onSave: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    val inputFocusRequester = remember { FocusRequester() }
    val saveFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(200)
        try { inputFocusRequester.requestFocus() } catch (_: Exception) {}
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown &&
                        (event.key == Key.Back || event.key == Key.Escape)) {
                        onDismiss()
                        return@onPreviewKeyEvent true
                    }
                    false
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(520.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E1E1E))
                    .clickable { /* absorb clicks */ }
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Text(stringResource(R.string.plugin_screen_add_repo_dialog_title), style = MaterialTheme.typography.titleLarge, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.OutlinedTextField(
                        value = value,
                        onValueChange = { value = it },
                        singleLine = true,
                        label = { androidx.compose.material3.Text(stringResource(R.string.plugin_screen_repo_url)) },
                        modifier = Modifier.fillMaxWidth().focusRequester(inputFocusRequester),
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color(0xFFE91E63),
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color(0xFFE91E63),
                            unfocusedLabelColor = Color.Gray
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Surface(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            colors = ClickableSurfaceDefaults.colors(
                                containerColor = Color(0xFF2B2B2B),
                                focusedContainerColor = Color(0xFF3B3B3B)
                            ),
                            shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
                                modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }

                        Surface(
                            onClick = { onSave(value) },
                            modifier = Modifier.weight(1f).focusRequester(saveFocusRequester),
                            colors = ClickableSurfaceDefaults.colors(
                                containerColor = Color(0xFFE91E63),
                                focusedContainerColor = Color(0xFFFF4081)
                            ),
                            shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = stringResource(R.string.add),
                                modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
