package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbMediaItem;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/TmdbMediaItem;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$categories$1", f = "MediaRepository.kt", l = {1682}, m = "invokeSuspend", v = 2)
public final class MediaRepository$getHomeCategoriesInternal$2$categories$1 extends f7.j implements r7.l<d7.d<? super List<? extends TmdbMediaItem>>, Object> {
    final /* synthetic */ ka.p0<List<TmdbMediaItem>> $trendingMovies;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public MediaRepository$getHomeCategoriesInternal$2$categories$1(ka.p0<? extends List<TmdbMediaItem>> p0Var, d7.d<? super MediaRepository$getHomeCategoriesInternal$2$categories$1> dVar) {
        super(1, dVar);
        this.$trendingMovies = p0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(d7.d<?> dVar) {
        return new MediaRepository$getHomeCategoriesInternal$2$categories$1(this.$trendingMovies, dVar);
    }

    @Override // r7.l
    public final Object invoke(d7.d<? super List<TmdbMediaItem>> dVar) {
        return ((MediaRepository$getHomeCategoriesInternal$2$categories$1) create(dVar)).invokeSuspend(t0.f22605a);
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
        ka.p0<List<TmdbMediaItem>> p0Var = this.$trendingMovies;
        this.label = 1;
        Object objO = p0Var.o(this);
        e7.a aVar = e7.a.f15033i;
        return objO == aVar ? aVar : objO;
    }
}
