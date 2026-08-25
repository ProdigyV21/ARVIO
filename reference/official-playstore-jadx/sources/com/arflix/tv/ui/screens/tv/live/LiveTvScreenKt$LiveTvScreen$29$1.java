package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$29$1", f = "LiveTvScreen.kt", l = {1357}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$29$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<String> $catchupHistoryAnchorIds;
    final /* synthetic */ MutableState<Boolean> $startupChannelApplied$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    final /* synthetic */ TvViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$29$1(List<String> list, State<TvUiState> state, MutableState<Boolean> mutableState, TvViewModel tvViewModel, d7.d<? super LiveTvScreenKt$LiveTvScreen$29$1> dVar) {
        super(2, dVar);
        this.$catchupHistoryAnchorIds = list;
        this.$state$delegate = state;
        this.$startupChannelApplied$delegate = mutableState;
        this.$viewModel = tvViewModel;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$29$1(this.$catchupHistoryAnchorIds, this.$state$delegate, this.$startupChannelApplied$delegate, this.$viewModel, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getIptvPreferencesLoaded() || !LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getTvSessionLoaded() || !LiveTvScreenKt.LiveTvScreen$lambda$74(this.$startupChannelApplied$delegate) || this.$catchupHistoryAnchorIds.isEmpty()) {
                return t0Var;
            }
            this.label = 1;
            Object objA = ka.s0.a(120L, this);
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
        List<String> list = this.$catchupHistoryAnchorIds;
        TvViewModel tvViewModel = this.$viewModel;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            tvViewModel.refreshCatchupHistoryForChannel((String) it.next());
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$29$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
