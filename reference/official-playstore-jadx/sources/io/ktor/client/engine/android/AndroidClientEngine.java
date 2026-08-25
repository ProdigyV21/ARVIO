package io.ktor.client.engine.android;

import d7.d;
import d7.j;
import f7.c;
import f7.e;
import io.ktor.client.engine.HttpClientEngineBase;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.http.HeadersImpl;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.GMTDate;
import io.ktor.utils.io.ByteReadChannel;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/ktor/client/engine/android/AndroidClientEngine;", "Lio/ktor/client/engine/HttpClientEngineBase;", "Lio/ktor/client/engine/android/AndroidEngineConfig;", "config", "<init>", "(Lio/ktor/client/engine/android/AndroidEngineConfig;)V", "", "urlString", "Ljava/net/HttpURLConnection;", "getProxyAwareConnection", "(Ljava/lang/String;)Ljava/net/HttpURLConnection;", "Lio/ktor/client/request/HttpRequestData;", "data", "Lio/ktor/client/request/HttpResponseData;", "execute", "(Lio/ktor/client/request/HttpRequestData;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/engine/android/AndroidEngineConfig;", "getConfig", "()Lio/ktor/client/engine/android/AndroidEngineConfig;", "", "Lio/ktor/client/engine/HttpClientEngineCapability;", "supportedCapabilities", "Ljava/util/Set;", "getSupportedCapabilities", "()Ljava/util/Set;", "ktor-client-android"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidClientEngine extends HttpClientEngineBase {
    private final AndroidEngineConfig config;
    private final Set<HttpClientEngineCapability<?>> supportedCapabilities;

    /* JADX INFO: renamed from: io.ktor.client.engine.android.AndroidClientEngine$execute$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.engine.android.AndroidClientEngine", f = "AndroidClientEngine.kt", l = {35, 79, 82}, m = "execute")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidClientEngine.this.execute(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.engine.android.AndroidClientEngine$execute$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/client/request/HttpResponseData;", "current", "Ljava/net/HttpURLConnection;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<HttpURLConnection, HttpResponseData> {
        final /* synthetic */ j $callContext;
        final /* synthetic */ HttpRequestData $data;
        final /* synthetic */ GMTDate $requestTime;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(j jVar, HttpRequestData httpRequestData, GMTDate gMTDate) {
            super(1);
            this.$callContext = jVar;
            this.$data = httpRequestData;
            this.$requestTime = gMTDate;
        }

        @Override // r7.l
        public final HttpResponseData invoke(HttpURLConnection httpURLConnection) throws IOException {
            int responseCode = httpURLConnection.getResponseCode();
            String responseMessage = httpURLConnection.getResponseMessage();
            HttpStatusCode httpStatusCode = responseMessage != null ? new HttpStatusCode(responseCode, responseMessage) : HttpStatusCode.INSTANCE.fromValue(responseCode);
            ByteReadChannel byteReadChannelContent = AndroidURLConnectionUtilsKt.content(httpURLConnection, this.$callContext, this.$data);
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(headerFields.size()));
            Iterator<T> it = headerFields.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                linkedHashMap.put(str != null ? str.toLowerCase(Locale.getDefault()) : "", entry.getValue());
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                if (!o.h0((CharSequence) entry2.getKey())) {
                    linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                }
            }
            return new HttpResponseData(httpStatusCode, this.$requestTime, new HeadersImpl(linkedHashMap2), HttpProtocolVersion.INSTANCE.getHTTP_1_1(), byteReadChannelContent, this.$callContext);
        }
    }

    public AndroidClientEngine(AndroidEngineConfig androidEngineConfig) {
        super("ktor-android");
        this.config = androidEngineConfig;
        this.supportedCapabilities = Collections.singleton(HttpTimeout.INSTANCE);
    }

    private final HttpURLConnection getProxyAwareConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        Proxy proxy = getConfig().getProxy();
        URLConnection uRLConnectionOpenConnection = proxy != null ? url.openConnection(proxy) : null;
        if (uRLConnectionOpenConnection == null) {
            uRLConnectionOpenConnection = url.openConnection();
        }
        return (HttpURLConnection) uRLConnectionOpenConnection;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0191 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // io.ktor.client.engine.HttpClientEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object execute(io.ktor.client.request.HttpRequestData r18, d7.d<? super io.ktor.client.request.HttpResponseData> r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.android.AndroidClientEngine.execute(io.ktor.client.request.HttpRequestData, d7.d):java.lang.Object");
    }

    @Override // io.ktor.client.engine.HttpClientEngineBase, io.ktor.client.engine.HttpClientEngine
    public Set<HttpClientEngineCapability<?>> getSupportedCapabilities() {
        return this.supportedCapabilities;
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public AndroidEngineConfig getConfig() {
        return this.config;
    }
}
