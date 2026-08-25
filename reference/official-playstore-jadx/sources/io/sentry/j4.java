package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class j4 implements c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j4 f17401a = new j4();

    @Override // io.sentry.c1
    public final c1 A(String str) {
        return s4.b().A("getCurrentScopes");
    }

    @Override // io.sentry.c1
    public final void a(boolean z) {
        s4.a();
    }

    @Override // io.sentry.c1
    public final void b(long j10) {
        s4.b().b(j10);
    }

    @Override // io.sentry.c1
    public final boolean c() {
        return s4.b().c();
    }

    @Override // io.sentry.c1
    public final void d(io.sentry.protocol.i0 i0Var) {
        s4.j(i0Var);
    }

    @Override // io.sentry.c1
    public final void e(String str, String str2) {
        s4.i(str, str2);
    }

    @Override // io.sentry.c1
    public final io.sentry.transport.p f() {
        return s4.b().f();
    }

    @Override // io.sentry.c1
    public final void g(String str, String str2) {
        s4.h(str, str2);
    }

    @Override // io.sentry.c1
    public final y6 getOptions() {
        return s4.b().getOptions();
    }

    @Override // io.sentry.c1
    public final void h(f fVar, j0 j0Var) {
        s4.b().h(fVar, j0Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v i(fi.iki.elonen.f fVar, j0 j0Var) {
        return s4.b().i(fVar, j0Var);
    }

    @Override // io.sentry.c1
    public final boolean isEnabled() {
        return s4.f();
    }

    @Override // io.sentry.c1
    public final m1 j() {
        return s4.b().j();
    }

    @Override // io.sentry.c1
    public final void k(f fVar) {
        h(fVar, new j0());
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v l(q3 q3Var) {
        return s4.b().l(q3Var);
    }

    @Override // io.sentry.c1
    public final void m() {
        s4.b().m();
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v n(io.sentry.protocol.e0 e0Var, s7 s7Var, j0 j0Var) {
        return z(e0Var, s7Var, j0Var, null);
    }

    @Override // io.sentry.c1
    public final void o(e4 e4Var) {
        s4.b().o(e4Var);
    }

    @Override // io.sentry.c1
    public final /* synthetic */ boolean p() {
        return false;
    }

    @Override // io.sentry.c1
    public final void q() {
        s4.b().q();
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v r(a7 a7Var, j0 j0Var) {
        return s4.b().r(a7Var, j0Var);
    }

    @Override // io.sentry.c1
    public final void s(e4 e4Var) {
        o(e4Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v t(j5 j5Var, j0 j0Var) {
        return s4.b().t(j5Var, j0Var);
    }

    @Override // io.sentry.c1
    public final m1 u(u7 u7Var, w7 w7Var) {
        return s4.b().u(u7Var, w7Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v v(io.sentry.protocol.i iVar) {
        return s4.b().v(iVar);
    }

    @Override // io.sentry.c1
    public final a1 w() {
        return s4.b().w();
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v x(Throwable th) {
        return y(th, new j0());
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v y(Throwable th, j0 j0Var) {
        return s4.b().y(th, j0Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v z(io.sentry.protocol.e0 e0Var, s7 s7Var, j0 j0Var, t3 t3Var) {
        return s4.b().z(e0Var, s7Var, j0Var, t3Var);
    }

    @Override // io.sentry.c1
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final t0 m7022clone() {
        return s4.b().m7022clone();
    }
}
