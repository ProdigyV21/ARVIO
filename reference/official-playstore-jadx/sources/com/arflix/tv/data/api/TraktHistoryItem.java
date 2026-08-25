package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\rHÆ\u0003JU\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0014\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006*"}, d2 = {"Lcom/arflix/tv/data/api/TraktHistoryItem;", "", TtmlNode.ATTR_ID, "", "watchedAt", "", "action", LinkHeader.Parameters.Type, "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "episode", "Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktShowInfo;Lcom/arflix/tv/data/api/TraktEpisodeInfo;)V", "getId", "()J", "getWatchedAt", "()Ljava/lang/String;", "getAction", "getType", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "getEpisode", "()Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktHistoryItem {
    public static final int $stable = 0;
    private final String action;
    private final TraktEpisodeInfo episode;
    private final long id;
    private final TraktMovieInfo movie;
    private final TraktShowInfo show;
    private final String type;

    @SerializedName("watched_at")
    private final String watchedAt;

    public TraktHistoryItem(long j10, String str, String str2, String str3, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, TraktEpisodeInfo traktEpisodeInfo) {
        this.id = j10;
        this.watchedAt = str;
        this.action = str2;
        this.type = str3;
        this.movie = traktMovieInfo;
        this.show = traktShowInfo;
        this.episode = traktEpisodeInfo;
    }

    public static /* synthetic */ TraktHistoryItem copy$default(TraktHistoryItem traktHistoryItem, long j10, String str, String str2, String str3, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, TraktEpisodeInfo traktEpisodeInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = traktHistoryItem.id;
        }
        long j11 = j10;
        if ((i10 & 2) != 0) {
            str = traktHistoryItem.watchedAt;
        }
        String str4 = str;
        if ((i10 & 4) != 0) {
            str2 = traktHistoryItem.action;
        }
        String str5 = str2;
        if ((i10 & 8) != 0) {
            str3 = traktHistoryItem.type;
        }
        String str6 = str3;
        if ((i10 & 16) != 0) {
            traktMovieInfo = traktHistoryItem.movie;
        }
        return traktHistoryItem.copy(j11, str4, str5, str6, traktMovieInfo, (i10 & 32) != 0 ? traktHistoryItem.show : traktShowInfo, (i10 & 64) != 0 ? traktHistoryItem.episode : traktEpisodeInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getWatchedAt() {
        return this.watchedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAction() {
        return this.action;
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
    public final TraktShowInfo getShow() {
        return this.show;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final TraktEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final TraktHistoryItem copy(long id, String watchedAt, String action, String type, TraktMovieInfo movie, TraktShowInfo show, TraktEpisodeInfo episode) {
        return new TraktHistoryItem(id, watchedAt, action, type, movie, show, episode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktHistoryItem)) {
            return false;
        }
        TraktHistoryItem traktHistoryItem = (TraktHistoryItem) other;
        return this.id == traktHistoryItem.id && p.a(this.watchedAt, traktHistoryItem.watchedAt) && p.a(this.action, traktHistoryItem.action) && p.a(this.type, traktHistoryItem.type) && p.a(this.movie, traktHistoryItem.movie) && p.a(this.show, traktHistoryItem.show) && p.a(this.episode, traktHistoryItem.episode);
    }

    public final String getAction() {
        return this.action;
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

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public int hashCode() {
        long j10 = this.id;
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(((int) (j10 ^ (j10 >>> 32))) * 31, 31, this.watchedAt), 31, this.action), 31, this.type);
        TraktMovieInfo traktMovieInfo = this.movie;
        int iHashCode = (iC + (traktMovieInfo == null ? 0 : traktMovieInfo.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        int iHashCode2 = (iHashCode + (traktShowInfo == null ? 0 : traktShowInfo.hashCode())) * 31;
        TraktEpisodeInfo traktEpisodeInfo = this.episode;
        return iHashCode2 + (traktEpisodeInfo != null ? traktEpisodeInfo.hashCode() : 0);
    }

    public String toString() {
        long j10 = this.id;
        String str = this.watchedAt;
        String str2 = this.action;
        String str3 = this.type;
        TraktMovieInfo traktMovieInfo = this.movie;
        TraktShowInfo traktShowInfo = this.show;
        TraktEpisodeInfo traktEpisodeInfo = this.episode;
        StringBuilder sb2 = new StringBuilder("TraktHistoryItem(id=");
        sb2.append(j10);
        sb2.append(", watchedAt=");
        sb2.append(str);
        y.a.i(sb2, ", action=", str2, ", type=", str3);
        sb2.append(", movie=");
        sb2.append(traktMovieInfo);
        sb2.append(", show=");
        sb2.append(traktShowInfo);
        sb2.append(", episode=");
        sb2.append(traktEpisodeInfo);
        sb2.append(")");
        return sb2.toString();
    }
}
