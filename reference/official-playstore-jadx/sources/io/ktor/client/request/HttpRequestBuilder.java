package io.ktor.client.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.engine.HttpClientEngineCapabilityKt;
import io.ktor.client.utils.EmptyContent;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.AttributesKt;
import io.ktor.util.InternalAPI;
import io.ktor.util.StringValuesKt;
import io.ktor.util.reflect.TypeInfo;
import java.util.Map;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 G2\u00020\u0001:\u0001GB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\t\u001a\u00020\u00062\u001d\u0010\b\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ&\u0010\u0010\u001a\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00060\u000e¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000¢\u0006\u0004\b\u0015\u0010\u0014J-\u0010\u001b\u001a\u00020\u0006\"\b\b\u0000\u0010\u0017*\u00020\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010\u001a\u001a\u00028\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001d\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0017*\u00020\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\t\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u001f\u001a\u0004\b \u0010!R\"\u0010#\u001a\u00020\"8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010*\u001a\u00020)8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R*\u0010/\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u00168\u0006@GX\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R*\u00106\u001a\u0002052\u0006\u0010.\u001a\u0002058\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0017\u0010<\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R(\u0010F\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010@8F@GX\u0086\u000e¢\u0006\f\u001a\u0004\bB\u0010C\"\u0004\bD\u0010E¨\u0006H"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/http/HttpMessageBuilder;", "<init>", "()V", "Lkotlin/Function2;", "Lio/ktor/http/URLBuilder;", "Lx6/t0;", "Lx6/n;", "block", "url", "(Lr7/p;)V", "Lio/ktor/client/request/HttpRequestData;", "build", "()Lio/ktor/client/request/HttpRequestData;", "Lkotlin/Function1;", "Lio/ktor/util/Attributes;", "setAttributes", "(Lr7/l;)V", "builder", "takeFromWithExecutionContext", "(Lio/ktor/client/request/HttpRequestBuilder;)Lio/ktor/client/request/HttpRequestBuilder;", "takeFrom", "", "T", "Lio/ktor/client/engine/HttpClientEngineCapability;", "key", "capability", "setCapability", "(Lio/ktor/client/engine/HttpClientEngineCapability;Ljava/lang/Object;)V", "getCapabilityOrNull", "(Lio/ktor/client/engine/HttpClientEngineCapability;)Ljava/lang/Object;", "Lio/ktor/http/URLBuilder;", "getUrl", "()Lio/ktor/http/URLBuilder;", "Lio/ktor/http/HttpMethod;", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "setMethod", "(Lio/ktor/http/HttpMethod;)V", "Lio/ktor/http/HeadersBuilder;", "headers", "Lio/ktor/http/HeadersBuilder;", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", "<set-?>", TtmlNode.TAG_BODY, "Ljava/lang/Object;", "getBody", "()Ljava/lang/Object;", "setBody", "(Ljava/lang/Object;)V", "Lka/v1;", "executionContext", "Lka/v1;", "getExecutionContext", "()Lka/v1;", "setExecutionContext$ktor_client_core", "(Lka/v1;)V", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "Lio/ktor/util/reflect/TypeInfo;", "value", "getBodyType", "()Lio/ktor/util/reflect/TypeInfo;", "setBodyType", "(Lio/ktor/util/reflect/TypeInfo;)V", "bodyType", "Companion", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestBuilder implements HttpMessageBuilder {
    private final URLBuilder url = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
    private HttpMethod method = HttpMethod.INSTANCE.getGet();
    private final HeadersBuilder headers = new HeadersBuilder(0, 1, null);
    private Object body = EmptyContent.INSTANCE;
    private v1 executionContext = m0.d();
    private final Attributes attributes = AttributesJvmKt.Attributes(true);

    public final HttpRequestData build() {
        Url urlBuild = this.url.build();
        HttpMethod httpMethod = this.method;
        Headers headersBuild = getHeaders().build();
        Object obj = this.body;
        OutgoingContent outgoingContent = obj instanceof OutgoingContent ? (OutgoingContent) obj : null;
        if (outgoingContent != null) {
            return new HttpRequestData(urlBuild, httpMethod, headersBuild, outgoingContent, this.executionContext, this.attributes);
        }
        throw new IllegalStateException(("No request transformation found: " + this.body).toString());
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final Object getBody() {
        return this.body;
    }

    public final TypeInfo getBodyType() {
        return (TypeInfo) this.attributes.getOrNull(RequestBodyKt.getBodyTypeAttributeKey());
    }

    public final <T> T getCapabilityOrNull(HttpClientEngineCapability<T> key) {
        Map map = (Map) this.attributes.getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
        if (map != null) {
            return (T) map.get(key);
        }
        return null;
    }

    public final v1 getExecutionContext() {
        return this.executionContext;
    }

    @Override // io.ktor.http.HttpMessageBuilder
    public HeadersBuilder getHeaders() {
        return this.headers;
    }

    public final HttpMethod getMethod() {
        return this.method;
    }

    public final URLBuilder getUrl() {
        return this.url;
    }

    public final void setAttributes(l<? super Attributes, t0> block) {
        block.invoke(this.attributes);
    }

    @InternalAPI
    public final void setBody(Object obj) {
        this.body = obj;
    }

    @InternalAPI
    public final void setBodyType(TypeInfo typeInfo) {
        if (typeInfo != null) {
            this.attributes.put(RequestBodyKt.getBodyTypeAttributeKey(), typeInfo);
        } else {
            this.attributes.remove(RequestBodyKt.getBodyTypeAttributeKey());
        }
    }

    public final <T> void setCapability(HttpClientEngineCapability<T> key, T capability) {
        ((Map) this.attributes.computeIfAbsent(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY(), HttpRequestBuilder$setCapability$capabilities$1.INSTANCE)).put(key, capability);
    }

    public final void setExecutionContext$ktor_client_core(v1 v1Var) {
        this.executionContext = v1Var;
    }

    public final void setMethod(HttpMethod httpMethod) {
        this.method = httpMethod;
    }

    public final HttpRequestBuilder takeFrom(HttpRequestBuilder builder) {
        this.method = builder.method;
        this.body = builder.body;
        setBodyType(builder.getBodyType());
        URLUtilsKt.takeFrom(this.url, builder.url);
        URLBuilder uRLBuilder = this.url;
        uRLBuilder.setEncodedPathSegments(uRLBuilder.getEncodedPathSegments());
        StringValuesKt.appendAll(getHeaders(), builder.getHeaders());
        AttributesKt.putAll(this.attributes, builder.attributes);
        return this;
    }

    @InternalAPI
    public final HttpRequestBuilder takeFromWithExecutionContext(HttpRequestBuilder builder) {
        this.executionContext = builder.executionContext;
        return takeFrom(builder);
    }

    public final void url(p<? super URLBuilder, ? super URLBuilder, t0> block) {
        URLBuilder uRLBuilder = this.url;
        block.invoke(uRLBuilder, uRLBuilder);
    }
}
