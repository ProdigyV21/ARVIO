package kotlin.text;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection;
import com.arflix.tv.ui.components.f3;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o extends u {
    public static boolean A0(String str, char c10) {
        return str.length() > 0 && r0.j(str.charAt(0), c10, false);
    }

    public static String B0(String str, x7.i iVar) {
        return str.substring(iVar.f22619i, iVar.f22620l + 1);
    }

    public static String C0(char c10, String str, String str2) {
        int iE0 = e0(str, c10, 0, false, 6);
        return iE0 == -1 ? str2 : str.substring(iE0 + 1, str.length());
    }

    public static String D0(String str, String str2, String str3) {
        int iF0 = f0(str, str2, 0, false, 6);
        return iF0 == -1 ? str3 : str.substring(str2.length() + iF0, str.length());
    }

    public static String E0(char c10, String str, String str2) {
        int iK0 = k0(str, c10, 0, 6);
        return iK0 == -1 ? str2 : str.substring(iK0 + 1, str.length());
    }

    public static String F0(String str, String str2) {
        int iJ0 = j0(6, str, str2);
        return iJ0 == -1 ? str : str.substring(str2.length() + iJ0, str.length());
    }

    public static String G0(char c10, String str, String str2) {
        int iE0 = e0(str, c10, 0, false, 6);
        return iE0 == -1 ? str2 : str.substring(0, iE0);
    }

    public static String H0(String str, String str2) {
        int iF0 = f0(str, str2, 0, false, 6);
        return iF0 == -1 ? str : str.substring(0, iF0);
    }

    public static String I0(int i10, String str) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested character count ", " is less than zero.").toString());
        }
        int length = str.length();
        if (i10 > length) {
            i10 = length;
        }
        return str.substring(0, i10);
    }

    public static String J0(int i10, String str) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested character count ", " is less than zero.").toString());
        }
        int length = str.length();
        if (i10 > length) {
            i10 = length;
        }
        return str.substring(length - i10);
    }

    public static void K0(CharSequence charSequence, PersistentCollection.Builder builder) {
        for (int i10 = 0; i10 < charSequence.length(); i10++) {
            builder.add(Character.valueOf(charSequence.charAt(i10)));
        }
    }

    public static CharSequence L0(CharSequence charSequence) {
        int length = charSequence.length() - 1;
        int i10 = 0;
        boolean z = false;
        while (i10 <= length) {
            boolean zU = r0.u(charSequence.charAt(!z ? i10 : length));
            if (z) {
                if (!zU) {
                    break;
                }
                length--;
            } else if (zU) {
                i10++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i10, length + 1);
    }

    public static String M0(String str, char... cArr) {
        int length = str.length() - 1;
        int i10 = 0;
        boolean z = false;
        while (i10 <= length) {
            boolean zH = kotlin.collections.r.H(cArr, str.charAt(!z ? i10 : length));
            if (z) {
                if (!zH) {
                    break;
                }
                length--;
            } else if (zH) {
                i10++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i10, length + 1).toString();
    }

    public static CharSequence N0(String str) {
        int length = str.length() - 1;
        if (length < 0) {
            return "";
        }
        while (true) {
            int i10 = length - 1;
            if (!r0.u(str.charAt(length))) {
                return str.subSequence(0, length + 1);
            }
            if (i10 < 0) {
                return "";
            }
            length = i10;
        }
    }

    public static String O0(String str, char... cArr) {
        CharSequence charSequenceSubSequence;
        int length = str.length() - 1;
        if (length >= 0) {
            while (true) {
                int i10 = length - 1;
                if (!kotlin.collections.r.H(cArr, str.charAt(length))) {
                    charSequenceSubSequence = str.subSequence(0, length + 1);
                    break;
                }
                if (i10 < 0) {
                    break;
                }
                length = i10;
            }
            charSequenceSubSequence = "";
        } else {
            charSequenceSubSequence = "";
        }
        return charSequenceSubSequence.toString();
    }

    public static CharSequence P0(String str) {
        int length = str.length();
        for (int i10 = 0; i10 < length; i10++) {
            if (!r0.u(str.charAt(i10))) {
                return str.subSequence(i10, str.length());
            }
        }
        return "";
    }

    public static String Q0(String str, char... cArr) {
        CharSequence charSequenceSubSequence;
        int length = str.length();
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                charSequenceSubSequence = "";
                break;
            }
            if (!kotlin.collections.r.H(cArr, str.charAt(i10))) {
                charSequenceSubSequence = str.subSequence(i10, str.length());
                break;
            }
            i10++;
        }
        return charSequenceSubSequence.toString();
    }

    public static boolean T(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence2 instanceof String) {
            if (f0(charSequence, (String) charSequence2, 0, z, 2) >= 0) {
                return true;
            }
        } else if (d0(charSequence, charSequence2, 0, charSequence.length(), z, false) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean U(CharSequence charSequence, char c10) {
        return e0(charSequence, c10, 0, false, 2) >= 0;
    }

    public static String V(int i10, String str) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested character count ", " is less than zero.").toString());
        }
        int length = str.length();
        if (i10 > length) {
            i10 = length;
        }
        return str.substring(i10);
    }

    public static String W(int i10, String str) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested character count ", " is less than zero.").toString());
        }
        int length = str.length() - i10;
        if (length < 0) {
            length = 0;
        }
        return I0(length, str);
    }

    public static boolean X(String str, char c10) {
        return str.length() > 0 && r0.j(str.charAt(b0(str)), c10, false);
    }

    public static boolean Y(String str, CharSequence charSequence) {
        return charSequence instanceof String ? u.K((String) charSequence, str, false) : q0(charSequence, charSequence.length() - str.length(), str, 0, str.length(), false);
    }

    public static char Z(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            return charSequence.charAt(0);
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static Character a0(String str) {
        if (str.length() == 0) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static int b0(CharSequence charSequence) {
        return charSequence.length() - 1;
    }

    public static final int c0(CharSequence charSequence, String str, int i10, boolean z) {
        return (z || !(charSequence instanceof String)) ? d0(charSequence, str, i10, charSequence.length(), z, false) : ((String) charSequence).indexOf(str, i10);
    }

    public static final int d0(CharSequence charSequence, CharSequence charSequence2, int i10, int i11, boolean z, boolean z5) {
        x7.g gVar;
        CharSequence charSequence3 = charSequence2;
        int i12 = i10;
        int i13 = i11;
        if (z5) {
            int iB0 = b0(charSequence);
            if (i12 > iB0) {
                i12 = iB0;
            }
            if (i13 < 0) {
                i13 = 0;
            }
            gVar = new x7.g(i12, i13, -1);
        } else {
            if (i12 < 0) {
                i12 = 0;
            }
            int length = charSequence.length();
            if (i13 > length) {
                i13 = length;
            }
            gVar = new x7.i(i12, i13, 1);
        }
        boolean z10 = charSequence instanceof String;
        int i14 = gVar.f22621m;
        int i15 = gVar.f22620l;
        int i16 = gVar.f22619i;
        if (z10 && (charSequence3 instanceof String)) {
            if ((i14 > 0 && i16 <= i15) || (i14 < 0 && i15 <= i16)) {
                int i17 = i16;
                while (true) {
                    String str = (String) charSequence3;
                    String str2 = (String) charSequence;
                    int length2 = str.length();
                    if (!(!z ? str.regionMatches(0, str2, i17, length2) : str.regionMatches(z, 0, str2, i17, length2))) {
                        if (i17 == i15) {
                            break;
                        }
                        i17 += i14;
                    } else {
                        return i17;
                    }
                }
            }
        } else if ((i14 > 0 && i16 <= i15) || (i14 < 0 && i15 <= i16)) {
            int i18 = i16;
            while (!q0(charSequence3, 0, charSequence, i18, charSequence3.length(), z)) {
                if (i18 != i15) {
                    i18 += i14;
                    charSequence3 = charSequence2;
                }
            }
            return i18;
        }
        return -1;
    }

    public static int e0(CharSequence charSequence, char c10, int i10, boolean z, int i11) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        if ((i11 & 4) != 0) {
            z = false;
        }
        return (z || !(charSequence instanceof String)) ? g0(charSequence, new char[]{c10}, i10, z) : ((String) charSequence).indexOf(c10, i10);
    }

    public static /* synthetic */ int f0(CharSequence charSequence, String str, int i10, boolean z, int i11) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        if ((i11 & 4) != 0) {
            z = false;
        }
        return c0(charSequence, str, i10, z);
    }

    public static final int g0(CharSequence charSequence, char[] cArr, int i10, boolean z) {
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            int length = cArr.length;
            if (length == 0) {
                throw new NoSuchElementException("Array is empty.");
            }
            if (length != 1) {
                throw new IllegalArgumentException("Array has more than one element.");
            }
            return ((String) charSequence).indexOf(cArr[0], i10);
        }
        if (i10 < 0) {
            i10 = 0;
        }
        int iB0 = b0(charSequence);
        if (i10 > iB0) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(i10);
            for (char c10 : cArr) {
                if (r0.j(c10, cCharAt, z)) {
                    return i10;
                }
            }
            if (i10 == iB0) {
                return -1;
            }
            i10++;
        }
    }

    public static boolean h0(CharSequence charSequence) {
        for (int i10 = 0; i10 < charSequence.length(); i10++) {
            if (!r0.u(charSequence.charAt(i10))) {
                return false;
            }
        }
        return true;
    }

    public static char i0(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            return charSequence.charAt(b0(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static int j0(int i10, String str, String str2) {
        int iB0 = (i10 & 2) != 0 ? b0(str) : 0;
        return !androidx.compose.material3.d.B(str) ? d0(str, str2, iB0, 0, false, true) : str.lastIndexOf(str2, iB0);
    }

    public static int k0(CharSequence charSequence, char c10, int i10, int i11) {
        if ((i11 & 2) != 0) {
            i10 = b0(charSequence);
        }
        return !(charSequence instanceof String) ? l0(charSequence, new char[]{c10}, i10) : ((String) charSequence).lastIndexOf(c10, i10);
    }

    public static final int l0(CharSequence charSequence, char[] cArr, int i10) {
        if (cArr.length == 1 && (charSequence instanceof String)) {
            int length = cArr.length;
            if (length == 0) {
                throw new NoSuchElementException("Array is empty.");
            }
            if (length != 1) {
                throw new IllegalArgumentException("Array has more than one element.");
            }
            return ((String) charSequence).lastIndexOf(cArr[0], i10);
        }
        int iB0 = b0(charSequence);
        if (i10 > iB0) {
            i10 = iB0;
        }
        while (-1 < i10) {
            char cCharAt = charSequence.charAt(i10);
            for (char c10 : cArr) {
                if (r0.j(c10, cCharAt, false)) {
                    return i10;
                }
            }
            i10--;
        }
        return -1;
    }

    public static ga.p m0(String str) {
        return new ga.p(str, 4);
    }

    public static List n0(String str) {
        e eVar = new e(str);
        if (!eVar.hasNext()) {
            return z.f19728i;
        }
        Object next = eVar.next();
        if (!eVar.hasNext()) {
            return Collections.singletonList(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (eVar.hasNext()) {
            arrayList.add(eVar.next());
        }
        return arrayList;
    }

    public static String o0(int i10, String str) {
        CharSequence charSequenceSubSequence;
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Desired length ", " is less than zero."));
        }
        if (i10 <= str.length()) {
            charSequenceSubSequence = str.subSequence(0, str.length());
        } else {
            StringBuilder sb2 = new StringBuilder(i10);
            int length = i10 - str.length();
            int i11 = 1;
            if (1 <= length) {
                while (true) {
                    sb2.append('0');
                    if (i11 == length) {
                        break;
                    }
                    i11++;
                }
            }
            sb2.append((CharSequence) str);
            charSequenceSubSequence = sb2;
        }
        return charSequenceSubSequence.toString();
    }

    public static c p0(CharSequence charSequence, String[] strArr, int i10) {
        u0(i10);
        return new c(charSequence, i10, new f3(Arrays.asList(strArr), 3));
    }

    public static final boolean q0(CharSequence charSequence, int i10, CharSequence charSequence2, int i11, int i12, boolean z) {
        if (i11 < 0 || i10 < 0 || i10 > charSequence.length() - i12 || i11 > charSequence2.length() - i12) {
            return false;
        }
        for (int i13 = 0; i13 < i12; i13++) {
            if (!r0.j(charSequence.charAt(i10 + i13), charSequence2.charAt(i11 + i13), z)) {
                return false;
            }
        }
        return true;
    }

    public static String r0(String str, String str2) {
        return z0(str, str2) ? str.substring(str2.length()) : str;
    }

    public static String s0(String str, String str2) {
        return Y(str2, str) ? str.substring(0, str.length() - str2.length()) : str;
    }

    public static String t0(String str) {
        return (str.length() >= 2 && z0(str, "\"") && Y("\"", str)) ? str.substring(1, str.length() - 1) : str;
    }

    public static final void u0(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "Limit must be non-negative, but was ").toString());
        }
    }

    public static char v0(String str) {
        int length = str.length();
        if (length == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        if (length == 1) {
            return str.charAt(0);
        }
        throw new IllegalArgumentException("Char sequence has more than one element.");
    }

    public static final List w0(String str, CharSequence charSequence, int i10) {
        u0(i10);
        int iC0 = c0(charSequence, str, 0, false);
        if (iC0 == -1 || i10 == 1) {
            return Collections.singletonList(charSequence.toString());
        }
        boolean z = i10 > 0;
        int i11 = 10;
        if (z && i10 <= 10) {
            i11 = i10;
        }
        ArrayList arrayList = new ArrayList(i11);
        int length = 0;
        do {
            arrayList.add(charSequence.subSequence(length, iC0).toString());
            length = str.length() + iC0;
            if (z && arrayList.size() == i10 - 1) {
                break;
            }
            iC0 = c0(charSequence, str, length, false);
        } while (iC0 != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static List x0(CharSequence charSequence, char[] cArr, int i10) {
        int i11 = (i10 & 4) != 0 ? 0 : 2;
        if (cArr.length == 1) {
            return w0(String.valueOf(cArr[0]), charSequence, i11);
        }
        u0(i11);
        c<x7.i> cVar = new c(charSequence, i11, new f3(cArr, 2));
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(new ab.k(cVar, 1), 10));
        for (x7.i iVar : cVar) {
            arrayList.add(charSequence.subSequence(iVar.f22619i, iVar.f22620l + 1).toString());
        }
        return arrayList;
    }

    public static List y0(CharSequence charSequence, String[] strArr, int i10, int i11) {
        if ((i11 & 4) != 0) {
            i10 = 0;
        }
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() != 0) {
                return w0(str, charSequence, i10);
            }
        }
        c<x7.i> cVarP0 = p0(charSequence, strArr, i10);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(new ab.k(cVarP0, 1), 10));
        for (x7.i iVar : cVarP0) {
            arrayList.add(charSequence.subSequence(iVar.f22619i, iVar.f22620l + 1).toString());
        }
        return arrayList;
    }

    public static boolean z0(CharSequence charSequence, CharSequence charSequence2) {
        return ((charSequence instanceof String) && (charSequence2 instanceof String)) ? u.P((String) charSequence, (String) charSequence2, false) : q0(charSequence, 0, charSequence2, 0, charSequence2.length(), false);
    }
}
