package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.runtime.MutableIntState;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistScreen$17$1", f = "WatchlistScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$WatchlistScreen$17$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $focusedItemIndex$delegate;
    final /* synthetic */ MutableIntState $focusedSectionIndex$delegate;
    final /* synthetic */ List<x> $watchlistSections;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WatchlistScreenKt$WatchlistScreen$17$1(List<? extends x> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super WatchlistScreenKt$WatchlistScreen$17$1> dVar) {
        super(2, dVar);
        this.$watchlistSections = list;
        this.$focusedSectionIndex$delegate = mutableIntState;
        this.$focusedItemIndex$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$WatchlistScreen$17$1(this.$watchlistSections, this.$focusedSectionIndex$delegate, this.$focusedItemIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (!this.$watchlistSections.isEmpty() && this.$focusedSectionIndex$delegate.getIntValue() >= this.$watchlistSections.size()) {
            this.$focusedSectionIndex$delegate.setIntValue(0);
            this.$focusedItemIndex$delegate.setIntValue(0);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WatchlistScreenKt$WatchlistScreen$17$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
