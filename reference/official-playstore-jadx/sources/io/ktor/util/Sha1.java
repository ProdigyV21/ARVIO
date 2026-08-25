package io.ktor.util;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\u0003J'\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u0018R\u0016\u0010\u001d\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u0018R\u0016\u0010\u001e\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u0018R\u0016\u0010\u001f\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u0018R\u0016\u0010 \u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010\u0018¨\u0006!"}, d2 = {"Lio/ktor/util/Sha1;", "Lio/ktor/util/HashFunction;", "<init>", "()V", "", "input", "", "pos", "Lx6/t0;", "processChunk", "([BI)V", "reset", "offset", "length", "update", "([BII)V", "digest", "()[B", "", "messageLength", "J", "unprocessed", "[B", "unprocessedLimit", "I", "", "words", "[I", "h0", "h1", "h2", "h3", "h4", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Sha1 implements HashFunction {
    private long messageLength;
    private int unprocessedLimit;
    private final byte[] unprocessed = new byte[64];
    private final int[] words = new int[80];
    private int h0 = 1732584193;
    private int h1 = -271733879;
    private int h2 = -1732584194;
    private int h3 = 271733878;
    private int h4 = -1009589776;

    private final void processChunk(byte[] input, int pos) {
        int i10;
        int iLeftRotate;
        int i11;
        int[] iArr = this.words;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            if (i13 >= 16) {
                break;
            }
            int i14 = pos + 3;
            int i15 = ((input[pos + 1] & 255) << 16) | ((input[pos] & 255) << 24) | ((input[pos + 2] & 255) << 8);
            pos += 4;
            iArr[i13] = i15 | (input[i14] & 255);
            i13++;
        }
        for (i10 = 16; i10 < 80; i10++) {
            iArr[i10] = HashFunctionKt.leftRotate(((iArr[i10 - 3] ^ iArr[i10 - 8]) ^ iArr[i10 - 14]) ^ iArr[i10 - 16], 1);
        }
        int i16 = this.h0;
        int i17 = this.h1;
        int iLeftRotate2 = this.h2;
        int i18 = this.h3;
        int i19 = this.h4;
        while (i12 < 80) {
            if (i12 < 20) {
                iLeftRotate = HashFunctionKt.leftRotate(i16, 5) + (((iLeftRotate2 ^ i18) & i17) ^ i18) + i19 + 1518500249;
                i11 = iArr[i12];
            } else if (i12 < 40) {
                iLeftRotate = HashFunctionKt.leftRotate(i16, 5) + ((i17 ^ iLeftRotate2) ^ i18) + i19 + 1859775393;
                i11 = iArr[i12];
            } else if (i12 < 60) {
                iLeftRotate = ((HashFunctionKt.leftRotate(i16, 5) + (((iLeftRotate2 | i18) & i17) | (iLeftRotate2 & i18))) + i19) - 1894007588;
                i11 = iArr[i12];
            } else {
                iLeftRotate = ((HashFunctionKt.leftRotate(i16, 5) + ((i17 ^ iLeftRotate2) ^ i18)) + i19) - 899497514;
                i11 = iArr[i12];
            }
            int i20 = iLeftRotate + i11;
            i12++;
            i19 = i18;
            i18 = iLeftRotate2;
            iLeftRotate2 = HashFunctionKt.leftRotate(i17, 30);
            i17 = i16;
            i16 = i20;
        }
        this.h0 += i16;
        this.h1 += i17;
        this.h2 += iLeftRotate2;
        this.h3 += i18;
        this.h4 += i19;
    }

    private final void reset() {
        this.messageLength = 0L;
        byte[] bArr = this.unprocessed;
        Arrays.fill(bArr, 0, bArr.length, (byte) 0);
        this.unprocessedLimit = 0;
        r.U(this.words, 0, 0, 6);
        this.h0 = 1732584193;
        this.h1 = -271733879;
        this.h2 = -1732584194;
        this.h3 = 271733878;
        this.h4 = -1009589776;
    }

    @Override // io.ktor.util.HashFunction
    public byte[] digest() {
        byte[] bArr = this.unprocessed;
        int i10 = this.unprocessedLimit;
        long j10 = this.messageLength * ((long) 8);
        int i11 = i10 + 1;
        bArr[i10] = -128;
        if (i11 > 56) {
            Arrays.fill(bArr, i11, 64, (byte) 0);
            processChunk(bArr, 0);
            Arrays.fill(bArr, 0, i11, (byte) 0);
        } else {
            Arrays.fill(bArr, i11, 56, (byte) 0);
        }
        bArr[56] = (byte) (j10 >>> 56);
        bArr[57] = (byte) (j10 >>> 48);
        bArr[58] = (byte) (j10 >>> 40);
        bArr[59] = (byte) (j10 >>> 32);
        bArr[60] = (byte) (j10 >>> 24);
        bArr[61] = (byte) (j10 >>> 16);
        bArr[62] = (byte) (j10 >>> 8);
        bArr[63] = (byte) j10;
        processChunk(bArr, 0);
        int i12 = this.h0;
        int i13 = this.h1;
        int i14 = this.h2;
        int i15 = this.h3;
        int i16 = this.h4;
        reset();
        return new byte[]{(byte) (i12 >> 24), (byte) (i12 >> 16), (byte) (i12 >> 8), (byte) i12, (byte) (i13 >> 24), (byte) (i13 >> 16), (byte) (i13 >> 8), (byte) i13, (byte) (i14 >> 24), (byte) (i14 >> 16), (byte) (i14 >> 8), (byte) i14, (byte) (i15 >> 24), (byte) (i15 >> 16), (byte) (i15 >> 8), (byte) i15, (byte) (i16 >> 24), (byte) (i16 >> 16), (byte) (i16 >> 8), (byte) i16};
    }

    @Override // io.ktor.util.HashFunction
    public void update(byte[] input, int offset, int length) {
        this.messageLength += (long) length;
        int i10 = offset + length;
        byte[] bArr = this.unprocessed;
        int i11 = this.unprocessedLimit;
        if (i11 > 0) {
            int i12 = length + i11;
            if (i12 < 64) {
                r.L(input, i11, offset, bArr, i10);
                this.unprocessedLimit = i12;
                return;
            } else {
                int i13 = (64 - i11) + offset;
                r.L(input, i11, offset, bArr, i13);
                processChunk(bArr, 0);
                this.unprocessedLimit = 0;
                offset = i13;
            }
        }
        while (offset < i10) {
            int i14 = offset + 64;
            if (i14 > i10) {
                r.L(input, 0, offset, bArr, i10);
                this.unprocessedLimit = i10 - offset;
                return;
            } else {
                processChunk(input, offset);
                offset = i14;
            }
        }
    }
}
