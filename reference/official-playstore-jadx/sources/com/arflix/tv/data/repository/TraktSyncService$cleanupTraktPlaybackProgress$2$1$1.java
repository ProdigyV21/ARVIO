package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$cleanupTraktPlaybackProgress$2$1$1", f = "TraktSyncService.kt", l = {2085, 1714}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$cleanupTraktPlaybackProgress$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<String> $chunk;
    final /* synthetic */ ua.f $semaphore;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$cleanupTraktPlaybackProgress$2$1$1(ua.f fVar, TraktSyncService traktSyncService, List<String> list, d7.d<? super TraktSyncService$cleanupTraktPlaybackProgress$2$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.this$0 = traktSyncService;
        this.$chunk = list;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktSyncService$cleanupTraktPlaybackProgress$2$1$1(this.$semaphore, this.this$0, this.$chunk, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x006a, code lost:
    
        if (r5.executeSupabaseCall("delete stale playback batch", r7, r9) == r4) goto L22;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v2, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r0v3, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            int r0 = r9.label
            r1 = 0
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L35
            if (r0 == r3) goto L21
            if (r0 != r2) goto L19
            java.lang.Object r0 = r9.L$0
            ua.f r0 = (ua.f) r0
            k2.c.G(r10)     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            goto L75
        L15:
            r10 = move-exception
            goto L7d
        L17:
            r10 = move-exception
            goto L6d
        L19:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L21:
            int r0 = r9.I$0
            java.lang.Object r3 = r9.L$2
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r5 = r9.L$1
            com.arflix.tv.data.repository.TraktSyncService r5 = (com.arflix.tv.data.repository.TraktSyncService) r5
            java.lang.Object r6 = r9.L$0
            ua.f r6 = (ua.f) r6
            k2.c.G(r10)
            r10 = r0
            r0 = r6
            goto L52
        L35:
            k2.c.G(r10)
            ua.f r10 = r9.$semaphore
            com.arflix.tv.data.repository.TraktSyncService r5 = r9.this$0
            java.util.List<java.lang.String> r0 = r9.$chunk
            r9.L$0 = r10
            r9.L$1 = r5
            r9.L$2 = r0
            r9.I$0 = r1
            r9.label = r3
            java.lang.Object r3 = r10.a(r9)
            if (r3 != r4) goto L4f
            goto L6c
        L4f:
            r3 = r0
            r0 = r10
            r10 = r1
        L52:
            java.lang.String r6 = "delete stale playback batch"
            com.arflix.tv.data.repository.TraktSyncService$cleanupTraktPlaybackProgress$2$1$1$1$1 r7 = new com.arflix.tv.data.repository.TraktSyncService$cleanupTraktPlaybackProgress$2$1$1$1$1     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r8 = 0
            r7.<init>(r5, r3, r8)     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r9.L$0 = r0     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r9.L$1 = r8     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r9.L$2 = r8     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r9.I$0 = r10     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r9.I$1 = r1     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r9.label = r2     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            java.lang.Object r10 = com.arflix.tv.data.repository.TraktSyncService.access$executeSupabaseCall(r5, r6, r7, r9)     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            if (r10 != r4) goto L75
        L6c:
            return r4
        L6d:
            boolean r1 = r10 instanceof java.util.concurrent.CancellationException     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L7c
            boolean r1 = r10 instanceof java.util.concurrent.CancellationException     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L7b
        L75:
            r0.release()
            x6.t0 r10 = x6.t0.f22605a
            return r10
        L7b:
            throw r10     // Catch: java.lang.Throwable -> L15
        L7c:
            throw r10     // Catch: java.lang.Throwable -> L15
        L7d:
            r0.release()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TraktSyncService$cleanupTraktPlaybackProgress$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TraktSyncService$cleanupTraktPlaybackProgress$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
