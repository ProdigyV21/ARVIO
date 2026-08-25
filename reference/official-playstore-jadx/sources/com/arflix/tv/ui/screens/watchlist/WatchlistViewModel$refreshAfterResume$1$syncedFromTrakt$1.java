package com.arflix.tv.ui.screens.watchlist;

import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$refreshAfterResume$1$syncedFromTrakt$1", f = "WatchlistViewModel.kt", l = {1114}, m = "invokeSuspend", v = 2)
public final class WatchlistViewModel$refreshAfterResume$1$syncedFromTrakt$1 extends f7.j implements p<k0, d7.d<? super Boolean>, Object> {
    int label;
    final /* synthetic */ WatchlistViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistViewModel$refreshAfterResume$1$syncedFromTrakt$1(WatchlistViewModel watchlistViewModel, d7.d<? super WatchlistViewModel$refreshAfterResume$1$syncedFromTrakt$1> dVar) {
        super(2, dVar);
        this.this$0 = watchlistViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistViewModel$refreshAfterResume$1$syncedFromTrakt$1(this.this$0, dVar);
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
        WatchlistViewModel watchlistViewModel = this.this$0;
        this.label = 1;
        Object objSyncTraktWatchlistSuspend = watchlistViewModel.syncTraktWatchlistSuspend(this);
        e7.a aVar = e7.a.f15033i;
        return objSyncTraktWatchlistSuspend == aVar ? aVar : objSyncTraktWatchlistSuspend;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super Boolean> dVar) {
        return ((WatchlistViewModel$refreshAfterResume$1$syncedFromTrakt$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
