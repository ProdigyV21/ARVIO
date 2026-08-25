package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/arflix/tv/data/api/TraktShowProgress;", "", "aired", "", "completed", "lastWatchedAt", "", "resetAt", "nextEpisode", "Lcom/arflix/tv/data/api/TraktNextEpisode;", "seasons", "", "Lcom/arflix/tv/data/api/TraktProgressSeason;", "<init>", "(IILjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktNextEpisode;Ljava/util/List;)V", "getAired", "()I", "getCompleted", "getLastWatchedAt", "()Ljava/lang/String;", "getResetAt", "getNextEpisode", "()Lcom/arflix/tv/data/api/TraktNextEpisode;", "getSeasons", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktShowProgress {
    public static final int $stable = 0;
    private final int aired;
    private final int completed;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;

    @SerializedName("next_episode")
    private final TraktNextEpisode nextEpisode;

    @SerializedName("reset_at")
    private final String resetAt;
    private final List<TraktProgressSeason> seasons;

    public TraktShowProgress(int i10, int i11, String str, String str2, TraktNextEpisode traktNextEpisode, List<TraktProgressSeason> list) {
        this.aired = i10;
        this.completed = i11;
        this.lastWatchedAt = str;
        this.resetAt = str2;
        this.nextEpisode = traktNextEpisode;
        this.seasons = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktShowProgress copy$default(TraktShowProgress traktShowProgress, int i10, int i11, String str, String str2, TraktNextEpisode traktNextEpisode, List list, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = traktShowProgress.aired;
        }
        if ((i12 & 2) != 0) {
            i11 = traktShowProgress.completed;
        }
        if ((i12 & 4) != 0) {
            str = traktShowProgress.lastWatchedAt;
        }
        if ((i12 & 8) != 0) {
            str2 = traktShowProgress.resetAt;
        }
        if ((i12 & 16) != 0) {
            traktNextEpisode = traktShowProgress.nextEpisode;
        }
        if ((i12 & 32) != 0) {
            list = traktShowProgress.seasons;
        }
        TraktNextEpisode traktNextEpisode2 = traktNextEpisode;
        List list2 = list;
        return traktShowProgress.copy(i10, i11, str, str2, traktNextEpisode2, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAired() {
        return this.aired;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getCompleted() {
        return this.completed;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getResetAt() {
        return this.resetAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TraktNextEpisode getNextEpisode() {
        return this.nextEpisode;
    }

    public final List<TraktProgressSeason> component6() {
        return this.seasons;
    }

    public final TraktShowProgress copy(int aired, int completed, String lastWatchedAt, String resetAt, TraktNextEpisode nextEpisode, List<TraktProgressSeason> seasons) {
        return new TraktShowProgress(aired, completed, lastWatchedAt, resetAt, nextEpisode, seasons);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktShowProgress)) {
            return false;
        }
        TraktShowProgress traktShowProgress = (TraktShowProgress) other;
        return this.aired == traktShowProgress.aired && this.completed == traktShowProgress.completed && p.a(this.lastWatchedAt, traktShowProgress.lastWatchedAt) && p.a(this.resetAt, traktShowProgress.resetAt) && p.a(this.nextEpisode, traktShowProgress.nextEpisode) && p.a(this.seasons, traktShowProgress.seasons);
    }

    public final int getAired() {
        return this.aired;
    }

    public final int getCompleted() {
        return this.completed;
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final TraktNextEpisode getNextEpisode() {
        return this.nextEpisode;
    }

    public final String getResetAt() {
        return this.resetAt;
    }

    public final List<TraktProgressSeason> getSeasons() {
        return this.seasons;
    }

    public int hashCode() {
        int i10 = ((this.aired * 31) + this.completed) * 31;
        String str = this.lastWatchedAt;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.resetAt;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        TraktNextEpisode traktNextEpisode = this.nextEpisode;
        int iHashCode3 = (iHashCode2 + (traktNextEpisode == null ? 0 : traktNextEpisode.hashCode())) * 31;
        List<TraktProgressSeason> list = this.seasons;
        return iHashCode3 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.aired;
        int i11 = this.completed;
        String str = this.lastWatchedAt;
        String str2 = this.resetAt;
        TraktNextEpisode traktNextEpisode = this.nextEpisode;
        List<TraktProgressSeason> list = this.seasons;
        StringBuilder sbV = androidx.compose.foundation.c.v("TraktShowProgress(aired=", i10, ", completed=", i11, ", lastWatchedAt=");
        y.a.i(sbV, str, ", resetAt=", str2, ", nextEpisode=");
        sbV.append(traktNextEpisode);
        sbV.append(", seasons=");
        sbV.append(list);
        sbV.append(")");
        return sbV.toString();
    }
}
