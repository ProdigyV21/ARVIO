package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/api/MdbScrobbleSeason;", "", "number", "", "episode", "Lcom/arflix/tv/data/api/MdbScrobbleEpisodeNumber;", "<init>", "(ILcom/arflix/tv/data/api/MdbScrobbleEpisodeNumber;)V", "getNumber", "()I", "getEpisode", "()Lcom/arflix/tv/data/api/MdbScrobbleEpisodeNumber;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbScrobbleSeason {
    public static final int $stable = 0;
    private final MdbScrobbleEpisodeNumber episode;
    private final int number;

    public MdbScrobbleSeason(int i10, MdbScrobbleEpisodeNumber mdbScrobbleEpisodeNumber) {
        this.number = i10;
        this.episode = mdbScrobbleEpisodeNumber;
    }

    public static /* synthetic */ MdbScrobbleSeason copy$default(MdbScrobbleSeason mdbScrobbleSeason, int i10, MdbScrobbleEpisodeNumber mdbScrobbleEpisodeNumber, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = mdbScrobbleSeason.number;
        }
        if ((i11 & 2) != 0) {
            mdbScrobbleEpisodeNumber = mdbScrobbleSeason.episode;
        }
        return mdbScrobbleSeason.copy(i10, mdbScrobbleEpisodeNumber);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MdbScrobbleEpisodeNumber getEpisode() {
        return this.episode;
    }

    public final MdbScrobbleSeason copy(int number, MdbScrobbleEpisodeNumber episode) {
        return new MdbScrobbleSeason(number, episode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbScrobbleSeason)) {
            return false;
        }
        MdbScrobbleSeason mdbScrobbleSeason = (MdbScrobbleSeason) other;
        return this.number == mdbScrobbleSeason.number && p.a(this.episode, mdbScrobbleSeason.episode);
    }

    public final MdbScrobbleEpisodeNumber getEpisode() {
        return this.episode;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        return this.episode.hashCode() + (this.number * 31);
    }

    public String toString() {
        return "MdbScrobbleSeason(number=" + this.number + ", episode=" + this.episode + ")";
    }
}
