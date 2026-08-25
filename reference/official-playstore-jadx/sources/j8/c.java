package j8;

import g8.z0;
import io.ktor.http.ContentDisposition;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c extends j {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(u9.v vVar, g8.k kVar, h8.h hVar, f9.f fVar, int i10, boolean z, int i11, z0 z0Var) {
        super(vVar, kVar, hVar, fVar, i10, z, i11, z0Var);
        if (vVar == null) {
            v(0);
            throw null;
        }
        if (kVar == null) {
            v(1);
            throw null;
        }
        if (fVar == null) {
            v(3);
            throw null;
        }
        if (i10 == 0) {
            v(4);
            throw null;
        }
        if (z0Var != null) {
        } else {
            v(6);
            throw null;
        }
    }

    public static /* synthetic */ void v(int i10) {
        Object[] objArr = new Object[3];
        switch (i10) {
            case 1:
                objArr[0] = "containingDeclaration";
                break;
            case 2:
                objArr[0] = "annotations";
                break;
            case 3:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 4:
                objArr[0] = "variance";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
                objArr[0] = "supertypeLoopChecker";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractLazyTypeParameterDescriptor";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // j8.o
    public final String toString() {
        return (this.f19287p ? "reified " : "") + (y() != 1 ? v.f.B(y()).concat(" ") : "") + getName();
    }
}
