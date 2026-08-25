package j$.sun.nio.cs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends CharsetDecoder {
    public a(c cVar) {
        super(cVar, 1.0f, 1.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.nio.ByteBuffer] */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.nio.CharBuffer] */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.nio.ByteBuffer] */
    /* JADX WARN: Type inference failed for: r9v5 */
    @Override // java.nio.charset.CharsetDecoder
    public final CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) throws Throwable {
        CoderResult coderResult;
        CoderResult coderResult2;
        if (byteBuffer.hasArray() && charBuffer.hasArray()) {
            byte[] bArrArray = byteBuffer.array();
            int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
            int iLimit = byteBuffer.limit() + byteBuffer.arrayOffset();
            if (iPosition > iLimit) {
                iPosition = iLimit;
            }
            char[] cArrArray = charBuffer.array();
            int iPosition2 = charBuffer.position() + charBuffer.arrayOffset();
            int iLimit2 = charBuffer.limit() + charBuffer.arrayOffset();
            if (iPosition2 > iLimit2) {
                iPosition2 = iLimit2;
            }
            while (true) {
                if (iPosition < iLimit) {
                    try {
                        byte b10 = bArrArray[iPosition];
                        if (iPosition2 >= iLimit2) {
                            coderResult2 = CoderResult.OVERFLOW;
                            break;
                        }
                        int i10 = iPosition2 + 1;
                        try {
                            cArrArray[iPosition2] = (char) (b10 & 255);
                            iPosition++;
                            iPosition2 = i10;
                        } catch (Throwable th) {
                            th = th;
                            iPosition2 = i10;
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    coderResult2 = CoderResult.UNDERFLOW;
                    break;
                }
            }
            iPosition -= byteBuffer.arrayOffset();
            iPosition2 -= charBuffer.arrayOffset();
            byteBuffer = (CharBuffer) charBuffer.position(iPosition2);
            return coderResult2;
        }
        int iPosition3 = byteBuffer.position();
        while (true) {
            try {
                if (byteBuffer.hasRemaining()) {
                    byte b11 = byteBuffer.get();
                    if (!charBuffer.hasRemaining()) {
                        coderResult = CoderResult.OVERFLOW;
                        break;
                    }
                    charBuffer.put((char) (b11 & 255));
                    iPosition3++;
                } else {
                    coderResult = CoderResult.UNDERFLOW;
                    break;
                }
            } finally {
            }
        }
        return coderResult;
    }
}
