package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1", f = "IptvRepository.kt", l = {3383}, m = "invokeSuspend", v = 2)
public final class IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvRepository.XtreamSeriesEpisode>>, Object> {
    final /* synthetic */ IptvRepository.ResolverCandidate $candidate;
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ String $providerKey;
    int label;
    final /* synthetic */ IptvRepository.IptvSeriesResolverService this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1$1", f = "IptvRepository.kt", l = {3384}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvRepository.XtreamSeriesEpisode>>, Object> {
        final /* synthetic */ IptvRepository.ResolverCandidate $candidate;
        final /* synthetic */ IptvRepository.XtreamCredentials $creds;
        final /* synthetic */ String $providerKey;
        int label;
        final /* synthetic */ IptvRepository.IptvSeriesResolverService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(IptvRepository.IptvSeriesResolverService iptvSeriesResolverService, String str, IptvRepository.XtreamCredentials xtreamCredentials, IptvRepository.ResolverCandidate resolverCandidate, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = iptvSeriesResolverService;
            this.$providerKey = str;
            this.$creds = xtreamCredentials;
            this.$candidate = resolverCandidate;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.this$0, this.$providerKey, this.$creds, this.$candidate, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            IptvRepository.IptvSeriesResolverService iptvSeriesResolverService = this.this$0;
            String str = this.$providerKey;
            IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
            int seriesId = this.$candidate.getEntry().getSeriesId();
            this.label = 1;
            Object objLoadSeriesInfo = iptvSeriesResolverService.loadSeriesInfo(str, xtreamCredentials, seriesId, true, this);
            e7.a aVar = e7.a.f15033i;
            return objLoadSeriesInfo == aVar ? aVar : objLoadSeriesInfo;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvRepository.XtreamSeriesEpisode>> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1(IptvRepository.IptvSeriesResolverService iptvSeriesResolverService, String str, IptvRepository.XtreamCredentials xtreamCredentials, IptvRepository.ResolverCandidate resolverCandidate, d7.d<? super IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvSeriesResolverService;
        this.$providerKey = str;
        this.$creds = xtreamCredentials;
        this.$candidate = resolverCandidate;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1(this.this$0, this.$providerKey, this.$creds, this.$candidate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$providerKey, this.$creds, this.$candidate, null);
        this.label = 1;
        Object objA = ka.m0.A(5000L, anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objA == aVar ? aVar : objA;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvRepository.XtreamSeriesEpisode>> dVar) {
        return ((IptvRepository$IptvSeriesResolverService$prefetchSeriesInfo$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
