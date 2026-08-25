package com.google.android.gms.internal.cast;

import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class e4 implements d4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13238i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f4 f13239l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f13240m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile Object f13241n;

    public e4(int i10) {
        this.f13238i = i10;
        switch (i10) {
            case 1:
                this.f13239l = new f4();
                this.f13240m = i9.class.getName();
                break;
            default:
                k5 k5Var = k5.K;
                this.f13239l = new f4();
                this.f13241n = k5Var;
                break;
        }
    }

    public Logger a() {
        Logger logger = (Logger) this.f13241n;
        if (logger != null) {
            return logger;
        }
        synchronized (this.f13239l) {
            try {
                Logger logger2 = (Logger) this.f13241n;
                if (logger2 != null) {
                    return logger2;
                }
                Logger logger3 = Logger.getLogger((String) this.f13240m);
                this.f13241n = logger3;
                return logger3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String toString() {
        switch (this.f13238i) {
            case 0:
                Object objQ = (d4) this.f13241n;
                if (objQ == k5.J) {
                    String strValueOf = String.valueOf(this.f13240m);
                    objQ = androidx.compose.material3.d.q(new StringBuilder(strValueOf.length() + 25), "<supplier that returned ", strValueOf, ">");
                }
                String strValueOf2 = String.valueOf(objQ);
                return androidx.compose.material3.d.q(new StringBuilder(strValueOf2.length() + 19), "Suppliers.memoize(", strValueOf2, ")");
            default:
                return super.toString();
        }
    }

    @Override // com.google.android.gms.internal.cast.d4
    public Object zza() {
        d4 d4Var = (d4) this.f13241n;
        k5 k5Var = k5.J;
        if (d4Var != k5Var) {
            synchronized (this.f13239l) {
                try {
                    if (((d4) this.f13241n) != k5Var) {
                        Object objZza = ((d4) this.f13241n).zza();
                        this.f13240m = objZza;
                        this.f13241n = k5Var;
                        return objZza;
                    }
                } finally {
                }
            }
        }
        return this.f13240m;
    }
}
