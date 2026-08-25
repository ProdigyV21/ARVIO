package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class n1 implements k1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile k1 f13077i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f13078l;

    public final String toString() {
        Object objL = this.f13077i;
        if (objL == m1.f13074i) {
            objL = a0.c.l("<supplier that returned ", String.valueOf(this.f13078l), ">");
        }
        return a0.c.l("Suppliers.memoize(", String.valueOf(objL), ")");
    }

    @Override // com.google.android.gms.internal.auth.k1
    public final Object zza() {
        k1 k1Var = this.f13077i;
        m1 m1Var = m1.f13074i;
        if (k1Var != m1Var) {
            synchronized (this) {
                try {
                    if (this.f13077i != m1Var) {
                        Object objZza = this.f13077i.zza();
                        this.f13078l = objZza;
                        this.f13077i = m1Var;
                        return objZza;
                    }
                } finally {
                }
            }
        }
        return this.f13078l;
    }
}
