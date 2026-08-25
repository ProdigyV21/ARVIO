package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$16$1", f = "DetailsScreen.kt", l = {438}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsScreen$16$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $seasonIndex$delegate;
    final /* synthetic */ State<DetailsUiState> $uiState$delegate;
    final /* synthetic */ DetailsViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsScreen$16$1(DetailsViewModel detailsViewModel, State<DetailsUiState> state, MutableIntState mutableIntState, d7.d<? super DetailsScreenKt$DetailsScreen$16$1> dVar) {
        super(2, dVar);
        this.$viewModel = detailsViewModel;
        this.$uiState$delegate = state;
        this.$seasonIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$DetailsScreen$16$1(this.$viewModel, this.$uiState$delegate, this.$seasonIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getTotalSeasons() > 1) {
                this.label = 1;
                Object objA = ka.s0.a(100L, this);
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
        this.$viewModel.loadSeason(this.$seasonIndex$delegate.getIntValue() + 1);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsScreen$16$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
