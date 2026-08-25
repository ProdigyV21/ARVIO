package j8;

import g8.w;
import io.ktor.http.ContentDisposition;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public class o0 extends w implements g8.u0 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o0(g8.k kVar, g8.u0 u0Var, h8.h hVar, f9.f fVar, int i10, g8.v0 v0Var) {
        super(i10, fVar, kVar, u0Var, v0Var, hVar);
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
        if (i10 == 0) {
            v(3);
            throw null;
        }
        if (v0Var != null) {
        } else {
            v(4);
            throw null;
        }
    }

    public static o0 R0(b bVar, f9.f fVar, int i10, g8.v0 v0Var) {
        if (bVar == null) {
            v(5);
            throw null;
        }
        if (fVar == null) {
            v(7);
            throw null;
        }
        if (i10 == 0) {
            v(8);
            throw null;
        }
        if (v0Var != null) {
            return new o0(bVar, null, h8.g.f15863a, fVar, i10, v0Var);
        }
        v(9);
        throw null;
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 13 || i10 == 18 || i10 == 23 || i10 == 24 || i10 == 29 || i10 == 30) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 13 || i10 == 18 || i10 == 23 || i10 == 24 || i10 == 29 || i10 == 30) ? 2 : 3];
        switch (i10) {
            case 1:
            case 6:
            case 27:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
            case 8:
            case 26:
                objArr[0] = "kind";
                break;
            case 4:
            case 9:
            case 28:
                objArr[0] = "source";
                break;
            case 5:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 10:
            case 15:
            case 20:
                objArr[0] = "typeParameters";
                break;
            case 11:
            case 16:
            case 21:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 12:
            case 17:
            case 22:
                objArr[0] = "visibility";
                break;
            case 13:
            case 18:
            case 23:
            case 24:
            case 29:
            case 30:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
                break;
            case 14:
            case 19:
                objArr[0] = "contextReceiverParameters";
                break;
            case 25:
                objArr[0] = "newOwner";
                break;
        }
        if (i10 == 13 || i10 == 18 || i10 == 23) {
            objArr[1] = "initialize";
        } else if (i10 == 24) {
            objArr[1] = "getOriginal";
        } else if (i10 == 29) {
            objArr[1] = "copy";
        } else if (i10 != 30) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
        } else {
            objArr[1] = "newCopyBuilder";
        }
        switch (i10) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                objArr[2] = "create";
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
                objArr[2] = "initialize";
                break;
            case 13:
            case 18:
            case 23:
            case 24:
            case 29:
            case 30:
                break;
            case 25:
            case 26:
            case 27:
            case 28:
                objArr[2] = "createSubstitutedCopy";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 13 && i10 != 18 && i10 != 23 && i10 != 24 && i10 != 29 && i10 != 30) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // j8.w, g8.w
    public w.a C0() {
        return M0(y0.f22262b);
    }

    @Override // j8.w
    public w I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, g8.v0 v0Var, h8.h hVar) {
        if (kVar == null) {
            v(25);
            throw null;
        }
        if (i10 == 0) {
            v(26);
            throw null;
        }
        if (hVar == null) {
            v(27);
            throw null;
        }
        g8.u0 u0Var = (g8.u0) wVar;
        if (fVar == null) {
            fVar = getName();
        }
        return new o0(kVar, u0Var, hVar, fVar, i10, v0Var);
    }

    @Override // j8.w, j8.p, j8.o, g8.k
    /* JADX INFO: renamed from: S0, reason: merged with bridge method [inline-methods] */
    public final g8.u0 a() {
        g8.u0 u0Var = (g8.u0) super.a();
        if (u0Var != null) {
            return u0Var;
        }
        v(24);
        throw null;
    }

    @Override // j8.w
    /* JADX INFO: renamed from: T0, reason: merged with bridge method [inline-methods] */
    public final o0 L0(g8.s0 s0Var, g8.s0 s0Var2, List list, List list2, List list3, v9.w wVar, int i10, g8.p pVar) {
        if (list == null) {
            v(14);
            throw null;
        }
        if (list2 == null) {
            v(15);
            throw null;
        }
        if (list3 == null) {
            v(16);
            throw null;
        }
        if (pVar != null) {
            return U0(s0Var, s0Var2, list, list2, list3, wVar, i10, pVar, null);
        }
        v(17);
        throw null;
    }

    public o0 U0(g8.s0 s0Var, g8.s0 s0Var2, List list, List list2, List list3, v9.w wVar, int i10, g8.p pVar, Map map) {
        if (list == null) {
            v(19);
            throw null;
        }
        if (list2 == null) {
            v(20);
            throw null;
        }
        if (list3 == null) {
            v(21);
            throw null;
        }
        if (pVar == null) {
            v(22);
            throw null;
        }
        super.L0(s0Var, s0Var2, list, list2, list3, wVar, i10, pVar);
        if (map != null && !map.isEmpty()) {
            this.N = new LinkedHashMap(map);
        }
        return this;
    }
}
