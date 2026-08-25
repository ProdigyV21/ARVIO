package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/model/StreamSource;", "addon", "Lcom/arflix/tv/data/model/Addon;", "request", "Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$stremioAddonRuntime$2", f = "StreamRepository.kt", l = {351}, m = "invokeSuspend", v = 2)
public final class StreamRepository$stremioAddonRuntime$2 extends f7.j implements r7.q<Addon, EpisodeRuntimeRequest, d7.d<? super List<? extends StreamSource>>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$stremioAddonRuntime$2(StreamRepository streamRepository, d7.d<? super StreamRepository$stremioAddonRuntime$2> dVar) {
        super(3, dVar);
        this.this$0 = streamRepository;
    }

    @Override // r7.q
    public final Object invoke(Addon addon, EpisodeRuntimeRequest episodeRuntimeRequest, d7.d<? super List<StreamSource>> dVar) {
        StreamRepository$stremioAddonRuntime$2 streamRepository$stremioAddonRuntime$2 = new StreamRepository$stremioAddonRuntime$2(this.this$0, dVar);
        streamRepository$stremioAddonRuntime$2.L$0 = addon;
        streamRepository$stremioAddonRuntime$2.L$1 = episodeRuntimeRequest;
        return streamRepository$stremioAddonRuntime$2.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Addon addon = (Addon) this.L$0;
        EpisodeRuntimeRequest episodeRuntimeRequest = (EpisodeRuntimeRequest) this.L$1;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        StreamRepository streamRepository = this.this$0;
        String imdbId = episodeRuntimeRequest.getImdbId();
        int season = episodeRuntimeRequest.getSeason();
        int episode = episodeRuntimeRequest.getEpisode();
        Integer tmdbId = episodeRuntimeRequest.getTmdbId();
        Integer tvdbId = episodeRuntimeRequest.getTvdbId();
        List<Integer> genreIds = episodeRuntimeRequest.getGenreIds();
        String originalLanguage = episodeRuntimeRequest.getOriginalLanguage();
        String title = episodeRuntimeRequest.getTitle();
        String animeQueryOverride = episodeRuntimeRequest.getAnimeQueryOverride();
        String airDate = episodeRuntimeRequest.getAirDate();
        this.L$0 = null;
        this.L$1 = null;
        this.label = 1;
        Object objFetchEpisodeStreamsFromAddon = streamRepository.fetchEpisodeStreamsFromAddon(addon, imdbId, season, episode, tmdbId, tvdbId, genreIds, originalLanguage, title, animeQueryOverride, airDate, this);
        e7.a aVar = e7.a.f15033i;
        return objFetchEpisodeStreamsFromAddon == aVar ? aVar : objFetchEpisodeStreamsFromAddon;
    }
}
