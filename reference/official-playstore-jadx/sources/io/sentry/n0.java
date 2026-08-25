package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class n0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i4 f17482a;

    public n0(i4 i4Var) {
        this.f17482a = i4Var;
    }

    @Override // io.sentry.c1
    public final c1 A(String str) {
        return this.f17482a.A("getCurrentScopes");
    }

    @Override // io.sentry.c1
    public final void a(boolean z) {
        this.f17482a.a(z);
    }

    @Override // io.sentry.c1
    public final void b(long j10) {
        this.f17482a.b(j10);
    }

    @Override // io.sentry.c1
    public final boolean c() {
        return this.f17482a.c();
    }

    @Override // io.sentry.c1
    /* JADX INFO: renamed from: clone */
    public final t0 m7021clone() {
        return this.f17482a.m7021clone();
    }

    @Override // io.sentry.c1
    public final void d(io.sentry.protocol.i0 i0Var) {
        this.f17482a.d(i0Var);
    }

    @Override // io.sentry.c1
    public final void e(String str, String str2) {
        this.f17482a.e(str, str2);
    }

    @Override // io.sentry.c1
    public final io.sentry.transport.p f() {
        return this.f17482a.f();
    }

    @Override // io.sentry.c1
    public final void g(String str, String str2) {
        this.f17482a.g(str, str2);
    }

    @Override // io.sentry.c1
    public final y6 getOptions() {
        return this.f17482a.getOptions();
    }

    @Override // io.sentry.c1
    public final void h(f fVar, j0 j0Var) {
        this.f17482a.h(fVar, j0Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v i(fi.iki.elonen.f fVar, j0 j0Var) {
        return this.f17482a.i(fVar, j0Var);
    }

    @Override // io.sentry.c1
    public final boolean isEnabled() {
        return this.f17482a.isEnabled();
    }

    @Override // io.sentry.c1
    public final m1 j() {
        return this.f17482a.j();
    }

    @Override // io.sentry.c1
    public final void k(f fVar) {
        this.f17482a.k(fVar);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v l(q3 q3Var) {
        return this.f17482a.l(q3Var);
    }

    @Override // io.sentry.c1
    public final void m() {
        this.f17482a.m();
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v n(io.sentry.protocol.e0 e0Var, s7 s7Var, j0 j0Var) {
        return this.f17482a.z(e0Var, s7Var, j0Var, null);
    }

    @Override // io.sentry.c1
    public final void o(e4 e4Var) {
        this.f17482a.o(e4Var);
    }

    @Override // io.sentry.c1
    public final /* synthetic */ boolean p() {
        return false;
    }

    @Override // io.sentry.c1
    public final void q() {
        this.f17482a.q();
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v r(a7 a7Var, j0 j0Var) {
        return this.f17482a.r(a7Var, j0Var);
    }

    @Override // io.sentry.c1
    public final void s(e4 e4Var) {
        o(e4Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v t(j5 j5Var, j0 j0Var) {
        return this.f17482a.t(j5Var, j0Var);
    }

    @Override // io.sentry.c1
    public final m1 u(u7 u7Var, w7 w7Var) {
        return this.f17482a.u(u7Var, w7Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v v(io.sentry.protocol.i iVar) {
        return this.f17482a.B(iVar);
    }

    @Override // io.sentry.c1
    public final a1 w() {
        return this.f17482a.f17347a;
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v x(Throwable th) {
        return y(th, new j0());
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v y(Throwable th, j0 j0Var) {
        return this.f17482a.y(th, j0Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v z(io.sentry.protocol.e0 e0Var, s7 s7Var, j0 j0Var, t3 t3Var) {
        return this.f17482a.z(e0Var, s7Var, j0Var, t3Var);
    }

    /* JADX INFO: renamed from: clone, reason: collision with other method in class */
    public final Object m7023clone() {
        return this.f17482a.m7021clone();
    }
}
