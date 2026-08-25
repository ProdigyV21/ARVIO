package t8;

import androidx.appcompat.app.i1;
import androidx.media3.extractor.text.ttml.TtmlNode;
import g8.s0;
import j8.v0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import p8.k0;
import v9.d1;

/* JADX INFO: loaded from: classes5.dex */
public abstract class z extends p9.o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f21988m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.core.provider.e f21989b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final z f21990c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u9.s f21991d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u9.s f21992e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u9.q f21993f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u9.r f21994g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final u9.q f21995h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u9.s f21996i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final u9.s f21997j;
    public final u9.s k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final u9.q f21998l;

    static {
        m0 m0Var = l0.f19747a;
        f21988m = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(z.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(z.class), "propertyNamesLazy", "getPropertyNamesLazy()Ljava/util/Set;")), m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(z.class), "classNamesLazy", "getClassNamesLazy()Ljava/util/Set;"))};
    }

    public z(androidx.core.provider.e eVar, n nVar) {
        this.f21989b = eVar;
        this.f21990c = nVar;
        u9.v vVar = ((s8.a) eVar.f2140l).f21645a;
        this.f21991d = vVar.f(new w(this, 0));
        this.f21992e = vVar.d(new w(this, 2));
        this.f21993f = vVar.g(new x(this, 1));
        this.f21994g = vVar.e(new x(this, 0));
        this.f21995h = vVar.g(new x(this, 2));
        this.f21996i = vVar.d(new w(this, 3));
        this.f21997j = vVar.d(new w(this, 4));
        this.k = vVar.d(new w(this, 1));
        this.f21998l = vVar.g(new x(this, 3));
    }

    public static androidx.mediarouter.media.x t(androidx.core.provider.e eVar, j8.w wVar, List list) throws Throwable {
        x6.x xVar;
        f9.f name;
        i1 i1Var = (i1) eVar.f2144p;
        s8.a aVar = (s8.a) eVar.f2140l;
        g8.b0 b0Var = aVar.f21658o;
        ab.k kVarH1 = kotlin.collections.x.h1(list);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(kVarH1, 10));
        Iterator it = kVarH1.iterator();
        boolean z = false;
        while (true) {
            ga.d dVar = (ga.d) it;
            if (!dVar.f15570m.hasNext()) {
                return new androidx.mediarouter.media.x(kotlin.collections.x.c1(arrayList), z);
            }
            kotlin.collections.d0 d0Var = (kotlin.collections.d0) dVar.next();
            int i10 = d0Var.f19688a;
            w8.z zVar = (w8.z) d0Var.f19689b;
            s8.e eVarZ = qb.l.z(eVar, zVar);
            u8.a aVarC = qb.l.C(2, false, null, 7);
            if (zVar.f()) {
                w8.w type = zVar.getType();
                w8.f fVar = type instanceof w8.f ? (w8.f) type : null;
                if (fVar == null) {
                    throw new AssertionError("Vararg parameter should be an array: " + zVar);
                }
                d1 d1VarC0 = i1Var.c0(fVar, aVarC, true);
                xVar = new x6.x(d1VarC0, b0Var.j().f(d1VarC0));
            } else {
                xVar = new x6.x(i1Var.d0(zVar.getType(), aVarC), null);
            }
            v9.w wVar2 = (v9.w) xVar.f22608i;
            v9.w wVar3 = (v9.w) xVar.f22609l;
            if (kotlin.jvm.internal.p.a(wVar.getName().d(), "equals") && list.size() == 1 && b0Var.j().o().equals(wVar2)) {
                name = f9.f.j("other");
            } else {
                name = zVar.getName();
                if (name == null) {
                    z = true;
                }
                if (name == null) {
                    name = f9.f.j(TtmlNode.TAG_P + i10);
                }
            }
            arrayList.add(new v0(wVar, null, i10, eVarZ, name, wVar2, false, false, false, wVar3, aVar.f21654j.c(zVar)));
        }
    }

    @Override // p9.o, p9.n
    public final Set a() {
        kotlin.reflect.m mVar = f21988m[0];
        return (Set) this.f21996i.invoke();
    }

    @Override // p9.o, p9.n
    public final Set b() {
        kotlin.reflect.m mVar = f21988m[1];
        return (Set) this.f21997j.invoke();
    }

    @Override // p9.o, p9.n
    public Collection c(f9.f fVar, o8.b bVar) {
        return !a().contains(fVar) ? kotlin.collections.z.f19728i : (Collection) this.f21995h.invoke(fVar);
    }

    @Override // p9.o, p9.n
    public final Set d() {
        kotlin.reflect.m mVar = f21988m[2];
        return (Set) this.k.invoke();
    }

    @Override // p9.o, p9.p
    public Collection f(p9.f fVar, r7.l lVar) {
        return (Collection) this.f21991d.invoke();
    }

    @Override // p9.o, p9.n
    public Collection g(f9.f fVar, o8.e eVar) {
        return !b().contains(fVar) ? kotlin.collections.z.f19728i : (Collection) this.f21998l.invoke(fVar);
    }

    public abstract Set h(p9.f fVar, r7.l lVar);

    public abstract Set i(p9.f fVar, p9.k kVar);

    public abstract c k();

    public abstract void l(LinkedHashSet linkedHashSet, f9.f fVar);

    public abstract void m(f9.f fVar, ArrayList arrayList);

    public abstract Set n();

    public abstract s0 o();

    public abstract g8.k p();

    public boolean q(r8.g gVar) {
        return true;
    }

    public abstract v r(w8.q qVar, ArrayList arrayList, v9.w wVar, List list);

    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object, x6.s] */
    public final r8.g s(w8.q qVar) throws Throwable {
        androidx.core.provider.e eVar = this.f21989b;
        int i10 = 1;
        r8.g gVarV0 = r8.g.V0(p(), qb.l.z(eVar, qVar), qVar.getName(), ((s8.a) eVar.f2140l).f21654j.c(qVar), ((c) this.f21992e.invoke()).f(qVar.getName()) != null && ((ArrayList) qVar.e()).isEmpty());
        androidx.core.provider.e eVarG = qb.d.g(eVar, gVarV0, qVar, 0, eVar.f2142n);
        ArrayList typeParameters = qVar.getTypeParameters();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(typeParameters, 10));
        Iterator it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((s8.h) eVarG.f2141m).a((w8.x) it.next()));
        }
        androidx.mediarouter.media.x xVarT = t(eVarG, gVarV0, qVar.e());
        v vVarR = r(qVar, arrayList, ((i1) eVarG.f2144p).d0(qVar.y(), qb.l.C(2, qVar.l().f20288a.isAnnotation(), null, 6)), xVarT.f4506b);
        List list = vVarR.f21979d;
        s0 s0VarO = o();
        ArrayList arrayList2 = vVarR.f21978c;
        List list2 = vVarR.f21977b;
        v9.w wVar = vVarR.f21976a;
        boolean zIsAbstract = qVar.isAbstract();
        boolean zIsFinal = qVar.isFinal();
        if (zIsAbstract) {
            i10 = 4;
        } else if (!zIsFinal) {
            i10 = 3;
        }
        gVarV0.U0(null, s0VarO, kotlin.collections.z.f19728i, arrayList2, list2, wVar, i10, k0.a(qVar.getVisibility()), kotlin.collections.a0.f19683i);
        gVarV0.W0(false, xVarT.f4507c);
        if (list.isEmpty()) {
            return gVarV0;
        }
        ((s8.a) eVarG.f2140l).f21649e.b(gVarV0, list);
        throw null;
    }

    public String toString() {
        return "Lazy scope for " + p();
    }

    public void j(f9.f fVar, ArrayList arrayList) {
    }
}
