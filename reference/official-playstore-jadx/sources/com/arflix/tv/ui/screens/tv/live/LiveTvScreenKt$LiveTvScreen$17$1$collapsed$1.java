package com.arflix.tv.ui.screens.tv.live;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends EnrichedChannel>>, Object> {
    final /* synthetic */ List<EnrichedChannel> $source;
    final /* synthetic */ Map<String, List<EnrichedChannel>> $variantGroups;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1(List<EnrichedChannel> list, Map<String, ? extends List<EnrichedChannel>> map, d7.d<? super LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1> dVar) {
        super(2, dVar);
        this.$source = list;
        this.$variantGroups = map;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1(this.$source, this.$variantGroups, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return LiveTvEnhancementsKt.collapseChannelVariants(this.$source, this.$variantGroups);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<EnrichedChannel>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$17$1$collapsed$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
