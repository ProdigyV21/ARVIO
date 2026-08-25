package kotlin.text;

import androidx.media3.common.C;
import com.google.common.util.concurrent.r0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u extends t {
    public static String I(char[] cArr, int i10, int i11) {
        kotlin.collections.c cVar = kotlin.collections.f.Companion;
        int length = cArr.length;
        cVar.getClass();
        kotlin.collections.c.a(i10, i11, length);
        return new String(cArr, i10, i11 - i10);
    }

    public static boolean J(CharSequence charSequence, CharSequence charSequence2) {
        boolean z = charSequence instanceof String;
        if (z && charSequence2 != null) {
            return ((String) charSequence).contentEquals(charSequence2);
        }
        if (z && (charSequence2 instanceof String)) {
            return charSequence.equals(charSequence2);
        }
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence != null && charSequence2 != null && charSequence.length() == charSequence2.length()) {
            int length = charSequence.length();
            for (int i10 = 0; i10 < length; i10++) {
                if (charSequence.charAt(i10) == charSequence2.charAt(i10)) {
                }
            }
            return true;
        }
        return false;
    }

    public static boolean K(String str, String str2, boolean z) {
        return !z ? str.endsWith(str2) : str.regionMatches(true, str.length() - str2.length(), str2, 0, str2.length());
    }

    public static boolean L(String str, String str2, boolean z) {
        return str == null ? str2 == null : !z ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public static final void M(String str) {
        throw new NumberFormatException(androidx.compose.material3.d.i('\'', "Invalid number format: '", str));
    }

    public static String N(int i10, String str) {
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.k("Count 'n' must be non-negative, but was ", i10, '.').toString());
        }
        if (i10 == 0) {
            return "";
        }
        int i11 = 1;
        if (i10 == 1) {
            return str.toString();
        }
        int length = str.length();
        if (length == 0) {
            return "";
        }
        if (length != 1) {
            StringBuilder sb2 = new StringBuilder(str.length() * i10);
            if (1 <= i10) {
                while (true) {
                    sb2.append((CharSequence) str);
                    if (i11 == i10) {
                        break;
                    }
                    i11++;
                }
            }
            return sb2.toString();
        }
        char cCharAt = str.charAt(0);
        char[] cArr = new char[i10];
        for (int i12 = 0; i12 < i10; i12++) {
            cArr[i12] = cCharAt;
        }
        return new String(cArr);
    }

    public static String O(String str, String str2, String str3, boolean z) {
        int i10 = 0;
        int iC0 = o.c0(str, str2, 0, z);
        if (iC0 < 0) {
            return str;
        }
        int length = str2.length();
        int i11 = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb2 = new StringBuilder(length2);
        do {
            sb2.append((CharSequence) str, i10, iC0);
            sb2.append(str3);
            i10 = iC0 + length;
            if (iC0 >= str.length()) {
                break;
            }
            iC0 = o.c0(str, str2, iC0 + i11, z);
        } while (iC0 > 0);
        sb2.append((CharSequence) str, i10, str.length());
        return sb2.toString();
    }

    public static boolean P(String str, String str2, boolean z) {
        if (!z) {
            return str.startsWith(str2);
        }
        int length = str2.length();
        return !z ? str.regionMatches(0, str2, 0, length) : str.regionMatches(z, 0, str2, 0, length);
    }

    public static Integer R(String str) {
        boolean z;
        int i10;
        int i11;
        r0.c(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i12 = 0;
        char cCharAt = str.charAt(0);
        int iC = kotlin.jvm.internal.p.c(cCharAt, 48);
        int i13 = C.RATE_UNSET_INT;
        if (iC < 0) {
            i10 = 1;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '+') {
                z = false;
            } else {
                if (cCharAt != '-') {
                    return null;
                }
                i13 = Integer.MIN_VALUE;
                z = true;
            }
        } else {
            z = false;
            i10 = 0;
        }
        int i14 = -59652323;
        while (i10 < length) {
            int iDigit = Character.digit((int) str.charAt(i10), 10);
            if (iDigit < 0) {
                return null;
            }
            if ((i12 < i14 && (i14 != -59652323 || i12 < (i14 = i13 / 10))) || (i11 = i12 * 10) < i13 + iDigit) {
                return null;
            }
            i12 = i11 - iDigit;
            i10++;
        }
        return z ? Integer.valueOf(i12) : Integer.valueOf(-i12);
    }

    public static Long S(String str) {
        boolean z;
        r0.c(10);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i10 = 0;
        char cCharAt = str.charAt(0);
        int iC = kotlin.jvm.internal.p.c(cCharAt, 48);
        long j10 = C.TIME_UNSET;
        if (iC < 0) {
            z = true;
            if (length == 1) {
                return null;
            }
            if (cCharAt == '+') {
                z = false;
                i10 = 1;
            } else {
                if (cCharAt != '-') {
                    return null;
                }
                j10 = Long.MIN_VALUE;
                i10 = 1;
            }
        } else {
            z = false;
        }
        long j11 = 0;
        long j12 = -256204778801521550L;
        while (i10 < length) {
            int iDigit = Character.digit((int) str.charAt(i10), 10);
            if (iDigit < 0) {
                return null;
            }
            if (j11 < j12) {
                if (j12 != -256204778801521550L) {
                    return null;
                }
                j12 = j10 / ((long) 10);
                if (j11 < j12) {
                    return null;
                }
            }
            long j13 = j11 * ((long) 10);
            long j14 = iDigit;
            if (j13 < j10 + j14) {
                return null;
            }
            j11 = j13 - j14;
            i10++;
        }
        return z ? Long.valueOf(j11) : Long.valueOf(-j11);
    }
}
