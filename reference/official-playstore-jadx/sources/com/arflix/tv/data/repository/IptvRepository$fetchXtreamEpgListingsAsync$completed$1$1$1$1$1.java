package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1$1$1$1", f = "IptvRepository.kt", l = {8688, 6147, 6156, 6166}, m = "invokeSuspend", v = 2)
public final class IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $allowUnboundedFallback;
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ ua.f $gate;
    final /* synthetic */ int $listingLimit;
    final /* synthetic */ ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> $listingsResult;
    final /* synthetic */ r7.p<Integer, Boolean, t0> $onStreamProcessed;
    final /* synthetic */ AtomicBoolean $sampleLogged;
    final /* synthetic */ int $sid;
    final /* synthetic */ AtomicInteger $simpleFallbacks;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1$1$1$1(ua.f fVar, IptvRepository.XtreamCredentials xtreamCredentials, int i10, int i11, IptvRepository iptvRepository, boolean z, AtomicInteger atomicInteger, ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> concurrentLinkedQueue, AtomicBoolean atomicBoolean, r7.p<? super Integer, ? super Boolean, t0> pVar, d7.d<? super IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1$1$1$1> dVar) {
        super(2, dVar);
        this.$gate = fVar;
        this.$creds = xtreamCredentials;
        this.$sid = i10;
        this.$listingLimit = i11;
        this.this$0 = iptvRepository;
        this.$allowUnboundedFallback = z;
        this.$simpleFallbacks = atomicInteger;
        this.$listingsResult = concurrentLinkedQueue;
        this.$sampleLogged = atomicBoolean;
        this.$onStreamProcessed = pVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1$1$1$1(this.$gate, this.$creds, this.$sid, this.$listingLimit, this.this$0, this.$allowUnboundedFallback, this.$simpleFallbacks, this.$listingsResult, this.$sampleLogged, this.$onStreamProcessed, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:(1:(10:150|(1:(1:(16:8|144|9|10|81|(1:83)|84|(1:89)|90|91|(4:152|94|(1:96)(5:97|98|148|99|(3:101|(1:103)(1:105)|106))|109)(0)|118|(1:120)(1:121)|122|123|124)(2:15|16))(18:17|146|18|19|136|65|(1:67)(1:68)|69|(1:(2:77|(1:156)(13:80|81|(0)|84|(2:86|89)|90|91|(0)(0)|118|(0)(0)|122|123|124)))|75|92|91|(0)(0)|118|(0)(0)|122|123|124))(16:24|140|25|26|142|46|(2:138|48)(1:53)|(1:(3:60|61|(1:157)(11:64|136|65|(0)(0)|69|(7:72|74|(0)|75|92|91|(0)(0))(0)|118|(0)(0)|122|123|124)))|58|70|(0)(0)|118|(0)(0)|122|123|124)|23|14|117|118|(0)(0)|122|123|124)(1:28))(2:30|(2:32|79)(1:33))|134|34|130|35|36|154|37|38|132|39|40|41|42|(2:44|155)(10:45|142|46|(0)(0)|(6:55|57|(0)|58|70|(0)(0))(0)|118|(0)(0)|122|123|124)) */
    /* JADX WARN: Can't wrap try/catch for region: R(19:0|2|(1:(10:150|(1:(1:(16:8|144|9|10|81|(1:83)|84|(1:89)|90|91|(4:152|94|(1:96)(5:97|98|148|99|(3:101|(1:103)(1:105)|106))|109)(0)|118|(1:120)(1:121)|122|123|124)(2:15|16))(18:17|146|18|19|136|65|(1:67)(1:68)|69|(1:(2:77|(1:156)(13:80|81|(0)|84|(2:86|89)|90|91|(0)(0)|118|(0)(0)|122|123|124)))|75|92|91|(0)(0)|118|(0)(0)|122|123|124))(16:24|140|25|26|142|46|(2:138|48)(1:53)|(1:(3:60|61|(1:157)(11:64|136|65|(0)(0)|69|(7:72|74|(0)|75|92|91|(0)(0))(0)|118|(0)(0)|122|123|124)))|58|70|(0)(0)|118|(0)(0)|122|123|124)|23|14|117|118|(0)(0)|122|123|124)(1:28))(2:30|(2:32|79)(1:33))|29|134|34|130|35|36|154|37|38|132|39|40|41|42|(2:44|155)(10:45|142|46|(0)(0)|(6:55|57|(0)|58|70|(0)(0))(0)|118|(0)(0)|122|123|124)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0427, code lost:
    
        r7 = true;
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0429, code lost:
    
        r3 = r6;
        r4 = r11;
        r9 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x042d, code lost:
    
        r8 = false;
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0430, code lost:
    
        r11 = r22;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0221 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0392 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0248 A[Catch: all -> 0x0226, Exception -> 0x022a, TRY_LEAVE, TryCatch #4 {Exception -> 0x022a, blocks: (B:48:0x0221, B:55:0x0233, B:60:0x0248), top: B:138:0x0221 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02c8 A[Catch: all -> 0x0226, Exception -> 0x022c, TryCatch #3 {Exception -> 0x022c, blocks: (B:81:0x0363, B:84:0x0368, B:86:0x037e, B:89:0x0385, B:65:0x02c4, B:67:0x02c8, B:72:0x02ef, B:77:0x02fc), top: B:136:0x02c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02ef A[Catch: all -> 0x0226, Exception -> 0x022c, TryCatch #3 {Exception -> 0x022c, blocks: (B:81:0x0363, B:84:0x0368, B:86:0x037e, B:89:0x0385, B:65:0x02c4, B:67:0x02c8, B:72:0x02ef, B:77:0x02fc), top: B:136:0x02c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02fc A[Catch: all -> 0x0226, Exception -> 0x022c, TryCatch #3 {Exception -> 0x022c, blocks: (B:81:0x0363, B:84:0x0368, B:86:0x037e, B:89:0x0385, B:65:0x02c4, B:67:0x02c8, B:72:0x02ef, B:77:0x02fc), top: B:136:0x02c4 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0398  */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r13v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v23, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r9v9 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1103
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
