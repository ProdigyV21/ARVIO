package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.data.model.IptvNowNext;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$updated$1", f = "TvViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvViewModel$refreshGuideFromCache$updated$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<String, ? extends IptvNowNext>>, Object> {
    final /* synthetic */ LinkedHashSet<String> $channelIds;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$refreshGuideFromCache$updated$1(TvViewModel tvViewModel, LinkedHashSet<String> linkedHashSet, d7.d<? super TvViewModel$refreshGuideFromCache$updated$1> dVar) {
        super(2, dVar);
        this.this$0 = tvViewModel;
        this.$channelIds = linkedHashSet;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvViewModel$refreshGuideFromCache$updated$1(this.this$0, this.$channelIds, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return this.this$0.getIptvRepository().reDeriveCachedNowNext(this.$channelIds);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Map<String, IptvNowNext>> dVar) {
        return ((TvViewModel$refreshGuideFromCache$updated$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
