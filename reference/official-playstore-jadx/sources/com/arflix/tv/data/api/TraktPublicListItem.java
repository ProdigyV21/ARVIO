package com.arflix.tv.data.api;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003J<\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/api/TraktPublicListItem;", "", "rank", "", LinkHeader.Parameters.Type, "", "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktShowInfo;)V", "getRank", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getType", "()Ljava/lang/String;", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktShowInfo;)Lcom/arflix/tv/data/api/TraktPublicListItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktPublicListItem {
    public static final int $stable = 0;
    private final TraktMovieInfo movie;
    private final Integer rank;
    private final TraktShowInfo show;
    private final String type;

    public TraktPublicListItem(Integer num, String str, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo) {
        this.rank = num;
        this.type = str;
        this.movie = traktMovieInfo;
        this.show = traktShowInfo;
    }

    public static /* synthetic */ TraktPublicListItem copy$default(TraktPublicListItem traktPublicListItem, Integer num, String str, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = traktPublicListItem.rank;
        }
        if ((i10 & 2) != 0) {
            str = traktPublicListItem.type;
        }
        if ((i10 & 4) != 0) {
            traktMovieInfo = traktPublicListItem.movie;
        }
        if ((i10 & 8) != 0) {
            traktShowInfo = traktPublicListItem.show;
        }
        return traktPublicListItem.copy(num, str, traktMovieInfo, traktShowInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getRank() {
        return this.rank;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final TraktPublicListItem copy(Integer rank, String type, TraktMovieInfo movie, TraktShowInfo show) {
        return new TraktPublicListItem(rank, type, movie, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktPublicListItem)) {
            return false;
        }
        TraktPublicListItem traktPublicListItem = (TraktPublicListItem) other;
        return p.a(this.rank, traktPublicListItem.rank) && p.a(this.type, traktPublicListItem.type) && p.a(this.movie, traktPublicListItem.movie) && p.a(this.show, traktPublicListItem.show);
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final Integer getRank() {
        return this.rank;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        Integer num = this.rank;
        int iC = androidx.compose.foundation.c.c((num == null ? 0 : num.hashCode()) * 31, 31, this.type);
        TraktMovieInfo traktMovieInfo = this.movie;
        int iHashCode = (iC + (traktMovieInfo == null ? 0 : traktMovieInfo.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        return iHashCode + (traktShowInfo != null ? traktShowInfo.hashCode() : 0);
    }

    public String toString() {
        return "TraktPublicListItem(rank=" + this.rank + ", type=" + this.type + ", movie=" + this.movie + ", show=" + this.show + ")";
    }

    public /* synthetic */ TraktPublicListItem(Integer num, String str, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, str, (i10 & 4) != 0 ? null : traktMovieInfo, (i10 & 8) != 0 ? null : traktShowInfo);
    }
}
