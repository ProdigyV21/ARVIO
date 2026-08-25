package androidx.datastore.preferences.protobuf;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes3.dex */
public final class k2 extends d implements RandomAccess {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k2 f2458n;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f2459l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2460m;

    static {
        k2 k2Var = new k2(new Object[0], 0);
        f2458n = k2Var;
        k2Var.f2415i = false;
    }

    public k2(Object[] objArr, int i10) {
        this.f2459l = objArr;
        this.f2460m = i10;
    }

    @Override // androidx.datastore.preferences.protobuf.e1
    public final e1 V(int i10) {
        if (i10 >= this.f2460m) {
            return new k2(Arrays.copyOf(this.f2459l, i10), this.f2460m);
        }
        throw new IllegalArgumentException();
    }

    @Override // androidx.datastore.preferences.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        a();
        int i10 = this.f2460m;
        Object[] objArr = this.f2459l;
        if (i10 == objArr.length) {
            this.f2459l = Arrays.copyOf(objArr, ((i10 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.f2459l;
        int i11 = this.f2460m;
        this.f2460m = i11 + 1;
        objArr2[i11] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(int i10) {
        if (i10 < 0 || i10 >= this.f2460m) {
            StringBuilder sbS = a0.c.s(i10, "Index:", ", Size:");
            sbS.append(this.f2460m);
            throw new IndexOutOfBoundsException(sbS.toString());
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        b(i10);
        return this.f2459l[i10];
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i10) {
        a();
        b(i10);
        Object[] objArr = this.f2459l;
        Object obj = objArr[i10];
        if (i10 < this.f2460m - 1) {
            System.arraycopy(objArr, i10 + 1, objArr, i10, (r2 - i10) - 1);
        }
        this.f2460m--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        a();
        b(i10);
        Object[] objArr = this.f2459l;
        Object obj2 = objArr[i10];
        objArr[i10] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f2460m;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        int i11;
        a();
        if (i10 >= 0 && i10 <= (i11 = this.f2460m)) {
            Object[] objArr = this.f2459l;
            if (i11 < objArr.length) {
                System.arraycopy(objArr, i10, objArr, i10 + 1, i11 - i10);
            } else {
                Object[] objArr2 = new Object[((i11 * 3) / 2) + 1];
                System.arraycopy(objArr, 0, objArr2, 0, i10);
                System.arraycopy(this.f2459l, i10, objArr2, i10 + 1, this.f2460m - i10);
                this.f2459l = objArr2;
            }
            this.f2459l[i10] = obj;
            this.f2460m++;
            ((AbstractList) this).modCount++;
            return;
        }
        StringBuilder sbS = a0.c.s(i10, "Index:", ", Size:");
        sbS.append(this.f2460m);
        throw new IndexOutOfBoundsException(sbS.toString());
    }
}
