package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class k2 extends c2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14566e;

    public k2(l6.k kVar, String str) {
        super(12, kVar, null, null);
        this.f14566e = str;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean a(c2 c2Var) {
        return c2Var instanceof k2;
    }

    @Override // com.typesafe.config.impl.c2
    public final String e() {
        return this.f14566e;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean equals(Object obj) {
        return super.equals(obj) && ((k2) obj).f14566e.equals(this.f14566e);
    }

    @Override // com.typesafe.config.impl.c2
    public final int hashCode() {
        return this.f14566e.hashCode() + ((h.f0.c(this.f14513a) + 41) * 41);
    }

    @Override // com.typesafe.config.impl.c2
    public final String toString() {
        return a0.c.p(new StringBuilder("'"), this.f14566e, "'");
    }
}
