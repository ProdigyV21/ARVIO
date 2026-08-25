package io.ktor.websocket;

import d7.d;
import d7.h;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.websocket.Frame;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.j0;
import ka.k0;
import ka.m0;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import m2.f0;
import ma.f;
import ma.x;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a^\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u00012\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\"\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000bH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\"\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015\"\u0014\u0010\u0016\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lka/k0;", "Lma/x;", "Lio/ktor/websocket/Frame$Pong;", "outgoing", "Lio/ktor/websocket/Frame$Ping;", "ponger", "(Lka/k0;Lma/x;)Lma/x;", "Lio/ktor/websocket/Frame;", "", "periodMillis", "timeoutMillis", "Lkotlin/Function2;", "Lio/ktor/websocket/CloseReason;", "Ld7/d;", "Lx6/t0;", "", "onTimeout", "pinger", "(Lka/k0;Lma/x;JJLr7/p;)Lma/x;", "Lka/j0;", "PongerCoroutineName", "Lka/j0;", "PingerCoroutineName", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PingPongKt {
    private static final j0 PongerCoroutineName = new j0("ws-ponger");
    private static final j0 PingerCoroutineName = new j0("ws-pinger");

    /* JADX INFO: renamed from: io.ktor.websocket.PingPongKt$pinger$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.websocket.PingPongKt$pinger$1", f = "PingPong.kt", l = {64, 73, 95}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ma.j<Frame.Pong> $channel;
        final /* synthetic */ p<CloseReason, d<? super t0>, Object> $onTimeout;
        final /* synthetic */ x<Frame> $outgoing;
        final /* synthetic */ long $periodMillis;
        final /* synthetic */ long $timeoutMillis;
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: io.ktor.websocket.PingPongKt$pinger$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.websocket.PingPongKt$pinger$1$1", f = "PingPong.kt", l = {66}, m = "invokeSuspend")
        public static final class C02411 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ ma.j<Frame.Pong> $channel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02411(ma.j<Frame.Pong> jVar, d<? super C02411> dVar) {
                super(2, dVar);
                this.$channel = jVar;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C02411(this.$channel, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object objReceive;
                a aVar;
                int i10 = this.label;
                if (i10 != 0 && i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                do {
                    ma.j<Frame.Pong> jVar = this.$channel;
                    this.label = 1;
                    objReceive = jVar.receive(this);
                    aVar = a.f15033i;
                } while (objReceive != aVar);
                return aVar;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C02411) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(long j10, long j11, p<? super CloseReason, ? super d<? super t0>, ? extends Object> pVar, ma.j<Frame.Pong> jVar, x<? super Frame> xVar, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$periodMillis = j10;
            this.$timeoutMillis = j11;
            this.$onTimeout = pVar;
            this.$channel = jVar;
            this.$outgoing = xVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$periodMillis, this.$timeoutMillis, this.$onTimeout, this.$channel, this.$outgoing, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x00fe, code lost:
        
            if (r13.invoke(r0, r12) == r5) goto L34;
         */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00a5  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00dc A[Catch: CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, PHI: r0 r6 r13
          0x00dc: PHI (r0v11 byte[]) = (r0v9 byte[]), (r0v17 byte[]) binds: [B:28:0x00d9, B:12:0x0025] A[DONT_GENERATE, DONT_INLINE]
          0x00dc: PHI (r6v12 v7.d) = (r6v17 v7.d), (r6v18 v7.d) binds: [B:28:0x00d9, B:12:0x0025] A[DONT_GENERATE, DONT_INLINE]
          0x00dc: PHI (r13v9 java.lang.Object) = (r13v7 java.lang.Object), (r13v0 java.lang.Object) binds: [B:28:0x00d9, B:12:0x0025] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, blocks: (B:7:0x0010, B:12:0x0025, B:12:0x0025, B:12:0x0025, B:30:0x00dc, B:30:0x00dc, B:30:0x00dc, B:32:0x00e0, B:32:0x00e0, B:32:0x00e0, B:23:0x0090, B:23:0x0090, B:23:0x0090, B:27:0x00a7, B:27:0x00a7, B:27:0x00a7, B:15:0x0032, B:15:0x0032, B:15:0x0032), top: B:41:0x0008 }] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00e0 A[Catch: CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, TRY_LEAVE, TryCatch #0 {CancellationException | ClosedReceiveChannelException | ClosedSendChannelException -> 0x0103, blocks: (B:7:0x0010, B:12:0x0025, B:12:0x0025, B:12:0x0025, B:30:0x00dc, B:30:0x00dc, B:30:0x00dc, B:32:0x00e0, B:32:0x00e0, B:32:0x00e0, B:23:0x0090, B:23:0x0090, B:23:0x0090, B:27:0x00a7, B:27:0x00a7, B:27:0x00a7, B:15:0x0032, B:15:0x0032, B:15:0x0032), top: B:41:0x0008 }] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0101  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00d9 -> B:30:0x00dc). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 270
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.PingPongKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.PingPongKt$pinger$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Throwable, t0> {
        final /* synthetic */ t $actorJob;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(t tVar) {
            super(1);
            this.$actorJob = tVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            this.$actorJob.cancel((CancellationException) null);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.PingPongKt$ponger$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.websocket.PingPongKt$ponger$1", f = "PingPong.kt", l = {119, 32}, m = "invokeSuspend")
    public static final class C20581 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ma.j<Frame.Ping> $channel;
        final /* synthetic */ x<Frame.Pong> $outgoing;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C20581(ma.j<Frame.Ping> jVar, x<? super Frame.Pong> xVar, d<? super C20581> dVar) {
            super(2, dVar);
            this.$channel = jVar;
            this.$outgoing = xVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C20581(this.$channel, this.$outgoing, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0081, code lost:
        
            if (r5.send(r7, r10) == r3) goto L27;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x005d A[Catch: all -> 0x001d, TRY_LEAVE, TryCatch #1 {all -> 0x001d, blocks: (B:7:0x0018, B:19:0x0042, B:23:0x0054, B:25:0x005d, B:14:0x0033, B:18:0x003e), top: B:39:0x0006, outer: #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0084 A[Catch: ClosedSendChannelException -> 0x008e, TRY_ENTER, TRY_LEAVE, TryCatch #2 {ClosedSendChannelException -> 0x008e, blocks: (B:28:0x0084, B:32:0x008a, B:33:0x008d, B:17:0x003a, B:30:0x0088, B:7:0x0018, B:19:0x0042, B:23:0x0054, B:25:0x005d, B:14:0x0033, B:18:0x003e), top: B:39:0x0006, inners: #0, #1 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0081 -> B:8:0x001b). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                int r0 = r10.label
                r1 = 1
                r2 = 2
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L37
                if (r0 == r1) goto L27
                if (r0 != r2) goto L1f
                java.lang.Object r0 = r10.L$2
                ma.l r0 = (ma.l) r0
                java.lang.Object r4 = r10.L$1
                ma.w r4 = (ma.w) r4
                java.lang.Object r5 = r10.L$0
                ma.x r5 = (ma.x) r5
                k2.c.G(r11)     // Catch: java.lang.Throwable -> L1d
            L1b:
                r11 = r5
                goto L42
            L1d:
                r11 = move-exception
                goto L88
            L1f:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L27:
                java.lang.Object r0 = r10.L$2
                ma.l r0 = (ma.l) r0
                java.lang.Object r4 = r10.L$1
                ma.w r4 = (ma.w) r4
                java.lang.Object r5 = r10.L$0
                ma.x r5 = (ma.x) r5
                k2.c.G(r11)     // Catch: java.lang.Throwable -> L1d
                goto L54
            L37:
                k2.c.G(r11)
                ma.j<io.ktor.websocket.Frame$Ping> r4 = r10.$channel     // Catch: kotlinx.coroutines.channels.ClosedSendChannelException -> L8e
                ma.x<io.ktor.websocket.Frame$Pong> r11 = r10.$outgoing     // Catch: kotlinx.coroutines.channels.ClosedSendChannelException -> L8e
                ma.l r0 = r4.iterator()     // Catch: java.lang.Throwable -> L1d
            L42:
                r10.L$0 = r11     // Catch: java.lang.Throwable -> L1d
                r10.L$1 = r4     // Catch: java.lang.Throwable -> L1d
                r10.L$2 = r0     // Catch: java.lang.Throwable -> L1d
                r10.label = r1     // Catch: java.lang.Throwable -> L1d
                java.lang.Object r5 = r0.a(r10)     // Catch: java.lang.Throwable -> L1d
                if (r5 != r3) goto L51
                goto L83
            L51:
                r9 = r5
                r5 = r11
                r11 = r9
            L54:
                java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch: java.lang.Throwable -> L1d
                boolean r11 = r11.booleanValue()     // Catch: java.lang.Throwable -> L1d
                r6 = 0
                if (r11 == 0) goto L84
                java.lang.Object r11 = r0.next()     // Catch: java.lang.Throwable -> L1d
                io.ktor.websocket.Frame$Ping r11 = (io.ktor.websocket.Frame.Ping) r11     // Catch: java.lang.Throwable -> L1d
                vc.b r7 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()     // Catch: java.lang.Throwable -> L1d
                java.lang.String r8 = "Received ping message, sending pong message"
                r7.h(r8)     // Catch: java.lang.Throwable -> L1d
                io.ktor.websocket.Frame$Pong r7 = new io.ktor.websocket.Frame$Pong     // Catch: java.lang.Throwable -> L1d
                byte[] r11 = r11.getData()     // Catch: java.lang.Throwable -> L1d
                r7.<init>(r11, r6, r2, r6)     // Catch: java.lang.Throwable -> L1d
                r10.L$0 = r5     // Catch: java.lang.Throwable -> L1d
                r10.L$1 = r4     // Catch: java.lang.Throwable -> L1d
                r10.L$2 = r0     // Catch: java.lang.Throwable -> L1d
                r10.label = r2     // Catch: java.lang.Throwable -> L1d
                java.lang.Object r11 = r5.send(r7, r10)     // Catch: java.lang.Throwable -> L1d
                if (r11 != r3) goto L1b
            L83:
                return r3
            L84:
                r4.cancel(r6)     // Catch: kotlinx.coroutines.channels.ClosedSendChannelException -> L8e
                goto L8e
            L88:
                throw r11     // Catch: java.lang.Throwable -> L89
            L89:
                r0 = move-exception
                qb.d.e(r4, r11)     // Catch: kotlinx.coroutines.channels.ClosedSendChannelException -> L8e
                throw r0     // Catch: kotlinx.coroutines.channels.ClosedSendChannelException -> L8e
            L8e:
                x6.t0 r11 = x6.t0.f22605a
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.PingPongKt.C20581.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C20581) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final x<Frame.Pong> pinger(k0 k0Var, x<? super Frame> xVar, long j10, long j11, p<? super CloseReason, ? super d<? super t0>, ? extends Object> pVar) {
        x1 x1VarC = m0.c();
        f fVarA = f0.a(Integer.MAX_VALUE, 0, 6);
        m0.p(k0Var, h.a(x1VarC, PingerCoroutineName), 0, new AnonymousClass1(j10, j11, pVar, fVarA, xVar, null), 2);
        ((v1) k0Var.getCoroutineContext().get(u1.f19642i)).invokeOnCompletion(new AnonymousClass2(x1VarC));
        return fVarA;
    }

    public static final x<Frame.Ping> ponger(k0 k0Var, x<? super Frame.Pong> xVar) {
        f fVarA = f0.a(5, 0, 6);
        m0.p(k0Var, PongerCoroutineName, 0, new C20581(fVarA, xVar, null), 2);
        return fVarA;
    }
}
