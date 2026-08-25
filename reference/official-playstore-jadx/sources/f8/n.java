package f8;

import a8.g0;
import androidx.work.impl.t;
import com.google.common.util.concurrent.r0;
import g8.b0;
import g8.e1;
import g8.u0;
import g8.w;
import g8.y;
import j8.d0;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.z;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.v;
import u9.s;
import v9.x;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class n implements i8.b, i8.d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f15334h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0 f15335a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f15336b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v9.b0 f15337c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f15338d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u9.a f15339e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s f15340f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u9.q f15341g;

    static {
        m0 m0Var = l0.f19747a;
        f15334h = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(n.class), "settings", "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltIns$Settings;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(n.class), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(n.class), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;"))};
    }

    public n(d0 d0Var, u9.p pVar, g0 g0Var) {
        this.f15335a = d0Var;
        this.f15336b = new u9.l(pVar, g0Var);
        j8.m mVar = new j8.m(new j(d0Var, new f9.c("java.io"), 0), f9.f.j("Serializable"), 4, 2, Collections.singletonList(new x(pVar, new k(this, 0))), pVar);
        mVar.G0(p9.m.f21235b, kotlin.collections.b0.f19686i, null);
        this.f15337c = mVar.m();
        this.f15338d = new u9.l(pVar, new a8.l0(this, pVar, 13));
        this.f15339e = pVar.a();
        this.f15340f = new u9.l(pVar, new k(this, 1));
        this.f15341g = pVar.g(new ab.h(this, 14));
    }

    @Override // i8.b
    public final Collection a(g8.f fVar) {
        g8.f fVarA;
        f9.e eVarG;
        y8.q qVar = y8.q.f22971d;
        if (fVar.p0() == 1) {
            g().getClass();
            t8.i iVarF = f(fVar);
            if (iVarF != null && (fVarA = e.a(m9.d.g(iVarF), b.f15293f)) != null) {
                y0 y0Var = new y0(t.k(fVarA, iVarF));
                List list = (List) iVarF.A.f21953q.invoke();
                ArrayList<g8.e> arrayList = new ArrayList();
                for (Object obj : list) {
                    g8.e eVar = (g8.e) obj;
                    if (eVar.getVisibility().f15522a.f15521b) {
                        Collection collectionH = fVarA.h();
                        if (!(collectionH instanceof Collection) || !collectionH.isEmpty()) {
                            Iterator it = collectionH.iterator();
                            while (it.hasNext()) {
                                if (i9.n.j((g8.e) it.next(), eVar.b(y0Var)) == 1) {
                                    break;
                                }
                            }
                        }
                        if (eVar.e().size() == 1) {
                            g8.h hVarE = ((e1) kotlin.collections.x.R0(eVar.e())).getType().I0().e();
                            if (hVarE != null) {
                                int i10 = m9.d.f20302a;
                                eVarG = i9.f.g(hVarE);
                            } else {
                                eVarG = null;
                            }
                            int i11 = m9.d.f20302a;
                            if (kotlin.jvm.internal.p.a(eVarG, i9.f.g(fVar))) {
                            }
                        }
                        if (!d8.k.C(eVar)) {
                            LinkedHashSet linkedHashSet = q.f15353f;
                            String strF = r0.f(eVar, 3);
                            String str = d.f15297a;
                            f9.b bVarE = d.e(m9.d.g(iVarF).i());
                            if (!linkedHashSet.contains((bVarE != null ? n9.b.b(bVarE).e() : t.i(iVarF, qVar)) + '.' + strF)) {
                                arrayList.add(obj);
                            }
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
                for (g8.e eVar2 : arrayList) {
                    w.a aVarC0 = eVar2.C0();
                    aVarC0.p(fVar);
                    aVarC0.j(fVar.m());
                    aVarC0.l();
                    aVarC0.c(y0Var.f());
                    LinkedHashSet linkedHashSet2 = q.f15354g;
                    String strF2 = r0.f(eVar2, 3);
                    String str2 = d.f15297a;
                    f9.b bVarE2 = d.e(m9.d.g(iVarF).i());
                    if (!linkedHashSet2.contains((bVarE2 != null ? n9.b.b(bVarE2).e() : t.i(iVarF, qVar)) + '.' + strF2)) {
                        kotlin.reflect.m mVar = f15334h[2];
                        aVarC0.o((h8.h) this.f15340f.invoke());
                    }
                    arrayList2.add((g8.e) aVarC0.build());
                }
                return arrayList2;
            }
        }
        return z.f19728i;
    }

    @Override // i8.b
    public final Collection b(g8.f fVar) {
        int i10 = m9.d.f20302a;
        f9.e eVarG = i9.f.g(fVar);
        LinkedHashSet linkedHashSet = q.f15348a;
        f9.e eVar = d8.p.f14739g;
        boolean zEquals = eVarG.equals(eVar);
        boolean zIsAssignableFrom = false;
        v9.b0 b0Var = this.f15337c;
        if (!zEquals) {
            HashMap map = d8.p.c0;
            if (map.get(eVarG) == null) {
                if (eVarG.equals(eVar) || map.get(eVarG) != null) {
                    zIsAssignableFrom = true;
                } else {
                    String str = d.f15297a;
                    f9.b bVarE = d.e(eVarG);
                    if (bVarE != null) {
                        try {
                            zIsAssignableFrom = Serializable.class.isAssignableFrom(Class.forName(bVarE.b().b()));
                        } catch (ClassNotFoundException unused) {
                        }
                    }
                }
                return zIsAssignableFrom ? Collections.singletonList(b0Var) : z.f19728i;
            }
        }
        kotlin.reflect.m mVar = f15334h[1];
        return t7.a.E((v9.b0) this.f15338d.invoke(), b0Var);
    }

    @Override // i8.b
    public final Collection c(g8.f fVar) {
        Set setA;
        g().getClass();
        t8.i iVarF = f(fVar);
        if (iVarF == null || (setA = iVarF.G0().a()) == null) {
            setA = kotlin.collections.b0.f19686i;
        }
        return setA;
    }

    @Override // i8.d
    public final boolean d(g8.f fVar, v vVar) {
        t8.i iVarF = f(fVar);
        if (iVarF == null || !vVar.getAnnotations().x(i8.e.f16242a)) {
            return true;
        }
        g().getClass();
        String strF = r0.f(vVar, 3);
        Collection collectionC = iVarF.G0().c(vVar.getName(), o8.e.f20793i);
        if ((collectionC instanceof Collection) && collectionC.isEmpty()) {
            return false;
        }
        Iterator it = collectionC.iterator();
        while (it.hasNext()) {
            if (r0.f((u0) it.next(), 3).equals(strF)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0161 A[EDGE_INSN: B:117:0x0161->B:43:0x0161 BREAK  A[LOOP:3: B:56:0x018e->B:118:?]] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0206  */
    @Override // i8.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Collection e(f9.f r18, g8.f r19) {
        /*
            Method dump skipped, instruction units count: 790
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: f8.n.e(f9.f, g8.f):java.util.Collection");
    }

    public final t8.i f(g8.f fVar) {
        if (fVar == null) {
            d8.k.a(109);
            throw null;
        }
        f9.f fVar2 = d8.k.f14705e;
        if (!d8.k.b(fVar, d8.p.f14732a) && d8.k.J(fVar)) {
            int i10 = m9.d.f20302a;
            f9.e eVarG = i9.f.g(fVar);
            if (eVarG.d()) {
                String str = d.f15297a;
                f9.b bVarE = d.e(eVarG);
                if (bVarE != null) {
                    g8.f fVarI = y.i(g().f15318a, bVarE.b());
                    if (fVarI instanceof t8.i) {
                        return (t8.i) fVarI;
                    }
                }
            }
        }
        return null;
    }

    public final g g() {
        kotlin.reflect.m mVar = f15334h[0];
        return (g) this.f15336b.invoke();
    }
}
