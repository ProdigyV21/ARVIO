package com.arflix.tv.util;

import a0.c;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/util/EpisodeProgressSnapshot;", "", "season", "", "episode", "completed", "", "<init>", "(IIZ)V", "getSeason", "()I", "getEpisode", "getCompleted", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeProgressSnapshot {
    public static final int $stable = 0;
    private final boolean completed;
    private final int episode;
    private final int season;

    public EpisodeProgressSnapshot(int i10, int i11, boolean z) {
        this.season = i10;
        this.episode = i11;
        this.completed = z;
    }

    public static /* synthetic */ EpisodeProgressSnapshot copy$default(EpisodeProgressSnapshot episodeProgressSnapshot, int i10, int i11, boolean z, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = episodeProgressSnapshot.season;
        }
        if ((i12 & 2) != 0) {
            i11 = episodeProgressSnapshot.episode;
        }
        if ((i12 & 4) != 0) {
            z = episodeProgressSnapshot.completed;
        }
        return episodeProgressSnapshot.copy(i10, i11, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getCompleted() {
        return this.completed;
    }

    public final EpisodeProgressSnapshot copy(int season, int episode, boolean completed) {
        return new EpisodeProgressSnapshot(season, episode, completed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeProgressSnapshot)) {
            return false;
        }
        EpisodeProgressSnapshot episodeProgressSnapshot = (EpisodeProgressSnapshot) other;
        return this.season == episodeProgressSnapshot.season && this.episode == episodeProgressSnapshot.episode && this.completed == episodeProgressSnapshot.completed;
    }

    public final boolean getCompleted() {
        return this.completed;
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final int getSeason() {
        return this.season;
    }

    public int hashCode() {
        return (((this.season * 31) + this.episode) * 31) + (this.completed ? 1231 : 1237);
    }

    public String toString() {
        return c.m(")", this.completed, androidx.compose.foundation.c.v("EpisodeProgressSnapshot(season=", this.season, ", episode=", this.episode, ", completed="));
    }
}
