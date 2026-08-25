package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1", f = "IptvRepository.kt", l = {8688, 6230}, m = "invokeSuspend", v = 2)
public final class IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ ua.f $gate;
    final /* synthetic */ ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> $listingsResult;
    final /* synthetic */ r7.p<Integer, Boolean, t0> $onStreamProcessed;
    final /* synthetic */ int $sid;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1(ua.f fVar, IptvRepository.XtreamCredentials xtreamCredentials, int i10, IptvRepository iptvRepository, ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> concurrentLinkedQueue, r7.p<? super Integer, ? super Boolean, t0> pVar, d7.d<? super IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1> dVar) {
        super(2, dVar);
        this.$gate = fVar;
        this.$creds = xtreamCredentials;
        this.$sid = i10;
        this.this$0 = iptvRepository;
        this.$listingsResult = concurrentLinkedQueue;
        this.$onStreamProcessed = pVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1(this.$gate, this.$creds, this.$sid, this.this$0, this.$listingsResult, this.$onStreamProcessed, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(1:(1:(15:6|47|7|8|53|23|(1:25)|26|(1:28)|33|38|(1:40)|41|42|43)(2:11|12))(1:13))(3:14|(1:17)|21)|52|18|50|19|(12:22|53|23|(0)|26|(0)|33|38|(0)|41|42|43)|21) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x010a, code lost:
    
        r2 = r6;
        r5 = r7;
        r8 = r11;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00fb A[Catch: all -> 0x00ff, Exception -> 0x0102, TRY_LEAVE, TryCatch #6 {Exception -> 0x0102, all -> 0x00ff, blocks: (B:23:0x00da, B:26:0x00df, B:28:0x00fb), top: B:53:0x00da }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0116  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
