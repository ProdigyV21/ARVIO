package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class w0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f14632a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g1 f14633b;

    public w0(g gVar, g1 g1Var) {
        this.f14632a = gVar;
        this.f14633b = g1Var;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof w0) {
            w0 w0Var = (w0) obj;
            if (w0Var.f14632a != this.f14632a) {
                return false;
            }
            g1 g1Var = w0Var.f14633b;
            g1 g1Var2 = this.f14633b;
            if (g1Var == g1Var2) {
                return true;
            }
            if (g1Var != null && g1Var2 != null) {
                return g1Var.equals(g1Var2);
            }
        }
        return false;
    }

    public final int hashCode() {
        int iIdentityHashCode = System.identityHashCode(this.f14632a);
        g1 g1Var = this.f14633b;
        return g1Var != null ? ((g1Var.hashCode() + 41) * 41) + iIdentityHashCode : iIdentityHashCode;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("MemoKey(");
        g gVar = this.f14632a;
        sb2.append(gVar);
        sb2.append("@");
        sb2.append(System.identityHashCode(gVar));
        sb2.append(",");
        sb2.append(this.f14633b);
        sb2.append(")");
        return sb2.toString();
    }
}
