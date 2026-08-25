package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JL\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001a\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\r¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/api/MarkEpisodeWatchedParams;", "", "userId", "", "tmdbId", "", "season", "episode", "showTraktId", "source", "<init>", "(Ljava/lang/String;IIILjava/lang/Integer;Ljava/lang/String;)V", "getUserId", "()Ljava/lang/String;", "getTmdbId", "()I", "getSeason", "getEpisode", "getShowTraktId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSource", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;IIILjava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/MarkEpisodeWatchedParams;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MarkEpisodeWatchedParams {
    public static final int $stable = 0;

    @SerializedName("p_episode")
    private final int episode;

    @SerializedName("p_season")
    private final int season;

    @SerializedName("p_show_trakt_id")
    private final Integer showTraktId;

    @SerializedName("p_source")
    private final String source;

    @SerializedName("p_tmdb_id")
    private final int tmdbId;

    @SerializedName("p_user_id")
    private final String userId;

    public MarkEpisodeWatchedParams(String str, int i10, int i11, int i12, Integer num, String str2) {
        this.userId = str;
        this.tmdbId = i10;
        this.season = i11;
        this.episode = i12;
        this.showTraktId = num;
        this.source = str2;
    }

    public static /* synthetic */ MarkEpisodeWatchedParams copy$default(MarkEpisodeWatchedParams markEpisodeWatchedParams, String str, int i10, int i11, int i12, Integer num, String str2, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            str = markEpisodeWatchedParams.userId;
        }
        if ((i13 & 2) != 0) {
            i10 = markEpisodeWatchedParams.tmdbId;
        }
        if ((i13 & 4) != 0) {
            i11 = markEpisodeWatchedParams.season;
        }
        if ((i13 & 8) != 0) {
            i12 = markEpisodeWatchedParams.episode;
        }
        if ((i13 & 16) != 0) {
            num = markEpisodeWatchedParams.showTraktId;
        }
        if ((i13 & 32) != 0) {
            str2 = markEpisodeWatchedParams.source;
        }
        Integer num2 = num;
        String str3 = str2;
        return markEpisodeWatchedParams.copy(str, i10, i11, i12, num2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getTmdbId() {
        return this.tmdbId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final MarkEpisodeWatchedParams copy(String userId, int tmdbId, int season, int episode, Integer showTraktId, String source) {
        return new MarkEpisodeWatchedParams(userId, tmdbId, season, episode, showTraktId, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MarkEpisodeWatchedParams)) {
            return false;
        }
        MarkEpisodeWatchedParams markEpisodeWatchedParams = (MarkEpisodeWatchedParams) other;
        return p.a(this.userId, markEpisodeWatchedParams.userId) && this.tmdbId == markEpisodeWatchedParams.tmdbId && this.season == markEpisodeWatchedParams.season && this.episode == markEpisodeWatchedParams.episode && p.a(this.showTraktId, markEpisodeWatchedParams.showTraktId) && p.a(this.source, markEpisodeWatchedParams.source);
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final int getSeason() {
        return this.season;
    }

    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    public final String getSource() {
        return this.source;
    }

    public final int getTmdbId() {
        return this.tmdbId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        int iHashCode = ((((((this.userId.hashCode() * 31) + this.tmdbId) * 31) + this.season) * 31) + this.episode) * 31;
        Integer num = this.showTraktId;
        return this.source.hashCode() + ((iHashCode + (num == null ? 0 : num.hashCode())) * 31);
    }

    public String toString() {
        String str = this.userId;
        int i10 = this.tmdbId;
        int i11 = this.season;
        int i12 = this.episode;
        Integer num = this.showTraktId;
        String str2 = this.source;
        StringBuilder sbQ = a2.q("MarkEpisodeWatchedParams(userId=", str, ", tmdbId=", i10, ", season=");
        a2.y(sbQ, i11, ", episode=", i12, ", showTraktId=");
        sbQ.append(num);
        sbQ.append(", source=");
        sbQ.append(str2);
        sbQ.append(")");
        return sbQ.toString();
    }

    public /* synthetic */ MarkEpisodeWatchedParams(String str, int i10, int i11, int i12, Integer num, String str2, int i13, kotlin.jvm.internal.h hVar) {
        this(str, i10, i11, i12, (i13 & 16) != 0 ? null : num, (i13 & 32) != 0 ? "arvio" : str2);
    }
}
