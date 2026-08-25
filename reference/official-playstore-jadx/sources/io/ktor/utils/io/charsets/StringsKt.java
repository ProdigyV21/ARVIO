package io.ktor.utils.io.charsets;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0007\u001aG\u0010\n\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0080\bø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a/\u0010\n\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\n\u0010\f\u001a+\u0010\r\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\r\u0010\f\u001a+\u0010\u000e\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u000e\u0010\f\u001a@\u0010\r\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0082\b¢\u0006\u0004\b\r\u0010\u000b\u001a@\u0010\u000e\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0082\b¢\u0006\u0004\b\u000e\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"}, d2 = {"Ljava/nio/ByteBuffer;", "", "out", "", "offset", "length", "Lkotlin/Function1;", "", "", "predicate", "decodeASCII", "(Ljava/nio/ByteBuffer;[CIILr7/l;)I", "(Ljava/nio/ByteBuffer;[CII)I", "decodeASCII3_array", "decodeASCII3_buffer", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StringsKt {
    public static final int decodeASCII(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l<? super Character, Boolean> lVar) {
        int i12;
        int i13;
        if (!byteBuffer.hasArray()) {
            int i14 = i11 + i10;
            boolean z = false;
            if (i14 <= cArr.length) {
                i12 = i10;
                while (byteBuffer.hasRemaining()) {
                    byte b10 = byteBuffer.get();
                    if (b10 >= 0 && i12 < i14) {
                        char c10 = (char) b10;
                        if (((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                            cArr[i12] = c10;
                            i12++;
                        }
                    }
                    z = true;
                }
            } else {
                i12 = i10;
            }
            if (z) {
                byteBuffer.position(byteBuffer.position() - 1);
            }
            return i12 - i10;
        }
        int i15 = i11 + i10;
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (i15 > cArr.length || iRemaining > bArrArray.length) {
            i13 = i10;
        } else {
            i13 = i10;
            while (true) {
                if (iPosition < iRemaining && i13 < i15) {
                    byte b11 = bArrArray[iPosition];
                    if (b11 < 0) {
                        break;
                    }
                    char c11 = (char) b11;
                    if (!((Boolean) lVar.invoke(Character.valueOf(c11))).booleanValue()) {
                        iPosition--;
                        break;
                    }
                    cArr[i13] = c11;
                    i13++;
                    iPosition++;
                } else {
                    break;
                }
            }
            byteBuffer.position(iPosition - byteBuffer.arrayOffset());
        }
        return i13 - i10;
    }

    public static /* synthetic */ int decodeASCII$default(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l lVar, int i12, Object obj) {
        int i13;
        int i14;
        boolean z = false;
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = cArr.length;
        }
        if (!byteBuffer.hasArray()) {
            int i15 = i11 + i10;
            if (i15 <= cArr.length) {
                i13 = i10;
                while (byteBuffer.hasRemaining()) {
                    byte b10 = byteBuffer.get();
                    if (b10 >= 0 && i13 < i15) {
                        char c10 = (char) b10;
                        if (((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                            cArr[i13] = c10;
                            i13++;
                        }
                    }
                    z = true;
                }
            } else {
                i13 = i10;
            }
            if (z) {
                byteBuffer.position(byteBuffer.position() - 1);
            }
            return i13 - i10;
        }
        int i16 = i11 + i10;
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (i16 > cArr.length || iRemaining > bArrArray.length) {
            i14 = i10;
        } else {
            i14 = i10;
            while (true) {
                if (iPosition < iRemaining && i14 < i16) {
                    byte b11 = bArrArray[iPosition];
                    if (b11 < 0) {
                        break;
                    }
                    char c11 = (char) b11;
                    if (!((Boolean) lVar.invoke(Character.valueOf(c11))).booleanValue()) {
                        iPosition--;
                        break;
                    }
                    cArr[i14] = c11;
                    i14++;
                    iPosition++;
                } else {
                    break;
                }
            }
            byteBuffer.position(iPosition - byteBuffer.arrayOffset());
        }
        return i14 - i10;
    }

    private static final int decodeASCII3_array(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) {
        int i12;
        int i13 = i11 + i10;
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (i13 > cArr.length || iRemaining > bArrArray.length) {
            i12 = i10;
        } else {
            i12 = i10;
            while (iPosition < iRemaining && i12 < i13) {
                byte b10 = bArrArray[iPosition];
                if (b10 < 0) {
                    break;
                }
                cArr[i12] = (char) b10;
                i12++;
                iPosition++;
            }
            byteBuffer.position(iPosition - byteBuffer.arrayOffset());
        }
        return i12 - i10;
    }

    private static final int decodeASCII3_buffer(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) {
        int i12;
        int i13 = i11 + i10;
        boolean z = false;
        if (i13 <= cArr.length) {
            i12 = i10;
            while (byteBuffer.hasRemaining()) {
                byte b10 = byteBuffer.get();
                if (b10 < 0 || i12 >= i13) {
                    z = true;
                    break;
                }
                cArr[i12] = (char) b10;
                i12++;
            }
        } else {
            i12 = i10;
        }
        if (z) {
            byteBuffer.position(byteBuffer.position() - 1);
        }
        return i12 - i10;
    }

    private static final int decodeASCII3_buffer(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l<? super Character, Boolean> lVar) {
        int i12;
        int i13 = i11 + i10;
        boolean z = false;
        if (i13 <= cArr.length) {
            i12 = i10;
            while (byteBuffer.hasRemaining()) {
                byte b10 = byteBuffer.get();
                if (b10 >= 0 && i12 < i13) {
                    char c10 = (char) b10;
                    if (((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                        cArr[i12] = c10;
                        i12++;
                    }
                }
                z = true;
            }
        } else {
            i12 = i10;
        }
        if (z) {
            byteBuffer.position(byteBuffer.position() - 1);
        }
        return i12 - i10;
    }

    private static final int decodeASCII3_array(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l<? super Character, Boolean> lVar) {
        int i12;
        int i13 = i11 + i10;
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (i13 > cArr.length || iRemaining > bArrArray.length) {
            i12 = i10;
        } else {
            i12 = i10;
            while (true) {
                if (iPosition < iRemaining && i12 < i13) {
                    byte b10 = bArrArray[iPosition];
                    if (b10 < 0) {
                        break;
                    }
                    char c10 = (char) b10;
                    if (!((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                        iPosition--;
                        break;
                    }
                    cArr[i12] = c10;
                    i12++;
                    iPosition++;
                } else {
                    break;
                }
            }
            byteBuffer.position(iPosition - byteBuffer.arrayOffset());
        }
        return i12 - i10;
    }

    public static final int decodeASCII(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) {
        if (byteBuffer.hasArray()) {
            return decodeASCII3_array(byteBuffer, cArr, i10, i11);
        }
        return decodeASCII3_buffer(byteBuffer, cArr, i10, i11);
    }

    public static /* synthetic */ int decodeASCII$default(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = cArr.length;
        }
        return decodeASCII(byteBuffer, cArr, i10, i11);
    }
}
