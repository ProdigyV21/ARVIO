package j8;

import io.ktor.http.ContentDisposition;

/* JADX INFO: loaded from: classes5.dex */
public abstract class x0 extends w0 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f19391p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public u9.t f19392q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public r7.a f19393r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x0(g8.k kVar, h8.h hVar, f9.f fVar, boolean z, g8.v0 v0Var) {
        super(kVar, hVar, fVar, null, v0Var);
        if (kVar == null) {
            v(0);
            throw null;
        }
        if (hVar == null) {
            v(1);
            throw null;
        }
        if (fVar == null) {
            v(2);
            throw null;
        }
        if (v0Var == null) {
            v(3);
            throw null;
        }
        this.f19391p = z;
    }

    public static /* synthetic */ void v(int i10) {
        Object[] objArr = new Object[3];
        if (i10 == 1) {
            objArr[0] = "annotations";
        } else if (i10 == 2) {
            objArr[0] = ContentDisposition.Parameters.Name;
        } else if (i10 == 3) {
            objArr[0] = "source";
        } else if (i10 == 4 || i10 == 5) {
            objArr[0] = "compileTimeInitializerFactory";
        } else {
            objArr[0] = "containingDeclaration";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorWithInitializerImpl";
        if (i10 == 4) {
            objArr[2] = "setCompileTimeInitializerFactory";
        } else if (i10 != 5) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "setCompileTimeInitializer";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public final void G0(u9.t tVar, r7.a aVar) {
        if (aVar == null) {
            v(5);
            throw null;
        }
        this.f19393r = aVar;
        if (tVar == null) {
            tVar = (u9.t) aVar.invoke();
        }
        this.f19392q = tVar;
    }

    @Override // g8.g1
    public final boolean K() {
        return this.f19391p;
    }

    @Override // g8.g1
    public final k9.g k0() {
        u9.t tVar = this.f19392q;
        if (tVar != null) {
            return (k9.g) tVar.invoke();
        }
        return null;
    }
}
