package com.google.android.gms.internal.cast;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public final class pb extends ca implements RandomAccess {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Object[] f13546n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final pb f13547o;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f13548l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13549m;

    static {
        Object[] objArr = new Object[0];
        f13546n = objArr;
        f13547o = new pb(objArr, 0, false);
    }

    public pb(Object[] objArr, int i10, boolean z) {
        super(z);
        this.f13548l = objArr;
        this.f13549m = i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        int i11;
        a();
        if (i10 < 0 || i10 > (i11 = this.f13549m)) {
            throw new IndexOutOfBoundsException(u0.j(this.f13549m, i10, (byte) 13, "Index:", ", Size:"));
        }
        int i12 = i10 + 1;
        Object[] objArr = this.f13548l;
        int length = objArr.length;
        if (i11 < length) {
            System.arraycopy(objArr, i10, objArr, i12, i11 - i10);
        } else {
            Object[] objArr2 = new Object[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.f13548l, 0, objArr2, 0, i10);
            System.arraycopy(this.f13548l, i10, objArr2, i12, this.f13549m - i10);
            this.f13548l = objArr2;
        }
        this.f13548l[i10] = obj;
        this.f13549m++;
        ((AbstractList) this).modCount++;
    }

    public final void b(int i10) {
        if (i10 < 0 || i10 >= this.f13549m) {
            throw new IndexOutOfBoundsException(u0.j(this.f13549m, i10, (byte) 13, "Index:", ", Size:"));
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        b(i10);
        return this.f13548l[i10];
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.List
    public final Object remove(int i10) {
        a();
        b(i10);
        Object[] objArr = this.f13548l;
        Object obj = objArr[i10];
        if (i10 < this.f13549m - 1) {
            System.arraycopy(objArr, i10 + 1, objArr, i10, (r2 - i10) - 1);
        }
        this.f13549m--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        a();
        b(i10);
        Object[] objArr = this.f13548l;
        Object obj2 = objArr[i10];
        objArr[i10] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13549m;
    }

    @Override // com.google.android.gms.internal.cast.xa
    public final /* bridge */ /* synthetic */ xa zzf(int i10) {
        if (i10 >= this.f13549m) {
            return new pb(i10 == 0 ? f13546n : Arrays.copyOf(this.f13548l, i10), this.f13549m, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        a();
        int i10 = this.f13549m;
        int length = this.f13548l.length;
        if (i10 == length) {
            this.f13548l = Arrays.copyOf(this.f13548l, Math.max(((length * 3) / 2) + 1, 10));
        }
        Object[] objArr = this.f13548l;
        int i11 = this.f13549m;
        this.f13549m = i11 + 1;
        objArr[i11] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }
}
