package com.google.common.collect;

import j$.util.Objects;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes4.dex */
public abstract class w1 extends c1 implements Set, j$.util.Set {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ int f14132m = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient h1 f14133l;

    public static int h(int i10) {
        int iMax = Math.max(i10, 2);
        if (iMax >= 751619276) {
            ac.b.k(iMax < 1073741824, "collection too large");
            return 1073741824;
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
        while (((double) iHighestOneBit) * 0.7d < iMax) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    public static w1 k(int i10, Object... objArr) {
        if (i10 == 0) {
            return w3.f14135t;
        }
        if (i10 == 1) {
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            return new e4(obj);
        }
        int iH = h(i10);
        Object[] objArr2 = new Object[iH];
        int i11 = iH - 1;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < i10; i14++) {
            Object obj2 = objArr[i14];
            if (obj2 == null) {
                throw new NullPointerException(a0.c.i(i14, "at index "));
            }
            int iHashCode = obj2.hashCode();
            int iT = a0.t(iHashCode);
            while (true) {
                int i15 = iT & i11;
                Object obj3 = objArr2[i15];
                if (obj3 == null) {
                    objArr[i13] = obj2;
                    objArr2[i15] = obj2;
                    i12 += iHashCode;
                    i13++;
                    break;
                }
                if (obj3.equals(obj2)) {
                    break;
                }
                iT++;
            }
        }
        Arrays.fill(objArr, i13, i10, (Object) null);
        if (i13 == 1) {
            Object obj4 = objArr[0];
            Objects.requireNonNull(obj4);
            return new e4(obj4);
        }
        if (h(i13) < iH / 2) {
            return k(i13, objArr);
        }
        int length = objArr.length;
        if (i13 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i13);
        }
        return new w3(objArr, objArr2, i12, i11, i13);
    }

    public static w1 m(Collection collection) {
        if ((collection instanceof w1) && !(collection instanceof SortedSet)) {
            w1 w1Var = (w1) collection;
            if (!w1Var.f()) {
                return w1Var;
            }
        }
        Object[] array = collection.toArray();
        return k(array.length, array);
    }

    @Override // com.google.common.collect.c1
    public h1 a() {
        h1 h1Var = this.f14133l;
        if (h1Var != null) {
            return h1Var;
        }
        h1 h1VarN = n();
        this.f14133l = h1VarN;
        return h1VarN;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof w1) && (this instanceof w3) && (((w1) obj) instanceof w3) && hashCode() != obj.hashCode()) {
            return false;
        }
        return a0.g(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return a0.l(this);
    }

    public h1 n() {
        Object[] array = toArray(c1.f13956i);
        f1 f1Var = h1.f14020l;
        return h1.h(array.length, array);
    }
}
