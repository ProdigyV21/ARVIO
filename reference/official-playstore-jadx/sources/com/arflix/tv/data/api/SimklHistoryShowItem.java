package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010#\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010'\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001dJt\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u0010)J\u0014\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010-\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010.\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001f\u0010\u001d¨\u0006/"}, d2 = {"Lcom/arflix/tv/data/api/SimklHistoryShowItem;", "", "lastWatchedAt", "", "status", "show", "Lcom/arflix/tv/data/api/SimklShowRef;", "seasons", "", "Lcom/arflix/tv/data/api/SimklHistorySeasonItem;", "nextToWatch", "nextToWatchInfo", "Lcom/arflix/tv/data/api/SimklNextToWatchInfo;", "watchedEpisodesCount", "", "totalEpisodesCount", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklShowRef;Ljava/util/List;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklNextToWatchInfo;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getLastWatchedAt", "()Ljava/lang/String;", "getStatus", "getShow", "()Lcom/arflix/tv/data/api/SimklShowRef;", "getSeasons", "()Ljava/util/List;", "getNextToWatch", "getNextToWatchInfo", "()Lcom/arflix/tv/data/api/SimklNextToWatchInfo;", "getWatchedEpisodesCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTotalEpisodesCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklShowRef;Ljava/util/List;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklNextToWatchInfo;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/api/SimklHistoryShowItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklHistoryShowItem {
    public static final int $stable = 0;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;

    @SerializedName("next_to_watch")
    private final String nextToWatch;

    @SerializedName("next_to_watch_info")
    private final SimklNextToWatchInfo nextToWatchInfo;

    @SerializedName("seasons")
    private final List<SimklHistorySeasonItem> seasons;

    @SerializedName("show")
    private final SimklShowRef show;

    @SerializedName("status")
    private final String status;

    @SerializedName("total_episodes_count")
    private final Integer totalEpisodesCount;

    @SerializedName("watched_episodes_count")
    private final Integer watchedEpisodesCount;

    public SimklHistoryShowItem() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimklHistoryShowItem copy$default(SimklHistoryShowItem simklHistoryShowItem, String str, String str2, SimklShowRef simklShowRef, List list, String str3, SimklNextToWatchInfo simklNextToWatchInfo, Integer num, Integer num2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklHistoryShowItem.lastWatchedAt;
        }
        if ((i10 & 2) != 0) {
            str2 = simklHistoryShowItem.status;
        }
        if ((i10 & 4) != 0) {
            simklShowRef = simklHistoryShowItem.show;
        }
        if ((i10 & 8) != 0) {
            list = simklHistoryShowItem.seasons;
        }
        if ((i10 & 16) != 0) {
            str3 = simklHistoryShowItem.nextToWatch;
        }
        if ((i10 & 32) != 0) {
            simklNextToWatchInfo = simklHistoryShowItem.nextToWatchInfo;
        }
        if ((i10 & 64) != 0) {
            num = simklHistoryShowItem.watchedEpisodesCount;
        }
        if ((i10 & 128) != 0) {
            num2 = simklHistoryShowItem.totalEpisodesCount;
        }
        Integer num3 = num;
        Integer num4 = num2;
        String str4 = str3;
        SimklNextToWatchInfo simklNextToWatchInfo2 = simklNextToWatchInfo;
        return simklHistoryShowItem.copy(str, str2, simklShowRef, list, str4, simklNextToWatchInfo2, num3, num4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SimklShowRef getShow() {
        return this.show;
    }

    public final List<SimklHistorySeasonItem> component4() {
        return this.seasons;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getNextToWatch() {
        return this.nextToWatch;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final SimklNextToWatchInfo getNextToWatchInfo() {
        return this.nextToWatchInfo;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getWatchedEpisodesCount() {
        return this.watchedEpisodesCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getTotalEpisodesCount() {
        return this.totalEpisodesCount;
    }

    public final SimklHistoryShowItem copy(String lastWatchedAt, String status, SimklShowRef show, List<SimklHistorySeasonItem> seasons, String nextToWatch, SimklNextToWatchInfo nextToWatchInfo, Integer watchedEpisodesCount, Integer totalEpisodesCount) {
        return new SimklHistoryShowItem(lastWatchedAt, status, show, seasons, nextToWatch, nextToWatchInfo, watchedEpisodesCount, totalEpisodesCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklHistoryShowItem)) {
            return false;
        }
        SimklHistoryShowItem simklHistoryShowItem = (SimklHistoryShowItem) other;
        return p.a(this.lastWatchedAt, simklHistoryShowItem.lastWatchedAt) && p.a(this.status, simklHistoryShowItem.status) && p.a(this.show, simklHistoryShowItem.show) && p.a(this.seasons, simklHistoryShowItem.seasons) && p.a(this.nextToWatch, simklHistoryShowItem.nextToWatch) && p.a(this.nextToWatchInfo, simklHistoryShowItem.nextToWatchInfo) && p.a(this.watchedEpisodesCount, simklHistoryShowItem.watchedEpisodesCount) && p.a(this.totalEpisodesCount, simklHistoryShowItem.totalEpisodesCount);
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final String getNextToWatch() {
        return this.nextToWatch;
    }

    public final SimklNextToWatchInfo getNextToWatchInfo() {
        return this.nextToWatchInfo;
    }

    public final List<SimklHistorySeasonItem> getSeasons() {
        return this.seasons;
    }

    public final SimklShowRef getShow() {
        return this.show;
    }

    public final String getStatus() {
        return this.status;
    }

    public final Integer getTotalEpisodesCount() {
        return this.totalEpisodesCount;
    }

    public final Integer getWatchedEpisodesCount() {
        return this.watchedEpisodesCount;
    }

    public int hashCode() {
        String str = this.lastWatchedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.status;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        SimklShowRef simklShowRef = this.show;
        int iHashCode3 = (iHashCode2 + (simklShowRef == null ? 0 : simklShowRef.hashCode())) * 31;
        List<SimklHistorySeasonItem> list = this.seasons;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        String str3 = this.nextToWatch;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        SimklNextToWatchInfo simklNextToWatchInfo = this.nextToWatchInfo;
        int iHashCode6 = (iHashCode5 + (simklNextToWatchInfo == null ? 0 : simklNextToWatchInfo.hashCode())) * 31;
        Integer num = this.watchedEpisodesCount;
        int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.totalEpisodesCount;
        return iHashCode7 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        String str = this.lastWatchedAt;
        String str2 = this.status;
        SimklShowRef simklShowRef = this.show;
        List<SimklHistorySeasonItem> list = this.seasons;
        String str3 = this.nextToWatch;
        SimklNextToWatchInfo simklNextToWatchInfo = this.nextToWatchInfo;
        Integer num = this.watchedEpisodesCount;
        Integer num2 = this.totalEpisodesCount;
        StringBuilder sbR = a2.r("SimklHistoryShowItem(lastWatchedAt=", str, ", status=", str2, ", show=");
        sbR.append(simklShowRef);
        sbR.append(", seasons=");
        sbR.append(list);
        sbR.append(", nextToWatch=");
        sbR.append(str3);
        sbR.append(", nextToWatchInfo=");
        sbR.append(simklNextToWatchInfo);
        sbR.append(", watchedEpisodesCount=");
        sbR.append(num);
        sbR.append(", totalEpisodesCount=");
        sbR.append(num2);
        sbR.append(")");
        return sbR.toString();
    }

    public SimklHistoryShowItem(String str, String str2, SimklShowRef simklShowRef, List<SimklHistorySeasonItem> list, String str3, SimklNextToWatchInfo simklNextToWatchInfo, Integer num, Integer num2) {
        this.lastWatchedAt = str;
        this.status = str2;
        this.show = simklShowRef;
        this.seasons = list;
        this.nextToWatch = str3;
        this.nextToWatchInfo = simklNextToWatchInfo;
        this.watchedEpisodesCount = num;
        this.totalEpisodesCount = num2;
    }

    public /* synthetic */ SimklHistoryShowItem(String str, String str2, SimklShowRef simklShowRef, List list, String str3, SimklNextToWatchInfo simklNextToWatchInfo, Integer num, Integer num2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : simklShowRef, (i10 & 8) != 0 ? null : list, (i10 & 16) != 0 ? null : str3, (i10 & 32) != 0 ? null : simklNextToWatchInfo, (i10 & 64) != 0 ? null : num, (i10 & 128) != 0 ? null : num2);
    }
}
