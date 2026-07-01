package com.arflix.tv.ui.screens.plugin

import com.arflix.tv.R

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.arflix.tv.core.plugin.PluginManager
import com.arflix.tv.domain.model.MetaRepoEntry



import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PluginViewModel @Inject constructor(
    private val pluginManager: PluginManager,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(PluginUiState())
    val uiState: StateFlow<PluginUiState> = _uiState.asStateFlow()

    val isReadOnly: Boolean
        get() {
            return false
        }

    private var repoServer: Any? = null
    private var logoBytes: ByteArray? = null

    init {
        loadLogoBytes()
        observePluginData()
    }

    private fun loadLogoBytes() {
        try {
            val inputStream = context.resources.openRawResource(0)
            logoBytes = inputStream.use { it.readBytes() }
        } catch (_: Exception) { }
    }

    private fun observePluginData() {
        viewModelScope.launch {
            combine(
                pluginManager.pluginsEnabled,
                pluginManager.groupStreamsByRepository,
                pluginManager.repositories,
                pluginManager.scrapers
            ) { enabled, groupStreamsByRepository, repos, scrapers ->
                PluginUiState(
                    pluginsEnabled = enabled,
                    groupStreamsByRepository = groupStreamsByRepository,
                    repositories = repos,
                    scrapers = scrapers
                )
            }.collect { nextState ->
                val visibleScrapers = if (isReadOnly) {
                    nextState.scrapers.filter { it.enabled }
                } else {
                    nextState.scrapers
                }
                _uiState.update {
                    it.copy(
                        pluginsEnabled = nextState.pluginsEnabled,
                        groupStreamsByRepository = nextState.groupStreamsByRepository,
                        repositories = nextState.repositories,
                        scrapers = visibleScrapers
                    )
                }
            }
        }
    }

    fun onEvent(event: PluginUiEvent) {
        when (event) {
            is PluginUiEvent.AddRepository -> addRepository(event.url)
            is PluginUiEvent.RemoveRepository -> removeRepository(event.repoId)
            is PluginUiEvent.RefreshRepository -> refreshRepository(event.repoId)
            is PluginUiEvent.ToggleScraper -> toggleScraper(event.scraperId, event.enabled)
            is PluginUiEvent.ToggleAllScrapersForRepo -> toggleAllScrapersForRepo(event.repoId, event.enabled)
            is PluginUiEvent.TestScraper -> testScraper(event.scraperId)
            is PluginUiEvent.SetPluginsEnabled -> setPluginsEnabled(event.enabled)
            is PluginUiEvent.SetGroupStreamsByRepository -> setGroupStreamsByRepository(event.enabled)
            PluginUiEvent.ClearTestResults -> _uiState.update { it.copy(testResults = null, testDiagnostics = null, testScraperId = null) }
            PluginUiEvent.ClearError -> _uiState.update { it.copy(errorMessage = null) }
            PluginUiEvent.ClearSuccess -> _uiState.update { it.copy(successMessage = null) }
            PluginUiEvent.StartQrMode -> startQrMode()
            PluginUiEvent.StopQrMode -> stopQrMode()
            PluginUiEvent.ConfirmPendingRepoChange -> confirmPendingRepoChange()
            PluginUiEvent.RejectPendingRepoChange -> rejectPendingRepoChange()
            PluginUiEvent.ConfirmPendingScraperEnable -> confirmPendingScraperEnable()
            PluginUiEvent.DismissPendingScraperEnable -> dismissPendingScraperEnable()
            is PluginUiEvent.BrowseMetaRepo -> browseMetaRepo(event.url)
            is PluginUiEvent.InstallMetaRepoEntry -> installMetaRepoEntry(event.entry)
            PluginUiEvent.DismissMetaRepoBrowser -> _uiState.update { it.copy(metaRepoBrowseResult = null) }
        }
    }

    private fun addRepository(url: String) {
        if (url.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Error") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isAddingRepo = true, errorMessage = null) }

            val result = pluginManager.addRepository(url)

            result.fold(
                onSuccess = { repo ->
                    _uiState.update {
                        it.copy(
                            isAddingRepo = false,
                            successMessage = context.getString(
                                R.string.plugin_repo_added_with_providers,
                                repo.scraperCount
                            )
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isAddingRepo = false,
                            errorMessage = context.getString(R.string.plugin_error_add_repo, e.message ?: "")
                        )
                    }
                }
            )
        }
    }

    private fun removeRepository(repoId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            pluginManager.removeRepository(repoId)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    successMessage = "Error"
                )
            }
        }
    }

    private fun refreshRepository(repoId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = pluginManager.refreshRepository(repoId)

            result.fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            successMessage = "Error"
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = context.getString(R.string.plugin_error_refresh, e.message ?: "")
                        )
                    }
                }
            )
        }
    }

    private fun toggleScraper(scraperId: String, enabled: Boolean) {
        val scraper = _uiState.value.scrapers.firstOrNull { it.id == scraperId }
        if (enabled && scraper != null) {
            _uiState.update {
                it.copy(
                    pendingScraperEnable = PendingScraperEnableInfo(
                        scraperId = scraper.id,
                        scraperName = scraper.name
                    )
                )
            }
            return
        }

        viewModelScope.launch {
            pluginManager.toggleScraper(scraperId, enabled)
        }
    }

    private fun toggleAllScrapersForRepo(repoId: String, enabled: Boolean) {
        viewModelScope.launch {
            pluginManager.toggleAllScrapersForRepo(repoId, enabled)
        }
    }

    private fun confirmPendingScraperEnable() {
        val pending = _uiState.value.pendingScraperEnable ?: return
        _uiState.update { it.copy(pendingScraperEnable = null) }
        viewModelScope.launch {
            pluginManager.toggleScraper(pending.scraperId, true)
        }
    }

    private fun dismissPendingScraperEnable() {
        _uiState.update { it.copy(pendingScraperEnable = null) }
    }

    private fun setPluginsEnabled(enabled: Boolean) {
        if (isReadOnly) return
        viewModelScope.launch {
            pluginManager.setPluginsEnabled(enabled)
        }
    }

    private fun setGroupStreamsByRepository(enabled: Boolean) {
        if (isReadOnly) return
        viewModelScope.launch {
            pluginManager.setGroupStreamsByRepository(enabled)
        }
    }

    private fun testScraper(scraperId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isTesting = true, testScraperId = scraperId, testResults = null, testDiagnostics = null) }

            val result = pluginManager.testScraper(scraperId)

            result.fold(
                onSuccess = { (results, diagnostics) ->
                    _uiState.update {
                        it.copy(
                            isTesting = false,
                            testResults = results,
                            testDiagnostics = diagnostics,
                            successMessage = if (results.isEmpty()) {
                                "Error"
                            } else {
                                context.getString(R.string.plugin_test_found_streams, results.size)
                            }
                        )
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(
                            isTesting = false,
                            testResults = emptyList(),
                            testDiagnostics = null,
                            errorMessage = context.getString(
                                R.string.plugin_error_test,
                                e.message ?: "Error"
                            )
                        )
                    }
                }
            )
        }
    }

    private fun normalizeUrlForComparison(url: String): String {
        return url.trim().trimEnd('/').lowercase()
    }

    private fun browseMetaRepo(url: String) {
        if (url.isBlank()) return
        viewModelScope.launch {
            _uiState.update { it.copy(isAddingRepo = true, errorMessage = null) }
            val entries = pluginManager.browseMetaRepo(url)
            if (entries == null) {
                addRepository(url)
            } else {
                val alreadyInstalledUrls = _uiState.value.repositories.map { it.url }.toSet()
                _uiState.update {
                    it.copy(
                        isAddingRepo = false,
                        metaRepoBrowseResult = MetaRepoBrowseResult(
                            metaRepoUrl = url,
                            metaRepoName = url.substringAfter("://").substringBefore("/"),
                            entries = entries,
                            installed = alreadyInstalledUrls.intersect(entries.map { e -> e.pluginsUrl }.toSet())
                        )
                    )
                }
            }
        }
    }

    private fun installMetaRepoEntry(entry: MetaRepoEntry) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    metaRepoBrowseResult = state.metaRepoBrowseResult?.copy(
                        installing = state.metaRepoBrowseResult.installing + entry.pluginsUrl
                    )
                )
            }
            val result = pluginManager.addSubRepository(entry)
            _uiState.update { state ->
                val browse = state.metaRepoBrowseResult ?: return@update state
                state.copy(
                    metaRepoBrowseResult = browse.copy(
                        installing = browse.installing - entry.pluginsUrl,
                        installed = if (result.isSuccess) browse.installed + entry.pluginsUrl
                                    else browse.installed
                    ),
                    errorMessage = result.exceptionOrNull()?.message?.let {
                        "Failed to install ${entry.name}: $it"
                    }
                )
            }
        }
    }

    private fun startQrMode() {}
    fun stopQrMode() {}
    private fun confirmPendingRepoChange() {}
    private fun rejectPendingRepoChange() {}
    override fun onCleared() {
        super.onCleared()
    }
}
