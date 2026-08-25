package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class w3 implements x2, v2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x3 f13138i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d2 f13139l;

    public w3() {
        x3 x3Var = x3.zzb;
        this.f13138i = x3Var;
        if (x3Var.g()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.f13139l = x3Var.b();
    }

    public final d2 a() {
        if (!this.f13139l.g()) {
            return this.f13139l;
        }
        d2 d2Var = this.f13139l;
        d2Var.getClass();
        f3.f13029c.a(d2Var.getClass()).a(d2Var);
        d2Var.d();
        return this.f13139l;
    }

    public final Object clone() {
        w3 w3Var = (w3) this.f13138i.h(5);
        w3Var.f13139l = a();
        return w3Var;
    }
}
