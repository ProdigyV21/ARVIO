package j8;

import g8.a;
import g8.b1;
import g8.e1;
import g8.w;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class w extends p implements g8.w {
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public Collection I;
    public volatile h J;
    public final g8.w K;
    public final int L;
    public g8.w M;
    public Map N;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public List f19377o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f19378p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public v9.w f19379q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f19380r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g8.s0 f19381s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public g8.s0 f19382t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f19383u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public g8.p f19384v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f19385w;
    public boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f19386y;
    public boolean z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(int i10, f9.f fVar, g8.k kVar, g8.w wVar, g8.v0 v0Var, h8.h hVar) {
        super(kVar, hVar, fVar, v0Var);
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
        this.f19384v = g8.q.f15532i;
        this.f19385w = false;
        this.x = false;
        this.f19386y = false;
        this.z = false;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = true;
        this.H = false;
        this.I = null;
        this.J = null;
        this.M = null;
        this.N = null;
        this.K = wVar == null ? this : wVar;
        this.L = i10;
    }

    public static ArrayList K0(g8.w wVar, List list, y0 y0Var, boolean z, boolean z5, boolean[] zArr) {
        if (list == null) {
            v(30);
            throw null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            e1 e1Var = (e1) it.next();
            v9.w wVarI = y0Var.i(2, e1Var.getType());
            v9.w wVarQ0 = e1Var.q0();
            v9.w wVarI2 = wVarQ0 == null ? null : y0Var.i(2, wVarQ0);
            if (wVarI == null) {
                return null;
            }
            if ((wVarI != e1Var.getType() || wVarQ0 != wVarI2) && zArr != null) {
                zArr[0] = true;
            }
            d8.h hVar = e1Var instanceof u0 ? new d8.h((List) ((u0) e1Var).f19348v.getValue(), 3) : null;
            e1 e1Var2 = z ? null : e1Var;
            int index = e1Var.getIndex();
            h8.h annotations = e1Var.getAnnotations();
            f9.f name = e1Var.getName();
            boolean zW0 = e1Var.w0();
            boolean zM0 = e1Var.m0();
            boolean zL0 = e1Var.l0();
            g8.v0 v0VarF = z5 ? e1Var.f() : g8.v0.f15554b;
            arrayList.add(hVar == null ? new v0(wVar, e1Var2, index, annotations, name, wVarI, zW0, zM0, zL0, wVarI2, v0VarF) : new u0(wVar, e1Var2, index, annotations, name, wVarI, zW0, zM0, zL0, wVarI2, v0VarF, hVar));
        }
        return arrayList;
    }

    public static /* synthetic */ void v(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                i11 = 2;
                break;
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = "source";
                break;
            case 5:
                objArr[0] = "contextReceiverParameters";
                break;
            case 6:
                objArr[0] = "typeParameters";
                break;
            case 7:
            case 28:
            case 30:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 8:
            case 10:
                objArr[0] = "visibility";
                break;
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            case 11:
                objArr[0] = "unsubstitutedReturnType";
                break;
            case 12:
                objArr[0] = "extensionReceiverParameter";
                break;
            case 17:
                objArr[0] = "overriddenDescriptors";
                break;
            case 22:
                objArr[0] = "originalSubstitutor";
                break;
            case 24:
            case 29:
            case 31:
                objArr[0] = "substitutor";
                break;
            case 25:
                objArr[0] = "configuration";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i10) {
            case 9:
                objArr[1] = "initialize";
                break;
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            case 13:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 14:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 15:
                objArr[1] = "getModality";
                break;
            case 16:
                objArr[1] = "getVisibility";
                break;
            case 18:
                objArr[1] = "getTypeParameters";
                break;
            case 19:
                objArr[1] = "getValueParameters";
                break;
            case 20:
                objArr[1] = "getOriginal";
                break;
            case 21:
                objArr[1] = "getKind";
                break;
            case 23:
                objArr[1] = "newCopyBuilder";
                break;
            case 26:
                objArr[1] = "copy";
                break;
            case 27:
                objArr[1] = "getSourceToUseForCopy";
                break;
        }
        switch (i10) {
            case 5:
            case 6:
            case 7:
            case 8:
                objArr[2] = "initialize";
                break;
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                break;
            case 10:
                objArr[2] = "setVisibility";
                break;
            case 11:
                objArr[2] = "setReturnType";
                break;
            case 12:
                objArr[2] = "setExtensionReceiverParameter";
                break;
            case 17:
                objArr[2] = "setOverriddenDescriptors";
                break;
            case 22:
                objArr[2] = "substitute";
                break;
            case 24:
                objArr[2] = "newCopyBuilder";
                break;
            case 25:
                objArr[2] = "doSubstitute";
                break;
            case 28:
            case 29:
            case 30:
            case 31:
                objArr[2] = "getSubstitutedValueParameters";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                throw new IllegalStateException(str2);
            case 10:
            case 11:
            case 12:
            case 17:
            case 22:
            case 24:
            case 25:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public boolean A() {
        return this.A;
    }

    @Override // g8.w
    public final boolean B0() {
        return this.E;
    }

    public w.a C0() {
        return M0(y0.f22262b);
    }

    public final g8.w G0(g8.k kVar, int i10, g8.p pVar) {
        g8.w wVarBuild = C0().p(kVar).k(i10).n(pVar).b(2).f().build();
        if (wVarBuild != null) {
            return wVarBuild;
        }
        v(26);
        throw null;
    }

    @Override // g8.c
    /* JADX INFO: renamed from: H0, reason: merged with bridge method [inline-methods] */
    public g8.u0 v0(g8.k kVar, int i10, g8.p pVar) {
        return (g8.u0) G0(kVar, i10, pVar);
    }

    @Override // g8.a
    public final g8.s0 I() {
        return this.f19382t;
    }

    public abstract w I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, g8.v0 v0Var, h8.h hVar);

    /* JADX WARN: Removed duplicated region for block: B:103:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0211  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public j8.w J0(j8.v r23) {
        /*
            Method dump skipped, instruction units count: 608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.w.J0(j8.v):j8.w");
    }

    public Object L(g8.m mVar, Object obj) {
        return mVar.b(this, obj);
    }

    public void L0(g8.s0 s0Var, g8.s0 s0Var2, List list, List list2, List list3, v9.w wVar, int i10, g8.p pVar) {
        if (list == null) {
            v(5);
            throw null;
        }
        if (list2 == null) {
            v(6);
            throw null;
        }
        if (list3 == null) {
            v(7);
            throw null;
        }
        if (pVar == null) {
            v(8);
            throw null;
        }
        this.f19377o = kotlin.collections.x.c1(list2);
        this.f19378p = kotlin.collections.x.c1(list3);
        this.f19379q = wVar;
        this.f19383u = i10;
        this.f19384v = pVar;
        this.f19381s = s0Var;
        this.f19382t = s0Var2;
        this.f19380r = list;
        for (int i11 = 0; i11 < list2.size(); i11++) {
            b1 b1Var = (b1) list2.get(i11);
            if (b1Var.getIndex() != i11) {
                throw new IllegalStateException(b1Var + " index is " + b1Var.getIndex() + " but position is " + i11);
            }
        }
        for (int i12 = 0; i12 < list3.size(); i12++) {
            e1 e1Var = (e1) list3.get(i12);
            if (e1Var.getIndex() != i12) {
                throw new IllegalStateException(e1Var + "index is " + e1Var.getIndex() + " but position is " + i12);
            }
        }
    }

    @Override // g8.a
    public final g8.s0 M() {
        return this.f19381s;
    }

    public final v M0(y0 y0Var) {
        if (y0Var != null) {
            return new v(this, y0Var.f(), d(), o(), getVisibility(), getKind(), e(), s0(), this.f19381s, getReturnType());
        }
        v(24);
        throw null;
    }

    public final void N0(a.InterfaceC0225a interfaceC0225a, Object obj) {
        if (this.N == null) {
            this.N = new LinkedHashMap();
        }
        this.N.put(interfaceC0225a, obj);
    }

    public void O0(boolean z) {
        this.G = z;
    }

    public void P0(boolean z) {
        this.H = z;
    }

    public final void Q0(v9.b0 b0Var) {
        if (b0Var != null) {
            this.f19379q = b0Var;
        } else {
            v(11);
            throw null;
        }
    }

    @Override // g8.a0
    public final boolean V() {
        return this.C;
    }

    @Override // j8.p, j8.o, g8.k
    public g8.w a() {
        g8.w wVar = this.K;
        g8.w wVarA = wVar == this ? this : wVar.a();
        if (wVarA != null) {
            return wVarA;
        }
        v(20);
        throw null;
    }

    @Override // g8.a
    public Object b0(a.InterfaceC0225a interfaceC0225a) {
        Map map = this.N;
        if (map == null) {
            return null;
        }
        return map.get(interfaceC0225a);
    }

    @Override // g8.a
    public final List e() {
        List list = this.f19378p;
        if (list != null) {
            return list;
        }
        v(19);
        throw null;
    }

    @Override // g8.a
    public boolean f0() {
        return this.H;
    }

    @Override // g8.c
    public final int getKind() {
        int i10 = this.L;
        if (i10 != 0) {
            return i10;
        }
        v(21);
        throw null;
    }

    public v9.w getReturnType() {
        return this.f19379q;
    }

    @Override // g8.a
    public final List getTypeParameters() {
        List list = this.f19377o;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("typeParameters == null for " + this);
    }

    @Override // g8.o, g8.a0
    public final g8.p getVisibility() {
        g8.p pVar = this.f19384v;
        if (pVar != null) {
            return pVar;
        }
        v(16);
        throw null;
    }

    @Override // g8.a0
    public final boolean i0() {
        return this.B;
    }

    public boolean isExternal() {
        return this.f19386y;
    }

    @Override // g8.w
    public final boolean isInfix() {
        if (this.x) {
            return true;
        }
        Iterator it = a().k().iterator();
        while (it.hasNext()) {
            if (((g8.w) it.next()).isInfix()) {
                return true;
            }
        }
        return false;
    }

    public boolean isInline() {
        return this.z;
    }

    @Override // g8.w
    public final boolean isOperator() {
        if (this.f19385w) {
            return true;
        }
        Iterator it = a().k().iterator();
        while (it.hasNext()) {
            if (((g8.w) it.next()).isOperator()) {
                return true;
            }
        }
        return false;
    }

    @Override // g8.w
    public boolean isSuspend() {
        return this.F;
    }

    public Collection k() {
        h hVar = this.J;
        if (hVar != null) {
            this.I = (Collection) hVar.invoke();
            this.J = null;
        }
        Collection collection = this.I;
        if (collection == null) {
            collection = Collections.EMPTY_LIST;
        }
        if (collection != null) {
            return collection;
        }
        v(14);
        throw null;
    }

    @Override // g8.w
    public final g8.w n0() {
        return this.M;
    }

    @Override // g8.a0
    public final int o() {
        int i10 = this.f19383u;
        if (i10 != 0) {
            return i10;
        }
        v(15);
        throw null;
    }

    @Override // g8.a
    public final List s0() {
        List list = this.f19380r;
        if (list != null) {
            return list;
        }
        v(13);
        throw null;
    }

    @Override // g8.w
    public final boolean y0() {
        return this.D;
    }

    public void z0(Collection collection) {
        if (collection == null) {
            v(17);
            throw null;
        }
        this.I = collection;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (((g8.w) it.next()).B0()) {
                this.E = true;
                return;
            }
        }
    }

    @Override // g8.w, g8.x0
    public g8.w b(y0 y0Var) {
        if (y0Var == null) {
            v(22);
            throw null;
        }
        if (y0Var.f22263a.e()) {
            return this;
        }
        v vVarM0 = M0(y0Var);
        vVarM0.f19353e = a();
        vVarM0.f19362o = true;
        vVarM0.f19370w = true;
        return vVarM0.x.J0(vVarM0);
    }
}
