package com.google.android.gms.common.api.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f12730a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o3.d f12731b;

    public /* synthetic */ f0(a aVar, o3.d dVar) {
        this.f12730a = aVar;
        this.f12731b = dVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f0) {
            f0 f0Var = (f0) obj;
            if (com.google.android.gms.common.internal.t.l(this.f12730a, f0Var.f12730a) && com.google.android.gms.common.internal.t.l(this.f12731b, f0Var.f12731b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f12730a, this.f12731b});
    }

    public final String toString() {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this);
        cVar.w(this.f12730a, "key");
        cVar.w(this.f12731b, "feature");
        return cVar.toString();
    }
}
