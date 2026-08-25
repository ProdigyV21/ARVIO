package a8;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class u1 implements kotlin.jvm.internal.q {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f281o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v9.w f282i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final z1 f283l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final z1 f284m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final z1 f285n;

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f281o = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(u1.class), "classifier", "getClassifier()Lkotlin/reflect/KClassifier;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(u1.class), "arguments", "getArguments()Ljava/util/List;"))};
    }

    public u1(v9.w wVar, r7.a aVar) {
        this.f282i = wVar;
        z1 z1Var = aVar instanceof z1 ? (z1) aVar : null;
        this.f283l = z1Var == null ? aVar != null ? m2.f0.B(null, aVar) : null : z1Var;
        int i10 = 1;
        this.f284m = m2.f0.B(null, new t1(this, i10));
        this.f285n = m2.f0.B(null, new l0(this, aVar, i10));
    }

    @Override // kotlin.reflect.q
    public final kotlin.reflect.e b() {
        kotlin.reflect.m mVar = f281o[0];
        return (kotlin.reflect.e) this.f284m.invoke();
    }

    public final kotlin.reflect.e c(v9.w wVar) {
        v9.w type;
        g8.h hVarE = wVar.I0().e();
        if (hVarE instanceof g8.f) {
            Class clsI = e2.i((g8.f) hVarE);
            if (clsI != null) {
                if (!clsI.isArray()) {
                    if (v9.b1.e(wVar)) {
                        return new e0(clsI);
                    }
                    Class cls = (Class) m8.c.f20267b.get(clsI);
                    if (cls != null) {
                        clsI = cls;
                    }
                    return new e0(clsI);
                }
                v9.r0 r0Var = (v9.r0) kotlin.collections.x.T0(wVar.G0());
                if (r0Var == null || (type = r0Var.getType()) == null) {
                    return new e0(clsI);
                }
                kotlin.reflect.e eVarC = c(type);
                if (eVarC != null) {
                    return new e0(Array.newInstance((Class<?>) ((kotlin.jvm.internal.f) t7.a.u(eVarC)).c(), 0).getClass());
                }
                throw new x1("Cannot determine classifier for array element type: " + this);
            }
        } else {
            if (hVarE instanceof g8.b1) {
                return new v1(null, (g8.b1) hVarE);
            }
            if (hVarE instanceof g8.a1) {
                throw new x1("An operation is not implemented: Type alias classifiers are not yet supported");
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof u1)) {
            return false;
        }
        u1 u1Var = (u1) obj;
        return kotlin.jvm.internal.p.a(this.f282i, u1Var.f282i) && kotlin.jvm.internal.p.a(b(), u1Var.b()) && g().equals(u1Var.g());
    }

    @Override // kotlin.reflect.q
    public final List g() {
        kotlin.reflect.m mVar = f281o[1];
        return (List) this.f285n.invoke();
    }

    @Override // kotlin.reflect.b
    public final List getAnnotations() {
        return e2.d(this.f282i);
    }

    public final int hashCode() {
        int iHashCode = this.f282i.hashCode() * 31;
        kotlin.reflect.e eVarB = b();
        return g().hashCode() + ((iHashCode + (eVarB != null ? eVarB.hashCode() : 0)) * 31);
    }

    @Override // kotlin.reflect.q
    public final boolean j() {
        return this.f282i.J0();
    }

    @Override // kotlin.jvm.internal.q
    public final Type k() {
        z1 z1Var = this.f283l;
        if (z1Var != null) {
            return (Type) z1Var.invoke();
        }
        return null;
    }

    public final String toString() {
        h9.h hVar = b2.f169a;
        return b2.f169a.V(this.f282i);
    }
}
