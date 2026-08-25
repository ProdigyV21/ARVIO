package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/ui/screens/player/PlaybackEpisodeKey;", "", "mediaId", "", "seasonNumber", "episodeNumber", "<init>", "(III)V", "getMediaId", "()I", "getSeasonNumber", "getEpisodeNumber", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlaybackEpisodeKey {
    public static final int $stable = 0;
    private final int episodeNumber;
    private final int mediaId;
    private final int seasonNumber;

    public PlaybackEpisodeKey(int i10, int i11, int i12) {
        this.mediaId = i10;
        this.seasonNumber = i11;
        this.episodeNumber = i12;
    }

    public static /* synthetic */ PlaybackEpisodeKey copy$default(PlaybackEpisodeKey playbackEpisodeKey, int i10, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = playbackEpisodeKey.mediaId;
        }
        if ((i13 & 2) != 0) {
            i11 = playbackEpisodeKey.seasonNumber;
        }
        if ((i13 & 4) != 0) {
            i12 = playbackEpisodeKey.episodeNumber;
        }
        return playbackEpisodeKey.copy(i10, i11, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMediaId() {
        return this.mediaId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getEpisodeNumber() {
        return this.episodeNumber;
    }

    public final PlaybackEpisodeKey copy(int mediaId, int seasonNumber, int episodeNumber) {
        return new PlaybackEpisodeKey(mediaId, seasonNumber, episodeNumber);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackEpisodeKey)) {
            return false;
        }
        PlaybackEpisodeKey playbackEpisodeKey = (PlaybackEpisodeKey) other;
        return this.mediaId == playbackEpisodeKey.mediaId && this.seasonNumber == playbackEpisodeKey.seasonNumber && this.episodeNumber == playbackEpisodeKey.episodeNumber;
    }

    public final int getEpisodeNumber() {
        return this.episodeNumber;
    }

    public final int getMediaId() {
        return this.mediaId;
    }

    public final int getSeasonNumber() {
        return this.seasonNumber;
    }

    public int hashCode() {
        return (((this.mediaId * 31) + this.seasonNumber) * 31) + this.episodeNumber;
    }

    public String toString() {
        return androidx.compose.material3.d.j(this.episodeNumber, ")", androidx.compose.foundation.c.v("PlaybackEpisodeKey(mediaId=", this.mediaId, ", seasonNumber=", this.seasonNumber, ", episodeNumber="));
    }
}
