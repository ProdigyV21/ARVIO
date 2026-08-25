package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.repository.IptvRepository;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2", f = "TvViewModel.kt", l = {1428}, m = "invokeSuspend", v = 2)
public final class TvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<String, ? extends IptvNowNext>>, Object> {
    final /* synthetic */ Set<String> $claimedIds;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2(TvViewModel tvViewModel, Set<String> set, d7.d<? super TvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2> dVar) {
        super(2, dVar);
        this.this$0 = tvViewModel;
        this.$claimedIds = set;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2 tvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2 = new TvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2(this.this$0, this.$claimedIds, dVar);
        tvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2.L$0 = obj;
        return tvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                TvViewModel tvViewModel = this.this$0;
                Set<String> set = this.$claimedIds;
                IptvRepository iptvRepository = tvViewModel.getIptvRepository();
                int size = set.size();
                this.L$0 = null;
                this.L$1 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = iptvRepository.refreshEpgForChannels(set, size, false, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            c0Var = (Map) obj;
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            return null;
        }
        return c0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Map<String, IptvNowNext>> dVar) {
        return ((TvViewModel$prefetchVisibleCategoryEpg$1$refreshed$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
