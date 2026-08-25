package com.arflix.tv.data.api;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "", "season", "", "number", LinkHeader.Parameters.Title, "", "ids", "Lcom/arflix/tv/data/api/TraktIds;", "<init>", "(IILjava/lang/String;Lcom/arflix/tv/data/api/TraktIds;)V", "getSeason", "()I", "getNumber", "getTitle", "()Ljava/lang/String;", "getIds", "()Lcom/arflix/tv/data/api/TraktIds;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktEpisodeInfo {
    public static final int $stable = 0;
    private final TraktIds ids;
    private final int number;
    private final int season;
    private final String title;

    public TraktEpisodeInfo(int i10, int i11, String str, TraktIds traktIds) {
        this.season = i10;
        this.number = i11;
        this.title = str;
        this.ids = traktIds;
    }

    public static /* synthetic */ TraktEpisodeInfo copy$default(TraktEpisodeInfo traktEpisodeInfo, int i10, int i11, String str, TraktIds traktIds, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = traktEpisodeInfo.season;
        }
        if ((i12 & 2) != 0) {
            i11 = traktEpisodeInfo.number;
        }
        if ((i12 & 4) != 0) {
            str = traktEpisodeInfo.title;
        }
        if ((i12 & 8) != 0) {
            traktIds = traktEpisodeInfo.ids;
        }
        return traktEpisodeInfo.copy(i10, i11, str, traktIds);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktIds getIds() {
        return this.ids;
    }

    public final TraktEpisodeInfo copy(int season, int number, String title, TraktIds ids) {
        return new TraktEpisodeInfo(season, number, title, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktEpisodeInfo)) {
            return false;
        }
        TraktEpisodeInfo traktEpisodeInfo = (TraktEpisodeInfo) other;
        return this.season == traktEpisodeInfo.season && this.number == traktEpisodeInfo.number && p.a(this.title, traktEpisodeInfo.title) && p.a(this.ids, traktEpisodeInfo.ids);
    }

    public final TraktIds getIds() {
        return this.ids;
    }

    public final int getNumber() {
        return this.number;
    }

    public final int getSeason() {
        return this.season;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int i10 = ((this.season * 31) + this.number) * 31;
        String str = this.title;
        return this.ids.hashCode() + ((i10 + (str == null ? 0 : str.hashCode())) * 31);
    }

    public String toString() {
        int i10 = this.season;
        int i11 = this.number;
        String str = this.title;
        TraktIds traktIds = this.ids;
        StringBuilder sbV = androidx.compose.foundation.c.v("TraktEpisodeInfo(season=", i10, ", number=", i11, ", title=");
        sbV.append(str);
        sbV.append(", ids=");
        sbV.append(traktIds);
        sbV.append(")");
        return sbV.toString();
    }
}
