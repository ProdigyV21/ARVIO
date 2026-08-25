package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$getWatchlistItems$2$enrichedItems$1$1$1", f = "WatchlistRepository.kt", l = {671, 267}, m = "invokeSuspend", v = 2)
public final class WatchlistRepository$getWatchlistItems$2$enrichedItems$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super MediaItem>, Object> {
    final /* synthetic */ LocalWatchlistItem $item;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ WatchlistRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistRepository$getWatchlistItems$2$enrichedItems$1$1$1(WatchlistRepository watchlistRepository, LocalWatchlistItem localWatchlistItem, d7.d<? super WatchlistRepository$getWatchlistItems$2$enrichedItems$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = watchlistRepository;
        this.$item = localWatchlistItem;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistRepository$getWatchlistItems$2$enrichedItems$1$1$1(this.this$0, this.$item, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
    
        if (r8 == r4) goto L20;
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
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.label
            r1 = 0
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L33
            if (r0 == r3) goto L1f
            if (r0 != r2) goto L17
            java.lang.Object r0 = r7.L$0
            ua.f r0 = (ua.f) r0
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L15
            goto L68
        L15:
            r8 = move-exception
            goto L6e
        L17:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1f:
            int r0 = r7.I$0
            java.lang.Object r3 = r7.L$2
            com.arflix.tv.data.repository.LocalWatchlistItem r3 = (com.arflix.tv.data.repository.LocalWatchlistItem) r3
            java.lang.Object r5 = r7.L$1
            com.arflix.tv.data.repository.WatchlistRepository r5 = (com.arflix.tv.data.repository.WatchlistRepository) r5
            java.lang.Object r6 = r7.L$0
            ua.f r6 = (ua.f) r6
            k2.c.G(r8)
            r8 = r0
            r0 = r6
            goto L54
        L33:
            k2.c.G(r8)
            com.arflix.tv.data.repository.WatchlistRepository r8 = r7.this$0
            ua.f r8 = com.arflix.tv.data.repository.WatchlistRepository.access$getTmdbSemaphore$p(r8)
            com.arflix.tv.data.repository.WatchlistRepository r5 = r7.this$0
            com.arflix.tv.data.repository.LocalWatchlistItem r0 = r7.$item
            r7.L$0 = r8
            r7.L$1 = r5
            r7.L$2 = r0
            r7.I$0 = r1
            r7.label = r3
            java.lang.Object r3 = r8.a(r7)
            if (r3 != r4) goto L51
            goto L67
        L51:
            r3 = r0
            r0 = r8
            r8 = r1
        L54:
            r7.L$0 = r0     // Catch: java.lang.Throwable -> L15
            r6 = 0
            r7.L$1 = r6     // Catch: java.lang.Throwable -> L15
            r7.L$2 = r6     // Catch: java.lang.Throwable -> L15
            r7.I$0 = r8     // Catch: java.lang.Throwable -> L15
            r7.I$1 = r1     // Catch: java.lang.Throwable -> L15
            r7.label = r2     // Catch: java.lang.Throwable -> L15
            java.lang.Object r8 = com.arflix.tv.data.repository.WatchlistRepository.access$enrichWatchlistItem(r5, r3, r7)     // Catch: java.lang.Throwable -> L15
            if (r8 != r4) goto L68
        L67:
            return r4
        L68:
            com.arflix.tv.data.model.MediaItem r8 = (com.arflix.tv.data.model.MediaItem) r8     // Catch: java.lang.Throwable -> L15
            r0.release()
            return r8
        L6e:
            r0.release()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository$getWatchlistItems$2$enrichedItems$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super MediaItem> dVar) {
        return ((WatchlistRepository$getWatchlistItems$2$enrichedItems$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
