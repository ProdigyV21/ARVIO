package io.ktor.client.plugins.cache.storage;

import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.http.Url;
import io.ktor.util.CryptoKt;
import io.ktor.util.collections.ConcurrentMap;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import java.io.File;
import java.security.MessageDigest;
import java.util.List;
import ka.f0;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;
import ua.a;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00142\u0006\u0010\r\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u0012\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u001bJ\u001b\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u001cH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u001dJ#\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J!\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00142\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J1\u0010%\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\b2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0#H\u0096@ø\u0001\u0000¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010'R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010(R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020*0)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lio/ktor/client/plugins/cache/storage/FileCacheStorage;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "Ljava/io/File;", "directory", "Lka/f0;", "dispatcher", "<init>", "(Ljava/io/File;Lka/f0;)V", "Lio/ktor/http/Url;", "url", "", "key", "(Lio/ktor/http/Url;)Ljava/lang/String;", "urlHex", "", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "caches", "", "writeCache", "(Ljava/lang/String;Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "", "readCache", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteChannel;", "channel", "cache", "Lx6/t0;", "(Lio/ktor/utils/io/ByteChannel;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "data", "store", "(Lio/ktor/http/Url;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Ld7/d;)Ljava/lang/Object;", "findAll", "(Lio/ktor/http/Url;Ld7/d;)Ljava/lang/Object;", "", "varyKeys", "find", "(Lio/ktor/http/Url;Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "Ljava/io/File;", "Lka/f0;", "Lio/ktor/util/collections/ConcurrentMap;", "Lua/a;", "mutexes", "Lio/ktor/util/collections/ConcurrentMap;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FileCacheStorage implements CacheStorage {
    private final File directory;
    private final f0 dispatcher;
    private final ConcurrentMap<String, a> mutexes;

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage", f = "FileCacheStorage.kt", l = {81}, m = "find")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileCacheStorage.this.find(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage", f = "FileCacheStorage.kt", l = {77}, m = "findAll")
    public static final class C17381 extends c {
        int label;
        /* synthetic */ Object result;

        public C17381(d<? super C17381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileCacheStorage.this.findAll(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage", f = "FileCacheStorage.kt", l = {202, 119, 122, 124}, m = "readCache")
    public static final class C17391 extends c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C17391(d<? super C17391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileCacheStorage.this.readCache((String) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage", f = "FileCacheStorage.kt", l = {159, 160, 160, 161, 162, 165, 166, 169, 170, 171, TsExtractor.TS_STREAM_TYPE_AC4, 175, 176, 180, 182}, m = "readCache")
    public static final class AnonymousClass3 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileCacheStorage.this.readCache((ByteReadChannel) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.FileCacheStorage$store$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage$store$2", f = "FileCacheStorage.kt", l = {72, 73}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ CachedResponseData $data;
        final /* synthetic */ Url $url;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Url url, CachedResponseData cachedResponseData, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$url = url;
            this.$data = cachedResponseData;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return FileCacheStorage.this.new AnonymousClass2(this.$url, this.$data, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x0075, code lost:
        
            if (r2.writeCache(r0, r9, r8) == r3) goto L21;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
            /*
                r8 = this;
                int r0 = r8.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L20
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r9)
                goto L78
            L10:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L18:
                java.lang.Object r0 = r8.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r9)
                goto L38
            L20:
                k2.c.G(r9)
                io.ktor.client.plugins.cache.storage.FileCacheStorage r9 = io.ktor.client.plugins.cache.storage.FileCacheStorage.this
                io.ktor.http.Url r0 = r8.$url
                java.lang.String r0 = io.ktor.client.plugins.cache.storage.FileCacheStorage.access$key(r9, r0)
                io.ktor.client.plugins.cache.storage.FileCacheStorage r9 = io.ktor.client.plugins.cache.storage.FileCacheStorage.this
                r8.L$0 = r0
                r8.label = r2
                java.lang.Object r9 = io.ktor.client.plugins.cache.storage.FileCacheStorage.access$readCache(r9, r0, r8)
                if (r9 != r3) goto L38
                goto L77
            L38:
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                io.ktor.client.plugins.cache.storage.CachedResponseData r2 = r8.$data
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>()
                java.util.Iterator r9 = r9.iterator()
            L45:
                boolean r5 = r9.hasNext()
                if (r5 == 0) goto L64
                java.lang.Object r5 = r9.next()
                r6 = r5
                io.ktor.client.plugins.cache.storage.CachedResponseData r6 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r6
                java.util.Map r6 = r6.getVaryKeys()
                java.util.Map r7 = r2.getVaryKeys()
                boolean r6 = kotlin.jvm.internal.p.a(r6, r7)
                if (r6 != 0) goto L45
                r4.add(r5)
                goto L45
            L64:
                io.ktor.client.plugins.cache.storage.CachedResponseData r9 = r8.$data
                java.util.ArrayList r9 = kotlin.collections.x.H0(r9, r4)
                io.ktor.client.plugins.cache.storage.FileCacheStorage r2 = io.ktor.client.plugins.cache.storage.FileCacheStorage.this
                r4 = 0
                r8.L$0 = r4
                r8.label = r1
                java.lang.Object r9 = io.ktor.client.plugins.cache.storage.FileCacheStorage.access$writeCache(r2, r0, r9, r8)
                if (r9 != r3) goto L78
            L77:
                return r3
            L78:
                x6.t0 r9 = x6.t0.f22605a
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2", f = "FileCacheStorage.kt", l = {202, MediaError.DetailedErrorCode.MEDIA_DECODE}, m = "invokeSuspend")
    public static final class C17402 extends j implements p<k0, d<? super Object>, Object> {
        final /* synthetic */ List<CachedResponseData> $caches;
        final /* synthetic */ String $urlHex;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C17402(String str, List<CachedResponseData> list, d<? super C17402> dVar) {
            super(2, dVar);
            this.$urlHex = str;
            this.$caches = list;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C17402 c17402 = FileCacheStorage.this.new C17402(this.$urlHex, this.$caches, dVar);
            c17402.L$0 = obj;
            return c17402;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(9:(1:68)|(1:(1:(8:6|57|7|8|28|29|51|52)(2:11|12))(1:13))(3:15|(1:18)|26)|66|22|23|59|24|(5:27|28|29|51|52)|26)|14|19|64|20|61|21|(2:(0)|(1:63))) */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00e8, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00ea, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v0 */
        /* JADX WARN: Type inference failed for: r10v1 */
        /* JADX WARN: Type inference failed for: r10v11 */
        /* JADX WARN: Type inference failed for: r10v2, types: [ua.a] */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r10v4 */
        /* JADX WARN: Type inference failed for: r10v7 */
        /* JADX WARN: Type inference failed for: r10v8, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v10 */
        /* JADX WARN: Type inference failed for: r2v11 */
        /* JADX WARN: Type inference failed for: r2v12 */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v15, types: [ua.a] */
        /* JADX WARN: Type inference failed for: r2v16 */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        /* JADX WARN: Type inference failed for: r2v19 */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v4 */
        /* JADX WARN: Type inference failed for: r2v5, types: [ua.a] */
        /* JADX WARN: Type inference failed for: r2v8 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 277
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.C17402.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<Object> dVar) {
            return ((C17402) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage", f = "FileCacheStorage.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD, 137, TsExtractor.TS_STREAM_TYPE_DTS, TsExtractor.TS_STREAM_TYPE_DTS_UHD, 141, 143, 144, 146, 147, 148, 149, 151, 152, 154, 155}, m = "writeCache")
    public static final class C17413 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C17413(d<? super C17413> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileCacheStorage.this.writeCache((ByteChannel) null, (CachedResponseData) null, this);
        }
    }

    public FileCacheStorage(File file, f0 f0Var) {
        this.directory = file;
        this.dispatcher = f0Var;
        this.mutexes = new ConcurrentMap<>(0, 1, null);
        file.mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String key(Url url) {
        return CryptoKt.hex(MessageDigest.getInstance("MD5").digest(url.getUrlString().getBytes(kotlin.text.a.f19924a)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0165 A[Catch: all -> 0x0160, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0160, blocks: (B:50:0x0126, B:60:0x0165), top: B:92:0x0126 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0126 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.Object, ua.a] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.Object, ua.a] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [d7.d, io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1] */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v22, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [d7.d, io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v30, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.BufferedInputStream, java.io.InputStream, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v4, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v4, types: [io.ktor.client.plugins.cache.storage.FileCacheStorage, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0142 -> B:102:0x014c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readCache(java.lang.String r19, d7.d<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.readCache(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object writeCache(String str, List<CachedResponseData> list, d<Object> dVar) {
        return l0.c(new C17402(str, list, null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object find(io.ktor.http.Url r6, java.util.Map<java.lang.String, java.lang.String> r7, d7.d<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.FileCacheStorage.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1 r0 = (io.ktor.client.plugins.cache.storage.FileCacheStorage.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1 r0 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r6 = r0.L$0
            r7 = r6
            java.util.Map r7 = (java.util.Map) r7
            k2.c.G(r8)
            goto L46
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            k2.c.G(r8)
            java.lang.String r6 = r5.key(r6)
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r8 = r5.readCache(r6, r0)
            e7.a r6 = e7.a.f15033i
            if (r8 != r6) goto L46
            return r6
        L46:
            java.util.Set r8 = (java.util.Set) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r6 = r8.iterator()
        L4e:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L92
            java.lang.Object r8 = r6.next()
            r0 = r8
            io.ktor.client.plugins.cache.storage.CachedResponseData r0 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r0
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L62
            return r8
        L62:
            java.util.Set r1 = r7.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L6a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L91
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            java.util.Map r4 = r0.getVaryKeys()
            java.lang.Object r3 = r4.get(r3)
            boolean r2 = kotlin.jvm.internal.p.a(r3, r2)
            if (r2 != 0) goto L6a
            goto L4e
        L91:
            return r8
        L92:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.find(io.ktor.http.Url, java.util.Map, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object findAll(io.ktor.http.Url r5, d7.d<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.plugins.cache.storage.FileCacheStorage.C17381
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1 r0 = (io.ktor.client.plugins.cache.storage.FileCacheStorage.C17381) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1 r0 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)
            goto L3f
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r6)
            java.lang.String r5 = r4.key(r5)
            r0.label = r2
            java.lang.Object r6 = r4.readCache(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L3f
            return r5
        L3f:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Set r5 = kotlin.collections.x.g1(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.findAll(io.ktor.http.Url, d7.d):java.lang.Object");
    }

    @Override // io.ktor.client.plugins.cache.storage.CacheStorage
    public Object store(Url url, CachedResponseData cachedResponseData, d<? super t0> dVar) throws Throwable {
        Object objY = m0.y(this.dispatcher, new AnonymousClass2(url, cachedResponseData, null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x01a7, code lost:
    
        if (r1.writeInt(r11, r0) != r4) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x026d, code lost:
    
        if (r10.writeInt(r11, r0) != r4) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0308, code lost:
    
        if (io.ktor.utils.io.ByteWriteChannelKt.writeFully(r10, r9, r0) != r4) goto L85;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00dd A[PHI: r9 r10
      0x00dd: PHI (r9v12 io.ktor.client.plugins.cache.storage.CachedResponseData) = 
      (r9v9 io.ktor.client.plugins.cache.storage.CachedResponseData)
      (r9v16 io.ktor.client.plugins.cache.storage.CachedResponseData)
     binds: [B:39:0x018a, B:23:0x00d2] A[DONT_GENERATE, DONT_INLINE]
      0x00dd: PHI (r10v11 io.ktor.utils.io.ByteChannel) = (r10v8 io.ktor.utils.io.ByteChannel), (r10v14 io.ktor.utils.io.ByteChannel) binds: [B:39:0x018a, B:23:0x00d2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x016c A[PHI: r9 r10
      0x016c: PHI (r9v9 io.ktor.client.plugins.cache.storage.CachedResponseData) = 
      (r9v6 io.ktor.client.plugins.cache.storage.CachedResponseData)
      (r9v11 io.ktor.client.plugins.cache.storage.CachedResponseData)
     binds: [B:36:0x0168, B:25:0x00e1] A[DONT_GENERATE, DONT_INLINE]
      0x016c: PHI (r10v8 io.ktor.utils.io.ByteChannel) = (r10v5 io.ktor.utils.io.ByteChannel), (r10v10 io.ktor.utils.io.ByteChannel) binds: [B:36:0x0168, B:25:0x00e1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0243 A[PHI: r9 r10
      0x0243: PHI (r9v38 io.ktor.client.plugins.cache.storage.CachedResponseData) = 
      (r9v35 io.ktor.client.plugins.cache.storage.CachedResponseData)
      (r9v40 io.ktor.client.plugins.cache.storage.CachedResponseData)
     binds: [B:60:0x023f, B:18:0x0081] A[DONT_GENERATE, DONT_INLINE]
      0x0243: PHI (r10v30 io.ktor.utils.io.ByteChannel) = (r10v27 io.ktor.utils.io.ByteChannel), (r10v32 io.ktor.utils.io.ByteChannel) binds: [B:60:0x023f, B:18:0x0081] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0259 A[PHI: r9 r10
      0x0259: PHI (r9v41 io.ktor.client.plugins.cache.storage.CachedResponseData) = 
      (r9v38 io.ktor.client.plugins.cache.storage.CachedResponseData)
      (r9v43 io.ktor.client.plugins.cache.storage.CachedResponseData)
     binds: [B:63:0x0255, B:17:0x0074] A[DONT_GENERATE, DONT_INLINE]
      0x0259: PHI (r10v33 io.ktor.utils.io.ByteChannel) = (r10v30 io.ktor.utils.io.ByteChannel), (r10v35 io.ktor.utils.io.ByteChannel) binds: [B:63:0x0255, B:17:0x0074] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x020b -> B:45:0x01af). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:78:0x02de -> B:69:0x027d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeCache(io.ktor.utils.io.ByteChannel r9, io.ktor.client.plugins.cache.storage.CachedResponseData r10, d7.d<? super x6.t0> r11) {
        /*
            Method dump skipped, instruction units count: 818
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.writeCache(io.ktor.utils.io.ByteChannel, io.ktor.client.plugins.cache.storage.CachedResponseData, d7.d):java.lang.Object");
    }

    public FileCacheStorage(File file, f0 f0Var, int i10, h hVar) {
        this(file, (i10 & 2) != 0 ? x0.f19655d : f0Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x02b0, code lost:
    
        if (r1 != r8) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0301, code lost:
    
        if (r1 != r8) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0398, code lost:
    
        if (r1 != r8) goto L71;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0279 A[PHI: r0 r1 r4 r6
      0x0279: PHI (r0v7 int) = (r0v4 int), (r0v9 int) binds: [B:38:0x0275, B:27:0x0219] A[DONT_GENERATE, DONT_INLINE]
      0x0279: PHI (r1v11 java.lang.Object) = (r1v10 java.lang.Object), (r1v1 java.lang.Object) binds: [B:38:0x0275, B:27:0x0219] A[DONT_GENERATE, DONT_INLINE]
      0x0279: PHI (r4v8 java.lang.String) = (r4v4 java.lang.String), (r4v11 java.lang.String) binds: [B:38:0x0275, B:27:0x0219] A[DONT_GENERATE, DONT_INLINE]
      0x0279: PHI (r6v4 io.ktor.utils.io.ByteReadChannel) = (r6v2 io.ktor.utils.io.ByteReadChannel), (r6v7 io.ktor.utils.io.ByteReadChannel) binds: [B:38:0x0275, B:27:0x0219] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0352 A[PHI: r0 r1 r4 r6 r7 r9 r10
      0x0352: PHI (r0v28 io.ktor.util.date.GMTDate) = (r0v25 io.ktor.util.date.GMTDate), (r0v30 io.ktor.util.date.GMTDate) binds: [B:62:0x034e, B:20:0x016c] A[DONT_GENERATE, DONT_INLINE]
      0x0352: PHI (r1v36 java.lang.Object) = (r1v35 java.lang.Object), (r1v1 java.lang.Object) binds: [B:62:0x034e, B:20:0x016c] A[DONT_GENERATE, DONT_INLINE]
      0x0352: PHI (r4v30 io.ktor.http.HeadersBuilder) = (r4v26 io.ktor.http.HeadersBuilder), (r4v33 io.ktor.http.HeadersBuilder) binds: [B:62:0x034e, B:20:0x016c] A[DONT_GENERATE, DONT_INLINE]
      0x0352: PHI (r6v28 io.ktor.http.HttpProtocolVersion) = (r6v24 io.ktor.http.HttpProtocolVersion), (r6v31 io.ktor.http.HttpProtocolVersion) binds: [B:62:0x034e, B:20:0x016c] A[DONT_GENERATE, DONT_INLINE]
      0x0352: PHI (r7v23 io.ktor.http.HttpStatusCode) = (r7v19 io.ktor.http.HttpStatusCode), (r7v26 io.ktor.http.HttpStatusCode) binds: [B:62:0x034e, B:20:0x016c] A[DONT_GENERATE, DONT_INLINE]
      0x0352: PHI (r9v16 java.lang.String) = (r9v12 java.lang.String), (r9v19 java.lang.String) binds: [B:62:0x034e, B:20:0x016c] A[DONT_GENERATE, DONT_INLINE]
      0x0352: PHI (r10v15 io.ktor.utils.io.ByteReadChannel) = (r10v13 io.ktor.utils.io.ByteReadChannel), (r10v18 io.ktor.utils.io.ByteReadChannel) binds: [B:62:0x034e, B:20:0x016c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0479  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0301 -> B:23:0x01c6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:80:0x040a -> B:16:0x00cd). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readCache(io.ktor.utils.io.ByteReadChannel r24, d7.d<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r25) {
        /*
            Method dump skipped, instruction units count: 1202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.readCache(io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }
}
