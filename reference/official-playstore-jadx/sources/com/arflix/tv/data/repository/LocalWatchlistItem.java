package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006%"}, d2 = {"Lcom/arflix/tv/data/repository/LocalWatchlistItem;", "", "tmdbId", "", "mediaType", "", LinkHeader.Parameters.Title, "posterPath", "backdropPath", "addedAt", "", "sourceOrder", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JI)V", "getTmdbId", "()I", "getMediaType", "()Ljava/lang/String;", "getTitle", "getPosterPath", "getBackdropPath", "getAddedAt", "()J", "getSourceOrder", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LocalWatchlistItem {
    public static final int $stable = 0;
    private final long addedAt;
    private final String backdropPath;
    private final String mediaType;
    private final String posterPath;
    private final int sourceOrder;
    private final String title;
    private final int tmdbId;

    public LocalWatchlistItem(int i10, String str, String str2, String str3, String str4, long j10, int i11) {
        this.tmdbId = i10;
        this.mediaType = str;
        this.title = str2;
        this.posterPath = str3;
        this.backdropPath = str4;
        this.addedAt = j10;
        this.sourceOrder = i11;
    }

    public static /* synthetic */ LocalWatchlistItem copy$default(LocalWatchlistItem localWatchlistItem, int i10, String str, String str2, String str3, String str4, long j10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = localWatchlistItem.tmdbId;
        }
        if ((i12 & 2) != 0) {
            str = localWatchlistItem.mediaType;
        }
        if ((i12 & 4) != 0) {
            str2 = localWatchlistItem.title;
        }
        if ((i12 & 8) != 0) {
            str3 = localWatchlistItem.posterPath;
        }
        if ((i12 & 16) != 0) {
            str4 = localWatchlistItem.backdropPath;
        }
        if ((i12 & 32) != 0) {
            j10 = localWatchlistItem.addedAt;
        }
        if ((i12 & 64) != 0) {
            i11 = localWatchlistItem.sourceOrder;
        }
        int i13 = i11;
        long j11 = j10;
        String str5 = str4;
        String str6 = str2;
        return localWatchlistItem.copy(i10, str, str6, str3, str5, j11, i13);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getTmdbId() {
        return this.tmdbId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getAddedAt() {
        return this.addedAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getSourceOrder() {
        return this.sourceOrder;
    }

    public final LocalWatchlistItem copy(int tmdbId, String mediaType, String title, String posterPath, String backdropPath, long addedAt, int sourceOrder) {
        return new LocalWatchlistItem(tmdbId, mediaType, title, posterPath, backdropPath, addedAt, sourceOrder);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocalWatchlistItem)) {
            return false;
        }
        LocalWatchlistItem localWatchlistItem = (LocalWatchlistItem) other;
        return this.tmdbId == localWatchlistItem.tmdbId && kotlin.jvm.internal.p.a(this.mediaType, localWatchlistItem.mediaType) && kotlin.jvm.internal.p.a(this.title, localWatchlistItem.title) && kotlin.jvm.internal.p.a(this.posterPath, localWatchlistItem.posterPath) && kotlin.jvm.internal.p.a(this.backdropPath, localWatchlistItem.backdropPath) && this.addedAt == localWatchlistItem.addedAt && this.sourceOrder == localWatchlistItem.sourceOrder;
    }

    public final long getAddedAt() {
        return this.addedAt;
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public final int getSourceOrder() {
        return this.sourceOrder;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getTmdbId() {
        return this.tmdbId;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.tmdbId * 31, 31, this.mediaType), 31, this.title);
        String str = this.posterPath;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.backdropPath;
        int iHashCode2 = str2 != null ? str2.hashCode() : 0;
        long j10 = this.addedAt;
        return ((((iHashCode + iHashCode2) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31) + this.sourceOrder;
    }

    public String toString() {
        int i10 = this.tmdbId;
        String str = this.mediaType;
        String str2 = this.title;
        String str3 = this.posterPath;
        String str4 = this.backdropPath;
        long j10 = this.addedAt;
        int i11 = this.sourceOrder;
        StringBuilder sbO = a2.o("LocalWatchlistItem(tmdbId=", i10, ", mediaType=", str, ", title=");
        y.a.i(sbO, str2, ", posterPath=", str3, ", backdropPath=");
        sbO.append(str4);
        sbO.append(", addedAt=");
        sbO.append(j10);
        sbO.append(", sourceOrder=");
        sbO.append(i11);
        sbO.append(")");
        return sbO.toString();
    }

    public /* synthetic */ LocalWatchlistItem(int i10, String str, String str2, String str3, String str4, long j10, int i11, int i12, kotlin.jvm.internal.h hVar) {
        this(i10, str, str2, (i12 & 8) != 0 ? null : str3, (i12 & 16) != 0 ? null : str4, (i12 & 32) != 0 ? System.currentTimeMillis() : j10, (i12 & 64) != 0 ? Integer.MAX_VALUE : i11);
    }
}
