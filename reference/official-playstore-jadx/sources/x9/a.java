package x9;

import g8.q;
import g8.v0;
import j8.m;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.collections.z;
import p9.n;
import u9.p;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends m {
    /* JADX WARN: Illegal instructions before constructor call */
    public a(f9.f fVar) {
        k kVar = k.f22706a;
        e eVar = k.f22707b;
        u9.d dVar = p.f22089e;
        List list = z.f19728i;
        super(eVar, fVar, 3, 1, list, dVar);
        j8.k kVar2 = new j8.k(this, null, h8.g.f15863a, true, 1, v0.f15554b);
        kVar2.T0(list, q.f15527d);
        g gVarA = k.a(9, false, (String[]) Arrays.copyOf(new String[]{kVar2.getName().f15368i, ""}, 2));
        j jVar = j.F;
        kVar2.f19379q = new h(k.c(jVar, new String[0]), gVarA, jVar, list, false, new String[0]);
        G0(gVarA, Collections.singleton(kVar2), kVar2);
    }

    @Override // j8.m
    public final String toString() {
        return getName().d();
    }

    @Override // j8.b, j8.b0
    public final n v(v9.v0 v0Var, w9.e eVar) {
        String[] strArr = {getName().f15368i, v0Var.toString()};
        k kVar = k.f22706a;
        return k.a(9, false, (String[]) Arrays.copyOf(strArr, 2));
    }

    @Override // j8.b
    /* JADX INFO: renamed from: D0 */
    public final g8.f b(y0 y0Var) {
        return this;
    }

    @Override // j8.b, g8.x0
    public final g8.l b(y0 y0Var) {
        return this;
    }
}
