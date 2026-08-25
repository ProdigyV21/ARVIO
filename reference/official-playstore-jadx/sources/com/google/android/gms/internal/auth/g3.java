package com.google.android.gms.internal.auth;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public final class g3 extends q1 implements RandomAccess {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final g3 f13035n = new g3(new Object[0], 0, false);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f13036l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13037m;

    public g3(Object[] objArr, int i10, boolean z) {
        super(z);
        this.f13036l = objArr;
        this.f13037m = i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        int i11;
        a();
        if (i10 < 0 || i10 > (i11 = this.f13037m)) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, this.f13037m, "Index:", ", Size:"));
        }
        Object[] objArr = this.f13036l;
        if (i11 < objArr.length) {
            System.arraycopy(objArr, i10, objArr, i10 + 1, i11 - i10);
        } else {
            Object[] objArr2 = new Object[((i11 * 3) / 2) + 1];
            System.arraycopy(objArr, 0, objArr2, 0, i10);
            System.arraycopy(this.f13036l, i10, objArr2, i10 + 1, this.f13037m - i10);
            this.f13036l = objArr2;
        }
        this.f13036l[i10] = obj;
        this.f13037m++;
        ((AbstractList) this).modCount++;
    }

    public final void b(int i10) {
        if (i10 < 0 || i10 >= this.f13037m) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, this.f13037m, "Index:", ", Size:"));
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        b(i10);
        return this.f13036l[i10];
    }

    @Override // com.google.android.gms.internal.auth.q1, java.util.AbstractList, java.util.List
    public final Object remove(int i10) {
        a();
        b(i10);
        Object[] objArr = this.f13036l;
        Object obj = objArr[i10];
        if (i10 < this.f13037m - 1) {
            System.arraycopy(objArr, i10 + 1, objArr, i10, (r2 - i10) - 1);
        }
        this.f13037m--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        a();
        b(i10);
        Object[] objArr = this.f13036l;
        Object obj2 = objArr[i10];
        objArr[i10] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13037m;
    }

    @Override // com.google.android.gms.internal.auth.g2
    public final /* bridge */ /* synthetic */ g2 zzd(int i10) {
        if (i10 >= this.f13037m) {
            return new g3(Arrays.copyOf(this.f13036l, i10), this.f13037m, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.auth.q1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        a();
        int i10 = this.f13037m;
        Object[] objArr = this.f13036l;
        if (i10 == objArr.length) {
            this.f13036l = Arrays.copyOf(objArr, ((i10 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.f13036l;
        int i11 = this.f13037m;
        this.f13037m = i11 + 1;
        objArr2[i11] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }
}
