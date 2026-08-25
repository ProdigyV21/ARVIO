package v9;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f22202a = new e();

    public static final boolean b(y9.p pVar, y9.i iVar) {
        if (pVar.H(iVar)) {
            return true;
        }
        if (!(iVar instanceof y9.c)) {
            return false;
        }
        r0 r0VarX = pVar.x(pVar.y((y9.c) iVar));
        return !pVar.e0(r0VarX) && pVar.H(pVar.Q(pVar.g(r0VarX)));
    }

    public static final boolean c(y9.p pVar, n0 n0Var, y9.i iVar, y9.i iVar2, boolean z) {
        Collection<y9.h> collectionJ = pVar.J(iVar);
        if ((collectionJ instanceof Collection) && collectionJ.isEmpty()) {
            return false;
        }
        for (y9.h hVar : collectionJ) {
            if (kotlin.jvm.internal.p.a(pVar.t(hVar), pVar.D(iVar2))) {
                return true;
            }
            if (z && n(f22202a, n0Var, iVar2, hVar)) {
                return true;
            }
        }
        return false;
    }

    public static List d(n0 n0Var, y9.i iVar, y9.m mVar) {
        c cVarD;
        m0 m0Var = m0.f22226c;
        y9.p pVar = n0Var.f22231c;
        pVar.getClass();
        if (pVar.a(mVar) || !pVar.i(iVar)) {
            if (!pVar.G(mVar)) {
                ea.i iVar2 = new ea.i();
                n0Var.b();
                ArrayDeque arrayDeque = n0Var.f22235g;
                ea.l lVar = n0Var.f22236h;
                arrayDeque.push(iVar);
                while (!arrayDeque.isEmpty()) {
                    if (lVar.f15097l > 1000) {
                        throw new IllegalStateException(("Too many supertypes for type: " + iVar + ". Supertypes = " + kotlin.collections.x.u0(lVar, null, null, null, null, 63)).toString());
                    }
                    y9.i iVar3 = (y9.i) arrayDeque.pop();
                    if (lVar.add(iVar3)) {
                        y9.i iVarB = pVar.B(iVar3);
                        if (iVarB == null) {
                            iVarB = iVar3;
                        }
                        if (pVar.i0(pVar.D(iVarB), mVar)) {
                            iVar2.add(iVarB);
                            cVarD = m0Var;
                        } else {
                            cVarD = pVar.T(iVarB) == 0 ? m0.f22225b : pVar.d(iVarB);
                        }
                        if (cVarD.equals(m0Var)) {
                            cVarD = null;
                        }
                        if (cVarD != null) {
                            Iterator it = pVar.c0(pVar.D(iVar3)).iterator();
                            while (it.hasNext()) {
                                arrayDeque.add(cVarD.u(n0Var, (y9.h) it.next()));
                            }
                        }
                    }
                }
                n0Var.a();
                return iVar2;
            }
            if (pVar.i0(pVar.D(iVar), mVar)) {
                b0 b0VarB = pVar.B(iVar);
                if (b0VarB != null) {
                    iVar = b0VarB;
                }
                return Collections.singletonList(iVar);
            }
        }
        return kotlin.collections.z.f19728i;
    }

    public static List e(n0 n0Var, y9.i iVar, y9.m mVar) {
        int i10;
        List listD = d(n0Var, iVar, mVar);
        y9.p pVar = n0Var.f22231c;
        if (listD.size() >= 2) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : listD) {
                y9.k kVarX = pVar.X((y9.i) obj);
                int iE = pVar.e(kVarX);
                while (true) {
                    if (i10 >= iE) {
                        arrayList.add(obj);
                        break;
                    }
                    i10 = pVar.v(pVar.g(pVar.S(kVarX, i10))) == null ? i10 + 1 : 0;
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return listD;
    }

    public static boolean g(n0 n0Var, y9.h hVar, y9.h hVar2) {
        w9.e eVar = n0Var.f22233e;
        w9.d dVar = n0Var.f22232d;
        y9.p pVar = n0Var.f22231c;
        if (hVar == hVar2) {
            return true;
        }
        if (l(pVar, hVar) && l(pVar, hVar2)) {
            eVar.getClass();
            d1 d1VarA = dVar.a((w) hVar);
            eVar.getClass();
            d1 d1VarA2 = dVar.a((w) hVar2);
            b0 b0VarM = pVar.M(d1VarA);
            if (!pVar.i0(pVar.t(d1VarA), pVar.t(d1VarA2))) {
                return false;
            }
            if (pVar.T(b0VarM) == 0) {
                return pVar.N(d1VarA) || pVar.N(d1VarA2) || pVar.g0(b0VarM) == pVar.g0(pVar.M(d1VarA2));
            }
        }
        e eVar2 = f22202a;
        return n(eVar2, n0Var, hVar, hVar2) && n(eVar2, n0Var, hVar2, hVar);
    }

    public static final d1 j(b0 b0Var, b0 b0Var2) {
        return b0Var.equals(b0Var2) ? b0Var : new r(b0Var, b0Var2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:
    
        return r6.h(r6.t(r7), r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static y9.n k(y9.p r6, y9.h r7, y9.h r8) {
        /*
            int r0 = r6.T(r7)
            r1 = 0
            r2 = r1
        L6:
            r3 = 0
            if (r2 >= r0) goto L66
            y9.l r4 = r6.f(r7, r2)
            boolean r5 = r6.e0(r4)
            if (r5 != 0) goto L14
            r3 = r4
        L14:
            if (r3 == 0) goto L63
            v9.d1 r3 = r6.g(r3)
            if (r3 != 0) goto L1d
            goto L63
        L1d:
            v9.b0 r4 = r6.M(r3)
            y9.i r4 = r6.a0(r4)
            boolean r4 = r6.m(r4)
            if (r4 == 0) goto L3b
            v9.b0 r4 = r6.M(r8)
            y9.i r4 = r6.a0(r4)
            boolean r4 = r6.m(r4)
            if (r4 == 0) goto L3b
            r4 = 1
            goto L3c
        L3b:
            r4 = r1
        L3c:
            boolean r5 = r3.equals(r8)
            if (r5 != 0) goto L5a
            if (r4 == 0) goto L53
            v9.o0 r4 = r6.t(r3)
            v9.o0 r5 = r6.t(r8)
            boolean r4 = kotlin.jvm.internal.p.a(r4, r5)
            if (r4 == 0) goto L53
            goto L5a
        L53:
            y9.n r3 = k(r6, r3, r8)
            if (r3 == 0) goto L63
            return r3
        L5a:
            v9.o0 r7 = r6.t(r7)
            y9.n r6 = r6.h(r7, r2)
            return r6
        L63:
            int r2 = r2 + 1
            goto L6
        L66:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.e.k(y9.p, y9.h, y9.h):y9.n");
    }

    public static boolean l(y9.p pVar, y9.h hVar) {
        if (!pVar.b0(pVar.t(hVar))) {
            return false;
        }
        pVar.n(hVar);
        return (pVar.q(hVar) || pVar.k0(hVar) || !kotlin.jvm.internal.p.a(pVar.D(pVar.M(hVar)), pVar.D(pVar.Q(hVar)))) ? false : true;
    }

    public static boolean m(n0 n0Var, y9.k kVar, y9.i iVar) {
        boolean zN;
        y9.p pVar = n0Var.f22231c;
        o0 o0VarD = pVar.D(iVar);
        int iE = pVar.e(kVar);
        int iU = pVar.U(o0VarD);
        if (iE == iU && iE == pVar.T(iVar)) {
            for (int i10 = 0; i10 < iU; i10++) {
                y9.l lVarF = pVar.f(iVar, i10);
                if (!pVar.e0(lVarF)) {
                    d1 d1VarG = pVar.g(lVarF);
                    y9.l lVarS = pVar.S(kVar, i10);
                    pVar.A(lVarS);
                    d1 d1VarG2 = pVar.g(lVarS);
                    int iF0 = pVar.f0(pVar.h(o0VarD, i10));
                    int iA = pVar.A(lVarF);
                    if (iF0 == 3) {
                        iF0 = iA;
                    } else if (iA != 3 && iF0 != iA) {
                        iF0 = 0;
                    }
                    if (iF0 == 0) {
                        return n0Var.f22229a;
                    }
                    if (iF0 != 3 || (!o(pVar, d1VarG2, d1VarG, o0VarD) && !o(pVar, d1VarG, d1VarG2, o0VarD))) {
                        int i11 = n0Var.f22234f;
                        if (i11 > 100) {
                            throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + d1VarG2).toString());
                        }
                        n0Var.f22234f = i11 + 1;
                        int iC = h.f0.c(iF0);
                        e eVar = f22202a;
                        if (iC == 0) {
                            zN = n(eVar, n0Var, d1VarG, d1VarG2);
                        } else if (iC == 1) {
                            zN = n(eVar, n0Var, d1VarG2, d1VarG);
                        } else {
                            if (iC != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            zN = g(n0Var, d1VarG2, d1VarG);
                        }
                        n0Var.f22234f--;
                        if (!zN) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02ae A[EDGE_INSN: B:352:0x02ae->B:169:0x02ae BREAK  A[LOOP:11: B:159:0x028b->B:354:0x028b]] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fc  */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object, y9.p] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v4, types: [int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean n(v9.e r25, v9.n0 r26, y9.h r27, y9.h r28) {
        /*
            Method dump skipped, instruction units count: 1408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.e.n(v9.e, v9.n0, y9.h, y9.h):boolean");
    }

    public static boolean o(y9.p pVar, y9.h hVar, y9.h hVar2, y9.m mVar) {
        g8.b1 b1VarI;
        h8.a aVarV = pVar.V(hVar);
        if (!(aVarV instanceof y9.c)) {
            return false;
        }
        y9.c cVar = (y9.c) aVarV;
        if (pVar.c(cVar) || !pVar.e0(pVar.x(pVar.y(cVar))) || pVar.u(cVar) != 1) {
            return false;
        }
        o0 o0VarT = pVar.t(hVar2);
        y9.s sVar = o0VarT instanceof y9.s ? (y9.s) o0VarT : null;
        return (sVar == null || (b1VarI = pVar.I(sVar)) == null || !pVar.W(b1VarI, mVar)) ? false : true;
    }

    public static m p(d1 d1Var, boolean z) {
        boolean zE;
        if (d1Var instanceof m) {
            return (m) d1Var;
        }
        if ((d1Var.I0() instanceof w9.m) || (d1Var.I0().e() instanceof g8.b1) || (d1Var instanceof w9.g)) {
            g8.h hVarE = d1Var.I0().e();
            j8.s0 s0Var = hVarE instanceof j8.s0 ? (j8.s0) hVarE : null;
            zE = true;
            if (s0Var == null || s0Var.f19341v) {
                zE = (z && (d1Var.I0().e() instanceof g8.b1)) ? b1.e(d1Var) : true ^ c.e(w9.f.l(24, false), c.h(d1Var), m0.f22225b);
            }
        } else {
            zE = false;
        }
        if (!zE) {
            return null;
        }
        if (d1Var instanceof q) {
            q qVar = (q) d1Var;
            kotlin.jvm.internal.p.a(qVar.f22241l.I0(), qVar.f22242m.I0());
        }
        return new m(c.h(d1Var).M0(false), z);
    }

    public static final b0 q(k0 k0Var, g8.f fVar, List list) {
        return r(list, k0Var, fVar.g(), false);
    }

    public static b0 r(List list, k0 k0Var, o0 o0Var, boolean z) {
        p9.n nVarW;
        j8.b0 b0Var;
        p9.n nVarV;
        p9.n nVarG0;
        if (k0Var.isEmpty() && list.isEmpty() && !z && o0Var.e() != null) {
            return o0Var.e().m();
        }
        g8.h hVarE = o0Var.e();
        if (hVarE instanceof g8.b1) {
            nVarW = ((g8.b1) hVarE).m().l();
        } else if (hVarE instanceof g8.f) {
            int i10 = m9.d.f20302a;
            m9.d.h(i9.f.d(hVarE));
            boolean zIsEmpty = list.isEmpty();
            w9.e eVar = w9.e.f22508a;
            if (zIsEmpty) {
                g8.f fVar = (g8.f) hVarE;
                b0Var = fVar instanceof j8.b0 ? (j8.b0) fVar : null;
                nVarW = (b0Var == null || (nVarG0 = b0Var.g0(eVar)) == null) ? fVar.U() : nVarG0;
            } else {
                g8.f fVar2 = (g8.f) hVarE;
                v0 v0VarF = p0.f22240b.f(o0Var, list);
                b0Var = fVar2 instanceof j8.b0 ? (j8.b0) fVar2 : null;
                nVarW = (b0Var == null || (nVarV = b0Var.v(v0VarF, eVar)) == null) ? fVar2.x(v0VarF) : nVarV;
            }
        } else if (hVarE instanceof g8.a1) {
            nVarW = x9.k.a(4, true, ((g8.a1) hVarE).getName().f15368i);
        } else {
            if (!(o0Var instanceof v)) {
                throw new IllegalStateException("Unsupported classifier: " + hVarE + " for constructor: " + o0Var);
            }
            nVarW = ac.b.w("member scope for intersection type", ((v) o0Var).f22254b);
        }
        return t(k0Var, o0Var, list, z, nVarW, new androidx.navigation.p(list, k0Var, o0Var, z));
    }

    public static final b0 s(List list, p9.n nVar, k0 k0Var, o0 o0Var, boolean z) {
        c0 c0Var = new c0(o0Var, list, z, nVar, new androidx.navigation.compose.h(list, nVar, k0Var, o0Var, z));
        return k0Var.isEmpty() ? c0Var : new d0(c0Var, k0Var);
    }

    public static final b0 t(k0 k0Var, o0 o0Var, List list, boolean z, p9.n nVar, r7.l lVar) {
        c0 c0Var = new c0(o0Var, list, z, nVar, lVar);
        return k0Var.isEmpty() ? c0Var : new d0(c0Var, k0Var);
    }

    public void a(h8.h hVar, h8.h hVar2) {
        HashSet hashSet = new HashSet();
        Iterator<h8.b> it = hVar.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().c());
        }
        Iterator<h8.b> it2 = hVar2.iterator();
        while (it2.hasNext()) {
            hashSet.contains(it2.next().c());
        }
    }

    public v0 f(o0 o0Var, List list) {
        List parameters = o0Var.getParameters();
        g8.b1 b1Var = (g8.b1) kotlin.collections.x.y0(parameters);
        if (b1Var == null || !b1Var.O()) {
            return new t((g8.b1[]) parameters.toArray(new g8.b1[0]), (r0[]) list.toArray(new r0[0]), false);
        }
        List parameters2 = o0Var.getParameters();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(parameters2, 10));
        Iterator it = parameters2.iterator();
        while (it.hasNext()) {
            arrayList.add(((g8.b1) it.next()).g());
        }
        return new h0(kotlin.collections.h0.A0(kotlin.collections.x.i1(arrayList, list)), 1);
    }

    public b0 h(androidx.emoji2.text.e0 e0Var, k0 k0Var, boolean z, int i10, boolean z5) {
        h8.h hVar;
        k0 k0VarH;
        char c10;
        g8.a1 a1Var = (g8.a1) e0Var.f2601m;
        r0 r0VarI = i(new t0(1, a1Var.o0()), e0Var, null, i10);
        b0 b0VarB = c.b(r0VarI.getType());
        if (c.g(b0VarB)) {
            return b0VarB;
        }
        r0VarI.c();
        h8.h annotations = b0VarB.getAnnotations();
        char c11 = 0;
        h hVar2 = (h) i.f22216b.getValue(k0Var, i.f22215a[0]);
        if (hVar2 == null || (hVar = hVar2.f22212a) == null) {
            hVar = h8.g.f15863a;
        }
        a(annotations, hVar);
        if (!c.g(b0VarB)) {
            if (c.g(b0VarB)) {
                k0VarH = b0VarB.H0();
            } else {
                k0 k0VarH0 = b0VarB.H0();
                fi.iki.elonen.f fVar = k0.f22220l;
                if (k0Var.isEmpty() && k0VarH0.isEmpty()) {
                    k0VarH = k0Var;
                } else {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = ((ConcurrentHashMap) fVar.f15416i).values().iterator();
                    while (it.hasNext()) {
                        int iIntValue = ((Number) it.next()).intValue();
                        h hVar3 = (h) k0Var.f7274i.get(iIntValue);
                        h hVar4 = (h) k0VarH0.f7274i.get(iIntValue);
                        if (hVar3 != null) {
                            c10 = c11;
                            if (hVar4 != null) {
                                h8.h iVar = hVar3.f22212a;
                                h8.h hVar5 = hVar4.f22212a;
                                if (iVar.isEmpty()) {
                                    iVar = hVar5;
                                } else if (!hVar5.isEmpty()) {
                                    h8.h[] hVarArr = new h8.h[2];
                                    hVarArr[c10] = iVar;
                                    hVarArr[1] = hVar5;
                                    iVar = new h8.i(hVarArr);
                                }
                                hVar3 = new h(iVar);
                            }
                            hVar4 = hVar3;
                        } else if (hVar4 == null) {
                            c10 = c11;
                            hVar4 = null;
                        } else if (hVar3 == null) {
                            c10 = c11;
                        } else {
                            h8.h iVar2 = hVar4.f22212a;
                            h8.h hVar6 = hVar3.f22212a;
                            if (iVar2.isEmpty()) {
                                c10 = c11;
                                iVar2 = hVar6;
                            } else if (hVar6.isEmpty()) {
                                c10 = c11;
                            } else {
                                c10 = c11;
                                h8.h[] hVarArr2 = new h8.h[2];
                                hVarArr2[c10] = iVar2;
                                hVarArr2[1] = hVar6;
                                iVar2 = new h8.i(hVarArr2);
                            }
                            hVar4 = new h(iVar2);
                        }
                        ea.o.d(arrayList, hVar4);
                        c11 = c10;
                    }
                    k0VarH = fi.iki.elonen.f.H(arrayList);
                }
            }
            b0VarB = c.m(b0VarB, null, k0VarH, 1);
        }
        b0 b0VarI = b1.i(b0VarB, z);
        return z5 ? c.w(b0VarI, s((List) e0Var.f2602n, p9.m.f21235b, k0Var, a1Var.g(), z)) : b0VarI;
    }

    public r0 i(r0 r0Var, androidx.emoji2.text.e0 e0Var, g8.b1 b1Var, int i10) {
        int iY;
        g8.a1 a1Var = (g8.a1) e0Var.f2601m;
        if (i10 > 100) {
            throw new AssertionError("Too deep recursion while expanding type alias " + a1Var.getName());
        }
        if (r0Var.b()) {
            return b1.j(b1Var);
        }
        w type = r0Var.getType();
        g8.h hVarE = type.I0().e();
        r0 r0Var2 = hVarE instanceof g8.b1 ? (r0) ((Map) e0Var.f2603o).get(hVarE) : null;
        if (r0Var2 == null) {
            b0 b0VarB = c.b(r0Var.getType().L0());
            if (!c.g(b0VarB) && b1.c(b0VarB, z9.a.f23273m, null)) {
                o0 o0VarI0 = b0VarB.I0();
                g8.h hVarE2 = o0VarI0.e();
                o0VarI0.getParameters().size();
                b0VarB.G0().size();
                if (!(hVarE2 instanceof g8.b1)) {
                    if (!(hVarE2 instanceof g8.a1)) {
                        int i11 = 0;
                        b0 b0VarU = u(b0VarB, e0Var, i10);
                        y0.d(b0VarU);
                        for (Object obj : b0VarU.G0()) {
                            int i12 = i11 + 1;
                            if (i11 < 0) {
                                t7.a.Q();
                                throw null;
                            }
                            r0 r0Var3 = (r0) obj;
                            if (!r0Var3.b() && !b1.c(r0Var3.getType(), z9.a.f23272l, null)) {
                            }
                            i11 = i12;
                        }
                        return new t0(r0Var.c(), b0VarU);
                    }
                    g8.a1 a1Var2 = (g8.a1) hVarE2;
                    if (e0Var.w(a1Var2)) {
                        return new t0(1, x9.k.b(x9.j.f22695p, a1Var2.getName().f15368i));
                    }
                    List listG0 = b0VarB.G0();
                    int i13 = 0;
                    ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listG0, 10));
                    for (Object obj2 : listG0) {
                        int i14 = i13 + 1;
                        if (i13 < 0) {
                            t7.a.Q();
                            throw null;
                        }
                        arrayList.add(i((r0) obj2, e0Var, (g8.b1) o0VarI0.getParameters().get(i13), i10 + 1));
                        i13 = i14;
                    }
                    List parameters = a1Var2.g().getParameters();
                    ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(parameters, 10));
                    Iterator it = parameters.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(((g8.b1) it.next()).a());
                    }
                    return new t0(r0Var.c(), c.w(h(new androidx.emoji2.text.e0(e0Var, a1Var2, arrayList, kotlin.collections.h0.A0(kotlin.collections.x.i1(arrayList2, arrayList)), 16), b0VarB.H0(), b0VarB.J0(), i10 + 1, false), u(b0VarB, e0Var, i10)));
                }
            }
            return r0Var;
        }
        if (r0Var2.b()) {
            return b1.j(b1Var);
        }
        d1 d1VarL0 = r0Var2.getType().L0();
        int iC = r0Var2.c();
        int iC2 = r0Var.c();
        if (iC2 != iC && iC2 != 1 && iC == 1) {
            iC = iC2;
        }
        if (b1Var == null || (iY = b1Var.y()) == 0) {
            iY = 1;
        }
        if (iY != iC && iY != 1 && iC == 1) {
            iC = 1;
        }
        a(type.getAnnotations(), d1VarL0.getAnnotations());
        b0 b0VarI = b1.i(c.b(d1VarL0), type.J0());
        k0 k0VarH0 = type.H0();
        if (!c.g(b0VarI)) {
            if (c.g(b0VarI)) {
                k0VarH0 = b0VarI.H0();
            } else {
                k0 k0VarH02 = b0VarI.H0();
                fi.iki.elonen.f fVar = k0.f22220l;
                if (!k0VarH0.isEmpty() || !k0VarH02.isEmpty()) {
                    ArrayList arrayList3 = new ArrayList();
                    Iterator it2 = ((ConcurrentHashMap) fVar.f15416i).values().iterator();
                    while (it2.hasNext()) {
                        int iIntValue = ((Number) it2.next()).intValue();
                        h hVar = (h) k0VarH0.f7274i.get(iIntValue);
                        h hVar2 = (h) k0VarH02.f7274i.get(iIntValue);
                        if (hVar != null) {
                            if (hVar2 != null) {
                                h8.h iVar = hVar.f22212a;
                                h8.h hVar3 = hVar2.f22212a;
                                if (iVar.isEmpty()) {
                                    iVar = hVar3;
                                } else if (!hVar3.isEmpty()) {
                                    iVar = new h8.i(new h8.h[]{iVar, hVar3});
                                }
                                hVar = new h(iVar);
                            }
                            hVar2 = hVar;
                        } else if (hVar2 == null) {
                            hVar2 = null;
                        } else if (hVar != null) {
                            h8.h iVar2 = hVar2.f22212a;
                            h8.h hVar4 = hVar.f22212a;
                            if (iVar2.isEmpty()) {
                                iVar2 = hVar4;
                            } else if (!hVar4.isEmpty()) {
                                iVar2 = new h8.i(new h8.h[]{iVar2, hVar4});
                            }
                            hVar2 = new h(iVar2);
                        }
                        ea.o.d(arrayList3, hVar2);
                    }
                    k0VarH0 = fi.iki.elonen.f.H(arrayList3);
                }
            }
            b0VarI = c.m(b0VarI, null, k0VarH0, 1);
        }
        return new t0(iC, b0VarI);
    }

    public b0 u(b0 b0Var, androidx.emoji2.text.e0 e0Var, int i10) {
        o0 o0VarI0 = b0Var.I0();
        List listG0 = b0Var.G0();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listG0, 10));
        int i11 = 0;
        for (Object obj : listG0) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                t7.a.Q();
                throw null;
            }
            r0 r0Var = (r0) obj;
            r0 r0VarI = i(r0Var, e0Var, (g8.b1) o0VarI0.getParameters().get(i11), i10 + 1);
            if (!r0VarI.b()) {
                r0VarI = new t0(r0VarI.c(), b1.h(r0VarI.getType(), r0Var.getType().J0()));
            }
            arrayList.add(r0VarI);
            i11 = i12;
        }
        return c.m(b0Var, arrayList, null, 2);
    }
}
