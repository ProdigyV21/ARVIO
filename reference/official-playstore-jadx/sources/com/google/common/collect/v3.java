package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class v3 extends t1 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final v3 f14127r;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient f3 f14128o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f14129p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public transient u3 f14130q;

    static {
        f3 f3Var = new f3();
        f3Var.d(3);
        f14127r = new v3(f3Var);
    }

    public v3(f3 f3Var) {
        this.f14128o = f3Var;
        long j10 = 0;
        int i10 = 0;
        while (true) {
            int i11 = f3Var.f14000c;
            if (i10 >= i11) {
                this.f14129p = m2.f0.J(j10);
                return;
            } else {
                ac.b.n(i10, i11);
                j10 += (long) f3Var.f13999b[i10];
                i10++;
            }
        }
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f14129p;
    }

    @Override // com.google.common.collect.b3
    public final w1 t0() {
        u3 u3Var = this.f14130q;
        if (u3Var != null) {
            return u3Var;
        }
        u3 u3Var2 = new u3(this);
        this.f14130q = u3Var2;
        return u3Var2;
    }
}
