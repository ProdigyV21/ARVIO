package com.arflix.tv.data.api;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\nHÆ\u0003JJ\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0006HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/api/MdbEpisodeInfo;", "", "season", "", "number", ContentDisposition.Parameters.Name, "", "ids", "Lcom/arflix/tv/data/api/MdbIds;", "show", "Lcom/arflix/tv/data/api/MdbShowInfo;", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbIds;Lcom/arflix/tv/data/api/MdbShowInfo;)V", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNumber", "getName", "()Ljava/lang/String;", "getIds", "()Lcom/arflix/tv/data/api/MdbIds;", "getShow", "()Lcom/arflix/tv/data/api/MdbShowInfo;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbIds;Lcom/arflix/tv/data/api/MdbShowInfo;)Lcom/arflix/tv/data/api/MdbEpisodeInfo;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbEpisodeInfo {
    public static final int $stable = 0;
    private final MdbIds ids;
    private final String name;
    private final Integer number;
    private final Integer season;
    private final MdbShowInfo show;

    public MdbEpisodeInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ MdbEpisodeInfo copy$default(MdbEpisodeInfo mdbEpisodeInfo, Integer num, Integer num2, String str, MdbIds mdbIds, MdbShowInfo mdbShowInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = mdbEpisodeInfo.season;
        }
        if ((i10 & 2) != 0) {
            num2 = mdbEpisodeInfo.number;
        }
        if ((i10 & 4) != 0) {
            str = mdbEpisodeInfo.name;
        }
        if ((i10 & 8) != 0) {
            mdbIds = mdbEpisodeInfo.ids;
        }
        if ((i10 & 16) != 0) {
            mdbShowInfo = mdbEpisodeInfo.show;
        }
        MdbShowInfo mdbShowInfo2 = mdbShowInfo;
        String str2 = str;
        return mdbEpisodeInfo.copy(num, num2, str2, mdbIds, mdbShowInfo2);
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
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final MdbIds getIds() {
        return this.ids;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final MdbShowInfo getShow() {
        return this.show;
    }

    public final MdbEpisodeInfo copy(Integer season, Integer number, String name, MdbIds ids, MdbShowInfo show) {
        return new MdbEpisodeInfo(season, number, name, ids, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbEpisodeInfo)) {
            return false;
        }
        MdbEpisodeInfo mdbEpisodeInfo = (MdbEpisodeInfo) other;
        return p.a(this.season, mdbEpisodeInfo.season) && p.a(this.number, mdbEpisodeInfo.number) && p.a(this.name, mdbEpisodeInfo.name) && p.a(this.ids, mdbEpisodeInfo.ids) && p.a(this.show, mdbEpisodeInfo.show);
    }

    public final MdbIds getIds() {
        return this.ids;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getNumber() {
        return this.number;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final MdbShowInfo getShow() {
        return this.show;
    }

    public int hashCode() {
        Integer num = this.season;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.number;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str = this.name;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        MdbIds mdbIds = this.ids;
        int iHashCode4 = (iHashCode3 + (mdbIds == null ? 0 : mdbIds.hashCode())) * 31;
        MdbShowInfo mdbShowInfo = this.show;
        return iHashCode4 + (mdbShowInfo != null ? mdbShowInfo.hashCode() : 0);
    }

    public String toString() {
        return "MdbEpisodeInfo(season=" + this.season + ", number=" + this.number + ", name=" + this.name + ", ids=" + this.ids + ", show=" + this.show + ")";
    }

    public MdbEpisodeInfo(Integer num, Integer num2, String str, MdbIds mdbIds, MdbShowInfo mdbShowInfo) {
        this.season = num;
        this.number = num2;
        this.name = str;
        this.ids = mdbIds;
        this.show = mdbShowInfo;
    }

    public /* synthetic */ MdbEpisodeInfo(Integer num, Integer num2, String str, MdbIds mdbIds, MdbShowInfo mdbShowInfo, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : num2, (i10 & 4) != 0 ? null : str, (i10 & 8) != 0 ? null : mdbIds, (i10 & 16) != 0 ? null : mdbShowInfo);
    }
}
