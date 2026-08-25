package com.arflix.tv.ui.screens.tv.live;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1$providerResults$1$1", f = "SearchOverlay.kt", l = {113}, m = "invokeSuspend", v = 2)
public final class SearchOverlayKt$SearchOverlay$3$1$providerResults$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends EnrichedChannel>>, Object> {
    final /* synthetic */ r7.p<String, d7.d<? super List<EnrichedChannel>>, Object> $provider;
    final /* synthetic */ String $q;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchOverlayKt$SearchOverlay$3$1$providerResults$1$1(r7.p<? super String, ? super d7.d<? super List<EnrichedChannel>>, ? extends Object> pVar, String str, d7.d<? super SearchOverlayKt$SearchOverlay$3$1$providerResults$1$1> dVar) {
        super(2, dVar);
        this.$provider = pVar;
        this.$q = str;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SearchOverlayKt$SearchOverlay$3$1$providerResults$1$1(this.$provider, this.$q, dVar);
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
        r7.p<String, d7.d<? super List<EnrichedChannel>>, Object> pVar = this.$provider;
        String str = this.$q;
        this.label = 1;
        Object objInvoke = pVar.invoke(str, this);
        e7.a aVar = e7.a.f15033i;
        return objInvoke == aVar ? aVar : objInvoke;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<EnrichedChannel>> dVar) {
        return ((SearchOverlayKt$SearchOverlay$3$1$providerResults$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
