package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$25$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$25$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<TvFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableState<Boolean> $isFullScreen$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableState<String> $selectedChannelId$delegate;
    final /* synthetic */ String $selectedGroup;
    final /* synthetic */ TvViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$25$1(String str, TvViewModel tvViewModel, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<TvFocusZone> mutableState4, d7.d<? super TvScreenKt$TvScreen$25$1> dVar) {
        super(2, dVar);
        this.$selectedGroup = str;
        this.$viewModel = tvViewModel;
        this.$selectedChannelId$delegate = mutableState;
        this.$playingChannelId$delegate = mutableState2;
        this.$isFullScreen$delegate = mutableState3;
        this.$focusZone$delegate = mutableState4;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$25$1(this.$selectedGroup, this.$viewModel, this.$selectedChannelId$delegate, this.$playingChannelId$delegate, this.$isFullScreen$delegate, this.$focusZone$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zH0 = kotlin.text.o.h0(this.$selectedGroup);
        t0 t0Var = t0.f22605a;
        if (zH0 && TvScreenKt.TvScreen$lambda$20(this.$selectedChannelId$delegate) == null && TvScreenKt.TvScreen$lambda$23(this.$playingChannelId$delegate) == null) {
            return t0Var;
        }
        TvViewModel tvViewModel = this.$viewModel;
        String strTvScreen$lambda$23 = TvScreenKt.TvScreen$lambda$23(this.$playingChannelId$delegate);
        if (strTvScreen$lambda$23 == null) {
            strTvScreen$lambda$23 = TvScreenKt.TvScreen$lambda$20(this.$selectedChannelId$delegate);
        }
        TvViewModel.rememberTvSession$default(tvViewModel, strTvScreen$lambda$23, this.$selectedGroup, TvScreenKt.TvScreen$lambda$29(this.$isFullScreen$delegate) ? "GUIDE" : TvScreenKt.TvScreen$lambda$8(this.$focusZone$delegate).name(), false, 8, null);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$25$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
