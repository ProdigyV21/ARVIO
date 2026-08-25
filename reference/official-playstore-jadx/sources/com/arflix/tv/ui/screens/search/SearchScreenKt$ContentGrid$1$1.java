package com.arflix.tv.ui.screens.search;

import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchScreenKt$ContentGrid$1$1", f = "SearchScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SearchScreenKt$ContentGrid$1$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ LazyGridState $gridState;
    final /* synthetic */ List<MediaItem> $items;
    final /* synthetic */ r7.a<t0> $onLoadMore;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchScreenKt$ContentGrid$1$1(LazyGridState lazyGridState, List<MediaItem> list, r7.a<t0> aVar, d7.d<? super SearchScreenKt$ContentGrid$1$1> dVar) {
        super(2, dVar);
        this.$gridState = lazyGridState;
        this.$items = list;
        this.$onLoadMore = aVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchScreenKt$ContentGrid$1$1(this.$gridState, this.$items, this.$onLoadMore, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) kotlin.collections.x.y0(this.$gridState.getLayoutInfo().getVisibleItemsInfo());
        int index = lazyGridItemInfo != null ? lazyGridItemInfo.getIndex() : 0;
        if (!this.$items.isEmpty() && index >= this.$items.size() - 8) {
            this.$onLoadMore.invoke();
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SearchScreenKt$ContentGrid$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
