package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003JG\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/TraktWatchedShow;", "", "plays", "", "lastWatchedAt", "", "lastUpdatedAt", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "seasons", "", "Lcom/arflix/tv/data/api/TraktWatchedSeason;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktShowInfo;Ljava/util/List;)V", "getPlays", "()I", "getLastWatchedAt", "()Ljava/lang/String;", "getLastUpdatedAt", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "getSeasons", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktWatchedShow {
    public static final int $stable = 0;

    @SerializedName("last_updated_at")
    private final String lastUpdatedAt;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;
    private final int plays;
    private final List<TraktWatchedSeason> seasons;
    private final TraktShowInfo show;

    public TraktWatchedShow(int i10, String str, String str2, TraktShowInfo traktShowInfo, List<TraktWatchedSeason> list) {
        this.plays = i10;
        this.lastWatchedAt = str;
        this.lastUpdatedAt = str2;
        this.show = traktShowInfo;
        this.seasons = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktWatchedShow copy$default(TraktWatchedShow traktWatchedShow, int i10, String str, String str2, TraktShowInfo traktShowInfo, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktWatchedShow.plays;
        }
        if ((i11 & 2) != 0) {
            str = traktWatchedShow.lastWatchedAt;
        }
        if ((i11 & 4) != 0) {
            str2 = traktWatchedShow.lastUpdatedAt;
        }
        if ((i11 & 8) != 0) {
            traktShowInfo = traktWatchedShow.show;
        }
        if ((i11 & 16) != 0) {
            list = traktWatchedShow.seasons;
        }
        List list2 = list;
        String str3 = str2;
        return traktWatchedShow.copy(i10, str, str3, traktShowInfo, list2);
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
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final List<TraktWatchedSeason> component5() {
        return this.seasons;
    }

    public final TraktWatchedShow copy(int plays, String lastWatchedAt, String lastUpdatedAt, TraktShowInfo show, List<TraktWatchedSeason> seasons) {
        return new TraktWatchedShow(plays, lastWatchedAt, lastUpdatedAt, show, seasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktWatchedShow)) {
            return false;
        }
        TraktWatchedShow traktWatchedShow = (TraktWatchedShow) other;
        return this.plays == traktWatchedShow.plays && p.a(this.lastWatchedAt, traktWatchedShow.lastWatchedAt) && p.a(this.lastUpdatedAt, traktWatchedShow.lastUpdatedAt) && p.a(this.show, traktWatchedShow.show) && p.a(this.seasons, traktWatchedShow.seasons);
    }

    public final String getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final int getPlays() {
        return this.plays;
    }

    public final List<TraktWatchedSeason> getSeasons() {
        return this.seasons;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public int hashCode() {
        int i10 = this.plays * 31;
        String str = this.lastWatchedAt;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.lastUpdatedAt;
        int iHashCode2 = (this.show.hashCode() + ((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
        List<TraktWatchedSeason> list = this.seasons;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.plays;
        String str = this.lastWatchedAt;
        String str2 = this.lastUpdatedAt;
        TraktShowInfo traktShowInfo = this.show;
        List<TraktWatchedSeason> list = this.seasons;
        StringBuilder sbO = a2.o("TraktWatchedShow(plays=", i10, ", lastWatchedAt=", str, ", lastUpdatedAt=");
        sbO.append(str2);
        sbO.append(", show=");
        sbO.append(traktShowInfo);
        sbO.append(", seasons=");
        return a0.c.q(sbO, list, ")");
    }
}
