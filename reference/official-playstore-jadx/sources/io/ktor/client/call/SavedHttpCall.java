package io.ktor.client.call;

import d7.d;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.statement.HttpResponse;
import io.ktor.utils.io.ByteChannelCtorKt;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\fH\u0094@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u00108\u0014X\u0094D¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/client/call/SavedHttpCall;", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/HttpClient;", "client", "Lio/ktor/client/request/HttpRequest;", "request", "Lio/ktor/client/statement/HttpResponse;", "response", "", "responseBody", "<init>", "(Lio/ktor/client/HttpClient;Lio/ktor/client/request/HttpRequest;Lio/ktor/client/statement/HttpResponse;[B)V", "Lio/ktor/utils/io/ByteReadChannel;", "getResponseContent", "(Ld7/d;)Ljava/lang/Object;", "[B", "", "allowDoubleReceive", "Z", "getAllowDoubleReceive", "()Z", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SavedHttpCall extends HttpClientCall {
    private final boolean allowDoubleReceive;
    private final byte[] responseBody;

    public SavedHttpCall(HttpClient httpClient, HttpRequest httpRequest, HttpResponse httpResponse, byte[] bArr) {
        super(httpClient);
        this.responseBody = bArr;
        setRequest(new SavedHttpRequest(this, httpRequest));
        setResponse(new SavedHttpResponse(this, bArr, httpResponse));
        this.allowDoubleReceive = true;
    }

    @Override // io.ktor.client.call.HttpClientCall
    public boolean getAllowDoubleReceive() {
        return this.allowDoubleReceive;
    }

    @Override // io.ktor.client.call.HttpClientCall
    public Object getResponseContent(d<? super ByteReadChannel> dVar) {
        return ByteChannelCtorKt.ByteReadChannel(this.responseBody);
    }
}
