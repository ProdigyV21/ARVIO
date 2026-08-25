package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003J>\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/api/SimklHistoryMovieItem;", "", "lastWatchedAt", "", "userRating", "", "status", "movie", "Lcom/arflix/tv/data/api/SimklMovieRef;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklMovieRef;)V", "getLastWatchedAt", "()Ljava/lang/String;", "getUserRating", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatus", "getMovie", "()Lcom/arflix/tv/data/api/SimklMovieRef;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklMovieRef;)Lcom/arflix/tv/data/api/SimklHistoryMovieItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklHistoryMovieItem {
    public static final int $stable = 0;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;

    @SerializedName("movie")
    private final SimklMovieRef movie;

    @SerializedName("status")
    private final String status;

    @SerializedName("user_rating")
    private final Integer userRating;

    public SimklHistoryMovieItem() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ SimklHistoryMovieItem copy$default(SimklHistoryMovieItem simklHistoryMovieItem, String str, Integer num, String str2, SimklMovieRef simklMovieRef, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklHistoryMovieItem.lastWatchedAt;
        }
        if ((i10 & 2) != 0) {
            num = simklHistoryMovieItem.userRating;
        }
        if ((i10 & 4) != 0) {
            str2 = simklHistoryMovieItem.status;
        }
        if ((i10 & 8) != 0) {
            simklMovieRef = simklHistoryMovieItem.movie;
        }
        return simklHistoryMovieItem.copy(str, num, str2, simklMovieRef);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getUserRating() {
        return this.userRating;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SimklMovieRef getMovie() {
        return this.movie;
    }

    public final SimklHistoryMovieItem copy(String lastWatchedAt, Integer userRating, String status, SimklMovieRef movie) {
        return new SimklHistoryMovieItem(lastWatchedAt, userRating, status, movie);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklHistoryMovieItem)) {
            return false;
        }
        SimklHistoryMovieItem simklHistoryMovieItem = (SimklHistoryMovieItem) other;
        return p.a(this.lastWatchedAt, simklHistoryMovieItem.lastWatchedAt) && p.a(this.userRating, simklHistoryMovieItem.userRating) && p.a(this.status, simklHistoryMovieItem.status) && p.a(this.movie, simklHistoryMovieItem.movie);
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final SimklMovieRef getMovie() {
        return this.movie;
    }

    public final String getStatus() {
        return this.status;
    }

    public final Integer getUserRating() {
        return this.userRating;
    }

    public int hashCode() {
        String str = this.lastWatchedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.userRating;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.status;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        SimklMovieRef simklMovieRef = this.movie;
        return iHashCode3 + (simklMovieRef != null ? simklMovieRef.hashCode() : 0);
    }

    public String toString() {
        return "SimklHistoryMovieItem(lastWatchedAt=" + this.lastWatchedAt + ", userRating=" + this.userRating + ", status=" + this.status + ", movie=" + this.movie + ")";
    }

    public SimklHistoryMovieItem(String str, Integer num, String str2, SimklMovieRef simklMovieRef) {
        this.lastWatchedAt = str;
        this.userRating = num;
        this.status = str2;
        this.movie = simklMovieRef;
    }

    public /* synthetic */ SimklHistoryMovieItem(String str, Integer num, String str2, SimklMovieRef simklMovieRef, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : simklMovieRef);
    }
}
