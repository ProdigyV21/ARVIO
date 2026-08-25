package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/TraktWatchedMovie;", "", "plays", "", "lastWatchedAt", "", "lastUpdatedAt", "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;)V", "getPlays", "()I", "getLastWatchedAt", "()Ljava/lang/String;", "getLastUpdatedAt", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktWatchedMovie {
    public static final int $stable = 0;

    @SerializedName("last_updated_at")
    private final String lastUpdatedAt;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;
    private final TraktMovieInfo movie;
    private final int plays;

    public TraktWatchedMovie(int i10, String str, String str2, TraktMovieInfo traktMovieInfo) {
        this.plays = i10;
        this.lastWatchedAt = str;
        this.lastUpdatedAt = str2;
        this.movie = traktMovieInfo;
    }

    public static /* synthetic */ TraktWatchedMovie copy$default(TraktWatchedMovie traktWatchedMovie, int i10, String str, String str2, TraktMovieInfo traktMovieInfo, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktWatchedMovie.plays;
        }
        if ((i11 & 2) != 0) {
            str = traktWatchedMovie.lastWatchedAt;
        }
        if ((i11 & 4) != 0) {
            str2 = traktWatchedMovie.lastUpdatedAt;
        }
        if ((i11 & 8) != 0) {
            traktMovieInfo = traktWatchedMovie.movie;
        }
        return traktWatchedMovie.copy(i10, str, str2, traktMovieInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getPlays() {
        return this.plays;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final TraktWatchedMovie copy(int plays, String lastWatchedAt, String lastUpdatedAt, TraktMovieInfo movie) {
        return new TraktWatchedMovie(plays, lastWatchedAt, lastUpdatedAt, movie);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktWatchedMovie)) {
            return false;
        }
        TraktWatchedMovie traktWatchedMovie = (TraktWatchedMovie) other;
        return this.plays == traktWatchedMovie.plays && p.a(this.lastWatchedAt, traktWatchedMovie.lastWatchedAt) && p.a(this.lastUpdatedAt, traktWatchedMovie.lastUpdatedAt) && p.a(this.movie, traktWatchedMovie.movie);
    }

    public final String getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final int getPlays() {
        return this.plays;
    }

    public int hashCode() {
        int i10 = this.plays * 31;
        String str = this.lastWatchedAt;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.lastUpdatedAt;
        return this.movie.hashCode() + ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    public String toString() {
        int i10 = this.plays;
        String str = this.lastWatchedAt;
        String str2 = this.lastUpdatedAt;
        TraktMovieInfo traktMovieInfo = this.movie;
        StringBuilder sbO = a2.o("TraktWatchedMovie(plays=", i10, ", lastWatchedAt=", str, ", lastUpdatedAt=");
        sbO.append(str2);
        sbO.append(", movie=");
        sbO.append(traktMovieInfo);
        sbO.append(")");
        return sbO.toString();
    }
}
