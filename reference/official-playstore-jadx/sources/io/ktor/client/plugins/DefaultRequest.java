package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.URLParserKt;
import io.ktor.http.URLProtocol;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.KtorDsl;
import io.ktor.util.StringValuesKt;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import t7.a;
import x6.t0;
import z6.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0002\u000b\nB\"\b\u0002\u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005¢\u0006\u0004\b\u0007\u0010\bR%\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\t¨\u0006\f"}, d2 = {"Lio/ktor/client/plugins/DefaultRequest;", "", "Lkotlin/Function1;", "Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "Lx6/t0;", "Lx6/n;", "block", "<init>", "(Lr7/l;)V", "Lr7/l;", "Plugin", "DefaultRequestBuilder", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultRequest {

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<DefaultRequest> key = new AttributeKey<>("DefaultRequest");
    private final l<DefaultRequestBuilder, t0> block;

    @KtorDsl
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\t\u001a\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nJX\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\u0011J\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u000b¢\u0006\u0004\b\t\u0010\u0013J&\u0010\u0015\u001a\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0015\u0010\nR\u001a\u0010\u0017\u001a\u00020\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\t\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R$\u0010\r\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b#\u0010$\"\u0004\b%\u0010\u0013R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "Lio/ktor/http/HttpMessageBuilder;", "<init>", "()V", "Lkotlin/Function1;", "Lio/ktor/http/URLBuilder;", "Lx6/t0;", "Lx6/n;", "block", "url", "(Lr7/l;)V", "", "scheme", "host", "", "port", "path", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lr7/l;)V", "urlString", "(Ljava/lang/String;)V", "Lio/ktor/util/Attributes;", "setAttributes", "Lio/ktor/http/HeadersBuilder;", "headers", "Lio/ktor/http/HeadersBuilder;", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", "Lio/ktor/http/URLBuilder;", "getUrl", "()Lio/ktor/http/URLBuilder;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "value", "getHost", "()Ljava/lang/String;", "setHost", "getPort", "()I", "setPort", "(I)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultRequestBuilder implements HttpMessageBuilder {
        private final HeadersBuilder headers = new HeadersBuilder(0, 1, null);
        private final URLBuilder url = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
        private final Attributes attributes = AttributesJvmKt.Attributes(true);

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void url$default(DefaultRequestBuilder defaultRequestBuilder, String str, String str2, Integer num, String str3, l lVar, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = null;
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            if ((i10 & 4) != 0) {
                num = null;
            }
            if ((i10 & 8) != 0) {
                str3 = null;
            }
            if ((i10 & 16) != 0) {
                lVar = DefaultRequest$DefaultRequestBuilder$url$1.INSTANCE;
            }
            defaultRequestBuilder.url(str, str2, num, str3, lVar);
        }

        public final Attributes getAttributes() {
            return this.attributes;
        }

        @Override // io.ktor.http.HttpMessageBuilder
        public HeadersBuilder getHeaders() {
            return this.headers;
        }

        public final String getHost() {
            return this.url.getHost();
        }

        public final int getPort() {
            return this.url.getPort();
        }

        public final URLBuilder getUrl() {
            return this.url;
        }

        public final void setAttributes(l<? super Attributes, t0> block) {
            block.invoke(this.attributes);
        }

        public final void setHost(String str) {
            this.url.setHost(str);
        }

        public final void setPort(int i10) {
            this.url.setPort(i10);
        }

        public final void url(l<? super URLBuilder, t0> block) {
            block.invoke(this.url);
        }

        public final void url(String scheme, String host, Integer port, String path, l<? super URLBuilder, t0> block) {
            URLBuilderKt.set(this.url, scheme, host, port, path, block);
        }

        public final void url(String urlString) {
            URLParserKt.takeFrom(this.url, urlString);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.DefaultRequest$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ1\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J(\u0010\u0016\u001a\u00020\u00032\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0013¢\u0006\u0002\b\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lio/ktor/client/plugins/DefaultRequest$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "Lio/ktor/client/plugins/DefaultRequest;", "<init>", "()V", "Lio/ktor/http/Url;", "baseUrl", "Lio/ktor/http/URLBuilder;", "requestUrl", "Lx6/t0;", "mergeUrls", "(Lio/ktor/http/Url;Lio/ktor/http/URLBuilder;)V", "", "", "parent", "child", "concatenatePath", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "Lkotlin/Function1;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/DefaultRequest;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/DefaultRequest;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<DefaultRequestBuilder, DefaultRequest> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final List<String> concatenatePath(List<String> parent, List<String> child) {
            if (child.isEmpty()) {
                return parent;
            }
            if (parent.isEmpty() || ((CharSequence) x.m0(child)).length() == 0) {
                return child;
            }
            c cVar = new c((child.size() + parent.size()) - 1);
            int size = parent.size() - 1;
            for (int i10 = 0; i10 < size; i10++) {
                cVar.add(parent.get(i10));
            }
            cVar.addAll(child);
            return a.e(cVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void mergeUrls(Url baseUrl, URLBuilder requestUrl) {
            if (p.a(requestUrl.getProtocol(), URLProtocol.INSTANCE.getHTTP())) {
                requestUrl.setProtocol(baseUrl.getProtocol());
            }
            if (requestUrl.getHost().length() > 0) {
                return;
            }
            URLBuilder URLBuilder = URLUtilsKt.URLBuilder(baseUrl);
            URLBuilder.setProtocol(requestUrl.getProtocol());
            if (requestUrl.getPort() != 0) {
                URLBuilder.setPort(requestUrl.getPort());
            }
            URLBuilder.setEncodedPathSegments(DefaultRequest.INSTANCE.concatenatePath(URLBuilder.getEncodedPathSegments(), requestUrl.getEncodedPathSegments()));
            if (requestUrl.getEncodedFragment().length() > 0) {
                URLBuilder.setEncodedFragment(requestUrl.getEncodedFragment());
            }
            ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
            StringValuesKt.appendAll(parametersBuilderParametersBuilder$default, URLBuilder.getEncodedParameters());
            URLBuilder.setEncodedParameters(requestUrl.getEncodedParameters());
            Iterator<T> it = parametersBuilderParametersBuilder$default.entries().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                List list = (List) entry.getValue();
                if (!URLBuilder.getEncodedParameters().contains(str)) {
                    URLBuilder.getEncodedParameters().appendAll(str, list);
                }
            }
            URLUtilsKt.takeFrom(requestUrl, URLBuilder);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<DefaultRequest> getKey() {
            return DefaultRequest.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(DefaultRequest plugin, HttpClient scope) {
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getBefore(), new DefaultRequest$Plugin$install$1(plugin, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public DefaultRequest prepare(l<? super DefaultRequestBuilder, t0> block) {
            return new DefaultRequest(block, null);
        }
    }

    public /* synthetic */ DefaultRequest(l lVar, h hVar) {
        this(lVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DefaultRequest(l<? super DefaultRequestBuilder, t0> lVar) {
        this.block = lVar;
    }
}
