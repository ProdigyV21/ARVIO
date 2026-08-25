package io.ktor.utils.io.bits;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a>\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a>\u0010\n\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\f\u001a>\u0010\u000f\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\t\u001a>\u0010\u000f\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\r2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\f\u001a>\u0010\u0013\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00102\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a>\u0010\u0013\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00102\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0014\u001a>\u0010\u0018\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00152\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a>\u0010\u0018\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00152\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0019\u001a>\u0010\u001d\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u001a2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a>\u0010\u001d\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u001a2\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001e\u001a>\u0010\"\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\t\u001a>\u0010\"\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\f\u001a>\u0010$\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\r2\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\t\u001a>\u0010$\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\r2\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\f\u001a>\u0010&\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0012\u001a>\u0010&\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00102\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0014\u001a>\u0010(\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00152\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010\u0017\u001a>\u0010(\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00152\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010\u0019\u001a>\u0010*\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u001a2\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u001c\u001a>\u0010*\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001a2\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lio/ktor/utils/io/bits/Memory;", "", "offset", "", "destination", "destinationOffset", "count", "Lx6/t0;", "loadByteArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[BII)V", "loadByteArray", "", "(Ljava/nio/ByteBuffer;J[BII)V", "Lx6/l0;", "loadUByteArray-KqtU1YU", "loadUByteArray", "Lx6/s0;", "loadUShortArray-m8CCUi4", "(Ljava/nio/ByteBuffer;I[SII)V", "loadUShortArray", "(Ljava/nio/ByteBuffer;J[SII)V", "Lx6/n0;", "loadUIntArray-EM3dPTA", "(Ljava/nio/ByteBuffer;I[III)V", "loadUIntArray", "(Ljava/nio/ByteBuffer;J[III)V", "Lx6/p0;", "loadULongArray-bNlDJKc", "(Ljava/nio/ByteBuffer;I[JII)V", "loadULongArray", "(Ljava/nio/ByteBuffer;J[JII)V", "source", "sourceOffset", "storeByteArray-9zorpBc", "storeByteArray", "storeUByteArray-KqtU1YU", "storeUByteArray", "storeUShortArray-m8CCUi4", "storeUShortArray", "storeUIntArray-EM3dPTA", "storeUIntArray", "storeULongArray-bNlDJKc", "storeULongArray", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PrimiteArraysKt {
    /* JADX INFO: renamed from: loadByteArray-9zorpBc, reason: not valid java name */
    public static final void m6810loadByteArray9zorpBc(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12) {
        MemoryJvmKt.m6765copyTo9zorpBc(byteBuffer, bArr, i10, i12, i11);
    }

    /* JADX INFO: renamed from: loadByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6812loadByteArray9zorpBc$default(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = bArr.length - i11;
        }
        MemoryJvmKt.m6765copyTo9zorpBc(byteBuffer, bArr, i10, i12, i11);
    }

    /* JADX INFO: renamed from: loadUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m6814loadUByteArrayKqtU1YU(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12) {
        MemoryJvmKt.m6765copyTo9zorpBc(byteBuffer, bArr, i10, i12, i11);
    }

    /* JADX INFO: renamed from: loadUByteArray-KqtU1YU$default, reason: not valid java name */
    public static void m6816loadUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = bArr.length - i11;
        }
        MemoryJvmKt.m6765copyTo9zorpBc(byteBuffer, bArr, i10, i12, i11);
    }

    /* JADX INFO: renamed from: loadUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m6818loadUIntArrayEM3dPTA(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12) {
        PrimitiveArraysJvmKt.m6858loadIntArray9zorpBc(byteBuffer, i10, iArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadUIntArray-EM3dPTA$default, reason: not valid java name */
    public static void m6820loadUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = iArr.length - i11;
        }
        PrimitiveArraysJvmKt.m6858loadIntArray9zorpBc(byteBuffer, i10, iArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadULongArray-bNlDJKc, reason: not valid java name */
    public static final void m6822loadULongArraybNlDJKc(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12) {
        PrimitiveArraysJvmKt.m6862loadLongArray9zorpBc(byteBuffer, i10, jArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadULongArray-bNlDJKc$default, reason: not valid java name */
    public static void m6824loadULongArraybNlDJKc$default(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = jArr.length - i11;
        }
        PrimitiveArraysJvmKt.m6862loadLongArray9zorpBc(byteBuffer, i10, jArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m6826loadUShortArraym8CCUi4(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12) {
        PrimitiveArraysJvmKt.m6866loadShortArray9zorpBc(byteBuffer, i10, sArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadUShortArray-m8CCUi4$default, reason: not valid java name */
    public static void m6828loadUShortArraym8CCUi4$default(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = sArr.length - i11;
        }
        PrimitiveArraysJvmKt.m6866loadShortArray9zorpBc(byteBuffer, i10, sArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeByteArray-9zorpBc, reason: not valid java name */
    public static final void m6830storeByteArray9zorpBc(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12) {
        Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i11, i12).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0, i12, i10);
    }

    /* JADX INFO: renamed from: storeByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6832storeByteArray9zorpBc$default(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = bArr.length - i11;
        }
        Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i11, i12).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0, i12, i10);
    }

    /* JADX INFO: renamed from: storeUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m6834storeUByteArrayKqtU1YU(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12) {
        Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i11, i12).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0, i12, i10);
    }

    /* JADX INFO: renamed from: storeUByteArray-KqtU1YU$default, reason: not valid java name */
    public static void m6836storeUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, int i10, byte[] bArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = bArr.length - i11;
        }
        Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i11, i12).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0, i12, i10);
    }

    /* JADX INFO: renamed from: storeUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m6838storeUIntArrayEM3dPTA(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12) {
        PrimitiveArraysJvmKt.m6878storeIntArray9zorpBc(byteBuffer, i10, iArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeUIntArray-EM3dPTA$default, reason: not valid java name */
    public static void m6840storeUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, int i10, int[] iArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = iArr.length - i11;
        }
        PrimitiveArraysJvmKt.m6878storeIntArray9zorpBc(byteBuffer, i10, iArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeULongArray-bNlDJKc, reason: not valid java name */
    public static final void m6842storeULongArraybNlDJKc(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12) {
        PrimitiveArraysJvmKt.m6882storeLongArray9zorpBc(byteBuffer, i10, jArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeULongArray-bNlDJKc$default, reason: not valid java name */
    public static void m6844storeULongArraybNlDJKc$default(ByteBuffer byteBuffer, int i10, long[] jArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = jArr.length - i11;
        }
        PrimitiveArraysJvmKt.m6882storeLongArray9zorpBc(byteBuffer, i10, jArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m6846storeUShortArraym8CCUi4(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12) {
        PrimitiveArraysJvmKt.m6886storeShortArray9zorpBc(byteBuffer, i10, sArr, i11, i12);
    }

    /* JADX INFO: renamed from: storeUShortArray-m8CCUi4$default, reason: not valid java name */
    public static void m6848storeUShortArraym8CCUi4$default(ByteBuffer byteBuffer, int i10, short[] sArr, int i11, int i12, int i13, Object obj) {
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = sArr.length - i11;
        }
        PrimitiveArraysJvmKt.m6886storeShortArray9zorpBc(byteBuffer, i10, sArr, i11, i12);
    }

    /* JADX INFO: renamed from: loadByteArray-9zorpBc, reason: not valid java name */
    public static final void m6811loadByteArray9zorpBc(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11) {
        MemoryJvmKt.m6766copyTo9zorpBc(byteBuffer, bArr, j10, i11, i10);
    }

    /* JADX INFO: renamed from: loadUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m6815loadUByteArrayKqtU1YU(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11) {
        MemoryJvmKt.m6766copyTo9zorpBc(byteBuffer, bArr, j10, i11, i10);
    }

    /* JADX INFO: renamed from: loadUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m6819loadUIntArrayEM3dPTA(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11) {
        PrimitiveArraysJvmKt.m6859loadIntArray9zorpBc(byteBuffer, j10, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadULongArray-bNlDJKc, reason: not valid java name */
    public static final void m6823loadULongArraybNlDJKc(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11) {
        PrimitiveArraysJvmKt.m6863loadLongArray9zorpBc(byteBuffer, j10, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m6827loadUShortArraym8CCUi4(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11) {
        PrimitiveArraysJvmKt.m6867loadShortArray9zorpBc(byteBuffer, j10, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m6839storeUIntArrayEM3dPTA(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11) {
        PrimitiveArraysJvmKt.m6879storeIntArray9zorpBc(byteBuffer, j10, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeULongArray-bNlDJKc, reason: not valid java name */
    public static final void m6843storeULongArraybNlDJKc(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11) {
        PrimitiveArraysJvmKt.m6883storeLongArray9zorpBc(byteBuffer, j10, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: storeUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m6847storeUShortArraym8CCUi4(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11) {
        PrimitiveArraysJvmKt.m6887storeShortArray9zorpBc(byteBuffer, j10, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: loadByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6813loadByteArray9zorpBc$default(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = bArr.length - i13;
        }
        MemoryJvmKt.m6766copyTo9zorpBc(byteBuffer, bArr, j10, i11, i13);
    }

    /* JADX INFO: renamed from: loadUByteArray-KqtU1YU$default, reason: not valid java name */
    public static void m6817loadUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = bArr.length - i13;
        }
        MemoryJvmKt.m6766copyTo9zorpBc(byteBuffer, bArr, j10, i11, i13);
    }

    /* JADX INFO: renamed from: loadUIntArray-EM3dPTA$default, reason: not valid java name */
    public static void m6821loadUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = iArr.length - i13;
        }
        PrimitiveArraysJvmKt.m6859loadIntArray9zorpBc(byteBuffer, j10, iArr, i13, i11);
    }

    /* JADX INFO: renamed from: loadULongArray-bNlDJKc$default, reason: not valid java name */
    public static void m6825loadULongArraybNlDJKc$default(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = jArr.length - i13;
        }
        PrimitiveArraysJvmKt.m6863loadLongArray9zorpBc(byteBuffer, j10, jArr, i13, i11);
    }

    /* JADX INFO: renamed from: loadUShortArray-m8CCUi4$default, reason: not valid java name */
    public static void m6829loadUShortArraym8CCUi4$default(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = sArr.length - i13;
        }
        PrimitiveArraysJvmKt.m6867loadShortArray9zorpBc(byteBuffer, j10, sArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeByteArray-9zorpBc, reason: not valid java name */
    public static final void m6831storeByteArray9zorpBc(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11) {
        Memory.m6748copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0L, i11, j10);
    }

    /* JADX INFO: renamed from: storeUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m6835storeUByteArrayKqtU1YU(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11) {
        Memory.m6748copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0L, i11, j10);
    }

    /* JADX INFO: renamed from: storeUIntArray-EM3dPTA$default, reason: not valid java name */
    public static void m6841storeUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, long j10, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = iArr.length - i13;
        }
        PrimitiveArraysJvmKt.m6879storeIntArray9zorpBc(byteBuffer, j10, iArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeULongArray-bNlDJKc$default, reason: not valid java name */
    public static void m6845storeULongArraybNlDJKc$default(ByteBuffer byteBuffer, long j10, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = jArr.length - i13;
        }
        PrimitiveArraysJvmKt.m6883storeLongArray9zorpBc(byteBuffer, j10, jArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeUShortArray-m8CCUi4$default, reason: not valid java name */
    public static void m6849storeUShortArraym8CCUi4$default(ByteBuffer byteBuffer, long j10, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = sArr.length - i13;
        }
        PrimitiveArraysJvmKt.m6887storeShortArray9zorpBc(byteBuffer, j10, sArr, i13, i11);
    }

    /* JADX INFO: renamed from: storeByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m6833storeByteArray9zorpBc$default(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        if ((i12 & 8) != 0) {
            i11 = bArr.length - i10;
        }
        Memory.m6748copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0L, i11, j10);
    }

    /* JADX INFO: renamed from: storeUByteArray-KqtU1YU$default, reason: not valid java name */
    public static void m6837storeUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, long j10, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        if ((i12 & 8) != 0) {
            i11 = bArr.length - i10;
        }
        Memory.m6748copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN)), byteBuffer, 0L, i11, j10);
    }
}
