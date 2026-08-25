package w9;

import androidx.media3.exoplayer.upstream.CmcdData;
import g8.x;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.l0;
import v9.b0;
import v9.b1;
import v9.d1;
import v9.e0;
import v9.k0;
import v9.n0;
import v9.o0;
import v9.p0;
import v9.r0;
import v9.t0;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2.a f22509a = new c2.a("KotlinTypeRefiner", 2);

    public static boolean A(y9.m mVar) {
        if (mVar instanceof o0) {
            g8.h hVarE = ((o0) mVar).e();
            g8.f fVar = hVarE instanceof g8.f ? (g8.f) hVarE : null;
            return (fVar == null || fVar.o() != 1 || fVar.p0() == 3 || fVar.p0() == 4 || fVar.p0() == 5) ? false : true;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static boolean B(y9.m mVar) {
        if (mVar instanceof o0) {
            return ((o0) mVar).f();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static boolean C(y9.h hVar) {
        if (hVar instanceof v9.w) {
            return v9.c.g((v9.w) hVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    public static boolean D(y9.m mVar) {
        if (mVar instanceof o0) {
            g8.h hVarE = ((o0) mVar).e();
            g8.f fVar = hVarE instanceof g8.f ? (g8.f) hVarE : null;
            return (fVar != null ? fVar.S() : null) instanceof x;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static boolean E(y9.m mVar) {
        if (mVar instanceof o0) {
            return mVar instanceof k9.n;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static boolean F(y9.m mVar) {
        if (mVar instanceof o0) {
            return mVar instanceof v9.v;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static boolean G(y9.i iVar) {
        if (iVar instanceof b0) {
            return ((b0) iVar).J0();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static boolean H(y9.m mVar) {
        if (mVar instanceof o0) {
            return d8.k.I((o0) mVar, d8.p.f14734b);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static boolean I(y9.h hVar) {
        if (hVar instanceof v9.w) {
            return b1.e((v9.w) hVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean J(y9.i iVar) {
        if (iVar instanceof v9.w) {
            return d8.k.G((v9.w) iVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static boolean K(y9.c cVar) {
        if (cVar instanceof g) {
            return ((g) cVar).f22515q;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, cVar.getClass(), sb2).toString());
    }

    public static boolean L(y9.l lVar) {
        if (lVar instanceof r0) {
            return ((r0) lVar).b();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(lVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, lVar.getClass(), sb2).toString());
    }

    public static void M(y9.i iVar) {
        if (iVar instanceof b0) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static void N(y9.i iVar) {
        if (iVar instanceof b0) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static b0 O(y9.f fVar) {
        if (fVar instanceof v9.q) {
            return ((v9.q) fVar).f22241l;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(fVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, fVar.getClass(), sb2).toString());
    }

    public static d1 P(y9.c cVar) {
        if (cVar instanceof g) {
            return ((g) cVar).f22512n;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, cVar.getClass(), sb2).toString());
    }

    public static d1 Q(y9.h hVar) {
        if (hVar instanceof d1) {
            return v9.c.i((d1) hVar, false);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    public static b0 R(y9.d dVar) {
        if (dVar instanceof v9.m) {
            return ((v9.m) dVar).f22223l;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, dVar.getClass(), sb2).toString());
    }

    public static int S(y9.m mVar) {
        if (mVar instanceof o0) {
            return ((o0) mVar).getParameters().size();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static Collection T(b bVar, y9.i iVar) {
        o0 o0VarD = bVar.D(iVar);
        if (o0VarD instanceof k9.n) {
            return ((k9.n) o0VarD).f19535a;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static r0 U(y9.b bVar) {
        if (bVar instanceof i) {
            return ((i) bVar).f22518a;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(bVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, bVar.getClass(), sb2).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static a V(b bVar, y9.i iVar) {
        if (iVar instanceof b0) {
            v9.w wVar = (v9.w) iVar;
            return new a(bVar, new y0(p0.f22240b.f(wVar.I0(), wVar.G0())));
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static Collection W(y9.m mVar) {
        if (mVar instanceof o0) {
            return ((o0) mVar).d();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static o0 X(y9.i iVar) {
        if (iVar instanceof b0) {
            return ((b0) iVar).I0();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static i Y(y9.c cVar) {
        if (cVar instanceof g) {
            return ((g) cVar).f22511m;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, cVar.getClass(), sb2).toString());
    }

    public static b0 Z(y9.f fVar) {
        if (fVar instanceof v9.q) {
            return ((v9.q) fVar).f22242m;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(fVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, fVar.getClass(), sb2).toString());
    }

    public static /* synthetic */ void a(int i10) {
        Object[] objArr = new Object[3];
        switch (i10) {
            case 1:
            case 4:
                objArr[0] = "b";
                break;
            case 2:
            case 7:
                objArr[0] = "typeCheckingProcedure";
                break;
            case 3:
            default:
                objArr[0] = CmcdData.OBJECT_TYPE_AUDIO_ONLY;
                break;
            case 5:
            case 10:
                objArr[0] = "subtype";
                break;
            case 6:
            case 11:
                objArr[0] = "supertype";
                break;
            case 8:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 9:
                objArr[0] = "typeProjection";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/types/checker/TypeCheckerProcedureCallbacksImpl";
        switch (i10) {
            case 3:
            case 4:
                objArr[2] = "assertEqualTypeConstructors";
                break;
            case 5:
            case 6:
            case 7:
                objArr[2] = "assertSubtype";
                break;
            case 8:
            case 9:
                objArr[2] = "capture";
                break;
            case 10:
            case 11:
                objArr[2] = "noCorrespondingSupertype";
                break;
            default:
                objArr[2] = "assertEqualTypes";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static b0 a0(y9.i iVar, boolean z) {
        if (iVar instanceof b0) {
            return ((b0) iVar).M0(z);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static boolean b(y9.m mVar, y9.m mVar2) {
        if (!(mVar instanceof o0)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(mVar);
            sb2.append(", ");
            throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
        }
        if (mVar2 instanceof o0) {
            return mVar.equals(mVar2);
        }
        StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb3.append(mVar2);
        sb3.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar2.getClass(), sb3).toString());
    }

    public static y9.h b0(b bVar, y9.h hVar) {
        if (hVar instanceof y9.i) {
            return bVar.p((y9.i) hVar, true);
        }
        if (!(hVar instanceof y9.f)) {
            throw new IllegalStateException("sealed");
        }
        y9.f fVar = (y9.f) hVar;
        return bVar.F(bVar.p(bVar.l(fVar), true), bVar.p(bVar.d0(fVar), true));
    }

    public static int c(y9.h hVar) {
        if (hVar instanceof v9.w) {
            return ((v9.w) hVar).G0().size();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    public static y9.k d(y9.i iVar) {
        if (iVar instanceof b0) {
            return (y9.k) iVar;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static y9.c e(b bVar, y9.i iVar) {
        if (iVar instanceof b0) {
            if (iVar instanceof e0) {
                return bVar.E(((e0) iVar).f22203l);
            }
            if (iVar instanceof g) {
                return (g) iVar;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static v9.m f(y9.i iVar) {
        if (iVar instanceof b0) {
            if (iVar instanceof v9.m) {
                return (v9.m) iVar;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(iVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
    }

    public static v9.q g(y9.h hVar) {
        if (hVar instanceof v9.w) {
            d1 d1VarL0 = ((v9.w) hVar).L0();
            if (d1VarL0 instanceof v9.q) {
                return (v9.q) d1VarL0;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    public static b0 h(y9.h hVar) {
        if (hVar instanceof v9.w) {
            d1 d1VarL0 = ((v9.w) hVar).L0();
            if (d1VarL0 instanceof b0) {
                return (b0) d1VarL0;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    public static t0 i(y9.h hVar) {
        if (hVar instanceof v9.w) {
            return new t0(1, (v9.w) hVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    public static b0 j(y9.i iVar) {
        List listG0;
        ArrayList arrayList;
        d dVar;
        if (!(iVar instanceof b0)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(iVar);
            sb2.append(", ");
            throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
        }
        b0 b0Var = (b0) iVar;
        h hVar = null;
        if (b0Var.G0().size() == b0Var.I0().getParameters().size() && ((listG0 = b0Var.G0()) == null || !listG0.isEmpty())) {
            Iterator it = listG0.iterator();
            while (it.hasNext()) {
                if (((r0) it.next()).c() != 1) {
                    ArrayList<x6.x> arrayListI1 = kotlin.collections.x.i1(listG0, b0Var.I0().getParameters());
                    arrayList = new ArrayList(kotlin.collections.s.U(arrayListI1, 10));
                    for (x6.x xVar : arrayListI1) {
                        r0 t0Var = (r0) xVar.f22608i;
                        g8.b1 b1Var = (g8.b1) xVar.f22609l;
                        if (t0Var.c() != 1) {
                            t0Var = new t0(1, new g(1, new i(t0Var, hVar, b1Var, 6), (t0Var.b() || t0Var.c() != 2) ? null : t0Var.getType().L0(), (k0) null, false, 56));
                        }
                        arrayList.add(t0Var);
                    }
                    y0 y0Var = new y0(p0.f22240b.f(b0Var.I0(), arrayList));
                    int size = listG0.size();
                    for (int i10 = 0; i10 < size; i10++) {
                        r0 r0Var = (r0) listG0.get(i10);
                        r0 r0Var2 = (r0) arrayList.get(i10);
                        if (r0Var.c() != 1) {
                            List upperBounds = ((g8.b1) b0Var.I0().getParameters().get(i10)).getUpperBounds();
                            ArrayList arrayList2 = new ArrayList();
                            Iterator it2 = upperBounds.iterator();
                            while (true) {
                                boolean zHasNext = it2.hasNext();
                                dVar = d.f22507a;
                                if (!zHasNext) {
                                    break;
                                }
                                arrayList2.add(dVar.a(y0Var.g(1, (v9.w) it2.next()).L0()));
                            }
                            if (!r0Var.b() && r0Var.c() == 3) {
                                arrayList2.add(dVar.a(r0Var.getType().L0()));
                            }
                            i iVar2 = ((g) r0Var2.getType()).f22511m;
                            iVar2.getClass();
                            iVar2.f22519b = new h(1, arrayList2);
                        }
                    }
                }
            }
            arrayList = null;
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            return v9.e.r(arrayList, b0Var.H0(), b0Var.I0(), b0Var.J0());
        }
        return null;
    }

    public static int k(y9.c cVar) {
        if (cVar instanceof g) {
            return ((g) cVar).f22510l;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, cVar.getClass(), sb2).toString());
    }

    public static n0 l(int i10, boolean z) {
        return new n0(z, true, n.f22527a, d.f22507a, e.f22508a);
    }

    public static d1 m(b bVar, y9.i iVar, y9.i iVar2) {
        if (!(iVar instanceof b0)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(bVar);
            sb2.append(", ");
            throw new IllegalArgumentException(g8.b.h(l0.f19747a, bVar.getClass(), sb2).toString());
        }
        if (iVar2 instanceof b0) {
            return v9.e.j((b0) iVar, (b0) iVar2);
        }
        StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb3.append(bVar);
        sb3.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, bVar.getClass(), sb3).toString());
    }

    public static final String n(o0 o0Var) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("type: " + o0Var);
        sb2.append('\n');
        sb2.append("hashCode: " + o0Var.hashCode());
        sb2.append('\n');
        sb2.append("javaClass: " + o0Var.getClass().getCanonicalName());
        sb2.append('\n');
        for (g8.k kVarE = o0Var.e(); kVarE != null; kVarE = kVarE.d()) {
            sb2.append("fqName: ".concat(h9.h.f15907c.v(kVarE)));
            sb2.append('\n');
            sb2.append("javaClass: " + kVarE.getClass().getCanonicalName());
            sb2.append('\n');
        }
        return sb2.toString();
    }

    public static y9.l o(y9.h hVar, int i10) {
        if (hVar instanceof v9.w) {
            return (y9.l) ((v9.w) hVar).G0().get(i10);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(hVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, hVar.getClass(), sb2).toString());
    }

    public static y9.n p(y9.m mVar, int i10) {
        if (mVar instanceof o0) {
            return (y9.n) ((o0) mVar).getParameters().get(i10);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static d1 q(y9.l lVar) {
        if (lVar instanceof r0) {
            return ((r0) lVar).getType().L0();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(lVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, lVar.getClass(), sb2).toString());
    }

    public static g8.b1 r(y9.s sVar) {
        if (sVar instanceof m) {
            return ((m) sVar).a();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(sVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, sVar.getClass(), sb2).toString());
    }

    public static g8.b1 s(y9.m mVar) {
        if (mVar instanceof o0) {
            g8.h hVarE = ((o0) mVar).e();
            if (hVarE instanceof g8.b1) {
                return (g8.b1) hVarE;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static int t(y9.l lVar) {
        if (lVar instanceof r0) {
            return f4.f.f(((r0) lVar).c());
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(lVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, lVar.getClass(), sb2).toString());
    }

    public static int u(y9.n nVar) {
        if (nVar instanceof g8.b1) {
            return f4.f.f(((g8.b1) nVar).y());
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(nVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, nVar.getClass(), sb2).toString());
    }

    public static boolean v(y9.n nVar, y9.m mVar) {
        if (!(nVar instanceof g8.b1)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(nVar);
            sb2.append(", ");
            throw new IllegalArgumentException(g8.b.h(l0.f19747a, nVar.getClass(), sb2).toString());
        }
        if (mVar == null ? true : mVar instanceof o0) {
            return xc.d.q0((g8.b1) nVar, (o0) mVar, 4);
        }
        StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb3.append(nVar);
        sb3.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, nVar.getClass(), sb3).toString());
    }

    public static boolean w(y9.i iVar, y9.i iVar2) {
        if (!(iVar instanceof b0)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(iVar);
            sb2.append(", ");
            throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar.getClass(), sb2).toString());
        }
        if (iVar2 instanceof b0) {
            return ((b0) iVar).G0() == ((b0) iVar2).G0();
        }
        StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb3.append(iVar2);
        sb3.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, iVar2.getClass(), sb3).toString());
    }

    public static final d1 x(ArrayList arrayList) {
        b0 b0Var;
        int size = arrayList.size();
        if (size == 0) {
            throw new IllegalStateException("Expected some types");
        }
        if (size == 1) {
            return (d1) kotlin.collections.x.R0(arrayList);
        }
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        boolean z = false;
        boolean z5 = false;
        while (it.hasNext()) {
            d1 d1Var = (d1) it.next();
            z = z || v9.c.g(d1Var);
            if (d1Var instanceof b0) {
                b0Var = (b0) d1Var;
            } else {
                if (!(d1Var instanceof v9.q)) {
                    throw new NoWhenBranchMatchedException();
                }
                b0Var = ((v9.q) d1Var).f22241l;
                z5 = true;
            }
            arrayList2.add(b0Var);
        }
        if (z) {
            return x9.k.b(x9.j.H, arrayList.toString());
        }
        w wVar = w.f22536a;
        if (!z5) {
            return wVar.b(arrayList2);
        }
        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList3.add(v9.c.v((d1) it2.next()));
        }
        return v9.e.j(wVar.b(arrayList2), wVar.b(arrayList3));
    }

    public static boolean y(y9.m mVar) {
        if (mVar instanceof o0) {
            return d8.k.I((o0) mVar, d8.p.f14732a);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }

    public static boolean z(y9.m mVar) {
        if (mVar instanceof o0) {
            return ((o0) mVar).e() instanceof g8.f;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(", ");
        throw new IllegalArgumentException(g8.b.h(l0.f19747a, mVar.getClass(), sb2).toString());
    }
}
