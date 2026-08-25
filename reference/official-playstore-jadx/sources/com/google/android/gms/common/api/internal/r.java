package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class r implements s8.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f12786a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f12787b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f12788c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f12789d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f12790e;

    @Override // s8.h
    public g8.b1 a(w8.x xVar) {
        t8.e0 e0Var = (t8.e0) ((u9.r) this.f12790e).invoke(xVar);
        return e0Var != null ? e0Var : ((s8.h) ((androidx.core.provider.e) this.f12787b).f2141m).a(xVar);
    }

    public s b() {
        com.google.android.gms.common.internal.t.a("Must set register function", ((t) this.f12787b) != null);
        com.google.android.gms.common.internal.t.a("Must set unregister function", ((t) this.f12788c) != null);
        com.google.android.gms.common.internal.t.a("Must set holder", ((n) this.f12789d) != null);
        l lVar = ((n) this.f12789d).f12769c;
        com.google.android.gms.common.internal.t.j(lVar, "Key must not be null");
        return new s(new s0(this, (n) this.f12789d, (o3.d[]) this.f12790e, this.f12786a), new t0(this, lVar));
    }
}
