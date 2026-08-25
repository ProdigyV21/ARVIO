package kotlin.collections;

import java.io.IOException;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r extends qb.l {
    public static ga.m G(Object[] objArr) {
        return objArr.length == 0 ? ga.g.f15574a : new ga.p(objArr, 2);
    }

    public static boolean H(char[] cArr, char c10) {
        int length = cArr.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                i10 = -1;
                break;
            }
            if (c10 == cArr[i10]) {
                break;
            }
            i10++;
        }
        return i10 >= 0;
    }

    public static boolean I(int[] iArr, int i10) {
        int length = iArr.length;
        int i11 = 0;
        while (true) {
            if (i11 >= length) {
                i11 = -1;
                break;
            }
            if (i10 == iArr[i11]) {
                break;
            }
            i11++;
        }
        return i11 >= 0;
    }

    public static boolean J(Object[] objArr, Object obj) {
        return d0(objArr, obj) >= 0;
    }

    public static void K(int i10, int i11, int i12, int[] iArr, int[] iArr2) {
        System.arraycopy(iArr, i11, iArr2, i10, i12 - i11);
    }

    public static void L(byte[] bArr, int i10, int i11, byte[] bArr2, int i12) {
        System.arraycopy(bArr, i11, bArr2, i10, i12 - i11);
    }

    public static void M(char[] cArr, char[] cArr2, int i10, int i11, int i12) {
        System.arraycopy(cArr, i11, cArr2, i10, i12 - i11);
    }

    public static void N(Object[] objArr, Object[] objArr2, int i10, int i11, int i12) {
        System.arraycopy(objArr, i11, objArr2, i10, i12 - i11);
    }

    public static void O(int i10, int i11, int i12, int[] iArr, int[] iArr2) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 8) != 0) {
            i11 = iArr.length;
        }
        System.arraycopy(iArr, 0, iArr2, i10, i11);
    }

    public static void P(byte[] bArr, int i10, int i11, byte[] bArr2) {
        System.arraycopy(bArr, i10, bArr2, 0, i11 - i10);
    }

    public static void Q(float[] fArr, float[] fArr2, int i10) {
        System.arraycopy(fArr, 0, fArr2, 0, (i10 & 8) != 0 ? fArr.length : 6);
    }

    public static void R(Object[] objArr, Object[] objArr2, int i10, int i11, int i12, int i13) {
        if ((i13 & 2) != 0) {
            i10 = 0;
        }
        if ((i13 & 4) != 0) {
            i11 = 0;
        }
        if ((i13 & 8) != 0) {
            i12 = objArr.length;
        }
        System.arraycopy(objArr, i11, objArr2, i10, i12 - i11);
    }

    public static Object[] S(Object[] objArr, int i10, int i11) {
        qb.l.j(i11, objArr.length);
        return Arrays.copyOfRange(objArr, i10, i11);
    }

    public static List T(int i10, Object[] objArr) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
        }
        int length = objArr.length - i10;
        if (length < 0) {
            length = 0;
        }
        if (length < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(length, "Requested element count ", " is less than zero.").toString());
        }
        if (length == 0) {
            return z.f19728i;
        }
        int length2 = objArr.length;
        if (length >= length2) {
            return o0(objArr);
        }
        if (length == 1) {
            return Collections.singletonList(objArr[length2 - 1]);
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i11 = length2 - length; i11 < length2; i11++) {
            arrayList.add(objArr[i11]);
        }
        return arrayList;
    }

    public static void U(int[] iArr, int i10, int i11, int i12) {
        if ((i12 & 4) != 0) {
            i11 = iArr.length;
        }
        Arrays.fill(iArr, 0, i11, i10);
    }

    public static void V(long[] jArr) {
        Arrays.fill(jArr, 0, jArr.length, -9187201950435737472L);
    }

    public static void W(Object[] objArr, c2.a aVar) {
        Arrays.fill(objArr, 0, objArr.length, aVar);
    }

    public static ArrayList X(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Object Y(Object[] objArr) {
        if (objArr.length != 0) {
            return objArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static Object Z(Object[] objArr) {
        if (objArr.length == 0) {
            return null;
        }
        return objArr[0];
    }

    public static x7.i a0(int[] iArr) {
        return new x7.i(0, iArr.length - 1, 1);
    }

    public static Integer b0(int[] iArr, int i10) {
        if (i10 < 0 || i10 >= iArr.length) {
            return null;
        }
        return Integer.valueOf(iArr[i10]);
    }

    public static Object c0(int i10, Object[] objArr) {
        if (i10 < 0 || i10 >= objArr.length) {
            return null;
        }
        return objArr[i10];
    }

    public static int d0(Object[] objArr, Object obj) {
        int i10 = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i10 < length) {
                if (objArr[i10] == null) {
                    return i10;
                }
                i10++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i10 < length2) {
            if (obj.equals(objArr[i10])) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    public static final void e0(Object[] objArr, StringBuilder sb2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, r7.l lVar) throws IOException {
        sb2.append(charSequence2);
        int i10 = 0;
        for (Object obj : objArr) {
            i10++;
            if (i10 > 1) {
                sb2.append(charSequence);
            }
            f4.f.b(sb2, obj, lVar);
        }
        sb2.append(charSequence3);
    }

    public static String f0(Object[] objArr, String str, String str2, String str3, r7.l lVar, int i10) {
        if ((i10 & 1) != 0) {
            str = ", ";
        }
        String str4 = str;
        String str5 = (i10 & 2) != 0 ? "" : str2;
        String str6 = (i10 & 4) != 0 ? "" : str3;
        if ((i10 & 32) != 0) {
            lVar = null;
        }
        StringBuilder sb2 = new StringBuilder();
        e0(objArr, sb2, str4, str5, str6, lVar);
        return sb2.toString();
    }

    public static Object g0(Object[] objArr) {
        if (objArr.length != 0) {
            return objArr[objArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static int h0(int[] iArr) {
        if (iArr.length == 0) {
            throw new NoSuchElementException();
        }
        int i10 = iArr[0];
        int i11 = 1;
        int length = iArr.length - 1;
        if (1 <= length) {
            while (true) {
                int i12 = iArr[i11];
                if (i10 < i12) {
                    i10 = i12;
                }
                if (i11 == length) {
                    break;
                }
                i11++;
            }
        }
        return i10;
    }

    public static byte[] i0(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int length2 = bArr2.length;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, length + length2);
        System.arraycopy(bArr2, 0, bArrCopyOf, length, length2);
        return bArrCopyOf;
    }

    public static Object j0(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return objArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static List k0(Object[] objArr, Comparator comparator) {
        if (objArr.length != 0) {
            objArr = Arrays.copyOf(objArr, objArr.length);
            if (objArr.length > 1) {
                Arrays.sort(objArr, comparator);
            }
        }
        return Arrays.asList(objArr);
    }

    public static List l0(int i10, byte[] bArr) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Requested element count ", " is less than zero.").toString());
        }
        if (i10 == 0) {
            return z.f19728i;
        }
        if (i10 >= bArr.length) {
            return n0(bArr);
        }
        if (i10 == 1) {
            return Collections.singletonList(Byte.valueOf(bArr[0]));
        }
        ArrayList arrayList = new ArrayList(i10);
        int i11 = 0;
        for (byte b10 : bArr) {
            arrayList.add(Byte.valueOf(b10));
            i11++;
            if (i11 == i10) {
                break;
            }
        }
        return arrayList;
    }

    public static final void m0(Object[] objArr, AbstractSet abstractSet) {
        for (Object obj : objArr) {
            abstractSet.add(obj);
        }
    }

    public static List n0(byte[] bArr) {
        int length = bArr.length;
        if (length == 0) {
            return z.f19728i;
        }
        if (length == 1) {
            return Collections.singletonList(Byte.valueOf(bArr[0]));
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte b10 : bArr) {
            arrayList.add(Byte.valueOf(b10));
        }
        return arrayList;
    }

    public static List o0(Object[] objArr) {
        int length = objArr.length;
        return length != 0 ? length != 1 ? new ArrayList(new o(objArr, false)) : Collections.singletonList(objArr[0]) : z.f19728i;
    }

    public static Set p0(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return b0.f19686i;
        }
        if (length == 1) {
            return Collections.singleton(objArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(i0.q0(objArr.length));
        m0(objArr, linkedHashSet);
        return linkedHashSet;
    }

    public static ArrayList q0(Object[] objArr, Object[] objArr2) {
        int iMin = Math.min(objArr.length, objArr2.length);
        ArrayList arrayList = new ArrayList(iMin);
        for (int i10 = 0; i10 < iMin; i10++) {
            arrayList.add(new x6.x(objArr[i10], objArr2[i10]));
        }
        return arrayList;
    }
}
