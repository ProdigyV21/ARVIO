package io.sentry;

import java.util.Enumeration;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends io.sentry.protocol.c {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.protocol.c f17385m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.protocol.c f17386n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.protocol.c f17387o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final g4 f17388p;

    public j(io.sentry.protocol.c cVar, io.sentry.protocol.c cVar2, io.sentry.protocol.c cVar3, g4 g4Var) {
        this.f17385m = cVar;
        this.f17386n = cVar2;
        this.f17387o = cVar3;
        this.f17388p = g4Var;
    }

    @Override // io.sentry.protocol.c
    public final boolean a(Object obj) {
        throw null;
    }

    @Override // io.sentry.protocol.c
    public final Set b() {
        return y().f17550i.entrySet();
    }

    @Override // io.sentry.protocol.c
    public final Object c(Object obj) {
        Object objC = this.f17387o.c(obj);
        if (objC != null) {
            return objC;
        }
        Object objC2 = this.f17386n.c(obj);
        return objC2 != null ? objC2 : this.f17385m.c(obj);
    }

    @Override // io.sentry.protocol.c
    public final io.sentry.protocol.a d() {
        io.sentry.protocol.a aVarD = this.f17387o.d();
        if (aVarD != null) {
            return aVarD;
        }
        io.sentry.protocol.a aVarD2 = this.f17386n.d();
        return aVarD2 != null ? aVarD2 : this.f17385m.d();
    }

    @Override // io.sentry.protocol.c
    public final io.sentry.protocol.f e() {
        io.sentry.protocol.f fVarE = this.f17387o.e();
        if (fVarE != null) {
            return fVarE;
        }
        io.sentry.protocol.f fVarE2 = this.f17386n.e();
        return fVarE2 != null ? fVarE2 : this.f17385m.e();
    }

    @Override // io.sentry.protocol.c
    public final io.sentry.protocol.h f() {
        io.sentry.protocol.h hVarF = this.f17387o.f();
        if (hVarF != null) {
            return hVarF;
        }
        io.sentry.protocol.h hVarF2 = this.f17386n.f();
        return hVarF2 != null ? hVarF2 : this.f17385m.f();
    }

    @Override // io.sentry.protocol.c
    public final io.sentry.protocol.o g() {
        io.sentry.protocol.o oVarG = this.f17387o.g();
        if (oVarG != null) {
            return oVarG;
        }
        io.sentry.protocol.o oVarG2 = this.f17386n.g();
        return oVarG2 != null ? oVarG2 : this.f17385m.g();
    }

    @Override // io.sentry.protocol.c
    public final io.sentry.protocol.x h() {
        io.sentry.protocol.x xVarH = this.f17387o.h();
        if (xVarH != null) {
            return xVarH;
        }
        io.sentry.protocol.x xVarH2 = this.f17386n.h();
        return xVarH2 != null ? xVarH2 : this.f17385m.h();
    }

    @Override // io.sentry.protocol.c
    public final l7 i() {
        l7 l7VarI = this.f17387o.i();
        if (l7VarI != null) {
            return l7VarI;
        }
        l7 l7VarI2 = this.f17386n.i();
        return l7VarI2 != null ? l7VarI2 : this.f17385m.i();
    }

    @Override // io.sentry.protocol.c
    public final Enumeration j() {
        return y().f17550i.keys();
    }

    @Override // io.sentry.protocol.c
    public final Object k(String str, Object obj) {
        return x().k(str, obj);
    }

    @Override // io.sentry.protocol.c
    public final void l(io.sentry.protocol.c cVar) {
        throw null;
    }

    @Override // io.sentry.protocol.c
    public final void m(io.sentry.protocol.a aVar) {
        x().m(aVar);
    }

    @Override // io.sentry.protocol.c
    public final void n(io.sentry.protocol.b bVar) {
        x().n(bVar);
    }

    @Override // io.sentry.protocol.c
    public final void o(io.sentry.protocol.f fVar) {
        x().o(fVar);
    }

    @Override // io.sentry.protocol.c
    public final void p(io.sentry.protocol.h hVar) {
        throw null;
    }

    @Override // io.sentry.protocol.c
    public final void q(io.sentry.protocol.k kVar) {
        x().q(kVar);
    }

    @Override // io.sentry.protocol.c
    public final void r(io.sentry.protocol.o oVar) {
        x().r(oVar);
    }

    @Override // io.sentry.protocol.c
    public final void s(io.sentry.protocol.r rVar) {
        x().s(rVar);
    }

    @Override // io.sentry.protocol.c, io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        y().serialize(l3Var, iLogger);
    }

    @Override // io.sentry.protocol.c
    public final void t(io.sentry.protocol.x xVar) {
        x().t(xVar);
    }

    @Override // io.sentry.protocol.c
    public final void u(io.sentry.protocol.f0 f0Var) {
        x().u(f0Var);
    }

    @Override // io.sentry.protocol.c
    public final void v(l7 l7Var) {
        x().v(l7Var);
    }

    public final io.sentry.protocol.c x() {
        int i10 = i.f17343a[this.f17388p.ordinal()];
        io.sentry.protocol.c cVar = this.f17387o;
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? cVar : this.f17385m : this.f17386n : cVar;
    }

    public final io.sentry.protocol.c y() {
        io.sentry.protocol.c cVar = new io.sentry.protocol.c();
        cVar.l(this.f17385m);
        cVar.l(this.f17386n);
        cVar.l(this.f17387o);
        return cVar;
    }
}
