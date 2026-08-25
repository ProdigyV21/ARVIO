package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$13$1", f = "LiveTvScreen.kt", l = {783, 786, 787}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$13$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<EnrichedChannels> $enrichedState;
    final /* synthetic */ Set<String> $favSet;
    final /* synthetic */ Set<String> $hiddenGroupSet;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ MutableState<String> $selectedProviderId$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    final /* synthetic */ MutableState<EnrichedChannels> $visibleEnrichedState;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$13$1(MutableState<EnrichedChannels> mutableState, MutableState<EnrichedChannels> mutableState2, MutableState<String> mutableState3, State<TvUiState> state, Set<String> set, Set<String> set2, MutableState<LinkedHashSet<String>> mutableState4, d7.d<? super LiveTvScreenKt$LiveTvScreen$13$1> dVar) {
        super(2, dVar);
        this.$enrichedState = mutableState;
        this.$visibleEnrichedState = mutableState2;
        this.$selectedProviderId$delegate = mutableState3;
        this.$state$delegate = state;
        this.$hiddenGroupSet = set;
        this.$favSet = set2;
        this.$recents = mutableState4;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$13$1(this.$enrichedState, this.$visibleEnrichedState, this.$selectedProviderId$delegate, this.$state$delegate, this.$hiddenGroupSet, this.$favSet, this.$recents, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ca  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$13$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$13$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
