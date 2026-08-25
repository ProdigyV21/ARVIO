package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.ui.screens.search.SearchViewModel;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$executeSmartSearch$1$items$1$1$a$1", f = "SearchViewModel.kt", l = {346}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$executeSmartSearch$1$items$1$1$a$1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends MediaItem>>, Object> {
    final /* synthetic */ SearchViewModel.SmartQuery $sq;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$executeSmartSearch$1$items$1$1$a$1(SearchViewModel searchViewModel, SearchViewModel.SmartQuery smartQuery, d7.d<? super SearchViewModel$executeSmartSearch$1$items$1$1$a$1> dVar) {
        super(2, dVar);
        this.this$0 = searchViewModel;
        this.$sq = smartQuery;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchViewModel$executeSmartSearch$1$items$1$1$a$1(this.this$0, this.$sq, dVar);
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
        String genreId = this.$sq.getGenreId();
        String sort = this.$sq.getSort();
        Integer minVotes = this.$sq.getMinVotes();
        this.label = 1;
        Object objDiscoverMovies$default = MediaRepository.discoverMovies$default(mediaRepository, genreId, sort, minVotes, 1, null, null, null, null, null, this, 496, null);
        e7.a aVar = e7.a.f15033i;
        return objDiscoverMovies$default == aVar ? aVar : objDiscoverMovies$default;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
        return ((SearchViewModel$executeSmartSearch$1$items$1$1$a$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
