package j8;

import io.ktor.http.ContentDisposition;

/* JADX INFO: loaded from: classes5.dex */
public abstract class p extends o implements g8.l {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g8.k f19329m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final g8.v0 f19330n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(g8.k kVar, h8.h hVar, f9.f fVar, g8.v0 v0Var) {
        super(hVar, fVar);
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
        this.f19329m = kVar;
        this.f19330n = v0Var;
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 4 || i10 == 5 || i10 == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 4 || i10 == 5 || i10 == 6) ? 2 : 3];
        switch (i10) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
                objArr[0] = "source";
                break;
            case 4:
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i10 == 4) {
            objArr[1] = "getOriginal";
        } else if (i10 == 5) {
            objArr[1] = "getContainingDeclaration";
        } else if (i10 != 6) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
        } else {
            objArr[1] = "getSource";
        }
        if (i10 != 4 && i10 != 5 && i10 != 6) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 4 && i10 != 5 && i10 != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public g8.k d() {
        g8.k kVar = this.f19329m;
        if (kVar != null) {
            return kVar;
        }
        v(5);
        throw null;
    }

    public g8.v0 f() {
        g8.v0 v0Var = this.f19330n;
        if (v0Var != null) {
            return v0Var;
        }
        v(6);
        throw null;
    }

    @Override // j8.o, g8.k
    /* JADX INFO: renamed from: D0 */
    public g8.n a() {
        return this;
    }
}
