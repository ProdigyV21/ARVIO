package a8;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class v1 implements kotlin.reflect.r, f0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f290n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g8.b1 f291i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final z1 f292l = m2.f0.B(null, new g0(this, 4));

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w1 f293m;

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f290n = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(v1.class), "upperBounds", "getUpperBounds()Ljava/util/List;"))};
    }

    public v1(w1 w1Var, g8.b1 b1Var) {
        Class cls;
        e0 e0VarB;
        Object objL;
        this.f291i = b1Var;
        if (w1Var == null) {
            g8.k kVarD = b1Var.d();
            if (kVarD instanceof g8.f) {
                objL = b((g8.f) kVarD);
            } else {
                if (!(kVarD instanceof g8.c)) {
                    throw new x1("Unknown type parameter container: " + kVarD);
                }
                g8.k kVarD2 = ((g8.c) kVarD).d();
                if (kVarD2 instanceof g8.f) {
                    e0VarB = b((g8.f) kVarD2);
                } else {
                    kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m mVar = kVarD instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m ? (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.m) kVarD : null;
                    if (mVar == null) {
                        throw new x1("Non-class callable descriptor must be deserialized: " + kVarD);
                    }
                    kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.l lVarH = mVar.H();
                    y8.r rVar = lVarH instanceof y8.r ? (y8.r) lVarH : null;
                    y8.y yVar = rVar != null ? rVar.f22974m : null;
                    l8.e eVar = yVar instanceof l8.e ? (l8.e) yVar : null;
                    if (eVar == null || (cls = eVar.f20063a) == null) {
                        throw new x1("Container of deserialized member is not resolved: " + mVar);
                    }
                    e0VarB = (e0) kotlin.jvm.internal.l0.f19747a.b(cls);
                }
                objL = kVarD.L(new e(e0VarB, 0), x6.t0.f22605a);
            }
            w1Var = (w1) objL;
        }
        this.f293m = w1Var;
    }

    public static e0 b(g8.f fVar) {
        Class clsI = e2.i(fVar);
        e0 e0Var = (e0) (clsI != null ? kotlin.jvm.internal.l0.f19747a.b(clsI) : null);
        if (e0Var != null) {
            return e0Var;
        }
        throw new x1("Type parameter container is not resolved: " + fVar.d());
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof v1)) {
            return false;
        }
        v1 v1Var = (v1) obj;
        return kotlin.jvm.internal.p.a(this.f293m, v1Var.f293m) && getName().equals(v1Var.getName());
    }

    @Override // a8.f0
    public final g8.h getDescriptor() {
        return this.f291i;
    }

    @Override // kotlin.reflect.r
    public final String getName() {
        return this.f291i.getName().d();
    }

    @Override // kotlin.reflect.r
    public final List getUpperBounds() {
        kotlin.reflect.m mVar = f290n[0];
        return (List) this.f292l.invoke();
    }

    public final int hashCode() {
        return getName().hashCode() + (this.f293m.hashCode() * 31);
    }

    public final String toString() {
        int i10;
        StringBuilder sb2 = new StringBuilder();
        int iC = h.f0.c(this.f291i.y());
        if (iC == 0) {
            i10 = 1;
        } else if (iC == 1) {
            i10 = 2;
        } else {
            if (iC != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i10 = 3;
        }
        int iC2 = h.f0.c(i10);
        if (iC2 != 0) {
            if (iC2 == 1) {
                sb2.append("in ");
            } else {
                if (iC2 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                sb2.append("out ");
            }
        }
        sb2.append(getName());
        return sb2.toString();
    }
}
