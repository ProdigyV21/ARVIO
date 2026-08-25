package io.ktor.client.plugins.cache.storage;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import d7.j;
import f7.c;
import f7.e;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.SavedHttpCall;
import io.ktor.client.plugins.cache.HttpCacheEntry;
import io.ktor.client.plugins.cache.HttpCacheEntryKt;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.Url;
import io.ktor.util.InternalAPI;
import io.ktor.util.date.GMTDate;
import io.ktor.utils.io.ByteReadChannel;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a/\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\b\u001a\u00020\f*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0087@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\r\u001a3\u0010\b\u001a\u00020\f*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eH\u0087@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0011\u001a=\u0010\b\u001a\u00020\f*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0012\u001a+\u0010\u0019\u001a\u00020\u0003*\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "Lio/ktor/http/Url;", "url", "Lio/ktor/client/statement/HttpResponse;", "value", "", "isShared", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "store", "(Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;Lio/ktor/http/Url;Lio/ktor/client/statement/HttpResponse;ZLd7/d;)Ljava/lang/Object;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "response", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "", "", "varyKeys", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Ljava/util/Map;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Ljava/util/Map;ZLd7/d;)Ljava/lang/Object;", "Lio/ktor/client/HttpClient;", "client", "Lio/ktor/client/request/HttpRequest;", "request", "Ld7/j;", "responseContext", "createResponse", "(Lio/ktor/client/plugins/cache/storage/CachedResponseData;Lio/ktor/client/HttpClient;Lio/ktor/client/request/HttpRequest;Ld7/j;)Lio/ktor/client/statement/HttpResponse;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpCacheStorageKt {

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.HttpCacheStorageKt", f = "HttpCacheStorage.kt", l = {57}, m = "store")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCacheStorageKt.store((HttpCacheStorage) null, (Url) null, (HttpResponse) null, false, (d<? super HttpCacheEntry>) this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.storage.HttpCacheStorageKt", f = "HttpCacheStorage.kt", l = {125, TsExtractor.TS_STREAM_TYPE_DTS}, m = "store")
    public static final class AnonymousClass4 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass4(d<? super AnonymousClass4> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCacheStorageKt.store((CacheStorage) null, (HttpResponse) null, (Map<String, String>) null, false, (d<? super CachedResponseData>) this);
        }
    }

    public static final HttpResponse createResponse(final CachedResponseData cachedResponseData, HttpClient httpClient, HttpRequest httpRequest, final j jVar) {
        return new SavedHttpCall(httpClient, httpRequest, new HttpResponse(cachedResponseData, jVar) { // from class: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$createResponse$response$1
            private final j coroutineContext;
            private final Headers headers;
            private final GMTDate requestTime;
            private final GMTDate responseTime;
            private final HttpStatusCode status;
            private final HttpProtocolVersion version;

            {
                this.status = cachedResponseData.getStatusCode();
                this.version = cachedResponseData.getVersion();
                this.requestTime = cachedResponseData.getRequestTime();
                this.responseTime = cachedResponseData.getResponseTime();
                this.headers = cachedResponseData.getHeaders();
                this.coroutineContext = jVar;
            }

            @InternalAPI
            public static /* synthetic */ void getContent$annotations() {
            }

            @Override // io.ktor.client.statement.HttpResponse
            public HttpClientCall getCall() {
                throw new IllegalStateException("This is a fake response");
            }

            @Override // io.ktor.client.statement.HttpResponse
            public ByteReadChannel getContent() {
                throw new IllegalStateException("This is a fake response");
            }

            @Override // io.ktor.client.statement.HttpResponse, ka.k0
            public j getCoroutineContext() {
                return this.coroutineContext;
            }

            @Override // io.ktor.http.HttpMessage
            public Headers getHeaders() {
                return this.headers;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public GMTDate getRequestTime() {
                return this.requestTime;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public GMTDate getResponseTime() {
                return this.responseTime;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public HttpStatusCode getStatus() {
                return this.status;
            }

            @Override // io.ktor.client.statement.HttpResponse
            public HttpProtocolVersion getVersion() {
                return this.version;
            }
        }, cachedResponseData.getBody()).getResponse();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object store(io.ktor.client.plugins.cache.storage.HttpCacheStorage r4, io.ktor.http.Url r5, io.ktor.client.statement.HttpResponse r6, boolean r7, d7.d<? super io.ktor.client.plugins.cache.HttpCacheEntry> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1 r0 = (io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1 r0 = new io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r4 = r0.L$1
            r5 = r4
            io.ktor.http.Url r5 = (io.ktor.http.Url) r5
            java.lang.Object r4 = r0.L$0
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r4 = (io.ktor.client.plugins.cache.storage.HttpCacheStorage) r4
            k2.c.G(r8)
            goto L48
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            k2.c.G(r8)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r8 = io.ktor.client.plugins.cache.HttpCacheEntryKt.HttpCacheEntry(r7, r6, r0)
            e7.a r6 = e7.a.f15033i
            if (r8 != r6) goto L48
            return r6
        L48:
            io.ktor.client.plugins.cache.HttpCacheEntry r8 = (io.ktor.client.plugins.cache.HttpCacheEntry) r8
            r4.store(r5, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(io.ktor.client.plugins.cache.storage.HttpCacheStorage, io.ktor.http.Url, io.ktor.client.statement.HttpResponse, boolean, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object store$default(CacheStorage cacheStorage, HttpResponse httpResponse, Map map, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        return store(cacheStorage, httpResponse, (Map<String, String>) map, z, (d<? super CachedResponseData>) dVar);
    }

    @x6.e
    public static final Object store(CacheStorage cacheStorage, HttpResponse httpResponse, d<? super CachedResponseData> dVar) {
        return store$default(cacheStorage, httpResponse, HttpCacheEntryKt.varyKeys(httpResponse), false, dVar, 4, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object store(io.ktor.client.plugins.cache.storage.CacheStorage r22, io.ktor.client.statement.HttpResponse r23, java.util.Map<java.lang.String, java.lang.String> r24, boolean r25, d7.d<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r26) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(io.ktor.client.plugins.cache.storage.CacheStorage, io.ktor.client.statement.HttpResponse, java.util.Map, boolean, d7.d):java.lang.Object");
    }
}
