package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.ArmApi;
import com.arflix.tv.data.api.JikanApi;
import j$.util.DesugarCollections;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\nH\u0082@¢\u0006\u0004\b\u000f\u0010\u0010J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0086@¢\u0006\u0004\b\u0011\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\"\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/repository/AnimeScoreRepository;", "", "Lcom/arflix/tv/data/api/ArmApi;", "armApi", "Lcom/arflix/tv/data/api/JikanApi;", "jikanApi", "<init>", "(Lcom/arflix/tv/data/api/ArmApi;Lcom/arflix/tv/data/api/JikanApi;)V", "", "imdbId", "", "resolveMalId", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "malId", "", "resolveScore", "(ILd7/d;)Ljava/lang/Object;", "getMalScore", "Lcom/arflix/tv/data/api/ArmApi;", "Lcom/arflix/tv/data/api/JikanApi;", "", "malIdCache", "Ljava/util/Map;", "scoreCache", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AnimeScoreRepository {
    public static final int $stable = 0;
    private final ArmApi armApi;
    private final JikanApi jikanApi;
    private final Map<String, Integer> malIdCache = DesugarCollections.synchronizedMap(new AnimeScoreRepository$malIdCache$1());
    private final Map<Integer, Double> scoreCache = DesugarCollections.synchronizedMap(new AnimeScoreRepository$scoreCache$1());

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AnimeScoreRepository$getMalScore$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)D"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.AnimeScoreRepository$getMalScore$2", f = "AnimeScoreRepository.kt", l = {61, 62}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super Double>, Object> {
        final /* synthetic */ String $imdbId;
        int I$0;
        Object L$0;
        int label;
        final /* synthetic */ AnimeScoreRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, AnimeScoreRepository animeScoreRepository, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$imdbId = str;
            this.this$0 = animeScoreRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$imdbId, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                String str = this.$imdbId;
                String string = str != null ? kotlin.text.o.L0(str).toString() : null;
                if (string == null) {
                    string = "";
                }
                if (string.length() == 0) {
                    return null;
                }
                AnimeScoreRepository animeScoreRepository = this.this$0;
                this.L$0 = null;
                this.label = 1;
                obj = animeScoreRepository.resolveMalId(string, this);
                if (obj != aVar) {
                }
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            Integer num = (Integer) obj;
            if (num == null) {
                return null;
            }
            int iIntValue = num.intValue();
            AnimeScoreRepository animeScoreRepository2 = this.this$0;
            this.L$0 = null;
            this.I$0 = iIntValue;
            this.label = 2;
            Object objResolveScore = animeScoreRepository2.resolveScore(iIntValue, this);
            return objResolveScore == aVar ? aVar : objResolveScore;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Double> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AnimeScoreRepository$resolveMalId$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AnimeScoreRepository", f = "AnimeScoreRepository.kt", l = {70}, m = "resolveMalId", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeScoreRepository.this.resolveMalId(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AnimeScoreRepository$resolveScore$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AnimeScoreRepository", f = "AnimeScoreRepository.kt", l = {81}, m = "resolveScore", v = 2)
    public static final class C06711 extends f7.c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C06711(d7.d<? super C06711> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeScoreRepository.this.resolveScore(0, this);
        }
    }

    @Inject
    public AnimeScoreRepository(ArmApi armApi, JikanApi jikanApi) {
        this.armApi = armApi;
        this.jikanApi = jikanApi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveMalId(java.lang.String r5, d7.d<? super java.lang.Integer> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.AnimeScoreRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.AnimeScoreRepository$resolveMalId$1 r0 = (com.arflix.tv.data.repository.AnimeScoreRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AnimeScoreRepository$resolveMalId$1 r0 = new com.arflix.tv.data.repository.AnimeScoreRepository$resolveMalId$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.lang.Integer r5 = (java.lang.Integer) r5
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L62
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            java.util.Map<java.lang.String, java.lang.Integer> r6 = r4.malIdCache
            java.lang.Object r6 = r6.get(r5)
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 != 0) goto L69
            java.util.Map<java.lang.String, java.lang.Integer> r1 = r4.malIdCache
            boolean r1 = r1.containsKey(r5)
            if (r1 == 0) goto L4b
            goto L69
        L4b:
            com.arflix.tv.data.repository.AnimeScoreRepository$resolveMalId$resolved$1 r6 = new com.arflix.tv.data.repository.AnimeScoreRepository$resolveMalId$resolved$1
            r1 = 0
            r6.<init>(r4, r5, r1)
            r0.L$0 = r5
            r0.L$1 = r1
            r0.label = r2
            r1 = 2000(0x7d0, double:9.88E-321)
            java.lang.Object r6 = ka.m0.A(r1, r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L62
            return r0
        L62:
            java.lang.Integer r6 = (java.lang.Integer) r6
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r4.malIdCache
            r0.put(r5, r6)
        L69:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AnimeScoreRepository.resolveMalId(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveScore(int r5, d7.d<? super java.lang.Double> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.AnimeScoreRepository.C06711
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.AnimeScoreRepository$resolveScore$1 r0 = (com.arflix.tv.data.repository.AnimeScoreRepository.C06711) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AnimeScoreRepository$resolveScore$1 r0 = new com.arflix.tv.data.repository.AnimeScoreRepository$resolveScore$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            int r5 = r0.I$0
            java.lang.Object r0 = r0.L$0
            java.lang.Double r0 = (java.lang.Double) r0
            k2.c.G(r6)
            goto L6a
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r6)
            java.util.Map<java.lang.Integer, java.lang.Double> r6 = r4.scoreCache
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r5)
            java.lang.Object r6 = r6.get(r1)
            java.lang.Double r6 = (java.lang.Double) r6
            if (r6 != 0) goto L76
            java.util.Map<java.lang.Integer, java.lang.Double> r1 = r4.scoreCache
            java.lang.Integer r3 = new java.lang.Integer
            r3.<init>(r5)
            boolean r1 = r1.containsKey(r3)
            if (r1 == 0) goto L53
            goto L76
        L53:
            com.arflix.tv.data.repository.AnimeScoreRepository$resolveScore$score$1 r6 = new com.arflix.tv.data.repository.AnimeScoreRepository$resolveScore$score$1
            r1 = 0
            r6.<init>(r4, r5, r1)
            r0.L$0 = r1
            r0.I$0 = r5
            r0.label = r2
            r1 = 2000(0x7d0, double:9.88E-321)
            java.lang.Object r6 = ka.m0.A(r1, r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L6a
            return r0
        L6a:
            java.lang.Double r6 = (java.lang.Double) r6
            java.util.Map<java.lang.Integer, java.lang.Double> r0 = r4.scoreCache
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r5)
            r0.put(r1, r6)
        L76:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AnimeScoreRepository.resolveScore(int, d7.d):java.lang.Object");
    }

    public final Object getMalScore(String str, d7.d<? super Double> dVar) {
        return ka.m0.y(x0.f19655d, new AnonymousClass2(str, this, null), dVar);
    }
}
