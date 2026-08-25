package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/TraktShowActivityTimestamps;", "", "watchedAt", "", "collectedAt", "ratedAt", "watchlistedAt", "favoritedAt", "commentedAt", "hiddenAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getWatchedAt", "()Ljava/lang/String;", "getCollectedAt", "getRatedAt", "getWatchlistedAt", "getFavoritedAt", "getCommentedAt", "getHiddenAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktShowActivityTimestamps {
    public static final int $stable = 0;

    @SerializedName("collected_at")
    private final String collectedAt;

    @SerializedName("commented_at")
    private final String commentedAt;

    @SerializedName("favorited_at")
    private final String favoritedAt;

    @SerializedName("hidden_at")
    private final String hiddenAt;

    @SerializedName("rated_at")
    private final String ratedAt;

    @SerializedName("watched_at")
    private final String watchedAt;

    @SerializedName("watchlisted_at")
    private final String watchlistedAt;

    public TraktShowActivityTimestamps() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ TraktShowActivityTimestamps copy$default(TraktShowActivityTimestamps traktShowActivityTimestamps, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktShowActivityTimestamps.watchedAt;
        }
        if ((i10 & 2) != 0) {
            str2 = traktShowActivityTimestamps.collectedAt;
        }
        if ((i10 & 4) != 0) {
            str3 = traktShowActivityTimestamps.ratedAt;
        }
        if ((i10 & 8) != 0) {
            str4 = traktShowActivityTimestamps.watchlistedAt;
        }
        if ((i10 & 16) != 0) {
            str5 = traktShowActivityTimestamps.favoritedAt;
        }
        if ((i10 & 32) != 0) {
            str6 = traktShowActivityTimestamps.commentedAt;
        }
        if ((i10 & 64) != 0) {
            str7 = traktShowActivityTimestamps.hiddenAt;
        }
        String str8 = str6;
        String str9 = str7;
        String str10 = str5;
        String str11 = str3;
        return traktShowActivityTimestamps.copy(str, str2, str11, str4, str10, str8, str9);
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
    public final String getHiddenAt() {
        return this.hiddenAt;
    }

    public final TraktShowActivityTimestamps copy(String watchedAt, String collectedAt, String ratedAt, String watchlistedAt, String favoritedAt, String commentedAt, String hiddenAt) {
        return new TraktShowActivityTimestamps(watchedAt, collectedAt, ratedAt, watchlistedAt, favoritedAt, commentedAt, hiddenAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktShowActivityTimestamps)) {
            return false;
        }
        TraktShowActivityTimestamps traktShowActivityTimestamps = (TraktShowActivityTimestamps) other;
        return p.a(this.watchedAt, traktShowActivityTimestamps.watchedAt) && p.a(this.collectedAt, traktShowActivityTimestamps.collectedAt) && p.a(this.ratedAt, traktShowActivityTimestamps.ratedAt) && p.a(this.watchlistedAt, traktShowActivityTimestamps.watchlistedAt) && p.a(this.favoritedAt, traktShowActivityTimestamps.favoritedAt) && p.a(this.commentedAt, traktShowActivityTimestamps.commentedAt) && p.a(this.hiddenAt, traktShowActivityTimestamps.hiddenAt);
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

    public final String getRatedAt() {
        return this.ratedAt;
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
        String str7 = this.hiddenAt;
        return iHashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        String str = this.watchedAt;
        String str2 = this.collectedAt;
        String str3 = this.ratedAt;
        String str4 = this.watchlistedAt;
        String str5 = this.favoritedAt;
        String str6 = this.commentedAt;
        String str7 = this.hiddenAt;
        StringBuilder sbR = a2.r("TraktShowActivityTimestamps(watchedAt=", str, ", collectedAt=", str2, ", ratedAt=");
        y.a.i(sbR, str3, ", watchlistedAt=", str4, ", favoritedAt=");
        y.a.i(sbR, str5, ", commentedAt=", str6, ", hiddenAt=");
        return a0.c.p(sbR, str7, ")");
    }

    public TraktShowActivityTimestamps(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.watchedAt = str;
        this.collectedAt = str2;
        this.ratedAt = str3;
        this.watchlistedAt = str4;
        this.favoritedAt = str5;
        this.commentedAt = str6;
        this.hiddenAt = str7;
    }

    public /* synthetic */ TraktShowActivityTimestamps(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : str6, (i10 & 64) != 0 ? null : str7);
    }
}
