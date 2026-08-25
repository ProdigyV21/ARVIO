package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class z extends n0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14649m;

    public z(l6.k kVar, int i10, String str) {
        super(kVar, str);
        this.f14649m = i10;
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new z(w1Var, this.f14649m, this.f14592l);
    }

    @Override // com.typesafe.config.impl.g
    public final String M() {
        String str = this.f14592l;
        return str == null ? Integer.toString(this.f14649m) : str;
    }

    @Override // com.typesafe.config.impl.n0
    public final double S() {
        return this.f14649m;
    }

    @Override // com.typesafe.config.impl.n0
    public final long W() {
        return this.f14649m;
    }

    @Override // l6.o
    public final Object a() {
        return Integer.valueOf(this.f14649m);
    }

    @Override // l6.o
    public final int b() {
        return 3;
    }
}
