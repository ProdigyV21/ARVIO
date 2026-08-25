package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015JF\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006$"}, d2 = {"Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;", "", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "mediaId", "", "season", "episode", "startPositionMs", "", "<init>", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)V", "getMediaType", "()Lcom/arflix/tv/data/model/MediaType;", "getMediaId", "()I", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getStartPositionMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LauncherContinueWatchingRequest {
    public static final int $stable = 0;
    private final Integer episode;
    private final int mediaId;
    private final MediaType mediaType;
    private final Integer season;
    private final Long startPositionMs;

    public LauncherContinueWatchingRequest(MediaType mediaType, int i10, Integer num, Integer num2, Long l10) {
        this.mediaType = mediaType;
        this.mediaId = i10;
        this.season = num;
        this.episode = num2;
        this.startPositionMs = l10;
    }

    public static /* synthetic */ LauncherContinueWatchingRequest copy$default(LauncherContinueWatchingRequest launcherContinueWatchingRequest, MediaType mediaType, int i10, Integer num, Integer num2, Long l10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            mediaType = launcherContinueWatchingRequest.mediaType;
        }
        if ((i11 & 2) != 0) {
            i10 = launcherContinueWatchingRequest.mediaId;
        }
        if ((i11 & 4) != 0) {
            num = launcherContinueWatchingRequest.season;
        }
        if ((i11 & 8) != 0) {
            num2 = launcherContinueWatchingRequest.episode;
        }
        if ((i11 & 16) != 0) {
            l10 = launcherContinueWatchingRequest.startPositionMs;
        }
        Long l11 = l10;
        Integer num3 = num;
        return launcherContinueWatchingRequest.copy(mediaType, i10, num3, num2, l11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MediaType getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMediaId() {
        return this.mediaId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Long getStartPositionMs() {
        return this.startPositionMs;
    }

    public final LauncherContinueWatchingRequest copy(MediaType mediaType, int mediaId, Integer season, Integer episode, Long startPositionMs) {
        return new LauncherContinueWatchingRequest(mediaType, mediaId, season, episode, startPositionMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LauncherContinueWatchingRequest)) {
            return false;
        }
        LauncherContinueWatchingRequest launcherContinueWatchingRequest = (LauncherContinueWatchingRequest) other;
        return this.mediaType == launcherContinueWatchingRequest.mediaType && this.mediaId == launcherContinueWatchingRequest.mediaId && kotlin.jvm.internal.p.a(this.season, launcherContinueWatchingRequest.season) && kotlin.jvm.internal.p.a(this.episode, launcherContinueWatchingRequest.episode) && kotlin.jvm.internal.p.a(this.startPositionMs, launcherContinueWatchingRequest.startPositionMs);
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final int getMediaId() {
        return this.mediaId;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Long getStartPositionMs() {
        return this.startPositionMs;
    }

    public int hashCode() {
        int iHashCode = ((this.mediaType.hashCode() * 31) + this.mediaId) * 31;
        Integer num = this.season;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l10 = this.startPositionMs;
        return iHashCode3 + (l10 != null ? l10.hashCode() : 0);
    }

    public String toString() {
        MediaType mediaType = this.mediaType;
        int i10 = this.mediaId;
        Integer num = this.season;
        Integer num2 = this.episode;
        Long l10 = this.startPositionMs;
        StringBuilder sb2 = new StringBuilder("LauncherContinueWatchingRequest(mediaType=");
        sb2.append(mediaType);
        sb2.append(", mediaId=");
        sb2.append(i10);
        sb2.append(", season=");
        a2.z(sb2, num, ", episode=", num2, ", startPositionMs=");
        sb2.append(l10);
        sb2.append(")");
        return sb2.toString();
    }

    public /* synthetic */ LauncherContinueWatchingRequest(MediaType mediaType, int i10, Integer num, Integer num2, Long l10, int i11, kotlin.jvm.internal.h hVar) {
        this(mediaType, i10, (i11 & 4) != 0 ? null : num, (i11 & 8) != 0 ? null : num2, (i11 & 16) != 0 ? null : l10);
    }
}
