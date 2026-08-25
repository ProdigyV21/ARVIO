package s8;

import ea.o;
import g8.l0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.z;
import r7.l;
import t8.p;
import x6.q;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.core.provider.e f21675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u9.a f21676b;

    public f(a aVar) {
        this.f21675a = new androidx.core.provider.e(aVar, b.f21668b, new q(null));
        this.f21676b = aVar.f21645a.a();
    }

    @Override // g8.l0
    public final boolean a(f9.c cVar) {
        ((a) this.f21675a.f2140l).f21646b.d(cVar);
        return false;
    }

    @Override // g8.l0
    public final void b(f9.c cVar, ArrayList arrayList) {
        o.d(arrayList, d(cVar));
    }

    @Override // g8.i0
    public final List c(f9.c cVar) {
        return t7.a.F(d(cVar));
    }

    public final p d(f9.c cVar) {
        return (p) this.f21676b.a(cVar, new a8.l0(this, ((a) this.f21675a.f2140l).f21646b.d(cVar), 21));
    }

    @Override // g8.i0
    public final Collection p(f9.c cVar, l lVar) {
        p pVarD = d(cVar);
        List list = pVarD != null ? (List) pVarD.f21965u.invoke() : null;
        return list == null ? z.f19728i : list;
    }

    public final String toString() {
        return "LazyJavaPackageFragmentProvider of module " + ((a) this.f21675a.f2140l).f21658o;
    }
}
