package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import ka.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$10$1", f = "TvScreen.kt", l = {329}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$10$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $isFastNavigating$delegate;
    final /* synthetic */ MutableLongState $lastNavigationAt$delegate;
    long J$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$10$1(MutableLongState mutableLongState, MutableState<Boolean> mutableState, d7.d<? super TvScreenKt$TvScreen$10$1> dVar) {
        super(2, dVar);
        this.$lastNavigationAt$delegate = mutableLongState;
        this.$isFastNavigating$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$10$1(this.$lastNavigationAt$delegate, this.$isFastNavigating$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        long j10;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            long longValue = this.$lastNavigationAt$delegate.getLongValue();
            if (longValue <= 0) {
                TvScreenKt.TvScreen$lambda$51(this.$isFastNavigating$delegate, false);
                return t0Var;
            }
            TvScreenKt.TvScreen$lambda$51(this.$isFastNavigating$delegate, true);
            this.J$0 = longValue;
            this.label = 1;
            Object objA = s0.a(180L, this);
            e7.a aVar = e7.a.f15033i;
            if (objA == aVar) {
                return aVar;
            }
            j10 = longValue;
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j10 = this.J$0;
            k2.c.G(obj);
        }
        if (this.$lastNavigationAt$delegate.getLongValue() == j10) {
            TvScreenKt.TvScreen$lambda$51(this.$isFastNavigating$delegate, false);
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$10$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
