package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$9$1", f = "DetailsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsScreen$9$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $buttonIndex$delegate;
    final /* synthetic */ MutableIntState $castIndex$delegate;
    final /* synthetic */ MutableIntState $episodeIndex$delegate;
    final /* synthetic */ MutableState<FocusSection> $focusedSection$delegate;
    final /* synthetic */ Integer $initialEpisode;
    final /* synthetic */ Integer $initialSeason;
    final /* synthetic */ MutableState<Boolean> $isSidebarFocused$delegate;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ MutableIntState $ratingsIndex$delegate;
    final /* synthetic */ MutableIntState $reviewIndex$delegate;
    final /* synthetic */ MutableIntState $seasonIndex$delegate;
    final /* synthetic */ MutableIntState $similarIndex$delegate;
    final /* synthetic */ DetailsViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsScreen$9$1(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, Integer num, Integer num2, MutableState<FocusSection> mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableIntState mutableIntState3, MutableIntState mutableIntState4, MutableIntState mutableIntState5, MutableIntState mutableIntState6, MutableIntState mutableIntState7, MutableState<Boolean> mutableState2, d7.d<? super DetailsScreenKt$DetailsScreen$9$1> dVar) {
        super(2, dVar);
        this.$viewModel = detailsViewModel;
        this.$mediaType = mediaType;
        this.$mediaId = i10;
        this.$initialSeason = num;
        this.$initialEpisode = num2;
        this.$focusedSection$delegate = mutableState;
        this.$buttonIndex$delegate = mutableIntState;
        this.$episodeIndex$delegate = mutableIntState2;
        this.$ratingsIndex$delegate = mutableIntState3;
        this.$seasonIndex$delegate = mutableIntState4;
        this.$castIndex$delegate = mutableIntState5;
        this.$reviewIndex$delegate = mutableIntState6;
        this.$similarIndex$delegate = mutableIntState7;
        this.$isSidebarFocused$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$DetailsScreen$9$1(this.$viewModel, this.$mediaType, this.$mediaId, this.$initialSeason, this.$initialEpisode, this.$focusedSection$delegate, this.$buttonIndex$delegate, this.$episodeIndex$delegate, this.$ratingsIndex$delegate, this.$seasonIndex$delegate, this.$castIndex$delegate, this.$reviewIndex$delegate, this.$similarIndex$delegate, this.$isSidebarFocused$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.$focusedSection$delegate.setValue(FocusSection.BUTTONS);
        this.$buttonIndex$delegate.setIntValue(0);
        this.$episodeIndex$delegate.setIntValue(0);
        this.$ratingsIndex$delegate.setIntValue(0);
        this.$seasonIndex$delegate.setIntValue(0);
        this.$castIndex$delegate.setIntValue(0);
        this.$reviewIndex$delegate.setIntValue(0);
        this.$similarIndex$delegate.setIntValue(0);
        DetailsScreenKt.DetailsScreen$lambda$40(this.$isSidebarFocused$delegate, false);
        this.$viewModel.loadDetails(this.$mediaType, this.$mediaId, this.$initialSeason, this.$initialEpisode);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsScreen$9$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
