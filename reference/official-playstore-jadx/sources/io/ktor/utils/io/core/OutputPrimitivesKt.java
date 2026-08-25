package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.network.sockets.DatagramKt;
import io.ktor.utils.io.bits.Memory;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u0019\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u001b\u0010\n\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\t\u001a\u001b\u0010\u000b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000b\u0010\t\u001a\u0019\u0010\r\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000e\u001a\u001b\u0010\u000f\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u000e\u001a\u0019\u0010\u0011\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0014\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015\u001aH\u0010\u001e\u001a\u00020\u001d*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00072'\u0010\u001c\u001a#\u0012\u0004\u0012\u00020\u0018\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00030\u0017H\u0082\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a0\u0010#\u001a\u00020\u001d*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00072\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00030 H\u0082\b¢\u0006\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lio/ktor/utils/io/core/Output;", "", "value", "Lx6/t0;", "writeShort", "(Lio/ktor/utils/io/core/Output;S)V", "writeShortFallback", "", "writeInt", "(Lio/ktor/utils/io/core/Output;I)V", "writeIntFallback", "writeIntByteByByte", "", "writeLong", "(Lio/ktor/utils/io/core/Output;J)V", "writeLongFallback", "", "writeFloat", "(Lio/ktor/utils/io/core/Output;F)V", "", "writeDouble", "(Lio/ktor/utils/io/core/Output;D)V", "componentSize", "Lkotlin/Function2;", "Lio/ktor/utils/io/bits/Memory;", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "block", "", "writePrimitiveTemplate", "(Lio/ktor/utils/io/core/Output;ILr7/p;)Z", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "writeOperation", "writePrimitiveFallbackTemplate", "(Lio/ktor/utils/io/core/Output;ILr7/l;)Z", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutputPrimitivesKt {
    public static final void writeDouble(Output output, double d4) {
        int tailPosition = output.getTailPosition();
        if (output.getTailEndExclusive() - tailPosition <= 8) {
            writeLongFallback(output, Double.doubleToRawLongBits(d4));
        } else {
            output.setTailPosition$ktor_io(tailPosition + 8);
            output.getTailMemory().putDouble(tailPosition, d4);
        }
    }

    public static final void writeFloat(Output output, float f10) {
        int tailPosition = output.getTailPosition();
        if (output.getTailEndExclusive() - tailPosition <= 4) {
            writeIntFallback(output, Float.floatToRawIntBits(f10));
        } else {
            output.setTailPosition$ktor_io(tailPosition + 4);
            output.getTailMemory().putFloat(tailPosition, f10);
        }
    }

    public static final void writeInt(Output output, int i10) {
        int tailPosition = output.getTailPosition();
        if (output.getTailEndExclusive() - tailPosition <= 4) {
            writeIntFallback(output, i10);
        } else {
            output.setTailPosition$ktor_io(tailPosition + 4);
            output.getTailMemory().putInt(tailPosition, i10);
        }
    }

    private static final void writeIntByteByByte(Output output, int i10) throws InsufficientSpaceException {
        short s10 = (short) (i10 >>> 16);
        output.writeByte((byte) (s10 >>> 8));
        output.writeByte((byte) (s10 & 255));
        short s11 = (short) (i10 & DatagramKt.MAX_DATAGRAM_SIZE);
        output.writeByte((byte) (s11 >>> 8));
        output.writeByte((byte) (s11 & 255));
    }

    private static final void writeIntFallback(Output output, int i10) {
        BufferPrimitivesKt.writeInt((Buffer) output.prepareWriteHead(4), i10);
        output.afterHeadWrite();
    }

    public static final void writeLong(Output output, long j10) {
        int tailPosition = output.getTailPosition();
        if (output.getTailEndExclusive() - tailPosition <= 8) {
            writeLongFallback(output, j10);
        } else {
            output.setTailPosition$ktor_io(tailPosition + 8);
            output.getTailMemory().putLong(tailPosition, j10);
        }
    }

    private static final void writeLongFallback(Output output, long j10) {
        BufferPrimitivesKt.writeLong((Buffer) output.prepareWriteHead(8), j10);
        output.afterHeadWrite();
    }

    private static final boolean writePrimitiveFallbackTemplate(Output output, int i10, l<? super Buffer, t0> lVar) {
        lVar.invoke(output.prepareWriteHead(i10));
        output.afterHeadWrite();
        return true;
    }

    private static final boolean writePrimitiveTemplate(Output output, int i10, p<? super Memory, ? super Integer, t0> pVar) {
        int tailPosition = output.getTailPosition();
        if (output.getTailEndExclusive() - tailPosition <= i10) {
            return false;
        }
        output.setTailPosition$ktor_io(i10 + tailPosition);
        pVar.invoke(Memory.m6745boximpl(output.getTailMemory()), Integer.valueOf(tailPosition));
        return true;
    }

    public static final void writeShort(Output output, short s10) {
        int tailPosition = output.getTailPosition();
        if (output.getTailEndExclusive() - tailPosition <= 2) {
            writeShortFallback(output, s10);
        } else {
            output.setTailPosition$ktor_io(tailPosition + 2);
            output.getTailMemory().putShort(tailPosition, s10);
        }
    }

    private static final void writeShortFallback(Output output, short s10) {
        BufferPrimitivesKt.writeShort((Buffer) output.prepareWriteHead(2), s10);
        output.afterHeadWrite();
    }
}
