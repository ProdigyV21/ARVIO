package j8;

import g8.e1;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g8.k f19300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19301b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g8.p f19302c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f19304e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final g8.s0 f19307h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f9.f f19308i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final v9.w f19309j;
    public final /* synthetic */ l0 k;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g8.p0 f19303d = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v9.v0 f19305f = v9.v0.f22256a;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f19306g = true;

    public k0(l0 l0Var) {
        this.k = l0Var;
        this.f19300a = l0Var.d();
        this.f19301b = l0Var.o();
        this.f19302c = l0Var.getVisibility();
        this.f19304e = l0Var.getKind();
        this.f19307h = l0Var.D;
        this.f19308i = l0Var.getName();
        this.f19309j = l0Var.getType();
    }

    public static /* synthetic */ void a(int i10) {
        String str = (i10 == 1 || i10 == 2 || i10 == 3 || i10 == 5 || i10 == 7 || i10 == 9 || i10 == 11 || i10 == 19 || i10 == 13 || i10 == 14 || i10 == 16 || i10 == 17) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 1 || i10 == 2 || i10 == 3 || i10 == 5 || i10 == 7 || i10 == 9 || i10 == 11 || i10 == 19 || i10 == 13 || i10 == 14 || i10 == 16 || i10 == 17) ? 2 : 3];
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 14:
            case 16:
            case 17:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                break;
            case 4:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 6:
                objArr[0] = "modality";
                break;
            case 8:
                objArr[0] = "visibility";
                break;
            case 10:
                objArr[0] = "kind";
                break;
            case 12:
                objArr[0] = "typeParameters";
                break;
            case 15:
                objArr[0] = "substitution";
                break;
            case 18:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            default:
                objArr[0] = "owner";
                break;
        }
        if (i10 == 1) {
            objArr[1] = "setOwner";
        } else if (i10 == 2) {
            objArr[1] = "setOriginal";
        } else if (i10 == 3) {
            objArr[1] = "setPreserveSourceElement";
        } else if (i10 == 5) {
            objArr[1] = "setReturnType";
        } else if (i10 == 7) {
            objArr[1] = "setModality";
        } else if (i10 == 9) {
            objArr[1] = "setVisibility";
        } else if (i10 == 11) {
            objArr[1] = "setKind";
        } else if (i10 == 19) {
            objArr[1] = "setName";
        } else if (i10 == 13) {
            objArr[1] = "setTypeParameters";
        } else if (i10 == 14) {
            objArr[1] = "setDispatchReceiverParameter";
        } else if (i10 == 16) {
            objArr[1] = "setSubstitution";
        } else if (i10 != 17) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
        } else {
            objArr[1] = "setCopyOverrides";
        }
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 14:
            case 16:
            case 17:
            case 19:
                break;
            case 4:
                objArr[2] = "setReturnType";
                break;
            case 6:
                objArr[2] = "setModality";
                break;
            case 8:
                objArr[2] = "setVisibility";
                break;
            case 10:
                objArr[2] = "setKind";
                break;
            case 12:
                objArr[2] = "setTypeParameters";
                break;
            case 15:
                objArr[2] = "setSubstitution";
                break;
            case 18:
                objArr[2] = "setName";
                break;
            default:
                objArr[2] = "setOwner";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 1 && i10 != 2 && i10 != 3 && i10 != 5 && i10 != 7 && i10 != 9 && i10 != 11 && i10 != 19 && i10 != 13 && i10 != 14 && i10 != 16 && i10 != 17) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5, types: [g8.w, j8.j0, j8.n0] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [g8.q0] */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4, types: [g8.r0] */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v14, types: [j8.j0, j8.m0] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r4v24, types: [g8.t] */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34, types: [g8.w] */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36, types: [v9.w] */
    /* JADX WARN: Type inference failed for: r4v43 */
    /* JADX WARN: Type inference failed for: r4v44 */
    /* JADX WARN: Type inference failed for: r4v45 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13, types: [g8.w] */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v5, types: [g8.t] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r9v0, types: [g8.a, g8.k, g8.p0, j8.l0, j8.x0] */
    public final l0 b() {
        d dVar;
        ?? m0Var;
        ?? n0Var;
        y0 y0Var;
        r7.a aVar;
        x xVar;
        x xVar2;
        v9.w wVarI;
        g8.k kVar = this.f19300a;
        int i10 = this.f19301b;
        g8.p pVar = this.f19302c;
        g8.p0 p0Var = this.f19303d;
        int i11 = this.f19304e;
        f9.f fVar = this.f19308i;
        l0 l0Var = this.k;
        ?? J0 = l0Var.J0(kVar, i10, pVar, p0Var, i11, fVar);
        List typeParameters = l0Var.getTypeParameters();
        ArrayList arrayList = new ArrayList(((ArrayList) typeParameters).size());
        y0 y0VarR = v9.c.r(typeParameters, this.f19305f, J0, arrayList);
        v9.w wVar = this.f19309j;
        v9.w wVarI2 = y0VarR.i(3, wVar);
        x xVar3 = null;
        if (wVarI2 != null) {
            int i12 = 2;
            v9.w wVarI3 = y0VarR.i(2, wVar);
            if (wVarI3 != null) {
                J0.L0(wVarI3);
            }
            g8.s0 s0Var = this.f19307h;
            if (s0Var != null) {
                d dVarB = s0Var.b(y0VarR);
                dVar = dVarB != null ? dVarB : null;
            }
            g8.s0 s0Var2 = l0Var.E;
            x xVar4 = (s0Var2 == null || (wVarI = y0VarR.i(2, s0Var2.getType())) == null) ? null : new x(J0, new q9.b(J0, wVarI, s0Var2.getValue()), s0Var2.getAnnotations());
            ArrayList arrayList2 = new ArrayList();
            for (g8.s0 s0Var3 : l0Var.C) {
                v9.w wVarI4 = y0VarR.i(i12, s0Var3.getType());
                if (wVarI4 == null) {
                    xVar = xVar3;
                    xVar2 = xVar;
                } else {
                    xVar2 = xVar3;
                    xVar = new x(J0, new q9.a(J0, wVarI4, ((q9.d) s0Var3.getValue()).a(), s0Var3.getValue()), s0Var3.getAnnotations());
                }
                if (xVar != null) {
                    arrayList2.add(xVar);
                }
                xVar3 = xVar2;
                i12 = 2;
            }
            ?? r19 = xVar3;
            J0.M0(wVarI2, arrayList, dVar, xVar4, arrayList2);
            m0 m0Var2 = l0Var.G;
            g8.y0 y0Var2 = g8.v0.f15554b;
            if (m0Var2 == null) {
                m0Var = r19;
            } else {
                h8.h annotations = m0Var2.getAnnotations();
                int i13 = this.f19301b;
                g8.p visibility = l0Var.G.getVisibility();
                if (this.f19304e == 2 && g8.q.e(g8.q.g(visibility.f15522a.c()))) {
                    visibility = g8.q.f15531h;
                }
                g8.p pVar2 = visibility;
                m0 m0Var3 = l0Var.G;
                boolean z = m0Var3.f19292o;
                boolean z5 = m0Var3.f19293p;
                boolean z10 = m0Var3.f19296s;
                int i14 = this.f19304e;
                g8.p0 p0Var2 = this.f19303d;
                m0Var = new m0(J0, annotations, i13, pVar2, z, z5, z10, i14, p0Var2 == null ? r19 : p0Var2.getGetter(), y0Var2);
            }
            if (m0Var != 0) {
                m0 m0Var4 = l0Var.G;
                v9.w wVar2 = m0Var4.f19324w;
                m0Var.f19299v = m0Var4.n0() != null ? m0Var4.n0().b(y0VarR) : r19;
                m0Var.I0(wVar2 != null ? y0VarR.i(3, wVar2) : r19);
            }
            g8.r0 r0Var = l0Var.H;
            if (r0Var == null) {
                n0Var = r19;
            } else {
                h8.h annotations2 = r0Var.getAnnotations();
                int i15 = this.f19301b;
                g8.p visibility2 = l0Var.H.getVisibility();
                if (this.f19304e == 2 && g8.q.e(g8.q.g(visibility2.f15522a.c()))) {
                    visibility2 = g8.q.f15531h;
                }
                g8.p pVar3 = visibility2;
                boolean zE = l0Var.H.E();
                boolean zIsExternal = l0Var.H.isExternal();
                boolean zIsInline = l0Var.H.isInline();
                int i16 = this.f19304e;
                g8.p0 p0Var3 = this.f19303d;
                n0Var = new n0(J0, annotations2, i15, pVar3, zE, zIsExternal, zIsInline, i16, p0Var3 == null ? r19 : p0Var3.getSetter(), y0Var2);
            }
            if (n0Var != 0) {
                y0Var = y0VarR;
                List listK0 = w.K0(n0Var, l0Var.H.e(), y0Var, false, false, null);
                if (listK0 == null) {
                    listK0 = Collections.singletonList(n0.H0(n0Var, m9.d.e(this.f19300a).n(), ((e1) l0Var.H.e().get(0)).getAnnotations()));
                }
                if (listK0.size() != 1) {
                    throw new IllegalStateException();
                }
                g8.r0 r0Var2 = l0Var.H;
                if (r0Var2 == null) {
                    l0.v(31);
                    throw r19;
                }
                n0Var.f19299v = r0Var2.n0() != null ? r0Var2.n0().b(y0Var) : r19;
                e1 e1Var = (e1) listK0.get(0);
                if (e1Var == null) {
                    n0.v(6);
                    throw r19;
                }
                n0Var.f19327w = e1Var;
            } else {
                y0Var = y0VarR;
            }
            g8.t tVar = l0Var.I;
            ?? uVar = tVar == null ? r19 : new u(tVar.getAnnotations());
            g8.t tVar2 = l0Var.J;
            J0.K0(m0Var, n0Var, uVar, tVar2 == null ? r19 : new u(tVar2.getAnnotations()));
            if (this.f19306g) {
                ea.l lVar = new ea.l();
                Iterator it = l0Var.k().iterator();
                while (it.hasNext()) {
                    lVar.add(((g8.p0) it.next()).b(y0Var));
                }
                J0.f19314u = lVar;
            }
            if (l0Var.isConst() && (aVar = l0Var.f19393r) != null) {
                J0.G0(l0Var.f19392q, aVar);
            }
            return J0;
        }
        return null;
    }
}
