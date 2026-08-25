package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public abstract class q0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final char[] f13809i = "0123456789abcdef".toCharArray();

    public final boolean equals(Object obj) {
        boolean z;
        if (obj instanceof q0) {
            byte[] bArr = ((p0) this).f13806l;
            int length = bArr.length * 8;
            byte[] bArr2 = ((p0) ((q0) obj)).f13806l;
            if (length == bArr2.length * 8) {
                if (bArr.length == bArr2.length) {
                    z = true;
                    for (int i10 = 0; i10 < bArr.length; i10++) {
                        z &= bArr[i10] == bArr2[i10];
                    }
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        byte[] bArr = ((p0) this).f13806l;
        if (bArr.length * 8 < 32) {
            int i10 = bArr[0] & 255;
            for (int i11 = 1; i11 < bArr.length; i11++) {
                i10 |= (bArr[i11] & 255) << (i11 * 8);
            }
            return i10;
        }
        int length = bArr.length;
        if (length < 4) {
            throw new IllegalStateException(b.d("HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", Integer.valueOf(length)));
        }
        return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public final String toString() {
        byte[] bArr = ((p0) this).f13806l;
        int length = bArr.length;
        StringBuilder sb2 = new StringBuilder(length + length);
        for (byte b10 : bArr) {
            char[] cArr = f13809i;
            sb2.append(cArr[(b10 >> 4) & 15]);
            sb2.append(cArr[b10 & 15]);
        }
        return sb2.toString();
    }
}
