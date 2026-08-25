package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$15$1$result$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$15$1$result$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends EnrichedChannel>>, Object> {
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    final /* synthetic */ MutableState<EnrichedChannels> $visibleEnrichedState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$15$1$result$1(MutableState<EnrichedChannels> mutableState, MutableState<LinkedHashSet<String>> mutableState2, MutableState<String> mutableState3, State<TvUiState> state, d7.d<? super LiveTvScreenKt$LiveTvScreen$15$1$result$1> dVar) {
        super(2, dVar);
        this.$visibleEnrichedState = mutableState;
        this.$recents = mutableState2;
        this.$selectedCategoryId$delegate = mutableState3;
        this.$state$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$15$1$result$1(this.$visibleEnrichedState, this.$recents, this.$selectedCategoryId$delegate, this.$state$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return this.$visibleEnrichedState.getValue().getIndex().channelsFor(LiveTvScreenKt.LiveTvScreen$lambda$13(this.$selectedCategoryId$delegate), LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getSnapshot().getFavoriteChannels(), this.$recents.getValue());
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<EnrichedChannel>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$15$1$result$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
