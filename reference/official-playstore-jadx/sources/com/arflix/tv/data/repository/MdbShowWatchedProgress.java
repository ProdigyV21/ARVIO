package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u001b\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003JM\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/repository/MdbShowWatchedProgress;", "", "showTmdbId", "", LinkHeader.Parameters.Title, "", "year", "watchedBySeason", "", "", "lastWatchedAtMs", "", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/Map;J)V", "getShowTmdbId", "()I", "getTitle", "()Ljava/lang/String;", "getYear", "getWatchedBySeason", "()Ljava/util/Map;", "getLastWatchedAtMs", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbShowWatchedProgress {
    public static final int $stable = 0;
    private final long lastWatchedAtMs;
    private final int showTmdbId;
    private final String title;
    private final Map<Integer, Set<Integer>> watchedBySeason;
    private final String year;

    /* JADX WARN: Multi-variable type inference failed */
    public MdbShowWatchedProgress(int i10, String str, String str2, Map<Integer, ? extends Set<Integer>> map, long j10) {
        this.showTmdbId = i10;
        this.title = str;
        this.year = str2;
        this.watchedBySeason = map;
        this.lastWatchedAtMs = j10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MdbShowWatchedProgress copy$default(MdbShowWatchedProgress mdbShowWatchedProgress, int i10, String str, String str2, Map map, long j10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = mdbShowWatchedProgress.showTmdbId;
        }
        if ((i11 & 2) != 0) {
            str = mdbShowWatchedProgress.title;
        }
        if ((i11 & 4) != 0) {
            str2 = mdbShowWatchedProgress.year;
        }
        if ((i11 & 8) != 0) {
            map = mdbShowWatchedProgress.watchedBySeason;
        }
        if ((i11 & 16) != 0) {
            j10 = mdbShowWatchedProgress.lastWatchedAtMs;
        }
        long j11 = j10;
        return mdbShowWatchedProgress.copy(i10, str, str2, map, j11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getShowTmdbId() {
        return this.showTmdbId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getYear() {
        return this.year;
    }

    public final Map<Integer, Set<Integer>> component4() {
        return this.watchedBySeason;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLastWatchedAtMs() {
        return this.lastWatchedAtMs;
    }

    public final MdbShowWatchedProgress copy(int showTmdbId, String title, String year, Map<Integer, ? extends Set<Integer>> watchedBySeason, long lastWatchedAtMs) {
        return new MdbShowWatchedProgress(showTmdbId, title, year, watchedBySeason, lastWatchedAtMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbShowWatchedProgress)) {
            return false;
        }
        MdbShowWatchedProgress mdbShowWatchedProgress = (MdbShowWatchedProgress) other;
        return this.showTmdbId == mdbShowWatchedProgress.showTmdbId && kotlin.jvm.internal.p.a(this.title, mdbShowWatchedProgress.title) && kotlin.jvm.internal.p.a(this.year, mdbShowWatchedProgress.year) && kotlin.jvm.internal.p.a(this.watchedBySeason, mdbShowWatchedProgress.watchedBySeason) && this.lastWatchedAtMs == mdbShowWatchedProgress.lastWatchedAtMs;
    }

    public final long getLastWatchedAtMs() {
        return this.lastWatchedAtMs;
    }

    public final int getShowTmdbId() {
        return this.showTmdbId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Map<Integer, Set<Integer>> getWatchedBySeason() {
        return this.watchedBySeason;
    }

    public final String getYear() {
        return this.year;
    }

    public int hashCode() {
        int iG = a2.g(this.watchedBySeason, androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.showTmdbId * 31, 31, this.title), 31, this.year), 31);
        long j10 = this.lastWatchedAtMs;
        return iG + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        int i10 = this.showTmdbId;
        String str = this.title;
        String str2 = this.year;
        Map<Integer, Set<Integer>> map = this.watchedBySeason;
        long j10 = this.lastWatchedAtMs;
        StringBuilder sbO = a2.o("MdbShowWatchedProgress(showTmdbId=", i10, ", title=", str, ", year=");
        sbO.append(str2);
        sbO.append(", watchedBySeason=");
        sbO.append(map);
        sbO.append(", lastWatchedAtMs=");
        return a0.c.j(j10, ")", sbO);
    }
}
