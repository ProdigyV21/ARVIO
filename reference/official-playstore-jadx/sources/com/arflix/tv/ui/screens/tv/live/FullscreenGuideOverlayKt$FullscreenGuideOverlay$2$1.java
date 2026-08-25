package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableLongState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt$FullscreenGuideOverlay$2$1", f = "FullscreenGuideOverlay.kt", l = {115}, m = "invokeSuspend", v = 2)
public final class FullscreenGuideOverlayKt$FullscreenGuideOverlay$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableLongState $nowMillis$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullscreenGuideOverlayKt$FullscreenGuideOverlay$2$1(MutableLongState mutableLongState, d7.d<? super FullscreenGuideOverlayKt$FullscreenGuideOverlay$2$1> dVar) {
        super(2, dVar);
        this.$nowMillis$delegate = mutableLongState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new FullscreenGuideOverlayKt$FullscreenGuideOverlay$2$1(this.$nowMillis$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object objA;
        e7.a aVar;
        int i10 = this.label;
        if (i10 != 0 && i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        do {
            this.$nowMillis$delegate.setLongValue(System.currentTimeMillis());
            this.label = 1;
            objA = ka.s0.a(30000L, this);
            aVar = e7.a.f15033i;
        } while (objA != aVar);
        return aVar;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((FullscreenGuideOverlayKt$FullscreenGuideOverlay$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
