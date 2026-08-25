package io.ktor.websocket;

import d7.d;
import d7.j;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import ka.j0;
import ka.k0;
import ka.m0;
import ka.t;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import m2.f0;
import ma.x;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u00018B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u001c\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006¢\u0006\f\n\u0004\b\n\u0010&\u001a\u0004\b'\u0010(R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001a\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\f\n\u0004\b1\u00102\u0012\u0004\b3\u0010\u0012R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u0013048F¢\u0006\u0006\u001a\u0004\b5\u00106\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, d2 = {"Lio/ktor/websocket/WebSocketWriter;", "Lka/k0;", "Lio/ktor/utils/io/ByteWriteChannel;", "writeChannel", "Ld7/j;", "coroutineContext", "", "masking", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "<init>", "(Lio/ktor/utils/io/ByteWriteChannel;Ld7/j;ZLio/ktor/utils/io/pool/ObjectPool;)V", "buffer", "Lx6/t0;", "writeLoop", "(Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "drainQueueAndDiscard", "()V", "Lio/ktor/websocket/Frame;", "firstMsg", "drainQueueAndSerialize", "(Lio/ktor/websocket/Frame;Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "frame", "send", "(Lio/ktor/websocket/Frame;Ld7/d;)Ljava/lang/Object;", "flush", "(Ld7/d;)Ljava/lang/Object;", "close", "Lio/ktor/utils/io/ByteWriteChannel;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Z", "getMasking", "()Z", "setMasking", "(Z)V", "Lio/ktor/utils/io/pool/ObjectPool;", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "Lma/j;", "", "queue", "Lma/j;", "Lio/ktor/websocket/Serializer;", "serializer", "Lio/ktor/websocket/Serializer;", "Lka/v1;", "writeLoopJob", "Lka/v1;", "getWriteLoopJob$annotations", "Lma/x;", "getOutgoing", "()Lma/x;", "outgoing", "FlushRequest", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebSocketWriter implements k0 {
    private final j coroutineContext;
    private boolean masking;
    private final ObjectPool<ByteBuffer> pool;
    private final ma.j<Object> queue;
    private final Serializer serializer;
    private final ByteWriteChannel writeChannel;
    private final v1 writeLoopJob;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\n\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/websocket/WebSocketWriter$FlushRequest;", "", "Lka/v1;", "parent", "<init>", "(Lka/v1;)V", "", "complete", "()Z", "Lx6/t0;", "await", "(Ld7/d;)Ljava/lang/Object;", "Lka/t;", "done", "Lka/t;", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
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

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketWriter$drainQueueAndSerialize$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.WebSocketWriter", f = "WebSocketWriter.kt", l = {121}, m = "drainQueueAndSerialize")
    public static final class AnonymousClass1 extends c {
        int I$0;
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
            return WebSocketWriter.this.drainQueueAndSerialize(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketWriter$flush$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.WebSocketWriter", f = "WebSocketWriter.kt", l = {155, 158, 163}, m = "flush")
    public static final class C20621 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C20621(d<? super C20621> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebSocketWriter.this.flush(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketWriter$writeLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.WebSocketWriter", f = "WebSocketWriter.kt", l = {46, 48}, m = "writeLoop")
    public static final class C20631 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C20631(d<? super C20631> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebSocketWriter.this.writeLoop(null, this);
        }
    }

    public WebSocketWriter(ByteWriteChannel byteWriteChannel, j jVar, boolean z, ObjectPool<ByteBuffer> objectPool) {
        this.writeChannel = byteWriteChannel;
        this.coroutineContext = jVar;
        this.masking = z;
        this.pool = objectPool;
        this.queue = f0.a(8, 0, 6);
        this.serializer = new Serializer();
        this.writeLoopJob = m0.o(3, new j0("ws-writer"), this, new WebSocketWriter$writeLoopJob$1(this, null));
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
    
        throw new java.lang.IllegalArgumentException("unknown message " + r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void drainQueueAndDiscard() {
        /*
            r4 = this;
            ma.j<java.lang.Object> r0 = r4.queue
            r1 = 0
            r0.close(r1)
        L6:
            ma.j<java.lang.Object> r0 = r4.queue     // Catch: java.util.concurrent.CancellationException -> L4e
            java.lang.Object r0 = r0.b()     // Catch: java.util.concurrent.CancellationException -> L4e
            java.lang.Object r0 = ma.o.a(r0)     // Catch: java.util.concurrent.CancellationException -> L4e
            if (r0 != 0) goto L13
            goto L4e
        L13:
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Close     // Catch: java.util.concurrent.CancellationException -> L4e
            if (r1 != 0) goto L6
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Ping     // Catch: java.util.concurrent.CancellationException -> L4e
            r2 = 1
            if (r1 == 0) goto L1e
            r1 = r2
            goto L20
        L1e:
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Pong     // Catch: java.util.concurrent.CancellationException -> L4e
        L20:
            if (r1 != 0) goto L6
            boolean r1 = r0 instanceof io.ktor.websocket.WebSocketWriter.FlushRequest     // Catch: java.util.concurrent.CancellationException -> L4e
            if (r1 == 0) goto L2c
            io.ktor.websocket.WebSocketWriter$FlushRequest r0 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r0     // Catch: java.util.concurrent.CancellationException -> L4e
            r0.complete()     // Catch: java.util.concurrent.CancellationException -> L4e
            goto L6
        L2c:
            boolean r1 = r0 instanceof io.ktor.websocket.Frame.Text     // Catch: java.util.concurrent.CancellationException -> L4e
            if (r1 == 0) goto L31
            goto L33
        L31:
            boolean r2 = r0 instanceof io.ktor.websocket.Frame.Binary     // Catch: java.util.concurrent.CancellationException -> L4e
        L33:
            if (r2 == 0) goto L36
            goto L6
        L36:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch: java.util.concurrent.CancellationException -> L4e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.util.concurrent.CancellationException -> L4e
            r2.<init>()     // Catch: java.util.concurrent.CancellationException -> L4e
            java.lang.String r3 = "unknown message "
            r2.append(r3)     // Catch: java.util.concurrent.CancellationException -> L4e
            r2.append(r0)     // Catch: java.util.concurrent.CancellationException -> L4e
            java.lang.String r0 = r2.toString()     // Catch: java.util.concurrent.CancellationException -> L4e
            r1.<init>(r0)     // Catch: java.util.concurrent.CancellationException -> L4e
            throw r1     // Catch: java.util.concurrent.CancellationException -> L4e
        L4e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.drainQueueAndDiscard():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:62:0x0113, B:63:0x0115], limit reached: 77 */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v2, types: [int] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00ee -> B:53:0x00f1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:16:0x0051
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object drainQueueAndSerialize(io.ktor.websocket.Frame r7, java.nio.ByteBuffer r8, d7.d<? super java.lang.Boolean> r9) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.drainQueueAndSerialize(io.ktor.websocket.Frame, java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    private static /* synthetic */ void getWriteLoopJob$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009e, code lost:
    
        if (r12 == r6) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0088 A[Catch: all -> 0x003e, ChannelWriteException -> 0x0041, TryCatch #4 {ChannelWriteException -> 0x0041, all -> 0x003e, blocks: (B:13:0x0035, B:39:0x00a1, B:28:0x006d, B:32:0x0080, B:34:0x0088, B:36:0x0090, B:43:0x00ad, B:45:0x00b1, B:46:0x00b7, B:47:0x00ce, B:22:0x0058), top: B:63:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x009e -> B:39:0x00a1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00b1 -> B:42:0x00aa). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeLoop(java.nio.ByteBuffer r11, d7.d<? super x6.t0> r12) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.writeLoop(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    @x6.e
    public final void close() {
        this.queue.close(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0073, code lost:
    
        if (r9.send(r1, r0) == r6) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009f, code lost:
    
        if (r4.await(r0) != r6) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int, io.ktor.websocket.WebSocketWriter$FlushRequest] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object flush(d7.d<? super x6.t0> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.ktor.websocket.WebSocketWriter.C20621
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.websocket.WebSocketWriter$flush$1 r0 = (io.ktor.websocket.WebSocketWriter.C20621) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.WebSocketWriter$flush$1 r0 = new io.ktor.websocket.WebSocketWriter$flush$1
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
            io.ktor.websocket.WebSocketWriter$FlushRequest r1 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r1
            k2.c.G(r9)
            goto L92
        L3f:
            java.lang.Object r1 = r0.L$2
            io.ktor.websocket.WebSocketWriter$FlushRequest r1 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r1
            java.lang.Object r4 = r0.L$1
            io.ktor.websocket.WebSocketWriter$FlushRequest r4 = (io.ktor.websocket.WebSocketWriter.FlushRequest) r4
            java.lang.Object r7 = r0.L$0
            io.ktor.websocket.WebSocketWriter r7 = (io.ktor.websocket.WebSocketWriter) r7
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L7d
            goto L93
        L4f:
            r9 = move-exception
            goto L79
        L51:
            k2.c.G(r9)
            io.ktor.websocket.WebSocketWriter$FlushRequest r1 = new io.ktor.websocket.WebSocketWriter$FlushRequest
            d7.j r9 = r8.getCoroutineContext()
            ka.u1 r7 = ka.u1.f19642i
            d7.j$a r9 = r9.get(r7)
            ka.v1 r9 = (ka.v1) r9
            r1.<init>(r9)
            ma.j<java.lang.Object> r9 = r8.queue     // Catch: java.lang.Throwable -> L4f kotlinx.coroutines.channels.ClosedSendChannelException -> L76
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
            ka.v1 r9 = r7.writeLoopJob
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
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketWriter.flush(d7.d):java.lang.Object");
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    public final boolean getMasking() {
        return this.masking;
    }

    public final x<Frame> getOutgoing() {
        return this.queue;
    }

    public final ObjectPool<ByteBuffer> getPool() {
        return this.pool;
    }

    public final Object send(Frame frame, d<? super t0> dVar) {
        Object objSend = this.queue.send(frame, dVar);
        return objSend == a.f15033i ? objSend : t0.f22605a;
    }

    public final void setMasking(boolean z) {
        this.masking = z;
    }

    public /* synthetic */ WebSocketWriter(ByteWriteChannel byteWriteChannel, j jVar, boolean z, ObjectPool objectPool, int i10, h hVar) {
        this(byteWriteChannel, jVar, (i10 & 4) != 0 ? false : z, (i10 & 8) != 0 ? ByteBufferPoolKt.getKtorDefaultPool() : objectPool);
    }
}
