package j8;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.InvalidModuleException;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 extends o implements g8.b0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final u9.p f19252m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d8.k f19253n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Map f19254o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i0 f19255p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public androidx.work.impl.constraints.j f19256q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public g8.i0 f19257r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f19258s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final u9.q f19259t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final x6.i0 f19260u;

    public d0(f9.f fVar, u9.p pVar, d8.k kVar, int i10) {
        super(h8.g.f15863a, fVar);
        this.f19252m = pVar;
        this.f19253n = kVar;
        if (!fVar.f15369l) {
            throw new IllegalArgumentException("Module name must be special: " + fVar);
        }
        this.f19254o = kotlin.collections.a0.f19683i;
        i0 i0Var = (i0) X(g0.f19279a);
        this.f19255p = i0Var == null ? h0.f19283a : i0Var;
        this.f19258s = true;
        this.f19259t = pVar.g(new ab.h(this, 20));
        this.f19260u = new x6.i0(new d8.n(this, 2));
    }

    @Override // g8.b0
    public final g8.m0 B(f9.c cVar) {
        D0();
        return (g8.m0) this.f19259t.invoke(cVar);
    }

    public final void D0() {
        x6.t0 t0Var;
        if (this.f19258s) {
            return;
        }
        g8.z zVar = (g8.z) X(g8.y.f15558a);
        if (zVar != null) {
            zVar.a();
            t0Var = x6.t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var != null) {
            return;
        }
        throw new InvalidModuleException("Accessing invalid module descriptor " + this);
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.t(this, obj);
    }

    @Override // g8.b0
    public final Object X(c2.a aVar) {
        Object obj = this.f19254o.get(aVar);
        if (obj == null) {
            return null;
        }
        return obj;
    }

    @Override // g8.k
    public final g8.k d() {
        return null;
    }

    @Override // g8.b0
    public final d8.k j() {
        return this.f19253n;
    }

    @Override // g8.b0
    public final Collection p(f9.c cVar, r7.l lVar) {
        D0();
        D0();
        return ((n) this.f19260u.getValue()).p(cVar, lVar);
    }

    @Override // g8.b0
    public final boolean t(g8.b0 b0Var) {
        if (equals(b0Var)) {
            return true;
        }
        this.f19256q.getClass();
        if (kotlin.collections.x.f0(kotlin.collections.b0.f19686i, b0Var)) {
            return true;
        }
        u0();
        return b0Var.u0().contains(this);
    }

    @Override // j8.o
    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(o.A0(this));
        if (!this.f19258s) {
            sb2.append(" !isValid");
        }
        sb2.append(" packageFragmentProvider: ");
        g8.i0 i0Var = this.f19257r;
        sb2.append(i0Var != null ? i0Var.getClass().getSimpleName() : null);
        return sb2.toString();
    }

    @Override // g8.b0
    public final List u0() {
        if (this.f19256q != null) {
            return kotlin.collections.z.f19728i;
        }
        throw new AssertionError(a0.c.p(new StringBuilder("Dependencies of module "), getName().f15368i, " were not set"));
    }
}
