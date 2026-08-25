package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.HttpLocalScraperRuntime;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/HttpLocalScraperRuntime$HttpResolvedStream;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVideasyServers$2$1$1", f = "HttpLocalScraperRuntime.kt", l = {281, 283}, m = "invokeSuspend", v = 2)
public final class HttpLocalScraperRuntime$resolveVideasyServers$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends HttpLocalScraperRuntime.HttpResolvedStream>>, Object> {
    final /* synthetic */ HttpLocalScraperRuntime.HttpScraperTmdbDetails $details;
    final /* synthetic */ Integer $episode;
    final /* synthetic */ String $mediaType;
    final /* synthetic */ String $providerName;
    final /* synthetic */ Integer $season;
    final /* synthetic */ HttpLocalScraperRuntime.VideasyServer $server;
    final /* synthetic */ int $tmdbId;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ HttpLocalScraperRuntime this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpLocalScraperRuntime$resolveVideasyServers$2$1$1(String str, HttpLocalScraperRuntime.VideasyServer videasyServer, HttpLocalScraperRuntime httpLocalScraperRuntime, HttpLocalScraperRuntime.HttpScraperTmdbDetails httpScraperTmdbDetails, int i10, Integer num, Integer num2, String str2, d7.d<? super HttpLocalScraperRuntime$resolveVideasyServers$2$1$1> dVar) {
        super(2, dVar);
        this.$mediaType = str;
        this.$server = videasyServer;
        this.this$0 = httpLocalScraperRuntime;
        this.$details = httpScraperTmdbDetails;
        this.$tmdbId = i10;
        this.$season = num;
        this.$episode = num2;
        this.$providerName = str2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HttpLocalScraperRuntime$resolveVideasyServers$2$1$1 httpLocalScraperRuntime$resolveVideasyServers$2$1$1 = new HttpLocalScraperRuntime$resolveVideasyServers$2$1$1(this.$mediaType, this.$server, this.this$0, this.$details, this.$tmdbId, this.$season, this.$episode, this.$providerName, dVar);
        httpLocalScraperRuntime$resolveVideasyServers$2$1$1.L$0 = obj;
        return httpLocalScraperRuntime$resolveVideasyServers$2$1$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01e7 A[Catch: all -> 0x010e, TryCatch #1 {all -> 0x010e, blocks: (B:59:0x01b6, B:61:0x01ba, B:66:0x01c6, B:68:0x01ce, B:72:0x01d8, B:73:0x01e1, B:75:0x01e7, B:89:0x0291, B:78:0x01fe, B:81:0x0208, B:84:0x0232, B:87:0x025a, B:46:0x0159, B:48:0x0164, B:52:0x016f, B:55:0x0177, B:24:0x00b2, B:28:0x00c3, B:31:0x00cb, B:34:0x0109, B:39:0x0114, B:41:0x011a, B:42:0x0136), top: B:98:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02a4  */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v35 */
    /* JADX WARN: Type inference failed for: r21v0 */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v10 */
    /* JADX WARN: Type inference failed for: r21v11 */
    /* JADX WARN: Type inference failed for: r21v12 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r21v5 */
    /* JADX WARN: Type inference failed for: r21v6 */
    /* JADX WARN: Type inference failed for: r21v7 */
    /* JADX WARN: Type inference failed for: r21v8 */
    /* JADX WARN: Type inference failed for: r21v9 */
    /* JADX WARN: Type inference failed for: r6v0, types: [x6.c0] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v16, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Object] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r30) {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HttpLocalScraperRuntime$resolveVideasyServers$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<HttpLocalScraperRuntime.HttpResolvedStream>> dVar) {
        return ((HttpLocalScraperRuntime$resolveVideasyServers$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
