package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J>\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/SimklNextToWatchInfo;", "", LinkHeader.Parameters.Title, "", "season", "", "episode", "date", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getDate", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/SimklNextToWatchInfo;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklNextToWatchInfo {
    public static final int $stable = 0;

    @SerializedName("date")
    private final String date;

    @SerializedName("episode")
    private final Integer episode;

    @SerializedName("season")
    private final Integer season;

    @SerializedName(LinkHeader.Parameters.Title)
    private final String title;

    public SimklNextToWatchInfo() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ SimklNextToWatchInfo copy$default(SimklNextToWatchInfo simklNextToWatchInfo, String str, Integer num, Integer num2, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklNextToWatchInfo.title;
        }
        if ((i10 & 2) != 0) {
            num = simklNextToWatchInfo.season;
        }
        if ((i10 & 4) != 0) {
            num2 = simklNextToWatchInfo.episode;
        }
        if ((i10 & 8) != 0) {
            str2 = simklNextToWatchInfo.date;
        }
        return simklNextToWatchInfo.copy(str, num, num2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    public final SimklNextToWatchInfo copy(String title, Integer season, Integer episode, String date) {
        return new SimklNextToWatchInfo(title, season, episode, date);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklNextToWatchInfo)) {
            return false;
        }
        SimklNextToWatchInfo simklNextToWatchInfo = (SimklNextToWatchInfo) other;
        return p.a(this.title, simklNextToWatchInfo.title) && p.a(this.season, simklNextToWatchInfo.season) && p.a(this.episode, simklNextToWatchInfo.episode) && p.a(this.date, simklNextToWatchInfo.date);
    }

    public final String getDate() {
        return this.date;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String str = this.title;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.season;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.date;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SimklNextToWatchInfo(title=" + this.title + ", season=" + this.season + ", episode=" + this.episode + ", date=" + this.date + ")";
    }

    public SimklNextToWatchInfo(String str, Integer num, Integer num2, String str2) {
        this.title = str;
        this.season = num;
        this.episode = num2;
        this.date = str2;
    }

    public /* synthetic */ SimklNextToWatchInfo(String str, Integer num, Integer num2, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : num2, (i10 & 8) != 0 ? null : str2);
    }
}
