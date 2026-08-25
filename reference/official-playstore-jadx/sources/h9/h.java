package h9;

import a8.g0;
import androidx.appcompat.app.i1;
import com.google.common.util.concurrent.p0;
import g8.a0;
import g8.a1;
import g8.e1;
import g8.f0;
import g8.g1;
import g8.h0;
import g8.s0;
import g8.y;
import j8.m0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.b0;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.text.u;
import v9.b1;
import v9.d1;
import v9.o0;
import v9.r0;
import v9.v;
import v9.w;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final h f15907c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f15908d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f15909e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f15910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i0 f15911b = new i0(new g0(this, 21));

    static {
        l lVar = new l();
        lVar.l();
        lVar.f15927a = true;
        new h(lVar);
        l lVar2 = new l();
        lVar2.l();
        b0 b0Var = b0.f19686i;
        lVar2.j(b0Var);
        lVar2.f15927a = true;
        new h(lVar2);
        l lVar3 = new l();
        lVar3.l();
        lVar3.j(b0Var);
        lVar3.g();
        lVar3.f15927a = true;
        new h(lVar3);
        l lVar4 = new l();
        lVar4.j(b0Var);
        b bVar = b.f15896c;
        lVar4.c(bVar);
        p pVar = p.f15956l;
        lVar4.i(pVar);
        lVar4.f15927a = true;
        new h(lVar4);
        l lVar5 = new l();
        lVar5.l();
        lVar5.j(b0Var);
        lVar5.c(bVar);
        lVar5.d();
        lVar5.i(p.f15957m);
        lVar5.a();
        lVar5.b();
        lVar5.g();
        lVar5.e();
        lVar5.f15927a = true;
        new h(lVar5);
        l lVar6 = new l();
        lVar6.j(i.f15912l);
        lVar6.f15927a = true;
        f15907c = new h(lVar6);
        l lVar7 = new l();
        lVar7.j(i.f15913m);
        lVar7.f15927a = true;
        new h(lVar7);
        l lVar8 = new l();
        lVar8.c(bVar);
        lVar8.i(pVar);
        lVar8.f15927a = true;
        f15908d = new h(lVar8);
        l lVar9 = new l();
        lVar9.h();
        lVar9.c(b.f15895b);
        lVar9.j(i.f15913m);
        lVar9.f15927a = true;
        f15909e = new h(lVar9);
        l lVar10 = new l();
        lVar10.m();
        lVar10.j(i.f15913m);
        lVar10.f15927a = true;
        new h(lVar10);
    }

    public h(l lVar) {
        this.f15910a = lVar;
    }

    public static void U(StringBuilder sb2) {
        int length = sb2.length();
        if (length == 0 || sb2.charAt(length - 1) != ' ') {
            sb2.append(' ');
        }
    }

    public static boolean g0(w wVar) {
        if (!p0.t(wVar)) {
            return false;
        }
        List listG0 = wVar.G0();
        if (listG0 != null && listG0.isEmpty()) {
            return true;
        }
        Iterator it = listG0.iterator();
        while (it.hasNext()) {
            if (((r0) it.next()).b()) {
                return false;
            }
        }
        return true;
    }

    public static final void n(h hVar, g8.p0 p0Var, StringBuilder sb2) {
        boolean zQ = hVar.q();
        l lVar = hVar.f15910a;
        if (!zQ) {
            k kVar = lVar.f15933g;
            kotlin.reflect.m[] mVarArr = l.X;
            if (!((Boolean) kVar.getValue(lVar, mVarArr[5])).booleanValue()) {
                if (hVar.p().contains(i.ANNOTATIONS)) {
                    hVar.x(sb2, p0Var, null);
                    g8.t tVarR0 = p0Var.r0();
                    if (tVarR0 != null) {
                        hVar.x(sb2, tVarR0, h8.d.FIELD);
                    }
                    g8.t tVarN = p0Var.N();
                    if (tVarN != null) {
                        hVar.x(sb2, tVarN, h8.d.PROPERTY_DELEGATE_FIELD);
                    }
                    if (((q) lVar.H.getValue(lVar, mVarArr[32])) == q.f15960l) {
                        m0 getter = p0Var.getGetter();
                        if (getter != null) {
                            hVar.x(sb2, getter, h8.d.PROPERTY_GETTER);
                        }
                        g8.r0 setter = p0Var.getSetter();
                        if (setter != null) {
                            hVar.x(sb2, setter, h8.d.PROPERTY_SETTER);
                            hVar.x(sb2, (e1) x.R0(setter.e()), h8.d.SETTER_PARAMETER);
                        }
                    }
                }
                hVar.B(sb2, p0Var.s0());
                hVar.e0(p0Var.getVisibility(), sb2);
                hVar.M("const", hVar.p().contains(i.CONST) && p0Var.isConst(), sb2);
                hVar.J(p0Var, sb2);
                hVar.L(p0Var, sb2);
                hVar.R(p0Var, sb2);
                hVar.M("lateinit", hVar.p().contains(i.LATEINIT) && p0Var.t0(), sb2);
                hVar.I(p0Var, sb2);
            }
            hVar.b0(p0Var, sb2, false);
            hVar.a0(sb2, p0Var.getTypeParameters(), true);
            s0 s0VarM = p0Var.M();
            if (s0VarM != null) {
                hVar.x(sb2, s0VarM, h8.d.RECEIVER);
                sb2.append(hVar.F(s0VarM.getType()));
                sb2.append(".");
            }
        }
        hVar.O(p0Var, sb2, true);
        sb2.append(": ");
        sb2.append(hVar.V(p0Var.getType()));
        hVar.T(p0Var, sb2);
        hVar.G(p0Var, sb2);
        hVar.f0(sb2, p0Var.getTypeParameters());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int u(g8.a0 r5) {
        /*
            boolean r0 = r5 instanceof g8.f
            r1 = 4
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L10
            g8.f r5 = (g8.f) r5
            int r5 = r5.p0()
            if (r5 != r2) goto L52
            goto L4f
        L10:
            g8.k r0 = r5.d()
            boolean r4 = r0 instanceof g8.f
            if (r4 == 0) goto L1b
            g8.f r0 = (g8.f) r0
            goto L1c
        L1b:
            r0 = 0
        L1c:
            if (r0 != 0) goto L1f
            goto L52
        L1f:
            boolean r4 = r5 instanceof g8.c
            if (r4 != 0) goto L24
            goto L52
        L24:
            g8.c r5 = (g8.c) r5
            java.util.Collection r4 = r5.k()
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L37
            int r4 = r0.o()
            if (r4 == r3) goto L37
            goto L50
        L37:
            int r0 = r0.p0()
            if (r0 != r2) goto L52
            g8.p r0 = r5.getVisibility()
            g8.p r2 = g8.q.f15524a
            boolean r0 = kotlin.jvm.internal.p.a(r0, r2)
            if (r0 != 0) goto L52
            int r5 = r5.o()
            if (r5 != r1) goto L50
        L4f:
            return r1
        L50:
            r5 = 3
            return r5
        L52:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: h9.h.u(g8.a0):int");
    }

    public final String A(k9.g gVar) {
        l lVar = this.f15910a;
        r7.l lVar2 = (r7.l) lVar.f15947v.getValue(lVar, l.X[20]);
        if (lVar2 != null) {
            return (String) lVar2.invoke(gVar);
        }
        if (gVar instanceof k9.b) {
            Iterable iterable = (Iterable) ((k9.b) gVar).f19530a;
            ArrayList arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                String strA = A((k9.g) it.next());
                if (strA != null) {
                    arrayList.add(strA);
                }
            }
            return x.u0(arrayList, ", ", "{", "}", null, 56);
        }
        if (gVar instanceof k9.a) {
            return kotlin.text.o.r0(w((h8.b) ((k9.a) gVar).f19530a, null), "@");
        }
        if (!(gVar instanceof k9.s)) {
            return gVar.toString();
        }
        k9.r rVar = (k9.r) ((k9.s) gVar).f19530a;
        if (rVar instanceof k9.p) {
            return ((k9.p) rVar).f19537a + "::class";
        }
        if (!(rVar instanceof k9.q)) {
            throw new NoWhenBranchMatchedException();
        }
        k9.f fVar = ((k9.q) rVar).f19538a;
        String strB = fVar.f19528a.b().b();
        int i10 = fVar.f19529b;
        for (int i11 = 0; i11 < i10; i11++) {
            strB = androidx.compose.material3.d.i('>', "kotlin.Array<", strB);
        }
        return androidx.compose.material3.d.m(strB, "::class");
    }

    public final void B(StringBuilder sb2, List list) {
        if (list.isEmpty()) {
            return;
        }
        sb2.append("context(");
        Iterator it = list.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            int i11 = i10 + 1;
            s0 s0Var = (s0) it.next();
            x(sb2, s0Var, h8.d.RECEIVER);
            sb2.append(F(s0Var.getType()));
            if (i10 == t7.a.w(list)) {
                sb2.append(") ");
            } else {
                sb2.append(", ");
            }
            i10 = i11;
        }
    }

    public final void C(StringBuilder sb2, v9.b0 b0Var) {
        x(sb2, b0Var, null);
        if (v9.c.g(b0Var)) {
            boolean z = b0Var instanceof x9.h;
            l lVar = this.f15910a;
            if (z && ((x9.h) b0Var).f22684n.f22705l && ((Boolean) lVar.U.getValue(lVar, l.X[46])).booleanValue()) {
                x9.k kVar = x9.k.f22706a;
                if (z) {
                    boolean z5 = ((x9.h) b0Var).f22684n.f22705l;
                }
                sb2.append(D(((x9.i) b0Var.I0()).f22690b[0]));
            } else {
                if (!z || ((Boolean) lVar.W.getValue(lVar, l.X[48])).booleanValue()) {
                    sb2.append(b0Var.I0().toString());
                } else {
                    sb2.append(((x9.h) b0Var).f22688r);
                }
                sb2.append(W(b0Var.G0()));
            }
        } else {
            o0 o0VarI0 = b0Var.I0();
            g8.h hVarE = b0Var.I0().e();
            i1 i1VarA = y.a(b0Var, hVarE instanceof g8.i ? (g8.i) hVarE : null, 0);
            if (i1VarA == null) {
                sb2.append(X(o0VarI0));
                sb2.append(W(b0Var.G0()));
            } else {
                S(sb2, i1VarA);
            }
        }
        if (b0Var.J0()) {
            sb2.append("?");
        }
        if (b0Var instanceof v9.m) {
            sb2.append(" & Any");
        }
    }

    public final String D(String str) {
        int iOrdinal = r().ordinal();
        if (iOrdinal == 0) {
            return str;
        }
        if (iOrdinal == 1) {
            return a0.c.l("<font color=red><b>", str, "</b></font>");
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String E(String str, String str2, d8.k kVar) {
        if (p0.E(str, str2)) {
            return u.P(str2, "(", false) ? a0.c.l("(", str, ")!") : str.concat("!");
        }
        l lVar = this.f15910a;
        k kVar2 = lVar.f15928b;
        kotlin.reflect.m[] mVarArr = l.X;
        String strH0 = kotlin.text.o.H0(((c) kVar2.getValue(lVar, mVarArr[0])).a(kVar.i(d8.p.B), this), "Collection");
        String strB = p0.B(str, strH0.concat("Mutable"), str2, strH0, strH0.concat("(Mutable)"));
        if (strB != null) {
            return strB;
        }
        String strB2 = p0.B(str, strH0.concat("MutableMap.MutableEntry"), str2, strH0.concat("Map.Entry"), strH0.concat("(Mutable)Map.(Mutable)Entry"));
        if (strB2 != null) {
            return strB2;
        }
        String strH02 = kotlin.text.o.H0(((c) lVar.f15928b.getValue(lVar, mVarArr[0])).a(kVar.j("Array"), this), "Array");
        String strB3 = p0.B(str, strH02.concat(o("Array<")), str2, strH02.concat(o("Array<out ")), strH02.concat(o("Array<(out) ")));
        if (strB3 != null) {
            return strB3;
        }
        return "(" + str + ".." + str2 + ')';
    }

    public final String F(w wVar) throws IOException {
        String strV = V(wVar);
        return ((!g0(wVar) || b1.e(wVar)) && !(wVar instanceof v9.m)) ? strV : androidx.compose.material3.d.i(')', "(", strV);
    }

    public final void G(g1 g1Var, StringBuilder sb2) {
        k9.g gVarK0;
        String strA;
        l lVar = this.f15910a;
        if (!((Boolean) lVar.f15946u.getValue(lVar, l.X[19])).booleanValue() || (gVarK0 = g1Var.k0()) == null || (strA = A(gVarK0)) == null) {
            return;
        }
        sb2.append(" = ");
        sb2.append(o(strA));
    }

    public final String H(String str) {
        int iOrdinal = r().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            l lVar = this.f15910a;
            if (!((Boolean) lVar.V.getValue(lVar, l.X[47])).booleanValue()) {
                return a0.c.l("<b>", str, "</b>");
            }
        }
        return str;
    }

    public final void I(g8.c cVar, StringBuilder sb2) {
        String str;
        if (p().contains(i.MEMBER_KIND) && t() && cVar.getKind() != 1) {
            sb2.append("/*");
            int kind = cVar.getKind();
            if (kind == 1) {
                str = "DECLARATION";
            } else if (kind == 2) {
                str = "FAKE_OVERRIDE";
            } else if (kind == 3) {
                str = "DELEGATION";
            } else {
                if (kind != 4) {
                    throw null;
                }
                str = "SYNTHESIZED";
            }
            sb2.append(kotlin.reflect.b0.J(str));
            sb2.append("*/ ");
        }
    }

    public final void J(a0 a0Var, StringBuilder sb2) {
        M("external", a0Var.isExternal(), sb2);
        boolean z = false;
        M("expect", p().contains(i.EXPECT) && a0Var.i0(), sb2);
        if (p().contains(i.ACTUAL) && a0Var.V()) {
            z = true;
        }
        M("actual", z, sb2);
    }

    public final void K(StringBuilder sb2, int i10, int i11) {
        String str;
        l lVar = this.f15910a;
        if (((Boolean) lVar.f15941p.getValue(lVar, l.X[14])).booleanValue() || i10 != i11) {
            boolean zContains = p().contains(i.MODALITY);
            if (i10 == 1) {
                str = "FINAL";
            } else if (i10 == 2) {
                str = "SEALED";
            } else if (i10 == 3) {
                str = "OPEN";
            } else {
                if (i10 != 4) {
                    throw null;
                }
                str = "ABSTRACT";
            }
            M(kotlin.reflect.b0.J(str), zContains, sb2);
        }
    }

    public final void L(g8.c cVar, StringBuilder sb2) {
        if (i9.f.s(cVar) && cVar.o() == 1) {
            return;
        }
        l lVar = this.f15910a;
        if (((o) lVar.B.getValue(lVar, l.X[26])) == o.f15952i && cVar.o() == 3 && !cVar.k().isEmpty()) {
            return;
        }
        K(sb2, cVar.o(), u(cVar));
    }

    public final void M(String str, boolean z, StringBuilder sb2) {
        if (z) {
            sb2.append(H(str));
            sb2.append(" ");
        }
    }

    public final String N(f9.f fVar, boolean z) {
        String strO = o(p0.z(fVar));
        l lVar = this.f15910a;
        return (((Boolean) lVar.V.getValue(lVar, l.X[47])).booleanValue() && r() == t.f15963l && z) ? a0.c.l("<b>", strO, "</b>") : strO;
    }

    public final void O(g8.k kVar, StringBuilder sb2, boolean z) {
        sb2.append(N(kVar.getName(), z));
    }

    public final void P(StringBuilder sb2, w wVar) throws IOException {
        d1 d1VarL0 = wVar.L0();
        v9.a aVar = d1VarL0 instanceof v9.a ? (v9.a) d1VarL0 : null;
        if (aVar == null) {
            Q(sb2, wVar);
            return;
        }
        v9.b0 b0Var = aVar.f22185l;
        l lVar = this.f15910a;
        k kVar = lVar.R;
        kotlin.reflect.m[] mVarArr = l.X;
        if (((Boolean) kVar.getValue(lVar, mVarArr[42])).booleanValue()) {
            Q(sb2, b0Var);
            return;
        }
        Q(sb2, aVar.f22186m);
        if (((Boolean) lVar.Q.getValue(lVar, mVarArr[41])).booleanValue()) {
            t tVarR = r();
            r rVar = t.f15963l;
            if (tVarR == rVar) {
                sb2.append("<font color=\"808080\"><i>");
            }
            sb2.append(" /* = ");
            Q(sb2, b0Var);
            sb2.append(" */");
            if (r() == rVar) {
                sb2.append("</i></font>");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x0188  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Q(java.lang.StringBuilder r25, v9.w r26) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 634
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: h9.h.Q(java.lang.StringBuilder, v9.w):void");
    }

    public final void R(g8.c cVar, StringBuilder sb2) {
        if (p().contains(i.OVERRIDE) && !cVar.k().isEmpty()) {
            l lVar = this.f15910a;
            if (((o) lVar.B.getValue(lVar, l.X[26])) != o.f15953l) {
                M("override", true, sb2);
                if (t()) {
                    sb2.append("/*");
                    sb2.append(cVar.k().size());
                    sb2.append("*/ ");
                }
            }
        }
    }

    public final void S(StringBuilder sb2, i1 i1Var) {
        i1 i1Var2 = (i1) i1Var.f1061n;
        g8.i iVar = (g8.i) i1Var.f1059l;
        if (i1Var2 != null) {
            S(sb2, i1Var2);
            sb2.append('.');
            sb2.append(N(iVar.getName(), false));
        } else {
            sb2.append(X(iVar.g()));
        }
        sb2.append(W((List) i1Var.f1060m));
    }

    public final void T(g8.c cVar, StringBuilder sb2) {
        s0 s0VarM;
        l lVar = this.f15910a;
        if (((Boolean) lVar.F.getValue(lVar, l.X[30])).booleanValue() && (s0VarM = cVar.M()) != null) {
            sb2.append(" on ");
            sb2.append(V(s0VarM.getType()));
        }
    }

    public final String V(w wVar) throws IOException {
        StringBuilder sb2 = new StringBuilder();
        l lVar = this.f15910a;
        P(sb2, (w) ((r7.l) lVar.f15949y.getValue(lVar, l.X[23])).invoke(wVar));
        return sb2.toString();
    }

    public final String W(List list) throws IOException {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(o("<"));
        x.t0(list, sb2, ", ", null, null, new f(this, 0), 60);
        sb2.append(o(">"));
        return sb2.toString();
    }

    public final String X(o0 o0Var) {
        g8.h hVarE = o0Var.e();
        if (hVarE instanceof g8.b1 ? true : hVarE instanceof g8.f ? true : hVarE instanceof a1) {
            if (x9.k.e(hVarE)) {
                return hVarE.g().toString();
            }
            l lVar = this.f15910a;
            return ((c) lVar.f15928b.getValue(lVar, l.X[0])).a(hVarE, this);
        }
        if (hVarE == null) {
            return o0Var instanceof v ? ((v) o0Var).g(g.f15903m) : o0Var.toString();
        }
        throw new IllegalStateException(("Unexpected classifier: " + hVarE.getClass()).toString());
    }

    public final void Y(g8.b1 b1Var, StringBuilder sb2, boolean z) {
        String str;
        if (z) {
            sb2.append(o("<"));
        }
        if (t()) {
            sb2.append("/*");
            sb2.append(b1Var.getIndex());
            sb2.append("*/ ");
        }
        M("reified", b1Var.u(), sb2);
        int iY = b1Var.y();
        if (iY == 1) {
            str = "";
        } else if (iY == 2) {
            str = "in";
        } else {
            if (iY != 3) {
                throw null;
            }
            str = "out";
        }
        boolean z5 = true;
        M(str, str.length() > 0, sb2);
        x(sb2, b1Var, null);
        O(b1Var, sb2, z);
        int size = b1Var.getUpperBounds().size();
        if ((size > 1 && !z) || size == 1) {
            w wVar = (w) b1Var.getUpperBounds().iterator().next();
            if (wVar == null) {
                d8.k.a(142);
                throw null;
            }
            if (!d8.k.x(wVar) || !wVar.J0()) {
                sb2.append(" : ");
                sb2.append(V(wVar));
            }
        } else if (z) {
            for (w wVar2 : b1Var.getUpperBounds()) {
                if (wVar2 == null) {
                    d8.k.a(142);
                    throw null;
                }
                if (!d8.k.x(wVar2) || !wVar2.J0()) {
                    if (z5) {
                        sb2.append(" : ");
                    } else {
                        sb2.append(" & ");
                    }
                    sb2.append(V(wVar2));
                    z5 = false;
                }
            }
        }
        if (z) {
            sb2.append(o(">"));
        }
    }

    public final void Z(StringBuilder sb2, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Y((g8.b1) it.next(), sb2, false);
            if (it.hasNext()) {
                sb2.append(", ");
            }
        }
    }

    @Override // h9.j
    public final void a() {
        this.f15910a.a();
    }

    public final void a0(StringBuilder sb2, List list, boolean z) {
        l lVar = this.f15910a;
        if (((Boolean) lVar.f15948w.getValue(lVar, l.X[21])).booleanValue() || list.isEmpty()) {
            return;
        }
        sb2.append(o("<"));
        Z(sb2, list);
        sb2.append(o(">"));
        if (z) {
            sb2.append(" ");
        }
    }

    @Override // h9.j
    public final void b() {
        this.f15910a.b();
    }

    public final void b0(g1 g1Var, StringBuilder sb2, boolean z) {
        if (z || !(g1Var instanceof e1)) {
            sb2.append(H(g1Var.K() ? "var" : "val"));
            sb2.append(" ");
        }
    }

    @Override // h9.j
    public final void c(c cVar) {
        this.f15910a.c(cVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c0(g8.e1 r11, boolean r12, java.lang.StringBuilder r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: h9.h.c0(g8.e1, boolean, java.lang.StringBuilder, boolean):void");
    }

    @Override // h9.j
    public final void d() {
        this.f15910a.d();
    }

    public final void d0(StringBuilder sb2, List list, boolean z) {
        l lVar = this.f15910a;
        int iOrdinal = ((p) lVar.E.getValue(lVar, l.X[29])).ordinal();
        boolean z5 = true;
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal != 2) {
                    throw new NoWhenBranchMatchedException();
                }
            } else if (z) {
            }
            z5 = false;
        }
        int size = list.size();
        s().a(sb2);
        Iterator it = list.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            e1 e1Var = (e1) it.next();
            s().getClass();
            c0(e1Var, z5, sb2, false);
            s().b(sb2, i10, size);
            i10++;
        }
        s().c(sb2);
    }

    @Override // h9.j
    public final void e() {
        this.f15910a.e();
    }

    public final boolean e0(g8.p pVar, StringBuilder sb2) {
        if (!p().contains(i.VISIBILITY)) {
            return false;
        }
        l lVar = this.f15910a;
        k kVar = lVar.f15939n;
        kotlin.reflect.m[] mVarArr = l.X;
        if (((Boolean) kVar.getValue(lVar, mVarArr[12])).booleanValue()) {
            pVar = g8.q.g(pVar.f15522a.c());
        }
        if (!((Boolean) lVar.f15940o.getValue(lVar, mVarArr[13])).booleanValue() && kotlin.jvm.internal.p.a(pVar, g8.q.f15534l)) {
            return false;
        }
        sb2.append(H(pVar.f15522a.b()));
        sb2.append(" ");
        return true;
    }

    @Override // h9.j
    public final Set f() {
        return this.f15910a.f();
    }

    public final void f0(StringBuilder sb2, List list) {
        l lVar = this.f15910a;
        if (((Boolean) lVar.f15948w.getValue(lVar, l.X[21])).booleanValue()) {
            return;
        }
        ArrayList arrayList = new ArrayList(0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            g8.b1 b1Var = (g8.b1) it.next();
            Iterator it2 = x.h0(b1Var.getUpperBounds(), 1).iterator();
            while (it2.hasNext()) {
                arrayList.add(N(b1Var.getName(), false) + " : " + V((w) it2.next()));
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        sb2.append(" ");
        sb2.append(H("where"));
        sb2.append(" ");
        x.t0(arrayList, sb2, ", ", null, null, null, 124);
    }

    @Override // h9.j
    public final void g() {
        this.f15910a.g();
    }

    @Override // h9.j
    public final void h() {
        this.f15910a.h();
    }

    @Override // h9.j
    public final void i(p pVar) {
        this.f15910a.i(pVar);
    }

    @Override // h9.j
    public final void j(Set set) {
        this.f15910a.j(set);
    }

    @Override // h9.j
    public final void k(LinkedHashSet linkedHashSet) {
        this.f15910a.k(linkedHashSet);
    }

    @Override // h9.j
    public final void l() {
        this.f15910a.l();
    }

    @Override // h9.j
    public final void m() {
        this.f15910a.m();
    }

    public final String o(String str) {
        return r().a(str);
    }

    public final Set p() {
        l lVar = this.f15910a;
        return (Set) lVar.f15931e.getValue(lVar, l.X[3]);
    }

    public final boolean q() {
        l lVar = this.f15910a;
        return ((Boolean) lVar.f15932f.getValue(lVar, l.X[4])).booleanValue();
    }

    public final t r() {
        l lVar = this.f15910a;
        return (t) lVar.D.getValue(lVar, l.X[28]);
    }

    public final e s() {
        l lVar = this.f15910a;
        return (e) lVar.C.getValue(lVar, l.X[27]);
    }

    public final boolean t() {
        l lVar = this.f15910a;
        return ((Boolean) lVar.f15936j.getValue(lVar, l.X[8])).booleanValue();
    }

    public final String v(g8.k kVar) {
        g8.k kVarD;
        StringBuilder sb2 = new StringBuilder();
        kVar.L(new a8.e(this, 21), sb2);
        l lVar = this.f15910a;
        k kVar2 = lVar.f15929c;
        kotlin.reflect.m[] mVarArr = l.X;
        if (((Boolean) kVar2.getValue(lVar, mVarArr[1])).booleanValue() && !(kVar instanceof h0) && !(kVar instanceof g8.m0) && (kVarD = kVar.d()) != null && !(kVarD instanceof g8.b0)) {
            sb2.append(" ");
            int iOrdinal = r().ordinal();
            String strL = "defined in";
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                strL = a0.c.l("<i>", "defined in", "</i>");
            }
            sb2.append(strL);
            sb2.append(" ");
            f9.e eVarG = i9.f.g(kVarD);
            sb2.append(eVarG.f15364a.isEmpty() ? "root package" : o(p0.A(eVarG.e())));
            if (((Boolean) lVar.f15930d.getValue(lVar, mVarArr[2])).booleanValue() && (kVarD instanceof h0) && (kVar instanceof g8.n)) {
                ((g8.n) kVar).f().getClass();
            }
        }
        return sb2.toString();
    }

    public final String w(h8.b bVar, h8.d dVar) throws IOException {
        g8.e eVarC;
        List listE;
        l lVar = this.f15910a;
        k kVar = lVar.N;
        StringBuilder sb2 = new StringBuilder();
        sb2.append('@');
        if (dVar != null) {
            sb2.append(dVar.f15857i + ':');
        }
        w type = bVar.getType();
        sb2.append(V(type));
        kotlin.reflect.m[] mVarArr = l.X;
        if (((a) kVar.getValue(lVar, mVarArr[38])).f15893i) {
            Map mapA = bVar.a();
            List list = null;
            g8.f fVarD = ((Boolean) lVar.I.getValue(lVar, mVarArr[33])).booleanValue() ? m9.d.d(bVar) : null;
            if (fVarD != null && (eVarC = fVarD.C()) != null && (listE = eVarC.e()) != null) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : listE) {
                    if (((e1) obj).w0()) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((e1) it.next()).getName());
                }
                list = arrayList2;
            }
            if (list == null) {
                list = z.f19728i;
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : list) {
                if (!mapA.containsKey((f9.f) obj2)) {
                    arrayList3.add(obj2);
                }
            }
            ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add(((f9.f) it2.next()).d() + " = ...");
            }
            Set<Map.Entry> setEntrySet = mapA.entrySet();
            ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(setEntrySet, 10));
            for (Map.Entry entry : setEntrySet) {
                f9.f fVar = (f9.f) entry.getKey();
                k9.g gVar = (k9.g) entry.getValue();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(fVar.d());
                sb3.append(" = ");
                sb3.append(!list.contains(fVar) ? A(gVar) : "...");
                arrayList5.add(sb3.toString());
            }
            List listV0 = x.V0(x.I0(arrayList4, arrayList5));
            if (((a) kVar.getValue(lVar, l.X[38])).f15894l || !listV0.isEmpty()) {
                x.t0(listV0, sb2, ", ", "(", ")", null, 112);
            }
        }
        if (t() && (v9.c.g(type) || (type.I0().e() instanceof f0))) {
            sb2.append(" /* annotation class not found */");
        }
        return sb2.toString();
    }

    public final void x(StringBuilder sb2, h8.a aVar, h8.d dVar) {
        if (p().contains(i.ANNOTATIONS)) {
            boolean z = aVar instanceof w;
            l lVar = this.f15910a;
            Set setF = z ? lVar.f() : (Set) lVar.K.getValue(lVar, l.X[35]);
            r7.l lVar2 = (r7.l) lVar.M.getValue(lVar, l.X[37]);
            for (h8.b bVar : aVar.getAnnotations()) {
                if (!x.f0(setF, bVar.c()) && !kotlin.jvm.internal.p.a(bVar.c(), d8.p.f14749r) && (lVar2 == null || ((Boolean) lVar2.invoke(bVar)).booleanValue())) {
                    sb2.append(w(bVar, dVar));
                    if (((Boolean) lVar.J.getValue(lVar, l.X[34])).booleanValue()) {
                        sb2.append('\n');
                    } else {
                        sb2.append(" ");
                    }
                }
            }
        }
    }

    public final void z(g8.i iVar, StringBuilder sb2) {
        List listN = iVar.n();
        List parameters = iVar.g().getParameters();
        if (t() && iVar.i() && parameters.size() > listN.size()) {
            sb2.append(" /*captured type parameters: ");
            Z(sb2, parameters.subList(listN.size(), parameters.size()));
            sb2.append("*/");
        }
    }
}
