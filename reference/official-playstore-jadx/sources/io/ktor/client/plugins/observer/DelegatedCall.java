package io.ktor.client.plugins.observer;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/client/plugins/observer/DelegatedCall;", "Lio/ktor/client/call/HttpClientCall;", "client", "Lio/ktor/client/HttpClient;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "originCall", "(Lio/ktor/client/HttpClient;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/client/call/HttpClientCall;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DelegatedCall extends HttpClientCall {
    public DelegatedCall(HttpClient httpClient, ByteReadChannel byteReadChannel, HttpClientCall httpClientCall) {
        super(httpClient);
        setRequest(new DelegatedRequest(this, httpClientCall.getRequest()));
        setResponse(new DelegatedResponse(this, byteReadChannel, httpClientCall.getResponse()));
    }
}
