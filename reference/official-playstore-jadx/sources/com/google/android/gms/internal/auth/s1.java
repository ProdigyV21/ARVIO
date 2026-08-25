package com.google.android.gms.internal.auth;

import android.content.Context;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class s1 {
    public static int a(byte[] bArr, int i10, a3.d dVar) throws zzfb {
        int i11 = i(bArr, i10, dVar);
        int i12 = dVar.f116a;
        if (i12 < 0) {
            throw zzfb.b();
        }
        if (i12 > bArr.length - i11) {
            throw zzfb.c();
        }
        if (i12 == 0) {
            dVar.f118c = x1.f13142m;
            return i11;
        }
        dVar.f118c = x1.e(i11, i12, bArr);
        return i11 + i12;
    }

    public static c b(Context context) {
        return new c(context, null, c.f13002a, com.google.android.gms.common.api.d.f12682a, com.google.android.gms.common.api.m.f12824c);
    }

    public static k1 c(k1 k1Var) {
        if ((k1Var instanceof n1) || (k1Var instanceof l1)) {
            return k1Var;
        }
        if (k1Var instanceof Serializable) {
            return new l1(k1Var);
        }
        n1 n1Var = new n1();
        n1Var.f13077i = k1Var;
        return n1Var;
    }

    public static String d(x1 x1Var) {
        StringBuilder sb2 = new StringBuilder(x1Var.c());
        for (int i10 = 0; i10 < x1Var.c(); i10++) {
            byte bA = x1Var.a(i10);
            if (bA == 34) {
                sb2.append("\\\"");
            } else if (bA == 39) {
                sb2.append("\\'");
            } else if (bA != 92) {
                switch (bA) {
                    case 7:
                        sb2.append("\\a");
                        break;
                    case 8:
                        sb2.append("\\b");
                        break;
                    case 9:
                        sb2.append("\\t");
                        break;
                    case 10:
                        sb2.append("\\n");
                        break;
                    case 11:
                        sb2.append("\\v");
                        break;
                    case 12:
                        sb2.append("\\f");
                        break;
                    case 13:
                        sb2.append("\\r");
                        break;
                    default:
                        if (bA < 32 || bA > 126) {
                            sb2.append('\\');
                            sb2.append((char) (((bA >>> 6) & 3) + 48));
                            sb2.append((char) (((bA >>> 3) & 7) + 48));
                            sb2.append((char) ((bA & 7) + 48));
                        } else {
                            sb2.append((char) bA);
                        }
                        break;
                }
            } else {
                sb2.append("\\\\");
            }
        }
        return sb2.toString();
    }

    public static int e(int i10, byte[] bArr) {
        int i11 = bArr[i10] & 255;
        int i12 = bArr[i10 + 1] & 255;
        int i13 = bArr[i10 + 2] & 255;
        return ((bArr[i10 + 3] & 255) << 24) | (i12 << 8) | i11 | (i13 << 16);
    }

    public static int f(i3 i3Var, int i10, byte[] bArr, int i11, int i12, g2 g2Var, a3.d dVar) throws zzfb {
        d2 d2VarZzd = i3Var.zzd();
        i3 i3Var2 = i3Var;
        byte[] bArr2 = bArr;
        int i13 = i12;
        a3.d dVar2 = dVar;
        int iL = l(d2VarZzd, i3Var2, bArr2, i11, i13, dVar2);
        i3Var2.a(d2VarZzd);
        dVar2.f118c = d2VarZzd;
        g2Var.add(d2VarZzd);
        while (iL < i13) {
            a3.d dVar3 = dVar2;
            int i14 = i13;
            int i15 = i(bArr2, iL, dVar3);
            if (i10 != dVar3.f116a) {
                break;
            }
            byte[] bArr3 = bArr2;
            i3 i3Var3 = i3Var2;
            d2 d2VarZzd2 = i3Var3.zzd();
            iL = l(d2VarZzd2, i3Var3, bArr3, i15, i14, dVar3);
            i3Var2 = i3Var3;
            bArr2 = bArr3;
            i13 = i14;
            dVar2 = dVar3;
            i3Var2.a(d2VarZzd2);
            dVar2.f118c = d2VarZzd2;
            g2Var.add(d2VarZzd2);
        }
        return iL;
    }

    public static boolean g(byte b10) {
        return b10 > -65;
    }

    public static int h(int i10, byte[] bArr, int i11, int i12, l3 l3Var, a3.d dVar) throws zzfb {
        if ((i10 >>> 3) == 0) {
            throw new zzfb("Protocol message contained an invalid tag (zero).");
        }
        int i13 = i10 & 7;
        if (i13 == 0) {
            int iK = k(bArr, i11, dVar);
            l3Var.b(i10, Long.valueOf(dVar.f117b));
            return iK;
        }
        if (i13 == 1) {
            l3Var.b(i10, Long.valueOf(m(i11, bArr)));
            return i11 + 8;
        }
        if (i13 == 2) {
            int i14 = i(bArr, i11, dVar);
            int i15 = dVar.f116a;
            if (i15 < 0) {
                throw zzfb.b();
            }
            if (i15 > bArr.length - i14) {
                throw zzfb.c();
            }
            if (i15 == 0) {
                l3Var.b(i10, x1.f13142m);
            } else {
                l3Var.b(i10, x1.e(i14, i15, bArr));
            }
            return i14 + i15;
        }
        if (i13 != 3) {
            if (i13 != 5) {
                throw new zzfb("Protocol message contained an invalid tag (zero).");
            }
            l3Var.b(i10, Integer.valueOf(e(i11, bArr)));
            return i11 + 4;
        }
        int i16 = (i10 & (-8)) | 4;
        l3 l3VarA = l3.a();
        int i17 = 0;
        while (true) {
            if (i11 >= i12) {
                break;
            }
            int i18 = i(bArr, i11, dVar);
            i17 = dVar.f116a;
            if (i17 == i16) {
                i11 = i18;
                break;
            }
            i11 = h(i17, bArr, i18, i12, l3VarA, dVar);
        }
        if (i11 > i12 || i17 != i16) {
            throw new zzfb("Failed to parse the message.");
        }
        l3Var.b(i10, l3VarA);
        return i11;
    }

    public static int i(byte[] bArr, int i10, a3.d dVar) {
        int i11 = i10 + 1;
        byte b10 = bArr[i10];
        if (b10 < 0) {
            return j(b10, bArr, i11, dVar);
        }
        dVar.f116a = b10;
        return i11;
    }

    public static int j(int i10, byte[] bArr, int i11, a3.d dVar) {
        byte b10 = bArr[i11];
        int i12 = i11 + 1;
        int i13 = i10 & 127;
        if (b10 >= 0) {
            dVar.f116a = i13 | (b10 << 7);
            return i12;
        }
        int i14 = i13 | ((b10 & 127) << 7);
        int i15 = i11 + 2;
        byte b11 = bArr[i12];
        if (b11 >= 0) {
            dVar.f116a = i14 | (b11 << 14);
            return i15;
        }
        int i16 = i14 | ((b11 & 127) << 14);
        int i17 = i11 + 3;
        byte b12 = bArr[i15];
        if (b12 >= 0) {
            dVar.f116a = i16 | (b12 << 21);
            return i17;
        }
        int i18 = i16 | ((b12 & 127) << 21);
        int i19 = i11 + 4;
        byte b13 = bArr[i17];
        if (b13 >= 0) {
            dVar.f116a = i18 | (b13 << 28);
            return i19;
        }
        int i20 = i18 | ((b13 & 127) << 28);
        while (true) {
            int i21 = i19 + 1;
            if (bArr[i19] >= 0) {
                dVar.f116a = i20;
                return i21;
            }
            i19 = i21;
        }
    }

    public static int k(byte[] bArr, int i10, a3.d dVar) {
        long j10 = bArr[i10];
        int i11 = i10 + 1;
        if (j10 >= 0) {
            dVar.f117b = j10;
            return i11;
        }
        int i12 = i10 + 2;
        byte b10 = bArr[i11];
        long j11 = (j10 & 127) | (((long) (b10 & 127)) << 7);
        int i13 = 7;
        while (b10 < 0) {
            int i14 = i12 + 1;
            byte b11 = bArr[i12];
            i13 += 7;
            j11 |= ((long) (b11 & 127)) << i13;
            b10 = b11;
            i12 = i14;
        }
        dVar.f117b = j11;
        return i12;
    }

    public static int l(Object obj, i3 i3Var, byte[] bArr, int i10, int i11, a3.d dVar) throws zzfb {
        int iJ = i10 + 1;
        int i12 = bArr[i10];
        if (i12 < 0) {
            iJ = j(i12, bArr, iJ, dVar);
            i12 = dVar.f116a;
        }
        int i13 = iJ;
        if (i12 < 0 || i12 > i11 - i13) {
            throw zzfb.c();
        }
        int i14 = i13 + i12;
        i3Var.f(obj, bArr, i13, i14, dVar);
        dVar.f118c = obj;
        return i14;
    }

    public static long m(int i10, byte[] bArr) {
        return (((long) bArr[i10]) & 255) | ((((long) bArr[i10 + 1]) & 255) << 8) | ((((long) bArr[i10 + 2]) & 255) << 16) | ((((long) bArr[i10 + 3]) & 255) << 24) | ((((long) bArr[i10 + 4]) & 255) << 32) | ((((long) bArr[i10 + 5]) & 255) << 40) | ((((long) bArr[i10 + 6]) & 255) << 48) | ((((long) bArr[i10 + 7]) & 255) << 56);
    }
}
