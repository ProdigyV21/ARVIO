package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/api/MdbWatchedMovieRow;", "", "lastWatchedAt", "", "movie", "Lcom/arflix/tv/data/api/MdbMovieInfo;", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/api/MdbMovieInfo;)V", "getLastWatchedAt", "()Ljava/lang/String;", "getMovie", "()Lcom/arflix/tv/data/api/MdbMovieInfo;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbWatchedMovieRow {
    public static final int $stable = 0;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;
    private final MdbMovieInfo movie;

    /* JADX WARN: Multi-variable type inference failed */
    public MdbWatchedMovieRow() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ MdbWatchedMovieRow copy$default(MdbWatchedMovieRow mdbWatchedMovieRow, String str, MdbMovieInfo mdbMovieInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mdbWatchedMovieRow.lastWatchedAt;
        }
        if ((i10 & 2) != 0) {
            mdbMovieInfo = mdbWatchedMovieRow.movie;
        }
        return mdbWatchedMovieRow.copy(str, mdbMovieInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MdbMovieInfo getMovie() {
        return this.movie;
    }

    public final MdbWatchedMovieRow copy(String lastWatchedAt, MdbMovieInfo movie) {
        return new MdbWatchedMovieRow(lastWatchedAt, movie);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbWatchedMovieRow)) {
            return false;
        }
        MdbWatchedMovieRow mdbWatchedMovieRow = (MdbWatchedMovieRow) other;
        return p.a(this.lastWatchedAt, mdbWatchedMovieRow.lastWatchedAt) && p.a(this.movie, mdbWatchedMovieRow.movie);
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final MdbMovieInfo getMovie() {
        return this.movie;
    }

    public int hashCode() {
        String str = this.lastWatchedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        MdbMovieInfo mdbMovieInfo = this.movie;
        return iHashCode + (mdbMovieInfo != null ? mdbMovieInfo.hashCode() : 0);
    }

    public String toString() {
        return "MdbWatchedMovieRow(lastWatchedAt=" + this.lastWatchedAt + ", movie=" + this.movie + ")";
    }

    public MdbWatchedMovieRow(String str, MdbMovieInfo mdbMovieInfo) {
        this.lastWatchedAt = str;
        this.movie = mdbMovieInfo;
    }

    public /* synthetic */ MdbWatchedMovieRow(String str, MdbMovieInfo mdbMovieInfo, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : mdbMovieInfo);
    }
}
