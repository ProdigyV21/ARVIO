package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$parseAddonPageRefs$2$resolvedTitleRefs$1$1", f = "MediaRepository.kt", l = {3970, 2577}, m = "invokeSuspend", v = 2)
public final class MediaRepository$parseAddonPageRefs$2$resolvedTitleRefs$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ MediaType $hint;
    final /* synthetic */ String $title;
    final /* synthetic */ ua.f $titleSemaphore;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$parseAddonPageRefs$2$resolvedTitleRefs$1$1(ua.f fVar, MediaRepository mediaRepository, String str, MediaType mediaType, d7.d<? super MediaRepository$parseAddonPageRefs$2$resolvedTitleRefs$1$1> dVar) {
        super(2, dVar);
        this.$titleSemaphore = fVar;
        this.this$0 = mediaRepository;
        this.$title = str;
        this.$hint = mediaType;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MediaRepository$parseAddonPageRefs$2$resolvedTitleRefs$1$1(this.$titleSemaphore, this.this$0, this.$title, this.$hint, dVar);
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
            com.arflix.tv.data.model.MediaType r3 = (com.arflix.tv.data.model.MediaType) r3
            java.lang.Object r5 = r8.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r8.L$1
            com.arflix.tv.data.repository.MediaRepository r6 = (com.arflix.tv.data.repository.MediaRepository) r6
            java.lang.Object r7 = r8.L$0
            ua.f r7 = (ua.f) r7
            k2.c.G(r9)
            r9 = r0
            r0 = r7
            goto L58
        L37:
            k2.c.G(r9)
            ua.f r9 = r8.$titleSemaphore
            com.arflix.tv.data.repository.MediaRepository r6 = r8.this$0
            java.lang.String r5 = r8.$title
            com.arflix.tv.data.model.MediaType r0 = r8.$hint
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
            java.lang.Object r9 = com.arflix.tv.data.repository.MediaRepository.access$resolveTitleToTmdbRef(r6, r5, r3, r8)     // Catch: java.lang.Throwable -> L15
            if (r9 != r4) goto L6e
        L6d:
            return r4
        L6e:
            x6.x r9 = (x6.x) r9     // Catch: java.lang.Throwable -> L15
            r0.release()
            return r9
        L74:
            r0.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$parseAddonPageRefs$2$resolvedTitleRefs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((MediaRepository$parseAddonPageRefs$2$resolvedTitleRefs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
