package a8;

import kotlin.reflect.h;

/* JADX INFO: loaded from: classes5.dex */
public abstract class p1 extends l1 implements h.a {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f243s;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final z1 f244q = m2.f0.B(null, new o1(this, 1));

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Object f245r = f4.f.o(2, new o1(this, 0));

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f243s = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(p1.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertySetterDescriptor;"))};
    }

    @Override // a8.l1
    public final g8.o0 B() {
        kotlin.reflect.m mVar = f243s[0];
        return (g8.r0) this.f244q.invoke();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof p1) && kotlin.jvm.internal.p.a(C(), ((p1) obj).C());
    }

    @Override // kotlin.reflect.c
    public final String getName() {
        return androidx.compose.foundation.c.u(new StringBuilder("<set-"), C().f255r, '>');
    }

    public final int hashCode() {
        return C().hashCode();
    }

    public final String toString() {
        return "setter of " + C();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.u
    public final b8.e u() {
        return (b8.e) this.f245r.getValue();
    }

    @Override // a8.u
    public final g8.c x() {
        kotlin.reflect.m mVar = f243s[0];
        return (g8.r0) this.f244q.invoke();
    }
}
