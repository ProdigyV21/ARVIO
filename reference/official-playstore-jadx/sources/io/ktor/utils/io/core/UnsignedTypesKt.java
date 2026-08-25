package io.ktor.utils.io.core;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0005\u001a\u00020\u0004*\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\b\u001a\u00020\u0007*\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\u000b\u001a\u00020\n*\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a6\u0010\u0015\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a6\u0010\u0015\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00162\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a6\u0010\u0015\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00192\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a6\u0010\u0015\u001a\u00020\u0012*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u001c2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\"\u0010#\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0001H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\"\u0010&\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0004H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a\"\u0010)\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0007H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\"\u0010,\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010 \u001a\u00020\nH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a6\u00100\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010-\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a6\u00100\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010-\u001a\u00020\u00162\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a6\u00100\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010-\u001a\u00020\u00192\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a6\u00100\u001a\u00020\u0012*\u00020\u001f2\u0006\u0010-\u001a\u00020\u001c2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b5\u00106\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00067"}, d2 = {"Lio/ktor/utils/io/core/Input;", "Lx6/k0;", "readUByte", "(Lio/ktor/utils/io/core/Input;)B", "Lx6/r0;", "readUShort", "(Lio/ktor/utils/io/core/Input;)S", "Lx6/m0;", "readUInt", "(Lio/ktor/utils/io/core/Input;)I", "Lx6/o0;", "readULong", "(Lio/ktor/utils/io/core/Input;)J", "Lx6/l0;", "dst", "", "offset", "length", "Lx6/t0;", "readFully-o1GoV1E", "(Lio/ktor/utils/io/core/Input;[BII)V", "readFully", "Lx6/s0;", "readFully-Wt3Bwxc", "(Lio/ktor/utils/io/core/Input;[SII)V", "Lx6/n0;", "readFully-o2ZM2JE", "(Lio/ktor/utils/io/core/Input;[III)V", "Lx6/p0;", "readFully-pqYNikA", "(Lio/ktor/utils/io/core/Input;[JII)V", "Lio/ktor/utils/io/core/Output;", "v", "writeUByte-EK-6454", "(Lio/ktor/utils/io/core/Output;B)V", "writeUByte", "writeUShort-i8woANY", "(Lio/ktor/utils/io/core/Output;S)V", "writeUShort", "writeUInt-Qn1smSk", "(Lio/ktor/utils/io/core/Output;I)V", "writeUInt", "writeULong-2TYgG_w", "(Lio/ktor/utils/io/core/Output;J)V", "writeULong", "array", "writeFully-o1GoV1E", "(Lio/ktor/utils/io/core/Output;[BII)V", "writeFully", "writeFully-Wt3Bwxc", "(Lio/ktor/utils/io/core/Output;[SII)V", "writeFully-o2ZM2JE", "(Lio/ktor/utils/io/core/Output;[III)V", "writeFully-pqYNikA", "(Lio/ktor/utils/io/core/Output;[JII)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UnsignedTypesKt {
    /* JADX INFO: renamed from: readFully-Wt3Bwxc, reason: not valid java name */
    public static final void m6975readFullyWt3Bwxc(Input input, short[] sArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-Wt3Bwxc$default, reason: not valid java name */
    public static void m6976readFullyWt3Bwxc$default(Input input, short[] sArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        InputArraysKt.readFully(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o1GoV1E, reason: not valid java name */
    public static final void m6977readFullyo1GoV1E(Input input, byte[] bArr, int i10, int i11) {
        InputArraysKt.readFully(input, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o1GoV1E$default, reason: not valid java name */
    public static void m6978readFullyo1GoV1E$default(Input input, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        InputArraysKt.readFully(input, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o2ZM2JE, reason: not valid java name */
    public static final void m6979readFullyo2ZM2JE(Input input, int[] iArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o2ZM2JE$default, reason: not valid java name */
    public static void m6980readFullyo2ZM2JE$default(Input input, int[] iArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        InputArraysKt.readFully(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-pqYNikA, reason: not valid java name */
    public static final void m6981readFullypqYNikA(Input input, long[] jArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-pqYNikA$default, reason: not valid java name */
    public static void m6982readFullypqYNikA$default(Input input, long[] jArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        InputArraysKt.readFully(input, jArr, i10, i11);
    }

    public static final byte readUByte(Input input) {
        return input.readByte();
    }

    public static final int readUInt(Input input) {
        return InputPrimitivesKt.readInt(input);
    }

    public static final long readULong(Input input) {
        return InputPrimitivesKt.readLong(input);
    }

    public static final short readUShort(Input input) {
        return InputPrimitivesKt.readShort(input);
    }

    /* JADX INFO: renamed from: writeFully-Wt3Bwxc, reason: not valid java name */
    public static final void m6983writeFullyWt3Bwxc(Output output, short[] sArr, int i10, int i11) {
        OutputKt.writeFully(output, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-Wt3Bwxc$default, reason: not valid java name */
    public static void m6984writeFullyWt3Bwxc$default(Output output, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        OutputKt.writeFully(output, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o1GoV1E, reason: not valid java name */
    public static final void m6985writeFullyo1GoV1E(Output output, byte[] bArr, int i10, int i11) {
        OutputKt.writeFully(output, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o1GoV1E$default, reason: not valid java name */
    public static void m6986writeFullyo1GoV1E$default(Output output, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        OutputKt.writeFully(output, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o2ZM2JE, reason: not valid java name */
    public static final void m6987writeFullyo2ZM2JE(Output output, int[] iArr, int i10, int i11) {
        OutputKt.writeFully(output, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o2ZM2JE$default, reason: not valid java name */
    public static void m6988writeFullyo2ZM2JE$default(Output output, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        OutputKt.writeFully(output, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-pqYNikA, reason: not valid java name */
    public static final void m6989writeFullypqYNikA(Output output, long[] jArr, int i10, int i11) {
        OutputKt.writeFully(output, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-pqYNikA$default, reason: not valid java name */
    public static void m6990writeFullypqYNikA$default(Output output, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        OutputKt.writeFully(output, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeUByte-EK-6454, reason: not valid java name */
    public static final void m6991writeUByteEK6454(Output output, byte b10) throws InsufficientSpaceException {
        output.writeByte(b10);
    }

    /* JADX INFO: renamed from: writeUInt-Qn1smSk, reason: not valid java name */
    public static final void m6992writeUIntQn1smSk(Output output, int i10) {
        OutputPrimitivesKt.writeInt(output, i10);
    }

    /* JADX INFO: renamed from: writeULong-2TYgG_w, reason: not valid java name */
    public static final void m6993writeULong2TYgG_w(Output output, long j10) {
        OutputPrimitivesKt.writeLong(output, j10);
    }

    /* JADX INFO: renamed from: writeUShort-i8woANY, reason: not valid java name */
    public static final void m6994writeUShorti8woANY(Output output, short s10) {
        OutputPrimitivesKt.writeShort(output, s10);
    }
}
