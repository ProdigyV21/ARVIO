package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Lka/k0;", "", "Lx6/j0;", "Lcom/arflix/tv/data/repository/IptvRepository$ResolverCandidate;", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1", f = "IptvRepository.kt", l = {3617}, m = "invokeSuspend", v = 2)
public final class IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends x6.j0>>, Object> {
    final /* synthetic */ boolean $allowNetwork;
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ int $episode;
    final /* synthetic */ List<IptvRepository.ResolverCandidate> $probeList;
    final /* synthetic */ String $providerKey;
    final /* synthetic */ int $season;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IptvRepository.IptvSeriesResolverService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1(List<IptvRepository.ResolverCandidate> list, IptvRepository.IptvSeriesResolverService iptvSeriesResolverService, String str, IptvRepository.XtreamCredentials xtreamCredentials, boolean z, int i10, int i11, d7.d<? super IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1> dVar) {
        super(2, dVar);
        this.$probeList = list;
        this.this$0 = iptvSeriesResolverService;
        this.$providerKey = str;
        this.$creds = xtreamCredentials;
        this.$allowNetwork = z;
        this.$season = i10;
        this.$episode = i11;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1 iptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1 = new IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1(this.$probeList, this.this$0, this.$providerKey, this.$creds, this.$allowNetwork, this.$season, this.$episode, dVar);
        iptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1.L$0 = obj;
        return iptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            List<IptvRepository.ResolverCandidate> list = this.$probeList;
            IptvRepository.IptvSeriesResolverService iptvSeriesResolverService = this.this$0;
            String str = this.$providerKey;
            IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
            boolean z = this.$allowNetwork;
            int i11 = this.$season;
            int i12 = this.$episode;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ka.m0.e(3, null, k0Var, new IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1$1$1(iptvSeriesResolverService, str, xtreamCredentials, (IptvRepository.ResolverCandidate) it.next(), z, i11, i12, null)));
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
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<x6.j0>> dVar) {
        return ((IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$hits$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
