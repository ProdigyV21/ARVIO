package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.WatchHistoryRecord;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0002¨\u0006\u0004"}, d2 = {"toRecord", "Lcom/arflix/tv/data/api/WatchHistoryRecord;", "Lcom/arflix/tv/data/repository/WatchHistoryEntry;", "toEntry", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class WatchHistoryRepositoryKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final WatchHistoryEntry toEntry(WatchHistoryRecord watchHistoryRecord) {
        String id = watchHistoryRecord.getId();
        String userId = watchHistoryRecord.getUserId();
        String profileId = watchHistoryRecord.getProfileId();
        String mediaType = watchHistoryRecord.getMediaType();
        Integer showTmdbId = watchHistoryRecord.getShowTmdbId();
        return new WatchHistoryEntry(id, userId, profileId, mediaType, showTmdbId != null ? showTmdbId.intValue() : 0, watchHistoryRecord.getShowTraktId(), watchHistoryRecord.getSeason(), watchHistoryRecord.getEpisode(), watchHistoryRecord.getTraktEpisodeId(), watchHistoryRecord.getTmdbEpisodeId(), watchHistoryRecord.getTitle(), watchHistoryRecord.getEpisodeTitle(), watchHistoryRecord.getProgress(), watchHistoryRecord.getDurationSeconds(), watchHistoryRecord.getPositionSeconds(), watchHistoryRecord.getPausedAt(), watchHistoryRecord.getUpdatedAt(), watchHistoryRecord.getSource(), watchHistoryRecord.getBackdropPath(), watchHistoryRecord.getPosterPath(), watchHistoryRecord.getStreamKey(), watchHistoryRecord.getStreamAddonId(), watchHistoryRecord.getStreamTitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WatchHistoryRecord toRecord(WatchHistoryEntry watchHistoryEntry) {
        return new WatchHistoryRecord(null, watchHistoryEntry.getUser_id(), watchHistoryEntry.getProfile_id(), watchHistoryEntry.getMedia_type(), Integer.valueOf(watchHistoryEntry.getShow_tmdb_id()), watchHistoryEntry.getShow_trakt_id(), watchHistoryEntry.getSeason(), watchHistoryEntry.getEpisode(), watchHistoryEntry.getTrakt_episode_id(), watchHistoryEntry.getTmdb_episode_id(), watchHistoryEntry.getProgress(), watchHistoryEntry.getPosition_seconds(), watchHistoryEntry.getDuration_seconds(), watchHistoryEntry.getPaused_at(), watchHistoryEntry.getUpdated_at(), watchHistoryEntry.getSource(), watchHistoryEntry.getTitle(), watchHistoryEntry.getEpisode_title(), watchHistoryEntry.getBackdrop_path(), watchHistoryEntry.getPoster_path(), watchHistoryEntry.getStream_key(), watchHistoryEntry.getStream_addon_id(), watchHistoryEntry.getStream_title(), null, 8388609, null);
    }
}
