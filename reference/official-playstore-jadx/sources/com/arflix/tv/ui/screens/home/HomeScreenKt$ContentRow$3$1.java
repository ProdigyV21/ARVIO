package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableIntState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$3$1", f = "HomeScreen.kt", l = {3515}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$ContentRow$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $featuredExpandedForIndex$delegate;
    final /* synthetic */ long $featuredTrailerDelayMs;
    final /* synthetic */ int $focusedItemIndex;
    final /* synthetic */ boolean $hasFeaturedCard;
    final /* synthetic */ boolean $isCurrentRow;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$ContentRow$3$1(boolean z, boolean z5, int i10, long j10, MutableIntState mutableIntState, d7.d<? super HomeScreenKt$ContentRow$3$1> dVar) {
        super(2, dVar);
        this.$hasFeaturedCard = z;
        this.$isCurrentRow = z5;
        this.$focusedItemIndex = i10;
        this.$featuredTrailerDelayMs = j10;
        this.$featuredExpandedForIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$ContentRow$3$1(this.$hasFeaturedCard, this.$isCurrentRow, this.$focusedItemIndex, this.$featuredTrailerDelayMs, this.$featuredExpandedForIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.$featuredExpandedForIndex$delegate.setIntValue(-1);
            if (this.$hasFeaturedCard && this.$isCurrentRow && this.$focusedItemIndex >= 0) {
                long j10 = this.$featuredTrailerDelayMs;
                if (j10 < 500) {
                    j10 = 500;
                }
                this.label = 1;
                Object objA = ka.s0.a(j10, this);
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
        this.$featuredExpandedForIndex$delegate.setIntValue(this.$focusedItemIndex);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$ContentRow$3$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
