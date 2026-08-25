package com.google.android.gms.internal.fido;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b implements r0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static volatile a f13752l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Object f13751i = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final p1 f13753m = new p1(TtmlNode.ATTR_ID);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final p1 f13754n = new p1(LinkHeader.Parameters.Type);

    public static int a(int i10, int i11) {
        RoundingMode roundingMode = RoundingMode.CEILING;
        roundingMode.getClass();
        if (i11 == 0) {
            throw new ArithmeticException("/ by zero");
        }
        int i12 = i10 / i11;
        int i13 = i10 - (i11 * i12);
        if (i13 == 0) {
            return i12;
        }
        int i14 = ((i10 ^ i11) >> 31) | 1;
        switch (b1.f13758a[roundingMode.ordinal()]) {
            case 1:
                throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
            case 2:
                return i12;
            case 3:
                if (i14 >= 0) {
                    return i12;
                }
                break;
            case 4:
                break;
            case 5:
                if (i14 <= 0) {
                    return i12;
                }
                break;
            case 6:
            case 7:
            case 8:
                int iAbs = Math.abs(i13);
                int iAbs2 = iAbs - (Math.abs(i11) - iAbs);
                if (iAbs2 == 0) {
                    RoundingMode roundingMode2 = RoundingMode.HALF_UP;
                    RoundingMode roundingMode3 = RoundingMode.HALF_EVEN;
                    return i12;
                }
                if (iAbs2 <= 0) {
                    return i12;
                }
                break;
            default:
                throw new AssertionError();
        }
        return i12 + i14;
    }

    public static int b(s sVar) {
        Iterator it = sVar.iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            Object next = it.next();
            iHashCode += next != null ? next.hashCode() : 0;
        }
        return iHashCode;
    }

    public static String c(i1 i1Var) {
        StringBuilder sb2 = new StringBuilder(i1Var.d());
        for (int i10 = 0; i10 < i1Var.d(); i10++) {
            byte bA = i1Var.a(i10);
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

    public static String d(String str, Object... objArr) {
        int length;
        int length2;
        int iIndexOf;
        String strM;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            length = objArr.length;
            if (i11 >= length) {
                break;
            }
            Object obj = objArr[i11];
            if (obj == null) {
                strM = "null";
            } else {
                try {
                    strM = obj.toString();
                } catch (Exception e5) {
                    String strT = androidx.compose.foundation.c.t(obj.getClass().getName(), "@", Integer.toHexString(System.identityHashCode(obj)));
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(strT), (Throwable) e5);
                    strM = androidx.fragment.app.a2.m("<", strT, " threw ", e5.getClass().getName(), ">");
                }
            }
            objArr[i11] = strM;
            i11++;
        }
        StringBuilder sb2 = new StringBuilder(str.length() + (length * 16));
        int i12 = 0;
        while (true) {
            length2 = objArr.length;
            if (i10 >= length2 || (iIndexOf = str.indexOf("%s", i12)) == -1) {
                break;
            }
            sb2.append((CharSequence) str, i12, iIndexOf);
            sb2.append(objArr[i10]);
            i10++;
            i12 = iIndexOf + 2;
        }
        sb2.append((CharSequence) str, i12, str.length());
        if (i10 < length2) {
            sb2.append(" [");
            sb2.append(objArr[i10]);
            for (int i13 = i10 + 1; i13 < objArr.length; i13++) {
                sb2.append(", ");
                sb2.append(objArr[i13]);
            }
            sb2.append(']');
        }
        return sb2.toString();
    }

    public static void e(int i10, int i11) {
        String strD;
        if (i10 < 0 || i10 >= i11) {
            if (i10 < 0) {
                strD = d("%s (%s) must not be negative", "index", Integer.valueOf(i10));
            } else {
                if (i11 < 0) {
                    throw new IllegalArgumentException(a0.c.i(i11, "negative size: "));
                }
                strD = d("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i10), Integer.valueOf(i11));
            }
            throw new IndexOutOfBoundsException(strD);
        }
    }

    public static void f(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(obj2)));
        }
        if (obj2 == null) {
            throw new NullPointerException(a0.c.l("null value in entry: ", obj.toString(), "=null"));
        }
    }

    public static final void g(StringBuilder sb2, Iterator it, a1.a aVar) {
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            sb2.append(a1.a.t(entry.getKey()));
            sb2.append(" : ");
            sb2.append(a1.a.t(entry.getValue()));
            while (it.hasNext()) {
                sb2.append(",\n  ");
                Map.Entry entry2 = (Map.Entry) it.next();
                sb2.append(a1.a.t(entry2.getKey()));
                sb2.append(" : ");
                sb2.append(a1.a.t(entry2.getValue()));
            }
        }
    }

    public static boolean h(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static boolean i(Comparator comparator, Collection collection) {
        Comparator comparator2;
        comparator.getClass();
        collection.getClass();
        if (collection instanceof SortedSet) {
            comparator2 = ((SortedSet) collection).comparator();
            if (comparator2 == null) {
                comparator2 = z.f13850l;
            }
        } else {
            if (!(collection instanceof g0)) {
                return false;
            }
            comparator2 = ((g0) collection).comparator();
        }
        return comparator.equals(comparator2);
    }

    public static byte[] j(byte[]... bArr) {
        int i10 = 0;
        int length = 0;
        while (true) {
            if (i10 >= bArr.length) {
                break;
            }
            length += bArr[i10].length;
            i10++;
        }
        byte[] bArr2 = new byte[length];
        int i11 = 0;
        for (byte[] bArr3 : bArr) {
            int length2 = bArr3.length;
            System.arraycopy(bArr3, 0, bArr2, i11, length2);
            i11 += length2;
        }
        return bArr2;
    }

    public static final q1 k(s1 s1Var) throws zzhj {
        try {
            r1 r1VarL = s1Var.l();
            if (r1VarL == null) {
                throw new zzhj("Parser being asked to parse an empty input stream");
            }
            try {
                try {
                    byte b10 = r1VarL.f13812a;
                    byte b11 = r1VarL.f13813b;
                    int i10 = 0;
                    if (b10 == -128) {
                        long jI = s1Var.i();
                        if (jI > 1000) {
                            throw new zzhj("Parser being asked to read a large CBOR array");
                        }
                        l(b11, jI);
                        q1[] q1VarArr = new q1[(int) jI];
                        while (i10 < jI) {
                            q1VarArr[i10] = k(s1Var);
                            i10++;
                        }
                        return new k1(q.k(q1VarArr));
                    }
                    if (b10 != -96) {
                        if (b10 == -64) {
                            throw new zzhj("Tags are currently unsupported");
                        }
                        if (b10 == -32) {
                            return new l1(s1Var.o());
                        }
                        if (b10 == 0 || b10 == 32) {
                            long j10 = s1Var.j();
                            l(b11, j10 > 0 ? j10 : ~j10);
                            return new n1(j10);
                        }
                        if (b10 == 64) {
                            s1Var.G((byte) 64);
                            byte[] bArrR = s1Var.R();
                            int length = bArrR.length;
                            l(b11, length);
                            return new m1(i1.h(length, bArrR));
                        }
                        if (b10 == 96) {
                            s1Var.G((byte) 96);
                            String str = new String(s1Var.R(), StandardCharsets.UTF_8);
                            l(b11, str.length());
                            return new p1(str);
                        }
                        throw new zzhj("Unidentifiable major type: " + ((b10 >> 5) & 7));
                    }
                    long jK = s1Var.k();
                    if (jK > 1000) {
                        throw new zzhj("Parser being asked to read a large CBOR map");
                    }
                    l(b11, jK);
                    int i11 = (int) jK;
                    io.sentry.internal.debugmeta.c[] cVarArr = new io.sentry.internal.debugmeta.c[i11];
                    q1 q1Var = null;
                    int i12 = 0;
                    while (i12 < jK) {
                        q1 q1VarK = k(s1Var);
                        if (q1Var != null && q1VarK.compareTo(q1Var) <= 0) {
                            throw new zzhf("Keys in CBOR Map not in strictly ascending natural order:\nPrevious key: " + q1Var.toString() + "\nCurrent key: " + q1VarK.toString());
                        }
                        cVarArr[i12] = new io.sentry.internal.debugmeta.c(20, q1VarK, false, k(s1Var));
                        i12++;
                        q1Var = q1VarK;
                    }
                    TreeMap treeMap = new TreeMap();
                    while (i10 < i11) {
                        io.sentry.internal.debugmeta.c cVar = cVarArr[i10];
                        if (treeMap.containsKey((q1) cVar.f17367l)) {
                            throw new zzhf("Attempted to add duplicate key to canonical CBOR Map.");
                        }
                        treeMap.put((q1) cVar.f17367l, (q1) cVar.f17368m);
                        i10++;
                    }
                    return new o1(v.c(treeMap));
                } catch (IOException | RuntimeException e5) {
                    e = e5;
                    throw new zzhj(e);
                }
            } catch (RuntimeException e6) {
                e = e6;
                throw new zzhj(e);
            }
        } catch (IOException e10) {
            throw new zzhj(e10);
        }
    }

    public static final void l(byte b10, long j10) throws zzhf {
        switch (b10) {
            case 24:
                if (j10 < 24) {
                    throw new zzhf(androidx.compose.material3.d.k(j10, "Integer value ", " after add info could have been represented in 0 additional bytes, but used 1"));
                }
                return;
            case 25:
                if (j10 < 256) {
                    throw new zzhf(androidx.compose.material3.d.k(j10, "Integer value ", " after add info could have been represented in 0-1 additional bytes, but used 2"));
                }
                return;
            case 26:
                if (j10 < 65536) {
                    throw new zzhf(androidx.compose.material3.d.k(j10, "Integer value ", " after add info could have been represented in 0-2 additional bytes, but used 4"));
                }
                return;
            case 27:
                if (j10 < 4294967296L) {
                    throw new zzhf(androidx.compose.material3.d.k(j10, "Integer value ", " after add info could have been represented in 0-4 additional bytes, but used 8"));
                }
                return;
            default:
                return;
        }
    }

    public static void m(int i10, int i11, int i12) {
        if (i10 < 0 || i11 < i10 || i11 > i12) {
            throw new IndexOutOfBoundsException((i10 < 0 || i10 > i12) ? n(i10, i12, "start index") : (i11 < 0 || i11 > i12) ? n(i11, i12, "end index") : d("end index (%s) must not be less than start index (%s)", Integer.valueOf(i11), Integer.valueOf(i10)));
        }
    }

    public static String n(int i10, int i11, String str) {
        if (i10 < 0) {
            return d("%s (%s) must not be negative", str, Integer.valueOf(i10));
        }
        if (i11 >= 0) {
            return d("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i10), Integer.valueOf(i11));
        }
        throw new IllegalArgumentException(a0.c.i(i11, "negative size: "));
    }
}
