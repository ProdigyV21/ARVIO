package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StremioAddonRuntime$resolveEpisodeStreams$2$1$1", f = "AddonRuntimeImplementations.kt", l = {29}, m = "invokeSuspend", v = 2)
public final class StremioAddonRuntime$resolveEpisodeStreams$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ Addon $addon;
    final /* synthetic */ EpisodeRuntimeRequest $request;
    int label;
    final /* synthetic */ StremioAddonRuntime this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StremioAddonRuntime$resolveEpisodeStreams$2$1$1(StremioAddonRuntime stremioAddonRuntime, Addon addon, EpisodeRuntimeRequest episodeRuntimeRequest, d7.d<? super StremioAddonRuntime$resolveEpisodeStreams$2$1$1> dVar) {
        super(2, dVar);
        this.this$0 = stremioAddonRuntime;
        this.$addon = addon;
        this.$request = episodeRuntimeRequest;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StremioAddonRuntime$resolveEpisodeStreams$2$1$1(this.this$0, this.$addon, this.$request, dVar);
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
        r7.q qVar = this.this$0.episodeResolver;
        Addon addon = this.$addon;
        EpisodeRuntimeRequest episodeRuntimeRequest = this.$request;
        this.label = 1;
        Object objInvoke = qVar.invoke(addon, episodeRuntimeRequest, this);
        e7.a aVar = e7.a.f15033i;
        return objInvoke == aVar ? aVar : objInvoke;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((StremioAddonRuntime$resolveEpisodeStreams$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
