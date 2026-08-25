package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J2\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/SimklEpisodeRef;", "", "season", "", "number", "ids", "Lcom/arflix/tv/data/api/SimklIds;", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/api/SimklIds;)V", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNumber", "getIds", "()Lcom/arflix/tv/data/api/SimklIds;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/api/SimklIds;)Lcom/arflix/tv/data/api/SimklEpisodeRef;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklEpisodeRef {
    public static final int $stable = 0;

    @SerializedName("ids")
    private final SimklIds ids;

    @SerializedName(alternate = {"episode"}, value = "number")
    private final Integer number;

    @SerializedName("season")
    private final Integer season;

    public SimklEpisodeRef() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ SimklEpisodeRef copy$default(SimklEpisodeRef simklEpisodeRef, Integer num, Integer num2, SimklIds simklIds, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = simklEpisodeRef.season;
        }
        if ((i10 & 2) != 0) {
            num2 = simklEpisodeRef.number;
        }
        if ((i10 & 4) != 0) {
            simklIds = simklEpisodeRef.ids;
        }
        return simklEpisodeRef.copy(num, num2, simklIds);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SimklIds getIds() {
        return this.ids;
    }

    public final SimklEpisodeRef copy(Integer season, Integer number, SimklIds ids) {
        return new SimklEpisodeRef(season, number, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklEpisodeRef)) {
            return false;
        }
        SimklEpisodeRef simklEpisodeRef = (SimklEpisodeRef) other;
        return p.a(this.season, simklEpisodeRef.season) && p.a(this.number, simklEpisodeRef.number) && p.a(this.ids, simklEpisodeRef.ids);
    }

    public final SimklIds getIds() {
        return this.ids;
    }

    public final Integer getNumber() {
        return this.number;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public int hashCode() {
        Integer num = this.season;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.number;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        SimklIds simklIds = this.ids;
        return iHashCode2 + (simklIds != null ? simklIds.hashCode() : 0);
    }

    public String toString() {
        return "SimklEpisodeRef(season=" + this.season + ", number=" + this.number + ", ids=" + this.ids + ")";
    }

    public SimklEpisodeRef(Integer num, Integer num2, SimklIds simklIds) {
        this.season = num;
        this.number = num2;
        this.ids = simklIds;
    }

    public /* synthetic */ SimklEpisodeRef(Integer num, Integer num2, SimklIds simklIds, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : num2, (i10 & 4) != 0 ? null : simklIds);
    }
}
