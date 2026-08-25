package io.ktor.client.plugins;

import d7.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B=\u00121\u0010\n\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0002ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fRE\u0010\n\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00028\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b\n\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/client/plugins/ExceptionHandlerWrapper;", "Lio/ktor/client/plugins/HandlerWrapper;", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "cause", "Ld7/d;", "Lx6/t0;", "", "handler", "<init>", "(Lr7/p;)V", "Lr7/p;", "getHandler", "()Lr7/p;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExceptionHandlerWrapper implements HandlerWrapper {
    private final p<Throwable, d<? super t0>, Object> handler;

    /* JADX WARN: Multi-variable type inference failed */
    public ExceptionHandlerWrapper(p<? super Throwable, ? super d<? super t0>, ? extends Object> pVar) {
        this.handler = pVar;
    }

    public final p<Throwable, d<? super t0>, Object> getHandler() {
        return this.handler;
    }
}
