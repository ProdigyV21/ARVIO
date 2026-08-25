package io.ktor.server.cio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.http.cio.Request;
import io.ktor.server.cio.backend.ServerRequestScope;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import k2.c;
import ka.k0;
import ka.r;
import kotlin.Metadata;
import r7.q;
import r7.t;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a¤\u0001\u0010\u0015\u001a\u00020\u0014*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0083\u0001\u0010\u0013\u001a\u007f\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\r\u0018\u00010\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0003¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lka/k0;", "Lio/ktor/server/cio/HttpServerSettings;", "settings", "Lkotlin/Function6;", "Lio/ktor/http/cio/Request;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "Lka/r;", "", "upgraded", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "handler", "Lio/ktor/server/cio/HttpServer;", "httpServer", "(Lka/k0;Lio/ktor/server/cio/HttpServerSettings;Lr7/t;)Lio/ktor/server/cio/HttpServer;", "ktor-server-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpServerKt {

    /* JADX INFO: renamed from: io.ktor.server.cio.HttpServerKt$httpServer$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/server/cio/backend/ServerRequestScope;", "Lio/ktor/http/cio/Request;", "request", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/cio/backend/ServerRequestScope;Lio/ktor/http/cio/Request;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.HttpServerKt$httpServer$1", f = "HttpServer.kt", l = {56}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<ServerRequestScope, Request, d<? super t0>, Object> {
        final /* synthetic */ t<k0, Request, ByteReadChannel, ByteWriteChannel, r<Boolean>, d<? super t0>, Object> $handler;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(t<? super k0, ? super Request, ? super ByteReadChannel, ? super ByteWriteChannel, ? super r<Boolean>, ? super d<? super t0>, ? extends Object> tVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = tVar;
        }

        @Override // r7.q
        public final Object invoke(ServerRequestScope serverRequestScope, Request request, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = serverRequestScope;
            anonymousClass1.L$1 = request;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ServerRequestScope serverRequestScope = (ServerRequestScope) this.L$0;
                Request request = (Request) this.L$1;
                t<k0, Request, ByteReadChannel, ByteWriteChannel, r<Boolean>, d<? super t0>, Object> tVar = this.$handler;
                ByteReadChannel input = serverRequestScope.getInput();
                ByteWriteChannel output = serverRequestScope.getOutput();
                r<Boolean> upgraded = serverRequestScope.getUpgraded();
                this.L$0 = null;
                this.label = 1;
                Object objInvoke = tVar.invoke(serverRequestScope, request, input, output, upgraded, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    @x6.e
    public static final HttpServer httpServer(k0 k0Var, HttpServerSettings httpServerSettings, t<? super k0, ? super Request, ? super ByteReadChannel, ? super ByteWriteChannel, ? super r<Boolean>, ? super d<? super t0>, ? extends Object> tVar) {
        return io.ktor.server.cio.backend.HttpServerKt.httpServer(k0Var, httpServerSettings, new AnonymousClass1(tVar, null));
    }
}
