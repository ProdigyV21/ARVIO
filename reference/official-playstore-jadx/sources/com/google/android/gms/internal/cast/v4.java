package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class v4 extends m4 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Object[] f13638s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final v4 f13639t;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object[] f13640n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient int f13641o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient Object[] f13642p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f13643q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final transient int f13644r;

    static {
        Object[] objArr = new Object[0];
        f13638s = objArr;
        f13639t = new v4(objArr, objArr, 0, 0, 0);
    }

    public v4(Object[] objArr, Object[] objArr2, int i10, int i11, int i12) {
        this.f13640n = objArr;
        this.f13641o = i10;
        this.f13642p = objArr2;
        this.f13643q = i11;
        this.f13644r = i12;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final Object[] a() {
        return this.f13640n;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int b() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int c() {
        return this.f13644r;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj != null) {
            Object[] objArr = this.f13642p;
            if (objArr.length != 0) {
                int iD = u0.d(obj.hashCode());
                while (true) {
                    int i10 = iD & this.f13643q;
                    Object obj2 = objArr[i10];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    iD = i10 + 1;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int f(Object[] objArr) {
        Object[] objArr2 = this.f13640n;
        int i10 = this.f13644r;
        System.arraycopy(objArr2, 0, objArr, 0, i10);
        return i10;
    }

    @Override // com.google.android.gms.internal.cast.m4
    public final j4 h() {
        return j4.h(this.f13644r, this.f13640n);
    }

    @Override // com.google.android.gms.internal.cast.m4, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f13641o;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return d().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13644r;
    }
}
