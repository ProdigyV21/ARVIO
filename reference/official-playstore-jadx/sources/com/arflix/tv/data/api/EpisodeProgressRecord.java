package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b5\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B»\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001fJ\t\u00107\u001a\u00020\fHÆ\u0003J\t\u00108\u001a\u00020\u000eHÆ\u0003J\t\u00109\u001a\u00020\u000eHÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÎ\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010BJ\u0014\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010F\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010G\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010 \u001a\u0004\b!\u0010\u001fR\u001a\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\"\u0010\u001fR\u001a\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b#\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0016\u0010\u000f\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001aR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001aR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001a¨\u0006H"}, d2 = {"Lcom/arflix/tv/data/api/EpisodeProgressRecord;", "", "userId", "", "tmdbId", "", "mediaType", "season", "episode", "traktId", "showTraktId", "progress", "", "positionSeconds", "", "durationSeconds", "pausedAt", "lastUpdatedAt", "source", LinkHeader.Parameters.Title, "episodeTitle", "backdropPath", "posterPath", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getUserId", "()Ljava/lang/String;", "getTmdbId", "()I", "getMediaType", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getTraktId", "getShowTraktId", "getProgress", "()F", "getPositionSeconds", "()J", "getDurationSeconds", "getPausedAt", "getLastUpdatedAt", "getSource", "getTitle", "getEpisodeTitle", "getBackdropPath", "getPosterPath", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/EpisodeProgressRecord;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeProgressRecord {
    public static final int $stable = 0;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("duration_seconds")
    private final long durationSeconds;
    private final Integer episode;

    @SerializedName("episode_title")
    private final String episodeTitle;

    @SerializedName("last_updated_at")
    private final String lastUpdatedAt;

    @SerializedName("media_type")
    private final String mediaType;

    @SerializedName("paused_at")
    private final String pausedAt;

    @SerializedName("position_seconds")
    private final long positionSeconds;

    @SerializedName("poster_path")
    private final String posterPath;
    private final float progress;
    private final Integer season;

    @SerializedName("show_trakt_id")
    private final Integer showTraktId;
    private final String source;
    private final String title;

    @SerializedName("tmdb_id")
    private final int tmdbId;

    @SerializedName("trakt_id")
    private final Integer traktId;

    @SerializedName("user_id")
    private final String userId;

    public EpisodeProgressRecord(String str, int i10, String str2, Integer num, Integer num2, Integer num3, Integer num4, float f10, long j10, long j11, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.userId = str;
        this.tmdbId = i10;
        this.mediaType = str2;
        this.season = num;
        this.episode = num2;
        this.traktId = num3;
        this.showTraktId = num4;
        this.progress = f10;
        this.positionSeconds = j10;
        this.durationSeconds = j11;
        this.pausedAt = str3;
        this.lastUpdatedAt = str4;
        this.source = str5;
        this.title = str6;
        this.episodeTitle = str7;
        this.backdropPath = str8;
        this.posterPath = str9;
    }

    public static /* synthetic */ EpisodeProgressRecord copy$default(EpisodeProgressRecord episodeProgressRecord, String str, int i10, String str2, Integer num, Integer num2, Integer num3, Integer num4, float f10, long j10, long j11, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i11, Object obj) {
        String str10;
        String str11;
        String str12 = (i11 & 1) != 0 ? episodeProgressRecord.userId : str;
        int i12 = (i11 & 2) != 0 ? episodeProgressRecord.tmdbId : i10;
        String str13 = (i11 & 4) != 0 ? episodeProgressRecord.mediaType : str2;
        Integer num5 = (i11 & 8) != 0 ? episodeProgressRecord.season : num;
        Integer num6 = (i11 & 16) != 0 ? episodeProgressRecord.episode : num2;
        Integer num7 = (i11 & 32) != 0 ? episodeProgressRecord.traktId : num3;
        Integer num8 = (i11 & 64) != 0 ? episodeProgressRecord.showTraktId : num4;
        float f11 = (i11 & 128) != 0 ? episodeProgressRecord.progress : f10;
        long j12 = (i11 & 256) != 0 ? episodeProgressRecord.positionSeconds : j10;
        long j13 = (i11 & 512) != 0 ? episodeProgressRecord.durationSeconds : j11;
        String str14 = (i11 & 1024) != 0 ? episodeProgressRecord.pausedAt : str3;
        String str15 = (i11 & 2048) != 0 ? episodeProgressRecord.lastUpdatedAt : str4;
        String str16 = str12;
        String str17 = (i11 & 4096) != 0 ? episodeProgressRecord.source : str5;
        String str18 = (i11 & 8192) != 0 ? episodeProgressRecord.title : str6;
        String str19 = (i11 & 16384) != 0 ? episodeProgressRecord.episodeTitle : str7;
        String str20 = (i11 & 32768) != 0 ? episodeProgressRecord.backdropPath : str8;
        if ((i11 & 65536) != 0) {
            str11 = str20;
            str10 = episodeProgressRecord.posterPath;
        } else {
            str10 = str9;
            str11 = str20;
        }
        return episodeProgressRecord.copy(str16, i12, str13, num5, num6, num7, num8, f11, j12, j13, str14, str15, str17, str18, str19, str11, str10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getDurationSeconds() {
        return this.durationSeconds;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPausedAt() {
        return this.pausedAt;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getEpisodeTitle() {
        return this.episodeTitle;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getTmdbId() {
        return this.tmdbId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getTraktId() {
        return this.traktId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getPositionSeconds() {
        return this.positionSeconds;
    }

    public final EpisodeProgressRecord copy(String userId, int tmdbId, String mediaType, Integer season, Integer episode, Integer traktId, Integer showTraktId, float progress, long positionSeconds, long durationSeconds, String pausedAt, String lastUpdatedAt, String source, String title, String episodeTitle, String backdropPath, String posterPath) {
        return new EpisodeProgressRecord(userId, tmdbId, mediaType, season, episode, traktId, showTraktId, progress, positionSeconds, durationSeconds, pausedAt, lastUpdatedAt, source, title, episodeTitle, backdropPath, posterPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeProgressRecord)) {
            return false;
        }
        EpisodeProgressRecord episodeProgressRecord = (EpisodeProgressRecord) other;
        return p.a(this.userId, episodeProgressRecord.userId) && this.tmdbId == episodeProgressRecord.tmdbId && p.a(this.mediaType, episodeProgressRecord.mediaType) && p.a(this.season, episodeProgressRecord.season) && p.a(this.episode, episodeProgressRecord.episode) && p.a(this.traktId, episodeProgressRecord.traktId) && p.a(this.showTraktId, episodeProgressRecord.showTraktId) && Float.compare(this.progress, episodeProgressRecord.progress) == 0 && this.positionSeconds == episodeProgressRecord.positionSeconds && this.durationSeconds == episodeProgressRecord.durationSeconds && p.a(this.pausedAt, episodeProgressRecord.pausedAt) && p.a(this.lastUpdatedAt, episodeProgressRecord.lastUpdatedAt) && p.a(this.source, episodeProgressRecord.source) && p.a(this.title, episodeProgressRecord.title) && p.a(this.episodeTitle, episodeProgressRecord.episodeTitle) && p.a(this.backdropPath, episodeProgressRecord.backdropPath) && p.a(this.posterPath, episodeProgressRecord.posterPath);
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final long getDurationSeconds() {
        return this.durationSeconds;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getEpisodeTitle() {
        return this.episodeTitle;
    }

    public final String getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getPausedAt() {
        return this.pausedAt;
    }

    public final long getPositionSeconds() {
        return this.positionSeconds;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    public final String getSource() {
        return this.source;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTmdbId() {
        return this.tmdbId;
    }

    public final Integer getTraktId() {
        return this.traktId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(((this.userId.hashCode() * 31) + this.tmdbId) * 31, 31, this.mediaType);
        Integer num = this.season;
        int iHashCode = (iC + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.traktId;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.showTraktId;
        int iB = a0.c.b(this.progress, (iHashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31, 31);
        long j10 = this.positionSeconds;
        int i10 = (iB + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.durationSeconds;
        int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
        String str = this.pausedAt;
        int iHashCode4 = (i11 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.lastUpdatedAt;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.source;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.title;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.episodeTitle;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.backdropPath;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.posterPath;
        return iHashCode9 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        String str = this.userId;
        int i10 = this.tmdbId;
        String str2 = this.mediaType;
        Integer num = this.season;
        Integer num2 = this.episode;
        Integer num3 = this.traktId;
        Integer num4 = this.showTraktId;
        float f10 = this.progress;
        long j10 = this.positionSeconds;
        long j11 = this.durationSeconds;
        String str3 = this.pausedAt;
        String str4 = this.lastUpdatedAt;
        String str5 = this.source;
        String str6 = this.title;
        String str7 = this.episodeTitle;
        String str8 = this.backdropPath;
        String str9 = this.posterPath;
        StringBuilder sbQ = a2.q("EpisodeProgressRecord(userId=", str, ", tmdbId=", i10, ", mediaType=");
        sbQ.append(str2);
        sbQ.append(", season=");
        sbQ.append(num);
        sbQ.append(", episode=");
        a2.z(sbQ, num2, ", traktId=", num3, ", showTraktId=");
        sbQ.append(num4);
        sbQ.append(", progress=");
        sbQ.append(f10);
        sbQ.append(", positionSeconds=");
        sbQ.append(j10);
        androidx.compose.foundation.c.z(sbQ, ", durationSeconds=", j11, ", pausedAt=");
        y.a.i(sbQ, str3, ", lastUpdatedAt=", str4, ", source=");
        y.a.i(sbQ, str5, ", title=", str6, ", episodeTitle=");
        y.a.i(sbQ, str7, ", backdropPath=", str8, ", posterPath=");
        return a0.c.p(sbQ, str9, ")");
    }

    public /* synthetic */ EpisodeProgressRecord(String str, int i10, String str2, Integer num, Integer num2, Integer num3, Integer num4, float f10, long j10, long j11, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i11, kotlin.jvm.internal.h hVar) {
        this(str, i10, str2, (i11 & 8) != 0 ? null : num, (i11 & 16) != 0 ? null : num2, (i11 & 32) != 0 ? null : num3, (i11 & 64) != 0 ? null : num4, f10, j10, j11, (i11 & 1024) != 0 ? null : str3, (i11 & 2048) != 0 ? null : str4, (i11 & 4096) != 0 ? null : str5, (i11 & 8192) != 0 ? null : str6, (i11 & 16384) != 0 ? null : str7, (32768 & i11) != 0 ? null : str8, (i11 & 65536) != 0 ? null : str9);
    }
}
