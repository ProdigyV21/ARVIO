package com.arflix.tv.data.api;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TraktSyncCounts;", "", "movies", "", "shows", "episodes", "<init>", "(III)V", "getMovies", "()I", "getShows", "getEpisodes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSyncCounts {
    public static final int $stable = 0;
    private final int episodes;
    private final int movies;
    private final int shows;

    public TraktSyncCounts() {
        this(0, 0, 0, 7, null);
    }

    public static /* synthetic */ TraktSyncCounts copy$default(TraktSyncCounts traktSyncCounts, int i10, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = traktSyncCounts.movies;
        }
        if ((i13 & 2) != 0) {
            i11 = traktSyncCounts.shows;
        }
        if ((i13 & 4) != 0) {
            i12 = traktSyncCounts.episodes;
        }
        return traktSyncCounts.copy(i10, i11, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMovies() {
        return this.movies;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getShows() {
        return this.shows;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getEpisodes() {
        return this.episodes;
    }

    public final TraktSyncCounts copy(int movies, int shows, int episodes) {
        return new TraktSyncCounts(movies, shows, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSyncCounts)) {
            return false;
        }
        TraktSyncCounts traktSyncCounts = (TraktSyncCounts) other;
        return this.movies == traktSyncCounts.movies && this.shows == traktSyncCounts.shows && this.episodes == traktSyncCounts.episodes;
    }

    public final int getEpisodes() {
        return this.episodes;
    }

    public final int getMovies() {
        return this.movies;
    }

    public final int getShows() {
        return this.shows;
    }

    public int hashCode() {
        return (((this.movies * 31) + this.shows) * 31) + this.episodes;
    }

    public String toString() {
        return androidx.compose.material3.d.j(this.episodes, ")", androidx.compose.foundation.c.v("TraktSyncCounts(movies=", this.movies, ", shows=", this.shows, ", episodes="));
    }

    public TraktSyncCounts(int i10, int i11, int i12) {
        this.movies = i10;
        this.shows = i11;
        this.episodes = i12;
    }

    public /* synthetic */ TraktSyncCounts(int i10, int i11, int i12, int i13, kotlin.jvm.internal.h hVar) {
        this((i13 & 1) != 0 ? 0 : i10, (i13 & 2) != 0 ? 0 : i11, (i13 & 4) != 0 ? 0 : i12);
    }
}
