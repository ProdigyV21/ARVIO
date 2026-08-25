package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.util.DataStoresKt;
import java.util.List;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u008a@"}, d2 = {"T", "R", "Lna/k;", "it", "Lx6/t0;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$$inlined$flatMapLatest$1", f = "CatalogRepository.kt", l = {193}, m = "invokeSuspend", v = 2)
public final class CatalogRepository$observeCatalogs$$inlined$flatMapLatest$1 extends f7.j implements r7.q<na.k<? super List<? extends CatalogConfig>>, String, d7.d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ CatalogRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogRepository$observeCatalogs$$inlined$flatMapLatest$1(d7.d dVar, CatalogRepository catalogRepository) {
        super(3, dVar);
        this.this$0 = catalogRepository;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((na.k<? super List<? extends CatalogConfig>>) obj, (String) obj2, (d7.d<? super t0>) obj3);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            na.k kVar = (na.k) this.L$0;
            final String str = (String) this.L$1;
            final na.j data = DataStoresKt.getSettingsDataStore(this.this$0.context).getData();
            final CatalogRepository catalogRepository = this.this$0;
            na.j<List<? extends CatalogConfig>> jVar = new na.j<List<? extends CatalogConfig>>() { // from class: com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1$2, reason: invalid class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
                public static final class AnonymousClass2<T> implements na.k {
                    final /* synthetic */ String $profileId$inlined;
                    final /* synthetic */ na.k $this_unsafeFlow;
                    final /* synthetic */ CatalogRepository this$0;

                    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1$2", f = "CatalogRepository.kt", l = {223}, m = "emit", v = 2)
                    public static final class AnonymousClass1 extends f7.c {
                        int I$0;
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        int label;
                        /* synthetic */ Object result;

                        public AnonymousClass1(d7.d dVar) {
                            super(dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(na.k kVar, CatalogRepository catalogRepository, String str) {
                        this.$this_unsafeFlow = kVar;
                        this.this$0 = catalogRepository;
                        this.$profileId$inlined = str;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // na.k
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object emit(java.lang.Object r5, d7.d r6) throws java.lang.Exception {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1$2$1 r0 = (com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1$2$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            int r1 = r0.label
                            r2 = 1
                            if (r1 == 0) goto L35
                            if (r1 != r2) goto L2d
                            java.lang.Object r5 = r0.L$3
                            na.k r5 = (na.k) r5
                            java.lang.Object r5 = r0.L$1
                            com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1$2$1 r5 = (com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                            k2.c.G(r6)
                            goto L5b
                        L2d:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L35:
                            k2.c.G(r6)
                            na.k r6 = r4.$this_unsafeFlow
                            c1.g r5 = (c1.g) r5
                            com.arflix.tv.data.repository.CatalogRepository r1 = r4.this$0
                            java.lang.String r3 = r4.$profileId$inlined
                            java.util.List r5 = com.arflix.tv.data.repository.CatalogRepository.access$readCatalogsFromPrefs(r1, r3, r5)
                            r1 = 0
                            r0.L$0 = r1
                            r0.L$1 = r1
                            r0.L$2 = r1
                            r0.L$3 = r1
                            r1 = 0
                            r0.I$0 = r1
                            r0.label = r2
                            java.lang.Object r5 = r6.emit(r5, r0)
                            e7.a r6 = e7.a.f15033i
                            if (r5 != r6) goto L5b
                            return r6
                        L5b:
                            x6.t0 r5 = x6.t0.f22605a
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository$observeCatalogs$lambda$0$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                    }
                }

                @Override // na.j
                public Object collect(na.k kVar2, d7.d dVar) {
                    Object objCollect = data.collect(new AnonymousClass2(kVar2, catalogRepository, str), dVar);
                    return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
                }
            };
            this.L$0 = null;
            this.L$1 = null;
            this.label = 1;
            Object objJ = y0.j(kVar, jVar, this);
            e7.a aVar = e7.a.f15033i;
            if (objJ == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    public final Object invoke(na.k<? super List<? extends CatalogConfig>> kVar, String str, d7.d<? super t0> dVar) {
        CatalogRepository$observeCatalogs$$inlined$flatMapLatest$1 catalogRepository$observeCatalogs$$inlined$flatMapLatest$1 = new CatalogRepository$observeCatalogs$$inlined$flatMapLatest$1(dVar, this.this$0);
        catalogRepository$observeCatalogs$$inlined$flatMapLatest$1.L$0 = kVar;
        catalogRepository$observeCatalogs$$inlined$flatMapLatest$1.L$1 = str;
        return catalogRepository$observeCatalogs$$inlined$flatMapLatest$1.invokeSuspend(t0.f22605a);
    }
}
