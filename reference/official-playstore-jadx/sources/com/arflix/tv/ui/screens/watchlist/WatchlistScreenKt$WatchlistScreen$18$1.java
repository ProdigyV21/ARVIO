package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistScreen$18$1", f = "WatchlistScreen.kt", l = {398}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$WatchlistScreen$18$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<WatchlistFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableIntState $focusedSectionIndex$delegate;
    final /* synthetic */ boolean $isLibraryMode;
    final /* synthetic */ LazyListState $watchlistColumnState;
    final /* synthetic */ List<x> $watchlistSections;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WatchlistScreenKt$WatchlistScreen$18$1(boolean z, List<? extends x> list, LazyListState lazyListState, MutableState<WatchlistFocusZone> mutableState, MutableIntState mutableIntState, d7.d<? super WatchlistScreenKt$WatchlistScreen$18$1> dVar) {
        super(2, dVar);
        this.$isLibraryMode = z;
        this.$watchlistSections = list;
        this.$watchlistColumnState = lazyListState;
        this.$focusZone$delegate = mutableState;
        this.$focusedSectionIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$WatchlistScreen$18$1(this.$isLibraryMode, this.$watchlistSections, this.$watchlistColumnState, this.$focusZone$delegate, this.$focusedSectionIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!this.$isLibraryMode && WatchlistScreenKt.WatchlistScreen$lambda$15(this.$focusZone$delegate) == WatchlistFocusZone.CONTENT && !this.$watchlistSections.isEmpty()) {
                LazyListState lazyListState = this.$watchlistColumnState;
                int intValue = this.$focusedSectionIndex$delegate.getIntValue();
                this.label = 1;
                Object objAnimateScrollToItem$default = LazyListState.animateScrollToItem$default(lazyListState, intValue, 0, this, 2, null);
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
        return ((WatchlistScreenKt$WatchlistScreen$18$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
