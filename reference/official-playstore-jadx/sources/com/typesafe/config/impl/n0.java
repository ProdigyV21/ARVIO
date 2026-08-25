package com.typesafe.config.impl;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class n0 extends g implements Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f14592l;

    public n0(l6.k kVar, String str) {
        super(kVar);
        this.f14592l = str;
    }

    public abstract double S();

    public final boolean U() {
        return ((double) W()) == S();
    }

    public abstract long W();

    @Override // com.typesafe.config.impl.g
    public final boolean equals(Object obj) {
        if (!(obj instanceof n0)) {
            return false;
        }
        n0 n0Var = (n0) obj;
        return U() ? n0Var.U() && W() == n0Var.W() : !n0Var.U() && S() == n0Var.S();
    }

    @Override // com.typesafe.config.impl.g
    public final int hashCode() {
        long jW = U() ? W() : Double.doubleToLongBits(S());
        return (int) (jW ^ (jW >>> 32));
    }

    @Override // com.typesafe.config.impl.g
    public final boolean t(l6.o oVar) {
        return oVar instanceof n0;
    }
}
