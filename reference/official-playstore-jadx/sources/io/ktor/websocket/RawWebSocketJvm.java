package io.ktor.websocket;

import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.ts.TsExtractor;
import d7.j;
import f7.e;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.pool.ObjectPool;
import io.ktor.websocket.WebSocketSession;
import java.nio.ByteBuffer;
import java.util.List;
import ka.j0;
import ka.k0;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.v;
import kotlin.reflect.m;
import m2.f0;
import ma.w;
import ma.x;
import r7.p;
import u7.a;
import u7.d;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u0011H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0011H\u0017¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR+\u0010\u0007\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00068V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R+\u0010\t\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\"\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010-\u001a\u00020,8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u001a\u00102\u001a\u0002018\u0000X\u0080\u0004¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020\u001a068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001a0:8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<R\u001e\u0010B\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030?0>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010A\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006C"}, d2 = {"Lio/ktor/websocket/RawWebSocketJvm;", "Lio/ktor/websocket/WebSocketSession;", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "", "maxFrameSize", "", "masking", "Ld7/j;", "coroutineContext", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "<init>", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JZLd7/j;Lio/ktor/utils/io/pool/ObjectPool;)V", "Lx6/t0;", "flush", "(Ld7/d;)Ljava/lang/Object;", "terminate", "()V", "Lka/t;", "socketJob", "Lka/t;", "Lma/j;", "Lio/ktor/websocket/Frame;", "filtered", "Lma/j;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "<set-?>", "maxFrameSize$delegate", "Lu7/d;", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "masking$delegate", "getMasking", "()Z", "setMasking", "(Z)V", "Lio/ktor/websocket/WebSocketWriter;", "writer", "Lio/ktor/websocket/WebSocketWriter;", "getWriter$ktor_websockets", "()Lio/ktor/websocket/WebSocketWriter;", "Lio/ktor/websocket/WebSocketReader;", "reader", "Lio/ktor/websocket/WebSocketReader;", "getReader$ktor_websockets", "()Lio/ktor/websocket/WebSocketReader;", "Lma/w;", "getIncoming", "()Lma/w;", "incoming", "Lma/x;", "getOutgoing", "()Lma/x;", "outgoing", "", "Lio/ktor/websocket/WebSocketExtension;", "getExtensions", "()Ljava/util/List;", "extensions", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RawWebSocketJvm implements WebSocketSession {
    static final /* synthetic */ m<Object>[] $$delegatedProperties;
    private final j coroutineContext;
    private final ma.j<Frame> filtered;

    /* JADX INFO: renamed from: masking$delegate, reason: from kotlin metadata */
    private final d masking;

    /* JADX INFO: renamed from: maxFrameSize$delegate, reason: from kotlin metadata */
    private final d maxFrameSize;
    private final WebSocketReader reader;
    private final t socketJob;
    private final WebSocketWriter writer;

    /* JADX INFO: renamed from: io.ktor.websocket.RawWebSocketJvm$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.websocket.RawWebSocketJvm$1", f = "RawWebSocketJvm.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32, 68, TsExtractor.TS_SYNC_BYTE, 74}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        Object L$0;
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return RawWebSocketJvm.this.new AnonymousClass1(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:41:0x0089, code lost:
        
            if (r7.send(r10, r9) == r6) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00f7, code lost:
        
            if (r10.send(r2, r9) != r6) goto L53;
         */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0075 A[Catch: all -> 0x0039, CancellationException -> 0x003b, ProtocolViolationException -> 0x003d, FrameTooBigException -> 0x0041, TRY_LEAVE, TryCatch #5 {ProtocolViolationException -> 0x003d, CancellationException -> 0x003b, blocks: (B:19:0x0034, B:34:0x005e, B:38:0x006d, B:40:0x0075, B:30:0x0049, B:33:0x0050), top: B:59:0x0009, outer: #4 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0089 -> B:20:0x0037). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 273
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketJvm.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    static {
        v vVar = new v(RawWebSocketJvm.class, "maxFrameSize", "getMaxFrameSize()J", 0);
        m0 m0Var = l0.f19747a;
        $$delegatedProperties = new m[]{m0Var.e(vVar), androidx.compose.material3.d.v(RawWebSocketJvm.class, "masking", "getMasking()Z", 0, m0Var)};
    }

    public RawWebSocketJvm(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, boolean z, j jVar, ObjectPool<ByteBuffer> objectPool) {
        x1 x1Var = new x1((v1) jVar.get(u1.f19642i));
        this.socketJob = x1Var;
        this.filtered = f0.a(0, 0, 6);
        this.coroutineContext = jVar.plus(x1Var).plus(new j0("raw-ws"));
        final Long lValueOf = Long.valueOf(j10);
        this.maxFrameSize = new a(lValueOf) { // from class: io.ktor.websocket.RawWebSocketJvm$special$$inlined$observable$1
            @Override // u7.a
            public void afterChange(m<?> property, Long oldValue, Long newValue) {
                long jLongValue = newValue.longValue();
                oldValue.longValue();
                this.getReader().setMaxFrameSize(jLongValue);
            }
        };
        final Boolean boolValueOf = Boolean.valueOf(z);
        this.masking = new a(boolValueOf) { // from class: io.ktor.websocket.RawWebSocketJvm$special$$inlined$observable$2
            @Override // u7.a
            public void afterChange(m<?> property, Boolean oldValue, Boolean newValue) {
                boolean zBooleanValue = newValue.booleanValue();
                oldValue.getClass();
                this.getWriter().setMasking(zBooleanValue);
            }
        };
        this.writer = new WebSocketWriter(byteWriteChannel, getCoroutineContext(), z, objectPool);
        this.reader = new WebSocketReader(byteReadChannel, getCoroutineContext(), j10, objectPool);
        ka.m0.p(this, null, 0, new AnonymousClass1(null), 3);
        x1Var.complete();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object flush(d7.d<? super t0> dVar) {
        Object objFlush = this.writer.flush(dVar);
        return objFlush == e7.a.f15033i ? objFlush : t0.f22605a;
    }

    @Override // io.ktor.websocket.WebSocketSession, ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public List<WebSocketExtension<?>> getExtensions() {
        return z.f19728i;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public w<Frame> getIncoming() {
        return this.filtered;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public boolean getMasking() {
        return ((Boolean) this.masking.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public long getMaxFrameSize() {
        return ((Number) this.maxFrameSize.getValue(this, $$delegatedProperties[0])).longValue();
    }

    @Override // io.ktor.websocket.WebSocketSession
    public x<Frame> getOutgoing() {
        return this.writer.getOutgoing();
    }

    /* JADX INFO: renamed from: getReader$ktor_websockets, reason: from getter */
    public final WebSocketReader getReader() {
        return this.reader;
    }

    /* JADX INFO: renamed from: getWriter$ktor_websockets, reason: from getter */
    public final WebSocketWriter getWriter() {
        return this.writer;
    }

    @Override // io.ktor.websocket.WebSocketSession
    public Object send(Frame frame, d7.d<? super t0> dVar) {
        return WebSocketSession.DefaultImpls.send(this, frame, dVar);
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMasking(boolean z) {
        this.masking.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    @Override // io.ktor.websocket.WebSocketSession
    public void setMaxFrameSize(long j10) {
        this.maxFrameSize.setValue(this, $$delegatedProperties[0], Long.valueOf(j10));
    }

    @Override // io.ktor.websocket.WebSocketSession
    @x6.e
    public void terminate() {
        getOutgoing().close(null);
        this.socketJob.complete();
    }

    public /* synthetic */ RawWebSocketJvm(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, boolean z, j jVar, ObjectPool objectPool, int i10, h hVar) {
        this(byteReadChannel, byteWriteChannel, (i10 & 4) != 0 ? 2147483647L : j10, (i10 & 8) != 0 ? false : z, jVar, (i10 & 32) != 0 ? ByteBufferPoolKt.getKtorDefaultPool() : objectPool);
    }
}
