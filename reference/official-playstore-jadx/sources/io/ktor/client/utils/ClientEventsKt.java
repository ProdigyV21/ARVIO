package io.ktor.client.utils;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.events.EventDefinition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0004\"\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0004\"\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0004¨\u0006\u000f"}, d2 = {"HttpRequestCreated", "Lio/ktor/events/EventDefinition;", "Lio/ktor/client/request/HttpRequestBuilder;", "getHttpRequestCreated", "()Lio/ktor/events/EventDefinition;", "HttpRequestIsReadyForSending", "getHttpRequestIsReadyForSending", "HttpResponseCancelled", "Lio/ktor/client/statement/HttpResponse;", "getHttpResponseCancelled", "HttpResponseReceiveFailed", "Lio/ktor/client/utils/HttpResponseReceiveFail;", "getHttpResponseReceiveFailed", "HttpResponseReceived", "getHttpResponseReceived", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClientEventsKt {
    private static final EventDefinition<HttpRequestBuilder> HttpRequestCreated = new EventDefinition<>();
    private static final EventDefinition<HttpRequestBuilder> HttpRequestIsReadyForSending = new EventDefinition<>();
    private static final EventDefinition<HttpResponse> HttpResponseReceived = new EventDefinition<>();
    private static final EventDefinition<HttpResponseReceiveFail> HttpResponseReceiveFailed = new EventDefinition<>();
    private static final EventDefinition<HttpResponse> HttpResponseCancelled = new EventDefinition<>();

    public static final EventDefinition<HttpRequestBuilder> getHttpRequestCreated() {
        return HttpRequestCreated;
    }

    public static final EventDefinition<HttpRequestBuilder> getHttpRequestIsReadyForSending() {
        return HttpRequestIsReadyForSending;
    }

    public static final EventDefinition<HttpResponse> getHttpResponseCancelled() {
        return HttpResponseCancelled;
    }

    public static final EventDefinition<HttpResponseReceiveFail> getHttpResponseReceiveFailed() {
        return HttpResponseReceiveFailed;
    }

    public static final EventDefinition<HttpResponse> getHttpResponseReceived() {
        return HttpResponseReceived;
    }
}
