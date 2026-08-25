package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003JH\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0013\u0010\u000fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006!"}, d2 = {"Lcom/arflix/tv/data/api/SimklMovieRef;", "", LinkHeader.Parameters.Title, "", "year", "", "ids", "Lcom/arflix/tv/data/api/SimklIds;", "runtime", "poster", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/SimklIds;Ljava/lang/Integer;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIds", "()Lcom/arflix/tv/data/api/SimklIds;", "getRuntime", "getPoster", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/api/SimklIds;Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/SimklMovieRef;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklMovieRef {
    public static final int $stable = 0;

    @SerializedName("ids")
    private final SimklIds ids;

    @SerializedName("poster")
    private final String poster;

    @SerializedName("runtime")
    private final Integer runtime;

    @SerializedName(LinkHeader.Parameters.Title)
    private final String title;

    @SerializedName("year")
    private final Integer year;

    public SimklMovieRef(String str, Integer num, SimklIds simklIds, Integer num2, String str2) {
        this.title = str;
        this.year = num;
        this.ids = simklIds;
        this.runtime = num2;
        this.poster = str2;
    }

    public static /* synthetic */ SimklMovieRef copy$default(SimklMovieRef simklMovieRef, String str, Integer num, SimklIds simklIds, Integer num2, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklMovieRef.title;
        }
        if ((i10 & 2) != 0) {
            num = simklMovieRef.year;
        }
        if ((i10 & 4) != 0) {
            simklIds = simklMovieRef.ids;
        }
        if ((i10 & 8) != 0) {
            num2 = simklMovieRef.runtime;
        }
        if ((i10 & 16) != 0) {
            str2 = simklMovieRef.poster;
        }
        String str3 = str2;
        SimklIds simklIds2 = simklIds;
        return simklMovieRef.copy(str, num, simklIds2, num2, str3);
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

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getRuntime() {
        return this.runtime;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    public final SimklMovieRef copy(String title, Integer year, SimklIds ids, Integer runtime, String poster) {
        return new SimklMovieRef(title, year, ids, runtime, poster);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklMovieRef)) {
            return false;
        }
        SimklMovieRef simklMovieRef = (SimklMovieRef) other;
        return p.a(this.title, simklMovieRef.title) && p.a(this.year, simklMovieRef.year) && p.a(this.ids, simklMovieRef.ids) && p.a(this.runtime, simklMovieRef.runtime) && p.a(this.poster, simklMovieRef.poster);
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
        Integer num2 = this.runtime;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.poster;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.title;
        Integer num = this.year;
        SimklIds simklIds = this.ids;
        Integer num2 = this.runtime;
        String str2 = this.poster;
        StringBuilder sb2 = new StringBuilder("SimklMovieRef(title=");
        sb2.append(str);
        sb2.append(", year=");
        sb2.append(num);
        sb2.append(", ids=");
        sb2.append(simklIds);
        sb2.append(", runtime=");
        sb2.append(num2);
        sb2.append(", poster=");
        return a0.c.p(sb2, str2, ")");
    }

    public /* synthetic */ SimklMovieRef(String str, Integer num, SimklIds simklIds, Integer num2, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num, simklIds, (i10 & 8) != 0 ? null : num2, (i10 & 16) != 0 ? null : str2);
    }
}
