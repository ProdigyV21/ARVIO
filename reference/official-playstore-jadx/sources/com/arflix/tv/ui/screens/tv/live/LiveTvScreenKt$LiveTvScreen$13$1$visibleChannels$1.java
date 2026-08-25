package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.screens.tv.TvUiState;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$13$1$visibleChannels$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$13$1$visibleChannels$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends EnrichedChannel>>, Object> {
    final /* synthetic */ EnrichedChannels $current;
    final /* synthetic */ MutableState<String> $selectedProviderId$delegate;
    final /* synthetic */ State<TvUiState> $state$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$13$1$visibleChannels$1(EnrichedChannels enrichedChannels, MutableState<String> mutableState, State<TvUiState> state, d7.d<? super LiveTvScreenKt$LiveTvScreen$13$1$visibleChannels$1> dVar) {
        super(2, dVar);
        this.$current = enrichedChannels;
        this.$selectedProviderId$delegate = mutableState;
        this.$state$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$13$1$visibleChannels$1(this.$current, this.$selectedProviderId$delegate, this.$state$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        List<EnrichedChannel> all = this.$current.getAll();
        r7.l<EnrichedChannel, Boolean> lVarProviderMatcher = LiveTvEnhancementsKt.providerMatcher(LiveTvScreenKt.LiveTvScreen$lambda$16(this.$selectedProviderId$delegate), LiveTvScreenKt.LiveTvScreen$lambda$7(this.$state$delegate).getConfig());
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : all) {
            if (((Boolean) lVarProviderMatcher.invoke(obj2)).booleanValue()) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<EnrichedChannel>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$13$1$visibleChannels$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
