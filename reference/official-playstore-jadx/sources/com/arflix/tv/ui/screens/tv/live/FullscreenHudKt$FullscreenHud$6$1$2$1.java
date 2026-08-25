package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.FullscreenHudKt$FullscreenHud$6$1$2$1", f = "FullscreenHud.kt", l = {}, m = "invokeSuspend", v = 2)
public final class FullscreenHudKt$FullscreenHud$6$1$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ long $currentElapsedShowMs;
    final /* synthetic */ MutableState<Long> $frozenElapsedMs$delegate;
    final /* synthetic */ boolean $isBuffering;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullscreenHudKt$FullscreenHud$6$1$2$1(boolean z, long j10, MutableState<Long> mutableState, d7.d<? super FullscreenHudKt$FullscreenHud$6$1$2$1> dVar) {
        super(2, dVar);
        this.$isBuffering = z;
        this.$currentElapsedShowMs = j10;
        this.$frozenElapsedMs$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new FullscreenHudKt$FullscreenHud$6$1$2$1(this.$isBuffering, this.$currentElapsedShowMs, this.$frozenElapsedMs$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (!this.$isBuffering) {
            this.$frozenElapsedMs$delegate.setValue(null);
        } else if (FullscreenHudKt.FullscreenHud$lambda$21$0$2(this.$frozenElapsedMs$delegate) == null) {
            this.$frozenElapsedMs$delegate.setValue(new Long(this.$currentElapsedShowMs));
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((FullscreenHudKt$FullscreenHud$6$1$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
