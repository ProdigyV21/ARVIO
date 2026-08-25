package com.arflix.tv.data.api;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003J<\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/arflix/tv/data/api/TraktSearchResult;", "", LinkHeader.Parameters.Type, "", "score", "", "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/Float;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktShowInfo;)V", "getType", "()Ljava/lang/String;", "getScore", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Float;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktShowInfo;)Lcom/arflix/tv/data/api/TraktSearchResult;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSearchResult {
    public static final int $stable = 0;
    private final TraktMovieInfo movie;
    private final Float score;
    private final TraktShowInfo show;
    private final String type;

    public TraktSearchResult(String str, Float f10, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo) {
        this.type = str;
        this.score = f10;
        this.movie = traktMovieInfo;
        this.show = traktShowInfo;
    }

    public static /* synthetic */ TraktSearchResult copy$default(TraktSearchResult traktSearchResult, String str, Float f10, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktSearchResult.type;
        }
        if ((i10 & 2) != 0) {
            f10 = traktSearchResult.score;
        }
        if ((i10 & 4) != 0) {
            traktMovieInfo = traktSearchResult.movie;
        }
        if ((i10 & 8) != 0) {
            traktShowInfo = traktSearchResult.show;
        }
        return traktSearchResult.copy(str, f10, traktMovieInfo, traktShowInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Float getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final TraktSearchResult copy(String type, Float score, TraktMovieInfo movie, TraktShowInfo show) {
        return new TraktSearchResult(type, score, movie, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSearchResult)) {
            return false;
        }
        TraktSearchResult traktSearchResult = (TraktSearchResult) other;
        return p.a(this.type, traktSearchResult.type) && p.a(this.score, traktSearchResult.score) && p.a(this.movie, traktSearchResult.movie) && p.a(this.show, traktSearchResult.show);
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final Float getScore() {
        return this.score;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.type.hashCode() * 31;
        Float f10 = this.score;
        int iHashCode2 = (iHashCode + (f10 == null ? 0 : f10.hashCode())) * 31;
        TraktMovieInfo traktMovieInfo = this.movie;
        int iHashCode3 = (iHashCode2 + (traktMovieInfo == null ? 0 : traktMovieInfo.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        return iHashCode3 + (traktShowInfo != null ? traktShowInfo.hashCode() : 0);
    }

    public String toString() {
        return "TraktSearchResult(type=" + this.type + ", score=" + this.score + ", movie=" + this.movie + ", show=" + this.show + ")";
    }
}
