package com.arflix.tv.data.api;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J.\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/api/TraktMovieInfo;", "", LinkHeader.Parameters.Title, "", "year", "", "ids", "Lcom/arflix/tv/data/api/TraktIds;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/TraktIds;)V", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIds", "()Lcom/arflix/tv/data/api/TraktIds;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/TraktIds;)Lcom/arflix/tv/data/api/TraktMovieInfo;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktMovieInfo {
    public static final int $stable = 0;
    private final TraktIds ids;
    private final String title;
    private final Integer year;

    public TraktMovieInfo(String str, Integer num, TraktIds traktIds) {
        this.title = str;
        this.year = num;
        this.ids = traktIds;
    }

    public static /* synthetic */ TraktMovieInfo copy$default(TraktMovieInfo traktMovieInfo, String str, Integer num, TraktIds traktIds, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktMovieInfo.title;
        }
        if ((i10 & 2) != 0) {
            num = traktMovieInfo.year;
        }
        if ((i10 & 4) != 0) {
            traktIds = traktMovieInfo.ids;
        }
        return traktMovieInfo.copy(str, num, traktIds);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktIds getIds() {
        return this.ids;
    }

    public final TraktMovieInfo copy(String title, Integer year, TraktIds ids) {
        return new TraktMovieInfo(title, year, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktMovieInfo)) {
            return false;
        }
        TraktMovieInfo traktMovieInfo = (TraktMovieInfo) other;
        return p.a(this.title, traktMovieInfo.title) && p.a(this.year, traktMovieInfo.year) && p.a(this.ids, traktMovieInfo.ids);
    }

    public final TraktIds getIds() {
        return this.ids;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getYear() {
        return this.year;
    }

    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        Integer num = this.year;
        return this.ids.hashCode() + ((iHashCode + (num == null ? 0 : num.hashCode())) * 31);
    }

    public String toString() {
        return "TraktMovieInfo(title=" + this.title + ", year=" + this.year + ", ids=" + this.ids + ")";
    }
}
