package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1", f = "MediaRepository.kt", l = {3970, 1871}, m = "invokeSuspend", v = 2)
public final class MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super MediaItem>, Object> {
    final /* synthetic */ ua.f $semaphore;
    final /* synthetic */ HomeServerCatalogItem $serverItem;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1(ua.f fVar, MediaRepository mediaRepository, HomeServerCatalogItem homeServerCatalogItem, d7.d<? super MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.this$0 = mediaRepository;
        this.$serverItem = homeServerCatalogItem;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1 mediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1 = new MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1(this.$semaphore, this.this$0, this.$serverItem, dVar);
        mediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1.L$0 = obj;
        return mediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0070, code lost:
    
        if (r9 == r6) goto L20;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ka.k0] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r0v4, types: [ua.f] */
    /* JADX WARN: Type inference failed for: r0v8 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r8.label
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L3c
            if (r1 == r3) goto L28
            if (r1 != r2) goto L20
            java.lang.Object r0 = r8.L$2
            ka.k0 r0 = (ka.k0) r0
            java.lang.Object r0 = r8.L$1
            ua.f r0 = (ua.f) r0
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L1e
            goto L73
        L1e:
            r9 = move-exception
            goto L76
        L20:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L28:
            int r0 = r8.I$0
            java.lang.Object r1 = r8.L$3
            com.arflix.tv.data.repository.HomeServerCatalogItem r1 = (com.arflix.tv.data.repository.HomeServerCatalogItem) r1
            java.lang.Object r3 = r8.L$2
            com.arflix.tv.data.repository.MediaRepository r3 = (com.arflix.tv.data.repository.MediaRepository) r3
            java.lang.Object r7 = r8.L$1
            ua.f r7 = (ua.f) r7
            k2.c.G(r9)
            r9 = r0
            r0 = r7
            goto L5c
        L3c:
            k2.c.G(r9)
            ua.f r9 = r8.$semaphore
            com.arflix.tv.data.repository.MediaRepository r1 = r8.this$0
            com.arflix.tv.data.repository.HomeServerCatalogItem r7 = r8.$serverItem
            r8.L$0 = r0
            r8.L$1 = r9
            r8.L$2 = r1
            r8.L$3 = r7
            r8.I$0 = r4
            r8.label = r3
            java.lang.Object r0 = r9.a(r8)
            if (r0 != r6) goto L58
            goto L72
        L58:
            r0 = r9
            r3 = r1
            r9 = r4
            r1 = r7
        L5c:
            r8.L$0 = r5     // Catch: java.lang.Throwable -> L1e
            r8.L$1 = r0     // Catch: java.lang.Throwable -> L1e
            r8.L$2 = r5     // Catch: java.lang.Throwable -> L1e
            r8.L$3 = r5     // Catch: java.lang.Throwable -> L1e
            r8.I$0 = r9     // Catch: java.lang.Throwable -> L1e
            r8.I$1 = r4     // Catch: java.lang.Throwable -> L1e
            r8.I$2 = r4     // Catch: java.lang.Throwable -> L1e
            r8.label = r2     // Catch: java.lang.Throwable -> L1e
            java.lang.Object r9 = com.arflix.tv.data.repository.MediaRepository.access$resolveHomeServerCatalogItem(r3, r1, r8)     // Catch: java.lang.Throwable -> L1e
            if (r9 != r6) goto L73
        L72:
            return r6
        L73:
            com.arflix.tv.data.model.MediaItem r9 = (com.arflix.tv.data.model.MediaItem) r9     // Catch: java.lang.Throwable -> L1e
            goto L7c
        L76:
            x6.c0 r1 = new x6.c0     // Catch: java.lang.Throwable -> L89
            r1.<init>(r9)     // Catch: java.lang.Throwable -> L89
            r9 = r1
        L7c:
            boolean r1 = r9 instanceof x6.c0     // Catch: java.lang.Throwable -> L89
            if (r1 == 0) goto L82
            goto L83
        L82:
            r5 = r9
        L83:
            com.arflix.tv.data.model.MediaItem r5 = (com.arflix.tv.data.model.MediaItem) r5     // Catch: java.lang.Throwable -> L89
            r0.release()
            return r5
        L89:
            r9 = move-exception
            r0.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super MediaItem> dVar) {
        return ((MediaRepository$loadHomeServerCatalogPage$2$orderedItems$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
