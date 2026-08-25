package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J>\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0007HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/api/SimklIds;", "", "simkl", "", "tmdb", "", "imdb", "", "tvdb", "<init>", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getSimkl", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTmdb", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getImdb", "()Ljava/lang/String;", "getTvdb", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/SimklIds;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklIds {
    public static final int $stable = 0;

    @SerializedName("imdb")
    private final String imdb;

    @SerializedName("simkl")
    private final Long simkl;

    @SerializedName("tmdb")
    private final Integer tmdb;

    @SerializedName("tvdb")
    private final String tvdb;

    public SimklIds() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ SimklIds copy$default(SimklIds simklIds, Long l10, Integer num, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            l10 = simklIds.simkl;
        }
        if ((i10 & 2) != 0) {
            num = simklIds.tmdb;
        }
        if ((i10 & 4) != 0) {
            str = simklIds.imdb;
        }
        if ((i10 & 8) != 0) {
            str2 = simklIds.tvdb;
        }
        return simklIds.copy(l10, num, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Long getSimkl() {
        return this.simkl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTmdb() {
        return this.tmdb;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getImdb() {
        return this.imdb;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTvdb() {
        return this.tvdb;
    }

    public final SimklIds copy(Long simkl, Integer tmdb, String imdb, String tvdb) {
        return new SimklIds(simkl, tmdb, imdb, tvdb);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklIds)) {
            return false;
        }
        SimklIds simklIds = (SimklIds) other;
        return p.a(this.simkl, simklIds.simkl) && p.a(this.tmdb, simklIds.tmdb) && p.a(this.imdb, simklIds.imdb) && p.a(this.tvdb, simklIds.tvdb);
    }

    public final String getImdb() {
        return this.imdb;
    }

    public final Long getSimkl() {
        return this.simkl;
    }

    public final Integer getTmdb() {
        return this.tmdb;
    }

    public final String getTvdb() {
        return this.tvdb;
    }

    public int hashCode() {
        Long l10 = this.simkl;
        int iHashCode = (l10 == null ? 0 : l10.hashCode()) * 31;
        Integer num = this.tmdb;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.imdb;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.tvdb;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        Long l10 = this.simkl;
        Integer num = this.tmdb;
        String str = this.imdb;
        String str2 = this.tvdb;
        StringBuilder sb2 = new StringBuilder("SimklIds(simkl=");
        sb2.append(l10);
        sb2.append(", tmdb=");
        sb2.append(num);
        sb2.append(", imdb=");
        return a2.n(sb2, str, ", tvdb=", str2, ")");
    }

    public SimklIds(Long l10, Integer num, String str, String str2) {
        this.simkl = l10;
        this.tmdb = num;
        this.imdb = str;
        this.tvdb = str2;
    }

    public /* synthetic */ SimklIds(Long l10, Integer num, String str, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : l10, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : str, (i10 & 8) != 0 ? null : str2);
    }
}
