package com.arflix.tv.data.api;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J0\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/TraktListSearchResult;", "", LinkHeader.Parameters.Type, "", "score", "", "list", "Lcom/arflix/tv/data/api/TraktSearchList;", "<init>", "(Ljava/lang/String;Ljava/lang/Float;Lcom/arflix/tv/data/api/TraktSearchList;)V", "getType", "()Ljava/lang/String;", "getScore", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getList", "()Lcom/arflix/tv/data/api/TraktSearchList;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Float;Lcom/arflix/tv/data/api/TraktSearchList;)Lcom/arflix/tv/data/api/TraktListSearchResult;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktListSearchResult {
    public static final int $stable = 0;
    private final TraktSearchList list;
    private final Float score;
    private final String type;

    public TraktListSearchResult(String str, Float f10, TraktSearchList traktSearchList) {
        this.type = str;
        this.score = f10;
        this.list = traktSearchList;
    }

    public static /* synthetic */ TraktListSearchResult copy$default(TraktListSearchResult traktListSearchResult, String str, Float f10, TraktSearchList traktSearchList, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktListSearchResult.type;
        }
        if ((i10 & 2) != 0) {
            f10 = traktListSearchResult.score;
        }
        if ((i10 & 4) != 0) {
            traktSearchList = traktListSearchResult.list;
        }
        return traktListSearchResult.copy(str, f10, traktSearchList);
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
    public final TraktSearchList getList() {
        return this.list;
    }

    public final TraktListSearchResult copy(String type, Float score, TraktSearchList list) {
        return new TraktListSearchResult(type, score, list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktListSearchResult)) {
            return false;
        }
        TraktListSearchResult traktListSearchResult = (TraktListSearchResult) other;
        return p.a(this.type, traktListSearchResult.type) && p.a(this.score, traktListSearchResult.score) && p.a(this.list, traktListSearchResult.list);
    }

    public final TraktSearchList getList() {
        return this.list;
    }

    public final Float getScore() {
        return this.score;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.type.hashCode() * 31;
        Float f10 = this.score;
        int iHashCode2 = (iHashCode + (f10 == null ? 0 : f10.hashCode())) * 31;
        TraktSearchList traktSearchList = this.list;
        return iHashCode2 + (traktSearchList != null ? traktSearchList.hashCode() : 0);
    }

    public String toString() {
        return "TraktListSearchResult(type=" + this.type + ", score=" + this.score + ", list=" + this.list + ")";
    }
}
