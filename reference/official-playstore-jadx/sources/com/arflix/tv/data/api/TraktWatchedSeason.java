package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TraktWatchedSeason;", "", "number", "", "episodes", "", "Lcom/arflix/tv/data/api/TraktWatchedEpisode;", "<init>", "(ILjava/util/List;)V", "getNumber", "()I", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktWatchedSeason {
    public static final int $stable = 0;
    private final List<TraktWatchedEpisode> episodes;
    private final int number;

    public TraktWatchedSeason(int i10, List<TraktWatchedEpisode> list) {
        this.number = i10;
        this.episodes = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktWatchedSeason copy$default(TraktWatchedSeason traktWatchedSeason, int i10, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktWatchedSeason.number;
        }
        if ((i11 & 2) != 0) {
            list = traktWatchedSeason.episodes;
        }
        return traktWatchedSeason.copy(i10, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    public final List<TraktWatchedEpisode> component2() {
        return this.episodes;
    }

    public final TraktWatchedSeason copy(int number, List<TraktWatchedEpisode> episodes) {
        return new TraktWatchedSeason(number, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktWatchedSeason)) {
            return false;
        }
        TraktWatchedSeason traktWatchedSeason = (TraktWatchedSeason) other;
        return this.number == traktWatchedSeason.number && p.a(this.episodes, traktWatchedSeason.episodes);
    }

    public final List<TraktWatchedEpisode> getEpisodes() {
        return this.episodes;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        return this.episodes.hashCode() + (this.number * 31);
    }

    public String toString() {
        return "TraktWatchedSeason(number=" + this.number + ", episodes=" + this.episodes + ")";
    }
}
