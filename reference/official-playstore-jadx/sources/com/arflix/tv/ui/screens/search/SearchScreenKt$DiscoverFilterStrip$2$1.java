package com.arflix.tv.ui.screens.search;

import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListState;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchScreenKt$DiscoverFilterStrip$2$1", f = "SearchScreen.kt", l = {795}, m = "invokeSuspend", v = 2)
public final class SearchScreenKt$DiscoverFilterStrip$2$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<DiscoverQuickFilter> $filters;
    final /* synthetic */ int $focusedFilterIndex;
    final /* synthetic */ LazyListState $rowState;
    int I$0;
    int I$1;
    int I$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchScreenKt$DiscoverFilterStrip$2$1(int i10, List<DiscoverQuickFilter> list, LazyListState lazyListState, d7.d<? super SearchScreenKt$DiscoverFilterStrip$2$1> dVar) {
        super(2, dVar);
        this.$focusedFilterIndex = i10;
        this.$filters = list;
        this.$rowState = lazyListState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchScreenKt$DiscoverFilterStrip$2$1(this.$focusedFilterIndex, this.$filters, this.$rowState, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            int i11 = this.$focusedFilterIndex;
            int size = this.$filters.size() - 1;
            if (size < 0) {
                size = 0;
            }
            int iN = qb.d.n(i11, 0, size);
            int firstVisibleItemIndex = this.$rowState.getFirstVisibleItemIndex();
            LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) kotlin.collections.x.y0(this.$rowState.getLayoutInfo().getVisibleItemsInfo());
            int index = lazyListItemInfo != null ? lazyListItemInfo.getIndex() : firstVisibleItemIndex;
            if (iN < firstVisibleItemIndex || iN > index - 1) {
                LazyListState lazyListState = this.$rowState;
                this.I$0 = iN;
                this.I$1 = firstVisibleItemIndex;
                this.I$2 = index;
                this.label = 1;
                Object objAnimateScrollToItem$default = LazyListState.animateScrollToItem$default(lazyListState, iN, 0, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateScrollToItem$default == aVar) {
                    return aVar;
                }
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SearchScreenKt$DiscoverFilterStrip$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
