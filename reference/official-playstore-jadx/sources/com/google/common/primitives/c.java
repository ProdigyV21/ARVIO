package com.google.common.primitives;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import m2.f0;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends AbstractList implements RandomAccess, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f14180i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f14181l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14182m;

    public c(int i10, int i11, int[] iArr) {
        this.f14180i = iArr;
        this.f14181l = i10;
        this.f14182m = i11;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof Integer) {
            return f0.z(this.f14180i, ((Integer) obj).intValue(), this.f14181l, this.f14182m) != -1;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return super.equals(obj);
        }
        c cVar = (c) obj;
        int size = size();
        if (cVar.size() != size) {
            return false;
        }
        for (int i10 = 0; i10 < size; i10++) {
            if (this.f14180i[this.f14181l + i10] != cVar.f14180i[cVar.f14181l + i10]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        ac.b.n(i10, size());
        return Integer.valueOf(this.f14180i[this.f14181l + i10]);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i10 = 1;
        for (int i11 = this.f14181l; i11 < this.f14182m; i11++) {
            i10 = (i10 * 31) + this.f14180i[i11];
        }
        return i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i10 = this.f14182m;
        int[] iArr = this.f14180i;
        int i11 = this.f14181l;
        int iZ = f0.z(iArr, iIntValue, i11, i10);
        if (iZ >= 0) {
            return iZ - i11;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int i10;
        if (obj instanceof Integer) {
            int iIntValue = ((Integer) obj).intValue();
            int i11 = this.f14182m;
            while (true) {
                i11--;
                i10 = this.f14181l;
                if (i11 < i10) {
                    i11 = -1;
                    break;
                }
                if (this.f14180i[i11] == iIntValue) {
                    break;
                }
            }
            if (i11 >= 0) {
                return i11 - i10;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        Integer num = (Integer) obj;
        ac.b.n(i10, size());
        int i11 = this.f14181l + i10;
        int[] iArr = this.f14180i;
        int i12 = iArr[i11];
        num.getClass();
        iArr[i11] = num.intValue();
        return Integer.valueOf(i12);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14182m - this.f14181l;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i10, int i11) {
        ac.b.r(i10, i11, size());
        if (i10 == i11) {
            return Collections.EMPTY_LIST;
        }
        int i12 = this.f14181l;
        return new c(i10 + i12, i12 + i11, this.f14180i);
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb2 = new StringBuilder(size() * 5);
        sb2.append('[');
        int[] iArr = this.f14180i;
        int i10 = this.f14181l;
        sb2.append(iArr[i10]);
        while (true) {
            i10++;
            if (i10 >= this.f14182m) {
                sb2.append(']');
                return sb2.toString();
            }
            sb2.append(", ");
            sb2.append(iArr[i10]);
        }
    }
}
