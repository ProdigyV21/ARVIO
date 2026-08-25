package io.ktor.utils.io.core;

import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import java.io.EOFException;
import kotlin.Metadata;
import r7.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086\b¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0086\b¢\u0006\u0004\b\u0004\u0010\u0003\u001at\u0010\u000e\u001a\u00020\n*\u00020\u00002K\u0010\r\u001aG\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\n0\u0005H\u0086\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001at\u0010\u0010\u001a\u00020\n*\u00020\u00002K\u0010\r\u001aG\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\n0\u0005H\u0086\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u0010\u0010\u000f\u001a\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001f\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0017\u0010\u0015\u001a\u001f\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0019\u0010\u0015\u001a\u001b\u0010\u001b\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001b\u0010\u001d\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001d\u0010\u001c\u001a\u001b\u0010 \u001a\u00020\u001f*\u00020\u00002\u0006\u0010\u001e\u001a\u00020\nH\u0000¢\u0006\u0004\b \u0010!\u001a\u001b\u0010\"\u001a\u00020\u001f*\u00020\u00002\u0006\u0010\u001e\u001a\u00020\nH\u0000¢\u0006\u0004\b\"\u0010!\u001a\u001b\u0010#\u001a\u00020\u001f*\u00020\u00002\u0006\u0010\u001e\u001a\u00020\nH\u0000¢\u0006\u0004\b#\u0010!\u001a\u001b\u0010%\u001a\u00020\u001f*\u00020\u00002\u0006\u0010$\u001a\u00020\nH\u0000¢\u0006\u0004\b%\u0010!\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "", "canRead", "(Lio/ktor/utils/io/core/Buffer;)Z", "canWrite", "Lkotlin/Function3;", "Lio/ktor/utils/io/bits/Memory;", "Lx6/y;", ContentDisposition.Parameters.Name, "memory", "", TtmlNode.START, "endExclusive", "block", "read", "(Lio/ktor/utils/io/core/Buffer;Lr7/q;)I", "write", "count", "readRemaining", "", "discardFailed", "(II)Ljava/lang/Void;", "writeRemaining", "commitWrittenFailed", "rewindRemaining", "rewindFailed", "startGap", "startGapReservationFailedDueToLimit", "(Lio/ktor/utils/io/core/Buffer;I)Ljava/lang/Void;", "startGapReservationFailed", "endGap", "Lx6/t0;", "endGapReservationFailedDueToCapacity", "(Lio/ktor/utils/io/core/Buffer;I)V", "endGapReservationFailedDueToStartGap", "endGapReservationFailedDueToContent", ContentDisposition.Parameters.Size, "restoreStartGap", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BufferKt {
    public static final boolean canRead(Buffer buffer) {
        return buffer.getWritePosition() > buffer.getReadPosition();
    }

    public static final boolean canWrite(Buffer buffer) {
        return buffer.getLimit() > buffer.getWritePosition();
    }

    public static final Void commitWrittenFailed(int i10, int i11) {
        throw new EOFException(c.s("Unable to discard ", i10, " bytes: only ", i11, " available for writing"));
    }

    public static final Void discardFailed(int i10, int i11) {
        throw new EOFException(c.s("Unable to discard ", i10, " bytes: only ", i11, " available for reading"));
    }

    public static final void endGapReservationFailedDueToCapacity(Buffer buffer, int i10) {
        StringBuilder sbS = a0.c.s(i10, "End gap ", " is too big: capacity is ");
        sbS.append(buffer.getCapacity());
        throw new IllegalArgumentException(sbS.toString());
    }

    public static final void endGapReservationFailedDueToContent(Buffer buffer, int i10) {
        StringBuilder sbS = a0.c.s(i10, "Unable to reserve end gap ", ": there are already ");
        sbS.append(buffer.getWritePosition() - buffer.getReadPosition());
        sbS.append(" content bytes at offset ");
        sbS.append(buffer.getReadPosition());
        throw new IllegalArgumentException(sbS.toString());
    }

    public static final void endGapReservationFailedDueToStartGap(Buffer buffer, int i10) {
        StringBuilder sbS = a0.c.s(i10, "End gap ", " is too big: there are already ");
        sbS.append(buffer.getStartGap());
        sbS.append(" bytes reserved in the beginning");
        throw new IllegalArgumentException(sbS.toString());
    }

    public static final int read(Buffer buffer, q<? super Memory, ? super Integer, ? super Integer, Integer> qVar) {
        int iIntValue = ((Number) qVar.invoke(Memory.m6745boximpl(buffer.getMemory()), Integer.valueOf(buffer.getReadPosition()), Integer.valueOf(buffer.getWritePosition()))).intValue();
        buffer.discardExact(iIntValue);
        return iIntValue;
    }

    public static final void restoreStartGap(Buffer buffer, int i10) {
        buffer.releaseStartGap$ktor_io(buffer.getReadPosition() - i10);
    }

    public static final Void rewindFailed(int i10, int i11) {
        throw new IllegalArgumentException(c.s("Unable to rewind ", i10, " bytes: only ", i11, " could be rewinded"));
    }

    public static final Void startGapReservationFailed(Buffer buffer, int i10) {
        StringBuilder sbS = a0.c.s(i10, "Unable to reserve ", " start gap: there are already ");
        sbS.append(buffer.getWritePosition() - buffer.getReadPosition());
        sbS.append(" content bytes starting at offset ");
        sbS.append(buffer.getReadPosition());
        throw new IllegalStateException(sbS.toString());
    }

    public static final Void startGapReservationFailedDueToLimit(Buffer buffer, int i10) {
        if (i10 > buffer.getCapacity()) {
            StringBuilder sbS = a0.c.s(i10, "Start gap ", " is bigger than the capacity ");
            sbS.append(buffer.getCapacity());
            throw new IllegalArgumentException(sbS.toString());
        }
        StringBuilder sbS2 = a0.c.s(i10, "Unable to reserve ", " start gap: there are already ");
        sbS2.append(buffer.getCapacity() - buffer.getLimit());
        sbS2.append(" bytes reserved in the end");
        throw new IllegalStateException(sbS2.toString());
    }

    public static final int write(Buffer buffer, q<? super Memory, ? super Integer, ? super Integer, Integer> qVar) {
        int iIntValue = ((Number) qVar.invoke(Memory.m6745boximpl(buffer.getMemory()), Integer.valueOf(buffer.getWritePosition()), Integer.valueOf(buffer.getLimit()))).intValue();
        buffer.commitWritten(iIntValue);
        return iIntValue;
    }
}
