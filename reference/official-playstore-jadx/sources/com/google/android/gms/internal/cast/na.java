package com.google.android.gms.internal.cast;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public final class na extends ca implements RandomAccess, ua {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final float[] f13492n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final na f13493o;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float[] f13494l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13495m;

    static {
        float[] fArr = new float[0];
        f13492n = fArr;
        f13493o = new na(0, fArr, false);
    }

    public na(int i10, float[] fArr, boolean z) {
        super(z);
        this.f13494l = fArr;
        this.f13495m = i10;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        int i11;
        float fFloatValue = ((Float) obj).floatValue();
        a();
        if (i10 < 0 || i10 > (i11 = this.f13495m)) {
            throw new IndexOutOfBoundsException(u0.j(this.f13495m, i10, (byte) 13, "Index:", ", Size:"));
        }
        int i12 = i10 + 1;
        float[] fArr = this.f13494l;
        int length = fArr.length;
        if (i11 < length) {
            System.arraycopy(fArr, i10, fArr, i12, i11 - i10);
        } else {
            float[] fArr2 = new float[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.f13494l, 0, fArr2, 0, i10);
            System.arraycopy(this.f13494l, i10, fArr2, i12, this.f13495m - i10);
            this.f13494l = fArr2;
        }
        this.f13494l[i10] = fFloatValue;
        this.f13495m++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = ya.f13698a;
        collection.getClass();
        if (!(collection instanceof na)) {
            return super.addAll(collection);
        }
        na naVar = (na) collection;
        int i10 = naVar.f13495m;
        if (i10 == 0) {
            return false;
        }
        int i11 = this.f13495m;
        if (Integer.MAX_VALUE - i11 < i10) {
            throw new OutOfMemoryError();
        }
        int i12 = i11 + i10;
        float[] fArr = this.f13494l;
        if (i12 > fArr.length) {
            this.f13494l = Arrays.copyOf(fArr, i12);
        }
        System.arraycopy(naVar.f13494l, 0, this.f13494l, this.f13495m, naVar.f13495m);
        this.f13495m = i12;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(int i10) {
        if (i10 < 0 || i10 >= this.f13495m) {
            throw new IndexOutOfBoundsException(u0.j(this.f13495m, i10, (byte) 13, "Index:", ", Size:"));
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
        if (!(obj instanceof na)) {
            return super.equals(obj);
        }
        na naVar = (na) obj;
        if (this.f13495m != naVar.f13495m) {
            return false;
        }
        float[] fArr = naVar.f13494l;
        for (int i10 = 0; i10 < this.f13495m; i10++) {
            if (Float.floatToIntBits(this.f13494l[i10]) != Float.floatToIntBits(fArr[i10])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i10) {
        b(i10);
        return Float.valueOf(this.f13494l[i10]);
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i10 = 0; i10 < this.f13495m; i10++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.f13494l[i10]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float fFloatValue = ((Float) obj).floatValue();
        int i10 = this.f13495m;
        for (int i11 = 0; i11 < i10; i11++) {
            if (this.f13494l[i11] == fFloatValue) {
                return i11;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.cast.ca, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i10) {
        a();
        b(i10);
        float[] fArr = this.f13494l;
        float f10 = fArr[i10];
        if (i10 < this.f13495m - 1) {
            System.arraycopy(fArr, i10 + 1, fArr, i10, (r2 - i10) - 1);
        }
        this.f13495m--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f10);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i10, int i11) {
        a();
        if (i11 < i10) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.f13494l;
        System.arraycopy(fArr, i11, fArr, i10, this.f13495m - i11);
        this.f13495m -= i11 - i10;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i10, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        a();
        b(i10);
        float[] fArr = this.f13494l;
        float f10 = fArr[i10];
        fArr[i10] = fFloatValue;
        return Float.valueOf(f10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13495m;
    }

    @Override // com.google.android.gms.internal.cast.xa
    public final xa zzf(int i10) {
        if (i10 >= this.f13495m) {
            return new na(this.f13495m, i10 == 0 ? f13492n : Arrays.copyOf(this.f13494l, i10), true);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        a();
        int i10 = this.f13495m;
        int length = this.f13494l.length;
        if (i10 == length) {
            float[] fArr = new float[Math.max(((length * 3) / 2) + 1, 10)];
            System.arraycopy(this.f13494l, 0, fArr, 0, this.f13495m);
            this.f13494l = fArr;
        }
        float[] fArr2 = this.f13494l;
        int i11 = this.f13495m;
        this.f13495m = i11 + 1;
        fArr2[i11] = fFloatValue;
        return true;
    }
}
