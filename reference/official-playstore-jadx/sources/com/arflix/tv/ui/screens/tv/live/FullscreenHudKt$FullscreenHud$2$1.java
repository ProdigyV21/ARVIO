package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.FullscreenHudKt$FullscreenHud$2$1", f = "FullscreenHud.kt", l = {118}, m = "invokeSuspend", v = 2)
public final class FullscreenHudKt$FullscreenHud$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableLongState $lastPoke$delegate;
    final /* synthetic */ r7.l<Boolean, x6.t0> $onVisibilityChanged;
    final /* synthetic */ MutableState<Boolean> $visible$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FullscreenHudKt$FullscreenHud$2$1(r7.l<? super Boolean, x6.t0> lVar, MutableState<Boolean> mutableState, MutableLongState mutableLongState, d7.d<? super FullscreenHudKt$FullscreenHud$2$1> dVar) {
        super(2, dVar);
        this.$onVisibilityChanged = lVar;
        this.$visible$delegate = mutableState;
        this.$lastPoke$delegate = mutableLongState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new FullscreenHudKt$FullscreenHud$2$1(this.$onVisibilityChanged, this.$visible$delegate, this.$lastPoke$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            FullscreenHudKt.FullscreenHud$lambda$2(this.$visible$delegate, true);
            r7.l<Boolean, x6.t0> lVar = this.$onVisibilityChanged;
            if (lVar != null) {
                lVar.invoke(Boolean.TRUE);
            }
            this.$lastPoke$delegate.setLongValue(System.currentTimeMillis());
            this.label = 1;
            Object objA = ka.s0.a(5000L, this);
            e7.a aVar = e7.a.f15033i;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        if (System.currentTimeMillis() - this.$lastPoke$delegate.getLongValue() >= 5000) {
            FullscreenHudKt.FullscreenHud$lambda$2(this.$visible$delegate, false);
            r7.l<Boolean, x6.t0> lVar2 = this.$onVisibilityChanged;
            if (lVar2 != null) {
                lVar2.invoke(Boolean.FALSE);
            }
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((FullscreenHudKt$FullscreenHud$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
