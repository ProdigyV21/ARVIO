package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbSeasonDetails;
import j$.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/ContinueWatchingItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$enrichContinueWatchingItems$2$1$1", f = "TraktRepository.kt", l = {4806, 2516}, m = "invokeSuspend", v = 2)
public final class TraktRepository$enrichContinueWatchingItems$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super ContinueWatchingItem>, Object> {
    final /* synthetic */ ContinueWatchingItem $item;
    final /* synthetic */ ConcurrentHashMap<x6.x, ka.p0<TmdbSeasonDetails>> $seasonCache;
    final /* synthetic */ ua.f $semaphore;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$enrichContinueWatchingItems$2$1$1(ua.f fVar, TraktRepository traktRepository, ContinueWatchingItem continueWatchingItem, ConcurrentHashMap<x6.x, ka.p0<TmdbSeasonDetails>> concurrentHashMap, d7.d<? super TraktRepository$enrichContinueWatchingItems$2$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.this$0 = traktRepository;
        this.$item = continueWatchingItem;
        this.$seasonCache = concurrentHashMap;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktRepository$enrichContinueWatchingItems$2$1$1(this.$semaphore, this.this$0, this.$item, this.$seasonCache, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x006b, code lost:
    
        if (r9 == r4) goto L20;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v5, types: [ua.f] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            int r0 = r8.label
            r1 = 0
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L37
            if (r0 == r3) goto L1f
            if (r0 != r2) goto L17
            java.lang.Object r0 = r8.L$0
            ua.f r0 = (ua.f) r0
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L15
            goto L6e
        L15:
            r9 = move-exception
            goto L74
        L17:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L1f:
            int r0 = r8.I$0
            java.lang.Object r3 = r8.L$3
            j$.util.concurrent.ConcurrentHashMap r3 = (j$.util.concurrent.ConcurrentHashMap) r3
            java.lang.Object r5 = r8.L$2
            com.arflix.tv.data.repository.ContinueWatchingItem r5 = (com.arflix.tv.data.repository.ContinueWatchingItem) r5
            java.lang.Object r6 = r8.L$1
            com.arflix.tv.data.repository.TraktRepository r6 = (com.arflix.tv.data.repository.TraktRepository) r6
            java.lang.Object r7 = r8.L$0
            ua.f r7 = (ua.f) r7
            k2.c.G(r9)
            r9 = r0
            r0 = r7
            goto L58
        L37:
            k2.c.G(r9)
            ua.f r9 = r8.$semaphore
            com.arflix.tv.data.repository.TraktRepository r6 = r8.this$0
            com.arflix.tv.data.repository.ContinueWatchingItem r5 = r8.$item
            j$.util.concurrent.ConcurrentHashMap<x6.x, ka.p0<com.arflix.tv.data.api.TmdbSeasonDetails>> r0 = r8.$seasonCache
            r8.L$0 = r9
            r8.L$1 = r6
            r8.L$2 = r5
            r8.L$3 = r0
            r8.I$0 = r1
            r8.label = r3
            java.lang.Object r3 = r9.a(r8)
            if (r3 != r4) goto L55
            goto L6d
        L55:
            r3 = r0
            r0 = r9
            r9 = r1
        L58:
            r8.L$0 = r0     // Catch: java.lang.Throwable -> L15
            r7 = 0
            r8.L$1 = r7     // Catch: java.lang.Throwable -> L15
            r8.L$2 = r7     // Catch: java.lang.Throwable -> L15
            r8.L$3 = r7     // Catch: java.lang.Throwable -> L15
            r8.I$0 = r9     // Catch: java.lang.Throwable -> L15
            r8.I$1 = r1     // Catch: java.lang.Throwable -> L15
            r8.label = r2     // Catch: java.lang.Throwable -> L15
            java.lang.Object r9 = com.arflix.tv.data.repository.TraktRepository.access$enrichLocalContinueWatchingItem(r6, r5, r3, r8)     // Catch: java.lang.Throwable -> L15
            if (r9 != r4) goto L6e
        L6d:
            return r4
        L6e:
            com.arflix.tv.data.repository.ContinueWatchingItem r9 = (com.arflix.tv.data.repository.ContinueWatchingItem) r9     // Catch: java.lang.Throwable -> L15
            r0.release()
            return r9
        L74:
            r0.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TraktRepository$enrichContinueWatchingItems$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super ContinueWatchingItem> dVar) {
        return ((TraktRepository$enrichContinueWatchingItems$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
