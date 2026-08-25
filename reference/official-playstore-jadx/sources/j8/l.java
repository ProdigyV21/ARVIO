package j8;

import io.ktor.http.ContentDisposition;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l extends b {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final g8.k f19310o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final g8.v0 f19311p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(u9.v vVar, g8.k kVar, f9.f fVar, g8.v0 v0Var) {
        super(vVar, fVar);
        if (vVar == null) {
            A0(0);
            throw null;
        }
        if (kVar == null) {
            A0(1);
            throw null;
        }
        if (fVar == null) {
            A0(2);
            throw null;
        }
        if (v0Var == null) {
            A0(3);
            throw null;
        }
        this.f19310o = kVar;
        this.f19311p = v0Var;
    }

    public static /* synthetic */ void A0(int i10) {
        String str = (i10 == 4 || i10 == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 4 || i10 == 5) ? 2 : 3];
        if (i10 == 1) {
            objArr[0] = "containingDeclaration";
        } else if (i10 == 2) {
            objArr[0] = ContentDisposition.Parameters.Name;
        } else if (i10 == 3) {
            objArr[0] = "source";
        } else if (i10 == 4 || i10 == 5) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
        } else {
            objArr[0] = "storageManager";
        }
        if (i10 == 4) {
            objArr[1] = "getContainingDeclaration";
        } else if (i10 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
        } else {
            objArr[1] = "getSource";
        }
        if (i10 != 4 && i10 != 5) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 4 && i10 != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // g8.k
    public final g8.k d() {
        g8.k kVar = this.f19310o;
        if (kVar != null) {
            return kVar;
        }
        A0(4);
        throw null;
    }

    @Override // g8.n
    public final g8.v0 f() {
        g8.v0 v0Var = this.f19311p;
        if (v0Var != null) {
            return v0Var;
        }
        A0(5);
        throw null;
    }

    public boolean isExternal() {
        return false;
    }
}
