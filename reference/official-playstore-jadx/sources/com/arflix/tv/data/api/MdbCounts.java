package com.arflix.tv.data.api;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/api/MdbCounts;", "", "movies", "", "shows", "seasons", "episodes", "<init>", "(IIII)V", "getMovies", "()I", "getShows", "getSeasons", "getEpisodes", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbCounts {
    public static final int $stable = 0;
    private final int episodes;
    private final int movies;
    private final int seasons;
    private final int shows;

    public MdbCounts() {
        this(0, 0, 0, 0, 15, null);
    }

    public static /* synthetic */ MdbCounts copy$default(MdbCounts mdbCounts, int i10, int i11, int i12, int i13, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = mdbCounts.movies;
        }
        if ((i14 & 2) != 0) {
            i11 = mdbCounts.shows;
        }
        if ((i14 & 4) != 0) {
            i12 = mdbCounts.seasons;
        }
        if ((i14 & 8) != 0) {
            i13 = mdbCounts.episodes;
        }
        return mdbCounts.copy(i10, i11, i12, i13);
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
    public final int getSeasons() {
        return this.seasons;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEpisodes() {
        return this.episodes;
    }

    public final MdbCounts copy(int movies, int shows, int seasons, int episodes) {
        return new MdbCounts(movies, shows, seasons, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbCounts)) {
            return false;
        }
        MdbCounts mdbCounts = (MdbCounts) other;
        return this.movies == mdbCounts.movies && this.shows == mdbCounts.shows && this.seasons == mdbCounts.seasons && this.episodes == mdbCounts.episodes;
    }

    public final int getEpisodes() {
        return this.episodes;
    }

    public final int getMovies() {
        return this.movies;
    }

    public final int getSeasons() {
        return this.seasons;
    }

    public final int getShows() {
        return this.shows;
    }

    public int hashCode() {
        return (((((this.movies * 31) + this.shows) * 31) + this.seasons) * 31) + this.episodes;
    }

    public String toString() {
        int i10 = this.movies;
        int i11 = this.shows;
        int i12 = this.seasons;
        int i13 = this.episodes;
        StringBuilder sbV = androidx.compose.foundation.c.v("MdbCounts(movies=", i10, ", shows=", i11, ", seasons=");
        sbV.append(i12);
        sbV.append(", episodes=");
        sbV.append(i13);
        sbV.append(")");
        return sbV.toString();
    }

    public MdbCounts(int i10, int i11, int i12, int i13) {
        this.movies = i10;
        this.shows = i11;
        this.seasons = i12;
        this.episodes = i13;
    }

    public /* synthetic */ MdbCounts(int i10, int i11, int i12, int i13, int i14, kotlin.jvm.internal.h hVar) {
        this((i14 & 1) != 0 ? 0 : i10, (i14 & 2) != 0 ? 0 : i11, (i14 & 4) != 0 ? 0 : i12, (i14 & 8) != 0 ? 0 : i13);
    }
}
