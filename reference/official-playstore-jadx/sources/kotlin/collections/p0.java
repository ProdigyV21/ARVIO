package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes5.dex */
public final class p0 extends f implements RandomAccess {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object[] f19710i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f19711l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19712m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19713n;

    public p0(Object[] objArr, int i10) {
        this.f19710i = objArr;
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "ring buffer filled size should not be negative but it is ").toString());
        }
        if (i10 <= objArr.length) {
            this.f19711l = objArr.length;
            this.f19713n = i10;
        } else {
            StringBuilder sbS = a0.c.s(i10, "ring buffer filled size: ", " cannot be larger than the buffer size: ");
            sbS.append(objArr.length);
            throw new IllegalArgumentException(sbS.toString().toString());
        }
    }

    public final void a(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "n shouldn't be negative but it is ").toString());
        }
        if (i10 > size()) {
            StringBuilder sbS = a0.c.s(i10, "n shouldn't be greater than the buffer size: n = ", ", size = ");
            sbS.append(size());
            throw new IllegalArgumentException(sbS.toString().toString());
        }
        if (i10 > 0) {
            int i11 = this.f19712m;
            int i12 = this.f19711l;
            int i13 = (i11 + i10) % i12;
            Object[] objArr = this.f19710i;
            if (i11 > i13) {
                Arrays.fill(objArr, i11, i12, (Object) null);
                Arrays.fill(objArr, 0, i13, (Object) null);
            } else {
                Arrays.fill(objArr, i11, i13, (Object) null);
            }
            this.f19712m = i13;
            this.f19713n = size() - i10;
        }
    }

    @Override // kotlin.collections.f, java.util.List
    public final Object get(int i10) {
        c cVar = f.Companion;
        int size = size();
        cVar.getClass();
        c.b(i10, size);
        return this.f19710i[(this.f19712m + i10) % this.f19711l];
    }

    @Override // kotlin.collections.f, kotlin.collections.a
    /* JADX INFO: renamed from: getSize */
    public final int get_size() {
        return this.f19713n;
    }

    @Override // kotlin.collections.f, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new o0(this);
    }

    @Override // kotlin.collections.a, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        Object[] objArr2;
        if (objArr.length < size()) {
            objArr = Arrays.copyOf(objArr, size());
        }
        int size = size();
        int i10 = this.f19712m;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            objArr2 = this.f19710i;
            if (i12 >= size || i10 >= this.f19711l) {
                break;
            }
            objArr[i12] = objArr2[i10];
            i12++;
            i10++;
        }
        while (i12 < size) {
            objArr[i12] = objArr2[i11];
            i12++;
            i11++;
        }
        if (size < objArr.length) {
            objArr[size] = null;
        }
        return objArr;
    }

    @Override // kotlin.collections.a, java.util.Collection
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
