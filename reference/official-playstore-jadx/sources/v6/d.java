package v6;

import com.google.common.util.concurrent.p0;

/* JADX INFO: loaded from: classes5.dex */
public final class d {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f22163j = {1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225};
    public static final int[] k = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22164a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22165b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f22166c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f22167d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22168e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f22169f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f22170g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int[] f22171h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f22172i;

    public d() {
        String strI = a0.c.i(256, "SHA");
        this.f22164a = 64;
        this.f22165b = 32;
        this.f22166c = strI;
        this.f22167d = new byte[64];
        int[] iArr = new int[8];
        this.f22170g = iArr;
        this.f22171h = new int[8];
        this.f22172i = new int[64];
        System.arraycopy(f22163j, 0, iArr, 0, 8);
    }

    public final void a(byte[] bArr) {
        int i10;
        int[] iArr;
        int i11;
        int[] iArr2 = this.f22170g;
        int i12 = 0;
        int[] iArr3 = this.f22171h;
        System.arraycopy(iArr2, 0, iArr3, 0, 8);
        int i13 = 0;
        while (true) {
            i10 = 16;
            iArr = this.f22172i;
            if (i13 >= 16) {
                break;
            }
            int i14 = i13 * 4;
            iArr[i13] = ((bArr[i14 + 1] & 255) << 16) | (bArr[i14 + 3] & 255) | ((bArr[i14 + 2] & 255) << 8) | ((bArr[i14] & 255) << 24);
            i13++;
        }
        while (true) {
            if (i10 >= 64) {
                break;
            }
            int i15 = i10 - 15;
            int i16 = i10 - 2;
            iArr[i10] = iArr[i10 - 16] + ((iArr[i15] >>> 3) ^ (p0.C(iArr[i15], 7) ^ p0.C(iArr[i15], 18))) + iArr[i10 - 7] + ((iArr[i16] >>> 10) ^ (p0.C(iArr[i16], 17) ^ p0.C(iArr[i16], 19)));
            i10++;
        }
        int i17 = 0;
        for (i11 = 64; i17 < i11; i11 = 64) {
            int iC = (p0.C(iArr3[4], 6) ^ p0.C(iArr3[4], 11)) ^ p0.C(iArr3[4], 25);
            int i18 = iArr3[4];
            int i19 = iArr3[7] + iC + (((~i18) & iArr3[6]) ^ (iArr3[5] & i18)) + k[i17] + iArr[i17];
            int i20 = i12;
            int iC2 = (p0.C(iArr3[i20], 13) ^ p0.C(iArr3[i12], 2)) ^ p0.C(iArr3[i20], 22);
            int i21 = iArr3[i20];
            int i22 = iArr3[1];
            int i23 = iArr3[2];
            iArr3[7] = iArr3[6];
            iArr3[6] = iArr3[5];
            iArr3[5] = iArr3[4];
            iArr3[4] = iArr3[3] + i19;
            iArr3[3] = i23;
            iArr3[2] = i22;
            iArr3[1] = i21;
            iArr3[i20] = i19 + iC2 + (((i21 & i22) ^ (i21 & i23)) ^ (i22 & i23));
            i17++;
            i12 = i20;
        }
        while (i12 < 8) {
            iArr2[i12] = iArr2[i12] + iArr3[i12];
            i12++;
        }
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("Hasher("), this.f22166c, ')');
    }
}
