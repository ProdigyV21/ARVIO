package com.google.android.gms.internal.auth;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class l1 implements Serializable, k1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k1 f13061i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile transient boolean f13062l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient Object f13063m;

    public l1(k1 k1Var) {
        this.f13061i = k1Var;
    }

    public final String toString() {
        return a0.c.l("Suppliers.memoize(", (this.f13062l ? a0.c.l("<supplier that returned ", String.valueOf(this.f13063m), ">") : this.f13061i).toString(), ")");
    }

    @Override // com.google.android.gms.internal.auth.k1
    public final Object zza() {
        if (!this.f13062l) {
            synchronized (this) {
                try {
                    if (!this.f13062l) {
                        Object objZza = this.f13061i.zza();
                        this.f13063m = objZza;
                        this.f13062l = true;
                        return objZza;
                    }
                } finally {
                }
            }
        }
        return this.f13063m;
    }
}
