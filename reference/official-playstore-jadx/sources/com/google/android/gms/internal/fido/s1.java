package com.google.android.gms.internal.fido;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes4.dex */
public final class s1 implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ByteArrayInputStream f13816i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public r1 f13817l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte[] f13818m = new byte[8];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a8.e f13819n = new a8.e(9);

    public s1(ByteArrayInputStream byteArrayInputStream) {
        this.f13816i = byteArrayInputStream;
    }

    public final void G(byte b10) {
        l();
        if (this.f13817l.f13812a != b10) {
            throw new IllegalStateException(androidx.compose.foundation.c.n((b10 >> 5) & 7, (this.f13817l.f13812a >> 5) & 7, "expected major type ", " but found "));
        }
    }

    public final void O(int i10, byte[] bArr) throws IOException {
        int i11 = 0;
        while (i11 != i10) {
            int i12 = this.f13816i.read(bArr, i11, i10 - i11);
            if (i12 == -1) {
                throw new EOFException();
            }
            i11 += i12;
        }
        this.f13817l = null;
    }

    public final byte[] R() {
        y();
        long jX = x();
        if (jX < 0 || jX > 2147483647L) {
            throw new UnsupportedOperationException("the maximum supported byte/text string length is 2147483647 bytes");
        }
        if (this.f13816i.available() < jX) {
            throw new EOFException();
        }
        int i10 = (int) jX;
        byte[] bArr = new byte[i10];
        O(i10, bArr);
        return bArr;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f13816i.close();
        this.f13819n.u0();
    }

    public final long i() {
        G((byte) -128);
        y();
        long jX = x();
        if (jX < 0) {
            throw new UnsupportedOperationException("the maximum supported array length is 9223372036854775807");
        }
        if (jX > 0) {
            ((ArrayDeque) this.f13819n.f183l).push(Long.valueOf(jX));
        }
        return jX;
    }

    public final long j() {
        boolean z;
        l();
        byte b10 = this.f13817l.f13812a;
        if (b10 == 0) {
            z = true;
        } else {
            if (b10 != 32) {
                throw new IllegalStateException(a0.c.i((this.f13817l.f13812a >> 5) & 7, "expected major type 0 or 1 but found "));
            }
            z = false;
        }
        long jX = x();
        if (jX >= 0) {
            return z ? jX : ~jX;
        }
        throw new UnsupportedOperationException("the maximum supported unsigned/negative integer is 9223372036854775807");
    }

    public final long k() {
        G((byte) -96);
        y();
        long jX = x();
        if (jX < 0 || jX > 4611686018427387903L) {
            throw new UnsupportedOperationException("the maximum supported map length is 4611686018427387903L");
        }
        if (jX > 0) {
            ((ArrayDeque) this.f13819n.f183l).push(Long.valueOf(jX + jX));
        }
        return jX;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0098, code lost:
    
        if (r0 != (-2)) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.fido.r1 l() {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.s1.l():com.google.android.gms.internal.fido.r1");
    }

    public final boolean o() {
        G((byte) -32);
        if (this.f13817l.f13813b > 24) {
            throw new IllegalStateException("expected simple value");
        }
        int iX = (int) x();
        if (iX == 20) {
            return false;
        }
        if (iX == 21) {
            return true;
        }
        throw new IllegalStateException("expected FALSE or TRUE");
    }

    public final long x() throws IOException {
        byte b10 = this.f13817l.f13813b;
        if (b10 < 24) {
            long j10 = b10;
            this.f13817l = null;
            return j10;
        }
        if (b10 == 24) {
            int i10 = this.f13816i.read();
            if (i10 == -1) {
                throw new EOFException();
            }
            this.f13817l = null;
            return ((long) i10) & 255;
        }
        byte[] bArr = this.f13818m;
        if (b10 == 25) {
            O(2, bArr);
            return ((((long) bArr[0]) & 255) << 8) | (255 & ((long) bArr[1]));
        }
        if (b10 == 26) {
            O(4, bArr);
            return ((((long) bArr[0]) & 255) << 24) | ((((long) bArr[1]) & 255) << 16) | ((((long) bArr[2]) & 255) << 8) | (((long) bArr[3]) & 255);
        }
        if (b10 != 27) {
            r1 r1Var = this.f13817l;
            throw new IOException(androidx.compose.foundation.c.n(r1Var.f13813b, (r1Var.f13812a >> 5) & 7, "invalid additional information ", " for major type "));
        }
        O(8, bArr);
        return ((bArr[0] & 255) << 56) | ((bArr[1] & 255) << 48) | ((bArr[2] & 255) << 40) | ((bArr[3] & 255) << 32) | ((((long) bArr[4]) & 255) << 24) | ((((long) bArr[5]) & 255) << 16) | ((((long) bArr[6]) & 255) << 8) | (((long) bArr[7]) & 255);
    }

    public final void y() {
        l();
        if (this.f13817l.f13813b == 31) {
            throw new IllegalStateException(a0.c.i(this.f13817l.f13813b, "expected definite length but found "));
        }
    }
}
