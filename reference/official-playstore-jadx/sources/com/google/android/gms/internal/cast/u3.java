package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class u3 implements com.google.android.gms.common.api.x {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r3 f13623i;

    public u3(r3 r3Var) {
        this.f13623i = r3Var;
    }

    @Override // com.google.android.gms.common.api.x
    public final Status getStatus() {
        return Status.f12670o;
    }

    public final String toString() {
        r3 r3Var = this.f13623i;
        com.google.android.gms.common.internal.t.i(r3Var);
        return "OptInOptionsResultImpl[" + (r3Var.f13582i == 1) + "]";
    }
}
