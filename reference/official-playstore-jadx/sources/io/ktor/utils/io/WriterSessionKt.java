package io.ktor.utils.io;

import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u001aq\u0010\f\u001a\u00020\u0001*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012K\u0010\u000b\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0003H\u0086Hø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a!\u0010\u000f\u001a\u0004\u0018\u00010\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0014\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0001H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001f\u0010\u0016\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a%\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u000f\u0010\u001c\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u0018*\u00020\u0000H\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "", "desiredSpace", "Lkotlin/Function3;", "Lio/ktor/utils/io/bits/Memory;", "Lx6/y;", ContentDisposition.Parameters.Name, "freeSpace", "", "startOffset", "endExclusive", "block", "write", "(Lio/ktor/utils/io/ByteWriteChannel;ILr7/q;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/Buffer;", "requestWriteBuffer", "(Lio/ktor/utils/io/ByteWriteChannel;ILd7/d;)Ljava/lang/Object;", "buffer", "written", "Lx6/t0;", "completeWriting", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/core/Buffer;ILd7/d;)Ljava/lang/Object;", "completeWritingFallback", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/core/Buffer;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/WriterSuspendSession;", SettingsSessionManager.SETTINGS_KEY, "writeBufferSuspend", "(Lio/ktor/utils/io/WriterSuspendSession;ILd7/d;)Ljava/lang/Object;", "writeBufferFallback", "()Lio/ktor/utils/io/core/Buffer;", "writeSessionFor", "(Lio/ktor/utils/io/ByteWriteChannel;)Lio/ktor/utils/io/WriterSuspendSession;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WriterSessionKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.WriterSessionKt", f = "WriterSession.kt", l = {83}, m = "completeWritingFallback")
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
            return WriterSessionKt.completeWritingFallback(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.WriterSessionKt$write$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.WriterSessionKt", f = "WriterSession.kt", l = {22, 29, 29}, m = "write")
    public static final class C20451 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20451(d<? super C20451> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WriterSessionKt.write(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.WriterSessionKt", f = "WriterSession.kt", l = {93}, m = "writeBufferSuspend")
    public static final class C20461 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20461(d<? super C20461> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WriterSessionKt.writeBufferSuspend(null, 0, this);
        }
    }

    public static final Object completeWriting(ByteWriteChannel byteWriteChannel, Buffer buffer, int i10, d<? super t0> dVar) {
        boolean z = byteWriteChannel instanceof HasWriteSession;
        t0 t0Var = t0.f22605a;
        if (z) {
            ((HasWriteSession) byteWriteChannel).endWriteSession(i10);
            return t0Var;
        }
        Object objCompleteWritingFallback = completeWritingFallback(byteWriteChannel, buffer, dVar);
        return objCompleteWritingFallback == a.f15033i ? objCompleteWritingFallback : t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object completeWritingFallback(io.ktor.utils.io.ByteWriteChannel r4, io.ktor.utils.io.core.Buffer r5, d7.d<? super x6.t0> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.WriterSessionKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1 r0 = (io.ktor.utils.io.WriterSessionKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1 r0 = new io.ktor.utils.io.WriterSessionKt$completeWritingFallback$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5
            k2.c.G(r6)
            goto L46
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r6)
            boolean r6 = r5 instanceof io.ktor.utils.io.core.internal.ChunkBuffer
            if (r6 == 0) goto L54
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r4 = r4.writeFully(r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r4 != r6) goto L46
            return r6
        L46:
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = (io.ktor.utils.io.core.internal.ChunkBuffer) r5
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r4 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r4 = r4.getPool()
            r5.release(r4)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        L54:
            java.lang.UnsupportedOperationException r4 = new java.lang.UnsupportedOperationException
            java.lang.String r5 = "Only ChunkBuffer instance is supported."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.WriterSessionKt.completeWritingFallback(io.ktor.utils.io.ByteWriteChannel, io.ktor.utils.io.core.Buffer, d7.d):java.lang.Object");
    }

    public static final Object requestWriteBuffer(ByteWriteChannel byteWriteChannel, int i10, d<? super Buffer> dVar) {
        WriterSuspendSession writerSuspendSessionWriteSessionFor = writeSessionFor(byteWriteChannel);
        if (writerSuspendSessionWriteSessionFor == null) {
            return writeBufferFallback();
        }
        ChunkBuffer chunkBufferRequest = writerSuspendSessionWriteSessionFor.request(i10);
        return chunkBufferRequest != null ? chunkBufferRequest : writeBufferSuspend(writerSuspendSessionWriteSessionFor, i10, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:0|2|(2:4|(1:6)(1:7))(0)|8|(1:(1:(1:(2:13|14)(2:15|40))(2:16|39))(1:17))(3:18|(0)|34)|21|(1:23)|24|37|25|26|(2:29|30)|34) */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a8, code lost:
    
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a9, code lost:
    
        r0.L$0 = r12;
        r0.L$1 = null;
        r0.label = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b3, code lost:
    
        if (completeWriting(r10, r13, r1, r0) != r5) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b7, code lost:
    
        throw r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object write(io.ktor.utils.io.ByteWriteChannel r10, int r11, r7.q<? super io.ktor.utils.io.bits.Memory, ? super java.lang.Long, ? super java.lang.Long, java.lang.Integer> r12, d7.d<? super java.lang.Integer> r13) throws java.lang.Throwable {
        /*
            boolean r0 = r13 instanceof io.ktor.utils.io.WriterSessionKt.C20451
            if (r0 == 0) goto L13
            r0 = r13
            io.ktor.utils.io.WriterSessionKt$write$1 r0 = (io.ktor.utils.io.WriterSessionKt.C20451) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.WriterSessionKt$write$1 r0 = new io.ktor.utils.io.WriterSessionKt$write$1
            r0.<init>(r13)
        L18:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L4f
            if (r1 == r4) goto L42
            if (r1 == r3) goto L3a
            if (r1 == r2) goto L31
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L31:
            java.lang.Object r10 = r0.L$0
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            k2.c.G(r13)
            goto Lb7
        L3a:
            java.lang.Object r10 = r0.L$0
            java.lang.Integer r10 = (java.lang.Integer) r10
            k2.c.G(r13)
            goto La7
        L42:
            java.lang.Object r10 = r0.L$1
            r12 = r10
            r7.q r12 = (r7.q) r12
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            k2.c.G(r13)
            goto L5f
        L4f:
            k2.c.G(r13)
            r0.L$0 = r10
            r0.L$1 = r12
            r0.label = r4
            java.lang.Object r13 = requestWriteBuffer(r10, r11, r0)
            if (r13 != r5) goto L5f
            goto Lb5
        L5f:
            io.ktor.utils.io.core.Buffer r13 = (io.ktor.utils.io.core.Buffer) r13
            if (r13 != 0) goto L69
            io.ktor.utils.io.core.Buffer$Companion r11 = io.ktor.utils.io.core.Buffer.INSTANCE
            io.ktor.utils.io.core.Buffer r13 = r11.getEmpty()
        L69:
            r11 = 0
            r1 = 0
            java.nio.ByteBuffer r4 = r13.getMemory()     // Catch: java.lang.Throwable -> La8
            io.ktor.utils.io.bits.Memory r4 = io.ktor.utils.io.bits.Memory.m6745boximpl(r4)     // Catch: java.lang.Throwable -> La8
            int r6 = r13.getWritePosition()     // Catch: java.lang.Throwable -> La8
            long r6 = (long) r6     // Catch: java.lang.Throwable -> La8
            java.lang.Long r8 = new java.lang.Long     // Catch: java.lang.Throwable -> La8
            r8.<init>(r6)     // Catch: java.lang.Throwable -> La8
            int r6 = r13.getLimit()     // Catch: java.lang.Throwable -> La8
            long r6 = (long) r6     // Catch: java.lang.Throwable -> La8
            java.lang.Long r9 = new java.lang.Long     // Catch: java.lang.Throwable -> La8
            r9.<init>(r6)     // Catch: java.lang.Throwable -> La8
            java.lang.Object r12 = r12.invoke(r4, r8, r9)     // Catch: java.lang.Throwable -> La8
            java.lang.Number r12 = (java.lang.Number) r12     // Catch: java.lang.Throwable -> La8
            int r1 = r12.intValue()     // Catch: java.lang.Throwable -> La8
            r13.commitWritten(r1)     // Catch: java.lang.Throwable -> La8
            java.lang.Integer r12 = new java.lang.Integer     // Catch: java.lang.Throwable -> La8
            r12.<init>(r1)     // Catch: java.lang.Throwable -> La8
            r0.L$0 = r12
            r0.L$1 = r11
            r0.label = r3
            java.lang.Object r10 = completeWriting(r10, r13, r1, r0)
            if (r10 != r5) goto La6
            goto Lb5
        La6:
            r10 = r12
        La7:
            return r10
        La8:
            r12 = move-exception
            r0.L$0 = r12
            r0.L$1 = r11
            r0.label = r2
            java.lang.Object r10 = completeWriting(r10, r13, r1, r0)
            if (r10 != r5) goto Lb6
        Lb5:
            return r5
        Lb6:
            r10 = r12
        Lb7:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.WriterSessionKt.write(io.ktor.utils.io.ByteWriteChannel, int, r7.q, d7.d):java.lang.Object");
    }

    private static final Object write$$forInline(ByteWriteChannel byteWriteChannel, int i10, q<? super Memory, ? super Long, ? super Long, Integer> qVar, d<? super Integer> dVar) {
        Buffer empty = (Buffer) requestWriteBuffer(byteWriteChannel, i10, dVar);
        if (empty == null) {
            empty = Buffer.INSTANCE.getEmpty();
        }
        int iIntValue = 0;
        try {
            iIntValue = ((Number) qVar.invoke(Memory.m6745boximpl(empty.getMemory()), Long.valueOf(empty.getWritePosition()), Long.valueOf(empty.getLimit()))).intValue();
            empty.commitWritten(iIntValue);
            return Integer.valueOf(iIntValue);
        } finally {
            completeWriting(byteWriteChannel, empty, iIntValue, dVar);
        }
    }

    public static /* synthetic */ Object write$default(ByteWriteChannel byteWriteChannel, int i10, q qVar, d dVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 1;
        }
        Buffer empty = (Buffer) requestWriteBuffer(byteWriteChannel, i10, dVar);
        if (empty == null) {
            empty = Buffer.INSTANCE.getEmpty();
        }
        int iIntValue = 0;
        try {
            iIntValue = ((Number) qVar.invoke(Memory.m6745boximpl(empty.getMemory()), Long.valueOf(empty.getWritePosition()), Long.valueOf(empty.getLimit()))).intValue();
            empty.commitWritten(iIntValue);
            return Integer.valueOf(iIntValue);
        } finally {
            completeWriting(byteWriteChannel, empty, iIntValue, dVar);
        }
    }

    private static final Buffer writeBufferFallback() {
        ChunkBuffer chunkBufferBorrow = ChunkBuffer.INSTANCE.getPool().borrow();
        ChunkBuffer chunkBuffer = chunkBufferBorrow;
        chunkBuffer.resetForWrite();
        chunkBuffer.reserveEndGap(8);
        return chunkBufferBorrow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writeBufferSuspend(io.ktor.utils.io.WriterSuspendSession r4, int r5, d7.d<? super io.ktor.utils.io.core.Buffer> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.WriterSessionKt.C20461
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1 r0 = (io.ktor.utils.io.WriterSessionKt.C20461) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1 r0 = new io.ktor.utils.io.WriterSessionKt$writeBufferSuspend$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            int r5 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.WriterSuspendSession r4 = (io.ktor.utils.io.WriterSuspendSession) r4
            k2.c.G(r6)
            goto L45
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.I$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.tryAwait(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L45
            return r0
        L45:
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = r4.request(r5)
            if (r5 == 0) goto L4c
            return r5
        L4c:
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = r4.request(r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.WriterSessionKt.writeBufferSuspend(io.ktor.utils.io.WriterSuspendSession, int, d7.d):java.lang.Object");
    }

    private static final WriterSuspendSession writeSessionFor(ByteWriteChannel byteWriteChannel) {
        if (byteWriteChannel instanceof HasWriteSession) {
            return ((HasWriteSession) byteWriteChannel).beginWriteSession();
        }
        return null;
    }
}
