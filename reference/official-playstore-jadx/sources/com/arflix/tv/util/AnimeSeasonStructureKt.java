package com.arflix.tv.util;

import com.arflix.tv.data.model.EpisodeIdentity;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a,\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0000¨\u0006\r"}, d2 = {"fallbackAdjacentEpisodeIdentity", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "current", "forward", "", "buildAnimeSeasonStructure", "Lcom/arflix/tv/util/AnimeSeasonStructure;", "tmdbSeasonEpisodeCounts", "", "", "providerSeasons", "", "Lcom/arflix/tv/util/AnimeProviderSeason;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AnimeSeasonStructureKt {
    /* JADX WARN: Removed duplicated region for block: B:60:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0243  */
    /* JADX WARN: Type inference failed for: r24v0 */
    /* JADX WARN: Type inference failed for: r24v4, types: [com.arflix.tv.util.AnimeSeasonStructure, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r24v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.arflix.tv.util.AnimeSeasonStructure buildAnimeSeasonStructure(java.util.Map<java.lang.Integer, java.lang.Integer> r28, java.util.List<com.arflix.tv.util.AnimeProviderSeason> r29) {
        /*
            Method dump skipped, instruction units count: 790
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeSeasonStructureKt.buildAnimeSeasonStructure(java.util.Map, java.util.List):com.arflix.tv.util.AnimeSeasonStructure");
    }

    public static final EpisodeIdentity fallbackAdjacentEpisodeIdentity(EpisodeIdentity episodeIdentity, boolean z) {
        int i10 = z ? 1 : -1;
        int displayEpisode = episodeIdentity.getDisplayEpisode() + i10;
        int tmdbEpisode = episodeIdentity.getTmdbEpisode() + i10;
        if (displayEpisode <= 0 || tmdbEpisode <= 0) {
            return null;
        }
        return new EpisodeIdentity(episodeIdentity.getDisplaySeason(), displayEpisode, episodeIdentity.getTmdbSeason(), tmdbEpisode, null, null, null, 112, null);
    }
}
