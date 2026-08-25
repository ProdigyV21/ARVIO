package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public final class b2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g1 f14509a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14510b;

    public b2(g1 g1Var, boolean z) {
        this.f14509a = g1Var;
        this.f14510b = z;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof b2) {
            b2 b2Var = (b2) obj;
            if (b2Var.f14509a.equals(this.f14509a) && b2Var.f14510b == this.f14510b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((this.f14509a.hashCode() + 41) * 41) + (this.f14510b ? 1 : 0)) * 41;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("${");
        sb2.append(this.f14510b ? "?" : "");
        sb2.append(this.f14509a.e());
        sb2.append("}");
        return sb2.toString();
    }
}
