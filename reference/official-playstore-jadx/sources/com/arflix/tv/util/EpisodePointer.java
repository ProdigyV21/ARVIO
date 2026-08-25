package com.arflix.tv.util;

import androidx.compose.foundation.c;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/util/EpisodePointer;", "", "season", "", "episode", "<init>", "(II)V", "getSeason", "()I", "getEpisode", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodePointer {
    public static final int $stable = 0;
    private final int episode;
    private final int season;

    public EpisodePointer(int i10, int i11) {
        this.season = i10;
        this.episode = i11;
    }

    public static /* synthetic */ EpisodePointer copy$default(EpisodePointer episodePointer, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = episodePointer.season;
        }
        if ((i12 & 2) != 0) {
            i11 = episodePointer.episode;
        }
        return episodePointer.copy(i10, i11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    public final EpisodePointer copy(int season, int episode) {
        return new EpisodePointer(season, episode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodePointer)) {
            return false;
        }
        EpisodePointer episodePointer = (EpisodePointer) other;
        return this.season == episodePointer.season && this.episode == episodePointer.episode;
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final int getSeason() {
        return this.season;
    }

    public int hashCode() {
        return (this.season * 31) + this.episode;
    }

    public String toString() {
        return c.s("EpisodePointer(season=", this.season, ", episode=", this.episode, ")");
    }
}
