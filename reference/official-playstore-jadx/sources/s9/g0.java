package s9;

import a9.k1;
import a9.r1;
import com.google.common.util.concurrent.p0;
import g8.b1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import v9.j0;
import v9.k0;
import v9.o0;
import v9.r0;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f21697a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g0 f21698b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f21699c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f21700d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u9.r f21701e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u9.r f21702f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f21703g;

    public g0(m mVar, g0 g0Var, List list, String str, String str2) {
        Map linkedHashMap;
        this.f21697a = mVar;
        this.f21698b = g0Var;
        this.f21699c = str;
        this.f21700d = str2;
        u9.p pVar = mVar.f21731a.f21706a;
        this.f21701e = pVar.e(new d0(this, 0));
        this.f21702f = pVar.e(new d0(this, 1));
        if (list.isEmpty()) {
            linkedHashMap = kotlin.collections.a0.f19683i;
        } else {
            linkedHashMap = new LinkedHashMap();
            Iterator it = list.iterator();
            int i10 = 0;
            while (it.hasNext()) {
                r1 r1Var = (r1) it.next();
                linkedHashMap.put(Integer.valueOf(r1Var.f648n), new kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.x(this.f21697a, r1Var, i10));
                i10++;
            }
        }
        this.f21703g = linkedHashMap;
    }

    public static v9.b0 a(v9.b0 b0Var, v9.w wVar) {
        d8.k kVarJ = b0Var.I0().j();
        h8.h annotations = b0Var.getAnnotations();
        v9.w wVarO = p0.o(b0Var);
        List listL = p0.l(b0Var);
        List listI0 = kotlin.collections.x.i0(1, p0.r(b0Var));
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listI0, 10));
        Iterator it = listI0.iterator();
        while (it.hasNext()) {
            arrayList.add(((r0) it.next()).getType());
        }
        return p0.h(kVarJ, annotations, wVarO, listL, arrayList, wVar, true).M0(b0Var.J0());
    }

    public static final ArrayList e(k1 k1Var, g0 g0Var) {
        List list = k1Var.f521n;
        androidx.work.impl.constraints.j jVar = g0Var.f21697a.f21734d;
        int i10 = k1Var.f520m;
        k1 k1VarB = (i10 & 256) == 256 ? k1Var.f530w : (i10 & 512) == 512 ? jVar.b(k1Var.x) : null;
        Iterable iterableE = k1VarB != null ? e(k1VarB, g0Var) : null;
        if (iterableE == null) {
            iterableE = kotlin.collections.z.f19728i;
        }
        return kotlin.collections.x.I0(list, iterableE);
    }

    public static k0 f(List list, h8.h hVar, o0 o0Var, g8.k kVar) {
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((j0) it.next()).a(hVar));
        }
        ArrayList arrayListV = kotlin.collections.s.V(arrayList);
        k0.f22220l.getClass();
        return fi.iki.elonen.f.H(arrayListV);
    }

    public static final g8.f h(g0 g0Var, k1 k1Var, int i10) {
        m mVar = g0Var.f21697a;
        f9.b bVarR = t7.a.r(mVar.f21732b, i10);
        List listV = ga.r.V(new ga.t(ga.r.M(k1Var, new d0(g0Var, 2)), f0.f21692l));
        int iF = ga.r.F(ga.r.M(bVarR, e0.f21689i));
        while (true) {
            ArrayList arrayList = (ArrayList) listV;
            if (arrayList.size() >= iF) {
                return mVar.f21731a.f21716l.v(bVarR, listV);
            }
            arrayList.add(0);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Map] */
    public final List b() {
        return kotlin.collections.x.c1(this.f21703g.values());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Map] */
    public final b1 c(int i10) {
        b1 b1Var = (b1) this.f21703g.get(Integer.valueOf(i10));
        if (b1Var != null) {
            return b1Var;
        }
        g0 g0Var = this.f21698b;
        if (g0Var != null) {
            return g0Var.c(i10);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x011b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final v9.b0 d(a9.k1 r25, boolean r26) {
        /*
            Method dump skipped, instruction units count: 962
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s9.g0.d(a9.k1, boolean):v9.b0");
    }

    public final v9.w g(k1 k1Var) {
        if ((k1Var.f520m & 2) != 2) {
            return d(k1Var, true);
        }
        m mVar = this.f21697a;
        String string = mVar.f21732b.getString(k1Var.f523p);
        v9.b0 b0VarD = d(k1Var, true);
        androidx.work.impl.constraints.j jVar = mVar.f21734d;
        int i10 = k1Var.f520m;
        return mVar.f21731a.f21715j.c(k1Var, string, b0VarD, d((i10 & 4) == 4 ? k1Var.f524q : (i10 & 8) == 8 ? jVar.b(k1Var.f525r) : null, true));
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f21699c);
        g0 g0Var = this.f21698b;
        if (g0Var == null) {
            str = "";
        } else {
            str = ". Child of " + g0Var.f21699c;
        }
        sb2.append(str);
        return sb2.toString();
    }
}
