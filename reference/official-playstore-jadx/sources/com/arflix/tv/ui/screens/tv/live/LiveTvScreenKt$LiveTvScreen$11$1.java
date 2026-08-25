package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$11$1", f = "LiveTvScreen.kt", l = {743}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$11$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<EnrichedChannels> $enrichedState;
    final /* synthetic */ Set<String> $favSet;
    final /* synthetic */ Set<String> $hiddenGroupSet;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ State<TvUiState> $state$delegate;
    int I$0;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$11$1(MutableState<EnrichedChannels> mutableState, Set<String> set, MutableState<LinkedHashSet<String>> mutableState2, Set<String> set2, State<TvUiState> state, d7.d<? super LiveTvScreenKt$LiveTvScreen$11$1> dVar) {
        super(2, dVar);
        this.$enrichedState = mutableState;
        this.$favSet = set;
        this.$recents = mutableState2;
        this.$hiddenGroupSet = set2;
        this.$state$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$11$1(this.$enrichedState, this.$favSet, this.$recents, this.$hiddenGroupSet, this.$state$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnrichedChannels enrichedChannels;
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            EnrichedChannels value = this.$enrichedState.getValue();
            if (value != EnrichedChannels.INSTANCE.getEmpty()) {
                Integer numCountForCategory = LiveTvScreenKt.countForCategory(value.getTree(), TtmlNode.COMBINE_ALL);
                int iIntValue = numCountForCategory != null ? numCountForCategory.intValue() : value.getAll().size();
                if (iIntValue <= value.getAll().size()) {
                    ra.d dVar = ka.x0.f19653b;
                    LiveTvScreenKt$LiveTvScreen$11$1$tree$1 liveTvScreenKt$LiveTvScreen$11$1$tree$1 = new LiveTvScreenKt$LiveTvScreen$11$1$tree$1(value, this.$favSet, this.$recents, this.$hiddenGroupSet, this.$state$delegate, null);
                    this.L$0 = value;
                    this.I$0 = iIntValue;
                    this.label = 1;
                    obj = ka.m0.y(dVar, liveTvScreenKt$LiveTvScreen$11$1$tree$1, this);
                    e7.a aVar = e7.a.f15033i;
                    if (obj == aVar) {
                        return aVar;
                    }
                    enrichedChannels = value;
                }
            }
            return t0Var;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        EnrichedChannels enrichedChannels2 = (EnrichedChannels) this.L$0;
        k2.c.G(obj);
        enrichedChannels = enrichedChannels2;
        this.$enrichedState.setValue(EnrichedChannels.copy$default(enrichedChannels, null, (LiveCategoryTree) obj, null, 5, null));
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$11$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
