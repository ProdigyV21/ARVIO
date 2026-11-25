package com.arflix.tv.ui.screens.news

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arflix.tv.data.news.NewsRepository
import com.arflix.tv.data.news.NewsSourcePreferences
import com.arflix.tv.data.news.NewsSourceSelection
import com.arflix.tv.data.news.NewsTopic
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.util.settingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** The active profile's news setup, following profile switches and edits made anywhere. */
internal fun newsSelectionFlow(context: Context, profileManager: ProfileManager): Flow<NewsSourceSelection> =
    combine(profileManager.activeProfileId, context.settingsDataStore.data) { profileId, prefs ->
        NewsSourcePreferences.decode(prefs[NewsSourcePreferences.key(profileId)])
    }.distinctUntilChanged()

data class NewsSettingsState(
    val isProbingFeed: Boolean = false,
    /** The last custom feed address could not be read as RSS/Atom. */
    val customFeedError: Boolean = false,
)

/** Settings → News: which providers and topics the News page shows. Fetches nothing but a new custom feed. */
@HiltViewModel
class NewsSettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: NewsRepository,
    private val profileManager: ProfileManager,
) : ViewModel() {
    val selection: StateFlow<NewsSourceSelection> = newsSelectionFlow(context, profileManager)
        .stateIn(viewModelScope, SharingStarted.Eagerly, NewsSourceSelection(emptyList()))

    private val _state = MutableStateFlow(NewsSettingsState())
    val state: StateFlow<NewsSettingsState> = _state.asStateFlow()

    fun toggleProvider(providerId: String) = saveSelection { NewsSourcePreferences.toggle(it, providerId) }

    /** `null` = all topics. */
    fun toggleTopic(topic: NewsTopic?) = saveSelection { NewsSourcePreferences.toggleTopic(it, topic) }

    fun removeCustomProvider(providerId: String) = saveSelection { NewsSourcePreferences.removeCustom(it, providerId) }

    fun dismissCustomFeedError() = _state.update { it.copy(customFeedError = false) }

    /** Saves the feed only once it has been read successfully; the feed's own title names it. */
    fun addCustomProvider(url: String) {
        val current = selection.value
        val next = NewsSourcePreferences.addCustom(current, "", url)
        if (next == null) {
            _state.update { it.copy(customFeedError = true) }
            return
        }
        val added = next.customProviders.lastOrNull()?.takeIf { p -> current.customProviders.none { it.id == p.id } }
        if (added == null) {
            saveSelection { next }
            return
        }
        _state.update { it.copy(isProbingFeed = true, customFeedError = false) }
        viewModelScope.launch {
            val title = runCatching { repository.probeFeed(added) }
            _state.update { it.copy(isProbingFeed = false, customFeedError = title.isFailure) }
            if (title.isFailure) return@launch
            val named = title.getOrNull()?.let { added.copy(name = it.take(40)) } ?: added
            saveSelection { selection ->
                NewsSourcePreferences.addCustom(selection, named.name, named.url) ?: selection
            }
        }
    }

    private fun saveSelection(transform: (NewsSourceSelection) -> NewsSourceSelection) {
        viewModelScope.launch {
            val profileId = profileManager.getProfileId()
            context.settingsDataStore.edit { prefs ->
                val key = NewsSourcePreferences.key(profileId)
                val current = NewsSourcePreferences.decode(prefs[key])
                prefs[key] = NewsSourcePreferences.encode(transform(current))
            }
        }
    }
}
