package io.ktor.utils.io.internal;

import g8.b;
import io.ktor.utils.io.charsets.UTFKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a/\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a/\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\t\u0010\n\u001a+\u0010\u000b\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u000b\u0010\n\u001a+\u0010\f\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\f\u0010\n\u001a+\u0010\r\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\r\u0010\u0007\u001a+\u0010\u000e\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u000e\u0010\u0007\u001a@\u0010\r\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0082\b¢\u0006\u0004\b\r\u0010\u0013\u001a@\u0010\u000e\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0082\b¢\u0006\u0004\b\u000e\u0010\u0013¨\u0006\u0014"}, d2 = {"Ljava/nio/ByteBuffer;", "", "out", "", "offset", "length", "decodeASCII", "(Ljava/nio/ByteBuffer;[CII)I", "", "decodeASCIILine", "(Ljava/nio/ByteBuffer;[CII)J", "decodeASCIILine_array", "decodeASCIILine_buffer", "decodeASCII3_array", "decodeASCII3_buffer", "Lkotlin/Function1;", "", "", "predicate", "(Ljava/nio/ByteBuffer;[CIILr7/l;)J", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StringsKt {
    public static final int decodeASCII(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) {
        return byteBuffer.hasArray() ? decodeASCII3_array(byteBuffer, cArr, i10, i11) : decodeASCII3_buffer(byteBuffer, cArr, i10, i11);
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

    public static final long decodeASCIILine(ByteBuffer byteBuffer, char[] cArr, int i10, int i11) {
        return byteBuffer.hasArray() ? decodeASCIILine_array(byteBuffer, cArr, i10, i11) : decodeASCIILine_buffer(byteBuffer, cArr, i10, i11);
    }

    public static /* synthetic */ long decodeASCIILine$default(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = cArr.length;
        }
        return decodeASCIILine(byteBuffer, cArr, i10, i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0039 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long decodeASCIILine_array(java.nio.ByteBuffer r11, char[] r12, int r13, int r14) {
        /*
            int r14 = r14 + r13
            byte[] r0 = r11.array()
            int r1 = r11.arrayOffset()
            int r2 = r11.position()
            int r2 = r2 + r1
            int r1 = r11.remaining()
            int r1 = r1 + r2
            int r3 = r12.length
            r4 = 13
            r5 = -1
            r6 = 1
            r7 = 0
            if (r14 > r3) goto L51
            int r3 = r0.length
            if (r1 > r3) goto L51
            r8 = r13
            r3 = r7
        L20:
            if (r2 >= r1) goto L48
            r9 = r0[r2]
            if (r9 < 0) goto L48
            char r9 = (char) r9
            if (r9 != r4) goto L2c
            r3 = r6
        L2a:
            r10 = r3
            goto L37
        L2c:
            r10 = 10
            if (r9 != r10) goto L32
            r3 = r7
            goto L2a
        L32:
            if (r3 == 0) goto L36
            r10 = r7
            goto L37
        L36:
            r10 = r6
        L37:
            if (r10 != 0) goto L3e
            long r13 = g8.b.p(r11, r2, r8, r13, r5)
            goto L58
        L3e:
            if (r8 < r14) goto L41
            goto L48
        L41:
            r12[r8] = r9
            int r8 = r8 + 1
            int r2 = r2 + 1
            goto L20
        L48:
            int r14 = r11.arrayOffset()
            int r2 = r2 - r14
            r11.position(r2)
            goto L53
        L51:
            r8 = r13
            r3 = r7
        L53:
            int r8 = r8 - r13
            long r13 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r8, r7)
        L58:
            r0 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r0 = r0 & r13
            int r0 = (int) r0
            r1 = 32
            if (r0 != r5) goto L82
            long r0 = r13 >> r1
            int r0 = (int) r0
            if (r3 == 0) goto L6e
            int r0 = r0 - r6
            long r11 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r0, r5)
            return r11
        L6e:
            int r1 = r11.position()
            int r1 = r1 + r6
            r11.position(r1)
            if (r0 <= 0) goto L8d
            int r0 = r0 - r6
            char r11 = r12[r0]
            if (r11 != r4) goto L8d
            long r11 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r0, r5)
            return r11
        L82:
            if (r3 == 0) goto L8d
            long r12 = r13 >> r1
            int r12 = (int) r12
            r13 = 2
            long r11 = g8.b.e(r11, r6, r12, r6, r13)
            return r11
        L8d:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.StringsKt.decodeASCIILine_array(java.nio.ByteBuffer, char[], int, int):long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x002d, code lost:
    
        r5 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x002c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long decodeASCIILine_buffer(java.nio.ByteBuffer r7, char[] r8, int r9, int r10) {
        /*
            int r10 = r10 + r9
            int r0 = r8.length
            r1 = 13
            r2 = 1
            r3 = 0
            r4 = r9
            if (r10 > r0) goto L39
            r0 = r3
        La:
            boolean r5 = r7.hasRemaining()
            if (r5 == 0) goto L37
            byte r5 = r7.get()
            if (r5 >= 0) goto L19
        L16:
            r10 = r2
            r5 = r3
            goto L3c
        L19:
            char r5 = (char) r5
            if (r5 != r1) goto L1f
            r0 = r2
        L1d:
            r6 = r0
            goto L2a
        L1f:
            r6 = 10
            if (r5 != r6) goto L25
            r0 = r3
            goto L1d
        L25:
            if (r0 == 0) goto L29
            r6 = r3
            goto L2a
        L29:
            r6 = r2
        L2a:
            if (r6 != 0) goto L2f
            r10 = r2
        L2d:
            r5 = r10
            goto L3c
        L2f:
            if (r4 < r10) goto L32
            goto L16
        L32:
            r8[r4] = r5
            int r4 = r4 + 1
            goto La
        L37:
            r10 = r3
            goto L2d
        L39:
            r10 = r3
            r0 = r10
            r5 = r0
        L3c:
            if (r10 == 0) goto L46
            int r10 = r7.position()
            int r10 = r10 - r2
            r7.position(r10)
        L46:
            int r4 = r4 - r9
            r9 = -1
            if (r5 == 0) goto L4b
            r3 = r9
        L4b:
            long r3 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r4, r3)
            r5 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r5 = r5 & r3
            int r10 = (int) r5
            r5 = 32
            if (r10 != r9) goto L79
            long r5 = r3 >> r5
            int r10 = (int) r5
            if (r0 == 0) goto L65
            int r10 = r10 - r2
            long r7 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r10, r9)
            return r7
        L65:
            int r0 = r7.position()
            int r0 = r0 + r2
            r7.position(r0)
            if (r10 <= 0) goto L84
            int r10 = r10 - r2
            char r7 = r8[r10]
            if (r7 != r1) goto L84
            long r7 = io.ktor.utils.io.charsets.UTFKt.decodeUtf8Result(r10, r9)
            return r7
        L79:
            if (r0 == 0) goto L84
            long r8 = r3 >> r5
            int r8 = (int) r8
            r9 = 2
            long r7 = g8.b.e(r7, r2, r8, r2, r9)
            return r7
        L84:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.StringsKt.decodeASCIILine_buffer(java.nio.ByteBuffer, char[], int, int):long");
    }

    private static final long decodeASCII3_buffer(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l<? super Character, Boolean> lVar) {
        int i12;
        boolean z;
        boolean z5;
        int i13 = i11 + i10;
        if (i13 <= cArr.length) {
            i12 = i10;
            while (byteBuffer.hasRemaining()) {
                byte b10 = byteBuffer.get();
                if (b10 >= 0) {
                    char c10 = (char) b10;
                    if (!((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                        z = true;
                        break;
                    }
                    if (i12 < i13) {
                        cArr[i12] = c10;
                        i12++;
                    }
                }
                z = true;
                z5 = false;
            }
        } else {
            i12 = i10;
        }
        z = false;
        z5 = z;
        if (z) {
            byteBuffer.position(byteBuffer.position() - 1);
        }
        return UTFKt.decodeUtf8Result(i12 - i10, z5 ? -1 : 0);
    }

    private static final long decodeASCII3_array(ByteBuffer byteBuffer, char[] cArr, int i10, int i11, l<? super Character, Boolean> lVar) {
        int i12;
        int i13 = i11 + i10;
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position() + byteBuffer.arrayOffset();
        int iRemaining = byteBuffer.remaining() + iPosition;
        if (i13 > cArr.length || iRemaining > bArrArray.length) {
            i12 = i10;
        } else {
            i12 = i10;
            while (iPosition < iRemaining) {
                byte b10 = bArrArray[iPosition];
                if (b10 < 0) {
                    break;
                }
                char c10 = (char) b10;
                if (!((Boolean) lVar.invoke(Character.valueOf(c10))).booleanValue()) {
                    return b.p(byteBuffer, iPosition, i12, i10, -1);
                }
                if (i12 >= i13) {
                    break;
                }
                cArr[i12] = c10;
                i12++;
                iPosition++;
            }
            byteBuffer.position(iPosition - byteBuffer.arrayOffset());
        }
        return UTFKt.decodeUtf8Result(i12 - i10, 0);
    }
}
