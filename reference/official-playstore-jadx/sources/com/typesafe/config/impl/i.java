package com.typesafe.config.impl;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class i extends g implements Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f14550l;

    public i(l6.k kVar, boolean z) {
        super(kVar);
        this.f14550l = z;
    }

    @Override // com.typesafe.config.impl.g
    public final g D(w1 w1Var) {
        return new i(w1Var, this.f14550l);
    }

    @Override // com.typesafe.config.impl.g
    public final String M() {
        return this.f14550l ? "true" : "false";
    }

    @Override // l6.o
    public final Object a() {
        return Boolean.valueOf(this.f14550l);
    }

    @Override // l6.o
    public final int b() {
        return 4;
    }
}
