package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$13$1", f = "HomeScreen.kt", l = {729}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$13$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $isMobile;
    final /* synthetic */ long $startupEffectsDelayMs;
    final /* synthetic */ MutableState<Boolean> $startupEffectsSettled$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeScreen$13$1(boolean z, long j10, MutableState<Boolean> mutableState, d7.d<? super HomeScreenKt$HomeScreen$13$1> dVar) {
        super(2, dVar);
        this.$isMobile = z;
        this.$startupEffectsDelayMs = j10;
        this.$startupEffectsSettled$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$13$1(this.$isMobile, this.$startupEffectsDelayMs, this.$startupEffectsSettled$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$isMobile) {
                HomeScreenKt.HomeScreen$lambda$27(this.$startupEffectsSettled$delegate, true);
                return t0Var;
            }
            HomeScreenKt.HomeScreen$lambda$27(this.$startupEffectsSettled$delegate, false);
            long j10 = this.$startupEffectsDelayMs;
            this.label = 1;
            Object objA = ka.s0.a(j10, this);
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
        HomeScreenKt.HomeScreen$lambda$27(this.$startupEffectsSettled$delegate, true);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$13$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
