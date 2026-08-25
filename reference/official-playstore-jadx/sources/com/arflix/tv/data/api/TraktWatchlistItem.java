package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003J?\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/arflix/tv/data/api/TraktWatchlistItem;", "", "rank", "", "listedAt", "", LinkHeader.Parameters.Type, "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktShowInfo;)V", "getRank", "()I", "getListedAt", "()Ljava/lang/String;", "getType", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktWatchlistItem {
    public static final int $stable = 0;

    @SerializedName("listed_at")
    private final String listedAt;
    private final TraktMovieInfo movie;
    private final int rank;
    private final TraktShowInfo show;
    private final String type;

    public TraktWatchlistItem(int i10, String str, String str2, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo) {
        this.rank = i10;
        this.listedAt = str;
        this.type = str2;
        this.movie = traktMovieInfo;
        this.show = traktShowInfo;
    }

    public static /* synthetic */ TraktWatchlistItem copy$default(TraktWatchlistItem traktWatchlistItem, int i10, String str, String str2, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktWatchlistItem.rank;
        }
        if ((i11 & 2) != 0) {
            str = traktWatchlistItem.listedAt;
        }
        if ((i11 & 4) != 0) {
            str2 = traktWatchlistItem.type;
        }
        if ((i11 & 8) != 0) {
            traktMovieInfo = traktWatchlistItem.movie;
        }
        if ((i11 & 16) != 0) {
            traktShowInfo = traktWatchlistItem.show;
        }
        TraktShowInfo traktShowInfo2 = traktShowInfo;
        String str3 = str2;
        return traktWatchlistItem.copy(i10, str, str3, traktMovieInfo, traktShowInfo2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRank() {
        return this.rank;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getListedAt() {
        return this.listedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final TraktWatchlistItem copy(int rank, String listedAt, String type, TraktMovieInfo movie, TraktShowInfo show) {
        return new TraktWatchlistItem(rank, listedAt, type, movie, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktWatchlistItem)) {
            return false;
        }
        TraktWatchlistItem traktWatchlistItem = (TraktWatchlistItem) other;
        return this.rank == traktWatchlistItem.rank && p.a(this.listedAt, traktWatchlistItem.listedAt) && p.a(this.type, traktWatchlistItem.type) && p.a(this.movie, traktWatchlistItem.movie) && p.a(this.show, traktWatchlistItem.show);
    }

    public final String getListedAt() {
        return this.listedAt;
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final int getRank() {
        return this.rank;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.rank * 31, 31, this.listedAt), 31, this.type);
        TraktMovieInfo traktMovieInfo = this.movie;
        int iHashCode = (iC + (traktMovieInfo == null ? 0 : traktMovieInfo.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        return iHashCode + (traktShowInfo != null ? traktShowInfo.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.rank;
        String str = this.listedAt;
        String str2 = this.type;
        TraktMovieInfo traktMovieInfo = this.movie;
        TraktShowInfo traktShowInfo = this.show;
        StringBuilder sbO = a2.o("TraktWatchlistItem(rank=", i10, ", listedAt=", str, ", type=");
        sbO.append(str2);
        sbO.append(", movie=");
        sbO.append(traktMovieInfo);
        sbO.append(", show=");
        sbO.append(traktShowInfo);
        sbO.append(")");
        return sbO.toString();
    }
}
