package r8;

import ba.s;
import g8.a;
import g8.k;
import g8.p;
import g8.s0;
import g8.u0;
import g8.v0;
import i9.q;
import io.ktor.http.ContentDisposition;
import j8.o0;
import j8.v;
import j8.w;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.text.m;
import m2.f0;
import v9.y0;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends o0 implements a {
    public static final e Q = new e();
    public static final f R = new f();
    public int O;
    public final boolean P;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(k kVar, u0 u0Var, h8.h hVar, f9.f fVar, int i10, v0 v0Var, boolean z) {
        super(kVar, u0Var, hVar, fVar, i10, v0Var);
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
        if (v0Var == null) {
            v(4);
            throw null;
        }
        this.O = 0;
        this.P = z;
    }

    public static g V0(k kVar, s8.e eVar, f9.f fVar, v8.a aVar, boolean z) {
        if (kVar == null) {
            v(5);
            throw null;
        }
        if (fVar == null) {
            v(7);
            throw null;
        }
        if (aVar != null) {
            return new g(kVar, null, eVar, fVar, 1, aVar, z);
        }
        v(8);
        throw null;
    }

    public static /* synthetic */ void v(int i10) {
        String str = (i10 == 13 || i10 == 18 || i10 == 21) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 13 || i10 == 18 || i10 == 21) ? 2 : 3];
        switch (i10) {
            case 1:
            case 6:
            case 16:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
            case 15:
                objArr[0] = "kind";
                break;
            case 4:
            case 8:
            case 17:
                objArr[0] = "source";
                break;
            case 5:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 9:
                objArr[0] = "contextReceiverParameters";
                break;
            case 10:
                objArr[0] = "typeParameters";
                break;
            case 11:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 12:
                objArr[0] = "visibility";
                break;
            case 13:
            case 18:
            case 21:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
                break;
            case 14:
                objArr[0] = "newOwner";
                break;
            case 19:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 20:
                objArr[0] = "enhancedReturnType";
                break;
        }
        if (i10 == 13) {
            objArr[1] = "initialize";
        } else if (i10 == 18) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i10 != 21) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i10) {
            case 5:
            case 6:
            case 7:
            case 8:
                objArr[2] = "createJavaMethod";
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "initialize";
                break;
            case 13:
            case 18:
            case 21:
                break;
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 19:
            case 20:
                objArr[2] = "enhance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 13 && i10 != 18 && i10 != 21) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // j8.o0, j8.w
    public final w I0(int i10, f9.f fVar, k kVar, g8.w wVar, v0 v0Var, h8.h hVar) {
        if (kVar == null) {
            v(14);
            throw null;
        }
        if (i10 == 0) {
            v(15);
            throw null;
        }
        if (hVar == null) {
            v(16);
            throw null;
        }
        u0 u0Var = (u0) wVar;
        if (fVar == null) {
            fVar = getName();
        }
        g gVar = new g(kVar, u0Var, hVar, fVar, i10, v0Var, this.P);
        int i11 = this.O;
        boolean z = false;
        if (i11 != 1) {
            if (i11 == 2) {
                z = true;
            } else if (i11 != 3) {
                if (i11 != 4) {
                    throw null;
                }
                z = true;
            }
        }
        gVar.W0(z, g8.b.d(i11));
        return gVar;
    }

    @Override // r8.a
    public final a Q(v9.w wVar, ArrayList arrayList, v9.w wVar2, x xVar) {
        if (wVar2 == null) {
            v(20);
            throw null;
        }
        ArrayList arrayListJ = f0.j(arrayList, e(), this);
        j8.x xVarK = wVar == null ? null : q.k(this, wVar, h8.g.f15863a);
        v vVarM0 = M0(y0.f22262b);
        vVarM0.f19355g = arrayListJ;
        vVarM0.k = wVar2;
        vVarM0.f19357i = xVarK;
        vVarM0.f19363p = true;
        vVarM0.f19362o = true;
        g gVar = (g) vVarM0.x.J0(vVarM0);
        if (xVar != null) {
            gVar.N0((a.InterfaceC0225a) xVar.f22608i, xVar.f22609l);
        }
        if (gVar != null) {
            return gVar;
        }
        v(21);
        throw null;
    }

    @Override // j8.o0
    public final o0 U0(s0 s0Var, s0 s0Var2, List list, List list2, List list3, v9.w wVar, int i10, p pVar, Map map) {
        ba.f fVar;
        if (list == null) {
            v(9);
            throw null;
        }
        if (list2 == null) {
            v(10);
            throw null;
        }
        if (list3 == null) {
            v(11);
            throw null;
        }
        if (pVar == null) {
            v(12);
            throw null;
        }
        super.U0(s0Var, s0Var2, list, list2, list3, wVar, i10, pVar, map);
        for (ba.i iVar : s.f7308b) {
            m mVar = iVar.f7289b;
            f9.f fVar2 = iVar.f7288a;
            if (fVar2 == null || kotlin.jvm.internal.p.a(getName(), fVar2)) {
                if (mVar == null || mVar.e(getName().d())) {
                    Collection collection = iVar.f7290c;
                    if (collection == null || collection.contains(getName())) {
                        ba.e[] eVarArr = iVar.f7292e;
                        int length = eVarArr.length;
                        int i11 = 0;
                        while (true) {
                            if (i11 >= length) {
                                fVar = ((String) iVar.f7291d.invoke(this)) != null ? new ba.f(false) : ba.f.f7276c;
                            } else {
                                if (eVarArr[i11].b(this) != null) {
                                    fVar = new ba.f(false);
                                    break;
                                }
                                i11++;
                            }
                        }
                        this.f19385w = fVar.f7277a;
                        return this;
                    }
                }
            }
        }
        fVar = ba.f.f7275b;
        this.f19385w = fVar.f7277a;
        return this;
    }

    public final void W0(boolean z, boolean z5) {
        this.O = z ? z5 ? 4 : 2 : z5 ? 3 : 1;
    }

    @Override // j8.w, g8.a
    public final boolean f0() {
        return g8.b.d(this.O);
    }
}
