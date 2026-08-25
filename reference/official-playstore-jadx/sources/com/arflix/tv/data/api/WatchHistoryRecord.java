package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\bF\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0093\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\u0010\u0010A\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010&J\u0010\u0010B\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010&J\u0010\u0010C\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010&J\u0010\u0010D\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010&J\u0010\u0010E\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010&J\u0010\u0010F\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010&J\t\u0010G\u001a\u00020\u000fHÆ\u0003J\t\u0010H\u001a\u00020\u0011HÆ\u0003J\t\u0010I\u001a\u00020\u0011HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¤\u0002\u0010U\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010VJ\u0014\u0010W\u001a\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010Z\u001a\u00020\bHÖ\u0081\u0004J\n\u0010[\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R\u001a\u0010\t\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b(\u0010&R\u0015\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010'\u001a\u0004\b)\u0010&R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010'\u001a\u0004\b*\u0010&R\u001a\u0010\f\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b+\u0010&R\u001a\u0010\r\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b,\u0010&R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0016\u0010\u0012\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00100R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010!R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010!R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010!R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010!R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010!R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010!R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010!R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010!R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010!R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010!R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010!¨\u0006\\"}, d2 = {"Lcom/arflix/tv/data/api/WatchHistoryRecord;", "", TtmlNode.ATTR_ID, "", "userId", "profileId", "mediaType", "showTmdbId", "", "showTraktId", "season", "episode", "traktEpisodeId", "tmdbEpisodeId", "progress", "", "positionSeconds", "", "durationSeconds", "pausedAt", "updatedAt", "source", LinkHeader.Parameters.Title, "episodeTitle", "backdropPath", "posterPath", "streamKey", "streamAddonId", "streamTitle", "createdAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getUserId", "getProfileId", "getMediaType", "getShowTmdbId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowTraktId", "getSeason", "getEpisode", "getTraktEpisodeId", "getTmdbEpisodeId", "getProgress", "()F", "getPositionSeconds", "()J", "getDurationSeconds", "getPausedAt", "getUpdatedAt", "getSource", "getTitle", "getEpisodeTitle", "getBackdropPath", "getPosterPath", "getStreamKey", "getStreamAddonId", "getStreamTitle", "getCreatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/WatchHistoryRecord;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class WatchHistoryRecord {
    public static final int $stable = 0;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("created_at")
    private final String createdAt;

    @SerializedName("duration_seconds")
    private final long durationSeconds;
    private final Integer episode;

    @SerializedName("episode_title")
    private final String episodeTitle;
    private final String id;

    @SerializedName("media_type")
    private final String mediaType;

    @SerializedName("paused_at")
    private final String pausedAt;

    @SerializedName("position_seconds")
    private final long positionSeconds;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("profile_id")
    private final String profileId;
    private final float progress;
    private final Integer season;

    @SerializedName("show_tmdb_id")
    private final Integer showTmdbId;

    @SerializedName("show_trakt_id")
    private final Integer showTraktId;
    private final String source;

    @SerializedName("stream_addon_id")
    private final String streamAddonId;

    @SerializedName("stream_key")
    private final String streamKey;

    @SerializedName("stream_title")
    private final String streamTitle;
    private final String title;

    @SerializedName("tmdb_episode_id")
    private final Integer tmdbEpisodeId;

    @SerializedName("trakt_episode_id")
    private final Integer traktEpisodeId;

    @SerializedName("updated_at")
    private final String updatedAt;

    @SerializedName("user_id")
    private final String userId;

    public WatchHistoryRecord(String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, float f10, long j10, long j11, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
        this.id = str;
        this.userId = str2;
        this.profileId = str3;
        this.mediaType = str4;
        this.showTmdbId = num;
        this.showTraktId = num2;
        this.season = num3;
        this.episode = num4;
        this.traktEpisodeId = num5;
        this.tmdbEpisodeId = num6;
        this.progress = f10;
        this.positionSeconds = j10;
        this.durationSeconds = j11;
        this.pausedAt = str5;
        this.updatedAt = str6;
        this.source = str7;
        this.title = str8;
        this.episodeTitle = str9;
        this.backdropPath = str10;
        this.posterPath = str11;
        this.streamKey = str12;
        this.streamAddonId = str13;
        this.streamTitle = str14;
        this.createdAt = str15;
    }

    public static /* synthetic */ WatchHistoryRecord copy$default(WatchHistoryRecord watchHistoryRecord, String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, float f10, long j10, long j11, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, int i10, Object obj) {
        String str16;
        String str17;
        String str18 = (i10 & 1) != 0 ? watchHistoryRecord.id : str;
        String str19 = (i10 & 2) != 0 ? watchHistoryRecord.userId : str2;
        String str20 = (i10 & 4) != 0 ? watchHistoryRecord.profileId : str3;
        String str21 = (i10 & 8) != 0 ? watchHistoryRecord.mediaType : str4;
        Integer num7 = (i10 & 16) != 0 ? watchHistoryRecord.showTmdbId : num;
        Integer num8 = (i10 & 32) != 0 ? watchHistoryRecord.showTraktId : num2;
        Integer num9 = (i10 & 64) != 0 ? watchHistoryRecord.season : num3;
        Integer num10 = (i10 & 128) != 0 ? watchHistoryRecord.episode : num4;
        Integer num11 = (i10 & 256) != 0 ? watchHistoryRecord.traktEpisodeId : num5;
        Integer num12 = (i10 & 512) != 0 ? watchHistoryRecord.tmdbEpisodeId : num6;
        float f11 = (i10 & 1024) != 0 ? watchHistoryRecord.progress : f10;
        long j12 = (i10 & 2048) != 0 ? watchHistoryRecord.positionSeconds : j10;
        String str22 = str18;
        String str23 = str19;
        long j13 = (i10 & 4096) != 0 ? watchHistoryRecord.durationSeconds : j11;
        String str24 = (i10 & 8192) != 0 ? watchHistoryRecord.pausedAt : str5;
        long j14 = j13;
        String str25 = (i10 & 16384) != 0 ? watchHistoryRecord.updatedAt : str6;
        String str26 = (32768 & i10) != 0 ? watchHistoryRecord.source : str7;
        String str27 = (i10 & 65536) != 0 ? watchHistoryRecord.title : str8;
        String str28 = (i10 & 131072) != 0 ? watchHistoryRecord.episodeTitle : str9;
        String str29 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? watchHistoryRecord.backdropPath : str10;
        String str30 = (i10 & 524288) != 0 ? watchHistoryRecord.posterPath : str11;
        String str31 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? watchHistoryRecord.streamKey : str12;
        String str32 = (i10 & 2097152) != 0 ? watchHistoryRecord.streamAddonId : str13;
        String str33 = (i10 & 4194304) != 0 ? watchHistoryRecord.streamTitle : str14;
        if ((i10 & 8388608) != 0) {
            str17 = str33;
            str16 = watchHistoryRecord.createdAt;
        } else {
            str16 = str15;
            str17 = str33;
        }
        return watchHistoryRecord.copy(str22, str23, str20, str21, num7, num8, num9, num10, num11, num12, f11, j12, j14, str24, str25, str26, str27, str28, str29, str30, str31, str32, str17, str16);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getTmdbEpisodeId() {
        return this.tmdbEpisodeId;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getPositionSeconds() {
        return this.positionSeconds;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getDurationSeconds() {
        return this.durationSeconds;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getPausedAt() {
        return this.pausedAt;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getEpisodeTitle() {
        return this.episodeTitle;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getStreamKey() {
        return this.streamKey;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getStreamAddonId() {
        return this.streamAddonId;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getStreamTitle() {
        return this.streamTitle;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getProfileId() {
        return this.profileId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getShowTmdbId() {
        return this.showTmdbId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getTraktEpisodeId() {
        return this.traktEpisodeId;
    }

    public final WatchHistoryRecord copy(String id, String userId, String profileId, String mediaType, Integer showTmdbId, Integer showTraktId, Integer season, Integer episode, Integer traktEpisodeId, Integer tmdbEpisodeId, float progress, long positionSeconds, long durationSeconds, String pausedAt, String updatedAt, String source, String title, String episodeTitle, String backdropPath, String posterPath, String streamKey, String streamAddonId, String streamTitle, String createdAt) {
        return new WatchHistoryRecord(id, userId, profileId, mediaType, showTmdbId, showTraktId, season, episode, traktEpisodeId, tmdbEpisodeId, progress, positionSeconds, durationSeconds, pausedAt, updatedAt, source, title, episodeTitle, backdropPath, posterPath, streamKey, streamAddonId, streamTitle, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchHistoryRecord)) {
            return false;
        }
        WatchHistoryRecord watchHistoryRecord = (WatchHistoryRecord) other;
        return p.a(this.id, watchHistoryRecord.id) && p.a(this.userId, watchHistoryRecord.userId) && p.a(this.profileId, watchHistoryRecord.profileId) && p.a(this.mediaType, watchHistoryRecord.mediaType) && p.a(this.showTmdbId, watchHistoryRecord.showTmdbId) && p.a(this.showTraktId, watchHistoryRecord.showTraktId) && p.a(this.season, watchHistoryRecord.season) && p.a(this.episode, watchHistoryRecord.episode) && p.a(this.traktEpisodeId, watchHistoryRecord.traktEpisodeId) && p.a(this.tmdbEpisodeId, watchHistoryRecord.tmdbEpisodeId) && Float.compare(this.progress, watchHistoryRecord.progress) == 0 && this.positionSeconds == watchHistoryRecord.positionSeconds && this.durationSeconds == watchHistoryRecord.durationSeconds && p.a(this.pausedAt, watchHistoryRecord.pausedAt) && p.a(this.updatedAt, watchHistoryRecord.updatedAt) && p.a(this.source, watchHistoryRecord.source) && p.a(this.title, watchHistoryRecord.title) && p.a(this.episodeTitle, watchHistoryRecord.episodeTitle) && p.a(this.backdropPath, watchHistoryRecord.backdropPath) && p.a(this.posterPath, watchHistoryRecord.posterPath) && p.a(this.streamKey, watchHistoryRecord.streamKey) && p.a(this.streamAddonId, watchHistoryRecord.streamAddonId) && p.a(this.streamTitle, watchHistoryRecord.streamTitle) && p.a(this.createdAt, watchHistoryRecord.createdAt);
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final String getCreatedAt() {
        return this.createdAt;
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

    public final String getId() {
        return this.id;
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

    public final String getProfileId() {
        return this.profileId;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getShowTmdbId() {
        return this.showTmdbId;
    }

    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    public final String getSource() {
        return this.source;
    }

    public final String getStreamAddonId() {
        return this.streamAddonId;
    }

    public final String getStreamKey() {
        return this.streamKey;
    }

    public final String getStreamTitle() {
        return this.streamTitle;
    }

    public final String getTitle() {
        return this.title;
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

    public int hashCode() {
        String str = this.id;
        int iC = androidx.compose.foundation.c.c((str == null ? 0 : str.hashCode()) * 31, 31, this.userId);
        String str2 = this.profileId;
        int iC2 = androidx.compose.foundation.c.c((iC + (str2 == null ? 0 : str2.hashCode())) * 31, 31, this.mediaType);
        Integer num = this.showTmdbId;
        int iHashCode = (iC2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.showTraktId;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.season;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.episode;
        int iHashCode4 = (iHashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.traktEpisodeId;
        int iHashCode5 = (iHashCode4 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.tmdbEpisodeId;
        int iB = a0.c.b(this.progress, (iHashCode5 + (num6 == null ? 0 : num6.hashCode())) * 31, 31);
        long j10 = this.positionSeconds;
        int i10 = (iB + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.durationSeconds;
        int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
        String str3 = this.pausedAt;
        int iHashCode6 = (i11 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.updatedAt;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.source;
        int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.title;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.episodeTitle;
        int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.backdropPath;
        int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.posterPath;
        int iHashCode12 = (iHashCode11 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.streamKey;
        int iHashCode13 = (iHashCode12 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.streamAddonId;
        int iHashCode14 = (iHashCode13 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.streamTitle;
        int iHashCode15 = (iHashCode14 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.createdAt;
        return iHashCode15 + (str13 != null ? str13.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.userId;
        String str3 = this.profileId;
        String str4 = this.mediaType;
        Integer num = this.showTmdbId;
        Integer num2 = this.showTraktId;
        Integer num3 = this.season;
        Integer num4 = this.episode;
        Integer num5 = this.traktEpisodeId;
        Integer num6 = this.tmdbEpisodeId;
        float f10 = this.progress;
        long j10 = this.positionSeconds;
        long j11 = this.durationSeconds;
        String str5 = this.pausedAt;
        String str6 = this.updatedAt;
        String str7 = this.source;
        String str8 = this.title;
        String str9 = this.episodeTitle;
        String str10 = this.backdropPath;
        String str11 = this.posterPath;
        String str12 = this.streamKey;
        String str13 = this.streamAddonId;
        String str14 = this.streamTitle;
        String str15 = this.createdAt;
        StringBuilder sbR = a2.r("WatchHistoryRecord(id=", str, ", userId=", str2, ", profileId=");
        y.a.i(sbR, str3, ", mediaType=", str4, ", showTmdbId=");
        a2.z(sbR, num, ", showTraktId=", num2, ", season=");
        a2.z(sbR, num3, ", episode=", num4, ", traktEpisodeId=");
        a2.z(sbR, num5, ", tmdbEpisodeId=", num6, ", progress=");
        sbR.append(f10);
        sbR.append(", positionSeconds=");
        sbR.append(j10);
        androidx.compose.foundation.c.z(sbR, ", durationSeconds=", j11, ", pausedAt=");
        y.a.i(sbR, str5, ", updatedAt=", str6, ", source=");
        y.a.i(sbR, str7, ", title=", str8, ", episodeTitle=");
        y.a.i(sbR, str9, ", backdropPath=", str10, ", posterPath=");
        y.a.i(sbR, str11, ", streamKey=", str12, ", streamAddonId=");
        y.a.i(sbR, str13, ", streamTitle=", str14, ", createdAt=");
        return a0.c.p(sbR, str15, ")");
    }

    public /* synthetic */ WatchHistoryRecord(String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, float f10, long j10, long j11, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, str2, (i10 & 4) != 0 ? null : str3, str4, (i10 & 16) != 0 ? null : num, (i10 & 32) != 0 ? null : num2, (i10 & 64) != 0 ? null : num3, (i10 & 128) != 0 ? null : num4, (i10 & 256) != 0 ? null : num5, (i10 & 512) != 0 ? null : num6, f10, j10, j11, (i10 & 8192) != 0 ? null : str5, (i10 & 16384) != 0 ? null : str6, (32768 & i10) != 0 ? null : str7, (65536 & i10) != 0 ? null : str8, (131072 & i10) != 0 ? null : str9, (262144 & i10) != 0 ? null : str10, (524288 & i10) != 0 ? null : str11, (1048576 & i10) != 0 ? null : str12, (2097152 & i10) != 0 ? null : str13, (4194304 & i10) != 0 ? null : str14, (i10 & 8388608) != 0 ? null : str15);
    }
}
