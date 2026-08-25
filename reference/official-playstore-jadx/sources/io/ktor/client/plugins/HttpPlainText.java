package io.ktor.client.plugins;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.util.concurrent.r0;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.TextContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.jvm.internal.h;
import kotlin.text.a;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 %2\u00020\u0001:\u0002&%BO\b\u0000\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0002\u0012\u0016\u0010\b\u001a\u0012\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\u0010\t\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004\u0012\n\u0010\n\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010 \u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\rH\u0000¢\u0006\u0004\b\u001e\u0010\u001fR\u0018\u0010\n\u001a\u00060\u0003j\u0002`\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010!R\u0018\u0010\"\u001a\u00060\u0003j\u0002`\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010#\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText;", "", "", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "charsets", "", "", "charsetQuality", "sendCharset", "responseCharsetFallback", "<init>", "(Ljava/util/Set;Ljava/util/Map;Ljava/nio/charset/Charset;Ljava/nio/charset/Charset;)V", "Lio/ktor/client/request/HttpRequestBuilder;", "request", "", "content", "Lio/ktor/http/ContentType;", "requestContentType", "wrapContent", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/String;Lio/ktor/http/ContentType;)Ljava/lang/Object;", "Lio/ktor/client/call/HttpClientCall;", "call", "Lio/ktor/utils/io/core/Input;", TtmlNode.TAG_BODY, "read$ktor_client_core", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/utils/io/core/Input;)Ljava/lang/String;", "read", "context", "Lx6/t0;", "addCharsetHeaders$ktor_client_core", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "addCharsetHeaders", "Ljava/nio/charset/Charset;", "requestCharset", "acceptCharsetHeader", "Ljava/lang/String;", "Plugin", "Config", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpPlainText {

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpPlainText> key = new AttributeKey<>("HttpPlainText");
    private final String acceptCharsetHeader;
    private final Charset requestCharset;
    private final Charset responseCharsetFallback;

    @KtorDsl
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\t2\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bR$\u0010\r\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R*\u0010\u0012\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00070\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R*\u0010\u0016\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u00060\u0004j\u0002`\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001b¨\u0006\u001f"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText$Config;", "", "<init>", "()V", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "", "quality", "Lx6/t0;", "register", "(Ljava/nio/charset/Charset;Ljava/lang/Float;)V", "", "charsets", "Ljava/util/Set;", "getCharsets$ktor_client_core", "()Ljava/util/Set;", "", "charsetQuality", "Ljava/util/Map;", "getCharsetQuality$ktor_client_core", "()Ljava/util/Map;", "sendCharset", "Ljava/nio/charset/Charset;", "getSendCharset", "()Ljava/nio/charset/Charset;", "setSendCharset", "(Ljava/nio/charset/Charset;)V", "responseCharsetFallback", "getResponseCharsetFallback", "setResponseCharsetFallback", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config {
        private Charset sendCharset;
        private final Set<Charset> charsets = new LinkedHashSet();
        private final Map<Charset, Float> charsetQuality = new LinkedHashMap();
        private Charset responseCharsetFallback = a.f19924a;

        public static /* synthetic */ void register$default(Config config, Charset charset, Float f10, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                f10 = null;
            }
            config.register(charset, f10);
        }

        public final Map<Charset, Float> getCharsetQuality$ktor_client_core() {
            return this.charsetQuality;
        }

        public final Set<Charset> getCharsets$ktor_client_core() {
            return this.charsets;
        }

        public final Charset getResponseCharsetFallback() {
            return this.responseCharsetFallback;
        }

        public final Charset getSendCharset() {
            return this.sendCharset;
        }

        public final void register(Charset charset, Float quality) {
            if (quality != null) {
                double dFloatValue = quality.floatValue();
                if (0.0d > dFloatValue || dFloatValue > 1.0d) {
                    throw new IllegalStateException("Check failed.");
                }
            }
            this.charsets.add(charset);
            if (quality == null) {
                this.charsetQuality.remove(charset);
            } else {
                this.charsetQuality.put(charset, quality);
            }
        }

        public final void setResponseCharsetFallback(Charset charset) {
            this.responseCharsetFallback = charset;
        }

        public final void setSendCharset(Charset charset) {
            this.sendCharset = charset;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpPlainText$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpPlainText$Config;", "Lio/ktor/client/plugins/HttpPlainText;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/HttpPlainText;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/HttpPlainText;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<Config, HttpPlainText> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpPlainText> getKey() {
            return HttpPlainText.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpPlainText plugin, HttpClient scope) {
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getRender(), new HttpPlainText$Plugin$install$1(plugin, null));
            scope.getResponsePipeline().intercept(HttpResponsePipeline.INSTANCE.getTransform(), new HttpPlainText$Plugin$install$2(plugin, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpPlainText prepare(l<? super Config, t0> block) {
            Config config = new Config();
            block.invoke(config);
            return new HttpPlainText(config.getCharsets$ktor_client_core(), config.getCharsetQuality$ktor_client_core(), config.getSendCharset(), config.getResponseCharsetFallback());
        }
    }

    public HttpPlainText(Set<? extends Charset> set, Map<Charset, Float> map, Charset charset, Charset charset2) {
        this.responseCharsetFallback = charset2;
        List<x> listW0 = kotlin.collections.x.W0(h0.z0(map), new Comparator() { // from class: io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e((Float) ((x) t10).f22609l, (Float) ((x) t2).f22609l);
            }
        });
        ArrayList arrayList = new ArrayList();
        for (Object obj : set) {
            if (!map.containsKey((Charset) obj)) {
                arrayList.add(obj);
            }
        }
        List<Charset> listW02 = kotlin.collections.x.W0(arrayList, new Comparator() { // from class: io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(CharsetJVMKt.getName((Charset) t2), CharsetJVMKt.getName((Charset) t10));
            }
        });
        StringBuilder sb2 = new StringBuilder();
        for (Charset charset3 : listW02) {
            if (sb2.length() > 0) {
                sb2.append(",");
            }
            sb2.append(CharsetJVMKt.getName(charset3));
        }
        for (x xVar : listW0) {
            Charset charset4 = (Charset) xVar.f22608i;
            float fFloatValue = ((Number) xVar.f22609l).floatValue();
            if (sb2.length() > 0) {
                sb2.append(",");
            }
            double d4 = fFloatValue;
            if (0.0d > d4 || d4 > 1.0d) {
                throw new IllegalStateException("Check failed.");
            }
            sb2.append(CharsetJVMKt.getName(charset4) + ";q=" + (((double) t7.a.M(100 * fFloatValue)) / 100.0d));
        }
        if (sb2.length() == 0) {
            sb2.append(CharsetJVMKt.getName(this.responseCharsetFallback));
        }
        this.acceptCharsetHeader = sb2.toString();
        if (charset == null && (charset = (Charset) kotlin.collections.x.o0(listW02)) == null) {
            x xVar2 = (x) kotlin.collections.x.o0(listW0);
            charset = xVar2 != null ? (Charset) xVar2.f22608i : null;
            if (charset == null) {
                charset = a.f19924a;
            }
        }
        this.requestCharset = charset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object wrapContent(HttpRequestBuilder request, String content, ContentType requestContentType) {
        Charset charset;
        ContentType plain = requestContentType == null ? ContentType.Text.INSTANCE.getPlain() : requestContentType;
        if (requestContentType == null || (charset = ContentTypesKt.charset(requestContentType)) == null) {
            charset = this.requestCharset;
        }
        HttpPlainTextKt.LOGGER.h("Sending request body to " + request.getUrl() + " as text/plain with charset " + charset);
        return new TextContent(content, ContentTypesKt.withCharset(plain, charset), null, 4, null);
    }

    public final void addCharsetHeaders$ktor_client_core(HttpRequestBuilder context) {
        HeadersBuilder headers = context.getHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        if (headers.get(httpHeaders.getAcceptCharset()) != null) {
            return;
        }
        HttpPlainTextKt.LOGGER.h("Adding Accept-Charset=" + this.acceptCharsetHeader + " to " + context.getUrl());
        context.getHeaders().set(httpHeaders.getAcceptCharset(), this.acceptCharsetHeader);
    }

    public final String read$ktor_client_core(HttpClientCall call, Input body) {
        Charset charset = HttpMessagePropertiesKt.charset(call.getResponse());
        if (charset == null) {
            charset = this.responseCharsetFallback;
        }
        HttpPlainTextKt.LOGGER.h("Reading response body for " + call.getRequest().getUrl() + " as String with charset " + charset);
        return StringsKt.readText$default(body, charset, 0, 2, (Object) null);
    }
}
