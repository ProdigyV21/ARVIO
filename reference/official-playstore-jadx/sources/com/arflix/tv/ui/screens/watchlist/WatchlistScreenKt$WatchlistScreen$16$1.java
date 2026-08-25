package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.MutableIntState;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistScreen$16$1", f = "WatchlistScreen.kt", l = {388}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$WatchlistScreen$16$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $focusedItemIndex$delegate;
    final /* synthetic */ boolean $isLibraryMode;
    final /* synthetic */ LazyGridState $libraryGridState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$WatchlistScreen$16$1(boolean z, LazyGridState lazyGridState, MutableIntState mutableIntState, d7.d<? super WatchlistScreenKt$WatchlistScreen$16$1> dVar) {
        super(2, dVar);
        this.$isLibraryMode = z;
        this.$libraryGridState = lazyGridState;
        this.$focusedItemIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$WatchlistScreen$16$1(this.$isLibraryMode, this.$libraryGridState, this.$focusedItemIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.$focusedItemIndex$delegate.setIntValue(0);
            if (this.$isLibraryMode) {
                LazyGridState lazyGridState = this.$libraryGridState;
                this.label = 1;
                Object objScrollToItem$default = LazyGridState.scrollToItem$default(lazyGridState, 0, 0, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objScrollToItem$default == aVar) {
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
        return ((WatchlistScreenKt$WatchlistScreen$16$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
