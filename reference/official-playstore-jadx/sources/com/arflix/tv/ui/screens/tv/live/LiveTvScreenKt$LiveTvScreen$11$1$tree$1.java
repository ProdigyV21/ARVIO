package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$11$1$tree$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$11$1$tree$1 extends f7.j implements r7.p<ka.k0, d7.d<? super LiveCategoryTree>, Object> {
    final /* synthetic */ EnrichedChannels $current;
    final /* synthetic */ Set<String> $favSet;
    final /* synthetic */ Set<String> $hiddenGroupSet;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ State<TvUiState> $state$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$11$1$tree$1(EnrichedChannels enrichedChannels, Set<String> set, MutableState<LinkedHashSet<String>> mutableState, Set<String> set2, State<TvUiState> state, d7.d<? super LiveTvScreenKt$LiveTvScreen$11$1$tree$1> dVar) {
        super(2, dVar);
        this.$current = enrichedChannels;
        this.$favSet = set;
        this.$recents = mutableState;
        this.$hiddenGroupSet = set2;
        this.$state$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$11$1$tree$1(this.$current, this.$favSet, this.$recents, this.$hiddenGroupSet, this.$state$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        List<EnrichedChannel> all = this.$current.getAll();
        Set<String> set = this.$favSet;
        EnrichedChannels enrichedChannels = this.$current;
        int i11 = 0;
        if ((set instanceof Collection) && set.isEmpty()) {
            i10 = 0;
        } else {
            Iterator<T> it = set.iterator();
            i10 = 0;
            while (it.hasNext()) {
                if (LiveCategoryKt.isVisibleNonAdultChannel(enrichedChannels.getIndex(), (String) it.next()) && (i10 = i10 + 1) < 0) {
                    t7.a.P();
                    throw null;
                }
            }
        }
        LinkedHashSet<String> value = this.$recents.getValue();
        EnrichedChannels enrichedChannels2 = this.$current;
        if (!(value instanceof Collection) || !value.isEmpty()) {
            Iterator<T> it2 = value.iterator();
            while (it2.hasNext()) {
                if (LiveCategoryKt.isVisibleNonAdultChannel(enrichedChannels2.getIndex(), (String) it2.next()) && (i11 = i11 + 1) < 0) {
                    t7.a.P();
                    throw null;
                }
            }
        }
        return LiveCategoryKt.buildCategoryTree(all, i10, i11, this.$hiddenGroupSet, LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getSnapshot().getGroupOrder());
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super LiveCategoryTree> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$11$1$tree$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
