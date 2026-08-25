package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableState;
import ka.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$34$4$1$1", f = "TvScreen.kt", l = {1360}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$34$4$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<Long> $fullscreenOverlayTrigger$delegate;
    final /* synthetic */ MutableState<Boolean> $showFullscreenOverlay$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$34$4$1$1(MutableState<Boolean> mutableState, MutableState<Long> mutableState2, d7.d<? super TvScreenKt$TvScreen$34$4$1$1> dVar) {
        super(2, dVar);
        this.$showFullscreenOverlay$delegate = mutableState;
        this.$fullscreenOverlayTrigger$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$34$4$1$1(this.$showFullscreenOverlay$delegate, this.$fullscreenOverlayTrigger$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (TvScreenKt.TvScreen$lambda$32(this.$showFullscreenOverlay$delegate) && TvScreenKt.TvScreen$lambda$35(this.$fullscreenOverlayTrigger$delegate) > 0) {
                this.label = 1;
                Object objA = s0.a(5000L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0.f22605a;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        TvScreenKt.TvScreen$lambda$33(this.$showFullscreenOverlay$delegate, false);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$34$4$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
