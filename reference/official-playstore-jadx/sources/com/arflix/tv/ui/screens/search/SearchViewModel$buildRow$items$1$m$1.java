package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.MediaRepository;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$items$1$m$1", f = "SearchViewModel.kt", l = {184}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$buildRow$items$1$m$1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends MediaItem>>, Object> {
    final /* synthetic */ String $lang;
    final /* synthetic */ Integer $minVotes;
    final /* synthetic */ String $movieGenre;
    final /* synthetic */ int $page;
    final /* synthetic */ String $releaseDateGte;
    final /* synthetic */ String $releaseDateLte;
    final /* synthetic */ String $sort;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$buildRow$items$1$m$1(SearchViewModel searchViewModel, String str, String str2, Integer num, int i10, String str3, String str4, String str5, d7.d<? super SearchViewModel$buildRow$items$1$m$1> dVar) {
        super(2, dVar);
        this.this$0 = searchViewModel;
        this.$movieGenre = str;
        this.$sort = str2;
        this.$minVotes = num;
        this.$page = i10;
        this.$lang = str3;
        this.$releaseDateLte = str4;
        this.$releaseDateGte = str5;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchViewModel$buildRow$items$1$m$1(this.this$0, this.$movieGenre, this.$sort, this.$minVotes, this.$page, this.$lang, this.$releaseDateLte, this.$releaseDateGte, dVar);
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
        MediaRepository mediaRepository = this.this$0.mediaRepository;
        String str = this.$movieGenre;
        String str2 = this.$sort;
        Integer num = this.$minVotes;
        int i11 = this.$page;
        String str3 = this.$lang;
        String str4 = this.$releaseDateLte;
        String str5 = this.$releaseDateGte;
        this.label = 1;
        Object objDiscoverMovies$default = MediaRepository.discoverMovies$default(mediaRepository, str, str2, num, i11, str3, null, null, str4, str5, this, 96, null);
        e7.a aVar = e7.a.f15033i;
        return objDiscoverMovies$default == aVar ? aVar : objDiscoverMovies$default;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
        return ((SearchViewModel$buildRow$items$1$m$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
