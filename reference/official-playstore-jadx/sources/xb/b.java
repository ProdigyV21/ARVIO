package xb;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f22715a = new g();

    public static final boolean a(byte[] bArr, int i10, int i11, byte[] bArr2, int i12) {
        for (int i13 = 0; i13 < i12; i13++) {
            if (bArr[i13 + i10] != bArr2[i13 + i11]) {
                return false;
            }
        }
        return true;
    }

    public static void b(long j10, j jVar, int i10, ArrayList arrayList, int i11, int i12, ArrayList arrayList2) {
        int i13;
        int i14;
        ArrayList arrayList3;
        long j11;
        int i15;
        int i16 = i10;
        ArrayList arrayList4 = arrayList;
        ArrayList arrayList5 = arrayList2;
        if (i11 >= i12) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        for (int i17 = i11; i17 < i12; i17++) {
            if (((m) arrayList4.get(i17)).f() < i16) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }
        m mVar = (m) arrayList.get(i11);
        m mVar2 = (m) arrayList4.get(i12 - 1);
        if (i16 == mVar.f()) {
            int iIntValue = ((Number) arrayList5.get(i11)).intValue();
            int i18 = i11 + 1;
            m mVar3 = (m) arrayList4.get(i18);
            i13 = i18;
            i14 = iIntValue;
            mVar = mVar3;
        } else {
            i13 = i11;
            i14 = -1;
        }
        if (mVar.m(i16) == mVar2.m(i16)) {
            int iMin = Math.min(mVar.f(), mVar2.f());
            int i19 = 0;
            for (int i20 = i16; i20 < iMin && mVar.m(i20) == mVar2.m(i20); i20++) {
                i19++;
            }
            long j12 = 4;
            long j13 = (jVar.f22759l / j12) + j10 + ((long) 2) + ((long) i19) + 1;
            jVar.P0(-i19);
            jVar.P0(i14);
            int i21 = i16 + i19;
            while (i16 < i21) {
                jVar.P0(mVar.m(i16) & 255);
                i16++;
            }
            if (i13 + 1 == i12) {
                if (i21 != ((m) arrayList4.get(i13)).f()) {
                    throw new IllegalStateException("Check failed.");
                }
                jVar.P0(((Number) arrayList5.get(i13)).intValue());
                return;
            } else {
                j jVar2 = new j();
                jVar.P0(((int) ((jVar2.f22759l / j12) + j13)) * (-1));
                b(j13, jVar2, i21, arrayList4, i13, i12, arrayList5);
                jVar.z0(jVar2);
                return;
            }
        }
        int i22 = 1;
        for (int i23 = i13 + 1; i23 < i12; i23++) {
            if (((m) arrayList4.get(i23 - 1)).m(i16) != ((m) arrayList4.get(i23)).m(i16)) {
                i22++;
            }
        }
        long j14 = 4;
        long j15 = (jVar.f22759l / j14) + j10 + ((long) 2) + ((long) (i22 * 2));
        jVar.P0(i22);
        jVar.P0(i14);
        for (int i24 = i13; i24 < i12; i24++) {
            int iM = ((m) arrayList4.get(i24)).m(i16);
            if (i24 == i13 || iM != ((m) arrayList4.get(i24 - 1)).m(i16)) {
                jVar.P0(iM & 255);
            }
        }
        j jVar3 = new j();
        int i25 = i13;
        while (i25 < i12) {
            byte bM = ((m) arrayList4.get(i25)).m(i16);
            int i26 = i25 + 1;
            int i27 = i26;
            while (true) {
                if (i27 >= i12) {
                    i27 = i12;
                    break;
                } else if (bM != ((m) arrayList4.get(i27)).m(i16)) {
                    break;
                } else {
                    i27++;
                }
            }
            if (i26 == i27 && i16 + 1 == ((m) arrayList4.get(i25)).f()) {
                jVar.P0(((Number) arrayList5.get(i25)).intValue());
                arrayList3 = arrayList5;
                j11 = j15;
                i15 = i27;
            } else {
                jVar.P0(((int) ((jVar3.f22759l / j14) + j15)) * (-1));
                arrayList3 = arrayList5;
                j11 = j15;
                i15 = i27;
                b(j11, jVar3, i16 + 1, arrayList, i25, i15, arrayList3);
                arrayList4 = arrayList;
            }
            j15 = j11;
            i25 = i15;
            arrayList5 = arrayList3;
        }
        jVar.z0(jVar3);
    }

    public static final void c(long j10, long j11, long j12) {
        if ((j11 | j12) < 0 || j11 > j10 || j10 - j11 < j12) {
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "size=", " offset=");
            sbR.append(j11);
            sbR.append(" byteCount=");
            sbR.append(j12);
            throw new ArrayIndexOutOfBoundsException(sbR.toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d4, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static xb.e0 d(xb.m... r11) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: xb.b.d(xb.m[]):xb.e0");
    }

    public static final c e(OutputStream outputStream) {
        Logger logger = d0.f22723a;
        return new c(outputStream, new q0(), 1);
    }

    public static long f(String str) {
        int i10;
        int length = str.length();
        if (length < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(length, 0, "endIndex < beginIndex: ", " < ").toString());
        }
        if (length > str.length()) {
            StringBuilder sbS = a0.c.s(length, "endIndex > string.length: ", " > ");
            sbS.append(str.length());
            throw new IllegalArgumentException(sbS.toString().toString());
        }
        long j10 = 0;
        int i11 = 0;
        while (i11 < length) {
            char cCharAt = str.charAt(i11);
            if (cCharAt < 128) {
                j10++;
            } else {
                if (cCharAt < 2048) {
                    i10 = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i10 = 3;
                } else {
                    int i12 = i11 + 1;
                    char cCharAt2 = i12 < length ? str.charAt(i12) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j10++;
                        i11 = i12;
                    } else {
                        j10 += (long) 4;
                        i11 += 2;
                    }
                }
                j10 += (long) i10;
            }
            i11++;
        }
        return j10;
    }

    public static final d g(InputStream inputStream) {
        Logger logger = d0.f22723a;
        return new d(inputStream, new q0(), 1);
    }

    public static final String h(byte b10) {
        char[] cArr = yb.b.f23007a;
        return new String(new char[]{cArr[(b10 >> 4) & 15], cArr[b10 & 15]});
    }
}
