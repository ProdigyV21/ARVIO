package com.google.android.gms.internal.fido;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends s {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Object[] f13760s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final c0 f13761t;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object[] f13762n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient int f13763o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient Object[] f13764p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f13765q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final transient int f13766r;

    static {
        Object[] objArr = new Object[0];
        f13760s = objArr;
        f13761t = new c0(objArr, objArr, 0, 0, 0);
    }

    public c0(Object[] objArr, Object[] objArr2, int i10, int i11, int i12) {
        this.f13762n = objArr;
        this.f13763o = i10;
        this.f13764p = objArr2;
        this.f13765q = i11;
        this.f13766r = i12;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int a(Object[] objArr) {
        Object[] objArr2 = this.f13762n;
        int i10 = this.f13766r;
        System.arraycopy(objArr2, 0, objArr, 0, i10);
        return i10;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int b() {
        return this.f13766r;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int c() {
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        Object[] objArr = this.f13764p;
        if (objArr.length == 0) {
            return false;
        }
        int iRotateLeft = (int) (((long) Integer.rotateLeft((int) (((long) obj.hashCode()) * (-862048943)), 15)) * 461845907);
        while (true) {
            int i10 = iRotateLeft & this.f13765q;
            Object obj2 = objArr[i10];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            iRotateLeft = i10 + 1;
        }
    }

    @Override // com.google.android.gms.internal.fido.l
    public final h0 d() {
        return h().listIterator(0);
    }

    @Override // com.google.android.gms.internal.fido.l
    public final Object[] e() {
        return this.f13762n;
    }

    @Override // com.google.android.gms.internal.fido.s, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f13763o;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return h().listIterator(0);
    }

    @Override // com.google.android.gms.internal.fido.s
    public final q k() {
        return q.h(this.f13766r, this.f13762n);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13766r;
    }
}
