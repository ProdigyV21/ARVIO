package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "Lx6/x;", "", "", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$perSeriesEpisodes$1$1$1", f = "IptvRepository.kt", l = {3522}, m = "invokeSuspend", v = 2)
public final class IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$perSeriesEpisodes$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ boolean $allowNetwork;
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ String $providerKey;
    final /* synthetic */ int $seriesId;
    Object L$0;
    int label;
    final /* synthetic */ IptvRepository.IptvSeriesResolverService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$perSeriesEpisodes$1$1$1(int i10, IptvRepository.IptvSeriesResolverService iptvSeriesResolverService, String str, IptvRepository.XtreamCredentials xtreamCredentials, boolean z, d7.d<? super IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$perSeriesEpisodes$1$1$1> dVar) {
        super(2, dVar);
        this.$seriesId = i10;
        this.this$0 = iptvSeriesResolverService;
        this.$providerKey = str;
        this.$creds = xtreamCredentials;
        this.$allowNetwork = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$perSeriesEpisodes$1$1$1(this.$seriesId, this.this$0, this.$providerKey, this.$creds, this.$allowNetwork, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Integer num;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            num = new Integer(this.$seriesId);
            IptvRepository.IptvSeriesResolverService iptvSeriesResolverService = this.this$0;
            String str = this.$providerKey;
            IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
            int i11 = this.$seriesId;
            boolean z = this.$allowNetwork;
            this.L$0 = num;
            this.label = 1;
            obj = iptvSeriesResolverService.loadSeriesInfo(str, xtreamCredentials, i11, z, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            num = (Integer) this.L$0;
            k2.c.G(obj);
        }
        return new x6.x(num, obj);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((IptvRepository$IptvSeriesResolverService$resolveEpisodeVariants$perSeriesEpisodes$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
