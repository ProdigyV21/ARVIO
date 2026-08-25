package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$10$1", f = "DetailsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsScreen$10$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $castIndex$delegate;
    final /* synthetic */ MutableIntState $episodeIndex$delegate;
    final /* synthetic */ MutableIntState $reviewIndex$delegate;
    final /* synthetic */ MutableIntState $seasonIndex$delegate;
    final /* synthetic */ MutableIntState $similarIndex$delegate;
    final /* synthetic */ State<DetailsUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsScreen$10$1(MutableIntState mutableIntState, State<DetailsUiState> state, MutableIntState mutableIntState2, MutableIntState mutableIntState3, MutableIntState mutableIntState4, MutableIntState mutableIntState5, d7.d<? super DetailsScreenKt$DetailsScreen$10$1> dVar) {
        super(2, dVar);
        this.$episodeIndex$delegate = mutableIntState;
        this.$uiState$delegate = state;
        this.$seasonIndex$delegate = mutableIntState2;
        this.$castIndex$delegate = mutableIntState3;
        this.$reviewIndex$delegate = mutableIntState4;
        this.$similarIndex$delegate = mutableIntState5;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$DetailsScreen$10$1(this.$episodeIndex$delegate, this.$uiState$delegate, this.$seasonIndex$delegate, this.$castIndex$delegate, this.$reviewIndex$delegate, this.$similarIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$episodeIndex$delegate.getIntValue() >= DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getEpisodes().size()) {
            MutableIntState mutableIntState = this.$episodeIndex$delegate;
            int size = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getEpisodes().size() - 1;
            if (size < 0) {
                size = 0;
            }
            mutableIntState.setIntValue(size);
        }
        if (this.$seasonIndex$delegate.getIntValue() >= DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getTotalSeasons()) {
            MutableIntState mutableIntState2 = this.$seasonIndex$delegate;
            int totalSeasons = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getTotalSeasons() - 1;
            if (totalSeasons < 0) {
                totalSeasons = 0;
            }
            mutableIntState2.setIntValue(totalSeasons);
        }
        if (this.$castIndex$delegate.getIntValue() >= DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getCast().size()) {
            MutableIntState mutableIntState3 = this.$castIndex$delegate;
            int size2 = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getCast().size() - 1;
            if (size2 < 0) {
                size2 = 0;
            }
            mutableIntState3.setIntValue(size2);
        }
        if (this.$reviewIndex$delegate.getIntValue() >= DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getReviews().size()) {
            MutableIntState mutableIntState4 = this.$reviewIndex$delegate;
            int size3 = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getReviews().size() - 1;
            if (size3 < 0) {
                size3 = 0;
            }
            mutableIntState4.setIntValue(size3);
        }
        if (this.$similarIndex$delegate.getIntValue() >= DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getSimilar().size()) {
            MutableIntState mutableIntState5 = this.$similarIndex$delegate;
            int size4 = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getSimilar().size() - 1;
            mutableIntState5.setIntValue(size4 >= 0 ? size4 : 0);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsScreen$10$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
