package j8;

import g8.b1;
import g8.z0;
import io.ktor.http.LinkHeader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends v9.g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final z0 f19284c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ j f19285d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar, u9.v vVar, z0 z0Var) {
        super(vVar);
        if (vVar == null) {
            m(0);
            throw null;
        }
        this.f19285d = jVar;
        this.f19284c = z0Var;
    }

    public static /* synthetic */ void m(int i10) {
        String str = (i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4 || i10 == 5 || i10 == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4 || i10 == 5 || i10 == 8) ? 2 : 3];
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
                break;
            case 6:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 7:
                objArr[0] = "supertypes";
                break;
            case 9:
                objArr[0] = "classifier";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        if (i10 == 1) {
            objArr[1] = "computeSupertypes";
        } else if (i10 == 2) {
            objArr[1] = "getParameters";
        } else if (i10 == 3) {
            objArr[1] = "getDeclarationDescriptor";
        } else if (i10 == 4) {
            objArr[1] = "getBuiltIns";
        } else if (i10 == 5) {
            objArr[1] = "getSupertypeLoopChecker";
        } else if (i10 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
        } else {
            objArr[1] = "processSupertypesWithoutCycles";
        }
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
                break;
            case 6:
                objArr[2] = "reportSupertypeLoopError";
                break;
            case 7:
                objArr[2] = "processSupertypesWithoutCycles";
                break;
            case 9:
                objArr[2] = "isSameClassifier";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 1 && i10 != 2 && i10 != 3 && i10 != 4 && i10 != 5 && i10 != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // v9.g
    public final Collection c() {
        List listH0 = this.f19285d.H0();
        if (listH0 != null) {
            return listH0;
        }
        m(1);
        throw null;
    }

    @Override // v9.o0
    public final g8.h e() {
        j jVar = this.f19285d;
        if (jVar != null) {
            return jVar;
        }
        m(3);
        throw null;
    }

    @Override // v9.o0
    public final boolean f() {
        return true;
    }

    @Override // v9.g
    public final v9.w g() {
        return x9.k.b(x9.j.f22696q, new String[0]);
    }

    @Override // v9.o0
    public final List getParameters() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        m(2);
        throw null;
    }

    @Override // v9.g
    public final z0 h() {
        z0 z0Var = this.f19284c;
        if (z0Var != null) {
            return z0Var;
        }
        m(5);
        throw null;
    }

    @Override // v9.o0
    public final d8.k j() {
        d8.k kVarE = m9.d.e(this.f19285d);
        if (kVarE != null) {
            return kVarE;
        }
        m(4);
        throw null;
    }

    @Override // v9.g
    public final boolean k(g8.h hVar) {
        if (!(hVar instanceof b1)) {
            return false;
        }
        return i9.d.f16248a.b(this.f19285d, (b1) hVar, true, i9.c.f16247i);
    }

    @Override // v9.g
    public final List l(List list) {
        List listG0 = this.f19285d.G0(list);
        if (listG0 != null) {
            return listG0;
        }
        m(8);
        throw null;
    }

    public final String toString() {
        return this.f19285d.getName().f15368i;
    }
}
