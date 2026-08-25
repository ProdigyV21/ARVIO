package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/MdbLastActivities;", "", "watchlistedAt", "", "watchedAt", "seasonWatchedAt", "episodeWatchedAt", "pausedAt", "episodePausedAt", "ratedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getWatchlistedAt", "()Ljava/lang/String;", "getWatchedAt", "getSeasonWatchedAt", "getEpisodeWatchedAt", "getPausedAt", "getEpisodePausedAt", "getRatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbLastActivities {
    public static final int $stable = 0;

    @SerializedName("episode_paused_at")
    private final String episodePausedAt;

    @SerializedName("episode_watched_at")
    private final String episodeWatchedAt;

    @SerializedName("paused_at")
    private final String pausedAt;

    @SerializedName("rated_at")
    private final String ratedAt;

    @SerializedName("season_watched_at")
    private final String seasonWatchedAt;

    @SerializedName("watched_at")
    private final String watchedAt;

    @SerializedName("watchlisted_at")
    private final String watchlistedAt;

    public MdbLastActivities() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ MdbLastActivities copy$default(MdbLastActivities mdbLastActivities, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mdbLastActivities.watchlistedAt;
        }
        if ((i10 & 2) != 0) {
            str2 = mdbLastActivities.watchedAt;
        }
        if ((i10 & 4) != 0) {
            str3 = mdbLastActivities.seasonWatchedAt;
        }
        if ((i10 & 8) != 0) {
            str4 = mdbLastActivities.episodeWatchedAt;
        }
        if ((i10 & 16) != 0) {
            str5 = mdbLastActivities.pausedAt;
        }
        if ((i10 & 32) != 0) {
            str6 = mdbLastActivities.episodePausedAt;
        }
        if ((i10 & 64) != 0) {
            str7 = mdbLastActivities.ratedAt;
        }
        String str8 = str6;
        String str9 = str7;
        String str10 = str5;
        String str11 = str3;
        return mdbLastActivities.copy(str, str2, str11, str4, str10, str8, str9);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getWatchlistedAt() {
        return this.watchlistedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getWatchedAt() {
        return this.watchedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSeasonWatchedAt() {
        return this.seasonWatchedAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getEpisodeWatchedAt() {
        return this.episodeWatchedAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPausedAt() {
        return this.pausedAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getEpisodePausedAt() {
        return this.episodePausedAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getRatedAt() {
        return this.ratedAt;
    }

    public final MdbLastActivities copy(String watchlistedAt, String watchedAt, String seasonWatchedAt, String episodeWatchedAt, String pausedAt, String episodePausedAt, String ratedAt) {
        return new MdbLastActivities(watchlistedAt, watchedAt, seasonWatchedAt, episodeWatchedAt, pausedAt, episodePausedAt, ratedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbLastActivities)) {
            return false;
        }
        MdbLastActivities mdbLastActivities = (MdbLastActivities) other;
        return p.a(this.watchlistedAt, mdbLastActivities.watchlistedAt) && p.a(this.watchedAt, mdbLastActivities.watchedAt) && p.a(this.seasonWatchedAt, mdbLastActivities.seasonWatchedAt) && p.a(this.episodeWatchedAt, mdbLastActivities.episodeWatchedAt) && p.a(this.pausedAt, mdbLastActivities.pausedAt) && p.a(this.episodePausedAt, mdbLastActivities.episodePausedAt) && p.a(this.ratedAt, mdbLastActivities.ratedAt);
    }

    public final String getEpisodePausedAt() {
        return this.episodePausedAt;
    }

    public final String getEpisodeWatchedAt() {
        return this.episodeWatchedAt;
    }

    public final String getPausedAt() {
        return this.pausedAt;
    }

    public final String getRatedAt() {
        return this.ratedAt;
    }

    public final String getSeasonWatchedAt() {
        return this.seasonWatchedAt;
    }

    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public final String getWatchlistedAt() {
        return this.watchlistedAt;
    }

    public int hashCode() {
        String str = this.watchlistedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.watchedAt;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.seasonWatchedAt;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.episodeWatchedAt;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.pausedAt;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.episodePausedAt;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.ratedAt;
        return iHashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        String str = this.watchlistedAt;
        String str2 = this.watchedAt;
        String str3 = this.seasonWatchedAt;
        String str4 = this.episodeWatchedAt;
        String str5 = this.pausedAt;
        String str6 = this.episodePausedAt;
        String str7 = this.ratedAt;
        StringBuilder sbR = a2.r("MdbLastActivities(watchlistedAt=", str, ", watchedAt=", str2, ", seasonWatchedAt=");
        y.a.i(sbR, str3, ", episodeWatchedAt=", str4, ", pausedAt=");
        y.a.i(sbR, str5, ", episodePausedAt=", str6, ", ratedAt=");
        return a0.c.p(sbR, str7, ")");
    }

    public MdbLastActivities(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.watchlistedAt = str;
        this.watchedAt = str2;
        this.seasonWatchedAt = str3;
        this.episodeWatchedAt = str4;
        this.pausedAt = str5;
        this.episodePausedAt = str6;
        this.ratedAt = str7;
    }

    public /* synthetic */ MdbLastActivities(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : str6, (i10 & 64) != 0 ? null : str7);
    }
}
