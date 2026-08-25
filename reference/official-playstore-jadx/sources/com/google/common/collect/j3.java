package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class j3 extends k3 implements com.google.common.base.r {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final j3 f14034m = new j3(n0.f14064n, n0.f14063m);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o0 f14035i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o0 f14036l;

    public j3(o0 o0Var, o0 o0Var2) {
        this.f14035i = o0Var;
        this.f14036l = o0Var2;
        if (o0Var.compareTo(o0Var2) > 0 || o0Var == n0.f14063m || o0Var2 == n0.f14064n) {
            StringBuilder sb2 = new StringBuilder("Invalid range: ");
            StringBuilder sb3 = new StringBuilder(16);
            o0Var.d(sb3);
            sb3.append("..");
            o0Var2.f(sb3);
            sb2.append(sb3.toString());
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        Comparable comparable = (Comparable) obj;
        comparable.getClass();
        return this.f14035i.j(comparable) && !this.f14036l.j(comparable);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j3) {
            j3 j3Var = (j3) obj;
            if (this.f14035i.equals(j3Var.f14035i) && this.f14036l.equals(j3Var.f14036l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f14036l.hashCode() + (this.f14035i.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(16);
        this.f14035i.d(sb2);
        sb2.append("..");
        this.f14036l.f(sb2);
        return sb2.toString();
    }
}
