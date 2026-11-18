package com.arflix.tv.ui.screens.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** Immediate feedback, with ordered persistence and rollback to the last successful write. */
internal class SettingsPreferenceWriter(
    private val store: DataStore<Preferences>,
    private val state: MutableStateFlow<SettingsUiState>,
    private val scope: CoroutineScope,
    private val profileId: () -> String,
    private val onSaved: suspend () -> Unit,
    private val onFailure: (Exception) -> Unit,
    private val onSyncFailure: (Exception) -> Unit = onFailure,
    private val onCommitted: () -> Unit = {}
) {
    private class Pending(
        val profile: String,
        var rollback: (SettingsUiState) -> SettingsUiState,
        var latest: Long = 0,
        var count: Int = 0,
        var committed: Boolean = false
    )
    private val pending = mutableMapOf<String, Pending>()
    private var revision = 0L

    fun <T> save(
        key: Preferences.Key<T>, value: T,
        read: (SettingsUiState) -> T,
        write: (SettingsUiState, T) -> SettingsUiState,
        /** Numeric aliases must commit/roll back atomically with their named preset. */
        editRelated: (androidx.datastore.preferences.core.MutablePreferences) -> Unit = {},
        sync: Boolean = true,
        afterSave: suspend () -> Unit = onSaved
    ) {
        val profile = profileId()
        val previous = read(state.value)
        val entry = pending[key.name]?.takeIf { it.profile == profile }
            ?: Pending(profile, { write(it, previous) }).also { pending[key.name] = it }
        val version = ++revision
        entry.latest = version
        entry.count++
        state.update { write(it, value) }
        scope.launch(start = CoroutineStart.UNDISPATCHED) {
            try {
                try {
                    // Leaving Settings immediately must not discard an acknowledged edit.
                    withContext(NonCancellable) {
                        store.edit { it[key] = value; editRelated(it) }
                        entry.rollback = { write(it, value) }
                        entry.committed = true
                        if (sync) onCommitted()
                    }
                } catch (cancelled: CancellationException) { throw cancelled }
                catch (error: Exception) {
                    if (entry.latest == version && profileId() == profile) {
                        state.update(entry.rollback)
                        onFailure(error)
                        if (sync && entry.committed) syncSafely(onSaved)
                    }
                    return@launch
                }
                currentCoroutineContext().ensureActive()
                if (sync && entry.latest == version) {
                    // Persisted edits still need cloud dirtiness after a profile switch,
                    // but profile-specific callbacks must not target the new profile.
                    syncSafely(if (profileId() == profile) afterSave else onSaved)
                }
            } finally {
                if (--entry.count == 0 && pending[key.name] === entry) pending.remove(key.name)
            }
        }
    }

    private suspend fun syncSafely(action: suspend () -> Unit) {
        try { action() }
        catch (cancelled: CancellationException) { throw cancelled }
        catch (error: Exception) { onSyncFailure(error) }
    }
}
