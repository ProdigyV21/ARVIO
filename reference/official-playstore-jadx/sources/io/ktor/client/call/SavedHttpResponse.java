package io.ktor.client.call;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.j;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.date.GMTDate;
import io.ktor.utils.io.ByteChannelCtorKt;
import io.ktor.utils.io.ByteReadChannel;
import ka.m0;
import ka.t;
import ka.x1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u00198\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u00198\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u001a\u0010!\u001a\u00020 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010&\u001a\u00020%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R \u0010+\u001a\u00020*8\u0016X\u0096\u0004¢\u0006\u0012\n\u0004\b+\u0010,\u0012\u0004\b/\u00100\u001a\u0004\b-\u0010.¨\u00061"}, d2 = {"Lio/ktor/client/call/SavedHttpResponse;", "Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/call/SavedHttpCall;", "call", "", TtmlNode.TAG_BODY, TtmlNode.ATTR_TTS_ORIGIN, "<init>", "(Lio/ktor/client/call/SavedHttpCall;[BLio/ktor/client/statement/HttpResponse;)V", "Lio/ktor/client/call/SavedHttpCall;", "getCall", "()Lio/ktor/client/call/SavedHttpCall;", "Lka/t;", "context", "Lka/t;", "Lio/ktor/http/HttpStatusCode;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "Lio/ktor/http/HttpProtocolVersion;", "version", "Lio/ktor/http/HttpProtocolVersion;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "Lio/ktor/util/date/GMTDate;", "requestTime", "Lio/ktor/util/date/GMTDate;", "getRequestTime", "()Lio/ktor/util/date/GMTDate;", "responseTime", "getResponseTime", "Lio/ktor/http/Headers;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "Ld7/j;", "coroutineContext", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Lio/ktor/utils/io/ByteReadChannel;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "getContent", "()Lio/ktor/utils/io/ByteReadChannel;", "getContent$annotations", "()V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SavedHttpResponse extends HttpResponse {
    private final SavedHttpCall call;
    private final ByteReadChannel content;
    private final t context;
    private final j coroutineContext;
    private final Headers headers;
    private final GMTDate requestTime;
    private final GMTDate responseTime;
    private final HttpStatusCode status;
    private final HttpProtocolVersion version;

    public SavedHttpResponse(SavedHttpCall savedHttpCall, byte[] bArr, HttpResponse httpResponse) {
        this.call = savedHttpCall;
        x1 x1VarC = m0.c();
        this.context = x1VarC;
        this.status = httpResponse.getStatus();
        this.version = httpResponse.getVersion();
        this.requestTime = httpResponse.getRequestTime();
        this.responseTime = httpResponse.getResponseTime();
        this.headers = httpResponse.getHeaders();
        this.coroutineContext = httpResponse.getCoroutineContext().plus(x1VarC);
        this.content = ByteChannelCtorKt.ByteReadChannel(bArr);
    }

    public static /* synthetic */ void getContent$annotations() {
    }

    @Override // io.ktor.client.statement.HttpResponse
    public ByteReadChannel getContent() {
        return this.content;
    }

    @Override // io.ktor.client.statement.HttpResponse, ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.http.HttpMessage
    public Headers getHeaders() {
        return this.headers;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public GMTDate getRequestTime() {
        return this.requestTime;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public GMTDate getResponseTime() {
        return this.responseTime;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public HttpStatusCode getStatus() {
        return this.status;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public HttpProtocolVersion getVersion() {
        return this.version;
    }

    @Override // io.ktor.client.statement.HttpResponse
    public SavedHttpCall getCall() {
        return this.call;
    }
}
