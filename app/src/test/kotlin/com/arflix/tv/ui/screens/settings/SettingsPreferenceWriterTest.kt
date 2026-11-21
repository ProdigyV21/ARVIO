package com.arflix.tv.ui.screens.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsPreferenceWriterTest {
    private class SlowStore : DataStore<Preferences> {
        override val data = MutableStateFlow(emptyPreferences())
        private val mutex = Mutex()
        val gates = ArrayDeque<CompletableDeferred<Boolean>>()
        override suspend fun updateData(transform: suspend (Preferences) -> Preferences): Preferences = mutex.withLock {
            val gate = CompletableDeferred<Boolean>().also { gates.addLast(it) }
            if (!gate.await()) throw IOException("Disk unavailable")
            transform(data.value).also { data.value = it }
        }
        fun finish(success: Boolean = true) { gates.removeFirst().complete(success) }
    }
    private class Fixture(scope: CoroutineScope) {
        val store = SlowStore()
        val state = MutableStateFlow(SettingsUiState(subtitleSize = "Small"))
        var profile = "profile-a"
        var synced = 0
        var failed = 0
        var committed = 0
        val key = stringPreferencesKey("profile_a_subtitle_size")
        val writer = SettingsPreferenceWriter(store, state, scope, { profile }, { synced++ }, { failed++ }, onCommitted = { committed++ })
        fun size(value: String, sync: Boolean = true, afterSave: suspend () -> Unit = { synced++ }) =
            writer.save(key, value, { it.subtitleSize }, { s, v -> s.copy(subtitleSize = v) }, sync = sync, afterSave = afterSave)
    }

    @Test fun feedbackDoesNotWaitForDiskOrCloud() = runTest {
        val f = Fixture(this)
        f.size("Medium")
        assertEquals("Medium", f.state.value.subtitleSize)
        assertEquals(0, f.synced)
        runCurrent()
        assertNull(f.store.data.value[f.key])
        f.store.finish()
        advanceUntilIdle()
        assertEquals("Medium", f.store.data.value[f.key])
        assertEquals(1, f.synced)
    }

    @Test fun rapidPressesKeepTheNewestValueWhileOlderWritesFinish() = runTest {
        val f = Fixture(this)
        f.size("Medium")
        f.size("Large")
        runCurrent()
        f.store.finish()
        runCurrent()
        assertEquals("Large", f.state.value.subtitleSize)
        assertEquals(0, f.synced)
        f.store.finish()
        advanceUntilIdle()
        assertEquals("Large", f.store.data.value[f.key])
        assertEquals(1, f.synced)
    }

    @Test fun olderFailureCannotRollBackANewerPress() = runTest {
        val f = Fixture(this)
        f.size("Medium")
        f.size("Large")
        runCurrent()
        f.store.finish(false)
        runCurrent()
        assertEquals("Large", f.state.value.subtitleSize)
        assertEquals(0, f.failed)
        f.store.finish()
        advanceUntilIdle()
        assertEquals("Large", f.state.value.subtitleSize)
    }

    @Test fun allFailedWritesRestoreTheOriginalNotAnotherUncommittedValue() = runTest {
        val f = Fixture(this)
        f.size("Medium")
        f.size("Large")
        runCurrent()
        f.store.finish(false)
        runCurrent()
        f.store.finish(false)
        advanceUntilIdle()
        assertEquals("Small", f.state.value.subtitleSize)
        assertEquals(1, f.failed)
        assertEquals(0, f.synced)
    }

    @Test fun latestFailureRestoresTheLastSuccessfulWrite() = runTest {
        val f = Fixture(this)
        f.size("Medium")
        f.size("Large")
        runCurrent()
        f.store.finish()
        runCurrent()
        f.store.finish(false)
        advanceUntilIdle()
        assertEquals("Medium", f.state.value.subtitleSize)
        assertEquals("Medium", f.store.data.value[f.key])
        assertEquals(1, f.synced)
    }

    @Test fun oldProfileCompletionCannotChangeTheNewProfile() = runTest {
        val f = Fixture(this)
        f.size("Medium")
        runCurrent()
        f.profile = "profile-b"
        f.state.value = f.state.value.copy(subtitleSize = "Extra Large")
        f.store.finish(false)
        advanceUntilIdle()
        assertEquals("Extra Large", f.state.value.subtitleSize)
        assertEquals(0, f.failed)
        assertEquals(0, f.synced)
    }

    @Test fun cloudFailureDoesNotUndoASuccessfulLocalWrite() = runTest {
        val f = Fixture(this)
        f.size("Medium", afterSave = { throw IOException("Cloud unavailable") })
        runCurrent()
        f.store.finish()
        advanceUntilIdle()
        assertEquals("Medium", f.state.value.subtitleSize)
        assertEquals("Medium", f.store.data.value[f.key])
    }

    @Test fun profileSwitchStillSyncsPersistedEditsWithoutCallingTheNewProfilesCallback() = runTest {
        val f = Fixture(this)
        var profileCallback = 0
        f.size("Medium", afterSave = { profileCallback++ })
        runCurrent()
        f.profile = "profile-b"
        f.state.value = f.state.value.copy(subtitleSize = "Extra Large")
        f.store.finish()
        advanceUntilIdle()
        assertEquals("Extra Large", f.state.value.subtitleSize)
        assertEquals("Medium", f.store.data.value[f.key])
        assertEquals(1, f.synced)
        assertEquals(0, profileCallback)
    }

    @Test fun localOnlyPreferencesDoNotTriggerCloudWork() = runTest {
        val f = Fixture(this)
        f.size("Medium", sync = false)
        runCurrent()
        f.store.finish()
        advanceUntilIdle()
        assertEquals(0, f.synced)
        assertEquals(0, f.committed)
    }

    @Test fun leavingSettingsDoesNotLoseAnAcknowledgedEditOrItsCloudDirtyFlag() = runTest {
        val owner = CoroutineScope(coroutineContext + Job())
        val f = Fixture(owner)
        f.size("Medium")
        runCurrent()
        owner.cancel()
        f.store.finish()
        advanceUntilIdle()
        assertEquals("Medium", f.store.data.value[f.key])
        assertEquals(1, f.committed)
        assertEquals(0, f.synced)
    }

    @Test fun namedPresetAndItsNumericAliasCommitInTheSameEdit() = runTest {
        val f = Fixture(this)
        val numeric = androidx.datastore.preferences.core.intPreferencesKey("profile_a_subtitle_size_pct")
        f.writer.save(
            f.key, "Large", { it.subtitleSize }, { s, v -> s.copy(subtitleSize = v) },
            editRelated = { it[numeric] = 120 },
        )
        runCurrent()
        f.store.finish()
        advanceUntilIdle()
        // Both keys land together: the player reads the numeric one, the settings UI the named one.
        assertEquals("Large", f.store.data.value[f.key])
        assertEquals(120, f.store.data.value[numeric])
    }
}
