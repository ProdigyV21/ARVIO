package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$resolveHubCloudChain$2$1$1", f = "StreamRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class StreamRepository$resolveHubCloudChain$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ String $pageUrl;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$resolveHubCloudChain$2$1$1(String str, StreamRepository streamRepository, d7.d<? super StreamRepository$resolveHubCloudChain$2$1$1> dVar) {
        super(2, dVar);
        this.$pageUrl = str;
        this.this$0 = streamRepository;
    }

    private static final String invokeSuspend$lambda$1(StreamRepository streamRepository, kotlin.text.i iVar) {
        return streamRepository.htmlUnescape((String) iVar.b().get(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String invokeSuspend$lambda$3(StreamRepository streamRepository, kotlin.text.i iVar) {
        return streamRepository.htmlUnescape((String) iVar.b().get(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$4(String str) {
        return kotlin.text.u.P(str, "http", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$5(String str) {
        return kotlin.text.o.T(str, "/drive/", true) || kotlin.text.o.T(str, "favicon", true);
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        StreamRepository$resolveHubCloudChain$2$1$1 streamRepository$resolveHubCloudChain$2$1$1 = new StreamRepository$resolveHubCloudChain$2$1$1(this.$pageUrl, this.this$0, dVar);
        streamRepository$resolveHubCloudChain$2$1$1.L$0 = obj;
        return streamRepository$resolveHubCloudChain$2$1$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0082 A[PHI: r6
      0x0082: PHI (r6v3 java.lang.String) = (r6v2 java.lang.String), (r6v16 java.lang.String) binds: [B:20:0x003e, B:33:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepository$resolveHubCloudChain$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((StreamRepository$resolveHubCloudChain$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
