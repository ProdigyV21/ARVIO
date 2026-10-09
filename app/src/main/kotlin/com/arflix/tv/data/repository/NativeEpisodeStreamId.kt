package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StremioMetaPreview

/** Addon video IDs are opaque, not necessarily showId:season:episode. */
internal fun nativeEpisodeStreamId(meta: StremioMetaPreview?, season: Int, episode: Int): String? =
    meta?.videos?.firstOrNull { it.season == season && it.episode == episode }
        ?.id?.takeIf { it.isNotBlank() }
