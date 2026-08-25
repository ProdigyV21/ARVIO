package a8;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class b1 implements kotlin.reflect.l {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f163p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f164i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f165l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final z1 f167n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final z1 f168o = m2.f0.B(null, new a1(this, 0));

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f163p = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(b1.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(b1.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    public b1(u uVar, int i10, int i11, r7.a aVar) {
        this.f164i = uVar;
        this.f165l = i10;
        this.f166m = i11;
        this.f167n = m2.f0.B(null, aVar);
    }

    public static final Type c(b1 b1Var, Type... typeArr) {
        int length = typeArr.length;
        if (length != 0) {
            return length != 1 ? new z0(typeArr) : (Type) kotlin.collections.r.j0(typeArr);
        }
        throw new x1("Expected at least 1 type for compound type");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b1)) {
            return false;
        }
        b1 b1Var = (b1) obj;
        return kotlin.jvm.internal.p.a(this.f164i, b1Var.f164i) && this.f165l == b1Var.f165l;
    }

    @Override // kotlin.reflect.l
    public final boolean f() {
        g8.n0 n0VarM = m();
        return (n0VarM instanceof g8.e1) && ((g8.e1) n0VarM).q0() != null;
    }

    @Override // kotlin.reflect.b
    public final List getAnnotations() {
        kotlin.reflect.m mVar = f163p[1];
        return (List) this.f168o.invoke();
    }

    @Override // kotlin.reflect.l
    public final int getIndex() {
        return this.f165l;
    }

    @Override // kotlin.reflect.l
    public final int getKind() {
        return this.f166m;
    }

    @Override // kotlin.reflect.l
    public final String getName() {
        g8.n0 n0VarM = m();
        g8.e1 e1Var = n0VarM instanceof g8.e1 ? (g8.e1) n0VarM : null;
        if (e1Var != null && !e1Var.d().f0()) {
            f9.f name = e1Var.getName();
            if (!name.f15369l) {
                return name.d();
            }
        }
        return null;
    }

    @Override // kotlin.reflect.l
    public final u1 getType() {
        return new u1(m().getType(), new a1(this, 1));
    }

    public final int hashCode() {
        return (this.f164i.hashCode() * 31) + this.f165l;
    }

    public final g8.n0 m() {
        kotlin.reflect.m mVar = f163p[0];
        return (g8.n0) this.f167n.invoke();
    }

    @Override // kotlin.reflect.l
    public final boolean s() {
        g8.n0 n0VarM = m();
        g8.e1 e1Var = n0VarM instanceof g8.e1 ? (g8.e1) n0VarM : null;
        if (e1Var != null) {
            return m9.d.a(e1Var);
        }
        return false;
    }

    public final String toString() throws IOException {
        String strB;
        h9.h hVar = b2.f169a;
        StringBuilder sb2 = new StringBuilder();
        int iC = h.f0.c(this.f166m);
        if (iC == 0) {
            sb2.append("instance parameter");
        } else if (iC == 2) {
            sb2.append("extension receiver parameter");
        } else if (iC == 3) {
            sb2.append("parameter #" + this.f165l + ' ' + getName());
        }
        sb2.append(" of ");
        g8.c cVarX = this.f164i.x();
        if (cVarX instanceof g8.p0) {
            strB = b2.c((g8.p0) cVarX);
        } else {
            if (!(cVarX instanceof g8.w)) {
                throw new IllegalStateException(("Illegal callable: " + cVarX).toString());
            }
            strB = b2.b((g8.w) cVarX);
        }
        sb2.append(strB);
        return sb2.toString();
    }
}
