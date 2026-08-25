package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/TraktCollectionMovie;", "", "collectedAt", "", "updatedAt", "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;)V", "getCollectedAt", "()Ljava/lang/String;", "getUpdatedAt", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktCollectionMovie {
    public static final int $stable = 0;

    @SerializedName("collected_at")
    private final String collectedAt;
    private final TraktMovieInfo movie;

    @SerializedName("updated_at")
    private final String updatedAt;

    public TraktCollectionMovie(String str, String str2, TraktMovieInfo traktMovieInfo) {
        this.collectedAt = str;
        this.updatedAt = str2;
        this.movie = traktMovieInfo;
    }

    public static /* synthetic */ TraktCollectionMovie copy$default(TraktCollectionMovie traktCollectionMovie, String str, String str2, TraktMovieInfo traktMovieInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktCollectionMovie.collectedAt;
        }
        if ((i10 & 2) != 0) {
            str2 = traktCollectionMovie.updatedAt;
        }
        if ((i10 & 4) != 0) {
            traktMovieInfo = traktCollectionMovie.movie;
        }
        return traktCollectionMovie.copy(str, str2, traktMovieInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCollectedAt() {
        return this.collectedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final TraktCollectionMovie copy(String collectedAt, String updatedAt, TraktMovieInfo movie) {
        return new TraktCollectionMovie(collectedAt, updatedAt, movie);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktCollectionMovie)) {
            return false;
        }
        TraktCollectionMovie traktCollectionMovie = (TraktCollectionMovie) other;
        return p.a(this.collectedAt, traktCollectionMovie.collectedAt) && p.a(this.updatedAt, traktCollectionMovie.updatedAt) && p.a(this.movie, traktCollectionMovie.movie);
    }

    public final String getCollectedAt() {
        return this.collectedAt;
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public int hashCode() {
        String str = this.collectedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.updatedAt;
        return this.movie.hashCode() + ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    public String toString() {
        String str = this.collectedAt;
        String str2 = this.updatedAt;
        TraktMovieInfo traktMovieInfo = this.movie;
        StringBuilder sbR = a2.r("TraktCollectionMovie(collectedAt=", str, ", updatedAt=", str2, ", movie=");
        sbR.append(traktMovieInfo);
        sbR.append(")");
        return sbR.toString();
    }
}
