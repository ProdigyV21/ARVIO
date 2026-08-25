package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class n extends n0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final double f14591m;

    public n(l6.k kVar, double d4, String str) {
        super(kVar, str);
        this.f14591m = d4;
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new n(w1Var, this.f14591m, this.f14592l);
    }

    @Override // com.typesafe.config.impl.g
    public final String M() {
        String str = this.f14592l;
        return str == null ? Double.toString(this.f14591m) : str;
    }

    @Override // com.typesafe.config.impl.n0
    public final double S() {
        return this.f14591m;
    }

    @Override // com.typesafe.config.impl.n0
    public final long W() {
        return (long) this.f14591m;
    }

    @Override // l6.o
    public final Object a() {
        return Double.valueOf(this.f14591m);
    }

    @Override // l6.o
    public final int b() {
        return 3;
    }
}
