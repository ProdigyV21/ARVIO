package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$LibraryResults$2$1", f = "WatchlistScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$LibraryResults$2$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $columns;
    final /* synthetic */ LazyGridState $gridState;
    final /* synthetic */ r7.a<t0> $onLoadMore;
    final /* synthetic */ HomeLibraryUiState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$LibraryResults$2$1(LazyGridState lazyGridState, HomeLibraryUiState homeLibraryUiState, int i10, r7.a<t0> aVar, d7.d<? super WatchlistScreenKt$LibraryResults$2$1> dVar) {
        super(2, dVar);
        this.$gridState = lazyGridState;
        this.$state = homeLibraryUiState;
        this.$columns = i10;
        this.$onLoadMore = aVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$LibraryResults$2$1(this.$gridState, this.$state, this.$columns, this.$onLoadMore, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) x.y0(this.$gridState.getLayoutInfo().getVisibleItemsInfo());
        if ((lazyGridItemInfo != null ? lazyGridItemInfo.getIndex() : -1) >= this.$state.getItems().size() - (this.$columns * 2)) {
            this.$onLoadMore.invoke();
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WatchlistScreenKt$LibraryResults$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
