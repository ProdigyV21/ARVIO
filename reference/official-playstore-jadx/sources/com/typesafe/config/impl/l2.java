package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class l2 extends c2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f14569e;

    public l2(g gVar, String str) {
        super(10, gVar.f14537i, str, null);
        this.f14569e = gVar;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean a(c2 c2Var) {
        return c2Var instanceof l2;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean equals(Object obj) {
        return super.equals(obj) && ((l2) obj).f14569e.equals(this.f14569e);
    }

    @Override // com.typesafe.config.impl.c2
    public final int hashCode() {
        return this.f14569e.hashCode() + ((h.f0.c(this.f14513a) + 41) * 41);
    }

    @Override // com.typesafe.config.impl.c2
    public final String toString() {
        g gVar = this.f14569e;
        if (gVar.J() != 2) {
            return "'<unresolved value>' (" + g8.b.q(gVar.b()) + ")";
        }
        return "'" + gVar.a() + "' (" + g8.b.q(gVar.b()) + ")";
    }
}
