package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$8$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$8$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ State<TvUiState> $state$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$8$1(MutableState<LinkedHashSet<String>> mutableState, State<TvUiState> state, d7.d<? super LiveTvScreenKt$LiveTvScreen$8$1> dVar) {
        super(2, dVar);
        this.$recents = mutableState;
        this.$state$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$8$1(this.$recents, this.$state$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        List<String> recentChannelIds = LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getTvSession().getRecentChannelIds();
        State<TvUiState> state = this.$state$delegate;
        if (recentChannelIds.isEmpty()) {
            String lastChannelId = LiveTvScreenKt.LiveTvScreen$lambda$7(state).getTvSession().getLastChannelId();
            if (kotlin.text.o.h0(lastChannelId)) {
                lastChannelId = null;
            }
            recentChannelIds = t7.a.F(lastChannelId);
        }
        if (!recentChannelIds.isEmpty()) {
            MutableState<LinkedHashSet<String>> mutableState = this.$recents;
            LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
            for (String str : recentChannelIds) {
                if (!kotlin.text.o.h0(str)) {
                    linkedHashSet.add(str);
                }
            }
            while (linkedHashSet.size() > 40) {
                linkedHashSet.remove(kotlin.collections.x.l0(linkedHashSet));
            }
            mutableState.setValue(linkedHashSet);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$8$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
