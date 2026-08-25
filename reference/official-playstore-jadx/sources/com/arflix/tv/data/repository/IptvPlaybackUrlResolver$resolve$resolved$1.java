package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvPlaybackUrlResolver;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/IptvPlaybackTarget;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvPlaybackUrlResolver$resolve$resolved$1", f = "IptvPlaybackUrlResolver.kt", l = {}, m = "invokeSuspend", v = 2)
public final class IptvPlaybackUrlResolver$resolve$resolved$1 extends f7.j implements r7.p<ka.k0, d7.d<? super IptvPlaybackTarget>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ IptvPlaybackTarget $inferredTarget;
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ IptvPlaybackUrlResolver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvPlaybackUrlResolver$resolve$resolved$1(IptvPlaybackUrlResolver iptvPlaybackUrlResolver, String str, Map<String, String> map, IptvPlaybackTarget iptvPlaybackTarget, d7.d<? super IptvPlaybackUrlResolver$resolve$resolved$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvPlaybackUrlResolver;
        this.$url = str;
        this.$headers = map;
        this.$inferredTarget = iptvPlaybackTarget;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvPlaybackUrlResolver$resolve$resolved$1(this.this$0, this.$url, this.$headers, this.$inferredTarget, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        IptvPlaybackTarget target;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        IptvPlaybackUrlResolver.ProbeResult probeResultExecuteProbe = this.this$0.executeProbe(this.$url, this.$headers, true);
        if (probeResultExecuteProbe != null && probeResultExecuteProbe.isConclusive()) {
            return probeResultExecuteProbe.getTarget();
        }
        IptvPlaybackUrlResolver.ProbeResult probeResultExecuteProbe2 = this.this$0.executeProbe(this.$url, this.$headers, false);
        return (probeResultExecuteProbe2 == null || (target = probeResultExecuteProbe2.getTarget()) == null) ? this.$inferredTarget : target;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super IptvPlaybackTarget> dVar) {
        return ((IptvPlaybackUrlResolver$resolve$resolved$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
