package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class n3 extends z0 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final n3 f14068s = new n3();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object f14069n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient Object[] f14070o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f14071p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f14072q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final transient n3 f14073r;

    public n3() {
        this.f14069n = null;
        this.f14070o = new Object[0];
        this.f14071p = 0;
        this.f14072q = 0;
        this.f14073r = this;
    }

    @Override // com.google.common.collect.l1
    public final q3 c() {
        return new q3(this, this.f14070o, this.f14071p, this.f14072q);
    }

    @Override // com.google.common.collect.l1
    public final r3 e() {
        return new r3(this, new s3(this.f14070o, this.f14071p, this.f14072q));
    }

    @Override // com.google.common.collect.l1, java.util.Map
    public final Object get(Object obj) {
        Object objL = t3.l(this.f14070o, this.f14072q, this.f14071p, this.f14069n, obj);
        if (objL == null) {
            return null;
        }
        return objL;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f14072q;
    }

    public n3(Object obj, Object[] objArr, int i10, n3 n3Var) {
        this.f14069n = obj;
        this.f14070o = objArr;
        this.f14071p = 1;
        this.f14072q = i10;
        this.f14073r = n3Var;
    }

    public n3(Object[] objArr, int i10) {
        this.f14070o = objArr;
        this.f14072q = i10;
        this.f14071p = 0;
        int iH = i10 >= 2 ? w1.h(i10) : 0;
        Object objK = t3.k(objArr, i10, iH, 0);
        if (!(objK instanceof Object[])) {
            this.f14069n = objK;
            Object objK2 = t3.k(objArr, i10, iH, 1);
            if (!(objK2 instanceof Object[])) {
                this.f14073r = new n3(objK2, objArr, i10, this);
                return;
            }
            throw ((j1) ((Object[]) objK2)[2]).a();
        }
        throw ((j1) ((Object[]) objK)[2]).a();
    }
}
