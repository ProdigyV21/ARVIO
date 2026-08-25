package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.MediaItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.h0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$search$1$logos$1", f = "SearchViewModel.kt", l = {306}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$search$1$logos$1 extends f7.j implements r7.p<k0, d7.d<? super Map<String, ? extends String>>, Object> {
    final /* synthetic */ List<MediaItem> $top;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$search$1$logos$1(List<MediaItem> list, SearchViewModel searchViewModel, d7.d<? super SearchViewModel$search$1$logos$1> dVar) {
        super(2, dVar);
        this.$top = list;
        this.this$0 = searchViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        SearchViewModel$search$1$logos$1 searchViewModel$search$1$logos$1 = new SearchViewModel$search$1$logos$1(this.$top, this.this$0, dVar);
        searchViewModel$search$1$logos$1.L$0 = obj;
        return searchViewModel$search$1$logos$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        k0 k0Var = (k0) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            List<MediaItem> list = this.$top;
            SearchViewModel searchViewModel = this.this$0;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(m0.e(3, null, k0Var, new SearchViewModel$search$1$logos$1$1$1((MediaItem) it.next(), searchViewModel, null)));
            }
            this.L$0 = null;
            this.label = 1;
            obj = m0.f(arrayList, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return h0.A0(kotlin.collections.x.k0((Iterable) obj));
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super Map<String, String>> dVar) {
        return ((SearchViewModel$search$1$logos$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
