package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$10$1$initialValue$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$10$1$initialValue$1 extends f7.j implements r7.p<ka.k0, d7.d<? super EnrichedChannels>, Object> {
    final /* synthetic */ Set<String> $favSet;
    final /* synthetic */ Set<String> $hiddenGroupSet;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ List<IptvChannel> $snapshot;
    final /* synthetic */ State<TvUiState> $state$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$10$1$initialValue$1(List<IptvChannel> list, Set<String> set, MutableState<LinkedHashSet<String>> mutableState, Set<String> set2, State<TvUiState> state, d7.d<? super LiveTvScreenKt$LiveTvScreen$10$1$initialValue$1> dVar) {
        super(2, dVar);
        this.$snapshot = list;
        this.$favSet = set;
        this.$recents = mutableState;
        this.$hiddenGroupSet = set2;
        this.$state$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$10$1$initialValue$1(this.$snapshot, this.$favSet, this.$recents, this.$hiddenGroupSet, this.$state$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return LiveCategoryKt.buildFastStartupChannelState(this.$snapshot, this.$favSet, this.$recents.getValue(), this.$hiddenGroupSet, LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getSnapshot().getGroupOrder());
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super EnrichedChannels> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$10$1$initialValue$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
