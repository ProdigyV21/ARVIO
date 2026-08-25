package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003Ju\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006("}, d2 = {"Lcom/arflix/tv/data/api/TraktActivityTimestamps;", "", "watchedAt", "", "collectedAt", "ratedAt", "watchlistedAt", "favoritedAt", "commentedAt", "pausedAt", "hiddenAt", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getWatchedAt", "()Ljava/lang/String;", "getCollectedAt", "getRatedAt", "getWatchlistedAt", "getFavoritedAt", "getCommentedAt", "getPausedAt", "getHiddenAt", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktActivityTimestamps {
    public static final int $stable = 0;

    @SerializedName("collected_at")
    private final String collectedAt;

    @SerializedName("commented_at")
    private final String commentedAt;

    @SerializedName("favorited_at")
    private final String favoritedAt;

    @SerializedName("hidden_at")
    private final String hiddenAt;

    @SerializedName("paused_at")
    private final String pausedAt;

    @SerializedName("rated_at")
    private final String ratedAt;

    @SerializedName("updated_at")
    private final String updatedAt;

    @SerializedName("watched_at")
    private final String watchedAt;

    @SerializedName("watchlisted_at")
    private final String watchlistedAt;

    public TraktActivityTimestamps() {
        this(null, null, null, null, null, null, null, null, null, 511, null);
    }

    public static /* synthetic */ TraktActivityTimestamps copy$default(TraktActivityTimestamps traktActivityTimestamps, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktActivityTimestamps.watchedAt;
        }
        if ((i10 & 2) != 0) {
            str2 = traktActivityTimestamps.collectedAt;
        }
        if ((i10 & 4) != 0) {
            str3 = traktActivityTimestamps.ratedAt;
        }
        if ((i10 & 8) != 0) {
            str4 = traktActivityTimestamps.watchlistedAt;
        }
        if ((i10 & 16) != 0) {
            str5 = traktActivityTimestamps.favoritedAt;
        }
        if ((i10 & 32) != 0) {
            str6 = traktActivityTimestamps.commentedAt;
        }
        if ((i10 & 64) != 0) {
            str7 = traktActivityTimestamps.pausedAt;
        }
        if ((i10 & 128) != 0) {
            str8 = traktActivityTimestamps.hiddenAt;
        }
        if ((i10 & 256) != 0) {
            str9 = traktActivityTimestamps.updatedAt;
        }
        String str10 = str8;
        String str11 = str9;
        String str12 = str6;
        String str13 = str7;
        String str14 = str5;
        String str15 = str3;
        return traktActivityTimestamps.copy(str, str2, str15, str4, str14, str12, str13, str10, str11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getWatchedAt() {
        return this.watchedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCollectedAt() {
        return this.collectedAt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRatedAt() {
        return this.ratedAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getWatchlistedAt() {
        return this.watchlistedAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFavoritedAt() {
        return this.favoritedAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getCommentedAt() {
        return this.commentedAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPausedAt() {
        return this.pausedAt;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getHiddenAt() {
        return this.hiddenAt;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final TraktActivityTimestamps copy(String watchedAt, String collectedAt, String ratedAt, String watchlistedAt, String favoritedAt, String commentedAt, String pausedAt, String hiddenAt, String updatedAt) {
        return new TraktActivityTimestamps(watchedAt, collectedAt, ratedAt, watchlistedAt, favoritedAt, commentedAt, pausedAt, hiddenAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktActivityTimestamps)) {
            return false;
        }
        TraktActivityTimestamps traktActivityTimestamps = (TraktActivityTimestamps) other;
        return p.a(this.watchedAt, traktActivityTimestamps.watchedAt) && p.a(this.collectedAt, traktActivityTimestamps.collectedAt) && p.a(this.ratedAt, traktActivityTimestamps.ratedAt) && p.a(this.watchlistedAt, traktActivityTimestamps.watchlistedAt) && p.a(this.favoritedAt, traktActivityTimestamps.favoritedAt) && p.a(this.commentedAt, traktActivityTimestamps.commentedAt) && p.a(this.pausedAt, traktActivityTimestamps.pausedAt) && p.a(this.hiddenAt, traktActivityTimestamps.hiddenAt) && p.a(this.updatedAt, traktActivityTimestamps.updatedAt);
    }

    public final String getCollectedAt() {
        return this.collectedAt;
    }

    public final String getCommentedAt() {
        return this.commentedAt;
    }

    public final String getFavoritedAt() {
        return this.favoritedAt;
    }

    public final String getHiddenAt() {
        return this.hiddenAt;
    }

    public final String getPausedAt() {
        return this.pausedAt;
    }

    public final String getRatedAt() {
        return this.ratedAt;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public final String getWatchlistedAt() {
        return this.watchlistedAt;
    }

    public int hashCode() {
        String str = this.watchedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.collectedAt;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.ratedAt;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.watchlistedAt;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.favoritedAt;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.commentedAt;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.pausedAt;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.hiddenAt;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.updatedAt;
        return iHashCode8 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        String str = this.watchedAt;
        String str2 = this.collectedAt;
        String str3 = this.ratedAt;
        String str4 = this.watchlistedAt;
        String str5 = this.favoritedAt;
        String str6 = this.commentedAt;
        String str7 = this.pausedAt;
        String str8 = this.hiddenAt;
        String str9 = this.updatedAt;
        StringBuilder sbR = a2.r("TraktActivityTimestamps(watchedAt=", str, ", collectedAt=", str2, ", ratedAt=");
        y.a.i(sbR, str3, ", watchlistedAt=", str4, ", favoritedAt=");
        y.a.i(sbR, str5, ", commentedAt=", str6, ", pausedAt=");
        y.a.i(sbR, str7, ", hiddenAt=", str8, ", updatedAt=");
        return a0.c.p(sbR, str9, ")");
    }

    public TraktActivityTimestamps(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.watchedAt = str;
        this.collectedAt = str2;
        this.ratedAt = str3;
        this.watchlistedAt = str4;
        this.favoritedAt = str5;
        this.commentedAt = str6;
        this.pausedAt = str7;
        this.hiddenAt = str8;
        this.updatedAt = str9;
    }

    public /* synthetic */ TraktActivityTimestamps(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : str6, (i10 & 64) != 0 ? null : str7, (i10 & 128) != 0 ? null : str8, (i10 & 256) != 0 ? null : str9);
    }
}
