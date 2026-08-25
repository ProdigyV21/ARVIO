package io.ktor.server.cio.backend;

import d7.d;
import d7.h;
import io.ktor.http.ContentDisposition;
import io.ktor.http.cio.Request;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.selector.SelectorManagerKt;
import io.ktor.network.sockets.ServerSocket;
import io.ktor.server.cio.HttpServer;
import io.ktor.server.cio.HttpServerSettings;
import io.ktor.server.cio.internal.WeakTimeoutQueue;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.io.IOException;
import ka.j0;
import ka.k0;
import ka.m0;
import ka.o2;
import ka.s;
import ka.t;
import ka.x1;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aZ\u0010\u000f\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012<\u0010\r\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0003¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lka/k0;", "Lio/ktor/server/cio/HttpServerSettings;", "settings", "Lkotlin/Function3;", "Lio/ktor/server/cio/backend/ServerRequestScope;", "Lio/ktor/http/cio/Request;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "handler", "Lio/ktor/server/cio/HttpServer;", "httpServer", "(Lka/k0;Lio/ktor/server/cio/HttpServerSettings;Lr7/q;)Lio/ktor/server/cio/HttpServer;", "ktor-server-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpServerKt {

    /* JADX INFO: renamed from: io.ktor.server.cio.backend.HttpServerKt$httpServer$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "cause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        final /* synthetic */ t $serverLatch;
        final /* synthetic */ ka.r<ServerSocket> $socket;
        final /* synthetic */ WeakTimeoutQueue $timeout;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(t tVar, WeakTimeoutQueue weakTimeoutQueue, ka.r<ServerSocket> rVar) {
            super(1);
            this.$serverLatch = tVar;
            this.$timeout = weakTimeoutQueue;
            this.$socket = rVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            if (th != null) {
                this.$socket.i(th);
            }
            this.$serverLatch.complete();
            this.$timeout.process();
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.backend.HttpServerKt$httpServer$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Throwable, t0> {
        final /* synthetic */ WeakTimeoutQueue $timeout;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(WeakTimeoutQueue weakTimeoutQueue) {
            super(1);
            this.$timeout = weakTimeoutQueue;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            this.$timeout.cancel();
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.backend.HttpServerKt$httpServer$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<Throwable, t0> {
        final /* synthetic */ SelectorManager $selector;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(SelectorManager selectorManager) {
            super(1);
            this.$selector = selectorManager;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) throws IOException {
            this.$selector.close();
        }
    }

    public static final HttpServer httpServer(k0 k0Var, HttpServerSettings httpServerSettings, q<? super ServerRequestScope, ? super Request, ? super d<? super t0>, ? extends Object> qVar) {
        s sVarB = m0.b();
        x1 x1VarC = m0.c();
        o2 o2VarO = m0.o(4, new j0("server-root-" + httpServerSettings.getPort()), k0Var, new HttpServerKt$httpServer$serverJob$1(x1VarC, null));
        SelectorManager SelectorManager = SelectorManagerKt.SelectorManager(k0Var.getCoroutineContext());
        WeakTimeoutQueue weakTimeoutQueue = new WeakTimeoutQueue(httpServerSettings.getConnectionIdleTimeoutSeconds() * 1000, null, 2, null);
        kotlin.jvm.internal.m0 m0Var = l0.f19747a;
        String strR = m0Var.b(HttpServer.class).r();
        if (strR == null && (strR = m0Var.b(HttpServer.class).o()) == null) {
            strR = m0Var.b(HttpServer.class).toString();
        }
        o2 o2VarP = m0.p(k0Var, h.a(o2VarO, new j0("accept-" + httpServerSettings.getPort())), 0, new HttpServerKt$httpServer$acceptJob$1(SelectorManager, httpServerSettings, sVarB, KtorSimpleLoggerJvmKt.KtorSimpleLogger(strR), o2VarO, weakTimeoutQueue, qVar, null), 2);
        o2VarP.invokeOnCompletion(new AnonymousClass1(x1VarC, weakTimeoutQueue, sVarB));
        o2VarO.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new AnonymousClass2(weakTimeoutQueue));
        o2VarO.invokeOnCompletion(new AnonymousClass3(SelectorManager));
        return new HttpServer(o2VarO, o2VarP, sVarB);
    }
}
