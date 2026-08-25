package com.google.android.gms.internal.cast;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public final class qa extends ca implements RandomAccess, va {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f13562n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final qa f13563o;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f13564l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13565m;

    static {
        int[] iArr = new int[0];
        f13562n = iArr;
        f13563o = new qa(0, iArr, false);
    }

    public qa(int i10, int[] iArr, boolean z) {
        super(z);
        this.f13564l = iArr;
        this.f13565m = i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        int i11;
        int iIntValue = ((Integer) obj).intValue();
        a();
        if (i10 < 0 || i10 > (i11 = this.f13565m)) {
            throw new IndexOutOfBoundsException(u0.j(this.f13565m, i10, (byte) 13, "Index:", ", Size:"));
        }
        int i12 = i10 + 1;
        int[] iArr = this.f13564l;
        int length = iArr.length;
        if (i11 < length) {
            System.arraycopy(iArr, i10, iArr, i12, i11 - i10);
        } else {
            int[] iArr2 = new int[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.f13564l, 0, iArr2, 0, i10);
            System.arraycopy(this.f13564l, i10, iArr2, i12, this.f13565m - i10);
            this.f13564l = iArr2;
        }
        this.f13564l[i10] = iIntValue;
        this.f13565m++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = ya.f13698a;
        collection.getClass();
        if (!(collection instanceof qa)) {
            return super.addAll(collection);
        }
        qa qaVar = (qa) collection;
        int i10 = qaVar.f13565m;
        if (i10 == 0) {
            return false;
        }
        int i11 = this.f13565m;
        if (Integer.MAX_VALUE - i11 < i10) {
            throw new OutOfMemoryError();
        }
        int i12 = i11 + i10;
        int[] iArr = this.f13564l;
        if (i12 > iArr.length) {
            this.f13564l = Arrays.copyOf(iArr, i12);
        }
        System.arraycopy(qaVar.f13564l, 0, this.f13564l, this.f13565m, qaVar.f13565m);
        this.f13565m = i12;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final int b(int i10) {
        c(i10);
        return this.f13564l[i10];
    }

    public final void c(int i10) {
        if (i10 < 0 || i10 >= this.f13565m) {
            throw new IndexOutOfBoundsException(u0.j(this.f13565m, i10, (byte) 13, "Index:", ", Size:"));
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
        if (!(obj instanceof qa)) {
            return super.equals(obj);
        }
        qa qaVar = (qa) obj;
        if (this.f13565m != qaVar.f13565m) {
            return false;
        }
        int[] iArr = qaVar.f13564l;
        for (int i10 = 0; i10 < this.f13565m; i10++) {
            if (this.f13564l[i10] != iArr[i10]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i10) {
        c(i10);
        return Integer.valueOf(this.f13564l[i10]);
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i10 = 1;
        for (int i11 = 0; i11 < this.f13565m; i11++) {
            i10 = (i10 * 31) + this.f13564l[i11];
        }
        return i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i10 = this.f13565m;
        for (int i11 = 0; i11 < i10; i11++) {
            if (this.f13564l[i11] == iIntValue) {
                return i11;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i10) {
        a();
        c(i10);
        int[] iArr = this.f13564l;
        int i11 = iArr[i10];
        if (i10 < this.f13565m - 1) {
            System.arraycopy(iArr, i10 + 1, iArr, i10, (r2 - i10) - 1);
        }
        this.f13565m--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i11);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i10, int i11) {
        a();
        if (i11 < i10) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.f13564l;
        System.arraycopy(iArr, i11, iArr, i10, this.f13565m - i11);
        this.f13565m -= i11 - i10;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i10, Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        a();
        c(i10);
        int[] iArr = this.f13564l;
        int i11 = iArr[i10];
        iArr[i10] = iIntValue;
        return Integer.valueOf(i11);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13565m;
    }

    @Override // com.google.android.gms.internal.cast.xa
    /* JADX INFO: renamed from: zze, reason: merged with bridge method [inline-methods] */
    public final qa zzf(int i10) {
        if (i10 >= this.f13565m) {
            return new qa(this.f13565m, i10 == 0 ? f13562n : Arrays.copyOf(this.f13564l, i10), true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.cast.va
    public final void zzh(int i10) {
        a();
        int i11 = this.f13565m;
        int length = this.f13564l.length;
        if (i11 == length) {
            int[] iArr = new int[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.f13564l, 0, iArr, 0, this.f13565m);
            this.f13564l = iArr;
        }
        int[] iArr2 = this.f13564l;
        int i12 = this.f13565m;
        this.f13565m = i12 + 1;
        iArr2[i12] = i10;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzh(((Integer) obj).intValue());
        return true;
    }
}
