package com.google.android.gms.internal.cast;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public final class cb extends ca implements RandomAccess, wa {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final long[] f13222n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final cb f13223o;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long[] f13224l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13225m;

    static {
        long[] jArr = new long[0];
        f13222n = jArr;
        f13223o = new cb(jArr, 0, false);
    }

    public cb(long[] jArr, int i10, boolean z) {
        super(z);
        this.f13224l = jArr;
        this.f13225m = i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        int i11;
        long jLongValue = ((Long) obj).longValue();
        a();
        if (i10 < 0 || i10 > (i11 = this.f13225m)) {
            throw new IndexOutOfBoundsException(u0.j(this.f13225m, i10, (byte) 13, "Index:", ", Size:"));
        }
        int i12 = i10 + 1;
        long[] jArr = this.f13224l;
        int length = jArr.length;
        if (i11 < length) {
            System.arraycopy(jArr, i10, jArr, i12, i11 - i10);
        } else {
            long[] jArr2 = new long[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.f13224l, 0, jArr2, 0, i10);
            System.arraycopy(this.f13224l, i10, jArr2, i12, this.f13225m - i10);
            this.f13224l = jArr2;
        }
        this.f13224l[i10] = jLongValue;
        this.f13225m++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = ya.f13698a;
        collection.getClass();
        if (!(collection instanceof cb)) {
            return super.addAll(collection);
        }
        cb cbVar = (cb) collection;
        int i10 = cbVar.f13225m;
        if (i10 == 0) {
            return false;
        }
        int i11 = this.f13225m;
        if (Integer.MAX_VALUE - i11 < i10) {
            throw new OutOfMemoryError();
        }
        int i12 = i11 + i10;
        long[] jArr = this.f13224l;
        if (i12 > jArr.length) {
            this.f13224l = Arrays.copyOf(jArr, i12);
        }
        System.arraycopy(cbVar.f13224l, 0, this.f13224l, this.f13225m, cbVar.f13225m);
        this.f13225m = i12;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final long b(int i10) {
        c(i10);
        return this.f13224l[i10];
    }

    public final void c(int i10) {
        if (i10 < 0 || i10 >= this.f13225m) {
            throw new IndexOutOfBoundsException(u0.j(this.f13225m, i10, (byte) 13, "Index:", ", Size:"));
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cb)) {
            return super.equals(obj);
        }
        cb cbVar = (cb) obj;
        if (this.f13225m != cbVar.f13225m) {
            return false;
        }
        long[] jArr = cbVar.f13224l;
        for (int i10 = 0; i10 < this.f13225m; i10++) {
            if (this.f13224l[i10] != jArr[i10]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i10) {
        c(i10);
        return Long.valueOf(this.f13224l[i10]);
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i10 = 1;
        for (int i11 = 0; i11 < this.f13225m; i11++) {
            long j10 = this.f13224l[i11];
            Charset charset = ya.f13698a;
            i10 = (i10 * 31) + ((int) (j10 ^ (j10 >>> 32)));
        }
        return i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long jLongValue = ((Long) obj).longValue();
        int i10 = this.f13225m;
        for (int i11 = 0; i11 < i10; i11++) {
            if (this.f13224l[i11] == jLongValue) {
                return i11;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i10) {
        a();
        c(i10);
        long[] jArr = this.f13224l;
        long j10 = jArr[i10];
        if (i10 < this.f13225m - 1) {
            System.arraycopy(jArr, i10 + 1, jArr, i10, (r3 - i10) - 1);
        }
        this.f13225m--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j10);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i10, int i11) {
        a();
        if (i11 < i10) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.f13224l;
        System.arraycopy(jArr, i11, jArr, i10, this.f13225m - i11);
        this.f13225m -= i11 - i10;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i10, Object obj) {
        long jLongValue = ((Long) obj).longValue();
        a();
        c(i10);
        long[] jArr = this.f13224l;
        long j10 = jArr[i10];
        jArr[i10] = jLongValue;
        return Long.valueOf(j10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13225m;
    }

    @Override // com.google.android.gms.internal.cast.xa
    public final xa zzf(int i10) {
        if (i10 >= this.f13225m) {
            return new cb(i10 == 0 ? f13222n : Arrays.copyOf(this.f13224l, i10), this.f13225m, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        long jLongValue = ((Long) obj).longValue();
        a();
        int i10 = this.f13225m;
        int length = this.f13224l.length;
        if (i10 == length) {
            long[] jArr = new long[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.f13224l, 0, jArr, 0, this.f13225m);
            this.f13224l = jArr;
        }
        long[] jArr2 = this.f13224l;
        int i11 = this.f13225m;
        this.f13225m = i11 + 1;
        jArr2[i11] = jLongValue;
        return true;
    }
}
