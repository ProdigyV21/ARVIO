package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b*\b\u0087\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0006HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010+\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010,\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010-\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0096\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00102J\u0014\u00103\u001a\u00020\r2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u00020\u0006HÖ\u0081\u0004J\n\u00106\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001d\u0010\u001bR\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001e\u0010\u001bR\u001a\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014¨\u00067"}, d2 = {"Lcom/arflix/tv/data/api/WatchedEpisodeRecord;", "", "userId", "", "profileId", "showTmdbId", "", "season", "episode", "traktEpisodeId", "tmdbEpisodeId", "showTraktId", "watched", "", "watchedAt", "source", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getUserId", "()Ljava/lang/String;", "getProfileId", "getShowTmdbId", "()I", "getSeason", "getEpisode", "getTraktEpisodeId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTmdbEpisodeId", "getShowTraktId", "getWatched", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getWatchedAt", "getSource", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/WatchedEpisodeRecord;", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class WatchedEpisodeRecord {
    public static final int $stable = 0;
    private final int episode;

    @SerializedName("profile_id")
    private final String profileId;
    private final int season;

    @SerializedName("tmdb_id")
    private final int showTmdbId;

    @SerializedName("show_trakt_id")
    private final Integer showTraktId;
    private final String source;

    @SerializedName("tmdb_episode_id")
    private final Integer tmdbEpisodeId;

    @SerializedName("trakt_episode_id")
    private final Integer traktEpisodeId;

    @SerializedName("updated_at")
    private final String updatedAt;

    @SerializedName("user_id")
    private final String userId;

    @SerializedName("watched")
    private final Boolean watched;

    @SerializedName("watched_at")
    private final String watchedAt;

    public WatchedEpisodeRecord(String str, String str2, int i10, int i11, int i12, Integer num, Integer num2, Integer num3, Boolean bool, String str3, String str4, String str5) {
        this.userId = str;
        this.profileId = str2;
        this.showTmdbId = i10;
        this.season = i11;
        this.episode = i12;
        this.traktEpisodeId = num;
        this.tmdbEpisodeId = num2;
        this.showTraktId = num3;
        this.watched = bool;
        this.watchedAt = str3;
        this.source = str4;
        this.updatedAt = str5;
    }

    public static /* synthetic */ WatchedEpisodeRecord copy$default(WatchedEpisodeRecord watchedEpisodeRecord, String str, String str2, int i10, int i11, int i12, Integer num, Integer num2, Integer num3, Boolean bool, String str3, String str4, String str5, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            str = watchedEpisodeRecord.userId;
        }
        if ((i13 & 2) != 0) {
            str2 = watchedEpisodeRecord.profileId;
        }
        if ((i13 & 4) != 0) {
            i10 = watchedEpisodeRecord.showTmdbId;
        }
        if ((i13 & 8) != 0) {
            i11 = watchedEpisodeRecord.season;
        }
        if ((i13 & 16) != 0) {
            i12 = watchedEpisodeRecord.episode;
        }
        if ((i13 & 32) != 0) {
            num = watchedEpisodeRecord.traktEpisodeId;
        }
        if ((i13 & 64) != 0) {
            num2 = watchedEpisodeRecord.tmdbEpisodeId;
        }
        if ((i13 & 128) != 0) {
            num3 = watchedEpisodeRecord.showTraktId;
        }
        if ((i13 & 256) != 0) {
            bool = watchedEpisodeRecord.watched;
        }
        if ((i13 & 512) != 0) {
            str3 = watchedEpisodeRecord.watchedAt;
        }
        if ((i13 & 1024) != 0) {
            str4 = watchedEpisodeRecord.source;
        }
        if ((i13 & 2048) != 0) {
            str5 = watchedEpisodeRecord.updatedAt;
        }
        String str6 = str4;
        String str7 = str5;
        Boolean bool2 = bool;
        String str8 = str3;
        Integer num4 = num2;
        Integer num5 = num3;
        int i14 = i12;
        Integer num6 = num;
        return watchedEpisodeRecord.copy(str, str2, i10, i11, i14, num6, num4, num5, bool2, str8, str6, str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getWatchedAt() {
        return this.watchedAt;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProfileId() {
        return this.profileId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getShowTmdbId() {
        return this.showTmdbId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getTraktEpisodeId() {
        return this.traktEpisodeId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getTmdbEpisodeId() {
        return this.tmdbEpisodeId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Boolean getWatched() {
        return this.watched;
    }

    public final WatchedEpisodeRecord copy(String userId, String profileId, int showTmdbId, int season, int episode, Integer traktEpisodeId, Integer tmdbEpisodeId, Integer showTraktId, Boolean watched, String watchedAt, String source, String updatedAt) {
        return new WatchedEpisodeRecord(userId, profileId, showTmdbId, season, episode, traktEpisodeId, tmdbEpisodeId, showTraktId, watched, watchedAt, source, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchedEpisodeRecord)) {
            return false;
        }
        WatchedEpisodeRecord watchedEpisodeRecord = (WatchedEpisodeRecord) other;
        return p.a(this.userId, watchedEpisodeRecord.userId) && p.a(this.profileId, watchedEpisodeRecord.profileId) && this.showTmdbId == watchedEpisodeRecord.showTmdbId && this.season == watchedEpisodeRecord.season && this.episode == watchedEpisodeRecord.episode && p.a(this.traktEpisodeId, watchedEpisodeRecord.traktEpisodeId) && p.a(this.tmdbEpisodeId, watchedEpisodeRecord.tmdbEpisodeId) && p.a(this.showTraktId, watchedEpisodeRecord.showTraktId) && p.a(this.watched, watchedEpisodeRecord.watched) && p.a(this.watchedAt, watchedEpisodeRecord.watchedAt) && p.a(this.source, watchedEpisodeRecord.source) && p.a(this.updatedAt, watchedEpisodeRecord.updatedAt);
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final int getSeason() {
        return this.season;
    }

    public final int getShowTmdbId() {
        return this.showTmdbId;
    }

    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    public final String getSource() {
        return this.source;
    }

    public final Integer getTmdbEpisodeId() {
        return this.tmdbEpisodeId;
    }

    public final Integer getTraktEpisodeId() {
        return this.traktEpisodeId;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final Boolean getWatched() {
        return this.watched;
    }

    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public int hashCode() {
        int iHashCode = this.userId.hashCode() * 31;
        String str = this.profileId;
        int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.showTmdbId) * 31) + this.season) * 31) + this.episode) * 31;
        Integer num = this.traktEpisodeId;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.tmdbEpisodeId;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.showTraktId;
        int iHashCode5 = (iHashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Boolean bool = this.watched;
        int iHashCode6 = (iHashCode5 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str2 = this.watchedAt;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.source;
        int iHashCode8 = (iHashCode7 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.updatedAt;
        return iHashCode8 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        String str = this.userId;
        String str2 = this.profileId;
        int i10 = this.showTmdbId;
        int i11 = this.season;
        int i12 = this.episode;
        Integer num = this.traktEpisodeId;
        Integer num2 = this.tmdbEpisodeId;
        Integer num3 = this.showTraktId;
        Boolean bool = this.watched;
        String str3 = this.watchedAt;
        String str4 = this.source;
        String str5 = this.updatedAt;
        StringBuilder sbR = a2.r("WatchedEpisodeRecord(userId=", str, ", profileId=", str2, ", showTmdbId=");
        a2.y(sbR, i10, ", season=", i11, ", episode=");
        sbR.append(i12);
        sbR.append(", traktEpisodeId=");
        sbR.append(num);
        sbR.append(", tmdbEpisodeId=");
        a2.z(sbR, num2, ", showTraktId=", num3, ", watched=");
        sbR.append(bool);
        sbR.append(", watchedAt=");
        sbR.append(str3);
        sbR.append(", source=");
        return a2.n(sbR, str4, ", updatedAt=", str5, ")");
    }

    public /* synthetic */ WatchedEpisodeRecord(String str, String str2, int i10, int i11, int i12, Integer num, Integer num2, Integer num3, Boolean bool, String str3, String str4, String str5, int i13, kotlin.jvm.internal.h hVar) {
        this(str, (i13 & 2) != 0 ? null : str2, i10, i11, i12, (i13 & 32) != 0 ? null : num, (i13 & 64) != 0 ? null : num2, (i13 & 128) != 0 ? null : num3, (i13 & 256) != 0 ? Boolean.TRUE : bool, (i13 & 512) != 0 ? null : str3, (i13 & 1024) != 0 ? null : str4, (i13 & 2048) != 0 ? null : str5);
    }
}
