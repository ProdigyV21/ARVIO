package com.arflix.tv.ui.screens.watchlist;

import com.arflix.tv.data.model.MediaItem;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.s;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0004\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "Lx6/x;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$fetchLogos$1$resolved$1", f = "WatchlistViewModel.kt", l = {MediaError.DetailedErrorCode.MEDIA_ERROR_MESSAGE}, m = "invokeSuspend", v = 2)
public final class WatchlistViewModel$fetchLogos$1$resolved$1 extends f7.j implements p<k0, d7.d<? super List<? extends x>>, Object> {
    final /* synthetic */ ua.f $limiter;
    final /* synthetic */ List<MediaItem> $pending;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WatchlistViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistViewModel$fetchLogos$1$resolved$1(List<MediaItem> list, ua.f fVar, WatchlistViewModel watchlistViewModel, d7.d<? super WatchlistViewModel$fetchLogos$1$resolved$1> dVar) {
        super(2, dVar);
        this.$pending = list;
        this.$limiter = fVar;
        this.this$0 = watchlistViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        WatchlistViewModel$fetchLogos$1$resolved$1 watchlistViewModel$fetchLogos$1$resolved$1 = new WatchlistViewModel$fetchLogos$1$resolved$1(this.$pending, this.$limiter, this.this$0, dVar);
        watchlistViewModel$fetchLogos$1$resolved$1.L$0 = obj;
        return watchlistViewModel$fetchLogos$1$resolved$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        k0 k0Var = (k0) this.L$0;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        List<MediaItem> list = this.$pending;
        ua.f fVar = this.$limiter;
        WatchlistViewModel watchlistViewModel = this.this$0;
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(m0.e(3, null, k0Var, new WatchlistViewModel$fetchLogos$1$resolved$1$1$1(fVar, (MediaItem) it.next(), watchlistViewModel, null)));
        }
        this.L$0 = null;
        this.label = 1;
        Object objF = m0.f(arrayList, this);
        e7.a aVar = e7.a.f15033i;
        return objF == aVar ? aVar : objF;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<x>> dVar) {
        return ((WatchlistViewModel$fetchLogos$1$resolved$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
