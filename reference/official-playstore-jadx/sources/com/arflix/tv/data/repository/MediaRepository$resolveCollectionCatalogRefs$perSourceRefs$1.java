package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.CatalogConfig;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0005\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1", f = "MediaRepository.kt", l = {286}, m = "invokeSuspend", v = 2)
public final class MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends List<? extends x6.x>>>, Object> {
    final /* synthetic */ CatalogConfig $catalog;
    final /* synthetic */ List<Integer> $sourceBudgets;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1(CatalogConfig catalogConfig, MediaRepository mediaRepository, List<Integer> list, d7.d<? super MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1> dVar) {
        super(2, dVar);
        this.$catalog = catalogConfig;
        this.this$0 = mediaRepository;
        this.$sourceBudgets = list;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1 mediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1 = new MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1(this.$catalog, this.this$0, this.$sourceBudgets, dVar);
        mediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1.L$0 = obj;
        return mediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00be  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00b6 -> B:23:0x00b7). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r13.label
            r2 = 1
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            int r0 = r13.I$1
            int r1 = r13.I$0
            java.lang.Object r5 = r13.L$7
            java.util.Collection r5 = (java.util.Collection) r5
            java.lang.Object r6 = r13.L$6
            ka.p0 r6 = (ka.p0) r6
            java.lang.Object r6 = r13.L$4
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r13.L$3
            java.util.Collection r7 = (java.util.Collection) r7
            java.lang.Object r8 = r13.L$2
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.lang.Object r8 = r13.L$1
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            k2.c.G(r14)
            goto Lb7
        L2e:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L36:
            k2.c.G(r14)
            com.arflix.tv.data.model.CatalogConfig r14 = r13.$catalog
            java.util.List r14 = r14.getCollectionSources()
            com.arflix.tv.data.repository.MediaRepository r6 = r13.this$0
            java.util.List<java.lang.Integer> r8 = r13.$sourceBudgets
            java.util.ArrayList r1 = new java.util.ArrayList
            r11 = 10
            int r5 = kotlin.collections.s.U(r14, r11)
            r1.<init>(r5)
            java.util.Iterator r14 = r14.iterator()
            r9 = r3
        L53:
            boolean r5 = r14.hasNext()
            if (r5 == 0) goto L78
            java.lang.Object r5 = r14.next()
            int r12 = r9 + 1
            if (r9 < 0) goto L74
            r7 = r5
            com.arflix.tv.data.model.CollectionSourceConfig r7 = (com.arflix.tv.data.model.CollectionSourceConfig) r7
            com.arflix.tv.data.repository.MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1 r5 = new com.arflix.tv.data.repository.MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1$1$1
            r10 = 0
            r5.<init>(r6, r7, r8, r9, r10)
            r7 = 3
            ka.q0 r5 = ka.m0.e(r7, r4, r0, r5)
            r1.add(r5)
            r9 = r12
            goto L53
        L74:
            t7.a.Q()
            throw r4
        L78:
            java.util.ArrayList r14 = new java.util.ArrayList
            int r0 = kotlin.collections.s.U(r1, r11)
            r14.<init>(r0)
            java.util.Iterator r0 = r1.iterator()
            r5 = r14
            r6 = r0
            r0 = r3
            r1 = r0
        L89:
            boolean r14 = r6.hasNext()
            if (r14 == 0) goto Lbe
            java.lang.Object r14 = r6.next()
            ka.p0 r14 = (ka.p0) r14
            r13.L$0 = r4
            r13.L$1 = r4
            r13.L$2 = r4
            r13.L$3 = r5
            r13.L$4 = r6
            r13.L$5 = r4
            r13.L$6 = r4
            r13.L$7 = r5
            r13.I$0 = r1
            r13.I$1 = r0
            r13.I$2 = r3
            r13.label = r2
            java.lang.Object r14 = r14.o(r13)
            e7.a r7 = e7.a.f15033i
            if (r14 != r7) goto Lb6
            return r7
        Lb6:
            r7 = r5
        Lb7:
            java.util.List r14 = (java.util.List) r14
            r5.add(r14)
            r5 = r7
            goto L89
        Lbe:
            java.util.List r5 = (java.util.List) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends List<? extends x6.x>>> dVar) {
        return ((MediaRepository$resolveCollectionCatalogRefs$perSourceRefs$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
