package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$22$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$22$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<String> $guideScopeKey$delegate;
    final /* synthetic */ MutableIntState $guideWindowEnd$delegate;
    final /* synthetic */ MutableIntState $guideWindowStart$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$22$1(MutableState<String> mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super LiveTvScreenKt$LiveTvScreen$22$1> dVar) {
        super(2, dVar);
        this.$guideScopeKey$delegate = mutableState;
        this.$guideWindowStart$delegate = mutableIntState;
        this.$guideWindowEnd$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$22$1(this.$guideScopeKey$delegate, this.$guideWindowStart$delegate, this.$guideWindowEnd$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.$guideScopeKey$delegate.setValue("");
        this.$guideWindowStart$delegate.setIntValue(0);
        this.$guideWindowEnd$delegate.setIntValue(48);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$22$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
