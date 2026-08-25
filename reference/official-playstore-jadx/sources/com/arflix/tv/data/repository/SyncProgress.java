package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/repository/SyncProgress;", "", "status", "Lcom/arflix/tv/data/repository/SyncStatus;", "message", "", "moviesProcessed", "", "totalMovies", "episodesProcessed", "totalEpisodes", "<init>", "(Lcom/arflix/tv/data/repository/SyncStatus;Ljava/lang/String;IIII)V", "getStatus", "()Lcom/arflix/tv/data/repository/SyncStatus;", "getMessage", "()Ljava/lang/String;", "getMoviesProcessed", "()I", "getTotalMovies", "getEpisodesProcessed", "getTotalEpisodes", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SyncProgress {
    public static final int $stable = 0;
    private final int episodesProcessed;
    private final String message;
    private final int moviesProcessed;
    private final SyncStatus status;
    private final int totalEpisodes;
    private final int totalMovies;

    public SyncProgress() {
        this(null, null, 0, 0, 0, 0, 63, null);
    }

    public static /* synthetic */ SyncProgress copy$default(SyncProgress syncProgress, SyncStatus syncStatus, String str, int i10, int i11, int i12, int i13, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            syncStatus = syncProgress.status;
        }
        if ((i14 & 2) != 0) {
            str = syncProgress.message;
        }
        if ((i14 & 4) != 0) {
            i10 = syncProgress.moviesProcessed;
        }
        if ((i14 & 8) != 0) {
            i11 = syncProgress.totalMovies;
        }
        if ((i14 & 16) != 0) {
            i12 = syncProgress.episodesProcessed;
        }
        if ((i14 & 32) != 0) {
            i13 = syncProgress.totalEpisodes;
        }
        int i15 = i12;
        int i16 = i13;
        return syncProgress.copy(syncStatus, str, i10, i11, i15, i16);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SyncStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMoviesProcessed() {
        return this.moviesProcessed;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getTotalMovies() {
        return this.totalMovies;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getEpisodesProcessed() {
        return this.episodesProcessed;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getTotalEpisodes() {
        return this.totalEpisodes;
    }

    public final SyncProgress copy(SyncStatus status, String message, int moviesProcessed, int totalMovies, int episodesProcessed, int totalEpisodes) {
        return new SyncProgress(status, message, moviesProcessed, totalMovies, episodesProcessed, totalEpisodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncProgress)) {
            return false;
        }
        SyncProgress syncProgress = (SyncProgress) other;
        return this.status == syncProgress.status && kotlin.jvm.internal.p.a(this.message, syncProgress.message) && this.moviesProcessed == syncProgress.moviesProcessed && this.totalMovies == syncProgress.totalMovies && this.episodesProcessed == syncProgress.episodesProcessed && this.totalEpisodes == syncProgress.totalEpisodes;
    }

    public final int getEpisodesProcessed() {
        return this.episodesProcessed;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getMoviesProcessed() {
        return this.moviesProcessed;
    }

    public final SyncStatus getStatus() {
        return this.status;
    }

    public final int getTotalEpisodes() {
        return this.totalEpisodes;
    }

    public final int getTotalMovies() {
        return this.totalMovies;
    }

    public int hashCode() {
        return ((((((androidx.compose.foundation.c.c(this.status.hashCode() * 31, 31, this.message) + this.moviesProcessed) * 31) + this.totalMovies) * 31) + this.episodesProcessed) * 31) + this.totalEpisodes;
    }

    public String toString() {
        SyncStatus syncStatus = this.status;
        String str = this.message;
        int i10 = this.moviesProcessed;
        int i11 = this.totalMovies;
        int i12 = this.episodesProcessed;
        int i13 = this.totalEpisodes;
        StringBuilder sb2 = new StringBuilder("SyncProgress(status=");
        sb2.append(syncStatus);
        sb2.append(", message=");
        sb2.append(str);
        sb2.append(", moviesProcessed=");
        a2.y(sb2, i10, ", totalMovies=", i11, ", episodesProcessed=");
        sb2.append(i12);
        sb2.append(", totalEpisodes=");
        sb2.append(i13);
        sb2.append(")");
        return sb2.toString();
    }

    public SyncProgress(SyncStatus syncStatus, String str, int i10, int i11, int i12, int i13) {
        this.status = syncStatus;
        this.message = str;
        this.moviesProcessed = i10;
        this.totalMovies = i11;
        this.episodesProcessed = i12;
        this.totalEpisodes = i13;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ SyncProgress(com.arflix.tv.data.repository.SyncStatus r2, java.lang.String r3, int r4, int r5, int r6, int r7, int r8, kotlin.jvm.internal.h r9) {
        /*
            r1 = this;
            r9 = r8 & 1
            if (r9 == 0) goto L6
            com.arflix.tv.data.repository.SyncStatus r2 = com.arflix.tv.data.repository.SyncStatus.IDLE
        L6:
            r9 = r8 & 2
            if (r9 == 0) goto Lc
            java.lang.String r3 = ""
        Lc:
            r9 = r8 & 4
            r0 = 0
            if (r9 == 0) goto L12
            r4 = r0
        L12:
            r9 = r8 & 8
            if (r9 == 0) goto L17
            r5 = r0
        L17:
            r9 = r8 & 16
            if (r9 == 0) goto L1c
            r6 = r0
        L1c:
            r8 = r8 & 32
            if (r8 == 0) goto L28
            r9 = r0
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            goto L2f
        L28:
            r9 = r7
            r8 = r6
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
        L2f:
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SyncProgress.<init>(com.arflix.tv.data.repository.SyncStatus, java.lang.String, int, int, int, int, int, kotlin.jvm.internal.h):void");
    }
}
