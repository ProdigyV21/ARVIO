package com.google.android.gms.internal.fido;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class j implements Serializable, i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13787i;

    public j(Object obj) {
        this.f13787i = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            return b.h(this.f13787i, ((j) obj).f13787i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f13787i});
    }

    public final String toString() {
        return a0.c.l("Suppliers.ofInstance(", this.f13787i.toString(), ")");
    }
}
