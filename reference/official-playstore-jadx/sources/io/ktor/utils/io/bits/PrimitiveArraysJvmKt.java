package io.ktor.utils.io.bits;

import g8.b;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0013\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a;\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a;\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\f\u001a;\u0010\u0010\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a;\u0010\u0010\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u0011\u001a;\u0010\u0015\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00122\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001a;\u0010\u0015\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00122\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0016\u001a;\u0010\u001a\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00172\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a;\u0010\u001a\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00172\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u001b\u001a;\u0010\u001f\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u001c2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a;\u0010\u001f\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u001c2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010 \u001a;\u0010$\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\t\u001a;\u0010$\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\f\u001a;\u0010&\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u000f\u001a;\u0010&\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\r2\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0011\u001a;\u0010(\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010\u0014\u001a;\u0010(\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010\u0016\u001a;\u0010*\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00172\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u0019\u001a;\u0010*\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00172\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u001b\u001a;\u0010,\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u001c2\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010\u001e\u001a;\u0010,\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u001c2\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010 \u001a\u001c\u0010.\u001a\u00020-*\u00020-2\u0006\u0010\u0002\u001a\u00020\u0001H\u0082\b¢\u0006\u0004\b.\u0010/\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00060"}, d2 = {"Lio/ktor/utils/io/bits/Memory;", "", "offset", "", "destination", "destinationOffset", "count", "Lx6/t0;", "loadShortArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[SII)V", "loadShortArray", "", "(Ljava/nio/ByteBuffer;J[SII)V", "", "loadIntArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[III)V", "loadIntArray", "(Ljava/nio/ByteBuffer;J[III)V", "", "loadLongArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[JII)V", "loadLongArray", "(Ljava/nio/ByteBuffer;J[JII)V", "", "loadFloatArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[FII)V", "loadFloatArray", "(Ljava/nio/ByteBuffer;J[FII)V", "", "loadDoubleArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[DII)V", "loadDoubleArray", "(Ljava/nio/ByteBuffer;J[DII)V", "source", "sourceOffset", "storeShortArray-9zorpBc", "storeShortArray", "storeIntArray-9zorpBc", "storeIntArray", "storeLongArray-9zorpBc", "storeLongArray", "storeFloatArray-9zorpBc", "storeFloatArray", "storeDoubleArray-9zorpBc", "storeDoubleArray", "Ljava/nio/ByteBuffer;", "withOffset", "(Ljava/nio/ByteBuffer;I)Ljava/nio/ByteBuffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PrimitiveArraysJvmKt {
    /* JADX INFO: renamed from: loadDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m6851loadDoubleArray9zorpBc(ByteBuffer byteBuffer, long j10, double[] dArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6850loadDoubleArray9zorpBc(byteBuffer, (int) j10, dArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6852loadDoubleArray9zorpBc$default(ByteBuffer byteBuffer, int i10, double[] dArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = dArr.length - i11;
        }
        m6850loadDoubleArray9zorpBc(byteBuffer, i10, dArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadFloatArray-9zorpBc, reason: not valid java name */
    public static final void m6855loadFloatArray9zorpBc(ByteBuffer byteBuffer, long j10, float[] fArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6854loadFloatArray9zorpBc(byteBuffer, (int) j10, fArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6856loadFloatArray9zorpBc$default(ByteBuffer byteBuffer, int i10, float[] fArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = fArr.length - i11;
        }
        m6854loadFloatArray9zorpBc(byteBuffer, i10, fArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadIntArray-9zorpBc, reason: not valid java name */
    public static final void m6859loadIntArray9zorpBc(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6858loadIntArray9zorpBc(byteBuffer, (int) j10, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6860loadIntArray9zorpBc$default(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = iArr.length - i11;
        }
        m6858loadIntArray9zorpBc(byteBuffer, i10, iArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadLongArray-9zorpBc, reason: not valid java name */
    public static final void m6863loadLongArray9zorpBc(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6862loadLongArray9zorpBc(byteBuffer, (int) j10, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6864loadLongArray9zorpBc$default(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = jArr.length - i11;
        }
        m6862loadLongArray9zorpBc(byteBuffer, i10, jArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadShortArray-9zorpBc, reason: not valid java name */
    public static final void m6867loadShortArray9zorpBc(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6866loadShortArray9zorpBc(byteBuffer, (int) j10, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6868loadShortArray9zorpBc$default(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = sArr.length - i11;
        }
        m6866loadShortArray9zorpBc(byteBuffer, i10, sArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m6871storeDoubleArray9zorpBc(ByteBuffer byteBuffer, long j10, double[] dArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6870storeDoubleArray9zorpBc(byteBuffer, (int) j10, dArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6872storeDoubleArray9zorpBc$default(ByteBuffer byteBuffer, int i10, double[] dArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = dArr.length - i11;
        }
        m6870storeDoubleArray9zorpBc(byteBuffer, i10, dArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeFloatArray-9zorpBc, reason: not valid java name */
    public static final void m6875storeFloatArray9zorpBc(ByteBuffer byteBuffer, long j10, float[] fArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6874storeFloatArray9zorpBc(byteBuffer, (int) j10, fArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6876storeFloatArray9zorpBc$default(ByteBuffer byteBuffer, int i10, float[] fArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = fArr.length - i11;
        }
        m6874storeFloatArray9zorpBc(byteBuffer, i10, fArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeIntArray-9zorpBc, reason: not valid java name */
    public static final void m6879storeIntArray9zorpBc(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6878storeIntArray9zorpBc(byteBuffer, (int) j10, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6880storeIntArray9zorpBc$default(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = iArr.length - i11;
        }
        m6878storeIntArray9zorpBc(byteBuffer, i10, iArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeLongArray-9zorpBc, reason: not valid java name */
    public static final void m6883storeLongArray9zorpBc(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6882storeLongArray9zorpBc(byteBuffer, (int) j10, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6884storeLongArray9zorpBc$default(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = jArr.length - i11;
        }
        m6882storeLongArray9zorpBc(byteBuffer, i10, jArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeShortArray-9zorpBc, reason: not valid java name */
    public static final void m6887storeShortArray9zorpBc(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        m6886storeShortArray9zorpBc(byteBuffer, (int) j10, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6888storeShortArray9zorpBc$default(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = sArr.length - i11;
        }
        m6886storeShortArray9zorpBc(byteBuffer, i10, sArr, i11, i12);
    }

    private static final ByteBuffer withOffset(ByteBuffer byteBuffer, int i10) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        return byteBufferDuplicate;
    }

    /* JADX INFO: renamed from: loadDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6853loadDoubleArray9zorpBc$default(ByteBuffer byteBuffer, long j10, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = dArr.length - i13;
        }
        m6851loadDoubleArray9zorpBc(byteBuffer, j10, dArr, i13, i11);
    }

    /* JADX INFO: renamed from: loadFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6857loadFloatArray9zorpBc$default(ByteBuffer byteBuffer, long j10, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = fArr.length - i13;
        }
        m6855loadFloatArray9zorpBc(byteBuffer, j10, fArr, i13, i11);
    }

    /* JADX INFO: renamed from: loadIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6861loadIntArray9zorpBc$default(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = iArr.length - i13;
        }
        m6859loadIntArray9zorpBc(byteBuffer, j10, iArr, i13, i11);
    }

    /* JADX INFO: renamed from: loadLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6865loadLongArray9zorpBc$default(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = jArr.length - i13;
        }
        m6863loadLongArray9zorpBc(byteBuffer, j10, jArr, i13, i11);
    }

    /* JADX INFO: renamed from: loadShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6869loadShortArray9zorpBc$default(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = sArr.length - i13;
        }
        m6867loadShortArray9zorpBc(byteBuffer, j10, sArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeDoubleArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6873storeDoubleArray9zorpBc$default(ByteBuffer byteBuffer, long j10, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = dArr.length - i13;
        }
        m6871storeDoubleArray9zorpBc(byteBuffer, j10, dArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeFloatArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6877storeFloatArray9zorpBc$default(ByteBuffer byteBuffer, long j10, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = fArr.length - i13;
        }
        m6875storeFloatArray9zorpBc(byteBuffer, j10, fArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeIntArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6881storeIntArray9zorpBc$default(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = iArr.length - i13;
        }
        m6879storeIntArray9zorpBc(byteBuffer, j10, iArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeLongArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6885storeLongArray9zorpBc$default(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = jArr.length - i13;
        }
        m6883storeLongArray9zorpBc(byteBuffer, j10, jArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeShortArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6889storeShortArray9zorpBc$default(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = sArr.length - i13;
        }
        m6887storeShortArray9zorpBc(byteBuffer, j10, sArr, i13, i11);
    }

    /* JADX INFO: renamed from: loadDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m6850loadDoubleArray9zorpBc(ByteBuffer byteBuffer, int i10, double[] dArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asDoubleBuffer().get(dArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadFloatArray-9zorpBc, reason: not valid java name */
    public static final void m6854loadFloatArray9zorpBc(ByteBuffer byteBuffer, int i10, float[] fArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asFloatBuffer().get(fArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadIntArray-9zorpBc, reason: not valid java name */
    public static final void m6858loadIntArray9zorpBc(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asIntBuffer().get(iArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadLongArray-9zorpBc, reason: not valid java name */
    public static final void m6862loadLongArray9zorpBc(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asLongBuffer().get(jArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadShortArray-9zorpBc, reason: not valid java name */
    public static final void m6866loadShortArray9zorpBc(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asShortBuffer().get(sArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeDoubleArray-9zorpBc, reason: not valid java name */
    public static final void m6870storeDoubleArray9zorpBc(ByteBuffer byteBuffer, int i10, double[] dArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asDoubleBuffer().put(dArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeFloatArray-9zorpBc, reason: not valid java name */
    public static final void m6874storeFloatArray9zorpBc(ByteBuffer byteBuffer, int i10, float[] fArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asFloatBuffer().put(fArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeIntArray-9zorpBc, reason: not valid java name */
    public static final void m6878storeIntArray9zorpBc(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asIntBuffer().put(iArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeLongArray-9zorpBc, reason: not valid java name */
    public static final void m6882storeLongArray9zorpBc(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asLongBuffer().put(jArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeShortArray-9zorpBc, reason: not valid java name */
    public static final void m6886storeShortArray9zorpBc(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.asShortBuffer().put(sArr, i11, i12);
    }
}
