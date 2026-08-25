package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.ui.screens.tv.TvViewModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)I"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1$pagedTotal$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$10$1$pagedTotal$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Integer>, Object> {
    final /* synthetic */ TvViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$10$1$pagedTotal$1(TvViewModel tvViewModel, d7.d<? super LiveTvScreenKt$LiveTvScreen$10$1$pagedTotal$1> dVar) {
        super(2, dVar);
        this.$viewModel = tvViewModel;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$10$1$pagedTotal$1(this.$viewModel, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return new Integer(this.$viewModel.getIptvRepository().pagedChannelsReady() ? this.$viewModel.getIptvRepository().pagedChannelCount(null) : 0);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Integer> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$10$1$pagedTotal$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
