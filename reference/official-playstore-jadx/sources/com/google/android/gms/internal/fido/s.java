package com.google.android.gms.internal.fido;

import j$.util.Objects;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class s extends l implements Set, j$.util.Set {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ int f13814m = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient q f13815l;

    public static s f(int i10, Object... objArr) {
        if (i10 == 0) {
            return c0.f13761t;
        }
        if (i10 == 1) {
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            return new f0(obj);
        }
        int iG = g(i10);
        Object[] objArr2 = new Object[iG];
        int i11 = iG - 1;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < i10; i14++) {
            Object obj2 = objArr[i14];
            if (obj2 == null) {
                throw new NullPointerException(a0.c.i(i14, "at index "));
            }
            int iHashCode = obj2.hashCode();
            int iRotateLeft = (int) (((long) Integer.rotateLeft((int) (((long) iHashCode) * (-862048943)), 15)) * 461845907);
            while (true) {
                int i15 = iRotateLeft & i11;
                Object obj3 = objArr2[i15];
                if (obj3 == null) {
                    objArr[i13] = obj2;
                    objArr2[i15] = obj2;
                    i12 += iHashCode;
                    i13++;
                    break;
                }
                if (!obj3.equals(obj2)) {
                    iRotateLeft++;
                }
            }
        }
        Arrays.fill(objArr, i13, i10, (Object) null);
        if (i13 == 1) {
            Object obj4 = objArr[0];
            Objects.requireNonNull(obj4);
            return new f0(obj4);
        }
        if (g(i13) < iG / 2) {
            return f(i13, objArr);
        }
        if (i13 <= 0) {
            objArr = Arrays.copyOf(objArr, i13);
        }
        return new c0(objArr, objArr2, i12, i11, i13);
    }

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

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof s) && (this instanceof c0) && (((s) obj) instanceof c0) && ((c0) this).f13763o != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        try {
            if (size() == set.size()) {
                return containsAll(set);
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public q h() {
        q qVar = this.f13815l;
        if (qVar != null) {
            return qVar;
        }
        q qVarK = k();
        this.f13815l = qVarK;
        return qVarK;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return b.b(this);
    }

    public q k() {
        Object[] array = toArray(l.f13792i);
        m mVar = q.f13808l;
        return q.h(array.length, array);
    }
}
