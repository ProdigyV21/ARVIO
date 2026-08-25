package y8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends c implements y.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ d f22914d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(d dVar, a0 a0Var) {
        super(dVar, a0Var);
        this.f22914d = dVar;
    }

    public final m c(int i10, f9.b bVar, l8.a aVar) {
        a0 a0Var = new a0(this.f22915a.f22913a + '@' + i10);
        d dVar = this.f22914d;
        HashMap map = dVar.f22920b;
        List arrayList = (List) map.get(a0Var);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(a0Var, arrayList);
        }
        List list = arrayList;
        e eVar = dVar.f22919a;
        if (c8.b.f7422a.contains(bVar)) {
            return null;
        }
        n nVar = (n) eVar;
        return new m(nVar, g8.y.f(nVar.f22959m, bVar, nVar.f22960n), bVar, list, aVar);
    }
}
