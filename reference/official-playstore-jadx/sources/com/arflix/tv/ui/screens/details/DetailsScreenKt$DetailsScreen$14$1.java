package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$14$1", f = "DetailsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsScreen$14$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $episodeIndex$delegate;
    final /* synthetic */ MutableIntState $ratingsIndex$delegate;
    final /* synthetic */ State<DetailsUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsScreen$14$1(State<DetailsUiState> state, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super DetailsScreenKt$DetailsScreen$14$1> dVar) {
        super(2, dVar);
        this.$uiState$delegate = state;
        this.$episodeIndex$delegate = mutableIntState;
        this.$ratingsIndex$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$DetailsScreen$14$1(this.$uiState$delegate, this.$episodeIndex$delegate, this.$ratingsIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zIsEmpty = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getEpisodes().isEmpty();
        x6.t0 t0Var = x6.t0.f22605a;
        if (zIsEmpty) {
            return t0Var;
        }
        this.$episodeIndex$delegate.setIntValue(DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getCurrentSeason() == DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getInitialSeasonIndex() + 1 ? qb.d.n(DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getInitialEpisodeIndex(), 0, t7.a.w(DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getEpisodes())) : 0);
        this.$ratingsIndex$delegate.setIntValue(this.$episodeIndex$delegate.getIntValue() / 12);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsScreen$14$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
