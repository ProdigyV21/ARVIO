package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchFreshChannelsForStartup$channels$1", f = "IptvRepository.kt", l = {2747}, m = "invokeSuspend", v = 2)
public final class IptvRepository$fetchFreshChannelsForStartup$channels$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvChannel>>, Object> {
    final /* synthetic */ List<IptvPlaylistEntry> $activeLists;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$fetchFreshChannelsForStartup$channels$1(List<IptvPlaylistEntry> list, IptvRepository iptvRepository, d7.d<? super IptvRepository$fetchFreshChannelsForStartup$channels$1> dVar) {
        super(2, dVar);
        this.$activeLists = list;
        this.this$0 = iptvRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        IptvRepository$fetchFreshChannelsForStartup$channels$1 iptvRepository$fetchFreshChannelsForStartup$channels$1 = new IptvRepository$fetchFreshChannelsForStartup$channels$1(this.$activeLists, this.this$0, dVar);
        iptvRepository$fetchFreshChannelsForStartup$channels$1.L$0 = obj;
        return iptvRepository$fetchFreshChannelsForStartup$channels$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            List<IptvPlaylistEntry> list = this.$activeLists;
            IptvRepository iptvRepository = this.this$0;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ka.m0.e(3, null, k0Var, new IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1(iptvRepository, (IptvPlaylistEntry) it.next(), null)));
            }
            this.L$0 = null;
            this.label = 1;
            obj = ka.m0.f(arrayList, this);
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
        return kotlin.collections.s.V((Iterable) obj);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvChannel>> dVar) {
        return ((IptvRepository$fetchFreshChannelsForStartup$channels$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
