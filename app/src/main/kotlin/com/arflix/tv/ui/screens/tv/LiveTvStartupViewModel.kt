package com.arflix.tv.ui.screens.tv

import androidx.lifecycle.ViewModel
import com.arflix.tv.data.repository.IptvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** Answers "should the app open in Live TV, and on which channel?" (Settings > TV > Start in last channel). */
@HiltViewModel
class LiveTvStartupViewModel @Inject constructor(
    private val iptvRepository: IptvRepository,
) : ViewModel() {
    /** Whether this app session already made its one startup decision (survives recreation). */
    var decided: Boolean = false

    suspend fun startupChannelId(): String? = runCatching { iptvRepository.startupChannelId() }.getOrNull()
}
