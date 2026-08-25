package a8;

import kotlin.reflect.m;

/* JADX INFO: loaded from: classes5.dex */
public abstract class n1 extends l1 implements m.b {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f229s;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final z1 f230q = m2.f0.B(null, new m1(this, 1));

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Object f231r = f4.f.o(2, new m1(this, 0));

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f229s = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(n1.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;"))};
    }

    @Override // a8.l1
    public final g8.o0 B() {
        kotlin.reflect.m mVar = f229s[0];
        return (g8.q0) this.f230q.invoke();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof n1) && kotlin.jvm.internal.p.a(C(), ((n1) obj).C());
    }

    @Override // kotlin.reflect.c
    public final String getName() {
        return androidx.compose.foundation.c.u(new StringBuilder("<get-"), C().f255r, '>');
    }

    public final int hashCode() {
        return C().hashCode();
    }

    public final String toString() {
        return "getter of " + C();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.u
    public final b8.e u() {
        return (b8.e) this.f231r.getValue();
    }

    @Override // a8.u
    public final g8.c x() {
        kotlin.reflect.m mVar = f229s[0];
        return (g8.q0) this.f230q.invoke();
    }
}
