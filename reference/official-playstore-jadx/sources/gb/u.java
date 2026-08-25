package gb;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class u implements b, r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final u f15819c = new u();

    public static final m b(u uVar, String str) {
        m mVar = new m(str);
        m.f15741d.put(str, mVar);
        return mVar;
    }

    public static String c(int i10, int i11, int i12, String str, String str2, boolean z) {
        int i13 = 0;
        int i14 = (i12 & 1) != 0 ? 0 : i10;
        int length = (i12 & 2) != 0 ? str.length() : i11;
        boolean z5 = (i12 & 8) != 0 ? false : z;
        boolean z10 = (i12 & 16) == 0;
        boolean z11 = (i12 & 32) == 0;
        boolean z12 = (i12 & 64) == 0;
        int iCharCount = i14;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            int i15 = 32;
            int i16 = 43;
            if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && !z12) || kotlin.text.o.U(str2, (char) iCodePointAt) || ((iCodePointAt == 37 && (!z5 || (z10 && !f(iCharCount, length, str)))) || (iCodePointAt == 43 && z11)))) {
                xb.j jVar = new xb.j();
                jVar.R0(i14, iCharCount, str);
                xb.j jVar2 = null;
                while (iCharCount < length) {
                    int iCodePointAt2 = str.codePointAt(iCharCount);
                    if (!z5 || (iCodePointAt2 != 9 && iCodePointAt2 != 10 && iCodePointAt2 != 12 && iCodePointAt2 != 13)) {
                        if (iCodePointAt2 == i16 && z11) {
                            String str3 = z5 ? "+" : "%2B";
                            jVar.R0(i13, str3.length(), str3);
                        } else {
                            if (iCodePointAt2 >= i15 && iCodePointAt2 != 127) {
                                if ((iCodePointAt2 < 128 || z12) && !kotlin.text.o.U(str2, (char) iCodePointAt2) && (iCodePointAt2 != 37 || (z5 && (!z10 || f(iCharCount, length, str))))) {
                                    jVar.T0(iCodePointAt2);
                                }
                            }
                            if (jVar2 == null) {
                                jVar2 = new xb.j();
                            }
                            jVar2.T0(iCodePointAt2);
                            while (!jVar2.o()) {
                                byte b10 = jVar2.readByte();
                                jVar.M0(37);
                                char[] cArr = b0.k;
                                jVar.M0(cArr[((b10 & 255) >> 4) & 15]);
                                jVar.M0(cArr[b10 & 15]);
                            }
                        }
                    }
                    iCharCount += Character.charCount(iCodePointAt2);
                    i13 = 0;
                    i15 = 32;
                    i16 = 43;
                }
                return jVar.t0();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.substring(i14, length);
    }

    public static m0 d(String str, d0 d0Var) {
        Charset charset = kotlin.text.a.f19924a;
        if (d0Var != null) {
            Pattern pattern = d0.f15633d;
            Charset charsetA = d0Var.a(null);
            if (charsetA == null) {
                try {
                    d0Var = xc.d.i0(d0Var + "; charset=utf-8");
                } catch (IllegalArgumentException unused) {
                    d0Var = null;
                }
            } else {
                charset = charsetA;
            }
        }
        byte[] bytes = str.getBytes(charset);
        int length = bytes.length;
        ib.c.c(bytes.length, 0, length);
        return new m0(d0Var, length, bytes);
    }

    public static boolean f(int i10, int i11, String str) {
        int i12 = i10 + 2;
        return i12 < i11 && str.charAt(i10) == '%' && ib.c.r(str.charAt(i10 + 1)) != -1 && ib.c.r(str.charAt(i12)) != -1;
    }

    public static String g(String str, int i10, int i11, int i12) {
        int i13;
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = str.length();
        }
        boolean z = (i12 & 4) == 0;
        int iCharCount = i10;
        while (iCharCount < i11) {
            char cCharAt = str.charAt(iCharCount);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                xb.j jVar = new xb.j();
                jVar.R0(i10, iCharCount, str);
                while (iCharCount < i11) {
                    int iCodePointAt = str.codePointAt(iCharCount);
                    if (iCodePointAt == 37 && (i13 = iCharCount + 2) < i11) {
                        int iR = ib.c.r(str.charAt(iCharCount + 1));
                        int iR2 = ib.c.r(str.charAt(i13));
                        if (iR == -1 || iR2 == -1) {
                            jVar.T0(iCodePointAt);
                            iCharCount += Character.charCount(iCodePointAt);
                        } else {
                            jVar.M0((iR << 4) + iR2);
                            iCharCount = Character.charCount(iCodePointAt) + i13;
                        }
                    } else if (iCodePointAt == 43 && z) {
                        jVar.M0(32);
                        iCharCount++;
                    } else {
                        jVar.T0(iCodePointAt);
                        iCharCount += Character.charCount(iCodePointAt);
                    }
                }
                return jVar.t0();
            }
            iCharCount++;
        }
        return str.substring(i10, i11);
    }

    public static ArrayList h(String str) {
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (i10 <= str.length()) {
            int iE0 = kotlin.text.o.e0(str, '&', i10, false, 4);
            if (iE0 == -1) {
                iE0 = str.length();
            }
            int iE02 = kotlin.text.o.e0(str, '=', i10, false, 4);
            if (iE02 == -1 || iE02 > iE0) {
                arrayList.add(str.substring(i10, iE0));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i10, iE02));
                arrayList.add(str.substring(iE02 + 1, iE0));
            }
            i10 = iE0 + 1;
        }
        return arrayList;
    }

    public static void i(StringBuilder sb2, List list) {
        x7.g gVarV = qb.d.V(qb.d.X(0, list.size()), 2);
        int i10 = gVarV.f22619i;
        int i11 = gVarV.f22620l;
        int i12 = gVarV.f22621m;
        if ((i12 <= 0 || i10 > i11) && (i12 >= 0 || i11 > i10)) {
            return;
        }
        while (true) {
            String str = (String) list.get(i10);
            String str2 = (String) list.get(i10 + 1);
            if (i10 > 0) {
                sb2.append('&');
            }
            sb2.append(str);
            if (str2 != null) {
                sb2.append('=');
                sb2.append(str2);
            }
            if (i10 == i11) {
                return;
            } else {
                i10 += i12;
            }
        }
    }

    public synchronized m e(String str) {
        m mVar;
        try {
            LinkedHashMap linkedHashMap = m.f15741d;
            mVar = (m) linkedHashMap.get(str);
            if (mVar == null) {
                mVar = (m) linkedHashMap.get(kotlin.text.u.P(str, "TLS_", false) ? "SSL_".concat(str.substring(4)) : kotlin.text.u.P(str, "SSL_", false) ? "TLS_".concat(str.substring(4)) : str);
                if (mVar == null) {
                    mVar = new m(str);
                }
                linkedHashMap.put(str, mVar);
            }
        } catch (Throwable th) {
            throw th;
        }
        return mVar;
    }

    @Override // gb.r
    public List loadForRequest(b0 b0Var) {
        return kotlin.collections.z.f19728i;
    }

    @Override // gb.b
    public void a(t0 t0Var, p0 p0Var) {
    }

    @Override // gb.r
    public void saveFromResponse(b0 b0Var, List list) {
    }
}
