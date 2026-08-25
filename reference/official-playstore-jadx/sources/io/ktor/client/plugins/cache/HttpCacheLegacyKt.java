package io.ktor.client.plugins.cache;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import com.google.common.util.concurrent.r0;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.cache.storage.HttpCacheStorage;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.request.UtilsKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.date.GMTDate;
import io.ktor.util.pipeline.PipelineContext;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.q0;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a;\u0010\n\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0080@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a;\u0010\u000e\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a3\u0010\u0012\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001f\u0010\u0014\u001a\u00020\f*\u00020\u00032\u0006\u0010\r\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a%\u0010\u0018\u001a\u0004\u0018\u00010\f*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001aA\u0010\"\u001a\u0004\u0018\u00010!*\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001a2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\"\u0010#\u001a%\u0010\"\u001a\u0004\u0018\u00010!*\u00020\u00032\u0006\u0010$\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\"\u0010%\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/plugins/cache/HttpCache;", "plugin", "Lio/ktor/http/content/OutgoingContent;", "content", "Lio/ktor/client/HttpClient;", "scope", "Lx6/t0;", "interceptSendLegacy", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/http/content/OutgoingContent;Lio/ktor/client/HttpClient;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpResponse;", "response", "interceptReceiveLegacy", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponse;Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/HttpClient;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/call/HttpClientCall;", "cachedCall", "proceedWithWarning", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/HttpClient;Ld7/d;)Ljava/lang/Object;", "cacheResponse", "(Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/request/HttpRequest;", "request", "findAndRefresh", "(Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/request/HttpRequest;Lio/ktor/client/statement/HttpResponse;)Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "storage", "", "", "varyKeys", "Lio/ktor/http/Url;", "url", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "findResponse", "(Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;Ljava/util/Map;Lio/ktor/http/Url;Lio/ktor/client/request/HttpRequest;)Lio/ktor/client/plugins/cache/HttpCacheEntry;", "context", "(Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/http/content/OutgoingContent;)Lio/ktor/client/plugins/cache/HttpCacheEntry;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpCacheLegacyKt {

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.HttpCacheLegacyKt", f = "HttpCacheLegacy.kt", l = {111}, m = "cacheResponse")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCacheLegacyKt.cacheResponse(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.HttpCacheLegacyKt", f = "HttpCacheLegacy.kt", l = {62, ColorSpace.MaxId, 73}, m = "interceptReceiveLegacy")
    public static final class C17351 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C17351(d<? super C17351> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCacheLegacyKt.interceptReceiveLegacy(null, null, null, null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object cacheResponse(io.ktor.client.plugins.cache.HttpCache r7, io.ktor.client.statement.HttpResponse r8, d7.d<? super io.ktor.client.statement.HttpResponse> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.client.plugins.cache.HttpCacheLegacyKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1 r0 = (io.ktor.client.plugins.cache.HttpCacheLegacyKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1 r0 = new io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r9)
            goto L7d
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            k2.c.G(r9)
            io.ktor.client.call.HttpClientCall r9 = r8.getCall()
            io.ktor.client.request.HttpRequest r9 = r9.getRequest()
            java.util.List r1 = io.ktor.http.HttpMessagePropertiesKt.cacheControl(r8)
            java.util.List r3 = io.ktor.http.HttpMessagePropertiesKt.cacheControl(r9)
            io.ktor.client.plugins.cache.CacheControl r4 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r5 = r4.getPRIVATE$ktor_client_core()
            boolean r5 = r1.contains(r5)
            if (r5 == 0) goto L51
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r5 = r7.getPrivateStorage()
            goto L55
        L51:
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r5 = r7.getPublicStorage()
        L55:
            io.ktor.http.HeaderValue r6 = r4.getNO_STORE$ktor_client_core()
            boolean r1 = r1.contains(r6)
            if (r1 != 0) goto L84
            io.ktor.http.HeaderValue r1 = r4.getNO_STORE$ktor_client_core()
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L6a
            goto L84
        L6a:
            io.ktor.http.Url r9 = r9.getUrl()
            boolean r7 = r7.getIsSharedClient()
            r0.label = r2
            java.lang.Object r9 = io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(r5, r9, r8, r7, r0)
            e7.a r7 = e7.a.f15033i
            if (r9 != r7) goto L7d
            return r7
        L7d:
            io.ktor.client.plugins.cache.HttpCacheEntry r9 = (io.ktor.client.plugins.cache.HttpCacheEntry) r9
            io.ktor.client.statement.HttpResponse r7 = r9.produceResponse$ktor_client_core()
            return r7
        L84:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheLegacyKt.cacheResponse(io.ktor.client.plugins.cache.HttpCache, io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }

    private static final HttpResponse findAndRefresh(HttpCache httpCache, HttpRequest httpRequest, HttpResponse httpResponse) {
        Url url = httpResponse.getCall().getRequest().getUrl();
        HttpCacheStorage privateStorage = HttpMessagePropertiesKt.cacheControl(httpResponse).contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core()) ? httpCache.getPrivateStorage() : httpCache.getPublicStorage();
        Map<String, String> mapVaryKeys = HttpCacheEntryKt.varyKeys(httpResponse);
        HttpCacheEntry httpCacheEntryFindResponse = findResponse(httpCache, privateStorage, mapVaryKeys, url, httpRequest);
        if (httpCacheEntryFindResponse == null) {
            return null;
        }
        if (mapVaryKeys.isEmpty()) {
            mapVaryKeys = httpCacheEntryFindResponse.getVaryKeys();
        }
        privateStorage.store(url, new HttpCacheEntry(HttpCacheEntryKt.cacheExpires$default(httpResponse, httpCache.getIsSharedClient(), null, 2, null), mapVaryKeys, httpCacheEntryFindResponse.getResponse(), httpCacheEntryFindResponse.getBody()));
        return httpCacheEntryFindResponse.produceResponse$ktor_client_core();
    }

    private static final HttpCacheEntry findResponse(HttpCache httpCache, HttpCacheStorage httpCacheStorage, Map<String, String> map, Url url, HttpRequest httpRequest) {
        Object next;
        if (!map.isEmpty()) {
            return httpCacheStorage.find(url, map);
        }
        l<String, String> lVarMergedHeadersLookup = HttpCacheKt.mergedHeadersLookup(httpRequest.getContent(), new HttpCacheLegacyKt$findResponse$requestHeaders$1(httpRequest.getHeaders()), new HttpCacheLegacyKt$findResponse$requestHeaders$2(httpRequest.getHeaders()));
        Iterator it = x.W0(httpCacheStorage.findByUrl(url), new Comparator() { // from class: io.ktor.client.plugins.cache.HttpCacheLegacyKt$findResponse$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(((HttpCacheEntry) t10).getResponse().getResponseTime(), ((HttpCacheEntry) t2).getResponse().getResponseTime());
            }
        }).iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Map<String, String> varyKeys = ((HttpCacheEntry) next).getVaryKeys();
            if (!varyKeys.isEmpty()) {
                for (Map.Entry<String, String> entry : varyKeys.entrySet()) {
                    String key = entry.getKey();
                    if (!p.a(lVarMergedHeadersLookup.invoke(key), entry.getValue())) {
                        break;
                    }
                }
                break loop0;
            }
            break;
        }
        return (HttpCacheEntry) next;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object interceptReceiveLegacy(io.ktor.util.pipeline.PipelineContext<io.ktor.client.statement.HttpResponse, x6.t0> r7, io.ktor.client.statement.HttpResponse r8, io.ktor.client.plugins.cache.HttpCache r9, io.ktor.client.HttpClient r10, d7.d<? super x6.t0> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.client.plugins.cache.HttpCacheLegacyKt.C17351
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1 r0 = (io.ktor.client.plugins.cache.HttpCacheLegacyKt.C17351) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1 r0 = new io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            x6.t0 r5 = x6.t0.f22605a
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L44
            if (r1 == r4) goto L3c
            if (r1 == r3) goto L38
            if (r1 != r2) goto L30
            k2.c.G(r11)
            goto La2
        L30:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L38:
            k2.c.G(r11)
            goto L6a
        L3c:
            java.lang.Object r7 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r7 = (io.ktor.util.pipeline.PipelineContext) r7
            k2.c.G(r11)
            goto L5c
        L44:
            k2.c.G(r11)
            io.ktor.http.HttpStatusCode r11 = r8.getStatus()
            boolean r11 = io.ktor.http.HttpStatusCodeKt.isSuccess(r11)
            if (r11 == 0) goto L6b
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r11 = cacheResponse(r9, r8, r0)
            if (r11 != r6) goto L5c
            goto La1
        L5c:
            io.ktor.client.statement.HttpResponse r11 = (io.ktor.client.statement.HttpResponse) r11
            r8 = 0
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r7 = r7.proceedWith(r11, r0)
            if (r7 != r6) goto L6a
            goto La1
        L6a:
            return r5
        L6b:
            io.ktor.http.HttpStatusCode r11 = r8.getStatus()
            io.ktor.http.HttpStatusCode$Companion r1 = io.ktor.http.HttpStatusCode.INSTANCE
            io.ktor.http.HttpStatusCode r1 = r1.getNotModified()
            boolean r11 = kotlin.jvm.internal.p.a(r11, r1)
            if (r11 == 0) goto Lb5
            io.ktor.client.statement.HttpResponseKt.complete(r8)
            io.ktor.client.call.HttpClientCall r11 = r8.getCall()
            io.ktor.client.request.HttpRequest r11 = r11.getRequest()
            io.ktor.client.statement.HttpResponse r9 = findAndRefresh(r9, r11, r8)
            if (r9 == 0) goto La3
            io.ktor.events.Events r8 = r10.getMonitor()
            io.ktor.client.plugins.cache.HttpCache$Companion r10 = io.ktor.client.plugins.cache.HttpCache.INSTANCE
            io.ktor.events.EventDefinition r10 = r10.getHttpResponseFromCache()
            r8.raise(r10, r9)
            r0.label = r2
            java.lang.Object r7 = r7.proceedWith(r9, r0)
            if (r7 != r6) goto La2
        La1:
            return r6
        La2:
            return r5
        La3:
            io.ktor.client.plugins.cache.InvalidCacheStateException r7 = new io.ktor.client.plugins.cache.InvalidCacheStateException
            io.ktor.client.call.HttpClientCall r8 = r8.getCall()
            io.ktor.client.request.HttpRequest r8 = r8.getRequest()
            io.ktor.http.Url r8 = r8.getUrl()
            r7.<init>(r8)
            throw r7
        Lb5:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheLegacyKt.interceptReceiveLegacy(io.ktor.util.pipeline.PipelineContext, io.ktor.client.statement.HttpResponse, io.ktor.client.plugins.cache.HttpCache, io.ktor.client.HttpClient, d7.d):java.lang.Object");
    }

    public static final Object interceptSendLegacy(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpCache httpCache, OutgoingContent outgoingContent, HttpClient httpClient, d<? super t0> dVar) {
        Object objProceedWithMissingCache$ktor_client_core;
        HttpCacheEntry httpCacheEntryFindResponse = findResponse(httpCache, pipelineContext.getContext(), outgoingContent);
        a aVar = a.f15033i;
        t0 t0Var = t0.f22605a;
        if (httpCacheEntryFindResponse == null) {
            return (HttpHeaderValueParserKt.parseHeaderValue(pipelineContext.getContext().getHeaders().get(HttpHeaders.INSTANCE.getCacheControl())).contains(CacheControl.INSTANCE.getONLY_IF_CACHED$ktor_client_core()) && (objProceedWithMissingCache$ktor_client_core = HttpCache.INSTANCE.proceedWithMissingCache$ktor_client_core(pipelineContext, httpClient, dVar)) == aVar) ? objProceedWithMissingCache$ktor_client_core : t0Var;
        }
        HttpClientCall call = httpCacheEntryFindResponse.produceResponse$ktor_client_core().getCall();
        ValidateStatus validateStatusShouldValidate = HttpCacheEntryKt.shouldValidate(httpCacheEntryFindResponse.getExpires(), httpCacheEntryFindResponse.getResponse().getHeaders(), pipelineContext.getContext());
        if (validateStatusShouldValidate == ValidateStatus.ShouldNotValidate) {
            Object objProceedWithCache$ktor_client_core = HttpCache.INSTANCE.proceedWithCache$ktor_client_core(pipelineContext, httpClient, call, dVar);
            return objProceedWithCache$ktor_client_core == aVar ? objProceedWithCache$ktor_client_core : t0Var;
        }
        if (validateStatusShouldValidate == ValidateStatus.ShouldWarn) {
            Object objProceedWithWarning = proceedWithWarning(pipelineContext, call, httpClient, dVar);
            return objProceedWithWarning == aVar ? objProceedWithWarning : t0Var;
        }
        Headers responseHeaders = httpCacheEntryFindResponse.getResponseHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        String str = responseHeaders.get(httpHeaders.getETag());
        if (str != null) {
            UtilsKt.header(pipelineContext.getContext(), httpHeaders.getIfNoneMatch(), str);
        }
        String str2 = httpCacheEntryFindResponse.getResponseHeaders().get(httpHeaders.getLastModified());
        if (str2 != null) {
            UtilsKt.header(pipelineContext.getContext(), httpHeaders.getIfModifiedSince(), str2);
        }
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object proceedWithWarning(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpClientCall httpClientCall, HttpClient httpClient, d<? super t0> dVar) {
        HttpRequestData httpRequestDataBuild = pipelineContext.getContext().build();
        HttpStatusCode status = httpClientCall.getResponse().getStatus();
        GMTDate requestTime = httpClientCall.getResponse().getRequestTime();
        Headers.Companion companion = Headers.INSTANCE;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        headersBuilder.appendAll(httpClientCall.getResponse().getHeaders());
        headersBuilder.append(HttpHeaders.INSTANCE.getWarning(), "110");
        HttpClientCall httpClientCall2 = new HttpClientCall(httpClient, httpRequestDataBuild, new HttpResponseData(status, requestTime, headersBuilder.build(), httpClientCall.getResponse().getVersion(), httpClientCall.getResponse().getContent(), httpClientCall.getResponse().getCoroutineContext()));
        pipelineContext.finish();
        httpClient.getMonitor().raise(HttpCache.INSTANCE.getHttpResponseFromCache(), httpClientCall2.getResponse());
        Object objProceedWith = pipelineContext.proceedWith(httpClientCall2, dVar);
        return objProceedWith == a.f15033i ? objProceedWith : t0.f22605a;
    }

    private static final HttpCacheEntry findResponse(HttpCache httpCache, HttpRequestBuilder httpRequestBuilder, OutgoingContent outgoingContent) {
        Url Url = URLUtilsKt.Url(httpRequestBuilder.getUrl());
        l<String, String> lVarMergedHeadersLookup = HttpCacheKt.mergedHeadersLookup(outgoingContent, new HttpCacheLegacyKt$findResponse$lookup$1(httpRequestBuilder.getHeaders()), new HttpCacheLegacyKt$findResponse$lookup$2(httpRequestBuilder.getHeaders()));
        for (HttpCacheEntry httpCacheEntry : q0.b0(httpCache.getPrivateStorage().findByUrl(Url), httpCache.getPublicStorage().findByUrl(Url))) {
            Map<String, String> varyKeys = httpCacheEntry.getVaryKeys();
            if (!varyKeys.isEmpty() && !varyKeys.isEmpty()) {
                for (Map.Entry<String, String> entry : varyKeys.entrySet()) {
                    String key = entry.getKey();
                    if (!p.a(lVarMergedHeadersLookup.invoke(key), entry.getValue())) {
                        break;
                    }
                }
            }
            return httpCacheEntry;
        }
        return null;
    }
}
