package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TmdbMovieDetails;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/api/TmdbMovieDetails;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/api/TmdbMovieDetails;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getMovieDetails$item$1$detailsDeferred$1", f = "MediaRepository.kt", l = {2855}, m = "invokeSuspend", v = 2)
public final class MediaRepository$getMovieDetails$item$1$detailsDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super TmdbMovieDetails>, Object> {
    final /* synthetic */ int $movieId;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$getMovieDetails$item$1$detailsDeferred$1(MediaRepository mediaRepository, int i10, d7.d<? super MediaRepository$getMovieDetails$item$1$detailsDeferred$1> dVar) {
        super(2, dVar);
        this.this$0 = mediaRepository;
        this.$movieId = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MediaRepository$getMovieDetails$item$1$detailsDeferred$1(this.this$0, this.$movieId, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        TmdbApi tmdbApi = this.this$0.tmdbApi;
        int i11 = this.$movieId;
        String str = this.this$0.apiKey;
        String contentLanguage = this.this$0.getContentLanguage();
        this.label = 1;
        Object movieDetails = tmdbApi.getMovieDetails(i11, str, contentLanguage, this);
        e7.a aVar = e7.a.f15033i;
        return movieDetails == aVar ? aVar : movieDetails;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super TmdbMovieDetails> dVar) {
        return ((MediaRepository$getMovieDetails$item$1$detailsDeferred$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
