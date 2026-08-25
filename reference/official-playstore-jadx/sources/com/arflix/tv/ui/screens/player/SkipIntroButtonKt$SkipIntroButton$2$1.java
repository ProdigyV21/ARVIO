package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.SkipIntroButtonKt$SkipIntroButton$2$1", f = "SkipIntroButton.kt", l = {TsExtractor.TS_SYNC_BYTE}, m = "invokeSuspend", v = 2)
public final class SkipIntroButtonKt$SkipIntroButton$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $autoHidden$delegate;
    final /* synthetic */ boolean $shouldShow;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipIntroButtonKt$SkipIntroButton$2$1(boolean z, MutableState<Boolean> mutableState, d7.d<? super SkipIntroButtonKt$SkipIntroButton$2$1> dVar) {
        super(2, dVar);
        this.$shouldShow = z;
        this.$autoHidden$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SkipIntroButtonKt$SkipIntroButton$2$1(this.$shouldShow, this.$autoHidden$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$shouldShow && !SkipIntroButtonKt.SkipIntroButton$lambda$1(this.$autoHidden$delegate)) {
                this.label = 1;
                Object objA = ka.s0.a(15000L, this);
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
        SkipIntroButtonKt.SkipIntroButton$lambda$2(this.$autoHidden$delegate, true);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SkipIntroButtonKt$SkipIntroButton$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
