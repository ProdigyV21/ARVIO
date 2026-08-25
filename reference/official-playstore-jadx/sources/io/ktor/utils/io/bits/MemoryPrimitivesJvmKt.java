package io.ktor.utils.io.bits;

import g8.b;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\"\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\"\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\b\u001a\"\u0010\u000b\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\f\u001a\"\u0010\u000f\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a\"\u0010\u000f\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u0010\u001a\"\u0010\u0014\u001a\u00020\u0011*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013\u001a\"\u0010\u0014\u001a\u00020\u0011*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0015\u001a\"\u0010\u0019\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a\"\u0010\u0019\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u001a\u001a*\u0010\u001f\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a*\u0010\u001f\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010 \u001a*\u0010#\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0003H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\"\u001a*\u0010#\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0003H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010$\u001a*\u0010'\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u001a*\u0010'\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010(\u001a*\u0010+\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0011H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\u001a*\u0010+\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0011H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010,\u001a*\u0010/\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u0016H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u001a*\u0010/\u001a\u00020\u001c*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0016H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u00100\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00061"}, d2 = {"Lio/ktor/utils/io/bits/Memory;", "", "offset", "", "loadShortAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)S", "loadShortAt", "", "(Ljava/nio/ByteBuffer;J)S", "loadIntAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)I", "loadIntAt", "(Ljava/nio/ByteBuffer;J)I", "loadLongAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)J", "loadLongAt", "(Ljava/nio/ByteBuffer;J)J", "", "loadFloatAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)F", "loadFloatAt", "(Ljava/nio/ByteBuffer;J)F", "", "loadDoubleAt-eY85DW0", "(Ljava/nio/ByteBuffer;I)D", "loadDoubleAt", "(Ljava/nio/ByteBuffer;J)D", "value", "Lx6/t0;", "storeIntAt-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "storeIntAt", "(Ljava/nio/ByteBuffer;JI)V", "storeShortAt-62zg_DM", "(Ljava/nio/ByteBuffer;IS)V", "storeShortAt", "(Ljava/nio/ByteBuffer;JS)V", "storeLongAt-62zg_DM", "(Ljava/nio/ByteBuffer;IJ)V", "storeLongAt", "(Ljava/nio/ByteBuffer;JJ)V", "storeFloatAt-62zg_DM", "(Ljava/nio/ByteBuffer;IF)V", "storeFloatAt", "(Ljava/nio/ByteBuffer;JF)V", "storeDoubleAt-62zg_DM", "(Ljava/nio/ByteBuffer;ID)V", "storeDoubleAt", "(Ljava/nio/ByteBuffer;JD)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MemoryPrimitivesJvmKt {
    /* JADX INFO: renamed from: loadDoubleAt-eY85DW0, reason: not valid java name */
    public static final double m6778loadDoubleAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getDouble(i10);
    }

    /* JADX INFO: renamed from: loadFloatAt-eY85DW0, reason: not valid java name */
    public static final float m6780loadFloatAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getFloat(i10);
    }

    /* JADX INFO: renamed from: loadIntAt-eY85DW0, reason: not valid java name */
    public static final int m6782loadIntAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getInt(i10);
    }

    /* JADX INFO: renamed from: loadLongAt-eY85DW0, reason: not valid java name */
    public static final long m6784loadLongAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getLong(i10);
    }

    /* JADX INFO: renamed from: loadShortAt-eY85DW0, reason: not valid java name */
    public static final short m6786loadShortAteY85DW0(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.getShort(i10);
    }

    /* JADX INFO: renamed from: storeDoubleAt-62zg_DM, reason: not valid java name */
    public static final void m6788storeDoubleAt62zg_DM(ByteBuffer byteBuffer, int i10, double d4) {
        byteBuffer.putDouble(i10, d4);
    }

    /* JADX INFO: renamed from: storeFloatAt-62zg_DM, reason: not valid java name */
    public static final void m6790storeFloatAt62zg_DM(ByteBuffer byteBuffer, int i10, float f10) {
        byteBuffer.putFloat(i10, f10);
    }

    /* JADX INFO: renamed from: storeIntAt-62zg_DM, reason: not valid java name */
    public static final void m6792storeIntAt62zg_DM(ByteBuffer byteBuffer, int i10, int i11) {
        byteBuffer.putInt(i10, i11);
    }

    /* JADX INFO: renamed from: storeLongAt-62zg_DM, reason: not valid java name */
    public static final void m6794storeLongAt62zg_DM(ByteBuffer byteBuffer, int i10, long j10) {
        byteBuffer.putLong(i10, j10);
    }

    /* JADX INFO: renamed from: storeShortAt-62zg_DM, reason: not valid java name */
    public static final void m6796storeShortAt62zg_DM(ByteBuffer byteBuffer, int i10, short s10) {
        byteBuffer.putShort(i10, s10);
    }

    /* JADX INFO: renamed from: loadDoubleAt-eY85DW0, reason: not valid java name */
    public static final double m6779loadDoubleAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getDouble((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: loadFloatAt-eY85DW0, reason: not valid java name */
    public static final float m6781loadFloatAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getFloat((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: loadIntAt-eY85DW0, reason: not valid java name */
    public static final int m6783loadIntAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getInt((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: loadLongAt-eY85DW0, reason: not valid java name */
    public static final long m6785loadLongAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getLong((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: loadShortAt-eY85DW0, reason: not valid java name */
    public static final short m6787loadShortAteY85DW0(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.getShort((int) j10);
        }
        throw b.j(j10, "offset");
    }

    /* JADX INFO: renamed from: storeDoubleAt-62zg_DM, reason: not valid java name */
    public static final void m6789storeDoubleAt62zg_DM(ByteBuffer byteBuffer, long j10, double d4) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putDouble((int) j10, d4);
    }

    /* JADX INFO: renamed from: storeFloatAt-62zg_DM, reason: not valid java name */
    public static final void m6791storeFloatAt62zg_DM(ByteBuffer byteBuffer, long j10, float f10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putFloat((int) j10, f10);
    }

    /* JADX INFO: renamed from: storeIntAt-62zg_DM, reason: not valid java name */
    public static final void m6793storeIntAt62zg_DM(ByteBuffer byteBuffer, long j10, int i10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putInt((int) j10, i10);
    }

    /* JADX INFO: renamed from: storeLongAt-62zg_DM, reason: not valid java name */
    public static final void m6795storeLongAt62zg_DM(ByteBuffer byteBuffer, long j10, long j11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putLong((int) j10, j11);
    }

    /* JADX INFO: renamed from: storeShortAt-62zg_DM, reason: not valid java name */
    public static final void m6797storeShortAt62zg_DM(ByteBuffer byteBuffer, long j10, short s10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        byteBuffer.putShort((int) j10, s10);
    }
}
