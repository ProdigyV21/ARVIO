package com.arflix.tv.util;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/util/WatchedEpisodeSnapshot;", "", "season", "", "episode", "watchedAt", "", "<init>", "(IILjava/lang/String;)V", "getSeason", "()I", "getEpisode", "getWatchedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class WatchedEpisodeSnapshot {
    public static final int $stable = 0;
    private final int episode;
    private final int season;
    private final String watchedAt;

    public WatchedEpisodeSnapshot(int i10, int i11, String str) {
        this.season = i10;
        this.episode = i11;
        this.watchedAt = str;
    }

    public static /* synthetic */ WatchedEpisodeSnapshot copy$default(WatchedEpisodeSnapshot watchedEpisodeSnapshot, int i10, int i11, String str, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = watchedEpisodeSnapshot.season;
        }
        if ((i12 & 2) != 0) {
            i11 = watchedEpisodeSnapshot.episode;
        }
        if ((i12 & 4) != 0) {
            str = watchedEpisodeSnapshot.watchedAt;
        }
        return watchedEpisodeSnapshot.copy(i10, i11, str);
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
    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public final WatchedEpisodeSnapshot copy(int season, int episode, String watchedAt) {
        return new WatchedEpisodeSnapshot(season, episode, watchedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchedEpisodeSnapshot)) {
            return false;
        }
        WatchedEpisodeSnapshot watchedEpisodeSnapshot = (WatchedEpisodeSnapshot) other;
        return this.season == watchedEpisodeSnapshot.season && this.episode == watchedEpisodeSnapshot.episode && p.a(this.watchedAt, watchedEpisodeSnapshot.watchedAt);
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final int getSeason() {
        return this.season;
    }

    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public int hashCode() {
        int i10 = ((this.season * 31) + this.episode) * 31;
        String str = this.watchedAt;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        int i10 = this.season;
        int i11 = this.episode;
        return c.p(androidx.compose.foundation.c.v("WatchedEpisodeSnapshot(season=", i10, ", episode=", i11, ", watchedAt="), this.watchedAt, ")");
    }
}
