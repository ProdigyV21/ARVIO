package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ2\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/TraktCommentUserStats;", "", "rating", "", "playCount", "completedCount", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getRating", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPlayCount", "getCompletedCount", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/api/TraktCommentUserStats;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktCommentUserStats {
    public static final int $stable = 0;

    @SerializedName("completed_count")
    private final Integer completedCount;

    @SerializedName("play_count")
    private final Integer playCount;
    private final Integer rating;

    public TraktCommentUserStats(Integer num, Integer num2, Integer num3) {
        this.rating = num;
        this.playCount = num2;
        this.completedCount = num3;
    }

    public static /* synthetic */ TraktCommentUserStats copy$default(TraktCommentUserStats traktCommentUserStats, Integer num, Integer num2, Integer num3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = traktCommentUserStats.rating;
        }
        if ((i10 & 2) != 0) {
            num2 = traktCommentUserStats.playCount;
        }
        if ((i10 & 4) != 0) {
            num3 = traktCommentUserStats.completedCount;
        }
        return traktCommentUserStats.copy(num, num2, num3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getPlayCount() {
        return this.playCount;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getCompletedCount() {
        return this.completedCount;
    }

    public final TraktCommentUserStats copy(Integer rating, Integer playCount, Integer completedCount) {
        return new TraktCommentUserStats(rating, playCount, completedCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktCommentUserStats)) {
            return false;
        }
        TraktCommentUserStats traktCommentUserStats = (TraktCommentUserStats) other;
        return p.a(this.rating, traktCommentUserStats.rating) && p.a(this.playCount, traktCommentUserStats.playCount) && p.a(this.completedCount, traktCommentUserStats.completedCount);
    }

    public final Integer getCompletedCount() {
        return this.completedCount;
    }

    public final Integer getPlayCount() {
        return this.playCount;
    }

    public final Integer getRating() {
        return this.rating;
    }

    public int hashCode() {
        Integer num = this.rating;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.playCount;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.completedCount;
        return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        return "TraktCommentUserStats(rating=" + this.rating + ", playCount=" + this.playCount + ", completedCount=" + this.completedCount + ")";
    }
}
