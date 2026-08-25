package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000eHÆ\u0003JW\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0014\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020*HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Lcom/arflix/tv/data/api/TraktPlaybackItem;", "", TtmlNode.ATTR_ID, "", "progress", "", "pausedAt", "", LinkHeader.Parameters.Type, "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "episode", "Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "<init>", "(JFLjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktEpisodeInfo;Lcom/arflix/tv/data/api/TraktShowInfo;)V", "getId", "()J", "getProgress", "()F", "getPausedAt", "()Ljava/lang/String;", "getType", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "getEpisode", "()Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktPlaybackItem {
    public static final int $stable = 0;
    private final TraktEpisodeInfo episode;
    private final long id;
    private final TraktMovieInfo movie;

    @SerializedName("paused_at")
    private final String pausedAt;
    private final float progress;
    private final TraktShowInfo show;
    private final String type;

    public TraktPlaybackItem(long j10, float f10, String str, String str2, TraktMovieInfo traktMovieInfo, TraktEpisodeInfo traktEpisodeInfo, TraktShowInfo traktShowInfo) {
        this.id = j10;
        this.progress = f10;
        this.pausedAt = str;
        this.type = str2;
        this.movie = traktMovieInfo;
        this.episode = traktEpisodeInfo;
        this.show = traktShowInfo;
    }

    public static /* synthetic */ TraktPlaybackItem copy$default(TraktPlaybackItem traktPlaybackItem, long j10, float f10, String str, String str2, TraktMovieInfo traktMovieInfo, TraktEpisodeInfo traktEpisodeInfo, TraktShowInfo traktShowInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = traktPlaybackItem.id;
        }
        long j11 = j10;
        if ((i10 & 2) != 0) {
            f10 = traktPlaybackItem.progress;
        }
        float f11 = f10;
        if ((i10 & 4) != 0) {
            str = traktPlaybackItem.pausedAt;
        }
        String str3 = str;
        if ((i10 & 8) != 0) {
            str2 = traktPlaybackItem.type;
        }
        String str4 = str2;
        if ((i10 & 16) != 0) {
            traktMovieInfo = traktPlaybackItem.movie;
        }
        return traktPlaybackItem.copy(j11, f11, str3, str4, traktMovieInfo, (i10 & 32) != 0 ? traktPlaybackItem.episode : traktEpisodeInfo, (i10 & 64) != 0 ? traktPlaybackItem.show : traktShowInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPausedAt() {
        return this.pausedAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TraktEpisodeInfo getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final TraktPlaybackItem copy(long id, float progress, String pausedAt, String type, TraktMovieInfo movie, TraktEpisodeInfo episode, TraktShowInfo show) {
        return new TraktPlaybackItem(id, progress, pausedAt, type, movie, episode, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktPlaybackItem)) {
            return false;
        }
        TraktPlaybackItem traktPlaybackItem = (TraktPlaybackItem) other;
        return this.id == traktPlaybackItem.id && Float.compare(this.progress, traktPlaybackItem.progress) == 0 && p.a(this.pausedAt, traktPlaybackItem.pausedAt) && p.a(this.type, traktPlaybackItem.type) && p.a(this.movie, traktPlaybackItem.movie) && p.a(this.episode, traktPlaybackItem.episode) && p.a(this.show, traktPlaybackItem.show);
    }

    public final TraktEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final long getId() {
        return this.id;
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final String getPausedAt() {
        return this.pausedAt;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        long j10 = this.id;
        int iB = a0.c.b(this.progress, ((int) (j10 ^ (j10 >>> 32))) * 31, 31);
        String str = this.pausedAt;
        int iC = androidx.compose.foundation.c.c((iB + (str == null ? 0 : str.hashCode())) * 31, 31, this.type);
        TraktMovieInfo traktMovieInfo = this.movie;
        int iHashCode = (iC + (traktMovieInfo == null ? 0 : traktMovieInfo.hashCode())) * 31;
        TraktEpisodeInfo traktEpisodeInfo = this.episode;
        int iHashCode2 = (iHashCode + (traktEpisodeInfo == null ? 0 : traktEpisodeInfo.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        return iHashCode2 + (traktShowInfo != null ? traktShowInfo.hashCode() : 0);
    }

    public String toString() {
        long j10 = this.id;
        float f10 = this.progress;
        String str = this.pausedAt;
        String str2 = this.type;
        TraktMovieInfo traktMovieInfo = this.movie;
        TraktEpisodeInfo traktEpisodeInfo = this.episode;
        TraktShowInfo traktShowInfo = this.show;
        StringBuilder sb2 = new StringBuilder("TraktPlaybackItem(id=");
        sb2.append(j10);
        sb2.append(", progress=");
        sb2.append(f10);
        y.a.i(sb2, ", pausedAt=", str, ", type=", str2);
        sb2.append(", movie=");
        sb2.append(traktMovieInfo);
        sb2.append(", episode=");
        sb2.append(traktEpisodeInfo);
        sb2.append(", show=");
        sb2.append(traktShowInfo);
        sb2.append(")");
        return sb2.toString();
    }
}
