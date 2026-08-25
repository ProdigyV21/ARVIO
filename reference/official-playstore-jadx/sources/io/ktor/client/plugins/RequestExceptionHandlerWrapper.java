package io.ktor.client.plugins;

import d7.d;
import io.ktor.client.request.HttpRequest;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001BR\u0012F\u0010\f\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eRZ\u0010\f\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00028\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b\f\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/RequestExceptionHandlerWrapper;", "Lio/ktor/client/plugins/HandlerWrapper;", "Lkotlin/Function3;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "cause", "Lio/ktor/client/request/HttpRequest;", "request", "Ld7/d;", "Lx6/t0;", "", "handler", "<init>", "(Lr7/q;)V", "Lr7/q;", "getHandler", "()Lr7/q;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RequestExceptionHandlerWrapper implements HandlerWrapper {
    private final q<Throwable, HttpRequest, d<? super t0>, Object> handler;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestExceptionHandlerWrapper(q<? super Throwable, ? super HttpRequest, ? super d<? super t0>, ? extends Object> qVar) {
        this.handler = qVar;
    }

    public final q<Throwable, HttpRequest, d<? super t0>, Object> getHandler() {
        return this.handler;
    }
}
