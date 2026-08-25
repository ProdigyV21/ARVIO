package com.arflix.tv.util;

import androidx.compose.material3.d;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/util/AnimeProviderSeason;", "", "kitsuId", "", "tmdbSeason", "episodeCount", "<init>", "(ILjava/lang/Integer;I)V", "getKitsuId", "()I", "getTmdbSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodeCount", "component1", "component2", "component3", "copy", "(ILjava/lang/Integer;I)Lcom/arflix/tv/util/AnimeProviderSeason;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AnimeProviderSeason {
    public static final int $stable = 0;
    private final int episodeCount;
    private final int kitsuId;
    private final Integer tmdbSeason;

    public AnimeProviderSeason(int i10, Integer num, int i11) {
        this.kitsuId = i10;
        this.tmdbSeason = num;
        this.episodeCount = i11;
    }

    public static /* synthetic */ AnimeProviderSeason copy$default(AnimeProviderSeason animeProviderSeason, int i10, Integer num, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = animeProviderSeason.kitsuId;
        }
        if ((i12 & 2) != 0) {
            num = animeProviderSeason.tmdbSeason;
        }
        if ((i12 & 4) != 0) {
            i11 = animeProviderSeason.episodeCount;
        }
        return animeProviderSeason.copy(i10, num, i11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getKitsuId() {
        return this.kitsuId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTmdbSeason() {
        return this.tmdbSeason;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getEpisodeCount() {
        return this.episodeCount;
    }

    public final AnimeProviderSeason copy(int kitsuId, Integer tmdbSeason, int episodeCount) {
        return new AnimeProviderSeason(kitsuId, tmdbSeason, episodeCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnimeProviderSeason)) {
            return false;
        }
        AnimeProviderSeason animeProviderSeason = (AnimeProviderSeason) other;
        return this.kitsuId == animeProviderSeason.kitsuId && p.a(this.tmdbSeason, animeProviderSeason.tmdbSeason) && this.episodeCount == animeProviderSeason.episodeCount;
    }

    public final int getEpisodeCount() {
        return this.episodeCount;
    }

    public final int getKitsuId() {
        return this.kitsuId;
    }

    public final Integer getTmdbSeason() {
        return this.tmdbSeason;
    }

    public int hashCode() {
        int i10 = this.kitsuId * 31;
        Integer num = this.tmdbSeason;
        return ((i10 + (num == null ? 0 : num.hashCode())) * 31) + this.episodeCount;
    }

    public String toString() {
        int i10 = this.kitsuId;
        Integer num = this.tmdbSeason;
        int i11 = this.episodeCount;
        StringBuilder sb2 = new StringBuilder("AnimeProviderSeason(kitsuId=");
        sb2.append(i10);
        sb2.append(", tmdbSeason=");
        sb2.append(num);
        sb2.append(", episodeCount=");
        return d.j(i11, ")", sb2);
    }
}
