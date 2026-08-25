package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class f2 extends c2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14535e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f14536f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f2(w1 w1Var, String str, int i10) {
        super(16, w1Var, null, null);
        this.f14536f = i10;
        this.f14535e = str;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean a(c2 c2Var) {
        return c2Var instanceof f2;
    }

    @Override // com.typesafe.config.impl.c2
    public final String e() {
        switch (this.f14536f) {
            case 0:
                return "//" + this.f14535e;
            default:
                return "#" + this.f14535e;
        }
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean equals(Object obj) {
        return super.equals(obj) && ((f2) obj).f14535e.equals(this.f14535e);
    }

    @Override // com.typesafe.config.impl.c2
    public final int hashCode() {
        return androidx.compose.foundation.c.c((h.f0.c(this.f14513a) + 41) * 41, 41, this.f14535e);
    }

    @Override // com.typesafe.config.impl.c2
    public final String toString() {
        return a0.c.p(new StringBuilder("'#"), this.f14535e, "' (COMMENT)");
    }
}
