package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H\u0086@¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b\u0016\u0010\u0017J\u001e\u0010\u001a\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u0018H\u0086@¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u0018H\u0086@¢\u0006\u0004\b\u001c\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/repository/TraktOutboxRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/ProfileManager;)V", "Lc1/e;", "", "outboxKey", "()Lc1/e;", "json", "", "Lcom/arflix/tv/data/repository/TraktOutboxItem;", "decode", "(Ljava/lang/String;)Ljava/util/List;", "", "loadAll", "(Ld7/d;)Ljava/lang/Object;", "item", "Lx6/t0;", "enqueue", "(Lcom/arflix/tv/data/repository/TraktOutboxItem;Ld7/d;)Ljava/lang/Object;", "", "ids", "remove", "(Ljava/util/Set;Ld7/d;)Ljava/lang/Object;", "incrementAttempts", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TraktOutboxRepository {
    public static final int $stable = 8;
    private final Context context;
    private final Gson gson = new Gson();
    private final ProfileManager profileManager;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.TraktOutboxRepository$enqueue$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.TraktOutboxRepository$enqueue$2", f = "TraktOutboxRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ TraktOutboxItem $item;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TraktOutboxItem traktOutboxItem, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$item = traktOutboxItem;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = TraktOutboxRepository.this.new AnonymousClass2(this.$item, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            TraktOutboxRepository traktOutboxRepository = TraktOutboxRepository.this;
            List listDecode = traktOutboxRepository.decode((String) bVar.f7368a.get(traktOutboxRepository.outboxKey()));
            listDecode.add(this.$item);
            bVar.d(TraktOutboxRepository.this.outboxKey(), TraktOutboxRepository.this.gson.toJson(listDecode));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.TraktOutboxRepository$incrementAttempts$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.TraktOutboxRepository$incrementAttempts$2", f = "TraktOutboxRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C10412 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ Set<String> $ids;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C10412(Set<String> set, d7.d<? super C10412> dVar) {
            super(2, dVar);
            this.$ids = set;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C10412 c10412 = TraktOutboxRepository.this.new C10412(this.$ids, dVar);
            c10412.L$0 = obj;
            return c10412;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C10412) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            TraktOutboxRepository traktOutboxRepository = TraktOutboxRepository.this;
            List<TraktOutboxItem> listDecode = traktOutboxRepository.decode((String) bVar.f7368a.get(traktOutboxRepository.outboxKey()));
            Set<String> set = this.$ids;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listDecode, 10));
            for (TraktOutboxItem traktOutboxItemCopy$default : listDecode) {
                if (set.contains(traktOutboxItemCopy$default.getId())) {
                    traktOutboxItemCopy$default = TraktOutboxItem.copy$default(traktOutboxItemCopy$default, null, null, null, null, null, null, null, null, null, null, traktOutboxItemCopy$default.getAttempts() + 1, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
                }
                arrayList.add(traktOutboxItemCopy$default);
            }
            bVar.d(TraktOutboxRepository.this.outboxKey(), TraktOutboxRepository.this.gson.toJson(arrayList));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.TraktOutboxRepository$loadAll$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.TraktOutboxRepository", f = "TraktOutboxRepository.kt", l = {53}, m = "loadAll", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TraktOutboxRepository.this.loadAll(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.TraktOutboxRepository$remove$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.TraktOutboxRepository$remove$2", f = "TraktOutboxRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C10422 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ Set<String> $ids;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C10422(Set<String> set, d7.d<? super C10422> dVar) {
            super(2, dVar);
            this.$ids = set;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C10422 c10422 = TraktOutboxRepository.this.new C10422(this.$ids, dVar);
            c10422.L$0 = obj;
            return c10422;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C10422) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            TraktOutboxRepository traktOutboxRepository = TraktOutboxRepository.this;
            List listDecode = traktOutboxRepository.decode((String) bVar.f7368a.get(traktOutboxRepository.outboxKey()));
            Set<String> set = this.$ids;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : listDecode) {
                if (!set.contains(((TraktOutboxItem) obj2).getId())) {
                    arrayList.add(obj2);
                }
            }
            bVar.d(TraktOutboxRepository.this.outboxKey(), TraktOutboxRepository.this.gson.toJson(arrayList));
            return t0.f22605a;
        }
    }

    @Inject
    public TraktOutboxRepository(@ApplicationContext Context context, ProfileManager profileManager) {
        this.context = context;
        this.profileManager = profileManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<TraktOutboxItem> decode(String json) {
        if (json == null || kotlin.text.o.h0(json)) {
            return new ArrayList();
        }
        List<TraktOutboxItem> list = (List) this.gson.fromJson(json, TypeToken.getParameterized(List.class, TraktOutboxItem.class).getType());
        return list == null ? new ArrayList() : list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e outboxKey() {
        return this.profileManager.profileStringKey("trakt_outbox_items");
    }

    public final Object enqueue(TraktOutboxItem traktOutboxItem, d7.d<? super t0> dVar) {
        Object objO = androidx.work.impl.t.o(TraktOutboxRepositoryKt.getTraktOutboxDataStore(this.context), new AnonymousClass2(traktOutboxItem, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    public final Object incrementAttempts(Set<String> set, d7.d<? super t0> dVar) {
        Object objO;
        boolean zIsEmpty = set.isEmpty();
        t0 t0Var = t0.f22605a;
        return (!zIsEmpty && (objO = androidx.work.impl.t.o(TraktOutboxRepositoryKt.getTraktOutboxDataStore(this.context), new C10412(set, null), dVar)) == e7.a.f15033i) ? objO : t0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadAll(d7.d<? super java.util.List<com.arflix.tv.data.repository.TraktOutboxItem>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.TraktOutboxRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.TraktOutboxRepository$loadAll$1 r0 = (com.arflix.tv.data.repository.TraktOutboxRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.TraktOutboxRepository$loadAll$1 r0 = new com.arflix.tv.data.repository.TraktOutboxRepository$loadAll$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L45
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            android.content.Context r5 = r4.context
            z0.g r5 = com.arflix.tv.data.repository.TraktOutboxRepositoryKt.access$getTraktOutboxDataStore(r5)
            na.j r5 = r5.getData()
            r0.label = r2
            java.lang.Object r5 = na.y0.l(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L45
            return r0
        L45:
            c1.g r5 = (c1.g) r5
            c1.e r0 = r4.outboxKey()
            c1.b r5 = (c1.b) r5
            java.util.LinkedHashMap r5 = r5.f7368a
            java.lang.Object r5 = r5.get(r0)
            java.lang.String r5 = (java.lang.String) r5
            if (r5 != 0) goto L5a
            kotlin.collections.z r5 = kotlin.collections.z.f19728i
            return r5
        L5a:
            java.util.List r5 = r4.decode(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TraktOutboxRepository.loadAll(d7.d):java.lang.Object");
    }

    public final Object remove(Set<String> set, d7.d<? super t0> dVar) {
        Object objO;
        boolean zIsEmpty = set.isEmpty();
        t0 t0Var = t0.f22605a;
        return (!zIsEmpty && (objO = androidx.work.impl.t.o(TraktOutboxRepositoryKt.getTraktOutboxDataStore(this.context), new C10422(set, null), dVar)) == e7.a.f15033i) ? objO : t0Var;
    }
}
