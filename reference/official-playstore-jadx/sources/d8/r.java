package d8;

import g8.w;
import j8.e0;
import j8.s0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e0 f14772a;

    static {
        x9.k kVar = x9.k.f22706a;
        f8.j jVar = new f8.j(x9.k.f22707b, q.f14761f, 1);
        f9.f fVarF = q.f14762g.f();
        u9.d dVar = u9.p.f22089e;
        e0 e0Var = new e0(jVar, fVarF, dVar);
        e0Var.f19263r = 4;
        e0Var.f19264s = g8.q.f15528e;
        List listSingletonList = Collections.singletonList(s0.J0(e0Var, 2, f9.f.j("T"), 0, dVar));
        if (e0Var.f19266u != null) {
            throw new IllegalStateException("Type parameters are already set for " + e0Var.getName());
        }
        ArrayList arrayList = new ArrayList(listSingletonList);
        e0Var.f19266u = arrayList;
        e0Var.f19265t = new v9.j(e0Var, arrayList, e0Var.f19267v, e0Var.f19268w);
        Set set = Collections.EMPTY_SET;
        if (set == null) {
            e0.A0(13);
            throw null;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((j8.k) ((w) it.next())).Q0(e0Var.m());
        }
        f14772a = e0Var;
    }
}
