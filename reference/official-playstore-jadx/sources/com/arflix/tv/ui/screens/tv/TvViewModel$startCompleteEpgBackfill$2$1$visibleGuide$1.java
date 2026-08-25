package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvSnapshot;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1", f = "TvViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<String, ? extends IptvNowNext>>, Object> {
    final /* synthetic */ LinkedHashSet<String> $priorityIds;
    final /* synthetic */ IptvSnapshot $snapshot;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1(TvViewModel tvViewModel, LinkedHashSet<String> linkedHashSet, IptvSnapshot iptvSnapshot, d7.d<? super TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1> dVar) {
        super(2, dVar);
        this.this$0 = tvViewModel;
        this.$priorityIds = linkedHashSet;
        this.$snapshot = iptvSnapshot;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1(this.this$0, this.$priorityIds, this.$snapshot, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        Map<String, IptvNowNext> mapReDeriveCachedNowNext = this.this$0.getIptvRepository().reDeriveCachedNowNext(this.$priorityIds);
        if (mapReDeriveCachedNowNext == null) {
            mapReDeriveCachedNowNext = kotlin.collections.a0.f19683i;
        }
        Map<String, IptvNowNext> nowNext = this.$snapshot.getNowNext();
        LinkedHashSet<String> linkedHashSet = this.$priorityIds;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, IptvNowNext> entry : nowNext.entrySet()) {
            if (linkedHashSet.contains(entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap.isEmpty() ? mapReDeriveCachedNowNext : kotlin.collections.h0.v0(linkedHashMap, mapReDeriveCachedNowNext);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Map<String, IptvNowNext>> dVar) {
        return ((TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
