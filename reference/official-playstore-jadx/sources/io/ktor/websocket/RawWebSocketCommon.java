package io.ktor.websocket;

import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.websocket.WebSocketSession;
import java.util.List;
import ka.j0;
import ka.m0;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import m2.f0;
import ma.j;
import ma.w;
import ma.x;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001?B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000f\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0017¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014R\"\u0010\u0007\u001a\u00020\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\t\u001a\u00020\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010%R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010+\u001a\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00100R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020#028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020#068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u001e\u0010>\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030;0:8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006@"}, d2 = {"Lio/ktor/websocket/RawWebSocketCommon;", "Lio/ktor/websocket/WebSocketSession;", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "", "maxFrameSize", "", "masking", "Ld7/j;", "coroutineContext", "<init>", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JZLd7/j;)V", "Lx6/t0;", "flush", "(Ld7/d;)Ljava/lang/Object;", "terminate", "()V", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "J", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "Z", "getMasking", "()Z", "setMasking", "(Z)V", "Lka/t;", "socketJob", "Lka/t;", "Lma/j;", "Lio/ktor/websocket/Frame;", "_incoming", "Lma/j;", "", "_outgoing", "", "lastOpcode", "I", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Lka/v1;", "writerJob", "Lka/v1;", "readerJob", "Lma/w;", "getIncoming", "()Lma/w;", "incoming", "Lma/x;", "getOutgoing", "()Lma/x;", "outgoing", "", "Lio/ktor/websocket/WebSocketExtension;", "getExtensions", "()Ljava/util/List;", "extensions", "FlushRequest", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RawWebSocketCommon implements WebSocketSession {
    private final j<Frame> _incoming;
    private final j<Object> _outgoing;
    private final d7.j coroutineContext;
    private final ByteReadChannel input;
    private int lastOpcode;
    private boolean masking;
    private long maxFrameSize;
    private final ByteWriteChannel output;
    private final v1 readerJob;
    private final t socketJob;
    private final v1 writerJob;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\n\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/websocket/RawWebSocketCommon$FlushRequest;", "", "Lka/v1;", "parent", "<init>", "(Lka/v1;)V", "", "complete", "()Z", "Lx6/t0;", "await", "(Ld7/d;)Ljava/lang/Object;", "Lka/t;", "done", "Lka/t;", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FlushRequest {
        private final t done;

        public FlushRequest(v1 v1Var) {
            this.done = new x1(v1Var);
        }

        public final Object await(d<? super t0> dVar) {
            Object objJoin = this.done.join(dVar);
            return objJoin == a.f15033i ? objJoin : t0.f22605a;
        }

        public final boolean complete() {
            return this.done.complete();
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.RawWebSocketCommon$flush$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.RawWebSocketCommon", f = "RawWebSocketCommon.kt", l = {123, 126, 131}, m = "flush")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RawWebSocketCommon.this.flush(this);
        }
    }

    public RawWebSocketCommon(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, boolean z, d7.j jVar) {
        this.input = byteReadChannel;
        this.output = byteWriteChannel;
        this.maxFrameSize = j10;
        this.masking = z;
        x1 x1Var = new x1((v1) jVar.get(u1.f19642i));
        this.socketJob = x1Var;
        this._incoming = f0.a(8, 0, 6);
        this._outgoing = f0.a(8, 0, 6);
        this.coroutineContext = jVar.plus(x1Var).plus(new j0("raw-ws"));
        this.writerJob = m0.o(3, new j0("ws-writer"), this, new RawWebSocketCommon$writerJob$1(this, null));
        this.readerJob = m0.o(3, new j0("ws-reader"), this, new RawWebSocketCommon$readerJob$1(this, null));
        x1Var.complete();
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0073, code lost:
    
        if (r9.send(r1, r0) == r6) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009f, code lost:
    
        if (r4.await(r0) != r6) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int, io.ktor.websocket.RawWebSocketCommon$FlushRequest] */
    @Override // io.ktor.websocket.WebSocketSession
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object flush(d7.d<? super x6.t0> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.ktor.websocket.RawWebSocketCommon.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.websocket.RawWebSocketCommon$flush$1 r0 = (io.ktor.websocket.RawWebSocketCommon.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.RawWebSocketCommon$flush$1 r0 = new io.ktor.websocket.RawWebSocketCommon$flush$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L51
            if (r1 == r4) goto L3f
            if (r1 == r3) goto L37
            if (r1 != r2) goto L2f
            k2.c.G(r9)
            goto La2
        L2f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L37:
            java.lang.Object r1 = r0.L$0
            io.ktor.websocket.RawWebSocketCommon$FlushRequest r1 = (io.ktor.websocket.RawWebSocketCommon.FlushRequest) r1
            k2.c.G(r9)
            goto L92
        L3f:
            java.lang.Object r1 = r0.L$2
            io.ktor.websocket.RawWebSocketCommon$FlushRequest r1 = (io.ktor.websocket.RawWebSocketCommon.FlushRequest) r1
            java.lang.Object r4 = r0.L$1
            io.ktor.websocket.RawWebSocketCommon$FlushRequest r4 = (io.ktor.websocket.RawWebSocketCommon.FlushRequest) r4
            java.lang.Object r7 = r0.L$0
            io.ktor.websocket.RawWebSocketCommon r7 = (io.ktor.websocket.RawWebSocketCommon) r7
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L7d
            goto L93
        L4f:
            r9 = move-exception
            goto L79
        L51:
            k2.c.G(r9)
            io.ktor.websocket.RawWebSocketCommon$FlushRequest r1 = new io.ktor.websocket.RawWebSocketCommon$FlushRequest
            d7.j r9 = r8.getCoroutineContext()
            ka.u1 r7 = ka.u1.f19642i
            d7.j$a r9 = r9.get(r7)
            ka.v1 r9 = (ka.v1) r9
            r1.<init>(r9)
            ma.j<java.lang.Object> r9 = r8._outgoing     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L76
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L76
            r0.L$1 = r1     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L76
            r0.L$2 = r1     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L76
            r0.label = r4     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L76
            java.lang.Object r9 = r9.send(r1, r0)     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L76
            if (r9 != r6) goto L92
            goto La1
        L76:
            r7 = r8
            r4 = r1
            goto L7d
        L79:
            r1.complete()
            throw r9
        L7d:
            r1.complete()
            ka.v1 r9 = r7.writerJob
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r5
            r0.label = r3
            java.lang.Object r9 = r9.join(r0)
            if (r9 != r6) goto L91
            goto La1
        L91:
            r1 = r4
        L92:
            r4 = r1
        L93:
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.label = r2
            java.lang.Object r9 = r4.await(r0)
            if (r9 != r6) goto La2
        La1:
            return r6
        La2:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommon.flush(d7.d):java.lang.Object");
    }

    @Override // io.ktor.websocket.WebSocketSession, ka.k0
    public d7.j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public List<WebSocketExtension<?>> getExtensions() {
        return z.f19728i;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public w<Frame> getIncoming() {
        return this._incoming;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public boolean getMasking() {
        return this.masking;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public long getMaxFrameSize() {
        return this.maxFrameSize;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public x<Frame> getOutgoing() {
        return this._outgoing;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object send(Frame frame, d<? super t0> dVar) {
        return WebSocketSession.DefaultImpls.send(this, frame, dVar);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMasking(boolean z) {
        this.masking = z;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMaxFrameSize(long j10) {
        this.maxFrameSize = j10;
    }

    @Override // io.ktor.websocket.WebSocketSession
    @x6.e
    public void terminate() {
        getOutgoing().close(null);
        this.socketJob.complete();
    }

    public /* synthetic */ RawWebSocketCommon(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, boolean z, d7.j jVar, int i10, h hVar) {
        this(byteReadChannel, byteWriteChannel, (i10 & 4) != 0 ? 2147483647L : j10, (i10 & 8) != 0 ? false : z, jVar);
    }
}
