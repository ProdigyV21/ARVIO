package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class w3 extends w1 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Object[] f14134s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final w3 f14135t;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object[] f14136n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient int f14137o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient Object[] f14138p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f14139q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final transient int f14140r;

    static {
        Object[] objArr = new Object[0];
        f14134s = objArr;
        f14135t = new w3(objArr, objArr, 0, 0, 0);
    }

    public w3(Object[] objArr, Object[] objArr2, int i10, int i11, int i12) {
        this.f14136n = objArr;
        this.f14137o = i10;
        this.f14138p = objArr2;
        this.f14139q = i11;
        this.f14140r = i12;
    }

    @Override // com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        Object[] objArr2 = this.f14136n;
        int i11 = this.f14140r;
        System.arraycopy(objArr2, 0, objArr, i10, i11);
        return i10 + i11;
    }

    @Override // com.google.common.collect.c1
    public final Object[] c() {
        return this.f14136n;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj != null) {
            Object[] objArr = this.f14138p;
            if (objArr.length != 0) {
                int iU = a0.u(obj);
                while (true) {
                    int i10 = iU & this.f14139q;
                    Object obj2 = objArr[i10];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    iU = i10 + 1;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.c1
    public final int d() {
        return this.f14140r;
    }

    @Override // com.google.common.collect.c1
    public final int e() {
        return 0;
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return false;
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        return a().listIterator(0);
    }

    @Override // com.google.common.collect.w1, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f14137o;
    }

    @Override // com.google.common.collect.w1
    public final h1 n() {
        return h1.h(this.f14140r, this.f14136n);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f14140r;
    }
}
