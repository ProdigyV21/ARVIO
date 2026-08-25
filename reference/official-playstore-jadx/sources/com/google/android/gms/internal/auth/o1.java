package com.google.android.gms.internal.auth;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class o1 implements Serializable, k1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13084i;

    public o1(Object obj) {
        this.f13084i = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof o1)) {
            return false;
        }
        Object obj2 = ((o1) obj).f13084i;
        Object obj3 = this.f13084i;
        return obj3 == obj2 || obj3.equals(obj2);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f13084i});
    }

    public final String toString() {
        return a0.c.l("Suppliers.ofInstance(", this.f13084i.toString(), ")");
    }

    @Override // com.google.android.gms.internal.auth.k1
    public final Object zza() {
        return this.f13084i;
    }
}
