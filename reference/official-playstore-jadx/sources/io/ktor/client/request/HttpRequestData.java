package io.ktor.client.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.engine.HttpClientEngineCapabilityKt;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.Attributes;
import io.ktor.util.InternalAPI;
import java.util.Map;
import java.util.Set;
import ka.v1;
import kotlin.Metadata;
import kotlin.collections.b0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\"\n\u0002\b\u0005\u0018\u00002\u00020\u0001B9\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0013\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010'\u001a\u0004\b(\u0010)R$\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110*8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.¨\u0006/"}, d2 = {"Lio/ktor/client/request/HttpRequestData;", "", "Lio/ktor/http/Url;", "url", "Lio/ktor/http/HttpMethod;", "method", "Lio/ktor/http/Headers;", "headers", "Lio/ktor/http/content/OutgoingContent;", TtmlNode.TAG_BODY, "Lka/v1;", "executionContext", "Lio/ktor/util/Attributes;", "attributes", "<init>", "(Lio/ktor/http/Url;Lio/ktor/http/HttpMethod;Lio/ktor/http/Headers;Lio/ktor/http/content/OutgoingContent;Lka/v1;Lio/ktor/util/Attributes;)V", "T", "Lio/ktor/client/engine/HttpClientEngineCapability;", "key", "getCapabilityOrNull", "(Lio/ktor/client/engine/HttpClientEngineCapability;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "Lio/ktor/http/Url;", "getUrl", "()Lio/ktor/http/Url;", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "Lio/ktor/http/content/OutgoingContent;", "getBody", "()Lio/ktor/http/content/OutgoingContent;", "Lka/v1;", "getExecutionContext", "()Lka/v1;", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "", "requiredCapabilities", "Ljava/util/Set;", "getRequiredCapabilities$ktor_client_core", "()Ljava/util/Set;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestData {
    private final Attributes attributes;
    private final OutgoingContent body;
    private final v1 executionContext;
    private final Headers headers;
    private final HttpMethod method;
    private final Set<HttpClientEngineCapability<?>> requiredCapabilities;
    private final Url url;

    @InternalAPI
    public HttpRequestData(Url url, HttpMethod httpMethod, Headers headers, OutgoingContent outgoingContent, v1 v1Var, Attributes attributes) {
        Set<HttpClientEngineCapability<?>> setKeySet;
        this.url = url;
        this.method = httpMethod;
        this.headers = headers;
        this.body = outgoingContent;
        this.executionContext = v1Var;
        this.attributes = attributes;
        Map map = (Map) attributes.getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
        this.requiredCapabilities = (map == null || (setKeySet = map.keySet()) == null) ? b0.f19686i : setKeySet;
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final OutgoingContent getBody() {
        return this.body;
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

    public final Headers getHeaders() {
        return this.headers;
    }

    public final HttpMethod getMethod() {
        return this.method;
    }

    public final Set<HttpClientEngineCapability<?>> getRequiredCapabilities$ktor_client_core() {
        return this.requiredCapabilities;
    }

    public final Url getUrl() {
        return this.url;
    }

    public String toString() {
        return "HttpRequestData(url=" + this.url + ", method=" + this.method + ')';
    }
}
