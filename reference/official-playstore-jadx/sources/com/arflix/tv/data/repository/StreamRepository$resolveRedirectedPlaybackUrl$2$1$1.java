package com.arflix.tv.data.repository;

import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$resolveRedirectedPlaybackUrl$2$1$1", f = "StreamRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class StreamRepository$resolveRedirectedPlaybackUrl$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ Map<String, String> $headers;
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$resolveRedirectedPlaybackUrl$2$1$1(Map<String, String> map, String str, StreamRepository streamRepository, d7.d<? super StreamRepository$resolveRedirectedPlaybackUrl$2$1$1> dVar) {
        super(2, dVar);
        this.$headers = map;
        this.$url = str;
        this.this$0 = streamRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$resolveRedirectedPlaybackUrl$2$1$1(this.$headers, this.$url, this.this$0, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0110  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepository$resolveRedirectedPlaybackUrl$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((StreamRepository$resolveRedirectedPlaybackUrl$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
