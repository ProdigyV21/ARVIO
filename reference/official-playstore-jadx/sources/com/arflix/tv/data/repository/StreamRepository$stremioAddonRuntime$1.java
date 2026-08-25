package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/model/StreamSource;", "addon", "Lcom/arflix/tv/data/model/Addon;", "request", "Lcom/arflix/tv/data/repository/MovieRuntimeRequest;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$stremioAddonRuntime$1", f = "StreamRepository.kt", l = {343}, m = "invokeSuspend", v = 2)
public final class StreamRepository$stremioAddonRuntime$1 extends f7.j implements r7.q<Addon, MovieRuntimeRequest, d7.d<? super List<? extends StreamSource>>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$stremioAddonRuntime$1(StreamRepository streamRepository, d7.d<? super StreamRepository$stremioAddonRuntime$1> dVar) {
        super(3, dVar);
        this.this$0 = streamRepository;
    }

    @Override // r7.q
    public final Object invoke(Addon addon, MovieRuntimeRequest movieRuntimeRequest, d7.d<? super List<StreamSource>> dVar) {
        StreamRepository$stremioAddonRuntime$1 streamRepository$stremioAddonRuntime$1 = new StreamRepository$stremioAddonRuntime$1(this.this$0, dVar);
        streamRepository$stremioAddonRuntime$1.L$0 = addon;
        streamRepository$stremioAddonRuntime$1.L$1 = movieRuntimeRequest;
        return streamRepository$stremioAddonRuntime$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Addon addon = (Addon) this.L$0;
        MovieRuntimeRequest movieRuntimeRequest = (MovieRuntimeRequest) this.L$1;
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
        String imdbId = movieRuntimeRequest.getImdbId();
        String title = movieRuntimeRequest.getTitle();
        Integer year = movieRuntimeRequest.getYear();
        this.L$0 = null;
        this.L$1 = null;
        this.label = 1;
        Object objFetchMovieStreamsFromAddon = streamRepository.fetchMovieStreamsFromAddon(addon, imdbId, title, year, this);
        e7.a aVar = e7.a.f15033i;
        return objFetchMovieStreamsFromAddon == aVar ? aVar : objFetchMovieStreamsFromAddon;
    }
}
