package io.ktor.utils.io;

import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001f\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a#\u0010\r\u001a\u00020\f*\u00020\u00002\n\u0010\u000b\u001a\u00060\tj\u0002`\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0003\u001a\u0011\u0010\u0011\u001a\u00020\f*\u00020\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0017\u0010\u0014\u001a\u00020\u0013*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0003\u001a\u001f\u0010\u0016\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0013H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001f\u0010\u001a\u001a\u00020\u0019*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u001b\u001a\u001f\u0010\u001d\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u001cH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a)\u0010 \u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/core/ByteReadPacket;", "readRemaining", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "dst", "Lx6/t0;", "readFully", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/internal/ChunkBuffer;Ld7/d;)Ljava/lang/Object;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "", "readUTF8LineTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;Ld7/d;)Ljava/lang/Object;", "", "readUTF8Line", "cancel", "(Lio/ktor/utils/io/ByteReadChannel;)Z", "", "discard", "n", "discardExact", "(Lio/ktor/utils/io/ByteReadChannel;JLd7/d;)Ljava/lang/Object;", "", "", "readAvailable", "(Lio/ktor/utils/io/ByteReadChannel;[BLd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteWriteChannel;", "copyTo", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "limit", "copyAndClose", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLd7/d;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteReadChannelKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteReadChannelKt", f = "ByteReadChannel.kt", l = {261}, m = "copyAndClose")
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
            return ByteReadChannelKt.copyAndClose(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelKt$discardExact$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    @e(c = "io.ktor.utils.io.ByteReadChannelKt", f = "ByteReadChannel.kt", l = {232}, m = "discardExact")
    public static final class C20311 extends c {
        long J$0;
        int label;
        /* synthetic */ Object result;

        public C20311(d<? super C20311> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelKt.discardExact(null, 0L, this);
        }
    }

    public static final boolean cancel(ByteReadChannel byteReadChannel) {
        return byteReadChannel.cancel(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyAndClose(io.ktor.utils.io.ByteReadChannel r4, io.ktor.utils.io.ByteWriteChannel r5, long r6, d7.d<? super java.lang.Long> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteReadChannelKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1 r0 = (io.ktor.utils.io.ByteReadChannelKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1 r0 = new io.ktor.utils.io.ByteReadChannelKt$copyAndClose$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
            k2.c.G(r8)
            goto L42
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r8)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r8 = io.ktor.utils.io.ByteReadChannelJVMKt.copyTo(r4, r5, r6, r0)
            e7.a r4 = e7.a.f15033i
            if (r8 != r4) goto L42
            return r4
        L42:
            java.lang.Number r8 = (java.lang.Number) r8
            long r6 = r8.longValue()
            io.ktor.utils.io.ByteWriteChannelKt.close(r5)
            java.lang.Long r4 = new java.lang.Long
            r4.<init>(r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelKt.copyAndClose(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object copyAndClose$default(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = Long.MAX_VALUE;
        }
        return copyAndClose(byteReadChannel, byteWriteChannel, j10, dVar);
    }

    public static final Object copyTo(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, d<? super Long> dVar) {
        return ByteReadChannelJVMKt.copyTo(byteReadChannel, byteWriteChannel, Long.MAX_VALUE, dVar);
    }

    public static final Object discard(ByteReadChannel byteReadChannel, d<? super Long> dVar) {
        return byteReadChannel.discard(Long.MAX_VALUE, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object discardExact(io.ktor.utils.io.ByteReadChannel r4, long r5, d7.d<? super x6.t0> r7) throws java.io.EOFException {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteReadChannelKt.C20311
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteReadChannelKt$discardExact$1 r0 = (io.ktor.utils.io.ByteReadChannelKt.C20311) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteReadChannelKt$discardExact$1 r0 = new io.ktor.utils.io.ByteReadChannelKt$discardExact$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            long r5 = r0.J$0
            k2.c.G(r7)
            goto L3f
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            k2.c.G(r7)
            r0.J$0 = r5
            r0.label = r2
            java.lang.Object r7 = r4.discard(r5, r0)
            e7.a r4 = e7.a.f15033i
            if (r7 != r4) goto L3f
            return r4
        L3f:
            java.lang.Number r7 = (java.lang.Number) r7
            long r0 = r7.longValue()
            int r4 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r4 != 0) goto L4c
            x6.t0 r4 = x6.t0.f22605a
            return r4
        L4c:
            java.io.EOFException r4 = new java.io.EOFException
            java.lang.String r7 = "Unable to discard "
            java.lang.String r0 = " bytes"
            java.lang.String r5 = androidx.compose.material3.d.k(r5, r7, r0)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelKt.discardExact(io.ktor.utils.io.ByteReadChannel, long, d7.d):java.lang.Object");
    }

    private static final Object discardExact$$forInline(ByteReadChannel byteReadChannel, long j10, d<? super t0> dVar) throws EOFException {
        if (((Number) byteReadChannel.discard(j10, dVar)).longValue() == j10) {
            return t0.f22605a;
        }
        throw new EOFException(androidx.compose.material3.d.k(j10, "Unable to discard ", " bytes"));
    }

    public static final Object readAvailable(ByteReadChannel byteReadChannel, byte[] bArr, d<? super Integer> dVar) {
        return byteReadChannel.readAvailable(bArr, 0, bArr.length, dVar);
    }

    public static final Object readFully(ByteReadChannel byteReadChannel, byte[] bArr, d<? super t0> dVar) {
        Object fully = byteReadChannel.readFully(bArr, 0, bArr.length, dVar);
        return fully == a.f15033i ? fully : t0.f22605a;
    }

    public static final Object readRemaining(ByteReadChannel byteReadChannel, d<? super ByteReadPacket> dVar) {
        return byteReadChannel.readRemaining(Long.MAX_VALUE, dVar);
    }

    public static final Object readUTF8Line(ByteReadChannel byteReadChannel, d<? super String> dVar) {
        return byteReadChannel.readUTF8Line(Integer.MAX_VALUE, dVar);
    }

    public static final Object readUTF8LineTo(ByteReadChannel byteReadChannel, Appendable appendable, d<? super Boolean> dVar) {
        return byteReadChannel.readUTF8LineTo(appendable, Integer.MAX_VALUE, dVar);
    }

    public static final Object readFully(ByteReadChannel byteReadChannel, ChunkBuffer chunkBuffer, d<? super t0> dVar) {
        Object fully = byteReadChannel.readFully(chunkBuffer, chunkBuffer.getLimit() - chunkBuffer.getWritePosition(), dVar);
        return fully == a.f15033i ? fully : t0.f22605a;
    }
}
