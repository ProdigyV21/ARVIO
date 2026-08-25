package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$loadSeriesInfo$episodes$1", f = "IptvRepository.kt", l = {3936}, m = "invokeSuspend", v = 2)
public final class IptvRepository$IptvSeriesResolverService$loadSeriesInfo$episodes$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvRepository.XtreamSeriesEpisode>>, Object> {
    final /* synthetic */ boolean $allowNetwork;
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ int $seriesId;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$loadSeriesInfo$episodes$1(IptvRepository iptvRepository, IptvRepository.XtreamCredentials xtreamCredentials, int i10, boolean z, d7.d<? super IptvRepository$IptvSeriesResolverService$loadSeriesInfo$episodes$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$creds = xtreamCredentials;
        this.$seriesId = i10;
        this.$allowNetwork = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$IptvSeriesResolverService$loadSeriesInfo$episodes$1(this.this$0, this.$creds, this.$seriesId, this.$allowNetwork, dVar);
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
        IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
        int i11 = this.$seriesId;
        boolean z = this.$allowNetwork;
        this.label = 1;
        Object xtreamSeriesEpisodes = iptvRepository.getXtreamSeriesEpisodes(xtreamCredentials, i11, z, false, this);
        e7.a aVar = e7.a.f15033i;
        return xtreamSeriesEpisodes == aVar ? aVar : xtreamSeriesEpisodes;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvRepository.XtreamSeriesEpisode>> dVar) {
        return ((IptvRepository$IptvSeriesResolverService$loadSeriesInfo$episodes$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
