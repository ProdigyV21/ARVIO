package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/IptvRepository$ResolverCatalogIndex;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/IptvRepository$ResolverCatalogIndex;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$loadCatalog$2", f = "IptvRepository.kt", l = {8689, 3725}, m = "invokeSuspend", v = 2)
public final class IptvRepository$IptvSeriesResolverService$loadCatalog$2 extends f7.j implements r7.p<ka.k0, d7.d<? super IptvRepository.ResolverCatalogIndex>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ boolean $forceRefresh;
    final /* synthetic */ String $providerKey;
    int I$0;
    int I$1;
    long J$0;
    long J$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    int label;
    final /* synthetic */ IptvRepository.IptvSeriesResolverService this$0;
    final /* synthetic */ IptvRepository this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$loadCatalog$2(IptvRepository.IptvSeriesResolverService iptvSeriesResolverService, String str, boolean z, IptvRepository iptvRepository, IptvRepository.XtreamCredentials xtreamCredentials, d7.d<? super IptvRepository$IptvSeriesResolverService$loadCatalog$2> dVar) {
        super(2, dVar);
        this.this$0 = iptvSeriesResolverService;
        this.$providerKey = str;
        this.$forceRefresh = z;
        this.this$1 = iptvRepository;
        this.$creds = xtreamCredentials;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        IptvRepository$IptvSeriesResolverService$loadCatalog$2 iptvRepository$IptvSeriesResolverService$loadCatalog$2 = new IptvRepository$IptvSeriesResolverService$loadCatalog$2(this.this$0, this.$providerKey, this.$forceRefresh, this.this$1, this.$creds, dVar);
        iptvRepository$IptvSeriesResolverService$loadCatalog$2.L$0 = obj;
        return iptvRepository$IptvSeriesResolverService$loadCatalog$2;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01d5 A[Catch: all -> 0x0048, TryCatch #2 {all -> 0x0048, blocks: (B:7:0x003a, B:65:0x01d1, B:67:0x01d5, B:68:0x01d7, B:71:0x0205, B:73:0x020f, B:74:0x0244, B:76:0x0274, B:18:0x00b1, B:22:0x00ca, B:24:0x00d8, B:37:0x0116, B:40:0x011c, B:42:0x0120, B:49:0x013b, B:52:0x0141, B:54:0x0145, B:56:0x014f, B:58:0x015d, B:61:0x0191, B:48:0x0135, B:36:0x0110, B:45:0x0127), top: B:90:0x0018, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0244 A[Catch: all -> 0x0048, TryCatch #2 {all -> 0x0048, blocks: (B:7:0x003a, B:65:0x01d1, B:67:0x01d5, B:68:0x01d7, B:71:0x0205, B:73:0x020f, B:74:0x0244, B:76:0x0274, B:18:0x00b1, B:22:0x00ca, B:24:0x00d8, B:37:0x0116, B:40:0x011c, B:42:0x0120, B:49:0x013b, B:52:0x0141, B:54:0x0145, B:56:0x014f, B:58:0x015d, B:61:0x0191, B:48:0x0135, B:36:0x0110, B:45:0x0127), top: B:90:0x0018, inners: #0 }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r27) {
        /*
            Method dump skipped, instruction units count: 679
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$loadCatalog$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super IptvRepository.ResolverCatalogIndex> dVar) {
        return ((IptvRepository$IptvSeriesResolverService$loadCatalog$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
