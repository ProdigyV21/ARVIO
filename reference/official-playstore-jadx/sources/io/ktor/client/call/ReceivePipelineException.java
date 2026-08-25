package io.ktor.client.call;

import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/ktor/client/call/ReceivePipelineException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "request", "Lio/ktor/client/call/HttpClientCall;", "info", "Lio/ktor/util/reflect/TypeInfo;", "cause", "", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getInfo", "()Lio/ktor/util/reflect/TypeInfo;", "getRequest", "()Lio/ktor/client/call/HttpClientCall;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ReceivePipelineException extends IllegalStateException {
    private final Throwable cause;
    private final TypeInfo info;
    private final HttpClientCall request;

    public ReceivePipelineException(HttpClientCall httpClientCall, TypeInfo typeInfo, Throwable th) {
        super("Fail to run receive pipeline: " + th);
        this.request = httpClientCall;
        this.info = typeInfo;
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public final TypeInfo getInfo() {
        return this.info;
    }

    public final HttpClientCall getRequest() {
        return this.request;
    }
}
