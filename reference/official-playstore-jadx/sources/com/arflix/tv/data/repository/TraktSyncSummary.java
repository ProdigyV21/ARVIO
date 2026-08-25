package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/TraktSyncSummary;", "", "lastSyncAt", "", "moviesSynced", "", "episodesSynced", "<init>", "(Ljava/lang/String;II)V", "getLastSyncAt", "()Ljava/lang/String;", "getMoviesSynced", "()I", "getEpisodesSynced", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSyncSummary {
    public static final int $stable = 0;
    private final int episodesSynced;
    private final String lastSyncAt;
    private final int moviesSynced;

    public TraktSyncSummary(String str, int i10, int i11) {
        this.lastSyncAt = str;
        this.moviesSynced = i10;
        this.episodesSynced = i11;
    }

    public static /* synthetic */ TraktSyncSummary copy$default(TraktSyncSummary traktSyncSummary, String str, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = traktSyncSummary.lastSyncAt;
        }
        if ((i12 & 2) != 0) {
            i10 = traktSyncSummary.moviesSynced;
        }
        if ((i12 & 4) != 0) {
            i11 = traktSyncSummary.episodesSynced;
        }
        return traktSyncSummary.copy(str, i10, i11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLastSyncAt() {
        return this.lastSyncAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMoviesSynced() {
        return this.moviesSynced;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getEpisodesSynced() {
        return this.episodesSynced;
    }

    public final TraktSyncSummary copy(String lastSyncAt, int moviesSynced, int episodesSynced) {
        return new TraktSyncSummary(lastSyncAt, moviesSynced, episodesSynced);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSyncSummary)) {
            return false;
        }
        TraktSyncSummary traktSyncSummary = (TraktSyncSummary) other;
        return kotlin.jvm.internal.p.a(this.lastSyncAt, traktSyncSummary.lastSyncAt) && this.moviesSynced == traktSyncSummary.moviesSynced && this.episodesSynced == traktSyncSummary.episodesSynced;
    }

    public final int getEpisodesSynced() {
        return this.episodesSynced;
    }

    public final String getLastSyncAt() {
        return this.lastSyncAt;
    }

    public final int getMoviesSynced() {
        return this.moviesSynced;
    }

    public int hashCode() {
        String str = this.lastSyncAt;
        return ((((str == null ? 0 : str.hashCode()) * 31) + this.moviesSynced) * 31) + this.episodesSynced;
    }

    public String toString() {
        return androidx.compose.material3.d.j(this.episodesSynced, ")", a2.q("TraktSyncSummary(lastSyncAt=", this.lastSyncAt, ", moviesSynced=", this.moviesSynced, ", episodesSynced="));
    }
}
