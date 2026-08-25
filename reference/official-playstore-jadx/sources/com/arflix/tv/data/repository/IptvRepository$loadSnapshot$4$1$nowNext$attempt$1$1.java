package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$loadSnapshot$4$1$nowNext$attempt$1$1", f = "IptvRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class IptvRepository$loadSnapshot$4$1$nowNext$attempt$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<String, ? extends IptvNowNext>>, Object> {
    final /* synthetic */ List<IptvChannel> $candidateChannels;
    final /* synthetic */ String $epgUrl;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$loadSnapshot$4$1$nowNext$attempt$1$1(IptvRepository iptvRepository, String str, List<IptvChannel> list, d7.d<? super IptvRepository$loadSnapshot$4$1$nowNext$attempt$1$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$epgUrl = str;
        this.$candidateChannels = list;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$loadSnapshot$4$1$nowNext$attempt$1$1(this.this$0, this.$epgUrl, this.$candidateChannels, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return this.this$0.fetchAndParseEpg(this.$epgUrl, this.$candidateChannels);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Map<String, IptvNowNext>> dVar) {
        return ((IptvRepository$loadSnapshot$4$1$nowNext$attempt$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
