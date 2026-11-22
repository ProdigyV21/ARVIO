package com.arflix.tv.ui.screens.news

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.news.NewsArticle
import com.arflix.tv.data.news.NewsArticleBody
import com.arflix.tv.data.news.NewsMerge
import com.arflix.tv.data.news.NewsProvider
import com.arflix.tv.data.news.NewsRepository
import com.arflix.tv.data.news.NewsSourceSelection
import com.arflix.tv.data.repository.ProfileManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** `null` tab = every followed provider merged by time. */
data class NewsUiState(
    val selection: NewsSourceSelection = NewsSourceSelection(emptyList()),
    val activeProviderId: String? = null,
    val articles: List<NewsArticle> = emptyList(),
    val isLoading: Boolean = true,
    /** Providers that failed on the last load, by display name. */
    val failedProviders: List<String> = emptyList(),
    val openArticle: NewsArticle? = null,
    val openBody: NewsArticleBody? = null,
    val isBodyLoading: Boolean = false,
) {
    /** The tabs: followed providers that have something for the chosen topics. */
    val providers: List<NewsProvider> get() = selection.shownProviders
    fun provider(id: String): NewsProvider? = providers.firstOrNull { it.id == id }
        ?: selection.customProviders.firstOrNull { it.id == id }
        ?: com.arflix.tv.data.news.NewsProviders.presets.firstOrNull { it.id == id }
}

@HiltViewModel
class NewsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: NewsRepository,
    private val profileManager: ProfileManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private var loadJob: Job? = null
    private var bodyJob: Job? = null
    private val previews = MutableStateFlow<Map<String, NewsArticleBody>>(emptyMap())
    val previewBodies: StateFlow<Map<String, NewsArticleBody>> = previews.asStateFlow()

    init {
        viewModelScope.launch {
            newsSelectionFlow(context, profileManager).collect { selection ->
                val previous = _uiState.value
                val shownIds = selection.shownProviders.map { it.id }
                val active = previous.activeProviderId?.takeIf { it in shownIds }
                    ?: if (shownIds.size == 1) shownIds.first() else null
                _uiState.update { it.copy(selection = selection, activeProviderId = active) }
                if (previous.selection.selectedIds != selection.selectedIds || previous.selection.providers != selection.providers ||
                    previous.selection.topics != selection.topics ||
                    previous.articles.isEmpty()) load(force = false)
            }
        }
    }

    fun selectProvider(providerId: String?) {
        if (_uiState.value.activeProviderId == providerId) return
        _uiState.update { it.copy(activeProviderId = providerId, articles = emptyList()) }
        load(force = false)
    }

    fun refresh() = load(force = true)

    private fun load(force: Boolean) {
        loadJob?.cancel()
        val state = _uiState.value
        val topics = state.selection.topics
        val targets = state.activeProviderId?.let { id -> listOfNotNull(state.provider(id)) } ?: state.providers
        if (targets.isEmpty()) {
            _uiState.update { it.copy(articles = emptyList(), isLoading = false, failedProviders = emptyList()) }
            return
        }
        _uiState.update { it.copy(isLoading = true, failedProviders = emptyList()) }
        loadJob = viewModelScope.launch {
            val results = targets.map { provider ->
                async { provider to runCatching { repository.headlines(provider, topics, force) } }
            }.awaitAll()
            val merged = NewsMerge.interleave(results.map { it.second.getOrNull().orEmpty() })
            _uiState.update {
                it.copy(
                    articles = merged,
                    isLoading = false,
                    failedProviders = results.filter { r -> r.second.isFailure }.map { r -> r.first.name },
                )
            }
        }
    }

    fun openArticle(article: NewsArticle) {
        bodyJob?.cancel()
        val cached = repository.cachedBody(article)
        _uiState.update { it.copy(openArticle = article, openBody = cached, isBodyLoading = cached == null) }
        if (cached != null) return
        bodyJob = viewModelScope.launch {
            val body = runCatching { repository.articleBody(article) }.getOrNull()
            _uiState.update {
                if (it.openArticle?.link != article.link) it
                else it.copy(openBody = body ?: NewsArticleBody(listOf(article.summary).filter { s -> s.isNotBlank() }, article.imageUrl),
                    isBodyLoading = false)
            }
        }
    }

    fun closeArticle() {
        bodyJob?.cancel()
        _uiState.update { it.copy(openArticle = null, openBody = null, isBodyLoading = false) }
    }

    /** Fills the TV preview pane (image + opening lines) for headline-only feeds like CNN. */
    suspend fun loadPreview(article: NewsArticle) {
        if (article.imageUrl != null && article.summary.isNotBlank()) return
        if (previews.value.containsKey(article.link)) return
        val body = runCatching { repository.articleBody(article) }.getOrNull() ?: return
        previews.update { it + (article.link to body) }
    }
}
