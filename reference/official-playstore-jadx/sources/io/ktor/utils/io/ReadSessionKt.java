package io.ktor.utils.io;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aq\u0010\f\u001a\u00020\u0001*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012K\u0010\u000b\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0003H\u0086Hø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a!\u0010\u000f\u001a\u0004\u0018\u00010\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a)\u0010\u0014\u001a\u00020\u0013*\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0012\u001a\u00020\u0001H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a!\u0010\u0017\u001a\u0004\u0018\u00010\u000e*\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001f\u0010\u001a\u001a\u00020\u0019*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0010\u001a\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u0016*\u00020\u0000H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "", "desiredSize", "Lkotlin/Function3;", "Lio/ktor/utils/io/bits/Memory;", "Lx6/y;", ContentDisposition.Parameters.Name, "source", "", TtmlNode.START, "endExclusive", "block", "read", "(Lio/ktor/utils/io/ByteReadChannel;ILr7/q;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/Buffer;", "requestBuffer", "(Lio/ktor/utils/io/ByteReadChannel;ILd7/d;)Ljava/lang/Object;", "buffer", "bytesRead", "Lx6/t0;", "completeReadingFromBuffer", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/Buffer;ILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/SuspendableReadSession;", "requestBufferSuspend", "(Lio/ktor/utils/io/SuspendableReadSession;ILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "requestBufferFallback", "readSessionFor", "(Lio/ktor/utils/io/ByteReadChannel;)Lio/ktor/utils/io/SuspendableReadSession;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ReadSessionKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ReadSessionKt$read$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ReadSessionKt", f = "ReadSession.kt", l = {24, 28, 31}, m = "read")
    public static final class AnonymousClass1 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ReadSessionKt.read(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ReadSessionKt", f = "ReadSession.kt", l = {133}, m = "requestBufferFallback")
    public static final class C20431 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20431(d<? super C20431> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ReadSessionKt.requestBufferFallback(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ReadSessionKt", f = "ReadSession.kt", l = {125}, m = "requestBufferSuspend")
    public static final class C20441 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20441(d<? super C20441> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ReadSessionKt.requestBufferSuspend(null, 0, this);
        }
    }

    public static final Object completeReadingFromBuffer(ByteReadChannel byteReadChannel, Buffer buffer, int i10, d<? super t0> dVar) {
        if (i10 < 0) {
            throw new IllegalStateException(a0.c.i(i10, "bytesRead shouldn't be negative: ").toString());
        }
        SuspendableReadSession sessionFor = readSessionFor(byteReadChannel);
        t0 t0Var = t0.f22605a;
        if (sessionFor != null) {
            sessionFor.discard(i10);
            if (byteReadChannel instanceof HasReadSession) {
                ((HasReadSession) byteReadChannel).endReadSession();
            }
            return t0Var;
        }
        if (buffer instanceof ChunkBuffer) {
            ChunkBuffer.Companion companion = ChunkBuffer.INSTANCE;
            if (buffer != companion.getEmpty()) {
                ((ChunkBuffer) buffer).release(companion.getPool());
                Object objDiscard = byteReadChannel.discard(i10, dVar);
                if (objDiscard == a.f15033i) {
                    return objDiscard;
                }
            }
        }
        return t0Var;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(2:4|(1:6)(1:7))(0)|8|(1:(1:(1:(2:13|14)(2:15|40))(5:16|43|17|33|34))(1:21))(3:22|(0)|39)|25|(1:27)(1:28)|41|29|(3:32|33|34)|39) */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b2, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b3, code lost:
    
        r11 = r9;
        r9 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:?, code lost:
    
        throw r9;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object read(io.ktor.utils.io.ByteReadChannel r9, int r10, r7.q<? super io.ktor.utils.io.bits.Memory, ? super java.lang.Long, ? super java.lang.Long, java.lang.Integer> r11, d7.d<? super java.lang.Integer> r12) throws java.lang.Throwable {
        /*
            boolean r0 = r12 instanceof io.ktor.utils.io.ReadSessionKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r12
            io.ktor.utils.io.ReadSessionKt$read$1 r0 = (io.ktor.utils.io.ReadSessionKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ReadSessionKt$read$1 r0 = new io.ktor.utils.io.ReadSessionKt$read$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L58
            if (r1 == r4) goto L4b
            if (r1 == r3) goto L3a
            if (r1 == r2) goto L31
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L31:
            java.lang.Object r9 = r0.L$0
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            k2.c.G(r12)
            goto Lc5
        L3a:
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$1
            io.ktor.utils.io.core.Buffer r10 = (io.ktor.utils.io.core.Buffer) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            k2.c.G(r12)     // Catch: java.lang.Throwable -> L48
            goto Lac
        L48:
            r9 = move-exception
            goto Lb6
        L4b:
            java.lang.Object r9 = r0.L$1
            r11 = r9
            r7.q r11 = (r7.q) r11
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            k2.c.G(r12)
            goto L68
        L58:
            k2.c.G(r12)
            r0.L$0 = r9
            r0.L$1 = r11
            r0.label = r4
            java.lang.Object r12 = requestBuffer(r9, r10, r0)
            if (r12 != r5) goto L68
            goto Lc4
        L68:
            io.ktor.utils.io.core.Buffer r12 = (io.ktor.utils.io.core.Buffer) r12
            if (r12 != 0) goto L73
            io.ktor.utils.io.core.Buffer$Companion r10 = io.ktor.utils.io.core.Buffer.INSTANCE
            io.ktor.utils.io.core.Buffer r10 = r10.getEmpty()
            goto L74
        L73:
            r10 = r12
        L74:
            java.nio.ByteBuffer r12 = r10.getMemory()     // Catch: java.lang.Throwable -> Lb2
            io.ktor.utils.io.bits.Memory r12 = io.ktor.utils.io.bits.Memory.m6745boximpl(r12)     // Catch: java.lang.Throwable -> Lb2
            int r1 = r10.getReadPosition()     // Catch: java.lang.Throwable -> Lb2
            long r6 = (long) r1     // Catch: java.lang.Throwable -> Lb2
            java.lang.Long r1 = new java.lang.Long     // Catch: java.lang.Throwable -> Lb2
            r1.<init>(r6)     // Catch: java.lang.Throwable -> Lb2
            int r4 = r10.getWritePosition()     // Catch: java.lang.Throwable -> Lb2
            long r6 = (long) r4     // Catch: java.lang.Throwable -> Lb2
            java.lang.Long r4 = new java.lang.Long     // Catch: java.lang.Throwable -> Lb2
            r4.<init>(r6)     // Catch: java.lang.Throwable -> Lb2
            java.lang.Object r11 = r11.invoke(r12, r1, r4)     // Catch: java.lang.Throwable -> Lb2
            java.lang.Number r11 = (java.lang.Number) r11     // Catch: java.lang.Throwable -> Lb2
            int r11 = r11.intValue()     // Catch: java.lang.Throwable -> Lb2
            r0.L$0 = r9     // Catch: java.lang.Throwable -> Lb2
            r0.L$1 = r10     // Catch: java.lang.Throwable -> Lb2
            r0.I$0 = r11     // Catch: java.lang.Throwable -> Lb2
            r0.label = r3     // Catch: java.lang.Throwable -> Lb2
            java.lang.Object r12 = completeReadingFromBuffer(r9, r10, r11, r0)     // Catch: java.lang.Throwable -> Lb2
            if (r12 != r5) goto La9
            goto Lc4
        La9:
            r8 = r11
            r11 = r9
            r9 = r8
        Lac:
            java.lang.Integer r12 = new java.lang.Integer     // Catch: java.lang.Throwable -> L48
            r12.<init>(r9)     // Catch: java.lang.Throwable -> L48
            return r12
        Lb2:
            r11 = move-exception
            r8 = r11
            r11 = r9
            r9 = r8
        Lb6:
            r0.L$0 = r9
            r12 = 0
            r0.L$1 = r12
            r0.label = r2
            r12 = 0
            java.lang.Object r10 = completeReadingFromBuffer(r11, r10, r12, r0)
            if (r10 != r5) goto Lc5
        Lc4:
            return r5
        Lc5:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ReadSessionKt.read(io.ktor.utils.io.ByteReadChannel, int, r7.q, d7.d):java.lang.Object");
    }

    private static final Object read$$forInline(ByteReadChannel byteReadChannel, int i10, q<? super Memory, ? super Long, ? super Long, Integer> qVar, d<? super Integer> dVar) {
        Buffer empty = (Buffer) requestBuffer(byteReadChannel, i10, dVar);
        if (empty == null) {
            empty = Buffer.INSTANCE.getEmpty();
        }
        try {
            int iIntValue = ((Number) qVar.invoke(Memory.m6745boximpl(empty.getMemory()), Long.valueOf(empty.getReadPosition()), Long.valueOf(empty.getWritePosition()))).intValue();
            completeReadingFromBuffer(byteReadChannel, empty, iIntValue, dVar);
            return Integer.valueOf(iIntValue);
        } catch (Throwable th) {
            completeReadingFromBuffer(byteReadChannel, empty, 0, dVar);
            throw th;
        }
    }

    public static /* synthetic */ Object read$default(ByteReadChannel byteReadChannel, int i10, q qVar, d dVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 1;
        }
        Buffer empty = (Buffer) requestBuffer(byteReadChannel, i10, dVar);
        if (empty == null) {
            empty = Buffer.INSTANCE.getEmpty();
        }
        try {
            int iIntValue = ((Number) qVar.invoke(Memory.m6745boximpl(empty.getMemory()), Long.valueOf(empty.getReadPosition()), Long.valueOf(empty.getWritePosition()))).intValue();
            completeReadingFromBuffer(byteReadChannel, empty, iIntValue, dVar);
            return Integer.valueOf(iIntValue);
        } catch (Throwable th) {
            completeReadingFromBuffer(byteReadChannel, empty, 0, dVar);
            throw th;
        }
    }

    private static final SuspendableReadSession readSessionFor(ByteReadChannel byteReadChannel) {
        if (byteReadChannel instanceof HasReadSession) {
            return ((HasReadSession) byteReadChannel).startReadSession();
        }
        return null;
    }

    public static final Object requestBuffer(ByteReadChannel byteReadChannel, int i10, d<? super Buffer> dVar) {
        SuspendableReadSession suspendableReadSessionStartReadSession = byteReadChannel instanceof SuspendableReadSession ? (SuspendableReadSession) byteReadChannel : byteReadChannel instanceof HasReadSession ? ((HasReadSession) byteReadChannel).startReadSession() : null;
        if (suspendableReadSessionStartReadSession == null) {
            return requestBufferFallback(byteReadChannel, i10, dVar);
        }
        ChunkBuffer chunkBufferRequest = suspendableReadSessionStartReadSession.request(i10 <= 8 ? i10 : 8);
        return chunkBufferRequest != null ? chunkBufferRequest : requestBufferSuspend(suspendableReadSessionStartReadSession, i10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object requestBufferFallback(io.ktor.utils.io.ByteReadChannel r13, int r14, d7.d<? super io.ktor.utils.io.core.internal.ChunkBuffer> r15) {
        /*
            boolean r0 = r15 instanceof io.ktor.utils.io.ReadSessionKt.C20431
            if (r0 == 0) goto L14
            r0 = r15
            io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1 r0 = (io.ktor.utils.io.ReadSessionKt.C20431) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r11 = r0
            goto L1a
        L14:
            io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1 r0 = new io.ktor.utils.io.ReadSessionKt$requestBufferFallback$1
            r0.<init>(r15)
            goto L12
        L1a:
            java.lang.Object r15 = r11.result
            int r0 = r11.label
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            java.lang.Object r13 = r11.L$0
            io.ktor.utils.io.core.internal.ChunkBuffer r13 = (io.ktor.utils.io.core.internal.ChunkBuffer) r13
            k2.c.G(r15)
            goto L69
        L2b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L33:
            k2.c.G(r15)
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r15 = io.ktor.utils.io.core.internal.ChunkBuffer.INSTANCE
            io.ktor.utils.io.pool.ObjectPool r15 = r15.getPool()
            java.lang.Object r15 = r15.borrow()
            io.ktor.utils.io.core.internal.ChunkBuffer r15 = (io.ktor.utils.io.core.internal.ChunkBuffer) r15
            java.nio.ByteBuffer r2 = r15.getMemory()
            int r0 = r15.getWritePosition()
            long r3 = (long) r0
            long r7 = (long) r14
            int r14 = r15.getLimit()
            int r0 = r15.getWritePosition()
            int r14 = r14 - r0
            long r9 = (long) r14
            r11.L$0 = r15
            r11.label = r1
            r5 = 0
            r1 = r13
            java.lang.Object r13 = r1.mo6735peekTolBXzO7A(r2, r3, r5, r7, r9, r11)
            e7.a r14 = e7.a.f15033i
            if (r13 != r14) goto L66
            return r14
        L66:
            r12 = r15
            r15 = r13
            r13 = r12
        L69:
            java.lang.Number r15 = (java.lang.Number) r15
            long r14 = r15.longValue()
            int r14 = (int) r14
            r13.commitWritten(r14)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ReadSessionKt.requestBufferFallback(io.ktor.utils.io.ByteReadChannel, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object requestBufferSuspend(io.ktor.utils.io.SuspendableReadSession r4, int r5, d7.d<? super io.ktor.utils.io.core.Buffer> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ReadSessionKt.C20441
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1 r0 = (io.ktor.utils.io.ReadSessionKt.C20441) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1 r0 = new io.ktor.utils.io.ReadSessionKt$requestBufferSuspend$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.SuspendableReadSession r4 = (io.ktor.utils.io.SuspendableReadSession) r4
            k2.c.G(r6)
            goto L41
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r5 = r4.await(r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L41
            return r6
        L41:
            io.ktor.utils.io.core.internal.ChunkBuffer r4 = r4.request(r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ReadSessionKt.requestBufferSuspend(io.ktor.utils.io.SuspendableReadSession, int, d7.d):java.lang.Object");
    }
}
