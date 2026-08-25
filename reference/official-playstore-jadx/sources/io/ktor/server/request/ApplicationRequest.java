package io.ktor.server.request;

import io.ktor.http.Headers;
import io.ktor.http.Parameters;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.server.application.ApplicationCall;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u001c\u001a\u00020\u001dH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/server/request/ApplicationRequest;", "", "call", "Lio/ktor/server/application/ApplicationCall;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "cookies", "Lio/ktor/server/request/RequestCookies;", "getCookies", "()Lio/ktor/server/request/RequestCookies;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "local", "Lio/ktor/http/RequestConnectionPoint;", "getLocal", "()Lio/ktor/http/RequestConnectionPoint;", "pipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "getPipeline", "()Lio/ktor/server/request/ApplicationReceivePipeline;", "queryParameters", "Lio/ktor/http/Parameters;", "getQueryParameters", "()Lio/ktor/http/Parameters;", "rawQueryParameters", "getRawQueryParameters", "receiveChannel", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationRequest {
    ApplicationCall getCall();

    RequestCookies getCookies();

    Headers getHeaders();

    RequestConnectionPoint getLocal();

    ApplicationReceivePipeline getPipeline();

    Parameters getQueryParameters();

    Parameters getRawQueryParameters();

    ByteReadChannel receiveChannel();
}
