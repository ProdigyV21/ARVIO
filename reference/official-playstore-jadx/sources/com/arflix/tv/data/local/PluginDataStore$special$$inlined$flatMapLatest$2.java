package com.arflix.tv.data.local;

import com.arflix.tv.domain.model.ScraperInfo;
import d7.d;
import f7.e;
import f7.j;
import java.util.List;
import k2.c;
import kotlin.Metadata;
import na.k;
import na.y0;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u008a@"}, d2 = {"T", "R", "Lna/k;", "it", "Lx6/t0;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.local.PluginDataStore$special$$inlined$flatMapLatest$2", f = "PluginDataStore.kt", l = {193}, m = "invokeSuspend", v = 2)
public final class PluginDataStore$special$$inlined$flatMapLatest$2 extends j implements q<k<? super List<? extends ScraperInfo>>, Integer, d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PluginDataStore this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginDataStore$special$$inlined$flatMapLatest$2(d dVar, PluginDataStore pluginDataStore) {
        super(3, dVar);
        this.this$0 = pluginDataStore;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((k<? super List<? extends ScraperInfo>>) obj, (Integer) obj2, (d<? super t0>) obj3);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            k kVar = (k) this.L$0;
            final na.j data = this.this$0.factory.get(((Number) this.L$1).intValue(), "plugin_settings").getData();
            final PluginDataStore pluginDataStore = this.this$0;
            na.j<List<? extends ScraperInfo>> jVar = new na.j<List<? extends ScraperInfo>>() { // from class: com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1

                /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1$2, reason: invalid class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
                public static final class AnonymousClass2<T> implements k {
                    final /* synthetic */ k $this_unsafeFlow;
                    final /* synthetic */ PluginDataStore this$0;

                    /* JADX INFO: renamed from: com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1$2$1, reason: invalid class name */
                    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                    @e(c = "com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1$2", f = "PluginDataStore.kt", l = {223}, m = "emit", v = 2)
                    public static final class AnonymousClass1 extends f7.c {
                        int I$0;
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        int label;
                        /* synthetic */ Object result;

                        public AnonymousClass1(d dVar) {
                            super(dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            this.result = obj;
                            this.label |= Integer.MIN_VALUE;
                            return AnonymousClass2.this.emit(null, this);
                        }
                    }

                    public AnonymousClass2(k kVar, PluginDataStore pluginDataStore) {
                        this.$this_unsafeFlow = kVar;
                        this.this$0 = pluginDataStore;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                    @Override // na.k
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object emit(java.lang.Object r8, d7.d r9) throws java.lang.Exception {
                        /*
                            r7 = this;
                            boolean r0 = r9 instanceof com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r9
                            com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1$2$1 r0 = (com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1$2$1 r0 = new com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1$2$1
                            r0.<init>(r9)
                        L18:
                            java.lang.Object r9 = r0.result
                            int r1 = r0.label
                            r2 = 1
                            if (r1 == 0) goto L35
                            if (r1 != r2) goto L2d
                            java.lang.Object r8 = r0.L$3
                            na.k r8 = (na.k) r8
                            java.lang.Object r8 = r0.L$1
                            com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1$2$1 r8 = (com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1.AnonymousClass2.AnonymousClass1) r8
                            k2.c.G(r9)
                            goto L8e
                        L2d:
                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                            r8.<init>(r9)
                            throw r8
                        L35:
                            k2.c.G(r9)
                            na.k r9 = r7.$this_unsafeFlow
                            c1.g r8 = (c1.g) r8
                            com.arflix.tv.data.local.PluginDataStore r1 = r7.this$0
                            c1.e r1 = com.arflix.tv.data.local.PluginDataStore.access$getScrapersKey$p(r1)
                            c1.b r8 = (c1.b) r8
                            java.util.LinkedHashMap r8 = r8.f7368a
                            java.lang.Object r8 = r8.get(r1)
                            java.lang.String r8 = (java.lang.String) r8
                            kotlin.collections.z r1 = kotlin.collections.z.f19728i
                            r3 = 0
                            if (r8 == 0) goto L78
                            com.arflix.tv.data.local.PluginDataStore r4 = r7.this$0     // Catch: java.lang.Exception -> L71
                            com.squareup.moshi.z r4 = com.arflix.tv.data.local.PluginDataStore.access$getMoshi$p(r4)     // Catch: java.lang.Exception -> L71
                            com.arflix.tv.data.local.PluginDataStore r5 = r7.this$0     // Catch: java.lang.Exception -> L71
                            java.lang.reflect.ParameterizedType r5 = com.arflix.tv.data.local.PluginDataStore.access$getScraperListType$p(r5)     // Catch: java.lang.Exception -> L71
                            r4.getClass()     // Catch: java.lang.Exception -> L71
                            java.util.Set r6 = j6.e.f19235a     // Catch: java.lang.Exception -> L71
                            com.squareup.moshi.l r4 = r4.a(r5, r6, r3)     // Catch: java.lang.Exception -> L71
                            java.lang.Object r8 = r4.b(r8)     // Catch: java.lang.Exception -> L71
                            java.util.List r8 = (java.util.List) r8     // Catch: java.lang.Exception -> L71
                            if (r8 != 0) goto L6f
                            goto L78
                        L6f:
                            r1 = r8
                            goto L78
                        L71:
                            r8 = move-exception
                            boolean r4 = r8 instanceof java.util.concurrent.CancellationException
                            if (r4 != 0) goto L77
                            goto L78
                        L77:
                            throw r8
                        L78:
                            r0.L$0 = r3
                            r0.L$1 = r3
                            r0.L$2 = r3
                            r0.L$3 = r3
                            r8 = 0
                            r0.I$0 = r8
                            r0.label = r2
                            java.lang.Object r8 = r9.emit(r1, r0)
                            e7.a r9 = e7.a.f15033i
                            if (r8 != r9) goto L8e
                            return r9
                        L8e:
                            x6.t0 r8 = x6.t0.f22605a
                            return r8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.local.PluginDataStore$scrapers$lambda$0$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                    }
                }

                @Override // na.j
                public Object collect(k kVar2, d dVar) {
                    Object objCollect = data.collect(new AnonymousClass2(kVar2, pluginDataStore), dVar);
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
            c.G(obj);
        }
        return t0.f22605a;
    }

    public final Object invoke(k<? super List<? extends ScraperInfo>> kVar, Integer num, d<? super t0> dVar) {
        PluginDataStore$special$$inlined$flatMapLatest$2 pluginDataStore$special$$inlined$flatMapLatest$2 = new PluginDataStore$special$$inlined$flatMapLatest$2(dVar, this.this$0);
        pluginDataStore$special$$inlined$flatMapLatest$2.L$0 = kVar;
        pluginDataStore$special$$inlined$flatMapLatest$2.L$1 = num;
        return pluginDataStore$special$$inlined$flatMapLatest$2.invokeSuspend(t0.f22605a);
    }
}
