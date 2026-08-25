package m7;

import kotlin.collections.f;

/* JADX INFO: loaded from: classes5.dex */
public class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f20248e = new a(false, false, -1);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f20249f = {13, 10};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f20250g = new b(true, false, -1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f20251a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f20252b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f20253c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f20254d;

    static {
        new b(false, true, 76);
        new b(false, true, 64);
    }

    public b(boolean z, boolean z5, int i10) {
        this.f20251a = z;
        this.f20252b = z5;
        this.f20253c = i10;
        if (z && z5) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.f20254d = i10 / 4;
    }

    public static String a(b bVar, byte[] bArr) {
        int i10;
        int length = bArr.length;
        bVar.getClass();
        int length2 = bArr.length;
        kotlin.collections.c cVar = f.Companion;
        cVar.getClass();
        kotlin.collections.c.a(0, length, length2);
        int iB = bVar.b(length);
        byte[] bArr2 = new byte[iB];
        int length3 = bArr.length;
        cVar.getClass();
        kotlin.collections.c.a(0, length, length3);
        int iB2 = bVar.b(length);
        if (iB < 0) {
            throw new IndexOutOfBoundsException(a0.c.i(iB, "destination offset: 0, destination size: "));
        }
        if (iB2 < 0 || iB2 > iB) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(iB, iB2, "The destination array does not have enough capacity, destination offset: 0, destination size: ", ", capacity needed: "));
        }
        byte[] bArr3 = bVar.f20251a ? c.f20256b : c.f20255a;
        int i11 = bVar.f20252b ? bVar.f20254d : Integer.MAX_VALUE;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            i10 = i12 + 2;
            if (i10 >= length) {
                break;
            }
            int iMin = Math.min((length - i12) / 3, i11);
            for (int i14 = 0; i14 < iMin; i14++) {
                int i15 = bArr[i12] & 255;
                int i16 = i12 + 2;
                int i17 = bArr[i12 + 1] & 255;
                i12 += 3;
                int i18 = (i17 << 8) | (i15 << 16) | (bArr[i16] & 255);
                bArr2[i13] = bArr3[i18 >>> 18];
                bArr2[i13 + 1] = bArr3[(i18 >>> 12) & 63];
                int i19 = i13 + 3;
                bArr2[i13 + 2] = bArr3[(i18 >>> 6) & 63];
                i13 += 4;
                bArr2[i19] = bArr3[i18 & 63];
            }
            if (iMin == i11 && i12 != length) {
                int i20 = i13 + 1;
                byte[] bArr4 = f20249f;
                bArr2[i13] = bArr4[0];
                i13 += 2;
                bArr2[i20] = bArr4[1];
            }
        }
        int i21 = length - i12;
        if (i21 == 1) {
            int i22 = (bArr[i12] & 255) << 4;
            bArr2[i13] = bArr3[i22 >>> 6];
            bArr2[i13 + 1] = bArr3[i22 & 63];
            bArr2[i13 + 2] = 61;
            bArr2[i13 + 3] = 61;
            i12++;
        } else if (i21 == 2) {
            int i23 = ((bArr[i12 + 1] & 255) << 2) | ((bArr[i12] & 255) << 10);
            bArr2[i13] = bArr3[i23 >>> 12];
            bArr2[i13 + 1] = bArr3[(i23 >>> 6) & 63];
            bArr2[i13 + 2] = bArr3[i23 & 63];
            bArr2[i13 + 3] = 61;
            i12 = i10;
        }
        if (i12 == length) {
            return new String(bArr2, kotlin.text.a.f19926c);
        }
        throw new IllegalStateException("Check failed.");
    }

    public final int b(int i10) {
        int i11 = (i10 / 3) * 4;
        if (i10 % 3 != 0) {
            i11 += 4;
        }
        if (i11 < 0) {
            throw new IllegalArgumentException("Input is too big");
        }
        if (this.f20252b) {
            i11 += ((i11 - 1) / this.f20253c) * 2;
        }
        if (i11 >= 0) {
            return i11;
        }
        throw new IllegalArgumentException("Input is too big");
    }
}
