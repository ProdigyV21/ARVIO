package j$.sun.nio.cs;

import j$.util.Objects;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends CharsetEncoder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f18088a;

    @Override // java.nio.charset.CharsetEncoder
    public final boolean canEncode(char c10) {
        return c10 <= 255;
    }

    @Override // java.nio.charset.CharsetEncoder
    public final boolean isLegalReplacement(byte[] bArr) {
        return true;
    }

    public b(c cVar) {
        super(cVar, 1.0f, 1.0f);
        e eVar = new e();
        eVar.f18091a = CoderResult.UNDERFLOW;
        this.f18088a = eVar;
    }

    public static int a(char[] cArr, int i10, byte[] bArr, int i11, int i12) {
        int i13 = 0;
        if (i12 <= 0) {
            return 0;
        }
        Objects.requireNonNull(cArr);
        Objects.requireNonNull(bArr);
        if (i10 < 0 || i10 >= cArr.length) {
            throw new ArrayIndexOutOfBoundsException(i10);
        }
        if (i11 < 0 || i11 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i11);
        }
        int i14 = (i10 + i12) - 1;
        if (i14 < 0 || i14 >= cArr.length) {
            throw new ArrayIndexOutOfBoundsException(i14);
        }
        int i15 = (i11 + i12) - 1;
        if (i15 < 0 || i15 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i15);
        }
        while (i13 < i12) {
            int i16 = i10 + 1;
            char c10 = cArr[i10];
            if (c10 > 255) {
                break;
            }
            bArr[i11] = (byte) c10;
            i13++;
            i10 = i16;
            i11++;
        }
        return i13;
    }

    @Override // java.nio.charset.CharsetEncoder
    public final CoderResult encodeLoop(CharBuffer charBuffer, ByteBuffer byteBuffer) {
        CoderResult coderResultUnmappableForLength;
        CoderResult coderResultUnmappableForLength2;
        int i10 = 2;
        if (!charBuffer.hasArray() || !byteBuffer.hasArray()) {
            int iPosition = charBuffer.position();
            while (true) {
                try {
                    if (!charBuffer.hasRemaining()) {
                        coderResultUnmappableForLength = CoderResult.UNDERFLOW;
                        break;
                    }
                    char c10 = charBuffer.get();
                    if (c10 <= 255) {
                        if (!byteBuffer.hasRemaining()) {
                            coderResultUnmappableForLength = CoderResult.OVERFLOW;
                            break;
                        }
                        byteBuffer.put((byte) c10);
                        iPosition++;
                    } else if (this.f18088a.a(c10, charBuffer) < 0) {
                        coderResultUnmappableForLength = this.f18088a.f18091a;
                    } else {
                        if (!this.f18088a.f18092b) {
                            i10 = 1;
                        }
                        coderResultUnmappableForLength = CoderResult.unmappableForLength(i10);
                    }
                } finally {
                }
            }
            return coderResultUnmappableForLength;
        }
        char[] cArrArray = charBuffer.array();
        int iArrayOffset = charBuffer.arrayOffset();
        int iPosition2 = charBuffer.position() + iArrayOffset;
        int iLimit = charBuffer.limit() + iArrayOffset;
        if (iPosition2 > iLimit) {
            iPosition2 = iLimit;
        }
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset2 = byteBuffer.arrayOffset();
        int iPosition3 = byteBuffer.position() + iArrayOffset2;
        int iLimit2 = byteBuffer.limit() + iArrayOffset2;
        if (iPosition3 > iLimit2) {
            iPosition3 = iLimit2;
        }
        int i11 = iLimit2 - iPosition3;
        int i12 = iLimit - iPosition2;
        if (i11 >= i12) {
            i11 = i12;
        }
        try {
            int iA = a(cArrArray, iPosition2, bArrArray, iPosition3, i11);
            int i13 = iPosition2 + iA;
            int i14 = iPosition3 + iA;
            if (iA == i11) {
                coderResultUnmappableForLength2 = i11 < i12 ? CoderResult.OVERFLOW : CoderResult.UNDERFLOW;
            } else if (this.f18088a.b(cArrArray[i13], cArrArray, i13, iLimit) < 0) {
                coderResultUnmappableForLength2 = this.f18088a.f18091a;
            } else {
                if (!this.f18088a.f18092b) {
                    i10 = 1;
                }
                coderResultUnmappableForLength2 = CoderResult.unmappableForLength(i10);
            }
            return coderResultUnmappableForLength2;
        } catch (Throwable th) {
            throw th;
        }
    }
}
