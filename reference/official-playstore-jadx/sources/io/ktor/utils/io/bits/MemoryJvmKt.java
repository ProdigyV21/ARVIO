package io.ktor.utils.io.bits;

import g8.b;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0005\n\u0002\b\u0006\u001a7\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a7\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\f\u001a'\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a'\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u0010\u001a'\u0010\n\u001a\u00020\u0007*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u000f\u001a\u0014\u0010\u0012\u001a\u00020\r*\u00020\rH\u0082\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0014\u0010\u0014\u001a\u00020\r*\u00020\rH\u0082\b¢\u0006\u0004\b\u0014\u0010\u0013\u001a\u0014\u0010\u0015\u001a\u00020\r*\u00020\rH\u0082\b¢\u0006\u0004\b\u0015\u0010\u0013\u001a#\u0010\u0016\u001a\u00020\r*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a/\u0010\u001d\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0019ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a/\u0010\u001d\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0019ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lio/ktor/utils/io/bits/Memory;", "", "destination", "", "offset", "length", "destinationOffset", "Lx6/t0;", "copyTo-9zorpBc", "(Ljava/nio/ByteBuffer;[BIII)V", "copyTo", "", "(Ljava/nio/ByteBuffer;[BJII)V", "Ljava/nio/ByteBuffer;", "copyTo-62zg_DM", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;I)V", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;J)V", "copyTo-SG11BkQ", "myDuplicate", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "mySlice", "suppressNullCheck", "sliceSafe", "(Ljava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;", "count", "", "value", "fill-JT6ljtQ", "(Ljava/nio/ByteBuffer;JJB)V", "fill", "(Ljava/nio/ByteBuffer;IIB)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MemoryJvmKt {
    /* JADX INFO: renamed from: copyTo-62zg_DM, reason: not valid java name */
    public static final void m6763copyTo62zg_DM(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10) {
        int iRemaining = byteBuffer2.remaining();
        if (byteBuffer.hasArray() && !byteBuffer.isReadOnly() && byteBuffer2.hasArray() && !byteBuffer2.isReadOnly()) {
            int iPosition = byteBuffer2.position();
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + i10, byteBuffer2.array(), byteBuffer2.arrayOffset() + iPosition, iRemaining);
            byteBuffer2.position(iPosition + iRemaining);
        } else {
            ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
            byteBufferDuplicate.limit(iRemaining + i10);
            byteBufferDuplicate.position(i10);
            byteBuffer2.put(byteBufferDuplicate);
        }
    }

    /* JADX INFO: renamed from: copyTo-9zorpBc, reason: not valid java name */
    public static final void m6765copyTo9zorpBc(ByteBuffer byteBuffer, byte[] bArr, int i10, int i11, int i12) {
        if (!byteBuffer.hasArray() || byteBuffer.isReadOnly()) {
            byteBuffer.duplicate().get(bArr, i12, i11);
        } else {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + i10, bArr, i12, i11);
        }
    }

    /* JADX INFO: renamed from: copyTo-SG11BkQ, reason: not valid java name */
    public static final void m6767copyToSG11BkQ(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10) {
        if (!byteBuffer.hasArray() || byteBuffer.isReadOnly()) {
            sliceSafe(byteBuffer2, i10, byteBuffer.remaining()).put(byteBuffer);
            return;
        }
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining();
        Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArrArray, iPosition, iRemaining).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer2, 0, iRemaining, i10);
        byteBuffer.position(byteBuffer.limit());
    }

    /* JADX INFO: renamed from: fill-JT6ljtQ, reason: not valid java name */
    public static final void m6769fillJT6ljtQ(ByteBuffer byteBuffer, long j10, long j11, byte b10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        int i10 = (int) j10;
        if (j11 >= 2147483647L) {
            throw b.j(j11, "count");
        }
        m6768fillJT6ljtQ(byteBuffer, i10, (int) j11, b10);
    }

    private static final ByteBuffer myDuplicate(ByteBuffer byteBuffer) {
        return byteBuffer.duplicate();
    }

    private static final ByteBuffer mySlice(ByteBuffer byteBuffer) {
        return byteBuffer.slice();
    }

    public static final ByteBuffer sliceSafe(ByteBuffer byteBuffer, int i10, int i11) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.limit(i10 + i11);
        return byteBufferDuplicate.slice();
    }

    private static final ByteBuffer suppressNullCheck(ByteBuffer byteBuffer) {
        return byteBuffer;
    }

    /* JADX INFO: renamed from: copyTo-9zorpBc, reason: not valid java name */
    public static final void m6766copyTo9zorpBc(ByteBuffer byteBuffer, byte[] bArr, long j10, int i10, int i11) {
        if (j10 < 2147483647L) {
            m6765copyTo9zorpBc(byteBuffer, bArr, (int) j10, i10, i11);
            return;
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: fill-JT6ljtQ, reason: not valid java name */
    public static final void m6768fillJT6ljtQ(ByteBuffer byteBuffer, int i10, int i11, byte b10) {
        int i12 = i11 + i10;
        while (i10 < i12) {
            byteBuffer.put(i10, b10);
            i10++;
        }
    }

    /* JADX INFO: renamed from: copyTo-62zg_DM, reason: not valid java name */
    public static final void m6764copyTo62zg_DM(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, long j10) {
        if (j10 < 2147483647L) {
            m6763copyTo62zg_DM(byteBuffer, byteBuffer2, (int) j10);
            return;
        }
        throw b.j(j10, "offset");
    }
}
