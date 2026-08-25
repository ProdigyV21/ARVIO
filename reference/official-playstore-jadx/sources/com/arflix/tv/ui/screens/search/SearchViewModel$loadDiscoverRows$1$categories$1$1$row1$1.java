package com.arflix.tv.ui.screens.search;

import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.model.Category;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/Category;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$categories$1$1$row1$1", f = "SearchViewModel.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$loadDiscoverRows$1$categories$1$1$row1$1 extends f7.j implements r7.p<k0, d7.d<? super Category>, Object> {
    final /* synthetic */ String $genre;
    final /* synthetic */ boolean $isAnime;
    final /* synthetic */ String $lang;
    final /* synthetic */ String $today;
    final /* synthetic */ DiscoverType $type;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$loadDiscoverRows$1$categories$1$1$row1$1(SearchViewModel searchViewModel, DiscoverType discoverType, String str, String str2, boolean z, String str3, d7.d<? super SearchViewModel$loadDiscoverRows$1$categories$1$1$row1$1> dVar) {
        super(2, dVar);
        this.this$0 = searchViewModel;
        this.$type = discoverType;
        this.$genre = str;
        this.$lang = str2;
        this.$isAnime = z;
        this.$today = str3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchViewModel$loadDiscoverRows$1$categories$1$1$row1$1(this.this$0, this.$type, this.$genre, this.$lang, this.$isAnime, this.$today, dVar);
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
        SearchViewModel searchViewModel = this.this$0;
        DiscoverType discoverType = this.$type;
        String str = this.$genre;
        Integer num = new Integer(50);
        String str2 = this.$lang;
        boolean z = this.$isAnime;
        String str3 = this.$today;
        this.label = 1;
        Object objBuildRow$default = SearchViewModel.buildRow$default(searchViewModel, "Trending", discoverType, str, "popularity.desc", num, str2, z, 1, null, str3, this, 256, null);
        e7.a aVar = e7.a.f15033i;
        return objBuildRow$default == aVar ? aVar : objBuildRow$default;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super Category> dVar) {
        return ((SearchViewModel$loadDiscoverRows$1$categories$1$1$row1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
