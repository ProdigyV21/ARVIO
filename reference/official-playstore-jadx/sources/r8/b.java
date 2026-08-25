package r8;

import g8.a;
import g8.v0;
import i9.q;
import j8.k;
import j8.w;
import java.util.ArrayList;
import kotlin.collections.z;
import m2.f0;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends k implements a {
    public Boolean P;
    public Boolean Q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(g8.f fVar, b bVar, h8.h hVar, boolean z, int i10, v0 v0Var) {
        super(fVar, bVar, hVar, z, i10, v0Var);
        if (fVar == null) {
            v(0);
            throw null;
        }
        if (hVar == null) {
            v(1);
            throw null;
        }
        if (i10 == 0) {
            v(2);
            throw null;
        }
        if (v0Var == null) {
            v(3);
            throw null;
        }
        this.P = null;
        this.Q = null;
    }

    public static b V0(g8.f fVar, h8.h hVar, boolean z, v8.a aVar) {
        if (fVar == null) {
            v(4);
            throw null;
        }
        if (aVar != null) {
            return new b(fVar, null, hVar, z, 1, aVar);
        }
        v(6);
        throw null;
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 11 || i10 == 18) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 11 || i10 == 18) ? 2 : 3];
        switch (i10) {
            case 1:
            case 5:
            case 9:
            case 15:
                objArr[0] = "annotations";
                break;
            case 2:
            case 8:
            case 13:
                objArr[0] = "kind";
                break;
            case 3:
            case 6:
            case 10:
                objArr[0] = "source";
                break;
            case 4:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 7:
            case 12:
                objArr[0] = "newOwner";
                break;
            case 11:
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
                break;
            case 14:
                objArr[0] = "sourceElement";
                break;
            case 16:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 17:
                objArr[0] = "enhancedReturnType";
                break;
        }
        if (i10 == 11) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i10 != 18) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i10) {
            case 4:
            case 5:
            case 6:
                objArr[2] = "createJavaConstructor";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 11:
            case 18:
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[2] = "createDescriptor";
                break;
            case 16:
            case 17:
                objArr[2] = "enhance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 11 && i10 != 18) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // j8.k, j8.w
    public final /* bridge */ /* synthetic */ w I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, v0 v0Var, h8.h hVar) {
        return W0(kVar, wVar, i10, hVar, v0Var);
    }

    @Override // j8.w
    public final void O0(boolean z) {
        this.P = Boolean.valueOf(z);
    }

    @Override // j8.w
    public final void P0(boolean z) {
        this.Q = Boolean.valueOf(z);
    }

    @Override // r8.a
    public final a Q(v9.w wVar, ArrayList arrayList, v9.w wVar2, x xVar) {
        if (wVar2 == null) {
            v(17);
            throw null;
        }
        b bVarW0 = W0(d(), null, getKind(), getAnnotations(), f());
        bVarW0.L0(wVar != null ? q.k(bVarW0, wVar, h8.g.f15863a) : null, this.f19382t, z.f19728i, getTypeParameters(), f0.j(arrayList, e(), bVarW0), wVar2, o(), getVisibility());
        if (xVar != null) {
            bVarW0.N0((a.InterfaceC0225a) xVar.f22608i, xVar.f22609l);
        }
        return bVarW0;
    }

    @Override // j8.k
    /* JADX INFO: renamed from: R0 */
    public final /* bridge */ /* synthetic */ k I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, v0 v0Var, h8.h hVar) {
        return W0(kVar, wVar, i10, hVar, v0Var);
    }

    public final b W0(g8.k kVar, g8.w wVar, int i10, h8.h hVar, v0 v0Var) {
        if (kVar == null) {
            v(7);
            throw null;
        }
        if (i10 == 0) {
            v(8);
            throw null;
        }
        if (hVar == null) {
            v(9);
            throw null;
        }
        if (v0Var == null) {
            v(10);
            throw null;
        }
        if (i10 != 1 && i10 != 4) {
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + kVar + "\nkind: " + g8.b.r(i10));
        }
        g8.f fVar = (g8.f) kVar;
        b bVar = (b) wVar;
        if (i10 == 0) {
            v(13);
            throw null;
        }
        b bVar2 = new b(fVar, bVar, hVar, this.O, i10, v0Var);
        Boolean bool = this.P;
        bool.getClass();
        bVar2.P = bool;
        Boolean bool2 = this.Q;
        bool2.getClass();
        bVar2.Q = bool2;
        return bVar2;
    }

    @Override // j8.w, g8.a
    public final boolean f0() {
        return this.Q.booleanValue();
    }
}
