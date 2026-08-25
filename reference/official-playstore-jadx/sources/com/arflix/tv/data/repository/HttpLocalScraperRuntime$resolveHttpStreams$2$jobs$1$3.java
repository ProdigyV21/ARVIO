package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.HttpLocalScraperRuntime;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpResolvedStream;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveHttpStreams$2$jobs$1$3", f = "HttpLocalScraperRuntime.kt", l = {166}, m = "invokeSuspend", v = 2)
public final class HttpLocalScraperRuntime$resolveHttpStreams$2$jobs$1$3 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends HttpLocalScraperRuntime.HttpResolvedStream>>, Object> {
    final /* synthetic */ Integer $episode;
    final /* synthetic */ String $mediaType;
    final /* synthetic */ Integer $season;
    final /* synthetic */ int $tmdbId;
    int label;
    final /* synthetic */ HttpLocalScraperRuntime this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpLocalScraperRuntime$resolveHttpStreams$2$jobs$1$3(HttpLocalScraperRuntime httpLocalScraperRuntime, int i10, String str, Integer num, Integer num2, d7.d<? super HttpLocalScraperRuntime$resolveHttpStreams$2$jobs$1$3> dVar) {
        super(2, dVar);
        this.this$0 = httpLocalScraperRuntime;
        this.$tmdbId = i10;
        this.$mediaType = str;
        this.$season = num;
        this.$episode = num2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HttpLocalScraperRuntime$resolveHttpStreams$2$jobs$1$3(this.this$0, this.$tmdbId, this.$mediaType, this.$season, this.$episode, dVar);
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
        HttpLocalScraperRuntime httpLocalScraperRuntime = this.this$0;
        int i11 = this.$tmdbId;
        String str = this.$mediaType;
        Integer num = this.$season;
        Integer num2 = this.$episode;
        this.label = 1;
        Object objResolveVidMody = httpLocalScraperRuntime.resolveVidMody(i11, str, num, num2, this);
        e7.a aVar = e7.a.f15033i;
        return objResolveVidMody == aVar ? aVar : objResolveVidMody;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<HttpLocalScraperRuntime.HttpResolvedStream>> dVar) {
        return ((HttpLocalScraperRuntime$resolveHttpStreams$2$jobs$1$3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
