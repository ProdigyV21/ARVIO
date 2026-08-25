package io.ktor.server.cio.backend;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.cio.Request;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.ServerSocket;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.server.cio.HttpServerSettings;
import io.ktor.server.cio.internal.WeakTimeoutQueue;
import ka.k0;
import ka.r;
import ka.v1;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.cio.backend.HttpServerKt$httpServer$acceptJob$1", f = "HttpServer.kt", l = {66, 92, 92}, m = "invokeSuspend")
public final class HttpServerKt$httpServer$acceptJob$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ q<ServerRequestScope, Request, d<? super t0>, Object> $handler;
    final /* synthetic */ b $logger;
    final /* synthetic */ SelectorManager $selector;
    final /* synthetic */ v1 $serverJob;
    final /* synthetic */ HttpServerSettings $settings;
    final /* synthetic */ r<ServerSocket> $socket;
    final /* synthetic */ WeakTimeoutQueue $timeout;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: renamed from: io.ktor.server.cio.backend.HttpServerKt$httpServer$acceptJob$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "Lx6/t0;", "invoke", "(Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements l<SocketOptions.AcceptorOptions, t0> {
        final /* synthetic */ HttpServerSettings $settings;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpServerSettings httpServerSettings) {
            super(1);
            this.$settings = httpServerSettings;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SocketOptions.AcceptorOptions) obj);
            return t0.f22605a;
        }

        public final void invoke(SocketOptions.AcceptorOptions acceptorOptions) {
            acceptorOptions.setReuseAddress(this.$settings.getReuseAddress());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HttpServerKt$httpServer$acceptJob$1(SelectorManager selectorManager, HttpServerSettings httpServerSettings, r<ServerSocket> rVar, b bVar, v1 v1Var, WeakTimeoutQueue weakTimeoutQueue, q<? super ServerRequestScope, ? super Request, ? super d<? super t0>, ? extends Object> qVar, d<? super HttpServerKt$httpServer$acceptJob$1> dVar) {
        super(2, dVar);
        this.$selector = selectorManager;
        this.$settings = httpServerSettings;
        this.$socket = rVar;
        this.$logger = bVar;
        this.$serverJob = v1Var;
        this.$timeout = weakTimeoutQueue;
        this.$handler = qVar;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        HttpServerKt$httpServer$acceptJob$1 httpServerKt$httpServer$acceptJob$1 = new HttpServerKt$httpServer$acceptJob$1(this.$selector, this.$settings, this.$socket, this.$logger, this.$serverJob, this.$timeout, this.$handler, dVar);
        httpServerKt$httpServer$acceptJob$1.L$0 = obj;
        return httpServerKt$httpServer$acceptJob$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0174, code lost:
    
        if (io.ktor.network.sockets.SocketsKt.awaitClosed(r10, r17) != r7) goto L50;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f7 A[Catch: all -> 0x0061, IOException -> 0x0066, PHI: r0 r2 r3 r4 r5 r8 r9 r10 r11 r12 r13 r14
      0x00f7: PHI (r0v10 r7.q<io.ktor.server.cio.backend.ServerRequestScope, io.ktor.http.cio.Request, d7.d<? super x6.t0>, java.lang.Object>) = 
      (r0v9 r7.q<io.ktor.server.cio.backend.ServerRequestScope, io.ktor.http.cio.Request, d7.d<? super x6.t0>, java.lang.Object>)
      (r0v22 r7.q<io.ktor.server.cio.backend.ServerRequestScope, io.ktor.http.cio.Request, d7.d<? super x6.t0>, java.lang.Object>)
     binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r2v8 boolean) = (r2v7 boolean), (r2v0 boolean) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r3v8 ??) = (r3v24 ??), (r3v23 ?? I:??[int, float, short, byte, char]) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r4v7 char) = (r4v6 char), (r4v0 char) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r5v3 int) = (r5v2 int), (r5v0 int) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r8v8 int) = (r8v7 int), (r8v9 int) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r9v14 ka.k0) = (r9v13 ka.k0), (r9v16 ka.k0) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r10v11 io.ktor.network.sockets.ServerSocket) = (r10v10 io.ktor.network.sockets.ServerSocket), (r10v13 io.ktor.network.sockets.ServerSocket) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r11v6 io.ktor.server.cio.internal.WeakTimeoutQueue) = (r11v5 io.ktor.server.cio.internal.WeakTimeoutQueue), (r11v8 io.ktor.server.cio.internal.WeakTimeoutQueue) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r12v6 java.io.Closeable) = (r12v5 java.io.Closeable), (r12v8 java.io.Closeable) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r13v4 ka.k0) = (r13v3 ka.k0), (r13v6 ka.k0) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r14v3 java.lang.Object) = (r14v2 java.lang.Object), (r14v5 java.lang.Object) binds: [B:32:0x00f3, B:19:0x005d] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #0 {all -> 0x0061, blocks: (B:18:0x005a, B:34:0x00f7, B:35:0x00f9, B:31:0x00df, B:37:0x0127), top: B:58:0x005a, outer: #6, inners: #3 }] */
    /* JADX WARN: Type inference failed for: r2v12, types: [ka.v1] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v13, types: [io.ktor.server.cio.backend.HttpServerKt$httpServer$acceptJob$1$2$1, r7.l] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00f3 -> B:34:0x00f7). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.backend.HttpServerKt$httpServer$acceptJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((HttpServerKt$httpServer$acceptJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
