package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/WatchlistRecord;", "", "userId", "", "tmdbId", "", "mediaType", "addedAt", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getUserId", "()Ljava/lang/String;", "getTmdbId", "()I", "getMediaType", "getAddedAt", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class WatchlistRecord {
    public static final int $stable = 0;

    @SerializedName("added_at")
    private final String addedAt;

    @SerializedName("media_type")
    private final String mediaType;

    @SerializedName("tmdb_id")
    private final int tmdbId;

    @SerializedName("user_id")
    private final String userId;

    public WatchlistRecord(String str, int i10, String str2, String str3) {
        this.userId = str;
        this.tmdbId = i10;
        this.mediaType = str2;
        this.addedAt = str3;
    }

    public static /* synthetic */ WatchlistRecord copy$default(WatchlistRecord watchlistRecord, String str, int i10, String str2, String str3, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = watchlistRecord.userId;
        }
        if ((i11 & 2) != 0) {
            i10 = watchlistRecord.tmdbId;
        }
        if ((i11 & 4) != 0) {
            str2 = watchlistRecord.mediaType;
        }
        if ((i11 & 8) != 0) {
            str3 = watchlistRecord.addedAt;
        }
        return watchlistRecord.copy(str, i10, str2, str3);
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
    public final String getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddedAt() {
        return this.addedAt;
    }

    public final WatchlistRecord copy(String userId, int tmdbId, String mediaType, String addedAt) {
        return new WatchlistRecord(userId, tmdbId, mediaType, addedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchlistRecord)) {
            return false;
        }
        WatchlistRecord watchlistRecord = (WatchlistRecord) other;
        return p.a(this.userId, watchlistRecord.userId) && this.tmdbId == watchlistRecord.tmdbId && p.a(this.mediaType, watchlistRecord.mediaType) && p.a(this.addedAt, watchlistRecord.addedAt);
    }

    public final String getAddedAt() {
        return this.addedAt;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final int getTmdbId() {
        return this.tmdbId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(((this.userId.hashCode() * 31) + this.tmdbId) * 31, 31, this.mediaType);
        String str = this.addedAt;
        return iC + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.userId;
        int i10 = this.tmdbId;
        return a2.n(a2.q("WatchlistRecord(userId=", str, ", tmdbId=", i10, ", mediaType="), this.mediaType, ", addedAt=", this.addedAt, ")");
    }

    public /* synthetic */ WatchlistRecord(String str, int i10, String str2, String str3, int i11, kotlin.jvm.internal.h hVar) {
        this(str, i10, str2, (i11 & 8) != 0 ? null : str3);
    }
}
