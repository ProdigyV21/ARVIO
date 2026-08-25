package t8;

import androidx.appcompat.app.i1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import v9.b1;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends j8.c {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final androidx.core.provider.e f21916u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final w8.x f21917v;

    /* JADX WARN: Illegal instructions before constructor call */
    public e0(androidx.core.provider.e eVar, w8.x xVar, int i10, g8.l lVar) {
        s8.a aVar = (s8.a) eVar.f2140l;
        super(aVar.f21645a, lVar, new s8.e(eVar, xVar, false), xVar.getName(), 1, false, i10, aVar.f21656m);
        this.f21916u = eVar;
        this.f21917v = xVar;
    }

    @Override // j8.j
    public final List G0(List list) {
        x8.m mVar;
        androidx.core.provider.e eVar = this.f21916u;
        x8.m mVar2 = ((s8.a) eVar.f2140l).f21661r;
        mVar2.getClass();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            v9.w wVarA = (v9.w) it.next();
            if (b1.c(wVarA, x8.l.f22665n, null)) {
                mVar = mVar2;
            } else {
                mVar = mVar2;
                wVarA = mVar.a(new c2.c((h8.a) this, false, eVar, p8.a.TYPE_PARAMETER_BOUNDS, false), wVarA, kotlin.collections.z.f19728i, null, false);
                if (wVarA == null) {
                    wVarA = wVarA;
                }
            }
            arrayList.add(wVarA);
            mVar2 = mVar;
        }
        return arrayList;
    }

    @Override // j8.j
    public final List H0() {
        Collection upperBounds = this.f21917v.getUpperBounds();
        boolean zIsEmpty = upperBounds.isEmpty();
        androidx.core.provider.e eVar = this.f21916u;
        if (zIsEmpty) {
            return Collections.singletonList(v9.e.j(((s8.a) eVar.f2140l).f21658o.j().e(), ((s8.a) eVar.f2140l).f21658o.j().o()));
        }
        Collection collection = upperBounds;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collection, 10));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((i1) eVar.f2144p).d0((w8.j) it.next(), qb.l.C(2, false, this, 3)));
        }
        return arrayList;
    }
}
