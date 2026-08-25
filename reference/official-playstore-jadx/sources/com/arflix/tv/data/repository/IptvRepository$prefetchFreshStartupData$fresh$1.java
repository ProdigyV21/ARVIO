package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "Lx6/x;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "Lcom/arflix/tv/data/api/StalkerApi;", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$prefetchFreshStartupData$fresh$1", f = "IptvRepository.kt", l = {2782}, m = "invokeSuspend", v = 2)
public final class IptvRepository$prefetchFreshStartupData$fresh$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ IptvConfig $config;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$prefetchFreshStartupData$fresh$1(IptvRepository iptvRepository, IptvConfig iptvConfig, d7.d<? super IptvRepository$prefetchFreshStartupData$fresh$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$config = iptvConfig;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$prefetchFreshStartupData$fresh$1(this.this$0, this.$config, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        IptvRepository iptvRepository = this.this$0;
        IptvConfig iptvConfig = this.$config;
        this.label = 1;
        Object objFetchFreshChannelsForStartup = iptvRepository.fetchFreshChannelsForStartup(iptvConfig, this);
        e7.a aVar = e7.a.f15033i;
        return objFetchFreshChannelsForStartup == aVar ? aVar : objFetchFreshChannelsForStartup;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((IptvRepository$prefetchFreshStartupData$fresh$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
