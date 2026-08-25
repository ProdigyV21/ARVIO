package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class a0 extends n0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f14504m;

    public a0(l6.k kVar, long j10, String str) {
        super(kVar, str);
        this.f14504m = j10;
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new a0(w1Var, this.f14504m, this.f14592l);
    }

    @Override // com.typesafe.config.impl.g
    public final String M() {
        String str = this.f14592l;
        return str == null ? Long.toString(this.f14504m) : str;
    }

    @Override // com.typesafe.config.impl.n0
    public final double S() {
        return this.f14504m;
    }

    @Override // com.typesafe.config.impl.n0
    public final long W() {
        return this.f14504m;
    }

    @Override // l6.o
    public final Object a() {
        return Long.valueOf(this.f14504m);
    }

    @Override // l6.o
    public final int b() {
        return 3;
    }
}
