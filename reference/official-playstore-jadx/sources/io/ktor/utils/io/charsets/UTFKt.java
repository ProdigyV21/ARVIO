package io.ktor.utils.io.charsets;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.common.util.concurrent.r0;
import g8.b;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.text.o;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\n\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\b\u0010\t\u001a)\u0010\u000f\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a-\u0010\u0011\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u000e\u001a\u00020\u0000¢\u0006\u0004\b\u0011\u0010\u0010\u001a+\u0010\u0012\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0012\u0010\u0010\u001a+\u0010\u0013\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0013\u0010\u0010\u001a+\u0010\u0014\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0014\u0010\u0010\u001a+\u0010\u0015\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0015\u0010\u0010\u001a@\u0010\u0014\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016H\u0082\b¢\u0006\u0004\b\u0014\u0010\u001a\u001a@\u0010\u0015\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016H\u0082\b¢\u0006\u0004\b\u0015\u0010\u001a\u001a\u0017\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0017\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u001f\u0010\u001d\u001a\u0017\u0010 \u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b \u0010!\u001a\u0017\u0010\"\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\"\u0010!\u001a'\u0010%\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0000H\u0002¢\u0006\u0004\b%\u0010&\u001a\u0017\u0010)\u001a\u00020(2\u0006\u0010'\u001a\u00020\u0000H\u0002¢\u0006\u0004\b)\u0010*\u001a\u0017\u0010-\u001a\u00020(2\u0006\u0010,\u001a\u00020+H\u0002¢\u0006\u0004\b-\u0010.\"\u0014\u0010/\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b/\u00100\"\u0014\u00101\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b1\u00100\"\u0014\u00102\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b2\u00100\"\u0014\u00103\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b3\u00100\"\u0014\u00104\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b4\u00100¨\u00065"}, d2 = {"", "numberOfChars", "requireBytes", "", "decodeUtf8Result", "(II)J", "preDecoded", "result", "decodeUtf8ResultAcc", "(IJ)J", "Ljava/nio/ByteBuffer;", "", "out", "offset", "length", "decodeUTF", "(Ljava/nio/ByteBuffer;[CII)J", "decodeUTF8Line", "decodeUTF8Line_array", "decodeUTF8Line_buffer", "decodeUTF8_array", "decodeUTF8_buffer", "Lkotlin/Function1;", "", "", "predicate", "(Ljava/nio/ByteBuffer;[CIILr7/l;)J", "cp", "isBmpCodePoint", "(I)Z", "codePoint", "isValidCodePoint", "lowSurrogate", "(I)I", "highSurrogate", "arrayLength", "", "indexOutOfBounds", "(III)Ljava/lang/Throwable;", "value", "", "malformedCodePoint", "(I)Ljava/lang/Void;", "", "b", "unsupportedByteCount", "(B)Ljava/lang/Void;", "MaxCodePoint", "I", "MinLowSurrogate", "MinHighSurrogate", "MinSupplementary", "HighSurrogateMagic", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UTFKt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    public static final long decodeUTF(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) {
        int iDecodeASCII = StringsKt.decodeASCII(byteBuffer, cArr, i10, i11);
        return (!byteBuffer.hasRemaining() || iDecodeASCII == i11) ? decodeUtf8Result(iDecodeASCII, 0) : byteBuffer.hasArray() ? decodeUtf8ResultAcc(iDecodeASCII, decodeUTF8_array(byteBuffer, cArr, i10 + iDecodeASCII, i11 - iDecodeASCII)) : decodeUtf8ResultAcc(iDecodeASCII, decodeUTF8_buffer(byteBuffer, cArr, i10 + iDecodeASCII, i11 - iDecodeASCII));
    }

    public static final long decodeUTF8Line(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) {
        return byteBuffer.hasArray() ? decodeUTF8Line_array(byteBuffer, cArr, i10, i11) : decodeUTF8Line_buffer(byteBuffer, cArr, i10, i11);
    }

    public static /* synthetic */ long decodeUTF8Line$default(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = cArr.length;
        }
        return decodeUTF8Line(byteBuffer, cArr, i10, i11);
    }

    private static final long decodeUTF8Line_array(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) throws Throwable {
        int i12;
        long jP;
        boolean z;
        char c10;
        boolean z5;
        boolean z10;
        boolean z11;
        boolean z12;
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (iPosition > iRemaining) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (iRemaining > bArrArray.length) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i13 = i10 + i11;
        if (i13 > cArr.length) {
            throw indexOutOfBounds(i10, i11, cArr.length);
        }
        int i14 = i10;
        boolean z13 = false;
        while (iPosition < iRemaining && i14 < i13) {
            int i15 = iPosition + 1;
            byte b10 = bArrArray[iPosition];
            i12 = 1;
            if (b10 >= 0) {
                char c11 = (char) b10;
                if (c11 == '\r') {
                    z13 = true;
                    z = true;
                } else {
                    if (c11 == '\n') {
                        z13 = false;
                    } else if (!z13) {
                        z = true;
                    }
                    z = false;
                }
                if (!z) {
                    jP = b.p(byteBuffer, iPosition, i14, i10, -1);
                    break;
                }
                cArr[i14] = c11;
                i14++;
                iPosition = i15;
            } else if ((b10 & 224) == 192) {
                if (i15 >= iRemaining) {
                    jP = b.p(byteBuffer, iPosition, i14, i10, 2);
                    break;
                }
                int i16 = iPosition + 2;
                char c12 = (char) ((bArrArray[i15] & 63) | ((b10 & 31) << 6));
                if (c12 == '\r') {
                    z13 = true;
                    z12 = true;
                } else {
                    if (c12 == '\n') {
                        z13 = false;
                    } else if (!z13) {
                        z12 = true;
                    }
                    z12 = false;
                }
                if (!z12) {
                    jP = b.p(byteBuffer, iPosition, i14, i10, -1);
                    break;
                }
                cArr[i14] = c12;
                i14++;
                iPosition = i16;
            } else {
                if ((b10 & 240) != 224) {
                    if ((b10 & 248) != 240) {
                        unsupportedByteCount(b10);
                        throw new KotlinNothingValueException();
                    }
                    if (iRemaining - i15 < 3) {
                        jP = b.p(byteBuffer, iPosition, i14, i10, 4);
                        break;
                    }
                    byte b11 = bArrArray[i15];
                    i15 = iPosition + 4;
                    int i17 = ((bArrArray[iPosition + 2] & 63) << 6) | ((b11 & 63) << 12) | ((b10 & 7) << 18) | (bArrArray[iPosition + 3] & 63);
                    if (!isValidCodePoint(i17)) {
                        malformedCodePoint(i17);
                        throw new KotlinNothingValueException();
                    }
                    if (i13 - i14 < 2) {
                        jP = b.p(byteBuffer, iPosition, i14, i10, 0);
                        break;
                    }
                    char cHighSurrogate = (char) highSurrogate(i17);
                    char cLowSurrogate = (char) lowSurrogate(i17);
                    if (cHighSurrogate == '\r') {
                        z13 = true;
                        z5 = true;
                        c10 = '\n';
                    } else {
                        c10 = '\n';
                        if (cHighSurrogate == '\n') {
                            z13 = false;
                        } else if (!z13) {
                            z5 = true;
                        }
                        z5 = false;
                    }
                    if (z5) {
                        if (cLowSurrogate == '\r') {
                            z13 = true;
                            z10 = true;
                        } else {
                            if (cLowSurrogate == c10) {
                                z13 = false;
                            } else if (!z13) {
                                z10 = true;
                            }
                            z10 = false;
                        }
                        if (z10) {
                            int i18 = i14 + 1;
                            cArr[i14] = cHighSurrogate;
                            i14 += 2;
                            cArr[i18] = cLowSurrogate;
                            iPosition = i15;
                        }
                    }
                    jP = b.p(byteBuffer, iPosition, i14, i10, -1);
                    break;
                }
                if (iRemaining - i15 < 2) {
                    jP = b.p(byteBuffer, iPosition, i14, i10, 3);
                    break;
                }
                byte b12 = bArrArray[i15];
                int i19 = iPosition + 3;
                int i20 = b10 & 15;
                int i21 = (bArrArray[iPosition + 2] & 63) | ((b12 & 63) << 6) | (i20 << 12);
                if (i20 != 0 && !isBmpCodePoint(i21)) {
                    malformedCodePoint(i21);
                    throw new KotlinNothingValueException();
                }
                char c13 = (char) i21;
                if (c13 == '\r') {
                    z13 = true;
                    z11 = true;
                } else {
                    if (c13 == '\n') {
                        z13 = false;
                    } else if (!z13) {
                        z11 = true;
                    }
                    z11 = false;
                }
                if (!z11) {
                    jP = b.p(byteBuffer, iPosition - 1, i14, i10, -1);
                    break;
                }
                cArr[i14] = c13;
                i14++;
                iPosition = i19;
            }
        }
        i12 = 1;
        jP = b.p(byteBuffer, iPosition, i14, i10, 0);
        int i22 = (int) (4294967295L & jP);
        if (i22 == -1) {
            int i23 = (int) (jP >> 32);
            if (z13) {
                return decodeUtf8Result(i23 - 1, -1);
            }
            byteBuffer.position(byteBuffer.position() + 1);
            if (i23 > 0) {
                int i24 = i23 - 1;
                if (cArr[i24] == '\r') {
                    return decodeUtf8Result(i24, -1);
                }
            }
        } else if (i22 == 0 && z13) {
            int i25 = (int) (jP >> 32);
            int i26 = i12;
            return b.e(byteBuffer, i26, i25, i26, 2);
        }
        return jP;
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0036 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0141 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0141 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long decodeUTF8Line_buffer(java.nio.ByteBuffer r17, char[] r18, int r19, int r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.charsets.UTFKt.decodeUTF8Line_buffer(java.nio.ByteBuffer, char[], int, int):long");
    }

    private static final long decodeUTF8_array(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) throws Throwable {
        int i12;
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (iPosition > iRemaining) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (iRemaining > bArrArray.length) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int i13 = i10 + i11;
        if (i13 > cArr.length) {
            throw indexOutOfBounds(i10, i11, cArr.length);
        }
        int i14 = i10;
        while (iPosition < iRemaining && i14 < i13) {
            int i15 = iPosition + 1;
            byte b10 = bArrArray[iPosition];
            if (b10 >= 0) {
                cArr[i14] = (char) b10;
                i14++;
                iPosition = i15;
            } else {
                if ((b10 & 224) == 192) {
                    if (i15 >= iRemaining) {
                        return b.p(byteBuffer, iPosition, i14, i10, 2);
                    }
                    iPosition += 2;
                    byte b11 = bArrArray[i15];
                    i12 = i14 + 1;
                    cArr[i14] = (char) ((b11 & 63) | ((b10 & 31) << 6));
                } else if ((b10 & 240) == 224) {
                    if (iRemaining - i15 < 2) {
                        return b.p(byteBuffer, iPosition, i14, i10, 3);
                    }
                    int i16 = iPosition + 2;
                    iPosition += 3;
                    int i17 = b10 & 15;
                    int i18 = (bArrArray[i15] & 63) << 6;
                    int i19 = (bArrArray[i16] & 63) | i18 | (i17 << 12);
                    if (i17 != 0 && !isBmpCodePoint(i19)) {
                        malformedCodePoint(i19);
                        throw new KotlinNothingValueException();
                    }
                    i12 = i14 + 1;
                    cArr[i14] = (char) i19;
                } else {
                    if ((b10 & 248) != 240) {
                        unsupportedByteCount(b10);
                        throw new KotlinNothingValueException();
                    }
                    if (iRemaining - i15 < 3) {
                        return b.p(byteBuffer, iPosition, i14, i10, 4);
                    }
                    byte b12 = bArrArray[i15];
                    int i20 = iPosition + 4;
                    int i21 = (b12 & 63) << 12;
                    int i22 = i21 | ((b10 & 7) << 18) | ((bArrArray[iPosition + 2] & 63) << 6) | (bArrArray[iPosition + 3] & 63);
                    if (!isValidCodePoint(i22)) {
                        malformedCodePoint(i22);
                        throw new KotlinNothingValueException();
                    }
                    if (i13 - i14 < 2) {
                        return b.p(byteBuffer, iPosition, i14, i10, 0);
                    }
                    int iHighSurrogate = highSurrogate(i22);
                    int iLowSurrogate = lowSurrogate(i22);
                    int i23 = i14 + 1;
                    cArr[i14] = (char) iHighSurrogate;
                    i14 += 2;
                    cArr[i23] = (char) iLowSurrogate;
                    iPosition = i20;
                }
                i14 = i12;
            }
        }
        return b.p(byteBuffer, iPosition, i14, i10, 0);
    }

    private static final long decodeUTF8_buffer(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) throws Throwable {
        int i12 = i10 + i11;
        if (i12 > cArr.length) {
            throw indexOutOfBounds(i10, i11, cArr.length);
        }
        int i13 = i10;
        while (byteBuffer.hasRemaining() && i13 < i12) {
            byte b10 = byteBuffer.get();
            if (b10 >= 0) {
                cArr[i13] = (char) b10;
                i13++;
            } else if ((b10 & 224) == 192) {
                if (byteBuffer.hasRemaining()) {
                    return b.e(byteBuffer, 1, i13, i10, 2);
                }
                cArr[i13] = (char) (((b10 & 31) << 6) | (byteBuffer.get() & 63));
                i13++;
            } else if ((b10 & 240) == 224) {
                if (byteBuffer.remaining() < 2) {
                    return b.e(byteBuffer, 1, i13, i10, 3);
                }
                int i14 = b10 & 15;
                int i15 = ((byteBuffer.get() & 63) << 6) | (i14 << 12) | (byteBuffer.get() & 63);
                if (i14 != 0 && !isBmpCodePoint(i15)) {
                    malformedCodePoint(i15);
                    throw new KotlinNothingValueException();
                }
                cArr[i13] = (char) i15;
                i13++;
            } else {
                if ((b10 & 248) != 240) {
                    unsupportedByteCount(b10);
                    throw new KotlinNothingValueException();
                }
                if (byteBuffer.remaining() < 3) {
                    return b.e(byteBuffer, 1, i13, i10, 4);
                }
                int i16 = ((b10 & 7) << 18) | ((byteBuffer.get() & 63) << 12) | ((byteBuffer.get() & 63) << 6) | (byteBuffer.get() & 63);
                if (!isValidCodePoint(i16)) {
                    malformedCodePoint(i16);
                    throw new KotlinNothingValueException();
                }
                if (i12 - i13 < 2) {
                    return b.e(byteBuffer, 4, i13, i10, 0);
                }
                int iHighSurrogate = highSurrogate(i16);
                int iLowSurrogate = lowSurrogate(i16);
                int i17 = i13 + 1;
                cArr[i13] = (char) iHighSurrogate;
                i13 += 2;
                cArr[i17] = (char) iLowSurrogate;
            }
        }
        return decodeUtf8Result(i13 - i10, 0);
    }

    public static final long decodeUtf8Result(int i10, int i11) {
        return (((long) i11) & 4294967295L) | (((long) i10) << 32);
    }

    public static final long decodeUtf8ResultAcc(int i10, long j10) {
        return decodeUtf8Result(i10 + ((int) (j10 >> 32)), (int) (j10 & 4294967295L));
    }

    private static final int highSurrogate(int i10) {
        return (i10 >>> 10) + HighSurrogateMagic;
    }

    private static final Throwable indexOutOfBounds(int i10, int i11, int i12) {
        return new IndexOutOfBoundsException(i10 + " (offset) + " + i11 + " (length) > " + i12 + " (array.length)");
    }

    private static final boolean isBmpCodePoint(int i10) {
        return (i10 >>> 16) == 0;
    }

    private static final boolean isValidCodePoint(int i10) {
        return i10 <= MaxCodePoint;
    }

    private static final int lowSurrogate(int i10) {
        return (i10 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + MinLowSurrogate;
    }

    private static final Void malformedCodePoint(int i10) {
        throw new IllegalArgumentException("Malformed code-point " + Integer.toHexString(i10) + " found");
    }

    private static final Void unsupportedByteCount(byte b10) {
        StringBuilder sb2 = new StringBuilder("Unsupported byte code, first byte is 0x");
        r0.c(16);
        sb2.append(o.o0(2, Integer.toString(b10 & 255, 16)));
        throw new IllegalStateException(sb2.toString().toString());
    }

    private static final long decodeUTF8_buffer(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l<? super Character, Boolean> lVar) throws Throwable {
        int i12;
        int i13 = i10 + i11;
        if (i13 <= cArr.length) {
            int i14 = i10;
            while (byteBuffer.hasRemaining() && i14 < i13) {
                byte b10 = byteBuffer.get();
                if (b10 >= 0) {
                    char c10 = (char) b10;
                    if (!((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                        return b.e(byteBuffer, 1, i14, i10, -1);
                    }
                    i12 = i14 + 1;
                    cArr[i14] = c10;
                } else if ((b10 & 224) == 192) {
                    if (!byteBuffer.hasRemaining()) {
                        return b.e(byteBuffer, 1, i14, i10, 2);
                    }
                    char c11 = (char) (((b10 & 31) << 6) | (byteBuffer.get() & 63));
                    if (!((Boolean) lVar.invoke(Character.valueOf(c11))).booleanValue()) {
                        return b.e(byteBuffer, 2, i14, i10, -1);
                    }
                    i12 = i14 + 1;
                    cArr[i14] = c11;
                } else if ((b10 & 240) == 224) {
                    if (byteBuffer.remaining() < 2) {
                        return b.e(byteBuffer, 1, i14, i10, 3);
                    }
                    int i15 = b10 & 15;
                    int i16 = ((byteBuffer.get() & 63) << 6) | (i15 << 12) | (byteBuffer.get() & 63);
                    if (i15 != 0 && !isBmpCodePoint(i16)) {
                        malformedCodePoint(i16);
                        throw new KotlinNothingValueException();
                    }
                    char c12 = (char) i16;
                    if (!((Boolean) lVar.invoke(Character.valueOf(c12))).booleanValue()) {
                        return b.e(byteBuffer, 3, i14, i10, -1);
                    }
                    i12 = i14 + 1;
                    cArr[i14] = c12;
                } else if ((b10 & 248) == 240) {
                    if (byteBuffer.remaining() < 3) {
                        return b.e(byteBuffer, 1, i14, i10, 4);
                    }
                    int i17 = ((b10 & 7) << 18) | ((byteBuffer.get() & 63) << 12) | ((byteBuffer.get() & 63) << 6) | (byteBuffer.get() & 63);
                    if (!isValidCodePoint(i17)) {
                        malformedCodePoint(i17);
                        throw new KotlinNothingValueException();
                    }
                    if (i13 - i14 >= 2) {
                        char cHighSurrogate = (char) highSurrogate(i17);
                        char cLowSurrogate = (char) lowSurrogate(i17);
                        if (((Boolean) lVar.invoke(Character.valueOf(cHighSurrogate))).booleanValue() && ((Boolean) lVar.invoke(Character.valueOf(cLowSurrogate))).booleanValue()) {
                            int i18 = i14 + 1;
                            cArr[i14] = cHighSurrogate;
                            i14 += 2;
                            cArr[i18] = cLowSurrogate;
                        } else {
                            return b.e(byteBuffer, 4, i14, i10, -1);
                        }
                    } else {
                        return b.e(byteBuffer, 4, i14, i10, 0);
                    }
                } else {
                    unsupportedByteCount(b10);
                    throw new KotlinNothingValueException();
                }
                i14 = i12;
            }
            return decodeUtf8Result(i14 - i10, 0);
        }
        throw indexOutOfBounds(i10, i11, cArr.length);
    }

    private static final long decodeUTF8_array(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l<? super Character, Boolean> lVar) throws Throwable {
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (iPosition <= iRemaining) {
            if (iRemaining <= bArrArray.length) {
                int i12 = i10 + i11;
                if (i12 <= cArr.length) {
                    int i13 = i10;
                    while (iPosition < iRemaining && i13 < i12) {
                        int i14 = iPosition + 1;
                        byte b10 = bArrArray[iPosition];
                        if (b10 >= 0) {
                            char c10 = (char) b10;
                            if (!((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                                return b.p(byteBuffer, iPosition, i13, i10, -1);
                            }
                            cArr[i13] = c10;
                            i13++;
                            iPosition = i14;
                        } else if ((b10 & 224) == 192) {
                            if (i14 >= iRemaining) {
                                return b.p(byteBuffer, iPosition, i13, i10, 2);
                            }
                            int i15 = iPosition + 2;
                            char c11 = (char) ((bArrArray[i14] & 63) | ((b10 & 31) << 6));
                            if (!((Boolean) lVar.invoke(Character.valueOf(c11))).booleanValue()) {
                                return b.p(byteBuffer, iPosition, i13, i10, -1);
                            }
                            cArr[i13] = c11;
                            i13++;
                            iPosition = i15;
                        } else if ((b10 & 240) == 224) {
                            if (iRemaining - i14 < 2) {
                                return b.p(byteBuffer, iPosition, i13, i10, 3);
                            }
                            int i16 = iPosition + 3;
                            int i17 = b10 & 15;
                            int i18 = (bArrArray[iPosition + 2] & 63) | ((bArrArray[i14] & 63) << 6) | (i17 << 12);
                            if (i17 != 0 && !isBmpCodePoint(i18)) {
                                malformedCodePoint(i18);
                                throw new KotlinNothingValueException();
                            }
                            char c12 = (char) i18;
                            if (!((Boolean) lVar.invoke(Character.valueOf(c12))).booleanValue()) {
                                return b.p(byteBuffer, iPosition - 1, i13, i10, -1);
                            }
                            cArr[i13] = c12;
                            i13++;
                            iPosition = i16;
                        } else {
                            if ((b10 & 248) != 240) {
                                unsupportedByteCount(b10);
                                throw new KotlinNothingValueException();
                            }
                            if (iRemaining - i14 < 3) {
                                return b.p(byteBuffer, iPosition, i13, i10, 4);
                            }
                            byte b11 = bArrArray[i14];
                            int i19 = iPosition + 4;
                            int i20 = ((bArrArray[iPosition + 2] & 63) << 6) | ((b11 & 63) << 12) | ((b10 & 7) << 18) | (bArrArray[iPosition + 3] & 63);
                            if (!isValidCodePoint(i20)) {
                                malformedCodePoint(i20);
                                throw new KotlinNothingValueException();
                            }
                            if (i12 - i13 >= 2) {
                                char cHighSurrogate = (char) highSurrogate(i20);
                                char cLowSurrogate = (char) lowSurrogate(i20);
                                if (((Boolean) lVar.invoke(Character.valueOf(cHighSurrogate))).booleanValue() && ((Boolean) lVar.invoke(Character.valueOf(cLowSurrogate))).booleanValue()) {
                                    int i21 = i13 + 1;
                                    cArr[i13] = cHighSurrogate;
                                    i13 += 2;
                                    cArr[i21] = cLowSurrogate;
                                    iPosition = i19;
                                } else {
                                    return b.p(byteBuffer, iPosition, i13, i10, -1);
                                }
                            } else {
                                return b.p(byteBuffer, iPosition, i13, i10, 0);
                            }
                        }
                    }
                    return b.p(byteBuffer, iPosition, i13, i10, 0);
                }
                throw indexOutOfBounds(i10, i11, cArr.length);
            }
            throw new IllegalArgumentException("Failed requirement.");
        }
        throw new IllegalArgumentException("Failed requirement.");
    }
}
