package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2", f = "IptvRepository.kt", l = {3392}, m = "invokeSuspend", v = 2)
public final class IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends List<? extends IptvRepository.XtreamSeriesEpisode>>>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ List<IptvRepository.ResolverCandidate> $probeList;
    final /* synthetic */ String $providerKey;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IptvRepository.IptvSeriesResolverService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2(List<IptvRepository.ResolverCandidate> list, IptvRepository.IptvSeriesResolverService iptvSeriesResolverService, String str, IptvRepository.XtreamCredentials xtreamCredentials, d7.d<? super IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2> dVar) {
        super(2, dVar);
        this.$probeList = list;
        this.this$0 = iptvSeriesResolverService;
        this.$providerKey = str;
        this.$creds = xtreamCredentials;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2 iptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2 = new IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2(this.$probeList, this.this$0, this.$providerKey, this.$creds, dVar);
        iptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2.L$0 = obj;
        return iptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        List<IptvRepository.ResolverCandidate> list = this.$probeList;
        IptvRepository.IptvSeriesResolverService iptvSeriesResolverService = this.this$0;
        String str = this.$providerKey;
        IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(ka.m0.e(3, null, k0Var, new IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1(iptvSeriesResolverService, str, xtreamCredentials, (IptvRepository.ResolverCandidate) it.next(), null)));
        }
        this.L$0 = null;
        this.label = 1;
        Object objF = ka.m0.f(arrayList, this);
        e7.a aVar = e7.a.f15033i;
        return objF == aVar ? aVar : objF;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends List<IptvRepository.XtreamSeriesEpisode>>> dVar) {
        return ((IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
