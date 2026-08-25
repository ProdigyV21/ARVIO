package a8;

import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class y0 extends j0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Class f310l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f311m = f4.f.o(2, new t0(this, 1));

    public y0(Class cls) {
        this.f310l = cls;
    }

    @Override // kotlin.jvm.internal.f
    public final Class c() {
        return this.f310l;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof y0) {
            return kotlin.jvm.internal.p.a(this.f310l, ((y0) obj).f310l);
        }
        return false;
    }

    public final int hashCode() {
        return this.f310l.hashCode();
    }

    @Override // a8.j0
    public final Collection t() {
        return kotlin.collections.z.f19728i;
    }

    public final String toString() {
        return "file class " + m8.c.a(this.f310l).b();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.j0
    public final Collection u(f9.f fVar) {
        z1 z1Var = ((w0) this.f311m.getValue()).f298d;
        kotlin.reflect.m mVar = w0.f296h[1];
        return ((p9.n) z1Var.invoke()).c(fVar, o8.e.f20794l);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, x6.s] */
    @Override // a8.j0
    public final g8.p0 v(int i10) {
        x6.j0 j0Var = (x6.j0) ((w0) this.f311m.getValue()).f300f.getValue();
        if (j0Var == null) {
            return null;
        }
        e9.g gVar = (e9.g) j0Var.f22587i;
        a9.n0 n0Var = (a9.n0) j0Var.f22588l;
        e9.f fVar = (e9.f) j0Var.f22589m;
        a9.t0 t0Var = (a9.t0) com.google.common.util.concurrent.r0.m(n0Var, d9.q.f14875n, i10);
        if (t0Var == null) {
            return null;
        }
        return (g8.p0) e2.f(this.f310l, t0Var, gVar, new androidx.work.impl.constraints.j(n0Var.f601q), fVar, x0.f305i);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, x6.s] */
    @Override // a8.j0
    public final Class x() {
        Class cls = (Class) ((w0) this.f311m.getValue()).f299e.getValue();
        return cls == null ? this.f310l : cls;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.j0
    public final Collection y(f9.f fVar) {
        z1 z1Var = ((w0) this.f311m.getValue()).f298d;
        kotlin.reflect.m mVar = w0.f296h[1];
        return ((p9.n) z1Var.invoke()).g(fVar, o8.e.f20794l);
    }
}
