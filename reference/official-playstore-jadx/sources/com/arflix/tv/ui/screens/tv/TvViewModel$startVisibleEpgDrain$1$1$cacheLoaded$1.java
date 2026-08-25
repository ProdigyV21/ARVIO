package com.arflix.tv.ui.screens.tv;

import java.util.LinkedHashSet;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startVisibleEpgDrain$1$1$cacheLoaded$1", f = "TvViewModel.kt", l = {1651}, m = "invokeSuspend", v = 2)
public final class TvViewModel$startVisibleEpgDrain$1$1$cacheLoaded$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
    final /* synthetic */ LinkedHashSet<String> $batchSet;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$startVisibleEpgDrain$1$1$cacheLoaded$1(TvViewModel tvViewModel, LinkedHashSet<String> linkedHashSet, d7.d<? super TvViewModel$startVisibleEpgDrain$1$1$cacheLoaded$1> dVar) {
        super(2, dVar);
        this.this$0 = tvViewModel;
        this.$batchSet = linkedHashSet;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvViewModel$startVisibleEpgDrain$1$1$cacheLoaded$1(this.this$0, this.$batchSet, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            TvViewModel tvViewModel = this.this$0;
            LinkedHashSet<String> linkedHashSet = this.$batchSet;
            this.label = 1;
            Object objRefreshGuideFromCache = tvViewModel.refreshGuideFromCache(linkedHashSet, this);
            e7.a aVar = e7.a.f15033i;
            if (objRefreshGuideFromCache == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return Boolean.TRUE;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
        return ((TvViewModel$startVisibleEpgDrain$1$1$cacheLoaded$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
