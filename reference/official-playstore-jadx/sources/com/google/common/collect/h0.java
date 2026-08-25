package com.google.common.collect;

import j$.util.Objects;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class h0 extends AbstractSet implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public transient Object f14015i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient int[] f14016l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient Object[] f14017m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient int f14018n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public transient int f14019o;

    public static h0 a(int i10) {
        h0 h0Var = new h0();
        ac.b.k(i10 >= 0, "Expected size must be >= 0");
        h0Var.f14018n = m2.f0.h(i10, 1);
        return h0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int iMin;
        char c10 = 31;
        if (c()) {
            ac.b.t(c(), "Arrays already allocated");
            int i10 = this.f14018n;
            int iMax = Math.max(i10 + 1, 2);
            int iHighestOneBit = Integer.highestOneBit(iMax);
            if (iMax > ((int) (1.0d * ((double) iHighestOneBit))) && (iHighestOneBit = iHighestOneBit << 1) <= 0) {
                iHighestOneBit = 1073741824;
            }
            int iMax2 = Math.max(4, iHighestOneBit);
            this.f14015i = a0.f(iMax2);
            this.f14018n = a0.n(this.f14018n, 32 - Integer.numberOfLeadingZeros(iMax2 - 1), 31);
            this.f14016l = new int[i10];
            this.f14017m = new Object[i10];
        }
        Set setB = b();
        if (setB != null) {
            return setB.add(obj);
        }
        int[] iArrE = e();
        Object[] objArrD = d();
        int i11 = this.f14019o;
        int i12 = i11 + 1;
        int iU = a0.u(obj);
        int iF = (1 << (this.f14018n & 31)) - 1;
        int i13 = iU & iF;
        Object obj2 = this.f14015i;
        Objects.requireNonNull(obj2);
        int iV = a0.v(i13, obj2);
        if (iV != 0) {
            int i14 = ~iF;
            int i15 = iU & i14;
            int i16 = 0;
            while (true) {
                int i17 = iV - 1;
                int i18 = iArrE[i17];
                char c11 = c10;
                if ((i18 & i14) == i15 && a.a.L(obj, objArrD[i17])) {
                    return false;
                }
                int i19 = i18 & iF;
                i16++;
                if (i19 != 0) {
                    iV = i19;
                    c10 = c11;
                } else {
                    if (i16 >= 9) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet(1 << (this.f14018n & 31), 1.0f);
                        int i20 = isEmpty() ? -1 : 0;
                        while (i20 >= 0) {
                            linkedHashSet.add(d()[i20]);
                            i20++;
                            if (i20 >= this.f14019o) {
                                i20 = -1;
                            }
                        }
                        this.f14015i = linkedHashSet;
                        this.f14016l = null;
                        this.f14017m = null;
                        this.f14018n += 32;
                        return linkedHashSet.add(obj);
                    }
                    if (i12 > iF) {
                        iF = f(iF, a0.p(iF), iU, i11);
                    } else {
                        iArrE[i17] = a0.n(i18, i12, iF);
                    }
                }
            }
        } else if (i12 > iF) {
            iF = f(iF, a0.p(iF), iU, i11);
        } else {
            Object obj3 = this.f14015i;
            Objects.requireNonNull(obj3);
            a0.w(i13, i12, obj3);
        }
        int length = e().length;
        if (i12 > length && (iMin = Math.min(1073741823, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.f14016l = Arrays.copyOf(e(), iMin);
            this.f14017m = Arrays.copyOf(d(), iMin);
        }
        e()[i11] = a0.n(iU, 0, iF);
        d()[i11] = obj;
        this.f14019o = i12;
        this.f14018n += 32;
        return true;
    }

    public final Set b() {
        Object obj = this.f14015i;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    public final boolean c() {
        return this.f14015i == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        if (c()) {
            return;
        }
        this.f14018n += 32;
        Set setB = b();
        if (setB != null) {
            this.f14018n = m2.f0.h(size(), 3);
            setB.clear();
            this.f14015i = null;
            this.f14019o = 0;
            return;
        }
        Arrays.fill(d(), 0, this.f14019o, (Object) null);
        Object obj = this.f14015i;
        Objects.requireNonNull(obj);
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(e(), 0, this.f14019o, 0);
        this.f14019o = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (c()) {
            return false;
        }
        Set setB = b();
        if (setB != null) {
            return setB.contains(obj);
        }
        int iU = a0.u(obj);
        int i10 = (1 << (this.f14018n & 31)) - 1;
        Object obj2 = this.f14015i;
        Objects.requireNonNull(obj2);
        int iV = a0.v(iU & i10, obj2);
        if (iV == 0) {
            return false;
        }
        int i11 = ~i10;
        int i12 = iU & i11;
        do {
            int i13 = iV - 1;
            int i14 = e()[i13];
            if ((i14 & i11) == i12 && a.a.L(obj, d()[i13])) {
                return true;
            }
            iV = i14 & i10;
        } while (iV != 0);
        return false;
    }

    public final Object[] d() {
        Object[] objArr = this.f14017m;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public final int[] e() {
        int[] iArr = this.f14016l;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    public final int f(int i10, int i11, int i12, int i13) {
        Object objF = a0.f(i11);
        int i14 = i11 - 1;
        if (i13 != 0) {
            a0.w(i12 & i14, i13 + 1, objF);
        }
        Object obj = this.f14015i;
        Objects.requireNonNull(obj);
        int[] iArrE = e();
        for (int i15 = 0; i15 <= i10; i15++) {
            int iV = a0.v(i15, obj);
            while (iV != 0) {
                int i16 = iV - 1;
                int i17 = iArrE[i16];
                int i18 = ((~i10) & i17) | i15;
                int i19 = i18 & i14;
                int iV2 = a0.v(i19, objF);
                a0.w(i19, iV, objF);
                iArrE[i16] = a0.n(i18, iV2, i14);
                iV = i17 & i10;
            }
        }
        this.f14015i = objF;
        this.f14018n = a0.n(this.f14018n, 32 - Integer.numberOfLeadingZeros(i14), 31);
        return i14;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        Set setB = b();
        return setB != null ? setB.iterator() : new g0(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int i10;
        int i11;
        if (!c()) {
            Set setB = b();
            if (setB != null) {
                return setB.remove(obj);
            }
            int i12 = (1 << (this.f14018n & 31)) - 1;
            Object obj2 = this.f14015i;
            Objects.requireNonNull(obj2);
            int iQ = a0.q(obj, null, i12, obj2, e(), d(), null);
            if (iQ != -1) {
                Object obj3 = this.f14015i;
                Objects.requireNonNull(obj3);
                int[] iArrE = e();
                Object[] objArrD = d();
                int size = size();
                int i13 = size - 1;
                if (iQ < i13) {
                    Object obj4 = objArrD[i13];
                    objArrD[iQ] = obj4;
                    objArrD[i13] = null;
                    iArrE[iQ] = iArrE[i13];
                    iArrE[i13] = 0;
                    int iU = a0.u(obj4) & i12;
                    int iV = a0.v(iU, obj3);
                    if (iV == size) {
                        a0.w(iU, iQ + 1, obj3);
                    } else {
                        while (true) {
                            i10 = iV - 1;
                            i11 = iArrE[i10];
                            int i14 = i11 & i12;
                            if (i14 == size) {
                                break;
                            }
                            iV = i14;
                        }
                        iArrE[i10] = a0.n(i11, iQ + 1, i12);
                    }
                } else {
                    objArrD[iQ] = null;
                    iArrE[iQ] = 0;
                }
                this.f14019o--;
                this.f14018n += 32;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        Set setB = b();
        return setB != null ? setB.size() : this.f14019o;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray() {
        if (c()) {
            return new Object[0];
        }
        Set setB = b();
        return setB != null ? setB.toArray() : Arrays.copyOf(d(), this.f14019o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        if (c()) {
            if (objArr.length > 0) {
                objArr[0] = null;
            }
            return objArr;
        }
        Set setB = b();
        if (setB != null) {
            return setB.toArray(objArr);
        }
        Object[] objArrD = d();
        int i10 = this.f14019o;
        ac.b.r(0, i10, objArrD.length);
        if (objArr.length < i10) {
            if (objArr.length != 0) {
                objArr = Arrays.copyOf(objArr, 0);
            }
            objArr = Arrays.copyOf(objArr, i10);
        } else if (objArr.length > i10) {
            objArr[i10] = null;
        }
        System.arraycopy(objArrD, 0, objArr, 0, i10);
        return objArr;
    }
}
