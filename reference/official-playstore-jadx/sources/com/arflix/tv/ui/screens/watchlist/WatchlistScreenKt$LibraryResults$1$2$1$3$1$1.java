package com.arflix.tv.ui.screens.watchlist;

import com.arflix.tv.data.model.MediaItem;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$LibraryResults$1$2$1$3$1$1", f = "WatchlistScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$LibraryResults$1$2$1$3$1$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MediaItem $item;
    final /* synthetic */ r7.l<MediaItem, t0> $onItemVisible;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WatchlistScreenKt$LibraryResults$1$2$1$3$1$1(r7.l<? super MediaItem, t0> lVar, MediaItem mediaItem, d7.d<? super WatchlistScreenKt$LibraryResults$1$2$1$3$1$1> dVar) {
        super(2, dVar);
        this.$onItemVisible = lVar;
        this.$item = mediaItem;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$LibraryResults$1$2$1$3$1$1(this.$onItemVisible, this.$item, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.$onItemVisible.invoke(this.$item);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WatchlistScreenKt$LibraryResults$1$2$1$3$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
