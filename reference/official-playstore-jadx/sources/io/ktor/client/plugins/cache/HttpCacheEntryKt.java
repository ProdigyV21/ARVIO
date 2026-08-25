package io.ktor.client.plugins.cache;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.DateUtilsKt;
import io.ktor.http.HeaderValue;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.DateKt;
import io.ktor.util.date.GMTDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import kotlin.text.u;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u0002H\u0000¢\u0006\u0004\b\t\u0010\n\u001a+\u0010\u000e\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a'\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"", "isShared", "Lio/ktor/client/statement/HttpResponse;", "response", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "HttpCacheEntry", "(ZLio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "", "", "varyKeys", "(Lio/ktor/client/statement/HttpResponse;)Ljava/util/Map;", "Lkotlin/Function0;", "Lio/ktor/util/date/GMTDate;", "fallback", "cacheExpires", "(Lio/ktor/client/statement/HttpResponse;ZLr7/a;)Lio/ktor/util/date/GMTDate;", "Lio/ktor/http/Headers;", "responseHeaders", "Lio/ktor/client/request/HttpRequestBuilder;", "request", "Lio/ktor/client/plugins/cache/ValidateStatus;", "shouldValidate", "(Lio/ktor/util/date/GMTDate;Lio/ktor/http/Headers;Lio/ktor/client/request/HttpRequestBuilder;)Lio/ktor/client/plugins/cache/ValidateStatus;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpCacheEntryKt {

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.cache.HttpCacheEntryKt", f = "HttpCacheEntry.kt", l = {18}, m = "HttpCacheEntry")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCacheEntryKt.HttpCacheEntry(false, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.cache.HttpCacheEntryKt$cacheExpires$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/util/date/GMTDate;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C17341 extends r implements a<GMTDate> {
        public static final C17341 INSTANCE = new C17341();

        public C17341() {
            super(0);
        }

        @Override // r7.a
        public final GMTDate invoke() {
            return DateJvmKt.GMTDate$default(null, 1, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object HttpCacheEntry(boolean r8, io.ktor.client.statement.HttpResponse r9, d7.d<? super io.ktor.client.plugins.cache.HttpCacheEntry> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.client.plugins.cache.HttpCacheEntryKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1 r0 = (io.ktor.client.plugins.cache.HttpCacheEntryKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1 r0 = new io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1
            r0.<init>(r10)
            goto L12
        L1a:
            java.lang.Object r10 = r4.result
            int r0 = r4.label
            r7 = 1
            if (r0 == 0) goto L35
            if (r0 != r7) goto L2d
            boolean r8 = r4.Z$0
            java.lang.Object r9 = r4.L$0
            io.ktor.client.statement.HttpResponse r9 = (io.ktor.client.statement.HttpResponse) r9
            k2.c.G(r10)
            goto L4f
        L2d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L35:
            k2.c.G(r10)
            io.ktor.utils.io.ByteReadChannel r1 = r9.getContent()
            r4.L$0 = r9
            r4.Z$0 = r8
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r10 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            e7.a r0 = e7.a.f15033i
            if (r10 != r0) goto L4f
            return r0
        L4f:
            io.ktor.utils.io.core.ByteReadPacket r10 = (io.ktor.utils.io.core.ByteReadPacket) r10
            r0 = 0
            r1 = 0
            byte[] r10 = io.ktor.utils.io.core.StringsKt.readBytes$default(r10, r0, r7, r1)
            io.ktor.client.statement.HttpResponseKt.complete(r9)
            io.ktor.client.plugins.cache.HttpCacheEntry r0 = new io.ktor.client.plugins.cache.HttpCacheEntry
            r2 = 2
            io.ktor.util.date.GMTDate r8 = cacheExpires$default(r9, r8, r1, r2, r1)
            java.util.Map r1 = varyKeys(r9)
            r0.<init>(r8, r1, r9, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheEntryKt.HttpCacheEntry(boolean, io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }

    public static final GMTDate cacheExpires(HttpResponse httpResponse, boolean z, a<GMTDate> aVar) {
        String str;
        Integer numValueOf;
        Object next;
        String value;
        String str2;
        List<HeaderValue> listCacheControl = HttpMessagePropertiesKt.cacheControl(httpResponse);
        if (!z || (listCacheControl != null && listCacheControl.isEmpty())) {
            str = io.ktor.client.utils.CacheControl.MAX_AGE;
        } else {
            Iterator<T> it = listCacheControl.iterator();
            while (it.hasNext()) {
                String value2 = ((HeaderValue) it.next()).getValue();
                str = io.ktor.client.utils.CacheControl.S_MAX_AGE;
                if (u.P(value2, io.ktor.client.utils.CacheControl.S_MAX_AGE, false)) {
                    break;
                }
            }
            str = io.ktor.client.utils.CacheControl.MAX_AGE;
        }
        Iterator<T> it2 = listCacheControl.iterator();
        while (true) {
            numValueOf = null;
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            if (u.P(((HeaderValue) next).getValue(), str, false)) {
                break;
            }
        }
        HeaderValue headerValue = (HeaderValue) next;
        if (headerValue != null && (value = headerValue.getValue()) != null && (str2 = (String) o.y0(value, new String[]{"="}, 0, 6).get(1)) != null) {
            numValueOf = Integer.valueOf(Integer.parseInt(str2));
        }
        if (numValueOf != null) {
            return DateKt.plus(httpResponse.getRequestTime(), ((long) numValueOf.intValue()) * 1000);
        }
        String str3 = httpResponse.getHeaders().get(HttpHeaders.INSTANCE.getExpires());
        if (str3 == null) {
            return (GMTDate) aVar.invoke();
        }
        if (str3.equals("0") || o.h0(str3)) {
            return (GMTDate) aVar.invoke();
        }
        try {
            return DateUtilsKt.fromHttpToGmtDate(str3);
        } catch (Throwable unused) {
            return (GMTDate) aVar.invoke();
        }
    }

    public static /* synthetic */ GMTDate cacheExpires$default(HttpResponse httpResponse, boolean z, a aVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            aVar = C17341.INSTANCE;
        }
        return cacheExpires(httpResponse, z, aVar);
    }

    public static final ValidateStatus shouldValidate(GMTDate gMTDate, Headers headers, HttpRequestBuilder httpRequestBuilder) {
        int iIntValue;
        Object next;
        Integer numValueOf;
        String value;
        Integer numR;
        String value2;
        String str;
        HeadersBuilder headers2 = httpRequestBuilder.getHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        List<String> all = headers.getAll(httpHeaders.getCacheControl());
        Object obj = null;
        List<HeaderValue> headerValue = HttpHeaderValueParserKt.parseHeaderValue(all != null ? x.u0(all, ",", null, null, null, 62) : null);
        List<String> all2 = headers2.getAll(httpHeaders.getCacheControl());
        List<HeaderValue> headerValue2 = HttpHeaderValueParserKt.parseHeaderValue(all2 != null ? x.u0(all2, ",", null, null, null, 62) : null);
        if (headerValue2.contains(CacheControl.INSTANCE.getNO_CACHE$ktor_client_core())) {
            HttpCacheKt.getLOGGER().h("\"no-cache\" is set for " + httpRequestBuilder.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        Iterator<T> it = headerValue2.iterator();
        while (true) {
            iIntValue = 0;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (u.P(((HeaderValue) next).getValue(), "max-age=", false)) {
                break;
            }
        }
        HeaderValue headerValue3 = (HeaderValue) next;
        if (headerValue3 == null || (value2 = headerValue3.getValue()) == null || (str = (String) o.y0(value2, new String[]{"="}, 0, 6).get(1)) == null) {
            numValueOf = null;
        } else {
            Integer numR2 = u.R(str);
            numValueOf = Integer.valueOf(numR2 != null ? numR2.intValue() : 0);
        }
        if (numValueOf != null && numValueOf.intValue() == 0) {
            HttpCacheKt.getLOGGER().h("\"max-age\" is not set for " + httpRequestBuilder.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        CacheControl cacheControl = CacheControl.INSTANCE;
        if (headerValue.contains(cacheControl.getNO_CACHE$ktor_client_core())) {
            HttpCacheKt.getLOGGER().h("\"no-cache\" is set for " + httpRequestBuilder.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        long timestamp = gMTDate.getTimestamp() - DateJvmKt.getTimeMillis();
        if (timestamp > 0) {
            HttpCacheKt.getLOGGER().h("Cached response is valid for " + httpRequestBuilder.getUrl() + ", should not validate");
            return ValidateStatus.ShouldNotValidate;
        }
        if (headerValue.contains(cacheControl.getMUST_REVALIDATE$ktor_client_core())) {
            HttpCacheKt.getLOGGER().h("\"must-revalidate\" is set for " + httpRequestBuilder.getUrl() + ", should validate cached response");
            return ValidateStatus.ShouldValidate;
        }
        Iterator<T> it2 = headerValue2.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next2 = it2.next();
            if (u.P(((HeaderValue) next2).getValue(), "max-stale=", false)) {
                obj = next2;
                break;
            }
        }
        HeaderValue headerValue4 = (HeaderValue) obj;
        if (headerValue4 != null && (value = headerValue4.getValue()) != null && (numR = u.R(value.substring(10))) != null) {
            iIntValue = numR.intValue();
        }
        if ((((long) iIntValue) * 1000) + timestamp > 0) {
            HttpCacheKt.getLOGGER().h("Cached response is stale for " + httpRequestBuilder.getUrl() + " but less than max-stale, should warn");
            return ValidateStatus.ShouldWarn;
        }
        HttpCacheKt.getLOGGER().h("Cached response is stale for " + httpRequestBuilder.getUrl() + ", should validate cached response");
        return ValidateStatus.ShouldValidate;
    }

    public static final Map<String, String> varyKeys(HttpResponse httpResponse) {
        List<String> listVary = HttpMessagePropertiesKt.vary(httpResponse);
        if (listVary == null) {
            return a0.f19683i;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Headers headers = httpResponse.getCall().getRequest().getHeaders();
        for (String str : listVary) {
            String str2 = headers.get(str);
            if (str2 == null) {
                str2 = "";
            }
            linkedHashMap.put(str, str2);
        }
        return linkedHashMap;
    }
}
