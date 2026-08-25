package io.ktor.utils.io;

import androidx.media3.extractor.AacUtil;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import g8.b;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.ByteBuffersKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlinx.coroutines.channels.ClosedSendChannelException;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u00019B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\fJ\u0017\u0010\u000f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\fJ#\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0016\u0010\u0010J\u0017\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0017H\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\fJ\u001b\u0010\u001c\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\fJ\u001b\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\fJ+\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\u001fH\u0016¢\u0006\u0004\b\u001d\u0010!J\u001b\u0010\"\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\"\u0010\fJ.\u0010'\u001a\u00028\u0000\"\u0004\b\u0000\u0010#2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00028\u00000\u001f¢\u0006\u0002\b%H\u0017¢\u0006\u0004\b'\u0010(JB\u0010-\u001a\u00028\u0000\"\u0004\b\u0000\u0010#2'\u0010&\u001a#\b\u0001\u0012\u0004\u0012\u00020*\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000+\u0012\u0006\u0012\u0004\u0018\u00010,0)¢\u0006\u0002\b%H\u0097@ø\u0001\u0000¢\u0006\u0004\b-\u0010.J/\u00100\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\n2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\u001fH\u0096@ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u0013\u00102\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0004\b2\u00103J+\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\u001fH\u0016¢\u0006\u0004\b\u001b\u0010!J/\u00104\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\n2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\u001fH\u0096@ø\u0001\u0000¢\u0006\u0004\b4\u00101J'\u00105\u001a\u00020\r2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u001fH\u0096@ø\u0001\u0000¢\u0006\u0004\b5\u00106R\u0018\u00107\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006:"}, d2 = {"Lio/ktor/utils/io/ByteChannelSequentialJVM;", "Lio/ktor/utils/io/ByteChannelSequentialBase;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "initial", "", "autoFlush", "<init>", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Z)V", "Ljava/nio/ByteBuffer;", "src", "", "writeAvailableSuspend", "(Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "writeFullySuspend", "tryWriteAvailable", "(Ljava/nio/ByteBuffer;)I", "dst", "readAvailableSuspend", "rc0", "readFullySuspend", "(Ljava/nio/ByteBuffer;ILd7/d;)Ljava/lang/Object;", "tryReadAvailable", "Lka/v1;", "job", "attachJob", "(Lka/v1;)V", "writeAvailable", "writeFully", "readAvailable", "min", "Lkotlin/Function1;", "block", "(ILr7/l;)I", "readFully", "R", "Lio/ktor/utils/io/LookAheadSession;", "Lx6/n;", "visitor", "lookAhead", "(Lr7/l;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lio/ktor/utils/io/LookAheadSuspendSession;", "Ld7/d;", "", "lookAheadSuspend", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "consumer", "read", "(ILr7/l;Ld7/d;)Ljava/lang/Object;", "awaitContent", "(Ld7/d;)Ljava/lang/Object;", "write", "writeWhile", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "attachedJob", "Lka/v1;", "Session", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ByteChannelSequentialJVM extends ByteChannelSequentialBase {
    private volatile v1 attachedJob;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\f\u0010\rJ!\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/utils/io/ByteChannelSequentialJVM$Session;", "Lio/ktor/utils/io/LookAheadSuspendSession;", "Lio/ktor/utils/io/ByteChannelSequentialJVM;", "channel", "<init>", "(Lio/ktor/utils/io/ByteChannelSequentialJVM;)V", "", "n", "", "awaitAtLeast", "(ILd7/d;)Ljava/lang/Object;", "Lx6/t0;", "consumed", "(I)V", "skip", "atLeast", "Ljava/nio/ByteBuffer;", "request", "(II)Ljava/nio/ByteBuffer;", "Lio/ktor/utils/io/ByteChannelSequentialJVM;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Session implements LookAheadSuspendSession {
        private final ByteChannelSequentialJVM channel;

        public Session(ByteChannelSequentialJVM byteChannelSequentialJVM) {
            this.channel = byteChannelSequentialJVM;
        }

        @Override // io.ktor.utils.io.LookAheadSuspendSession
        public Object awaitAtLeast(int i10, d<? super Boolean> dVar) throws Throwable {
            Throwable closedCause = this.channel.getClosedCause();
            if (closedCause == null) {
                return this.channel.await(i10, dVar);
            }
            throw closedCause;
        }

        @Override // io.ktor.utils.io.LookAheadSession
        /* JADX INFO: renamed from: consumed */
        public void mo7011consumed(int n6) throws Throwable {
            Throwable closedCause = this.channel.getClosedCause();
            if (closedCause != null) {
                throw closedCause;
            }
            this.channel.discard(n6);
        }

        @Override // io.ktor.utils.io.LookAheadSession
        public ByteBuffer request(int skip, int atLeast) throws Throwable {
            Throwable closedCause = this.channel.getClosedCause();
            if (closedCause != null) {
                throw closedCause;
            }
            if (this.channel.isClosedForRead()) {
                return null;
            }
            if (this.channel.getReadable().getEndOfInput()) {
                this.channel.prepareFlushedBytes();
            }
            ChunkBuffer head = this.channel.getReadable().getHead();
            if (head.getWritePosition() - head.getReadPosition() < atLeast + skip) {
                return null;
            }
            ByteBuffer byteBufferSlice = head.getMemory().slice();
            byteBufferSlice.position(head.getReadPosition() + skip);
            byteBufferSlice.limit(head.getWritePosition());
            return byteBufferSlice;
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$attachJob$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "cause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            ByteChannelSequentialJVM.this.attachedJob = null;
            if (th != null) {
                ByteChannelSequentialJVM.this.cancel(ExceptionUtilsKt.unwrapCancellationException(th));
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$read$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialJVM", f = "ByteChannelSequentialJVM.kt", l = {196}, m = "read")
    public static final class C20231 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20231(d<? super C20231> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialJVM.this.read(0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$readAvailableSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialJVM", f = "ByteChannelSequentialJVM.kt", l = {112, 113}, m = "readAvailableSuspend")
    public static final class C20241 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20241(d<? super C20241> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialJVM.this.readAvailableSuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$readFullySuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialJVM", f = "ByteChannelSequentialJVM.kt", l = {128}, m = "readFullySuspend")
    public static final class C20251 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20251(d<? super C20251> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialJVM.this.readFullySuspend((ByteBuffer) null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$write$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialJVM", f = "ByteChannelSequentialJVM.kt", l = {234}, m = "write")
    public static final class C20261 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20261(d<? super C20261> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialJVM.this.write(0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$writeAvailableSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialJVM", f = "ByteChannelSequentialJVM.kt", l = {41, AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE}, m = "writeAvailableSuspend")
    public static final class C20271 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20271(d<? super C20271> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialJVM.this.writeAvailableSuspend((ByteBuffer) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$writeFullySuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialJVM", f = "ByteChannelSequentialJVM.kt", l = {54}, m = "writeFullySuspend")
    public static final class C20281 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20281(d<? super C20281> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialJVM.this.writeFullySuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialJVM$writeWhile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialJVM", f = "ByteChannelSequentialJVM.kt", l = {246}, m = "writeWhile")
    public static final class C20291 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C20291(d<? super C20291> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialJVM.this.writeWhile(null, this);
        }
    }

    public ByteChannelSequentialJVM(ChunkBuffer chunkBuffer, boolean z) {
        super(chunkBuffer, z, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailableSuspend(java.nio.ByteBuffer r6, d7.d<? super java.lang.Integer> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialJVM.C20241
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialJVM$readAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialJVM.C20241) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialJVM$readAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialJVM$readAvailableSuspend$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            return r7
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialJVM r1 = (io.ktor.utils.io.ByteChannelSequentialJVM) r1
            k2.c.G(r7)
            goto L4f
        L3e:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r5.await(r3, r0)
            if (r7 != r4) goto L4e
            goto L6b
        L4e:
            r1 = r5
        L4f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L5e
            java.lang.Integer r6 = new java.lang.Integer
            r7 = -1
            r6.<init>(r7)
            return r6
        L5e:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r6 = r1.readAvailable(r6, r0)
            if (r6 != r4) goto L6c
        L6b:
            return r4
        L6c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.readAvailableSuspend(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0052 -> B:20:0x0055). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(java.nio.ByteBuffer r7, int r8, d7.d<? super java.lang.Integer> r9) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialJVM.C20251
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialJVM$readFullySuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialJVM.C20251) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialJVM$readFullySuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialJVM$readFullySuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialJVM r1 = (io.ktor.utils.io.ByteChannelSequentialJVM) r1
            k2.c.G(r9)
            r5 = r8
            r8 = r7
            r7 = r5
            goto L55
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            k2.c.G(r9)
            r1 = r6
        L3e:
            boolean r9 = r7.hasRemaining()
            if (r9 == 0) goto L74
            r0.L$0 = r1
            r0.L$1 = r7
            r0.I$0 = r8
            r0.label = r2
            java.lang.Object r9 = r1.await(r2, r0)
            e7.a r3 = e7.a.f15033i
            if (r9 != r3) goto L55
            return r3
        L55:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            java.lang.String r3 = "Channel closed"
            if (r9 == 0) goto L6e
            int r9 = r1.tryReadAvailable(r7)
            r4 = -1
            if (r9 == r4) goto L68
            int r8 = r8 + r9
            goto L3e
        L68:
            java.io.EOFException r7 = new java.io.EOFException
            r7.<init>(r3)
            throw r7
        L6e:
            java.io.EOFException r7 = new java.io.EOFException
            r7.<init>(r3)
            throw r7
        L74:
            java.lang.Integer r7 = new java.lang.Integer
            r7.<init>(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.readFullySuspend(java.nio.ByteBuffer, int, d7.d):java.lang.Object");
    }

    private final int tryReadAvailable(ByteBuffer dst) throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
        if (getClosed() && get_availableForRead() == 0) {
            return -1;
        }
        if (!getReadable().canRead()) {
            prepareFlushedBytes();
        }
        int available = ByteBuffersKt.readAvailable(getReadable(), dst);
        afterRead(available);
        return available;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int tryWriteAvailable(java.nio.ByteBuffer r4) throws java.lang.Throwable {
        /*
            r3 = this;
            int r0 = r4.remaining()
            int r1 = r3.getAvailableForWrite()
            boolean r2 = r3.getClosed()
            if (r2 == 0) goto L1c
            java.lang.Throwable r4 = r3.getClosedCause()
            if (r4 != 0) goto L1b
            kotlinx.coroutines.channels.ClosedSendChannelException r4 = new kotlinx.coroutines.channels.ClosedSendChannelException
            java.lang.String r0 = "Channel closed for write"
            r4.<init>(r0)
        L1b:
            throw r4
        L1c:
            r2 = 0
            if (r0 != 0) goto L21
        L1f:
            r0 = r2
            goto L45
        L21:
            if (r0 > r1) goto L2b
            io.ktor.utils.io.core.BytePacketBuilder r1 = r3.getWritable()
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r1, r4)
            goto L45
        L2b:
            if (r1 != 0) goto L2e
            goto L1f
        L2e:
            int r0 = r4.limit()
            int r2 = r4.position()
            int r2 = r2 + r1
            r4.limit(r2)
            io.ktor.utils.io.core.BytePacketBuilder r2 = r3.getWritable()
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r2, r4)
            r4.limit(r0)
            r0 = r1
        L45:
            r3.afterWrite(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.tryWriteAvailable(java.nio.ByteBuffer):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(java.nio.ByteBuffer r6, d7.d<? super java.lang.Integer> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialJVM.C20271
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialJVM$writeAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialJVM.C20271) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialJVM$writeAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialJVM$writeAvailableSuspend$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            return r7
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialJVM r1 = (io.ktor.utils.io.ByteChannelSequentialJVM) r1
            k2.c.G(r7)
            goto L4f
        L3e:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r7 != r4) goto L4e
            goto L5c
        L4e:
            r1 = r5
        L4f:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r6 = r1.writeAvailable(r6, r0)
            if (r6 != r4) goto L5d
        L5c:
            return r4
        L5d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.writeAvailableSuspend(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x004b -> B:20:0x004e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeFullySuspend(java.nio.ByteBuffer r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialJVM.C20281
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialJVM$writeFullySuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialJVM.C20281) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialJVM$writeFullySuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialJVM$writeFullySuspend$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialJVM r1 = (io.ktor.utils.io.ByteChannelSequentialJVM) r1
            k2.c.G(r6)
            goto L4e
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            r1 = r4
        L39:
            boolean r6 = r5.hasRemaining()
            if (r6 == 0) goto L56
            r0.L$0 = r1
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = r1.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r3 = e7.a.f15033i
            if (r6 != r3) goto L4e
            return r3
        L4e:
            int r6 = r1.tryWriteAvailable(r5)
            r1.afterWrite(r6)
            goto L39
        L56:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.writeFullySuspend(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteChannel
    @x6.e
    public void attachJob(v1 job) {
        v1 v1Var = this.attachedJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.attachedJob = job;
        job.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new AnonymousClass1());
    }

    @Override // io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.ByteReadChannel
    public Object awaitContent(d<? super t0> dVar) {
        Object objAwait = await(1, dVar);
        return objAwait == a.f15033i ? objAwait : t0.f22605a;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @x6.e
    public <R> R lookAhead(l<? super LookAheadSession, ? extends R> visitor) {
        return (R) visitor.invoke(new Session(this));
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @x6.e
    public <R> Object lookAheadSuspend(p<? super LookAheadSuspendSession, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
        return pVar.invoke(new Session(this), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object read(int r6, r7.l<? super java.nio.ByteBuffer, x6.t0> r7, d7.d<? super x6.t0> r8) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.read(int, r7.l, d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(ByteBuffer byteBuffer, d<? super Integer> dVar) throws Throwable {
        int iTryReadAvailable = tryReadAvailable(byteBuffer);
        return iTryReadAvailable != 0 ? new Integer(iTryReadAvailable) : !byteBuffer.hasRemaining() ? new Integer(0) : readAvailableSuspend(byteBuffer, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFully(ByteBuffer byteBuffer, d<? super Integer> dVar) throws Throwable {
        int iTryReadAvailable = tryReadAvailable(byteBuffer);
        if (iTryReadAvailable != -1) {
            return !byteBuffer.hasRemaining() ? new Integer(iTryReadAvailable) : readFullySuspend(byteBuffer, iTryReadAvailable, dVar);
        }
        throw new EOFException("Channel closed");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.utils.io.ByteWriteChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object write(int r5, r7.l<? super java.nio.ByteBuffer, x6.t0> r6, d7.d<? super x6.t0> r7) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialJVM.C20261
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialJVM$write$1 r0 = (io.ktor.utils.io.ByteChannelSequentialJVM.C20261) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialJVM$write$1 r0 = new io.ktor.utils.io.ByteChannelSequentialJVM$write$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$1
            r7.l r6 = (r7.l) r6
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialJVM r0 = (io.ktor.utils.io.ByteChannelSequentialJVM) r0
            k2.c.G(r7)
            goto L60
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            k2.c.G(r7)
            boolean r7 = r4.getClosed()
            if (r7 == 0) goto L4e
            java.lang.Throwable r5 = r4.getClosedCause()
            if (r5 != 0) goto L4d
            kotlinx.coroutines.channels.ClosedSendChannelException r5 = new kotlinx.coroutines.channels.ClosedSendChannelException
            java.lang.String r6 = "Channel closed for write"
            r5.<init>(r6)
        L4d:
            throw r5
        L4e:
            r0.L$0 = r4
            r0.L$1 = r6
            r0.I$0 = r5
            r0.label = r2
            java.lang.Object r7 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L5f
            return r0
        L5f:
            r0 = r4
        L60:
            io.ktor.utils.io.core.BytePacketBuilder r7 = r0.getWritable()
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = r7.prepareWriteHead(r5)
            java.nio.ByteBuffer r1 = r5.getMemory()     // Catch: java.lang.Throwable -> L9c
            int r2 = r5.getWritePosition()     // Catch: java.lang.Throwable -> L9c
            int r3 = r5.getLimit()     // Catch: java.lang.Throwable -> L9c
            int r3 = r3 - r2
            java.nio.ByteBuffer r1 = io.ktor.utils.io.bits.Memory.m6756slice87lwejk(r1, r2, r3)     // Catch: java.lang.Throwable -> L9c
            r6.invoke(r1)     // Catch: java.lang.Throwable -> L9c
            int r6 = r1.limit()     // Catch: java.lang.Throwable -> L9c
            if (r6 != r3) goto L9e
            int r6 = r1.position()     // Catch: java.lang.Throwable -> L9c
            r5.commitWritten(r6)     // Catch: java.lang.Throwable -> L9c
            if (r6 < 0) goto L94
            r7.afterHeadWrite()
            r0.afterWrite(r6)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L94:
            java.lang.String r5 = "The returned value shouldn't be negative"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L9c
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L9c
            throw r6     // Catch: java.lang.Throwable -> L9c
        L9c:
            r5 = move-exception
            goto La6
        L9e:
            java.lang.String r5 = "Buffer's limit change is not allowed"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L9c
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L9c
            throw r6     // Catch: java.lang.Throwable -> L9c
        La6:
            r7.afterHeadWrite()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.write(int, r7.l, d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(ByteBuffer byteBuffer, d<? super Integer> dVar) throws Throwable {
        int iTryWriteAvailable = tryWriteAvailable(byteBuffer);
        if (iTryWriteAvailable <= 0) {
            if (byteBuffer.hasRemaining()) {
                return writeAvailableSuspend(byteBuffer, dVar);
            }
            iTryWriteAvailable = 0;
        }
        return new Integer(iTryWriteAvailable);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(ByteBuffer byteBuffer, d<? super t0> dVar) throws Throwable {
        Object objWriteFullySuspend;
        tryWriteAvailable(byteBuffer);
        boolean zHasRemaining = byteBuffer.hasRemaining();
        t0 t0Var = t0.f22605a;
        return (zHasRemaining && (objWriteFullySuspend = writeFullySuspend(byteBuffer, dVar)) == a.f15033i) ? objWriteFullySuspend : t0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0094 A[Catch: all -> 0x00b4, TRY_LEAVE, TryCatch #0 {all -> 0x00b4, blocks: (B:26:0x0071, B:28:0x0094, B:35:0x00ac, B:36:0x00b3, B:39:0x00b6, B:40:0x00bd), top: B:43:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b6 A[Catch: all -> 0x00b4, TryCatch #0 {all -> 0x00b4, blocks: (B:26:0x0071, B:28:0x0094, B:35:0x00ac, B:36:0x00b3, B:39:0x00b6, B:40:0x00bd), top: B:43:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0067 -> B:25:0x0069). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteWriteChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeWhile(r7.l<? super java.nio.ByteBuffer, java.lang.Boolean> r9, d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteChannelSequentialJVM.C20291
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteChannelSequentialJVM$writeWhile$1 r0 = (io.ktor.utils.io.ByteChannelSequentialJVM.C20291) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialJVM$writeWhile$1 r0 = new io.ktor.utils.io.ByteChannelSequentialJVM$writeWhile$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L39
            if (r1 != r2) goto L31
            java.lang.Object r9 = r0.L$2
            kotlin.jvm.internal.f0 r9 = (kotlin.jvm.internal.f0) r9
            java.lang.Object r1 = r0.L$1
            r7.l r1 = (r7.l) r1
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialJVM r3 = (io.ktor.utils.io.ByteChannelSequentialJVM) r3
            k2.c.G(r10)
            goto L69
        L31:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L39:
            k2.c.G(r10)
            r3 = r8
        L3d:
            boolean r10 = r3.getClosed()
            if (r10 == 0) goto L51
            java.lang.Throwable r9 = r3.getClosedCause()
            if (r9 != 0) goto L50
            kotlinx.coroutines.channels.ClosedSendChannelException r9 = new kotlinx.coroutines.channels.ClosedSendChannelException
            java.lang.String r10 = "Channel closed for write"
            r9.<init>(r10)
        L50:
            throw r9
        L51:
            kotlin.jvm.internal.f0 r10 = new kotlin.jvm.internal.f0
            r10.<init>()
            r0.L$0 = r3
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r2
            java.lang.Object r1 = r3.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r4 = e7.a.f15033i
            if (r1 != r4) goto L67
            return r4
        L67:
            r1 = r9
            r9 = r10
        L69:
            io.ktor.utils.io.core.BytePacketBuilder r10 = r3.getWritable()
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = r10.prepareWriteHead(r2)
            java.nio.ByteBuffer r5 = r4.getMemory()     // Catch: java.lang.Throwable -> Lb4
            int r6 = r4.getWritePosition()     // Catch: java.lang.Throwable -> Lb4
            int r7 = r4.getLimit()     // Catch: java.lang.Throwable -> Lb4
            int r7 = r7 - r6
            java.nio.ByteBuffer r5 = io.ktor.utils.io.bits.Memory.m6756slice87lwejk(r5, r6, r7)     // Catch: java.lang.Throwable -> Lb4
            java.lang.Object r6 = r1.invoke(r5)     // Catch: java.lang.Throwable -> Lb4
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Throwable -> Lb4
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Throwable -> Lb4
            r9.f19738i = r6     // Catch: java.lang.Throwable -> Lb4
            int r6 = r5.limit()     // Catch: java.lang.Throwable -> Lb4
            if (r6 != r7) goto Lb6
            int r5 = r5.position()     // Catch: java.lang.Throwable -> Lb4
            r4.commitWritten(r5)     // Catch: java.lang.Throwable -> Lb4
            if (r5 < 0) goto Lac
            r10.afterHeadWrite()
            r3.afterWrite(r5)
            boolean r9 = r9.f19738i
            if (r9 != 0) goto Laa
            x6.t0 r9 = x6.t0.f22605a
            return r9
        Laa:
            r9 = r1
            goto L3d
        Lac:
            java.lang.String r9 = "The returned value shouldn't be negative"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lb4
            r0.<init>(r9)     // Catch: java.lang.Throwable -> Lb4
            throw r0     // Catch: java.lang.Throwable -> Lb4
        Lb4:
            r9 = move-exception
            goto Lbe
        Lb6:
            java.lang.String r9 = "Buffer's limit change is not allowed"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lb4
            r0.<init>(r9)     // Catch: java.lang.Throwable -> Lb4
            throw r0     // Catch: java.lang.Throwable -> Lb4
        Lbe:
            r10.afterHeadWrite()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialJVM.writeWhile(r7.l, d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public int writeAvailable(int min, l<? super ByteBuffer, t0> block) throws Throwable {
        if (getClosed()) {
            Throwable closedCause = getClosedCause();
            if (closedCause == null) {
                throw new ClosedSendChannelException("Channel closed for write");
            }
            throw closedCause;
        }
        if (getAvailableForWrite() < min) {
            return 0;
        }
        BytePacketBuilder writable = getWritable();
        ChunkBuffer chunkBufferPrepareWriteHead = writable.prepareWriteHead(min);
        try {
            ByteBuffer memory = chunkBufferPrepareWriteHead.getMemory();
            int writePosition = chunkBufferPrepareWriteHead.getWritePosition();
            int limit = chunkBufferPrepareWriteHead.getLimit() - writePosition;
            ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, writePosition, limit);
            int iPosition = byteBufferM6756slice87lwejk.position();
            block.invoke(byteBufferM6756slice87lwejk);
            int iPosition2 = byteBufferM6756slice87lwejk.position() - iPosition;
            if (byteBufferM6756slice87lwejk.limit() == limit) {
                int iPosition3 = byteBufferM6756slice87lwejk.position();
                chunkBufferPrepareWriteHead.commitWritten(iPosition3);
                if (iPosition3 >= 0) {
                    writable.afterHeadWrite();
                    return iPosition2;
                }
                throw new IllegalStateException("The returned value shouldn't be negative");
            }
            throw new IllegalStateException("Buffer's limit change is not allowed");
        } catch (Throwable th) {
            writable.afterHeadWrite();
            throw th;
        }
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public int readAvailable(int min, l<? super ByteBuffer, t0> block) throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause == null) {
            if (get_availableForRead() < min) {
                return -1;
            }
            prepareFlushedBytes();
            ByteReadPacket readable = getReadable();
            ChunkBuffer chunkBufferPrepareRead = readable.prepareRead(min);
            if (chunkBufferPrepareRead != null) {
                int readPosition = chunkBufferPrepareRead.getReadPosition();
                try {
                    ByteBuffer memory = chunkBufferPrepareRead.getMemory();
                    int readPosition2 = chunkBufferPrepareRead.getReadPosition();
                    int writePosition = chunkBufferPrepareRead.getWritePosition() - readPosition2;
                    ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition2, writePosition);
                    int iPosition = byteBufferM6756slice87lwejk.position();
                    block.invoke(byteBufferM6756slice87lwejk);
                    int iPosition2 = byteBufferM6756slice87lwejk.position() - iPosition;
                    if (byteBufferM6756slice87lwejk.limit() == writePosition) {
                        chunkBufferPrepareRead.discardExact(byteBufferM6756slice87lwejk.position());
                        int readPosition3 = chunkBufferPrepareRead.getReadPosition();
                        if (readPosition3 >= readPosition) {
                            if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                                readable.ensureNext(chunkBufferPrepareRead);
                                return iPosition2;
                            }
                            readable.setHeadPosition(readPosition3);
                            return iPosition2;
                        }
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                    throw new IllegalStateException("Buffer's limit change is not allowed");
                } catch (Throwable th) {
                    int readPosition4 = chunkBufferPrepareRead.getReadPosition();
                    if (readPosition4 >= readPosition) {
                        if (readPosition4 == chunkBufferPrepareRead.getWritePosition()) {
                            readable.ensureNext(chunkBufferPrepareRead);
                        } else {
                            readable.setHeadPosition(readPosition4);
                        }
                        throw th;
                    }
                    throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                }
            }
            throw b.i(min);
        }
        throw closedCause;
    }
}
