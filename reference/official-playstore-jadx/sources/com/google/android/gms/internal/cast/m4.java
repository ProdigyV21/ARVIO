package com.google.android.gms.internal.cast;

import j$.util.Objects;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class m4 extends g4 implements Set, j$.util.Set {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ int f13441m = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient j4 f13442l;

    public static int g(int i10) {
        int iMax = Math.max(i10, 2);
        if (iMax >= 751619276) {
            if (iMax < 1073741824) {
                return 1073741824;
            }
            throw new IllegalArgumentException("collection too large");
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1);
        do {
            iHighestOneBit += iHighestOneBit;
        } while (((double) iHighestOneBit) * 0.7d < iMax);
        return iHighestOneBit;
    }

    public static m4 k(int i10, Object... objArr) {
        if (i10 == 0) {
            return v4.f13639t;
        }
        if (i10 == 1) {
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            return new w4(obj);
        }
        int iG = g(i10);
        Object[] objArr2 = new Object[iG];
        int i11 = iG - 1;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < i10; i14++) {
            Object obj2 = objArr[i14];
            u0.s(i14, obj2);
            int iHashCode = obj2.hashCode();
            int iD = u0.d(iHashCode);
            while (true) {
                int i15 = iD & i11;
                Object obj3 = objArr2[i15];
                if (obj3 == null) {
                    objArr[i13] = obj2;
                    objArr2[i15] = obj2;
                    i12 += iHashCode;
                    i13++;
                    break;
                }
                if (!obj3.equals(obj2)) {
                    iD++;
                }
            }
        }
        Arrays.fill(objArr, i13, i10, (Object) null);
        if (i13 == 1) {
            Object obj4 = objArr[0];
            Objects.requireNonNull(obj4);
            return new w4(obj4);
        }
        if (g(i13) < iG / 2) {
            return k(i13, objArr);
        }
        int length = objArr.length;
        if (i13 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i13);
        }
        return new v4(objArr, objArr2, i12, i11, i13);
    }

    @Override // com.google.android.gms.internal.cast.g4
    public j4 d() {
        j4 j4Var = this.f13442l;
        if (j4Var != null) {
            return j4Var;
        }
        j4 j4VarH = h();
        this.f13442l = j4VarH;
        return j4VarH;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof m4) && (this instanceof v4) && (((m4) obj) instanceof v4) && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    return containsAll(set);
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public j4 h() {
        Object[] array = toArray(g4.f13259i);
        h4 h4Var = j4.f13340l;
        return j4.h(array.length, array);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        Iterator it = iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            Object next = it.next();
            iHashCode += next != null ? next.hashCode() : 0;
        }
        return iHashCode;
    }
}
