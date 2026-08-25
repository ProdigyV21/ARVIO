package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class g2 extends c2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14543e;

    public g2(w1 w1Var, String str) {
        super(13, w1Var, null, null);
        this.f14543e = str;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean a(c2 c2Var) {
        return c2Var instanceof g2;
    }

    @Override // com.typesafe.config.impl.c2
    public final String e() {
        return this.f14543e;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean equals(Object obj) {
        return super.equals(obj) && ((g2) obj).f14543e.equals(this.f14543e);
    }

    @Override // com.typesafe.config.impl.c2
    public final int hashCode() {
        return this.f14543e.hashCode() + ((h.f0.c(this.f14513a) + 41) * 41);
    }

    @Override // com.typesafe.config.impl.c2
    public final String toString() {
        return a0.c.p(new StringBuilder("'"), this.f14543e, "' (WHITESPACE)");
    }
}
