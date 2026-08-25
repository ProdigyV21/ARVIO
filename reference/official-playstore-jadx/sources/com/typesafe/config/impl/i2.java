package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class i2 extends c2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f14555e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f14556f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f14557g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Throwable f14558h;

    public i2(l6.k kVar, String str, String str2, boolean z, Throwable th) {
        super(15, kVar, null, null);
        this.f14555e = str;
        this.f14556f = str2;
        this.f14557g = z;
        this.f14558h = th;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean a(c2 c2Var) {
        return c2Var instanceof i2;
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        i2 i2Var = (i2) obj;
        return i2Var.f14555e.equals(this.f14555e) && i2Var.f14556f.equals(this.f14556f) && i2Var.f14557g == this.f14557g && y.a(i2Var.f14558h, this.f14558h);
    }

    @Override // com.typesafe.config.impl.c2
    public final int hashCode() {
        int iHashCode = (Boolean.valueOf(this.f14557g).hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((h.f0.c(this.f14513a) + 41) * 41, 41, this.f14555e), 41, this.f14556f)) * 41;
        Throwable th = this.f14558h;
        return th != null ? (th.hashCode() + iHashCode) * 41 : iHashCode;
    }

    @Override // com.typesafe.config.impl.c2
    public final String toString() {
        StringBuilder sb2 = new StringBuilder("'");
        sb2.append(this.f14555e);
        sb2.append("' (");
        return a0.c.p(sb2, this.f14556f, ")");
    }
}
