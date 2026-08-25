package t;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class l0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f21847i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f21848l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f21849m;

    public l0(int i10) {
        this.f21847i = i10 == 0 ? u.a.f22008a : new int[i10];
        this.f21848l = i10 == 0 ? u.a.f22010c : new Object[i10 << 1];
    }

    public final int b(Object obj) {
        int i10 = this.f21849m * 2;
        Object[] objArr = this.f21848l;
        if (obj == null) {
            for (int i11 = 1; i11 < i10; i11 += 2) {
                if (objArr[i11] == null) {
                    return i11 >> 1;
                }
            }
            return -1;
        }
        for (int i12 = 1; i12 < i10; i12 += 2) {
            if (obj.equals(objArr[i12])) {
                return i12 >> 1;
            }
        }
        return -1;
    }

    public final int c(Object obj, int i10) {
        int i11 = this.f21849m;
        if (i11 == 0) {
            return -1;
        }
        int iA = u.a.a(i11, i10, this.f21847i);
        if (iA < 0 || kotlin.jvm.internal.p.a(obj, this.f21848l[iA << 1])) {
            return iA;
        }
        int i12 = iA + 1;
        while (i12 < i11 && this.f21847i[i12] == i10) {
            if (kotlin.jvm.internal.p.a(obj, this.f21848l[i12 << 1])) {
                return i12;
            }
            i12++;
        }
        for (int i13 = iA - 1; i13 >= 0 && this.f21847i[i13] == i10; i13--) {
            if (kotlin.jvm.internal.p.a(obj, this.f21848l[i13 << 1])) {
                return i13;
            }
        }
        return ~i12;
    }

    public final void clear() {
        if (this.f21849m > 0) {
            this.f21847i = u.a.f22008a;
            this.f21848l = u.a.f22010c;
            this.f21849m = 0;
        }
        if (this.f21849m > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return b(obj) >= 0;
    }

    public final int e(Object obj) {
        return obj == null ? g() : c(obj, obj.hashCode());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof l0) {
                int i10 = this.f21849m;
                if (i10 != ((l0) obj).f21849m) {
                    return false;
                }
                l0 l0Var = (l0) obj;
                for (int i11 = 0; i11 < i10; i11++) {
                    Object objH = h(i11);
                    Object objK = k(i11);
                    Object obj2 = l0Var.get(objH);
                    if (objK == null) {
                        if (obj2 != null || !l0Var.containsKey(objH)) {
                            return false;
                        }
                    } else if (!objK.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.f21849m != ((Map) obj).size()) {
                return false;
            }
            int i12 = this.f21849m;
            for (int i13 = 0; i13 < i12; i13++) {
                Object objH2 = h(i13);
                Object objK2 = k(i13);
                Object obj3 = ((Map) obj).get(objH2);
                if (objK2 == null) {
                    if (obj3 != null || !((Map) obj).containsKey(objH2)) {
                        return false;
                    }
                } else if (!objK2.equals(obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final int g() {
        int i10 = this.f21849m;
        if (i10 == 0) {
            return -1;
        }
        int iA = u.a.a(i10, 0, this.f21847i);
        if (iA < 0 || this.f21848l[iA << 1] == null) {
            return iA;
        }
        int i11 = iA + 1;
        while (i11 < i10 && this.f21847i[i11] == 0) {
            if (this.f21848l[i11 << 1] == null) {
                return i11;
            }
            i11++;
        }
        for (int i12 = iA - 1; i12 >= 0 && this.f21847i[i12] == 0; i12--) {
            if (this.f21848l[i12 << 1] == null) {
                return i12;
            }
        }
        return ~i11;
    }

    public Object get(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return this.f21848l[(iE << 1) + 1];
        }
        return null;
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int iE = e(obj);
        return iE >= 0 ? this.f21848l[(iE << 1) + 1] : obj2;
    }

    public final Object h(int i10) {
        if (i10 < 0 || i10 >= this.f21849m) {
            throw new IllegalArgumentException(a0.c.i(i10, "Expected index to be within 0..size()-1, but was ").toString());
        }
        return this.f21848l[i10 << 1];
    }

    public final int hashCode() {
        int[] iArr = this.f21847i;
        Object[] objArr = this.f21848l;
        int i10 = this.f21849m;
        int i11 = 1;
        int i12 = 0;
        int iHashCode = 0;
        while (i12 < i10) {
            Object obj = objArr[i11];
            iHashCode += (obj != null ? obj.hashCode() : 0) ^ iArr[i12];
            i12++;
            i11 += 2;
        }
        return iHashCode;
    }

    public final Object i(int i10) {
        int i11;
        if (i10 < 0 || i10 >= (i11 = this.f21849m)) {
            throw new IllegalArgumentException(a0.c.i(i10, "Expected index to be within 0..size()-1, but was ").toString());
        }
        Object[] objArr = this.f21848l;
        int i12 = i10 << 1;
        Object obj = objArr[i12 + 1];
        if (i11 <= 1) {
            clear();
            return obj;
        }
        int i13 = i11 - 1;
        int[] iArr = this.f21847i;
        if (iArr.length <= 8 || i11 >= iArr.length / 3) {
            if (i10 < i13) {
                int i14 = i10 + 1;
                kotlin.collections.r.K(i10, i14, i11, iArr, iArr);
                Object[] objArr2 = this.f21848l;
                kotlin.collections.r.N(objArr2, objArr2, i12, i14 << 1, i11 << 1);
            }
            Object[] objArr3 = this.f21848l;
            int i15 = i13 << 1;
            objArr3[i15] = null;
            objArr3[i15 + 1] = null;
        } else {
            int i16 = i11 > 8 ? i11 + (i11 >> 1) : 8;
            this.f21847i = Arrays.copyOf(iArr, i16);
            this.f21848l = Arrays.copyOf(this.f21848l, i16 << 1);
            if (i11 != this.f21849m) {
                throw new ConcurrentModificationException();
            }
            if (i10 > 0) {
                kotlin.collections.r.K(0, 0, i10, iArr, this.f21847i);
                kotlin.collections.r.N(objArr, this.f21848l, 0, 0, i12);
            }
            if (i10 < i13) {
                int i17 = i10 + 1;
                kotlin.collections.r.K(i10, i17, i11, iArr, this.f21847i);
                kotlin.collections.r.N(objArr, this.f21848l, i12, i17 << 1, i11 << 1);
            }
        }
        if (i11 != this.f21849m) {
            throw new ConcurrentModificationException();
        }
        this.f21849m = i13;
        return obj;
    }

    public final boolean isEmpty() {
        return this.f21849m <= 0;
    }

    public final Object j(int i10, Object obj) {
        if (i10 < 0 || i10 >= this.f21849m) {
            throw new IllegalArgumentException(a0.c.i(i10, "Expected index to be within 0..size()-1, but was ").toString());
        }
        int i11 = (i10 << 1) + 1;
        Object[] objArr = this.f21848l;
        Object obj2 = objArr[i11];
        objArr[i11] = obj;
        return obj2;
    }

    public final Object k(int i10) {
        if (i10 < 0 || i10 >= this.f21849m) {
            throw new IllegalArgumentException(a0.c.i(i10, "Expected index to be within 0..size()-1, but was ").toString());
        }
        return this.f21848l[(i10 << 1) + 1];
    }

    public final Object put(Object obj, Object obj2) {
        int i10 = this.f21849m;
        int iHashCode = obj != null ? obj.hashCode() : 0;
        int iC = obj != null ? c(obj, iHashCode) : g();
        if (iC >= 0) {
            int i11 = (iC << 1) + 1;
            Object[] objArr = this.f21848l;
            Object obj3 = objArr[i11];
            objArr[i11] = obj2;
            return obj3;
        }
        int i12 = ~iC;
        int[] iArr = this.f21847i;
        if (i10 >= iArr.length) {
            int i13 = 8;
            if (i10 >= 8) {
                i13 = (i10 >> 1) + i10;
            } else if (i10 < 4) {
                i13 = 4;
            }
            this.f21847i = Arrays.copyOf(iArr, i13);
            this.f21848l = Arrays.copyOf(this.f21848l, i13 << 1);
            if (i10 != this.f21849m) {
                throw new ConcurrentModificationException();
            }
        }
        if (i12 < i10) {
            int[] iArr2 = this.f21847i;
            int i14 = i12 + 1;
            kotlin.collections.r.K(i14, i12, i10, iArr2, iArr2);
            Object[] objArr2 = this.f21848l;
            kotlin.collections.r.N(objArr2, objArr2, i14 << 1, i12 << 1, this.f21849m << 1);
        }
        int i15 = this.f21849m;
        if (i10 == i15) {
            int[] iArr3 = this.f21847i;
            if (i12 < iArr3.length) {
                iArr3[i12] = iHashCode;
                Object[] objArr3 = this.f21848l;
                int i16 = i12 << 1;
                objArr3[i16] = obj;
                objArr3[i16 + 1] = obj2;
                this.f21849m = i15 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? put(obj, obj2) : obj3;
    }

    public Object remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return i(iE);
        }
        return null;
    }

    public final Object replace(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE >= 0) {
            return j(iE, obj2);
        }
        return null;
    }

    public final int size() {
        return this.f21849m;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb2 = new StringBuilder(this.f21849m * 28);
        sb2.append('{');
        int i10 = this.f21849m;
        for (int i11 = 0; i11 < i10; i11++) {
            if (i11 > 0) {
                sb2.append(", ");
            }
            Object objH = h(i11);
            if (objH != sb2) {
                sb2.append(objH);
            } else {
                sb2.append("(this Map)");
            }
            sb2.append('=');
            Object objK = k(i11);
            if (objK != sb2) {
                sb2.append(objK);
            } else {
                sb2.append("(this Map)");
            }
        }
        sb2.append('}');
        return sb2.toString();
    }

    public final boolean remove(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0 || !kotlin.jvm.internal.p.a(obj2, k(iE))) {
            return false;
        }
        i(iE);
        return true;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iE = e(obj);
        if (iE < 0 || !kotlin.jvm.internal.p.a(obj2, k(iE))) {
            return false;
        }
        j(iE, obj3);
        return true;
    }
}
