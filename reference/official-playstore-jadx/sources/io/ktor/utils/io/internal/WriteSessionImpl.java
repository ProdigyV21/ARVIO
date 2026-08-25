package io.ktor.utils.io.internal;

import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.utils.io.ByteBufferChannel;
import io.ktor.utils.io.WriterSuspendSession;
import io.ktor.utils.io.core.BufferUtilsJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u000fJ\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\rJ\u000f\u0010\u0018\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0018\u0010\u000fR\u0016\u0010\u0019\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lio/ktor/utils/io/internal/WriteSessionImpl;", "Lio/ktor/utils/io/WriterSuspendSession;", "Lio/ktor/utils/io/ByteBufferChannel;", "channel", "<init>", "(Lio/ktor/utils/io/ByteBufferChannel;)V", "", "n", "", "writtenFailed", "(I)Ljava/lang/Void;", "Lx6/t0;", "tryAwaitJoinSwitch", "(ILd7/d;)Ljava/lang/Object;", "begin", "()V", "complete", "min", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "request", "(I)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "written", "(I)V", "tryAwait", "flush", "locked", "I", "current", "Lio/ktor/utils/io/ByteBufferChannel;", "Ljava/nio/ByteBuffer;", "byteBuffer", "Ljava/nio/ByteBuffer;", "view", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "ringBufferCapacity", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WriteSessionImpl implements WriterSuspendSession {
    private ByteBuffer byteBuffer;
    private ByteBufferChannel current;
    private int locked;
    private RingBufferCapacity ringBufferCapacity;
    private ChunkBuffer view;

    /* JADX INFO: renamed from: io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.internal.WriteSessionImpl", f = "WriteSessionImpl.kt", l = {86}, m = "tryAwaitJoinSwitch")
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
            return WriteSessionImpl.this.tryAwaitJoinSwitch(0, this);
        }
    }

    public WriteSessionImpl(ByteBufferChannel byteBufferChannel) {
        this.current = byteBufferChannel.resolveChannelInstance$ktor_io();
        ChunkBuffer.Companion companion = ChunkBuffer.INSTANCE;
        this.byteBuffer = companion.getEmpty().getMemory();
        this.view = companion.getEmpty();
        this.ringBufferCapacity = this.current.currentState$ktor_io().capacity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryAwaitJoinSwitch(int r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.internal.WriteSessionImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1 r0 = (io.ktor.utils.io.internal.WriteSessionImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1 r0 = new io.ktor.utils.io.internal.WriteSessionImpl$tryAwaitJoinSwitch$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.internal.WriteSessionImpl r5 = (io.ktor.utils.io.internal.WriteSessionImpl) r5
            k2.c.G(r6)
            goto L5d
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            int r6 = r4.locked
            if (r6 <= 0) goto L40
            io.ktor.utils.io.internal.RingBufferCapacity r1 = r4.ringBufferCapacity
            r1.completeRead(r6)
            r6 = 0
            r4.locked = r6
        L40:
            r4.flush()
            io.ktor.utils.io.ByteBufferChannel r6 = r4.current
            r6.restoreStateAfterWrite$ktor_io()
            io.ktor.utils.io.ByteBufferChannel r6 = r4.current
            r6.tryTerminate$ktor_io()
            io.ktor.utils.io.ByteBufferChannel r6 = r4.current
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r5 = r6.tryWriteSuspend$ktor_io(r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L5c
            return r6
        L5c:
            r5 = r4
        L5d:
            io.ktor.utils.io.ByteBufferChannel r6 = r5.current
            io.ktor.utils.io.ByteBufferChannel r6 = r6.resolveChannelInstance$ktor_io()
            r5.current = r6
            java.nio.ByteBuffer r6 = r6.setupStateForWrite$ktor_io()
            if (r6 != 0) goto L6c
            goto L8d
        L6c:
            r5.byteBuffer = r6
            io.ktor.utils.io.ByteBufferChannel r6 = r5.current
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r6.currentState$ktor_io()
            java.nio.ByteBuffer r6 = r6.backingBuffer
            r0 = 2
            r1 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r6 = io.ktor.utils.io.core.BufferUtilsJvmKt.ChunkBuffer$default(r6, r1, r0, r1)
            r5.view = r6
            java.nio.ByteBuffer r0 = r5.byteBuffer
            io.ktor.utils.io.core.BufferUtilsJvmKt.resetFromContentToWrite(r6, r0)
            io.ktor.utils.io.ByteBufferChannel r6 = r5.current
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r6.currentState$ktor_io()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            r5.ringBufferCapacity = r6
        L8d:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.WriteSessionImpl.tryAwaitJoinSwitch(int, d7.d):java.lang.Object");
    }

    private final Void writtenFailed(int n6) {
        if (n6 < 0) {
            throw new IllegalArgumentException(a0.c.i(n6, "Written bytes count shouldn't be negative: "));
        }
        throw new IllegalStateException(androidx.compose.material3.d.j(this.locked, " were pre-locked.", a0.c.s(n6, "Unable to mark ", " bytes as written: only ")));
    }

    public final void begin() {
        ByteBufferChannel byteBufferChannelResolveChannelInstance$ktor_io = this.current.resolveChannelInstance$ktor_io();
        this.current = byteBufferChannelResolveChannelInstance$ktor_io;
        ByteBuffer byteBuffer = byteBufferChannelResolveChannelInstance$ktor_io.setupStateForWrite$ktor_io();
        if (byteBuffer == null) {
            return;
        }
        this.byteBuffer = byteBuffer;
        ChunkBuffer chunkBufferChunkBuffer$default = BufferUtilsJvmKt.ChunkBuffer$default(this.current.currentState$ktor_io().backingBuffer, null, 2, null);
        this.view = chunkBufferChunkBuffer$default;
        BufferUtilsJvmKt.resetFromContentToWrite(chunkBufferChunkBuffer$default, this.byteBuffer);
        this.ringBufferCapacity = this.current.currentState$ktor_io().capacity;
    }

    public final void complete() {
        int i10 = this.locked;
        if (i10 > 0) {
            this.ringBufferCapacity.completeRead(i10);
            this.locked = 0;
        }
        this.current.restoreStateAfterWrite$ktor_io();
        this.current.tryTerminate$ktor_io();
    }

    @Override // io.ktor.utils.io.WriterSession
    public void flush() {
        this.current.flush();
    }

    @Override // io.ktor.utils.io.WriterSession
    public ChunkBuffer request(int min) {
        int iTryWriteAtLeast = this.ringBufferCapacity.tryWriteAtLeast(0) + this.locked;
        this.locked = iTryWriteAtLeast;
        if (iTryWriteAtLeast < min) {
            return null;
        }
        this.current.prepareWriteBuffer$ktor_io(this.byteBuffer, iTryWriteAtLeast);
        if (this.byteBuffer.remaining() < min) {
            return null;
        }
        BufferUtilsJvmKt.resetFromContentToWrite(this.view, this.byteBuffer);
        return this.view;
    }

    @Override // io.ktor.utils.io.WriterSuspendSession
    public Object tryAwait(int i10, d<? super t0> dVar) throws Throwable {
        JoiningState joining = this.current.getJoining();
        a aVar = a.f15033i;
        t0 t0Var = t0.f22605a;
        if (joining != null) {
            Object objTryAwaitJoinSwitch = tryAwaitJoinSwitch(i10, dVar);
            return objTryAwaitJoinSwitch == aVar ? objTryAwaitJoinSwitch : t0Var;
        }
        int i11 = this.locked;
        if (i11 >= i10) {
            return t0Var;
        }
        if (i11 > 0) {
            this.ringBufferCapacity.completeRead(i11);
            this.locked = 0;
        }
        Object objTryWriteSuspend$ktor_io = this.current.tryWriteSuspend$ktor_io(i10, dVar);
        return objTryWriteSuspend$ktor_io == aVar ? objTryWriteSuspend$ktor_io : t0Var;
    }

    @Override // io.ktor.utils.io.WriterSession
    public void written(int n6) {
        int i10;
        if (n6 < 0 || n6 > (i10 = this.locked)) {
            writtenFailed(n6);
            throw new KotlinNothingValueException();
        }
        this.locked = i10 - n6;
        this.current.bytesWrittenFromSession$ktor_io(this.byteBuffer, this.ringBufferCapacity, n6);
    }
}
