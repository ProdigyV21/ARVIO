package io.ktor.websocket;

import d7.d;
import d7.j;
import f7.c;
import f7.e;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import ka.j0;
import ka.k0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import m2.f0;
import ma.w;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u00013B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u001a\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b+\u0010,\u0012\u0004\b-\u0010.R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020'0/8F¢\u0006\u0006\u001a\u0004\b0\u00101\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"Lio/ktor/websocket/WebSocketReader;", "Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "byteChannel", "Ld7/j;", "coroutineContext", "", "maxFrameSize", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "pool", "<init>", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/j;JLio/ktor/utils/io/pool/ObjectPool;)V", "buffer", "Lx6/t0;", "readLoop", "(Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "parseLoop", "handleFrameIfProduced", "(Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "J", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "Lio/ktor/websocket/WebSocketReader$State;", "state", "Lio/ktor/websocket/WebSocketReader$State;", "Lio/ktor/websocket/FrameParser;", "frameParser", "Lio/ktor/websocket/FrameParser;", "Lio/ktor/websocket/SimpleFrameCollector;", "collector", "Lio/ktor/websocket/SimpleFrameCollector;", "Lma/j;", "Lio/ktor/websocket/Frame;", "queue", "Lma/j;", "Lka/v1;", "readerJob", "Lka/v1;", "getReaderJob$annotations", "()V", "Lma/w;", "getIncoming", "()Lma/w;", "incoming", "State", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebSocketReader implements k0 {
    private final ByteReadChannel byteChannel;
    private final SimpleFrameCollector collector;
    private final j coroutineContext;
    private final FrameParser frameParser;
    private long maxFrameSize;
    private final ma.j<Frame> queue;
    private final v1 readerJob;
    private State state;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/websocket/WebSocketReader$State;", "", "(Ljava/lang/String;I)V", "HEADER", "BODY", "CLOSED", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum State {
        HEADER,
        BODY,
        CLOSED
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            try {
                iArr[State.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[State.BODY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[State.CLOSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.WebSocketReader", f = "WebSocketReader.kt", l = {115}, m = "handleFrameIfProduced")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebSocketReader.this.handleFrameIfProduced(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketReader$parseLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.WebSocketReader", f = "WebSocketReader.kt", l = {92, 100}, m = "parseLoop")
    public static final class C20601 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20601(d<? super C20601> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebSocketReader.this.parseLoop(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketReader$readLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.WebSocketReader", f = "WebSocketReader.kt", l = {68, 74}, m = "readLoop")
    public static final class C20611 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20611(d<? super C20611> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebSocketReader.this.readLoop(null, this);
        }
    }

    public WebSocketReader(ByteReadChannel byteReadChannel, j jVar, long j10, ObjectPool<ByteBuffer> objectPool) {
        this.byteChannel = byteReadChannel;
        this.coroutineContext = jVar;
        this.maxFrameSize = j10;
        this.state = State.HEADER;
        this.frameParser = new FrameParser();
        this.collector = new SimpleFrameCollector();
        this.queue = f0.a(8, 0, 6);
        this.readerJob = m0.o(3, new j0("ws-reader"), this, new WebSocketReader$readerJob$1(objectPool, this, null));
    }

    private static /* synthetic */ void getReaderJob$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object handleFrameIfProduced(d7.d<? super x6.t0> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof io.ktor.websocket.WebSocketReader.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1 r0 = (io.ktor.websocket.WebSocketReader.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1 r0 = new io.ktor.websocket.WebSocketReader$handleFrameIfProduced$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r0 = r0.L$0
            io.ktor.websocket.WebSocketReader r0 = (io.ktor.websocket.WebSocketReader) r0
            k2.c.G(r11)
            goto L87
        L29:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L31:
            k2.c.G(r11)
            io.ktor.websocket.SimpleFrameCollector r11 = r10.collector
            boolean r11 = r11.getHasRemaining()
            if (r11 != 0) goto L8c
            io.ktor.websocket.FrameParser r11 = r10.frameParser
            io.ktor.websocket.FrameType r11 = r11.getFrameType()
            io.ktor.websocket.FrameType r1 = io.ktor.websocket.FrameType.CLOSE
            if (r11 != r1) goto L49
            io.ktor.websocket.WebSocketReader$State r11 = io.ktor.websocket.WebSocketReader.State.CLOSED
            goto L4b
        L49:
            io.ktor.websocket.WebSocketReader$State r11 = io.ktor.websocket.WebSocketReader.State.HEADER
        L4b:
            r10.state = r11
            io.ktor.websocket.FrameParser r11 = r10.frameParser
            io.ktor.websocket.Frame$Companion r3 = io.ktor.websocket.Frame.INSTANCE
            boolean r4 = r11.getFin()
            io.ktor.websocket.FrameType r5 = r11.getFrameType()
            io.ktor.websocket.SimpleFrameCollector r1 = r10.collector
            java.lang.Integer r6 = r11.getMaskKey()
            java.nio.ByteBuffer r1 = r1.take(r6)
            byte[] r6 = io.ktor.util.NIOKt.moveToByteArray(r1)
            boolean r7 = r11.getRsv1()
            boolean r8 = r11.getRsv2()
            boolean r9 = r11.getRsv3()
            io.ktor.websocket.Frame r11 = r3.byType(r4, r5, r6, r7, r8, r9)
            ma.j<io.ktor.websocket.Frame> r1 = r10.queue
            r0.L$0 = r10
            r0.label = r2
            java.lang.Object r11 = r1.send(r11, r0)
            e7.a r0 = e7.a.f15033i
            if (r11 != r0) goto L86
            return r0
        L86:
            r0 = r10
        L87:
            io.ktor.websocket.FrameParser r11 = r0.frameParser
            r11.bodyComplete()
        L8c:
            x6.t0 r11 = x6.t0.f22605a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketReader.handleFrameIfProduced(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b9, code lost:
    
        throw new io.ktor.websocket.FrameTooBigException(r1.frameParser.getLength());
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ba, code lost:
    
        return r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object parseLoop(java.nio.ByteBuffer r11, d7.d<? super x6.t0> r12) throws io.ktor.websocket.FrameTooBigException {
        /*
            r10 = this;
            boolean r0 = r12 instanceof io.ktor.websocket.WebSocketReader.C20601
            if (r0 == 0) goto L13
            r0 = r12
            io.ktor.websocket.WebSocketReader$parseLoop$1 r0 = (io.ktor.websocket.WebSocketReader.C20601) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.WebSocketReader$parseLoop$1 r0 = new io.ktor.websocket.WebSocketReader$parseLoop$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L38
            if (r1 == r3) goto L24
            if (r1 != r2) goto L30
        L24:
            java.lang.Object r11 = r0.L$1
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r1 = r0.L$0
            io.ktor.websocket.WebSocketReader r1 = (io.ktor.websocket.WebSocketReader) r1
            k2.c.G(r12)
            goto L3c
        L30:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L38:
            k2.c.G(r12)
            r1 = r10
        L3c:
            boolean r12 = r11.hasRemaining()
            x6.t0 r4 = x6.t0.f22605a
            if (r12 == 0) goto Lba
            io.ktor.websocket.WebSocketReader$State r12 = r1.state
            int[] r5 = io.ktor.websocket.WebSocketReader.WhenMappings.$EnumSwitchMapping$0
            int r12 = r12.ordinal()
            r12 = r5[r12]
            e7.a r5 = e7.a.f15033i
            if (r12 == r3) goto L6b
            if (r12 == r2) goto L59
            r5 = 3
            if (r12 == r5) goto L58
            goto L3c
        L58:
            return r4
        L59:
            io.ktor.websocket.SimpleFrameCollector r12 = r1.collector
            r12.handle(r11)
            r0.L$0 = r1
            r0.L$1 = r11
            r0.label = r2
            java.lang.Object r12 = r1.handleFrameIfProduced(r0)
            if (r12 != r5) goto L3c
            goto Lad
        L6b:
            io.ktor.websocket.FrameParser r12 = r1.frameParser
            r12.frame(r11)
            io.ktor.websocket.FrameParser r12 = r1.frameParser
            boolean r12 = r12.getBodyReady()
            if (r12 == 0) goto Lba
            io.ktor.websocket.WebSocketReader$State r12 = io.ktor.websocket.WebSocketReader.State.BODY
            r1.state = r12
            io.ktor.websocket.FrameParser r12 = r1.frameParser
            long r6 = r12.getLength()
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r12 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r12 > 0) goto Lae
            io.ktor.websocket.FrameParser r12 = r1.frameParser
            long r6 = r12.getLength()
            long r8 = r1.maxFrameSize
            int r12 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r12 > 0) goto Lae
            io.ktor.websocket.SimpleFrameCollector r12 = r1.collector
            io.ktor.websocket.FrameParser r4 = r1.frameParser
            long r6 = r4.getLength()
            int r4 = (int) r6
            r12.start(r4, r11)
            r0.L$0 = r1
            r0.L$1 = r11
            r0.label = r3
            java.lang.Object r12 = r1.handleFrameIfProduced(r0)
            if (r12 != r5) goto L3c
        Lad:
            return r5
        Lae:
            io.ktor.websocket.FrameTooBigException r11 = new io.ktor.websocket.FrameTooBigException
            io.ktor.websocket.FrameParser r12 = r1.frameParser
            long r0 = r12.getLength()
            r11.<init>(r0)
            throw r11
        Lba:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketReader.parseLoop(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0081, code lost:
    
        if (r1.parseLoop(r8, r0) == r4) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0081 -> B:13:0x0031). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readLoop(java.nio.ByteBuffer r8, d7.d<? super x6.t0> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.websocket.WebSocketReader.C20611
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.websocket.WebSocketReader$readLoop$1 r0 = (io.ktor.websocket.WebSocketReader.C20611) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.WebSocketReader$readLoop$1 r0 = new io.ktor.websocket.WebSocketReader$readLoop$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L47
            if (r1 == r3) goto L3b
            if (r1 != r2) goto L33
            java.lang.Object r8 = r0.L$1
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.websocket.WebSocketReader r1 = (io.ktor.websocket.WebSocketReader) r1
            k2.c.G(r9)
        L31:
            r9 = r1
            goto L84
        L33:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3b:
            java.lang.Object r8 = r0.L$1
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.websocket.WebSocketReader r1 = (io.ktor.websocket.WebSocketReader) r1
            k2.c.G(r9)
            goto L66
        L47:
            k2.c.G(r9)
            r8.clear()
            r9 = r7
        L4e:
            io.ktor.websocket.WebSocketReader$State r1 = r9.state
            io.ktor.websocket.WebSocketReader$State r5 = io.ktor.websocket.WebSocketReader.State.CLOSED
            if (r1 == r5) goto L88
            io.ktor.utils.io.ByteReadChannel r1 = r9.byteChannel
            r0.L$0 = r9
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r1 = r1.readAvailable(r8, r0)
            if (r1 != r4) goto L63
            goto L83
        L63:
            r6 = r1
            r1 = r9
            r9 = r6
        L66:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            r5 = -1
            if (r9 != r5) goto L74
            io.ktor.websocket.WebSocketReader$State r8 = io.ktor.websocket.WebSocketReader.State.CLOSED
            r1.state = r8
            goto L88
        L74:
            r8.flip()
            r0.L$0 = r1
            r0.L$1 = r8
            r0.label = r2
            java.lang.Object r9 = r1.parseLoop(r8, r0)
            if (r9 != r4) goto L31
        L83:
            return r4
        L84:
            r8.compact()
            goto L4e
        L88:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketReader.readLoop(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    public final w<Frame> getIncoming() {
        return this.queue;
    }

    public final long getMaxFrameSize() {
        return this.maxFrameSize;
    }

    public final void setMaxFrameSize(long j10) {
        this.maxFrameSize = j10;
    }

    public /* synthetic */ WebSocketReader(ByteReadChannel byteReadChannel, j jVar, long j10, ObjectPool objectPool, int i10, h hVar) {
        this(byteReadChannel, jVar, j10, (i10 & 8) != 0 ? ByteBufferPoolKt.getKtorDefaultPool() : objectPool);
    }
}
