package com.arflix.tv.data.api;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/api/MdbMovieInfo;", "", LinkHeader.Parameters.Title, "", "year", "", "ids", "Lcom/arflix/tv/data/api/MdbIds;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/MdbIds;)V", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIds", "()Lcom/arflix/tv/data/api/MdbIds;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/MdbIds;)Lcom/arflix/tv/data/api/MdbMovieInfo;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbMovieInfo {
    public static final int $stable = 0;
    private final MdbIds ids;
    private final String title;
    private final Integer year;

    public MdbMovieInfo() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ MdbMovieInfo copy$default(MdbMovieInfo mdbMovieInfo, String str, Integer num, MdbIds mdbIds, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mdbMovieInfo.title;
        }
        if ((i10 & 2) != 0) {
            num = mdbMovieInfo.year;
        }
        if ((i10 & 4) != 0) {
            mdbIds = mdbMovieInfo.ids;
        }
        return mdbMovieInfo.copy(str, num, mdbIds);
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
    public final MdbIds getIds() {
        return this.ids;
    }

    public final MdbMovieInfo copy(String title, Integer year, MdbIds ids) {
        return new MdbMovieInfo(title, year, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbMovieInfo)) {
            return false;
        }
        MdbMovieInfo mdbMovieInfo = (MdbMovieInfo) other;
        return p.a(this.title, mdbMovieInfo.title) && p.a(this.year, mdbMovieInfo.year) && p.a(this.ids, mdbMovieInfo.ids);
    }

    public final MdbIds getIds() {
        return this.ids;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getYear() {
        return this.year;
    }

    public int hashCode() {
        String str = this.title;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.year;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        MdbIds mdbIds = this.ids;
        return iHashCode2 + (mdbIds != null ? mdbIds.hashCode() : 0);
    }

    public String toString() {
        return "MdbMovieInfo(title=" + this.title + ", year=" + this.year + ", ids=" + this.ids + ")";
    }

    public MdbMovieInfo(String str, Integer num, MdbIds mdbIds) {
        this.title = str;
        this.year = num;
        this.ids = mdbIds;
    }

    public /* synthetic */ MdbMovieInfo(String str, Integer num, MdbIds mdbIds, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : mdbIds);
    }
}
