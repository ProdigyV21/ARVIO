package io.ktor.client.plugins;

import d7.j;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpCallValidator;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import r7.l;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0002\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a.\u0010\u000b\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00052\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\u000b\u0010\f\"\u0018\u0010\u000f\u001a\u00060\rj\u0002`\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\" \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"(\u0010\u001c\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b*`\u0010$\"-\b\u0001\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\"\u0012\u0006\u0012\u0004\u0018\u00010#0\u001d2-\b\u0001\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\"\u0012\u0006\u0012\u0004\u0018\u00010#0\u001d*\u008a\u0001\u0010(\"B\b\u0001\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00110&¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b('\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\"\u0012\u0006\u0012\u0004\u0018\u00010#0%2B\b\u0001\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00110&¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b('\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\"\u0012\u0006\u0012\u0004\u0018\u00010#0%*`\u0010+\"-\b\u0001\u0012\u0013\u0012\u00110)¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\"\u0012\u0006\u0012\u0004\u0018\u00010#0\u001d2-\b\u0001\u0012\u0013\u0012\u00110)¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\"\u0012\u0006\u0012\u0004\u0018\u00010#0\u001d¨\u0006,"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "builder", "io/ktor/client/plugins/HttpCallValidatorKt$HttpRequest$1", "HttpRequest", "(Lio/ktor/client/request/HttpRequestBuilder;)Lio/ktor/client/plugins/HttpCallValidatorKt$HttpRequest$1;", "Lio/ktor/client/HttpClientConfig;", "Lkotlin/Function1;", "Lio/ktor/client/plugins/HttpCallValidator$Config;", "Lx6/t0;", "Lx6/n;", "block", "HttpResponseValidator", "(Lio/ktor/client/HttpClientConfig;Lr7/l;)V", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "Lio/ktor/util/AttributeKey;", "", "ExpectSuccessAttributeKey", "Lio/ktor/util/AttributeKey;", "getExpectSuccessAttributeKey", "()Lio/ktor/util/AttributeKey;", "value", "getExpectSuccess", "(Lio/ktor/client/request/HttpRequestBuilder;)Z", "setExpectSuccess", "(Lio/ktor/client/request/HttpRequestBuilder;Z)V", "expectSuccess", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "cause", "Ld7/d;", "", "CallExceptionHandler", "Lkotlin/Function3;", "Lio/ktor/client/request/HttpRequest;", "request", "CallRequestExceptionHandler", "Lio/ktor/client/statement/HttpResponse;", "response", "ResponseValidator", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpCallValidatorKt {
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCallValidator");
    private static final AttributeKey<Boolean> ExpectSuccessAttributeKey = new AttributeKey<>("ExpectSuccessAttributeKey");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [io.ktor.client.plugins.HttpCallValidatorKt$HttpRequest$1] */
    public static final AnonymousClass1 HttpRequest(HttpRequestBuilder httpRequestBuilder) {
        return new HttpRequest() { // from class: io.ktor.client.plugins.HttpCallValidatorKt.HttpRequest.1
            private final Attributes attributes;
            private final Headers headers;
            private final HttpMethod method;
            private final Url url;

            {
                this.method = this.$builder.getMethod();
                this.url = this.$builder.getUrl().build();
                this.attributes = this.$builder.getAttributes();
                this.headers = this.$builder.getHeaders().build();
            }

            @Override // io.ktor.client.request.HttpRequest
            public Attributes getAttributes() {
                return this.attributes;
            }

            @Override // io.ktor.client.request.HttpRequest
            public HttpClientCall getCall() {
                throw new IllegalStateException("Call is not initialized");
            }

            @Override // io.ktor.client.request.HttpRequest
            public OutgoingContent getContent() {
                Object body = this.$builder.getBody();
                OutgoingContent outgoingContent = body instanceof OutgoingContent ? (OutgoingContent) body : null;
                if (outgoingContent != null) {
                    return outgoingContent;
                }
                throw new IllegalStateException(("Content was not transformed to OutgoingContent yet. Current body is " + this.$builder.getBody()).toString());
            }

            @Override // io.ktor.client.request.HttpRequest, ka.k0
            public j getCoroutineContext() {
                return HttpRequest.DefaultImpls.getCoroutineContext(this);
            }

            @Override // io.ktor.http.HttpMessage
            public Headers getHeaders() {
                return this.headers;
            }

            @Override // io.ktor.client.request.HttpRequest
            public HttpMethod getMethod() {
                return this.method;
            }

            @Override // io.ktor.client.request.HttpRequest
            public Url getUrl() {
                return this.url;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void HttpResponseValidator(HttpClientConfig<?> httpClientConfig, l<? super HttpCallValidator.Config, t0> lVar) {
        httpClientConfig.install(HttpCallValidator.INSTANCE, lVar);
    }

    public static final boolean getExpectSuccess(HttpRequestBuilder httpRequestBuilder) {
        Boolean bool = (Boolean) httpRequestBuilder.getAttributes().getOrNull(ExpectSuccessAttributeKey);
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    public static final AttributeKey<Boolean> getExpectSuccessAttributeKey() {
        return ExpectSuccessAttributeKey;
    }

    public static final void setExpectSuccess(HttpRequestBuilder httpRequestBuilder, boolean z) {
        httpRequestBuilder.getAttributes().put(ExpectSuccessAttributeKey, Boolean.valueOf(z));
    }
}
