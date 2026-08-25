package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013JH\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/TraktRatingEpisodeItem;", "", "rating", "", "ratedAt", "", "ids", "Lcom/arflix/tv/data/api/TraktIds;", "season", "number", "<init>", "(ILjava/lang/String;Lcom/arflix/tv/data/api/TraktIds;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getRating", "()I", "getRatedAt", "()Ljava/lang/String;", "getIds", "()Lcom/arflix/tv/data/api/TraktIds;", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNumber", "component1", "component2", "component3", "component4", "component5", "copy", "(ILjava/lang/String;Lcom/arflix/tv/data/api/TraktIds;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/api/TraktRatingEpisodeItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktRatingEpisodeItem {
    public static final int $stable = 0;
    private final TraktIds ids;
    private final Integer number;

    @SerializedName("rated_at")
    private final String ratedAt;
    private final int rating;
    private final Integer season;

    public TraktRatingEpisodeItem(int i10, String str, TraktIds traktIds, Integer num, Integer num2) {
        this.rating = i10;
        this.ratedAt = str;
        this.ids = traktIds;
        this.season = num;
        this.number = num2;
    }

    public static /* synthetic */ TraktRatingEpisodeItem copy$default(TraktRatingEpisodeItem traktRatingEpisodeItem, int i10, String str, TraktIds traktIds, Integer num, Integer num2, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktRatingEpisodeItem.rating;
        }
        if ((i11 & 2) != 0) {
            str = traktRatingEpisodeItem.ratedAt;
        }
        if ((i11 & 4) != 0) {
            traktIds = traktRatingEpisodeItem.ids;
        }
        if ((i11 & 8) != 0) {
            num = traktRatingEpisodeItem.season;
        }
        if ((i11 & 16) != 0) {
            num2 = traktRatingEpisodeItem.number;
        }
        Integer num3 = num2;
        TraktIds traktIds2 = traktIds;
        return traktRatingEpisodeItem.copy(i10, str, traktIds2, num, num3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRatedAt() {
        return this.ratedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktIds getIds() {
        return this.ids;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getNumber() {
        return this.number;
    }

    public final TraktRatingEpisodeItem copy(int rating, String ratedAt, TraktIds ids, Integer season, Integer number) {
        return new TraktRatingEpisodeItem(rating, ratedAt, ids, season, number);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktRatingEpisodeItem)) {
            return false;
        }
        TraktRatingEpisodeItem traktRatingEpisodeItem = (TraktRatingEpisodeItem) other;
        return this.rating == traktRatingEpisodeItem.rating && p.a(this.ratedAt, traktRatingEpisodeItem.ratedAt) && p.a(this.ids, traktRatingEpisodeItem.ids) && p.a(this.season, traktRatingEpisodeItem.season) && p.a(this.number, traktRatingEpisodeItem.number);
    }

    public final TraktIds getIds() {
        return this.ids;
    }

    public final Integer getNumber() {
        return this.number;
    }

    public final String getRatedAt() {
        return this.ratedAt;
    }

    public final int getRating() {
        return this.rating;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public int hashCode() {
        int i10 = this.rating * 31;
        String str = this.ratedAt;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        TraktIds traktIds = this.ids;
        int iHashCode2 = (iHashCode + (traktIds == null ? 0 : traktIds.hashCode())) * 31;
        Integer num = this.season;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.number;
        return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.rating;
        String str = this.ratedAt;
        TraktIds traktIds = this.ids;
        Integer num = this.season;
        Integer num2 = this.number;
        StringBuilder sbO = a2.o("TraktRatingEpisodeItem(rating=", i10, ", ratedAt=", str, ", ids=");
        sbO.append(traktIds);
        sbO.append(", season=");
        sbO.append(num);
        sbO.append(", number=");
        sbO.append(num2);
        sbO.append(")");
        return sbO.toString();
    }

    public /* synthetic */ TraktRatingEpisodeItem(int i10, String str, TraktIds traktIds, Integer num, Integer num2, int i11, kotlin.jvm.internal.h hVar) {
        this(i10, (i11 & 2) != 0 ? null : str, (i11 & 4) != 0 ? null : traktIds, (i11 & 8) != 0 ? null : num, (i11 & 16) != 0 ? null : num2);
    }
}
