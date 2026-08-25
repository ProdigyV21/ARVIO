package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J9\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/TraktProgressSeason;", "", "number", "", "aired", "completed", "episodes", "", "Lcom/arflix/tv/data/api/TraktProgressEpisode;", "<init>", "(IIILjava/util/List;)V", "getNumber", "()I", "getAired", "getCompleted", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktProgressSeason {
    public static final int $stable = 0;
    private final int aired;
    private final int completed;
    private final List<TraktProgressEpisode> episodes;
    private final int number;

    public TraktProgressSeason(int i10, int i11, int i12, List<TraktProgressEpisode> list) {
        this.number = i10;
        this.aired = i11;
        this.completed = i12;
        this.episodes = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktProgressSeason copy$default(TraktProgressSeason traktProgressSeason, int i10, int i11, int i12, List list, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = traktProgressSeason.number;
        }
        if ((i13 & 2) != 0) {
            i11 = traktProgressSeason.aired;
        }
        if ((i13 & 4) != 0) {
            i12 = traktProgressSeason.completed;
        }
        if ((i13 & 8) != 0) {
            list = traktProgressSeason.episodes;
        }
        return traktProgressSeason.copy(i10, i11, i12, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getAired() {
        return this.aired;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCompleted() {
        return this.completed;
    }

    public final List<TraktProgressEpisode> component4() {
        return this.episodes;
    }

    public final TraktProgressSeason copy(int number, int aired, int completed, List<TraktProgressEpisode> episodes) {
        return new TraktProgressSeason(number, aired, completed, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktProgressSeason)) {
            return false;
        }
        TraktProgressSeason traktProgressSeason = (TraktProgressSeason) other;
        return this.number == traktProgressSeason.number && this.aired == traktProgressSeason.aired && this.completed == traktProgressSeason.completed && p.a(this.episodes, traktProgressSeason.episodes);
    }

    public final int getAired() {
        return this.aired;
    }

    public final int getCompleted() {
        return this.completed;
    }

    public final List<TraktProgressEpisode> getEpisodes() {
        return this.episodes;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        int i10 = ((((this.number * 31) + this.aired) * 31) + this.completed) * 31;
        List<TraktProgressEpisode> list = this.episodes;
        return i10 + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        int i10 = this.number;
        int i11 = this.aired;
        int i12 = this.completed;
        List<TraktProgressEpisode> list = this.episodes;
        StringBuilder sbV = androidx.compose.foundation.c.v("TraktProgressSeason(number=", i10, ", aired=", i11, ", completed=");
        sbV.append(i12);
        sbV.append(", episodes=");
        sbV.append(list);
        sbV.append(")");
        return sbV.toString();
    }
}
