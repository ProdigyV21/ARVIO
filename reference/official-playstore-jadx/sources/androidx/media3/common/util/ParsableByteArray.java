package androidx.media3.common.util;

import com.google.common.collect.w1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import m2.f0;
import qb.l;

/* JADX INFO: loaded from: classes3.dex */
@x4.b
public final class ParsableByteArray {
    public static final int INVALID_CODE_POINT = 1114112;
    private byte[] data;
    private int limit;
    private int position;
    private static final char[] CR_AND_LF = {'\r', '\n'};
    private static final char[] LF = {'\n'};
    private static final w1 SUPPORTED_CHARSETS_FOR_READLINE = w1.k(5, StandardCharsets.US_ASCII, StandardCharsets.UTF_8, StandardCharsets.UTF_16, StandardCharsets.UTF_16BE, StandardCharsets.UTF_16LE);
    private static final AtomicBoolean shouldEnforceLimitOnLegacyMethods = new AtomicBoolean();

    public ParsableByteArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    private static int decodeUtf8CodeUnit(int i10, int i11, int i12, int i13) {
        byte b10 = (byte) i12;
        return f0.n((byte) 0, l.f(((i10 & 7) << 2) | ((i11 & 48) >> 4)), l.f(((((byte) i11) & 15) << 4) | ((b10 & 60) >> 2)), l.f(((b10 & 3) << 6) | (((byte) i13) & 63)));
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int findNextLineTerminator(java.nio.charset.Charset r5) {
        /*
            r4 = this;
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L40
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.US_ASCII
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L11
            goto L40
        L11:
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_16
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L3e
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_16LE
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L3e
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_16BE
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L2a
            goto L3e
        L2a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unsupported charset: "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L3e:
            r0 = 2
            goto L41
        L40:
            r0 = 1
        L41:
            int r1 = r4.position
        L43:
            int r2 = r4.limit
            int r3 = r0 + (-1)
            int r3 = r2 - r3
            if (r1 >= r3) goto La2
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_8
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L5b
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.US_ASCII
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L66
        L5b:
            byte[] r2 = r4.data
            r2 = r2[r1]
            boolean r2 = androidx.media3.common.util.Util.isLinebreak(r2)
            if (r2 == 0) goto L66
            goto L9f
        L66:
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_16
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L76
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_16BE
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L87
        L76:
            byte[] r2 = r4.data
            r3 = r2[r1]
            if (r3 != 0) goto L87
            int r3 = r1 + 1
            r2 = r2[r3]
            boolean r2 = androidx.media3.common.util.Util.isLinebreak(r2)
            if (r2 == 0) goto L87
            goto L9f
        L87:
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_16LE
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto La0
            byte[] r2 = r4.data
            int r3 = r1 + 1
            r3 = r2[r3]
            if (r3 != 0) goto La0
            r2 = r2[r1]
            boolean r2 = androidx.media3.common.util.Util.isLinebreak(r2)
            if (r2 == 0) goto La0
        L9f:
            return r1
        La0:
            int r1 = r1 + r0
            goto L43
        La2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.ParsableByteArray.findNextLineTerminator(java.nio.charset.Charset):int");
    }

    private static int getSmallestCodeUnitSize(Charset charset) {
        ac.b.l(SUPPORTED_CHARSETS_FOR_READLINE.contains(charset), "Unsupported charset: %s", charset);
        return (charset.equals(StandardCharsets.UTF_8) || charset.equals(StandardCharsets.US_ASCII)) ? 1 : 2;
    }

    private static boolean isUtf8ContinuationByte(byte b10) {
        return (b10 & 192) == 128;
    }

    private void maybeAssertAtLeastBytesLeftForLegacyMethod(int i10) {
        if (!shouldEnforceLimitOnLegacyMethods.get() || bytesLeft() >= i10) {
            return;
        }
        StringBuilder sbS = a0.c.s(i10, "bytesNeeded= ", ", bytesLeft=");
        sbS.append(bytesLeft());
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    private int peekCodePointAndSize(Charset charset) {
        int codePoint;
        int iDecodeUtf8CodeUnit;
        ac.b.l(SUPPORTED_CHARSETS_FOR_READLINE.contains(charset), "Unsupported charset: %s", charset);
        if (bytesLeft() < getSmallestCodeUnitSize(charset)) {
            throw new IndexOutOfBoundsException("position=" + this.position + ", limit=" + this.limit);
        }
        byte b10 = 1;
        if (charset.equals(StandardCharsets.US_ASCII)) {
            byte b11 = this.data[this.position];
            if ((b11 & 128) != 0) {
                return 0;
            }
            codePoint = b11 & 255;
        } else if (charset.equals(StandardCharsets.UTF_8)) {
            byte bPeekUtf8CodeUnitSize = peekUtf8CodeUnitSize();
            if (bPeekUtf8CodeUnitSize == 1) {
                iDecodeUtf8CodeUnit = this.data[this.position] & 255;
            } else if (bPeekUtf8CodeUnitSize == 2) {
                byte[] bArr = this.data;
                int i10 = this.position;
                iDecodeUtf8CodeUnit = decodeUtf8CodeUnit(0, 0, bArr[i10], bArr[i10 + 1]);
            } else if (bPeekUtf8CodeUnitSize == 3) {
                byte[] bArr2 = this.data;
                int i11 = this.position;
                iDecodeUtf8CodeUnit = decodeUtf8CodeUnit(0, bArr2[i11] & 15, bArr2[i11 + 1], bArr2[i11 + 2]);
            } else {
                if (bPeekUtf8CodeUnitSize != 4) {
                    return 0;
                }
                byte[] bArr3 = this.data;
                int i12 = this.position;
                iDecodeUtf8CodeUnit = decodeUtf8CodeUnit(bArr3[i12], bArr3[i12 + 1], bArr3[i12 + 2], bArr3[i12 + 3]);
            }
            b10 = bPeekUtf8CodeUnitSize;
            codePoint = iDecodeUtf8CodeUnit;
        } else {
            ByteOrder byteOrder = charset.equals(StandardCharsets.UTF_16LE) ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
            char cPeekChar = peekChar(byteOrder, 0);
            if (!Character.isHighSurrogate(cPeekChar) || bytesLeft() < 4) {
                codePoint = cPeekChar;
                b10 = 2;
            } else {
                codePoint = Character.toCodePoint(cPeekChar, peekChar(byteOrder, 2));
                b10 = 4;
            }
        }
        return (codePoint << 8) | b10;
    }

    private byte peekUtf8CodeUnitSize() {
        byte b10 = this.data[this.position];
        if ((b10 & 128) == 0) {
            return (byte) 1;
        }
        if ((b10 & 224) == 192 && bytesLeft() >= 2 && isUtf8ContinuationByte(this.data[this.position + 1])) {
            return (byte) 2;
        }
        if ((this.data[this.position] & 240) == 224 && bytesLeft() >= 3 && isUtf8ContinuationByte(this.data[this.position + 1]) && isUtf8ContinuationByte(this.data[this.position + 2])) {
            return (byte) 3;
        }
        return ((this.data[this.position] & 248) == 240 && bytesLeft() >= 4 && isUtf8ContinuationByte(this.data[this.position + 1]) && isUtf8ContinuationByte(this.data[this.position + 2]) && isUtf8ContinuationByte(this.data[this.position + 3])) ? (byte) 4 : (byte) 0;
    }

    private char readCharacterIfInList(Charset charset, char[] cArr) {
        int iPeekCodePointAndSize;
        if (bytesLeft() < getSmallestCodeUnitSize(charset) || (iPeekCodePointAndSize = peekCodePointAndSize(charset)) == 0) {
            return (char) 0;
        }
        long j10 = iPeekCodePointAndSize >>> 8;
        ac.b.g(j10, "out of range: %s", (j10 >> 32) == 0);
        int i10 = (int) j10;
        if (Character.isSupplementaryCodePoint(i10)) {
            return (char) 0;
        }
        long j11 = i10;
        char c10 = (char) j11;
        ac.b.g(j11, "Out of range: %s", ((long) c10) == j11);
        for (char c11 : cArr) {
            if (c11 == c10) {
                this.position = f0.f(iPeekCodePointAndSize & 255) + this.position;
                return c10;
            }
        }
        return (char) 0;
    }

    public static void setShouldEnforceLimitOnLegacyMethods(boolean z) {
        shouldEnforceLimitOnLegacyMethods.set(z);
    }

    private void skipLineTerminator(Charset charset) {
        if (readCharacterIfInList(charset, CR_AND_LF) == '\r') {
            readCharacterIfInList(charset, LF);
        }
    }

    public int bytesLeft() {
        return Math.max(this.limit - this.position, 0);
    }

    public int capacity() {
        return this.data.length;
    }

    public void ensureCapacity(int i10) {
        if (i10 > capacity()) {
            this.data = Arrays.copyOf(this.data, i10);
        }
    }

    public byte[] getData() {
        return this.data;
    }

    public int getPosition() {
        return this.position;
    }

    public int limit() {
        return this.limit;
    }

    public char peekChar() {
        return peekChar(ByteOrder.BIG_ENDIAN, 0);
    }

    public int peekCodePoint(Charset charset) {
        return peekCodePointAndSize(charset) != 0 ? f0.f(r3 >>> 8) : INVALID_CODE_POINT;
    }

    public int peekInt() {
        if (bytesLeft() >= 4) {
            int i10 = readInt();
            this.position -= 4;
            return i10;
        }
        throw new IndexOutOfBoundsException("position=" + this.position + ", limit=" + this.limit);
    }

    public int peekUnsignedByte() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(1);
        return this.data[this.position] & 255;
    }

    public int peekUnsignedInt24() {
        if (bytesLeft() >= 3) {
            int unsignedInt24 = readUnsignedInt24();
            this.position -= 3;
            return unsignedInt24;
        }
        throw new IndexOutOfBoundsException("position=" + this.position + ", limit=" + this.limit);
    }

    public void readBytes(ParsableBitArray parsableBitArray, int i10) {
        readBytes(parsableBitArray.data, 0, i10);
        parsableBitArray.setPosition(0);
    }

    public String readDelimiterTerminatedString(char c10) {
        if (bytesLeft() == 0) {
            return null;
        }
        int i10 = this.position;
        while (i10 < this.limit && this.data[i10] != c10) {
            i10++;
        }
        byte[] bArr = this.data;
        int i11 = this.position;
        String strFromUtf8Bytes = Util.fromUtf8Bytes(bArr, i11, i10 - i11);
        this.position = i10;
        if (i10 < this.limit) {
            this.position = i10 + 1;
        }
        return strFromUtf8Bytes;
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public int readInt() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(4);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = (bArr[i10] & 255) << 24;
        int i13 = i10 + 2;
        this.position = i13;
        int i14 = ((bArr[i11] & 255) << 16) | i12;
        int i15 = i10 + 3;
        this.position = i15;
        int i16 = i14 | ((bArr[i13] & 255) << 8);
        this.position = i10 + 4;
        return (bArr[i15] & 255) | i16;
    }

    public int readInt24() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(3);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = ((bArr[i10] & 255) << 24) >> 8;
        int i13 = i10 + 2;
        this.position = i13;
        int i14 = ((bArr[i11] & 255) << 8) | i12;
        this.position = i10 + 3;
        return (bArr[i13] & 255) | i14;
    }

    public String readLine() {
        return readLine(StandardCharsets.UTF_8);
    }

    public int readLittleEndianInt() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(4);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = bArr[i10] & 255;
        int i13 = i10 + 2;
        this.position = i13;
        int i14 = ((bArr[i11] & 255) << 8) | i12;
        int i15 = i10 + 3;
        this.position = i15;
        int i16 = i14 | ((bArr[i13] & 255) << 16);
        this.position = i10 + 4;
        return ((bArr[i15] & 255) << 24) | i16;
    }

    public int readLittleEndianInt24() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(3);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = bArr[i10] & 255;
        int i13 = i10 + 2;
        this.position = i13;
        int i14 = ((bArr[i11] & 255) << 8) | i12;
        this.position = i10 + 3;
        return ((bArr[i13] & 255) << 16) | i14;
    }

    public long readLittleEndianLong() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(8);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        long j10 = ((long) bArr[i10]) & 255;
        int i12 = i10 + 2;
        this.position = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 8);
        int i13 = i10 + 3;
        this.position = i13;
        long j12 = j11 | ((((long) bArr[i12]) & 255) << 16);
        int i14 = i10 + 4;
        this.position = i14;
        long j13 = j12 | ((((long) bArr[i13]) & 255) << 24);
        int i15 = i10 + 5;
        this.position = i15;
        long j14 = j13 | ((((long) bArr[i14]) & 255) << 32);
        int i16 = i10 + 6;
        this.position = i16;
        long j15 = j14 | ((((long) bArr[i15]) & 255) << 40);
        int i17 = i10 + 7;
        this.position = i17;
        long j16 = j15 | ((((long) bArr[i16]) & 255) << 48);
        this.position = i10 + 8;
        return ((((long) bArr[i17]) & 255) << 56) | j16;
    }

    public short readLittleEndianShort() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(2);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = bArr[i10] & 255;
        this.position = i10 + 2;
        return (short) (((bArr[i11] & 255) << 8) | i12);
    }

    public long readLittleEndianUnsignedInt() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(4);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        long j10 = ((long) bArr[i10]) & 255;
        int i12 = i10 + 2;
        this.position = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 8);
        int i13 = i10 + 3;
        this.position = i13;
        long j12 = j11 | ((((long) bArr[i12]) & 255) << 16);
        this.position = i10 + 4;
        return ((((long) bArr[i13]) & 255) << 24) | j12;
    }

    public int readLittleEndianUnsignedInt24() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(3);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = bArr[i10] & 255;
        int i13 = i10 + 2;
        this.position = i13;
        int i14 = ((bArr[i11] & 255) << 8) | i12;
        this.position = i10 + 3;
        return ((bArr[i13] & 255) << 16) | i14;
    }

    public int readLittleEndianUnsignedIntToInt() {
        int littleEndianInt = readLittleEndianInt();
        if (littleEndianInt >= 0) {
            return littleEndianInt;
        }
        throw new IllegalStateException(a0.c.i(littleEndianInt, "Top bit not zero: "));
    }

    public int readLittleEndianUnsignedShort() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(2);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = bArr[i10] & 255;
        this.position = i10 + 2;
        return ((bArr[i11] & 255) << 8) | i12;
    }

    public long readLong() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(8);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        long j10 = (((long) bArr[i10]) & 255) << 56;
        int i12 = i10 + 2;
        this.position = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 48);
        int i13 = i10 + 3;
        this.position = i13;
        long j12 = j11 | ((((long) bArr[i12]) & 255) << 40);
        int i14 = i10 + 4;
        this.position = i14;
        long j13 = j12 | ((((long) bArr[i13]) & 255) << 32);
        int i15 = i10 + 5;
        this.position = i15;
        long j14 = j13 | ((((long) bArr[i14]) & 255) << 24);
        int i16 = i10 + 6;
        this.position = i16;
        long j15 = j14 | ((((long) bArr[i15]) & 255) << 16);
        int i17 = i10 + 7;
        this.position = i17;
        long j16 = j15 | ((((long) bArr[i16]) & 255) << 8);
        this.position = i10 + 8;
        return (((long) bArr[i17]) & 255) | j16;
    }

    public String readNullTerminatedString(int i10) {
        maybeAssertAtLeastBytesLeftForLegacyMethod(i10);
        if (i10 == 0) {
            return "";
        }
        int i11 = this.position;
        int i12 = (i11 + i10) - 1;
        String strFromUtf8Bytes = Util.fromUtf8Bytes(this.data, i11, (i12 >= this.limit || this.data[i12] != 0) ? i10 : i10 - 1);
        this.position += i10;
        return strFromUtf8Bytes;
    }

    public short readShort() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(2);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = (bArr[i10] & 255) << 8;
        this.position = i10 + 2;
        return (short) ((bArr[i11] & 255) | i12);
    }

    public String readString(int i10) {
        return readString(i10, StandardCharsets.UTF_8);
    }

    public int readSynchSafeInt() {
        return (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7) | readUnsignedByte();
    }

    public int readUnsignedByte() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(1);
        byte[] bArr = this.data;
        int i10 = this.position;
        this.position = i10 + 1;
        return bArr[i10] & 255;
    }

    public int readUnsignedFixedPoint1616() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(4);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = (bArr[i10] & 255) << 8;
        this.position = i10 + 2;
        int i13 = (bArr[i11] & 255) | i12;
        this.position = i10 + 4;
        return i13;
    }

    public long readUnsignedInt() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(4);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        long j10 = (((long) bArr[i10]) & 255) << 24;
        int i12 = i10 + 2;
        this.position = i12;
        long j11 = j10 | ((((long) bArr[i11]) & 255) << 16);
        int i13 = i10 + 3;
        this.position = i13;
        long j12 = j11 | ((((long) bArr[i12]) & 255) << 8);
        this.position = i10 + 4;
        return (((long) bArr[i13]) & 255) | j12;
    }

    public int readUnsignedInt24() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(3);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = (bArr[i10] & 255) << 16;
        int i13 = i10 + 2;
        this.position = i13;
        int i14 = ((bArr[i11] & 255) << 8) | i12;
        this.position = i10 + 3;
        return (bArr[i13] & 255) | i14;
    }

    public int readUnsignedIntToInt() {
        int i10 = readInt();
        if (i10 >= 0) {
            return i10;
        }
        throw new IllegalStateException(a0.c.i(i10, "Top bit not zero: "));
    }

    public int readUnsignedLeb128ToInt() {
        return f0.f(readUnsignedLeb128ToLong());
    }

    public long readUnsignedLeb128ToLong() {
        long j10 = 0;
        for (int i10 = 0; i10 < 9; i10++) {
            if (this.position == this.limit) {
                throw new IllegalStateException("Attempting to read a byte over the limit.");
            }
            long unsignedByte = readUnsignedByte();
            j10 |= (127 & unsignedByte) << (i10 * 7);
            if ((unsignedByte & 128) == 0) {
                return j10;
            }
        }
        return j10;
    }

    public long readUnsignedLongToLong() {
        long j10 = readLong();
        if (j10 >= 0) {
            return j10;
        }
        throw new IllegalStateException(androidx.compose.foundation.c.q(j10, "Top bit not zero: "));
    }

    public int readUnsignedShort() {
        maybeAssertAtLeastBytesLeftForLegacyMethod(2);
        byte[] bArr = this.data;
        int i10 = this.position;
        int i11 = i10 + 1;
        this.position = i11;
        int i12 = (bArr[i10] & 255) << 8;
        this.position = i10 + 2;
        return (bArr[i11] & 255) | i12;
    }

    public long readUtf8EncodedLong() {
        int i10;
        maybeAssertAtLeastBytesLeftForLegacyMethod(1);
        long j10 = this.data[this.position];
        int i11 = 7;
        while (true) {
            if (i11 < 0) {
                break;
            }
            int i12 = 1 << i11;
            if ((((long) i12) & j10) != 0) {
                i11--;
            } else if (i11 < 6) {
                j10 &= (long) (i12 - 1);
                i10 = 7 - i11;
            } else if (i11 == 7) {
                i10 = 1;
            }
        }
        i10 = 0;
        if (i10 == 0) {
            throw new NumberFormatException(androidx.compose.foundation.c.q(j10, "Invalid UTF-8 sequence first byte: "));
        }
        maybeAssertAtLeastBytesLeftForLegacyMethod(i10);
        for (int i13 = 1; i13 < i10; i13++) {
            byte b10 = this.data[this.position + i13];
            if ((b10 & 192) != 128) {
                throw new NumberFormatException(androidx.compose.foundation.c.q(j10, "Invalid UTF-8 sequence continuation byte: "));
            }
            j10 = (j10 << 6) | ((long) (b10 & 63));
        }
        this.position += i10;
        return j10;
    }

    public Charset readUtfCharsetFromBom() {
        if (bytesLeft() >= 3) {
            byte[] bArr = this.data;
            int i10 = this.position;
            if (bArr[i10] == -17 && bArr[i10 + 1] == -69 && bArr[i10 + 2] == -65) {
                this.position = i10 + 3;
                return StandardCharsets.UTF_8;
            }
        }
        if (bytesLeft() < 2) {
            return null;
        }
        byte[] bArr2 = this.data;
        int i11 = this.position;
        byte b10 = bArr2[i11];
        if (b10 == -2 && bArr2[i11 + 1] == -1) {
            this.position = i11 + 2;
            return StandardCharsets.UTF_16BE;
        }
        if (b10 != -1 || bArr2[i11 + 1] != -2) {
            return null;
        }
        this.position = i11 + 2;
        return StandardCharsets.UTF_16LE;
    }

    public void reset(int i10) {
        reset(capacity() < i10 ? new byte[i10] : this.data, i10);
    }

    public void setLimit(int i10) {
        ac.b.j(i10 >= 0 && i10 <= this.data.length);
        this.limit = i10;
    }

    public void setPosition(int i10) {
        ac.b.j(i10 >= 0 && i10 <= this.limit);
        this.position = i10;
    }

    public void skipBytes(int i10) {
        setPosition(this.position + i10);
    }

    public void skipLeb128() {
        while ((readUnsignedByte() & 128) != 0) {
        }
    }

    @Deprecated
    public char peekChar(Charset charset) {
        int iPeekUnsignedByte;
        ac.b.l(SUPPORTED_CHARSETS_FOR_READLINE.contains(charset), "Unsupported charset: %s", charset);
        if (bytesLeft() == 0) {
            return (char) 0;
        }
        if (charset.equals(StandardCharsets.US_ASCII)) {
            iPeekUnsignedByte = peekUnsignedByte();
        } else {
            if (!charset.equals(StandardCharsets.UTF_8)) {
                if (bytesLeft() < 2) {
                    return (char) 0;
                }
                return peekChar(charset.equals(StandardCharsets.UTF_16LE) ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN, 0);
            }
            if ((this.data[this.position] & 128) != 0) {
                return (char) 0;
            }
            iPeekUnsignedByte = peekUnsignedByte();
        }
        return (char) iPeekUnsignedByte;
    }

    public String readLine(Charset charset) {
        ac.b.l(SUPPORTED_CHARSETS_FOR_READLINE.contains(charset), "Unsupported charset: %s", charset);
        if (bytesLeft() == 0) {
            return null;
        }
        if (!charset.equals(StandardCharsets.US_ASCII)) {
            readUtfCharsetFromBom();
        }
        String string = readString(findNextLineTerminator(charset) - this.position, charset);
        if (this.position == this.limit) {
            return string;
        }
        skipLineTerminator(charset);
        return string;
    }

    public String readString(int i10, Charset charset) {
        maybeAssertAtLeastBytesLeftForLegacyMethod(i10);
        String str = new String(this.data, this.position, i10, charset);
        this.position += i10;
        return str;
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public ParsableByteArray(int i10) {
        this.data = new byte[i10];
        this.limit = i10;
    }

    public void readBytes(byte[] bArr, int i10, int i11) {
        maybeAssertAtLeastBytesLeftForLegacyMethod(i11);
        System.arraycopy(this.data, this.position, bArr, i10, i11);
        this.position += i11;
    }

    public void reset(byte[] bArr, int i10) {
        this.data = bArr;
        this.limit = i10;
        this.position = 0;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    public void readBytes(ByteBuffer byteBuffer, int i10) {
        maybeAssertAtLeastBytesLeftForLegacyMethod(i10);
        byteBuffer.put(this.data, this.position, i10);
        this.position += i10;
    }

    public String readNullTerminatedString() {
        return readDelimiterTerminatedString((char) 0);
    }

    public ParsableByteArray(byte[] bArr, int i10) {
        this.data = bArr;
        this.limit = i10;
    }

    private char peekChar(ByteOrder byteOrder, int i10) {
        byte b10;
        byte b11;
        maybeAssertAtLeastBytesLeftForLegacyMethod(2);
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            byte[] bArr = this.data;
            int i11 = this.position + i10;
            b10 = bArr[i11];
            b11 = bArr[i11 + 1];
        } else {
            byte[] bArr2 = this.data;
            int i12 = this.position + i10;
            b10 = bArr2[i12 + 1];
            b11 = bArr2[i12];
        }
        return (char) ((b11 & 255) | (b10 << 8));
    }
}
