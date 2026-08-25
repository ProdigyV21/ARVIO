package com.arflix.tv.updater;

import android.content.Context;
import androidx.work.impl.t;
import c1.b;
import c1.e;
import com.arflix.tv.util.DataStoresKt;
import d7.d;
import dagger.hilt.android.qualifiers.ApplicationContext;
import e7.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import k2.c;
import kotlin.Metadata;
import na.j;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Singleton
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0086@¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u001f\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00148\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/updater/UpdatePreferences;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "tag", "Lx6/t0;", "setIgnoredTag", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "", "value", "setLastCheckAtMs", "(JLd7/d;)Ljava/lang/Object;", "Landroid/content/Context;", "Lc1/e;", "ignoredTagKey", "Lc1/e;", "lastCheckAtKey", "Lna/j;", "ignoredTag", "Lna/j;", "getIgnoredTag", "()Lna/j;", "lastCheckAtMs", "getLastCheckAtMs", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UpdatePreferences {
    public static final int $stable = 8;
    private final Context context;
    private final j<String> ignoredTag;
    private final e ignoredTagKey = new e("app_update_ignored_release_tag");
    private final e lastCheckAtKey = new e("app_update_last_check_at_ms");
    private final j<Long> lastCheckAtMs;

    /* JADX INFO: renamed from: com.arflix.tv.updater.UpdatePreferences$setIgnoredTag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.updater.UpdatePreferences$setIgnoredTag$2", f = "UpdatePreferences.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ String $tag;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ UpdatePreferences this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, UpdatePreferences updatePreferences, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$tag = str;
            this.this$0 = updatePreferences;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$tag, this.this$0, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            if (this.$tag == null) {
                bVar.c(this.this$0.ignoredTagKey);
            } else {
                bVar.d(this.this$0.ignoredTagKey, this.$tag);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.updater.UpdatePreferences$setLastCheckAtMs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.updater.UpdatePreferences$setLastCheckAtMs$2", f = "UpdatePreferences.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C16412 extends f7.j implements p<b, d<? super t0>, Object> {
        final /* synthetic */ long $value;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16412(long j10, d<? super C16412> dVar) {
            super(2, dVar);
            this.$value = j10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C16412 c16412 = UpdatePreferences.this.new C16412(this.$value, dVar);
            c16412.L$0 = obj;
            return c16412;
        }

        @Override // r7.p
        public final Object invoke(b bVar, d<? super t0> dVar) {
            return ((C16412) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            b bVar = (b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            bVar.d(UpdatePreferences.this.lastCheckAtKey, new Long(this.$value));
            return t0.f22605a;
        }
    }

    @Inject
    public UpdatePreferences(@ApplicationContext Context context) {
        this.context = context;
        final j data = DataStoresKt.getSettingsDataStore(context).getData();
        this.ignoredTag = new j<String>() { // from class: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1

            /* JADX INFO: renamed from: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements k {
                final /* synthetic */ k $this_unsafeFlow;
                final /* synthetic */ UpdatePreferences this$0;

                /* JADX INFO: renamed from: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1$2", f = "UpdatePreferences.kt", l = {223}, m = "emit", v = 2)
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

                public AnonymousClass2(k kVar, UpdatePreferences updatePreferences) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = updatePreferences;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, d7.d r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1$2$1 r0 = (com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1$2$1 r0 = new com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1$2$1
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
                        com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1$2$1 r5 = (com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                        k2.c.G(r6)
                        goto L61
                    L2d:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L35:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        c1.g r5 = (c1.g) r5
                        com.arflix.tv.updater.UpdatePreferences r1 = r4.this$0
                        c1.e r1 = com.arflix.tv.updater.UpdatePreferences.access$getIgnoredTagKey$p(r1)
                        c1.b r5 = (c1.b) r5
                        java.util.LinkedHashMap r5 = r5.f7368a
                        java.lang.Object r5 = r5.get(r1)
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
                        if (r5 != r6) goto L61
                        return r6
                    L61:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(k kVar, d dVar) {
                Object objCollect = data.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == a.f15033i ? objCollect : t0.f22605a;
            }
        };
        final j data2 = DataStoresKt.getSettingsDataStore(context).getData();
        this.lastCheckAtMs = new j<Long>() { // from class: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2

            /* JADX INFO: renamed from: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements k {
                final /* synthetic */ k $this_unsafeFlow;
                final /* synthetic */ UpdatePreferences this$0;

                /* JADX INFO: renamed from: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2$2", f = "UpdatePreferences.kt", l = {223}, m = "emit", v = 2)
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

                public AnonymousClass2(k kVar, UpdatePreferences updatePreferences) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = updatePreferences;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, d7.d r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2$2$1 r0 = (com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2$2$1 r0 = new com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L35
                        if (r1 != r2) goto L2d
                        java.lang.Object r6 = r0.L$3
                        na.k r6 = (na.k) r6
                        java.lang.Object r6 = r0.L$1
                        com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2$2$1 r6 = (com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r6
                        k2.c.G(r7)
                        goto L71
                    L2d:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L35:
                        k2.c.G(r7)
                        na.k r7 = r5.$this_unsafeFlow
                        c1.g r6 = (c1.g) r6
                        com.arflix.tv.updater.UpdatePreferences r1 = r5.this$0
                        c1.e r1 = com.arflix.tv.updater.UpdatePreferences.access$getLastCheckAtKey$p(r1)
                        c1.b r6 = (c1.b) r6
                        java.util.LinkedHashMap r6 = r6.f7368a
                        java.lang.Object r6 = r6.get(r1)
                        java.lang.Long r6 = (java.lang.Long) r6
                        if (r6 == 0) goto L53
                        long r3 = r6.longValue()
                        goto L55
                    L53:
                        r3 = 0
                    L55:
                        java.lang.Long r6 = new java.lang.Long
                        r6.<init>(r3)
                        r1 = 0
                        r0.L$0 = r1
                        r0.L$1 = r1
                        r0.L$2 = r1
                        r0.L$3 = r1
                        r1 = 0
                        r0.I$0 = r1
                        r0.label = r2
                        java.lang.Object r6 = r7.emit(r6, r0)
                        e7.a r7 = e7.a.f15033i
                        if (r6 != r7) goto L71
                        return r7
                    L71:
                        x6.t0 r6 = x6.t0.f22605a
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.updater.UpdatePreferences$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(k kVar, d dVar) {
                Object objCollect = data2.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == a.f15033i ? objCollect : t0.f22605a;
            }
        };
    }

    public final j<String> getIgnoredTag() {
        return this.ignoredTag;
    }

    public final j<Long> getLastCheckAtMs() {
        return this.lastCheckAtMs;
    }

    public final Object setIgnoredTag(String str, d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getSettingsDataStore(this.context), new AnonymousClass2(str, this, null), dVar);
        return objO == a.f15033i ? objO : t0.f22605a;
    }

    public final Object setLastCheckAtMs(long j10, d<? super t0> dVar) {
        Object objO = t.o(DataStoresKt.getSettingsDataStore(this.context), new C16412(j10, null), dVar);
        return objO == a.f15033i ? objO : t0.f22605a;
    }
}
