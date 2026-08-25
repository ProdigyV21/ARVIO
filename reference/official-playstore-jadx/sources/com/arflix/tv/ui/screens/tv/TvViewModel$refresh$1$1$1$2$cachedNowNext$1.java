package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvSnapshot;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$refresh$1$1$1$2$cachedNowNext$1", f = "TvViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvViewModel$refresh$1$1$1$2$cachedNowNext$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<String, ? extends IptvNowNext>>, Object> {
    final /* synthetic */ List<IptvChannel> $channels;
    final /* synthetic */ TvUiState $currentState;
    final /* synthetic */ Map<String, List<IptvChannel>> $freshGrouped;
    final /* synthetic */ IptvSnapshot $seedSnapshot;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TvViewModel$refresh$1$1$1$2$cachedNowNext$1(List<IptvChannel> list, TvUiState tvUiState, IptvSnapshot iptvSnapshot, Map<String, ? extends List<IptvChannel>> map, TvViewModel tvViewModel, d7.d<? super TvViewModel$refresh$1$1$1$2$cachedNowNext$1> dVar) {
        super(2, dVar);
        this.$channels = list;
        this.$currentState = tvUiState;
        this.$seedSnapshot = iptvSnapshot;
        this.$freshGrouped = map;
        this.this$0 = tvViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvViewModel$refresh$1$1$1$2$cachedNowNext$1(this.$channels, this.$currentState, this.$seedSnapshot, this.$freshGrouped, this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        LinkedHashSet linkedHashSet;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (TvViewModelKt.isLargeIptvList(this.$channels.size())) {
            linkedHashSet = TvViewModelKt.buildPriorityEpgChannelIds(TvUiState.copy$default(this.$currentState, false, null, null, 0, null, this.$seedSnapshot, null, kotlin.collections.x.c1(this.$freshGrouped.keySet()), this.$freshGrouped, null, false, false, false, null, null, null, false, 130655, null), 360);
        } else {
            ga.t tVar = new ga.t(new ga.p(this.$channels, 3), new j0(0));
            linkedHashSet = new LinkedHashSet();
            ga.r.T(tVar, linkedHashSet);
        }
        Map<String, IptvNowNext> mapReDeriveCachedNowNext = this.this$0.getIptvRepository().reDeriveCachedNowNext(linkedHashSet);
        return mapReDeriveCachedNowNext == null ? kotlin.collections.a0.f19683i : mapReDeriveCachedNowNext;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Map<String, IptvNowNext>> dVar) {
        return ((TvViewModel$refresh$1$1$1$2$cachedNowNext$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
