package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\b\u0087\b\u0018\u00002\u00020\u0001B£\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u001f\u001a\u00020\n\u0012\b\b\u0002\u0010 \u001a\u00020\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\u0003¢\u0006\u0004\b\"\u0010#J\u0012\u0010G\u001a\u00020H2\n\b\u0002\u0010I\u001a\u0004\u0018\u00010JJ\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0007HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\nHÆ\u0003J\t\u0010P\u001a\u00020\nHÆ\u0003J\u0010\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010R\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010/J\u000b\u0010U\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010[\u001a\u00020\u0005HÆ\u0003J\t\u0010\\\u001a\u00020\u0005HÆ\u0003J\t\u0010]\u001a\u00020\u0019HÆ\u0003J\t\u0010^\u001a\u00020\u0005HÆ\u0003J\t\u0010_\u001a\u00020\u0005HÆ\u0003J\t\u0010`\u001a\u00020\u0005HÆ\u0003J\t\u0010a\u001a\u00020\u0005HÆ\u0003J\u0010\u0010b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010BJ\t\u0010c\u001a\u00020\nHÆ\u0003J\t\u0010d\u001a\u00020\u0003HÆ\u0003J\t\u0010e\u001a\u00020\u0003HÆ\u0003J²\u0002\u0010f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u001f\u001a\u00020\n2\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010gJ\u0014\u0010h\u001a\u00020\u00192\b\u0010i\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010j\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010k\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b-\u0010,R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00100\u001a\u0004\b.\u0010/R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00100\u001a\u0004\b1\u0010/R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00100\u001a\u0004\b2\u0010/R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u00100\u001a\u0004\b3\u0010/R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010'R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010'R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010'R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010'R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010'R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010'R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010'R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010'R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010<R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010'R\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010'R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010'R\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b@\u0010'R\u0015\u0010\u001e\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010C\u001a\u0004\bA\u0010BR\u0011\u0010\u001f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\bD\u0010,R\u0011\u0010 \u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bE\u0010%R\u0011\u0010!\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bF\u0010%¨\u0006l"}, d2 = {"Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "progress", "resumePositionSeconds", "", "durationSeconds", "season", "episode", "displaySeason", "displayEpisode", "episodeTitle", "backdropPath", "posterPath", "streamKey", "streamAddonId", "streamTitle", "year", "releaseDate", "isUpNext", "", "overview", "imdbRating", "tmdbRating", "duration", "budget", "updatedAtMs", "totalEpisodes", "watchedEpisodes", "<init>", "(ILjava/lang/String;Lcom/arflix/tv/data/model/MediaType;IJJLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;JII)V", "getId", "()I", "getTitle", "()Ljava/lang/String;", "getMediaType", "()Lcom/arflix/tv/data/model/MediaType;", "getProgress", "getResumePositionSeconds", "()J", "getDurationSeconds", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getDisplaySeason", "getDisplayEpisode", "getEpisodeTitle", "getBackdropPath", "getPosterPath", "getStreamKey", "getStreamAddonId", "getStreamTitle", "getYear", "getReleaseDate", "()Z", "getOverview", "getImdbRating", "getTmdbRating", "getDuration", "getBudget", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getUpdatedAtMs", "getTotalEpisodes", "getWatchedEpisodes", "toMediaItem", "Lcom/arflix/tv/data/model/MediaItem;", "context", "Landroid/content/Context;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "copy", "(ILjava/lang/String;Lcom/arflix/tv/data/model/MediaType;IJJLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;JII)Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ContinueWatchingItem {
    public static final int $stable = 0;
    private final String backdropPath;
    private final Long budget;
    private final Integer displayEpisode;
    private final Integer displaySeason;
    private final String duration;
    private final long durationSeconds;
    private final Integer episode;
    private final String episodeTitle;
    private final int id;
    private final String imdbRating;
    private final boolean isUpNext;
    private final MediaType mediaType;
    private final String overview;
    private final String posterPath;
    private final int progress;
    private final String releaseDate;
    private final long resumePositionSeconds;
    private final Integer season;
    private final String streamAddonId;
    private final String streamKey;
    private final String streamTitle;
    private final String title;
    private final String tmdbRating;
    private final int totalEpisodes;
    private final long updatedAtMs;
    private final int watchedEpisodes;
    private final String year;

    public ContinueWatchingItem(int i10, String str, MediaType mediaType, int i11, long j10, long j11, Integer num, Integer num2, Integer num3, Integer num4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10, String str11, String str12, String str13, Long l10, long j12, int i12, int i13) {
        this.id = i10;
        this.title = str;
        this.mediaType = mediaType;
        this.progress = i11;
        this.resumePositionSeconds = j10;
        this.durationSeconds = j11;
        this.season = num;
        this.episode = num2;
        this.displaySeason = num3;
        this.displayEpisode = num4;
        this.episodeTitle = str2;
        this.backdropPath = str3;
        this.posterPath = str4;
        this.streamKey = str5;
        this.streamAddonId = str6;
        this.streamTitle = str7;
        this.year = str8;
        this.releaseDate = str9;
        this.isUpNext = z;
        this.overview = str10;
        this.imdbRating = str11;
        this.tmdbRating = str12;
        this.duration = str13;
        this.budget = l10;
        this.updatedAtMs = j12;
        this.totalEpisodes = i12;
        this.watchedEpisodes = i13;
    }

    public static /* synthetic */ ContinueWatchingItem copy$default(ContinueWatchingItem continueWatchingItem, int i10, String str, MediaType mediaType, int i11, long j10, long j11, Integer num, Integer num2, Integer num3, Integer num4, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10, String str11, String str12, String str13, Long l10, long j12, int i12, int i13, int i14, Object obj) {
        int i15 = (i14 & 1) != 0 ? continueWatchingItem.id : i10;
        return continueWatchingItem.copy(i15, (i14 & 2) != 0 ? continueWatchingItem.title : str, (i14 & 4) != 0 ? continueWatchingItem.mediaType : mediaType, (i14 & 8) != 0 ? continueWatchingItem.progress : i11, (i14 & 16) != 0 ? continueWatchingItem.resumePositionSeconds : j10, (i14 & 32) != 0 ? continueWatchingItem.durationSeconds : j11, (i14 & 64) != 0 ? continueWatchingItem.season : num, (i14 & 128) != 0 ? continueWatchingItem.episode : num2, (i14 & 256) != 0 ? continueWatchingItem.displaySeason : num3, (i14 & 512) != 0 ? continueWatchingItem.displayEpisode : num4, (i14 & 1024) != 0 ? continueWatchingItem.episodeTitle : str2, (i14 & 2048) != 0 ? continueWatchingItem.backdropPath : str3, (i14 & 4096) != 0 ? continueWatchingItem.posterPath : str4, (i14 & 8192) != 0 ? continueWatchingItem.streamKey : str5, (i14 & 16384) != 0 ? continueWatchingItem.streamAddonId : str6, (i14 & 32768) != 0 ? continueWatchingItem.streamTitle : str7, (i14 & 65536) != 0 ? continueWatchingItem.year : str8, (i14 & 131072) != 0 ? continueWatchingItem.releaseDate : str9, (i14 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? continueWatchingItem.isUpNext : z, (i14 & 524288) != 0 ? continueWatchingItem.overview : str10, (i14 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? continueWatchingItem.imdbRating : str11, (i14 & 2097152) != 0 ? continueWatchingItem.tmdbRating : str12, (i14 & 4194304) != 0 ? continueWatchingItem.duration : str13, (i14 & 8388608) != 0 ? continueWatchingItem.budget : l10, (i14 & 16777216) != 0 ? continueWatchingItem.updatedAtMs : j12, (i14 & 33554432) != 0 ? continueWatchingItem.totalEpisodes : i12, (i14 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? continueWatchingItem.watchedEpisodes : i13);
    }

    public static /* synthetic */ MediaItem toMediaItem$default(ContinueWatchingItem continueWatchingItem, Context context, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            context = null;
        }
        return continueWatchingItem.toMediaItem(context);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getDisplayEpisode() {
        return this.displayEpisode;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getEpisodeTitle() {
        return this.episodeTitle;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getStreamKey() {
        return this.streamKey;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getStreamAddonId() {
        return this.streamAddonId;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getStreamTitle() {
        return this.streamTitle;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getReleaseDate() {
        return this.releaseDate;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getIsUpNext() {
        return this.isUpNext;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getImdbRating() {
        return this.imdbRating;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getTmdbRating() {
        return this.tmdbRating;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final Long getBudget() {
        return this.budget;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final long getUpdatedAtMs() {
        return this.updatedAtMs;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final int getTotalEpisodes() {
        return this.totalEpisodes;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final int getWatchedEpisodes() {
        return this.watchedEpisodes;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MediaType getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getResumePositionSeconds() {
        return this.resumePositionSeconds;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getDurationSeconds() {
        return this.durationSeconds;
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
    public final Integer getDisplaySeason() {
        return this.displaySeason;
    }

    public final ContinueWatchingItem copy(int id, String title, MediaType mediaType, int progress, long resumePositionSeconds, long durationSeconds, Integer season, Integer episode, Integer displaySeason, Integer displayEpisode, String episodeTitle, String backdropPath, String posterPath, String streamKey, String streamAddonId, String streamTitle, String year, String releaseDate, boolean isUpNext, String overview, String imdbRating, String tmdbRating, String duration, Long budget, long updatedAtMs, int totalEpisodes, int watchedEpisodes) {
        return new ContinueWatchingItem(id, title, mediaType, progress, resumePositionSeconds, durationSeconds, season, episode, displaySeason, displayEpisode, episodeTitle, backdropPath, posterPath, streamKey, streamAddonId, streamTitle, year, releaseDate, isUpNext, overview, imdbRating, tmdbRating, duration, budget, updatedAtMs, totalEpisodes, watchedEpisodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContinueWatchingItem)) {
            return false;
        }
        ContinueWatchingItem continueWatchingItem = (ContinueWatchingItem) other;
        return this.id == continueWatchingItem.id && kotlin.jvm.internal.p.a(this.title, continueWatchingItem.title) && this.mediaType == continueWatchingItem.mediaType && this.progress == continueWatchingItem.progress && this.resumePositionSeconds == continueWatchingItem.resumePositionSeconds && this.durationSeconds == continueWatchingItem.durationSeconds && kotlin.jvm.internal.p.a(this.season, continueWatchingItem.season) && kotlin.jvm.internal.p.a(this.episode, continueWatchingItem.episode) && kotlin.jvm.internal.p.a(this.displaySeason, continueWatchingItem.displaySeason) && kotlin.jvm.internal.p.a(this.displayEpisode, continueWatchingItem.displayEpisode) && kotlin.jvm.internal.p.a(this.episodeTitle, continueWatchingItem.episodeTitle) && kotlin.jvm.internal.p.a(this.backdropPath, continueWatchingItem.backdropPath) && kotlin.jvm.internal.p.a(this.posterPath, continueWatchingItem.posterPath) && kotlin.jvm.internal.p.a(this.streamKey, continueWatchingItem.streamKey) && kotlin.jvm.internal.p.a(this.streamAddonId, continueWatchingItem.streamAddonId) && kotlin.jvm.internal.p.a(this.streamTitle, continueWatchingItem.streamTitle) && kotlin.jvm.internal.p.a(this.year, continueWatchingItem.year) && kotlin.jvm.internal.p.a(this.releaseDate, continueWatchingItem.releaseDate) && this.isUpNext == continueWatchingItem.isUpNext && kotlin.jvm.internal.p.a(this.overview, continueWatchingItem.overview) && kotlin.jvm.internal.p.a(this.imdbRating, continueWatchingItem.imdbRating) && kotlin.jvm.internal.p.a(this.tmdbRating, continueWatchingItem.tmdbRating) && kotlin.jvm.internal.p.a(this.duration, continueWatchingItem.duration) && kotlin.jvm.internal.p.a(this.budget, continueWatchingItem.budget) && this.updatedAtMs == continueWatchingItem.updatedAtMs && this.totalEpisodes == continueWatchingItem.totalEpisodes && this.watchedEpisodes == continueWatchingItem.watchedEpisodes;
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final Long getBudget() {
        return this.budget;
    }

    public final Integer getDisplayEpisode() {
        return this.displayEpisode;
    }

    public final Integer getDisplaySeason() {
        return this.displaySeason;
    }

    public final String getDuration() {
        return this.duration;
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

    public final int getId() {
        return this.id;
    }

    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final String getReleaseDate() {
        return this.releaseDate;
    }

    public final long getResumePositionSeconds() {
        return this.resumePositionSeconds;
    }

    public final Integer getSeason() {
        return this.season;
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

    public final String getTmdbRating() {
        return this.tmdbRating;
    }

    public final int getTotalEpisodes() {
        return this.totalEpisodes;
    }

    public final long getUpdatedAtMs() {
        return this.updatedAtMs;
    }

    public final int getWatchedEpisodes() {
        return this.watchedEpisodes;
    }

    public final String getYear() {
        return this.year;
    }

    public int hashCode() {
        int iHashCode = (((this.mediaType.hashCode() + androidx.compose.foundation.c.c(this.id * 31, 31, this.title)) * 31) + this.progress) * 31;
        long j10 = this.resumePositionSeconds;
        int i10 = (iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.durationSeconds;
        int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
        Integer num = this.season;
        int iHashCode2 = (i11 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.displaySeason;
        int iHashCode4 = (iHashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.displayEpisode;
        int iHashCode5 = (iHashCode4 + (num4 == null ? 0 : num4.hashCode())) * 31;
        String str = this.episodeTitle;
        int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.backdropPath;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.posterPath;
        int iHashCode8 = (iHashCode7 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.streamKey;
        int iHashCode9 = (iHashCode8 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.streamAddonId;
        int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.streamTitle;
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((iHashCode10 + (str6 == null ? 0 : str6.hashCode())) * 31, 31, this.year), 31, this.releaseDate) + (this.isUpNext ? 1231 : 1237)) * 31, 31, this.overview), 31, this.imdbRating), 31, this.tmdbRating), 31, this.duration);
        Long l10 = this.budget;
        int iHashCode11 = l10 != null ? l10.hashCode() : 0;
        long j12 = this.updatedAtMs;
        return ((((((iC + iHashCode11) * 31) + ((int) (j12 ^ (j12 >>> 32)))) * 31) + this.totalEpisodes) * 31) + this.watchedEpisodes;
    }

    public final boolean isUpNext() {
        return this.isUpNext;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.data.model.MediaItem toMediaItem(android.content.Context r60) {
        /*
            Method dump skipped, instruction units count: 593
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ContinueWatchingItem.toMediaItem(android.content.Context):com.arflix.tv.data.model.MediaItem");
    }

    public String toString() {
        int i10 = this.id;
        String str = this.title;
        MediaType mediaType = this.mediaType;
        int i11 = this.progress;
        long j10 = this.resumePositionSeconds;
        long j11 = this.durationSeconds;
        Integer num = this.season;
        Integer num2 = this.episode;
        Integer num3 = this.displaySeason;
        Integer num4 = this.displayEpisode;
        String str2 = this.episodeTitle;
        String str3 = this.backdropPath;
        String str4 = this.posterPath;
        String str5 = this.streamKey;
        String str6 = this.streamAddonId;
        String str7 = this.streamTitle;
        String str8 = this.year;
        String str9 = this.releaseDate;
        boolean z = this.isUpNext;
        String str10 = this.overview;
        String str11 = this.imdbRating;
        String str12 = this.tmdbRating;
        String str13 = this.duration;
        Long l10 = this.budget;
        long j12 = this.updatedAtMs;
        int i12 = this.totalEpisodes;
        int i13 = this.watchedEpisodes;
        StringBuilder sbO = a2.o("ContinueWatchingItem(id=", i10, ", title=", str, ", mediaType=");
        sbO.append(mediaType);
        sbO.append(", progress=");
        sbO.append(i11);
        sbO.append(", resumePositionSeconds=");
        sbO.append(j10);
        androidx.compose.foundation.c.z(sbO, ", durationSeconds=", j11, ", season=");
        a2.z(sbO, num, ", episode=", num2, ", displaySeason=");
        a2.z(sbO, num3, ", displayEpisode=", num4, ", episodeTitle=");
        y.a.i(sbO, str2, ", backdropPath=", str3, ", posterPath=");
        y.a.i(sbO, str4, ", streamKey=", str5, ", streamAddonId=");
        y.a.i(sbO, str6, ", streamTitle=", str7, ", year=");
        y.a.i(sbO, str8, ", releaseDate=", str9, ", isUpNext=");
        a2.B(sbO, z, ", overview=", str10, ", imdbRating=");
        y.a.i(sbO, str11, ", tmdbRating=", str12, ", duration=");
        sbO.append(str13);
        sbO.append(", budget=");
        sbO.append(l10);
        sbO.append(", updatedAtMs=");
        sbO.append(j12);
        sbO.append(", totalEpisodes=");
        sbO.append(i12);
        sbO.append(", watchedEpisodes=");
        sbO.append(i13);
        sbO.append(")");
        return sbO.toString();
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ ContinueWatchingItem(int r36, java.lang.String r37, com.arflix.tv.data.model.MediaType r38, int r39, long r40, long r42, java.lang.Integer r44, java.lang.Integer r45, java.lang.Integer r46, java.lang.Integer r47, java.lang.String r48, java.lang.String r49, java.lang.String r50, java.lang.String r51, java.lang.String r52, java.lang.String r53, java.lang.String r54, java.lang.String r55, boolean r56, java.lang.String r57, java.lang.String r58, java.lang.String r59, java.lang.String r60, java.lang.Long r61, long r62, int r64, int r65, int r66, kotlin.jvm.internal.h r67) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ContinueWatchingItem.<init>(int, java.lang.String, com.arflix.tv.data.model.MediaType, int, long, long, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, long, int, int, int, kotlin.jvm.internal.h):void");
    }
}
