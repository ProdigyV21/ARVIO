package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u0011\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/api/TraktCollectionShow;", "", "collectedAt", "", "updatedAt", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "seasons", "", "Lcom/arflix/tv/data/api/TraktCollectionSeason;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktShowInfo;Ljava/util/List;)V", "getCollectedAt", "()Ljava/lang/String;", "getUpdatedAt", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "getSeasons", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktCollectionShow {
    public static final int $stable = 0;

    @SerializedName("collected_at")
    private final String collectedAt;
    private final List<TraktCollectionSeason> seasons;
    private final TraktShowInfo show;

    @SerializedName("updated_at")
    private final String updatedAt;

    public TraktCollectionShow(String str, String str2, TraktShowInfo traktShowInfo, List<TraktCollectionSeason> list) {
        this.collectedAt = str;
        this.updatedAt = str2;
        this.show = traktShowInfo;
        this.seasons = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktCollectionShow copy$default(TraktCollectionShow traktCollectionShow, String str, String str2, TraktShowInfo traktShowInfo, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktCollectionShow.collectedAt;
        }
        if ((i10 & 2) != 0) {
            str2 = traktCollectionShow.updatedAt;
        }
        if ((i10 & 4) != 0) {
            traktShowInfo = traktCollectionShow.show;
        }
        if ((i10 & 8) != 0) {
            list = traktCollectionShow.seasons;
        }
        return traktCollectionShow.copy(str, str2, traktShowInfo, list);
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
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final List<TraktCollectionSeason> component4() {
        return this.seasons;
    }

    public final TraktCollectionShow copy(String collectedAt, String updatedAt, TraktShowInfo show, List<TraktCollectionSeason> seasons) {
        return new TraktCollectionShow(collectedAt, updatedAt, show, seasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktCollectionShow)) {
            return false;
        }
        TraktCollectionShow traktCollectionShow = (TraktCollectionShow) other;
        return p.a(this.collectedAt, traktCollectionShow.collectedAt) && p.a(this.updatedAt, traktCollectionShow.updatedAt) && p.a(this.show, traktCollectionShow.show) && p.a(this.seasons, traktCollectionShow.seasons);
    }

    public final String getCollectedAt() {
        return this.collectedAt;
    }

    public final List<TraktCollectionSeason> getSeasons() {
        return this.seasons;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public int hashCode() {
        String str = this.collectedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.updatedAt;
        int iHashCode2 = (this.show.hashCode() + ((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
        List<TraktCollectionSeason> list = this.seasons;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        String str = this.collectedAt;
        String str2 = this.updatedAt;
        TraktShowInfo traktShowInfo = this.show;
        List<TraktCollectionSeason> list = this.seasons;
        StringBuilder sbR = a2.r("TraktCollectionShow(collectedAt=", str, ", updatedAt=", str2, ", show=");
        sbR.append(traktShowInfo);
        sbR.append(", seasons=");
        sbR.append(list);
        sbR.append(")");
        return sbR.toString();
    }
}
