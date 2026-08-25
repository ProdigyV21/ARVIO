package io.ktor.websocket;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import d7.j;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.websocket.CloseReason;
import io.ktor.websocket.DefaultWebSocketSession;
import io.ktor.websocket.Frame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.j0;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.p0;
import ka.r;
import ka.s;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x0;
import ka.x1;
import kotlin.Metadata;
import m2.f0;
import ma.w;
import ma.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0000\u0018\u0000 f2\u00020\u00012\u00020\u0002:\u0001fB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\r\u001a\u00020\f2\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\fH\u0017¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u001b\u001a\u00020\u001a2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0014J)\u0010$\u001a\u00020\f2\b\u0010!\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"H\u0082@ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u000f\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\fH\u0002¢\u0006\u0004\b)\u0010\u0016J%\u0010.\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0002¢\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0002¢\u0006\u0004\b2\u00101R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020 048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020,078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00020,078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u001e\u0010?\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u001a\u0010B\u001a\u00020A8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER*\u0010G\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00048\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR*\u0010\u0006\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00048\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010H\u001a\u0004\bM\u0010J\"\u0004\bN\u0010LR\"\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0O8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010SR\u001a\u0010W\u001a\b\u0012\u0004\u0012\u00020,0T8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001a\u0010Z\u001a\b\u0012\u0004\u0012\u00020,0\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u001e\u0010]\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\R$\u0010b\u001a\u00020&2\u0006\u0010^\u001a\u00020&8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b_\u0010(\"\u0004\b`\u0010aR$\u0010e\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bc\u0010J\"\u0004\bd\u0010L\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006g"}, d2 = {"Lio/ktor/websocket/DefaultWebSocketSessionImpl;", "Lio/ktor/websocket/DefaultWebSocketSession;", "Lio/ktor/websocket/WebSocketSession;", "raw", "", "pingInterval", "timeoutMillis", "<init>", "(Lio/ktor/websocket/WebSocketSession;JJ)V", "", "Lio/ktor/websocket/WebSocketExtension;", "negotiatedExtensions", "Lx6/t0;", TtmlNode.START, "(Ljava/util/List;)V", "", "message", "goingAway", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "flush", "(Ld7/d;)Ljava/lang/Object;", "terminate", "()V", "Lma/x;", "Lio/ktor/websocket/Frame$Ping;", "ponger", "Lka/v1;", "runIncomingProcessor", "(Lma/x;)Lka/v1;", "runOutgoingProcessor", "()Lka/v1;", "outgoingProcessorLoop", "Lio/ktor/websocket/CloseReason;", "reason", "", "exception", "sendCloseSequence", "(Lio/ktor/websocket/CloseReason;Ljava/lang/Throwable;Ld7/d;)Ljava/lang/Object;", "", "tryClose", "()Z", "runOrCancelPinger", "Lio/ktor/utils/io/core/BytePacketBuilder;", "packet", "Lio/ktor/websocket/Frame;", "frame", "checkMaxFrameSize", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lio/ktor/websocket/Frame;Ld7/d;)Ljava/lang/Object;", "processIncomingExtensions", "(Lio/ktor/websocket/Frame;)Lio/ktor/websocket/Frame;", "processOutgoingExtensions", "Lio/ktor/websocket/WebSocketSession;", "Lka/r;", "closeReasonRef", "Lka/r;", "Lma/j;", "filtered", "Lma/j;", "outgoingToBeProcessed", "Lka/t;", "context", "Lka/t;", "", "_extensions", "Ljava/util/List;", "Ld7/j;", "coroutineContext", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "newValue", "pingIntervalMillis", "J", "getPingIntervalMillis", "()J", "setPingIntervalMillis", "(J)V", "getTimeoutMillis", "setTimeoutMillis", "Lka/p0;", "closeReason", "Lka/p0;", "getCloseReason", "()Lka/p0;", "Lma/w;", "getIncoming", "()Lma/w;", "incoming", "getOutgoing", "()Lma/x;", "outgoing", "getExtensions", "()Ljava/util/List;", "extensions", "value", "getMasking", "setMasking", "(Z)V", "masking", "getMaxFrameSize", "setMaxFrameSize", "maxFrameSize", "Companion", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultWebSocketSessionImpl implements DefaultWebSocketSession, WebSocketSession {
    private final List<WebSocketExtension<?>> _extensions;
    private final p0<CloseReason> closeReason;
    private final r<CloseReason> closeReasonRef;
    private volatile /* synthetic */ int closed;
    private final t context;
    private final j coroutineContext;
    private final ma.j<Frame> filtered;
    private final ma.j<Frame> outgoingToBeProcessed;
    private long pingIntervalMillis;
    volatile /* synthetic */ Object pinger = null;
    private final WebSocketSession raw;
    private volatile /* synthetic */ int started;
    private long timeoutMillis;
    private static final Frame.Pong EmptyPong = new Frame.Pong(new byte[0], NonDisposableHandle.INSTANCE);
    static final /* synthetic */ AtomicReferenceFieldUpdater pinger$FU = AtomicReferenceFieldUpdater.newUpdater(DefaultWebSocketSessionImpl.class, Object.class, "pinger");
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(DefaultWebSocketSessionImpl.class, "closed");
    private static final /* synthetic */ AtomicIntegerFieldUpdater started$FU = AtomicIntegerFieldUpdater.newUpdater(DefaultWebSocketSessionImpl.class, "started");

    /* JADX INFO: renamed from: io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.DefaultWebSocketSessionImpl", f = "DefaultWebSocketSession.kt", l = {327}, m = "checkMaxFrameSize")
    public static final class AnonymousClass1 extends c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultWebSocketSessionImpl.this.checkMaxFrameSize(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.DefaultWebSocketSessionImpl$outgoingProcessorLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.DefaultWebSocketSessionImpl", f = "DefaultWebSocketSession.kt", l = {252, 256, 266}, m = "outgoingProcessorLoop")
    public static final class C20541 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20541(d<? super C20541> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultWebSocketSessionImpl.this.outgoingProcessorLoop(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.DefaultWebSocketSessionImpl$runIncomingProcessor$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.websocket.DefaultWebSocketSessionImpl$runIncomingProcessor$1", f = "DefaultWebSocketSession.kt", l = {352, TsExtractor.TS_STREAM_TYPE_AC4, 226, 178, 179, 181, 196, 211, 226, 226, 226, 226}, m = "invokeSuspend")
    public static final class C20551 extends f7.j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ x<Frame.Ping> $ponger;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C20551(x<? super Frame.Ping> xVar, d<? super C20551> dVar) {
            super(2, dVar);
            this.$ponger = xVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C20551 c20551 = DefaultWebSocketSessionImpl.this.new C20551(this.$ponger, dVar);
            c20551.L$0 = obj;
            return c20551;
        }

        /* JADX WARN: Removed duplicated region for block: B:104:0x03a6  */
        /* JADX WARN: Removed duplicated region for block: B:146:0x04c9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0170  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0183 A[Catch: all -> 0x004f, TryCatch #6 {all -> 0x004f, blocks: (B:11:0x0049, B:40:0x017b, B:42:0x0183, B:44:0x01ad, B:46:0x01b7, B:48:0x01c5, B:49:0x01c9, B:52:0x01e8, B:65:0x0231, B:67:0x0235, B:69:0x023b, B:74:0x0260, B:76:0x0264, B:79:0x027f, B:16:0x0073, B:28:0x0101, B:31:0x0126), top: B:156:0x000c }] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x01fa  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x020a  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x02b2 A[Catch: all -> 0x00a1, TryCatch #0 {all -> 0x00a1, blocks: (B:83:0x02ac, B:85:0x02b2, B:87:0x02b6, B:88:0x02b8, B:90:0x02bc, B:91:0x02c5, B:92:0x02e5, B:94:0x02e9, B:98:0x0319, B:19:0x009c), top: B:148:0x009c }] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x02e5 A[Catch: all -> 0x00a1, TryCatch #0 {all -> 0x00a1, blocks: (B:83:0x02ac, B:85:0x02b2, B:87:0x02b6, B:88:0x02b8, B:90:0x02bc, B:91:0x02c5, B:92:0x02e5, B:94:0x02e9, B:98:0x0319, B:19:0x009c), top: B:148:0x009c }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:101:0x038f -> B:102:0x0397). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r31) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 1258
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.C20551.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C20551) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.DefaultWebSocketSessionImpl$runOutgoingProcessor$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.websocket.DefaultWebSocketSessionImpl$runOutgoingProcessor$1", f = "DefaultWebSocketSession.kt", l = {236, 247, 247, 247, PsExtractor.VIDEO_STREAM_MASK, 247, 247, 244, 247, 247}, m = "invokeSuspend")
    public static final class C20561 extends f7.j implements p<k0, d<? super t0>, Object> {
        Object L$0;
        int label;

        public C20561(d<? super C20561> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return DefaultWebSocketSessionImpl.this.new C20561(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x0062, code lost:
        
            if (io.ktor.websocket.WebSocketSessionKt.close$default(r11, null, r10, 1, null) != r3) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x009f, code lost:
        
            if (io.ktor.websocket.WebSocketSessionKt.close$default(r11, null, r10, 1, null) == r3) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00b8, code lost:
        
            if (io.ktor.websocket.WebSocketSessionKt.close$default(r11, null, r10, 1, null) != r3) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00eb, code lost:
        
            if (io.ktor.websocket.WebSocketSessionKt.close$default(r11, null, r10, 1, null) == r3) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0127, code lost:
        
            if (io.ktor.websocket.WebSocketSessionKt.close$default(r11, null, r10, 1, null) != r3) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0140, code lost:
        
            if (io.ktor.websocket.WebSocketSessionKt.close$default(r11, null, r10, 1, null) != r3) goto L59;
         */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0142 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 352
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.C20561.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C20561) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.DefaultWebSocketSessionImpl$sendCloseSequence$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.DefaultWebSocketSessionImpl", f = "DefaultWebSocketSession.kt", l = {281}, m = "sendCloseSequence")
    public static final class C20571 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C20571(d<? super C20571> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultWebSocketSessionImpl.this.sendCloseSequence(null, null, this);
        }
    }

    public DefaultWebSocketSessionImpl(WebSocketSession webSocketSession, long j10, long j11) {
        this.raw = webSocketSession;
        s sVarB = m0.b();
        this.closeReasonRef = sVarB;
        this.filtered = f0.a(8, 0, 6);
        this.outgoingToBeProcessed = f0.a(UtilsKt.getOUTGOING_CHANNEL_CAPACITY(), 0, 6);
        this.closed = 0;
        x1 x1Var = new x1((v1) webSocketSession.getCoroutineContext().get(u1.f19642i));
        this.context = x1Var;
        this._extensions = new ArrayList();
        this.started = 0;
        this.coroutineContext = webSocketSession.getCoroutineContext().plus(x1Var).plus(new j0("ws-default"));
        this.pingIntervalMillis = j10;
        this.timeoutMillis = j11;
        this.closeReason = sVarB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkMaxFrameSize(io.ktor.utils.io.core.BytePacketBuilder r8, io.ktor.websocket.Frame r9, d7.d<? super x6.t0> r10) throws io.ktor.websocket.FrameTooBigException {
        /*
            r7 = this;
            boolean r0 = r10 instanceof io.ktor.websocket.DefaultWebSocketSessionImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1 r0 = (io.ktor.websocket.DefaultWebSocketSessionImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1 r0 = new io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 == r2) goto L29
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L29:
            int r8 = r0.I$0
            k2.c.G(r10)
            goto L76
        L2f:
            k2.c.G(r10)
            byte[] r9 = r9.getData()
            int r9 = r9.length
            if (r8 == 0) goto L3e
            int r10 = r8.getSize()
            goto L3f
        L3e:
            r10 = 0
        L3f:
            int r9 = r9 + r10
            long r3 = (long) r9
            long r5 = r7.getMaxFrameSize()
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 <= 0) goto L7d
            if (r8 == 0) goto L4e
            r8.release()
        L4e:
            io.ktor.websocket.CloseReason r8 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r10 = io.ktor.websocket.CloseReason.Codes.TOO_BIG
            java.lang.String r1 = "Frame is too big: "
            java.lang.String r3 = ". Max size is "
            java.lang.StringBuilder r1 = a0.c.s(r9, r1, r3)
            long r3 = r7.getMaxFrameSize()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r8.<init>(r10, r1)
            r0.I$0 = r9
            r0.label = r2
            java.lang.Object r8 = io.ktor.websocket.WebSocketSessionKt.close(r7, r8, r0)
            e7.a r10 = e7.a.f15033i
            if (r8 != r10) goto L75
            return r10
        L75:
            r8 = r9
        L76:
            io.ktor.websocket.FrameTooBigException r9 = new io.ktor.websocket.FrameTooBigException
            long r0 = (long) r8
            r9.<init>(r0)
            throw r9
        L7d:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.checkMaxFrameSize(io.ktor.utils.io.core.BytePacketBuilder, io.ktor.websocket.Frame, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object goingAway$default(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, String str, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = "Server is going down";
        }
        return defaultWebSocketSessionImpl.goingAway(str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00af, code lost:
    
        if (sendCloseSequence$default(r6, r7, null, r9, 2, null) == r5) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00d3 -> B:14:0x0034). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object outgoingProcessorLoop(d7.d<? super x6.t0> r13) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.outgoingProcessorLoop(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Frame processIncomingExtensions(Frame frame) {
        Iterator<T> it = getExtensions().iterator();
        while (it.hasNext()) {
            frame = ((WebSocketExtension) it.next()).processIncomingFrame(frame);
        }
        return frame;
    }

    private final Frame processOutgoingExtensions(Frame frame) {
        Iterator<T> it = getExtensions().iterator();
        while (it.hasNext()) {
            frame = ((WebSocketExtension) it.next()).processOutgoingFrame(frame);
        }
        return frame;
    }

    private final v1 runIncomingProcessor(x<? super Frame.Ping> ponger) {
        return m0.p(this, DefaultWebSocketSessionKt.IncomingProcessorCoroutineName.plus(x0.f19654c), 0, new C20551(ponger, null), 2);
    }

    private final void runOrCancelPinger() {
        DefaultWebSocketSessionImpl defaultWebSocketSessionImpl;
        x<Frame.Pong> xVarPinger;
        long pingIntervalMillis = getPingIntervalMillis();
        if (this.closed == 0 && pingIntervalMillis > 0) {
            defaultWebSocketSessionImpl = this;
            xVarPinger = PingPongKt.pinger(defaultWebSocketSessionImpl, this.raw.getOutgoing(), pingIntervalMillis, getTimeoutMillis(), new DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1(this, null));
        } else {
            defaultWebSocketSessionImpl = this;
            xVarPinger = null;
        }
        x xVar = (x) pinger$FU.getAndSet(this, xVarPinger);
        if (xVar != null) {
            xVar.close(null);
        }
        if (xVarPinger != null) {
            xVarPinger.mo6685trySendJP2dKIU(EmptyPong);
        }
        if (defaultWebSocketSessionImpl.closed == 0 || xVarPinger == null) {
            return;
        }
        runOrCancelPinger();
    }

    private final v1 runOutgoingProcessor() {
        return m0.o(4, DefaultWebSocketSessionKt.OutgoingProcessorCoroutineName.plus(x0.f19654c), this, new C20561(null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sendCloseSequence(io.ktor.websocket.CloseReason r6, java.lang.Throwable r7, d7.d<? super x6.t0> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.sendCloseSequence(io.ktor.websocket.CloseReason, java.lang.Throwable, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object sendCloseSequence$default(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, CloseReason closeReason, Throwable th, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            th = null;
        }
        return defaultWebSocketSessionImpl.sendCloseSequence(closeReason, th, dVar);
    }

    private final boolean tryClose() {
        return closed$FU.compareAndSet(this, 0, 1);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object flush(d<? super t0> dVar) {
        Object objFlush = this.raw.flush(dVar);
        return objFlush == a.f15033i ? objFlush : t0.f22605a;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public p0<CloseReason> getCloseReason() {
        return this.closeReason;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession, io.ktor.websocket.WebSocketSession, ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public List<WebSocketExtension<?>> getExtensions() {
        return this._extensions;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public w<Frame> getIncoming() {
        return this.filtered;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public boolean getMasking() {
        return this.raw.getMasking();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public long getMaxFrameSize() {
        return this.raw.getMaxFrameSize();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public x<Frame> getOutgoing() {
        return this.outgoingToBeProcessed;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public long getPingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public final Object goingAway(String str, d<? super t0> dVar) {
        Object objSendCloseSequence$default = sendCloseSequence$default(this, new CloseReason(CloseReason.Codes.GOING_AWAY, str), null, dVar, 2, null);
        return objSendCloseSequence$default == a.f15033i ? objSendCloseSequence$default : t0.f22605a;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object send(Frame frame, d<? super t0> dVar) {
        return DefaultWebSocketSession.DefaultImpls.send(this, frame, dVar);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMasking(boolean z) {
        this.raw.setMasking(z);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMaxFrameSize(long j10) {
        this.raw.setMaxFrameSize(j10);
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void setPingIntervalMillis(long j10) {
        this.pingIntervalMillis = j10;
        runOrCancelPinger();
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void setTimeoutMillis(long j10) {
        this.timeoutMillis = j10;
        runOrCancelPinger();
    }

    @Override // io.ktor.websocket.DefaultWebSocketSession
    public void start(List<? extends WebSocketExtension<?>> negotiatedExtensions) {
        if (!started$FU.compareAndSet(this, 0, 1)) {
            throw new IllegalStateException(("WebSocket session " + this + " is already started.").toString());
        }
        DefaultWebSocketSessionKt.getLOGGER().h("Starting default WebSocketSession(" + this + ") with negotiated extensions: " + kotlin.collections.x.u0(negotiatedExtensions, null, null, null, null, 63));
        this._extensions.addAll(negotiatedExtensions);
        runOrCancelPinger();
        runIncomingProcessor(PingPongKt.ponger(this, getOutgoing()));
        runOutgoingProcessor();
    }

    @Override // io.ktor.websocket.WebSocketSession
    @x6.e
    public void terminate() {
        this.context.cancel((CancellationException) null);
        l0.b(this.raw, null);
    }
}
