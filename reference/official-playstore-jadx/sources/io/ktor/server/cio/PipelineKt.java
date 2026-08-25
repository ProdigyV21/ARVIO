package io.ktor.server.cio;

import io.ktor.http.ContentDisposition;
import ka.j0;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\" \u0010\u0001\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0001\u0010\u0002\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\" \u0010\u0007\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010\u0002\u0012\u0004\b\t\u0010\u0006\u001a\u0004\b\b\u0010\u0004\" \u0010\n\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\n\u0010\u0002\u0012\u0004\b\f\u0010\u0006\u001a\u0004\b\u000b\u0010\u0004*v\u0010\u0017\"8\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\u0002\b\u001628\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\u0002\b\u0016¨\u0006\u0018"}, d2 = {"Lka/j0;", "HttpPipelineCoroutine", "Lka/j0;", "getHttpPipelineCoroutine", "()Lka/j0;", "getHttpPipelineCoroutine$annotations", "()V", "HttpPipelineWriterCoroutine", "getHttpPipelineWriterCoroutine", "getHttpPipelineWriterCoroutine$annotations", "RequestHandlerCoroutine", "getRequestHandlerCoroutine", "getRequestHandlerCoroutine$annotations", "Lkotlin/Function3;", "Lio/ktor/server/cio/backend/ServerRequestScope;", "Lio/ktor/http/cio/Request;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "HttpRequestHandler", "ktor-server-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PipelineKt {
    private static final j0 HttpPipelineCoroutine = new j0("http-pipeline");
    private static final j0 HttpPipelineWriterCoroutine = new j0("http-pipeline-writer");
    private static final j0 RequestHandlerCoroutine = new j0("request-handler");

    public static final j0 getHttpPipelineCoroutine() {
        return HttpPipelineCoroutine;
    }

    @e
    public static /* synthetic */ void getHttpPipelineCoroutine$annotations() {
    }

    public static final j0 getHttpPipelineWriterCoroutine() {
        return HttpPipelineWriterCoroutine;
    }

    @e
    public static /* synthetic */ void getHttpPipelineWriterCoroutine$annotations() {
    }

    public static final j0 getRequestHandlerCoroutine() {
        return RequestHandlerCoroutine;
    }

    @e
    public static /* synthetic */ void getRequestHandlerCoroutine$annotations() {
    }
}
