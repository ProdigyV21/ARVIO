package i9;

import g8.a1;
import g8.b0;
import g8.p0;
import g8.v0;
import j8.l0;
import j8.m0;
import j8.n0;
import j8.o0;
import j8.x;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import v9.k0;
import v9.t0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2.a f16264a = new c2.a("ResolutionAnchorProvider", 2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c2.a f16265b = new c2.a("StdlibClassFinder", 2);

    public static /* synthetic */ void a(int i10) {
        String str = (i10 == 12 || i10 == 23 || i10 == 25) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 12 || i10 == 23 || i10 == 25) ? 2 : 3];
        switch (i10) {
            case 1:
            case 4:
            case 8:
            case 14:
            case 16:
            case 18:
            case 31:
            case 33:
            case 35:
                objArr[0] = "annotations";
                break;
            case 2:
            case 5:
            case 9:
                objArr[0] = "parameterAnnotations";
                break;
            case 3:
            case 7:
            case 13:
            case 15:
            case 17:
            default:
                objArr[0] = "propertyDescriptor";
                break;
            case 6:
            case 11:
            case 19:
                objArr[0] = "sourceElement";
                break;
            case 10:
                objArr[0] = "visibility";
                break;
            case 12:
            case 23:
            case 25:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
                break;
            case 20:
                objArr[0] = "containingClass";
                break;
            case 21:
                objArr[0] = "source";
                break;
            case 22:
            case 24:
            case 26:
                objArr[0] = "enumClass";
                break;
            case 27:
            case 28:
            case 29:
                objArr[0] = "descriptor";
                break;
            case 30:
            case 32:
            case 34:
                objArr[0] = "owner";
                break;
        }
        if (i10 == 12) {
            objArr[1] = "createSetter";
        } else if (i10 == 23) {
            objArr[1] = "createEnumValuesMethod";
        } else if (i10 != 25) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
        } else {
            objArr[1] = "createEnumValueOfMethod";
        }
        switch (i10) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "createSetter";
                break;
            case 12:
            case 23:
            case 25:
                break;
            case 13:
            case 14:
                objArr[2] = "createDefaultGetter";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[2] = "createGetter";
                break;
            case 20:
            case 21:
                objArr[2] = "createPrimaryConstructorForObject";
                break;
            case 22:
                objArr[2] = "createEnumValuesMethod";
                break;
            case 24:
                objArr[2] = "createEnumValueOfMethod";
                break;
            case 26:
                objArr[2] = "createEnumEntriesProperty";
                break;
            case 27:
                objArr[2] = "isEnumValuesMethod";
                break;
            case 28:
                objArr[2] = "isEnumValueOfMethod";
                break;
            case 29:
                objArr[2] = "isEnumSpecialMethod";
                break;
            case 30:
            case 31:
                objArr[2] = "createExtensionReceiverParameterForCallable";
                break;
            case 32:
            case 33:
                objArr[2] = "createContextReceiverParameterForCallable";
                break;
            case 34:
            case 35:
                objArr[2] = "createContextReceiverParameterForClass";
                break;
            default:
                objArr[2] = "createDefaultSetter";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i10 != 12 && i10 != 23 && i10 != 25) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static final void c(g8.f fVar, LinkedHashSet linkedHashSet, p9.n nVar, boolean z) {
        for (g8.k kVar : a.a.P(nVar, p9.f.f21214o, 2)) {
            if (kVar instanceof g8.f) {
                g8.f fVarR = (g8.f) kVar;
                if (fVarR.i0()) {
                    g8.h hVarE = nVar.e(fVarR.getName(), o8.e.f20796n);
                    fVarR = hVarE instanceof g8.f ? (g8.f) hVarE : hVarE instanceof a1 ? ((a1) hVarE).r() : null;
                }
                if (fVarR != null) {
                    int i10 = f.f16249a;
                    Iterator it = fVarR.g().d().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (f.p((w) it.next(), fVar.a())) {
                                linkedHashSet.add(fVarR);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (z) {
                        c(fVar, linkedHashSet, fVarR.R(), z);
                    }
                }
            }
        }
    }

    public static x e(g8.a aVar, w wVar, f9.f fVar, h8.h hVar, int i10) {
        if (hVar == null) {
            a(33);
            throw null;
        }
        if (wVar == null) {
            return null;
        }
        q9.a aVar2 = new q9.a(aVar, wVar, fVar, null);
        kotlin.text.m mVar = f9.g.f15370a;
        return new x(aVar, aVar2, hVar, f9.f.j(f9.g.f15371b + '_' + i10));
    }

    public static m0 f(p0 p0Var, h8.h hVar) {
        return l(p0Var, hVar, true, p0Var.f());
    }

    public static n0 g(p0 p0Var, h8.h hVar) {
        v0 v0VarF = p0Var.f();
        if (v0VarF != null) {
            return m(p0Var, hVar, h8.g.f15863a, true, p0Var.getVisibility(), v0VarF);
        }
        a(6);
        throw null;
    }

    public static l0 h(j8.b bVar) {
        if (bVar == null) {
            a(26);
            throw null;
        }
        b0 b0VarD = f.d(bVar);
        r rVar = (r) b0VarD.X(f16265b);
        if (rVar == null) {
            rVar = a.f16243a;
        }
        g8.f fVarA = rVar.a(b0VarD);
        if (fVarA == null) {
            return null;
        }
        g8.p pVar = g8.q.f15528e;
        l0 l0VarI0 = l0.I0(bVar, 1, false, d8.q.f14757b, 4, bVar.f());
        m0 m0Var = new m0(l0VarI0, h8.g.f15863a, 1, pVar, false, false, false, 4, null, bVar.f());
        l0VarI0.K0(m0Var, null, null, null);
        k0.f22220l.getClass();
        v9.b0 b0VarR = v9.e.r(Collections.singletonList(new t0(bVar.m())), k0.f22221m, fVarA.g(), false);
        List list = Collections.EMPTY_LIST;
        l0VarI0.M0(b0VarR, list, null, null, list);
        m0Var.I0(l0VarI0.getReturnType());
        return l0VarI0;
    }

    public static o0 i(j8.b bVar) {
        if (bVar == null) {
            a(24);
            throw null;
        }
        o0 o0VarR0 = o0.R0(bVar, d8.q.f14758c, 4, bVar.f());
        j8.v0 v0Var = new j8.v0(o0VarR0, null, 0, h8.g.f15863a, f9.f.j("value"), m9.d.e(bVar).u(), false, false, false, null, bVar.f());
        List list = Collections.EMPTY_LIST;
        return o0VarR0.L0(null, null, list, list, Collections.singletonList(v0Var), bVar.m(), 1, g8.q.f15528e);
    }

    public static o0 j(j8.b bVar) {
        if (bVar == null) {
            a(22);
            throw null;
        }
        o0 o0VarR0 = o0.R0(bVar, d8.q.f14756a, 4, bVar.f());
        List list = Collections.EMPTY_LIST;
        return o0VarR0.L0(null, null, list, list, list, m9.d.e(bVar).h(bVar.m()), 1, g8.q.f15528e);
    }

    public static x k(g8.a aVar, w wVar, h8.h hVar) {
        if (wVar == null) {
            return null;
        }
        return new x(aVar, new q9.b(aVar, wVar, null), hVar);
    }

    public static m0 l(p0 p0Var, h8.h hVar, boolean z, v0 v0Var) {
        if (hVar == null) {
            a(18);
            throw null;
        }
        if (v0Var != null) {
            return new m0(p0Var, hVar, p0Var.o(), p0Var.getVisibility(), z, false, false, 1, null, v0Var);
        }
        a(19);
        throw null;
    }

    public static n0 m(p0 p0Var, h8.h hVar, h8.h hVar2, boolean z, g8.p pVar, v0 v0Var) {
        if (hVar == null) {
            a(8);
            throw null;
        }
        if (hVar2 == null) {
            a(9);
            throw null;
        }
        if (pVar == null) {
            a(10);
            throw null;
        }
        if (v0Var == null) {
            a(11);
            throw null;
        }
        n0 n0Var = new n0(p0Var, hVar, p0Var.o(), pVar, z, false, false, 1, null, v0Var);
        n0Var.f19327w = n0.H0(n0Var, p0Var.getType(), hVar2);
        return n0Var;
    }

    public static boolean n(g8.w wVar) {
        return wVar.getKind() == 4 && f.n(wVar.d(), 3);
    }

    public static final Collection o(Collection collection, r7.l lVar) {
        if (collection.size() <= 1) {
            return collection;
        }
        LinkedList linkedList = new LinkedList(collection);
        ea.l lVar2 = new ea.l();
        while (!linkedList.isEmpty()) {
            Object objM0 = kotlin.collections.x.m0(linkedList);
            ea.l lVar3 = new ea.l();
            ArrayList arrayListG = n.g(objM0, linkedList, lVar, new ab.h(lVar3, 16));
            if (arrayListG.size() == 1 && lVar3.isEmpty()) {
                lVar2.add(kotlin.collections.x.Q0(arrayListG));
            } else {
                Object objS = n.s(arrayListG, lVar);
                g8.a aVar = (g8.a) lVar.invoke(objS);
                for (Object obj : arrayListG) {
                    if (!n.k(aVar, (g8.a) lVar.invoke(obj))) {
                        lVar3.add(obj);
                    }
                }
                if (!lVar3.isEmpty()) {
                    lVar2.addAll(lVar3);
                }
                lVar2.add(objS);
            }
        }
        return lVar2;
    }

    public abstract void b(g8.c cVar);

    public abstract void d(g8.c cVar, g8.c cVar2);

    public void p(g8.c cVar, Collection collection) {
        cVar.z0(collection);
    }
}
