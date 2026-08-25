package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class h2 extends c2 {
    @Override // com.typesafe.config.impl.c2
    public final boolean a(c2 c2Var) {
        return c2Var instanceof h2;
    }

    @Override // com.typesafe.config.impl.c2
    public final String e() {
        return "\n";
    }

    @Override // com.typesafe.config.impl.c2
    public final boolean equals(Object obj) {
        return super.equals(obj) && ((h2) obj).b() == b();
    }

    @Override // com.typesafe.config.impl.c2
    public final int hashCode() {
        return b() + ((h.f0.c(this.f14513a) + 41) * 41);
    }

    @Override // com.typesafe.config.impl.c2
    public final String toString() {
        return "'\\n'@" + b();
    }
}
