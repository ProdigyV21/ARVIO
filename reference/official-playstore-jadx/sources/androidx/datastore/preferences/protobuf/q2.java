package androidx.datastore.preferences.protobuf;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes3.dex */
public abstract class q2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f2507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f3 f2508b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f3 f2509c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h3 f2510d;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.datastore.preferences.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            cls = null;
        }
        f2507a = cls;
        f2508b = w(false);
        f2509c = w(true);
        f2510d = new h3();
    }

    public static void A(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.K(z, list, i10);
    }

    public static void B(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.x(z, list, i10);
    }

    public static void C(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.e(z, list, i10);
    }

    public static void D(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.l(z, list, i10);
    }

    public static void E(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.A(z, list, i10);
    }

    public static void F(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.f(z, list, i10);
    }

    public static void G(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.o(z, list, i10);
    }

    public static void H(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.v(z, list, i10);
    }

    public static void I(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.j(z, list, i10);
    }

    public static void J(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.s(z, list, i10);
    }

    public static void K(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.c(z, list, i10);
    }

    public static void L(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.t(z, list, i10);
    }

    public static void M(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.a(z, list, i10);
    }

    public static void N(int i10, List list, a4 a4Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        a4Var.C(z, list, i10);
    }

    public static int a(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iT = u.t(i10) * size;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iT += u.n((m) list.get(i11));
        }
        return iT;
    }

    public static int b(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (u.t(i10) * size) + c(list);
    }

    public static int c(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iR = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iR += u.r(((Integer) list.get(i10)).intValue());
        }
        return iR;
    }

    public static int d(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return u.o(i10) * size;
    }

    public static int e(List list) {
        return list.size() * 4;
    }

    public static int f(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return u.p(i10) * size;
    }

    public static int g(List list) {
        return list.size() * 8;
    }

    public static int h(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (u.t(i10) * size) + i(list);
    }

    public static int i(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iR = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iR += u.r(((Integer) list.get(i10)).intValue());
        }
        return iR;
    }

    public static int j(int i10, List list) {
        if (list.size() == 0) {
            return 0;
        }
        return (u.t(i10) * list.size()) + k(list);
    }

    public static int k(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iW = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iW += u.w(((Long) list.get(i10)).longValue());
        }
        return iW;
    }

    public static int l(int i10, List list, o2 o2Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iT = u.t(i10) * size;
        for (int i11 = 0; i11 < size; i11++) {
            int iG = ((c) ((v1) list.get(i11))).g(o2Var);
            iT += u.v(iG) + iG;
        }
        return iT;
    }

    public static int m(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (u.t(i10) * size) + n(list);
    }

    public static int n(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iV = 0;
        for (int i10 = 0; i10 < size; i10++) {
            int iIntValue = ((Integer) list.get(i10)).intValue();
            iV += u.v((iIntValue >> 31) ^ (iIntValue << 1));
        }
        return iV;
    }

    public static int o(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (u.t(i10) * size) + p(list);
    }

    public static int p(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iW = 0;
        for (int i10 = 0; i10 < size; i10++) {
            long jLongValue = ((Long) list.get(i10)).longValue();
            iW += u.w((jLongValue >> 63) ^ (jLongValue << 1));
        }
        return iW;
    }

    public static int q(int i10, List list) {
        int size = list.size();
        int i11 = 0;
        if (size == 0) {
            return 0;
        }
        int iT = u.t(i10) * size;
        if (!(list instanceof i1)) {
            while (i11 < size) {
                Object obj = list.get(i11);
                if (obj instanceof m) {
                    int size2 = ((m) obj).size();
                    iT = u.v(size2) + size2 + iT;
                } else {
                    iT = u.s((String) obj) + iT;
                }
                i11++;
            }
            return iT;
        }
        i1 i1Var = (i1) list;
        while (i11 < size) {
            Object objJ0 = i1Var.J0(i11);
            if (objJ0 instanceof m) {
                int size3 = ((m) objJ0).size();
                iT = u.v(size3) + size3 + iT;
            } else {
                iT = u.s((String) objJ0) + iT;
            }
            i11++;
        }
        return iT;
    }

    public static int r(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (u.t(i10) * size) + s(list);
    }

    public static int s(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iV = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iV += u.v(((Integer) list.get(i10)).intValue());
        }
        return iV;
    }

    public static int t(int i10, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (u.t(i10) * size) + u(list);
    }

    public static int u(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iW = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iW += u.w(((Long) list.get(i10)).longValue());
        }
        return iW;
    }

    public static Object v(int i10, List list, y0 y0Var, Object obj, f3 f3Var) {
        if (y0Var == null) {
            return obj;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                if (!y0Var.a()) {
                    obj = z(i10, iIntValue, obj, f3Var);
                    it.remove();
                }
            }
            return obj;
        }
        int size = list.size();
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            Integer num = (Integer) list.get(i12);
            int iIntValue2 = num.intValue();
            if (y0Var.a()) {
                if (i12 != i11) {
                    list.set(i11, num);
                }
                i11++;
            } else {
                obj = z(i10, iIntValue2, obj, f3Var);
            }
        }
        if (i11 != size) {
            list.subList(i11, size).clear();
        }
        return obj;
    }

    public static f3 w(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.datastore.preferences.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls != null) {
            try {
                return (f3) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
            } catch (Throwable unused2) {
            }
        }
        return null;
    }

    public static void x(f3 f3Var, Object obj, Object obj2) {
        ((h3) f3Var).getClass();
        r0 r0Var = (r0) obj;
        g3 g3Var = r0Var.unknownFields;
        g3 g3Var2 = ((r0) obj2).unknownFields;
        if (!g3Var2.equals(g3.f2439f)) {
            int i10 = g3Var.f2440a + g3Var2.f2440a;
            int[] iArrCopyOf = Arrays.copyOf(g3Var.f2441b, i10);
            System.arraycopy(g3Var2.f2441b, 0, iArrCopyOf, g3Var.f2440a, g3Var2.f2440a);
            Object[] objArrCopyOf = Arrays.copyOf(g3Var.f2442c, i10);
            System.arraycopy(g3Var2.f2442c, 0, objArrCopyOf, g3Var.f2440a, g3Var2.f2440a);
            g3Var = new g3(i10, iArrCopyOf, objArrCopyOf, true);
        }
        r0Var.unknownFields = g3Var;
    }

    public static boolean y(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static Object z(int i10, int i11, Object obj, f3 f3Var) {
        if (obj == null) {
            ((h3) f3Var).getClass();
            obj = g3.b();
        }
        ((h3) f3Var).getClass();
        ((g3) obj).c(i10 << 3, Long.valueOf(i11));
        return obj;
    }
}
