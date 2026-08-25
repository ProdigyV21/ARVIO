package r8;

import d8.u;
import g8.a;
import g8.e1;
import g8.k;
import g8.p;
import g8.p0;
import g8.r0;
import g8.v0;
import i9.q;
import io.ktor.http.ContentDisposition;
import j8.l0;
import j8.m0;
import j8.n0;
import java.util.ArrayList;
import kotlin.collections.z;
import p8.b0;
import v9.b1;
import v9.w;
import w9.n;
import x6.x;
import x8.r;

/* JADX INFO: loaded from: classes5.dex */
public class h extends l0 implements a {
    public final boolean K;
    public final x L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(k kVar, h8.h hVar, int i10, p pVar, boolean z, f9.f fVar, v0 v0Var, p0 p0Var, int i11, boolean z5, x xVar) {
        super(kVar, p0Var, hVar, i10, pVar, z, fVar, i11, v0Var, false, false, false, false, false);
        if (kVar == null) {
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
        if (pVar == null) {
            v(3);
            throw null;
        }
        if (fVar == null) {
            v(4);
            throw null;
        }
        if (v0Var == null) {
            v(5);
            throw null;
        }
        if (i11 == 0) {
            v(6);
            throw null;
        }
        this.K = z5;
        this.L = xVar;
    }

    public static h N0(k kVar, s8.e eVar, p pVar, boolean z, f9.f fVar, v8.a aVar, boolean z5) {
        if (kVar == null) {
            v(7);
            throw null;
        }
        if (fVar == null) {
            v(11);
            throw null;
        }
        if (aVar != null) {
            return new h(kVar, eVar, 1, pVar, z, fVar, aVar, null, 1, z5, null);
        }
        v(12);
        throw null;
    }

    public static /* synthetic */ void v(int i10) {
        String str = i10 != 21 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i10 != 21 ? 3 : 2];
        switch (i10) {
            case 1:
            case 8:
                objArr[0] = "annotations";
                break;
            case 2:
            case 9:
                objArr[0] = "modality";
                break;
            case 3:
            case 10:
                objArr[0] = "visibility";
                break;
            case 4:
            case 11:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 5:
            case 12:
            case 18:
                objArr[0] = "source";
                break;
            case 6:
            case 16:
                objArr[0] = "kind";
                break;
            case 7:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 13:
                objArr[0] = "newOwner";
                break;
            case 14:
                objArr[0] = "newModality";
                break;
            case 15:
                objArr[0] = "newVisibility";
                break;
            case 17:
                objArr[0] = "newName";
                break;
            case 19:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 20:
                objArr[0] = "enhancedReturnType";
                break;
            case 21:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
                break;
            case 22:
                objArr[0] = "inType";
                break;
        }
        if (i10 != 21) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i10) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "create";
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 19:
            case 20:
                objArr[2] = "enhance";
                break;
            case 21:
                break;
            case 22:
                objArr[2] = "setInType";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 == 21) {
            throw new IllegalStateException(str2);
        }
    }

    @Override // j8.l0
    public final l0 J0(k kVar, int i10, p pVar, p0 p0Var, int i11, f9.f fVar) {
        if (kVar == null) {
            v(13);
            throw null;
        }
        if (i10 == 0) {
            v(14);
            throw null;
        }
        if (pVar == null) {
            v(15);
            throw null;
        }
        if (i11 == 0) {
            v(16);
            throw null;
        }
        if (fVar == null) {
            v(17);
            throw null;
        }
        return new h(kVar, getAnnotations(), i10, pVar, this.f19391p, fVar, v0.f15554b, p0Var, i11, this.K, this.L);
    }

    @Override // r8.a
    public final a Q(w wVar, ArrayList arrayList, w wVar2, x xVar) {
        m0 m0Var;
        n0 n0Var;
        if (wVar2 == null) {
            v(20);
            throw null;
        }
        p0 p0VarA = a() == this ? null : a();
        h hVar = new h(d(), getAnnotations(), o(), getVisibility(), this.f19391p, getName(), f(), p0VarA, getKind(), this.K, xVar);
        m0 m0Var2 = this.G;
        if (m0Var2 != null) {
            m0 m0Var3 = new m0(hVar, m0Var2.getAnnotations(), m0Var2.o(), m0Var2.getVisibility(), m0Var2.f19292o, m0Var2.f19293p, m0Var2.f19296s, getKind(), p0VarA == null ? null : p0VarA.getGetter(), m0Var2.f());
            m0Var3.f19299v = m0Var2.f19299v;
            m0Var3.f19324w = wVar2;
            m0Var = m0Var3;
        } else {
            m0Var = null;
        }
        r0 r0Var = this.H;
        if (r0Var != null) {
            n0Var = new n0(hVar, r0Var.getAnnotations(), r0Var.o(), r0Var.getVisibility(), r0Var.E(), r0Var.isExternal(), r0Var.isInline(), getKind(), p0VarA == null ? null : p0VarA.getSetter(), r0Var.f());
            n0Var.f19299v = n0Var.f19299v;
            e1 e1Var = (e1) r0Var.e().get(0);
            if (e1Var == null) {
                n0.v(6);
                throw null;
            }
            n0Var.f19327w = e1Var;
        } else {
            n0Var = null;
        }
        hVar.K0(m0Var, n0Var, this.I, this.J);
        r7.a aVar = this.f19393r;
        if (aVar != null) {
            hVar.G0(this.f19392q, aVar);
        }
        hVar.z0(k());
        hVar.M0(wVar2, getTypeParameters(), this.D, wVar != null ? q.k(this, wVar, h8.g.f15863a) : null, z.f19728i);
        return hVar;
    }

    @Override // j8.l0, g8.a
    public final Object b0(a.InterfaceC0225a interfaceC0225a) {
        x xVar = this.L;
        if (xVar == null || !((a.InterfaceC0225a) xVar.f22608i).equals(interfaceC0225a)) {
            return null;
        }
        return xVar.f22609l;
    }

    @Override // j8.w0, g8.a
    public final boolean f0() {
        return false;
    }

    @Override // j8.l0, g8.g1
    public final boolean isConst() {
        w type = getType();
        if (!this.K) {
            return false;
        }
        if (((!d8.k.G(type) && !u.a(type)) || b1.e(type)) && !d8.k.H(type)) {
            return false;
        }
        h8.i iVar = r.f22672a;
        return !n.f22527a.l0(type, b0.f21102p) || d8.k.H(type);
    }

    @Override // j8.l0
    public final void L0(w wVar) {
    }
}
