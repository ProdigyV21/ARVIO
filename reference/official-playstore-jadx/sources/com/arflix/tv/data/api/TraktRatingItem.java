package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\fHÆ\u0003JM\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lcom/arflix/tv/data/api/TraktRatingItem;", "", "ratedAt", "", "rating", "", LinkHeader.Parameters.Type, "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "episode", "Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "<init>", "(Ljava/lang/String;ILjava/lang/String;Lcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktShowInfo;Lcom/arflix/tv/data/api/TraktEpisodeInfo;)V", "getRatedAt", "()Ljava/lang/String;", "getRating", "()I", "getType", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "getEpisode", "()Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktRatingItem {
    public static final int $stable = 0;
    private final TraktEpisodeInfo episode;
    private final TraktMovieInfo movie;

    @SerializedName("rated_at")
    private final String ratedAt;
    private final int rating;
    private final TraktShowInfo show;
    private final String type;

    public TraktRatingItem(String str, int i10, String str2, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, TraktEpisodeInfo traktEpisodeInfo) {
        this.ratedAt = str;
        this.rating = i10;
        this.type = str2;
        this.movie = traktMovieInfo;
        this.show = traktShowInfo;
        this.episode = traktEpisodeInfo;
    }

    public static /* synthetic */ TraktRatingItem copy$default(TraktRatingItem traktRatingItem, String str, int i10, String str2, TraktMovieInfo traktMovieInfo, TraktShowInfo traktShowInfo, TraktEpisodeInfo traktEpisodeInfo, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = traktRatingItem.ratedAt;
        }
        if ((i11 & 2) != 0) {
            i10 = traktRatingItem.rating;
        }
        if ((i11 & 4) != 0) {
            str2 = traktRatingItem.type;
        }
        if ((i11 & 8) != 0) {
            traktMovieInfo = traktRatingItem.movie;
        }
        if ((i11 & 16) != 0) {
            traktShowInfo = traktRatingItem.show;
        }
        if ((i11 & 32) != 0) {
            traktEpisodeInfo = traktRatingItem.episode;
        }
        TraktShowInfo traktShowInfo2 = traktShowInfo;
        TraktEpisodeInfo traktEpisodeInfo2 = traktEpisodeInfo;
        return traktRatingItem.copy(str, i10, str2, traktMovieInfo, traktShowInfo2, traktEpisodeInfo2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRatedAt() {
        return this.ratedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TraktEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final TraktRatingItem copy(String ratedAt, int rating, String type, TraktMovieInfo movie, TraktShowInfo show, TraktEpisodeInfo episode) {
        return new TraktRatingItem(ratedAt, rating, type, movie, show, episode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktRatingItem)) {
            return false;
        }
        TraktRatingItem traktRatingItem = (TraktRatingItem) other;
        return p.a(this.ratedAt, traktRatingItem.ratedAt) && this.rating == traktRatingItem.rating && p.a(this.type, traktRatingItem.type) && p.a(this.movie, traktRatingItem.movie) && p.a(this.show, traktRatingItem.show) && p.a(this.episode, traktRatingItem.episode);
    }

    public final TraktEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final String getRatedAt() {
        return this.ratedAt;
    }

    public final int getRating() {
        return this.rating;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.ratedAt;
        int iC = androidx.compose.foundation.c.c((((str == null ? 0 : str.hashCode()) * 31) + this.rating) * 31, 31, this.type);
        TraktMovieInfo traktMovieInfo = this.movie;
        int iHashCode = (iC + (traktMovieInfo == null ? 0 : traktMovieInfo.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        int iHashCode2 = (iHashCode + (traktShowInfo == null ? 0 : traktShowInfo.hashCode())) * 31;
        TraktEpisodeInfo traktEpisodeInfo = this.episode;
        return iHashCode2 + (traktEpisodeInfo != null ? traktEpisodeInfo.hashCode() : 0);
    }

    public String toString() {
        String str = this.ratedAt;
        int i10 = this.rating;
        String str2 = this.type;
        TraktMovieInfo traktMovieInfo = this.movie;
        TraktShowInfo traktShowInfo = this.show;
        TraktEpisodeInfo traktEpisodeInfo = this.episode;
        StringBuilder sbQ = a2.q("TraktRatingItem(ratedAt=", str, ", rating=", i10, ", type=");
        sbQ.append(str2);
        sbQ.append(", movie=");
        sbQ.append(traktMovieInfo);
        sbQ.append(", show=");
        sbQ.append(traktShowInfo);
        sbQ.append(", episode=");
        sbQ.append(traktEpisodeInfo);
        sbQ.append(")");
        return sbQ.toString();
    }
}
