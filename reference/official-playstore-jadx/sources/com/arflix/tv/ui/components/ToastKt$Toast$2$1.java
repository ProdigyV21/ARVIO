package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.ToastKt$Toast$2$1", f = "Toast.kt", l = {ColorSpace.MaxId}, m = "invokeSuspend", v = 2)
public final class ToastKt$Toast$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ long $durationMs;
    final /* synthetic */ boolean $isVisible;
    final /* synthetic */ r7.a<x6.t0> $onDismiss;
    final /* synthetic */ MutableState<Boolean> $visible$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToastKt$Toast$2$1(boolean z, long j10, r7.a<x6.t0> aVar, MutableState<Boolean> mutableState, d7.d<? super ToastKt$Toast$2$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$durationMs = j10;
        this.$onDismiss = aVar;
        this.$visible$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new ToastKt$Toast$2$1(this.$isVisible, this.$durationMs, this.$onDismiss, this.$visible$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$isVisible) {
                ToastKt.Toast$lambda$3(this.$visible$delegate, true);
                long j10 = this.$durationMs;
                this.label = 1;
                Object objA = ka.s0.a(j10, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return x6.t0.f22605a;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        ToastKt.Toast$lambda$3(this.$visible$delegate, false);
        this.$onDismiss.invoke();
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((ToastKt$Toast$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
