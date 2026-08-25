package com.arflix.tv.data.api;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u007f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0086\u0001\u00100\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u00101J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u00020\nHÖ\u0081\u0004J\n\u00106\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u001a\u0010\u0015R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u00067"}, d2 = {"Lcom/arflix/tv/data/api/MdbPlaybackItem;", "", TtmlNode.ATTR_ID, "", "progress", "", "updatedAt", "updatedAtTs", "pausedAt", "runtime", "", LinkHeader.Parameters.Type, "movie", "Lcom/arflix/tv/data/api/MdbMovieInfo;", "show", "Lcom/arflix/tv/data/api/MdbShowInfo;", "episode", "Lcom/arflix/tv/data/api/MdbEpisodeInfo;", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbMovieInfo;Lcom/arflix/tv/data/api/MdbShowInfo;Lcom/arflix/tv/data/api/MdbEpisodeInfo;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getProgress", "()Ljava/lang/String;", "getUpdatedAt", "getUpdatedAtTs", "getPausedAt", "getRuntime", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getType", "getMovie", "()Lcom/arflix/tv/data/api/MdbMovieInfo;", "getShow", "()Lcom/arflix/tv/data/api/MdbShowInfo;", "getEpisode", "()Lcom/arflix/tv/data/api/MdbEpisodeInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbMovieInfo;Lcom/arflix/tv/data/api/MdbShowInfo;Lcom/arflix/tv/data/api/MdbEpisodeInfo;)Lcom/arflix/tv/data/api/MdbPlaybackItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbPlaybackItem {
    public static final int $stable = 0;
    private final MdbEpisodeInfo episode;
    private final Long id;
    private final MdbMovieInfo movie;

    @SerializedName("paused_at")
    private final String pausedAt;
    private final String progress;
    private final Integer runtime;
    private final MdbShowInfo show;
    private final String type;

    @SerializedName("updated_at")
    private final String updatedAt;

    @SerializedName("updated_at_ts")
    private final Long updatedAtTs;

    public MdbPlaybackItem() {
        this(null, null, null, null, null, null, null, null, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    public static /* synthetic */ MdbPlaybackItem copy$default(MdbPlaybackItem mdbPlaybackItem, Long l10, String str, String str2, Long l11, String str3, Integer num, String str4, MdbMovieInfo mdbMovieInfo, MdbShowInfo mdbShowInfo, MdbEpisodeInfo mdbEpisodeInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            l10 = mdbPlaybackItem.id;
        }
        if ((i10 & 2) != 0) {
            str = mdbPlaybackItem.progress;
        }
        if ((i10 & 4) != 0) {
            str2 = mdbPlaybackItem.updatedAt;
        }
        if ((i10 & 8) != 0) {
            l11 = mdbPlaybackItem.updatedAtTs;
        }
        if ((i10 & 16) != 0) {
            str3 = mdbPlaybackItem.pausedAt;
        }
        if ((i10 & 32) != 0) {
            num = mdbPlaybackItem.runtime;
        }
        if ((i10 & 64) != 0) {
            str4 = mdbPlaybackItem.type;
        }
        if ((i10 & 128) != 0) {
            mdbMovieInfo = mdbPlaybackItem.movie;
        }
        if ((i10 & 256) != 0) {
            mdbShowInfo = mdbPlaybackItem.show;
        }
        if ((i10 & 512) != 0) {
            mdbEpisodeInfo = mdbPlaybackItem.episode;
        }
        MdbShowInfo mdbShowInfo2 = mdbShowInfo;
        MdbEpisodeInfo mdbEpisodeInfo2 = mdbEpisodeInfo;
        String str5 = str4;
        MdbMovieInfo mdbMovieInfo2 = mdbMovieInfo;
        String str6 = str3;
        Integer num2 = num;
        return mdbPlaybackItem.copy(l10, str, str2, l11, str6, num2, str5, mdbMovieInfo2, mdbShowInfo2, mdbEpisodeInfo2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final MdbEpisodeInfo getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getUpdatedAtTs() {
        return this.updatedAtTs;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPausedAt() {
        return this.pausedAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getRuntime() {
        return this.runtime;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final MdbMovieInfo getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final MdbShowInfo getShow() {
        return this.show;
    }

    public final MdbPlaybackItem copy(Long id, String progress, String updatedAt, Long updatedAtTs, String pausedAt, Integer runtime, String type, MdbMovieInfo movie, MdbShowInfo show, MdbEpisodeInfo episode) {
        return new MdbPlaybackItem(id, progress, updatedAt, updatedAtTs, pausedAt, runtime, type, movie, show, episode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbPlaybackItem)) {
            return false;
        }
        MdbPlaybackItem mdbPlaybackItem = (MdbPlaybackItem) other;
        return p.a(this.id, mdbPlaybackItem.id) && p.a(this.progress, mdbPlaybackItem.progress) && p.a(this.updatedAt, mdbPlaybackItem.updatedAt) && p.a(this.updatedAtTs, mdbPlaybackItem.updatedAtTs) && p.a(this.pausedAt, mdbPlaybackItem.pausedAt) && p.a(this.runtime, mdbPlaybackItem.runtime) && p.a(this.type, mdbPlaybackItem.type) && p.a(this.movie, mdbPlaybackItem.movie) && p.a(this.show, mdbPlaybackItem.show) && p.a(this.episode, mdbPlaybackItem.episode);
    }

    public final MdbEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final Long getId() {
        return this.id;
    }

    public final MdbMovieInfo getMovie() {
        return this.movie;
    }

    public final String getPausedAt() {
        return this.pausedAt;
    }

    public final String getProgress() {
        return this.progress;
    }

    public final Integer getRuntime() {
        return this.runtime;
    }

    public final MdbShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final Long getUpdatedAtTs() {
        return this.updatedAtTs;
    }

    public int hashCode() {
        Long l10 = this.id;
        int iHashCode = (l10 == null ? 0 : l10.hashCode()) * 31;
        String str = this.progress;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.updatedAt;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l11 = this.updatedAtTs;
        int iHashCode4 = (iHashCode3 + (l11 == null ? 0 : l11.hashCode())) * 31;
        String str3 = this.pausedAt;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.runtime;
        int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
        String str4 = this.type;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        MdbMovieInfo mdbMovieInfo = this.movie;
        int iHashCode8 = (iHashCode7 + (mdbMovieInfo == null ? 0 : mdbMovieInfo.hashCode())) * 31;
        MdbShowInfo mdbShowInfo = this.show;
        int iHashCode9 = (iHashCode8 + (mdbShowInfo == null ? 0 : mdbShowInfo.hashCode())) * 31;
        MdbEpisodeInfo mdbEpisodeInfo = this.episode;
        return iHashCode9 + (mdbEpisodeInfo != null ? mdbEpisodeInfo.hashCode() : 0);
    }

    public String toString() {
        return "MdbPlaybackItem(id=" + this.id + ", progress=" + this.progress + ", updatedAt=" + this.updatedAt + ", updatedAtTs=" + this.updatedAtTs + ", pausedAt=" + this.pausedAt + ", runtime=" + this.runtime + ", type=" + this.type + ", movie=" + this.movie + ", show=" + this.show + ", episode=" + this.episode + ")";
    }

    public MdbPlaybackItem(Long l10, String str, String str2, Long l11, String str3, Integer num, String str4, MdbMovieInfo mdbMovieInfo, MdbShowInfo mdbShowInfo, MdbEpisodeInfo mdbEpisodeInfo) {
        this.id = l10;
        this.progress = str;
        this.updatedAt = str2;
        this.updatedAtTs = l11;
        this.pausedAt = str3;
        this.runtime = num;
        this.type = str4;
        this.movie = mdbMovieInfo;
        this.show = mdbShowInfo;
        this.episode = mdbEpisodeInfo;
    }

    public /* synthetic */ MdbPlaybackItem(Long l10, String str, String str2, Long l11, String str3, Integer num, String str4, MdbMovieInfo mdbMovieInfo, MdbShowInfo mdbShowInfo, MdbEpisodeInfo mdbEpisodeInfo, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : l10, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : l11, (i10 & 16) != 0 ? null : str3, (i10 & 32) != 0 ? null : num, (i10 & 64) != 0 ? null : str4, (i10 & 128) != 0 ? null : mdbMovieInfo, (i10 & 256) != 0 ? null : mdbShowInfo, (i10 & 512) != 0 ? null : mdbEpisodeInfo);
    }
}
