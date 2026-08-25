package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003JF\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/api/WatchedMovieRecord;", "", "userId", "", "profileId", "tmdbId", "", "traktId", "watchedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/String;)V", "getUserId", "()Ljava/lang/String;", "getProfileId", "getTmdbId", "()I", "getTraktId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getWatchedAt", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/WatchedMovieRecord;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class WatchedMovieRecord {
    public static final int $stable = 0;

    @SerializedName("profile_id")
    private final String profileId;

    @SerializedName("tmdb_id")
    private final int tmdbId;

    @SerializedName("trakt_id")
    private final Integer traktId;

    @SerializedName("user_id")
    private final String userId;

    @SerializedName("watched_at")
    private final String watchedAt;

    public WatchedMovieRecord(String str, String str2, int i10, Integer num, String str3) {
        this.userId = str;
        this.profileId = str2;
        this.tmdbId = i10;
        this.traktId = num;
        this.watchedAt = str3;
    }

    public static /* synthetic */ WatchedMovieRecord copy$default(WatchedMovieRecord watchedMovieRecord, String str, String str2, int i10, Integer num, String str3, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = watchedMovieRecord.userId;
        }
        if ((i11 & 2) != 0) {
            str2 = watchedMovieRecord.profileId;
        }
        if ((i11 & 4) != 0) {
            i10 = watchedMovieRecord.tmdbId;
        }
        if ((i11 & 8) != 0) {
            num = watchedMovieRecord.traktId;
        }
        if ((i11 & 16) != 0) {
            str3 = watchedMovieRecord.watchedAt;
        }
        String str4 = str3;
        int i12 = i10;
        return watchedMovieRecord.copy(str, str2, i12, num, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProfileId() {
        return this.profileId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTmdbId() {
        return this.tmdbId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getTraktId() {
        return this.traktId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public final WatchedMovieRecord copy(String userId, String profileId, int tmdbId, Integer traktId, String watchedAt) {
        return new WatchedMovieRecord(userId, profileId, tmdbId, traktId, watchedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchedMovieRecord)) {
            return false;
        }
        WatchedMovieRecord watchedMovieRecord = (WatchedMovieRecord) other;
        return p.a(this.userId, watchedMovieRecord.userId) && p.a(this.profileId, watchedMovieRecord.profileId) && this.tmdbId == watchedMovieRecord.tmdbId && p.a(this.traktId, watchedMovieRecord.traktId) && p.a(this.watchedAt, watchedMovieRecord.watchedAt);
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final int getTmdbId() {
        return this.tmdbId;
    }

    public final Integer getTraktId() {
        return this.traktId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public int hashCode() {
        int iHashCode = this.userId.hashCode() * 31;
        String str = this.profileId;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.tmdbId) * 31;
        Integer num = this.traktId;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.watchedAt;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.userId;
        String str2 = this.profileId;
        int i10 = this.tmdbId;
        Integer num = this.traktId;
        String str3 = this.watchedAt;
        StringBuilder sbR = a2.r("WatchedMovieRecord(userId=", str, ", profileId=", str2, ", tmdbId=");
        sbR.append(i10);
        sbR.append(", traktId=");
        sbR.append(num);
        sbR.append(", watchedAt=");
        return a0.c.p(sbR, str3, ")");
    }

    public /* synthetic */ WatchedMovieRecord(String str, String str2, int i10, Integer num, String str3, int i11, kotlin.jvm.internal.h hVar) {
        this(str, (i11 & 2) != 0 ? null : str2, i10, (i11 & 8) != 0 ? null : num, (i11 & 16) != 0 ? null : str3);
    }
}
