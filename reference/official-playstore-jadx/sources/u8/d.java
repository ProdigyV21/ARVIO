package u8;

import d8.k;
import g8.b1;
import g8.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.collections.s;
import p9.n;
import qb.l;
import v9.b0;
import v9.k0;
import v9.o0;
import v9.r0;
import v9.t0;
import v9.v0;
import v9.w;
import x6.x;
import x9.j;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends v0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f22069c = l.C(2, false, null, 5).b(3);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f22070d = l.C(2, false, null, 5).b(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final fi.iki.elonen.f f22071b = new fi.iki.elonen.f(new io.sentry.util.l(27));

    @Override // v9.v0
    public final r0 d(w wVar) {
        return new t0(h(wVar, new a(2, false, false, null, 62)));
    }

    public final x g(b0 b0Var, g8.f fVar, a aVar) {
        if (b0Var.I0().getParameters().isEmpty()) {
            return new x(b0Var, Boolean.FALSE);
        }
        if (k.y(b0Var)) {
            r0 r0Var = (r0) b0Var.G0().get(0);
            return new x(v9.e.r(Collections.singletonList(new t0(r0Var.c(), h(r0Var.getType(), aVar))), b0Var.H0(), b0Var.I0(), b0Var.J0()), Boolean.FALSE);
        }
        if (v9.c.g(b0Var)) {
            return new x(x9.k.b(j.x, b0Var.I0().toString()), Boolean.FALSE);
        }
        n nVarX = fVar.x(this);
        k0 k0VarH0 = b0Var.H0();
        o0 o0VarG = fVar.g();
        List<b1> parameters = fVar.g().getParameters();
        ArrayList arrayList = new ArrayList(s.U(parameters, 10));
        for (b1 b1Var : parameters) {
            arrayList.add(io.sentry.util.l.g(b1Var, aVar, this.f22071b.P(b1Var, aVar)));
        }
        return new x(v9.e.t(k0VarH0, o0VarG, arrayList, b0Var.J0(), nVarX, new s8.d(fVar, this, b0Var, aVar)), Boolean.TRUE);
    }

    public final w h(w wVar, a aVar) {
        h hVarE = wVar.I0().e();
        if (hVarE instanceof b1) {
            aVar.getClass();
            return h(this.f22071b.P((b1) hVarE, a.a(aVar, 0, true, null, null, 59)), aVar);
        }
        if (!(hVarE instanceof g8.f)) {
            throw new IllegalStateException(("Unexpected declaration kind: " + hVarE).toString());
        }
        h hVarE2 = v9.c.v(wVar).I0().e();
        if (hVarE2 instanceof g8.f) {
            x xVarG = g(v9.c.h(wVar), (g8.f) hVarE, f22069c);
            b0 b0Var = (b0) xVarG.f22608i;
            boolean zBooleanValue = ((Boolean) xVarG.f22609l).booleanValue();
            x xVarG2 = g(v9.c.v(wVar), (g8.f) hVarE2, f22070d);
            b0 b0Var2 = (b0) xVarG2.f22608i;
            return (zBooleanValue || ((Boolean) xVarG2.f22609l).booleanValue()) ? new f(b0Var, b0Var2, 0) : v9.e.j(b0Var, b0Var2);
        }
        throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + hVarE2 + "\" while for lower it's \"" + hVarE + '\"').toString());
    }
}
