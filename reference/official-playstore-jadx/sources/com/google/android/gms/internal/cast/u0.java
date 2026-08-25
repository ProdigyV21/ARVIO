package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class u0 implements e0.i {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final o3.d[] f13619l = {new o3.d("usage_and_diagnostics_listener", -1, 1, true), new o3.d("usage_and_diagnostics_consents", -1, 1, true), new o3.d("usage_and_diagnostics_check_consents", -1, 1, true), new o3.d("usage_and_diagnostics_settings_access", -1, 1, true), new o3.d("el_capitan", -1, 1, false)};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13620i;

    public /* synthetic */ u0(int i10) {
        this.f13620i = i10;
    }

    public static float a(float f10, float f11, float f12, float f13) {
        return (float) Math.hypot(f12 - f10, f13 - f11);
    }

    public static int d(int i10) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i10) * (-862048943)), 15)) * 461845907);
    }

    public static int f(int i10, int i11) {
        if (i11 < 0) {
            throw new IllegalArgumentException("cannot store more than Integer.MAX_VALUE elements");
        }
        if (i11 <= i10) {
            return i10;
        }
        int i12 = i10 + (i10 >> 1) + 1;
        if (i12 < i11) {
            int iHighestOneBit = Integer.highestOneBit(i11 - 1);
            i12 = iHighestOneBit + iHighestOneBit;
        }
        if (i12 < 0) {
            return Integer.MAX_VALUE;
        }
        return i12;
    }

    public static com.google.android.gms.common.api.j i() {
        com.google.android.gms.common.api.l lVar = new com.google.android.gms.common.api.l(-1, -1, 0, true);
        Parcelable.Creator<com.google.android.gms.common.api.j> creator = com.google.android.gms.common.api.j.CREATOR;
        com.google.android.gms.common.api.j jVar = new com.google.android.gms.common.api.j(lVar, false);
        jVar.f12819m = false;
        return jVar;
    }

    public static /* synthetic */ String j(int i10, int i11, byte b10, String str, String str2) {
        StringBuilder sb2 = new StringBuilder(String.valueOf(i11).length() + b10 + String.valueOf(i10).length());
        sb2.append(str);
        sb2.append(i11);
        sb2.append(str2);
        sb2.append(i10);
        return sb2.toString();
    }

    public static String k(ha haVar) {
        StringBuilder sb2 = new StringBuilder(haVar.c());
        for (int i10 = 0; i10 < haVar.c(); i10++) {
            byte bA = haVar.a(i10);
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

    public static String l(String str) {
        int length = str.length();
        int i10 = 0;
        while (i10 < length) {
            char cCharAt = str.charAt(i10);
            if (cCharAt >= 'a' && cCharAt <= 'z') {
                char[] charArray = str.toCharArray();
                while (i10 < length) {
                    char c10 = charArray[i10];
                    if (c10 >= 'a' && c10 <= 'z') {
                        charArray[i10] = (char) (c10 ^ ' ');
                    }
                    i10++;
                }
                return String.valueOf(charArray);
            }
            i10++;
        }
        return str;
    }

    public static String m(String str, Object... objArr) {
        int length;
        int iIndexOf;
        StringBuilder sb2 = new StringBuilder(str.length() + (objArr.length * 16));
        int i10 = 0;
        int i11 = 0;
        while (true) {
            length = objArr.length;
            if (i10 >= length || (iIndexOf = str.indexOf("%s", i11)) == -1) {
                break;
            }
            sb2.append((CharSequence) str, i11, iIndexOf);
            sb2.append(q(objArr[i10]));
            i11 = iIndexOf + 2;
            i10++;
        }
        sb2.append((CharSequence) str, i11, str.length());
        if (i10 < length) {
            String str2 = " [";
            while (i10 < objArr.length) {
                sb2.append(str2);
                sb2.append(q(objArr[i10]));
                i10++;
                str2 = ", ";
            }
            sb2.append(']');
        }
        return sb2.toString();
    }

    public static Map n(Bundle bundle, String str) {
        Map map = (Map) bundle.getSerializable(str);
        if (map == null) {
            return u4.f13624q;
        }
        HashMap map2 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                map2.put((Integer) entry.getKey(), (Integer) entry.getValue());
            }
        }
        return Collections.unmodifiableMap(map2);
    }

    public static void p(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static String q(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return obj.toString();
        } catch (Exception e5) {
            String name = obj.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(obj));
            String strQ = androidx.compose.material3.d.q(new StringBuilder(name.length() + 1 + String.valueOf(hexString).length()), name, "@", hexString);
            Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(strQ), (Throwable) e5);
            String name2 = e5.getClass().getName();
            StringBuilder sb2 = new StringBuilder(strQ.length() + 8 + name2.length() + 1);
            y.a.i(sb2, "<", strQ, " threw ", name2);
            sb2.append(">");
            return sb2.toString();
        }
    }

    public static void r(int i10, int i11) {
        String strM;
        if (i10 < 0 || i10 >= i11) {
            if (i10 < 0) {
                strM = m("%s (%s) must not be negative", "index", Integer.valueOf(i10));
            } else {
                if (i11 < 0) {
                    throw new IllegalArgumentException(androidx.compose.foundation.c.p(i11, "negative size: ", new StringBuilder(String.valueOf(i11).length() + 15)));
                }
                strM = m("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i10), Integer.valueOf(i11));
            }
            throw new IndexOutOfBoundsException(strM);
        }
    }

    public static void s(int i10, Object obj) {
        if (obj == null) {
            throw new NullPointerException(androidx.compose.foundation.c.p(i10, "at index ", new StringBuilder(String.valueOf(i10).length() + 9)));
        }
    }

    public static void u(int i10, int i11) {
        if (i10 < 0 || i10 > i11) {
            throw new IndexOutOfBoundsException(z(i10, i11, "index"));
        }
    }

    public static void w(int i10, int i11, int i12) {
        if (i10 < 0 || i11 < i10 || i11 > i12) {
            throw new IndexOutOfBoundsException((i10 < 0 || i10 > i12) ? z(i10, i12, "start index") : (i11 < 0 || i11 > i12) ? z(i11, i12, "end index") : m("end index (%s) must not be less than start index (%s)", Integer.valueOf(i11), Integer.valueOf(i10)));
        }
    }

    public static String z(int i10, int i11, String str) {
        if (i10 < 0) {
            return m("%s (%s) must not be negative", str, Integer.valueOf(i10));
        }
        if (i11 >= 0) {
            return m("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i10), Integer.valueOf(i11));
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.p(i11, "negative size: ", new StringBuilder(String.valueOf(i11).length() + 15)));
    }

    public abstract g9 A(i9 i9Var);

    public abstract boolean B(q9 q9Var, Object obj, Object obj2);

    public abstract void o(p9 p9Var, Thread thread);

    public abstract void t(p9 p9Var, p9 p9Var2);

    public String toString() {
        switch (this.f13620i) {
            case 9:
                return ((w9) this).f13669m.toString();
            default:
                return super.toString();
        }
    }

    public abstract boolean v(q9 q9Var, p9 p9Var, p9 p9Var2);

    public abstract boolean x(q9 q9Var, g9 g9Var, g9 g9Var2);

    public abstract p9 y(i9 i9Var);
}
