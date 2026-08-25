package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003JZ\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010!J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0018\u0010\u0012R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006'"}, d2 = {"Lcom/arflix/tv/data/api/SimklShowRef;", "", LinkHeader.Parameters.Title, "", "year", "", "ids", "Lcom/arflix/tv/data/api/SimklIds;", "seasons", "", "Lcom/arflix/tv/data/api/SimklSeasonRef;", "runtime", "poster", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/SimklIds;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIds", "()Lcom/arflix/tv/data/api/SimklIds;", "getSeasons", "()Ljava/util/List;", "getRuntime", "getPoster", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/SimklIds;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/SimklShowRef;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklShowRef {
    public static final int $stable = 0;

    @SerializedName("ids")
    private final SimklIds ids;

    @SerializedName("poster")
    private final String poster;

    @SerializedName("runtime")
    private final Integer runtime;

    @SerializedName("seasons")
    private final List<SimklSeasonRef> seasons;

    @SerializedName(LinkHeader.Parameters.Title)
    private final String title;

    @SerializedName("year")
    private final Integer year;

    public SimklShowRef(String str, Integer num, SimklIds simklIds, List<SimklSeasonRef> list, Integer num2, String str2) {
        this.title = str;
        this.year = num;
        this.ids = simklIds;
        this.seasons = list;
        this.runtime = num2;
        this.poster = str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimklShowRef copy$default(SimklShowRef simklShowRef, String str, Integer num, SimklIds simklIds, List list, Integer num2, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklShowRef.title;
        }
        if ((i10 & 2) != 0) {
            num = simklShowRef.year;
        }
        if ((i10 & 4) != 0) {
            simklIds = simklShowRef.ids;
        }
        if ((i10 & 8) != 0) {
            list = simklShowRef.seasons;
        }
        if ((i10 & 16) != 0) {
            num2 = simklShowRef.runtime;
        }
        if ((i10 & 32) != 0) {
            str2 = simklShowRef.poster;
        }
        Integer num3 = num2;
        String str3 = str2;
        return simklShowRef.copy(str, num, simklIds, list, num3, str3);
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
    public final SimklIds getIds() {
        return this.ids;
    }

    public final List<SimklSeasonRef> component4() {
        return this.seasons;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getRuntime() {
        return this.runtime;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    public final SimklShowRef copy(String title, Integer year, SimklIds ids, List<SimklSeasonRef> seasons, Integer runtime, String poster) {
        return new SimklShowRef(title, year, ids, seasons, runtime, poster);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklShowRef)) {
            return false;
        }
        SimklShowRef simklShowRef = (SimklShowRef) other;
        return p.a(this.title, simklShowRef.title) && p.a(this.year, simklShowRef.year) && p.a(this.ids, simklShowRef.ids) && p.a(this.seasons, simklShowRef.seasons) && p.a(this.runtime, simklShowRef.runtime) && p.a(this.poster, simklShowRef.poster);
    }

    public final SimklIds getIds() {
        return this.ids;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final Integer getRuntime() {
        return this.runtime;
    }

    public final List<SimklSeasonRef> getSeasons() {
        return this.seasons;
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
        int iHashCode2 = (this.ids.hashCode() + ((iHashCode + (num == null ? 0 : num.hashCode())) * 31)) * 31;
        List<SimklSeasonRef> list = this.seasons;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        Integer num2 = this.runtime;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.poster;
        return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SimklShowRef(title=" + this.title + ", year=" + this.year + ", ids=" + this.ids + ", seasons=" + this.seasons + ", runtime=" + this.runtime + ", poster=" + this.poster + ")";
    }

    public /* synthetic */ SimklShowRef(String str, Integer num, SimklIds simklIds, List list, Integer num2, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num, simklIds, (i10 & 8) != 0 ? null : list, (i10 & 16) != 0 ? null : num2, (i10 & 32) != 0 ? null : str2);
    }
}
