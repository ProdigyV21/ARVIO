package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/api/TraktListItem;", "", "rank", "", LinkHeader.Parameters.Type, "", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "<init>", "(ILjava/lang/String;Lcom/arflix/tv/data/api/TraktShowInfo;)V", "getRank", "()I", "getType", "()Ljava/lang/String;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktListItem {
    public static final int $stable = 0;
    private final int rank;
    private final TraktShowInfo show;
    private final String type;

    public TraktListItem(int i10, String str, TraktShowInfo traktShowInfo) {
        this.rank = i10;
        this.type = str;
        this.show = traktShowInfo;
    }

    public static /* synthetic */ TraktListItem copy$default(TraktListItem traktListItem, int i10, String str, TraktShowInfo traktShowInfo, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktListItem.rank;
        }
        if ((i11 & 2) != 0) {
            str = traktListItem.type;
        }
        if ((i11 & 4) != 0) {
            traktShowInfo = traktListItem.show;
        }
        return traktListItem.copy(i10, str, traktShowInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRank() {
        return this.rank;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final TraktListItem copy(int rank, String type, TraktShowInfo show) {
        return new TraktListItem(rank, type, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktListItem)) {
            return false;
        }
        TraktListItem traktListItem = (TraktListItem) other;
        return this.rank == traktListItem.rank && p.a(this.type, traktListItem.type) && p.a(this.show, traktListItem.show);
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
        int iC = androidx.compose.foundation.c.c(this.rank * 31, 31, this.type);
        TraktShowInfo traktShowInfo = this.show;
        return iC + (traktShowInfo == null ? 0 : traktShowInfo.hashCode());
    }

    public String toString() {
        int i10 = this.rank;
        String str = this.type;
        TraktShowInfo traktShowInfo = this.show;
        StringBuilder sbO = a2.o("TraktListItem(rank=", i10, ", type=", str, ", show=");
        sbO.append(traktShowInfo);
        sbO.append(")");
        return sbO.toString();
    }
}
