package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchAndParseM3uWithRetries$2$1$1", f = "IptvRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class IptvRepository$fetchAndParseM3uWithRetries$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvChannel>>, Object> {
    final /* synthetic */ String $normalizedUrl;
    final /* synthetic */ r7.l<IptvLoadProgress, t0> $onProgress;
    final /* synthetic */ IptvRepository $this_runCatching;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IptvRepository$fetchAndParseM3uWithRetries$2$1$1(IptvRepository iptvRepository, String str, r7.l<? super IptvLoadProgress, t0> lVar, d7.d<? super IptvRepository$fetchAndParseM3uWithRetries$2$1$1> dVar) {
        super(2, dVar);
        this.$this_runCatching = iptvRepository;
        this.$normalizedUrl = str;
        this.$onProgress = lVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$fetchAndParseM3uWithRetries$2$1$1(this.$this_runCatching, this.$normalizedUrl, this.$onProgress, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return IptvRepository.fetchAndParseM3uOnce$default(this.$this_runCatching, this.$normalizedUrl, this.$onProgress, null, 4, null);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvChannel>> dVar) {
        return ((IptvRepository$fetchAndParseM3uWithRetries$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
