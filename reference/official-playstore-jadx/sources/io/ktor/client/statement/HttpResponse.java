package io.ktor.client.statement;

import d7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.http.HttpMessage;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.InternalAPI;
import io.ktor.util.date.GMTDate;
import io.ktor.utils.io.ByteReadChannel;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\f\u001a\u00020\b8&X§\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u00158&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001c\u001a\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001b¨\u0006\u001f"}, d2 = {"Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/http/HttpMessage;", "Lka/k0;", "<init>", "()V", "", "toString", "()Ljava/lang/String;", "Lio/ktor/utils/io/ByteReadChannel;", "getContent", "()Lio/ktor/utils/io/ByteReadChannel;", "getContent$annotations", "content", "Lio/ktor/client/call/HttpClientCall;", "getCall", "()Lio/ktor/client/call/HttpClientCall;", "call", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "status", "Lio/ktor/http/HttpProtocolVersion;", "getVersion", "()Lio/ktor/http/HttpProtocolVersion;", "version", "Lio/ktor/util/date/GMTDate;", "getRequestTime", "()Lio/ktor/util/date/GMTDate;", "requestTime", "getResponseTime", "responseTime", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class HttpResponse implements HttpMessage, k0 {
    @InternalAPI
    public static /* synthetic */ void getContent$annotations() {
    }

    public abstract HttpClientCall getCall();

    public abstract ByteReadChannel getContent();

    public abstract /* synthetic */ j getCoroutineContext();

    public abstract GMTDate getRequestTime();

    public abstract GMTDate getResponseTime();

    public abstract HttpStatusCode getStatus();

    public abstract HttpProtocolVersion getVersion();

    public String toString() {
        return "HttpResponse[" + HttpResponseKt.getRequest(this).getUrl() + ", " + getStatus() + ']';
    }
}
