package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.m1;
import java.util.List;
import v9.b0;
import v9.b1;
import v9.k0;
import v9.o0;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends j8.f implements m {
    public List A;
    public b0 B;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final m1 f19901t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final c9.f f19902u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final androidx.work.impl.constraints.j f19903v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final c9.g f19904w;
    public final l x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public b0 f19905y;
    public b0 z;

    public w(u9.v vVar, g8.k kVar, h8.h hVar, f9.f fVar, g8.p pVar, m1 m1Var, c9.f fVar2, androidx.work.impl.constraints.j jVar, c9.g gVar, l lVar) {
        super(vVar, kVar, hVar, fVar, pVar);
        this.f19901t = m1Var;
        this.f19902u = fVar2;
        this.f19903v = jVar;
        this.f19904w = gVar;
        this.x = lVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final androidx.work.impl.constraints.j D() {
        throw null;
    }

    @Override // g8.a1
    public final b0 F() {
        b0 b0Var = this.z;
        if (b0Var != null) {
            return b0Var;
        }
        kotlin.jvm.internal.p.i("expandedType");
        throw null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final c9.f G() {
        throw null;
    }

    public final void G0(List list, b0 b0Var, b0 b0Var2) {
        p9.n nVarU;
        b0 b0VarT;
        this.f19272q = list;
        this.f19905y = b0Var;
        this.z = b0Var2;
        this.A = g8.y.c(this);
        g8.f fVarR = r();
        if (fVarR == null || (nVarU = fVarR.U()) == null) {
            nVarU = p9.m.f21235b;
        }
        p9.n nVar = nVarU;
        f8.m mVar = new f8.m(this, 1);
        x9.h hVar = b1.f22188a;
        if (x9.k.e(this)) {
            b0VarT = x9.k.b(x9.j.f22700u, toString());
        } else {
            o0 o0VarG = g();
            if (o0VarG == null) {
                b1.a(12);
                throw null;
            }
            List listD = b1.d(((j8.e) o0VarG).getParameters());
            k0.f22220l.getClass();
            b0VarT = v9.e.t(k0.f22221m, o0VarG, listD, false, nVar, mVar);
        }
        this.B = b0VarT;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m
    public final l H() {
        return this.x;
    }

    @Override // g8.x0
    public final g8.l b(y0 y0Var) {
        if (y0Var.f22263a.e()) {
            return this;
        }
        w wVar = new w(this.f19270o, d(), getAnnotations(), getName(), this.f19271p, this.f19901t, this.f19902u, this.f19903v, this.f19904w, this.x);
        wVar.G0(n(), v9.c.b(y0Var.g(1, o0())), v9.c.b(y0Var.g(1, F())));
        return wVar;
    }

    @Override // g8.h
    public final b0 m() {
        b0 b0Var = this.B;
        if (b0Var != null) {
            return b0Var;
        }
        kotlin.jvm.internal.p.i("defaultTypeImpl");
        throw null;
    }

    @Override // g8.a1
    public final b0 o0() {
        b0 b0Var = this.f19905y;
        if (b0Var != null) {
            return b0Var;
        }
        kotlin.jvm.internal.p.i("underlyingType");
        throw null;
    }

    @Override // g8.a1
    public final g8.f r() {
        if (v9.c.g(F())) {
            return null;
        }
        g8.h hVarE = F().I0().e();
        if (hVarE instanceof g8.f) {
            return (g8.f) hVarE;
        }
        return null;
    }
}
