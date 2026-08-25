package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.n0;
import g8.h0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.b0;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends s {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h0 f19898g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f19899h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f9.c f19900i;

    public t(h0 h0Var, n0 n0Var, c9.f fVar, c9.a aVar, y8.r rVar, s9.j jVar, String str, r7.a aVar2) {
        androidx.work.impl.constraints.j jVar2 = new androidx.work.impl.constraints.j(n0Var.f601q);
        c9.g gVar = c9.g.f7455b;
        c9.g gVarE = k2.c.e(n0Var.f602r);
        jVar.getClass();
        super(new s9.m(jVar, fVar, h0Var, jVar2, gVarE, aVar, rVar, null, z.f19728i), n0Var.f598n, n0Var.f599o, n0Var.f600p, aVar2);
        this.f19898g = h0Var;
        this.f19899h = str;
        this.f19900i = h0Var.c();
    }

    @Override // p9.o, p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        s9.m mVar = this.f19894b;
        xc.d.E0(mVar.f21731a.f21714i, bVar, this.f19898g, fVar);
        if (q(fVar)) {
            return (g8.f) mVar.f21731a.f21724t.f21696b.invoke(new s9.f(l(fVar), null));
        }
        n nVar = this.f19895c;
        if (nVar.d().contains(fVar)) {
            return nVar.g(fVar);
        }
        return null;
    }

    @Override // p9.o, p9.p
    public final Collection f(p9.f fVar, r7.l lVar) {
        List listI = i(fVar, lVar);
        Iterable iterable = this.f19894b.f21731a.k;
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            kotlin.collections.x.b0(arrayList, ((i8.c) it.next()).c(this.f19900i));
        }
        return kotlin.collections.x.I0(listI, arrayList);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final f9.b l(f9.f fVar) {
        return new f9.b(this.f19900i, fVar);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final Set n() {
        return b0.f19686i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final Set o() {
        return b0.f19686i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final Set p() {
        return b0.f19686i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final boolean q(f9.f fVar) {
        if (super.q(fVar)) {
            return true;
        }
        Iterable iterable = this.f19894b.f21731a.k;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            if (((i8.c) it.next()).a(this.f19900i, fVar)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        return this.f19899h;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.s
    public final void h(ArrayList arrayList) {
    }
}
