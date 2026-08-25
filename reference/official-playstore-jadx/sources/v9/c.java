package v9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {
    public static /* synthetic */ void a(int i10) {
        String str = i10 != 4 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i10 != 4 ? 3 : 2];
        switch (i10) {
            case 1:
            case 6:
                objArr[0] = "originalSubstitution";
                break;
            case 2:
            case 7:
                objArr[0] = "newContainingDeclaration";
                break;
            case 3:
            case 8:
                objArr[0] = "result";
                break;
            case 4:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
                break;
            case 5:
            default:
                objArr[0] = "typeParameters";
                break;
        }
        if (i10 != 4) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
        } else {
            objArr[1] = "substituteTypeParameters";
        }
        if (i10 != 4) {
            objArr[2] = "substituteTypeParameters";
        }
        String str2 = String.format(str, objArr);
        if (i10 == 4) {
            throw new IllegalStateException(str2);
        }
    }

    public static final b0 b(w wVar) {
        d1 d1VarL0 = wVar.L0();
        b0 b0Var = d1VarL0 instanceof b0 ? (b0) d1VarL0 : null;
        if (b0Var != null) {
            return b0Var;
        }
        throw new IllegalStateException(("This is should be simple type: " + wVar).toString());
    }

    public static final y9.h c(y9.h hVar, HashSet hashSet) {
        y9.h hVarC;
        w9.n nVar = w9.n.f22527a;
        o0 o0VarT = nVar.t(hVar);
        if (!hashSet.add(o0VarT)) {
            return null;
        }
        g8.b1 b1VarS = w9.f.s(o0VarT);
        if (b1VarS != null) {
            if (!(b1VarS instanceof g8.b1)) {
                StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
                sb2.append(b1VarS);
                sb2.append(", ");
                throw new IllegalArgumentException(g8.b.h(kotlin.jvm.internal.l0.f19747a, b1VarS.getClass(), sb2).toString());
            }
            y9.h hVarN0 = xc.d.n0(b1VarS);
            y9.h hVarC2 = c(hVarN0, hashSet);
            if (hVarC2 != null) {
                return ((hVarC2 instanceof y9.i) && w9.f.J((y9.i) hVarC2) && w9.f.I(hVar) && (w9.f.D(nVar.t(hVarN0)) || ((hVarN0 instanceof y9.i) && w9.f.J((y9.i) hVarN0)))) ? nVar.m0(hVarN0) : (!w9.f.I(hVarC2) && (hVar instanceof y9.i) && w9.f.G((y9.i) hVar)) ? nVar.m0(hVarC2) : hVarC2;
            }
            return null;
        }
        if (w9.f.D(o0VarT)) {
            if (!(hVar instanceof w)) {
                StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
                sb3.append(hVar);
                sb3.append(", ");
                throw new IllegalArgumentException(g8.b.h(kotlin.jvm.internal.l0.f19747a, hVar.getClass(), sb3).toString());
            }
            b0 b0VarG = i9.h.g((w) hVar);
            if (b0VarG == null || (hVarC = c(b0VarG, hashSet)) == null) {
                return null;
            }
            if (!w9.f.I(hVar)) {
                return hVarC;
            }
            if (!w9.f.I(hVarC) && (!(hVarC instanceof y9.i) || !w9.f.J((y9.i) hVarC))) {
                return nVar.m0(hVarC);
            }
        }
        return hVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final w d(w wVar) {
        if (wVar instanceof c1) {
            return ((c1) wVar).g0();
        }
        return null;
    }

    public static boolean e(n0 n0Var, y9.i iVar, c cVar) {
        m0 m0Var = m0.f22226c;
        y9.p pVar = n0Var.f22231c;
        if ((pVar.i(iVar) && !pVar.g0(iVar)) || pVar.q(iVar)) {
            return true;
        }
        n0Var.b();
        ArrayDeque arrayDeque = n0Var.f22235g;
        ea.l lVar = n0Var.f22236h;
        arrayDeque.push(iVar);
        while (!arrayDeque.isEmpty()) {
            if (lVar.f15097l > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + iVar + ". Supertypes = " + kotlin.collections.x.u0(lVar, null, null, null, null, 63)).toString());
            }
            y9.i iVar2 = (y9.i) arrayDeque.pop();
            if (lVar.add(iVar2)) {
                c cVar2 = pVar.g0(iVar2) ? m0Var : cVar;
                if (cVar2.equals(m0Var)) {
                    cVar2 = null;
                }
                if (cVar2 == null) {
                    continue;
                } else {
                    Iterator it = pVar.c0(pVar.D(iVar2)).iterator();
                    while (it.hasNext()) {
                        y9.i iVarU = cVar2.u(n0Var, (y9.h) it.next());
                        if ((pVar.i(iVarU) && !pVar.g0(iVarU)) || pVar.q(iVarU)) {
                            n0Var.a();
                            return true;
                        }
                        arrayDeque.add(iVarU);
                    }
                }
            }
        }
        n0Var.a();
        return false;
    }

    public static boolean f(n0 n0Var, y9.i iVar, y9.m mVar) {
        y9.p pVar = n0Var.f22231c;
        if (pVar.k(iVar)) {
            return true;
        }
        if (pVar.g0(iVar)) {
            return false;
        }
        if (n0Var.f22230b) {
            pVar.P(iVar);
        }
        return pVar.i0(pVar.D(iVar), mVar);
    }

    public static final boolean g(w wVar) {
        d1 d1VarL0 = wVar.L0();
        if (d1VarL0 instanceof x9.h) {
            return true;
        }
        return (d1VarL0 instanceof q) && (((q) d1VarL0).P0() instanceof x9.h);
    }

    public static final b0 h(w wVar) {
        d1 d1VarL0 = wVar.L0();
        if (d1VarL0 instanceof q) {
            return ((q) d1VarL0).f22241l;
        }
        if (d1VarL0 instanceof b0) {
            return (b0) d1VarL0;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final d1 i(d1 d1Var, boolean z) {
        m mVarP = e.p(d1Var, z);
        if (mVarP != null) {
            return mVarP;
        }
        b0 b0VarJ = j(d1Var);
        return b0VarJ != null ? b0VarJ : d1Var.M0(false);
    }

    public static final b0 j(d1 d1Var) {
        v vVar;
        o0 o0VarI0 = d1Var.I0();
        v vVar2 = o0VarI0 instanceof v ? (v) o0VarI0 : null;
        if (vVar2 != null) {
            LinkedHashSet<w> linkedHashSet = vVar2.f22254b;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(linkedHashSet, 10));
            boolean z = false;
            for (w wVarI : linkedHashSet) {
                if (b1.e(wVarI)) {
                    wVarI = i(wVarI.L0(), false);
                    z = true;
                }
                arrayList.add(wVarI);
            }
            if (z) {
                w wVarI2 = vVar2.f22253a;
                if (wVarI2 == null) {
                    wVarI2 = null;
                } else if (b1.e(wVarI2)) {
                    wVarI2 = i(wVarI2.L0(), false);
                }
                arrayList.isEmpty();
                LinkedHashSet linkedHashSet2 = new LinkedHashSet(arrayList);
                linkedHashSet2.hashCode();
                vVar = new v(linkedHashSet2);
                vVar.f22253a = wVarI2;
            } else {
                vVar = null;
            }
            if (vVar != null) {
                return vVar.c();
            }
        }
        return null;
    }

    public static final b0 k(b0 b0Var, List list, k0 k0Var) {
        if (list.isEmpty() && k0Var == b0Var.H0()) {
            return b0Var;
        }
        if (list.isEmpty()) {
            return b0Var.O0(k0Var);
        }
        if (!(b0Var instanceof x9.h)) {
            return e.r(list, k0Var, b0Var.I0(), b0Var.J0());
        }
        x9.h hVar = (x9.h) b0Var;
        o0 o0Var = hVar.f22682l;
        x9.g gVar = hVar.f22683m;
        x9.j jVar = hVar.f22684n;
        boolean z = hVar.f22686p;
        String[] strArr = hVar.f22687q;
        return new x9.h(o0Var, gVar, jVar, list, z, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static w l(w wVar, List list, h8.h hVar, int i10) {
        if ((i10 & 2) != 0) {
            hVar = wVar.getAnnotations();
        }
        if ((list.isEmpty() || list == wVar.G0()) && hVar == wVar.getAnnotations()) {
            return wVar;
        }
        k0 k0VarH0 = wVar.H0();
        if ((hVar instanceof h8.l) && ((h8.l) hVar).isEmpty()) {
            hVar = h8.g.f15863a;
        }
        k0 k0VarN = n(k0VarH0, hVar);
        d1 d1VarL0 = wVar.L0();
        if (d1VarL0 instanceof q) {
            q qVar = (q) d1VarL0;
            return e.j(k(qVar.f22241l, list, k0VarN), k(qVar.f22242m, list, k0VarN));
        }
        if (d1VarL0 instanceof b0) {
            return k((b0) d1VarL0, list, k0VarN);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ b0 m(b0 b0Var, List list, k0 k0Var, int i10) {
        if ((i10 & 1) != 0) {
            list = b0Var.G0();
        }
        if ((i10 & 2) != 0) {
            k0Var = b0Var.H0();
        }
        return k(b0Var, list, k0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final v9.k0 n(v9.k0 r5, h8.h r6) {
        /*
            androidx.leanback.widget.r r0 = v9.i.f22216b
            kotlin.reflect.m[] r1 = v9.i.f22215a
            r2 = 0
            r3 = r1[r2]
            java.lang.Object r3 = r0.getValue(r5, r3)
            v9.h r3 = (v9.h) r3
            if (r3 == 0) goto L13
            h8.h r3 = r3.f22212a
            if (r3 != 0) goto L15
        L13:
            h8.f r3 = h8.g.f15863a
        L15:
            if (r3 != r6) goto L18
            return r5
        L18:
            r1 = r1[r2]
            java.lang.Object r0 = r0.getValue(r5, r1)
            v9.h r0 = (v9.h) r0
            if (r0 == 0) goto L66
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L29
            goto L57
        L29:
            ba.a r1 = r5.f7274i
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L34:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L4b
            java.lang.Object r3 = r1.next()
            r4 = r3
            v9.h r4 = (v9.h) r4
            boolean r4 = kotlin.jvm.internal.p.a(r4, r0)
            if (r4 != 0) goto L34
            r2.add(r3)
            goto L34
        L4b:
            int r0 = r2.size()
            ba.a r1 = r5.f7274i
            int r1 = r1.a()
            if (r0 != r1) goto L59
        L57:
            r0 = r5
            goto L62
        L59:
            fi.iki.elonen.f r0 = v9.k0.f22220l
            r0.getClass()
            v9.k0 r0 = fi.iki.elonen.f.H(r2)
        L62:
            if (r0 != 0) goto L65
            goto L66
        L65:
            r5 = r0
        L66:
            java.util.Iterator r0 = r6.iterator()
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L77
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L77
            goto L99
        L77:
            v9.h r0 = new v9.h
            r0.<init>(r6)
            fi.iki.elonen.f r6 = v9.k0.f22220l
            java.lang.Class<v9.h> r1 = v9.h.class
            kotlin.jvm.internal.m0 r2 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r2.b(r1)
            r6.getClass()
            java.lang.String r1 = r1.o()
            int r6 = r6.Q(r1)
            ba.a r1 = r5.f7274i
            java.lang.Object r6 = r1.get(r6)
            if (r6 == 0) goto L9a
        L99:
            return r5
        L9a:
            boolean r6 = r5.isEmpty()
            if (r6 == 0) goto Laa
            v9.k0 r5 = new v9.k0
            java.util.List r6 = java.util.Collections.singletonList(r0)
            r5.<init>(r6)
            return r5
        Laa:
            java.util.List r5 = kotlin.collections.x.c1(r5)
            java.util.ArrayList r5 = kotlin.collections.x.H0(r0, r5)
            v9.k0 r5 = fi.iki.elonen.f.H(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.c.n(v9.k0, h8.h):v9.k0");
    }

    public static final w o(g8.b1 b1Var) {
        g8.k kVarD = b1Var.d();
        if (kVarD instanceof g8.i) {
            List parameters = ((g8.i) kVarD).g().getParameters();
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(parameters, 10));
            Iterator it = parameters.iterator();
            while (it.hasNext()) {
                arrayList.add(((g8.b1) it.next()).g());
            }
            List upperBounds = b1Var.getUpperBounds();
            d8.k kVarE = m9.d.e(b1Var);
            w wVarI = new y0(new h0(arrayList, 0)).i(3, (w) kotlin.collections.x.m0(upperBounds));
            return wVarI == null ? kVarE.m() : wVarI;
        }
        if (!(kVarD instanceof g8.w)) {
            throw new IllegalArgumentException("Unsupported descriptor type to build star projection type based on type parameters of it");
        }
        List typeParameters = ((g8.w) kVarD).getTypeParameters();
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(typeParameters, 10));
        Iterator it2 = typeParameters.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((g8.b1) it2.next()).g());
        }
        List upperBounds2 = b1Var.getUpperBounds();
        d8.k kVarE2 = m9.d.e(b1Var);
        w wVarI2 = new y0(new h0(arrayList2, 0)).i(3, (w) kotlin.collections.x.m0(upperBounds2));
        return wVarI2 == null ? kVarE2.m() : wVarI2;
    }

    public static boolean p(y9.p pVar, y9.i iVar, y9.i iVar2) {
        if (pVar.T(iVar) == pVar.T(iVar2) && pVar.g0(iVar) == pVar.g0(iVar2)) {
            if ((pVar.L(iVar) == null) == (pVar.L(iVar2) == null) && pVar.i0(pVar.D(iVar), pVar.D(iVar2))) {
                if (!pVar.w(iVar, iVar2)) {
                    int iT = pVar.T(iVar);
                    for (int i10 = 0; i10 < iT; i10++) {
                        y9.l lVarF = pVar.f(iVar, i10);
                        y9.l lVarF2 = pVar.f(iVar2, i10);
                        if (pVar.e0(lVarF) == pVar.e0(lVarF2) && (pVar.e0(lVarF) || (pVar.A(lVarF) == pVar.A(lVarF2) && q(pVar, pVar.g(lVarF), pVar.g(lVarF2))))) {
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean q(y9.p pVar, y9.h hVar, y9.h hVar2) {
        if (hVar == hVar2) {
            return true;
        }
        b0 b0VarV = pVar.V(hVar);
        b0 b0VarV2 = pVar.V(hVar2);
        if (b0VarV != null && b0VarV2 != null) {
            return p(pVar, b0VarV, b0VarV2);
        }
        q qVarV = pVar.v(hVar);
        q qVarV2 = pVar.v(hVar2);
        return qVarV != null && qVarV2 != null && p(pVar, pVar.l(qVarV), pVar.l(qVarV2)) && p(pVar, pVar.d0(qVarV), pVar.d0(qVarV2));
    }

    public static y0 r(List list, v0 v0Var, g8.k kVar, ArrayList arrayList) {
        if (v0Var == null) {
            a(1);
            throw null;
        }
        if (kVar == null) {
            a(2);
            throw null;
        }
        if (arrayList == null) {
            a(3);
            throw null;
        }
        y0 y0VarS = s(list, v0Var, kVar, arrayList, null);
        if (y0VarS != null) {
            return y0VarS;
        }
        throw new AssertionError("Substitution failed");
    }

    public static y0 s(List list, v0 v0Var, g8.k kVar, List list2, boolean[] zArr) {
        if (v0Var == null) {
            a(6);
            throw null;
        }
        if (kVar == null) {
            a(7);
            throw null;
        }
        if (list2 == null) {
            a(8);
            throw null;
        }
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        Iterator it = list.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            g8.b1 b1Var = (g8.b1) it.next();
            j8.s0 s0VarI0 = j8.s0.I0(kVar, b1Var.getAnnotations(), b1Var.u(), b1Var.y(), b1Var.getName(), i10, b1Var.J());
            map.put(b1Var.g(), new t0(s0VarI0.m()));
            map2.put(b1Var, s0VarI0);
            list2.add(s0VarI0);
            i10++;
        }
        h0 h0Var = new h0(map, 1);
        y0 y0VarE = y0.e(v0Var, h0Var);
        y0 y0VarE2 = y0.e(new j9.d(v0Var, 1), h0Var);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            g8.b1 b1Var2 = (g8.b1) it2.next();
            j8.s0 s0Var = (j8.s0) map2.get(b1Var2);
            for (w wVar : b1Var2.getUpperBounds()) {
                g8.h hVarE = wVar.I0().e();
                w wVarI = (((hVarE instanceof g8.b1) && xc.d.q0((g8.b1) hVarE, null, 6)) ? y0VarE : y0VarE2).i(3, wVar);
                if (wVarI == null) {
                    return null;
                }
                if (wVarI != wVar && zArr != null) {
                    zArr[0] = true;
                }
                if (s0Var.f19341v) {
                    throw new IllegalStateException("Type parameter descriptor is already initialized: " + s0Var.K0());
                }
                if (!g(wVarI)) {
                    s0Var.f19340u.add(wVarI);
                }
            }
            if (s0Var.f19341v) {
                throw new IllegalStateException("Type parameter descriptor is already initialized: " + s0Var.K0());
            }
            s0Var.f19341v = true;
        }
        return y0VarE;
    }

    public static final k0 t(h8.h hVar) {
        if (hVar.isEmpty()) {
            k0.f22220l.getClass();
            return k0.f22221m;
        }
        fi.iki.elonen.f fVar = k0.f22220l;
        List listSingletonList = Collections.singletonList(new h(hVar));
        fVar.getClass();
        return fi.iki.elonen.f.H(listSingletonList);
    }

    public static final b0 v(w wVar) {
        d1 d1VarL0 = wVar.L0();
        if (d1VarL0 instanceof q) {
            return ((q) d1VarL0).f22242m;
        }
        if (d1VarL0 instanceof b0) {
            return (b0) d1VarL0;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final b0 w(b0 b0Var, b0 b0Var2) {
        return g(b0Var) ? b0Var : new a(b0Var, b0Var2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final d1 x(d1 d1Var, w wVar) {
        if (d1Var instanceof c1) {
            return x(((c1) d1Var).D0(), wVar);
        }
        if (wVar == null || wVar.equals(d1Var)) {
            return d1Var;
        }
        if (d1Var instanceof b0) {
            return new e0((b0) d1Var, wVar);
        }
        if (d1Var instanceof q) {
            return new s((q) d1Var, wVar);
        }
        throw new NoWhenBranchMatchedException();
    }

    public abstract y9.i u(n0 n0Var, y9.h hVar);
}
