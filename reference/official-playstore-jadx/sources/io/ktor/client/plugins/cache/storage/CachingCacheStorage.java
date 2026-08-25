package io.ktor.client.plugins.cache.storage;

import androidx.media3.extractor.flac.FlacConstants;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.Url;
import io.ktor.util.collections.ConcurrentMap;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J#\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ1\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010\u0006\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0014R&\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/client/plugins/cache/storage/CachingCacheStorage;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "delegate", "<init>", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;)V", "Lio/ktor/http/Url;", "url", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "data", "Lx6/t0;", "store", "(Lio/ktor/http/Url;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Ld7/d;)Ljava/lang/Object;", "", "", "varyKeys", "find", "(Lio/ktor/http/Url;Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "", "findAll", "(Lio/ktor/http/Url;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "Lio/ktor/util/collections/ConcurrentMap;", "Lio/ktor/util/collections/ConcurrentMap;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CachingCacheStorage implements CacheStorage {
    private final CacheStorage delegate;
    private final ConcurrentMap<Url, Set<CachedResponseData>> store = new ConcurrentMap<>(0, 1, null);

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.CachingCacheStorage", f = "FileCacheStorage.kt", l = {43}, m = "find")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CachingCacheStorage.this.find(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.CachingCacheStorage", f = "FileCacheStorage.kt", l = {53}, m = "findAll")
    public static final class C17361 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C17361(d<? super C17361> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CachingCacheStorage.this.findAll(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.CachingCacheStorage", f = "FileCacheStorage.kt", l = {37, FlacConstants.STREAM_INFO_BLOCK_SIZE}, m = "store")
    public static final class C17371 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C17371(d<? super C17371> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CachingCacheStorage.this.store(null, null, this);
        }
    }

    public CachingCacheStorage(CacheStorage cacheStorage) {
        this.delegate = cacheStorage;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object find(io.ktor.http.Url r6, java.util.Map<java.lang.String, java.lang.String> r7, d7.d<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.CachingCacheStorage.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1 r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1 r0 = new io.ktor.client.plugins.cache.storage.CachingCacheStorage$find$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L41
            if (r1 != r2) goto L39
            java.lang.Object r6 = r0.L$4
            io.ktor.http.Url r6 = (io.ktor.http.Url) r6
            java.lang.Object r7 = r0.L$3
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r1 = r0.L$2
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.http.Url r2 = (io.ktor.http.Url) r2
            java.lang.Object r0 = r0.L$0
            io.ktor.client.plugins.cache.storage.CachingCacheStorage r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage) r0
            k2.c.G(r8)
            goto L6a
        L39:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L41:
            k2.c.G(r8)
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r8 = r5.store
            boolean r8 = r8.containsKey(r6)
            if (r8 != 0) goto L70
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r8 = r5.store
            io.ktor.client.plugins.cache.storage.CacheStorage r1 = r5.delegate
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r8
            r0.L$4 = r6
            r0.label = r2
            java.lang.Object r0 = r1.findAll(r6, r0)
            e7.a r1 = e7.a.f15033i
            if (r0 != r1) goto L65
            return r1
        L65:
            r2 = r6
            r1 = r7
            r7 = r8
            r8 = r0
            r0 = r5
        L6a:
            r7.put(r6, r8)
            r7 = r1
            r6 = r2
            goto L71
        L70:
            r0 = r5
        L71:
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r8 = r0.store
            java.lang.Object r6 = kotlin.collections.h0.s0(r8, r6)
            java.util.Set r6 = (java.util.Set) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L7f:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto Lc3
            java.lang.Object r8 = r6.next()
            r0 = r8
            io.ktor.client.plugins.cache.storage.CachedResponseData r0 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r0
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L93
            return r8
        L93:
            java.util.Set r1 = r7.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L9b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto Lc2
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            java.util.Map r4 = r0.getVaryKeys()
            java.lang.Object r3 = r4.get(r3)
            boolean r2 = kotlin.jvm.internal.p.a(r3, r2)
            if (r2 != 0) goto L9b
            goto L7f
        Lc2:
            return r8
        Lc3:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.CachingCacheStorage.find(io.ktor.http.Url, java.util.Map, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object findAll(io.ktor.http.Url r5, d7.d<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.plugins.cache.storage.CachingCacheStorage.C17361
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1 r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage.C17361) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1 r0 = new io.ktor.client.plugins.cache.storage.CachingCacheStorage$findAll$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3d
            if (r1 != r2) goto L35
            java.lang.Object r5 = r0.L$3
            io.ktor.http.Url r5 = (io.ktor.http.Url) r5
            java.lang.Object r1 = r0.L$2
            java.util.Map r1 = (java.util.Map) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.http.Url r2 = (io.ktor.http.Url) r2
            java.lang.Object r0 = r0.L$0
            io.ktor.client.plugins.cache.storage.CachingCacheStorage r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage) r0
            k2.c.G(r6)
            goto L61
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            k2.c.G(r6)
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r6 = r4.store
            boolean r6 = r6.containsKey(r5)
            if (r6 != 0) goto L66
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r1 = r4.store
            io.ktor.client.plugins.cache.storage.CacheStorage r6 = r4.delegate
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r1
            r0.L$3 = r5
            r0.label = r2
            java.lang.Object r6 = r6.findAll(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L5f
            return r0
        L5f:
            r0 = r4
            r2 = r5
        L61:
            r1.put(r5, r6)
            r5 = r2
            goto L67
        L66:
            r0 = r4
        L67:
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r6 = r0.store
            java.lang.Object r5 = kotlin.collections.h0.s0(r6, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.CachingCacheStorage.findAll(io.ktor.http.Url, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object store(io.ktor.http.Url r7, io.ktor.client.plugins.cache.storage.CachedResponseData r8, d7.d<? super x6.t0> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ktor.client.plugins.cache.storage.CachingCacheStorage.C17371
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1 r0 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage.C17371) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1 r0 = new io.ktor.client.plugins.cache.storage.CachingCacheStorage$store$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L46
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            java.lang.Object r7 = r0.L$1
            io.ktor.http.Url r7 = (io.ktor.http.Url) r7
            java.lang.Object r8 = r0.L$0
            java.util.Map r8 = (java.util.Map) r8
            k2.c.G(r9)
            goto L6d
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            java.lang.Object r7 = r0.L$1
            io.ktor.http.Url r7 = (io.ktor.http.Url) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.client.plugins.cache.storage.CachingCacheStorage r8 = (io.ktor.client.plugins.cache.storage.CachingCacheStorage) r8
            k2.c.G(r9)
            goto L59
        L46:
            k2.c.G(r9)
            io.ktor.client.plugins.cache.storage.CacheStorage r9 = r6.delegate
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r9.store(r7, r8, r0)
            if (r8 != r4) goto L58
            goto L69
        L58:
            r8 = r6
        L59:
            io.ktor.util.collections.ConcurrentMap<io.ktor.http.Url, java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r9 = r8.store
            io.ktor.client.plugins.cache.storage.CacheStorage r8 = r8.delegate
            r0.L$0 = r9
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r8 = r8.findAll(r7, r0)
            if (r8 != r4) goto L6a
        L69:
            return r4
        L6a:
            r5 = r9
            r9 = r8
            r8 = r5
        L6d:
            r8.put(r7, r9)
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.CachingCacheStorage.store(io.ktor.http.Url, io.ktor.client.plugins.cache.storage.CachedResponseData, d7.d):java.lang.Object");
    }
}
